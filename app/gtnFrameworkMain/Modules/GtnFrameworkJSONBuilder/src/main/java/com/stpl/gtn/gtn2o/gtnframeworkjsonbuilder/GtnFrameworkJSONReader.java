
package com.stpl.gtn.gtn2o.gtnframeworkjsonbuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;

public class GtnFrameworkJSONReader {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkJSONReader.class);

	@SuppressWarnings("deprecation")
	public GtnUIFrameworkRootConfig getConfigFromJSON(String path, String fileName) {
		GtnUIFrameworkRootConfig rootConfig = null;
		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string and save into a file directly
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		try {
			rootConfig = mapper.reader(GtnUIFrameworkRootConfig.class)
					.readValue(GtnFileNameUtils.getFile(path + GtnFrameworkCommonStringConstants.STR_SLASH + fileName));
		} catch (JsonGenerationException e) {
			LOGGER.error(GtnFrameworkCommonConstants.JSON_GENERATION_EXCEPTION_EXCEPTION + e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(GtnFrameworkCommonConstants.JSON_MAPPING_EXCEPTION_EXCEPTION + e.getMessage());
		} catch (IOException e) {
			LOGGER.error(GtnFrameworkCommonConstants.IO_EXCEPTION + e.getMessage());
		}
		return rootConfig;

	}

	public <A> A getTypeConfigFromJson(String path, String fileName, Class<A> classType) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		try {
			return mapper.readValue(GtnFileNameUtils.getFile(
					System.getProperty(GtnFrameworkCommonStringConstants.GTNFRAMEWORK_BASE_PATH) + path + fileName),
					classType);
		} catch (JsonGenerationException e) {
			LOGGER.error(GtnFrameworkCommonConstants.JSON_GENERATION_EXCEPTION_EXCEPTION + e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(GtnFrameworkCommonConstants.JSON_MAPPING_EXCEPTION_EXCEPTION + e.getMessage());
		} catch (IOException e) {
			LOGGER.error(GtnFrameworkCommonConstants.IO_EXCEPTION + e.getMessage());
		}
		return null;
	}

	public List<GtnWSTransactionColumnBean> getCustomConfigFromJSON(String path, String fileName) {
		List<GtnWSTransactionColumnBean> resultList = null;
		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string and save into a file directly
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		try {
			resultList = (Arrays.asList(mapper.readValue(GtnFileNameUtils.getFile(
					System.getProperty(GtnFrameworkCommonStringConstants.GTNFRAMEWORK_BASE_PATH) + path + fileName),
					GtnWSTransactionColumnBean[].class)));
		} catch (JsonGenerationException e) {
			LOGGER.error(GtnFrameworkCommonConstants.JSON_GENERATION_EXCEPTION_EXCEPTION + e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(GtnFrameworkCommonConstants.JSON_MAPPING_EXCEPTION_EXCEPTION + e.getMessage());
		} catch (IOException e) {
			LOGGER.error(GtnFrameworkCommonConstants.IO_EXCEPTION + e.getMessage());
		}
		return resultList;

	}

	public static void main(String[] arg) {
		try {
			GtnUIFrameworkRootConfig rootConfig = new GtnFrameworkJSONReader().getConfigFromJSON("D:/Harlin/Gtn2o",
					"CompanyMaster.json");
			new GtnFrameworkJSONWriter().createJson("D:/Harlin/Gtn2o", "CompanyMaster.json", rootConfig);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

}
