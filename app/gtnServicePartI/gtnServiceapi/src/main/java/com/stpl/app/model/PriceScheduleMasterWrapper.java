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
 * This class is a wrapper for {@link PriceScheduleMaster}.
 * </p>
 *
 * @author
 * @see PriceScheduleMaster
 * @generated
 */
@ProviderType
public class PriceScheduleMasterWrapper implements PriceScheduleMaster,
	ModelWrapper<PriceScheduleMaster> {
	public PriceScheduleMasterWrapper(PriceScheduleMaster priceScheduleMaster) {
		_priceScheduleMaster = priceScheduleMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return PriceScheduleMaster.class;
	}

	@Override
	public String getModelClassName() {
		return PriceScheduleMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("parentPriceScheduleName", getParentPriceScheduleName());
		attributes.put("parentPriceScheduleId", getParentPriceScheduleId());
		attributes.put("priceScheduleStartDate", getPriceScheduleStartDate());
		attributes.put("priceScheduleNo", getPriceScheduleNo());
		attributes.put("priceScheduleName", getPriceScheduleName());
		attributes.put("priceScheduleId", getPriceScheduleId());
		attributes.put("priceScheduleType", getPriceScheduleType());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("priceScheduleSystemId", getPriceScheduleSystemId());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("priceScheduleDesignation", getPriceScheduleDesignation());
		attributes.put("priceScheduleEndDate", getPriceScheduleEndDate());
		attributes.put("priceScheduleStatus", getPriceScheduleStatus());
		attributes.put("batchId", getBatchId());
		attributes.put("priceScheduleCategory", getPriceScheduleCategory());
		attributes.put("tradeClass", getTradeClass());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedBy", getModifiedBy());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String parentPriceScheduleName = (String)attributes.get(
				"parentPriceScheduleName");

		if (parentPriceScheduleName != null) {
			setParentPriceScheduleName(parentPriceScheduleName);
		}

		String parentPriceScheduleId = (String)attributes.get(
				"parentPriceScheduleId");

		if (parentPriceScheduleId != null) {
			setParentPriceScheduleId(parentPriceScheduleId);
		}

		Date priceScheduleStartDate = (Date)attributes.get(
				"priceScheduleStartDate");

		if (priceScheduleStartDate != null) {
			setPriceScheduleStartDate(priceScheduleStartDate);
		}

		String priceScheduleNo = (String)attributes.get("priceScheduleNo");

		if (priceScheduleNo != null) {
			setPriceScheduleNo(priceScheduleNo);
		}

		String priceScheduleName = (String)attributes.get("priceScheduleName");

		if (priceScheduleName != null) {
			setPriceScheduleName(priceScheduleName);
		}

		String priceScheduleId = (String)attributes.get("priceScheduleId");

		if (priceScheduleId != null) {
			setPriceScheduleId(priceScheduleId);
		}

		String priceScheduleType = (String)attributes.get("priceScheduleType");

		if (priceScheduleType != null) {
			setPriceScheduleType(priceScheduleType);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer priceScheduleSystemId = (Integer)attributes.get(
				"priceScheduleSystemId");

		if (priceScheduleSystemId != null) {
			setPriceScheduleSystemId(priceScheduleSystemId);
		}

		String recordLockStatus = (String)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String priceScheduleDesignation = (String)attributes.get(
				"priceScheduleDesignation");

		if (priceScheduleDesignation != null) {
			setPriceScheduleDesignation(priceScheduleDesignation);
		}

		Date priceScheduleEndDate = (Date)attributes.get("priceScheduleEndDate");

		if (priceScheduleEndDate != null) {
			setPriceScheduleEndDate(priceScheduleEndDate);
		}

		String priceScheduleStatus = (String)attributes.get(
				"priceScheduleStatus");

		if (priceScheduleStatus != null) {
			setPriceScheduleStatus(priceScheduleStatus);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String priceScheduleCategory = (String)attributes.get(
				"priceScheduleCategory");

		if (priceScheduleCategory != null) {
			setPriceScheduleCategory(priceScheduleCategory);
		}

		String tradeClass = (String)attributes.get("tradeClass");

		if (tradeClass != null) {
			setTradeClass(tradeClass);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new PriceScheduleMasterWrapper((PriceScheduleMaster)_priceScheduleMaster.clone());
	}

	@Override
	public int compareTo(PriceScheduleMaster priceScheduleMaster) {
		return _priceScheduleMaster.compareTo(priceScheduleMaster);
	}

	/**
	* Returns the batch ID of this price schedule master.
	*
	* @return the batch ID of this price schedule master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _priceScheduleMaster.getBatchId();
	}

	/**
	* Returns the created by of this price schedule master.
	*
	* @return the created by of this price schedule master
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _priceScheduleMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this price schedule master.
	*
	* @return the created date of this price schedule master
	*/
	@Override
	public Date getCreatedDate() {
		return _priceScheduleMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _priceScheduleMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this price schedule master.
	*
	* @return the inbound status of this price schedule master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _priceScheduleMaster.getInboundStatus();
	}

	/**
	* Returns the modified by of this price schedule master.
	*
	* @return the modified by of this price schedule master
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _priceScheduleMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this price schedule master.
	*
	* @return the modified date of this price schedule master
	*/
	@Override
	public Date getModifiedDate() {
		return _priceScheduleMaster.getModifiedDate();
	}

	/**
	* Returns the parent price schedule ID of this price schedule master.
	*
	* @return the parent price schedule ID of this price schedule master
	*/
	@Override
	public java.lang.String getParentPriceScheduleId() {
		return _priceScheduleMaster.getParentPriceScheduleId();
	}

	/**
	* Returns the parent price schedule name of this price schedule master.
	*
	* @return the parent price schedule name of this price schedule master
	*/
	@Override
	public java.lang.String getParentPriceScheduleName() {
		return _priceScheduleMaster.getParentPriceScheduleName();
	}

	/**
	* Returns the price schedule category of this price schedule master.
	*
	* @return the price schedule category of this price schedule master
	*/
	@Override
	public java.lang.String getPriceScheduleCategory() {
		return _priceScheduleMaster.getPriceScheduleCategory();
	}

	/**
	* Returns the price schedule designation of this price schedule master.
	*
	* @return the price schedule designation of this price schedule master
	*/
	@Override
	public java.lang.String getPriceScheduleDesignation() {
		return _priceScheduleMaster.getPriceScheduleDesignation();
	}

	/**
	* Returns the price schedule end date of this price schedule master.
	*
	* @return the price schedule end date of this price schedule master
	*/
	@Override
	public Date getPriceScheduleEndDate() {
		return _priceScheduleMaster.getPriceScheduleEndDate();
	}

	/**
	* Returns the price schedule ID of this price schedule master.
	*
	* @return the price schedule ID of this price schedule master
	*/
	@Override
	public java.lang.String getPriceScheduleId() {
		return _priceScheduleMaster.getPriceScheduleId();
	}

	/**
	* Returns the price schedule name of this price schedule master.
	*
	* @return the price schedule name of this price schedule master
	*/
	@Override
	public java.lang.String getPriceScheduleName() {
		return _priceScheduleMaster.getPriceScheduleName();
	}

	/**
	* Returns the price schedule no of this price schedule master.
	*
	* @return the price schedule no of this price schedule master
	*/
	@Override
	public java.lang.String getPriceScheduleNo() {
		return _priceScheduleMaster.getPriceScheduleNo();
	}

	/**
	* Returns the price schedule start date of this price schedule master.
	*
	* @return the price schedule start date of this price schedule master
	*/
	@Override
	public Date getPriceScheduleStartDate() {
		return _priceScheduleMaster.getPriceScheduleStartDate();
	}

	/**
	* Returns the price schedule status of this price schedule master.
	*
	* @return the price schedule status of this price schedule master
	*/
	@Override
	public java.lang.String getPriceScheduleStatus() {
		return _priceScheduleMaster.getPriceScheduleStatus();
	}

	/**
	* Returns the price schedule system ID of this price schedule master.
	*
	* @return the price schedule system ID of this price schedule master
	*/
	@Override
	public int getPriceScheduleSystemId() {
		return _priceScheduleMaster.getPriceScheduleSystemId();
	}

	/**
	* Returns the price schedule type of this price schedule master.
	*
	* @return the price schedule type of this price schedule master
	*/
	@Override
	public java.lang.String getPriceScheduleType() {
		return _priceScheduleMaster.getPriceScheduleType();
	}

	/**
	* Returns the primary key of this price schedule master.
	*
	* @return the primary key of this price schedule master
	*/
	@Override
	public int getPrimaryKey() {
		return _priceScheduleMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _priceScheduleMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this price schedule master.
	*
	* @return the record lock status of this price schedule master
	*/
	@Override
	public java.lang.String getRecordLockStatus() {
		return _priceScheduleMaster.getRecordLockStatus();
	}

	/**
	* Returns the trade class of this price schedule master.
	*
	* @return the trade class of this price schedule master
	*/
	@Override
	public java.lang.String getTradeClass() {
		return _priceScheduleMaster.getTradeClass();
	}

	@Override
	public int hashCode() {
		return _priceScheduleMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _priceScheduleMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _priceScheduleMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _priceScheduleMaster.isNew();
	}

	@Override
	public void persist() {
		_priceScheduleMaster.persist();
	}

	/**
	* Sets the batch ID of this price schedule master.
	*
	* @param batchId the batch ID of this price schedule master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_priceScheduleMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_priceScheduleMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this price schedule master.
	*
	* @param createdBy the created by of this price schedule master
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_priceScheduleMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this price schedule master.
	*
	* @param createdDate the created date of this price schedule master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_priceScheduleMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_priceScheduleMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_priceScheduleMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_priceScheduleMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this price schedule master.
	*
	* @param inboundStatus the inbound status of this price schedule master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_priceScheduleMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this price schedule master.
	*
	* @param modifiedBy the modified by of this price schedule master
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_priceScheduleMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this price schedule master.
	*
	* @param modifiedDate the modified date of this price schedule master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_priceScheduleMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_priceScheduleMaster.setNew(n);
	}

	/**
	* Sets the parent price schedule ID of this price schedule master.
	*
	* @param parentPriceScheduleId the parent price schedule ID of this price schedule master
	*/
	@Override
	public void setParentPriceScheduleId(java.lang.String parentPriceScheduleId) {
		_priceScheduleMaster.setParentPriceScheduleId(parentPriceScheduleId);
	}

	/**
	* Sets the parent price schedule name of this price schedule master.
	*
	* @param parentPriceScheduleName the parent price schedule name of this price schedule master
	*/
	@Override
	public void setParentPriceScheduleName(
		java.lang.String parentPriceScheduleName) {
		_priceScheduleMaster.setParentPriceScheduleName(parentPriceScheduleName);
	}

	/**
	* Sets the price schedule category of this price schedule master.
	*
	* @param priceScheduleCategory the price schedule category of this price schedule master
	*/
	@Override
	public void setPriceScheduleCategory(java.lang.String priceScheduleCategory) {
		_priceScheduleMaster.setPriceScheduleCategory(priceScheduleCategory);
	}

	/**
	* Sets the price schedule designation of this price schedule master.
	*
	* @param priceScheduleDesignation the price schedule designation of this price schedule master
	*/
	@Override
	public void setPriceScheduleDesignation(
		java.lang.String priceScheduleDesignation) {
		_priceScheduleMaster.setPriceScheduleDesignation(priceScheduleDesignation);
	}

	/**
	* Sets the price schedule end date of this price schedule master.
	*
	* @param priceScheduleEndDate the price schedule end date of this price schedule master
	*/
	@Override
	public void setPriceScheduleEndDate(Date priceScheduleEndDate) {
		_priceScheduleMaster.setPriceScheduleEndDate(priceScheduleEndDate);
	}

	/**
	* Sets the price schedule ID of this price schedule master.
	*
	* @param priceScheduleId the price schedule ID of this price schedule master
	*/
	@Override
	public void setPriceScheduleId(java.lang.String priceScheduleId) {
		_priceScheduleMaster.setPriceScheduleId(priceScheduleId);
	}

	/**
	* Sets the price schedule name of this price schedule master.
	*
	* @param priceScheduleName the price schedule name of this price schedule master
	*/
	@Override
	public void setPriceScheduleName(java.lang.String priceScheduleName) {
		_priceScheduleMaster.setPriceScheduleName(priceScheduleName);
	}

	/**
	* Sets the price schedule no of this price schedule master.
	*
	* @param priceScheduleNo the price schedule no of this price schedule master
	*/
	@Override
	public void setPriceScheduleNo(java.lang.String priceScheduleNo) {
		_priceScheduleMaster.setPriceScheduleNo(priceScheduleNo);
	}

	/**
	* Sets the price schedule start date of this price schedule master.
	*
	* @param priceScheduleStartDate the price schedule start date of this price schedule master
	*/
	@Override
	public void setPriceScheduleStartDate(Date priceScheduleStartDate) {
		_priceScheduleMaster.setPriceScheduleStartDate(priceScheduleStartDate);
	}

	/**
	* Sets the price schedule status of this price schedule master.
	*
	* @param priceScheduleStatus the price schedule status of this price schedule master
	*/
	@Override
	public void setPriceScheduleStatus(java.lang.String priceScheduleStatus) {
		_priceScheduleMaster.setPriceScheduleStatus(priceScheduleStatus);
	}

	/**
	* Sets the price schedule system ID of this price schedule master.
	*
	* @param priceScheduleSystemId the price schedule system ID of this price schedule master
	*/
	@Override
	public void setPriceScheduleSystemId(int priceScheduleSystemId) {
		_priceScheduleMaster.setPriceScheduleSystemId(priceScheduleSystemId);
	}

	/**
	* Sets the price schedule type of this price schedule master.
	*
	* @param priceScheduleType the price schedule type of this price schedule master
	*/
	@Override
	public void setPriceScheduleType(java.lang.String priceScheduleType) {
		_priceScheduleMaster.setPriceScheduleType(priceScheduleType);
	}

	/**
	* Sets the primary key of this price schedule master.
	*
	* @param primaryKey the primary key of this price schedule master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_priceScheduleMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_priceScheduleMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the record lock status of this price schedule master.
	*
	* @param recordLockStatus the record lock status of this price schedule master
	*/
	@Override
	public void setRecordLockStatus(java.lang.String recordLockStatus) {
		_priceScheduleMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the trade class of this price schedule master.
	*
	* @param tradeClass the trade class of this price schedule master
	*/
	@Override
	public void setTradeClass(java.lang.String tradeClass) {
		_priceScheduleMaster.setTradeClass(tradeClass);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PriceScheduleMaster> toCacheModel() {
		return _priceScheduleMaster.toCacheModel();
	}

	@Override
	public PriceScheduleMaster toEscapedModel() {
		return new PriceScheduleMasterWrapper(_priceScheduleMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _priceScheduleMaster.toString();
	}

	@Override
	public PriceScheduleMaster toUnescapedModel() {
		return new PriceScheduleMasterWrapper(_priceScheduleMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _priceScheduleMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PriceScheduleMasterWrapper)) {
			return false;
		}

		PriceScheduleMasterWrapper priceScheduleMasterWrapper = (PriceScheduleMasterWrapper)obj;

		if (Objects.equals(_priceScheduleMaster,
					priceScheduleMasterWrapper._priceScheduleMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public PriceScheduleMaster getWrappedModel() {
		return _priceScheduleMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _priceScheduleMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _priceScheduleMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_priceScheduleMaster.resetOriginalValues();
	}

	private final PriceScheduleMaster _priceScheduleMaster;
}