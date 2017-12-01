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

import com.stpl.app.model.StChProjectionDiscount;
import com.stpl.app.model.StChProjectionDiscountModel;
import com.stpl.app.service.persistence.StChProjectionDiscountPK;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the StChProjectionDiscount service. Represents a row in the &quot;ST_CH_PROJECTION_DISCOUNT&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link StChProjectionDiscountModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StChProjectionDiscountImpl}.
 * </p>
 *
 * @author
 * @see StChProjectionDiscountImpl
 * @see StChProjectionDiscount
 * @see StChProjectionDiscountModel
 * @generated
 */
@ProviderType
public class StChProjectionDiscountModelImpl extends BaseModelImpl<StChProjectionDiscount>
	implements StChProjectionDiscountModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a st ch projection discount model instance should use the {@link StChProjectionDiscount} interface instead.
	 */
	public static final String TABLE_NAME = "ST_CH_PROJECTION_DISCOUNT";
	public static final Object[][] TABLE_COLUMNS = {
			{ "LAST_MODIFIED_DATE", Types.TIMESTAMP },
			{ "ADJUSTMENT_METHODOLOGY", Types.VARCHAR },
			{ "PRODUCT_GROWTH", Types.DOUBLE },
			{ "PROJECTION_RATE", Types.DOUBLE },
			{ "PROJECTION_DETAILS_SID", Types.INTEGER },
			{ "USER_ID", Types.INTEGER },
			{ "ACCOUNT_GROWTH", Types.DOUBLE },
			{ "DISCOUNT_AMOUNT", Types.DOUBLE },
			{ "DISCOUNT_RATE", Types.DOUBLE },
			{ "PERIOD_SID", Types.INTEGER },
			{ "ADJUSTMENT_BASIS", Types.VARCHAR },
			{ "SESSION_ID", Types.INTEGER },
			{ "ADJUSTMENT_VALUE", Types.DOUBLE },
			{ "ADJUSTMENT_TYPE", Types.VARCHAR },
			{ "RS_MODEL_SID", Types.INTEGER },
			{ "PROJECTION_SALES", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("LAST_MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ADJUSTMENT_METHODOLOGY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("PRODUCT_GROWTH", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("PROJECTION_RATE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("PROJECTION_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("USER_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ACCOUNT_GROWTH", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("DISCOUNT_AMOUNT", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("DISCOUNT_RATE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("PERIOD_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ADJUSTMENT_BASIS", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("SESSION_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ADJUSTMENT_VALUE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("ADJUSTMENT_TYPE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("RS_MODEL_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("PROJECTION_SALES", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table ST_CH_PROJECTION_DISCOUNT (LAST_MODIFIED_DATE DATE null,ADJUSTMENT_METHODOLOGY VARCHAR(75) null,PRODUCT_GROWTH DOUBLE,PROJECTION_RATE DOUBLE,PROJECTION_DETAILS_SID INTEGER not null IDENTITY,USER_ID INTEGER not null IDENTITY,ACCOUNT_GROWTH DOUBLE,DISCOUNT_AMOUNT DOUBLE,DISCOUNT_RATE DOUBLE,PERIOD_SID INTEGER not null IDENTITY,ADJUSTMENT_BASIS VARCHAR(75) null,SESSION_ID INTEGER not null IDENTITY,ADJUSTMENT_VALUE DOUBLE,ADJUSTMENT_TYPE VARCHAR(75) null,RS_MODEL_SID INTEGER not null IDENTITY,PROJECTION_SALES DOUBLE,primary key (PROJECTION_DETAILS_SID, USER_ID, PERIOD_SID, SESSION_ID, RS_MODEL_SID))";
	public static final String TABLE_SQL_DROP = "drop table ST_CH_PROJECTION_DISCOUNT";
	public static final String ORDER_BY_JPQL = " ORDER BY stChProjectionDiscount.id.projectionDetailsSid ASC, stChProjectionDiscount.id.userId ASC, stChProjectionDiscount.id.periodSid ASC, stChProjectionDiscount.id.sessionId ASC, stChProjectionDiscount.id.rsModelSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ST_CH_PROJECTION_DISCOUNT.PROJECTION_DETAILS_SID ASC, ST_CH_PROJECTION_DISCOUNT.USER_ID ASC, ST_CH_PROJECTION_DISCOUNT.PERIOD_SID ASC, ST_CH_PROJECTION_DISCOUNT.SESSION_ID ASC, ST_CH_PROJECTION_DISCOUNT.RS_MODEL_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.StChProjectionDiscount"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.StChProjectionDiscount"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.StChProjectionDiscount"));

	public StChProjectionDiscountModelImpl() {
	}

	@Override
	public StChProjectionDiscountPK getPrimaryKey() {
		return new StChProjectionDiscountPK(_projectionDetailsSid, _userId,
			_periodSid, _sessionId, _rsModelSid);
	}

	@Override
	public void setPrimaryKey(StChProjectionDiscountPK primaryKey) {
		setProjectionDetailsSid(primaryKey.projectionDetailsSid);
		setUserId(primaryKey.userId);
		setPeriodSid(primaryKey.periodSid);
		setSessionId(primaryKey.sessionId);
		setRsModelSid(primaryKey.rsModelSid);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new StChProjectionDiscountPK(_projectionDetailsSid, _userId,
			_periodSid, _sessionId, _rsModelSid);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((StChProjectionDiscountPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return StChProjectionDiscount.class;
	}

	@Override
	public String getModelClassName() {
		return StChProjectionDiscount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("adjustmentMethodology", getAdjustmentMethodology());
		attributes.put("productGrowth", getProductGrowth());
		attributes.put("projectionRate", getProjectionRate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("userId", getUserId());
		attributes.put("accountGrowth", getAccountGrowth());
		attributes.put("discountAmount", getDiscountAmount());
		attributes.put("discountRate", getDiscountRate());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("adjustmentBasis", getAdjustmentBasis());
		attributes.put("sessionId", getSessionId());
		attributes.put("adjustmentValue", getAdjustmentValue());
		attributes.put("adjustmentType", getAdjustmentType());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("projectionSales", getProjectionSales());

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

		String adjustmentMethodology = (String)attributes.get(
				"adjustmentMethodology");

		if (adjustmentMethodology != null) {
			setAdjustmentMethodology(adjustmentMethodology);
		}

		Double productGrowth = (Double)attributes.get("productGrowth");

		if (productGrowth != null) {
			setProductGrowth(productGrowth);
		}

		Double projectionRate = (Double)attributes.get("projectionRate");

		if (projectionRate != null) {
			setProjectionRate(projectionRate);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Double accountGrowth = (Double)attributes.get("accountGrowth");

		if (accountGrowth != null) {
			setAccountGrowth(accountGrowth);
		}

		Double discountAmount = (Double)attributes.get("discountAmount");

		if (discountAmount != null) {
			setDiscountAmount(discountAmount);
		}

		Double discountRate = (Double)attributes.get("discountRate");

		if (discountRate != null) {
			setDiscountRate(discountRate);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		String adjustmentBasis = (String)attributes.get("adjustmentBasis");

		if (adjustmentBasis != null) {
			setAdjustmentBasis(adjustmentBasis);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double adjustmentValue = (Double)attributes.get("adjustmentValue");

		if (adjustmentValue != null) {
			setAdjustmentValue(adjustmentValue);
		}

		String adjustmentType = (String)attributes.get("adjustmentType");

		if (adjustmentType != null) {
			setAdjustmentType(adjustmentType);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		Double projectionSales = (Double)attributes.get("projectionSales");

		if (projectionSales != null) {
			setProjectionSales(projectionSales);
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
	public String getAdjustmentMethodology() {
		if (_adjustmentMethodology == null) {
			return StringPool.BLANK;
		}
		else {
			return _adjustmentMethodology;
		}
	}

	@Override
	public void setAdjustmentMethodology(String adjustmentMethodology) {
		_adjustmentMethodology = adjustmentMethodology;
	}

	@Override
	public double getProductGrowth() {
		return _productGrowth;
	}

	@Override
	public void setProductGrowth(double productGrowth) {
		_productGrowth = productGrowth;
	}

	@Override
	public double getProjectionRate() {
		return _projectionRate;
	}

	@Override
	public void setProjectionRate(double projectionRate) {
		_projectionRate = projectionRate;
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
	public int getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(int userId) {
		_userId = userId;
	}

	@Override
	public double getAccountGrowth() {
		return _accountGrowth;
	}

	@Override
	public void setAccountGrowth(double accountGrowth) {
		_accountGrowth = accountGrowth;
	}

	@Override
	public double getDiscountAmount() {
		return _discountAmount;
	}

	@Override
	public void setDiscountAmount(double discountAmount) {
		_discountAmount = discountAmount;
	}

	@Override
	public double getDiscountRate() {
		return _discountRate;
	}

	@Override
	public void setDiscountRate(double discountRate) {
		_discountRate = discountRate;
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
	public String getAdjustmentBasis() {
		if (_adjustmentBasis == null) {
			return StringPool.BLANK;
		}
		else {
			return _adjustmentBasis;
		}
	}

	@Override
	public void setAdjustmentBasis(String adjustmentBasis) {
		_adjustmentBasis = adjustmentBasis;
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
	public double getAdjustmentValue() {
		return _adjustmentValue;
	}

	@Override
	public void setAdjustmentValue(double adjustmentValue) {
		_adjustmentValue = adjustmentValue;
	}

	@Override
	public String getAdjustmentType() {
		if (_adjustmentType == null) {
			return StringPool.BLANK;
		}
		else {
			return _adjustmentType;
		}
	}

	@Override
	public void setAdjustmentType(String adjustmentType) {
		_adjustmentType = adjustmentType;
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
	public double getProjectionSales() {
		return _projectionSales;
	}

	@Override
	public void setProjectionSales(double projectionSales) {
		_projectionSales = projectionSales;
	}

	@Override
	public StChProjectionDiscount toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (StChProjectionDiscount)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StChProjectionDiscountImpl stChProjectionDiscountImpl = new StChProjectionDiscountImpl();

		stChProjectionDiscountImpl.setLastModifiedDate(getLastModifiedDate());
		stChProjectionDiscountImpl.setAdjustmentMethodology(getAdjustmentMethodology());
		stChProjectionDiscountImpl.setProductGrowth(getProductGrowth());
		stChProjectionDiscountImpl.setProjectionRate(getProjectionRate());
		stChProjectionDiscountImpl.setProjectionDetailsSid(getProjectionDetailsSid());
		stChProjectionDiscountImpl.setUserId(getUserId());
		stChProjectionDiscountImpl.setAccountGrowth(getAccountGrowth());
		stChProjectionDiscountImpl.setDiscountAmount(getDiscountAmount());
		stChProjectionDiscountImpl.setDiscountRate(getDiscountRate());
		stChProjectionDiscountImpl.setPeriodSid(getPeriodSid());
		stChProjectionDiscountImpl.setAdjustmentBasis(getAdjustmentBasis());
		stChProjectionDiscountImpl.setSessionId(getSessionId());
		stChProjectionDiscountImpl.setAdjustmentValue(getAdjustmentValue());
		stChProjectionDiscountImpl.setAdjustmentType(getAdjustmentType());
		stChProjectionDiscountImpl.setRsModelSid(getRsModelSid());
		stChProjectionDiscountImpl.setProjectionSales(getProjectionSales());

		stChProjectionDiscountImpl.resetOriginalValues();

		return stChProjectionDiscountImpl;
	}

	@Override
	public int compareTo(StChProjectionDiscount stChProjectionDiscount) {
		StChProjectionDiscountPK primaryKey = stChProjectionDiscount.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChProjectionDiscount)) {
			return false;
		}

		StChProjectionDiscount stChProjectionDiscount = (StChProjectionDiscount)obj;

		StChProjectionDiscountPK primaryKey = stChProjectionDiscount.getPrimaryKey();

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
	public CacheModel<StChProjectionDiscount> toCacheModel() {
		StChProjectionDiscountCacheModel stChProjectionDiscountCacheModel = new StChProjectionDiscountCacheModel();

		stChProjectionDiscountCacheModel.stChProjectionDiscountPK = getPrimaryKey();

		Date lastModifiedDate = getLastModifiedDate();

		if (lastModifiedDate != null) {
			stChProjectionDiscountCacheModel.lastModifiedDate = lastModifiedDate.getTime();
		}
		else {
			stChProjectionDiscountCacheModel.lastModifiedDate = Long.MIN_VALUE;
		}

		stChProjectionDiscountCacheModel.adjustmentMethodology = getAdjustmentMethodology();

		String adjustmentMethodology = stChProjectionDiscountCacheModel.adjustmentMethodology;

		if ((adjustmentMethodology != null) &&
				(adjustmentMethodology.length() == 0)) {
			stChProjectionDiscountCacheModel.adjustmentMethodology = null;
		}

		stChProjectionDiscountCacheModel.productGrowth = getProductGrowth();

		stChProjectionDiscountCacheModel.projectionRate = getProjectionRate();

		stChProjectionDiscountCacheModel.projectionDetailsSid = getProjectionDetailsSid();

		stChProjectionDiscountCacheModel.userId = getUserId();

		stChProjectionDiscountCacheModel.accountGrowth = getAccountGrowth();

		stChProjectionDiscountCacheModel.discountAmount = getDiscountAmount();

		stChProjectionDiscountCacheModel.discountRate = getDiscountRate();

		stChProjectionDiscountCacheModel.periodSid = getPeriodSid();

		stChProjectionDiscountCacheModel.adjustmentBasis = getAdjustmentBasis();

		String adjustmentBasis = stChProjectionDiscountCacheModel.adjustmentBasis;

		if ((adjustmentBasis != null) && (adjustmentBasis.length() == 0)) {
			stChProjectionDiscountCacheModel.adjustmentBasis = null;
		}

		stChProjectionDiscountCacheModel.sessionId = getSessionId();

		stChProjectionDiscountCacheModel.adjustmentValue = getAdjustmentValue();

		stChProjectionDiscountCacheModel.adjustmentType = getAdjustmentType();

		String adjustmentType = stChProjectionDiscountCacheModel.adjustmentType;

		if ((adjustmentType != null) && (adjustmentType.length() == 0)) {
			stChProjectionDiscountCacheModel.adjustmentType = null;
		}

		stChProjectionDiscountCacheModel.rsModelSid = getRsModelSid();

		stChProjectionDiscountCacheModel.projectionSales = getProjectionSales();

		return stChProjectionDiscountCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{lastModifiedDate=");
		sb.append(getLastModifiedDate());
		sb.append(", adjustmentMethodology=");
		sb.append(getAdjustmentMethodology());
		sb.append(", productGrowth=");
		sb.append(getProductGrowth());
		sb.append(", projectionRate=");
		sb.append(getProjectionRate());
		sb.append(", projectionDetailsSid=");
		sb.append(getProjectionDetailsSid());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", accountGrowth=");
		sb.append(getAccountGrowth());
		sb.append(", discountAmount=");
		sb.append(getDiscountAmount());
		sb.append(", discountRate=");
		sb.append(getDiscountRate());
		sb.append(", periodSid=");
		sb.append(getPeriodSid());
		sb.append(", adjustmentBasis=");
		sb.append(getAdjustmentBasis());
		sb.append(", sessionId=");
		sb.append(getSessionId());
		sb.append(", adjustmentValue=");
		sb.append(getAdjustmentValue());
		sb.append(", adjustmentType=");
		sb.append(getAdjustmentType());
		sb.append(", rsModelSid=");
		sb.append(getRsModelSid());
		sb.append(", projectionSales=");
		sb.append(getProjectionSales());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.StChProjectionDiscount");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
		sb.append(getLastModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>adjustmentMethodology</column-name><column-value><![CDATA[");
		sb.append(getAdjustmentMethodology());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productGrowth</column-name><column-value><![CDATA[");
		sb.append(getProductGrowth());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectionRate</column-name><column-value><![CDATA[");
		sb.append(getProjectionRate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getProjectionDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountGrowth</column-name><column-value><![CDATA[");
		sb.append(getAccountGrowth());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>discountAmount</column-name><column-value><![CDATA[");
		sb.append(getDiscountAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>discountRate</column-name><column-value><![CDATA[");
		sb.append(getDiscountRate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>periodSid</column-name><column-value><![CDATA[");
		sb.append(getPeriodSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>adjustmentBasis</column-name><column-value><![CDATA[");
		sb.append(getAdjustmentBasis());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionId</column-name><column-value><![CDATA[");
		sb.append(getSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>adjustmentValue</column-name><column-value><![CDATA[");
		sb.append(getAdjustmentValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
		sb.append(getAdjustmentType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
		sb.append(getRsModelSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectionSales</column-name><column-value><![CDATA[");
		sb.append(getProjectionSales());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = StChProjectionDiscount.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			StChProjectionDiscount.class
		};
	private Date _lastModifiedDate;
	private String _adjustmentMethodology;
	private double _productGrowth;
	private double _projectionRate;
	private int _projectionDetailsSid;
	private int _userId;
	private double _accountGrowth;
	private double _discountAmount;
	private double _discountRate;
	private int _periodSid;
	private String _adjustmentBasis;
	private int _sessionId;
	private double _adjustmentValue;
	private String _adjustmentType;
	private int _rsModelSid;
	private double _projectionSales;
	private StChProjectionDiscount _escapedModel;
}