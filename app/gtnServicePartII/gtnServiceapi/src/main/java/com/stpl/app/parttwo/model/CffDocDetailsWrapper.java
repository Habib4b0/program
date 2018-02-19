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

package com.stpl.app.parttwo.model;

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
 * This class is a wrapper for {@link CffDocDetails}.
 * </p>
 *
 * @author
 * @see CffDocDetails
 * @generated
 */
@ProviderType
public class CffDocDetailsWrapper implements CffDocDetails,
	ModelWrapper<CffDocDetails> {
	public CffDocDetailsWrapper(CffDocDetails cffDocDetails) {
		_cffDocDetails = cffDocDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CffDocDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CffDocDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fileName", getFileName());
		attributes.put("uploadDate", getUploadDate());
		attributes.put("fileType", getFileType());
		attributes.put("uploadBy", getUploadBy());
		attributes.put("cffMasterSid", getCffMasterSid());
		attributes.put("cffDocDetailsSid", getCffDocDetailsSid());
		attributes.put("fileSize", getFileSize());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Date uploadDate = (Date)attributes.get("uploadDate");

		if (uploadDate != null) {
			setUploadDate(uploadDate);
		}

		String fileType = (String)attributes.get("fileType");

		if (fileType != null) {
			setFileType(fileType);
		}

		Integer uploadBy = (Integer)attributes.get("uploadBy");

		if (uploadBy != null) {
			setUploadBy(uploadBy);
		}

		Integer cffMasterSid = (Integer)attributes.get("cffMasterSid");

		if (cffMasterSid != null) {
			setCffMasterSid(cffMasterSid);
		}

		Integer cffDocDetailsSid = (Integer)attributes.get("cffDocDetailsSid");

		if (cffDocDetailsSid != null) {
			setCffDocDetailsSid(cffDocDetailsSid);
		}

		String fileSize = (String)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CffDocDetailsWrapper((CffDocDetails)_cffDocDetails.clone());
	}

	@Override
	public int compareTo(CffDocDetails cffDocDetails) {
		return _cffDocDetails.compareTo(cffDocDetails);
	}

	/**
	* Returns the cff doc details sid of this cff doc details.
	*
	* @return the cff doc details sid of this cff doc details
	*/
	@Override
	public int getCffDocDetailsSid() {
		return _cffDocDetails.getCffDocDetailsSid();
	}

	/**
	* Returns the cff master sid of this cff doc details.
	*
	* @return the cff master sid of this cff doc details
	*/
	@Override
	public int getCffMasterSid() {
		return _cffDocDetails.getCffMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffDocDetails.getExpandoBridge();
	}

	/**
	* Returns the file name of this cff doc details.
	*
	* @return the file name of this cff doc details
	*/
	@Override
	public java.lang.String getFileName() {
		return _cffDocDetails.getFileName();
	}

	/**
	* Returns the file size of this cff doc details.
	*
	* @return the file size of this cff doc details
	*/
	@Override
	public java.lang.String getFileSize() {
		return _cffDocDetails.getFileSize();
	}

	/**
	* Returns the file type of this cff doc details.
	*
	* @return the file type of this cff doc details
	*/
	@Override
	public java.lang.String getFileType() {
		return _cffDocDetails.getFileType();
	}

	/**
	* Returns the primary key of this cff doc details.
	*
	* @return the primary key of this cff doc details
	*/
	@Override
	public int getPrimaryKey() {
		return _cffDocDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffDocDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the upload by of this cff doc details.
	*
	* @return the upload by of this cff doc details
	*/
	@Override
	public int getUploadBy() {
		return _cffDocDetails.getUploadBy();
	}

	/**
	* Returns the upload date of this cff doc details.
	*
	* @return the upload date of this cff doc details
	*/
	@Override
	public Date getUploadDate() {
		return _cffDocDetails.getUploadDate();
	}

	@Override
	public int hashCode() {
		return _cffDocDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffDocDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffDocDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffDocDetails.isNew();
	}

	@Override
	public void persist() {
		_cffDocDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffDocDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cff doc details sid of this cff doc details.
	*
	* @param cffDocDetailsSid the cff doc details sid of this cff doc details
	*/
	@Override
	public void setCffDocDetailsSid(int cffDocDetailsSid) {
		_cffDocDetails.setCffDocDetailsSid(cffDocDetailsSid);
	}

	/**
	* Sets the cff master sid of this cff doc details.
	*
	* @param cffMasterSid the cff master sid of this cff doc details
	*/
	@Override
	public void setCffMasterSid(int cffMasterSid) {
		_cffDocDetails.setCffMasterSid(cffMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffDocDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffDocDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffDocDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this cff doc details.
	*
	* @param fileName the file name of this cff doc details
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_cffDocDetails.setFileName(fileName);
	}

	/**
	* Sets the file size of this cff doc details.
	*
	* @param fileSize the file size of this cff doc details
	*/
	@Override
	public void setFileSize(java.lang.String fileSize) {
		_cffDocDetails.setFileSize(fileSize);
	}

	/**
	* Sets the file type of this cff doc details.
	*
	* @param fileType the file type of this cff doc details
	*/
	@Override
	public void setFileType(java.lang.String fileType) {
		_cffDocDetails.setFileType(fileType);
	}

	@Override
	public void setNew(boolean n) {
		_cffDocDetails.setNew(n);
	}

	/**
	* Sets the primary key of this cff doc details.
	*
	* @param primaryKey the primary key of this cff doc details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffDocDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffDocDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the upload by of this cff doc details.
	*
	* @param uploadBy the upload by of this cff doc details
	*/
	@Override
	public void setUploadBy(int uploadBy) {
		_cffDocDetails.setUploadBy(uploadBy);
	}

	/**
	* Sets the upload date of this cff doc details.
	*
	* @param uploadDate the upload date of this cff doc details
	*/
	@Override
	public void setUploadDate(Date uploadDate) {
		_cffDocDetails.setUploadDate(uploadDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffDocDetails> toCacheModel() {
		return _cffDocDetails.toCacheModel();
	}

	@Override
	public CffDocDetails toEscapedModel() {
		return new CffDocDetailsWrapper(_cffDocDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffDocDetails.toString();
	}

	@Override
	public CffDocDetails toUnescapedModel() {
		return new CffDocDetailsWrapper(_cffDocDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffDocDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffDocDetailsWrapper)) {
			return false;
		}

		CffDocDetailsWrapper cffDocDetailsWrapper = (CffDocDetailsWrapper)obj;

		if (Objects.equals(_cffDocDetails, cffDocDetailsWrapper._cffDocDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CffDocDetails getWrappedModel() {
		return _cffDocDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffDocDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffDocDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffDocDetails.resetOriginalValues();
	}

	private final CffDocDetails _cffDocDetails;
}