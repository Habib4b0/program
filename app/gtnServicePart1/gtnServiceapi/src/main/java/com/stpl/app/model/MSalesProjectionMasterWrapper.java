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
 * This class is a wrapper for {@link MSalesProjectionMaster}.
 * </p>
 *
 * @author
 * @see MSalesProjectionMaster
 * @generated
 */
@ProviderType
public class MSalesProjectionMasterWrapper implements MSalesProjectionMaster,
	ModelWrapper<MSalesProjectionMaster> {
	public MSalesProjectionMasterWrapper(
		MSalesProjectionMaster mSalesProjectionMaster) {
		_mSalesProjectionMaster = mSalesProjectionMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return MSalesProjectionMaster.class;
	}

	@Override
	public String getModelClassName() {
		return MSalesProjectionMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("methodology", getMethodology());
		attributes.put("calculationPeriods", getCalculationPeriods());
		attributes.put("calculationBased", getCalculationBased());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String methodology = (String)attributes.get("methodology");

		if (methodology != null) {
			setMethodology(methodology);
		}

		String calculationPeriods = (String)attributes.get("calculationPeriods");

		if (calculationPeriods != null) {
			setCalculationPeriods(calculationPeriods);
		}

		String calculationBased = (String)attributes.get("calculationBased");

		if (calculationBased != null) {
			setCalculationBased(calculationBased);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MSalesProjectionMasterWrapper((MSalesProjectionMaster)_mSalesProjectionMaster.clone());
	}

	@Override
	public int compareTo(MSalesProjectionMaster mSalesProjectionMaster) {
		return _mSalesProjectionMaster.compareTo(mSalesProjectionMaster);
	}

	/**
	* Returns the calculation based of this m sales projection master.
	*
	* @return the calculation based of this m sales projection master
	*/
	@Override
	public java.lang.String getCalculationBased() {
		return _mSalesProjectionMaster.getCalculationBased();
	}

	/**
	* Returns the calculation periods of this m sales projection master.
	*
	* @return the calculation periods of this m sales projection master
	*/
	@Override
	public java.lang.String getCalculationPeriods() {
		return _mSalesProjectionMaster.getCalculationPeriods();
	}

	/**
	* Returns the check record of this m sales projection master.
	*
	* @return the check record of this m sales projection master
	*/
	@Override
	public boolean getCheckRecord() {
		return _mSalesProjectionMaster.getCheckRecord();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mSalesProjectionMaster.getExpandoBridge();
	}

	/**
	* Returns the methodology of this m sales projection master.
	*
	* @return the methodology of this m sales projection master
	*/
	@Override
	public java.lang.String getMethodology() {
		return _mSalesProjectionMaster.getMethodology();
	}

	/**
	* Returns the primary key of this m sales projection master.
	*
	* @return the primary key of this m sales projection master
	*/
	@Override
	public int getPrimaryKey() {
		return _mSalesProjectionMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mSalesProjectionMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this m sales projection master.
	*
	* @return the projection details sid of this m sales projection master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _mSalesProjectionMaster.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _mSalesProjectionMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mSalesProjectionMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this m sales projection master is check record.
	*
	* @return <code>true</code> if this m sales projection master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _mSalesProjectionMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _mSalesProjectionMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mSalesProjectionMaster.isNew();
	}

	@Override
	public void persist() {
		_mSalesProjectionMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mSalesProjectionMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the calculation based of this m sales projection master.
	*
	* @param calculationBased the calculation based of this m sales projection master
	*/
	@Override
	public void setCalculationBased(java.lang.String calculationBased) {
		_mSalesProjectionMaster.setCalculationBased(calculationBased);
	}

	/**
	* Sets the calculation periods of this m sales projection master.
	*
	* @param calculationPeriods the calculation periods of this m sales projection master
	*/
	@Override
	public void setCalculationPeriods(java.lang.String calculationPeriods) {
		_mSalesProjectionMaster.setCalculationPeriods(calculationPeriods);
	}

	/**
	* Sets whether this m sales projection master is check record.
	*
	* @param checkRecord the check record of this m sales projection master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_mSalesProjectionMaster.setCheckRecord(checkRecord);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mSalesProjectionMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mSalesProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mSalesProjectionMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the methodology of this m sales projection master.
	*
	* @param methodology the methodology of this m sales projection master
	*/
	@Override
	public void setMethodology(java.lang.String methodology) {
		_mSalesProjectionMaster.setMethodology(methodology);
	}

	@Override
	public void setNew(boolean n) {
		_mSalesProjectionMaster.setNew(n);
	}

	/**
	* Sets the primary key of this m sales projection master.
	*
	* @param primaryKey the primary key of this m sales projection master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_mSalesProjectionMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mSalesProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this m sales projection master.
	*
	* @param projectionDetailsSid the projection details sid of this m sales projection master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_mSalesProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MSalesProjectionMaster> toCacheModel() {
		return _mSalesProjectionMaster.toCacheModel();
	}

	@Override
	public MSalesProjectionMaster toEscapedModel() {
		return new MSalesProjectionMasterWrapper(_mSalesProjectionMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mSalesProjectionMaster.toString();
	}

	@Override
	public MSalesProjectionMaster toUnescapedModel() {
		return new MSalesProjectionMasterWrapper(_mSalesProjectionMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _mSalesProjectionMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSalesProjectionMasterWrapper)) {
			return false;
		}

		MSalesProjectionMasterWrapper mSalesProjectionMasterWrapper = (MSalesProjectionMasterWrapper)obj;

		if (Objects.equals(_mSalesProjectionMaster,
					mSalesProjectionMasterWrapper._mSalesProjectionMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public MSalesProjectionMaster getWrappedModel() {
		return _mSalesProjectionMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mSalesProjectionMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mSalesProjectionMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mSalesProjectionMaster.resetOriginalValues();
	}

	private final MSalesProjectionMaster _mSalesProjectionMaster;
}