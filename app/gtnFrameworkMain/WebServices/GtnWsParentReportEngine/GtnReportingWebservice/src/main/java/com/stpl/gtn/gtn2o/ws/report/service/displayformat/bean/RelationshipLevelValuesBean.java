package com.stpl.gtn.gtn2o.ws.report.service.displayformat.bean;

public class RelationshipLevelValuesBean {
	private int noOfSelectFormed = 0;
	private String query;
	private String defaultGroupBy;

	public RelationshipLevelValuesBean() {
		super();
	}

	public int getNoOfSelectFormed() {
		return noOfSelectFormed;
	}

	public void setNoOfSelectFormed(int noOfSelectFormed) {
		this.noOfSelectFormed = noOfSelectFormed;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getDefaultGroupBy() {
		return defaultGroupBy;
	}

	public void setDefaultGroupBy(String defaultGroupBy) {
		this.defaultGroupBy += defaultGroupBy;
	}
}
