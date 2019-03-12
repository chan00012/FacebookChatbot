package com.amdocs.facebookchatbot.model.events;

import java.util.List;

import com.amdocs.facebookchatbot.model.events.objects.Attachment;
import com.amdocs.facebookchatbot.model.events.objects.QuickReply;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import util.Utils;


@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Messages {
	
	private Long seq;
	
	@JsonProperty("sticker_id")
	private Long stickerId;
	
	private String mid;
	
	private String text;
	
	@JsonProperty("quick_reply")
	private QuickReply quickReply;
	
	@JsonProperty("quick_replies")
	private List<QuickReply> quickReplies;
	
	private List<Attachment> attachments;
	
	private Attachment attachment;
	
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}


}
