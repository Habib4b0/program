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
public class RebatePlanTierSoap implements Serializable {
	public static RebatePlanTierSoap toSoapModel(RebatePlanTier model) {
		RebatePlanTierSoap soapModel = new RebatePlanTierSoap();

		soapModel.setTierValue(model.getTierValue());
		soapModel.setReturnRateSid(model.getReturnRateSid());
		soapModel.setRebatePlanMasterSid(model.getRebatePlanMasterSid());
		soapModel.setRebatePlanTierSid(model.getRebatePlanTierSid());
		soapModel.setItemPricingQualifierSid(model.getItemPricingQualifierSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTierTolerance(model.getTierTolerance());
		soapModel.setTierFrom(model.getTierFrom());
		soapModel.setTierOperator(model.getTierOperator());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setTierTo(model.getTierTo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setRebatePlanTierId(model.getRebatePlanTierId());
		soapModel.setFreeAmount(model.getFreeAmount());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setTierLevel(model.getTierLevel());
		soapModel.setFormulaNo(model.getFormulaNo());
		soapModel.setFormulaName(model.getFormulaName());
		soapModel.setSecondaryRebatePlanNo(model.getSecondaryRebatePlanNo());
		soapModel.setSecondaryRebatePlanName(model.getSecondaryRebatePlanName());
		soapModel.setSecondaryRebatePlanSid(model.getSecondaryRebatePlanSid());

		return soapModel;
	}

	public static RebatePlanTierSoap[] toSoapModels(RebatePlanTier[] models) {
		RebatePlanTierSoap[] soapModels = new RebatePlanTierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RebatePlanTierSoap[][] toSoapModels(RebatePlanTier[][] models) {
		RebatePlanTierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RebatePlanTierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RebatePlanTierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RebatePlanTierSoap[] toSoapModels(List<RebatePlanTier> models) {
		List<RebatePlanTierSoap> soapModels = new ArrayList<RebatePlanTierSoap>(models.size());

		for (RebatePlanTier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RebatePlanTierSoap[soapModels.size()]);
	}

	public RebatePlanTierSoap() {
	}

	public int getPrimaryKey() {
		return _rebatePlanTierSid;
	}

	public void setPrimaryKey(int pk) {
		setRebatePlanTierSid(pk);
	}

	public double getTierValue() {
		return _tierValue;
	}

	public void setTierValue(double tierValue) {
		_tierValue = tierValue;
	}

	public String getReturnRateSid() {
		return _returnRateSid;
	}

	public void setReturnRateSid(String returnRateSid) {
		_returnRateSid = returnRateSid;
	}

	public int getRebatePlanMasterSid() {
		return _rebatePlanMasterSid;
	}

	public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
		_rebatePlanMasterSid = rebatePlanMasterSid;
	}

	public int getRebatePlanTierSid() {
		return _rebatePlanTierSid;
	}

	public void setRebatePlanTierSid(int rebatePlanTierSid) {
		_rebatePlanTierSid = rebatePlanTierSid;
	}

	public String getItemPricingQualifierSid() {
		return _itemPricingQualifierSid;
	}

	public void setItemPricingQualifierSid(String itemPricingQualifierSid) {
		_itemPricingQualifierSid = itemPricingQualifierSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public double getTierTolerance() {
		return _tierTolerance;
	}

	public void setTierTolerance(double tierTolerance) {
		_tierTolerance = tierTolerance;
	}

	public String getTierFrom() {
		return _tierFrom;
	}

	public void setTierFrom(String tierFrom) {
		_tierFrom = tierFrom;
	}

	public String getTierOperator() {
		return _tierOperator;
	}

	public void setTierOperator(String tierOperator) {
		_tierOperator = tierOperator;
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

	public String getTierTo() {
		return _tierTo;
	}

	public void setTierTo(String tierTo) {
		_tierTo = tierTo;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getRebatePlanTierId() {
		return _rebatePlanTierId;
	}

	public void setRebatePlanTierId(String rebatePlanTierId) {
		_rebatePlanTierId = rebatePlanTierId;
	}

	public double getFreeAmount() {
		return _freeAmount;
	}

	public void setFreeAmount(double freeAmount) {
		_freeAmount = freeAmount;
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

	public String getTierLevel() {
		return _tierLevel;
	}

	public void setTierLevel(String tierLevel) {
		_tierLevel = tierLevel;
	}

	public String getFormulaNo() {
		return _formulaNo;
	}

	public void setFormulaNo(String formulaNo) {
		_formulaNo = formulaNo;
	}

	public String getFormulaName() {
		return _formulaName;
	}

	public void setFormulaName(String formulaName) {
		_formulaName = formulaName;
	}

	public String getSecondaryRebatePlanNo() {
		return _secondaryRebatePlanNo;
	}

	public void setSecondaryRebatePlanNo(String secondaryRebatePlanNo) {
		_secondaryRebatePlanNo = secondaryRebatePlanNo;
	}

	public String getSecondaryRebatePlanName() {
		return _secondaryRebatePlanName;
	}

	public void setSecondaryRebatePlanName(String secondaryRebatePlanName) {
		_secondaryRebatePlanName = secondaryRebatePlanName;
	}

	public String getSecondaryRebatePlanSid() {
		return _secondaryRebatePlanSid;
	}

	public void setSecondaryRebatePlanSid(String secondaryRebatePlanSid) {
		_secondaryRebatePlanSid = secondaryRebatePlanSid;
	}

	private double _tierValue;
	private String _returnRateSid;
	private int _rebatePlanMasterSid;
	private int _rebatePlanTierSid;
	private String _itemPricingQualifierSid;
	private Date _modifiedDate;
	private double _tierTolerance;
	private String _tierFrom;
	private String _tierOperator;
	private boolean _recordLockStatus;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private String _tierTo;
	private String _batchId;
	private String _rebatePlanTierId;
	private double _freeAmount;
	private int _modifiedBy;
	private String _inboundStatus;
	private String _tierLevel;
	private String _formulaNo;
	private String _formulaName;
	private String _secondaryRebatePlanNo;
	private String _secondaryRebatePlanName;
	private String _secondaryRebatePlanSid;
}