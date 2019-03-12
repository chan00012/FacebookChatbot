package com.amdocs.facebookchatbot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import util.Utils;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {
	
	private Long id;
	
	private Long time;
	
	@JsonProperty("messaging")
	private List<Event> events;
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}
	
	

}
