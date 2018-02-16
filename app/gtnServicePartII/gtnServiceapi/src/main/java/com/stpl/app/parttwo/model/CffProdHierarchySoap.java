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

package com.stpl.app.parttwo.model;

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
public class CffProdHierarchySoap implements Serializable {
	public static CffProdHierarchySoap toSoapModel(CffProdHierarchy model) {
		CffProdHierarchySoap soapModel = new CffProdHierarchySoap();

		soapModel.setCffMasterSid(model.getCffMasterSid());
		soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());
		soapModel.setCffProdHierarchySid(model.getCffProdHierarchySid());

		return soapModel;
	}

	public static CffProdHierarchySoap[] toSoapModels(CffProdHierarchy[] models) {
		CffProdHierarchySoap[] soapModels = new CffProdHierarchySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CffProdHierarchySoap[][] toSoapModels(
		CffProdHierarchy[][] models) {
		CffProdHierarchySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CffProdHierarchySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CffProdHierarchySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CffProdHierarchySoap[] toSoapModels(
		List<CffProdHierarchy> models) {
		List<CffProdHierarchySoap> soapModels = new ArrayList<CffProdHierarchySoap>(models.size());

		for (CffProdHierarchy model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CffProdHierarchySoap[soapModels.size()]);
	}

	public CffProdHierarchySoap() {
	}

	public int getPrimaryKey() {
		return _cffProdHierarchySid;
	}

	public void setPrimaryKey(int pk) {
		setCffProdHierarchySid(pk);
	}

	public int getCffMasterSid() {
		return _cffMasterSid;
	}

	public void setCffMasterSid(int cffMasterSid) {
		_cffMasterSid = cffMasterSid;
	}

	public int getRelationshipLevelSid() {
		return _relationshipLevelSid;
	}

	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_relationshipLevelSid = relationshipLevelSid;
	}

	public int getCffProdHierarchySid() {
		return _cffProdHierarchySid;
	}

	public void setCffProdHierarchySid(int cffProdHierarchySid) {
		_cffProdHierarchySid = cffProdHierarchySid;
	}

	private int _cffMasterSid;
	private int _relationshipLevelSid;
	private int _cffProdHierarchySid;
}