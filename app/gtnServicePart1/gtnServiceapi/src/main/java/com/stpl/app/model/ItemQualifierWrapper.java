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
 * This class is a wrapper for {@link ItemQualifier}.
 * </p>
 *
 * @author
 * @see ItemQualifier
 * @generated
 */
@ProviderType
public class ItemQualifierWrapper implements ItemQualifier,
	ModelWrapper<ItemQualifier> {
	public ItemQualifierWrapper(ItemQualifier itemQualifier) {
		_itemQualifier = itemQualifier;
	}

	@Override
	public Class<?> getModelClass() {
		return ItemQualifier.class;
	}

	@Override
	public String getModelClassName() {
		return ItemQualifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("itemQualifierSid", getItemQualifierSid());
		attributes.put("specificEntityCode", getSpecificEntityCode());
		attributes.put("itemQualifierName", getItemQualifierName());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("effectiveDates", getEffectiveDates());
		attributes.put("notes", getNotes());
		attributes.put("itemQualifierValue", getItemQualifierValue());
		attributes.put("recordLockStatus", getRecordLockStatus());
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

		Integer itemQualifierSid = (Integer)attributes.get("itemQualifierSid");

		if (itemQualifierSid != null) {
			setItemQualifierSid(itemQualifierSid);
		}

		String specificEntityCode = (String)attributes.get("specificEntityCode");

		if (specificEntityCode != null) {
			setSpecificEntityCode(specificEntityCode);
		}

		String itemQualifierName = (String)attributes.get("itemQualifierName");

		if (itemQualifierName != null) {
			setItemQualifierName(itemQualifierName);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String effectiveDates = (String)attributes.get("effectiveDates");

		if (effectiveDates != null) {
			setEffectiveDates(effectiveDates);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		String itemQualifierValue = (String)attributes.get("itemQualifierValue");

		if (itemQualifierValue != null) {
			setItemQualifierValue(itemQualifierValue);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
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
		return new ItemQualifierWrapper((ItemQualifier)_itemQualifier.clone());
	}

	@Override
	public int compareTo(ItemQualifier itemQualifier) {
		return _itemQualifier.compareTo(itemQualifier);
	}

	/**
	* Returns the batch ID of this item qualifier.
	*
	* @return the batch ID of this item qualifier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _itemQualifier.getBatchId();
	}

	/**
	* Returns the created by of this item qualifier.
	*
	* @return the created by of this item qualifier
	*/
	@Override
	public int getCreatedBy() {
		return _itemQualifier.getCreatedBy();
	}

	/**
	* Returns the created date of this item qualifier.
	*
	* @return the created date of this item qualifier
	*/
	@Override
	public Date getCreatedDate() {
		return _itemQualifier.getCreatedDate();
	}

	/**
	* Returns the effective dates of this item qualifier.
	*
	* @return the effective dates of this item qualifier
	*/
	@Override
	public java.lang.String getEffectiveDates() {
		return _itemQualifier.getEffectiveDates();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _itemQualifier.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this item qualifier.
	*
	* @return the inbound status of this item qualifier
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _itemQualifier.getInboundStatus();
	}

	/**
	* Returns the item qualifier name of this item qualifier.
	*
	* @return the item qualifier name of this item qualifier
	*/
	@Override
	public java.lang.String getItemQualifierName() {
		return _itemQualifier.getItemQualifierName();
	}

	/**
	* Returns the item qualifier sid of this item qualifier.
	*
	* @return the item qualifier sid of this item qualifier
	*/
	@Override
	public int getItemQualifierSid() {
		return _itemQualifier.getItemQualifierSid();
	}

	/**
	* Returns the item qualifier value of this item qualifier.
	*
	* @return the item qualifier value of this item qualifier
	*/
	@Override
	public java.lang.String getItemQualifierValue() {
		return _itemQualifier.getItemQualifierValue();
	}

	/**
	* Returns the modified by of this item qualifier.
	*
	* @return the modified by of this item qualifier
	*/
	@Override
	public int getModifiedBy() {
		return _itemQualifier.getModifiedBy();
	}

	/**
	* Returns the modified date of this item qualifier.
	*
	* @return the modified date of this item qualifier
	*/
	@Override
	public Date getModifiedDate() {
		return _itemQualifier.getModifiedDate();
	}

	/**
	* Returns the notes of this item qualifier.
	*
	* @return the notes of this item qualifier
	*/
	@Override
	public java.lang.String getNotes() {
		return _itemQualifier.getNotes();
	}

	/**
	* Returns the primary key of this item qualifier.
	*
	* @return the primary key of this item qualifier
	*/
	@Override
	public int getPrimaryKey() {
		return _itemQualifier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemQualifier.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this item qualifier.
	*
	* @return the record lock status of this item qualifier
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _itemQualifier.getRecordLockStatus();
	}

	/**
	* Returns the source of this item qualifier.
	*
	* @return the source of this item qualifier
	*/
	@Override
	public java.lang.String getSource() {
		return _itemQualifier.getSource();
	}

	/**
	* Returns the specific entity code of this item qualifier.
	*
	* @return the specific entity code of this item qualifier
	*/
	@Override
	public java.lang.String getSpecificEntityCode() {
		return _itemQualifier.getSpecificEntityCode();
	}

	@Override
	public int hashCode() {
		return _itemQualifier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _itemQualifier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _itemQualifier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _itemQualifier.isNew();
	}

	/**
	* Returns <code>true</code> if this item qualifier is record lock status.
	*
	* @return <code>true</code> if this item qualifier is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _itemQualifier.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_itemQualifier.persist();
	}

	/**
	* Sets the batch ID of this item qualifier.
	*
	* @param batchId the batch ID of this item qualifier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_itemQualifier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_itemQualifier.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this item qualifier.
	*
	* @param createdBy the created by of this item qualifier
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_itemQualifier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this item qualifier.
	*
	* @param createdDate the created date of this item qualifier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_itemQualifier.setCreatedDate(createdDate);
	}

	/**
	* Sets the effective dates of this item qualifier.
	*
	* @param effectiveDates the effective dates of this item qualifier
	*/
	@Override
	public void setEffectiveDates(java.lang.String effectiveDates) {
		_itemQualifier.setEffectiveDates(effectiveDates);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_itemQualifier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_itemQualifier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_itemQualifier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this item qualifier.
	*
	* @param inboundStatus the inbound status of this item qualifier
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_itemQualifier.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item qualifier name of this item qualifier.
	*
	* @param itemQualifierName the item qualifier name of this item qualifier
	*/
	@Override
	public void setItemQualifierName(java.lang.String itemQualifierName) {
		_itemQualifier.setItemQualifierName(itemQualifierName);
	}

	/**
	* Sets the item qualifier sid of this item qualifier.
	*
	* @param itemQualifierSid the item qualifier sid of this item qualifier
	*/
	@Override
	public void setItemQualifierSid(int itemQualifierSid) {
		_itemQualifier.setItemQualifierSid(itemQualifierSid);
	}

	/**
	* Sets the item qualifier value of this item qualifier.
	*
	* @param itemQualifierValue the item qualifier value of this item qualifier
	*/
	@Override
	public void setItemQualifierValue(java.lang.String itemQualifierValue) {
		_itemQualifier.setItemQualifierValue(itemQualifierValue);
	}

	/**
	* Sets the modified by of this item qualifier.
	*
	* @param modifiedBy the modified by of this item qualifier
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_itemQualifier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this item qualifier.
	*
	* @param modifiedDate the modified date of this item qualifier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_itemQualifier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_itemQualifier.setNew(n);
	}

	/**
	* Sets the notes of this item qualifier.
	*
	* @param notes the notes of this item qualifier
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_itemQualifier.setNotes(notes);
	}

	/**
	* Sets the primary key of this item qualifier.
	*
	* @param primaryKey the primary key of this item qualifier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_itemQualifier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_itemQualifier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this item qualifier is record lock status.
	*
	* @param recordLockStatus the record lock status of this item qualifier
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_itemQualifier.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this item qualifier.
	*
	* @param source the source of this item qualifier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_itemQualifier.setSource(source);
	}

	/**
	* Sets the specific entity code of this item qualifier.
	*
	* @param specificEntityCode the specific entity code of this item qualifier
	*/
	@Override
	public void setSpecificEntityCode(java.lang.String specificEntityCode) {
		_itemQualifier.setSpecificEntityCode(specificEntityCode);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ItemQualifier> toCacheModel() {
		return _itemQualifier.toCacheModel();
	}

	@Override
	public ItemQualifier toEscapedModel() {
		return new ItemQualifierWrapper(_itemQualifier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _itemQualifier.toString();
	}

	@Override
	public ItemQualifier toUnescapedModel() {
		return new ItemQualifierWrapper(_itemQualifier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _itemQualifier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemQualifierWrapper)) {
			return false;
		}

		ItemQualifierWrapper itemQualifierWrapper = (ItemQualifierWrapper)obj;

		if (Objects.equals(_itemQualifier, itemQualifierWrapper._itemQualifier)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemQualifier getWrappedModel() {
		return _itemQualifier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _itemQualifier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _itemQualifier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_itemQualifier.resetOriginalValues();
	}

	private final ItemQualifier _itemQualifier;
}