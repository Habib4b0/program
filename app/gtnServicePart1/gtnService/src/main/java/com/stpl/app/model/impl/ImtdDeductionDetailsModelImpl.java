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

import com.stpl.app.model.ImtdDeductionDetails;
import com.stpl.app.model.ImtdDeductionDetailsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ImtdDeductionDetails service. Represents a row in the &quot;IMTD_DEDUCTION_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ImtdDeductionDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ImtdDeductionDetailsImpl}.
 * </p>
 *
 * @author
 * @see ImtdDeductionDetailsImpl
 * @see ImtdDeductionDetails
 * @see ImtdDeductionDetailsModel
 * @generated
 */
@ProviderType
public class ImtdDeductionDetailsModelImpl extends BaseModelImpl<ImtdDeductionDetails>
	implements ImtdDeductionDetailsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a imtd deduction details model instance should use the {@link ImtdDeductionDetails} interface instead.
	 */
	public static final String TABLE_NAME = "IMTD_DEDUCTION_DETAILS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "IMTD_DEDUCTION_DETAILS_SID", Types.INTEGER },
			{ "DEDUCTION_NAME", Types.VARCHAR },
			{ "MODIFIED_BY", Types.INTEGER },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "IMTD_CREATED_DATE", Types.VARCHAR },
			{ "DEDUCTION_DETAILS_SID", Types.INTEGER },
			{ "INDICATOR", Types.VARCHAR },
			{ "CONTRACT_NO", Types.VARCHAR },
			{ "CHECK_RECORD", Types.BOOLEAN },
			{ "DEDUCTION_SUB_TYPE", Types.VARCHAR },
			{ "CDR_MODEL_SID", Types.INTEGER },
			{ "CREATED_BY", Types.INTEGER },
			{ "DEDUCTION_NO", Types.VARCHAR },
			{ "NET_SALES_FORMULA_MASTER_SID", Types.INTEGER },
			{ "USERS_SID", Types.INTEGER },
			{ "CONTRACT_MASTER_SID", Types.INTEGER },
			{ "CONTRACT_NAME", Types.VARCHAR },
			{ "DEDUCTION_CATEGORY", Types.VARCHAR },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "DEDUCTION_TYPE", Types.VARCHAR },
			{ "RECORD_LOCK_STATUS", Types.BOOLEAN },
			{ "OPERATION", Types.VARCHAR },
			{ "SESSION_ID", Types.VARCHAR },
			{ "RS_CONTRACT_SID", Types.INTEGER },
			{ "INBOUND_STATUS", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("IMTD_DEDUCTION_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("DEDUCTION_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("IMTD_CREATED_DATE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("DEDUCTION_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("INDICATOR", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CONTRACT_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CHECK_RECORD", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("DEDUCTION_SUB_TYPE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CDR_MODEL_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("DEDUCTION_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("NET_SALES_FORMULA_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("USERS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CONTRACT_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CONTRACT_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("DEDUCTION_CATEGORY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("DEDUCTION_TYPE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("RECORD_LOCK_STATUS", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("OPERATION", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("SESSION_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("RS_CONTRACT_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("INBOUND_STATUS", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table IMTD_DEDUCTION_DETAILS (IMTD_DEDUCTION_DETAILS_SID INTEGER not null primary key IDENTITY,DEDUCTION_NAME VARCHAR(75) null,MODIFIED_BY INTEGER,CREATED_DATE DATE null,IMTD_CREATED_DATE VARCHAR(75) null,DEDUCTION_DETAILS_SID INTEGER,INDICATOR VARCHAR(75) null,CONTRACT_NO VARCHAR(75) null,CHECK_RECORD BOOLEAN,DEDUCTION_SUB_TYPE VARCHAR(75) null,CDR_MODEL_SID INTEGER,CREATED_BY INTEGER,DEDUCTION_NO VARCHAR(75) null,NET_SALES_FORMULA_MASTER_SID INTEGER,USERS_SID INTEGER,CONTRACT_MASTER_SID INTEGER,CONTRACT_NAME VARCHAR(75) null,DEDUCTION_CATEGORY VARCHAR(75) null,MODIFIED_DATE DATE null,DEDUCTION_TYPE VARCHAR(75) null,RECORD_LOCK_STATUS BOOLEAN,OPERATION VARCHAR(75) null,SESSION_ID VARCHAR(75) null,RS_CONTRACT_SID INTEGER,INBOUND_STATUS VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table IMTD_DEDUCTION_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY imtdDeductionDetails.imtdDeductionDetailsSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY IMTD_DEDUCTION_DETAILS.IMTD_DEDUCTION_DETAILS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.ImtdDeductionDetails"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.ImtdDeductionDetails"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.ImtdDeductionDetails"));

	public ImtdDeductionDetailsModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _imtdDeductionDetailsSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setImtdDeductionDetailsSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _imtdDeductionDetailsSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ImtdDeductionDetails.class;
	}

	@Override
	public String getModelClassName() {
		return ImtdDeductionDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("imtdDeductionDetailsSid", getImtdDeductionDetailsSid());
		attributes.put("deductionName", getDeductionName());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("imtdCreatedDate", getImtdCreatedDate());
		attributes.put("deductionDetailsSid", getDeductionDetailsSid());
		attributes.put("indicator", getIndicator());
		attributes.put("contractNo", getContractNo());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("deductionSubType", getDeductionSubType());
		attributes.put("cdrModelSid", getCdrModelSid());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("deductionNo", getDeductionNo());
		attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
		attributes.put("usersSid", getUsersSid());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("contractName", getContractName());
		attributes.put("deductionCategory", getDeductionCategory());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("deductionType", getDeductionType());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("operation", getOperation());
		attributes.put("sessionId", getSessionId());
		attributes.put("rsContractSid", getRsContractSid());
		attributes.put("inboundStatus", getInboundStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer imtdDeductionDetailsSid = (Integer)attributes.get(
				"imtdDeductionDetailsSid");

		if (imtdDeductionDetailsSid != null) {
			setImtdDeductionDetailsSid(imtdDeductionDetailsSid);
		}

		String deductionName = (String)attributes.get("deductionName");

		if (deductionName != null) {
			setDeductionName(deductionName);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String imtdCreatedDate = (String)attributes.get("imtdCreatedDate");

		if (imtdCreatedDate != null) {
			setImtdCreatedDate(imtdCreatedDate);
		}

		Integer deductionDetailsSid = (Integer)attributes.get(
				"deductionDetailsSid");

		if (deductionDetailsSid != null) {
			setDeductionDetailsSid(deductionDetailsSid);
		}

		String indicator = (String)attributes.get("indicator");

		if (indicator != null) {
			setIndicator(indicator);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String deductionSubType = (String)attributes.get("deductionSubType");

		if (deductionSubType != null) {
			setDeductionSubType(deductionSubType);
		}

		Integer cdrModelSid = (Integer)attributes.get("cdrModelSid");

		if (cdrModelSid != null) {
			setCdrModelSid(cdrModelSid);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String deductionNo = (String)attributes.get("deductionNo");

		if (deductionNo != null) {
			setDeductionNo(deductionNo);
		}

		Integer netSalesFormulaMasterSid = (Integer)attributes.get(
				"netSalesFormulaMasterSid");

		if (netSalesFormulaMasterSid != null) {
			setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		}

		Integer usersSid = (Integer)attributes.get("usersSid");

		if (usersSid != null) {
			setUsersSid(usersSid);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		String contractName = (String)attributes.get("contractName");

		if (contractName != null) {
			setContractName(contractName);
		}

		String deductionCategory = (String)attributes.get("deductionCategory");

		if (deductionCategory != null) {
			setDeductionCategory(deductionCategory);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String deductionType = (String)attributes.get("deductionType");

		if (deductionType != null) {
			setDeductionType(deductionType);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String operation = (String)attributes.get("operation");

		if (operation != null) {
			setOperation(operation);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer rsContractSid = (Integer)attributes.get("rsContractSid");

		if (rsContractSid != null) {
			setRsContractSid(rsContractSid);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public int getImtdDeductionDetailsSid() {
		return _imtdDeductionDetailsSid;
	}

	@Override
	public void setImtdDeductionDetailsSid(int imtdDeductionDetailsSid) {
		_imtdDeductionDetailsSid = imtdDeductionDetailsSid;
	}

	@Override
	public String getDeductionName() {
		if (_deductionName == null) {
			return StringPool.BLANK;
		}
		else {
			return _deductionName;
		}
	}

	@Override
	public void setDeductionName(String deductionName) {
		_deductionName = deductionName;
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
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	@Override
	public String getImtdCreatedDate() {
		if (_imtdCreatedDate == null) {
			return StringPool.BLANK;
		}
		else {
			return _imtdCreatedDate;
		}
	}

	@Override
	public void setImtdCreatedDate(String imtdCreatedDate) {
		_imtdCreatedDate = imtdCreatedDate;
	}

	@Override
	public int getDeductionDetailsSid() {
		return _deductionDetailsSid;
	}

	@Override
	public void setDeductionDetailsSid(int deductionDetailsSid) {
		_deductionDetailsSid = deductionDetailsSid;
	}

	@Override
	public String getIndicator() {
		if (_indicator == null) {
			return StringPool.BLANK;
		}
		else {
			return _indicator;
		}
	}

	@Override
	public void setIndicator(String indicator) {
		_indicator = indicator;
	}

	@Override
	public String getContractNo() {
		if (_contractNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _contractNo;
		}
	}

	@Override
	public void setContractNo(String contractNo) {
		_contractNo = contractNo;
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
	public String getDeductionSubType() {
		if (_deductionSubType == null) {
			return StringPool.BLANK;
		}
		else {
			return _deductionSubType;
		}
	}

	@Override
	public void setDeductionSubType(String deductionSubType) {
		_deductionSubType = deductionSubType;
	}

	@Override
	public int getCdrModelSid() {
		return _cdrModelSid;
	}

	@Override
	public void setCdrModelSid(int cdrModelSid) {
		_cdrModelSid = cdrModelSid;
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
	public String getDeductionNo() {
		if (_deductionNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _deductionNo;
		}
	}

	@Override
	public void setDeductionNo(String deductionNo) {
		_deductionNo = deductionNo;
	}

	@Override
	public int getNetSalesFormulaMasterSid() {
		return _netSalesFormulaMasterSid;
	}

	@Override
	public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
		_netSalesFormulaMasterSid = netSalesFormulaMasterSid;
	}

	@Override
	public int getUsersSid() {
		return _usersSid;
	}

	@Override
	public void setUsersSid(int usersSid) {
		_usersSid = usersSid;
	}

	@Override
	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	@Override
	public String getContractName() {
		if (_contractName == null) {
			return StringPool.BLANK;
		}
		else {
			return _contractName;
		}
	}

	@Override
	public void setContractName(String contractName) {
		_contractName = contractName;
	}

	@Override
	public String getDeductionCategory() {
		if (_deductionCategory == null) {
			return StringPool.BLANK;
		}
		else {
			return _deductionCategory;
		}
	}

	@Override
	public void setDeductionCategory(String deductionCategory) {
		_deductionCategory = deductionCategory;
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
	public String getDeductionType() {
		if (_deductionType == null) {
			return StringPool.BLANK;
		}
		else {
			return _deductionType;
		}
	}

	@Override
	public void setDeductionType(String deductionType) {
		_deductionType = deductionType;
	}

	@Override
	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	@Override
	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
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
	public int getRsContractSid() {
		return _rsContractSid;
	}

	@Override
	public void setRsContractSid(int rsContractSid) {
		_rsContractSid = rsContractSid;
	}

	@Override
	public String getInboundStatus() {
		if (_inboundStatus == null) {
			return StringPool.BLANK;
		}
		else {
			return _inboundStatus;
		}
	}

	@Override
	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	@Override
	public ImtdDeductionDetails toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ImtdDeductionDetails)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ImtdDeductionDetailsImpl imtdDeductionDetailsImpl = new ImtdDeductionDetailsImpl();

		imtdDeductionDetailsImpl.setImtdDeductionDetailsSid(getImtdDeductionDetailsSid());
		imtdDeductionDetailsImpl.setDeductionName(getDeductionName());
		imtdDeductionDetailsImpl.setModifiedBy(getModifiedBy());
		imtdDeductionDetailsImpl.setCreatedDate(getCreatedDate());
		imtdDeductionDetailsImpl.setImtdCreatedDate(getImtdCreatedDate());
		imtdDeductionDetailsImpl.setDeductionDetailsSid(getDeductionDetailsSid());
		imtdDeductionDetailsImpl.setIndicator(getIndicator());
		imtdDeductionDetailsImpl.setContractNo(getContractNo());
		imtdDeductionDetailsImpl.setCheckRecord(getCheckRecord());
		imtdDeductionDetailsImpl.setDeductionSubType(getDeductionSubType());
		imtdDeductionDetailsImpl.setCdrModelSid(getCdrModelSid());
		imtdDeductionDetailsImpl.setCreatedBy(getCreatedBy());
		imtdDeductionDetailsImpl.setDeductionNo(getDeductionNo());
		imtdDeductionDetailsImpl.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
		imtdDeductionDetailsImpl.setUsersSid(getUsersSid());
		imtdDeductionDetailsImpl.setContractMasterSid(getContractMasterSid());
		imtdDeductionDetailsImpl.setContractName(getContractName());
		imtdDeductionDetailsImpl.setDeductionCategory(getDeductionCategory());
		imtdDeductionDetailsImpl.setModifiedDate(getModifiedDate());
		imtdDeductionDetailsImpl.setDeductionType(getDeductionType());
		imtdDeductionDetailsImpl.setRecordLockStatus(getRecordLockStatus());
		imtdDeductionDetailsImpl.setOperation(getOperation());
		imtdDeductionDetailsImpl.setSessionId(getSessionId());
		imtdDeductionDetailsImpl.setRsContractSid(getRsContractSid());
		imtdDeductionDetailsImpl.setInboundStatus(getInboundStatus());

		imtdDeductionDetailsImpl.resetOriginalValues();

		return imtdDeductionDetailsImpl;
	}

	@Override
	public int compareTo(ImtdDeductionDetails imtdDeductionDetails) {
		int primaryKey = imtdDeductionDetails.getPrimaryKey();

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

		if (!(obj instanceof ImtdDeductionDetails)) {
			return false;
		}

		ImtdDeductionDetails imtdDeductionDetails = (ImtdDeductionDetails)obj;

		int primaryKey = imtdDeductionDetails.getPrimaryKey();

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
	public CacheModel<ImtdDeductionDetails> toCacheModel() {
		ImtdDeductionDetailsCacheModel imtdDeductionDetailsCacheModel = new ImtdDeductionDetailsCacheModel();

		imtdDeductionDetailsCacheModel.imtdDeductionDetailsSid = getImtdDeductionDetailsSid();

		imtdDeductionDetailsCacheModel.deductionName = getDeductionName();

		String deductionName = imtdDeductionDetailsCacheModel.deductionName;

		if ((deductionName != null) && (deductionName.length() == 0)) {
			imtdDeductionDetailsCacheModel.deductionName = null;
		}

		imtdDeductionDetailsCacheModel.modifiedBy = getModifiedBy();

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			imtdDeductionDetailsCacheModel.createdDate = createdDate.getTime();
		}
		else {
			imtdDeductionDetailsCacheModel.createdDate = Long.MIN_VALUE;
		}

		imtdDeductionDetailsCacheModel.imtdCreatedDate = getImtdCreatedDate();

		String imtdCreatedDate = imtdDeductionDetailsCacheModel.imtdCreatedDate;

		if ((imtdCreatedDate != null) && (imtdCreatedDate.length() == 0)) {
			imtdDeductionDetailsCacheModel.imtdCreatedDate = null;
		}

		imtdDeductionDetailsCacheModel.deductionDetailsSid = getDeductionDetailsSid();

		imtdDeductionDetailsCacheModel.indicator = getIndicator();

		String indicator = imtdDeductionDetailsCacheModel.indicator;

		if ((indicator != null) && (indicator.length() == 0)) {
			imtdDeductionDetailsCacheModel.indicator = null;
		}

		imtdDeductionDetailsCacheModel.contractNo = getContractNo();

		String contractNo = imtdDeductionDetailsCacheModel.contractNo;

		if ((contractNo != null) && (contractNo.length() == 0)) {
			imtdDeductionDetailsCacheModel.contractNo = null;
		}

		imtdDeductionDetailsCacheModel.checkRecord = getCheckRecord();

		imtdDeductionDetailsCacheModel.deductionSubType = getDeductionSubType();

		String deductionSubType = imtdDeductionDetailsCacheModel.deductionSubType;

		if ((deductionSubType != null) && (deductionSubType.length() == 0)) {
			imtdDeductionDetailsCacheModel.deductionSubType = null;
		}

		imtdDeductionDetailsCacheModel.cdrModelSid = getCdrModelSid();

		imtdDeductionDetailsCacheModel.createdBy = getCreatedBy();

		imtdDeductionDetailsCacheModel.deductionNo = getDeductionNo();

		String deductionNo = imtdDeductionDetailsCacheModel.deductionNo;

		if ((deductionNo != null) && (deductionNo.length() == 0)) {
			imtdDeductionDetailsCacheModel.deductionNo = null;
		}

		imtdDeductionDetailsCacheModel.netSalesFormulaMasterSid = getNetSalesFormulaMasterSid();

		imtdDeductionDetailsCacheModel.usersSid = getUsersSid();

		imtdDeductionDetailsCacheModel.contractMasterSid = getContractMasterSid();

		imtdDeductionDetailsCacheModel.contractName = getContractName();

		String contractName = imtdDeductionDetailsCacheModel.contractName;

		if ((contractName != null) && (contractName.length() == 0)) {
			imtdDeductionDetailsCacheModel.contractName = null;
		}

		imtdDeductionDetailsCacheModel.deductionCategory = getDeductionCategory();

		String deductionCategory = imtdDeductionDetailsCacheModel.deductionCategory;

		if ((deductionCategory != null) && (deductionCategory.length() == 0)) {
			imtdDeductionDetailsCacheModel.deductionCategory = null;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			imtdDeductionDetailsCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			imtdDeductionDetailsCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		imtdDeductionDetailsCacheModel.deductionType = getDeductionType();

		String deductionType = imtdDeductionDetailsCacheModel.deductionType;

		if ((deductionType != null) && (deductionType.length() == 0)) {
			imtdDeductionDetailsCacheModel.deductionType = null;
		}

		imtdDeductionDetailsCacheModel.recordLockStatus = getRecordLockStatus();

		imtdDeductionDetailsCacheModel.operation = getOperation();

		String operation = imtdDeductionDetailsCacheModel.operation;

		if ((operation != null) && (operation.length() == 0)) {
			imtdDeductionDetailsCacheModel.operation = null;
		}

		imtdDeductionDetailsCacheModel.sessionId = getSessionId();

		String sessionId = imtdDeductionDetailsCacheModel.sessionId;

		if ((sessionId != null) && (sessionId.length() == 0)) {
			imtdDeductionDetailsCacheModel.sessionId = null;
		}

		imtdDeductionDetailsCacheModel.rsContractSid = getRsContractSid();

		imtdDeductionDetailsCacheModel.inboundStatus = getInboundStatus();

		String inboundStatus = imtdDeductionDetailsCacheModel.inboundStatus;

		if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
			imtdDeductionDetailsCacheModel.inboundStatus = null;
		}

		return imtdDeductionDetailsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{imtdDeductionDetailsSid=");
		sb.append(getImtdDeductionDetailsSid());
		sb.append(", deductionName=");
		sb.append(getDeductionName());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", imtdCreatedDate=");
		sb.append(getImtdCreatedDate());
		sb.append(", deductionDetailsSid=");
		sb.append(getDeductionDetailsSid());
		sb.append(", indicator=");
		sb.append(getIndicator());
		sb.append(", contractNo=");
		sb.append(getContractNo());
		sb.append(", checkRecord=");
		sb.append(getCheckRecord());
		sb.append(", deductionSubType=");
		sb.append(getDeductionSubType());
		sb.append(", cdrModelSid=");
		sb.append(getCdrModelSid());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", deductionNo=");
		sb.append(getDeductionNo());
		sb.append(", netSalesFormulaMasterSid=");
		sb.append(getNetSalesFormulaMasterSid());
		sb.append(", usersSid=");
		sb.append(getUsersSid());
		sb.append(", contractMasterSid=");
		sb.append(getContractMasterSid());
		sb.append(", contractName=");
		sb.append(getContractName());
		sb.append(", deductionCategory=");
		sb.append(getDeductionCategory());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", deductionType=");
		sb.append(getDeductionType());
		sb.append(", recordLockStatus=");
		sb.append(getRecordLockStatus());
		sb.append(", operation=");
		sb.append(getOperation());
		sb.append(", sessionId=");
		sb.append(getSessionId());
		sb.append(", rsContractSid=");
		sb.append(getRsContractSid());
		sb.append(", inboundStatus=");
		sb.append(getInboundStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(79);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.ImtdDeductionDetails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>imtdDeductionDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getImtdDeductionDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deductionName</column-name><column-value><![CDATA[");
		sb.append(getDeductionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdDate</column-name><column-value><![CDATA[");
		sb.append(getCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imtdCreatedDate</column-name><column-value><![CDATA[");
		sb.append(getImtdCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deductionDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getDeductionDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>indicator</column-name><column-value><![CDATA[");
		sb.append(getIndicator());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contractNo</column-name><column-value><![CDATA[");
		sb.append(getContractNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>checkRecord</column-name><column-value><![CDATA[");
		sb.append(getCheckRecord());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deductionSubType</column-name><column-value><![CDATA[");
		sb.append(getDeductionSubType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
		sb.append(getCdrModelSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deductionNo</column-name><column-value><![CDATA[");
		sb.append(getDeductionNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
		sb.append(getNetSalesFormulaMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>usersSid</column-name><column-value><![CDATA[");
		sb.append(getUsersSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
		sb.append(getContractMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contractName</column-name><column-value><![CDATA[");
		sb.append(getContractName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deductionCategory</column-name><column-value><![CDATA[");
		sb.append(getDeductionCategory());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deductionType</column-name><column-value><![CDATA[");
		sb.append(getDeductionType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
		sb.append(getRecordLockStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operation</column-name><column-value><![CDATA[");
		sb.append(getOperation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionId</column-name><column-value><![CDATA[");
		sb.append(getSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rsContractSid</column-name><column-value><![CDATA[");
		sb.append(getRsContractSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
		sb.append(getInboundStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ImtdDeductionDetails.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ImtdDeductionDetails.class
		};
	private int _imtdDeductionDetailsSid;
	private String _deductionName;
	private int _modifiedBy;
	private Date _createdDate;
	private String _imtdCreatedDate;
	private int _deductionDetailsSid;
	private String _indicator;
	private String _contractNo;
	private boolean _checkRecord;
	private String _deductionSubType;
	private int _cdrModelSid;
	private int _createdBy;
	private String _deductionNo;
	private int _netSalesFormulaMasterSid;
	private int _usersSid;
	private int _contractMasterSid;
	private String _contractName;
	private String _deductionCategory;
	private Date _modifiedDate;
	private String _deductionType;
	private boolean _recordLockStatus;
	private String _operation;
	private String _sessionId;
	private int _rsContractSid;
	private String _inboundStatus;
	private ImtdDeductionDetails _escapedModel;
}