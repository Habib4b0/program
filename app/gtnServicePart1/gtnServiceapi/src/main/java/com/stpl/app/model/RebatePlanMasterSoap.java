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
public class RebatePlanMasterSoap implements Serializable {
	public static RebatePlanMasterSoap toSoapModel(RebatePlanMaster model) {
		RebatePlanMasterSoap soapModel = new RebatePlanMasterSoap();

		soapModel.setSelfGrowthIndicator(model.getSelfGrowthIndicator());
		soapModel.setRebateStructure(model.getRebateStructure());
		soapModel.setMarketShareFrom(model.getMarketShareFrom());
		soapModel.setSecondaryRebatePlanNo(model.getSecondaryRebatePlanNo());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRebateRangeBasedOn(model.getRebateRangeBasedOn());
		soapModel.setCdrModelSid(model.getCdrModelSid());
		soapModel.setRebateRule(model.getRebateRule());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setRebateBasedOn(model.getRebateBasedOn());
		soapModel.setRebatePlanType(model.getRebatePlanType());
		soapModel.setRebatePlanId(model.getRebatePlanId());
		soapModel.setManfCompanyMasterSid(model.getManfCompanyMasterSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setSecondaryRebatePlanId(model.getSecondaryRebatePlanId());
		soapModel.setMarketShareIndicator(model.getMarketShareIndicator());
		soapModel.setBogoEligible(model.getBogoEligible());
		soapModel.setMarketShareTo(model.getMarketShareTo());
		soapModel.setRebatePlanStatus(model.getRebatePlanStatus());
		soapModel.setRebatePlanMasterSid(model.getRebatePlanMasterSid());
		soapModel.setMarketShareReference(model.getMarketShareReference());
		soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
		soapModel.setSelfGrowthFrom(model.getSelfGrowthFrom());
		soapModel.setInternalNotes(model.getInternalNotes());
		soapModel.setSecondaryRebatePlanName(model.getSecondaryRebatePlanName());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setRebatePlanName(model.getRebatePlanName());
		soapModel.setSelfGrowthReference(model.getSelfGrowthReference());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setFormulaType(model.getFormulaType());
		soapModel.setSelfGrowthTo(model.getSelfGrowthTo());
		soapModel.setRebatePlanNo(model.getRebatePlanNo());

		return soapModel;
	}

	public static RebatePlanMasterSoap[] toSoapModels(RebatePlanMaster[] models) {
		RebatePlanMasterSoap[] soapModels = new RebatePlanMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RebatePlanMasterSoap[][] toSoapModels(
		RebatePlanMaster[][] models) {
		RebatePlanMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RebatePlanMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RebatePlanMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RebatePlanMasterSoap[] toSoapModels(
		List<RebatePlanMaster> models) {
		List<RebatePlanMasterSoap> soapModels = new ArrayList<RebatePlanMasterSoap>(models.size());

		for (RebatePlanMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RebatePlanMasterSoap[soapModels.size()]);
	}

	public RebatePlanMasterSoap() {
	}

	public int getPrimaryKey() {
		return _rebatePlanMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setRebatePlanMasterSid(pk);
	}

	public String getSelfGrowthIndicator() {
		return _selfGrowthIndicator;
	}

	public void setSelfGrowthIndicator(String selfGrowthIndicator) {
		_selfGrowthIndicator = selfGrowthIndicator;
	}

	public String getRebateStructure() {
		return _rebateStructure;
	}

	public void setRebateStructure(String rebateStructure) {
		_rebateStructure = rebateStructure;
	}

	public Date getMarketShareFrom() {
		return _marketShareFrom;
	}

	public void setMarketShareFrom(Date marketShareFrom) {
		_marketShareFrom = marketShareFrom;
	}

	public String getSecondaryRebatePlanNo() {
		return _secondaryRebatePlanNo;
	}

	public void setSecondaryRebatePlanNo(String secondaryRebatePlanNo) {
		_secondaryRebatePlanNo = secondaryRebatePlanNo;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getRebateRangeBasedOn() {
		return _rebateRangeBasedOn;
	}

	public void setRebateRangeBasedOn(int rebateRangeBasedOn) {
		_rebateRangeBasedOn = rebateRangeBasedOn;
	}

	public String getCdrModelSid() {
		return _cdrModelSid;
	}

	public void setCdrModelSid(String cdrModelSid) {
		_cdrModelSid = cdrModelSid;
	}

	public String getRebateRule() {
		return _rebateRule;
	}

	public void setRebateRule(String rebateRule) {
		_rebateRule = rebateRule;
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

	public int getRebateBasedOn() {
		return _rebateBasedOn;
	}

	public void setRebateBasedOn(int rebateBasedOn) {
		_rebateBasedOn = rebateBasedOn;
	}

	public int getRebatePlanType() {
		return _rebatePlanType;
	}

	public void setRebatePlanType(int rebatePlanType) {
		_rebatePlanType = rebatePlanType;
	}

	public String getRebatePlanId() {
		return _rebatePlanId;
	}

	public void setRebatePlanId(String rebatePlanId) {
		_rebatePlanId = rebatePlanId;
	}

	public int getManfCompanyMasterSid() {
		return _manfCompanyMasterSid;
	}

	public void setManfCompanyMasterSid(int manfCompanyMasterSid) {
		_manfCompanyMasterSid = manfCompanyMasterSid;
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

	public String getSecondaryRebatePlanId() {
		return _secondaryRebatePlanId;
	}

	public void setSecondaryRebatePlanId(String secondaryRebatePlanId) {
		_secondaryRebatePlanId = secondaryRebatePlanId;
	}

	public String getMarketShareIndicator() {
		return _marketShareIndicator;
	}

	public void setMarketShareIndicator(String marketShareIndicator) {
		_marketShareIndicator = marketShareIndicator;
	}

	public String getBogoEligible() {
		return _bogoEligible;
	}

	public void setBogoEligible(String bogoEligible) {
		_bogoEligible = bogoEligible;
	}

	public Date getMarketShareTo() {
		return _marketShareTo;
	}

	public void setMarketShareTo(Date marketShareTo) {
		_marketShareTo = marketShareTo;
	}

	public int getRebatePlanStatus() {
		return _rebatePlanStatus;
	}

	public void setRebatePlanStatus(int rebatePlanStatus) {
		_rebatePlanStatus = rebatePlanStatus;
	}

	public int getRebatePlanMasterSid() {
		return _rebatePlanMasterSid;
	}

	public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
		_rebatePlanMasterSid = rebatePlanMasterSid;
	}

	public String getMarketShareReference() {
		return _marketShareReference;
	}

	public void setMarketShareReference(String marketShareReference) {
		_marketShareReference = marketShareReference;
	}

	public String getNetSalesFormulaMasterSid() {
		return _netSalesFormulaMasterSid;
	}

	public void setNetSalesFormulaMasterSid(String netSalesFormulaMasterSid) {
		_netSalesFormulaMasterSid = netSalesFormulaMasterSid;
	}

	public Date getSelfGrowthFrom() {
		return _selfGrowthFrom;
	}

	public void setSelfGrowthFrom(Date selfGrowthFrom) {
		_selfGrowthFrom = selfGrowthFrom;
	}

	public String getInternalNotes() {
		return _internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		_internalNotes = internalNotes;
	}

	public String getSecondaryRebatePlanName() {
		return _secondaryRebatePlanName;
	}

	public void setSecondaryRebatePlanName(String secondaryRebatePlanName) {
		_secondaryRebatePlanName = secondaryRebatePlanName;
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

	public String getRebatePlanName() {
		return _rebatePlanName;
	}

	public void setRebatePlanName(String rebatePlanName) {
		_rebatePlanName = rebatePlanName;
	}

	public String getSelfGrowthReference() {
		return _selfGrowthReference;
	}

	public void setSelfGrowthReference(String selfGrowthReference) {
		_selfGrowthReference = selfGrowthReference;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getFormulaType() {
		return _formulaType;
	}

	public void setFormulaType(int formulaType) {
		_formulaType = formulaType;
	}

	public Date getSelfGrowthTo() {
		return _selfGrowthTo;
	}

	public void setSelfGrowthTo(Date selfGrowthTo) {
		_selfGrowthTo = selfGrowthTo;
	}

	public String getRebatePlanNo() {
		return _rebatePlanNo;
	}

	public void setRebatePlanNo(String rebatePlanNo) {
		_rebatePlanNo = rebatePlanNo;
	}

	private String _selfGrowthIndicator;
	private String _rebateStructure;
	private Date _marketShareFrom;
	private String _secondaryRebatePlanNo;
	private Date _modifiedDate;
	private int _rebateRangeBasedOn;
	private String _cdrModelSid;
	private String _rebateRule;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private int _rebateBasedOn;
	private int _rebatePlanType;
	private String _rebatePlanId;
	private int _manfCompanyMasterSid;
	private int _modifiedBy;
	private String _inboundStatus;
	private String _secondaryRebatePlanId;
	private String _marketShareIndicator;
	private String _bogoEligible;
	private Date _marketShareTo;
	private int _rebatePlanStatus;
	private int _rebatePlanMasterSid;
	private String _marketShareReference;
	private String _netSalesFormulaMasterSid;
	private Date _selfGrowthFrom;
	private String _internalNotes;
	private String _secondaryRebatePlanName;
	private boolean _recordLockStatus;
	private String _rebatePlanName;
	private String _selfGrowthReference;
	private String _batchId;
	private int _formulaType;
	private Date _selfGrowthTo;
	private String _rebatePlanNo;
}