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
 * This class is a wrapper for {@link NationalAssumptionsProj}.
 * </p>
 *
 * @author
 * @see NationalAssumptionsProj
 * @generated
 */
@ProviderType
public class NationalAssumptionsProjWrapper implements NationalAssumptionsProj,
	ModelWrapper<NationalAssumptionsProj> {
	public NationalAssumptionsProjWrapper(
		NationalAssumptionsProj nationalAssumptionsProj) {
		_nationalAssumptionsProj = nationalAssumptionsProj;
	}

	@Override
	public Class<?> getModelClass() {
		return NationalAssumptionsProj.class;
	}

	@Override
	public String getModelClassName() {
		return NationalAssumptionsProj.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodSid", getPeriodSid());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("priceType", getPriceType());
		attributes.put("projectionPrice", getProjectionPrice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String priceType = (String)attributes.get("priceType");

		if (priceType != null) {
			setPriceType(priceType);
		}

		Double projectionPrice = (Double)attributes.get("projectionPrice");

		if (projectionPrice != null) {
			setProjectionPrice(projectionPrice);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NationalAssumptionsProjWrapper((NationalAssumptionsProj)_nationalAssumptionsProj.clone());
	}

	@Override
	public int compareTo(NationalAssumptionsProj nationalAssumptionsProj) {
		return _nationalAssumptionsProj.compareTo(nationalAssumptionsProj);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nationalAssumptionsProj.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this national assumptions proj.
	*
	* @return the item master sid of this national assumptions proj
	*/
	@Override
	public int getItemMasterSid() {
		return _nationalAssumptionsProj.getItemMasterSid();
	}

	/**
	* Returns the period sid of this national assumptions proj.
	*
	* @return the period sid of this national assumptions proj
	*/
	@Override
	public int getPeriodSid() {
		return _nationalAssumptionsProj.getPeriodSid();
	}

	/**
	* Returns the price type of this national assumptions proj.
	*
	* @return the price type of this national assumptions proj
	*/
	@Override
	public java.lang.String getPriceType() {
		return _nationalAssumptionsProj.getPriceType();
	}

	/**
	* Returns the primary key of this national assumptions proj.
	*
	* @return the primary key of this national assumptions proj
	*/
	@Override
	public com.stpl.app.service.persistence.NationalAssumptionsProjPK getPrimaryKey() {
		return _nationalAssumptionsProj.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nationalAssumptionsProj.getPrimaryKeyObj();
	}

	/**
	* Returns the projection price of this national assumptions proj.
	*
	* @return the projection price of this national assumptions proj
	*/
	@Override
	public double getProjectionPrice() {
		return _nationalAssumptionsProj.getProjectionPrice();
	}

	@Override
	public int hashCode() {
		return _nationalAssumptionsProj.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nationalAssumptionsProj.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nationalAssumptionsProj.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nationalAssumptionsProj.isNew();
	}

	@Override
	public void persist() {
		_nationalAssumptionsProj.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nationalAssumptionsProj.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nationalAssumptionsProj.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nationalAssumptionsProj.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nationalAssumptionsProj.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this national assumptions proj.
	*
	* @param itemMasterSid the item master sid of this national assumptions proj
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_nationalAssumptionsProj.setItemMasterSid(itemMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_nationalAssumptionsProj.setNew(n);
	}

	/**
	* Sets the period sid of this national assumptions proj.
	*
	* @param periodSid the period sid of this national assumptions proj
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_nationalAssumptionsProj.setPeriodSid(periodSid);
	}

	/**
	* Sets the price type of this national assumptions proj.
	*
	* @param priceType the price type of this national assumptions proj
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_nationalAssumptionsProj.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this national assumptions proj.
	*
	* @param primaryKey the primary key of this national assumptions proj
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.NationalAssumptionsProjPK primaryKey) {
		_nationalAssumptionsProj.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nationalAssumptionsProj.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection price of this national assumptions proj.
	*
	* @param projectionPrice the projection price of this national assumptions proj
	*/
	@Override
	public void setProjectionPrice(double projectionPrice) {
		_nationalAssumptionsProj.setProjectionPrice(projectionPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NationalAssumptionsProj> toCacheModel() {
		return _nationalAssumptionsProj.toCacheModel();
	}

	@Override
	public NationalAssumptionsProj toEscapedModel() {
		return new NationalAssumptionsProjWrapper(_nationalAssumptionsProj.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nationalAssumptionsProj.toString();
	}

	@Override
	public NationalAssumptionsProj toUnescapedModel() {
		return new NationalAssumptionsProjWrapper(_nationalAssumptionsProj.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nationalAssumptionsProj.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NationalAssumptionsProjWrapper)) {
			return false;
		}

		NationalAssumptionsProjWrapper nationalAssumptionsProjWrapper = (NationalAssumptionsProjWrapper)obj;

		if (Objects.equals(_nationalAssumptionsProj,
					nationalAssumptionsProjWrapper._nationalAssumptionsProj)) {
			return true;
		}

		return false;
	}

	@Override
	public NationalAssumptionsProj getWrappedModel() {
		return _nationalAssumptionsProj;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nationalAssumptionsProj.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nationalAssumptionsProj.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nationalAssumptionsProj.resetOriginalValues();
	}

	private final NationalAssumptionsProj _nationalAssumptionsProj;
}