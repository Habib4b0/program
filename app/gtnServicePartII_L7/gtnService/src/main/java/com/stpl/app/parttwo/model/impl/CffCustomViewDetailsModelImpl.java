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

import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.model.CffCustomViewDetailsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the CffCustomViewDetails service. Represents a row in the &quot;CFF_CUSTOM_VIEW_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CffCustomViewDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CffCustomViewDetailsImpl}.
 * </p>
 *
 * @author
 * @see CffCustomViewDetailsImpl
 * @see CffCustomViewDetails
 * @see CffCustomViewDetailsModel
 * @generated
 */
@ProviderType
public class CffCustomViewDetailsModelImpl extends BaseModelImpl<CffCustomViewDetails>
	implements CffCustomViewDetailsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cff custom view details model instance should use the {@link CffCustomViewDetails} interface instead.
	 */
	public static final String TABLE_NAME = "CFF_CUSTOM_VIEW_DETAILS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "HIERARCHY_ID", Types.INTEGER },
			{ "HIERARCHY_INDICATOR", Types.VARCHAR },
			{ "CFF_CUSTOM_VIEW_DETAILS_SID", Types.INTEGER },
			{ "LEVEL_NO", Types.INTEGER },
			{ "CFF_CUSTOM_VIEW_MASTER_SID", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("HIERARCHY_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("HIERARCHY_INDICATOR", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CFF_CUSTOM_VIEW_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("LEVEL_NO", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CFF_CUSTOM_VIEW_MASTER_SID", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table CFF_CUSTOM_VIEW_DETAILS (HIERARCHY_ID INTEGER,HIERARCHY_INDICATOR VARCHAR(75) null,CFF_CUSTOM_VIEW_DETAILS_SID INTEGER not null primary key IDENTITY,LEVEL_NO INTEGER,CFF_CUSTOM_VIEW_MASTER_SID INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table CFF_CUSTOM_VIEW_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY cffCustomViewDetails.cffCustomViewDetailsSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CFF_CUSTOM_VIEW_DETAILS.CFF_CUSTOM_VIEW_DETAILS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.parttwo.model.CffCustomViewDetails"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.parttwo.model.CffCustomViewDetails"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.parttwo.model.CffCustomViewDetails"));

	public CffCustomViewDetailsModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _cffCustomViewDetailsSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setCffCustomViewDetailsSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffCustomViewDetailsSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CffCustomViewDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CffCustomViewDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("hierarchyId", getHierarchyId());
		attributes.put("hierarchyIndicator", getHierarchyIndicator());
		attributes.put("cffCustomViewDetailsSid", getCffCustomViewDetailsSid());
		attributes.put("levelNo", getLevelNo());
		attributes.put("cffCustomViewMasterSid", getCffCustomViewMasterSid());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer hierarchyId = (Integer)attributes.get("hierarchyId");

		if (hierarchyId != null) {
			setHierarchyId(hierarchyId);
		}

		String hierarchyIndicator = (String)attributes.get("hierarchyIndicator");

		if (hierarchyIndicator != null) {
			setHierarchyIndicator(hierarchyIndicator);
		}

		Integer cffCustomViewDetailsSid = (Integer)attributes.get(
				"cffCustomViewDetailsSid");

		if (cffCustomViewDetailsSid != null) {
			setCffCustomViewDetailsSid(cffCustomViewDetailsSid);
		}

		Integer levelNo = (Integer)attributes.get("levelNo");

		if (levelNo != null) {
			setLevelNo(levelNo);
		}

		Integer cffCustomViewMasterSid = (Integer)attributes.get(
				"cffCustomViewMasterSid");

		if (cffCustomViewMasterSid != null) {
			setCffCustomViewMasterSid(cffCustomViewMasterSid);
		}
	}

	@Override
	public int getHierarchyId() {
		return _hierarchyId;
	}

	@Override
	public void setHierarchyId(int hierarchyId) {
		_hierarchyId = hierarchyId;
	}

	@Override
	public String getHierarchyIndicator() {
		if (_hierarchyIndicator == null) {
			return StringPool.BLANK;
		}
		else {
			return _hierarchyIndicator;
		}
	}

	@Override
	public void setHierarchyIndicator(String hierarchyIndicator) {
		_hierarchyIndicator = hierarchyIndicator;
	}

	@Override
	public int getCffCustomViewDetailsSid() {
		return _cffCustomViewDetailsSid;
	}

	@Override
	public void setCffCustomViewDetailsSid(int cffCustomViewDetailsSid) {
		_cffCustomViewDetailsSid = cffCustomViewDetailsSid;
	}

	@Override
	public int getLevelNo() {
		return _levelNo;
	}

	@Override
	public void setLevelNo(int levelNo) {
		_levelNo = levelNo;
	}

	@Override
	public int getCffCustomViewMasterSid() {
		return _cffCustomViewMasterSid;
	}

	@Override
	public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
		_cffCustomViewMasterSid = cffCustomViewMasterSid;
	}

	@Override
	public CffCustomViewDetails toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CffCustomViewDetails)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CffCustomViewDetailsImpl cffCustomViewDetailsImpl = new CffCustomViewDetailsImpl();

		cffCustomViewDetailsImpl.setHierarchyId(getHierarchyId());
		cffCustomViewDetailsImpl.setHierarchyIndicator(getHierarchyIndicator());
		cffCustomViewDetailsImpl.setCffCustomViewDetailsSid(getCffCustomViewDetailsSid());
		cffCustomViewDetailsImpl.setLevelNo(getLevelNo());
		cffCustomViewDetailsImpl.setCffCustomViewMasterSid(getCffCustomViewMasterSid());

		cffCustomViewDetailsImpl.resetOriginalValues();

		return cffCustomViewDetailsImpl;
	}

	@Override
	public int compareTo(CffCustomViewDetails cffCustomViewDetails) {
		int primaryKey = cffCustomViewDetails.getPrimaryKey();

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

		if (!(obj instanceof CffCustomViewDetails)) {
			return false;
		}

		CffCustomViewDetails cffCustomViewDetails = (CffCustomViewDetails)obj;

		int primaryKey = cffCustomViewDetails.getPrimaryKey();

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
	public CacheModel<CffCustomViewDetails> toCacheModel() {
		CffCustomViewDetailsCacheModel cffCustomViewDetailsCacheModel = new CffCustomViewDetailsCacheModel();

		cffCustomViewDetailsCacheModel.hierarchyId = getHierarchyId();

		cffCustomViewDetailsCacheModel.hierarchyIndicator = getHierarchyIndicator();

		String hierarchyIndicator = cffCustomViewDetailsCacheModel.hierarchyIndicator;

		if ((hierarchyIndicator != null) && (hierarchyIndicator.length() == 0)) {
			cffCustomViewDetailsCacheModel.hierarchyIndicator = null;
		}

		cffCustomViewDetailsCacheModel.cffCustomViewDetailsSid = getCffCustomViewDetailsSid();

		cffCustomViewDetailsCacheModel.levelNo = getLevelNo();

		cffCustomViewDetailsCacheModel.cffCustomViewMasterSid = getCffCustomViewMasterSid();

		return cffCustomViewDetailsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{hierarchyId=");
		sb.append(getHierarchyId());
		sb.append(", hierarchyIndicator=");
		sb.append(getHierarchyIndicator());
		sb.append(", cffCustomViewDetailsSid=");
		sb.append(getCffCustomViewDetailsSid());
		sb.append(", levelNo=");
		sb.append(getLevelNo());
		sb.append(", cffCustomViewMasterSid=");
		sb.append(getCffCustomViewMasterSid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.parttwo.model.CffCustomViewDetails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hierarchyId</column-name><column-value><![CDATA[");
		sb.append(getHierarchyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hierarchyIndicator</column-name><column-value><![CDATA[");
		sb.append(getHierarchyIndicator());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cffCustomViewDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getCffCustomViewDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>levelNo</column-name><column-value><![CDATA[");
		sb.append(getLevelNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cffCustomViewMasterSid</column-name><column-value><![CDATA[");
		sb.append(getCffCustomViewMasterSid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CffCustomViewDetails.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CffCustomViewDetails.class
		};
	private int _hierarchyId;
	private String _hierarchyIndicator;
	private int _cffCustomViewDetailsSid;
	private int _levelNo;
	private int _cffCustomViewMasterSid;
	private CffCustomViewDetails _escapedModel;
}