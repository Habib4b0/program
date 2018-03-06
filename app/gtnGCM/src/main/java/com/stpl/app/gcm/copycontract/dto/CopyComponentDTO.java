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
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String therapyClass = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String ifpStatus = StringUtils.EMPTY;
    private String ifpStartDate = StringUtils.EMPTY;
    private String ifpEndDate = StringUtils.EMPTY;
    private HelperDTO aliasType;
    private String aliasNumber = StringUtils.EMPTY;
    private Date aliasstartdate;
    private Date aliasenddate;
    private String CompanyId = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String companyStatus = StringUtils.EMPTY;
    private String companyStartDate = StringUtils.EMPTY;
    private String companyEndDate = StringUtils.EMPTY;
    private String priceType = StringUtils.EMPTY;
    private String ppStartDate = StringUtils.EMPTY;
    private String psStartDate = StringUtils.EMPTY;
    private String psEndDate = StringUtils.EMPTY;
    private String companyType = StringUtils.EMPTY;
    private String companyCategory = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String contractHolder = StringUtils.EMPTY;
    private String rebateScheduleId = StringUtils.EMPTY;
    private String rebateScheduleName = StringUtils.EMPTY;
    private String rebateScheduleAlias = StringUtils.EMPTY;
    private String rebateScheduleNo = StringUtils.EMPTY;
    private String value = StringUtils.EMPTY;
    private String massField = StringUtils.EMPTY;
    private String rsNo = StringUtils.EMPTY;
    private HelperDTO status;
    private String rebateFrequency = StringUtils.EMPTY;
    private String start = StringUtils.EMPTY;
    private String rarType = StringUtils.EMPTY;
    private String basis = StringUtils.EMPTY;
    private String end = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private String rebateScheduleType = StringUtils.EMPTY;
    private String rebateProgramType = StringUtils.EMPTY;
    private String rebateScheduleCategory = StringUtils.EMPTY;
    private HelperDTO marketType;
    private String rarCategory = StringUtils.EMPTY;
    private String field = StringUtils.EMPTY;
    private String componentSelection = StringUtils.EMPTY;
    private String CFPname = StringUtils.EMPTY;
    private String IFPname = StringUtils.EMPTY;
    private String PSname = StringUtils.EMPTY;
    private String RSname = StringUtils.EMPTY;
    private String contractStartDate = StringUtils.EMPTY;
    private String contractEndDate = StringUtils.EMPTY;
    private String ifp = StringUtils.EMPTY;
    private String ps = StringUtils.EMPTY;
    private String rs = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;
    private String rebatePlan = StringUtils.EMPTY;
    private String rsStatus = StringUtils.EMPTY;
    private String rsStartDate = StringUtils.EMPTY;
    private String rsEndDate = StringUtils.EMPTY;
    private String category = StringUtils.EMPTY;
    private String dashboardId = StringUtils.EMPTY;
    private String dashboardNumber = StringUtils.EMPTY;
    private String dashboardName = StringUtils.EMPTY;
    private String hiddenId = StringUtils.EMPTY;
    private String savedSystemId = StringUtils.EMPTY;
    private String modelId = StringUtils.EMPTY;
    private String contractId = StringUtils.EMPTY;
    private String contractHolderName = StringUtils.EMPTY;
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

    private Integer CFPId = 0;
    private Integer IFPId = 0;
    private Integer PSId = 0;
    private Integer RSId = 0;
    private String levelNo = Constants.ZEROSTRING;
    private boolean check = false;

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
        return aliasstartdate == null ? null : (Date) aliasstartdate.clone();
    }

    public void setAliasstartdate(Date aliasstartdate) {
        this.aliasstartdate = aliasstartdate == null ? null : (Date) aliasstartdate.clone();
    }

    public Date getAliasenddate() {
        return aliasenddate == null ? null : (Date) aliasenddate.clone();
    }

    public void setAliasenddate(Date aliasenddate) {
        this.aliasenddate = aliasenddate == null ? null : (Date) aliasenddate.clone();
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
