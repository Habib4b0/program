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
 * This class is a wrapper for {@link ChProjectionSelection}.
 * </p>
 *
 * @author
 * @see ChProjectionSelection
 * @generated
 */
@ProviderType
public class ChProjectionSelectionWrapper implements ChProjectionSelection,
	ModelWrapper<ChProjectionSelection> {
	public ChProjectionSelectionWrapper(
		ChProjectionSelection chProjectionSelection) {
		_chProjectionSelection = chProjectionSelection;
	}

	@Override
	public Class<?> getModelClass() {
		return ChProjectionSelection.class;
	}

	@Override
	public String getModelClassName() {
		return ChProjectionSelection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("screenName", getScreenName());
		attributes.put("fieldName", getFieldName());
		attributes.put("chProjectionSelectionSid", getChProjectionSelectionSid());
		attributes.put("fieldValues", getFieldValues());
		attributes.put("projectionMasterSid", getProjectionMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String screenName = (String)attributes.get("screenName");

		if (screenName != null) {
			setScreenName(screenName);
		}

		String fieldName = (String)attributes.get("fieldName");

		if (fieldName != null) {
			setFieldName(fieldName);
		}

		Integer chProjectionSelectionSid = (Integer)attributes.get(
				"chProjectionSelectionSid");

		if (chProjectionSelectionSid != null) {
			setChProjectionSelectionSid(chProjectionSelectionSid);
		}

		String fieldValues = (String)attributes.get("fieldValues");

		if (fieldValues != null) {
			setFieldValues(fieldValues);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ChProjectionSelectionWrapper((ChProjectionSelection)_chProjectionSelection.clone());
	}

	@Override
	public int compareTo(ChProjectionSelection chProjectionSelection) {
		return _chProjectionSelection.compareTo(chProjectionSelection);
	}

	/**
	* Returns the ch projection selection sid of this ch projection selection.
	*
	* @return the ch projection selection sid of this ch projection selection
	*/
	@Override
	public int getChProjectionSelectionSid() {
		return _chProjectionSelection.getChProjectionSelectionSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _chProjectionSelection.getExpandoBridge();
	}

	/**
	* Returns the field name of this ch projection selection.
	*
	* @return the field name of this ch projection selection
	*/
	@Override
	public java.lang.String getFieldName() {
		return _chProjectionSelection.getFieldName();
	}

	/**
	* Returns the field values of this ch projection selection.
	*
	* @return the field values of this ch projection selection
	*/
	@Override
	public java.lang.String getFieldValues() {
		return _chProjectionSelection.getFieldValues();
	}

	/**
	* Returns the primary key of this ch projection selection.
	*
	* @return the primary key of this ch projection selection
	*/
	@Override
	public int getPrimaryKey() {
		return _chProjectionSelection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chProjectionSelection.getPrimaryKeyObj();
	}

	/**
	* Returns the projection master sid of this ch projection selection.
	*
	* @return the projection master sid of this ch projection selection
	*/
	@Override
	public int getProjectionMasterSid() {
		return _chProjectionSelection.getProjectionMasterSid();
	}

	/**
	* Returns the screen name of this ch projection selection.
	*
	* @return the screen name of this ch projection selection
	*/
	@Override
	public java.lang.String getScreenName() {
		return _chProjectionSelection.getScreenName();
	}

	@Override
	public int hashCode() {
		return _chProjectionSelection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _chProjectionSelection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _chProjectionSelection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _chProjectionSelection.isNew();
	}

	@Override
	public void persist() {
		_chProjectionSelection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_chProjectionSelection.setCachedModel(cachedModel);
	}

	/**
	* Sets the ch projection selection sid of this ch projection selection.
	*
	* @param chProjectionSelectionSid the ch projection selection sid of this ch projection selection
	*/
	@Override
	public void setChProjectionSelectionSid(int chProjectionSelectionSid) {
		_chProjectionSelection.setChProjectionSelectionSid(chProjectionSelectionSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_chProjectionSelection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_chProjectionSelection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_chProjectionSelection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field name of this ch projection selection.
	*
	* @param fieldName the field name of this ch projection selection
	*/
	@Override
	public void setFieldName(java.lang.String fieldName) {
		_chProjectionSelection.setFieldName(fieldName);
	}

	/**
	* Sets the field values of this ch projection selection.
	*
	* @param fieldValues the field values of this ch projection selection
	*/
	@Override
	public void setFieldValues(java.lang.String fieldValues) {
		_chProjectionSelection.setFieldValues(fieldValues);
	}

	@Override
	public void setNew(boolean n) {
		_chProjectionSelection.setNew(n);
	}

	/**
	* Sets the primary key of this ch projection selection.
	*
	* @param primaryKey the primary key of this ch projection selection
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_chProjectionSelection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_chProjectionSelection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection master sid of this ch projection selection.
	*
	* @param projectionMasterSid the projection master sid of this ch projection selection
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_chProjectionSelection.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the screen name of this ch projection selection.
	*
	* @param screenName the screen name of this ch projection selection
	*/
	@Override
	public void setScreenName(java.lang.String screenName) {
		_chProjectionSelection.setScreenName(screenName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ChProjectionSelection> toCacheModel() {
		return _chProjectionSelection.toCacheModel();
	}

	@Override
	public ChProjectionSelection toEscapedModel() {
		return new ChProjectionSelectionWrapper(_chProjectionSelection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _chProjectionSelection.toString();
	}

	@Override
	public ChProjectionSelection toUnescapedModel() {
		return new ChProjectionSelectionWrapper(_chProjectionSelection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _chProjectionSelection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChProjectionSelectionWrapper)) {
			return false;
		}

		ChProjectionSelectionWrapper chProjectionSelectionWrapper = (ChProjectionSelectionWrapper)obj;

		if (Objects.equals(_chProjectionSelection,
					chProjectionSelectionWrapper._chProjectionSelection)) {
			return true;
		}

		return false;
	}

	@Override
	public ChProjectionSelection getWrappedModel() {
		return _chProjectionSelection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _chProjectionSelection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _chProjectionSelection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_chProjectionSelection.resetOriginalValues();
	}

	private final ChProjectionSelection _chProjectionSelection;
}