package com.amdocs.facebookchatbot.model.events.objects;

import com.amdocs.facebookchatbot.enums.QuickReplyType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuickReply {
	
	@JsonProperty("content_type")
	QuickReplyType contentType;
	
	String title;
	
	Object payload;
	
	@JsonProperty("image_url")
	String imageUrl;

}
