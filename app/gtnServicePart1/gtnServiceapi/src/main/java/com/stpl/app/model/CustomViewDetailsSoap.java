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
public class CustomViewDetailsSoap implements Serializable {
	public static CustomViewDetailsSoap toSoapModel(CustomViewDetails model) {
		CustomViewDetailsSoap soapModel = new CustomViewDetailsSoap();

		soapModel.setHierarchyId(model.getHierarchyId());
		soapModel.setHierarchyIndicator(model.getHierarchyIndicator());
		soapModel.setCustomViewMasterSid(model.getCustomViewMasterSid());
		soapModel.setCustomViewDetailsSid(model.getCustomViewDetailsSid());
		soapModel.setLevelNo(model.getLevelNo());

		return soapModel;
	}

	public static CustomViewDetailsSoap[] toSoapModels(
		CustomViewDetails[] models) {
		CustomViewDetailsSoap[] soapModels = new CustomViewDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CustomViewDetailsSoap[][] toSoapModels(
		CustomViewDetails[][] models) {
		CustomViewDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CustomViewDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CustomViewDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CustomViewDetailsSoap[] toSoapModels(
		List<CustomViewDetails> models) {
		List<CustomViewDetailsSoap> soapModels = new ArrayList<CustomViewDetailsSoap>(models.size());

		for (CustomViewDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CustomViewDetailsSoap[soapModels.size()]);
	}

	public CustomViewDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _customViewDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCustomViewDetailsSid(pk);
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

	public int getCustomViewMasterSid() {
		return _customViewMasterSid;
	}

	public void setCustomViewMasterSid(int customViewMasterSid) {
		_customViewMasterSid = customViewMasterSid;
	}

	public int getCustomViewDetailsSid() {
		return _customViewDetailsSid;
	}

	public void setCustomViewDetailsSid(int customViewDetailsSid) {
		_customViewDetailsSid = customViewDetailsSid;
	}

	public int getLevelNo() {
		return _levelNo;
	}

	public void setLevelNo(int levelNo) {
		_levelNo = levelNo;
	}

	private int _hierarchyId;
	private String _hierarchyIndicator;
	private int _customViewMasterSid;
	private int _customViewDetailsSid;
	private int _levelNo;
}