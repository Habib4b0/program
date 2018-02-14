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
 * This class is a wrapper for {@link CompanyMaster}.
 * </p>
 *
 * @author
 * @see CompanyMaster
 * @generated
 */
@ProviderType
public class CompanyMasterWrapper implements CompanyMaster,
	ModelWrapper<CompanyMaster> {
	public CompanyMasterWrapper(CompanyMaster companyMaster) {
		_companyMaster = companyMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return CompanyMaster.class;
	}

	@Override
	public String getModelClassName() {
		return CompanyMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("state", getState());
		attributes.put("financialSystem", getFinancialSystem());
		attributes.put("companyGroup", getCompanyGroup());
		attributes.put("companyName", getCompanyName());
		attributes.put("companyCategory", getCompanyCategory());
		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lives", getLives());
		attributes.put("address2", getAddress2());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("address1", getAddress1());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("zipCode", getZipCode());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("country", getCountry());
		attributes.put("internalNotes", getInternalNotes());
		attributes.put("orgKey", getOrgKey());
		attributes.put("companyType", getCompanyType());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("companyStartDate", getCompanyStartDate());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("batchId", getBatchId());
		attributes.put("companyStatus", getCompanyStatus());
		attributes.put("companyEndDate", getCompanyEndDate());
		attributes.put("city", getCity());
		attributes.put("regionCode", getRegionCode());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer state = (Integer)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		String financialSystem = (String)attributes.get("financialSystem");

		if (financialSystem != null) {
			setFinancialSystem(financialSystem);
		}

		String companyGroup = (String)attributes.get("companyGroup");

		if (companyGroup != null) {
			setCompanyGroup(companyGroup);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		Integer companyCategory = (Integer)attributes.get("companyCategory");

		if (companyCategory != null) {
			setCompanyCategory(companyCategory);
		}

		Date lastUpdatedDate = (Date)attributes.get("lastUpdatedDate");

		if (lastUpdatedDate != null) {
			setLastUpdatedDate(lastUpdatedDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer lives = (Integer)attributes.get("lives");

		if (lives != null) {
			setLives(lives);
		}

		String address2 = (String)attributes.get("address2");

		if (address2 != null) {
			setAddress2(address2);
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

		String address1 = (String)attributes.get("address1");

		if (address1 != null) {
			setAddress1(address1);
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

		String zipCode = (String)attributes.get("zipCode");

		if (zipCode != null) {
			setZipCode(zipCode);
		}

		String companyStringId = (String)attributes.get("companyStringId");

		if (companyStringId != null) {
			setCompanyStringId(companyStringId);
		}

		Integer country = (Integer)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		String internalNotes = (String)attributes.get("internalNotes");

		if (internalNotes != null) {
			setInternalNotes(internalNotes);
		}

		String orgKey = (String)attributes.get("orgKey");

		if (orgKey != null) {
			setOrgKey(orgKey);
		}

		Integer companyType = (Integer)attributes.get("companyType");

		if (companyType != null) {
			setCompanyType(companyType);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date companyStartDate = (Date)attributes.get("companyStartDate");

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

		Integer companyStatus = (Integer)attributes.get("companyStatus");

		if (companyStatus != null) {
			setCompanyStatus(companyStatus);
		}

		Date companyEndDate = (Date)attributes.get("companyEndDate");

		if (companyEndDate != null) {
			setCompanyEndDate(companyEndDate);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		String regionCode = (String)attributes.get("regionCode");

		if (regionCode != null) {
			setRegionCode(regionCode);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CompanyMasterWrapper((CompanyMaster)_companyMaster.clone());
	}

	@Override
	public int compareTo(CompanyMaster companyMaster) {
		return _companyMaster.compareTo(companyMaster);
	}

	/**
	* Returns the address1 of this company master.
	*
	* @return the address1 of this company master
	*/
	@Override
	public java.lang.String getAddress1() {
		return _companyMaster.getAddress1();
	}

	/**
	* Returns the address2 of this company master.
	*
	* @return the address2 of this company master
	*/
	@Override
	public java.lang.String getAddress2() {
		return _companyMaster.getAddress2();
	}

	/**
	* Returns the batch ID of this company master.
	*
	* @return the batch ID of this company master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _companyMaster.getBatchId();
	}

	/**
	* Returns the city of this company master.
	*
	* @return the city of this company master
	*/
	@Override
	public java.lang.String getCity() {
		return _companyMaster.getCity();
	}

	/**
	* Returns the company category of this company master.
	*
	* @return the company category of this company master
	*/
	@Override
	public int getCompanyCategory() {
		return _companyMaster.getCompanyCategory();
	}

	/**
	* Returns the company end date of this company master.
	*
	* @return the company end date of this company master
	*/
	@Override
	public Date getCompanyEndDate() {
		return _companyMaster.getCompanyEndDate();
	}

	/**
	* Returns the company group of this company master.
	*
	* @return the company group of this company master
	*/
	@Override
	public java.lang.String getCompanyGroup() {
		return _companyMaster.getCompanyGroup();
	}

	/**
	* Returns the company master sid of this company master.
	*
	* @return the company master sid of this company master
	*/
	@Override
	public int getCompanyMasterSid() {
		return _companyMaster.getCompanyMasterSid();
	}

	/**
	* Returns the company name of this company master.
	*
	* @return the company name of this company master
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _companyMaster.getCompanyName();
	}

	/**
	* Returns the company no of this company master.
	*
	* @return the company no of this company master
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _companyMaster.getCompanyNo();
	}

	/**
	* Returns the company start date of this company master.
	*
	* @return the company start date of this company master
	*/
	@Override
	public Date getCompanyStartDate() {
		return _companyMaster.getCompanyStartDate();
	}

	/**
	* Returns the company status of this company master.
	*
	* @return the company status of this company master
	*/
	@Override
	public int getCompanyStatus() {
		return _companyMaster.getCompanyStatus();
	}

	/**
	* Returns the company string ID of this company master.
	*
	* @return the company string ID of this company master
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _companyMaster.getCompanyStringId();
	}

	/**
	* Returns the company type of this company master.
	*
	* @return the company type of this company master
	*/
	@Override
	public int getCompanyType() {
		return _companyMaster.getCompanyType();
	}

	/**
	* Returns the country of this company master.
	*
	* @return the country of this company master
	*/
	@Override
	public int getCountry() {
		return _companyMaster.getCountry();
	}

	/**
	* Returns the created by of this company master.
	*
	* @return the created by of this company master
	*/
	@Override
	public int getCreatedBy() {
		return _companyMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this company master.
	*
	* @return the created date of this company master
	*/
	@Override
	public Date getCreatedDate() {
		return _companyMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _companyMaster.getExpandoBridge();
	}

	/**
	* Returns the financial system of this company master.
	*
	* @return the financial system of this company master
	*/
	@Override
	public java.lang.String getFinancialSystem() {
		return _companyMaster.getFinancialSystem();
	}

	/**
	* Returns the inbound status of this company master.
	*
	* @return the inbound status of this company master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _companyMaster.getInboundStatus();
	}

	/**
	* Returns the internal notes of this company master.
	*
	* @return the internal notes of this company master
	*/
	@Override
	public java.lang.String getInternalNotes() {
		return _companyMaster.getInternalNotes();
	}

	/**
	* Returns the last updated date of this company master.
	*
	* @return the last updated date of this company master
	*/
	@Override
	public Date getLastUpdatedDate() {
		return _companyMaster.getLastUpdatedDate();
	}

	/**
	* Returns the lives of this company master.
	*
	* @return the lives of this company master
	*/
	@Override
	public int getLives() {
		return _companyMaster.getLives();
	}

	/**
	* Returns the modified by of this company master.
	*
	* @return the modified by of this company master
	*/
	@Override
	public int getModifiedBy() {
		return _companyMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this company master.
	*
	* @return the modified date of this company master
	*/
	@Override
	public Date getModifiedDate() {
		return _companyMaster.getModifiedDate();
	}

	/**
	* Returns the org key of this company master.
	*
	* @return the org key of this company master
	*/
	@Override
	public java.lang.String getOrgKey() {
		return _companyMaster.getOrgKey();
	}

	/**
	* Returns the primary key of this company master.
	*
	* @return the primary key of this company master
	*/
	@Override
	public int getPrimaryKey() {
		return _companyMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _companyMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this company master.
	*
	* @return the record lock status of this company master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _companyMaster.getRecordLockStatus();
	}

	/**
	* Returns the region code of this company master.
	*
	* @return the region code of this company master
	*/
	@Override
	public java.lang.String getRegionCode() {
		return _companyMaster.getRegionCode();
	}

	/**
	* Returns the source of this company master.
	*
	* @return the source of this company master
	*/
	@Override
	public java.lang.String getSource() {
		return _companyMaster.getSource();
	}

	/**
	* Returns the state of this company master.
	*
	* @return the state of this company master
	*/
	@Override
	public int getState() {
		return _companyMaster.getState();
	}

	/**
	* Returns the zip code of this company master.
	*
	* @return the zip code of this company master
	*/
	@Override
	public java.lang.String getZipCode() {
		return _companyMaster.getZipCode();
	}

	@Override
	public int hashCode() {
		return _companyMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _companyMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _companyMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _companyMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this company master is record lock status.
	*
	* @return <code>true</code> if this company master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _companyMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_companyMaster.persist();
	}

	/**
	* Sets the address1 of this company master.
	*
	* @param address1 the address1 of this company master
	*/
	@Override
	public void setAddress1(java.lang.String address1) {
		_companyMaster.setAddress1(address1);
	}

	/**
	* Sets the address2 of this company master.
	*
	* @param address2 the address2 of this company master
	*/
	@Override
	public void setAddress2(java.lang.String address2) {
		_companyMaster.setAddress2(address2);
	}

	/**
	* Sets the batch ID of this company master.
	*
	* @param batchId the batch ID of this company master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_companyMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_companyMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the city of this company master.
	*
	* @param city the city of this company master
	*/
	@Override
	public void setCity(java.lang.String city) {
		_companyMaster.setCity(city);
	}

	/**
	* Sets the company category of this company master.
	*
	* @param companyCategory the company category of this company master
	*/
	@Override
	public void setCompanyCategory(int companyCategory) {
		_companyMaster.setCompanyCategory(companyCategory);
	}

	/**
	* Sets the company end date of this company master.
	*
	* @param companyEndDate the company end date of this company master
	*/
	@Override
	public void setCompanyEndDate(Date companyEndDate) {
		_companyMaster.setCompanyEndDate(companyEndDate);
	}

	/**
	* Sets the company group of this company master.
	*
	* @param companyGroup the company group of this company master
	*/
	@Override
	public void setCompanyGroup(java.lang.String companyGroup) {
		_companyMaster.setCompanyGroup(companyGroup);
	}

	/**
	* Sets the company master sid of this company master.
	*
	* @param companyMasterSid the company master sid of this company master
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMaster.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company name of this company master.
	*
	* @param companyName the company name of this company master
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_companyMaster.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this company master.
	*
	* @param companyNo the company no of this company master
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_companyMaster.setCompanyNo(companyNo);
	}

	/**
	* Sets the company start date of this company master.
	*
	* @param companyStartDate the company start date of this company master
	*/
	@Override
	public void setCompanyStartDate(Date companyStartDate) {
		_companyMaster.setCompanyStartDate(companyStartDate);
	}

	/**
	* Sets the company status of this company master.
	*
	* @param companyStatus the company status of this company master
	*/
	@Override
	public void setCompanyStatus(int companyStatus) {
		_companyMaster.setCompanyStatus(companyStatus);
	}

	/**
	* Sets the company string ID of this company master.
	*
	* @param companyStringId the company string ID of this company master
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_companyMaster.setCompanyStringId(companyStringId);
	}

	/**
	* Sets the company type of this company master.
	*
	* @param companyType the company type of this company master
	*/
	@Override
	public void setCompanyType(int companyType) {
		_companyMaster.setCompanyType(companyType);
	}

	/**
	* Sets the country of this company master.
	*
	* @param country the country of this company master
	*/
	@Override
	public void setCountry(int country) {
		_companyMaster.setCountry(country);
	}

	/**
	* Sets the created by of this company master.
	*
	* @param createdBy the created by of this company master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_companyMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this company master.
	*
	* @param createdDate the created date of this company master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_companyMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_companyMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_companyMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_companyMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the financial system of this company master.
	*
	* @param financialSystem the financial system of this company master
	*/
	@Override
	public void setFinancialSystem(java.lang.String financialSystem) {
		_companyMaster.setFinancialSystem(financialSystem);
	}

	/**
	* Sets the inbound status of this company master.
	*
	* @param inboundStatus the inbound status of this company master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_companyMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the internal notes of this company master.
	*
	* @param internalNotes the internal notes of this company master
	*/
	@Override
	public void setInternalNotes(java.lang.String internalNotes) {
		_companyMaster.setInternalNotes(internalNotes);
	}

	/**
	* Sets the last updated date of this company master.
	*
	* @param lastUpdatedDate the last updated date of this company master
	*/
	@Override
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_companyMaster.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the lives of this company master.
	*
	* @param lives the lives of this company master
	*/
	@Override
	public void setLives(int lives) {
		_companyMaster.setLives(lives);
	}

	/**
	* Sets the modified by of this company master.
	*
	* @param modifiedBy the modified by of this company master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_companyMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this company master.
	*
	* @param modifiedDate the modified date of this company master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_companyMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_companyMaster.setNew(n);
	}

	/**
	* Sets the org key of this company master.
	*
	* @param orgKey the org key of this company master
	*/
	@Override
	public void setOrgKey(java.lang.String orgKey) {
		_companyMaster.setOrgKey(orgKey);
	}

	/**
	* Sets the primary key of this company master.
	*
	* @param primaryKey the primary key of this company master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_companyMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_companyMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this company master is record lock status.
	*
	* @param recordLockStatus the record lock status of this company master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_companyMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the region code of this company master.
	*
	* @param regionCode the region code of this company master
	*/
	@Override
	public void setRegionCode(java.lang.String regionCode) {
		_companyMaster.setRegionCode(regionCode);
	}

	/**
	* Sets the source of this company master.
	*
	* @param source the source of this company master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_companyMaster.setSource(source);
	}

	/**
	* Sets the state of this company master.
	*
	* @param state the state of this company master
	*/
	@Override
	public void setState(int state) {
		_companyMaster.setState(state);
	}

	/**
	* Sets the zip code of this company master.
	*
	* @param zipCode the zip code of this company master
	*/
	@Override
	public void setZipCode(java.lang.String zipCode) {
		_companyMaster.setZipCode(zipCode);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CompanyMaster> toCacheModel() {
		return _companyMaster.toCacheModel();
	}

	@Override
	public CompanyMaster toEscapedModel() {
		return new CompanyMasterWrapper(_companyMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _companyMaster.toString();
	}

	@Override
	public CompanyMaster toUnescapedModel() {
		return new CompanyMasterWrapper(_companyMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _companyMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyMasterWrapper)) {
			return false;
		}

		CompanyMasterWrapper companyMasterWrapper = (CompanyMasterWrapper)obj;

		if (Objects.equals(_companyMaster, companyMasterWrapper._companyMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public CompanyMaster getWrappedModel() {
		return _companyMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _companyMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _companyMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_companyMaster.resetOriginalValues();
	}

	private final CompanyMaster _companyMaster;
}