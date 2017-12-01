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

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Period service. Represents a row in the &quot;PERIOD&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.PeriodModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.PeriodImpl}.
 * </p>
 *
 * @author
 * @see Period
 * @see com.stpl.app.model.impl.PeriodImpl
 * @see com.stpl.app.model.impl.PeriodModelImpl
 * @generated
 */
@ProviderType
public interface PeriodModel extends BaseModel<Period> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a period model instance should use the {@link Period} interface instead.
	 */

	/**
	 * Returns the primary key of this period.
	 *
	 * @return the primary key of this period
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this period.
	 *
	 * @param primaryKey the primary key of this period
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the period sid of this period.
	 *
	 * @return the period sid of this period
	 */
	public int getPeriodSid();

	/**
	 * Sets the period sid of this period.
	 *
	 * @param periodSid the period sid of this period
	 */
	public void setPeriodSid(int periodSid);

	/**
	 * Returns the period date of this period.
	 *
	 * @return the period date of this period
	 */
	public Date getPeriodDate();

	/**
	 * Sets the period date of this period.
	 *
	 * @param periodDate the period date of this period
	 */
	public void setPeriodDate(Date periodDate);

	/**
	 * Returns the quarter of this period.
	 *
	 * @return the quarter of this period
	 */
	public int getQuarter();

	/**
	 * Sets the quarter of this period.
	 *
	 * @param quarter the quarter of this period
	 */
	public void setQuarter(int quarter);

	/**
	 * Returns the year of this period.
	 *
	 * @return the year of this period
	 */
	public int getYear();

	/**
	 * Sets the year of this period.
	 *
	 * @param year the year of this period
	 */
	public void setYear(int year);

	/**
	 * Returns the semi annual of this period.
	 *
	 * @return the semi annual of this period
	 */
	public int getSemiAnnual();

	/**
	 * Sets the semi annual of this period.
	 *
	 * @param semiAnnual the semi annual of this period
	 */
	public void setSemiAnnual(int semiAnnual);

	/**
	 * Returns the month of this period.
	 *
	 * @return the month of this period
	 */
	public int getMonth();

	/**
	 * Sets the month of this period.
	 *
	 * @param month the month of this period
	 */
	public void setMonth(int month);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Period period);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Period> toCacheModel();

	@Override
	public Period toEscapedModel();

	@Override
	public Period toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}