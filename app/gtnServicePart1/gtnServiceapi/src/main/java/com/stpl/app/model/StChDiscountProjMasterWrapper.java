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
 * This class is a wrapper for {@link StChDiscountProjMaster}.
 * </p>
 *
 * @author
 * @see StChDiscountProjMaster
 * @generated
 */
@ProviderType
public class StChDiscountProjMasterWrapper implements StChDiscountProjMaster,
	ModelWrapper<StChDiscountProjMaster> {
	public StChDiscountProjMasterWrapper(
		StChDiscountProjMaster stChDiscountProjMaster) {
		_stChDiscountProjMaster = stChDiscountProjMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return StChDiscountProjMaster.class;
	}

	@Override
	public String getModelClassName() {
		return StChDiscountProjMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkRecord", getCheckRecord());
		attributes.put("selectedPeriods", getSelectedPeriods());
		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("priceGroupType", getPriceGroupType());
		attributes.put("userId", getUserId());
		attributes.put("netFlag", getNetFlag());
		attributes.put("projectedType", getProjectedType());
		attributes.put("baselinePeriods", getBaselinePeriods());
		attributes.put("sessionId", getSessionId());
		attributes.put("methodology", getMethodology());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("discountType", getDiscountType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String selectedPeriods = (String)attributes.get("selectedPeriods");

		if (selectedPeriods != null) {
			setSelectedPeriods(selectedPeriods);
		}

		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		String priceGroupType = (String)attributes.get("priceGroupType");

		if (priceGroupType != null) {
			setPriceGroupType(priceGroupType);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String netFlag = (String)attributes.get("netFlag");

		if (netFlag != null) {
			setNetFlag(netFlag);
		}

		String projectedType = (String)attributes.get("projectedType");

		if (projectedType != null) {
			setProjectedType(projectedType);
		}

		String baselinePeriods = (String)attributes.get("baselinePeriods");

		if (baselinePeriods != null) {
			setBaselinePeriods(baselinePeriods);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String methodology = (String)attributes.get("methodology");

		if (methodology != null) {
			setMethodology(methodology);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		String discountType = (String)attributes.get("discountType");

		if (discountType != null) {
			setDiscountType(discountType);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StChDiscountProjMasterWrapper((StChDiscountProjMaster)_stChDiscountProjMaster.clone());
	}

	@Override
	public int compareTo(StChDiscountProjMaster stChDiscountProjMaster) {
		return _stChDiscountProjMaster.compareTo(stChDiscountProjMaster);
	}

	/**
	* Returns the baseline periods of this st ch discount proj master.
	*
	* @return the baseline periods of this st ch discount proj master
	*/
	@Override
	public java.lang.String getBaselinePeriods() {
		return _stChDiscountProjMaster.getBaselinePeriods();
	}

	/**
	* Returns the check record of this st ch discount proj master.
	*
	* @return the check record of this st ch discount proj master
	*/
	@Override
	public boolean getCheckRecord() {
		return _stChDiscountProjMaster.getCheckRecord();
	}

	/**
	* Returns the discount type of this st ch discount proj master.
	*
	* @return the discount type of this st ch discount proj master
	*/
	@Override
	public java.lang.String getDiscountType() {
		return _stChDiscountProjMaster.getDiscountType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stChDiscountProjMaster.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st ch discount proj master.
	*
	* @return the last modified date of this st ch discount proj master
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stChDiscountProjMaster.getLastModifiedDate();
	}

	/**
	* Returns the methodology of this st ch discount proj master.
	*
	* @return the methodology of this st ch discount proj master
	*/
	@Override
	public java.lang.String getMethodology() {
		return _stChDiscountProjMaster.getMethodology();
	}

	/**
	* Returns the net flag of this st ch discount proj master.
	*
	* @return the net flag of this st ch discount proj master
	*/
	@Override
	public java.lang.String getNetFlag() {
		return _stChDiscountProjMaster.getNetFlag();
	}

	/**
	* Returns the price group type of this st ch discount proj master.
	*
	* @return the price group type of this st ch discount proj master
	*/
	@Override
	public java.lang.String getPriceGroupType() {
		return _stChDiscountProjMaster.getPriceGroupType();
	}

	/**
	* Returns the primary key of this st ch discount proj master.
	*
	* @return the primary key of this st ch discount proj master
	*/
	@Override
	public com.stpl.app.service.persistence.StChDiscountProjMasterPK getPrimaryKey() {
		return _stChDiscountProjMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stChDiscountProjMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projected type of this st ch discount proj master.
	*
	* @return the projected type of this st ch discount proj master
	*/
	@Override
	public java.lang.String getProjectedType() {
		return _stChDiscountProjMaster.getProjectedType();
	}

	/**
	* Returns the projection details sid of this st ch discount proj master.
	*
	* @return the projection details sid of this st ch discount proj master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stChDiscountProjMaster.getProjectionDetailsSid();
	}

	/**
	* Returns the rs model sid of this st ch discount proj master.
	*
	* @return the rs model sid of this st ch discount proj master
	*/
	@Override
	public int getRsModelSid() {
		return _stChDiscountProjMaster.getRsModelSid();
	}

	/**
	* Returns the selected periods of this st ch discount proj master.
	*
	* @return the selected periods of this st ch discount proj master
	*/
	@Override
	public java.lang.String getSelectedPeriods() {
		return _stChDiscountProjMaster.getSelectedPeriods();
	}

	/**
	* Returns the session ID of this st ch discount proj master.
	*
	* @return the session ID of this st ch discount proj master
	*/
	@Override
	public int getSessionId() {
		return _stChDiscountProjMaster.getSessionId();
	}

	/**
	* Returns the user ID of this st ch discount proj master.
	*
	* @return the user ID of this st ch discount proj master
	*/
	@Override
	public int getUserId() {
		return _stChDiscountProjMaster.getUserId();
	}

	@Override
	public int hashCode() {
		return _stChDiscountProjMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stChDiscountProjMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this st ch discount proj master is check record.
	*
	* @return <code>true</code> if this st ch discount proj master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _stChDiscountProjMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _stChDiscountProjMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stChDiscountProjMaster.isNew();
	}

	@Override
	public void persist() {
		_stChDiscountProjMaster.persist();
	}

	/**
	* Sets the baseline periods of this st ch discount proj master.
	*
	* @param baselinePeriods the baseline periods of this st ch discount proj master
	*/
	@Override
	public void setBaselinePeriods(java.lang.String baselinePeriods) {
		_stChDiscountProjMaster.setBaselinePeriods(baselinePeriods);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stChDiscountProjMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this st ch discount proj master is check record.
	*
	* @param checkRecord the check record of this st ch discount proj master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_stChDiscountProjMaster.setCheckRecord(checkRecord);
	}

	/**
	* Sets the discount type of this st ch discount proj master.
	*
	* @param discountType the discount type of this st ch discount proj master
	*/
	@Override
	public void setDiscountType(java.lang.String discountType) {
		_stChDiscountProjMaster.setDiscountType(discountType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stChDiscountProjMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stChDiscountProjMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stChDiscountProjMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st ch discount proj master.
	*
	* @param lastModifiedDate the last modified date of this st ch discount proj master
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stChDiscountProjMaster.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the methodology of this st ch discount proj master.
	*
	* @param methodology the methodology of this st ch discount proj master
	*/
	@Override
	public void setMethodology(java.lang.String methodology) {
		_stChDiscountProjMaster.setMethodology(methodology);
	}

	/**
	* Sets the net flag of this st ch discount proj master.
	*
	* @param netFlag the net flag of this st ch discount proj master
	*/
	@Override
	public void setNetFlag(java.lang.String netFlag) {
		_stChDiscountProjMaster.setNetFlag(netFlag);
	}

	@Override
	public void setNew(boolean n) {
		_stChDiscountProjMaster.setNew(n);
	}

	/**
	* Sets the price group type of this st ch discount proj master.
	*
	* @param priceGroupType the price group type of this st ch discount proj master
	*/
	@Override
	public void setPriceGroupType(java.lang.String priceGroupType) {
		_stChDiscountProjMaster.setPriceGroupType(priceGroupType);
	}

	/**
	* Sets the primary key of this st ch discount proj master.
	*
	* @param primaryKey the primary key of this st ch discount proj master
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StChDiscountProjMasterPK primaryKey) {
		_stChDiscountProjMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stChDiscountProjMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projected type of this st ch discount proj master.
	*
	* @param projectedType the projected type of this st ch discount proj master
	*/
	@Override
	public void setProjectedType(java.lang.String projectedType) {
		_stChDiscountProjMaster.setProjectedType(projectedType);
	}

	/**
	* Sets the projection details sid of this st ch discount proj master.
	*
	* @param projectionDetailsSid the projection details sid of this st ch discount proj master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stChDiscountProjMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the rs model sid of this st ch discount proj master.
	*
	* @param rsModelSid the rs model sid of this st ch discount proj master
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_stChDiscountProjMaster.setRsModelSid(rsModelSid);
	}

	/**
	* Sets the selected periods of this st ch discount proj master.
	*
	* @param selectedPeriods the selected periods of this st ch discount proj master
	*/
	@Override
	public void setSelectedPeriods(java.lang.String selectedPeriods) {
		_stChDiscountProjMaster.setSelectedPeriods(selectedPeriods);
	}

	/**
	* Sets the session ID of this st ch discount proj master.
	*
	* @param sessionId the session ID of this st ch discount proj master
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stChDiscountProjMaster.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st ch discount proj master.
	*
	* @param userId the user ID of this st ch discount proj master
	*/
	@Override
	public void setUserId(int userId) {
		_stChDiscountProjMaster.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StChDiscountProjMaster> toCacheModel() {
		return _stChDiscountProjMaster.toCacheModel();
	}

	@Override
	public StChDiscountProjMaster toEscapedModel() {
		return new StChDiscountProjMasterWrapper(_stChDiscountProjMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stChDiscountProjMaster.toString();
	}

	@Override
	public StChDiscountProjMaster toUnescapedModel() {
		return new StChDiscountProjMasterWrapper(_stChDiscountProjMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stChDiscountProjMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChDiscountProjMasterWrapper)) {
			return false;
		}

		StChDiscountProjMasterWrapper stChDiscountProjMasterWrapper = (StChDiscountProjMasterWrapper)obj;

		if (Objects.equals(_stChDiscountProjMaster,
					stChDiscountProjMasterWrapper._stChDiscountProjMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public StChDiscountProjMaster getWrappedModel() {
		return _stChDiscountProjMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stChDiscountProjMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stChDiscountProjMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stChDiscountProjMaster.resetOriginalValues();
	}

	private final StChDiscountProjMaster _stChDiscountProjMaster;
}