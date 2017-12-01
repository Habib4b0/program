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
 * This class is a wrapper for {@link StDeductionCalendarDetails}.
 * </p>
 *
 * @author
 * @see StDeductionCalendarDetails
 * @generated
 */
@ProviderType
public class StDeductionCalendarDetailsWrapper
	implements StDeductionCalendarDetails,
		ModelWrapper<StDeductionCalendarDetails> {
	public StDeductionCalendarDetailsWrapper(
		StDeductionCalendarDetails stDeductionCalendarDetails) {
		_stDeductionCalendarDetails = stDeductionCalendarDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return StDeductionCalendarDetails.class;
	}

	@Override
	public String getModelClassName() {
		return StDeductionCalendarDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("adjustmentBasis", getAdjustmentBasis());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("adjustmentValue", getAdjustmentValue());
		attributes.put("adjustmentAllocationMethodology",
			getAdjustmentAllocationMethodology());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("discountAmount", getDiscountAmount());
		attributes.put("adjustmentVariable", getAdjustmentVariable());
		attributes.put("userId", getUserId());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("adjustmentType", getAdjustmentType());
		attributes.put("sessionId", getSessionId());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String adjustmentBasis = (String)attributes.get("adjustmentBasis");

		if (adjustmentBasis != null) {
			setAdjustmentBasis(adjustmentBasis);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		String adjustmentValue = (String)attributes.get("adjustmentValue");

		if (adjustmentValue != null) {
			setAdjustmentValue(adjustmentValue);
		}

		String adjustmentAllocationMethodology = (String)attributes.get(
				"adjustmentAllocationMethodology");

		if (adjustmentAllocationMethodology != null) {
			setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		Integer discountAmount = (Integer)attributes.get("discountAmount");

		if (discountAmount != null) {
			setDiscountAmount(discountAmount);
		}

		String adjustmentVariable = (String)attributes.get("adjustmentVariable");

		if (adjustmentVariable != null) {
			setAdjustmentVariable(adjustmentVariable);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String adjustmentType = (String)attributes.get("adjustmentType");

		if (adjustmentType != null) {
			setAdjustmentType(adjustmentType);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StDeductionCalendarDetailsWrapper((StDeductionCalendarDetails)_stDeductionCalendarDetails.clone());
	}

	@Override
	public int compareTo(StDeductionCalendarDetails stDeductionCalendarDetails) {
		return _stDeductionCalendarDetails.compareTo(stDeductionCalendarDetails);
	}

	/**
	* Returns the adjustment allocation methodology of this st deduction calendar details.
	*
	* @return the adjustment allocation methodology of this st deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentAllocationMethodology() {
		return _stDeductionCalendarDetails.getAdjustmentAllocationMethodology();
	}

	/**
	* Returns the adjustment basis of this st deduction calendar details.
	*
	* @return the adjustment basis of this st deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentBasis() {
		return _stDeductionCalendarDetails.getAdjustmentBasis();
	}

	/**
	* Returns the adjustment type of this st deduction calendar details.
	*
	* @return the adjustment type of this st deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentType() {
		return _stDeductionCalendarDetails.getAdjustmentType();
	}

	/**
	* Returns the adjustment value of this st deduction calendar details.
	*
	* @return the adjustment value of this st deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentValue() {
		return _stDeductionCalendarDetails.getAdjustmentValue();
	}

	/**
	* Returns the adjustment variable of this st deduction calendar details.
	*
	* @return the adjustment variable of this st deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentVariable() {
		return _stDeductionCalendarDetails.getAdjustmentVariable();
	}

	/**
	* Returns the check record of this st deduction calendar details.
	*
	* @return the check record of this st deduction calendar details
	*/
	@Override
	public boolean getCheckRecord() {
		return _stDeductionCalendarDetails.getCheckRecord();
	}

	/**
	* Returns the company master sid of this st deduction calendar details.
	*
	* @return the company master sid of this st deduction calendar details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _stDeductionCalendarDetails.getCompanyMasterSid();
	}

	/**
	* Returns the discount amount of this st deduction calendar details.
	*
	* @return the discount amount of this st deduction calendar details
	*/
	@Override
	public int getDiscountAmount() {
		return _stDeductionCalendarDetails.getDiscountAmount();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stDeductionCalendarDetails.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this st deduction calendar details.
	*
	* @return the item master sid of this st deduction calendar details
	*/
	@Override
	public int getItemMasterSid() {
		return _stDeductionCalendarDetails.getItemMasterSid();
	}

	/**
	* Returns the period sid of this st deduction calendar details.
	*
	* @return the period sid of this st deduction calendar details
	*/
	@Override
	public int getPeriodSid() {
		return _stDeductionCalendarDetails.getPeriodSid();
	}

	/**
	* Returns the primary key of this st deduction calendar details.
	*
	* @return the primary key of this st deduction calendar details
	*/
	@Override
	public com.stpl.app.service.persistence.StDeductionCalendarDetailsPK getPrimaryKey() {
		return _stDeductionCalendarDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stDeductionCalendarDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the session ID of this st deduction calendar details.
	*
	* @return the session ID of this st deduction calendar details
	*/
	@Override
	public java.lang.String getSessionId() {
		return _stDeductionCalendarDetails.getSessionId();
	}

	/**
	* Returns the user ID of this st deduction calendar details.
	*
	* @return the user ID of this st deduction calendar details
	*/
	@Override
	public int getUserId() {
		return _stDeductionCalendarDetails.getUserId();
	}

	@Override
	public int hashCode() {
		return _stDeductionCalendarDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stDeductionCalendarDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this st deduction calendar details is check record.
	*
	* @return <code>true</code> if this st deduction calendar details is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _stDeductionCalendarDetails.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _stDeductionCalendarDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stDeductionCalendarDetails.isNew();
	}

	@Override
	public void persist() {
		_stDeductionCalendarDetails.persist();
	}

	/**
	* Sets the adjustment allocation methodology of this st deduction calendar details.
	*
	* @param adjustmentAllocationMethodology the adjustment allocation methodology of this st deduction calendar details
	*/
	@Override
	public void setAdjustmentAllocationMethodology(
		java.lang.String adjustmentAllocationMethodology) {
		_stDeductionCalendarDetails.setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
	}

	/**
	* Sets the adjustment basis of this st deduction calendar details.
	*
	* @param adjustmentBasis the adjustment basis of this st deduction calendar details
	*/
	@Override
	public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
		_stDeductionCalendarDetails.setAdjustmentBasis(adjustmentBasis);
	}

	/**
	* Sets the adjustment type of this st deduction calendar details.
	*
	* @param adjustmentType the adjustment type of this st deduction calendar details
	*/
	@Override
	public void setAdjustmentType(java.lang.String adjustmentType) {
		_stDeductionCalendarDetails.setAdjustmentType(adjustmentType);
	}

	/**
	* Sets the adjustment value of this st deduction calendar details.
	*
	* @param adjustmentValue the adjustment value of this st deduction calendar details
	*/
	@Override
	public void setAdjustmentValue(java.lang.String adjustmentValue) {
		_stDeductionCalendarDetails.setAdjustmentValue(adjustmentValue);
	}

	/**
	* Sets the adjustment variable of this st deduction calendar details.
	*
	* @param adjustmentVariable the adjustment variable of this st deduction calendar details
	*/
	@Override
	public void setAdjustmentVariable(java.lang.String adjustmentVariable) {
		_stDeductionCalendarDetails.setAdjustmentVariable(adjustmentVariable);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stDeductionCalendarDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this st deduction calendar details is check record.
	*
	* @param checkRecord the check record of this st deduction calendar details
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_stDeductionCalendarDetails.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company master sid of this st deduction calendar details.
	*
	* @param companyMasterSid the company master sid of this st deduction calendar details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_stDeductionCalendarDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the discount amount of this st deduction calendar details.
	*
	* @param discountAmount the discount amount of this st deduction calendar details
	*/
	@Override
	public void setDiscountAmount(int discountAmount) {
		_stDeductionCalendarDetails.setDiscountAmount(discountAmount);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stDeductionCalendarDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stDeductionCalendarDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stDeductionCalendarDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this st deduction calendar details.
	*
	* @param itemMasterSid the item master sid of this st deduction calendar details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_stDeductionCalendarDetails.setItemMasterSid(itemMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_stDeductionCalendarDetails.setNew(n);
	}

	/**
	* Sets the period sid of this st deduction calendar details.
	*
	* @param periodSid the period sid of this st deduction calendar details
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_stDeductionCalendarDetails.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this st deduction calendar details.
	*
	* @param primaryKey the primary key of this st deduction calendar details
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StDeductionCalendarDetailsPK primaryKey) {
		_stDeductionCalendarDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stDeductionCalendarDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this st deduction calendar details.
	*
	* @param sessionId the session ID of this st deduction calendar details
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_stDeductionCalendarDetails.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st deduction calendar details.
	*
	* @param userId the user ID of this st deduction calendar details
	*/
	@Override
	public void setUserId(int userId) {
		_stDeductionCalendarDetails.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StDeductionCalendarDetails> toCacheModel() {
		return _stDeductionCalendarDetails.toCacheModel();
	}

	@Override
	public StDeductionCalendarDetails toEscapedModel() {
		return new StDeductionCalendarDetailsWrapper(_stDeductionCalendarDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stDeductionCalendarDetails.toString();
	}

	@Override
	public StDeductionCalendarDetails toUnescapedModel() {
		return new StDeductionCalendarDetailsWrapper(_stDeductionCalendarDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stDeductionCalendarDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StDeductionCalendarDetailsWrapper)) {
			return false;
		}

		StDeductionCalendarDetailsWrapper stDeductionCalendarDetailsWrapper = (StDeductionCalendarDetailsWrapper)obj;

		if (Objects.equals(_stDeductionCalendarDetails,
					stDeductionCalendarDetailsWrapper._stDeductionCalendarDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public StDeductionCalendarDetails getWrappedModel() {
		return _stDeductionCalendarDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stDeductionCalendarDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stDeductionCalendarDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stDeductionCalendarDetails.resetOriginalValues();
	}

	private final StDeductionCalendarDetails _stDeductionCalendarDetails;
}