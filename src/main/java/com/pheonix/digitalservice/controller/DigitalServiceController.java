package com.pheonix.digitalservice.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pheonix.digitalservice.model.DigitalService;
import com.pheonix.digitalservice.model.DigitalServiceUpdate;
import com.pheonix.digitalservice.service.DGS;

@RestController
@RequestMapping("/customers")
public class DigitalServiceController {


	private DGS digitalService;

	@Autowired
	public DigitalServiceController(DGS digitalService) {

		this.digitalService = digitalService;
	}


	@RequestMapping(path = "/dgs/application/sample", method = RequestMethod.POST)
	public ResponseEntity<String> sampleData() {

		digitalService.sampleDataCreation();
		return new ResponseEntity("SampleApplication created", HttpStatus.CREATED);
	}

	@RequestMapping(path = "/dgs/application", method = RequestMethod.POST)
	public ResponseEntity<String> createDGSApplication(@RequestBody DigitalService dgs) {

		digitalService.createDigitalServiceApplication( dgs);

		return new ResponseEntity("Requested Data created", HttpStatus.CREATED);
	}

	@RequestMapping(path = "/dgs/application", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, List<DigitalService>>> digitalServiceSummary() {

		List<DigitalService> dgsList = digitalService.digitalServiceList();
		return new ResponseEntity<>(Collections.singletonMap("dgsList", dgsList), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/dgs/application/{app_no}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DigitalService> digitalApplicationSummary(@PathVariable("app_no") String appid) {

		DigitalService dgs = digitalService.digitalSummaryForApplicationNumber(appid);
		return new ResponseEntity<>(dgs, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/dgs/application", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<DigitalService> digitalApplicationSummary(@RequestBody DigitalServiceUpdate dgsUpdate) {

		DigitalService dgs = digitalService.dgsUpdateKeyfields(dgsUpdate);
		return new ResponseEntity<>(dgs, HttpStatus.OK);
	}

}
