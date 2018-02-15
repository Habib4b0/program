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
 * This class is a wrapper for {@link ImtdDeductionDetails}.
 * </p>
 *
 * @author
 * @see ImtdDeductionDetails
 * @generated
 */
@ProviderType
public class ImtdDeductionDetailsWrapper implements ImtdDeductionDetails,
	ModelWrapper<ImtdDeductionDetails> {
	public ImtdDeductionDetailsWrapper(
		ImtdDeductionDetails imtdDeductionDetails) {
		_imtdDeductionDetails = imtdDeductionDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return ImtdDeductionDetails.class;
	}

	@Override
	public String getModelClassName() {
		return ImtdDeductionDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("imtdDeductionDetailsSid", getImtdDeductionDetailsSid());
		attributes.put("deductionName", getDeductionName());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("imtdCreatedDate", getImtdCreatedDate());
		attributes.put("deductionDetailsSid", getDeductionDetailsSid());
		attributes.put("indicator", getIndicator());
		attributes.put("contractNo", getContractNo());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("deductionSubType", getDeductionSubType());
		attributes.put("cdrModelSid", getCdrModelSid());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("deductionNo", getDeductionNo());
		attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
		attributes.put("usersSid", getUsersSid());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("contractName", getContractName());
		attributes.put("deductionCategory", getDeductionCategory());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("deductionType", getDeductionType());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("operation", getOperation());
		attributes.put("sessionId", getSessionId());
		attributes.put("rsContractSid", getRsContractSid());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer imtdDeductionDetailsSid = (Integer)attributes.get(
				"imtdDeductionDetailsSid");

		if (imtdDeductionDetailsSid != null) {
			setImtdDeductionDetailsSid(imtdDeductionDetailsSid);
		}

		String deductionName = (String)attributes.get("deductionName");

		if (deductionName != null) {
			setDeductionName(deductionName);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String imtdCreatedDate = (String)attributes.get("imtdCreatedDate");

		if (imtdCreatedDate != null) {
			setImtdCreatedDate(imtdCreatedDate);
		}

		Integer deductionDetailsSid = (Integer)attributes.get(
				"deductionDetailsSid");

		if (deductionDetailsSid != null) {
			setDeductionDetailsSid(deductionDetailsSid);
		}

		String indicator = (String)attributes.get("indicator");

		if (indicator != null) {
			setIndicator(indicator);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String deductionSubType = (String)attributes.get("deductionSubType");

		if (deductionSubType != null) {
			setDeductionSubType(deductionSubType);
		}

		Integer cdrModelSid = (Integer)attributes.get("cdrModelSid");

		if (cdrModelSid != null) {
			setCdrModelSid(cdrModelSid);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String deductionNo = (String)attributes.get("deductionNo");

		if (deductionNo != null) {
			setDeductionNo(deductionNo);
		}

		Integer netSalesFormulaMasterSid = (Integer)attributes.get(
				"netSalesFormulaMasterSid");

		if (netSalesFormulaMasterSid != null) {
			setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		}

		Integer usersSid = (Integer)attributes.get("usersSid");

		if (usersSid != null) {
			setUsersSid(usersSid);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		String contractName = (String)attributes.get("contractName");

		if (contractName != null) {
			setContractName(contractName);
		}

		String deductionCategory = (String)attributes.get("deductionCategory");

		if (deductionCategory != null) {
			setDeductionCategory(deductionCategory);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String deductionType = (String)attributes.get("deductionType");

		if (deductionType != null) {
			setDeductionType(deductionType);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String operation = (String)attributes.get("operation");

		if (operation != null) {
			setOperation(operation);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer rsContractSid = (Integer)attributes.get("rsContractSid");

		if (rsContractSid != null) {
			setRsContractSid(rsContractSid);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ImtdDeductionDetailsWrapper((ImtdDeductionDetails)_imtdDeductionDetails.clone());
	}

	@Override
	public int compareTo(ImtdDeductionDetails imtdDeductionDetails) {
		return _imtdDeductionDetails.compareTo(imtdDeductionDetails);
	}

	/**
	* Returns the cdr model sid of this imtd deduction details.
	*
	* @return the cdr model sid of this imtd deduction details
	*/
	@Override
	public int getCdrModelSid() {
		return _imtdDeductionDetails.getCdrModelSid();
	}

	/**
	* Returns the check record of this imtd deduction details.
	*
	* @return the check record of this imtd deduction details
	*/
	@Override
	public boolean getCheckRecord() {
		return _imtdDeductionDetails.getCheckRecord();
	}

	/**
	* Returns the contract master sid of this imtd deduction details.
	*
	* @return the contract master sid of this imtd deduction details
	*/
	@Override
	public int getContractMasterSid() {
		return _imtdDeductionDetails.getContractMasterSid();
	}

	/**
	* Returns the contract name of this imtd deduction details.
	*
	* @return the contract name of this imtd deduction details
	*/
	@Override
	public java.lang.String getContractName() {
		return _imtdDeductionDetails.getContractName();
	}

	/**
	* Returns the contract no of this imtd deduction details.
	*
	* @return the contract no of this imtd deduction details
	*/
	@Override
	public java.lang.String getContractNo() {
		return _imtdDeductionDetails.getContractNo();
	}

	/**
	* Returns the created by of this imtd deduction details.
	*
	* @return the created by of this imtd deduction details
	*/
	@Override
	public int getCreatedBy() {
		return _imtdDeductionDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this imtd deduction details.
	*
	* @return the created date of this imtd deduction details
	*/
	@Override
	public Date getCreatedDate() {
		return _imtdDeductionDetails.getCreatedDate();
	}

	/**
	* Returns the deduction category of this imtd deduction details.
	*
	* @return the deduction category of this imtd deduction details
	*/
	@Override
	public java.lang.String getDeductionCategory() {
		return _imtdDeductionDetails.getDeductionCategory();
	}

	/**
	* Returns the deduction details sid of this imtd deduction details.
	*
	* @return the deduction details sid of this imtd deduction details
	*/
	@Override
	public int getDeductionDetailsSid() {
		return _imtdDeductionDetails.getDeductionDetailsSid();
	}

	/**
	* Returns the deduction name of this imtd deduction details.
	*
	* @return the deduction name of this imtd deduction details
	*/
	@Override
	public java.lang.String getDeductionName() {
		return _imtdDeductionDetails.getDeductionName();
	}

	/**
	* Returns the deduction no of this imtd deduction details.
	*
	* @return the deduction no of this imtd deduction details
	*/
	@Override
	public java.lang.String getDeductionNo() {
		return _imtdDeductionDetails.getDeductionNo();
	}

	/**
	* Returns the deduction sub type of this imtd deduction details.
	*
	* @return the deduction sub type of this imtd deduction details
	*/
	@Override
	public java.lang.String getDeductionSubType() {
		return _imtdDeductionDetails.getDeductionSubType();
	}

	/**
	* Returns the deduction type of this imtd deduction details.
	*
	* @return the deduction type of this imtd deduction details
	*/
	@Override
	public java.lang.String getDeductionType() {
		return _imtdDeductionDetails.getDeductionType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _imtdDeductionDetails.getExpandoBridge();
	}

	/**
	* Returns the imtd created date of this imtd deduction details.
	*
	* @return the imtd created date of this imtd deduction details
	*/
	@Override
	public java.lang.String getImtdCreatedDate() {
		return _imtdDeductionDetails.getImtdCreatedDate();
	}

	/**
	* Returns the imtd deduction details sid of this imtd deduction details.
	*
	* @return the imtd deduction details sid of this imtd deduction details
	*/
	@Override
	public int getImtdDeductionDetailsSid() {
		return _imtdDeductionDetails.getImtdDeductionDetailsSid();
	}

	/**
	* Returns the inbound status of this imtd deduction details.
	*
	* @return the inbound status of this imtd deduction details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _imtdDeductionDetails.getInboundStatus();
	}

	/**
	* Returns the indicator of this imtd deduction details.
	*
	* @return the indicator of this imtd deduction details
	*/
	@Override
	public java.lang.String getIndicator() {
		return _imtdDeductionDetails.getIndicator();
	}

	/**
	* Returns the modified by of this imtd deduction details.
	*
	* @return the modified by of this imtd deduction details
	*/
	@Override
	public int getModifiedBy() {
		return _imtdDeductionDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this imtd deduction details.
	*
	* @return the modified date of this imtd deduction details
	*/
	@Override
	public Date getModifiedDate() {
		return _imtdDeductionDetails.getModifiedDate();
	}

	/**
	* Returns the net sales formula master sid of this imtd deduction details.
	*
	* @return the net sales formula master sid of this imtd deduction details
	*/
	@Override
	public int getNetSalesFormulaMasterSid() {
		return _imtdDeductionDetails.getNetSalesFormulaMasterSid();
	}

	/**
	* Returns the operation of this imtd deduction details.
	*
	* @return the operation of this imtd deduction details
	*/
	@Override
	public java.lang.String getOperation() {
		return _imtdDeductionDetails.getOperation();
	}

	/**
	* Returns the primary key of this imtd deduction details.
	*
	* @return the primary key of this imtd deduction details
	*/
	@Override
	public int getPrimaryKey() {
		return _imtdDeductionDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _imtdDeductionDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this imtd deduction details.
	*
	* @return the record lock status of this imtd deduction details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _imtdDeductionDetails.getRecordLockStatus();
	}

	/**
	* Returns the rs contract sid of this imtd deduction details.
	*
	* @return the rs contract sid of this imtd deduction details
	*/
	@Override
	public int getRsContractSid() {
		return _imtdDeductionDetails.getRsContractSid();
	}

	/**
	* Returns the session ID of this imtd deduction details.
	*
	* @return the session ID of this imtd deduction details
	*/
	@Override
	public java.lang.String getSessionId() {
		return _imtdDeductionDetails.getSessionId();
	}

	/**
	* Returns the users sid of this imtd deduction details.
	*
	* @return the users sid of this imtd deduction details
	*/
	@Override
	public int getUsersSid() {
		return _imtdDeductionDetails.getUsersSid();
	}

	@Override
	public int hashCode() {
		return _imtdDeductionDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _imtdDeductionDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this imtd deduction details is check record.
	*
	* @return <code>true</code> if this imtd deduction details is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _imtdDeductionDetails.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _imtdDeductionDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _imtdDeductionDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this imtd deduction details is record lock status.
	*
	* @return <code>true</code> if this imtd deduction details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _imtdDeductionDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_imtdDeductionDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_imtdDeductionDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cdr model sid of this imtd deduction details.
	*
	* @param cdrModelSid the cdr model sid of this imtd deduction details
	*/
	@Override
	public void setCdrModelSid(int cdrModelSid) {
		_imtdDeductionDetails.setCdrModelSid(cdrModelSid);
	}

	/**
	* Sets whether this imtd deduction details is check record.
	*
	* @param checkRecord the check record of this imtd deduction details
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_imtdDeductionDetails.setCheckRecord(checkRecord);
	}

	/**
	* Sets the contract master sid of this imtd deduction details.
	*
	* @param contractMasterSid the contract master sid of this imtd deduction details
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_imtdDeductionDetails.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the contract name of this imtd deduction details.
	*
	* @param contractName the contract name of this imtd deduction details
	*/
	@Override
	public void setContractName(java.lang.String contractName) {
		_imtdDeductionDetails.setContractName(contractName);
	}

	/**
	* Sets the contract no of this imtd deduction details.
	*
	* @param contractNo the contract no of this imtd deduction details
	*/
	@Override
	public void setContractNo(java.lang.String contractNo) {
		_imtdDeductionDetails.setContractNo(contractNo);
	}

	/**
	* Sets the created by of this imtd deduction details.
	*
	* @param createdBy the created by of this imtd deduction details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_imtdDeductionDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this imtd deduction details.
	*
	* @param createdDate the created date of this imtd deduction details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_imtdDeductionDetails.setCreatedDate(createdDate);
	}

	/**
	* Sets the deduction category of this imtd deduction details.
	*
	* @param deductionCategory the deduction category of this imtd deduction details
	*/
	@Override
	public void setDeductionCategory(java.lang.String deductionCategory) {
		_imtdDeductionDetails.setDeductionCategory(deductionCategory);
	}

	/**
	* Sets the deduction details sid of this imtd deduction details.
	*
	* @param deductionDetailsSid the deduction details sid of this imtd deduction details
	*/
	@Override
	public void setDeductionDetailsSid(int deductionDetailsSid) {
		_imtdDeductionDetails.setDeductionDetailsSid(deductionDetailsSid);
	}

	/**
	* Sets the deduction name of this imtd deduction details.
	*
	* @param deductionName the deduction name of this imtd deduction details
	*/
	@Override
	public void setDeductionName(java.lang.String deductionName) {
		_imtdDeductionDetails.setDeductionName(deductionName);
	}

	/**
	* Sets the deduction no of this imtd deduction details.
	*
	* @param deductionNo the deduction no of this imtd deduction details
	*/
	@Override
	public void setDeductionNo(java.lang.String deductionNo) {
		_imtdDeductionDetails.setDeductionNo(deductionNo);
	}

	/**
	* Sets the deduction sub type of this imtd deduction details.
	*
	* @param deductionSubType the deduction sub type of this imtd deduction details
	*/
	@Override
	public void setDeductionSubType(java.lang.String deductionSubType) {
		_imtdDeductionDetails.setDeductionSubType(deductionSubType);
	}

	/**
	* Sets the deduction type of this imtd deduction details.
	*
	* @param deductionType the deduction type of this imtd deduction details
	*/
	@Override
	public void setDeductionType(java.lang.String deductionType) {
		_imtdDeductionDetails.setDeductionType(deductionType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_imtdDeductionDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_imtdDeductionDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_imtdDeductionDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the imtd created date of this imtd deduction details.
	*
	* @param imtdCreatedDate the imtd created date of this imtd deduction details
	*/
	@Override
	public void setImtdCreatedDate(java.lang.String imtdCreatedDate) {
		_imtdDeductionDetails.setImtdCreatedDate(imtdCreatedDate);
	}

	/**
	* Sets the imtd deduction details sid of this imtd deduction details.
	*
	* @param imtdDeductionDetailsSid the imtd deduction details sid of this imtd deduction details
	*/
	@Override
	public void setImtdDeductionDetailsSid(int imtdDeductionDetailsSid) {
		_imtdDeductionDetails.setImtdDeductionDetailsSid(imtdDeductionDetailsSid);
	}

	/**
	* Sets the inbound status of this imtd deduction details.
	*
	* @param inboundStatus the inbound status of this imtd deduction details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_imtdDeductionDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the indicator of this imtd deduction details.
	*
	* @param indicator the indicator of this imtd deduction details
	*/
	@Override
	public void setIndicator(java.lang.String indicator) {
		_imtdDeductionDetails.setIndicator(indicator);
	}

	/**
	* Sets the modified by of this imtd deduction details.
	*
	* @param modifiedBy the modified by of this imtd deduction details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_imtdDeductionDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this imtd deduction details.
	*
	* @param modifiedDate the modified date of this imtd deduction details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_imtdDeductionDetails.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the net sales formula master sid of this imtd deduction details.
	*
	* @param netSalesFormulaMasterSid the net sales formula master sid of this imtd deduction details
	*/
	@Override
	public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
		_imtdDeductionDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_imtdDeductionDetails.setNew(n);
	}

	/**
	* Sets the operation of this imtd deduction details.
	*
	* @param operation the operation of this imtd deduction details
	*/
	@Override
	public void setOperation(java.lang.String operation) {
		_imtdDeductionDetails.setOperation(operation);
	}

	/**
	* Sets the primary key of this imtd deduction details.
	*
	* @param primaryKey the primary key of this imtd deduction details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_imtdDeductionDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_imtdDeductionDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this imtd deduction details is record lock status.
	*
	* @param recordLockStatus the record lock status of this imtd deduction details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_imtdDeductionDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the rs contract sid of this imtd deduction details.
	*
	* @param rsContractSid the rs contract sid of this imtd deduction details
	*/
	@Override
	public void setRsContractSid(int rsContractSid) {
		_imtdDeductionDetails.setRsContractSid(rsContractSid);
	}

	/**
	* Sets the session ID of this imtd deduction details.
	*
	* @param sessionId the session ID of this imtd deduction details
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_imtdDeductionDetails.setSessionId(sessionId);
	}

	/**
	* Sets the users sid of this imtd deduction details.
	*
	* @param usersSid the users sid of this imtd deduction details
	*/
	@Override
	public void setUsersSid(int usersSid) {
		_imtdDeductionDetails.setUsersSid(usersSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ImtdDeductionDetails> toCacheModel() {
		return _imtdDeductionDetails.toCacheModel();
	}

	@Override
	public ImtdDeductionDetails toEscapedModel() {
		return new ImtdDeductionDetailsWrapper(_imtdDeductionDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _imtdDeductionDetails.toString();
	}

	@Override
	public ImtdDeductionDetails toUnescapedModel() {
		return new ImtdDeductionDetailsWrapper(_imtdDeductionDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _imtdDeductionDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdDeductionDetailsWrapper)) {
			return false;
		}

		ImtdDeductionDetailsWrapper imtdDeductionDetailsWrapper = (ImtdDeductionDetailsWrapper)obj;

		if (Objects.equals(_imtdDeductionDetails,
					imtdDeductionDetailsWrapper._imtdDeductionDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public ImtdDeductionDetails getWrappedModel() {
		return _imtdDeductionDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _imtdDeductionDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _imtdDeductionDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_imtdDeductionDetails.resetOriginalValues();
	}

	private final ImtdDeductionDetails _imtdDeductionDetails;
}