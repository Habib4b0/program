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

import com.stpl.app.service.persistence.DeductionCalendarDetailsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class DeductionCalendarDetailsSoap implements Serializable {
	public static DeductionCalendarDetailsSoap toSoapModel(
		DeductionCalendarDetails model) {
		DeductionCalendarDetailsSoap soapModel = new DeductionCalendarDetailsSoap();

		soapModel.setDeductionCalendarMasterSid(model.getDeductionCalendarMasterSid());
		soapModel.setAdjustmentBasis(model.getAdjustmentBasis());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setAdjustmentValue(model.getAdjustmentValue());
		soapModel.setAdjustmentAllocationMethodology(model.getAdjustmentAllocationMethodology());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
		soapModel.setDiscountAmount(model.getDiscountAmount());
		soapModel.setAdjustmentVariable(model.getAdjustmentVariable());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setAdjustmentType(model.getAdjustmentType());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static DeductionCalendarDetailsSoap[] toSoapModels(
		DeductionCalendarDetails[] models) {
		DeductionCalendarDetailsSoap[] soapModels = new DeductionCalendarDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DeductionCalendarDetailsSoap[][] toSoapModels(
		DeductionCalendarDetails[][] models) {
		DeductionCalendarDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DeductionCalendarDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DeductionCalendarDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DeductionCalendarDetailsSoap[] toSoapModels(
		List<DeductionCalendarDetails> models) {
		List<DeductionCalendarDetailsSoap> soapModels = new ArrayList<DeductionCalendarDetailsSoap>(models.size());

		for (DeductionCalendarDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DeductionCalendarDetailsSoap[soapModels.size()]);
	}

	public DeductionCalendarDetailsSoap() {
	}

	public DeductionCalendarDetailsPK getPrimaryKey() {
		return new DeductionCalendarDetailsPK(_deductionCalendarMasterSid,
			_periodSid, _companyMasterSid, _itemMasterSid);
	}

	public void setPrimaryKey(DeductionCalendarDetailsPK pk) {
		setDeductionCalendarMasterSid(pk.deductionCalendarMasterSid);
		setPeriodSid(pk.periodSid);
		setCompanyMasterSid(pk.companyMasterSid);
		setItemMasterSid(pk.itemMasterSid);
	}

	public int getDeductionCalendarMasterSid() {
		return _deductionCalendarMasterSid;
	}

	public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
		_deductionCalendarMasterSid = deductionCalendarMasterSid;
	}

	public String getAdjustmentBasis() {
		return _adjustmentBasis;
	}

	public void setAdjustmentBasis(String adjustmentBasis) {
		_adjustmentBasis = adjustmentBasis;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public String getAdjustmentValue() {
		return _adjustmentValue;
	}

	public void setAdjustmentValue(String adjustmentValue) {
		_adjustmentValue = adjustmentValue;
	}

	public String getAdjustmentAllocationMethodology() {
		return _adjustmentAllocationMethodology;
	}

	public void setAdjustmentAllocationMethodology(
		String adjustmentAllocationMethodology) {
		_adjustmentAllocationMethodology = adjustmentAllocationMethodology;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	public int getDiscountAmount() {
		return _discountAmount;
	}

	public void setDiscountAmount(int discountAmount) {
		_discountAmount = discountAmount;
	}

	public String getAdjustmentVariable() {
		return _adjustmentVariable;
	}

	public void setAdjustmentVariable(String adjustmentVariable) {
		_adjustmentVariable = adjustmentVariable;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public String getAdjustmentType() {
		return _adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		_adjustmentType = adjustmentType;
	}

	public boolean getCheckRecord() {
		return _checkRecord;
	}

	public boolean isCheckRecord() {
		return _checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		_checkRecord = checkRecord;
	}

	private int _deductionCalendarMasterSid;
	private String _adjustmentBasis;
	private int _periodSid;
	private String _adjustmentValue;
	private String _adjustmentAllocationMethodology;
	private int _companyMasterSid;
	private int _discountAmount;
	private String _adjustmentVariable;
	private int _itemMasterSid;
	private String _adjustmentType;
	private boolean _checkRecord;
}