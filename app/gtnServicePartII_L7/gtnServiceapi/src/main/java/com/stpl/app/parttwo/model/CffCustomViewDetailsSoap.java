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
public class CffCustomViewDetailsSoap implements Serializable {
	public static CffCustomViewDetailsSoap toSoapModel(
		CffCustomViewDetails model) {
		CffCustomViewDetailsSoap soapModel = new CffCustomViewDetailsSoap();

		soapModel.setHierarchyId(model.getHierarchyId());
		soapModel.setHierarchyIndicator(model.getHierarchyIndicator());
		soapModel.setCffCustomViewDetailsSid(model.getCffCustomViewDetailsSid());
		soapModel.setLevelNo(model.getLevelNo());
		soapModel.setCffCustomViewMasterSid(model.getCffCustomViewMasterSid());

		return soapModel;
	}

	public static CffCustomViewDetailsSoap[] toSoapModels(
		CffCustomViewDetails[] models) {
		CffCustomViewDetailsSoap[] soapModels = new CffCustomViewDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CffCustomViewDetailsSoap[][] toSoapModels(
		CffCustomViewDetails[][] models) {
		CffCustomViewDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CffCustomViewDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CffCustomViewDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CffCustomViewDetailsSoap[] toSoapModels(
		List<CffCustomViewDetails> models) {
		List<CffCustomViewDetailsSoap> soapModels = new ArrayList<CffCustomViewDetailsSoap>(models.size());

		for (CffCustomViewDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CffCustomViewDetailsSoap[soapModels.size()]);
	}

	public CffCustomViewDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _cffCustomViewDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCffCustomViewDetailsSid(pk);
	}

	public int getHierarchyId() {
		return _hierarchyId;
	}

	public void setHierarchyId(int hierarchyId) {
		_hierarchyId = hierarchyId;
	}

	public String getHierarchyIndicator() {
		return _hierarchyIndicator;
	}

	public void setHierarchyIndicator(String hierarchyIndicator) {
		_hierarchyIndicator = hierarchyIndicator;
	}

	public int getCffCustomViewDetailsSid() {
		return _cffCustomViewDetailsSid;
	}

	public void setCffCustomViewDetailsSid(int cffCustomViewDetailsSid) {
		_cffCustomViewDetailsSid = cffCustomViewDetailsSid;
	}

	public int getLevelNo() {
		return _levelNo;
	}

	public void setLevelNo(int levelNo) {
		_levelNo = levelNo;
	}

	public int getCffCustomViewMasterSid() {
		return _cffCustomViewMasterSid;
	}

	public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
		_cffCustomViewMasterSid = cffCustomViewMasterSid;
	}

	private int _hierarchyId;
	private String _hierarchyIndicator;
	private int _cffCustomViewDetailsSid;
	private int _levelNo;
	private int _cffCustomViewMasterSid;
}