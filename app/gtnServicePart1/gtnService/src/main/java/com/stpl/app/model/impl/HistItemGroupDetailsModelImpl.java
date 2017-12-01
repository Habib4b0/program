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

import com.stpl.app.model.HistItemGroupDetails;
import com.stpl.app.model.HistItemGroupDetailsModel;
import com.stpl.app.service.persistence.HistItemGroupDetailsPK;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the HistItemGroupDetails service. Represents a row in the &quot;HIST_ITEM_GROUP_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link HistItemGroupDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HistItemGroupDetailsImpl}.
 * </p>
 *
 * @author
 * @see HistItemGroupDetailsImpl
 * @see HistItemGroupDetails
 * @see HistItemGroupDetailsModel
 * @generated
 */
@ProviderType
public class HistItemGroupDetailsModelImpl extends BaseModelImpl<HistItemGroupDetails>
	implements HistItemGroupDetailsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a hist item group details model instance should use the {@link HistItemGroupDetails} interface instead.
	 */
	public static final String TABLE_NAME = "HIST_ITEM_GROUP_DETAILS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "ITEM_GROUP_DETAILS_SID", Types.INTEGER },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "CREATED_BY", Types.INTEGER },
			{ "ACTION_DATE", Types.TIMESTAMP },
			{ "ITEM_MASTER_SID", Types.INTEGER },
			{ "ACTION_FLAG", Types.VARCHAR },
			{ "VERSION_NO", Types.INTEGER },
			{ "MODIFIED_BY", Types.INTEGER },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "ITEM_GROUP_SID", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("ITEM_GROUP_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ACTION_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ITEM_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ACTION_FLAG", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("VERSION_NO", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ITEM_GROUP_SID", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table HIST_ITEM_GROUP_DETAILS (ITEM_GROUP_DETAILS_SID INTEGER not null IDENTITY,CREATED_DATE DATE null,CREATED_BY INTEGER,ACTION_DATE DATE null,ITEM_MASTER_SID INTEGER,ACTION_FLAG VARCHAR(75) not null,VERSION_NO INTEGER not null IDENTITY,MODIFIED_BY INTEGER,MODIFIED_DATE DATE null,ITEM_GROUP_SID INTEGER,primary key (ITEM_GROUP_DETAILS_SID, ACTION_FLAG, VERSION_NO))";
	public static final String TABLE_SQL_DROP = "drop table HIST_ITEM_GROUP_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY histItemGroupDetails.id.itemGroupDetailsSid ASC, histItemGroupDetails.id.actionFlag ASC, histItemGroupDetails.id.versionNo ASC";
	public static final String ORDER_BY_SQL = " ORDER BY HIST_ITEM_GROUP_DETAILS.ITEM_GROUP_DETAILS_SID ASC, HIST_ITEM_GROUP_DETAILS.ACTION_FLAG ASC, HIST_ITEM_GROUP_DETAILS.VERSION_NO ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.HistItemGroupDetails"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.HistItemGroupDetails"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.HistItemGroupDetails"));

	public HistItemGroupDetailsModelImpl() {
	}

	@Override
	public HistItemGroupDetailsPK getPrimaryKey() {
		return new HistItemGroupDetailsPK(_itemGroupDetailsSid, _actionFlag,
			_versionNo);
	}

	@Override
	public void setPrimaryKey(HistItemGroupDetailsPK primaryKey) {
		setItemGroupDetailsSid(primaryKey.itemGroupDetailsSid);
		setActionFlag(primaryKey.actionFlag);
		setVersionNo(primaryKey.versionNo);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new HistItemGroupDetailsPK(_itemGroupDetailsSid, _actionFlag,
			_versionNo);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((HistItemGroupDetailsPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return HistItemGroupDetails.class;
	}

	@Override
	public String getModelClassName() {
		return HistItemGroupDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemGroupDetailsSid", getItemGroupDetailsSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("actionDate", getActionDate());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemGroupSid", getItemGroupSid());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemGroupDetailsSid = (Integer)attributes.get(
				"itemGroupDetailsSid");

		if (itemGroupDetailsSid != null) {
			setItemGroupDetailsSid(itemGroupDetailsSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer itemGroupSid = (Integer)attributes.get("itemGroupSid");

		if (itemGroupSid != null) {
			setItemGroupSid(itemGroupSid);
		}
	}

	@Override
	public int getItemGroupDetailsSid() {
		return _itemGroupDetailsSid;
	}

	@Override
	public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
		_itemGroupDetailsSid = itemGroupDetailsSid;
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
	public Date getActionDate() {
		return _actionDate;
	}

	@Override
	public void setActionDate(Date actionDate) {
		_actionDate = actionDate;
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
	public String getActionFlag() {
		if (_actionFlag == null) {
			return StringPool.BLANK;
		}
		else {
			return _actionFlag;
		}
	}

	@Override
	public void setActionFlag(String actionFlag) {
		_actionFlag = actionFlag;
	}

	@Override
	public int getVersionNo() {
		return _versionNo;
	}

	@Override
	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
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
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public int getItemGroupSid() {
		return _itemGroupSid;
	}

	@Override
	public void setItemGroupSid(int itemGroupSid) {
		_itemGroupSid = itemGroupSid;
	}

	@Override
	public HistItemGroupDetails toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (HistItemGroupDetails)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		HistItemGroupDetailsImpl histItemGroupDetailsImpl = new HistItemGroupDetailsImpl();

		histItemGroupDetailsImpl.setItemGroupDetailsSid(getItemGroupDetailsSid());
		histItemGroupDetailsImpl.setCreatedDate(getCreatedDate());
		histItemGroupDetailsImpl.setCreatedBy(getCreatedBy());
		histItemGroupDetailsImpl.setActionDate(getActionDate());
		histItemGroupDetailsImpl.setItemMasterSid(getItemMasterSid());
		histItemGroupDetailsImpl.setActionFlag(getActionFlag());
		histItemGroupDetailsImpl.setVersionNo(getVersionNo());
		histItemGroupDetailsImpl.setModifiedBy(getModifiedBy());
		histItemGroupDetailsImpl.setModifiedDate(getModifiedDate());
		histItemGroupDetailsImpl.setItemGroupSid(getItemGroupSid());

		histItemGroupDetailsImpl.resetOriginalValues();

		return histItemGroupDetailsImpl;
	}

	@Override
	public int compareTo(HistItemGroupDetails histItemGroupDetails) {
		HistItemGroupDetailsPK primaryKey = histItemGroupDetails.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistItemGroupDetails)) {
			return false;
		}

		HistItemGroupDetails histItemGroupDetails = (HistItemGroupDetails)obj;

		HistItemGroupDetailsPK primaryKey = histItemGroupDetails.getPrimaryKey();

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
	public CacheModel<HistItemGroupDetails> toCacheModel() {
		HistItemGroupDetailsCacheModel histItemGroupDetailsCacheModel = new HistItemGroupDetailsCacheModel();

		histItemGroupDetailsCacheModel.histItemGroupDetailsPK = getPrimaryKey();

		histItemGroupDetailsCacheModel.itemGroupDetailsSid = getItemGroupDetailsSid();

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			histItemGroupDetailsCacheModel.createdDate = createdDate.getTime();
		}
		else {
			histItemGroupDetailsCacheModel.createdDate = Long.MIN_VALUE;
		}

		histItemGroupDetailsCacheModel.createdBy = getCreatedBy();

		Date actionDate = getActionDate();

		if (actionDate != null) {
			histItemGroupDetailsCacheModel.actionDate = actionDate.getTime();
		}
		else {
			histItemGroupDetailsCacheModel.actionDate = Long.MIN_VALUE;
		}

		histItemGroupDetailsCacheModel.itemMasterSid = getItemMasterSid();

		histItemGroupDetailsCacheModel.actionFlag = getActionFlag();

		String actionFlag = histItemGroupDetailsCacheModel.actionFlag;

		if ((actionFlag != null) && (actionFlag.length() == 0)) {
			histItemGroupDetailsCacheModel.actionFlag = null;
		}

		histItemGroupDetailsCacheModel.versionNo = getVersionNo();

		histItemGroupDetailsCacheModel.modifiedBy = getModifiedBy();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			histItemGroupDetailsCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			histItemGroupDetailsCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		histItemGroupDetailsCacheModel.itemGroupSid = getItemGroupSid();

		return histItemGroupDetailsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{itemGroupDetailsSid=");
		sb.append(getItemGroupDetailsSid());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", actionDate=");
		sb.append(getActionDate());
		sb.append(", itemMasterSid=");
		sb.append(getItemMasterSid());
		sb.append(", actionFlag=");
		sb.append(getActionFlag());
		sb.append(", versionNo=");
		sb.append(getVersionNo());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", itemGroupSid=");
		sb.append(getItemGroupSid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.HistItemGroupDetails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>itemGroupDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getItemGroupDetailsSid());
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
			"<column><column-name>actionDate</column-name><column-value><![CDATA[");
		sb.append(getActionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
		sb.append(getItemMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actionFlag</column-name><column-value><![CDATA[");
		sb.append(getActionFlag());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>versionNo</column-name><column-value><![CDATA[");
		sb.append(getVersionNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemGroupSid</column-name><column-value><![CDATA[");
		sb.append(getItemGroupSid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = HistItemGroupDetails.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			HistItemGroupDetails.class
		};
	private int _itemGroupDetailsSid;
	private Date _createdDate;
	private int _createdBy;
	private Date _actionDate;
	private int _itemMasterSid;
	private String _actionFlag;
	private int _versionNo;
	private int _modifiedBy;
	private Date _modifiedDate;
	private int _itemGroupSid;
	private HistItemGroupDetails _escapedModel;
}