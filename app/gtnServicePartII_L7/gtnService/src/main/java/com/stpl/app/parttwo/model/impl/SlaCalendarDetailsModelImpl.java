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

import com.stpl.app.parttwo.model.SlaCalendarDetails;
import com.stpl.app.parttwo.model.SlaCalendarDetailsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the SlaCalendarDetails service. Represents a row in the &quot;SLA_CALENDAR_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SlaCalendarDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SlaCalendarDetailsImpl}.
 * </p>
 *
 * @author
 * @see SlaCalendarDetailsImpl
 * @see SlaCalendarDetails
 * @see SlaCalendarDetailsModel
 * @generated
 */
@ProviderType
public class SlaCalendarDetailsModelImpl extends BaseModelImpl<SlaCalendarDetails>
	implements SlaCalendarDetailsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sla calendar details model instance should use the {@link SlaCalendarDetails} interface instead.
	 */
	public static final String TABLE_NAME = "SLA_CALENDAR_DETAILS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "CREATED_BY", Types.INTEGER },
			{ "SLA_CALENDAR_MASTER_SID", Types.INTEGER },
			{ "HOLIDAY_YEAR", Types.VARCHAR },
			{ "SLA_CALENDAR_DETAILS_SID", Types.INTEGER },
			{ "MODIFIED_BY", Types.INTEGER },
			{ "INBOUND_STATUS", Types.VARCHAR },
			{ "HOLIDAY_DAY", Types.VARCHAR },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "HOLIDAY_COMBINED", Types.TIMESTAMP },
			{ "HOLIDAY_MONTH", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("SLA_CALENDAR_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("HOLIDAY_YEAR", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("SLA_CALENDAR_DETAILS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("INBOUND_STATUS", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("HOLIDAY_DAY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("HOLIDAY_COMBINED", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("HOLIDAY_MONTH", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table SLA_CALENDAR_DETAILS (CREATED_DATE DATE null,CREATED_BY INTEGER,SLA_CALENDAR_MASTER_SID INTEGER,HOLIDAY_YEAR VARCHAR(75) null,SLA_CALENDAR_DETAILS_SID INTEGER not null primary key IDENTITY,MODIFIED_BY INTEGER,INBOUND_STATUS VARCHAR(75) null,HOLIDAY_DAY VARCHAR(75) null,MODIFIED_DATE DATE null,HOLIDAY_COMBINED DATE null,HOLIDAY_MONTH VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table SLA_CALENDAR_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY slaCalendarDetails.slaCalendarDetailsSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY SLA_CALENDAR_DETAILS.SLA_CALENDAR_DETAILS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.parttwo.model.SlaCalendarDetails"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.parttwo.model.SlaCalendarDetails"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.parttwo.model.SlaCalendarDetails"));

	public SlaCalendarDetailsModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _slaCalendarDetailsSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setSlaCalendarDetailsSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _slaCalendarDetailsSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SlaCalendarDetails.class;
	}

	@Override
	public String getModelClassName() {
		return SlaCalendarDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("slaCalendarMasterSid", getSlaCalendarMasterSid());
		attributes.put("holidayYear", getHolidayYear());
		attributes.put("slaCalendarDetailsSid", getSlaCalendarDetailsSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("holidayDay", getHolidayDay());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("holidayCombined", getHolidayCombined());
		attributes.put("holidayMonth", getHolidayMonth());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer slaCalendarMasterSid = (Integer)attributes.get(
				"slaCalendarMasterSid");

		if (slaCalendarMasterSid != null) {
			setSlaCalendarMasterSid(slaCalendarMasterSid);
		}

		String holidayYear = (String)attributes.get("holidayYear");

		if (holidayYear != null) {
			setHolidayYear(holidayYear);
		}

		Integer slaCalendarDetailsSid = (Integer)attributes.get(
				"slaCalendarDetailsSid");

		if (slaCalendarDetailsSid != null) {
			setSlaCalendarDetailsSid(slaCalendarDetailsSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String holidayDay = (String)attributes.get("holidayDay");

		if (holidayDay != null) {
			setHolidayDay(holidayDay);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date holidayCombined = (Date)attributes.get("holidayCombined");

		if (holidayCombined != null) {
			setHolidayCombined(holidayCombined);
		}

		String holidayMonth = (String)attributes.get("holidayMonth");

		if (holidayMonth != null) {
			setHolidayMonth(holidayMonth);
		}
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
	public int getSlaCalendarMasterSid() {
		return _slaCalendarMasterSid;
	}

	@Override
	public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
		_slaCalendarMasterSid = slaCalendarMasterSid;
	}

	@Override
	public String getHolidayYear() {
		if (_holidayYear == null) {
			return StringPool.BLANK;
		}
		else {
			return _holidayYear;
		}
	}

	@Override
	public void setHolidayYear(String holidayYear) {
		_holidayYear = holidayYear;
	}

	@Override
	public int getSlaCalendarDetailsSid() {
		return _slaCalendarDetailsSid;
	}

	@Override
	public void setSlaCalendarDetailsSid(int slaCalendarDetailsSid) {
		_slaCalendarDetailsSid = slaCalendarDetailsSid;
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
	public String getInboundStatus() {
		if (_inboundStatus == null) {
			return StringPool.BLANK;
		}
		else {
			return _inboundStatus;
		}
	}

	@Override
	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	@Override
	public String getHolidayDay() {
		if (_holidayDay == null) {
			return StringPool.BLANK;
		}
		else {
			return _holidayDay;
		}
	}

	@Override
	public void setHolidayDay(String holidayDay) {
		_holidayDay = holidayDay;
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
	public Date getHolidayCombined() {
		return _holidayCombined;
	}

	@Override
	public void setHolidayCombined(Date holidayCombined) {
		_holidayCombined = holidayCombined;
	}

	@Override
	public String getHolidayMonth() {
		if (_holidayMonth == null) {
			return StringPool.BLANK;
		}
		else {
			return _holidayMonth;
		}
	}

	@Override
	public void setHolidayMonth(String holidayMonth) {
		_holidayMonth = holidayMonth;
	}

	@Override
	public SlaCalendarDetails toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SlaCalendarDetails)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SlaCalendarDetailsImpl slaCalendarDetailsImpl = new SlaCalendarDetailsImpl();

		slaCalendarDetailsImpl.setCreatedDate(getCreatedDate());
		slaCalendarDetailsImpl.setCreatedBy(getCreatedBy());
		slaCalendarDetailsImpl.setSlaCalendarMasterSid(getSlaCalendarMasterSid());
		slaCalendarDetailsImpl.setHolidayYear(getHolidayYear());
		slaCalendarDetailsImpl.setSlaCalendarDetailsSid(getSlaCalendarDetailsSid());
		slaCalendarDetailsImpl.setModifiedBy(getModifiedBy());
		slaCalendarDetailsImpl.setInboundStatus(getInboundStatus());
		slaCalendarDetailsImpl.setHolidayDay(getHolidayDay());
		slaCalendarDetailsImpl.setModifiedDate(getModifiedDate());
		slaCalendarDetailsImpl.setHolidayCombined(getHolidayCombined());
		slaCalendarDetailsImpl.setHolidayMonth(getHolidayMonth());

		slaCalendarDetailsImpl.resetOriginalValues();

		return slaCalendarDetailsImpl;
	}

	@Override
	public int compareTo(SlaCalendarDetails slaCalendarDetails) {
		int primaryKey = slaCalendarDetails.getPrimaryKey();

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

		if (!(obj instanceof SlaCalendarDetails)) {
			return false;
		}

		SlaCalendarDetails slaCalendarDetails = (SlaCalendarDetails)obj;

		int primaryKey = slaCalendarDetails.getPrimaryKey();

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
	public CacheModel<SlaCalendarDetails> toCacheModel() {
		SlaCalendarDetailsCacheModel slaCalendarDetailsCacheModel = new SlaCalendarDetailsCacheModel();

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			slaCalendarDetailsCacheModel.createdDate = createdDate.getTime();
		}
		else {
			slaCalendarDetailsCacheModel.createdDate = Long.MIN_VALUE;
		}

		slaCalendarDetailsCacheModel.createdBy = getCreatedBy();

		slaCalendarDetailsCacheModel.slaCalendarMasterSid = getSlaCalendarMasterSid();

		slaCalendarDetailsCacheModel.holidayYear = getHolidayYear();

		String holidayYear = slaCalendarDetailsCacheModel.holidayYear;

		if ((holidayYear != null) && (holidayYear.length() == 0)) {
			slaCalendarDetailsCacheModel.holidayYear = null;
		}

		slaCalendarDetailsCacheModel.slaCalendarDetailsSid = getSlaCalendarDetailsSid();

		slaCalendarDetailsCacheModel.modifiedBy = getModifiedBy();

		slaCalendarDetailsCacheModel.inboundStatus = getInboundStatus();

		String inboundStatus = slaCalendarDetailsCacheModel.inboundStatus;

		if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
			slaCalendarDetailsCacheModel.inboundStatus = null;
		}

		slaCalendarDetailsCacheModel.holidayDay = getHolidayDay();

		String holidayDay = slaCalendarDetailsCacheModel.holidayDay;

		if ((holidayDay != null) && (holidayDay.length() == 0)) {
			slaCalendarDetailsCacheModel.holidayDay = null;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			slaCalendarDetailsCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			slaCalendarDetailsCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		Date holidayCombined = getHolidayCombined();

		if (holidayCombined != null) {
			slaCalendarDetailsCacheModel.holidayCombined = holidayCombined.getTime();
		}
		else {
			slaCalendarDetailsCacheModel.holidayCombined = Long.MIN_VALUE;
		}

		slaCalendarDetailsCacheModel.holidayMonth = getHolidayMonth();

		String holidayMonth = slaCalendarDetailsCacheModel.holidayMonth;

		if ((holidayMonth != null) && (holidayMonth.length() == 0)) {
			slaCalendarDetailsCacheModel.holidayMonth = null;
		}

		return slaCalendarDetailsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{createdDate=");
		sb.append(getCreatedDate());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", slaCalendarMasterSid=");
		sb.append(getSlaCalendarMasterSid());
		sb.append(", holidayYear=");
		sb.append(getHolidayYear());
		sb.append(", slaCalendarDetailsSid=");
		sb.append(getSlaCalendarDetailsSid());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", inboundStatus=");
		sb.append(getInboundStatus());
		sb.append(", holidayDay=");
		sb.append(getHolidayDay());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", holidayCombined=");
		sb.append(getHolidayCombined());
		sb.append(", holidayMonth=");
		sb.append(getHolidayMonth());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.parttwo.model.SlaCalendarDetails");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>createdDate</column-name><column-value><![CDATA[");
		sb.append(getCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>slaCalendarMasterSid</column-name><column-value><![CDATA[");
		sb.append(getSlaCalendarMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>holidayYear</column-name><column-value><![CDATA[");
		sb.append(getHolidayYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>slaCalendarDetailsSid</column-name><column-value><![CDATA[");
		sb.append(getSlaCalendarDetailsSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
		sb.append(getInboundStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>holidayDay</column-name><column-value><![CDATA[");
		sb.append(getHolidayDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>holidayCombined</column-name><column-value><![CDATA[");
		sb.append(getHolidayCombined());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>holidayMonth</column-name><column-value><![CDATA[");
		sb.append(getHolidayMonth());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SlaCalendarDetails.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SlaCalendarDetails.class
		};
	private Date _createdDate;
	private int _createdBy;
	private int _slaCalendarMasterSid;
	private String _holidayYear;
	private int _slaCalendarDetailsSid;
	private int _modifiedBy;
	private String _inboundStatus;
	private String _holidayDay;
	private Date _modifiedDate;
	private Date _holidayCombined;
	private String _holidayMonth;
	private SlaCalendarDetails _escapedModel;
}