package com.amdocs.facebookchatbot.model.events.objects;

import com.amdocs.facebookchatbot.enums.AttachmentType;
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
public class Attachment {
	
	private AttachmentType type;
	
	@JsonProperty("payload")
	private Payload attachmentPayload;
	
	private String title;
	 
	private String url;
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}

}
