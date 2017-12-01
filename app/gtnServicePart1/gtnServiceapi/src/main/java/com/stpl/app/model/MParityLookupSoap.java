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
public class MParityLookupSoap implements Serializable {
	public static MParityLookupSoap toSoapModel(MParityLookup model) {
		MParityLookupSoap soapModel = new MParityLookupSoap();

		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setMarketType(model.getMarketType());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setMParityLookupSid(model.getMParityLookupSid());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());

		return soapModel;
	}

	public static MParityLookupSoap[] toSoapModels(MParityLookup[] models) {
		MParityLookupSoap[] soapModels = new MParityLookupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MParityLookupSoap[][] toSoapModels(MParityLookup[][] models) {
		MParityLookupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MParityLookupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MParityLookupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MParityLookupSoap[] toSoapModels(List<MParityLookup> models) {
		List<MParityLookupSoap> soapModels = new ArrayList<MParityLookupSoap>(models.size());

		for (MParityLookup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MParityLookupSoap[soapModels.size()]);
	}

	public MParityLookupSoap() {
	}

	public int getPrimaryKey() {
		return _mParityLookupSid;
	}

	public void setPrimaryKey(int pk) {
		setMParityLookupSid(pk);
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public String getMarketType() {
		return _marketType;
	}

	public void setMarketType(String marketType) {
		_marketType = marketType;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public int getMParityLookupSid() {
		return _mParityLookupSid;
	}

	public void setMParityLookupSid(int mParityLookupSid) {
		_mParityLookupSid = mParityLookupSid;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	private int _contractMasterSid;
	private String _marketType;
	private int _itemMasterSid;
	private int _mParityLookupSid;
	private int _projectionDetailsSid;
}