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

import com.stpl.app.model.StNationalAssumptions;
import com.stpl.app.model.StNationalAssumptionsModel;
import com.stpl.app.service.persistence.StNationalAssumptionsPK;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the StNationalAssumptions service. Represents a row in the &quot;ST_NATIONAL_ASSUMPTIONS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link StNationalAssumptionsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StNationalAssumptionsImpl}.
 * </p>
 *
 * @author
 * @see StNationalAssumptionsImpl
 * @see StNationalAssumptions
 * @see StNationalAssumptionsModel
 * @generated
 */
@ProviderType
public class StNationalAssumptionsModelImpl extends BaseModelImpl<StNationalAssumptions>
	implements StNationalAssumptionsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a st national assumptions model instance should use the {@link StNationalAssumptions} interface instead.
	 */
	public static final String TABLE_NAME = "ST_NATIONAL_ASSUMPTIONS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "LAST_MODIFIED_DATE", Types.TIMESTAMP },
			{ "BASELINE_PERIOD", Types.VARCHAR },
			{ "START_PERIOD", Types.VARCHAR },
			{ "FREQUENCY", Types.VARCHAR },
			{ "USER_ID", Types.INTEGER },
			{ "END_PERIOD", Types.VARCHAR },
			{ "NA_PROJ_MASTER_SID", Types.INTEGER },
			{ "ROLLING_PERIOD", Types.VARCHAR },
			{ "FORECAST_METHODOLOGY", Types.VARCHAR },
			{ "PRICE_TYPE", Types.VARCHAR },
			{ "PRICE_BASIS", Types.VARCHAR },
			{ "SESSION_ID", Types.INTEGER },
			{ "BASELINE_METHODOLOGY", Types.VARCHAR },
			{ "GROWTH_RATE", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("LAST_MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("BASELINE_PERIOD", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("START_PERIOD", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("FREQUENCY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("USER_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("END_PERIOD", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("NA_PROJ_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ROLLING_PERIOD", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("FORECAST_METHODOLOGY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("PRICE_TYPE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("PRICE_BASIS", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("SESSION_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("BASELINE_METHODOLOGY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("GROWTH_RATE", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table ST_NATIONAL_ASSUMPTIONS (LAST_MODIFIED_DATE DATE null,BASELINE_PERIOD VARCHAR(75) null,START_PERIOD VARCHAR(75) not null IDENTITY,FREQUENCY VARCHAR(75) null,USER_ID INTEGER not null IDENTITY,END_PERIOD VARCHAR(75) not null IDENTITY,NA_PROJ_MASTER_SID INTEGER not null IDENTITY,ROLLING_PERIOD VARCHAR(75) null,FORECAST_METHODOLOGY VARCHAR(75) null,PRICE_TYPE VARCHAR(75) not null IDENTITY,PRICE_BASIS VARCHAR(75) null,SESSION_ID INTEGER not null IDENTITY,BASELINE_METHODOLOGY VARCHAR(75) null,GROWTH_RATE DOUBLE,primary key (START_PERIOD, USER_ID, END_PERIOD, NA_PROJ_MASTER_SID, PRICE_TYPE, SESSION_ID))";
	public static final String TABLE_SQL_DROP = "drop table ST_NATIONAL_ASSUMPTIONS";
	public static final String ORDER_BY_JPQL = " ORDER BY stNationalAssumptions.id.startPeriod ASC, stNationalAssumptions.id.userId ASC, stNationalAssumptions.id.endPeriod ASC, stNationalAssumptions.id.naProjMasterSid ASC, stNationalAssumptions.id.priceType ASC, stNationalAssumptions.id.sessionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ST_NATIONAL_ASSUMPTIONS.START_PERIOD ASC, ST_NATIONAL_ASSUMPTIONS.USER_ID ASC, ST_NATIONAL_ASSUMPTIONS.END_PERIOD ASC, ST_NATIONAL_ASSUMPTIONS.NA_PROJ_MASTER_SID ASC, ST_NATIONAL_ASSUMPTIONS.PRICE_TYPE ASC, ST_NATIONAL_ASSUMPTIONS.SESSION_ID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.StNationalAssumptions"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.StNationalAssumptions"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.StNationalAssumptions"));

	public StNationalAssumptionsModelImpl() {
	}

	@Override
	public StNationalAssumptionsPK getPrimaryKey() {
		return new StNationalAssumptionsPK(_startPeriod, _userId, _endPeriod,
			_naProjMasterSid, _priceType, _sessionId);
	}

	@Override
	public void setPrimaryKey(StNationalAssumptionsPK primaryKey) {
		setStartPeriod(primaryKey.startPeriod);
		setUserId(primaryKey.userId);
		setEndPeriod(primaryKey.endPeriod);
		setNaProjMasterSid(primaryKey.naProjMasterSid);
		setPriceType(primaryKey.priceType);
		setSessionId(primaryKey.sessionId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new StNationalAssumptionsPK(_startPeriod, _userId, _endPeriod,
			_naProjMasterSid, _priceType, _sessionId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((StNationalAssumptionsPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return StNationalAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return StNationalAssumptions.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("baselinePeriod", getBaselinePeriod());
		attributes.put("startPeriod", getStartPeriod());
		attributes.put("frequency", getFrequency());
		attributes.put("userId", getUserId());
		attributes.put("endPeriod", getEndPeriod());
		attributes.put("naProjMasterSid", getNaProjMasterSid());
		attributes.put("rollingPeriod", getRollingPeriod());
		attributes.put("forecastMethodology", getForecastMethodology());
		attributes.put("priceType", getPriceType());
		attributes.put("priceBasis", getPriceBasis());
		attributes.put("sessionId", getSessionId());
		attributes.put("baselineMethodology", getBaselineMethodology());
		attributes.put("growthRate", getGrowthRate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		String baselinePeriod = (String)attributes.get("baselinePeriod");

		if (baselinePeriod != null) {
			setBaselinePeriod(baselinePeriod);
		}

		String startPeriod = (String)attributes.get("startPeriod");

		if (startPeriod != null) {
			setStartPeriod(startPeriod);
		}

		String frequency = (String)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String endPeriod = (String)attributes.get("endPeriod");

		if (endPeriod != null) {
			setEndPeriod(endPeriod);
		}

		Integer naProjMasterSid = (Integer)attributes.get("naProjMasterSid");

		if (naProjMasterSid != null) {
			setNaProjMasterSid(naProjMasterSid);
		}

		String rollingPeriod = (String)attributes.get("rollingPeriod");

		if (rollingPeriod != null) {
			setRollingPeriod(rollingPeriod);
		}

		String forecastMethodology = (String)attributes.get(
				"forecastMethodology");

		if (forecastMethodology != null) {
			setForecastMethodology(forecastMethodology);
		}

		String priceType = (String)attributes.get("priceType");

		if (priceType != null) {
			setPriceType(priceType);
		}

		String priceBasis = (String)attributes.get("priceBasis");

		if (priceBasis != null) {
			setPriceBasis(priceBasis);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String baselineMethodology = (String)attributes.get(
				"baselineMethodology");

		if (baselineMethodology != null) {
			setBaselineMethodology(baselineMethodology);
		}

		Double growthRate = (Double)attributes.get("growthRate");

		if (growthRate != null) {
			setGrowthRate(growthRate);
		}
	}

	@Override
	public Date getLastModifiedDate() {
		return _lastModifiedDate;
	}

	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String getBaselinePeriod() {
		if (_baselinePeriod == null) {
			return StringPool.BLANK;
		}
		else {
			return _baselinePeriod;
		}
	}

	@Override
	public void setBaselinePeriod(String baselinePeriod) {
		_baselinePeriod = baselinePeriod;
	}

	@Override
	public String getStartPeriod() {
		if (_startPeriod == null) {
			return StringPool.BLANK;
		}
		else {
			return _startPeriod;
		}
	}

	@Override
	public void setStartPeriod(String startPeriod) {
		_startPeriod = startPeriod;
	}

	@Override
	public String getFrequency() {
		if (_frequency == null) {
			return StringPool.BLANK;
		}
		else {
			return _frequency;
		}
	}

	@Override
	public void setFrequency(String frequency) {
		_frequency = frequency;
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
	public String getEndPeriod() {
		if (_endPeriod == null) {
			return StringPool.BLANK;
		}
		else {
			return _endPeriod;
		}
	}

	@Override
	public void setEndPeriod(String endPeriod) {
		_endPeriod = endPeriod;
	}

	@Override
	public int getNaProjMasterSid() {
		return _naProjMasterSid;
	}

	@Override
	public void setNaProjMasterSid(int naProjMasterSid) {
		_naProjMasterSid = naProjMasterSid;
	}

	@Override
	public String getRollingPeriod() {
		if (_rollingPeriod == null) {
			return StringPool.BLANK;
		}
		else {
			return _rollingPeriod;
		}
	}

	@Override
	public void setRollingPeriod(String rollingPeriod) {
		_rollingPeriod = rollingPeriod;
	}

	@Override
	public String getForecastMethodology() {
		if (_forecastMethodology == null) {
			return StringPool.BLANK;
		}
		else {
			return _forecastMethodology;
		}
	}

	@Override
	public void setForecastMethodology(String forecastMethodology) {
		_forecastMethodology = forecastMethodology;
	}

	@Override
	public String getPriceType() {
		if (_priceType == null) {
			return StringPool.BLANK;
		}
		else {
			return _priceType;
		}
	}

	@Override
	public void setPriceType(String priceType) {
		_priceType = priceType;
	}

	@Override
	public String getPriceBasis() {
		if (_priceBasis == null) {
			return StringPool.BLANK;
		}
		else {
			return _priceBasis;
		}
	}

	@Override
	public void setPriceBasis(String priceBasis) {
		_priceBasis = priceBasis;
	}

	@Override
	public int getSessionId() {
		return _sessionId;
	}

	@Override
	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	@Override
	public String getBaselineMethodology() {
		if (_baselineMethodology == null) {
			return StringPool.BLANK;
		}
		else {
			return _baselineMethodology;
		}
	}

	@Override
	public void setBaselineMethodology(String baselineMethodology) {
		_baselineMethodology = baselineMethodology;
	}

	@Override
	public double getGrowthRate() {
		return _growthRate;
	}

	@Override
	public void setGrowthRate(double growthRate) {
		_growthRate = growthRate;
	}

	@Override
	public StNationalAssumptions toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (StNationalAssumptions)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StNationalAssumptionsImpl stNationalAssumptionsImpl = new StNationalAssumptionsImpl();

		stNationalAssumptionsImpl.setLastModifiedDate(getLastModifiedDate());
		stNationalAssumptionsImpl.setBaselinePeriod(getBaselinePeriod());
		stNationalAssumptionsImpl.setStartPeriod(getStartPeriod());
		stNationalAssumptionsImpl.setFrequency(getFrequency());
		stNationalAssumptionsImpl.setUserId(getUserId());
		stNationalAssumptionsImpl.setEndPeriod(getEndPeriod());
		stNationalAssumptionsImpl.setNaProjMasterSid(getNaProjMasterSid());
		stNationalAssumptionsImpl.setRollingPeriod(getRollingPeriod());
		stNationalAssumptionsImpl.setForecastMethodology(getForecastMethodology());
		stNationalAssumptionsImpl.setPriceType(getPriceType());
		stNationalAssumptionsImpl.setPriceBasis(getPriceBasis());
		stNationalAssumptionsImpl.setSessionId(getSessionId());
		stNationalAssumptionsImpl.setBaselineMethodology(getBaselineMethodology());
		stNationalAssumptionsImpl.setGrowthRate(getGrowthRate());

		stNationalAssumptionsImpl.resetOriginalValues();

		return stNationalAssumptionsImpl;
	}

	@Override
	public int compareTo(StNationalAssumptions stNationalAssumptions) {
		StNationalAssumptionsPK primaryKey = stNationalAssumptions.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNationalAssumptions)) {
			return false;
		}

		StNationalAssumptions stNationalAssumptions = (StNationalAssumptions)obj;

		StNationalAssumptionsPK primaryKey = stNationalAssumptions.getPrimaryKey();

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
	public CacheModel<StNationalAssumptions> toCacheModel() {
		StNationalAssumptionsCacheModel stNationalAssumptionsCacheModel = new StNationalAssumptionsCacheModel();

		stNationalAssumptionsCacheModel.stNationalAssumptionsPK = getPrimaryKey();

		Date lastModifiedDate = getLastModifiedDate();

		if (lastModifiedDate != null) {
			stNationalAssumptionsCacheModel.lastModifiedDate = lastModifiedDate.getTime();
		}
		else {
			stNationalAssumptionsCacheModel.lastModifiedDate = Long.MIN_VALUE;
		}

		stNationalAssumptionsCacheModel.baselinePeriod = getBaselinePeriod();

		String baselinePeriod = stNationalAssumptionsCacheModel.baselinePeriod;

		if ((baselinePeriod != null) && (baselinePeriod.length() == 0)) {
			stNationalAssumptionsCacheModel.baselinePeriod = null;
		}

		stNationalAssumptionsCacheModel.startPeriod = getStartPeriod();

		String startPeriod = stNationalAssumptionsCacheModel.startPeriod;

		if ((startPeriod != null) && (startPeriod.length() == 0)) {
			stNationalAssumptionsCacheModel.startPeriod = null;
		}

		stNationalAssumptionsCacheModel.frequency = getFrequency();

		String frequency = stNationalAssumptionsCacheModel.frequency;

		if ((frequency != null) && (frequency.length() == 0)) {
			stNationalAssumptionsCacheModel.frequency = null;
		}

		stNationalAssumptionsCacheModel.userId = getUserId();

		stNationalAssumptionsCacheModel.endPeriod = getEndPeriod();

		String endPeriod = stNationalAssumptionsCacheModel.endPeriod;

		if ((endPeriod != null) && (endPeriod.length() == 0)) {
			stNationalAssumptionsCacheModel.endPeriod = null;
		}

		stNationalAssumptionsCacheModel.naProjMasterSid = getNaProjMasterSid();

		stNationalAssumptionsCacheModel.rollingPeriod = getRollingPeriod();

		String rollingPeriod = stNationalAssumptionsCacheModel.rollingPeriod;

		if ((rollingPeriod != null) && (rollingPeriod.length() == 0)) {
			stNationalAssumptionsCacheModel.rollingPeriod = null;
		}

		stNationalAssumptionsCacheModel.forecastMethodology = getForecastMethodology();

		String forecastMethodology = stNationalAssumptionsCacheModel.forecastMethodology;

		if ((forecastMethodology != null) &&
				(forecastMethodology.length() == 0)) {
			stNationalAssumptionsCacheModel.forecastMethodology = null;
		}

		stNationalAssumptionsCacheModel.priceType = getPriceType();

		String priceType = stNationalAssumptionsCacheModel.priceType;

		if ((priceType != null) && (priceType.length() == 0)) {
			stNationalAssumptionsCacheModel.priceType = null;
		}

		stNationalAssumptionsCacheModel.priceBasis = getPriceBasis();

		String priceBasis = stNationalAssumptionsCacheModel.priceBasis;

		if ((priceBasis != null) && (priceBasis.length() == 0)) {
			stNationalAssumptionsCacheModel.priceBasis = null;
		}

		stNationalAssumptionsCacheModel.sessionId = getSessionId();

		stNationalAssumptionsCacheModel.baselineMethodology = getBaselineMethodology();

		String baselineMethodology = stNationalAssumptionsCacheModel.baselineMethodology;

		if ((baselineMethodology != null) &&
				(baselineMethodology.length() == 0)) {
			stNationalAssumptionsCacheModel.baselineMethodology = null;
		}

		stNationalAssumptionsCacheModel.growthRate = getGrowthRate();

		return stNationalAssumptionsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{lastModifiedDate=");
		sb.append(getLastModifiedDate());
		sb.append(", baselinePeriod=");
		sb.append(getBaselinePeriod());
		sb.append(", startPeriod=");
		sb.append(getStartPeriod());
		sb.append(", frequency=");
		sb.append(getFrequency());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", endPeriod=");
		sb.append(getEndPeriod());
		sb.append(", naProjMasterSid=");
		sb.append(getNaProjMasterSid());
		sb.append(", rollingPeriod=");
		sb.append(getRollingPeriod());
		sb.append(", forecastMethodology=");
		sb.append(getForecastMethodology());
		sb.append(", priceType=");
		sb.append(getPriceType());
		sb.append(", priceBasis=");
		sb.append(getPriceBasis());
		sb.append(", sessionId=");
		sb.append(getSessionId());
		sb.append(", baselineMethodology=");
		sb.append(getBaselineMethodology());
		sb.append(", growthRate=");
		sb.append(getGrowthRate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.StNationalAssumptions");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
		sb.append(getLastModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>baselinePeriod</column-name><column-value><![CDATA[");
		sb.append(getBaselinePeriod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startPeriod</column-name><column-value><![CDATA[");
		sb.append(getStartPeriod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>frequency</column-name><column-value><![CDATA[");
		sb.append(getFrequency());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endPeriod</column-name><column-value><![CDATA[");
		sb.append(getEndPeriod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>naProjMasterSid</column-name><column-value><![CDATA[");
		sb.append(getNaProjMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rollingPeriod</column-name><column-value><![CDATA[");
		sb.append(getRollingPeriod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>forecastMethodology</column-name><column-value><![CDATA[");
		sb.append(getForecastMethodology());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priceType</column-name><column-value><![CDATA[");
		sb.append(getPriceType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priceBasis</column-name><column-value><![CDATA[");
		sb.append(getPriceBasis());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionId</column-name><column-value><![CDATA[");
		sb.append(getSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>baselineMethodology</column-name><column-value><![CDATA[");
		sb.append(getBaselineMethodology());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>growthRate</column-name><column-value><![CDATA[");
		sb.append(getGrowthRate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = StNationalAssumptions.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			StNationalAssumptions.class
		};
	private Date _lastModifiedDate;
	private String _baselinePeriod;
	private String _startPeriod;
	private String _frequency;
	private int _userId;
	private String _endPeriod;
	private int _naProjMasterSid;
	private String _rollingPeriod;
	private String _forecastMethodology;
	private String _priceType;
	private String _priceBasis;
	private int _sessionId;
	private String _baselineMethodology;
	private double _growthRate;
	private StNationalAssumptions _escapedModel;
}