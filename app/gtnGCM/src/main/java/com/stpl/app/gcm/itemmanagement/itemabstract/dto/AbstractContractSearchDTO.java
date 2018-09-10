/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.dto;

import com.stpl.ifs.util.HelperDTO;
import com.vaadin.ui.Component;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Srithar
 */
public class AbstractContractSearchDTO {

    private String projectionId = StringUtils.EMPTY;
    private String workFlowStatus = StringUtils.EMPTY;
    private String contractHolder = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private HelperDTO status;
    private Date itemStartDate;
    private Date itemEndDate;
    private Date cpStartDate;
    private Date cpEndDate;
    private String contractPrice = StringUtils.EMPTY;
    private String price = StringUtils.EMPTY;
    private Date priceProtectionStartDate;
    private Date priceProtectionEndDate;
    private String priceTolerance = StringUtils.EMPTY;
    private HelperDTO priceToleranceInterval;
    private HelperDTO priceToleranceFrequency;
    private HelperDTO priceToleranceType;
    private String basePrice = StringUtils.EMPTY;
    private Date rsStartDate;
    private Date rsEndDate;
    private String formulaId = StringUtils.EMPTY;
    private String rebatePlan = StringUtils.EMPTY;
    private String formulaMethodId = StringUtils.EMPTY;
    private String rebateAmount = StringUtils.EMPTY;
    private String cfpNO = StringUtils.EMPTY;
    private String cfpName = StringUtils.EMPTY;
    private String ifpNo = StringUtils.EMPTY;
    private String ifpName = StringUtils.EMPTY;
    private String psNo = StringUtils.EMPTY;
    private String psName = StringUtils.EMPTY;
    private String rsNo = StringUtils.EMPTY;
    private String rsName = StringUtils.EMPTY;
    private String rarCategory = StringUtils.EMPTY;
    private String rebateScheduleId = StringUtils.EMPTY;
    private String rebateScheduleName = StringUtils.EMPTY;
    private HelperDTO rebateScheduleTypeDTO;
    private HelperDTO rarCategoryDTO;
    private String rebateScheduleNo = StringUtils.EMPTY;
    private HelperDTO rebateScheduleCategoryDTO;
    private HelperDTO rebateProgramTypeDTO;
    private HelperDTO marketTypeDTO;
    private String rebateScheduleAlias = StringUtils.EMPTY;
    private Integer contractMasterSid = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;
    private boolean checkRecord = false;
    private Integer contractSid = 0;
    private Integer companySid = 0;
    private Integer cfpContractSid = 0;
    private Integer ifpConteractSid = 0;
    private Integer psContractSid = 0;
    private Integer rsContractSid = 0;
    private Integer itemMasterSid = 0;
    private Integer caseNo = 0;
    private String columnName = StringUtils.EMPTY;
    private Component projectionIdLink;
    private String transferScreenName = StringUtils.EMPTY;
    private Object fieldFactoryValue ;
    private Integer tempSid;
    private int priceType;
    private HelperDTO priceProtectionStatus;
    private int measurementPrice;
    private HelperDTO resetEligible;
    private HelperDTO resetType;
    private HelperDTO resetInterval;
    private HelperDTO resetFrequency;
    private int resetPriceType;
    private HelperDTO netResetPriceType;
    private HelperDTO netPriceType;
    private int subsequentPeriodPriceType;
    private HelperDTO netSubsequentPeriodPrice;
    private HelperDTO baselineNetWAC;
    private HelperDTO basePriceType;
    private Object baselineWAC;
    private Date resetDate;
    private String netResetPriceFormula = StringUtils.EMPTY;
    private String netSubsequentPeriodPriceFormula = StringUtils.EMPTY;
    private String netPriceTypeFormula = StringUtils.EMPTY;
    private String maxIncrementalChange = StringUtils.EMPTY;
    private String netBaselineWACFormula = StringUtils.EMPTY;
    private String nepFormula = StringUtils.EMPTY;
    private String nep = StringUtils.EMPTY;
    private String baseLineWacManual = StringUtils.EMPTY;
    private int baseLineWacPriceType;
    private Date baseLineWacDate;

    public AbstractContractSearchDTO() {
        super();
    }
    
    public Integer getTempSid() {
        return tempSid;
    }

    public void setTempSid(Integer tempSid) {
        this.tempSid = tempSid;
    }

    public String getTransferScreenName() {
        return transferScreenName;
    }

