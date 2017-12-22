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

package com.stpl.app.parttwo.model;

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
 * This class is a wrapper for {@link AccClosureDetails}.
 * </p>
 *
 * @author
 * @see AccClosureDetails
 * @generated
 */
@ProviderType
public class AccClosureDetailsWrapper implements AccClosureDetails,
	ModelWrapper<AccClosureDetails> {
	public AccClosureDetailsWrapper(AccClosureDetails accClosureDetails) {
		_accClosureDetails = accClosureDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return AccClosureDetails.class;
	}

	@Override
	public String getModelClassName() {
		return AccClosureDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accClosureDetailsSid", getAccClosureDetailsSid());
		attributes.put("ccpDetailsSid", getCcpDetailsSid());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("rsModelSid", getRsModelSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer accClosureDetailsSid = (Integer)attributes.get(
				"accClosureDetailsSid");

		if (accClosureDetailsSid != null) {
			setAccClosureDetailsSid(accClosureDetailsSid);
		}

		Integer ccpDetailsSid = (Integer)attributes.get("ccpDetailsSid");

		if (ccpDetailsSid != null) {
			setCcpDetailsSid(ccpDetailsSid);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AccClosureDetailsWrapper((AccClosureDetails)_accClosureDetails.clone());
	}

	@Override
	public int compareTo(AccClosureDetails accClosureDetails) {
		return _accClosureDetails.compareTo(accClosureDetails);
	}

	/**
	* Returns the acc closure details sid of this acc closure details.
	*
	* @return the acc closure details sid of this acc closure details
	*/
	@Override
	public int getAccClosureDetailsSid() {
		return _accClosureDetails.getAccClosureDetailsSid();
	}

	/**
	* Returns the acc closure master sid of this acc closure details.
	*
	* @return the acc closure master sid of this acc closure details
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _accClosureDetails.getAccClosureMasterSid();
	}

	/**
	* Returns the ccp details sid of this acc closure details.
	*
	* @return the ccp details sid of this acc closure details
	*/
	@Override
	public int getCcpDetailsSid() {
		return _accClosureDetails.getCcpDetailsSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accClosureDetails.getExpandoBridge();
	}

	/**
	* Returns the primary key of this acc closure details.
	*
	* @return the primary key of this acc closure details
	*/
	@Override
	public int getPrimaryKey() {
		return _accClosureDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accClosureDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the rs model sid of this acc closure details.
	*
	* @return the rs model sid of this acc closure details
	*/
	@Override
	public int getRsModelSid() {
		return _accClosureDetails.getRsModelSid();
	}

	@Override
	public int hashCode() {
		return _accClosureDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _accClosureDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accClosureDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accClosureDetails.isNew();
	}

	@Override
	public void persist() {
		_accClosureDetails.persist();
	}

	/**
	* Sets the acc closure details sid of this acc closure details.
	*
	* @param accClosureDetailsSid the acc closure details sid of this acc closure details
	*/
	@Override
	public void setAccClosureDetailsSid(int accClosureDetailsSid) {
		_accClosureDetails.setAccClosureDetailsSid(accClosureDetailsSid);
	}

	/**
	* Sets the acc closure master sid of this acc closure details.
	*
	* @param accClosureMasterSid the acc closure master sid of this acc closure details
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_accClosureDetails.setAccClosureMasterSid(accClosureMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accClosureDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the ccp details sid of this acc closure details.
	*
	* @param ccpDetailsSid the ccp details sid of this acc closure details
	*/
	@Override
	public void setCcpDetailsSid(int ccpDetailsSid) {
		_accClosureDetails.setCcpDetailsSid(ccpDetailsSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accClosureDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accClosureDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accClosureDetails.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_accClosureDetails.setNew(n);
	}

	/**
	* Sets the primary key of this acc closure details.
	*
	* @param primaryKey the primary key of this acc closure details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_accClosureDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accClosureDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rs model sid of this acc closure details.
	*
	* @param rsModelSid the rs model sid of this acc closure details
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_accClosureDetails.setRsModelSid(rsModelSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccClosureDetails> toCacheModel() {
		return _accClosureDetails.toCacheModel();
	}

	@Override
	public AccClosureDetails toEscapedModel() {
		return new AccClosureDetailsWrapper(_accClosureDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accClosureDetails.toString();
	}

	@Override
	public AccClosureDetails toUnescapedModel() {
		return new AccClosureDetailsWrapper(_accClosureDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _accClosureDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccClosureDetailsWrapper)) {
			return false;
		}

		AccClosureDetailsWrapper accClosureDetailsWrapper = (AccClosureDetailsWrapper)obj;

		if (Objects.equals(_accClosureDetails,
					accClosureDetailsWrapper._accClosureDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public AccClosureDetails getWrappedModel() {
		return _accClosureDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accClosureDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accClosureDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accClosureDetails.resetOriginalValues();
	}

	private final AccClosureDetails _accClosureDetails;
}