package com.emsr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.emsr.repository"})
//@ComponentScan(basePackages = {"com.emsr.controller","com.emsr.service","com.emsr.repository"})
public class EmployeeManagementServiceReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementServiceReactApplication.class, args);
	}

}
