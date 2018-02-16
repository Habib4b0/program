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
 * This class is a wrapper for {@link ChSalesProjection}.
 * </p>
 *
 * @author
 * @see ChSalesProjection
 * @generated
 */
@ProviderType
public class ChSalesProjectionWrapper implements ChSalesProjection,
	ModelWrapper<ChSalesProjection> {
	public ChSalesProjectionWrapper(ChSalesProjection chSalesProjection) {
		_chSalesProjection = chSalesProjection;
	}

	@Override
	public Class<?> getModelClass() {
		return ChSalesProjection.class;
	}

	@Override
	public String getModelClassName() {
		return ChSalesProjection.class.getName();
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
		return new ChSalesProjectionWrapper((ChSalesProjection)_chSalesProjection.clone());
	}

	@Override
	public int compareTo(ChSalesProjection chSalesProjection) {
		return _chSalesProjection.compareTo(chSalesProjection);
	}

	/**
	* Returns the contract sales of this ch sales projection.
	*
	* @return the contract sales of this ch sales projection
	*/
	@Override
	public double getContractSales() {
		return _chSalesProjection.getContractSales();
	}

	/**
	* Returns the contract units of this ch sales projection.
	*
	* @return the contract units of this ch sales projection
	*/
	@Override
	public double getContractUnits() {
		return _chSalesProjection.getContractUnits();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _chSalesProjection.getExpandoBridge();
	}

	/**
	* Returns the gts sales of this ch sales projection.
	*
	* @return the gts sales of this ch sales projection
	*/
	@Override
	public double getGtsSales() {
		return _chSalesProjection.getGtsSales();
	}

	/**
	* Returns the period sid of this ch sales projection.
	*
	* @return the period sid of this ch sales projection
	*/
	@Override
	public int getPeriodSid() {
		return _chSalesProjection.getPeriodSid();
	}

	/**
	* Returns the per of business of this ch sales projection.
	*
	* @return the per of business of this ch sales projection
	*/
	@Override
	public double getPerOfBusiness() {
		return _chSalesProjection.getPerOfBusiness();
	}

	/**
	* Returns the primary key of this ch sales projection.
	*
	* @return the primary key of this ch sales projection
	*/
	@Override
	public com.stpl.app.service.persistence.ChSalesProjectionPK getPrimaryKey() {
		return _chSalesProjection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chSalesProjection.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this ch sales projection.
	*
	* @return the projection details sid of this ch sales projection
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _chSalesProjection.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _chSalesProjection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _chSalesProjection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _chSalesProjection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _chSalesProjection.isNew();
	}

	@Override
	public void persist() {
		_chSalesProjection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_chSalesProjection.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract sales of this ch sales projection.
	*
	* @param contractSales the contract sales of this ch sales projection
	*/
	@Override
	public void setContractSales(double contractSales) {
		_chSalesProjection.setContractSales(contractSales);
	}

	/**
	* Sets the contract units of this ch sales projection.
	*
	* @param contractUnits the contract units of this ch sales projection
	*/
	@Override
	public void setContractUnits(double contractUnits) {
		_chSalesProjection.setContractUnits(contractUnits);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_chSalesProjection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_chSalesProjection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_chSalesProjection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gts sales of this ch sales projection.
	*
	* @param gtsSales the gts sales of this ch sales projection
	*/
	@Override
	public void setGtsSales(double gtsSales) {
		_chSalesProjection.setGtsSales(gtsSales);
	}

	@Override
	public void setNew(boolean n) {
		_chSalesProjection.setNew(n);
	}

	/**
	* Sets the period sid of this ch sales projection.
	*
	* @param periodSid the period sid of this ch sales projection
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_chSalesProjection.setPeriodSid(periodSid);
	}

	/**
	* Sets the per of business of this ch sales projection.
	*
	* @param perOfBusiness the per of business of this ch sales projection
	*/
	@Override
	public void setPerOfBusiness(double perOfBusiness) {
		_chSalesProjection.setPerOfBusiness(perOfBusiness);
	}

	/**
	* Sets the primary key of this ch sales projection.
	*
	* @param primaryKey the primary key of this ch sales projection
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.ChSalesProjectionPK primaryKey) {
		_chSalesProjection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_chSalesProjection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this ch sales projection.
	*
	* @param projectionDetailsSid the projection details sid of this ch sales projection
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_chSalesProjection.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ChSalesProjection> toCacheModel() {
		return _chSalesProjection.toCacheModel();
	}

	@Override
	public ChSalesProjection toEscapedModel() {
		return new ChSalesProjectionWrapper(_chSalesProjection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _chSalesProjection.toString();
	}

	@Override
	public ChSalesProjection toUnescapedModel() {
		return new ChSalesProjectionWrapper(_chSalesProjection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _chSalesProjection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChSalesProjectionWrapper)) {
			return false;
		}

		ChSalesProjectionWrapper chSalesProjectionWrapper = (ChSalesProjectionWrapper)obj;

		if (Objects.equals(_chSalesProjection,
					chSalesProjectionWrapper._chSalesProjection)) {
			return true;
		}

		return false;
	}

	@Override
	public ChSalesProjection getWrappedModel() {
		return _chSalesProjection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _chSalesProjection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _chSalesProjection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_chSalesProjection.resetOriginalValues();
	}

	private final ChSalesProjection _chSalesProjection;
}