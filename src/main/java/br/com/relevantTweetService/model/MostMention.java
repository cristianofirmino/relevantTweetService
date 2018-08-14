package br.com.relevantTweetService.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostMention {
	
	
	private String screen_name;
	List<MostMentionSummary> mostMentionSummaries;

}
