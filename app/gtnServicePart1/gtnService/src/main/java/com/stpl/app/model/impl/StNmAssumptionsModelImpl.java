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

import com.stpl.app.model.StNmAssumptions;
import com.stpl.app.model.StNmAssumptionsModel;
import com.stpl.app.service.persistence.StNmAssumptionsPK;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the StNmAssumptions service. Represents a row in the &quot;ST_NM_ASSUMPTIONS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link StNmAssumptionsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StNmAssumptionsImpl}.
 * </p>
 *
 * @author
 * @see StNmAssumptionsImpl
 * @see StNmAssumptions
 * @see StNmAssumptionsModel
 * @generated
 */
@ProviderType
public class StNmAssumptionsModelImpl extends BaseModelImpl<StNmAssumptions>
	implements StNmAssumptionsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a st nm assumptions model instance should use the {@link StNmAssumptions} interface instead.
	 */
	public static final String TABLE_NAME = "ST_NM_ASSUMPTIONS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "LAST_MODIFIED_DATE", Types.TIMESTAMP },
			{ "PARENT", Types.BOOLEAN },
			{ "PROJECTION_PERIOD", Types.INTEGER },
			{ "COMMENTARY", Types.VARCHAR },
			{ "NM_ASSUMPTIONS_SID", Types.INTEGER },
			{ "PROJECTION_DETAILS_SID", Types.INTEGER },
			{ "NET_SALES_PRIOR", Types.DOUBLE },
			{ "USER_ID", Types.INTEGER },
			{ "GROSS_SALES_PERCENT_CHANGE", Types.DOUBLE },
			{ "TOTAL_DISCOUNT_PERCENT_CHANGE", Types.DOUBLE },
			{ "REASON_CODES", Types.VARCHAR },
			{ "TOTAL_DISCOUNT_PERCENT_PROJECTED", Types.DOUBLE },
			{ "TOTAL_DISCOUNT_PERCENT_PRIOR", Types.DOUBLE },
			{ "NET_SALES_PROJECTED", Types.DOUBLE },
			{ "ST_NM_ASSUMPTIONS_SID", Types.VARCHAR },
			{ "GROSS_SALES_PROJECTED", Types.DOUBLE },
			{ "SESSION_ID", Types.INTEGER },
			{ "GROSS_SALES_PRIOR", Types.DOUBLE },
			{ "IS_CHECKED", Types.BOOLEAN },
			{ "CAM_ID", Types.INTEGER },
			{ "NET_SALES_PERCENT_CHANGE", Types.DOUBLE },
			{ "SEGMENT", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("LAST_MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("PARENT", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("PROJECTION_PERIOD", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("COMMENTARY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("NM_ASSUMPTIONS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("PROJECTION_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("NET_SALES_PRIOR", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("USER_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("GROSS_SALES_PERCENT_CHANGE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_PERCENT_CHANGE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("REASON_CODES", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_PERCENT_PROJECTED", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("TOTAL_DISCOUNT_PERCENT_PRIOR", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("NET_SALES_PROJECTED", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("ST_NM_ASSUMPTIONS_SID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("GROSS_SALES_PROJECTED", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("SESSION_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("GROSS_SALES_PRIOR", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("IS_CHECKED", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("CAM_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("NET_SALES_PERCENT_CHANGE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("SEGMENT", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table ST_NM_ASSUMPTIONS (LAST_MODIFIED_DATE DATE null,PARENT BOOLEAN,PROJECTION_PERIOD INTEGER not null IDENTITY,COMMENTARY VARCHAR(75) null,NM_ASSUMPTIONS_SID INTEGER not null IDENTITY,PROJECTION_DETAILS_SID INTEGER not null IDENTITY,NET_SALES_PRIOR DOUBLE,USER_ID INTEGER not null IDENTITY,GROSS_SALES_PERCENT_CHANGE DOUBLE,TOTAL_DISCOUNT_PERCENT_CHANGE DOUBLE,REASON_CODES VARCHAR(75) null,TOTAL_DISCOUNT_PERCENT_PROJECTED DOUBLE,TOTAL_DISCOUNT_PERCENT_PRIOR DOUBLE,NET_SALES_PROJECTED DOUBLE,ST_NM_ASSUMPTIONS_SID VARCHAR(75) not null IDENTITY,GROSS_SALES_PROJECTED DOUBLE,SESSION_ID INTEGER not null IDENTITY,GROSS_SALES_PRIOR DOUBLE,IS_CHECKED BOOLEAN,CAM_ID INTEGER,NET_SALES_PERCENT_CHANGE DOUBLE,SEGMENT VARCHAR(75) null,primary key (PROJECTION_PERIOD, NM_ASSUMPTIONS_SID, PROJECTION_DETAILS_SID, USER_ID, ST_NM_ASSUMPTIONS_SID, SESSION_ID))";
	public static final String TABLE_SQL_DROP = "drop table ST_NM_ASSUMPTIONS";
	public static final String ORDER_BY_JPQL = " ORDER BY stNmAssumptions.id.projectionPeriod ASC, stNmAssumptions.id.nmAssumptionsSid ASC, stNmAssumptions.id.projectionDetailsSid ASC, stNmAssumptions.id.userId ASC, stNmAssumptions.id.stNmAssumptionsSid ASC, stNmAssumptions.id.sessionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ST_NM_ASSUMPTIONS.PROJECTION_PERIOD ASC, ST_NM_ASSUMPTIONS.NM_ASSUMPTIONS_SID ASC, ST_NM_ASSUMPTIONS.PROJECTION_DETAILS_SID ASC, ST_NM_ASSUMPTIONS.USER_ID ASC, ST_NM_ASSUMPTIONS.ST_NM_ASSUMPTIONS_SID ASC, ST_NM_ASSUMPTIONS.SESSION_ID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.StNmAssumptions"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.StNmAssumptions"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.StNmAssumptions"));

	public StNmAssumptionsModelImpl() {
	}

	@Override
	public StNmAssumptionsPK getPrimaryKey() {
		return new StNmAssumptionsPK(_projectionPeriod, _nmAssumptionsSid,
			_projectionDetailsSid, _userId, _stNmAssumptionsSid, _sessionId);
	}

	@Override
	public void setPrimaryKey(StNmAssumptionsPK primaryKey) {
		setProjectionPeriod(primaryKey.projectionPeriod);
		setNmAssumptionsSid(primaryKey.nmAssumptionsSid);
		setProjectionDetailsSid(primaryKey.projectionDetailsSid);
		setUserId(primaryKey.userId);
		setStNmAssumptionsSid(primaryKey.stNmAssumptionsSid);
		setSessionId(primaryKey.sessionId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new StNmAssumptionsPK(_projectionPeriod, _nmAssumptionsSid,
			_projectionDetailsSid, _userId, _stNmAssumptionsSid, _sessionId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((StNmAssumptionsPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return StNmAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return StNmAssumptions.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("parent", getParent());
		attributes.put("projectionPeriod", getProjectionPeriod());
		attributes.put("commentary", getCommentary());
		attributes.put("nmAssumptionsSid", getNmAssumptionsSid());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("netSalesPrior", getNetSalesPrior());
		attributes.put("userId", getUserId());
		attributes.put("grossSalesPercentChange", getGrossSalesPercentChange());
		attributes.put("totalDiscountPercentChange",
			getTotalDiscountPercentChange());
		attributes.put("reasonCodes", getReasonCodes());
		attributes.put("totalDiscountPercentProjected",
			getTotalDiscountPercentProjected());
		attributes.put("totalDiscountPercentPrior",
			getTotalDiscountPercentPrior());
		attributes.put("netSalesProjected", getNetSalesProjected());
		attributes.put("stNmAssumptionsSid", getStNmAssumptionsSid());
		attributes.put("grossSalesProjected", getGrossSalesProjected());
		attributes.put("sessionId", getSessionId());
		attributes.put("grossSalesPrior", getGrossSalesPrior());
		attributes.put("isChecked", getIsChecked());
		attributes.put("camId", getCamId());
		attributes.put("netSalesPercentChange", getNetSalesPercentChange());
		attributes.put("segment", getSegment());

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

		Boolean parent = (Boolean)attributes.get("parent");

		if (parent != null) {
			setParent(parent);
		}

		Integer projectionPeriod = (Integer)attributes.get("projectionPeriod");

		if (projectionPeriod != null) {
			setProjectionPeriod(projectionPeriod);
		}

		String commentary = (String)attributes.get("commentary");

		if (commentary != null) {
			setCommentary(commentary);
		}

		Integer nmAssumptionsSid = (Integer)attributes.get("nmAssumptionsSid");

		if (nmAssumptionsSid != null) {
			setNmAssumptionsSid(nmAssumptionsSid);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double netSalesPrior = (Double)attributes.get("netSalesPrior");

		if (netSalesPrior != null) {
			setNetSalesPrior(netSalesPrior);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Double grossSalesPercentChange = (Double)attributes.get(
				"grossSalesPercentChange");

		if (grossSalesPercentChange != null) {
			setGrossSalesPercentChange(grossSalesPercentChange);
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

		Double netSalesProjected = (Double)attributes.get("netSalesProjected");

		if (netSalesProjected != null) {
			setNetSalesProjected(netSalesProjected);
		}

		String stNmAssumptionsSid = (String)attributes.get("stNmAssumptionsSid");

		if (stNmAssumptionsSid != null) {
			setStNmAssumptionsSid(stNmAssumptionsSid);
		}

		Double grossSalesProjected = (Double)attributes.get(
				"grossSalesProjected");

		if (grossSalesProjected != null) {
			setGrossSalesProjected(grossSalesProjected);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double grossSalesPrior = (Double)attributes.get("grossSalesPrior");

		if (grossSalesPrior != null) {
			setGrossSalesPrior(grossSalesPrior);
		}

		Boolean isChecked = (Boolean)attributes.get("isChecked");

		if (isChecked != null) {
			setIsChecked(isChecked);
		}

		Integer camId = (Integer)attributes.get("camId");

		if (camId != null) {
			setCamId(camId);
		}

		Double netSalesPercentChange = (Double)attributes.get(
				"netSalesPercentChange");

		if (netSalesPercentChange != null) {
			setNetSalesPercentChange(netSalesPercentChange);
		}

		String segment = (String)attributes.get("segment");

		if (segment != null) {
			setSegment(segment);
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
	public int getProjectionPeriod() {
		return _projectionPeriod;
	}

	@Override
	public void setProjectionPeriod(int projectionPeriod) {
		_projectionPeriod = projectionPeriod;
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
	public int getNmAssumptionsSid() {
		return _nmAssumptionsSid;
	}

	@Override
	public void setNmAssumptionsSid(int nmAssumptionsSid) {
		_nmAssumptionsSid = nmAssumptionsSid;
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
	public double getNetSalesPrior() {
		return _netSalesPrior;
	}

	@Override
	public void setNetSalesPrior(double netSalesPrior) {
		_netSalesPrior = netSalesPrior;
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
	public double getGrossSalesPercentChange() {
		return _grossSalesPercentChange;
	}

	@Override
	public void setGrossSalesPercentChange(double grossSalesPercentChange) {
		_grossSalesPercentChange = grossSalesPercentChange;
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
	public double getNetSalesProjected() {
		return _netSalesProjected;
	}

	@Override
	public void setNetSalesProjected(double netSalesProjected) {
		_netSalesProjected = netSalesProjected;
	}

	@Override
	public String getStNmAssumptionsSid() {
		if (_stNmAssumptionsSid == null) {
			return StringPool.BLANK;
		}
		else {
			return _stNmAssumptionsSid;
		}
	}

	@Override
	public void setStNmAssumptionsSid(String stNmAssumptionsSid) {
		_stNmAssumptionsSid = stNmAssumptionsSid;
	}

	@Override
	public double getGrossSalesProjected() {
		return _grossSalesProjected;
	}

	@Override
	public void setGrossSalesProjected(double grossSalesProjected) {
		_grossSalesProjected = grossSalesProjected;
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
	public double getGrossSalesPrior() {
		return _grossSalesPrior;
	}

	@Override
	public void setGrossSalesPrior(double grossSalesPrior) {
		_grossSalesPrior = grossSalesPrior;
	}

	@Override
	public boolean getIsChecked() {
		return _isChecked;
	}

	@Override
	public boolean isIsChecked() {
		return _isChecked;
	}

	@Override
	public void setIsChecked(boolean isChecked) {
		_isChecked = isChecked;
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
	public double getNetSalesPercentChange() {
		return _netSalesPercentChange;
	}

	@Override
	public void setNetSalesPercentChange(double netSalesPercentChange) {
		_netSalesPercentChange = netSalesPercentChange;
	}

	@Override
	public String getSegment() {
		if (_segment == null) {
			return StringPool.BLANK;
		}
		else {
			return _segment;
		}
	}

	@Override
	public void setSegment(String segment) {
		_segment = segment;
	}

	@Override
	public StNmAssumptions toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (StNmAssumptions)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StNmAssumptionsImpl stNmAssumptionsImpl = new StNmAssumptionsImpl();

		stNmAssumptionsImpl.setLastModifiedDate(getLastModifiedDate());
		stNmAssumptionsImpl.setParent(getParent());
		stNmAssumptionsImpl.setProjectionPeriod(getProjectionPeriod());
		stNmAssumptionsImpl.setCommentary(getCommentary());
		stNmAssumptionsImpl.setNmAssumptionsSid(getNmAssumptionsSid());
		stNmAssumptionsImpl.setProjectionDetailsSid(getProjectionDetailsSid());
		stNmAssumptionsImpl.setNetSalesPrior(getNetSalesPrior());
		stNmAssumptionsImpl.setUserId(getUserId());
		stNmAssumptionsImpl.setGrossSalesPercentChange(getGrossSalesPercentChange());
		stNmAssumptionsImpl.setTotalDiscountPercentChange(getTotalDiscountPercentChange());
		stNmAssumptionsImpl.setReasonCodes(getReasonCodes());
		stNmAssumptionsImpl.setTotalDiscountPercentProjected(getTotalDiscountPercentProjected());
		stNmAssumptionsImpl.setTotalDiscountPercentPrior(getTotalDiscountPercentPrior());
		stNmAssumptionsImpl.setNetSalesProjected(getNetSalesProjected());
		stNmAssumptionsImpl.setStNmAssumptionsSid(getStNmAssumptionsSid());
		stNmAssumptionsImpl.setGrossSalesProjected(getGrossSalesProjected());
		stNmAssumptionsImpl.setSessionId(getSessionId());
		stNmAssumptionsImpl.setGrossSalesPrior(getGrossSalesPrior());
		stNmAssumptionsImpl.setIsChecked(getIsChecked());
		stNmAssumptionsImpl.setCamId(getCamId());
		stNmAssumptionsImpl.setNetSalesPercentChange(getNetSalesPercentChange());
		stNmAssumptionsImpl.setSegment(getSegment());

		stNmAssumptionsImpl.resetOriginalValues();

		return stNmAssumptionsImpl;
	}

	@Override
	public int compareTo(StNmAssumptions stNmAssumptions) {
		StNmAssumptionsPK primaryKey = stNmAssumptions.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmAssumptions)) {
			return false;
		}

		StNmAssumptions stNmAssumptions = (StNmAssumptions)obj;

		StNmAssumptionsPK primaryKey = stNmAssumptions.getPrimaryKey();

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
	public CacheModel<StNmAssumptions> toCacheModel() {
		StNmAssumptionsCacheModel stNmAssumptionsCacheModel = new StNmAssumptionsCacheModel();

		stNmAssumptionsCacheModel.stNmAssumptionsPK = getPrimaryKey();

		Date lastModifiedDate = getLastModifiedDate();

		if (lastModifiedDate != null) {
			stNmAssumptionsCacheModel.lastModifiedDate = lastModifiedDate.getTime();
		}
		else {
			stNmAssumptionsCacheModel.lastModifiedDate = Long.MIN_VALUE;
		}

		stNmAssumptionsCacheModel.parent = getParent();

		stNmAssumptionsCacheModel.projectionPeriod = getProjectionPeriod();

		stNmAssumptionsCacheModel.commentary = getCommentary();

		String commentary = stNmAssumptionsCacheModel.commentary;

		if ((commentary != null) && (commentary.length() == 0)) {
			stNmAssumptionsCacheModel.commentary = null;
		}

		stNmAssumptionsCacheModel.nmAssumptionsSid = getNmAssumptionsSid();

		stNmAssumptionsCacheModel.projectionDetailsSid = getProjectionDetailsSid();

		stNmAssumptionsCacheModel.netSalesPrior = getNetSalesPrior();

		stNmAssumptionsCacheModel.userId = getUserId();

		stNmAssumptionsCacheModel.grossSalesPercentChange = getGrossSalesPercentChange();

		stNmAssumptionsCacheModel.totalDiscountPercentChange = getTotalDiscountPercentChange();

		stNmAssumptionsCacheModel.reasonCodes = getReasonCodes();

		String reasonCodes = stNmAssumptionsCacheModel.reasonCodes;

		if ((reasonCodes != null) && (reasonCodes.length() == 0)) {
			stNmAssumptionsCacheModel.reasonCodes = null;
		}

		stNmAssumptionsCacheModel.totalDiscountPercentProjected = getTotalDiscountPercentProjected();

		stNmAssumptionsCacheModel.totalDiscountPercentPrior = getTotalDiscountPercentPrior();

		stNmAssumptionsCacheModel.netSalesProjected = getNetSalesProjected();

		stNmAssumptionsCacheModel.stNmAssumptionsSid = getStNmAssumptionsSid();

		String stNmAssumptionsSid = stNmAssumptionsCacheModel.stNmAssumptionsSid;

		if ((stNmAssumptionsSid != null) && (stNmAssumptionsSid.length() == 0)) {
			stNmAssumptionsCacheModel.stNmAssumptionsSid = null;
		}

		stNmAssumptionsCacheModel.grossSalesProjected = getGrossSalesProjected();

		stNmAssumptionsCacheModel.sessionId = getSessionId();

		stNmAssumptionsCacheModel.grossSalesPrior = getGrossSalesPrior();

		stNmAssumptionsCacheModel.isChecked = getIsChecked();

		stNmAssumptionsCacheModel.camId = getCamId();

		stNmAssumptionsCacheModel.netSalesPercentChange = getNetSalesPercentChange();

		stNmAssumptionsCacheModel.segment = getSegment();

		String segment = stNmAssumptionsCacheModel.segment;

		if ((segment != null) && (segment.length() == 0)) {
			stNmAssumptionsCacheModel.segment = null;
		}

		return stNmAssumptionsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{lastModifiedDate=");
		sb.append(getLastModifiedDate());
		sb.append(", parent=");
		sb.append(getParent());
		sb.append(", projectionPeriod=");
		sb.append(getProjectionPeriod());
		sb.append(", commentary=");
		sb.append(getCommentary());
		sb.append(", nmAssumptionsSid=");
		sb.append(getNmAssumptionsSid());
		sb.append(", projectionDetailsSid=");
		sb.append(getProjectionDetailsSid());
		sb.append(", netSalesPrior=");
		sb.append(getNetSalesPrior());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", grossSalesPercentChange=");
		sb.append(getGrossSalesPercentChange());
		sb.append(", totalDiscountPercentChange=");
		sb.append(getTotalDiscountPercentChange());
		sb.append(", reasonCodes=");
		sb.append(getReasonCodes());
		sb.append(", totalDiscountPercentProjected=");
		sb.append(getTotalDiscountPercentProjected());
		sb.append(", totalDiscountPercentPrior=");
		sb.append(getTotalDiscountPercentPrior());
		sb.append(", netSalesProjected=");
		sb.append(getNetSalesProjected());
		sb.append(", stNmAssumptionsSid=");
		sb.append(getStNmAssumptionsSid());
		sb.append(", grossSalesProjected=");
		sb.append(getGrossSalesProjected());
		sb.append(", sessionId=");
		sb.append(getSessionId());
		sb.append(", grossSalesPrior=");
		sb.append(getGrossSalesPrior());
		sb.append(", isChecked=");
		sb.append(getIsChecked());
		sb.append(", camId=");
		sb.append(getCamId());
		sb.append(", netSalesPercentChange=");
		sb.append(getNetSalesPercentChange());
		sb.append(", segment=");
		sb.append(getSegment());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(70);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.StNmAssumptions");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
		sb.append(getLastModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parent</column-name><column-value><![CDATA[");
		sb.append(getParent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectionPeriod</column-name><column-value><![CDATA[");
		sb.append(getProjectionPeriod());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commentary</column-name><column-value><![CDATA[");
		sb.append(getCommentary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nmAssumptionsSid</column-name><column-value><![CDATA[");
		sb.append(getNmAssumptionsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getProjectionDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>netSalesPrior</column-name><column-value><![CDATA[");
		sb.append(getNetSalesPrior());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>grossSalesPercentChange</column-name><column-value><![CDATA[");
		sb.append(getGrossSalesPercentChange());
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
			"<column><column-name>totalDiscountPercentProjected</column-name><column-value><![CDATA[");
		sb.append(getTotalDiscountPercentProjected());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalDiscountPercentPrior</column-name><column-value><![CDATA[");
		sb.append(getTotalDiscountPercentPrior());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>netSalesProjected</column-name><column-value><![CDATA[");
		sb.append(getNetSalesProjected());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stNmAssumptionsSid</column-name><column-value><![CDATA[");
		sb.append(getStNmAssumptionsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>grossSalesProjected</column-name><column-value><![CDATA[");
		sb.append(getGrossSalesProjected());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionId</column-name><column-value><![CDATA[");
		sb.append(getSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>grossSalesPrior</column-name><column-value><![CDATA[");
		sb.append(getGrossSalesPrior());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isChecked</column-name><column-value><![CDATA[");
		sb.append(getIsChecked());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>camId</column-name><column-value><![CDATA[");
		sb.append(getCamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>netSalesPercentChange</column-name><column-value><![CDATA[");
		sb.append(getNetSalesPercentChange());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>segment</column-name><column-value><![CDATA[");
		sb.append(getSegment());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = StNmAssumptions.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			StNmAssumptions.class
		};
	private Date _lastModifiedDate;
	private boolean _parent;
	private int _projectionPeriod;
	private String _commentary;
	private int _nmAssumptionsSid;
	private int _projectionDetailsSid;
	private double _netSalesPrior;
	private int _userId;
	private double _grossSalesPercentChange;
	private double _totalDiscountPercentChange;
	private String _reasonCodes;
	private double _totalDiscountPercentProjected;
	private double _totalDiscountPercentPrior;
	private double _netSalesProjected;
	private String _stNmAssumptionsSid;
	private double _grossSalesProjected;
	private int _sessionId;
	private double _grossSalesPrior;
	private boolean _isChecked;
	private int _camId;
	private double _netSalesPercentChange;
	private String _segment;
	private StNmAssumptions _escapedModel;
}