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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class SlaCalendarMasterSoap implements Serializable {
	public static SlaCalendarMasterSoap toSoapModel(SlaCalendarMaster model) {
		SlaCalendarMasterSoap soapModel = new SlaCalendarMasterSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setSlaCalendarMasterSid(model.getSlaCalendarMasterSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setDefaultHolidays(model.getDefaultHolidays());
		soapModel.setCalendarName(model.getCalendarName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static SlaCalendarMasterSoap[] toSoapModels(
		SlaCalendarMaster[] models) {
		SlaCalendarMasterSoap[] soapModels = new SlaCalendarMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SlaCalendarMasterSoap[][] toSoapModels(
		SlaCalendarMaster[][] models) {
		SlaCalendarMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SlaCalendarMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SlaCalendarMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SlaCalendarMasterSoap[] toSoapModels(
		List<SlaCalendarMaster> models) {
		List<SlaCalendarMasterSoap> soapModels = new ArrayList<SlaCalendarMasterSoap>(models.size());

		for (SlaCalendarMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SlaCalendarMasterSoap[soapModels.size()]);
	}

	public SlaCalendarMasterSoap() {
	}

	public int getPrimaryKey() {
		return _slaCalendarMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setSlaCalendarMasterSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public int getSlaCalendarMasterSid() {
		return _slaCalendarMasterSid;
	}

	public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
		_slaCalendarMasterSid = slaCalendarMasterSid;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public boolean getDefaultHolidays() {
		return _defaultHolidays;
	}

	public boolean isDefaultHolidays() {
		return _defaultHolidays;
	}

	public void setDefaultHolidays(boolean defaultHolidays) {
		_defaultHolidays = defaultHolidays;
	}

	public String getCalendarName() {
		return _calendarName;
	}

	public void setCalendarName(String calendarName) {
		_calendarName = calendarName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private int _createdBy;
	private int _modifiedBy;
	private int _slaCalendarMasterSid;
	private Date _createdDate;
	private boolean _defaultHolidays;
	private String _calendarName;
	private Date _modifiedDate;
	private String _inboundStatus;
}