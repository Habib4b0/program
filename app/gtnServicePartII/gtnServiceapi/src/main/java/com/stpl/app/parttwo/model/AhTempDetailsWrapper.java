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
 * This class is a wrapper for {@link AhTempDetails}.
 * </p>
 *
 * @author
 * @see AhTempDetails
 * @generated
 */
@ProviderType
public class AhTempDetailsWrapper implements AhTempDetails,
	ModelWrapper<AhTempDetails> {
	public AhTempDetailsWrapper(AhTempDetails ahTempDetails) {
		_ahTempDetails = ahTempDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return AhTempDetails.class;
	}

	@Override
	public String getModelClassName() {
		return AhTempDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkRecord", getCheckRecord());
		attributes.put("contractHolder", getContractHolder());
		attributes.put("userId", getUserId());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("businessUnitNo", getBusinessUnitNo());
		attributes.put("companyName", getCompanyName());
		attributes.put("itemId", getItemId());
		attributes.put("brandName", getBrandName());
		attributes.put("componentName", getComponentName());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("screenName", getScreenName());
		attributes.put("businessUnitName", getBusinessUnitName());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("itemIdentifierType", getItemIdentifierType());
		attributes.put("componentNo", getComponentNo());
		attributes.put("sessionId", getSessionId());
		attributes.put("itemName", getItemName());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("companySid", getCompanySid());
		attributes.put("itemNo", getItemNo());
		attributes.put("componentType", getComponentType());
		attributes.put("theraputicClass", getTheraputicClass());
		attributes.put("componentMasterSid", getComponentMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String contractHolder = (String)attributes.get("contractHolder");

		if (contractHolder != null) {
			setContractHolder(contractHolder);
		}

		String userId = (String)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String businessUnitNo = (String)attributes.get("businessUnitNo");

		if (businessUnitNo != null) {
			setBusinessUnitNo(businessUnitNo);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String brandName = (String)attributes.get("brandName");

		if (brandName != null) {
			setBrandName(brandName);
		}

		String componentName = (String)attributes.get("componentName");

		if (componentName != null) {
			setComponentName(componentName);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String screenName = (String)attributes.get("screenName");

		if (screenName != null) {
			setScreenName(screenName);
		}

		String businessUnitName = (String)attributes.get("businessUnitName");

		if (businessUnitName != null) {
			setBusinessUnitName(businessUnitName);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String itemIdentifierType = (String)attributes.get("itemIdentifierType");

		if (itemIdentifierType != null) {
			setItemIdentifierType(itemIdentifierType);
		}

		String componentNo = (String)attributes.get("componentNo");

		if (componentNo != null) {
			setComponentNo(componentNo);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String itemIdentifier = (String)attributes.get("itemIdentifier");

		if (itemIdentifier != null) {
			setItemIdentifier(itemIdentifier);
		}

		Integer companySid = (Integer)attributes.get("companySid");

		if (companySid != null) {
			setCompanySid(companySid);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		String componentType = (String)attributes.get("componentType");

		if (componentType != null) {
			setComponentType(componentType);
		}

		String theraputicClass = (String)attributes.get("theraputicClass");

		if (theraputicClass != null) {
			setTheraputicClass(theraputicClass);
		}

		Integer componentMasterSid = (Integer)attributes.get(
				"componentMasterSid");

		if (componentMasterSid != null) {
			setComponentMasterSid(componentMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AhTempDetailsWrapper((AhTempDetails)_ahTempDetails.clone());
	}

	@Override
	public int compareTo(AhTempDetails ahTempDetails) {
		return _ahTempDetails.compareTo(ahTempDetails);
	}

	/**
	* Returns the brand name of this ah temp details.
	*
	* @return the brand name of this ah temp details
	*/
	@Override
	public java.lang.String getBrandName() {
		return _ahTempDetails.getBrandName();
	}

	/**
	* Returns the business unit name of this ah temp details.
	*
	* @return the business unit name of this ah temp details
	*/
	@Override
	public java.lang.String getBusinessUnitName() {
		return _ahTempDetails.getBusinessUnitName();
	}

	/**
	* Returns the business unit no of this ah temp details.
	*
	* @return the business unit no of this ah temp details
	*/
	@Override
	public java.lang.String getBusinessUnitNo() {
		return _ahTempDetails.getBusinessUnitNo();
	}

	/**
	* Returns the check record of this ah temp details.
	*
	* @return the check record of this ah temp details
	*/
	@Override
	public boolean getCheckRecord() {
		return _ahTempDetails.getCheckRecord();
	}

	/**
	* Returns the company name of this ah temp details.
	*
	* @return the company name of this ah temp details
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _ahTempDetails.getCompanyName();
	}

	/**
	* Returns the company no of this ah temp details.
	*
	* @return the company no of this ah temp details
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _ahTempDetails.getCompanyNo();
	}

	/**
	* Returns the company sid of this ah temp details.
	*
	* @return the company sid of this ah temp details
	*/
	@Override
	public int getCompanySid() {
		return _ahTempDetails.getCompanySid();
	}

	/**
	* Returns the component master sid of this ah temp details.
	*
	* @return the component master sid of this ah temp details
	*/
	@Override
	public int getComponentMasterSid() {
		return _ahTempDetails.getComponentMasterSid();
	}

	/**
	* Returns the component name of this ah temp details.
	*
	* @return the component name of this ah temp details
	*/
	@Override
	public java.lang.String getComponentName() {
		return _ahTempDetails.getComponentName();
	}

	/**
	* Returns the component no of this ah temp details.
	*
	* @return the component no of this ah temp details
	*/
	@Override
	public java.lang.String getComponentNo() {
		return _ahTempDetails.getComponentNo();
	}

	/**
	* Returns the component type of this ah temp details.
	*
	* @return the component type of this ah temp details
	*/
	@Override
	public java.lang.String getComponentType() {
		return _ahTempDetails.getComponentType();
	}

	/**
	* Returns the contract holder of this ah temp details.
	*
	* @return the contract holder of this ah temp details
	*/
	@Override
	public java.lang.String getContractHolder() {
		return _ahTempDetails.getContractHolder();
	}

	/**
	* Returns the created by of this ah temp details.
	*
	* @return the created by of this ah temp details
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ahTempDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this ah temp details.
	*
	* @return the created date of this ah temp details
	*/
	@Override
	public Date getCreatedDate() {
		return _ahTempDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ahTempDetails.getExpandoBridge();
	}

	/**
	* Returns the item ID of this ah temp details.
	*
	* @return the item ID of this ah temp details
	*/
	@Override
	public java.lang.String getItemId() {
		return _ahTempDetails.getItemId();
	}

	/**
	* Returns the item identifier of this ah temp details.
	*
	* @return the item identifier of this ah temp details
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _ahTempDetails.getItemIdentifier();
	}

	/**
	* Returns the item identifier type of this ah temp details.
	*
	* @return the item identifier type of this ah temp details
	*/
	@Override
	public java.lang.String getItemIdentifierType() {
		return _ahTempDetails.getItemIdentifierType();
	}

	/**
	* Returns the item master sid of this ah temp details.
	*
	* @return the item master sid of this ah temp details
	*/
	@Override
	public int getItemMasterSid() {
		return _ahTempDetails.getItemMasterSid();
	}

	/**
	* Returns the item name of this ah temp details.
	*
	* @return the item name of this ah temp details
	*/
	@Override
	public java.lang.String getItemName() {
		return _ahTempDetails.getItemName();
	}

	/**
	* Returns the item no of this ah temp details.
	*
	* @return the item no of this ah temp details
	*/
	@Override
	public java.lang.String getItemNo() {
		return _ahTempDetails.getItemNo();
	}

	/**
	* Returns the primary key of this ah temp details.
	*
	* @return the primary key of this ah temp details
	*/
	@Override
	public int getPrimaryKey() {
		return _ahTempDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ahTempDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the screen name of this ah temp details.
	*
	* @return the screen name of this ah temp details
	*/
	@Override
	public java.lang.String getScreenName() {
		return _ahTempDetails.getScreenName();
	}

	/**
	* Returns the session ID of this ah temp details.
	*
	* @return the session ID of this ah temp details
	*/
	@Override
	public java.lang.String getSessionId() {
		return _ahTempDetails.getSessionId();
	}

	/**
	* Returns the theraputic class of this ah temp details.
	*
	* @return the theraputic class of this ah temp details
	*/
	@Override
	public java.lang.String getTheraputicClass() {
		return _ahTempDetails.getTheraputicClass();
	}

	/**
	* Returns the user ID of this ah temp details.
	*
	* @return the user ID of this ah temp details
	*/
	@Override
	public java.lang.String getUserId() {
		return _ahTempDetails.getUserId();
	}

	@Override
	public int hashCode() {
		return _ahTempDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ahTempDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ah temp details is check record.
	*
	* @return <code>true</code> if this ah temp details is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ahTempDetails.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ahTempDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ahTempDetails.isNew();
	}

	@Override
	public void persist() {
		_ahTempDetails.persist();
	}

	/**
	* Sets the brand name of this ah temp details.
	*
	* @param brandName the brand name of this ah temp details
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_ahTempDetails.setBrandName(brandName);
	}

	/**
	* Sets the business unit name of this ah temp details.
	*
	* @param businessUnitName the business unit name of this ah temp details
	*/
	@Override
	public void setBusinessUnitName(java.lang.String businessUnitName) {
		_ahTempDetails.setBusinessUnitName(businessUnitName);
	}

	/**
	* Sets the business unit no of this ah temp details.
	*
	* @param businessUnitNo the business unit no of this ah temp details
	*/
	@Override
	public void setBusinessUnitNo(java.lang.String businessUnitNo) {
		_ahTempDetails.setBusinessUnitNo(businessUnitNo);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ahTempDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ah temp details is check record.
	*
	* @param checkRecord the check record of this ah temp details
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ahTempDetails.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company name of this ah temp details.
	*
	* @param companyName the company name of this ah temp details
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_ahTempDetails.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this ah temp details.
	*
	* @param companyNo the company no of this ah temp details
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_ahTempDetails.setCompanyNo(companyNo);
	}

	/**
	* Sets the company sid of this ah temp details.
	*
	* @param companySid the company sid of this ah temp details
	*/
	@Override
	public void setCompanySid(int companySid) {
		_ahTempDetails.setCompanySid(companySid);
	}

	/**
	* Sets the component master sid of this ah temp details.
	*
	* @param componentMasterSid the component master sid of this ah temp details
	*/
	@Override
	public void setComponentMasterSid(int componentMasterSid) {
		_ahTempDetails.setComponentMasterSid(componentMasterSid);
	}

	/**
	* Sets the component name of this ah temp details.
	*
	* @param componentName the component name of this ah temp details
	*/
	@Override
	public void setComponentName(java.lang.String componentName) {
		_ahTempDetails.setComponentName(componentName);
	}

	/**
	* Sets the component no of this ah temp details.
	*
	* @param componentNo the component no of this ah temp details
	*/
	@Override
	public void setComponentNo(java.lang.String componentNo) {
		_ahTempDetails.setComponentNo(componentNo);
	}

	/**
	* Sets the component type of this ah temp details.
	*
	* @param componentType the component type of this ah temp details
	*/
	@Override
	public void setComponentType(java.lang.String componentType) {
		_ahTempDetails.setComponentType(componentType);
	}

	/**
	* Sets the contract holder of this ah temp details.
	*
	* @param contractHolder the contract holder of this ah temp details
	*/
	@Override
	public void setContractHolder(java.lang.String contractHolder) {
		_ahTempDetails.setContractHolder(contractHolder);
	}

	/**
	* Sets the created by of this ah temp details.
	*
	* @param createdBy the created by of this ah temp details
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ahTempDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ah temp details.
	*
	* @param createdDate the created date of this ah temp details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ahTempDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ahTempDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ahTempDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ahTempDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item ID of this ah temp details.
	*
	* @param itemId the item ID of this ah temp details
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ahTempDetails.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this ah temp details.
	*
	* @param itemIdentifier the item identifier of this ah temp details
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_ahTempDetails.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier type of this ah temp details.
	*
	* @param itemIdentifierType the item identifier type of this ah temp details
	*/
	@Override
	public void setItemIdentifierType(java.lang.String itemIdentifierType) {
		_ahTempDetails.setItemIdentifierType(itemIdentifierType);
	}

	/**
	* Sets the item master sid of this ah temp details.
	*
	* @param itemMasterSid the item master sid of this ah temp details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_ahTempDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item name of this ah temp details.
	*
	* @param itemName the item name of this ah temp details
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_ahTempDetails.setItemName(itemName);
	}

	/**
	* Sets the item no of this ah temp details.
	*
	* @param itemNo the item no of this ah temp details
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_ahTempDetails.setItemNo(itemNo);
	}

	@Override
	public void setNew(boolean n) {
		_ahTempDetails.setNew(n);
	}

	/**
	* Sets the primary key of this ah temp details.
	*
	* @param primaryKey the primary key of this ah temp details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ahTempDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ahTempDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the screen name of this ah temp details.
	*
	* @param screenName the screen name of this ah temp details
	*/
	@Override
	public void setScreenName(java.lang.String screenName) {
		_ahTempDetails.setScreenName(screenName);
	}

	/**
	* Sets the session ID of this ah temp details.
	*
	* @param sessionId the session ID of this ah temp details
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_ahTempDetails.setSessionId(sessionId);
	}

	/**
	* Sets the theraputic class of this ah temp details.
	*
	* @param theraputicClass the theraputic class of this ah temp details
	*/
	@Override
	public void setTheraputicClass(java.lang.String theraputicClass) {
		_ahTempDetails.setTheraputicClass(theraputicClass);
	}

	/**
	* Sets the user ID of this ah temp details.
	*
	* @param userId the user ID of this ah temp details
	*/
	@Override
	public void setUserId(java.lang.String userId) {
		_ahTempDetails.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AhTempDetails> toCacheModel() {
		return _ahTempDetails.toCacheModel();
	}

	@Override
	public AhTempDetails toEscapedModel() {
		return new AhTempDetailsWrapper(_ahTempDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ahTempDetails.toString();
	}

	@Override
	public AhTempDetails toUnescapedModel() {
		return new AhTempDetailsWrapper(_ahTempDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ahTempDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AhTempDetailsWrapper)) {
			return false;
		}

		AhTempDetailsWrapper ahTempDetailsWrapper = (AhTempDetailsWrapper)obj;

		if (Objects.equals(_ahTempDetails, ahTempDetailsWrapper._ahTempDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public AhTempDetails getWrappedModel() {
		return _ahTempDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ahTempDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ahTempDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ahTempDetails.resetOriginalValues();
	}

	private final AhTempDetails _ahTempDetails;
}