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
 * This class is a wrapper for {@link ChActualSales}.
 * </p>
 *
 * @author
 * @see ChActualSales
 * @generated
 */
@ProviderType
public class ChActualSalesWrapper implements ChActualSales,
	ModelWrapper<ChActualSales> {
	public ChActualSalesWrapper(ChActualSales chActualSales) {
		_chActualSales = chActualSales;
	}

	@Override
	public Class<?> getModelClass() {
		return ChActualSales.class;
	}

	@Override
	public String getModelClassName() {
		return ChActualSales.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractUnits", getContractUnits());
		attributes.put("perOfBusiness", getPerOfBusiness());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("contractSales", getContractSales());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("gtsSales", getGtsSales());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double contractUnits = (Double)attributes.get("contractUnits");

		if (contractUnits != null) {
			setContractUnits(contractUnits);
		}

		Double perOfBusiness = (Double)attributes.get("perOfBusiness");

		if (perOfBusiness != null) {
			setPerOfBusiness(perOfBusiness);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Double contractSales = (Double)attributes.get("contractSales");

		if (contractSales != null) {
			setContractSales(contractSales);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double gtsSales = (Double)attributes.get("gtsSales");

		if (gtsSales != null) {
			setGtsSales(gtsSales);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ChActualSalesWrapper((ChActualSales)_chActualSales.clone());
	}

	@Override
	public int compareTo(ChActualSales chActualSales) {
		return _chActualSales.compareTo(chActualSales);
	}

	/**
	* Returns the contract sales of this ch actual sales.
	*
	* @return the contract sales of this ch actual sales
	*/
	@Override
	public double getContractSales() {
		return _chActualSales.getContractSales();
	}

	/**
	* Returns the contract units of this ch actual sales.
	*
	* @return the contract units of this ch actual sales
	*/
	@Override
	public double getContractUnits() {
		return _chActualSales.getContractUnits();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _chActualSales.getExpandoBridge();
	}

	/**
	* Returns the gts sales of this ch actual sales.
	*
	* @return the gts sales of this ch actual sales
	*/
	@Override
	public double getGtsSales() {
		return _chActualSales.getGtsSales();
	}

	/**
	* Returns the period sid of this ch actual sales.
	*
	* @return the period sid of this ch actual sales
	*/
	@Override
	public int getPeriodSid() {
		return _chActualSales.getPeriodSid();
	}

	/**
	* Returns the per of business of this ch actual sales.
	*
	* @return the per of business of this ch actual sales
	*/
	@Override
	public double getPerOfBusiness() {
		return _chActualSales.getPerOfBusiness();
	}

	/**
	* Returns the primary key of this ch actual sales.
	*
	* @return the primary key of this ch actual sales
	*/
	@Override
	public com.stpl.app.service.persistence.ChActualSalesPK getPrimaryKey() {
		return _chActualSales.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chActualSales.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this ch actual sales.
	*
	* @return the projection details sid of this ch actual sales
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _chActualSales.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _chActualSales.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _chActualSales.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _chActualSales.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _chActualSales.isNew();
	}

	@Override
	public void persist() {
		_chActualSales.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_chActualSales.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract sales of this ch actual sales.
	*
	* @param contractSales the contract sales of this ch actual sales
	*/
	@Override
	public void setContractSales(double contractSales) {
		_chActualSales.setContractSales(contractSales);
	}

	/**
	* Sets the contract units of this ch actual sales.
	*
	* @param contractUnits the contract units of this ch actual sales
	*/
	@Override
	public void setContractUnits(double contractUnits) {
		_chActualSales.setContractUnits(contractUnits);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_chActualSales.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_chActualSales.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_chActualSales.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gts sales of this ch actual sales.
	*
	* @param gtsSales the gts sales of this ch actual sales
	*/
	@Override
	public void setGtsSales(double gtsSales) {
		_chActualSales.setGtsSales(gtsSales);
	}

	@Override
	public void setNew(boolean n) {
		_chActualSales.setNew(n);
	}

	/**
	* Sets the period sid of this ch actual sales.
	*
	* @param periodSid the period sid of this ch actual sales
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_chActualSales.setPeriodSid(periodSid);
	}

	/**
	* Sets the per of business of this ch actual sales.
	*
	* @param perOfBusiness the per of business of this ch actual sales
	*/
	@Override
	public void setPerOfBusiness(double perOfBusiness) {
		_chActualSales.setPerOfBusiness(perOfBusiness);
	}

	/**
	* Sets the primary key of this ch actual sales.
	*
	* @param primaryKey the primary key of this ch actual sales
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.ChActualSalesPK primaryKey) {
		_chActualSales.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_chActualSales.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this ch actual sales.
	*
	* @param projectionDetailsSid the projection details sid of this ch actual sales
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_chActualSales.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ChActualSales> toCacheModel() {
		return _chActualSales.toCacheModel();
	}

	@Override
	public ChActualSales toEscapedModel() {
		return new ChActualSalesWrapper(_chActualSales.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _chActualSales.toString();
	}

	@Override
	public ChActualSales toUnescapedModel() {
		return new ChActualSalesWrapper(_chActualSales.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _chActualSales.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChActualSalesWrapper)) {
			return false;
		}

		ChActualSalesWrapper chActualSalesWrapper = (ChActualSalesWrapper)obj;

		if (Objects.equals(_chActualSales, chActualSalesWrapper._chActualSales)) {
			return true;
		}

		return false;
	}

	@Override
	public ChActualSales getWrappedModel() {
		return _chActualSales;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _chActualSales.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _chActualSales.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_chActualSales.resetOriginalValues();
	}

	private final ChActualSales _chActualSales;
}