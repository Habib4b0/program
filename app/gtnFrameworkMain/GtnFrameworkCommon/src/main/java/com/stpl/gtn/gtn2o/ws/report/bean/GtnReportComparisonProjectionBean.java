package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Comparator;
import java.util.Date;

public class GtnReportComparisonProjectionBean implements Comparator<GtnReportComparisonProjectionBean> {

   
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
	private String projectionType;
	private String userId;

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
		return this.createdDate == null ? null : (Date) this.createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getProjectionType() {
		return projectionType;
	}

	public void setProjectionType(String projectionType) {
		this.projectionType = projectionType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((brand == null) ? 0 : brand.hashCode());
		result = PRIME * result + ((contract == null) ? 0 : contract.hashCode());
		result = PRIME * result + ((contractHolder == null) ? 0 : contractHolder.hashCode());
		result = PRIME * result + createdBy;
		result = PRIME * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = PRIME * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = PRIME * result + ((itemNo == null) ? 0 : itemNo.hashCode());
		result = PRIME * result + ((marketType == null) ? 0 : marketType.hashCode());
		result = PRIME * result + ((projectionDescription == null) ? 0 : projectionDescription.hashCode());
		result = PRIME * result + projectionMasterSid;
		result = PRIME * result + ((projectionName == null) ? 0 : projectionName.hashCode());
		result = PRIME * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GtnReportComparisonProjectionBean other = (GtnReportComparisonProjectionBean) obj;
		return equalsChecking(other);
	}

	private boolean equalsChecking(GtnReportComparisonProjectionBean other) {
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (contractHolder == null) {
			if (other.contractHolder != null)
				return false;
		} else if (!contractHolder.equals(other.contractHolder))
			return false;
		if (createdBy != other.createdBy)
			return false;
		return equalsValidationOne(other);
	}

	private boolean equalsValidationOne(GtnReportComparisonProjectionBean other) {
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemNo == null) {
			if (other.itemNo != null)
				return false;
		} else if (!itemNo.equals(other.itemNo))
			return false;
		return equalsValidationTwo(other);
	}

	private boolean equalsValidationTwo(GtnReportComparisonProjectionBean other) {
		if (marketType == null) {
			if (other.marketType != null)
				return false;
		} else if (!marketType.equals(other.marketType))
			return false;
		if (projectionDescription == null) {
			if (other.projectionDescription != null)
				return false;
		} else if (!projectionDescription.equals(other.projectionDescription))
			return false;
		return equalsValidationThree(other);
	}

	private boolean equalsValidationThree(GtnReportComparisonProjectionBean other) {
		if (projectionMasterSid != other.projectionMasterSid)
			return false;
		if (projectionName == null) {
			if (other.projectionName != null)
				return false;
		} else if (!projectionName.equals(other.projectionName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public int compare(GtnReportComparisonProjectionBean obj1, GtnReportComparisonProjectionBean obj2) {
		return (obj1.getProjectionMasterSid() - obj2.getProjectionMasterSid())
				+ obj1.getProjectionType().compareTo(obj2.getProjectionType());
	}
}
