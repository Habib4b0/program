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
 * This class is a wrapper for {@link StMedicaidNewNdc}.
 * </p>
 *
 * @author
 * @see StMedicaidNewNdc
 * @generated
 */
@ProviderType
public class StMedicaidNewNdcWrapper implements StMedicaidNewNdc,
	ModelWrapper<StMedicaidNewNdc> {
	public StMedicaidNewNdcWrapper(StMedicaidNewNdc stMedicaidNewNdc) {
		_stMedicaidNewNdc = stMedicaidNewNdc;
	}

	@Override
	public Class<?> getModelClass() {
		return StMedicaidNewNdc.class;
	}

	@Override
	public String getModelClassName() {
		return StMedicaidNewNdc.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("forecastAmp", getForecastAmp());
		attributes.put("forecastBestprice", getForecastBestprice());
		attributes.put("baseYearCpi", getBaseYearCpi());
		attributes.put("ndc9", getNdc9());
		attributes.put("userId", getUserId());
		attributes.put("lastModifiedDate", getLastModifiedDate());
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

		Double baseYearCpi = (Double)attributes.get("baseYearCpi");

		if (baseYearCpi != null) {
			setBaseYearCpi(baseYearCpi);
		}

		String ndc9 = (String)attributes.get("ndc9");

		if (ndc9 != null) {
			setNdc9(ndc9);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
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
		return new StMedicaidNewNdcWrapper((StMedicaidNewNdc)_stMedicaidNewNdc.clone());
	}

	@Override
	public int compareTo(StMedicaidNewNdc stMedicaidNewNdc) {
		return _stMedicaidNewNdc.compareTo(stMedicaidNewNdc);
	}

	/**
	* Returns the base year amp of this st medicaid new ndc.
	*
	* @return the base year amp of this st medicaid new ndc
	*/
	@Override
	public double getBaseYearAmp() {
		return _stMedicaidNewNdc.getBaseYearAmp();
	}

	/**
	* Returns the base year cpi of this st medicaid new ndc.
	*
	* @return the base year cpi of this st medicaid new ndc
	*/
	@Override
	public double getBaseYearCpi() {
		return _stMedicaidNewNdc.getBaseYearCpi();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stMedicaidNewNdc.getExpandoBridge();
	}

	/**
	* Returns the forecast amp of this st medicaid new ndc.
	*
	* @return the forecast amp of this st medicaid new ndc
	*/
	@Override
	public double getForecastAmp() {
		return _stMedicaidNewNdc.getForecastAmp();
	}

	/**
	* Returns the forecast bestprice of this st medicaid new ndc.
	*
	* @return the forecast bestprice of this st medicaid new ndc
	*/
	@Override
	public double getForecastBestprice() {
		return _stMedicaidNewNdc.getForecastBestprice();
	}

	/**
	* Returns the last modified date of this st medicaid new ndc.
	*
	* @return the last modified date of this st medicaid new ndc
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stMedicaidNewNdc.getLastModifiedDate();
	}

	/**
	* Returns the ndc9 of this st medicaid new ndc.
	*
	* @return the ndc9 of this st medicaid new ndc
	*/
	@Override
	public java.lang.String getNdc9() {
		return _stMedicaidNewNdc.getNdc9();
	}

	/**
	* Returns the primary key of this st medicaid new ndc.
	*
	* @return the primary key of this st medicaid new ndc
	*/
	@Override
	public com.stpl.app.service.persistence.StMedicaidNewNdcPK getPrimaryKey() {
		return _stMedicaidNewNdc.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stMedicaidNewNdc.getPrimaryKeyObj();
	}

	/**
	* Returns the session ID of this st medicaid new ndc.
	*
	* @return the session ID of this st medicaid new ndc
	*/
	@Override
	public int getSessionId() {
		return _stMedicaidNewNdc.getSessionId();
	}

	/**
	* Returns the user ID of this st medicaid new ndc.
	*
	* @return the user ID of this st medicaid new ndc
	*/
	@Override
	public int getUserId() {
		return _stMedicaidNewNdc.getUserId();
	}

	/**
	* Returns the wac price of this st medicaid new ndc.
	*
	* @return the wac price of this st medicaid new ndc
	*/
	@Override
	public double getWacPrice() {
		return _stMedicaidNewNdc.getWacPrice();
	}

	@Override
	public int hashCode() {
		return _stMedicaidNewNdc.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stMedicaidNewNdc.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stMedicaidNewNdc.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stMedicaidNewNdc.isNew();
	}

	@Override
	public void persist() {
		_stMedicaidNewNdc.persist();
	}

	/**
	* Sets the base year amp of this st medicaid new ndc.
	*
	* @param baseYearAmp the base year amp of this st medicaid new ndc
	*/
	@Override
	public void setBaseYearAmp(double baseYearAmp) {
		_stMedicaidNewNdc.setBaseYearAmp(baseYearAmp);
	}

	/**
	* Sets the base year cpi of this st medicaid new ndc.
	*
	* @param baseYearCpi the base year cpi of this st medicaid new ndc
	*/
	@Override
	public void setBaseYearCpi(double baseYearCpi) {
		_stMedicaidNewNdc.setBaseYearCpi(baseYearCpi);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stMedicaidNewNdc.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stMedicaidNewNdc.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stMedicaidNewNdc.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stMedicaidNewNdc.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast amp of this st medicaid new ndc.
	*
	* @param forecastAmp the forecast amp of this st medicaid new ndc
	*/
	@Override
	public void setForecastAmp(double forecastAmp) {
		_stMedicaidNewNdc.setForecastAmp(forecastAmp);
	}

	/**
	* Sets the forecast bestprice of this st medicaid new ndc.
	*
	* @param forecastBestprice the forecast bestprice of this st medicaid new ndc
	*/
	@Override
	public void setForecastBestprice(double forecastBestprice) {
		_stMedicaidNewNdc.setForecastBestprice(forecastBestprice);
	}

	/**
	* Sets the last modified date of this st medicaid new ndc.
	*
	* @param lastModifiedDate the last modified date of this st medicaid new ndc
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stMedicaidNewNdc.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the ndc9 of this st medicaid new ndc.
	*
	* @param ndc9 the ndc9 of this st medicaid new ndc
	*/
	@Override
	public void setNdc9(java.lang.String ndc9) {
		_stMedicaidNewNdc.setNdc9(ndc9);
	}

	@Override
	public void setNew(boolean n) {
		_stMedicaidNewNdc.setNew(n);
	}

	/**
	* Sets the primary key of this st medicaid new ndc.
	*
	* @param primaryKey the primary key of this st medicaid new ndc
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StMedicaidNewNdcPK primaryKey) {
		_stMedicaidNewNdc.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stMedicaidNewNdc.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this st medicaid new ndc.
	*
	* @param sessionId the session ID of this st medicaid new ndc
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stMedicaidNewNdc.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st medicaid new ndc.
	*
	* @param userId the user ID of this st medicaid new ndc
	*/
	@Override
	public void setUserId(int userId) {
		_stMedicaidNewNdc.setUserId(userId);
	}

	/**
	* Sets the wac price of this st medicaid new ndc.
	*
	* @param wacPrice the wac price of this st medicaid new ndc
	*/
	@Override
	public void setWacPrice(double wacPrice) {
		_stMedicaidNewNdc.setWacPrice(wacPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StMedicaidNewNdc> toCacheModel() {
		return _stMedicaidNewNdc.toCacheModel();
	}

	@Override
	public StMedicaidNewNdc toEscapedModel() {
		return new StMedicaidNewNdcWrapper(_stMedicaidNewNdc.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stMedicaidNewNdc.toString();
	}

	@Override
	public StMedicaidNewNdc toUnescapedModel() {
		return new StMedicaidNewNdcWrapper(_stMedicaidNewNdc.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stMedicaidNewNdc.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StMedicaidNewNdcWrapper)) {
			return false;
		}

		StMedicaidNewNdcWrapper stMedicaidNewNdcWrapper = (StMedicaidNewNdcWrapper)obj;

		if (Objects.equals(_stMedicaidNewNdc,
					stMedicaidNewNdcWrapper._stMedicaidNewNdc)) {
			return true;
		}

		return false;
	}

	@Override
	public StMedicaidNewNdc getWrappedModel() {
		return _stMedicaidNewNdc;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stMedicaidNewNdc.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stMedicaidNewNdc.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stMedicaidNewNdc.resetOriginalValues();
	}

	private final StMedicaidNewNdc _stMedicaidNewNdc;
}