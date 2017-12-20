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
 * This class is a wrapper for {@link BrandMaster}.
 * </p>
 *
 * @author
 * @see BrandMaster
 * @generated
 */
@ProviderType
public class BrandMasterWrapper implements BrandMaster,
	ModelWrapper<BrandMaster> {
	public BrandMasterWrapper(BrandMaster brandMaster) {
		_brandMaster = brandMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return BrandMaster.class;
	}

	@Override
	public String getModelClassName() {
		return BrandMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("brandId", getBrandId());
		attributes.put("displayBrand", getDisplayBrand());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("brandName", getBrandName());
		attributes.put("brandDesc", getBrandDesc());
		attributes.put("source", getSource());
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

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String brandId = (String)attributes.get("brandId");

		if (brandId != null) {
			setBrandId(brandId);
		}

		String displayBrand = (String)attributes.get("displayBrand");

		if (displayBrand != null) {
			setDisplayBrand(displayBrand);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String brandName = (String)attributes.get("brandName");

		if (brandName != null) {
			setBrandName(brandName);
		}

		String brandDesc = (String)attributes.get("brandDesc");

		if (brandDesc != null) {
			setBrandDesc(brandDesc);
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
		return new BrandMasterWrapper((BrandMaster)_brandMaster.clone());
	}

	@Override
	public int compareTo(BrandMaster brandMaster) {
		return _brandMaster.compareTo(brandMaster);
	}

	/**
	* Returns the batch ID of this brand master.
	*
	* @return the batch ID of this brand master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _brandMaster.getBatchId();
	}

	/**
	* Returns the brand desc of this brand master.
	*
	* @return the brand desc of this brand master
	*/
	@Override
	public java.lang.String getBrandDesc() {
		return _brandMaster.getBrandDesc();
	}

	/**
	* Returns the brand ID of this brand master.
	*
	* @return the brand ID of this brand master
	*/
	@Override
	public java.lang.String getBrandId() {
		return _brandMaster.getBrandId();
	}

	/**
	* Returns the brand master sid of this brand master.
	*
	* @return the brand master sid of this brand master
	*/
	@Override
	public int getBrandMasterSid() {
		return _brandMaster.getBrandMasterSid();
	}

	/**
	* Returns the brand name of this brand master.
	*
	* @return the brand name of this brand master
	*/
	@Override
	public java.lang.String getBrandName() {
		return _brandMaster.getBrandName();
	}

	/**
	* Returns the created by of this brand master.
	*
	* @return the created by of this brand master
	*/
	@Override
	public int getCreatedBy() {
		return _brandMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this brand master.
	*
	* @return the created date of this brand master
	*/
	@Override
	public Date getCreatedDate() {
		return _brandMaster.getCreatedDate();
	}

	/**
	* Returns the display brand of this brand master.
	*
	* @return the display brand of this brand master
	*/
	@Override
	public java.lang.String getDisplayBrand() {
		return _brandMaster.getDisplayBrand();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _brandMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this brand master.
	*
	* @return the inbound status of this brand master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _brandMaster.getInboundStatus();
	}

	/**
	* Returns the modified by of this brand master.
	*
	* @return the modified by of this brand master
	*/
	@Override
	public int getModifiedBy() {
		return _brandMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this brand master.
	*
	* @return the modified date of this brand master
	*/
	@Override
	public Date getModifiedDate() {
		return _brandMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this brand master.
	*
	* @return the primary key of this brand master
	*/
	@Override
	public int getPrimaryKey() {
		return _brandMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _brandMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this brand master.
	*
	* @return the record lock status of this brand master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _brandMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this brand master.
	*
	* @return the source of this brand master
	*/
	@Override
	public java.lang.String getSource() {
		return _brandMaster.getSource();
	}

	@Override
	public int hashCode() {
		return _brandMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _brandMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _brandMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _brandMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this brand master is record lock status.
	*
	* @return <code>true</code> if this brand master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _brandMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_brandMaster.persist();
	}

	/**
	* Sets the batch ID of this brand master.
	*
	* @param batchId the batch ID of this brand master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_brandMaster.setBatchId(batchId);
	}

	/**
	* Sets the brand desc of this brand master.
	*
	* @param brandDesc the brand desc of this brand master
	*/
	@Override
	public void setBrandDesc(java.lang.String brandDesc) {
		_brandMaster.setBrandDesc(brandDesc);
	}

	/**
	* Sets the brand ID of this brand master.
	*
	* @param brandId the brand ID of this brand master
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_brandMaster.setBrandId(brandId);
	}

	/**
	* Sets the brand master sid of this brand master.
	*
	* @param brandMasterSid the brand master sid of this brand master
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_brandMaster.setBrandMasterSid(brandMasterSid);
	}

	/**
	* Sets the brand name of this brand master.
	*
	* @param brandName the brand name of this brand master
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_brandMaster.setBrandName(brandName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_brandMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this brand master.
	*
	* @param createdBy the created by of this brand master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_brandMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this brand master.
	*
	* @param createdDate the created date of this brand master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_brandMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the display brand of this brand master.
	*
	* @param displayBrand the display brand of this brand master
	*/
	@Override
	public void setDisplayBrand(java.lang.String displayBrand) {
		_brandMaster.setDisplayBrand(displayBrand);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_brandMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_brandMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_brandMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this brand master.
	*
	* @param inboundStatus the inbound status of this brand master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_brandMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this brand master.
	*
	* @param modifiedBy the modified by of this brand master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_brandMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this brand master.
	*
	* @param modifiedDate the modified date of this brand master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_brandMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_brandMaster.setNew(n);
	}

	/**
	* Sets the primary key of this brand master.
	*
	* @param primaryKey the primary key of this brand master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_brandMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_brandMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this brand master is record lock status.
	*
	* @param recordLockStatus the record lock status of this brand master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_brandMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this brand master.
	*
	* @param source the source of this brand master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_brandMaster.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<BrandMaster> toCacheModel() {
		return _brandMaster.toCacheModel();
	}

	@Override
	public BrandMaster toEscapedModel() {
		return new BrandMasterWrapper(_brandMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _brandMaster.toString();
	}

	@Override
	public BrandMaster toUnescapedModel() {
		return new BrandMasterWrapper(_brandMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _brandMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BrandMasterWrapper)) {
			return false;
		}

		BrandMasterWrapper brandMasterWrapper = (BrandMasterWrapper)obj;

		if (Objects.equals(_brandMaster, brandMasterWrapper._brandMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public BrandMaster getWrappedModel() {
		return _brandMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _brandMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _brandMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_brandMaster.resetOriginalValues();
	}

	private final BrandMaster _brandMaster;
}