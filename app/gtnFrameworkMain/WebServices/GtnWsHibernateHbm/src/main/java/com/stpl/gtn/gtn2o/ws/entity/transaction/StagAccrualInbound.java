package com.stpl.gtn.gtn2o.ws.entity.transaction;

import java.util.Date;

@SuppressWarnings("serial")
public class StagAccrualInbound implements java.io.Serializable {

	private int stagAccrualInboundSid;
	private String accrualInboundInterfaceId;
	private String accrualId;
	private String salesMasterId;
	private String glAccount;
	private String companyId;
	private String companyNo;
	private String compIdentCodeQualifier;
	private String companyCostCenter;
	private String accountId;
	private String accountNo;
	private String accountName;
	private String acctIdentCodeQualifier;
	private String itemId;
	private String itemNo;
	private String itemIdentCodeQualifier;
	private String contractId;
	private String contractNo;
	private String contractName;
	private String brandId;
	private String categoryId;
	private String provisionId;
	private String accrualType;
	private String accrualPeriodStartDate;
	private String accrualPeriodEndDate;
	private String deductionAmount;
	private String subLedger;
	private String subLedgerType;
	private String documentType;
	private String postingDate;
	private String glDate;
	private String recordCreatedDate;
	private String batchId;
	private String source;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String addChgDelIndicator;
	private String companyName;
	private String businessUnitId;
	private String businessUnitNo;
	private String buIdentCodeQualifier;
	private String businessUnitName;
	private String itemName;
	private String brandName;
	private String accrualCategory;
	private String accrualScheduleType;
	private String accrualProgramType;
	private String salesAmount;
	private String quantity;
	private String accrualUdc1;
	private String accrualUdc2;
	private String accrualUdc3;
	private String accrualUdc4;
	private String accrualUdc5;
	private String accrualUdc6;

	public StagAccrualInbound() {
	}

	public StagAccrualInbound(int stagAccrualInboundSid, String accrualInboundInterfaceId, String accrualId,
			String glAccount, String companyId, String companyNo, String companyCostCenter, String accountId,
			String itemId, String contractId, String provisionId, String accrualPeriodStartDate,
			String accrualPeriodEndDate, String deductionAmount, String subLedger, String subLedgerType,
			String documentType, String batchId, String addChgDelIndicator, String businessUnitId) {
		this.stagAccrualInboundSid = stagAccrualInboundSid;
		this.accrualInboundInterfaceId = accrualInboundInterfaceId;
		this.accrualId = accrualId;
		this.glAccount = glAccount;
		this.companyId = companyId;
		this.companyNo = companyNo;
		this.companyCostCenter = companyCostCenter;
		this.accountId = accountId;
		this.itemId = itemId;
		this.contractId = contractId;
		this.provisionId = provisionId;
		this.accrualPeriodStartDate = accrualPeriodStartDate;
		this.accrualPeriodEndDate = accrualPeriodEndDate;
		this.deductionAmount = deductionAmount;
		this.subLedger = subLedger;
		this.subLedgerType = subLedgerType;
		this.documentType = documentType;
		this.batchId = batchId;
		this.addChgDelIndicator = addChgDelIndicator;
		this.businessUnitId = businessUnitId;
	}

