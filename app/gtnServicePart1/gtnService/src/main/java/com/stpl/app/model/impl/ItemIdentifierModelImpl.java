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

import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.model.ItemIdentifierModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ItemIdentifier service. Represents a row in the &quot;ITEM_IDENTIFIER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ItemIdentifierModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ItemIdentifierImpl}.
 * </p>
 *
 * @author
 * @see ItemIdentifierImpl
 * @see ItemIdentifier
 * @see ItemIdentifierModel
 * @generated
 */
@ProviderType
public class ItemIdentifierModelImpl extends BaseModelImpl<ItemIdentifier>
	implements ItemIdentifierModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a item identifier model instance should use the {@link ItemIdentifier} interface instead.
	 */
	public static final String TABLE_NAME = "ITEM_IDENTIFIER";
	public static final Object[][] TABLE_COLUMNS = {
			{ "ITEM_IDENTIFIER_SID", Types.INTEGER },
			{ "ITEM_MASTER_SID", Types.INTEGER },
			{ "END_DATE", Types.TIMESTAMP },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "IDENTIFIER_STATUS", Types.INTEGER },
			{ "ENTITY_CODE", Types.VARCHAR },
			{ "ITEM_IDENTIFIER_VALUE", Types.VARCHAR },
			{ "RECORD_LOCK_STATUS", Types.BOOLEAN },
			{ "ITEM_QUALIFIER_SID", Types.INTEGER },
			{ "START_DATE", Types.TIMESTAMP },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "SOURCE", Types.VARCHAR },
			{ "CREATED_BY", Types.INTEGER },
			{ "BATCH_ID", Types.VARCHAR },
			{ "MODIFIED_BY", Types.INTEGER },
			{ "INBOUND_STATUS", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("ITEM_IDENTIFIER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ITEM_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("END_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("IDENTIFIER_STATUS", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ENTITY_CODE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ITEM_IDENTIFIER_VALUE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("RECORD_LOCK_STATUS", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("ITEM_QUALIFIER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("START_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("SOURCE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("BATCH_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("INBOUND_STATUS", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table ITEM_IDENTIFIER (ITEM_IDENTIFIER_SID INTEGER not null primary key IDENTITY,ITEM_MASTER_SID INTEGER,END_DATE DATE null,MODIFIED_DATE DATE null,IDENTIFIER_STATUS INTEGER,ENTITY_CODE VARCHAR(75) null,ITEM_IDENTIFIER_VALUE VARCHAR(75) null,RECORD_LOCK_STATUS BOOLEAN,ITEM_QUALIFIER_SID INTEGER,START_DATE DATE null,CREATED_DATE DATE null,SOURCE VARCHAR(75) null,CREATED_BY INTEGER,BATCH_ID VARCHAR(75) null,MODIFIED_BY INTEGER,INBOUND_STATUS VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table ITEM_IDENTIFIER";
	public static final String ORDER_BY_JPQL = " ORDER BY itemIdentifier.itemIdentifierSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ITEM_IDENTIFIER.ITEM_IDENTIFIER_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.ItemIdentifier"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.ItemIdentifier"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.stpl.app.model.ItemIdentifier"),
			true);
	public static final long IDENTIFIERSTATUS_COLUMN_BITMASK = 1L;
	public static final long ITEMIDENTIFIERVALUE_COLUMN_BITMASK = 2L;
	public static final long ITEMMASTERSID_COLUMN_BITMASK = 4L;
	public static final long ITEMQUALIFIERSID_COLUMN_BITMASK = 8L;
	public static final long STARTDATE_COLUMN_BITMASK = 16L;
	public static final long ITEMIDENTIFIERSID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.ItemIdentifier"));

	public ItemIdentifierModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _itemIdentifierSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setItemIdentifierSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemIdentifierSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ItemIdentifier.class;
	}

	@Override
	public String getModelClassName() {
		return ItemIdentifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemIdentifierSid", getItemIdentifierSid());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("endDate", getEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("identifierStatus", getIdentifierStatus());
		attributes.put("entityCode", getEntityCode());
		attributes.put("itemIdentifierValue", getItemIdentifierValue());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("itemQualifierSid", getItemQualifierSid());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemIdentifierSid = (Integer)attributes.get("itemIdentifierSid");

		if (itemIdentifierSid != null) {
			setItemIdentifierSid(itemIdentifierSid);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer identifierStatus = (Integer)attributes.get("identifierStatus");

		if (identifierStatus != null) {
			setIdentifierStatus(identifierStatus);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		String itemIdentifierValue = (String)attributes.get(
				"itemIdentifierValue");

		if (itemIdentifierValue != null) {
			setItemIdentifierValue(itemIdentifierValue);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Integer itemQualifierSid = (Integer)attributes.get("itemQualifierSid");

		if (itemQualifierSid != null) {
			setItemQualifierSid(itemQualifierSid);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public int getItemIdentifierSid() {
		return _itemIdentifierSid;
	}

	@Override
	public void setItemIdentifierSid(int itemIdentifierSid) {
		_itemIdentifierSid = itemIdentifierSid;
	}

	@Override
	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_columnBitmask |= ITEMMASTERSID_COLUMN_BITMASK;

		if (!_setOriginalItemMasterSid) {
			_setOriginalItemMasterSid = true;

			_originalItemMasterSid = _itemMasterSid;
		}

		_itemMasterSid = itemMasterSid;
	}

	public int getOriginalItemMasterSid() {
		return _originalItemMasterSid;
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
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
	public int getIdentifierStatus() {
		return _identifierStatus;
	}

	@Override
	public void setIdentifierStatus(int identifierStatus) {
		_columnBitmask |= IDENTIFIERSTATUS_COLUMN_BITMASK;

		if (!_setOriginalIdentifierStatus) {
			_setOriginalIdentifierStatus = true;

			_originalIdentifierStatus = _identifierStatus;
		}

		_identifierStatus = identifierStatus;
	}

	public int getOriginalIdentifierStatus() {
		return _originalIdentifierStatus;
	}

	@Override
	public String getEntityCode() {
		if (_entityCode == null) {
			return StringPool.BLANK;
		}
		else {
			return _entityCode;
		}
	}

	@Override
	public void setEntityCode(String entityCode) {
		_entityCode = entityCode;
	}

	@Override
	public String getItemIdentifierValue() {
		if (_itemIdentifierValue == null) {
			return StringPool.BLANK;
		}
		else {
			return _itemIdentifierValue;
		}
	}

	@Override
	public void setItemIdentifierValue(String itemIdentifierValue) {
		_columnBitmask |= ITEMIDENTIFIERVALUE_COLUMN_BITMASK;

		if (_originalItemIdentifierValue == null) {
			_originalItemIdentifierValue = _itemIdentifierValue;
		}

		_itemIdentifierValue = itemIdentifierValue;
	}

	public String getOriginalItemIdentifierValue() {
		return GetterUtil.getString(_originalItemIdentifierValue);
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
	public int getItemQualifierSid() {
		return _itemQualifierSid;
	}

	@Override
	public void setItemQualifierSid(int itemQualifierSid) {
		_columnBitmask |= ITEMQUALIFIERSID_COLUMN_BITMASK;

		if (!_setOriginalItemQualifierSid) {
			_setOriginalItemQualifierSid = true;

			_originalItemQualifierSid = _itemQualifierSid;
		}

		_itemQualifierSid = itemQualifierSid;
	}

	public int getOriginalItemQualifierSid() {
		return _originalItemQualifierSid;
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_columnBitmask |= STARTDATE_COLUMN_BITMASK;

		if (_originalStartDate == null) {
			_originalStartDate = _startDate;
		}

		_startDate = startDate;
	}

	public Date getOriginalStartDate() {
		return _originalStartDate;
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
	public int getCreatedBy() {
		return _createdBy;
	}

	@Override
	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ItemIdentifier toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ItemIdentifier)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ItemIdentifierImpl itemIdentifierImpl = new ItemIdentifierImpl();

		itemIdentifierImpl.setItemIdentifierSid(getItemIdentifierSid());
		itemIdentifierImpl.setItemMasterSid(getItemMasterSid());
		itemIdentifierImpl.setEndDate(getEndDate());
		itemIdentifierImpl.setModifiedDate(getModifiedDate());
		itemIdentifierImpl.setIdentifierStatus(getIdentifierStatus());
		itemIdentifierImpl.setEntityCode(getEntityCode());
		itemIdentifierImpl.setItemIdentifierValue(getItemIdentifierValue());
		itemIdentifierImpl.setRecordLockStatus(getRecordLockStatus());
		itemIdentifierImpl.setItemQualifierSid(getItemQualifierSid());
		itemIdentifierImpl.setStartDate(getStartDate());
		itemIdentifierImpl.setCreatedDate(getCreatedDate());
		itemIdentifierImpl.setSource(getSource());
		itemIdentifierImpl.setCreatedBy(getCreatedBy());
		itemIdentifierImpl.setBatchId(getBatchId());
		itemIdentifierImpl.setModifiedBy(getModifiedBy());
		itemIdentifierImpl.setInboundStatus(getInboundStatus());

		itemIdentifierImpl.resetOriginalValues();

		return itemIdentifierImpl;
	}

	@Override
	public int compareTo(ItemIdentifier itemIdentifier) {
		int primaryKey = itemIdentifier.getPrimaryKey();

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

		if (!(obj instanceof ItemIdentifier)) {
			return false;
		}

		ItemIdentifier itemIdentifier = (ItemIdentifier)obj;

		int primaryKey = itemIdentifier.getPrimaryKey();

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
		ItemIdentifierModelImpl itemIdentifierModelImpl = this;

		itemIdentifierModelImpl._originalItemMasterSid = itemIdentifierModelImpl._itemMasterSid;

		itemIdentifierModelImpl._setOriginalItemMasterSid = false;

		itemIdentifierModelImpl._originalIdentifierStatus = itemIdentifierModelImpl._identifierStatus;

		itemIdentifierModelImpl._setOriginalIdentifierStatus = false;

		itemIdentifierModelImpl._originalItemIdentifierValue = itemIdentifierModelImpl._itemIdentifierValue;

		itemIdentifierModelImpl._originalItemQualifierSid = itemIdentifierModelImpl._itemQualifierSid;

		itemIdentifierModelImpl._setOriginalItemQualifierSid = false;

		itemIdentifierModelImpl._originalStartDate = itemIdentifierModelImpl._startDate;

		itemIdentifierModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ItemIdentifier> toCacheModel() {
		ItemIdentifierCacheModel itemIdentifierCacheModel = new ItemIdentifierCacheModel();

		itemIdentifierCacheModel.itemIdentifierSid = getItemIdentifierSid();

		itemIdentifierCacheModel.itemMasterSid = getItemMasterSid();

		Date endDate = getEndDate();

		if (endDate != null) {
			itemIdentifierCacheModel.endDate = endDate.getTime();
		}
		else {
			itemIdentifierCacheModel.endDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			itemIdentifierCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			itemIdentifierCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		itemIdentifierCacheModel.identifierStatus = getIdentifierStatus();

		itemIdentifierCacheModel.entityCode = getEntityCode();

		String entityCode = itemIdentifierCacheModel.entityCode;

		if ((entityCode != null) && (entityCode.length() == 0)) {
			itemIdentifierCacheModel.entityCode = null;
		}

		itemIdentifierCacheModel.itemIdentifierValue = getItemIdentifierValue();

		String itemIdentifierValue = itemIdentifierCacheModel.itemIdentifierValue;

		if ((itemIdentifierValue != null) &&
				(itemIdentifierValue.length() == 0)) {
			itemIdentifierCacheModel.itemIdentifierValue = null;
		}

		itemIdentifierCacheModel.recordLockStatus = getRecordLockStatus();

		itemIdentifierCacheModel.itemQualifierSid = getItemQualifierSid();

		Date startDate = getStartDate();

		if (startDate != null) {
			itemIdentifierCacheModel.startDate = startDate.getTime();
		}
		else {
			itemIdentifierCacheModel.startDate = Long.MIN_VALUE;
		}

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			itemIdentifierCacheModel.createdDate = createdDate.getTime();
		}
		else {
			itemIdentifierCacheModel.createdDate = Long.MIN_VALUE;
		}

		itemIdentifierCacheModel.source = getSource();

		String source = itemIdentifierCacheModel.source;

		if ((source != null) && (source.length() == 0)) {
			itemIdentifierCacheModel.source = null;
		}

		itemIdentifierCacheModel.createdBy = getCreatedBy();

		itemIdentifierCacheModel.batchId = getBatchId();

		String batchId = itemIdentifierCacheModel.batchId;

		if ((batchId != null) && (batchId.length() == 0)) {
			itemIdentifierCacheModel.batchId = null;
		}

		itemIdentifierCacheModel.modifiedBy = getModifiedBy();

		itemIdentifierCacheModel.inboundStatus = getInboundStatus();

		String inboundStatus = itemIdentifierCacheModel.inboundStatus;

		if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
			itemIdentifierCacheModel.inboundStatus = null;
		}

		return itemIdentifierCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{itemIdentifierSid=");
		sb.append(getItemIdentifierSid());
		sb.append(", itemMasterSid=");
		sb.append(getItemMasterSid());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", identifierStatus=");
		sb.append(getIdentifierStatus());
		sb.append(", entityCode=");
		sb.append(getEntityCode());
		sb.append(", itemIdentifierValue=");
		sb.append(getItemIdentifierValue());
		sb.append(", recordLockStatus=");
		sb.append(getRecordLockStatus());
		sb.append(", itemQualifierSid=");
		sb.append(getItemQualifierSid());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", source=");
		sb.append(getSource());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", batchId=");
		sb.append(getBatchId());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", inboundStatus=");
		sb.append(getInboundStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.ItemIdentifier");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>itemIdentifierSid</column-name><column-value><![CDATA[");
		sb.append(getItemIdentifierSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
		sb.append(getItemMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>identifierStatus</column-name><column-value><![CDATA[");
		sb.append(getIdentifierStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>entityCode</column-name><column-value><![CDATA[");
		sb.append(getEntityCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemIdentifierValue</column-name><column-value><![CDATA[");
		sb.append(getItemIdentifierValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
		sb.append(getRecordLockStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemQualifierSid</column-name><column-value><![CDATA[");
		sb.append(getItemQualifierSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdDate</column-name><column-value><![CDATA[");
		sb.append(getCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>source</column-name><column-value><![CDATA[");
		sb.append(getSource());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>batchId</column-name><column-value><![CDATA[");
		sb.append(getBatchId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
		sb.append(getInboundStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ItemIdentifier.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ItemIdentifier.class
		};
	private int _itemIdentifierSid;
	private int _itemMasterSid;
	private int _originalItemMasterSid;
	private boolean _setOriginalItemMasterSid;
	private Date _endDate;
	private Date _modifiedDate;
	private int _identifierStatus;
	private int _originalIdentifierStatus;
	private boolean _setOriginalIdentifierStatus;
	private String _entityCode;
	private String _itemIdentifierValue;
	private String _originalItemIdentifierValue;
	private boolean _recordLockStatus;
	private int _itemQualifierSid;
	private int _originalItemQualifierSid;
	private boolean _setOriginalItemQualifierSid;
	private Date _startDate;
	private Date _originalStartDate;
	private Date _createdDate;
	private String _source;
	private int _createdBy;
	private String _batchId;
	private int _modifiedBy;
	private String _inboundStatus;
	private long _columnBitmask;
	private ItemIdentifier _escapedModel;
}