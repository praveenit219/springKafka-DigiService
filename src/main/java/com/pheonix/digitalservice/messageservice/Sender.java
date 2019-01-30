package com.pheonix.digitalservice.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.pheonix.digitalservice.model.ApplicationCreatedEvent;
import com.pheonix.digitalservice.model.ApplicationUpdatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Sender {

	@Autowired
	private KafkaTemplate<String, ApplicationCreatedEvent> applicationCreatedkafkaTemplate;

	public void sendApplicationCreatedTemplate(String topic,int partition,String key, ApplicationCreatedEvent payload) {

		log.info("sending payload='{}' to topic='{}'", payload, topic);
		applicationCreatedkafkaTemplate.send(topic,partition,key, payload);
	}

	@Autowired
	private KafkaTemplate<String, ApplicationUpdatedEvent> applicationUpdatedkafkaTemplate;

	public void sendApplicationUdpatedTemplate(String topic,int partition,String key, ApplicationUpdatedEvent payload) {

		log.info("sending payload='{}' to topic='{}'", payload, topic);
		applicationUpdatedkafkaTemplate.send(topic,partition,key, payload);
	}
	
	/*@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void send(String topic,int partition,String key, Object payload) {

		log.info("sending payload='{}' to topic='{}'", payload, topic);
		kafkaTemplate.send(topic,partition,key, payload);
	}*/

}
