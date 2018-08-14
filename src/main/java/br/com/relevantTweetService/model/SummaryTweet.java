package br.com.relevantTweetService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryTweet {

	private long followers_count;
	private String screen_name;
	private String profile_link;
	private String created_at;
	private String link;
	private long retweet_count;
	private String text;
	private long favorite_count;

}
