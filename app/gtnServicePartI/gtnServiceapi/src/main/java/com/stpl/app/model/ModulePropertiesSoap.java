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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class ModulePropertiesSoap implements Serializable {
	public static ModulePropertiesSoap toSoapModel(ModuleProperties model) {
		ModulePropertiesSoap soapModel = new ModulePropertiesSoap();

		soapModel.setModulePropertySid(model.getModulePropertySid());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setModuleName(model.getModuleName());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setNullFlag(model.getNullFlag());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModuleSubmoduleSid(model.getModuleSubmoduleSid());
		soapModel.setCategoryName(model.getCategoryName());
		soapModel.setPropertyName(model.getPropertyName());
		soapModel.setDisplayName(model.getDisplayName());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static ModulePropertiesSoap[] toSoapModels(ModuleProperties[] models) {
		ModulePropertiesSoap[] soapModels = new ModulePropertiesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ModulePropertiesSoap[][] toSoapModels(
		ModuleProperties[][] models) {
		ModulePropertiesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ModulePropertiesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ModulePropertiesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ModulePropertiesSoap[] toSoapModels(
		List<ModuleProperties> models) {
		List<ModulePropertiesSoap> soapModels = new ArrayList<ModulePropertiesSoap>(models.size());

		for (ModuleProperties model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ModulePropertiesSoap[soapModels.size()]);
	}

	public ModulePropertiesSoap() {
	}

	public int getPrimaryKey() {
		return _modulePropertySid;
	}

	public void setPrimaryKey(int pk) {
		setModulePropertySid(pk);
	}

	public int getModulePropertySid() {
		return _modulePropertySid;
	}

	public void setModulePropertySid(int modulePropertySid) {
		_modulePropertySid = modulePropertySid;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public String getModuleName() {
		return _moduleName;
	}

	public void setModuleName(String moduleName) {
		_moduleName = moduleName;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getNullFlag() {
		return _nullFlag;
	}

	public void setNullFlag(String nullFlag) {
		_nullFlag = nullFlag;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public int getModuleSubmoduleSid() {
		return _moduleSubmoduleSid;
	}

	public void setModuleSubmoduleSid(int moduleSubmoduleSid) {
		_moduleSubmoduleSid = moduleSubmoduleSid;
	}

	public String getCategoryName() {
		return _categoryName;
	}

	public void setCategoryName(String categoryName) {
		_categoryName = categoryName;
	}

	public String getPropertyName() {
		return _propertyName;
	}

	public void setPropertyName(String propertyName) {
		_propertyName = propertyName;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private int _modulePropertySid;
	private int _createdBy;
	private String _moduleName;
	private int _modifiedBy;
	private Date _createdDate;
	private String _nullFlag;
	private int _versionNo;
	private int _moduleSubmoduleSid;
	private String _categoryName;
	private String _propertyName;
	private String _displayName;
	private Date _modifiedDate;
}