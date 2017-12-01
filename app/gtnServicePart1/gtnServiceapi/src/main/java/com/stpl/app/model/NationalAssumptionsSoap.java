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

import com.stpl.app.service.persistence.NationalAssumptionsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class NationalAssumptionsSoap implements Serializable {
	public static NationalAssumptionsSoap toSoapModel(NationalAssumptions model) {
		NationalAssumptionsSoap soapModel = new NationalAssumptionsSoap();

		soapModel.setBaselinePeriod(model.getBaselinePeriod());
		soapModel.setFrequency(model.getFrequency());
		soapModel.setStartPeriod(model.getStartPeriod());
		soapModel.setForecastMethodology(model.getForecastMethodology());
		soapModel.setPriceType(model.getPriceType());
		soapModel.setEndPeriod(model.getEndPeriod());
		soapModel.setPriceBasis(model.getPriceBasis());
		soapModel.setNaProjMasterSid(model.getNaProjMasterSid());
		soapModel.setRollingPeriod(model.getRollingPeriod());
		soapModel.setBaselineMethodology(model.getBaselineMethodology());
		soapModel.setGrowthRate(model.getGrowthRate());

		return soapModel;
	}

	public static NationalAssumptionsSoap[] toSoapModels(
		NationalAssumptions[] models) {
		NationalAssumptionsSoap[] soapModels = new NationalAssumptionsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NationalAssumptionsSoap[][] toSoapModels(
		NationalAssumptions[][] models) {
		NationalAssumptionsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NationalAssumptionsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NationalAssumptionsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NationalAssumptionsSoap[] toSoapModels(
		List<NationalAssumptions> models) {
		List<NationalAssumptionsSoap> soapModels = new ArrayList<NationalAssumptionsSoap>(models.size());

		for (NationalAssumptions model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NationalAssumptionsSoap[soapModels.size()]);
	}

	public NationalAssumptionsSoap() {
	}

	public NationalAssumptionsPK getPrimaryKey() {
		return new NationalAssumptionsPK(_startPeriod, _priceType, _endPeriod,
			_naProjMasterSid);
	}

	public void setPrimaryKey(NationalAssumptionsPK pk) {
		setStartPeriod(pk.startPeriod);
		setPriceType(pk.priceType);
		setEndPeriod(pk.endPeriod);
		setNaProjMasterSid(pk.naProjMasterSid);
	}

	public String getBaselinePeriod() {
		return _baselinePeriod;
	}

	public void setBaselinePeriod(String baselinePeriod) {
		_baselinePeriod = baselinePeriod;
	}

	public String getFrequency() {
		return _frequency;
	}

	public void setFrequency(String frequency) {
		_frequency = frequency;
	}

	public String getStartPeriod() {
		return _startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		_startPeriod = startPeriod;
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

	public String getEndPeriod() {
		return _endPeriod;
	}

	public void setEndPeriod(String endPeriod) {
		_endPeriod = endPeriod;
	}

	public String getPriceBasis() {
		return _priceBasis;
	}

	public void setPriceBasis(String priceBasis) {
		_priceBasis = priceBasis;
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

	private String _baselinePeriod;
	private String _frequency;
	private String _startPeriod;
	private String _forecastMethodology;
	private String _priceType;
	private String _endPeriod;
	private String _priceBasis;
	private int _naProjMasterSid;
	private String _rollingPeriod;
	private String _baselineMethodology;
	private double _growthRate;
}