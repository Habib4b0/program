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
 * This class is a wrapper for {@link CcpDetails}.
 * </p>
 *
 * @author
 * @see CcpDetails
 * @generated
 */
@ProviderType
public class CcpDetailsWrapper implements CcpDetails, ModelWrapper<CcpDetails> {
	public CcpDetailsWrapper(CcpDetails ccpDetails) {
		_ccpDetails = ccpDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CcpDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CcpDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("ccpDetailsSid", getCcpDetailsSid());
		attributes.put("companyMasterSid", getCompanyMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer ccpDetailsSid = (Integer)attributes.get("ccpDetailsSid");

		if (ccpDetailsSid != null) {
			setCcpDetailsSid(ccpDetailsSid);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CcpDetailsWrapper((CcpDetails)_ccpDetails.clone());
	}

	@Override
	public int compareTo(CcpDetails ccpDetails) {
		return _ccpDetails.compareTo(ccpDetails);
	}

	/**
	* Returns the ccp details sid of this ccp details.
	*
	* @return the ccp details sid of this ccp details
	*/
	@Override
	public int getCcpDetailsSid() {
		return _ccpDetails.getCcpDetailsSid();
	}

	/**
	* Returns the company master sid of this ccp details.
	*
	* @return the company master sid of this ccp details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _ccpDetails.getCompanyMasterSid();
	}

	/**
	* Returns the contract master sid of this ccp details.
	*
	* @return the contract master sid of this ccp details
	*/
	@Override
	public int getContractMasterSid() {
		return _ccpDetails.getContractMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ccpDetails.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this ccp details.
	*
	* @return the item master sid of this ccp details
	*/
	@Override
	public int getItemMasterSid() {
		return _ccpDetails.getItemMasterSid();
	}

	/**
	* Returns the primary key of this ccp details.
	*
	* @return the primary key of this ccp details
	*/
	@Override
	public int getPrimaryKey() {
		return _ccpDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ccpDetails.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _ccpDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ccpDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ccpDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ccpDetails.isNew();
	}

	@Override
	public void persist() {
		_ccpDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ccpDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the ccp details sid of this ccp details.
	*
	* @param ccpDetailsSid the ccp details sid of this ccp details
	*/
	@Override
	public void setCcpDetailsSid(int ccpDetailsSid) {
		_ccpDetails.setCcpDetailsSid(ccpDetailsSid);
	}

	/**
	* Sets the company master sid of this ccp details.
	*
	* @param companyMasterSid the company master sid of this ccp details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_ccpDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the contract master sid of this ccp details.
	*
	* @param contractMasterSid the contract master sid of this ccp details
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_ccpDetails.setContractMasterSid(contractMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ccpDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ccpDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ccpDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this ccp details.
	*
	* @param itemMasterSid the item master sid of this ccp details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_ccpDetails.setItemMasterSid(itemMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_ccpDetails.setNew(n);
	}

	/**
	* Sets the primary key of this ccp details.
	*
	* @param primaryKey the primary key of this ccp details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ccpDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ccpDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CcpDetails> toCacheModel() {
		return _ccpDetails.toCacheModel();
	}

	@Override
	public CcpDetails toEscapedModel() {
		return new CcpDetailsWrapper(_ccpDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ccpDetails.toString();
	}

	@Override
	public CcpDetails toUnescapedModel() {
		return new CcpDetailsWrapper(_ccpDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ccpDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CcpDetailsWrapper)) {
			return false;
		}

		CcpDetailsWrapper ccpDetailsWrapper = (CcpDetailsWrapper)obj;

		if (Objects.equals(_ccpDetails, ccpDetailsWrapper._ccpDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CcpDetails getWrappedModel() {
		return _ccpDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ccpDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ccpDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ccpDetails.resetOriginalValues();
	}

	private final CcpDetails _ccpDetails;
}