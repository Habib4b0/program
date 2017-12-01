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
 * This class is a wrapper for {@link HistHierarchyLevelDefn}.
 * </p>
 *
 * @author
 * @see HistHierarchyLevelDefn
 * @generated
 */
@ProviderType
public class HistHierarchyLevelDefnWrapper implements HistHierarchyLevelDefn,
	ModelWrapper<HistHierarchyLevelDefn> {
	public HistHierarchyLevelDefnWrapper(
		HistHierarchyLevelDefn histHierarchyLevelDefn) {
		_histHierarchyLevelDefn = histHierarchyLevelDefn;
	}

	@Override
	public Class<?> getModelClass() {
		return HistHierarchyLevelDefn.class;
	}

	@Override
	public String getModelClassName() {
		return HistHierarchyLevelDefn.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("tableName", getTableName());
		attributes.put("actionDate", getActionDate());
		attributes.put("fieldName", getFieldName());
		attributes.put("hierarchyLevelDefinitionSid",
			getHierarchyLevelDefinitionSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("levelValueReference", getLevelValueReference());
		attributes.put("levelNo", getLevelNo());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("levelName", getLevelName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String tableName = (String)attributes.get("tableName");

		if (tableName != null) {
			setTableName(tableName);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		String fieldName = (String)attributes.get("fieldName");

		if (fieldName != null) {
			setFieldName(fieldName);
		}

		Integer hierarchyLevelDefinitionSid = (Integer)attributes.get(
				"hierarchyLevelDefinitionSid");

		if (hierarchyLevelDefinitionSid != null) {
			setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String levelValueReference = (String)attributes.get(
				"levelValueReference");

		if (levelValueReference != null) {
			setLevelValueReference(levelValueReference);
		}

		Integer levelNo = (Integer)attributes.get("levelNo");

		if (levelNo != null) {
			setLevelNo(levelNo);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		Integer hierarchyDefinitionSid = (Integer)attributes.get(
				"hierarchyDefinitionSid");

		if (hierarchyDefinitionSid != null) {
			setHierarchyDefinitionSid(hierarchyDefinitionSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String levelName = (String)attributes.get("levelName");

		if (levelName != null) {
			setLevelName(levelName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HistHierarchyLevelDefnWrapper((HistHierarchyLevelDefn)_histHierarchyLevelDefn.clone());
	}

	@Override
	public int compareTo(HistHierarchyLevelDefn histHierarchyLevelDefn) {
		return _histHierarchyLevelDefn.compareTo(histHierarchyLevelDefn);
	}

	/**
	* Returns the action date of this hist hierarchy level defn.
	*
	* @return the action date of this hist hierarchy level defn
	*/
	@Override
	public Date getActionDate() {
		return _histHierarchyLevelDefn.getActionDate();
	}

	/**
	* Returns the action flag of this hist hierarchy level defn.
	*
	* @return the action flag of this hist hierarchy level defn
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histHierarchyLevelDefn.getActionFlag();
	}

	/**
	* Returns the created by of this hist hierarchy level defn.
	*
	* @return the created by of this hist hierarchy level defn
	*/
	@Override
	public int getCreatedBy() {
		return _histHierarchyLevelDefn.getCreatedBy();
	}

	/**
	* Returns the created date of this hist hierarchy level defn.
	*
	* @return the created date of this hist hierarchy level defn
	*/
	@Override
	public Date getCreatedDate() {
		return _histHierarchyLevelDefn.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histHierarchyLevelDefn.getExpandoBridge();
	}

	/**
	* Returns the field name of this hist hierarchy level defn.
	*
	* @return the field name of this hist hierarchy level defn
	*/
	@Override
	public java.lang.String getFieldName() {
		return _histHierarchyLevelDefn.getFieldName();
	}

	/**
	* Returns the hierarchy definition sid of this hist hierarchy level defn.
	*
	* @return the hierarchy definition sid of this hist hierarchy level defn
	*/
	@Override
	public int getHierarchyDefinitionSid() {
		return _histHierarchyLevelDefn.getHierarchyDefinitionSid();
	}

	/**
	* Returns the hierarchy level definition sid of this hist hierarchy level defn.
	*
	* @return the hierarchy level definition sid of this hist hierarchy level defn
	*/
	@Override
	public int getHierarchyLevelDefinitionSid() {
		return _histHierarchyLevelDefn.getHierarchyLevelDefinitionSid();
	}

	/**
	* Returns the level name of this hist hierarchy level defn.
	*
	* @return the level name of this hist hierarchy level defn
	*/
	@Override
	public java.lang.String getLevelName() {
		return _histHierarchyLevelDefn.getLevelName();
	}

	/**
	* Returns the level no of this hist hierarchy level defn.
	*
	* @return the level no of this hist hierarchy level defn
	*/
	@Override
	public int getLevelNo() {
		return _histHierarchyLevelDefn.getLevelNo();
	}

	/**
	* Returns the level value reference of this hist hierarchy level defn.
	*
	* @return the level value reference of this hist hierarchy level defn
	*/
	@Override
	public java.lang.String getLevelValueReference() {
		return _histHierarchyLevelDefn.getLevelValueReference();
	}

	/**
	* Returns the modified by of this hist hierarchy level defn.
	*
	* @return the modified by of this hist hierarchy level defn
	*/
	@Override
	public int getModifiedBy() {
		return _histHierarchyLevelDefn.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist hierarchy level defn.
	*
	* @return the modified date of this hist hierarchy level defn
	*/
	@Override
	public Date getModifiedDate() {
		return _histHierarchyLevelDefn.getModifiedDate();
	}

	/**
	* Returns the primary key of this hist hierarchy level defn.
	*
	* @return the primary key of this hist hierarchy level defn
	*/
	@Override
	public com.stpl.app.service.persistence.HistHierarchyLevelDefnPK getPrimaryKey() {
		return _histHierarchyLevelDefn.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histHierarchyLevelDefn.getPrimaryKeyObj();
	}

	/**
	* Returns the table name of this hist hierarchy level defn.
	*
	* @return the table name of this hist hierarchy level defn
	*/
	@Override
	public java.lang.String getTableName() {
		return _histHierarchyLevelDefn.getTableName();
	}

	/**
	* Returns the version no of this hist hierarchy level defn.
	*
	* @return the version no of this hist hierarchy level defn
	*/
	@Override
	public int getVersionNo() {
		return _histHierarchyLevelDefn.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histHierarchyLevelDefn.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histHierarchyLevelDefn.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histHierarchyLevelDefn.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histHierarchyLevelDefn.isNew();
	}

	@Override
	public void persist() {
		_histHierarchyLevelDefn.persist();
	}

	/**
	* Sets the action date of this hist hierarchy level defn.
	*
	* @param actionDate the action date of this hist hierarchy level defn
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histHierarchyLevelDefn.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist hierarchy level defn.
	*
	* @param actionFlag the action flag of this hist hierarchy level defn
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histHierarchyLevelDefn.setActionFlag(actionFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histHierarchyLevelDefn.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hist hierarchy level defn.
	*
	* @param createdBy the created by of this hist hierarchy level defn
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histHierarchyLevelDefn.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist hierarchy level defn.
	*
	* @param createdDate the created date of this hist hierarchy level defn
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histHierarchyLevelDefn.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histHierarchyLevelDefn.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histHierarchyLevelDefn.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histHierarchyLevelDefn.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field name of this hist hierarchy level defn.
	*
	* @param fieldName the field name of this hist hierarchy level defn
	*/
	@Override
	public void setFieldName(java.lang.String fieldName) {
		_histHierarchyLevelDefn.setFieldName(fieldName);
	}

	/**
	* Sets the hierarchy definition sid of this hist hierarchy level defn.
	*
	* @param hierarchyDefinitionSid the hierarchy definition sid of this hist hierarchy level defn
	*/
	@Override
	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		_histHierarchyLevelDefn.setHierarchyDefinitionSid(hierarchyDefinitionSid);
	}

	/**
	* Sets the hierarchy level definition sid of this hist hierarchy level defn.
	*
	* @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hist hierarchy level defn
	*/
	@Override
	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		_histHierarchyLevelDefn.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
	}

	/**
	* Sets the level name of this hist hierarchy level defn.
	*
	* @param levelName the level name of this hist hierarchy level defn
	*/
	@Override
	public void setLevelName(java.lang.String levelName) {
		_histHierarchyLevelDefn.setLevelName(levelName);
	}

	/**
	* Sets the level no of this hist hierarchy level defn.
	*
	* @param levelNo the level no of this hist hierarchy level defn
	*/
	@Override
	public void setLevelNo(int levelNo) {
		_histHierarchyLevelDefn.setLevelNo(levelNo);
	}

	/**
	* Sets the level value reference of this hist hierarchy level defn.
	*
	* @param levelValueReference the level value reference of this hist hierarchy level defn
	*/
	@Override
	public void setLevelValueReference(java.lang.String levelValueReference) {
		_histHierarchyLevelDefn.setLevelValueReference(levelValueReference);
	}

	/**
	* Sets the modified by of this hist hierarchy level defn.
	*
	* @param modifiedBy the modified by of this hist hierarchy level defn
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histHierarchyLevelDefn.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist hierarchy level defn.
	*
	* @param modifiedDate the modified date of this hist hierarchy level defn
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histHierarchyLevelDefn.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histHierarchyLevelDefn.setNew(n);
	}

	/**
	* Sets the primary key of this hist hierarchy level defn.
	*
	* @param primaryKey the primary key of this hist hierarchy level defn
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistHierarchyLevelDefnPK primaryKey) {
		_histHierarchyLevelDefn.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histHierarchyLevelDefn.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the table name of this hist hierarchy level defn.
	*
	* @param tableName the table name of this hist hierarchy level defn
	*/
	@Override
	public void setTableName(java.lang.String tableName) {
		_histHierarchyLevelDefn.setTableName(tableName);
	}

	/**
	* Sets the version no of this hist hierarchy level defn.
	*
	* @param versionNo the version no of this hist hierarchy level defn
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histHierarchyLevelDefn.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistHierarchyLevelDefn> toCacheModel() {
		return _histHierarchyLevelDefn.toCacheModel();
	}

	@Override
	public HistHierarchyLevelDefn toEscapedModel() {
		return new HistHierarchyLevelDefnWrapper(_histHierarchyLevelDefn.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histHierarchyLevelDefn.toString();
	}

	@Override
	public HistHierarchyLevelDefn toUnescapedModel() {
		return new HistHierarchyLevelDefnWrapper(_histHierarchyLevelDefn.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histHierarchyLevelDefn.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistHierarchyLevelDefnWrapper)) {
			return false;
		}

		HistHierarchyLevelDefnWrapper histHierarchyLevelDefnWrapper = (HistHierarchyLevelDefnWrapper)obj;

		if (Objects.equals(_histHierarchyLevelDefn,
					histHierarchyLevelDefnWrapper._histHierarchyLevelDefn)) {
			return true;
		}

		return false;
	}

	@Override
	public HistHierarchyLevelDefn getWrappedModel() {
		return _histHierarchyLevelDefn;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histHierarchyLevelDefn.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histHierarchyLevelDefn.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histHierarchyLevelDefn.resetOriginalValues();
	}

	private final HistHierarchyLevelDefn _histHierarchyLevelDefn;
}