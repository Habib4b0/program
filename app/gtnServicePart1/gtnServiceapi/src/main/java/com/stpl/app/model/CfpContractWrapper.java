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

package com.stpl.app.model;

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
 * This class is a wrapper for {@link CfpContract}.
 * </p>
 *
 * @author
 * @see CfpContract
 * @generated
 */
@ProviderType
public class CfpContractWrapper implements CfpContract,
	ModelWrapper<CfpContract> {
	public CfpContractWrapper(CfpContract cfpContract) {
		_cfpContract = cfpContract;
	}

	@Override
	public Class<?> getModelClass() {
		return CfpContract.class;
	}

	@Override
	public String getModelClassName() {
		return CfpContract.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("cfpContractSid", getCfpContractSid());
		attributes.put("cfpType", getCfpType());
		attributes.put("cfpTradeClass", getCfpTradeClass());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("cfpContractAttachedDate", getCfpContractAttachedDate());
		attributes.put("cfpModelSid", getCfpModelSid());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("cfpDesignation", getCfpDesignation());
		attributes.put("cfpName", getCfpName());
		attributes.put("cfpNo", getCfpNo());
		attributes.put("cfpCategory", getCfpCategory());
		attributes.put("source", getSource());
		attributes.put("cfpStatus", getCfpStatus());
		attributes.put("parentCfpId", getParentCfpId());
		attributes.put("cfpContractAttachedStatus",
			getCfpContractAttachedStatus());
		attributes.put("cfpStartDate", getCfpStartDate());
		attributes.put("cfpEndDate", getCfpEndDate());
		attributes.put("parentCfpName", getParentCfpName());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("salesInclusion", getSalesInclusion());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer cfpContractSid = (Integer)attributes.get("cfpContractSid");

		if (cfpContractSid != null) {
			setCfpContractSid(cfpContractSid);
		}

		Integer cfpType = (Integer)attributes.get("cfpType");

		if (cfpType != null) {
			setCfpType(cfpType);
		}

		Integer cfpTradeClass = (Integer)attributes.get("cfpTradeClass");

		if (cfpTradeClass != null) {
			setCfpTradeClass(cfpTradeClass);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Date cfpContractAttachedDate = (Date)attributes.get(
				"cfpContractAttachedDate");

		if (cfpContractAttachedDate != null) {
			setCfpContractAttachedDate(cfpContractAttachedDate);
		}

		Integer cfpModelSid = (Integer)attributes.get("cfpModelSid");

		if (cfpModelSid != null) {
			setCfpModelSid(cfpModelSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String cfpDesignation = (String)attributes.get("cfpDesignation");

		if (cfpDesignation != null) {
			setCfpDesignation(cfpDesignation);
		}

		String cfpName = (String)attributes.get("cfpName");

		if (cfpName != null) {
			setCfpName(cfpName);
		}

		String cfpNo = (String)attributes.get("cfpNo");

		if (cfpNo != null) {
			setCfpNo(cfpNo);
		}

		Integer cfpCategory = (Integer)attributes.get("cfpCategory");

		if (cfpCategory != null) {
			setCfpCategory(cfpCategory);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer cfpStatus = (Integer)attributes.get("cfpStatus");

		if (cfpStatus != null) {
			setCfpStatus(cfpStatus);
		}

		Integer parentCfpId = (Integer)attributes.get("parentCfpId");

		if (parentCfpId != null) {
			setParentCfpId(parentCfpId);
		}

		Integer cfpContractAttachedStatus = (Integer)attributes.get(
				"cfpContractAttachedStatus");

		if (cfpContractAttachedStatus != null) {
			setCfpContractAttachedStatus(cfpContractAttachedStatus);
		}

		Date cfpStartDate = (Date)attributes.get("cfpStartDate");

		if (cfpStartDate != null) {
			setCfpStartDate(cfpStartDate);
		}

		Date cfpEndDate = (Date)attributes.get("cfpEndDate");

		if (cfpEndDate != null) {
			setCfpEndDate(cfpEndDate);
		}

		String parentCfpName = (String)attributes.get("parentCfpName");

		if (parentCfpName != null) {
			setParentCfpName(parentCfpName);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer salesInclusion = (Integer)attributes.get("salesInclusion");

		if (salesInclusion != null) {
			setSalesInclusion(salesInclusion);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CfpContractWrapper((CfpContract)_cfpContract.clone());
	}

	@Override
	public int compareTo(CfpContract cfpContract) {
		return _cfpContract.compareTo(cfpContract);
	}

	/**
	* Returns the batch ID of this cfp contract.
	*
	* @return the batch ID of this cfp contract
	*/
	@Override
	public java.lang.String getBatchId() {
		return _cfpContract.getBatchId();
	}

	/**
	* Returns the cfp category of this cfp contract.
	*
	* @return the cfp category of this cfp contract
	*/
	@Override
	public int getCfpCategory() {
		return _cfpContract.getCfpCategory();
	}

	/**
	* Returns the cfp contract attached date of this cfp contract.
	*
	* @return the cfp contract attached date of this cfp contract
	*/
	@Override
	public Date getCfpContractAttachedDate() {
		return _cfpContract.getCfpContractAttachedDate();
	}

	/**
	* Returns the cfp contract attached status of this cfp contract.
	*
	* @return the cfp contract attached status of this cfp contract
	*/
	@Override
	public int getCfpContractAttachedStatus() {
		return _cfpContract.getCfpContractAttachedStatus();
	}

	/**
	* Returns the cfp contract sid of this cfp contract.
	*
	* @return the cfp contract sid of this cfp contract
	*/
	@Override
	public int getCfpContractSid() {
		return _cfpContract.getCfpContractSid();
	}

	/**
	* Returns the cfp designation of this cfp contract.
	*
	* @return the cfp designation of this cfp contract
	*/
	@Override
	public java.lang.String getCfpDesignation() {
		return _cfpContract.getCfpDesignation();
	}

	/**
	* Returns the cfp end date of this cfp contract.
	*
	* @return the cfp end date of this cfp contract
	*/
	@Override
	public Date getCfpEndDate() {
		return _cfpContract.getCfpEndDate();
	}

	/**
	* Returns the cfp model sid of this cfp contract.
	*
	* @return the cfp model sid of this cfp contract
	*/
	@Override
	public int getCfpModelSid() {
		return _cfpContract.getCfpModelSid();
	}

	/**
	* Returns the cfp name of this cfp contract.
	*
	* @return the cfp name of this cfp contract
	*/
	@Override
	public java.lang.String getCfpName() {
		return _cfpContract.getCfpName();
	}

	/**
	* Returns the cfp no of this cfp contract.
	*
	* @return the cfp no of this cfp contract
	*/
	@Override
	public java.lang.String getCfpNo() {
		return _cfpContract.getCfpNo();
	}

	/**
	* Returns the cfp start date of this cfp contract.
	*
	* @return the cfp start date of this cfp contract
	*/
	@Override
	public Date getCfpStartDate() {
		return _cfpContract.getCfpStartDate();
	}

	/**
	* Returns the cfp status of this cfp contract.
	*
	* @return the cfp status of this cfp contract
	*/
	@Override
	public int getCfpStatus() {
		return _cfpContract.getCfpStatus();
	}

	/**
	* Returns the cfp trade class of this cfp contract.
	*
	* @return the cfp trade class of this cfp contract
	*/
	@Override
	public int getCfpTradeClass() {
		return _cfpContract.getCfpTradeClass();
	}

	/**
	* Returns the cfp type of this cfp contract.
	*
	* @return the cfp type of this cfp contract
	*/
	@Override
	public int getCfpType() {
		return _cfpContract.getCfpType();
	}

	/**
	* Returns the contract master sid of this cfp contract.
	*
	* @return the contract master sid of this cfp contract
	*/
	@Override
	public int getContractMasterSid() {
		return _cfpContract.getContractMasterSid();
	}

	/**
	* Returns the created by of this cfp contract.
	*
	* @return the created by of this cfp contract
	*/
	@Override
	public int getCreatedBy() {
		return _cfpContract.getCreatedBy();
	}

	/**
	* Returns the created date of this cfp contract.
	*
	* @return the created date of this cfp contract
	*/
	@Override
	public Date getCreatedDate() {
		return _cfpContract.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cfpContract.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this cfp contract.
	*
	* @return the inbound status of this cfp contract
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _cfpContract.getInboundStatus();
	}

	/**
	* Returns the modified by of this cfp contract.
	*
	* @return the modified by of this cfp contract
	*/
	@Override
	public int getModifiedBy() {
		return _cfpContract.getModifiedBy();
	}

	/**
	* Returns the modified date of this cfp contract.
	*
	* @return the modified date of this cfp contract
	*/
	@Override
	public Date getModifiedDate() {
		return _cfpContract.getModifiedDate();
	}

	/**
	* Returns the parent cfp ID of this cfp contract.
	*
	* @return the parent cfp ID of this cfp contract
	*/
	@Override
	public int getParentCfpId() {
		return _cfpContract.getParentCfpId();
	}

	/**
	* Returns the parent cfp name of this cfp contract.
	*
	* @return the parent cfp name of this cfp contract
	*/
	@Override
	public java.lang.String getParentCfpName() {
		return _cfpContract.getParentCfpName();
	}

	/**
	* Returns the primary key of this cfp contract.
	*
	* @return the primary key of this cfp contract
	*/
	@Override
	public int getPrimaryKey() {
		return _cfpContract.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cfpContract.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this cfp contract.
	*
	* @return the record lock status of this cfp contract
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _cfpContract.getRecordLockStatus();
	}

	/**
	* Returns the sales inclusion of this cfp contract.
	*
	* @return the sales inclusion of this cfp contract
	*/
	@Override
	public int getSalesInclusion() {
		return _cfpContract.getSalesInclusion();
	}

	/**
	* Returns the source of this cfp contract.
	*
	* @return the source of this cfp contract
	*/
	@Override
	public java.lang.String getSource() {
		return _cfpContract.getSource();
	}

	@Override
	public int hashCode() {
		return _cfpContract.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cfpContract.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cfpContract.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cfpContract.isNew();
	}

	/**
	* Returns <code>true</code> if this cfp contract is record lock status.
	*
	* @return <code>true</code> if this cfp contract is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _cfpContract.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_cfpContract.persist();
	}

	/**
	* Sets the batch ID of this cfp contract.
	*
	* @param batchId the batch ID of this cfp contract
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_cfpContract.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cfpContract.setCachedModel(cachedModel);
	}

	/**
	* Sets the cfp category of this cfp contract.
	*
	* @param cfpCategory the cfp category of this cfp contract
	*/
	@Override
	public void setCfpCategory(int cfpCategory) {
		_cfpContract.setCfpCategory(cfpCategory);
	}

	/**
	* Sets the cfp contract attached date of this cfp contract.
	*
	* @param cfpContractAttachedDate the cfp contract attached date of this cfp contract
	*/
	@Override
	public void setCfpContractAttachedDate(Date cfpContractAttachedDate) {
		_cfpContract.setCfpContractAttachedDate(cfpContractAttachedDate);
	}

	/**
	* Sets the cfp contract attached status of this cfp contract.
	*
	* @param cfpContractAttachedStatus the cfp contract attached status of this cfp contract
	*/
	@Override
	public void setCfpContractAttachedStatus(int cfpContractAttachedStatus) {
		_cfpContract.setCfpContractAttachedStatus(cfpContractAttachedStatus);
	}

	/**
	* Sets the cfp contract sid of this cfp contract.
	*
	* @param cfpContractSid the cfp contract sid of this cfp contract
	*/
	@Override
	public void setCfpContractSid(int cfpContractSid) {
		_cfpContract.setCfpContractSid(cfpContractSid);
	}

	/**
	* Sets the cfp designation of this cfp contract.
	*
	* @param cfpDesignation the cfp designation of this cfp contract
	*/
	@Override
	public void setCfpDesignation(java.lang.String cfpDesignation) {
		_cfpContract.setCfpDesignation(cfpDesignation);
	}

	/**
	* Sets the cfp end date of this cfp contract.
	*
	* @param cfpEndDate the cfp end date of this cfp contract
	*/
	@Override
	public void setCfpEndDate(Date cfpEndDate) {
		_cfpContract.setCfpEndDate(cfpEndDate);
	}

	/**
	* Sets the cfp model sid of this cfp contract.
	*
	* @param cfpModelSid the cfp model sid of this cfp contract
	*/
	@Override
	public void setCfpModelSid(int cfpModelSid) {
		_cfpContract.setCfpModelSid(cfpModelSid);
	}

	/**
	* Sets the cfp name of this cfp contract.
	*
	* @param cfpName the cfp name of this cfp contract
	*/
	@Override
	public void setCfpName(java.lang.String cfpName) {
		_cfpContract.setCfpName(cfpName);
	}

	/**
	* Sets the cfp no of this cfp contract.
	*
	* @param cfpNo the cfp no of this cfp contract
	*/
	@Override
	public void setCfpNo(java.lang.String cfpNo) {
		_cfpContract.setCfpNo(cfpNo);
	}

	/**
	* Sets the cfp start date of this cfp contract.
	*
	* @param cfpStartDate the cfp start date of this cfp contract
	*/
	@Override
	public void setCfpStartDate(Date cfpStartDate) {
		_cfpContract.setCfpStartDate(cfpStartDate);
	}

	/**
	* Sets the cfp status of this cfp contract.
	*
	* @param cfpStatus the cfp status of this cfp contract
	*/
	@Override
	public void setCfpStatus(int cfpStatus) {
		_cfpContract.setCfpStatus(cfpStatus);
	}

	/**
	* Sets the cfp trade class of this cfp contract.
	*
	* @param cfpTradeClass the cfp trade class of this cfp contract
	*/
	@Override
	public void setCfpTradeClass(int cfpTradeClass) {
		_cfpContract.setCfpTradeClass(cfpTradeClass);
	}

	/**
	* Sets the cfp type of this cfp contract.
	*
	* @param cfpType the cfp type of this cfp contract
	*/
	@Override
	public void setCfpType(int cfpType) {
		_cfpContract.setCfpType(cfpType);
	}

	/**
	* Sets the contract master sid of this cfp contract.
	*
	* @param contractMasterSid the contract master sid of this cfp contract
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_cfpContract.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the created by of this cfp contract.
	*
	* @param createdBy the created by of this cfp contract
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_cfpContract.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cfp contract.
	*
	* @param createdDate the created date of this cfp contract
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cfpContract.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cfpContract.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cfpContract.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cfpContract.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this cfp contract.
	*
	* @param inboundStatus the inbound status of this cfp contract
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_cfpContract.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this cfp contract.
	*
	* @param modifiedBy the modified by of this cfp contract
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_cfpContract.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this cfp contract.
	*
	* @param modifiedDate the modified date of this cfp contract
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cfpContract.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cfpContract.setNew(n);
	}

	/**
	* Sets the parent cfp ID of this cfp contract.
	*
	* @param parentCfpId the parent cfp ID of this cfp contract
	*/
	@Override
	public void setParentCfpId(int parentCfpId) {
		_cfpContract.setParentCfpId(parentCfpId);
	}

	/**
	* Sets the parent cfp name of this cfp contract.
	*
	* @param parentCfpName the parent cfp name of this cfp contract
	*/
	@Override
	public void setParentCfpName(java.lang.String parentCfpName) {
		_cfpContract.setParentCfpName(parentCfpName);
	}

	/**
	* Sets the primary key of this cfp contract.
	*
	* @param primaryKey the primary key of this cfp contract
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cfpContract.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cfpContract.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this cfp contract is record lock status.
	*
	* @param recordLockStatus the record lock status of this cfp contract
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_cfpContract.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the sales inclusion of this cfp contract.
	*
	* @param salesInclusion the sales inclusion of this cfp contract
	*/
	@Override
	public void setSalesInclusion(int salesInclusion) {
		_cfpContract.setSalesInclusion(salesInclusion);
	}

	/**
	* Sets the source of this cfp contract.
	*
	* @param source the source of this cfp contract
	*/
	@Override
	public void setSource(java.lang.String source) {
		_cfpContract.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CfpContract> toCacheModel() {
		return _cfpContract.toCacheModel();
	}

	@Override
	public CfpContract toEscapedModel() {
		return new CfpContractWrapper(_cfpContract.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cfpContract.toString();
	}

	@Override
	public CfpContract toUnescapedModel() {
		return new CfpContractWrapper(_cfpContract.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cfpContract.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CfpContractWrapper)) {
			return false;
		}

		CfpContractWrapper cfpContractWrapper = (CfpContractWrapper)obj;

		if (Objects.equals(_cfpContract, cfpContractWrapper._cfpContract)) {
			return true;
		}

		return false;
	}

	@Override
	public CfpContract getWrappedModel() {
		return _cfpContract;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cfpContract.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cfpContract.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cfpContract.resetOriginalValues();
	}

	private final CfpContract _cfpContract;
}