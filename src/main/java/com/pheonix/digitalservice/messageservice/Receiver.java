package com.pheonix.digitalservice.messageservice;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pheonix.digitalservice.model.ApplicationCreatedEvent;
import com.pheonix.digitalservice.model.ApplicationUpdatedEvent;

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

    
    @KafkaListener(topics = "${spring.kafka.topic.dgs-applicationRegistration}", containerFactory = "digitalAppCreatedKafkaListenerContainerFactory")
    public void receiveAppRegistration(ApplicationCreatedEvent dgs) {

        log.info("receiveAppRegistration payload='{}'", dgs);
        latch.countDown();
       
    } 
    
    
    /*@KafkaListener(topics = "${spring.kafka.topic.dgs-applicationRegistration}", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Messasge based on header: " + message + " from partition: -->" + partition);
        latch.countDown();
    }*/
    
    
    
   // @KafkaListener(topicPartitions = @TopicPartition(topic = "${spring.kafka.topic.dgs-applicationRegistration}", partitions = { "0", "1" }))
   /* @KafkaListener(topicPartitions = { @TopicPartition(topic = "${spring.kafka.topic.dgs-applicationRegistration}", partitions = { "0", "1" }) }, containerFactory = "digitalAppCreatedKafkaListenerContainerFactory")
    public void listenToParition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message based on partitions for topic create: " + message + " from partition: ==>" + partition);
        this.partitionLatch.countDown();
    } */
    
  /*  @KafkaListener(topicPartitions = @TopicPartition(topic = "${spring.kafka.topic.dgs-applicationRegistrationUpate}", partitions = { "0" }))
    public void listenToParitionForAnother(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message based on partitions for topic updated : " + message + " from partition: ==>" + partition);
        this.partitionLatch.countDown();
    } */
    
   /* @KafkaListener(topics = "${spring.kafka.topic.dgs-applicationRegistrationUpate}")
    public void receiveAppUpdateInPartition(ApplicationUpdatedEvent dgs) {

        log.info("receiveAppUpdateInPartition payload='{}'", dgs);
        latch.countDown();
       
    } */
    
   @KafkaListener(topics = "${spring.kafka.topic.dgs-applicationRegistrationUpate}", containerFactory = "digitalAppUpdatedKafkaListenerContainerFactory")
    public void receiveAppUpdate(ApplicationUpdatedEvent dgs) {

        log.info("receiveAppUpdate payload='{}'", dgs);
        latch.countDown();
       
    } 

}
