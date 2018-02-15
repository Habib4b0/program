/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.parttwo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AccClosureMaster}.
 * </p>
 *
 * @author
 * @see AccClosureMaster
 * @generated
 */
@ProviderType
public class AccClosureMasterWrapper implements AccClosureMaster,
	ModelWrapper<AccClosureMaster> {
	public AccClosureMasterWrapper(AccClosureMaster accClosureMaster) {
		_accClosureMaster = accClosureMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return AccClosureMaster.class;
	}

	@Override
	public String getModelClassName() {
		return AccClosureMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("saveFlag", getSaveFlag());
		attributes.put("accountNo", getAccountNo());
		attributes.put("toDate", getToDate());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("description", getDescription());
		attributes.put("reportName", getReportName());
		attributes.put("rsType", getRsType());
		attributes.put("productIdentifier", getProductIdentifier());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("workflowStatus", getWorkflowStatus());
		attributes.put("moduleType", getModuleType());
		attributes.put("fromDate", getFromDate());
		attributes.put("contractType", getContractType());
		attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("accrualPeriod", getAccrualPeriod());
		attributes.put("companyGroupSid", getCompanyGroupSid());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("rsCategory", getRsCategory());
		attributes.put("adjustmentType", getAdjustmentType());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("itemGroupSid", getItemGroupSid());
		attributes.put("rebateProgramType", getRebateProgramType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean saveFlag = (Boolean)attributes.get("saveFlag");

		if (saveFlag != null) {
			setSaveFlag(saveFlag);
		}

		String accountNo = (String)attributes.get("accountNo");

		if (accountNo != null) {
			setAccountNo(accountNo);
		}

		Date toDate = (Date)attributes.get("toDate");

		if (toDate != null) {
			setToDate(toDate);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String reportName = (String)attributes.get("reportName");

		if (reportName != null) {
			setReportName(reportName);
		}

		Integer rsType = (Integer)attributes.get("rsType");

		if (rsType != null) {
			setRsType(rsType);
		}

		String productIdentifier = (String)attributes.get("productIdentifier");

		if (productIdentifier != null) {
			setProductIdentifier(productIdentifier);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer workflowStatus = (Integer)attributes.get("workflowStatus");

		if (workflowStatus != null) {
			setWorkflowStatus(workflowStatus);
		}

		String moduleType = (String)attributes.get("moduleType");

		if (moduleType != null) {
			setModuleType(moduleType);
		}

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		Integer contractType = (Integer)attributes.get("contractType");

		if (contractType != null) {
			setContractType(contractType);
		}

		Integer glCompanyMasterSid = (Integer)attributes.get(
				"glCompanyMasterSid");

		if (glCompanyMasterSid != null) {
			setGlCompanyMasterSid(glCompanyMasterSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		String accrualPeriod = (String)attributes.get("accrualPeriod");

		if (accrualPeriod != null) {
			setAccrualPeriod(accrualPeriod);
		}

		String companyGroupSid = (String)attributes.get("companyGroupSid");

		if (companyGroupSid != null) {
			setCompanyGroupSid(companyGroupSid);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		Integer rsCategory = (Integer)attributes.get("rsCategory");

		if (rsCategory != null) {
			setRsCategory(rsCategory);
		}

		Integer adjustmentType = (Integer)attributes.get("adjustmentType");

		if (adjustmentType != null) {
			setAdjustmentType(adjustmentType);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String itemGroupSid = (String)attributes.get("itemGroupSid");

		if (itemGroupSid != null) {
			setItemGroupSid(itemGroupSid);
		}

		Integer rebateProgramType = (Integer)attributes.get("rebateProgramType");

		if (rebateProgramType != null) {
			setRebateProgramType(rebateProgramType);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AccClosureMasterWrapper((AccClosureMaster)_accClosureMaster.clone());
	}

	@Override
	public int compareTo(AccClosureMaster accClosureMaster) {
		return _accClosureMaster.compareTo(accClosureMaster);
	}

	/**
	* Returns the acc closure master sid of this acc closure master.
	*
	* @return the acc closure master sid of this acc closure master
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _accClosureMaster.getAccClosureMasterSid();
	}

	/**
	* Returns the account no of this acc closure master.
	*
	* @return the account no of this acc closure master
	*/
	@Override
	public java.lang.String getAccountNo() {
		return _accClosureMaster.getAccountNo();
	}

	/**
	* Returns the accrual period of this acc closure master.
	*
	* @return the accrual period of this acc closure master
	*/
	@Override
	public java.lang.String getAccrualPeriod() {
		return _accClosureMaster.getAccrualPeriod();
	}

	/**
	* Returns the adjustment type of this acc closure master.
	*
	* @return the adjustment type of this acc closure master
	*/
	@Override
	public int getAdjustmentType() {
		return _accClosureMaster.getAdjustmentType();
	}

	/**
	* Returns the company group sid of this acc closure master.
	*
	* @return the company group sid of this acc closure master
	*/
	@Override
	public java.lang.String getCompanyGroupSid() {
		return _accClosureMaster.getCompanyGroupSid();
	}

	/**
	* Returns the contract master sid of this acc closure master.
	*
	* @return the contract master sid of this acc closure master
	*/
	@Override
	public int getContractMasterSid() {
		return _accClosureMaster.getContractMasterSid();
	}

	/**
	* Returns the contract type of this acc closure master.
	*
	* @return the contract type of this acc closure master
	*/
	@Override
	public int getContractType() {
		return _accClosureMaster.getContractType();
	}

	/**
	* Returns the created by of this acc closure master.
	*
	* @return the created by of this acc closure master
	*/
	@Override
	public int getCreatedBy() {
		return _accClosureMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this acc closure master.
	*
	* @return the created date of this acc closure master
	*/
	@Override
	public Date getCreatedDate() {
		return _accClosureMaster.getCreatedDate();
	}

	/**
	* Returns the description of this acc closure master.
	*
	* @return the description of this acc closure master
	*/
	@Override
	public java.lang.String getDescription() {
		return _accClosureMaster.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accClosureMaster.getExpandoBridge();
	}

	/**
	* Returns the from date of this acc closure master.
	*
	* @return the from date of this acc closure master
	*/
	@Override
	public Date getFromDate() {
		return _accClosureMaster.getFromDate();
	}

	/**
	* Returns the gl company master sid of this acc closure master.
	*
	* @return the gl company master sid of this acc closure master
	*/
	@Override
	public int getGlCompanyMasterSid() {
		return _accClosureMaster.getGlCompanyMasterSid();
	}

	/**
	* Returns the item group sid of this acc closure master.
	*
	* @return the item group sid of this acc closure master
	*/
	@Override
	public java.lang.String getItemGroupSid() {
		return _accClosureMaster.getItemGroupSid();
	}

	/**
	* Returns the item master sid of this acc closure master.
	*
	* @return the item master sid of this acc closure master
	*/
	@Override
	public int getItemMasterSid() {
		return _accClosureMaster.getItemMasterSid();
	}

	/**
	* Returns the modified by of this acc closure master.
	*
	* @return the modified by of this acc closure master
	*/
	@Override
	public int getModifiedBy() {
		return _accClosureMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this acc closure master.
	*
	* @return the modified date of this acc closure master
	*/
	@Override
	public Date getModifiedDate() {
		return _accClosureMaster.getModifiedDate();
	}

	/**
	* Returns the module type of this acc closure master.
	*
	* @return the module type of this acc closure master
	*/
	@Override
	public java.lang.String getModuleType() {
		return _accClosureMaster.getModuleType();
	}

	/**
	* Returns the primary key of this acc closure master.
	*
	* @return the primary key of this acc closure master
	*/
	@Override
	public int getPrimaryKey() {
		return _accClosureMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accClosureMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the product identifier of this acc closure master.
	*
	* @return the product identifier of this acc closure master
	*/
	@Override
	public java.lang.String getProductIdentifier() {
		return _accClosureMaster.getProductIdentifier();
	}

	/**
	* Returns the rebate program type of this acc closure master.
	*
	* @return the rebate program type of this acc closure master
	*/
	@Override
	public int getRebateProgramType() {
		return _accClosureMaster.getRebateProgramType();
	}

	/**
	* Returns the report name of this acc closure master.
	*
	* @return the report name of this acc closure master
	*/
	@Override
	public java.lang.String getReportName() {
		return _accClosureMaster.getReportName();
	}

	/**
	* Returns the rs category of this acc closure master.
	*
	* @return the rs category of this acc closure master
	*/
	@Override
	public int getRsCategory() {
		return _accClosureMaster.getRsCategory();
	}

	/**
	* Returns the rs type of this acc closure master.
	*
	* @return the rs type of this acc closure master
	*/
	@Override
	public int getRsType() {
		return _accClosureMaster.getRsType();
	}

	/**
	* Returns the save flag of this acc closure master.
	*
	* @return the save flag of this acc closure master
	*/
	@Override
	public boolean getSaveFlag() {
		return _accClosureMaster.getSaveFlag();
	}

	/**
	* Returns the to date of this acc closure master.
	*
	* @return the to date of this acc closure master
	*/
	@Override
	public Date getToDate() {
		return _accClosureMaster.getToDate();
	}

	/**
	* Returns the workflow status of this acc closure master.
	*
	* @return the workflow status of this acc closure master
	*/
	@Override
	public int getWorkflowStatus() {
		return _accClosureMaster.getWorkflowStatus();
	}

	@Override
	public int hashCode() {
		return _accClosureMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _accClosureMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accClosureMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accClosureMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this acc closure master is save flag.
	*
	* @return <code>true</code> if this acc closure master is save flag; <code>false</code> otherwise
	*/
	@Override
	public boolean isSaveFlag() {
		return _accClosureMaster.isSaveFlag();
	}

	@Override
	public void persist() {
		_accClosureMaster.persist();
	}

	/**
	* Sets the acc closure master sid of this acc closure master.
	*
	* @param accClosureMasterSid the acc closure master sid of this acc closure master
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_accClosureMaster.setAccClosureMasterSid(accClosureMasterSid);
	}

	/**
	* Sets the account no of this acc closure master.
	*
	* @param accountNo the account no of this acc closure master
	*/
	@Override
	public void setAccountNo(java.lang.String accountNo) {
		_accClosureMaster.setAccountNo(accountNo);
	}

	/**
	* Sets the accrual period of this acc closure master.
	*
	* @param accrualPeriod the accrual period of this acc closure master
	*/
	@Override
	public void setAccrualPeriod(java.lang.String accrualPeriod) {
		_accClosureMaster.setAccrualPeriod(accrualPeriod);
	}

	/**
	* Sets the adjustment type of this acc closure master.
	*
	* @param adjustmentType the adjustment type of this acc closure master
	*/
	@Override
	public void setAdjustmentType(int adjustmentType) {
		_accClosureMaster.setAdjustmentType(adjustmentType);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accClosureMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the company group sid of this acc closure master.
	*
	* @param companyGroupSid the company group sid of this acc closure master
	*/
	@Override
	public void setCompanyGroupSid(java.lang.String companyGroupSid) {
		_accClosureMaster.setCompanyGroupSid(companyGroupSid);
	}

	/**
	* Sets the contract master sid of this acc closure master.
	*
	* @param contractMasterSid the contract master sid of this acc closure master
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_accClosureMaster.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the contract type of this acc closure master.
	*
	* @param contractType the contract type of this acc closure master
	*/
	@Override
	public void setContractType(int contractType) {
		_accClosureMaster.setContractType(contractType);
	}

	/**
	* Sets the created by of this acc closure master.
	*
	* @param createdBy the created by of this acc closure master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_accClosureMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this acc closure master.
	*
	* @param createdDate the created date of this acc closure master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_accClosureMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the description of this acc closure master.
	*
	* @param description the description of this acc closure master
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_accClosureMaster.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accClosureMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accClosureMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accClosureMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the from date of this acc closure master.
	*
	* @param fromDate the from date of this acc closure master
	*/
	@Override
	public void setFromDate(Date fromDate) {
		_accClosureMaster.setFromDate(fromDate);
	}

	/**
	* Sets the gl company master sid of this acc closure master.
	*
	* @param glCompanyMasterSid the gl company master sid of this acc closure master
	*/
	@Override
	public void setGlCompanyMasterSid(int glCompanyMasterSid) {
		_accClosureMaster.setGlCompanyMasterSid(glCompanyMasterSid);
	}

	/**
	* Sets the item group sid of this acc closure master.
	*
	* @param itemGroupSid the item group sid of this acc closure master
	*/
	@Override
	public void setItemGroupSid(java.lang.String itemGroupSid) {
		_accClosureMaster.setItemGroupSid(itemGroupSid);
	}

	/**
	* Sets the item master sid of this acc closure master.
	*
	* @param itemMasterSid the item master sid of this acc closure master
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_accClosureMaster.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this acc closure master.
	*
	* @param modifiedBy the modified by of this acc closure master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_accClosureMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this acc closure master.
	*
	* @param modifiedDate the modified date of this acc closure master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accClosureMaster.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the module type of this acc closure master.
	*
	* @param moduleType the module type of this acc closure master
	*/
	@Override
	public void setModuleType(java.lang.String moduleType) {
		_accClosureMaster.setModuleType(moduleType);
	}

	@Override
	public void setNew(boolean n) {
		_accClosureMaster.setNew(n);
	}

	/**
	* Sets the primary key of this acc closure master.
	*
	* @param primaryKey the primary key of this acc closure master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_accClosureMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accClosureMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product identifier of this acc closure master.
	*
	* @param productIdentifier the product identifier of this acc closure master
	*/
	@Override
	public void setProductIdentifier(java.lang.String productIdentifier) {
		_accClosureMaster.setProductIdentifier(productIdentifier);
	}

	/**
	* Sets the rebate program type of this acc closure master.
	*
	* @param rebateProgramType the rebate program type of this acc closure master
	*/
	@Override
	public void setRebateProgramType(int rebateProgramType) {
		_accClosureMaster.setRebateProgramType(rebateProgramType);
	}

	/**
	* Sets the report name of this acc closure master.
	*
	* @param reportName the report name of this acc closure master
	*/
	@Override
	public void setReportName(java.lang.String reportName) {
		_accClosureMaster.setReportName(reportName);
	}

	/**
	* Sets the rs category of this acc closure master.
	*
	* @param rsCategory the rs category of this acc closure master
	*/
	@Override
	public void setRsCategory(int rsCategory) {
		_accClosureMaster.setRsCategory(rsCategory);
	}

	/**
	* Sets the rs type of this acc closure master.
	*
	* @param rsType the rs type of this acc closure master
	*/
	@Override
	public void setRsType(int rsType) {
		_accClosureMaster.setRsType(rsType);
	}

	/**
	* Sets whether this acc closure master is save flag.
	*
	* @param saveFlag the save flag of this acc closure master
	*/
	@Override
	public void setSaveFlag(boolean saveFlag) {
		_accClosureMaster.setSaveFlag(saveFlag);
	}

	/**
	* Sets the to date of this acc closure master.
	*
	* @param toDate the to date of this acc closure master
	*/
	@Override
	public void setToDate(Date toDate) {
		_accClosureMaster.setToDate(toDate);
	}

	/**
	* Sets the workflow status of this acc closure master.
	*
	* @param workflowStatus the workflow status of this acc closure master
	*/
	@Override
	public void setWorkflowStatus(int workflowStatus) {
		_accClosureMaster.setWorkflowStatus(workflowStatus);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccClosureMaster> toCacheModel() {
		return _accClosureMaster.toCacheModel();
	}

	@Override
	public AccClosureMaster toEscapedModel() {
		return new AccClosureMasterWrapper(_accClosureMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accClosureMaster.toString();
	}

	@Override
	public AccClosureMaster toUnescapedModel() {
		return new AccClosureMasterWrapper(_accClosureMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _accClosureMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccClosureMasterWrapper)) {
			return false;
		}

		AccClosureMasterWrapper accClosureMasterWrapper = (AccClosureMasterWrapper)obj;

		if (Objects.equals(_accClosureMaster,
					accClosureMasterWrapper._accClosureMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public AccClosureMaster getWrappedModel() {
		return _accClosureMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accClosureMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accClosureMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accClosureMaster.resetOriginalValues();
	}

	private final AccClosureMaster _accClosureMaster;
}