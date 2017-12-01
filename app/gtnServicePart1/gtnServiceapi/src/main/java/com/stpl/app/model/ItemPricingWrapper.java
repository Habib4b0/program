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
 * This class is a wrapper for {@link ItemPricing}.
 * </p>
 *
 * @author
 * @see ItemPricing
 * @generated
 */
@ProviderType
public class ItemPricingWrapper implements ItemPricing,
	ModelWrapper<ItemPricing> {
	public ItemPricingWrapper(ItemPricing itemPricing) {
		_itemPricing = itemPricing;
	}

	@Override
	public Class<?> getModelClass() {
		return ItemPricing.class;
	}

	@Override
	public String getModelClassName() {
		return ItemPricing.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
		attributes.put("itemPrice", getItemPrice());
		attributes.put("endDate", getEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("entityCode", getEntityCode());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("batchId", getBatchId());
		attributes.put("itemUom", getItemUom());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("itemPricingSid", getItemPricingSid());
		attributes.put("pricingCodeStatus", getPricingCodeStatus());
		attributes.put("itemPricePrecision", getItemPricePrecision());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer itemPricingQualifierSid = (Integer)attributes.get(
				"itemPricingQualifierSid");

		if (itemPricingQualifierSid != null) {
			setItemPricingQualifierSid(itemPricingQualifierSid);
		}

		Double itemPrice = (Double)attributes.get("itemPrice");

		if (itemPrice != null) {
			setItemPrice(itemPrice);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
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

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer itemUom = (Integer)attributes.get("itemUom");

		if (itemUom != null) {
			setItemUom(itemUom);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer itemPricingSid = (Integer)attributes.get("itemPricingSid");

		if (itemPricingSid != null) {
			setItemPricingSid(itemPricingSid);
		}

		Integer pricingCodeStatus = (Integer)attributes.get("pricingCodeStatus");

		if (pricingCodeStatus != null) {
			setPricingCodeStatus(pricingCodeStatus);
		}

		Integer itemPricePrecision = (Integer)attributes.get(
				"itemPricePrecision");

		if (itemPricePrecision != null) {
			setItemPricePrecision(itemPricePrecision);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ItemPricingWrapper((ItemPricing)_itemPricing.clone());
	}

	@Override
	public int compareTo(ItemPricing itemPricing) {
		return _itemPricing.compareTo(itemPricing);
	}

	/**
	* Returns the batch ID of this item pricing.
	*
	* @return the batch ID of this item pricing
	*/
	@Override
	public java.lang.String getBatchId() {
		return _itemPricing.getBatchId();
	}

	/**
	* Returns the created by of this item pricing.
	*
	* @return the created by of this item pricing
	*/
	@Override
	public int getCreatedBy() {
		return _itemPricing.getCreatedBy();
	}

	/**
	* Returns the created date of this item pricing.
	*
	* @return the created date of this item pricing
	*/
	@Override
	public Date getCreatedDate() {
		return _itemPricing.getCreatedDate();
	}

	/**
	* Returns the end date of this item pricing.
	*
	* @return the end date of this item pricing
	*/
	@Override
	public Date getEndDate() {
		return _itemPricing.getEndDate();
	}

	/**
	* Returns the entity code of this item pricing.
	*
	* @return the entity code of this item pricing
	*/
	@Override
	public java.lang.String getEntityCode() {
		return _itemPricing.getEntityCode();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _itemPricing.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this item pricing.
	*
	* @return the inbound status of this item pricing
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _itemPricing.getInboundStatus();
	}

	/**
	* Returns the item master sid of this item pricing.
	*
	* @return the item master sid of this item pricing
	*/
	@Override
	public int getItemMasterSid() {
		return _itemPricing.getItemMasterSid();
	}

	/**
	* Returns the item price of this item pricing.
	*
	* @return the item price of this item pricing
	*/
	@Override
	public double getItemPrice() {
		return _itemPricing.getItemPrice();
	}

	/**
	* Returns the item price precision of this item pricing.
	*
	* @return the item price precision of this item pricing
	*/
	@Override
	public int getItemPricePrecision() {
		return _itemPricing.getItemPricePrecision();
	}

	/**
	* Returns the item pricing qualifier sid of this item pricing.
	*
	* @return the item pricing qualifier sid of this item pricing
	*/
	@Override
	public int getItemPricingQualifierSid() {
		return _itemPricing.getItemPricingQualifierSid();
	}

	/**
	* Returns the item pricing sid of this item pricing.
	*
	* @return the item pricing sid of this item pricing
	*/
	@Override
	public int getItemPricingSid() {
		return _itemPricing.getItemPricingSid();
	}

	/**
	* Returns the item uom of this item pricing.
	*
	* @return the item uom of this item pricing
	*/
	@Override
	public int getItemUom() {
		return _itemPricing.getItemUom();
	}

	/**
	* Returns the modified by of this item pricing.
	*
	* @return the modified by of this item pricing
	*/
	@Override
	public int getModifiedBy() {
		return _itemPricing.getModifiedBy();
	}

	/**
	* Returns the modified date of this item pricing.
	*
	* @return the modified date of this item pricing
	*/
	@Override
	public Date getModifiedDate() {
		return _itemPricing.getModifiedDate();
	}

	/**
	* Returns the pricing code status of this item pricing.
	*
	* @return the pricing code status of this item pricing
	*/
	@Override
	public int getPricingCodeStatus() {
		return _itemPricing.getPricingCodeStatus();
	}

	/**
	* Returns the primary key of this item pricing.
	*
	* @return the primary key of this item pricing
	*/
	@Override
	public int getPrimaryKey() {
		return _itemPricing.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemPricing.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this item pricing.
	*
	* @return the record lock status of this item pricing
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _itemPricing.getRecordLockStatus();
	}

	/**
	* Returns the source of this item pricing.
	*
	* @return the source of this item pricing
	*/
	@Override
	public java.lang.String getSource() {
		return _itemPricing.getSource();
	}

	/**
	* Returns the start date of this item pricing.
	*
	* @return the start date of this item pricing
	*/
	@Override
	public Date getStartDate() {
		return _itemPricing.getStartDate();
	}

	@Override
	public int hashCode() {
		return _itemPricing.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _itemPricing.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _itemPricing.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _itemPricing.isNew();
	}

	/**
	* Returns <code>true</code> if this item pricing is record lock status.
	*
	* @return <code>true</code> if this item pricing is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _itemPricing.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_itemPricing.persist();
	}

	/**
	* Sets the batch ID of this item pricing.
	*
	* @param batchId the batch ID of this item pricing
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_itemPricing.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_itemPricing.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this item pricing.
	*
	* @param createdBy the created by of this item pricing
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_itemPricing.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this item pricing.
	*
	* @param createdDate the created date of this item pricing
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_itemPricing.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this item pricing.
	*
	* @param endDate the end date of this item pricing
	*/
	@Override
	public void setEndDate(Date endDate) {
		_itemPricing.setEndDate(endDate);
	}

	/**
	* Sets the entity code of this item pricing.
	*
	* @param entityCode the entity code of this item pricing
	*/
	@Override
	public void setEntityCode(java.lang.String entityCode) {
		_itemPricing.setEntityCode(entityCode);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_itemPricing.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_itemPricing.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_itemPricing.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this item pricing.
	*
	* @param inboundStatus the inbound status of this item pricing
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_itemPricing.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item master sid of this item pricing.
	*
	* @param itemMasterSid the item master sid of this item pricing
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_itemPricing.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item price of this item pricing.
	*
	* @param itemPrice the item price of this item pricing
	*/
	@Override
	public void setItemPrice(double itemPrice) {
		_itemPricing.setItemPrice(itemPrice);
	}

	/**
	* Sets the item price precision of this item pricing.
	*
	* @param itemPricePrecision the item price precision of this item pricing
	*/
	@Override
	public void setItemPricePrecision(int itemPricePrecision) {
		_itemPricing.setItemPricePrecision(itemPricePrecision);
	}

	/**
	* Sets the item pricing qualifier sid of this item pricing.
	*
	* @param itemPricingQualifierSid the item pricing qualifier sid of this item pricing
	*/
	@Override
	public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
		_itemPricing.setItemPricingQualifierSid(itemPricingQualifierSid);
	}

	/**
	* Sets the item pricing sid of this item pricing.
	*
	* @param itemPricingSid the item pricing sid of this item pricing
	*/
	@Override
	public void setItemPricingSid(int itemPricingSid) {
		_itemPricing.setItemPricingSid(itemPricingSid);
	}

	/**
	* Sets the item uom of this item pricing.
	*
	* @param itemUom the item uom of this item pricing
	*/
	@Override
	public void setItemUom(int itemUom) {
		_itemPricing.setItemUom(itemUom);
	}

	/**
	* Sets the modified by of this item pricing.
	*
	* @param modifiedBy the modified by of this item pricing
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_itemPricing.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this item pricing.
	*
	* @param modifiedDate the modified date of this item pricing
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_itemPricing.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_itemPricing.setNew(n);
	}

	/**
	* Sets the pricing code status of this item pricing.
	*
	* @param pricingCodeStatus the pricing code status of this item pricing
	*/
	@Override
	public void setPricingCodeStatus(int pricingCodeStatus) {
		_itemPricing.setPricingCodeStatus(pricingCodeStatus);
	}

	/**
	* Sets the primary key of this item pricing.
	*
	* @param primaryKey the primary key of this item pricing
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_itemPricing.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_itemPricing.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this item pricing is record lock status.
	*
	* @param recordLockStatus the record lock status of this item pricing
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_itemPricing.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this item pricing.
	*
	* @param source the source of this item pricing
	*/
	@Override
	public void setSource(java.lang.String source) {
		_itemPricing.setSource(source);
	}

	/**
	* Sets the start date of this item pricing.
	*
	* @param startDate the start date of this item pricing
	*/
	@Override
	public void setStartDate(Date startDate) {
		_itemPricing.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ItemPricing> toCacheModel() {
		return _itemPricing.toCacheModel();
	}

	@Override
	public ItemPricing toEscapedModel() {
		return new ItemPricingWrapper(_itemPricing.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _itemPricing.toString();
	}

	@Override
	public ItemPricing toUnescapedModel() {
		return new ItemPricingWrapper(_itemPricing.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _itemPricing.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemPricingWrapper)) {
			return false;
		}

		ItemPricingWrapper itemPricingWrapper = (ItemPricingWrapper)obj;

		if (Objects.equals(_itemPricing, itemPricingWrapper._itemPricing)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemPricing getWrappedModel() {
		return _itemPricing;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _itemPricing.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _itemPricing.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_itemPricing.resetOriginalValues();
	}

	private final ItemPricing _itemPricing;
}