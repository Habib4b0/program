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

import com.stpl.app.service.persistence.StNmPpaProjectionMasterPK;

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
public class StNmPpaProjectionMasterSoap implements Serializable {
	public static StNmPpaProjectionMasterSoap toSoapModel(
		StNmPpaProjectionMaster model) {
		StNmPpaProjectionMasterSoap soapModel = new StNmPpaProjectionMasterSoap();

		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setUserGroup(model.getUserGroup());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setUserId(model.getUserId());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setPriceBasis(model.getPriceBasis());
		soapModel.setPriceProtectionEndDate(model.getPriceProtectionEndDate());
		soapModel.setPriceProtectionStartDate(model.getPriceProtectionStartDate());
		soapModel.setActualPriceCap(model.getActualPriceCap());

		return soapModel;
	}

	public static StNmPpaProjectionMasterSoap[] toSoapModels(
		StNmPpaProjectionMaster[] models) {
		StNmPpaProjectionMasterSoap[] soapModels = new StNmPpaProjectionMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StNmPpaProjectionMasterSoap[][] toSoapModels(
		StNmPpaProjectionMaster[][] models) {
		StNmPpaProjectionMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StNmPpaProjectionMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StNmPpaProjectionMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StNmPpaProjectionMasterSoap[] toSoapModels(
		List<StNmPpaProjectionMaster> models) {
		List<StNmPpaProjectionMasterSoap> soapModels = new ArrayList<StNmPpaProjectionMasterSoap>(models.size());

		for (StNmPpaProjectionMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StNmPpaProjectionMasterSoap[soapModels.size()]);
	}

	public StNmPpaProjectionMasterSoap() {
	}

	public StNmPpaProjectionMasterPK getPrimaryKey() {
		return new StNmPpaProjectionMasterPK(_projectionDetailsSid, _userId,
			_sessionId);
	}

	public void setPrimaryKey(StNmPpaProjectionMasterPK pk) {
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setUserId(pk.userId);
		setSessionId(pk.sessionId);
	}

	public Date getLastModifiedDate() {
		return _lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		_lastModifiedDate = lastModifiedDate;
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

	public String getUserGroup() {
		return _userGroup;
	}

	public void setUserGroup(String userGroup) {
		_userGroup = userGroup;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	public String getPriceBasis() {
		return _priceBasis;
	}

	public void setPriceBasis(String priceBasis) {
		_priceBasis = priceBasis;
	}

	public Date getPriceProtectionEndDate() {
		return _priceProtectionEndDate;
	}

	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_priceProtectionEndDate = priceProtectionEndDate;
	}

	public Date getPriceProtectionStartDate() {
		return _priceProtectionStartDate;
	}

	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_priceProtectionStartDate = priceProtectionStartDate;
	}

	public double getActualPriceCap() {
		return _actualPriceCap;
	}

	public void setActualPriceCap(double actualPriceCap) {
		_actualPriceCap = actualPriceCap;
	}

	private Date _lastModifiedDate;
	private boolean _checkRecord;
	private String _userGroup;
	private int _projectionDetailsSid;
	private int _userId;
	private int _sessionId;
	private String _priceBasis;
	private Date _priceProtectionEndDate;
	private Date _priceProtectionStartDate;
	private double _actualPriceCap;
}