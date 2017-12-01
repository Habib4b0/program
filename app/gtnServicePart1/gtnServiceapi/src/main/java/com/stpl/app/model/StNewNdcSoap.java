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

import com.stpl.app.service.persistence.StNewNdcPK;

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
public class StNewNdcSoap implements Serializable {
	public static StNewNdcSoap toSoapModel(StNewNdc model) {
		StNewNdcSoap soapModel = new StNewNdcSoap();

		soapModel.setForecastAmp(model.getForecastAmp());
		soapModel.setForecastBestprice(model.getForecastBestprice());
		soapModel.setNaProjDetailsSid(model.getNaProjDetailsSid());
		soapModel.setBaseYearCpi(model.getBaseYearCpi());
		soapModel.setUserId(model.getUserId());
		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setWacPrice(model.getWacPrice());
		soapModel.setBaseYearAmp(model.getBaseYearAmp());
		soapModel.setSessionId(model.getSessionId());

		return soapModel;
	}

	public static StNewNdcSoap[] toSoapModels(StNewNdc[] models) {
		StNewNdcSoap[] soapModels = new StNewNdcSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StNewNdcSoap[][] toSoapModels(StNewNdc[][] models) {
		StNewNdcSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StNewNdcSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StNewNdcSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StNewNdcSoap[] toSoapModels(List<StNewNdc> models) {
		List<StNewNdcSoap> soapModels = new ArrayList<StNewNdcSoap>(models.size());

		for (StNewNdc model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StNewNdcSoap[soapModels.size()]);
	}

	public StNewNdcSoap() {
	}

	public StNewNdcPK getPrimaryKey() {
		return new StNewNdcPK(_naProjDetailsSid, _userId, _itemMasterSid,
			_sessionId);
	}

	public void setPrimaryKey(StNewNdcPK pk) {
		setNaProjDetailsSid(pk.naProjDetailsSid);
		setUserId(pk.userId);
		setItemMasterSid(pk.itemMasterSid);
		setSessionId(pk.sessionId);
	}

	public double getForecastAmp() {
		return _forecastAmp;
	}

	public void setForecastAmp(double forecastAmp) {
		_forecastAmp = forecastAmp;
	}

	public double getForecastBestprice() {
		return _forecastBestprice;
	}

	public void setForecastBestprice(double forecastBestprice) {
		_forecastBestprice = forecastBestprice;
	}

	public int getNaProjDetailsSid() {
		return _naProjDetailsSid;
	}

	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_naProjDetailsSid = naProjDetailsSid;
	}

	public double getBaseYearCpi() {
		return _baseYearCpi;
	}

	public void setBaseYearCpi(double baseYearCpi) {
		_baseYearCpi = baseYearCpi;
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

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public double getWacPrice() {
		return _wacPrice;
	}

	public void setWacPrice(double wacPrice) {
		_wacPrice = wacPrice;
	}

	public double getBaseYearAmp() {
		return _baseYearAmp;
	}

	public void setBaseYearAmp(double baseYearAmp) {
		_baseYearAmp = baseYearAmp;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	private double _forecastAmp;
	private double _forecastBestprice;
	private int _naProjDetailsSid;
	private double _baseYearCpi;
	private int _userId;
	private Date _lastModifiedDate;
	private int _itemMasterSid;
	private double _wacPrice;
	private double _baseYearAmp;
	private int _sessionId;
}