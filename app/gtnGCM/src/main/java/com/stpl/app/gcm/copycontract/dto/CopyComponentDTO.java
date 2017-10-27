/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dto;

import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author nandhakumar
 */
public class CopyComponentDTO implements Serializable {

    String itemNo = StringUtils.EMPTY;
    String itemName = StringUtils.EMPTY;
    String therapyClass = StringUtils.EMPTY;
    String brand = StringUtils.EMPTY;
    String ifpStatus = StringUtils.EMPTY;
    String ifpStartDate = StringUtils.EMPTY;
    String ifpEndDate = StringUtils.EMPTY;
    HelperDTO aliasType ;
    String aliasNumber = StringUtils.EMPTY;
    Date aliasstartdate;
    Date aliasenddate;
    String CompanyId = StringUtils.EMPTY;
    String companyNo = StringUtils.EMPTY;
    String companyName = StringUtils.EMPTY;
    String companyStatus = StringUtils.EMPTY;
    String companyStartDate = StringUtils.EMPTY;
    String companyEndDate = StringUtils.EMPTY;
    String priceType = StringUtils.EMPTY;
    String ppStartDate = StringUtils.EMPTY;
    String psStartDate = StringUtils.EMPTY;
    String psEndDate = StringUtils.EMPTY;
    String companyType = StringUtils.EMPTY;
    String companyCategory = StringUtils.EMPTY;
    String tradeClass = StringUtils.EMPTY;
    String contractNo = StringUtils.EMPTY;
    String contractName = StringUtils.EMPTY;
    String contractHolder = StringUtils.EMPTY;
    String rebateScheduleId = StringUtils.EMPTY;
    String rebateScheduleName = StringUtils.EMPTY;
    String rebateScheduleAlias = StringUtils.EMPTY;
    String rebateScheduleNo = StringUtils.EMPTY;
    String value = StringUtils.EMPTY;
    String massField = StringUtils.EMPTY;
    String rsNo = StringUtils.EMPTY;
    HelperDTO status;
    String rebateFrequency = StringUtils.EMPTY;
    String start = StringUtils.EMPTY;
    String rarType = StringUtils.EMPTY;
    String basis = StringUtils.EMPTY;
    String end = StringUtils.EMPTY;
    Date startDate;
    Date endDate;
    String rebateScheduleType = StringUtils.EMPTY;
    String rebateProgramType = StringUtils.EMPTY;
    String rebateScheduleCategory = StringUtils.EMPTY;
    HelperDTO marketType;
    String rarCategory = StringUtils.EMPTY;
    String field = StringUtils.EMPTY;
    String componentSelection = StringUtils.EMPTY;
    String CFPname = StringUtils.EMPTY;
    String IFPname = StringUtils.EMPTY;
    String PSname = StringUtils.EMPTY;
    String RSname = StringUtils.EMPTY;
    String contractStartDate = StringUtils.EMPTY;
    String contractEndDate = StringUtils.EMPTY;
    String ifp = StringUtils.EMPTY;
    String ps = StringUtils.EMPTY;
    String rs = StringUtils.EMPTY;
    String formulaId = StringUtils.EMPTY;
    String rebatePlan = StringUtils.EMPTY;
    String rsStatus = StringUtils.EMPTY;
    String rsStartDate = StringUtils.EMPTY;
    String rsEndDate = StringUtils.EMPTY;
    String category = StringUtils.EMPTY;
    String dashboardId = StringUtils.EMPTY;
    String dashboardNumber = StringUtils.EMPTY;
    String dashboardName = StringUtils.EMPTY;
    String hiddenId = StringUtils.EMPTY;
    String savedSystemId = StringUtils.EMPTY;
    String modelId = StringUtils.EMPTY;
    String contractId = StringUtils.EMPTY;
    String contractHolderName = StringUtils.EMPTY;
    private  String attachedDate=StringUtils.EMPTY;
    private String pricePlanNo = StringUtils.EMPTY;
    private String pricePlanName = StringUtils.EMPTY;
    private String priceProtectionStatus = StringUtils.EMPTY;
    private String priceProtectionEndDate= StringUtils.EMPTY;
    private String priceProtectionPriceType = StringUtils.EMPTY;
    private String priceToleranceInterval = StringUtils.EMPTY;
    private String priceToleranceFrequency = StringUtils.EMPTY;
    private String priceToleranceType = StringUtils.EMPTY;
    private String priceTolerance = StringUtils.EMPTY;
    private String maxIncrementalChange = StringUtils.EMPTY;
    private String reset = StringUtils.EMPTY;
    private String eligibility = StringUtils.EMPTY;
    private String resetType = StringUtils.EMPTY;
    private String resetDate = StringUtils.EMPTY;
    private String resetIntervel = StringUtils.EMPTY;
    private String resetFrequency = StringUtils.EMPTY;
    private String formulaType = StringUtils.EMPTY;
    private String formulaName = StringUtils.EMPTY;
    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String rebateAmount = StringUtils.EMPTY;
    private String bundleNo = StringUtils.EMPTY;
    private String priceProtectionStartDate= StringUtils.EMPTY;
    private String tpNo=StringUtils.EMPTY;
    private String addBy=StringUtils.EMPTY;


    Integer CFPId = 0;
    Integer IFPId = 0;
    Integer PSId = 0;
    Integer RSId = 0;
    String levelNo = Constants.ZEROSTRING;
    Boolean check = false;

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
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

