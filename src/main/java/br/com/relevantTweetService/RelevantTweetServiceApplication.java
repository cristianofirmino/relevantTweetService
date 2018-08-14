package br.com.relevantTweetService;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
public class RelevantTweetServiceApplication {

	public static Logger Log = LoggerFactory.getLogger("RelevantTweetServiceApplication");

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		SpringApplication.run(RelevantTweetServiceApplication.class, args);

	}
}
