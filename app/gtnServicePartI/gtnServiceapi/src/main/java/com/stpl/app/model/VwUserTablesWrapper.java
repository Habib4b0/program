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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link VwUserTables}.
 * </p>
 *
 * @author
 * @see VwUserTables
 * @generated
 */
@ProviderType
public class VwUserTablesWrapper implements VwUserTables,
	ModelWrapper<VwUserTables> {
	public VwUserTablesWrapper(VwUserTables vwUserTables) {
		_vwUserTables = vwUserTables;
	}

	@Override
	public Class<?> getModelClass() {
		return VwUserTables.class;
	}

	@Override
	public String getModelClassName() {
		return VwUserTables.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uniqueId", getUniqueId());
		attributes.put("tableName", getTableName());
		attributes.put("columnName", getColumnName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer uniqueId = (Integer)attributes.get("uniqueId");

		if (uniqueId != null) {
			setUniqueId(uniqueId);
		}

		String tableName = (String)attributes.get("tableName");

		if (tableName != null) {
			setTableName(tableName);
		}

		String columnName = (String)attributes.get("columnName");

		if (columnName != null) {
			setColumnName(columnName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwUserTablesWrapper((VwUserTables)_vwUserTables.clone());
	}

	@Override
	public int compareTo(VwUserTables vwUserTables) {
		return _vwUserTables.compareTo(vwUserTables);
	}

	/**
	* Returns the column name of this vw user tables.
	*
	* @return the column name of this vw user tables
	*/
	@Override
	public java.lang.String getColumnName() {
		return _vwUserTables.getColumnName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwUserTables.getExpandoBridge();
	}

	/**
	* Returns the primary key of this vw user tables.
	*
	* @return the primary key of this vw user tables
	*/
	@Override
	public int getPrimaryKey() {
		return _vwUserTables.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwUserTables.getPrimaryKeyObj();
	}

	/**
	* Returns the table name of this vw user tables.
	*
	* @return the table name of this vw user tables
	*/
	@Override
	public java.lang.String getTableName() {
		return _vwUserTables.getTableName();
	}

	/**
	* Returns the unique ID of this vw user tables.
	*
	* @return the unique ID of this vw user tables
	*/
	@Override
	public int getUniqueId() {
		return _vwUserTables.getUniqueId();
	}

	@Override
	public int hashCode() {
		return _vwUserTables.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwUserTables.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwUserTables.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwUserTables.isNew();
	}

	@Override
	public void persist() {
		_vwUserTables.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwUserTables.setCachedModel(cachedModel);
	}

	/**
	* Sets the column name of this vw user tables.
	*
	* @param columnName the column name of this vw user tables
	*/
	@Override
	public void setColumnName(java.lang.String columnName) {
		_vwUserTables.setColumnName(columnName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwUserTables.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwUserTables.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwUserTables.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_vwUserTables.setNew(n);
	}

	/**
	* Sets the primary key of this vw user tables.
	*
	* @param primaryKey the primary key of this vw user tables
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwUserTables.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwUserTables.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the table name of this vw user tables.
	*
	* @param tableName the table name of this vw user tables
	*/
	@Override
	public void setTableName(java.lang.String tableName) {
		_vwUserTables.setTableName(tableName);
	}

	/**
	* Sets the unique ID of this vw user tables.
	*
	* @param uniqueId the unique ID of this vw user tables
	*/
	@Override
	public void setUniqueId(int uniqueId) {
		_vwUserTables.setUniqueId(uniqueId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwUserTables> toCacheModel() {
		return _vwUserTables.toCacheModel();
	}

	@Override
	public VwUserTables toEscapedModel() {
		return new VwUserTablesWrapper(_vwUserTables.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwUserTables.toString();
	}

	@Override
	public VwUserTables toUnescapedModel() {
		return new VwUserTablesWrapper(_vwUserTables.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwUserTables.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwUserTablesWrapper)) {
			return false;
		}

		VwUserTablesWrapper vwUserTablesWrapper = (VwUserTablesWrapper)obj;

		if (Objects.equals(_vwUserTables, vwUserTablesWrapper._vwUserTables)) {
			return true;
		}

		return false;
	}

	@Override
	public VwUserTables getWrappedModel() {
		return _vwUserTables;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwUserTables.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwUserTables.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwUserTables.resetOriginalValues();
	}

	private final VwUserTables _vwUserTables;
}