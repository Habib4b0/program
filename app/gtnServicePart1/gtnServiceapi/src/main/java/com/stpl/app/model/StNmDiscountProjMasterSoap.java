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

import com.stpl.app.service.persistence.StNmDiscountProjMasterPK;

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
public class StNmDiscountProjMasterSoap implements Serializable {
	public static StNmDiscountProjMasterSoap toSoapModel(
		StNmDiscountProjMaster model) {
		StNmDiscountProjMasterSoap soapModel = new StNmDiscountProjMasterSoap();

		soapModel.setSelectedPeriods(model.getSelectedPeriods());
		soapModel.setMethodology(model.getMethodology());
		soapModel.setNetFlag(model.getNetFlag());
		soapModel.setPriceGroupType(model.getPriceGroupType());
		soapModel.setUserGroup(model.getUserGroup());
		soapModel.setUserId(model.getUserId());
		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setBaselinePeriods(model.getBaselinePeriods());

		return soapModel;
	}

	public static StNmDiscountProjMasterSoap[] toSoapModels(
		StNmDiscountProjMaster[] models) {
		StNmDiscountProjMasterSoap[] soapModels = new StNmDiscountProjMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StNmDiscountProjMasterSoap[][] toSoapModels(
		StNmDiscountProjMaster[][] models) {
		StNmDiscountProjMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StNmDiscountProjMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StNmDiscountProjMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StNmDiscountProjMasterSoap[] toSoapModels(
		List<StNmDiscountProjMaster> models) {
		List<StNmDiscountProjMasterSoap> soapModels = new ArrayList<StNmDiscountProjMasterSoap>(models.size());

		for (StNmDiscountProjMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StNmDiscountProjMasterSoap[soapModels.size()]);
	}

	public StNmDiscountProjMasterSoap() {
	}

	public StNmDiscountProjMasterPK getPrimaryKey() {
		return new StNmDiscountProjMasterPK(_userId, _projectionDetailsSid,
			_rsModelSid, _sessionId);
	}

	public void setPrimaryKey(StNmDiscountProjMasterPK pk) {
		setUserId(pk.userId);
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setRsModelSid(pk.rsModelSid);
		setSessionId(pk.sessionId);
	}

	public String getSelectedPeriods() {
		return _selectedPeriods;
	}

	public void setSelectedPeriods(String selectedPeriods) {
		_selectedPeriods = selectedPeriods;
	}

	public String getMethodology() {
		return _methodology;
	}

	public void setMethodology(String methodology) {
		_methodology = methodology;
	}

	public String getNetFlag() {
		return _netFlag;
	}

	public void setNetFlag(String netFlag) {
		_netFlag = netFlag;
	}

	public String getPriceGroupType() {
		return _priceGroupType;
	}

	public void setPriceGroupType(String priceGroupType) {
		_priceGroupType = priceGroupType;
	}

	public String getUserGroup() {
		return _userGroup;
	}

	public void setUserGroup(String userGroup) {
		_userGroup = userGroup;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public Date getLastModifiedDate() {
		return _lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		_lastModifiedDate = lastModifiedDate;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
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

	public String getBaselinePeriods() {
		return _baselinePeriods;
	}

	public void setBaselinePeriods(String baselinePeriods) {
		_baselinePeriods = baselinePeriods;
	}

	private String _selectedPeriods;
	private String _methodology;
	private String _netFlag;
	private String _priceGroupType;
	private String _userGroup;
	private int _userId;
	private Date _lastModifiedDate;
	private int _projectionDetailsSid;
	private int _rsModelSid;
	private int _sessionId;
	private boolean _checkRecord;
	private String _baselinePeriods;
}