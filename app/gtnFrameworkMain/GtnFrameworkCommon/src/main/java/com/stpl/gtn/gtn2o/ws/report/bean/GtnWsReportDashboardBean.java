/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.report.bean;

/**
 *
 * @author Karthik.Raja
 */
public class GtnWsReportDashboardBean {

	private String[] input;
	private Object[] values;
	private String customViewName;
	private String sessionId;
	private GtnWsHierarchyType hierarchyType;
	private String[] selectedVariableType;
	private String[] selectedVariableCategoryType;
	private boolean isVariablesVariances;
	private int headerSequence;
	private int periodRangeFromSid;
	private int periodRangeToSid;
	private String periodStart;
	private String periodTo;
	private String selectFreqString;
	private int salesInclusion;
	private int deductionInclusion;
	private String itemUom;
	private String currencyConversion;
	private Object[] displayFormat;
	private String annualTotals;

	public String[] getInput() {
		return input;
	}

	public void setInput(String[] input) {
		this.input = input;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String getCustomViewName() {
		return customViewName;
	}

	public void setCustomViewName(String customViewName) {
		this.customViewName = customViewName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTableNameWithUniqueId(String tableName) {
		return tableName + "_" + sessionId;
	}

	public GtnWsHierarchyType getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(GtnWsHierarchyType hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public String[] getSelectedVariableType() {
		return selectedVariableType;
	}

	public void setSelectedVariableType(String[] selectedVariableType) {
		this.selectedVariableType = selectedVariableType;
	}

	public String[] getSelectedVariableCategoryType() {
		return selectedVariableCategoryType;
	}

	public void setSelectedVariableCategoryType(String[] selectedVariableCategoryType) {
		this.selectedVariableCategoryType = selectedVariableCategoryType;
	}

	public boolean isVariablesVariances() {
		return isVariablesVariances;
	}

	public void setVariablesVariances(boolean isVariablesVariances) {
		this.isVariablesVariances = isVariablesVariances;
	}

	public int getHeaderSequence() {
		return headerSequence;
	}

	public void setHeaderSequence(int headerSequence) {
		this.headerSequence = headerSequence;
	}

	public int getPeriodRangeFromSid() {
		return periodRangeFromSid;
	}

	public void setPeriodRangeFromSid(int periodRangeFromSid) {
		this.periodRangeFromSid = periodRangeFromSid;
	}

	public int getPeriodRangeToSid() {
		return periodRangeToSid;
	}

	public void setPeriodRangeToSid(int periodRangeToSid) {
		this.periodRangeToSid = periodRangeToSid;
	}

	public String getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(String periodStart) {
		this.periodStart = periodStart;
	}

	public String getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	public String getSelectFreqString() {
		return selectFreqString;
	}

	public void setSelectFreqString(String selectFreqString) {
		this.selectFreqString = selectFreqString;
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

	public String getItemUom() {
		return itemUom;
	}

	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}

	public String getCurrencyConversion() {
		return currencyConversion;
	}

	public void setCurrencyConversion(String currencyConversion) {
		this.currencyConversion = currencyConversion;
	}

	public Object[] getDisplayFormat() {
		return displayFormat;
	}

	public void setDisplayFormat(Object[] displayFormat) {
		this.displayFormat = displayFormat;
	}

	public String getAnnualTotals() {
		return annualTotals;
	}

	public void setAnnualTotals(String annualTotals) {
		this.annualTotals = annualTotals;
	}

}
