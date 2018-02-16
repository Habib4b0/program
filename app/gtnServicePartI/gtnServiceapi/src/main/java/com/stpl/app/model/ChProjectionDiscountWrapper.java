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
 * This class is a wrapper for {@link ChProjectionDiscount}.
 * </p>
 *
 * @author
 * @see ChProjectionDiscount
 * @generated
 */
@ProviderType
public class ChProjectionDiscountWrapper implements ChProjectionDiscount,
	ModelWrapper<ChProjectionDiscount> {
	public ChProjectionDiscountWrapper(
		ChProjectionDiscount chProjectionDiscount) {
		_chProjectionDiscount = chProjectionDiscount;
	}

	@Override
	public Class<?> getModelClass() {
		return ChProjectionDiscount.class;
	}

	@Override
	public String getModelClassName() {
		return ChProjectionDiscount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("adjustmentMethodology", getAdjustmentMethodology());
		attributes.put("productGrowth", getProductGrowth());
		attributes.put("projectionRate", getProjectionRate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("accountGrowth", getAccountGrowth());
		attributes.put("discountAmount", getDiscountAmount());
		attributes.put("discountRate", getDiscountRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("adjustmentBasis", getAdjustmentBasis());
		attributes.put("adjustmentValue", getAdjustmentValue());
		attributes.put("adjustmentType", getAdjustmentType());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("projectionSales", getProjectionSales());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
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
		return new ChProjectionDiscountWrapper((ChProjectionDiscount)_chProjectionDiscount.clone());
	}

	@Override
	public int compareTo(ChProjectionDiscount chProjectionDiscount) {
		return _chProjectionDiscount.compareTo(chProjectionDiscount);
	}

	/**
	* Returns the account growth of this ch projection discount.
	*
	* @return the account growth of this ch projection discount
	*/
	@Override
	public double getAccountGrowth() {
		return _chProjectionDiscount.getAccountGrowth();
	}

	/**
	* Returns the adjustment basis of this ch projection discount.
	*
	* @return the adjustment basis of this ch projection discount
	*/
	@Override
	public java.lang.String getAdjustmentBasis() {
		return _chProjectionDiscount.getAdjustmentBasis();
	}

	/**
	* Returns the adjustment methodology of this ch projection discount.
	*
	* @return the adjustment methodology of this ch projection discount
	*/
	@Override
	public java.lang.String getAdjustmentMethodology() {
		return _chProjectionDiscount.getAdjustmentMethodology();
	}

	/**
	* Returns the adjustment type of this ch projection discount.
	*
	* @return the adjustment type of this ch projection discount
	*/
	@Override
	public java.lang.String getAdjustmentType() {
		return _chProjectionDiscount.getAdjustmentType();
	}

	/**
	* Returns the adjustment value of this ch projection discount.
	*
	* @return the adjustment value of this ch projection discount
	*/
	@Override
	public double getAdjustmentValue() {
		return _chProjectionDiscount.getAdjustmentValue();
	}

	/**
	* Returns the discount amount of this ch projection discount.
	*
	* @return the discount amount of this ch projection discount
	*/
	@Override
	public double getDiscountAmount() {
		return _chProjectionDiscount.getDiscountAmount();
	}

	/**
	* Returns the discount rate of this ch projection discount.
	*
	* @return the discount rate of this ch projection discount
	*/
	@Override
	public double getDiscountRate() {
		return _chProjectionDiscount.getDiscountRate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _chProjectionDiscount.getExpandoBridge();
	}

	/**
	* Returns the period sid of this ch projection discount.
	*
	* @return the period sid of this ch projection discount
	*/
	@Override
	public int getPeriodSid() {
		return _chProjectionDiscount.getPeriodSid();
	}

	/**
	* Returns the primary key of this ch projection discount.
	*
	* @return the primary key of this ch projection discount
	*/
	@Override
	public com.stpl.app.service.persistence.ChProjectionDiscountPK getPrimaryKey() {
		return _chProjectionDiscount.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chProjectionDiscount.getPrimaryKeyObj();
	}

	/**
	* Returns the product growth of this ch projection discount.
	*
	* @return the product growth of this ch projection discount
	*/
	@Override
	public double getProductGrowth() {
		return _chProjectionDiscount.getProductGrowth();
	}

	/**
	* Returns the projection details sid of this ch projection discount.
	*
	* @return the projection details sid of this ch projection discount
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _chProjectionDiscount.getProjectionDetailsSid();
	}

	/**
	* Returns the projection rate of this ch projection discount.
	*
	* @return the projection rate of this ch projection discount
	*/
	@Override
	public double getProjectionRate() {
		return _chProjectionDiscount.getProjectionRate();
	}

	/**
	* Returns the projection sales of this ch projection discount.
	*
	* @return the projection sales of this ch projection discount
	*/
	@Override
	public double getProjectionSales() {
		return _chProjectionDiscount.getProjectionSales();
	}

	/**
	* Returns the rs model sid of this ch projection discount.
	*
	* @return the rs model sid of this ch projection discount
	*/
	@Override
	public int getRsModelSid() {
		return _chProjectionDiscount.getRsModelSid();
	}

	@Override
	public int hashCode() {
		return _chProjectionDiscount.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _chProjectionDiscount.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _chProjectionDiscount.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _chProjectionDiscount.isNew();
	}

	@Override
	public void persist() {
		_chProjectionDiscount.persist();
	}

	/**
	* Sets the account growth of this ch projection discount.
	*
	* @param accountGrowth the account growth of this ch projection discount
	*/
	@Override
	public void setAccountGrowth(double accountGrowth) {
		_chProjectionDiscount.setAccountGrowth(accountGrowth);
	}

	/**
	* Sets the adjustment basis of this ch projection discount.
	*
	* @param adjustmentBasis the adjustment basis of this ch projection discount
	*/
	@Override
	public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
		_chProjectionDiscount.setAdjustmentBasis(adjustmentBasis);
	}

	/**
	* Sets the adjustment methodology of this ch projection discount.
	*
	* @param adjustmentMethodology the adjustment methodology of this ch projection discount
	*/
	@Override
	public void setAdjustmentMethodology(java.lang.String adjustmentMethodology) {
		_chProjectionDiscount.setAdjustmentMethodology(adjustmentMethodology);
	}

	/**
	* Sets the adjustment type of this ch projection discount.
	*
	* @param adjustmentType the adjustment type of this ch projection discount
	*/
	@Override
	public void setAdjustmentType(java.lang.String adjustmentType) {
		_chProjectionDiscount.setAdjustmentType(adjustmentType);
	}

	/**
	* Sets the adjustment value of this ch projection discount.
	*
	* @param adjustmentValue the adjustment value of this ch projection discount
	*/
	@Override
	public void setAdjustmentValue(double adjustmentValue) {
		_chProjectionDiscount.setAdjustmentValue(adjustmentValue);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_chProjectionDiscount.setCachedModel(cachedModel);
	}

	/**
	* Sets the discount amount of this ch projection discount.
	*
	* @param discountAmount the discount amount of this ch projection discount
	*/
	@Override
	public void setDiscountAmount(double discountAmount) {
		_chProjectionDiscount.setDiscountAmount(discountAmount);
	}

	/**
	* Sets the discount rate of this ch projection discount.
	*
	* @param discountRate the discount rate of this ch projection discount
	*/
	@Override
	public void setDiscountRate(double discountRate) {
		_chProjectionDiscount.setDiscountRate(discountRate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_chProjectionDiscount.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_chProjectionDiscount.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_chProjectionDiscount.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_chProjectionDiscount.setNew(n);
	}

	/**
	* Sets the period sid of this ch projection discount.
	*
	* @param periodSid the period sid of this ch projection discount
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_chProjectionDiscount.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this ch projection discount.
	*
	* @param primaryKey the primary key of this ch projection discount
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.ChProjectionDiscountPK primaryKey) {
		_chProjectionDiscount.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_chProjectionDiscount.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product growth of this ch projection discount.
	*
	* @param productGrowth the product growth of this ch projection discount
	*/
	@Override
	public void setProductGrowth(double productGrowth) {
		_chProjectionDiscount.setProductGrowth(productGrowth);
	}

	/**
	* Sets the projection details sid of this ch projection discount.
	*
	* @param projectionDetailsSid the projection details sid of this ch projection discount
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_chProjectionDiscount.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection rate of this ch projection discount.
	*
	* @param projectionRate the projection rate of this ch projection discount
	*/
	@Override
	public void setProjectionRate(double projectionRate) {
		_chProjectionDiscount.setProjectionRate(projectionRate);
	}

	/**
	* Sets the projection sales of this ch projection discount.
	*
	* @param projectionSales the projection sales of this ch projection discount
	*/
	@Override
	public void setProjectionSales(double projectionSales) {
		_chProjectionDiscount.setProjectionSales(projectionSales);
	}

	/**
	* Sets the rs model sid of this ch projection discount.
	*
	* @param rsModelSid the rs model sid of this ch projection discount
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_chProjectionDiscount.setRsModelSid(rsModelSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ChProjectionDiscount> toCacheModel() {
		return _chProjectionDiscount.toCacheModel();
	}

	@Override
	public ChProjectionDiscount toEscapedModel() {
		return new ChProjectionDiscountWrapper(_chProjectionDiscount.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _chProjectionDiscount.toString();
	}

	@Override
	public ChProjectionDiscount toUnescapedModel() {
		return new ChProjectionDiscountWrapper(_chProjectionDiscount.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _chProjectionDiscount.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChProjectionDiscountWrapper)) {
			return false;
		}

		ChProjectionDiscountWrapper chProjectionDiscountWrapper = (ChProjectionDiscountWrapper)obj;

		if (Objects.equals(_chProjectionDiscount,
					chProjectionDiscountWrapper._chProjectionDiscount)) {
			return true;
		}

		return false;
	}

	@Override
	public ChProjectionDiscount getWrappedModel() {
		return _chProjectionDiscount;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _chProjectionDiscount.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _chProjectionDiscount.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_chProjectionDiscount.resetOriginalValues();
	}

	private final ChProjectionDiscount _chProjectionDiscount;
}