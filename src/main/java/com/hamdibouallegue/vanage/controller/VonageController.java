package com.hamdibouallegue.vanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

@RestController
@RequestMapping(value = "/api")
public class VonageController {
	private static final Logger logger = LoggerFactory.getLogger(VonageController.class);

	@PostMapping("/send")
	public void send() {
		// Initilise Vonage client
		VonageClient client = VonageClient.builder().apiKey("API_KEY").apiSecret("API_SECRET").build();
		
		TextMessage message = new TextMessage("The SMS Title", "YOUR_PHONE_NUMBER",
				"The SMS content");

		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

		if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
			logger.info("Message sent successfully.");
		} else {
			logger.info("Message failed with error: " + response.getMessages().get(0).getErrorText());
		}
	}
}
