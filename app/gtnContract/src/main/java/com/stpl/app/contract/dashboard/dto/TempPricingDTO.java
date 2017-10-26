
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.HelperUtils;
import com.vaadin.ui.Field;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;


public class TempPricingDTO implements Serializable {
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -3119001486984120288L;
    /**
     * The cp end date.
     */
    private Date cpEndDate;
    /**
     * The company family plan system id.
     */
    private String companyFamilyPlanSystemId;
    /**
     * The price schedule system id.
     */
    private String priceScheduleSystemId;
    /**
     * The item familyplan system id.
     */
    private String itemFamilyplanSystemId;
    /**
     * The pp start date.
     */
    private Date ppStartDate;
    /**
     * The item system id.
     */
    private String itemSystemId;
    /**
     * The contractprice.
     */
    private String contractprice = HelperUtils.EMPTY;
    /**
     * The price tolerance frequency.
     */
    private String priceToleranceFrequency = HelperUtils.EMPTY;
    /**
     * The end date.
     */
    private Date endDate;
    /**
     * The cp start date.
     */
    private Date cpStartDate;
    /**
     * The base price.
     */
    private String basePrice = HelperUtils.EMPTY;
    /**
     * The price.
     */
    private String price = HelperUtils.EMPTY;
    /**
     * The globalitemstatus.
     */
    private String globalitemstatus;
    /**
     * The price tolerance.
     */
    private String priceTolerance = HelperUtils.EMPTY;
    /**
     * The price type.
     */
    private HelperDTO priceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The price type.
     */
    private String priceTypeDesc = StringUtils.EMPTY;

    /**
     * The attached date.
     */
    private Date attachedDate;
    /**
     * The revision date.
     */
    private Date revisionDate;
    /**
     * The attached status.
     */
    private String attachedStatus = HelperUtils.EMPTY;
    /**
     * The start date.
     */
    private Date startDate;
    /**
     * The pp end date.
     */
    private Date ppEndDate;
    /**
     * The price tolerance interval.
     */
    private String priceToleranceInterval = HelperUtils.EMPTY;
    /**
     * The item name.
     */
    private String itemName = HelperUtils.EMPTY;
    /**
     * The primary uom.
     */
    private String primaryUom = HelperUtils.EMPTY;
    /**
     * The secondary uom.
     */
    private String secondaryUom = HelperUtils.EMPTY;
    /**
     * The item no.
     */
    private String itemNo = HelperUtils.EMPTY;
    /**
     * The package size.
     */
    private String packageSize = HelperUtils.EMPTY;
    /**
     * The item id.
     */
    private String itemId = HelperUtils.EMPTY;
    /**
     * The price tolerance type.
     */
    private String priceToleranceType = HelperUtils.EMPTY;
    /**
     * The contract system id.
     */
    private String contractSystemId;
    /**
     * The ifp details system id.
     */
    private String ifpDetailsSystemId;
    /**
     * The ps details system id.
     */
    private String psDetailsSystemId;
    /**
     * The unique date.
     */
    private String uniqueDate;
    /**
     * The checkbox.
     */
    private Boolean checkbox;
    
    private String tempItemPriceRebateSystemId;
    
    /** System generated field in Price Schedule details. */
    private String priceScheduleDetailsSystemId;
    
    /** Price Protection Start Date Field. */
    private Date priceProtectionStartDate;
    
    /** Price Protection End Date Field. */
    private Date priceProtectionEndDate;
    
    /** priceToleranceTypeValue. */
    private String priceToleranceTypeValue = StringUtils.EMPTY;

    /** priceToleranceFrequencyValue. */
    private String priceToleranceIntervalValue = StringUtils.EMPTY;

    /** Price Tolerance Frequency Field. */
    private String priceToleranceFrequencyValue = StringUtils.EMPTY;
    
    /** Contract Price Field. */
    private String contractPrice = StringUtils.EMPTY;
    
    /** Contract Price Start Date Field. */
    private Date contractPriceStartDate;
    
    /** Contract Price End Date Field. */
    private Date contractPriceEndDate;
   
