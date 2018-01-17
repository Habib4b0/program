/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Manasa
 */
public class CFFSearchDTO {

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
    private Integer createdUser = 0;
    private HelperDTO typeDdlb;
    private HelperDTO statusDdlb;
    private String typeDesc = StringUtils.EMPTY;
    private String statusDesc = StringUtils.EMPTY;
    private int excelId = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean count = false;
    private List<SortByColumn> orderByColumns = new ArrayList<>();
    private Set<Container.Filter> filters;
    
    private String activeFromDateExcel;
    private String activeToDateExcel;
    private String finalApprovalDateExcel;

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

    public Date getFinalApprovalDate() {
        return finalApprovalDate == null ? null : (Date) finalApprovalDate.clone();
    }

    public void setFinalApprovalDate(Date finalApprovalDate) {
        this.finalApprovalDate = finalApprovalDate == null ? null : (Date) finalApprovalDate.clone();
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getActiveFromDate() {
        return activeFromDate == null ? null : (Date) activeFromDate.clone();
    }

    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate == null ? null : (Date) activeFromDate.clone();
    }

    public Date getActiveToDate() {
        return activeToDate == null ? null : (Date) activeToDate.clone();
    }

    public void setActiveToDate(Date activeToDate) {
        this.activeToDate = activeToDate == null ? null : (Date) activeToDate.clone();
    }

    public Date getCreationToDate() {
        return creationToDate == null ? null : (Date) creationToDate.clone();
    }

    public void setCreationToDate(Date creationToDate) {
        this.creationToDate = creationToDate == null ? null : (Date) creationToDate.clone();
    }

    public Date getApprovalFromDate() {
        return approvalFromDate == null ? null : (Date) approvalFromDate.clone();
    }

    public void setApprovalFromDate(Date approvalFromDate) {
        this.approvalFromDate = approvalFromDate == null ? null : (Date) approvalFromDate.clone();
    }

    public Date getApprovalToDate() {
        return approvalToDate == null ? null : (Date) approvalToDate.clone();
    }

    public void setApprovalToDate(Date approvalToDate) {
        this.approvalToDate = approvalToDate == null ? null : (Date) approvalToDate.clone();
    }

    public Integer getCffMasterSid() {
        return cffMasterSid;
    }

    public void setCffMasterSid(Integer cffMasterSid) {
        this.cffMasterSid = cffMasterSid;
    }

    public Date getCreationFromDate() {
        return creationFromDate == null ? null : (Date) creationFromDate.clone();
    }

    public void setCreationFromDate(Date creationFromDate) {
        this.creationFromDate = creationFromDate == null ? null : (Date) creationFromDate.clone();
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

    public Integer getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Integer createdUser) {
        this.createdUser = createdUser;
    }

    public String getActiveFromDateExcel() {
        return activeFromDateExcel;
    }

    public void setActiveFromDateExcel(String activeFromDateExcel) {
        this.activeFromDateExcel = activeFromDateExcel;
    }

    public String getActiveToDateExcel() {
        return activeToDateExcel;
    }

    public void setActiveToDateExcel(String activeToDateExcel) {
        this.activeToDateExcel = activeToDateExcel;
    }

    public String getFinalApprovalDateExcel() {
        return finalApprovalDateExcel;
    }

    public void setFinalApprovalDateExcel(String finalApprovalDateExcel) {
        this.finalApprovalDateExcel = finalApprovalDateExcel;
    }
    
    
    
    }
