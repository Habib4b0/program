package com.stpl.gtn.gtn2o.ws.report.service.transform;

import java.util.Map;

public class GtnWsReportRightTableData {
	private String hierarchyNo;
	private int year;
	private int period;
	private String projectionName;
	private Map<String, Double> dataMap;
	private String variableName;

	public GtnWsReportRightTableData() {
		super();
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public Map<String, Double> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Double> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
		return hierarchyNo + "-" + projectionName + dataMap;
	}

}
