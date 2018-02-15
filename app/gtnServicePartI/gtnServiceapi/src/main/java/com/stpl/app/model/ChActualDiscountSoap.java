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

import com.stpl.app.service.persistence.ChActualDiscountPK;

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
public class ChActualDiscountSoap implements Serializable {
	public static ChActualDiscountSoap toSoapModel(ChActualDiscount model) {
		ChActualDiscountSoap soapModel = new ChActualDiscountSoap();

		soapModel.setActualRate(model.getActualRate());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setActualSales(model.getActualSales());

		return soapModel;
	}

	public static ChActualDiscountSoap[] toSoapModels(ChActualDiscount[] models) {
		ChActualDiscountSoap[] soapModels = new ChActualDiscountSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChActualDiscountSoap[][] toSoapModels(
		ChActualDiscount[][] models) {
		ChActualDiscountSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChActualDiscountSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChActualDiscountSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChActualDiscountSoap[] toSoapModels(
		List<ChActualDiscount> models) {
		List<ChActualDiscountSoap> soapModels = new ArrayList<ChActualDiscountSoap>(models.size());

		for (ChActualDiscount model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChActualDiscountSoap[soapModels.size()]);
	}

	public ChActualDiscountSoap() {
	}

	public ChActualDiscountPK getPrimaryKey() {
		return new ChActualDiscountPK(_periodSid, _projectionDetailsSid,
			_rsModelSid);
	}

	public void setPrimaryKey(ChActualDiscountPK pk) {
		setPeriodSid(pk.periodSid);
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setRsModelSid(pk.rsModelSid);
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

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
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
	private int _rsModelSid;
	private double _actualSales;
}