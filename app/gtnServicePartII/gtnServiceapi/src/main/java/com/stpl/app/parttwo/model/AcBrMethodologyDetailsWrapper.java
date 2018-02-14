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
 * This class is a wrapper for {@link AcBrMethodologyDetails}.
 * </p>
 *
 * @author
 * @see AcBrMethodologyDetails
 * @generated
 */
@ProviderType
public class AcBrMethodologyDetailsWrapper implements AcBrMethodologyDetails,
	ModelWrapper<AcBrMethodologyDetails> {
	public AcBrMethodologyDetailsWrapper(
		AcBrMethodologyDetails acBrMethodologyDetails) {
		_acBrMethodologyDetails = acBrMethodologyDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return AcBrMethodologyDetails.class;
	}

	@Override
	public String getModelClassName() {
		return AcBrMethodologyDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("salesGrowthRate", getSalesGrowthRate());
		attributes.put("methodologyStartDate", getMethodologyStartDate());
		attributes.put("frequency", getFrequency());
		attributes.put("calculationFlag", getCalculationFlag());
		attributes.put("provisionGrowthRate", getProvisionGrowthRate());
		attributes.put("salesBasis", getSalesBasis());
		attributes.put("acBrMethodologyDetailsSid",
			getAcBrMethodologyDetailsSid());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("methodologyEndDate", getMethodologyEndDate());
		attributes.put("methodologyValue", getMethodologyValue());
		attributes.put("dampingFactor", getDampingFactor());
		attributes.put("methodologyName", getMethodologyName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double salesGrowthRate = (Double)attributes.get("salesGrowthRate");

		if (salesGrowthRate != null) {
			setSalesGrowthRate(salesGrowthRate);
		}

		Date methodologyStartDate = (Date)attributes.get("methodologyStartDate");

		if (methodologyStartDate != null) {
			setMethodologyStartDate(methodologyStartDate);
		}

		String frequency = (String)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		Boolean calculationFlag = (Boolean)attributes.get("calculationFlag");

		if (calculationFlag != null) {
			setCalculationFlag(calculationFlag);
		}

		Double provisionGrowthRate = (Double)attributes.get(
				"provisionGrowthRate");

		if (provisionGrowthRate != null) {
			setProvisionGrowthRate(provisionGrowthRate);
		}

		Integer salesBasis = (Integer)attributes.get("salesBasis");

		if (salesBasis != null) {
			setSalesBasis(salesBasis);
		}

		Integer acBrMethodologyDetailsSid = (Integer)attributes.get(
				"acBrMethodologyDetailsSid");

		if (acBrMethodologyDetailsSid != null) {
			setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		Date methodologyEndDate = (Date)attributes.get("methodologyEndDate");

		if (methodologyEndDate != null) {
			setMethodologyEndDate(methodologyEndDate);
		}

		Double methodologyValue = (Double)attributes.get("methodologyValue");

		if (methodologyValue != null) {
			setMethodologyValue(methodologyValue);
		}

		Double dampingFactor = (Double)attributes.get("dampingFactor");

		if (dampingFactor != null) {
			setDampingFactor(dampingFactor);
		}

		String methodologyName = (String)attributes.get("methodologyName");

		if (methodologyName != null) {
			setMethodologyName(methodologyName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AcBrMethodologyDetailsWrapper((AcBrMethodologyDetails)_acBrMethodologyDetails.clone());
	}

	@Override
	public int compareTo(AcBrMethodologyDetails acBrMethodologyDetails) {
		return _acBrMethodologyDetails.compareTo(acBrMethodologyDetails);
	}

	/**
	* Returns the ac br methodology details sid of this ac br methodology details.
	*
	* @return the ac br methodology details sid of this ac br methodology details
	*/
	@Override
	public int getAcBrMethodologyDetailsSid() {
		return _acBrMethodologyDetails.getAcBrMethodologyDetailsSid();
	}

	/**
	* Returns the acc closure master sid of this ac br methodology details.
	*
	* @return the acc closure master sid of this ac br methodology details
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _acBrMethodologyDetails.getAccClosureMasterSid();
	}

	/**
	* Returns the calculation flag of this ac br methodology details.
	*
	* @return the calculation flag of this ac br methodology details
	*/
	@Override
	public boolean getCalculationFlag() {
		return _acBrMethodologyDetails.getCalculationFlag();
	}

	/**
	* Returns the damping factor of this ac br methodology details.
	*
	* @return the damping factor of this ac br methodology details
	*/
	@Override
	public double getDampingFactor() {
		return _acBrMethodologyDetails.getDampingFactor();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _acBrMethodologyDetails.getExpandoBridge();
	}

	/**
	* Returns the frequency of this ac br methodology details.
	*
	* @return the frequency of this ac br methodology details
	*/
	@Override
	public java.lang.String getFrequency() {
		return _acBrMethodologyDetails.getFrequency();
	}

	/**
	* Returns the methodology end date of this ac br methodology details.
	*
	* @return the methodology end date of this ac br methodology details
	*/
	@Override
	public Date getMethodologyEndDate() {
		return _acBrMethodologyDetails.getMethodologyEndDate();
	}

	/**
	* Returns the methodology name of this ac br methodology details.
	*
	* @return the methodology name of this ac br methodology details
	*/
	@Override
	public java.lang.String getMethodologyName() {
		return _acBrMethodologyDetails.getMethodologyName();
	}

	/**
	* Returns the methodology start date of this ac br methodology details.
	*
	* @return the methodology start date of this ac br methodology details
	*/
	@Override
	public Date getMethodologyStartDate() {
		return _acBrMethodologyDetails.getMethodologyStartDate();
	}

	/**
	* Returns the methodology value of this ac br methodology details.
	*
	* @return the methodology value of this ac br methodology details
	*/
	@Override
	public double getMethodologyValue() {
		return _acBrMethodologyDetails.getMethodologyValue();
	}

	/**
	* Returns the primary key of this ac br methodology details.
	*
	* @return the primary key of this ac br methodology details
	*/
	@Override
	public int getPrimaryKey() {
		return _acBrMethodologyDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _acBrMethodologyDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the provision growth rate of this ac br methodology details.
	*
	* @return the provision growth rate of this ac br methodology details
	*/
	@Override
	public double getProvisionGrowthRate() {
		return _acBrMethodologyDetails.getProvisionGrowthRate();
	}

	/**
	* Returns the sales basis of this ac br methodology details.
	*
	* @return the sales basis of this ac br methodology details
	*/
	@Override
	public int getSalesBasis() {
		return _acBrMethodologyDetails.getSalesBasis();
	}

	/**
	* Returns the sales growth rate of this ac br methodology details.
	*
	* @return the sales growth rate of this ac br methodology details
	*/
	@Override
	public double getSalesGrowthRate() {
		return _acBrMethodologyDetails.getSalesGrowthRate();
	}

	@Override
	public int hashCode() {
		return _acBrMethodologyDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _acBrMethodologyDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ac br methodology details is calculation flag.
	*
	* @return <code>true</code> if this ac br methodology details is calculation flag; <code>false</code> otherwise
	*/
	@Override
	public boolean isCalculationFlag() {
		return _acBrMethodologyDetails.isCalculationFlag();
	}

	@Override
	public boolean isEscapedModel() {
		return _acBrMethodologyDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _acBrMethodologyDetails.isNew();
	}

	@Override
	public void persist() {
		_acBrMethodologyDetails.persist();
	}

	/**
	* Sets the ac br methodology details sid of this ac br methodology details.
	*
	* @param acBrMethodologyDetailsSid the ac br methodology details sid of this ac br methodology details
	*/
	@Override
	public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
		_acBrMethodologyDetails.setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
	}

	/**
	* Sets the acc closure master sid of this ac br methodology details.
	*
	* @param accClosureMasterSid the acc closure master sid of this ac br methodology details
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_acBrMethodologyDetails.setAccClosureMasterSid(accClosureMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_acBrMethodologyDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ac br methodology details is calculation flag.
	*
	* @param calculationFlag the calculation flag of this ac br methodology details
	*/
	@Override
	public void setCalculationFlag(boolean calculationFlag) {
		_acBrMethodologyDetails.setCalculationFlag(calculationFlag);
	}

	/**
	* Sets the damping factor of this ac br methodology details.
	*
	* @param dampingFactor the damping factor of this ac br methodology details
	*/
	@Override
	public void setDampingFactor(double dampingFactor) {
		_acBrMethodologyDetails.setDampingFactor(dampingFactor);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_acBrMethodologyDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_acBrMethodologyDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_acBrMethodologyDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the frequency of this ac br methodology details.
	*
	* @param frequency the frequency of this ac br methodology details
	*/
	@Override
	public void setFrequency(java.lang.String frequency) {
		_acBrMethodologyDetails.setFrequency(frequency);
	}

	/**
	* Sets the methodology end date of this ac br methodology details.
	*
	* @param methodologyEndDate the methodology end date of this ac br methodology details
	*/
	@Override
	public void setMethodologyEndDate(Date methodologyEndDate) {
		_acBrMethodologyDetails.setMethodologyEndDate(methodologyEndDate);
	}

	/**
	* Sets the methodology name of this ac br methodology details.
	*
	* @param methodologyName the methodology name of this ac br methodology details
	*/
	@Override
	public void setMethodologyName(java.lang.String methodologyName) {
		_acBrMethodologyDetails.setMethodologyName(methodologyName);
	}

	/**
	* Sets the methodology start date of this ac br methodology details.
	*
	* @param methodologyStartDate the methodology start date of this ac br methodology details
	*/
	@Override
	public void setMethodologyStartDate(Date methodologyStartDate) {
		_acBrMethodologyDetails.setMethodologyStartDate(methodologyStartDate);
	}

	/**
	* Sets the methodology value of this ac br methodology details.
	*
	* @param methodologyValue the methodology value of this ac br methodology details
	*/
	@Override
	public void setMethodologyValue(double methodologyValue) {
		_acBrMethodologyDetails.setMethodologyValue(methodologyValue);
	}

	@Override
	public void setNew(boolean n) {
		_acBrMethodologyDetails.setNew(n);
	}

	/**
	* Sets the primary key of this ac br methodology details.
	*
	* @param primaryKey the primary key of this ac br methodology details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_acBrMethodologyDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_acBrMethodologyDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the provision growth rate of this ac br methodology details.
	*
	* @param provisionGrowthRate the provision growth rate of this ac br methodology details
	*/
	@Override
	public void setProvisionGrowthRate(double provisionGrowthRate) {
		_acBrMethodologyDetails.setProvisionGrowthRate(provisionGrowthRate);
	}

	/**
	* Sets the sales basis of this ac br methodology details.
	*
	* @param salesBasis the sales basis of this ac br methodology details
	*/
	@Override
	public void setSalesBasis(int salesBasis) {
		_acBrMethodologyDetails.setSalesBasis(salesBasis);
	}

	/**
	* Sets the sales growth rate of this ac br methodology details.
	*
	* @param salesGrowthRate the sales growth rate of this ac br methodology details
	*/
	@Override
	public void setSalesGrowthRate(double salesGrowthRate) {
		_acBrMethodologyDetails.setSalesGrowthRate(salesGrowthRate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AcBrMethodologyDetails> toCacheModel() {
		return _acBrMethodologyDetails.toCacheModel();
	}

	@Override
	public AcBrMethodologyDetails toEscapedModel() {
		return new AcBrMethodologyDetailsWrapper(_acBrMethodologyDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _acBrMethodologyDetails.toString();
	}

	@Override
	public AcBrMethodologyDetails toUnescapedModel() {
		return new AcBrMethodologyDetailsWrapper(_acBrMethodologyDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _acBrMethodologyDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AcBrMethodologyDetailsWrapper)) {
			return false;
		}

		AcBrMethodologyDetailsWrapper acBrMethodologyDetailsWrapper = (AcBrMethodologyDetailsWrapper)obj;

		if (Objects.equals(_acBrMethodologyDetails,
					acBrMethodologyDetailsWrapper._acBrMethodologyDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public AcBrMethodologyDetails getWrappedModel() {
		return _acBrMethodologyDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _acBrMethodologyDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _acBrMethodologyDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_acBrMethodologyDetails.resetOriginalValues();
	}

	private final AcBrMethodologyDetails _acBrMethodologyDetails;
}