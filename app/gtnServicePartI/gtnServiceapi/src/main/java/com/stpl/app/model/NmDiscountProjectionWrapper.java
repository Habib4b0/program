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
 * This class is a wrapper for {@link NmDiscountProjection}.
 * </p>
 *
 * @author
 * @see NmDiscountProjection
 * @generated
 */
@ProviderType
public class NmDiscountProjectionWrapper implements NmDiscountProjection,
	ModelWrapper<NmDiscountProjection> {
	public NmDiscountProjectionWrapper(
		NmDiscountProjection nmDiscountProjection) {
		_nmDiscountProjection = nmDiscountProjection;
	}

	@Override
	public Class<?> getModelClass() {
		return NmDiscountProjection.class;
	}

	@Override
	public String getModelClassName() {
		return NmDiscountProjection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodSid", getPeriodSid());
		attributes.put("projectionRate", getProjectionRate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("projectionSales", getProjectionSales());

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

		Double projectionSales = (Double)attributes.get("projectionSales");

		if (projectionSales != null) {
			setProjectionSales(projectionSales);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NmDiscountProjectionWrapper((NmDiscountProjection)_nmDiscountProjection.clone());
	}

	@Override
	public int compareTo(NmDiscountProjection nmDiscountProjection) {
		return _nmDiscountProjection.compareTo(nmDiscountProjection);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmDiscountProjection.getExpandoBridge();
	}

	/**
	* Returns the period sid of this nm discount projection.
	*
	* @return the period sid of this nm discount projection
	*/
	@Override
	public int getPeriodSid() {
		return _nmDiscountProjection.getPeriodSid();
	}

	/**
	* Returns the primary key of this nm discount projection.
	*
	* @return the primary key of this nm discount projection
	*/
	@Override
	public com.stpl.app.service.persistence.NmDiscountProjectionPK getPrimaryKey() {
		return _nmDiscountProjection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmDiscountProjection.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this nm discount projection.
	*
	* @return the projection details sid of this nm discount projection
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _nmDiscountProjection.getProjectionDetailsSid();
	}

	/**
	* Returns the projection rate of this nm discount projection.
	*
	* @return the projection rate of this nm discount projection
	*/
	@Override
	public double getProjectionRate() {
		return _nmDiscountProjection.getProjectionRate();
	}

	/**
	* Returns the projection sales of this nm discount projection.
	*
	* @return the projection sales of this nm discount projection
	*/
	@Override
	public double getProjectionSales() {
		return _nmDiscountProjection.getProjectionSales();
	}

	@Override
	public int hashCode() {
		return _nmDiscountProjection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmDiscountProjection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmDiscountProjection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmDiscountProjection.isNew();
	}

	@Override
	public void persist() {
		_nmDiscountProjection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmDiscountProjection.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmDiscountProjection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmDiscountProjection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmDiscountProjection.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_nmDiscountProjection.setNew(n);
	}

	/**
	* Sets the period sid of this nm discount projection.
	*
	* @param periodSid the period sid of this nm discount projection
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_nmDiscountProjection.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this nm discount projection.
	*
	* @param primaryKey the primary key of this nm discount projection
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.NmDiscountProjectionPK primaryKey) {
		_nmDiscountProjection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmDiscountProjection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this nm discount projection.
	*
	* @param projectionDetailsSid the projection details sid of this nm discount projection
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_nmDiscountProjection.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection rate of this nm discount projection.
	*
	* @param projectionRate the projection rate of this nm discount projection
	*/
	@Override
	public void setProjectionRate(double projectionRate) {
		_nmDiscountProjection.setProjectionRate(projectionRate);
	}

	/**
	* Sets the projection sales of this nm discount projection.
	*
	* @param projectionSales the projection sales of this nm discount projection
	*/
	@Override
	public void setProjectionSales(double projectionSales) {
		_nmDiscountProjection.setProjectionSales(projectionSales);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmDiscountProjection> toCacheModel() {
		return _nmDiscountProjection.toCacheModel();
	}

	@Override
	public NmDiscountProjection toEscapedModel() {
		return new NmDiscountProjectionWrapper(_nmDiscountProjection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmDiscountProjection.toString();
	}

	@Override
	public NmDiscountProjection toUnescapedModel() {
		return new NmDiscountProjectionWrapper(_nmDiscountProjection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmDiscountProjection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmDiscountProjectionWrapper)) {
			return false;
		}

		NmDiscountProjectionWrapper nmDiscountProjectionWrapper = (NmDiscountProjectionWrapper)obj;

		if (Objects.equals(_nmDiscountProjection,
					nmDiscountProjectionWrapper._nmDiscountProjection)) {
			return true;
		}

		return false;
	}

	@Override
	public NmDiscountProjection getWrappedModel() {
		return _nmDiscountProjection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmDiscountProjection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmDiscountProjection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmDiscountProjection.resetOriginalValues();
	}

	private final NmDiscountProjection _nmDiscountProjection;
}