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

import com.stpl.app.model.NmActualPpa;
import com.stpl.app.model.NmActualPpaModel;
import com.stpl.app.service.persistence.NmActualPpaPK;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the NmActualPpa service. Represents a row in the &quot;NM_ACTUAL_PPA&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link NmActualPpaModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NmActualPpaImpl}.
 * </p>
 *
 * @author
 * @see NmActualPpaImpl
 * @see NmActualPpa
 * @see NmActualPpaModel
 * @generated
 */
@ProviderType
public class NmActualPpaModelImpl extends BaseModelImpl<NmActualPpa>
	implements NmActualPpaModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a nm actual ppa model instance should use the {@link NmActualPpa} interface instead.
	 */
	public static final String TABLE_NAME = "NM_ACTUAL_PPA";
	public static final Object[][] TABLE_COLUMNS = {
			{ "ACTUAL_RATE", Types.DOUBLE },
			{ "PERIOD_SID", Types.INTEGER },
			{ "PROJECTION_DETAILS_SID", Types.INTEGER },
			{ "ACTUAL_DISCOUNT_DOLLAR", Types.DOUBLE },
			{ "ACTUAL_DISCOUNT_UNITS", Types.DOUBLE },
			{ "ACTUAL_SALES", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("ACTUAL_RATE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("PERIOD_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("PROJECTION_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ACTUAL_DISCOUNT_DOLLAR", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("ACTUAL_DISCOUNT_UNITS", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("ACTUAL_SALES", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table NM_ACTUAL_PPA (ACTUAL_RATE DOUBLE,PERIOD_SID INTEGER not null IDENTITY,PROJECTION_DETAILS_SID INTEGER not null IDENTITY,ACTUAL_DISCOUNT_DOLLAR DOUBLE,ACTUAL_DISCOUNT_UNITS DOUBLE,ACTUAL_SALES DOUBLE,primary key (PERIOD_SID, PROJECTION_DETAILS_SID))";
	public static final String TABLE_SQL_DROP = "drop table NM_ACTUAL_PPA";
	public static final String ORDER_BY_JPQL = " ORDER BY nmActualPpa.id.periodSid ASC, nmActualPpa.id.projectionDetailsSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY NM_ACTUAL_PPA.PERIOD_SID ASC, NM_ACTUAL_PPA.PROJECTION_DETAILS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.NmActualPpa"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.NmActualPpa"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.NmActualPpa"));

	public NmActualPpaModelImpl() {
	}

	@Override
	public NmActualPpaPK getPrimaryKey() {
		return new NmActualPpaPK(_periodSid, _projectionDetailsSid);
	}

	@Override
	public void setPrimaryKey(NmActualPpaPK primaryKey) {
		setPeriodSid(primaryKey.periodSid);
		setProjectionDetailsSid(primaryKey.projectionDetailsSid);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new NmActualPpaPK(_periodSid, _projectionDetailsSid);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((NmActualPpaPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return NmActualPpa.class;
	}

	@Override
	public String getModelClassName() {
		return NmActualPpa.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("actualRate", getActualRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("actualDiscountDollar", getActualDiscountDollar());
		attributes.put("actualDiscountUnits", getActualDiscountUnits());
		attributes.put("actualSales", getActualSales());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double actualRate = (Double)attributes.get("actualRate");

		if (actualRate != null) {
			setActualRate(actualRate);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double actualDiscountDollar = (Double)attributes.get(
				"actualDiscountDollar");

		if (actualDiscountDollar != null) {
			setActualDiscountDollar(actualDiscountDollar);
		}

		Double actualDiscountUnits = (Double)attributes.get(
				"actualDiscountUnits");

		if (actualDiscountUnits != null) {
			setActualDiscountUnits(actualDiscountUnits);
		}

		Double actualSales = (Double)attributes.get("actualSales");

		if (actualSales != null) {
			setActualSales(actualSales);
		}
	}

	@Override
	public double getActualRate() {
		return _actualRate;
	}

	@Override
	public void setActualRate(double actualRate) {
		_actualRate = actualRate;
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
	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	@Override
	public double getActualDiscountDollar() {
		return _actualDiscountDollar;
	}

	@Override
	public void setActualDiscountDollar(double actualDiscountDollar) {
		_actualDiscountDollar = actualDiscountDollar;
	}

	@Override
	public double getActualDiscountUnits() {
		return _actualDiscountUnits;
	}

	@Override
	public void setActualDiscountUnits(double actualDiscountUnits) {
		_actualDiscountUnits = actualDiscountUnits;
	}

	@Override
	public double getActualSales() {
		return _actualSales;
	}

	@Override
	public void setActualSales(double actualSales) {
		_actualSales = actualSales;
	}

	@Override
	public NmActualPpa toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (NmActualPpa)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		NmActualPpaImpl nmActualPpaImpl = new NmActualPpaImpl();

		nmActualPpaImpl.setActualRate(getActualRate());
		nmActualPpaImpl.setPeriodSid(getPeriodSid());
		nmActualPpaImpl.setProjectionDetailsSid(getProjectionDetailsSid());
		nmActualPpaImpl.setActualDiscountDollar(getActualDiscountDollar());
		nmActualPpaImpl.setActualDiscountUnits(getActualDiscountUnits());
		nmActualPpaImpl.setActualSales(getActualSales());

		nmActualPpaImpl.resetOriginalValues();

		return nmActualPpaImpl;
	}

	@Override
	public int compareTo(NmActualPpa nmActualPpa) {
		NmActualPpaPK primaryKey = nmActualPpa.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmActualPpa)) {
			return false;
		}

		NmActualPpa nmActualPpa = (NmActualPpa)obj;

		NmActualPpaPK primaryKey = nmActualPpa.getPrimaryKey();

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
	public CacheModel<NmActualPpa> toCacheModel() {
		NmActualPpaCacheModel nmActualPpaCacheModel = new NmActualPpaCacheModel();

		nmActualPpaCacheModel.nmActualPpaPK = getPrimaryKey();

		nmActualPpaCacheModel.actualRate = getActualRate();

		nmActualPpaCacheModel.periodSid = getPeriodSid();

		nmActualPpaCacheModel.projectionDetailsSid = getProjectionDetailsSid();

		nmActualPpaCacheModel.actualDiscountDollar = getActualDiscountDollar();

		nmActualPpaCacheModel.actualDiscountUnits = getActualDiscountUnits();

		nmActualPpaCacheModel.actualSales = getActualSales();

		return nmActualPpaCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{actualRate=");
		sb.append(getActualRate());
		sb.append(", periodSid=");
		sb.append(getPeriodSid());
		sb.append(", projectionDetailsSid=");
		sb.append(getProjectionDetailsSid());
		sb.append(", actualDiscountDollar=");
		sb.append(getActualDiscountDollar());
		sb.append(", actualDiscountUnits=");
		sb.append(getActualDiscountUnits());
		sb.append(", actualSales=");
		sb.append(getActualSales());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.NmActualPpa");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>actualRate</column-name><column-value><![CDATA[");
		sb.append(getActualRate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>periodSid</column-name><column-value><![CDATA[");
		sb.append(getPeriodSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getProjectionDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualDiscountDollar</column-name><column-value><![CDATA[");
		sb.append(getActualDiscountDollar());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualDiscountUnits</column-name><column-value><![CDATA[");
		sb.append(getActualDiscountUnits());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualSales</column-name><column-value><![CDATA[");
		sb.append(getActualSales());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = NmActualPpa.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			NmActualPpa.class
		};
	private double _actualRate;
	private int _periodSid;
	private int _projectionDetailsSid;
	private double _actualDiscountDollar;
	private double _actualDiscountUnits;
	private double _actualSales;
	private NmActualPpa _escapedModel;
}