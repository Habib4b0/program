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

import com.stpl.app.service.persistence.HistHierarchyLevelDefnPK;

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
public class HistHierarchyLevelDefnSoap implements Serializable {
	public static HistHierarchyLevelDefnSoap toSoapModel(
		HistHierarchyLevelDefn model) {
		HistHierarchyLevelDefnSoap soapModel = new HistHierarchyLevelDefnSoap();

		soapModel.setTableName(model.getTableName());
		soapModel.setActionDate(model.getActionDate());
		soapModel.setFieldName(model.getFieldName());
		soapModel.setHierarchyLevelDefinitionSid(model.getHierarchyLevelDefinitionSid());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setLevelValueReference(model.getLevelValueReference());
		soapModel.setLevelNo(model.getLevelNo());
		soapModel.setActionFlag(model.getActionFlag());
		soapModel.setHierarchyDefinitionSid(model.getHierarchyDefinitionSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setLevelName(model.getLevelName());

		return soapModel;
	}

	public static HistHierarchyLevelDefnSoap[] toSoapModels(
		HistHierarchyLevelDefn[] models) {
		HistHierarchyLevelDefnSoap[] soapModels = new HistHierarchyLevelDefnSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HistHierarchyLevelDefnSoap[][] toSoapModels(
		HistHierarchyLevelDefn[][] models) {
		HistHierarchyLevelDefnSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HistHierarchyLevelDefnSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HistHierarchyLevelDefnSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HistHierarchyLevelDefnSoap[] toSoapModels(
		List<HistHierarchyLevelDefn> models) {
		List<HistHierarchyLevelDefnSoap> soapModels = new ArrayList<HistHierarchyLevelDefnSoap>(models.size());

		for (HistHierarchyLevelDefn model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HistHierarchyLevelDefnSoap[soapModels.size()]);
	}

	public HistHierarchyLevelDefnSoap() {
	}

	public HistHierarchyLevelDefnPK getPrimaryKey() {
		return new HistHierarchyLevelDefnPK(_hierarchyLevelDefinitionSid,
			_versionNo, _actionFlag);
	}

	public void setPrimaryKey(HistHierarchyLevelDefnPK pk) {
		setHierarchyLevelDefinitionSid(pk.hierarchyLevelDefinitionSid);
		setVersionNo(pk.versionNo);
		setActionFlag(pk.actionFlag);
	}

	public String getTableName() {
		return _tableName;
	}

	public void setTableName(String tableName) {
		_tableName = tableName;
	}

	public Date getActionDate() {
		return _actionDate;
	}

	public void setActionDate(Date actionDate) {
		_actionDate = actionDate;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public int getHierarchyLevelDefinitionSid() {
		return _hierarchyLevelDefinitionSid;
	}

	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		_hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getLevelValueReference() {
		return _levelValueReference;
	}

	public void setLevelValueReference(String levelValueReference) {
		_levelValueReference = levelValueReference;
	}

	public int getLevelNo() {
		return _levelNo;
	}

	public void setLevelNo(int levelNo) {
		_levelNo = levelNo;
	}

	public String getActionFlag() {
		return _actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		_actionFlag = actionFlag;
	}

	public int getHierarchyDefinitionSid() {
		return _hierarchyDefinitionSid;
	}

	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		_hierarchyDefinitionSid = hierarchyDefinitionSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getLevelName() {
		return _levelName;
	}

	public void setLevelName(String levelName) {
		_levelName = levelName;
	}

	private String _tableName;
	private Date _actionDate;
	private String _fieldName;
	private int _hierarchyLevelDefinitionSid;
	private int _versionNo;
	private Date _modifiedDate;
	private int _createdBy;
	private Date _createdDate;
	private String _levelValueReference;
	private int _levelNo;
	private String _actionFlag;
	private int _hierarchyDefinitionSid;
	private int _modifiedBy;
	private String _levelName;
}