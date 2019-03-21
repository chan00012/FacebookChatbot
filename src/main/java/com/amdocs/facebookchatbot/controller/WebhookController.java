package com.amdocs.facebookchatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.facebookchatbot.client.messenger.FacebookMessenger;
import com.amdocs.facebookchatbot.dto.ClientRequest;
import com.amdocs.facebookchatbot.service.EventHandlerService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/callback")
public class WebhookController {
	
	@Autowired
	FacebookMessenger facebookMessenger;
	
	@Autowired
	EventHandlerService eventHandlerService;
	
	@GetMapping
	public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode,
												@RequestParam("hub.verify_token") String verifyToken,
												@RequestParam("hub.challenge") String challenge){
		
		log.info("Received Webhook verification request - mode: {} | verifyToken: {} | challenge: {}", mode,
                verifyToken, challenge);
		
		return ResponseEntity.ok(challenge);
	}
	
	@PostMapping
	public ResponseEntity<Void> handleCallback(@RequestBody String payload,
											   @RequestHeader("X-Hub-Signature") String signature){
		
		log.info("Received Messenger Platform callback - payload: {} | signature: {}", payload, signature);
		
		try {
		eventHandlerService.handleRequest(payload);
		return ResponseEntity.status(HttpStatus.OK).build();
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
}
