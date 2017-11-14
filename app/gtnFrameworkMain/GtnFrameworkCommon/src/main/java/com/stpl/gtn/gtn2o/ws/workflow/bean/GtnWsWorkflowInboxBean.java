/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.workflow.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnWsWorkflowInboxBean implements Serializable {

	public GtnWsWorkflowInboxBean() {
		// constructor

	}

	private static final long serialVersionUID = -5362500031533055558L;

	private int workflowSid;

	private String workflowId;

	private String workflowName;

	private String workflowDesc;

	private Date createdFrom;

	private Date createdTo;

	private Date approvedFrom;

	private Date approvedTo;

	private String contractId;

	private String contractNo;

	private String companyNo;

	private String companyName;

	private String businessUnitId;

	private String businessUnitNo;

	private String businessUnitName;

	private String contractName;

	private String itemNo;

	private String itemName;

	private int forecastdeductionValue;

	private int forecastdeductionLevel;

	private int contractType;

	private String companyID;

	private String itemId;

	private String businessUnitIdReturns;

	private String businessUnitNoReturns;

	private String businessUnitNameReturns;

	private String itemNoReturns;

	private String itemNameReturns;

	private String itemIdReturns;

	private int companyARM;

	private int businessUnitARM;

	private int workflowStatusArm;

	private int adjustmentType;

	private String contractIdArm;

	private String contractNoArm;

	private String customerNoArm;

	private String customerNameArm;

	private String brandIdArm;

	private String contractNameArm;

	private String itemNoArm;

	private String itemNameArm;

	private String brandNameArm;

	private Date glDateArm;

	private int deductionLevelArm;

	private int deductionValueArm;

	private String deductionNoArm;

	private String deductionNameArm;

	private String viewType;

	private String viewName;
	// viewNamePrivate

	private boolean viewMode;

	private String businessProcess;

	private String createdByPrivate;

	private Date createdDatePrivate;

	private String approvedByPrivate;

	private Date approvedDateToPrivate;

	private String viewNamePrivate;

	private String fileName;

	private int processInstanceId;

	private int accClosureMasterSid;

	private int contractMasterSid;

	private String contractStructure;

	private int projectionMasterSid;

	private int workflowinboxSid;

	private String createdBy;

	private String approvedBy;

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getWorkflowDesc() {
		return workflowDesc;
	}

	public void setWorkflowDesc(String workflowDesc) {
		this.workflowDesc = workflowDesc;
	}

	public Date getCreatedFrom() {
		return checkNullForDateAndAssign(createdFrom);
	}

	public void setCreatedFrom(Date createdFrom) {
		this.createdFrom = checkNullForDateAndAssign(createdFrom);
	}

	public Date getCreatedTo() {
		return checkNullForDateAndAssign(createdTo);
	}

	public void setCreatedTo(Date createdTo) {
		this.createdTo = checkNullForDateAndAssign(createdTo);
	}

	public Date getApprovedFrom() {
		return checkNullForDateAndAssign(approvedFrom);
	}

	public void setApprovedFrom(Date approvedFrom) {
		this.approvedFrom = checkNullForDateAndAssign(approvedFrom);
	}

	public Date getApprovedTo() {
		return checkNullForDateAndAssign(approvedTo);
	}

	public void setApprovedTo(Date approvedTo) {
		this.approvedTo = checkNullForDateAndAssign(approvedTo);
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitNo() {
		return businessUnitNo;
	}

	public void setBusinessUnitNo(String businessUnitNo) {
		this.businessUnitNo = businessUnitNo;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getForecastdeductionValue() {
		return forecastdeductionValue;
	}

	public void setForecastdeductionValue(int forecastdeductionValue) {
		this.forecastdeductionValue = forecastdeductionValue;
	}

	public int getForecastdeductionLevel() {
		return forecastdeductionLevel;
	}

	public void setForecastdeductionLevel(int forecastdeductionLevel) {
		this.forecastdeductionLevel = forecastdeductionLevel;
	}

	public int getContractType() {
		return contractType;
	}

	public void setContractType(int contractType) {
		this.contractType = contractType;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBusinessUnitIdReturns() {
		return businessUnitIdReturns;
	}

	public void setBusinessUnitIdReturns(String businessUnitIdReturns) {
		this.businessUnitIdReturns = businessUnitIdReturns;
	}

	public String getBusinessUnitNoReturns() {
		return businessUnitNoReturns;
	}

	public void setBusinessUnitNoReturns(String businessUnitNoReturns) {
		this.businessUnitNoReturns = businessUnitNoReturns;
	}

	public String getBusinessUnitNameReturns() {
		return businessUnitNameReturns;
	}

	public void setBusinessUnitNameReturns(String businessUnitNameReturns) {
		this.businessUnitNameReturns = businessUnitNameReturns;
	}

	public String getItemNoReturns() {
		return itemNoReturns;
	}

	public void setItemNoReturns(String itemNoReturns) {
		this.itemNoReturns = itemNoReturns;
	}

	public String getItemNameReturns() {
		return itemNameReturns;
	}

	public void setItemNameReturns(String itemNameReturns) {
		this.itemNameReturns = itemNameReturns;
	}

	public String getItemIdReturns() {
		return itemIdReturns;
	}

	public void setItemIdReturns(String itemIdReturns) {
		this.itemIdReturns = itemIdReturns;
	}

	public int getCompanyARM() {
		return companyARM;
	}

	public void setCompanyARM(int companyARM) {
		this.companyARM = companyARM;
	}

	public int getBusinessUnitARM() {
		return businessUnitARM;
	}

	public void setBusinessUnitARM(int businessUnitARM) {
		this.businessUnitARM = businessUnitARM;
	}

	public int getWorkflowStatusArm() {
		return workflowStatusArm;
	}

	public void setWorkflowStatusArm(int workflowStatusArm) {
		this.workflowStatusArm = workflowStatusArm;
	}

	public int getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(int adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getContractIdArm() {
		return contractIdArm;
	}

	public void setContractIdArm(String contractIdArm) {
		this.contractIdArm = contractIdArm;
	}

	public String getContractNoArm() {
		return contractNoArm;
	}

	public void setContractNoArm(String contractNoArm) {
		this.contractNoArm = contractNoArm;
	}

	public String getCustomerNoArm() {
		return customerNoArm;
	}

	public void setCustomerNoArm(String customerNoArm) {
		this.customerNoArm = customerNoArm;
	}

	public String getCustomerNameArm() {
		return customerNameArm;
	}

	public void setCustomerNameArm(String customerNameArm) {
		this.customerNameArm = customerNameArm;
	}

	public String getBrandIdArm() {
		return brandIdArm;
	}

	public void setBrandIdArm(String brandIdArm) {
		this.brandIdArm = brandIdArm;
	}

	public String getContractNameArm() {
		return contractNameArm;
	}

	public void setContractNameArm(String contractNameArm) {
		this.contractNameArm = contractNameArm;
	}

	public String getItemNoArm() {
		return itemNoArm;
	}

	public void setItemNoArm(String itemNoArm) {
		this.itemNoArm = itemNoArm;
	}

	public String getItemNameArm() {
		return itemNameArm;
	}

	public void setItemNameArm(String itemNameArm) {
		this.itemNameArm = itemNameArm;
	}

	public String getBrandNameArm() {
		return brandNameArm;
	}

	public void setBrandNameArm(String brandNameArm) {
		this.brandNameArm = brandNameArm;
	}

	public Date getGlDateArm() {
		return glDateArm == null ? null : (Date) glDateArm.clone();
	}

	public void setGlDateArm(Date glDateArm) {
		this.glDateArm = glDateArm == null ? null : (Date) glDateArm.clone();
	}

	public int getDeductionLevelArm() {
		return deductionLevelArm;
	}

	public void setDeductionLevelArm(int deductionLevelArm) {
		this.deductionLevelArm = deductionLevelArm;
	}

	public int getDeductionValueArm() {
		return deductionValueArm;
	}

	public void setDeductionValueArm(int deductionValueArm) {
		this.deductionValueArm = deductionValueArm;
	}

	public String getDeductionNoArm() {
		return deductionNoArm;
	}

	public void setDeductionNoArm(String deductionNoArm) {
		this.deductionNoArm = deductionNoArm;
	}

	public String getDeductionNameArm() {
		return deductionNameArm;
	}

	public void setDeductionNameArm(String deductionNameArm) {
		this.deductionNameArm = deductionNameArm;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public boolean isViewMode() {
		return viewMode;
	}

	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}

	public int getWorkflowSid() {
		return workflowSid;
	}

	public void setWorkflowSid(int workflowSid) {
		this.workflowSid = workflowSid;
	}

	public String getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}

	public String getCreatedByPrivate() {
		return createdByPrivate;
	}

	public void setCreatedByPrivate(String createdByPrivate) {
		this.createdByPrivate = createdByPrivate;
	}

	public Date getCreatedDatePrivate() {
		return createdDatePrivate == null ? null : (Date) createdDatePrivate.clone();
	}

	public void setCreatedDatePrivate(Date createdDatePrivate) {
		this.createdDatePrivate = createdDatePrivate == null ? null : (Date) createdDatePrivate.clone();
	}

	public String getApprovedByPrivate() {
		return approvedByPrivate;
	}

	public void setApprovedByPrivate(String approvedByPrivate) {
		this.approvedByPrivate = approvedByPrivate;
	}

	public Date getApprovedDateToPrivate() {
		return approvedDateToPrivate == null ? null : (Date) approvedDateToPrivate.clone();
	}

	public void setApprovedDateToPrivate(Date approvedDateToPrivate) {
		this.approvedDateToPrivate = approvedDateToPrivate == null ? null : (Date) approvedDateToPrivate.clone();
	}

	public String getViewNamePrivate() {
		return viewNamePrivate;
	}

	public void setViewNamePrivate(String viewNamePrivate) {
		this.viewNamePrivate = viewNamePrivate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(int processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public int getAccClosureMasterSid() {
		return accClosureMasterSid;
	}

	public void setAccClosureMasterSid(int accClosureMasterSid) {
		this.accClosureMasterSid = accClosureMasterSid;
	}

	public int getContractMasterSid() {
		return contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		this.contractMasterSid = contractMasterSid;
	}

	public String getContractStructure() {
		return contractStructure;
	}

	public void setContractStructure(String contractStructure) {
		this.contractStructure = contractStructure;
	}

	public int getProjectionMasterSid() {
		return projectionMasterSid;
	}

	public void setProjectionMasterSid(int projectionMasterSid) {
		this.projectionMasterSid = projectionMasterSid;
	}

	public int getWorkflowinboxSid() {
		return workflowinboxSid;
	}

	public void setWorkflowinboxSid(int workflowinboxSid) {
		this.workflowinboxSid = workflowinboxSid;
	}

	private Date checkNullForDateAndAssign(Date date) {
		return date != null ? (Date) date.clone() : date;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

}
