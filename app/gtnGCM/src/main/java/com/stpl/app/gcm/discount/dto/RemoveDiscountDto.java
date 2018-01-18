/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dto;

import com.stpl.app.gcm.util.Constants;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vigneshkanna
 */
public class RemoveDiscountDto {

    private boolean checkRecord = false;
    private String contractHolder = StringUtils.EMPTY;
    private String contractId = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String marketType = "-Select One-";
    private Date startDate;
    private Date endDate ;
    private String cfpName = StringUtils.EMPTY;
    private String ifpName = StringUtils.EMPTY;
    private String psName = StringUtils.EMPTY;
    private String rsName = StringUtils.EMPTY;
    private String customer = StringUtils.EMPTY;
    private String aliastype = Constants.SELECT_ONE;
    private String aliasnumber = StringUtils.EMPTY;
    private Date aliasStartDate;
    private Date aliasEndDate;

    private String contractStatus = StringUtils.EMPTY;
    private Date contractendDate;
    private Date contractstartDate;
    private String frequency = StringUtils.EMPTY;
    private String rarType = StringUtils.EMPTY;
    private String basis = StringUtils.EMPTY;

    private String category = StringUtils.EMPTY;

    private String itemNo = StringUtils.EMPTY;
    private String item = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String therapyClass = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    private String itemStartDate = StringUtils.EMPTY;
    private String itemEndDate = StringUtils.EMPTY;
    private String rebatePlan = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;

    private int contractSid = 0;
    private int cfpSid = 0;
    private int ifpSid = 0;
    private int psSid = 0;
    private int rsSid = 0;
    private int companySid = 0;
    private int itemSid = 0;
    private Integer count = 0;
    private Integer startIndex = 0;
    private Integer offSet = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;

    private int projectionSid = 0;
    private String forecastingType = StringUtils.EMPTY;
    private Date fromDate;
    private Date toDate;
    private int parent = 0;
    private String levelValue = StringUtils.EMPTY;
    private int levelNo = 0;
    private String rsId = StringUtils.EMPTY;
    private String rsNo = StringUtils.EMPTY;
    private String rsStatus = StringUtils.EMPTY;
    private String rsStartDate = StringUtils.EMPTY;
    private String rsEndDate = StringUtils.EMPTY;
    private String rebateFrequency = StringUtils.EMPTY;
    private String rsContractSid;
    private String formulaName = StringUtils.EMPTY;
    private String formulaType = StringUtils.EMPTY;
    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String rebateAmount = StringUtils.EMPTY;
    private String bundleNo = StringUtils.EMPTY;
    private String attachedDate = StringUtils.EMPTY;
    private String programType = StringUtils.EMPTY;
    private String rsType = StringUtils.EMPTY;
    private String rsCategory = StringUtils.EMPTY;
    private String paymentFrequency = StringUtils.EMPTY;
    private String rebatePlanLevel = StringUtils.EMPTY;
    private Integer sessionId;
    private Integer userId;
   private boolean search;

    public String getAliastype() {
        return aliastype;
    }

    public void setAliastype(String aliastype) {
        this.aliastype = aliastype;
    }

    public String getAliasnumber() {
        return aliasnumber;
    }

    public void setAliasnumber(String aliasnumber) {
        this.aliasnumber = aliasnumber;
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

    public boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
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

    public String getCfpName() {
        return cfpName;
    }

    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Date getContractendDate() {
        return contractendDate == null ? null : (Date) contractendDate.clone();
    }

    public void setContractendDate(Date contractendDate) {
        this.contractendDate = contractendDate == null ? null : (Date) contractendDate.clone();
    }

    public Date getContractstartDate() {
        return contractstartDate == null ? null : (Date) contractstartDate.clone();
    }

    public void setContractstartDate(Date contractstartDate) {
        this.contractstartDate = contractstartDate == null ? null : (Date) contractstartDate.clone();
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRarType() {
        return rarType;
    }

    public void setRarType(String rarType) {
        this.rarType = rarType;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getTherapyClass() {
        return therapyClass;
    }

    public void setTherapyClass(String therapyClass) {
        this.therapyClass = therapyClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(String itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public String getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(String itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    public String getRebatePlan() {
        return rebatePlan;
    }

    public void setRebatePlan(String rebatePlan) {
        this.rebatePlan = rebatePlan;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getContractSid() {
        return contractSid;
    }

    public void setContractSid(int contractSid) {
        this.contractSid = contractSid;
    }

    public int getCfpSid() {
        return cfpSid;
    }

    public void setCfpSid(int cfpSid) {
        this.cfpSid = cfpSid;
    }

    public int getIfpSid() {
        return ifpSid;
    }

    public void setIfpSid(int ifpSid) {
        this.ifpSid = ifpSid;
    }

    public int getPsSid() {
        return psSid;
    }

    public void setPsSid(int psSid) {
        this.psSid = psSid;
    }

    public int getRsSid() {
        return rsSid;
    }

    public void setRsSid(int rsSid) {
        this.rsSid = rsSid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCompanySid() {
        return companySid;
    }

    public void setCompanySid(int companySid) {
        this.companySid = companySid;
    }

    public int getItemSid() {
        return itemSid;
    }

    public void setItemSid(int itemSid) {
        this.itemSid = itemSid;
    }

    public int getProjectionSid() {
        return projectionSid;
    }

    public void setProjectionSid(int projectionSid) {
        this.projectionSid = projectionSid;
    }

    public String getForecastingType() {
        return forecastingType;
    }

    public void setForecastingType(String forecastingType) {
        this.forecastingType = forecastingType;
    }

    public Date getFromDate() {
        return fromDate == null ? null : (Date) fromDate.clone();
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate == null ? null : (Date) fromDate.clone();
    }

    public Date getToDate() {
        return toDate == null ? null : (Date) toDate.clone();
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate == null ? null : (Date) toDate.clone();
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public String getRsNo() {
        return rsNo;
    }

    public void setRsNo(String rsNo) {
        this.rsNo = rsNo;
    }

    public String getRsStatus() {
        return rsStatus;
    }

    public void setRsStatus(String rsStatus) {
        this.rsStatus = rsStatus;
    }

    public String getRsStartDate() {
        return rsStartDate;
    }

    public void setRsStartDate(String rsStartDate) {
        this.rsStartDate = rsStartDate;
    }

    public String getRsEndDate() {
        return rsEndDate;
    }

    public void setRsEndDate(String rsEndDate) {
        this.rsEndDate = rsEndDate;
    }

    public String getRebateFrequency() {
        return rebateFrequency;
    }

    public void setRebateFrequency(String rebateFrequency) {
        this.rebateFrequency = rebateFrequency;
    }

    public String getRsContractSid() {
        return rsContractSid;
    }

    public void setRsContractSid(String rsContractSid) {
        this.rsContractSid = rsContractSid;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public String getRebatePlanId() {
        return rebatePlanId;
    }

    public void setRebatePlanId(String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }

    public String getRebatePlanName() {
        return rebatePlanName;
    }

    public void setRebatePlanName(String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    public String getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(String rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public String getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }

    public String getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(String attachedDate) {
        this.attachedDate = attachedDate;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public String getRsCategory() {
        return rsCategory;
    }

    public void setRsCategory(String rsCategory) {
        this.rsCategory = rsCategory;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public String getRebatePlanLevel() {
        return rebatePlanLevel;
    }

    public void setRebatePlanLevel(String rebatePlanLevel) {
        this.rebatePlanLevel = rebatePlanLevel;
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

     public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }
    

}
