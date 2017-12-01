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
 * This class is a wrapper for {@link HierarchyLevelValues}.
 * </p>
 *
 * @author
 * @see HierarchyLevelValues
 * @generated
 */
@ProviderType
public class HierarchyLevelValuesWrapper implements HierarchyLevelValues,
	ModelWrapper<HierarchyLevelValues> {
	public HierarchyLevelValuesWrapper(
		HierarchyLevelValues hierarchyLevelValues) {
		_hierarchyLevelValues = hierarchyLevelValues;
	}

	@Override
	public Class<?> getModelClass() {
		return HierarchyLevelValues.class;
	}

	@Override
	public String getModelClassName() {
		return HierarchyLevelValues.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("levelValues", getLevelValues());
		attributes.put("hierarchyLevelValuesSid", getHierarchyLevelValuesSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("hierarchyLevelDefinitionSid",
			getHierarchyLevelDefinitionSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String levelValues = (String)attributes.get("levelValues");

		if (levelValues != null) {
			setLevelValues(levelValues);
		}

		Integer hierarchyLevelValuesSid = (Integer)attributes.get(
				"hierarchyLevelValuesSid");

		if (hierarchyLevelValuesSid != null) {
			setHierarchyLevelValuesSid(hierarchyLevelValuesSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
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

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HierarchyLevelValuesWrapper((HierarchyLevelValues)_hierarchyLevelValues.clone());
	}

	@Override
	public int compareTo(HierarchyLevelValues hierarchyLevelValues) {
		return _hierarchyLevelValues.compareTo(hierarchyLevelValues);
	}

	/**
	* Returns the created by of this hierarchy level values.
	*
	* @return the created by of this hierarchy level values
	*/
	@Override
	public int getCreatedBy() {
		return _hierarchyLevelValues.getCreatedBy();
	}

	/**
	* Returns the created date of this hierarchy level values.
	*
	* @return the created date of this hierarchy level values
	*/
	@Override
	public Date getCreatedDate() {
		return _hierarchyLevelValues.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _hierarchyLevelValues.getExpandoBridge();
	}

	/**
	* Returns the hierarchy level definition sid of this hierarchy level values.
	*
	* @return the hierarchy level definition sid of this hierarchy level values
	*/
	@Override
	public int getHierarchyLevelDefinitionSid() {
		return _hierarchyLevelValues.getHierarchyLevelDefinitionSid();
	}

	/**
	* Returns the hierarchy level values sid of this hierarchy level values.
	*
	* @return the hierarchy level values sid of this hierarchy level values
	*/
	@Override
	public int getHierarchyLevelValuesSid() {
		return _hierarchyLevelValues.getHierarchyLevelValuesSid();
	}

	/**
	* Returns the level values of this hierarchy level values.
	*
	* @return the level values of this hierarchy level values
	*/
	@Override
	public java.lang.String getLevelValues() {
		return _hierarchyLevelValues.getLevelValues();
	}

	/**
	* Returns the modified by of this hierarchy level values.
	*
	* @return the modified by of this hierarchy level values
	*/
	@Override
	public int getModifiedBy() {
		return _hierarchyLevelValues.getModifiedBy();
	}

	/**
	* Returns the modified date of this hierarchy level values.
	*
	* @return the modified date of this hierarchy level values
	*/
	@Override
	public Date getModifiedDate() {
		return _hierarchyLevelValues.getModifiedDate();
	}

	/**
	* Returns the primary key of this hierarchy level values.
	*
	* @return the primary key of this hierarchy level values
	*/
	@Override
	public int getPrimaryKey() {
		return _hierarchyLevelValues.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _hierarchyLevelValues.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this hierarchy level values.
	*
	* @return the version no of this hierarchy level values
	*/
	@Override
	public int getVersionNo() {
		return _hierarchyLevelValues.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _hierarchyLevelValues.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _hierarchyLevelValues.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _hierarchyLevelValues.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _hierarchyLevelValues.isNew();
	}

	@Override
	public void persist() {
		_hierarchyLevelValues.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_hierarchyLevelValues.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hierarchy level values.
	*
	* @param createdBy the created by of this hierarchy level values
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_hierarchyLevelValues.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hierarchy level values.
	*
	* @param createdDate the created date of this hierarchy level values
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_hierarchyLevelValues.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_hierarchyLevelValues.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_hierarchyLevelValues.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_hierarchyLevelValues.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hierarchy level definition sid of this hierarchy level values.
	*
	* @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hierarchy level values
	*/
	@Override
	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		_hierarchyLevelValues.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
	}

	/**
	* Sets the hierarchy level values sid of this hierarchy level values.
	*
	* @param hierarchyLevelValuesSid the hierarchy level values sid of this hierarchy level values
	*/
	@Override
	public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid) {
		_hierarchyLevelValues.setHierarchyLevelValuesSid(hierarchyLevelValuesSid);
	}

	/**
	* Sets the level values of this hierarchy level values.
	*
	* @param levelValues the level values of this hierarchy level values
	*/
	@Override
	public void setLevelValues(java.lang.String levelValues) {
		_hierarchyLevelValues.setLevelValues(levelValues);
	}

	/**
	* Sets the modified by of this hierarchy level values.
	*
	* @param modifiedBy the modified by of this hierarchy level values
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_hierarchyLevelValues.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hierarchy level values.
	*
	* @param modifiedDate the modified date of this hierarchy level values
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_hierarchyLevelValues.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_hierarchyLevelValues.setNew(n);
	}

	/**
	* Sets the primary key of this hierarchy level values.
	*
	* @param primaryKey the primary key of this hierarchy level values
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_hierarchyLevelValues.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_hierarchyLevelValues.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this hierarchy level values.
	*
	* @param versionNo the version no of this hierarchy level values
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_hierarchyLevelValues.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HierarchyLevelValues> toCacheModel() {
		return _hierarchyLevelValues.toCacheModel();
	}

	@Override
	public HierarchyLevelValues toEscapedModel() {
		return new HierarchyLevelValuesWrapper(_hierarchyLevelValues.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hierarchyLevelValues.toString();
	}

	@Override
	public HierarchyLevelValues toUnescapedModel() {
		return new HierarchyLevelValuesWrapper(_hierarchyLevelValues.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _hierarchyLevelValues.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HierarchyLevelValuesWrapper)) {
			return false;
		}

		HierarchyLevelValuesWrapper hierarchyLevelValuesWrapper = (HierarchyLevelValuesWrapper)obj;

		if (Objects.equals(_hierarchyLevelValues,
					hierarchyLevelValuesWrapper._hierarchyLevelValues)) {
			return true;
		}

		return false;
	}

	@Override
	public HierarchyLevelValues getWrappedModel() {
		return _hierarchyLevelValues;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _hierarchyLevelValues.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _hierarchyLevelValues.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_hierarchyLevelValues.resetOriginalValues();
	}

	private final HierarchyLevelValues _hierarchyLevelValues;
}