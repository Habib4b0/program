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
public class NaProjDetailsSoap implements Serializable {
	public static NaProjDetailsSoap toSoapModel(NaProjDetails model) {
		NaProjDetailsSoap soapModel = new NaProjDetailsSoap();

		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setNaProjMasterSid(model.getNaProjMasterSid());
		soapModel.setNaProjDetailsSid(model.getNaProjDetailsSid());

		return soapModel;
	}

	public static NaProjDetailsSoap[] toSoapModels(NaProjDetails[] models) {
		NaProjDetailsSoap[] soapModels = new NaProjDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NaProjDetailsSoap[][] toSoapModels(NaProjDetails[][] models) {
		NaProjDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NaProjDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NaProjDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NaProjDetailsSoap[] toSoapModels(List<NaProjDetails> models) {
		List<NaProjDetailsSoap> soapModels = new ArrayList<NaProjDetailsSoap>(models.size());

		for (NaProjDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NaProjDetailsSoap[soapModels.size()]);
	}

	public NaProjDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _naProjDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setNaProjDetailsSid(pk);
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public int getNaProjMasterSid() {
		return _naProjMasterSid;
	}

	public void setNaProjMasterSid(int naProjMasterSid) {
		_naProjMasterSid = naProjMasterSid;
	}

	public int getNaProjDetailsSid() {
		return _naProjDetailsSid;
	}

	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_naProjDetailsSid = naProjDetailsSid;
	}

	private int _itemMasterSid;
	private int _naProjMasterSid;
	private int _naProjDetailsSid;
}