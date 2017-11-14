package com.stpl.gtn.gtn2o.ws.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkFileReadWriteService {

	public GtnFrameworkFileReadWriteService() {
		super();
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCallEtlService.class);

	private List<GtnFrameworkHierarchyQueryBean> getConfigFromJSON(int hierarchyId) {

		String fileName = getFileNameByHierarchyId(hierarchyId);
		List<GtnFrameworkHierarchyQueryBean> rootConfig = null;
		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string and save into a file directly
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			rootConfig = (Arrays
					.asList(mapper.readValue(GtnFileNameUtils.getFile(fileName), GtnFrameworkHierarchyQueryBean[].class)));
		} catch (JsonGenerationException e) {
			LOGGER.error(GtnFrameworkCommonConstants.JSON_GENERATION_EXCEPTION_EXCEPTION + e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(GtnFrameworkCommonConstants.JSON_MAPPING_EXCEPTION_EXCEPTION + e.getMessage());
		} catch (IOException e) {
			LOGGER.error(GtnFrameworkCommonConstants.IO_EXCEPTION + e.getMessage());
		}
		return rootConfig;

	}

	public void createJson(Object obj, int hierarchyId) {

		String folderName = getFolderName();
		File folder = new File(folderName);
		folder.mkdirs();
		String fileName = getFileNameByHierarchyId(hierarchyId);
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
		return getFilePath() + "/HierarchyQueries/";
	}

	private File getProperFile(String fileName) {
		File file = new File(fileName);
		file.deleteOnExit();
		return file;
	}

	private String getFileNameByHierarchyId(int hierarchyId) {

		return getFolderName() + "HierachyBuilerQuery" + hierarchyId + ".json";

	}

	private String getFilePath() {

		return System.getProperty("com.stpl.gtnframework.base.path");

	}

	public GtnFrameworkHierarchyQueryBean getQueryFromFile(Integer hierarchyId, int hierarchyLevelDefnId)
			throws ClassNotFoundException, IOException {
		List<GtnFrameworkHierarchyQueryBean> queryBeanList = getConfigFromJSON(hierarchyId);
		for (GtnFrameworkHierarchyQueryBean gtnFrameworkQueryBean : queryBeanList) {
			if (gtnFrameworkQueryBean.getHierarchyLevelDefSid() == hierarchyLevelDefnId) {
				return gtnFrameworkQueryBean;
			}
		}
		return null;

	}

}
