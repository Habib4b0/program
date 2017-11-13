package com.stpl.gtn.gtn2o.ws.forecast.service.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnWsFileWriterRunnable implements Runnable {

	private final GtnWsFileBean gtnWsFileBean;

	private final Map<String, List<String>> inputParameters;

	private final String queryId;

	private final GtnFrameworkSqlQueryEngine sqlQueryEngine;

	private final GtnWsSqlService gtnWsSqlService;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsFileWriterRunnable.class);

	public GtnWsFileWriterRunnable(final GtnWsFileBean gtnWsFileBean, final GtnForecastBean gtnForecastBean,
			final String queryId, final GtnFrameworkSqlQueryEngine sqlQueryEngine,
			final GtnWsSqlService gtnWsSqlService) {

		this.gtnWsFileBean = gtnWsFileBean;
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

			String finalFileName = gtnWsFileBean.getFilePath() + gtnWsFileBean.getFileName()
					+ GtnFrameworkCommonStringConstants.JSON_EXTENSION;

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
			throw new GtnFrameworkGeneralException("Error createJSONFile: " + e);
		}

	}

	public void pivotDataBasedOnResultList(JsonGenerator jsonGenerator, List<Object[]> resultList)
			throws GtnFrameworkGeneralException {
		String changeValue = null;
		try {
			jsonGenerator.writeStartArray();

			boolean isFirstIteration = false;

			for (Object[] object : resultList) {
				if (object[gtnWsFileBean.getGroupingColumnIndex()].toString().equals(changeValue)) {
					writeArrayToJSON(object, jsonGenerator);
				} else {
					changeValue = String.valueOf(object[gtnWsFileBean.getGroupingColumnIndex()]);
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
			throw new GtnFrameworkGeneralException("Error pivotDataBasedOnResultList: " + e);
		}
	}

	private void writeArrayToJSON(Object[] row, JsonGenerator jsonGenerator) throws GtnFrameworkGeneralException {
		try {

			for (int i = gtnWsFileBean.getFileStartIndex(); i < row.length; i++) {
				jsonGenerator.writeObject(row[i]);
			}

		} catch (IOException e) {
			throw new GtnFrameworkGeneralException("Error writeArrayToJSON: " + e);
		}
	}

}
