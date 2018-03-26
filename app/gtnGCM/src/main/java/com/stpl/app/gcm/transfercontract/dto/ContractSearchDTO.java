/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.dto;

import com.stpl.app.gcm.transfercontract.util.Constant;
import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Harlin
 */
public class ContractSearchDTO {

    private String contractHolder = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String customerSid = StringUtils.EMPTY;
    private String customerNo = StringUtils.EMPTY;
    private String customerName = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private String marketTypeValue = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private String itemSid = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String cfpContractSid = StringUtils.EMPTY;
    private String companyFamilyPlan = StringUtils.EMPTY;
    private String ifpContractSid = StringUtils.EMPTY;
    private String itemFamilyPlan = StringUtils.EMPTY;
    private String psContractSid = StringUtils.EMPTY;
    private String priceSchedule = StringUtils.EMPTY;
    private String rsContractSid = StringUtils.EMPTY;
    private String rebateSchedule = StringUtils.EMPTY;
    private Boolean check = Boolean.FALSE;
    private HelperDTO aliasType = Constant.HELPER_DTO;
    private String aliasNumber = StringUtils.EMPTY;
    private Date aliasStartDate;
    private Date aliasEndDate;
    private String hiddenId = StringUtils.EMPTY;
    private String aliastypecc = StringUtils.EMPTY;
    private Integer sessionId;
    private Integer userId;

    public ContractSearchDTO() {
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

   

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getCustomerSid() {
        return customerSid;
    }

    public void setCustomerSid(String customerSid) {
        this.customerSid = customerSid;
    }

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public String getCfpContractSid() {
        return cfpContractSid;
    }

    public void setCfpContractSid(String cfpContractSid) {
        this.cfpContractSid = cfpContractSid;
    }

    public String getIfpContractSid() {
        return ifpContractSid;
    }

    public void setIfpContractSid(String ifpContractSid) {
        this.ifpContractSid = ifpContractSid;
    }

    public String getPsContractSid() {
        return psContractSid;
    }

    public void setPsContractSid(String psContractSid) {
        this.psContractSid = psContractSid;
    }

    public String getRsContractSid() {
        return rsContractSid;
    }

    public void setRsContractSid(String rsContractSid) {
        this.rsContractSid = rsContractSid;
    }

    public String getMarketTypeValue() {
        return marketTypeValue;
    }

    public void setMarketTypeValue(String marketTypeValue) {
        this.marketTypeValue = marketTypeValue;
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

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
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


    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
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

    public String getCompanyFamilyPlan() {
        return companyFamilyPlan;
    }

    public void setCompanyFamilyPlan(String companyFamilyPlan) {
        this.companyFamilyPlan = companyFamilyPlan;
    }

    public String getItemFamilyPlan() {
        return itemFamilyPlan;
    }

    public void setItemFamilyPlan(String itemFamilyPlan) {
        this.itemFamilyPlan = itemFamilyPlan;
    }

    public String getPriceSchedule() {
        return priceSchedule;
    }

    public void setPriceSchedule(String priceSchedule) {
        this.priceSchedule = priceSchedule;
    }

    public String getRebateSchedule() {
        return rebateSchedule;
    }

    public void setRebateSchedule(String rebateSchedule) {
        this.rebateSchedule = rebateSchedule;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public HelperDTO getAliasType() {
        return aliasType;
    }

    public void setAliasType(HelperDTO aliasType) {
        this.aliasType = aliasType;
    }

    public String getAliasNumber() {
        return aliasNumber;
    }

    public void setAliasNumber(String aliasNumber) {
        this.aliasNumber = aliasNumber;
    }

    public Date getAliasStartDate() {
        return aliasStartDate == null ? null : (Date) aliasStartDate.clone();
    }

    public void setAliasStartDate(Date aliasStartDate) {
        this.aliasStartDate = aliasStartDate == null ? null : (Date) aliasStartDate.clone();
    }

    public Date getAliasEndDate() {
        return aliasEndDate == null ? null : (Date) aliasEndDate.clone();
    }

    public void setAliasEndDate(Date aliasEndDate) {
        this.aliasEndDate = aliasEndDate == null ? null : (Date) aliasEndDate.clone();
    }

    public String getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(String hiddenId) {
        this.hiddenId = hiddenId;
    }

    public String getAliastypecc() {
        return aliastypecc;
    }

    public void setAliastypecc(String aliastypecc) {
        this.aliastypecc = aliastypecc;
    }

}
