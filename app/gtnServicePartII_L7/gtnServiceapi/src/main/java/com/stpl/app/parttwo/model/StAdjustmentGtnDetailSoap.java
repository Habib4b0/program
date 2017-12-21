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
public class StAdjustmentGtnDetailSoap implements Serializable {
	public static StAdjustmentGtnDetailSoap toSoapModel(
		StAdjustmentGtnDetail model) {
		StAdjustmentGtnDetailSoap soapModel = new StAdjustmentGtnDetailSoap();

		soapModel.setAdjustmentCreatedDate(model.getAdjustmentCreatedDate());
		soapModel.setEtlCheckRecord(model.getEtlCheckRecord());
		soapModel.setBusinessUnitNo(model.getBusinessUnitNo());
		soapModel.setRedemptionPeriod(model.getRedemptionPeriod());
		soapModel.setDeductionId(model.getDeductionId());
		soapModel.setGlYear(model.getGlYear());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccount(model.getAccount());
		soapModel.setSource(model.getSource());
		soapModel.setWorkflowApprovedDate(model.getWorkflowApprovedDate());
		soapModel.setUdc6(model.getUdc6());
		soapModel.setBusinessUnitName(model.getBusinessUnitName());
		soapModel.setUdc5(model.getUdc5());
		soapModel.setWorkflowCreatedDate(model.getWorkflowCreatedDate());
		soapModel.setUdc4(model.getUdc4());
		soapModel.setUdc3(model.getUdc3());
		soapModel.setUdc2(model.getUdc2());
		soapModel.setUdc1(model.getUdc1());
		soapModel.setAdjustmentType(model.getAdjustmentType());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setDeductionType(model.getDeductionType());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setContractName(model.getContractName());
		soapModel.setDeductionRate(model.getDeductionRate());
		soapModel.setDeductionCategory(model.getDeductionCategory());
		soapModel.setDeductionNo(model.getDeductionNo());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setGlcompanyId(model.getGlcompanyId());
		soapModel.setItemName(model.getItemName());
		soapModel.setDeductionInclusion(model.getDeductionInclusion());
		soapModel.setDeductionAmount(model.getDeductionAmount());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setProject(model.getProject());
		soapModel.setDeductionUdc3(model.getDeductionUdc3());
		soapModel.setDeductionUdc4(model.getDeductionUdc4());
		soapModel.setDeductionUdc1(model.getDeductionUdc1());
		soapModel.setItemId(model.getItemId());
		soapModel.setDeductionUdc2(model.getDeductionUdc2());
		soapModel.setAccountType(model.getAccountType());
		soapModel.setGlString(model.getGlString());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setDeductionUdc6(model.getDeductionUdc6());
		soapModel.setDeductionUdc5(model.getDeductionUdc5());
		soapModel.setGlCompanyName(model.getGlCompanyName());
		soapModel.setWorkflowId(model.getWorkflowId());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setContractId(model.getContractId());
		soapModel.setDeductionProgram(model.getDeductionProgram());
		soapModel.setBusinessUnitId(model.getBusinessUnitId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCostCenter(model.getCostCenter());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setOutboundStatus(model.getOutboundStatus());
		soapModel.setFuture1(model.getFuture1());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setDeductionName(model.getDeductionName());
		soapModel.setFuture2(model.getFuture2());
		soapModel.setWorkflowName(model.getWorkflowName());
		soapModel.setGlDate(model.getGlDate());
		soapModel.setWorkflowCreatedBy(model.getWorkflowCreatedBy());
		soapModel.setGlMonth(model.getGlMonth());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setAccountCategory(model.getAccountCategory());
		soapModel.setGlCompanyNo(model.getGlCompanyNo());
		soapModel.setWorkflowApprovedBy(model.getWorkflowApprovedBy());
		soapModel.setContractNo(model.getContractNo());
		soapModel.setOriginalBatchId(model.getOriginalBatchId());
		soapModel.setAdjustmentLevel(model.getAdjustmentLevel());

		return soapModel;
	}

	public static StAdjustmentGtnDetailSoap[] toSoapModels(
		StAdjustmentGtnDetail[] models) {
		StAdjustmentGtnDetailSoap[] soapModels = new StAdjustmentGtnDetailSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StAdjustmentGtnDetailSoap[][] toSoapModels(
		StAdjustmentGtnDetail[][] models) {
		StAdjustmentGtnDetailSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StAdjustmentGtnDetailSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StAdjustmentGtnDetailSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StAdjustmentGtnDetailSoap[] toSoapModels(
		List<StAdjustmentGtnDetail> models) {
		List<StAdjustmentGtnDetailSoap> soapModels = new ArrayList<StAdjustmentGtnDetailSoap>(models.size());

		for (StAdjustmentGtnDetail model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StAdjustmentGtnDetailSoap[soapModels.size()]);
	}

	public StAdjustmentGtnDetailSoap() {
	}

	public String getPrimaryKey() {
		return _workflowId;
	}

	public void setPrimaryKey(String pk) {
		setWorkflowId(pk);
	}

	public Date getAdjustmentCreatedDate() {
		return _adjustmentCreatedDate;
	}

	public void setAdjustmentCreatedDate(Date adjustmentCreatedDate) {
		_adjustmentCreatedDate = adjustmentCreatedDate;
	}

	public boolean getEtlCheckRecord() {
		return _etlCheckRecord;
	}

	public boolean isEtlCheckRecord() {
		return _etlCheckRecord;
	}

	public void setEtlCheckRecord(boolean etlCheckRecord) {
		_etlCheckRecord = etlCheckRecord;
	}

	public String getBusinessUnitNo() {
		return _businessUnitNo;
	}

	public void setBusinessUnitNo(String businessUnitNo) {
		_businessUnitNo = businessUnitNo;
	}

	public Date getRedemptionPeriod() {
		return _redemptionPeriod;
	}

	public void setRedemptionPeriod(Date redemptionPeriod) {
		_redemptionPeriod = redemptionPeriod;
	}

	public String getDeductionId() {
		return _deductionId;
	}

	public void setDeductionId(String deductionId) {
		_deductionId = deductionId;
	}

	public int getGlYear() {
		return _glYear;
	}

	public void setGlYear(int glYear) {
		_glYear = glYear;
	}

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getAccount() {
		return _account;
	}

	public void setAccount(String account) {
		_account = account;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public Date getWorkflowApprovedDate() {
		return _workflowApprovedDate;
	}

	public void setWorkflowApprovedDate(Date workflowApprovedDate) {
		_workflowApprovedDate = workflowApprovedDate;
	}

	public String getUdc6() {
		return _udc6;
	}

	public void setUdc6(String udc6) {
		_udc6 = udc6;
	}

	public String getBusinessUnitName() {
		return _businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		_businessUnitName = businessUnitName;
	}

	public String getUdc5() {
		return _udc5;
	}

	public void setUdc5(String udc5) {
		_udc5 = udc5;
	}

	public Date getWorkflowCreatedDate() {
		return _workflowCreatedDate;
	}

	public void setWorkflowCreatedDate(Date workflowCreatedDate) {
		_workflowCreatedDate = workflowCreatedDate;
	}

	public String getUdc4() {
		return _udc4;
	}

	public void setUdc4(String udc4) {
		_udc4 = udc4;
	}

	public String getUdc3() {
		return _udc3;
	}

	public void setUdc3(String udc3) {
		_udc3 = udc3;
	}

	public String getUdc2() {
		return _udc2;
	}

	public void setUdc2(String udc2) {
		_udc2 = udc2;
	}

	public String getUdc1() {
		return _udc1;
	}

	public void setUdc1(String udc1) {
		_udc1 = udc1;
	}

	public String getAdjustmentType() {
		return _adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		_adjustmentType = adjustmentType;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getDeductionType() {
		return _deductionType;
	}

	public void setDeductionType(String deductionType) {
		_deductionType = deductionType;
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

	public String getContractName() {
		return _contractName;
	}

	public void setContractName(String contractName) {
		_contractName = contractName;
	}

	public String getDeductionRate() {
		return _deductionRate;
	}

	public void setDeductionRate(String deductionRate) {
		_deductionRate = deductionRate;
	}

	public String getDeductionCategory() {
		return _deductionCategory;
	}

	public void setDeductionCategory(String deductionCategory) {
		_deductionCategory = deductionCategory;
	}

	public String getDeductionNo() {
		return _deductionNo;
	}

	public void setDeductionNo(String deductionNo) {
		_deductionNo = deductionNo;
	}

	public String getCompanyNo() {
		return _companyNo;
	}

	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public String getGlcompanyId() {
		return _glcompanyId;
	}

	public void setGlcompanyId(String glcompanyId) {
		_glcompanyId = glcompanyId;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getDeductionInclusion() {
		return _deductionInclusion;
	}

	public void setDeductionInclusion(String deductionInclusion) {
		_deductionInclusion = deductionInclusion;
	}

	public String getDeductionAmount() {
		return _deductionAmount;
	}

	public void setDeductionAmount(String deductionAmount) {
		_deductionAmount = deductionAmount;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public String getProject() {
		return _project;
	}

	public void setProject(String project) {
		_project = project;
	}

	public String getDeductionUdc3() {
		return _deductionUdc3;
	}

	public void setDeductionUdc3(String deductionUdc3) {
		_deductionUdc3 = deductionUdc3;
	}

	public String getDeductionUdc4() {
		return _deductionUdc4;
	}

	public void setDeductionUdc4(String deductionUdc4) {
		_deductionUdc4 = deductionUdc4;
	}

	public String getDeductionUdc1() {
		return _deductionUdc1;
	}

	public void setDeductionUdc1(String deductionUdc1) {
		_deductionUdc1 = deductionUdc1;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public String getDeductionUdc2() {
		return _deductionUdc2;
	}

	public void setDeductionUdc2(String deductionUdc2) {
		_deductionUdc2 = deductionUdc2;
	}

	public String getAccountType() {
		return _accountType;
	}

	public void setAccountType(String accountType) {
		_accountType = accountType;
	}

	public String getGlString() {
		return _glString;
	}

	public void setGlString(String glString) {
		_glString = glString;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public String getDeductionUdc6() {
		return _deductionUdc6;
	}

	public void setDeductionUdc6(String deductionUdc6) {
		_deductionUdc6 = deductionUdc6;
	}

	public String getDeductionUdc5() {
		return _deductionUdc5;
	}

	public void setDeductionUdc5(String deductionUdc5) {
		_deductionUdc5 = deductionUdc5;
	}

	public String getGlCompanyName() {
		return _glCompanyName;
	}

	public void setGlCompanyName(String glCompanyName) {
		_glCompanyName = glCompanyName;
	}

	public String getWorkflowId() {
		return _workflowId;
	}

	public void setWorkflowId(String workflowId) {
		_workflowId = workflowId;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public String getContractId() {
		return _contractId;
	}

	public void setContractId(String contractId) {
		_contractId = contractId;
	}

	public String getDeductionProgram() {
		return _deductionProgram;
	}

	public void setDeductionProgram(String deductionProgram) {
		_deductionProgram = deductionProgram;
	}

	public String getBusinessUnitId() {
		return _businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		_businessUnitId = businessUnitId;
	}

	public String getUserId() {
		return _userId;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	public String getCostCenter() {
		return _costCenter;
	}

	public void setCostCenter(String costCenter) {
		_costCenter = costCenter;
	}

	public String getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(String companyId) {
		_companyId = companyId;
	}

	public String getOutboundStatus() {
		return _outboundStatus;
	}

	public void setOutboundStatus(String outboundStatus) {
		_outboundStatus = outboundStatus;
	}

	public String getFuture1() {
		return _future1;
	}

	public void setFuture1(String future1) {
		_future1 = future1;
	}

	public String getBrandId() {
		return _brandId;
	}

	public void setBrandId(String brandId) {
		_brandId = brandId;
	}

	public String getDeductionName() {
		return _deductionName;
	}

	public void setDeductionName(String deductionName) {
		_deductionName = deductionName;
	}

	public String getFuture2() {
		return _future2;
	}

	public void setFuture2(String future2) {
		_future2 = future2;
	}

	public String getWorkflowName() {
		return _workflowName;
	}

	public void setWorkflowName(String workflowName) {
		_workflowName = workflowName;
	}

	public Date getGlDate() {
		return _glDate;
	}

	public void setGlDate(Date glDate) {
		_glDate = glDate;
	}

	public String getWorkflowCreatedBy() {
		return _workflowCreatedBy;
	}

	public void setWorkflowCreatedBy(String workflowCreatedBy) {
		_workflowCreatedBy = workflowCreatedBy;
	}

	public int getGlMonth() {
		return _glMonth;
	}

	public void setGlMonth(int glMonth) {
		_glMonth = glMonth;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getAccountCategory() {
		return _accountCategory;
	}

	public void setAccountCategory(String accountCategory) {
		_accountCategory = accountCategory;
	}

	public String getGlCompanyNo() {
		return _glCompanyNo;
	}

	public void setGlCompanyNo(String glCompanyNo) {
		_glCompanyNo = glCompanyNo;
	}

	public String getWorkflowApprovedBy() {
		return _workflowApprovedBy;
	}

	public void setWorkflowApprovedBy(String workflowApprovedBy) {
		_workflowApprovedBy = workflowApprovedBy;
	}

	public String getContractNo() {
		return _contractNo;
	}

	public void setContractNo(String contractNo) {
		_contractNo = contractNo;
	}

	public String getOriginalBatchId() {
		return _originalBatchId;
	}

	public void setOriginalBatchId(String originalBatchId) {
		_originalBatchId = originalBatchId;
	}

	public String getAdjustmentLevel() {
		return _adjustmentLevel;
	}

	public void setAdjustmentLevel(String adjustmentLevel) {
		_adjustmentLevel = adjustmentLevel;
	}

	private Date _adjustmentCreatedDate;
	private boolean _etlCheckRecord;
	private String _businessUnitNo;
	private Date _redemptionPeriod;
	private String _deductionId;
	private int _glYear;
	private String _brandName;
	private Date _modifiedDate;
	private String _account;
	private String _source;
	private Date _workflowApprovedDate;
	private String _udc6;
	private String _businessUnitName;
	private String _udc5;
	private Date _workflowCreatedDate;
	private String _udc4;
	private String _udc3;
	private String _udc2;
	private String _udc1;
	private String _adjustmentType;
	private String _modifiedBy;
	private String _deductionType;
	private boolean _checkRecord;
	private String _contractName;
	private String _deductionRate;
	private String _deductionCategory;
	private String _deductionNo;
	private String _companyNo;
	private String _sessionId;
	private String _glcompanyId;
	private String _itemName;
	private String _deductionInclusion;
	private String _deductionAmount;
	private String _companyName;
	private String _project;
	private String _deductionUdc3;
	private String _deductionUdc4;
	private String _deductionUdc1;
	private String _itemId;
	private String _deductionUdc2;
	private String _accountType;
	private String _glString;
	private Date _createdDate;
	private String _createdBy;
	private String _deductionUdc6;
	private String _deductionUdc5;
	private String _glCompanyName;
	private String _workflowId;
	private String _itemNo;
	private String _contractId;
	private String _deductionProgram;
	private String _businessUnitId;
	private String _userId;
	private String _costCenter;
	private String _companyId;
	private String _outboundStatus;
	private String _future1;
	private String _brandId;
	private String _deductionName;
	private String _future2;
	private String _workflowName;
	private Date _glDate;
	private String _workflowCreatedBy;
	private int _glMonth;
	private String _batchId;
	private String _accountCategory;
	private String _glCompanyNo;
	private String _workflowApprovedBy;
	private String _contractNo;
	private String _originalBatchId;
	private String _adjustmentLevel;
}