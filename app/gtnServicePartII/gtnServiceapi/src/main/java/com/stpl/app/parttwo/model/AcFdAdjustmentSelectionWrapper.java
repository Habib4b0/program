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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AcFdAdjustmentSelection}.
 * </p>
 *
 * @author
 * @see AcFdAdjustmentSelection
 * @generated
 */
@ProviderType
public class AcFdAdjustmentSelectionWrapper implements AcFdAdjustmentSelection,
	ModelWrapper<AcFdAdjustmentSelection> {
	public AcFdAdjustmentSelectionWrapper(
		AcFdAdjustmentSelection acFdAdjustmentSelection) {
		_acFdAdjustmentSelection = acFdAdjustmentSelection;
	}

	@Override
	public Class<?> getModelClass() {
		return AcFdAdjustmentSelection.class;
	}

	@Override
	public String getModelClassName() {
		return AcFdAdjustmentSelection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("methodologyStartDate", getMethodologyStartDate());
		attributes.put("allocationMethod", getAllocationMethod());
		attributes.put("startDate", getStartDate());
		attributes.put("totalFixedDollarAdj", getTotalFixedDollarAdj());
		attributes.put("calculationFlag", getCalculationFlag());
		attributes.put("rateCorrection", getRateCorrection());
		attributes.put("businessDays", getBusinessDays());
		attributes.put("glImpactDate", getGlImpactDate());
		attributes.put("salesBasis", getSalesBasis());
		attributes.put("releaseType", getReleaseType());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("releaseAmount", getReleaseAmount());
		attributes.put("suggestedAdj", getSuggestedAdj());
		attributes.put("methodologyEndDate", getMethodologyEndDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String methodologyStartDate = (String)attributes.get(
				"methodologyStartDate");

		if (methodologyStartDate != null) {
			setMethodologyStartDate(methodologyStartDate);
		}

		Integer allocationMethod = (Integer)attributes.get("allocationMethod");

		if (allocationMethod != null) {
			setAllocationMethod(allocationMethod);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Double totalFixedDollarAdj = (Double)attributes.get(
				"totalFixedDollarAdj");

		if (totalFixedDollarAdj != null) {
			setTotalFixedDollarAdj(totalFixedDollarAdj);
		}

		Boolean calculationFlag = (Boolean)attributes.get("calculationFlag");

		if (calculationFlag != null) {
			setCalculationFlag(calculationFlag);
		}

		Double rateCorrection = (Double)attributes.get("rateCorrection");

		if (rateCorrection != null) {
			setRateCorrection(rateCorrection);
		}

		Integer businessDays = (Integer)attributes.get("businessDays");

		if (businessDays != null) {
			setBusinessDays(businessDays);
		}

		Date glImpactDate = (Date)attributes.get("glImpactDate");

		if (glImpactDate != null) {
			setGlImpactDate(glImpactDate);
		}

		Integer salesBasis = (Integer)attributes.get("salesBasis");

		if (salesBasis != null) {
			setSalesBasis(salesBasis);
		}

		Boolean releaseType = (Boolean)attributes.get("releaseType");

		if (releaseType != null) {
			setReleaseType(releaseType);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		Double releaseAmount = (Double)attributes.get("releaseAmount");

		if (releaseAmount != null) {
			setReleaseAmount(releaseAmount);
		}

		Double suggestedAdj = (Double)attributes.get("suggestedAdj");

		if (suggestedAdj != null) {
			setSuggestedAdj(suggestedAdj);
		}

		String methodologyEndDate = (String)attributes.get("methodologyEndDate");

		if (methodologyEndDate != null) {
			setMethodologyEndDate(methodologyEndDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AcFdAdjustmentSelectionWrapper((AcFdAdjustmentSelection)_acFdAdjustmentSelection.clone());
	}

	@Override
	public int compareTo(AcFdAdjustmentSelection acFdAdjustmentSelection) {
		return _acFdAdjustmentSelection.compareTo(acFdAdjustmentSelection);
	}

	/**
	* Returns the acc closure master sid of this ac fd adjustment selection.
	*
	* @return the acc closure master sid of this ac fd adjustment selection
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _acFdAdjustmentSelection.getAccClosureMasterSid();
	}

	/**
	* Returns the allocation method of this ac fd adjustment selection.
	*
	* @return the allocation method of this ac fd adjustment selection
	*/
	@Override
	public int getAllocationMethod() {
		return _acFdAdjustmentSelection.getAllocationMethod();
	}

	/**
	* Returns the business days of this ac fd adjustment selection.
	*
	* @return the business days of this ac fd adjustment selection
	*/
	@Override
	public int getBusinessDays() {
		return _acFdAdjustmentSelection.getBusinessDays();
	}

	/**
	* Returns the calculation flag of this ac fd adjustment selection.
	*
	* @return the calculation flag of this ac fd adjustment selection
	*/
	@Override
	public boolean getCalculationFlag() {
		return _acFdAdjustmentSelection.getCalculationFlag();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _acFdAdjustmentSelection.getExpandoBridge();
	}

	/**
	* Returns the gl impact date of this ac fd adjustment selection.
	*
	* @return the gl impact date of this ac fd adjustment selection
	*/
	@Override
	public Date getGlImpactDate() {
		return _acFdAdjustmentSelection.getGlImpactDate();
	}

	/**
	* Returns the methodology end date of this ac fd adjustment selection.
	*
	* @return the methodology end date of this ac fd adjustment selection
	*/
	@Override
	public java.lang.String getMethodologyEndDate() {
		return _acFdAdjustmentSelection.getMethodologyEndDate();
	}

	/**
	* Returns the methodology start date of this ac fd adjustment selection.
	*
	* @return the methodology start date of this ac fd adjustment selection
	*/
	@Override
	public java.lang.String getMethodologyStartDate() {
		return _acFdAdjustmentSelection.getMethodologyStartDate();
	}

	/**
	* Returns the primary key of this ac fd adjustment selection.
	*
	* @return the primary key of this ac fd adjustment selection
	*/
	@Override
	public int getPrimaryKey() {
		return _acFdAdjustmentSelection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _acFdAdjustmentSelection.getPrimaryKeyObj();
	}

	/**
	* Returns the rate correction of this ac fd adjustment selection.
	*
	* @return the rate correction of this ac fd adjustment selection
	*/
	@Override
	public double getRateCorrection() {
		return _acFdAdjustmentSelection.getRateCorrection();
	}

	/**
	* Returns the release amount of this ac fd adjustment selection.
	*
	* @return the release amount of this ac fd adjustment selection
	*/
	@Override
	public double getReleaseAmount() {
		return _acFdAdjustmentSelection.getReleaseAmount();
	}

	/**
	* Returns the release type of this ac fd adjustment selection.
	*
	* @return the release type of this ac fd adjustment selection
	*/
	@Override
	public boolean getReleaseType() {
		return _acFdAdjustmentSelection.getReleaseType();
	}

	/**
	* Returns the sales basis of this ac fd adjustment selection.
	*
	* @return the sales basis of this ac fd adjustment selection
	*/
	@Override
	public int getSalesBasis() {
		return _acFdAdjustmentSelection.getSalesBasis();
	}

	/**
	* Returns the start date of this ac fd adjustment selection.
	*
	* @return the start date of this ac fd adjustment selection
	*/
	@Override
	public Date getStartDate() {
		return _acFdAdjustmentSelection.getStartDate();
	}

	/**
	* Returns the suggested adj of this ac fd adjustment selection.
	*
	* @return the suggested adj of this ac fd adjustment selection
	*/
	@Override
	public double getSuggestedAdj() {
		return _acFdAdjustmentSelection.getSuggestedAdj();
	}

	/**
	* Returns the total fixed dollar adj of this ac fd adjustment selection.
	*
	* @return the total fixed dollar adj of this ac fd adjustment selection
	*/
	@Override
	public double getTotalFixedDollarAdj() {
		return _acFdAdjustmentSelection.getTotalFixedDollarAdj();
	}

	@Override
	public int hashCode() {
		return _acFdAdjustmentSelection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _acFdAdjustmentSelection.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ac fd adjustment selection is calculation flag.
	*
	* @return <code>true</code> if this ac fd adjustment selection is calculation flag; <code>false</code> otherwise
	*/
	@Override
	public boolean isCalculationFlag() {
		return _acFdAdjustmentSelection.isCalculationFlag();
	}

	@Override
	public boolean isEscapedModel() {
		return _acFdAdjustmentSelection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _acFdAdjustmentSelection.isNew();
	}

	/**
	* Returns <code>true</code> if this ac fd adjustment selection is release type.
	*
	* @return <code>true</code> if this ac fd adjustment selection is release type; <code>false</code> otherwise
	*/
	@Override
	public boolean isReleaseType() {
		return _acFdAdjustmentSelection.isReleaseType();
	}

	@Override
	public void persist() {
		_acFdAdjustmentSelection.persist();
	}

	/**
	* Sets the acc closure master sid of this ac fd adjustment selection.
	*
	* @param accClosureMasterSid the acc closure master sid of this ac fd adjustment selection
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_acFdAdjustmentSelection.setAccClosureMasterSid(accClosureMasterSid);
	}

	/**
	* Sets the allocation method of this ac fd adjustment selection.
	*
	* @param allocationMethod the allocation method of this ac fd adjustment selection
	*/
	@Override
	public void setAllocationMethod(int allocationMethod) {
		_acFdAdjustmentSelection.setAllocationMethod(allocationMethod);
	}

	/**
	* Sets the business days of this ac fd adjustment selection.
	*
	* @param businessDays the business days of this ac fd adjustment selection
	*/
	@Override
	public void setBusinessDays(int businessDays) {
		_acFdAdjustmentSelection.setBusinessDays(businessDays);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_acFdAdjustmentSelection.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ac fd adjustment selection is calculation flag.
	*
	* @param calculationFlag the calculation flag of this ac fd adjustment selection
	*/
	@Override
	public void setCalculationFlag(boolean calculationFlag) {
		_acFdAdjustmentSelection.setCalculationFlag(calculationFlag);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_acFdAdjustmentSelection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_acFdAdjustmentSelection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_acFdAdjustmentSelection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gl impact date of this ac fd adjustment selection.
	*
	* @param glImpactDate the gl impact date of this ac fd adjustment selection
	*/
	@Override
	public void setGlImpactDate(Date glImpactDate) {
		_acFdAdjustmentSelection.setGlImpactDate(glImpactDate);
	}

	/**
	* Sets the methodology end date of this ac fd adjustment selection.
	*
	* @param methodologyEndDate the methodology end date of this ac fd adjustment selection
	*/
	@Override
	public void setMethodologyEndDate(java.lang.String methodologyEndDate) {
		_acFdAdjustmentSelection.setMethodologyEndDate(methodologyEndDate);
	}

	/**
	* Sets the methodology start date of this ac fd adjustment selection.
	*
	* @param methodologyStartDate the methodology start date of this ac fd adjustment selection
	*/
	@Override
	public void setMethodologyStartDate(java.lang.String methodologyStartDate) {
		_acFdAdjustmentSelection.setMethodologyStartDate(methodologyStartDate);
	}

	@Override
	public void setNew(boolean n) {
		_acFdAdjustmentSelection.setNew(n);
	}

	/**
	* Sets the primary key of this ac fd adjustment selection.
	*
	* @param primaryKey the primary key of this ac fd adjustment selection
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_acFdAdjustmentSelection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_acFdAdjustmentSelection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rate correction of this ac fd adjustment selection.
	*
	* @param rateCorrection the rate correction of this ac fd adjustment selection
	*/
	@Override
	public void setRateCorrection(double rateCorrection) {
		_acFdAdjustmentSelection.setRateCorrection(rateCorrection);
	}

	/**
	* Sets the release amount of this ac fd adjustment selection.
	*
	* @param releaseAmount the release amount of this ac fd adjustment selection
	*/
	@Override
	public void setReleaseAmount(double releaseAmount) {
		_acFdAdjustmentSelection.setReleaseAmount(releaseAmount);
	}

	/**
	* Sets whether this ac fd adjustment selection is release type.
	*
	* @param releaseType the release type of this ac fd adjustment selection
	*/
	@Override
	public void setReleaseType(boolean releaseType) {
		_acFdAdjustmentSelection.setReleaseType(releaseType);
	}

	/**
	* Sets the sales basis of this ac fd adjustment selection.
	*
	* @param salesBasis the sales basis of this ac fd adjustment selection
	*/
	@Override
	public void setSalesBasis(int salesBasis) {
		_acFdAdjustmentSelection.setSalesBasis(salesBasis);
	}

	/**
	* Sets the start date of this ac fd adjustment selection.
	*
	* @param startDate the start date of this ac fd adjustment selection
	*/
	@Override
	public void setStartDate(Date startDate) {
		_acFdAdjustmentSelection.setStartDate(startDate);
	}

	/**
	* Sets the suggested adj of this ac fd adjustment selection.
	*
	* @param suggestedAdj the suggested adj of this ac fd adjustment selection
	*/
	@Override
	public void setSuggestedAdj(double suggestedAdj) {
		_acFdAdjustmentSelection.setSuggestedAdj(suggestedAdj);
	}

	/**
	* Sets the total fixed dollar adj of this ac fd adjustment selection.
	*
	* @param totalFixedDollarAdj the total fixed dollar adj of this ac fd adjustment selection
	*/
	@Override
	public void setTotalFixedDollarAdj(double totalFixedDollarAdj) {
		_acFdAdjustmentSelection.setTotalFixedDollarAdj(totalFixedDollarAdj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AcFdAdjustmentSelection> toCacheModel() {
		return _acFdAdjustmentSelection.toCacheModel();
	}

	@Override
	public AcFdAdjustmentSelection toEscapedModel() {
		return new AcFdAdjustmentSelectionWrapper(_acFdAdjustmentSelection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _acFdAdjustmentSelection.toString();
	}

	@Override
	public AcFdAdjustmentSelection toUnescapedModel() {
		return new AcFdAdjustmentSelectionWrapper(_acFdAdjustmentSelection.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _acFdAdjustmentSelection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AcFdAdjustmentSelectionWrapper)) {
			return false;
		}

		AcFdAdjustmentSelectionWrapper acFdAdjustmentSelectionWrapper = (AcFdAdjustmentSelectionWrapper)obj;

		if (Objects.equals(_acFdAdjustmentSelection,
					acFdAdjustmentSelectionWrapper._acFdAdjustmentSelection)) {
			return true;
		}

		return false;
	}

	@Override
	public AcFdAdjustmentSelection getWrappedModel() {
		return _acFdAdjustmentSelection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _acFdAdjustmentSelection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _acFdAdjustmentSelection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_acFdAdjustmentSelection.resetOriginalValues();
	}

	private final AcFdAdjustmentSelection _acFdAdjustmentSelection;
}