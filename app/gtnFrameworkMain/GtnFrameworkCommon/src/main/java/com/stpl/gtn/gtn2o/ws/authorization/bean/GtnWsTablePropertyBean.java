package com.stpl.gtn.gtn2o.ws.authorization.bean;

public class GtnWsTablePropertyBean {
	private String singleHeaderVisibleColumns;
	private String doubleHeaderVisibleColumns;
	private String singleHeaderVisibleHeaders;
	private String doubleHeaderVisibleHeaders;
	private String moduleName;

	public String getSingleHeaderVisibleColumns() {
		return singleHeaderVisibleColumns;
	}

	public void setSingleHeaderVisibleColumns(String singleHeaderVisibleColumns) {
		this.singleHeaderVisibleColumns = singleHeaderVisibleColumns;
	}

	public String getDoubleHeaderVisibleColumns() {
		return doubleHeaderVisibleColumns;
	}

	public void setDoubleHeaderVisibleColumns(String doubleHeaderVisibleColumns) {
		this.doubleHeaderVisibleColumns = doubleHeaderVisibleColumns;
	}

	public String getSingleHeaderVisibleHeaders() {
		return singleHeaderVisibleHeaders;
	}

	public void setSingleHeaderVisibleHeaders(String singleHeaderVisibleHeaders) {
		this.singleHeaderVisibleHeaders = singleHeaderVisibleHeaders;
	}

	public String getDoubleHeaderVisibleHeaders() {
		return doubleHeaderVisibleHeaders;
	}

	public void setDoubleHeaderVisibleHeaders(String doubleHeaderVisibleHeaders) {
		this.doubleHeaderVisibleHeaders = doubleHeaderVisibleHeaders;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public GtnWsTablePropertyBean() {
		super();
	}

}
