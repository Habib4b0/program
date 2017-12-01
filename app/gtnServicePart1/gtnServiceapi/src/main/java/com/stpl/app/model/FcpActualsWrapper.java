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
 * This class is a wrapper for {@link FcpActuals}.
 * </p>
 *
 * @author
 * @see FcpActuals
 * @generated
 */
@ProviderType
public class FcpActualsWrapper implements FcpActuals, ModelWrapper<FcpActuals> {
	public FcpActualsWrapper(FcpActuals fcpActuals) {
		_fcpActuals = fcpActuals;
	}

	@Override
	public Class<?> getModelClass() {
		return FcpActuals.class;
	}

	@Override
	public String getModelClassName() {
		return FcpActuals.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodSid", getPeriodSid());
		attributes.put("priceType", getPriceType());
		attributes.put("actualPrice", getActualPrice());
		attributes.put("notes", getNotes());
		attributes.put("naProjDetailsSid", getNaProjDetailsSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		String priceType = (String)attributes.get("priceType");

		if (priceType != null) {
			setPriceType(priceType);
		}

		Double actualPrice = (Double)attributes.get("actualPrice");

		if (actualPrice != null) {
			setActualPrice(actualPrice);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Integer naProjDetailsSid = (Integer)attributes.get("naProjDetailsSid");

		if (naProjDetailsSid != null) {
			setNaProjDetailsSid(naProjDetailsSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new FcpActualsWrapper((FcpActuals)_fcpActuals.clone());
	}

	@Override
	public int compareTo(FcpActuals fcpActuals) {
		return _fcpActuals.compareTo(fcpActuals);
	}

	/**
	* Returns the actual price of this fcp actuals.
	*
	* @return the actual price of this fcp actuals
	*/
	@Override
	public double getActualPrice() {
		return _fcpActuals.getActualPrice();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fcpActuals.getExpandoBridge();
	}

	/**
	* Returns the na proj details sid of this fcp actuals.
	*
	* @return the na proj details sid of this fcp actuals
	*/
	@Override
	public int getNaProjDetailsSid() {
		return _fcpActuals.getNaProjDetailsSid();
	}

	/**
	* Returns the notes of this fcp actuals.
	*
	* @return the notes of this fcp actuals
	*/
	@Override
	public java.lang.String getNotes() {
		return _fcpActuals.getNotes();
	}

	/**
	* Returns the period sid of this fcp actuals.
	*
	* @return the period sid of this fcp actuals
	*/
	@Override
	public int getPeriodSid() {
		return _fcpActuals.getPeriodSid();
	}

	/**
	* Returns the price type of this fcp actuals.
	*
	* @return the price type of this fcp actuals
	*/
	@Override
	public java.lang.String getPriceType() {
		return _fcpActuals.getPriceType();
	}

	/**
	* Returns the primary key of this fcp actuals.
	*
	* @return the primary key of this fcp actuals
	*/
	@Override
	public com.stpl.app.service.persistence.FcpActualsPK getPrimaryKey() {
		return _fcpActuals.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fcpActuals.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _fcpActuals.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _fcpActuals.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _fcpActuals.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _fcpActuals.isNew();
	}

	@Override
	public void persist() {
		_fcpActuals.persist();
	}

	/**
	* Sets the actual price of this fcp actuals.
	*
	* @param actualPrice the actual price of this fcp actuals
	*/
	@Override
	public void setActualPrice(double actualPrice) {
		_fcpActuals.setActualPrice(actualPrice);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fcpActuals.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_fcpActuals.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_fcpActuals.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_fcpActuals.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the na proj details sid of this fcp actuals.
	*
	* @param naProjDetailsSid the na proj details sid of this fcp actuals
	*/
	@Override
	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_fcpActuals.setNaProjDetailsSid(naProjDetailsSid);
	}

	@Override
	public void setNew(boolean n) {
		_fcpActuals.setNew(n);
	}

	/**
	* Sets the notes of this fcp actuals.
	*
	* @param notes the notes of this fcp actuals
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_fcpActuals.setNotes(notes);
	}

	/**
	* Sets the period sid of this fcp actuals.
	*
	* @param periodSid the period sid of this fcp actuals
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_fcpActuals.setPeriodSid(periodSid);
	}

	/**
	* Sets the price type of this fcp actuals.
	*
	* @param priceType the price type of this fcp actuals
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_fcpActuals.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this fcp actuals.
	*
	* @param primaryKey the primary key of this fcp actuals
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.FcpActualsPK primaryKey) {
		_fcpActuals.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fcpActuals.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FcpActuals> toCacheModel() {
		return _fcpActuals.toCacheModel();
	}

	@Override
	public FcpActuals toEscapedModel() {
		return new FcpActualsWrapper(_fcpActuals.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _fcpActuals.toString();
	}

	@Override
	public FcpActuals toUnescapedModel() {
		return new FcpActualsWrapper(_fcpActuals.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _fcpActuals.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FcpActualsWrapper)) {
			return false;
		}

		FcpActualsWrapper fcpActualsWrapper = (FcpActualsWrapper)obj;

		if (Objects.equals(_fcpActuals, fcpActualsWrapper._fcpActuals)) {
			return true;
		}

		return false;
	}

	@Override
	public FcpActuals getWrappedModel() {
		return _fcpActuals;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _fcpActuals.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _fcpActuals.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_fcpActuals.resetOriginalValues();
	}

	private final FcpActuals _fcpActuals;
}