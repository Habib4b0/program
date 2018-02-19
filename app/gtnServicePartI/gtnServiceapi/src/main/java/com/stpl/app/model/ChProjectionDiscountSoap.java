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

import com.stpl.app.service.persistence.ChProjectionDiscountPK;

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
public class ChProjectionDiscountSoap implements Serializable {
	public static ChProjectionDiscountSoap toSoapModel(
		ChProjectionDiscount model) {
		ChProjectionDiscountSoap soapModel = new ChProjectionDiscountSoap();

		soapModel.setAdjustmentMethodology(model.getAdjustmentMethodology());
		soapModel.setProductGrowth(model.getProductGrowth());
		soapModel.setProjectionRate(model.getProjectionRate());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setAccountGrowth(model.getAccountGrowth());
		soapModel.setDiscountAmount(model.getDiscountAmount());
		soapModel.setDiscountRate(model.getDiscountRate());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setAdjustmentBasis(model.getAdjustmentBasis());
		soapModel.setAdjustmentValue(model.getAdjustmentValue());
		soapModel.setAdjustmentType(model.getAdjustmentType());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setProjectionSales(model.getProjectionSales());

		return soapModel;
	}

	public static ChProjectionDiscountSoap[] toSoapModels(
		ChProjectionDiscount[] models) {
		ChProjectionDiscountSoap[] soapModels = new ChProjectionDiscountSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChProjectionDiscountSoap[][] toSoapModels(
		ChProjectionDiscount[][] models) {
		ChProjectionDiscountSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChProjectionDiscountSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChProjectionDiscountSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChProjectionDiscountSoap[] toSoapModels(
		List<ChProjectionDiscount> models) {
		List<ChProjectionDiscountSoap> soapModels = new ArrayList<ChProjectionDiscountSoap>(models.size());

		for (ChProjectionDiscount model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChProjectionDiscountSoap[soapModels.size()]);
	}

	public ChProjectionDiscountSoap() {
	}

	public ChProjectionDiscountPK getPrimaryKey() {
		return new ChProjectionDiscountPK(_projectionDetailsSid, _periodSid,
			_rsModelSid);
	}

	public void setPrimaryKey(ChProjectionDiscountPK pk) {
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setPeriodSid(pk.periodSid);
		setRsModelSid(pk.rsModelSid);
	}

	public String getAdjustmentMethodology() {
		return _adjustmentMethodology;
	}

	public void setAdjustmentMethodology(String adjustmentMethodology) {
		_adjustmentMethodology = adjustmentMethodology;
	}

	public double getProductGrowth() {
		return _productGrowth;
	}

	public void setProductGrowth(double productGrowth) {
		_productGrowth = productGrowth;
	}

	public double getProjectionRate() {
		return _projectionRate;
	}

	public void setProjectionRate(double projectionRate) {
		_projectionRate = projectionRate;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public double getAccountGrowth() {
		return _accountGrowth;
	}

	public void setAccountGrowth(double accountGrowth) {
		_accountGrowth = accountGrowth;
	}

	public double getDiscountAmount() {
		return _discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		_discountAmount = discountAmount;
	}

	public double getDiscountRate() {
		return _discountRate;
	}

	public void setDiscountRate(double discountRate) {
		_discountRate = discountRate;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public String getAdjustmentBasis() {
		return _adjustmentBasis;
	}

	public void setAdjustmentBasis(String adjustmentBasis) {
		_adjustmentBasis = adjustmentBasis;
	}

	public double getAdjustmentValue() {
		return _adjustmentValue;
	}

	public void setAdjustmentValue(double adjustmentValue) {
		_adjustmentValue = adjustmentValue;
	}

	public String getAdjustmentType() {
		return _adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		_adjustmentType = adjustmentType;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public double getProjectionSales() {
		return _projectionSales;
	}

	public void setProjectionSales(double projectionSales) {
		_projectionSales = projectionSales;
	}

	private String _adjustmentMethodology;
	private double _productGrowth;
	private double _projectionRate;
	private int _projectionDetailsSid;
	private double _accountGrowth;
	private double _discountAmount;
	private double _discountRate;
	private int _periodSid;
	private String _adjustmentBasis;
	private double _adjustmentValue;
	private String _adjustmentType;
	private int _rsModelSid;
	private double _projectionSales;
}