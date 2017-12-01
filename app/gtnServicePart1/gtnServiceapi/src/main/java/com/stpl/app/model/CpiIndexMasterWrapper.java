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
 * This class is a wrapper for {@link CpiIndexMaster}.
 * </p>
 *
 * @author
 * @see CpiIndexMaster
 * @generated
 */
@ProviderType
public class CpiIndexMasterWrapper implements CpiIndexMaster,
	ModelWrapper<CpiIndexMaster> {
	public CpiIndexMasterWrapper(CpiIndexMaster cpiIndexMaster) {
		_cpiIndexMaster = cpiIndexMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return CpiIndexMaster.class;
	}

	@Override
	public String getModelClassName() {
		return CpiIndexMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("effectiveDate", getEffectiveDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("cpiIndexMasterSid", getCpiIndexMasterSid());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("indexType", getIndexType());
		attributes.put("indexId", getIndexId());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("indexValue", getIndexValue());
		attributes.put("source", getSource());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date effectiveDate = (Date)attributes.get("effectiveDate");

		if (effectiveDate != null) {
			setEffectiveDate(effectiveDate);
		}

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

		Integer cpiIndexMasterSid = (Integer)attributes.get("cpiIndexMasterSid");

		if (cpiIndexMasterSid != null) {
			setCpiIndexMasterSid(cpiIndexMasterSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String indexType = (String)attributes.get("indexType");

		if (indexType != null) {
			setIndexType(indexType);
		}

		String indexId = (String)attributes.get("indexId");

		if (indexId != null) {
			setIndexId(indexId);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String indexValue = (String)attributes.get("indexValue");

		if (indexValue != null) {
			setIndexValue(indexValue);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CpiIndexMasterWrapper((CpiIndexMaster)_cpiIndexMaster.clone());
	}

	@Override
	public int compareTo(CpiIndexMaster cpiIndexMaster) {
		return _cpiIndexMaster.compareTo(cpiIndexMaster);
	}

	/**
	* Returns the batch ID of this cpi index master.
	*
	* @return the batch ID of this cpi index master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _cpiIndexMaster.getBatchId();
	}

	/**
	* Returns the cpi index master sid of this cpi index master.
	*
	* @return the cpi index master sid of this cpi index master
	*/
	@Override
	public int getCpiIndexMasterSid() {
		return _cpiIndexMaster.getCpiIndexMasterSid();
	}

	/**
	* Returns the created by of this cpi index master.
	*
	* @return the created by of this cpi index master
	*/
	@Override
	public int getCreatedBy() {
		return _cpiIndexMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this cpi index master.
	*
	* @return the created date of this cpi index master
	*/
	@Override
	public Date getCreatedDate() {
		return _cpiIndexMaster.getCreatedDate();
	}

	/**
	* Returns the effective date of this cpi index master.
	*
	* @return the effective date of this cpi index master
	*/
	@Override
	public Date getEffectiveDate() {
		return _cpiIndexMaster.getEffectiveDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cpiIndexMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this cpi index master.
	*
	* @return the inbound status of this cpi index master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _cpiIndexMaster.getInboundStatus();
	}

	/**
	* Returns the index ID of this cpi index master.
	*
	* @return the index ID of this cpi index master
	*/
	@Override
	public java.lang.String getIndexId() {
		return _cpiIndexMaster.getIndexId();
	}

	/**
	* Returns the index type of this cpi index master.
	*
	* @return the index type of this cpi index master
	*/
	@Override
	public java.lang.String getIndexType() {
		return _cpiIndexMaster.getIndexType();
	}

	/**
	* Returns the index value of this cpi index master.
	*
	* @return the index value of this cpi index master
	*/
	@Override
	public java.lang.String getIndexValue() {
		return _cpiIndexMaster.getIndexValue();
	}

	/**
	* Returns the modified by of this cpi index master.
	*
	* @return the modified by of this cpi index master
	*/
	@Override
	public int getModifiedBy() {
		return _cpiIndexMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this cpi index master.
	*
	* @return the modified date of this cpi index master
	*/
	@Override
	public Date getModifiedDate() {
		return _cpiIndexMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this cpi index master.
	*
	* @return the primary key of this cpi index master
	*/
	@Override
	public int getPrimaryKey() {
		return _cpiIndexMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpiIndexMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this cpi index master.
	*
	* @return the record lock status of this cpi index master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _cpiIndexMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this cpi index master.
	*
	* @return the source of this cpi index master
	*/
	@Override
	public java.lang.String getSource() {
		return _cpiIndexMaster.getSource();
	}

	/**
	* Returns the status of this cpi index master.
	*
	* @return the status of this cpi index master
	*/
	@Override
	public java.lang.String getStatus() {
		return _cpiIndexMaster.getStatus();
	}

	@Override
	public int hashCode() {
		return _cpiIndexMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cpiIndexMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cpiIndexMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cpiIndexMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this cpi index master is record lock status.
	*
	* @return <code>true</code> if this cpi index master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _cpiIndexMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_cpiIndexMaster.persist();
	}

	/**
	* Sets the batch ID of this cpi index master.
	*
	* @param batchId the batch ID of this cpi index master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_cpiIndexMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cpiIndexMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the cpi index master sid of this cpi index master.
	*
	* @param cpiIndexMasterSid the cpi index master sid of this cpi index master
	*/
	@Override
	public void setCpiIndexMasterSid(int cpiIndexMasterSid) {
		_cpiIndexMaster.setCpiIndexMasterSid(cpiIndexMasterSid);
	}

	/**
	* Sets the created by of this cpi index master.
	*
	* @param createdBy the created by of this cpi index master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_cpiIndexMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cpi index master.
	*
	* @param createdDate the created date of this cpi index master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cpiIndexMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the effective date of this cpi index master.
	*
	* @param effectiveDate the effective date of this cpi index master
	*/
	@Override
	public void setEffectiveDate(Date effectiveDate) {
		_cpiIndexMaster.setEffectiveDate(effectiveDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cpiIndexMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cpiIndexMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cpiIndexMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this cpi index master.
	*
	* @param inboundStatus the inbound status of this cpi index master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_cpiIndexMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the index ID of this cpi index master.
	*
	* @param indexId the index ID of this cpi index master
	*/
	@Override
	public void setIndexId(java.lang.String indexId) {
		_cpiIndexMaster.setIndexId(indexId);
	}

	/**
	* Sets the index type of this cpi index master.
	*
	* @param indexType the index type of this cpi index master
	*/
	@Override
	public void setIndexType(java.lang.String indexType) {
		_cpiIndexMaster.setIndexType(indexType);
	}

	/**
	* Sets the index value of this cpi index master.
	*
	* @param indexValue the index value of this cpi index master
	*/
	@Override
	public void setIndexValue(java.lang.String indexValue) {
		_cpiIndexMaster.setIndexValue(indexValue);
	}

	/**
	* Sets the modified by of this cpi index master.
	*
	* @param modifiedBy the modified by of this cpi index master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_cpiIndexMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this cpi index master.
	*
	* @param modifiedDate the modified date of this cpi index master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cpiIndexMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cpiIndexMaster.setNew(n);
	}

	/**
	* Sets the primary key of this cpi index master.
	*
	* @param primaryKey the primary key of this cpi index master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cpiIndexMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cpiIndexMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this cpi index master is record lock status.
	*
	* @param recordLockStatus the record lock status of this cpi index master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_cpiIndexMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this cpi index master.
	*
	* @param source the source of this cpi index master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_cpiIndexMaster.setSource(source);
	}

	/**
	* Sets the status of this cpi index master.
	*
	* @param status the status of this cpi index master
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_cpiIndexMaster.setStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CpiIndexMaster> toCacheModel() {
		return _cpiIndexMaster.toCacheModel();
	}

	@Override
	public CpiIndexMaster toEscapedModel() {
		return new CpiIndexMasterWrapper(_cpiIndexMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cpiIndexMaster.toString();
	}

	@Override
	public CpiIndexMaster toUnescapedModel() {
		return new CpiIndexMasterWrapper(_cpiIndexMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cpiIndexMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CpiIndexMasterWrapper)) {
			return false;
		}

		CpiIndexMasterWrapper cpiIndexMasterWrapper = (CpiIndexMasterWrapper)obj;

		if (Objects.equals(_cpiIndexMaster,
					cpiIndexMasterWrapper._cpiIndexMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public CpiIndexMaster getWrappedModel() {
		return _cpiIndexMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cpiIndexMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cpiIndexMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cpiIndexMaster.resetOriginalValues();
	}

	private final CpiIndexMaster _cpiIndexMaster;
}