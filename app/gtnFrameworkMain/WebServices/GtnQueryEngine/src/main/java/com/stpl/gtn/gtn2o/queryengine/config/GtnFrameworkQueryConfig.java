package com.stpl.gtn.gtn2o.queryengine.config;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkQueryConfig {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkQueryConfig.class);

	private String query;

	private int[] paramPositionArray;

	private String[] dataTypeArray;

	private boolean insertOrSelectQuery;

	private int[] resultStoragePositionArray;

	private boolean updateOrDeleteQuery;

	public GtnFrameworkQueryConfig() {
		super();
	}

	public GtnFrameworkQueryConfig(String query, boolean insertOrSelectQuery, boolean updateOrDeleteQuery) {
		super();
		this.query = query;
		this.insertOrSelectQuery = insertOrSelectQuery;
		this.updateOrDeleteQuery = updateOrDeleteQuery;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String[] getDataTypeArray() {
		return dataTypeArray;
	}

	public void setDataTypeArray(String[] dataTypeArray) {
		this.dataTypeArray = dataTypeArray;
	}

	public boolean isInsertOrSelectQuery() {
		return insertOrSelectQuery;
	}

	public void setInsertOrSelectQuery(boolean insertOrSelectQuery) {
		this.insertOrSelectQuery = insertOrSelectQuery;
	}

	public int[] getResultStoragePositionArray() {
		return resultStoragePositionArray;
	}

	public void setResultStoragePositionArray(int[] resultStoragePositionArray) {
		this.resultStoragePositionArray = resultStoragePositionArray;
	}

	public int[] getParamPositionArray() {
		return paramPositionArray;
	}

	public void setParamPositionArray(int[] paramPositionArray) {
		this.paramPositionArray = paramPositionArray;
	}

	public int setParamPositionArray(int startPos, int endPos) {
		gtnLogger.debug(GtnFrameworkWebserviceConstant.START_POSITION + startPos
				+ GtnFrameworkWebserviceConstant.END_POSITION + endPos);

		this.paramPositionArray = new int[endPos - startPos];
		for (int i = 0; i < this.paramPositionArray.length; i++) {
			this.paramPositionArray[i] = startPos + i;
		}
		return endPos;
	}

	public int setParamPositionArray(int[] initialArray, int startPos, int endPos) {
		gtnLogger.debug(GtnFrameworkWebserviceConstant.START_POSITION + startPos
				+ GtnFrameworkWebserviceConstant.END_POSITION + endPos);

		this.paramPositionArray = new int[initialArray.length + (endPos - startPos)];
		for (int i = 0; i < initialArray.length; i++) {
			this.paramPositionArray[i] = initialArray[i];
		}
		for (int i = initialArray.length; i < this.paramPositionArray.length; i++) {
			this.paramPositionArray[i] = startPos++;
		}
		return endPos;
	}

	public void setParamPositionArray(int initialParamPos, int startPos, int endPos) {

		this.paramPositionArray = new int[1 + (endPos - startPos)];
		this.paramPositionArray[0] = initialParamPos;
		for (int i = 1; i < this.paramPositionArray.length; i++) {
			this.paramPositionArray[i] = 1 + startPos + i;
		}
	}

	public boolean isUpdateOrDeleteQuery() {
		return updateOrDeleteQuery;
	}

	public void setUpdateOrDeleteQuery(boolean updateOrDeleteQuery) {
		this.updateOrDeleteQuery = updateOrDeleteQuery;
	}

	public int setParamPositionArrayForChild(int[] initialArray, int startPos, int endPos) {
		this.paramPositionArray = new int[initialArray.length + (endPos - startPos)];
		for (int i = 0; i < initialArray.length; i++) {
			this.paramPositionArray[i] = initialArray[i];
		}
		for (int i = initialArray.length; i < this.paramPositionArray.length; i++) {
			this.paramPositionArray[i] = ++startPos;
		}
		return endPos;
	}

}
