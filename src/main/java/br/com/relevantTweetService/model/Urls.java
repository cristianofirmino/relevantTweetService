package br.com.relevantTweetService.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Urls {

	private String expanded_url;
	private String url;
	private List<Long> indices;
}
