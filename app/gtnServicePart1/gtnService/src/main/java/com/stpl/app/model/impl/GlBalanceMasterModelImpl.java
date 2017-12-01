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

import com.stpl.app.model.GlBalanceMaster;
import com.stpl.app.model.GlBalanceMasterModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the GlBalanceMaster service. Represents a row in the &quot;GL_BALANCE_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link GlBalanceMasterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GlBalanceMasterImpl}.
 * </p>
 *
 * @author
 * @see GlBalanceMasterImpl
 * @see GlBalanceMaster
 * @see GlBalanceMasterModel
 * @generated
 */
@ProviderType
public class GlBalanceMasterModelImpl extends BaseModelImpl<GlBalanceMaster>
	implements GlBalanceMasterModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a gl balance master model instance should use the {@link GlBalanceMaster} interface instead.
	 */
	public static final String TABLE_NAME = "GL_BALANCE_MASTER";
	public static final Object[][] TABLE_COLUMNS = {
			{ "CREATED_BY", Types.INTEGER },
			{ "MODIFIED_BY", Types.INTEGER },
			{ "ACCOUNT_ID", Types.VARCHAR },
			{ "UPLOAD_DATE", Types.TIMESTAMP },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "GL_BALANCE_MASTER_SID", Types.INTEGER },
			{ "IS_ACTIVE", Types.VARCHAR },
			{ "BATCH_ID", Types.VARCHAR },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "BALANCE", Types.VARCHAR },
			{ "CLOSE_INDICATOR", Types.VARCHAR },
			{ "RECORD_LOCK_STATUS", Types.BOOLEAN },
			{ "YEAR", Types.VARCHAR },
			{ "PERIOD", Types.VARCHAR },
			{ "SOURCE", Types.VARCHAR },
			{ "INSERTED_DATE", Types.TIMESTAMP },
			{ "ACCOUNT_NO", Types.VARCHAR },
			{ "INBOUND_STATUS", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ACCOUNT_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("UPLOAD_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("GL_BALANCE_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("IS_ACTIVE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("BATCH_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("BALANCE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CLOSE_INDICATOR", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("RECORD_LOCK_STATUS", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("YEAR", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("PERIOD", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("SOURCE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("INSERTED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ACCOUNT_NO", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("INBOUND_STATUS", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table GL_BALANCE_MASTER (CREATED_BY INTEGER,MODIFIED_BY INTEGER,ACCOUNT_ID VARCHAR(75) null,UPLOAD_DATE DATE null,CREATED_DATE DATE null,GL_BALANCE_MASTER_SID INTEGER not null primary key IDENTITY,IS_ACTIVE VARCHAR(75) null,BATCH_ID VARCHAR(75) null,MODIFIED_DATE DATE null,BALANCE VARCHAR(75) null,CLOSE_INDICATOR VARCHAR(75) null,RECORD_LOCK_STATUS BOOLEAN,YEAR VARCHAR(75) null,PERIOD VARCHAR(75) null,SOURCE VARCHAR(75) null,INSERTED_DATE DATE null,ACCOUNT_NO VARCHAR(75) null,INBOUND_STATUS VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table GL_BALANCE_MASTER";
	public static final String ORDER_BY_JPQL = " ORDER BY glBalanceMaster.glBalanceMasterSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY GL_BALANCE_MASTER.GL_BALANCE_MASTER_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.GlBalanceMaster"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.GlBalanceMaster"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.stpl.app.model.GlBalanceMaster"),
			true);
	public static final long ACCOUNTID_COLUMN_BITMASK = 1L;
	public static final long ACCOUNTNO_COLUMN_BITMASK = 2L;
	public static final long ISACTIVE_COLUMN_BITMASK = 4L;
	public static final long PERIOD_COLUMN_BITMASK = 8L;
	public static final long UPLOADDATE_COLUMN_BITMASK = 16L;
	public static final long YEAR_COLUMN_BITMASK = 32L;
	public static final long GLBALANCEMASTERSID_COLUMN_BITMASK = 64L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.GlBalanceMaster"));

	public GlBalanceMasterModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _glBalanceMasterSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setGlBalanceMasterSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _glBalanceMasterSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return GlBalanceMaster.class;
	}

	@Override
	public String getModelClassName() {
		return GlBalanceMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("accountId", getAccountId());
		attributes.put("uploadDate", getUploadDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("glBalanceMasterSid", getGlBalanceMasterSid());
		attributes.put("isActive", getIsActive());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("balance", getBalance());
		attributes.put("closeIndicator", getCloseIndicator());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("year", getYear());
		attributes.put("period", getPeriod());
		attributes.put("source", getSource());
		attributes.put("insertedDate", getInsertedDate());
		attributes.put("accountNo", getAccountNo());
		attributes.put("inboundStatus", getInboundStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String accountId = (String)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Date uploadDate = (Date)attributes.get("uploadDate");

		if (uploadDate != null) {
			setUploadDate(uploadDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer glBalanceMasterSid = (Integer)attributes.get(
				"glBalanceMasterSid");

		if (glBalanceMasterSid != null) {
			setGlBalanceMasterSid(glBalanceMasterSid);
		}

		String isActive = (String)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String balance = (String)attributes.get("balance");

		if (balance != null) {
			setBalance(balance);
		}

		String closeIndicator = (String)attributes.get("closeIndicator");

		if (closeIndicator != null) {
			setCloseIndicator(closeIndicator);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String year = (String)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String period = (String)attributes.get("period");

		if (period != null) {
			setPeriod(period);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Date insertedDate = (Date)attributes.get("insertedDate");

		if (insertedDate != null) {
			setInsertedDate(insertedDate);
		}

		String accountNo = (String)attributes.get("accountNo");

		if (accountNo != null) {
			setAccountNo(accountNo);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
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
	public int getModifiedBy() {
		return _modifiedBy;
	}

	@Override
	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	@Override
	public String getAccountId() {
		if (_accountId == null) {
			return StringPool.BLANK;
		}
		else {
			return _accountId;
		}
	}

	@Override
	public void setAccountId(String accountId) {
		_columnBitmask |= ACCOUNTID_COLUMN_BITMASK;

		if (_originalAccountId == null) {
			_originalAccountId = _accountId;
		}

		_accountId = accountId;
	}

	public String getOriginalAccountId() {
		return GetterUtil.getString(_originalAccountId);
	}

	@Override
	public Date getUploadDate() {
		return _uploadDate;
	}

	@Override
	public void setUploadDate(Date uploadDate) {
		_columnBitmask |= UPLOADDATE_COLUMN_BITMASK;

		if (_originalUploadDate == null) {
			_originalUploadDate = _uploadDate;
		}

		_uploadDate = uploadDate;
	}

	public Date getOriginalUploadDate() {
		return _originalUploadDate;
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
	public int getGlBalanceMasterSid() {
		return _glBalanceMasterSid;
	}

	@Override
	public void setGlBalanceMasterSid(int glBalanceMasterSid) {
		_glBalanceMasterSid = glBalanceMasterSid;
	}

	@Override
	public String getIsActive() {
		if (_isActive == null) {
			return StringPool.BLANK;
		}
		else {
			return _isActive;
		}
	}

	@Override
	public void setIsActive(String isActive) {
		_columnBitmask |= ISACTIVE_COLUMN_BITMASK;

		if (_originalIsActive == null) {
			_originalIsActive = _isActive;
		}

		_isActive = isActive;
	}

	public String getOriginalIsActive() {
		return GetterUtil.getString(_originalIsActive);
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
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public String getBalance() {
		if (_balance == null) {
			return StringPool.BLANK;
		}
		else {
			return _balance;
		}
	}

	@Override
	public void setBalance(String balance) {
		_balance = balance;
	}

	@Override
	public String getCloseIndicator() {
		if (_closeIndicator == null) {
			return StringPool.BLANK;
		}
		else {
			return _closeIndicator;
		}
	}

	@Override
	public void setCloseIndicator(String closeIndicator) {
		_closeIndicator = closeIndicator;
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
	public String getYear() {
		if (_year == null) {
			return StringPool.BLANK;
		}
		else {
			return _year;
		}
	}

	@Override
	public void setYear(String year) {
		_columnBitmask |= YEAR_COLUMN_BITMASK;

		if (_originalYear == null) {
			_originalYear = _year;
		}

		_year = year;
	}

	public String getOriginalYear() {
		return GetterUtil.getString(_originalYear);
	}

	@Override
	public String getPeriod() {
		if (_period == null) {
			return StringPool.BLANK;
		}
		else {
			return _period;
		}
	}

	@Override
	public void setPeriod(String period) {
		_columnBitmask |= PERIOD_COLUMN_BITMASK;

		if (_originalPeriod == null) {
			_originalPeriod = _period;
		}

		_period = period;
	}

	public String getOriginalPeriod() {
		return GetterUtil.getString(_originalPeriod);
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
	public Date getInsertedDate() {
		return _insertedDate;
	}

	@Override
	public void setInsertedDate(Date insertedDate) {
		_insertedDate = insertedDate;
	}

	@Override
	public String getAccountNo() {
		if (_accountNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _accountNo;
		}
	}

	@Override
	public void setAccountNo(String accountNo) {
		_columnBitmask |= ACCOUNTNO_COLUMN_BITMASK;

		if (_originalAccountNo == null) {
			_originalAccountNo = _accountNo;
		}

		_accountNo = accountNo;
	}

	public String getOriginalAccountNo() {
		return GetterUtil.getString(_originalAccountNo);
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
	public GlBalanceMaster toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (GlBalanceMaster)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		GlBalanceMasterImpl glBalanceMasterImpl = new GlBalanceMasterImpl();

		glBalanceMasterImpl.setCreatedBy(getCreatedBy());
		glBalanceMasterImpl.setModifiedBy(getModifiedBy());
		glBalanceMasterImpl.setAccountId(getAccountId());
		glBalanceMasterImpl.setUploadDate(getUploadDate());
		glBalanceMasterImpl.setCreatedDate(getCreatedDate());
		glBalanceMasterImpl.setGlBalanceMasterSid(getGlBalanceMasterSid());
		glBalanceMasterImpl.setIsActive(getIsActive());
		glBalanceMasterImpl.setBatchId(getBatchId());
		glBalanceMasterImpl.setModifiedDate(getModifiedDate());
		glBalanceMasterImpl.setBalance(getBalance());
		glBalanceMasterImpl.setCloseIndicator(getCloseIndicator());
		glBalanceMasterImpl.setRecordLockStatus(getRecordLockStatus());
		glBalanceMasterImpl.setYear(getYear());
		glBalanceMasterImpl.setPeriod(getPeriod());
		glBalanceMasterImpl.setSource(getSource());
		glBalanceMasterImpl.setInsertedDate(getInsertedDate());
		glBalanceMasterImpl.setAccountNo(getAccountNo());
		glBalanceMasterImpl.setInboundStatus(getInboundStatus());

		glBalanceMasterImpl.resetOriginalValues();

		return glBalanceMasterImpl;
	}

	@Override
	public int compareTo(GlBalanceMaster glBalanceMaster) {
		int primaryKey = glBalanceMaster.getPrimaryKey();

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

		if (!(obj instanceof GlBalanceMaster)) {
			return false;
		}

		GlBalanceMaster glBalanceMaster = (GlBalanceMaster)obj;

		int primaryKey = glBalanceMaster.getPrimaryKey();

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
		GlBalanceMasterModelImpl glBalanceMasterModelImpl = this;

		glBalanceMasterModelImpl._originalAccountId = glBalanceMasterModelImpl._accountId;

		glBalanceMasterModelImpl._originalUploadDate = glBalanceMasterModelImpl._uploadDate;

		glBalanceMasterModelImpl._originalIsActive = glBalanceMasterModelImpl._isActive;

		glBalanceMasterModelImpl._originalYear = glBalanceMasterModelImpl._year;

		glBalanceMasterModelImpl._originalPeriod = glBalanceMasterModelImpl._period;

		glBalanceMasterModelImpl._originalAccountNo = glBalanceMasterModelImpl._accountNo;

		glBalanceMasterModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<GlBalanceMaster> toCacheModel() {
		GlBalanceMasterCacheModel glBalanceMasterCacheModel = new GlBalanceMasterCacheModel();

		glBalanceMasterCacheModel.createdBy = getCreatedBy();

		glBalanceMasterCacheModel.modifiedBy = getModifiedBy();

		glBalanceMasterCacheModel.accountId = getAccountId();

		String accountId = glBalanceMasterCacheModel.accountId;

		if ((accountId != null) && (accountId.length() == 0)) {
			glBalanceMasterCacheModel.accountId = null;
		}

		Date uploadDate = getUploadDate();

		if (uploadDate != null) {
			glBalanceMasterCacheModel.uploadDate = uploadDate.getTime();
		}
		else {
			glBalanceMasterCacheModel.uploadDate = Long.MIN_VALUE;
		}

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			glBalanceMasterCacheModel.createdDate = createdDate.getTime();
		}
		else {
			glBalanceMasterCacheModel.createdDate = Long.MIN_VALUE;
		}

		glBalanceMasterCacheModel.glBalanceMasterSid = getGlBalanceMasterSid();

		glBalanceMasterCacheModel.isActive = getIsActive();

		String isActive = glBalanceMasterCacheModel.isActive;

		if ((isActive != null) && (isActive.length() == 0)) {
			glBalanceMasterCacheModel.isActive = null;
		}

		glBalanceMasterCacheModel.batchId = getBatchId();

		String batchId = glBalanceMasterCacheModel.batchId;

		if ((batchId != null) && (batchId.length() == 0)) {
			glBalanceMasterCacheModel.batchId = null;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			glBalanceMasterCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			glBalanceMasterCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		glBalanceMasterCacheModel.balance = getBalance();

		String balance = glBalanceMasterCacheModel.balance;

		if ((balance != null) && (balance.length() == 0)) {
			glBalanceMasterCacheModel.balance = null;
		}

		glBalanceMasterCacheModel.closeIndicator = getCloseIndicator();

		String closeIndicator = glBalanceMasterCacheModel.closeIndicator;

		if ((closeIndicator != null) && (closeIndicator.length() == 0)) {
			glBalanceMasterCacheModel.closeIndicator = null;
		}

		glBalanceMasterCacheModel.recordLockStatus = getRecordLockStatus();

		glBalanceMasterCacheModel.year = getYear();

		String year = glBalanceMasterCacheModel.year;

		if ((year != null) && (year.length() == 0)) {
			glBalanceMasterCacheModel.year = null;
		}

		glBalanceMasterCacheModel.period = getPeriod();

		String period = glBalanceMasterCacheModel.period;

		if ((period != null) && (period.length() == 0)) {
			glBalanceMasterCacheModel.period = null;
		}

		glBalanceMasterCacheModel.source = getSource();

		String source = glBalanceMasterCacheModel.source;

		if ((source != null) && (source.length() == 0)) {
			glBalanceMasterCacheModel.source = null;
		}

		Date insertedDate = getInsertedDate();

		if (insertedDate != null) {
			glBalanceMasterCacheModel.insertedDate = insertedDate.getTime();
		}
		else {
			glBalanceMasterCacheModel.insertedDate = Long.MIN_VALUE;
		}

		glBalanceMasterCacheModel.accountNo = getAccountNo();

		String accountNo = glBalanceMasterCacheModel.accountNo;

		if ((accountNo != null) && (accountNo.length() == 0)) {
			glBalanceMasterCacheModel.accountNo = null;
		}

		glBalanceMasterCacheModel.inboundStatus = getInboundStatus();

		String inboundStatus = glBalanceMasterCacheModel.inboundStatus;

		if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
			glBalanceMasterCacheModel.inboundStatus = null;
		}

		return glBalanceMasterCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{createdBy=");
		sb.append(getCreatedBy());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", accountId=");
		sb.append(getAccountId());
		sb.append(", uploadDate=");
		sb.append(getUploadDate());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", glBalanceMasterSid=");
		sb.append(getGlBalanceMasterSid());
		sb.append(", isActive=");
		sb.append(getIsActive());
		sb.append(", batchId=");
		sb.append(getBatchId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", balance=");
		sb.append(getBalance());
		sb.append(", closeIndicator=");
		sb.append(getCloseIndicator());
		sb.append(", recordLockStatus=");
		sb.append(getRecordLockStatus());
		sb.append(", year=");
		sb.append(getYear());
		sb.append(", period=");
		sb.append(getPeriod());
		sb.append(", source=");
		sb.append(getSource());
		sb.append(", insertedDate=");
		sb.append(getInsertedDate());
		sb.append(", accountNo=");
		sb.append(getAccountNo());
		sb.append(", inboundStatus=");
		sb.append(getInboundStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.GlBalanceMaster");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountId</column-name><column-value><![CDATA[");
		sb.append(getAccountId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>uploadDate</column-name><column-value><![CDATA[");
		sb.append(getUploadDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdDate</column-name><column-value><![CDATA[");
		sb.append(getCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>glBalanceMasterSid</column-name><column-value><![CDATA[");
		sb.append(getGlBalanceMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isActive</column-name><column-value><![CDATA[");
		sb.append(getIsActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>batchId</column-name><column-value><![CDATA[");
		sb.append(getBatchId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>balance</column-name><column-value><![CDATA[");
		sb.append(getBalance());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>closeIndicator</column-name><column-value><![CDATA[");
		sb.append(getCloseIndicator());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
		sb.append(getRecordLockStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>period</column-name><column-value><![CDATA[");
		sb.append(getPeriod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>source</column-name><column-value><![CDATA[");
		sb.append(getSource());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>insertedDate</column-name><column-value><![CDATA[");
		sb.append(getInsertedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountNo</column-name><column-value><![CDATA[");
		sb.append(getAccountNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
		sb.append(getInboundStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = GlBalanceMaster.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			GlBalanceMaster.class
		};
	private int _createdBy;
	private int _modifiedBy;
	private String _accountId;
	private String _originalAccountId;
	private Date _uploadDate;
	private Date _originalUploadDate;
	private Date _createdDate;
	private int _glBalanceMasterSid;
	private String _isActive;
	private String _originalIsActive;
	private String _batchId;
	private Date _modifiedDate;
	private String _balance;
	private String _closeIndicator;
	private boolean _recordLockStatus;
	private String _year;
	private String _originalYear;
	private String _period;
	private String _originalPeriod;
	private String _source;
	private Date _insertedDate;
	private String _accountNo;
	private String _originalAccountNo;
	private String _inboundStatus;
	private long _columnBitmask;
	private GlBalanceMaster _escapedModel;
}