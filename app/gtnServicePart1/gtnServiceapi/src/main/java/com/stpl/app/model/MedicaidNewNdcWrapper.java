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
 * This class is a wrapper for {@link MedicaidNewNdc}.
 * </p>
 *
 * @author
 * @see MedicaidNewNdc
 * @generated
 */
@ProviderType
public class MedicaidNewNdcWrapper implements MedicaidNewNdc,
	ModelWrapper<MedicaidNewNdc> {
	public MedicaidNewNdcWrapper(MedicaidNewNdc medicaidNewNdc) {
		_medicaidNewNdc = medicaidNewNdc;
	}

	@Override
	public Class<?> getModelClass() {
		return MedicaidNewNdc.class;
	}

	@Override
	public String getModelClassName() {
		return MedicaidNewNdc.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("forecastAmp", getForecastAmp());
		attributes.put("forecastBestprice", getForecastBestprice());
		attributes.put("baseYearCpi", getBaseYearCpi());
		attributes.put("ndc9", getNdc9());
		attributes.put("wacPrice", getWacPrice());
		attributes.put("baseYearAmp", getBaseYearAmp());

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

		Double wacPrice = (Double)attributes.get("wacPrice");

		if (wacPrice != null) {
			setWacPrice(wacPrice);
		}

		Double baseYearAmp = (Double)attributes.get("baseYearAmp");

		if (baseYearAmp != null) {
			setBaseYearAmp(baseYearAmp);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MedicaidNewNdcWrapper((MedicaidNewNdc)_medicaidNewNdc.clone());
	}

	@Override
	public int compareTo(MedicaidNewNdc medicaidNewNdc) {
		return _medicaidNewNdc.compareTo(medicaidNewNdc);
	}

	/**
	* Returns the base year amp of this medicaid new ndc.
	*
	* @return the base year amp of this medicaid new ndc
	*/
	@Override
	public double getBaseYearAmp() {
		return _medicaidNewNdc.getBaseYearAmp();
	}

	/**
	* Returns the base year cpi of this medicaid new ndc.
	*
	* @return the base year cpi of this medicaid new ndc
	*/
	@Override
	public double getBaseYearCpi() {
		return _medicaidNewNdc.getBaseYearCpi();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _medicaidNewNdc.getExpandoBridge();
	}

	/**
	* Returns the forecast amp of this medicaid new ndc.
	*
	* @return the forecast amp of this medicaid new ndc
	*/
	@Override
	public double getForecastAmp() {
		return _medicaidNewNdc.getForecastAmp();
	}

	/**
	* Returns the forecast bestprice of this medicaid new ndc.
	*
	* @return the forecast bestprice of this medicaid new ndc
	*/
	@Override
	public double getForecastBestprice() {
		return _medicaidNewNdc.getForecastBestprice();
	}

	/**
	* Returns the ndc9 of this medicaid new ndc.
	*
	* @return the ndc9 of this medicaid new ndc
	*/
	@Override
	public java.lang.String getNdc9() {
		return _medicaidNewNdc.getNdc9();
	}

	/**
	* Returns the primary key of this medicaid new ndc.
	*
	* @return the primary key of this medicaid new ndc
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _medicaidNewNdc.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _medicaidNewNdc.getPrimaryKeyObj();
	}

	/**
	* Returns the wac price of this medicaid new ndc.
	*
	* @return the wac price of this medicaid new ndc
	*/
	@Override
	public double getWacPrice() {
		return _medicaidNewNdc.getWacPrice();
	}

	@Override
	public int hashCode() {
		return _medicaidNewNdc.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _medicaidNewNdc.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _medicaidNewNdc.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _medicaidNewNdc.isNew();
	}

	@Override
	public void persist() {
		_medicaidNewNdc.persist();
	}

	/**
	* Sets the base year amp of this medicaid new ndc.
	*
	* @param baseYearAmp the base year amp of this medicaid new ndc
	*/
	@Override
	public void setBaseYearAmp(double baseYearAmp) {
		_medicaidNewNdc.setBaseYearAmp(baseYearAmp);
	}

	/**
	* Sets the base year cpi of this medicaid new ndc.
	*
	* @param baseYearCpi the base year cpi of this medicaid new ndc
	*/
	@Override
	public void setBaseYearCpi(double baseYearCpi) {
		_medicaidNewNdc.setBaseYearCpi(baseYearCpi);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_medicaidNewNdc.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_medicaidNewNdc.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_medicaidNewNdc.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_medicaidNewNdc.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast amp of this medicaid new ndc.
	*
	* @param forecastAmp the forecast amp of this medicaid new ndc
	*/
	@Override
	public void setForecastAmp(double forecastAmp) {
		_medicaidNewNdc.setForecastAmp(forecastAmp);
	}

	/**
	* Sets the forecast bestprice of this medicaid new ndc.
	*
	* @param forecastBestprice the forecast bestprice of this medicaid new ndc
	*/
	@Override
	public void setForecastBestprice(double forecastBestprice) {
		_medicaidNewNdc.setForecastBestprice(forecastBestprice);
	}

	/**
	* Sets the ndc9 of this medicaid new ndc.
	*
	* @param ndc9 the ndc9 of this medicaid new ndc
	*/
	@Override
	public void setNdc9(java.lang.String ndc9) {
		_medicaidNewNdc.setNdc9(ndc9);
	}

	@Override
	public void setNew(boolean n) {
		_medicaidNewNdc.setNew(n);
	}

	/**
	* Sets the primary key of this medicaid new ndc.
	*
	* @param primaryKey the primary key of this medicaid new ndc
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_medicaidNewNdc.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_medicaidNewNdc.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the wac price of this medicaid new ndc.
	*
	* @param wacPrice the wac price of this medicaid new ndc
	*/
	@Override
	public void setWacPrice(double wacPrice) {
		_medicaidNewNdc.setWacPrice(wacPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MedicaidNewNdc> toCacheModel() {
		return _medicaidNewNdc.toCacheModel();
	}

	@Override
	public MedicaidNewNdc toEscapedModel() {
		return new MedicaidNewNdcWrapper(_medicaidNewNdc.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _medicaidNewNdc.toString();
	}

	@Override
	public MedicaidNewNdc toUnescapedModel() {
		return new MedicaidNewNdcWrapper(_medicaidNewNdc.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _medicaidNewNdc.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MedicaidNewNdcWrapper)) {
			return false;
		}

		MedicaidNewNdcWrapper medicaidNewNdcWrapper = (MedicaidNewNdcWrapper)obj;

		if (Objects.equals(_medicaidNewNdc,
					medicaidNewNdcWrapper._medicaidNewNdc)) {
			return true;
		}

		return false;
	}

	@Override
	public MedicaidNewNdc getWrappedModel() {
		return _medicaidNewNdc;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _medicaidNewNdc.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _medicaidNewNdc.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_medicaidNewNdc.resetOriginalValues();
	}

	private final MedicaidNewNdc _medicaidNewNdc;
}