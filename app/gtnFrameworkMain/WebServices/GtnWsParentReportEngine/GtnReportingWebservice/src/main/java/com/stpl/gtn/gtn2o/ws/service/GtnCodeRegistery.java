package com.stpl.gtn.gtn2o.ws.service;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;

@Component
public class GtnCodeRegistery {

	private CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
			fromProviders(PojoCodecProvider.builder().automatic(true).build()));

	public CodecRegistry getPojoCodecRegistry() {
		return pojoCodecRegistry;
	}

	public void setPojoCodecRegistry(CodecRegistry pojoCodecRegistry) {
		this.pojoCodecRegistry = pojoCodecRegistry;
	}

}
