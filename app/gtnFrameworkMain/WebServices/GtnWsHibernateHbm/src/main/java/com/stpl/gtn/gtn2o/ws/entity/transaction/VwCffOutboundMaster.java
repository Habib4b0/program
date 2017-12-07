package com.stpl.gtn.gtn2o.ws.entity.transaction;

import java.util.Date;

public class VwCffOutboundMaster implements java.io.Serializable {

	private String cffOutboundSid;
	private String financialForecastId;
	private String financialForecastName;
	private String type;
	private String projectId;
	private String projectionName;
	private Integer year;
	private Integer month;
	private String contractId;
	private String contractNo;
	private String contractName;
	private String contractType;
	private String contractHolderId;
	private String contractHolderNo;
	private String contractHolderName;
	private String customerId;
	private String customerNo;
	private String customerName;
	private Integer itemMasterSid;
	private Integer companyMasterSid;
	private Integer contractMasterSid;
	private Integer glCompanyMasterSid;
	private String itemId;
	private String itemNo;
	private String itemName;
	private Double salesDollars;
	private Double salesUnits;
	private String salesInclusion;
	private String deductionId;
	private String deductionNo;
	private String deductionName;
	private String deductionCategory;
	private String deductionType;
	private String deductionProgram;
	private String deductionInclusion;
	private String deductionCategory1;
	private String deductionCategory2;
	private String deductionCategory3;
	private String deductionCategory4;
	private String deductionCategory5;
	private String deductionCategory6;
	private Double deductionDollars;
	private Double deductionRate;
	private Double deductionPerUnit;
	private Double netSalesDollar;
	private Double cogsAmount;
	private Double cogsPerUnit;
	private Double netProfitDollars;
	private Double netProfitPerUnit;
	private String companyId;
	private String companyNo;
	private String companyName;
	private String businessUnitId;
	private String businessUnitNo;
	private String businessUnitName;
	private Date financialForecastCreationDate;
	private Date financialForecastApprovalDate;
	private String batchId;
	private String source;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String outboundStatus;
	private String originalBatchId;
	private boolean checkRecord;
	private int cffDetailsSid;
	private int rsModelSid;
	private int periodSid;

	public VwCffOutboundMaster() {
	}

	public String getCffOutboundSid() {
		return cffOutboundSid;
	}

	public void setCffOutboundSid(String cffOutboundSid) {
		this.cffOutboundSid = cffOutboundSid;
	}

	public String getFinancialForecastId() {
		return this.financialForecastId;
	}

	public void setFinancialForecastId(String financialForecastId) {
		this.financialForecastId = financialForecastId;
	}

	public String getFinancialForecastName() {
		return this.financialForecastName;
	}

