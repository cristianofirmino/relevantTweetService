package br.com.relevantTweetService.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
		
public class MostMentionSummary {
	private List<SummaryTweet> mostMentions;
	
}
