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
 * This class is a wrapper for {@link PsContract}.
 * </p>
 *
 * @author
 * @see PsContract
 * @generated
 */
@ProviderType
public class PsContractWrapper implements PsContract, ModelWrapper<PsContract> {
	public PsContractWrapper(PsContract psContract) {
		_psContract = psContract;
	}

	@Override
	public Class<?> getModelClass() {
		return PsContract.class;
	}

	@Override
	public String getModelClassName() {
		return PsContract.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("psName", getPsName());
		attributes.put("psNo", getPsNo());
		attributes.put("cfpContractSid", getCfpContractSid());
		attributes.put("psContractSid", getPsContractSid());
		attributes.put("psType", getPsType());
		attributes.put("psContractAttachedStatus", getPsContractAttachedStatus());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("psCategory", getPsCategory());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("psStatus", getPsStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("parentPsId", getParentPsId());
		attributes.put("psDesignation", getPsDesignation());
		attributes.put("batchId", getBatchId());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("psModelSid", getPsModelSid());
		attributes.put("psContractAttachedDate", getPsContractAttachedDate());
		attributes.put("psEndDate", getPsEndDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("parentPsName", getParentPsName());
		attributes.put("psStartDate", getPsStartDate());
		attributes.put("ifpContractSid", getIfpContractSid());
		attributes.put("psTradeClass", getPsTradeClass());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String psName = (String)attributes.get("psName");

		if (psName != null) {
			setPsName(psName);
		}

		String psNo = (String)attributes.get("psNo");

		if (psNo != null) {
			setPsNo(psNo);
		}

		String cfpContractSid = (String)attributes.get("cfpContractSid");

		if (cfpContractSid != null) {
			setCfpContractSid(cfpContractSid);
		}

		Integer psContractSid = (Integer)attributes.get("psContractSid");

		if (psContractSid != null) {
			setPsContractSid(psContractSid);
		}

		Integer psType = (Integer)attributes.get("psType");

		if (psType != null) {
			setPsType(psType);
		}

		Integer psContractAttachedStatus = (Integer)attributes.get(
				"psContractAttachedStatus");

		if (psContractAttachedStatus != null) {
			setPsContractAttachedStatus(psContractAttachedStatus);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer psCategory = (Integer)attributes.get("psCategory");

		if (psCategory != null) {
			setPsCategory(psCategory);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Integer psStatus = (Integer)attributes.get("psStatus");

		if (psStatus != null) {
			setPsStatus(psStatus);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String parentPsId = (String)attributes.get("parentPsId");

		if (parentPsId != null) {
			setParentPsId(parentPsId);
		}

		String psDesignation = (String)attributes.get("psDesignation");

		if (psDesignation != null) {
			setPsDesignation(psDesignation);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer psModelSid = (Integer)attributes.get("psModelSid");

		if (psModelSid != null) {
			setPsModelSid(psModelSid);
		}

		Date psContractAttachedDate = (Date)attributes.get(
				"psContractAttachedDate");

		if (psContractAttachedDate != null) {
			setPsContractAttachedDate(psContractAttachedDate);
		}

		Date psEndDate = (Date)attributes.get("psEndDate");

		if (psEndDate != null) {
			setPsEndDate(psEndDate);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String parentPsName = (String)attributes.get("parentPsName");

		if (parentPsName != null) {
			setParentPsName(parentPsName);
		}

		Date psStartDate = (Date)attributes.get("psStartDate");

		if (psStartDate != null) {
			setPsStartDate(psStartDate);
		}

		String ifpContractSid = (String)attributes.get("ifpContractSid");

		if (ifpContractSid != null) {
			setIfpContractSid(ifpContractSid);
		}

		Integer psTradeClass = (Integer)attributes.get("psTradeClass");

		if (psTradeClass != null) {
			setPsTradeClass(psTradeClass);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new PsContractWrapper((PsContract)_psContract.clone());
	}

	@Override
	public int compareTo(PsContract psContract) {
		return _psContract.compareTo(psContract);
	}

	/**
	* Returns the batch ID of this ps contract.
	*
	* @return the batch ID of this ps contract
	*/
	@Override
	public java.lang.String getBatchId() {
		return _psContract.getBatchId();
	}

	/**
	* Returns the cfp contract sid of this ps contract.
	*
	* @return the cfp contract sid of this ps contract
	*/
	@Override
	public java.lang.String getCfpContractSid() {
		return _psContract.getCfpContractSid();
	}

	/**
	* Returns the contract master sid of this ps contract.
	*
	* @return the contract master sid of this ps contract
	*/
	@Override
	public int getContractMasterSid() {
		return _psContract.getContractMasterSid();
	}

	/**
	* Returns the created by of this ps contract.
	*
	* @return the created by of this ps contract
	*/
	@Override
	public int getCreatedBy() {
		return _psContract.getCreatedBy();
	}

	/**
	* Returns the created date of this ps contract.
	*
	* @return the created date of this ps contract
	*/
	@Override
	public Date getCreatedDate() {
		return _psContract.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _psContract.getExpandoBridge();
	}

	/**
	* Returns the ifp contract sid of this ps contract.
	*
	* @return the ifp contract sid of this ps contract
	*/
	@Override
	public java.lang.String getIfpContractSid() {
		return _psContract.getIfpContractSid();
	}

	/**
	* Returns the inbound status of this ps contract.
	*
	* @return the inbound status of this ps contract
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _psContract.getInboundStatus();
	}

	/**
	* Returns the modified by of this ps contract.
	*
	* @return the modified by of this ps contract
	*/
	@Override
	public int getModifiedBy() {
		return _psContract.getModifiedBy();
	}

	/**
	* Returns the modified date of this ps contract.
	*
	* @return the modified date of this ps contract
	*/
	@Override
	public Date getModifiedDate() {
		return _psContract.getModifiedDate();
	}

	/**
	* Returns the parent ps ID of this ps contract.
	*
	* @return the parent ps ID of this ps contract
	*/
	@Override
	public java.lang.String getParentPsId() {
		return _psContract.getParentPsId();
	}

	/**
	* Returns the parent ps name of this ps contract.
	*
	* @return the parent ps name of this ps contract
	*/
	@Override
	public java.lang.String getParentPsName() {
		return _psContract.getParentPsName();
	}

	/**
	* Returns the primary key of this ps contract.
	*
	* @return the primary key of this ps contract
	*/
	@Override
	public int getPrimaryKey() {
		return _psContract.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _psContract.getPrimaryKeyObj();
	}

	/**
	* Returns the ps category of this ps contract.
	*
	* @return the ps category of this ps contract
	*/
	@Override
	public int getPsCategory() {
		return _psContract.getPsCategory();
	}

	/**
	* Returns the ps contract attached date of this ps contract.
	*
	* @return the ps contract attached date of this ps contract
	*/
	@Override
	public Date getPsContractAttachedDate() {
		return _psContract.getPsContractAttachedDate();
	}

	/**
	* Returns the ps contract attached status of this ps contract.
	*
	* @return the ps contract attached status of this ps contract
	*/
	@Override
	public int getPsContractAttachedStatus() {
		return _psContract.getPsContractAttachedStatus();
	}

	/**
	* Returns the ps contract sid of this ps contract.
	*
	* @return the ps contract sid of this ps contract
	*/
	@Override
	public int getPsContractSid() {
		return _psContract.getPsContractSid();
	}

	/**
	* Returns the ps designation of this ps contract.
	*
	* @return the ps designation of this ps contract
	*/
	@Override
	public java.lang.String getPsDesignation() {
		return _psContract.getPsDesignation();
	}

	/**
	* Returns the ps end date of this ps contract.
	*
	* @return the ps end date of this ps contract
	*/
	@Override
	public Date getPsEndDate() {
		return _psContract.getPsEndDate();
	}

	/**
	* Returns the ps model sid of this ps contract.
	*
	* @return the ps model sid of this ps contract
	*/
	@Override
	public int getPsModelSid() {
		return _psContract.getPsModelSid();
	}

	/**
	* Returns the ps name of this ps contract.
	*
	* @return the ps name of this ps contract
	*/
	@Override
	public java.lang.String getPsName() {
		return _psContract.getPsName();
	}

	/**
	* Returns the ps no of this ps contract.
	*
	* @return the ps no of this ps contract
	*/
	@Override
	public java.lang.String getPsNo() {
		return _psContract.getPsNo();
	}

	/**
	* Returns the ps start date of this ps contract.
	*
	* @return the ps start date of this ps contract
	*/
	@Override
	public Date getPsStartDate() {
		return _psContract.getPsStartDate();
	}

	/**
	* Returns the ps status of this ps contract.
	*
	* @return the ps status of this ps contract
	*/
	@Override
	public int getPsStatus() {
		return _psContract.getPsStatus();
	}

	/**
	* Returns the ps trade class of this ps contract.
	*
	* @return the ps trade class of this ps contract
	*/
	@Override
	public int getPsTradeClass() {
		return _psContract.getPsTradeClass();
	}

	/**
	* Returns the ps type of this ps contract.
	*
	* @return the ps type of this ps contract
	*/
	@Override
	public int getPsType() {
		return _psContract.getPsType();
	}

	/**
	* Returns the record lock status of this ps contract.
	*
	* @return the record lock status of this ps contract
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _psContract.getRecordLockStatus();
	}

	/**
	* Returns the source of this ps contract.
	*
	* @return the source of this ps contract
	*/
	@Override
	public java.lang.String getSource() {
		return _psContract.getSource();
	}

	@Override
	public int hashCode() {
		return _psContract.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _psContract.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _psContract.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _psContract.isNew();
	}

	/**
	* Returns <code>true</code> if this ps contract is record lock status.
	*
	* @return <code>true</code> if this ps contract is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _psContract.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_psContract.persist();
	}

	/**
	* Sets the batch ID of this ps contract.
	*
	* @param batchId the batch ID of this ps contract
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_psContract.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_psContract.setCachedModel(cachedModel);
	}

	/**
	* Sets the cfp contract sid of this ps contract.
	*
	* @param cfpContractSid the cfp contract sid of this ps contract
	*/
	@Override
	public void setCfpContractSid(java.lang.String cfpContractSid) {
		_psContract.setCfpContractSid(cfpContractSid);
	}

	/**
	* Sets the contract master sid of this ps contract.
	*
	* @param contractMasterSid the contract master sid of this ps contract
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_psContract.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the created by of this ps contract.
	*
	* @param createdBy the created by of this ps contract
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_psContract.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ps contract.
	*
	* @param createdDate the created date of this ps contract
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_psContract.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_psContract.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_psContract.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_psContract.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ifp contract sid of this ps contract.
	*
	* @param ifpContractSid the ifp contract sid of this ps contract
	*/
	@Override
	public void setIfpContractSid(java.lang.String ifpContractSid) {
		_psContract.setIfpContractSid(ifpContractSid);
	}

	/**
	* Sets the inbound status of this ps contract.
	*
	* @param inboundStatus the inbound status of this ps contract
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_psContract.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this ps contract.
	*
	* @param modifiedBy the modified by of this ps contract
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_psContract.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ps contract.
	*
	* @param modifiedDate the modified date of this ps contract
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_psContract.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_psContract.setNew(n);
	}

	/**
	* Sets the parent ps ID of this ps contract.
	*
	* @param parentPsId the parent ps ID of this ps contract
	*/
	@Override
	public void setParentPsId(java.lang.String parentPsId) {
		_psContract.setParentPsId(parentPsId);
	}

	/**
	* Sets the parent ps name of this ps contract.
	*
	* @param parentPsName the parent ps name of this ps contract
	*/
	@Override
	public void setParentPsName(java.lang.String parentPsName) {
		_psContract.setParentPsName(parentPsName);
	}

	/**
	* Sets the primary key of this ps contract.
	*
	* @param primaryKey the primary key of this ps contract
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_psContract.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_psContract.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ps category of this ps contract.
	*
	* @param psCategory the ps category of this ps contract
	*/
	@Override
	public void setPsCategory(int psCategory) {
		_psContract.setPsCategory(psCategory);
	}

	/**
	* Sets the ps contract attached date of this ps contract.
	*
	* @param psContractAttachedDate the ps contract attached date of this ps contract
	*/
	@Override
	public void setPsContractAttachedDate(Date psContractAttachedDate) {
		_psContract.setPsContractAttachedDate(psContractAttachedDate);
	}

	/**
	* Sets the ps contract attached status of this ps contract.
	*
	* @param psContractAttachedStatus the ps contract attached status of this ps contract
	*/
	@Override
	public void setPsContractAttachedStatus(int psContractAttachedStatus) {
		_psContract.setPsContractAttachedStatus(psContractAttachedStatus);
	}

	/**
	* Sets the ps contract sid of this ps contract.
	*
	* @param psContractSid the ps contract sid of this ps contract
	*/
	@Override
	public void setPsContractSid(int psContractSid) {
		_psContract.setPsContractSid(psContractSid);
	}

	/**
	* Sets the ps designation of this ps contract.
	*
	* @param psDesignation the ps designation of this ps contract
	*/
	@Override
	public void setPsDesignation(java.lang.String psDesignation) {
		_psContract.setPsDesignation(psDesignation);
	}

	/**
	* Sets the ps end date of this ps contract.
	*
	* @param psEndDate the ps end date of this ps contract
	*/
	@Override
	public void setPsEndDate(Date psEndDate) {
		_psContract.setPsEndDate(psEndDate);
	}

	/**
	* Sets the ps model sid of this ps contract.
	*
	* @param psModelSid the ps model sid of this ps contract
	*/
	@Override
	public void setPsModelSid(int psModelSid) {
		_psContract.setPsModelSid(psModelSid);
	}

	/**
	* Sets the ps name of this ps contract.
	*
	* @param psName the ps name of this ps contract
	*/
	@Override
	public void setPsName(java.lang.String psName) {
		_psContract.setPsName(psName);
	}

	/**
	* Sets the ps no of this ps contract.
	*
	* @param psNo the ps no of this ps contract
	*/
	@Override
	public void setPsNo(java.lang.String psNo) {
		_psContract.setPsNo(psNo);
	}

	/**
	* Sets the ps start date of this ps contract.
	*
	* @param psStartDate the ps start date of this ps contract
	*/
	@Override
	public void setPsStartDate(Date psStartDate) {
		_psContract.setPsStartDate(psStartDate);
	}

	/**
	* Sets the ps status of this ps contract.
	*
	* @param psStatus the ps status of this ps contract
	*/
	@Override
	public void setPsStatus(int psStatus) {
		_psContract.setPsStatus(psStatus);
	}

	/**
	* Sets the ps trade class of this ps contract.
	*
	* @param psTradeClass the ps trade class of this ps contract
	*/
	@Override
	public void setPsTradeClass(int psTradeClass) {
		_psContract.setPsTradeClass(psTradeClass);
	}

	/**
	* Sets the ps type of this ps contract.
	*
	* @param psType the ps type of this ps contract
	*/
	@Override
	public void setPsType(int psType) {
		_psContract.setPsType(psType);
	}

	/**
	* Sets whether this ps contract is record lock status.
	*
	* @param recordLockStatus the record lock status of this ps contract
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_psContract.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this ps contract.
	*
	* @param source the source of this ps contract
	*/
	@Override
	public void setSource(java.lang.String source) {
		_psContract.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PsContract> toCacheModel() {
		return _psContract.toCacheModel();
	}

	@Override
	public PsContract toEscapedModel() {
		return new PsContractWrapper(_psContract.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _psContract.toString();
	}

	@Override
	public PsContract toUnescapedModel() {
		return new PsContractWrapper(_psContract.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _psContract.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PsContractWrapper)) {
			return false;
		}

		PsContractWrapper psContractWrapper = (PsContractWrapper)obj;

		if (Objects.equals(_psContract, psContractWrapper._psContract)) {
			return true;
		}

		return false;
	}

	@Override
	public PsContract getWrappedModel() {
		return _psContract;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _psContract.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _psContract.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_psContract.resetOriginalValues();
	}

	private final PsContract _psContract;
}