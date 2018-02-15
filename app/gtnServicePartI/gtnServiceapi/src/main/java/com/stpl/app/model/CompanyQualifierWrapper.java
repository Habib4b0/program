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
 * This class is a wrapper for {@link CompanyQualifier}.
 * </p>
 *
 * @author
 * @see CompanyQualifier
 * @generated
 */
@ProviderType
public class CompanyQualifierWrapper implements CompanyQualifier,
	ModelWrapper<CompanyQualifier> {
	public CompanyQualifierWrapper(CompanyQualifier companyQualifier) {
		_companyQualifier = companyQualifier;
	}

	@Override
	public Class<?> getModelClass() {
		return CompanyQualifier.class;
	}

	@Override
	public String getModelClassName() {
		return CompanyQualifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("companyQualifierValue", getCompanyQualifierValue());
		attributes.put("batchId", getBatchId());
		attributes.put("companyQualifierSid", getCompanyQualifierSid());
		attributes.put("companyQualifierName", getCompanyQualifierName());
		attributes.put("effectiveDates", getEffectiveDates());
		attributes.put("notes", getNotes());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
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

		String companyQualifierValue = (String)attributes.get(
				"companyQualifierValue");

		if (companyQualifierValue != null) {
			setCompanyQualifierValue(companyQualifierValue);
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

		String companyQualifierName = (String)attributes.get(
				"companyQualifierName");

		if (companyQualifierName != null) {
			setCompanyQualifierName(companyQualifierName);
		}

		String effectiveDates = (String)attributes.get("effectiveDates");

		if (effectiveDates != null) {
			setEffectiveDates(effectiveDates);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CompanyQualifierWrapper((CompanyQualifier)_companyQualifier.clone());
	}

	@Override
	public int compareTo(CompanyQualifier companyQualifier) {
		return _companyQualifier.compareTo(companyQualifier);
	}

	/**
	* Returns the batch ID of this company qualifier.
	*
	* @return the batch ID of this company qualifier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _companyQualifier.getBatchId();
	}

	/**
	* Returns the company qualifier name of this company qualifier.
	*
	* @return the company qualifier name of this company qualifier
	*/
	@Override
	public java.lang.String getCompanyQualifierName() {
		return _companyQualifier.getCompanyQualifierName();
	}

	/**
	* Returns the company qualifier sid of this company qualifier.
	*
	* @return the company qualifier sid of this company qualifier
	*/
	@Override
	public int getCompanyQualifierSid() {
		return _companyQualifier.getCompanyQualifierSid();
	}

	/**
	* Returns the company qualifier value of this company qualifier.
	*
	* @return the company qualifier value of this company qualifier
	*/
	@Override
	public java.lang.String getCompanyQualifierValue() {
		return _companyQualifier.getCompanyQualifierValue();
	}

	/**
	* Returns the created by of this company qualifier.
	*
	* @return the created by of this company qualifier
	*/
	@Override
	public int getCreatedBy() {
		return _companyQualifier.getCreatedBy();
	}

	/**
	* Returns the created date of this company qualifier.
	*
	* @return the created date of this company qualifier
	*/
	@Override
	public Date getCreatedDate() {
		return _companyQualifier.getCreatedDate();
	}

	/**
	* Returns the effective dates of this company qualifier.
	*
	* @return the effective dates of this company qualifier
	*/
	@Override
	public java.lang.String getEffectiveDates() {
		return _companyQualifier.getEffectiveDates();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _companyQualifier.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this company qualifier.
	*
	* @return the inbound status of this company qualifier
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _companyQualifier.getInboundStatus();
	}

	/**
	* Returns the modified by of this company qualifier.
	*
	* @return the modified by of this company qualifier
	*/
	@Override
	public int getModifiedBy() {
		return _companyQualifier.getModifiedBy();
	}

	/**
	* Returns the modified date of this company qualifier.
	*
	* @return the modified date of this company qualifier
	*/
	@Override
	public Date getModifiedDate() {
		return _companyQualifier.getModifiedDate();
	}

	/**
	* Returns the notes of this company qualifier.
	*
	* @return the notes of this company qualifier
	*/
	@Override
	public java.lang.String getNotes() {
		return _companyQualifier.getNotes();
	}

	/**
	* Returns the primary key of this company qualifier.
	*
	* @return the primary key of this company qualifier
	*/
	@Override
	public int getPrimaryKey() {
		return _companyQualifier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _companyQualifier.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this company qualifier.
	*
	* @return the record lock status of this company qualifier
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _companyQualifier.getRecordLockStatus();
	}

	/**
	* Returns the source of this company qualifier.
	*
	* @return the source of this company qualifier
	*/
	@Override
	public java.lang.String getSource() {
		return _companyQualifier.getSource();
	}

	@Override
	public int hashCode() {
		return _companyQualifier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _companyQualifier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _companyQualifier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _companyQualifier.isNew();
	}

	/**
	* Returns <code>true</code> if this company qualifier is record lock status.
	*
	* @return <code>true</code> if this company qualifier is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _companyQualifier.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_companyQualifier.persist();
	}

	/**
	* Sets the batch ID of this company qualifier.
	*
	* @param batchId the batch ID of this company qualifier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_companyQualifier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_companyQualifier.setCachedModel(cachedModel);
	}

	/**
	* Sets the company qualifier name of this company qualifier.
	*
	* @param companyQualifierName the company qualifier name of this company qualifier
	*/
	@Override
	public void setCompanyQualifierName(java.lang.String companyQualifierName) {
		_companyQualifier.setCompanyQualifierName(companyQualifierName);
	}

	/**
	* Sets the company qualifier sid of this company qualifier.
	*
	* @param companyQualifierSid the company qualifier sid of this company qualifier
	*/
	@Override
	public void setCompanyQualifierSid(int companyQualifierSid) {
		_companyQualifier.setCompanyQualifierSid(companyQualifierSid);
	}

	/**
	* Sets the company qualifier value of this company qualifier.
	*
	* @param companyQualifierValue the company qualifier value of this company qualifier
	*/
	@Override
	public void setCompanyQualifierValue(java.lang.String companyQualifierValue) {
		_companyQualifier.setCompanyQualifierValue(companyQualifierValue);
	}

	/**
	* Sets the created by of this company qualifier.
	*
	* @param createdBy the created by of this company qualifier
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_companyQualifier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this company qualifier.
	*
	* @param createdDate the created date of this company qualifier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_companyQualifier.setCreatedDate(createdDate);
	}

	/**
	* Sets the effective dates of this company qualifier.
	*
	* @param effectiveDates the effective dates of this company qualifier
	*/
	@Override
	public void setEffectiveDates(java.lang.String effectiveDates) {
		_companyQualifier.setEffectiveDates(effectiveDates);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_companyQualifier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_companyQualifier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_companyQualifier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this company qualifier.
	*
	* @param inboundStatus the inbound status of this company qualifier
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_companyQualifier.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this company qualifier.
	*
	* @param modifiedBy the modified by of this company qualifier
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_companyQualifier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this company qualifier.
	*
	* @param modifiedDate the modified date of this company qualifier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_companyQualifier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_companyQualifier.setNew(n);
	}

	/**
	* Sets the notes of this company qualifier.
	*
	* @param notes the notes of this company qualifier
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_companyQualifier.setNotes(notes);
	}

	/**
	* Sets the primary key of this company qualifier.
	*
	* @param primaryKey the primary key of this company qualifier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_companyQualifier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_companyQualifier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this company qualifier is record lock status.
	*
	* @param recordLockStatus the record lock status of this company qualifier
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_companyQualifier.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this company qualifier.
	*
	* @param source the source of this company qualifier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_companyQualifier.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CompanyQualifier> toCacheModel() {
		return _companyQualifier.toCacheModel();
	}

	@Override
	public CompanyQualifier toEscapedModel() {
		return new CompanyQualifierWrapper(_companyQualifier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _companyQualifier.toString();
	}

	@Override
	public CompanyQualifier toUnescapedModel() {
		return new CompanyQualifierWrapper(_companyQualifier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _companyQualifier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyQualifierWrapper)) {
			return false;
		}

		CompanyQualifierWrapper companyQualifierWrapper = (CompanyQualifierWrapper)obj;

		if (Objects.equals(_companyQualifier,
					companyQualifierWrapper._companyQualifier)) {
			return true;
		}

		return false;
	}

	@Override
	public CompanyQualifier getWrappedModel() {
		return _companyQualifier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _companyQualifier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _companyQualifier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_companyQualifier.resetOriginalValues();
	}

	private final CompanyQualifier _companyQualifier;
}