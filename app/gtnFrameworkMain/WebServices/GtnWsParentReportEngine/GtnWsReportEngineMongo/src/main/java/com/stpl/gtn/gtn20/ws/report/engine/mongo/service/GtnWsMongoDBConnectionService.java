package com.stpl.gtn.gtn20.ws.report.engine.mongo.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.constants.MongoConstants;

@Service
@Scope(value = "singleton")
public class GtnWsMongoDBConnectionService {

	@Autowired
	@Qualifier("mongoCLientFactory")
	private MongoClient client;

	@Autowired
	GtnCodeRegistery registery;

	private MongoDatabase mongoDBInstance = null;

	@PostConstruct
	public void init() {
		if (mongoDBInstance == null) {
			mongoDBInstance = client.getDatabase(MongoConstants.DATABSE_NAME);
			mongoDBInstance = client.getDatabase(MongoConstants.DATABSE_NAME)
					.withCodecRegistry(registery.getPojoCodecRegistry());
		}
	}

	public MongoDatabase getDBInstance() {
		return mongoDBInstance;
	}

}
