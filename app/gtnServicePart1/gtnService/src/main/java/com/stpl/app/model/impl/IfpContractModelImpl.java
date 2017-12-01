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

import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpContractModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the IfpContract service. Represents a row in the &quot;IFP_CONTRACT&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link IfpContractModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IfpContractImpl}.
 * </p>
 *
 * @author
 * @see IfpContractImpl
 * @see IfpContract
 * @see IfpContractModel
 * @generated
 */
@ProviderType
public class IfpContractModelImpl extends BaseModelImpl<IfpContract>
	implements IfpContractModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ifp contract model instance should use the {@link IfpContract} interface instead.
	 */
	public static final String TABLE_NAME = "IFP_CONTRACT";
	public static final Object[][] TABLE_COLUMNS = {
			{ "CFP_CONTRACT_SID", Types.VARCHAR },
			{ "PARENT_IFP_NAME", Types.VARCHAR },
			{ "IFP_CONTRACT_ATTACHED_DATE", Types.TIMESTAMP },
			{ "IFP_STATUS", Types.INTEGER },
			{ "IFP_START_DATE", Types.TIMESTAMP },
			{ "IFP_CONTRACT_ATTACHED_STATUS", Types.INTEGER },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "IFP_CATEGORY", Types.INTEGER },
			{ "RECORD_LOCK_STATUS", Types.BOOLEAN },
			{ "IFP_END_DATE", Types.TIMESTAMP },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "CREATED_BY", Types.INTEGER },
			{ "SOURCE", Types.VARCHAR },
			{ "IFP_DESIGNATION", Types.VARCHAR },
			{ "PARENT_IFP_ID", Types.VARCHAR },
			{ "BATCH_ID", Types.VARCHAR },
			{ "CONTRACT_MASTER_SID", Types.INTEGER },
			{ "IFP_TYPE", Types.INTEGER },
			{ "IFP_NAME", Types.VARCHAR },
			{ "IFP_NO", Types.VARCHAR },
			{ "MODIFIED_BY", Types.INTEGER },
			{ "INBOUND_STATUS", Types.VARCHAR },
			{ "IFP_CONTRACT_SID", Types.INTEGER },
			{ "IFP_MODEL_SID", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CFP_CONTRACT_SID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("PARENT_IFP_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_CONTRACT_ATTACHED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("IFP_STATUS", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("IFP_START_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("IFP_CONTRACT_ATTACHED_STATUS", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("IFP_CATEGORY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("RECORD_LOCK_STATUS", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("IFP_END_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("SOURCE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_DESIGNATION", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("PARENT_IFP_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("BATCH_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CONTRACT_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("IFP_TYPE", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("IFP_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("INBOUND_STATUS", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_CONTRACT_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("IFP_MODEL_SID", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table IFP_CONTRACT (CFP_CONTRACT_SID VARCHAR(75) null,PARENT_IFP_NAME VARCHAR(75) null,IFP_CONTRACT_ATTACHED_DATE DATE null,IFP_STATUS INTEGER,IFP_START_DATE DATE null,IFP_CONTRACT_ATTACHED_STATUS INTEGER,MODIFIED_DATE DATE null,IFP_CATEGORY INTEGER,RECORD_LOCK_STATUS BOOLEAN,IFP_END_DATE DATE null,CREATED_DATE DATE null,CREATED_BY INTEGER,SOURCE VARCHAR(75) null,IFP_DESIGNATION VARCHAR(75) null,PARENT_IFP_ID VARCHAR(75) null,BATCH_ID VARCHAR(75) null,CONTRACT_MASTER_SID INTEGER,IFP_TYPE INTEGER,IFP_NAME VARCHAR(75) null,IFP_NO VARCHAR(75) null,MODIFIED_BY INTEGER,INBOUND_STATUS VARCHAR(75) null,IFP_CONTRACT_SID INTEGER not null primary key IDENTITY,IFP_MODEL_SID INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table IFP_CONTRACT";
	public static final String ORDER_BY_JPQL = " ORDER BY ifpContract.ifpContractSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY IFP_CONTRACT.IFP_CONTRACT_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.IfpContract"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.IfpContract"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.IfpContract"));

	public IfpContractModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _ifpContractSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setIfpContractSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ifpContractSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return IfpContract.class;
	}

	@Override
	public String getModelClassName() {
		return IfpContract.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cfpContractSid", getCfpContractSid());
		attributes.put("parentIfpName", getParentIfpName());
		attributes.put("ifpContractAttachedDate", getIfpContractAttachedDate());
		attributes.put("ifpStatus", getIfpStatus());
		attributes.put("ifpStartDate", getIfpStartDate());
		attributes.put("ifpContractAttachedStatus",
			getIfpContractAttachedStatus());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ifpCategory", getIfpCategory());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("ifpEndDate", getIfpEndDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("ifpDesignation", getIfpDesignation());
		attributes.put("parentIfpId", getParentIfpId());
		attributes.put("batchId", getBatchId());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("ifpType", getIfpType());
		attributes.put("ifpName", getIfpName());
		attributes.put("ifpNo", getIfpNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("ifpContractSid", getIfpContractSid());
		attributes.put("ifpModelSid", getIfpModelSid());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String cfpContractSid = (String)attributes.get("cfpContractSid");

		if (cfpContractSid != null) {
			setCfpContractSid(cfpContractSid);
		}

		String parentIfpName = (String)attributes.get("parentIfpName");

		if (parentIfpName != null) {
			setParentIfpName(parentIfpName);
		}

		Date ifpContractAttachedDate = (Date)attributes.get(
				"ifpContractAttachedDate");

		if (ifpContractAttachedDate != null) {
			setIfpContractAttachedDate(ifpContractAttachedDate);
		}

		Integer ifpStatus = (Integer)attributes.get("ifpStatus");

		if (ifpStatus != null) {
			setIfpStatus(ifpStatus);
		}

		Date ifpStartDate = (Date)attributes.get("ifpStartDate");

		if (ifpStartDate != null) {
			setIfpStartDate(ifpStartDate);
		}

		Integer ifpContractAttachedStatus = (Integer)attributes.get(
				"ifpContractAttachedStatus");

		if (ifpContractAttachedStatus != null) {
			setIfpContractAttachedStatus(ifpContractAttachedStatus);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer ifpCategory = (Integer)attributes.get("ifpCategory");

		if (ifpCategory != null) {
			setIfpCategory(ifpCategory);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date ifpEndDate = (Date)attributes.get("ifpEndDate");

		if (ifpEndDate != null) {
			setIfpEndDate(ifpEndDate);
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

		String ifpDesignation = (String)attributes.get("ifpDesignation");

		if (ifpDesignation != null) {
			setIfpDesignation(ifpDesignation);
		}

		String parentIfpId = (String)attributes.get("parentIfpId");

		if (parentIfpId != null) {
			setParentIfpId(parentIfpId);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer ifpType = (Integer)attributes.get("ifpType");

		if (ifpType != null) {
			setIfpType(ifpType);
		}

		String ifpName = (String)attributes.get("ifpName");

		if (ifpName != null) {
			setIfpName(ifpName);
		}

		String ifpNo = (String)attributes.get("ifpNo");

		if (ifpNo != null) {
			setIfpNo(ifpNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer ifpContractSid = (Integer)attributes.get("ifpContractSid");

		if (ifpContractSid != null) {
			setIfpContractSid(ifpContractSid);
		}

		Integer ifpModelSid = (Integer)attributes.get("ifpModelSid");

		if (ifpModelSid != null) {
			setIfpModelSid(ifpModelSid);
		}
	}

	@Override
	public String getCfpContractSid() {
		return _cfpContractSid;
	}

	@Override
	public void setCfpContractSid(String cfpContractSid) {
		_cfpContractSid = cfpContractSid;
	}

	@Override
	public String getParentIfpName() {
		if (_parentIfpName == null) {
			return StringPool.BLANK;
		}
		else {
			return _parentIfpName;
		}
	}

	@Override
	public void setParentIfpName(String parentIfpName) {
		_parentIfpName = parentIfpName;
	}

	@Override
	public Date getIfpContractAttachedDate() {
		return _ifpContractAttachedDate;
	}

	@Override
	public void setIfpContractAttachedDate(Date ifpContractAttachedDate) {
		_ifpContractAttachedDate = ifpContractAttachedDate;
	}

	@Override
	public int getIfpStatus() {
		return _ifpStatus;
	}

	@Override
	public void setIfpStatus(int ifpStatus) {
		_ifpStatus = ifpStatus;
	}

	@Override
	public Date getIfpStartDate() {
		return _ifpStartDate;
	}

	@Override
	public void setIfpStartDate(Date ifpStartDate) {
		_ifpStartDate = ifpStartDate;
	}

	@Override
	public int getIfpContractAttachedStatus() {
		return _ifpContractAttachedStatus;
	}

	@Override
	public void setIfpContractAttachedStatus(int ifpContractAttachedStatus) {
		_ifpContractAttachedStatus = ifpContractAttachedStatus;
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
	public int getIfpCategory() {
		return _ifpCategory;
	}

	@Override
	public void setIfpCategory(int ifpCategory) {
		_ifpCategory = ifpCategory;
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
	public Date getIfpEndDate() {
		return _ifpEndDate;
	}

	@Override
	public void setIfpEndDate(Date ifpEndDate) {
		_ifpEndDate = ifpEndDate;
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
	public String getSource() {
		if (_source == null) {
			return StringPool.BLANK;
		}
		else {
			return _source;
		}
	}

	@Override
	public void setSource(String source) {
		_source = source;
	}

	@Override
	public String getIfpDesignation() {
		if (_ifpDesignation == null) {
			return StringPool.BLANK;
		}
		else {
			return _ifpDesignation;
		}
	}

	@Override
	public void setIfpDesignation(String ifpDesignation) {
		_ifpDesignation = ifpDesignation;
	}

	@Override
	public String getParentIfpId() {
		if (_parentIfpId == null) {
			return StringPool.BLANK;
		}
		else {
			return _parentIfpId;
		}
	}

	@Override
	public void setParentIfpId(String parentIfpId) {
		_parentIfpId = parentIfpId;
	}

	@Override
	public String getBatchId() {
		if (_batchId == null) {
			return StringPool.BLANK;
		}
		else {
			return _batchId;
		}
	}

	@Override
	public void setBatchId(String batchId) {
		_batchId = batchId;
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
	public int getIfpType() {
		return _ifpType;
	}

	@Override
	public void setIfpType(int ifpType) {
		_ifpType = ifpType;
	}

	@Override
	public String getIfpName() {
		if (_ifpName == null) {
			return StringPool.BLANK;
		}
		else {
			return _ifpName;
		}
	}

	@Override
	public void setIfpName(String ifpName) {
		_ifpName = ifpName;
	}

	@Override
	public String getIfpNo() {
		if (_ifpNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _ifpNo;
		}
	}

	@Override
	public void setIfpNo(String ifpNo) {
		_ifpNo = ifpNo;
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
	public int getIfpContractSid() {
		return _ifpContractSid;
	}

	@Override
	public void setIfpContractSid(int ifpContractSid) {
		_ifpContractSid = ifpContractSid;
	}

	@Override
	public int getIfpModelSid() {
		return _ifpModelSid;
	}

	@Override
	public void setIfpModelSid(int ifpModelSid) {
		_ifpModelSid = ifpModelSid;
	}

	@Override
	public IfpContract toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (IfpContract)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		IfpContractImpl ifpContractImpl = new IfpContractImpl();

		ifpContractImpl.setCfpContractSid(getCfpContractSid());
		ifpContractImpl.setParentIfpName(getParentIfpName());
		ifpContractImpl.setIfpContractAttachedDate(getIfpContractAttachedDate());
		ifpContractImpl.setIfpStatus(getIfpStatus());
		ifpContractImpl.setIfpStartDate(getIfpStartDate());
		ifpContractImpl.setIfpContractAttachedStatus(getIfpContractAttachedStatus());
		ifpContractImpl.setModifiedDate(getModifiedDate());
		ifpContractImpl.setIfpCategory(getIfpCategory());
		ifpContractImpl.setRecordLockStatus(getRecordLockStatus());
		ifpContractImpl.setIfpEndDate(getIfpEndDate());
		ifpContractImpl.setCreatedDate(getCreatedDate());
		ifpContractImpl.setCreatedBy(getCreatedBy());
		ifpContractImpl.setSource(getSource());
		ifpContractImpl.setIfpDesignation(getIfpDesignation());
		ifpContractImpl.setParentIfpId(getParentIfpId());
		ifpContractImpl.setBatchId(getBatchId());
		ifpContractImpl.setContractMasterSid(getContractMasterSid());
		ifpContractImpl.setIfpType(getIfpType());
		ifpContractImpl.setIfpName(getIfpName());
		ifpContractImpl.setIfpNo(getIfpNo());
		ifpContractImpl.setModifiedBy(getModifiedBy());
		ifpContractImpl.setInboundStatus(getInboundStatus());
		ifpContractImpl.setIfpContractSid(getIfpContractSid());
		ifpContractImpl.setIfpModelSid(getIfpModelSid());

		ifpContractImpl.resetOriginalValues();

		return ifpContractImpl;
	}

	@Override
	public int compareTo(IfpContract ifpContract) {
		int primaryKey = ifpContract.getPrimaryKey();

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

		if (!(obj instanceof IfpContract)) {
			return false;
		}

		IfpContract ifpContract = (IfpContract)obj;

		int primaryKey = ifpContract.getPrimaryKey();

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
	public CacheModel<IfpContract> toCacheModel() {
		IfpContractCacheModel ifpContractCacheModel = new IfpContractCacheModel();

		ifpContractCacheModel.cfpContractSid = getCfpContractSid();

		String cfpContractSid = ifpContractCacheModel.cfpContractSid;

		if ((cfpContractSid != null) && (cfpContractSid.length() == 0)) {
			ifpContractCacheModel.cfpContractSid = null;
		}

		ifpContractCacheModel.parentIfpName = getParentIfpName();

		String parentIfpName = ifpContractCacheModel.parentIfpName;

		if ((parentIfpName != null) && (parentIfpName.length() == 0)) {
			ifpContractCacheModel.parentIfpName = null;
		}

		Date ifpContractAttachedDate = getIfpContractAttachedDate();

		if (ifpContractAttachedDate != null) {
			ifpContractCacheModel.ifpContractAttachedDate = ifpContractAttachedDate.getTime();
		}
		else {
			ifpContractCacheModel.ifpContractAttachedDate = Long.MIN_VALUE;
		}

		ifpContractCacheModel.ifpStatus = getIfpStatus();

		Date ifpStartDate = getIfpStartDate();

		if (ifpStartDate != null) {
			ifpContractCacheModel.ifpStartDate = ifpStartDate.getTime();
		}
		else {
			ifpContractCacheModel.ifpStartDate = Long.MIN_VALUE;
		}

		ifpContractCacheModel.ifpContractAttachedStatus = getIfpContractAttachedStatus();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ifpContractCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			ifpContractCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ifpContractCacheModel.ifpCategory = getIfpCategory();

		ifpContractCacheModel.recordLockStatus = getRecordLockStatus();

		Date ifpEndDate = getIfpEndDate();

		if (ifpEndDate != null) {
			ifpContractCacheModel.ifpEndDate = ifpEndDate.getTime();
		}
		else {
			ifpContractCacheModel.ifpEndDate = Long.MIN_VALUE;
		}

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			ifpContractCacheModel.createdDate = createdDate.getTime();
		}
		else {
			ifpContractCacheModel.createdDate = Long.MIN_VALUE;
		}

		ifpContractCacheModel.createdBy = getCreatedBy();

		ifpContractCacheModel.source = getSource();

		String source = ifpContractCacheModel.source;

		if ((source != null) && (source.length() == 0)) {
			ifpContractCacheModel.source = null;
		}

		ifpContractCacheModel.ifpDesignation = getIfpDesignation();

		String ifpDesignation = ifpContractCacheModel.ifpDesignation;

		if ((ifpDesignation != null) && (ifpDesignation.length() == 0)) {
			ifpContractCacheModel.ifpDesignation = null;
		}

		ifpContractCacheModel.parentIfpId = getParentIfpId();

		String parentIfpId = ifpContractCacheModel.parentIfpId;

		if ((parentIfpId != null) && (parentIfpId.length() == 0)) {
			ifpContractCacheModel.parentIfpId = null;
		}

		ifpContractCacheModel.batchId = getBatchId();

		String batchId = ifpContractCacheModel.batchId;

		if ((batchId != null) && (batchId.length() == 0)) {
			ifpContractCacheModel.batchId = null;
		}

		ifpContractCacheModel.contractMasterSid = getContractMasterSid();

		ifpContractCacheModel.ifpType = getIfpType();

		ifpContractCacheModel.ifpName = getIfpName();

		String ifpName = ifpContractCacheModel.ifpName;

		if ((ifpName != null) && (ifpName.length() == 0)) {
			ifpContractCacheModel.ifpName = null;
		}

		ifpContractCacheModel.ifpNo = getIfpNo();

		String ifpNo = ifpContractCacheModel.ifpNo;

		if ((ifpNo != null) && (ifpNo.length() == 0)) {
			ifpContractCacheModel.ifpNo = null;
		}

		ifpContractCacheModel.modifiedBy = getModifiedBy();

		ifpContractCacheModel.inboundStatus = getInboundStatus();

		String inboundStatus = ifpContractCacheModel.inboundStatus;

		if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
			ifpContractCacheModel.inboundStatus = null;
		}

		ifpContractCacheModel.ifpContractSid = getIfpContractSid();

		ifpContractCacheModel.ifpModelSid = getIfpModelSid();

		return ifpContractCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{cfpContractSid=");
		sb.append(getCfpContractSid());
		sb.append(", parentIfpName=");
		sb.append(getParentIfpName());
		sb.append(", ifpContractAttachedDate=");
		sb.append(getIfpContractAttachedDate());
		sb.append(", ifpStatus=");
		sb.append(getIfpStatus());
		sb.append(", ifpStartDate=");
		sb.append(getIfpStartDate());
		sb.append(", ifpContractAttachedStatus=");
		sb.append(getIfpContractAttachedStatus());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", ifpCategory=");
		sb.append(getIfpCategory());
		sb.append(", recordLockStatus=");
		sb.append(getRecordLockStatus());
		sb.append(", ifpEndDate=");
		sb.append(getIfpEndDate());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", source=");
		sb.append(getSource());
		sb.append(", ifpDesignation=");
		sb.append(getIfpDesignation());
		sb.append(", parentIfpId=");
		sb.append(getParentIfpId());
		sb.append(", batchId=");
		sb.append(getBatchId());
		sb.append(", contractMasterSid=");
		sb.append(getContractMasterSid());
		sb.append(", ifpType=");
		sb.append(getIfpType());
		sb.append(", ifpName=");
		sb.append(getIfpName());
		sb.append(", ifpNo=");
		sb.append(getIfpNo());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", inboundStatus=");
		sb.append(getInboundStatus());
		sb.append(", ifpContractSid=");
		sb.append(getIfpContractSid());
		sb.append(", ifpModelSid=");
		sb.append(getIfpModelSid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(76);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.IfpContract");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>cfpContractSid</column-name><column-value><![CDATA[");
		sb.append(getCfpContractSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentIfpName</column-name><column-value><![CDATA[");
		sb.append(getParentIfpName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpContractAttachedDate</column-name><column-value><![CDATA[");
		sb.append(getIfpContractAttachedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpStatus</column-name><column-value><![CDATA[");
		sb.append(getIfpStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpStartDate</column-name><column-value><![CDATA[");
		sb.append(getIfpStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpContractAttachedStatus</column-name><column-value><![CDATA[");
		sb.append(getIfpContractAttachedStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpCategory</column-name><column-value><![CDATA[");
		sb.append(getIfpCategory());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
		sb.append(getRecordLockStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpEndDate</column-name><column-value><![CDATA[");
		sb.append(getIfpEndDate());
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
			"<column><column-name>source</column-name><column-value><![CDATA[");
		sb.append(getSource());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpDesignation</column-name><column-value><![CDATA[");
		sb.append(getIfpDesignation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentIfpId</column-name><column-value><![CDATA[");
		sb.append(getParentIfpId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>batchId</column-name><column-value><![CDATA[");
		sb.append(getBatchId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
		sb.append(getContractMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpType</column-name><column-value><![CDATA[");
		sb.append(getIfpType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpName</column-name><column-value><![CDATA[");
		sb.append(getIfpName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpNo</column-name><column-value><![CDATA[");
		sb.append(getIfpNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
		sb.append(getInboundStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpContractSid</column-name><column-value><![CDATA[");
		sb.append(getIfpContractSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
		sb.append(getIfpModelSid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = IfpContract.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			IfpContract.class
		};
	private String _cfpContractSid;
	private String _parentIfpName;
	private Date _ifpContractAttachedDate;
	private int _ifpStatus;
	private Date _ifpStartDate;
	private int _ifpContractAttachedStatus;
	private Date _modifiedDate;
	private int _ifpCategory;
	private boolean _recordLockStatus;
	private Date _ifpEndDate;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private String _ifpDesignation;
	private String _parentIfpId;
	private String _batchId;
	private int _contractMasterSid;
	private int _ifpType;
	private String _ifpName;
	private String _ifpNo;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _ifpContractSid;
	private int _ifpModelSid;
	private IfpContract _escapedModel;
}