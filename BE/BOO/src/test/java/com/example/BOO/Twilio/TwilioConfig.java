package com.example.BOO.Twilio;

import com.example.BOO.Config.TwilioConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TwilioConfig {

    @Autowired
    TwilioConfiguration twilioConfiguration ;

    @Test
    public void test1(){

        System.out.println(twilioConfiguration.getAccountSid());

    }
}
