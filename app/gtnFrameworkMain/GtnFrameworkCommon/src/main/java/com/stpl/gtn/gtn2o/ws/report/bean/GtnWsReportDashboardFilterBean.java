package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.ArrayList;
import java.util.List;

public class GtnWsReportDashboardFilterBean {

   
	private int customerLevelNo;
	private int productLevelNo;
	private int deductionLevelNo;
	private String hierarchyType;
	private List<Object> selectedCustomerList = new ArrayList<>();
	private List<Object> selectedProductList = new ArrayList<>();
	private List<Object> selectedDeductionList = new ArrayList<>();

	public int getCustomerLevelNo() {
		return customerLevelNo;
	}

	public void setCustomerLevelNo(int customerLevelNo) {
		this.customerLevelNo = customerLevelNo;
	}

	public int getProductLevelNo() {
		return productLevelNo;
	}

	public void setProductLevelNo(int productLevelNo) {
		this.productLevelNo = productLevelNo;
	}

	public int getDeductionLevelNo() {
		return deductionLevelNo;
	}

	public void setDeductionLevelNo(int deductionLevelNo) {
		this.deductionLevelNo = deductionLevelNo;
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public List<Object> getSelectedCustomerList() {
		return selectedCustomerList;
	}

	public void setSelectedCustomerList(List<Object> selectedCustomerList) {
		this.selectedCustomerList = selectedCustomerList;
	}

	public List<Object> getSelectedProductList() {
		return selectedProductList;
	}

	public void setSelectedProductList(List<Object> selectedProductList) {
		this.selectedProductList = selectedProductList;
	}

	public List<Object> getSelectedDeductionList() {
		return selectedDeductionList;
	}

	public void setSelectedDeductionList(List<Object> selectedDeductionList) {
		this.selectedDeductionList = selectedDeductionList;
	}

}
