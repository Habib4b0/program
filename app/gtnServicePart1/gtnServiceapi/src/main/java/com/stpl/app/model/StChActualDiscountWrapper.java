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
 * This class is a wrapper for {@link StChActualDiscount}.
 * </p>
 *
 * @author
 * @see StChActualDiscount
 * @generated
 */
@ProviderType
public class StChActualDiscountWrapper implements StChActualDiscount,
	ModelWrapper<StChActualDiscount> {
	public StChActualDiscountWrapper(StChActualDiscount stChActualDiscount) {
		_stChActualDiscount = stChActualDiscount;
	}

	@Override
	public Class<?> getModelClass() {
		return StChActualDiscount.class;
	}

	@Override
	public String getModelClassName() {
		return StChActualDiscount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("actualRate", getActualRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("userId", getUserId());
		attributes.put("sessionId", getSessionId());
		attributes.put("rsModelSid", getRsModelSid());
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

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		Double actualSales = (Double)attributes.get("actualSales");

		if (actualSales != null) {
			setActualSales(actualSales);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StChActualDiscountWrapper((StChActualDiscount)_stChActualDiscount.clone());
	}

	@Override
	public int compareTo(StChActualDiscount stChActualDiscount) {
		return _stChActualDiscount.compareTo(stChActualDiscount);
	}

	/**
	* Returns the actual rate of this st ch actual discount.
	*
	* @return the actual rate of this st ch actual discount
	*/
	@Override
	public double getActualRate() {
		return _stChActualDiscount.getActualRate();
	}

	/**
	* Returns the actual sales of this st ch actual discount.
	*
	* @return the actual sales of this st ch actual discount
	*/
	@Override
	public double getActualSales() {
		return _stChActualDiscount.getActualSales();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stChActualDiscount.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st ch actual discount.
	*
	* @return the last modified date of this st ch actual discount
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stChActualDiscount.getLastModifiedDate();
	}

	/**
	* Returns the period sid of this st ch actual discount.
	*
	* @return the period sid of this st ch actual discount
	*/
	@Override
	public int getPeriodSid() {
		return _stChActualDiscount.getPeriodSid();
	}

	/**
	* Returns the primary key of this st ch actual discount.
	*
	* @return the primary key of this st ch actual discount
	*/
	@Override
	public com.stpl.app.service.persistence.StChActualDiscountPK getPrimaryKey() {
		return _stChActualDiscount.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stChActualDiscount.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st ch actual discount.
	*
	* @return the projection details sid of this st ch actual discount
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stChActualDiscount.getProjectionDetailsSid();
	}

	/**
	* Returns the rs model sid of this st ch actual discount.
	*
	* @return the rs model sid of this st ch actual discount
	*/
	@Override
	public int getRsModelSid() {
		return _stChActualDiscount.getRsModelSid();
	}

	/**
	* Returns the session ID of this st ch actual discount.
	*
	* @return the session ID of this st ch actual discount
	*/
	@Override
	public int getSessionId() {
		return _stChActualDiscount.getSessionId();
	}

	/**
	* Returns the user ID of this st ch actual discount.
	*
	* @return the user ID of this st ch actual discount
	*/
	@Override
	public int getUserId() {
		return _stChActualDiscount.getUserId();
	}

	@Override
	public int hashCode() {
		return _stChActualDiscount.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stChActualDiscount.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stChActualDiscount.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stChActualDiscount.isNew();
	}

	@Override
	public void persist() {
		_stChActualDiscount.persist();
	}

	/**
	* Sets the actual rate of this st ch actual discount.
	*
	* @param actualRate the actual rate of this st ch actual discount
	*/
	@Override
	public void setActualRate(double actualRate) {
		_stChActualDiscount.setActualRate(actualRate);
	}

	/**
	* Sets the actual sales of this st ch actual discount.
	*
	* @param actualSales the actual sales of this st ch actual discount
	*/
	@Override
	public void setActualSales(double actualSales) {
		_stChActualDiscount.setActualSales(actualSales);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stChActualDiscount.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stChActualDiscount.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stChActualDiscount.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stChActualDiscount.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st ch actual discount.
	*
	* @param lastModifiedDate the last modified date of this st ch actual discount
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stChActualDiscount.setLastModifiedDate(lastModifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stChActualDiscount.setNew(n);
	}

	/**
	* Sets the period sid of this st ch actual discount.
	*
	* @param periodSid the period sid of this st ch actual discount
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_stChActualDiscount.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this st ch actual discount.
	*
	* @param primaryKey the primary key of this st ch actual discount
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StChActualDiscountPK primaryKey) {
		_stChActualDiscount.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stChActualDiscount.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st ch actual discount.
	*
	* @param projectionDetailsSid the projection details sid of this st ch actual discount
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stChActualDiscount.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the rs model sid of this st ch actual discount.
	*
	* @param rsModelSid the rs model sid of this st ch actual discount
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_stChActualDiscount.setRsModelSid(rsModelSid);
	}

	/**
	* Sets the session ID of this st ch actual discount.
	*
	* @param sessionId the session ID of this st ch actual discount
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stChActualDiscount.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st ch actual discount.
	*
	* @param userId the user ID of this st ch actual discount
	*/
	@Override
	public void setUserId(int userId) {
		_stChActualDiscount.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StChActualDiscount> toCacheModel() {
		return _stChActualDiscount.toCacheModel();
	}

	@Override
	public StChActualDiscount toEscapedModel() {
		return new StChActualDiscountWrapper(_stChActualDiscount.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stChActualDiscount.toString();
	}

	@Override
	public StChActualDiscount toUnescapedModel() {
		return new StChActualDiscountWrapper(_stChActualDiscount.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stChActualDiscount.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChActualDiscountWrapper)) {
			return false;
		}

		StChActualDiscountWrapper stChActualDiscountWrapper = (StChActualDiscountWrapper)obj;

		if (Objects.equals(_stChActualDiscount,
					stChActualDiscountWrapper._stChActualDiscount)) {
			return true;
		}

		return false;
	}

	@Override
	public StChActualDiscount getWrappedModel() {
		return _stChActualDiscount;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stChActualDiscount.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stChActualDiscount.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stChActualDiscount.resetOriginalValues();
	}

	private final StChActualDiscount _stChActualDiscount;
}