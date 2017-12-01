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
 * This class is a wrapper for {@link ItemGroupDetails}.
 * </p>
 *
 * @author
 * @see ItemGroupDetails
 * @generated
 */
@ProviderType
public class ItemGroupDetailsWrapper implements ItemGroupDetails,
	ModelWrapper<ItemGroupDetails> {
	public ItemGroupDetailsWrapper(ItemGroupDetails itemGroupDetails) {
		_itemGroupDetails = itemGroupDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return ItemGroupDetails.class;
	}

	@Override
	public String getModelClassName() {
		return ItemGroupDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemGroupDetailsSid", getItemGroupDetailsSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemGroupSid", getItemGroupSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemGroupDetailsSid = (Integer)attributes.get(
				"itemGroupDetailsSid");

		if (itemGroupDetailsSid != null) {
			setItemGroupDetailsSid(itemGroupDetailsSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer itemGroupSid = (Integer)attributes.get("itemGroupSid");

		if (itemGroupSid != null) {
			setItemGroupSid(itemGroupSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ItemGroupDetailsWrapper((ItemGroupDetails)_itemGroupDetails.clone());
	}

	@Override
	public int compareTo(ItemGroupDetails itemGroupDetails) {
		return _itemGroupDetails.compareTo(itemGroupDetails);
	}

	/**
	* Returns the created by of this item group details.
	*
	* @return the created by of this item group details
	*/
	@Override
	public int getCreatedBy() {
		return _itemGroupDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this item group details.
	*
	* @return the created date of this item group details
	*/
	@Override
	public Date getCreatedDate() {
		return _itemGroupDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _itemGroupDetails.getExpandoBridge();
	}

	/**
	* Returns the item group details sid of this item group details.
	*
	* @return the item group details sid of this item group details
	*/
	@Override
	public int getItemGroupDetailsSid() {
		return _itemGroupDetails.getItemGroupDetailsSid();
	}

	/**
	* Returns the item group sid of this item group details.
	*
	* @return the item group sid of this item group details
	*/
	@Override
	public int getItemGroupSid() {
		return _itemGroupDetails.getItemGroupSid();
	}

	/**
	* Returns the item master sid of this item group details.
	*
	* @return the item master sid of this item group details
	*/
	@Override
	public int getItemMasterSid() {
		return _itemGroupDetails.getItemMasterSid();
	}

	/**
	* Returns the modified by of this item group details.
	*
	* @return the modified by of this item group details
	*/
	@Override
	public int getModifiedBy() {
		return _itemGroupDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this item group details.
	*
	* @return the modified date of this item group details
	*/
	@Override
	public Date getModifiedDate() {
		return _itemGroupDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this item group details.
	*
	* @return the primary key of this item group details
	*/
	@Override
	public int getPrimaryKey() {
		return _itemGroupDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemGroupDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this item group details.
	*
	* @return the version no of this item group details
	*/
	@Override
	public int getVersionNo() {
		return _itemGroupDetails.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _itemGroupDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _itemGroupDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _itemGroupDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _itemGroupDetails.isNew();
	}

	@Override
	public void persist() {
		_itemGroupDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_itemGroupDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this item group details.
	*
	* @param createdBy the created by of this item group details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_itemGroupDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this item group details.
	*
	* @param createdDate the created date of this item group details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_itemGroupDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_itemGroupDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_itemGroupDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_itemGroupDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item group details sid of this item group details.
	*
	* @param itemGroupDetailsSid the item group details sid of this item group details
	*/
	@Override
	public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
		_itemGroupDetails.setItemGroupDetailsSid(itemGroupDetailsSid);
	}

	/**
	* Sets the item group sid of this item group details.
	*
	* @param itemGroupSid the item group sid of this item group details
	*/
	@Override
	public void setItemGroupSid(int itemGroupSid) {
		_itemGroupDetails.setItemGroupSid(itemGroupSid);
	}

	/**
	* Sets the item master sid of this item group details.
	*
	* @param itemMasterSid the item master sid of this item group details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_itemGroupDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this item group details.
	*
	* @param modifiedBy the modified by of this item group details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_itemGroupDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this item group details.
	*
	* @param modifiedDate the modified date of this item group details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_itemGroupDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_itemGroupDetails.setNew(n);
	}

	/**
	* Sets the primary key of this item group details.
	*
	* @param primaryKey the primary key of this item group details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_itemGroupDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_itemGroupDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this item group details.
	*
	* @param versionNo the version no of this item group details
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_itemGroupDetails.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ItemGroupDetails> toCacheModel() {
		return _itemGroupDetails.toCacheModel();
	}

	@Override
	public ItemGroupDetails toEscapedModel() {
		return new ItemGroupDetailsWrapper(_itemGroupDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _itemGroupDetails.toString();
	}

	@Override
	public ItemGroupDetails toUnescapedModel() {
		return new ItemGroupDetailsWrapper(_itemGroupDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _itemGroupDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemGroupDetailsWrapper)) {
			return false;
		}

		ItemGroupDetailsWrapper itemGroupDetailsWrapper = (ItemGroupDetailsWrapper)obj;

		if (Objects.equals(_itemGroupDetails,
					itemGroupDetailsWrapper._itemGroupDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemGroupDetails getWrappedModel() {
		return _itemGroupDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _itemGroupDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _itemGroupDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_itemGroupDetails.resetOriginalValues();
	}

	private final ItemGroupDetails _itemGroupDetails;
}