    /** The temp ps details system id. */
    private int tempPsDetailsSystemId;
    /**
     * The userID.
     */
    private String userID = StringUtils.EMPTY;
    /**
     * The sessionID.
     */
    private String sessionID = StringUtils.EMPTY;
    /**
     * The createdBy.
     */
    private String createdBy = StringUtils.EMPTY;
    /**
     * The createdDate.
     */
    private Date createdDate;

    /**
     * The modifiedBy.
     */
    private String modifiedBy = StringUtils.EMPTY;
    /**
     * The modifiedDate.
     */
    private Date modifiedDate;

    /**
     * The operationFlag.
     */
    private String operation;

    /** The item id. */
    private String itemID = StringUtils.EMPTY;

    /** The brand. */
    private String brand = StringUtils.EMPTY;
    
    private String suggestedPrice = StringUtils.EMPTY;
    
    private String source = StringUtils.EMPTY;

    /** The price protection status. */
    private HelperDTO priceProtectionStatus = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The price protection price type. */
    private HelperDTO priceProtectionPriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The nep. */
    private String nep = StringUtils.EMPTY;

    /** The nep formula. */
    private String nepFormula = StringUtils.EMPTY;
    /** The nep formula. */
    private int nepFormulaId ;
    /** The nep formula. */
    private String nepFormulaName = StringUtils.EMPTY;

    /** The max incremental change. */
    private String maxIncrementalChange = StringUtils.EMPTY;

    /** The reset eligible. */
    private HelperDTO resetEligible=new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The reset type. */
    private HelperDTO resetType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The reset date. */
    private Date resetDate;

    /** The reset interval. */
    private HelperDTO resetInterval = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The reset frequency. */
    private HelperDTO resetFrequency = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The net price type. */
    private HelperDTO netPriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The net price type formula. */
    private String netPriceTypeFormula = StringUtils.EMPTY;
    /** The net price type formula. */
    private String netPriceTypeFormulaID = StringUtils.EMPTY;
    
    private String brandMasterSID = StringUtils.EMPTY;
    
    private HelperDTO itemStatus = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private String pricePlanID = StringUtils.EMPTY;
    private String pricePlanName = StringUtils.EMPTY;
    
    private String createdUserName = StringUtils.EMPTY;
    /** The nep formula. */
    private String nepFormulaSid = StringUtils.EMPTY;
    
    private HelperDTO ppPriceToleranceInterval = new HelperDTO(tempPsDetailsSystemId, operation);
    
    private HelperDTO ppPriceToleranceFrequency = new HelperDTO(tempPsDetailsSystemId, operation);
    
    private HelperDTO ppPriceToleranceType = new HelperDTO(tempPsDetailsSystemId, operation);
    
    private HelperDTO basePriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private Object basePriceValue;
    
    private String basePriceEntry = StringUtils.EMPTY;

    
    private Date basePriceDate;
    