	public void setFinancialForecastName(String financialForecastName) {
		this.financialForecastName = financialForecastName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectionName() {
		return this.projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractType() {
		return this.contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractHolderId() {
		return this.contractHolderId;
	}

	public void setContractHolderId(String contractHolderId) {
		this.contractHolderId = contractHolderId;
	}

	public String getContractHolderNo() {
		return this.contractHolderNo;
	}

	public void setContractHolderNo(String contractHolderNo) {
		this.contractHolderNo = contractHolderNo;
	}

	public String getContractHolderName() {
		return this.contractHolderName;
	}

	public void setContractHolderName(String contractHolderName) {
		this.contractHolderName = contractHolderName;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getItemMasterSid() {
		return this.itemMasterSid;
	}

	public void setItemMasterSid(Integer itemMasterSid) {
		this.itemMasterSid = itemMasterSid;
	}

	public Integer getCompanyMasterSid() {
		return this.companyMasterSid;
	}

	public void setCompanyMasterSid(Integer companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public Integer getContractMasterSid() {
		return this.contractMasterSid;
	}

	public void setContractMasterSid(Integer contractMasterSid) {
		this.contractMasterSid = contractMasterSid;
	}

	public Integer getGlCompanyMasterSid() {
		return this.glCompanyMasterSid;
	}

	public void setGlCompanyMasterSid(Integer glCompanyMasterSid) {
		this.glCompanyMasterSid = glCompanyMasterSid;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getSalesDollars() {
		return this.salesDollars;
	}

	public void setSalesDollars(Double salesDollars) {
		this.salesDollars = salesDollars;
	}

	public Double getSalesUnits() {
		return this.salesUnits;
	}

	public void setSalesUnits(Double salesUnits) {
		this.salesUnits = salesUnits;
	}

	public String getSalesInclusion() {
		return this.salesInclusion;
	}

	public void setSalesInclusion(String salesInclusion) {
		this.salesInclusion = salesInclusion;
	}

	public String getDeductionId() {
		return this.deductionId;
	}

	public void setDeductionId(String deductionId) {
		this.deductionId = deductionId;
	}

	public String getDeductionNo() {
		return this.deductionNo;
	}

	public void setDeductionNo(String deductionNo) {
		this.deductionNo = deductionNo;
	}

	public String getDeductionName() {
		return this.deductionName;
	}

	public void setDeductionName(String deductionName) {
		this.deductionName = deductionName;
	}

	public String getDeductionCategory() {
		return this.deductionCategory;
	}

	public void setDeductionCategory(String deductionCategory) {
		this.deductionCategory = deductionCategory;
	}

	public String getDeductionType() {
		return this.deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	public String getDeductionProgram() {
		return this.deductionProgram;
	}

	public void setDeductionProgram(String deductionProgram) {
		this.deductionProgram = deductionProgram;
	}

	public String getDeductionInclusion() {
		return this.deductionInclusion;
	}

	public void setDeductionInclusion(String deductionInclusion) {
		this.deductionInclusion = deductionInclusion;
	}

	public String getDeductionCategory1() {
		return this.deductionCategory1;
	}

	public void setDeductionCategory1(String deductionCategory1) {
		this.deductionCategory1 = deductionCategory1;
	}

	public String getDeductionCategory2() {
		return this.deductionCategory2;
	}

	public void setDeductionCategory2(String deductionCategory2) {
		this.deductionCategory2 = deductionCategory2;
	}

	public String getDeductionCategory3() {
		return this.deductionCategory3;
	}

	public void setDeductionCategory3(String deductionCategory3) {
		this.deductionCategory3 = deductionCategory3;
	}

	public String getDeductionCategory4() {
		return this.deductionCategory4;
	}

	public void setDeductionCategory4(String deductionCategory4) {
		this.deductionCategory4 = deductionCategory4;
	}

	public String getDeductionCategory5() {
		return this.deductionCategory5;
	}

	public void setDeductionCategory5(String deductionCategory5) {
		this.deductionCategory5 = deductionCategory5;
	}

	public String getDeductionCategory6() {
		return this.deductionCategory6;
	}

	public void setDeductionCategory6(String deductionCategory6) {
		this.deductionCategory6 = deductionCategory6;
	}

	public Double getDeductionDollars() {
		return this.deductionDollars;
	}

	public void setDeductionDollars(Double deductionDollars) {
		this.deductionDollars = deductionDollars;
	}

	public Double getDeductionRate() {
		return this.deductionRate;
	}

	public void setDeductionRate(Double deductionRate) {
		this.deductionRate = deductionRate;
	}

	public Double getDeductionPerUnit() {
		return this.deductionPerUnit;
	}

	public void setDeductionPerUnit(Double deductionPerUnit) {
		this.deductionPerUnit = deductionPerUnit;
	}

	public Double getNetSalesDollar() {
		return this.netSalesDollar;
	}

	public void setNetSalesDollar(Double netSalesDollar) {
		this.netSalesDollar = netSalesDollar;
	}

	public Double getCogsAmount() {
		return this.cogsAmount;
	}

	public void setCogsAmount(Double cogsAmount) {
		this.cogsAmount = cogsAmount;
	}

	public Double getCogsPerUnit() {
		return this.cogsPerUnit;
	}

	public void setCogsPerUnit(Double cogsPerUnit) {
		this.cogsPerUnit = cogsPerUnit;
	}

	public Double getNetProfitDollars() {
		return this.netProfitDollars;
	}

	public void setNetProfitDollars(Double netProfitDollars) {
		this.netProfitDollars = netProfitDollars;
	}

	public Double getNetProfitPerUnit() {
		return this.netProfitPerUnit;
	}

	public void setNetProfitPerUnit(Double netProfitPerUnit) {
		this.netProfitPerUnit = netProfitPerUnit;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBusinessUnitId() {
		return this.businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitNo() {
		return this.businessUnitNo;
	}

	public void setBusinessUnitNo(String businessUnitNo) {
		this.businessUnitNo = businessUnitNo;
	}

	public String getBusinessUnitName() {
		return this.businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public Date getFinancialForecastCreationDate() {
		return this.financialForecastCreationDate;
	}

	public void setFinancialForecastCreationDate(Date financialForecastCreationDate) {
		this.financialForecastCreationDate = financialForecastCreationDate;
	}

	public Date getFinancialForecastApprovalDate() {
		return this.financialForecastApprovalDate;
	}

	public void setFinancialForecastApprovalDate(Date financialForecastApprovalDate) {
		this.financialForecastApprovalDate = financialForecastApprovalDate;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOutboundStatus() {
		return this.outboundStatus;
	}

	public void setOutboundStatus(String outboundStatus) {
		this.outboundStatus = outboundStatus;
	}

	public String getOriginalBatchId() {
		return this.originalBatchId;
	}

	public void setOriginalBatchId(String originalBatchId) {
		this.originalBatchId = originalBatchId;
	}

	public boolean isCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		this.checkRecord = checkRecord;
	}

	public int getCffDetailsSid() {
		return cffDetailsSid;
	}

	public void setCffDetailsSid(int cffDetailsSid) {
		this.cffDetailsSid = cffDetailsSid;
	}

	public int getRsModelSid() {
		return rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		this.rsModelSid = rsModelSid;
	}

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
	}

}
