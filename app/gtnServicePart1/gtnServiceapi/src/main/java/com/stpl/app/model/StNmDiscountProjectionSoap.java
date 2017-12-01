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

import com.stpl.app.service.persistence.StNmDiscountProjectionPK;

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
public class StNmDiscountProjectionSoap implements Serializable {
	public static StNmDiscountProjectionSoap toSoapModel(
		StNmDiscountProjection model) {
		StNmDiscountProjectionSoap soapModel = new StNmDiscountProjectionSoap();

		soapModel.setProjectionRate(model.getProjectionRate());
		soapModel.setAdjustmentValue(model.getAdjustmentValue());
		soapModel.setUserId(model.getUserId());
		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setDiscountRate(model.getDiscountRate());
		soapModel.setProjectionSales(model.getProjectionSales());
		soapModel.setAdjustmentType(model.getAdjustmentType());
		soapModel.setAdjustmentBasis(model.getAdjustmentBasis());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setAdjustmentMethodology(model.getAdjustmentMethodology());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setSessionId(model.getSessionId());

		return soapModel;
	}

	public static StNmDiscountProjectionSoap[] toSoapModels(
		StNmDiscountProjection[] models) {
		StNmDiscountProjectionSoap[] soapModels = new StNmDiscountProjectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StNmDiscountProjectionSoap[][] toSoapModels(
		StNmDiscountProjection[][] models) {
		StNmDiscountProjectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StNmDiscountProjectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StNmDiscountProjectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StNmDiscountProjectionSoap[] toSoapModels(
		List<StNmDiscountProjection> models) {
		List<StNmDiscountProjectionSoap> soapModels = new ArrayList<StNmDiscountProjectionSoap>(models.size());

		for (StNmDiscountProjection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StNmDiscountProjectionSoap[soapModels.size()]);
	}

	public StNmDiscountProjectionSoap() {
	}

	public StNmDiscountProjectionPK getPrimaryKey() {
		return new StNmDiscountProjectionPK(_userId, _periodSid,
			_projectionDetailsSid, _rsModelSid, _sessionId);
	}

	public void setPrimaryKey(StNmDiscountProjectionPK pk) {
		setUserId(pk.userId);
		setPeriodSid(pk.periodSid);
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setRsModelSid(pk.rsModelSid);
		setSessionId(pk.sessionId);
	}

	public double getProjectionRate() {
		return _projectionRate;
	}

	public void setProjectionRate(double projectionRate) {
		_projectionRate = projectionRate;
	}

	public double getAdjustmentValue() {
		return _adjustmentValue;
	}

	public void setAdjustmentValue(double adjustmentValue) {
		_adjustmentValue = adjustmentValue;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public Date getLastModifiedDate() {
		return _lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		_lastModifiedDate = lastModifiedDate;
	}

	public double getDiscountRate() {
		return _discountRate;
	}

	public void setDiscountRate(double discountRate) {
		_discountRate = discountRate;
	}

	public double getProjectionSales() {
		return _projectionSales;
	}

	public void setProjectionSales(double projectionSales) {
		_projectionSales = projectionSales;
	}

	public String getAdjustmentType() {
		return _adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		_adjustmentType = adjustmentType;
	}

	public String getAdjustmentBasis() {
		return _adjustmentBasis;
	}

	public void setAdjustmentBasis(String adjustmentBasis) {
		_adjustmentBasis = adjustmentBasis;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public String getAdjustmentMethodology() {
		return _adjustmentMethodology;
	}

	public void setAdjustmentMethodology(String adjustmentMethodology) {
		_adjustmentMethodology = adjustmentMethodology;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	private double _projectionRate;
	private double _adjustmentValue;
	private int _userId;
	private Date _lastModifiedDate;
	private double _discountRate;
	private double _projectionSales;
	private String _adjustmentType;
	private String _adjustmentBasis;
	private int _periodSid;
	private String _adjustmentMethodology;
	private int _projectionDetailsSid;
	private int _rsModelSid;
	private int _sessionId;
}