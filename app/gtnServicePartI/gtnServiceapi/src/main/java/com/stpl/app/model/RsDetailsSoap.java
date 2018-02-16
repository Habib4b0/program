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
public class RsDetailsSoap implements Serializable {
	public static RsDetailsSoap toSoapModel(RsDetails model) {
		RsDetailsSoap soapModel = new RsDetailsSoap();

		soapModel.setRebateAmount(model.getRebateAmount());
		soapModel.setItemRsAttachedStatus(model.getItemRsAttachedStatus());
		soapModel.setFormulaMethodId(model.getFormulaMethodId());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setRebatePlanMasterSid(model.getRebatePlanMasterSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBundleNo(model.getBundleNo());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setItemRebateEndDate(model.getItemRebateEndDate());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemRebateStartDate(model.getItemRebateStartDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setRsDetailsSid(model.getRsDetailsSid());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setFormulaId(model.getFormulaId());
		soapModel.setItemRsAttachedDate(model.getItemRsAttachedDate());
		soapModel.setIfpModelSid(model.getIfpModelSid());
		soapModel.setDeductionCalendarMasterSid(model.getDeductionCalendarMasterSid());
		soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
		soapModel.setEvaluationRule(model.getEvaluationRule());
		soapModel.setNetSalesRule(model.getNetSalesRule());
		soapModel.setFormulaType(model.getFormulaType());
		soapModel.setCalculationRule(model.getCalculationRule());
		soapModel.setCalculationRuleBundle(model.getCalculationRuleBundle());
		soapModel.setEvaluationRuleBundle(model.getEvaluationRuleBundle());

		return soapModel;
	}

	public static RsDetailsSoap[] toSoapModels(RsDetails[] models) {
		RsDetailsSoap[] soapModels = new RsDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RsDetailsSoap[][] toSoapModels(RsDetails[][] models) {
		RsDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RsDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RsDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RsDetailsSoap[] toSoapModels(List<RsDetails> models) {
		List<RsDetailsSoap> soapModels = new ArrayList<RsDetailsSoap>(models.size());

		for (RsDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RsDetailsSoap[soapModels.size()]);
	}

	public RsDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _rsDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setRsDetailsSid(pk);
	}

	public double getRebateAmount() {
		return _rebateAmount;
	}

	public void setRebateAmount(double rebateAmount) {
		_rebateAmount = rebateAmount;
	}

	public int getItemRsAttachedStatus() {
		return _itemRsAttachedStatus;
	}

	public void setItemRsAttachedStatus(int itemRsAttachedStatus) {
		_itemRsAttachedStatus = itemRsAttachedStatus;
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

	public int getRebatePlanMasterSid() {
		return _rebatePlanMasterSid;
	}

	public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
		_rebatePlanMasterSid = rebatePlanMasterSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public int getRsDetailsSid() {
		return _rsDetailsSid;
	}

	public void setRsDetailsSid(int rsDetailsSid) {
		_rsDetailsSid = rsDetailsSid;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public int getFormulaId() {
		return _formulaId;
	}

	public void setFormulaId(int formulaId) {
		_formulaId = formulaId;
	}

	public Date getItemRsAttachedDate() {
		return _itemRsAttachedDate;
	}

	public void setItemRsAttachedDate(Date itemRsAttachedDate) {
		_itemRsAttachedDate = itemRsAttachedDate;
	}

	public int getIfpModelSid() {
		return _ifpModelSid;
	}

	public void setIfpModelSid(int ifpModelSid) {
		_ifpModelSid = ifpModelSid;
	}

	public int getDeductionCalendarMasterSid() {
		return _deductionCalendarMasterSid;
	}

	public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
		_deductionCalendarMasterSid = deductionCalendarMasterSid;
	}

	public int getNetSalesFormulaMasterSid() {
		return _netSalesFormulaMasterSid;
	}

	public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
		_netSalesFormulaMasterSid = netSalesFormulaMasterSid;
	}

	public String getEvaluationRule() {
		return _evaluationRule;
	}

	public void setEvaluationRule(String evaluationRule) {
		_evaluationRule = evaluationRule;
	}

	public String getNetSalesRule() {
		return _netSalesRule;
	}

	public void setNetSalesRule(String netSalesRule) {
		_netSalesRule = netSalesRule;
	}

	public String getFormulaType() {
		return _formulaType;
	}

	public void setFormulaType(String formulaType) {
		_formulaType = formulaType;
	}

	public String getCalculationRule() {
		return _calculationRule;
	}

	public void setCalculationRule(String calculationRule) {
		_calculationRule = calculationRule;
	}

	public String getCalculationRuleBundle() {
		return _calculationRuleBundle;
	}

	public void setCalculationRuleBundle(String calculationRuleBundle) {
		_calculationRuleBundle = calculationRuleBundle;
	}

	public String getEvaluationRuleBundle() {
		return _evaluationRuleBundle;
	}

	public void setEvaluationRuleBundle(String evaluationRuleBundle) {
		_evaluationRuleBundle = evaluationRuleBundle;
	}

	private double _rebateAmount;
	private int _itemRsAttachedStatus;
	private String _formulaMethodId;
	private int _itemMasterSid;
	private int _rebatePlanMasterSid;
	private Date _modifiedDate;
	private String _bundleNo;
	private boolean _recordLockStatus;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private Date _itemRebateEndDate;
	private String _batchId;
	private Date _itemRebateStartDate;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _rsDetailsSid;
	private int _rsModelSid;
	private int _formulaId;
	private Date _itemRsAttachedDate;
	private int _ifpModelSid;
	private int _deductionCalendarMasterSid;
	private int _netSalesFormulaMasterSid;
	private String _evaluationRule;
	private String _netSalesRule;
	private String _formulaType;
	private String _calculationRule;
	private String _calculationRuleBundle;
	private String _evaluationRuleBundle;
}