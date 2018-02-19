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

package com.stpl.app.parttwo.model;

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
 * This class is a wrapper for {@link VwItemPricing}.
 * </p>
 *
 * @author
 * @see VwItemPricing
 * @generated
 */
@ProviderType
public class VwItemPricingWrapper implements VwItemPricing,
	ModelWrapper<VwItemPricing> {
	public VwItemPricingWrapper(VwItemPricing vwItemPricing) {
		_vwItemPricing = vwItemPricing;
	}

	@Override
	public Class<?> getModelClass() {
		return VwItemPricing.class;
	}

	@Override
	public String getModelClassName() {
		return VwItemPricing.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("pricingCodeQualifier", getPricingCodeQualifier());
		attributes.put("itemPrice", getItemPrice());
		attributes.put("endDate", getEndDate());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("entityCode", getEntityCode());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("batchId", getBatchId());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("itemName", getItemName());
		attributes.put("itemUom", getItemUom());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("itemNo", getItemNo());
		attributes.put("itemPricingSid", getItemPricingSid());
		attributes.put("pricingCodeStatus", getPricingCodeStatus());
		attributes.put("pricingCodeQualifierName", getPricingCodeQualifierName());
		attributes.put("itemPriceprecision", getItemPriceprecision());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String pricingCodeQualifier = (String)attributes.get(
				"pricingCodeQualifier");

		if (pricingCodeQualifier != null) {
			setPricingCodeQualifier(pricingCodeQualifier);
		}

		String itemPrice = (String)attributes.get("itemPrice");

		if (itemPrice != null) {
			setItemPrice(itemPrice);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

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

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String itemUom = (String)attributes.get("itemUom");

		if (itemUom != null) {
			setItemUom(itemUom);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		Integer itemPricingSid = (Integer)attributes.get("itemPricingSid");

		if (itemPricingSid != null) {
			setItemPricingSid(itemPricingSid);
		}

		String pricingCodeStatus = (String)attributes.get("pricingCodeStatus");

		if (pricingCodeStatus != null) {
			setPricingCodeStatus(pricingCodeStatus);
		}

		String pricingCodeQualifierName = (String)attributes.get(
				"pricingCodeQualifierName");

		if (pricingCodeQualifierName != null) {
			setPricingCodeQualifierName(pricingCodeQualifierName);
		}

		Integer itemPriceprecision = (Integer)attributes.get(
				"itemPriceprecision");

		if (itemPriceprecision != null) {
			setItemPriceprecision(itemPriceprecision);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwItemPricingWrapper((VwItemPricing)_vwItemPricing.clone());
	}

	@Override
	public int compareTo(VwItemPricing vwItemPricing) {
		return _vwItemPricing.compareTo(vwItemPricing);
	}

	/**
	* Returns the add chg del indicator of this vw item pricing.
	*
	* @return the add chg del indicator of this vw item pricing
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _vwItemPricing.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this vw item pricing.
	*
	* @return the batch ID of this vw item pricing
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwItemPricing.getBatchId();
	}

	/**
	* Returns the created by of this vw item pricing.
	*
	* @return the created by of this vw item pricing
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwItemPricing.getCreatedBy();
	}

	/**
	* Returns the created date of this vw item pricing.
	*
	* @return the created date of this vw item pricing
	*/
	@Override
	public Date getCreatedDate() {
		return _vwItemPricing.getCreatedDate();
	}

	/**
	* Returns the end date of this vw item pricing.
	*
	* @return the end date of this vw item pricing
	*/
	@Override
	public Date getEndDate() {
		return _vwItemPricing.getEndDate();
	}

	/**
	* Returns the entity code of this vw item pricing.
	*
	* @return the entity code of this vw item pricing
	*/
	@Override
	public java.lang.String getEntityCode() {
		return _vwItemPricing.getEntityCode();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwItemPricing.getExpandoBridge();
	}

	/**
	* Returns the item ID of this vw item pricing.
	*
	* @return the item ID of this vw item pricing
	*/
	@Override
	public java.lang.String getItemId() {
		return _vwItemPricing.getItemId();
	}

	/**
	* Returns the item name of this vw item pricing.
	*
	* @return the item name of this vw item pricing
	*/
	@Override
	public java.lang.String getItemName() {
		return _vwItemPricing.getItemName();
	}

	/**
	* Returns the item no of this vw item pricing.
	*
	* @return the item no of this vw item pricing
	*/
	@Override
	public java.lang.String getItemNo() {
		return _vwItemPricing.getItemNo();
	}

	/**
	* Returns the item price of this vw item pricing.
	*
	* @return the item price of this vw item pricing
	*/
	@Override
	public java.lang.String getItemPrice() {
		return _vwItemPricing.getItemPrice();
	}

	/**
	* Returns the item priceprecision of this vw item pricing.
	*
	* @return the item priceprecision of this vw item pricing
	*/
	@Override
	public int getItemPriceprecision() {
		return _vwItemPricing.getItemPriceprecision();
	}

	/**
	* Returns the item pricing sid of this vw item pricing.
	*
	* @return the item pricing sid of this vw item pricing
	*/
	@Override
	public int getItemPricingSid() {
		return _vwItemPricing.getItemPricingSid();
	}

	/**
	* Returns the item uom of this vw item pricing.
	*
	* @return the item uom of this vw item pricing
	*/
	@Override
	public java.lang.String getItemUom() {
		return _vwItemPricing.getItemUom();
	}

	/**
	* Returns the modified by of this vw item pricing.
	*
	* @return the modified by of this vw item pricing
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwItemPricing.getModifiedBy();
	}

	/**
	* Returns the modified date of this vw item pricing.
	*
	* @return the modified date of this vw item pricing
	*/
	@Override
	public Date getModifiedDate() {
		return _vwItemPricing.getModifiedDate();
	}

	/**
	* Returns the pricing code qualifier of this vw item pricing.
	*
	* @return the pricing code qualifier of this vw item pricing
	*/
	@Override
	public java.lang.String getPricingCodeQualifier() {
		return _vwItemPricing.getPricingCodeQualifier();
	}

	/**
	* Returns the pricing code qualifier name of this vw item pricing.
	*
	* @return the pricing code qualifier name of this vw item pricing
	*/
	@Override
	public java.lang.String getPricingCodeQualifierName() {
		return _vwItemPricing.getPricingCodeQualifierName();
	}

	/**
	* Returns the pricing code status of this vw item pricing.
	*
	* @return the pricing code status of this vw item pricing
	*/
	@Override
	public java.lang.String getPricingCodeStatus() {
		return _vwItemPricing.getPricingCodeStatus();
	}

	/**
	* Returns the primary key of this vw item pricing.
	*
	* @return the primary key of this vw item pricing
	*/
	@Override
	public int getPrimaryKey() {
		return _vwItemPricing.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwItemPricing.getPrimaryKeyObj();
	}

	/**
	* Returns the source of this vw item pricing.
	*
	* @return the source of this vw item pricing
	*/
	@Override
	public java.lang.String getSource() {
		return _vwItemPricing.getSource();
	}

	/**
	* Returns the start date of this vw item pricing.
	*
	* @return the start date of this vw item pricing
	*/
	@Override
	public Date getStartDate() {
		return _vwItemPricing.getStartDate();
	}

	@Override
	public int hashCode() {
		return _vwItemPricing.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwItemPricing.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwItemPricing.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwItemPricing.isNew();
	}

	@Override
	public void persist() {
		_vwItemPricing.persist();
	}

	/**
	* Sets the add chg del indicator of this vw item pricing.
	*
	* @param addChgDelIndicator the add chg del indicator of this vw item pricing
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_vwItemPricing.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this vw item pricing.
	*
	* @param batchId the batch ID of this vw item pricing
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwItemPricing.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwItemPricing.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this vw item pricing.
	*
	* @param createdBy the created by of this vw item pricing
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwItemPricing.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vw item pricing.
	*
	* @param createdDate the created date of this vw item pricing
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwItemPricing.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this vw item pricing.
	*
	* @param endDate the end date of this vw item pricing
	*/
	@Override
	public void setEndDate(Date endDate) {
		_vwItemPricing.setEndDate(endDate);
	}

	/**
	* Sets the entity code of this vw item pricing.
	*
	* @param entityCode the entity code of this vw item pricing
	*/
	@Override
	public void setEntityCode(java.lang.String entityCode) {
		_vwItemPricing.setEntityCode(entityCode);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwItemPricing.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwItemPricing.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwItemPricing.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item ID of this vw item pricing.
	*
	* @param itemId the item ID of this vw item pricing
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_vwItemPricing.setItemId(itemId);
	}

	/**
	* Sets the item name of this vw item pricing.
	*
	* @param itemName the item name of this vw item pricing
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_vwItemPricing.setItemName(itemName);
	}

	/**
	* Sets the item no of this vw item pricing.
	*
	* @param itemNo the item no of this vw item pricing
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_vwItemPricing.setItemNo(itemNo);
	}

	/**
	* Sets the item price of this vw item pricing.
	*
	* @param itemPrice the item price of this vw item pricing
	*/
	@Override
	public void setItemPrice(java.lang.String itemPrice) {
		_vwItemPricing.setItemPrice(itemPrice);
	}

	/**
	* Sets the item priceprecision of this vw item pricing.
	*
	* @param itemPriceprecision the item priceprecision of this vw item pricing
	*/
	@Override
	public void setItemPriceprecision(int itemPriceprecision) {
		_vwItemPricing.setItemPriceprecision(itemPriceprecision);
	}

	/**
	* Sets the item pricing sid of this vw item pricing.
	*
	* @param itemPricingSid the item pricing sid of this vw item pricing
	*/
	@Override
	public void setItemPricingSid(int itemPricingSid) {
		_vwItemPricing.setItemPricingSid(itemPricingSid);
	}

	/**
	* Sets the item uom of this vw item pricing.
	*
	* @param itemUom the item uom of this vw item pricing
	*/
	@Override
	public void setItemUom(java.lang.String itemUom) {
		_vwItemPricing.setItemUom(itemUom);
	}

	/**
	* Sets the modified by of this vw item pricing.
	*
	* @param modifiedBy the modified by of this vw item pricing
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwItemPricing.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vw item pricing.
	*
	* @param modifiedDate the modified date of this vw item pricing
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwItemPricing.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_vwItemPricing.setNew(n);
	}

	/**
	* Sets the pricing code qualifier of this vw item pricing.
	*
	* @param pricingCodeQualifier the pricing code qualifier of this vw item pricing
	*/
	@Override
	public void setPricingCodeQualifier(java.lang.String pricingCodeQualifier) {
		_vwItemPricing.setPricingCodeQualifier(pricingCodeQualifier);
	}

	/**
	* Sets the pricing code qualifier name of this vw item pricing.
	*
	* @param pricingCodeQualifierName the pricing code qualifier name of this vw item pricing
	*/
	@Override
	public void setPricingCodeQualifierName(
		java.lang.String pricingCodeQualifierName) {
		_vwItemPricing.setPricingCodeQualifierName(pricingCodeQualifierName);
	}

	/**
	* Sets the pricing code status of this vw item pricing.
	*
	* @param pricingCodeStatus the pricing code status of this vw item pricing
	*/
	@Override
	public void setPricingCodeStatus(java.lang.String pricingCodeStatus) {
		_vwItemPricing.setPricingCodeStatus(pricingCodeStatus);
	}

	/**
	* Sets the primary key of this vw item pricing.
	*
	* @param primaryKey the primary key of this vw item pricing
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwItemPricing.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwItemPricing.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the source of this vw item pricing.
	*
	* @param source the source of this vw item pricing
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwItemPricing.setSource(source);
	}

	/**
	* Sets the start date of this vw item pricing.
	*
	* @param startDate the start date of this vw item pricing
	*/
	@Override
	public void setStartDate(Date startDate) {
		_vwItemPricing.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwItemPricing> toCacheModel() {
		return _vwItemPricing.toCacheModel();
	}

	@Override
	public VwItemPricing toEscapedModel() {
		return new VwItemPricingWrapper(_vwItemPricing.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwItemPricing.toString();
	}

	@Override
	public VwItemPricing toUnescapedModel() {
		return new VwItemPricingWrapper(_vwItemPricing.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwItemPricing.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwItemPricingWrapper)) {
			return false;
		}

		VwItemPricingWrapper vwItemPricingWrapper = (VwItemPricingWrapper)obj;

		if (Objects.equals(_vwItemPricing, vwItemPricingWrapper._vwItemPricing)) {
			return true;
		}

		return false;
	}

	@Override
	public VwItemPricing getWrappedModel() {
		return _vwItemPricing;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwItemPricing.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwItemPricing.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwItemPricing.resetOriginalValues();
	}

	private final VwItemPricing _vwItemPricing;
}