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
public class CcpMapSoap implements Serializable {
	public static CcpMapSoap toSoapModel(CcpMap model) {
		CcpMapSoap soapModel = new CcpMapSoap();

		soapModel.setCcpDetailsSid(model.getCcpDetailsSid());
		soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());
		soapModel.setCcpMapSid(model.getCcpMapSid());

		return soapModel;
	}

	public static CcpMapSoap[] toSoapModels(CcpMap[] models) {
		CcpMapSoap[] soapModels = new CcpMapSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CcpMapSoap[][] toSoapModels(CcpMap[][] models) {
		CcpMapSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CcpMapSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CcpMapSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CcpMapSoap[] toSoapModels(List<CcpMap> models) {
		List<CcpMapSoap> soapModels = new ArrayList<CcpMapSoap>(models.size());

		for (CcpMap model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CcpMapSoap[soapModels.size()]);
	}

	public CcpMapSoap() {
	}

	public int getPrimaryKey() {
		return _ccpMapSid;
	}

	public void setPrimaryKey(int pk) {
		setCcpMapSid(pk);
	}

	public int getCcpDetailsSid() {
		return _ccpDetailsSid;
	}

	public void setCcpDetailsSid(int ccpDetailsSid) {
		_ccpDetailsSid = ccpDetailsSid;
	}

	public int getRelationshipLevelSid() {
		return _relationshipLevelSid;
	}

	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_relationshipLevelSid = relationshipLevelSid;
	}

	public int getCcpMapSid() {
		return _ccpMapSid;
	}

	public void setCcpMapSid(int ccpMapSid) {
		_ccpMapSid = ccpMapSid;
	}

	private int _ccpDetailsSid;
	private int _relationshipLevelSid;
	private int _ccpMapSid;
}