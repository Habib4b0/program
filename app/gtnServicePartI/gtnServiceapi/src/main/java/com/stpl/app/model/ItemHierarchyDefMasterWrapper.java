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
 * This class is a wrapper for {@link ItemHierarchyDefMaster}.
 * </p>
 *
 * @author
 * @see ItemHierarchyDefMaster
 * @generated
 */
@ProviderType
public class ItemHierarchyDefMasterWrapper implements ItemHierarchyDefMaster,
	ModelWrapper<ItemHierarchyDefMaster> {
	public ItemHierarchyDefMasterWrapper(
		ItemHierarchyDefMaster itemHierarchyDefMaster) {
		_itemHierarchyDefMaster = itemHierarchyDefMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ItemHierarchyDefMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ItemHierarchyDefMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemHierarchyDefMasterSid",
			getItemHierarchyDefMasterSid());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("alias", getAlias());
		attributes.put("source", getSource());
		attributes.put("batchId", getBatchId());
		attributes.put("bpiLvl", getBpiLvl());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("member", getMember());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemHierarchyDefMasterSid = (Integer)attributes.get(
				"itemHierarchyDefMasterSid");

		if (itemHierarchyDefMasterSid != null) {
			setItemHierarchyDefMasterSid(itemHierarchyDefMasterSid);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String alias = (String)attributes.get("alias");

		if (alias != null) {
			setAlias(alias);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String bpiLvl = (String)attributes.get("bpiLvl");

		if (bpiLvl != null) {
			setBpiLvl(bpiLvl);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String member = (String)attributes.get("member");

		if (member != null) {
			setMember(member);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ItemHierarchyDefMasterWrapper((ItemHierarchyDefMaster)_itemHierarchyDefMaster.clone());
	}

	@Override
	public int compareTo(ItemHierarchyDefMaster itemHierarchyDefMaster) {
		return _itemHierarchyDefMaster.compareTo(itemHierarchyDefMaster);
	}

	/**
	* Returns the alias of this item hierarchy def master.
	*
	* @return the alias of this item hierarchy def master
	*/
	@Override
	public java.lang.String getAlias() {
		return _itemHierarchyDefMaster.getAlias();
	}

	/**
	* Returns the batch ID of this item hierarchy def master.
	*
	* @return the batch ID of this item hierarchy def master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _itemHierarchyDefMaster.getBatchId();
	}

	/**
	* Returns the bpi lvl of this item hierarchy def master.
	*
	* @return the bpi lvl of this item hierarchy def master
	*/
	@Override
	public java.lang.String getBpiLvl() {
		return _itemHierarchyDefMaster.getBpiLvl();
	}

	/**
	* Returns the created by of this item hierarchy def master.
	*
	* @return the created by of this item hierarchy def master
	*/
	@Override
	public int getCreatedBy() {
		return _itemHierarchyDefMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this item hierarchy def master.
	*
	* @return the created date of this item hierarchy def master
	*/
	@Override
	public Date getCreatedDate() {
		return _itemHierarchyDefMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _itemHierarchyDefMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this item hierarchy def master.
	*
	* @return the inbound status of this item hierarchy def master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _itemHierarchyDefMaster.getInboundStatus();
	}

	/**
	* Returns the item hierarchy def master sid of this item hierarchy def master.
	*
	* @return the item hierarchy def master sid of this item hierarchy def master
	*/
	@Override
	public int getItemHierarchyDefMasterSid() {
		return _itemHierarchyDefMaster.getItemHierarchyDefMasterSid();
	}

	/**
	* Returns the member of this item hierarchy def master.
	*
	* @return the member of this item hierarchy def master
	*/
	@Override
	public java.lang.String getMember() {
		return _itemHierarchyDefMaster.getMember();
	}

	/**
	* Returns the modified by of this item hierarchy def master.
	*
	* @return the modified by of this item hierarchy def master
	*/
	@Override
	public int getModifiedBy() {
		return _itemHierarchyDefMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this item hierarchy def master.
	*
	* @return the modified date of this item hierarchy def master
	*/
	@Override
	public Date getModifiedDate() {
		return _itemHierarchyDefMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this item hierarchy def master.
	*
	* @return the primary key of this item hierarchy def master
	*/
	@Override
	public int getPrimaryKey() {
		return _itemHierarchyDefMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemHierarchyDefMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this item hierarchy def master.
	*
	* @return the record lock status of this item hierarchy def master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _itemHierarchyDefMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this item hierarchy def master.
	*
	* @return the source of this item hierarchy def master
	*/
	@Override
	public java.lang.String getSource() {
		return _itemHierarchyDefMaster.getSource();
	}

	@Override
	public int hashCode() {
		return _itemHierarchyDefMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _itemHierarchyDefMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _itemHierarchyDefMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _itemHierarchyDefMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this item hierarchy def master is record lock status.
	*
	* @return <code>true</code> if this item hierarchy def master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _itemHierarchyDefMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_itemHierarchyDefMaster.persist();
	}

	/**
	* Sets the alias of this item hierarchy def master.
	*
	* @param alias the alias of this item hierarchy def master
	*/
	@Override
	public void setAlias(java.lang.String alias) {
		_itemHierarchyDefMaster.setAlias(alias);
	}

	/**
	* Sets the batch ID of this item hierarchy def master.
	*
	* @param batchId the batch ID of this item hierarchy def master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_itemHierarchyDefMaster.setBatchId(batchId);
	}

	/**
	* Sets the bpi lvl of this item hierarchy def master.
	*
	* @param bpiLvl the bpi lvl of this item hierarchy def master
	*/
	@Override
	public void setBpiLvl(java.lang.String bpiLvl) {
		_itemHierarchyDefMaster.setBpiLvl(bpiLvl);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_itemHierarchyDefMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this item hierarchy def master.
	*
	* @param createdBy the created by of this item hierarchy def master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_itemHierarchyDefMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this item hierarchy def master.
	*
	* @param createdDate the created date of this item hierarchy def master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_itemHierarchyDefMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_itemHierarchyDefMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_itemHierarchyDefMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_itemHierarchyDefMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this item hierarchy def master.
	*
	* @param inboundStatus the inbound status of this item hierarchy def master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_itemHierarchyDefMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item hierarchy def master sid of this item hierarchy def master.
	*
	* @param itemHierarchyDefMasterSid the item hierarchy def master sid of this item hierarchy def master
	*/
	@Override
	public void setItemHierarchyDefMasterSid(int itemHierarchyDefMasterSid) {
		_itemHierarchyDefMaster.setItemHierarchyDefMasterSid(itemHierarchyDefMasterSid);
	}

	/**
	* Sets the member of this item hierarchy def master.
	*
	* @param member the member of this item hierarchy def master
	*/
	@Override
	public void setMember(java.lang.String member) {
		_itemHierarchyDefMaster.setMember(member);
	}

	/**
	* Sets the modified by of this item hierarchy def master.
	*
	* @param modifiedBy the modified by of this item hierarchy def master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_itemHierarchyDefMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this item hierarchy def master.
	*
	* @param modifiedDate the modified date of this item hierarchy def master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_itemHierarchyDefMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_itemHierarchyDefMaster.setNew(n);
	}

	/**
	* Sets the primary key of this item hierarchy def master.
	*
	* @param primaryKey the primary key of this item hierarchy def master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_itemHierarchyDefMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_itemHierarchyDefMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this item hierarchy def master is record lock status.
	*
	* @param recordLockStatus the record lock status of this item hierarchy def master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_itemHierarchyDefMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this item hierarchy def master.
	*
	* @param source the source of this item hierarchy def master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_itemHierarchyDefMaster.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ItemHierarchyDefMaster> toCacheModel() {
		return _itemHierarchyDefMaster.toCacheModel();
	}

	@Override
	public ItemHierarchyDefMaster toEscapedModel() {
		return new ItemHierarchyDefMasterWrapper(_itemHierarchyDefMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _itemHierarchyDefMaster.toString();
	}

	@Override
	public ItemHierarchyDefMaster toUnescapedModel() {
		return new ItemHierarchyDefMasterWrapper(_itemHierarchyDefMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _itemHierarchyDefMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemHierarchyDefMasterWrapper)) {
			return false;
		}

		ItemHierarchyDefMasterWrapper itemHierarchyDefMasterWrapper = (ItemHierarchyDefMasterWrapper)obj;

		if (Objects.equals(_itemHierarchyDefMaster,
					itemHierarchyDefMasterWrapper._itemHierarchyDefMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemHierarchyDefMaster getWrappedModel() {
		return _itemHierarchyDefMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _itemHierarchyDefMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _itemHierarchyDefMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_itemHierarchyDefMaster.resetOriginalValues();
	}

	private final ItemHierarchyDefMaster _itemHierarchyDefMaster;
}