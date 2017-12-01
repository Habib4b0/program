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
 * This class is a wrapper for {@link NmActualDiscount}.
 * </p>
 *
 * @author
 * @see NmActualDiscount
 * @generated
 */
@ProviderType
public class NmActualDiscountWrapper implements NmActualDiscount,
	ModelWrapper<NmActualDiscount> {
	public NmActualDiscountWrapper(NmActualDiscount nmActualDiscount) {
		_nmActualDiscount = nmActualDiscount;
	}

	@Override
	public Class<?> getModelClass() {
		return NmActualDiscount.class;
	}

	@Override
	public String getModelClassName() {
		return NmActualDiscount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("actualRate", getActualRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
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

		Double actualSales = (Double)attributes.get("actualSales");

		if (actualSales != null) {
			setActualSales(actualSales);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NmActualDiscountWrapper((NmActualDiscount)_nmActualDiscount.clone());
	}

	@Override
	public int compareTo(NmActualDiscount nmActualDiscount) {
		return _nmActualDiscount.compareTo(nmActualDiscount);
	}

	/**
	* Returns the actual rate of this nm actual discount.
	*
	* @return the actual rate of this nm actual discount
	*/
	@Override
	public double getActualRate() {
		return _nmActualDiscount.getActualRate();
	}

	/**
	* Returns the actual sales of this nm actual discount.
	*
	* @return the actual sales of this nm actual discount
	*/
	@Override
	public double getActualSales() {
		return _nmActualDiscount.getActualSales();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmActualDiscount.getExpandoBridge();
	}

	/**
	* Returns the period sid of this nm actual discount.
	*
	* @return the period sid of this nm actual discount
	*/
	@Override
	public int getPeriodSid() {
		return _nmActualDiscount.getPeriodSid();
	}

	/**
	* Returns the primary key of this nm actual discount.
	*
	* @return the primary key of this nm actual discount
	*/
	@Override
	public com.stpl.app.service.persistence.NmActualDiscountPK getPrimaryKey() {
		return _nmActualDiscount.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmActualDiscount.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this nm actual discount.
	*
	* @return the projection details sid of this nm actual discount
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _nmActualDiscount.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _nmActualDiscount.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmActualDiscount.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmActualDiscount.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmActualDiscount.isNew();
	}

	@Override
	public void persist() {
		_nmActualDiscount.persist();
	}

	/**
	* Sets the actual rate of this nm actual discount.
	*
	* @param actualRate the actual rate of this nm actual discount
	*/
	@Override
	public void setActualRate(double actualRate) {
		_nmActualDiscount.setActualRate(actualRate);
	}

	/**
	* Sets the actual sales of this nm actual discount.
	*
	* @param actualSales the actual sales of this nm actual discount
	*/
	@Override
	public void setActualSales(double actualSales) {
		_nmActualDiscount.setActualSales(actualSales);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmActualDiscount.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmActualDiscount.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmActualDiscount.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmActualDiscount.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_nmActualDiscount.setNew(n);
	}

	/**
	* Sets the period sid of this nm actual discount.
	*
	* @param periodSid the period sid of this nm actual discount
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_nmActualDiscount.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this nm actual discount.
	*
	* @param primaryKey the primary key of this nm actual discount
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.NmActualDiscountPK primaryKey) {
		_nmActualDiscount.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmActualDiscount.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this nm actual discount.
	*
	* @param projectionDetailsSid the projection details sid of this nm actual discount
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_nmActualDiscount.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmActualDiscount> toCacheModel() {
		return _nmActualDiscount.toCacheModel();
	}

	@Override
	public NmActualDiscount toEscapedModel() {
		return new NmActualDiscountWrapper(_nmActualDiscount.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmActualDiscount.toString();
	}

	@Override
	public NmActualDiscount toUnescapedModel() {
		return new NmActualDiscountWrapper(_nmActualDiscount.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmActualDiscount.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmActualDiscountWrapper)) {
			return false;
		}

		NmActualDiscountWrapper nmActualDiscountWrapper = (NmActualDiscountWrapper)obj;

		if (Objects.equals(_nmActualDiscount,
					nmActualDiscountWrapper._nmActualDiscount)) {
			return true;
		}

		return false;
	}

	@Override
	public NmActualDiscount getWrappedModel() {
		return _nmActualDiscount;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmActualDiscount.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmActualDiscount.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmActualDiscount.resetOriginalValues();
	}

	private final NmActualDiscount _nmActualDiscount;
}