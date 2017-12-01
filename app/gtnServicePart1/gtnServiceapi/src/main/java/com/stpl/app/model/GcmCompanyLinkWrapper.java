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
 * This class is a wrapper for {@link GcmCompanyLink}.
 * </p>
 *
 * @author
 * @see GcmCompanyLink
 * @generated
 */
@ProviderType
public class GcmCompanyLinkWrapper implements GcmCompanyLink,
	ModelWrapper<GcmCompanyLink> {
	public GcmCompanyLinkWrapper(GcmCompanyLink gcmCompanyLink) {
		_gcmCompanyLink = gcmCompanyLink;
	}

	@Override
	public Class<?> getModelClass() {
		return GcmCompanyLink.class;
	}

	@Override
	public String getModelClassName() {
		return GcmCompanyLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkRecord", getCheckRecord());
		attributes.put("userId", getUserId());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("gcmCompanyLinkSid", getGcmCompanyLinkSid());
		attributes.put("sessionId", getSessionId());
		attributes.put("companyName", getCompanyName());
		attributes.put("linkId", getLinkId());
		attributes.put("companyMasterSid", getCompanyMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String companyStringId = (String)attributes.get("companyStringId");

		if (companyStringId != null) {
			setCompanyStringId(companyStringId);
		}

		Integer gcmCompanyLinkSid = (Integer)attributes.get("gcmCompanyLinkSid");

		if (gcmCompanyLinkSid != null) {
			setGcmCompanyLinkSid(gcmCompanyLinkSid);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String linkId = (String)attributes.get("linkId");

		if (linkId != null) {
			setLinkId(linkId);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new GcmCompanyLinkWrapper((GcmCompanyLink)_gcmCompanyLink.clone());
	}

	@Override
	public int compareTo(GcmCompanyLink gcmCompanyLink) {
		return _gcmCompanyLink.compareTo(gcmCompanyLink);
	}

	/**
	* Returns the check record of this gcm company link.
	*
	* @return the check record of this gcm company link
	*/
	@Override
	public boolean getCheckRecord() {
		return _gcmCompanyLink.getCheckRecord();
	}

	/**
	* Returns the company master sid of this gcm company link.
	*
	* @return the company master sid of this gcm company link
	*/
	@Override
	public int getCompanyMasterSid() {
		return _gcmCompanyLink.getCompanyMasterSid();
	}

	/**
	* Returns the company name of this gcm company link.
	*
	* @return the company name of this gcm company link
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _gcmCompanyLink.getCompanyName();
	}

	/**
	* Returns the company no of this gcm company link.
	*
	* @return the company no of this gcm company link
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _gcmCompanyLink.getCompanyNo();
	}

	/**
	* Returns the company string ID of this gcm company link.
	*
	* @return the company string ID of this gcm company link
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _gcmCompanyLink.getCompanyStringId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _gcmCompanyLink.getExpandoBridge();
	}

	/**
	* Returns the gcm company link sid of this gcm company link.
	*
	* @return the gcm company link sid of this gcm company link
	*/
	@Override
	public int getGcmCompanyLinkSid() {
		return _gcmCompanyLink.getGcmCompanyLinkSid();
	}

	/**
	* Returns the link ID of this gcm company link.
	*
	* @return the link ID of this gcm company link
	*/
	@Override
	public java.lang.String getLinkId() {
		return _gcmCompanyLink.getLinkId();
	}

	/**
	* Returns the primary key of this gcm company link.
	*
	* @return the primary key of this gcm company link
	*/
	@Override
	public int getPrimaryKey() {
		return _gcmCompanyLink.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _gcmCompanyLink.getPrimaryKeyObj();
	}

	/**
	* Returns the session ID of this gcm company link.
	*
	* @return the session ID of this gcm company link
	*/
	@Override
	public java.lang.String getSessionId() {
		return _gcmCompanyLink.getSessionId();
	}

	/**
	* Returns the user ID of this gcm company link.
	*
	* @return the user ID of this gcm company link
	*/
	@Override
	public int getUserId() {
		return _gcmCompanyLink.getUserId();
	}

	@Override
	public int hashCode() {
		return _gcmCompanyLink.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _gcmCompanyLink.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this gcm company link is check record.
	*
	* @return <code>true</code> if this gcm company link is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _gcmCompanyLink.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _gcmCompanyLink.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _gcmCompanyLink.isNew();
	}

	@Override
	public void persist() {
		_gcmCompanyLink.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_gcmCompanyLink.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this gcm company link is check record.
	*
	* @param checkRecord the check record of this gcm company link
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_gcmCompanyLink.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company master sid of this gcm company link.
	*
	* @param companyMasterSid the company master sid of this gcm company link
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_gcmCompanyLink.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company name of this gcm company link.
	*
	* @param companyName the company name of this gcm company link
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_gcmCompanyLink.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this gcm company link.
	*
	* @param companyNo the company no of this gcm company link
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_gcmCompanyLink.setCompanyNo(companyNo);
	}

	/**
	* Sets the company string ID of this gcm company link.
	*
	* @param companyStringId the company string ID of this gcm company link
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_gcmCompanyLink.setCompanyStringId(companyStringId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_gcmCompanyLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_gcmCompanyLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_gcmCompanyLink.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gcm company link sid of this gcm company link.
	*
	* @param gcmCompanyLinkSid the gcm company link sid of this gcm company link
	*/
	@Override
	public void setGcmCompanyLinkSid(int gcmCompanyLinkSid) {
		_gcmCompanyLink.setGcmCompanyLinkSid(gcmCompanyLinkSid);
	}

	/**
	* Sets the link ID of this gcm company link.
	*
	* @param linkId the link ID of this gcm company link
	*/
	@Override
	public void setLinkId(java.lang.String linkId) {
		_gcmCompanyLink.setLinkId(linkId);
	}

	@Override
	public void setNew(boolean n) {
		_gcmCompanyLink.setNew(n);
	}

	/**
	* Sets the primary key of this gcm company link.
	*
	* @param primaryKey the primary key of this gcm company link
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_gcmCompanyLink.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_gcmCompanyLink.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this gcm company link.
	*
	* @param sessionId the session ID of this gcm company link
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_gcmCompanyLink.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this gcm company link.
	*
	* @param userId the user ID of this gcm company link
	*/
	@Override
	public void setUserId(int userId) {
		_gcmCompanyLink.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<GcmCompanyLink> toCacheModel() {
		return _gcmCompanyLink.toCacheModel();
	}

	@Override
	public GcmCompanyLink toEscapedModel() {
		return new GcmCompanyLinkWrapper(_gcmCompanyLink.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _gcmCompanyLink.toString();
	}

	@Override
	public GcmCompanyLink toUnescapedModel() {
		return new GcmCompanyLinkWrapper(_gcmCompanyLink.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _gcmCompanyLink.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GcmCompanyLinkWrapper)) {
			return false;
		}

		GcmCompanyLinkWrapper gcmCompanyLinkWrapper = (GcmCompanyLinkWrapper)obj;

		if (Objects.equals(_gcmCompanyLink,
					gcmCompanyLinkWrapper._gcmCompanyLink)) {
			return true;
		}

		return false;
	}

	@Override
	public GcmCompanyLink getWrappedModel() {
		return _gcmCompanyLink;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _gcmCompanyLink.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _gcmCompanyLink.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_gcmCompanyLink.resetOriginalValues();
	}

	private final GcmCompanyLink _gcmCompanyLink;
}