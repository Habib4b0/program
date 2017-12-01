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
 * This class is a wrapper for {@link StChProjectionDiscount}.
 * </p>
 *
 * @author
 * @see StChProjectionDiscount
 * @generated
 */
@ProviderType
public class StChProjectionDiscountWrapper implements StChProjectionDiscount,
	ModelWrapper<StChProjectionDiscount> {
	public StChProjectionDiscountWrapper(
		StChProjectionDiscount stChProjectionDiscount) {
		_stChProjectionDiscount = stChProjectionDiscount;
	}

	@Override
	public Class<?> getModelClass() {
		return StChProjectionDiscount.class;
	}

	@Override
	public String getModelClassName() {
		return StChProjectionDiscount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("adjustmentMethodology", getAdjustmentMethodology());
		attributes.put("productGrowth", getProductGrowth());
		attributes.put("projectionRate", getProjectionRate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("userId", getUserId());
		attributes.put("accountGrowth", getAccountGrowth());
		attributes.put("discountAmount", getDiscountAmount());
		attributes.put("discountRate", getDiscountRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("adjustmentBasis", getAdjustmentBasis());
		attributes.put("sessionId", getSessionId());
		attributes.put("adjustmentValue", getAdjustmentValue());
		attributes.put("adjustmentType", getAdjustmentType());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("projectionSales", getProjectionSales());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		String adjustmentMethodology = (String)attributes.get(
				"adjustmentMethodology");

		if (adjustmentMethodology != null) {
			setAdjustmentMethodology(adjustmentMethodology);
		}

		Double productGrowth = (Double)attributes.get("productGrowth");

		if (productGrowth != null) {
			setProductGrowth(productGrowth);
		}

		Double projectionRate = (Double)attributes.get("projectionRate");

		if (projectionRate != null) {
			setProjectionRate(projectionRate);
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

		Double accountGrowth = (Double)attributes.get("accountGrowth");

		if (accountGrowth != null) {
			setAccountGrowth(accountGrowth);
		}

		Double discountAmount = (Double)attributes.get("discountAmount");

		if (discountAmount != null) {
			setDiscountAmount(discountAmount);
		}

		Double discountRate = (Double)attributes.get("discountRate");

		if (discountRate != null) {
			setDiscountRate(discountRate);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		String adjustmentBasis = (String)attributes.get("adjustmentBasis");

		if (adjustmentBasis != null) {
			setAdjustmentBasis(adjustmentBasis);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double adjustmentValue = (Double)attributes.get("adjustmentValue");

		if (adjustmentValue != null) {
			setAdjustmentValue(adjustmentValue);
		}

		String adjustmentType = (String)attributes.get("adjustmentType");

		if (adjustmentType != null) {
			setAdjustmentType(adjustmentType);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		Double projectionSales = (Double)attributes.get("projectionSales");

		if (projectionSales != null) {
			setProjectionSales(projectionSales);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StChProjectionDiscountWrapper((StChProjectionDiscount)_stChProjectionDiscount.clone());
	}

	@Override
	public int compareTo(StChProjectionDiscount stChProjectionDiscount) {
		return _stChProjectionDiscount.compareTo(stChProjectionDiscount);
	}

	/**
	* Returns the account growth of this st ch projection discount.
	*
	* @return the account growth of this st ch projection discount
	*/
	@Override
	public double getAccountGrowth() {
		return _stChProjectionDiscount.getAccountGrowth();
	}

	/**
	* Returns the adjustment basis of this st ch projection discount.
	*
	* @return the adjustment basis of this st ch projection discount
	*/
	@Override
	public java.lang.String getAdjustmentBasis() {
		return _stChProjectionDiscount.getAdjustmentBasis();
	}

	/**
	* Returns the adjustment methodology of this st ch projection discount.
	*
	* @return the adjustment methodology of this st ch projection discount
	*/
	@Override
	public java.lang.String getAdjustmentMethodology() {
		return _stChProjectionDiscount.getAdjustmentMethodology();
	}

	/**
	* Returns the adjustment type of this st ch projection discount.
	*
	* @return the adjustment type of this st ch projection discount
	*/
	@Override
	public java.lang.String getAdjustmentType() {
		return _stChProjectionDiscount.getAdjustmentType();
	}

	/**
	* Returns the adjustment value of this st ch projection discount.
	*
	* @return the adjustment value of this st ch projection discount
	*/
	@Override
	public double getAdjustmentValue() {
		return _stChProjectionDiscount.getAdjustmentValue();
	}

	/**
	* Returns the discount amount of this st ch projection discount.
	*
	* @return the discount amount of this st ch projection discount
	*/
	@Override
	public double getDiscountAmount() {
		return _stChProjectionDiscount.getDiscountAmount();
	}

	/**
	* Returns the discount rate of this st ch projection discount.
	*
	* @return the discount rate of this st ch projection discount
	*/
	@Override
	public double getDiscountRate() {
		return _stChProjectionDiscount.getDiscountRate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stChProjectionDiscount.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st ch projection discount.
	*
	* @return the last modified date of this st ch projection discount
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stChProjectionDiscount.getLastModifiedDate();
	}

	/**
	* Returns the period sid of this st ch projection discount.
	*
	* @return the period sid of this st ch projection discount
	*/
	@Override
	public int getPeriodSid() {
		return _stChProjectionDiscount.getPeriodSid();
	}

	/**
	* Returns the primary key of this st ch projection discount.
	*
	* @return the primary key of this st ch projection discount
	*/
	@Override
	public com.stpl.app.service.persistence.StChProjectionDiscountPK getPrimaryKey() {
		return _stChProjectionDiscount.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stChProjectionDiscount.getPrimaryKeyObj();
	}

	/**
	* Returns the product growth of this st ch projection discount.
	*
	* @return the product growth of this st ch projection discount
	*/
	@Override
	public double getProductGrowth() {
		return _stChProjectionDiscount.getProductGrowth();
	}

	/**
	* Returns the projection details sid of this st ch projection discount.
	*
	* @return the projection details sid of this st ch projection discount
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stChProjectionDiscount.getProjectionDetailsSid();
	}

	/**
	* Returns the projection rate of this st ch projection discount.
	*
	* @return the projection rate of this st ch projection discount
	*/
	@Override
	public double getProjectionRate() {
		return _stChProjectionDiscount.getProjectionRate();
	}

	/**
	* Returns the projection sales of this st ch projection discount.
	*
	* @return the projection sales of this st ch projection discount
	*/
	@Override
	public double getProjectionSales() {
		return _stChProjectionDiscount.getProjectionSales();
	}

	/**
	* Returns the rs model sid of this st ch projection discount.
	*
	* @return the rs model sid of this st ch projection discount
	*/
	@Override
	public int getRsModelSid() {
		return _stChProjectionDiscount.getRsModelSid();
	}

	/**
	* Returns the session ID of this st ch projection discount.
	*
	* @return the session ID of this st ch projection discount
	*/
	@Override
	public int getSessionId() {
		return _stChProjectionDiscount.getSessionId();
	}

	/**
	* Returns the user ID of this st ch projection discount.
	*
	* @return the user ID of this st ch projection discount
	*/
	@Override
	public int getUserId() {
		return _stChProjectionDiscount.getUserId();
	}

	@Override
	public int hashCode() {
		return _stChProjectionDiscount.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stChProjectionDiscount.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stChProjectionDiscount.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stChProjectionDiscount.isNew();
	}

	@Override
	public void persist() {
		_stChProjectionDiscount.persist();
	}

	/**
	* Sets the account growth of this st ch projection discount.
	*
	* @param accountGrowth the account growth of this st ch projection discount
	*/
	@Override
	public void setAccountGrowth(double accountGrowth) {
		_stChProjectionDiscount.setAccountGrowth(accountGrowth);
	}

	/**
	* Sets the adjustment basis of this st ch projection discount.
	*
	* @param adjustmentBasis the adjustment basis of this st ch projection discount
	*/
	@Override
	public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
		_stChProjectionDiscount.setAdjustmentBasis(adjustmentBasis);
	}

	/**
	* Sets the adjustment methodology of this st ch projection discount.
	*
	* @param adjustmentMethodology the adjustment methodology of this st ch projection discount
	*/
	@Override
	public void setAdjustmentMethodology(java.lang.String adjustmentMethodology) {
		_stChProjectionDiscount.setAdjustmentMethodology(adjustmentMethodology);
	}

	/**
	* Sets the adjustment type of this st ch projection discount.
	*
	* @param adjustmentType the adjustment type of this st ch projection discount
	*/
	@Override
	public void setAdjustmentType(java.lang.String adjustmentType) {
		_stChProjectionDiscount.setAdjustmentType(adjustmentType);
	}

	/**
	* Sets the adjustment value of this st ch projection discount.
	*
	* @param adjustmentValue the adjustment value of this st ch projection discount
	*/
	@Override
	public void setAdjustmentValue(double adjustmentValue) {
		_stChProjectionDiscount.setAdjustmentValue(adjustmentValue);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stChProjectionDiscount.setCachedModel(cachedModel);
	}

	/**
	* Sets the discount amount of this st ch projection discount.
	*
	* @param discountAmount the discount amount of this st ch projection discount
	*/
	@Override
	public void setDiscountAmount(double discountAmount) {
		_stChProjectionDiscount.setDiscountAmount(discountAmount);
	}

	/**
	* Sets the discount rate of this st ch projection discount.
	*
	* @param discountRate the discount rate of this st ch projection discount
	*/
	@Override
	public void setDiscountRate(double discountRate) {
		_stChProjectionDiscount.setDiscountRate(discountRate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stChProjectionDiscount.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stChProjectionDiscount.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stChProjectionDiscount.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st ch projection discount.
	*
	* @param lastModifiedDate the last modified date of this st ch projection discount
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stChProjectionDiscount.setLastModifiedDate(lastModifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stChProjectionDiscount.setNew(n);
	}

	/**
	* Sets the period sid of this st ch projection discount.
	*
	* @param periodSid the period sid of this st ch projection discount
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_stChProjectionDiscount.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this st ch projection discount.
	*
	* @param primaryKey the primary key of this st ch projection discount
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StChProjectionDiscountPK primaryKey) {
		_stChProjectionDiscount.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stChProjectionDiscount.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product growth of this st ch projection discount.
	*
	* @param productGrowth the product growth of this st ch projection discount
	*/
	@Override
	public void setProductGrowth(double productGrowth) {
		_stChProjectionDiscount.setProductGrowth(productGrowth);
	}

	/**
	* Sets the projection details sid of this st ch projection discount.
	*
	* @param projectionDetailsSid the projection details sid of this st ch projection discount
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stChProjectionDiscount.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection rate of this st ch projection discount.
	*
	* @param projectionRate the projection rate of this st ch projection discount
	*/
	@Override
	public void setProjectionRate(double projectionRate) {
		_stChProjectionDiscount.setProjectionRate(projectionRate);
	}

	/**
	* Sets the projection sales of this st ch projection discount.
	*
	* @param projectionSales the projection sales of this st ch projection discount
	*/
	@Override
	public void setProjectionSales(double projectionSales) {
		_stChProjectionDiscount.setProjectionSales(projectionSales);
	}

	/**
	* Sets the rs model sid of this st ch projection discount.
	*
	* @param rsModelSid the rs model sid of this st ch projection discount
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_stChProjectionDiscount.setRsModelSid(rsModelSid);
	}

	/**
	* Sets the session ID of this st ch projection discount.
	*
	* @param sessionId the session ID of this st ch projection discount
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stChProjectionDiscount.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st ch projection discount.
	*
	* @param userId the user ID of this st ch projection discount
	*/
	@Override
	public void setUserId(int userId) {
		_stChProjectionDiscount.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StChProjectionDiscount> toCacheModel() {
		return _stChProjectionDiscount.toCacheModel();
	}

	@Override
	public StChProjectionDiscount toEscapedModel() {
		return new StChProjectionDiscountWrapper(_stChProjectionDiscount.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stChProjectionDiscount.toString();
	}

	@Override
	public StChProjectionDiscount toUnescapedModel() {
		return new StChProjectionDiscountWrapper(_stChProjectionDiscount.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stChProjectionDiscount.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChProjectionDiscountWrapper)) {
			return false;
		}

		StChProjectionDiscountWrapper stChProjectionDiscountWrapper = (StChProjectionDiscountWrapper)obj;

		if (Objects.equals(_stChProjectionDiscount,
					stChProjectionDiscountWrapper._stChProjectionDiscount)) {
			return true;
		}

		return false;
	}

	@Override
	public StChProjectionDiscount getWrappedModel() {
		return _stChProjectionDiscount;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stChProjectionDiscount.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stChProjectionDiscount.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stChProjectionDiscount.resetOriginalValues();
	}

	private final StChProjectionDiscount _stChProjectionDiscount;
}