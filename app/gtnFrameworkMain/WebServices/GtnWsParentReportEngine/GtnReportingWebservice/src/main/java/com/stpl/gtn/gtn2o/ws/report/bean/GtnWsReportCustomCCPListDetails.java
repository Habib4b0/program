package com.stpl.gtn.gtn2o.ws.report.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GtnWsReportCustomCCPListDetails {

	public GtnWsReportCustomCCPListDetails() {
		super();
	}

	private int levelNo;
	private String hierarchyNo;
	private int childCount;
	private Object[] data;
	private int rowIndex;
	private int variableCount;

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public Object[] getData() {
		return data != null ? data.clone() : null;
	}

	public void setData(Object[] data) {
		this.data = data != null ? data.clone() : null;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getVariableCount() {
		return variableCount;
	}

	public void setVariableCount(int variableCount) {
		this.variableCount = variableCount;
	}

}
