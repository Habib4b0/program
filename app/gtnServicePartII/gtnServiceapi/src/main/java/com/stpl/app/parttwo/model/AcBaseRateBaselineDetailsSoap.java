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
public class AcBaseRateBaselineDetailsSoap implements Serializable {
	public static AcBaseRateBaselineDetailsSoap toSoapModel(
		AcBaseRateBaselineDetails model) {
		AcBaseRateBaselineDetailsSoap soapModel = new AcBaseRateBaselineDetailsSoap();

		soapModel.setPeriodValue(model.getPeriodValue());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setPaymentsPeriod(model.getPaymentsPeriod());
		soapModel.setAcBrMethodologyDetailsSid(model.getAcBrMethodologyDetailsSid());
		soapModel.setSalesPeriod(model.getSalesPeriod());

		return soapModel;
	}

	public static AcBaseRateBaselineDetailsSoap[] toSoapModels(
		AcBaseRateBaselineDetails[] models) {
		AcBaseRateBaselineDetailsSoap[] soapModels = new AcBaseRateBaselineDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AcBaseRateBaselineDetailsSoap[][] toSoapModels(
		AcBaseRateBaselineDetails[][] models) {
		AcBaseRateBaselineDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AcBaseRateBaselineDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AcBaseRateBaselineDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AcBaseRateBaselineDetailsSoap[] toSoapModels(
		List<AcBaseRateBaselineDetails> models) {
		List<AcBaseRateBaselineDetailsSoap> soapModels = new ArrayList<AcBaseRateBaselineDetailsSoap>(models.size());

		for (AcBaseRateBaselineDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AcBaseRateBaselineDetailsSoap[soapModels.size()]);
	}

	public AcBaseRateBaselineDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _acBrMethodologyDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setAcBrMethodologyDetailsSid(pk);
	}

	public double getPeriodValue() {
		return _periodValue;
	}

	public void setPeriodValue(double periodValue) {
		_periodValue = periodValue;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public boolean getPaymentsPeriod() {
		return _paymentsPeriod;
	}

	public boolean isPaymentsPeriod() {
		return _paymentsPeriod;
	}

	public void setPaymentsPeriod(boolean paymentsPeriod) {
		_paymentsPeriod = paymentsPeriod;
	}

	public int getAcBrMethodologyDetailsSid() {
		return _acBrMethodologyDetailsSid;
	}

	public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
		_acBrMethodologyDetailsSid = acBrMethodologyDetailsSid;
	}

	public boolean getSalesPeriod() {
		return _salesPeriod;
	}

	public boolean isSalesPeriod() {
		return _salesPeriod;
	}

	public void setSalesPeriod(boolean salesPeriod) {
		_salesPeriod = salesPeriod;
	}

	private double _periodValue;
	private int _periodSid;
	private boolean _paymentsPeriod;
	private int _acBrMethodologyDetailsSid;
	private boolean _salesPeriod;
}