/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author karthikraja.k
 */
public class SalesBasisDto implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -9084529327026132883L;
    private String formulaNo = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;
    private String formulaName = StringUtils.EMPTY;
    private String formulaCategory;
    private String contractNo = StringUtils.EMPTY;
    private String contractHolder = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String companyFamilyPlanNo = StringUtils.EMPTY;
    private String companyFamilyPlanName = StringUtils.EMPTY;
    private String itemFamilyPlanName = StringUtils.EMPTY;
    private String itemFamilyPlanNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private HelperDTO marketType;
    private String netSalesRule = StringUtils.EMPTY;
    private String deductionName = StringUtils.EMPTY;
    private String deductionNo = StringUtils.EMPTY;
    private String priceScheduleName = StringUtils.EMPTY;
    private String priceScheduleNo = StringUtils.EMPTY;
    private String netSalesRuleName = StringUtils.EMPTY;
    private String netSalesRuleNo = StringUtils.EMPTY;
    private String contractMasterSid = StringUtils.EMPTY;
    private boolean availableCheck;
    private String inboundStatus;
    private String crdModelSid = StringUtils.EMPTY;
    private int imtdSalesBasisDetailsSid;
    private String cfpContractDetailsSid = StringUtils.EMPTY;
    private HelperDTO ruleType;
    private HelperDTO ruleCategory;
    
    /**
     * The userID.
     */
    private String userID = StringUtils.EMPTY;
    /**
     * The sessionID.
     */
    private String sessionID = StringUtils.EMPTY;
     /**
     * The operationFlag.
     */
    private String operation;

    /**
     * The modifiedDate.
     */
    private Date modifiedDate;
    
     /**
     * The createdDate.
     */
    private Date createdDate;
    
    /**
     * The modifiedBy.
     */
    private String modifiedBy = StringUtils.EMPTY;
    
    /**
     * The modifiedBy.
     */
    private String createdBy = StringUtils.EMPTY;
    
    public String getNetSalesRuleName() {
        return netSalesRuleName;
    }

    public void setNetSalesRuleName(String netSalesRuleName) {
        this.netSalesRuleName = netSalesRuleName;
    }

    public String getNetSalesRuleNo() {
        return netSalesRuleNo;
    }

    public void setNetSalesRuleNo(String netSalesRuleNo) {
        this.netSalesRuleNo = netSalesRuleNo;
    }

    public String getPriceScheduleName() {
        return priceScheduleName;
    }

    public void setPriceScheduleName(String priceScheduleName) {
        this.priceScheduleName = priceScheduleName;
    }

    public String getPriceScheduleNo() {
        return priceScheduleNo;
    }

    public void setPriceScheduleNo(String priceScheduleNo) {
        this.priceScheduleNo = priceScheduleNo;
    }

    public String getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormulaCategory() {
        return formulaCategory;
    }

    public void setFormulaCategory(String formulaCategory) {
        this.formulaCategory = formulaCategory;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getCompanyFamilyPlanNo() {
        return companyFamilyPlanNo;
    }

    public void setCompanyFamilyPlanNo(String companyFamilyPlanNo) {
        this.companyFamilyPlanNo = companyFamilyPlanNo;
    }

    public String getCompanyFamilyPlanName() {
        return companyFamilyPlanName;
    }

    public void setCompanyFamilyPlanName(String companyFamilyPlanName) {
        this.companyFamilyPlanName = companyFamilyPlanName;
    }

    public String getItemFamilyPlanName() {
        return itemFamilyPlanName;
    }

    public void setItemFamilyPlanName(String itemFamilyPlanName) {
        this.itemFamilyPlanName = itemFamilyPlanName;
    }

    public String getItemFamilyPlanNo() {
        return itemFamilyPlanNo;
    }

    public void setItemFamilyPlanNo(String itemFamilyPlanNo) {
        this.itemFamilyPlanNo = itemFamilyPlanNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public HelperDTO getMarketType() {
        return marketType;
    }

    public void setMarketType(HelperDTO marketType) {
        this.marketType = marketType;
    }

    public String getNetSalesRule() {
        return netSalesRule;
    }

    public void setNetSalesRule(String netSalesRule) {
        this.netSalesRule = netSalesRule;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public String getDeductionNo() {
        return deductionNo;
    }

    public void setDeductionNo(String deductionNo) {
        this.deductionNo = deductionNo;
    }

    public boolean isAvailableCheck() {
        return availableCheck;
    }

    public void setAvailableCheck(boolean availableCheck) {
        this.availableCheck = availableCheck;
    }

    public String getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(String contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public String getInboundStatus() {
        return inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCrdModelSid() {
        return crdModelSid;
    }

    public void setCrdModelSid(String crdModelSid) {
        this.crdModelSid = crdModelSid;
    }

    public int getImtdSalesBasisDetailsSid() {
        return imtdSalesBasisDetailsSid;
    }

    public void setImtdSalesBasisDetailsSid(int imtdSalesBasisDetailsSid) {
        this.imtdSalesBasisDetailsSid = imtdSalesBasisDetailsSid;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCfpContractDetailsSid() {
        return cfpContractDetailsSid;
    }

    public void setCfpContractDetailsSid(String cfpContractDetailsSid) {
        this.cfpContractDetailsSid = cfpContractDetailsSid;
    }

    public HelperDTO getRuleType() {
        return ruleType;
    }

    public void setRuleType(HelperDTO ruleType) {
        this.ruleType = ruleType;
    }

    public HelperDTO getRuleCategory() {
        return ruleCategory;
    }

    public void setRuleCategory(HelperDTO ruleCategory) {
        this.ruleCategory = ruleCategory;
    }
    
}
