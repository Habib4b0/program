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
 * This class is a wrapper for {@link NmProjectionSelection}.
 * </p>
 *
 * @author
 * @see NmProjectionSelection
 * @generated
 */
@ProviderType
public class NmProjectionSelectionWrapper implements NmProjectionSelection,
	ModelWrapper<NmProjectionSelection> {
	public NmProjectionSelectionWrapper(
		NmProjectionSelection nmProjectionSelection) {
		_nmProjectionSelection = nmProjectionSelection;
	}

	@Override
	public Class<?> getModelClass() {
		return NmProjectionSelection.class;
	}

	@Override
	public String getModelClassName() {
		return NmProjectionSelection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("screenName", getScreenName());
		attributes.put("nmProjectionSelectionSid", getNmProjectionSelectionSid());
		attributes.put("fieldName", getFieldName());
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

		Integer nmProjectionSelectionSid = (Integer)attributes.get(
				"nmProjectionSelectionSid");

		if (nmProjectionSelectionSid != null) {
			setNmProjectionSelectionSid(nmProjectionSelectionSid);
		}

		String fieldName = (String)attributes.get("fieldName");

		if (fieldName != null) {
			setFieldName(fieldName);
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
		return new NmProjectionSelectionWrapper((NmProjectionSelection)_nmProjectionSelection.clone());
	}

	@Override
	public int compareTo(NmProjectionSelection nmProjectionSelection) {
		return _nmProjectionSelection.compareTo(nmProjectionSelection);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmProjectionSelection.getExpandoBridge();
	}

	/**
	* Returns the field name of this nm projection selection.
	*
	* @return the field name of this nm projection selection
	*/
	@Override
	public java.lang.String getFieldName() {
		return _nmProjectionSelection.getFieldName();
	}

	/**
	* Returns the field values of this nm projection selection.
	*
	* @return the field values of this nm projection selection
	*/
	@Override
	public java.lang.String getFieldValues() {
		return _nmProjectionSelection.getFieldValues();
	}

	/**
	* Returns the nm projection selection sid of this nm projection selection.
	*
	* @return the nm projection selection sid of this nm projection selection
	*/
	@Override
	public int getNmProjectionSelectionSid() {
		return _nmProjectionSelection.getNmProjectionSelectionSid();
	}

	/**
	* Returns the primary key of this nm projection selection.
	*
	* @return the primary key of this nm projection selection
	*/
	@Override
	public int getPrimaryKey() {
		return _nmProjectionSelection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmProjectionSelection.getPrimaryKeyObj();
	}

	/**
	* Returns the projection master sid of this nm projection selection.
	*
	* @return the projection master sid of this nm projection selection
	*/
	@Override
	public int getProjectionMasterSid() {
		return _nmProjectionSelection.getProjectionMasterSid();
	}

	/**
	* Returns the screen name of this nm projection selection.
	*
	* @return the screen name of this nm projection selection
	*/
	@Override
	public java.lang.String getScreenName() {
		return _nmProjectionSelection.getScreenName();
	}

	@Override
	public int hashCode() {
		return _nmProjectionSelection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmProjectionSelection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmProjectionSelection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmProjectionSelection.isNew();
	}

	@Override
	public void persist() {
		_nmProjectionSelection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmProjectionSelection.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmProjectionSelection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmProjectionSelection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmProjectionSelection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field name of this nm projection selection.
	*
	* @param fieldName the field name of this nm projection selection
	*/
	@Override
	public void setFieldName(java.lang.String fieldName) {
		_nmProjectionSelection.setFieldName(fieldName);
	}

	/**
	* Sets the field values of this nm projection selection.
	*
	* @param fieldValues the field values of this nm projection selection
	*/
	@Override
	public void setFieldValues(java.lang.String fieldValues) {
		_nmProjectionSelection.setFieldValues(fieldValues);
	}

	@Override
	public void setNew(boolean n) {
		_nmProjectionSelection.setNew(n);
	}

	/**
	* Sets the nm projection selection sid of this nm projection selection.
	*
	* @param nmProjectionSelectionSid the nm projection selection sid of this nm projection selection
	*/
	@Override
	public void setNmProjectionSelectionSid(int nmProjectionSelectionSid) {
		_nmProjectionSelection.setNmProjectionSelectionSid(nmProjectionSelectionSid);
	}

	/**
	* Sets the primary key of this nm projection selection.
	*
	* @param primaryKey the primary key of this nm projection selection
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_nmProjectionSelection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmProjectionSelection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection master sid of this nm projection selection.
	*
	* @param projectionMasterSid the projection master sid of this nm projection selection
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_nmProjectionSelection.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the screen name of this nm projection selection.
	*
	* @param screenName the screen name of this nm projection selection
	*/
	@Override
	public void setScreenName(java.lang.String screenName) {
		_nmProjectionSelection.setScreenName(screenName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmProjectionSelection> toCacheModel() {
		return _nmProjectionSelection.toCacheModel();
	}

	@Override
	public NmProjectionSelection toEscapedModel() {
		return new NmProjectionSelectionWrapper(_nmProjectionSelection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmProjectionSelection.toString();
	}

	@Override
	public NmProjectionSelection toUnescapedModel() {
		return new NmProjectionSelectionWrapper(_nmProjectionSelection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmProjectionSelection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmProjectionSelectionWrapper)) {
			return false;
		}

		NmProjectionSelectionWrapper nmProjectionSelectionWrapper = (NmProjectionSelectionWrapper)obj;

		if (Objects.equals(_nmProjectionSelection,
					nmProjectionSelectionWrapper._nmProjectionSelection)) {
			return true;
		}

		return false;
	}

	@Override
	public NmProjectionSelection getWrappedModel() {
		return _nmProjectionSelection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmProjectionSelection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmProjectionSelection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmProjectionSelection.resetOriginalValues();
	}

	private final NmProjectionSelection _nmProjectionSelection;
}