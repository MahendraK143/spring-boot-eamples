package com.apigatway.empnetflixzuulapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@EnableZuulProxy  
@EnableDiscoveryClient
@SpringBootApplication
public class EmpNetflixZuulApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpNetflixZuulApiGatewayServerApplication.class, args);
	}
	//creating a bean  
	@Bean
	//creating a sampler called   
	public Sampler defaultSampler()  
	{  
	return Sampler.ALWAYS_SAMPLE;  
	}  
}
