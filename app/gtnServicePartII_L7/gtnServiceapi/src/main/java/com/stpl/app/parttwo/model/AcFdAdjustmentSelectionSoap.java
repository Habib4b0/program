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

package com.stpl.app.parttwo.model;

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
public class AcFdAdjustmentSelectionSoap implements Serializable {
	public static AcFdAdjustmentSelectionSoap toSoapModel(
		AcFdAdjustmentSelection model) {
		AcFdAdjustmentSelectionSoap soapModel = new AcFdAdjustmentSelectionSoap();

		soapModel.setMethodologyStartDate(model.getMethodologyStartDate());
		soapModel.setAllocationMethod(model.getAllocationMethod());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setTotalFixedDollarAdj(model.getTotalFixedDollarAdj());
		soapModel.setCalculationFlag(model.getCalculationFlag());
		soapModel.setRateCorrection(model.getRateCorrection());
		soapModel.setBusinessDays(model.getBusinessDays());
		soapModel.setGlImpactDate(model.getGlImpactDate());
		soapModel.setSalesBasis(model.getSalesBasis());
		soapModel.setReleaseType(model.getReleaseType());
		soapModel.setAccClosureMasterSid(model.getAccClosureMasterSid());
		soapModel.setReleaseAmount(model.getReleaseAmount());
		soapModel.setSuggestedAdj(model.getSuggestedAdj());
		soapModel.setMethodologyEndDate(model.getMethodologyEndDate());

		return soapModel;
	}

	public static AcFdAdjustmentSelectionSoap[] toSoapModels(
		AcFdAdjustmentSelection[] models) {
		AcFdAdjustmentSelectionSoap[] soapModels = new AcFdAdjustmentSelectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AcFdAdjustmentSelectionSoap[][] toSoapModels(
		AcFdAdjustmentSelection[][] models) {
		AcFdAdjustmentSelectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AcFdAdjustmentSelectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AcFdAdjustmentSelectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AcFdAdjustmentSelectionSoap[] toSoapModels(
		List<AcFdAdjustmentSelection> models) {
		List<AcFdAdjustmentSelectionSoap> soapModels = new ArrayList<AcFdAdjustmentSelectionSoap>(models.size());

		for (AcFdAdjustmentSelection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AcFdAdjustmentSelectionSoap[soapModels.size()]);
	}

	public AcFdAdjustmentSelectionSoap() {
	}

	public int getPrimaryKey() {
		return _accClosureMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setAccClosureMasterSid(pk);
	}

	public String getMethodologyStartDate() {
		return _methodologyStartDate;
	}

	public void setMethodologyStartDate(String methodologyStartDate) {
		_methodologyStartDate = methodologyStartDate;
	}

	public int getAllocationMethod() {
		return _allocationMethod;
	}

	public void setAllocationMethod(int allocationMethod) {
		_allocationMethod = allocationMethod;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public double getTotalFixedDollarAdj() {
		return _totalFixedDollarAdj;
	}

	public void setTotalFixedDollarAdj(double totalFixedDollarAdj) {
		_totalFixedDollarAdj = totalFixedDollarAdj;
	}

	public boolean getCalculationFlag() {
		return _calculationFlag;
	}

	public boolean isCalculationFlag() {
		return _calculationFlag;
	}

	public void setCalculationFlag(boolean calculationFlag) {
		_calculationFlag = calculationFlag;
	}

	public double getRateCorrection() {
		return _rateCorrection;
	}

	public void setRateCorrection(double rateCorrection) {
		_rateCorrection = rateCorrection;
	}

	public int getBusinessDays() {
		return _businessDays;
	}

	public void setBusinessDays(int businessDays) {
		_businessDays = businessDays;
	}

	public Date getGlImpactDate() {
		return _glImpactDate;
	}

	public void setGlImpactDate(Date glImpactDate) {
		_glImpactDate = glImpactDate;
	}

	public int getSalesBasis() {
		return _salesBasis;
	}

	public void setSalesBasis(int salesBasis) {
		_salesBasis = salesBasis;
	}

	public boolean getReleaseType() {
		return _releaseType;
	}

	public boolean isReleaseType() {
		return _releaseType;
	}

	public void setReleaseType(boolean releaseType) {
		_releaseType = releaseType;
	}

	public int getAccClosureMasterSid() {
		return _accClosureMasterSid;
	}

	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_accClosureMasterSid = accClosureMasterSid;
	}

	public double getReleaseAmount() {
		return _releaseAmount;
	}

	public void setReleaseAmount(double releaseAmount) {
		_releaseAmount = releaseAmount;
	}

	public double getSuggestedAdj() {
		return _suggestedAdj;
	}

	public void setSuggestedAdj(double suggestedAdj) {
		_suggestedAdj = suggestedAdj;
	}

	public String getMethodologyEndDate() {
		return _methodologyEndDate;
	}

	public void setMethodologyEndDate(String methodologyEndDate) {
		_methodologyEndDate = methodologyEndDate;
	}

	private String _methodologyStartDate;
	private int _allocationMethod;
	private Date _startDate;
	private double _totalFixedDollarAdj;
	private boolean _calculationFlag;
	private double _rateCorrection;
	private int _businessDays;
	private Date _glImpactDate;
	private int _salesBasis;
	private boolean _releaseType;
	private int _accClosureMasterSid;
	private double _releaseAmount;
	private double _suggestedAdj;
	private String _methodologyEndDate;
}