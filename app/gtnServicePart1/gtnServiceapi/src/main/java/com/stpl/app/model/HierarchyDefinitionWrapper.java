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
 * This class is a wrapper for {@link HierarchyDefinition}.
 * </p>
 *
 * @author
 * @see HierarchyDefinition
 * @generated
 */
@ProviderType
public class HierarchyDefinitionWrapper implements HierarchyDefinition,
	ModelWrapper<HierarchyDefinition> {
	public HierarchyDefinitionWrapper(HierarchyDefinition hierarchyDefinition) {
		_hierarchyDefinition = hierarchyDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return HierarchyDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return HierarchyDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("noOfLevels", getNoOfLevels());
		attributes.put("hierarchyType", getHierarchyType());
		attributes.put("hierarchyName", getHierarchyName());
		attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("hierarchyCategory", getHierarchyCategory());

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

		Integer noOfLevels = (Integer)attributes.get("noOfLevels");

		if (noOfLevels != null) {
			setNoOfLevels(noOfLevels);
		}

		Integer hierarchyType = (Integer)attributes.get("hierarchyType");

		if (hierarchyType != null) {
			setHierarchyType(hierarchyType);
		}

		String hierarchyName = (String)attributes.get("hierarchyName");

		if (hierarchyName != null) {
			setHierarchyName(hierarchyName);
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

		Integer hierarchyCategory = (Integer)attributes.get("hierarchyCategory");

		if (hierarchyCategory != null) {
			setHierarchyCategory(hierarchyCategory);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HierarchyDefinitionWrapper((HierarchyDefinition)_hierarchyDefinition.clone());
	}

	@Override
	public int compareTo(HierarchyDefinition hierarchyDefinition) {
		return _hierarchyDefinition.compareTo(hierarchyDefinition);
	}

	/**
	* Returns the created by of this hierarchy definition.
	*
	* @return the created by of this hierarchy definition
	*/
	@Override
	public int getCreatedBy() {
		return _hierarchyDefinition.getCreatedBy();
	}

	/**
	* Returns the created date of this hierarchy definition.
	*
	* @return the created date of this hierarchy definition
	*/
	@Override
	public Date getCreatedDate() {
		return _hierarchyDefinition.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _hierarchyDefinition.getExpandoBridge();
	}

	/**
	* Returns the hierarchy category of this hierarchy definition.
	*
	* @return the hierarchy category of this hierarchy definition
	*/
	@Override
	public int getHierarchyCategory() {
		return _hierarchyDefinition.getHierarchyCategory();
	}

	/**
	* Returns the hierarchy definition sid of this hierarchy definition.
	*
	* @return the hierarchy definition sid of this hierarchy definition
	*/
	@Override
	public int getHierarchyDefinitionSid() {
		return _hierarchyDefinition.getHierarchyDefinitionSid();
	}

	/**
	* Returns the hierarchy name of this hierarchy definition.
	*
	* @return the hierarchy name of this hierarchy definition
	*/
	@Override
	public java.lang.String getHierarchyName() {
		return _hierarchyDefinition.getHierarchyName();
	}

	/**
	* Returns the hierarchy type of this hierarchy definition.
	*
	* @return the hierarchy type of this hierarchy definition
	*/
	@Override
	public int getHierarchyType() {
		return _hierarchyDefinition.getHierarchyType();
	}

	/**
	* Returns the modified by of this hierarchy definition.
	*
	* @return the modified by of this hierarchy definition
	*/
	@Override
	public int getModifiedBy() {
		return _hierarchyDefinition.getModifiedBy();
	}

	/**
	* Returns the modified date of this hierarchy definition.
	*
	* @return the modified date of this hierarchy definition
	*/
	@Override
	public Date getModifiedDate() {
		return _hierarchyDefinition.getModifiedDate();
	}

	/**
	* Returns the no of levels of this hierarchy definition.
	*
	* @return the no of levels of this hierarchy definition
	*/
	@Override
	public int getNoOfLevels() {
		return _hierarchyDefinition.getNoOfLevels();
	}

	/**
	* Returns the primary key of this hierarchy definition.
	*
	* @return the primary key of this hierarchy definition
	*/
	@Override
	public int getPrimaryKey() {
		return _hierarchyDefinition.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _hierarchyDefinition.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this hierarchy definition.
	*
	* @return the version no of this hierarchy definition
	*/
	@Override
	public int getVersionNo() {
		return _hierarchyDefinition.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _hierarchyDefinition.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _hierarchyDefinition.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _hierarchyDefinition.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _hierarchyDefinition.isNew();
	}

	@Override
	public void persist() {
		_hierarchyDefinition.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_hierarchyDefinition.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hierarchy definition.
	*
	* @param createdBy the created by of this hierarchy definition
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_hierarchyDefinition.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hierarchy definition.
	*
	* @param createdDate the created date of this hierarchy definition
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_hierarchyDefinition.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_hierarchyDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_hierarchyDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_hierarchyDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hierarchy category of this hierarchy definition.
	*
	* @param hierarchyCategory the hierarchy category of this hierarchy definition
	*/
	@Override
	public void setHierarchyCategory(int hierarchyCategory) {
		_hierarchyDefinition.setHierarchyCategory(hierarchyCategory);
	}

	/**
	* Sets the hierarchy definition sid of this hierarchy definition.
	*
	* @param hierarchyDefinitionSid the hierarchy definition sid of this hierarchy definition
	*/
	@Override
	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		_hierarchyDefinition.setHierarchyDefinitionSid(hierarchyDefinitionSid);
	}

	/**
	* Sets the hierarchy name of this hierarchy definition.
	*
	* @param hierarchyName the hierarchy name of this hierarchy definition
	*/
	@Override
	public void setHierarchyName(java.lang.String hierarchyName) {
		_hierarchyDefinition.setHierarchyName(hierarchyName);
	}

	/**
	* Sets the hierarchy type of this hierarchy definition.
	*
	* @param hierarchyType the hierarchy type of this hierarchy definition
	*/
	@Override
	public void setHierarchyType(int hierarchyType) {
		_hierarchyDefinition.setHierarchyType(hierarchyType);
	}

	/**
	* Sets the modified by of this hierarchy definition.
	*
	* @param modifiedBy the modified by of this hierarchy definition
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_hierarchyDefinition.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hierarchy definition.
	*
	* @param modifiedDate the modified date of this hierarchy definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_hierarchyDefinition.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_hierarchyDefinition.setNew(n);
	}

	/**
	* Sets the no of levels of this hierarchy definition.
	*
	* @param noOfLevels the no of levels of this hierarchy definition
	*/
	@Override
	public void setNoOfLevels(int noOfLevels) {
		_hierarchyDefinition.setNoOfLevels(noOfLevels);
	}

	/**
	* Sets the primary key of this hierarchy definition.
	*
	* @param primaryKey the primary key of this hierarchy definition
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_hierarchyDefinition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_hierarchyDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this hierarchy definition.
	*
	* @param versionNo the version no of this hierarchy definition
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_hierarchyDefinition.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HierarchyDefinition> toCacheModel() {
		return _hierarchyDefinition.toCacheModel();
	}

	@Override
	public HierarchyDefinition toEscapedModel() {
		return new HierarchyDefinitionWrapper(_hierarchyDefinition.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hierarchyDefinition.toString();
	}

	@Override
	public HierarchyDefinition toUnescapedModel() {
		return new HierarchyDefinitionWrapper(_hierarchyDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _hierarchyDefinition.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HierarchyDefinitionWrapper)) {
			return false;
		}

		HierarchyDefinitionWrapper hierarchyDefinitionWrapper = (HierarchyDefinitionWrapper)obj;

		if (Objects.equals(_hierarchyDefinition,
					hierarchyDefinitionWrapper._hierarchyDefinition)) {
			return true;
		}

		return false;
	}

	@Override
	public HierarchyDefinition getWrappedModel() {
		return _hierarchyDefinition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _hierarchyDefinition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _hierarchyDefinition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_hierarchyDefinition.resetOriginalValues();
	}

	private final HierarchyDefinition _hierarchyDefinition;
}