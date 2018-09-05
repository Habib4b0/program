/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class ComponentInfoDTO {

    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private String formulaType = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;
    private String formulaName = StringUtils.EMPTY;
    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String rebateAmount = StringUtils.EMPTY;
    private String bundleNo = StringUtils.EMPTY;
    private Date attachedDate;
    private String tradeClass = StringUtils.EMPTY;
    private String priceType = StringUtils.EMPTY;
    private String pricePlanNo = StringUtils.EMPTY;
    private String pricePlanName = StringUtils.EMPTY;
    private String priceProtectionStatus = StringUtils.EMPTY;
    private Date priceProtectionStartDate;
    private Date priceProtectionEndDate;
    private String priceProtectionPriceType = StringUtils.EMPTY;
    private String priceToleranceInterval = StringUtils.EMPTY;
    private String priceToleranceFrequency = StringUtils.EMPTY;
    private String priceToleranceType = StringUtils.EMPTY;
    private String priceTolerance = StringUtils.EMPTY;
    private String maxIncrementalChange = StringUtils.EMPTY;
    private String resetEligible = StringUtils.EMPTY;
    private String resetType = StringUtils.EMPTY;
    private Date resetDate;
    private String resetInterval = StringUtils.EMPTY;
    private String resetFrequency = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    private Integer count = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;
    private String attachedStatus = StringUtils.EMPTY;
    private String componenId = StringUtils.EMPTY;
    private String componenNumber = StringUtils.EMPTY;
    private String componenName = StringUtils.EMPTY;
    private String componentStatus = StringUtils.EMPTY;
    private String componentType = StringUtils.EMPTY;
    private Date componentStartDate;
    private Date componentEndDate;
    private Integer systemId = 1;
    private HelperDTO rsTypeDTO;
    private HelperDTO rsCategoryDTO;
    private HelperDTO rsProgramTypeDTO;
    private HelperDTO paymentFrequencyDTO;
    private HelperDTO rebatePlanLevelDTO;
    private String rsTypeValue = StringUtils.EMPTY;
    private String rsCategoryValue = StringUtils.EMPTY;
    private String rsProgramTypeValue = StringUtils.EMPTY;
    private String paymentFrequencyValue = StringUtils.EMPTY;
    private String rebatePlanLevelValue = StringUtils.EMPTY;
    private String rebateFrequencyValue = StringUtils.EMPTY;
    private String itemContractNo = StringUtils.EMPTY;
    private boolean reset = false;
    private String measurementPrice = StringUtils.EMPTY;
    private String resetPriceType = StringUtils.EMPTY;
    private String netResetPriceType = StringUtils.EMPTY;
    private String netPriceType = StringUtils.EMPTY;
    private String subsequentPeriodPriceType = StringUtils.EMPTY;
    private String netSubsequentPeriodPrice= StringUtils.EMPTY;
    private String baselineNetWAC= StringUtils.EMPTY;
    private String basePriceType= StringUtils.EMPTY;
    private Object baselineWAC;
    private String netResetPriceFormula = StringUtils.EMPTY;
    private String netPriceTypeFormula = StringUtils.EMPTY;
    private String netSubsequentPeriodPriceFormula = StringUtils.EMPTY;
    private String netBaselineWACFormula = StringUtils.EMPTY;
    private String nepFormula = StringUtils.EMPTY;
    private String nep = StringUtils.EMPTY;

    public ComponentInfoDTO() {
        super();
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
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

    public String getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }

    public Date getAttachedDate() {
        return attachedDate == null ? null : (Date) attachedDate.clone();
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate == null ? null : (Date) attachedDate.clone();
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPricePlanNo() {
        return pricePlanNo;
    }

    public void setPricePlanNo(String pricePlanNo) {
        this.pricePlanNo = pricePlanNo;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }

    public String getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    public void setPriceProtectionStatus(String priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
    }

    public String getPriceProtectionPriceType() {
        return priceProtectionPriceType;
    }

    public void setPriceProtectionPriceType(String priceProtectionPriceType) {
        this.priceProtectionPriceType = priceProtectionPriceType;
    }

    public String getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(String priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public String getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(String priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    public String getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(String priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
    }

    public String getResetEligible() {
        return resetEligible;
    }

    public void setResetEligible(String resetEligible) {
        this.resetEligible = resetEligible;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public Date getResetDate() {
        return resetDate == null ? null : (Date) resetDate.clone();
    }

    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate == null ? null : (Date) resetDate.clone();
    }

    public String getResetInterval() {
        return resetInterval;
    }

    public void setResetInterval(String resetInterval) {
        this.resetInterval = resetInterval;
    }

    public String getResetFrequency() {
        return resetFrequency;
    }

    public void setResetFrequency(String resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(String rebateAmount) {
        this.rebateAmount = rebateAmount;
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

    public Boolean getIsCount() {
        return isCount;
    }


    public void setIsCount(Boolean isCount) {
        this.isCount = isCount;
    }

    public String getComponenId() {
        return componenId;
    }

    public void setComponenId(String componenId) {
        this.componenId = componenId;
    }

    public String getComponenNumber() {
        return componenNumber;
    }

    public void setComponenNumber(String componenNumber) {
        this.componenNumber = componenNumber;
    }

    public String getComponenName() {
        return componenName;
    }

    public void setComponenName(String componenName) {
        this.componenName = componenName;
    }

    public String getComponentStatus() {
        return componentStatus;
    }

    public void setComponentStatus(String componentStatus) {
        this.componentStatus = componentStatus;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public Date getPriceProtectionStartDate() {
        return priceProtectionStartDate == null ? null : (Date) priceProtectionStartDate.clone();
    }

    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate == null ? null : (Date) priceProtectionStartDate.clone();
    }

    public Date getPriceProtectionEndDate() {
        return priceProtectionEndDate == null ? null : (Date) priceProtectionEndDate.clone();
    }

    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate == null ? null : (Date) priceProtectionEndDate.clone();
    }

    public Date getComponentStartDate() {
        return componentStartDate == null ? null : (Date) componentStartDate.clone();
    }

    public void setComponentStartDate(Date componentStartDate) {
        this.componentStartDate = componentStartDate == null ? null : (Date) componentStartDate.clone();
    }

    public Date getComponentEndDate() {
        return componentEndDate == null ? null : (Date) componentEndDate.clone();
    }

    public void setComponentEndDate(Date componentEndDate) {
        this.componentEndDate = componentEndDate == null ? null : (Date) componentEndDate.clone();
    }

    public String getAttachedStatus() {
        return attachedStatus;
    }

    public void setAttachedStatus(String attachedStatus) {
        this.attachedStatus = attachedStatus;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public HelperDTO getRsType_DTO() {
        return rsTypeDTO;
    }

    public void setRsType_DTO(HelperDTO rsTypeDto) {
        this.rsTypeDTO = rsTypeDto;
    }

    public HelperDTO getRsCategory_DTO() {
        return rsCategoryDTO;
    }

    public void setRsCategory_DTO(HelperDTO rsCategoryDto) {
        this.rsCategoryDTO = rsCategoryDto;
    }

    public HelperDTO getRsProgramType_DTO() {
        return rsProgramTypeDTO;
    }

    public void setRsProgramType_DTO(HelperDTO rsProgramTypeDto) {
        this.rsProgramTypeDTO = rsProgramTypeDto;
    }

    public HelperDTO getPaymentFrequency_DTO() {
        return paymentFrequencyDTO;
    }

    public void setPaymentFrequency_DTO(HelperDTO paymentFrequencyDto) {
        this.paymentFrequencyDTO = paymentFrequencyDto;
    }

    public HelperDTO getRebatePlanLevel_DTO() {
        return rebatePlanLevelDTO;
    }

    public void setRebatePlanLevel_DTO(HelperDTO rebatePlanLevelDto) {
        this.rebatePlanLevelDTO = rebatePlanLevelDto;
    }

    public String getRsType_Value() {
        return rsTypeValue;
    }

    public void setRsType_Value(String rsTypeValue) {
        this.rsTypeValue = rsTypeValue;
    }

    public String getRsCategory_Value() {
        return rsCategoryValue;
    }

    public void setRsCategory_Value(String rsCategoryValue) {
        this.rsCategoryValue = rsCategoryValue;
    }

    public String getRsProgramType_Value() {
        return rsProgramTypeValue;
    }

    public void setRsProgramType_Value(String rsProgramTypeValue) {
        this.rsProgramTypeValue = rsProgramTypeValue;
    }

    public String getPaymentFrequency_Value() {
        return paymentFrequencyValue;
    }

    public void setPaymentFrequency_Value(String paymentFrequencyValue) {
        this.paymentFrequencyValue = paymentFrequencyValue;
    }

    public String getRebatePlanLevel_Value() {
        return rebatePlanLevelValue;
    }

    public void setRebatePlanLevel_Value(String rebatePlanLevelValue) {
        this.rebatePlanLevelValue = rebatePlanLevelValue;
    }

    public String getRebateFrequency_Value() {
        return rebateFrequencyValue;
    }

    public void setRebateFrequency_Value(String rebateFrequencyValue) {
        this.rebateFrequencyValue = rebateFrequencyValue;
    }

    public String getItemContractNo() {
        return itemContractNo;
    }

    public void setItemContractNo(String itemContractNo) {
        this.itemContractNo = itemContractNo;
    }

    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public String getMeasurementPrice() {
        return measurementPrice;
    }

    public void setMeasurementPrice(String measurementPrice) {
        this.measurementPrice = measurementPrice;
    }

    public String getResetPriceType() {
        return resetPriceType;
    }

    public void setResetPriceType(String resetPriceType) {
        this.resetPriceType = resetPriceType;
    }

    public String getNetResetPriceType() {
        return netResetPriceType;
    }

    public void setNetResetPriceType(String netResetPriceType) {
        this.netResetPriceType = netResetPriceType;
    }

    public String getNetPriceType() {
        return netPriceType;
    }

    public void setNetPriceType(String netPriceType) {
        this.netPriceType = netPriceType;
    }

    public String getSubsequentPeriodPriceType() {
        return subsequentPeriodPriceType;
    }

    public void setSubsequentPeriodPriceType(String subsequentPeriodPriceType) {
        this.subsequentPeriodPriceType = subsequentPeriodPriceType;
    }

    public String getNetSubsequentPeriodPrice() {
        return netSubsequentPeriodPrice;
    }

    public void setNetSubsequentPeriodPrice(String netSubsequentPeriodPrice) {
        this.netSubsequentPeriodPrice = netSubsequentPeriodPrice;
    }

    public String getBaselineNetWAC() {
        return baselineNetWAC;
    }

    public void setBaselineNetWAC(String baselineNetWAC) {
        this.baselineNetWAC = baselineNetWAC;
    }

    public String getBasePriceType() {
        return basePriceType;
    }

    public void setBasePriceType(String basePriceType) {
        this.basePriceType = basePriceType;
    }

    public Object getBaselineWAC() {
        return baselineWAC;
    }

    public void setBaselineWAC(Object baselineWAC) {
        this.baselineWAC = baselineWAC;
    }

    public String getNetSubsequentPeriodPriceFormula() {
        return netSubsequentPeriodPriceFormula;
    }

    public void setNetSubsequentPeriodPriceFormula(String netSubsequentPeriodPriceFormula) {
        this.netSubsequentPeriodPriceFormula = netSubsequentPeriodPriceFormula;
    }


    public String getNetResetPriceFormula() {
        return netResetPriceFormula;
    }

    public void setNetResetPriceFormula(String netResetPriceFormula) {
        this.netResetPriceFormula = netResetPriceFormula;
    }

    public String getNetPriceTypeFormula() {
        return netPriceTypeFormula;
    }

    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        this.netPriceTypeFormula = netPriceTypeFormula;
    }

    public String getNetBaselineWACFormula() {
        return netBaselineWACFormula;
    }

    public void setNetBaselineWACFormula(String netBaselineWACFormula) {
        this.netBaselineWACFormula = netBaselineWACFormula;
    }

    public String getNepFormula() {
        return nepFormula;
    }

    public void setNepFormula(String nepFormula) {
        this.nepFormula = nepFormula;
    }


    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }
    

}
