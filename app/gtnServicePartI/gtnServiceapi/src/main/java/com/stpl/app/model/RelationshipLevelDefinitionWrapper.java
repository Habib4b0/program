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
 * This class is a wrapper for {@link RelationshipLevelDefinition}.
 * </p>
 *
 * @author
 * @see RelationshipLevelDefinition
 * @generated
 */
@ProviderType
public class RelationshipLevelDefinitionWrapper
	implements RelationshipLevelDefinition,
		ModelWrapper<RelationshipLevelDefinition> {
	public RelationshipLevelDefinitionWrapper(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		_relationshipLevelDefinition = relationshipLevelDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return RelationshipLevelDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return RelationshipLevelDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("relationshipLevelValues", getRelationshipLevelValues());
		attributes.put("hierarchyLevelDefinitionSid",
			getHierarchyLevelDefinitionSid());
		attributes.put("parentNode", getParentNode());
		attributes.put("versionNo", getVersionNo());
		attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("levelNo", getLevelNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("hierarchyNo", getHierarchyNo());
		attributes.put("relationshipLevelSid", getRelationshipLevelSid());
		attributes.put("flag", getFlag());
		attributes.put("levelName", getLevelName());
		attributes.put("parentHierarchyNo", getParentHierarchyNo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String relationshipLevelValues = (String)attributes.get(
				"relationshipLevelValues");

		if (relationshipLevelValues != null) {
			setRelationshipLevelValues(relationshipLevelValues);
		}

		Integer hierarchyLevelDefinitionSid = (Integer)attributes.get(
				"hierarchyLevelDefinitionSid");

		if (hierarchyLevelDefinitionSid != null) {
			setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
		}

		String parentNode = (String)attributes.get("parentNode");

		if (parentNode != null) {
			setParentNode(parentNode);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer relationshipBuilderSid = (Integer)attributes.get(
				"relationshipBuilderSid");

		if (relationshipBuilderSid != null) {
			setRelationshipBuilderSid(relationshipBuilderSid);
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

		String levelNo = (String)attributes.get("levelNo");

		if (levelNo != null) {
			setLevelNo(levelNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String hierarchyNo = (String)attributes.get("hierarchyNo");

		if (hierarchyNo != null) {
			setHierarchyNo(hierarchyNo);
		}

		Integer relationshipLevelSid = (Integer)attributes.get(
				"relationshipLevelSid");

		if (relationshipLevelSid != null) {
			setRelationshipLevelSid(relationshipLevelSid);
		}

		String flag = (String)attributes.get("flag");

		if (flag != null) {
			setFlag(flag);
		}

		String levelName = (String)attributes.get("levelName");

		if (levelName != null) {
			setLevelName(levelName);
		}

		String parentHierarchyNo = (String)attributes.get("parentHierarchyNo");

		if (parentHierarchyNo != null) {
			setParentHierarchyNo(parentHierarchyNo);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new RelationshipLevelDefinitionWrapper((RelationshipLevelDefinition)_relationshipLevelDefinition.clone());
	}

	@Override
	public int compareTo(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		return _relationshipLevelDefinition.compareTo(relationshipLevelDefinition);
	}

	/**
	* Returns the created by of this relationship level definition.
	*
	* @return the created by of this relationship level definition
	*/
	@Override
	public int getCreatedBy() {
		return _relationshipLevelDefinition.getCreatedBy();
	}

	/**
	* Returns the created date of this relationship level definition.
	*
	* @return the created date of this relationship level definition
	*/
	@Override
	public Date getCreatedDate() {
		return _relationshipLevelDefinition.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _relationshipLevelDefinition.getExpandoBridge();
	}

	/**
	* Returns the flag of this relationship level definition.
	*
	* @return the flag of this relationship level definition
	*/
	@Override
	public java.lang.String getFlag() {
		return _relationshipLevelDefinition.getFlag();
	}

	/**
	* Returns the hierarchy level definition sid of this relationship level definition.
	*
	* @return the hierarchy level definition sid of this relationship level definition
	*/
	@Override
	public int getHierarchyLevelDefinitionSid() {
		return _relationshipLevelDefinition.getHierarchyLevelDefinitionSid();
	}

	/**
	* Returns the hierarchy no of this relationship level definition.
	*
	* @return the hierarchy no of this relationship level definition
	*/
	@Override
	public java.lang.String getHierarchyNo() {
		return _relationshipLevelDefinition.getHierarchyNo();
	}

	/**
	* Returns the level name of this relationship level definition.
	*
	* @return the level name of this relationship level definition
	*/
	@Override
	public java.lang.String getLevelName() {
		return _relationshipLevelDefinition.getLevelName();
	}

	/**
	* Returns the level no of this relationship level definition.
	*
	* @return the level no of this relationship level definition
	*/
	@Override
	public java.lang.String getLevelNo() {
		return _relationshipLevelDefinition.getLevelNo();
	}

	/**
	* Returns the modified by of this relationship level definition.
	*
	* @return the modified by of this relationship level definition
	*/
	@Override
	public int getModifiedBy() {
		return _relationshipLevelDefinition.getModifiedBy();
	}

	/**
	* Returns the modified date of this relationship level definition.
	*
	* @return the modified date of this relationship level definition
	*/
	@Override
	public Date getModifiedDate() {
		return _relationshipLevelDefinition.getModifiedDate();
	}

	/**
	* Returns the parent hierarchy no of this relationship level definition.
	*
	* @return the parent hierarchy no of this relationship level definition
	*/
	@Override
	public java.lang.String getParentHierarchyNo() {
		return _relationshipLevelDefinition.getParentHierarchyNo();
	}

	/**
	* Returns the parent node of this relationship level definition.
	*
	* @return the parent node of this relationship level definition
	*/
	@Override
	public java.lang.String getParentNode() {
		return _relationshipLevelDefinition.getParentNode();
	}

	/**
	* Returns the primary key of this relationship level definition.
	*
	* @return the primary key of this relationship level definition
	*/
	@Override
	public int getPrimaryKey() {
		return _relationshipLevelDefinition.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _relationshipLevelDefinition.getPrimaryKeyObj();
	}

	/**
	* Returns the relationship builder sid of this relationship level definition.
	*
	* @return the relationship builder sid of this relationship level definition
	*/
	@Override
	public int getRelationshipBuilderSid() {
		return _relationshipLevelDefinition.getRelationshipBuilderSid();
	}

	/**
	* Returns the relationship level sid of this relationship level definition.
	*
	* @return the relationship level sid of this relationship level definition
	*/
	@Override
	public int getRelationshipLevelSid() {
		return _relationshipLevelDefinition.getRelationshipLevelSid();
	}

	/**
	* Returns the relationship level values of this relationship level definition.
	*
	* @return the relationship level values of this relationship level definition
	*/
	@Override
	public java.lang.String getRelationshipLevelValues() {
		return _relationshipLevelDefinition.getRelationshipLevelValues();
	}

	/**
	* Returns the version no of this relationship level definition.
	*
	* @return the version no of this relationship level definition
	*/
	@Override
	public int getVersionNo() {
		return _relationshipLevelDefinition.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _relationshipLevelDefinition.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _relationshipLevelDefinition.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _relationshipLevelDefinition.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _relationshipLevelDefinition.isNew();
	}

	@Override
	public void persist() {
		_relationshipLevelDefinition.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_relationshipLevelDefinition.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this relationship level definition.
	*
	* @param createdBy the created by of this relationship level definition
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_relationshipLevelDefinition.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this relationship level definition.
	*
	* @param createdDate the created date of this relationship level definition
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_relationshipLevelDefinition.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_relationshipLevelDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_relationshipLevelDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_relationshipLevelDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the flag of this relationship level definition.
	*
	* @param flag the flag of this relationship level definition
	*/
	@Override
	public void setFlag(java.lang.String flag) {
		_relationshipLevelDefinition.setFlag(flag);
	}

	/**
	* Sets the hierarchy level definition sid of this relationship level definition.
	*
	* @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this relationship level definition
	*/
	@Override
	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		_relationshipLevelDefinition.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
	}

	/**
	* Sets the hierarchy no of this relationship level definition.
	*
	* @param hierarchyNo the hierarchy no of this relationship level definition
	*/
	@Override
	public void setHierarchyNo(java.lang.String hierarchyNo) {
		_relationshipLevelDefinition.setHierarchyNo(hierarchyNo);
	}

	/**
	* Sets the level name of this relationship level definition.
	*
	* @param levelName the level name of this relationship level definition
	*/
	@Override
	public void setLevelName(java.lang.String levelName) {
		_relationshipLevelDefinition.setLevelName(levelName);
	}

	/**
	* Sets the level no of this relationship level definition.
	*
	* @param levelNo the level no of this relationship level definition
	*/
	@Override
	public void setLevelNo(java.lang.String levelNo) {
		_relationshipLevelDefinition.setLevelNo(levelNo);
	}

	/**
	* Sets the modified by of this relationship level definition.
	*
	* @param modifiedBy the modified by of this relationship level definition
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_relationshipLevelDefinition.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this relationship level definition.
	*
	* @param modifiedDate the modified date of this relationship level definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_relationshipLevelDefinition.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_relationshipLevelDefinition.setNew(n);
	}

	/**
	* Sets the parent hierarchy no of this relationship level definition.
	*
	* @param parentHierarchyNo the parent hierarchy no of this relationship level definition
	*/
	@Override
	public void setParentHierarchyNo(java.lang.String parentHierarchyNo) {
		_relationshipLevelDefinition.setParentHierarchyNo(parentHierarchyNo);
	}

	/**
	* Sets the parent node of this relationship level definition.
	*
	* @param parentNode the parent node of this relationship level definition
	*/
	@Override
	public void setParentNode(java.lang.String parentNode) {
		_relationshipLevelDefinition.setParentNode(parentNode);
	}

	/**
	* Sets the primary key of this relationship level definition.
	*
	* @param primaryKey the primary key of this relationship level definition
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_relationshipLevelDefinition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_relationshipLevelDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the relationship builder sid of this relationship level definition.
	*
	* @param relationshipBuilderSid the relationship builder sid of this relationship level definition
	*/
	@Override
	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		_relationshipLevelDefinition.setRelationshipBuilderSid(relationshipBuilderSid);
	}

	/**
	* Sets the relationship level sid of this relationship level definition.
	*
	* @param relationshipLevelSid the relationship level sid of this relationship level definition
	*/
	@Override
	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_relationshipLevelDefinition.setRelationshipLevelSid(relationshipLevelSid);
	}

	/**
	* Sets the relationship level values of this relationship level definition.
	*
	* @param relationshipLevelValues the relationship level values of this relationship level definition
	*/
	@Override
	public void setRelationshipLevelValues(
		java.lang.String relationshipLevelValues) {
		_relationshipLevelDefinition.setRelationshipLevelValues(relationshipLevelValues);
	}

	/**
	* Sets the version no of this relationship level definition.
	*
	* @param versionNo the version no of this relationship level definition
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_relationshipLevelDefinition.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RelationshipLevelDefinition> toCacheModel() {
		return _relationshipLevelDefinition.toCacheModel();
	}

	@Override
	public RelationshipLevelDefinition toEscapedModel() {
		return new RelationshipLevelDefinitionWrapper(_relationshipLevelDefinition.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _relationshipLevelDefinition.toString();
	}

	@Override
	public RelationshipLevelDefinition toUnescapedModel() {
		return new RelationshipLevelDefinitionWrapper(_relationshipLevelDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _relationshipLevelDefinition.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RelationshipLevelDefinitionWrapper)) {
			return false;
		}

		RelationshipLevelDefinitionWrapper relationshipLevelDefinitionWrapper = (RelationshipLevelDefinitionWrapper)obj;

		if (Objects.equals(_relationshipLevelDefinition,
					relationshipLevelDefinitionWrapper._relationshipLevelDefinition)) {
			return true;
		}

		return false;
	}

	@Override
	public RelationshipLevelDefinition getWrappedModel() {
		return _relationshipLevelDefinition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _relationshipLevelDefinition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _relationshipLevelDefinition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_relationshipLevelDefinition.resetOriginalValues();
	}

	private final RelationshipLevelDefinition _relationshipLevelDefinition;
}