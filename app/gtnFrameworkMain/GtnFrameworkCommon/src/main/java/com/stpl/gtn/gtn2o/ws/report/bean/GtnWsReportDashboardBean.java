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

}
