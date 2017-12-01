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
public class GcmCompanyLinkSoap implements Serializable {
	public static GcmCompanyLinkSoap toSoapModel(GcmCompanyLink model) {
		GcmCompanyLinkSoap soapModel = new GcmCompanyLinkSoap();

		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setUserId(model.getUserId());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setCompanyStringId(model.getCompanyStringId());
		soapModel.setGcmCompanyLinkSid(model.getGcmCompanyLinkSid());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setLinkId(model.getLinkId());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

		return soapModel;
	}

	public static GcmCompanyLinkSoap[] toSoapModels(GcmCompanyLink[] models) {
		GcmCompanyLinkSoap[] soapModels = new GcmCompanyLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GcmCompanyLinkSoap[][] toSoapModels(GcmCompanyLink[][] models) {
		GcmCompanyLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GcmCompanyLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new GcmCompanyLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GcmCompanyLinkSoap[] toSoapModels(List<GcmCompanyLink> models) {
		List<GcmCompanyLinkSoap> soapModels = new ArrayList<GcmCompanyLinkSoap>(models.size());

		for (GcmCompanyLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GcmCompanyLinkSoap[soapModels.size()]);
	}

	public GcmCompanyLinkSoap() {
	}

	public int getPrimaryKey() {
		return _gcmCompanyLinkSid;
	}

	public void setPrimaryKey(int pk) {
		setGcmCompanyLinkSid(pk);
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

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public String getCompanyNo() {
		return _companyNo;
	}

	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	public String getCompanyStringId() {
		return _companyStringId;
	}

	public void setCompanyStringId(String companyStringId) {
		_companyStringId = companyStringId;
	}

	public int getGcmCompanyLinkSid() {
		return _gcmCompanyLinkSid;
	}

	public void setGcmCompanyLinkSid(int gcmCompanyLinkSid) {
		_gcmCompanyLinkSid = gcmCompanyLinkSid;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public String getLinkId() {
		return _linkId;
	}

	public void setLinkId(String linkId) {
		_linkId = linkId;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	private boolean _checkRecord;
	private int _userId;
	private String _companyNo;
	private String _companyStringId;
	private int _gcmCompanyLinkSid;
	private String _sessionId;
	private String _companyName;
	private String _linkId;
	private int _companyMasterSid;
}