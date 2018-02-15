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
public class RelationshipLevelDefinitionSoap implements Serializable {
	public static RelationshipLevelDefinitionSoap toSoapModel(
		RelationshipLevelDefinition model) {
		RelationshipLevelDefinitionSoap soapModel = new RelationshipLevelDefinitionSoap();

		soapModel.setRelationshipLevelValues(model.getRelationshipLevelValues());
		soapModel.setHierarchyLevelDefinitionSid(model.getHierarchyLevelDefinitionSid());
		soapModel.setParentNode(model.getParentNode());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setRelationshipBuilderSid(model.getRelationshipBuilderSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setLevelNo(model.getLevelNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setHierarchyNo(model.getHierarchyNo());
		soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());
		soapModel.setFlag(model.getFlag());
		soapModel.setLevelName(model.getLevelName());
		soapModel.setParentHierarchyNo(model.getParentHierarchyNo());

		return soapModel;
	}

	public static RelationshipLevelDefinitionSoap[] toSoapModels(
		RelationshipLevelDefinition[] models) {
		RelationshipLevelDefinitionSoap[] soapModels = new RelationshipLevelDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RelationshipLevelDefinitionSoap[][] toSoapModels(
		RelationshipLevelDefinition[][] models) {
		RelationshipLevelDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RelationshipLevelDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RelationshipLevelDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RelationshipLevelDefinitionSoap[] toSoapModels(
		List<RelationshipLevelDefinition> models) {
		List<RelationshipLevelDefinitionSoap> soapModels = new ArrayList<RelationshipLevelDefinitionSoap>(models.size());

		for (RelationshipLevelDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RelationshipLevelDefinitionSoap[soapModels.size()]);
	}

	public RelationshipLevelDefinitionSoap() {
	}

	public int getPrimaryKey() {
		return _relationshipLevelSid;
	}

	public void setPrimaryKey(int pk) {
		setRelationshipLevelSid(pk);
	}

	public String getRelationshipLevelValues() {
		return _relationshipLevelValues;
	}

	public void setRelationshipLevelValues(String relationshipLevelValues) {
		_relationshipLevelValues = relationshipLevelValues;
	}

	public int getHierarchyLevelDefinitionSid() {
		return _hierarchyLevelDefinitionSid;
	}

	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		_hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
	}

	public String getParentNode() {
		return _parentNode;
	}

	public void setParentNode(String parentNode) {
		_parentNode = parentNode;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public int getRelationshipBuilderSid() {
		return _relationshipBuilderSid;
	}

	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		_relationshipBuilderSid = relationshipBuilderSid;
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

	public String getLevelNo() {
		return _levelNo;
	}

	public void setLevelNo(String levelNo) {
		_levelNo = levelNo;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getHierarchyNo() {
		return _hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		_hierarchyNo = hierarchyNo;
	}

	public int getRelationshipLevelSid() {
		return _relationshipLevelSid;
	}

	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_relationshipLevelSid = relationshipLevelSid;
	}

	public String getFlag() {
		return _flag;
	}

	public void setFlag(String flag) {
		_flag = flag;
	}

	public String getLevelName() {
		return _levelName;
	}

	public void setLevelName(String levelName) {
		_levelName = levelName;
	}

	public String getParentHierarchyNo() {
		return _parentHierarchyNo;
	}

	public void setParentHierarchyNo(String parentHierarchyNo) {
		_parentHierarchyNo = parentHierarchyNo;
	}

	private String _relationshipLevelValues;
	private int _hierarchyLevelDefinitionSid;
	private String _parentNode;
	private int _versionNo;
	private int _relationshipBuilderSid;
	private Date _modifiedDate;
	private int _createdBy;
	private Date _createdDate;
	private String _levelNo;
	private int _modifiedBy;
	private String _hierarchyNo;
	private int _relationshipLevelSid;
	private String _flag;
	private String _levelName;
	private String _parentHierarchyNo;
}