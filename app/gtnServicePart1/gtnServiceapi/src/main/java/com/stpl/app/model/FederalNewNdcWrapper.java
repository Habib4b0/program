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
 * This class is a wrapper for {@link FederalNewNdc}.
 * </p>
 *
 * @author
 * @see FederalNewNdc
 * @generated
 */
@ProviderType
public class FederalNewNdcWrapper implements FederalNewNdc,
	ModelWrapper<FederalNewNdc> {
	public FederalNewNdcWrapper(FederalNewNdc federalNewNdc) {
		_federalNewNdc = federalNewNdc;
	}

	@Override
	public Class<?> getModelClass() {
		return FederalNewNdc.class;
	}

	@Override
	public String getModelClassName() {
		return FederalNewNdc.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fss", getFss());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("wacPrice", getWacPrice());
		attributes.put("nonFamp", getNonFamp());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double fss = (Double)attributes.get("fss");

		if (fss != null) {
			setFss(fss);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Double wacPrice = (Double)attributes.get("wacPrice");

		if (wacPrice != null) {
			setWacPrice(wacPrice);
		}

		Double nonFamp = (Double)attributes.get("nonFamp");

		if (nonFamp != null) {
			setNonFamp(nonFamp);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new FederalNewNdcWrapper((FederalNewNdc)_federalNewNdc.clone());
	}

	@Override
	public int compareTo(FederalNewNdc federalNewNdc) {
		return _federalNewNdc.compareTo(federalNewNdc);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _federalNewNdc.getExpandoBridge();
	}

	/**
	* Returns the fss of this federal new ndc.
	*
	* @return the fss of this federal new ndc
	*/
	@Override
	public double getFss() {
		return _federalNewNdc.getFss();
	}

	/**
	* Returns the item master sid of this federal new ndc.
	*
	* @return the item master sid of this federal new ndc
	*/
	@Override
	public int getItemMasterSid() {
		return _federalNewNdc.getItemMasterSid();
	}

	/**
	* Returns the non famp of this federal new ndc.
	*
	* @return the non famp of this federal new ndc
	*/
	@Override
	public double getNonFamp() {
		return _federalNewNdc.getNonFamp();
	}

	/**
	* Returns the primary key of this federal new ndc.
	*
	* @return the primary key of this federal new ndc
	*/
	@Override
	public int getPrimaryKey() {
		return _federalNewNdc.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _federalNewNdc.getPrimaryKeyObj();
	}

	/**
	* Returns the wac price of this federal new ndc.
	*
	* @return the wac price of this federal new ndc
	*/
	@Override
	public double getWacPrice() {
		return _federalNewNdc.getWacPrice();
	}

	@Override
	public int hashCode() {
		return _federalNewNdc.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _federalNewNdc.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _federalNewNdc.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _federalNewNdc.isNew();
	}

	@Override
	public void persist() {
		_federalNewNdc.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_federalNewNdc.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_federalNewNdc.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_federalNewNdc.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_federalNewNdc.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fss of this federal new ndc.
	*
	* @param fss the fss of this federal new ndc
	*/
	@Override
	public void setFss(double fss) {
		_federalNewNdc.setFss(fss);
	}

	/**
	* Sets the item master sid of this federal new ndc.
	*
	* @param itemMasterSid the item master sid of this federal new ndc
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_federalNewNdc.setItemMasterSid(itemMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_federalNewNdc.setNew(n);
	}

	/**
	* Sets the non famp of this federal new ndc.
	*
	* @param nonFamp the non famp of this federal new ndc
	*/
	@Override
	public void setNonFamp(double nonFamp) {
		_federalNewNdc.setNonFamp(nonFamp);
	}

	/**
	* Sets the primary key of this federal new ndc.
	*
	* @param primaryKey the primary key of this federal new ndc
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_federalNewNdc.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_federalNewNdc.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the wac price of this federal new ndc.
	*
	* @param wacPrice the wac price of this federal new ndc
	*/
	@Override
	public void setWacPrice(double wacPrice) {
		_federalNewNdc.setWacPrice(wacPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FederalNewNdc> toCacheModel() {
		return _federalNewNdc.toCacheModel();
	}

	@Override
	public FederalNewNdc toEscapedModel() {
		return new FederalNewNdcWrapper(_federalNewNdc.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _federalNewNdc.toString();
	}

	@Override
	public FederalNewNdc toUnescapedModel() {
		return new FederalNewNdcWrapper(_federalNewNdc.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _federalNewNdc.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FederalNewNdcWrapper)) {
			return false;
		}

		FederalNewNdcWrapper federalNewNdcWrapper = (FederalNewNdcWrapper)obj;

		if (Objects.equals(_federalNewNdc, federalNewNdcWrapper._federalNewNdc)) {
			return true;
		}

		return false;
	}

	@Override
	public FederalNewNdc getWrappedModel() {
		return _federalNewNdc;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _federalNewNdc.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _federalNewNdc.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_federalNewNdc.resetOriginalValues();
	}

	private final FederalNewNdc _federalNewNdc;
}