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
 * This class is a wrapper for {@link StMSupplementalDiscActuals}.
 * </p>
 *
 * @author
 * @see StMSupplementalDiscActuals
 * @generated
 */
@ProviderType
public class StMSupplementalDiscActualsWrapper
	implements StMSupplementalDiscActuals,
		ModelWrapper<StMSupplementalDiscActuals> {
	public StMSupplementalDiscActualsWrapper(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		_stMSupplementalDiscActuals = stMSupplementalDiscActuals;
	}

	@Override
	public Class<?> getModelClass() {
		return StMSupplementalDiscActuals.class;
	}

	@Override
	public String getModelClassName() {
		return StMSupplementalDiscActuals.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("actualSales", getActualSales());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("actualRate", getActualRate());
		attributes.put("userId", getUserId());
		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("actualProjectionSales", getActualProjectionSales());
		attributes.put("actualProjectionRate", getActualProjectionRate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("sessionId", getSessionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double actualSales = (Double)attributes.get("actualSales");

		if (actualSales != null) {
			setActualSales(actualSales);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Double actualRate = (Double)attributes.get("actualRate");

		if (actualRate != null) {
			setActualRate(actualRate);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Double actualProjectionSales = (Double)attributes.get(
				"actualProjectionSales");

		if (actualProjectionSales != null) {
			setActualProjectionSales(actualProjectionSales);
		}

		Double actualProjectionRate = (Double)attributes.get(
				"actualProjectionRate");

		if (actualProjectionRate != null) {
			setActualProjectionRate(actualProjectionRate);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StMSupplementalDiscActualsWrapper((StMSupplementalDiscActuals)_stMSupplementalDiscActuals.clone());
	}

	@Override
	public int compareTo(StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		return _stMSupplementalDiscActuals.compareTo(stMSupplementalDiscActuals);
	}

	/**
	* Returns the actual projection rate of this st m supplemental disc actuals.
	*
	* @return the actual projection rate of this st m supplemental disc actuals
	*/
	@Override
	public double getActualProjectionRate() {
		return _stMSupplementalDiscActuals.getActualProjectionRate();
	}

	/**
	* Returns the actual projection sales of this st m supplemental disc actuals.
	*
	* @return the actual projection sales of this st m supplemental disc actuals
	*/
	@Override
	public double getActualProjectionSales() {
		return _stMSupplementalDiscActuals.getActualProjectionSales();
	}

	/**
	* Returns the actual rate of this st m supplemental disc actuals.
	*
	* @return the actual rate of this st m supplemental disc actuals
	*/
	@Override
	public double getActualRate() {
		return _stMSupplementalDiscActuals.getActualRate();
	}

	/**
	* Returns the actual sales of this st m supplemental disc actuals.
	*
	* @return the actual sales of this st m supplemental disc actuals
	*/
	@Override
	public double getActualSales() {
		return _stMSupplementalDiscActuals.getActualSales();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stMSupplementalDiscActuals.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st m supplemental disc actuals.
	*
	* @return the last modified date of this st m supplemental disc actuals
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stMSupplementalDiscActuals.getLastModifiedDate();
	}

	/**
	* Returns the period sid of this st m supplemental disc actuals.
	*
	* @return the period sid of this st m supplemental disc actuals
	*/
	@Override
	public int getPeriodSid() {
		return _stMSupplementalDiscActuals.getPeriodSid();
	}

	/**
	* Returns the primary key of this st m supplemental disc actuals.
	*
	* @return the primary key of this st m supplemental disc actuals
	*/
	@Override
	public com.stpl.app.service.persistence.StMSupplementalDiscActualsPK getPrimaryKey() {
		return _stMSupplementalDiscActuals.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stMSupplementalDiscActuals.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st m supplemental disc actuals.
	*
	* @return the projection details sid of this st m supplemental disc actuals
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stMSupplementalDiscActuals.getProjectionDetailsSid();
	}

	/**
	* Returns the session ID of this st m supplemental disc actuals.
	*
	* @return the session ID of this st m supplemental disc actuals
	*/
	@Override
	public int getSessionId() {
		return _stMSupplementalDiscActuals.getSessionId();
	}

	/**
	* Returns the user ID of this st m supplemental disc actuals.
	*
	* @return the user ID of this st m supplemental disc actuals
	*/
	@Override
	public int getUserId() {
		return _stMSupplementalDiscActuals.getUserId();
	}

	@Override
	public int hashCode() {
		return _stMSupplementalDiscActuals.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stMSupplementalDiscActuals.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stMSupplementalDiscActuals.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stMSupplementalDiscActuals.isNew();
	}

	@Override
	public void persist() {
		_stMSupplementalDiscActuals.persist();
	}

	/**
	* Sets the actual projection rate of this st m supplemental disc actuals.
	*
	* @param actualProjectionRate the actual projection rate of this st m supplemental disc actuals
	*/
	@Override
	public void setActualProjectionRate(double actualProjectionRate) {
		_stMSupplementalDiscActuals.setActualProjectionRate(actualProjectionRate);
	}

	/**
	* Sets the actual projection sales of this st m supplemental disc actuals.
	*
	* @param actualProjectionSales the actual projection sales of this st m supplemental disc actuals
	*/
	@Override
	public void setActualProjectionSales(double actualProjectionSales) {
		_stMSupplementalDiscActuals.setActualProjectionSales(actualProjectionSales);
	}

	/**
	* Sets the actual rate of this st m supplemental disc actuals.
	*
	* @param actualRate the actual rate of this st m supplemental disc actuals
	*/
	@Override
	public void setActualRate(double actualRate) {
		_stMSupplementalDiscActuals.setActualRate(actualRate);
	}

	/**
	* Sets the actual sales of this st m supplemental disc actuals.
	*
	* @param actualSales the actual sales of this st m supplemental disc actuals
	*/
	@Override
	public void setActualSales(double actualSales) {
		_stMSupplementalDiscActuals.setActualSales(actualSales);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stMSupplementalDiscActuals.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stMSupplementalDiscActuals.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stMSupplementalDiscActuals.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stMSupplementalDiscActuals.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st m supplemental disc actuals.
	*
	* @param lastModifiedDate the last modified date of this st m supplemental disc actuals
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stMSupplementalDiscActuals.setLastModifiedDate(lastModifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stMSupplementalDiscActuals.setNew(n);
	}

	/**
	* Sets the period sid of this st m supplemental disc actuals.
	*
	* @param periodSid the period sid of this st m supplemental disc actuals
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_stMSupplementalDiscActuals.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this st m supplemental disc actuals.
	*
	* @param primaryKey the primary key of this st m supplemental disc actuals
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StMSupplementalDiscActualsPK primaryKey) {
		_stMSupplementalDiscActuals.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stMSupplementalDiscActuals.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st m supplemental disc actuals.
	*
	* @param projectionDetailsSid the projection details sid of this st m supplemental disc actuals
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stMSupplementalDiscActuals.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the session ID of this st m supplemental disc actuals.
	*
	* @param sessionId the session ID of this st m supplemental disc actuals
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stMSupplementalDiscActuals.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st m supplemental disc actuals.
	*
	* @param userId the user ID of this st m supplemental disc actuals
	*/
	@Override
	public void setUserId(int userId) {
		_stMSupplementalDiscActuals.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StMSupplementalDiscActuals> toCacheModel() {
		return _stMSupplementalDiscActuals.toCacheModel();
	}

	@Override
	public StMSupplementalDiscActuals toEscapedModel() {
		return new StMSupplementalDiscActualsWrapper(_stMSupplementalDiscActuals.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stMSupplementalDiscActuals.toString();
	}

	@Override
	public StMSupplementalDiscActuals toUnescapedModel() {
		return new StMSupplementalDiscActualsWrapper(_stMSupplementalDiscActuals.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stMSupplementalDiscActuals.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StMSupplementalDiscActualsWrapper)) {
			return false;
		}

		StMSupplementalDiscActualsWrapper stMSupplementalDiscActualsWrapper = (StMSupplementalDiscActualsWrapper)obj;

		if (Objects.equals(_stMSupplementalDiscActuals,
					stMSupplementalDiscActualsWrapper._stMSupplementalDiscActuals)) {
			return true;
		}

		return false;
	}

	@Override
	public StMSupplementalDiscActuals getWrappedModel() {
		return _stMSupplementalDiscActuals;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stMSupplementalDiscActuals.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stMSupplementalDiscActuals.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stMSupplementalDiscActuals.resetOriginalValues();
	}

	private final StMSupplementalDiscActuals _stMSupplementalDiscActuals;
}