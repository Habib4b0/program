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
 * This class is a wrapper for {@link StChSalesProjectionMaster}.
 * </p>
 *
 * @author
 * @see StChSalesProjectionMaster
 * @generated
 */
@ProviderType
public class StChSalesProjectionMasterWrapper
	implements StChSalesProjectionMaster,
		ModelWrapper<StChSalesProjectionMaster> {
	public StChSalesProjectionMasterWrapper(
		StChSalesProjectionMaster stChSalesProjectionMaster) {
		_stChSalesProjectionMaster = stChSalesProjectionMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return StChSalesProjectionMaster.class;
	}

	@Override
	public String getModelClassName() {
		return StChSalesProjectionMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("calculationPeriods", getCalculationPeriods());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("userId", getUserId());
		attributes.put("sessionId", getSessionId());
		attributes.put("methodology", getMethodology());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String calculationPeriods = (String)attributes.get("calculationPeriods");

		if (calculationPeriods != null) {
			setCalculationPeriods(calculationPeriods);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String methodology = (String)attributes.get("methodology");

		if (methodology != null) {
			setMethodology(methodology);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StChSalesProjectionMasterWrapper((StChSalesProjectionMaster)_stChSalesProjectionMaster.clone());
	}

	@Override
	public int compareTo(StChSalesProjectionMaster stChSalesProjectionMaster) {
		return _stChSalesProjectionMaster.compareTo(stChSalesProjectionMaster);
	}

	/**
	* Returns the calculation periods of this st ch sales projection master.
	*
	* @return the calculation periods of this st ch sales projection master
	*/
	@Override
	public java.lang.String getCalculationPeriods() {
		return _stChSalesProjectionMaster.getCalculationPeriods();
	}

	/**
	* Returns the check record of this st ch sales projection master.
	*
	* @return the check record of this st ch sales projection master
	*/
	@Override
	public boolean getCheckRecord() {
		return _stChSalesProjectionMaster.getCheckRecord();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stChSalesProjectionMaster.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st ch sales projection master.
	*
	* @return the last modified date of this st ch sales projection master
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stChSalesProjectionMaster.getLastModifiedDate();
	}

	/**
	* Returns the methodology of this st ch sales projection master.
	*
	* @return the methodology of this st ch sales projection master
	*/
	@Override
	public java.lang.String getMethodology() {
		return _stChSalesProjectionMaster.getMethodology();
	}

	/**
	* Returns the primary key of this st ch sales projection master.
	*
	* @return the primary key of this st ch sales projection master
	*/
	@Override
	public com.stpl.app.service.persistence.StChSalesProjectionMasterPK getPrimaryKey() {
		return _stChSalesProjectionMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stChSalesProjectionMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st ch sales projection master.
	*
	* @return the projection details sid of this st ch sales projection master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stChSalesProjectionMaster.getProjectionDetailsSid();
	}

	/**
	* Returns the session ID of this st ch sales projection master.
	*
	* @return the session ID of this st ch sales projection master
	*/
	@Override
	public int getSessionId() {
		return _stChSalesProjectionMaster.getSessionId();
	}

	/**
	* Returns the user ID of this st ch sales projection master.
	*
	* @return the user ID of this st ch sales projection master
	*/
	@Override
	public int getUserId() {
		return _stChSalesProjectionMaster.getUserId();
	}

	@Override
	public int hashCode() {
		return _stChSalesProjectionMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stChSalesProjectionMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this st ch sales projection master is check record.
	*
	* @return <code>true</code> if this st ch sales projection master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _stChSalesProjectionMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _stChSalesProjectionMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stChSalesProjectionMaster.isNew();
	}

	@Override
	public void persist() {
		_stChSalesProjectionMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stChSalesProjectionMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the calculation periods of this st ch sales projection master.
	*
	* @param calculationPeriods the calculation periods of this st ch sales projection master
	*/
	@Override
	public void setCalculationPeriods(java.lang.String calculationPeriods) {
		_stChSalesProjectionMaster.setCalculationPeriods(calculationPeriods);
	}

	/**
	* Sets whether this st ch sales projection master is check record.
	*
	* @param checkRecord the check record of this st ch sales projection master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_stChSalesProjectionMaster.setCheckRecord(checkRecord);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stChSalesProjectionMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stChSalesProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stChSalesProjectionMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st ch sales projection master.
	*
	* @param lastModifiedDate the last modified date of this st ch sales projection master
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stChSalesProjectionMaster.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the methodology of this st ch sales projection master.
	*
	* @param methodology the methodology of this st ch sales projection master
	*/
	@Override
	public void setMethodology(java.lang.String methodology) {
		_stChSalesProjectionMaster.setMethodology(methodology);
	}

	@Override
	public void setNew(boolean n) {
		_stChSalesProjectionMaster.setNew(n);
	}

	/**
	* Sets the primary key of this st ch sales projection master.
	*
	* @param primaryKey the primary key of this st ch sales projection master
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StChSalesProjectionMasterPK primaryKey) {
		_stChSalesProjectionMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stChSalesProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st ch sales projection master.
	*
	* @param projectionDetailsSid the projection details sid of this st ch sales projection master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stChSalesProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the session ID of this st ch sales projection master.
	*
	* @param sessionId the session ID of this st ch sales projection master
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stChSalesProjectionMaster.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st ch sales projection master.
	*
	* @param userId the user ID of this st ch sales projection master
	*/
	@Override
	public void setUserId(int userId) {
		_stChSalesProjectionMaster.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StChSalesProjectionMaster> toCacheModel() {
		return _stChSalesProjectionMaster.toCacheModel();
	}

	@Override
	public StChSalesProjectionMaster toEscapedModel() {
		return new StChSalesProjectionMasterWrapper(_stChSalesProjectionMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stChSalesProjectionMaster.toString();
	}

	@Override
	public StChSalesProjectionMaster toUnescapedModel() {
		return new StChSalesProjectionMasterWrapper(_stChSalesProjectionMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stChSalesProjectionMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChSalesProjectionMasterWrapper)) {
			return false;
		}

		StChSalesProjectionMasterWrapper stChSalesProjectionMasterWrapper = (StChSalesProjectionMasterWrapper)obj;

		if (Objects.equals(_stChSalesProjectionMaster,
					stChSalesProjectionMasterWrapper._stChSalesProjectionMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public StChSalesProjectionMaster getWrappedModel() {
		return _stChSalesProjectionMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stChSalesProjectionMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stChSalesProjectionMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stChSalesProjectionMaster.resetOriginalValues();
	}

	private final StChSalesProjectionMaster _stChSalesProjectionMaster;
}