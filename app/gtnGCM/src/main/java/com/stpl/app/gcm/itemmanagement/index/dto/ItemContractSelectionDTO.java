
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.dto;

import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 */
public class ItemContractSelectionDTO implements Cloneable {

    
    
    private Boolean checkRecord;
    private String contractHolder = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String rebateScheduleNo = StringUtils.EMPTY;
    private String rebateScheduleName = StringUtils.EMPTY;
    private String rarCategory = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private Date companyStartDate;
    private Date companyEndDate;
    private Integer contractSid;
    private String cfp = StringUtils.EMPTY;
    private HelperDTO marketType_DTO;
    private String ifp = StringUtils.EMPTY;
    private String priceSchedule = StringUtils.EMPTY;
    private String customerNo = StringUtils.EMPTY;
    private String customerName = StringUtils.EMPTY;
    private String rebateSchedule = StringUtils.EMPTY;
    private HelperDTO typeDdlb;
    private Date aliasFromDate;
    private String number = StringUtils.EMPTY;
    private Date aliasToDate;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;

    public Integer getContractSid() {
        return contractSid;
    }

    public void setContractSid(Integer contractSid) {
        this.contractSid = contractSid;
    }

    public String getCfp() {
        return cfp;
    }

    public void setCfp(String cfp) {
        this.cfp = cfp;
    }

    public HelperDTO getMarketType_DTO() {
        return marketType_DTO;
    }

    public void setMarketType_DTO(HelperDTO marketType_DTO) {
        this.marketType_DTO = marketType_DTO;
    }

    public String getIfp() {
        return ifp;
    }

    public void setIfp(String ifp) {
        this.ifp = ifp;
    }

    public String getPriceSchedule() {
        return priceSchedule;
    }

    public void setPriceSchedule(String priceSchedule) {
        this.priceSchedule = priceSchedule;
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

    public String getRebateSchedule() {
        return rebateSchedule;
    }

    public void setRebateSchedule(String rebateSchedule) {
        this.rebateSchedule = rebateSchedule;
    }

    public HelperDTO getTypeDdlb() {
        return typeDdlb;
    }

    public void setTypeDdlb(HelperDTO typeDdlb) {
        this.typeDdlb = typeDdlb;
    }

    public Date getAliasFromDate() {
        return aliasFromDate == null ? null : (Date) aliasFromDate.clone();
    }

    public void setAliasFromDate(Date aliasFromDate) {
        this.aliasFromDate = aliasFromDate == null ? null : (Date) aliasFromDate.clone();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getAliasToDate() {
        return aliasToDate == null ? null : (Date) aliasToDate.clone();
    }

    public void setAliasToDate(Date aliasToDate) {
        this.aliasToDate = aliasToDate == null ? null : (Date) aliasToDate.clone();
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
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

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    public String getRarCategory() {
        return rarCategory;
    }

    public void setRarCategory(String rarCategory) {
        this.rarCategory = rarCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompanyStartDate() {
        return companyStartDate == null ? null : (Date) companyStartDate.clone();
    }

    public void setCompanyStartDate(Date companyStartDate) {
        this.companyStartDate = companyStartDate == null ? null : (Date) companyStartDate.clone();
    }

    public Date getCompanyEndDate() {
        return companyEndDate == null ? null : (Date) companyEndDate.clone();
    }

    public void setCompanyEndDate(Date companyEndDate) {
        this.companyEndDate = companyEndDate == null ? null : (Date) companyEndDate.clone();
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

    public boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(boolean isCount) {
        this.isCount = isCount;
    }

    @Override
    public ItemContractSelectionDTO clone() throws CloneNotSupportedException {
        ItemContractSelectionDTO selection = new ItemContractSelectionDTO();
        selection.contractSid = this.contractSid;
        selection.checkRecord = BooleanConstant.getFalseFlag();
        selection.contractHolder = this.contractHolder;
        selection.contractNo = this.contractNo;
        selection.contractName = this.contractName;
        selection.marketType = this.marketType;
        selection.startDate = this.startDate;
        selection.endDate = this.endDate;
        selection.companyName = this.companyName;
        selection.companyNo = this.contractNo;
        selection.rebateScheduleNo = this.rebateScheduleNo;
        selection.rebateScheduleName = this.rebateScheduleName;
        selection.rarCategory = this.rarCategory;
        selection.status = this.status;
        selection.companyStartDate = this.companyStartDate;
        selection.companyEndDate = this.companyEndDate;
        return selection;
    }
}
