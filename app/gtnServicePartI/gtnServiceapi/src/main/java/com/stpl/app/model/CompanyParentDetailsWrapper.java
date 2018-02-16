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
 * This class is a wrapper for {@link CompanyParentDetails}.
 * </p>
 *
 * @author
 * @see CompanyParentDetails
 * @generated
 */
@ProviderType
public class CompanyParentDetailsWrapper implements CompanyParentDetails,
	ModelWrapper<CompanyParentDetails> {
	public CompanyParentDetailsWrapper(
		CompanyParentDetails companyParentDetails) {
		_companyParentDetails = companyParentDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CompanyParentDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CompanyParentDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("parentEndDate", getParentEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentCompanyMasterSid", getParentCompanyMasterSid());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("priorParentStartDate", getPriorParentStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
		attributes.put("priorParentCmpyMasterSid", getPriorParentCmpyMasterSid());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("parentStartDate", getParentStartDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastUpdatedDate = (Date)attributes.get("lastUpdatedDate");

		if (lastUpdatedDate != null) {
			setLastUpdatedDate(lastUpdatedDate);
		}

		Date parentEndDate = (Date)attributes.get("parentEndDate");

		if (parentEndDate != null) {
			setParentEndDate(parentEndDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer parentCompanyMasterSid = (Integer)attributes.get(
				"parentCompanyMasterSid");

		if (parentCompanyMasterSid != null) {
			setParentCompanyMasterSid(parentCompanyMasterSid);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date priorParentStartDate = (Date)attributes.get("priorParentStartDate");

		if (priorParentStartDate != null) {
			setPriorParentStartDate(priorParentStartDate);
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

		Integer companyParentDetailsSid = (Integer)attributes.get(
				"companyParentDetailsSid");

		if (companyParentDetailsSid != null) {
			setCompanyParentDetailsSid(companyParentDetailsSid);
		}

		String priorParentCmpyMasterSid = (String)attributes.get(
				"priorParentCmpyMasterSid");

		if (priorParentCmpyMasterSid != null) {
			setPriorParentCmpyMasterSid(priorParentCmpyMasterSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
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

		Date parentStartDate = (Date)attributes.get("parentStartDate");

		if (parentStartDate != null) {
			setParentStartDate(parentStartDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CompanyParentDetailsWrapper((CompanyParentDetails)_companyParentDetails.clone());
	}

	@Override
	public int compareTo(CompanyParentDetails companyParentDetails) {
		return _companyParentDetails.compareTo(companyParentDetails);
	}

	/**
	* Returns the batch ID of this company parent details.
	*
	* @return the batch ID of this company parent details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _companyParentDetails.getBatchId();
	}

	/**
	* Returns the company master sid of this company parent details.
	*
	* @return the company master sid of this company parent details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _companyParentDetails.getCompanyMasterSid();
	}

	/**
	* Returns the company parent details sid of this company parent details.
	*
	* @return the company parent details sid of this company parent details
	*/
	@Override
	public int getCompanyParentDetailsSid() {
		return _companyParentDetails.getCompanyParentDetailsSid();
	}

	/**
	* Returns the created by of this company parent details.
	*
	* @return the created by of this company parent details
	*/
	@Override
	public int getCreatedBy() {
		return _companyParentDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this company parent details.
	*
	* @return the created date of this company parent details
	*/
	@Override
	public Date getCreatedDate() {
		return _companyParentDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _companyParentDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this company parent details.
	*
	* @return the inbound status of this company parent details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _companyParentDetails.getInboundStatus();
	}

	/**
	* Returns the last updated date of this company parent details.
	*
	* @return the last updated date of this company parent details
	*/
	@Override
	public Date getLastUpdatedDate() {
		return _companyParentDetails.getLastUpdatedDate();
	}

	/**
	* Returns the modified by of this company parent details.
	*
	* @return the modified by of this company parent details
	*/
	@Override
	public int getModifiedBy() {
		return _companyParentDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this company parent details.
	*
	* @return the modified date of this company parent details
	*/
	@Override
	public Date getModifiedDate() {
		return _companyParentDetails.getModifiedDate();
	}

	/**
	* Returns the parent company master sid of this company parent details.
	*
	* @return the parent company master sid of this company parent details
	*/
	@Override
	public int getParentCompanyMasterSid() {
		return _companyParentDetails.getParentCompanyMasterSid();
	}

	/**
	* Returns the parent end date of this company parent details.
	*
	* @return the parent end date of this company parent details
	*/
	@Override
	public Date getParentEndDate() {
		return _companyParentDetails.getParentEndDate();
	}

	/**
	* Returns the parent start date of this company parent details.
	*
	* @return the parent start date of this company parent details
	*/
	@Override
	public Date getParentStartDate() {
		return _companyParentDetails.getParentStartDate();
	}

	/**
	* Returns the primary key of this company parent details.
	*
	* @return the primary key of this company parent details
	*/
	@Override
	public int getPrimaryKey() {
		return _companyParentDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _companyParentDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the prior parent cmpy master sid of this company parent details.
	*
	* @return the prior parent cmpy master sid of this company parent details
	*/
	@Override
	public java.lang.String getPriorParentCmpyMasterSid() {
		return _companyParentDetails.getPriorParentCmpyMasterSid();
	}

	/**
	* Returns the prior parent start date of this company parent details.
	*
	* @return the prior parent start date of this company parent details
	*/
	@Override
	public Date getPriorParentStartDate() {
		return _companyParentDetails.getPriorParentStartDate();
	}

	/**
	* Returns the record lock status of this company parent details.
	*
	* @return the record lock status of this company parent details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _companyParentDetails.getRecordLockStatus();
	}

	/**
	* Returns the source of this company parent details.
	*
	* @return the source of this company parent details
	*/
	@Override
	public java.lang.String getSource() {
		return _companyParentDetails.getSource();
	}

	@Override
	public int hashCode() {
		return _companyParentDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _companyParentDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _companyParentDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _companyParentDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this company parent details is record lock status.
	*
	* @return <code>true</code> if this company parent details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _companyParentDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_companyParentDetails.persist();
	}

	/**
	* Sets the batch ID of this company parent details.
	*
	* @param batchId the batch ID of this company parent details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_companyParentDetails.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_companyParentDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the company master sid of this company parent details.
	*
	* @param companyMasterSid the company master sid of this company parent details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_companyParentDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company parent details sid of this company parent details.
	*
	* @param companyParentDetailsSid the company parent details sid of this company parent details
	*/
	@Override
	public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
		_companyParentDetails.setCompanyParentDetailsSid(companyParentDetailsSid);
	}

	/**
	* Sets the created by of this company parent details.
	*
	* @param createdBy the created by of this company parent details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_companyParentDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this company parent details.
	*
	* @param createdDate the created date of this company parent details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_companyParentDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_companyParentDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_companyParentDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_companyParentDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this company parent details.
	*
	* @param inboundStatus the inbound status of this company parent details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_companyParentDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the last updated date of this company parent details.
	*
	* @param lastUpdatedDate the last updated date of this company parent details
	*/
	@Override
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_companyParentDetails.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the modified by of this company parent details.
	*
	* @param modifiedBy the modified by of this company parent details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_companyParentDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this company parent details.
	*
	* @param modifiedDate the modified date of this company parent details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_companyParentDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_companyParentDetails.setNew(n);
	}

	/**
	* Sets the parent company master sid of this company parent details.
	*
	* @param parentCompanyMasterSid the parent company master sid of this company parent details
	*/
	@Override
	public void setParentCompanyMasterSid(int parentCompanyMasterSid) {
		_companyParentDetails.setParentCompanyMasterSid(parentCompanyMasterSid);
	}

	/**
	* Sets the parent end date of this company parent details.
	*
	* @param parentEndDate the parent end date of this company parent details
	*/
	@Override
	public void setParentEndDate(Date parentEndDate) {
		_companyParentDetails.setParentEndDate(parentEndDate);
	}

	/**
	* Sets the parent start date of this company parent details.
	*
	* @param parentStartDate the parent start date of this company parent details
	*/
	@Override
	public void setParentStartDate(Date parentStartDate) {
		_companyParentDetails.setParentStartDate(parentStartDate);
	}

	/**
	* Sets the primary key of this company parent details.
	*
	* @param primaryKey the primary key of this company parent details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_companyParentDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_companyParentDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the prior parent cmpy master sid of this company parent details.
	*
	* @param priorParentCmpyMasterSid the prior parent cmpy master sid of this company parent details
	*/
	@Override
	public void setPriorParentCmpyMasterSid(
		java.lang.String priorParentCmpyMasterSid) {
		_companyParentDetails.setPriorParentCmpyMasterSid(priorParentCmpyMasterSid);
	}

	/**
	* Sets the prior parent start date of this company parent details.
	*
	* @param priorParentStartDate the prior parent start date of this company parent details
	*/
	@Override
	public void setPriorParentStartDate(Date priorParentStartDate) {
		_companyParentDetails.setPriorParentStartDate(priorParentStartDate);
	}

	/**
	* Sets whether this company parent details is record lock status.
	*
	* @param recordLockStatus the record lock status of this company parent details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_companyParentDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this company parent details.
	*
	* @param source the source of this company parent details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_companyParentDetails.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CompanyParentDetails> toCacheModel() {
		return _companyParentDetails.toCacheModel();
	}

	@Override
	public CompanyParentDetails toEscapedModel() {
		return new CompanyParentDetailsWrapper(_companyParentDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _companyParentDetails.toString();
	}

	@Override
	public CompanyParentDetails toUnescapedModel() {
		return new CompanyParentDetailsWrapper(_companyParentDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _companyParentDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyParentDetailsWrapper)) {
			return false;
		}

		CompanyParentDetailsWrapper companyParentDetailsWrapper = (CompanyParentDetailsWrapper)obj;

		if (Objects.equals(_companyParentDetails,
					companyParentDetailsWrapper._companyParentDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CompanyParentDetails getWrappedModel() {
		return _companyParentDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _companyParentDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _companyParentDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_companyParentDetails.resetOriginalValues();
	}

	private final CompanyParentDetails _companyParentDetails;
}