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
 * This class is a wrapper for {@link StNmActualPpa}.
 * </p>
 *
 * @author
 * @see StNmActualPpa
 * @generated
 */
@ProviderType
public class StNmActualPpaWrapper implements StNmActualPpa,
	ModelWrapper<StNmActualPpa> {
	public StNmActualPpaWrapper(StNmActualPpa stNmActualPpa) {
		_stNmActualPpa = stNmActualPpa;
	}

	@Override
	public Class<?> getModelClass() {
		return StNmActualPpa.class;
	}

	@Override
	public String getModelClassName() {
		return StNmActualPpa.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("actualRate", getActualRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("actualProjDiscountDollar", getActualProjDiscountDollar());
		attributes.put("actualProjectionSales", getActualProjectionSales());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("userId", getUserId());
		attributes.put("actualProjectionRate", getActualProjectionRate());
		attributes.put("sessionId", getSessionId());
		attributes.put("actualProjDiscountUnits", getActualProjDiscountUnits());
		attributes.put("actualDiscountDollar", getActualDiscountDollar());
		attributes.put("actualDiscountUnits", getActualDiscountUnits());
		attributes.put("actualSales", getActualSales());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Double actualRate = (Double)attributes.get("actualRate");

		if (actualRate != null) {
			setActualRate(actualRate);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Double actualProjDiscountDollar = (Double)attributes.get(
				"actualProjDiscountDollar");

		if (actualProjDiscountDollar != null) {
			setActualProjDiscountDollar(actualProjDiscountDollar);
		}

		Double actualProjectionSales = (Double)attributes.get(
				"actualProjectionSales");

		if (actualProjectionSales != null) {
			setActualProjectionSales(actualProjectionSales);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Double actualProjectionRate = (Double)attributes.get(
				"actualProjectionRate");

		if (actualProjectionRate != null) {
			setActualProjectionRate(actualProjectionRate);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double actualProjDiscountUnits = (Double)attributes.get(
				"actualProjDiscountUnits");

		if (actualProjDiscountUnits != null) {
			setActualProjDiscountUnits(actualProjDiscountUnits);
		}

		Double actualDiscountDollar = (Double)attributes.get(
				"actualDiscountDollar");

		if (actualDiscountDollar != null) {
			setActualDiscountDollar(actualDiscountDollar);
		}

		Double actualDiscountUnits = (Double)attributes.get(
				"actualDiscountUnits");

		if (actualDiscountUnits != null) {
			setActualDiscountUnits(actualDiscountUnits);
		}

		Double actualSales = (Double)attributes.get("actualSales");

		if (actualSales != null) {
			setActualSales(actualSales);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StNmActualPpaWrapper((StNmActualPpa)_stNmActualPpa.clone());
	}

	@Override
	public int compareTo(StNmActualPpa stNmActualPpa) {
		return _stNmActualPpa.compareTo(stNmActualPpa);
	}

	/**
	* Returns the actual discount dollar of this st nm actual ppa.
	*
	* @return the actual discount dollar of this st nm actual ppa
	*/
	@Override
	public double getActualDiscountDollar() {
		return _stNmActualPpa.getActualDiscountDollar();
	}

	/**
	* Returns the actual discount units of this st nm actual ppa.
	*
	* @return the actual discount units of this st nm actual ppa
	*/
	@Override
	public double getActualDiscountUnits() {
		return _stNmActualPpa.getActualDiscountUnits();
	}

	/**
	* Returns the actual proj discount dollar of this st nm actual ppa.
	*
	* @return the actual proj discount dollar of this st nm actual ppa
	*/
	@Override
	public double getActualProjDiscountDollar() {
		return _stNmActualPpa.getActualProjDiscountDollar();
	}

	/**
	* Returns the actual proj discount units of this st nm actual ppa.
	*
	* @return the actual proj discount units of this st nm actual ppa
	*/
	@Override
	public double getActualProjDiscountUnits() {
		return _stNmActualPpa.getActualProjDiscountUnits();
	}

	/**
	* Returns the actual projection rate of this st nm actual ppa.
	*
	* @return the actual projection rate of this st nm actual ppa
	*/
	@Override
	public double getActualProjectionRate() {
		return _stNmActualPpa.getActualProjectionRate();
	}

	/**
	* Returns the actual projection sales of this st nm actual ppa.
	*
	* @return the actual projection sales of this st nm actual ppa
	*/
	@Override
	public double getActualProjectionSales() {
		return _stNmActualPpa.getActualProjectionSales();
	}

	/**
	* Returns the actual rate of this st nm actual ppa.
	*
	* @return the actual rate of this st nm actual ppa
	*/
	@Override
	public double getActualRate() {
		return _stNmActualPpa.getActualRate();
	}

	/**
	* Returns the actual sales of this st nm actual ppa.
	*
	* @return the actual sales of this st nm actual ppa
	*/
	@Override
	public double getActualSales() {
		return _stNmActualPpa.getActualSales();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stNmActualPpa.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st nm actual ppa.
	*
	* @return the last modified date of this st nm actual ppa
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stNmActualPpa.getLastModifiedDate();
	}

	/**
	* Returns the period sid of this st nm actual ppa.
	*
	* @return the period sid of this st nm actual ppa
	*/
	@Override
	public int getPeriodSid() {
		return _stNmActualPpa.getPeriodSid();
	}

	/**
	* Returns the primary key of this st nm actual ppa.
	*
	* @return the primary key of this st nm actual ppa
	*/
	@Override
	public com.stpl.app.service.persistence.StNmActualPpaPK getPrimaryKey() {
		return _stNmActualPpa.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stNmActualPpa.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st nm actual ppa.
	*
	* @return the projection details sid of this st nm actual ppa
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stNmActualPpa.getProjectionDetailsSid();
	}

	/**
	* Returns the session ID of this st nm actual ppa.
	*
	* @return the session ID of this st nm actual ppa
	*/
	@Override
	public int getSessionId() {
		return _stNmActualPpa.getSessionId();
	}

	/**
	* Returns the user ID of this st nm actual ppa.
	*
	* @return the user ID of this st nm actual ppa
	*/
	@Override
	public int getUserId() {
		return _stNmActualPpa.getUserId();
	}

	@Override
	public int hashCode() {
		return _stNmActualPpa.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stNmActualPpa.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stNmActualPpa.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stNmActualPpa.isNew();
	}

	@Override
	public void persist() {
		_stNmActualPpa.persist();
	}

	/**
	* Sets the actual discount dollar of this st nm actual ppa.
	*
	* @param actualDiscountDollar the actual discount dollar of this st nm actual ppa
	*/
	@Override
	public void setActualDiscountDollar(double actualDiscountDollar) {
		_stNmActualPpa.setActualDiscountDollar(actualDiscountDollar);
	}

	/**
	* Sets the actual discount units of this st nm actual ppa.
	*
	* @param actualDiscountUnits the actual discount units of this st nm actual ppa
	*/
	@Override
	public void setActualDiscountUnits(double actualDiscountUnits) {
		_stNmActualPpa.setActualDiscountUnits(actualDiscountUnits);
	}

	/**
	* Sets the actual proj discount dollar of this st nm actual ppa.
	*
	* @param actualProjDiscountDollar the actual proj discount dollar of this st nm actual ppa
	*/
	@Override
	public void setActualProjDiscountDollar(double actualProjDiscountDollar) {
		_stNmActualPpa.setActualProjDiscountDollar(actualProjDiscountDollar);
	}

	/**
	* Sets the actual proj discount units of this st nm actual ppa.
	*
	* @param actualProjDiscountUnits the actual proj discount units of this st nm actual ppa
	*/
	@Override
	public void setActualProjDiscountUnits(double actualProjDiscountUnits) {
		_stNmActualPpa.setActualProjDiscountUnits(actualProjDiscountUnits);
	}

	/**
	* Sets the actual projection rate of this st nm actual ppa.
	*
	* @param actualProjectionRate the actual projection rate of this st nm actual ppa
	*/
	@Override
	public void setActualProjectionRate(double actualProjectionRate) {
		_stNmActualPpa.setActualProjectionRate(actualProjectionRate);
	}

	/**
	* Sets the actual projection sales of this st nm actual ppa.
	*
	* @param actualProjectionSales the actual projection sales of this st nm actual ppa
	*/
	@Override
	public void setActualProjectionSales(double actualProjectionSales) {
		_stNmActualPpa.setActualProjectionSales(actualProjectionSales);
	}

	/**
	* Sets the actual rate of this st nm actual ppa.
	*
	* @param actualRate the actual rate of this st nm actual ppa
	*/
	@Override
	public void setActualRate(double actualRate) {
		_stNmActualPpa.setActualRate(actualRate);
	}

	/**
	* Sets the actual sales of this st nm actual ppa.
	*
	* @param actualSales the actual sales of this st nm actual ppa
	*/
	@Override
	public void setActualSales(double actualSales) {
		_stNmActualPpa.setActualSales(actualSales);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stNmActualPpa.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stNmActualPpa.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stNmActualPpa.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stNmActualPpa.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st nm actual ppa.
	*
	* @param lastModifiedDate the last modified date of this st nm actual ppa
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stNmActualPpa.setLastModifiedDate(lastModifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stNmActualPpa.setNew(n);
	}

	/**
	* Sets the period sid of this st nm actual ppa.
	*
	* @param periodSid the period sid of this st nm actual ppa
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_stNmActualPpa.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this st nm actual ppa.
	*
	* @param primaryKey the primary key of this st nm actual ppa
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StNmActualPpaPK primaryKey) {
		_stNmActualPpa.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stNmActualPpa.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st nm actual ppa.
	*
	* @param projectionDetailsSid the projection details sid of this st nm actual ppa
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stNmActualPpa.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the session ID of this st nm actual ppa.
	*
	* @param sessionId the session ID of this st nm actual ppa
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stNmActualPpa.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st nm actual ppa.
	*
	* @param userId the user ID of this st nm actual ppa
	*/
	@Override
	public void setUserId(int userId) {
		_stNmActualPpa.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StNmActualPpa> toCacheModel() {
		return _stNmActualPpa.toCacheModel();
	}

	@Override
	public StNmActualPpa toEscapedModel() {
		return new StNmActualPpaWrapper(_stNmActualPpa.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stNmActualPpa.toString();
	}

	@Override
	public StNmActualPpa toUnescapedModel() {
		return new StNmActualPpaWrapper(_stNmActualPpa.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stNmActualPpa.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmActualPpaWrapper)) {
			return false;
		}

		StNmActualPpaWrapper stNmActualPpaWrapper = (StNmActualPpaWrapper)obj;

		if (Objects.equals(_stNmActualPpa, stNmActualPpaWrapper._stNmActualPpa)) {
			return true;
		}

		return false;
	}

	@Override
	public StNmActualPpa getWrappedModel() {
		return _stNmActualPpa;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stNmActualPpa.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stNmActualPpa.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stNmActualPpa.resetOriginalValues();
	}

	private final StNmActualPpa _stNmActualPpa;
}