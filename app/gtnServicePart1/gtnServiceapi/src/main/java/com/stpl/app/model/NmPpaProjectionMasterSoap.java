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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class NmPpaProjectionMasterSoap implements Serializable {
	public static NmPpaProjectionMasterSoap toSoapModel(
		NmPpaProjectionMaster model) {
		NmPpaProjectionMasterSoap soapModel = new NmPpaProjectionMasterSoap();

		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setUserGroup(model.getUserGroup());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setPriceBasis(model.getPriceBasis());
		soapModel.setPriceProtectionEndDate(model.getPriceProtectionEndDate());
		soapModel.setPriceProtectionStartDate(model.getPriceProtectionStartDate());
		soapModel.setActualPriceCap(model.getActualPriceCap());

		return soapModel;
	}

	public static NmPpaProjectionMasterSoap[] toSoapModels(
		NmPpaProjectionMaster[] models) {
		NmPpaProjectionMasterSoap[] soapModels = new NmPpaProjectionMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NmPpaProjectionMasterSoap[][] toSoapModels(
		NmPpaProjectionMaster[][] models) {
		NmPpaProjectionMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NmPpaProjectionMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NmPpaProjectionMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NmPpaProjectionMasterSoap[] toSoapModels(
		List<NmPpaProjectionMaster> models) {
		List<NmPpaProjectionMasterSoap> soapModels = new ArrayList<NmPpaProjectionMasterSoap>(models.size());

		for (NmPpaProjectionMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NmPpaProjectionMasterSoap[soapModels.size()]);
	}

	public NmPpaProjectionMasterSoap() {
	}

	public int getPrimaryKey() {
		return _projectionDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setProjectionDetailsSid(pk);
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

	public String getUserGroup() {
		return _userGroup;
	}

	public void setUserGroup(String userGroup) {
		_userGroup = userGroup;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public String getPriceBasis() {
		return _priceBasis;
	}

	public void setPriceBasis(String priceBasis) {
		_priceBasis = priceBasis;
	}

	public Date getPriceProtectionEndDate() {
		return _priceProtectionEndDate;
	}

	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_priceProtectionEndDate = priceProtectionEndDate;
	}

	public Date getPriceProtectionStartDate() {
		return _priceProtectionStartDate;
	}

	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_priceProtectionStartDate = priceProtectionStartDate;
	}

	public double getActualPriceCap() {
		return _actualPriceCap;
	}

	public void setActualPriceCap(double actualPriceCap) {
		_actualPriceCap = actualPriceCap;
	}

	private boolean _checkRecord;
	private String _userGroup;
	private int _projectionDetailsSid;
	private String _priceBasis;
	private Date _priceProtectionEndDate;
	private Date _priceProtectionStartDate;
	private double _actualPriceCap;
}