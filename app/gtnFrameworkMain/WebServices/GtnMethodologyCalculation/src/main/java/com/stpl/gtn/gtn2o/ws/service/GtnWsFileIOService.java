package com.stpl.gtn.gtn2o.ws.service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GtnWsFileIOService {

	public <T extends Object> T readJSONDataFromFile(String fileName, Class<T> clazz) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new FileInputStream(fileName), clazz);
		
	}

	public void writeJsonDataToFile(final String path, final List<List<Object>> fileList) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new FileWriter(path), fileList);
		
	}

}
