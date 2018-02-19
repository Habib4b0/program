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

import com.stpl.app.service.persistence.ChSalesProjectionPK;

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
public class ChSalesProjectionSoap implements Serializable {
	public static ChSalesProjectionSoap toSoapModel(ChSalesProjection model) {
		ChSalesProjectionSoap soapModel = new ChSalesProjectionSoap();

		soapModel.setContractUnits(model.getContractUnits());
		soapModel.setPerOfBusiness(model.getPerOfBusiness());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setContractSales(model.getContractSales());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setGtsSales(model.getGtsSales());

		return soapModel;
	}

	public static ChSalesProjectionSoap[] toSoapModels(
		ChSalesProjection[] models) {
		ChSalesProjectionSoap[] soapModels = new ChSalesProjectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChSalesProjectionSoap[][] toSoapModels(
		ChSalesProjection[][] models) {
		ChSalesProjectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChSalesProjectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChSalesProjectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChSalesProjectionSoap[] toSoapModels(
		List<ChSalesProjection> models) {
		List<ChSalesProjectionSoap> soapModels = new ArrayList<ChSalesProjectionSoap>(models.size());

		for (ChSalesProjection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChSalesProjectionSoap[soapModels.size()]);
	}

	public ChSalesProjectionSoap() {
	}

	public ChSalesProjectionPK getPrimaryKey() {
		return new ChSalesProjectionPK(_periodSid, _projectionDetailsSid);
	}

	public void setPrimaryKey(ChSalesProjectionPK pk) {
		setPeriodSid(pk.periodSid);
		setProjectionDetailsSid(pk.projectionDetailsSid);
	}

	public double getContractUnits() {
		return _contractUnits;
	}

	public void setContractUnits(double contractUnits) {
		_contractUnits = contractUnits;
	}

	public double getPerOfBusiness() {
		return _perOfBusiness;
	}

	public void setPerOfBusiness(double perOfBusiness) {
		_perOfBusiness = perOfBusiness;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public double getContractSales() {
		return _contractSales;
	}

	public void setContractSales(double contractSales) {
		_contractSales = contractSales;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public double getGtsSales() {
		return _gtsSales;
	}

	public void setGtsSales(double gtsSales) {
		_gtsSales = gtsSales;
	}

	private double _contractUnits;
	private double _perOfBusiness;
	private int _periodSid;
	private double _contractSales;
	private int _projectionDetailsSid;
	private double _gtsSales;
}