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

import com.stpl.app.service.persistence.StMSupplementalDiscActualsPK;

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
public class StMSupplementalDiscActualsSoap implements Serializable {
	public static StMSupplementalDiscActualsSoap toSoapModel(
		StMSupplementalDiscActuals model) {
		StMSupplementalDiscActualsSoap soapModel = new StMSupplementalDiscActualsSoap();

		soapModel.setActualSales(model.getActualSales());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setActualRate(model.getActualRate());
		soapModel.setUserId(model.getUserId());
		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setActualProjectionSales(model.getActualProjectionSales());
		soapModel.setActualProjectionRate(model.getActualProjectionRate());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setSessionId(model.getSessionId());

		return soapModel;
	}

	public static StMSupplementalDiscActualsSoap[] toSoapModels(
		StMSupplementalDiscActuals[] models) {
		StMSupplementalDiscActualsSoap[] soapModels = new StMSupplementalDiscActualsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StMSupplementalDiscActualsSoap[][] toSoapModels(
		StMSupplementalDiscActuals[][] models) {
		StMSupplementalDiscActualsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StMSupplementalDiscActualsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StMSupplementalDiscActualsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StMSupplementalDiscActualsSoap[] toSoapModels(
		List<StMSupplementalDiscActuals> models) {
		List<StMSupplementalDiscActualsSoap> soapModels = new ArrayList<StMSupplementalDiscActualsSoap>(models.size());

		for (StMSupplementalDiscActuals model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StMSupplementalDiscActualsSoap[soapModels.size()]);
	}

	public StMSupplementalDiscActualsSoap() {
	}

	public StMSupplementalDiscActualsPK getPrimaryKey() {
		return new StMSupplementalDiscActualsPK(_userId, _projectionDetailsSid,
			_sessionId);
	}

	public void setPrimaryKey(StMSupplementalDiscActualsPK pk) {
		setUserId(pk.userId);
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setSessionId(pk.sessionId);
	}

	public double getActualSales() {
		return _actualSales;
	}

	public void setActualSales(double actualSales) {
		_actualSales = actualSales;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public double getActualRate() {
		return _actualRate;
	}

	public void setActualRate(double actualRate) {
		_actualRate = actualRate;
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

	public double getActualProjectionSales() {
		return _actualProjectionSales;
	}

	public void setActualProjectionSales(double actualProjectionSales) {
		_actualProjectionSales = actualProjectionSales;
	}

	public double getActualProjectionRate() {
		return _actualProjectionRate;
	}

	public void setActualProjectionRate(double actualProjectionRate) {
		_actualProjectionRate = actualProjectionRate;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	private double _actualSales;
	private int _periodSid;
	private double _actualRate;
	private int _userId;
	private Date _lastModifiedDate;
	private double _actualProjectionSales;
	private double _actualProjectionRate;
	private int _projectionDetailsSid;
	private int _sessionId;
}