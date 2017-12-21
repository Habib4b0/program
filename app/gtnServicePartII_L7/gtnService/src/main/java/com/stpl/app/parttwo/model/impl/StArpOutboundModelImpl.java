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

import com.stpl.app.parttwo.model.StArpOutbound;
import com.stpl.app.parttwo.model.StArpOutboundModel;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPK;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the StArpOutbound service. Represents a row in the &quot;ST_ARP_OUTBOUND&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link StArpOutboundModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StArpOutboundImpl}.
 * </p>
 *
 * @author
 * @see StArpOutboundImpl
 * @see StArpOutbound
 * @see StArpOutboundModel
 * @generated
 */
@ProviderType
public class StArpOutboundModelImpl extends BaseModelImpl<StArpOutbound>
	implements StArpOutboundModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a st arp outbound model instance should use the {@link StArpOutbound} interface instead.
	 */
	public static final String TABLE_NAME = "ST_ARP_OUTBOUND";
	public static final Object[][] TABLE_COLUMNS = {
			{ "SALES_UNITS_RATE", Types.DOUBLE },
			{ "ACCOUNT_TYPE", Types.VARCHAR },
			{ "ORIGINAL_BATCH_ID", Types.VARCHAR },
			{ "COMPANY_MASTER_SID", Types.INTEGER },
			{ "BRAND_MASTER_SID", Types.INTEGER },
			{ "ARP_APPROVAL_DATE", Types.TIMESTAMP },
			{ "USER_ID", Types.INTEGER },
			{ "ARP_MASTER_SID", Types.INTEGER },
			{ "ARP_CREATION_DATE", Types.TIMESTAMP },
			{ "CHECK_RECORD", Types.BOOLEAN },
			{ "ARP_ID", Types.INTEGER },
			{ "ACCOUNT", Types.VARCHAR },
			{ "OUTBOUND_STATUS", Types.BOOLEAN },
			{ "PERIOD_SID", Types.INTEGER },
			{ "ITEM_MASTER_SID", Types.INTEGER },
			{ "RS_MODEL_SID", Types.INTEGER },
			{ "SESSION_ID", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("SALES_UNITS_RATE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("ACCOUNT_TYPE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ORIGINAL_BATCH_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("BRAND_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ARP_APPROVAL_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("USER_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ARP_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ARP_CREATION_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CHECK_RECORD", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("ARP_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ACCOUNT", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("OUTBOUND_STATUS", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("PERIOD_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ITEM_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("RS_MODEL_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("SESSION_ID", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table ST_ARP_OUTBOUND (SALES_UNITS_RATE DOUBLE,ACCOUNT_TYPE VARCHAR(75) null,ORIGINAL_BATCH_ID VARCHAR(75) null,COMPANY_MASTER_SID INTEGER not null IDENTITY,BRAND_MASTER_SID INTEGER,ARP_APPROVAL_DATE DATE null,USER_ID INTEGER not null IDENTITY,ARP_MASTER_SID INTEGER not null IDENTITY,ARP_CREATION_DATE DATE null,CHECK_RECORD BOOLEAN,ARP_ID INTEGER not null IDENTITY,ACCOUNT VARCHAR(75) null,OUTBOUND_STATUS BOOLEAN,PERIOD_SID INTEGER not null IDENTITY,ITEM_MASTER_SID INTEGER not null IDENTITY,RS_MODEL_SID INTEGER not null IDENTITY,SESSION_ID VARCHAR(75) not null IDENTITY,primary key (COMPANY_MASTER_SID, USER_ID, ARP_MASTER_SID, ARP_ID, PERIOD_SID, ITEM_MASTER_SID, RS_MODEL_SID, SESSION_ID))";
	public static final String TABLE_SQL_DROP = "drop table ST_ARP_OUTBOUND";
	public static final String ORDER_BY_JPQL = " ORDER BY stArpOutbound.id.companyMasterSid ASC, stArpOutbound.id.userId ASC, stArpOutbound.id.arpMasterSid ASC, stArpOutbound.id.arpId ASC, stArpOutbound.id.periodSid ASC, stArpOutbound.id.itemMasterSid ASC, stArpOutbound.id.rsModelSid ASC, stArpOutbound.id.sessionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ST_ARP_OUTBOUND.COMPANY_MASTER_SID ASC, ST_ARP_OUTBOUND.USER_ID ASC, ST_ARP_OUTBOUND.ARP_MASTER_SID ASC, ST_ARP_OUTBOUND.ARP_ID ASC, ST_ARP_OUTBOUND.PERIOD_SID ASC, ST_ARP_OUTBOUND.ITEM_MASTER_SID ASC, ST_ARP_OUTBOUND.RS_MODEL_SID ASC, ST_ARP_OUTBOUND.SESSION_ID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.parttwo.model.StArpOutbound"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.parttwo.model.StArpOutbound"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.parttwo.model.StArpOutbound"));

	public StArpOutboundModelImpl() {
	}

	@Override
	public StArpOutboundPK getPrimaryKey() {
		return new StArpOutboundPK(_companyMasterSid, _userId, _arpMasterSid,
			_arpId, _periodSid, _itemMasterSid, _rsModelSid, _sessionId);
	}

	@Override
	public void setPrimaryKey(StArpOutboundPK primaryKey) {
		setCompanyMasterSid(primaryKey.companyMasterSid);
		setUserId(primaryKey.userId);
		setArpMasterSid(primaryKey.arpMasterSid);
		setArpId(primaryKey.arpId);
		setPeriodSid(primaryKey.periodSid);
		setItemMasterSid(primaryKey.itemMasterSid);
		setRsModelSid(primaryKey.rsModelSid);
		setSessionId(primaryKey.sessionId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new StArpOutboundPK(_companyMasterSid, _userId, _arpMasterSid,
			_arpId, _periodSid, _itemMasterSid, _rsModelSid, _sessionId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((StArpOutboundPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return StArpOutbound.class;
	}

	@Override
	public String getModelClassName() {
		return StArpOutbound.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("salesUnitsRate", getSalesUnitsRate());
		attributes.put("accountType", getAccountType());
		attributes.put("originalBatchId", getOriginalBatchId());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("arpApprovalDate", getArpApprovalDate());
		attributes.put("userId", getUserId());
		attributes.put("arpMasterSid", getArpMasterSid());
		attributes.put("arpCreationDate", getArpCreationDate());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("arpId", getArpId());
		attributes.put("account", getAccount());
		attributes.put("outboundStatus", getOutboundStatus());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("sessionId", getSessionId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double salesUnitsRate = (Double)attributes.get("salesUnitsRate");

		if (salesUnitsRate != null) {
			setSalesUnitsRate(salesUnitsRate);
		}

		String accountType = (String)attributes.get("accountType");

		if (accountType != null) {
			setAccountType(accountType);
		}

		String originalBatchId = (String)attributes.get("originalBatchId");

		if (originalBatchId != null) {
			setOriginalBatchId(originalBatchId);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		Date arpApprovalDate = (Date)attributes.get("arpApprovalDate");

		if (arpApprovalDate != null) {
			setArpApprovalDate(arpApprovalDate);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer arpMasterSid = (Integer)attributes.get("arpMasterSid");

		if (arpMasterSid != null) {
			setArpMasterSid(arpMasterSid);
		}

		Date arpCreationDate = (Date)attributes.get("arpCreationDate");

		if (arpCreationDate != null) {
			setArpCreationDate(arpCreationDate);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		Integer arpId = (Integer)attributes.get("arpId");

		if (arpId != null) {
			setArpId(arpId);
		}

		String account = (String)attributes.get("account");

		if (account != null) {
			setAccount(account);
		}

		Boolean outboundStatus = (Boolean)attributes.get("outboundStatus");

		if (outboundStatus != null) {
			setOutboundStatus(outboundStatus);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}
	}

	@Override
	public double getSalesUnitsRate() {
		return _salesUnitsRate;
	}

	@Override
	public void setSalesUnitsRate(double salesUnitsRate) {
		_salesUnitsRate = salesUnitsRate;
	}

	@Override
	public String getAccountType() {
		if (_accountType == null) {
			return StringPool.BLANK;
		}
		else {
			return _accountType;
		}
	}

	@Override
	public void setAccountType(String accountType) {
		_accountType = accountType;
	}

	@Override
	public String getOriginalBatchId() {
		if (_originalBatchId == null) {
			return StringPool.BLANK;
		}
		else {
			return _originalBatchId;
		}
	}

	@Override
	public void setOriginalBatchId(String originalBatchId) {
		_originalBatchId = originalBatchId;
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
	public int getBrandMasterSid() {
		return _brandMasterSid;
	}

	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_brandMasterSid = brandMasterSid;
	}

	@Override
	public Date getArpApprovalDate() {
		return _arpApprovalDate;
	}

	@Override
	public void setArpApprovalDate(Date arpApprovalDate) {
		_arpApprovalDate = arpApprovalDate;
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
	public int getArpMasterSid() {
		return _arpMasterSid;
	}

	@Override
	public void setArpMasterSid(int arpMasterSid) {
		_arpMasterSid = arpMasterSid;
	}

	@Override
	public Date getArpCreationDate() {
		return _arpCreationDate;
	}

	@Override
	public void setArpCreationDate(Date arpCreationDate) {
		_arpCreationDate = arpCreationDate;
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
	public int getArpId() {
		return _arpId;
	}

	@Override
	public void setArpId(int arpId) {
		_arpId = arpId;
	}

	@Override
	public String getAccount() {
		if (_account == null) {
			return StringPool.BLANK;
		}
		else {
			return _account;
		}
	}

	@Override
	public void setAccount(String account) {
		_account = account;
	}

	@Override
	public boolean getOutboundStatus() {
		return _outboundStatus;
	}

	@Override
	public boolean isOutboundStatus() {
		return _outboundStatus;
	}

	@Override
	public void setOutboundStatus(boolean outboundStatus) {
		_outboundStatus = outboundStatus;
	}

	@Override
	public int getPeriodSid() {
		return _periodSid;
	}

	@Override
	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
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
	public int getRsModelSid() {
		return _rsModelSid;
	}

	@Override
	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
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
	public StArpOutbound toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (StArpOutbound)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StArpOutboundImpl stArpOutboundImpl = new StArpOutboundImpl();

		stArpOutboundImpl.setSalesUnitsRate(getSalesUnitsRate());
		stArpOutboundImpl.setAccountType(getAccountType());
		stArpOutboundImpl.setOriginalBatchId(getOriginalBatchId());
		stArpOutboundImpl.setCompanyMasterSid(getCompanyMasterSid());
		stArpOutboundImpl.setBrandMasterSid(getBrandMasterSid());
		stArpOutboundImpl.setArpApprovalDate(getArpApprovalDate());
		stArpOutboundImpl.setUserId(getUserId());
		stArpOutboundImpl.setArpMasterSid(getArpMasterSid());
		stArpOutboundImpl.setArpCreationDate(getArpCreationDate());
		stArpOutboundImpl.setCheckRecord(getCheckRecord());
		stArpOutboundImpl.setArpId(getArpId());
		stArpOutboundImpl.setAccount(getAccount());
		stArpOutboundImpl.setOutboundStatus(getOutboundStatus());
		stArpOutboundImpl.setPeriodSid(getPeriodSid());
		stArpOutboundImpl.setItemMasterSid(getItemMasterSid());
		stArpOutboundImpl.setRsModelSid(getRsModelSid());
		stArpOutboundImpl.setSessionId(getSessionId());

		stArpOutboundImpl.resetOriginalValues();

		return stArpOutboundImpl;
	}

	@Override
	public int compareTo(StArpOutbound stArpOutbound) {
		StArpOutboundPK primaryKey = stArpOutbound.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StArpOutbound)) {
			return false;
		}

		StArpOutbound stArpOutbound = (StArpOutbound)obj;

		StArpOutboundPK primaryKey = stArpOutbound.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
	public CacheModel<StArpOutbound> toCacheModel() {
		StArpOutboundCacheModel stArpOutboundCacheModel = new StArpOutboundCacheModel();

		stArpOutboundCacheModel.stArpOutboundPK = getPrimaryKey();

		stArpOutboundCacheModel.salesUnitsRate = getSalesUnitsRate();

		stArpOutboundCacheModel.accountType = getAccountType();

		String accountType = stArpOutboundCacheModel.accountType;

		if ((accountType != null) && (accountType.length() == 0)) {
			stArpOutboundCacheModel.accountType = null;
		}

		stArpOutboundCacheModel.originalBatchId = getOriginalBatchId();

		String originalBatchId = stArpOutboundCacheModel.originalBatchId;

		if ((originalBatchId != null) && (originalBatchId.length() == 0)) {
			stArpOutboundCacheModel.originalBatchId = null;
		}

		stArpOutboundCacheModel.companyMasterSid = getCompanyMasterSid();

		stArpOutboundCacheModel.brandMasterSid = getBrandMasterSid();

		Date arpApprovalDate = getArpApprovalDate();

		if (arpApprovalDate != null) {
			stArpOutboundCacheModel.arpApprovalDate = arpApprovalDate.getTime();
		}
		else {
			stArpOutboundCacheModel.arpApprovalDate = Long.MIN_VALUE;
		}

		stArpOutboundCacheModel.userId = getUserId();

		stArpOutboundCacheModel.arpMasterSid = getArpMasterSid();

		Date arpCreationDate = getArpCreationDate();

		if (arpCreationDate != null) {
			stArpOutboundCacheModel.arpCreationDate = arpCreationDate.getTime();
		}
		else {
			stArpOutboundCacheModel.arpCreationDate = Long.MIN_VALUE;
		}

		stArpOutboundCacheModel.checkRecord = getCheckRecord();

		stArpOutboundCacheModel.arpId = getArpId();

		stArpOutboundCacheModel.account = getAccount();

		String account = stArpOutboundCacheModel.account;

		if ((account != null) && (account.length() == 0)) {
			stArpOutboundCacheModel.account = null;
		}

		stArpOutboundCacheModel.outboundStatus = getOutboundStatus();

		stArpOutboundCacheModel.periodSid = getPeriodSid();

		stArpOutboundCacheModel.itemMasterSid = getItemMasterSid();

		stArpOutboundCacheModel.rsModelSid = getRsModelSid();

		stArpOutboundCacheModel.sessionId = getSessionId();

		String sessionId = stArpOutboundCacheModel.sessionId;

		if ((sessionId != null) && (sessionId.length() == 0)) {
			stArpOutboundCacheModel.sessionId = null;
		}

		return stArpOutboundCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{salesUnitsRate=");
		sb.append(getSalesUnitsRate());
		sb.append(", accountType=");
		sb.append(getAccountType());
		sb.append(", originalBatchId=");
		sb.append(getOriginalBatchId());
		sb.append(", companyMasterSid=");
		sb.append(getCompanyMasterSid());
		sb.append(", brandMasterSid=");
		sb.append(getBrandMasterSid());
		sb.append(", arpApprovalDate=");
		sb.append(getArpApprovalDate());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", arpMasterSid=");
		sb.append(getArpMasterSid());
		sb.append(", arpCreationDate=");
		sb.append(getArpCreationDate());
		sb.append(", checkRecord=");
		sb.append(getCheckRecord());
		sb.append(", arpId=");
		sb.append(getArpId());
		sb.append(", account=");
		sb.append(getAccount());
		sb.append(", outboundStatus=");
		sb.append(getOutboundStatus());
		sb.append(", periodSid=");
		sb.append(getPeriodSid());
		sb.append(", itemMasterSid=");
		sb.append(getItemMasterSid());
		sb.append(", rsModelSid=");
		sb.append(getRsModelSid());
		sb.append(", sessionId=");
		sb.append(getSessionId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.parttwo.model.StArpOutbound");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>salesUnitsRate</column-name><column-value><![CDATA[");
		sb.append(getSalesUnitsRate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountType</column-name><column-value><![CDATA[");
		sb.append(getAccountType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>originalBatchId</column-name><column-value><![CDATA[");
		sb.append(getOriginalBatchId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
		sb.append(getCompanyMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
		sb.append(getBrandMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>arpApprovalDate</column-name><column-value><![CDATA[");
		sb.append(getArpApprovalDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>arpMasterSid</column-name><column-value><![CDATA[");
		sb.append(getArpMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>arpCreationDate</column-name><column-value><![CDATA[");
		sb.append(getArpCreationDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>checkRecord</column-name><column-value><![CDATA[");
		sb.append(getCheckRecord());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>arpId</column-name><column-value><![CDATA[");
		sb.append(getArpId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>account</column-name><column-value><![CDATA[");
		sb.append(getAccount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outboundStatus</column-name><column-value><![CDATA[");
		sb.append(getOutboundStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>periodSid</column-name><column-value><![CDATA[");
		sb.append(getPeriodSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
		sb.append(getItemMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
		sb.append(getRsModelSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionId</column-name><column-value><![CDATA[");
		sb.append(getSessionId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = StArpOutbound.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			StArpOutbound.class
		};
	private double _salesUnitsRate;
	private String _accountType;
	private String _originalBatchId;
	private int _companyMasterSid;
	private int _brandMasterSid;
	private Date _arpApprovalDate;
	private int _userId;
	private int _arpMasterSid;
	private Date _arpCreationDate;
	private boolean _checkRecord;
	private int _arpId;
	private String _account;
	private boolean _outboundStatus;
	private int _periodSid;
	private int _itemMasterSid;
	private int _rsModelSid;
	private String _sessionId;
	private StArpOutbound _escapedModel;
}