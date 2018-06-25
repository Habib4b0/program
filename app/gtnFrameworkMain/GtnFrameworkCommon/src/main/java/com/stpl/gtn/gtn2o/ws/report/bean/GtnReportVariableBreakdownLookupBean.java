/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportVariableBreakdownLookupBean {

	private int masterSid;
	private int period;
	private int year;
	private int selectedVariable;
	private List<Object[]> resultList;

	private String property;
	private String componentId;
	private int rowId;
	private int col;
	private String currentDateField;

	private String projectionName;

	private List variableBreakdownSaveActionList;
	private int rowCount;
	private List<GtnReportComparisonProjectionBean> comparisonLookupBeanList;

	public List<Object[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object[]> resultList) {
		this.resultList = resultList;
	}

	public int getMasterSid() {
		return masterSid;
	}

	public void setMasterSid(int masterSid) {
		this.masterSid = masterSid;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSelectedVariable() {
		return selectedVariable;
	}

	public void setSelectedVariable(int selectedVariable) {
		this.selectedVariable = selectedVariable;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getCurrentDateField() {
		return currentDateField;
	}

	public void setCurrentDateField(String currentDateField) {
		this.currentDateField = currentDateField;
	}

	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public List getVariableBreakdownSaveActionList() {
		return variableBreakdownSaveActionList;
	}

	public void setVariableBreakdownSaveActionList(List variableBreakdownSaveActionList) {
		this.variableBreakdownSaveActionList = variableBreakdownSaveActionList;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<GtnReportComparisonProjectionBean> getComparisonLookupBeanList() {
		return comparisonLookupBeanList;
	}

	public void setComparisonLookupBeanList(List<GtnReportComparisonProjectionBean> comparisonLookupBeanList) {
		this.comparisonLookupBeanList = comparisonLookupBeanList;
	}

}
