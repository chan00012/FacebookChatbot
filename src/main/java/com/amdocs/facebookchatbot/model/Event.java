package com.amdocs.facebookchatbot.model;

import com.amdocs.facebookchatbot.model.events.MessageReads;
import com.amdocs.facebookchatbot.model.events.Messages;
import com.amdocs.facebookchatbot.model.events.MessagingDeliveries;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import util.Utils;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	
	private UserEntity sender;
	
	private UserEntity recipient;
	
	private Long timestamp;
	
	private Messages message;
	
	private MessagingDeliveries delivery;
	
	private MessageReads read;
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}

}
