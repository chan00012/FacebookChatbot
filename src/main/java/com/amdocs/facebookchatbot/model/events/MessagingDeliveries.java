package com.amdocs.facebookchatbot.model.events;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import util.Utils;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagingDeliveries {
	
	private List<String> mids;
	
	private Long watermark;
	
	private Long seq;
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}
	
}
