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

import com.stpl.app.service.persistence.StSelectionTablePK;

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
public class StSelectionTableSoap implements Serializable {
	public static StSelectionTableSoap toSoapModel(StSelectionTable model) {
		StSelectionTableSoap soapModel = new StSelectionTableSoap();

		soapModel.setSelectionType(model.getSelectionType());
		soapModel.setUserId(model.getUserId());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setCompanyItemSid(model.getCompanyItemSid());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static StSelectionTableSoap[] toSoapModels(StSelectionTable[] models) {
		StSelectionTableSoap[] soapModels = new StSelectionTableSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StSelectionTableSoap[][] toSoapModels(
		StSelectionTable[][] models) {
		StSelectionTableSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StSelectionTableSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StSelectionTableSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StSelectionTableSoap[] toSoapModels(
		List<StSelectionTable> models) {
		List<StSelectionTableSoap> soapModels = new ArrayList<StSelectionTableSoap>(models.size());

		for (StSelectionTable model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StSelectionTableSoap[soapModels.size()]);
	}

	public StSelectionTableSoap() {
	}

	public StSelectionTablePK getPrimaryKey() {
		return new StSelectionTablePK(_selectionType, _userId, _sessionId,
			_companyItemSid);
	}

	public void setPrimaryKey(StSelectionTablePK pk) {
		setSelectionType(pk.selectionType);
		setUserId(pk.userId);
		setSessionId(pk.sessionId);
		setCompanyItemSid(pk.companyItemSid);
	}

	public String getSelectionType() {
		return _selectionType;
	}

	public void setSelectionType(String selectionType) {
		_selectionType = selectionType;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public int getCompanyItemSid() {
		return _companyItemSid;
	}

	public void setCompanyItemSid(int companyItemSid) {
		_companyItemSid = companyItemSid;
	}

	public boolean getCheckRecord() {
		return _checkRecord;
	}

	public boolean isCheckRecord() {
		return _checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		_checkRecord = checkRecord;
	}

	private String _selectionType;
	private int _userId;
	private String _sessionId;
	private int _companyItemSid;
	private boolean _checkRecord;
}