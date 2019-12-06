package com.hrt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hrt.controller","com.hrt.service"})
public class HeartRateTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartRateTrackerApplication.class, args);
	}

}
