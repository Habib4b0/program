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
 * This class is a wrapper for {@link NmSalesProjection}.
 * </p>
 *
 * @author
 * @see NmSalesProjection
 * @generated
 */
@ProviderType
public class NmSalesProjectionWrapper implements NmSalesProjection,
	ModelWrapper<NmSalesProjection> {
	public NmSalesProjectionWrapper(NmSalesProjection nmSalesProjection) {
		_nmSalesProjection = nmSalesProjection;
	}

	@Override
	public Class<?> getModelClass() {
		return NmSalesProjection.class;
	}

	@Override
	public String getModelClassName() {
		return NmSalesProjection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodSid", getPeriodSid());
		attributes.put("productGrowth", getProductGrowth());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("accountGrowth", getAccountGrowth());
		attributes.put("projectionUnits", getProjectionUnits());
		attributes.put("projectionSales", getProjectionSales());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Double productGrowth = (Double)attributes.get("productGrowth");

		if (productGrowth != null) {
			setProductGrowth(productGrowth);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double accountGrowth = (Double)attributes.get("accountGrowth");

		if (accountGrowth != null) {
			setAccountGrowth(accountGrowth);
		}

		Double projectionUnits = (Double)attributes.get("projectionUnits");

		if (projectionUnits != null) {
			setProjectionUnits(projectionUnits);
		}

		Double projectionSales = (Double)attributes.get("projectionSales");

		if (projectionSales != null) {
			setProjectionSales(projectionSales);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NmSalesProjectionWrapper((NmSalesProjection)_nmSalesProjection.clone());
	}

	@Override
	public int compareTo(NmSalesProjection nmSalesProjection) {
		return _nmSalesProjection.compareTo(nmSalesProjection);
	}

	/**
	* Returns the account growth of this nm sales projection.
	*
	* @return the account growth of this nm sales projection
	*/
	@Override
	public double getAccountGrowth() {
		return _nmSalesProjection.getAccountGrowth();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmSalesProjection.getExpandoBridge();
	}

	/**
	* Returns the period sid of this nm sales projection.
	*
	* @return the period sid of this nm sales projection
	*/
	@Override
	public int getPeriodSid() {
		return _nmSalesProjection.getPeriodSid();
	}

	/**
	* Returns the primary key of this nm sales projection.
	*
	* @return the primary key of this nm sales projection
	*/
	@Override
	public com.stpl.app.service.persistence.NmSalesProjectionPK getPrimaryKey() {
		return _nmSalesProjection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmSalesProjection.getPrimaryKeyObj();
	}

	/**
	* Returns the product growth of this nm sales projection.
	*
	* @return the product growth of this nm sales projection
	*/
	@Override
	public double getProductGrowth() {
		return _nmSalesProjection.getProductGrowth();
	}

	/**
	* Returns the projection details sid of this nm sales projection.
	*
	* @return the projection details sid of this nm sales projection
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _nmSalesProjection.getProjectionDetailsSid();
	}

	/**
	* Returns the projection sales of this nm sales projection.
	*
	* @return the projection sales of this nm sales projection
	*/
	@Override
	public double getProjectionSales() {
		return _nmSalesProjection.getProjectionSales();
	}

	/**
	* Returns the projection units of this nm sales projection.
	*
	* @return the projection units of this nm sales projection
	*/
	@Override
	public double getProjectionUnits() {
		return _nmSalesProjection.getProjectionUnits();
	}

	@Override
	public int hashCode() {
		return _nmSalesProjection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmSalesProjection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmSalesProjection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmSalesProjection.isNew();
	}

	@Override
	public void persist() {
		_nmSalesProjection.persist();
	}

	/**
	* Sets the account growth of this nm sales projection.
	*
	* @param accountGrowth the account growth of this nm sales projection
	*/
	@Override
	public void setAccountGrowth(double accountGrowth) {
		_nmSalesProjection.setAccountGrowth(accountGrowth);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmSalesProjection.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmSalesProjection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmSalesProjection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmSalesProjection.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_nmSalesProjection.setNew(n);
	}

	/**
	* Sets the period sid of this nm sales projection.
	*
	* @param periodSid the period sid of this nm sales projection
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_nmSalesProjection.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this nm sales projection.
	*
	* @param primaryKey the primary key of this nm sales projection
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.NmSalesProjectionPK primaryKey) {
		_nmSalesProjection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmSalesProjection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product growth of this nm sales projection.
	*
	* @param productGrowth the product growth of this nm sales projection
	*/
	@Override
	public void setProductGrowth(double productGrowth) {
		_nmSalesProjection.setProductGrowth(productGrowth);
	}

	/**
	* Sets the projection details sid of this nm sales projection.
	*
	* @param projectionDetailsSid the projection details sid of this nm sales projection
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_nmSalesProjection.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection sales of this nm sales projection.
	*
	* @param projectionSales the projection sales of this nm sales projection
	*/
	@Override
	public void setProjectionSales(double projectionSales) {
		_nmSalesProjection.setProjectionSales(projectionSales);
	}

	/**
	* Sets the projection units of this nm sales projection.
	*
	* @param projectionUnits the projection units of this nm sales projection
	*/
	@Override
	public void setProjectionUnits(double projectionUnits) {
		_nmSalesProjection.setProjectionUnits(projectionUnits);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmSalesProjection> toCacheModel() {
		return _nmSalesProjection.toCacheModel();
	}

	@Override
	public NmSalesProjection toEscapedModel() {
		return new NmSalesProjectionWrapper(_nmSalesProjection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmSalesProjection.toString();
	}

	@Override
	public NmSalesProjection toUnescapedModel() {
		return new NmSalesProjectionWrapper(_nmSalesProjection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmSalesProjection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmSalesProjectionWrapper)) {
			return false;
		}

		NmSalesProjectionWrapper nmSalesProjectionWrapper = (NmSalesProjectionWrapper)obj;

		if (Objects.equals(_nmSalesProjection,
					nmSalesProjectionWrapper._nmSalesProjection)) {
			return true;
		}

		return false;
	}

	@Override
	public NmSalesProjection getWrappedModel() {
		return _nmSalesProjection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmSalesProjection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmSalesProjection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmSalesProjection.resetOriginalValues();
	}

	private final NmSalesProjection _nmSalesProjection;
}