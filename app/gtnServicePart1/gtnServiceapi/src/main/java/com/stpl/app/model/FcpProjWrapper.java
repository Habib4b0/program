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
 * This class is a wrapper for {@link FcpProj}.
 * </p>
 *
 * @author
 * @see FcpProj
 * @generated
 */
@ProviderType
public class FcpProjWrapper implements FcpProj, ModelWrapper<FcpProj> {
	public FcpProjWrapper(FcpProj fcpProj) {
		_fcpProj = fcpProj;
	}

	@Override
	public Class<?> getModelClass() {
		return FcpProj.class;
	}

	@Override
	public String getModelClassName() {
		return FcpProj.class.getName();
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
		return new FcpProjWrapper((FcpProj)_fcpProj.clone());
	}

	@Override
	public int compareTo(FcpProj fcpProj) {
		return _fcpProj.compareTo(fcpProj);
	}

	/**
	* Returns the adjustment of this fcp proj.
	*
	* @return the adjustment of this fcp proj
	*/
	@Override
	public double getAdjustment() {
		return _fcpProj.getAdjustment();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fcpProj.getExpandoBridge();
	}

	/**
	* Returns the na proj details sid of this fcp proj.
	*
	* @return the na proj details sid of this fcp proj
	*/
	@Override
	public int getNaProjDetailsSid() {
		return _fcpProj.getNaProjDetailsSid();
	}

	/**
	* Returns the notes of this fcp proj.
	*
	* @return the notes of this fcp proj
	*/
	@Override
	public java.lang.String getNotes() {
		return _fcpProj.getNotes();
	}

	/**
	* Returns the period sid of this fcp proj.
	*
	* @return the period sid of this fcp proj
	*/
	@Override
	public int getPeriodSid() {
		return _fcpProj.getPeriodSid();
	}

	/**
	* Returns the price type of this fcp proj.
	*
	* @return the price type of this fcp proj
	*/
	@Override
	public java.lang.String getPriceType() {
		return _fcpProj.getPriceType();
	}

	/**
	* Returns the primary key of this fcp proj.
	*
	* @return the primary key of this fcp proj
	*/
	@Override
	public com.stpl.app.service.persistence.FcpProjPK getPrimaryKey() {
		return _fcpProj.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fcpProj.getPrimaryKeyObj();
	}

	/**
	* Returns the projection price of this fcp proj.
	*
	* @return the projection price of this fcp proj
	*/
	@Override
	public double getProjectionPrice() {
		return _fcpProj.getProjectionPrice();
	}

	@Override
	public int hashCode() {
		return _fcpProj.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _fcpProj.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _fcpProj.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _fcpProj.isNew();
	}

	@Override
	public void persist() {
		_fcpProj.persist();
	}

	/**
	* Sets the adjustment of this fcp proj.
	*
	* @param adjustment the adjustment of this fcp proj
	*/
	@Override
	public void setAdjustment(double adjustment) {
		_fcpProj.setAdjustment(adjustment);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fcpProj.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_fcpProj.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_fcpProj.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_fcpProj.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the na proj details sid of this fcp proj.
	*
	* @param naProjDetailsSid the na proj details sid of this fcp proj
	*/
	@Override
	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_fcpProj.setNaProjDetailsSid(naProjDetailsSid);
	}

	@Override
	public void setNew(boolean n) {
		_fcpProj.setNew(n);
	}

	/**
	* Sets the notes of this fcp proj.
	*
	* @param notes the notes of this fcp proj
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_fcpProj.setNotes(notes);
	}

	/**
	* Sets the period sid of this fcp proj.
	*
	* @param periodSid the period sid of this fcp proj
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_fcpProj.setPeriodSid(periodSid);
	}

	/**
	* Sets the price type of this fcp proj.
	*
	* @param priceType the price type of this fcp proj
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_fcpProj.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this fcp proj.
	*
	* @param primaryKey the primary key of this fcp proj
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.FcpProjPK primaryKey) {
		_fcpProj.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fcpProj.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection price of this fcp proj.
	*
	* @param projectionPrice the projection price of this fcp proj
	*/
	@Override
	public void setProjectionPrice(double projectionPrice) {
		_fcpProj.setProjectionPrice(projectionPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FcpProj> toCacheModel() {
		return _fcpProj.toCacheModel();
	}

	@Override
	public FcpProj toEscapedModel() {
		return new FcpProjWrapper(_fcpProj.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _fcpProj.toString();
	}

	@Override
	public FcpProj toUnescapedModel() {
		return new FcpProjWrapper(_fcpProj.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _fcpProj.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FcpProjWrapper)) {
			return false;
		}

		FcpProjWrapper fcpProjWrapper = (FcpProjWrapper)obj;

		if (Objects.equals(_fcpProj, fcpProjWrapper._fcpProj)) {
			return true;
		}

		return false;
	}

	@Override
	public FcpProj getWrappedModel() {
		return _fcpProj;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _fcpProj.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _fcpProj.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_fcpProj.resetOriginalValues();
	}

	private final FcpProj _fcpProj;
}