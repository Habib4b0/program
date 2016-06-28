/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class ComponentInfoDTO implements Serializable {

    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String therapyClass = StringUtils.EMPTY;
    private String rebatePlan = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;
    private Date itemStartDate;
    private Date itemEndDate;
    private String brand = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private HelperDTO statusId ;
    private boolean checkRecord = false;
    private boolean checkAll = false;
    private String itemId = StringUtils.EMPTY;
    private String form = StringUtils.EMPTY;
    private String strength = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    private String rebateScheduleId = StringUtils.EMPTY;
    private String rebateScheduleNumber = StringUtils.EMPTY;
    private String rebateScheduleName = StringUtils.EMPTY;
    private String rarType = StringUtils.EMPTY;
    private String rsType = StringUtils.EMPTY;
    private String rebateFrequency = StringUtils.EMPTY;
    private String basis = StringUtils.EMPTY;
    private String frequency = StringUtils.EMPTY;
    private String id = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    private String endDate = StringUtils.EMPTY;
    private String ifpName = StringUtils.EMPTY;
    private String type = StringUtils.EMPTY;
    private String psCategory = StringUtils.EMPTY;
    private String designation = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private String searchField = StringUtils.EMPTY;
    private String searchFieldValue = StringUtils.EMPTY;
    private Integer count = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private Boolean isCount = false;
    private String rsSid = StringUtils.EMPTY;
    private String ifpSystemId = StringUtils.EMPTY;
    private String category = StringUtils.EMPTY;
    private Integer internalId = 0;
    private String psSid = StringUtils.EMPTY;
    private Integer cfpId = 0;
    private String ifpId = StringUtils.EMPTY;
    private Integer modelSysId = 0;
    private String contractId = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private Integer systemId = 0;
    private String levelNo = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private String contractHolder = StringUtils.EMPTY;
    private String cfpStartDate = StringUtils.EMPTY;
    private String cfpEndDate = StringUtils.EMPTY;
    private String aliasStartDate = StringUtils.EMPTY;
    private String aliasEndDate = StringUtils.EMPTY;
    private String cfpName = StringUtils.EMPTY;
    private String hiddenId = "0";
    private String tpNo = StringUtils.EMPTY;
    private String tpName = StringUtils.EMPTY;
    private String tpContractNo = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private String attachedDate = StringUtils.EMPTY;
    private String therapeuticClass = StringUtils.EMPTY;
    private String priceType = StringUtils.EMPTY;
    private String pricePlanNo = StringUtils.EMPTY;
    private String pricePlanName = StringUtils.EMPTY;
    private String priceProtectionStatus = StringUtils.EMPTY;
    private String priceProtectionStartDate = StringUtils.EMPTY;
    private String priceProtectionEndDate = StringUtils.EMPTY;
    private String priceProtectionPriceType = StringUtils.EMPTY;
    private String priceToleranceInterval = StringUtils.EMPTY;
    private String priceToleranceFrequency = StringUtils.EMPTY;
    private String priceToleranceType = StringUtils.EMPTY;
    private String maxIncrementalChange = StringUtils.EMPTY;
    private String priceTolerance = StringUtils.EMPTY;
    private String resetEligible = StringUtils.EMPTY;
    private String resetType = StringUtils.EMPTY;
    private String resetDate = StringUtils.EMPTY;
    private String resetInterval = StringUtils.EMPTY;
    private String resetFrequency = StringUtils.EMPTY;
    private String dashboardId = StringUtils.EMPTY;
    private String dashboardNumber = StringUtils.EMPTY;
    private String dashboardName = StringUtils.EMPTY;
    String modelId = StringUtils.EMPTY;
    private String companyStatus = StringUtils.EMPTY;
    private String companyStartDate = StringUtils.EMPTY;
    private String companyEndDate = StringUtils.EMPTY;
    private String ifpStatus = StringUtils.EMPTY;
    private String ifpStartDate = StringUtils.EMPTY;
    private String ifpEndDate = StringUtils.EMPTY;
    private String ppStartDate = StringUtils.EMPTY;

    private String formulaType = StringUtils.EMPTY;
    private String formulaName = StringUtils.EMPTY;
    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String rebateAmount = StringUtils.EMPTY;
    private String bundleNo = StringUtils.EMPTY;
    private String itemMasterId = StringUtils.EMPTY;
    private String componentValue=StringUtils.EMPTY;
    
    private String cfpNo=StringUtils.EMPTY;
    private int cfpType=0;
    private String cfpStatus=StringUtils.EMPTY;
    private ComponentInfoDTO parent4;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private List<ComponentInfoDTO> ifpList=new ArrayList<ComponentInfoDTO>();
    private List<ComponentInfoDTO> rsList=new ArrayList<ComponentInfoDTO>();
    private List<ComponentInfoDTO> psList=new ArrayList<ComponentInfoDTO>();
    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
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

    public HelperDTO getStatusId() {
        return statusId;
    }

    public void setStatusId(HelperDTO statusId) {
        this.statusId = statusId;
    }

    public String getItemMasterId() {
        return itemMasterId;
    }

    public void setItemMasterId(String itemMasterId) {
        this.itemMasterId = itemMasterId;
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

    public String getPpStartDate() {
        return ppStartDate;
    }

    public void setPpStartDate(String ppStartDate) {
        this.ppStartDate = ppStartDate;
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

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
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

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(String hiddenId) {
        this.hiddenId = hiddenId;
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

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
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

    public String getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(String priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
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

    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
    }

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
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

    public String getResetDate() {
        return resetDate;
    }

    public void setResetDate(String resetDate) {
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

    public String getTpNo() {
        return tpNo;
    }

    public void setTpNo(String tpNo) {
        this.tpNo = tpNo;
    }

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName) {
        this.tpName = tpName;
    }

    public String getTpContractNo() {
        return tpContractNo;
    }

    public void setTpContractNo(String tpContractNo) {
        this.tpContractNo = tpContractNo;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(String attachedDate) {
        this.attachedDate = attachedDate;
    }

    public String getCfpName() {
        return cfpName;
    }

    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }

    public String getCfpStartDate() {
        return cfpStartDate;
    }

    public void setCfpStartDate(String cfpStartDate) {
        this.cfpStartDate = cfpStartDate;
    }

    public String getCfpEndDate() {
        return cfpEndDate;
    }

    public void setCfpEndDate(String cfpEndDate) {
        this.cfpEndDate = cfpEndDate;
    }

    public String getAliasStartDate() {
        return aliasStartDate;
    }

    public void setAliasStartDate(String aliasStartDate) {
        this.aliasStartDate = aliasStartDate;
    }

    public String getAliasEndDate() {
        return aliasEndDate;
    }

    public void setAliasEndDate(String aliasEndDate) {
        this.aliasEndDate = aliasEndDate;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }
    /**
     * Static Final Variable LEVEL1.
     */
    public static final int LEVEL1 = 1;
    /**
     * Static Final Variable LEVEL2.
     */
    public static final int LEVEL2 = 2;
    /**
     * Static Final Variable LEVEL3.
     */
    public static final int LEVEL3 = 3;
    /**
     * Static Final Variable LEVEL4.
     */
    public static final int LEVEL4 = 4;
    /**
     * Static Final Variable LEVEL5.
     */
    public static final int LEVEL5 = 5;
    private Integer level;
    /**
     * Variable used for PARENT1.
     */
    private ComponentInfoDTO parent1;
    /**
     * Variable used for PARENT2.
     */
    private ComponentInfoDTO parent2;
    /**
     * Variable used for PARENT3.
     */
    private ComponentInfoDTO parent3;
    /**
     * Variable used for PARENT4.
     */

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

    public ComponentInfoDTO getParent1() {
        return parent1;
    }

    public void setParent1(ComponentInfoDTO parent1) {
        this.parent1 = parent1;
    }

    public ComponentInfoDTO getParent2() {
        return parent2;
    }

    public void setParent2(ComponentInfoDTO parent2) {
        this.parent2 = parent2;
    }

    public ComponentInfoDTO getParent3() {
        return parent3;
    }

    public void setParent3(ComponentInfoDTO parent3) {
        this.parent3 = parent3;
    }

    public ComponentInfoDTO getParent4() {
        return parent4;
    }

    public void setParent4(ComponentInfoDTO parent4) {
        this.parent4 = parent4;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
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

    public Integer getModelSysId() {
        return modelSysId;
    }

    public void setModelSysId(Integer modelSysId) {
        this.modelSysId = modelSysId;
    }

    public Boolean getIsCount() {
        return isCount;
    }

    public void setIsCount(Boolean isCount) {
        this.isCount = isCount;
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

    public String getRsSid() {
        return rsSid;
    }

    public void setRsSid(String rsSid) {
        this.rsSid = rsSid;
    }

    public String getIfpSystemId() {
        return ifpSystemId;
    }

    public void setIfpSystemId(String ifpSystemId) {
        this.ifpSystemId = ifpSystemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getInternalId() {
        return internalId;
    }

    public void setInternalId(Integer internalId) {
        this.internalId = internalId;
    }

    public String getPsSid() {
        return psSid;
    }

    public void setPsSid(String psSid) {
        this.psSid = psSid;
    }

    public Integer getCfpId() {
        return cfpId;
    }

    public void setCfpId(Integer cfpId) {
        this.cfpId = cfpId;
    }

    public String getIfpId() {
        return ifpId;
    }

    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchFieldValue() {
        return searchFieldValue;
    }

    public void setSearchFieldValue(String searchFieldValue) {
        this.searchFieldValue = searchFieldValue;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPsCategory() {
        return psCategory;
    }

    public void setPsCategory(String psCategory) {
        this.psCategory = psCategory;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRebateScheduleId() {
        return rebateScheduleId;
    }

    public void setRebateScheduleId(String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    public String getRebateScheduleNumber() {
        return rebateScheduleNumber;
    }

    public void setRebateScheduleNumber(String rebateScheduleNumber) {
        this.rebateScheduleNumber = rebateScheduleNumber;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    public String getRarType() {
        return rarType;
    }

    public void setRarType(String rarType) {
        this.rarType = rarType;
    }

    public String getRebateFrequency() {
        return rebateFrequency;
    }

    public void setRebateFrequency(String rebateFrequency) {
        this.rebateFrequency = rebateFrequency;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
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

    public String getRebatePlan() {
        return rebatePlan;
    }

    public void setRebatePlan(String rebatePlan) {
        this.rebatePlan = rebatePlan;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
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

    public String getComponentValue() {
        return componentValue;
}

    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    public String getCfpNo() {
        return cfpNo;
    }

    public void setCfpNo(String cfpNo) {
        this.cfpNo = cfpNo;
    }

    public int getCfpType() {
        return cfpType;
    }

    public void setCfpType(int cfpType) {
        this.cfpType = cfpType;
    }

    public String getCfpStatus() {
        return cfpStatus;
    }

    public void setCfpStatus(String cfpStatus) {
        this.cfpStatus = cfpStatus;
    }

    public List<ComponentInfoDTO> getIfpList() {
        return ifpList;
    }
 
    public void setIfpList(List<ComponentInfoDTO> ifpList) {
        this.ifpList = ifpList;
    }
 
    public List<ComponentInfoDTO> getRsList() {
        return rsList;
}

    public void setRsList(List<ComponentInfoDTO> rsList) {
        this.rsList = rsList;
    }

    public List<ComponentInfoDTO> getPsList() {
        return psList;
    }

    public void setPsList(List<ComponentInfoDTO> psList) {
        this.psList = psList;
    }
    
    public boolean isCheckAll() {
        return checkAll;
    }

    public void setCheckAll(boolean checkAll) {
        this.checkAll = checkAll;
    }
}