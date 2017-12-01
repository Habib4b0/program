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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link StFederalNewNdc}.
 * </p>
 *
 * @author
 * @see StFederalNewNdc
 * @generated
 */
@ProviderType
public class StFederalNewNdcWrapper implements StFederalNewNdc,
	ModelWrapper<StFederalNewNdc> {
	public StFederalNewNdcWrapper(StFederalNewNdc stFederalNewNdc) {
		_stFederalNewNdc = stFederalNewNdc;
	}

	@Override
	public Class<?> getModelClass() {
		return StFederalNewNdc.class;
	}

	@Override
	public String getModelClassName() {
		return StFederalNewNdc.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fss", getFss());
		attributes.put("userId", getUserId());
		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("wacPrice", getWacPrice());
		attributes.put("sessionId", getSessionId());
		attributes.put("nonFamp", getNonFamp());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double fss = (Double)attributes.get("fss");

		if (fss != null) {
			setFss(fss);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Double wacPrice = (Double)attributes.get("wacPrice");

		if (wacPrice != null) {
			setWacPrice(wacPrice);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double nonFamp = (Double)attributes.get("nonFamp");

		if (nonFamp != null) {
			setNonFamp(nonFamp);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StFederalNewNdcWrapper((StFederalNewNdc)_stFederalNewNdc.clone());
	}

	@Override
	public int compareTo(StFederalNewNdc stFederalNewNdc) {
		return _stFederalNewNdc.compareTo(stFederalNewNdc);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stFederalNewNdc.getExpandoBridge();
	}

	/**
	* Returns the fss of this st federal new ndc.
	*
	* @return the fss of this st federal new ndc
	*/
	@Override
	public double getFss() {
		return _stFederalNewNdc.getFss();
	}

	/**
	* Returns the item master sid of this st federal new ndc.
	*
	* @return the item master sid of this st federal new ndc
	*/
	@Override
	public int getItemMasterSid() {
		return _stFederalNewNdc.getItemMasterSid();
	}

	/**
	* Returns the last modified date of this st federal new ndc.
	*
	* @return the last modified date of this st federal new ndc
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stFederalNewNdc.getLastModifiedDate();
	}

	/**
	* Returns the non famp of this st federal new ndc.
	*
	* @return the non famp of this st federal new ndc
	*/
	@Override
	public double getNonFamp() {
		return _stFederalNewNdc.getNonFamp();
	}

	/**
	* Returns the primary key of this st federal new ndc.
	*
	* @return the primary key of this st federal new ndc
	*/
	@Override
	public com.stpl.app.service.persistence.StFederalNewNdcPK getPrimaryKey() {
		return _stFederalNewNdc.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stFederalNewNdc.getPrimaryKeyObj();
	}

	/**
	* Returns the session ID of this st federal new ndc.
	*
	* @return the session ID of this st federal new ndc
	*/
	@Override
	public int getSessionId() {
		return _stFederalNewNdc.getSessionId();
	}

	/**
	* Returns the user ID of this st federal new ndc.
	*
	* @return the user ID of this st federal new ndc
	*/
	@Override
	public int getUserId() {
		return _stFederalNewNdc.getUserId();
	}

	/**
	* Returns the wac price of this st federal new ndc.
	*
	* @return the wac price of this st federal new ndc
	*/
	@Override
	public double getWacPrice() {
		return _stFederalNewNdc.getWacPrice();
	}

	@Override
	public int hashCode() {
		return _stFederalNewNdc.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stFederalNewNdc.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stFederalNewNdc.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stFederalNewNdc.isNew();
	}

	@Override
	public void persist() {
		_stFederalNewNdc.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stFederalNewNdc.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stFederalNewNdc.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stFederalNewNdc.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stFederalNewNdc.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fss of this st federal new ndc.
	*
	* @param fss the fss of this st federal new ndc
	*/
	@Override
	public void setFss(double fss) {
		_stFederalNewNdc.setFss(fss);
	}

	/**
	* Sets the item master sid of this st federal new ndc.
	*
	* @param itemMasterSid the item master sid of this st federal new ndc
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_stFederalNewNdc.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the last modified date of this st federal new ndc.
	*
	* @param lastModifiedDate the last modified date of this st federal new ndc
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stFederalNewNdc.setLastModifiedDate(lastModifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stFederalNewNdc.setNew(n);
	}

	/**
	* Sets the non famp of this st federal new ndc.
	*
	* @param nonFamp the non famp of this st federal new ndc
	*/
	@Override
	public void setNonFamp(double nonFamp) {
		_stFederalNewNdc.setNonFamp(nonFamp);
	}

	/**
	* Sets the primary key of this st federal new ndc.
	*
	* @param primaryKey the primary key of this st federal new ndc
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StFederalNewNdcPK primaryKey) {
		_stFederalNewNdc.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stFederalNewNdc.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this st federal new ndc.
	*
	* @param sessionId the session ID of this st federal new ndc
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stFederalNewNdc.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st federal new ndc.
	*
	* @param userId the user ID of this st federal new ndc
	*/
	@Override
	public void setUserId(int userId) {
		_stFederalNewNdc.setUserId(userId);
	}

	/**
	* Sets the wac price of this st federal new ndc.
	*
	* @param wacPrice the wac price of this st federal new ndc
	*/
	@Override
	public void setWacPrice(double wacPrice) {
		_stFederalNewNdc.setWacPrice(wacPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StFederalNewNdc> toCacheModel() {
		return _stFederalNewNdc.toCacheModel();
	}

	@Override
	public StFederalNewNdc toEscapedModel() {
		return new StFederalNewNdcWrapper(_stFederalNewNdc.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stFederalNewNdc.toString();
	}

	@Override
	public StFederalNewNdc toUnescapedModel() {
		return new StFederalNewNdcWrapper(_stFederalNewNdc.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stFederalNewNdc.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StFederalNewNdcWrapper)) {
			return false;
		}

		StFederalNewNdcWrapper stFederalNewNdcWrapper = (StFederalNewNdcWrapper)obj;

		if (Objects.equals(_stFederalNewNdc,
					stFederalNewNdcWrapper._stFederalNewNdc)) {
			return true;
		}

		return false;
	}

	@Override
	public StFederalNewNdc getWrappedModel() {
		return _stFederalNewNdc;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stFederalNewNdc.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stFederalNewNdc.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stFederalNewNdc.resetOriginalValues();
	}

	private final StFederalNewNdc _stFederalNewNdc;
}