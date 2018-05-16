package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.util.List;

public class GtnWsReportComputedResultsBean {

	public GtnWsReportComputedResultsBean() {
		super();
	}

	private String hierarchyNo;

	private int levelNumber;

	private String levelValue;

	private String generatedHierarchyNo;

	private List<GtnWsAttributeBean> attributes;

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public String getGeneratedHierarchyNo() {
		return generatedHierarchyNo;
	}

	public void setGeneratedHierarchyNo(String generatedHierarchyNo) {
		this.generatedHierarchyNo = generatedHierarchyNo;
	}

	public List<GtnWsAttributeBean> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<GtnWsAttributeBean> attributes) {
		this.attributes = attributes;
	}

}
