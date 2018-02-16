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
 * This class is a wrapper for {@link CffDetails}.
 * </p>
 *
 * @author
 * @see CffDetails
 * @generated
 */
@ProviderType
public class CffDetailsWrapper implements CffDetails, ModelWrapper<CffDetails> {
	public CffDetailsWrapper(CffDetails cffDetails) {
		_cffDetails = cffDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CffDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CffDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ccpDetailsSid", getCcpDetailsSid());
		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("cffMasterSid", getCffMasterSid());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("cffDetailsSid", getCffDetailsSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer ccpDetailsSid = (Integer)attributes.get("ccpDetailsSid");

		if (ccpDetailsSid != null) {
			setCcpDetailsSid(ccpDetailsSid);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}

		Integer cffMasterSid = (Integer)attributes.get("cffMasterSid");

		if (cffMasterSid != null) {
			setCffMasterSid(cffMasterSid);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer cffDetailsSid = (Integer)attributes.get("cffDetailsSid");

		if (cffDetailsSid != null) {
			setCffDetailsSid(cffDetailsSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CffDetailsWrapper((CffDetails)_cffDetails.clone());
	}

	@Override
	public int compareTo(CffDetails cffDetails) {
		return _cffDetails.compareTo(cffDetails);
	}

	/**
	* Returns the ccp details sid of this cff details.
	*
	* @return the ccp details sid of this cff details
	*/
	@Override
	public int getCcpDetailsSid() {
		return _cffDetails.getCcpDetailsSid();
	}

	/**
	* Returns the cff details sid of this cff details.
	*
	* @return the cff details sid of this cff details
	*/
	@Override
	public int getCffDetailsSid() {
		return _cffDetails.getCffDetailsSid();
	}

	/**
	* Returns the cff master sid of this cff details.
	*
	* @return the cff master sid of this cff details
	*/
	@Override
	public int getCffMasterSid() {
		return _cffDetails.getCffMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this cff details.
	*
	* @return the inbound status of this cff details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _cffDetails.getInboundStatus();
	}

	/**
	* Returns the primary key of this cff details.
	*
	* @return the primary key of this cff details
	*/
	@Override
	public int getPrimaryKey() {
		return _cffDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the projection master sid of this cff details.
	*
	* @return the projection master sid of this cff details
	*/
	@Override
	public int getProjectionMasterSid() {
		return _cffDetails.getProjectionMasterSid();
	}

	@Override
	public int hashCode() {
		return _cffDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffDetails.isNew();
	}

	@Override
	public void persist() {
		_cffDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the ccp details sid of this cff details.
	*
	* @param ccpDetailsSid the ccp details sid of this cff details
	*/
	@Override
	public void setCcpDetailsSid(int ccpDetailsSid) {
		_cffDetails.setCcpDetailsSid(ccpDetailsSid);
	}

	/**
	* Sets the cff details sid of this cff details.
	*
	* @param cffDetailsSid the cff details sid of this cff details
	*/
	@Override
	public void setCffDetailsSid(int cffDetailsSid) {
		_cffDetails.setCffDetailsSid(cffDetailsSid);
	}

	/**
	* Sets the cff master sid of this cff details.
	*
	* @param cffMasterSid the cff master sid of this cff details
	*/
	@Override
	public void setCffMasterSid(int cffMasterSid) {
		_cffDetails.setCffMasterSid(cffMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this cff details.
	*
	* @param inboundStatus the inbound status of this cff details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_cffDetails.setInboundStatus(inboundStatus);
	}

	@Override
	public void setNew(boolean n) {
		_cffDetails.setNew(n);
	}

	/**
	* Sets the primary key of this cff details.
	*
	* @param primaryKey the primary key of this cff details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection master sid of this cff details.
	*
	* @param projectionMasterSid the projection master sid of this cff details
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_cffDetails.setProjectionMasterSid(projectionMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffDetails> toCacheModel() {
		return _cffDetails.toCacheModel();
	}

	@Override
	public CffDetails toEscapedModel() {
		return new CffDetailsWrapper(_cffDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffDetails.toString();
	}

	@Override
	public CffDetails toUnescapedModel() {
		return new CffDetailsWrapper(_cffDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffDetailsWrapper)) {
			return false;
		}

		CffDetailsWrapper cffDetailsWrapper = (CffDetailsWrapper)obj;

		if (Objects.equals(_cffDetails, cffDetailsWrapper._cffDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CffDetails getWrappedModel() {
		return _cffDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffDetails.resetOriginalValues();
	}

	private final CffDetails _cffDetails;
}