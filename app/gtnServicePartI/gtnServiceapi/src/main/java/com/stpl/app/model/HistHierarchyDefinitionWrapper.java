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
 * This class is a wrapper for {@link HistHierarchyDefinition}.
 * </p>
 *
 * @author
 * @see HistHierarchyDefinition
 * @generated
 */
@ProviderType
public class HistHierarchyDefinitionWrapper implements HistHierarchyDefinition,
	ModelWrapper<HistHierarchyDefinition> {
	public HistHierarchyDefinitionWrapper(
		HistHierarchyDefinition histHierarchyDefinition) {
		_histHierarchyDefinition = histHierarchyDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return HistHierarchyDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return HistHierarchyDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("noOfLevels", getNoOfLevels());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("actionDate", getActionDate());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("hierarchyType", getHierarchyType());
		attributes.put("hierarchyCategory", getHierarchyCategory());
		attributes.put("hierarchyName", getHierarchyName());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer noOfLevels = (Integer)attributes.get("noOfLevels");

		if (noOfLevels != null) {
			setNoOfLevels(noOfLevels);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer hierarchyDefinitionSid = (Integer)attributes.get(
				"hierarchyDefinitionSid");

		if (hierarchyDefinitionSid != null) {
			setHierarchyDefinitionSid(hierarchyDefinitionSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer hierarchyType = (Integer)attributes.get("hierarchyType");

		if (hierarchyType != null) {
			setHierarchyType(hierarchyType);
		}

		Integer hierarchyCategory = (Integer)attributes.get("hierarchyCategory");

		if (hierarchyCategory != null) {
			setHierarchyCategory(hierarchyCategory);
		}

		String hierarchyName = (String)attributes.get("hierarchyName");

		if (hierarchyName != null) {
			setHierarchyName(hierarchyName);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HistHierarchyDefinitionWrapper((HistHierarchyDefinition)_histHierarchyDefinition.clone());
	}

	@Override
	public int compareTo(HistHierarchyDefinition histHierarchyDefinition) {
		return _histHierarchyDefinition.compareTo(histHierarchyDefinition);
	}

	/**
	* Returns the action date of this hist hierarchy definition.
	*
	* @return the action date of this hist hierarchy definition
	*/
	@Override
	public Date getActionDate() {
		return _histHierarchyDefinition.getActionDate();
	}

	/**
	* Returns the action flag of this hist hierarchy definition.
	*
	* @return the action flag of this hist hierarchy definition
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histHierarchyDefinition.getActionFlag();
	}

	/**
	* Returns the created by of this hist hierarchy definition.
	*
	* @return the created by of this hist hierarchy definition
	*/
	@Override
	public int getCreatedBy() {
		return _histHierarchyDefinition.getCreatedBy();
	}

	/**
	* Returns the created date of this hist hierarchy definition.
	*
	* @return the created date of this hist hierarchy definition
	*/
	@Override
	public Date getCreatedDate() {
		return _histHierarchyDefinition.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histHierarchyDefinition.getExpandoBridge();
	}

	/**
	* Returns the hierarchy category of this hist hierarchy definition.
	*
	* @return the hierarchy category of this hist hierarchy definition
	*/
	@Override
	public int getHierarchyCategory() {
		return _histHierarchyDefinition.getHierarchyCategory();
	}

	/**
	* Returns the hierarchy definition sid of this hist hierarchy definition.
	*
	* @return the hierarchy definition sid of this hist hierarchy definition
	*/
	@Override
	public int getHierarchyDefinitionSid() {
		return _histHierarchyDefinition.getHierarchyDefinitionSid();
	}

	/**
	* Returns the hierarchy name of this hist hierarchy definition.
	*
	* @return the hierarchy name of this hist hierarchy definition
	*/
	@Override
	public java.lang.String getHierarchyName() {
		return _histHierarchyDefinition.getHierarchyName();
	}

	/**
	* Returns the hierarchy type of this hist hierarchy definition.
	*
	* @return the hierarchy type of this hist hierarchy definition
	*/
	@Override
	public int getHierarchyType() {
		return _histHierarchyDefinition.getHierarchyType();
	}

	/**
	* Returns the modified by of this hist hierarchy definition.
	*
	* @return the modified by of this hist hierarchy definition
	*/
	@Override
	public int getModifiedBy() {
		return _histHierarchyDefinition.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist hierarchy definition.
	*
	* @return the modified date of this hist hierarchy definition
	*/
	@Override
	public Date getModifiedDate() {
		return _histHierarchyDefinition.getModifiedDate();
	}

	/**
	* Returns the no of levels of this hist hierarchy definition.
	*
	* @return the no of levels of this hist hierarchy definition
	*/
	@Override
	public int getNoOfLevels() {
		return _histHierarchyDefinition.getNoOfLevels();
	}

	/**
	* Returns the primary key of this hist hierarchy definition.
	*
	* @return the primary key of this hist hierarchy definition
	*/
	@Override
	public com.stpl.app.service.persistence.HistHierarchyDefinitionPK getPrimaryKey() {
		return _histHierarchyDefinition.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histHierarchyDefinition.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this hist hierarchy definition.
	*
	* @return the version no of this hist hierarchy definition
	*/
	@Override
	public int getVersionNo() {
		return _histHierarchyDefinition.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histHierarchyDefinition.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histHierarchyDefinition.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histHierarchyDefinition.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histHierarchyDefinition.isNew();
	}

	@Override
	public void persist() {
		_histHierarchyDefinition.persist();
	}

	/**
	* Sets the action date of this hist hierarchy definition.
	*
	* @param actionDate the action date of this hist hierarchy definition
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histHierarchyDefinition.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist hierarchy definition.
	*
	* @param actionFlag the action flag of this hist hierarchy definition
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histHierarchyDefinition.setActionFlag(actionFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histHierarchyDefinition.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hist hierarchy definition.
	*
	* @param createdBy the created by of this hist hierarchy definition
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histHierarchyDefinition.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist hierarchy definition.
	*
	* @param createdDate the created date of this hist hierarchy definition
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histHierarchyDefinition.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histHierarchyDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histHierarchyDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histHierarchyDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hierarchy category of this hist hierarchy definition.
	*
	* @param hierarchyCategory the hierarchy category of this hist hierarchy definition
	*/
	@Override
	public void setHierarchyCategory(int hierarchyCategory) {
		_histHierarchyDefinition.setHierarchyCategory(hierarchyCategory);
	}

	/**
	* Sets the hierarchy definition sid of this hist hierarchy definition.
	*
	* @param hierarchyDefinitionSid the hierarchy definition sid of this hist hierarchy definition
	*/
	@Override
	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		_histHierarchyDefinition.setHierarchyDefinitionSid(hierarchyDefinitionSid);
	}

	/**
	* Sets the hierarchy name of this hist hierarchy definition.
	*
	* @param hierarchyName the hierarchy name of this hist hierarchy definition
	*/
	@Override
	public void setHierarchyName(java.lang.String hierarchyName) {
		_histHierarchyDefinition.setHierarchyName(hierarchyName);
	}

	/**
	* Sets the hierarchy type of this hist hierarchy definition.
	*
	* @param hierarchyType the hierarchy type of this hist hierarchy definition
	*/
	@Override
	public void setHierarchyType(int hierarchyType) {
		_histHierarchyDefinition.setHierarchyType(hierarchyType);
	}

	/**
	* Sets the modified by of this hist hierarchy definition.
	*
	* @param modifiedBy the modified by of this hist hierarchy definition
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histHierarchyDefinition.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist hierarchy definition.
	*
	* @param modifiedDate the modified date of this hist hierarchy definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histHierarchyDefinition.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histHierarchyDefinition.setNew(n);
	}

	/**
	* Sets the no of levels of this hist hierarchy definition.
	*
	* @param noOfLevels the no of levels of this hist hierarchy definition
	*/
	@Override
	public void setNoOfLevels(int noOfLevels) {
		_histHierarchyDefinition.setNoOfLevels(noOfLevels);
	}

	/**
	* Sets the primary key of this hist hierarchy definition.
	*
	* @param primaryKey the primary key of this hist hierarchy definition
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistHierarchyDefinitionPK primaryKey) {
		_histHierarchyDefinition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histHierarchyDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this hist hierarchy definition.
	*
	* @param versionNo the version no of this hist hierarchy definition
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histHierarchyDefinition.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistHierarchyDefinition> toCacheModel() {
		return _histHierarchyDefinition.toCacheModel();
	}

	@Override
	public HistHierarchyDefinition toEscapedModel() {
		return new HistHierarchyDefinitionWrapper(_histHierarchyDefinition.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histHierarchyDefinition.toString();
	}

	@Override
	public HistHierarchyDefinition toUnescapedModel() {
		return new HistHierarchyDefinitionWrapper(_histHierarchyDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histHierarchyDefinition.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistHierarchyDefinitionWrapper)) {
			return false;
		}

		HistHierarchyDefinitionWrapper histHierarchyDefinitionWrapper = (HistHierarchyDefinitionWrapper)obj;

		if (Objects.equals(_histHierarchyDefinition,
					histHierarchyDefinitionWrapper._histHierarchyDefinition)) {
			return true;
		}

		return false;
	}

	@Override
	public HistHierarchyDefinition getWrappedModel() {
		return _histHierarchyDefinition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histHierarchyDefinition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histHierarchyDefinition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histHierarchyDefinition.resetOriginalValues();
	}

	private final HistHierarchyDefinition _histHierarchyDefinition;
}