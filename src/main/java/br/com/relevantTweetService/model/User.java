package br.com.relevantTweetService.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

	private String profile_sidebar_fill_color;
	private String profile_sidebar_border_color;
	private String profile_background_tile;
	private String name;
	private String profile_image_url;
	private String created_at;
	private String location;
	private String follow_request_sent;
	private String profile_link_color;
	private boolean is_translator;
	private String id_str;
	
	private EntitiesUser entities;
	
	private boolean default_profile;
	private boolean contributors_enabled;
	private long favourites_count;
	private String url;
	private String profile_image_url_https;
	private long utc_offset;
	
	@Id
	private long id;
	
	private boolean profile_use_background_image;
	private long listed_count;
	private String profile_text_color;
	private String lang;
	private long followers_count;
	
	@JsonProperty("protected")
	private boolean protecteD;
	
	private String notifications;
	private String profile_background_image_url_https;
	private String profile_background_color;
	private boolean verified;
	private boolean geo_enabled;
	private String time_zone;
	private String description;
	private boolean default_profile_image;
	private String profile_background_image_url;
	private long statuses_count;
	private long friends_count;
	private int following;
	private boolean show_all_inline_media;
	private String screen_name;	

}
