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
 * This class is a wrapper for {@link NationalAssumptionsActuals}.
 * </p>
 *
 * @author
 * @see NationalAssumptionsActuals
 * @generated
 */
@ProviderType
public class NationalAssumptionsActualsWrapper
	implements NationalAssumptionsActuals,
		ModelWrapper<NationalAssumptionsActuals> {
	public NationalAssumptionsActualsWrapper(
		NationalAssumptionsActuals nationalAssumptionsActuals) {
		_nationalAssumptionsActuals = nationalAssumptionsActuals;
	}

	@Override
	public Class<?> getModelClass() {
		return NationalAssumptionsActuals.class;
	}

	@Override
	public String getModelClassName() {
		return NationalAssumptionsActuals.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodSid", getPeriodSid());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("priceType", getPriceType());
		attributes.put("actualPrice", getActualPrice());

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

		Double actualPrice = (Double)attributes.get("actualPrice");

		if (actualPrice != null) {
			setActualPrice(actualPrice);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NationalAssumptionsActualsWrapper((NationalAssumptionsActuals)_nationalAssumptionsActuals.clone());
	}

	@Override
	public int compareTo(NationalAssumptionsActuals nationalAssumptionsActuals) {
		return _nationalAssumptionsActuals.compareTo(nationalAssumptionsActuals);
	}

	/**
	* Returns the actual price of this national assumptions actuals.
	*
	* @return the actual price of this national assumptions actuals
	*/
	@Override
	public double getActualPrice() {
		return _nationalAssumptionsActuals.getActualPrice();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nationalAssumptionsActuals.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this national assumptions actuals.
	*
	* @return the item master sid of this national assumptions actuals
	*/
	@Override
	public int getItemMasterSid() {
		return _nationalAssumptionsActuals.getItemMasterSid();
	}

	/**
	* Returns the period sid of this national assumptions actuals.
	*
	* @return the period sid of this national assumptions actuals
	*/
	@Override
	public int getPeriodSid() {
		return _nationalAssumptionsActuals.getPeriodSid();
	}

	/**
	* Returns the price type of this national assumptions actuals.
	*
	* @return the price type of this national assumptions actuals
	*/
	@Override
	public java.lang.String getPriceType() {
		return _nationalAssumptionsActuals.getPriceType();
	}

	/**
	* Returns the primary key of this national assumptions actuals.
	*
	* @return the primary key of this national assumptions actuals
	*/
	@Override
	public com.stpl.app.service.persistence.NationalAssumptionsActualsPK getPrimaryKey() {
		return _nationalAssumptionsActuals.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nationalAssumptionsActuals.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _nationalAssumptionsActuals.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nationalAssumptionsActuals.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nationalAssumptionsActuals.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nationalAssumptionsActuals.isNew();
	}

	@Override
	public void persist() {
		_nationalAssumptionsActuals.persist();
	}

	/**
	* Sets the actual price of this national assumptions actuals.
	*
	* @param actualPrice the actual price of this national assumptions actuals
	*/
	@Override
	public void setActualPrice(double actualPrice) {
		_nationalAssumptionsActuals.setActualPrice(actualPrice);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nationalAssumptionsActuals.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nationalAssumptionsActuals.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nationalAssumptionsActuals.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nationalAssumptionsActuals.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this national assumptions actuals.
	*
	* @param itemMasterSid the item master sid of this national assumptions actuals
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_nationalAssumptionsActuals.setItemMasterSid(itemMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_nationalAssumptionsActuals.setNew(n);
	}

	/**
	* Sets the period sid of this national assumptions actuals.
	*
	* @param periodSid the period sid of this national assumptions actuals
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_nationalAssumptionsActuals.setPeriodSid(periodSid);
	}

	/**
	* Sets the price type of this national assumptions actuals.
	*
	* @param priceType the price type of this national assumptions actuals
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_nationalAssumptionsActuals.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this national assumptions actuals.
	*
	* @param primaryKey the primary key of this national assumptions actuals
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK primaryKey) {
		_nationalAssumptionsActuals.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nationalAssumptionsActuals.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NationalAssumptionsActuals> toCacheModel() {
		return _nationalAssumptionsActuals.toCacheModel();
	}

	@Override
	public NationalAssumptionsActuals toEscapedModel() {
		return new NationalAssumptionsActualsWrapper(_nationalAssumptionsActuals.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nationalAssumptionsActuals.toString();
	}

	@Override
	public NationalAssumptionsActuals toUnescapedModel() {
		return new NationalAssumptionsActualsWrapper(_nationalAssumptionsActuals.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nationalAssumptionsActuals.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NationalAssumptionsActualsWrapper)) {
			return false;
		}

		NationalAssumptionsActualsWrapper nationalAssumptionsActualsWrapper = (NationalAssumptionsActualsWrapper)obj;

		if (Objects.equals(_nationalAssumptionsActuals,
					nationalAssumptionsActualsWrapper._nationalAssumptionsActuals)) {
			return true;
		}

		return false;
	}

	@Override
	public NationalAssumptionsActuals getWrappedModel() {
		return _nationalAssumptionsActuals;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nationalAssumptionsActuals.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nationalAssumptionsActuals.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nationalAssumptionsActuals.resetOriginalValues();
	}

	private final NationalAssumptionsActuals _nationalAssumptionsActuals;
}