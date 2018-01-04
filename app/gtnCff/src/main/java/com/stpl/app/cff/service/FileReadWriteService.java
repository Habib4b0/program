package com.stpl.app.cff.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.jboss.logging.Logger;

public class FileReadWriteService {

	public FileReadWriteService() {
		super();
	}

	private static final Logger LOGGER = Logger.getLogger(FileReadWriteService.class);

	private List<GtnFrameworkHierarchyQueryBean> getConfigFromJSON(int hierarchyId, int hierarchyVersionNo) {

		String fileName = getFileNameByHierarchyId(hierarchyId, hierarchyVersionNo);
		List<GtnFrameworkHierarchyQueryBean> rootConfig = null;
		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string and save into a file directly
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			rootConfig = (Arrays.asList(mapper.readValue(new File(fileName), GtnFrameworkHierarchyQueryBean[].class)));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return rootConfig;

	}

	public void createJson(Object obj, int hierarchyId, int hierarchyVersionNo) {

		String folderName = getFolderName();
		File folder = new File(folderName);
		folder.mkdirs();
		String fileName = getFileNameByHierarchyId(hierarchyId, hierarchyVersionNo);
		File file = getProperFile(fileName);
		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string and save into a file directly
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		try {
			mapper.writeValue(file, obj);
		} catch (JsonGenerationException e) {
			LOGGER.error("JsonGenerationException Exception" + e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error("JsonMappingException Exception" + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("IO Exception" + e.getMessage());
		}
	}

	private String getFolderName() {
		return getFilePath() + "HierarchyQueries/";
	}

	private File getProperFile(String fileName) {
		File file = new File(fileName);
		file.deleteOnExit();
		return file;
	}

	private String getFileNameByHierarchyId(int hierarchyId, int hierarchyVersionNo) {

		return getFolderName() + "HierachyBuilerQuery" + hierarchyId + "_"+hierarchyVersionNo+".json";

	}

	private String getFilePath() {

		return System.getProperty("com.stpl.gtnframework.base.path");

	}

	public GtnFrameworkHierarchyQueryBean getQueryFromFile(int hierarchyId, String hierarchyLevelDefnId, int hierarchyVersionNo) {
		List<GtnFrameworkHierarchyQueryBean> queryBeanList = getConfigFromJSON(hierarchyId, hierarchyVersionNo);
		for (GtnFrameworkHierarchyQueryBean gtnFrameworkQueryBean : queryBeanList) {
			if (gtnFrameworkQueryBean.getHierarchyLevelDefSid() == Integer.parseInt(hierarchyLevelDefnId)) {
				return gtnFrameworkQueryBean;
			}
		}
		return null;

	}

}
