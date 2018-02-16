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
 * This class is a wrapper for {@link MProjectionSelection}.
 * </p>
 *
 * @author
 * @see MProjectionSelection
 * @generated
 */
@ProviderType
public class MProjectionSelectionWrapper implements MProjectionSelection,
	ModelWrapper<MProjectionSelection> {
	public MProjectionSelectionWrapper(
		MProjectionSelection mProjectionSelection) {
		_mProjectionSelection = mProjectionSelection;
	}

	@Override
	public Class<?> getModelClass() {
		return MProjectionSelection.class;
	}

	@Override
	public String getModelClassName() {
		return MProjectionSelection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mProjectionSelectionSid", getMProjectionSelectionSid());
		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("fieldValues", getFieldValues());
		attributes.put("fieldName", getFieldName());
		attributes.put("screenName", getScreenName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer mProjectionSelectionSid = (Integer)attributes.get(
				"mProjectionSelectionSid");

		if (mProjectionSelectionSid != null) {
			setMProjectionSelectionSid(mProjectionSelectionSid);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}

		String fieldValues = (String)attributes.get("fieldValues");

		if (fieldValues != null) {
			setFieldValues(fieldValues);
		}

		String fieldName = (String)attributes.get("fieldName");

		if (fieldName != null) {
			setFieldName(fieldName);
		}

		String screenName = (String)attributes.get("screenName");

		if (screenName != null) {
			setScreenName(screenName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MProjectionSelectionWrapper((MProjectionSelection)_mProjectionSelection.clone());
	}

	@Override
	public int compareTo(MProjectionSelection mProjectionSelection) {
		return _mProjectionSelection.compareTo(mProjectionSelection);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mProjectionSelection.getExpandoBridge();
	}

	/**
	* Returns the field name of this m projection selection.
	*
	* @return the field name of this m projection selection
	*/
	@Override
	public java.lang.String getFieldName() {
		return _mProjectionSelection.getFieldName();
	}

	/**
	* Returns the field values of this m projection selection.
	*
	* @return the field values of this m projection selection
	*/
	@Override
	public java.lang.String getFieldValues() {
		return _mProjectionSelection.getFieldValues();
	}

	/**
	* Returns the m projection selection sid of this m projection selection.
	*
	* @return the m projection selection sid of this m projection selection
	*/
	@Override
	public int getMProjectionSelectionSid() {
		return _mProjectionSelection.getMProjectionSelectionSid();
	}

	/**
	* Returns the primary key of this m projection selection.
	*
	* @return the primary key of this m projection selection
	*/
	@Override
	public int getPrimaryKey() {
		return _mProjectionSelection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mProjectionSelection.getPrimaryKeyObj();
	}

	/**
	* Returns the projection master sid of this m projection selection.
	*
	* @return the projection master sid of this m projection selection
	*/
	@Override
	public int getProjectionMasterSid() {
		return _mProjectionSelection.getProjectionMasterSid();
	}

	/**
	* Returns the screen name of this m projection selection.
	*
	* @return the screen name of this m projection selection
	*/
	@Override
	public java.lang.String getScreenName() {
		return _mProjectionSelection.getScreenName();
	}

	@Override
	public int hashCode() {
		return _mProjectionSelection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mProjectionSelection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mProjectionSelection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mProjectionSelection.isNew();
	}

	@Override
	public void persist() {
		_mProjectionSelection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mProjectionSelection.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mProjectionSelection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mProjectionSelection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mProjectionSelection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field name of this m projection selection.
	*
	* @param fieldName the field name of this m projection selection
	*/
	@Override
	public void setFieldName(java.lang.String fieldName) {
		_mProjectionSelection.setFieldName(fieldName);
	}

	/**
	* Sets the field values of this m projection selection.
	*
	* @param fieldValues the field values of this m projection selection
	*/
	@Override
	public void setFieldValues(java.lang.String fieldValues) {
		_mProjectionSelection.setFieldValues(fieldValues);
	}

	/**
	* Sets the m projection selection sid of this m projection selection.
	*
	* @param mProjectionSelectionSid the m projection selection sid of this m projection selection
	*/
	@Override
	public void setMProjectionSelectionSid(int mProjectionSelectionSid) {
		_mProjectionSelection.setMProjectionSelectionSid(mProjectionSelectionSid);
	}

	@Override
	public void setNew(boolean n) {
		_mProjectionSelection.setNew(n);
	}

	/**
	* Sets the primary key of this m projection selection.
	*
	* @param primaryKey the primary key of this m projection selection
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_mProjectionSelection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mProjectionSelection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection master sid of this m projection selection.
	*
	* @param projectionMasterSid the projection master sid of this m projection selection
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_mProjectionSelection.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the screen name of this m projection selection.
	*
	* @param screenName the screen name of this m projection selection
	*/
	@Override
	public void setScreenName(java.lang.String screenName) {
		_mProjectionSelection.setScreenName(screenName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MProjectionSelection> toCacheModel() {
		return _mProjectionSelection.toCacheModel();
	}

	@Override
	public MProjectionSelection toEscapedModel() {
		return new MProjectionSelectionWrapper(_mProjectionSelection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mProjectionSelection.toString();
	}

	@Override
	public MProjectionSelection toUnescapedModel() {
		return new MProjectionSelectionWrapper(_mProjectionSelection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _mProjectionSelection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MProjectionSelectionWrapper)) {
			return false;
		}

		MProjectionSelectionWrapper mProjectionSelectionWrapper = (MProjectionSelectionWrapper)obj;

		if (Objects.equals(_mProjectionSelection,
					mProjectionSelectionWrapper._mProjectionSelection)) {
			return true;
		}

		return false;
	}

	@Override
	public MProjectionSelection getWrappedModel() {
		return _mProjectionSelection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mProjectionSelection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mProjectionSelection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mProjectionSelection.resetOriginalValues();
	}

	private final MProjectionSelection _mProjectionSelection;
}