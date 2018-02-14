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
 * This class is a wrapper for {@link IvldCompanyMaster}.
 * </p>
 *
 * @author
 * @see IvldCompanyMaster
 * @generated
 */
@ProviderType
public class IvldCompanyMasterWrapper implements IvldCompanyMaster,
	ModelWrapper<IvldCompanyMaster> {
	public IvldCompanyMasterWrapper(IvldCompanyMaster ivldCompanyMaster) {
		_ivldCompanyMaster = ivldCompanyMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldCompanyMaster.class;
	}

	@Override
	public String getModelClassName() {
		return IvldCompanyMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("state", getState());
		attributes.put("financialSystem", getFinancialSystem());
		attributes.put("companyName", getCompanyName());
		attributes.put("companyGroup", getCompanyGroup());
		attributes.put("companyCategory", getCompanyCategory());
		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("ivldCompanyMasterSid", getIvldCompanyMasterSid());
		attributes.put("lives", getLives());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("source", getSource());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("companyMasterIntfid", getCompanyMasterIntfid());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("udc6", getUdc6());
		attributes.put("udc5", getUdc5());
		attributes.put("udc4", getUdc4());
		attributes.put("udc1", getUdc1());
		attributes.put("udc2", getUdc2());
		attributes.put("zipCode", getZipCode());
		attributes.put("udc3", getUdc3());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("companyIdString", getCompanyIdString());
		attributes.put("address1", getAddress1());
		attributes.put("country", getCountry());
		attributes.put("address2", getAddress2());
		attributes.put("companyType", getCompanyType());
		attributes.put("companyStartDate", getCompanyStartDate());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("batchId", getBatchId());
		attributes.put("companyStatus", getCompanyStatus());
		attributes.put("companyEndDate", getCompanyEndDate());
		attributes.put("errorField", getErrorField());
		attributes.put("city", getCity());
		attributes.put("regionCode", getRegionCode());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String state = (String)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		String financialSystem = (String)attributes.get("financialSystem");

		if (financialSystem != null) {
			setFinancialSystem(financialSystem);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String companyGroup = (String)attributes.get("companyGroup");

		if (companyGroup != null) {
			setCompanyGroup(companyGroup);
		}

		String companyCategory = (String)attributes.get("companyCategory");

		if (companyCategory != null) {
			setCompanyCategory(companyCategory);
		}

		String lastUpdatedDate = (String)attributes.get("lastUpdatedDate");

		if (lastUpdatedDate != null) {
			setLastUpdatedDate(lastUpdatedDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer ivldCompanyMasterSid = (Integer)attributes.get(
				"ivldCompanyMasterSid");

		if (ivldCompanyMasterSid != null) {
			setIvldCompanyMasterSid(ivldCompanyMasterSid);
		}

		String lives = (String)attributes.get("lives");

		if (lives != null) {
			setLives(lives);
		}

		String organizationKey = (String)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
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

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
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

		String companyMasterIntfid = (String)attributes.get(
				"companyMasterIntfid");

		if (companyMasterIntfid != null) {
			setCompanyMasterIntfid(companyMasterIntfid);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String udc6 = (String)attributes.get("udc6");

		if (udc6 != null) {
			setUdc6(udc6);
		}

		String udc5 = (String)attributes.get("udc5");

		if (udc5 != null) {
			setUdc5(udc5);
		}

		String udc4 = (String)attributes.get("udc4");

		if (udc4 != null) {
			setUdc4(udc4);
		}

		String udc1 = (String)attributes.get("udc1");

		if (udc1 != null) {
			setUdc1(udc1);
		}

		String udc2 = (String)attributes.get("udc2");

		if (udc2 != null) {
			setUdc2(udc2);
		}

		String zipCode = (String)attributes.get("zipCode");

		if (zipCode != null) {
			setZipCode(zipCode);
		}

		String udc3 = (String)attributes.get("udc3");

		if (udc3 != null) {
			setUdc3(udc3);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String companyIdString = (String)attributes.get("companyIdString");

		if (companyIdString != null) {
			setCompanyIdString(companyIdString);
		}

		String address1 = (String)attributes.get("address1");

		if (address1 != null) {
			setAddress1(address1);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		String address2 = (String)attributes.get("address2");

		if (address2 != null) {
			setAddress2(address2);
		}

		String companyType = (String)attributes.get("companyType");

		if (companyType != null) {
			setCompanyType(companyType);
		}

		String companyStartDate = (String)attributes.get("companyStartDate");

		if (companyStartDate != null) {
			setCompanyStartDate(companyStartDate);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String companyStatus = (String)attributes.get("companyStatus");

		if (companyStatus != null) {
			setCompanyStatus(companyStatus);
		}

		String companyEndDate = (String)attributes.get("companyEndDate");

		if (companyEndDate != null) {
			setCompanyEndDate(companyEndDate);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		String regionCode = (String)attributes.get("regionCode");

		if (regionCode != null) {
			setRegionCode(regionCode);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldCompanyMasterWrapper((IvldCompanyMaster)_ivldCompanyMaster.clone());
	}

	@Override
	public int compareTo(IvldCompanyMaster ivldCompanyMaster) {
		return _ivldCompanyMaster.compareTo(ivldCompanyMaster);
	}

	/**
	* Returns the add chg del indicator of this ivld company master.
	*
	* @return the add chg del indicator of this ivld company master
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldCompanyMaster.getAddChgDelIndicator();
	}

	/**
	* Returns the address1 of this ivld company master.
	*
	* @return the address1 of this ivld company master
	*/
	@Override
	public java.lang.String getAddress1() {
		return _ivldCompanyMaster.getAddress1();
	}

	/**
	* Returns the address2 of this ivld company master.
	*
	* @return the address2 of this ivld company master
	*/
	@Override
	public java.lang.String getAddress2() {
		return _ivldCompanyMaster.getAddress2();
	}

	/**
	* Returns the batch ID of this ivld company master.
	*
	* @return the batch ID of this ivld company master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldCompanyMaster.getBatchId();
	}

	/**
	* Returns the check record of this ivld company master.
	*
	* @return the check record of this ivld company master
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldCompanyMaster.getCheckRecord();
	}

	/**
	* Returns the city of this ivld company master.
	*
	* @return the city of this ivld company master
	*/
	@Override
	public java.lang.String getCity() {
		return _ivldCompanyMaster.getCity();
	}

	/**
	* Returns the company category of this ivld company master.
	*
	* @return the company category of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyCategory() {
		return _ivldCompanyMaster.getCompanyCategory();
	}

	/**
	* Returns the company end date of this ivld company master.
	*
	* @return the company end date of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyEndDate() {
		return _ivldCompanyMaster.getCompanyEndDate();
	}

	/**
	* Returns the company group of this ivld company master.
	*
	* @return the company group of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyGroup() {
		return _ivldCompanyMaster.getCompanyGroup();
	}

	/**
	* Returns the company ID string of this ivld company master.
	*
	* @return the company ID string of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyIdString() {
		return _ivldCompanyMaster.getCompanyIdString();
	}

	/**
	* Returns the company master intfid of this ivld company master.
	*
	* @return the company master intfid of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyMasterIntfid() {
		return _ivldCompanyMaster.getCompanyMasterIntfid();
	}

	/**
	* Returns the company name of this ivld company master.
	*
	* @return the company name of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _ivldCompanyMaster.getCompanyName();
	}

	/**
	* Returns the company no of this ivld company master.
	*
	* @return the company no of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _ivldCompanyMaster.getCompanyNo();
	}

	/**
	* Returns the company start date of this ivld company master.
	*
	* @return the company start date of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyStartDate() {
		return _ivldCompanyMaster.getCompanyStartDate();
	}

	/**
	* Returns the company status of this ivld company master.
	*
	* @return the company status of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyStatus() {
		return _ivldCompanyMaster.getCompanyStatus();
	}

	/**
	* Returns the company type of this ivld company master.
	*
	* @return the company type of this ivld company master
	*/
	@Override
	public java.lang.String getCompanyType() {
		return _ivldCompanyMaster.getCompanyType();
	}

	/**
	* Returns the country of this ivld company master.
	*
	* @return the country of this ivld company master
	*/
	@Override
	public java.lang.String getCountry() {
		return _ivldCompanyMaster.getCountry();
	}

	/**
	* Returns the created by of this ivld company master.
	*
	* @return the created by of this ivld company master
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldCompanyMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld company master.
	*
	* @return the created date of this ivld company master
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldCompanyMaster.getCreatedDate();
	}

	/**
	* Returns the error code of this ivld company master.
	*
	* @return the error code of this ivld company master
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldCompanyMaster.getErrorCode();
	}

	/**
	* Returns the error field of this ivld company master.
	*
	* @return the error field of this ivld company master
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldCompanyMaster.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldCompanyMaster.getExpandoBridge();
	}

	/**
	* Returns the financial system of this ivld company master.
	*
	* @return the financial system of this ivld company master
	*/
	@Override
	public java.lang.String getFinancialSystem() {
		return _ivldCompanyMaster.getFinancialSystem();
	}

	/**
	* Returns the intf inserted date of this ivld company master.
	*
	* @return the intf inserted date of this ivld company master
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldCompanyMaster.getIntfInsertedDate();
	}

	/**
	* Returns the ivld company master sid of this ivld company master.
	*
	* @return the ivld company master sid of this ivld company master
	*/
	@Override
	public int getIvldCompanyMasterSid() {
		return _ivldCompanyMaster.getIvldCompanyMasterSid();
	}

	/**
	* Returns the last updated date of this ivld company master.
	*
	* @return the last updated date of this ivld company master
	*/
	@Override
	public java.lang.String getLastUpdatedDate() {
		return _ivldCompanyMaster.getLastUpdatedDate();
	}

	/**
	* Returns the lives of this ivld company master.
	*
	* @return the lives of this ivld company master
	*/
	@Override
	public java.lang.String getLives() {
		return _ivldCompanyMaster.getLives();
	}

	/**
	* Returns the modified by of this ivld company master.
	*
	* @return the modified by of this ivld company master
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldCompanyMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld company master.
	*
	* @return the modified date of this ivld company master
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldCompanyMaster.getModifiedDate();
	}

	/**
	* Returns the organization key of this ivld company master.
	*
	* @return the organization key of this ivld company master
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _ivldCompanyMaster.getOrganizationKey();
	}

	/**
	* Returns the primary key of this ivld company master.
	*
	* @return the primary key of this ivld company master
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldCompanyMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldCompanyMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld company master.
	*
	* @return the reason for failure of this ivld company master
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldCompanyMaster.getReasonForFailure();
	}

	/**
	* Returns the region code of this ivld company master.
	*
	* @return the region code of this ivld company master
	*/
	@Override
	public java.lang.String getRegionCode() {
		return _ivldCompanyMaster.getRegionCode();
	}

	/**
	* Returns the reprocessed flag of this ivld company master.
	*
	* @return the reprocessed flag of this ivld company master
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldCompanyMaster.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld company master.
	*
	* @return the source of this ivld company master
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldCompanyMaster.getSource();
	}

	/**
	* Returns the state of this ivld company master.
	*
	* @return the state of this ivld company master
	*/
	@Override
	public java.lang.String getState() {
		return _ivldCompanyMaster.getState();
	}

	/**
	* Returns the status of this ivld company master.
	*
	* @return the status of this ivld company master
	*/
	@Override
	public java.lang.String getStatus() {
		return _ivldCompanyMaster.getStatus();
	}

	/**
	* Returns the udc1 of this ivld company master.
	*
	* @return the udc1 of this ivld company master
	*/
	@Override
	public java.lang.String getUdc1() {
		return _ivldCompanyMaster.getUdc1();
	}

	/**
	* Returns the udc2 of this ivld company master.
	*
	* @return the udc2 of this ivld company master
	*/
	@Override
	public java.lang.String getUdc2() {
		return _ivldCompanyMaster.getUdc2();
	}

	/**
	* Returns the udc3 of this ivld company master.
	*
	* @return the udc3 of this ivld company master
	*/
	@Override
	public java.lang.String getUdc3() {
		return _ivldCompanyMaster.getUdc3();
	}

	/**
	* Returns the udc4 of this ivld company master.
	*
	* @return the udc4 of this ivld company master
	*/
	@Override
	public java.lang.String getUdc4() {
		return _ivldCompanyMaster.getUdc4();
	}

	/**
	* Returns the udc5 of this ivld company master.
	*
	* @return the udc5 of this ivld company master
	*/
	@Override
	public java.lang.String getUdc5() {
		return _ivldCompanyMaster.getUdc5();
	}

	/**
	* Returns the udc6 of this ivld company master.
	*
	* @return the udc6 of this ivld company master
	*/
	@Override
	public java.lang.String getUdc6() {
		return _ivldCompanyMaster.getUdc6();
	}

	/**
	* Returns the zip code of this ivld company master.
	*
	* @return the zip code of this ivld company master
	*/
	@Override
	public java.lang.String getZipCode() {
		return _ivldCompanyMaster.getZipCode();
	}

	@Override
	public int hashCode() {
		return _ivldCompanyMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldCompanyMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld company master is check record.
	*
	* @return <code>true</code> if this ivld company master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldCompanyMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldCompanyMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldCompanyMaster.isNew();
	}

	@Override
	public void persist() {
		_ivldCompanyMaster.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld company master.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld company master
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldCompanyMaster.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the address1 of this ivld company master.
	*
	* @param address1 the address1 of this ivld company master
	*/
	@Override
	public void setAddress1(java.lang.String address1) {
		_ivldCompanyMaster.setAddress1(address1);
	}

	/**
	* Sets the address2 of this ivld company master.
	*
	* @param address2 the address2 of this ivld company master
	*/
	@Override
	public void setAddress2(java.lang.String address2) {
		_ivldCompanyMaster.setAddress2(address2);
	}

	/**
	* Sets the batch ID of this ivld company master.
	*
	* @param batchId the batch ID of this ivld company master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldCompanyMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldCompanyMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld company master is check record.
	*
	* @param checkRecord the check record of this ivld company master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldCompanyMaster.setCheckRecord(checkRecord);
	}

	/**
	* Sets the city of this ivld company master.
	*
	* @param city the city of this ivld company master
	*/
	@Override
	public void setCity(java.lang.String city) {
		_ivldCompanyMaster.setCity(city);
	}

	/**
	* Sets the company category of this ivld company master.
	*
	* @param companyCategory the company category of this ivld company master
	*/
	@Override
	public void setCompanyCategory(java.lang.String companyCategory) {
		_ivldCompanyMaster.setCompanyCategory(companyCategory);
	}

	/**
	* Sets the company end date of this ivld company master.
	*
	* @param companyEndDate the company end date of this ivld company master
	*/
	@Override
	public void setCompanyEndDate(java.lang.String companyEndDate) {
		_ivldCompanyMaster.setCompanyEndDate(companyEndDate);
	}

	/**
	* Sets the company group of this ivld company master.
	*
	* @param companyGroup the company group of this ivld company master
	*/
	@Override
	public void setCompanyGroup(java.lang.String companyGroup) {
		_ivldCompanyMaster.setCompanyGroup(companyGroup);
	}

	/**
	* Sets the company ID string of this ivld company master.
	*
	* @param companyIdString the company ID string of this ivld company master
	*/
	@Override
	public void setCompanyIdString(java.lang.String companyIdString) {
		_ivldCompanyMaster.setCompanyIdString(companyIdString);
	}

	/**
	* Sets the company master intfid of this ivld company master.
	*
	* @param companyMasterIntfid the company master intfid of this ivld company master
	*/
	@Override
	public void setCompanyMasterIntfid(java.lang.String companyMasterIntfid) {
		_ivldCompanyMaster.setCompanyMasterIntfid(companyMasterIntfid);
	}

	/**
	* Sets the company name of this ivld company master.
	*
	* @param companyName the company name of this ivld company master
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_ivldCompanyMaster.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this ivld company master.
	*
	* @param companyNo the company no of this ivld company master
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_ivldCompanyMaster.setCompanyNo(companyNo);
	}

	/**
	* Sets the company start date of this ivld company master.
	*
	* @param companyStartDate the company start date of this ivld company master
	*/
	@Override
	public void setCompanyStartDate(java.lang.String companyStartDate) {
		_ivldCompanyMaster.setCompanyStartDate(companyStartDate);
	}

	/**
	* Sets the company status of this ivld company master.
	*
	* @param companyStatus the company status of this ivld company master
	*/
	@Override
	public void setCompanyStatus(java.lang.String companyStatus) {
		_ivldCompanyMaster.setCompanyStatus(companyStatus);
	}

	/**
	* Sets the company type of this ivld company master.
	*
	* @param companyType the company type of this ivld company master
	*/
	@Override
	public void setCompanyType(java.lang.String companyType) {
		_ivldCompanyMaster.setCompanyType(companyType);
	}

	/**
	* Sets the country of this ivld company master.
	*
	* @param country the country of this ivld company master
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_ivldCompanyMaster.setCountry(country);
	}

	/**
	* Sets the created by of this ivld company master.
	*
	* @param createdBy the created by of this ivld company master
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldCompanyMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld company master.
	*
	* @param createdDate the created date of this ivld company master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldCompanyMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the error code of this ivld company master.
	*
	* @param errorCode the error code of this ivld company master
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldCompanyMaster.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld company master.
	*
	* @param errorField the error field of this ivld company master
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldCompanyMaster.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldCompanyMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldCompanyMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldCompanyMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the financial system of this ivld company master.
	*
	* @param financialSystem the financial system of this ivld company master
	*/
	@Override
	public void setFinancialSystem(java.lang.String financialSystem) {
		_ivldCompanyMaster.setFinancialSystem(financialSystem);
	}

	/**
	* Sets the intf inserted date of this ivld company master.
	*
	* @param intfInsertedDate the intf inserted date of this ivld company master
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldCompanyMaster.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the ivld company master sid of this ivld company master.
	*
	* @param ivldCompanyMasterSid the ivld company master sid of this ivld company master
	*/
	@Override
	public void setIvldCompanyMasterSid(int ivldCompanyMasterSid) {
		_ivldCompanyMaster.setIvldCompanyMasterSid(ivldCompanyMasterSid);
	}

	/**
	* Sets the last updated date of this ivld company master.
	*
	* @param lastUpdatedDate the last updated date of this ivld company master
	*/
	@Override
	public void setLastUpdatedDate(java.lang.String lastUpdatedDate) {
		_ivldCompanyMaster.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the lives of this ivld company master.
	*
	* @param lives the lives of this ivld company master
	*/
	@Override
	public void setLives(java.lang.String lives) {
		_ivldCompanyMaster.setLives(lives);
	}

	/**
	* Sets the modified by of this ivld company master.
	*
	* @param modifiedBy the modified by of this ivld company master
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldCompanyMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld company master.
	*
	* @param modifiedDate the modified date of this ivld company master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldCompanyMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldCompanyMaster.setNew(n);
	}

	/**
	* Sets the organization key of this ivld company master.
	*
	* @param organizationKey the organization key of this ivld company master
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_ivldCompanyMaster.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this ivld company master.
	*
	* @param primaryKey the primary key of this ivld company master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldCompanyMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldCompanyMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld company master.
	*
	* @param reasonForFailure the reason for failure of this ivld company master
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldCompanyMaster.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the region code of this ivld company master.
	*
	* @param regionCode the region code of this ivld company master
	*/
	@Override
	public void setRegionCode(java.lang.String regionCode) {
		_ivldCompanyMaster.setRegionCode(regionCode);
	}

	/**
	* Sets the reprocessed flag of this ivld company master.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld company master
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldCompanyMaster.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld company master.
	*
	* @param source the source of this ivld company master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldCompanyMaster.setSource(source);
	}

	/**
	* Sets the state of this ivld company master.
	*
	* @param state the state of this ivld company master
	*/
	@Override
	public void setState(java.lang.String state) {
		_ivldCompanyMaster.setState(state);
	}

	/**
	* Sets the status of this ivld company master.
	*
	* @param status the status of this ivld company master
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_ivldCompanyMaster.setStatus(status);
	}

	/**
	* Sets the udc1 of this ivld company master.
	*
	* @param udc1 the udc1 of this ivld company master
	*/
	@Override
	public void setUdc1(java.lang.String udc1) {
		_ivldCompanyMaster.setUdc1(udc1);
	}

	/**
	* Sets the udc2 of this ivld company master.
	*
	* @param udc2 the udc2 of this ivld company master
	*/
	@Override
	public void setUdc2(java.lang.String udc2) {
		_ivldCompanyMaster.setUdc2(udc2);
	}

	/**
	* Sets the udc3 of this ivld company master.
	*
	* @param udc3 the udc3 of this ivld company master
	*/
	@Override
	public void setUdc3(java.lang.String udc3) {
		_ivldCompanyMaster.setUdc3(udc3);
	}

	/**
	* Sets the udc4 of this ivld company master.
	*
	* @param udc4 the udc4 of this ivld company master
	*/
	@Override
	public void setUdc4(java.lang.String udc4) {
		_ivldCompanyMaster.setUdc4(udc4);
	}

	/**
	* Sets the udc5 of this ivld company master.
	*
	* @param udc5 the udc5 of this ivld company master
	*/
	@Override
	public void setUdc5(java.lang.String udc5) {
		_ivldCompanyMaster.setUdc5(udc5);
	}

	/**
	* Sets the udc6 of this ivld company master.
	*
	* @param udc6 the udc6 of this ivld company master
	*/
	@Override
	public void setUdc6(java.lang.String udc6) {
		_ivldCompanyMaster.setUdc6(udc6);
	}

	/**
	* Sets the zip code of this ivld company master.
	*
	* @param zipCode the zip code of this ivld company master
	*/
	@Override
	public void setZipCode(java.lang.String zipCode) {
		_ivldCompanyMaster.setZipCode(zipCode);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldCompanyMaster> toCacheModel() {
		return _ivldCompanyMaster.toCacheModel();
	}

	@Override
	public IvldCompanyMaster toEscapedModel() {
		return new IvldCompanyMasterWrapper(_ivldCompanyMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldCompanyMaster.toString();
	}

	@Override
	public IvldCompanyMaster toUnescapedModel() {
		return new IvldCompanyMasterWrapper(_ivldCompanyMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldCompanyMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldCompanyMasterWrapper)) {
			return false;
		}

		IvldCompanyMasterWrapper ivldCompanyMasterWrapper = (IvldCompanyMasterWrapper)obj;

		if (Objects.equals(_ivldCompanyMaster,
					ivldCompanyMasterWrapper._ivldCompanyMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldCompanyMaster getWrappedModel() {
		return _ivldCompanyMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldCompanyMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldCompanyMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldCompanyMaster.resetOriginalValues();
	}

	private final IvldCompanyMaster _ivldCompanyMaster;
}