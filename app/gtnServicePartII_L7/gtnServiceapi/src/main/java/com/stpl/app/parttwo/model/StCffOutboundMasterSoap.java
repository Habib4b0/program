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

import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK;

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
public class StCffOutboundMasterSoap implements Serializable {
	public static StCffOutboundMasterSoap toSoapModel(StCffOutboundMaster model) {
		StCffOutboundMasterSoap soapModel = new StCffOutboundMasterSoap();

		soapModel.setEtlCheckRecord(model.getEtlCheckRecord());
		soapModel.setCustomerName(model.getCustomerName());
		soapModel.setContractHolderId(model.getContractHolderId());
		soapModel.setBusinessUnitNo(model.getBusinessUnitNo());
		soapModel.setYear(model.getYear());
		soapModel.setFinancialForecastApprovalDate(model.getFinancialForecastApprovalDate());
		soapModel.setDeductionId(model.getDeductionId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDeductionPerUnit(model.getDeductionPerUnit());
		soapModel.setCogsPerUnit(model.getCogsPerUnit());
		soapModel.setContractType(model.getContractType());
		soapModel.setSource(model.getSource());
		soapModel.setBusinessUnitName(model.getBusinessUnitName());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setFinancialForecastId(model.getFinancialForecastId());
		soapModel.setProjectId(model.getProjectId());
		soapModel.setCustomerNo(model.getCustomerNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setSalesDollars(model.getSalesDollars());
		soapModel.setMonth(model.getMonth());
		soapModel.setCffDetailsSid(model.getCffDetailsSid());
		soapModel.setType(model.getType());
		soapModel.setDeductionType(model.getDeductionType());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setContractName(model.getContractName());
		soapModel.setDeductionRate(model.getDeductionRate());
		soapModel.setDeductionCategory(model.getDeductionCategory());
		soapModel.setCogsAmount(model.getCogsAmount());
		soapModel.setDeductionNo(model.getDeductionNo());
		soapModel.setFinancialForecastCreationDate(model.getFinancialForecastCreationDate());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setSalesUnits(model.getSalesUnits());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setItemName(model.getItemName());
		soapModel.setDeductionInclusion(model.getDeductionInclusion());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setContractHolderName(model.getContractHolderName());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setCustomerId(model.getCustomerId());
		soapModel.setItemId(model.getItemId());
		soapModel.setNetProfitDollars(model.getNetProfitDollars());
		soapModel.setGlCompanyMasterSid(model.getGlCompanyMasterSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setDeductionCategory1(model.getDeductionCategory1());
		soapModel.setDeductionCategory2(model.getDeductionCategory2());
		soapModel.setContractHolderNo(model.getContractHolderNo());
		soapModel.setDeductionCategory3(model.getDeductionCategory3());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setDeductionCategory4(model.getDeductionCategory4());
		soapModel.setDeductionCategory5(model.getDeductionCategory5());
		soapModel.setDeductionCategory6(model.getDeductionCategory6());
		soapModel.setContractId(model.getContractId());
		soapModel.setDeductionProgram(model.getDeductionProgram());
		soapModel.setBusinessUnitId(model.getBusinessUnitId());
		soapModel.setProjectionName(model.getProjectionName());
		soapModel.setUserId(model.getUserId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setOutboundStatus(model.getOutboundStatus());
		soapModel.setOriginalBatchId(model.getOriginalBatchId());
		soapModel.setDeductionName(model.getDeductionName());
		soapModel.setNetProfitPerUnit(model.getNetProfitPerUnit());
		soapModel.setPeriodSid(model.getPeriodSid());
		soapModel.setSalesInclusion(model.getSalesInclusion());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setFinancialForecastName(model.getFinancialForecastName());
		soapModel.setNetSalesDollar(model.getNetSalesDollar());
		soapModel.setDeductionDollars(model.getDeductionDollars());
		soapModel.setContractNo(model.getContractNo());

		return soapModel;
	}

	public static StCffOutboundMasterSoap[] toSoapModels(
		StCffOutboundMaster[] models) {
		StCffOutboundMasterSoap[] soapModels = new StCffOutboundMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StCffOutboundMasterSoap[][] toSoapModels(
		StCffOutboundMaster[][] models) {
		StCffOutboundMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StCffOutboundMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StCffOutboundMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StCffOutboundMasterSoap[] toSoapModels(
		List<StCffOutboundMaster> models) {
		List<StCffOutboundMasterSoap> soapModels = new ArrayList<StCffOutboundMasterSoap>(models.size());

		for (StCffOutboundMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StCffOutboundMasterSoap[soapModels.size()]);
	}

	public StCffOutboundMasterSoap() {
	}

	public StCffOutboundMasterPK getPrimaryKey() {
		return new StCffOutboundMasterPK(_cffDetailsSid, _sessionId,
			_rsModelSid, _userId, _periodSid);
	}

	public void setPrimaryKey(StCffOutboundMasterPK pk) {
		setCffDetailsSid(pk.cffDetailsSid);
		setSessionId(pk.sessionId);
		setRsModelSid(pk.rsModelSid);
		setUserId(pk.userId);
		setPeriodSid(pk.periodSid);
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

	public String getCustomerName() {
		return _customerName;
	}

	public void setCustomerName(String customerName) {
		_customerName = customerName;
	}

	public String getContractHolderId() {
		return _contractHolderId;
	}

	public void setContractHolderId(String contractHolderId) {
		_contractHolderId = contractHolderId;
	}

	public String getBusinessUnitNo() {
		return _businessUnitNo;
	}

	public void setBusinessUnitNo(String businessUnitNo) {
		_businessUnitNo = businessUnitNo;
	}

	public String getYear() {
		return _year;
	}

	public void setYear(String year) {
		_year = year;
	}

	public Date getFinancialForecastApprovalDate() {
		return _financialForecastApprovalDate;
	}

	public void setFinancialForecastApprovalDate(
		Date financialForecastApprovalDate) {
		_financialForecastApprovalDate = financialForecastApprovalDate;
	}

	public String getDeductionId() {
		return _deductionId;
	}

	public void setDeductionId(String deductionId) {
		_deductionId = deductionId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public double getDeductionPerUnit() {
		return _deductionPerUnit;
	}

	public void setDeductionPerUnit(double deductionPerUnit) {
		_deductionPerUnit = deductionPerUnit;
	}

	public double getCogsPerUnit() {
		return _cogsPerUnit;
	}

	public void setCogsPerUnit(double cogsPerUnit) {
		_cogsPerUnit = cogsPerUnit;
	}

	public String getContractType() {
		return _contractType;
	}

	public void setContractType(String contractType) {
		_contractType = contractType;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getBusinessUnitName() {
		return _businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		_businessUnitName = businessUnitName;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public String getFinancialForecastId() {
		return _financialForecastId;
	}

	public void setFinancialForecastId(String financialForecastId) {
		_financialForecastId = financialForecastId;
	}

	public String getProjectId() {
		return _projectId;
	}

	public void setProjectId(String projectId) {
		_projectId = projectId;
	}

	public String getCustomerNo() {
		return _customerNo;
	}

	public void setCustomerNo(String customerNo) {
		_customerNo = customerNo;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public double getSalesDollars() {
		return _salesDollars;
	}

	public void setSalesDollars(double salesDollars) {
		_salesDollars = salesDollars;
	}

	public int getMonth() {
		return _month;
	}

	public void setMonth(int month) {
		_month = month;
	}

	public int getCffDetailsSid() {
		return _cffDetailsSid;
	}

	public void setCffDetailsSid(int cffDetailsSid) {
		_cffDetailsSid = cffDetailsSid;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getDeductionType() {
		return _deductionType;
	}

	public void setDeductionType(String deductionType) {
		_deductionType = deductionType;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
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

	public double getDeductionRate() {
		return _deductionRate;
	}

	public void setDeductionRate(double deductionRate) {
		_deductionRate = deductionRate;
	}

	public String getDeductionCategory() {
		return _deductionCategory;
	}

	public void setDeductionCategory(String deductionCategory) {
		_deductionCategory = deductionCategory;
	}

	public double getCogsAmount() {
		return _cogsAmount;
	}

	public void setCogsAmount(double cogsAmount) {
		_cogsAmount = cogsAmount;
	}

	public String getDeductionNo() {
		return _deductionNo;
	}

	public void setDeductionNo(String deductionNo) {
		_deductionNo = deductionNo;
	}

	public Date getFinancialForecastCreationDate() {
		return _financialForecastCreationDate;
	}

	public void setFinancialForecastCreationDate(
		Date financialForecastCreationDate) {
		_financialForecastCreationDate = financialForecastCreationDate;
	}

	public String getCompanyNo() {
		return _companyNo;
	}

	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	public double getSalesUnits() {
		return _salesUnits;
	}

	public void setSalesUnits(double salesUnits) {
		_salesUnits = salesUnits;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
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

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public String getContractHolderName() {
		return _contractHolderName;
	}

	public void setContractHolderName(String contractHolderName) {
		_contractHolderName = contractHolderName;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public String getCustomerId() {
		return _customerId;
	}

	public void setCustomerId(String customerId) {
		_customerId = customerId;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public double getNetProfitDollars() {
		return _netProfitDollars;
	}

	public void setNetProfitDollars(double netProfitDollars) {
		_netProfitDollars = netProfitDollars;
	}

	public int getGlCompanyMasterSid() {
		return _glCompanyMasterSid;
	}

	public void setGlCompanyMasterSid(int glCompanyMasterSid) {
		_glCompanyMasterSid = glCompanyMasterSid;
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

	public String getDeductionCategory1() {
		return _deductionCategory1;
	}

	public void setDeductionCategory1(String deductionCategory1) {
		_deductionCategory1 = deductionCategory1;
	}

	public String getDeductionCategory2() {
		return _deductionCategory2;
	}

	public void setDeductionCategory2(String deductionCategory2) {
		_deductionCategory2 = deductionCategory2;
	}

	public String getContractHolderNo() {
		return _contractHolderNo;
	}

	public void setContractHolderNo(String contractHolderNo) {
		_contractHolderNo = contractHolderNo;
	}

	public String getDeductionCategory3() {
		return _deductionCategory3;
	}

	public void setDeductionCategory3(String deductionCategory3) {
		_deductionCategory3 = deductionCategory3;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public String getDeductionCategory4() {
		return _deductionCategory4;
	}

	public void setDeductionCategory4(String deductionCategory4) {
		_deductionCategory4 = deductionCategory4;
	}

	public String getDeductionCategory5() {
		return _deductionCategory5;
	}

	public void setDeductionCategory5(String deductionCategory5) {
		_deductionCategory5 = deductionCategory5;
	}

	public String getDeductionCategory6() {
		return _deductionCategory6;
	}

	public void setDeductionCategory6(String deductionCategory6) {
		_deductionCategory6 = deductionCategory6;
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

	public String getProjectionName() {
		return _projectionName;
	}

	public void setProjectionName(String projectionName) {
		_projectionName = projectionName;
	}

	public String getUserId() {
		return _userId;
	}

	public void setUserId(String userId) {
		_userId = userId;
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

	public String getOriginalBatchId() {
		return _originalBatchId;
	}

	public void setOriginalBatchId(String originalBatchId) {
		_originalBatchId = originalBatchId;
	}

	public String getDeductionName() {
		return _deductionName;
	}

	public void setDeductionName(String deductionName) {
		_deductionName = deductionName;
	}

	public double getNetProfitPerUnit() {
		return _netProfitPerUnit;
	}

	public void setNetProfitPerUnit(double netProfitPerUnit) {
		_netProfitPerUnit = netProfitPerUnit;
	}

	public int getPeriodSid() {
		return _periodSid;
	}

	public void setPeriodSid(int periodSid) {
		_periodSid = periodSid;
	}

	public String getSalesInclusion() {
		return _salesInclusion;
	}

	public void setSalesInclusion(String salesInclusion) {
		_salesInclusion = salesInclusion;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getFinancialForecastName() {
		return _financialForecastName;
	}

	public void setFinancialForecastName(String financialForecastName) {
		_financialForecastName = financialForecastName;
	}

	public double getNetSalesDollar() {
		return _netSalesDollar;
	}

	public void setNetSalesDollar(double netSalesDollar) {
		_netSalesDollar = netSalesDollar;
	}

	public double getDeductionDollars() {
		return _deductionDollars;
	}

	public void setDeductionDollars(double deductionDollars) {
		_deductionDollars = deductionDollars;
	}

	public String getContractNo() {
		return _contractNo;
	}

	public void setContractNo(String contractNo) {
		_contractNo = contractNo;
	}

	private boolean _etlCheckRecord;
	private String _customerName;
	private String _contractHolderId;
	private String _businessUnitNo;
	private String _year;
	private Date _financialForecastApprovalDate;
	private String _deductionId;
	private Date _modifiedDate;
	private double _deductionPerUnit;
	private double _cogsPerUnit;
	private String _contractType;
	private String _source;
	private String _businessUnitName;
	private int _contractMasterSid;
	private String _financialForecastId;
	private String _projectId;
	private String _customerNo;
	private String _modifiedBy;
	private double _salesDollars;
	private int _month;
	private int _cffDetailsSid;
	private String _type;
	private String _deductionType;
	private int _companyMasterSid;
	private boolean _checkRecord;
	private String _contractName;
	private double _deductionRate;
	private String _deductionCategory;
	private double _cogsAmount;
	private String _deductionNo;
	private Date _financialForecastCreationDate;
	private String _companyNo;
	private double _salesUnits;
	private String _sessionId;
	private String _itemName;
	private String _deductionInclusion;
	private int _rsModelSid;
	private String _contractHolderName;
	private int _itemMasterSid;
	private String _companyName;
	private String _customerId;
	private String _itemId;
	private double _netProfitDollars;
	private int _glCompanyMasterSid;
	private Date _createdDate;
	private String _createdBy;
	private String _deductionCategory1;
	private String _deductionCategory2;
	private String _contractHolderNo;
	private String _deductionCategory3;
	private String _itemNo;
	private String _deductionCategory4;
	private String _deductionCategory5;
	private String _deductionCategory6;
	private String _contractId;
	private String _deductionProgram;
	private String _businessUnitId;
	private String _projectionName;
	private String _userId;
	private String _companyId;
	private String _outboundStatus;
	private String _originalBatchId;
	private String _deductionName;
	private double _netProfitPerUnit;
	private int _periodSid;
	private String _salesInclusion;
	private String _batchId;
	private String _financialForecastName;
	private double _netSalesDollar;
	private double _deductionDollars;
	private String _contractNo;
}