    public void setTransferScreenName(String transferScreenName) {
        this.transferScreenName = transferScreenName;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Integer getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(Integer itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(Integer caseNo) {
        this.caseNo = caseNo;
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
    }

    public String getWorkFlowStatus() {
        return workFlowStatus;
    }

    public void setWorkFlowStatus(String workFlowStatus) {
        this.workFlowStatus = workFlowStatus;
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

    public HelperDTO getStatus() {
        return status;
    }

    public void setStatus(HelperDTO status) {
        this.status = status;
    }

    public Date getItemStartDate() {
        return itemStartDate == null ? null : (Date) itemStartDate.clone();
    }

    public void setItemStartDate(Date itemStartDate) {
        this.itemStartDate = itemStartDate == null ? null : (Date) itemStartDate.clone();
    }

    public Date getItemEndDate() {
        return itemEndDate == null ? null : (Date) itemEndDate.clone();
    }

    public void setItemEndDate(Date itemEndDate) {
        this.itemEndDate = itemEndDate == null ? null : (Date) itemEndDate.clone();
    }

    public Date getCpStartDate() {
        return cpStartDate == null ? null : (Date) cpStartDate.clone();
    }

    public void setCpStartDate(Date cpStartDate) {
        this.cpStartDate = cpStartDate == null ? null : (Date) cpStartDate.clone();
    }

    public Date getCpEndDate() {
        return cpEndDate == null ? null : (Date) cpEndDate.clone();
    }

    public void setCpEndDate(Date cpEndDate) {
        this.cpEndDate = cpEndDate == null ? null : (Date) cpEndDate.clone();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    public HelperDTO getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(HelperDTO priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public Date getRSStartDate() {
        return rsStartDate == null ? null : (Date) rsStartDate.clone();
    }

    public void setRSStartDate(Date rsStartDate) {
        this.rsStartDate = rsStartDate == null ? null : (Date) rsStartDate.clone();
    }

    public Date getRSEndDate() {
        return rsEndDate == null ? null : (Date) rsEndDate.clone();
    }

    public void setRSEndDate(Date rsEndDate) {
        this.rsEndDate = rsEndDate == null ? null : (Date) rsEndDate.clone();
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getRebatePlan() {
        return rebatePlan;
    }

    public void setRebatePlan(String rebatePlan) {
        this.rebatePlan = rebatePlan;
    }

    public String getFormulaMethodId() {
        return formulaMethodId;
    }

    public void setFormulaMethodId(String formulaMethodId) {
        this.formulaMethodId = formulaMethodId;
    }

    public String getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(String rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public String getCfpNO() {
        return cfpNO;
    }

    public void setCfpNO(String cfpNO) {
        this.cfpNO = cfpNO;
    }

    public String getCfpName() {
        return cfpName;
    }

    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }

    public String getIfpNo() {
        return ifpNo;
    }

    public void setIfpNo(String ifpNo) {
        this.ifpNo = ifpNo;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getPsNo() {
        return psNo;
    }

    public void setPsNo(String psNo) {
        this.psNo = psNo;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getRsNo() {
        return rsNo;
    }

    public void setRsNo(String rsNo) {
        this.rsNo = rsNo;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getRarCategory() {
        return rarCategory;
    }

    public void setRarCategory(String rarCategory) {
        this.rarCategory = rarCategory;
    }

    public String getRebateScheduleId() {
        return rebateScheduleId;
    }

    public void setRebateScheduleId(String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    public HelperDTO getRebateScheduleTypeDto() {
        return rebateScheduleTypeDTO;
    }

    public void setRebateScheduleTypeDto(HelperDTO rebateScheduleTypeDto) {
        this.rebateScheduleTypeDTO = rebateScheduleTypeDto;
    }

    public HelperDTO getRarCategoryDto() {
        return rarCategoryDTO;
    }

    public void setRarCategoryDto(HelperDTO rarCategoryDto) {
        this.rarCategoryDTO = rarCategoryDto;
    }

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public HelperDTO getRebateScheduleCategoryDto() {
        return rebateScheduleCategoryDTO;
    }

    public void setRebateScheduleCategoryDto(HelperDTO rebateScheduleCategoryDto) {
        this.rebateScheduleCategoryDTO = rebateScheduleCategoryDto;
    }

    public HelperDTO getRebateProgramTypeDto() {
        return rebateProgramTypeDTO;
    }

    public void setRebateProgramTypeDto(HelperDTO rebateProgramTypeDto) {
        this.rebateProgramTypeDTO = rebateProgramTypeDto;
    }

    public HelperDTO getMarketTypeDto() {
        return marketTypeDTO;
    }

    public void setMarketTypeDto(HelperDTO marketTypeDto) {
        this.marketTypeDTO = marketTypeDto;
    }

    public String getRebateScheduleAlias() {
        return rebateScheduleAlias;
    }

    public void setRebateScheduleAlias(String rebateScheduleAlias) {
        this.rebateScheduleAlias = rebateScheduleAlias;
    }

    public Integer getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(Integer contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
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

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public Integer getContractSid() {
        return contractSid;
    }

    public void setContractSid(Integer contractSid) {
        this.contractSid = contractSid;
    }

    public Integer getCompanySid() {
        return companySid;
    }

    public void setCompanySid(Integer companySid) {
        this.companySid = companySid;
    }

    public Integer getCfpContractSid() {
        return cfpContractSid;
    }

    public void setCfpContractSid(Integer cfpContractSid) {
        this.cfpContractSid = cfpContractSid;
    }

    public Integer getIfpConteractSid() {
        return ifpConteractSid;
    }

    public void setIfpConteractSid(Integer ifpConteractSid) {
        this.ifpConteractSid = ifpConteractSid;
    }

    public Integer getPsContractSid() {
        return psContractSid;
    }

    public void setPsContractSid(Integer psContractSid) {
        this.psContractSid = psContractSid;
    }

    public Integer getRsContractSid() {
        return rsContractSid;
    }

    public void setRsContractSid(Integer rsContractSid) {
        this.rsContractSid = rsContractSid;
    }

    public HelperDTO getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(HelperDTO priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    public HelperDTO getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(HelperDTO priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public Component getProjectionIdLink() {
        return projectionIdLink;
    }

    public void setProjectionIdLink(Component projectionIdLink) {
        this.projectionIdLink = projectionIdLink;
    }

    public Object getFieldFactoryValue() {
        return fieldFactoryValue;
    }

    public void setFieldFactoryValue(Object fieldFactoryValue) {
        this.fieldFactoryValue = fieldFactoryValue;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public HelperDTO getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    public void setPriceProtectionStatus(HelperDTO priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
    }

    public int getMeasurementPrice() {
        return measurementPrice;
    }

    public void setMeasurementPrice(int measurementPrice) {
        this.measurementPrice = measurementPrice;
    }


    public HelperDTO getResetEligible() {
        return resetEligible;
    }

    public void setResetEligible(HelperDTO resetEligible) {
        this.resetEligible = resetEligible;
    }

    public HelperDTO getResetType() {
        return resetType;
    }

    public void setResetType(HelperDTO resetType) {
        this.resetType = resetType;
    }

    public HelperDTO getResetInterval() {
        return resetInterval;
    }

    public void setResetInterval(HelperDTO resetInterval) {
        this.resetInterval = resetInterval;
    }

    public HelperDTO getResetFrequency() {
        return resetFrequency;
    }

    public void setResetFrequency(HelperDTO resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public int getResetPriceType() {
        return resetPriceType;
    }

    public void setResetPriceType(int resetPriceType) {
        this.resetPriceType = resetPriceType;
    }

    public HelperDTO getNetResetPriceType() {
        return netResetPriceType;
    }

    public void setNetResetPriceType(HelperDTO netResetPriceType) {
        this.netResetPriceType = netResetPriceType;
    }
    public HelperDTO getNetPriceType() {
        return netPriceType;
    }

    public void setNetPriceType(HelperDTO netPriceType) {
        this.netPriceType = netPriceType;
    }

    public int getSubsequentPeriodPriceType() {
        return subsequentPeriodPriceType;
    }

    public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
        this.subsequentPeriodPriceType = subsequentPeriodPriceType;
    }

    public HelperDTO getNetSubsequentPeriodPrice() {
        return netSubsequentPeriodPrice;
    }

    public void setNetSubsequentPeriodPrice(HelperDTO netSubsequentPeriodPrice) {
        this.netSubsequentPeriodPrice = netSubsequentPeriodPrice;
    }
    
    public String getNetSubsequentPeriodPriceFormula() {
        return netSubsequentPeriodPriceFormula;
    }

    public void setNetSubsequentPeriodPriceFormula(String netSubsequentPeriodPriceFormula) {
        this.netSubsequentPeriodPriceFormula = netSubsequentPeriodPriceFormula;
    }
    public HelperDTO getBaselineNetWAC() {
        return baselineNetWAC;
    }

    public void setBaselineNetWAC(HelperDTO baselineNetWAC) {
        this.baselineNetWAC = baselineNetWAC;
    }

    public HelperDTO getBasePriceType() {
        return basePriceType;
    }

    public void setBasePriceType(HelperDTO basePriceType) {
        this.basePriceType = basePriceType;
    }

    public Object getBaselineWAC() {
        return baselineWAC;
    }

    public void setBaselineWAC(Object baselineWAC) {
        this.baselineWAC = baselineWAC;
    }

    public Date getResetDate() {
        return resetDate == null ? null : (Date) resetDate.clone();
    }

    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate == null ? null : (Date) resetDate.clone();
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

    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
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

    public String getBaseLineWacManual() {
        return baseLineWacManual;
    }

    public void setBaseLineWacManual(String baseLineWacManual) {
        this.baseLineWacManual = baseLineWacManual;
    }

    public int getBaseLineWacPriceType() {
        return baseLineWacPriceType;
    }

    public void setBaseLineWacPriceType(int baseLineWacPriceType) {
        this.baseLineWacPriceType = baseLineWacPriceType;
    }

    public Date getBaseLineWacDate() {
        return baseLineWacDate == null ? null : (Date) baseLineWacDate.clone();
    }

    public void setBaseLineWacDate(Date baseLineWacDate) {
        this.baseLineWacDate = baseLineWacDate == null ? null : (Date) baseLineWacDate.clone();
    }

}
