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
 * This class is a wrapper for {@link SlaCalendarDetails}.
 * </p>
 *
 * @author
 * @see SlaCalendarDetails
 * @generated
 */
@ProviderType
public class SlaCalendarDetailsWrapper implements SlaCalendarDetails,
	ModelWrapper<SlaCalendarDetails> {
	public SlaCalendarDetailsWrapper(SlaCalendarDetails slaCalendarDetails) {
		_slaCalendarDetails = slaCalendarDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return SlaCalendarDetails.class;
	}

	@Override
	public String getModelClassName() {
		return SlaCalendarDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("slaCalendarMasterSid", getSlaCalendarMasterSid());
		attributes.put("holidayYear", getHolidayYear());
		attributes.put("slaCalendarDetailsSid", getSlaCalendarDetailsSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("holidayDay", getHolidayDay());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("holidayCombined", getHolidayCombined());
		attributes.put("holidayMonth", getHolidayMonth());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer slaCalendarMasterSid = (Integer)attributes.get(
				"slaCalendarMasterSid");

		if (slaCalendarMasterSid != null) {
			setSlaCalendarMasterSid(slaCalendarMasterSid);
		}

		String holidayYear = (String)attributes.get("holidayYear");

		if (holidayYear != null) {
			setHolidayYear(holidayYear);
		}

		Integer slaCalendarDetailsSid = (Integer)attributes.get(
				"slaCalendarDetailsSid");

		if (slaCalendarDetailsSid != null) {
			setSlaCalendarDetailsSid(slaCalendarDetailsSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String holidayDay = (String)attributes.get("holidayDay");

		if (holidayDay != null) {
			setHolidayDay(holidayDay);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date holidayCombined = (Date)attributes.get("holidayCombined");

		if (holidayCombined != null) {
			setHolidayCombined(holidayCombined);
		}

		String holidayMonth = (String)attributes.get("holidayMonth");

		if (holidayMonth != null) {
			setHolidayMonth(holidayMonth);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new SlaCalendarDetailsWrapper((SlaCalendarDetails)_slaCalendarDetails.clone());
	}

	@Override
	public int compareTo(SlaCalendarDetails slaCalendarDetails) {
		return _slaCalendarDetails.compareTo(slaCalendarDetails);
	}

	/**
	* Returns the created by of this sla calendar details.
	*
	* @return the created by of this sla calendar details
	*/
	@Override
	public int getCreatedBy() {
		return _slaCalendarDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this sla calendar details.
	*
	* @return the created date of this sla calendar details
	*/
	@Override
	public Date getCreatedDate() {
		return _slaCalendarDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _slaCalendarDetails.getExpandoBridge();
	}

	/**
	* Returns the holiday combined of this sla calendar details.
	*
	* @return the holiday combined of this sla calendar details
	*/
	@Override
	public Date getHolidayCombined() {
		return _slaCalendarDetails.getHolidayCombined();
	}

	/**
	* Returns the holiday day of this sla calendar details.
	*
	* @return the holiday day of this sla calendar details
	*/
	@Override
	public java.lang.String getHolidayDay() {
		return _slaCalendarDetails.getHolidayDay();
	}

	/**
	* Returns the holiday month of this sla calendar details.
	*
	* @return the holiday month of this sla calendar details
	*/
	@Override
	public java.lang.String getHolidayMonth() {
		return _slaCalendarDetails.getHolidayMonth();
	}

	/**
	* Returns the holiday year of this sla calendar details.
	*
	* @return the holiday year of this sla calendar details
	*/
	@Override
	public java.lang.String getHolidayYear() {
		return _slaCalendarDetails.getHolidayYear();
	}

	/**
	* Returns the inbound status of this sla calendar details.
	*
	* @return the inbound status of this sla calendar details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _slaCalendarDetails.getInboundStatus();
	}

	/**
	* Returns the modified by of this sla calendar details.
	*
	* @return the modified by of this sla calendar details
	*/
	@Override
	public int getModifiedBy() {
		return _slaCalendarDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this sla calendar details.
	*
	* @return the modified date of this sla calendar details
	*/
	@Override
	public Date getModifiedDate() {
		return _slaCalendarDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this sla calendar details.
	*
	* @return the primary key of this sla calendar details
	*/
	@Override
	public int getPrimaryKey() {
		return _slaCalendarDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _slaCalendarDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the sla calendar details sid of this sla calendar details.
	*
	* @return the sla calendar details sid of this sla calendar details
	*/
	@Override
	public int getSlaCalendarDetailsSid() {
		return _slaCalendarDetails.getSlaCalendarDetailsSid();
	}

	/**
	* Returns the sla calendar master sid of this sla calendar details.
	*
	* @return the sla calendar master sid of this sla calendar details
	*/
	@Override
	public int getSlaCalendarMasterSid() {
		return _slaCalendarDetails.getSlaCalendarMasterSid();
	}

	@Override
	public int hashCode() {
		return _slaCalendarDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _slaCalendarDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _slaCalendarDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _slaCalendarDetails.isNew();
	}

	@Override
	public void persist() {
		_slaCalendarDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_slaCalendarDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this sla calendar details.
	*
	* @param createdBy the created by of this sla calendar details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_slaCalendarDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this sla calendar details.
	*
	* @param createdDate the created date of this sla calendar details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_slaCalendarDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_slaCalendarDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_slaCalendarDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_slaCalendarDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the holiday combined of this sla calendar details.
	*
	* @param holidayCombined the holiday combined of this sla calendar details
	*/
	@Override
	public void setHolidayCombined(Date holidayCombined) {
		_slaCalendarDetails.setHolidayCombined(holidayCombined);
	}

	/**
	* Sets the holiday day of this sla calendar details.
	*
	* @param holidayDay the holiday day of this sla calendar details
	*/
	@Override
	public void setHolidayDay(java.lang.String holidayDay) {
		_slaCalendarDetails.setHolidayDay(holidayDay);
	}

	/**
	* Sets the holiday month of this sla calendar details.
	*
	* @param holidayMonth the holiday month of this sla calendar details
	*/
	@Override
	public void setHolidayMonth(java.lang.String holidayMonth) {
		_slaCalendarDetails.setHolidayMonth(holidayMonth);
	}

	/**
	* Sets the holiday year of this sla calendar details.
	*
	* @param holidayYear the holiday year of this sla calendar details
	*/
	@Override
	public void setHolidayYear(java.lang.String holidayYear) {
		_slaCalendarDetails.setHolidayYear(holidayYear);
	}

	/**
	* Sets the inbound status of this sla calendar details.
	*
	* @param inboundStatus the inbound status of this sla calendar details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_slaCalendarDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this sla calendar details.
	*
	* @param modifiedBy the modified by of this sla calendar details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_slaCalendarDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this sla calendar details.
	*
	* @param modifiedDate the modified date of this sla calendar details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_slaCalendarDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_slaCalendarDetails.setNew(n);
	}

	/**
	* Sets the primary key of this sla calendar details.
	*
	* @param primaryKey the primary key of this sla calendar details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_slaCalendarDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_slaCalendarDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sla calendar details sid of this sla calendar details.
	*
	* @param slaCalendarDetailsSid the sla calendar details sid of this sla calendar details
	*/
	@Override
	public void setSlaCalendarDetailsSid(int slaCalendarDetailsSid) {
		_slaCalendarDetails.setSlaCalendarDetailsSid(slaCalendarDetailsSid);
	}

	/**
	* Sets the sla calendar master sid of this sla calendar details.
	*
	* @param slaCalendarMasterSid the sla calendar master sid of this sla calendar details
	*/
	@Override
	public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
		_slaCalendarDetails.setSlaCalendarMasterSid(slaCalendarMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SlaCalendarDetails> toCacheModel() {
		return _slaCalendarDetails.toCacheModel();
	}

	@Override
	public SlaCalendarDetails toEscapedModel() {
		return new SlaCalendarDetailsWrapper(_slaCalendarDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _slaCalendarDetails.toString();
	}

	@Override
	public SlaCalendarDetails toUnescapedModel() {
		return new SlaCalendarDetailsWrapper(_slaCalendarDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _slaCalendarDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SlaCalendarDetailsWrapper)) {
			return false;
		}

		SlaCalendarDetailsWrapper slaCalendarDetailsWrapper = (SlaCalendarDetailsWrapper)obj;

		if (Objects.equals(_slaCalendarDetails,
					slaCalendarDetailsWrapper._slaCalendarDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public SlaCalendarDetails getWrappedModel() {
		return _slaCalendarDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _slaCalendarDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _slaCalendarDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_slaCalendarDetails.resetOriginalValues();
	}

	private final SlaCalendarDetails _slaCalendarDetails;
}