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
 * This class is a wrapper for {@link RsContractDetailsFr}.
 * </p>
 *
 * @author
 * @see RsContractDetailsFr
 * @generated
 */
@ProviderType
public class RsContractDetailsFrWrapper implements RsContractDetailsFr,
	ModelWrapper<RsContractDetailsFr> {
	public RsContractDetailsFrWrapper(RsContractDetailsFr rsContractDetailsFr) {
		_rsContractDetailsFr = rsContractDetailsFr;
	}

	@Override
	public Class<?> getModelClass() {
		return RsContractDetailsFr.class;
	}

	@Override
	public String getModelClassName() {
		return RsContractDetailsFr.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("formulaMethodId", getFormulaMethodId());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("batchId", getBatchId());
		attributes.put("rsContractDetailsFrSid", getRsContractDetailsFrSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("formulaId", getFormulaId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("rsContractDetailsSid", getRsContractDetailsSid());

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

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer rsContractDetailsFrSid = (Integer)attributes.get(
				"rsContractDetailsFrSid");

		if (rsContractDetailsFrSid != null) {
			setRsContractDetailsFrSid(rsContractDetailsFrSid);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer rsContractDetailsSid = (Integer)attributes.get(
				"rsContractDetailsSid");

		if (rsContractDetailsSid != null) {
			setRsContractDetailsSid(rsContractDetailsSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new RsContractDetailsFrWrapper((RsContractDetailsFr)_rsContractDetailsFr.clone());
	}

	@Override
	public int compareTo(RsContractDetailsFr rsContractDetailsFr) {
		return _rsContractDetailsFr.compareTo(rsContractDetailsFr);
	}

	/**
	* Returns the batch ID of this rs contract details fr.
	*
	* @return the batch ID of this rs contract details fr
	*/
	@Override
	public java.lang.String getBatchId() {
		return _rsContractDetailsFr.getBatchId();
	}

	/**
	* Returns the created by of this rs contract details fr.
	*
	* @return the created by of this rs contract details fr
	*/
	@Override
	public int getCreatedBy() {
		return _rsContractDetailsFr.getCreatedBy();
	}

	/**
	* Returns the created date of this rs contract details fr.
	*
	* @return the created date of this rs contract details fr
	*/
	@Override
	public Date getCreatedDate() {
		return _rsContractDetailsFr.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _rsContractDetailsFr.getExpandoBridge();
	}

	/**
	* Returns the formula ID of this rs contract details fr.
	*
	* @return the formula ID of this rs contract details fr
	*/
	@Override
	public int getFormulaId() {
		return _rsContractDetailsFr.getFormulaId();
	}

	/**
	* Returns the formula method ID of this rs contract details fr.
	*
	* @return the formula method ID of this rs contract details fr
	*/
	@Override
	public java.lang.String getFormulaMethodId() {
		return _rsContractDetailsFr.getFormulaMethodId();
	}

	/**
	* Returns the inbound status of this rs contract details fr.
	*
	* @return the inbound status of this rs contract details fr
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _rsContractDetailsFr.getInboundStatus();
	}

	/**
	* Returns the item master sid of this rs contract details fr.
	*
	* @return the item master sid of this rs contract details fr
	*/
	@Override
	public int getItemMasterSid() {
		return _rsContractDetailsFr.getItemMasterSid();
	}

	/**
	* Returns the modified by of this rs contract details fr.
	*
	* @return the modified by of this rs contract details fr
	*/
	@Override
	public int getModifiedBy() {
		return _rsContractDetailsFr.getModifiedBy();
	}

	/**
	* Returns the modified date of this rs contract details fr.
	*
	* @return the modified date of this rs contract details fr
	*/
	@Override
	public Date getModifiedDate() {
		return _rsContractDetailsFr.getModifiedDate();
	}

	/**
	* Returns the primary key of this rs contract details fr.
	*
	* @return the primary key of this rs contract details fr
	*/
	@Override
	public int getPrimaryKey() {
		return _rsContractDetailsFr.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _rsContractDetailsFr.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this rs contract details fr.
	*
	* @return the record lock status of this rs contract details fr
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _rsContractDetailsFr.getRecordLockStatus();
	}

	/**
	* Returns the rs contract details fr sid of this rs contract details fr.
	*
	* @return the rs contract details fr sid of this rs contract details fr
	*/
	@Override
	public int getRsContractDetailsFrSid() {
		return _rsContractDetailsFr.getRsContractDetailsFrSid();
	}

	/**
	* Returns the rs contract details sid of this rs contract details fr.
	*
	* @return the rs contract details sid of this rs contract details fr
	*/
	@Override
	public int getRsContractDetailsSid() {
		return _rsContractDetailsFr.getRsContractDetailsSid();
	}

	/**
	* Returns the source of this rs contract details fr.
	*
	* @return the source of this rs contract details fr
	*/
	@Override
	public java.lang.String getSource() {
		return _rsContractDetailsFr.getSource();
	}

	@Override
	public int hashCode() {
		return _rsContractDetailsFr.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _rsContractDetailsFr.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _rsContractDetailsFr.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _rsContractDetailsFr.isNew();
	}

	/**
	* Returns <code>true</code> if this rs contract details fr is record lock status.
	*
	* @return <code>true</code> if this rs contract details fr is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _rsContractDetailsFr.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_rsContractDetailsFr.persist();
	}

	/**
	* Sets the batch ID of this rs contract details fr.
	*
	* @param batchId the batch ID of this rs contract details fr
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_rsContractDetailsFr.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_rsContractDetailsFr.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this rs contract details fr.
	*
	* @param createdBy the created by of this rs contract details fr
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_rsContractDetailsFr.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this rs contract details fr.
	*
	* @param createdDate the created date of this rs contract details fr
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_rsContractDetailsFr.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_rsContractDetailsFr.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_rsContractDetailsFr.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_rsContractDetailsFr.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the formula ID of this rs contract details fr.
	*
	* @param formulaId the formula ID of this rs contract details fr
	*/
	@Override
	public void setFormulaId(int formulaId) {
		_rsContractDetailsFr.setFormulaId(formulaId);
	}

	/**
	* Sets the formula method ID of this rs contract details fr.
	*
	* @param formulaMethodId the formula method ID of this rs contract details fr
	*/
	@Override
	public void setFormulaMethodId(java.lang.String formulaMethodId) {
		_rsContractDetailsFr.setFormulaMethodId(formulaMethodId);
	}

	/**
	* Sets the inbound status of this rs contract details fr.
	*
	* @param inboundStatus the inbound status of this rs contract details fr
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_rsContractDetailsFr.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item master sid of this rs contract details fr.
	*
	* @param itemMasterSid the item master sid of this rs contract details fr
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_rsContractDetailsFr.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this rs contract details fr.
	*
	* @param modifiedBy the modified by of this rs contract details fr
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_rsContractDetailsFr.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this rs contract details fr.
	*
	* @param modifiedDate the modified date of this rs contract details fr
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_rsContractDetailsFr.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_rsContractDetailsFr.setNew(n);
	}

	/**
	* Sets the primary key of this rs contract details fr.
	*
	* @param primaryKey the primary key of this rs contract details fr
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_rsContractDetailsFr.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_rsContractDetailsFr.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this rs contract details fr is record lock status.
	*
	* @param recordLockStatus the record lock status of this rs contract details fr
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_rsContractDetailsFr.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the rs contract details fr sid of this rs contract details fr.
	*
	* @param rsContractDetailsFrSid the rs contract details fr sid of this rs contract details fr
	*/
	@Override
	public void setRsContractDetailsFrSid(int rsContractDetailsFrSid) {
		_rsContractDetailsFr.setRsContractDetailsFrSid(rsContractDetailsFrSid);
	}

	/**
	* Sets the rs contract details sid of this rs contract details fr.
	*
	* @param rsContractDetailsSid the rs contract details sid of this rs contract details fr
	*/
	@Override
	public void setRsContractDetailsSid(int rsContractDetailsSid) {
		_rsContractDetailsFr.setRsContractDetailsSid(rsContractDetailsSid);
	}

	/**
	* Sets the source of this rs contract details fr.
	*
	* @param source the source of this rs contract details fr
	*/
	@Override
	public void setSource(java.lang.String source) {
		_rsContractDetailsFr.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RsContractDetailsFr> toCacheModel() {
		return _rsContractDetailsFr.toCacheModel();
	}

	@Override
	public RsContractDetailsFr toEscapedModel() {
		return new RsContractDetailsFrWrapper(_rsContractDetailsFr.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _rsContractDetailsFr.toString();
	}

	@Override
	public RsContractDetailsFr toUnescapedModel() {
		return new RsContractDetailsFrWrapper(_rsContractDetailsFr.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _rsContractDetailsFr.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RsContractDetailsFrWrapper)) {
			return false;
		}

		RsContractDetailsFrWrapper rsContractDetailsFrWrapper = (RsContractDetailsFrWrapper)obj;

		if (Objects.equals(_rsContractDetailsFr,
					rsContractDetailsFrWrapper._rsContractDetailsFr)) {
			return true;
		}

		return false;
	}

	@Override
	public RsContractDetailsFr getWrappedModel() {
		return _rsContractDetailsFr;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _rsContractDetailsFr.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _rsContractDetailsFr.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_rsContractDetailsFr.resetOriginalValues();
	}

	private final RsContractDetailsFr _rsContractDetailsFr;
}