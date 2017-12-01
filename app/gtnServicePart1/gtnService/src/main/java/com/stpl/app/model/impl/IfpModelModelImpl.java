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

import com.stpl.app.model.IfpModel;
import com.stpl.app.model.IfpModelModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the IfpModel service. Represents a row in the &quot;IFP_MODEL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link IfpModelModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IfpModelImpl}.
 * </p>
 *
 * @author
 * @see IfpModelImpl
 * @see IfpModel
 * @see IfpModelModel
 * @generated
 */
@ProviderType
public class IfpModelModelImpl extends BaseModelImpl<IfpModel>
	implements IfpModelModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ifp model model instance should use the {@link IfpModel} interface instead.
	 */
	public static final String TABLE_NAME = "IFP_MODEL";
	public static final Object[][] TABLE_COLUMNS = {
			{ "MODIFIED_BY", Types.INTEGER },
			{ "TOTAL_DOLLAR_COMMITMENT", Types.VARCHAR },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "IFP_STATUS", Types.INTEGER },
			{ "TOTAL_VOLUME_COMMITMENT", Types.VARCHAR },
			{ "BATCH_ID", Types.VARCHAR },
			{ "INTERNAL_NOTES", Types.VARCHAR },
			{ "IFP_ID", Types.VARCHAR },
			{ "TOTAL_MARKETSHARE_COMMITMENT", Types.VARCHAR },
			{ "IFP_CATEGORY", Types.INTEGER },
			{ "PARENT_IFP_NAME", Types.VARCHAR },
			{ "IFP_END_DATE", Types.TIMESTAMP },
			{ "IFP_DESIGNATION", Types.VARCHAR },
			{ "CREATED_BY", Types.INTEGER },
			{ "IFP_START_DATE", Types.TIMESTAMP },
			{ "PARENT_IFP_ID", Types.VARCHAR },
			{ "COMMITMENT_PERIOD", Types.VARCHAR },
			{ "IFP_TYPE", Types.INTEGER },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "IFP_MODEL_SID", Types.INTEGER },
			{ "RECORD_LOCK_STATUS", Types.BOOLEAN },
			{ "SOURCE", Types.VARCHAR },
			{ "IFP_NAME", Types.VARCHAR },
			{ "IFP_NO", Types.VARCHAR },
			{ "INBOUND_STATUS", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("TOTAL_DOLLAR_COMMITMENT", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("IFP_STATUS", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("TOTAL_VOLUME_COMMITMENT", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("BATCH_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("INTERNAL_NOTES", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("TOTAL_MARKETSHARE_COMMITMENT", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_CATEGORY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("PARENT_IFP_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_END_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("IFP_DESIGNATION", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("IFP_START_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("PARENT_IFP_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMMITMENT_PERIOD", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_TYPE", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("IFP_MODEL_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("RECORD_LOCK_STATUS", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("SOURCE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IFP_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("INBOUND_STATUS", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table IFP_MODEL (MODIFIED_BY INTEGER,TOTAL_DOLLAR_COMMITMENT VARCHAR(75) null,CREATED_DATE DATE null,IFP_STATUS INTEGER,TOTAL_VOLUME_COMMITMENT VARCHAR(75) null,BATCH_ID VARCHAR(75) null,INTERNAL_NOTES VARCHAR(75) null,IFP_ID VARCHAR(75) null,TOTAL_MARKETSHARE_COMMITMENT VARCHAR(75) null,IFP_CATEGORY INTEGER,PARENT_IFP_NAME VARCHAR(75) null,IFP_END_DATE DATE null,IFP_DESIGNATION VARCHAR(75) null,CREATED_BY INTEGER,IFP_START_DATE DATE null,PARENT_IFP_ID VARCHAR(75) null,COMMITMENT_PERIOD VARCHAR(75) null,IFP_TYPE INTEGER,MODIFIED_DATE DATE null,IFP_MODEL_SID INTEGER not null primary key IDENTITY,RECORD_LOCK_STATUS BOOLEAN,SOURCE VARCHAR(75) null,IFP_NAME VARCHAR(75) null,IFP_NO VARCHAR(75) null,INBOUND_STATUS VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table IFP_MODEL";
	public static final String ORDER_BY_JPQL = " ORDER BY ifpModel.ifpModelSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY IFP_MODEL.IFP_MODEL_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.IfpModel"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.IfpModel"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.stpl.app.model.IfpModel"),
			true);
	public static final long IFPID_COLUMN_BITMASK = 1L;
	public static final long IFPNAME_COLUMN_BITMASK = 2L;
	public static final long IFPNO_COLUMN_BITMASK = 4L;
	public static final long IFPSTATUS_COLUMN_BITMASK = 8L;
	public static final long IFPTYPE_COLUMN_BITMASK = 16L;
	public static final long IFPMODELSID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.IfpModel"));

	public IfpModelModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _ifpModelSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setIfpModelSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ifpModelSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return IfpModel.class;
	}

	@Override
	public String getModelClassName() {
		return IfpModel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("totalDollarCommitment", getTotalDollarCommitment());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("ifpStatus", getIfpStatus());
		attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
		attributes.put("batchId", getBatchId());
		attributes.put("internalNotes", getInternalNotes());
		attributes.put("ifpId", getIfpId());
		attributes.put("totalMarketshareCommitment",
			getTotalMarketshareCommitment());
		attributes.put("ifpCategory", getIfpCategory());
		attributes.put("parentIfpName", getParentIfpName());
		attributes.put("ifpEndDate", getIfpEndDate());
		attributes.put("ifpDesignation", getIfpDesignation());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("ifpStartDate", getIfpStartDate());
		attributes.put("parentIfpId", getParentIfpId());
		attributes.put("commitmentPeriod", getCommitmentPeriod());
		attributes.put("ifpType", getIfpType());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ifpModelSid", getIfpModelSid());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("source", getSource());
		attributes.put("ifpName", getIfpName());
		attributes.put("ifpNo", getIfpNo());
		attributes.put("inboundStatus", getInboundStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String totalDollarCommitment = (String)attributes.get(
				"totalDollarCommitment");

		if (totalDollarCommitment != null) {
			setTotalDollarCommitment(totalDollarCommitment);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer ifpStatus = (Integer)attributes.get("ifpStatus");

		if (ifpStatus != null) {
			setIfpStatus(ifpStatus);
		}

		String totalVolumeCommitment = (String)attributes.get(
				"totalVolumeCommitment");

		if (totalVolumeCommitment != null) {
			setTotalVolumeCommitment(totalVolumeCommitment);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String internalNotes = (String)attributes.get("internalNotes");

		if (internalNotes != null) {
			setInternalNotes(internalNotes);
		}

		String ifpId = (String)attributes.get("ifpId");

		if (ifpId != null) {
			setIfpId(ifpId);
		}

		String totalMarketshareCommitment = (String)attributes.get(
				"totalMarketshareCommitment");

		if (totalMarketshareCommitment != null) {
			setTotalMarketshareCommitment(totalMarketshareCommitment);
		}

		Integer ifpCategory = (Integer)attributes.get("ifpCategory");

		if (ifpCategory != null) {
			setIfpCategory(ifpCategory);
		}

		String parentIfpName = (String)attributes.get("parentIfpName");

		if (parentIfpName != null) {
			setParentIfpName(parentIfpName);
		}

		Date ifpEndDate = (Date)attributes.get("ifpEndDate");

		if (ifpEndDate != null) {
			setIfpEndDate(ifpEndDate);
		}

		String ifpDesignation = (String)attributes.get("ifpDesignation");

		if (ifpDesignation != null) {
			setIfpDesignation(ifpDesignation);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date ifpStartDate = (Date)attributes.get("ifpStartDate");

		if (ifpStartDate != null) {
			setIfpStartDate(ifpStartDate);
		}

		String parentIfpId = (String)attributes.get("parentIfpId");

		if (parentIfpId != null) {
			setParentIfpId(parentIfpId);
		}

		String commitmentPeriod = (String)attributes.get("commitmentPeriod");

		if (commitmentPeriod != null) {
			setCommitmentPeriod(commitmentPeriod);
		}

		Integer ifpType = (Integer)attributes.get("ifpType");

		if (ifpType != null) {
			setIfpType(ifpType);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer ifpModelSid = (Integer)attributes.get("ifpModelSid");

		if (ifpModelSid != null) {
			setIfpModelSid(ifpModelSid);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String ifpName = (String)attributes.get("ifpName");

		if (ifpName != null) {
			setIfpName(ifpName);
		}

		String ifpNo = (String)attributes.get("ifpNo");

		if (ifpNo != null) {
			setIfpNo(ifpNo);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
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
	public String getTotalDollarCommitment() {
		if (_totalDollarCommitment == null) {
			return StringPool.BLANK;
		}
		else {
			return _totalDollarCommitment;
		}
	}

	@Override
	public void setTotalDollarCommitment(String totalDollarCommitment) {
		_totalDollarCommitment = totalDollarCommitment;
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
	public int getIfpStatus() {
		return _ifpStatus;
	}

	@Override
	public void setIfpStatus(int ifpStatus) {
		_columnBitmask |= IFPSTATUS_COLUMN_BITMASK;

		if (!_setOriginalIfpStatus) {
			_setOriginalIfpStatus = true;

			_originalIfpStatus = _ifpStatus;
		}

		_ifpStatus = ifpStatus;
	}

	public int getOriginalIfpStatus() {
		return _originalIfpStatus;
	}

	@Override
	public String getTotalVolumeCommitment() {
		if (_totalVolumeCommitment == null) {
			return StringPool.BLANK;
		}
		else {
			return _totalVolumeCommitment;
		}
	}

	@Override
	public void setTotalVolumeCommitment(String totalVolumeCommitment) {
		_totalVolumeCommitment = totalVolumeCommitment;
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
	public String getInternalNotes() {
		if (_internalNotes == null) {
			return StringPool.BLANK;
		}
		else {
			return _internalNotes;
		}
	}

	@Override
	public void setInternalNotes(String internalNotes) {
		_internalNotes = internalNotes;
	}

	@Override
	public String getIfpId() {
		if (_ifpId == null) {
			return StringPool.BLANK;
		}
		else {
			return _ifpId;
		}
	}

	@Override
	public void setIfpId(String ifpId) {
		_columnBitmask |= IFPID_COLUMN_BITMASK;

		if (_originalIfpId == null) {
			_originalIfpId = _ifpId;
		}

		_ifpId = ifpId;
	}

	public String getOriginalIfpId() {
		return GetterUtil.getString(_originalIfpId);
	}

	@Override
	public String getTotalMarketshareCommitment() {
		if (_totalMarketshareCommitment == null) {
			return StringPool.BLANK;
		}
		else {
			return _totalMarketshareCommitment;
		}
	}

	@Override
	public void setTotalMarketshareCommitment(String totalMarketshareCommitment) {
		_totalMarketshareCommitment = totalMarketshareCommitment;
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
	public Date getIfpEndDate() {
		return _ifpEndDate;
	}

	@Override
	public void setIfpEndDate(Date ifpEndDate) {
		_ifpEndDate = ifpEndDate;
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
	public int getCreatedBy() {
		return _createdBy;
	}

	@Override
	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
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
	public String getCommitmentPeriod() {
		if (_commitmentPeriod == null) {
			return StringPool.BLANK;
		}
		else {
			return _commitmentPeriod;
		}
	}

	@Override
	public void setCommitmentPeriod(String commitmentPeriod) {
		_commitmentPeriod = commitmentPeriod;
	}

	@Override
	public int getIfpType() {
		return _ifpType;
	}

	@Override
	public void setIfpType(int ifpType) {
		_columnBitmask |= IFPTYPE_COLUMN_BITMASK;

		if (!_setOriginalIfpType) {
			_setOriginalIfpType = true;

			_originalIfpType = _ifpType;
		}

		_ifpType = ifpType;
	}

	public int getOriginalIfpType() {
		return _originalIfpType;
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
	public int getIfpModelSid() {
		return _ifpModelSid;
	}

	@Override
	public void setIfpModelSid(int ifpModelSid) {
		_ifpModelSid = ifpModelSid;
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
		_columnBitmask |= IFPNAME_COLUMN_BITMASK;

		if (_originalIfpName == null) {
			_originalIfpName = _ifpName;
		}

		_ifpName = ifpName;
	}

	public String getOriginalIfpName() {
		return GetterUtil.getString(_originalIfpName);
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
		_columnBitmask |= IFPNO_COLUMN_BITMASK;

		if (_originalIfpNo == null) {
			_originalIfpNo = _ifpNo;
		}

		_ifpNo = ifpNo;
	}

	public String getOriginalIfpNo() {
		return GetterUtil.getString(_originalIfpNo);
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public IfpModel toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (IfpModel)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		IfpModelImpl ifpModelImpl = new IfpModelImpl();

		ifpModelImpl.setModifiedBy(getModifiedBy());
		ifpModelImpl.setTotalDollarCommitment(getTotalDollarCommitment());
		ifpModelImpl.setCreatedDate(getCreatedDate());
		ifpModelImpl.setIfpStatus(getIfpStatus());
		ifpModelImpl.setTotalVolumeCommitment(getTotalVolumeCommitment());
		ifpModelImpl.setBatchId(getBatchId());
		ifpModelImpl.setInternalNotes(getInternalNotes());
		ifpModelImpl.setIfpId(getIfpId());
		ifpModelImpl.setTotalMarketshareCommitment(getTotalMarketshareCommitment());
		ifpModelImpl.setIfpCategory(getIfpCategory());
		ifpModelImpl.setParentIfpName(getParentIfpName());
		ifpModelImpl.setIfpEndDate(getIfpEndDate());
		ifpModelImpl.setIfpDesignation(getIfpDesignation());
		ifpModelImpl.setCreatedBy(getCreatedBy());
		ifpModelImpl.setIfpStartDate(getIfpStartDate());
		ifpModelImpl.setParentIfpId(getParentIfpId());
		ifpModelImpl.setCommitmentPeriod(getCommitmentPeriod());
		ifpModelImpl.setIfpType(getIfpType());
		ifpModelImpl.setModifiedDate(getModifiedDate());
		ifpModelImpl.setIfpModelSid(getIfpModelSid());
		ifpModelImpl.setRecordLockStatus(getRecordLockStatus());
		ifpModelImpl.setSource(getSource());
		ifpModelImpl.setIfpName(getIfpName());
		ifpModelImpl.setIfpNo(getIfpNo());
		ifpModelImpl.setInboundStatus(getInboundStatus());

		ifpModelImpl.resetOriginalValues();

		return ifpModelImpl;
	}

	@Override
	public int compareTo(IfpModel ifpModel) {
		int primaryKey = ifpModel.getPrimaryKey();

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

		if (!(obj instanceof IfpModel)) {
			return false;
		}

		IfpModel ifpModel = (IfpModel)obj;

		int primaryKey = ifpModel.getPrimaryKey();

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
		IfpModelModelImpl ifpModelModelImpl = this;

		ifpModelModelImpl._originalIfpStatus = ifpModelModelImpl._ifpStatus;

		ifpModelModelImpl._setOriginalIfpStatus = false;

		ifpModelModelImpl._originalIfpId = ifpModelModelImpl._ifpId;

		ifpModelModelImpl._originalIfpType = ifpModelModelImpl._ifpType;

		ifpModelModelImpl._setOriginalIfpType = false;

		ifpModelModelImpl._originalIfpName = ifpModelModelImpl._ifpName;

		ifpModelModelImpl._originalIfpNo = ifpModelModelImpl._ifpNo;

		ifpModelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<IfpModel> toCacheModel() {
		IfpModelCacheModel ifpModelCacheModel = new IfpModelCacheModel();

		ifpModelCacheModel.modifiedBy = getModifiedBy();

		ifpModelCacheModel.totalDollarCommitment = getTotalDollarCommitment();

		String totalDollarCommitment = ifpModelCacheModel.totalDollarCommitment;

		if ((totalDollarCommitment != null) &&
				(totalDollarCommitment.length() == 0)) {
			ifpModelCacheModel.totalDollarCommitment = null;
		}

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			ifpModelCacheModel.createdDate = createdDate.getTime();
		}
		else {
			ifpModelCacheModel.createdDate = Long.MIN_VALUE;
		}

		ifpModelCacheModel.ifpStatus = getIfpStatus();

		ifpModelCacheModel.totalVolumeCommitment = getTotalVolumeCommitment();

		String totalVolumeCommitment = ifpModelCacheModel.totalVolumeCommitment;

		if ((totalVolumeCommitment != null) &&
				(totalVolumeCommitment.length() == 0)) {
			ifpModelCacheModel.totalVolumeCommitment = null;
		}

		ifpModelCacheModel.batchId = getBatchId();

		String batchId = ifpModelCacheModel.batchId;

		if ((batchId != null) && (batchId.length() == 0)) {
			ifpModelCacheModel.batchId = null;
		}

		ifpModelCacheModel.internalNotes = getInternalNotes();

		String internalNotes = ifpModelCacheModel.internalNotes;

		if ((internalNotes != null) && (internalNotes.length() == 0)) {
			ifpModelCacheModel.internalNotes = null;
		}

		ifpModelCacheModel.ifpId = getIfpId();

		String ifpId = ifpModelCacheModel.ifpId;

		if ((ifpId != null) && (ifpId.length() == 0)) {
			ifpModelCacheModel.ifpId = null;
		}

		ifpModelCacheModel.totalMarketshareCommitment = getTotalMarketshareCommitment();

		String totalMarketshareCommitment = ifpModelCacheModel.totalMarketshareCommitment;

		if ((totalMarketshareCommitment != null) &&
				(totalMarketshareCommitment.length() == 0)) {
			ifpModelCacheModel.totalMarketshareCommitment = null;
		}

		ifpModelCacheModel.ifpCategory = getIfpCategory();

		ifpModelCacheModel.parentIfpName = getParentIfpName();

		String parentIfpName = ifpModelCacheModel.parentIfpName;

		if ((parentIfpName != null) && (parentIfpName.length() == 0)) {
			ifpModelCacheModel.parentIfpName = null;
		}

		Date ifpEndDate = getIfpEndDate();

		if (ifpEndDate != null) {
			ifpModelCacheModel.ifpEndDate = ifpEndDate.getTime();
		}
		else {
			ifpModelCacheModel.ifpEndDate = Long.MIN_VALUE;
		}

		ifpModelCacheModel.ifpDesignation = getIfpDesignation();

		String ifpDesignation = ifpModelCacheModel.ifpDesignation;

		if ((ifpDesignation != null) && (ifpDesignation.length() == 0)) {
			ifpModelCacheModel.ifpDesignation = null;
		}

		ifpModelCacheModel.createdBy = getCreatedBy();

		Date ifpStartDate = getIfpStartDate();

		if (ifpStartDate != null) {
			ifpModelCacheModel.ifpStartDate = ifpStartDate.getTime();
		}
		else {
			ifpModelCacheModel.ifpStartDate = Long.MIN_VALUE;
		}

		ifpModelCacheModel.parentIfpId = getParentIfpId();

		String parentIfpId = ifpModelCacheModel.parentIfpId;

		if ((parentIfpId != null) && (parentIfpId.length() == 0)) {
			ifpModelCacheModel.parentIfpId = null;
		}

		ifpModelCacheModel.commitmentPeriod = getCommitmentPeriod();

		String commitmentPeriod = ifpModelCacheModel.commitmentPeriod;

		if ((commitmentPeriod != null) && (commitmentPeriod.length() == 0)) {
			ifpModelCacheModel.commitmentPeriod = null;
		}

		ifpModelCacheModel.ifpType = getIfpType();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ifpModelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			ifpModelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ifpModelCacheModel.ifpModelSid = getIfpModelSid();

		ifpModelCacheModel.recordLockStatus = getRecordLockStatus();

		ifpModelCacheModel.source = getSource();

		String source = ifpModelCacheModel.source;

		if ((source != null) && (source.length() == 0)) {
			ifpModelCacheModel.source = null;
		}

		ifpModelCacheModel.ifpName = getIfpName();

		String ifpName = ifpModelCacheModel.ifpName;

		if ((ifpName != null) && (ifpName.length() == 0)) {
			ifpModelCacheModel.ifpName = null;
		}

		ifpModelCacheModel.ifpNo = getIfpNo();

		String ifpNo = ifpModelCacheModel.ifpNo;

		if ((ifpNo != null) && (ifpNo.length() == 0)) {
			ifpModelCacheModel.ifpNo = null;
		}

		ifpModelCacheModel.inboundStatus = getInboundStatus();

		String inboundStatus = ifpModelCacheModel.inboundStatus;

		if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
			ifpModelCacheModel.inboundStatus = null;
		}

		return ifpModelCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", totalDollarCommitment=");
		sb.append(getTotalDollarCommitment());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", ifpStatus=");
		sb.append(getIfpStatus());
		sb.append(", totalVolumeCommitment=");
		sb.append(getTotalVolumeCommitment());
		sb.append(", batchId=");
		sb.append(getBatchId());
		sb.append(", internalNotes=");
		sb.append(getInternalNotes());
		sb.append(", ifpId=");
		sb.append(getIfpId());
		sb.append(", totalMarketshareCommitment=");
		sb.append(getTotalMarketshareCommitment());
		sb.append(", ifpCategory=");
		sb.append(getIfpCategory());
		sb.append(", parentIfpName=");
		sb.append(getParentIfpName());
		sb.append(", ifpEndDate=");
		sb.append(getIfpEndDate());
		sb.append(", ifpDesignation=");
		sb.append(getIfpDesignation());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", ifpStartDate=");
		sb.append(getIfpStartDate());
		sb.append(", parentIfpId=");
		sb.append(getParentIfpId());
		sb.append(", commitmentPeriod=");
		sb.append(getCommitmentPeriod());
		sb.append(", ifpType=");
		sb.append(getIfpType());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", ifpModelSid=");
		sb.append(getIfpModelSid());
		sb.append(", recordLockStatus=");
		sb.append(getRecordLockStatus());
		sb.append(", source=");
		sb.append(getSource());
		sb.append(", ifpName=");
		sb.append(getIfpName());
		sb.append(", ifpNo=");
		sb.append(getIfpNo());
		sb.append(", inboundStatus=");
		sb.append(getInboundStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(79);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.IfpModel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalDollarCommitment</column-name><column-value><![CDATA[");
		sb.append(getTotalDollarCommitment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdDate</column-name><column-value><![CDATA[");
		sb.append(getCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpStatus</column-name><column-value><![CDATA[");
		sb.append(getIfpStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalVolumeCommitment</column-name><column-value><![CDATA[");
		sb.append(getTotalVolumeCommitment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>batchId</column-name><column-value><![CDATA[");
		sb.append(getBatchId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>internalNotes</column-name><column-value><![CDATA[");
		sb.append(getInternalNotes());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpId</column-name><column-value><![CDATA[");
		sb.append(getIfpId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalMarketshareCommitment</column-name><column-value><![CDATA[");
		sb.append(getTotalMarketshareCommitment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpCategory</column-name><column-value><![CDATA[");
		sb.append(getIfpCategory());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentIfpName</column-name><column-value><![CDATA[");
		sb.append(getParentIfpName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpEndDate</column-name><column-value><![CDATA[");
		sb.append(getIfpEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpDesignation</column-name><column-value><![CDATA[");
		sb.append(getIfpDesignation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpStartDate</column-name><column-value><![CDATA[");
		sb.append(getIfpStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentIfpId</column-name><column-value><![CDATA[");
		sb.append(getParentIfpId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commitmentPeriod</column-name><column-value><![CDATA[");
		sb.append(getCommitmentPeriod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpType</column-name><column-value><![CDATA[");
		sb.append(getIfpType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
		sb.append(getIfpModelSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
		sb.append(getRecordLockStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>source</column-name><column-value><![CDATA[");
		sb.append(getSource());
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
			"<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
		sb.append(getInboundStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = IfpModel.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			IfpModel.class
		};
	private int _modifiedBy;
	private String _totalDollarCommitment;
	private Date _createdDate;
	private int _ifpStatus;
	private int _originalIfpStatus;
	private boolean _setOriginalIfpStatus;
	private String _totalVolumeCommitment;
	private String _batchId;
	private String _internalNotes;
	private String _ifpId;
	private String _originalIfpId;
	private String _totalMarketshareCommitment;
	private int _ifpCategory;
	private String _parentIfpName;
	private Date _ifpEndDate;
	private String _ifpDesignation;
	private int _createdBy;
	private Date _ifpStartDate;
	private String _parentIfpId;
	private String _commitmentPeriod;
	private int _ifpType;
	private int _originalIfpType;
	private boolean _setOriginalIfpType;
	private Date _modifiedDate;
	private int _ifpModelSid;
	private boolean _recordLockStatus;
	private String _source;
	private String _ifpName;
	private String _originalIfpName;
	private String _ifpNo;
	private String _originalIfpNo;
	private String _inboundStatus;
	private long _columnBitmask;
	private IfpModel _escapedModel;
}