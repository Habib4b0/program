/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author STPL
 */
public class GtnWsCustomFileWriterRunnable implements Runnable {

	private final String fileName;

	private final String filePath;

	private final Map<String, List<String>> inputParameters;

	private final String queryId;

	private final GtnFrameworkSqlQueryEngine sqlQueryEngine;

	private final GtnWsSqlService gtnWsSqlService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCustomFileWriterRunnable.class);

	GtnWsCustomFileWriterRunnable(final String fileName, final String filePath, final GtnForecastBean gtnForecastBean,
			final String queryId, final GtnFrameworkSqlQueryEngine sqlQueryEngine,
			final GtnWsSqlService gtnWsSqlService) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.inputParameters = gtnForecastBean.getQueryParameters();
		this.queryId = queryId;
		this.sqlQueryEngine = sqlQueryEngine;
		this.gtnWsSqlService = gtnWsSqlService;
	}

	@Override
	public void run() {
		List<Object[]> resultList;
		try {
			String queryString = gtnWsSqlService.getQueryWithReplacedValues(this.queryId,
					this.inputParameters.get(queryId));
			String finalFileName = filePath + fileName + GtnFrameworkCommonStringConstants.STPL_EXTENSION;
			resultList = sqlQueryEngine.executeSelectQuery(queryString, finalFileName);
			createJSONFile(resultList, finalFileName);
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Error in GtnWsCustomFileWriterRunnable run", e);
		}

	}

	public void createJSONFile(List<Object[]> resultList, String finalFileName) throws GtnFrameworkGeneralException {
		JsonFactory jsonFactory = new JsonFactory();
		try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(new FileOutputStream(finalFileName))) {
			jsonGenerator.writeStartArray();
			pivotDataBasedOnResultList(jsonGenerator, resultList);
			jsonGenerator.writeEndArray();
			jsonGenerator.flush();
		} catch (IOException e) {
			throw new GtnFrameworkGeneralException("Error in  createJSONFile: " + e);
		}

	}

	public void pivotDataBasedOnResultList(JsonGenerator jsonGenerator, List<Object[]> resultList)
			throws GtnFrameworkGeneralException {
		String changeValue = null;
		try {
			jsonGenerator.writeStartArray();

			boolean isFirstIteration = false;

			for (Object[] object : resultList) {
				if (object[0].toString().equals(changeValue)) {
					writeArrayToJSON(object, jsonGenerator);
				} else {
					changeValue = String.valueOf(object[0]);
					if (isFirstIteration) {
						jsonGenerator.writeEndArray();
						jsonGenerator.writeStartArray();
					}
					writeArrayToJSON(object, jsonGenerator);
					isFirstIteration = true;
				}
			}
			jsonGenerator.writeEndArray();
		} catch (IOException e) {
			throw new GtnFrameworkGeneralException("Error in  pivotDataBasedOnResultList: " + e);
		}
	}

	private void writeArrayToJSON(Object[] row, JsonGenerator jsonGenerator) throws GtnFrameworkGeneralException {
		int i = 1;
		if (fileName.contains("MASTER")) {
			i = 0;
		}
		for (; i < row.length; i++) {
			Object object = row[i];
			try {
				if (object instanceof Timestamp) {
					Timestamp timestamp = (Timestamp) object;

					jsonGenerator.writeObject(timestamp.getTime());

				} else {
					jsonGenerator.writeObject(object);
				}
			} catch (IOException e) {
				throw new GtnFrameworkGeneralException("Error in  writeArrayToJSON: " + e);
			}
		}
	}
}
