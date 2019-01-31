package com.pheonix.digitalservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.pheonix.digitalservice.handler.AfterSaveListener;
import com.pheonix.digitalservice.model.DigitalService;
import com.pheonix.digitalservice.model.DigitalServiceUpdate;
import com.pheonix.digitalservice.utilities.SampleData;

@Service
public class DGS {

	/*private DigitalServiceRepository digitalServiceRepository;

	@Autowired
	public DGS(DigitalServiceRepository digitalServiceRepository) {

		this.digitalServiceRepository = digitalServiceRepository;
	}*/

	@Autowired
	MongoOperations mongoOperations;

	/*@Autowired
	MongoTemplate mongoTemplate;*/

	public DigitalService createDigitalServiceApplication(DigitalService dgs) {
		mongoOperations.save(dgs);


		//return digitalServiceRepository.save(dgs);
		return dgs;
	}

	public List<DigitalService> digitalServiceList() {
		//List<DigitalService> dgsList = digitalServiceRepository.findAll();

		List<DigitalService> dgsList = mongoOperations.findAll(DigitalService.class);
		return dgsList;
	}
	public void  deleteAll() {
		//digitalServiceRepository.deleteAll();

		mongoOperations.dropCollection(DigitalService.class);
	}

	public void  sampleDataCreation() {

		//digitalServiceRepository.saveAll(SampleData.createDigitalServiceApplication());
		mongoOperations.insertAll(SampleData.createDigitalServiceApplication());

	}

	public DigitalService  digitalSummaryForApplicationNumber(String app_no) {

		//return digitalServiceRepository.findById(appid);
		Query query = new Query();
		query.addCriteria(Criteria.where("app_no").is("app_no"));
		return mongoOperations.findOne(query, DigitalService.class);

	}



	@Autowired
	private AfterSaveListener afterSaveListener;




	public DigitalService dgsUpdateKeyfields(DigitalServiceUpdate dgsDetails) {



		Query query = new Query();
		query.addCriteria(Criteria.where("app_no").is(dgsDetails.getApp_no()));
		
		//DigitalService userTest1 = mongoOperations.findOne(query, DigitalService.class);

		//System.out.println("DigitalService find - " + userTest1);

		Update update = new Update();
		update.set("updatedAt",dgsDetails.getUpdatedAt());
		update.set("svcName", dgsDetails.getSvcName());

		DigitalService dgs =  mongoOperations.findAndModify(query, update, new FindAndModifyOptions().returnNew(true),DigitalService.class);
		//System.out.println("DigitalService find - " + dgs);
		if(dgs!=null) {
			afterSaveListener.updateEvent(dgs);
			//afterSaveListener.updateEventInDiffPar(dgs);
		}
		return dgs;
	}



}
