package com.amdocs.facebookchatbot.model;

import com.amdocs.facebookchatbot.enums.EventType;
import com.amdocs.facebookchatbot.model.events.MessageReads;
import com.amdocs.facebookchatbot.model.events.Messages;
import com.amdocs.facebookchatbot.model.events.MessagingDeliveries;
import com.amdocs.facebookchatbot.service.EventHandlerService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import util.Utils;

@Log4j2
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
	
	@JsonIgnoreProperties
	private EventType eventType;
	
	@Override
	public String toString() {
		return Utils.toJson(this);
	}
	
//	public void classifyEvent() {
//		if(this.getMessage() != null && this.getRecipient().getId().equals(EventHandlerService.pageId)) {
//			
//			if(this.getMessage().getAttachments() != null && 
//			   this.getMessage().getStickerId() == null ) {
//				
//				log.info("|EVENT HANDLED| Event Type: {}", EventType.MESSAGE_WITH_ATTACHMENTS);
//				this.eventType = EventType.MESSAGE_WITH_ATTACHMENTS;
//			}
//			
//			log.info("|EVENT HANDLED| Event Type: {}", EventType.MESSAGES);
//			this.eventType = EventType.MESSAGES;
//		}
//		
//		if(this.getMessage() != null && !this.getRecipient().getId().equals(EventHandlerService.pageId)) {
//			//log.info("|EVENT HANDLED| Event Type: {}", EventType.REPLY);
//			this.eventType = EventType.REPLY;
//		}
//		
//		if(this.getDelivery() != null) {
//			log.info("|EVENT HANDLED| Event Type: {}", EventType.MESSAGE_DELIVERIES);
//			this.eventType = EventType.MESSAGE_DELIVERIES;
//		}
//		
//		if(this.getRead()!= null) {
//			log.info("|EVENT HANDLED| Event Type: {}", EventType.MESSAGE_READS);
//			this.eventType = EventType.MESSAGE_READS;
//		}
//		
//		log.info("|EVENT NOT HANDLED| Uknown Event" );
//		this.eventType = null;
//	}
}


