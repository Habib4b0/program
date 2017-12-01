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

import com.stpl.app.model.ChAssumptions;
import com.stpl.app.model.ChAssumptionsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ChAssumptions service. Represents a row in the &quot;CH_ASSUMPTIONS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ChAssumptionsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ChAssumptionsImpl}.
 * </p>
 *
 * @author
 * @see ChAssumptionsImpl
 * @see ChAssumptions
 * @see ChAssumptionsModel
 * @generated
 */
@ProviderType
public class ChAssumptionsModelImpl extends BaseModelImpl<ChAssumptions>
	implements ChAssumptionsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ch assumptions model instance should use the {@link ChAssumptions} interface instead.
	 */
	public static final String TABLE_NAME = "CH_ASSUMPTIONS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "PARENT", Types.BOOLEAN },
			{ "PROJECTION_DETAILS_SID", Types.INTEGER },
			{ "COMMENTARY", Types.VARCHAR },
			{ "QUARTER", Types.INTEGER },
			{ "TOTAL_DISCOUNT_PERCENT_CHANGE", Types.DOUBLE },
			{ "REASON_CODES", Types.VARCHAR },
			{ "YEAR", Types.INTEGER },
			{ "TOTAL_DISCOUNT_PERCENT_PROJECTED", Types.DOUBLE },
			{ "TOTAL_DISCOUNT_PERCENT_PRIOR", Types.DOUBLE },
			{ "CH_ASSUMPTIONS_SID", Types.INTEGER },
			{ "TOTAL_DISCOUNT_CHANGE", Types.DOUBLE },
			{ "TOTAL_DISCOUNT_PROJECTED", Types.DOUBLE },
			{ "CAM_ID", Types.INTEGER },
			{ "GROSS_TRADE_SALES", Types.DOUBLE },
			{ "TOTAL_DISCOUNT_PRIOR", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("PARENT", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("PROJECTION_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("COMMENTARY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("QUARTER", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_PERCENT_CHANGE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("REASON_CODES", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("YEAR", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_PERCENT_PROJECTED", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_PERCENT_PRIOR", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("CH_ASSUMPTIONS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_CHANGE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_PROJECTED", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("CAM_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("GROSS_TRADE_SALES", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_PRIOR", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table CH_ASSUMPTIONS (PARENT BOOLEAN,PROJECTION_DETAILS_SID INTEGER,COMMENTARY VARCHAR(75) null,QUARTER INTEGER,TOTAL_DISCOUNT_PERCENT_CHANGE DOUBLE,REASON_CODES VARCHAR(75) null,YEAR INTEGER,TOTAL_DISCOUNT_PERCENT_PROJECTED DOUBLE,TOTAL_DISCOUNT_PERCENT_PRIOR DOUBLE,CH_ASSUMPTIONS_SID INTEGER not null primary key IDENTITY,TOTAL_DISCOUNT_CHANGE DOUBLE,TOTAL_DISCOUNT_PROJECTED DOUBLE,CAM_ID INTEGER,GROSS_TRADE_SALES DOUBLE,TOTAL_DISCOUNT_PRIOR DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table CH_ASSUMPTIONS";
	public static final String ORDER_BY_JPQL = " ORDER BY chAssumptions.chAssumptionsSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CH_ASSUMPTIONS.CH_ASSUMPTIONS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.ChAssumptions"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.ChAssumptions"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.ChAssumptions"));

	public ChAssumptionsModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _chAssumptionsSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setChAssumptionsSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chAssumptionsSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ChAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return ChAssumptions.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("parent", getParent());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("commentary", getCommentary());
		attributes.put("quarter", getQuarter());
		attributes.put("totalDiscountPercentChange",
			getTotalDiscountPercentChange());
		attributes.put("reasonCodes", getReasonCodes());
		attributes.put("year", getYear());
		attributes.put("totalDiscountPercentProjected",
			getTotalDiscountPercentProjected());
		attributes.put("totalDiscountPercentPrior",
			getTotalDiscountPercentPrior());
		attributes.put("chAssumptionsSid", getChAssumptionsSid());
		attributes.put("totalDiscountChange", getTotalDiscountChange());
		attributes.put("totalDiscountProjected", getTotalDiscountProjected());
		attributes.put("camId", getCamId());
		attributes.put("grossTradeSales", getGrossTradeSales());
		attributes.put("totalDiscountPrior", getTotalDiscountPrior());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean parent = (Boolean)attributes.get("parent");

		if (parent != null) {
			setParent(parent);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		String commentary = (String)attributes.get("commentary");

		if (commentary != null) {
			setCommentary(commentary);
		}

		Integer quarter = (Integer)attributes.get("quarter");

		if (quarter != null) {
			setQuarter(quarter);
		}

		Double totalDiscountPercentChange = (Double)attributes.get(
				"totalDiscountPercentChange");

		if (totalDiscountPercentChange != null) {
			setTotalDiscountPercentChange(totalDiscountPercentChange);
		}

		String reasonCodes = (String)attributes.get("reasonCodes");

		if (reasonCodes != null) {
			setReasonCodes(reasonCodes);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		Double totalDiscountPercentProjected = (Double)attributes.get(
				"totalDiscountPercentProjected");

		if (totalDiscountPercentProjected != null) {
			setTotalDiscountPercentProjected(totalDiscountPercentProjected);
		}

		Double totalDiscountPercentPrior = (Double)attributes.get(
				"totalDiscountPercentPrior");

		if (totalDiscountPercentPrior != null) {
			setTotalDiscountPercentPrior(totalDiscountPercentPrior);
		}

		Integer chAssumptionsSid = (Integer)attributes.get("chAssumptionsSid");

		if (chAssumptionsSid != null) {
			setChAssumptionsSid(chAssumptionsSid);
		}

		Double totalDiscountChange = (Double)attributes.get(
				"totalDiscountChange");

		if (totalDiscountChange != null) {
			setTotalDiscountChange(totalDiscountChange);
		}

		Double totalDiscountProjected = (Double)attributes.get(
				"totalDiscountProjected");

		if (totalDiscountProjected != null) {
			setTotalDiscountProjected(totalDiscountProjected);
		}

		Integer camId = (Integer)attributes.get("camId");

		if (camId != null) {
			setCamId(camId);
		}

		Double grossTradeSales = (Double)attributes.get("grossTradeSales");

		if (grossTradeSales != null) {
			setGrossTradeSales(grossTradeSales);
		}

		Double totalDiscountPrior = (Double)attributes.get("totalDiscountPrior");

		if (totalDiscountPrior != null) {
			setTotalDiscountPrior(totalDiscountPrior);
		}
	}

	@Override
	public boolean getParent() {
		return _parent;
	}

	@Override
	public boolean isParent() {
		return _parent;
	}

	@Override
	public void setParent(boolean parent) {
		_parent = parent;
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
	public String getCommentary() {
		if (_commentary == null) {
			return StringPool.BLANK;
		}
		else {
			return _commentary;
		}
	}

	@Override
	public void setCommentary(String commentary) {
		_commentary = commentary;
	}

	@Override
	public int getQuarter() {
		return _quarter;
	}

	@Override
	public void setQuarter(int quarter) {
		_quarter = quarter;
	}

	@Override
	public double getTotalDiscountPercentChange() {
		return _totalDiscountPercentChange;
	}

	@Override
	public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
		_totalDiscountPercentChange = totalDiscountPercentChange;
	}

	@Override
	public String getReasonCodes() {
		if (_reasonCodes == null) {
			return StringPool.BLANK;
		}
		else {
			return _reasonCodes;
		}
	}

	@Override
	public void setReasonCodes(String reasonCodes) {
		_reasonCodes = reasonCodes;
	}

	@Override
	public int getYear() {
		return _year;
	}

	@Override
	public void setYear(int year) {
		_year = year;
	}

	@Override
	public double getTotalDiscountPercentProjected() {
		return _totalDiscountPercentProjected;
	}

	@Override
	public void setTotalDiscountPercentProjected(
		double totalDiscountPercentProjected) {
		_totalDiscountPercentProjected = totalDiscountPercentProjected;
	}

	@Override
	public double getTotalDiscountPercentPrior() {
		return _totalDiscountPercentPrior;
	}

	@Override
	public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
		_totalDiscountPercentPrior = totalDiscountPercentPrior;
	}

	@Override
	public int getChAssumptionsSid() {
		return _chAssumptionsSid;
	}

	@Override
	public void setChAssumptionsSid(int chAssumptionsSid) {
		_chAssumptionsSid = chAssumptionsSid;
	}

	@Override
	public double getTotalDiscountChange() {
		return _totalDiscountChange;
	}

	@Override
	public void setTotalDiscountChange(double totalDiscountChange) {
		_totalDiscountChange = totalDiscountChange;
	}

	@Override
	public double getTotalDiscountProjected() {
		return _totalDiscountProjected;
	}

	@Override
	public void setTotalDiscountProjected(double totalDiscountProjected) {
		_totalDiscountProjected = totalDiscountProjected;
	}

	@Override
	public int getCamId() {
		return _camId;
	}

	@Override
	public void setCamId(int camId) {
		_camId = camId;
	}

	@Override
	public double getGrossTradeSales() {
		return _grossTradeSales;
	}

	@Override
	public void setGrossTradeSales(double grossTradeSales) {
		_grossTradeSales = grossTradeSales;
	}

	@Override
	public double getTotalDiscountPrior() {
		return _totalDiscountPrior;
	}

	@Override
	public void setTotalDiscountPrior(double totalDiscountPrior) {
		_totalDiscountPrior = totalDiscountPrior;
	}

	@Override
	public ChAssumptions toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ChAssumptions)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ChAssumptionsImpl chAssumptionsImpl = new ChAssumptionsImpl();

		chAssumptionsImpl.setParent(getParent());
		chAssumptionsImpl.setProjectionDetailsSid(getProjectionDetailsSid());
		chAssumptionsImpl.setCommentary(getCommentary());
		chAssumptionsImpl.setQuarter(getQuarter());
		chAssumptionsImpl.setTotalDiscountPercentChange(getTotalDiscountPercentChange());
		chAssumptionsImpl.setReasonCodes(getReasonCodes());
		chAssumptionsImpl.setYear(getYear());
		chAssumptionsImpl.setTotalDiscountPercentProjected(getTotalDiscountPercentProjected());
		chAssumptionsImpl.setTotalDiscountPercentPrior(getTotalDiscountPercentPrior());
		chAssumptionsImpl.setChAssumptionsSid(getChAssumptionsSid());
		chAssumptionsImpl.setTotalDiscountChange(getTotalDiscountChange());
		chAssumptionsImpl.setTotalDiscountProjected(getTotalDiscountProjected());
		chAssumptionsImpl.setCamId(getCamId());
		chAssumptionsImpl.setGrossTradeSales(getGrossTradeSales());
		chAssumptionsImpl.setTotalDiscountPrior(getTotalDiscountPrior());

		chAssumptionsImpl.resetOriginalValues();

		return chAssumptionsImpl;
	}

	@Override
	public int compareTo(ChAssumptions chAssumptions) {
		int primaryKey = chAssumptions.getPrimaryKey();

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

		if (!(obj instanceof ChAssumptions)) {
			return false;
		}

		ChAssumptions chAssumptions = (ChAssumptions)obj;

		int primaryKey = chAssumptions.getPrimaryKey();

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
	public CacheModel<ChAssumptions> toCacheModel() {
		ChAssumptionsCacheModel chAssumptionsCacheModel = new ChAssumptionsCacheModel();

		chAssumptionsCacheModel.parent = getParent();

		chAssumptionsCacheModel.projectionDetailsSid = getProjectionDetailsSid();

		chAssumptionsCacheModel.commentary = getCommentary();

		String commentary = chAssumptionsCacheModel.commentary;

		if ((commentary != null) && (commentary.length() == 0)) {
			chAssumptionsCacheModel.commentary = null;
		}

		chAssumptionsCacheModel.quarter = getQuarter();

		chAssumptionsCacheModel.totalDiscountPercentChange = getTotalDiscountPercentChange();

		chAssumptionsCacheModel.reasonCodes = getReasonCodes();

		String reasonCodes = chAssumptionsCacheModel.reasonCodes;

		if ((reasonCodes != null) && (reasonCodes.length() == 0)) {
			chAssumptionsCacheModel.reasonCodes = null;
		}

		chAssumptionsCacheModel.year = getYear();

		chAssumptionsCacheModel.totalDiscountPercentProjected = getTotalDiscountPercentProjected();

		chAssumptionsCacheModel.totalDiscountPercentPrior = getTotalDiscountPercentPrior();

		chAssumptionsCacheModel.chAssumptionsSid = getChAssumptionsSid();

		chAssumptionsCacheModel.totalDiscountChange = getTotalDiscountChange();

		chAssumptionsCacheModel.totalDiscountProjected = getTotalDiscountProjected();

		chAssumptionsCacheModel.camId = getCamId();

		chAssumptionsCacheModel.grossTradeSales = getGrossTradeSales();

		chAssumptionsCacheModel.totalDiscountPrior = getTotalDiscountPrior();

		return chAssumptionsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{parent=");
		sb.append(getParent());
		sb.append(", projectionDetailsSid=");
		sb.append(getProjectionDetailsSid());
		sb.append(", commentary=");
		sb.append(getCommentary());
		sb.append(", quarter=");
		sb.append(getQuarter());
		sb.append(", totalDiscountPercentChange=");
		sb.append(getTotalDiscountPercentChange());
		sb.append(", reasonCodes=");
		sb.append(getReasonCodes());
		sb.append(", year=");
		sb.append(getYear());
		sb.append(", totalDiscountPercentProjected=");
		sb.append(getTotalDiscountPercentProjected());
		sb.append(", totalDiscountPercentPrior=");
		sb.append(getTotalDiscountPercentPrior());
		sb.append(", chAssumptionsSid=");
		sb.append(getChAssumptionsSid());
		sb.append(", totalDiscountChange=");
		sb.append(getTotalDiscountChange());
		sb.append(", totalDiscountProjected=");
		sb.append(getTotalDiscountProjected());
		sb.append(", camId=");
		sb.append(getCamId());
		sb.append(", grossTradeSales=");
		sb.append(getGrossTradeSales());
		sb.append(", totalDiscountPrior=");
		sb.append(getTotalDiscountPrior());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.ChAssumptions");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>parent</column-name><column-value><![CDATA[");
		sb.append(getParent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getProjectionDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commentary</column-name><column-value><![CDATA[");
		sb.append(getCommentary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quarter</column-name><column-value><![CDATA[");
		sb.append(getQuarter());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalDiscountPercentChange</column-name><column-value><![CDATA[");
		sb.append(getTotalDiscountPercentChange());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reasonCodes</column-name><column-value><![CDATA[");
		sb.append(getReasonCodes());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalDiscountPercentProjected</column-name><column-value><![CDATA[");
		sb.append(getTotalDiscountPercentProjected());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalDiscountPercentPrior</column-name><column-value><![CDATA[");
		sb.append(getTotalDiscountPercentPrior());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>chAssumptionsSid</column-name><column-value><![CDATA[");
		sb.append(getChAssumptionsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalDiscountChange</column-name><column-value><![CDATA[");
		sb.append(getTotalDiscountChange());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalDiscountProjected</column-name><column-value><![CDATA[");
		sb.append(getTotalDiscountProjected());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>camId</column-name><column-value><![CDATA[");
		sb.append(getCamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>grossTradeSales</column-name><column-value><![CDATA[");
		sb.append(getGrossTradeSales());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalDiscountPrior</column-name><column-value><![CDATA[");
		sb.append(getTotalDiscountPrior());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ChAssumptions.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ChAssumptions.class
		};
	private boolean _parent;
	private int _projectionDetailsSid;
	private String _commentary;
	private int _quarter;
	private double _totalDiscountPercentChange;
	private String _reasonCodes;
	private int _year;
	private double _totalDiscountPercentProjected;
	private double _totalDiscountPercentPrior;
	private int _chAssumptionsSid;
	private double _totalDiscountChange;
	private double _totalDiscountProjected;
	private int _camId;
	private double _grossTradeSales;
	private double _totalDiscountPrior;
	private ChAssumptions _escapedModel;
}