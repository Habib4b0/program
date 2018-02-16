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
 * This class is a wrapper for {@link HierarchyLevelDefinition}.
 * </p>
 *
 * @author
 * @see HierarchyLevelDefinition
 * @generated
 */
@ProviderType
public class HierarchyLevelDefinitionWrapper implements HierarchyLevelDefinition,
	ModelWrapper<HierarchyLevelDefinition> {
	public HierarchyLevelDefinitionWrapper(
		HierarchyLevelDefinition hierarchyLevelDefinition) {
		_hierarchyLevelDefinition = hierarchyLevelDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return HierarchyLevelDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return HierarchyLevelDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("tableName", getTableName());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("levelValueReference", getLevelValueReference());
		attributes.put("fieldName", getFieldName());
		attributes.put("levelNo", getLevelNo());
		attributes.put("hierarchyLevelDefinitionSid",
			getHierarchyLevelDefinitionSid());
		attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("levelName", getLevelName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String tableName = (String)attributes.get("tableName");

		if (tableName != null) {
			setTableName(tableName);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String levelValueReference = (String)attributes.get(
				"levelValueReference");

		if (levelValueReference != null) {
			setLevelValueReference(levelValueReference);
		}

		String fieldName = (String)attributes.get("fieldName");

		if (fieldName != null) {
			setFieldName(fieldName);
		}

		Integer levelNo = (Integer)attributes.get("levelNo");

		if (levelNo != null) {
			setLevelNo(levelNo);
		}

		Integer hierarchyLevelDefinitionSid = (Integer)attributes.get(
				"hierarchyLevelDefinitionSid");

		if (hierarchyLevelDefinitionSid != null) {
			setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
		}

		Integer hierarchyDefinitionSid = (Integer)attributes.get(
				"hierarchyDefinitionSid");

		if (hierarchyDefinitionSid != null) {
			setHierarchyDefinitionSid(hierarchyDefinitionSid);
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

		String levelName = (String)attributes.get("levelName");

		if (levelName != null) {
			setLevelName(levelName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HierarchyLevelDefinitionWrapper((HierarchyLevelDefinition)_hierarchyLevelDefinition.clone());
	}

	@Override
	public int compareTo(HierarchyLevelDefinition hierarchyLevelDefinition) {
		return _hierarchyLevelDefinition.compareTo(hierarchyLevelDefinition);
	}

	/**
	* Returns the created by of this hierarchy level definition.
	*
	* @return the created by of this hierarchy level definition
	*/
	@Override
	public int getCreatedBy() {
		return _hierarchyLevelDefinition.getCreatedBy();
	}

	/**
	* Returns the created date of this hierarchy level definition.
	*
	* @return the created date of this hierarchy level definition
	*/
	@Override
	public Date getCreatedDate() {
		return _hierarchyLevelDefinition.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _hierarchyLevelDefinition.getExpandoBridge();
	}

	/**
	* Returns the field name of this hierarchy level definition.
	*
	* @return the field name of this hierarchy level definition
	*/
	@Override
	public java.lang.String getFieldName() {
		return _hierarchyLevelDefinition.getFieldName();
	}

	/**
	* Returns the hierarchy definition sid of this hierarchy level definition.
	*
	* @return the hierarchy definition sid of this hierarchy level definition
	*/
	@Override
	public int getHierarchyDefinitionSid() {
		return _hierarchyLevelDefinition.getHierarchyDefinitionSid();
	}

	/**
	* Returns the hierarchy level definition sid of this hierarchy level definition.
	*
	* @return the hierarchy level definition sid of this hierarchy level definition
	*/
	@Override
	public int getHierarchyLevelDefinitionSid() {
		return _hierarchyLevelDefinition.getHierarchyLevelDefinitionSid();
	}

	/**
	* Returns the level name of this hierarchy level definition.
	*
	* @return the level name of this hierarchy level definition
	*/
	@Override
	public java.lang.String getLevelName() {
		return _hierarchyLevelDefinition.getLevelName();
	}

	/**
	* Returns the level no of this hierarchy level definition.
	*
	* @return the level no of this hierarchy level definition
	*/
	@Override
	public int getLevelNo() {
		return _hierarchyLevelDefinition.getLevelNo();
	}

	/**
	* Returns the level value reference of this hierarchy level definition.
	*
	* @return the level value reference of this hierarchy level definition
	*/
	@Override
	public java.lang.String getLevelValueReference() {
		return _hierarchyLevelDefinition.getLevelValueReference();
	}

	/**
	* Returns the modified by of this hierarchy level definition.
	*
	* @return the modified by of this hierarchy level definition
	*/
	@Override
	public int getModifiedBy() {
		return _hierarchyLevelDefinition.getModifiedBy();
	}

	/**
	* Returns the modified date of this hierarchy level definition.
	*
	* @return the modified date of this hierarchy level definition
	*/
	@Override
	public Date getModifiedDate() {
		return _hierarchyLevelDefinition.getModifiedDate();
	}

	/**
	* Returns the primary key of this hierarchy level definition.
	*
	* @return the primary key of this hierarchy level definition
	*/
	@Override
	public int getPrimaryKey() {
		return _hierarchyLevelDefinition.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _hierarchyLevelDefinition.getPrimaryKeyObj();
	}

	/**
	* Returns the table name of this hierarchy level definition.
	*
	* @return the table name of this hierarchy level definition
	*/
	@Override
	public java.lang.String getTableName() {
		return _hierarchyLevelDefinition.getTableName();
	}

	/**
	* Returns the version no of this hierarchy level definition.
	*
	* @return the version no of this hierarchy level definition
	*/
	@Override
	public int getVersionNo() {
		return _hierarchyLevelDefinition.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _hierarchyLevelDefinition.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _hierarchyLevelDefinition.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _hierarchyLevelDefinition.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _hierarchyLevelDefinition.isNew();
	}

	@Override
	public void persist() {
		_hierarchyLevelDefinition.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_hierarchyLevelDefinition.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hierarchy level definition.
	*
	* @param createdBy the created by of this hierarchy level definition
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_hierarchyLevelDefinition.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hierarchy level definition.
	*
	* @param createdDate the created date of this hierarchy level definition
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_hierarchyLevelDefinition.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_hierarchyLevelDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_hierarchyLevelDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_hierarchyLevelDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field name of this hierarchy level definition.
	*
	* @param fieldName the field name of this hierarchy level definition
	*/
	@Override
	public void setFieldName(java.lang.String fieldName) {
		_hierarchyLevelDefinition.setFieldName(fieldName);
	}

	/**
	* Sets the hierarchy definition sid of this hierarchy level definition.
	*
	* @param hierarchyDefinitionSid the hierarchy definition sid of this hierarchy level definition
	*/
	@Override
	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		_hierarchyLevelDefinition.setHierarchyDefinitionSid(hierarchyDefinitionSid);
	}

	/**
	* Sets the hierarchy level definition sid of this hierarchy level definition.
	*
	* @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hierarchy level definition
	*/
	@Override
	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		_hierarchyLevelDefinition.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
	}

	/**
	* Sets the level name of this hierarchy level definition.
	*
	* @param levelName the level name of this hierarchy level definition
	*/
	@Override
	public void setLevelName(java.lang.String levelName) {
		_hierarchyLevelDefinition.setLevelName(levelName);
	}

	/**
	* Sets the level no of this hierarchy level definition.
	*
	* @param levelNo the level no of this hierarchy level definition
	*/
	@Override
	public void setLevelNo(int levelNo) {
		_hierarchyLevelDefinition.setLevelNo(levelNo);
	}

	/**
	* Sets the level value reference of this hierarchy level definition.
	*
	* @param levelValueReference the level value reference of this hierarchy level definition
	*/
	@Override
	public void setLevelValueReference(java.lang.String levelValueReference) {
		_hierarchyLevelDefinition.setLevelValueReference(levelValueReference);
	}

	/**
	* Sets the modified by of this hierarchy level definition.
	*
	* @param modifiedBy the modified by of this hierarchy level definition
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_hierarchyLevelDefinition.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hierarchy level definition.
	*
	* @param modifiedDate the modified date of this hierarchy level definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_hierarchyLevelDefinition.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_hierarchyLevelDefinition.setNew(n);
	}

	/**
	* Sets the primary key of this hierarchy level definition.
	*
	* @param primaryKey the primary key of this hierarchy level definition
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_hierarchyLevelDefinition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_hierarchyLevelDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the table name of this hierarchy level definition.
	*
	* @param tableName the table name of this hierarchy level definition
	*/
	@Override
	public void setTableName(java.lang.String tableName) {
		_hierarchyLevelDefinition.setTableName(tableName);
	}

	/**
	* Sets the version no of this hierarchy level definition.
	*
	* @param versionNo the version no of this hierarchy level definition
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_hierarchyLevelDefinition.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HierarchyLevelDefinition> toCacheModel() {
		return _hierarchyLevelDefinition.toCacheModel();
	}

	@Override
	public HierarchyLevelDefinition toEscapedModel() {
		return new HierarchyLevelDefinitionWrapper(_hierarchyLevelDefinition.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hierarchyLevelDefinition.toString();
	}

	@Override
	public HierarchyLevelDefinition toUnescapedModel() {
		return new HierarchyLevelDefinitionWrapper(_hierarchyLevelDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _hierarchyLevelDefinition.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HierarchyLevelDefinitionWrapper)) {
			return false;
		}

		HierarchyLevelDefinitionWrapper hierarchyLevelDefinitionWrapper = (HierarchyLevelDefinitionWrapper)obj;

		if (Objects.equals(_hierarchyLevelDefinition,
					hierarchyLevelDefinitionWrapper._hierarchyLevelDefinition)) {
			return true;
		}

		return false;
	}

	@Override
	public HierarchyLevelDefinition getWrappedModel() {
		return _hierarchyLevelDefinition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _hierarchyLevelDefinition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _hierarchyLevelDefinition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_hierarchyLevelDefinition.resetOriginalValues();
	}

	private final HierarchyLevelDefinition _hierarchyLevelDefinition;
}