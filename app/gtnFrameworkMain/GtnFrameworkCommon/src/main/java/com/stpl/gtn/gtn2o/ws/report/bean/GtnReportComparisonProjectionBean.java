package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Date;

public class GtnReportComparisonProjectionBean {

	private String projectionName;
	private String projectionDescription;
	private String marketType;
	private String contractHolder;
	private String contract;
	private String brand;
	private String itemNo;
	private String itemName;
	private int projectionMasterSid;
	private Date createdDate;
	private int createdBy;

	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public String getProjectionDescription() {
		return projectionDescription;
	}

	public void setProjectionDescription(String projectionDescription) {
		this.projectionDescription = projectionDescription;
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	public String getContractHolder() {
		return contractHolder;
	}

	public void setContractHolder(String contractHolder) {
		this.contractHolder = contractHolder;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getProjectionMasterSid() {
		return projectionMasterSid;
	}

	public void setProjectionMasterSid(int projectionMasterSid) {
		this.projectionMasterSid = projectionMasterSid;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

}
