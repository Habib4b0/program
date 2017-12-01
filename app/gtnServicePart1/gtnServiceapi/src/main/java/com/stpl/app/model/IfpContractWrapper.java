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
 * This class is a wrapper for {@link IfpContract}.
 * </p>
 *
 * @author
 * @see IfpContract
 * @generated
 */
@ProviderType
public class IfpContractWrapper implements IfpContract,
	ModelWrapper<IfpContract> {
	public IfpContractWrapper(IfpContract ifpContract) {
		_ifpContract = ifpContract;
	}

	@Override
	public Class<?> getModelClass() {
		return IfpContract.class;
	}

	@Override
	public String getModelClassName() {
		return IfpContract.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cfpContractSid", getCfpContractSid());
		attributes.put("parentIfpName", getParentIfpName());
		attributes.put("ifpContractAttachedDate", getIfpContractAttachedDate());
		attributes.put("ifpStatus", getIfpStatus());
		attributes.put("ifpStartDate", getIfpStartDate());
		attributes.put("ifpContractAttachedStatus",
			getIfpContractAttachedStatus());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ifpCategory", getIfpCategory());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("ifpEndDate", getIfpEndDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("ifpDesignation", getIfpDesignation());
		attributes.put("parentIfpId", getParentIfpId());
		attributes.put("batchId", getBatchId());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("ifpType", getIfpType());
		attributes.put("ifpName", getIfpName());
		attributes.put("ifpNo", getIfpNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("ifpContractSid", getIfpContractSid());
		attributes.put("ifpModelSid", getIfpModelSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String cfpContractSid = (String)attributes.get("cfpContractSid");

		if (cfpContractSid != null) {
			setCfpContractSid(cfpContractSid);
		}

		String parentIfpName = (String)attributes.get("parentIfpName");

		if (parentIfpName != null) {
			setParentIfpName(parentIfpName);
		}

		Date ifpContractAttachedDate = (Date)attributes.get(
				"ifpContractAttachedDate");

		if (ifpContractAttachedDate != null) {
			setIfpContractAttachedDate(ifpContractAttachedDate);
		}

		Integer ifpStatus = (Integer)attributes.get("ifpStatus");

		if (ifpStatus != null) {
			setIfpStatus(ifpStatus);
		}

		Date ifpStartDate = (Date)attributes.get("ifpStartDate");

		if (ifpStartDate != null) {
			setIfpStartDate(ifpStartDate);
		}

		Integer ifpContractAttachedStatus = (Integer)attributes.get(
				"ifpContractAttachedStatus");

		if (ifpContractAttachedStatus != null) {
			setIfpContractAttachedStatus(ifpContractAttachedStatus);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer ifpCategory = (Integer)attributes.get("ifpCategory");

		if (ifpCategory != null) {
			setIfpCategory(ifpCategory);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date ifpEndDate = (Date)attributes.get("ifpEndDate");

		if (ifpEndDate != null) {
			setIfpEndDate(ifpEndDate);
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

		String ifpDesignation = (String)attributes.get("ifpDesignation");

		if (ifpDesignation != null) {
			setIfpDesignation(ifpDesignation);
		}

		String parentIfpId = (String)attributes.get("parentIfpId");

		if (parentIfpId != null) {
			setParentIfpId(parentIfpId);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer ifpType = (Integer)attributes.get("ifpType");

		if (ifpType != null) {
			setIfpType(ifpType);
		}

		String ifpName = (String)attributes.get("ifpName");

		if (ifpName != null) {
			setIfpName(ifpName);
		}

		String ifpNo = (String)attributes.get("ifpNo");

		if (ifpNo != null) {
			setIfpNo(ifpNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer ifpContractSid = (Integer)attributes.get("ifpContractSid");

		if (ifpContractSid != null) {
			setIfpContractSid(ifpContractSid);
		}

		Integer ifpModelSid = (Integer)attributes.get("ifpModelSid");

		if (ifpModelSid != null) {
			setIfpModelSid(ifpModelSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IfpContractWrapper((IfpContract)_ifpContract.clone());
	}

	@Override
	public int compareTo(IfpContract ifpContract) {
		return _ifpContract.compareTo(ifpContract);
	}

	/**
	* Returns the batch ID of this ifp contract.
	*
	* @return the batch ID of this ifp contract
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ifpContract.getBatchId();
	}

	/**
	* Returns the cfp contract sid of this ifp contract.
	*
	* @return the cfp contract sid of this ifp contract
	*/
	@Override
	public java.lang.String getCfpContractSid() {
		return _ifpContract.getCfpContractSid();
	}

	/**
	* Returns the contract master sid of this ifp contract.
	*
	* @return the contract master sid of this ifp contract
	*/
	@Override
	public int getContractMasterSid() {
		return _ifpContract.getContractMasterSid();
	}

	/**
	* Returns the created by of this ifp contract.
	*
	* @return the created by of this ifp contract
	*/
	@Override
	public int getCreatedBy() {
		return _ifpContract.getCreatedBy();
	}

	/**
	* Returns the created date of this ifp contract.
	*
	* @return the created date of this ifp contract
	*/
	@Override
	public Date getCreatedDate() {
		return _ifpContract.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ifpContract.getExpandoBridge();
	}

	/**
	* Returns the ifp category of this ifp contract.
	*
	* @return the ifp category of this ifp contract
	*/
	@Override
	public int getIfpCategory() {
		return _ifpContract.getIfpCategory();
	}

	/**
	* Returns the ifp contract attached date of this ifp contract.
	*
	* @return the ifp contract attached date of this ifp contract
	*/
	@Override
	public Date getIfpContractAttachedDate() {
		return _ifpContract.getIfpContractAttachedDate();
	}

	/**
	* Returns the ifp contract attached status of this ifp contract.
	*
	* @return the ifp contract attached status of this ifp contract
	*/
	@Override
	public int getIfpContractAttachedStatus() {
		return _ifpContract.getIfpContractAttachedStatus();
	}

	/**
	* Returns the ifp contract sid of this ifp contract.
	*
	* @return the ifp contract sid of this ifp contract
	*/
	@Override
	public int getIfpContractSid() {
		return _ifpContract.getIfpContractSid();
	}

	/**
	* Returns the ifp designation of this ifp contract.
	*
	* @return the ifp designation of this ifp contract
	*/
	@Override
	public java.lang.String getIfpDesignation() {
		return _ifpContract.getIfpDesignation();
	}

	/**
	* Returns the ifp end date of this ifp contract.
	*
	* @return the ifp end date of this ifp contract
	*/
	@Override
	public Date getIfpEndDate() {
		return _ifpContract.getIfpEndDate();
	}

	/**
	* Returns the ifp model sid of this ifp contract.
	*
	* @return the ifp model sid of this ifp contract
	*/
	@Override
	public int getIfpModelSid() {
		return _ifpContract.getIfpModelSid();
	}

	/**
	* Returns the ifp name of this ifp contract.
	*
	* @return the ifp name of this ifp contract
	*/
	@Override
	public java.lang.String getIfpName() {
		return _ifpContract.getIfpName();
	}

	/**
	* Returns the ifp no of this ifp contract.
	*
	* @return the ifp no of this ifp contract
	*/
	@Override
	public java.lang.String getIfpNo() {
		return _ifpContract.getIfpNo();
	}

	/**
	* Returns the ifp start date of this ifp contract.
	*
	* @return the ifp start date of this ifp contract
	*/
	@Override
	public Date getIfpStartDate() {
		return _ifpContract.getIfpStartDate();
	}

	/**
	* Returns the ifp status of this ifp contract.
	*
	* @return the ifp status of this ifp contract
	*/
	@Override
	public int getIfpStatus() {
		return _ifpContract.getIfpStatus();
	}

	/**
	* Returns the ifp type of this ifp contract.
	*
	* @return the ifp type of this ifp contract
	*/
	@Override
	public int getIfpType() {
		return _ifpContract.getIfpType();
	}

	/**
	* Returns the inbound status of this ifp contract.
	*
	* @return the inbound status of this ifp contract
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _ifpContract.getInboundStatus();
	}

	/**
	* Returns the modified by of this ifp contract.
	*
	* @return the modified by of this ifp contract
	*/
	@Override
	public int getModifiedBy() {
		return _ifpContract.getModifiedBy();
	}

	/**
	* Returns the modified date of this ifp contract.
	*
	* @return the modified date of this ifp contract
	*/
	@Override
	public Date getModifiedDate() {
		return _ifpContract.getModifiedDate();
	}

	/**
	* Returns the parent ifp ID of this ifp contract.
	*
	* @return the parent ifp ID of this ifp contract
	*/
	@Override
	public java.lang.String getParentIfpId() {
		return _ifpContract.getParentIfpId();
	}

	/**
	* Returns the parent ifp name of this ifp contract.
	*
	* @return the parent ifp name of this ifp contract
	*/
	@Override
	public java.lang.String getParentIfpName() {
		return _ifpContract.getParentIfpName();
	}

	/**
	* Returns the primary key of this ifp contract.
	*
	* @return the primary key of this ifp contract
	*/
	@Override
	public int getPrimaryKey() {
		return _ifpContract.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ifpContract.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this ifp contract.
	*
	* @return the record lock status of this ifp contract
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _ifpContract.getRecordLockStatus();
	}

	/**
	* Returns the source of this ifp contract.
	*
	* @return the source of this ifp contract
	*/
	@Override
	public java.lang.String getSource() {
		return _ifpContract.getSource();
	}

	@Override
	public int hashCode() {
		return _ifpContract.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ifpContract.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ifpContract.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ifpContract.isNew();
	}

	/**
	* Returns <code>true</code> if this ifp contract is record lock status.
	*
	* @return <code>true</code> if this ifp contract is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _ifpContract.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_ifpContract.persist();
	}

	/**
	* Sets the batch ID of this ifp contract.
	*
	* @param batchId the batch ID of this ifp contract
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ifpContract.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ifpContract.setCachedModel(cachedModel);
	}

	/**
	* Sets the cfp contract sid of this ifp contract.
	*
	* @param cfpContractSid the cfp contract sid of this ifp contract
	*/
	@Override
	public void setCfpContractSid(java.lang.String cfpContractSid) {
		_ifpContract.setCfpContractSid(cfpContractSid);
	}

	/**
	* Sets the contract master sid of this ifp contract.
	*
	* @param contractMasterSid the contract master sid of this ifp contract
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_ifpContract.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the created by of this ifp contract.
	*
	* @param createdBy the created by of this ifp contract
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_ifpContract.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ifp contract.
	*
	* @param createdDate the created date of this ifp contract
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ifpContract.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ifpContract.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ifpContract.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ifpContract.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ifp category of this ifp contract.
	*
	* @param ifpCategory the ifp category of this ifp contract
	*/
	@Override
	public void setIfpCategory(int ifpCategory) {
		_ifpContract.setIfpCategory(ifpCategory);
	}

	/**
	* Sets the ifp contract attached date of this ifp contract.
	*
	* @param ifpContractAttachedDate the ifp contract attached date of this ifp contract
	*/
	@Override
	public void setIfpContractAttachedDate(Date ifpContractAttachedDate) {
		_ifpContract.setIfpContractAttachedDate(ifpContractAttachedDate);
	}

	/**
	* Sets the ifp contract attached status of this ifp contract.
	*
	* @param ifpContractAttachedStatus the ifp contract attached status of this ifp contract
	*/
	@Override
	public void setIfpContractAttachedStatus(int ifpContractAttachedStatus) {
		_ifpContract.setIfpContractAttachedStatus(ifpContractAttachedStatus);
	}

	/**
	* Sets the ifp contract sid of this ifp contract.
	*
	* @param ifpContractSid the ifp contract sid of this ifp contract
	*/
	@Override
	public void setIfpContractSid(int ifpContractSid) {
		_ifpContract.setIfpContractSid(ifpContractSid);
	}

	/**
	* Sets the ifp designation of this ifp contract.
	*
	* @param ifpDesignation the ifp designation of this ifp contract
	*/
	@Override
	public void setIfpDesignation(java.lang.String ifpDesignation) {
		_ifpContract.setIfpDesignation(ifpDesignation);
	}

	/**
	* Sets the ifp end date of this ifp contract.
	*
	* @param ifpEndDate the ifp end date of this ifp contract
	*/
	@Override
	public void setIfpEndDate(Date ifpEndDate) {
		_ifpContract.setIfpEndDate(ifpEndDate);
	}

	/**
	* Sets the ifp model sid of this ifp contract.
	*
	* @param ifpModelSid the ifp model sid of this ifp contract
	*/
	@Override
	public void setIfpModelSid(int ifpModelSid) {
		_ifpContract.setIfpModelSid(ifpModelSid);
	}

	/**
	* Sets the ifp name of this ifp contract.
	*
	* @param ifpName the ifp name of this ifp contract
	*/
	@Override
	public void setIfpName(java.lang.String ifpName) {
		_ifpContract.setIfpName(ifpName);
	}

	/**
	* Sets the ifp no of this ifp contract.
	*
	* @param ifpNo the ifp no of this ifp contract
	*/
	@Override
	public void setIfpNo(java.lang.String ifpNo) {
		_ifpContract.setIfpNo(ifpNo);
	}

	/**
	* Sets the ifp start date of this ifp contract.
	*
	* @param ifpStartDate the ifp start date of this ifp contract
	*/
	@Override
	public void setIfpStartDate(Date ifpStartDate) {
		_ifpContract.setIfpStartDate(ifpStartDate);
	}

	/**
	* Sets the ifp status of this ifp contract.
	*
	* @param ifpStatus the ifp status of this ifp contract
	*/
	@Override
	public void setIfpStatus(int ifpStatus) {
		_ifpContract.setIfpStatus(ifpStatus);
	}

	/**
	* Sets the ifp type of this ifp contract.
	*
	* @param ifpType the ifp type of this ifp contract
	*/
	@Override
	public void setIfpType(int ifpType) {
		_ifpContract.setIfpType(ifpType);
	}

	/**
	* Sets the inbound status of this ifp contract.
	*
	* @param inboundStatus the inbound status of this ifp contract
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_ifpContract.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this ifp contract.
	*
	* @param modifiedBy the modified by of this ifp contract
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_ifpContract.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ifp contract.
	*
	* @param modifiedDate the modified date of this ifp contract
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ifpContract.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ifpContract.setNew(n);
	}

	/**
	* Sets the parent ifp ID of this ifp contract.
	*
	* @param parentIfpId the parent ifp ID of this ifp contract
	*/
	@Override
	public void setParentIfpId(java.lang.String parentIfpId) {
		_ifpContract.setParentIfpId(parentIfpId);
	}

	/**
	* Sets the parent ifp name of this ifp contract.
	*
	* @param parentIfpName the parent ifp name of this ifp contract
	*/
	@Override
	public void setParentIfpName(java.lang.String parentIfpName) {
		_ifpContract.setParentIfpName(parentIfpName);
	}

	/**
	* Sets the primary key of this ifp contract.
	*
	* @param primaryKey the primary key of this ifp contract
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ifpContract.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ifpContract.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this ifp contract is record lock status.
	*
	* @param recordLockStatus the record lock status of this ifp contract
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_ifpContract.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this ifp contract.
	*
	* @param source the source of this ifp contract
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ifpContract.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IfpContract> toCacheModel() {
		return _ifpContract.toCacheModel();
	}

	@Override
	public IfpContract toEscapedModel() {
		return new IfpContractWrapper(_ifpContract.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ifpContract.toString();
	}

	@Override
	public IfpContract toUnescapedModel() {
		return new IfpContractWrapper(_ifpContract.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ifpContract.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IfpContractWrapper)) {
			return false;
		}

		IfpContractWrapper ifpContractWrapper = (IfpContractWrapper)obj;

		if (Objects.equals(_ifpContract, ifpContractWrapper._ifpContract)) {
			return true;
		}

		return false;
	}

	@Override
	public IfpContract getWrappedModel() {
		return _ifpContract;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ifpContract.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ifpContract.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ifpContract.resetOriginalValues();
	}

	private final IfpContract _ifpContract;
}