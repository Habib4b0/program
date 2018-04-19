package com.stpl.gtn.gtn2o.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class GtnEsReportCustomViewController {
	@Autowired
	@Qualifier("mongoCLientFactory")
	private org.springframework.jndi.JndiObjectFactoryBean mongoCLientFactory;

	private MongoClient client = getMongoClient();

	@RequestMapping(value = "/loadHierarchy", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse display(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		MongoDatabase dataBase = client.getDatabase("GtnReport");
		System.out.println(dataBase.getCollection("GtnReport").count()); 
		return null;
	}

	private MongoClient getMongoClient() {
		return (MongoClient) mongoCLientFactory.getObject();

	}
}
