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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ChActualDiscount}.
 * </p>
 *
 * @author
 * @see ChActualDiscount
 * @generated
 */
@ProviderType
public class ChActualDiscountWrapper implements ChActualDiscount,
	ModelWrapper<ChActualDiscount> {
	public ChActualDiscountWrapper(ChActualDiscount chActualDiscount) {
		_chActualDiscount = chActualDiscount;
	}

	@Override
	public Class<?> getModelClass() {
		return ChActualDiscount.class;
	}

	@Override
	public String getModelClassName() {
		return ChActualDiscount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("actualRate", getActualRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("actualSales", getActualSales());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
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
		return new ChActualDiscountWrapper((ChActualDiscount)_chActualDiscount.clone());
	}

	@Override
	public int compareTo(ChActualDiscount chActualDiscount) {
		return _chActualDiscount.compareTo(chActualDiscount);
	}

	/**
	* Returns the actual rate of this ch actual discount.
	*
	* @return the actual rate of this ch actual discount
	*/
	@Override
	public double getActualRate() {
		return _chActualDiscount.getActualRate();
	}

	/**
	* Returns the actual sales of this ch actual discount.
	*
	* @return the actual sales of this ch actual discount
	*/
	@Override
	public double getActualSales() {
		return _chActualDiscount.getActualSales();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _chActualDiscount.getExpandoBridge();
	}

	/**
	* Returns the period sid of this ch actual discount.
	*
	* @return the period sid of this ch actual discount
	*/
	@Override
	public int getPeriodSid() {
		return _chActualDiscount.getPeriodSid();
	}

	/**
	* Returns the primary key of this ch actual discount.
	*
	* @return the primary key of this ch actual discount
	*/
	@Override
	public com.stpl.app.service.persistence.ChActualDiscountPK getPrimaryKey() {
		return _chActualDiscount.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chActualDiscount.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this ch actual discount.
	*
	* @return the projection details sid of this ch actual discount
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _chActualDiscount.getProjectionDetailsSid();
	}

	/**
	* Returns the rs model sid of this ch actual discount.
	*
	* @return the rs model sid of this ch actual discount
	*/
	@Override
	public int getRsModelSid() {
		return _chActualDiscount.getRsModelSid();
	}

	@Override
	public int hashCode() {
		return _chActualDiscount.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _chActualDiscount.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _chActualDiscount.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _chActualDiscount.isNew();
	}

	@Override
	public void persist() {
		_chActualDiscount.persist();
	}

	/**
	* Sets the actual rate of this ch actual discount.
	*
	* @param actualRate the actual rate of this ch actual discount
	*/
	@Override
	public void setActualRate(double actualRate) {
		_chActualDiscount.setActualRate(actualRate);
	}

	/**
	* Sets the actual sales of this ch actual discount.
	*
	* @param actualSales the actual sales of this ch actual discount
	*/
	@Override
	public void setActualSales(double actualSales) {
		_chActualDiscount.setActualSales(actualSales);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_chActualDiscount.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_chActualDiscount.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_chActualDiscount.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_chActualDiscount.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_chActualDiscount.setNew(n);
	}

	/**
	* Sets the period sid of this ch actual discount.
	*
	* @param periodSid the period sid of this ch actual discount
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_chActualDiscount.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this ch actual discount.
	*
	* @param primaryKey the primary key of this ch actual discount
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.ChActualDiscountPK primaryKey) {
		_chActualDiscount.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_chActualDiscount.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this ch actual discount.
	*
	* @param projectionDetailsSid the projection details sid of this ch actual discount
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_chActualDiscount.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the rs model sid of this ch actual discount.
	*
	* @param rsModelSid the rs model sid of this ch actual discount
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_chActualDiscount.setRsModelSid(rsModelSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ChActualDiscount> toCacheModel() {
		return _chActualDiscount.toCacheModel();
	}

	@Override
	public ChActualDiscount toEscapedModel() {
		return new ChActualDiscountWrapper(_chActualDiscount.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _chActualDiscount.toString();
	}

	@Override
	public ChActualDiscount toUnescapedModel() {
		return new ChActualDiscountWrapper(_chActualDiscount.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _chActualDiscount.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChActualDiscountWrapper)) {
			return false;
		}

		ChActualDiscountWrapper chActualDiscountWrapper = (ChActualDiscountWrapper)obj;

		if (Objects.equals(_chActualDiscount,
					chActualDiscountWrapper._chActualDiscount)) {
			return true;
		}

		return false;
	}

	@Override
	public ChActualDiscount getWrappedModel() {
		return _chActualDiscount;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _chActualDiscount.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _chActualDiscount.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_chActualDiscount.resetOriginalValues();
	}

	private final ChActualDiscount _chActualDiscount;
}