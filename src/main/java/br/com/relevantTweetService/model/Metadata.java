package br.com.relevantTweetService.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("metadata")
public class Metadata {
	
	private String iso_language_code;
	private String result_type;

}
