package com.stpl.gtn.gtn2o.ws.report.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnReportComparisonBreakdownLookupBean implements Serializable {

    public GtnReportComparisonBreakdownLookupBean() {
        super();
    }

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
    private String columnId;
    private int rowId;
    private int col;
    private String projectionName;
    private List comparisonBreakdownSaveActionList;
    private List<GtnReportComparisonProjectionBean> comparisonLookupBeanList;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getPeriod() {
		return period;
	}

	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public List getComparisonBreakdownSaveActionList() {
		return comparisonBreakdownSaveActionList == null ? comparisonBreakdownSaveActionList
				: Collections.unmodifiableList(comparisonBreakdownSaveActionList);
	}

	public void setComparisonBreakdownSaveActionList(List comparisonBreakdownSaveActionList) {
		this.comparisonBreakdownSaveActionList = comparisonBreakdownSaveActionList == null
				? comparisonBreakdownSaveActionList
				: new ArrayList<>(comparisonBreakdownSaveActionList);
	}

	public List<GtnReportComparisonProjectionBean> getComparisonLookupBeanList() {
		return comparisonLookupBeanList;
	}

	public List<Object[]> getResultList() {
		return resultList == null ? resultList : new ArrayList<>(resultList);
	}

	public void setComparisonLookupBeanList(List<GtnReportComparisonProjectionBean> comparisonLookupBeanList) {
		this.comparisonLookupBeanList = comparisonLookupBeanList == null ? comparisonLookupBeanList
				: new ArrayList<>(comparisonLookupBeanList);
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getMasterSid() {
		return masterSid;
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

	public int getSelectedVariable() {
		return selectedVariable;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public void setResultList(List<Object[]> resultList) {
		this.resultList = resultList == null ? resultList : new ArrayList<>(resultList);
	}

	public void setMasterSid(int masterSid) {
		this.masterSid = masterSid;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setSelectedVariable(int selectedVariable) {
		this.selectedVariable = selectedVariable;
	}

	public int getYear() {
		return year;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

}
