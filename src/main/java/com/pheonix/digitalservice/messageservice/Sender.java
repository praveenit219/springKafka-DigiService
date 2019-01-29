package com.pheonix.digitalservice.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sender {

	/*@Autowired
	private KafkaTemplate<String, ApplicationCreatedEvent> applicationCreatedkafkaTemplate;

	public void send(String topic, ApplicationCreatedEvent payload) {

		log.info("sending payload='{}' to topic='{}'", payload, topic);
		applicationCreatedkafkaTemplate.send(topic, payload);
	}

	@Autowired
	private KafkaTemplate<String, ApplicationUpdatedEvent> applicationUpdatedkafkaTemplate;

	public void send(String topic, ApplicationUpdatedEvent payload) {

		log.info("sending payload='{}' to topic='{}'", payload, topic);
		applicationUpdatedkafkaTemplate.send(topic, payload);
	}*/
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void send(String topic,int partition,String key, Object payload) {

		log.info("sending payload='{}' to topic='{}'", payload, topic);
		kafkaTemplate.send(topic,partition,key, payload);
	}

}
