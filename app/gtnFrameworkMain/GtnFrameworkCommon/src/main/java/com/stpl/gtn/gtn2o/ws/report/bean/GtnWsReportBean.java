package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;
import java.util.Map;

public class GtnWsReportBean {
	private GtnWsReportDataSelectionBean dataSelectionBean;
	private GtnWsReportCustomViewBean customViewBean;
	private GtnReportHierarchyLookupBean hierarchyLookupBean;
	private List<Object[]> businessUnitList;
	private Map<String, String> customerDescMap;
	private Map<String, String> productDescMap;
	private String customerRelationshipBuilderSid;
	private String productRelationshipBuilderSid;
	private int customerHierarchyVersionNo;
	private int productHierarchyVersionNo;
	private int customerRelationVersionNo;
	private int productRelationVersionNo;
	private Map<String, List> hierarchyLevelDetails;

	public GtnWsReportDataSelectionBean getDataSelectionBean() {
		return dataSelectionBean;
	}

	public void setDataSelectionBean(GtnWsReportDataSelectionBean dataSelectionBean) {
		this.dataSelectionBean = dataSelectionBean;
	}

	public GtnWsReportCustomViewBean getCustomViewBean() {
		return customViewBean;
	}

	public void setCustomViewBean(GtnWsReportCustomViewBean customViewBean) {
		this.customViewBean = customViewBean;
	}

	public GtnReportHierarchyLookupBean getHierarchyLookupBean() {
		return hierarchyLookupBean;
	}

	public void setHierarchyLookupBean(GtnReportHierarchyLookupBean hierarchyLookupBean) {
		this.hierarchyLookupBean = hierarchyLookupBean;
	}

	public List<Object[]> getBusinessUnitList() {
		return businessUnitList;
	}

	public void setBusinessUnitList(List<Object[]> businessUnitList) {
		this.businessUnitList = businessUnitList;
	}

	public Map<String, String> getCustomerDescMap() {
		return customerDescMap;
	}

	public void setCustomerDescMap(Map<String, String> customerDescMap) {
		this.customerDescMap = customerDescMap;
	}

	public Map<String, String> getProductDescMap() {
		return productDescMap;
	}

	public void setProductDescMap(Map<String, String> productDescMap) {
		this.productDescMap = productDescMap;
	}

	public String getCustomerRelationshipBuilderSid() {
		return customerRelationshipBuilderSid;
	}

	public void setCustomerRelationshipBuilderSid(String customerRelationshipBuilderSid) {
		this.customerRelationshipBuilderSid = customerRelationshipBuilderSid;
	}

	public String getProductRelationshipBuilderSid() {
		return productRelationshipBuilderSid;
	}

	public void setProductRelationshipBuilderSid(String productRelationshipBuilderSid) {
		this.productRelationshipBuilderSid = productRelationshipBuilderSid;
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

	public int getCustomerRelationVersionNo() {
		return customerRelationVersionNo;
	}

	public void setCustomerRelationVersionNo(int customerRelationVersionNo) {
		this.customerRelationVersionNo = customerRelationVersionNo;
	}

	public int getProductRelationVersionNo() {
		return productRelationVersionNo;
	}

	public void setProductRelationVersionNo(int productRelationVersionNo) {
		this.productRelationVersionNo = productRelationVersionNo;
	}

	public Map<String, List> getHierarchyLevelDetails() {
		return hierarchyLevelDetails;
	}

	public void setHierarchyLevelDetails(Map<String, List> hierarchyLevelDetails) {
		this.hierarchyLevelDetails = hierarchyLevelDetails;
	}
	
}
