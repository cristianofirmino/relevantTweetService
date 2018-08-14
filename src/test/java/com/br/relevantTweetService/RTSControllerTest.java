package com.br.relevantTweetService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.relevantTweetService.RelevantTweetServiceApplication;
import br.com.relevantTweetService.controller.RTSController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RelevantTweetServiceApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RTSControllerTest {
	
	private MockMvc mockMvc;
	private MockHttpServletRequestBuilder getMostMentions = get("/api/most_mentions")
			.accept(MediaType.APPLICATION_JSON_UTF8);
	private MockHttpServletRequestBuilder getMostRelevants = get("/api/most_relevants")
			.accept(MediaType.APPLICATION_JSON_UTF8);

	@Autowired
	private RTSController rtsController;
	
	@Autowired
	RelevantTweetServiceApplication application;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(rtsController).build();
	}

	@Test
	public void test1_return_json_type_of_most_relevants() throws Exception {
		mockMvc
			.perform(getMostRelevants)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void test2_return_json_type_of_most_mentios() throws Exception {
		mockMvc
			.perform(getMostMentions)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void test3_most_relevants_is_array_and_not_null() throws Exception {
		mockMvc
			.perform(getMostRelevants)
			.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void test4_most_mentios_is_map_and_not_null() throws Exception {
		mockMvc
			.perform(getMostMentions)
			.andExpect(status().isOk())
				.andExpect(jsonPath("$").isMap())
				.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void test5_most_relevants_structure_of_objects() throws Exception {
		mockMvc
			.perform(getMostRelevants)
				.andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].followers_count").exists())
	            .andExpect(jsonPath("$[0].screen_name").exists())
	            .andExpect(jsonPath("$[0].profile_link").exists())
	            .andExpect(jsonPath("$[0].created_at").exists())
	            .andExpect(jsonPath("$[0].link").exists())
	            .andExpect(jsonPath("$[0].retweet_count").exists())
	            .andExpect(jsonPath("$[0].text").exists())
	            .andExpect(jsonPath("$[0].favorite_count").exists());

	}
	
	@Test
	public void test6_most_mentions_structure_of_objects() throws Exception {
		mockMvc
			.perform(getMostMentions)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isMap())
				.andExpect(jsonPath("$..[0]").isArray())
	            .andExpect(jsonPath("$..[0].followers_count").exists())
	            .andExpect(jsonPath("$..[0].screen_name").exists())
	            .andExpect(jsonPath("$..[0].profile_link").exists())
	            .andExpect(jsonPath("$..[0].created_at").exists())
	            .andExpect(jsonPath("$..[0].link").exists())
	            .andExpect(jsonPath("$..[0].retweet_count").exists())
	            .andExpect(jsonPath("$..[0].text").exists())
	            .andExpect(jsonPath("$..[0].favorite_count").exists());

	}
	
}
