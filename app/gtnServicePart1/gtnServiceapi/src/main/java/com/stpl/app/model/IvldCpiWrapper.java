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
 * This class is a wrapper for {@link IvldCpi}.
 * </p>
 *
 * @author
 * @see IvldCpi
 * @generated
 */
@ProviderType
public class IvldCpiWrapper implements IvldCpi, ModelWrapper<IvldCpi> {
	public IvldCpiWrapper(IvldCpi ivldCpi) {
		_ivldCpi = ivldCpi;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldCpi.class;
	}

	@Override
	public String getModelClassName() {
		return IvldCpi.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("effectiveDate", getEffectiveDate());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("indexType", getIndexType());
		attributes.put("status", getStatus());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("cpiIntfid", getCpiIntfid());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("indexValue", getIndexValue());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("batchId", getBatchId());
		attributes.put("ivldCpiSid", getIvldCpiSid());
		attributes.put("errorField", getErrorField());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("indexId", getIndexId());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String effectiveDate = (String)attributes.get("effectiveDate");

		if (effectiveDate != null) {
			setEffectiveDate(effectiveDate);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String indexType = (String)attributes.get("indexType");

		if (indexType != null) {
			setIndexType(indexType);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String cpiIntfid = (String)attributes.get("cpiIntfid");

		if (cpiIntfid != null) {
			setCpiIntfid(cpiIntfid);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String indexValue = (String)attributes.get("indexValue");

		if (indexValue != null) {
			setIndexValue(indexValue);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer ivldCpiSid = (Integer)attributes.get("ivldCpiSid");

		if (ivldCpiSid != null) {
			setIvldCpiSid(ivldCpiSid);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String indexId = (String)attributes.get("indexId");

		if (indexId != null) {
			setIndexId(indexId);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldCpiWrapper((IvldCpi)_ivldCpi.clone());
	}

	@Override
	public int compareTo(IvldCpi ivldCpi) {
		return _ivldCpi.compareTo(ivldCpi);
	}

	/**
	* Returns the add chg del indicator of this ivld cpi.
	*
	* @return the add chg del indicator of this ivld cpi
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldCpi.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivld cpi.
	*
	* @return the batch ID of this ivld cpi
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldCpi.getBatchId();
	}

	/**
	* Returns the check record of this ivld cpi.
	*
	* @return the check record of this ivld cpi
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldCpi.getCheckRecord();
	}

	/**
	* Returns the cpi intfid of this ivld cpi.
	*
	* @return the cpi intfid of this ivld cpi
	*/
	@Override
	public java.lang.String getCpiIntfid() {
		return _ivldCpi.getCpiIntfid();
	}

	/**
	* Returns the created by of this ivld cpi.
	*
	* @return the created by of this ivld cpi
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldCpi.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld cpi.
	*
	* @return the created date of this ivld cpi
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldCpi.getCreatedDate();
	}

	/**
	* Returns the effective date of this ivld cpi.
	*
	* @return the effective date of this ivld cpi
	*/
	@Override
	public java.lang.String getEffectiveDate() {
		return _ivldCpi.getEffectiveDate();
	}

	/**
	* Returns the error code of this ivld cpi.
	*
	* @return the error code of this ivld cpi
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldCpi.getErrorCode();
	}

	/**
	* Returns the error field of this ivld cpi.
	*
	* @return the error field of this ivld cpi
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldCpi.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldCpi.getExpandoBridge();
	}

	/**
	* Returns the index ID of this ivld cpi.
	*
	* @return the index ID of this ivld cpi
	*/
	@Override
	public java.lang.String getIndexId() {
		return _ivldCpi.getIndexId();
	}

	/**
	* Returns the index type of this ivld cpi.
	*
	* @return the index type of this ivld cpi
	*/
	@Override
	public java.lang.String getIndexType() {
		return _ivldCpi.getIndexType();
	}

	/**
	* Returns the index value of this ivld cpi.
	*
	* @return the index value of this ivld cpi
	*/
	@Override
	public java.lang.String getIndexValue() {
		return _ivldCpi.getIndexValue();
	}

	/**
	* Returns the intf inserted date of this ivld cpi.
	*
	* @return the intf inserted date of this ivld cpi
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldCpi.getIntfInsertedDate();
	}

	/**
	* Returns the ivld cpi sid of this ivld cpi.
	*
	* @return the ivld cpi sid of this ivld cpi
	*/
	@Override
	public int getIvldCpiSid() {
		return _ivldCpi.getIvldCpiSid();
	}

	/**
	* Returns the modified by of this ivld cpi.
	*
	* @return the modified by of this ivld cpi
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldCpi.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld cpi.
	*
	* @return the modified date of this ivld cpi
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldCpi.getModifiedDate();
	}

	/**
	* Returns the primary key of this ivld cpi.
	*
	* @return the primary key of this ivld cpi
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldCpi.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldCpi.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld cpi.
	*
	* @return the reason for failure of this ivld cpi
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldCpi.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld cpi.
	*
	* @return the reprocessed flag of this ivld cpi
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldCpi.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld cpi.
	*
	* @return the source of this ivld cpi
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldCpi.getSource();
	}

	/**
	* Returns the status of this ivld cpi.
	*
	* @return the status of this ivld cpi
	*/
	@Override
	public java.lang.String getStatus() {
		return _ivldCpi.getStatus();
	}

	@Override
	public int hashCode() {
		return _ivldCpi.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldCpi.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld cpi is check record.
	*
	* @return <code>true</code> if this ivld cpi is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldCpi.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldCpi.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldCpi.isNew();
	}

	@Override
	public void persist() {
		_ivldCpi.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld cpi.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld cpi
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldCpi.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivld cpi.
	*
	* @param batchId the batch ID of this ivld cpi
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldCpi.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldCpi.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld cpi is check record.
	*
	* @param checkRecord the check record of this ivld cpi
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldCpi.setCheckRecord(checkRecord);
	}

	/**
	* Sets the cpi intfid of this ivld cpi.
	*
	* @param cpiIntfid the cpi intfid of this ivld cpi
	*/
	@Override
	public void setCpiIntfid(java.lang.String cpiIntfid) {
		_ivldCpi.setCpiIntfid(cpiIntfid);
	}

	/**
	* Sets the created by of this ivld cpi.
	*
	* @param createdBy the created by of this ivld cpi
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldCpi.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld cpi.
	*
	* @param createdDate the created date of this ivld cpi
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldCpi.setCreatedDate(createdDate);
	}

	/**
	* Sets the effective date of this ivld cpi.
	*
	* @param effectiveDate the effective date of this ivld cpi
	*/
	@Override
	public void setEffectiveDate(java.lang.String effectiveDate) {
		_ivldCpi.setEffectiveDate(effectiveDate);
	}

	/**
	* Sets the error code of this ivld cpi.
	*
	* @param errorCode the error code of this ivld cpi
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldCpi.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld cpi.
	*
	* @param errorField the error field of this ivld cpi
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldCpi.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldCpi.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldCpi.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldCpi.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the index ID of this ivld cpi.
	*
	* @param indexId the index ID of this ivld cpi
	*/
	@Override
	public void setIndexId(java.lang.String indexId) {
		_ivldCpi.setIndexId(indexId);
	}

	/**
	* Sets the index type of this ivld cpi.
	*
	* @param indexType the index type of this ivld cpi
	*/
	@Override
	public void setIndexType(java.lang.String indexType) {
		_ivldCpi.setIndexType(indexType);
	}

	/**
	* Sets the index value of this ivld cpi.
	*
	* @param indexValue the index value of this ivld cpi
	*/
	@Override
	public void setIndexValue(java.lang.String indexValue) {
		_ivldCpi.setIndexValue(indexValue);
	}

	/**
	* Sets the intf inserted date of this ivld cpi.
	*
	* @param intfInsertedDate the intf inserted date of this ivld cpi
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldCpi.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the ivld cpi sid of this ivld cpi.
	*
	* @param ivldCpiSid the ivld cpi sid of this ivld cpi
	*/
	@Override
	public void setIvldCpiSid(int ivldCpiSid) {
		_ivldCpi.setIvldCpiSid(ivldCpiSid);
	}

	/**
	* Sets the modified by of this ivld cpi.
	*
	* @param modifiedBy the modified by of this ivld cpi
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldCpi.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld cpi.
	*
	* @param modifiedDate the modified date of this ivld cpi
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldCpi.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldCpi.setNew(n);
	}

	/**
	* Sets the primary key of this ivld cpi.
	*
	* @param primaryKey the primary key of this ivld cpi
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldCpi.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldCpi.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld cpi.
	*
	* @param reasonForFailure the reason for failure of this ivld cpi
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldCpi.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld cpi.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld cpi
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldCpi.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld cpi.
	*
	* @param source the source of this ivld cpi
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldCpi.setSource(source);
	}

	/**
	* Sets the status of this ivld cpi.
	*
	* @param status the status of this ivld cpi
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_ivldCpi.setStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldCpi> toCacheModel() {
		return _ivldCpi.toCacheModel();
	}

	@Override
	public IvldCpi toEscapedModel() {
		return new IvldCpiWrapper(_ivldCpi.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldCpi.toString();
	}

	@Override
	public IvldCpi toUnescapedModel() {
		return new IvldCpiWrapper(_ivldCpi.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldCpi.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldCpiWrapper)) {
			return false;
		}

		IvldCpiWrapper ivldCpiWrapper = (IvldCpiWrapper)obj;

		if (Objects.equals(_ivldCpi, ivldCpiWrapper._ivldCpi)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldCpi getWrappedModel() {
		return _ivldCpi;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldCpi.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldCpi.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldCpi.resetOriginalValues();
	}

	private final IvldCpi _ivldCpi;
}