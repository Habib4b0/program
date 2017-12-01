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
public class IvldItemHierarchyDefinitionSoap implements Serializable {
	public static IvldItemHierarchyDefinitionSoap toSoapModel(
		IvldItemHierarchyDefinition model) {
		IvldItemHierarchyDefinitionSoap soapModel = new IvldItemHierarchyDefinitionSoap();

		soapModel.setMember(model.getMember());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setItemHierarchyDefnIntfid(model.getItemHierarchyDefnIntfid());
		soapModel.setBpiLvl(model.getBpiLvl());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAlias(model.getAlias());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setSource(model.getSource());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setIvldItemHierarchyDefinitionSid(model.getIvldItemHierarchyDefinitionSid());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static IvldItemHierarchyDefinitionSoap[] toSoapModels(
		IvldItemHierarchyDefinition[] models) {
		IvldItemHierarchyDefinitionSoap[] soapModels = new IvldItemHierarchyDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldItemHierarchyDefinitionSoap[][] toSoapModels(
		IvldItemHierarchyDefinition[][] models) {
		IvldItemHierarchyDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldItemHierarchyDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldItemHierarchyDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldItemHierarchyDefinitionSoap[] toSoapModels(
		List<IvldItemHierarchyDefinition> models) {
		List<IvldItemHierarchyDefinitionSoap> soapModels = new ArrayList<IvldItemHierarchyDefinitionSoap>(models.size());

		for (IvldItemHierarchyDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldItemHierarchyDefinitionSoap[soapModels.size()]);
	}

	public IvldItemHierarchyDefinitionSoap() {
	}

	public int getPrimaryKey() {
		return _ivldItemHierarchyDefinitionSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldItemHierarchyDefinitionSid(pk);
	}

	public String getMember() {
		return _member;
	}

	public void setMember(String member) {
		_member = member;
	}

	public String getReasonForFailure() {
		return _reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		_reasonForFailure = reasonForFailure;
	}

	public String getItemHierarchyDefnIntfid() {
		return _itemHierarchyDefnIntfid;
	}

	public void setItemHierarchyDefnIntfid(String itemHierarchyDefnIntfid) {
		_itemHierarchyDefnIntfid = itemHierarchyDefnIntfid;
	}

	public String getBpiLvl() {
		return _bpiLvl;
	}

	public void setBpiLvl(String bpiLvl) {
		_bpiLvl = bpiLvl;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getAlias() {
		return _alias;
	}

	public void setAlias(String alias) {
		_alias = alias;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public int getIvldItemHierarchyDefinitionSid() {
		return _ivldItemHierarchyDefinitionSid;
	}

	public void setIvldItemHierarchyDefinitionSid(
		int ivldItemHierarchyDefinitionSid) {
		_ivldItemHierarchyDefinitionSid = ivldItemHierarchyDefinitionSid;
	}

	public String getErrorField() {
		return _errorField;
	}

	public void setErrorField(String errorField) {
		_errorField = errorField;
	}

	public String getErrorCode() {
		return _errorCode;
	}

	public void setErrorCode(String errorCode) {
		_errorCode = errorCode;
	}

	public Date getIntfInsertedDate() {
		return _intfInsertedDate;
	}

	public void setIntfInsertedDate(Date intfInsertedDate) {
		_intfInsertedDate = intfInsertedDate;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getReprocessedFlag() {
		return _reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		_reprocessedFlag = reprocessedFlag;
	}

	public boolean getCheckRecord() {
		return _checkRecord;
	}

	public boolean isCheckRecord() {
		return _checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		_checkRecord = checkRecord;
	}

	private String _member;
	private String _reasonForFailure;
	private String _itemHierarchyDefnIntfid;
	private String _bpiLvl;
	private Date _modifiedDate;
	private String _alias;
	private String _createdBy;
	private Date _createdDate;
	private String _source;
	private String _batchId;
	private String _addChgDelIndicator;
	private int _ivldItemHierarchyDefinitionSid;
	private String _errorField;
	private String _errorCode;
	private Date _intfInsertedDate;
	private String _modifiedBy;
	private String _reprocessedFlag;
	private boolean _checkRecord;
}