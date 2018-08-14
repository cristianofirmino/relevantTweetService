package br.com.relevantTweetService.util;

import java.util.Comparator;
import java.util.List;

import br.com.relevantTweetService.model.SummaryTweet;
import br.com.relevantTweetService.model.Tweet;

public class Comparators {

	public static Comparator<Tweet> getTweetComparator() {
		return Comparator
				.comparing(Tweet::getFollowersCount)
					.thenComparing(Tweet::getRetweet_count)
					.thenComparing(Tweet::getFavorite_count)
						.reversed();
	}
	
	public static Comparator<List<SummaryTweet>> getListSummaryComparator() {
		return Comparator
					.comparing(list -> list.get(0),
							Comparator
								.comparingLong(SummaryTweet::getFollowers_count)
									.thenComparing(Comparator.comparingLong(SummaryTweet::getRetweet_count)
									.thenComparing(Comparator.comparingLong(SummaryTweet::getFavorite_count)))
										.reversed());
	}

	public static Comparator<SummaryTweet> getSummaryComparator() {
		return Comparator
				.comparing(SummaryTweet::getFollowers_count)
					.thenComparing(SummaryTweet::getRetweet_count)
					.thenComparing(SummaryTweet::getFavorite_count)
						.reversed();
	}
}
