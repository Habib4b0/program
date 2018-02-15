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
 * This class is a wrapper for {@link AdditionalNotes}.
 * </p>
 *
 * @author
 * @see AdditionalNotes
 * @generated
 */
@ProviderType
public class AdditionalNotesWrapper implements AdditionalNotes,
	ModelWrapper<AdditionalNotes> {
	public AdditionalNotesWrapper(AdditionalNotes additionalNotes) {
		_additionalNotes = additionalNotes;
	}

	@Override
	public Class<?> getModelClass() {
		return AdditionalNotes.class;
	}

	@Override
	public String getModelClassName() {
		return AdditionalNotes.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("forecastType", getForecastType());
		attributes.put("additionalNotesId", getAdditionalNotesId());
		attributes.put("projectionId", getProjectionId());
		attributes.put("notes", getNotes());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String forecastType = (String)attributes.get("forecastType");

		if (forecastType != null) {
			setForecastType(forecastType);
		}

		Integer additionalNotesId = (Integer)attributes.get("additionalNotesId");

		if (additionalNotesId != null) {
			setAdditionalNotesId(additionalNotesId);
		}

		Integer projectionId = (Integer)attributes.get("projectionId");

		if (projectionId != null) {
			setProjectionId(projectionId);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AdditionalNotesWrapper((AdditionalNotes)_additionalNotes.clone());
	}

	@Override
	public int compareTo(AdditionalNotes additionalNotes) {
		return _additionalNotes.compareTo(additionalNotes);
	}

	/**
	* Returns the additional notes ID of this additional notes.
	*
	* @return the additional notes ID of this additional notes
	*/
	@Override
	public int getAdditionalNotesId() {
		return _additionalNotes.getAdditionalNotesId();
	}

	/**
	* Returns the created by of this additional notes.
	*
	* @return the created by of this additional notes
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _additionalNotes.getCreatedBy();
	}

	/**
	* Returns the created date of this additional notes.
	*
	* @return the created date of this additional notes
	*/
	@Override
	public Date getCreatedDate() {
		return _additionalNotes.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _additionalNotes.getExpandoBridge();
	}

	/**
	* Returns the forecast type of this additional notes.
	*
	* @return the forecast type of this additional notes
	*/
	@Override
	public java.lang.String getForecastType() {
		return _additionalNotes.getForecastType();
	}

	/**
	* Returns the notes of this additional notes.
	*
	* @return the notes of this additional notes
	*/
	@Override
	public java.lang.String getNotes() {
		return _additionalNotes.getNotes();
	}

	/**
	* Returns the primary key of this additional notes.
	*
	* @return the primary key of this additional notes
	*/
	@Override
	public int getPrimaryKey() {
		return _additionalNotes.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _additionalNotes.getPrimaryKeyObj();
	}

	/**
	* Returns the projection ID of this additional notes.
	*
	* @return the projection ID of this additional notes
	*/
	@Override
	public int getProjectionId() {
		return _additionalNotes.getProjectionId();
	}

	@Override
	public int hashCode() {
		return _additionalNotes.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _additionalNotes.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _additionalNotes.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _additionalNotes.isNew();
	}

	@Override
	public void persist() {
		_additionalNotes.persist();
	}

	/**
	* Sets the additional notes ID of this additional notes.
	*
	* @param additionalNotesId the additional notes ID of this additional notes
	*/
	@Override
	public void setAdditionalNotesId(int additionalNotesId) {
		_additionalNotes.setAdditionalNotesId(additionalNotesId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_additionalNotes.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this additional notes.
	*
	* @param createdBy the created by of this additional notes
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_additionalNotes.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this additional notes.
	*
	* @param createdDate the created date of this additional notes
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_additionalNotes.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_additionalNotes.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_additionalNotes.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_additionalNotes.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast type of this additional notes.
	*
	* @param forecastType the forecast type of this additional notes
	*/
	@Override
	public void setForecastType(java.lang.String forecastType) {
		_additionalNotes.setForecastType(forecastType);
	}

	@Override
	public void setNew(boolean n) {
		_additionalNotes.setNew(n);
	}

	/**
	* Sets the notes of this additional notes.
	*
	* @param notes the notes of this additional notes
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_additionalNotes.setNotes(notes);
	}

	/**
	* Sets the primary key of this additional notes.
	*
	* @param primaryKey the primary key of this additional notes
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_additionalNotes.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_additionalNotes.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection ID of this additional notes.
	*
	* @param projectionId the projection ID of this additional notes
	*/
	@Override
	public void setProjectionId(int projectionId) {
		_additionalNotes.setProjectionId(projectionId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AdditionalNotes> toCacheModel() {
		return _additionalNotes.toCacheModel();
	}

	@Override
	public AdditionalNotes toEscapedModel() {
		return new AdditionalNotesWrapper(_additionalNotes.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _additionalNotes.toString();
	}

	@Override
	public AdditionalNotes toUnescapedModel() {
		return new AdditionalNotesWrapper(_additionalNotes.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _additionalNotes.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AdditionalNotesWrapper)) {
			return false;
		}

		AdditionalNotesWrapper additionalNotesWrapper = (AdditionalNotesWrapper)obj;

		if (Objects.equals(_additionalNotes,
					additionalNotesWrapper._additionalNotes)) {
			return true;
		}

		return false;
	}

	@Override
	public AdditionalNotes getWrappedModel() {
		return _additionalNotes;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _additionalNotes.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _additionalNotes.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_additionalNotes.resetOriginalValues();
	}

	private final AdditionalNotes _additionalNotes;
}