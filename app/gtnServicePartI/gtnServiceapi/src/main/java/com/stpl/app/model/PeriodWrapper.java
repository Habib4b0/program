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
 * This class is a wrapper for {@link Period}.
 * </p>
 *
 * @author
 * @see Period
 * @generated
 */
@ProviderType
public class PeriodWrapper implements Period, ModelWrapper<Period> {
	public PeriodWrapper(Period period) {
		_period = period;
	}

	@Override
	public Class<?> getModelClass() {
		return Period.class;
	}

	@Override
	public String getModelClassName() {
		return Period.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodSid", getPeriodSid());
		attributes.put("periodDate", getPeriodDate());
		attributes.put("quarter", getQuarter());
		attributes.put("year", getYear());
		attributes.put("semiAnnual", getSemiAnnual());
		attributes.put("month", getMonth());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Date periodDate = (Date)attributes.get("periodDate");

		if (periodDate != null) {
			setPeriodDate(periodDate);
		}

		Integer quarter = (Integer)attributes.get("quarter");

		if (quarter != null) {
			setQuarter(quarter);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		Integer semiAnnual = (Integer)attributes.get("semiAnnual");

		if (semiAnnual != null) {
			setSemiAnnual(semiAnnual);
		}

		Integer month = (Integer)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new PeriodWrapper((Period)_period.clone());
	}

	@Override
	public int compareTo(Period period) {
		return _period.compareTo(period);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _period.getExpandoBridge();
	}

	/**
	* Returns the month of this period.
	*
	* @return the month of this period
	*/
	@Override
	public int getMonth() {
		return _period.getMonth();
	}

	/**
	* Returns the period date of this period.
	*
	* @return the period date of this period
	*/
	@Override
	public Date getPeriodDate() {
		return _period.getPeriodDate();
	}

	/**
	* Returns the period sid of this period.
	*
	* @return the period sid of this period
	*/
	@Override
	public int getPeriodSid() {
		return _period.getPeriodSid();
	}

	/**
	* Returns the primary key of this period.
	*
	* @return the primary key of this period
	*/
	@Override
	public int getPrimaryKey() {
		return _period.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _period.getPrimaryKeyObj();
	}

	/**
	* Returns the quarter of this period.
	*
	* @return the quarter of this period
	*/
	@Override
	public int getQuarter() {
		return _period.getQuarter();
	}

	/**
	* Returns the semi annual of this period.
	*
	* @return the semi annual of this period
	*/
	@Override
	public int getSemiAnnual() {
		return _period.getSemiAnnual();
	}

	/**
	* Returns the year of this period.
	*
	* @return the year of this period
	*/
	@Override
	public int getYear() {
		return _period.getYear();
	}

	@Override
	public int hashCode() {
		return _period.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _period.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _period.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _period.isNew();
	}

	@Override
	public void persist() {
		_period.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_period.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_period.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_period.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_period.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the month of this period.
	*
	* @param month the month of this period
	*/
	@Override
	public void setMonth(int month) {
		_period.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_period.setNew(n);
	}

	/**
	* Sets the period date of this period.
	*
	* @param periodDate the period date of this period
	*/
	@Override
	public void setPeriodDate(Date periodDate) {
		_period.setPeriodDate(periodDate);
	}

	/**
	* Sets the period sid of this period.
	*
	* @param periodSid the period sid of this period
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_period.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this period.
	*
	* @param primaryKey the primary key of this period
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_period.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_period.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the quarter of this period.
	*
	* @param quarter the quarter of this period
	*/
	@Override
	public void setQuarter(int quarter) {
		_period.setQuarter(quarter);
	}

	/**
	* Sets the semi annual of this period.
	*
	* @param semiAnnual the semi annual of this period
	*/
	@Override
	public void setSemiAnnual(int semiAnnual) {
		_period.setSemiAnnual(semiAnnual);
	}

	/**
	* Sets the year of this period.
	*
	* @param year the year of this period
	*/
	@Override
	public void setYear(int year) {
		_period.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Period> toCacheModel() {
		return _period.toCacheModel();
	}

	@Override
	public Period toEscapedModel() {
		return new PeriodWrapper(_period.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _period.toString();
	}

	@Override
	public Period toUnescapedModel() {
		return new PeriodWrapper(_period.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _period.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PeriodWrapper)) {
			return false;
		}

		PeriodWrapper periodWrapper = (PeriodWrapper)obj;

		if (Objects.equals(_period, periodWrapper._period)) {
			return true;
		}

		return false;
	}

	@Override
	public Period getWrappedModel() {
		return _period;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _period.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _period.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_period.resetOriginalValues();
	}

	private final Period _period;
}