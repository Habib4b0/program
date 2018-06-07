package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.util.Collections;
import java.util.List;

public class GtnWsReportComputedResultsBean {

	public GtnWsReportComputedResultsBean() {
		super();
	}

	private int levelNumber;
	private String hierarchyNo;
	private String generatedHierarchyNo;
	private String levelValue;
	private List<GtnWsAttributeBean> attributes;

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public String getLevelValue() {
		return levelValue;
	}

	public String getGeneratedHierarchyNo() {
		return generatedHierarchyNo;
	}

	public List<GtnWsAttributeBean> getAttributes() {
		return attributes != null ? Collections.unmodifiableList(attributes) : attributes;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public void setGeneratedHierarchyNo(String generatedHierarchyNo) {
		this.generatedHierarchyNo = generatedHierarchyNo;
	}

	public void setAttributes(List<GtnWsAttributeBean> attributes) {
		this.attributes = attributes != null ? Collections.unmodifiableList(attributes) : attributes;
	}

}
