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
    private Date RSStartDate;
    private Date RSEndDate;
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
    private HelperDTO rebateScheduleType_DTO;
    private HelperDTO rarCategory_DTO;
    private String rebateScheduleNo = StringUtils.EMPTY;
    private HelperDTO rebateScheduleCategory_DTO;
    private HelperDTO rebateProgramType_DTO;
    private HelperDTO marketType_DTO;
    private String rebateScheduleAlias = StringUtils.EMPTY;
    private Integer contractMasterSid = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;
    private Boolean checkRecord = false;
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
    private Integer tempSid ;

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

    public HelperDTO getStatus() {
        return status;
    }

    public void setStatus(HelperDTO status) {
        this.status = status;
    }

    public Date getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(Date itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public Date getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(Date itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    public Date getCpStartDate() {
        return cpStartDate;
    }

    public void setCpStartDate(Date cpStartDate) {
        this.cpStartDate = cpStartDate;
    }

    public Date getCpEndDate() {
        return cpEndDate;
    }

    public void setCpEndDate(Date cpEndDate) {
        this.cpEndDate = cpEndDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
        return RSStartDate;
    }

    public void setRSStartDate(Date RSStartDate) {
        this.RSStartDate = RSStartDate;
    }

    public Date getRSEndDate() {
        return RSEndDate;
    }

    public void setRSEndDate(Date RSEndDate) {
        this.RSEndDate = RSEndDate;
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

    public HelperDTO getRebateScheduleType_DTO() {
        return rebateScheduleType_DTO;
    }

    public void setRebateScheduleType_DTO(HelperDTO rebateScheduleType_DTO) {
        this.rebateScheduleType_DTO = rebateScheduleType_DTO;
    }

    public HelperDTO getRarCategory_DTO() {
        return rarCategory_DTO;
    }

    public void setRarCategory_DTO(HelperDTO rarCategory_DTO) {
        this.rarCategory_DTO = rarCategory_DTO;
    }

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public HelperDTO getRebateScheduleCategory_DTO() {
        return rebateScheduleCategory_DTO;
    }

    public void setRebateScheduleCategory_DTO(HelperDTO rebateScheduleCategory_DTO) {
        this.rebateScheduleCategory_DTO = rebateScheduleCategory_DTO;
    }

    public HelperDTO getRebateProgramType_DTO() {
        return rebateProgramType_DTO;
    }

    public void setRebateProgramType_DTO(HelperDTO rebateProgramType_DTO) {
        this.rebateProgramType_DTO = rebateProgramType_DTO;
    }

    public HelperDTO getMarketType_DTO() {
        return marketType_DTO;
    }

    public void setMarketType_DTO(HelperDTO marketType_DTO) {
        this.marketType_DTO = marketType_DTO;
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

}
