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

import com.stpl.app.parttwo.model.AcBrMethodologyDetails;
import com.stpl.app.parttwo.model.AcBrMethodologyDetailsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the AcBrMethodologyDetails service. Represents a row in the &quot;AC_BR_METHODOLOGY_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AcBrMethodologyDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AcBrMethodologyDetailsImpl}.
 * </p>
 *
 * @author
 * @see AcBrMethodologyDetailsImpl
 * @see AcBrMethodologyDetails
 * @see AcBrMethodologyDetailsModel
 * @generated
 */
@ProviderType
public class AcBrMethodologyDetailsModelImpl extends BaseModelImpl<AcBrMethodologyDetails>
	implements AcBrMethodologyDetailsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ac br methodology details model instance should use the {@link AcBrMethodologyDetails} interface instead.
	 */
	public static final String TABLE_NAME = "AC_BR_METHODOLOGY_DETAILS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "SALES_GROWTH_RATE", Types.DOUBLE },
			{ "METHODOLOGY_START_DATE", Types.TIMESTAMP },
			{ "FREQUENCY", Types.VARCHAR },
			{ "CALCULATION_FLAG", Types.BOOLEAN },
			{ "PROVISION_GROWTH_RATE", Types.DOUBLE },
			{ "SALES_BASIS", Types.INTEGER },
			{ "AC_BR_METHODOLOGY_DETAILS_SID", Types.INTEGER },
			{ "ACC_CLOSURE_MASTER_SID", Types.INTEGER },
			{ "METHODOLOGY_END_DATE", Types.TIMESTAMP },
			{ "METHODOLOGY_VALUE", Types.DOUBLE },
			{ "DAMPING_FACTOR", Types.DOUBLE },
			{ "METHODOLOGY_NAME", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("SALES_GROWTH_RATE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("METHODOLOGY_START_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("FREQUENCY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CALCULATION_FLAG", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("PROVISION_GROWTH_RATE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("SALES_BASIS", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("AC_BR_METHODOLOGY_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ACC_CLOSURE_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("METHODOLOGY_END_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("METHODOLOGY_VALUE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("DAMPING_FACTOR", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("METHODOLOGY_NAME", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table AC_BR_METHODOLOGY_DETAILS (SALES_GROWTH_RATE DOUBLE,METHODOLOGY_START_DATE DATE null,FREQUENCY VARCHAR(75) null,CALCULATION_FLAG BOOLEAN,PROVISION_GROWTH_RATE DOUBLE,SALES_BASIS INTEGER,AC_BR_METHODOLOGY_DETAILS_SID INTEGER not null primary key IDENTITY,ACC_CLOSURE_MASTER_SID INTEGER,METHODOLOGY_END_DATE DATE null,METHODOLOGY_VALUE DOUBLE,DAMPING_FACTOR DOUBLE,METHODOLOGY_NAME VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table AC_BR_METHODOLOGY_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY acBrMethodologyDetails.acBrMethodologyDetailsSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY AC_BR_METHODOLOGY_DETAILS.AC_BR_METHODOLOGY_DETAILS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.parttwo.model.AcBrMethodologyDetails"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.parttwo.model.AcBrMethodologyDetails"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.parttwo.model.AcBrMethodologyDetails"));

	public AcBrMethodologyDetailsModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _acBrMethodologyDetailsSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setAcBrMethodologyDetailsSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _acBrMethodologyDetailsSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AcBrMethodologyDetails.class;
	}

	@Override
	public String getModelClassName() {
		return AcBrMethodologyDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("salesGrowthRate", getSalesGrowthRate());
		attributes.put("methodologyStartDate", getMethodologyStartDate());
		attributes.put("frequency", getFrequency());
		attributes.put("calculationFlag", getCalculationFlag());
		attributes.put("provisionGrowthRate", getProvisionGrowthRate());
		attributes.put("salesBasis", getSalesBasis());
		attributes.put("acBrMethodologyDetailsSid",
			getAcBrMethodologyDetailsSid());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("methodologyEndDate", getMethodologyEndDate());
		attributes.put("methodologyValue", getMethodologyValue());
		attributes.put("dampingFactor", getDampingFactor());
		attributes.put("methodologyName", getMethodologyName());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double salesGrowthRate = (Double)attributes.get("salesGrowthRate");

		if (salesGrowthRate != null) {
			setSalesGrowthRate(salesGrowthRate);
		}

		Date methodologyStartDate = (Date)attributes.get("methodologyStartDate");

		if (methodologyStartDate != null) {
			setMethodologyStartDate(methodologyStartDate);
		}

		String frequency = (String)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		Boolean calculationFlag = (Boolean)attributes.get("calculationFlag");

		if (calculationFlag != null) {
			setCalculationFlag(calculationFlag);
		}

		Double provisionGrowthRate = (Double)attributes.get(
				"provisionGrowthRate");

		if (provisionGrowthRate != null) {
			setProvisionGrowthRate(provisionGrowthRate);
		}

		Integer salesBasis = (Integer)attributes.get("salesBasis");

		if (salesBasis != null) {
			setSalesBasis(salesBasis);
		}

		Integer acBrMethodologyDetailsSid = (Integer)attributes.get(
				"acBrMethodologyDetailsSid");

		if (acBrMethodologyDetailsSid != null) {
			setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		Date methodologyEndDate = (Date)attributes.get("methodologyEndDate");

		if (methodologyEndDate != null) {
			setMethodologyEndDate(methodologyEndDate);
		}

		Double methodologyValue = (Double)attributes.get("methodologyValue");

		if (methodologyValue != null) {
			setMethodologyValue(methodologyValue);
		}

		Double dampingFactor = (Double)attributes.get("dampingFactor");

		if (dampingFactor != null) {
			setDampingFactor(dampingFactor);
		}

		String methodologyName = (String)attributes.get("methodologyName");

		if (methodologyName != null) {
			setMethodologyName(methodologyName);
		}
	}

	@Override
	public double getSalesGrowthRate() {
		return _salesGrowthRate;
	}

	@Override
	public void setSalesGrowthRate(double salesGrowthRate) {
		_salesGrowthRate = salesGrowthRate;
	}

	@Override
	public Date getMethodologyStartDate() {
		return _methodologyStartDate;
	}

	@Override
	public void setMethodologyStartDate(Date methodologyStartDate) {
		_methodologyStartDate = methodologyStartDate;
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
	public boolean getCalculationFlag() {
		return _calculationFlag;
	}

	@Override
	public boolean isCalculationFlag() {
		return _calculationFlag;
	}

	@Override
	public void setCalculationFlag(boolean calculationFlag) {
		_calculationFlag = calculationFlag;
	}

	@Override
	public double getProvisionGrowthRate() {
		return _provisionGrowthRate;
	}

	@Override
	public void setProvisionGrowthRate(double provisionGrowthRate) {
		_provisionGrowthRate = provisionGrowthRate;
	}

	@Override
	public int getSalesBasis() {
		return _salesBasis;
	}

	@Override
	public void setSalesBasis(int salesBasis) {
		_salesBasis = salesBasis;
	}

	@Override
	public int getAcBrMethodologyDetailsSid() {
		return _acBrMethodologyDetailsSid;
	}

	@Override
	public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
		_acBrMethodologyDetailsSid = acBrMethodologyDetailsSid;
	}

	@Override
	public int getAccClosureMasterSid() {
		return _accClosureMasterSid;
	}

	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_accClosureMasterSid = accClosureMasterSid;
	}

	@Override
	public Date getMethodologyEndDate() {
		return _methodologyEndDate;
	}

	@Override
	public void setMethodologyEndDate(Date methodologyEndDate) {
		_methodologyEndDate = methodologyEndDate;
	}

	@Override
	public double getMethodologyValue() {
		return _methodologyValue;
	}

	@Override
	public void setMethodologyValue(double methodologyValue) {
		_methodologyValue = methodologyValue;
	}

	@Override
	public double getDampingFactor() {
		return _dampingFactor;
	}

	@Override
	public void setDampingFactor(double dampingFactor) {
		_dampingFactor = dampingFactor;
	}

	@Override
	public String getMethodologyName() {
		if (_methodologyName == null) {
			return StringPool.BLANK;
		}
		else {
			return _methodologyName;
		}
	}

	@Override
	public void setMethodologyName(String methodologyName) {
		_methodologyName = methodologyName;
	}

	@Override
	public AcBrMethodologyDetails toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AcBrMethodologyDetails)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AcBrMethodologyDetailsImpl acBrMethodologyDetailsImpl = new AcBrMethodologyDetailsImpl();

		acBrMethodologyDetailsImpl.setSalesGrowthRate(getSalesGrowthRate());
		acBrMethodologyDetailsImpl.setMethodologyStartDate(getMethodologyStartDate());
		acBrMethodologyDetailsImpl.setFrequency(getFrequency());
		acBrMethodologyDetailsImpl.setCalculationFlag(getCalculationFlag());
		acBrMethodologyDetailsImpl.setProvisionGrowthRate(getProvisionGrowthRate());
		acBrMethodologyDetailsImpl.setSalesBasis(getSalesBasis());
		acBrMethodologyDetailsImpl.setAcBrMethodologyDetailsSid(getAcBrMethodologyDetailsSid());
		acBrMethodologyDetailsImpl.setAccClosureMasterSid(getAccClosureMasterSid());
		acBrMethodologyDetailsImpl.setMethodologyEndDate(getMethodologyEndDate());
		acBrMethodologyDetailsImpl.setMethodologyValue(getMethodologyValue());
		acBrMethodologyDetailsImpl.setDampingFactor(getDampingFactor());
		acBrMethodologyDetailsImpl.setMethodologyName(getMethodologyName());

		acBrMethodologyDetailsImpl.resetOriginalValues();

		return acBrMethodologyDetailsImpl;
	}

	@Override
	public int compareTo(AcBrMethodologyDetails acBrMethodologyDetails) {
		int primaryKey = acBrMethodologyDetails.getPrimaryKey();

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

		if (!(obj instanceof AcBrMethodologyDetails)) {
			return false;
		}

		AcBrMethodologyDetails acBrMethodologyDetails = (AcBrMethodologyDetails)obj;

		int primaryKey = acBrMethodologyDetails.getPrimaryKey();

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
	public CacheModel<AcBrMethodologyDetails> toCacheModel() {
		AcBrMethodologyDetailsCacheModel acBrMethodologyDetailsCacheModel = new AcBrMethodologyDetailsCacheModel();

		acBrMethodologyDetailsCacheModel.salesGrowthRate = getSalesGrowthRate();

		Date methodologyStartDate = getMethodologyStartDate();

		if (methodologyStartDate != null) {
			acBrMethodologyDetailsCacheModel.methodologyStartDate = methodologyStartDate.getTime();
		}
		else {
			acBrMethodologyDetailsCacheModel.methodologyStartDate = Long.MIN_VALUE;
		}

		acBrMethodologyDetailsCacheModel.frequency = getFrequency();

		String frequency = acBrMethodologyDetailsCacheModel.frequency;

		if ((frequency != null) && (frequency.length() == 0)) {
			acBrMethodologyDetailsCacheModel.frequency = null;
		}

		acBrMethodologyDetailsCacheModel.calculationFlag = getCalculationFlag();

		acBrMethodologyDetailsCacheModel.provisionGrowthRate = getProvisionGrowthRate();

		acBrMethodologyDetailsCacheModel.salesBasis = getSalesBasis();

		acBrMethodologyDetailsCacheModel.acBrMethodologyDetailsSid = getAcBrMethodologyDetailsSid();

		acBrMethodologyDetailsCacheModel.accClosureMasterSid = getAccClosureMasterSid();

		Date methodologyEndDate = getMethodologyEndDate();

		if (methodologyEndDate != null) {
			acBrMethodologyDetailsCacheModel.methodologyEndDate = methodologyEndDate.getTime();
		}
		else {
			acBrMethodologyDetailsCacheModel.methodologyEndDate = Long.MIN_VALUE;
		}

		acBrMethodologyDetailsCacheModel.methodologyValue = getMethodologyValue();

		acBrMethodologyDetailsCacheModel.dampingFactor = getDampingFactor();

		acBrMethodologyDetailsCacheModel.methodologyName = getMethodologyName();

		String methodologyName = acBrMethodologyDetailsCacheModel.methodologyName;

		if ((methodologyName != null) && (methodologyName.length() == 0)) {
			acBrMethodologyDetailsCacheModel.methodologyName = null;
		}

		return acBrMethodologyDetailsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{salesGrowthRate=");
		sb.append(getSalesGrowthRate());
		sb.append(", methodologyStartDate=");
		sb.append(getMethodologyStartDate());
		sb.append(", frequency=");
		sb.append(getFrequency());
		sb.append(", calculationFlag=");
		sb.append(getCalculationFlag());
		sb.append(", provisionGrowthRate=");
		sb.append(getProvisionGrowthRate());
		sb.append(", salesBasis=");
		sb.append(getSalesBasis());
		sb.append(", acBrMethodologyDetailsSid=");
		sb.append(getAcBrMethodologyDetailsSid());
		sb.append(", accClosureMasterSid=");
		sb.append(getAccClosureMasterSid());
		sb.append(", methodologyEndDate=");
		sb.append(getMethodologyEndDate());
		sb.append(", methodologyValue=");
		sb.append(getMethodologyValue());
		sb.append(", dampingFactor=");
		sb.append(getDampingFactor());
		sb.append(", methodologyName=");
		sb.append(getMethodologyName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.parttwo.model.AcBrMethodologyDetails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>salesGrowthRate</column-name><column-value><![CDATA[");
		sb.append(getSalesGrowthRate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>methodologyStartDate</column-name><column-value><![CDATA[");
		sb.append(getMethodologyStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>frequency</column-name><column-value><![CDATA[");
		sb.append(getFrequency());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calculationFlag</column-name><column-value><![CDATA[");
		sb.append(getCalculationFlag());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>provisionGrowthRate</column-name><column-value><![CDATA[");
		sb.append(getProvisionGrowthRate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>salesBasis</column-name><column-value><![CDATA[");
		sb.append(getSalesBasis());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>acBrMethodologyDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getAcBrMethodologyDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
		sb.append(getAccClosureMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>methodologyEndDate</column-name><column-value><![CDATA[");
		sb.append(getMethodologyEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>methodologyValue</column-name><column-value><![CDATA[");
		sb.append(getMethodologyValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dampingFactor</column-name><column-value><![CDATA[");
		sb.append(getDampingFactor());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>methodologyName</column-name><column-value><![CDATA[");
		sb.append(getMethodologyName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = AcBrMethodologyDetails.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			AcBrMethodologyDetails.class
		};
	private double _salesGrowthRate;
	private Date _methodologyStartDate;
	private String _frequency;
	private boolean _calculationFlag;
	private double _provisionGrowthRate;
	private int _salesBasis;
	private int _acBrMethodologyDetailsSid;
	private int _accClosureMasterSid;
	private Date _methodologyEndDate;
	private double _methodologyValue;
	private double _dampingFactor;
	private String _methodologyName;
	private AcBrMethodologyDetails _escapedModel;
}