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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ItemPricing service. Represents a row in the &quot;ITEM_PRICING&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ItemPricingModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ItemPricingImpl}.
 * </p>
 *
 * @author
 * @see ItemPricing
 * @see com.stpl.app.model.impl.ItemPricingImpl
 * @see com.stpl.app.model.impl.ItemPricingModelImpl
 * @generated
 */
@ProviderType
public interface ItemPricingModel extends BaseModel<ItemPricing> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a item pricing model instance should use the {@link ItemPricing} interface instead.
	 */

	/**
	 * Returns the primary key of this item pricing.
	 *
	 * @return the primary key of this item pricing
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this item pricing.
	 *
	 * @param primaryKey the primary key of this item pricing
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the item master sid of this item pricing.
	 *
	 * @return the item master sid of this item pricing
	 */
	public int getItemMasterSid();

	/**
	 * Sets the item master sid of this item pricing.
	 *
	 * @param itemMasterSid the item master sid of this item pricing
	 */
	public void setItemMasterSid(int itemMasterSid);

	/**
	 * Returns the item pricing qualifier sid of this item pricing.
	 *
	 * @return the item pricing qualifier sid of this item pricing
	 */
	public int getItemPricingQualifierSid();

	/**
	 * Sets the item pricing qualifier sid of this item pricing.
	 *
	 * @param itemPricingQualifierSid the item pricing qualifier sid of this item pricing
	 */
	public void setItemPricingQualifierSid(int itemPricingQualifierSid);

	/**
	 * Returns the item price of this item pricing.
	 *
	 * @return the item price of this item pricing
	 */
	public double getItemPrice();

	/**
	 * Sets the item price of this item pricing.
	 *
	 * @param itemPrice the item price of this item pricing
	 */
	public void setItemPrice(double itemPrice);

	/**
	 * Returns the end date of this item pricing.
	 *
	 * @return the end date of this item pricing
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this item pricing.
	 *
	 * @param endDate the end date of this item pricing
	 */
	public void setEndDate(Date endDate);

	/**
	 * Returns the modified date of this item pricing.
	 *
	 * @return the modified date of this item pricing
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this item pricing.
	 *
	 * @param modifiedDate the modified date of this item pricing
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the entity code of this item pricing.
	 *
	 * @return the entity code of this item pricing
	 */
	@AutoEscape
	public String getEntityCode();

	/**
	 * Sets the entity code of this item pricing.
	 *
	 * @param entityCode the entity code of this item pricing
	 */
	public void setEntityCode(String entityCode);

	/**
	 * Returns the record lock status of this item pricing.
	 *
	 * @return the record lock status of this item pricing
	 */
	public boolean getRecordLockStatus();

	/**
	 * Returns <code>true</code> if this item pricing is record lock status.
	 *
	 * @return <code>true</code> if this item pricing is record lock status; <code>false</code> otherwise
	 */
	public boolean isRecordLockStatus();

	/**
	 * Sets whether this item pricing is record lock status.
	 *
	 * @param recordLockStatus the record lock status of this item pricing
	 */
	public void setRecordLockStatus(boolean recordLockStatus);

	/**
	 * Returns the start date of this item pricing.
	 *
	 * @return the start date of this item pricing
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this item pricing.
	 *
	 * @param startDate the start date of this item pricing
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the created date of this item pricing.
	 *
	 * @return the created date of this item pricing
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this item pricing.
	 *
	 * @param createdDate the created date of this item pricing
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the created by of this item pricing.
	 *
	 * @return the created by of this item pricing
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this item pricing.
	 *
	 * @param createdBy the created by of this item pricing
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the source of this item pricing.
	 *
	 * @return the source of this item pricing
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this item pricing.
	 *
	 * @param source the source of this item pricing
	 */
	public void setSource(String source);

	/**
	 * Returns the batch ID of this item pricing.
	 *
	 * @return the batch ID of this item pricing
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this item pricing.
	 *
	 * @param batchId the batch ID of this item pricing
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the item uom of this item pricing.
	 *
	 * @return the item uom of this item pricing
	 */
	public int getItemUom();

	/**
	 * Sets the item uom of this item pricing.
	 *
	 * @param itemUom the item uom of this item pricing
	 */
	public void setItemUom(int itemUom);

	/**
	 * Returns the modified by of this item pricing.
	 *
	 * @return the modified by of this item pricing
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this item pricing.
	 *
	 * @param modifiedBy the modified by of this item pricing
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the inbound status of this item pricing.
	 *
	 * @return the inbound status of this item pricing
	 */
	@AutoEscape
	public String getInboundStatus();

	/**
	 * Sets the inbound status of this item pricing.
	 *
	 * @param inboundStatus the inbound status of this item pricing
	 */
	public void setInboundStatus(String inboundStatus);

	/**
	 * Returns the item pricing sid of this item pricing.
	 *
	 * @return the item pricing sid of this item pricing
	 */
	public int getItemPricingSid();

	/**
	 * Sets the item pricing sid of this item pricing.
	 *
	 * @param itemPricingSid the item pricing sid of this item pricing
	 */
	public void setItemPricingSid(int itemPricingSid);

	/**
	 * Returns the pricing code status of this item pricing.
	 *
	 * @return the pricing code status of this item pricing
	 */
	public int getPricingCodeStatus();

	/**
	 * Sets the pricing code status of this item pricing.
	 *
	 * @param pricingCodeStatus the pricing code status of this item pricing
	 */
	public void setPricingCodeStatus(int pricingCodeStatus);

	/**
	 * Returns the item price precision of this item pricing.
	 *
	 * @return the item price precision of this item pricing
	 */
	public int getItemPricePrecision();

	/**
	 * Sets the item price precision of this item pricing.
	 *
	 * @param itemPricePrecision the item price precision of this item pricing
	 */
	public void setItemPricePrecision(int itemPricePrecision);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(ItemPricing itemPricing);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ItemPricing> toCacheModel();

	@Override
	public ItemPricing toEscapedModel();

	@Override
	public ItemPricing toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}