package com.rgbrain.brianbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BrianbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrianbotApplication.class, args);
	}

}
