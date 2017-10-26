/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import org.asi.container.ExtMapDTO;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class FixedDollarDTO extends ExtMapDTO implements Comparator<FixedDollarDTO> {

    private String companyId = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private Integer projectionId;
    private Boolean checkRecord;
    private String id = StringUtils.EMPTY;
    private String accrualPeriodSales = StringUtils.EMPTY;
    private String accrualPeriodAccruals = StringUtils.EMPTY;
    private String accrualPeriodActuals = StringUtils.EMPTY;
    private String variance = StringUtils.EMPTY;
    private String suggestedAdj = StringUtils.EMPTY;
    private String year = StringUtils.EMPTY;
    private String actuals = StringUtils.EMPTY;
    private String accruals = StringUtils.EMPTY;
    private String remainingEstimate = StringUtils.EMPTY;
    private String projectedRemainingEstimate = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private String acctType = StringUtils.EMPTY;
    private String acctSubType = StringUtils.EMPTY;
    private String adjustmentType = StringUtils.EMPTY;
    private String accrualPeriod = StringUtils.EMPTY;
    private String customerGroup = StringUtils.EMPTY;
    private String contract = StringUtils.EMPTY;
    private String productGroup = StringUtils.EMPTY;
    private String product = StringUtils.EMPTY;
    private String productIdentifier = StringUtils.EMPTY;
    private String companyMasterSid = StringUtils.EMPTY;
    private Integer startIndex = 0;
    private Integer offSet = 0;
    private String countFlag = StringUtils.EMPTY;
    private Integer parent = 0;
    private Integer levelNo = 0;
    private Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>();
    private String contractSid = StringUtils.EMPTY;
    private String brandMasterSid = StringUtils.EMPTY;
    private String itemSid = StringUtils.EMPTY;
    private String rebateId = StringUtils.EMPTY;
    private String ccpSid = StringUtils.EMPTY;
    private String percentageRelease = StringUtils.EMPTY;
    private String manualAmount = StringUtils.EMPTY;
    private String totalAdjustmentDollar = StringUtils.EMPTY;
    private String totalAdjustmentDollarPerDay = StringUtils.EMPTY;
    private String reasonCode = StringUtils.EMPTY;
    private String notes = StringUtils.EMPTY;
    private String autoAccruals = StringUtils.EMPTY;
    private String manualAdjustment = StringUtils.EMPTY;
    private String paymentTrueUp = StringUtils.EMPTY;
    private String other = StringUtils.EMPTY;
    private String projectedDisc = StringUtils.EMPTY;
    private String percentageAccrualToProj = StringUtils.EMPTY;
    private String percentageActualToProj = StringUtils.EMPTY;
    private String dateRangeGTS = StringUtils.EMPTY;
    private String dateRangeSales = StringUtils.EMPTY;
    private String dateRangeAccruals = StringUtils.EMPTY;
    private String dateRangeActuals = StringUtils.EMPTY;
    private String AccountClosureMasterSid = StringUtils.EMPTY;
    private String calculate = "0";
    private String detailssid = StringUtils.EMPTY;
    private String methodology = StringUtils.EMPTY;
    private String sessionId = StringUtils.EMPTY;
    private String viewtype = "0";
    private String ccpSidFromGtn = StringUtils.EMPTY;
    private String currentCCpid = StringUtils.EMPTY;
    private String selectedCCplist = StringUtils.EMPTY;

    public String getDateRangeActuals() {
        return dateRangeActuals;
    }

    public void setDateRangeActuals(String dateRangeActuals) {
        this.dateRangeActuals = dateRangeActuals;
    }

    public String getDateRangeAccruals() {
        return dateRangeAccruals;
    }

    public void setDateRangeAccruals(String dateRangeAccruals) {
        this.dateRangeAccruals = dateRangeAccruals;
    }

    public String getDateRangeSales() {
        return dateRangeSales;
    }

    public void setDateRangeSales(String dateRangeSales) {
        this.dateRangeSales = dateRangeSales;
    }

    public String getDateRangeGTS() {
        return dateRangeGTS;
    }

    public void setDateRangeGTS(String dateRangeGTS) {
        this.dateRangeGTS = dateRangeGTS;
    }

    public String getAutoAccruals() {
        return autoAccruals;
    }

    public void setAutoAccruals(String autoAccruals) {
        this.autoAccruals = autoAccruals;
    }

    public String getManualAdjustment() {
        return manualAdjustment;
    }

    public void setManualAdjustment(String manualAdjustment) {
        this.manualAdjustment = manualAdjustment;
    }

    public String getPaymentTrueUp() {
        return paymentTrueUp;
    }

    public void setPaymentTrueUp(String paymentTrueUp) {
        this.paymentTrueUp = paymentTrueUp;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getProjectedDisc() {
        return projectedDisc;
    }

    public void setProjectedDisc(String projectedDisc) {
        this.projectedDisc = projectedDisc;
    }

    public String getPercentageAccrualToProj() {
        return percentageAccrualToProj;
    }

    public void setPercentageAccrualToProj(String percentageAccrualToProj) {
        this.percentageAccrualToProj = percentageAccrualToProj;
    }

    public String getPercentageRelease() {
        return percentageRelease;
    }

    public void setPercentageRelease(String percentageRelease) {
        this.percentageRelease = percentageRelease;
    }

    public String getManualAmount() {
        return manualAmount;
    }

    public void setManualAmount(String manualAmount) {
        this.manualAmount = manualAmount;
    }

    public String getTotalAdjustmentDollar() {
        return totalAdjustmentDollar;
    }

    public void setTotalAdjustmentDollar(String totalAdjustmentDollar) {
        this.totalAdjustmentDollar = totalAdjustmentDollar;
    }

    public String getTotalAdjustmentDollarPerDay() {
        return totalAdjustmentDollarPerDay;
    }

    public void setTotalAdjustmentDollarPerDay(String totalAdjustmentDollarPerDay) {
        this.totalAdjustmentDollarPerDay = totalAdjustmentDollarPerDay;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCcpSid() {
        return ccpSid;
    }

    public void setCcpSid(String ccpSid) {
        this.ccpSid = ccpSid;
    }

    public String getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(String brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public String getRebateId() {
        return rebateId;
    }

    public void setRebateId(String rebateId) {
        this.rebateId = rebateId;
    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public Map<Integer, Map<String, String>> getIdMainMap() {
        return idMainMap;
    }

    public void setIdMainMap(Map<Integer, Map<String, String>> idMainMap) {
        this.idMainMap = idMainMap;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public String getCountFlag() {
        return countFlag;
    }

    public void setCountFlag(String countFlag) {
        this.countFlag = countFlag;
    }

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getAcctSubType() {
        return acctSubType;
    }

    public void setAcctSubType(String acctSubType) {
        this.acctSubType = acctSubType;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAccrualPeriod() {
        return accrualPeriod;
    }

    public void setAccrualPeriod(String accrualPeriod) {
        this.accrualPeriod = accrualPeriod;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getActuals() {
        return actuals;
    }

    public void setActuals(String actuals) {
        this.actuals = actuals;
    }

    public String getAccruals() {
        return accruals;
    }

    public void setAccruals(String accruals) {
        this.accruals = accruals;
    }

    public String getRemainingEstimate() {
        return remainingEstimate;
    }

    public void setRemainingEstimate(String remainingEstimate) {
        this.remainingEstimate = remainingEstimate;
    }

    public String getProjectedRemainingEstimate() {
        return projectedRemainingEstimate;
    }

    public void setProjectedRemainingEstimate(String projectedRemainingEstimate) {
        this.projectedRemainingEstimate = projectedRemainingEstimate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getAccrualPeriodSales() {
        return accrualPeriodSales;
    }

    public void setAccrualPeriodSales(String accrualPeriodSales) {
        this.accrualPeriodSales = accrualPeriodSales;
    }

    public String getAccrualPeriodAccruals() {
        return accrualPeriodAccruals;
    }

    public void setAccrualPeriodAccruals(String accrualPeriodAccruals) {
        this.accrualPeriodAccruals = accrualPeriodAccruals;
    }

    public String getAccrualPeriodActuals() {
        return accrualPeriodActuals;
    }

    public void setAccrualPeriodActuals(String accrualPeriodActuals) {
        this.accrualPeriodActuals = accrualPeriodActuals;
    }

    public String getVariance() {
        return variance;
    }

    public void setVariance(String variance) {
        this.variance = variance;
    }

    public String getSuggestedAdj() {
        return suggestedAdj;
    }

    public void setSuggestedAdj(String suggestedAdj) {
        this.suggestedAdj = suggestedAdj;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(Integer projectionId) {
        this.projectionId = projectionId;
    }

    public int compare(FixedDollarDTO o1, FixedDollarDTO o2) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public String getAccountClosureMasterSid() {
        return AccountClosureMasterSid;
    }

    public void setAccountClosureMasterSid(String AccountClosureMasterSid) {
        this.AccountClosureMasterSid = AccountClosureMasterSid;
    }

    public String getCalculate() {
        return calculate;
    }

    public void setCalculate(String calculate) {
        this.calculate = calculate;
    }

    public String getDetailssid() {
        return detailssid;
    }

    public void setDetailssid(String detailssid) {
        this.detailssid = detailssid;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getViewtype() {
        return viewtype;
    }

    public void setViewtype(String viewtype) {
        this.viewtype = viewtype;
    }

    public String getCcpSidFromGtn() {
        return ccpSidFromGtn;
    }

    public void setCcpSidFromGtn(String ccpSidFromGtn) {
        this.ccpSidFromGtn = ccpSidFromGtn;
    }

    public String getPercentageActualToProj() {
        return percentageActualToProj;
    }

    public void setPercentageActualToProj(String percentageActualToProj) {
        this.percentageActualToProj = percentageActualToProj;
    }

    public String getCurrentCCpid() {
        return currentCCpid;
    }

    public void setCurrentCCpid(String currentCCpid) {
        this.currentCCpid = currentCCpid;
    }

    public String getSelectedCCplist() {
        return selectedCCplist;
    }

    public void setSelectedCCplist(String selectedCCplist) {
        this.selectedCCplist = selectedCCplist;
    }

    
}
