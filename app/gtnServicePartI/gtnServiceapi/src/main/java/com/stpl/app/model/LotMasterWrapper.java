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
 * This class is a wrapper for {@link LotMaster}.
 * </p>
 *
 * @author
 * @see LotMaster
 * @generated
 */
@ProviderType
public class LotMasterWrapper implements LotMaster, ModelWrapper<LotMaster> {
	public LotMasterWrapper(LotMaster lotMaster) {
		_lotMaster = lotMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return LotMaster.class;
	}

	@Override
	public String getModelClassName() {
		return LotMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("itemId", getItemId());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("lastLotSoldDate", getLastLotSoldDate());
		attributes.put("lotExpirationDate", getLotExpirationDate());
		attributes.put("source", getSource());
		attributes.put("lotMasterSid", getLotMasterSid());
		attributes.put("lotNo", getLotNo());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
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

		Date lastLotSoldDate = (Date)attributes.get("lastLotSoldDate");

		if (lastLotSoldDate != null) {
			setLastLotSoldDate(lastLotSoldDate);
		}

		Date lotExpirationDate = (Date)attributes.get("lotExpirationDate");

		if (lotExpirationDate != null) {
			setLotExpirationDate(lotExpirationDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer lotMasterSid = (Integer)attributes.get("lotMasterSid");

		if (lotMasterSid != null) {
			setLotMasterSid(lotMasterSid);
		}

		String lotNo = (String)attributes.get("lotNo");

		if (lotNo != null) {
			setLotNo(lotNo);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new LotMasterWrapper((LotMaster)_lotMaster.clone());
	}

	@Override
	public int compareTo(LotMaster lotMaster) {
		return _lotMaster.compareTo(lotMaster);
	}

	/**
	* Returns the batch ID of this lot master.
	*
	* @return the batch ID of this lot master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _lotMaster.getBatchId();
	}

	/**
	* Returns the created by of this lot master.
	*
	* @return the created by of this lot master
	*/
	@Override
	public int getCreatedBy() {
		return _lotMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this lot master.
	*
	* @return the created date of this lot master
	*/
	@Override
	public Date getCreatedDate() {
		return _lotMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lotMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this lot master.
	*
	* @return the inbound status of this lot master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _lotMaster.getInboundStatus();
	}

	/**
	* Returns the item ID of this lot master.
	*
	* @return the item ID of this lot master
	*/
	@Override
	public java.lang.String getItemId() {
		return _lotMaster.getItemId();
	}

	/**
	* Returns the last lot sold date of this lot master.
	*
	* @return the last lot sold date of this lot master
	*/
	@Override
	public Date getLastLotSoldDate() {
		return _lotMaster.getLastLotSoldDate();
	}

	/**
	* Returns the lot expiration date of this lot master.
	*
	* @return the lot expiration date of this lot master
	*/
	@Override
	public Date getLotExpirationDate() {
		return _lotMaster.getLotExpirationDate();
	}

	/**
	* Returns the lot master sid of this lot master.
	*
	* @return the lot master sid of this lot master
	*/
	@Override
	public int getLotMasterSid() {
		return _lotMaster.getLotMasterSid();
	}

	/**
	* Returns the lot no of this lot master.
	*
	* @return the lot no of this lot master
	*/
	@Override
	public java.lang.String getLotNo() {
		return _lotMaster.getLotNo();
	}

	/**
	* Returns the modified by of this lot master.
	*
	* @return the modified by of this lot master
	*/
	@Override
	public int getModifiedBy() {
		return _lotMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this lot master.
	*
	* @return the modified date of this lot master
	*/
	@Override
	public Date getModifiedDate() {
		return _lotMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this lot master.
	*
	* @return the primary key of this lot master
	*/
	@Override
	public int getPrimaryKey() {
		return _lotMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lotMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this lot master.
	*
	* @return the record lock status of this lot master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _lotMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this lot master.
	*
	* @return the source of this lot master
	*/
	@Override
	public java.lang.String getSource() {
		return _lotMaster.getSource();
	}

	@Override
	public int hashCode() {
		return _lotMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _lotMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lotMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lotMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this lot master is record lock status.
	*
	* @return <code>true</code> if this lot master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _lotMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_lotMaster.persist();
	}

	/**
	* Sets the batch ID of this lot master.
	*
	* @param batchId the batch ID of this lot master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_lotMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lotMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this lot master.
	*
	* @param createdBy the created by of this lot master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_lotMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this lot master.
	*
	* @param createdDate the created date of this lot master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_lotMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lotMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lotMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lotMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this lot master.
	*
	* @param inboundStatus the inbound status of this lot master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_lotMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item ID of this lot master.
	*
	* @param itemId the item ID of this lot master
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_lotMaster.setItemId(itemId);
	}

	/**
	* Sets the last lot sold date of this lot master.
	*
	* @param lastLotSoldDate the last lot sold date of this lot master
	*/
	@Override
	public void setLastLotSoldDate(Date lastLotSoldDate) {
		_lotMaster.setLastLotSoldDate(lastLotSoldDate);
	}

	/**
	* Sets the lot expiration date of this lot master.
	*
	* @param lotExpirationDate the lot expiration date of this lot master
	*/
	@Override
	public void setLotExpirationDate(Date lotExpirationDate) {
		_lotMaster.setLotExpirationDate(lotExpirationDate);
	}

	/**
	* Sets the lot master sid of this lot master.
	*
	* @param lotMasterSid the lot master sid of this lot master
	*/
	@Override
	public void setLotMasterSid(int lotMasterSid) {
		_lotMaster.setLotMasterSid(lotMasterSid);
	}

	/**
	* Sets the lot no of this lot master.
	*
	* @param lotNo the lot no of this lot master
	*/
	@Override
	public void setLotNo(java.lang.String lotNo) {
		_lotMaster.setLotNo(lotNo);
	}

	/**
	* Sets the modified by of this lot master.
	*
	* @param modifiedBy the modified by of this lot master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_lotMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this lot master.
	*
	* @param modifiedDate the modified date of this lot master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_lotMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_lotMaster.setNew(n);
	}

	/**
	* Sets the primary key of this lot master.
	*
	* @param primaryKey the primary key of this lot master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_lotMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lotMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this lot master is record lock status.
	*
	* @param recordLockStatus the record lock status of this lot master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_lotMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this lot master.
	*
	* @param source the source of this lot master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_lotMaster.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LotMaster> toCacheModel() {
		return _lotMaster.toCacheModel();
	}

	@Override
	public LotMaster toEscapedModel() {
		return new LotMasterWrapper(_lotMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _lotMaster.toString();
	}

	@Override
	public LotMaster toUnescapedModel() {
		return new LotMasterWrapper(_lotMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _lotMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LotMasterWrapper)) {
			return false;
		}

		LotMasterWrapper lotMasterWrapper = (LotMasterWrapper)obj;

		if (Objects.equals(_lotMaster, lotMasterWrapper._lotMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public LotMaster getWrappedModel() {
		return _lotMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lotMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lotMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lotMaster.resetOriginalValues();
	}

	private final LotMaster _lotMaster;
}