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
 * This class is a wrapper for {@link NmSalesProjectionMaster}.
 * </p>
 *
 * @author
 * @see NmSalesProjectionMaster
 * @generated
 */
@ProviderType
public class NmSalesProjectionMasterWrapper implements NmSalesProjectionMaster,
	ModelWrapper<NmSalesProjectionMaster> {
	public NmSalesProjectionMasterWrapper(
		NmSalesProjectionMaster nmSalesProjectionMaster) {
		_nmSalesProjectionMaster = nmSalesProjectionMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return NmSalesProjectionMaster.class;
	}

	@Override
	public String getModelClassName() {
		return NmSalesProjectionMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkRecord", getCheckRecord());
		attributes.put("calculationPeriods", getCalculationPeriods());
		attributes.put("userGroup", getUserGroup());
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

		String userGroup = (String)attributes.get("userGroup");

		if (userGroup != null) {
			setUserGroup(userGroup);
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
		return new NmSalesProjectionMasterWrapper((NmSalesProjectionMaster)_nmSalesProjectionMaster.clone());
	}

	@Override
	public int compareTo(NmSalesProjectionMaster nmSalesProjectionMaster) {
		return _nmSalesProjectionMaster.compareTo(nmSalesProjectionMaster);
	}

	/**
	* Returns the calculation periods of this nm sales projection master.
	*
	* @return the calculation periods of this nm sales projection master
	*/
	@Override
	public java.lang.String getCalculationPeriods() {
		return _nmSalesProjectionMaster.getCalculationPeriods();
	}

	/**
	* Returns the check record of this nm sales projection master.
	*
	* @return the check record of this nm sales projection master
	*/
	@Override
	public boolean getCheckRecord() {
		return _nmSalesProjectionMaster.getCheckRecord();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nmSalesProjectionMaster.getExpandoBridge();
	}

	/**
	* Returns the methodology of this nm sales projection master.
	*
	* @return the methodology of this nm sales projection master
	*/
	@Override
	public java.lang.String getMethodology() {
		return _nmSalesProjectionMaster.getMethodology();
	}

	/**
	* Returns the primary key of this nm sales projection master.
	*
	* @return the primary key of this nm sales projection master
	*/
	@Override
	public int getPrimaryKey() {
		return _nmSalesProjectionMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nmSalesProjectionMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this nm sales projection master.
	*
	* @return the projection details sid of this nm sales projection master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _nmSalesProjectionMaster.getProjectionDetailsSid();
	}

	/**
	* Returns the user group of this nm sales projection master.
	*
	* @return the user group of this nm sales projection master
	*/
	@Override
	public java.lang.String getUserGroup() {
		return _nmSalesProjectionMaster.getUserGroup();
	}

	@Override
	public int hashCode() {
		return _nmSalesProjectionMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nmSalesProjectionMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this nm sales projection master is check record.
	*
	* @return <code>true</code> if this nm sales projection master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _nmSalesProjectionMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _nmSalesProjectionMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nmSalesProjectionMaster.isNew();
	}

	@Override
	public void persist() {
		_nmSalesProjectionMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nmSalesProjectionMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the calculation periods of this nm sales projection master.
	*
	* @param calculationPeriods the calculation periods of this nm sales projection master
	*/
	@Override
	public void setCalculationPeriods(java.lang.String calculationPeriods) {
		_nmSalesProjectionMaster.setCalculationPeriods(calculationPeriods);
	}

	/**
	* Sets whether this nm sales projection master is check record.
	*
	* @param checkRecord the check record of this nm sales projection master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_nmSalesProjectionMaster.setCheckRecord(checkRecord);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nmSalesProjectionMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nmSalesProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nmSalesProjectionMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the methodology of this nm sales projection master.
	*
	* @param methodology the methodology of this nm sales projection master
	*/
	@Override
	public void setMethodology(java.lang.String methodology) {
		_nmSalesProjectionMaster.setMethodology(methodology);
	}

	@Override
	public void setNew(boolean n) {
		_nmSalesProjectionMaster.setNew(n);
	}

	/**
	* Sets the primary key of this nm sales projection master.
	*
	* @param primaryKey the primary key of this nm sales projection master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_nmSalesProjectionMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nmSalesProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this nm sales projection master.
	*
	* @param projectionDetailsSid the projection details sid of this nm sales projection master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_nmSalesProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the user group of this nm sales projection master.
	*
	* @param userGroup the user group of this nm sales projection master
	*/
	@Override
	public void setUserGroup(java.lang.String userGroup) {
		_nmSalesProjectionMaster.setUserGroup(userGroup);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NmSalesProjectionMaster> toCacheModel() {
		return _nmSalesProjectionMaster.toCacheModel();
	}

	@Override
	public NmSalesProjectionMaster toEscapedModel() {
		return new NmSalesProjectionMasterWrapper(_nmSalesProjectionMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nmSalesProjectionMaster.toString();
	}

	@Override
	public NmSalesProjectionMaster toUnescapedModel() {
		return new NmSalesProjectionMasterWrapper(_nmSalesProjectionMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nmSalesProjectionMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmSalesProjectionMasterWrapper)) {
			return false;
		}

		NmSalesProjectionMasterWrapper nmSalesProjectionMasterWrapper = (NmSalesProjectionMasterWrapper)obj;

		if (Objects.equals(_nmSalesProjectionMaster,
					nmSalesProjectionMasterWrapper._nmSalesProjectionMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public NmSalesProjectionMaster getWrappedModel() {
		return _nmSalesProjectionMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nmSalesProjectionMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nmSalesProjectionMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nmSalesProjectionMaster.resetOriginalValues();
	}

	private final NmSalesProjectionMaster _nmSalesProjectionMaster;
}