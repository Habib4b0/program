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
 * This class is a wrapper for {@link DeductionCalendarMaster}.
 * </p>
 *
 * @author
 * @see DeductionCalendarMaster
 * @generated
 */
@ProviderType
public class DeductionCalendarMasterWrapper implements DeductionCalendarMaster,
	ModelWrapper<DeductionCalendarMaster> {
	public DeductionCalendarMasterWrapper(
		DeductionCalendarMaster deductionCalendarMaster) {
		_deductionCalendarMaster = deductionCalendarMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return DeductionCalendarMaster.class;
	}

	@Override
	public String getModelClassName() {
		return DeductionCalendarMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("deductionCalendarMasterSid",
			getDeductionCalendarMasterSid());
		attributes.put("deductionCalendarNo", getDeductionCalendarNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("category", getCategory());
		attributes.put("additionalNotes", getAdditionalNotes());
		attributes.put("userId", getUserId());
		attributes.put("deductionCalendarName", getDeductionCalendarName());
		attributes.put("deductionCalendarDesc", getDeductionCalendarDesc());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer deductionCalendarMasterSid = (Integer)attributes.get(
				"deductionCalendarMasterSid");

		if (deductionCalendarMasterSid != null) {
			setDeductionCalendarMasterSid(deductionCalendarMasterSid);
		}

		String deductionCalendarNo = (String)attributes.get(
				"deductionCalendarNo");

		if (deductionCalendarNo != null) {
			setDeductionCalendarNo(deductionCalendarNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer category = (Integer)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String additionalNotes = (String)attributes.get("additionalNotes");

		if (additionalNotes != null) {
			setAdditionalNotes(additionalNotes);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String deductionCalendarName = (String)attributes.get(
				"deductionCalendarName");

		if (deductionCalendarName != null) {
			setDeductionCalendarName(deductionCalendarName);
		}

		String deductionCalendarDesc = (String)attributes.get(
				"deductionCalendarDesc");

		if (deductionCalendarDesc != null) {
			setDeductionCalendarDesc(deductionCalendarDesc);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new DeductionCalendarMasterWrapper((DeductionCalendarMaster)_deductionCalendarMaster.clone());
	}

	@Override
	public int compareTo(DeductionCalendarMaster deductionCalendarMaster) {
		return _deductionCalendarMaster.compareTo(deductionCalendarMaster);
	}

	/**
	* Returns the additional notes of this deduction calendar master.
	*
	* @return the additional notes of this deduction calendar master
	*/
	@Override
	public java.lang.String getAdditionalNotes() {
		return _deductionCalendarMaster.getAdditionalNotes();
	}

	/**
	* Returns the category of this deduction calendar master.
	*
	* @return the category of this deduction calendar master
	*/
	@Override
	public int getCategory() {
		return _deductionCalendarMaster.getCategory();
	}

	/**
	* Returns the created by of this deduction calendar master.
	*
	* @return the created by of this deduction calendar master
	*/
	@Override
	public int getCreatedBy() {
		return _deductionCalendarMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this deduction calendar master.
	*
	* @return the created date of this deduction calendar master
	*/
	@Override
	public Date getCreatedDate() {
		return _deductionCalendarMaster.getCreatedDate();
	}

	/**
	* Returns the deduction calendar desc of this deduction calendar master.
	*
	* @return the deduction calendar desc of this deduction calendar master
	*/
	@Override
	public java.lang.String getDeductionCalendarDesc() {
		return _deductionCalendarMaster.getDeductionCalendarDesc();
	}

	/**
	* Returns the deduction calendar master sid of this deduction calendar master.
	*
	* @return the deduction calendar master sid of this deduction calendar master
	*/
	@Override
	public int getDeductionCalendarMasterSid() {
		return _deductionCalendarMaster.getDeductionCalendarMasterSid();
	}

	/**
	* Returns the deduction calendar name of this deduction calendar master.
	*
	* @return the deduction calendar name of this deduction calendar master
	*/
	@Override
	public java.lang.String getDeductionCalendarName() {
		return _deductionCalendarMaster.getDeductionCalendarName();
	}

	/**
	* Returns the deduction calendar no of this deduction calendar master.
	*
	* @return the deduction calendar no of this deduction calendar master
	*/
	@Override
	public java.lang.String getDeductionCalendarNo() {
		return _deductionCalendarMaster.getDeductionCalendarNo();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deductionCalendarMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this deduction calendar master.
	*
	* @return the inbound status of this deduction calendar master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _deductionCalendarMaster.getInboundStatus();
	}

	/**
	* Returns the modified by of this deduction calendar master.
	*
	* @return the modified by of this deduction calendar master
	*/
	@Override
	public int getModifiedBy() {
		return _deductionCalendarMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this deduction calendar master.
	*
	* @return the modified date of this deduction calendar master
	*/
	@Override
	public Date getModifiedDate() {
		return _deductionCalendarMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this deduction calendar master.
	*
	* @return the primary key of this deduction calendar master
	*/
	@Override
	public int getPrimaryKey() {
		return _deductionCalendarMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deductionCalendarMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this deduction calendar master.
	*
	* @return the user ID of this deduction calendar master
	*/
	@Override
	public int getUserId() {
		return _deductionCalendarMaster.getUserId();
	}

	@Override
	public int hashCode() {
		return _deductionCalendarMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deductionCalendarMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _deductionCalendarMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _deductionCalendarMaster.isNew();
	}

	@Override
	public void persist() {
		_deductionCalendarMaster.persist();
	}

	/**
	* Sets the additional notes of this deduction calendar master.
	*
	* @param additionalNotes the additional notes of this deduction calendar master
	*/
	@Override
	public void setAdditionalNotes(java.lang.String additionalNotes) {
		_deductionCalendarMaster.setAdditionalNotes(additionalNotes);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deductionCalendarMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the category of this deduction calendar master.
	*
	* @param category the category of this deduction calendar master
	*/
	@Override
	public void setCategory(int category) {
		_deductionCalendarMaster.setCategory(category);
	}

	/**
	* Sets the created by of this deduction calendar master.
	*
	* @param createdBy the created by of this deduction calendar master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_deductionCalendarMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this deduction calendar master.
	*
	* @param createdDate the created date of this deduction calendar master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_deductionCalendarMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the deduction calendar desc of this deduction calendar master.
	*
	* @param deductionCalendarDesc the deduction calendar desc of this deduction calendar master
	*/
	@Override
	public void setDeductionCalendarDesc(java.lang.String deductionCalendarDesc) {
		_deductionCalendarMaster.setDeductionCalendarDesc(deductionCalendarDesc);
	}

	/**
	* Sets the deduction calendar master sid of this deduction calendar master.
	*
	* @param deductionCalendarMasterSid the deduction calendar master sid of this deduction calendar master
	*/
	@Override
	public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
		_deductionCalendarMaster.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
	}

	/**
	* Sets the deduction calendar name of this deduction calendar master.
	*
	* @param deductionCalendarName the deduction calendar name of this deduction calendar master
	*/
	@Override
	public void setDeductionCalendarName(java.lang.String deductionCalendarName) {
		_deductionCalendarMaster.setDeductionCalendarName(deductionCalendarName);
	}

	/**
	* Sets the deduction calendar no of this deduction calendar master.
	*
	* @param deductionCalendarNo the deduction calendar no of this deduction calendar master
	*/
	@Override
	public void setDeductionCalendarNo(java.lang.String deductionCalendarNo) {
		_deductionCalendarMaster.setDeductionCalendarNo(deductionCalendarNo);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deductionCalendarMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deductionCalendarMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deductionCalendarMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this deduction calendar master.
	*
	* @param inboundStatus the inbound status of this deduction calendar master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_deductionCalendarMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this deduction calendar master.
	*
	* @param modifiedBy the modified by of this deduction calendar master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_deductionCalendarMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this deduction calendar master.
	*
	* @param modifiedDate the modified date of this deduction calendar master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deductionCalendarMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_deductionCalendarMaster.setNew(n);
	}

	/**
	* Sets the primary key of this deduction calendar master.
	*
	* @param primaryKey the primary key of this deduction calendar master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_deductionCalendarMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deductionCalendarMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this deduction calendar master.
	*
	* @param userId the user ID of this deduction calendar master
	*/
	@Override
	public void setUserId(int userId) {
		_deductionCalendarMaster.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DeductionCalendarMaster> toCacheModel() {
		return _deductionCalendarMaster.toCacheModel();
	}

	@Override
	public DeductionCalendarMaster toEscapedModel() {
		return new DeductionCalendarMasterWrapper(_deductionCalendarMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _deductionCalendarMaster.toString();
	}

	@Override
	public DeductionCalendarMaster toUnescapedModel() {
		return new DeductionCalendarMasterWrapper(_deductionCalendarMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _deductionCalendarMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionCalendarMasterWrapper)) {
			return false;
		}

		DeductionCalendarMasterWrapper deductionCalendarMasterWrapper = (DeductionCalendarMasterWrapper)obj;

		if (Objects.equals(_deductionCalendarMaster,
					deductionCalendarMasterWrapper._deductionCalendarMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public DeductionCalendarMaster getWrappedModel() {
		return _deductionCalendarMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deductionCalendarMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deductionCalendarMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deductionCalendarMaster.resetOriginalValues();
	}

	private final DeductionCalendarMaster _deductionCalendarMaster;
}