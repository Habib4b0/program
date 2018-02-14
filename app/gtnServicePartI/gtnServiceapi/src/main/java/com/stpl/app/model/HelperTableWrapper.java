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
 * This class is a wrapper for {@link HelperTable}.
 * </p>
 *
 * @author
 * @see HelperTable
 * @generated
 */
@ProviderType
public class HelperTableWrapper implements HelperTable,
	ModelWrapper<HelperTable> {
	public HelperTableWrapper(HelperTable helperTable) {
		_helperTable = helperTable;
	}

	@Override
	public Class<?> getModelClass() {
		return HelperTable.class;
	}

	@Override
	public String getModelClassName() {
		return HelperTable.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("description", getDescription());
		attributes.put("refCount", getRefCount());
		attributes.put("listName", getListName());
		attributes.put("helperTableSid", getHelperTableSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("aliasName", getAliasName());

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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer refCount = (Integer)attributes.get("refCount");

		if (refCount != null) {
			setRefCount(refCount);
		}

		String listName = (String)attributes.get("listName");

		if (listName != null) {
			setListName(listName);
		}

		Integer helperTableSid = (Integer)attributes.get("helperTableSid");

		if (helperTableSid != null) {
			setHelperTableSid(helperTableSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String aliasName = (String)attributes.get("aliasName");

		if (aliasName != null) {
			setAliasName(aliasName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HelperTableWrapper((HelperTable)_helperTable.clone());
	}

	@Override
	public int compareTo(HelperTable helperTable) {
		return _helperTable.compareTo(helperTable);
	}

	/**
	* Returns the alias name of this helper table.
	*
	* @return the alias name of this helper table
	*/
	@Override
	public java.lang.String getAliasName() {
		return _helperTable.getAliasName();
	}

	/**
	* Returns the created by of this helper table.
	*
	* @return the created by of this helper table
	*/
	@Override
	public int getCreatedBy() {
		return _helperTable.getCreatedBy();
	}

	/**
	* Returns the created date of this helper table.
	*
	* @return the created date of this helper table
	*/
	@Override
	public Date getCreatedDate() {
		return _helperTable.getCreatedDate();
	}

	/**
	* Returns the description of this helper table.
	*
	* @return the description of this helper table
	*/
	@Override
	public java.lang.String getDescription() {
		return _helperTable.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _helperTable.getExpandoBridge();
	}

	/**
	* Returns the helper table sid of this helper table.
	*
	* @return the helper table sid of this helper table
	*/
	@Override
	public int getHelperTableSid() {
		return _helperTable.getHelperTableSid();
	}

	/**
	* Returns the list name of this helper table.
	*
	* @return the list name of this helper table
	*/
	@Override
	public java.lang.String getListName() {
		return _helperTable.getListName();
	}

	/**
	* Returns the modified by of this helper table.
	*
	* @return the modified by of this helper table
	*/
	@Override
	public int getModifiedBy() {
		return _helperTable.getModifiedBy();
	}

	/**
	* Returns the modified date of this helper table.
	*
	* @return the modified date of this helper table
	*/
	@Override
	public Date getModifiedDate() {
		return _helperTable.getModifiedDate();
	}

	/**
	* Returns the primary key of this helper table.
	*
	* @return the primary key of this helper table
	*/
	@Override
	public int getPrimaryKey() {
		return _helperTable.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _helperTable.getPrimaryKeyObj();
	}

	/**
	* Returns the ref count of this helper table.
	*
	* @return the ref count of this helper table
	*/
	@Override
	public int getRefCount() {
		return _helperTable.getRefCount();
	}

	@Override
	public int hashCode() {
		return _helperTable.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _helperTable.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _helperTable.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _helperTable.isNew();
	}

	@Override
	public void persist() {
		_helperTable.persist();
	}

	/**
	* Sets the alias name of this helper table.
	*
	* @param aliasName the alias name of this helper table
	*/
	@Override
	public void setAliasName(java.lang.String aliasName) {
		_helperTable.setAliasName(aliasName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_helperTable.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this helper table.
	*
	* @param createdBy the created by of this helper table
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_helperTable.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this helper table.
	*
	* @param createdDate the created date of this helper table
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_helperTable.setCreatedDate(createdDate);
	}

	/**
	* Sets the description of this helper table.
	*
	* @param description the description of this helper table
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_helperTable.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_helperTable.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_helperTable.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_helperTable.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the helper table sid of this helper table.
	*
	* @param helperTableSid the helper table sid of this helper table
	*/
	@Override
	public void setHelperTableSid(int helperTableSid) {
		_helperTable.setHelperTableSid(helperTableSid);
	}

	/**
	* Sets the list name of this helper table.
	*
	* @param listName the list name of this helper table
	*/
	@Override
	public void setListName(java.lang.String listName) {
		_helperTable.setListName(listName);
	}

	/**
	* Sets the modified by of this helper table.
	*
	* @param modifiedBy the modified by of this helper table
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_helperTable.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this helper table.
	*
	* @param modifiedDate the modified date of this helper table
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_helperTable.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_helperTable.setNew(n);
	}

	/**
	* Sets the primary key of this helper table.
	*
	* @param primaryKey the primary key of this helper table
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_helperTable.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_helperTable.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ref count of this helper table.
	*
	* @param refCount the ref count of this helper table
	*/
	@Override
	public void setRefCount(int refCount) {
		_helperTable.setRefCount(refCount);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HelperTable> toCacheModel() {
		return _helperTable.toCacheModel();
	}

	@Override
	public HelperTable toEscapedModel() {
		return new HelperTableWrapper(_helperTable.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _helperTable.toString();
	}

	@Override
	public HelperTable toUnescapedModel() {
		return new HelperTableWrapper(_helperTable.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _helperTable.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HelperTableWrapper)) {
			return false;
		}

		HelperTableWrapper helperTableWrapper = (HelperTableWrapper)obj;

		if (Objects.equals(_helperTable, helperTableWrapper._helperTable)) {
			return true;
		}

		return false;
	}

	@Override
	public HelperTable getWrappedModel() {
		return _helperTable;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _helperTable.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _helperTable.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_helperTable.resetOriginalValues();
	}

	private final HelperTable _helperTable;
}