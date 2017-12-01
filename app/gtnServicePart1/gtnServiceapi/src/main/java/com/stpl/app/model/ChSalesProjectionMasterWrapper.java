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
 * This class is a wrapper for {@link ChSalesProjectionMaster}.
 * </p>
 *
 * @author
 * @see ChSalesProjectionMaster
 * @generated
 */
@ProviderType
public class ChSalesProjectionMasterWrapper implements ChSalesProjectionMaster,
	ModelWrapper<ChSalesProjectionMaster> {
	public ChSalesProjectionMasterWrapper(
		ChSalesProjectionMaster chSalesProjectionMaster) {
		_chSalesProjectionMaster = chSalesProjectionMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ChSalesProjectionMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ChSalesProjectionMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkRecord", getCheckRecord());
		attributes.put("calculationPeriods", getCalculationPeriods());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("methodology", getMethodology());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String calculationPeriods = (String)attributes.get("calculationPeriods");

		if (calculationPeriods != null) {
			setCalculationPeriods(calculationPeriods);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		String methodology = (String)attributes.get("methodology");

		if (methodology != null) {
			setMethodology(methodology);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ChSalesProjectionMasterWrapper((ChSalesProjectionMaster)_chSalesProjectionMaster.clone());
	}

	@Override
	public int compareTo(ChSalesProjectionMaster chSalesProjectionMaster) {
		return _chSalesProjectionMaster.compareTo(chSalesProjectionMaster);
	}

	/**
	* Returns the calculation periods of this ch sales projection master.
	*
	* @return the calculation periods of this ch sales projection master
	*/
	@Override
	public java.lang.String getCalculationPeriods() {
		return _chSalesProjectionMaster.getCalculationPeriods();
	}

	/**
	* Returns the check record of this ch sales projection master.
	*
	* @return the check record of this ch sales projection master
	*/
	@Override
	public boolean getCheckRecord() {
		return _chSalesProjectionMaster.getCheckRecord();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _chSalesProjectionMaster.getExpandoBridge();
	}

	/**
	* Returns the methodology of this ch sales projection master.
	*
	* @return the methodology of this ch sales projection master
	*/
	@Override
	public java.lang.String getMethodology() {
		return _chSalesProjectionMaster.getMethodology();
	}

	/**
	* Returns the primary key of this ch sales projection master.
	*
	* @return the primary key of this ch sales projection master
	*/
	@Override
	public int getPrimaryKey() {
		return _chSalesProjectionMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chSalesProjectionMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this ch sales projection master.
	*
	* @return the projection details sid of this ch sales projection master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _chSalesProjectionMaster.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _chSalesProjectionMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _chSalesProjectionMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ch sales projection master is check record.
	*
	* @return <code>true</code> if this ch sales projection master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _chSalesProjectionMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _chSalesProjectionMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _chSalesProjectionMaster.isNew();
	}

	@Override
	public void persist() {
		_chSalesProjectionMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_chSalesProjectionMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the calculation periods of this ch sales projection master.
	*
	* @param calculationPeriods the calculation periods of this ch sales projection master
	*/
	@Override
	public void setCalculationPeriods(java.lang.String calculationPeriods) {
		_chSalesProjectionMaster.setCalculationPeriods(calculationPeriods);
	}

	/**
	* Sets whether this ch sales projection master is check record.
	*
	* @param checkRecord the check record of this ch sales projection master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_chSalesProjectionMaster.setCheckRecord(checkRecord);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_chSalesProjectionMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_chSalesProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_chSalesProjectionMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the methodology of this ch sales projection master.
	*
	* @param methodology the methodology of this ch sales projection master
	*/
	@Override
	public void setMethodology(java.lang.String methodology) {
		_chSalesProjectionMaster.setMethodology(methodology);
	}

	@Override
	public void setNew(boolean n) {
		_chSalesProjectionMaster.setNew(n);
	}

	/**
	* Sets the primary key of this ch sales projection master.
	*
	* @param primaryKey the primary key of this ch sales projection master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_chSalesProjectionMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_chSalesProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this ch sales projection master.
	*
	* @param projectionDetailsSid the projection details sid of this ch sales projection master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_chSalesProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ChSalesProjectionMaster> toCacheModel() {
		return _chSalesProjectionMaster.toCacheModel();
	}

	@Override
	public ChSalesProjectionMaster toEscapedModel() {
		return new ChSalesProjectionMasterWrapper(_chSalesProjectionMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _chSalesProjectionMaster.toString();
	}

	@Override
	public ChSalesProjectionMaster toUnescapedModel() {
		return new ChSalesProjectionMasterWrapper(_chSalesProjectionMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _chSalesProjectionMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChSalesProjectionMasterWrapper)) {
			return false;
		}

		ChSalesProjectionMasterWrapper chSalesProjectionMasterWrapper = (ChSalesProjectionMasterWrapper)obj;

		if (Objects.equals(_chSalesProjectionMaster,
					chSalesProjectionMasterWrapper._chSalesProjectionMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ChSalesProjectionMaster getWrappedModel() {
		return _chSalesProjectionMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _chSalesProjectionMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _chSalesProjectionMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_chSalesProjectionMaster.resetOriginalValues();
	}

	private final ChSalesProjectionMaster _chSalesProjectionMaster;
}