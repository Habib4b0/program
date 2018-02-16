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
 * This class is a wrapper for {@link ItemIdentifier}.
 * </p>
 *
 * @author
 * @see ItemIdentifier
 * @generated
 */
@ProviderType
public class ItemIdentifierWrapper implements ItemIdentifier,
	ModelWrapper<ItemIdentifier> {
	public ItemIdentifierWrapper(ItemIdentifier itemIdentifier) {
		_itemIdentifier = itemIdentifier;
	}

	@Override
	public Class<?> getModelClass() {
		return ItemIdentifier.class;
	}

	@Override
	public String getModelClassName() {
		return ItemIdentifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemIdentifierSid", getItemIdentifierSid());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("endDate", getEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("identifierStatus", getIdentifierStatus());
		attributes.put("entityCode", getEntityCode());
		attributes.put("itemIdentifierValue", getItemIdentifierValue());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("itemQualifierSid", getItemQualifierSid());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemIdentifierSid = (Integer)attributes.get("itemIdentifierSid");

		if (itemIdentifierSid != null) {
			setItemIdentifierSid(itemIdentifierSid);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer identifierStatus = (Integer)attributes.get("identifierStatus");

		if (identifierStatus != null) {
			setIdentifierStatus(identifierStatus);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		String itemIdentifierValue = (String)attributes.get(
				"itemIdentifierValue");

		if (itemIdentifierValue != null) {
			setItemIdentifierValue(itemIdentifierValue);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Integer itemQualifierSid = (Integer)attributes.get("itemQualifierSid");

		if (itemQualifierSid != null) {
			setItemQualifierSid(itemQualifierSid);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
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
	}

	@Override
	public java.lang.Object clone() {
		return new ItemIdentifierWrapper((ItemIdentifier)_itemIdentifier.clone());
	}

	@Override
	public int compareTo(ItemIdentifier itemIdentifier) {
		return _itemIdentifier.compareTo(itemIdentifier);
	}

	/**
	* Returns the batch ID of this item identifier.
	*
	* @return the batch ID of this item identifier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _itemIdentifier.getBatchId();
	}

	/**
	* Returns the created by of this item identifier.
	*
	* @return the created by of this item identifier
	*/
	@Override
	public int getCreatedBy() {
		return _itemIdentifier.getCreatedBy();
	}

	/**
	* Returns the created date of this item identifier.
	*
	* @return the created date of this item identifier
	*/
	@Override
	public Date getCreatedDate() {
		return _itemIdentifier.getCreatedDate();
	}

	/**
	* Returns the end date of this item identifier.
	*
	* @return the end date of this item identifier
	*/
	@Override
	public Date getEndDate() {
		return _itemIdentifier.getEndDate();
	}

	/**
	* Returns the entity code of this item identifier.
	*
	* @return the entity code of this item identifier
	*/
	@Override
	public java.lang.String getEntityCode() {
		return _itemIdentifier.getEntityCode();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _itemIdentifier.getExpandoBridge();
	}

	/**
	* Returns the identifier status of this item identifier.
	*
	* @return the identifier status of this item identifier
	*/
	@Override
	public int getIdentifierStatus() {
		return _itemIdentifier.getIdentifierStatus();
	}

	/**
	* Returns the inbound status of this item identifier.
	*
	* @return the inbound status of this item identifier
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _itemIdentifier.getInboundStatus();
	}

	/**
	* Returns the item identifier sid of this item identifier.
	*
	* @return the item identifier sid of this item identifier
	*/
	@Override
	public int getItemIdentifierSid() {
		return _itemIdentifier.getItemIdentifierSid();
	}

	/**
	* Returns the item identifier value of this item identifier.
	*
	* @return the item identifier value of this item identifier
	*/
	@Override
	public java.lang.String getItemIdentifierValue() {
		return _itemIdentifier.getItemIdentifierValue();
	}

	/**
	* Returns the item master sid of this item identifier.
	*
	* @return the item master sid of this item identifier
	*/
	@Override
	public int getItemMasterSid() {
		return _itemIdentifier.getItemMasterSid();
	}

	/**
	* Returns the item qualifier sid of this item identifier.
	*
	* @return the item qualifier sid of this item identifier
	*/
	@Override
	public int getItemQualifierSid() {
		return _itemIdentifier.getItemQualifierSid();
	}

	/**
	* Returns the modified by of this item identifier.
	*
	* @return the modified by of this item identifier
	*/
	@Override
	public int getModifiedBy() {
		return _itemIdentifier.getModifiedBy();
	}

	/**
	* Returns the modified date of this item identifier.
	*
	* @return the modified date of this item identifier
	*/
	@Override
	public Date getModifiedDate() {
		return _itemIdentifier.getModifiedDate();
	}

	/**
	* Returns the primary key of this item identifier.
	*
	* @return the primary key of this item identifier
	*/
	@Override
	public int getPrimaryKey() {
		return _itemIdentifier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemIdentifier.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this item identifier.
	*
	* @return the record lock status of this item identifier
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _itemIdentifier.getRecordLockStatus();
	}

	/**
	* Returns the source of this item identifier.
	*
	* @return the source of this item identifier
	*/
	@Override
	public java.lang.String getSource() {
		return _itemIdentifier.getSource();
	}

	/**
	* Returns the start date of this item identifier.
	*
	* @return the start date of this item identifier
	*/
	@Override
	public Date getStartDate() {
		return _itemIdentifier.getStartDate();
	}

	@Override
	public int hashCode() {
		return _itemIdentifier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _itemIdentifier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _itemIdentifier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _itemIdentifier.isNew();
	}

	/**
	* Returns <code>true</code> if this item identifier is record lock status.
	*
	* @return <code>true</code> if this item identifier is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _itemIdentifier.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_itemIdentifier.persist();
	}

	/**
	* Sets the batch ID of this item identifier.
	*
	* @param batchId the batch ID of this item identifier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_itemIdentifier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_itemIdentifier.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this item identifier.
	*
	* @param createdBy the created by of this item identifier
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_itemIdentifier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this item identifier.
	*
	* @param createdDate the created date of this item identifier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_itemIdentifier.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this item identifier.
	*
	* @param endDate the end date of this item identifier
	*/
	@Override
	public void setEndDate(Date endDate) {
		_itemIdentifier.setEndDate(endDate);
	}

	/**
	* Sets the entity code of this item identifier.
	*
	* @param entityCode the entity code of this item identifier
	*/
	@Override
	public void setEntityCode(java.lang.String entityCode) {
		_itemIdentifier.setEntityCode(entityCode);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_itemIdentifier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_itemIdentifier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_itemIdentifier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the identifier status of this item identifier.
	*
	* @param identifierStatus the identifier status of this item identifier
	*/
	@Override
	public void setIdentifierStatus(int identifierStatus) {
		_itemIdentifier.setIdentifierStatus(identifierStatus);
	}

	/**
	* Sets the inbound status of this item identifier.
	*
	* @param inboundStatus the inbound status of this item identifier
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_itemIdentifier.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item identifier sid of this item identifier.
	*
	* @param itemIdentifierSid the item identifier sid of this item identifier
	*/
	@Override
	public void setItemIdentifierSid(int itemIdentifierSid) {
		_itemIdentifier.setItemIdentifierSid(itemIdentifierSid);
	}

	/**
	* Sets the item identifier value of this item identifier.
	*
	* @param itemIdentifierValue the item identifier value of this item identifier
	*/
	@Override
	public void setItemIdentifierValue(java.lang.String itemIdentifierValue) {
		_itemIdentifier.setItemIdentifierValue(itemIdentifierValue);
	}

	/**
	* Sets the item master sid of this item identifier.
	*
	* @param itemMasterSid the item master sid of this item identifier
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_itemIdentifier.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item qualifier sid of this item identifier.
	*
	* @param itemQualifierSid the item qualifier sid of this item identifier
	*/
	@Override
	public void setItemQualifierSid(int itemQualifierSid) {
		_itemIdentifier.setItemQualifierSid(itemQualifierSid);
	}

	/**
	* Sets the modified by of this item identifier.
	*
	* @param modifiedBy the modified by of this item identifier
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_itemIdentifier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this item identifier.
	*
	* @param modifiedDate the modified date of this item identifier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_itemIdentifier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_itemIdentifier.setNew(n);
	}

	/**
	* Sets the primary key of this item identifier.
	*
	* @param primaryKey the primary key of this item identifier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_itemIdentifier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_itemIdentifier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this item identifier is record lock status.
	*
	* @param recordLockStatus the record lock status of this item identifier
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_itemIdentifier.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this item identifier.
	*
	* @param source the source of this item identifier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_itemIdentifier.setSource(source);
	}

	/**
	* Sets the start date of this item identifier.
	*
	* @param startDate the start date of this item identifier
	*/
	@Override
	public void setStartDate(Date startDate) {
		_itemIdentifier.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ItemIdentifier> toCacheModel() {
		return _itemIdentifier.toCacheModel();
	}

	@Override
	public ItemIdentifier toEscapedModel() {
		return new ItemIdentifierWrapper(_itemIdentifier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _itemIdentifier.toString();
	}

	@Override
	public ItemIdentifier toUnescapedModel() {
		return new ItemIdentifierWrapper(_itemIdentifier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _itemIdentifier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemIdentifierWrapper)) {
			return false;
		}

		ItemIdentifierWrapper itemIdentifierWrapper = (ItemIdentifierWrapper)obj;

		if (Objects.equals(_itemIdentifier,
					itemIdentifierWrapper._itemIdentifier)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemIdentifier getWrappedModel() {
		return _itemIdentifier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _itemIdentifier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _itemIdentifier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_itemIdentifier.resetOriginalValues();
	}

	private final ItemIdentifier _itemIdentifier;
}