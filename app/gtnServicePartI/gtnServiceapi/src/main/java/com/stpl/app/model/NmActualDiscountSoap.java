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

import com.stpl.app.service.persistence.NmActualDiscountPK;

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
public class NmActualDiscountSoap implements Serializable {
	public static NmActualDiscountSoap toSoapModel(NmActualDiscount model) {
		NmActualDiscountSoap soapModel = new NmActualDiscountSoap();

		soapModel.setActualRate(model.getActualRate());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setActualSales(model.getActualSales());

		return soapModel;
	}

	public static NmActualDiscountSoap[] toSoapModels(NmActualDiscount[] models) {
		NmActualDiscountSoap[] soapModels = new NmActualDiscountSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NmActualDiscountSoap[][] toSoapModels(
		NmActualDiscount[][] models) {
		NmActualDiscountSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NmActualDiscountSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NmActualDiscountSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NmActualDiscountSoap[] toSoapModels(
		List<NmActualDiscount> models) {
		List<NmActualDiscountSoap> soapModels = new ArrayList<NmActualDiscountSoap>(models.size());

		for (NmActualDiscount model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NmActualDiscountSoap[soapModels.size()]);
	}

	public NmActualDiscountSoap() {
	}

	public NmActualDiscountPK getPrimaryKey() {
		return new NmActualDiscountPK(_periodSid, _projectionDetailsSid);
	}

	public void setPrimaryKey(NmActualDiscountPK pk) {
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

	public double getActualSales() {
		return _actualSales;
	}

	public void setActualSales(double actualSales) {
		_actualSales = actualSales;
	}

	private double _actualRate;
	private int _periodSid;
	private int _projectionDetailsSid;
	private double _actualSales;
}