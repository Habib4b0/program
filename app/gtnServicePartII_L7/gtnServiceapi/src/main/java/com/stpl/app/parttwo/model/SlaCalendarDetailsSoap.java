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
public class SlaCalendarDetailsSoap implements Serializable {
	public static SlaCalendarDetailsSoap toSoapModel(SlaCalendarDetails model) {
		SlaCalendarDetailsSoap soapModel = new SlaCalendarDetailsSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSlaCalendarMasterSid(model.getSlaCalendarMasterSid());
		soapModel.setHolidayYear(model.getHolidayYear());
		soapModel.setSlaCalendarDetailsSid(model.getSlaCalendarDetailsSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setHolidayDay(model.getHolidayDay());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHolidayCombined(model.getHolidayCombined());
		soapModel.setHolidayMonth(model.getHolidayMonth());

		return soapModel;
	}

	public static SlaCalendarDetailsSoap[] toSoapModels(
		SlaCalendarDetails[] models) {
		SlaCalendarDetailsSoap[] soapModels = new SlaCalendarDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SlaCalendarDetailsSoap[][] toSoapModels(
		SlaCalendarDetails[][] models) {
		SlaCalendarDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SlaCalendarDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SlaCalendarDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SlaCalendarDetailsSoap[] toSoapModels(
		List<SlaCalendarDetails> models) {
		List<SlaCalendarDetailsSoap> soapModels = new ArrayList<SlaCalendarDetailsSoap>(models.size());

		for (SlaCalendarDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SlaCalendarDetailsSoap[soapModels.size()]);
	}

	public SlaCalendarDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _slaCalendarDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setSlaCalendarDetailsSid(pk);
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getSlaCalendarMasterSid() {
		return _slaCalendarMasterSid;
	}

	public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
		_slaCalendarMasterSid = slaCalendarMasterSid;
	}

	public String getHolidayYear() {
		return _holidayYear;
	}

	public void setHolidayYear(String holidayYear) {
		_holidayYear = holidayYear;
	}

	public int getSlaCalendarDetailsSid() {
		return _slaCalendarDetailsSid;
	}

	public void setSlaCalendarDetailsSid(int slaCalendarDetailsSid) {
		_slaCalendarDetailsSid = slaCalendarDetailsSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getHolidayDay() {
		return _holidayDay;
	}

	public void setHolidayDay(String holidayDay) {
		_holidayDay = holidayDay;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public Date getHolidayCombined() {
		return _holidayCombined;
	}

	public void setHolidayCombined(Date holidayCombined) {
		_holidayCombined = holidayCombined;
	}

	public String getHolidayMonth() {
		return _holidayMonth;
	}

	public void setHolidayMonth(String holidayMonth) {
		_holidayMonth = holidayMonth;
	}

	private Date _createdDate;
	private int _createdBy;
	private int _slaCalendarMasterSid;
	private String _holidayYear;
	private int _slaCalendarDetailsSid;
	private int _modifiedBy;
	private String _inboundStatus;
	private String _holidayDay;
	private Date _modifiedDate;
	private Date _holidayCombined;
	private String _holidayMonth;
}