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
 * This class is a wrapper for {@link ForecastingFormula}.
 * </p>
 *
 * @author
 * @see ForecastingFormula
 * @generated
 */
@ProviderType
public class ForecastingFormulaWrapper implements ForecastingFormula,
	ModelWrapper<ForecastingFormula> {
	public ForecastingFormulaWrapper(ForecastingFormula forecastingFormula) {
		_forecastingFormula = forecastingFormula;
	}

	@Override
	public Class<?> getModelClass() {
		return ForecastingFormula.class;
	}

	@Override
	public String getModelClassName() {
		return ForecastingFormula.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("formulaType", getFormulaType());
		attributes.put("forecastingFormulaSid", getForecastingFormulaSid());
		attributes.put("formula", getFormula());
		attributes.put("formulaNo", getFormulaNo());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("isActive", getIsActive());
		attributes.put("formulaName", getFormulaName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer formulaType = (Integer)attributes.get("formulaType");

		if (formulaType != null) {
			setFormulaType(formulaType);
		}

		Integer forecastingFormulaSid = (Integer)attributes.get(
				"forecastingFormulaSid");

		if (forecastingFormulaSid != null) {
			setForecastingFormulaSid(forecastingFormulaSid);
		}

		String formula = (String)attributes.get("formula");

		if (formula != null) {
			setFormula(formula);
		}

		String formulaNo = (String)attributes.get("formulaNo");

		if (formulaNo != null) {
			setFormulaNo(formulaNo);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean isActive = (Boolean)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		String formulaName = (String)attributes.get("formulaName");

		if (formulaName != null) {
			setFormulaName(formulaName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ForecastingFormulaWrapper((ForecastingFormula)_forecastingFormula.clone());
	}

	@Override
	public int compareTo(ForecastingFormula forecastingFormula) {
		return _forecastingFormula.compareTo(forecastingFormula);
	}

	/**
	* Returns the created date of this forecasting formula.
	*
	* @return the created date of this forecasting formula
	*/
	@Override
	public Date getCreatedDate() {
		return _forecastingFormula.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _forecastingFormula.getExpandoBridge();
	}

	/**
	* Returns the forecasting formula sid of this forecasting formula.
	*
	* @return the forecasting formula sid of this forecasting formula
	*/
	@Override
	public int getForecastingFormulaSid() {
		return _forecastingFormula.getForecastingFormulaSid();
	}

	/**
	* Returns the formula of this forecasting formula.
	*
	* @return the formula of this forecasting formula
	*/
	@Override
	public java.lang.String getFormula() {
		return _forecastingFormula.getFormula();
	}

	/**
	* Returns the formula name of this forecasting formula.
	*
	* @return the formula name of this forecasting formula
	*/
	@Override
	public java.lang.String getFormulaName() {
		return _forecastingFormula.getFormulaName();
	}

	/**
	* Returns the formula no of this forecasting formula.
	*
	* @return the formula no of this forecasting formula
	*/
	@Override
	public java.lang.String getFormulaNo() {
		return _forecastingFormula.getFormulaNo();
	}

	/**
	* Returns the formula type of this forecasting formula.
	*
	* @return the formula type of this forecasting formula
	*/
	@Override
	public int getFormulaType() {
		return _forecastingFormula.getFormulaType();
	}

	/**
	* Returns the is active of this forecasting formula.
	*
	* @return the is active of this forecasting formula
	*/
	@Override
	public boolean getIsActive() {
		return _forecastingFormula.getIsActive();
	}

	/**
	* Returns the modified date of this forecasting formula.
	*
	* @return the modified date of this forecasting formula
	*/
	@Override
	public Date getModifiedDate() {
		return _forecastingFormula.getModifiedDate();
	}

	/**
	* Returns the primary key of this forecasting formula.
	*
	* @return the primary key of this forecasting formula
	*/
	@Override
	public int getPrimaryKey() {
		return _forecastingFormula.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _forecastingFormula.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _forecastingFormula.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _forecastingFormula.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _forecastingFormula.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this forecasting formula is is active.
	*
	* @return <code>true</code> if this forecasting formula is is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsActive() {
		return _forecastingFormula.isIsActive();
	}

	@Override
	public boolean isNew() {
		return _forecastingFormula.isNew();
	}

	@Override
	public void persist() {
		_forecastingFormula.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_forecastingFormula.setCachedModel(cachedModel);
	}

	/**
	* Sets the created date of this forecasting formula.
	*
	* @param createdDate the created date of this forecasting formula
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_forecastingFormula.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_forecastingFormula.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_forecastingFormula.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_forecastingFormula.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecasting formula sid of this forecasting formula.
	*
	* @param forecastingFormulaSid the forecasting formula sid of this forecasting formula
	*/
	@Override
	public void setForecastingFormulaSid(int forecastingFormulaSid) {
		_forecastingFormula.setForecastingFormulaSid(forecastingFormulaSid);
	}

	/**
	* Sets the formula of this forecasting formula.
	*
	* @param formula the formula of this forecasting formula
	*/
	@Override
	public void setFormula(java.lang.String formula) {
		_forecastingFormula.setFormula(formula);
	}

	/**
	* Sets the formula name of this forecasting formula.
	*
	* @param formulaName the formula name of this forecasting formula
	*/
	@Override
	public void setFormulaName(java.lang.String formulaName) {
		_forecastingFormula.setFormulaName(formulaName);
	}

	/**
	* Sets the formula no of this forecasting formula.
	*
	* @param formulaNo the formula no of this forecasting formula
	*/
	@Override
	public void setFormulaNo(java.lang.String formulaNo) {
		_forecastingFormula.setFormulaNo(formulaNo);
	}

	/**
	* Sets the formula type of this forecasting formula.
	*
	* @param formulaType the formula type of this forecasting formula
	*/
	@Override
	public void setFormulaType(int formulaType) {
		_forecastingFormula.setFormulaType(formulaType);
	}

	/**
	* Sets whether this forecasting formula is is active.
	*
	* @param isActive the is active of this forecasting formula
	*/
	@Override
	public void setIsActive(boolean isActive) {
		_forecastingFormula.setIsActive(isActive);
	}

	/**
	* Sets the modified date of this forecasting formula.
	*
	* @param modifiedDate the modified date of this forecasting formula
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_forecastingFormula.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_forecastingFormula.setNew(n);
	}

	/**
	* Sets the primary key of this forecasting formula.
	*
	* @param primaryKey the primary key of this forecasting formula
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_forecastingFormula.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_forecastingFormula.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ForecastingFormula> toCacheModel() {
		return _forecastingFormula.toCacheModel();
	}

	@Override
	public ForecastingFormula toEscapedModel() {
		return new ForecastingFormulaWrapper(_forecastingFormula.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _forecastingFormula.toString();
	}

	@Override
	public ForecastingFormula toUnescapedModel() {
		return new ForecastingFormulaWrapper(_forecastingFormula.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _forecastingFormula.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ForecastingFormulaWrapper)) {
			return false;
		}

		ForecastingFormulaWrapper forecastingFormulaWrapper = (ForecastingFormulaWrapper)obj;

		if (Objects.equals(_forecastingFormula,
					forecastingFormulaWrapper._forecastingFormula)) {
			return true;
		}

		return false;
	}

	@Override
	public ForecastingFormula getWrappedModel() {
		return _forecastingFormula;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _forecastingFormula.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _forecastingFormula.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_forecastingFormula.resetOriginalValues();
	}

	private final ForecastingFormula _forecastingFormula;
}