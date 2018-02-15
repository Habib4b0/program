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

import com.stpl.app.service.persistence.NmDiscountProjectionPK;

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
public class NmDiscountProjectionSoap implements Serializable {
	public static NmDiscountProjectionSoap toSoapModel(
		NmDiscountProjection model) {
		NmDiscountProjectionSoap soapModel = new NmDiscountProjectionSoap();

		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setProjectionRate(model.getProjectionRate());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setProjectionSales(model.getProjectionSales());

		return soapModel;
	}

	public static NmDiscountProjectionSoap[] toSoapModels(
		NmDiscountProjection[] models) {
		NmDiscountProjectionSoap[] soapModels = new NmDiscountProjectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NmDiscountProjectionSoap[][] toSoapModels(
		NmDiscountProjection[][] models) {
		NmDiscountProjectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NmDiscountProjectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NmDiscountProjectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NmDiscountProjectionSoap[] toSoapModels(
		List<NmDiscountProjection> models) {
		List<NmDiscountProjectionSoap> soapModels = new ArrayList<NmDiscountProjectionSoap>(models.size());

		for (NmDiscountProjection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NmDiscountProjectionSoap[soapModels.size()]);
	}

	public NmDiscountProjectionSoap() {
	}

	public NmDiscountProjectionPK getPrimaryKey() {
		return new NmDiscountProjectionPK(_periodSid, _projectionDetailsSid);
	}

	public void setPrimaryKey(NmDiscountProjectionPK pk) {
		setPeriodSid(pk.periodSid);
		setProjectionDetailsSid(pk.projectionDetailsSid);
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public double getProjectionRate() {
		return _projectionRate;
	}

	public void setProjectionRate(double projectionRate) {
		_projectionRate = projectionRate;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public double getProjectionSales() {
		return _projectionSales;
	}

	public void setProjectionSales(double projectionSales) {
		_projectionSales = projectionSales;
	}

	private int _periodSid;
	private double _projectionRate;
	private int _projectionDetailsSid;
	private double _projectionSales;
}