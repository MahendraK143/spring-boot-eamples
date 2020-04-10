package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringConfigServerClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigServerClientApplication.class, args);
	}

}

@RefreshScope
@RestController
class MessageRestController {
 
    @Value("${msg}")
    private String msg;
 
    @RequestMapping("/msg")
    String getMsg() {
        return this.msg;
    }
}