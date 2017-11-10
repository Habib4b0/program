package com.stpl.gtn.gtn2o.ws.forecast.service.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsResourceService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Service
public class GtnWsFileService {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsFileService.class);

	@Autowired
	private GtnWsReturnsResourceService resourceService;

	/**
	 * Method returns the base file path.
	 * 
	 * @param testFilePath
	 * @param inputParam
	 * @return
	 * 
	 * 		File will be from
	 *         GTN_BASE_PATH/SERIALISED_FILE_PATH/${Module}/${Formatted_Date}/${
	 *         User_ID}/${Session_ID}/
	 */
	public String getFilePath(final String moduleName, final String userId, final String sessionId, String testFilePath)
			throws GtnFrameworkGeneralException {
		StringBuilder filePath = new StringBuilder(testFilePath);
		filePath.append(GtnFrameworkCommonStringConstants.SERIALISED_FILE_PATH);
		filePath.append(moduleName);
		filePath.append(CommonConstants.FORWARD_SLASH);
		filePath.append(getFormattedDate());
		filePath.append(CommonConstants.FORWARD_SLASH);
		filePath.append(userId);
		filePath.append(CommonConstants.FORWARD_SLASH);
		filePath.append(sessionId);
		filePath.append(CommonConstants.FORWARD_SLASH);
		Path path = Paths.get(filePath.toString());
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				LOGGER.error("Exception while Excuting getFilePath method", e);
				throw new GtnFrameworkGeneralException(e);

			}
		}
		return filePath.toString();
	}

	private String getFormattedDate() {
		String dateFormat = resourceService.resourceFileName("file-name", CommonConstants.RESOURCES_PATH,
				"date_format");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(new Date());
	}

	/**
	 *
	 * @param fileName
	 * @param object
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeDataIntoFile(String fileName, Object object) throws GtnFrameworkGeneralException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
		} catch (IOException e) {
			LOGGER.error("Exception while Excuting writeDataIntoFile method", e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	/**
	 *
	 * @param <T>
	 * @param fileName
	 * @param clazz
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public <T extends Object> T readDataFromFile(String fileName, Class<T> clazz) throws GtnFrameworkGeneralException {
		try (FileInputStream fileOutputStream = new FileInputStream(fileName);
				ObjectInputStream objectOutputStream = new ObjectInputStream(fileOutputStream);) {
			return clazz.cast(objectOutputStream.readObject());
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.error("Exception while Excuting readDataFromFile method", e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	/**
	 *
	 * @param <T>
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	public <T extends Object> T readJSONDataFromFile(String fileName, Class<T> clazz)
			throws GtnFrameworkGeneralException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(new FileInputStream(fileName), clazz);
		} catch (IOException e) {
			throw new GtnFrameworkGeneralException("Error in executing query : " + e);
		}

	}

	public void writeJsonDataToFile(final String path, final List<List<Object>> fileList)
			throws GtnFrameworkGeneralException {
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.info("written file"+path);
		try {
			objectMapper.writeValue(new FileWriter(path), fileList);
		} catch (IOException e) {
			LOGGER.error("Exception while Excuting writeJsonDataToFile method", e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

}
