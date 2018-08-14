package br.com.relevantTweetService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@ComponentScan(basePackages = { " br.com.* " })
@Getter
public class AppConfig {

	@Value("${twitter.link}")
	private String twitterLink;
	
	@Value("${twitter.api.link}")
	private String twitterApiLink;
	
	private String httpUsername = System.getProperty("HTTP_USERNAME");
	
	@Value("${id}")
	private String iD;
	
	public String getProfileLink(String profile) {

		return twitterLink + profile;
	}
	
	public String getTweetLink(String profile, long tweet) {
		
		return getProfileLink(profile) + "/status/" + tweet;
		
	}
	


}
