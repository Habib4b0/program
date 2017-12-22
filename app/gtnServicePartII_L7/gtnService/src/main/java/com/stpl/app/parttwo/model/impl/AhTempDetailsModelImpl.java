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

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.parttwo.model.AhTempDetails;
import com.stpl.app.parttwo.model.AhTempDetailsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the AhTempDetails service. Represents a row in the &quot;AH_TEMP_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AhTempDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AhTempDetailsImpl}.
 * </p>
 *
 * @author
 * @see AhTempDetailsImpl
 * @see AhTempDetails
 * @see AhTempDetailsModel
 * @generated
 */
@ProviderType
public class AhTempDetailsModelImpl extends BaseModelImpl<AhTempDetails>
	implements AhTempDetailsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ah temp details model instance should use the {@link AhTempDetails} interface instead.
	 */
	public static final String TABLE_NAME = "AH_TEMP_DETAILS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "CHECK_RECORD", Types.BOOLEAN },
			{ "CONTRACT_HOLDER", Types.VARCHAR },
			{ "USER_ID", Types.VARCHAR },
			{ "ITEM_MASTER_SID", Types.INTEGER },
			{ "BUSINESS_UNIT_NO", Types.VARCHAR },
			{ "COMPANY_NAME", Types.VARCHAR },
			{ "ITEM_ID", Types.VARCHAR },
			{ "BRAND_NAME", Types.VARCHAR },
			{ "COMPONENT_NAME", Types.VARCHAR },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "CREATED_BY", Types.VARCHAR },
			{ "SCREEN_NAME", Types.VARCHAR },
			{ "BUSINESS_UNIT_NAME", Types.VARCHAR },
			{ "COMPANY_NO", Types.VARCHAR },
			{ "ITEM_IDENTIFIER_TYPE", Types.VARCHAR },
			{ "COMPONENT_NO", Types.VARCHAR },
			{ "SESSION_ID", Types.VARCHAR },
			{ "ITEM_NAME", Types.VARCHAR },
			{ "ITEM_IDENTIFIER", Types.VARCHAR },
			{ "COMPANY_SID", Types.INTEGER },
			{ "ITEM_NO", Types.VARCHAR },
			{ "COMPONENT_TYPE", Types.VARCHAR },
			{ "THERAPUTIC_CLASS", Types.VARCHAR },
			{ "COMPONENT_MASTER_SID", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CHECK_RECORD", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("CONTRACT_HOLDER", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("USER_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ITEM_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("BUSINESS_UNIT_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ITEM_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("BRAND_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPONENT_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("SCREEN_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("BUSINESS_UNIT_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ITEM_IDENTIFIER_TYPE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPONENT_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("SESSION_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ITEM_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ITEM_IDENTIFIER", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ITEM_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPONENT_TYPE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("THERAPUTIC_CLASS", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPONENT_MASTER_SID", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table AH_TEMP_DETAILS (CHECK_RECORD BOOLEAN,CONTRACT_HOLDER VARCHAR(75) null,USER_ID VARCHAR(75) null,ITEM_MASTER_SID INTEGER,BUSINESS_UNIT_NO VARCHAR(75) null,COMPANY_NAME VARCHAR(75) null,ITEM_ID VARCHAR(75) null,BRAND_NAME VARCHAR(75) null,COMPONENT_NAME VARCHAR(75) null,CREATED_DATE DATE null,CREATED_BY VARCHAR(75) null,SCREEN_NAME VARCHAR(75) null,BUSINESS_UNIT_NAME VARCHAR(75) null,COMPANY_NO VARCHAR(75) null,ITEM_IDENTIFIER_TYPE VARCHAR(75) null,COMPONENT_NO VARCHAR(75) null,SESSION_ID VARCHAR(75) null,ITEM_NAME VARCHAR(75) null,ITEM_IDENTIFIER VARCHAR(75) null,COMPANY_SID INTEGER,ITEM_NO VARCHAR(75) null,COMPONENT_TYPE VARCHAR(75) null,THERAPUTIC_CLASS VARCHAR(75) null,COMPONENT_MASTER_SID INTEGER not null primary key)";
	public static final String TABLE_SQL_DROP = "drop table AH_TEMP_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY ahTempDetails.componentMasterSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY AH_TEMP_DETAILS.COMPONENT_MASTER_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.parttwo.model.AhTempDetails"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.parttwo.model.AhTempDetails"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.parttwo.model.AhTempDetails"));

	public AhTempDetailsModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _componentMasterSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setComponentMasterSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _componentMasterSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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
	public boolean getCheckRecord() {
		return _checkRecord;
	}

	@Override
	public boolean isCheckRecord() {
		return _checkRecord;
	}

	@Override
	public void setCheckRecord(boolean checkRecord) {
		_checkRecord = checkRecord;
	}

	@Override
	public String getContractHolder() {
		if (_contractHolder == null) {
			return StringPool.BLANK;
		}
		else {
			return _contractHolder;
		}
	}

	@Override
	public void setContractHolder(String contractHolder) {
		_contractHolder = contractHolder;
	}

	@Override
	public String getUserId() {
		if (_userId == null) {
			return StringPool.BLANK;
		}
		else {
			return _userId;
		}
	}

	@Override
	public void setUserId(String userId) {
		_userId = userId;
	}

	@Override
	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	@Override
	public String getBusinessUnitNo() {
		if (_businessUnitNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _businessUnitNo;
		}
	}

	@Override
	public void setBusinessUnitNo(String businessUnitNo) {
		_businessUnitNo = businessUnitNo;
	}

	@Override
	public String getCompanyName() {
		if (_companyName == null) {
			return StringPool.BLANK;
		}
		else {
			return _companyName;
		}
	}

	@Override
	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	@Override
	public String getItemId() {
		if (_itemId == null) {
			return StringPool.BLANK;
		}
		else {
			return _itemId;
		}
	}

	@Override
	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	@Override
	public String getBrandName() {
		if (_brandName == null) {
			return StringPool.BLANK;
		}
		else {
			return _brandName;
		}
	}

	@Override
	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	@Override
	public String getComponentName() {
		if (_componentName == null) {
			return StringPool.BLANK;
		}
		else {
			return _componentName;
		}
	}

	@Override
	public void setComponentName(String componentName) {
		_componentName = componentName;
	}

	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	@Override
	public String getCreatedBy() {
		if (_createdBy == null) {
			return StringPool.BLANK;
		}
		else {
			return _createdBy;
		}
	}

	@Override
	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	@Override
	public String getScreenName() {
		if (_screenName == null) {
			return StringPool.BLANK;
		}
		else {
			return _screenName;
		}
	}

	@Override
	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	@Override
	public String getBusinessUnitName() {
		if (_businessUnitName == null) {
			return StringPool.BLANK;
		}
		else {
			return _businessUnitName;
		}
	}

	@Override
	public void setBusinessUnitName(String businessUnitName) {
		_businessUnitName = businessUnitName;
	}

	@Override
	public String getCompanyNo() {
		if (_companyNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _companyNo;
		}
	}

	@Override
	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	@Override
	public String getItemIdentifierType() {
		if (_itemIdentifierType == null) {
			return StringPool.BLANK;
		}
		else {
			return _itemIdentifierType;
		}
	}

	@Override
	public void setItemIdentifierType(String itemIdentifierType) {
		_itemIdentifierType = itemIdentifierType;
	}

	@Override
	public String getComponentNo() {
		if (_componentNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _componentNo;
		}
	}

	@Override
	public void setComponentNo(String componentNo) {
		_componentNo = componentNo;
	}

	@Override
	public String getSessionId() {
		if (_sessionId == null) {
			return StringPool.BLANK;
		}
		else {
			return _sessionId;
		}
	}

	@Override
	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	@Override
	public String getItemName() {
		if (_itemName == null) {
			return StringPool.BLANK;
		}
		else {
			return _itemName;
		}
	}

	@Override
	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	@Override
	public String getItemIdentifier() {
		if (_itemIdentifier == null) {
			return StringPool.BLANK;
		}
		else {
			return _itemIdentifier;
		}
	}

	@Override
	public void setItemIdentifier(String itemIdentifier) {
		_itemIdentifier = itemIdentifier;
	}

	@Override
	public int getCompanySid() {
		return _companySid;
	}

	@Override
	public void setCompanySid(int companySid) {
		_companySid = companySid;
	}

	@Override
	public String getItemNo() {
		if (_itemNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _itemNo;
		}
	}

	@Override
	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	@Override
	public String getComponentType() {
		if (_componentType == null) {
			return StringPool.BLANK;
		}
		else {
			return _componentType;
		}
	}

	@Override
	public void setComponentType(String componentType) {
		_componentType = componentType;
	}

	@Override
	public String getTheraputicClass() {
		if (_theraputicClass == null) {
			return StringPool.BLANK;
		}
		else {
			return _theraputicClass;
		}
	}

	@Override
	public void setTheraputicClass(String theraputicClass) {
		_theraputicClass = theraputicClass;
	}

	@Override
	public int getComponentMasterSid() {
		return _componentMasterSid;
	}

	@Override
	public void setComponentMasterSid(int componentMasterSid) {
		_componentMasterSid = componentMasterSid;
	}

	@Override
	public AhTempDetails toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AhTempDetails)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AhTempDetailsImpl ahTempDetailsImpl = new AhTempDetailsImpl();

		ahTempDetailsImpl.setCheckRecord(getCheckRecord());
		ahTempDetailsImpl.setContractHolder(getContractHolder());
		ahTempDetailsImpl.setUserId(getUserId());
		ahTempDetailsImpl.setItemMasterSid(getItemMasterSid());
		ahTempDetailsImpl.setBusinessUnitNo(getBusinessUnitNo());
		ahTempDetailsImpl.setCompanyName(getCompanyName());
		ahTempDetailsImpl.setItemId(getItemId());
		ahTempDetailsImpl.setBrandName(getBrandName());
		ahTempDetailsImpl.setComponentName(getComponentName());
		ahTempDetailsImpl.setCreatedDate(getCreatedDate());
		ahTempDetailsImpl.setCreatedBy(getCreatedBy());
		ahTempDetailsImpl.setScreenName(getScreenName());
		ahTempDetailsImpl.setBusinessUnitName(getBusinessUnitName());
		ahTempDetailsImpl.setCompanyNo(getCompanyNo());
		ahTempDetailsImpl.setItemIdentifierType(getItemIdentifierType());
		ahTempDetailsImpl.setComponentNo(getComponentNo());
		ahTempDetailsImpl.setSessionId(getSessionId());
		ahTempDetailsImpl.setItemName(getItemName());
		ahTempDetailsImpl.setItemIdentifier(getItemIdentifier());
		ahTempDetailsImpl.setCompanySid(getCompanySid());
		ahTempDetailsImpl.setItemNo(getItemNo());
		ahTempDetailsImpl.setComponentType(getComponentType());
		ahTempDetailsImpl.setTheraputicClass(getTheraputicClass());
		ahTempDetailsImpl.setComponentMasterSid(getComponentMasterSid());

		ahTempDetailsImpl.resetOriginalValues();

		return ahTempDetailsImpl;
	}

	@Override
	public int compareTo(AhTempDetails ahTempDetails) {
		int primaryKey = ahTempDetails.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AhTempDetails)) {
			return false;
		}

		AhTempDetails ahTempDetails = (AhTempDetails)obj;

		int primaryKey = ahTempDetails.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<AhTempDetails> toCacheModel() {
		AhTempDetailsCacheModel ahTempDetailsCacheModel = new AhTempDetailsCacheModel();

		ahTempDetailsCacheModel.checkRecord = getCheckRecord();

		ahTempDetailsCacheModel.contractHolder = getContractHolder();

		String contractHolder = ahTempDetailsCacheModel.contractHolder;

		if ((contractHolder != null) && (contractHolder.length() == 0)) {
			ahTempDetailsCacheModel.contractHolder = null;
		}

		ahTempDetailsCacheModel.userId = getUserId();

		String userId = ahTempDetailsCacheModel.userId;

		if ((userId != null) && (userId.length() == 0)) {
			ahTempDetailsCacheModel.userId = null;
		}

		ahTempDetailsCacheModel.itemMasterSid = getItemMasterSid();

		ahTempDetailsCacheModel.businessUnitNo = getBusinessUnitNo();

		String businessUnitNo = ahTempDetailsCacheModel.businessUnitNo;

		if ((businessUnitNo != null) && (businessUnitNo.length() == 0)) {
			ahTempDetailsCacheModel.businessUnitNo = null;
		}

		ahTempDetailsCacheModel.companyName = getCompanyName();

		String companyName = ahTempDetailsCacheModel.companyName;

		if ((companyName != null) && (companyName.length() == 0)) {
			ahTempDetailsCacheModel.companyName = null;
		}

		ahTempDetailsCacheModel.itemId = getItemId();

		String itemId = ahTempDetailsCacheModel.itemId;

		if ((itemId != null) && (itemId.length() == 0)) {
			ahTempDetailsCacheModel.itemId = null;
		}

		ahTempDetailsCacheModel.brandName = getBrandName();

		String brandName = ahTempDetailsCacheModel.brandName;

		if ((brandName != null) && (brandName.length() == 0)) {
			ahTempDetailsCacheModel.brandName = null;
		}

		ahTempDetailsCacheModel.componentName = getComponentName();

		String componentName = ahTempDetailsCacheModel.componentName;

		if ((componentName != null) && (componentName.length() == 0)) {
			ahTempDetailsCacheModel.componentName = null;
		}

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			ahTempDetailsCacheModel.createdDate = createdDate.getTime();
		}
		else {
			ahTempDetailsCacheModel.createdDate = Long.MIN_VALUE;
		}

		ahTempDetailsCacheModel.createdBy = getCreatedBy();

		String createdBy = ahTempDetailsCacheModel.createdBy;

		if ((createdBy != null) && (createdBy.length() == 0)) {
			ahTempDetailsCacheModel.createdBy = null;
		}

		ahTempDetailsCacheModel.screenName = getScreenName();

		String screenName = ahTempDetailsCacheModel.screenName;

		if ((screenName != null) && (screenName.length() == 0)) {
			ahTempDetailsCacheModel.screenName = null;
		}

		ahTempDetailsCacheModel.businessUnitName = getBusinessUnitName();

		String businessUnitName = ahTempDetailsCacheModel.businessUnitName;

		if ((businessUnitName != null) && (businessUnitName.length() == 0)) {
			ahTempDetailsCacheModel.businessUnitName = null;
		}

		ahTempDetailsCacheModel.companyNo = getCompanyNo();

		String companyNo = ahTempDetailsCacheModel.companyNo;

		if ((companyNo != null) && (companyNo.length() == 0)) {
			ahTempDetailsCacheModel.companyNo = null;
		}

		ahTempDetailsCacheModel.itemIdentifierType = getItemIdentifierType();

		String itemIdentifierType = ahTempDetailsCacheModel.itemIdentifierType;

		if ((itemIdentifierType != null) && (itemIdentifierType.length() == 0)) {
			ahTempDetailsCacheModel.itemIdentifierType = null;
		}

		ahTempDetailsCacheModel.componentNo = getComponentNo();

		String componentNo = ahTempDetailsCacheModel.componentNo;

		if ((componentNo != null) && (componentNo.length() == 0)) {
			ahTempDetailsCacheModel.componentNo = null;
		}

		ahTempDetailsCacheModel.sessionId = getSessionId();

		String sessionId = ahTempDetailsCacheModel.sessionId;

		if ((sessionId != null) && (sessionId.length() == 0)) {
			ahTempDetailsCacheModel.sessionId = null;
		}

		ahTempDetailsCacheModel.itemName = getItemName();

		String itemName = ahTempDetailsCacheModel.itemName;

		if ((itemName != null) && (itemName.length() == 0)) {
			ahTempDetailsCacheModel.itemName = null;
		}

		ahTempDetailsCacheModel.itemIdentifier = getItemIdentifier();

		String itemIdentifier = ahTempDetailsCacheModel.itemIdentifier;

		if ((itemIdentifier != null) && (itemIdentifier.length() == 0)) {
			ahTempDetailsCacheModel.itemIdentifier = null;
		}

		ahTempDetailsCacheModel.companySid = getCompanySid();

		ahTempDetailsCacheModel.itemNo = getItemNo();

		String itemNo = ahTempDetailsCacheModel.itemNo;

		if ((itemNo != null) && (itemNo.length() == 0)) {
			ahTempDetailsCacheModel.itemNo = null;
		}

		ahTempDetailsCacheModel.componentType = getComponentType();

		String componentType = ahTempDetailsCacheModel.componentType;

		if ((componentType != null) && (componentType.length() == 0)) {
			ahTempDetailsCacheModel.componentType = null;
		}

		ahTempDetailsCacheModel.theraputicClass = getTheraputicClass();

		String theraputicClass = ahTempDetailsCacheModel.theraputicClass;

		if ((theraputicClass != null) && (theraputicClass.length() == 0)) {
			ahTempDetailsCacheModel.theraputicClass = null;
		}

		ahTempDetailsCacheModel.componentMasterSid = getComponentMasterSid();

		return ahTempDetailsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{checkRecord=");
		sb.append(getCheckRecord());
		sb.append(", contractHolder=");
		sb.append(getContractHolder());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", itemMasterSid=");
		sb.append(getItemMasterSid());
		sb.append(", businessUnitNo=");
		sb.append(getBusinessUnitNo());
		sb.append(", companyName=");
		sb.append(getCompanyName());
		sb.append(", itemId=");
		sb.append(getItemId());
		sb.append(", brandName=");
		sb.append(getBrandName());
		sb.append(", componentName=");
		sb.append(getComponentName());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", screenName=");
		sb.append(getScreenName());
		sb.append(", businessUnitName=");
		sb.append(getBusinessUnitName());
		sb.append(", companyNo=");
		sb.append(getCompanyNo());
		sb.append(", itemIdentifierType=");
		sb.append(getItemIdentifierType());
		sb.append(", componentNo=");
		sb.append(getComponentNo());
		sb.append(", sessionId=");
		sb.append(getSessionId());
		sb.append(", itemName=");
		sb.append(getItemName());
		sb.append(", itemIdentifier=");
		sb.append(getItemIdentifier());
		sb.append(", companySid=");
		sb.append(getCompanySid());
		sb.append(", itemNo=");
		sb.append(getItemNo());
		sb.append(", componentType=");
		sb.append(getComponentType());
		sb.append(", theraputicClass=");
		sb.append(getTheraputicClass());
		sb.append(", componentMasterSid=");
		sb.append(getComponentMasterSid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(76);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.parttwo.model.AhTempDetails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>checkRecord</column-name><column-value><![CDATA[");
		sb.append(getCheckRecord());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contractHolder</column-name><column-value><![CDATA[");
		sb.append(getContractHolder());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
		sb.append(getItemMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>businessUnitNo</column-name><column-value><![CDATA[");
		sb.append(getBusinessUnitNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyName</column-name><column-value><![CDATA[");
		sb.append(getCompanyName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemId</column-name><column-value><![CDATA[");
		sb.append(getItemId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>brandName</column-name><column-value><![CDATA[");
		sb.append(getBrandName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>componentName</column-name><column-value><![CDATA[");
		sb.append(getComponentName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdDate</column-name><column-value><![CDATA[");
		sb.append(getCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>screenName</column-name><column-value><![CDATA[");
		sb.append(getScreenName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>businessUnitName</column-name><column-value><![CDATA[");
		sb.append(getBusinessUnitName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyNo</column-name><column-value><![CDATA[");
		sb.append(getCompanyNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemIdentifierType</column-name><column-value><![CDATA[");
		sb.append(getItemIdentifierType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>componentNo</column-name><column-value><![CDATA[");
		sb.append(getComponentNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionId</column-name><column-value><![CDATA[");
		sb.append(getSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemName</column-name><column-value><![CDATA[");
		sb.append(getItemName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
		sb.append(getItemIdentifier());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companySid</column-name><column-value><![CDATA[");
		sb.append(getCompanySid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemNo</column-name><column-value><![CDATA[");
		sb.append(getItemNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>componentType</column-name><column-value><![CDATA[");
		sb.append(getComponentType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>theraputicClass</column-name><column-value><![CDATA[");
		sb.append(getTheraputicClass());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>componentMasterSid</column-name><column-value><![CDATA[");
		sb.append(getComponentMasterSid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = AhTempDetails.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			AhTempDetails.class
		};
	private boolean _checkRecord;
	private String _contractHolder;
	private String _userId;
	private int _itemMasterSid;
	private String _businessUnitNo;
	private String _companyName;
	private String _itemId;
	private String _brandName;
	private String _componentName;
	private Date _createdDate;
	private String _createdBy;
	private String _screenName;
	private String _businessUnitName;
	private String _companyNo;
	private String _itemIdentifierType;
	private String _componentNo;
	private String _sessionId;
	private String _itemName;
	private String _itemIdentifier;
	private int _companySid;
	private String _itemNo;
	private String _componentType;
	private String _theraputicClass;
	private int _componentMasterSid;
	private AhTempDetails _escapedModel;
}