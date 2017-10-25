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
    private Boolean isCount = false;
    private String attachedStatus = StringUtils.EMPTY;
    private String componenId = StringUtils.EMPTY;
    private String componenNumber = StringUtils.EMPTY;
    private String componenName = StringUtils.EMPTY;
    private String componentStatus = StringUtils.EMPTY;
    private String componentType = StringUtils.EMPTY;
    private Date componentStartDate;
    private Date componentEndDate;
    private Integer systemId = 1;
    private HelperDTO rsType_DTO;
    private HelperDTO rsCategory_DTO;
    private HelperDTO rsProgramType_DTO;
    private HelperDTO paymentFrequency_DTO;
    private HelperDTO rebatePlanLevel_DTO;
    private String rsType_Value = StringUtils.EMPTY;
    private String rsCategory_Value = StringUtils.EMPTY;
    private String rsProgramType_Value = StringUtils.EMPTY;
    private String paymentFrequency_Value = StringUtils.EMPTY;
    private String rebatePlanLevel_Value = StringUtils.EMPTY;
    private String rebateFrequency_Value = StringUtils.EMPTY;
    private String itemContractNo = StringUtils.EMPTY;
    private Boolean reset = false;

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
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
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
        return resetDate;
    }

    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
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
        return priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }

    public Date getPriceProtectionEndDate() {
        return priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate;
    }

    public Date getComponentStartDate() {
        return componentStartDate;
    }

    public void setComponentStartDate(Date componentStartDate) {
        this.componentStartDate = componentStartDate;
    }

    public Date getComponentEndDate() {
        return componentEndDate;
    }

    public void setComponentEndDate(Date componentEndDate) {
        this.componentEndDate = componentEndDate;
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
        return rsType_DTO;
    }

    public void setRsType_DTO(HelperDTO rsType_DTO) {
        this.rsType_DTO = rsType_DTO;
    }

    public HelperDTO getRsCategory_DTO() {
        return rsCategory_DTO;
    }

    public void setRsCategory_DTO(HelperDTO rsCategory_DTO) {
        this.rsCategory_DTO = rsCategory_DTO;
    }

    public HelperDTO getRsProgramType_DTO() {
        return rsProgramType_DTO;
    }

    public void setRsProgramType_DTO(HelperDTO rsProgramType_DTO) {
        this.rsProgramType_DTO = rsProgramType_DTO;
    }

    public HelperDTO getPaymentFrequency_DTO() {
        return paymentFrequency_DTO;
    }

    public void setPaymentFrequency_DTO(HelperDTO paymentFrequency_DTO) {
        this.paymentFrequency_DTO = paymentFrequency_DTO;
    }

    public HelperDTO getRebatePlanLevel_DTO() {
        return rebatePlanLevel_DTO;
    }

    public void setRebatePlanLevel_DTO(HelperDTO rebatePlanLevel_DTO) {
        this.rebatePlanLevel_DTO = rebatePlanLevel_DTO;
    }

    public String getRsType_Value() {
        return rsType_Value;
    }

    public void setRsType_Value(String rsType_Value) {
        this.rsType_Value = rsType_Value;
    }

    public String getRsCategory_Value() {
        return rsCategory_Value;
    }

    public void setRsCategory_Value(String rsCategory_Value) {
        this.rsCategory_Value = rsCategory_Value;
    }

    public String getRsProgramType_Value() {
        return rsProgramType_Value;
    }

    public void setRsProgramType_Value(String rsProgramType_Value) {
        this.rsProgramType_Value = rsProgramType_Value;
    }

    public String getPaymentFrequency_Value() {
        return paymentFrequency_Value;
    }

    public void setPaymentFrequency_Value(String paymentFrequency_Value) {
        this.paymentFrequency_Value = paymentFrequency_Value;
    }

    public String getRebatePlanLevel_Value() {
        return rebatePlanLevel_Value;
    }

    public void setRebatePlanLevel_Value(String rebatePlanLevel_Value) {
        this.rebatePlanLevel_Value = rebatePlanLevel_Value;
    }

    public String getRebateFrequency_Value() {
        return rebateFrequency_Value;
    }

    public void setRebateFrequency_Value(String rebateFrequency_Value) {
        this.rebateFrequency_Value = rebateFrequency_Value;
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

}
