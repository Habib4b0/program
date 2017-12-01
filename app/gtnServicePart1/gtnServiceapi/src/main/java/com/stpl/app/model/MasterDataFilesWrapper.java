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
 * This class is a wrapper for {@link MasterDataFiles}.
 * </p>
 *
 * @author
 * @see MasterDataFiles
 * @generated
 */
@ProviderType
public class MasterDataFilesWrapper implements MasterDataFiles,
	ModelWrapper<MasterDataFiles> {
	public MasterDataFilesWrapper(MasterDataFiles masterDataFiles) {
		_masterDataFiles = masterDataFiles;
	}

	@Override
	public Class<?> getModelClass() {
		return MasterDataFiles.class;
	}

	@Override
	public String getModelClassName() {
		return MasterDataFiles.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("masterTableSid", getMasterTableSid());
		attributes.put("masterDataFilesSid", getMasterDataFilesSid());
		attributes.put("masterTableName", getMasterTableName());
		attributes.put("filePath", getFilePath());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer masterTableSid = (Integer)attributes.get("masterTableSid");

		if (masterTableSid != null) {
			setMasterTableSid(masterTableSid);
		}

		Integer masterDataFilesSid = (Integer)attributes.get(
				"masterDataFilesSid");

		if (masterDataFilesSid != null) {
			setMasterDataFilesSid(masterDataFilesSid);
		}

		String masterTableName = (String)attributes.get("masterTableName");

		if (masterTableName != null) {
			setMasterTableName(masterTableName);
		}

		String filePath = (String)attributes.get("filePath");

		if (filePath != null) {
			setFilePath(filePath);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MasterDataFilesWrapper((MasterDataFiles)_masterDataFiles.clone());
	}

	@Override
	public int compareTo(MasterDataFiles masterDataFiles) {
		return _masterDataFiles.compareTo(masterDataFiles);
	}

	/**
	* Returns the created by of this master data files.
	*
	* @return the created by of this master data files
	*/
	@Override
	public int getCreatedBy() {
		return _masterDataFiles.getCreatedBy();
	}

	/**
	* Returns the created date of this master data files.
	*
	* @return the created date of this master data files
	*/
	@Override
	public Date getCreatedDate() {
		return _masterDataFiles.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _masterDataFiles.getExpandoBridge();
	}

	/**
	* Returns the file path of this master data files.
	*
	* @return the file path of this master data files
	*/
	@Override
	public java.lang.String getFilePath() {
		return _masterDataFiles.getFilePath();
	}

	/**
	* Returns the master data files sid of this master data files.
	*
	* @return the master data files sid of this master data files
	*/
	@Override
	public int getMasterDataFilesSid() {
		return _masterDataFiles.getMasterDataFilesSid();
	}

	/**
	* Returns the master table name of this master data files.
	*
	* @return the master table name of this master data files
	*/
	@Override
	public java.lang.String getMasterTableName() {
		return _masterDataFiles.getMasterTableName();
	}

	/**
	* Returns the master table sid of this master data files.
	*
	* @return the master table sid of this master data files
	*/
	@Override
	public int getMasterTableSid() {
		return _masterDataFiles.getMasterTableSid();
	}

	/**
	* Returns the primary key of this master data files.
	*
	* @return the primary key of this master data files
	*/
	@Override
	public int getPrimaryKey() {
		return _masterDataFiles.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _masterDataFiles.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _masterDataFiles.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _masterDataFiles.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _masterDataFiles.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _masterDataFiles.isNew();
	}

	@Override
	public void persist() {
		_masterDataFiles.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_masterDataFiles.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this master data files.
	*
	* @param createdBy the created by of this master data files
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_masterDataFiles.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this master data files.
	*
	* @param createdDate the created date of this master data files
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_masterDataFiles.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_masterDataFiles.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_masterDataFiles.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_masterDataFiles.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file path of this master data files.
	*
	* @param filePath the file path of this master data files
	*/
	@Override
	public void setFilePath(java.lang.String filePath) {
		_masterDataFiles.setFilePath(filePath);
	}

	/**
	* Sets the master data files sid of this master data files.
	*
	* @param masterDataFilesSid the master data files sid of this master data files
	*/
	@Override
	public void setMasterDataFilesSid(int masterDataFilesSid) {
		_masterDataFiles.setMasterDataFilesSid(masterDataFilesSid);
	}

	/**
	* Sets the master table name of this master data files.
	*
	* @param masterTableName the master table name of this master data files
	*/
	@Override
	public void setMasterTableName(java.lang.String masterTableName) {
		_masterDataFiles.setMasterTableName(masterTableName);
	}

	/**
	* Sets the master table sid of this master data files.
	*
	* @param masterTableSid the master table sid of this master data files
	*/
	@Override
	public void setMasterTableSid(int masterTableSid) {
		_masterDataFiles.setMasterTableSid(masterTableSid);
	}

	@Override
	public void setNew(boolean n) {
		_masterDataFiles.setNew(n);
	}

	/**
	* Sets the primary key of this master data files.
	*
	* @param primaryKey the primary key of this master data files
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_masterDataFiles.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_masterDataFiles.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MasterDataFiles> toCacheModel() {
		return _masterDataFiles.toCacheModel();
	}

	@Override
	public MasterDataFiles toEscapedModel() {
		return new MasterDataFilesWrapper(_masterDataFiles.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _masterDataFiles.toString();
	}

	@Override
	public MasterDataFiles toUnescapedModel() {
		return new MasterDataFilesWrapper(_masterDataFiles.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _masterDataFiles.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MasterDataFilesWrapper)) {
			return false;
		}

		MasterDataFilesWrapper masterDataFilesWrapper = (MasterDataFilesWrapper)obj;

		if (Objects.equals(_masterDataFiles,
					masterDataFilesWrapper._masterDataFiles)) {
			return true;
		}

		return false;
	}

	@Override
	public MasterDataFiles getWrappedModel() {
		return _masterDataFiles;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _masterDataFiles.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _masterDataFiles.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_masterDataFiles.resetOriginalValues();
	}

	private final MasterDataFiles _masterDataFiles;
}