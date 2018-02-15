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
public class MSupplementalDiscMasterSoap implements Serializable {
	public static MSupplementalDiscMasterSoap toSoapModel(
		MSupplementalDiscMaster model) {
		MSupplementalDiscMasterSoap soapModel = new MSupplementalDiscMasterSoap();

		soapModel.setActualDiscountRate2(model.getActualDiscountRate2());
		soapModel.setActualDiscountRate1(model.getActualDiscountRate1());
		soapModel.setMarketType(model.getMarketType());
		soapModel.setActualMethodology(model.getActualMethodology());
		soapModel.setActualContractPrice(model.getActualContractPrice());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setActualDiscount(model.getActualDiscount());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setContractEndDate(model.getContractEndDate());

		return soapModel;
	}

	public static MSupplementalDiscMasterSoap[] toSoapModels(
		MSupplementalDiscMaster[] models) {
		MSupplementalDiscMasterSoap[] soapModels = new MSupplementalDiscMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MSupplementalDiscMasterSoap[][] toSoapModels(
		MSupplementalDiscMaster[][] models) {
		MSupplementalDiscMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MSupplementalDiscMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MSupplementalDiscMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MSupplementalDiscMasterSoap[] toSoapModels(
		List<MSupplementalDiscMaster> models) {
		List<MSupplementalDiscMasterSoap> soapModels = new ArrayList<MSupplementalDiscMasterSoap>(models.size());

		for (MSupplementalDiscMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MSupplementalDiscMasterSoap[soapModels.size()]);
	}

	public MSupplementalDiscMasterSoap() {
	}

	public int getPrimaryKey() {
		return _projectionDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setProjectionDetailsSid(pk);
	}

	public double getActualDiscountRate2() {
		return _actualDiscountRate2;
	}

	public void setActualDiscountRate2(double actualDiscountRate2) {
		_actualDiscountRate2 = actualDiscountRate2;
	}

	public double getActualDiscountRate1() {
		return _actualDiscountRate1;
	}

	public void setActualDiscountRate1(double actualDiscountRate1) {
		_actualDiscountRate1 = actualDiscountRate1;
	}

	public String getMarketType() {
		return _marketType;
	}

	public void setMarketType(String marketType) {
		_marketType = marketType;
	}

	public String getActualMethodology() {
		return _actualMethodology;
	}

	public void setActualMethodology(String actualMethodology) {
		_actualMethodology = actualMethodology;
	}

	public double getActualContractPrice() {
		return _actualContractPrice;
	}

	public void setActualContractPrice(double actualContractPrice) {
		_actualContractPrice = actualContractPrice;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public double getActualDiscount() {
		return _actualDiscount;
	}

	public void setActualDiscount(double actualDiscount) {
		_actualDiscount = actualDiscount;
	}

	public int getCheckRecord() {
		return _checkRecord;
	}

	public void setCheckRecord(int checkRecord) {
		_checkRecord = checkRecord;
	}

	public Date getContractEndDate() {
		return _contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		_contractEndDate = contractEndDate;
	}

	private double _actualDiscountRate2;
	private double _actualDiscountRate1;
	private String _marketType;
	private String _actualMethodology;
	private double _actualContractPrice;
	private int _projectionDetailsSid;
	private double _actualDiscount;
	private int _checkRecord;
	private Date _contractEndDate;
}