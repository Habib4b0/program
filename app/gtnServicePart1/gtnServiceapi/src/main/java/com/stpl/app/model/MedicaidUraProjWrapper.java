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
 * This class is a wrapper for {@link MedicaidUraProj}.
 * </p>
 *
 * @author
 * @see MedicaidUraProj
 * @generated
 */
@ProviderType
public class MedicaidUraProjWrapper implements MedicaidUraProj,
	ModelWrapper<MedicaidUraProj> {
	public MedicaidUraProjWrapper(MedicaidUraProj medicaidUraProj) {
		_medicaidUraProj = medicaidUraProj;
	}

	@Override
	public Class<?> getModelClass() {
		return MedicaidUraProj.class;
	}

	@Override
	public String getModelClassName() {
		return MedicaidUraProj.class.getName();
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
		return new MedicaidUraProjWrapper((MedicaidUraProj)_medicaidUraProj.clone());
	}

	@Override
	public int compareTo(MedicaidUraProj medicaidUraProj) {
		return _medicaidUraProj.compareTo(medicaidUraProj);
	}

	/**
	* Returns the adjustment of this medicaid ura proj.
	*
	* @return the adjustment of this medicaid ura proj
	*/
	@Override
	public double getAdjustment() {
		return _medicaidUraProj.getAdjustment();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _medicaidUraProj.getExpandoBridge();
	}

	/**
	* Returns the na proj details sid of this medicaid ura proj.
	*
	* @return the na proj details sid of this medicaid ura proj
	*/
	@Override
	public int getNaProjDetailsSid() {
		return _medicaidUraProj.getNaProjDetailsSid();
	}

	/**
	* Returns the notes of this medicaid ura proj.
	*
	* @return the notes of this medicaid ura proj
	*/
	@Override
	public java.lang.String getNotes() {
		return _medicaidUraProj.getNotes();
	}

	/**
	* Returns the period sid of this medicaid ura proj.
	*
	* @return the period sid of this medicaid ura proj
	*/
	@Override
	public int getPeriodSid() {
		return _medicaidUraProj.getPeriodSid();
	}

	/**
	* Returns the price type of this medicaid ura proj.
	*
	* @return the price type of this medicaid ura proj
	*/
	@Override
	public java.lang.String getPriceType() {
		return _medicaidUraProj.getPriceType();
	}

	/**
	* Returns the primary key of this medicaid ura proj.
	*
	* @return the primary key of this medicaid ura proj
	*/
	@Override
	public com.stpl.app.service.persistence.MedicaidUraProjPK getPrimaryKey() {
		return _medicaidUraProj.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _medicaidUraProj.getPrimaryKeyObj();
	}

	/**
	* Returns the projection price of this medicaid ura proj.
	*
	* @return the projection price of this medicaid ura proj
	*/
	@Override
	public double getProjectionPrice() {
		return _medicaidUraProj.getProjectionPrice();
	}

	@Override
	public int hashCode() {
		return _medicaidUraProj.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _medicaidUraProj.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _medicaidUraProj.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _medicaidUraProj.isNew();
	}

	@Override
	public void persist() {
		_medicaidUraProj.persist();
	}

	/**
	* Sets the adjustment of this medicaid ura proj.
	*
	* @param adjustment the adjustment of this medicaid ura proj
	*/
	@Override
	public void setAdjustment(double adjustment) {
		_medicaidUraProj.setAdjustment(adjustment);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_medicaidUraProj.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_medicaidUraProj.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_medicaidUraProj.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_medicaidUraProj.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the na proj details sid of this medicaid ura proj.
	*
	* @param naProjDetailsSid the na proj details sid of this medicaid ura proj
	*/
	@Override
	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_medicaidUraProj.setNaProjDetailsSid(naProjDetailsSid);
	}

	@Override
	public void setNew(boolean n) {
		_medicaidUraProj.setNew(n);
	}

	/**
	* Sets the notes of this medicaid ura proj.
	*
	* @param notes the notes of this medicaid ura proj
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_medicaidUraProj.setNotes(notes);
	}

	/**
	* Sets the period sid of this medicaid ura proj.
	*
	* @param periodSid the period sid of this medicaid ura proj
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_medicaidUraProj.setPeriodSid(periodSid);
	}

	/**
	* Sets the price type of this medicaid ura proj.
	*
	* @param priceType the price type of this medicaid ura proj
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_medicaidUraProj.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this medicaid ura proj.
	*
	* @param primaryKey the primary key of this medicaid ura proj
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.MedicaidUraProjPK primaryKey) {
		_medicaidUraProj.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_medicaidUraProj.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection price of this medicaid ura proj.
	*
	* @param projectionPrice the projection price of this medicaid ura proj
	*/
	@Override
	public void setProjectionPrice(double projectionPrice) {
		_medicaidUraProj.setProjectionPrice(projectionPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MedicaidUraProj> toCacheModel() {
		return _medicaidUraProj.toCacheModel();
	}

	@Override
	public MedicaidUraProj toEscapedModel() {
		return new MedicaidUraProjWrapper(_medicaidUraProj.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _medicaidUraProj.toString();
	}

	@Override
	public MedicaidUraProj toUnescapedModel() {
		return new MedicaidUraProjWrapper(_medicaidUraProj.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _medicaidUraProj.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MedicaidUraProjWrapper)) {
			return false;
		}

		MedicaidUraProjWrapper medicaidUraProjWrapper = (MedicaidUraProjWrapper)obj;

		if (Objects.equals(_medicaidUraProj,
					medicaidUraProjWrapper._medicaidUraProj)) {
			return true;
		}

		return false;
	}

	@Override
	public MedicaidUraProj getWrappedModel() {
		return _medicaidUraProj;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _medicaidUraProj.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _medicaidUraProj.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_medicaidUraProj.resetOriginalValues();
	}

	private final MedicaidUraProj _medicaidUraProj;
}