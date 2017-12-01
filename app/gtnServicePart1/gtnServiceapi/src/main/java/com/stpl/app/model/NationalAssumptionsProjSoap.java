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

import com.stpl.app.service.persistence.NationalAssumptionsProjPK;

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
public class NationalAssumptionsProjSoap implements Serializable {
	public static NationalAssumptionsProjSoap toSoapModel(
		NationalAssumptionsProj model) {
		NationalAssumptionsProjSoap soapModel = new NationalAssumptionsProjSoap();

		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setPriceType(model.getPriceType());
		soapModel.setProjectionPrice(model.getProjectionPrice());

		return soapModel;
	}

	public static NationalAssumptionsProjSoap[] toSoapModels(
		NationalAssumptionsProj[] models) {
		NationalAssumptionsProjSoap[] soapModels = new NationalAssumptionsProjSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NationalAssumptionsProjSoap[][] toSoapModels(
		NationalAssumptionsProj[][] models) {
		NationalAssumptionsProjSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NationalAssumptionsProjSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NationalAssumptionsProjSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NationalAssumptionsProjSoap[] toSoapModels(
		List<NationalAssumptionsProj> models) {
		List<NationalAssumptionsProjSoap> soapModels = new ArrayList<NationalAssumptionsProjSoap>(models.size());

		for (NationalAssumptionsProj model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NationalAssumptionsProjSoap[soapModels.size()]);
	}

	public NationalAssumptionsProjSoap() {
	}

	public NationalAssumptionsProjPK getPrimaryKey() {
		return new NationalAssumptionsProjPK(_periodSid, _itemMasterSid,
			_priceType);
	}

	public void setPrimaryKey(NationalAssumptionsProjPK pk) {
		setPeriodSid(pk.periodSid);
		setItemMasterSid(pk.itemMasterSid);
		setPriceType(pk.priceType);
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
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

	private int _periodSid;
	private int _itemMasterSid;
	private String _priceType;
	private double _projectionPrice;
}