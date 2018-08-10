package com.stpl.dependency.queryengine.bean;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;

public class GtnFrameworkQueryExecutorBean {

	private String queryType;
	private String sqlQuery;
	private Object[] params;
	private GtnFrameworkDataType[] dataType;
	private String procedureName;

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public GtnFrameworkDataType[] getDataType() {
		return dataType;
	}

	public void setDataType(GtnFrameworkDataType[] dataType) {
		this.dataType = dataType;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

}
