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
 * This class is a wrapper for {@link StNewNdc}.
 * </p>
 *
 * @author
 * @see StNewNdc
 * @generated
 */
@ProviderType
public class StNewNdcWrapper implements StNewNdc, ModelWrapper<StNewNdc> {
	public StNewNdcWrapper(StNewNdc stNewNdc) {
		_stNewNdc = stNewNdc;
	}

	@Override
	public Class<?> getModelClass() {
		return StNewNdc.class;
	}

	@Override
	public String getModelClassName() {
		return StNewNdc.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("forecastAmp", getForecastAmp());
		attributes.put("forecastBestprice", getForecastBestprice());
		attributes.put("naProjDetailsSid", getNaProjDetailsSid());
		attributes.put("baseYearCpi", getBaseYearCpi());
		attributes.put("userId", getUserId());
		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("wacPrice", getWacPrice());
		attributes.put("baseYearAmp", getBaseYearAmp());
		attributes.put("sessionId", getSessionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double forecastAmp = (Double)attributes.get("forecastAmp");

		if (forecastAmp != null) {
			setForecastAmp(forecastAmp);
		}

		Double forecastBestprice = (Double)attributes.get("forecastBestprice");

		if (forecastBestprice != null) {
			setForecastBestprice(forecastBestprice);
		}

		Integer naProjDetailsSid = (Integer)attributes.get("naProjDetailsSid");

		if (naProjDetailsSid != null) {
			setNaProjDetailsSid(naProjDetailsSid);
		}

		Double baseYearCpi = (Double)attributes.get("baseYearCpi");

		if (baseYearCpi != null) {
			setBaseYearCpi(baseYearCpi);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Double wacPrice = (Double)attributes.get("wacPrice");

		if (wacPrice != null) {
			setWacPrice(wacPrice);
		}

		Double baseYearAmp = (Double)attributes.get("baseYearAmp");

		if (baseYearAmp != null) {
			setBaseYearAmp(baseYearAmp);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StNewNdcWrapper((StNewNdc)_stNewNdc.clone());
	}

	@Override
	public int compareTo(StNewNdc stNewNdc) {
		return _stNewNdc.compareTo(stNewNdc);
	}

	/**
	* Returns the base year amp of this st new ndc.
	*
	* @return the base year amp of this st new ndc
	*/
	@Override
	public double getBaseYearAmp() {
		return _stNewNdc.getBaseYearAmp();
	}

	/**
	* Returns the base year cpi of this st new ndc.
	*
	* @return the base year cpi of this st new ndc
	*/
	@Override
	public double getBaseYearCpi() {
		return _stNewNdc.getBaseYearCpi();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stNewNdc.getExpandoBridge();
	}

	/**
	* Returns the forecast amp of this st new ndc.
	*
	* @return the forecast amp of this st new ndc
	*/
	@Override
	public double getForecastAmp() {
		return _stNewNdc.getForecastAmp();
	}

	/**
	* Returns the forecast bestprice of this st new ndc.
	*
	* @return the forecast bestprice of this st new ndc
	*/
	@Override
	public double getForecastBestprice() {
		return _stNewNdc.getForecastBestprice();
	}

	/**
	* Returns the item master sid of this st new ndc.
	*
	* @return the item master sid of this st new ndc
	*/
	@Override
	public int getItemMasterSid() {
		return _stNewNdc.getItemMasterSid();
	}

	/**
	* Returns the last modified date of this st new ndc.
	*
	* @return the last modified date of this st new ndc
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stNewNdc.getLastModifiedDate();
	}

	/**
	* Returns the na proj details sid of this st new ndc.
	*
	* @return the na proj details sid of this st new ndc
	*/
	@Override
	public int getNaProjDetailsSid() {
		return _stNewNdc.getNaProjDetailsSid();
	}

	/**
	* Returns the primary key of this st new ndc.
	*
	* @return the primary key of this st new ndc
	*/
	@Override
	public com.stpl.app.service.persistence.StNewNdcPK getPrimaryKey() {
		return _stNewNdc.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stNewNdc.getPrimaryKeyObj();
	}

	/**
	* Returns the session ID of this st new ndc.
	*
	* @return the session ID of this st new ndc
	*/
	@Override
	public int getSessionId() {
		return _stNewNdc.getSessionId();
	}

	/**
	* Returns the user ID of this st new ndc.
	*
	* @return the user ID of this st new ndc
	*/
	@Override
	public int getUserId() {
		return _stNewNdc.getUserId();
	}

	/**
	* Returns the wac price of this st new ndc.
	*
	* @return the wac price of this st new ndc
	*/
	@Override
	public double getWacPrice() {
		return _stNewNdc.getWacPrice();
	}

	@Override
	public int hashCode() {
		return _stNewNdc.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stNewNdc.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stNewNdc.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stNewNdc.isNew();
	}

	@Override
	public void persist() {
		_stNewNdc.persist();
	}

	/**
	* Sets the base year amp of this st new ndc.
	*
	* @param baseYearAmp the base year amp of this st new ndc
	*/
	@Override
	public void setBaseYearAmp(double baseYearAmp) {
		_stNewNdc.setBaseYearAmp(baseYearAmp);
	}

	/**
	* Sets the base year cpi of this st new ndc.
	*
	* @param baseYearCpi the base year cpi of this st new ndc
	*/
	@Override
	public void setBaseYearCpi(double baseYearCpi) {
		_stNewNdc.setBaseYearCpi(baseYearCpi);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stNewNdc.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stNewNdc.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stNewNdc.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stNewNdc.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast amp of this st new ndc.
	*
	* @param forecastAmp the forecast amp of this st new ndc
	*/
	@Override
	public void setForecastAmp(double forecastAmp) {
		_stNewNdc.setForecastAmp(forecastAmp);
	}

	/**
	* Sets the forecast bestprice of this st new ndc.
	*
	* @param forecastBestprice the forecast bestprice of this st new ndc
	*/
	@Override
	public void setForecastBestprice(double forecastBestprice) {
		_stNewNdc.setForecastBestprice(forecastBestprice);
	}

	/**
	* Sets the item master sid of this st new ndc.
	*
	* @param itemMasterSid the item master sid of this st new ndc
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_stNewNdc.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the last modified date of this st new ndc.
	*
	* @param lastModifiedDate the last modified date of this st new ndc
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stNewNdc.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the na proj details sid of this st new ndc.
	*
	* @param naProjDetailsSid the na proj details sid of this st new ndc
	*/
	@Override
	public void setNaProjDetailsSid(int naProjDetailsSid) {
		_stNewNdc.setNaProjDetailsSid(naProjDetailsSid);
	}

	@Override
	public void setNew(boolean n) {
		_stNewNdc.setNew(n);
	}

	/**
	* Sets the primary key of this st new ndc.
	*
	* @param primaryKey the primary key of this st new ndc
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StNewNdcPK primaryKey) {
		_stNewNdc.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stNewNdc.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this st new ndc.
	*
	* @param sessionId the session ID of this st new ndc
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stNewNdc.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st new ndc.
	*
	* @param userId the user ID of this st new ndc
	*/
	@Override
	public void setUserId(int userId) {
		_stNewNdc.setUserId(userId);
	}

	/**
	* Sets the wac price of this st new ndc.
	*
	* @param wacPrice the wac price of this st new ndc
	*/
	@Override
	public void setWacPrice(double wacPrice) {
		_stNewNdc.setWacPrice(wacPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StNewNdc> toCacheModel() {
		return _stNewNdc.toCacheModel();
	}

	@Override
	public StNewNdc toEscapedModel() {
		return new StNewNdcWrapper(_stNewNdc.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stNewNdc.toString();
	}

	@Override
	public StNewNdc toUnescapedModel() {
		return new StNewNdcWrapper(_stNewNdc.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stNewNdc.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNewNdcWrapper)) {
			return false;
		}

		StNewNdcWrapper stNewNdcWrapper = (StNewNdcWrapper)obj;

		if (Objects.equals(_stNewNdc, stNewNdcWrapper._stNewNdc)) {
			return true;
		}

		return false;
	}

	@Override
	public StNewNdc getWrappedModel() {
		return _stNewNdc;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stNewNdc.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stNewNdc.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stNewNdc.resetOriginalValues();
	}

	private final StNewNdc _stNewNdc;
}