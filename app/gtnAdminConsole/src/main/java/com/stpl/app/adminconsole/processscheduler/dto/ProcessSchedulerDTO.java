/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.dto;

import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Jayaram
 */
public class ProcessSchedulerDTO {

    private Integer processSid = 0;
    private String processName = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private String frequencyRadio = StringUtils.EMPTY;
    private String manualLastRun;
    private String scheduleLastRun;
    private Date modifiedDate;
    private String modifiedBy = StringUtils.EMPTY;
    private String hoursOne = StringUtils.EMPTY;
    private String hoursTwo = StringUtils.EMPTY;
    private String hoursThree = StringUtils.EMPTY;
    private String minutesOne = StringUtils.EMPTY;
    private String minutesTwo = StringUtils.EMPTY;
    private String minutesThree = StringUtils.EMPTY;
    private String runHours = StringUtils.EMPTY;
    private String runMinutes = StringUtils.EMPTY;
    private String isSelected = StringUtils.EMPTY;

    private String processDisplayName = StringUtils.EMPTY;
    private String successCC = StringUtils.EMPTY;
    private String successTo = StringUtils.EMPTY;
    private String failCC = StringUtils.EMPTY;
    private String failTo = StringUtils.EMPTY;
    private String successSubject = StringUtils.EMPTY;
    private String failSubject = StringUtils.EMPTY;
    private String successText = StringUtils.EMPTY;
    private String failText = StringUtils.EMPTY;

    private String financialForecastId = StringUtils.EMPTY;
    private String financialForecastName = StringUtils.EMPTY;
    private HelperDTO typeDdlb;
    private String typeDesc = StringUtils.EMPTY;
    private int excelId = 0;
    private String year = "0";
    private int month = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean count = false;
    private List<SortByColumn> orderByColumns = new ArrayList<>();
    private Set<Container.Filter> filters;
    
    private String scriptName = StringUtils.EMPTY;
    private boolean checkRecord;
    
    private String projectionId = StringUtils.EMPTY;
    private String projectionName = StringUtils.EMPTY;
    private String contractId = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String contractType = StringUtils.EMPTY;
    private String contractHolderId = StringUtils.EMPTY;
    private String contractHolderNo = StringUtils.EMPTY;
    private String contractHolderName = StringUtils.EMPTY;
    private String customerId = StringUtils.EMPTY;
    private String customerNo = StringUtils.EMPTY;
    private String customerName = StringUtils.EMPTY;
    private String itemId = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String salesDollars = StringUtils.EMPTY;
    private String salesUnits = StringUtils.EMPTY;
    private String salesInclusion = StringUtils.EMPTY;
    private String deductionId = StringUtils.EMPTY;
    private String deductionNo = StringUtils.EMPTY;
    private String deductionName = StringUtils.EMPTY;
    private String deductionCategory = StringUtils.EMPTY;
    private String deductionType = StringUtils.EMPTY;
    private String deductionProgram = StringUtils.EMPTY;
    private String deductionInclusion = StringUtils.EMPTY;
    private String deductionCategoryOne = StringUtils.EMPTY;
    private String deductionCategoryTwo = StringUtils.EMPTY;
    private String deductionCategoryThree = StringUtils.EMPTY;
    private String deductionCategoryFour = StringUtils.EMPTY;
    private String deductionCategoryFive = StringUtils.EMPTY;
    private String deductionCategorySix = StringUtils.EMPTY;
    private String deductionDollars = StringUtils.EMPTY;
    private String deductionRate = StringUtils.EMPTY;
    private String deductionPerUnit = StringUtils.EMPTY;
    private String netSalesDollar = StringUtils.EMPTY;
    private String cogsAmount = StringUtils.EMPTY;
    private String cogsPerUnit = StringUtils.EMPTY;
    private String netProfitDollars = StringUtils.EMPTY;
    private String netProfitPerUnit = StringUtils.EMPTY;
    private String companyId = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String businessUnitId = StringUtils.EMPTY;
    private String businessUnitNo = StringUtils.EMPTY;
    private String businessUnitName = StringUtils.EMPTY;
    private String financialForecastCreationDate = StringUtils.EMPTY;
    private String financialForecastApprovalDate = StringUtils.EMPTY;
    private String batchId = StringUtils.EMPTY;
    private String source = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private String createdDate = StringUtils.EMPTY;
    private String outboundStatus = StringUtils.EMPTY;
    private String originalBatchId = StringUtils.EMPTY;  
    
