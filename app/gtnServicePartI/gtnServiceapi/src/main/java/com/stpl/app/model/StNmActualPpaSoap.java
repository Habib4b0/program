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

import com.stpl.app.service.persistence.StNmActualPpaPK;

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
public class StNmActualPpaSoap implements Serializable {
	public static StNmActualPpaSoap toSoapModel(StNmActualPpa model) {
		StNmActualPpaSoap soapModel = new StNmActualPpaSoap();

		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setActualRate(model.getActualRate());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setActualProjDiscountDollar(model.getActualProjDiscountDollar());
		soapModel.setActualProjectionSales(model.getActualProjectionSales());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setUserId(model.getUserId());
		soapModel.setActualProjectionRate(model.getActualProjectionRate());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setActualProjDiscountUnits(model.getActualProjDiscountUnits());
		soapModel.setActualDiscountDollar(model.getActualDiscountDollar());
		soapModel.setActualDiscountUnits(model.getActualDiscountUnits());
		soapModel.setActualSales(model.getActualSales());

		return soapModel;
	}

	public static StNmActualPpaSoap[] toSoapModels(StNmActualPpa[] models) {
		StNmActualPpaSoap[] soapModels = new StNmActualPpaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StNmActualPpaSoap[][] toSoapModels(StNmActualPpa[][] models) {
		StNmActualPpaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StNmActualPpaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StNmActualPpaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StNmActualPpaSoap[] toSoapModels(List<StNmActualPpa> models) {
		List<StNmActualPpaSoap> soapModels = new ArrayList<StNmActualPpaSoap>(models.size());

		for (StNmActualPpa model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StNmActualPpaSoap[soapModels.size()]);
	}

	public StNmActualPpaSoap() {
	}

	public StNmActualPpaPK getPrimaryKey() {
		return new StNmActualPpaPK(_periodSid, _projectionDetailsSid, _userId,
			_sessionId);
	}

	public void setPrimaryKey(StNmActualPpaPK pk) {
		setPeriodSid(pk.periodSid);
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

	public double getActualRate() {
		return _actualRate;
	}

	public void setActualRate(double actualRate) {
		_actualRate = actualRate;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public double getActualProjDiscountDollar() {
		return _actualProjDiscountDollar;
	}

	public void setActualProjDiscountDollar(double actualProjDiscountDollar) {
		_actualProjDiscountDollar = actualProjDiscountDollar;
	}

	public double getActualProjectionSales() {
		return _actualProjectionSales;
	}

	public void setActualProjectionSales(double actualProjectionSales) {
		_actualProjectionSales = actualProjectionSales;
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

	public double getActualProjectionRate() {
		return _actualProjectionRate;
	}

	public void setActualProjectionRate(double actualProjectionRate) {
		_actualProjectionRate = actualProjectionRate;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	public double getActualProjDiscountUnits() {
		return _actualProjDiscountUnits;
	}

	public void setActualProjDiscountUnits(double actualProjDiscountUnits) {
		_actualProjDiscountUnits = actualProjDiscountUnits;
	}

	public double getActualDiscountDollar() {
		return _actualDiscountDollar;
	}

	public void setActualDiscountDollar(double actualDiscountDollar) {
		_actualDiscountDollar = actualDiscountDollar;
	}

	public double getActualDiscountUnits() {
		return _actualDiscountUnits;
	}

	public void setActualDiscountUnits(double actualDiscountUnits) {
		_actualDiscountUnits = actualDiscountUnits;
	}

	public double getActualSales() {
		return _actualSales;
	}

	public void setActualSales(double actualSales) {
		_actualSales = actualSales;
	}

	private Date _lastModifiedDate;
	private double _actualRate;
	private int _periodSid;
	private double _actualProjDiscountDollar;
	private double _actualProjectionSales;
	private int _projectionDetailsSid;
	private int _userId;
	private double _actualProjectionRate;
	private int _sessionId;
	private double _actualProjDiscountUnits;
	private double _actualDiscountDollar;
	private double _actualDiscountUnits;
	private double _actualSales;
}