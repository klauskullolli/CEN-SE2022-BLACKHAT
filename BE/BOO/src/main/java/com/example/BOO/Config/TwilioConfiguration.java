package com.example.BOO.Config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("twilio")
@EnableAutoConfiguration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "twilio.yaml")

public class TwilioConfiguration {

    String accountSid ;
    String authToken ;
    String number ;

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "TwilioConfiguration{" +
                "accountSid='" + accountSid + '\'' +
                ", authToken='" + authToken + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
