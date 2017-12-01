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

import com.stpl.app.service.persistence.StNationalAssumptionsPK;

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
public class StNationalAssumptionsSoap implements Serializable {
	public static StNationalAssumptionsSoap toSoapModel(
		StNationalAssumptions model) {
		StNationalAssumptionsSoap soapModel = new StNationalAssumptionsSoap();

		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setBaselinePeriod(model.getBaselinePeriod());
		soapModel.setStartPeriod(model.getStartPeriod());
		soapModel.setFrequency(model.getFrequency());
		soapModel.setUserId(model.getUserId());
		soapModel.setEndPeriod(model.getEndPeriod());
		soapModel.setNaProjMasterSid(model.getNaProjMasterSid());
		soapModel.setRollingPeriod(model.getRollingPeriod());
		soapModel.setForecastMethodology(model.getForecastMethodology());
		soapModel.setPriceType(model.getPriceType());
		soapModel.setPriceBasis(model.getPriceBasis());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setBaselineMethodology(model.getBaselineMethodology());
		soapModel.setGrowthRate(model.getGrowthRate());

		return soapModel;
	}

	public static StNationalAssumptionsSoap[] toSoapModels(
		StNationalAssumptions[] models) {
		StNationalAssumptionsSoap[] soapModels = new StNationalAssumptionsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StNationalAssumptionsSoap[][] toSoapModels(
		StNationalAssumptions[][] models) {
		StNationalAssumptionsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StNationalAssumptionsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StNationalAssumptionsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StNationalAssumptionsSoap[] toSoapModels(
		List<StNationalAssumptions> models) {
		List<StNationalAssumptionsSoap> soapModels = new ArrayList<StNationalAssumptionsSoap>(models.size());

		for (StNationalAssumptions model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StNationalAssumptionsSoap[soapModels.size()]);
	}

	public StNationalAssumptionsSoap() {
	}

	public StNationalAssumptionsPK getPrimaryKey() {
		return new StNationalAssumptionsPK(_startPeriod, _userId, _endPeriod,
			_naProjMasterSid, _priceType, _sessionId);
	}

	public void setPrimaryKey(StNationalAssumptionsPK pk) {
		setStartPeriod(pk.startPeriod);
		setUserId(pk.userId);
		setEndPeriod(pk.endPeriod);
		setNaProjMasterSid(pk.naProjMasterSid);
		setPriceType(pk.priceType);
		setSessionId(pk.sessionId);
	}

	public Date getLastModifiedDate() {
		return _lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		_lastModifiedDate = lastModifiedDate;
	}

	public String getBaselinePeriod() {
		return _baselinePeriod;
	}

	public void setBaselinePeriod(String baselinePeriod) {
		_baselinePeriod = baselinePeriod;
	}

	public String getStartPeriod() {
		return _startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		_startPeriod = startPeriod;
	}

	public String getFrequency() {
		return _frequency;
	}

	public void setFrequency(String frequency) {
		_frequency = frequency;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public String getEndPeriod() {
		return _endPeriod;
	}

	public void setEndPeriod(String endPeriod) {
		_endPeriod = endPeriod;
	}

	public int getNaProjMasterSid() {
		return _naProjMasterSid;
	}

	public void setNaProjMasterSid(int naProjMasterSid) {
		_naProjMasterSid = naProjMasterSid;
	}

	public String getRollingPeriod() {
		return _rollingPeriod;
	}

	public void setRollingPeriod(String rollingPeriod) {
		_rollingPeriod = rollingPeriod;
	}

	public String getForecastMethodology() {
		return _forecastMethodology;
	}

	public void setForecastMethodology(String forecastMethodology) {
		_forecastMethodology = forecastMethodology;
	}

	public String getPriceType() {
		return _priceType;
	}

	public void setPriceType(String priceType) {
		_priceType = priceType;
	}

	public String getPriceBasis() {
		return _priceBasis;
	}

	public void setPriceBasis(String priceBasis) {
		_priceBasis = priceBasis;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	public String getBaselineMethodology() {
		return _baselineMethodology;
	}

	public void setBaselineMethodology(String baselineMethodology) {
		_baselineMethodology = baselineMethodology;
	}

	public double getGrowthRate() {
		return _growthRate;
	}

	public void setGrowthRate(double growthRate) {
		_growthRate = growthRate;
	}

	private Date _lastModifiedDate;
	private String _baselinePeriod;
	private String _startPeriod;
	private String _frequency;
	private int _userId;
	private String _endPeriod;
	private int _naProjMasterSid;
	private String _rollingPeriod;
	private String _forecastMethodology;
	private String _priceType;
	private String _priceBasis;
	private int _sessionId;
	private String _baselineMethodology;
	private double _growthRate;
}