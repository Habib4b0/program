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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SlaCalendarMaster}.
 * </p>
 *
 * @author
 * @see SlaCalendarMaster
 * @generated
 */
@ProviderType
public class SlaCalendarMasterWrapper implements SlaCalendarMaster,
	ModelWrapper<SlaCalendarMaster> {
	public SlaCalendarMasterWrapper(SlaCalendarMaster slaCalendarMaster) {
		_slaCalendarMaster = slaCalendarMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return SlaCalendarMaster.class;
	}

	@Override
	public String getModelClassName() {
		return SlaCalendarMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("slaCalendarMasterSid", getSlaCalendarMasterSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("defaultHolidays", getDefaultHolidays());
		attributes.put("calendarName", getCalendarName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer slaCalendarMasterSid = (Integer)attributes.get(
				"slaCalendarMasterSid");

		if (slaCalendarMasterSid != null) {
			setSlaCalendarMasterSid(slaCalendarMasterSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Boolean defaultHolidays = (Boolean)attributes.get("defaultHolidays");

		if (defaultHolidays != null) {
			setDefaultHolidays(defaultHolidays);
		}

		String calendarName = (String)attributes.get("calendarName");

		if (calendarName != null) {
			setCalendarName(calendarName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new SlaCalendarMasterWrapper((SlaCalendarMaster)_slaCalendarMaster.clone());
	}

	@Override
	public int compareTo(SlaCalendarMaster slaCalendarMaster) {
		return _slaCalendarMaster.compareTo(slaCalendarMaster);
	}

	/**
	* Returns the calendar name of this sla calendar master.
	*
	* @return the calendar name of this sla calendar master
	*/
	@Override
	public java.lang.String getCalendarName() {
		return _slaCalendarMaster.getCalendarName();
	}

	/**
	* Returns the created by of this sla calendar master.
	*
	* @return the created by of this sla calendar master
	*/
	@Override
	public int getCreatedBy() {
		return _slaCalendarMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this sla calendar master.
	*
	* @return the created date of this sla calendar master
	*/
	@Override
	public Date getCreatedDate() {
		return _slaCalendarMaster.getCreatedDate();
	}

	/**
	* Returns the default holidays of this sla calendar master.
	*
	* @return the default holidays of this sla calendar master
	*/
	@Override
	public boolean getDefaultHolidays() {
		return _slaCalendarMaster.getDefaultHolidays();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _slaCalendarMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this sla calendar master.
	*
	* @return the inbound status of this sla calendar master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _slaCalendarMaster.getInboundStatus();
	}

	/**
	* Returns the modified by of this sla calendar master.
	*
	* @return the modified by of this sla calendar master
	*/
	@Override
	public int getModifiedBy() {
		return _slaCalendarMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this sla calendar master.
	*
	* @return the modified date of this sla calendar master
	*/
	@Override
	public Date getModifiedDate() {
		return _slaCalendarMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this sla calendar master.
	*
	* @return the primary key of this sla calendar master
	*/
	@Override
	public int getPrimaryKey() {
		return _slaCalendarMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _slaCalendarMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the sla calendar master sid of this sla calendar master.
	*
	* @return the sla calendar master sid of this sla calendar master
	*/
	@Override
	public int getSlaCalendarMasterSid() {
		return _slaCalendarMaster.getSlaCalendarMasterSid();
	}

	@Override
	public int hashCode() {
		return _slaCalendarMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _slaCalendarMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this sla calendar master is default holidays.
	*
	* @return <code>true</code> if this sla calendar master is default holidays; <code>false</code> otherwise
	*/
	@Override
	public boolean isDefaultHolidays() {
		return _slaCalendarMaster.isDefaultHolidays();
	}

	@Override
	public boolean isEscapedModel() {
		return _slaCalendarMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _slaCalendarMaster.isNew();
	}

	@Override
	public void persist() {
		_slaCalendarMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_slaCalendarMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the calendar name of this sla calendar master.
	*
	* @param calendarName the calendar name of this sla calendar master
	*/
	@Override
	public void setCalendarName(java.lang.String calendarName) {
		_slaCalendarMaster.setCalendarName(calendarName);
	}

	/**
	* Sets the created by of this sla calendar master.
	*
	* @param createdBy the created by of this sla calendar master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_slaCalendarMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this sla calendar master.
	*
	* @param createdDate the created date of this sla calendar master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_slaCalendarMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets whether this sla calendar master is default holidays.
	*
	* @param defaultHolidays the default holidays of this sla calendar master
	*/
	@Override
	public void setDefaultHolidays(boolean defaultHolidays) {
		_slaCalendarMaster.setDefaultHolidays(defaultHolidays);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_slaCalendarMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_slaCalendarMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_slaCalendarMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this sla calendar master.
	*
	* @param inboundStatus the inbound status of this sla calendar master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_slaCalendarMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this sla calendar master.
	*
	* @param modifiedBy the modified by of this sla calendar master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_slaCalendarMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this sla calendar master.
	*
	* @param modifiedDate the modified date of this sla calendar master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_slaCalendarMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_slaCalendarMaster.setNew(n);
	}

	/**
	* Sets the primary key of this sla calendar master.
	*
	* @param primaryKey the primary key of this sla calendar master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_slaCalendarMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_slaCalendarMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sla calendar master sid of this sla calendar master.
	*
	* @param slaCalendarMasterSid the sla calendar master sid of this sla calendar master
	*/
	@Override
	public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
		_slaCalendarMaster.setSlaCalendarMasterSid(slaCalendarMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SlaCalendarMaster> toCacheModel() {
		return _slaCalendarMaster.toCacheModel();
	}

	@Override
	public SlaCalendarMaster toEscapedModel() {
		return new SlaCalendarMasterWrapper(_slaCalendarMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _slaCalendarMaster.toString();
	}

	@Override
	public SlaCalendarMaster toUnescapedModel() {
		return new SlaCalendarMasterWrapper(_slaCalendarMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _slaCalendarMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SlaCalendarMasterWrapper)) {
			return false;
		}

		SlaCalendarMasterWrapper slaCalendarMasterWrapper = (SlaCalendarMasterWrapper)obj;

		if (Objects.equals(_slaCalendarMaster,
					slaCalendarMasterWrapper._slaCalendarMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public SlaCalendarMaster getWrappedModel() {
		return _slaCalendarMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _slaCalendarMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _slaCalendarMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_slaCalendarMaster.resetOriginalValues();
	}

	private final SlaCalendarMaster _slaCalendarMaster;
}