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

import com.stpl.app.service.persistence.StChProjectionDiscountPK;

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
public class StChProjectionDiscountSoap implements Serializable {
	public static StChProjectionDiscountSoap toSoapModel(
		StChProjectionDiscount model) {
		StChProjectionDiscountSoap soapModel = new StChProjectionDiscountSoap();

		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setAdjustmentMethodology(model.getAdjustmentMethodology());
		soapModel.setProductGrowth(model.getProductGrowth());
		soapModel.setProjectionRate(model.getProjectionRate());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setUserId(model.getUserId());
		soapModel.setAccountGrowth(model.getAccountGrowth());
		soapModel.setDiscountAmount(model.getDiscountAmount());
		soapModel.setDiscountRate(model.getDiscountRate());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setAdjustmentBasis(model.getAdjustmentBasis());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setAdjustmentValue(model.getAdjustmentValue());
		soapModel.setAdjustmentType(model.getAdjustmentType());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setProjectionSales(model.getProjectionSales());

		return soapModel;
	}

	public static StChProjectionDiscountSoap[] toSoapModels(
		StChProjectionDiscount[] models) {
		StChProjectionDiscountSoap[] soapModels = new StChProjectionDiscountSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StChProjectionDiscountSoap[][] toSoapModels(
		StChProjectionDiscount[][] models) {
		StChProjectionDiscountSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StChProjectionDiscountSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StChProjectionDiscountSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StChProjectionDiscountSoap[] toSoapModels(
		List<StChProjectionDiscount> models) {
		List<StChProjectionDiscountSoap> soapModels = new ArrayList<StChProjectionDiscountSoap>(models.size());

		for (StChProjectionDiscount model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StChProjectionDiscountSoap[soapModels.size()]);
	}

	public StChProjectionDiscountSoap() {
	}

	public StChProjectionDiscountPK getPrimaryKey() {
		return new StChProjectionDiscountPK(_projectionDetailsSid, _userId,
			_periodSid, _sessionId, _rsModelSid);
	}

	public void setPrimaryKey(StChProjectionDiscountPK pk) {
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setUserId(pk.userId);
		setPeriodSid(pk.periodSid);
		setSessionId(pk.sessionId);
		setRsModelSid(pk.rsModelSid);
	}

	public Date getLastModifiedDate() {
		return _lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		_lastModifiedDate = lastModifiedDate;
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

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
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

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
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

	private Date _lastModifiedDate;
	private String _adjustmentMethodology;
	private double _productGrowth;
	private double _projectionRate;
	private int _projectionDetailsSid;
	private int _userId;
	private double _accountGrowth;
	private double _discountAmount;
	private double _discountRate;
	private int _periodSid;
	private String _adjustmentBasis;
	private int _sessionId;
	private double _adjustmentValue;
	private String _adjustmentType;
	private int _rsModelSid;
	private double _projectionSales;
}