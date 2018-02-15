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
 * This class is a wrapper for {@link NmActualPpa}.
 * </p>
 *
 * @author
 * @see NmActualPpa
 * @generated
 */
@ProviderType
public class NmActualPpaWrapper implements NmActualPpa,
	ModelWrapper<NmActualPpa> {
	public NmActualPpaWrapper(NmActualPpa nmActualPpa) {
		_nmActualPpa = nmActualPpa;
	}

	@Override
	public Class<?> getModelClass() {
		return NmActualPpa.class;
	}

	@Override
	public String getModelClassName() {
		return NmActualPpa.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("actualRate", getActualRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("actualDiscountDollar", getActualDiscountDollar());
		attributes.put("actualDiscountUnits", getActualDiscountUnits());
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
		return new NmActualPpaWrapper((NmActualPpa)_nmActualPpa.clone());
	}

	@Override
	public int compareTo(NmActualPpa nmActualPpa) {
		return _nmActualPpa.compareTo(nmActualPpa);
	}

	/**
	* Returns the actual discount dollar of this nm actual ppa.
	*
	* @return the actual discount dollar of this nm actual ppa
	*/
	@Override
	public double getActualDiscountDollar() {
		return _nmActualPpa.getActualDiscountDollar();
	}

	/**
	* Returns the actual discount units of this nm actual ppa.
	*
	* @return the actual discount units of this nm actual ppa
	*/
	@Override
	public double getActualDiscountUnits() {
		return _nmActualPpa.getActualDiscountUnits();
	}

	/**
	* Returns the actual rate of this nm actual ppa.
	*
	* @return the actual rate of this nm actual ppa
	*/
	@Override
	public double getActualRate() {
		return _nmActualPpa.getActualRate();
	}

	/**
	* Returns the actual sales of this nm actual ppa.
	*
	* @return the actual sales of this nm actual ppa
	*/
	@Override
	public double getActualSales() {
		return _nmActualPpa.getActualSales();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmActualPpa.getExpandoBridge();
	}

	/**
	* Returns the period sid of this nm actual ppa.
	*
	* @return the period sid of this nm actual ppa
	*/
	@Override
	public int getPeriodSid() {
		return _nmActualPpa.getPeriodSid();
	}

	/**
	* Returns the primary key of this nm actual ppa.
	*
	* @return the primary key of this nm actual ppa
	*/
	@Override
	public com.stpl.app.service.persistence.NmActualPpaPK getPrimaryKey() {
		return _nmActualPpa.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmActualPpa.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this nm actual ppa.
	*
	* @return the projection details sid of this nm actual ppa
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _nmActualPpa.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _nmActualPpa.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmActualPpa.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmActualPpa.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmActualPpa.isNew();
	}

	@Override
	public void persist() {
		_nmActualPpa.persist();
	}

	/**
	* Sets the actual discount dollar of this nm actual ppa.
	*
	* @param actualDiscountDollar the actual discount dollar of this nm actual ppa
	*/
	@Override
	public void setActualDiscountDollar(double actualDiscountDollar) {
		_nmActualPpa.setActualDiscountDollar(actualDiscountDollar);
	}

	/**
	* Sets the actual discount units of this nm actual ppa.
	*
	* @param actualDiscountUnits the actual discount units of this nm actual ppa
	*/
	@Override
	public void setActualDiscountUnits(double actualDiscountUnits) {
		_nmActualPpa.setActualDiscountUnits(actualDiscountUnits);
	}

	/**
	* Sets the actual rate of this nm actual ppa.
	*
	* @param actualRate the actual rate of this nm actual ppa
	*/
	@Override
	public void setActualRate(double actualRate) {
		_nmActualPpa.setActualRate(actualRate);
	}

	/**
	* Sets the actual sales of this nm actual ppa.
	*
	* @param actualSales the actual sales of this nm actual ppa
	*/
	@Override
	public void setActualSales(double actualSales) {
		_nmActualPpa.setActualSales(actualSales);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmActualPpa.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmActualPpa.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmActualPpa.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmActualPpa.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_nmActualPpa.setNew(n);
	}

	/**
	* Sets the period sid of this nm actual ppa.
	*
	* @param periodSid the period sid of this nm actual ppa
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_nmActualPpa.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this nm actual ppa.
	*
	* @param primaryKey the primary key of this nm actual ppa
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.NmActualPpaPK primaryKey) {
		_nmActualPpa.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmActualPpa.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this nm actual ppa.
	*
	* @param projectionDetailsSid the projection details sid of this nm actual ppa
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_nmActualPpa.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmActualPpa> toCacheModel() {
		return _nmActualPpa.toCacheModel();
	}

	@Override
	public NmActualPpa toEscapedModel() {
		return new NmActualPpaWrapper(_nmActualPpa.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmActualPpa.toString();
	}

	@Override
	public NmActualPpa toUnescapedModel() {
		return new NmActualPpaWrapper(_nmActualPpa.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmActualPpa.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmActualPpaWrapper)) {
			return false;
		}

		NmActualPpaWrapper nmActualPpaWrapper = (NmActualPpaWrapper)obj;

		if (Objects.equals(_nmActualPpa, nmActualPpaWrapper._nmActualPpa)) {
			return true;
		}

		return false;
	}

	@Override
	public NmActualPpa getWrappedModel() {
		return _nmActualPpa;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmActualPpa.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmActualPpa.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmActualPpa.resetOriginalValues();
	}

	private final NmActualPpa _nmActualPpa;
}