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

import com.stpl.app.service.persistence.NmActualPpaPK;

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
public class NmActualPpaSoap implements Serializable {
	public static NmActualPpaSoap toSoapModel(NmActualPpa model) {
		NmActualPpaSoap soapModel = new NmActualPpaSoap();

		soapModel.setActualRate(model.getActualRate());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setActualDiscountDollar(model.getActualDiscountDollar());
		soapModel.setActualDiscountUnits(model.getActualDiscountUnits());
		soapModel.setActualSales(model.getActualSales());

		return soapModel;
	}

	public static NmActualPpaSoap[] toSoapModels(NmActualPpa[] models) {
		NmActualPpaSoap[] soapModels = new NmActualPpaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NmActualPpaSoap[][] toSoapModels(NmActualPpa[][] models) {
		NmActualPpaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NmActualPpaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NmActualPpaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NmActualPpaSoap[] toSoapModels(List<NmActualPpa> models) {
		List<NmActualPpaSoap> soapModels = new ArrayList<NmActualPpaSoap>(models.size());

		for (NmActualPpa model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NmActualPpaSoap[soapModels.size()]);
	}

	public NmActualPpaSoap() {
	}

	public NmActualPpaPK getPrimaryKey() {
		return new NmActualPpaPK(_periodSid, _projectionDetailsSid);
	}

	public void setPrimaryKey(NmActualPpaPK pk) {
		setPeriodSid(pk.periodSid);
		setProjectionDetailsSid(pk.projectionDetailsSid);
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

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
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

	private double _actualRate;
	private int _periodSid;
	private int _projectionDetailsSid;
	private double _actualDiscountDollar;
	private double _actualDiscountUnits;
	private double _actualSales;
}