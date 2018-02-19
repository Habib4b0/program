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

import com.stpl.app.service.persistence.NmSalesProjectionPK;

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
public class NmSalesProjectionSoap implements Serializable {
	public static NmSalesProjectionSoap toSoapModel(NmSalesProjection model) {
		NmSalesProjectionSoap soapModel = new NmSalesProjectionSoap();

		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setProductGrowth(model.getProductGrowth());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setAccountGrowth(model.getAccountGrowth());
		soapModel.setProjectionUnits(model.getProjectionUnits());
		soapModel.setProjectionSales(model.getProjectionSales());

		return soapModel;
	}

	public static NmSalesProjectionSoap[] toSoapModels(
		NmSalesProjection[] models) {
		NmSalesProjectionSoap[] soapModels = new NmSalesProjectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NmSalesProjectionSoap[][] toSoapModels(
		NmSalesProjection[][] models) {
		NmSalesProjectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NmSalesProjectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NmSalesProjectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NmSalesProjectionSoap[] toSoapModels(
		List<NmSalesProjection> models) {
		List<NmSalesProjectionSoap> soapModels = new ArrayList<NmSalesProjectionSoap>(models.size());

		for (NmSalesProjection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NmSalesProjectionSoap[soapModels.size()]);
	}

	public NmSalesProjectionSoap() {
	}

	public NmSalesProjectionPK getPrimaryKey() {
		return new NmSalesProjectionPK(_periodSid, _projectionDetailsSid);
	}

	public void setPrimaryKey(NmSalesProjectionPK pk) {
		setPeriodSid(pk.periodSid);
		setProjectionDetailsSid(pk.projectionDetailsSid);
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public double getProductGrowth() {
		return _productGrowth;
	}

	public void setProductGrowth(double productGrowth) {
		_productGrowth = productGrowth;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public double getAccountGrowth() {
		return _accountGrowth;
	}

	public void setAccountGrowth(double accountGrowth) {
		_accountGrowth = accountGrowth;
	}

	public double getProjectionUnits() {
		return _projectionUnits;
	}

	public void setProjectionUnits(double projectionUnits) {
		_projectionUnits = projectionUnits;
	}

	public double getProjectionSales() {
		return _projectionSales;
	}

	public void setProjectionSales(double projectionSales) {
		_projectionSales = projectionSales;
	}

	private int _periodSid;
	private double _productGrowth;
	private int _projectionDetailsSid;
	private double _accountGrowth;
	private double _projectionUnits;
	private double _projectionSales;
}