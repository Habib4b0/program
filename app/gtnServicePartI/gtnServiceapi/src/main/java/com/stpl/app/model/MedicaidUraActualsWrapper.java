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
 * This class is a wrapper for {@link MedicaidUraActuals}.
 * </p>
 *
 * @author
 * @see MedicaidUraActuals
 * @generated
 */
@ProviderType
public class MedicaidUraActualsWrapper implements MedicaidUraActuals,
	ModelWrapper<MedicaidUraActuals> {
	public MedicaidUraActualsWrapper(MedicaidUraActuals medicaidUraActuals) {
		_medicaidUraActuals = medicaidUraActuals;
	}

	@Override
	public Class<?> getModelClass() {
		return MedicaidUraActuals.class;
	}

	@Override
	public String getModelClassName() {
		return MedicaidUraActuals.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodSid", getPeriodSid());
		attributes.put("priceType", getPriceType());
		attributes.put("actualPrice", getActualPrice());
		attributes.put("notes", getNotes());
		attributes.put("naProjDetailsSid", getNaProjDetailsSid());
		attributes.put("baseYear", getBaseYear());

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

		Double baseYear = (Double)attributes.get("baseYear");

		if (baseYear != null) {
			setBaseYear(baseYear);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MedicaidUraActualsWrapper((MedicaidUraActuals)_medicaidUraActuals.clone());
	}

	@Override
	public int compareTo(MedicaidUraActuals medicaidUraActuals) {
		return _medicaidUraActuals.compareTo(medicaidUraActuals);
	}

	/**
	* Returns the actual price of this medicaid ura actuals.
	*
	* @return the actual price of this medicaid ura actuals
	*/
	@Override
	public double getActualPrice() {
		return _medicaidUraActuals.getActualPrice();
	}

	/**
	* Returns the base year of this medicaid ura actuals.
	*
	* @return the base year of this medicaid ura actuals
	*/
	@Override
	public double getBaseYear() {
		return _medicaidUraActuals.getBaseYear();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _medicaidUraActuals.getExpandoBridge();
	}

	/**
	* Returns the na proj details sid of this medicaid ura actuals.
	*
	* @return the na proj details sid of this medicaid ura actuals
	*/
	@Override
	public int getNaProjDetailsSid() {
		return _medicaidUraActuals.getNaProjDetailsSid();
	}

	/**
	* Returns the notes of this medicaid ura actuals.
	*
	* @return the notes of this medicaid ura actuals
	*/
	@Override
	public java.lang.String getNotes() {
		return _medicaidUraActuals.getNotes();
	}

	/**
	* Returns the period sid of this medicaid ura actuals.
	*
	* @return the period sid of this medicaid ura actuals
	*/
	@Override
	public int getPeriodSid() {
		return _medicaidUraActuals.getPeriodSid();
	}

	/**
	* Returns the price type of this medicaid ura actuals.
	*
	* @return the price type of this medicaid ura actuals
	*/
	@Override
	public java.lang.String getPriceType() {
		return _medicaidUraActuals.getPriceType();
	}

	/**
	* Returns the primary key of this medicaid ura actuals.
	*
	* @return the primary key of this medicaid ura actuals
	*/
	@Override
	public com.stpl.app.service.persistence.MedicaidUraActualsPK getPrimaryKey() {
		return _medicaidUraActuals.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _medicaidUraActuals.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _medicaidUraActuals.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _medicaidUraActuals.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _medicaidUraActuals.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _medicaidUraActuals.isNew();
	}

	@Override
	public void persist() {
		_medicaidUraActuals.persist();
	}

	/**
	* Sets the actual price of this medicaid ura actuals.
	*
	* @param actualPrice the actual price of this medicaid ura actuals
	*/
	@Override
	public void setActualPrice(double actualPrice) {
		_medicaidUraActuals.setActualPrice(actualPrice);
	}

	/**
	* Sets the base year of this medicaid ura actuals.
	*
	* @param baseYear the base year of this medicaid ura actuals
	*/
	@Override
	public void setBaseYear(double baseYear) {
		_medicaidUraActuals.setBaseYear(baseYear);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_medicaidUraActuals.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_medicaidUraActuals.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_medicaidUraActuals.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_medicaidUraActuals.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the na proj details sid of this medicaid ura actuals.
	*
	* @param naProjDetailsSid the na proj details sid of this medicaid ura actuals
	*/
	@Override
	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_medicaidUraActuals.setNaProjDetailsSid(naProjDetailsSid);
	}

	@Override
	public void setNew(boolean n) {
		_medicaidUraActuals.setNew(n);
	}

	/**
	* Sets the notes of this medicaid ura actuals.
	*
	* @param notes the notes of this medicaid ura actuals
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_medicaidUraActuals.setNotes(notes);
	}

	/**
	* Sets the period sid of this medicaid ura actuals.
	*
	* @param periodSid the period sid of this medicaid ura actuals
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_medicaidUraActuals.setPeriodSid(periodSid);
	}

	/**
	* Sets the price type of this medicaid ura actuals.
	*
	* @param priceType the price type of this medicaid ura actuals
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_medicaidUraActuals.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this medicaid ura actuals.
	*
	* @param primaryKey the primary key of this medicaid ura actuals
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.MedicaidUraActualsPK primaryKey) {
		_medicaidUraActuals.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_medicaidUraActuals.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MedicaidUraActuals> toCacheModel() {
		return _medicaidUraActuals.toCacheModel();
	}

	@Override
	public MedicaidUraActuals toEscapedModel() {
		return new MedicaidUraActualsWrapper(_medicaidUraActuals.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _medicaidUraActuals.toString();
	}

	@Override
	public MedicaidUraActuals toUnescapedModel() {
		return new MedicaidUraActualsWrapper(_medicaidUraActuals.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _medicaidUraActuals.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MedicaidUraActualsWrapper)) {
			return false;
		}

		MedicaidUraActualsWrapper medicaidUraActualsWrapper = (MedicaidUraActualsWrapper)obj;

		if (Objects.equals(_medicaidUraActuals,
					medicaidUraActualsWrapper._medicaidUraActuals)) {
			return true;
		}

		return false;
	}

	@Override
	public MedicaidUraActuals getWrappedModel() {
		return _medicaidUraActuals;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _medicaidUraActuals.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _medicaidUraActuals.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_medicaidUraActuals.resetOriginalValues();
	}

	private final MedicaidUraActuals _medicaidUraActuals;
}