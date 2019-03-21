package com.amdocs.facebookchatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FacebookChatbot {

	public static void main(String[] args) {
		SpringApplication.run(FacebookChatbot.class, args);
	}

}
