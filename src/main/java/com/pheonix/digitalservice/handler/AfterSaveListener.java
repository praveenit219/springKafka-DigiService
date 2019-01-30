package com.pheonix.digitalservice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Controller;

import com.pheonix.digitalservice.messageservice.Sender;
import com.pheonix.digitalservice.model.ApplicationCreatedEvent;
import com.pheonix.digitalservice.model.ApplicationUpdatedEvent;
import com.pheonix.digitalservice.model.DigitalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AfterSaveListener extends AbstractMongoEventListener<DigitalService> {

	@Value("${spring.kafka.topic.dgs-applicationRegistration}")
	private String applicationcreatedtopic;
	
	@Value("${spring.kafka.topic.dgs-applicationRegistrationUpate}")
	private String applicationupdatedtopic;

	private Sender sender;

	@Autowired
	public AfterSaveListener(Sender sender) {

		this.sender = sender;
	}
	
	@Override
    public void onAfterSave(AfterSaveEvent<DigitalService> event) {

        log.info("onAfterSave event='{}'", event);
        DigitalService dgs = event.getSource();

        ApplicationCreatedEvent applicationCreatedEvent = new ApplicationCreatedEvent();
        applicationCreatedEvent.setId(dgs.getId());
        applicationCreatedEvent.setAgencyId(dgs.getAgencyId());
        applicationCreatedEvent.setApp_no(dgs.getApp_no());
        applicationCreatedEvent.setCreatedAt(dgs.getCreatedAt());
        applicationCreatedEvent.setUpdatedAt(dgs.getUpdatedAt());
        applicationCreatedEvent.setSvcName(dgs.getSvcName());
        applicationCreatedEvent.setAppStatus(dgs.getAppStatus());
        applicationCreatedEvent.setCustomerDetails(dgs.getCustomerDetails());
       
        sender.sendApplicationCreatedTemplate(applicationcreatedtopic,0,dgs.getApp_no(),applicationCreatedEvent);
    }
	
	 public void updateEventInDiffPar(DigitalService dgs) {

	        log.info("update updateEventInDiffPar='{}'", dgs);
	       
	        ApplicationUpdatedEvent applicationUpdatedEvent = new ApplicationUpdatedEvent();
	        applicationUpdatedEvent.setId(dgs.getId());
	        
	        applicationUpdatedEvent.setApp_no(dgs.getApp_no());
	        
	        applicationUpdatedEvent.setUpdatedAt(dgs.getUpdatedAt());
	        applicationUpdatedEvent.setSvcName(dgs.getSvcName());
	       
	        sender.sendApplicationUdpatedTemplate(applicationcreatedtopic,1,dgs.getApp_no(), applicationUpdatedEvent);
	    }
	
	
    public void updateEvent(DigitalService dgs) {

        log.info("update updateEvent='{}'", dgs);
       
        ApplicationUpdatedEvent applicationUpdatedEvent = new ApplicationUpdatedEvent();
        applicationUpdatedEvent.setId(dgs.getId());
        
        applicationUpdatedEvent.setApp_no(dgs.getApp_no());
        
        applicationUpdatedEvent.setUpdatedAt(dgs.getUpdatedAt());
        applicationUpdatedEvent.setSvcName(dgs.getSvcName());
       
        sender.sendApplicationUdpatedTemplate(applicationupdatedtopic,0,dgs.getApp_no(), applicationUpdatedEvent);
    }


}
