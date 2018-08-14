package br.com.relevantTweetService.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tweet {

	private String coordinates;
	private boolean favorited;
	private boolean truncated;
	private String created_at;
	private String id_str;
	private Entities entities;
	private String in_reply_to_user_id_str;
	private String contributors;
	private String text;
	private Metadata metadata;
	private long retweet_count;
	private String in_reply_to_status_id_str;
	private long id;
	private String geo;
	private boolean retweeted;
	private long in_reply_to_user_id;
	private String place;
	private long favorite_count;
	private User user;
	private String in_reply_to_screen_name;
	private String source;
	private long in_reply_to_status_id;

	public long getFollowersCount() {
		return this.user.getFollowers_count();
	}

	public String getUsersMention(String compare) {
		
		List<UserMentions> user_mentions = this.getEntities().getUser_mentions();
		long id = Long.parseLong(compare);
		String idUserMention;
		
		UserMentions userMentions = user_mentions.stream()
				.filter(um -> id == um.getId())
				.findAny()
				.orElse(null);
		
		try {
			idUserMention = String.valueOf(userMentions.getId());
		} catch (Exception e) {
			idUserMention = "0";			
		}
		
		return idUserMention;
	}

}
