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
 * This class is a wrapper for {@link NaProjectionSelection}.
 * </p>
 *
 * @author
 * @see NaProjectionSelection
 * @generated
 */
@ProviderType
public class NaProjectionSelectionWrapper implements NaProjectionSelection,
	ModelWrapper<NaProjectionSelection> {
	public NaProjectionSelectionWrapper(
		NaProjectionSelection naProjectionSelection) {
		_naProjectionSelection = naProjectionSelection;
	}

	@Override
	public Class<?> getModelClass() {
		return NaProjectionSelection.class;
	}

	@Override
	public String getModelClassName() {
		return NaProjectionSelection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("screenName", getScreenName());
		attributes.put("fieldName", getFieldName());
		attributes.put("fieldValues", getFieldValues());
		attributes.put("naProjectionSelectionSid", getNaProjectionSelectionSid());
		attributes.put("naProjMasterSid", getNaProjMasterSid());

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

		String fieldValues = (String)attributes.get("fieldValues");

		if (fieldValues != null) {
			setFieldValues(fieldValues);
		}

		Integer naProjectionSelectionSid = (Integer)attributes.get(
				"naProjectionSelectionSid");

		if (naProjectionSelectionSid != null) {
			setNaProjectionSelectionSid(naProjectionSelectionSid);
		}

		Integer naProjMasterSid = (Integer)attributes.get("naProjMasterSid");

		if (naProjMasterSid != null) {
			setNaProjMasterSid(naProjMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NaProjectionSelectionWrapper((NaProjectionSelection)_naProjectionSelection.clone());
	}

	@Override
	public int compareTo(NaProjectionSelection naProjectionSelection) {
		return _naProjectionSelection.compareTo(naProjectionSelection);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _naProjectionSelection.getExpandoBridge();
	}

	/**
	* Returns the field name of this na projection selection.
	*
	* @return the field name of this na projection selection
	*/
	@Override
	public java.lang.String getFieldName() {
		return _naProjectionSelection.getFieldName();
	}

	/**
	* Returns the field values of this na projection selection.
	*
	* @return the field values of this na projection selection
	*/
	@Override
	public java.lang.String getFieldValues() {
		return _naProjectionSelection.getFieldValues();
	}

	/**
	* Returns the na projection selection sid of this na projection selection.
	*
	* @return the na projection selection sid of this na projection selection
	*/
	@Override
	public int getNaProjectionSelectionSid() {
		return _naProjectionSelection.getNaProjectionSelectionSid();
	}

	/**
	* Returns the na proj master sid of this na projection selection.
	*
	* @return the na proj master sid of this na projection selection
	*/
	@Override
	public int getNaProjMasterSid() {
		return _naProjectionSelection.getNaProjMasterSid();
	}

	/**
	* Returns the primary key of this na projection selection.
	*
	* @return the primary key of this na projection selection
	*/
	@Override
	public int getPrimaryKey() {
		return _naProjectionSelection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _naProjectionSelection.getPrimaryKeyObj();
	}

	/**
	* Returns the screen name of this na projection selection.
	*
	* @return the screen name of this na projection selection
	*/
	@Override
	public java.lang.String getScreenName() {
		return _naProjectionSelection.getScreenName();
	}

	@Override
	public int hashCode() {
		return _naProjectionSelection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _naProjectionSelection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _naProjectionSelection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _naProjectionSelection.isNew();
	}

	@Override
	public void persist() {
		_naProjectionSelection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_naProjectionSelection.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_naProjectionSelection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_naProjectionSelection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_naProjectionSelection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field name of this na projection selection.
	*
	* @param fieldName the field name of this na projection selection
	*/
	@Override
	public void setFieldName(java.lang.String fieldName) {
		_naProjectionSelection.setFieldName(fieldName);
	}

	/**
	* Sets the field values of this na projection selection.
	*
	* @param fieldValues the field values of this na projection selection
	*/
	@Override
	public void setFieldValues(java.lang.String fieldValues) {
		_naProjectionSelection.setFieldValues(fieldValues);
	}

	/**
	* Sets the na projection selection sid of this na projection selection.
	*
	* @param naProjectionSelectionSid the na projection selection sid of this na projection selection
	*/
	@Override
	public void setNaProjectionSelectionSid(int naProjectionSelectionSid) {
		_naProjectionSelection.setNaProjectionSelectionSid(naProjectionSelectionSid);
	}

	/**
	* Sets the na proj master sid of this na projection selection.
	*
	* @param naProjMasterSid the na proj master sid of this na projection selection
	*/
	@Override
	public void setNaProjMasterSid(int naProjMasterSid) {
		_naProjectionSelection.setNaProjMasterSid(naProjMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_naProjectionSelection.setNew(n);
	}

	/**
	* Sets the primary key of this na projection selection.
	*
	* @param primaryKey the primary key of this na projection selection
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_naProjectionSelection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_naProjectionSelection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the screen name of this na projection selection.
	*
	* @param screenName the screen name of this na projection selection
	*/
	@Override
	public void setScreenName(java.lang.String screenName) {
		_naProjectionSelection.setScreenName(screenName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NaProjectionSelection> toCacheModel() {
		return _naProjectionSelection.toCacheModel();
	}

	@Override
	public NaProjectionSelection toEscapedModel() {
		return new NaProjectionSelectionWrapper(_naProjectionSelection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _naProjectionSelection.toString();
	}

	@Override
	public NaProjectionSelection toUnescapedModel() {
		return new NaProjectionSelectionWrapper(_naProjectionSelection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _naProjectionSelection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NaProjectionSelectionWrapper)) {
			return false;
		}

		NaProjectionSelectionWrapper naProjectionSelectionWrapper = (NaProjectionSelectionWrapper)obj;

		if (Objects.equals(_naProjectionSelection,
					naProjectionSelectionWrapper._naProjectionSelection)) {
			return true;
		}

		return false;
	}

	@Override
	public NaProjectionSelection getWrappedModel() {
		return _naProjectionSelection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _naProjectionSelection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _naProjectionSelection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_naProjectionSelection.resetOriginalValues();
	}

	private final NaProjectionSelection _naProjectionSelection;
}