package br.com.relevantTweetService.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Entities {
	
	private List<String> urls;
	private List<String> hashtags;
	
	private List<UserMentions> user_mentions;
	
	
	

}
