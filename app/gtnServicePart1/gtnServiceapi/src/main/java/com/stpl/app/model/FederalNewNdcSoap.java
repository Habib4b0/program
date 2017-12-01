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
public class FederalNewNdcSoap implements Serializable {
	public static FederalNewNdcSoap toSoapModel(FederalNewNdc model) {
		FederalNewNdcSoap soapModel = new FederalNewNdcSoap();

		soapModel.setFss(model.getFss());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setWacPrice(model.getWacPrice());
		soapModel.setNonFamp(model.getNonFamp());

		return soapModel;
	}

	public static FederalNewNdcSoap[] toSoapModels(FederalNewNdc[] models) {
		FederalNewNdcSoap[] soapModels = new FederalNewNdcSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FederalNewNdcSoap[][] toSoapModels(FederalNewNdc[][] models) {
		FederalNewNdcSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FederalNewNdcSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FederalNewNdcSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FederalNewNdcSoap[] toSoapModels(List<FederalNewNdc> models) {
		List<FederalNewNdcSoap> soapModels = new ArrayList<FederalNewNdcSoap>(models.size());

		for (FederalNewNdc model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FederalNewNdcSoap[soapModels.size()]);
	}

	public FederalNewNdcSoap() {
	}

	public int getPrimaryKey() {
		return _itemMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setItemMasterSid(pk);
	}

	public double getFss() {
		return _fss;
	}

	public void setFss(double fss) {
		_fss = fss;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public double getWacPrice() {
		return _wacPrice;
	}

	public void setWacPrice(double wacPrice) {
		_wacPrice = wacPrice;
	}

	public double getNonFamp() {
		return _nonFamp;
	}

	public void setNonFamp(double nonFamp) {
		_nonFamp = nonFamp;
	}

	private double _fss;
	private int _itemMasterSid;
	private double _wacPrice;
	private double _nonFamp;
}