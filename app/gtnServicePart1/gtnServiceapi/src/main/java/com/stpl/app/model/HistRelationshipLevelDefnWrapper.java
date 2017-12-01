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
 * This class is a wrapper for {@link HistRelationshipLevelDefn}.
 * </p>
 *
 * @author
 * @see HistRelationshipLevelDefn
 * @generated
 */
@ProviderType
public class HistRelationshipLevelDefnWrapper
	implements HistRelationshipLevelDefn,
		ModelWrapper<HistRelationshipLevelDefn> {
	public HistRelationshipLevelDefnWrapper(
		HistRelationshipLevelDefn histRelationshipLevelDefn) {
		_histRelationshipLevelDefn = histRelationshipLevelDefn;
	}

	@Override
	public Class<?> getModelClass() {
		return HistRelationshipLevelDefn.class;
	}

	@Override
	public String getModelClassName() {
		return HistRelationshipLevelDefn.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("relationshipLevelValues", getRelationshipLevelValues());
		attributes.put("actionDate", getActionDate());
		attributes.put("hierarchyLevelDefinitionSid",
			getHierarchyLevelDefinitionSid());
		attributes.put("parentNode", getParentNode());
		attributes.put("versionNo", getVersionNo());
		attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("levelNo", getLevelNo());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("hierarchyNo", getHierarchyNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("relationshipLevelSid", getRelationshipLevelSid());
		attributes.put("flag", getFlag());
		attributes.put("levelName", getLevelName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String relationshipLevelValues = (String)attributes.get(
				"relationshipLevelValues");

		if (relationshipLevelValues != null) {
			setRelationshipLevelValues(relationshipLevelValues);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
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

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		String hierarchyNo = (String)attributes.get("hierarchyNo");

		if (hierarchyNo != null) {
			setHierarchyNo(hierarchyNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
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
	}

	@Override
	public java.lang.Object clone() {
		return new HistRelationshipLevelDefnWrapper((HistRelationshipLevelDefn)_histRelationshipLevelDefn.clone());
	}

	@Override
	public int compareTo(HistRelationshipLevelDefn histRelationshipLevelDefn) {
		return _histRelationshipLevelDefn.compareTo(histRelationshipLevelDefn);
	}

	/**
	* Returns the action date of this hist relationship level defn.
	*
	* @return the action date of this hist relationship level defn
	*/
	@Override
	public Date getActionDate() {
		return _histRelationshipLevelDefn.getActionDate();
	}

	/**
	* Returns the action flag of this hist relationship level defn.
	*
	* @return the action flag of this hist relationship level defn
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histRelationshipLevelDefn.getActionFlag();
	}

	/**
	* Returns the created by of this hist relationship level defn.
	*
	* @return the created by of this hist relationship level defn
	*/
	@Override
	public int getCreatedBy() {
		return _histRelationshipLevelDefn.getCreatedBy();
	}

	/**
	* Returns the created date of this hist relationship level defn.
	*
	* @return the created date of this hist relationship level defn
	*/
	@Override
	public Date getCreatedDate() {
		return _histRelationshipLevelDefn.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histRelationshipLevelDefn.getExpandoBridge();
	}

	/**
	* Returns the flag of this hist relationship level defn.
	*
	* @return the flag of this hist relationship level defn
	*/
	@Override
	public java.lang.String getFlag() {
		return _histRelationshipLevelDefn.getFlag();
	}

	/**
	* Returns the hierarchy level definition sid of this hist relationship level defn.
	*
	* @return the hierarchy level definition sid of this hist relationship level defn
	*/
	@Override
	public int getHierarchyLevelDefinitionSid() {
		return _histRelationshipLevelDefn.getHierarchyLevelDefinitionSid();
	}

	/**
	* Returns the hierarchy no of this hist relationship level defn.
	*
	* @return the hierarchy no of this hist relationship level defn
	*/
	@Override
	public java.lang.String getHierarchyNo() {
		return _histRelationshipLevelDefn.getHierarchyNo();
	}

	/**
	* Returns the level name of this hist relationship level defn.
	*
	* @return the level name of this hist relationship level defn
	*/
	@Override
	public java.lang.String getLevelName() {
		return _histRelationshipLevelDefn.getLevelName();
	}

	/**
	* Returns the level no of this hist relationship level defn.
	*
	* @return the level no of this hist relationship level defn
	*/
	@Override
	public java.lang.String getLevelNo() {
		return _histRelationshipLevelDefn.getLevelNo();
	}

	/**
	* Returns the modified by of this hist relationship level defn.
	*
	* @return the modified by of this hist relationship level defn
	*/
	@Override
	public int getModifiedBy() {
		return _histRelationshipLevelDefn.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist relationship level defn.
	*
	* @return the modified date of this hist relationship level defn
	*/
	@Override
	public Date getModifiedDate() {
		return _histRelationshipLevelDefn.getModifiedDate();
	}

	/**
	* Returns the parent node of this hist relationship level defn.
	*
	* @return the parent node of this hist relationship level defn
	*/
	@Override
	public java.lang.String getParentNode() {
		return _histRelationshipLevelDefn.getParentNode();
	}

	/**
	* Returns the primary key of this hist relationship level defn.
	*
	* @return the primary key of this hist relationship level defn
	*/
	@Override
	public com.stpl.app.service.persistence.HistRelationshipLevelDefnPK getPrimaryKey() {
		return _histRelationshipLevelDefn.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histRelationshipLevelDefn.getPrimaryKeyObj();
	}

	/**
	* Returns the relationship builder sid of this hist relationship level defn.
	*
	* @return the relationship builder sid of this hist relationship level defn
	*/
	@Override
	public int getRelationshipBuilderSid() {
		return _histRelationshipLevelDefn.getRelationshipBuilderSid();
	}

	/**
	* Returns the relationship level sid of this hist relationship level defn.
	*
	* @return the relationship level sid of this hist relationship level defn
	*/
	@Override
	public int getRelationshipLevelSid() {
		return _histRelationshipLevelDefn.getRelationshipLevelSid();
	}

	/**
	* Returns the relationship level values of this hist relationship level defn.
	*
	* @return the relationship level values of this hist relationship level defn
	*/
	@Override
	public java.lang.String getRelationshipLevelValues() {
		return _histRelationshipLevelDefn.getRelationshipLevelValues();
	}

	/**
	* Returns the version no of this hist relationship level defn.
	*
	* @return the version no of this hist relationship level defn
	*/
	@Override
	public int getVersionNo() {
		return _histRelationshipLevelDefn.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histRelationshipLevelDefn.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histRelationshipLevelDefn.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histRelationshipLevelDefn.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histRelationshipLevelDefn.isNew();
	}

	@Override
	public void persist() {
		_histRelationshipLevelDefn.persist();
	}

	/**
	* Sets the action date of this hist relationship level defn.
	*
	* @param actionDate the action date of this hist relationship level defn
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histRelationshipLevelDefn.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist relationship level defn.
	*
	* @param actionFlag the action flag of this hist relationship level defn
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histRelationshipLevelDefn.setActionFlag(actionFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histRelationshipLevelDefn.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hist relationship level defn.
	*
	* @param createdBy the created by of this hist relationship level defn
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histRelationshipLevelDefn.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist relationship level defn.
	*
	* @param createdDate the created date of this hist relationship level defn
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histRelationshipLevelDefn.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histRelationshipLevelDefn.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histRelationshipLevelDefn.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histRelationshipLevelDefn.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the flag of this hist relationship level defn.
	*
	* @param flag the flag of this hist relationship level defn
	*/
	@Override
	public void setFlag(java.lang.String flag) {
		_histRelationshipLevelDefn.setFlag(flag);
	}

	/**
	* Sets the hierarchy level definition sid of this hist relationship level defn.
	*
	* @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hist relationship level defn
	*/
	@Override
	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		_histRelationshipLevelDefn.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
	}

	/**
	* Sets the hierarchy no of this hist relationship level defn.
	*
	* @param hierarchyNo the hierarchy no of this hist relationship level defn
	*/
	@Override
	public void setHierarchyNo(java.lang.String hierarchyNo) {
		_histRelationshipLevelDefn.setHierarchyNo(hierarchyNo);
	}

	/**
	* Sets the level name of this hist relationship level defn.
	*
	* @param levelName the level name of this hist relationship level defn
	*/
	@Override
	public void setLevelName(java.lang.String levelName) {
		_histRelationshipLevelDefn.setLevelName(levelName);
	}

	/**
	* Sets the level no of this hist relationship level defn.
	*
	* @param levelNo the level no of this hist relationship level defn
	*/
	@Override
	public void setLevelNo(java.lang.String levelNo) {
		_histRelationshipLevelDefn.setLevelNo(levelNo);
	}

	/**
	* Sets the modified by of this hist relationship level defn.
	*
	* @param modifiedBy the modified by of this hist relationship level defn
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histRelationshipLevelDefn.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist relationship level defn.
	*
	* @param modifiedDate the modified date of this hist relationship level defn
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histRelationshipLevelDefn.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histRelationshipLevelDefn.setNew(n);
	}

	/**
	* Sets the parent node of this hist relationship level defn.
	*
	* @param parentNode the parent node of this hist relationship level defn
	*/
	@Override
	public void setParentNode(java.lang.String parentNode) {
		_histRelationshipLevelDefn.setParentNode(parentNode);
	}

	/**
	* Sets the primary key of this hist relationship level defn.
	*
	* @param primaryKey the primary key of this hist relationship level defn
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistRelationshipLevelDefnPK primaryKey) {
		_histRelationshipLevelDefn.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histRelationshipLevelDefn.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the relationship builder sid of this hist relationship level defn.
	*
	* @param relationshipBuilderSid the relationship builder sid of this hist relationship level defn
	*/
	@Override
	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		_histRelationshipLevelDefn.setRelationshipBuilderSid(relationshipBuilderSid);
	}

	/**
	* Sets the relationship level sid of this hist relationship level defn.
	*
	* @param relationshipLevelSid the relationship level sid of this hist relationship level defn
	*/
	@Override
	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_histRelationshipLevelDefn.setRelationshipLevelSid(relationshipLevelSid);
	}

	/**
	* Sets the relationship level values of this hist relationship level defn.
	*
	* @param relationshipLevelValues the relationship level values of this hist relationship level defn
	*/
	@Override
	public void setRelationshipLevelValues(
		java.lang.String relationshipLevelValues) {
		_histRelationshipLevelDefn.setRelationshipLevelValues(relationshipLevelValues);
	}

	/**
	* Sets the version no of this hist relationship level defn.
	*
	* @param versionNo the version no of this hist relationship level defn
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histRelationshipLevelDefn.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistRelationshipLevelDefn> toCacheModel() {
		return _histRelationshipLevelDefn.toCacheModel();
	}

	@Override
	public HistRelationshipLevelDefn toEscapedModel() {
		return new HistRelationshipLevelDefnWrapper(_histRelationshipLevelDefn.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histRelationshipLevelDefn.toString();
	}

	@Override
	public HistRelationshipLevelDefn toUnescapedModel() {
		return new HistRelationshipLevelDefnWrapper(_histRelationshipLevelDefn.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histRelationshipLevelDefn.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistRelationshipLevelDefnWrapper)) {
			return false;
		}

		HistRelationshipLevelDefnWrapper histRelationshipLevelDefnWrapper = (HistRelationshipLevelDefnWrapper)obj;

		if (Objects.equals(_histRelationshipLevelDefn,
					histRelationshipLevelDefnWrapper._histRelationshipLevelDefn)) {
			return true;
		}

		return false;
	}

	@Override
	public HistRelationshipLevelDefn getWrappedModel() {
		return _histRelationshipLevelDefn;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histRelationshipLevelDefn.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histRelationshipLevelDefn.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histRelationshipLevelDefn.resetOriginalValues();
	}

	private final HistRelationshipLevelDefn _histRelationshipLevelDefn;
}