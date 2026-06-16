package com.larisa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
@Configuration
@Getter

public class TwilioConfig {
	 @Value("${twilio.accountSid}")
	    private String accountSid;

	    @Value("${twilio.authToken}")
	    private String authToken;

	    @Value("${twilio.phoneNumber}")
	    private String phoneNumber;

	    @PostConstruct
	    public void twilioInit() {
	    	System.out.println("Twilio Loaded "+accountSid+" "+authToken);
	        Twilio.init(accountSid, authToken);
	    }
}
