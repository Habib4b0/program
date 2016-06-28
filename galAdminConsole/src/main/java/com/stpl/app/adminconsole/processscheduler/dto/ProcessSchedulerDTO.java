/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.dto;

import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
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
    private Date creationFromDate;
    private Date creationToDate;
    private Date approvalFromDate;
    private Date approvalToDate;
    private Date finalApprovalDate;
    private String approvedBy = StringUtils.EMPTY;
    private Date activeFromDate;
    private Date activeToDate;
    private Integer cffMasterSid = 0;
    private HelperDTO typeDdlb;
    private HelperDTO statusDdlb;
    private String typeDesc = StringUtils.EMPTY;
    private String statusDesc = StringUtils.EMPTY;
    private int excelId = 0;
    private int year = 0;
    private int month = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean count = false;
    private List<SortByColumn> orderByColumns = new ArrayList<SortByColumn>();
    ;
    private Set<Container.Filter> filters;

    private String customer = StringUtils.EMPTY;
    private String item = StringUtils.EMPTY;
    private String contract = StringUtils.EMPTY;
    private String salesDollars = StringUtils.EMPTY;
    private String salesUnits = StringUtils.EMPTY;
    private String rebateDollars = StringUtils.EMPTY;
    private String rebateRate = StringUtils.EMPTY;
    private String rebatePerUnit = StringUtils.EMPTY;
    private String netSalesDollars = StringUtils.EMPTY;
    private String costOfGoodsSoldDollars = StringUtils.EMPTY;
    private String costOfGoodsSoldUnits = StringUtils.EMPTY;
    private String netProfutDollars = StringUtils.EMPTY;
    private String netProfitUnits = StringUtils.EMPTY;
    private String time = StringUtils.EMPTY;
    private String cffType = StringUtils.EMPTY;
    private String projectionName = StringUtils.EMPTY;
    private String rsId = StringUtils.EMPTY;
    private String scriptName = StringUtils.EMPTY;
    private boolean checkRecord;

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }


    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }
    
    
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getCffType() {
        return cffType;
    }

    public void setCffType(String cffType) {
        this.cffType = cffType;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
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

    public Date getCreationFromDate() {
        return creationFromDate;
    }

    public void setCreationFromDate(Date creationFromDate) {
        this.creationFromDate = creationFromDate;
    }

    public Date getCreationToDate() {
        return creationToDate;
    }

    public void setCreationToDate(Date creationToDate) {
        this.creationToDate = creationToDate;
    }

    public Date getApprovalFromDate() {
        return approvalFromDate;
    }

    public void setApprovalFromDate(Date approvalFromDate) {
        this.approvalFromDate = approvalFromDate;
    }

    public Date getApprovalToDate() {
        return approvalToDate;
    }

    public void setApprovalToDate(Date approvalToDate) {
        this.approvalToDate = approvalToDate;
    }

    public Date getFinalApprovalDate() {
        return finalApprovalDate;
    }

    public void setFinalApprovalDate(Date finalApprovalDate) {
        this.finalApprovalDate = finalApprovalDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getActiveFromDate() {
        return activeFromDate;
    }

    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
    }

    public Date getActiveToDate() {
        return activeToDate;
    }

    public void setActiveToDate(Date activeToDate) {
        this.activeToDate = activeToDate;
    }

    public Integer getCffMasterSid() {
        return cffMasterSid;
    }

    public void setCffMasterSid(Integer cffMasterSid) {
        this.cffMasterSid = cffMasterSid;
    }

    public HelperDTO getTypeDdlb() {
        return typeDdlb;
    }

    public void setTypeDdlb(HelperDTO typeDdlb) {
        this.typeDdlb = typeDdlb;
    }

    public HelperDTO getStatusDdlb() {
        return statusDdlb;
    }

    public void setStatusDdlb(HelperDTO statusDdlb) {
        this.statusDdlb = statusDdlb;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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
        return orderByColumns;
    }

    public void setOrderByColumns(List<SortByColumn> orderByColumns) {
        this.orderByColumns = orderByColumns;
    }

    public Set<Container.Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
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

    public String getRebateDollars() {
        return rebateDollars;
    }

    public void setRebateDollars(String rebateDollars) {
        this.rebateDollars = rebateDollars;
    }

    public String getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(String rebateRate) {
        this.rebateRate = rebateRate;
    }

    public String getRebatePerUnit() {
        return rebatePerUnit;
    }

    public void setRebatePerUnit(String rebatePerUnit) {
        this.rebatePerUnit = rebatePerUnit;
    }

    public String getNetSalesDollars() {
        return netSalesDollars;
    }

    public void setNetSalesDollars(String netSalesDollars) {
        this.netSalesDollars = netSalesDollars;
    }

    public String getCostOfGoodsSoldDollars() {
        return costOfGoodsSoldDollars;
    }

    public void setCostOfGoodsSoldDollars(String costOfGoodsSoldDollars) {
        this.costOfGoodsSoldDollars = costOfGoodsSoldDollars;
    }

    public String getCostOfGoodsSoldUnits() {
        return costOfGoodsSoldUnits;
    }

    public void setCostOfGoodsSoldUnits(String costOfGoodsSoldUnits) {
        this.costOfGoodsSoldUnits = costOfGoodsSoldUnits;
    }

    public String getNetProfutDollars() {
        return netProfutDollars;
    }

    public void setNetProfutDollars(String netProfutDollars) {
        this.netProfutDollars = netProfutDollars;
    }

    public String getNetProfitUnits() {
        return netProfitUnits;
    }

    public void setNetProfitUnits(String netProfitUnits) {
        this.netProfitUnits = netProfitUnits;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFrequencyRadio() {
        return frequencyRadio;
    }

    public void setFrequencyRadio(String frequencyRadio) {
        this.frequencyRadio = frequencyRadio;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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

}
