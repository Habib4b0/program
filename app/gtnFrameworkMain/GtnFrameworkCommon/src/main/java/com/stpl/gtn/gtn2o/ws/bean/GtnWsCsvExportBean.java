package com.stpl.gtn.gtn2o.ws.bean;

import java.util.ArrayList;
import java.util.List;

public class GtnWsCsvExportBean {

	public GtnWsCsvExportBean() {
		super();
	}

	private String countQuery;
	private String dataQuery;
	private String exportName;
	private List<String> headerList;

	public String getCountQuery() {
		return countQuery;
	}

	public void setCountQuery(String countQuery) {
		this.countQuery = countQuery;
	}

	public String getDataQuery() {
		return dataQuery;
	}

	public void setDataQuery(String dataQuery) {
		this.dataQuery = dataQuery;
	}

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public List<String> getHeaderList() {
		return headerList != null ? new ArrayList<>(headerList) : headerList;
	}

	public void setHeaderList(List<String> headerList) {
		this.headerList = headerList != null ? new ArrayList<>(headerList) : headerList;
	}

}
