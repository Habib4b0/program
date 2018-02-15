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
 * This class is a wrapper for {@link StNmPpaProjection}.
 * </p>
 *
 * @author
 * @see StNmPpaProjection
 * @generated
 */
@ProviderType
public class StNmPpaProjectionWrapper implements StNmPpaProjection,
	ModelWrapper<StNmPpaProjection> {
	public StNmPpaProjectionWrapper(StNmPpaProjection stNmPpaProjection) {
		_stNmPpaProjection = stNmPpaProjection;
	}

	@Override
	public Class<?> getModelClass() {
		return StNmPpaProjection.class;
	}

	@Override
	public String getModelClassName() {
		return StNmPpaProjection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("projectionRate", getProjectionRate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("userId", getUserId());
		attributes.put("priceCap", getPriceCap());
		attributes.put("projectionDiscountUnits", getProjectionDiscountUnits());
		attributes.put("sessionId", getSessionId());
		attributes.put("projectionDiscountDollar", getProjectionDiscountDollar());
		attributes.put("reset", getReset());
		attributes.put("projectionSales", getProjectionSales());
		attributes.put("projectionMap", getProjectionMap());
		attributes.put("resetPriceCap", getResetPriceCap());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Double projectionRate = (Double)attributes.get("projectionRate");

		if (projectionRate != null) {
			setProjectionRate(projectionRate);
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

		Double priceCap = (Double)attributes.get("priceCap");

		if (priceCap != null) {
			setPriceCap(priceCap);
		}

		Double projectionDiscountUnits = (Double)attributes.get(
				"projectionDiscountUnits");

		if (projectionDiscountUnits != null) {
			setProjectionDiscountUnits(projectionDiscountUnits);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double projectionDiscountDollar = (Double)attributes.get(
				"projectionDiscountDollar");

		if (projectionDiscountDollar != null) {
			setProjectionDiscountDollar(projectionDiscountDollar);
		}

		Boolean reset = (Boolean)attributes.get("reset");

		if (reset != null) {
			setReset(reset);
		}

		Double projectionSales = (Double)attributes.get("projectionSales");

		if (projectionSales != null) {
			setProjectionSales(projectionSales);
		}

		Double projectionMap = (Double)attributes.get("projectionMap");

		if (projectionMap != null) {
			setProjectionMap(projectionMap);
		}

		Boolean resetPriceCap = (Boolean)attributes.get("resetPriceCap");

		if (resetPriceCap != null) {
			setResetPriceCap(resetPriceCap);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StNmPpaProjectionWrapper((StNmPpaProjection)_stNmPpaProjection.clone());
	}

	@Override
	public int compareTo(StNmPpaProjection stNmPpaProjection) {
		return _stNmPpaProjection.compareTo(stNmPpaProjection);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stNmPpaProjection.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st nm ppa projection.
	*
	* @return the last modified date of this st nm ppa projection
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stNmPpaProjection.getLastModifiedDate();
	}

	/**
	* Returns the period sid of this st nm ppa projection.
	*
	* @return the period sid of this st nm ppa projection
	*/
	@Override
	public int getPeriodSid() {
		return _stNmPpaProjection.getPeriodSid();
	}

	/**
	* Returns the price cap of this st nm ppa projection.
	*
	* @return the price cap of this st nm ppa projection
	*/
	@Override
	public double getPriceCap() {
		return _stNmPpaProjection.getPriceCap();
	}

	/**
	* Returns the primary key of this st nm ppa projection.
	*
	* @return the primary key of this st nm ppa projection
	*/
	@Override
	public com.stpl.app.service.persistence.StNmPpaProjectionPK getPrimaryKey() {
		return _stNmPpaProjection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stNmPpaProjection.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st nm ppa projection.
	*
	* @return the projection details sid of this st nm ppa projection
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stNmPpaProjection.getProjectionDetailsSid();
	}

	/**
	* Returns the projection discount dollar of this st nm ppa projection.
	*
	* @return the projection discount dollar of this st nm ppa projection
	*/
	@Override
	public double getProjectionDiscountDollar() {
		return _stNmPpaProjection.getProjectionDiscountDollar();
	}

	/**
	* Returns the projection discount units of this st nm ppa projection.
	*
	* @return the projection discount units of this st nm ppa projection
	*/
	@Override
	public double getProjectionDiscountUnits() {
		return _stNmPpaProjection.getProjectionDiscountUnits();
	}

	/**
	* Returns the projection map of this st nm ppa projection.
	*
	* @return the projection map of this st nm ppa projection
	*/
	@Override
	public double getProjectionMap() {
		return _stNmPpaProjection.getProjectionMap();
	}

	/**
	* Returns the projection rate of this st nm ppa projection.
	*
	* @return the projection rate of this st nm ppa projection
	*/
	@Override
	public double getProjectionRate() {
		return _stNmPpaProjection.getProjectionRate();
	}

	/**
	* Returns the projection sales of this st nm ppa projection.
	*
	* @return the projection sales of this st nm ppa projection
	*/
	@Override
	public double getProjectionSales() {
		return _stNmPpaProjection.getProjectionSales();
	}

	/**
	* Returns the reset of this st nm ppa projection.
	*
	* @return the reset of this st nm ppa projection
	*/
	@Override
	public boolean getReset() {
		return _stNmPpaProjection.getReset();
	}

	/**
	* Returns the reset price cap of this st nm ppa projection.
	*
	* @return the reset price cap of this st nm ppa projection
	*/
	@Override
	public boolean getResetPriceCap() {
		return _stNmPpaProjection.getResetPriceCap();
	}

	/**
	* Returns the session ID of this st nm ppa projection.
	*
	* @return the session ID of this st nm ppa projection
	*/
	@Override
	public int getSessionId() {
		return _stNmPpaProjection.getSessionId();
	}

	/**
	* Returns the user ID of this st nm ppa projection.
	*
	* @return the user ID of this st nm ppa projection
	*/
	@Override
	public int getUserId() {
		return _stNmPpaProjection.getUserId();
	}

	@Override
	public int hashCode() {
		return _stNmPpaProjection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stNmPpaProjection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stNmPpaProjection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stNmPpaProjection.isNew();
	}

	/**
	* Returns <code>true</code> if this st nm ppa projection is reset.
	*
	* @return <code>true</code> if this st nm ppa projection is reset; <code>false</code> otherwise
	*/
	@Override
	public boolean isReset() {
		return _stNmPpaProjection.isReset();
	}

	/**
	* Returns <code>true</code> if this st nm ppa projection is reset price cap.
	*
	* @return <code>true</code> if this st nm ppa projection is reset price cap; <code>false</code> otherwise
	*/
	@Override
	public boolean isResetPriceCap() {
		return _stNmPpaProjection.isResetPriceCap();
	}

	@Override
	public void persist() {
		_stNmPpaProjection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stNmPpaProjection.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stNmPpaProjection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stNmPpaProjection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stNmPpaProjection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st nm ppa projection.
	*
	* @param lastModifiedDate the last modified date of this st nm ppa projection
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stNmPpaProjection.setLastModifiedDate(lastModifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stNmPpaProjection.setNew(n);
	}

	/**
	* Sets the period sid of this st nm ppa projection.
	*
	* @param periodSid the period sid of this st nm ppa projection
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_stNmPpaProjection.setPeriodSid(periodSid);
	}

	/**
	* Sets the price cap of this st nm ppa projection.
	*
	* @param priceCap the price cap of this st nm ppa projection
	*/
	@Override
	public void setPriceCap(double priceCap) {
		_stNmPpaProjection.setPriceCap(priceCap);
	}

	/**
	* Sets the primary key of this st nm ppa projection.
	*
	* @param primaryKey the primary key of this st nm ppa projection
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StNmPpaProjectionPK primaryKey) {
		_stNmPpaProjection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stNmPpaProjection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st nm ppa projection.
	*
	* @param projectionDetailsSid the projection details sid of this st nm ppa projection
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stNmPpaProjection.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection discount dollar of this st nm ppa projection.
	*
	* @param projectionDiscountDollar the projection discount dollar of this st nm ppa projection
	*/
	@Override
	public void setProjectionDiscountDollar(double projectionDiscountDollar) {
		_stNmPpaProjection.setProjectionDiscountDollar(projectionDiscountDollar);
	}

	/**
	* Sets the projection discount units of this st nm ppa projection.
	*
	* @param projectionDiscountUnits the projection discount units of this st nm ppa projection
	*/
	@Override
	public void setProjectionDiscountUnits(double projectionDiscountUnits) {
		_stNmPpaProjection.setProjectionDiscountUnits(projectionDiscountUnits);
	}

	/**
	* Sets the projection map of this st nm ppa projection.
	*
	* @param projectionMap the projection map of this st nm ppa projection
	*/
	@Override
	public void setProjectionMap(double projectionMap) {
		_stNmPpaProjection.setProjectionMap(projectionMap);
	}

	/**
	* Sets the projection rate of this st nm ppa projection.
	*
	* @param projectionRate the projection rate of this st nm ppa projection
	*/
	@Override
	public void setProjectionRate(double projectionRate) {
		_stNmPpaProjection.setProjectionRate(projectionRate);
	}

	/**
	* Sets the projection sales of this st nm ppa projection.
	*
	* @param projectionSales the projection sales of this st nm ppa projection
	*/
	@Override
	public void setProjectionSales(double projectionSales) {
		_stNmPpaProjection.setProjectionSales(projectionSales);
	}

	/**
	* Sets whether this st nm ppa projection is reset.
	*
	* @param reset the reset of this st nm ppa projection
	*/
	@Override
	public void setReset(boolean reset) {
		_stNmPpaProjection.setReset(reset);
	}

	/**
	* Sets whether this st nm ppa projection is reset price cap.
	*
	* @param resetPriceCap the reset price cap of this st nm ppa projection
	*/
	@Override
	public void setResetPriceCap(boolean resetPriceCap) {
		_stNmPpaProjection.setResetPriceCap(resetPriceCap);
	}

	/**
	* Sets the session ID of this st nm ppa projection.
	*
	* @param sessionId the session ID of this st nm ppa projection
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stNmPpaProjection.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st nm ppa projection.
	*
	* @param userId the user ID of this st nm ppa projection
	*/
	@Override
	public void setUserId(int userId) {
		_stNmPpaProjection.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StNmPpaProjection> toCacheModel() {
		return _stNmPpaProjection.toCacheModel();
	}

	@Override
	public StNmPpaProjection toEscapedModel() {
		return new StNmPpaProjectionWrapper(_stNmPpaProjection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stNmPpaProjection.toString();
	}

	@Override
	public StNmPpaProjection toUnescapedModel() {
		return new StNmPpaProjectionWrapper(_stNmPpaProjection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stNmPpaProjection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmPpaProjectionWrapper)) {
			return false;
		}

		StNmPpaProjectionWrapper stNmPpaProjectionWrapper = (StNmPpaProjectionWrapper)obj;

		if (Objects.equals(_stNmPpaProjection,
					stNmPpaProjectionWrapper._stNmPpaProjection)) {
			return true;
		}

		return false;
	}

	@Override
	public StNmPpaProjection getWrappedModel() {
		return _stNmPpaProjection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stNmPpaProjection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stNmPpaProjection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stNmPpaProjection.resetOriginalValues();
	}

	private final StNmPpaProjection _stNmPpaProjection;
}