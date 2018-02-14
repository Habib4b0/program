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

import com.stpl.app.service.persistence.StMedicaidNewNdcPK;

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
public class StMedicaidNewNdcSoap implements Serializable {
	public static StMedicaidNewNdcSoap toSoapModel(StMedicaidNewNdc model) {
		StMedicaidNewNdcSoap soapModel = new StMedicaidNewNdcSoap();

		soapModel.setForecastAmp(model.getForecastAmp());
		soapModel.setForecastBestprice(model.getForecastBestprice());
		soapModel.setBaseYearCpi(model.getBaseYearCpi());
		soapModel.setNdc9(model.getNdc9());
		soapModel.setUserId(model.getUserId());
		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setWacPrice(model.getWacPrice());
		soapModel.setBaseYearAmp(model.getBaseYearAmp());
		soapModel.setSessionId(model.getSessionId());

		return soapModel;
	}

	public static StMedicaidNewNdcSoap[] toSoapModels(StMedicaidNewNdc[] models) {
		StMedicaidNewNdcSoap[] soapModels = new StMedicaidNewNdcSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StMedicaidNewNdcSoap[][] toSoapModels(
		StMedicaidNewNdc[][] models) {
		StMedicaidNewNdcSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StMedicaidNewNdcSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StMedicaidNewNdcSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StMedicaidNewNdcSoap[] toSoapModels(
		List<StMedicaidNewNdc> models) {
		List<StMedicaidNewNdcSoap> soapModels = new ArrayList<StMedicaidNewNdcSoap>(models.size());

		for (StMedicaidNewNdc model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StMedicaidNewNdcSoap[soapModels.size()]);
	}

	public StMedicaidNewNdcSoap() {
	}

	public StMedicaidNewNdcPK getPrimaryKey() {
		return new StMedicaidNewNdcPK(_ndc9, _userId, _sessionId);
	}

	public void setPrimaryKey(StMedicaidNewNdcPK pk) {
		setNdc9(pk.ndc9);
		setUserId(pk.userId);
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

	public double getBaseYearCpi() {
		return _baseYearCpi;
	}

	public void setBaseYearCpi(double baseYearCpi) {
		_baseYearCpi = baseYearCpi;
	}

	public String getNdc9() {
		return _ndc9;
	}

	public void setNdc9(String ndc9) {
		_ndc9 = ndc9;
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
	private double _baseYearCpi;
	private String _ndc9;
	private int _userId;
	private Date _lastModifiedDate;
	private double _wacPrice;
	private double _baseYearAmp;
	private int _sessionId;
}