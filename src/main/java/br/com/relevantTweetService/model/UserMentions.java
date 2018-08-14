package br.com.relevantTweetService.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserMentions {
	
	private String screen_name;
	private String name;
	private long id;
	private String id_str;
	private List<Long> indices;

}
