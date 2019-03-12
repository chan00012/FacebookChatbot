package com.amdocs.facebookchatbot.model.events.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import util.Utils;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {
	
	private String url;
	
	@JsonProperty("sticker_id") 
	private Long stickerId;
	
	private Coordinates coordinates;
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}

}
