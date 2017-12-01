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
public class NaProjectionSelectionSoap implements Serializable {
	public static NaProjectionSelectionSoap toSoapModel(
		NaProjectionSelection model) {
		NaProjectionSelectionSoap soapModel = new NaProjectionSelectionSoap();

		soapModel.setScreenName(model.getScreenName());
		soapModel.setFieldName(model.getFieldName());
		soapModel.setFieldValues(model.getFieldValues());
		soapModel.setNaProjectionSelectionSid(model.getNaProjectionSelectionSid());
		soapModel.setNaProjMasterSid(model.getNaProjMasterSid());

		return soapModel;
	}

	public static NaProjectionSelectionSoap[] toSoapModels(
		NaProjectionSelection[] models) {
		NaProjectionSelectionSoap[] soapModels = new NaProjectionSelectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NaProjectionSelectionSoap[][] toSoapModels(
		NaProjectionSelection[][] models) {
		NaProjectionSelectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NaProjectionSelectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NaProjectionSelectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NaProjectionSelectionSoap[] toSoapModels(
		List<NaProjectionSelection> models) {
		List<NaProjectionSelectionSoap> soapModels = new ArrayList<NaProjectionSelectionSoap>(models.size());

		for (NaProjectionSelection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NaProjectionSelectionSoap[soapModels.size()]);
	}

	public NaProjectionSelectionSoap() {
	}

	public int getPrimaryKey() {
		return _naProjectionSelectionSid;
	}

	public void setPrimaryKey(int pk) {
		setNaProjectionSelectionSid(pk);
	}

	public String getScreenName() {
		return _screenName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public String getFieldValues() {
		return _fieldValues;
	}

	public void setFieldValues(String fieldValues) {
		_fieldValues = fieldValues;
	}

	public int getNaProjectionSelectionSid() {
		return _naProjectionSelectionSid;
	}

	public void setNaProjectionSelectionSid(int naProjectionSelectionSid) {
		_naProjectionSelectionSid = naProjectionSelectionSid;
	}

	public int getNaProjMasterSid() {
		return _naProjMasterSid;
	}

	public void setNaProjMasterSid(int naProjMasterSid) {
		_naProjMasterSid = naProjMasterSid;
	}

	private String _screenName;
	private String _fieldName;
	private String _fieldValues;
	private int _naProjectionSelectionSid;
	private int _naProjMasterSid;
}