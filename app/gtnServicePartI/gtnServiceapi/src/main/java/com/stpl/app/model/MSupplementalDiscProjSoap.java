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
public class MSupplementalDiscProjSoap implements Serializable {
	public static MSupplementalDiscProjSoap toSoapModel(
		MSupplementalDiscProj model) {
		MSupplementalDiscProjSoap soapModel = new MSupplementalDiscProjSoap();

		soapModel.setMethodology(model.getMethodology());
		soapModel.setProjectionRate(model.getProjectionRate());
		soapModel.setParity(model.getParity());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setDiscountRate1(model.getDiscountRate1());
		soapModel.setParityReference(model.getParityReference());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setDiscountRate2(model.getDiscountRate2());
		soapModel.setParityDiscount(model.getParityDiscount());
		soapModel.setProjectionSales(model.getProjectionSales());
		soapModel.setContractPrice(model.getContractPrice());
		soapModel.setAccess(model.getAccess());

		return soapModel;
	}

	public static MSupplementalDiscProjSoap[] toSoapModels(
		MSupplementalDiscProj[] models) {
		MSupplementalDiscProjSoap[] soapModels = new MSupplementalDiscProjSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MSupplementalDiscProjSoap[][] toSoapModels(
		MSupplementalDiscProj[][] models) {
		MSupplementalDiscProjSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MSupplementalDiscProjSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MSupplementalDiscProjSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MSupplementalDiscProjSoap[] toSoapModels(
		List<MSupplementalDiscProj> models) {
		List<MSupplementalDiscProjSoap> soapModels = new ArrayList<MSupplementalDiscProjSoap>(models.size());

		for (MSupplementalDiscProj model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MSupplementalDiscProjSoap[soapModels.size()]);
	}

	public MSupplementalDiscProjSoap() {
	}

	public int getPrimaryKey() {
		return _projectionDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setProjectionDetailsSid(pk);
	}

	public String getMethodology() {
		return _methodology;
	}

	public void setMethodology(String methodology) {
		_methodology = methodology;
	}

	public double getProjectionRate() {
		return _projectionRate;
	}

	public void setProjectionRate(double projectionRate) {
		_projectionRate = projectionRate;
	}

	public boolean getParity() {
		return _parity;
	}

	public boolean isParity() {
		return _parity;
	}

	public void setParity(boolean parity) {
		_parity = parity;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public double getDiscountRate1() {
		return _discountRate1;
	}

	public void setDiscountRate1(double discountRate1) {
		_discountRate1 = discountRate1;
	}

	public String getParityReference() {
		return _parityReference;
	}

	public void setParityReference(String parityReference) {
		_parityReference = parityReference;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public double getDiscountRate2() {
		return _discountRate2;
	}

	public void setDiscountRate2(double discountRate2) {
		_discountRate2 = discountRate2;
	}

	public double getParityDiscount() {
		return _parityDiscount;
	}

	public void setParityDiscount(double parityDiscount) {
		_parityDiscount = parityDiscount;
	}

	public double getProjectionSales() {
		return _projectionSales;
	}

	public void setProjectionSales(double projectionSales) {
		_projectionSales = projectionSales;
	}

	public double getContractPrice() {
		return _contractPrice;
	}

	public void setContractPrice(double contractPrice) {
		_contractPrice = contractPrice;
	}

	public String getAccess() {
		return _access;
	}

	public void setAccess(String access) {
		_access = access;
	}

	private String _methodology;
	private double _projectionRate;
	private boolean _parity;
	private int _periodSid;
	private double _discountRate1;
	private String _parityReference;
	private int _projectionDetailsSid;
	private double _discountRate2;
	private double _parityDiscount;
	private double _projectionSales;
	private double _contractPrice;
	private String _access;
}