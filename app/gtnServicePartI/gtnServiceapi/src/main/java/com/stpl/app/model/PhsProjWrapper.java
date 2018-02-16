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
 * This class is a wrapper for {@link PhsProj}.
 * </p>
 *
 * @author
 * @see PhsProj
 * @generated
 */
@ProviderType
public class PhsProjWrapper implements PhsProj, ModelWrapper<PhsProj> {
	public PhsProjWrapper(PhsProj phsProj) {
		_phsProj = phsProj;
	}

	@Override
	public Class<?> getModelClass() {
		return PhsProj.class;
	}

	@Override
	public String getModelClassName() {
		return PhsProj.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("adjustment", getAdjustment());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("priceType", getPriceType());
		attributes.put("projectionPrice", getProjectionPrice());
		attributes.put("notes", getNotes());
		attributes.put("naProjDetailsSid", getNaProjDetailsSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double adjustment = (Double)attributes.get("adjustment");

		if (adjustment != null) {
			setAdjustment(adjustment);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		String priceType = (String)attributes.get("priceType");

		if (priceType != null) {
			setPriceType(priceType);
		}

		Double projectionPrice = (Double)attributes.get("projectionPrice");

		if (projectionPrice != null) {
			setProjectionPrice(projectionPrice);
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
		return new PhsProjWrapper((PhsProj)_phsProj.clone());
	}

	@Override
	public int compareTo(PhsProj phsProj) {
		return _phsProj.compareTo(phsProj);
	}

	/**
	* Returns the adjustment of this phs proj.
	*
	* @return the adjustment of this phs proj
	*/
	@Override
	public double getAdjustment() {
		return _phsProj.getAdjustment();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _phsProj.getExpandoBridge();
	}

	/**
	* Returns the na proj details sid of this phs proj.
	*
	* @return the na proj details sid of this phs proj
	*/
	@Override
	public int getNaProjDetailsSid() {
		return _phsProj.getNaProjDetailsSid();
	}

	/**
	* Returns the notes of this phs proj.
	*
	* @return the notes of this phs proj
	*/
	@Override
	public java.lang.String getNotes() {
		return _phsProj.getNotes();
	}

	/**
	* Returns the period sid of this phs proj.
	*
	* @return the period sid of this phs proj
	*/
	@Override
	public int getPeriodSid() {
		return _phsProj.getPeriodSid();
	}

	/**
	* Returns the price type of this phs proj.
	*
	* @return the price type of this phs proj
	*/
	@Override
	public java.lang.String getPriceType() {
		return _phsProj.getPriceType();
	}

	/**
	* Returns the primary key of this phs proj.
	*
	* @return the primary key of this phs proj
	*/
	@Override
	public com.stpl.app.service.persistence.PhsProjPK getPrimaryKey() {
		return _phsProj.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _phsProj.getPrimaryKeyObj();
	}

	/**
	* Returns the projection price of this phs proj.
	*
	* @return the projection price of this phs proj
	*/
	@Override
	public double getProjectionPrice() {
		return _phsProj.getProjectionPrice();
	}

	@Override
	public int hashCode() {
		return _phsProj.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _phsProj.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _phsProj.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _phsProj.isNew();
	}

	@Override
	public void persist() {
		_phsProj.persist();
	}

	/**
	* Sets the adjustment of this phs proj.
	*
	* @param adjustment the adjustment of this phs proj
	*/
	@Override
	public void setAdjustment(double adjustment) {
		_phsProj.setAdjustment(adjustment);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_phsProj.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_phsProj.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_phsProj.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_phsProj.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the na proj details sid of this phs proj.
	*
	* @param naProjDetailsSid the na proj details sid of this phs proj
	*/
	@Override
	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_phsProj.setNaProjDetailsSid(naProjDetailsSid);
	}

	@Override
	public void setNew(boolean n) {
		_phsProj.setNew(n);
	}

	/**
	* Sets the notes of this phs proj.
	*
	* @param notes the notes of this phs proj
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_phsProj.setNotes(notes);
	}

	/**
	* Sets the period sid of this phs proj.
	*
	* @param periodSid the period sid of this phs proj
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_phsProj.setPeriodSid(periodSid);
	}

	/**
	* Sets the price type of this phs proj.
	*
	* @param priceType the price type of this phs proj
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_phsProj.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this phs proj.
	*
	* @param primaryKey the primary key of this phs proj
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.PhsProjPK primaryKey) {
		_phsProj.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_phsProj.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection price of this phs proj.
	*
	* @param projectionPrice the projection price of this phs proj
	*/
	@Override
	public void setProjectionPrice(double projectionPrice) {
		_phsProj.setProjectionPrice(projectionPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PhsProj> toCacheModel() {
		return _phsProj.toCacheModel();
	}

	@Override
	public PhsProj toEscapedModel() {
		return new PhsProjWrapper(_phsProj.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _phsProj.toString();
	}

	@Override
	public PhsProj toUnescapedModel() {
		return new PhsProjWrapper(_phsProj.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _phsProj.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PhsProjWrapper)) {
			return false;
		}

		PhsProjWrapper phsProjWrapper = (PhsProjWrapper)obj;

		if (Objects.equals(_phsProj, phsProjWrapper._phsProj)) {
			return true;
		}

		return false;
	}

	@Override
	public PhsProj getWrappedModel() {
		return _phsProj;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _phsProj.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _phsProj.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_phsProj.resetOriginalValues();
	}

	private final PhsProj _phsProj;
}