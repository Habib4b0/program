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
 * This class is a wrapper for {@link NaProjDetails}.
 * </p>
 *
 * @author
 * @see NaProjDetails
 * @generated
 */
@ProviderType
public class NaProjDetailsWrapper implements NaProjDetails,
	ModelWrapper<NaProjDetails> {
	public NaProjDetailsWrapper(NaProjDetails naProjDetails) {
		_naProjDetails = naProjDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return NaProjDetails.class;
	}

	@Override
	public String getModelClassName() {
		return NaProjDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("naProjMasterSid", getNaProjMasterSid());
		attributes.put("naProjDetailsSid", getNaProjDetailsSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer naProjMasterSid = (Integer)attributes.get("naProjMasterSid");

		if (naProjMasterSid != null) {
			setNaProjMasterSid(naProjMasterSid);
		}

		Integer naProjDetailsSid = (Integer)attributes.get("naProjDetailsSid");

		if (naProjDetailsSid != null) {
			setNaProjDetailsSid(naProjDetailsSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NaProjDetailsWrapper((NaProjDetails)_naProjDetails.clone());
	}

	@Override
	public int compareTo(NaProjDetails naProjDetails) {
		return _naProjDetails.compareTo(naProjDetails);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _naProjDetails.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this na proj details.
	*
	* @return the item master sid of this na proj details
	*/
	@Override
	public int getItemMasterSid() {
		return _naProjDetails.getItemMasterSid();
	}

	/**
	* Returns the na proj details sid of this na proj details.
	*
	* @return the na proj details sid of this na proj details
	*/
	@Override
	public int getNaProjDetailsSid() {
		return _naProjDetails.getNaProjDetailsSid();
	}

	/**
	* Returns the na proj master sid of this na proj details.
	*
	* @return the na proj master sid of this na proj details
	*/
	@Override
	public int getNaProjMasterSid() {
		return _naProjDetails.getNaProjMasterSid();
	}

	/**
	* Returns the primary key of this na proj details.
	*
	* @return the primary key of this na proj details
	*/
	@Override
	public int getPrimaryKey() {
		return _naProjDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _naProjDetails.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _naProjDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _naProjDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _naProjDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _naProjDetails.isNew();
	}

	@Override
	public void persist() {
		_naProjDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_naProjDetails.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_naProjDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_naProjDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_naProjDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this na proj details.
	*
	* @param itemMasterSid the item master sid of this na proj details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_naProjDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the na proj details sid of this na proj details.
	*
	* @param naProjDetailsSid the na proj details sid of this na proj details
	*/
	@Override
	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_naProjDetails.setNaProjDetailsSid(naProjDetailsSid);
	}

	/**
	* Sets the na proj master sid of this na proj details.
	*
	* @param naProjMasterSid the na proj master sid of this na proj details
	*/
	@Override
	public void setNaProjMasterSid(int naProjMasterSid) {
		_naProjDetails.setNaProjMasterSid(naProjMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_naProjDetails.setNew(n);
	}

	/**
	* Sets the primary key of this na proj details.
	*
	* @param primaryKey the primary key of this na proj details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_naProjDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_naProjDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NaProjDetails> toCacheModel() {
		return _naProjDetails.toCacheModel();
	}

	@Override
	public NaProjDetails toEscapedModel() {
		return new NaProjDetailsWrapper(_naProjDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _naProjDetails.toString();
	}

	@Override
	public NaProjDetails toUnescapedModel() {
		return new NaProjDetailsWrapper(_naProjDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _naProjDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NaProjDetailsWrapper)) {
			return false;
		}

		NaProjDetailsWrapper naProjDetailsWrapper = (NaProjDetailsWrapper)obj;

		if (Objects.equals(_naProjDetails, naProjDetailsWrapper._naProjDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public NaProjDetails getWrappedModel() {
		return _naProjDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _naProjDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _naProjDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_naProjDetails.resetOriginalValues();
	}

	private final NaProjDetails _naProjDetails;
}