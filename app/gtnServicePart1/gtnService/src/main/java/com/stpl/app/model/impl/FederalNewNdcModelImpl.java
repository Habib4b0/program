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

import com.stpl.app.model.FederalNewNdc;
import com.stpl.app.model.FederalNewNdcModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the FederalNewNdc service. Represents a row in the &quot;FEDERAL_NEW_NDC&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link FederalNewNdcModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FederalNewNdcImpl}.
 * </p>
 *
 * @author
 * @see FederalNewNdcImpl
 * @see FederalNewNdc
 * @see FederalNewNdcModel
 * @generated
 */
@ProviderType
public class FederalNewNdcModelImpl extends BaseModelImpl<FederalNewNdc>
	implements FederalNewNdcModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a federal new ndc model instance should use the {@link FederalNewNdc} interface instead.
	 */
	public static final String TABLE_NAME = "FEDERAL_NEW_NDC";
	public static final Object[][] TABLE_COLUMNS = {
			{ "FSS", Types.DOUBLE },
			{ "ITEM_MASTER_SID", Types.INTEGER },
			{ "WAC_PRICE", Types.DOUBLE },
			{ "NON_FAMP", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("FSS", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("ITEM_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("WAC_PRICE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("NON_FAMP", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table FEDERAL_NEW_NDC (FSS DOUBLE,ITEM_MASTER_SID INTEGER not null primary key,WAC_PRICE DOUBLE,NON_FAMP DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table FEDERAL_NEW_NDC";
	public static final String ORDER_BY_JPQL = " ORDER BY federalNewNdc.itemMasterSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY FEDERAL_NEW_NDC.ITEM_MASTER_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.FederalNewNdc"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.FederalNewNdc"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.FederalNewNdc"));

	public FederalNewNdcModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _itemMasterSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setItemMasterSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemMasterSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FederalNewNdc.class;
	}

	@Override
	public String getModelClassName() {
		return FederalNewNdc.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fss", getFss());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("wacPrice", getWacPrice());
		attributes.put("nonFamp", getNonFamp());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double fss = (Double)attributes.get("fss");

		if (fss != null) {
			setFss(fss);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Double wacPrice = (Double)attributes.get("wacPrice");

		if (wacPrice != null) {
			setWacPrice(wacPrice);
		}

		Double nonFamp = (Double)attributes.get("nonFamp");

		if (nonFamp != null) {
			setNonFamp(nonFamp);
		}
	}

	@Override
	public double getFss() {
		return _fss;
	}

	@Override
	public void setFss(double fss) {
		_fss = fss;
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
	public double getWacPrice() {
		return _wacPrice;
	}

	@Override
	public void setWacPrice(double wacPrice) {
		_wacPrice = wacPrice;
	}

	@Override
	public double getNonFamp() {
		return _nonFamp;
	}

	@Override
	public void setNonFamp(double nonFamp) {
		_nonFamp = nonFamp;
	}

	@Override
	public FederalNewNdc toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (FederalNewNdc)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		FederalNewNdcImpl federalNewNdcImpl = new FederalNewNdcImpl();

		federalNewNdcImpl.setFss(getFss());
		federalNewNdcImpl.setItemMasterSid(getItemMasterSid());
		federalNewNdcImpl.setWacPrice(getWacPrice());
		federalNewNdcImpl.setNonFamp(getNonFamp());

		federalNewNdcImpl.resetOriginalValues();

		return federalNewNdcImpl;
	}

	@Override
	public int compareTo(FederalNewNdc federalNewNdc) {
		int primaryKey = federalNewNdc.getPrimaryKey();

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

		if (!(obj instanceof FederalNewNdc)) {
			return false;
		}

		FederalNewNdc federalNewNdc = (FederalNewNdc)obj;

		int primaryKey = federalNewNdc.getPrimaryKey();

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
	public CacheModel<FederalNewNdc> toCacheModel() {
		FederalNewNdcCacheModel federalNewNdcCacheModel = new FederalNewNdcCacheModel();

		federalNewNdcCacheModel.fss = getFss();

		federalNewNdcCacheModel.itemMasterSid = getItemMasterSid();

		federalNewNdcCacheModel.wacPrice = getWacPrice();

		federalNewNdcCacheModel.nonFamp = getNonFamp();

		return federalNewNdcCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{fss=");
		sb.append(getFss());
		sb.append(", itemMasterSid=");
		sb.append(getItemMasterSid());
		sb.append(", wacPrice=");
		sb.append(getWacPrice());
		sb.append(", nonFamp=");
		sb.append(getNonFamp());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.FederalNewNdc");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>fss</column-name><column-value><![CDATA[");
		sb.append(getFss());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
		sb.append(getItemMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wacPrice</column-name><column-value><![CDATA[");
		sb.append(getWacPrice());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nonFamp</column-name><column-value><![CDATA[");
		sb.append(getNonFamp());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = FederalNewNdc.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			FederalNewNdc.class
		};
	private double _fss;
	private int _itemMasterSid;
	private double _wacPrice;
	private double _nonFamp;
	private FederalNewNdc _escapedModel;
}