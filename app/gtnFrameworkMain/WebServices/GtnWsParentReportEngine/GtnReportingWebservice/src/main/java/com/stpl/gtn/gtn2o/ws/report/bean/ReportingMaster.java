package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Date;

public class ReportingMaster {

//	private int reportingMasterId;
	private Date fromDate;
	private Date toDate;
	private int customerHierarchySid;
	private int customerHierarchyVersionNo;
	private int customerHierarchyLevel;
	private int customerRelationshipBuilderSid;
	private int customerRelationshipVersionNo;
	private int company;
	private int productHierarchySid;
	private int productHierarchyVersionNo;
	private int productHierarchyLevel;
	private int productRelationshipBuilderSid;
	private int productRelationshipVersionNo;
	private String reportingDataSource;
	private int businessUnit;
//	private int createdBy;
//	private Date createdDate;
//	private int modifiedBy;
//	private Date modifiedDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getCustomerHierarchySid() {
		return customerHierarchySid;
	}

	public void setCustomerHierarchySid(int customerHierarchySid) {
		this.customerHierarchySid = customerHierarchySid;
	}

	public int getCustomerHierarchyVersionNo() {
		return customerHierarchyVersionNo;
	}

	public void setCustomerHierarchyVersionNo(int customerHierarchyVersionNo) {
		this.customerHierarchyVersionNo = customerHierarchyVersionNo;
	}

	public int getCustomerHierarchyLevel() {
		return customerHierarchyLevel;
	}

	public void setCustomerHierarchyLevel(int customerHierarchyLevel) {
		this.customerHierarchyLevel = customerHierarchyLevel;
	}

	public int getCustomerRelationshipBuilderSid() {
		return customerRelationshipBuilderSid;
	}

	public void setCustomerRelationshipBuilderSid(int customerRelationshipBuilderSid) {
		this.customerRelationshipBuilderSid = customerRelationshipBuilderSid;
	}

	public int getCustomerRelationshipVersionNo() {
		return customerRelationshipVersionNo;
	}

	public void setCustomerRelationshipVersionNo(int customerRelationshipVersionNo) {
		this.customerRelationshipVersionNo = customerRelationshipVersionNo;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public int getProductHierarchySid() {
		return productHierarchySid;
	}

	public void setProductHierarchySid(int productHierarchySid) {
		this.productHierarchySid = productHierarchySid;
	}

	public int getProductHierarchyVersionNo() {
		return productHierarchyVersionNo;
	}

	public void setProductHierarchyVersionNo(int productHierarchyVersionNo) {
		this.productHierarchyVersionNo = productHierarchyVersionNo;
	}

	public int getProductHierarchyLevel() {
		return productHierarchyLevel;
	}

	public void setProductHierarchyLevel(int productHierarchyLevel) {
		this.productHierarchyLevel = productHierarchyLevel;
	}

	public int getProductRelationshipBuilderSid() {
		return productRelationshipBuilderSid;
	}

	public void setProductRelationshipBuilderSid(int productRelationshipBuilderSid) {
		this.productRelationshipBuilderSid = productRelationshipBuilderSid;
	}

	public int getProductRelationshipVersionNo() {
		return productRelationshipVersionNo;
	}

	public void setProductRelationshipVersionNo(int productRelationshipVersionNo) {
		this.productRelationshipVersionNo = productRelationshipVersionNo;
	}

	public String getReportingDataSource() {
		return reportingDataSource;
	}

	public void setReportingDataSource(String reportingDataSource) {
		this.reportingDataSource = reportingDataSource;
	}

	public int getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(int businessUnit) {
		this.businessUnit = businessUnit;
	}

}
