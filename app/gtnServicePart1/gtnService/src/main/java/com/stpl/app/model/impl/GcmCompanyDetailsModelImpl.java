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

package com.stpl.app.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.model.GcmCompanyDetails;
import com.stpl.app.model.GcmCompanyDetailsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the GcmCompanyDetails service. Represents a row in the &quot;GCM_COMPANY_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link GcmCompanyDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GcmCompanyDetailsImpl}.
 * </p>
 *
 * @author
 * @see GcmCompanyDetailsImpl
 * @see GcmCompanyDetails
 * @see GcmCompanyDetailsModel
 * @generated
 */
@ProviderType
public class GcmCompanyDetailsModelImpl extends BaseModelImpl<GcmCompanyDetails>
	implements GcmCompanyDetailsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a gcm company details model instance should use the {@link GcmCompanyDetails} interface instead.
	 */
	public static final String TABLE_NAME = "GCM_COMPANY_DETAILS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "CHECK_RECORD", Types.BOOLEAN },
			{ "USER_ID", Types.INTEGER },
			{ "MODULE_NAME", Types.VARCHAR },
			{ "COMPANY_ID", Types.VARCHAR },
			{ "CFP_DETAILS_TRADE_CLASS", Types.VARCHAR },
			{ "COMPANY_NAME", Types.VARCHAR },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "GCM_COMPANY_DETAILS_SID", Types.INTEGER },
			{ "ITEM_CFP_DETAILS_SID", Types.INTEGER },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "CREATED_BY", Types.INTEGER },
			{ "COMPANY_START_DATE", Types.TIMESTAMP },
			{ "COMPANY_NO", Types.VARCHAR },
			{ "COMPANY_STATUS", Types.VARCHAR },
			{ "SESSION_ID", Types.VARCHAR },
			{ "COMPANY_END_DATE", Types.TIMESTAMP },
			{ "CFP_DETAILS_START_DATE", Types.TIMESTAMP },
			{ "OPERATION", Types.VARCHAR },
			{ "CFP_MODEL_SID", Types.INTEGER },
			{ "MODIFIED_BY", Types.INTEGER },
			{ "SUB_MODULE_NAME", Types.VARCHAR },
			{ "CFP_DETAILS_END_DATE", Types.TIMESTAMP },
			{ "COMPANY_STATUS_SID", Types.INTEGER },
			{ "COMPANY_MASTER_SID", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CHECK_RECORD", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("USER_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("MODULE_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CFP_DETAILS_TRADE_CLASS", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("GCM_COMPANY_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ITEM_CFP_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("COMPANY_START_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("COMPANY_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_STATUS", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("SESSION_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_END_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CFP_DETAILS_START_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("OPERATION", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CFP_MODEL_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("SUB_MODULE_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CFP_DETAILS_END_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("COMPANY_STATUS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("COMPANY_MASTER_SID", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table GCM_COMPANY_DETAILS (CHECK_RECORD BOOLEAN,USER_ID INTEGER,MODULE_NAME VARCHAR(75) null,COMPANY_ID VARCHAR(75) null,CFP_DETAILS_TRADE_CLASS VARCHAR(75) null,COMPANY_NAME VARCHAR(75) null,MODIFIED_DATE DATE null,GCM_COMPANY_DETAILS_SID INTEGER not null primary key IDENTITY,ITEM_CFP_DETAILS_SID INTEGER,CREATED_DATE DATE null,CREATED_BY INTEGER,COMPANY_START_DATE DATE null,COMPANY_NO VARCHAR(75) null,COMPANY_STATUS VARCHAR(75) null,SESSION_ID VARCHAR(75) null,COMPANY_END_DATE DATE null,CFP_DETAILS_START_DATE DATE null,OPERATION VARCHAR(75) null,CFP_MODEL_SID INTEGER,MODIFIED_BY INTEGER,SUB_MODULE_NAME VARCHAR(75) null,CFP_DETAILS_END_DATE DATE null,COMPANY_STATUS_SID INTEGER,COMPANY_MASTER_SID INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table GCM_COMPANY_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY gcmCompanyDetails.gcmCompanyDetailsSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY GCM_COMPANY_DETAILS.GCM_COMPANY_DETAILS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.GcmCompanyDetails"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.GcmCompanyDetails"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.GcmCompanyDetails"));

	public GcmCompanyDetailsModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _gcmCompanyDetailsSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setGcmCompanyDetailsSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _gcmCompanyDetailsSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return GcmCompanyDetails.class;
	}

	@Override
	public String getModelClassName() {
		return GcmCompanyDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkRecord", getCheckRecord());
		attributes.put("userId", getUserId());
		attributes.put("moduleName", getModuleName());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("cfpDetailsTradeClass", getCfpDetailsTradeClass());
		attributes.put("companyName", getCompanyName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("gcmCompanyDetailsSid", getGcmCompanyDetailsSid());
		attributes.put("itemCfpDetailsSid", getItemCfpDetailsSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("companyStartDate", getCompanyStartDate());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("companyStatus", getCompanyStatus());
		attributes.put("sessionId", getSessionId());
		attributes.put("companyEndDate", getCompanyEndDate());
		attributes.put("cfpDetailsStartDate", getCfpDetailsStartDate());
		attributes.put("operation", getOperation());
		attributes.put("cfpModelSid", getCfpModelSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("subModuleName", getSubModuleName());
		attributes.put("cfpDetailsEndDate", getCfpDetailsEndDate());
		attributes.put("companyStatusSid", getCompanyStatusSid());
		attributes.put("companyMasterSid", getCompanyMasterSid());

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

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String moduleName = (String)attributes.get("moduleName");

		if (moduleName != null) {
			setModuleName(moduleName);
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

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer gcmCompanyDetailsSid = (Integer)attributes.get(
				"gcmCompanyDetailsSid");

		if (gcmCompanyDetailsSid != null) {
			setGcmCompanyDetailsSid(gcmCompanyDetailsSid);
		}

		Integer itemCfpDetailsSid = (Integer)attributes.get("itemCfpDetailsSid");

		if (itemCfpDetailsSid != null) {
			setItemCfpDetailsSid(itemCfpDetailsSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date companyStartDate = (Date)attributes.get("companyStartDate");

		if (companyStartDate != null) {
			setCompanyStartDate(companyStartDate);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String companyStatus = (String)attributes.get("companyStatus");

		if (companyStatus != null) {
			setCompanyStatus(companyStatus);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Date companyEndDate = (Date)attributes.get("companyEndDate");

		if (companyEndDate != null) {
			setCompanyEndDate(companyEndDate);
		}

		Date cfpDetailsStartDate = (Date)attributes.get("cfpDetailsStartDate");

		if (cfpDetailsStartDate != null) {
			setCfpDetailsStartDate(cfpDetailsStartDate);
		}

		String operation = (String)attributes.get("operation");

		if (operation != null) {
			setOperation(operation);
		}

		Integer cfpModelSid = (Integer)attributes.get("cfpModelSid");

		if (cfpModelSid != null) {
			setCfpModelSid(cfpModelSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String subModuleName = (String)attributes.get("subModuleName");

		if (subModuleName != null) {
			setSubModuleName(subModuleName);
		}

		Date cfpDetailsEndDate = (Date)attributes.get("cfpDetailsEndDate");

		if (cfpDetailsEndDate != null) {
			setCfpDetailsEndDate(cfpDetailsEndDate);
		}

		Integer companyStatusSid = (Integer)attributes.get("companyStatusSid");

		if (companyStatusSid != null) {
			setCompanyStatusSid(companyStatusSid);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
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
	public int getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(int userId) {
		_userId = userId;
	}

	@Override
	public String getModuleName() {
		if (_moduleName == null) {
			return StringPool.BLANK;
		}
		else {
			return _moduleName;
		}
	}

	@Override
	public void setModuleName(String moduleName) {
		_moduleName = moduleName;
	}

	@Override
	public String getCompanyStringId() {
		if (_companyStringId == null) {
			return StringPool.BLANK;
		}
		else {
			return _companyStringId;
		}
	}

	@Override
	public void setCompanyStringId(String companyStringId) {
		_companyStringId = companyStringId;
	}

	@Override
	public String getCfpDetailsTradeClass() {
		if (_cfpDetailsTradeClass == null) {
			return StringPool.BLANK;
		}
		else {
			return _cfpDetailsTradeClass;
		}
	}

	@Override
	public void setCfpDetailsTradeClass(String cfpDetailsTradeClass) {
		_cfpDetailsTradeClass = cfpDetailsTradeClass;
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
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public int getGcmCompanyDetailsSid() {
		return _gcmCompanyDetailsSid;
	}

	@Override
	public void setGcmCompanyDetailsSid(int gcmCompanyDetailsSid) {
		_gcmCompanyDetailsSid = gcmCompanyDetailsSid;
	}

	@Override
	public int getItemCfpDetailsSid() {
		return _itemCfpDetailsSid;
	}

	@Override
	public void setItemCfpDetailsSid(int itemCfpDetailsSid) {
		_itemCfpDetailsSid = itemCfpDetailsSid;
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
	public int getCreatedBy() {
		return _createdBy;
	}

	@Override
	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	@Override
	public Date getCompanyStartDate() {
		return _companyStartDate;
	}

	@Override
	public void setCompanyStartDate(Date companyStartDate) {
		_companyStartDate = companyStartDate;
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
	public String getCompanyStatus() {
		if (_companyStatus == null) {
			return StringPool.BLANK;
		}
		else {
			return _companyStatus;
		}
	}

	@Override
	public void setCompanyStatus(String companyStatus) {
		_companyStatus = companyStatus;
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
	public Date getCompanyEndDate() {
		return _companyEndDate;
	}

	@Override
	public void setCompanyEndDate(Date companyEndDate) {
		_companyEndDate = companyEndDate;
	}

	@Override
	public Date getCfpDetailsStartDate() {
		return _cfpDetailsStartDate;
	}

	@Override
	public void setCfpDetailsStartDate(Date cfpDetailsStartDate) {
		_cfpDetailsStartDate = cfpDetailsStartDate;
	}

	@Override
	public String getOperation() {
		if (_operation == null) {
			return StringPool.BLANK;
		}
		else {
			return _operation;
		}
	}

	@Override
	public void setOperation(String operation) {
		_operation = operation;
	}

	@Override
	public int getCfpModelSid() {
		return _cfpModelSid;
	}

	@Override
	public void setCfpModelSid(int cfpModelSid) {
		_cfpModelSid = cfpModelSid;
	}

	@Override
	public int getModifiedBy() {
		return _modifiedBy;
	}

	@Override
	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	@Override
	public String getSubModuleName() {
		if (_subModuleName == null) {
			return StringPool.BLANK;
		}
		else {
			return _subModuleName;
		}
	}

	@Override
	public void setSubModuleName(String subModuleName) {
		_subModuleName = subModuleName;
	}

	@Override
	public Date getCfpDetailsEndDate() {
		return _cfpDetailsEndDate;
	}

	@Override
	public void setCfpDetailsEndDate(Date cfpDetailsEndDate) {
		_cfpDetailsEndDate = cfpDetailsEndDate;
	}

	@Override
	public int getCompanyStatusSid() {
		return _companyStatusSid;
	}

	@Override
	public void setCompanyStatusSid(int companyStatusSid) {
		_companyStatusSid = companyStatusSid;
	}

	@Override
	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	@Override
	public GcmCompanyDetails toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (GcmCompanyDetails)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		GcmCompanyDetailsImpl gcmCompanyDetailsImpl = new GcmCompanyDetailsImpl();

		gcmCompanyDetailsImpl.setCheckRecord(getCheckRecord());
		gcmCompanyDetailsImpl.setUserId(getUserId());
		gcmCompanyDetailsImpl.setModuleName(getModuleName());
		gcmCompanyDetailsImpl.setCompanyStringId(getCompanyStringId());
		gcmCompanyDetailsImpl.setCfpDetailsTradeClass(getCfpDetailsTradeClass());
		gcmCompanyDetailsImpl.setCompanyName(getCompanyName());
		gcmCompanyDetailsImpl.setModifiedDate(getModifiedDate());
		gcmCompanyDetailsImpl.setGcmCompanyDetailsSid(getGcmCompanyDetailsSid());
		gcmCompanyDetailsImpl.setItemCfpDetailsSid(getItemCfpDetailsSid());
		gcmCompanyDetailsImpl.setCreatedDate(getCreatedDate());
		gcmCompanyDetailsImpl.setCreatedBy(getCreatedBy());
		gcmCompanyDetailsImpl.setCompanyStartDate(getCompanyStartDate());
		gcmCompanyDetailsImpl.setCompanyNo(getCompanyNo());
		gcmCompanyDetailsImpl.setCompanyStatus(getCompanyStatus());
		gcmCompanyDetailsImpl.setSessionId(getSessionId());
		gcmCompanyDetailsImpl.setCompanyEndDate(getCompanyEndDate());
		gcmCompanyDetailsImpl.setCfpDetailsStartDate(getCfpDetailsStartDate());
		gcmCompanyDetailsImpl.setOperation(getOperation());
		gcmCompanyDetailsImpl.setCfpModelSid(getCfpModelSid());
		gcmCompanyDetailsImpl.setModifiedBy(getModifiedBy());
		gcmCompanyDetailsImpl.setSubModuleName(getSubModuleName());
		gcmCompanyDetailsImpl.setCfpDetailsEndDate(getCfpDetailsEndDate());
		gcmCompanyDetailsImpl.setCompanyStatusSid(getCompanyStatusSid());
		gcmCompanyDetailsImpl.setCompanyMasterSid(getCompanyMasterSid());

		gcmCompanyDetailsImpl.resetOriginalValues();

		return gcmCompanyDetailsImpl;
	}

	@Override
	public int compareTo(GcmCompanyDetails gcmCompanyDetails) {
		int primaryKey = gcmCompanyDetails.getPrimaryKey();

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

		if (!(obj instanceof GcmCompanyDetails)) {
			return false;
		}

		GcmCompanyDetails gcmCompanyDetails = (GcmCompanyDetails)obj;

		int primaryKey = gcmCompanyDetails.getPrimaryKey();

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
	public CacheModel<GcmCompanyDetails> toCacheModel() {
		GcmCompanyDetailsCacheModel gcmCompanyDetailsCacheModel = new GcmCompanyDetailsCacheModel();

		gcmCompanyDetailsCacheModel.checkRecord = getCheckRecord();

		gcmCompanyDetailsCacheModel.userId = getUserId();

		gcmCompanyDetailsCacheModel.moduleName = getModuleName();

		String moduleName = gcmCompanyDetailsCacheModel.moduleName;

		if ((moduleName != null) && (moduleName.length() == 0)) {
			gcmCompanyDetailsCacheModel.moduleName = null;
		}

		gcmCompanyDetailsCacheModel.companyStringId = getCompanyStringId();

		String companyStringId = gcmCompanyDetailsCacheModel.companyStringId;

		if ((companyStringId != null) && (companyStringId.length() == 0)) {
			gcmCompanyDetailsCacheModel.companyStringId = null;
		}

		gcmCompanyDetailsCacheModel.cfpDetailsTradeClass = getCfpDetailsTradeClass();

		String cfpDetailsTradeClass = gcmCompanyDetailsCacheModel.cfpDetailsTradeClass;

		if ((cfpDetailsTradeClass != null) &&
				(cfpDetailsTradeClass.length() == 0)) {
			gcmCompanyDetailsCacheModel.cfpDetailsTradeClass = null;
		}

		gcmCompanyDetailsCacheModel.companyName = getCompanyName();

		String companyName = gcmCompanyDetailsCacheModel.companyName;

		if ((companyName != null) && (companyName.length() == 0)) {
			gcmCompanyDetailsCacheModel.companyName = null;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			gcmCompanyDetailsCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			gcmCompanyDetailsCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		gcmCompanyDetailsCacheModel.gcmCompanyDetailsSid = getGcmCompanyDetailsSid();

		gcmCompanyDetailsCacheModel.itemCfpDetailsSid = getItemCfpDetailsSid();

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			gcmCompanyDetailsCacheModel.createdDate = createdDate.getTime();
		}
		else {
			gcmCompanyDetailsCacheModel.createdDate = Long.MIN_VALUE;
		}

		gcmCompanyDetailsCacheModel.createdBy = getCreatedBy();

		Date companyStartDate = getCompanyStartDate();

		if (companyStartDate != null) {
			gcmCompanyDetailsCacheModel.companyStartDate = companyStartDate.getTime();
		}
		else {
			gcmCompanyDetailsCacheModel.companyStartDate = Long.MIN_VALUE;
		}

		gcmCompanyDetailsCacheModel.companyNo = getCompanyNo();

		String companyNo = gcmCompanyDetailsCacheModel.companyNo;

		if ((companyNo != null) && (companyNo.length() == 0)) {
			gcmCompanyDetailsCacheModel.companyNo = null;
		}

		gcmCompanyDetailsCacheModel.companyStatus = getCompanyStatus();

		String companyStatus = gcmCompanyDetailsCacheModel.companyStatus;

		if ((companyStatus != null) && (companyStatus.length() == 0)) {
			gcmCompanyDetailsCacheModel.companyStatus = null;
		}

		gcmCompanyDetailsCacheModel.sessionId = getSessionId();

		String sessionId = gcmCompanyDetailsCacheModel.sessionId;

		if ((sessionId != null) && (sessionId.length() == 0)) {
			gcmCompanyDetailsCacheModel.sessionId = null;
		}

		Date companyEndDate = getCompanyEndDate();

		if (companyEndDate != null) {
			gcmCompanyDetailsCacheModel.companyEndDate = companyEndDate.getTime();
		}
		else {
			gcmCompanyDetailsCacheModel.companyEndDate = Long.MIN_VALUE;
		}

		Date cfpDetailsStartDate = getCfpDetailsStartDate();

		if (cfpDetailsStartDate != null) {
			gcmCompanyDetailsCacheModel.cfpDetailsStartDate = cfpDetailsStartDate.getTime();
		}
		else {
			gcmCompanyDetailsCacheModel.cfpDetailsStartDate = Long.MIN_VALUE;
		}

		gcmCompanyDetailsCacheModel.operation = getOperation();

		String operation = gcmCompanyDetailsCacheModel.operation;

		if ((operation != null) && (operation.length() == 0)) {
			gcmCompanyDetailsCacheModel.operation = null;
		}

		gcmCompanyDetailsCacheModel.cfpModelSid = getCfpModelSid();

		gcmCompanyDetailsCacheModel.modifiedBy = getModifiedBy();

		gcmCompanyDetailsCacheModel.subModuleName = getSubModuleName();

		String subModuleName = gcmCompanyDetailsCacheModel.subModuleName;

		if ((subModuleName != null) && (subModuleName.length() == 0)) {
			gcmCompanyDetailsCacheModel.subModuleName = null;
		}

		Date cfpDetailsEndDate = getCfpDetailsEndDate();

		if (cfpDetailsEndDate != null) {
			gcmCompanyDetailsCacheModel.cfpDetailsEndDate = cfpDetailsEndDate.getTime();
		}
		else {
			gcmCompanyDetailsCacheModel.cfpDetailsEndDate = Long.MIN_VALUE;
		}

		gcmCompanyDetailsCacheModel.companyStatusSid = getCompanyStatusSid();

		gcmCompanyDetailsCacheModel.companyMasterSid = getCompanyMasterSid();

		return gcmCompanyDetailsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{checkRecord=");
		sb.append(getCheckRecord());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", moduleName=");
		sb.append(getModuleName());
		sb.append(", companyStringId=");
		sb.append(getCompanyStringId());
		sb.append(", cfpDetailsTradeClass=");
		sb.append(getCfpDetailsTradeClass());
		sb.append(", companyName=");
		sb.append(getCompanyName());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", gcmCompanyDetailsSid=");
		sb.append(getGcmCompanyDetailsSid());
		sb.append(", itemCfpDetailsSid=");
		sb.append(getItemCfpDetailsSid());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", companyStartDate=");
		sb.append(getCompanyStartDate());
		sb.append(", companyNo=");
		sb.append(getCompanyNo());
		sb.append(", companyStatus=");
		sb.append(getCompanyStatus());
		sb.append(", sessionId=");
		sb.append(getSessionId());
		sb.append(", companyEndDate=");
		sb.append(getCompanyEndDate());
		sb.append(", cfpDetailsStartDate=");
		sb.append(getCfpDetailsStartDate());
		sb.append(", operation=");
		sb.append(getOperation());
		sb.append(", cfpModelSid=");
		sb.append(getCfpModelSid());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", subModuleName=");
		sb.append(getSubModuleName());
		sb.append(", cfpDetailsEndDate=");
		sb.append(getCfpDetailsEndDate());
		sb.append(", companyStatusSid=");
		sb.append(getCompanyStatusSid());
		sb.append(", companyMasterSid=");
		sb.append(getCompanyMasterSid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(76);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.GcmCompanyDetails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>checkRecord</column-name><column-value><![CDATA[");
		sb.append(getCheckRecord());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moduleName</column-name><column-value><![CDATA[");
		sb.append(getModuleName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyStringId</column-name><column-value><![CDATA[");
		sb.append(getCompanyStringId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cfpDetailsTradeClass</column-name><column-value><![CDATA[");
		sb.append(getCfpDetailsTradeClass());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyName</column-name><column-value><![CDATA[");
		sb.append(getCompanyName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gcmCompanyDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getGcmCompanyDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemCfpDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getItemCfpDetailsSid());
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
			"<column><column-name>companyStartDate</column-name><column-value><![CDATA[");
		sb.append(getCompanyStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyNo</column-name><column-value><![CDATA[");
		sb.append(getCompanyNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyStatus</column-name><column-value><![CDATA[");
		sb.append(getCompanyStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionId</column-name><column-value><![CDATA[");
		sb.append(getSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyEndDate</column-name><column-value><![CDATA[");
		sb.append(getCompanyEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cfpDetailsStartDate</column-name><column-value><![CDATA[");
		sb.append(getCfpDetailsStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operation</column-name><column-value><![CDATA[");
		sb.append(getOperation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
		sb.append(getCfpModelSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subModuleName</column-name><column-value><![CDATA[");
		sb.append(getSubModuleName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cfpDetailsEndDate</column-name><column-value><![CDATA[");
		sb.append(getCfpDetailsEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyStatusSid</column-name><column-value><![CDATA[");
		sb.append(getCompanyStatusSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
		sb.append(getCompanyMasterSid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = GcmCompanyDetails.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			GcmCompanyDetails.class
		};
	private boolean _checkRecord;
	private int _userId;
	private String _moduleName;
	private String _companyStringId;
	private String _cfpDetailsTradeClass;
	private String _companyName;
	private Date _modifiedDate;
	private int _gcmCompanyDetailsSid;
	private int _itemCfpDetailsSid;
	private Date _createdDate;
	private int _createdBy;
	private Date _companyStartDate;
	private String _companyNo;
	private String _companyStatus;
	private String _sessionId;
	private Date _companyEndDate;
	private Date _cfpDetailsStartDate;
	private String _operation;
	private int _cfpModelSid;
	private int _modifiedBy;
	private String _subModuleName;
	private Date _cfpDetailsEndDate;
	private int _companyStatusSid;
	private int _companyMasterSid;
	private GcmCompanyDetails _escapedModel;
}