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
 * This class is a wrapper for {@link CfpDetails}.
 * </p>
 *
 * @author
 * @see CfpDetails
 * @generated
 */
@ProviderType
public class CfpDetailsWrapper implements CfpDetails, ModelWrapper<CfpDetails> {
	public CfpDetailsWrapper(CfpDetails cfpDetails) {
		_cfpDetails = cfpDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CfpDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CfpDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("companyCfpAttachedStatus", getCompanyCfpAttachedStatus());
		attributes.put("tradeClass", getTradeClass());
		attributes.put("tradeClassEndDate", getTradeClassEndDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("companyStartDate", getCompanyStartDate());
		attributes.put("tradeClassStartDate", getTradeClassStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("companyEndDate", getCompanyEndDate());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("companyCfpAttachedDate", getCompanyCfpAttachedDate());
		attributes.put("cfpModelSid", getCfpModelSid());
		attributes.put("batchId", getBatchId());
		attributes.put("cfpDetailsSid", getCfpDetailsSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("source", getSource());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer companyCfpAttachedStatus = (Integer)attributes.get(
				"companyCfpAttachedStatus");

		if (companyCfpAttachedStatus != null) {
			setCompanyCfpAttachedStatus(companyCfpAttachedStatus);
		}

		Integer tradeClass = (Integer)attributes.get("tradeClass");

		if (tradeClass != null) {
			setTradeClass(tradeClass);
		}

		Date tradeClassEndDate = (Date)attributes.get("tradeClassEndDate");

		if (tradeClassEndDate != null) {
			setTradeClassEndDate(tradeClassEndDate);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date companyStartDate = (Date)attributes.get("companyStartDate");

		if (companyStartDate != null) {
			setCompanyStartDate(companyStartDate);
		}

		Date tradeClassStartDate = (Date)attributes.get("tradeClassStartDate");

		if (tradeClassStartDate != null) {
			setTradeClassStartDate(tradeClassStartDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date companyEndDate = (Date)attributes.get("companyEndDate");

		if (companyEndDate != null) {
			setCompanyEndDate(companyEndDate);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		Date companyCfpAttachedDate = (Date)attributes.get(
				"companyCfpAttachedDate");

		if (companyCfpAttachedDate != null) {
			setCompanyCfpAttachedDate(companyCfpAttachedDate);
		}

		Integer cfpModelSid = (Integer)attributes.get("cfpModelSid");

		if (cfpModelSid != null) {
			setCfpModelSid(cfpModelSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer cfpDetailsSid = (Integer)attributes.get("cfpDetailsSid");

		if (cfpDetailsSid != null) {
			setCfpDetailsSid(cfpDetailsSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CfpDetailsWrapper((CfpDetails)_cfpDetails.clone());
	}

	@Override
	public int compareTo(CfpDetails cfpDetails) {
		return _cfpDetails.compareTo(cfpDetails);
	}

	/**
	* Returns the batch ID of this cfp details.
	*
	* @return the batch ID of this cfp details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _cfpDetails.getBatchId();
	}

	/**
	* Returns the cfp details sid of this cfp details.
	*
	* @return the cfp details sid of this cfp details
	*/
	@Override
	public int getCfpDetailsSid() {
		return _cfpDetails.getCfpDetailsSid();
	}

	/**
	* Returns the cfp model sid of this cfp details.
	*
	* @return the cfp model sid of this cfp details
	*/
	@Override
	public int getCfpModelSid() {
		return _cfpDetails.getCfpModelSid();
	}

	/**
	* Returns the company cfp attached date of this cfp details.
	*
	* @return the company cfp attached date of this cfp details
	*/
	@Override
	public Date getCompanyCfpAttachedDate() {
		return _cfpDetails.getCompanyCfpAttachedDate();
	}

	/**
	* Returns the company cfp attached status of this cfp details.
	*
	* @return the company cfp attached status of this cfp details
	*/
	@Override
	public int getCompanyCfpAttachedStatus() {
		return _cfpDetails.getCompanyCfpAttachedStatus();
	}

	/**
	* Returns the company end date of this cfp details.
	*
	* @return the company end date of this cfp details
	*/
	@Override
	public Date getCompanyEndDate() {
		return _cfpDetails.getCompanyEndDate();
	}

	/**
	* Returns the company master sid of this cfp details.
	*
	* @return the company master sid of this cfp details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _cfpDetails.getCompanyMasterSid();
	}

	/**
	* Returns the company start date of this cfp details.
	*
	* @return the company start date of this cfp details
	*/
	@Override
	public Date getCompanyStartDate() {
		return _cfpDetails.getCompanyStartDate();
	}

	/**
	* Returns the created by of this cfp details.
	*
	* @return the created by of this cfp details
	*/
	@Override
	public int getCreatedBy() {
		return _cfpDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this cfp details.
	*
	* @return the created date of this cfp details
	*/
	@Override
	public Date getCreatedDate() {
		return _cfpDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cfpDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this cfp details.
	*
	* @return the inbound status of this cfp details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _cfpDetails.getInboundStatus();
	}

	/**
	* Returns the modified by of this cfp details.
	*
	* @return the modified by of this cfp details
	*/
	@Override
	public int getModifiedBy() {
		return _cfpDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this cfp details.
	*
	* @return the modified date of this cfp details
	*/
	@Override
	public Date getModifiedDate() {
		return _cfpDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this cfp details.
	*
	* @return the primary key of this cfp details
	*/
	@Override
	public int getPrimaryKey() {
		return _cfpDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cfpDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this cfp details.
	*
	* @return the record lock status of this cfp details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _cfpDetails.getRecordLockStatus();
	}

	/**
	* Returns the source of this cfp details.
	*
	* @return the source of this cfp details
	*/
	@Override
	public java.lang.String getSource() {
		return _cfpDetails.getSource();
	}

	/**
	* Returns the trade class of this cfp details.
	*
	* @return the trade class of this cfp details
	*/
	@Override
	public int getTradeClass() {
		return _cfpDetails.getTradeClass();
	}

	/**
	* Returns the trade class end date of this cfp details.
	*
	* @return the trade class end date of this cfp details
	*/
	@Override
	public Date getTradeClassEndDate() {
		return _cfpDetails.getTradeClassEndDate();
	}

	/**
	* Returns the trade class start date of this cfp details.
	*
	* @return the trade class start date of this cfp details
	*/
	@Override
	public Date getTradeClassStartDate() {
		return _cfpDetails.getTradeClassStartDate();
	}

	@Override
	public int hashCode() {
		return _cfpDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cfpDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cfpDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cfpDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this cfp details is record lock status.
	*
	* @return <code>true</code> if this cfp details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _cfpDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_cfpDetails.persist();
	}

	/**
	* Sets the batch ID of this cfp details.
	*
	* @param batchId the batch ID of this cfp details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_cfpDetails.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cfpDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cfp details sid of this cfp details.
	*
	* @param cfpDetailsSid the cfp details sid of this cfp details
	*/
	@Override
	public void setCfpDetailsSid(int cfpDetailsSid) {
		_cfpDetails.setCfpDetailsSid(cfpDetailsSid);
	}

	/**
	* Sets the cfp model sid of this cfp details.
	*
	* @param cfpModelSid the cfp model sid of this cfp details
	*/
	@Override
	public void setCfpModelSid(int cfpModelSid) {
		_cfpDetails.setCfpModelSid(cfpModelSid);
	}

	/**
	* Sets the company cfp attached date of this cfp details.
	*
	* @param companyCfpAttachedDate the company cfp attached date of this cfp details
	*/
	@Override
	public void setCompanyCfpAttachedDate(Date companyCfpAttachedDate) {
		_cfpDetails.setCompanyCfpAttachedDate(companyCfpAttachedDate);
	}

	/**
	* Sets the company cfp attached status of this cfp details.
	*
	* @param companyCfpAttachedStatus the company cfp attached status of this cfp details
	*/
	@Override
	public void setCompanyCfpAttachedStatus(int companyCfpAttachedStatus) {
		_cfpDetails.setCompanyCfpAttachedStatus(companyCfpAttachedStatus);
	}

	/**
	* Sets the company end date of this cfp details.
	*
	* @param companyEndDate the company end date of this cfp details
	*/
	@Override
	public void setCompanyEndDate(Date companyEndDate) {
		_cfpDetails.setCompanyEndDate(companyEndDate);
	}

	/**
	* Sets the company master sid of this cfp details.
	*
	* @param companyMasterSid the company master sid of this cfp details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_cfpDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company start date of this cfp details.
	*
	* @param companyStartDate the company start date of this cfp details
	*/
	@Override
	public void setCompanyStartDate(Date companyStartDate) {
		_cfpDetails.setCompanyStartDate(companyStartDate);
	}

	/**
	* Sets the created by of this cfp details.
	*
	* @param createdBy the created by of this cfp details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_cfpDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cfp details.
	*
	* @param createdDate the created date of this cfp details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cfpDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cfpDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cfpDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cfpDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this cfp details.
	*
	* @param inboundStatus the inbound status of this cfp details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_cfpDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this cfp details.
	*
	* @param modifiedBy the modified by of this cfp details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_cfpDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this cfp details.
	*
	* @param modifiedDate the modified date of this cfp details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cfpDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cfpDetails.setNew(n);
	}

	/**
	* Sets the primary key of this cfp details.
	*
	* @param primaryKey the primary key of this cfp details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cfpDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cfpDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this cfp details is record lock status.
	*
	* @param recordLockStatus the record lock status of this cfp details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_cfpDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this cfp details.
	*
	* @param source the source of this cfp details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_cfpDetails.setSource(source);
	}

	/**
	* Sets the trade class of this cfp details.
	*
	* @param tradeClass the trade class of this cfp details
	*/
	@Override
	public void setTradeClass(int tradeClass) {
		_cfpDetails.setTradeClass(tradeClass);
	}

	/**
	* Sets the trade class end date of this cfp details.
	*
	* @param tradeClassEndDate the trade class end date of this cfp details
	*/
	@Override
	public void setTradeClassEndDate(Date tradeClassEndDate) {
		_cfpDetails.setTradeClassEndDate(tradeClassEndDate);
	}

	/**
	* Sets the trade class start date of this cfp details.
	*
	* @param tradeClassStartDate the trade class start date of this cfp details
	*/
	@Override
	public void setTradeClassStartDate(Date tradeClassStartDate) {
		_cfpDetails.setTradeClassStartDate(tradeClassStartDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CfpDetails> toCacheModel() {
		return _cfpDetails.toCacheModel();
	}

	@Override
	public CfpDetails toEscapedModel() {
		return new CfpDetailsWrapper(_cfpDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cfpDetails.toString();
	}

	@Override
	public CfpDetails toUnescapedModel() {
		return new CfpDetailsWrapper(_cfpDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cfpDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CfpDetailsWrapper)) {
			return false;
		}

		CfpDetailsWrapper cfpDetailsWrapper = (CfpDetailsWrapper)obj;

		if (Objects.equals(_cfpDetails, cfpDetailsWrapper._cfpDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CfpDetails getWrappedModel() {
		return _cfpDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cfpDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cfpDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cfpDetails.resetOriginalValues();
	}

	private final CfpDetails _cfpDetails;
}