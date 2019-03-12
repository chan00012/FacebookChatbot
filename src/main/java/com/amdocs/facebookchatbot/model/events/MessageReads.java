package com.amdocs.facebookchatbot.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import util.Utils;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageReads {
	
	private Long watermark;
	
	private Long seq;

	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}
	
}
