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

package com.stpl.app.parttwo.model;

import aQute.bnd.annotation.ProviderType;

import com.stpl.app.parttwo.service.persistence.StArpOutboundPK;

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
public class StArpOutboundSoap implements Serializable {
	public static StArpOutboundSoap toSoapModel(StArpOutbound model) {
		StArpOutboundSoap soapModel = new StArpOutboundSoap();

		soapModel.setSalesUnitsRate(model.getSalesUnitsRate());
		soapModel.setAccountType(model.getAccountType());
		soapModel.setOriginalBatchId(model.getOriginalBatchId());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
		soapModel.setBrandMasterSid(model.getBrandMasterSid());
		soapModel.setArpApprovalDate(model.getArpApprovalDate());
		soapModel.setUserId(model.getUserId());
		soapModel.setArpMasterSid(model.getArpMasterSid());
		soapModel.setArpCreationDate(model.getArpCreationDate());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setArpId(model.getArpId());
		soapModel.setAccount(model.getAccount());
		soapModel.setOutboundStatus(model.getOutboundStatus());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setSessionId(model.getSessionId());

		return soapModel;
	}

	public static StArpOutboundSoap[] toSoapModels(StArpOutbound[] models) {
		StArpOutboundSoap[] soapModels = new StArpOutboundSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StArpOutboundSoap[][] toSoapModels(StArpOutbound[][] models) {
		StArpOutboundSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StArpOutboundSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StArpOutboundSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StArpOutboundSoap[] toSoapModels(List<StArpOutbound> models) {
		List<StArpOutboundSoap> soapModels = new ArrayList<StArpOutboundSoap>(models.size());

		for (StArpOutbound model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StArpOutboundSoap[soapModels.size()]);
	}

	public StArpOutboundSoap() {
	}

	public StArpOutboundPK getPrimaryKey() {
		return new StArpOutboundPK(_companyMasterSid, _userId, _arpMasterSid,
			_arpId, _periodSid, _itemMasterSid, _rsModelSid, _sessionId);
	}

	public void setPrimaryKey(StArpOutboundPK pk) {
		setCompanyMasterSid(pk.companyMasterSid);
		setUserId(pk.userId);
		setArpMasterSid(pk.arpMasterSid);
		setArpId(pk.arpId);
		setPeriodSid(pk.periodSid);
		setItemMasterSid(pk.itemMasterSid);
		setRsModelSid(pk.rsModelSid);
		setSessionId(pk.sessionId);
	}

	public double getSalesUnitsRate() {
		return _salesUnitsRate;
	}

	public void setSalesUnitsRate(double salesUnitsRate) {
		_salesUnitsRate = salesUnitsRate;
	}

	public String getAccountType() {
		return _accountType;
	}

	public void setAccountType(String accountType) {
		_accountType = accountType;
	}

	public String getOriginalBatchId() {
		return _originalBatchId;
	}

	public void setOriginalBatchId(String originalBatchId) {
		_originalBatchId = originalBatchId;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	public int getBrandMasterSid() {
		return _brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		_brandMasterSid = brandMasterSid;
	}

	public Date getArpApprovalDate() {
		return _arpApprovalDate;
	}

	public void setArpApprovalDate(Date arpApprovalDate) {
		_arpApprovalDate = arpApprovalDate;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public int getArpMasterSid() {
		return _arpMasterSid;
	}

	public void setArpMasterSid(int arpMasterSid) {
		_arpMasterSid = arpMasterSid;
	}

	public Date getArpCreationDate() {
		return _arpCreationDate;
	}

	public void setArpCreationDate(Date arpCreationDate) {
		_arpCreationDate = arpCreationDate;
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

	public int getArpId() {
		return _arpId;
	}

	public void setArpId(int arpId) {
		_arpId = arpId;
	}

	public String getAccount() {
		return _account;
	}

	public void setAccount(String account) {
		_account = account;
	}

	public boolean getOutboundStatus() {
		return _outboundStatus;
	}

	public boolean isOutboundStatus() {
		return _outboundStatus;
	}

	public void setOutboundStatus(boolean outboundStatus) {
		_outboundStatus = outboundStatus;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	private double _salesUnitsRate;
	private String _accountType;
	private String _originalBatchId;
	private int _companyMasterSid;
	private int _brandMasterSid;
	private Date _arpApprovalDate;
	private int _userId;
	private int _arpMasterSid;
	private Date _arpCreationDate;
	private boolean _checkRecord;
	private int _arpId;
	private String _account;
	private boolean _outboundStatus;
	private int _periodSid;
	private int _itemMasterSid;
	private int _rsModelSid;
	private String _sessionId;
}