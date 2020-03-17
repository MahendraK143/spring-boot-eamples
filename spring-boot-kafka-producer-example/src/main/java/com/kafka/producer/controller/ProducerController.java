package com.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.model.User;

@RestController
@RequestMapping("kafka")
public class ProducerController {
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	private static final String TOPIC_NAME="post_topic";
	
	@GetMapping("/publish/{message}")
	public String publish(@PathVariable String message) {
		kafkaTemplate.send(TOPIC_NAME,new User(1,"Mahendra","Java developer"));
		return "Message published successfully...";
	}
}
