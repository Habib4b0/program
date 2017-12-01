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
 * This class is a wrapper for {@link MSupplementalDiscProj}.
 * </p>
 *
 * @author
 * @see MSupplementalDiscProj
 * @generated
 */
@ProviderType
public class MSupplementalDiscProjWrapper implements MSupplementalDiscProj,
	ModelWrapper<MSupplementalDiscProj> {
	public MSupplementalDiscProjWrapper(
		MSupplementalDiscProj mSupplementalDiscProj) {
		_mSupplementalDiscProj = mSupplementalDiscProj;
	}

	@Override
	public Class<?> getModelClass() {
		return MSupplementalDiscProj.class;
	}

	@Override
	public String getModelClassName() {
		return MSupplementalDiscProj.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("methodology", getMethodology());
		attributes.put("projectionRate", getProjectionRate());
		attributes.put("parity", getParity());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("discountRate1", getDiscountRate1());
		attributes.put("parityReference", getParityReference());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("discountRate2", getDiscountRate2());
		attributes.put("parityDiscount", getParityDiscount());
		attributes.put("projectionSales", getProjectionSales());
		attributes.put("contractPrice", getContractPrice());
		attributes.put("access", getAccess());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String methodology = (String)attributes.get("methodology");

		if (methodology != null) {
			setMethodology(methodology);
		}

		Double projectionRate = (Double)attributes.get("projectionRate");

		if (projectionRate != null) {
			setProjectionRate(projectionRate);
		}

		Boolean parity = (Boolean)attributes.get("parity");

		if (parity != null) {
			setParity(parity);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Double discountRate1 = (Double)attributes.get("discountRate1");

		if (discountRate1 != null) {
			setDiscountRate1(discountRate1);
		}

		String parityReference = (String)attributes.get("parityReference");

		if (parityReference != null) {
			setParityReference(parityReference);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double discountRate2 = (Double)attributes.get("discountRate2");

		if (discountRate2 != null) {
			setDiscountRate2(discountRate2);
		}

		Double parityDiscount = (Double)attributes.get("parityDiscount");

		if (parityDiscount != null) {
			setParityDiscount(parityDiscount);
		}

		Double projectionSales = (Double)attributes.get("projectionSales");

		if (projectionSales != null) {
			setProjectionSales(projectionSales);
		}

		Double contractPrice = (Double)attributes.get("contractPrice");

		if (contractPrice != null) {
			setContractPrice(contractPrice);
		}

		String access = (String)attributes.get("access");

		if (access != null) {
			setAccess(access);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MSupplementalDiscProjWrapper((MSupplementalDiscProj)_mSupplementalDiscProj.clone());
	}

	@Override
	public int compareTo(MSupplementalDiscProj mSupplementalDiscProj) {
		return _mSupplementalDiscProj.compareTo(mSupplementalDiscProj);
	}

	/**
	* Returns the access of this m supplemental disc proj.
	*
	* @return the access of this m supplemental disc proj
	*/
	@Override
	public java.lang.String getAccess() {
		return _mSupplementalDiscProj.getAccess();
	}

	/**
	* Returns the contract price of this m supplemental disc proj.
	*
	* @return the contract price of this m supplemental disc proj
	*/
	@Override
	public double getContractPrice() {
		return _mSupplementalDiscProj.getContractPrice();
	}

	/**
	* Returns the discount rate1 of this m supplemental disc proj.
	*
	* @return the discount rate1 of this m supplemental disc proj
	*/
	@Override
	public double getDiscountRate1() {
		return _mSupplementalDiscProj.getDiscountRate1();
	}

	/**
	* Returns the discount rate2 of this m supplemental disc proj.
	*
	* @return the discount rate2 of this m supplemental disc proj
	*/
	@Override
	public double getDiscountRate2() {
		return _mSupplementalDiscProj.getDiscountRate2();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mSupplementalDiscProj.getExpandoBridge();
	}

	/**
	* Returns the methodology of this m supplemental disc proj.
	*
	* @return the methodology of this m supplemental disc proj
	*/
	@Override
	public java.lang.String getMethodology() {
		return _mSupplementalDiscProj.getMethodology();
	}

	/**
	* Returns the parity of this m supplemental disc proj.
	*
	* @return the parity of this m supplemental disc proj
	*/
	@Override
	public boolean getParity() {
		return _mSupplementalDiscProj.getParity();
	}

	/**
	* Returns the parity discount of this m supplemental disc proj.
	*
	* @return the parity discount of this m supplemental disc proj
	*/
	@Override
	public double getParityDiscount() {
		return _mSupplementalDiscProj.getParityDiscount();
	}

	/**
	* Returns the parity reference of this m supplemental disc proj.
	*
	* @return the parity reference of this m supplemental disc proj
	*/
	@Override
	public java.lang.String getParityReference() {
		return _mSupplementalDiscProj.getParityReference();
	}

	/**
	* Returns the period sid of this m supplemental disc proj.
	*
	* @return the period sid of this m supplemental disc proj
	*/
	@Override
	public int getPeriodSid() {
		return _mSupplementalDiscProj.getPeriodSid();
	}

	/**
	* Returns the primary key of this m supplemental disc proj.
	*
	* @return the primary key of this m supplemental disc proj
	*/
	@Override
	public int getPrimaryKey() {
		return _mSupplementalDiscProj.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mSupplementalDiscProj.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this m supplemental disc proj.
	*
	* @return the projection details sid of this m supplemental disc proj
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _mSupplementalDiscProj.getProjectionDetailsSid();
	}

	/**
	* Returns the projection rate of this m supplemental disc proj.
	*
	* @return the projection rate of this m supplemental disc proj
	*/
	@Override
	public double getProjectionRate() {
		return _mSupplementalDiscProj.getProjectionRate();
	}

	/**
	* Returns the projection sales of this m supplemental disc proj.
	*
	* @return the projection sales of this m supplemental disc proj
	*/
	@Override
	public double getProjectionSales() {
		return _mSupplementalDiscProj.getProjectionSales();
	}

	@Override
	public int hashCode() {
		return _mSupplementalDiscProj.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mSupplementalDiscProj.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mSupplementalDiscProj.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mSupplementalDiscProj.isNew();
	}

	/**
	* Returns <code>true</code> if this m supplemental disc proj is parity.
	*
	* @return <code>true</code> if this m supplemental disc proj is parity; <code>false</code> otherwise
	*/
	@Override
	public boolean isParity() {
		return _mSupplementalDiscProj.isParity();
	}

	@Override
	public void persist() {
		_mSupplementalDiscProj.persist();
	}

	/**
	* Sets the access of this m supplemental disc proj.
	*
	* @param access the access of this m supplemental disc proj
	*/
	@Override
	public void setAccess(java.lang.String access) {
		_mSupplementalDiscProj.setAccess(access);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mSupplementalDiscProj.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract price of this m supplemental disc proj.
	*
	* @param contractPrice the contract price of this m supplemental disc proj
	*/
	@Override
	public void setContractPrice(double contractPrice) {
		_mSupplementalDiscProj.setContractPrice(contractPrice);
	}

	/**
	* Sets the discount rate1 of this m supplemental disc proj.
	*
	* @param discountRate1 the discount rate1 of this m supplemental disc proj
	*/
	@Override
	public void setDiscountRate1(double discountRate1) {
		_mSupplementalDiscProj.setDiscountRate1(discountRate1);
	}

	/**
	* Sets the discount rate2 of this m supplemental disc proj.
	*
	* @param discountRate2 the discount rate2 of this m supplemental disc proj
	*/
	@Override
	public void setDiscountRate2(double discountRate2) {
		_mSupplementalDiscProj.setDiscountRate2(discountRate2);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mSupplementalDiscProj.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mSupplementalDiscProj.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mSupplementalDiscProj.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the methodology of this m supplemental disc proj.
	*
	* @param methodology the methodology of this m supplemental disc proj
	*/
	@Override
	public void setMethodology(java.lang.String methodology) {
		_mSupplementalDiscProj.setMethodology(methodology);
	}

	@Override
	public void setNew(boolean n) {
		_mSupplementalDiscProj.setNew(n);
	}

	/**
	* Sets whether this m supplemental disc proj is parity.
	*
	* @param parity the parity of this m supplemental disc proj
	*/
	@Override
	public void setParity(boolean parity) {
		_mSupplementalDiscProj.setParity(parity);
	}

	/**
	* Sets the parity discount of this m supplemental disc proj.
	*
	* @param parityDiscount the parity discount of this m supplemental disc proj
	*/
	@Override
	public void setParityDiscount(double parityDiscount) {
		_mSupplementalDiscProj.setParityDiscount(parityDiscount);
	}

	/**
	* Sets the parity reference of this m supplemental disc proj.
	*
	* @param parityReference the parity reference of this m supplemental disc proj
	*/
	@Override
	public void setParityReference(java.lang.String parityReference) {
		_mSupplementalDiscProj.setParityReference(parityReference);
	}

	/**
	* Sets the period sid of this m supplemental disc proj.
	*
	* @param periodSid the period sid of this m supplemental disc proj
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_mSupplementalDiscProj.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this m supplemental disc proj.
	*
	* @param primaryKey the primary key of this m supplemental disc proj
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_mSupplementalDiscProj.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mSupplementalDiscProj.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this m supplemental disc proj.
	*
	* @param projectionDetailsSid the projection details sid of this m supplemental disc proj
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_mSupplementalDiscProj.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection rate of this m supplemental disc proj.
	*
	* @param projectionRate the projection rate of this m supplemental disc proj
	*/
	@Override
	public void setProjectionRate(double projectionRate) {
		_mSupplementalDiscProj.setProjectionRate(projectionRate);
	}

	/**
	* Sets the projection sales of this m supplemental disc proj.
	*
	* @param projectionSales the projection sales of this m supplemental disc proj
	*/
	@Override
	public void setProjectionSales(double projectionSales) {
		_mSupplementalDiscProj.setProjectionSales(projectionSales);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MSupplementalDiscProj> toCacheModel() {
		return _mSupplementalDiscProj.toCacheModel();
	}

	@Override
	public MSupplementalDiscProj toEscapedModel() {
		return new MSupplementalDiscProjWrapper(_mSupplementalDiscProj.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mSupplementalDiscProj.toString();
	}

	@Override
	public MSupplementalDiscProj toUnescapedModel() {
		return new MSupplementalDiscProjWrapper(_mSupplementalDiscProj.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _mSupplementalDiscProj.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSupplementalDiscProjWrapper)) {
			return false;
		}

		MSupplementalDiscProjWrapper mSupplementalDiscProjWrapper = (MSupplementalDiscProjWrapper)obj;

		if (Objects.equals(_mSupplementalDiscProj,
					mSupplementalDiscProjWrapper._mSupplementalDiscProj)) {
			return true;
		}

		return false;
	}

	@Override
	public MSupplementalDiscProj getWrappedModel() {
		return _mSupplementalDiscProj;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mSupplementalDiscProj.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mSupplementalDiscProj.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mSupplementalDiscProj.resetOriginalValues();
	}

	private final MSupplementalDiscProj _mSupplementalDiscProj;
}