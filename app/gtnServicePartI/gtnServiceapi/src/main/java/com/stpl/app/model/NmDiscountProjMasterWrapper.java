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
 * This class is a wrapper for {@link NmDiscountProjMaster}.
 * </p>
 *
 * @author
 * @see NmDiscountProjMaster
 * @generated
 */
@ProviderType
public class NmDiscountProjMasterWrapper implements NmDiscountProjMaster,
	ModelWrapper<NmDiscountProjMaster> {
	public NmDiscountProjMasterWrapper(
		NmDiscountProjMaster nmDiscountProjMaster) {
		_nmDiscountProjMaster = nmDiscountProjMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return NmDiscountProjMaster.class;
	}

	@Override
	public String getModelClassName() {
		return NmDiscountProjMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkRecord", getCheckRecord());
		attributes.put("discountId", getDiscountId());
		attributes.put("userGroup", getUserGroup());
		attributes.put("priceGroupType", getPriceGroupType());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("netFlag", getNetFlag());
		attributes.put("methodology", getMethodology());
		attributes.put("discountName", getDiscountName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String discountId = (String)attributes.get("discountId");

		if (discountId != null) {
			setDiscountId(discountId);
		}

		String userGroup = (String)attributes.get("userGroup");

		if (userGroup != null) {
			setUserGroup(userGroup);
		}

		String priceGroupType = (String)attributes.get("priceGroupType");

		if (priceGroupType != null) {
			setPriceGroupType(priceGroupType);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		String netFlag = (String)attributes.get("netFlag");

		if (netFlag != null) {
			setNetFlag(netFlag);
		}

		String methodology = (String)attributes.get("methodology");

		if (methodology != null) {
			setMethodology(methodology);
		}

		String discountName = (String)attributes.get("discountName");

		if (discountName != null) {
			setDiscountName(discountName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NmDiscountProjMasterWrapper((NmDiscountProjMaster)_nmDiscountProjMaster.clone());
	}

	@Override
	public int compareTo(NmDiscountProjMaster nmDiscountProjMaster) {
		return _nmDiscountProjMaster.compareTo(nmDiscountProjMaster);
	}

	/**
	* Returns the check record of this nm discount proj master.
	*
	* @return the check record of this nm discount proj master
	*/
	@Override
	public boolean getCheckRecord() {
		return _nmDiscountProjMaster.getCheckRecord();
	}

	/**
	* Returns the discount ID of this nm discount proj master.
	*
	* @return the discount ID of this nm discount proj master
	*/
	@Override
	public java.lang.String getDiscountId() {
		return _nmDiscountProjMaster.getDiscountId();
	}

	/**
	* Returns the discount name of this nm discount proj master.
	*
	* @return the discount name of this nm discount proj master
	*/
	@Override
	public java.lang.String getDiscountName() {
		return _nmDiscountProjMaster.getDiscountName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmDiscountProjMaster.getExpandoBridge();
	}

	/**
	* Returns the methodology of this nm discount proj master.
	*
	* @return the methodology of this nm discount proj master
	*/
	@Override
	public java.lang.String getMethodology() {
		return _nmDiscountProjMaster.getMethodology();
	}

	/**
	* Returns the net flag of this nm discount proj master.
	*
	* @return the net flag of this nm discount proj master
	*/
	@Override
	public java.lang.String getNetFlag() {
		return _nmDiscountProjMaster.getNetFlag();
	}

	/**
	* Returns the price group type of this nm discount proj master.
	*
	* @return the price group type of this nm discount proj master
	*/
	@Override
	public java.lang.String getPriceGroupType() {
		return _nmDiscountProjMaster.getPriceGroupType();
	}

	/**
	* Returns the primary key of this nm discount proj master.
	*
	* @return the primary key of this nm discount proj master
	*/
	@Override
	public int getPrimaryKey() {
		return _nmDiscountProjMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmDiscountProjMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this nm discount proj master.
	*
	* @return the projection details sid of this nm discount proj master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _nmDiscountProjMaster.getProjectionDetailsSid();
	}

	/**
	* Returns the user group of this nm discount proj master.
	*
	* @return the user group of this nm discount proj master
	*/
	@Override
	public java.lang.String getUserGroup() {
		return _nmDiscountProjMaster.getUserGroup();
	}

	@Override
	public int hashCode() {
		return _nmDiscountProjMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmDiscountProjMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this nm discount proj master is check record.
	*
	* @return <code>true</code> if this nm discount proj master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _nmDiscountProjMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmDiscountProjMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmDiscountProjMaster.isNew();
	}

	@Override
	public void persist() {
		_nmDiscountProjMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmDiscountProjMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this nm discount proj master is check record.
	*
	* @param checkRecord the check record of this nm discount proj master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_nmDiscountProjMaster.setCheckRecord(checkRecord);
	}

	/**
	* Sets the discount ID of this nm discount proj master.
	*
	* @param discountId the discount ID of this nm discount proj master
	*/
	@Override
	public void setDiscountId(java.lang.String discountId) {
		_nmDiscountProjMaster.setDiscountId(discountId);
	}

	/**
	* Sets the discount name of this nm discount proj master.
	*
	* @param discountName the discount name of this nm discount proj master
	*/
	@Override
	public void setDiscountName(java.lang.String discountName) {
		_nmDiscountProjMaster.setDiscountName(discountName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmDiscountProjMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmDiscountProjMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmDiscountProjMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the methodology of this nm discount proj master.
	*
	* @param methodology the methodology of this nm discount proj master
	*/
	@Override
	public void setMethodology(java.lang.String methodology) {
		_nmDiscountProjMaster.setMethodology(methodology);
	}

	/**
	* Sets the net flag of this nm discount proj master.
	*
	* @param netFlag the net flag of this nm discount proj master
	*/
	@Override
	public void setNetFlag(java.lang.String netFlag) {
		_nmDiscountProjMaster.setNetFlag(netFlag);
	}

	@Override
	public void setNew(boolean n) {
		_nmDiscountProjMaster.setNew(n);
	}

	/**
	* Sets the price group type of this nm discount proj master.
	*
	* @param priceGroupType the price group type of this nm discount proj master
	*/
	@Override
	public void setPriceGroupType(java.lang.String priceGroupType) {
		_nmDiscountProjMaster.setPriceGroupType(priceGroupType);
	}

	/**
	* Sets the primary key of this nm discount proj master.
	*
	* @param primaryKey the primary key of this nm discount proj master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_nmDiscountProjMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmDiscountProjMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this nm discount proj master.
	*
	* @param projectionDetailsSid the projection details sid of this nm discount proj master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_nmDiscountProjMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the user group of this nm discount proj master.
	*
	* @param userGroup the user group of this nm discount proj master
	*/
	@Override
	public void setUserGroup(java.lang.String userGroup) {
		_nmDiscountProjMaster.setUserGroup(userGroup);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmDiscountProjMaster> toCacheModel() {
		return _nmDiscountProjMaster.toCacheModel();
	}

	@Override
	public NmDiscountProjMaster toEscapedModel() {
		return new NmDiscountProjMasterWrapper(_nmDiscountProjMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmDiscountProjMaster.toString();
	}

	@Override
	public NmDiscountProjMaster toUnescapedModel() {
		return new NmDiscountProjMasterWrapper(_nmDiscountProjMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmDiscountProjMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmDiscountProjMasterWrapper)) {
			return false;
		}

		NmDiscountProjMasterWrapper nmDiscountProjMasterWrapper = (NmDiscountProjMasterWrapper)obj;

		if (Objects.equals(_nmDiscountProjMaster,
					nmDiscountProjMasterWrapper._nmDiscountProjMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public NmDiscountProjMaster getWrappedModel() {
		return _nmDiscountProjMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmDiscountProjMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmDiscountProjMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmDiscountProjMaster.resetOriginalValues();
	}

	private final NmDiscountProjMaster _nmDiscountProjMaster;
}