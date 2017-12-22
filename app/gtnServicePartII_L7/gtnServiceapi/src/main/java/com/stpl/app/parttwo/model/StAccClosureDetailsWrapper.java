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
 * This class is a wrapper for {@link StAccClosureDetails}.
 * </p>
 *
 * @author
 * @see StAccClosureDetails
 * @generated
 */
@ProviderType
public class StAccClosureDetailsWrapper implements StAccClosureDetails,
	ModelWrapper<StAccClosureDetails> {
	public StAccClosureDetailsWrapper(StAccClosureDetails stAccClosureDetails) {
		_stAccClosureDetails = stAccClosureDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return StAccClosureDetails.class;
	}

	@Override
	public String getModelClassName() {
		return StAccClosureDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("contractName", getContractName());
		attributes.put("userId", getUserId());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("moduleName", getModuleName());
		attributes.put("companyName", getCompanyName());
		attributes.put("brandName", getBrandName());
		attributes.put("companyCostCenter", getCompanyCostCenter());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("sessionId", getSessionId());
		attributes.put("ccpDetailsSid", getCcpDetailsSid());
		attributes.put("itemName", getItemName());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("contractNo", getContractNo());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("ndc8", getNdc8());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String contractName = (String)attributes.get("contractName");

		if (contractName != null) {
			setContractName(contractName);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String moduleName = (String)attributes.get("moduleName");

		if (moduleName != null) {
			setModuleName(moduleName);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String brandName = (String)attributes.get("brandName");

		if (brandName != null) {
			setBrandName(brandName);
		}

		String companyCostCenter = (String)attributes.get("companyCostCenter");

		if (companyCostCenter != null) {
			setCompanyCostCenter(companyCostCenter);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer ccpDetailsSid = (Integer)attributes.get("ccpDetailsSid");

		if (ccpDetailsSid != null) {
			setCcpDetailsSid(ccpDetailsSid);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		String ndc8 = (String)attributes.get("ndc8");

		if (ndc8 != null) {
			setNdc8(ndc8);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StAccClosureDetailsWrapper((StAccClosureDetails)_stAccClosureDetails.clone());
	}

	@Override
	public int compareTo(StAccClosureDetails stAccClosureDetails) {
		return _stAccClosureDetails.compareTo(stAccClosureDetails);
	}

	/**
	* Returns the acc closure master sid of this st acc closure details.
	*
	* @return the acc closure master sid of this st acc closure details
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _stAccClosureDetails.getAccClosureMasterSid();
	}

	/**
	* Returns the brand name of this st acc closure details.
	*
	* @return the brand name of this st acc closure details
	*/
	@Override
	public java.lang.String getBrandName() {
		return _stAccClosureDetails.getBrandName();
	}

	/**
	* Returns the ccp details sid of this st acc closure details.
	*
	* @return the ccp details sid of this st acc closure details
	*/
	@Override
	public int getCcpDetailsSid() {
		return _stAccClosureDetails.getCcpDetailsSid();
	}

	/**
	* Returns the check record of this st acc closure details.
	*
	* @return the check record of this st acc closure details
	*/
	@Override
	public boolean getCheckRecord() {
		return _stAccClosureDetails.getCheckRecord();
	}

	/**
	* Returns the company cost center of this st acc closure details.
	*
	* @return the company cost center of this st acc closure details
	*/
	@Override
	public java.lang.String getCompanyCostCenter() {
		return _stAccClosureDetails.getCompanyCostCenter();
	}

	/**
	* Returns the company master sid of this st acc closure details.
	*
	* @return the company master sid of this st acc closure details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _stAccClosureDetails.getCompanyMasterSid();
	}

	/**
	* Returns the company name of this st acc closure details.
	*
	* @return the company name of this st acc closure details
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _stAccClosureDetails.getCompanyName();
	}

	/**
	* Returns the company no of this st acc closure details.
	*
	* @return the company no of this st acc closure details
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _stAccClosureDetails.getCompanyNo();
	}

	/**
	* Returns the contract master sid of this st acc closure details.
	*
	* @return the contract master sid of this st acc closure details
	*/
	@Override
	public int getContractMasterSid() {
		return _stAccClosureDetails.getContractMasterSid();
	}

	/**
	* Returns the contract name of this st acc closure details.
	*
	* @return the contract name of this st acc closure details
	*/
	@Override
	public java.lang.String getContractName() {
		return _stAccClosureDetails.getContractName();
	}

	/**
	* Returns the contract no of this st acc closure details.
	*
	* @return the contract no of this st acc closure details
	*/
	@Override
	public java.lang.String getContractNo() {
		return _stAccClosureDetails.getContractNo();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stAccClosureDetails.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this st acc closure details.
	*
	* @return the item master sid of this st acc closure details
	*/
	@Override
	public int getItemMasterSid() {
		return _stAccClosureDetails.getItemMasterSid();
	}

	/**
	* Returns the item name of this st acc closure details.
	*
	* @return the item name of this st acc closure details
	*/
	@Override
	public java.lang.String getItemName() {
		return _stAccClosureDetails.getItemName();
	}

	/**
	* Returns the last modified date of this st acc closure details.
	*
	* @return the last modified date of this st acc closure details
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stAccClosureDetails.getLastModifiedDate();
	}

	/**
	* Returns the module name of this st acc closure details.
	*
	* @return the module name of this st acc closure details
	*/
	@Override
	public java.lang.String getModuleName() {
		return _stAccClosureDetails.getModuleName();
	}

	/**
	* Returns the ndc8 of this st acc closure details.
	*
	* @return the ndc8 of this st acc closure details
	*/
	@Override
	public java.lang.String getNdc8() {
		return _stAccClosureDetails.getNdc8();
	}

	/**
	* Returns the primary key of this st acc closure details.
	*
	* @return the primary key of this st acc closure details
	*/
	@Override
	public int getPrimaryKey() {
		return _stAccClosureDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stAccClosureDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the rs model sid of this st acc closure details.
	*
	* @return the rs model sid of this st acc closure details
	*/
	@Override
	public int getRsModelSid() {
		return _stAccClosureDetails.getRsModelSid();
	}

	/**
	* Returns the session ID of this st acc closure details.
	*
	* @return the session ID of this st acc closure details
	*/
	@Override
	public int getSessionId() {
		return _stAccClosureDetails.getSessionId();
	}

	/**
	* Returns the user ID of this st acc closure details.
	*
	* @return the user ID of this st acc closure details
	*/
	@Override
	public int getUserId() {
		return _stAccClosureDetails.getUserId();
	}

	@Override
	public int hashCode() {
		return _stAccClosureDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stAccClosureDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this st acc closure details is check record.
	*
	* @return <code>true</code> if this st acc closure details is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _stAccClosureDetails.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _stAccClosureDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stAccClosureDetails.isNew();
	}

	@Override
	public void persist() {
		_stAccClosureDetails.persist();
	}

	/**
	* Sets the acc closure master sid of this st acc closure details.
	*
	* @param accClosureMasterSid the acc closure master sid of this st acc closure details
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_stAccClosureDetails.setAccClosureMasterSid(accClosureMasterSid);
	}

	/**
	* Sets the brand name of this st acc closure details.
	*
	* @param brandName the brand name of this st acc closure details
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_stAccClosureDetails.setBrandName(brandName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stAccClosureDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the ccp details sid of this st acc closure details.
	*
	* @param ccpDetailsSid the ccp details sid of this st acc closure details
	*/
	@Override
	public void setCcpDetailsSid(int ccpDetailsSid) {
		_stAccClosureDetails.setCcpDetailsSid(ccpDetailsSid);
	}

	/**
	* Sets whether this st acc closure details is check record.
	*
	* @param checkRecord the check record of this st acc closure details
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_stAccClosureDetails.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company cost center of this st acc closure details.
	*
	* @param companyCostCenter the company cost center of this st acc closure details
	*/
	@Override
	public void setCompanyCostCenter(java.lang.String companyCostCenter) {
		_stAccClosureDetails.setCompanyCostCenter(companyCostCenter);
	}

	/**
	* Sets the company master sid of this st acc closure details.
	*
	* @param companyMasterSid the company master sid of this st acc closure details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_stAccClosureDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company name of this st acc closure details.
	*
	* @param companyName the company name of this st acc closure details
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_stAccClosureDetails.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this st acc closure details.
	*
	* @param companyNo the company no of this st acc closure details
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_stAccClosureDetails.setCompanyNo(companyNo);
	}

	/**
	* Sets the contract master sid of this st acc closure details.
	*
	* @param contractMasterSid the contract master sid of this st acc closure details
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_stAccClosureDetails.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the contract name of this st acc closure details.
	*
	* @param contractName the contract name of this st acc closure details
	*/
	@Override
	public void setContractName(java.lang.String contractName) {
		_stAccClosureDetails.setContractName(contractName);
	}

	/**
	* Sets the contract no of this st acc closure details.
	*
	* @param contractNo the contract no of this st acc closure details
	*/
	@Override
	public void setContractNo(java.lang.String contractNo) {
		_stAccClosureDetails.setContractNo(contractNo);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stAccClosureDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stAccClosureDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stAccClosureDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this st acc closure details.
	*
	* @param itemMasterSid the item master sid of this st acc closure details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_stAccClosureDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item name of this st acc closure details.
	*
	* @param itemName the item name of this st acc closure details
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_stAccClosureDetails.setItemName(itemName);
	}

	/**
	* Sets the last modified date of this st acc closure details.
	*
	* @param lastModifiedDate the last modified date of this st acc closure details
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stAccClosureDetails.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the module name of this st acc closure details.
	*
	* @param moduleName the module name of this st acc closure details
	*/
	@Override
	public void setModuleName(java.lang.String moduleName) {
		_stAccClosureDetails.setModuleName(moduleName);
	}

	/**
	* Sets the ndc8 of this st acc closure details.
	*
	* @param ndc8 the ndc8 of this st acc closure details
	*/
	@Override
	public void setNdc8(java.lang.String ndc8) {
		_stAccClosureDetails.setNdc8(ndc8);
	}

	@Override
	public void setNew(boolean n) {
		_stAccClosureDetails.setNew(n);
	}

	/**
	* Sets the primary key of this st acc closure details.
	*
	* @param primaryKey the primary key of this st acc closure details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_stAccClosureDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stAccClosureDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rs model sid of this st acc closure details.
	*
	* @param rsModelSid the rs model sid of this st acc closure details
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_stAccClosureDetails.setRsModelSid(rsModelSid);
	}

	/**
	* Sets the session ID of this st acc closure details.
	*
	* @param sessionId the session ID of this st acc closure details
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stAccClosureDetails.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st acc closure details.
	*
	* @param userId the user ID of this st acc closure details
	*/
	@Override
	public void setUserId(int userId) {
		_stAccClosureDetails.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StAccClosureDetails> toCacheModel() {
		return _stAccClosureDetails.toCacheModel();
	}

	@Override
	public StAccClosureDetails toEscapedModel() {
		return new StAccClosureDetailsWrapper(_stAccClosureDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stAccClosureDetails.toString();
	}

	@Override
	public StAccClosureDetails toUnescapedModel() {
		return new StAccClosureDetailsWrapper(_stAccClosureDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stAccClosureDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StAccClosureDetailsWrapper)) {
			return false;
		}

		StAccClosureDetailsWrapper stAccClosureDetailsWrapper = (StAccClosureDetailsWrapper)obj;

		if (Objects.equals(_stAccClosureDetails,
					stAccClosureDetailsWrapper._stAccClosureDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public StAccClosureDetails getWrappedModel() {
		return _stAccClosureDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stAccClosureDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stAccClosureDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stAccClosureDetails.resetOriginalValues();
	}

	private final StAccClosureDetails _stAccClosureDetails;
}