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
public class RsContractDetailsSoap implements Serializable {
	public static RsContractDetailsSoap toSoapModel(RsContractDetails model) {
		RsContractDetailsSoap soapModel = new RsContractDetailsSoap();

		soapModel.setRebateAmount(model.getRebateAmount());
		soapModel.setFormulaMethodId(model.getFormulaMethodId());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setRebatePlanMasterSid(model.getRebatePlanMasterSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRsContractDetailsSid(model.getRsContractDetailsSid());
		soapModel.setBundleNo(model.getBundleNo());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setRsContractSid(model.getRsContractSid());
		soapModel.setItemRebateEndDate(model.getItemRebateEndDate());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemRebateStartDate(model.getItemRebateStartDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setFormulaId(model.getFormulaId());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setDeductionCalendarMasterSid(model.getDeductionCalendarMasterSid());
		soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
		soapModel.setFormulaType(model.getFormulaType());
		soapModel.setNetSalesRule(model.getNetSalesRule());
		soapModel.setEvaluationRule(model.getEvaluationRule());
		soapModel.setEvaluationRuleBundle(model.getEvaluationRuleBundle());
		soapModel.setCalculationRule(model.getCalculationRule());
		soapModel.setCalculationRuleBundle(model.getCalculationRuleBundle());
		soapModel.setRsContractAttachedDate(model.getRsContractAttachedDate());
		soapModel.setRsContractAttachedStatus(model.getRsContractAttachedStatus());

		return soapModel;
	}

	public static RsContractDetailsSoap[] toSoapModels(
		RsContractDetails[] models) {
		RsContractDetailsSoap[] soapModels = new RsContractDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RsContractDetailsSoap[][] toSoapModels(
		RsContractDetails[][] models) {
		RsContractDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RsContractDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RsContractDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RsContractDetailsSoap[] toSoapModels(
		List<RsContractDetails> models) {
		List<RsContractDetailsSoap> soapModels = new ArrayList<RsContractDetailsSoap>(models.size());

		for (RsContractDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RsContractDetailsSoap[soapModels.size()]);
	}

	public RsContractDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _rsContractDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setRsContractDetailsSid(pk);
	}

	public double getRebateAmount() {
		return _rebateAmount;
	}

	public void setRebateAmount(double rebateAmount) {
		_rebateAmount = rebateAmount;
	}

	public String getFormulaMethodId() {
		return _formulaMethodId;
	}

	public void setFormulaMethodId(String formulaMethodId) {
		_formulaMethodId = formulaMethodId;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public String getRebatePlanMasterSid() {
		return _rebatePlanMasterSid;
	}

	public void setRebatePlanMasterSid(String rebatePlanMasterSid) {
		_rebatePlanMasterSid = rebatePlanMasterSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getRsContractDetailsSid() {
		return _rsContractDetailsSid;
	}

	public void setRsContractDetailsSid(int rsContractDetailsSid) {
		_rsContractDetailsSid = rsContractDetailsSid;
	}

	public String getBundleNo() {
		return _bundleNo;
	}

	public void setBundleNo(String bundleNo) {
		_bundleNo = bundleNo;
	}

	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public int getRsContractSid() {
		return _rsContractSid;
	}

	public void setRsContractSid(int rsContractSid) {
		_rsContractSid = rsContractSid;
	}

	public Date getItemRebateEndDate() {
		return _itemRebateEndDate;
	}

	public void setItemRebateEndDate(Date itemRebateEndDate) {
		_itemRebateEndDate = itemRebateEndDate;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public Date getItemRebateStartDate() {
		return _itemRebateStartDate;
	}

	public void setItemRebateStartDate(Date itemRebateStartDate) {
		_itemRebateStartDate = itemRebateStartDate;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public int getFormulaId() {
		return _formulaId;
	}

	public void setFormulaId(int formulaId) {
		_formulaId = formulaId;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getDeductionCalendarMasterSid() {
		return _deductionCalendarMasterSid;
	}

	public void setDeductionCalendarMasterSid(String deductionCalendarMasterSid) {
		_deductionCalendarMasterSid = deductionCalendarMasterSid;
	}

	public String getNetSalesFormulaMasterSid() {
		return _netSalesFormulaMasterSid;
	}

	public void setNetSalesFormulaMasterSid(String netSalesFormulaMasterSid) {
		_netSalesFormulaMasterSid = netSalesFormulaMasterSid;
	}

	public double getFormulaType() {
		return _formulaType;
	}

	public void setFormulaType(double formulaType) {
		_formulaType = formulaType;
	}

	public int getNetSalesRule() {
		return _netSalesRule;
	}

	public void setNetSalesRule(int netSalesRule) {
		_netSalesRule = netSalesRule;
	}

	public int getEvaluationRule() {
		return _evaluationRule;
	}

	public void setEvaluationRule(int evaluationRule) {
		_evaluationRule = evaluationRule;
	}

	public String getEvaluationRuleBundle() {
		return _evaluationRuleBundle;
	}

	public void setEvaluationRuleBundle(String evaluationRuleBundle) {
		_evaluationRuleBundle = evaluationRuleBundle;
	}

	public int getCalculationRule() {
		return _calculationRule;
	}

	public void setCalculationRule(int calculationRule) {
		_calculationRule = calculationRule;
	}

	public String getCalculationRuleBundle() {
		return _calculationRuleBundle;
	}

	public void setCalculationRuleBundle(String calculationRuleBundle) {
		_calculationRuleBundle = calculationRuleBundle;
	}

	public Date getRsContractAttachedDate() {
		return _rsContractAttachedDate;
	}

	public void setRsContractAttachedDate(Date rsContractAttachedDate) {
		_rsContractAttachedDate = rsContractAttachedDate;
	}

	public int getRsContractAttachedStatus() {
		return _rsContractAttachedStatus;
	}

	public void setRsContractAttachedStatus(int rsContractAttachedStatus) {
		_rsContractAttachedStatus = rsContractAttachedStatus;
	}

	private double _rebateAmount;
	private String _formulaMethodId;
	private int _itemMasterSid;
	private String _rebatePlanMasterSid;
	private Date _modifiedDate;
	private int _rsContractDetailsSid;
	private String _bundleNo;
	private boolean _recordLockStatus;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private int _rsContractSid;
	private Date _itemRebateEndDate;
	private String _batchId;
	private Date _itemRebateStartDate;
	private int _modifiedBy;
	private int _formulaId;
	private String _inboundStatus;
	private String _deductionCalendarMasterSid;
	private String _netSalesFormulaMasterSid;
	private double _formulaType;
	private int _netSalesRule;
	private int _evaluationRule;
	private String _evaluationRuleBundle;
	private int _calculationRule;
	private String _calculationRuleBundle;
	private Date _rsContractAttachedDate;
	private int _rsContractAttachedStatus;
}