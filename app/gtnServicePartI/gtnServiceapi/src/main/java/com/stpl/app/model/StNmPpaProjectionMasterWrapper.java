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
 * This class is a wrapper for {@link StNmPpaProjectionMaster}.
 * </p>
 *
 * @author
 * @see StNmPpaProjectionMaster
 * @generated
 */
@ProviderType
public class StNmPpaProjectionMasterWrapper implements StNmPpaProjectionMaster,
	ModelWrapper<StNmPpaProjectionMaster> {
	public StNmPpaProjectionMasterWrapper(
		StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		_stNmPpaProjectionMaster = stNmPpaProjectionMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return StNmPpaProjectionMaster.class;
	}

	@Override
	public String getModelClassName() {
		return StNmPpaProjectionMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("userGroup", getUserGroup());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("userId", getUserId());
		attributes.put("sessionId", getSessionId());
		attributes.put("priceBasis", getPriceBasis());
		attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
		attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
		attributes.put("actualPriceCap", getActualPriceCap());

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

		String userGroup = (String)attributes.get("userGroup");

		if (userGroup != null) {
			setUserGroup(userGroup);
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

		String priceBasis = (String)attributes.get("priceBasis");

		if (priceBasis != null) {
			setPriceBasis(priceBasis);
		}

		Date priceProtectionEndDate = (Date)attributes.get(
				"priceProtectionEndDate");

		if (priceProtectionEndDate != null) {
			setPriceProtectionEndDate(priceProtectionEndDate);
		}

		Date priceProtectionStartDate = (Date)attributes.get(
				"priceProtectionStartDate");

		if (priceProtectionStartDate != null) {
			setPriceProtectionStartDate(priceProtectionStartDate);
		}

		Double actualPriceCap = (Double)attributes.get("actualPriceCap");

		if (actualPriceCap != null) {
			setActualPriceCap(actualPriceCap);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StNmPpaProjectionMasterWrapper((StNmPpaProjectionMaster)_stNmPpaProjectionMaster.clone());
	}

	@Override
	public int compareTo(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		return _stNmPpaProjectionMaster.compareTo(stNmPpaProjectionMaster);
	}

	/**
	* Returns the actual price cap of this st nm ppa projection master.
	*
	* @return the actual price cap of this st nm ppa projection master
	*/
	@Override
	public double getActualPriceCap() {
		return _stNmPpaProjectionMaster.getActualPriceCap();
	}

	/**
	* Returns the check record of this st nm ppa projection master.
	*
	* @return the check record of this st nm ppa projection master
	*/
	@Override
	public boolean getCheckRecord() {
		return _stNmPpaProjectionMaster.getCheckRecord();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stNmPpaProjectionMaster.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st nm ppa projection master.
	*
	* @return the last modified date of this st nm ppa projection master
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stNmPpaProjectionMaster.getLastModifiedDate();
	}

	/**
	* Returns the price basis of this st nm ppa projection master.
	*
	* @return the price basis of this st nm ppa projection master
	*/
	@Override
	public java.lang.String getPriceBasis() {
		return _stNmPpaProjectionMaster.getPriceBasis();
	}

	/**
	* Returns the price protection end date of this st nm ppa projection master.
	*
	* @return the price protection end date of this st nm ppa projection master
	*/
	@Override
	public Date getPriceProtectionEndDate() {
		return _stNmPpaProjectionMaster.getPriceProtectionEndDate();
	}

	/**
	* Returns the price protection start date of this st nm ppa projection master.
	*
	* @return the price protection start date of this st nm ppa projection master
	*/
	@Override
	public Date getPriceProtectionStartDate() {
		return _stNmPpaProjectionMaster.getPriceProtectionStartDate();
	}

	/**
	* Returns the primary key of this st nm ppa projection master.
	*
	* @return the primary key of this st nm ppa projection master
	*/
	@Override
	public com.stpl.app.service.persistence.StNmPpaProjectionMasterPK getPrimaryKey() {
		return _stNmPpaProjectionMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stNmPpaProjectionMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st nm ppa projection master.
	*
	* @return the projection details sid of this st nm ppa projection master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stNmPpaProjectionMaster.getProjectionDetailsSid();
	}

	/**
	* Returns the session ID of this st nm ppa projection master.
	*
	* @return the session ID of this st nm ppa projection master
	*/
	@Override
	public int getSessionId() {
		return _stNmPpaProjectionMaster.getSessionId();
	}

	/**
	* Returns the user group of this st nm ppa projection master.
	*
	* @return the user group of this st nm ppa projection master
	*/
	@Override
	public java.lang.String getUserGroup() {
		return _stNmPpaProjectionMaster.getUserGroup();
	}

	/**
	* Returns the user ID of this st nm ppa projection master.
	*
	* @return the user ID of this st nm ppa projection master
	*/
	@Override
	public int getUserId() {
		return _stNmPpaProjectionMaster.getUserId();
	}

	@Override
	public int hashCode() {
		return _stNmPpaProjectionMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stNmPpaProjectionMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this st nm ppa projection master is check record.
	*
	* @return <code>true</code> if this st nm ppa projection master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _stNmPpaProjectionMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _stNmPpaProjectionMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stNmPpaProjectionMaster.isNew();
	}

	@Override
	public void persist() {
		_stNmPpaProjectionMaster.persist();
	}

	/**
	* Sets the actual price cap of this st nm ppa projection master.
	*
	* @param actualPriceCap the actual price cap of this st nm ppa projection master
	*/
	@Override
	public void setActualPriceCap(double actualPriceCap) {
		_stNmPpaProjectionMaster.setActualPriceCap(actualPriceCap);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stNmPpaProjectionMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this st nm ppa projection master is check record.
	*
	* @param checkRecord the check record of this st nm ppa projection master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_stNmPpaProjectionMaster.setCheckRecord(checkRecord);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stNmPpaProjectionMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stNmPpaProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stNmPpaProjectionMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st nm ppa projection master.
	*
	* @param lastModifiedDate the last modified date of this st nm ppa projection master
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stNmPpaProjectionMaster.setLastModifiedDate(lastModifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stNmPpaProjectionMaster.setNew(n);
	}

	/**
	* Sets the price basis of this st nm ppa projection master.
	*
	* @param priceBasis the price basis of this st nm ppa projection master
	*/
	@Override
	public void setPriceBasis(java.lang.String priceBasis) {
		_stNmPpaProjectionMaster.setPriceBasis(priceBasis);
	}

	/**
	* Sets the price protection end date of this st nm ppa projection master.
	*
	* @param priceProtectionEndDate the price protection end date of this st nm ppa projection master
	*/
	@Override
	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_stNmPpaProjectionMaster.setPriceProtectionEndDate(priceProtectionEndDate);
	}

	/**
	* Sets the price protection start date of this st nm ppa projection master.
	*
	* @param priceProtectionStartDate the price protection start date of this st nm ppa projection master
	*/
	@Override
	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_stNmPpaProjectionMaster.setPriceProtectionStartDate(priceProtectionStartDate);
	}

	/**
	* Sets the primary key of this st nm ppa projection master.
	*
	* @param primaryKey the primary key of this st nm ppa projection master
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StNmPpaProjectionMasterPK primaryKey) {
		_stNmPpaProjectionMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stNmPpaProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st nm ppa projection master.
	*
	* @param projectionDetailsSid the projection details sid of this st nm ppa projection master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stNmPpaProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the session ID of this st nm ppa projection master.
	*
	* @param sessionId the session ID of this st nm ppa projection master
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stNmPpaProjectionMaster.setSessionId(sessionId);
	}

	/**
	* Sets the user group of this st nm ppa projection master.
	*
	* @param userGroup the user group of this st nm ppa projection master
	*/
	@Override
	public void setUserGroup(java.lang.String userGroup) {
		_stNmPpaProjectionMaster.setUserGroup(userGroup);
	}

	/**
	* Sets the user ID of this st nm ppa projection master.
	*
	* @param userId the user ID of this st nm ppa projection master
	*/
	@Override
	public void setUserId(int userId) {
		_stNmPpaProjectionMaster.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StNmPpaProjectionMaster> toCacheModel() {
		return _stNmPpaProjectionMaster.toCacheModel();
	}

	@Override
	public StNmPpaProjectionMaster toEscapedModel() {
		return new StNmPpaProjectionMasterWrapper(_stNmPpaProjectionMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stNmPpaProjectionMaster.toString();
	}

	@Override
	public StNmPpaProjectionMaster toUnescapedModel() {
		return new StNmPpaProjectionMasterWrapper(_stNmPpaProjectionMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stNmPpaProjectionMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmPpaProjectionMasterWrapper)) {
			return false;
		}

		StNmPpaProjectionMasterWrapper stNmPpaProjectionMasterWrapper = (StNmPpaProjectionMasterWrapper)obj;

		if (Objects.equals(_stNmPpaProjectionMaster,
					stNmPpaProjectionMasterWrapper._stNmPpaProjectionMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public StNmPpaProjectionMaster getWrappedModel() {
		return _stNmPpaProjectionMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stNmPpaProjectionMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stNmPpaProjectionMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stNmPpaProjectionMaster.resetOriginalValues();
	}

	private final StNmPpaProjectionMaster _stNmPpaProjectionMaster;
}