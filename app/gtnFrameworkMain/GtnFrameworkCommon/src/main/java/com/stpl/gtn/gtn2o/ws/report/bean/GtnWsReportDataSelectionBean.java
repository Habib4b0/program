package com.stpl.gtn.gtn2o.ws.report.bean;

public class GtnWsReportDataSelectionBean {
	private long customerHierarchySid;
	private long productHierarchySid;
	private int customerHierarchyForecastLevel;
	private int productHierarchyForecastLevel;
	private String name;

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

}
