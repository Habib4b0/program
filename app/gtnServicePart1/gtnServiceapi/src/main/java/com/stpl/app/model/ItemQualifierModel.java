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
 * The base model interface for the ItemQualifier service. Represents a row in the &quot;ITEM_QUALIFIER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ItemQualifierModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ItemQualifierImpl}.
 * </p>
 *
 * @author
 * @see ItemQualifier
 * @see com.stpl.app.model.impl.ItemQualifierImpl
 * @see com.stpl.app.model.impl.ItemQualifierModelImpl
 * @generated
 */
@ProviderType
public interface ItemQualifierModel extends BaseModel<ItemQualifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a item qualifier model instance should use the {@link ItemQualifier} interface instead.
	 */

	/**
	 * Returns the primary key of this item qualifier.
	 *
	 * @return the primary key of this item qualifier
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this item qualifier.
	 *
	 * @param primaryKey the primary key of this item qualifier
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the created by of this item qualifier.
	 *
	 * @return the created by of this item qualifier
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this item qualifier.
	 *
	 * @param createdBy the created by of this item qualifier
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the item qualifier sid of this item qualifier.
	 *
	 * @return the item qualifier sid of this item qualifier
	 */
	public int getItemQualifierSid();

	/**
	 * Sets the item qualifier sid of this item qualifier.
	 *
	 * @param itemQualifierSid the item qualifier sid of this item qualifier
	 */
	public void setItemQualifierSid(int itemQualifierSid);

	/**
	 * Returns the specific entity code of this item qualifier.
	 *
	 * @return the specific entity code of this item qualifier
	 */
	@AutoEscape
	public String getSpecificEntityCode();

	/**
	 * Sets the specific entity code of this item qualifier.
	 *
	 * @param specificEntityCode the specific entity code of this item qualifier
	 */
	public void setSpecificEntityCode(String specificEntityCode);

	/**
	 * Returns the item qualifier name of this item qualifier.
	 *
	 * @return the item qualifier name of this item qualifier
	 */
	@AutoEscape
	public String getItemQualifierName();

	/**
	 * Sets the item qualifier name of this item qualifier.
	 *
	 * @param itemQualifierName the item qualifier name of this item qualifier
	 */
	public void setItemQualifierName(String itemQualifierName);

	/**
	 * Returns the modified by of this item qualifier.
	 *
	 * @return the modified by of this item qualifier
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this item qualifier.
	 *
	 * @param modifiedBy the modified by of this item qualifier
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the created date of this item qualifier.
	 *
	 * @return the created date of this item qualifier
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this item qualifier.
	 *
	 * @param createdDate the created date of this item qualifier
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the batch ID of this item qualifier.
	 *
	 * @return the batch ID of this item qualifier
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this item qualifier.
	 *
	 * @param batchId the batch ID of this item qualifier
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the modified date of this item qualifier.
	 *
	 * @return the modified date of this item qualifier
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this item qualifier.
	 *
	 * @param modifiedDate the modified date of this item qualifier
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the effective dates of this item qualifier.
	 *
	 * @return the effective dates of this item qualifier
	 */
	@AutoEscape
	public String getEffectiveDates();

	/**
	 * Sets the effective dates of this item qualifier.
	 *
	 * @param effectiveDates the effective dates of this item qualifier
	 */
	public void setEffectiveDates(String effectiveDates);

	/**
	 * Returns the notes of this item qualifier.
	 *
	 * @return the notes of this item qualifier
	 */
	@AutoEscape
	public String getNotes();

	/**
	 * Sets the notes of this item qualifier.
	 *
	 * @param notes the notes of this item qualifier
	 */
	public void setNotes(String notes);

	/**
	 * Returns the item qualifier value of this item qualifier.
	 *
	 * @return the item qualifier value of this item qualifier
	 */
	@AutoEscape
	public String getItemQualifierValue();

	/**
	 * Sets the item qualifier value of this item qualifier.
	 *
	 * @param itemQualifierValue the item qualifier value of this item qualifier
	 */
	public void setItemQualifierValue(String itemQualifierValue);

	/**
	 * Returns the record lock status of this item qualifier.
	 *
	 * @return the record lock status of this item qualifier
	 */
	public boolean getRecordLockStatus();

	/**
	 * Returns <code>true</code> if this item qualifier is record lock status.
	 *
	 * @return <code>true</code> if this item qualifier is record lock status; <code>false</code> otherwise
	 */
	public boolean isRecordLockStatus();

	/**
	 * Sets whether this item qualifier is record lock status.
	 *
	 * @param recordLockStatus the record lock status of this item qualifier
	 */
	public void setRecordLockStatus(boolean recordLockStatus);

	/**
	 * Returns the source of this item qualifier.
	 *
	 * @return the source of this item qualifier
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this item qualifier.
	 *
	 * @param source the source of this item qualifier
	 */
	public void setSource(String source);

	/**
	 * Returns the inbound status of this item qualifier.
	 *
	 * @return the inbound status of this item qualifier
	 */
	@AutoEscape
	public String getInboundStatus();

	/**
	 * Sets the inbound status of this item qualifier.
	 *
	 * @param inboundStatus the inbound status of this item qualifier
	 */
	public void setInboundStatus(String inboundStatus);

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
	public int compareTo(ItemQualifier itemQualifier);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ItemQualifier> toCacheModel();

	@Override
	public ItemQualifier toEscapedModel();

	@Override
	public ItemQualifier toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}