package com.java.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CustomerRewardProgramApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CustomerRewardProgramApplication.class, args);
	}
	
    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }

}
