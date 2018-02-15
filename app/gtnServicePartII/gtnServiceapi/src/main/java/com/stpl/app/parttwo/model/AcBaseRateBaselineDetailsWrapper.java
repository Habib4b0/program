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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AcBaseRateBaselineDetails}.
 * </p>
 *
 * @author
 * @see AcBaseRateBaselineDetails
 * @generated
 */
@ProviderType
public class AcBaseRateBaselineDetailsWrapper
	implements AcBaseRateBaselineDetails,
		ModelWrapper<AcBaseRateBaselineDetails> {
	public AcBaseRateBaselineDetailsWrapper(
		AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
		_acBaseRateBaselineDetails = acBaseRateBaselineDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return AcBaseRateBaselineDetails.class;
	}

	@Override
	public String getModelClassName() {
		return AcBaseRateBaselineDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("periodValue", getPeriodValue());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("paymentsPeriod", getPaymentsPeriod());
		attributes.put("acBrMethodologyDetailsSid",
			getAcBrMethodologyDetailsSid());
		attributes.put("salesPeriod", getSalesPeriod());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double periodValue = (Double)attributes.get("periodValue");

		if (periodValue != null) {
			setPeriodValue(periodValue);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Boolean paymentsPeriod = (Boolean)attributes.get("paymentsPeriod");

		if (paymentsPeriod != null) {
			setPaymentsPeriod(paymentsPeriod);
		}

		Integer acBrMethodologyDetailsSid = (Integer)attributes.get(
				"acBrMethodologyDetailsSid");

		if (acBrMethodologyDetailsSid != null) {
			setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
		}

		Boolean salesPeriod = (Boolean)attributes.get("salesPeriod");

		if (salesPeriod != null) {
			setSalesPeriod(salesPeriod);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AcBaseRateBaselineDetailsWrapper((AcBaseRateBaselineDetails)_acBaseRateBaselineDetails.clone());
	}

	@Override
	public int compareTo(AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
		return _acBaseRateBaselineDetails.compareTo(acBaseRateBaselineDetails);
	}

	/**
	* Returns the ac br methodology details sid of this ac base rate baseline details.
	*
	* @return the ac br methodology details sid of this ac base rate baseline details
	*/
	@Override
	public int getAcBrMethodologyDetailsSid() {
		return _acBaseRateBaselineDetails.getAcBrMethodologyDetailsSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _acBaseRateBaselineDetails.getExpandoBridge();
	}

	/**
	* Returns the payments period of this ac base rate baseline details.
	*
	* @return the payments period of this ac base rate baseline details
	*/
	@Override
	public boolean getPaymentsPeriod() {
		return _acBaseRateBaselineDetails.getPaymentsPeriod();
	}

	/**
	* Returns the period sid of this ac base rate baseline details.
	*
	* @return the period sid of this ac base rate baseline details
	*/
	@Override
	public int getPeriodSid() {
		return _acBaseRateBaselineDetails.getPeriodSid();
	}

	/**
	* Returns the period value of this ac base rate baseline details.
	*
	* @return the period value of this ac base rate baseline details
	*/
	@Override
	public double getPeriodValue() {
		return _acBaseRateBaselineDetails.getPeriodValue();
	}

	/**
	* Returns the primary key of this ac base rate baseline details.
	*
	* @return the primary key of this ac base rate baseline details
	*/
	@Override
	public int getPrimaryKey() {
		return _acBaseRateBaselineDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _acBaseRateBaselineDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the sales period of this ac base rate baseline details.
	*
	* @return the sales period of this ac base rate baseline details
	*/
	@Override
	public boolean getSalesPeriod() {
		return _acBaseRateBaselineDetails.getSalesPeriod();
	}

	@Override
	public int hashCode() {
		return _acBaseRateBaselineDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _acBaseRateBaselineDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _acBaseRateBaselineDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _acBaseRateBaselineDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this ac base rate baseline details is payments period.
	*
	* @return <code>true</code> if this ac base rate baseline details is payments period; <code>false</code> otherwise
	*/
	@Override
	public boolean isPaymentsPeriod() {
		return _acBaseRateBaselineDetails.isPaymentsPeriod();
	}

	/**
	* Returns <code>true</code> if this ac base rate baseline details is sales period.
	*
	* @return <code>true</code> if this ac base rate baseline details is sales period; <code>false</code> otherwise
	*/
	@Override
	public boolean isSalesPeriod() {
		return _acBaseRateBaselineDetails.isSalesPeriod();
	}

	@Override
	public void persist() {
		_acBaseRateBaselineDetails.persist();
	}

	/**
	* Sets the ac br methodology details sid of this ac base rate baseline details.
	*
	* @param acBrMethodologyDetailsSid the ac br methodology details sid of this ac base rate baseline details
	*/
	@Override
	public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
		_acBaseRateBaselineDetails.setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_acBaseRateBaselineDetails.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_acBaseRateBaselineDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_acBaseRateBaselineDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_acBaseRateBaselineDetails.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_acBaseRateBaselineDetails.setNew(n);
	}

	/**
	* Sets whether this ac base rate baseline details is payments period.
	*
	* @param paymentsPeriod the payments period of this ac base rate baseline details
	*/
	@Override
	public void setPaymentsPeriod(boolean paymentsPeriod) {
		_acBaseRateBaselineDetails.setPaymentsPeriod(paymentsPeriod);
	}

	/**
	* Sets the period sid of this ac base rate baseline details.
	*
	* @param periodSid the period sid of this ac base rate baseline details
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_acBaseRateBaselineDetails.setPeriodSid(periodSid);
	}

	/**
	* Sets the period value of this ac base rate baseline details.
	*
	* @param periodValue the period value of this ac base rate baseline details
	*/
	@Override
	public void setPeriodValue(double periodValue) {
		_acBaseRateBaselineDetails.setPeriodValue(periodValue);
	}

	/**
	* Sets the primary key of this ac base rate baseline details.
	*
	* @param primaryKey the primary key of this ac base rate baseline details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_acBaseRateBaselineDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_acBaseRateBaselineDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this ac base rate baseline details is sales period.
	*
	* @param salesPeriod the sales period of this ac base rate baseline details
	*/
	@Override
	public void setSalesPeriod(boolean salesPeriod) {
		_acBaseRateBaselineDetails.setSalesPeriod(salesPeriod);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AcBaseRateBaselineDetails> toCacheModel() {
		return _acBaseRateBaselineDetails.toCacheModel();
	}

	@Override
	public AcBaseRateBaselineDetails toEscapedModel() {
		return new AcBaseRateBaselineDetailsWrapper(_acBaseRateBaselineDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _acBaseRateBaselineDetails.toString();
	}

	@Override
	public AcBaseRateBaselineDetails toUnescapedModel() {
		return new AcBaseRateBaselineDetailsWrapper(_acBaseRateBaselineDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _acBaseRateBaselineDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AcBaseRateBaselineDetailsWrapper)) {
			return false;
		}

		AcBaseRateBaselineDetailsWrapper acBaseRateBaselineDetailsWrapper = (AcBaseRateBaselineDetailsWrapper)obj;

		if (Objects.equals(_acBaseRateBaselineDetails,
					acBaseRateBaselineDetailsWrapper._acBaseRateBaselineDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public AcBaseRateBaselineDetails getWrappedModel() {
		return _acBaseRateBaselineDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _acBaseRateBaselineDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _acBaseRateBaselineDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_acBaseRateBaselineDetails.resetOriginalValues();
	}

	private final AcBaseRateBaselineDetails _acBaseRateBaselineDetails;
}