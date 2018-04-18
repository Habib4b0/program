package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GtnWsJsonService {

	private static GtnWsJsonService JSON_SERVICE = null;

	private GtnWsJsonService() {
		super();
	}

	public static GtnWsJsonService getInstance() {
		if (JSON_SERVICE == null) {
			JSON_SERVICE = new GtnWsJsonService();	
		}
		return JSON_SERVICE;
	}

	public List<Object> convertJsonToList(String filePath, Class className) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			return (Arrays.asList(mapper.readValue(new File(filePath), className)));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public void writeToJsonFile(String filePath, Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			// Convert object to JSON string and save into a file directly
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			mapper.writeValue(new File(filePath), obj);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
