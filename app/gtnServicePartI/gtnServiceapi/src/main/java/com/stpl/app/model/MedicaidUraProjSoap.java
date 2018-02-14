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

import com.stpl.app.service.persistence.MedicaidUraProjPK;

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
public class MedicaidUraProjSoap implements Serializable {
	public static MedicaidUraProjSoap toSoapModel(MedicaidUraProj model) {
		MedicaidUraProjSoap soapModel = new MedicaidUraProjSoap();

		soapModel.setAdjustment(model.getAdjustment());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setPriceType(model.getPriceType());
		soapModel.setProjectionPrice(model.getProjectionPrice());
		soapModel.setNotes(model.getNotes());
		soapModel.setNaProjDetailsSid(model.getNaProjDetailsSid());

		return soapModel;
	}

	public static MedicaidUraProjSoap[] toSoapModels(MedicaidUraProj[] models) {
		MedicaidUraProjSoap[] soapModels = new MedicaidUraProjSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MedicaidUraProjSoap[][] toSoapModels(
		MedicaidUraProj[][] models) {
		MedicaidUraProjSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MedicaidUraProjSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MedicaidUraProjSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MedicaidUraProjSoap[] toSoapModels(
		List<MedicaidUraProj> models) {
		List<MedicaidUraProjSoap> soapModels = new ArrayList<MedicaidUraProjSoap>(models.size());

		for (MedicaidUraProj model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MedicaidUraProjSoap[soapModels.size()]);
	}

	public MedicaidUraProjSoap() {
	}

	public MedicaidUraProjPK getPrimaryKey() {
		return new MedicaidUraProjPK(_periodSid, _priceType, _naProjDetailsSid);
	}

	public void setPrimaryKey(MedicaidUraProjPK pk) {
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