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
 * This class is a wrapper for {@link MSupplementalDiscActuals}.
 * </p>
 *
 * @author
 * @see MSupplementalDiscActuals
 * @generated
 */
@ProviderType
public class MSupplementalDiscActualsWrapper implements MSupplementalDiscActuals,
	ModelWrapper<MSupplementalDiscActuals> {
	public MSupplementalDiscActualsWrapper(
		MSupplementalDiscActuals mSupplementalDiscActuals) {
		_mSupplementalDiscActuals = mSupplementalDiscActuals;
	}

	@Override
	public Class<?> getModelClass() {
		return MSupplementalDiscActuals.class;
	}

	@Override
	public String getModelClassName() {
		return MSupplementalDiscActuals.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("actualSales", getActualSales());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("actualRate", getActualRate());
		attributes.put("actualProjectionSales", getActualProjectionSales());
		attributes.put("actualProjectionRate", getActualProjectionRate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());

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
	}

	@Override
	public java.lang.Object clone() {
		return new MSupplementalDiscActualsWrapper((MSupplementalDiscActuals)_mSupplementalDiscActuals.clone());
	}

	@Override
	public int compareTo(MSupplementalDiscActuals mSupplementalDiscActuals) {
		return _mSupplementalDiscActuals.compareTo(mSupplementalDiscActuals);
	}

	/**
	* Returns the actual projection rate of this m supplemental disc actuals.
	*
	* @return the actual projection rate of this m supplemental disc actuals
	*/
	@Override
	public double getActualProjectionRate() {
		return _mSupplementalDiscActuals.getActualProjectionRate();
	}

	/**
	* Returns the actual projection sales of this m supplemental disc actuals.
	*
	* @return the actual projection sales of this m supplemental disc actuals
	*/
	@Override
	public double getActualProjectionSales() {
		return _mSupplementalDiscActuals.getActualProjectionSales();
	}

	/**
	* Returns the actual rate of this m supplemental disc actuals.
	*
	* @return the actual rate of this m supplemental disc actuals
	*/
	@Override
	public double getActualRate() {
		return _mSupplementalDiscActuals.getActualRate();
	}

	/**
	* Returns the actual sales of this m supplemental disc actuals.
	*
	* @return the actual sales of this m supplemental disc actuals
	*/
	@Override
	public double getActualSales() {
		return _mSupplementalDiscActuals.getActualSales();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mSupplementalDiscActuals.getExpandoBridge();
	}

	/**
	* Returns the period sid of this m supplemental disc actuals.
	*
	* @return the period sid of this m supplemental disc actuals
	*/
	@Override
	public int getPeriodSid() {
		return _mSupplementalDiscActuals.getPeriodSid();
	}

	/**
	* Returns the primary key of this m supplemental disc actuals.
	*
	* @return the primary key of this m supplemental disc actuals
	*/
	@Override
	public com.stpl.app.service.persistence.MSupplementalDiscActualsPK getPrimaryKey() {
		return _mSupplementalDiscActuals.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mSupplementalDiscActuals.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this m supplemental disc actuals.
	*
	* @return the projection details sid of this m supplemental disc actuals
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _mSupplementalDiscActuals.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _mSupplementalDiscActuals.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mSupplementalDiscActuals.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mSupplementalDiscActuals.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mSupplementalDiscActuals.isNew();
	}

	@Override
	public void persist() {
		_mSupplementalDiscActuals.persist();
	}

	/**
	* Sets the actual projection rate of this m supplemental disc actuals.
	*
	* @param actualProjectionRate the actual projection rate of this m supplemental disc actuals
	*/
	@Override
	public void setActualProjectionRate(double actualProjectionRate) {
		_mSupplementalDiscActuals.setActualProjectionRate(actualProjectionRate);
	}

	/**
	* Sets the actual projection sales of this m supplemental disc actuals.
	*
	* @param actualProjectionSales the actual projection sales of this m supplemental disc actuals
	*/
	@Override
	public void setActualProjectionSales(double actualProjectionSales) {
		_mSupplementalDiscActuals.setActualProjectionSales(actualProjectionSales);
	}

	/**
	* Sets the actual rate of this m supplemental disc actuals.
	*
	* @param actualRate the actual rate of this m supplemental disc actuals
	*/
	@Override
	public void setActualRate(double actualRate) {
		_mSupplementalDiscActuals.setActualRate(actualRate);
	}

	/**
	* Sets the actual sales of this m supplemental disc actuals.
	*
	* @param actualSales the actual sales of this m supplemental disc actuals
	*/
	@Override
	public void setActualSales(double actualSales) {
		_mSupplementalDiscActuals.setActualSales(actualSales);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mSupplementalDiscActuals.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mSupplementalDiscActuals.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mSupplementalDiscActuals.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mSupplementalDiscActuals.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_mSupplementalDiscActuals.setNew(n);
	}

	/**
	* Sets the period sid of this m supplemental disc actuals.
	*
	* @param periodSid the period sid of this m supplemental disc actuals
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_mSupplementalDiscActuals.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this m supplemental disc actuals.
	*
	* @param primaryKey the primary key of this m supplemental disc actuals
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.MSupplementalDiscActualsPK primaryKey) {
		_mSupplementalDiscActuals.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mSupplementalDiscActuals.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this m supplemental disc actuals.
	*
	* @param projectionDetailsSid the projection details sid of this m supplemental disc actuals
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_mSupplementalDiscActuals.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MSupplementalDiscActuals> toCacheModel() {
		return _mSupplementalDiscActuals.toCacheModel();
	}

	@Override
	public MSupplementalDiscActuals toEscapedModel() {
		return new MSupplementalDiscActualsWrapper(_mSupplementalDiscActuals.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mSupplementalDiscActuals.toString();
	}

	@Override
	public MSupplementalDiscActuals toUnescapedModel() {
		return new MSupplementalDiscActualsWrapper(_mSupplementalDiscActuals.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _mSupplementalDiscActuals.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSupplementalDiscActualsWrapper)) {
			return false;
		}

		MSupplementalDiscActualsWrapper mSupplementalDiscActualsWrapper = (MSupplementalDiscActualsWrapper)obj;

		if (Objects.equals(_mSupplementalDiscActuals,
					mSupplementalDiscActualsWrapper._mSupplementalDiscActuals)) {
			return true;
		}

		return false;
	}

	@Override
	public MSupplementalDiscActuals getWrappedModel() {
		return _mSupplementalDiscActuals;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mSupplementalDiscActuals.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mSupplementalDiscActuals.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mSupplementalDiscActuals.resetOriginalValues();
	}

	private final MSupplementalDiscActuals _mSupplementalDiscActuals;
}