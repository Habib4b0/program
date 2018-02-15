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
 * This class is a wrapper for {@link NmPpaProjection}.
 * </p>
 *
 * @author
 * @see NmPpaProjection
 * @generated
 */
@ProviderType
public class NmPpaProjectionWrapper implements NmPpaProjection,
	ModelWrapper<NmPpaProjection> {
	public NmPpaProjectionWrapper(NmPpaProjection nmPpaProjection) {
		_nmPpaProjection = nmPpaProjection;
	}

	@Override
	public Class<?> getModelClass() {
		return NmPpaProjection.class;
	}

	@Override
	public String getModelClassName() {
		return NmPpaProjection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodSid", getPeriodSid());
		attributes.put("projectionRate", getProjectionRate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("priceCap", getPriceCap());
		attributes.put("projectionDiscountUnits", getProjectionDiscountUnits());
		attributes.put("projectionDiscountDollar", getProjectionDiscountDollar());
		attributes.put("reset", getReset());
		attributes.put("projectionSales", getProjectionSales());
		attributes.put("projectionMap", getProjectionMap());
		attributes.put("resetPriceCap", getResetPriceCap());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Double projectionRate = (Double)attributes.get("projectionRate");

		if (projectionRate != null) {
			setProjectionRate(projectionRate);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double priceCap = (Double)attributes.get("priceCap");

		if (priceCap != null) {
			setPriceCap(priceCap);
		}

		Double projectionDiscountUnits = (Double)attributes.get(
				"projectionDiscountUnits");

		if (projectionDiscountUnits != null) {
			setProjectionDiscountUnits(projectionDiscountUnits);
		}

		Double projectionDiscountDollar = (Double)attributes.get(
				"projectionDiscountDollar");

		if (projectionDiscountDollar != null) {
			setProjectionDiscountDollar(projectionDiscountDollar);
		}

		Boolean reset = (Boolean)attributes.get("reset");

		if (reset != null) {
			setReset(reset);
		}

		Double projectionSales = (Double)attributes.get("projectionSales");

		if (projectionSales != null) {
			setProjectionSales(projectionSales);
		}

		Double projectionMap = (Double)attributes.get("projectionMap");

		if (projectionMap != null) {
			setProjectionMap(projectionMap);
		}

		Boolean resetPriceCap = (Boolean)attributes.get("resetPriceCap");

		if (resetPriceCap != null) {
			setResetPriceCap(resetPriceCap);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NmPpaProjectionWrapper((NmPpaProjection)_nmPpaProjection.clone());
	}

	@Override
	public int compareTo(NmPpaProjection nmPpaProjection) {
		return _nmPpaProjection.compareTo(nmPpaProjection);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmPpaProjection.getExpandoBridge();
	}

	/**
	* Returns the period sid of this nm ppa projection.
	*
	* @return the period sid of this nm ppa projection
	*/
	@Override
	public int getPeriodSid() {
		return _nmPpaProjection.getPeriodSid();
	}

	/**
	* Returns the price cap of this nm ppa projection.
	*
	* @return the price cap of this nm ppa projection
	*/
	@Override
	public double getPriceCap() {
		return _nmPpaProjection.getPriceCap();
	}

	/**
	* Returns the primary key of this nm ppa projection.
	*
	* @return the primary key of this nm ppa projection
	*/
	@Override
	public com.stpl.app.service.persistence.NmPpaProjectionPK getPrimaryKey() {
		return _nmPpaProjection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmPpaProjection.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this nm ppa projection.
	*
	* @return the projection details sid of this nm ppa projection
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _nmPpaProjection.getProjectionDetailsSid();
	}

	/**
	* Returns the projection discount dollar of this nm ppa projection.
	*
	* @return the projection discount dollar of this nm ppa projection
	*/
	@Override
	public double getProjectionDiscountDollar() {
		return _nmPpaProjection.getProjectionDiscountDollar();
	}

	/**
	* Returns the projection discount units of this nm ppa projection.
	*
	* @return the projection discount units of this nm ppa projection
	*/
	@Override
	public double getProjectionDiscountUnits() {
		return _nmPpaProjection.getProjectionDiscountUnits();
	}

	/**
	* Returns the projection map of this nm ppa projection.
	*
	* @return the projection map of this nm ppa projection
	*/
	@Override
	public double getProjectionMap() {
		return _nmPpaProjection.getProjectionMap();
	}

	/**
	* Returns the projection rate of this nm ppa projection.
	*
	* @return the projection rate of this nm ppa projection
	*/
	@Override
	public double getProjectionRate() {
		return _nmPpaProjection.getProjectionRate();
	}

	/**
	* Returns the projection sales of this nm ppa projection.
	*
	* @return the projection sales of this nm ppa projection
	*/
	@Override
	public double getProjectionSales() {
		return _nmPpaProjection.getProjectionSales();
	}

	/**
	* Returns the reset of this nm ppa projection.
	*
	* @return the reset of this nm ppa projection
	*/
	@Override
	public boolean getReset() {
		return _nmPpaProjection.getReset();
	}

	/**
	* Returns the reset price cap of this nm ppa projection.
	*
	* @return the reset price cap of this nm ppa projection
	*/
	@Override
	public boolean getResetPriceCap() {
		return _nmPpaProjection.getResetPriceCap();
	}

	@Override
	public int hashCode() {
		return _nmPpaProjection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmPpaProjection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmPpaProjection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmPpaProjection.isNew();
	}

	/**
	* Returns <code>true</code> if this nm ppa projection is reset.
	*
	* @return <code>true</code> if this nm ppa projection is reset; <code>false</code> otherwise
	*/
	@Override
	public boolean isReset() {
		return _nmPpaProjection.isReset();
	}

	/**
	* Returns <code>true</code> if this nm ppa projection is reset price cap.
	*
	* @return <code>true</code> if this nm ppa projection is reset price cap; <code>false</code> otherwise
	*/
	@Override
	public boolean isResetPriceCap() {
		return _nmPpaProjection.isResetPriceCap();
	}

	@Override
	public void persist() {
		_nmPpaProjection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmPpaProjection.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmPpaProjection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmPpaProjection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmPpaProjection.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_nmPpaProjection.setNew(n);
	}

	/**
	* Sets the period sid of this nm ppa projection.
	*
	* @param periodSid the period sid of this nm ppa projection
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_nmPpaProjection.setPeriodSid(periodSid);
	}

	/**
	* Sets the price cap of this nm ppa projection.
	*
	* @param priceCap the price cap of this nm ppa projection
	*/
	@Override
	public void setPriceCap(double priceCap) {
		_nmPpaProjection.setPriceCap(priceCap);
	}

	/**
	* Sets the primary key of this nm ppa projection.
	*
	* @param primaryKey the primary key of this nm ppa projection
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.NmPpaProjectionPK primaryKey) {
		_nmPpaProjection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmPpaProjection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this nm ppa projection.
	*
	* @param projectionDetailsSid the projection details sid of this nm ppa projection
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_nmPpaProjection.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection discount dollar of this nm ppa projection.
	*
	* @param projectionDiscountDollar the projection discount dollar of this nm ppa projection
	*/
	@Override
	public void setProjectionDiscountDollar(double projectionDiscountDollar) {
		_nmPpaProjection.setProjectionDiscountDollar(projectionDiscountDollar);
	}

	/**
	* Sets the projection discount units of this nm ppa projection.
	*
	* @param projectionDiscountUnits the projection discount units of this nm ppa projection
	*/
	@Override
	public void setProjectionDiscountUnits(double projectionDiscountUnits) {
		_nmPpaProjection.setProjectionDiscountUnits(projectionDiscountUnits);
	}

	/**
	* Sets the projection map of this nm ppa projection.
	*
	* @param projectionMap the projection map of this nm ppa projection
	*/
	@Override
	public void setProjectionMap(double projectionMap) {
		_nmPpaProjection.setProjectionMap(projectionMap);
	}

	/**
	* Sets the projection rate of this nm ppa projection.
	*
	* @param projectionRate the projection rate of this nm ppa projection
	*/
	@Override
	public void setProjectionRate(double projectionRate) {
		_nmPpaProjection.setProjectionRate(projectionRate);
	}

	/**
	* Sets the projection sales of this nm ppa projection.
	*
	* @param projectionSales the projection sales of this nm ppa projection
	*/
	@Override
	public void setProjectionSales(double projectionSales) {
		_nmPpaProjection.setProjectionSales(projectionSales);
	}

	/**
	* Sets whether this nm ppa projection is reset.
	*
	* @param reset the reset of this nm ppa projection
	*/
	@Override
	public void setReset(boolean reset) {
		_nmPpaProjection.setReset(reset);
	}

	/**
	* Sets whether this nm ppa projection is reset price cap.
	*
	* @param resetPriceCap the reset price cap of this nm ppa projection
	*/
	@Override
	public void setResetPriceCap(boolean resetPriceCap) {
		_nmPpaProjection.setResetPriceCap(resetPriceCap);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmPpaProjection> toCacheModel() {
		return _nmPpaProjection.toCacheModel();
	}

	@Override
	public NmPpaProjection toEscapedModel() {
		return new NmPpaProjectionWrapper(_nmPpaProjection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmPpaProjection.toString();
	}

	@Override
	public NmPpaProjection toUnescapedModel() {
		return new NmPpaProjectionWrapper(_nmPpaProjection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmPpaProjection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmPpaProjectionWrapper)) {
			return false;
		}

		NmPpaProjectionWrapper nmPpaProjectionWrapper = (NmPpaProjectionWrapper)obj;

		if (Objects.equals(_nmPpaProjection,
					nmPpaProjectionWrapper._nmPpaProjection)) {
			return true;
		}

		return false;
	}

	@Override
	public NmPpaProjection getWrappedModel() {
		return _nmPpaProjection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmPpaProjection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmPpaProjection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmPpaProjection.resetOriginalValues();
	}

	private final NmPpaProjection _nmPpaProjection;
}