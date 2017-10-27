/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.dto;

import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abishekram.r
 */
public class AddItemTableDTO {

    private String contractHolder = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private HelperDTO marketType_DTO=new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    private Date startDate;
    private Date endDate;
    private HelperDTO status;
    private Date itemStartDate;
    private Date itemEndDate;
    private String cfpNO = StringUtils.EMPTY;
    private String cfpName = StringUtils.EMPTY;
    private String ifpNo = StringUtils.EMPTY;
    private String ifpName = StringUtils.EMPTY;
    private String psNo = StringUtils.EMPTY;
    private String psName = StringUtils.EMPTY;
    private String rsNo = StringUtils.EMPTY;
    private String rsName = StringUtils.EMPTY;
    private String rarCategory = StringUtils.EMPTY;
    private String cfp = StringUtils.EMPTY;
    private String ifp = StringUtils.EMPTY;
    private String priceSchedule = StringUtils.EMPTY;
    private String customerNo = StringUtils.EMPTY;
    private String customerName = StringUtils.EMPTY;
    private String rebateSchedule = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private HelperDTO typeDdlb=new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    private Date fromDate;
    private Date toDate;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;
    private Boolean checkRecord = false;
    private List<ItemIndexDto> selectedItemList = new ArrayList<>();
    private Integer contractSid = 0;
    private Integer companySid = 0;
    private Integer cfpContractSid = 0;
    private Integer ifpConteractSid = 0;
    private Integer psContractSid = 0;
    private Integer rsContractSid = 0;
    private Date cpStartDate;
    private Date cpEndDate;
    private String contractPrice = StringUtils.EMPTY;
    private String price = StringUtils.EMPTY;
    private Date priceProtectionStartDate;
    private Date priceProtectionEndDate;
    private HelperDTO priceToleranceType;
    private String priceTolerance = StringUtils.EMPTY;
    private HelperDTO priceToleranceFrequency;
    private HelperDTO priceToleranceInterval;
    private String basePrice = StringUtils.EMPTY;
    private Date RSStartDate;
    private Date RSEndDate;
    private String formulaId = StringUtils.EMPTY;
    private String rebatePlan = StringUtils.EMPTY;
    private String formulaMethodId = StringUtils.EMPTY;
    private String rebateAmount = StringUtils.EMPTY;
    private String columnName = StringUtils.EMPTY;
    private Integer caseNo = 0;
    private String contractHolder_SID = StringUtils.EMPTY;
    private String contractNo_SID = StringUtils.EMPTY;
    private String contractName_SID = StringUtils.EMPTY;
    private String cfp_SID = StringUtils.EMPTY;
    private String ifp_SID = StringUtils.EMPTY;
    private String ps_SID = StringUtils.EMPTY;
    private String rs_SID = StringUtils.EMPTY;
    private String customer_SID = StringUtils.EMPTY;

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

    public HelperDTO getMarketType_DTO() {
        return marketType_DTO;
    }

    public void setMarketType_DTO(HelperDTO marketType_DTO) {
        this.marketType_DTO = marketType_DTO;
    }

    public String getCfp() {
        return cfp;
    }

    public void setCfp(String cfp) {
        this.cfp = cfp;
    }

    public String getIfp() {
        return ifp;
    }

    public void setIfp(String ifp) {
        this.ifp = ifp;
    }

    public String getPriceSchedule() {
        return priceSchedule;
    }

    public void setPriceSchedule(String priceSchedule) {
        this.priceSchedule = priceSchedule;
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

    public String getRebateSchedule() {
        return rebateSchedule;
    }

    public void setRebateSchedule(String rebateSchedule) {
        this.rebateSchedule = rebateSchedule;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public HelperDTO getTypeDdlb() {
        return typeDdlb;
    }

    public void setTypeDdlb(HelperDTO typeDdlb) {
        this.typeDdlb = typeDdlb;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public List<ItemIndexDto> getSelectedItemList() {
        return selectedItemList;
    }

    public void setSelectedItemList(List<ItemIndexDto> selectedItemList) {
        this.selectedItemList = selectedItemList;
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

    public void setRsContractSid(Integer rsCOntractSid) {
        this.rsContractSid = rsCOntractSid;
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

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
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

    public HelperDTO getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(HelperDTO priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public HelperDTO getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(HelperDTO priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public HelperDTO getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(HelperDTO priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
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

    public void setRSEndDate(Date RSEndate) {
        this.RSEndDate = RSEndate;
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

    public String getContractHolder_SID() {
        return contractHolder_SID;
    }

    public void setContractHolder_SID(String contractHolder_SID) {
        this.contractHolder_SID = contractHolder_SID;
    }

    public String getContractNo_SID() {
        return contractNo_SID;
    }

    public void setContractNo_SID(String contractNo_SID) {
        this.contractNo_SID = contractNo_SID;
    }

    public String getContractName_SID() {
        return contractName_SID;
    }

    public void setContractName_SID(String contractName_SID) {
        this.contractName_SID = contractName_SID;
    }

    public String getCfp_SID() {
        return cfp_SID;
    }

    public void setCfp_SID(String cfp_SID) {
        this.cfp_SID = cfp_SID;
    }

    public String getIfp_SID() {
        return ifp_SID;
    }

    public void setIfp_SID(String ifp_SID) {
        this.ifp_SID = ifp_SID;
    }

    public String getPs_SID() {
        return ps_SID;
    }

    public void setPs_SID(String ps_SID) {
        this.ps_SID = ps_SID;
    }

    public String getRs_SID() {
        return rs_SID;
    }

    public void setRs_SID(String rs_SID) {
        this.rs_SID = rs_SID;
    }

    public String getCustomer_SID() {
        return customer_SID;
    }

    public void setCustomer_SID(String customer_SID) {
        this.customer_SID = customer_SID;
    }

}