	public StagAccrualInbound(int stagAccrualInboundSid, String accrualInboundInterfaceId, String accrualId,
			String salesMasterId, String glAccount, String companyId, String companyNo, String compIdentCodeQualifier,
			String companyCostCenter, String accountId, String accountNo, String accountName,
			String acctIdentCodeQualifier, String itemId, String itemNo, String itemIdentCodeQualifier,
			String contractId, String contractNo, String contractName, String brandId, String categoryId,
			String provisionId, String accrualType, String accrualPeriodStartDate, String accrualPeriodEndDate,
			String deductionAmount, String subLedger, String subLedgerType, String documentType, String postingDate,
			String glDate, String recordCreatedDate, String batchId, String source, String createdBy, Date createdDate,
			String modifiedBy, Date modifiedDate, String addChgDelIndicator, String companyName, String businessUnitId,
			String businessUnitNo, String buIdentCodeQualifier, String businessUnitName, String itemName,
			String brandName, String accrualCategory, String accrualScheduleType, String accrualProgramType,
			String salesAmount, String quantity, String accrualUdc1, String accrualUdc2, String accrualUdc3,
			String accrualUdc4, String accrualUdc5, String accrualUdc6) {
		this.stagAccrualInboundSid = stagAccrualInboundSid;
		this.accrualInboundInterfaceId = accrualInboundInterfaceId;
		this.accrualId = accrualId;
		this.salesMasterId = salesMasterId;
		this.glAccount = glAccount;
		this.companyId = companyId;
		this.companyNo = companyNo;
		this.compIdentCodeQualifier = compIdentCodeQualifier;
		this.companyCostCenter = companyCostCenter;
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.acctIdentCodeQualifier = acctIdentCodeQualifier;
		this.itemId = itemId;
		this.itemNo = itemNo;
		this.itemIdentCodeQualifier = itemIdentCodeQualifier;
		this.contractId = contractId;
		this.contractNo = contractNo;
		this.contractName = contractName;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.provisionId = provisionId;
		this.accrualType = accrualType;
		this.accrualPeriodStartDate = accrualPeriodStartDate;
		this.accrualPeriodEndDate = accrualPeriodEndDate;
		this.deductionAmount = deductionAmount;
		this.subLedger = subLedger;
		this.subLedgerType = subLedgerType;
		this.documentType = documentType;
		this.postingDate = postingDate;
		this.glDate = glDate;
		this.recordCreatedDate = recordCreatedDate;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.addChgDelIndicator = addChgDelIndicator;
		this.companyName = companyName;
		this.businessUnitId = businessUnitId;
		this.businessUnitNo = businessUnitNo;
		this.buIdentCodeQualifier = buIdentCodeQualifier;
		this.businessUnitName = businessUnitName;
		this.itemName = itemName;
		this.brandName = brandName;
		this.accrualCategory = accrualCategory;
		this.accrualScheduleType = accrualScheduleType;
		this.accrualProgramType = accrualProgramType;
		this.salesAmount = salesAmount;
		this.quantity = quantity;
		this.accrualUdc1 = accrualUdc1;
		this.accrualUdc2 = accrualUdc2;
		this.accrualUdc3 = accrualUdc3;
		this.accrualUdc4 = accrualUdc4;
		this.accrualUdc5 = accrualUdc5;
		this.accrualUdc6 = accrualUdc6;
	}

	public int getStagAccrualInboundSid() {
		return this.stagAccrualInboundSid;
	}

	public void setStagAccrualInboundSid(int stagAccrualInboundSid) {
		this.stagAccrualInboundSid = stagAccrualInboundSid;
	}

	public String getAccrualInboundInterfaceId() {
		return this.accrualInboundInterfaceId;
	}

	public void setAccrualInboundInterfaceId(String accrualInboundInterfaceId) {
		this.accrualInboundInterfaceId = accrualInboundInterfaceId;
	}

	public String getAccrualId() {
		return this.accrualId;
	}

	public void setAccrualId(String accrualId) {
		this.accrualId = accrualId;
	}

	public String getSalesMasterId() {
		return this.salesMasterId;
	}

	public void setSalesMasterId(String salesMasterId) {
		this.salesMasterId = salesMasterId;
	}

	public String getGlAccount() {
		return this.glAccount;
	}

	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
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

	public String getCompIdentCodeQualifier() {
		return this.compIdentCodeQualifier;
	}

	public void setCompIdentCodeQualifier(String compIdentCodeQualifier) {
		this.compIdentCodeQualifier = compIdentCodeQualifier;
	}

	public String getCompanyCostCenter() {
		return this.companyCostCenter;
	}

