package com.stpl.gtn.gtn2o.gtnframeworkjsonbuilder;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkJSONWriter {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkJSONWriter.class);

	public void createJson(String path, String fileName, GtnUIFrameworkRootConfig rootConfig) {

		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string and save into a file directly
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		try {
			mapper.writeValue(new File(path + GtnFrameworkCommonStringConstants.STR_SLASH + fileName), rootConfig);
		} catch (JsonGenerationException e) {
			LOGGER.error("JsonGenerationException Exception" + e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error("JsonMappingException Exception" + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("IO Exception" + e.getMessage());
		}
	}

}
