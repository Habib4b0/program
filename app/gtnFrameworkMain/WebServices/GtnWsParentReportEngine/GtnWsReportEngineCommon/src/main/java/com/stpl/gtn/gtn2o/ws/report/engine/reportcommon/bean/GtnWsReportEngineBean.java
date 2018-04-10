package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.util.ArrayList;
import java.util.List;

public class GtnWsReportEngineBean {

	private List<String> comparisonTableName = new ArrayList<>();

	private int selectedProjectionId;

	private String comparisonBasis;

	private GtnWsReportEngineTreeNode input;

	private String frequency;

	private int salesInclusion;

	private int deductionInclusion;

	public List<String> getComparisonTableName() {
		return comparisonTableName;
	}

	public void addComparisonTableName(String collection) {
		this.comparisonTableName.add(collection);
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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getSalesInclusion() {
		return salesInclusion;
	}

	public void setSalesInclusion(int salesInclusion) {
		this.salesInclusion = salesInclusion;
	}

	public int getDeductionInclusion() {
		return deductionInclusion;
	}

	public void setDeductionInclusion(int deductionInclusion) {
		this.deductionInclusion = deductionInclusion;
	}

}