    public String getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(String ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public String getIfpStartDate() {
        return ifpStartDate;
    }

    public void setIfpStartDate(String ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }

    public String getIfpEndDate() {
        return ifpEndDate;
    }

    public void setIfpEndDate(String ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getCompanyEndDate() {
        return companyEndDate;
    }

    public void setCompanyEndDate(String companyEndDate) {
        this.companyEndDate = companyEndDate;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPpStartDate() {
        return ppStartDate;
    }

    public void setPpStartDate(String ppStartDate) {
        this.ppStartDate = ppStartDate;
    }

    public String getPsStartDate() {
        return psStartDate;
    }

    public void setPsStartDate(String psStartDate) {
        this.psStartDate = psStartDate;
    }

    public String getPsEndDate() {
        return psEndDate;
    }

    public void setPsEndDate(String psEndDate) {
        this.psEndDate = psEndDate;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
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

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
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

    public String getRebateScheduleAlias() {
        return rebateScheduleAlias;
    }

    public void setRebateScheduleAlias(String rebateScheduleAlias) {
        this.rebateScheduleAlias = rebateScheduleAlias;
    }

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassField() {
        return massField;
    }

    public void setMassField(String massField) {
        this.massField = massField;
    }

    public String getRsNo() {
        return rsNo;
    }

    public void setRsNo(String rsNo) {
        this.rsNo = rsNo;
    }

    public HelperDTO getStatus() {
        return status;
    }

    public void setStatus(HelperDTO status) {
        this.status = status;
    }

    public String getRebateFrequency() {
        return rebateFrequency;
    }

    public void setRebateFrequency(String rebateFrequency) {
        this.rebateFrequency = rebateFrequency;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
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

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public String getRebateScheduleCategory() {
        return rebateScheduleCategory;
    }

    public void setRebateScheduleCategory(String rebateScheduleCategory) {
        this.rebateScheduleCategory = rebateScheduleCategory;
    }

    public HelperDTO getMarketType() {
        return marketType;
    }

    public void setMarketType(HelperDTO marketType) {
        this.marketType = marketType;
    }

    public String getRarCategory() {
        return rarCategory;
    }

    public void setRarCategory(String rarCategory) {
        this.rarCategory = rarCategory;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getComponentSelection() {
        return componentSelection;
    }

    public void setComponentSelection(String componentSelection) {
        this.componentSelection = componentSelection;
    }

    public String getCFPname() {
        return CFPname;
    }

    public void setCFPname(String CFPname) {
        this.CFPname = CFPname;
    }

    public String getIFPname() {
        return IFPname;
    }

    public void setIFPname(String IFPname) {
        this.IFPname = IFPname;
    }

    public String getPSname() {
        return PSname;
    }

    public void setPSname(String PSname) {
        this.PSname = PSname;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getIfp() {
        return ifp;
    }

    public void setIfp(String ifp) {
        this.ifp = ifp;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getRSname() {
        return RSname;
    }

    public void setRSname(String RSname) {
        this.RSname = RSname;
    }

    public Integer getCFPId() {
        return CFPId;
    }

    public void setCFPId(Integer CFPId) {
        this.CFPId = CFPId;
    }

    public Integer getIFPId() {
        return IFPId;
    }

    public void setIFPId(Integer IFPId) {
        this.IFPId = IFPId;
    }

    public Integer getPSId() {
        return PSId;
    }

    public void setPSId(Integer PSId) {
        this.PSId = PSId;
    }

    public Integer getRSId() {
        return RSId;
    }

    public void setRSId(Integer RSId) {
        this.RSId = RSId;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getDashboardNumber() {
        return dashboardNumber;
    }

    public void setDashboardNumber(String dashboardNumber) {
        this.dashboardNumber = dashboardNumber;
    }

    public String getDashboardName() {
        return dashboardName;
    }

    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }

    public String getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(String hiddenId) {
        this.hiddenId = hiddenId;
    }

    public String getSavedSystemId() {
        return savedSystemId;
    }

    public void setSavedSystemId(String savedSystemId) {
        this.savedSystemId = savedSystemId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public HelperDTO getAliasType() {
        return aliasType;
    }

    public void setAliasType(HelperDTO aliasType) {
        this.aliasType = aliasType;
    }

    public String getAliasNumber() {
        return aliasNumber;
    }

    public void setAliasNumber(String aliasNumber) {
        this.aliasNumber = aliasNumber;
    }

    public Date getAliasstartdate() {
        return aliasstartdate;
    }

    public void setAliasstartdate(Date aliasstartdate) {
        this.aliasstartdate = aliasstartdate;
    }

    public Date getAliasenddate() {
        return aliasenddate;
    }

    public void setAliasenddate(Date aliasenddate) {
        this.aliasenddate = aliasenddate;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractHolderName() {
        return contractHolderName;
    }

    public void setContractHolderName(String contractHolderName) {
        this.contractHolderName = contractHolderName;
    }

    public String getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(String attachedDate) {
        this.attachedDate = attachedDate;
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

    public String getPriceProtectionEndDate() {
        return priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(String priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate;
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

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getResetDate() {
        return resetDate;
    }

    public void setResetDate(String resetDate) {
        this.resetDate = resetDate;
    }

    public String getResetIntervel() {
        return resetIntervel;
    }

    public void setResetIntervel(String resetIntervel) {
        this.resetIntervel = resetIntervel;
    }

    public String getResetFrequency() {
        return resetFrequency;
    }

    public void setResetFrequency(String resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
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

    public String getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(String priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }

    public String getTpNo() {
        return tpNo;
    }

    public void setTpNo(String tpNo) {
        this.tpNo = tpNo;
    }
    
    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }
}