    private HelperDTO basePriceItemPriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO netBasePrice =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);  
    
    private String netBasePriceFormula;

    private int netBasePriceFormulaID;
    
    private HelperDTO subsequentPeriodPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO netSubsequentPeriodPrice =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private String netSubsequentPriceFormula;
    
    private int netSubsequentPriceFormulaID;
    
    private HelperDTO resetPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO netResetPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    private String netResetPriceFormula;
    
    private int netResetPriceFormulaID;     
    
    private HelperDTO selectedBasePrice = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    private Map<String,Field> dtoFields = new HashMap<String,Field>();

    private HelperDTO ifpStatus = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private String itemDesc = StringUtils.EMPTY;
    private String form = StringUtils.EMPTY;
    private String strength = StringUtils.EMPTY;
    private String therapyClass = StringUtils.EMPTY;
    private String recordType = StringUtils.EMPTY;
    private String itemFamilyplanId = StringUtils.EMPTY;
    private String itemFamilyplanNo = StringUtils.EMPTY;
    private String itemFamilyplanName = StringUtils.EMPTY;
    private String parentItemFamilyplanId = StringUtils.EMPTY;
    private String parentItemFamilyplanName = StringUtils.EMPTY;
    private HelperDTO itemFamilyplanDesignation= new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private HelperDTO itemFamilyplanCategory = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private HelperDTO itemFamilyplanType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private HelperDTO itemFamilyplanStatus = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private String ifpCategory= StringUtils.EMPTY;
    private String ifpType= StringUtils.EMPTY;
    private String ifPStatus =  StringUtils.EMPTY;
     private String ifpDesignation =  StringUtils.EMPTY;
    private String itemsStatus=  StringUtils.EMPTY;

    public Map getDtoFields() {
        return dtoFields;
    }

    public void setDtoFields(Map dtoFields) {
        this.dtoFields = dtoFields;
    }

    public Field getDTOValue(String propertyId) {
        return dtoFields.get(propertyId);
    }

    public Field addDTOValue(String propertyId,Field field) {
        return dtoFields.put(propertyId, field);
    }
    
    public Boolean contains(String propertyId){
        return dtoFields.containsKey(propertyId);
    }

    public Date getCpEndDate() {
        return cpEndDate;
    }

    public void setCpEndDate(Date cpEndDate) {
        this.cpEndDate = cpEndDate;
    }

    public String getCompanyFamilyPlanSystemId() {
        return companyFamilyPlanSystemId;
    }

    public void setCompanyFamilyPlanSystemId(String companyFamilyPlanSystemId) {
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
    }

    public String getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    public void setPriceScheduleSystemId(String priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    public String getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    public void setItemFamilyplanSystemId(String itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    public Date getPpStartDate() {
        return ppStartDate;
    }

    public void setPpStartDate(Date ppStartDate) {
        this.ppStartDate = ppStartDate;
    }

    public String getItemSystemId() {
        return itemSystemId;
    }

    public void setItemSystemId(String itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    public String getContractprice() {
        return contractprice;
    }

    public void setContractprice(String contractprice) {
        this.contractprice = contractprice;
    }

    public String getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(String priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCpStartDate() {
        return cpStartDate;
    }

    public void setCpStartDate(Date cpStartDate) {
        this.cpStartDate = cpStartDate;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGlobalitemstatus() {
        return globalitemstatus;
    }

    public void setGlobalitemstatus(String globalitemstatus) {
        this.globalitemstatus = globalitemstatus;
    }

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    public HelperDTO getPriceType() {
        return priceType;
    }

    public void setPriceType(HelperDTO priceType) {
        this.priceType = priceType;
    }

    public Date getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getAttachedStatus() {
        return attachedStatus;
    }

    public void setAttachedStatus(String attachedStatus) {
        this.attachedStatus = attachedStatus;
    }

    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getPpEndDate() {
        return ppEndDate;
    }

    public void setPpEndDate(Date ppEndDate) {
        this.ppEndDate = ppEndDate;
    }

    public String getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(String priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrimaryUom() {
        return primaryUom;
    }

    public void setPrimaryUom(String primaryUom) {
        this.primaryUom = primaryUom;
    }

    public String getSecondaryUom() {
        return secondaryUom;
    }

    public void setSecondaryUom(String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(String priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public String getContractSystemId() {
        return contractSystemId;
    }

    public void setContractSystemId(String contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    public String getIfpDetailsSystemId() {
        return ifpDetailsSystemId;
    }

    public void setIfpDetailsSystemId(String ifpDetailsSystemId) {
        this.ifpDetailsSystemId = ifpDetailsSystemId;
    }

    public String getPsDetailsSystemId() {
        return psDetailsSystemId;
    }

    public void setPsDetailsSystemId(String psDetailsSystemId) {
        this.psDetailsSystemId = psDetailsSystemId;
    }

    public String getUniqueDate() {
        return uniqueDate;
    }

    public void setUniqueDate(String uniqueDate) {
        this.uniqueDate = uniqueDate;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getTempItemPriceRebateSystemId() {
        return tempItemPriceRebateSystemId;
    }

    public void setTempItemPriceRebateSystemId(String tempItemPriceRebateSystemId) {
        this.tempItemPriceRebateSystemId = tempItemPriceRebateSystemId;
    }

    public String getPriceScheduleDetailsSystemId() {
        return priceScheduleDetailsSystemId;
    }

    public void setPriceScheduleDetailsSystemId(String priceScheduleDetailsSystemId) {
        this.priceScheduleDetailsSystemId = priceScheduleDetailsSystemId;
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

    public String getPriceToleranceTypeValue() {
        return priceToleranceTypeValue;
    }

    public void setPriceToleranceTypeValue(String priceToleranceTypeValue) {
        this.priceToleranceTypeValue = priceToleranceTypeValue;
    }

    public String getPriceToleranceIntervalValue() {
        return priceToleranceIntervalValue;
    }

    public void setPriceToleranceIntervalValue(String priceToleranceIntervalValue) {
        this.priceToleranceIntervalValue = priceToleranceIntervalValue;
    }

    public String getPriceToleranceFrequencyValue() {
        return priceToleranceFrequencyValue;
    }

    public void setPriceToleranceFrequencyValue(String priceToleranceFrequencyValue) {
        this.priceToleranceFrequencyValue = priceToleranceFrequencyValue;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Date getContractPriceStartDate() {
        return contractPriceStartDate;
    }

    public void setContractPriceStartDate(Date contractPriceStartDate) {
        this.contractPriceStartDate = contractPriceStartDate;
    }

    public Date getContractPriceEndDate() {
        return contractPriceEndDate;
    }

    public void setContractPriceEndDate(Date contractPriceEndDate) {
        this.contractPriceEndDate = contractPriceEndDate;
    }

    public int getTempPsDetailsSystemId() {
        return tempPsDetailsSystemId;
    }

    public void setTempPsDetailsSystemId(int tempPsDetailsSystemId) {
        this.tempPsDetailsSystemId = tempPsDetailsSystemId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(String suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public HelperDTO getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    public void setPriceProtectionStatus(HelperDTO priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
    }

    public HelperDTO getPriceProtectionPriceType() {
        return priceProtectionPriceType;
    }

    public void setPriceProtectionPriceType(HelperDTO priceProtectionPriceType) {
        this.priceProtectionPriceType = priceProtectionPriceType;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public String getNepFormula() {
        return nepFormula;
    }

    public void setNepFormula(String nepFormula) {
        this.nepFormula = nepFormula;
    }

    public int getNepFormulaId() {
        return nepFormulaId;
    }

    public void setNepFormulaId(int nepFormulaId) {
        this.nepFormulaId = nepFormulaId;
    }

    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
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

    public Date getResetDate() {
        return resetDate;
    }

    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
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

    public HelperDTO getNetPriceType() {
        return netPriceType;
    }

    public void setNetPriceType(HelperDTO netPriceType) {
        this.netPriceType = netPriceType;
    }

    public String getNetPriceTypeFormula() {
        return netPriceTypeFormula;
    }

    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        this.netPriceTypeFormula = netPriceTypeFormula;
    }

    public String getBrandMasterSID() {
        return brandMasterSID;
    }

    public void setBrandMasterSID(String brandMasterSID) {
        this.brandMasterSID = brandMasterSID;
    }

    public HelperDTO getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(HelperDTO itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getPricePlanID() {
        return pricePlanID;
    }

    public void setPricePlanID(String pricePlanID) {
        this.pricePlanID = pricePlanID;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    public String getNepFormulaSid() {
        return nepFormulaSid;
    }

    public void setNepFormulaSid(String nepFormulaSid) {
        this.nepFormulaSid = nepFormulaSid;
    }
    
    public HelperDTO getPpPriceToleranceInterval() {
        return ppPriceToleranceInterval;
    }

    public void setPpPriceToleranceInterval(HelperDTO ppPriceToleranceInterval) {
        this.ppPriceToleranceInterval = ppPriceToleranceInterval;
    }

    public HelperDTO getPpPriceToleranceFrequency() {
        return ppPriceToleranceFrequency;
    }

    public void setPpPriceToleranceFrequency(HelperDTO ppPriceToleranceFrequency) {
        this.ppPriceToleranceFrequency = ppPriceToleranceFrequency;
    }

    public HelperDTO getPpPriceToleranceType() {
        return ppPriceToleranceType;
    }

    public void setPpPriceToleranceType(HelperDTO ppPriceToleranceType) {
        this.ppPriceToleranceType = ppPriceToleranceType;
    }
    
            
    public HelperDTO getBasePriceType() {
        return basePriceType;
    }

    public void setBasePriceType(HelperDTO basePriceType) {
        this.basePriceType = basePriceType;
    }

    public Object getBasePriceValue() {
        return basePriceValue;
    }

    public void setBasePriceValue(Object basePriceValue) {
        this.basePriceValue = basePriceValue;
    }

    public Date getBasePriceDate() {
        return basePriceDate;
    }

    public void setBasePriceDate(Date basePriceDate) {
        this.basePriceDate = basePriceDate;
    }

    public HelperDTO getBasePriceItemPriceType() {
        return basePriceItemPriceType;
    }

    public void setBasePriceItemPriceType(HelperDTO basePriceItemPriceType) {
        this.basePriceItemPriceType = basePriceItemPriceType;
    }

    public int getNetBasePriceFormulaID() {
        return netBasePriceFormulaID;
    }

    public void setNetBasePriceFormulaID(int netBasePriceFormulaName) {
        this.netBasePriceFormulaID = netBasePriceFormulaName;
    }

    public HelperDTO getSubsequentPeriodPriceType() {
        return subsequentPeriodPriceType;
    }

    public void setSubsequentPeriodPriceType(HelperDTO subsequentPeriodPriceType) {
        this.subsequentPeriodPriceType = subsequentPeriodPriceType;
    }
    
    public int getNetSubsequentPriceFormulaID() {
        return netSubsequentPriceFormulaID;
    }

    public void setNetSubsequentPriceFormulaID(int netSubsequentPriceFormulaName) {
        this.netSubsequentPriceFormulaID = netSubsequentPriceFormulaName;
    }

    public HelperDTO getResetPriceType() {
        return resetPriceType;
    }

    public void setResetPriceType(HelperDTO resetPriceType) {
        this.resetPriceType = resetPriceType;
    }

    public int getNetResetPriceFormulaID() {
        return netResetPriceFormulaID;
    }

    public void setNetResetPriceFormulaID(int netResetPriceFormulaID) {
        this.netResetPriceFormulaID = netResetPriceFormulaID;
    }
    
    public String getBasePriceEntry() {
        return basePriceEntry;
    }

    public void setBasePriceEntry(String basePriceEntry) {
        this.basePriceEntry = basePriceEntry;
    }
    
    public HelperDTO getNetBasePrice() {
        return netBasePrice;
    }

    public void setNetBasePrice(HelperDTO netBasePrice) {
        this.netBasePrice = netBasePrice;
    }

    public HelperDTO getNetSubsequentPeriodPrice() {
        return netSubsequentPeriodPrice;
    }

    public void setNetSubsequentPeriodPrice(HelperDTO netSubsequentPeriodPrice) {
        this.netSubsequentPeriodPrice = netSubsequentPeriodPrice;
    }

    public HelperDTO getNetResetPriceType() {
        return netResetPriceType;
    }

    public void setNetResetPriceType(HelperDTO netResetPriceType) {
        this.netResetPriceType = netResetPriceType;
    }
    
    public HelperDTO getSelectedBasePrice() {
        return selectedBasePrice;
    }

    public void setSelectedBasePrice(HelperDTO selectedBasePrice) {
        this.selectedBasePrice = selectedBasePrice;
    }

    public String getNepFormulaName() {
        return nepFormulaName;
    }

    public void setNepFormulaName(String nepFormulaName) {
        this.nepFormulaName = nepFormulaName;
    }

    public String getNetPriceTypeFormulaID() {
        return netPriceTypeFormulaID;
    }

    public void setNetPriceTypeFormulaID(String netPriceTypeFormulaID) {
        this.netPriceTypeFormulaID = netPriceTypeFormulaID;
    }
    
    public String getNetBasePriceFormula() {
        return netBasePriceFormula;
    }

    public void setNetBasePriceFormula(String netBasePriceFormula) {
        this.netBasePriceFormula = netBasePriceFormula;
    }

    public String getNetSubsequentPriceFormula() {
        return netSubsequentPriceFormula;
    }

    public void setNetSubsequentPriceFormula(String netSubsequentPriceFormula) {
        this.netSubsequentPriceFormula = netSubsequentPriceFormula;
    }

    public String getNetResetPriceFormula() {
        return netResetPriceFormula;
    }

    public void setNetResetPriceFormula(String netResetPriceFormula) {
        this.netResetPriceFormula = netResetPriceFormula;
    }
    
    public String getPriceTypeDesc() {
        return priceTypeDesc;
    }

    public void setPriceTypeDesc(String priceTypeDesc) {
        this.priceTypeDesc = priceTypeDesc;
    }

    public HelperDTO getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(HelperDTO ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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

    public String getTherapyClass() {
        return therapyClass;
    }

    public void setTherapyClass(String therapyClass) {
        this.therapyClass = therapyClass;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    

    public String getItemFamilyplanId() {
        return itemFamilyplanId;
    }

    public void setItemFamilyplanId(String itemFamilyplanId) {
        this.itemFamilyplanId = itemFamilyplanId;
    }

    public String getItemFamilyplanNo() {
        return itemFamilyplanNo;
    }

    public void setItemFamilyplanNo(String itemFamilyplanNo) {
        this.itemFamilyplanNo = itemFamilyplanNo;
    }

    public String getItemFamilyplanName() {
        return itemFamilyplanName;
    }

    public void setItemFamilyplanName(String itemFamilyplanName) {
        this.itemFamilyplanName = itemFamilyplanName;
    }

    public String getParentItemFamilyplanId() {
        return parentItemFamilyplanId;
    }

    public void setParentItemFamilyplanId(String parentItemFamilyplanId) {
        this.parentItemFamilyplanId = parentItemFamilyplanId;
    }

    public String getParentItemFamilyplanName() {
        return parentItemFamilyplanName;
    }

    public void setParentItemFamilyplanName(String parentItemFamilyplanName) {
        this.parentItemFamilyplanName = parentItemFamilyplanName;
    }

    public HelperDTO getItemFamilyplanDesignation() {
        return itemFamilyplanDesignation;
    }

    public void setItemFamilyplanDesignation(HelperDTO itemFamilyplanDesignation) {
        this.itemFamilyplanDesignation = itemFamilyplanDesignation;
    }

    public HelperDTO getItemFamilyplanCategory() {
        return itemFamilyplanCategory;
    }

    public void setItemFamilyplanCategory(HelperDTO itemFamilyplanCategory) {
        this.itemFamilyplanCategory = itemFamilyplanCategory;
    }

    public HelperDTO getItemFamilyplanType() {
        return itemFamilyplanType;
    }

    public void setItemFamilyplanType(HelperDTO itemFamilyplanType) {
        this.itemFamilyplanType = itemFamilyplanType;
    }

    public HelperDTO getItemFamilyplanStatus() {
        return itemFamilyplanStatus;
    }

    public void setItemFamilyplanStatus(HelperDTO itemFamilyplanStatus) {
        this.itemFamilyplanStatus = itemFamilyplanStatus;
    }

    public String getIfpCategory() {
        return ifpCategory;
    }

    public void setIfpCategory(String ifpCategory) {
        this.ifpCategory = ifpCategory;
    }

    public String getIfpType() {
        return ifpType;
    }

    public void setIfpType(String ifpType) {
        this.ifpType = ifpType;
    }

    public String getIfPStatus() {
        return ifPStatus;
    }

    public void setIfPStatus(String ifPStatus) {
        this.ifPStatus = ifPStatus;
    }

    public String getIfpDesignation() {
        return ifpDesignation;
    }

    public void setIfpDesignation(String ifpDesignation) {
        this.ifpDesignation = ifpDesignation;
    }

    public String getItemsStatus() {
        return itemsStatus;
    }

    public void setItemsStatus(String itemsStatus) {
        this.itemsStatus = itemsStatus;
    }
    
}

