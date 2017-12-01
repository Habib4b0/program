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
 * This class is a wrapper for {@link HistHierarchyLevelValues}.
 * </p>
 *
 * @author
 * @see HistHierarchyLevelValues
 * @generated
 */
@ProviderType
public class HistHierarchyLevelValuesWrapper implements HistHierarchyLevelValues,
	ModelWrapper<HistHierarchyLevelValues> {
	public HistHierarchyLevelValuesWrapper(
		HistHierarchyLevelValues histHierarchyLevelValues) {
		_histHierarchyLevelValues = histHierarchyLevelValues;
	}

	@Override
	public Class<?> getModelClass() {
		return HistHierarchyLevelValues.class;
	}

	@Override
	public String getModelClassName() {
		return HistHierarchyLevelValues.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("levelValues", getLevelValues());
		attributes.put("hierarchyLevelValuesSid", getHierarchyLevelValuesSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("actionDate", getActionDate());
		attributes.put("actionFlag", getActionFlag());
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

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
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
		return new HistHierarchyLevelValuesWrapper((HistHierarchyLevelValues)_histHierarchyLevelValues.clone());
	}

	@Override
	public int compareTo(HistHierarchyLevelValues histHierarchyLevelValues) {
		return _histHierarchyLevelValues.compareTo(histHierarchyLevelValues);
	}

	/**
	* Returns the action date of this hist hierarchy level values.
	*
	* @return the action date of this hist hierarchy level values
	*/
	@Override
	public Date getActionDate() {
		return _histHierarchyLevelValues.getActionDate();
	}

	/**
	* Returns the action flag of this hist hierarchy level values.
	*
	* @return the action flag of this hist hierarchy level values
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histHierarchyLevelValues.getActionFlag();
	}

	/**
	* Returns the created by of this hist hierarchy level values.
	*
	* @return the created by of this hist hierarchy level values
	*/
	@Override
	public int getCreatedBy() {
		return _histHierarchyLevelValues.getCreatedBy();
	}

	/**
	* Returns the created date of this hist hierarchy level values.
	*
	* @return the created date of this hist hierarchy level values
	*/
	@Override
	public Date getCreatedDate() {
		return _histHierarchyLevelValues.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histHierarchyLevelValues.getExpandoBridge();
	}

	/**
	* Returns the hierarchy level definition sid of this hist hierarchy level values.
	*
	* @return the hierarchy level definition sid of this hist hierarchy level values
	*/
	@Override
	public int getHierarchyLevelDefinitionSid() {
		return _histHierarchyLevelValues.getHierarchyLevelDefinitionSid();
	}

	/**
	* Returns the hierarchy level values sid of this hist hierarchy level values.
	*
	* @return the hierarchy level values sid of this hist hierarchy level values
	*/
	@Override
	public int getHierarchyLevelValuesSid() {
		return _histHierarchyLevelValues.getHierarchyLevelValuesSid();
	}

	/**
	* Returns the level values of this hist hierarchy level values.
	*
	* @return the level values of this hist hierarchy level values
	*/
	@Override
	public java.lang.String getLevelValues() {
		return _histHierarchyLevelValues.getLevelValues();
	}

	/**
	* Returns the modified by of this hist hierarchy level values.
	*
	* @return the modified by of this hist hierarchy level values
	*/
	@Override
	public int getModifiedBy() {
		return _histHierarchyLevelValues.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist hierarchy level values.
	*
	* @return the modified date of this hist hierarchy level values
	*/
	@Override
	public Date getModifiedDate() {
		return _histHierarchyLevelValues.getModifiedDate();
	}

	/**
	* Returns the primary key of this hist hierarchy level values.
	*
	* @return the primary key of this hist hierarchy level values
	*/
	@Override
	public com.stpl.app.service.persistence.HistHierarchyLevelValuesPK getPrimaryKey() {
		return _histHierarchyLevelValues.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histHierarchyLevelValues.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this hist hierarchy level values.
	*
	* @return the version no of this hist hierarchy level values
	*/
	@Override
	public int getVersionNo() {
		return _histHierarchyLevelValues.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histHierarchyLevelValues.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histHierarchyLevelValues.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histHierarchyLevelValues.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histHierarchyLevelValues.isNew();
	}

	@Override
	public void persist() {
		_histHierarchyLevelValues.persist();
	}

	/**
	* Sets the action date of this hist hierarchy level values.
	*
	* @param actionDate the action date of this hist hierarchy level values
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histHierarchyLevelValues.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist hierarchy level values.
	*
	* @param actionFlag the action flag of this hist hierarchy level values
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histHierarchyLevelValues.setActionFlag(actionFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histHierarchyLevelValues.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hist hierarchy level values.
	*
	* @param createdBy the created by of this hist hierarchy level values
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histHierarchyLevelValues.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist hierarchy level values.
	*
	* @param createdDate the created date of this hist hierarchy level values
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histHierarchyLevelValues.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histHierarchyLevelValues.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histHierarchyLevelValues.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histHierarchyLevelValues.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hierarchy level definition sid of this hist hierarchy level values.
	*
	* @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hist hierarchy level values
	*/
	@Override
	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		_histHierarchyLevelValues.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
	}

	/**
	* Sets the hierarchy level values sid of this hist hierarchy level values.
	*
	* @param hierarchyLevelValuesSid the hierarchy level values sid of this hist hierarchy level values
	*/
	@Override
	public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid) {
		_histHierarchyLevelValues.setHierarchyLevelValuesSid(hierarchyLevelValuesSid);
	}

	/**
	* Sets the level values of this hist hierarchy level values.
	*
	* @param levelValues the level values of this hist hierarchy level values
	*/
	@Override
	public void setLevelValues(java.lang.String levelValues) {
		_histHierarchyLevelValues.setLevelValues(levelValues);
	}

	/**
	* Sets the modified by of this hist hierarchy level values.
	*
	* @param modifiedBy the modified by of this hist hierarchy level values
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histHierarchyLevelValues.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist hierarchy level values.
	*
	* @param modifiedDate the modified date of this hist hierarchy level values
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histHierarchyLevelValues.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histHierarchyLevelValues.setNew(n);
	}

	/**
	* Sets the primary key of this hist hierarchy level values.
	*
	* @param primaryKey the primary key of this hist hierarchy level values
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistHierarchyLevelValuesPK primaryKey) {
		_histHierarchyLevelValues.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histHierarchyLevelValues.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this hist hierarchy level values.
	*
	* @param versionNo the version no of this hist hierarchy level values
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histHierarchyLevelValues.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistHierarchyLevelValues> toCacheModel() {
		return _histHierarchyLevelValues.toCacheModel();
	}

	@Override
	public HistHierarchyLevelValues toEscapedModel() {
		return new HistHierarchyLevelValuesWrapper(_histHierarchyLevelValues.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histHierarchyLevelValues.toString();
	}

	@Override
	public HistHierarchyLevelValues toUnescapedModel() {
		return new HistHierarchyLevelValuesWrapper(_histHierarchyLevelValues.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histHierarchyLevelValues.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistHierarchyLevelValuesWrapper)) {
			return false;
		}

		HistHierarchyLevelValuesWrapper histHierarchyLevelValuesWrapper = (HistHierarchyLevelValuesWrapper)obj;

		if (Objects.equals(_histHierarchyLevelValues,
					histHierarchyLevelValuesWrapper._histHierarchyLevelValues)) {
			return true;
		}

		return false;
	}

	@Override
	public HistHierarchyLevelValues getWrappedModel() {
		return _histHierarchyLevelValues;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histHierarchyLevelValues.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histHierarchyLevelValues.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histHierarchyLevelValues.resetOriginalValues();
	}

	private final HistHierarchyLevelValues _histHierarchyLevelValues;
}