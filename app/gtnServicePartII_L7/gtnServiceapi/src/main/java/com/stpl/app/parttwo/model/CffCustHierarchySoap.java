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
public class CffCustHierarchySoap implements Serializable {
	public static CffCustHierarchySoap toSoapModel(CffCustHierarchy model) {
		CffCustHierarchySoap soapModel = new CffCustHierarchySoap();

		soapModel.setCffCustHierarchySid(model.getCffCustHierarchySid());
		soapModel.setCffMasterSid(model.getCffMasterSid());
		soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());

		return soapModel;
	}

	public static CffCustHierarchySoap[] toSoapModels(CffCustHierarchy[] models) {
		CffCustHierarchySoap[] soapModels = new CffCustHierarchySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CffCustHierarchySoap[][] toSoapModels(
		CffCustHierarchy[][] models) {
		CffCustHierarchySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CffCustHierarchySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CffCustHierarchySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CffCustHierarchySoap[] toSoapModels(
		List<CffCustHierarchy> models) {
		List<CffCustHierarchySoap> soapModels = new ArrayList<CffCustHierarchySoap>(models.size());

		for (CffCustHierarchy model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CffCustHierarchySoap[soapModels.size()]);
	}

	public CffCustHierarchySoap() {
	}

	public int getPrimaryKey() {
		return _cffCustHierarchySid;
	}

	public void setPrimaryKey(int pk) {
		setCffCustHierarchySid(pk);
	}

	public int getCffCustHierarchySid() {
		return _cffCustHierarchySid;
	}

	public void setCffCustHierarchySid(int cffCustHierarchySid) {
		_cffCustHierarchySid = cffCustHierarchySid;
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

	private int _cffCustHierarchySid;
	private int _cffMasterSid;
	private int _relationshipLevelSid;
}