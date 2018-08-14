package br.com.relevantTweetService.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tweets {

	@JsonProperty("statuses")
	private List<Tweet> tweets;

}