	public void setCompanyCostCenter(String companyCostCenter) {
		this.companyCostCenter = companyCostCenter;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAcctIdentCodeQualifier() {
		return this.acctIdentCodeQualifier;
	}

	public void setAcctIdentCodeQualifier(String acctIdentCodeQualifier) {
		this.acctIdentCodeQualifier = acctIdentCodeQualifier;
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

	public String getItemIdentCodeQualifier() {
		return this.itemIdentCodeQualifier;
	}

	public void setItemIdentCodeQualifier(String itemIdentCodeQualifier) {
		this.itemIdentCodeQualifier = itemIdentCodeQualifier;
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

	public String getBrandId() {
		return this.brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProvisionId() {
		return this.provisionId;
	}

	public void setProvisionId(String provisionId) {
		this.provisionId = provisionId;
	}

	public String getAccrualType() {
		return this.accrualType;
	}

	public void setAccrualType(String accrualType) {
		this.accrualType = accrualType;
	}

	public String getDeductionAmount() {
		return this.deductionAmount;
	}

	public void setDeductionAmount(String deductionAmount) {
		this.deductionAmount = deductionAmount;
	}

	public String getSubLedger() {
		return this.subLedger;
	}

	public void setSubLedger(String subLedger) {
		this.subLedger = subLedger;
	}

	public String getSubLedgerType() {
		return this.subLedgerType;
	}

	public void setSubLedgerType(String subLedgerType) {
		this.subLedgerType = subLedgerType;
	}

	public String getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getAccrualPeriodStartDate() {
		return accrualPeriodStartDate;
	}

	public void setAccrualPeriodStartDate(String accrualPeriodStartDate) {
		this.accrualPeriodStartDate = accrualPeriodStartDate;
	}

	public String getAccrualPeriodEndDate() {
		return accrualPeriodEndDate;
	}

	public void setAccrualPeriodEndDate(String accrualPeriodEndDate) {
		this.accrualPeriodEndDate = accrualPeriodEndDate;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getGlDate() {
		return glDate;
	}

	public void setGlDate(String glDate) {
		this.glDate = glDate;
	}

	public String getRecordCreatedDate() {
		return recordCreatedDate;
	}

	public void setRecordCreatedDate(String recordCreatedDate) {
		this.recordCreatedDate = recordCreatedDate;
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

	public String getAddChgDelIndicator() {
		return this.addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		this.addChgDelIndicator = addChgDelIndicator;
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

	public String getBuIdentCodeQualifier() {
		return this.buIdentCodeQualifier;
	}

	public void setBuIdentCodeQualifier(String buIdentCodeQualifier) {
		this.buIdentCodeQualifier = buIdentCodeQualifier;
	}

	public String getBusinessUnitName() {
		return this.businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getAccrualCategory() {
		return this.accrualCategory;
	}

	public void setAccrualCategory(String accrualCategory) {
		this.accrualCategory = accrualCategory;
	}

	public String getAccrualScheduleType() {
		return this.accrualScheduleType;
	}

	public void setAccrualScheduleType(String accrualScheduleType) {
		this.accrualScheduleType = accrualScheduleType;
	}

	public String getAccrualProgramType() {
		return this.accrualProgramType;
	}

	public void setAccrualProgramType(String accrualProgramType) {
		this.accrualProgramType = accrualProgramType;
	}

	public String getSalesAmount() {
		return this.salesAmount;
	}

	public void setSalesAmount(String salesAmount) {
		this.salesAmount = salesAmount;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAccrualUdc1() {
		return this.accrualUdc1;
	}

	public void setAccrualUdc1(String accrualUdc1) {
		this.accrualUdc1 = accrualUdc1;
	}

	public String getAccrualUdc2() {
		return this.accrualUdc2;
	}

	public void setAccrualUdc2(String accrualUdc2) {
		this.accrualUdc2 = accrualUdc2;
	}

	public String getAccrualUdc3() {
		return this.accrualUdc3;
	}

	public void setAccrualUdc3(String accrualUdc3) {
		this.accrualUdc3 = accrualUdc3;
	}

	public String getAccrualUdc4() {
		return this.accrualUdc4;
	}

	public void setAccrualUdc4(String accrualUdc4) {
		this.accrualUdc4 = accrualUdc4;
	}

	public String getAccrualUdc5() {
		return this.accrualUdc5;
	}

	public void setAccrualUdc5(String accrualUdc5) {
		this.accrualUdc5 = accrualUdc5;
	}

	public String getAccrualUdc6() {
		return this.accrualUdc6;
	}

	public void setAccrualUdc6(String accrualUdc6) {
		this.accrualUdc6 = accrualUdc6;
	}

}
