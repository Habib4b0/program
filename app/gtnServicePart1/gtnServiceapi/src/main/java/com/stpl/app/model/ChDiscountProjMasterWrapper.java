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
 * This class is a wrapper for {@link ChDiscountProjMaster}.
 * </p>
 *
 * @author
 * @see ChDiscountProjMaster
 * @generated
 */
@ProviderType
public class ChDiscountProjMasterWrapper implements ChDiscountProjMaster,
	ModelWrapper<ChDiscountProjMaster> {
	public ChDiscountProjMasterWrapper(
		ChDiscountProjMaster chDiscountProjMaster) {
		_chDiscountProjMaster = chDiscountProjMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ChDiscountProjMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ChDiscountProjMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("selectedPeriods", getSelectedPeriods());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("priceGroupType", getPriceGroupType());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("baselinePeriods", getBaselinePeriods());
		attributes.put("netFlag", getNetFlag());
		attributes.put("methodology", getMethodology());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("discountType", getDiscountType());
		attributes.put("projectedType", getProjectedType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String selectedPeriods = (String)attributes.get("selectedPeriods");

		if (selectedPeriods != null) {
			setSelectedPeriods(selectedPeriods);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
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

		String baselinePeriods = (String)attributes.get("baselinePeriods");

		if (baselinePeriods != null) {
			setBaselinePeriods(baselinePeriods);
		}

		String netFlag = (String)attributes.get("netFlag");

		if (netFlag != null) {
			setNetFlag(netFlag);
		}

		String methodology = (String)attributes.get("methodology");

		if (methodology != null) {
			setMethodology(methodology);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		String discountType = (String)attributes.get("discountType");

		if (discountType != null) {
			setDiscountType(discountType);
		}

		String projectedType = (String)attributes.get("projectedType");

		if (projectedType != null) {
			setProjectedType(projectedType);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ChDiscountProjMasterWrapper((ChDiscountProjMaster)_chDiscountProjMaster.clone());
	}

	@Override
	public int compareTo(ChDiscountProjMaster chDiscountProjMaster) {
		return _chDiscountProjMaster.compareTo(chDiscountProjMaster);
	}

	/**
	* Returns the baseline periods of this ch discount proj master.
	*
	* @return the baseline periods of this ch discount proj master
	*/
	@Override
	public java.lang.String getBaselinePeriods() {
		return _chDiscountProjMaster.getBaselinePeriods();
	}

	/**
	* Returns the check record of this ch discount proj master.
	*
	* @return the check record of this ch discount proj master
	*/
	@Override
	public boolean getCheckRecord() {
		return _chDiscountProjMaster.getCheckRecord();
	}

	/**
	* Returns the discount type of this ch discount proj master.
	*
	* @return the discount type of this ch discount proj master
	*/
	@Override
	public java.lang.String getDiscountType() {
		return _chDiscountProjMaster.getDiscountType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _chDiscountProjMaster.getExpandoBridge();
	}

	/**
	* Returns the methodology of this ch discount proj master.
	*
	* @return the methodology of this ch discount proj master
	*/
	@Override
	public java.lang.String getMethodology() {
		return _chDiscountProjMaster.getMethodology();
	}

	/**
	* Returns the net flag of this ch discount proj master.
	*
	* @return the net flag of this ch discount proj master
	*/
	@Override
	public java.lang.String getNetFlag() {
		return _chDiscountProjMaster.getNetFlag();
	}

	/**
	* Returns the price group type of this ch discount proj master.
	*
	* @return the price group type of this ch discount proj master
	*/
	@Override
	public java.lang.String getPriceGroupType() {
		return _chDiscountProjMaster.getPriceGroupType();
	}

	/**
	* Returns the primary key of this ch discount proj master.
	*
	* @return the primary key of this ch discount proj master
	*/
	@Override
	public com.stpl.app.service.persistence.ChDiscountProjMasterPK getPrimaryKey() {
		return _chDiscountProjMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chDiscountProjMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projected type of this ch discount proj master.
	*
	* @return the projected type of this ch discount proj master
	*/
	@Override
	public java.lang.String getProjectedType() {
		return _chDiscountProjMaster.getProjectedType();
	}

	/**
	* Returns the projection details sid of this ch discount proj master.
	*
	* @return the projection details sid of this ch discount proj master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _chDiscountProjMaster.getProjectionDetailsSid();
	}

	/**
	* Returns the rs model sid of this ch discount proj master.
	*
	* @return the rs model sid of this ch discount proj master
	*/
	@Override
	public int getRsModelSid() {
		return _chDiscountProjMaster.getRsModelSid();
	}

	/**
	* Returns the selected periods of this ch discount proj master.
	*
	* @return the selected periods of this ch discount proj master
	*/
	@Override
	public java.lang.String getSelectedPeriods() {
		return _chDiscountProjMaster.getSelectedPeriods();
	}

	@Override
	public int hashCode() {
		return _chDiscountProjMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _chDiscountProjMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ch discount proj master is check record.
	*
	* @return <code>true</code> if this ch discount proj master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _chDiscountProjMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _chDiscountProjMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _chDiscountProjMaster.isNew();
	}

	@Override
	public void persist() {
		_chDiscountProjMaster.persist();
	}

	/**
	* Sets the baseline periods of this ch discount proj master.
	*
	* @param baselinePeriods the baseline periods of this ch discount proj master
	*/
	@Override
	public void setBaselinePeriods(java.lang.String baselinePeriods) {
		_chDiscountProjMaster.setBaselinePeriods(baselinePeriods);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_chDiscountProjMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ch discount proj master is check record.
	*
	* @param checkRecord the check record of this ch discount proj master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_chDiscountProjMaster.setCheckRecord(checkRecord);
	}

	/**
	* Sets the discount type of this ch discount proj master.
	*
	* @param discountType the discount type of this ch discount proj master
	*/
	@Override
	public void setDiscountType(java.lang.String discountType) {
		_chDiscountProjMaster.setDiscountType(discountType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_chDiscountProjMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_chDiscountProjMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_chDiscountProjMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the methodology of this ch discount proj master.
	*
	* @param methodology the methodology of this ch discount proj master
	*/
	@Override
	public void setMethodology(java.lang.String methodology) {
		_chDiscountProjMaster.setMethodology(methodology);
	}

	/**
	* Sets the net flag of this ch discount proj master.
	*
	* @param netFlag the net flag of this ch discount proj master
	*/
	@Override
	public void setNetFlag(java.lang.String netFlag) {
		_chDiscountProjMaster.setNetFlag(netFlag);
	}

	@Override
	public void setNew(boolean n) {
		_chDiscountProjMaster.setNew(n);
	}

	/**
	* Sets the price group type of this ch discount proj master.
	*
	* @param priceGroupType the price group type of this ch discount proj master
	*/
	@Override
	public void setPriceGroupType(java.lang.String priceGroupType) {
		_chDiscountProjMaster.setPriceGroupType(priceGroupType);
	}

	/**
	* Sets the primary key of this ch discount proj master.
	*
	* @param primaryKey the primary key of this ch discount proj master
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.ChDiscountProjMasterPK primaryKey) {
		_chDiscountProjMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_chDiscountProjMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projected type of this ch discount proj master.
	*
	* @param projectedType the projected type of this ch discount proj master
	*/
	@Override
	public void setProjectedType(java.lang.String projectedType) {
		_chDiscountProjMaster.setProjectedType(projectedType);
	}

	/**
	* Sets the projection details sid of this ch discount proj master.
	*
	* @param projectionDetailsSid the projection details sid of this ch discount proj master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_chDiscountProjMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the rs model sid of this ch discount proj master.
	*
	* @param rsModelSid the rs model sid of this ch discount proj master
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_chDiscountProjMaster.setRsModelSid(rsModelSid);
	}

	/**
	* Sets the selected periods of this ch discount proj master.
	*
	* @param selectedPeriods the selected periods of this ch discount proj master
	*/
	@Override
	public void setSelectedPeriods(java.lang.String selectedPeriods) {
		_chDiscountProjMaster.setSelectedPeriods(selectedPeriods);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ChDiscountProjMaster> toCacheModel() {
		return _chDiscountProjMaster.toCacheModel();
	}

	@Override
	public ChDiscountProjMaster toEscapedModel() {
		return new ChDiscountProjMasterWrapper(_chDiscountProjMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _chDiscountProjMaster.toString();
	}

	@Override
	public ChDiscountProjMaster toUnescapedModel() {
		return new ChDiscountProjMasterWrapper(_chDiscountProjMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _chDiscountProjMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChDiscountProjMasterWrapper)) {
			return false;
		}

		ChDiscountProjMasterWrapper chDiscountProjMasterWrapper = (ChDiscountProjMasterWrapper)obj;

		if (Objects.equals(_chDiscountProjMaster,
					chDiscountProjMasterWrapper._chDiscountProjMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ChDiscountProjMaster getWrappedModel() {
		return _chDiscountProjMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _chDiscountProjMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _chDiscountProjMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_chDiscountProjMaster.resetOriginalValues();
	}

	private final ChDiscountProjMaster _chDiscountProjMaster;
}