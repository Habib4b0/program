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
 * This class is a wrapper for {@link CfpContractDetails}.
 * </p>
 *
 * @author
 * @see CfpContractDetails
 * @generated
 */
@ProviderType
public class CfpContractDetailsWrapper implements CfpContractDetails,
	ModelWrapper<CfpContractDetails> {
	public CfpContractDetailsWrapper(CfpContractDetails cfpContractDetails) {
		_cfpContractDetails = cfpContractDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CfpContractDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CfpContractDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("tradeClass", getTradeClass());
		attributes.put("tradeClassEndDate", getTradeClassEndDate());
		attributes.put("cfpContractSid", getCfpContractSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("companyStartDate", getCompanyStartDate());
		attributes.put("tradeClassStartDate", getTradeClassStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("cfpContractAttachedDate", getCfpContractAttachedDate());
		attributes.put("companyEndDate", getCompanyEndDate());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("source", getSource());
		attributes.put("cfpContractDetailsSid", getCfpContractDetailsSid());
		attributes.put("cfpContractAttachedStatus",
			getCfpContractAttachedStatus());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer tradeClass = (Integer)attributes.get("tradeClass");

		if (tradeClass != null) {
			setTradeClass(tradeClass);
		}

		Date tradeClassEndDate = (Date)attributes.get("tradeClassEndDate");

		if (tradeClassEndDate != null) {
			setTradeClassEndDate(tradeClassEndDate);
		}

		Integer cfpContractSid = (Integer)attributes.get("cfpContractSid");

		if (cfpContractSid != null) {
			setCfpContractSid(cfpContractSid);
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

		Date cfpContractAttachedDate = (Date)attributes.get(
				"cfpContractAttachedDate");

		if (cfpContractAttachedDate != null) {
			setCfpContractAttachedDate(cfpContractAttachedDate);
		}

		Date companyEndDate = (Date)attributes.get("companyEndDate");

		if (companyEndDate != null) {
			setCompanyEndDate(companyEndDate);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
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

		Integer cfpContractDetailsSid = (Integer)attributes.get(
				"cfpContractDetailsSid");

		if (cfpContractDetailsSid != null) {
			setCfpContractDetailsSid(cfpContractDetailsSid);
		}

		Integer cfpContractAttachedStatus = (Integer)attributes.get(
				"cfpContractAttachedStatus");

		if (cfpContractAttachedStatus != null) {
			setCfpContractAttachedStatus(cfpContractAttachedStatus);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CfpContractDetailsWrapper((CfpContractDetails)_cfpContractDetails.clone());
	}

	@Override
	public int compareTo(CfpContractDetails cfpContractDetails) {
		return _cfpContractDetails.compareTo(cfpContractDetails);
	}

	/**
	* Returns the batch ID of this cfp contract details.
	*
	* @return the batch ID of this cfp contract details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _cfpContractDetails.getBatchId();
	}

	/**
	* Returns the cfp contract attached date of this cfp contract details.
	*
	* @return the cfp contract attached date of this cfp contract details
	*/
	@Override
	public Date getCfpContractAttachedDate() {
		return _cfpContractDetails.getCfpContractAttachedDate();
	}

	/**
	* Returns the cfp contract attached status of this cfp contract details.
	*
	* @return the cfp contract attached status of this cfp contract details
	*/
	@Override
	public int getCfpContractAttachedStatus() {
		return _cfpContractDetails.getCfpContractAttachedStatus();
	}

	/**
	* Returns the cfp contract details sid of this cfp contract details.
	*
	* @return the cfp contract details sid of this cfp contract details
	*/
	@Override
	public int getCfpContractDetailsSid() {
		return _cfpContractDetails.getCfpContractDetailsSid();
	}

	/**
	* Returns the cfp contract sid of this cfp contract details.
	*
	* @return the cfp contract sid of this cfp contract details
	*/
	@Override
	public int getCfpContractSid() {
		return _cfpContractDetails.getCfpContractSid();
	}

	/**
	* Returns the company end date of this cfp contract details.
	*
	* @return the company end date of this cfp contract details
	*/
	@Override
	public Date getCompanyEndDate() {
		return _cfpContractDetails.getCompanyEndDate();
	}

	/**
	* Returns the company master sid of this cfp contract details.
	*
	* @return the company master sid of this cfp contract details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _cfpContractDetails.getCompanyMasterSid();
	}

	/**
	* Returns the company start date of this cfp contract details.
	*
	* @return the company start date of this cfp contract details
	*/
	@Override
	public Date getCompanyStartDate() {
		return _cfpContractDetails.getCompanyStartDate();
	}

	/**
	* Returns the created by of this cfp contract details.
	*
	* @return the created by of this cfp contract details
	*/
	@Override
	public int getCreatedBy() {
		return _cfpContractDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this cfp contract details.
	*
	* @return the created date of this cfp contract details
	*/
	@Override
	public Date getCreatedDate() {
		return _cfpContractDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cfpContractDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this cfp contract details.
	*
	* @return the inbound status of this cfp contract details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _cfpContractDetails.getInboundStatus();
	}

	/**
	* Returns the modified by of this cfp contract details.
	*
	* @return the modified by of this cfp contract details
	*/
	@Override
	public int getModifiedBy() {
		return _cfpContractDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this cfp contract details.
	*
	* @return the modified date of this cfp contract details
	*/
	@Override
	public Date getModifiedDate() {
		return _cfpContractDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this cfp contract details.
	*
	* @return the primary key of this cfp contract details
	*/
	@Override
	public int getPrimaryKey() {
		return _cfpContractDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cfpContractDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this cfp contract details.
	*
	* @return the record lock status of this cfp contract details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _cfpContractDetails.getRecordLockStatus();
	}

	/**
	* Returns the source of this cfp contract details.
	*
	* @return the source of this cfp contract details
	*/
	@Override
	public java.lang.String getSource() {
		return _cfpContractDetails.getSource();
	}

	/**
	* Returns the trade class of this cfp contract details.
	*
	* @return the trade class of this cfp contract details
	*/
	@Override
	public int getTradeClass() {
		return _cfpContractDetails.getTradeClass();
	}

	/**
	* Returns the trade class end date of this cfp contract details.
	*
	* @return the trade class end date of this cfp contract details
	*/
	@Override
	public Date getTradeClassEndDate() {
		return _cfpContractDetails.getTradeClassEndDate();
	}

	/**
	* Returns the trade class start date of this cfp contract details.
	*
	* @return the trade class start date of this cfp contract details
	*/
	@Override
	public Date getTradeClassStartDate() {
		return _cfpContractDetails.getTradeClassStartDate();
	}

	@Override
	public int hashCode() {
		return _cfpContractDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cfpContractDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cfpContractDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cfpContractDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this cfp contract details is record lock status.
	*
	* @return <code>true</code> if this cfp contract details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _cfpContractDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_cfpContractDetails.persist();
	}

	/**
	* Sets the batch ID of this cfp contract details.
	*
	* @param batchId the batch ID of this cfp contract details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_cfpContractDetails.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cfpContractDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cfp contract attached date of this cfp contract details.
	*
	* @param cfpContractAttachedDate the cfp contract attached date of this cfp contract details
	*/
	@Override
	public void setCfpContractAttachedDate(Date cfpContractAttachedDate) {
		_cfpContractDetails.setCfpContractAttachedDate(cfpContractAttachedDate);
	}

	/**
	* Sets the cfp contract attached status of this cfp contract details.
	*
	* @param cfpContractAttachedStatus the cfp contract attached status of this cfp contract details
	*/
	@Override
	public void setCfpContractAttachedStatus(int cfpContractAttachedStatus) {
		_cfpContractDetails.setCfpContractAttachedStatus(cfpContractAttachedStatus);
	}

	/**
	* Sets the cfp contract details sid of this cfp contract details.
	*
	* @param cfpContractDetailsSid the cfp contract details sid of this cfp contract details
	*/
	@Override
	public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
		_cfpContractDetails.setCfpContractDetailsSid(cfpContractDetailsSid);
	}

	/**
	* Sets the cfp contract sid of this cfp contract details.
	*
	* @param cfpContractSid the cfp contract sid of this cfp contract details
	*/
	@Override
	public void setCfpContractSid(int cfpContractSid) {
		_cfpContractDetails.setCfpContractSid(cfpContractSid);
	}

	/**
	* Sets the company end date of this cfp contract details.
	*
	* @param companyEndDate the company end date of this cfp contract details
	*/
	@Override
	public void setCompanyEndDate(Date companyEndDate) {
		_cfpContractDetails.setCompanyEndDate(companyEndDate);
	}

	/**
	* Sets the company master sid of this cfp contract details.
	*
	* @param companyMasterSid the company master sid of this cfp contract details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_cfpContractDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company start date of this cfp contract details.
	*
	* @param companyStartDate the company start date of this cfp contract details
	*/
	@Override
	public void setCompanyStartDate(Date companyStartDate) {
		_cfpContractDetails.setCompanyStartDate(companyStartDate);
	}

	/**
	* Sets the created by of this cfp contract details.
	*
	* @param createdBy the created by of this cfp contract details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_cfpContractDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cfp contract details.
	*
	* @param createdDate the created date of this cfp contract details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cfpContractDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cfpContractDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cfpContractDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cfpContractDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this cfp contract details.
	*
	* @param inboundStatus the inbound status of this cfp contract details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_cfpContractDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this cfp contract details.
	*
	* @param modifiedBy the modified by of this cfp contract details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_cfpContractDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this cfp contract details.
	*
	* @param modifiedDate the modified date of this cfp contract details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cfpContractDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cfpContractDetails.setNew(n);
	}

	/**
	* Sets the primary key of this cfp contract details.
	*
	* @param primaryKey the primary key of this cfp contract details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cfpContractDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cfpContractDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this cfp contract details is record lock status.
	*
	* @param recordLockStatus the record lock status of this cfp contract details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_cfpContractDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this cfp contract details.
	*
	* @param source the source of this cfp contract details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_cfpContractDetails.setSource(source);
	}

	/**
	* Sets the trade class of this cfp contract details.
	*
	* @param tradeClass the trade class of this cfp contract details
	*/
	@Override
	public void setTradeClass(int tradeClass) {
		_cfpContractDetails.setTradeClass(tradeClass);
	}

	/**
	* Sets the trade class end date of this cfp contract details.
	*
	* @param tradeClassEndDate the trade class end date of this cfp contract details
	*/
	@Override
	public void setTradeClassEndDate(Date tradeClassEndDate) {
		_cfpContractDetails.setTradeClassEndDate(tradeClassEndDate);
	}

	/**
	* Sets the trade class start date of this cfp contract details.
	*
	* @param tradeClassStartDate the trade class start date of this cfp contract details
	*/
	@Override
	public void setTradeClassStartDate(Date tradeClassStartDate) {
		_cfpContractDetails.setTradeClassStartDate(tradeClassStartDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CfpContractDetails> toCacheModel() {
		return _cfpContractDetails.toCacheModel();
	}

	@Override
	public CfpContractDetails toEscapedModel() {
		return new CfpContractDetailsWrapper(_cfpContractDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cfpContractDetails.toString();
	}

	@Override
	public CfpContractDetails toUnescapedModel() {
		return new CfpContractDetailsWrapper(_cfpContractDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cfpContractDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CfpContractDetailsWrapper)) {
			return false;
		}

		CfpContractDetailsWrapper cfpContractDetailsWrapper = (CfpContractDetailsWrapper)obj;

		if (Objects.equals(_cfpContractDetails,
					cfpContractDetailsWrapper._cfpContractDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CfpContractDetails getWrappedModel() {
		return _cfpContractDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cfpContractDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cfpContractDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cfpContractDetails.resetOriginalValues();
	}

	private final CfpContractDetails _cfpContractDetails;
}