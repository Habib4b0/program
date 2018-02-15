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
public class PsContractSoap implements Serializable {
	public static PsContractSoap toSoapModel(PsContract model) {
		PsContractSoap soapModel = new PsContractSoap();

		soapModel.setPsName(model.getPsName());
		soapModel.setPsNo(model.getPsNo());
		soapModel.setCfpContractSid(model.getCfpContractSid());
		soapModel.setPsContractSid(model.getPsContractSid());
		soapModel.setPsType(model.getPsType());
		soapModel.setPsContractAttachedStatus(model.getPsContractAttachedStatus());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPsCategory(model.getPsCategory());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setPsStatus(model.getPsStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setParentPsId(model.getParentPsId());
		soapModel.setPsDesignation(model.getPsDesignation());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setPsModelSid(model.getPsModelSid());
		soapModel.setPsContractAttachedDate(model.getPsContractAttachedDate());
		soapModel.setPsEndDate(model.getPsEndDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setParentPsName(model.getParentPsName());
		soapModel.setPsStartDate(model.getPsStartDate());
		soapModel.setIfpContractSid(model.getIfpContractSid());
		soapModel.setPsTradeClass(model.getPsTradeClass());

		return soapModel;
	}

	public static PsContractSoap[] toSoapModels(PsContract[] models) {
		PsContractSoap[] soapModels = new PsContractSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PsContractSoap[][] toSoapModels(PsContract[][] models) {
		PsContractSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PsContractSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PsContractSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PsContractSoap[] toSoapModels(List<PsContract> models) {
		List<PsContractSoap> soapModels = new ArrayList<PsContractSoap>(models.size());

		for (PsContract model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PsContractSoap[soapModels.size()]);
	}

	public PsContractSoap() {
	}

	public int getPrimaryKey() {
		return _psContractSid;
	}

	public void setPrimaryKey(int pk) {
		setPsContractSid(pk);
	}

	public String getPsName() {
		return _psName;
	}

	public void setPsName(String psName) {
		_psName = psName;
	}

	public String getPsNo() {
		return _psNo;
	}

	public void setPsNo(String psNo) {
		_psNo = psNo;
	}

	public String getCfpContractSid() {
		return _cfpContractSid;
	}

	public void setCfpContractSid(String cfpContractSid) {
		_cfpContractSid = cfpContractSid;
	}

	public int getPsContractSid() {
		return _psContractSid;
	}

	public void setPsContractSid(int psContractSid) {
		_psContractSid = psContractSid;
	}

	public int getPsType() {
		return _psType;
	}

	public void setPsType(int psType) {
		_psType = psType;
	}

	public int getPsContractAttachedStatus() {
		return _psContractAttachedStatus;
	}

	public void setPsContractAttachedStatus(int psContractAttachedStatus) {
		_psContractAttachedStatus = psContractAttachedStatus;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getPsCategory() {
		return _psCategory;
	}

	public void setPsCategory(int psCategory) {
		_psCategory = psCategory;
	}

	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public int getPsStatus() {
		return _psStatus;
	}

	public void setPsStatus(int psStatus) {
		_psStatus = psStatus;
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

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getParentPsId() {
		return _parentPsId;
	}

	public void setParentPsId(String parentPsId) {
		_parentPsId = parentPsId;
	}

	public String getPsDesignation() {
		return _psDesignation;
	}

	public void setPsDesignation(String psDesignation) {
		_psDesignation = psDesignation;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public int getPsModelSid() {
		return _psModelSid;
	}

	public void setPsModelSid(int psModelSid) {
		_psModelSid = psModelSid;
	}

	public Date getPsContractAttachedDate() {
		return _psContractAttachedDate;
	}

	public void setPsContractAttachedDate(Date psContractAttachedDate) {
		_psContractAttachedDate = psContractAttachedDate;
	}

	public Date getPsEndDate() {
		return _psEndDate;
	}

	public void setPsEndDate(Date psEndDate) {
		_psEndDate = psEndDate;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getParentPsName() {
		return _parentPsName;
	}

	public void setParentPsName(String parentPsName) {
		_parentPsName = parentPsName;
	}

	public Date getPsStartDate() {
		return _psStartDate;
	}

	public void setPsStartDate(Date psStartDate) {
		_psStartDate = psStartDate;
	}

	public String getIfpContractSid() {
		return _ifpContractSid;
	}

	public void setIfpContractSid(String ifpContractSid) {
		_ifpContractSid = ifpContractSid;
	}

	public int getPsTradeClass() {
		return _psTradeClass;
	}

	public void setPsTradeClass(int psTradeClass) {
		_psTradeClass = psTradeClass;
	}

	private String _psName;
	private String _psNo;
	private String _cfpContractSid;
	private int _psContractSid;
	private int _psType;
	private int _psContractAttachedStatus;
	private Date _modifiedDate;
	private int _psCategory;
	private boolean _recordLockStatus;
	private int _psStatus;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private String _parentPsId;
	private String _psDesignation;
	private String _batchId;
	private int _contractMasterSid;
	private int _psModelSid;
	private Date _psContractAttachedDate;
	private Date _psEndDate;
	private int _modifiedBy;
	private String _inboundStatus;
	private String _parentPsName;
	private Date _psStartDate;
	private String _ifpContractSid;
	private int _psTradeClass;
}