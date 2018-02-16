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
 * This class is a wrapper for {@link ImtdRsDetailsFr}.
 * </p>
 *
 * @author
 * @see ImtdRsDetailsFr
 * @generated
 */
@ProviderType
public class ImtdRsDetailsFrWrapper implements ImtdRsDetailsFr,
	ModelWrapper<ImtdRsDetailsFr> {
	public ImtdRsDetailsFrWrapper(ImtdRsDetailsFr imtdRsDetailsFr) {
		_imtdRsDetailsFr = imtdRsDetailsFr;
	}

	@Override
	public Class<?> getModelClass() {
		return ImtdRsDetailsFr.class;
	}

	@Override
	public String getModelClassName() {
		return ImtdRsDetailsFr.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("formulaMethodId", getFormulaMethodId());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("imtdRsDetailsSid", getImtdRsDetailsSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("rsDetailsFrSid", getRsDetailsFrSid());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("imtdRsDetailsFrSid", getImtdRsDetailsFrSid());
		attributes.put("batchId", getBatchId());
		attributes.put("imtdCreatedDate", getImtdCreatedDate());
		attributes.put("sessionId", getSessionId());
		attributes.put("usersId", getUsersId());
		attributes.put("operation", getOperation());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("rsDetailsSid", getRsDetailsSid());
		attributes.put("formulaId", getFormulaId());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String formulaMethodId = (String)attributes.get("formulaMethodId");

		if (formulaMethodId != null) {
			setFormulaMethodId(formulaMethodId);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer imtdRsDetailsSid = (Integer)attributes.get("imtdRsDetailsSid");

		if (imtdRsDetailsSid != null) {
			setImtdRsDetailsSid(imtdRsDetailsSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer rsDetailsFrSid = (Integer)attributes.get("rsDetailsFrSid");

		if (rsDetailsFrSid != null) {
			setRsDetailsFrSid(rsDetailsFrSid);
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

		Integer imtdRsDetailsFrSid = (Integer)attributes.get(
				"imtdRsDetailsFrSid");

		if (imtdRsDetailsFrSid != null) {
			setImtdRsDetailsFrSid(imtdRsDetailsFrSid);
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

		Integer rsDetailsSid = (Integer)attributes.get("rsDetailsSid");

		if (rsDetailsSid != null) {
			setRsDetailsSid(rsDetailsSid);
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
		return new ImtdRsDetailsFrWrapper((ImtdRsDetailsFr)_imtdRsDetailsFr.clone());
	}

	@Override
	public int compareTo(ImtdRsDetailsFr imtdRsDetailsFr) {
		return _imtdRsDetailsFr.compareTo(imtdRsDetailsFr);
	}

	/**
	* Returns the batch ID of this imtd rs details fr.
	*
	* @return the batch ID of this imtd rs details fr
	*/
	@Override
	public java.lang.String getBatchId() {
		return _imtdRsDetailsFr.getBatchId();
	}

	/**
	* Returns the created by of this imtd rs details fr.
	*
	* @return the created by of this imtd rs details fr
	*/
	@Override
	public int getCreatedBy() {
		return _imtdRsDetailsFr.getCreatedBy();
	}

	/**
	* Returns the created date of this imtd rs details fr.
	*
	* @return the created date of this imtd rs details fr
	*/
	@Override
	public Date getCreatedDate() {
		return _imtdRsDetailsFr.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _imtdRsDetailsFr.getExpandoBridge();
	}

	/**
	* Returns the formula ID of this imtd rs details fr.
	*
	* @return the formula ID of this imtd rs details fr
	*/
	@Override
	public int getFormulaId() {
		return _imtdRsDetailsFr.getFormulaId();
	}

	/**
	* Returns the formula method ID of this imtd rs details fr.
	*
	* @return the formula method ID of this imtd rs details fr
	*/
	@Override
	public java.lang.String getFormulaMethodId() {
		return _imtdRsDetailsFr.getFormulaMethodId();
	}

	/**
	* Returns the imtd created date of this imtd rs details fr.
	*
	* @return the imtd created date of this imtd rs details fr
	*/
	@Override
	public Date getImtdCreatedDate() {
		return _imtdRsDetailsFr.getImtdCreatedDate();
	}

	/**
	* Returns the imtd rs details fr sid of this imtd rs details fr.
	*
	* @return the imtd rs details fr sid of this imtd rs details fr
	*/
	@Override
	public int getImtdRsDetailsFrSid() {
		return _imtdRsDetailsFr.getImtdRsDetailsFrSid();
	}

	/**
	* Returns the imtd rs details sid of this imtd rs details fr.
	*
	* @return the imtd rs details sid of this imtd rs details fr
	*/
	@Override
	public int getImtdRsDetailsSid() {
		return _imtdRsDetailsFr.getImtdRsDetailsSid();
	}

	/**
	* Returns the inbound status of this imtd rs details fr.
	*
	* @return the inbound status of this imtd rs details fr
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _imtdRsDetailsFr.getInboundStatus();
	}

	/**
	* Returns the item master sid of this imtd rs details fr.
	*
	* @return the item master sid of this imtd rs details fr
	*/
	@Override
	public int getItemMasterSid() {
		return _imtdRsDetailsFr.getItemMasterSid();
	}

	/**
	* Returns the modified by of this imtd rs details fr.
	*
	* @return the modified by of this imtd rs details fr
	*/
	@Override
	public int getModifiedBy() {
		return _imtdRsDetailsFr.getModifiedBy();
	}

	/**
	* Returns the modified date of this imtd rs details fr.
	*
	* @return the modified date of this imtd rs details fr
	*/
	@Override
	public Date getModifiedDate() {
		return _imtdRsDetailsFr.getModifiedDate();
	}

	/**
	* Returns the operation of this imtd rs details fr.
	*
	* @return the operation of this imtd rs details fr
	*/
	@Override
	public java.lang.String getOperation() {
		return _imtdRsDetailsFr.getOperation();
	}

	/**
	* Returns the primary key of this imtd rs details fr.
	*
	* @return the primary key of this imtd rs details fr
	*/
	@Override
	public int getPrimaryKey() {
		return _imtdRsDetailsFr.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _imtdRsDetailsFr.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this imtd rs details fr.
	*
	* @return the record lock status of this imtd rs details fr
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _imtdRsDetailsFr.getRecordLockStatus();
	}

	/**
	* Returns the rs details fr sid of this imtd rs details fr.
	*
	* @return the rs details fr sid of this imtd rs details fr
	*/
	@Override
	public int getRsDetailsFrSid() {
		return _imtdRsDetailsFr.getRsDetailsFrSid();
	}

	/**
	* Returns the rs details sid of this imtd rs details fr.
	*
	* @return the rs details sid of this imtd rs details fr
	*/
	@Override
	public int getRsDetailsSid() {
		return _imtdRsDetailsFr.getRsDetailsSid();
	}

	/**
	* Returns the session ID of this imtd rs details fr.
	*
	* @return the session ID of this imtd rs details fr
	*/
	@Override
	public java.lang.String getSessionId() {
		return _imtdRsDetailsFr.getSessionId();
	}

	/**
	* Returns the source of this imtd rs details fr.
	*
	* @return the source of this imtd rs details fr
	*/
	@Override
	public java.lang.String getSource() {
		return _imtdRsDetailsFr.getSource();
	}

	/**
	* Returns the users ID of this imtd rs details fr.
	*
	* @return the users ID of this imtd rs details fr
	*/
	@Override
	public java.lang.String getUsersId() {
		return _imtdRsDetailsFr.getUsersId();
	}

	@Override
	public int hashCode() {
		return _imtdRsDetailsFr.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _imtdRsDetailsFr.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _imtdRsDetailsFr.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _imtdRsDetailsFr.isNew();
	}

	/**
	* Returns <code>true</code> if this imtd rs details fr is record lock status.
	*
	* @return <code>true</code> if this imtd rs details fr is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _imtdRsDetailsFr.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_imtdRsDetailsFr.persist();
	}

	/**
	* Sets the batch ID of this imtd rs details fr.
	*
	* @param batchId the batch ID of this imtd rs details fr
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_imtdRsDetailsFr.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_imtdRsDetailsFr.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this imtd rs details fr.
	*
	* @param createdBy the created by of this imtd rs details fr
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_imtdRsDetailsFr.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this imtd rs details fr.
	*
	* @param createdDate the created date of this imtd rs details fr
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_imtdRsDetailsFr.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_imtdRsDetailsFr.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_imtdRsDetailsFr.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_imtdRsDetailsFr.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the formula ID of this imtd rs details fr.
	*
	* @param formulaId the formula ID of this imtd rs details fr
	*/
	@Override
	public void setFormulaId(int formulaId) {
		_imtdRsDetailsFr.setFormulaId(formulaId);
	}

	/**
	* Sets the formula method ID of this imtd rs details fr.
	*
	* @param formulaMethodId the formula method ID of this imtd rs details fr
	*/
	@Override
	public void setFormulaMethodId(java.lang.String formulaMethodId) {
		_imtdRsDetailsFr.setFormulaMethodId(formulaMethodId);
	}

	/**
	* Sets the imtd created date of this imtd rs details fr.
	*
	* @param imtdCreatedDate the imtd created date of this imtd rs details fr
	*/
	@Override
	public void setImtdCreatedDate(Date imtdCreatedDate) {
		_imtdRsDetailsFr.setImtdCreatedDate(imtdCreatedDate);
	}

	/**
	* Sets the imtd rs details fr sid of this imtd rs details fr.
	*
	* @param imtdRsDetailsFrSid the imtd rs details fr sid of this imtd rs details fr
	*/
	@Override
	public void setImtdRsDetailsFrSid(int imtdRsDetailsFrSid) {
		_imtdRsDetailsFr.setImtdRsDetailsFrSid(imtdRsDetailsFrSid);
	}

	/**
	* Sets the imtd rs details sid of this imtd rs details fr.
	*
	* @param imtdRsDetailsSid the imtd rs details sid of this imtd rs details fr
	*/
	@Override
	public void setImtdRsDetailsSid(int imtdRsDetailsSid) {
		_imtdRsDetailsFr.setImtdRsDetailsSid(imtdRsDetailsSid);
	}

	/**
	* Sets the inbound status of this imtd rs details fr.
	*
	* @param inboundStatus the inbound status of this imtd rs details fr
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_imtdRsDetailsFr.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item master sid of this imtd rs details fr.
	*
	* @param itemMasterSid the item master sid of this imtd rs details fr
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_imtdRsDetailsFr.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this imtd rs details fr.
	*
	* @param modifiedBy the modified by of this imtd rs details fr
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_imtdRsDetailsFr.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this imtd rs details fr.
	*
	* @param modifiedDate the modified date of this imtd rs details fr
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_imtdRsDetailsFr.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_imtdRsDetailsFr.setNew(n);
	}

	/**
	* Sets the operation of this imtd rs details fr.
	*
	* @param operation the operation of this imtd rs details fr
	*/
	@Override
	public void setOperation(java.lang.String operation) {
		_imtdRsDetailsFr.setOperation(operation);
	}

	/**
	* Sets the primary key of this imtd rs details fr.
	*
	* @param primaryKey the primary key of this imtd rs details fr
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_imtdRsDetailsFr.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_imtdRsDetailsFr.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this imtd rs details fr is record lock status.
	*
	* @param recordLockStatus the record lock status of this imtd rs details fr
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_imtdRsDetailsFr.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the rs details fr sid of this imtd rs details fr.
	*
	* @param rsDetailsFrSid the rs details fr sid of this imtd rs details fr
	*/
	@Override
	public void setRsDetailsFrSid(int rsDetailsFrSid) {
		_imtdRsDetailsFr.setRsDetailsFrSid(rsDetailsFrSid);
	}

	/**
	* Sets the rs details sid of this imtd rs details fr.
	*
	* @param rsDetailsSid the rs details sid of this imtd rs details fr
	*/
	@Override
	public void setRsDetailsSid(int rsDetailsSid) {
		_imtdRsDetailsFr.setRsDetailsSid(rsDetailsSid);
	}

	/**
	* Sets the session ID of this imtd rs details fr.
	*
	* @param sessionId the session ID of this imtd rs details fr
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_imtdRsDetailsFr.setSessionId(sessionId);
	}

	/**
	* Sets the source of this imtd rs details fr.
	*
	* @param source the source of this imtd rs details fr
	*/
	@Override
	public void setSource(java.lang.String source) {
		_imtdRsDetailsFr.setSource(source);
	}

	/**
	* Sets the users ID of this imtd rs details fr.
	*
	* @param usersId the users ID of this imtd rs details fr
	*/
	@Override
	public void setUsersId(java.lang.String usersId) {
		_imtdRsDetailsFr.setUsersId(usersId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ImtdRsDetailsFr> toCacheModel() {
		return _imtdRsDetailsFr.toCacheModel();
	}

	@Override
	public ImtdRsDetailsFr toEscapedModel() {
		return new ImtdRsDetailsFrWrapper(_imtdRsDetailsFr.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _imtdRsDetailsFr.toString();
	}

	@Override
	public ImtdRsDetailsFr toUnescapedModel() {
		return new ImtdRsDetailsFrWrapper(_imtdRsDetailsFr.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _imtdRsDetailsFr.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdRsDetailsFrWrapper)) {
			return false;
		}

		ImtdRsDetailsFrWrapper imtdRsDetailsFrWrapper = (ImtdRsDetailsFrWrapper)obj;

		if (Objects.equals(_imtdRsDetailsFr,
					imtdRsDetailsFrWrapper._imtdRsDetailsFr)) {
			return true;
		}

		return false;
	}

	@Override
	public ImtdRsDetailsFr getWrappedModel() {
		return _imtdRsDetailsFr;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _imtdRsDetailsFr.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _imtdRsDetailsFr.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_imtdRsDetailsFr.resetOriginalValues();
	}

	private final ImtdRsDetailsFr _imtdRsDetailsFr;
}