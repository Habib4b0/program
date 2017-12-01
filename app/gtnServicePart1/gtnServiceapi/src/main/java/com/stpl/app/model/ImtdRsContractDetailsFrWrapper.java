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
 * This class is a wrapper for {@link ImtdRsContractDetailsFr}.
 * </p>
 *
 * @author
 * @see ImtdRsContractDetailsFr
 * @generated
 */
@ProviderType
public class ImtdRsContractDetailsFrWrapper implements ImtdRsContractDetailsFr,
	ModelWrapper<ImtdRsContractDetailsFr> {
	public ImtdRsContractDetailsFrWrapper(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		_imtdRsContractDetailsFr = imtdRsContractDetailsFr;
	}

	@Override
	public Class<?> getModelClass() {
		return ImtdRsContractDetailsFr.class;
	}

	@Override
	public String getModelClassName() {
		return ImtdRsContractDetailsFr.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("imtdRsContractDetailsFrSid",
			getImtdRsContractDetailsFrSid());
		attributes.put("formulaMethodId", getFormulaMethodId());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("rsContractDetailsFrSid", getRsContractDetailsFrSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("rsContractDetailsSid", getRsContractDetailsSid());
		attributes.put("imtdItemPriceRebateDetailsSid",
			getImtdItemPriceRebateDetailsSid());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("batchId", getBatchId());
		attributes.put("imtdCreatedDate", getImtdCreatedDate());
		attributes.put("sessionId", getSessionId());
		attributes.put("usersId", getUsersId());
		attributes.put("operation", getOperation());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("formulaId", getFormulaId());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer imtdRsContractDetailsFrSid = (Integer)attributes.get(
				"imtdRsContractDetailsFrSid");

		if (imtdRsContractDetailsFrSid != null) {
			setImtdRsContractDetailsFrSid(imtdRsContractDetailsFrSid);
		}

		String formulaMethodId = (String)attributes.get("formulaMethodId");

		if (formulaMethodId != null) {
			setFormulaMethodId(formulaMethodId);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer rsContractDetailsFrSid = (Integer)attributes.get(
				"rsContractDetailsFrSid");

		if (rsContractDetailsFrSid != null) {
			setRsContractDetailsFrSid(rsContractDetailsFrSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer rsContractDetailsSid = (Integer)attributes.get(
				"rsContractDetailsSid");

		if (rsContractDetailsSid != null) {
			setRsContractDetailsSid(rsContractDetailsSid);
		}

		Integer imtdItemPriceRebateDetailsSid = (Integer)attributes.get(
				"imtdItemPriceRebateDetailsSid");

		if (imtdItemPriceRebateDetailsSid != null) {
			setImtdItemPriceRebateDetailsSid(imtdItemPriceRebateDetailsSid);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
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

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Date imtdCreatedDate = (Date)attributes.get("imtdCreatedDate");

		if (imtdCreatedDate != null) {
			setImtdCreatedDate(imtdCreatedDate);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String usersId = (String)attributes.get("usersId");

		if (usersId != null) {
			setUsersId(usersId);
		}

		String operation = (String)attributes.get("operation");

		if (operation != null) {
			setOperation(operation);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer formulaId = (Integer)attributes.get("formulaId");

		if (formulaId != null) {
			setFormulaId(formulaId);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ImtdRsContractDetailsFrWrapper((ImtdRsContractDetailsFr)_imtdRsContractDetailsFr.clone());
	}

	@Override
	public int compareTo(ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		return _imtdRsContractDetailsFr.compareTo(imtdRsContractDetailsFr);
	}

	/**
	* Returns the batch ID of this imtd rs contract details fr.
	*
	* @return the batch ID of this imtd rs contract details fr
	*/
	@Override
	public java.lang.String getBatchId() {
		return _imtdRsContractDetailsFr.getBatchId();
	}

	/**
	* Returns the created by of this imtd rs contract details fr.
	*
	* @return the created by of this imtd rs contract details fr
	*/
	@Override
	public int getCreatedBy() {
		return _imtdRsContractDetailsFr.getCreatedBy();
	}

	/**
	* Returns the created date of this imtd rs contract details fr.
	*
	* @return the created date of this imtd rs contract details fr
	*/
	@Override
	public Date getCreatedDate() {
		return _imtdRsContractDetailsFr.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _imtdRsContractDetailsFr.getExpandoBridge();
	}

	/**
	* Returns the formula ID of this imtd rs contract details fr.
	*
	* @return the formula ID of this imtd rs contract details fr
	*/
	@Override
	public int getFormulaId() {
		return _imtdRsContractDetailsFr.getFormulaId();
	}

	/**
	* Returns the formula method ID of this imtd rs contract details fr.
	*
	* @return the formula method ID of this imtd rs contract details fr
	*/
	@Override
	public java.lang.String getFormulaMethodId() {
		return _imtdRsContractDetailsFr.getFormulaMethodId();
	}

	/**
	* Returns the imtd created date of this imtd rs contract details fr.
	*
	* @return the imtd created date of this imtd rs contract details fr
	*/
	@Override
	public Date getImtdCreatedDate() {
		return _imtdRsContractDetailsFr.getImtdCreatedDate();
	}

	/**
	* Returns the imtd item price rebate details sid of this imtd rs contract details fr.
	*
	* @return the imtd item price rebate details sid of this imtd rs contract details fr
	*/
	@Override
	public int getImtdItemPriceRebateDetailsSid() {
		return _imtdRsContractDetailsFr.getImtdItemPriceRebateDetailsSid();
	}

	/**
	* Returns the imtd rs contract details fr sid of this imtd rs contract details fr.
	*
	* @return the imtd rs contract details fr sid of this imtd rs contract details fr
	*/
	@Override
	public int getImtdRsContractDetailsFrSid() {
		return _imtdRsContractDetailsFr.getImtdRsContractDetailsFrSid();
	}

	/**
	* Returns the inbound status of this imtd rs contract details fr.
	*
	* @return the inbound status of this imtd rs contract details fr
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _imtdRsContractDetailsFr.getInboundStatus();
	}

	/**
	* Returns the item master sid of this imtd rs contract details fr.
	*
	* @return the item master sid of this imtd rs contract details fr
	*/
	@Override
	public int getItemMasterSid() {
		return _imtdRsContractDetailsFr.getItemMasterSid();
	}

	/**
	* Returns the modified by of this imtd rs contract details fr.
	*
	* @return the modified by of this imtd rs contract details fr
	*/
	@Override
	public int getModifiedBy() {
		return _imtdRsContractDetailsFr.getModifiedBy();
	}

	/**
	* Returns the modified date of this imtd rs contract details fr.
	*
	* @return the modified date of this imtd rs contract details fr
	*/
	@Override
	public Date getModifiedDate() {
		return _imtdRsContractDetailsFr.getModifiedDate();
	}

	/**
	* Returns the operation of this imtd rs contract details fr.
	*
	* @return the operation of this imtd rs contract details fr
	*/
	@Override
	public java.lang.String getOperation() {
		return _imtdRsContractDetailsFr.getOperation();
	}

	/**
	* Returns the primary key of this imtd rs contract details fr.
	*
	* @return the primary key of this imtd rs contract details fr
	*/
	@Override
	public int getPrimaryKey() {
		return _imtdRsContractDetailsFr.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _imtdRsContractDetailsFr.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this imtd rs contract details fr.
	*
	* @return the record lock status of this imtd rs contract details fr
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _imtdRsContractDetailsFr.getRecordLockStatus();
	}

	/**
	* Returns the rs contract details fr sid of this imtd rs contract details fr.
	*
	* @return the rs contract details fr sid of this imtd rs contract details fr
	*/
	@Override
	public int getRsContractDetailsFrSid() {
		return _imtdRsContractDetailsFr.getRsContractDetailsFrSid();
	}

	/**
	* Returns the rs contract details sid of this imtd rs contract details fr.
	*
	* @return the rs contract details sid of this imtd rs contract details fr
	*/
	@Override
	public int getRsContractDetailsSid() {
		return _imtdRsContractDetailsFr.getRsContractDetailsSid();
	}

	/**
	* Returns the session ID of this imtd rs contract details fr.
	*
	* @return the session ID of this imtd rs contract details fr
	*/
	@Override
	public java.lang.String getSessionId() {
		return _imtdRsContractDetailsFr.getSessionId();
	}

	/**
	* Returns the source of this imtd rs contract details fr.
	*
	* @return the source of this imtd rs contract details fr
	*/
	@Override
	public java.lang.String getSource() {
		return _imtdRsContractDetailsFr.getSource();
	}

	/**
	* Returns the users ID of this imtd rs contract details fr.
	*
	* @return the users ID of this imtd rs contract details fr
	*/
	@Override
	public java.lang.String getUsersId() {
		return _imtdRsContractDetailsFr.getUsersId();
	}

	@Override
	public int hashCode() {
		return _imtdRsContractDetailsFr.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _imtdRsContractDetailsFr.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _imtdRsContractDetailsFr.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _imtdRsContractDetailsFr.isNew();
	}

	/**
	* Returns <code>true</code> if this imtd rs contract details fr is record lock status.
	*
	* @return <code>true</code> if this imtd rs contract details fr is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _imtdRsContractDetailsFr.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_imtdRsContractDetailsFr.persist();
	}

	/**
	* Sets the batch ID of this imtd rs contract details fr.
	*
	* @param batchId the batch ID of this imtd rs contract details fr
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_imtdRsContractDetailsFr.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_imtdRsContractDetailsFr.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this imtd rs contract details fr.
	*
	* @param createdBy the created by of this imtd rs contract details fr
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_imtdRsContractDetailsFr.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this imtd rs contract details fr.
	*
	* @param createdDate the created date of this imtd rs contract details fr
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_imtdRsContractDetailsFr.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_imtdRsContractDetailsFr.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_imtdRsContractDetailsFr.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_imtdRsContractDetailsFr.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the formula ID of this imtd rs contract details fr.
	*
	* @param formulaId the formula ID of this imtd rs contract details fr
	*/
	@Override
	public void setFormulaId(int formulaId) {
		_imtdRsContractDetailsFr.setFormulaId(formulaId);
	}

	/**
	* Sets the formula method ID of this imtd rs contract details fr.
	*
	* @param formulaMethodId the formula method ID of this imtd rs contract details fr
	*/
	@Override
	public void setFormulaMethodId(java.lang.String formulaMethodId) {
		_imtdRsContractDetailsFr.setFormulaMethodId(formulaMethodId);
	}

	/**
	* Sets the imtd created date of this imtd rs contract details fr.
	*
	* @param imtdCreatedDate the imtd created date of this imtd rs contract details fr
	*/
	@Override
	public void setImtdCreatedDate(Date imtdCreatedDate) {
		_imtdRsContractDetailsFr.setImtdCreatedDate(imtdCreatedDate);
	}

	/**
	* Sets the imtd item price rebate details sid of this imtd rs contract details fr.
	*
	* @param imtdItemPriceRebateDetailsSid the imtd item price rebate details sid of this imtd rs contract details fr
	*/
	@Override
	public void setImtdItemPriceRebateDetailsSid(
		int imtdItemPriceRebateDetailsSid) {
		_imtdRsContractDetailsFr.setImtdItemPriceRebateDetailsSid(imtdItemPriceRebateDetailsSid);
	}

	/**
	* Sets the imtd rs contract details fr sid of this imtd rs contract details fr.
	*
	* @param imtdRsContractDetailsFrSid the imtd rs contract details fr sid of this imtd rs contract details fr
	*/
	@Override
	public void setImtdRsContractDetailsFrSid(int imtdRsContractDetailsFrSid) {
		_imtdRsContractDetailsFr.setImtdRsContractDetailsFrSid(imtdRsContractDetailsFrSid);
	}

	/**
	* Sets the inbound status of this imtd rs contract details fr.
	*
	* @param inboundStatus the inbound status of this imtd rs contract details fr
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_imtdRsContractDetailsFr.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item master sid of this imtd rs contract details fr.
	*
	* @param itemMasterSid the item master sid of this imtd rs contract details fr
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_imtdRsContractDetailsFr.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this imtd rs contract details fr.
	*
	* @param modifiedBy the modified by of this imtd rs contract details fr
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_imtdRsContractDetailsFr.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this imtd rs contract details fr.
	*
	* @param modifiedDate the modified date of this imtd rs contract details fr
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_imtdRsContractDetailsFr.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_imtdRsContractDetailsFr.setNew(n);
	}

	/**
	* Sets the operation of this imtd rs contract details fr.
	*
	* @param operation the operation of this imtd rs contract details fr
	*/
	@Override
	public void setOperation(java.lang.String operation) {
		_imtdRsContractDetailsFr.setOperation(operation);
	}

	/**
	* Sets the primary key of this imtd rs contract details fr.
	*
	* @param primaryKey the primary key of this imtd rs contract details fr
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_imtdRsContractDetailsFr.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_imtdRsContractDetailsFr.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this imtd rs contract details fr is record lock status.
	*
	* @param recordLockStatus the record lock status of this imtd rs contract details fr
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_imtdRsContractDetailsFr.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the rs contract details fr sid of this imtd rs contract details fr.
	*
	* @param rsContractDetailsFrSid the rs contract details fr sid of this imtd rs contract details fr
	*/
	@Override
	public void setRsContractDetailsFrSid(int rsContractDetailsFrSid) {
		_imtdRsContractDetailsFr.setRsContractDetailsFrSid(rsContractDetailsFrSid);
	}

	/**
	* Sets the rs contract details sid of this imtd rs contract details fr.
	*
	* @param rsContractDetailsSid the rs contract details sid of this imtd rs contract details fr
	*/
	@Override
	public void setRsContractDetailsSid(int rsContractDetailsSid) {
		_imtdRsContractDetailsFr.setRsContractDetailsSid(rsContractDetailsSid);
	}

	/**
	* Sets the session ID of this imtd rs contract details fr.
	*
	* @param sessionId the session ID of this imtd rs contract details fr
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_imtdRsContractDetailsFr.setSessionId(sessionId);
	}

	/**
	* Sets the source of this imtd rs contract details fr.
	*
	* @param source the source of this imtd rs contract details fr
	*/
	@Override
	public void setSource(java.lang.String source) {
		_imtdRsContractDetailsFr.setSource(source);
	}

	/**
	* Sets the users ID of this imtd rs contract details fr.
	*
	* @param usersId the users ID of this imtd rs contract details fr
	*/
	@Override
	public void setUsersId(java.lang.String usersId) {
		_imtdRsContractDetailsFr.setUsersId(usersId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ImtdRsContractDetailsFr> toCacheModel() {
		return _imtdRsContractDetailsFr.toCacheModel();
	}

	@Override
	public ImtdRsContractDetailsFr toEscapedModel() {
		return new ImtdRsContractDetailsFrWrapper(_imtdRsContractDetailsFr.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _imtdRsContractDetailsFr.toString();
	}

	@Override
	public ImtdRsContractDetailsFr toUnescapedModel() {
		return new ImtdRsContractDetailsFrWrapper(_imtdRsContractDetailsFr.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _imtdRsContractDetailsFr.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdRsContractDetailsFrWrapper)) {
			return false;
		}

		ImtdRsContractDetailsFrWrapper imtdRsContractDetailsFrWrapper = (ImtdRsContractDetailsFrWrapper)obj;

		if (Objects.equals(_imtdRsContractDetailsFr,
					imtdRsContractDetailsFrWrapper._imtdRsContractDetailsFr)) {
			return true;
		}

		return false;
	}

	@Override
	public ImtdRsContractDetailsFr getWrappedModel() {
		return _imtdRsContractDetailsFr;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _imtdRsContractDetailsFr.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _imtdRsContractDetailsFr.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_imtdRsContractDetailsFr.resetOriginalValues();
	}

	private final ImtdRsContractDetailsFr _imtdRsContractDetailsFr;
}