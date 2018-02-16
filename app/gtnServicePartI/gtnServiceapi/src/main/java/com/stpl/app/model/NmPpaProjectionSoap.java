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

import com.stpl.app.service.persistence.NmPpaProjectionPK;

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
public class NmPpaProjectionSoap implements Serializable {
	public static NmPpaProjectionSoap toSoapModel(NmPpaProjection model) {
		NmPpaProjectionSoap soapModel = new NmPpaProjectionSoap();

		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setProjectionRate(model.getProjectionRate());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setPriceCap(model.getPriceCap());
		soapModel.setProjectionDiscountUnits(model.getProjectionDiscountUnits());
		soapModel.setProjectionDiscountDollar(model.getProjectionDiscountDollar());
		soapModel.setReset(model.getReset());
		soapModel.setProjectionSales(model.getProjectionSales());
		soapModel.setProjectionMap(model.getProjectionMap());
		soapModel.setResetPriceCap(model.getResetPriceCap());

		return soapModel;
	}

	public static NmPpaProjectionSoap[] toSoapModels(NmPpaProjection[] models) {
		NmPpaProjectionSoap[] soapModels = new NmPpaProjectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NmPpaProjectionSoap[][] toSoapModels(
		NmPpaProjection[][] models) {
		NmPpaProjectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NmPpaProjectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NmPpaProjectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NmPpaProjectionSoap[] toSoapModels(
		List<NmPpaProjection> models) {
		List<NmPpaProjectionSoap> soapModels = new ArrayList<NmPpaProjectionSoap>(models.size());

		for (NmPpaProjection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NmPpaProjectionSoap[soapModels.size()]);
	}

	public NmPpaProjectionSoap() {
	}

	public NmPpaProjectionPK getPrimaryKey() {
		return new NmPpaProjectionPK(_periodSid, _projectionDetailsSid);
	}

	public void setPrimaryKey(NmPpaProjectionPK pk) {
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

	public double getPriceCap() {
		return _priceCap;
	}

	public void setPriceCap(double priceCap) {
		_priceCap = priceCap;
	}

	public double getProjectionDiscountUnits() {
		return _projectionDiscountUnits;
	}

	public void setProjectionDiscountUnits(double projectionDiscountUnits) {
		_projectionDiscountUnits = projectionDiscountUnits;
	}

	public double getProjectionDiscountDollar() {
		return _projectionDiscountDollar;
	}

	public void setProjectionDiscountDollar(double projectionDiscountDollar) {
		_projectionDiscountDollar = projectionDiscountDollar;
	}

	public boolean getReset() {
		return _reset;
	}

	public boolean isReset() {
		return _reset;
	}

	public void setReset(boolean reset) {
		_reset = reset;
	}

	public double getProjectionSales() {
		return _projectionSales;
	}

	public void setProjectionSales(double projectionSales) {
		_projectionSales = projectionSales;
	}

	public double getProjectionMap() {
		return _projectionMap;
	}

	public void setProjectionMap(double projectionMap) {
		_projectionMap = projectionMap;
	}

	public boolean getResetPriceCap() {
		return _resetPriceCap;
	}

	public boolean isResetPriceCap() {
		return _resetPriceCap;
	}

	public void setResetPriceCap(boolean resetPriceCap) {
		_resetPriceCap = resetPriceCap;
	}

	private int _periodSid;
	private double _projectionRate;
	private int _projectionDetailsSid;
	private double _priceCap;
	private double _projectionDiscountUnits;
	private double _projectionDiscountDollar;
	private boolean _reset;
	private double _projectionSales;
	private double _projectionMap;
	private boolean _resetPriceCap;
}