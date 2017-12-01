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
 * This class is a wrapper for {@link ImtdCfpDetails}.
 * </p>
 *
 * @author
 * @see ImtdCfpDetails
 * @generated
 */
@ProviderType
public class ImtdCfpDetailsWrapper implements ImtdCfpDetails,
	ModelWrapper<ImtdCfpDetails> {
	public ImtdCfpDetailsWrapper(ImtdCfpDetails imtdCfpDetails) {
		_imtdCfpDetails = imtdCfpDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return ImtdCfpDetails.class;
	}

	@Override
	public String getModelClassName() {
		return ImtdCfpDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("companyNo", getCompanyNo());
		attributes.put("imtdCfpDetailsSid", getImtdCfpDetailsSid());
		attributes.put("cfpDetailsStartDate", getCfpDetailsStartDate());
		attributes.put("companyType", getCompanyType());
		attributes.put("cfpDetailsTcStartDate", getCfpDetailsTcStartDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("cfpDetailsTcEndDate", getCfpDetailsTcEndDate());
		attributes.put("cfpDetailsCreatedDate", getCfpDetailsCreatedDate());
		attributes.put("imtdCreatedDate", getImtdCreatedDate());
		attributes.put("cfpDetailsModifiedDate", getCfpDetailsModifiedDate());
		attributes.put("cfpDetailsAttachedStatus", getCfpDetailsAttachedStatus());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("cfpDetailsAttachedDate", getCfpDetailsAttachedDate());
		attributes.put("cfpDetailsEndDate", getCfpDetailsEndDate());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("cfpDetailsTradeClass", getCfpDetailsTradeClass());
		attributes.put("tradingPartnerContractNo", getTradingPartnerContractNo());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("usersSid", getUsersSid());
		attributes.put("companyStartDate", getCompanyStartDate());
		attributes.put("cfpDetailsModifiedBy", getCfpDetailsModifiedBy());
		attributes.put("companyEndDate", getCompanyEndDate());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("cfpModelSid", getCfpModelSid());
		attributes.put("cfpDetailsSid", getCfpDetailsSid());
		attributes.put("companyStatus", getCompanyStatus());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("companyName", getCompanyName());
		attributes.put("operation", getOperation());
		attributes.put("cfpDetailsCreatedBy", getCfpDetailsCreatedBy());
		attributes.put("sessionId", getSessionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		Integer imtdCfpDetailsSid = (Integer)attributes.get("imtdCfpDetailsSid");

		if (imtdCfpDetailsSid != null) {
			setImtdCfpDetailsSid(imtdCfpDetailsSid);
		}

		Date cfpDetailsStartDate = (Date)attributes.get("cfpDetailsStartDate");

		if (cfpDetailsStartDate != null) {
			setCfpDetailsStartDate(cfpDetailsStartDate);
		}

		String companyType = (String)attributes.get("companyType");

		if (companyType != null) {
			setCompanyType(companyType);
		}

		Date cfpDetailsTcStartDate = (Date)attributes.get(
				"cfpDetailsTcStartDate");

		if (cfpDetailsTcStartDate != null) {
			setCfpDetailsTcStartDate(cfpDetailsTcStartDate);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date cfpDetailsTcEndDate = (Date)attributes.get("cfpDetailsTcEndDate");

		if (cfpDetailsTcEndDate != null) {
			setCfpDetailsTcEndDate(cfpDetailsTcEndDate);
		}

		Date cfpDetailsCreatedDate = (Date)attributes.get(
				"cfpDetailsCreatedDate");

		if (cfpDetailsCreatedDate != null) {
			setCfpDetailsCreatedDate(cfpDetailsCreatedDate);
		}

		Date imtdCreatedDate = (Date)attributes.get("imtdCreatedDate");

		if (imtdCreatedDate != null) {
			setImtdCreatedDate(imtdCreatedDate);
		}

		Date cfpDetailsModifiedDate = (Date)attributes.get(
				"cfpDetailsModifiedDate");

		if (cfpDetailsModifiedDate != null) {
			setCfpDetailsModifiedDate(cfpDetailsModifiedDate);
		}

		Integer cfpDetailsAttachedStatus = (Integer)attributes.get(
				"cfpDetailsAttachedStatus");

		if (cfpDetailsAttachedStatus != null) {
			setCfpDetailsAttachedStatus(cfpDetailsAttachedStatus);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		Date cfpDetailsAttachedDate = (Date)attributes.get(
				"cfpDetailsAttachedDate");

		if (cfpDetailsAttachedDate != null) {
			setCfpDetailsAttachedDate(cfpDetailsAttachedDate);
		}

		Date cfpDetailsEndDate = (Date)attributes.get("cfpDetailsEndDate");

		if (cfpDetailsEndDate != null) {
			setCfpDetailsEndDate(cfpDetailsEndDate);
		}

		String companyStringId = (String)attributes.get("companyStringId");

		if (companyStringId != null) {
			setCompanyStringId(companyStringId);
		}

		String cfpDetailsTradeClass = (String)attributes.get(
				"cfpDetailsTradeClass");

		if (cfpDetailsTradeClass != null) {
			setCfpDetailsTradeClass(cfpDetailsTradeClass);
		}

		String tradingPartnerContractNo = (String)attributes.get(
				"tradingPartnerContractNo");

		if (tradingPartnerContractNo != null) {
			setTradingPartnerContractNo(tradingPartnerContractNo);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String usersSid = (String)attributes.get("usersSid");

		if (usersSid != null) {
			setUsersSid(usersSid);
		}

		Date companyStartDate = (Date)attributes.get("companyStartDate");

		if (companyStartDate != null) {
			setCompanyStartDate(companyStartDate);
		}

		String cfpDetailsModifiedBy = (String)attributes.get(
				"cfpDetailsModifiedBy");

		if (cfpDetailsModifiedBy != null) {
			setCfpDetailsModifiedBy(cfpDetailsModifiedBy);
		}

		Date companyEndDate = (Date)attributes.get("companyEndDate");

		if (companyEndDate != null) {
			setCompanyEndDate(companyEndDate);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		Integer cfpModelSid = (Integer)attributes.get("cfpModelSid");

		if (cfpModelSid != null) {
			setCfpModelSid(cfpModelSid);
		}

		Integer cfpDetailsSid = (Integer)attributes.get("cfpDetailsSid");

		if (cfpDetailsSid != null) {
			setCfpDetailsSid(cfpDetailsSid);
		}

		Integer companyStatus = (Integer)attributes.get("companyStatus");

		if (companyStatus != null) {
			setCompanyStatus(companyStatus);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String operation = (String)attributes.get("operation");

		if (operation != null) {
			setOperation(operation);
		}

		String cfpDetailsCreatedBy = (String)attributes.get(
				"cfpDetailsCreatedBy");

		if (cfpDetailsCreatedBy != null) {
			setCfpDetailsCreatedBy(cfpDetailsCreatedBy);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ImtdCfpDetailsWrapper((ImtdCfpDetails)_imtdCfpDetails.clone());
	}

	@Override
	public int compareTo(ImtdCfpDetails imtdCfpDetails) {
		return _imtdCfpDetails.compareTo(imtdCfpDetails);
	}

	/**
	* Returns the cfp details attached date of this imtd cfp details.
	*
	* @return the cfp details attached date of this imtd cfp details
	*/
	@Override
	public Date getCfpDetailsAttachedDate() {
		return _imtdCfpDetails.getCfpDetailsAttachedDate();
	}

	/**
	* Returns the cfp details attached status of this imtd cfp details.
	*
	* @return the cfp details attached status of this imtd cfp details
	*/
	@Override
	public int getCfpDetailsAttachedStatus() {
		return _imtdCfpDetails.getCfpDetailsAttachedStatus();
	}

	/**
	* Returns the cfp details created by of this imtd cfp details.
	*
	* @return the cfp details created by of this imtd cfp details
	*/
	@Override
	public java.lang.String getCfpDetailsCreatedBy() {
		return _imtdCfpDetails.getCfpDetailsCreatedBy();
	}

	/**
	* Returns the cfp details created date of this imtd cfp details.
	*
	* @return the cfp details created date of this imtd cfp details
	*/
	@Override
	public Date getCfpDetailsCreatedDate() {
		return _imtdCfpDetails.getCfpDetailsCreatedDate();
	}

	/**
	* Returns the cfp details end date of this imtd cfp details.
	*
	* @return the cfp details end date of this imtd cfp details
	*/
	@Override
	public Date getCfpDetailsEndDate() {
		return _imtdCfpDetails.getCfpDetailsEndDate();
	}

	/**
	* Returns the cfp details modified by of this imtd cfp details.
	*
	* @return the cfp details modified by of this imtd cfp details
	*/
	@Override
	public java.lang.String getCfpDetailsModifiedBy() {
		return _imtdCfpDetails.getCfpDetailsModifiedBy();
	}

	/**
	* Returns the cfp details modified date of this imtd cfp details.
	*
	* @return the cfp details modified date of this imtd cfp details
	*/
	@Override
	public Date getCfpDetailsModifiedDate() {
		return _imtdCfpDetails.getCfpDetailsModifiedDate();
	}

	/**
	* Returns the cfp details sid of this imtd cfp details.
	*
	* @return the cfp details sid of this imtd cfp details
	*/
	@Override
	public int getCfpDetailsSid() {
		return _imtdCfpDetails.getCfpDetailsSid();
	}

	/**
	* Returns the cfp details start date of this imtd cfp details.
	*
	* @return the cfp details start date of this imtd cfp details
	*/
	@Override
	public Date getCfpDetailsStartDate() {
		return _imtdCfpDetails.getCfpDetailsStartDate();
	}

	/**
	* Returns the cfp details tc end date of this imtd cfp details.
	*
	* @return the cfp details tc end date of this imtd cfp details
	*/
	@Override
	public Date getCfpDetailsTcEndDate() {
		return _imtdCfpDetails.getCfpDetailsTcEndDate();
	}

	/**
	* Returns the cfp details tc start date of this imtd cfp details.
	*
	* @return the cfp details tc start date of this imtd cfp details
	*/
	@Override
	public Date getCfpDetailsTcStartDate() {
		return _imtdCfpDetails.getCfpDetailsTcStartDate();
	}

	/**
	* Returns the cfp details trade class of this imtd cfp details.
	*
	* @return the cfp details trade class of this imtd cfp details
	*/
	@Override
	public java.lang.String getCfpDetailsTradeClass() {
		return _imtdCfpDetails.getCfpDetailsTradeClass();
	}

	/**
	* Returns the cfp model sid of this imtd cfp details.
	*
	* @return the cfp model sid of this imtd cfp details
	*/
	@Override
	public int getCfpModelSid() {
		return _imtdCfpDetails.getCfpModelSid();
	}

	/**
	* Returns the check record of this imtd cfp details.
	*
	* @return the check record of this imtd cfp details
	*/
	@Override
	public boolean getCheckRecord() {
		return _imtdCfpDetails.getCheckRecord();
	}

	/**
	* Returns the company end date of this imtd cfp details.
	*
	* @return the company end date of this imtd cfp details
	*/
	@Override
	public Date getCompanyEndDate() {
		return _imtdCfpDetails.getCompanyEndDate();
	}

	/**
	* Returns the company master sid of this imtd cfp details.
	*
	* @return the company master sid of this imtd cfp details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _imtdCfpDetails.getCompanyMasterSid();
	}

	/**
	* Returns the company name of this imtd cfp details.
	*
	* @return the company name of this imtd cfp details
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _imtdCfpDetails.getCompanyName();
	}

	/**
	* Returns the company no of this imtd cfp details.
	*
	* @return the company no of this imtd cfp details
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _imtdCfpDetails.getCompanyNo();
	}

	/**
	* Returns the company start date of this imtd cfp details.
	*
	* @return the company start date of this imtd cfp details
	*/
	@Override
	public Date getCompanyStartDate() {
		return _imtdCfpDetails.getCompanyStartDate();
	}

	/**
	* Returns the company status of this imtd cfp details.
	*
	* @return the company status of this imtd cfp details
	*/
	@Override
	public int getCompanyStatus() {
		return _imtdCfpDetails.getCompanyStatus();
	}

	/**
	* Returns the company string ID of this imtd cfp details.
	*
	* @return the company string ID of this imtd cfp details
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _imtdCfpDetails.getCompanyStringId();
	}

	/**
	* Returns the company type of this imtd cfp details.
	*
	* @return the company type of this imtd cfp details
	*/
	@Override
	public java.lang.String getCompanyType() {
		return _imtdCfpDetails.getCompanyType();
	}

	/**
	* Returns the created by of this imtd cfp details.
	*
	* @return the created by of this imtd cfp details
	*/
	@Override
	public int getCreatedBy() {
		return _imtdCfpDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this imtd cfp details.
	*
	* @return the created date of this imtd cfp details
	*/
	@Override
	public Date getCreatedDate() {
		return _imtdCfpDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _imtdCfpDetails.getExpandoBridge();
	}

	/**
	* Returns the imtd cfp details sid of this imtd cfp details.
	*
	* @return the imtd cfp details sid of this imtd cfp details
	*/
	@Override
	public int getImtdCfpDetailsSid() {
		return _imtdCfpDetails.getImtdCfpDetailsSid();
	}

	/**
	* Returns the imtd created date of this imtd cfp details.
	*
	* @return the imtd created date of this imtd cfp details
	*/
	@Override
	public Date getImtdCreatedDate() {
		return _imtdCfpDetails.getImtdCreatedDate();
	}

	/**
	* Returns the modified by of this imtd cfp details.
	*
	* @return the modified by of this imtd cfp details
	*/
	@Override
	public int getModifiedBy() {
		return _imtdCfpDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this imtd cfp details.
	*
	* @return the modified date of this imtd cfp details
	*/
	@Override
	public Date getModifiedDate() {
		return _imtdCfpDetails.getModifiedDate();
	}

	/**
	* Returns the operation of this imtd cfp details.
	*
	* @return the operation of this imtd cfp details
	*/
	@Override
	public java.lang.String getOperation() {
		return _imtdCfpDetails.getOperation();
	}

	/**
	* Returns the primary key of this imtd cfp details.
	*
	* @return the primary key of this imtd cfp details
	*/
	@Override
	public int getPrimaryKey() {
		return _imtdCfpDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _imtdCfpDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the session ID of this imtd cfp details.
	*
	* @return the session ID of this imtd cfp details
	*/
	@Override
	public java.lang.String getSessionId() {
		return _imtdCfpDetails.getSessionId();
	}

	/**
	* Returns the trading partner contract no of this imtd cfp details.
	*
	* @return the trading partner contract no of this imtd cfp details
	*/
	@Override
	public java.lang.String getTradingPartnerContractNo() {
		return _imtdCfpDetails.getTradingPartnerContractNo();
	}

	/**
	* Returns the users sid of this imtd cfp details.
	*
	* @return the users sid of this imtd cfp details
	*/
	@Override
	public java.lang.String getUsersSid() {
		return _imtdCfpDetails.getUsersSid();
	}

	@Override
	public int hashCode() {
		return _imtdCfpDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _imtdCfpDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this imtd cfp details is check record.
	*
	* @return <code>true</code> if this imtd cfp details is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _imtdCfpDetails.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _imtdCfpDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _imtdCfpDetails.isNew();
	}

	@Override
	public void persist() {
		_imtdCfpDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_imtdCfpDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cfp details attached date of this imtd cfp details.
	*
	* @param cfpDetailsAttachedDate the cfp details attached date of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsAttachedDate(Date cfpDetailsAttachedDate) {
		_imtdCfpDetails.setCfpDetailsAttachedDate(cfpDetailsAttachedDate);
	}

	/**
	* Sets the cfp details attached status of this imtd cfp details.
	*
	* @param cfpDetailsAttachedStatus the cfp details attached status of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsAttachedStatus(int cfpDetailsAttachedStatus) {
		_imtdCfpDetails.setCfpDetailsAttachedStatus(cfpDetailsAttachedStatus);
	}

	/**
	* Sets the cfp details created by of this imtd cfp details.
	*
	* @param cfpDetailsCreatedBy the cfp details created by of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsCreatedBy(java.lang.String cfpDetailsCreatedBy) {
		_imtdCfpDetails.setCfpDetailsCreatedBy(cfpDetailsCreatedBy);
	}

	/**
	* Sets the cfp details created date of this imtd cfp details.
	*
	* @param cfpDetailsCreatedDate the cfp details created date of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsCreatedDate(Date cfpDetailsCreatedDate) {
		_imtdCfpDetails.setCfpDetailsCreatedDate(cfpDetailsCreatedDate);
	}

	/**
	* Sets the cfp details end date of this imtd cfp details.
	*
	* @param cfpDetailsEndDate the cfp details end date of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsEndDate(Date cfpDetailsEndDate) {
		_imtdCfpDetails.setCfpDetailsEndDate(cfpDetailsEndDate);
	}

	/**
	* Sets the cfp details modified by of this imtd cfp details.
	*
	* @param cfpDetailsModifiedBy the cfp details modified by of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsModifiedBy(java.lang.String cfpDetailsModifiedBy) {
		_imtdCfpDetails.setCfpDetailsModifiedBy(cfpDetailsModifiedBy);
	}

	/**
	* Sets the cfp details modified date of this imtd cfp details.
	*
	* @param cfpDetailsModifiedDate the cfp details modified date of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsModifiedDate(Date cfpDetailsModifiedDate) {
		_imtdCfpDetails.setCfpDetailsModifiedDate(cfpDetailsModifiedDate);
	}

	/**
	* Sets the cfp details sid of this imtd cfp details.
	*
	* @param cfpDetailsSid the cfp details sid of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsSid(int cfpDetailsSid) {
		_imtdCfpDetails.setCfpDetailsSid(cfpDetailsSid);
	}

	/**
	* Sets the cfp details start date of this imtd cfp details.
	*
	* @param cfpDetailsStartDate the cfp details start date of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsStartDate(Date cfpDetailsStartDate) {
		_imtdCfpDetails.setCfpDetailsStartDate(cfpDetailsStartDate);
	}

	/**
	* Sets the cfp details tc end date of this imtd cfp details.
	*
	* @param cfpDetailsTcEndDate the cfp details tc end date of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsTcEndDate(Date cfpDetailsTcEndDate) {
		_imtdCfpDetails.setCfpDetailsTcEndDate(cfpDetailsTcEndDate);
	}

	/**
	* Sets the cfp details tc start date of this imtd cfp details.
	*
	* @param cfpDetailsTcStartDate the cfp details tc start date of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsTcStartDate(Date cfpDetailsTcStartDate) {
		_imtdCfpDetails.setCfpDetailsTcStartDate(cfpDetailsTcStartDate);
	}

	/**
	* Sets the cfp details trade class of this imtd cfp details.
	*
	* @param cfpDetailsTradeClass the cfp details trade class of this imtd cfp details
	*/
	@Override
	public void setCfpDetailsTradeClass(java.lang.String cfpDetailsTradeClass) {
		_imtdCfpDetails.setCfpDetailsTradeClass(cfpDetailsTradeClass);
	}

	/**
	* Sets the cfp model sid of this imtd cfp details.
	*
	* @param cfpModelSid the cfp model sid of this imtd cfp details
	*/
	@Override
	public void setCfpModelSid(int cfpModelSid) {
		_imtdCfpDetails.setCfpModelSid(cfpModelSid);
	}

	/**
	* Sets whether this imtd cfp details is check record.
	*
	* @param checkRecord the check record of this imtd cfp details
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_imtdCfpDetails.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company end date of this imtd cfp details.
	*
	* @param companyEndDate the company end date of this imtd cfp details
	*/
	@Override
	public void setCompanyEndDate(Date companyEndDate) {
		_imtdCfpDetails.setCompanyEndDate(companyEndDate);
	}

	/**
	* Sets the company master sid of this imtd cfp details.
	*
	* @param companyMasterSid the company master sid of this imtd cfp details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_imtdCfpDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company name of this imtd cfp details.
	*
	* @param companyName the company name of this imtd cfp details
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_imtdCfpDetails.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this imtd cfp details.
	*
	* @param companyNo the company no of this imtd cfp details
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_imtdCfpDetails.setCompanyNo(companyNo);
	}

	/**
	* Sets the company start date of this imtd cfp details.
	*
	* @param companyStartDate the company start date of this imtd cfp details
	*/
	@Override
	public void setCompanyStartDate(Date companyStartDate) {
		_imtdCfpDetails.setCompanyStartDate(companyStartDate);
	}

	/**
	* Sets the company status of this imtd cfp details.
	*
	* @param companyStatus the company status of this imtd cfp details
	*/
	@Override
	public void setCompanyStatus(int companyStatus) {
		_imtdCfpDetails.setCompanyStatus(companyStatus);
	}

	/**
	* Sets the company string ID of this imtd cfp details.
	*
	* @param companyStringId the company string ID of this imtd cfp details
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_imtdCfpDetails.setCompanyStringId(companyStringId);
	}

	/**
	* Sets the company type of this imtd cfp details.
	*
	* @param companyType the company type of this imtd cfp details
	*/
	@Override
	public void setCompanyType(java.lang.String companyType) {
		_imtdCfpDetails.setCompanyType(companyType);
	}

	/**
	* Sets the created by of this imtd cfp details.
	*
	* @param createdBy the created by of this imtd cfp details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_imtdCfpDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this imtd cfp details.
	*
	* @param createdDate the created date of this imtd cfp details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_imtdCfpDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_imtdCfpDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_imtdCfpDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_imtdCfpDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the imtd cfp details sid of this imtd cfp details.
	*
	* @param imtdCfpDetailsSid the imtd cfp details sid of this imtd cfp details
	*/
	@Override
	public void setImtdCfpDetailsSid(int imtdCfpDetailsSid) {
		_imtdCfpDetails.setImtdCfpDetailsSid(imtdCfpDetailsSid);
	}

	/**
	* Sets the imtd created date of this imtd cfp details.
	*
	* @param imtdCreatedDate the imtd created date of this imtd cfp details
	*/
	@Override
	public void setImtdCreatedDate(Date imtdCreatedDate) {
		_imtdCfpDetails.setImtdCreatedDate(imtdCreatedDate);
	}

	/**
	* Sets the modified by of this imtd cfp details.
	*
	* @param modifiedBy the modified by of this imtd cfp details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_imtdCfpDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this imtd cfp details.
	*
	* @param modifiedDate the modified date of this imtd cfp details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_imtdCfpDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_imtdCfpDetails.setNew(n);
	}

	/**
	* Sets the operation of this imtd cfp details.
	*
	* @param operation the operation of this imtd cfp details
	*/
	@Override
	public void setOperation(java.lang.String operation) {
		_imtdCfpDetails.setOperation(operation);
	}

	/**
	* Sets the primary key of this imtd cfp details.
	*
	* @param primaryKey the primary key of this imtd cfp details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_imtdCfpDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_imtdCfpDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this imtd cfp details.
	*
	* @param sessionId the session ID of this imtd cfp details
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_imtdCfpDetails.setSessionId(sessionId);
	}

	/**
	* Sets the trading partner contract no of this imtd cfp details.
	*
	* @param tradingPartnerContractNo the trading partner contract no of this imtd cfp details
	*/
	@Override
	public void setTradingPartnerContractNo(
		java.lang.String tradingPartnerContractNo) {
		_imtdCfpDetails.setTradingPartnerContractNo(tradingPartnerContractNo);
	}

	/**
	* Sets the users sid of this imtd cfp details.
	*
	* @param usersSid the users sid of this imtd cfp details
	*/
	@Override
	public void setUsersSid(java.lang.String usersSid) {
		_imtdCfpDetails.setUsersSid(usersSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ImtdCfpDetails> toCacheModel() {
		return _imtdCfpDetails.toCacheModel();
	}

	@Override
	public ImtdCfpDetails toEscapedModel() {
		return new ImtdCfpDetailsWrapper(_imtdCfpDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _imtdCfpDetails.toString();
	}

	@Override
	public ImtdCfpDetails toUnescapedModel() {
		return new ImtdCfpDetailsWrapper(_imtdCfpDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _imtdCfpDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdCfpDetailsWrapper)) {
			return false;
		}

		ImtdCfpDetailsWrapper imtdCfpDetailsWrapper = (ImtdCfpDetailsWrapper)obj;

		if (Objects.equals(_imtdCfpDetails,
					imtdCfpDetailsWrapper._imtdCfpDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public ImtdCfpDetails getWrappedModel() {
		return _imtdCfpDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _imtdCfpDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _imtdCfpDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_imtdCfpDetails.resetOriginalValues();
	}

	private final ImtdCfpDetails _imtdCfpDetails;
}