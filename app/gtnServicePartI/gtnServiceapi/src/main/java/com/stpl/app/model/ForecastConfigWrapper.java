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

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ForecastConfig}.
 * </p>
 *
 * @author
 * @see ForecastConfig
 * @generated
 */
@ProviderType
public class ForecastConfigWrapper implements ForecastConfig,
	ModelWrapper<ForecastConfig> {
	public ForecastConfigWrapper(ForecastConfig forecastConfig) {
		_forecastConfig = forecastConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return ForecastConfig.class;
	}

	@Override
	public String getModelClassName() {
		return ForecastConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("processType", getProcessType());
		attributes.put("toDate", getToDate());
		attributes.put("versionNo", getVersionNo());
		attributes.put("forecastConfigSid", getForecastConfigSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fromDate", getFromDate());
		attributes.put("projValue", getProjValue());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("projFreq", getProjFreq());
		attributes.put("histValue", getHistValue());
		attributes.put("businessProcessType", getBusinessProcessType());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("histFreq", getHistFreq());
		attributes.put("activeStartDate", getActiveStartDate());
		attributes.put("activeEndDate", getActiveEndDate());
		attributes.put("processMode", getProcessMode());
		attributes.put("historicalDataIntervalFrom",
			getHistoricalDataIntervalFrom());
		attributes.put("historicalTimePeriodFrom", getHistoricalTimePeriodFrom());
		attributes.put("projHistFreq", getProjHistFreq());
		attributes.put("futureTimePeriodFrom", getFutureTimePeriodFrom());
		attributes.put("historicalDataIntervalTo", getHistoricalDataIntervalTo());
		attributes.put("projHistValue", getProjHistValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean processType = (Boolean)attributes.get("processType");

		if (processType != null) {
			setProcessType(processType);
		}

		Date toDate = (Date)attributes.get("toDate");

		if (toDate != null) {
			setToDate(toDate);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer forecastConfigSid = (Integer)attributes.get("forecastConfigSid");

		if (forecastConfigSid != null) {
			setForecastConfigSid(forecastConfigSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		Integer projValue = (Integer)attributes.get("projValue");

		if (projValue != null) {
			setProjValue(projValue);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer projFreq = (Integer)attributes.get("projFreq");

		if (projFreq != null) {
			setProjFreq(projFreq);
		}

		Integer histValue = (Integer)attributes.get("histValue");

		if (histValue != null) {
			setHistValue(histValue);
		}

		Integer businessProcessType = (Integer)attributes.get(
				"businessProcessType");

		if (businessProcessType != null) {
			setBusinessProcessType(businessProcessType);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer histFreq = (Integer)attributes.get("histFreq");

		if (histFreq != null) {
			setHistFreq(histFreq);
		}

		Date activeStartDate = (Date)attributes.get("activeStartDate");

		if (activeStartDate != null) {
			setActiveStartDate(activeStartDate);
		}

		Date activeEndDate = (Date)attributes.get("activeEndDate");

		if (activeEndDate != null) {
			setActiveEndDate(activeEndDate);
		}

		Boolean processMode = (Boolean)attributes.get("processMode");

		if (processMode != null) {
			setProcessMode(processMode);
		}

		Date historicalDataIntervalFrom = (Date)attributes.get(
				"historicalDataIntervalFrom");

		if (historicalDataIntervalFrom != null) {
			setHistoricalDataIntervalFrom(historicalDataIntervalFrom);
		}

		Date historicalTimePeriodFrom = (Date)attributes.get(
				"historicalTimePeriodFrom");

		if (historicalTimePeriodFrom != null) {
			setHistoricalTimePeriodFrom(historicalTimePeriodFrom);
		}

		Integer projHistFreq = (Integer)attributes.get("projHistFreq");

		if (projHistFreq != null) {
			setProjHistFreq(projHistFreq);
		}

		Date futureTimePeriodFrom = (Date)attributes.get("futureTimePeriodFrom");

		if (futureTimePeriodFrom != null) {
			setFutureTimePeriodFrom(futureTimePeriodFrom);
		}

		Date historicalDataIntervalTo = (Date)attributes.get(
				"historicalDataIntervalTo");

		if (historicalDataIntervalTo != null) {
			setHistoricalDataIntervalTo(historicalDataIntervalTo);
		}

		Integer projHistValue = (Integer)attributes.get("projHistValue");

		if (projHistValue != null) {
			setProjHistValue(projHistValue);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ForecastConfigWrapper((ForecastConfig)_forecastConfig.clone());
	}

	@Override
	public int compareTo(ForecastConfig forecastConfig) {
		return _forecastConfig.compareTo(forecastConfig);
	}

	/**
	* Returns the active end date of this forecast config.
	*
	* @return the active end date of this forecast config
	*/
	@Override
	public Date getActiveEndDate() {
		return _forecastConfig.getActiveEndDate();
	}

	/**
	* Returns the active start date of this forecast config.
	*
	* @return the active start date of this forecast config
	*/
	@Override
	public Date getActiveStartDate() {
		return _forecastConfig.getActiveStartDate();
	}

	/**
	* Returns the business process type of this forecast config.
	*
	* @return the business process type of this forecast config
	*/
	@Override
	public int getBusinessProcessType() {
		return _forecastConfig.getBusinessProcessType();
	}

	/**
	* Returns the created by of this forecast config.
	*
	* @return the created by of this forecast config
	*/
	@Override
	public int getCreatedBy() {
		return _forecastConfig.getCreatedBy();
	}

	/**
	* Returns the created date of this forecast config.
	*
	* @return the created date of this forecast config
	*/
	@Override
	public Date getCreatedDate() {
		return _forecastConfig.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _forecastConfig.getExpandoBridge();
	}

	/**
	* Returns the forecast config sid of this forecast config.
	*
	* @return the forecast config sid of this forecast config
	*/
	@Override
	public int getForecastConfigSid() {
		return _forecastConfig.getForecastConfigSid();
	}

	/**
	* Returns the from date of this forecast config.
	*
	* @return the from date of this forecast config
	*/
	@Override
	public Date getFromDate() {
		return _forecastConfig.getFromDate();
	}

	/**
	* Returns the future time period from of this forecast config.
	*
	* @return the future time period from of this forecast config
	*/
	@Override
	public Date getFutureTimePeriodFrom() {
		return _forecastConfig.getFutureTimePeriodFrom();
	}

	/**
	* Returns the hist freq of this forecast config.
	*
	* @return the hist freq of this forecast config
	*/
	@Override
	public int getHistFreq() {
		return _forecastConfig.getHistFreq();
	}

	/**
	* Returns the historical data interval from of this forecast config.
	*
	* @return the historical data interval from of this forecast config
	*/
	@Override
	public Date getHistoricalDataIntervalFrom() {
		return _forecastConfig.getHistoricalDataIntervalFrom();
	}

	/**
	* Returns the historical data interval to of this forecast config.
	*
	* @return the historical data interval to of this forecast config
	*/
	@Override
	public Date getHistoricalDataIntervalTo() {
		return _forecastConfig.getHistoricalDataIntervalTo();
	}

	/**
	* Returns the historical time period from of this forecast config.
	*
	* @return the historical time period from of this forecast config
	*/
	@Override
	public Date getHistoricalTimePeriodFrom() {
		return _forecastConfig.getHistoricalTimePeriodFrom();
	}

	/**
	* Returns the hist value of this forecast config.
	*
	* @return the hist value of this forecast config
	*/
	@Override
	public int getHistValue() {
		return _forecastConfig.getHistValue();
	}

	/**
	* Returns the modified by of this forecast config.
	*
	* @return the modified by of this forecast config
	*/
	@Override
	public int getModifiedBy() {
		return _forecastConfig.getModifiedBy();
	}

	/**
	* Returns the modified date of this forecast config.
	*
	* @return the modified date of this forecast config
	*/
	@Override
	public Date getModifiedDate() {
		return _forecastConfig.getModifiedDate();
	}

	/**
	* Returns the primary key of this forecast config.
	*
	* @return the primary key of this forecast config
	*/
	@Override
	public int getPrimaryKey() {
		return _forecastConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _forecastConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the process mode of this forecast config.
	*
	* @return the process mode of this forecast config
	*/
	@Override
	public boolean getProcessMode() {
		return _forecastConfig.getProcessMode();
	}

	/**
	* Returns the process type of this forecast config.
	*
	* @return the process type of this forecast config
	*/
	@Override
	public boolean getProcessType() {
		return _forecastConfig.getProcessType();
	}

	/**
	* Returns the proj freq of this forecast config.
	*
	* @return the proj freq of this forecast config
	*/
	@Override
	public int getProjFreq() {
		return _forecastConfig.getProjFreq();
	}

	/**
	* Returns the proj hist freq of this forecast config.
	*
	* @return the proj hist freq of this forecast config
	*/
	@Override
	public int getProjHistFreq() {
		return _forecastConfig.getProjHistFreq();
	}

	/**
	* Returns the proj hist value of this forecast config.
	*
	* @return the proj hist value of this forecast config
	*/
	@Override
	public int getProjHistValue() {
		return _forecastConfig.getProjHistValue();
	}

	/**
	* Returns the proj value of this forecast config.
	*
	* @return the proj value of this forecast config
	*/
	@Override
	public int getProjValue() {
		return _forecastConfig.getProjValue();
	}

	/**
	* Returns the to date of this forecast config.
	*
	* @return the to date of this forecast config
	*/
	@Override
	public Date getToDate() {
		return _forecastConfig.getToDate();
	}

	/**
	* Returns the version no of this forecast config.
	*
	* @return the version no of this forecast config
	*/
	@Override
	public int getVersionNo() {
		return _forecastConfig.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _forecastConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _forecastConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _forecastConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _forecastConfig.isNew();
	}

	/**
	* Returns <code>true</code> if this forecast config is process mode.
	*
	* @return <code>true</code> if this forecast config is process mode; <code>false</code> otherwise
	*/
	@Override
	public boolean isProcessMode() {
		return _forecastConfig.isProcessMode();
	}

	/**
	* Returns <code>true</code> if this forecast config is process type.
	*
	* @return <code>true</code> if this forecast config is process type; <code>false</code> otherwise
	*/
	@Override
	public boolean isProcessType() {
		return _forecastConfig.isProcessType();
	}

	@Override
	public void persist() {
		_forecastConfig.persist();
	}

	/**
	* Sets the active end date of this forecast config.
	*
	* @param activeEndDate the active end date of this forecast config
	*/
	@Override
	public void setActiveEndDate(Date activeEndDate) {
		_forecastConfig.setActiveEndDate(activeEndDate);
	}

	/**
	* Sets the active start date of this forecast config.
	*
	* @param activeStartDate the active start date of this forecast config
	*/
	@Override
	public void setActiveStartDate(Date activeStartDate) {
		_forecastConfig.setActiveStartDate(activeStartDate);
	}

	/**
	* Sets the business process type of this forecast config.
	*
	* @param businessProcessType the business process type of this forecast config
	*/
	@Override
	public void setBusinessProcessType(int businessProcessType) {
		_forecastConfig.setBusinessProcessType(businessProcessType);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_forecastConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this forecast config.
	*
	* @param createdBy the created by of this forecast config
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_forecastConfig.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this forecast config.
	*
	* @param createdDate the created date of this forecast config
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_forecastConfig.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_forecastConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_forecastConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_forecastConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast config sid of this forecast config.
	*
	* @param forecastConfigSid the forecast config sid of this forecast config
	*/
	@Override
	public void setForecastConfigSid(int forecastConfigSid) {
		_forecastConfig.setForecastConfigSid(forecastConfigSid);
	}

	/**
	* Sets the from date of this forecast config.
	*
	* @param fromDate the from date of this forecast config
	*/
	@Override
	public void setFromDate(Date fromDate) {
		_forecastConfig.setFromDate(fromDate);
	}

	/**
	* Sets the future time period from of this forecast config.
	*
	* @param futureTimePeriodFrom the future time period from of this forecast config
	*/
	@Override
	public void setFutureTimePeriodFrom(Date futureTimePeriodFrom) {
		_forecastConfig.setFutureTimePeriodFrom(futureTimePeriodFrom);
	}

	/**
	* Sets the hist freq of this forecast config.
	*
	* @param histFreq the hist freq of this forecast config
	*/
	@Override
	public void setHistFreq(int histFreq) {
		_forecastConfig.setHistFreq(histFreq);
	}

	/**
	* Sets the historical data interval from of this forecast config.
	*
	* @param historicalDataIntervalFrom the historical data interval from of this forecast config
	*/
	@Override
	public void setHistoricalDataIntervalFrom(Date historicalDataIntervalFrom) {
		_forecastConfig.setHistoricalDataIntervalFrom(historicalDataIntervalFrom);
	}

	/**
	* Sets the historical data interval to of this forecast config.
	*
	* @param historicalDataIntervalTo the historical data interval to of this forecast config
	*/
	@Override
	public void setHistoricalDataIntervalTo(Date historicalDataIntervalTo) {
		_forecastConfig.setHistoricalDataIntervalTo(historicalDataIntervalTo);
	}

	/**
	* Sets the historical time period from of this forecast config.
	*
	* @param historicalTimePeriodFrom the historical time period from of this forecast config
	*/
	@Override
	public void setHistoricalTimePeriodFrom(Date historicalTimePeriodFrom) {
		_forecastConfig.setHistoricalTimePeriodFrom(historicalTimePeriodFrom);
	}

	/**
	* Sets the hist value of this forecast config.
	*
	* @param histValue the hist value of this forecast config
	*/
	@Override
	public void setHistValue(int histValue) {
		_forecastConfig.setHistValue(histValue);
	}

	/**
	* Sets the modified by of this forecast config.
	*
	* @param modifiedBy the modified by of this forecast config
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_forecastConfig.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this forecast config.
	*
	* @param modifiedDate the modified date of this forecast config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_forecastConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_forecastConfig.setNew(n);
	}

	/**
	* Sets the primary key of this forecast config.
	*
	* @param primaryKey the primary key of this forecast config
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_forecastConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_forecastConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this forecast config is process mode.
	*
	* @param processMode the process mode of this forecast config
	*/
	@Override
	public void setProcessMode(boolean processMode) {
		_forecastConfig.setProcessMode(processMode);
	}

	/**
	* Sets whether this forecast config is process type.
	*
	* @param processType the process type of this forecast config
	*/
	@Override
	public void setProcessType(boolean processType) {
		_forecastConfig.setProcessType(processType);
	}

	/**
	* Sets the proj freq of this forecast config.
	*
	* @param projFreq the proj freq of this forecast config
	*/
	@Override
	public void setProjFreq(int projFreq) {
		_forecastConfig.setProjFreq(projFreq);
	}

	/**
	* Sets the proj hist freq of this forecast config.
	*
	* @param projHistFreq the proj hist freq of this forecast config
	*/
	@Override
	public void setProjHistFreq(int projHistFreq) {
		_forecastConfig.setProjHistFreq(projHistFreq);
	}

	/**
	* Sets the proj hist value of this forecast config.
	*
	* @param projHistValue the proj hist value of this forecast config
	*/
	@Override
	public void setProjHistValue(int projHistValue) {
		_forecastConfig.setProjHistValue(projHistValue);
	}

	/**
	* Sets the proj value of this forecast config.
	*
	* @param projValue the proj value of this forecast config
	*/
	@Override
	public void setProjValue(int projValue) {
		_forecastConfig.setProjValue(projValue);
	}

	/**
	* Sets the to date of this forecast config.
	*
	* @param toDate the to date of this forecast config
	*/
	@Override
	public void setToDate(Date toDate) {
		_forecastConfig.setToDate(toDate);
	}

	/**
	* Sets the version no of this forecast config.
	*
	* @param versionNo the version no of this forecast config
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_forecastConfig.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ForecastConfig> toCacheModel() {
		return _forecastConfig.toCacheModel();
	}

	@Override
	public ForecastConfig toEscapedModel() {
		return new ForecastConfigWrapper(_forecastConfig.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _forecastConfig.toString();
	}

	@Override
	public ForecastConfig toUnescapedModel() {
		return new ForecastConfigWrapper(_forecastConfig.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _forecastConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ForecastConfigWrapper)) {
			return false;
		}

		ForecastConfigWrapper forecastConfigWrapper = (ForecastConfigWrapper)obj;

		if (Objects.equals(_forecastConfig,
					forecastConfigWrapper._forecastConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public ForecastConfig getWrappedModel() {
		return _forecastConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _forecastConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _forecastConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_forecastConfig.resetOriginalValues();
	}

	private final ForecastConfig _forecastConfig;
}