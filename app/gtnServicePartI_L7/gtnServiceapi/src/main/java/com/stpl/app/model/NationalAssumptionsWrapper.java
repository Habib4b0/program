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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link NationalAssumptions}.
 * </p>
 *
 * @author
 * @see NationalAssumptions
 * @generated
 */
@ProviderType
public class NationalAssumptionsWrapper implements NationalAssumptions,
	ModelWrapper<NationalAssumptions> {
	public NationalAssumptionsWrapper(NationalAssumptions nationalAssumptions) {
		_nationalAssumptions = nationalAssumptions;
	}

	@Override
	public Class<?> getModelClass() {
		return NationalAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return NationalAssumptions.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("baselinePeriod", getBaselinePeriod());
		attributes.put("frequency", getFrequency());
		attributes.put("startPeriod", getStartPeriod());
		attributes.put("forecastMethodology", getForecastMethodology());
		attributes.put("priceType", getPriceType());
		attributes.put("endPeriod", getEndPeriod());
		attributes.put("priceBasis", getPriceBasis());
		attributes.put("naProjMasterSid", getNaProjMasterSid());
		attributes.put("rollingPeriod", getRollingPeriod());
		attributes.put("baselineMethodology", getBaselineMethodology());
		attributes.put("growthRate", getGrowthRate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String baselinePeriod = (String)attributes.get("baselinePeriod");

		if (baselinePeriod != null) {
			setBaselinePeriod(baselinePeriod);
		}

		String frequency = (String)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		String startPeriod = (String)attributes.get("startPeriod");

		if (startPeriod != null) {
			setStartPeriod(startPeriod);
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

		String endPeriod = (String)attributes.get("endPeriod");

		if (endPeriod != null) {
			setEndPeriod(endPeriod);
		}

		String priceBasis = (String)attributes.get("priceBasis");

		if (priceBasis != null) {
			setPriceBasis(priceBasis);
		}

		Integer naProjMasterSid = (Integer)attributes.get("naProjMasterSid");

		if (naProjMasterSid != null) {
			setNaProjMasterSid(naProjMasterSid);
		}

		String rollingPeriod = (String)attributes.get("rollingPeriod");

		if (rollingPeriod != null) {
			setRollingPeriod(rollingPeriod);
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
		return new NationalAssumptionsWrapper((NationalAssumptions)_nationalAssumptions.clone());
	}

	@Override
	public int compareTo(NationalAssumptions nationalAssumptions) {
		return _nationalAssumptions.compareTo(nationalAssumptions);
	}

	/**
	* Returns the baseline methodology of this national assumptions.
	*
	* @return the baseline methodology of this national assumptions
	*/
	@Override
	public java.lang.String getBaselineMethodology() {
		return _nationalAssumptions.getBaselineMethodology();
	}

	/**
	* Returns the baseline period of this national assumptions.
	*
	* @return the baseline period of this national assumptions
	*/
	@Override
	public java.lang.String getBaselinePeriod() {
		return _nationalAssumptions.getBaselinePeriod();
	}

	/**
	* Returns the end period of this national assumptions.
	*
	* @return the end period of this national assumptions
	*/
	@Override
	public java.lang.String getEndPeriod() {
		return _nationalAssumptions.getEndPeriod();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _nationalAssumptions.getExpandoBridge();
	}

	/**
	* Returns the forecast methodology of this national assumptions.
	*
	* @return the forecast methodology of this national assumptions
	*/
	@Override
	public java.lang.String getForecastMethodology() {
		return _nationalAssumptions.getForecastMethodology();
	}

	/**
	* Returns the frequency of this national assumptions.
	*
	* @return the frequency of this national assumptions
	*/
	@Override
	public java.lang.String getFrequency() {
		return _nationalAssumptions.getFrequency();
	}

	/**
	* Returns the growth rate of this national assumptions.
	*
	* @return the growth rate of this national assumptions
	*/
	@Override
	public double getGrowthRate() {
		return _nationalAssumptions.getGrowthRate();
	}

	/**
	* Returns the na proj master sid of this national assumptions.
	*
	* @return the na proj master sid of this national assumptions
	*/
	@Override
	public int getNaProjMasterSid() {
		return _nationalAssumptions.getNaProjMasterSid();
	}

	/**
	* Returns the price basis of this national assumptions.
	*
	* @return the price basis of this national assumptions
	*/
	@Override
	public java.lang.String getPriceBasis() {
		return _nationalAssumptions.getPriceBasis();
	}

	/**
	* Returns the price type of this national assumptions.
	*
	* @return the price type of this national assumptions
	*/
	@Override
	public java.lang.String getPriceType() {
		return _nationalAssumptions.getPriceType();
	}

	/**
	* Returns the primary key of this national assumptions.
	*
	* @return the primary key of this national assumptions
	*/
	@Override
	public com.stpl.app.service.persistence.NationalAssumptionsPK getPrimaryKey() {
		return _nationalAssumptions.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nationalAssumptions.getPrimaryKeyObj();
	}

	/**
	* Returns the rolling period of this national assumptions.
	*
	* @return the rolling period of this national assumptions
	*/
	@Override
	public java.lang.String getRollingPeriod() {
		return _nationalAssumptions.getRollingPeriod();
	}

	/**
	* Returns the start period of this national assumptions.
	*
	* @return the start period of this national assumptions
	*/
	@Override
	public java.lang.String getStartPeriod() {
		return _nationalAssumptions.getStartPeriod();
	}

	@Override
	public int hashCode() {
		return _nationalAssumptions.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _nationalAssumptions.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _nationalAssumptions.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _nationalAssumptions.isNew();
	}

	@Override
	public void persist() {
		_nationalAssumptions.persist();
	}

	/**
	* Sets the baseline methodology of this national assumptions.
	*
	* @param baselineMethodology the baseline methodology of this national assumptions
	*/
	@Override
	public void setBaselineMethodology(java.lang.String baselineMethodology) {
		_nationalAssumptions.setBaselineMethodology(baselineMethodology);
	}

	/**
	* Sets the baseline period of this national assumptions.
	*
	* @param baselinePeriod the baseline period of this national assumptions
	*/
	@Override
	public void setBaselinePeriod(java.lang.String baselinePeriod) {
		_nationalAssumptions.setBaselinePeriod(baselinePeriod);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_nationalAssumptions.setCachedModel(cachedModel);
	}

	/**
	* Sets the end period of this national assumptions.
	*
	* @param endPeriod the end period of this national assumptions
	*/
	@Override
	public void setEndPeriod(java.lang.String endPeriod) {
		_nationalAssumptions.setEndPeriod(endPeriod);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_nationalAssumptions.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_nationalAssumptions.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_nationalAssumptions.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast methodology of this national assumptions.
	*
	* @param forecastMethodology the forecast methodology of this national assumptions
	*/
	@Override
	public void setForecastMethodology(java.lang.String forecastMethodology) {
		_nationalAssumptions.setForecastMethodology(forecastMethodology);
	}

	/**
	* Sets the frequency of this national assumptions.
	*
	* @param frequency the frequency of this national assumptions
	*/
	@Override
	public void setFrequency(java.lang.String frequency) {
		_nationalAssumptions.setFrequency(frequency);
	}

	/**
	* Sets the growth rate of this national assumptions.
	*
	* @param growthRate the growth rate of this national assumptions
	*/
	@Override
	public void setGrowthRate(double growthRate) {
		_nationalAssumptions.setGrowthRate(growthRate);
	}

	/**
	* Sets the na proj master sid of this national assumptions.
	*
	* @param naProjMasterSid the na proj master sid of this national assumptions
	*/
	@Override
	public void setNaProjMasterSid(int naProjMasterSid) {
		_nationalAssumptions.setNaProjMasterSid(naProjMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_nationalAssumptions.setNew(n);
	}

	/**
	* Sets the price basis of this national assumptions.
	*
	* @param priceBasis the price basis of this national assumptions
	*/
	@Override
	public void setPriceBasis(java.lang.String priceBasis) {
		_nationalAssumptions.setPriceBasis(priceBasis);
	}

	/**
	* Sets the price type of this national assumptions.
	*
	* @param priceType the price type of this national assumptions
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_nationalAssumptions.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this national assumptions.
	*
	* @param primaryKey the primary key of this national assumptions
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.NationalAssumptionsPK primaryKey) {
		_nationalAssumptions.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_nationalAssumptions.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rolling period of this national assumptions.
	*
	* @param rollingPeriod the rolling period of this national assumptions
	*/
	@Override
	public void setRollingPeriod(java.lang.String rollingPeriod) {
		_nationalAssumptions.setRollingPeriod(rollingPeriod);
	}

	/**
	* Sets the start period of this national assumptions.
	*
	* @param startPeriod the start period of this national assumptions
	*/
	@Override
	public void setStartPeriod(java.lang.String startPeriod) {
		_nationalAssumptions.setStartPeriod(startPeriod);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NationalAssumptions> toCacheModel() {
		return _nationalAssumptions.toCacheModel();
	}

	@Override
	public NationalAssumptions toEscapedModel() {
		return new NationalAssumptionsWrapper(_nationalAssumptions.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _nationalAssumptions.toString();
	}

	@Override
	public NationalAssumptions toUnescapedModel() {
		return new NationalAssumptionsWrapper(_nationalAssumptions.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _nationalAssumptions.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NationalAssumptionsWrapper)) {
			return false;
		}

		NationalAssumptionsWrapper nationalAssumptionsWrapper = (NationalAssumptionsWrapper)obj;

		if (Objects.equals(_nationalAssumptions,
					nationalAssumptionsWrapper._nationalAssumptions)) {
			return true;
		}

		return false;
	}

	@Override
	public NationalAssumptions getWrappedModel() {
		return _nationalAssumptions;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _nationalAssumptions.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _nationalAssumptions.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_nationalAssumptions.resetOriginalValues();
	}

	private final NationalAssumptions _nationalAssumptions;
}