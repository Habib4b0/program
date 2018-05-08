package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Date;

public class GtnWsReportDataSelectionBean {

	private long customerHierarchySid;
	private long productHierarchySid;
	private int customerHierarchyForecastLevel;
	private int productHierarchyForecastLevel;
	private String name;
	private int productRelationshipBuilderSid;
	private int customerRelationshipBuilderSid;
	private int customerHierarchyVersionNo;
	private int productHierarchyVersionNo;
	private int customerRelationshipVersionNo;
	private int productRelationshipVersionNo;
	private Date forecastEligibleDate;
	private String userId;
	private String sessionId;
	private String uniqueId;

	public int getProductRelationshipBuilderSid() {
		return productRelationshipBuilderSid;
	}

	public void setProductRelationshipBuilderSid(int productRelationshipBuilderSid) {
		this.productRelationshipBuilderSid = productRelationshipBuilderSid;
	}

	public int getCustomerRelationshipBuilderSid() {
		return customerRelationshipBuilderSid;
	}

	public void setCustomerRelationshipBuilderSid(int customerRelationshipBuilderSid) {
		this.customerRelationshipBuilderSid = customerRelationshipBuilderSid;
	}

	public int getCustomerHierarchyVersionNo() {
		return customerHierarchyVersionNo;
	}

	public void setCustomerHierarchyVersionNo(int customerHierarchyVersionNo) {
		this.customerHierarchyVersionNo = customerHierarchyVersionNo;
	}

	public int getProductHierarchyVersionNo() {
		return productHierarchyVersionNo;
	}

	public void setProductHierarchyVersionNo(int productHierarchyVersionNo) {
		this.productHierarchyVersionNo = productHierarchyVersionNo;
	}

	public int getCustomerRelationshipVersionNo() {
		return customerRelationshipVersionNo;
	}

	public void setCustomerRelationshipVersionNo(int customerRelationshipVersionNo) {
		this.customerRelationshipVersionNo = customerRelationshipVersionNo;
	}

	public int getProductRelationshipVersionNo() {
		return productRelationshipVersionNo;
	}

	public void setProductRelationshipVersionNo(int productRelationshipVersionNo) {
		this.productRelationshipVersionNo = productRelationshipVersionNo;
	}

	public Date getForecastEligibleDate() {
		return forecastEligibleDate;
	}

	public void setForecastEligibleDate(Date forecastEligibleDate) {
		this.forecastEligibleDate = forecastEligibleDate;
	}

	public String getReportDataSource() {
		return reportDataSource;
	}

	public void setReportDataSource(String reportDataSource) {
		this.reportDataSource = reportDataSource;
	}

	private String reportDataSource;

	public long getCustomerHierarchySid() {
		return customerHierarchySid;
	}

	public void setCustomerHierarchySid(long customerHierarchySid) {
		this.customerHierarchySid = customerHierarchySid;
	}

	public long getProductHierarchySid() {
		return productHierarchySid;
	}

	public void setProductHierarchySid(long productHierarchySid) {
		this.productHierarchySid = productHierarchySid;
	}

	public int getCustomerHierarchyForecastLevel() {
		return customerHierarchyForecastLevel;
	}

	public void setCustomerHierarchyForecastLevel(int customerHierarchyForecastLevel) {
		this.customerHierarchyForecastLevel = customerHierarchyForecastLevel;
	}

	public int getProductHierarchyForecastLevel() {
		return productHierarchyForecastLevel;
	}

	public void setProductHierarchyForecastLevel(int productHierarchyForecastLevel) {
		this.productHierarchyForecastLevel = productHierarchyForecastLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
}