    private Integer companyMasterSid = 0;
    private Integer itemMasterSid = 0;
    private Integer contractMasterSid = 0;
    private Integer glCompMasterSid = 0;
    private Integer periodSid = 0;
    private Integer cffDetailSid = 0;
    private Integer rsModelSid = 0;
    
    private Date cffCreationDateFrom;
    private Date cffCreationDateTo;
    private Date cffApprovalDateFrom;
    private Date cffApprovalDateTo;

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getFinancialForecastId() {
        return financialForecastId;
    }

    public void setFinancialForecastId(String financialForecastId) {
        this.financialForecastId = financialForecastId;
    }

    public String getFinancialForecastName() {
        return financialForecastName;
    }

    public void setFinancialForecastName(String financialForecastName) {
        this.financialForecastName = financialForecastName;
    }

    public HelperDTO getTypeDdlb() {
        return typeDdlb;
    }

    public void setTypeDdlb(HelperDTO typeDdlb) {
        this.typeDdlb = typeDdlb;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public int getExcelId() {
        return excelId;
    }

    public void setExcelId(int excelId) {
        this.excelId = excelId;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public List<SortByColumn> getOrderByColumns() {
        return orderByColumns == null ? orderByColumns : Collections.unmodifiableList(orderByColumns);
    }

    public void setOrderByColumns(List<SortByColumn> orderByColumns) {
        this.orderByColumns = orderByColumns == null ? orderByColumns : Collections.unmodifiableList(orderByColumns);
    }

    public Set<Container.Filter> getFilters() {
        return filters == null ? filters : Collections.unmodifiableSet(filters);
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters == null ? filters : Collections.unmodifiableSet(filters);
    }

    public String getProcessDisplayName() {
        return processDisplayName;
    }

    public void setProcessDisplayName(String processDisplayName) {
        this.processDisplayName = processDisplayName;
    }

    public String getSuccessCC() {
        return successCC;
    }

    public void setSuccessCC(String successCC) {
        this.successCC = successCC;
    }

    public String getSuccessTo() {
        return successTo;
    }

    public void setSuccessTo(String successTo) {
        this.successTo = successTo;
    }

    public String getFailCC() {
        return failCC;
    }

    public void setFailCC(String failCC) {
        this.failCC = failCC;
    }

    public String getFailTo() {
        return failTo;
    }

    public void setFailTo(String failTo) {
        this.failTo = failTo;
    }

    public String getSuccessSubject() {
        return successSubject;
    }

    public void setSuccessSubject(String successSubject) {
        this.successSubject = successSubject;
    }

    public String getFailSubject() {
        return failSubject;
    }

    public void setFailSubject(String failSubject) {
        this.failSubject = failSubject;
    }

    public String getSuccessText() {
        return successText;
    }

    public void setSuccessText(String successText) {
        this.successText = successText;
    }

    public String getFailText() {
        return failText;
    }

    public void setFailText(String failText) {
        this.failText = failText;
    }

    public String getManualLastRun() {
        return manualLastRun;
    }

    public void setManualLastRun(String manualLastRun) {
        this.manualLastRun = manualLastRun;
    }

    public String getScheduleLastRun() {
        return scheduleLastRun;
    }

    public void setScheduleLastRun(String scheduleLastRun) {
        this.scheduleLastRun = scheduleLastRun;
    }

    public String getRunHours() {
        return runHours;
    }

    public void setRunHours(String runHours) {
        this.runHours = runHours;
    }

    public String getRunMinutes() {
        return runMinutes;
    }

    public void setRunMinutes(String runMinutes) {
        this.runMinutes = runMinutes;
    }

    public String getHoursOne() {
        return hoursOne;
    }

    public void setHoursOne(String hoursOne) {
        this.hoursOne = hoursOne;
    }

    public String getHoursTwo() {
        return hoursTwo;
    }

    public void setHoursTwo(String hoursTwo) {
        this.hoursTwo = hoursTwo;
    }

    public String getHoursThree() {
        return hoursThree;
    }

    public void setHoursThree(String hoursThree) {
        this.hoursThree = hoursThree;
    }

    public String getMinutesOne() {
        return minutesOne;
    }

    public void setMinutesOne(String minutesOne) {
        this.minutesOne = minutesOne;
    }

    public String getMinutesTwo() {
        return minutesTwo;
    }

    public void setMinutesTwo(String minutesTwo) {
        this.minutesTwo = minutesTwo;
    }

    public String getMinutesThree() {
        return minutesThree;
    }

    public void setMinutesThree(String minutesThree) {
        this.minutesThree = minutesThree;
    }

    public Integer getProcessSid() {
        return processSid;
    }

    public void setProcessSid(Integer processSid) {
        this.processSid = processSid;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    public Date getEndDate() {
        return endDate == null ? null : (Date) endDate.clone();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate == null ? null : (Date) endDate.clone();
    }

    public String getFrequencyRadio() {
        return frequencyRadio;
    }

    public void setFrequencyRadio(String frequencyRadio) {
        this.frequencyRadio = frequencyRadio;
    }

    public Date getModifiedDate() {
        return modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
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

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractHolderId() {
        return contractHolderId;
    }

    public void setContractHolderId(String contractHolderId) {
        this.contractHolderId = contractHolderId;
    }

    public String getContractHolderNo() {
        return contractHolderNo;
    }

    public void setContractHolderNo(String contractHolderNo) {
        this.contractHolderNo = contractHolderNo;
    }

    public String getContractHolderName() {
        return contractHolderName;
    }

    public void setContractHolderName(String contractHolderName) {
        this.contractHolderName = contractHolderName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getSalesDollars() {
        return salesDollars;
    }

    public void setSalesDollars(String salesDollars) {
        this.salesDollars = salesDollars;
    }

    public String getSalesUnits() {
        return salesUnits;
    }

    public void setSalesUnits(String salesUnits) {
        this.salesUnits = salesUnits;
    }

    public String getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(String salesInclusion) {
        this.salesInclusion = salesInclusion;
    }

    public String getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(String deductionId) {
        this.deductionId = deductionId;
    }

    public String getDeductionNo() {
        return deductionNo;
    }

    public void setDeductionNo(String deductionNo) {
        this.deductionNo = deductionNo;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public String getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        this.deductionCategory = deductionCategory;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getDeductionProgram() {
        return deductionProgram;
    }

    public void setDeductionProgram(String deductionProgram) {
        this.deductionProgram = deductionProgram;
    }

    public String getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

    public String getDeductionCategoryOne() {
        return deductionCategoryOne;
    }

    public void setDeductionCategoryOne(String deductionCategoryOne) {
        this.deductionCategoryOne = deductionCategoryOne;
    }

    public String getDeductionCategoryTwo() {
        return deductionCategoryTwo;
    }

    public void setDeductionCategoryTwo(String deductionCategoryTwo) {
        this.deductionCategoryTwo = deductionCategoryTwo;
    }

    public String getDeductionCategoryThree() {
        return deductionCategoryThree;
    }

    public void setDeductionCategoryThree(String deductionCategoryThree) {
        this.deductionCategoryThree = deductionCategoryThree;
    }

    public String getDeductionCategoryFour() {
        return deductionCategoryFour;
    }

    public void setDeductionCategoryFour(String deductionCategoryFour) {
        this.deductionCategoryFour = deductionCategoryFour;
    }

    public String getDeductionCategoryFive() {
        return deductionCategoryFive;
    }

    public void setDeductionCategoryFive(String deductionCategoryFive) {
        this.deductionCategoryFive = deductionCategoryFive;
    }

    public String getDeductionCategorySix() {
        return deductionCategorySix;
    }

    public void setDeductionCategorySix(String deductionCategorySix) {
        this.deductionCategorySix = deductionCategorySix;
    }

    public String getDeductionDollars() {
        return deductionDollars;
    }

    public void setDeductionDollars(String deductionDollars) {
        this.deductionDollars = deductionDollars;
    }

    public String getDeductionRate() {
        return deductionRate;
    }

    public void setDeductionRate(String deductionRate) {
        this.deductionRate = deductionRate;
    }

    public String getDeductionPerUnit() {
        return deductionPerUnit;
    }

    public void setDeductionPerUnit(String deductionPerUnit) {
        this.deductionPerUnit = deductionPerUnit;
    }

    public String getNetSalesDollar() {
        return netSalesDollar;
    }

    public void setNetSalesDollar(String netSalesDollar) {
        this.netSalesDollar = netSalesDollar;
    }

    public String getCogsAmount() {
        return cogsAmount;
    }

    public void setCogsAmount(String cogsAmount) {
        this.cogsAmount = cogsAmount;
    }

    public String getCogsPerUnit() {
        return cogsPerUnit;
    }

    public void setCogsPerUnit(String cogsPerUnit) {
        this.cogsPerUnit = cogsPerUnit;
    }

    public String getNetProfitDollars() {
        return netProfitDollars;
    }

    public void setNetProfitDollars(String netProfitDollars) {
        this.netProfitDollars = netProfitDollars;
    }

    public String getNetProfitPerUnit() {
        return netProfitPerUnit;
    }

    public void setNetProfitPerUnit(String netProfitPerUnit) {
        this.netProfitPerUnit = netProfitPerUnit;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public String getFinancialForecastCreationDate() {
        return financialForecastCreationDate;
    }

    public void setFinancialForecastCreationDate(String financialForecastCreationDate) {
        this.financialForecastCreationDate = financialForecastCreationDate;
    }

    public String getFinancialForecastApprovalDate() {
        return financialForecastApprovalDate;
    }

    public void setFinancialForecastApprovalDate(String financialForecastApprovalDate) {
        this.financialForecastApprovalDate = financialForecastApprovalDate;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getOutboundStatus() {
        return outboundStatus;
    }

    public void setOutboundStatus(String outboundStatus) {
        this.outboundStatus = outboundStatus;
    }

    public String getOriginalBatchId() {
        return originalBatchId;
    }

    public void setOriginalBatchId(String originalBatchId) {
        this.originalBatchId = originalBatchId;
    }

    public Integer getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(Integer companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public Integer getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(Integer itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public Integer getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(Integer contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public Integer getGlCompMasterSid() {
        return glCompMasterSid;
    }

    public void setGlCompMasterSid(Integer glCompMasterSid) {
        this.glCompMasterSid = glCompMasterSid;
    }

    public Integer getPeriodSid() {
        return periodSid;
    }

    public void setPeriodSid(Integer periodSid) {
        this.periodSid = periodSid;
    }

    public Integer getCffDetailSid() {
        return cffDetailSid;
    }

    public void setCffDetailSid(Integer cffDetailSid) {
        this.cffDetailSid = cffDetailSid;
    }

    public Date getCffCreationDateFrom() {
        return cffCreationDateFrom == null ? null : (Date) cffCreationDateFrom.clone();
    }

    public void setCffCreationDateFrom(Date cffCreationDateFrom) {
        this.cffCreationDateFrom = cffCreationDateFrom == null ? null : (Date) cffCreationDateFrom.clone();
    }

    public Date getCffCreationDateTo() {
        return cffCreationDateTo == null ? null : (Date) cffCreationDateTo.clone();
    }

    public void setCffCreationDateTo(Date cffCreationDateTo) {
        this.cffCreationDateTo = cffCreationDateTo == null ? null : (Date) cffCreationDateTo.clone();
    }

    public Date getCffApprovalDateFrom() {
        return cffApprovalDateFrom == null ? null : (Date) cffApprovalDateFrom.clone();
    }

    public void setCffApprovalDateFrom(Date cffApprovalDateFrom) {
        this.cffApprovalDateFrom = cffApprovalDateFrom == null ? null : (Date) cffApprovalDateFrom.clone();
    }

    public Date getCffApprovalDateTo() {
        return cffApprovalDateTo == null ? null : (Date) cffApprovalDateTo.clone();
    }

    public void setCffApprovalDateTo(Date cffApprovalDateTo) {
        this.cffApprovalDateTo = cffApprovalDateTo == null ? null : (Date) cffApprovalDateTo.clone();
    }

    public Integer getRsModelSid() {
        return rsModelSid;
    }

    public void setRsModelSid(Integer rsModelSid) {
        this.rsModelSid = rsModelSid;
    }
    
}
