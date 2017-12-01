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
public class PeriodSoap implements Serializable {
	public static PeriodSoap toSoapModel(Period model) {
		PeriodSoap soapModel = new PeriodSoap();

		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setPeriodDate(model.getPeriodDate());
		soapModel.setQuarter(model.getQuarter());
		soapModel.setYear(model.getYear());
		soapModel.setSemiAnnual(model.getSemiAnnual());
		soapModel.setMonth(model.getMonth());

		return soapModel;
	}

	public static PeriodSoap[] toSoapModels(Period[] models) {
		PeriodSoap[] soapModels = new PeriodSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PeriodSoap[][] toSoapModels(Period[][] models) {
		PeriodSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PeriodSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PeriodSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PeriodSoap[] toSoapModels(List<Period> models) {
		List<PeriodSoap> soapModels = new ArrayList<PeriodSoap>(models.size());

		for (Period model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PeriodSoap[soapModels.size()]);
	}

	public PeriodSoap() {
	}

	public int getPrimaryKey() {
		return _periodSid;
	}

	public void setPrimaryKey(int pk) {
		setPeriodSid(pk);
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public Date getPeriodDate() {
		return _periodDate;
	}

	public void setPeriodDate(Date periodDate) {
		_periodDate = periodDate;
	}

	public int getQuarter() {
		return _quarter;
	}

	public void setQuarter(int quarter) {
		_quarter = quarter;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public int getSemiAnnual() {
		return _semiAnnual;
	}

	public void setSemiAnnual(int semiAnnual) {
		_semiAnnual = semiAnnual;
	}

	public int getMonth() {
		return _month;
	}

	public void setMonth(int month) {
		_month = month;
	}

	private int _periodSid;
	private Date _periodDate;
	private int _quarter;
	private int _year;
	private int _semiAnnual;
	private int _month;
}