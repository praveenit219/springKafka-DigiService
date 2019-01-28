package com.pheonix.digitalservice.repository;

import com.pheonix.digitalservice.model.DigitalService;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DigitalServiceRepository extends MongoRepository<DigitalService, String> {

}
