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
 * This class is a wrapper for {@link DeductionCalendarDetails}.
 * </p>
 *
 * @author
 * @see DeductionCalendarDetails
 * @generated
 */
@ProviderType
public class DeductionCalendarDetailsWrapper implements DeductionCalendarDetails,
	ModelWrapper<DeductionCalendarDetails> {
	public DeductionCalendarDetailsWrapper(
		DeductionCalendarDetails deductionCalendarDetails) {
		_deductionCalendarDetails = deductionCalendarDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return DeductionCalendarDetails.class;
	}

	@Override
	public String getModelClassName() {
		return DeductionCalendarDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("deductionCalendarMasterSid",
			getDeductionCalendarMasterSid());
		attributes.put("adjustmentBasis", getAdjustmentBasis());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("adjustmentValue", getAdjustmentValue());
		attributes.put("adjustmentAllocationMethodology",
			getAdjustmentAllocationMethodology());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("discountAmount", getDiscountAmount());
		attributes.put("adjustmentVariable", getAdjustmentVariable());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("adjustmentType", getAdjustmentType());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer deductionCalendarMasterSid = (Integer)attributes.get(
				"deductionCalendarMasterSid");

		if (deductionCalendarMasterSid != null) {
			setDeductionCalendarMasterSid(deductionCalendarMasterSid);
		}

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

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String adjustmentType = (String)attributes.get("adjustmentType");

		if (adjustmentType != null) {
			setAdjustmentType(adjustmentType);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new DeductionCalendarDetailsWrapper((DeductionCalendarDetails)_deductionCalendarDetails.clone());
	}

	@Override
	public int compareTo(DeductionCalendarDetails deductionCalendarDetails) {
		return _deductionCalendarDetails.compareTo(deductionCalendarDetails);
	}

	/**
	* Returns the adjustment allocation methodology of this deduction calendar details.
	*
	* @return the adjustment allocation methodology of this deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentAllocationMethodology() {
		return _deductionCalendarDetails.getAdjustmentAllocationMethodology();
	}

	/**
	* Returns the adjustment basis of this deduction calendar details.
	*
	* @return the adjustment basis of this deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentBasis() {
		return _deductionCalendarDetails.getAdjustmentBasis();
	}

	/**
	* Returns the adjustment type of this deduction calendar details.
	*
	* @return the adjustment type of this deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentType() {
		return _deductionCalendarDetails.getAdjustmentType();
	}

	/**
	* Returns the adjustment value of this deduction calendar details.
	*
	* @return the adjustment value of this deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentValue() {
		return _deductionCalendarDetails.getAdjustmentValue();
	}

	/**
	* Returns the adjustment variable of this deduction calendar details.
	*
	* @return the adjustment variable of this deduction calendar details
	*/
	@Override
	public java.lang.String getAdjustmentVariable() {
		return _deductionCalendarDetails.getAdjustmentVariable();
	}

	/**
	* Returns the check record of this deduction calendar details.
	*
	* @return the check record of this deduction calendar details
	*/
	@Override
	public boolean getCheckRecord() {
		return _deductionCalendarDetails.getCheckRecord();
	}

	/**
	* Returns the company master sid of this deduction calendar details.
	*
	* @return the company master sid of this deduction calendar details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _deductionCalendarDetails.getCompanyMasterSid();
	}

	/**
	* Returns the deduction calendar master sid of this deduction calendar details.
	*
	* @return the deduction calendar master sid of this deduction calendar details
	*/
	@Override
	public int getDeductionCalendarMasterSid() {
		return _deductionCalendarDetails.getDeductionCalendarMasterSid();
	}

	/**
	* Returns the discount amount of this deduction calendar details.
	*
	* @return the discount amount of this deduction calendar details
	*/
	@Override
	public int getDiscountAmount() {
		return _deductionCalendarDetails.getDiscountAmount();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deductionCalendarDetails.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this deduction calendar details.
	*
	* @return the item master sid of this deduction calendar details
	*/
	@Override
	public int getItemMasterSid() {
		return _deductionCalendarDetails.getItemMasterSid();
	}

	/**
	* Returns the period sid of this deduction calendar details.
	*
	* @return the period sid of this deduction calendar details
	*/
	@Override
	public int getPeriodSid() {
		return _deductionCalendarDetails.getPeriodSid();
	}

	/**
	* Returns the primary key of this deduction calendar details.
	*
	* @return the primary key of this deduction calendar details
	*/
	@Override
	public com.stpl.app.service.persistence.DeductionCalendarDetailsPK getPrimaryKey() {
		return _deductionCalendarDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deductionCalendarDetails.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _deductionCalendarDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deductionCalendarDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this deduction calendar details is check record.
	*
	* @return <code>true</code> if this deduction calendar details is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _deductionCalendarDetails.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _deductionCalendarDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _deductionCalendarDetails.isNew();
	}

	@Override
	public void persist() {
		_deductionCalendarDetails.persist();
	}

	/**
	* Sets the adjustment allocation methodology of this deduction calendar details.
	*
	* @param adjustmentAllocationMethodology the adjustment allocation methodology of this deduction calendar details
	*/
	@Override
	public void setAdjustmentAllocationMethodology(
		java.lang.String adjustmentAllocationMethodology) {
		_deductionCalendarDetails.setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
	}

	/**
	* Sets the adjustment basis of this deduction calendar details.
	*
	* @param adjustmentBasis the adjustment basis of this deduction calendar details
	*/
	@Override
	public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
		_deductionCalendarDetails.setAdjustmentBasis(adjustmentBasis);
	}

	/**
	* Sets the adjustment type of this deduction calendar details.
	*
	* @param adjustmentType the adjustment type of this deduction calendar details
	*/
	@Override
	public void setAdjustmentType(java.lang.String adjustmentType) {
		_deductionCalendarDetails.setAdjustmentType(adjustmentType);
	}

	/**
	* Sets the adjustment value of this deduction calendar details.
	*
	* @param adjustmentValue the adjustment value of this deduction calendar details
	*/
	@Override
	public void setAdjustmentValue(java.lang.String adjustmentValue) {
		_deductionCalendarDetails.setAdjustmentValue(adjustmentValue);
	}

	/**
	* Sets the adjustment variable of this deduction calendar details.
	*
	* @param adjustmentVariable the adjustment variable of this deduction calendar details
	*/
	@Override
	public void setAdjustmentVariable(java.lang.String adjustmentVariable) {
		_deductionCalendarDetails.setAdjustmentVariable(adjustmentVariable);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deductionCalendarDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this deduction calendar details is check record.
	*
	* @param checkRecord the check record of this deduction calendar details
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_deductionCalendarDetails.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company master sid of this deduction calendar details.
	*
	* @param companyMasterSid the company master sid of this deduction calendar details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_deductionCalendarDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the deduction calendar master sid of this deduction calendar details.
	*
	* @param deductionCalendarMasterSid the deduction calendar master sid of this deduction calendar details
	*/
	@Override
	public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
		_deductionCalendarDetails.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
	}

	/**
	* Sets the discount amount of this deduction calendar details.
	*
	* @param discountAmount the discount amount of this deduction calendar details
	*/
	@Override
	public void setDiscountAmount(int discountAmount) {
		_deductionCalendarDetails.setDiscountAmount(discountAmount);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deductionCalendarDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deductionCalendarDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deductionCalendarDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this deduction calendar details.
	*
	* @param itemMasterSid the item master sid of this deduction calendar details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_deductionCalendarDetails.setItemMasterSid(itemMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_deductionCalendarDetails.setNew(n);
	}

	/**
	* Sets the period sid of this deduction calendar details.
	*
	* @param periodSid the period sid of this deduction calendar details
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_deductionCalendarDetails.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this deduction calendar details.
	*
	* @param primaryKey the primary key of this deduction calendar details
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.DeductionCalendarDetailsPK primaryKey) {
		_deductionCalendarDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deductionCalendarDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DeductionCalendarDetails> toCacheModel() {
		return _deductionCalendarDetails.toCacheModel();
	}

	@Override
	public DeductionCalendarDetails toEscapedModel() {
		return new DeductionCalendarDetailsWrapper(_deductionCalendarDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _deductionCalendarDetails.toString();
	}

	@Override
	public DeductionCalendarDetails toUnescapedModel() {
		return new DeductionCalendarDetailsWrapper(_deductionCalendarDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _deductionCalendarDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionCalendarDetailsWrapper)) {
			return false;
		}

		DeductionCalendarDetailsWrapper deductionCalendarDetailsWrapper = (DeductionCalendarDetailsWrapper)obj;

		if (Objects.equals(_deductionCalendarDetails,
					deductionCalendarDetailsWrapper._deductionCalendarDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public DeductionCalendarDetails getWrappedModel() {
		return _deductionCalendarDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deductionCalendarDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deductionCalendarDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deductionCalendarDetails.resetOriginalValues();
	}

	private final DeductionCalendarDetails _deductionCalendarDetails;
}