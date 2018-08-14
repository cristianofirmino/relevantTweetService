package br.com.relevantTweetService.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DescriptionEntity {
	
	private List<Urls> urls;

}
