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

import com.stpl.app.service.persistence.MSupplementalDiscActualsPK;

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
public class MSupplementalDiscActualsSoap implements Serializable {
	public static MSupplementalDiscActualsSoap toSoapModel(
		MSupplementalDiscActuals model) {
		MSupplementalDiscActualsSoap soapModel = new MSupplementalDiscActualsSoap();

		soapModel.setActualSales(model.getActualSales());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setActualRate(model.getActualRate());
		soapModel.setActualProjectionSales(model.getActualProjectionSales());
		soapModel.setActualProjectionRate(model.getActualProjectionRate());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());

		return soapModel;
	}

	public static MSupplementalDiscActualsSoap[] toSoapModels(
		MSupplementalDiscActuals[] models) {
		MSupplementalDiscActualsSoap[] soapModels = new MSupplementalDiscActualsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MSupplementalDiscActualsSoap[][] toSoapModels(
		MSupplementalDiscActuals[][] models) {
		MSupplementalDiscActualsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MSupplementalDiscActualsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MSupplementalDiscActualsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MSupplementalDiscActualsSoap[] toSoapModels(
		List<MSupplementalDiscActuals> models) {
		List<MSupplementalDiscActualsSoap> soapModels = new ArrayList<MSupplementalDiscActualsSoap>(models.size());

		for (MSupplementalDiscActuals model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MSupplementalDiscActualsSoap[soapModels.size()]);
	}

	public MSupplementalDiscActualsSoap() {
	}

	public MSupplementalDiscActualsPK getPrimaryKey() {
		return new MSupplementalDiscActualsPK(_periodSid, _projectionDetailsSid);
	}

	public void setPrimaryKey(MSupplementalDiscActualsPK pk) {
		setPeriodSid(pk.periodSid);
		setProjectionDetailsSid(pk.projectionDetailsSid);
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

	private double _actualSales;
	private int _periodSid;
	private double _actualRate;
	private double _actualProjectionSales;
	private double _actualProjectionRate;
	private int _projectionDetailsSid;
}