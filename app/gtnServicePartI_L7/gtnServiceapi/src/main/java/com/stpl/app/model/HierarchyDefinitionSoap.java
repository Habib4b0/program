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
public class HierarchyDefinitionSoap implements Serializable {
	public static HierarchyDefinitionSoap toSoapModel(HierarchyDefinition model) {
		HierarchyDefinitionSoap soapModel = new HierarchyDefinitionSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setNoOfLevels(model.getNoOfLevels());
		soapModel.setHierarchyType(model.getHierarchyType());
		soapModel.setHierarchyName(model.getHierarchyName());
		soapModel.setHierarchyDefinitionSid(model.getHierarchyDefinitionSid());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHierarchyCategory(model.getHierarchyCategory());

		return soapModel;
	}

	public static HierarchyDefinitionSoap[] toSoapModels(
		HierarchyDefinition[] models) {
		HierarchyDefinitionSoap[] soapModels = new HierarchyDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HierarchyDefinitionSoap[][] toSoapModels(
		HierarchyDefinition[][] models) {
		HierarchyDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HierarchyDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HierarchyDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HierarchyDefinitionSoap[] toSoapModels(
		List<HierarchyDefinition> models) {
		List<HierarchyDefinitionSoap> soapModels = new ArrayList<HierarchyDefinitionSoap>(models.size());

		for (HierarchyDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HierarchyDefinitionSoap[soapModels.size()]);
	}

	public HierarchyDefinitionSoap() {
	}

	public int getPrimaryKey() {
		return _hierarchyDefinitionSid;
	}

	public void setPrimaryKey(int pk) {
		setHierarchyDefinitionSid(pk);
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getNoOfLevels() {
		return _noOfLevels;
	}

	public void setNoOfLevels(int noOfLevels) {
		_noOfLevels = noOfLevels;
	}

	public int getHierarchyType() {
		return _hierarchyType;
	}

	public void setHierarchyType(int hierarchyType) {
		_hierarchyType = hierarchyType;
	}

	public String getHierarchyName() {
		return _hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		_hierarchyName = hierarchyName;
	}

	public int getHierarchyDefinitionSid() {
		return _hierarchyDefinitionSid;
	}

	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		_hierarchyDefinitionSid = hierarchyDefinitionSid;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getHierarchyCategory() {
		return _hierarchyCategory;
	}

	public void setHierarchyCategory(int hierarchyCategory) {
		_hierarchyCategory = hierarchyCategory;
	}

	private Date _createdDate;
	private int _createdBy;
	private int _noOfLevels;
	private int _hierarchyType;
	private String _hierarchyName;
	private int _hierarchyDefinitionSid;
	private int _versionNo;
	private int _modifiedBy;
	private Date _modifiedDate;
	private int _hierarchyCategory;
}