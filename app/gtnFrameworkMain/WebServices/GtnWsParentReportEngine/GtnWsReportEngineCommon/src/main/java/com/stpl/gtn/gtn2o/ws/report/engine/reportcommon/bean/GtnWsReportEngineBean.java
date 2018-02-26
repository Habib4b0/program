package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.util.ArrayList;
import java.util.List;

public class GtnWsReportEngineBean {

	private List<String> collection = new ArrayList();

	private int selectedProjectionId;

	private String comparisonBasis;

	private GtnWsReportEngineTreeNode input;

	public List<String> getCollection() {
		return collection;
	}

	public void addCollection(String collection) {
		this.collection.add(collection);
	}

	public int getSelectedProjectionId() {
		return selectedProjectionId;
	}

	public void setSelectedProjectionId(int selectedProjectionId) {
		this.selectedProjectionId = selectedProjectionId;
	}

	public String getComparisonBasis() {
		return comparisonBasis;
	}

	public void setComparisonBasis(String comparisonBasis) {
		this.comparisonBasis = comparisonBasis;
	}

	public GtnWsReportEngineTreeNode getInput() {
		return input;
	}

	public void setInput(GtnWsReportEngineTreeNode input) {
		this.input = input;
	}

}
