package com.pheonix.digitalservice.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientURI;

@Configuration
@Component
public class MongoDBConfig {


	@Value("${spring.data.mongodb.uri}")
	private String bootstrapServers;
	
	public MongoClientURI mongoDetailsURI() {
		return new MongoClientURI(bootstrapServers);
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException{
		return new SimpleMongoDbFactory(mongoDetailsURI());
	}

	@Bean
	public MongoOperations mongoOperations() throws UnknownHostException{
		return new MongoTemplate(mongoDbFactory());
	}

}
