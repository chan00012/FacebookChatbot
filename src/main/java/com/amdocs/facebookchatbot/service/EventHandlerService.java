package com.amdocs.facebookchatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amdocs.facebookchatbot.dto.ClientRequest;
import com.amdocs.facebookchatbot.enums.EventType;
import com.amdocs.facebookchatbot.model.Event;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EventHandlerService {
	
	@Value("${messenger.pageId}")
	private Long pageId;
	
	@Autowired
	ConversationService conversationService;
	
	public void handleRequest(ClientRequest cr) {
		cr.getEntry().forEach(entry ->{
			Event event = entry.getEvents().get(0);
			EventType eventType = classifyEvent(event);
			conversationService.replyOnConversation(event, eventType);
			//conversationService.initializeConversation(event, eventType);
		});
	}

	public EventType classifyEvent(Event event) {
		
		if(event.getMessage() != null && event.getRecipient().getId().equals(pageId)) {
			
			if(event.getMessage().getAttachments() != null && event.getMessage().getStickerId() == null) {
				log.info("|EVENT HANDLED| Event Type: {}", EventType.MESSAGE_WITH_ATTACHMENTS);
				return EventType.MESSAGE_WITH_ATTACHMENTS;
			}
			
			log.info("|EVENT HANDLED| Event Type: {}", EventType.MESSAGES);
			return EventType.MESSAGES;
		}
		
		if(event.getMessage() != null && !event.getRecipient().getId().equals(pageId)) {
			log.info("|EVENT HANDLED| Event Type: {}", EventType.REPLY);
			return EventType.REPLY;
		}
		
		if(event.getDelivery() != null) {
			log.info("|EVENT HANDLED| Event Type: {}", EventType.MESSAGE_DELIVERIES);
			return EventType.MESSAGE_DELIVERIES;
		}
		
		if(event.getRead()!= null) {
			log.info("|EVENT HANDLED| Event Type: {}", EventType.MESSAGE_READS);
			return EventType.MESSAGE_READS;
		}
		
		log.info("|EVENT NOT HANDLED| Uknown Event" );
		return null;
	}

}
