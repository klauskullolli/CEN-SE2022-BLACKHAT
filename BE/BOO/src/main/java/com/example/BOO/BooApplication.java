package com.example.BOO;

import com.example.BOO.Config.TwilioConfiguration;
import com.example.BOO.Model.Admin;
import com.example.BOO.Repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List ;

@SpringBootApplication
public class BooApplication {

	@Autowired
	AdminRepository adminRepository ;

	@Autowired
	TwilioConfiguration twilioConfiguration ;

	private static final Logger logger = LoggerFactory.getLogger(BooApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BooApplication.class, args);
	}

	@Bean
	public void defaultAdmin(){
		List  <Admin> admins = adminRepository.findAll() ;
		if (admins.size()==0){
			Admin admin = new Admin() ;
			admin.setUsername("admin");
			admin.setPassword("admin123");
			admin = adminRepository.save(admin) ;
			logger.info(String.format("This is the default admin : " + admin));
			}
	}

	@Bean
	public void showTwilioConfiguration(){
		logger.info("Twilio configuration is: "+ twilioConfiguration.toString());
	}

	@Bean
	public WebMvcConfigurer customConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
				configurer.defaultContentType(MediaType.APPLICATION_JSON);
			}
		};
	}
}
