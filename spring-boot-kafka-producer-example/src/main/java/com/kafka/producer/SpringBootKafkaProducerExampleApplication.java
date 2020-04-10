package com.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootKafkaProducerExampleApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		for(String arg:args) {
            System.out.println(arg);
        }
        SpringApplication.run(SpringBootKafkaProducerExampleApplication.class, args);
	}
	

}
