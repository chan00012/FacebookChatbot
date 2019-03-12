package com.amdocs.facebookchatbot.dto;

import com.amdocs.facebookchatbot.enums.MessageType;
import com.amdocs.facebookchatbot.enums.SenderActionType;
import com.amdocs.facebookchatbot.model.UserEntity;
import com.amdocs.facebookchatbot.model.events.Messages;
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
public class MessengerRequest {
	
	@JsonProperty("messaging_type")
	private MessageType messagingType;
	private UserEntity recipient;
	private Messages message;
	
	@JsonProperty("sender_action")
	private SenderActionType senderAction;
	
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}
}
