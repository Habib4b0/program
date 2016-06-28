/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author karthikraja.k
 */
public class DeductionDto {
    private String formulaNo=StringUtils.EMPTY;
    private String formulaId=StringUtils.EMPTY;
    private String formulaName=StringUtils.EMPTY;
    private String formulaCategory;
    private HelperDTO formulaType;
    private String contractNo=StringUtils.EMPTY;
    private String contractHolder=StringUtils.EMPTY;
    private String contractName=StringUtils.EMPTY;
    private String companyFamilyPlanNo=StringUtils.EMPTY;
    private String companyFamilyPlanName=StringUtils.EMPTY;
    private String itemFamilyPlanName=StringUtils.EMPTY;
    private String itemFamilyPlanNo=StringUtils.EMPTY;
    private String itemName=StringUtils.EMPTY;
    private String itemNo=StringUtils.EMPTY;
    private String companyName=StringUtils.EMPTY;
    private String companyNo=StringUtils.EMPTY;
    private HelperDTO  marketType;
    private String netSalesRule=StringUtils.EMPTY;
    private String deductionName=StringUtils.EMPTY;
    private String deductionNo=StringUtils.EMPTY;
    private String priceScheduleName=StringUtils.EMPTY;
    private String priceScheduleNo=StringUtils.EMPTY;
    private String netSalesRuleName=StringUtils.EMPTY;
    private String netSalesRuleNo=StringUtils.EMPTY;
    private HelperDTO  deductionType;
    private HelperDTO deductionSubType;
    private HelperDTO deductionCategory;
    private Date startDate;
    private Date endDate;
    private String indicator;
    private String ruleName = " ";
    private String deductionAlias = StringUtils.EMPTY;
    private HelperDTO netSalesRuleType;
    private HelperDTO netSalesRuleCategory;
    private int netSalesRuleSystemId = 0;
    private int contractMasterSId = 0;
    boolean selectedCheck = false;
    int tempItemSystemId;
    int rsContractSid;
    private String startDateString;
    private String endDateString;
    private String marketTypeTable=StringUtils.EMPTY;
    private String deductionTypeTable=StringUtils.EMPTY;
    private String deductionSubTypeTable=StringUtils.EMPTY;
    private String deductionCategoryTable=StringUtils.EMPTY;
    
    public HelperDTO  getNetSalesRuleType() {
        return netSalesRuleType;
    }

    public void setNetSalesRuleType(HelperDTO  netSalesRuleType) {
        this.netSalesRuleType = netSalesRuleType;
    }

    public com.stpl.ifs.util.HelperDTO  getNetSalesRuleCategory() {
        return netSalesRuleCategory;
    }

    public void setNetSalesRuleCategory(HelperDTO  netSalesRuleCategory) {
        this.netSalesRuleCategory = netSalesRuleCategory;
    }
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }
           
    public HelperDTO getMarketType() {
        return marketType;
    }

    public void setMarketType(HelperDTO marketType) {
        this.marketType = marketType;
    }

    public HelperDTO getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(HelperDTO deductionType) {
        this.deductionType = deductionType;
    }

    public HelperDTO getDeductionSubType() {
        return deductionSubType;
    }

    public void setDeductionSubType(HelperDTO deductionSubType) {
        this.deductionSubType = deductionSubType;
    }

    public HelperDTO getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(HelperDTO deductionCategory) {
        this.deductionCategory = deductionCategory;
    }
    
  
    public HelperDTO getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(HelperDTO formulaType) {
        this.formulaType = formulaType;
    }

    public String getDeductionAlias() {
        return deductionAlias;
    }

    public void setDeductionAlias(String deductionAlias) {
        this.deductionAlias = deductionAlias;
    }

    public int getContractMasterSId() {
        return contractMasterSId;
    }

    public void setContractMasterSId(int contractMasterSId) {
        this.contractMasterSId = contractMasterSId;
    }

    public boolean getSelectedCheck() {
        return selectedCheck;
    }

    public void setSelectedCheck(boolean selectedCheck) {
        this.selectedCheck = selectedCheck;
    }

    public int getTempItemSystemId() {
        return tempItemSystemId;
    }

    public void setTempItemSystemId(int tempItemSystemId) {
        this.tempItemSystemId = tempItemSystemId;
    }

    public int getRsContractSid() {
        return rsContractSid;
    }

    public void setRsContractSid(int rsContractSid) {
        this.rsContractSid = rsContractSid;
    }

    public int getNetSalesRuleSystemId() {
        return netSalesRuleSystemId;
    }

    public void setNetSalesRuleSystemId(int netSalesRuleSystemId) {
        this.netSalesRuleSystemId = netSalesRuleSystemId;
    }

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    public String getMarketTypeTable() {
        return marketTypeTable;
    }

    public void setMarketTypeTable(String marketTypeTable) {
        this.marketTypeTable = marketTypeTable;
    }

    public String getDeductionTypeTable() {
        return deductionTypeTable;
    }

    public void setDeductionTypeTable(String deductionTypeTable) {
        this.deductionTypeTable = deductionTypeTable;
    }

    public String getDeductionSubTypeTable() {
        return deductionSubTypeTable;
    }

    public void setDeductionSubTypeTable(String deductionSubTypeTable) {
        this.deductionSubTypeTable = deductionSubTypeTable;
    }

    public String getDeductionCategoryTable() {
        return deductionCategoryTable;
    }

    public void setDeductionCategoryTable(String deductionCategoryTable) {
        this.deductionCategoryTable = deductionCategoryTable;
    }

}
