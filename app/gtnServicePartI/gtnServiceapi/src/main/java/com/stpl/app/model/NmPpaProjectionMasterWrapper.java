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
 * This class is a wrapper for {@link NmPpaProjectionMaster}.
 * </p>
 *
 * @author
 * @see NmPpaProjectionMaster
 * @generated
 */
@ProviderType
public class NmPpaProjectionMasterWrapper implements NmPpaProjectionMaster,
	ModelWrapper<NmPpaProjectionMaster> {
	public NmPpaProjectionMasterWrapper(
		NmPpaProjectionMaster nmPpaProjectionMaster) {
		_nmPpaProjectionMaster = nmPpaProjectionMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return NmPpaProjectionMaster.class;
	}

	@Override
	public String getModelClassName() {
		return NmPpaProjectionMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkRecord", getCheckRecord());
		attributes.put("userGroup", getUserGroup());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("priceBasis", getPriceBasis());
		attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
		attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
		attributes.put("actualPriceCap", getActualPriceCap());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
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
		return new NmPpaProjectionMasterWrapper((NmPpaProjectionMaster)_nmPpaProjectionMaster.clone());
	}

	@Override
	public int compareTo(NmPpaProjectionMaster nmPpaProjectionMaster) {
		return _nmPpaProjectionMaster.compareTo(nmPpaProjectionMaster);
	}

	/**
	* Returns the actual price cap of this nm ppa projection master.
	*
	* @return the actual price cap of this nm ppa projection master
	*/
	@Override
	public double getActualPriceCap() {
		return _nmPpaProjectionMaster.getActualPriceCap();
	}

	/**
	* Returns the check record of this nm ppa projection master.
	*
	* @return the check record of this nm ppa projection master
	*/
	@Override
	public boolean getCheckRecord() {
		return _nmPpaProjectionMaster.getCheckRecord();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmPpaProjectionMaster.getExpandoBridge();
	}

	/**
	* Returns the price basis of this nm ppa projection master.
	*
	* @return the price basis of this nm ppa projection master
	*/
	@Override
	public java.lang.String getPriceBasis() {
		return _nmPpaProjectionMaster.getPriceBasis();
	}

	/**
	* Returns the price protection end date of this nm ppa projection master.
	*
	* @return the price protection end date of this nm ppa projection master
	*/
	@Override
	public Date getPriceProtectionEndDate() {
		return _nmPpaProjectionMaster.getPriceProtectionEndDate();
	}

	/**
	* Returns the price protection start date of this nm ppa projection master.
	*
	* @return the price protection start date of this nm ppa projection master
	*/
	@Override
	public Date getPriceProtectionStartDate() {
		return _nmPpaProjectionMaster.getPriceProtectionStartDate();
	}

	/**
	* Returns the primary key of this nm ppa projection master.
	*
	* @return the primary key of this nm ppa projection master
	*/
	@Override
	public int getPrimaryKey() {
		return _nmPpaProjectionMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmPpaProjectionMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this nm ppa projection master.
	*
	* @return the projection details sid of this nm ppa projection master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _nmPpaProjectionMaster.getProjectionDetailsSid();
	}

	/**
	* Returns the user group of this nm ppa projection master.
	*
	* @return the user group of this nm ppa projection master
	*/
	@Override
	public java.lang.String getUserGroup() {
		return _nmPpaProjectionMaster.getUserGroup();
	}

	@Override
	public int hashCode() {
		return _nmPpaProjectionMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmPpaProjectionMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this nm ppa projection master is check record.
	*
	* @return <code>true</code> if this nm ppa projection master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _nmPpaProjectionMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmPpaProjectionMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmPpaProjectionMaster.isNew();
	}

	@Override
	public void persist() {
		_nmPpaProjectionMaster.persist();
	}

	/**
	* Sets the actual price cap of this nm ppa projection master.
	*
	* @param actualPriceCap the actual price cap of this nm ppa projection master
	*/
	@Override
	public void setActualPriceCap(double actualPriceCap) {
		_nmPpaProjectionMaster.setActualPriceCap(actualPriceCap);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmPpaProjectionMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this nm ppa projection master is check record.
	*
	* @param checkRecord the check record of this nm ppa projection master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_nmPpaProjectionMaster.setCheckRecord(checkRecord);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmPpaProjectionMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmPpaProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmPpaProjectionMaster.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_nmPpaProjectionMaster.setNew(n);
	}

	/**
	* Sets the price basis of this nm ppa projection master.
	*
	* @param priceBasis the price basis of this nm ppa projection master
	*/
	@Override
	public void setPriceBasis(java.lang.String priceBasis) {
		_nmPpaProjectionMaster.setPriceBasis(priceBasis);
	}

	/**
	* Sets the price protection end date of this nm ppa projection master.
	*
	* @param priceProtectionEndDate the price protection end date of this nm ppa projection master
	*/
	@Override
	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_nmPpaProjectionMaster.setPriceProtectionEndDate(priceProtectionEndDate);
	}

	/**
	* Sets the price protection start date of this nm ppa projection master.
	*
	* @param priceProtectionStartDate the price protection start date of this nm ppa projection master
	*/
	@Override
	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_nmPpaProjectionMaster.setPriceProtectionStartDate(priceProtectionStartDate);
	}

	/**
	* Sets the primary key of this nm ppa projection master.
	*
	* @param primaryKey the primary key of this nm ppa projection master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_nmPpaProjectionMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmPpaProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this nm ppa projection master.
	*
	* @param projectionDetailsSid the projection details sid of this nm ppa projection master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_nmPpaProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the user group of this nm ppa projection master.
	*
	* @param userGroup the user group of this nm ppa projection master
	*/
	@Override
	public void setUserGroup(java.lang.String userGroup) {
		_nmPpaProjectionMaster.setUserGroup(userGroup);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmPpaProjectionMaster> toCacheModel() {
		return _nmPpaProjectionMaster.toCacheModel();
	}

	@Override
	public NmPpaProjectionMaster toEscapedModel() {
		return new NmPpaProjectionMasterWrapper(_nmPpaProjectionMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmPpaProjectionMaster.toString();
	}

	@Override
	public NmPpaProjectionMaster toUnescapedModel() {
		return new NmPpaProjectionMasterWrapper(_nmPpaProjectionMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmPpaProjectionMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmPpaProjectionMasterWrapper)) {
			return false;
		}

		NmPpaProjectionMasterWrapper nmPpaProjectionMasterWrapper = (NmPpaProjectionMasterWrapper)obj;

		if (Objects.equals(_nmPpaProjectionMaster,
					nmPpaProjectionMasterWrapper._nmPpaProjectionMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public NmPpaProjectionMaster getWrappedModel() {
		return _nmPpaProjectionMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmPpaProjectionMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmPpaProjectionMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmPpaProjectionMaster.resetOriginalValues();
	}

	private final NmPpaProjectionMaster _nmPpaProjectionMaster;
}