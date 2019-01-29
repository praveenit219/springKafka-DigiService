package com.pheonix.digitalservice.messageservice;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.pheonix.digitalservice.model.DigitalService;
import com.pheonix.digitalservice.model.DigitalServiceUpdate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver {
	
	private CountDownLatch latch = new CountDownLatch(5);

	private CountDownLatch partitionLatch = new CountDownLatch(5);
	
	public CountDownLatch getPartitionLatch() {

        return partitionLatch;
    }
	
    public CountDownLatch getLatch() {

        return latch;
    }

    
    @KafkaListener(topics = "${spring.kafka.topic.dgs-applicationRegistration}")
    public void receiveAppRegistration(DigitalService dgs) {

        log.info("receiveAppRegistration payload='{}'", dgs);
        latch.countDown();
       
    }
    
    
    @KafkaListener(topics = "${spring.kafka.topic.dgs-applicationRegistration}", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Messasge based on header: " + message + " from partition: -->" + partition);
        latch.countDown();
    }
    
    @KafkaListener(topicPartitions = @TopicPartition(topic = "${spring.kafka.topic.dgs-applicationRegistration}", partitions = { "1", "2" }))
    public void listenToParition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message based on partitions: " + message + " from partition: ==>" + partition);
        this.partitionLatch.countDown();
    }
    
    @KafkaListener(topics = "${spring.kafka.topic.dgs-applicationRegistrationUpate}")
    public void receiveAppUpdateInPartition(DigitalServiceUpdate dgs) {

        log.info("receiveAppUpdateInPartition payload='{}'", dgs);
        latch.countDown();
       
    }
    
    @KafkaListener(topics = "${spring.kafka.topic.dgs-applicationRegistrationUpate}")
    public void receiveAppUpdate(DigitalServiceUpdate dgs) {

        log.info("receiveAppUpdate payload='{}'", dgs);
        latch.countDown();
       
    }

}
