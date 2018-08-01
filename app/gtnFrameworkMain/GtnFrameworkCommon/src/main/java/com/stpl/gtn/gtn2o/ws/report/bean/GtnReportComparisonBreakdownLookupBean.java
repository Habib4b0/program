package com.stpl.gtn.gtn2o.ws.report.bean;

import java.io.Serializable;
import java.util.List;

public class GtnReportComparisonBreakdownLookupBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int masterSid;
	private int period;
	private int year;
	private int selectedVariable;
	private List<Object[]> resultList;
	
	private int rowCount;
	private String property;
	private String componentId;
	private String columnId ;
	
	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	private int rowId;
	private int col;
	private String projectionName;
	private List comparisonBreakdownSaveActionList;
	
	
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

	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public List getComparisonBreakdownSaveActionList() {
		return comparisonBreakdownSaveActionList;
	}

	public void setComparisonBreakdownSaveActionList(List comparisonBreakdownSaveActionList) {
		this.comparisonBreakdownSaveActionList = comparisonBreakdownSaveActionList;
	}

	public List<GtnReportComparisonProjectionBean> getComparisonLookupBeanList() {
		return comparisonLookupBeanList;
	}

	public void setComparisonLookupBeanList(List<GtnReportComparisonProjectionBean> comparisonLookupBeanList) {
		this.comparisonLookupBeanList = comparisonLookupBeanList;
	}

	private List<GtnReportComparisonProjectionBean> comparisonLookupBeanList;

	
	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
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
}
