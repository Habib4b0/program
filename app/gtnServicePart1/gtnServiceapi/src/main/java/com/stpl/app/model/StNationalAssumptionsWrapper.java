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
 * This class is a wrapper for {@link StNationalAssumptions}.
 * </p>
 *
 * @author
 * @see StNationalAssumptions
 * @generated
 */
@ProviderType
public class StNationalAssumptionsWrapper implements StNationalAssumptions,
	ModelWrapper<StNationalAssumptions> {
	public StNationalAssumptionsWrapper(
		StNationalAssumptions stNationalAssumptions) {
		_stNationalAssumptions = stNationalAssumptions;
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
	public java.lang.Object clone() {
		return new StNationalAssumptionsWrapper((StNationalAssumptions)_stNationalAssumptions.clone());
	}

	@Override
	public int compareTo(StNationalAssumptions stNationalAssumptions) {
		return _stNationalAssumptions.compareTo(stNationalAssumptions);
	}

	/**
	* Returns the baseline methodology of this st national assumptions.
	*
	* @return the baseline methodology of this st national assumptions
	*/
	@Override
	public java.lang.String getBaselineMethodology() {
		return _stNationalAssumptions.getBaselineMethodology();
	}

	/**
	* Returns the baseline period of this st national assumptions.
	*
	* @return the baseline period of this st national assumptions
	*/
	@Override
	public java.lang.String getBaselinePeriod() {
		return _stNationalAssumptions.getBaselinePeriod();
	}

	/**
	* Returns the end period of this st national assumptions.
	*
	* @return the end period of this st national assumptions
	*/
	@Override
	public java.lang.String getEndPeriod() {
		return _stNationalAssumptions.getEndPeriod();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stNationalAssumptions.getExpandoBridge();
	}

	/**
	* Returns the forecast methodology of this st national assumptions.
	*
	* @return the forecast methodology of this st national assumptions
	*/
	@Override
	public java.lang.String getForecastMethodology() {
		return _stNationalAssumptions.getForecastMethodology();
	}

	/**
	* Returns the frequency of this st national assumptions.
	*
	* @return the frequency of this st national assumptions
	*/
	@Override
	public java.lang.String getFrequency() {
		return _stNationalAssumptions.getFrequency();
	}

	/**
	* Returns the growth rate of this st national assumptions.
	*
	* @return the growth rate of this st national assumptions
	*/
	@Override
	public double getGrowthRate() {
		return _stNationalAssumptions.getGrowthRate();
	}

	/**
	* Returns the last modified date of this st national assumptions.
	*
	* @return the last modified date of this st national assumptions
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stNationalAssumptions.getLastModifiedDate();
	}

	/**
	* Returns the na proj master sid of this st national assumptions.
	*
	* @return the na proj master sid of this st national assumptions
	*/
	@Override
	public int getNaProjMasterSid() {
		return _stNationalAssumptions.getNaProjMasterSid();
	}

	/**
	* Returns the price basis of this st national assumptions.
	*
	* @return the price basis of this st national assumptions
	*/
	@Override
	public java.lang.String getPriceBasis() {
		return _stNationalAssumptions.getPriceBasis();
	}

	/**
	* Returns the price type of this st national assumptions.
	*
	* @return the price type of this st national assumptions
	*/
	@Override
	public java.lang.String getPriceType() {
		return _stNationalAssumptions.getPriceType();
	}

	/**
	* Returns the primary key of this st national assumptions.
	*
	* @return the primary key of this st national assumptions
	*/
	@Override
	public com.stpl.app.service.persistence.StNationalAssumptionsPK getPrimaryKey() {
		return _stNationalAssumptions.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stNationalAssumptions.getPrimaryKeyObj();
	}

	/**
	* Returns the rolling period of this st national assumptions.
	*
	* @return the rolling period of this st national assumptions
	*/
	@Override
	public java.lang.String getRollingPeriod() {
		return _stNationalAssumptions.getRollingPeriod();
	}

	/**
	* Returns the session ID of this st national assumptions.
	*
	* @return the session ID of this st national assumptions
	*/
	@Override
	public int getSessionId() {
		return _stNationalAssumptions.getSessionId();
	}

	/**
	* Returns the start period of this st national assumptions.
	*
	* @return the start period of this st national assumptions
	*/
	@Override
	public java.lang.String getStartPeriod() {
		return _stNationalAssumptions.getStartPeriod();
	}

	/**
	* Returns the user ID of this st national assumptions.
	*
	* @return the user ID of this st national assumptions
	*/
	@Override
	public int getUserId() {
		return _stNationalAssumptions.getUserId();
	}

	@Override
	public int hashCode() {
		return _stNationalAssumptions.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stNationalAssumptions.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stNationalAssumptions.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stNationalAssumptions.isNew();
	}

	@Override
	public void persist() {
		_stNationalAssumptions.persist();
	}

	/**
	* Sets the baseline methodology of this st national assumptions.
	*
	* @param baselineMethodology the baseline methodology of this st national assumptions
	*/
	@Override
	public void setBaselineMethodology(java.lang.String baselineMethodology) {
		_stNationalAssumptions.setBaselineMethodology(baselineMethodology);
	}

	/**
	* Sets the baseline period of this st national assumptions.
	*
	* @param baselinePeriod the baseline period of this st national assumptions
	*/
	@Override
	public void setBaselinePeriod(java.lang.String baselinePeriod) {
		_stNationalAssumptions.setBaselinePeriod(baselinePeriod);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stNationalAssumptions.setCachedModel(cachedModel);
	}

	/**
	* Sets the end period of this st national assumptions.
	*
	* @param endPeriod the end period of this st national assumptions
	*/
	@Override
	public void setEndPeriod(java.lang.String endPeriod) {
		_stNationalAssumptions.setEndPeriod(endPeriod);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stNationalAssumptions.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stNationalAssumptions.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stNationalAssumptions.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast methodology of this st national assumptions.
	*
	* @param forecastMethodology the forecast methodology of this st national assumptions
	*/
	@Override
	public void setForecastMethodology(java.lang.String forecastMethodology) {
		_stNationalAssumptions.setForecastMethodology(forecastMethodology);
	}

	/**
	* Sets the frequency of this st national assumptions.
	*
	* @param frequency the frequency of this st national assumptions
	*/
	@Override
	public void setFrequency(java.lang.String frequency) {
		_stNationalAssumptions.setFrequency(frequency);
	}

	/**
	* Sets the growth rate of this st national assumptions.
	*
	* @param growthRate the growth rate of this st national assumptions
	*/
	@Override
	public void setGrowthRate(double growthRate) {
		_stNationalAssumptions.setGrowthRate(growthRate);
	}

	/**
	* Sets the last modified date of this st national assumptions.
	*
	* @param lastModifiedDate the last modified date of this st national assumptions
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stNationalAssumptions.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the na proj master sid of this st national assumptions.
	*
	* @param naProjMasterSid the na proj master sid of this st national assumptions
	*/
	@Override
	public void setNaProjMasterSid(int naProjMasterSid) {
		_stNationalAssumptions.setNaProjMasterSid(naProjMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_stNationalAssumptions.setNew(n);
	}

	/**
	* Sets the price basis of this st national assumptions.
	*
	* @param priceBasis the price basis of this st national assumptions
	*/
	@Override
	public void setPriceBasis(java.lang.String priceBasis) {
		_stNationalAssumptions.setPriceBasis(priceBasis);
	}

	/**
	* Sets the price type of this st national assumptions.
	*
	* @param priceType the price type of this st national assumptions
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_stNationalAssumptions.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this st national assumptions.
	*
	* @param primaryKey the primary key of this st national assumptions
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StNationalAssumptionsPK primaryKey) {
		_stNationalAssumptions.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stNationalAssumptions.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rolling period of this st national assumptions.
	*
	* @param rollingPeriod the rolling period of this st national assumptions
	*/
	@Override
	public void setRollingPeriod(java.lang.String rollingPeriod) {
		_stNationalAssumptions.setRollingPeriod(rollingPeriod);
	}

	/**
	* Sets the session ID of this st national assumptions.
	*
	* @param sessionId the session ID of this st national assumptions
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stNationalAssumptions.setSessionId(sessionId);
	}

	/**
	* Sets the start period of this st national assumptions.
	*
	* @param startPeriod the start period of this st national assumptions
	*/
	@Override
	public void setStartPeriod(java.lang.String startPeriod) {
		_stNationalAssumptions.setStartPeriod(startPeriod);
	}

	/**
	* Sets the user ID of this st national assumptions.
	*
	* @param userId the user ID of this st national assumptions
	*/
	@Override
	public void setUserId(int userId) {
		_stNationalAssumptions.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StNationalAssumptions> toCacheModel() {
		return _stNationalAssumptions.toCacheModel();
	}

	@Override
	public StNationalAssumptions toEscapedModel() {
		return new StNationalAssumptionsWrapper(_stNationalAssumptions.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stNationalAssumptions.toString();
	}

	@Override
	public StNationalAssumptions toUnescapedModel() {
		return new StNationalAssumptionsWrapper(_stNationalAssumptions.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stNationalAssumptions.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNationalAssumptionsWrapper)) {
			return false;
		}

		StNationalAssumptionsWrapper stNationalAssumptionsWrapper = (StNationalAssumptionsWrapper)obj;

		if (Objects.equals(_stNationalAssumptions,
					stNationalAssumptionsWrapper._stNationalAssumptions)) {
			return true;
		}

		return false;
	}

	@Override
	public StNationalAssumptions getWrappedModel() {
		return _stNationalAssumptions;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stNationalAssumptions.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stNationalAssumptions.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stNationalAssumptions.resetOriginalValues();
	}

	private final StNationalAssumptions _stNationalAssumptions;
}