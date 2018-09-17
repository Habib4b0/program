package com.stpl.gtn.gtn2o.ws.search.service;

import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Service
@Scope(value = "singleton")
public class GtnForecastJsonService {

	private static final GtnWSLogger GTNLOGGER = GtnWSLogger.getGTNLogger(GtnForecastJsonService.class);

	public String convertObjectAsJsonString(Object value) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			GTNLOGGER.error(e.getMessage());
		}

		return "";
	}

	public void writeObjectAsJson(Object input, File file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		mapper.writeValue(file, input);
	}

	public Object convertJsonToObject(String filePath, Class<?> classObj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		return mapper.readValue(new File(filePath), classObj);
	}



}
