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
 * This class is a wrapper for {@link StSelectionTable}.
 * </p>
 *
 * @author
 * @see StSelectionTable
 * @generated
 */
@ProviderType
public class StSelectionTableWrapper implements StSelectionTable,
	ModelWrapper<StSelectionTable> {
	public StSelectionTableWrapper(StSelectionTable stSelectionTable) {
		_stSelectionTable = stSelectionTable;
	}

	@Override
	public Class<?> getModelClass() {
		return StSelectionTable.class;
	}

	@Override
	public String getModelClassName() {
		return StSelectionTable.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("selectionType", getSelectionType());
		attributes.put("userId", getUserId());
		attributes.put("sessionId", getSessionId());
		attributes.put("companyItemSid", getCompanyItemSid());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String selectionType = (String)attributes.get("selectionType");

		if (selectionType != null) {
			setSelectionType(selectionType);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer companyItemSid = (Integer)attributes.get("companyItemSid");

		if (companyItemSid != null) {
			setCompanyItemSid(companyItemSid);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StSelectionTableWrapper((StSelectionTable)_stSelectionTable.clone());
	}

	@Override
	public int compareTo(StSelectionTable stSelectionTable) {
		return _stSelectionTable.compareTo(stSelectionTable);
	}

	/**
	* Returns the check record of this st selection table.
	*
	* @return the check record of this st selection table
	*/
	@Override
	public boolean getCheckRecord() {
		return _stSelectionTable.getCheckRecord();
	}

	/**
	* Returns the company item sid of this st selection table.
	*
	* @return the company item sid of this st selection table
	*/
	@Override
	public int getCompanyItemSid() {
		return _stSelectionTable.getCompanyItemSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stSelectionTable.getExpandoBridge();
	}

	/**
	* Returns the primary key of this st selection table.
	*
	* @return the primary key of this st selection table
	*/
	@Override
	public com.stpl.app.service.persistence.StSelectionTablePK getPrimaryKey() {
		return _stSelectionTable.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stSelectionTable.getPrimaryKeyObj();
	}

	/**
	* Returns the selection type of this st selection table.
	*
	* @return the selection type of this st selection table
	*/
	@Override
	public java.lang.String getSelectionType() {
		return _stSelectionTable.getSelectionType();
	}

	/**
	* Returns the session ID of this st selection table.
	*
	* @return the session ID of this st selection table
	*/
	@Override
	public java.lang.String getSessionId() {
		return _stSelectionTable.getSessionId();
	}

	/**
	* Returns the user ID of this st selection table.
	*
	* @return the user ID of this st selection table
	*/
	@Override
	public int getUserId() {
		return _stSelectionTable.getUserId();
	}

	@Override
	public int hashCode() {
		return _stSelectionTable.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stSelectionTable.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this st selection table is check record.
	*
	* @return <code>true</code> if this st selection table is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _stSelectionTable.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _stSelectionTable.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stSelectionTable.isNew();
	}

	@Override
	public void persist() {
		_stSelectionTable.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stSelectionTable.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this st selection table is check record.
	*
	* @param checkRecord the check record of this st selection table
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_stSelectionTable.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company item sid of this st selection table.
	*
	* @param companyItemSid the company item sid of this st selection table
	*/
	@Override
	public void setCompanyItemSid(int companyItemSid) {
		_stSelectionTable.setCompanyItemSid(companyItemSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stSelectionTable.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stSelectionTable.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stSelectionTable.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_stSelectionTable.setNew(n);
	}

	/**
	* Sets the primary key of this st selection table.
	*
	* @param primaryKey the primary key of this st selection table
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StSelectionTablePK primaryKey) {
		_stSelectionTable.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stSelectionTable.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the selection type of this st selection table.
	*
	* @param selectionType the selection type of this st selection table
	*/
	@Override
	public void setSelectionType(java.lang.String selectionType) {
		_stSelectionTable.setSelectionType(selectionType);
	}

	/**
	* Sets the session ID of this st selection table.
	*
	* @param sessionId the session ID of this st selection table
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_stSelectionTable.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st selection table.
	*
	* @param userId the user ID of this st selection table
	*/
	@Override
	public void setUserId(int userId) {
		_stSelectionTable.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StSelectionTable> toCacheModel() {
		return _stSelectionTable.toCacheModel();
	}

	@Override
	public StSelectionTable toEscapedModel() {
		return new StSelectionTableWrapper(_stSelectionTable.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stSelectionTable.toString();
	}

	@Override
	public StSelectionTable toUnescapedModel() {
		return new StSelectionTableWrapper(_stSelectionTable.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stSelectionTable.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StSelectionTableWrapper)) {
			return false;
		}

		StSelectionTableWrapper stSelectionTableWrapper = (StSelectionTableWrapper)obj;

		if (Objects.equals(_stSelectionTable,
					stSelectionTableWrapper._stSelectionTable)) {
			return true;
		}

		return false;
	}

	@Override
	public StSelectionTable getWrappedModel() {
		return _stSelectionTable;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stSelectionTable.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stSelectionTable.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stSelectionTable.resetOriginalValues();
	}

	private final StSelectionTable _stSelectionTable;
}