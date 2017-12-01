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
 * This class is a wrapper for {@link RsDetailsFr}.
 * </p>
 *
 * @author
 * @see RsDetailsFr
 * @generated
 */
@ProviderType
public class RsDetailsFrWrapper implements RsDetailsFr,
	ModelWrapper<RsDetailsFr> {
	public RsDetailsFrWrapper(RsDetailsFr rsDetailsFr) {
		_rsDetailsFr = rsDetailsFr;
	}

	@Override
	public Class<?> getModelClass() {
		return RsDetailsFr.class;
	}

	@Override
	public String getModelClassName() {
		return RsDetailsFr.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("formulaMethodId", getFormulaMethodId());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("formulaId", getFormulaId());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("rsDetailsSid", getRsDetailsSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("rsDetailsFrSid", getRsDetailsFrSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
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

		String formulaMethodId = (String)attributes.get("formulaMethodId");

		if (formulaMethodId != null) {
			setFormulaMethodId(formulaMethodId);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer formulaId = (Integer)attributes.get("formulaId");

		if (formulaId != null) {
			setFormulaId(formulaId);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer rsDetailsSid = (Integer)attributes.get("rsDetailsSid");

		if (rsDetailsSid != null) {
			setRsDetailsSid(rsDetailsSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer rsDetailsFrSid = (Integer)attributes.get("rsDetailsFrSid");

		if (rsDetailsFrSid != null) {
			setRsDetailsFrSid(rsDetailsFrSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new RsDetailsFrWrapper((RsDetailsFr)_rsDetailsFr.clone());
	}

	@Override
	public int compareTo(RsDetailsFr rsDetailsFr) {
		return _rsDetailsFr.compareTo(rsDetailsFr);
	}

	/**
	* Returns the batch ID of this rs details fr.
	*
	* @return the batch ID of this rs details fr
	*/
	@Override
	public java.lang.String getBatchId() {
		return _rsDetailsFr.getBatchId();
	}

	/**
	* Returns the created by of this rs details fr.
	*
	* @return the created by of this rs details fr
	*/
	@Override
	public int getCreatedBy() {
		return _rsDetailsFr.getCreatedBy();
	}

	/**
	* Returns the created date of this rs details fr.
	*
	* @return the created date of this rs details fr
	*/
	@Override
	public Date getCreatedDate() {
		return _rsDetailsFr.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _rsDetailsFr.getExpandoBridge();
	}

	/**
	* Returns the formula ID of this rs details fr.
	*
	* @return the formula ID of this rs details fr
	*/
	@Override
	public int getFormulaId() {
		return _rsDetailsFr.getFormulaId();
	}

	/**
	* Returns the formula method ID of this rs details fr.
	*
	* @return the formula method ID of this rs details fr
	*/
	@Override
	public java.lang.String getFormulaMethodId() {
		return _rsDetailsFr.getFormulaMethodId();
	}

	/**
	* Returns the inbound status of this rs details fr.
	*
	* @return the inbound status of this rs details fr
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _rsDetailsFr.getInboundStatus();
	}

	/**
	* Returns the item master sid of this rs details fr.
	*
	* @return the item master sid of this rs details fr
	*/
	@Override
	public int getItemMasterSid() {
		return _rsDetailsFr.getItemMasterSid();
	}

	/**
	* Returns the modified by of this rs details fr.
	*
	* @return the modified by of this rs details fr
	*/
	@Override
	public int getModifiedBy() {
		return _rsDetailsFr.getModifiedBy();
	}

	/**
	* Returns the modified date of this rs details fr.
	*
	* @return the modified date of this rs details fr
	*/
	@Override
	public Date getModifiedDate() {
		return _rsDetailsFr.getModifiedDate();
	}

	/**
	* Returns the primary key of this rs details fr.
	*
	* @return the primary key of this rs details fr
	*/
	@Override
	public int getPrimaryKey() {
		return _rsDetailsFr.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _rsDetailsFr.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this rs details fr.
	*
	* @return the record lock status of this rs details fr
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _rsDetailsFr.getRecordLockStatus();
	}

	/**
	* Returns the rs details fr sid of this rs details fr.
	*
	* @return the rs details fr sid of this rs details fr
	*/
	@Override
	public int getRsDetailsFrSid() {
		return _rsDetailsFr.getRsDetailsFrSid();
	}

	/**
	* Returns the rs details sid of this rs details fr.
	*
	* @return the rs details sid of this rs details fr
	*/
	@Override
	public int getRsDetailsSid() {
		return _rsDetailsFr.getRsDetailsSid();
	}

	/**
	* Returns the source of this rs details fr.
	*
	* @return the source of this rs details fr
	*/
	@Override
	public java.lang.String getSource() {
		return _rsDetailsFr.getSource();
	}

	@Override
	public int hashCode() {
		return _rsDetailsFr.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _rsDetailsFr.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _rsDetailsFr.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _rsDetailsFr.isNew();
	}

	/**
	* Returns <code>true</code> if this rs details fr is record lock status.
	*
	* @return <code>true</code> if this rs details fr is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _rsDetailsFr.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_rsDetailsFr.persist();
	}

	/**
	* Sets the batch ID of this rs details fr.
	*
	* @param batchId the batch ID of this rs details fr
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_rsDetailsFr.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_rsDetailsFr.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this rs details fr.
	*
	* @param createdBy the created by of this rs details fr
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_rsDetailsFr.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this rs details fr.
	*
	* @param createdDate the created date of this rs details fr
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_rsDetailsFr.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_rsDetailsFr.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_rsDetailsFr.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_rsDetailsFr.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the formula ID of this rs details fr.
	*
	* @param formulaId the formula ID of this rs details fr
	*/
	@Override
	public void setFormulaId(int formulaId) {
		_rsDetailsFr.setFormulaId(formulaId);
	}

	/**
	* Sets the formula method ID of this rs details fr.
	*
	* @param formulaMethodId the formula method ID of this rs details fr
	*/
	@Override
	public void setFormulaMethodId(java.lang.String formulaMethodId) {
		_rsDetailsFr.setFormulaMethodId(formulaMethodId);
	}

	/**
	* Sets the inbound status of this rs details fr.
	*
	* @param inboundStatus the inbound status of this rs details fr
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_rsDetailsFr.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item master sid of this rs details fr.
	*
	* @param itemMasterSid the item master sid of this rs details fr
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_rsDetailsFr.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this rs details fr.
	*
	* @param modifiedBy the modified by of this rs details fr
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_rsDetailsFr.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this rs details fr.
	*
	* @param modifiedDate the modified date of this rs details fr
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_rsDetailsFr.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_rsDetailsFr.setNew(n);
	}

	/**
	* Sets the primary key of this rs details fr.
	*
	* @param primaryKey the primary key of this rs details fr
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_rsDetailsFr.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_rsDetailsFr.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this rs details fr is record lock status.
	*
	* @param recordLockStatus the record lock status of this rs details fr
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_rsDetailsFr.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the rs details fr sid of this rs details fr.
	*
	* @param rsDetailsFrSid the rs details fr sid of this rs details fr
	*/
	@Override
	public void setRsDetailsFrSid(int rsDetailsFrSid) {
		_rsDetailsFr.setRsDetailsFrSid(rsDetailsFrSid);
	}

	/**
	* Sets the rs details sid of this rs details fr.
	*
	* @param rsDetailsSid the rs details sid of this rs details fr
	*/
	@Override
	public void setRsDetailsSid(int rsDetailsSid) {
		_rsDetailsFr.setRsDetailsSid(rsDetailsSid);
	}

	/**
	* Sets the source of this rs details fr.
	*
	* @param source the source of this rs details fr
	*/
	@Override
	public void setSource(java.lang.String source) {
		_rsDetailsFr.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RsDetailsFr> toCacheModel() {
		return _rsDetailsFr.toCacheModel();
	}

	@Override
	public RsDetailsFr toEscapedModel() {
		return new RsDetailsFrWrapper(_rsDetailsFr.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _rsDetailsFr.toString();
	}

	@Override
	public RsDetailsFr toUnescapedModel() {
		return new RsDetailsFrWrapper(_rsDetailsFr.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _rsDetailsFr.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RsDetailsFrWrapper)) {
			return false;
		}

		RsDetailsFrWrapper rsDetailsFrWrapper = (RsDetailsFrWrapper)obj;

		if (Objects.equals(_rsDetailsFr, rsDetailsFrWrapper._rsDetailsFr)) {
			return true;
		}

		return false;
	}

	@Override
	public RsDetailsFr getWrappedModel() {
		return _rsDetailsFr;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _rsDetailsFr.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _rsDetailsFr.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_rsDetailsFr.resetOriginalValues();
	}

	private final RsDetailsFr _rsDetailsFr;
}