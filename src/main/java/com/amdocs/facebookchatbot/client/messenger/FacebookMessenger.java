package com.amdocs.facebookchatbot.client.messenger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amdocs.facebookchatbot.dto.MessengerRequest;
import com.amdocs.facebookchatbot.enums.MessageType;
import com.amdocs.facebookchatbot.enums.QuickReplyType;
import com.amdocs.facebookchatbot.enums.SenderActionType;
import com.amdocs.facebookchatbot.model.UserEntity;
import com.amdocs.facebookchatbot.model.events.Messages;
import com.amdocs.facebookchatbot.model.events.objects.Attachment;
import com.amdocs.facebookchatbot.model.events.objects.QuickReply;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FacebookMessenger {
	
	private static final String URL_PATH = "https://graph.facebook.com/v2.6/me/messages?access_token=";
	
	@Value("${messenger.page-access-token}")
	private String accessToken;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(3000);
		return new RestTemplate(requestFactory);
	}
	
	
	
	public Long sendMessage(MessengerRequest ms) {
		
		String request_url = URL_PATH + accessToken;
		log.info("|POST REQUEST|- payload: {} url:{}", ms,request_url);
		JsonNode response = restTemplate.postForObject(request_url,ms,JsonNode.class);
		
		return response.get("message_id").asLong();
	}
	
	public Long sendMessage(Long recipient, String message) {
		
		Messages m = new Messages();
		m.setText(message);
		
		UserEntity ue = new UserEntity();
		ue.setId(recipient);
		
		MessengerRequest ms = MessengerRequest.builder()
														.messagingType(MessageType.RESPONSE)
														.recipient(ue)
														.message(m)
														.build();
		
		String request_url = URL_PATH + accessToken;
		log.info("|POST REQUEST|- payload: {} url:{}", ms,request_url);
		JsonNode response = restTemplate.postForObject(request_url,ms,JsonNode.class);
		
		return response.get("message_id").asLong();
	}
	
	public Long sendMessageWithAttachment(Long recipient, Attachment attachment) {
		
		Messages m = new Messages();
		m.setAttachment(attachment);
		UserEntity ue = new UserEntity();
		ue.setId(recipient);
		
		MessengerRequest ms = MessengerRequest.builder()
														.messagingType(MessageType.RESPONSE)
														.recipient(ue)
														.message(m)
														.build();
		
		String request_url = URL_PATH + accessToken;
		log.info("|POST REQUEST|- payload: {} url:{}", ms,request_url);
		JsonNode response = restTemplate.postForObject(request_url,ms,JsonNode.class);
		
		return response.get("message_id").asLong();
	}
	
	public Long sendQuickReply(Long recipient, List<QuickReply> qrList, String title) {
		
		Messages m = new Messages();
		m.setText(title);
		m.setQuickReplies(qrList);
		UserEntity ue = new UserEntity();
		ue.setId(recipient);
		
		MessengerRequest ms = MessengerRequest.builder()
														.messagingType(MessageType.RESPONSE)
														.recipient(ue)
														.message(m)
														.build();
		
		String request_url = URL_PATH + accessToken;
		log.info("|POST REQUEST|- payload: {} url:{}", ms,request_url);
		JsonNode response = restTemplate.postForObject(request_url,ms,JsonNode.class);
		
		return response.get("message_id").asLong();
	}

	public Long sendQuickReply(Long recipient, QuickReplyType quickReplyType, String title) {
		
		QuickReply qr = new QuickReply();
		qr.setContentType(quickReplyType);
		
		List<QuickReply> qrList = new ArrayList<>();
		qrList.add(qr);
		
		Messages m = new Messages();
		m.setText(title);
		m.setQuickReplies(qrList);
		
		UserEntity ue = new UserEntity();
		ue.setId(recipient);
		
		MessengerRequest ms = MessengerRequest.builder()
														.messagingType(MessageType.RESPONSE)
														.recipient(ue)
														.message(m)
														.build();
		
		String request_url = URL_PATH + accessToken;
		log.info("|POST REQUEST|- payload: {} url:{}", ms,request_url);
		JsonNode response = restTemplate.postForObject(request_url,ms,JsonNode.class);
		
		return response.get("message_id").asLong();
		
	}
	
	public Long sendSenderAction(Long recipient, SenderActionType senderActionType) {
		
		UserEntity ue = new UserEntity();
		ue.setId(recipient);
		
		MessengerRequest ms = MessengerRequest.builder()
														.messagingType(MessageType.RESPONSE)
														.recipient(ue)
														.senderAction(senderActionType)
														.build();
		
		String request_url = URL_PATH + accessToken;
		log.info("|POST REQUEST|- payload: {} url:{}", ms,request_url);
		JsonNode response = restTemplate.postForObject(request_url,ms,JsonNode.class);
		
		return response.get("recipient_id").asLong();
	}
	
	public List<QuickReply> quickReplyBuilder(List<String> quickReplies) {
		
		if(quickReplies.size() <= 11) {
			List<QuickReply> qrList = new ArrayList<>();
		
			quickReplies.forEach(quickReply -> {
				QuickReply qr = new QuickReply();
			
				qr.setContentType(QuickReplyType.text);
				qr.setTitle(quickReply);
				qr.setPayload(quickReply);
			
				qrList.add(qr);
			});
		
		return qrList;	
		}
		
		return null;
	}
	
}
