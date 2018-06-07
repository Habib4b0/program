package com.stpl.gtn.gtn2o.ws.report.service.transform;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

public class GtnWsReportRightTableData {
	private String hierarchyNo;
	private int year;
	private int period;
	private String projectionName;
	private Pair<List<String>, List<Double>> dataAliasPair;
	private String variableName;

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

	public Pair<List<String>, List<Double>> getDataAliasPair() {
		return dataAliasPair;
	}

	public void setDataAliasPair(Pair<List<String>, List<Double>> dataAliasPair) {
		this.dataAliasPair = dataAliasPair;
	}

	@Override
	public String toString() {
		return hierarchyNo + "-" + projectionName + dataAliasPair;
	}

}
