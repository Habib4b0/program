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

package com.stpl.app.parttwo.model;

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
 * This class is a wrapper for {@link IvldcompanyIdentifier}.
 * </p>
 *
 * @author
 * @see IvldcompanyIdentifier
 * @generated
 */
@ProviderType
public class IvldcompanyIdentifierWrapper implements IvldcompanyIdentifier,
	ModelWrapper<IvldcompanyIdentifier> {
	public IvldcompanyIdentifierWrapper(
		IvldcompanyIdentifier ivldcompanyIdentifier) {
		_ivldcompanyIdentifier = ivldcompanyIdentifier;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldcompanyIdentifier.class;
	}

	@Override
	public String getModelClassName() {
		return IvldcompanyIdentifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("companyId", getCompanyId());
		attributes.put("companyName", getCompanyName());
		attributes.put("endDate", getEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("identifierStatus", getIdentifierStatus());
		attributes.put("companyIdentifier", getCompanyIdentifier());
		attributes.put("entityCode", getEntityCode());
		attributes.put("companyIdentifierIntfid", getCompanyIdentifierIntfid());
		attributes.put("startDate", getStartDate());
		attributes.put("source", getSource());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("batchId", getBatchId());
		attributes.put("errorField", getErrorField());
		attributes.put("errorCode", getErrorCode());
		attributes.put("identifierCodeQualifierName",
			getIdentifierCodeQualifierName());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("ivldcompanyIdentifierSid", getIvldcompanyIdentifierSid());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String companyId = (String)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String endDate = (String)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String identifierStatus = (String)attributes.get("identifierStatus");

		if (identifierStatus != null) {
			setIdentifierStatus(identifierStatus);
		}

		String companyIdentifier = (String)attributes.get("companyIdentifier");

		if (companyIdentifier != null) {
			setCompanyIdentifier(companyIdentifier);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		String companyIdentifierIntfid = (String)attributes.get(
				"companyIdentifierIntfid");

		if (companyIdentifierIntfid != null) {
			setCompanyIdentifierIntfid(companyIdentifierIntfid);
		}

		String startDate = (String)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		String identifierCodeQualifierName = (String)attributes.get(
				"identifierCodeQualifierName");

		if (identifierCodeQualifierName != null) {
			setIdentifierCodeQualifierName(identifierCodeQualifierName);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer ivldcompanyIdentifierSid = (Integer)attributes.get(
				"ivldcompanyIdentifierSid");

		if (ivldcompanyIdentifierSid != null) {
			setIvldcompanyIdentifierSid(ivldcompanyIdentifierSid);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String identifierCodeQualifier = (String)attributes.get(
				"identifierCodeQualifier");

		if (identifierCodeQualifier != null) {
			setIdentifierCodeQualifier(identifierCodeQualifier);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldcompanyIdentifierWrapper((IvldcompanyIdentifier)_ivldcompanyIdentifier.clone());
	}

	@Override
	public int compareTo(IvldcompanyIdentifier ivldcompanyIdentifier) {
		return _ivldcompanyIdentifier.compareTo(ivldcompanyIdentifier);
	}

	/**
	* Returns the add chg del indicator of this ivldcompany identifier.
	*
	* @return the add chg del indicator of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldcompanyIdentifier.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivldcompany identifier.
	*
	* @return the batch ID of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldcompanyIdentifier.getBatchId();
	}

	/**
	* Returns the check record of this ivldcompany identifier.
	*
	* @return the check record of this ivldcompany identifier
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldcompanyIdentifier.getCheckRecord();
	}

	/**
	* Returns the company ID of this ivldcompany identifier.
	*
	* @return the company ID of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getCompanyId() {
		return _ivldcompanyIdentifier.getCompanyId();
	}

	/**
	* Returns the company identifier of this ivldcompany identifier.
	*
	* @return the company identifier of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getCompanyIdentifier() {
		return _ivldcompanyIdentifier.getCompanyIdentifier();
	}

	/**
	* Returns the company identifier intfid of this ivldcompany identifier.
	*
	* @return the company identifier intfid of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getCompanyIdentifierIntfid() {
		return _ivldcompanyIdentifier.getCompanyIdentifierIntfid();
	}

	/**
	* Returns the company name of this ivldcompany identifier.
	*
	* @return the company name of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _ivldcompanyIdentifier.getCompanyName();
	}

	/**
	* Returns the company no of this ivldcompany identifier.
	*
	* @return the company no of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _ivldcompanyIdentifier.getCompanyNo();
	}

	/**
	* Returns the created by of this ivldcompany identifier.
	*
	* @return the created by of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldcompanyIdentifier.getCreatedBy();
	}

	/**
	* Returns the created date of this ivldcompany identifier.
	*
	* @return the created date of this ivldcompany identifier
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldcompanyIdentifier.getCreatedDate();
	}

	/**
	* Returns the end date of this ivldcompany identifier.
	*
	* @return the end date of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getEndDate() {
		return _ivldcompanyIdentifier.getEndDate();
	}

	/**
	* Returns the entity code of this ivldcompany identifier.
	*
	* @return the entity code of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getEntityCode() {
		return _ivldcompanyIdentifier.getEntityCode();
	}

	/**
	* Returns the error code of this ivldcompany identifier.
	*
	* @return the error code of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldcompanyIdentifier.getErrorCode();
	}

	/**
	* Returns the error field of this ivldcompany identifier.
	*
	* @return the error field of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldcompanyIdentifier.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldcompanyIdentifier.getExpandoBridge();
	}

	/**
	* Returns the identifier code qualifier of this ivldcompany identifier.
	*
	* @return the identifier code qualifier of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifier() {
		return _ivldcompanyIdentifier.getIdentifierCodeQualifier();
	}

	/**
	* Returns the identifier code qualifier name of this ivldcompany identifier.
	*
	* @return the identifier code qualifier name of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifierName() {
		return _ivldcompanyIdentifier.getIdentifierCodeQualifierName();
	}

	/**
	* Returns the identifier status of this ivldcompany identifier.
	*
	* @return the identifier status of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getIdentifierStatus() {
		return _ivldcompanyIdentifier.getIdentifierStatus();
	}

	/**
	* Returns the intf inserted date of this ivldcompany identifier.
	*
	* @return the intf inserted date of this ivldcompany identifier
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldcompanyIdentifier.getIntfInsertedDate();
	}

	/**
	* Returns the ivldcompany identifier sid of this ivldcompany identifier.
	*
	* @return the ivldcompany identifier sid of this ivldcompany identifier
	*/
	@Override
	public int getIvldcompanyIdentifierSid() {
		return _ivldcompanyIdentifier.getIvldcompanyIdentifierSid();
	}

	/**
	* Returns the modified by of this ivldcompany identifier.
	*
	* @return the modified by of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldcompanyIdentifier.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivldcompany identifier.
	*
	* @return the modified date of this ivldcompany identifier
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldcompanyIdentifier.getModifiedDate();
	}

	/**
	* Returns the primary key of this ivldcompany identifier.
	*
	* @return the primary key of this ivldcompany identifier
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldcompanyIdentifier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldcompanyIdentifier.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivldcompany identifier.
	*
	* @return the reason for failure of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldcompanyIdentifier.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivldcompany identifier.
	*
	* @return the reprocessed flag of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldcompanyIdentifier.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivldcompany identifier.
	*
	* @return the source of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldcompanyIdentifier.getSource();
	}

	/**
	* Returns the start date of this ivldcompany identifier.
	*
	* @return the start date of this ivldcompany identifier
	*/
	@Override
	public java.lang.String getStartDate() {
		return _ivldcompanyIdentifier.getStartDate();
	}

	@Override
	public int hashCode() {
		return _ivldcompanyIdentifier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldcompanyIdentifier.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivldcompany identifier is check record.
	*
	* @return <code>true</code> if this ivldcompany identifier is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldcompanyIdentifier.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldcompanyIdentifier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldcompanyIdentifier.isNew();
	}

	@Override
	public void persist() {
		_ivldcompanyIdentifier.persist();
	}

	/**
	* Sets the add chg del indicator of this ivldcompany identifier.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivldcompany identifier
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldcompanyIdentifier.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivldcompany identifier.
	*
	* @param batchId the batch ID of this ivldcompany identifier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldcompanyIdentifier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldcompanyIdentifier.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivldcompany identifier is check record.
	*
	* @param checkRecord the check record of this ivldcompany identifier
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldcompanyIdentifier.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company ID of this ivldcompany identifier.
	*
	* @param companyId the company ID of this ivldcompany identifier
	*/
	@Override
	public void setCompanyId(java.lang.String companyId) {
		_ivldcompanyIdentifier.setCompanyId(companyId);
	}

	/**
	* Sets the company identifier of this ivldcompany identifier.
	*
	* @param companyIdentifier the company identifier of this ivldcompany identifier
	*/
	@Override
	public void setCompanyIdentifier(java.lang.String companyIdentifier) {
		_ivldcompanyIdentifier.setCompanyIdentifier(companyIdentifier);
	}

	/**
	* Sets the company identifier intfid of this ivldcompany identifier.
	*
	* @param companyIdentifierIntfid the company identifier intfid of this ivldcompany identifier
	*/
	@Override
	public void setCompanyIdentifierIntfid(
		java.lang.String companyIdentifierIntfid) {
		_ivldcompanyIdentifier.setCompanyIdentifierIntfid(companyIdentifierIntfid);
	}

	/**
	* Sets the company name of this ivldcompany identifier.
	*
	* @param companyName the company name of this ivldcompany identifier
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_ivldcompanyIdentifier.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this ivldcompany identifier.
	*
	* @param companyNo the company no of this ivldcompany identifier
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_ivldcompanyIdentifier.setCompanyNo(companyNo);
	}

	/**
	* Sets the created by of this ivldcompany identifier.
	*
	* @param createdBy the created by of this ivldcompany identifier
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldcompanyIdentifier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivldcompany identifier.
	*
	* @param createdDate the created date of this ivldcompany identifier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldcompanyIdentifier.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this ivldcompany identifier.
	*
	* @param endDate the end date of this ivldcompany identifier
	*/
	@Override
	public void setEndDate(java.lang.String endDate) {
		_ivldcompanyIdentifier.setEndDate(endDate);
	}

	/**
	* Sets the entity code of this ivldcompany identifier.
	*
	* @param entityCode the entity code of this ivldcompany identifier
	*/
	@Override
	public void setEntityCode(java.lang.String entityCode) {
		_ivldcompanyIdentifier.setEntityCode(entityCode);
	}

	/**
	* Sets the error code of this ivldcompany identifier.
	*
	* @param errorCode the error code of this ivldcompany identifier
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldcompanyIdentifier.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivldcompany identifier.
	*
	* @param errorField the error field of this ivldcompany identifier
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldcompanyIdentifier.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldcompanyIdentifier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldcompanyIdentifier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldcompanyIdentifier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the identifier code qualifier of this ivldcompany identifier.
	*
	* @param identifierCodeQualifier the identifier code qualifier of this ivldcompany identifier
	*/
	@Override
	public void setIdentifierCodeQualifier(
		java.lang.String identifierCodeQualifier) {
		_ivldcompanyIdentifier.setIdentifierCodeQualifier(identifierCodeQualifier);
	}

	/**
	* Sets the identifier code qualifier name of this ivldcompany identifier.
	*
	* @param identifierCodeQualifierName the identifier code qualifier name of this ivldcompany identifier
	*/
	@Override
	public void setIdentifierCodeQualifierName(
		java.lang.String identifierCodeQualifierName) {
		_ivldcompanyIdentifier.setIdentifierCodeQualifierName(identifierCodeQualifierName);
	}

	/**
	* Sets the identifier status of this ivldcompany identifier.
	*
	* @param identifierStatus the identifier status of this ivldcompany identifier
	*/
	@Override
	public void setIdentifierStatus(java.lang.String identifierStatus) {
		_ivldcompanyIdentifier.setIdentifierStatus(identifierStatus);
	}

	/**
	* Sets the intf inserted date of this ivldcompany identifier.
	*
	* @param intfInsertedDate the intf inserted date of this ivldcompany identifier
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldcompanyIdentifier.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the ivldcompany identifier sid of this ivldcompany identifier.
	*
	* @param ivldcompanyIdentifierSid the ivldcompany identifier sid of this ivldcompany identifier
	*/
	@Override
	public void setIvldcompanyIdentifierSid(int ivldcompanyIdentifierSid) {
		_ivldcompanyIdentifier.setIvldcompanyIdentifierSid(ivldcompanyIdentifierSid);
	}

	/**
	* Sets the modified by of this ivldcompany identifier.
	*
	* @param modifiedBy the modified by of this ivldcompany identifier
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldcompanyIdentifier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivldcompany identifier.
	*
	* @param modifiedDate the modified date of this ivldcompany identifier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldcompanyIdentifier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldcompanyIdentifier.setNew(n);
	}

	/**
	* Sets the primary key of this ivldcompany identifier.
	*
	* @param primaryKey the primary key of this ivldcompany identifier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldcompanyIdentifier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldcompanyIdentifier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivldcompany identifier.
	*
	* @param reasonForFailure the reason for failure of this ivldcompany identifier
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldcompanyIdentifier.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivldcompany identifier.
	*
	* @param reprocessedFlag the reprocessed flag of this ivldcompany identifier
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldcompanyIdentifier.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivldcompany identifier.
	*
	* @param source the source of this ivldcompany identifier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldcompanyIdentifier.setSource(source);
	}

	/**
	* Sets the start date of this ivldcompany identifier.
	*
	* @param startDate the start date of this ivldcompany identifier
	*/
	@Override
	public void setStartDate(java.lang.String startDate) {
		_ivldcompanyIdentifier.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldcompanyIdentifier> toCacheModel() {
		return _ivldcompanyIdentifier.toCacheModel();
	}

	@Override
	public IvldcompanyIdentifier toEscapedModel() {
		return new IvldcompanyIdentifierWrapper(_ivldcompanyIdentifier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldcompanyIdentifier.toString();
	}

	@Override
	public IvldcompanyIdentifier toUnescapedModel() {
		return new IvldcompanyIdentifierWrapper(_ivldcompanyIdentifier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldcompanyIdentifier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldcompanyIdentifierWrapper)) {
			return false;
		}

		IvldcompanyIdentifierWrapper ivldcompanyIdentifierWrapper = (IvldcompanyIdentifierWrapper)obj;

		if (Objects.equals(_ivldcompanyIdentifier,
					ivldcompanyIdentifierWrapper._ivldcompanyIdentifier)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldcompanyIdentifier getWrappedModel() {
		return _ivldcompanyIdentifier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldcompanyIdentifier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldcompanyIdentifier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldcompanyIdentifier.resetOriginalValues();
	}

	private final IvldcompanyIdentifier _ivldcompanyIdentifier;
}