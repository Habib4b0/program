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
 * This class is a wrapper for {@link FileManagement}.
 * </p>
 *
 * @author
 * @see FileManagement
 * @generated
 */
@ProviderType
public class FileManagementWrapper implements FileManagement,
	ModelWrapper<FileManagement> {
	public FileManagementWrapper(FileManagement fileManagement) {
		_fileManagement = fileManagement;
	}

	@Override
	public Class<?> getModelClass() {
		return FileManagement.class;
	}

	@Override
	public String getModelClassName() {
		return FileManagement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("country", getCountry());
		attributes.put("fromPeriod", getFromPeriod());
		attributes.put("versionNo", getVersionNo());
		attributes.put("forecastSource", getForecastSource());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("version", getVersion());
		attributes.put("fileSource", getFileSource());
		attributes.put("toPeriod", getToPeriod());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("fileManagementSid", getFileManagementSid());
		attributes.put("forecastName", getForecastName());
		attributes.put("fileType", getFileType());
		attributes.put("businessUnit", getBusinessUnit());
		attributes.put("company", getCompany());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer country = (Integer)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		Date fromPeriod = (Date)attributes.get("fromPeriod");

		if (fromPeriod != null) {
			setFromPeriod(fromPeriod);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		String forecastSource = (String)attributes.get("forecastSource");

		if (forecastSource != null) {
			setForecastSource(forecastSource);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String fileSource = (String)attributes.get("fileSource");

		if (fileSource != null) {
			setFileSource(fileSource);
		}

		Date toPeriod = (Date)attributes.get("toPeriod");

		if (toPeriod != null) {
			setToPeriod(toPeriod);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer fileManagementSid = (Integer)attributes.get("fileManagementSid");

		if (fileManagementSid != null) {
			setFileManagementSid(fileManagementSid);
		}

		String forecastName = (String)attributes.get("forecastName");

		if (forecastName != null) {
			setForecastName(forecastName);
		}

		Integer fileType = (Integer)attributes.get("fileType");

		if (fileType != null) {
			setFileType(fileType);
		}

		String businessUnit = (String)attributes.get("businessUnit");

		if (businessUnit != null) {
			setBusinessUnit(businessUnit);
		}

		Integer company = (Integer)attributes.get("company");

		if (company != null) {
			setCompany(company);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new FileManagementWrapper((FileManagement)_fileManagement.clone());
	}

	@Override
	public int compareTo(FileManagement fileManagement) {
		return _fileManagement.compareTo(fileManagement);
	}

	/**
	* Returns the business unit of this file management.
	*
	* @return the business unit of this file management
	*/
	@Override
	public java.lang.String getBusinessUnit() {
		return _fileManagement.getBusinessUnit();
	}

	/**
	* Returns the company of this file management.
	*
	* @return the company of this file management
	*/
	@Override
	public int getCompany() {
		return _fileManagement.getCompany();
	}

	/**
	* Returns the country of this file management.
	*
	* @return the country of this file management
	*/
	@Override
	public int getCountry() {
		return _fileManagement.getCountry();
	}

	/**
	* Returns the created by of this file management.
	*
	* @return the created by of this file management
	*/
	@Override
	public int getCreatedBy() {
		return _fileManagement.getCreatedBy();
	}

	/**
	* Returns the created date of this file management.
	*
	* @return the created date of this file management
	*/
	@Override
	public Date getCreatedDate() {
		return _fileManagement.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fileManagement.getExpandoBridge();
	}

	/**
	* Returns the file management sid of this file management.
	*
	* @return the file management sid of this file management
	*/
	@Override
	public int getFileManagementSid() {
		return _fileManagement.getFileManagementSid();
	}

	/**
	* Returns the file source of this file management.
	*
	* @return the file source of this file management
	*/
	@Override
	public java.lang.String getFileSource() {
		return _fileManagement.getFileSource();
	}

	/**
	* Returns the file type of this file management.
	*
	* @return the file type of this file management
	*/
	@Override
	public int getFileType() {
		return _fileManagement.getFileType();
	}

	/**
	* Returns the forecast name of this file management.
	*
	* @return the forecast name of this file management
	*/
	@Override
	public java.lang.String getForecastName() {
		return _fileManagement.getForecastName();
	}

	/**
	* Returns the forecast source of this file management.
	*
	* @return the forecast source of this file management
	*/
	@Override
	public java.lang.String getForecastSource() {
		return _fileManagement.getForecastSource();
	}

	/**
	* Returns the from period of this file management.
	*
	* @return the from period of this file management
	*/
	@Override
	public Date getFromPeriod() {
		return _fileManagement.getFromPeriod();
	}

	/**
	* Returns the modified by of this file management.
	*
	* @return the modified by of this file management
	*/
	@Override
	public int getModifiedBy() {
		return _fileManagement.getModifiedBy();
	}

	/**
	* Returns the modified date of this file management.
	*
	* @return the modified date of this file management
	*/
	@Override
	public Date getModifiedDate() {
		return _fileManagement.getModifiedDate();
	}

	/**
	* Returns the primary key of this file management.
	*
	* @return the primary key of this file management
	*/
	@Override
	public int getPrimaryKey() {
		return _fileManagement.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileManagement.getPrimaryKeyObj();
	}

	/**
	* Returns the to period of this file management.
	*
	* @return the to period of this file management
	*/
	@Override
	public Date getToPeriod() {
		return _fileManagement.getToPeriod();
	}

	/**
	* Returns the version of this file management.
	*
	* @return the version of this file management
	*/
	@Override
	public java.lang.String getVersion() {
		return _fileManagement.getVersion();
	}

	/**
	* Returns the version no of this file management.
	*
	* @return the version no of this file management
	*/
	@Override
	public int getVersionNo() {
		return _fileManagement.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _fileManagement.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _fileManagement.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _fileManagement.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _fileManagement.isNew();
	}

	@Override
	public void persist() {
		_fileManagement.persist();
	}

	/**
	* Sets the business unit of this file management.
	*
	* @param businessUnit the business unit of this file management
	*/
	@Override
	public void setBusinessUnit(java.lang.String businessUnit) {
		_fileManagement.setBusinessUnit(businessUnit);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fileManagement.setCachedModel(cachedModel);
	}

	/**
	* Sets the company of this file management.
	*
	* @param company the company of this file management
	*/
	@Override
	public void setCompany(int company) {
		_fileManagement.setCompany(company);
	}

	/**
	* Sets the country of this file management.
	*
	* @param country the country of this file management
	*/
	@Override
	public void setCountry(int country) {
		_fileManagement.setCountry(country);
	}

	/**
	* Sets the created by of this file management.
	*
	* @param createdBy the created by of this file management
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_fileManagement.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this file management.
	*
	* @param createdDate the created date of this file management
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_fileManagement.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_fileManagement.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_fileManagement.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_fileManagement.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file management sid of this file management.
	*
	* @param fileManagementSid the file management sid of this file management
	*/
	@Override
	public void setFileManagementSid(int fileManagementSid) {
		_fileManagement.setFileManagementSid(fileManagementSid);
	}

	/**
	* Sets the file source of this file management.
	*
	* @param fileSource the file source of this file management
	*/
	@Override
	public void setFileSource(java.lang.String fileSource) {
		_fileManagement.setFileSource(fileSource);
	}

	/**
	* Sets the file type of this file management.
	*
	* @param fileType the file type of this file management
	*/
	@Override
	public void setFileType(int fileType) {
		_fileManagement.setFileType(fileType);
	}

	/**
	* Sets the forecast name of this file management.
	*
	* @param forecastName the forecast name of this file management
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_fileManagement.setForecastName(forecastName);
	}

	/**
	* Sets the forecast source of this file management.
	*
	* @param forecastSource the forecast source of this file management
	*/
	@Override
	public void setForecastSource(java.lang.String forecastSource) {
		_fileManagement.setForecastSource(forecastSource);
	}

	/**
	* Sets the from period of this file management.
	*
	* @param fromPeriod the from period of this file management
	*/
	@Override
	public void setFromPeriod(Date fromPeriod) {
		_fileManagement.setFromPeriod(fromPeriod);
	}

	/**
	* Sets the modified by of this file management.
	*
	* @param modifiedBy the modified by of this file management
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_fileManagement.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this file management.
	*
	* @param modifiedDate the modified date of this file management
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_fileManagement.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_fileManagement.setNew(n);
	}

	/**
	* Sets the primary key of this file management.
	*
	* @param primaryKey the primary key of this file management
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_fileManagement.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fileManagement.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the to period of this file management.
	*
	* @param toPeriod the to period of this file management
	*/
	@Override
	public void setToPeriod(Date toPeriod) {
		_fileManagement.setToPeriod(toPeriod);
	}

	/**
	* Sets the version of this file management.
	*
	* @param version the version of this file management
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_fileManagement.setVersion(version);
	}

	/**
	* Sets the version no of this file management.
	*
	* @param versionNo the version no of this file management
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_fileManagement.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FileManagement> toCacheModel() {
		return _fileManagement.toCacheModel();
	}

	@Override
	public FileManagement toEscapedModel() {
		return new FileManagementWrapper(_fileManagement.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _fileManagement.toString();
	}

	@Override
	public FileManagement toUnescapedModel() {
		return new FileManagementWrapper(_fileManagement.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _fileManagement.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileManagementWrapper)) {
			return false;
		}

		FileManagementWrapper fileManagementWrapper = (FileManagementWrapper)obj;

		if (Objects.equals(_fileManagement,
					fileManagementWrapper._fileManagement)) {
			return true;
		}

		return false;
	}

	@Override
	public FileManagement getWrappedModel() {
		return _fileManagement;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _fileManagement.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _fileManagement.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_fileManagement.resetOriginalValues();
	}

	private final FileManagement _fileManagement;
}