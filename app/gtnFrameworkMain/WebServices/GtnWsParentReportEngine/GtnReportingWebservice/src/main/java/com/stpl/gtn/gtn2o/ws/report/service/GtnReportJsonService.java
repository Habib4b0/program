package com.stpl.gtn.gtn2o.ws.report.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;

@Service
@Scope(value = "singleton")
public class GtnReportJsonService {

	private static final GtnWSLogger GTNLOGGER = GtnWSLogger.getGTNLogger(GtnReportJsonService.class);

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
		return mapper.readValue(GtnFileNameUtils.getFile(filePath), classObj);
	}

	public File createJsonFilePath(String fileName, String sessionId) {
		String folderName = getFolderName();
		File folder = new File(folderName);
		folder.mkdir();
		String newFileName = getFileName(fileName, sessionId);
		return getCreateFileWithSessionId(newFileName);
	}

	private String getFolderName() {
		return getFilePath() + GtnWsQueryConstants.REPORTING;
	}

	public String getFileName(String filename, String sessionId) {
		return getFolderName() + filename + GtnWsQueryConstants.UNDERSCORE + sessionId;
	}

	private File getCreateFileWithSessionId(String fileName) {
		File file = GtnFileNameUtils.getFile(fileName);
		file.deleteOnExit();
		return file;
	}

	private String getFilePath() {
		return System.getProperty("gtn.app.data.path");
	}

	public boolean deleteFile(String fileName, String sessionId) throws IOException {
		return Files.deleteIfExists(Paths.get(getFileName(fileName, sessionId)));
	}

}
