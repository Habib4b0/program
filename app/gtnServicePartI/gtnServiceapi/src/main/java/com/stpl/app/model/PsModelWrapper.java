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
 * This class is a wrapper for {@link PsModel}.
 * </p>
 *
 * @author
 * @see PsModel
 * @generated
 */
@ProviderType
public class PsModelWrapper implements PsModel, ModelWrapper<PsModel> {
	public PsModelWrapper(PsModel psModel) {
		_psModel = psModel;
	}

	@Override
	public Class<?> getModelClass() {
		return PsModel.class;
	}

	@Override
	public String getModelClassName() {
		return PsModel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("psId", getPsId());
		attributes.put("psName", getPsName());
		attributes.put("psType", getPsType());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("psCategory", getPsCategory());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("psStatus", getPsStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("psNo", getPsNo());
		attributes.put("psDesignation", getPsDesignation());
		attributes.put("parentPsId", getParentPsId());
		attributes.put("batchId", getBatchId());
		attributes.put("psModelSid", getPsModelSid());
		attributes.put("psEndDate", getPsEndDate());
		attributes.put("psTradeClass", getPsTradeClass());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("psStartDate", getPsStartDate());
		attributes.put("parentPsName", getParentPsName());
		attributes.put("internalNotes", getInternalNotes());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String psId = (String)attributes.get("psId");

		if (psId != null) {
			setPsId(psId);
		}

		String psName = (String)attributes.get("psName");

		if (psName != null) {
			setPsName(psName);
		}

		Integer psType = (Integer)attributes.get("psType");

		if (psType != null) {
			setPsType(psType);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer psCategory = (Integer)attributes.get("psCategory");

		if (psCategory != null) {
			setPsCategory(psCategory);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Integer psStatus = (Integer)attributes.get("psStatus");

		if (psStatus != null) {
			setPsStatus(psStatus);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String psNo = (String)attributes.get("psNo");

		if (psNo != null) {
			setPsNo(psNo);
		}

		String psDesignation = (String)attributes.get("psDesignation");

		if (psDesignation != null) {
			setPsDesignation(psDesignation);
		}

		String parentPsId = (String)attributes.get("parentPsId");

		if (parentPsId != null) {
			setParentPsId(parentPsId);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer psModelSid = (Integer)attributes.get("psModelSid");

		if (psModelSid != null) {
			setPsModelSid(psModelSid);
		}

		Date psEndDate = (Date)attributes.get("psEndDate");

		if (psEndDate != null) {
			setPsEndDate(psEndDate);
		}

		Integer psTradeClass = (Integer)attributes.get("psTradeClass");

		if (psTradeClass != null) {
			setPsTradeClass(psTradeClass);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Date psStartDate = (Date)attributes.get("psStartDate");

		if (psStartDate != null) {
			setPsStartDate(psStartDate);
		}

		String parentPsName = (String)attributes.get("parentPsName");

		if (parentPsName != null) {
			setParentPsName(parentPsName);
		}

		String internalNotes = (String)attributes.get("internalNotes");

		if (internalNotes != null) {
			setInternalNotes(internalNotes);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new PsModelWrapper((PsModel)_psModel.clone());
	}

	@Override
	public int compareTo(PsModel psModel) {
		return _psModel.compareTo(psModel);
	}

	/**
	* Returns the batch ID of this ps model.
	*
	* @return the batch ID of this ps model
	*/
	@Override
	public java.lang.String getBatchId() {
		return _psModel.getBatchId();
	}

	/**
	* Returns the created by of this ps model.
	*
	* @return the created by of this ps model
	*/
	@Override
	public int getCreatedBy() {
		return _psModel.getCreatedBy();
	}

	/**
	* Returns the created date of this ps model.
	*
	* @return the created date of this ps model
	*/
	@Override
	public Date getCreatedDate() {
		return _psModel.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _psModel.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this ps model.
	*
	* @return the inbound status of this ps model
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _psModel.getInboundStatus();
	}

	/**
	* Returns the internal notes of this ps model.
	*
	* @return the internal notes of this ps model
	*/
	@Override
	public java.lang.String getInternalNotes() {
		return _psModel.getInternalNotes();
	}

	/**
	* Returns the modified by of this ps model.
	*
	* @return the modified by of this ps model
	*/
	@Override
	public int getModifiedBy() {
		return _psModel.getModifiedBy();
	}

	/**
	* Returns the modified date of this ps model.
	*
	* @return the modified date of this ps model
	*/
	@Override
	public Date getModifiedDate() {
		return _psModel.getModifiedDate();
	}

	/**
	* Returns the parent ps ID of this ps model.
	*
	* @return the parent ps ID of this ps model
	*/
	@Override
	public java.lang.String getParentPsId() {
		return _psModel.getParentPsId();
	}

	/**
	* Returns the parent ps name of this ps model.
	*
	* @return the parent ps name of this ps model
	*/
	@Override
	public java.lang.String getParentPsName() {
		return _psModel.getParentPsName();
	}

	/**
	* Returns the primary key of this ps model.
	*
	* @return the primary key of this ps model
	*/
	@Override
	public int getPrimaryKey() {
		return _psModel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _psModel.getPrimaryKeyObj();
	}

	/**
	* Returns the ps category of this ps model.
	*
	* @return the ps category of this ps model
	*/
	@Override
	public int getPsCategory() {
		return _psModel.getPsCategory();
	}

	/**
	* Returns the ps designation of this ps model.
	*
	* @return the ps designation of this ps model
	*/
	@Override
	public java.lang.String getPsDesignation() {
		return _psModel.getPsDesignation();
	}

	/**
	* Returns the ps end date of this ps model.
	*
	* @return the ps end date of this ps model
	*/
	@Override
	public Date getPsEndDate() {
		return _psModel.getPsEndDate();
	}

	/**
	* Returns the ps ID of this ps model.
	*
	* @return the ps ID of this ps model
	*/
	@Override
	public java.lang.String getPsId() {
		return _psModel.getPsId();
	}

	/**
	* Returns the ps model sid of this ps model.
	*
	* @return the ps model sid of this ps model
	*/
	@Override
	public int getPsModelSid() {
		return _psModel.getPsModelSid();
	}

	/**
	* Returns the ps name of this ps model.
	*
	* @return the ps name of this ps model
	*/
	@Override
	public java.lang.String getPsName() {
		return _psModel.getPsName();
	}

	/**
	* Returns the ps no of this ps model.
	*
	* @return the ps no of this ps model
	*/
	@Override
	public java.lang.String getPsNo() {
		return _psModel.getPsNo();
	}

	/**
	* Returns the ps start date of this ps model.
	*
	* @return the ps start date of this ps model
	*/
	@Override
	public Date getPsStartDate() {
		return _psModel.getPsStartDate();
	}

	/**
	* Returns the ps status of this ps model.
	*
	* @return the ps status of this ps model
	*/
	@Override
	public int getPsStatus() {
		return _psModel.getPsStatus();
	}

	/**
	* Returns the ps trade class of this ps model.
	*
	* @return the ps trade class of this ps model
	*/
	@Override
	public int getPsTradeClass() {
		return _psModel.getPsTradeClass();
	}

	/**
	* Returns the ps type of this ps model.
	*
	* @return the ps type of this ps model
	*/
	@Override
	public int getPsType() {
		return _psModel.getPsType();
	}

	/**
	* Returns the record lock status of this ps model.
	*
	* @return the record lock status of this ps model
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _psModel.getRecordLockStatus();
	}

	/**
	* Returns the source of this ps model.
	*
	* @return the source of this ps model
	*/
	@Override
	public java.lang.String getSource() {
		return _psModel.getSource();
	}

	@Override
	public int hashCode() {
		return _psModel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _psModel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _psModel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _psModel.isNew();
	}

	/**
	* Returns <code>true</code> if this ps model is record lock status.
	*
	* @return <code>true</code> if this ps model is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _psModel.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_psModel.persist();
	}

	/**
	* Sets the batch ID of this ps model.
	*
	* @param batchId the batch ID of this ps model
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_psModel.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_psModel.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this ps model.
	*
	* @param createdBy the created by of this ps model
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_psModel.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ps model.
	*
	* @param createdDate the created date of this ps model
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_psModel.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_psModel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_psModel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_psModel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this ps model.
	*
	* @param inboundStatus the inbound status of this ps model
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_psModel.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the internal notes of this ps model.
	*
	* @param internalNotes the internal notes of this ps model
	*/
	@Override
	public void setInternalNotes(java.lang.String internalNotes) {
		_psModel.setInternalNotes(internalNotes);
	}

	/**
	* Sets the modified by of this ps model.
	*
	* @param modifiedBy the modified by of this ps model
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_psModel.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ps model.
	*
	* @param modifiedDate the modified date of this ps model
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_psModel.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_psModel.setNew(n);
	}

	/**
	* Sets the parent ps ID of this ps model.
	*
	* @param parentPsId the parent ps ID of this ps model
	*/
	@Override
	public void setParentPsId(java.lang.String parentPsId) {
		_psModel.setParentPsId(parentPsId);
	}

	/**
	* Sets the parent ps name of this ps model.
	*
	* @param parentPsName the parent ps name of this ps model
	*/
	@Override
	public void setParentPsName(java.lang.String parentPsName) {
		_psModel.setParentPsName(parentPsName);
	}

	/**
	* Sets the primary key of this ps model.
	*
	* @param primaryKey the primary key of this ps model
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_psModel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_psModel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ps category of this ps model.
	*
	* @param psCategory the ps category of this ps model
	*/
	@Override
	public void setPsCategory(int psCategory) {
		_psModel.setPsCategory(psCategory);
	}

	/**
	* Sets the ps designation of this ps model.
	*
	* @param psDesignation the ps designation of this ps model
	*/
	@Override
	public void setPsDesignation(java.lang.String psDesignation) {
		_psModel.setPsDesignation(psDesignation);
	}

	/**
	* Sets the ps end date of this ps model.
	*
	* @param psEndDate the ps end date of this ps model
	*/
	@Override
	public void setPsEndDate(Date psEndDate) {
		_psModel.setPsEndDate(psEndDate);
	}

	/**
	* Sets the ps ID of this ps model.
	*
	* @param psId the ps ID of this ps model
	*/
	@Override
	public void setPsId(java.lang.String psId) {
		_psModel.setPsId(psId);
	}

	/**
	* Sets the ps model sid of this ps model.
	*
	* @param psModelSid the ps model sid of this ps model
	*/
	@Override
	public void setPsModelSid(int psModelSid) {
		_psModel.setPsModelSid(psModelSid);
	}

	/**
	* Sets the ps name of this ps model.
	*
	* @param psName the ps name of this ps model
	*/
	@Override
	public void setPsName(java.lang.String psName) {
		_psModel.setPsName(psName);
	}

	/**
	* Sets the ps no of this ps model.
	*
	* @param psNo the ps no of this ps model
	*/
	@Override
	public void setPsNo(java.lang.String psNo) {
		_psModel.setPsNo(psNo);
	}

	/**
	* Sets the ps start date of this ps model.
	*
	* @param psStartDate the ps start date of this ps model
	*/
	@Override
	public void setPsStartDate(Date psStartDate) {
		_psModel.setPsStartDate(psStartDate);
	}

	/**
	* Sets the ps status of this ps model.
	*
	* @param psStatus the ps status of this ps model
	*/
	@Override
	public void setPsStatus(int psStatus) {
		_psModel.setPsStatus(psStatus);
	}

	/**
	* Sets the ps trade class of this ps model.
	*
	* @param psTradeClass the ps trade class of this ps model
	*/
	@Override
	public void setPsTradeClass(int psTradeClass) {
		_psModel.setPsTradeClass(psTradeClass);
	}

	/**
	* Sets the ps type of this ps model.
	*
	* @param psType the ps type of this ps model
	*/
	@Override
	public void setPsType(int psType) {
		_psModel.setPsType(psType);
	}

	/**
	* Sets whether this ps model is record lock status.
	*
	* @param recordLockStatus the record lock status of this ps model
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_psModel.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this ps model.
	*
	* @param source the source of this ps model
	*/
	@Override
	public void setSource(java.lang.String source) {
		_psModel.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PsModel> toCacheModel() {
		return _psModel.toCacheModel();
	}

	@Override
	public PsModel toEscapedModel() {
		return new PsModelWrapper(_psModel.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _psModel.toString();
	}

	@Override
	public PsModel toUnescapedModel() {
		return new PsModelWrapper(_psModel.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _psModel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PsModelWrapper)) {
			return false;
		}

		PsModelWrapper psModelWrapper = (PsModelWrapper)obj;

		if (Objects.equals(_psModel, psModelWrapper._psModel)) {
			return true;
		}

		return false;
	}

	@Override
	public PsModel getWrappedModel() {
		return _psModel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _psModel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _psModel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_psModel.resetOriginalValues();
	}

	private final PsModel _psModel;
}