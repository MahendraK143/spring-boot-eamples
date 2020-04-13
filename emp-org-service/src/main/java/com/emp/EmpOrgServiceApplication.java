package com.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.emp.exception.FeignErrorDecoder;

import brave.sampler.Sampler;
import feign.Logger;

@SpringBootApplication
@EnableFeignClients("com.emp.service")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
public class EmpOrgServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpOrgServiceApplication.class, args);
	}

	@Bean
	Logger.Level feighLoggerLover() {
		return Logger.Level.FULL;
	}

	/*
	 * @Bean public FeignErrorDecoder getFeignErrorDecoder() { return new
	 * FeignErrorDecoder(); }
	 */
	// creating a bean
	@Bean
	// creating a sampler called
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
