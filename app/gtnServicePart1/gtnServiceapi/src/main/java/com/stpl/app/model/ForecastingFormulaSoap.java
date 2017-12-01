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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class ForecastingFormulaSoap implements Serializable {
	public static ForecastingFormulaSoap toSoapModel(ForecastingFormula model) {
		ForecastingFormulaSoap soapModel = new ForecastingFormulaSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setFormulaType(model.getFormulaType());
		soapModel.setForecastingFormulaSid(model.getForecastingFormulaSid());
		soapModel.setFormula(model.getFormula());
		soapModel.setFormulaNo(model.getFormulaNo());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setIsActive(model.getIsActive());
		soapModel.setFormulaName(model.getFormulaName());

		return soapModel;
	}

	public static ForecastingFormulaSoap[] toSoapModels(
		ForecastingFormula[] models) {
		ForecastingFormulaSoap[] soapModels = new ForecastingFormulaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ForecastingFormulaSoap[][] toSoapModels(
		ForecastingFormula[][] models) {
		ForecastingFormulaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ForecastingFormulaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ForecastingFormulaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ForecastingFormulaSoap[] toSoapModels(
		List<ForecastingFormula> models) {
		List<ForecastingFormulaSoap> soapModels = new ArrayList<ForecastingFormulaSoap>(models.size());

		for (ForecastingFormula model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ForecastingFormulaSoap[soapModels.size()]);
	}

	public ForecastingFormulaSoap() {
	}

	public int getPrimaryKey() {
		return _forecastingFormulaSid;
	}

	public void setPrimaryKey(int pk) {
		setForecastingFormulaSid(pk);
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getFormulaType() {
		return _formulaType;
	}

	public void setFormulaType(int formulaType) {
		_formulaType = formulaType;
	}

	public int getForecastingFormulaSid() {
		return _forecastingFormulaSid;
	}

	public void setForecastingFormulaSid(int forecastingFormulaSid) {
		_forecastingFormulaSid = forecastingFormulaSid;
	}

	public String getFormula() {
		return _formula;
	}

	public void setFormula(String formula) {
		_formula = formula;
	}

	public String getFormulaNo() {
		return _formulaNo;
	}

	public void setFormulaNo(String formulaNo) {
		_formulaNo = formulaNo;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public boolean getIsActive() {
		return _isActive;
	}

	public boolean isIsActive() {
		return _isActive;
	}

	public void setIsActive(boolean isActive) {
		_isActive = isActive;
	}

	public String getFormulaName() {
		return _formulaName;
	}

	public void setFormulaName(String formulaName) {
		_formulaName = formulaName;
	}

	private Date _createdDate;
	private int _formulaType;
	private int _forecastingFormulaSid;
	private String _formula;
	private String _formulaNo;
	private Date _modifiedDate;
	private boolean _isActive;
	private String _formulaName;
}