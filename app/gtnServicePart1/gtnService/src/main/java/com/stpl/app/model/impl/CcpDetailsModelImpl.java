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

import com.stpl.app.model.CcpDetails;
import com.stpl.app.model.CcpDetailsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the CcpDetails service. Represents a row in the &quot;CCP_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CcpDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CcpDetailsImpl}.
 * </p>
 *
 * @author
 * @see CcpDetailsImpl
 * @see CcpDetails
 * @see CcpDetailsModel
 * @generated
 */
@ProviderType
public class CcpDetailsModelImpl extends BaseModelImpl<CcpDetails>
	implements CcpDetailsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ccp details model instance should use the {@link CcpDetails} interface instead.
	 */
	public static final String TABLE_NAME = "CCP_DETAILS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "ITEM_MASTER_SID", Types.INTEGER },
			{ "CONTRACT_MASTER_SID", Types.INTEGER },
			{ "CCP_DETAILS_SID", Types.INTEGER },
			{ "COMPANY_MASTER_SID", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("ITEM_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CONTRACT_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CCP_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("COMPANY_MASTER_SID", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table CCP_DETAILS (ITEM_MASTER_SID INTEGER,CONTRACT_MASTER_SID INTEGER,CCP_DETAILS_SID INTEGER not null primary key IDENTITY,COMPANY_MASTER_SID INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table CCP_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY ccpDetails.ccpDetailsSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CCP_DETAILS.CCP_DETAILS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.CcpDetails"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.CcpDetails"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.CcpDetails"));

	public CcpDetailsModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _ccpDetailsSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setCcpDetailsSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ccpDetailsSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CcpDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CcpDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("ccpDetailsSid", getCcpDetailsSid());
		attributes.put("companyMasterSid", getCompanyMasterSid());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer ccpDetailsSid = (Integer)attributes.get("ccpDetailsSid");

		if (ccpDetailsSid != null) {
			setCcpDetailsSid(ccpDetailsSid);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}
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
	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	@Override
	public int getCcpDetailsSid() {
		return _ccpDetailsSid;
	}

	@Override
	public void setCcpDetailsSid(int ccpDetailsSid) {
		_ccpDetailsSid = ccpDetailsSid;
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
	public CcpDetails toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CcpDetails)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CcpDetailsImpl ccpDetailsImpl = new CcpDetailsImpl();

		ccpDetailsImpl.setItemMasterSid(getItemMasterSid());
		ccpDetailsImpl.setContractMasterSid(getContractMasterSid());
		ccpDetailsImpl.setCcpDetailsSid(getCcpDetailsSid());
		ccpDetailsImpl.setCompanyMasterSid(getCompanyMasterSid());

		ccpDetailsImpl.resetOriginalValues();

		return ccpDetailsImpl;
	}

	@Override
	public int compareTo(CcpDetails ccpDetails) {
		int primaryKey = ccpDetails.getPrimaryKey();

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

		if (!(obj instanceof CcpDetails)) {
			return false;
		}

		CcpDetails ccpDetails = (CcpDetails)obj;

		int primaryKey = ccpDetails.getPrimaryKey();

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
	public CacheModel<CcpDetails> toCacheModel() {
		CcpDetailsCacheModel ccpDetailsCacheModel = new CcpDetailsCacheModel();

		ccpDetailsCacheModel.itemMasterSid = getItemMasterSid();

		ccpDetailsCacheModel.contractMasterSid = getContractMasterSid();

		ccpDetailsCacheModel.ccpDetailsSid = getCcpDetailsSid();

		ccpDetailsCacheModel.companyMasterSid = getCompanyMasterSid();

		return ccpDetailsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{itemMasterSid=");
		sb.append(getItemMasterSid());
		sb.append(", contractMasterSid=");
		sb.append(getContractMasterSid());
		sb.append(", ccpDetailsSid=");
		sb.append(getCcpDetailsSid());
		sb.append(", companyMasterSid=");
		sb.append(getCompanyMasterSid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.CcpDetails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
		sb.append(getItemMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
		sb.append(getContractMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ccpDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getCcpDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
		sb.append(getCompanyMasterSid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CcpDetails.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CcpDetails.class
		};
	private int _itemMasterSid;
	private int _contractMasterSid;
	private int _ccpDetailsSid;
	private int _companyMasterSid;
	private CcpDetails _escapedModel;
}