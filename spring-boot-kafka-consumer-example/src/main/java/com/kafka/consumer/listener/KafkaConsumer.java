package com.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.consumer.model.User;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics = "post_topic", groupId = "group_id")
	public void consumer(String message) {
		System.out.println("Consumer Message:"+message);
	}
	@KafkaListener(topics = "post_topic_json", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
	public void userConsumer(User user) {
		System.out.println("Consumer Json Message:"+user);
	}
}
