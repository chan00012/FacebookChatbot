package com.amdocs.facebookchatbot.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amdocs.facebookchatbot.dto.ClientRequest;
import com.amdocs.facebookchatbot.enums.EventType;
import com.amdocs.facebookchatbot.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EventHandlerService {
	
	@Value("${messenger.pageId}")
	private Long pageId;
	
	@Autowired
	ConversationService conversationService;
	
	//@Async
	public void handleRequest(String payload) {
		
		ObjectMapper mapper = new ObjectMapper();
		log.info("|THREAD| : {}", Thread.currentThread().getName());
		
		ClientRequest cr;
		try {
			cr = mapper.readValue(payload, ClientRequest.class);
			
			cr.getEntry().forEach(entry ->{
				Event event = entry.getEvents().get(0);
				event = classifyEvent(event);
				
				conversationService.replyOnConversation(event);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Event classifyEvent(Event event) {
		
		if(event.getMessage() != null && event.getRecipient().getId().equals(pageId)) {
			
			if(event.getMessage().getAttachments() != null && 
			   event.getMessage().getStickerId() == null ) {
				
				event.setEventType(EventType.MESSAGE_WITH_ATTACHMENTS);
				log.info("|EVENT HANDLED| Event Type: {}", event.getEventType());
				return event;
			}
			
			event.setEventType(EventType.MESSAGES);
			log.info("|EVENT HANDLED| Event Type: {}", event.getEventType());
			return event;
		}
		
		if(event.getMessage() != null && !event.getRecipient().getId().equals(pageId)) {
			log.info("|EVENT HANDLED| Event Type: {}", EventType.REPLY);
			event.setEventType(EventType.REPLY);
			return event;
		}
		
		if(event.getDelivery() != null) {
			event.setEventType(EventType.MESSAGE_DELIVERIES);
			log.info("|EVENT HANDLED| Event Type: {}", event.getEventType());
			return event;
		}
		
		if(event.getRead()!= null) {
			event.setEventType(EventType.MESSAGE_READS);
			log.info("|EVENT HANDLED| Event Type: {}", event.getEventType());
			return event;
		}
		
		log.info("|EVENT NOT HANDLED| Uknown Event" );
		return event;
	}

}
