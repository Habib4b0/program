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
 * This class is a wrapper for {@link AverageShelfLifeMaster}.
 * </p>
 *
 * @author
 * @see AverageShelfLifeMaster
 * @generated
 */
@ProviderType
public class AverageShelfLifeMasterWrapper implements AverageShelfLifeMaster,
	ModelWrapper<AverageShelfLifeMaster> {
	public AverageShelfLifeMasterWrapper(
		AverageShelfLifeMaster averageShelfLifeMaster) {
		_averageShelfLifeMaster = averageShelfLifeMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return AverageShelfLifeMaster.class;
	}

	@Override
	public String getModelClassName() {
		return AverageShelfLifeMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("averageShelfLifeMasterSid",
			getAverageShelfLifeMasterSid());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("itemIdType", getItemIdType());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("itemId", getItemId());
		attributes.put("avgShelfLife", getAvgShelfLife());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer averageShelfLifeMasterSid = (Integer)attributes.get(
				"averageShelfLifeMasterSid");

		if (averageShelfLifeMasterSid != null) {
			setAverageShelfLifeMasterSid(averageShelfLifeMasterSid);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String itemIdType = (String)attributes.get("itemIdType");

		if (itemIdType != null) {
			setItemIdType(itemIdType);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String avgShelfLife = (String)attributes.get("avgShelfLife");

		if (avgShelfLife != null) {
			setAvgShelfLife(avgShelfLife);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AverageShelfLifeMasterWrapper((AverageShelfLifeMaster)_averageShelfLifeMaster.clone());
	}

	@Override
	public int compareTo(AverageShelfLifeMaster averageShelfLifeMaster) {
		return _averageShelfLifeMaster.compareTo(averageShelfLifeMaster);
	}

	/**
	* Returns the average shelf life master sid of this average shelf life master.
	*
	* @return the average shelf life master sid of this average shelf life master
	*/
	@Override
	public int getAverageShelfLifeMasterSid() {
		return _averageShelfLifeMaster.getAverageShelfLifeMasterSid();
	}

	/**
	* Returns the avg shelf life of this average shelf life master.
	*
	* @return the avg shelf life of this average shelf life master
	*/
	@Override
	public java.lang.String getAvgShelfLife() {
		return _averageShelfLifeMaster.getAvgShelfLife();
	}

	/**
	* Returns the batch ID of this average shelf life master.
	*
	* @return the batch ID of this average shelf life master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _averageShelfLifeMaster.getBatchId();
	}

	/**
	* Returns the created by of this average shelf life master.
	*
	* @return the created by of this average shelf life master
	*/
	@Override
	public int getCreatedBy() {
		return _averageShelfLifeMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this average shelf life master.
	*
	* @return the created date of this average shelf life master
	*/
	@Override
	public Date getCreatedDate() {
		return _averageShelfLifeMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _averageShelfLifeMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this average shelf life master.
	*
	* @return the inbound status of this average shelf life master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _averageShelfLifeMaster.getInboundStatus();
	}

	/**
	* Returns the item ID of this average shelf life master.
	*
	* @return the item ID of this average shelf life master
	*/
	@Override
	public java.lang.String getItemId() {
		return _averageShelfLifeMaster.getItemId();
	}

	/**
	* Returns the item ID type of this average shelf life master.
	*
	* @return the item ID type of this average shelf life master
	*/
	@Override
	public java.lang.String getItemIdType() {
		return _averageShelfLifeMaster.getItemIdType();
	}

	/**
	* Returns the modified by of this average shelf life master.
	*
	* @return the modified by of this average shelf life master
	*/
	@Override
	public int getModifiedBy() {
		return _averageShelfLifeMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this average shelf life master.
	*
	* @return the modified date of this average shelf life master
	*/
	@Override
	public Date getModifiedDate() {
		return _averageShelfLifeMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this average shelf life master.
	*
	* @return the primary key of this average shelf life master
	*/
	@Override
	public int getPrimaryKey() {
		return _averageShelfLifeMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _averageShelfLifeMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this average shelf life master.
	*
	* @return the record lock status of this average shelf life master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _averageShelfLifeMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this average shelf life master.
	*
	* @return the source of this average shelf life master
	*/
	@Override
	public java.lang.String getSource() {
		return _averageShelfLifeMaster.getSource();
	}

	@Override
	public int hashCode() {
		return _averageShelfLifeMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _averageShelfLifeMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _averageShelfLifeMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _averageShelfLifeMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this average shelf life master is record lock status.
	*
	* @return <code>true</code> if this average shelf life master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _averageShelfLifeMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_averageShelfLifeMaster.persist();
	}

	/**
	* Sets the average shelf life master sid of this average shelf life master.
	*
	* @param averageShelfLifeMasterSid the average shelf life master sid of this average shelf life master
	*/
	@Override
	public void setAverageShelfLifeMasterSid(int averageShelfLifeMasterSid) {
		_averageShelfLifeMaster.setAverageShelfLifeMasterSid(averageShelfLifeMasterSid);
	}

	/**
	* Sets the avg shelf life of this average shelf life master.
	*
	* @param avgShelfLife the avg shelf life of this average shelf life master
	*/
	@Override
	public void setAvgShelfLife(java.lang.String avgShelfLife) {
		_averageShelfLifeMaster.setAvgShelfLife(avgShelfLife);
	}

	/**
	* Sets the batch ID of this average shelf life master.
	*
	* @param batchId the batch ID of this average shelf life master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_averageShelfLifeMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_averageShelfLifeMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this average shelf life master.
	*
	* @param createdBy the created by of this average shelf life master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_averageShelfLifeMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this average shelf life master.
	*
	* @param createdDate the created date of this average shelf life master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_averageShelfLifeMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_averageShelfLifeMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_averageShelfLifeMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_averageShelfLifeMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this average shelf life master.
	*
	* @param inboundStatus the inbound status of this average shelf life master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_averageShelfLifeMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item ID of this average shelf life master.
	*
	* @param itemId the item ID of this average shelf life master
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_averageShelfLifeMaster.setItemId(itemId);
	}

	/**
	* Sets the item ID type of this average shelf life master.
	*
	* @param itemIdType the item ID type of this average shelf life master
	*/
	@Override
	public void setItemIdType(java.lang.String itemIdType) {
		_averageShelfLifeMaster.setItemIdType(itemIdType);
	}

	/**
	* Sets the modified by of this average shelf life master.
	*
	* @param modifiedBy the modified by of this average shelf life master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_averageShelfLifeMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this average shelf life master.
	*
	* @param modifiedDate the modified date of this average shelf life master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_averageShelfLifeMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_averageShelfLifeMaster.setNew(n);
	}

	/**
	* Sets the primary key of this average shelf life master.
	*
	* @param primaryKey the primary key of this average shelf life master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_averageShelfLifeMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_averageShelfLifeMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this average shelf life master is record lock status.
	*
	* @param recordLockStatus the record lock status of this average shelf life master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_averageShelfLifeMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this average shelf life master.
	*
	* @param source the source of this average shelf life master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_averageShelfLifeMaster.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AverageShelfLifeMaster> toCacheModel() {
		return _averageShelfLifeMaster.toCacheModel();
	}

	@Override
	public AverageShelfLifeMaster toEscapedModel() {
		return new AverageShelfLifeMasterWrapper(_averageShelfLifeMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _averageShelfLifeMaster.toString();
	}

	@Override
	public AverageShelfLifeMaster toUnescapedModel() {
		return new AverageShelfLifeMasterWrapper(_averageShelfLifeMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _averageShelfLifeMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AverageShelfLifeMasterWrapper)) {
			return false;
		}

		AverageShelfLifeMasterWrapper averageShelfLifeMasterWrapper = (AverageShelfLifeMasterWrapper)obj;

		if (Objects.equals(_averageShelfLifeMaster,
					averageShelfLifeMasterWrapper._averageShelfLifeMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public AverageShelfLifeMaster getWrappedModel() {
		return _averageShelfLifeMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _averageShelfLifeMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _averageShelfLifeMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_averageShelfLifeMaster.resetOriginalValues();
	}

	private final AverageShelfLifeMaster _averageShelfLifeMaster;
}