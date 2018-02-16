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
 * This class is a wrapper for {@link ImtdLevelValues}.
 * </p>
 *
 * @author
 * @see ImtdLevelValues
 * @generated
 */
@ProviderType
public class ImtdLevelValuesWrapper implements ImtdLevelValues,
	ModelWrapper<ImtdLevelValues> {
	public ImtdLevelValuesWrapper(ImtdLevelValues imtdLevelValues) {
		_imtdLevelValues = imtdLevelValues;
	}

	@Override
	public Class<?> getModelClass() {
		return ImtdLevelValues.class;
	}

	@Override
	public String getModelClassName() {
		return ImtdLevelValues.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("levelValues", getLevelValues());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("imtdLevelValuesSid", getImtdLevelValuesSid());
		attributes.put("levelNo", getLevelNo());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("levelName", getLevelName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String levelValues = (String)attributes.get("levelValues");

		if (levelValues != null) {
			setLevelValues(levelValues);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer imtdLevelValuesSid = (Integer)attributes.get(
				"imtdLevelValuesSid");

		if (imtdLevelValuesSid != null) {
			setImtdLevelValuesSid(imtdLevelValuesSid);
		}

		Integer levelNo = (Integer)attributes.get("levelNo");

		if (levelNo != null) {
			setLevelNo(levelNo);
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
		return new ImtdLevelValuesWrapper((ImtdLevelValues)_imtdLevelValues.clone());
	}

	@Override
	public int compareTo(ImtdLevelValues imtdLevelValues) {
		return _imtdLevelValues.compareTo(imtdLevelValues);
	}

	/**
	* Returns the created by of this imtd level values.
	*
	* @return the created by of this imtd level values
	*/
	@Override
	public int getCreatedBy() {
		return _imtdLevelValues.getCreatedBy();
	}

	/**
	* Returns the created date of this imtd level values.
	*
	* @return the created date of this imtd level values
	*/
	@Override
	public Date getCreatedDate() {
		return _imtdLevelValues.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _imtdLevelValues.getExpandoBridge();
	}

	/**
	* Returns the imtd level values sid of this imtd level values.
	*
	* @return the imtd level values sid of this imtd level values
	*/
	@Override
	public int getImtdLevelValuesSid() {
		return _imtdLevelValues.getImtdLevelValuesSid();
	}

	/**
	* Returns the level name of this imtd level values.
	*
	* @return the level name of this imtd level values
	*/
	@Override
	public java.lang.String getLevelName() {
		return _imtdLevelValues.getLevelName();
	}

	/**
	* Returns the level no of this imtd level values.
	*
	* @return the level no of this imtd level values
	*/
	@Override
	public int getLevelNo() {
		return _imtdLevelValues.getLevelNo();
	}

	/**
	* Returns the level values of this imtd level values.
	*
	* @return the level values of this imtd level values
	*/
	@Override
	public java.lang.String getLevelValues() {
		return _imtdLevelValues.getLevelValues();
	}

	/**
	* Returns the modified by of this imtd level values.
	*
	* @return the modified by of this imtd level values
	*/
	@Override
	public int getModifiedBy() {
		return _imtdLevelValues.getModifiedBy();
	}

	/**
	* Returns the modified date of this imtd level values.
	*
	* @return the modified date of this imtd level values
	*/
	@Override
	public Date getModifiedDate() {
		return _imtdLevelValues.getModifiedDate();
	}

	/**
	* Returns the primary key of this imtd level values.
	*
	* @return the primary key of this imtd level values
	*/
	@Override
	public int getPrimaryKey() {
		return _imtdLevelValues.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _imtdLevelValues.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this imtd level values.
	*
	* @return the version no of this imtd level values
	*/
	@Override
	public int getVersionNo() {
		return _imtdLevelValues.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _imtdLevelValues.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _imtdLevelValues.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _imtdLevelValues.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _imtdLevelValues.isNew();
	}

	@Override
	public void persist() {
		_imtdLevelValues.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_imtdLevelValues.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this imtd level values.
	*
	* @param createdBy the created by of this imtd level values
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_imtdLevelValues.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this imtd level values.
	*
	* @param createdDate the created date of this imtd level values
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_imtdLevelValues.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_imtdLevelValues.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_imtdLevelValues.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_imtdLevelValues.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the imtd level values sid of this imtd level values.
	*
	* @param imtdLevelValuesSid the imtd level values sid of this imtd level values
	*/
	@Override
	public void setImtdLevelValuesSid(int imtdLevelValuesSid) {
		_imtdLevelValues.setImtdLevelValuesSid(imtdLevelValuesSid);
	}

	/**
	* Sets the level name of this imtd level values.
	*
	* @param levelName the level name of this imtd level values
	*/
	@Override
	public void setLevelName(java.lang.String levelName) {
		_imtdLevelValues.setLevelName(levelName);
	}

	/**
	* Sets the level no of this imtd level values.
	*
	* @param levelNo the level no of this imtd level values
	*/
	@Override
	public void setLevelNo(int levelNo) {
		_imtdLevelValues.setLevelNo(levelNo);
	}

	/**
	* Sets the level values of this imtd level values.
	*
	* @param levelValues the level values of this imtd level values
	*/
	@Override
	public void setLevelValues(java.lang.String levelValues) {
		_imtdLevelValues.setLevelValues(levelValues);
	}

	/**
	* Sets the modified by of this imtd level values.
	*
	* @param modifiedBy the modified by of this imtd level values
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_imtdLevelValues.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this imtd level values.
	*
	* @param modifiedDate the modified date of this imtd level values
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_imtdLevelValues.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_imtdLevelValues.setNew(n);
	}

	/**
	* Sets the primary key of this imtd level values.
	*
	* @param primaryKey the primary key of this imtd level values
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_imtdLevelValues.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_imtdLevelValues.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this imtd level values.
	*
	* @param versionNo the version no of this imtd level values
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_imtdLevelValues.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ImtdLevelValues> toCacheModel() {
		return _imtdLevelValues.toCacheModel();
	}

	@Override
	public ImtdLevelValues toEscapedModel() {
		return new ImtdLevelValuesWrapper(_imtdLevelValues.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _imtdLevelValues.toString();
	}

	@Override
	public ImtdLevelValues toUnescapedModel() {
		return new ImtdLevelValuesWrapper(_imtdLevelValues.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _imtdLevelValues.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdLevelValuesWrapper)) {
			return false;
		}

		ImtdLevelValuesWrapper imtdLevelValuesWrapper = (ImtdLevelValuesWrapper)obj;

		if (Objects.equals(_imtdLevelValues,
					imtdLevelValuesWrapper._imtdLevelValues)) {
			return true;
		}

		return false;
	}

	@Override
	public ImtdLevelValues getWrappedModel() {
		return _imtdLevelValues;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _imtdLevelValues.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _imtdLevelValues.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_imtdLevelValues.resetOriginalValues();
	}

	private final ImtdLevelValues _imtdLevelValues;
}