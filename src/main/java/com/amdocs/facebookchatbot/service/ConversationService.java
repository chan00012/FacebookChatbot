package com.amdocs.facebookchatbot.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amdocs.facebookchatbot.client.messenger.FacebookMessenger;
import com.amdocs.facebookchatbot.enums.EventType;
import com.amdocs.facebookchatbot.enums.QuickReplyType;
import com.amdocs.facebookchatbot.enums.SenderActionType;
import com.amdocs.facebookchatbot.model.Event;
import com.amdocs.facebookchatbot.model.events.objects.QuickReply;

import lombok.extern.log4j.Log4j2;
import util.Utils;

@Log4j2
@Service
public class ConversationService {
	
	private final static String GET_STARTED = "get started";
	private final static String QUICK_REPLY = "quick reply";
	private final static String LOCATION = "location";
	private final static String EMAIL = "email";
	private final static String PHONE_NUMBER = "phone number";
	
	@Autowired
	FacebookMessenger facebookMessenger;
		
	public void replyOnConversation(Event event) {
				
		if(event.getEventType() == EventType.MESSAGES) {
			
			try {
				facebookMessenger.sendSenderAction(event.getSender().getId(), SenderActionType.mark_seen);
				facebookMessenger.sendSenderAction(event.getSender().getId(), SenderActionType.typing_on);
				Thread.sleep(Utils.delayReply());
				facebookMessenger.sendSenderAction(event.getSender().getId(), SenderActionType.typing_off);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
				switch(event.getMessage().getText().toLowerCase())
				{
					case QUICK_REPLY:
						List<QuickReply> qrList = facebookMessenger.quickReplyBuilder(Arrays.asList("red","yellow","blue"));
						facebookMessenger.sendQuickReply(event.getSender().getId(), qrList,"Sample Quick Reply");
						break;
					
					case LOCATION:
						facebookMessenger.sendQuickReply(event.getSender().getId(),QuickReplyType.location,"Please share your location");
						break;
		
					case EMAIL:
						facebookMessenger.sendQuickReply(event.getSender().getId(),QuickReplyType.user_email,"Please share your email");
						break;
					
					case PHONE_NUMBER:
						facebookMessenger.sendQuickReply(event.getSender().getId(),QuickReplyType.user_phone_number,"Please share your phone number");
						break;
					
					default:
						facebookMessenger.sendMessage(event.getSender().getId(), event.getMessage().getText());
				}
		}
		
		if(event.getEventType() == EventType.MESSAGE_WITH_ATTACHMENTS) {
			event.getMessage().getAttachments().forEach(attachment -> {
				facebookMessenger.sendMessageWithAttachment(event.getSender().getId(), attachment);
				
			});
		}
		
	}
	
	

}
