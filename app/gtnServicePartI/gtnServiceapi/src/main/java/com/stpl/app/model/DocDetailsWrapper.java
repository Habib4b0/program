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
 * This class is a wrapper for {@link DocDetails}.
 * </p>
 *
 * @author
 * @see DocDetails
 * @generated
 */
@ProviderType
public class DocDetailsWrapper implements DocDetails, ModelWrapper<DocDetails> {
	public DocDetailsWrapper(DocDetails docDetails) {
		_docDetails = docDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return DocDetails.class;
	}

	@Override
	public String getModelClassName() {
		return DocDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fileName", getFileName());
		attributes.put("fileType", getFileType());
		attributes.put("uploadedBy", getUploadedBy());
		attributes.put("forecastType", getForecastType());
		attributes.put("projectionId", getProjectionId());
		attributes.put("docDetailsId", getDocDetailsId());
		attributes.put("uploadedDate", getUploadedDate());
		attributes.put("fileSize", getFileSize());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String fileType = (String)attributes.get("fileType");

		if (fileType != null) {
			setFileType(fileType);
		}

		String uploadedBy = (String)attributes.get("uploadedBy");

		if (uploadedBy != null) {
			setUploadedBy(uploadedBy);
		}

		String forecastType = (String)attributes.get("forecastType");

		if (forecastType != null) {
			setForecastType(forecastType);
		}

		Integer projectionId = (Integer)attributes.get("projectionId");

		if (projectionId != null) {
			setProjectionId(projectionId);
		}

		Integer docDetailsId = (Integer)attributes.get("docDetailsId");

		if (docDetailsId != null) {
			setDocDetailsId(docDetailsId);
		}

		Date uploadedDate = (Date)attributes.get("uploadedDate");

		if (uploadedDate != null) {
			setUploadedDate(uploadedDate);
		}

		String fileSize = (String)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new DocDetailsWrapper((DocDetails)_docDetails.clone());
	}

	@Override
	public int compareTo(DocDetails docDetails) {
		return _docDetails.compareTo(docDetails);
	}

	/**
	* Returns the doc details ID of this doc details.
	*
	* @return the doc details ID of this doc details
	*/
	@Override
	public int getDocDetailsId() {
		return _docDetails.getDocDetailsId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _docDetails.getExpandoBridge();
	}

	/**
	* Returns the file name of this doc details.
	*
	* @return the file name of this doc details
	*/
	@Override
	public java.lang.String getFileName() {
		return _docDetails.getFileName();
	}

	/**
	* Returns the file size of this doc details.
	*
	* @return the file size of this doc details
	*/
	@Override
	public java.lang.String getFileSize() {
		return _docDetails.getFileSize();
	}

	/**
	* Returns the file type of this doc details.
	*
	* @return the file type of this doc details
	*/
	@Override
	public java.lang.String getFileType() {
		return _docDetails.getFileType();
	}

	/**
	* Returns the forecast type of this doc details.
	*
	* @return the forecast type of this doc details
	*/
	@Override
	public java.lang.String getForecastType() {
		return _docDetails.getForecastType();
	}

	/**
	* Returns the primary key of this doc details.
	*
	* @return the primary key of this doc details
	*/
	@Override
	public int getPrimaryKey() {
		return _docDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _docDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the projection ID of this doc details.
	*
	* @return the projection ID of this doc details
	*/
	@Override
	public int getProjectionId() {
		return _docDetails.getProjectionId();
	}

	/**
	* Returns the uploaded by of this doc details.
	*
	* @return the uploaded by of this doc details
	*/
	@Override
	public java.lang.String getUploadedBy() {
		return _docDetails.getUploadedBy();
	}

	/**
	* Returns the uploaded date of this doc details.
	*
	* @return the uploaded date of this doc details
	*/
	@Override
	public Date getUploadedDate() {
		return _docDetails.getUploadedDate();
	}

	@Override
	public int hashCode() {
		return _docDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _docDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _docDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _docDetails.isNew();
	}

	@Override
	public void persist() {
		_docDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_docDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the doc details ID of this doc details.
	*
	* @param docDetailsId the doc details ID of this doc details
	*/
	@Override
	public void setDocDetailsId(int docDetailsId) {
		_docDetails.setDocDetailsId(docDetailsId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_docDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_docDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_docDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this doc details.
	*
	* @param fileName the file name of this doc details
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_docDetails.setFileName(fileName);
	}

	/**
	* Sets the file size of this doc details.
	*
	* @param fileSize the file size of this doc details
	*/
	@Override
	public void setFileSize(java.lang.String fileSize) {
		_docDetails.setFileSize(fileSize);
	}

	/**
	* Sets the file type of this doc details.
	*
	* @param fileType the file type of this doc details
	*/
	@Override
	public void setFileType(java.lang.String fileType) {
		_docDetails.setFileType(fileType);
	}

	/**
	* Sets the forecast type of this doc details.
	*
	* @param forecastType the forecast type of this doc details
	*/
	@Override
	public void setForecastType(java.lang.String forecastType) {
		_docDetails.setForecastType(forecastType);
	}

	@Override
	public void setNew(boolean n) {
		_docDetails.setNew(n);
	}

	/**
	* Sets the primary key of this doc details.
	*
	* @param primaryKey the primary key of this doc details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_docDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_docDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection ID of this doc details.
	*
	* @param projectionId the projection ID of this doc details
	*/
	@Override
	public void setProjectionId(int projectionId) {
		_docDetails.setProjectionId(projectionId);
	}

	/**
	* Sets the uploaded by of this doc details.
	*
	* @param uploadedBy the uploaded by of this doc details
	*/
	@Override
	public void setUploadedBy(java.lang.String uploadedBy) {
		_docDetails.setUploadedBy(uploadedBy);
	}

	/**
	* Sets the uploaded date of this doc details.
	*
	* @param uploadedDate the uploaded date of this doc details
	*/
	@Override
	public void setUploadedDate(Date uploadedDate) {
		_docDetails.setUploadedDate(uploadedDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DocDetails> toCacheModel() {
		return _docDetails.toCacheModel();
	}

	@Override
	public DocDetails toEscapedModel() {
		return new DocDetailsWrapper(_docDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _docDetails.toString();
	}

	@Override
	public DocDetails toUnescapedModel() {
		return new DocDetailsWrapper(_docDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _docDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocDetailsWrapper)) {
			return false;
		}

		DocDetailsWrapper docDetailsWrapper = (DocDetailsWrapper)obj;

		if (Objects.equals(_docDetails, docDetailsWrapper._docDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public DocDetails getWrappedModel() {
		return _docDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _docDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _docDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_docDetails.resetOriginalValues();
	}

	private final DocDetails _docDetails;
}