/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Karthik.Raja
 */
public class GtnWsReportDashboardBean {

        public GtnWsReportDashboardBean() {
        super();
        }


	private String[] input;
	private Object[] values;
	private String customViewName;
	private int customViewMasterSid;
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
	private Set<Object> ccpDetailsSidList;
	private Set<Object> rsContractSidList;
	private Set<String> filteredHierarchy;
	private String annualTotals;
	private String comparisonBasis;
	private List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList;

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
		return displayFormat == null ? displayFormat : displayFormat.clone();
	}

	public void setDisplayFormat(Object[] displayFormat) {
		this.displayFormat = displayFormat == null ? displayFormat : displayFormat.clone();
	}

	public String getAnnualTotals() {
		return annualTotals;
	}

	public void setAnnualTotals(String annualTotals) {
		this.annualTotals = annualTotals;
	}

	public String getComparisonBasis() {
		return comparisonBasis;
	}

	public void setComparisonBasis(String comparisonBasis) {
		this.comparisonBasis = comparisonBasis;
	}

	public int getCustomViewMasterSid() {
		return customViewMasterSid;
	}

	public void setCustomViewMasterSid(int customViewMasterSid) {
		this.customViewMasterSid = customViewMasterSid;
	}

	public Set<Object> getCcpDetailsSidList() {
		return ccpDetailsSidList;
	}

	public void setCcpDetailsSidList(Set<Object> ccpDetailsSidList) {
		this.ccpDetailsSidList = ccpDetailsSidList;
	}

	public Set<String> getFilteredHierarchy() {
		return filteredHierarchy == null ? null : Collections.unmodifiableSet(filteredHierarchy);
	}

	public void setFilteredHierarchy(Set<String> filteredHierarchy) {
		this.filteredHierarchy = filteredHierarchy == null ? null : Collections.unmodifiableSet(filteredHierarchy);
	}

	public Set<Object> getRsContractSidList() {
		return rsContractSidList;
	}

	public void setRsContractSidList(Set<Object> rsContractSidList) {
		this.rsContractSidList = rsContractSidList;
	}

	public List<GtnReportComparisonProjectionBean> getComparisonProjectionBeanList() {
		return comparisonProjectionBeanList == null ? null : Collections.unmodifiableList(comparisonProjectionBeanList);
	}

	public void setComparisonProjectionBeanList(List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		this.comparisonProjectionBeanList = comparisonProjectionBeanList == null ? null
				: Collections.unmodifiableList(comparisonProjectionBeanList);
	}

}
