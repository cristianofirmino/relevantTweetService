package br.com.relevantTweetService.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.relevantTweetService.RelevantTweetServiceApplication;
import br.com.relevantTweetService.config.AppConfig;
import br.com.relevantTweetService.model.SummaryTweet;
import br.com.relevantTweetService.model.Tweet;
import br.com.relevantTweetService.model.Tweets;
import br.com.relevantTweetService.util.Comparators;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class RTSService {

	@Autowired
	private AppConfig appConfig;

	public List<SummaryTweet> getMostRevelants() throws Exception {

		return  getSummaryTweets(getTweetsList());
	}

	public Map<String, List<SummaryTweet>> getMostMentions() throws Exception {

		List<SummaryTweet> summaryTweets = getSummaryTweets(getTweetsList());
		Map<String, List<SummaryTweet>> mostMentionsMap = summaryTweets.stream()
				.collect(Collectors.groupingBy(SummaryTweet::getScreen_name));

		mostMentionsMap.forEach((k, v) -> {
			v.sort(Comparators.getSummaryComparator());
		});

		LinkedHashMap<String, List<SummaryTweet>> mostMentionsMapSorted = mostMentionsMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparators.getListSummaryComparator()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

		return mostMentionsMapSorted;
	}

	public Tweets getApiFromTweet() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Username", appConfig.getHttpUsername());
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RelevantTweetServiceApplication.Log.info("RTSService.getApiFromTweet :" + headers.toString());

		URI url = null;

		url = new URI(appConfig.getTwitterApiLink());

		RestTemplate restTemplate = new RestTemplate();
		Tweets tweets = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Tweets>() {
		}).getBody();

		return tweets;
	}

	public List<Tweet> getTweetsList() throws Exception {
		List<Tweet> tweets;

		try {
			tweets = getApiFromTweet().getTweets();
		} catch (Exception e) {
			throw new Exception(e);
		}

		List<Tweet> filteredTweets = tweetsFiltered(tweets);
		tweetsSortered(filteredTweets);
		return filteredTweets;
	}

	private void tweetsSortered(List<Tweet> tweets) {
		tweets.sort(Comparators.getTweetComparator());
	}

	private List<Tweet> tweetsFiltered(List<Tweet> tweets) {
		List<Tweet> filteredTweets = tweets.stream()
				.filter(t -> appConfig.getID().equals(t.getUsersMention(appConfig.getID())))
				.filter(t -> !t.getIn_reply_to_user_id_str().equals(appConfig.getID())).collect(Collectors.toList());
		
		return filteredTweets;
	}

	private List<SummaryTweet> getSummaryTweets(List<Tweet> filteredTweets) {
		List<SummaryTweet> summaryTweets = new ArrayList<>();
		filteredTweets.forEach(t -> {
			summaryTweets.add(new SummaryTweet(t.getFollowersCount(), t.getUser().getScreen_name(),
					appConfig.getProfileLink(t.getUser().getScreen_name()), t.getCreated_at(),
					appConfig.getTweetLink(t.getUser().getScreen_name(), t.getId()), t.getRetweet_count(), t.getText(),
					t.getFavorite_count()));
		});

		return summaryTweets;
	}

}
