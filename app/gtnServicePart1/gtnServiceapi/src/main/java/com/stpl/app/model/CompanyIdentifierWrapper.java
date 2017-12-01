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
 * This class is a wrapper for {@link CompanyIdentifier}.
 * </p>
 *
 * @author
 * @see CompanyIdentifier
 * @generated
 */
@ProviderType
public class CompanyIdentifierWrapper implements CompanyIdentifier,
	ModelWrapper<CompanyIdentifier> {
	public CompanyIdentifierWrapper(CompanyIdentifier companyIdentifier) {
		_companyIdentifier = companyIdentifier;
	}

	@Override
	public Class<?> getModelClass() {
		return CompanyIdentifier.class;
	}

	@Override
	public String getModelClassName() {
		return CompanyIdentifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("endDate", getEndDate());
		attributes.put("companyStringIdentifierSid",
			getCompanyStringIdentifierSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("identifierStatus", getIdentifierStatus());
		attributes.put("entityCode", getEntityCode());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("companyStringIdentifierValue",
			getCompanyStringIdentifierValue());
		attributes.put("batchId", getBatchId());
		attributes.put("companyQualifierSid", getCompanyQualifierSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("companyMasterSid", getCompanyMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Integer companyStringIdentifierSid = (Integer)attributes.get(
				"companyStringIdentifierSid");

		if (companyStringIdentifierSid != null) {
			setCompanyStringIdentifierSid(companyStringIdentifierSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer identifierStatus = (Integer)attributes.get("identifierStatus");

		if (identifierStatus != null) {
			setIdentifierStatus(identifierStatus);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String companyStringIdentifierValue = (String)attributes.get(
				"companyStringIdentifierValue");

		if (companyStringIdentifierValue != null) {
			setCompanyStringIdentifierValue(companyStringIdentifierValue);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer companyQualifierSid = (Integer)attributes.get(
				"companyQualifierSid");

		if (companyQualifierSid != null) {
			setCompanyQualifierSid(companyQualifierSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CompanyIdentifierWrapper((CompanyIdentifier)_companyIdentifier.clone());
	}

	@Override
	public int compareTo(CompanyIdentifier companyIdentifier) {
		return _companyIdentifier.compareTo(companyIdentifier);
	}

	/**
	* Returns the batch ID of this company identifier.
	*
	* @return the batch ID of this company identifier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _companyIdentifier.getBatchId();
	}

	/**
	* Returns the company master sid of this company identifier.
	*
	* @return the company master sid of this company identifier
	*/
	@Override
	public int getCompanyMasterSid() {
		return _companyIdentifier.getCompanyMasterSid();
	}

	/**
	* Returns the company qualifier sid of this company identifier.
	*
	* @return the company qualifier sid of this company identifier
	*/
	@Override
	public int getCompanyQualifierSid() {
		return _companyIdentifier.getCompanyQualifierSid();
	}

	/**
	* Returns the company string identifier sid of this company identifier.
	*
	* @return the company string identifier sid of this company identifier
	*/
	@Override
	public int getCompanyStringIdentifierSid() {
		return _companyIdentifier.getCompanyStringIdentifierSid();
	}

	/**
	* Returns the company string identifier value of this company identifier.
	*
	* @return the company string identifier value of this company identifier
	*/
	@Override
	public java.lang.String getCompanyStringIdentifierValue() {
		return _companyIdentifier.getCompanyStringIdentifierValue();
	}

	/**
	* Returns the created by of this company identifier.
	*
	* @return the created by of this company identifier
	*/
	@Override
	public int getCreatedBy() {
		return _companyIdentifier.getCreatedBy();
	}

	/**
	* Returns the created date of this company identifier.
	*
	* @return the created date of this company identifier
	*/
	@Override
	public Date getCreatedDate() {
		return _companyIdentifier.getCreatedDate();
	}

	/**
	* Returns the end date of this company identifier.
	*
	* @return the end date of this company identifier
	*/
	@Override
	public Date getEndDate() {
		return _companyIdentifier.getEndDate();
	}

	/**
	* Returns the entity code of this company identifier.
	*
	* @return the entity code of this company identifier
	*/
	@Override
	public java.lang.String getEntityCode() {
		return _companyIdentifier.getEntityCode();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _companyIdentifier.getExpandoBridge();
	}

	/**
	* Returns the identifier status of this company identifier.
	*
	* @return the identifier status of this company identifier
	*/
	@Override
	public int getIdentifierStatus() {
		return _companyIdentifier.getIdentifierStatus();
	}

	/**
	* Returns the inbound status of this company identifier.
	*
	* @return the inbound status of this company identifier
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _companyIdentifier.getInboundStatus();
	}

	/**
	* Returns the modified by of this company identifier.
	*
	* @return the modified by of this company identifier
	*/
	@Override
	public int getModifiedBy() {
		return _companyIdentifier.getModifiedBy();
	}

	/**
	* Returns the modified date of this company identifier.
	*
	* @return the modified date of this company identifier
	*/
	@Override
	public Date getModifiedDate() {
		return _companyIdentifier.getModifiedDate();
	}

	/**
	* Returns the primary key of this company identifier.
	*
	* @return the primary key of this company identifier
	*/
	@Override
	public int getPrimaryKey() {
		return _companyIdentifier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _companyIdentifier.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this company identifier.
	*
	* @return the record lock status of this company identifier
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _companyIdentifier.getRecordLockStatus();
	}

	/**
	* Returns the source of this company identifier.
	*
	* @return the source of this company identifier
	*/
	@Override
	public java.lang.String getSource() {
		return _companyIdentifier.getSource();
	}

	/**
	* Returns the start date of this company identifier.
	*
	* @return the start date of this company identifier
	*/
	@Override
	public Date getStartDate() {
		return _companyIdentifier.getStartDate();
	}

	@Override
	public int hashCode() {
		return _companyIdentifier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _companyIdentifier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _companyIdentifier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _companyIdentifier.isNew();
	}

	/**
	* Returns <code>true</code> if this company identifier is record lock status.
	*
	* @return <code>true</code> if this company identifier is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _companyIdentifier.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_companyIdentifier.persist();
	}

	/**
	* Sets the batch ID of this company identifier.
	*
	* @param batchId the batch ID of this company identifier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_companyIdentifier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_companyIdentifier.setCachedModel(cachedModel);
	}

	/**
	* Sets the company master sid of this company identifier.
	*
	* @param companyMasterSid the company master sid of this company identifier
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_companyIdentifier.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company qualifier sid of this company identifier.
	*
	* @param companyQualifierSid the company qualifier sid of this company identifier
	*/
	@Override
	public void setCompanyQualifierSid(int companyQualifierSid) {
		_companyIdentifier.setCompanyQualifierSid(companyQualifierSid);
	}

	/**
	* Sets the company string identifier sid of this company identifier.
	*
	* @param companyStringIdentifierSid the company string identifier sid of this company identifier
	*/
	@Override
	public void setCompanyStringIdentifierSid(int companyStringIdentifierSid) {
		_companyIdentifier.setCompanyStringIdentifierSid(companyStringIdentifierSid);
	}

	/**
	* Sets the company string identifier value of this company identifier.
	*
	* @param companyStringIdentifierValue the company string identifier value of this company identifier
	*/
	@Override
	public void setCompanyStringIdentifierValue(
		java.lang.String companyStringIdentifierValue) {
		_companyIdentifier.setCompanyStringIdentifierValue(companyStringIdentifierValue);
	}

	/**
	* Sets the created by of this company identifier.
	*
	* @param createdBy the created by of this company identifier
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_companyIdentifier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this company identifier.
	*
	* @param createdDate the created date of this company identifier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_companyIdentifier.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this company identifier.
	*
	* @param endDate the end date of this company identifier
	*/
	@Override
	public void setEndDate(Date endDate) {
		_companyIdentifier.setEndDate(endDate);
	}

	/**
	* Sets the entity code of this company identifier.
	*
	* @param entityCode the entity code of this company identifier
	*/
	@Override
	public void setEntityCode(java.lang.String entityCode) {
		_companyIdentifier.setEntityCode(entityCode);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_companyIdentifier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_companyIdentifier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_companyIdentifier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the identifier status of this company identifier.
	*
	* @param identifierStatus the identifier status of this company identifier
	*/
	@Override
	public void setIdentifierStatus(int identifierStatus) {
		_companyIdentifier.setIdentifierStatus(identifierStatus);
	}

	/**
	* Sets the inbound status of this company identifier.
	*
	* @param inboundStatus the inbound status of this company identifier
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_companyIdentifier.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this company identifier.
	*
	* @param modifiedBy the modified by of this company identifier
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_companyIdentifier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this company identifier.
	*
	* @param modifiedDate the modified date of this company identifier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_companyIdentifier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_companyIdentifier.setNew(n);
	}

	/**
	* Sets the primary key of this company identifier.
	*
	* @param primaryKey the primary key of this company identifier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_companyIdentifier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_companyIdentifier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this company identifier is record lock status.
	*
	* @param recordLockStatus the record lock status of this company identifier
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_companyIdentifier.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this company identifier.
	*
	* @param source the source of this company identifier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_companyIdentifier.setSource(source);
	}

	/**
	* Sets the start date of this company identifier.
	*
	* @param startDate the start date of this company identifier
	*/
	@Override
	public void setStartDate(Date startDate) {
		_companyIdentifier.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CompanyIdentifier> toCacheModel() {
		return _companyIdentifier.toCacheModel();
	}

	@Override
	public CompanyIdentifier toEscapedModel() {
		return new CompanyIdentifierWrapper(_companyIdentifier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _companyIdentifier.toString();
	}

	@Override
	public CompanyIdentifier toUnescapedModel() {
		return new CompanyIdentifierWrapper(_companyIdentifier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _companyIdentifier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyIdentifierWrapper)) {
			return false;
		}

		CompanyIdentifierWrapper companyIdentifierWrapper = (CompanyIdentifierWrapper)obj;

		if (Objects.equals(_companyIdentifier,
					companyIdentifierWrapper._companyIdentifier)) {
			return true;
		}

		return false;
	}

	@Override
	public CompanyIdentifier getWrappedModel() {
		return _companyIdentifier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _companyIdentifier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _companyIdentifier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_companyIdentifier.resetOriginalValues();
	}

	private final CompanyIdentifier _companyIdentifier;
}