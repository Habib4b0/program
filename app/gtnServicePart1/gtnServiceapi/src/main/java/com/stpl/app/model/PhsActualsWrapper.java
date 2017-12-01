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
 * This class is a wrapper for {@link PhsActuals}.
 * </p>
 *
 * @author
 * @see PhsActuals
 * @generated
 */
@ProviderType
public class PhsActualsWrapper implements PhsActuals, ModelWrapper<PhsActuals> {
	public PhsActualsWrapper(PhsActuals phsActuals) {
		_phsActuals = phsActuals;
	}

	@Override
	public Class<?> getModelClass() {
		return PhsActuals.class;
	}

	@Override
	public String getModelClassName() {
		return PhsActuals.class.getName();
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
		return new PhsActualsWrapper((PhsActuals)_phsActuals.clone());
	}

	@Override
	public int compareTo(PhsActuals phsActuals) {
		return _phsActuals.compareTo(phsActuals);
	}

	/**
	* Returns the actual price of this phs actuals.
	*
	* @return the actual price of this phs actuals
	*/
	@Override
	public double getActualPrice() {
		return _phsActuals.getActualPrice();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _phsActuals.getExpandoBridge();
	}

	/**
	* Returns the na proj details sid of this phs actuals.
	*
	* @return the na proj details sid of this phs actuals
	*/
	@Override
	public int getNaProjDetailsSid() {
		return _phsActuals.getNaProjDetailsSid();
	}

	/**
	* Returns the notes of this phs actuals.
	*
	* @return the notes of this phs actuals
	*/
	@Override
	public java.lang.String getNotes() {
		return _phsActuals.getNotes();
	}

	/**
	* Returns the period sid of this phs actuals.
	*
	* @return the period sid of this phs actuals
	*/
	@Override
	public int getPeriodSid() {
		return _phsActuals.getPeriodSid();
	}

	/**
	* Returns the price type of this phs actuals.
	*
	* @return the price type of this phs actuals
	*/
	@Override
	public java.lang.String getPriceType() {
		return _phsActuals.getPriceType();
	}

	/**
	* Returns the primary key of this phs actuals.
	*
	* @return the primary key of this phs actuals
	*/
	@Override
	public com.stpl.app.service.persistence.PhsActualsPK getPrimaryKey() {
		return _phsActuals.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _phsActuals.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _phsActuals.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _phsActuals.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _phsActuals.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _phsActuals.isNew();
	}

	@Override
	public void persist() {
		_phsActuals.persist();
	}

	/**
	* Sets the actual price of this phs actuals.
	*
	* @param actualPrice the actual price of this phs actuals
	*/
	@Override
	public void setActualPrice(double actualPrice) {
		_phsActuals.setActualPrice(actualPrice);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_phsActuals.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_phsActuals.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_phsActuals.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_phsActuals.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the na proj details sid of this phs actuals.
	*
	* @param naProjDetailsSid the na proj details sid of this phs actuals
	*/
	@Override
	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_phsActuals.setNaProjDetailsSid(naProjDetailsSid);
	}

	@Override
	public void setNew(boolean n) {
		_phsActuals.setNew(n);
	}

	/**
	* Sets the notes of this phs actuals.
	*
	* @param notes the notes of this phs actuals
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_phsActuals.setNotes(notes);
	}

	/**
	* Sets the period sid of this phs actuals.
	*
	* @param periodSid the period sid of this phs actuals
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_phsActuals.setPeriodSid(periodSid);
	}

	/**
	* Sets the price type of this phs actuals.
	*
	* @param priceType the price type of this phs actuals
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_phsActuals.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this phs actuals.
	*
	* @param primaryKey the primary key of this phs actuals
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.PhsActualsPK primaryKey) {
		_phsActuals.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_phsActuals.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PhsActuals> toCacheModel() {
		return _phsActuals.toCacheModel();
	}

	@Override
	public PhsActuals toEscapedModel() {
		return new PhsActualsWrapper(_phsActuals.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _phsActuals.toString();
	}

	@Override
	public PhsActuals toUnescapedModel() {
		return new PhsActualsWrapper(_phsActuals.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _phsActuals.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PhsActualsWrapper)) {
			return false;
		}

		PhsActualsWrapper phsActualsWrapper = (PhsActualsWrapper)obj;

		if (Objects.equals(_phsActuals, phsActualsWrapper._phsActuals)) {
			return true;
		}

		return false;
	}

	@Override
	public PhsActuals getWrappedModel() {
		return _phsActuals;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _phsActuals.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _phsActuals.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_phsActuals.resetOriginalValues();
	}

	private final PhsActuals _phsActuals;
}