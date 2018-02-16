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
 * This class is a wrapper for {@link ItemGroup}.
 * </p>
 *
 * @author
 * @see ItemGroup
 * @generated
 */
@ProviderType
public class ItemGroupWrapper implements ItemGroup, ModelWrapper<ItemGroup> {
	public ItemGroupWrapper(ItemGroup itemGroup) {
		_itemGroup = itemGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return ItemGroup.class;
	}

	@Override
	public String getModelClassName() {
		return ItemGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("itemGroupNo", getItemGroupNo());
		attributes.put("versionNo", getVersionNo());
		attributes.put("itemFilter", getItemFilter());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("itemGroupDescription", getItemGroupDescription());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemGroupName", getItemGroupName());
		attributes.put("itemGroupSid", getItemGroupSid());
		attributes.put("companyMasterSid", getCompanyMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String itemGroupNo = (String)attributes.get("itemGroupNo");

		if (itemGroupNo != null) {
			setItemGroupNo(itemGroupNo);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		String itemFilter = (String)attributes.get("itemFilter");

		if (itemFilter != null) {
			setItemFilter(itemFilter);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String itemGroupDescription = (String)attributes.get(
				"itemGroupDescription");

		if (itemGroupDescription != null) {
			setItemGroupDescription(itemGroupDescription);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String itemGroupName = (String)attributes.get("itemGroupName");

		if (itemGroupName != null) {
			setItemGroupName(itemGroupName);
		}

		Integer itemGroupSid = (Integer)attributes.get("itemGroupSid");

		if (itemGroupSid != null) {
			setItemGroupSid(itemGroupSid);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ItemGroupWrapper((ItemGroup)_itemGroup.clone());
	}

	@Override
	public int compareTo(ItemGroup itemGroup) {
		return _itemGroup.compareTo(itemGroup);
	}

	/**
	* Returns the company master sid of this item group.
	*
	* @return the company master sid of this item group
	*/
	@Override
	public int getCompanyMasterSid() {
		return _itemGroup.getCompanyMasterSid();
	}

	/**
	* Returns the created by of this item group.
	*
	* @return the created by of this item group
	*/
	@Override
	public int getCreatedBy() {
		return _itemGroup.getCreatedBy();
	}

	/**
	* Returns the created date of this item group.
	*
	* @return the created date of this item group
	*/
	@Override
	public Date getCreatedDate() {
		return _itemGroup.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _itemGroup.getExpandoBridge();
	}

	/**
	* Returns the item filter of this item group.
	*
	* @return the item filter of this item group
	*/
	@Override
	public java.lang.String getItemFilter() {
		return _itemGroup.getItemFilter();
	}

	/**
	* Returns the item group description of this item group.
	*
	* @return the item group description of this item group
	*/
	@Override
	public java.lang.String getItemGroupDescription() {
		return _itemGroup.getItemGroupDescription();
	}

	/**
	* Returns the item group name of this item group.
	*
	* @return the item group name of this item group
	*/
	@Override
	public java.lang.String getItemGroupName() {
		return _itemGroup.getItemGroupName();
	}

	/**
	* Returns the item group no of this item group.
	*
	* @return the item group no of this item group
	*/
	@Override
	public java.lang.String getItemGroupNo() {
		return _itemGroup.getItemGroupNo();
	}

	/**
	* Returns the item group sid of this item group.
	*
	* @return the item group sid of this item group
	*/
	@Override
	public int getItemGroupSid() {
		return _itemGroup.getItemGroupSid();
	}

	/**
	* Returns the modified by of this item group.
	*
	* @return the modified by of this item group
	*/
	@Override
	public int getModifiedBy() {
		return _itemGroup.getModifiedBy();
	}

	/**
	* Returns the modified date of this item group.
	*
	* @return the modified date of this item group
	*/
	@Override
	public Date getModifiedDate() {
		return _itemGroup.getModifiedDate();
	}

	/**
	* Returns the primary key of this item group.
	*
	* @return the primary key of this item group
	*/
	@Override
	public int getPrimaryKey() {
		return _itemGroup.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemGroup.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this item group.
	*
	* @return the version no of this item group
	*/
	@Override
	public int getVersionNo() {
		return _itemGroup.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _itemGroup.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _itemGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _itemGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _itemGroup.isNew();
	}

	@Override
	public void persist() {
		_itemGroup.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_itemGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the company master sid of this item group.
	*
	* @param companyMasterSid the company master sid of this item group
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_itemGroup.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the created by of this item group.
	*
	* @param createdBy the created by of this item group
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_itemGroup.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this item group.
	*
	* @param createdDate the created date of this item group
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_itemGroup.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_itemGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_itemGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_itemGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item filter of this item group.
	*
	* @param itemFilter the item filter of this item group
	*/
	@Override
	public void setItemFilter(java.lang.String itemFilter) {
		_itemGroup.setItemFilter(itemFilter);
	}

	/**
	* Sets the item group description of this item group.
	*
	* @param itemGroupDescription the item group description of this item group
	*/
	@Override
	public void setItemGroupDescription(java.lang.String itemGroupDescription) {
		_itemGroup.setItemGroupDescription(itemGroupDescription);
	}

	/**
	* Sets the item group name of this item group.
	*
	* @param itemGroupName the item group name of this item group
	*/
	@Override
	public void setItemGroupName(java.lang.String itemGroupName) {
		_itemGroup.setItemGroupName(itemGroupName);
	}

	/**
	* Sets the item group no of this item group.
	*
	* @param itemGroupNo the item group no of this item group
	*/
	@Override
	public void setItemGroupNo(java.lang.String itemGroupNo) {
		_itemGroup.setItemGroupNo(itemGroupNo);
	}

	/**
	* Sets the item group sid of this item group.
	*
	* @param itemGroupSid the item group sid of this item group
	*/
	@Override
	public void setItemGroupSid(int itemGroupSid) {
		_itemGroup.setItemGroupSid(itemGroupSid);
	}

	/**
	* Sets the modified by of this item group.
	*
	* @param modifiedBy the modified by of this item group
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_itemGroup.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this item group.
	*
	* @param modifiedDate the modified date of this item group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_itemGroup.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_itemGroup.setNew(n);
	}

	/**
	* Sets the primary key of this item group.
	*
	* @param primaryKey the primary key of this item group
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_itemGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_itemGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this item group.
	*
	* @param versionNo the version no of this item group
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_itemGroup.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ItemGroup> toCacheModel() {
		return _itemGroup.toCacheModel();
	}

	@Override
	public ItemGroup toEscapedModel() {
		return new ItemGroupWrapper(_itemGroup.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _itemGroup.toString();
	}

	@Override
	public ItemGroup toUnescapedModel() {
		return new ItemGroupWrapper(_itemGroup.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _itemGroup.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemGroupWrapper)) {
			return false;
		}

		ItemGroupWrapper itemGroupWrapper = (ItemGroupWrapper)obj;

		if (Objects.equals(_itemGroup, itemGroupWrapper._itemGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemGroup getWrappedModel() {
		return _itemGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _itemGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _itemGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_itemGroup.resetOriginalValues();
	}

	private final ItemGroup _itemGroup;
}