/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class TempRebateDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -3559879142770440174L;
    /**
     * The item familyplan system id.
     */
    private String itemFamilyplanSystemId;
    /**
     * The company familyplan system id.
     */
    private String companyFamilyplanSystemId;
    /**
     * The price schedule system id.
     */
    private String priceScheduleSystemId;
    /**
     * The rebate schedule system id.
     */
    private String rebateScheduleSystemId;
    /**
     * The rs details system id.
     */
    private String rsDetailsSystemId;
    /**
     * The contract system id.
     */
    private String contractSystemId;
    /**
     * Record Type
     */
    private String recordType = StringUtils.EMPTY;
    /**
     * The item no.
     */
    private String itemNo;
    /**
     * The item id.
     */
    private String itemId;
    /**
     * The item name.
     */
    private String itemName;
    /**
     * The item type.
     */
    private String itemType = StringUtils.EMPTY;
    /**
     * The item type Id.
     */
    private int itemTypeId;

    private String formulaNo = StringUtils.EMPTY;

    private String formulaMethodId = StringUtils.EMPTY;
    /**
     * The rebate plan name.
     */
    private String rebatePlanName= StringUtils.EMPTY;
    /**
     * The unique date.
     */
    private String bundleNo = StringUtils.EMPTY;
    /**
     * The rebate amount.
     */
    private String rebateAmount = StringUtils.EMPTY;
    /**
     * The revision date.
     */
    private Date rebateRevisionDate;

    /**
     * The formula id.
     */
    private String formulaId = StringUtils.EMPTY;
    /**
     * The formula name.
     */
    private String formulaName = StringUtils.EMPTY;
    /**
     * The item system id.
     */
    private String itemSystemId;
    /**
     * The start date.
     */
    private Date rebateStartDate;
    /**
     * The end date.
     */
    private Date rebateEndDate;
    /**
     * The rebate schedule type.
     */
    private String rebateScheduleType = StringUtils.EMPTY;
    /**
     * The rebate program type.
     */
    private String rebateProgramType = StringUtils.EMPTY;
    /**
     * The checkbox.
     */
    private Boolean checkbox = false;

    /**
     * The udc1.
     */
    private String udc1 = StringUtils.EMPTY;
    /**
     * The udc2.
     */
    private String udc2 = StringUtils.EMPTY;
    /**
     * The udc3.
     */
    private String udc3 = StringUtils.EMPTY;
    /**
     * The udc4.
     */
    private String udc4 = StringUtils.EMPTY;
    /**
     * The udc5.
     */
    private String udc5 = StringUtils.EMPTY;
    /**
     * The udc6.
     */
    private String udc6 = StringUtils.EMPTY;

    /**
     * The attached status.
     */
    private HelperDTO attachedStatus;
    /**
     * The start date.
     */
    private Date startDate;

    /**
     * The end date.
     */
    private Date endDate;

    /**
     * The formula type.
     */
    private String formulaType;

    private String netSalesFormulaNo = StringUtils.EMPTY;
    private String netSalesRule = StringUtils.EMPTY;
    private String evaluationRule = StringUtils.EMPTY;
    private String evaluationRuleBundle = StringUtils.EMPTY;
    private String calculationRule = StringUtils.EMPTY;
    private String calculationRuleBundle = StringUtils.EMPTY;
    private String rebatePlanNo = StringUtils.EMPTY;
    private String deductionCalendarNo = StringUtils.EMPTY;        
    private String deductionCalendarName = StringUtils.EMPTY;
    private String deductionSystemId = StringUtils.EMPTY;
    private int tempRSDetailsSystemId;
    /**
     * The userID.
     */
    private String userID = StringUtils.EMPTY;
    /**
     * The sessionID.
     */
    private String sessionID = StringUtils.EMPTY;
    /** The ifp system id. */
    private String ifpSystemId;
    /** The revision date. */
    private Date revisionDate;
    /** The attached date. */
    private Date attachedDate;
    private String systemID = StringUtils.EMPTY;
    private String netSalesSystemId=StringUtils.EMPTY;
    private String calculationSystemId=StringUtils.EMPTY;
    private String evaluationSystemId=StringUtils.EMPTY;
    private String netSalesFormulaName = StringUtils.EMPTY;
    /** The rebate plan name. */
    private String rebatePlanSystemId ;
    /** The formula no. */
    private String formulaSystemId = StringUtils.EMPTY;

    public String getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    public void setItemFamilyplanSystemId(String itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    public String getCompanyFamilyplanSystemId() {
        return companyFamilyplanSystemId;
    }

    public void setCompanyFamilyplanSystemId(String companyFamilyplanSystemId) {
        this.companyFamilyplanSystemId = companyFamilyplanSystemId;
    }

    public String getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    public void setPriceScheduleSystemId(String priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    public String getRebateScheduleSystemId() {
        return rebateScheduleSystemId;
    }

    public void setRebateScheduleSystemId(String rebateScheduleSystemId) {
        this.rebateScheduleSystemId = rebateScheduleSystemId;
    }

    public String getRsDetailsSystemId() {
        return rsDetailsSystemId;
    }

    public void setRsDetailsSystemId(String rsDetailsSystemId) {
        this.rsDetailsSystemId = rsDetailsSystemId;
    }

    public String getContractSystemId() {
        return contractSystemId;
    }

    public void setContractSystemId(String contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(String rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public Date getRebateRevisionDate() {
        return rebateRevisionDate;
    }

    public void setRebateRevisionDate(Date rebateRevisionDate) {
        this.rebateRevisionDate = rebateRevisionDate;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getItemSystemId() {
        return itemSystemId;
    }

    public void setItemSystemId(String itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    public Date getRebateStartDate() {
        return rebateStartDate;
    }

    public void setRebateStartDate(Date rebateStartDate) {
        this.rebateStartDate = rebateStartDate;
    }

    public Date getRebateEndDate() {
        return rebateEndDate;
    }

    public void setRebateEndDate(Date rebateEndDate) {
        this.rebateEndDate = rebateEndDate;
    }

    public String getRebateScheduleType() {
        return rebateScheduleType;
    }

    public void setRebateScheduleType(String rebateScheduleType) {
        this.rebateScheduleType = rebateScheduleType;
    }

    public String getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(String rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getUdc1() {
        return udc1;
    }

    public void setUdc1(String udc1) {
        this.udc1 = udc1;
    }

    public String getUdc2() {
        return udc2;
    }

    public void setUdc2(String udc2) {
        this.udc2 = udc2;
    }

    public String getUdc3() {
        return udc3;
    }

    public void setUdc3(String udc3) {
        this.udc3 = udc3;
    }

    public String getUdc4() {
        return udc4;
    }

    public void setUdc4(String udc4) {
        this.udc4 = udc4;
    }

    public String getUdc5() {
        return udc5;
    }

    public void setUdc5(String udc5) {
        this.udc5 = udc5;
    }

    public String getUdc6() {
        return udc6;
    }

    public void setUdc6(String udc6) {
        this.udc6 = udc6;
    }

    public String getTempItemPriceRebateSystemId() {
        return tempItemPriceRebateSystemId;
    }

    public void setTempItemPriceRebateSystemId(String tempItemPriceRebateSystemId) {
        this.tempItemPriceRebateSystemId = tempItemPriceRebateSystemId;
    }

    public String getRebatePlanName() {
        return rebatePlanName;
    }

    public void setRebatePlanName(String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    public String getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }

    private String tempItemPriceRebateSystemId;

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }

    public String getFormulaMethodId() {
        return formulaMethodId;
    }

    public void setFormulaMethodId(String formulaMethodId) {
        this.formulaMethodId = formulaMethodId;
    }

    public HelperDTO getAttachedStatus() {
        return attachedStatus;
    }

    public void setAttachedStatus(HelperDTO attachedStatus) {
        this.attachedStatus = attachedStatus;
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

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public String getNetSalesFormulaNo() {
        return netSalesFormulaNo;
    }

    public void setNetSalesFormulaNo(String netSalesFormulaNo) {
        this.netSalesFormulaNo = netSalesFormulaNo;
    }

    public String getNetSalesRule() {
        return netSalesRule;
    }

    public void setNetSalesRule(String netSalesRule) {
        this.netSalesRule = netSalesRule;
    }

    public String getEvaluationRule() {
        return evaluationRule;
    }

    public void setEvaluationRule(String evaluationRule) {
        this.evaluationRule = evaluationRule;
    }

    public String getEvaluationRuleBundle() {
        return evaluationRuleBundle;
    }

    public void setEvaluationRuleBundle(String evaluationRuleBundle) {
        this.evaluationRuleBundle = evaluationRuleBundle;
    }

    public String getCalculationRule() {
        return calculationRule;
    }

    public void setCalculationRule(String calculationRule) {
        this.calculationRule = calculationRule;
    }

    public String getCalculationRuleBundle() {
        return calculationRuleBundle;
    }

    public void setCalculationRuleBundle(String calculationRuleBundle) {
        this.calculationRuleBundle = calculationRuleBundle;
    }

    public String getRebatePlanNo() {
        return rebatePlanNo;
    }

    public void setRebatePlanNo(String rebatePlanNo) {
        this.rebatePlanNo = rebatePlanNo;
    }

    public String getDeductionCalendarNo() {
        return deductionCalendarNo;
    }

    public void setDeductionCalendarNo(String deductionCalendarNo) {
        this.deductionCalendarNo = deductionCalendarNo;
    }

    public String getDeductionCalendarName() {
        return deductionCalendarName;
    }

    public void setDeductionCalendarName(String deductionCalendarName) {
        this.deductionCalendarName = deductionCalendarName;
    }

    public String getDeductionSystemId() {
        return deductionSystemId;
    }

    public void setDeductionSystemId(String deductionSystemId) {
        this.deductionSystemId = deductionSystemId;
    }

    public int getTempRSDetailsSystemId() {
        return tempRSDetailsSystemId;
    }

    public void setTempRSDetailsSystemId(int tempRSDetailsSystemId) {
        this.tempRSDetailsSystemId = tempRSDetailsSystemId;
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

    public String getIfpSystemId() {
        return ifpSystemId;
    }

    public void setIfpSystemId(String ifpSystemId) {
        this.ifpSystemId = ifpSystemId;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Date getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getNetSalesSystemId() {
        return netSalesSystemId;
    }

    public void setNetSalesSystemId(String netSalesSystemId) {
        this.netSalesSystemId = netSalesSystemId;
    }

    public String getCalculationSystemId() {
        return calculationSystemId;
    }

    public void setCalculationSystemId(String calculationSystemId) {
        this.calculationSystemId = calculationSystemId;
    }

    public String getEvaluationSystemId() {
        return evaluationSystemId;
    }

    public void setEvaluationSystemId(String evaluationSystemId) {
        this.evaluationSystemId = evaluationSystemId;
    }

    public String getNetSalesFormulaName() {
        return netSalesFormulaName;
    }

    public void setNetSalesFormulaName(String netSalesFormulaName) {
        this.netSalesFormulaName = netSalesFormulaName;
    }

    public String getRebatePlanSystemId() {
        return rebatePlanSystemId;
    }

    public void setRebatePlanSystemId(String rebatePlanSystemId) {
        this.rebatePlanSystemId = rebatePlanSystemId;
    }

    public String getFormulaSystemId() {
        return formulaSystemId;
    }

    public void setFormulaSystemId(String formulaSystemId) {
        this.formulaSystemId = formulaSystemId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    
}