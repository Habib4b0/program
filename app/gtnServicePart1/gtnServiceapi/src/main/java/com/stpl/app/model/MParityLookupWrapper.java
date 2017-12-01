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
 * This class is a wrapper for {@link MParityLookup}.
 * </p>
 *
 * @author
 * @see MParityLookup
 * @generated
 */
@ProviderType
public class MParityLookupWrapper implements MParityLookup,
	ModelWrapper<MParityLookup> {
	public MParityLookupWrapper(MParityLookup mParityLookup) {
		_mParityLookup = mParityLookup;
	}

	@Override
	public Class<?> getModelClass() {
		return MParityLookup.class;
	}

	@Override
	public String getModelClassName() {
		return MParityLookup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("marketType", getMarketType());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("mParityLookupSid", getMParityLookupSid());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		String marketType = (String)attributes.get("marketType");

		if (marketType != null) {
			setMarketType(marketType);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer mParityLookupSid = (Integer)attributes.get("mParityLookupSid");

		if (mParityLookupSid != null) {
			setMParityLookupSid(mParityLookupSid);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MParityLookupWrapper((MParityLookup)_mParityLookup.clone());
	}

	@Override
	public int compareTo(MParityLookup mParityLookup) {
		return _mParityLookup.compareTo(mParityLookup);
	}

	/**
	* Returns the contract master sid of this m parity lookup.
	*
	* @return the contract master sid of this m parity lookup
	*/
	@Override
	public int getContractMasterSid() {
		return _mParityLookup.getContractMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mParityLookup.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this m parity lookup.
	*
	* @return the item master sid of this m parity lookup
	*/
	@Override
	public int getItemMasterSid() {
		return _mParityLookup.getItemMasterSid();
	}

	/**
	* Returns the market type of this m parity lookup.
	*
	* @return the market type of this m parity lookup
	*/
	@Override
	public java.lang.String getMarketType() {
		return _mParityLookup.getMarketType();
	}

	/**
	* Returns the m parity lookup sid of this m parity lookup.
	*
	* @return the m parity lookup sid of this m parity lookup
	*/
	@Override
	public int getMParityLookupSid() {
		return _mParityLookup.getMParityLookupSid();
	}

	/**
	* Returns the primary key of this m parity lookup.
	*
	* @return the primary key of this m parity lookup
	*/
	@Override
	public int getPrimaryKey() {
		return _mParityLookup.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mParityLookup.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this m parity lookup.
	*
	* @return the projection details sid of this m parity lookup
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _mParityLookup.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _mParityLookup.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mParityLookup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mParityLookup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mParityLookup.isNew();
	}

	@Override
	public void persist() {
		_mParityLookup.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mParityLookup.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract master sid of this m parity lookup.
	*
	* @param contractMasterSid the contract master sid of this m parity lookup
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_mParityLookup.setContractMasterSid(contractMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mParityLookup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mParityLookup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mParityLookup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this m parity lookup.
	*
	* @param itemMasterSid the item master sid of this m parity lookup
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_mParityLookup.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the market type of this m parity lookup.
	*
	* @param marketType the market type of this m parity lookup
	*/
	@Override
	public void setMarketType(java.lang.String marketType) {
		_mParityLookup.setMarketType(marketType);
	}

	/**
	* Sets the m parity lookup sid of this m parity lookup.
	*
	* @param mParityLookupSid the m parity lookup sid of this m parity lookup
	*/
	@Override
	public void setMParityLookupSid(int mParityLookupSid) {
		_mParityLookup.setMParityLookupSid(mParityLookupSid);
	}

	@Override
	public void setNew(boolean n) {
		_mParityLookup.setNew(n);
	}

	/**
	* Sets the primary key of this m parity lookup.
	*
	* @param primaryKey the primary key of this m parity lookup
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_mParityLookup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mParityLookup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this m parity lookup.
	*
	* @param projectionDetailsSid the projection details sid of this m parity lookup
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_mParityLookup.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MParityLookup> toCacheModel() {
		return _mParityLookup.toCacheModel();
	}

	@Override
	public MParityLookup toEscapedModel() {
		return new MParityLookupWrapper(_mParityLookup.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mParityLookup.toString();
	}

	@Override
	public MParityLookup toUnescapedModel() {
		return new MParityLookupWrapper(_mParityLookup.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _mParityLookup.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MParityLookupWrapper)) {
			return false;
		}

		MParityLookupWrapper mParityLookupWrapper = (MParityLookupWrapper)obj;

		if (Objects.equals(_mParityLookup, mParityLookupWrapper._mParityLookup)) {
			return true;
		}

		return false;
	}

	@Override
	public MParityLookup getWrappedModel() {
		return _mParityLookup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mParityLookup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mParityLookup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mParityLookup.resetOriginalValues();
	}

	private final MParityLookup _mParityLookup;
}