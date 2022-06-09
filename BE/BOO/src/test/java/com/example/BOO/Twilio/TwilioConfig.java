package com.example.BOO.Twilio;

import com.example.BOO.Config.TwilioConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TwilioConfig {

    @Autowired
    TwilioConfiguration twilioConfiguration ;

    @Test
    public void test1(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(formatter.format(new Date()));

    }
}
