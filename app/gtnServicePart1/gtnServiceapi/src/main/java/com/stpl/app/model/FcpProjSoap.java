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

import com.stpl.app.service.persistence.FcpProjPK;

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
public class FcpProjSoap implements Serializable {
	public static FcpProjSoap toSoapModel(FcpProj model) {
		FcpProjSoap soapModel = new FcpProjSoap();

		soapModel.setAdjustment(model.getAdjustment());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setPriceType(model.getPriceType());
		soapModel.setProjectionPrice(model.getProjectionPrice());
		soapModel.setNotes(model.getNotes());
		soapModel.setNaProjDetailsSid(model.getNaProjDetailsSid());

		return soapModel;
	}

	public static FcpProjSoap[] toSoapModels(FcpProj[] models) {
		FcpProjSoap[] soapModels = new FcpProjSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FcpProjSoap[][] toSoapModels(FcpProj[][] models) {
		FcpProjSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FcpProjSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FcpProjSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FcpProjSoap[] toSoapModels(List<FcpProj> models) {
		List<FcpProjSoap> soapModels = new ArrayList<FcpProjSoap>(models.size());

		for (FcpProj model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FcpProjSoap[soapModels.size()]);
	}

	public FcpProjSoap() {
	}

	public FcpProjPK getPrimaryKey() {
		return new FcpProjPK(_periodSid, _priceType, _naProjDetailsSid);
	}

	public void setPrimaryKey(FcpProjPK pk) {
		setPeriodSid(pk.periodSid);
		setPriceType(pk.priceType);
		setNaProjDetailsSid(pk.naProjDetailsSid);
	}

	public double getAdjustment() {
		return _adjustment;
	}

	public void setAdjustment(double adjustment) {
		_adjustment = adjustment;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public String getPriceType() {
		return _priceType;
	}

	public void setPriceType(String priceType) {
		_priceType = priceType;
	}

	public double getProjectionPrice() {
		return _projectionPrice;
	}

	public void setProjectionPrice(double projectionPrice) {
		_projectionPrice = projectionPrice;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		_notes = notes;
	}

	public int getNaProjDetailsSid() {
		return _naProjDetailsSid;
	}

	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_naProjDetailsSid = naProjDetailsSid;
	}

	private double _adjustment;
	private int _periodSid;
	private String _priceType;
	private double _projectionPrice;
	private String _notes;
	private int _naProjDetailsSid;
}