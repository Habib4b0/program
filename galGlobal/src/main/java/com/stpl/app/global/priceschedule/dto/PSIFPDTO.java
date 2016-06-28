package com.stpl.app.global.priceschedule.dto;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * This class is used to hold Price Schedule IFP Details.
 *
 * @author manikanta
 */
public class PSIFPDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -7336041461233983663L;

    /** The item family plan id. */
    private String itemFamilyPlanId = StringUtils.EMPTY;

    /** The item family plan status. */
    private String itemFamilyPlanStatus = StringUtils.EMPTY;

    /** The ifp start date. */
    private Date ifpStartDate;

    /** The ifp end date. */
    private Date ifpEndDate;

    /** The ifp type. */
    private String ifpType = StringUtils.EMPTY;

    /** The ifp category. */
    private String ifpCategory = StringUtils.EMPTY;

    /** Item Family plan No Field. */
    private String itemFamilyplanNo;
    
    /** Item Family plan No Field. */
    private String itemFamilyplanName;
    
    /** Item System Id system generated field in Item Master. */
    private String itemSystemId;
    
    /** System generated field in item Family Plan. */
    private String itemFamilyplanSystemId;
    
    /** System generated field in Price Schedule. */
    private String priceScheduleSystemId;

    /** System generated field in Price Schedule details. */
    private String priceScheduleDetailsSystemId;
    
    /** Item No Field. */
    private String itemNo = StringUtils.EMPTY;
    
    /** Item Name Field. */
    private String itemName = StringUtils.EMPTY;
    
    /** Price Protection Start Date Field. */
    private Date priceProtectionStartDate;
    
    /** Price Protection End Date Field. */
    private Date priceProtectionEndDate;
    
    /** Base Price in Item Master Field. */
    private String basePrice = StringUtils.EMPTY;
    
      private String netPriceTypeFormulaName = StringUtils.EMPTY;
    
    
    /** Price Type in Item Master Field. */
    private HelperDTO priceType;
    
    /** Price Tolerance Field. */
    private String priceTolerance = StringUtils.EMPTY;
    
    /** Attached Status Field. */
    private String attachedStatus = StringUtils.EMPTY;
    
    /** Price Tolerance Type Field. */
    private HelperDTO priceToleranceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    /** priceToleranceTypeValue. */
    private String priceToleranceTypeValue = StringUtils.EMPTY;
    
    /** Price Tolerance Interval Field. */
    private HelperDTO priceToleranceInterval = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** priceToleranceFrequencyValue. */
    private String priceToleranceIntervalValue = StringUtils.EMPTY;
    
    /** Price Tolerance Frequency Field. */
    private HelperDTO priceToleranceFrequency = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** Price Tolerance Frequency Field. */
    private String priceToleranceFrequencyValue = StringUtils.EMPTY;
    
    /** Price Field. */
    private String price = StringUtils.EMPTY;
    
    /** Contract Price Field. */
    private String contractPrice = StringUtils.EMPTY;
    
    /** Contract Price Start Date Field. */
    private Date contractPriceStartDate;
    
    /** Contract Price End Date Field. */
    private Date contractPriceEndDate;
    
    /** Revision Date Field. */
    private Date revisionDate;
    
    /** Attached Date Field. */
    private Date attachedDate;
    
    /** Check Box Field. */
    private Boolean checkRecord = false;

    /** The temp ps details system id. */
    private int tempPsDetailsSystemId;
    /**
     * The userID.
     */
    private int userID;
    /**
     * The sessionID.
     */
    private String sessionID = StringUtils.EMPTY;
    /**
     * The createdBy.
     */
    private int createdBy;
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
    private String nepFormulaId = StringUtils.EMPTY;

    /** The max incremental change. */
    private String maxIncrementalChange = StringUtils.EMPTY;

    /** The reset eligible. */
    private HelperDTO resetEligible =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);

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
    
    private String brandMasterSID = StringUtils.EMPTY;
    
    private HelperDTO itemStatus = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private String pricePlanID = StringUtils.EMPTY;
    private String pricePlanName = StringUtils.EMPTY;
    
    private String createdUserName = StringUtils.EMPTY;
    /** The nep formula. */
    private int nepFormulaSid;
    private HelperDTO basePriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private Object basePriceValue=StringUtils.EMPTY;
    
    private Double basePriceEntry;
    
    private Date basePriceDate;
    
    private HelperDTO basePriceItemPriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
     private HelperDTO netBasePrice =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private int netBasePriceFormulaId;
    
    private String netBasePriceFormulaName  = StringUtils.EMPTY;
    
    private HelperDTO subsequentPeriodPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
     private HelperDTO netSubsequentPeriodPrice =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private int netSubsequentPriceFormulaId;
    
    private String netSubsequentPriceFormulaName  = StringUtils.EMPTY;
    
    private HelperDTO resetPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
     private HelperDTO netResetPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private int netResetPriceFormulaId;
    
    private String netResetPriceFormulaName  = StringUtils.EMPTY;     

    /** The price type formula. */
    private String priceTypeFormulaSid = StringUtils.EMPTY;
    
  private HelperDTO selectedBasePrice  = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * Default Constructor.
     */
    public PSIFPDTO() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Parameterized Constructor.
     *
     * @param itemSystemId the item system id
     * @param itemFamilyplanNo the item familyplan no
     * @param itemFamilyplanSystemId the item familyplan system id
     * @param itemNo the item no
     * @param itemName the item name
     * @param priceScheduleSystemId the price schedule system id
     * @param attachedDate the attached date
     */
    public PSIFPDTO(final String itemSystemId, final String itemFamilyplanNo,
            final String itemFamilyplanSystemId, final String itemNo, final String itemName,
            final String priceScheduleSystemId, final Date attachedDate) {
        this.itemSystemId = itemSystemId;
        this.itemFamilyplanNo = itemFamilyplanNo;
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.priceScheduleSystemId = priceScheduleSystemId;
        this.attachedDate = attachedDate;
    }

    /**
     * Gets the item family plan status.
     *
     * @return the item family plan status
     */
    public String getItemFamilyPlanStatus() {
        return itemFamilyPlanStatus;
    }
/**
 * to getNepFormulaId
 * @return 
 */
    public String getNepFormulaId() {
        return nepFormulaId;
    }
/**
 * to setNepFormulaId
 * @param nepFormulaId 
 */
    public void setNepFormulaId(String nepFormulaId) {
        this.nepFormulaId = nepFormulaId;
    }

    /**
     * Sets the item family plan status.
     *
     * @param itemFamilyPlanStatus the new item family plan status
     */
    public void setItemFamilyPlanStatus(String itemFamilyPlanStatus) {
        this.itemFamilyPlanStatus = itemFamilyPlanStatus;
    }

    /**
     * Gets the ifp start date.
     *
     * @return the ifp start date
     */
    public Date getIfpStartDate() {
        return ifpStartDate;
    }

    /**
     * Sets the ifp start date.
     *
     * @param ifpStartDate the new ifp start date
     */
    public void setIfpStartDate(Date ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }

    /**
     * Gets the ifp end date.
     *
     * @return the ifp end date
     */
    public Date getIfpEndDate() {
        return ifpEndDate;
    }

    /**
     * Sets the ifp end date.
     *
     * @param ifpEndDate the new ifp end date
     */
    public void setIfpEndDate(Date ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
    }

    /**
     * Gets the ifp type.
     *
     * @return the ifp type
     */
    public String getIfpType() {
        return ifpType;
    }

    /**
     * Sets the ifp type.
     *
     * @param ifpType the new ifp type
     */
    public void setIfpType(String ifpType) {
        this.ifpType = ifpType;
    }

    /**
     * Gets the ifp category.
     *
     * @return the ifp category
     */
    public String getIfpCategory() {
        return ifpCategory;
    }

    /**
     * Sets the ifp category.
     *
     * @param ifpCategory the new ifp category
     */
    public void setIfpCategory(String ifpCategory) {
        this.ifpCategory = ifpCategory;
    }

    /**
     * Gets the item family plan id.
     *
     * @return the item family plan id
     */
    public String getItemFamilyPlanId() {
        return itemFamilyPlanId;
    }

    /**
     * Sets the item family plan id.
     *
     * @param itemFamilyPlanId the new item family plan id
     */
    public void setItemFamilyPlanId(String itemFamilyPlanId) {
        this.itemFamilyPlanId = itemFamilyPlanId;
    }

    /**
     * Gets the item familyplan no.
     *
     * @return the itemFamilyplanNo
     */
    public String getItemFamilyplanNo() {
        return itemFamilyplanNo;
    }

    /**
     * Sets the item familyplan no.
     *
     * @param itemFamilyplanNo the itemFamilyplanNo to set
     */
    public void setItemFamilyplanNo(final String itemFamilyplanNo) {
        this.itemFamilyplanNo = itemFamilyplanNo;
    }

    /**
     * Gets the item system id.
     *
     * @return the itemSystemId
     */
    public String getItemSystemId() {
        return itemSystemId;
    }

    /**
     * Sets the item system id.
     *
     * @param itemSystemId the itemSystemId to set
     */
    public void setItemSystemId(final String itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    /**
     * Gets the item familyplan system id.
     *
     * @return the itemFamilyplanSystemId
     */
    public String getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    /**
     * Sets the item familyplan system id.
     *
     * @param itemFamilyplanSystemId the itemFamilyplanSystemId to set
     */
    public void setItemFamilyplanSystemId(final String itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    /**
     * Gets the price schedule system id.
     *
     * @return the priceScheduleSystemId
     */
    public String getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    /**
     * Sets the price schedule system id.
     *
     * @param priceScheduleSystemId the priceScheduleSystemId to set
     */
    public void setPriceScheduleSystemId(final String priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    /**
     * Gets the item no.
     *
     * @return the itemNo
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the item no.
     *
     * @param itemNo the itemNo to set
     */
    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * Gets the item name.
     *
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the item name.
     *
     * @param itemName the itemName to set
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the price protection start date.
     *
     * @return the priceProtectionStartDate
     */
    public Date getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    /**
     * Sets the price protection start date.
     *
     * @param priceProtectionStartDate the priceProtectionStartDate to set
     */
    public void setPriceProtectionStartDate(final Date priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }

    /**
     * Gets the price protection end date.
     *
     * @return the priceProtectionEndDate
     */
    public Date getPriceProtectionEndDate() {
        return priceProtectionEndDate;
    }

    /**
     * Sets the price protection end date.
     *
     * @param priceProtectionEndDate the priceProtectionEndDate to set
     */
    public void setPriceProtectionEndDate(final Date priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate;
    }

    /**
     * Gets the base price.
     *
     * @return the basePrice
     */
    public String getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price.
     *
     * @param basePrice the basePrice to set
     */
    public void setBasePrice(final String basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Gets the price type.
     *
     * @return the priceType
     */
    public HelperDTO getPriceType() {
        return priceType;
    }

    /**
     * Sets the price type.
     *
     * @param priceType the priceType to set
     */
    public void setPriceType(final HelperDTO priceType) {
        this.priceType = priceType;
    }

    /**
     * Gets the price tolerance.
     *
     * @return the priceTolerance
     */
    public String getPriceTolerance() {
        return priceTolerance;
    }

    /**
     * Sets the price tolerance.
     *
     * @param priceTolerance the priceTolerance to set
     */
    public void setPriceTolerance(final String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    /**
     * Gets the attached status.
     *
     * @return the attachedStatus
     */
    public String getAttachedStatus() {
        return attachedStatus;
    }

    /**
     * Sets the attached status.
     *
     * @param attachedStatus the attachedStatus to set
     */
    public void setAttachedStatus(final String attachedStatus) {
        this.attachedStatus = attachedStatus;
    }

    /**
     * Gets the price tolerance frequency.
     *
     * @return the priceToleranceFrequency
     */
    public HelperDTO getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    /**
     * Sets the price tolerance frequency.
     *
     * @param priceToleranceFrequency the priceToleranceFrequency to set
     */
    public void setPriceToleranceFrequency(final HelperDTO priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the price to set
     */
    public void setPrice(final String price) {
        this.price = price;
    }

    /**
     * Gets the contract price.
     *
     * @return the contractPrice
     */
    public String getContractPrice() {
        return contractPrice;
    }

    /**
     * Sets the contract price.
     *
     * @param contractPrice the contractPrice to set
     */
    public void setContractPrice(final String contractPrice) {
        this.contractPrice = contractPrice;
    }

    /**
     * Gets the contract price start date.
     *
     * @return the contractPriceStartDate
     */
    public Date getContractPriceStartDate() {
        return contractPriceStartDate;
    }

    /**
     * Sets the contract price start date.
     *
     * @param contractPriceStartDate the contractPriceStartDate to set
     */
    public void setContractPriceStartDate(final Date contractPriceStartDate) {
        this.contractPriceStartDate = contractPriceStartDate;
    }

    /**
     * Gets the contract price end date.
     *
     * @return the contractPriceEndDate
     */
    public Date getContractPriceEndDate() {
        return contractPriceEndDate;
    }

    /**
     * Sets the contract price end date.
     *
     * @param contractPriceEndDate the contractPriceEndDate to set
     */
    public void setContractPriceEndDate(final Date contractPriceEndDate) {
        this.contractPriceEndDate = contractPriceEndDate;
    }

    /**
     * Gets the revision date.
     *
     * @return the revisionDate
     */
    public Date getRevisionDate() {
        return revisionDate;
    }

    /**
     * Sets the revision date.
     *
     * @param revisionDate the revisionDate to set
     */
    public void setRevisionDate(final Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    /**
     * Gets the attached date.
     *
     * @return the attachedDate
     */
    public Date getAttachedDate() {
        return attachedDate;
    }

    /**
     * Sets the attached date.
     *
     * @param attachedDate the attachedDate to set
     */
    public void setAttachedDate(final Date attachedDate) {
        this.attachedDate = attachedDate;
    }

    /**
     * Gets the check record.
     *
     * @return the check record
     */
    public Boolean getCheckRecord() {
        return checkRecord;
    }

    /**
     * Sets the check record.
     *
     * @param checkRecord the new check record
     */
    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    /**
     * Gets the item familyplan name.
     *
     * @return the item familyplan name
     */
    public String getItemFamilyplanName() {
        return itemFamilyplanName;
    }

    /**
     * Sets the item familyplan name.
     *
     * @param itemFamilyplanName the new item familyplan name
     */
    public void setItemFamilyplanName(String itemFamilyplanName) {
        this.itemFamilyplanName = itemFamilyplanName;
    }

    /**
     * Gets the temp ps details system id.
     *
     * @return the temp ps details system id
     */
    public int getTempPsDetailsSystemId() {
        return tempPsDetailsSystemId;
    }

    /**
     * Sets the temp ps details system id.
     *
     * @param tempPsDetailsSystemId the new temp ps details system id
     */
    public void setTempPsDetailsSystemId(int tempPsDetailsSystemId) {
        this.tempPsDetailsSystemId = tempPsDetailsSystemId;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the price schedule details system id.
     *
     * @return the price schedule details system id
     */
    public String getPriceScheduleDetailsSystemId() {
        return priceScheduleDetailsSystemId;
    }

    /**
     * Sets the price schedule details system id.
     *
     * @param priceScheduleDetailsSystemId the new price schedule details system id
     */
    public void setPriceScheduleDetailsSystemId(String priceScheduleDetailsSystemId) {
        this.priceScheduleDetailsSystemId = priceScheduleDetailsSystemId;
    }

    /**
     * Gets the operation.
     *
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the operation.
     *
     * @param operation the new operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Gets the price tolerance frequency value.
     *
     * @return the price tolerance frequency value
     */
    public String getPriceToleranceFrequencyValue() {
        return priceToleranceFrequencyValue;
    }

    /**
     * Sets the price tolerance frequency value.
     *
     * @param priceToleranceFrequencyValue the new price tolerance frequency value
     */
    public void setPriceToleranceFrequencyValue(String priceToleranceFrequencyValue) {
        this.priceToleranceFrequencyValue = priceToleranceFrequencyValue;
    }

    /**
     * Gets the price tolerance type.
     *
     * @return the price tolerance type
     */
    public HelperDTO getPriceToleranceType() {
        return priceToleranceType;
    }

    /**
     * Sets the price tolerance type.
     *
     * @param priceToleranceType the new price tolerance type
     */
    public void setPriceToleranceType(HelperDTO priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    /**
     * Gets the price tolerance interval.
     *
     * @return the price tolerance interval
     */
    public HelperDTO getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    /**
     * Sets the price tolerance interval.
     *
     * @param priceToleranceInterval the new price tolerance interval
     */
    public void setPriceToleranceInterval(HelperDTO priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    /**
     * Gets the price tolerance interval value.
     *
     * @return the price tolerance interval value
     */
    public String getPriceToleranceIntervalValue() {
        return priceToleranceIntervalValue;
    }

    /**
     * Sets the price tolerance interval value.
     *
     * @param priceToleranceIntervalValue the new price tolerance interval value
     */
    public void setPriceToleranceIntervalValue(String priceToleranceIntervalValue) {
        this.priceToleranceIntervalValue = priceToleranceIntervalValue;
    }

    /**
     * Gets the price tolerance type value.
     *
     * @return the price tolerance type value
     */
    public String getPriceToleranceTypeValue() {
        return priceToleranceTypeValue;
    }

    /**
     * Sets the price tolerance type value.
     *
     * @param priceToleranceTypeValue the new price tolerance type value
     */
    public void setPriceToleranceTypeValue(String priceToleranceTypeValue) {
        this.priceToleranceTypeValue = priceToleranceTypeValue;
    }

    /**
     * Gets the item id.
     *
     * @return the item id
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Sets the item id.
     *
     * @param itemID the new item id
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand.
     *
     * @param brand the new brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the price protection status.
     *
     * @return the price protection status
     */
    public HelperDTO getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    /**
     * Sets the price protection status.
     *
     * @param priceProtectionStatus the new price protection status
     */
    public void setPriceProtectionStatus(HelperDTO priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
    }

    /**
     * Gets the price protection price type.
     *
     * @return the price protection price type
     */
    public HelperDTO getPriceProtectionPriceType() {
        return priceProtectionPriceType;
    }

    /**
     * Sets the price protection price type.
     *
     * @param priceProtectionPriceType the new price protection price type
     */
    public void setPriceProtectionPriceType(HelperDTO priceProtectionPriceType) {
        this.priceProtectionPriceType = priceProtectionPriceType;
    }

    /**
     * Gets the nep.
     *
     * @return the nep
     */
    public String getNep() {
        return nep;
    }

    /**
     * Sets the nep.
     *
     * @param nep the new nep
     */
    public void setNep(String nep) {
        this.nep = nep;
    }

    /**
     * Gets the nep formula.
     *
     * @return the nep formula
     */
    public String getNepFormula() {
        return nepFormula;
    }

    /**
     * Sets the nep formula.
     *
     * @param nepFormula the new nep formula
     */
    public void setNepFormula(String nepFormula) {
        this.nepFormula = nepFormula;
    }

    /**
     * Gets the max incremental change.
     *
     * @return the max incremental change
     */
    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    /**
     * Sets the max incremental change.
     *
     * @param maxIncrementalChange the new max incremental change
     */
    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
    }



    /**
     * Gets the reset type.
     *
     * @return the reset type
     */
    public HelperDTO getResetType() {
        return resetType;
    }

    /**
     * Sets the reset type.
     *
     * @param resetType the new reset type
     */
    public void setResetType(HelperDTO resetType) {
        this.resetType = resetType;
    }

    /**
     * Gets the reset date.
     *
     * @return the reset date
     */
    public Date getResetDate() {
        return resetDate;
    }

    /**
     * Sets the reset date.
     *
     * @param resetDate the new reset date
     */
    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }

    /**
     * Gets the reset interval.
     *
     * @return the reset interval
     */
    public HelperDTO getResetInterval() {
        return resetInterval;
    }

    /**
     * Sets the reset interval.
     *
     * @param resetInterval the new reset interval
     */
    public void setResetInterval(HelperDTO resetInterval) {
        this.resetInterval = resetInterval;
    }

    /**
     * Gets the reset frequency.
     *
     * @return the reset frequency
     */
    public HelperDTO getResetFrequency() {
        return resetFrequency;
    }

    /**
     * Sets the reset frequency.
     *
     * @param resetFrequency the new reset frequency
     */
    public void setResetFrequency(HelperDTO resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public HelperDTO getNetPriceType() {
        return netPriceType;
    }

    public void setNetPriceType(HelperDTO netPriceType) {
        this.netPriceType = netPriceType;
    }


    /**
     * Gets the net price type formula.
     *
     * @return the net price type formula
     */
    public String getNetPriceTypeFormula() {
        return netPriceTypeFormula;
    }

    /**
     * Sets the net price type formula.
     *
     * @param netPriceTypeFormula the new net price type formula
     */
    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        this.netPriceTypeFormula = netPriceTypeFormula;
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

    public String getBrandMasterSID() {
        return brandMasterSID;
    }

    public void setBrandMasterSID(String brandMasterSID) {
        this.brandMasterSID = brandMasterSID;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }
    
    public static Comparator<PSIFPDTO> psIdAsc = new Comparator<PSIFPDTO>() {
        public int compare(PSIFPDTO s1, PSIFPDTO s2) {
            String value1 = String.valueOf(s1.getItemFamilyPlanStatus()).toUpperCase();
            String value2 = String.valueOf(s2.getItemFamilyPlanStatus()).toUpperCase();
            return value1.compareTo(value2);
        }
    };
    
    public static Comparator<PSIFPDTO> psIdDsc = new Comparator<PSIFPDTO>() {
	public int compare(PSIFPDTO s1, PSIFPDTO s2) {
	   String value1 = String.valueOf(s1.getItemFamilyPlanStatus()).toUpperCase();
	   String value2 = String.valueOf(s2.getItemFamilyPlanStatus()).toUpperCase();
	   return value2.compareTo(value1);
    }};
    public static Comparator<PSIFPDTO> ifpCategoryAsc = new Comparator<PSIFPDTO>() {
        public int compare(PSIFPDTO s1, PSIFPDTO s2) {
            String value1 = String.valueOf(s1.getIfpCategory()).toUpperCase();
            String value2 = String.valueOf(s2.getIfpCategory()).toUpperCase();
            return value1.compareTo(value2);
        }
    };
    
    public static Comparator<PSIFPDTO> ifpCategoryDsc = new Comparator<PSIFPDTO>() {
	public int compare(PSIFPDTO s1, PSIFPDTO s2) {
	   String value1 = String.valueOf(s1.getIfpCategory()).toUpperCase();
	   String value2 = String.valueOf(s2.getIfpCategory()).toUpperCase();
	   return value2.compareTo(value1);
    }};
    public static Comparator<PSIFPDTO> ifpTypeAsc = new Comparator<PSIFPDTO>() {
        public int compare(PSIFPDTO s1, PSIFPDTO s2) {
            String value1 = String.valueOf(s1.getIfpType()).toUpperCase();
            String value2 = String.valueOf(s2.getIfpType()).toUpperCase();
            return value1.compareTo(value2);
        }
    };
    
    public static Comparator<PSIFPDTO> ifpTypeDsc = new Comparator<PSIFPDTO>() {
	public int compare(PSIFPDTO s1, PSIFPDTO s2) {
	   String value1 = String.valueOf(s1.getIfpType()).toUpperCase();
	   String value2 = String.valueOf(s2.getIfpType()).toUpperCase();
	   return value2.compareTo(value1);
    }};


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

  

    public HelperDTO getSubsequentPeriodPriceType() {
        return subsequentPeriodPriceType;
    }

    public void setSubsequentPeriodPriceType(HelperDTO subsequentPeriodPriceType) {
        this.subsequentPeriodPriceType = subsequentPeriodPriceType;
    }

 

    public int getNetBasePriceFormulaId() {
        return netBasePriceFormulaId;
    }

    public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
        this.netBasePriceFormulaId = netBasePriceFormulaId;
    }

    public String getNetBasePriceFormulaName() {
        return netBasePriceFormulaName;
    }

    public void setNetBasePriceFormulaName(String netBasePriceFormulaName) {
        this.netBasePriceFormulaName = netBasePriceFormulaName;
    }

    public int getNetSubsequentPriceFormulaId() {
        return netSubsequentPriceFormulaId;
    }

    public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
        this.netSubsequentPriceFormulaId = netSubsequentPriceFormulaId;
    }

    public String getNetSubsequentPriceFormulaName() {
        return netSubsequentPriceFormulaName;
    }

    public void setNetSubsequentPriceFormulaName(String netSubsequentPriceFormulaName) {
        this.netSubsequentPriceFormulaName = netSubsequentPriceFormulaName;
    }

    public HelperDTO getResetPriceType() {
        return resetPriceType;
    }

    public void setResetPriceType(HelperDTO resetPriceType) {
        this.resetPriceType = resetPriceType;
    }

   

    public int getNetResetPriceFormulaId() {
        return netResetPriceFormulaId;
    }

    public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
        this.netResetPriceFormulaId = netResetPriceFormulaId;
    }

    public String getNetResetPriceFormulaName() {
        return netResetPriceFormulaName;
    }

    public void setNetResetPriceFormulaName(String netResetPriceFormulaName) {
        this.netResetPriceFormulaName = netResetPriceFormulaName;
    }
 

    public HelperDTO getSelectedBasePrice() {
        return selectedBasePrice;
    }

    public void setSelectedBasePrice(HelperDTO selectedBasePrice) {
        this.selectedBasePrice = selectedBasePrice;
    }

    public Object getBasePriceValue() {
        return basePriceValue;
    }

    public void setBasePriceValue(Object basePriceValue) {
        this.basePriceValue = basePriceValue;
    }

    public Double getBasePriceEntry() {
        return basePriceEntry;
    }

    public void setBasePriceEntry(Double basePriceEntry) {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public int getNepFormulaSid() {
        return nepFormulaSid;
    }

    public void setNepFormulaSid(int nepFormulaSid) {
        this.nepFormulaSid = nepFormulaSid;
    }

    public HelperDTO getResetEligible() {
        return resetEligible;
    }

    public void setResetEligible(HelperDTO resetEligible) {
        this.resetEligible = resetEligible;
    }

    public String getPriceTypeFormulaSid() {
        return priceTypeFormulaSid;
    }

    public void setPriceTypeFormulaSid(String priceTypeFormulaSid) {
        this.priceTypeFormulaSid = priceTypeFormulaSid;
    }

    public HelperDTO getBasePriceType() {
        return basePriceType;
    }

    public void setBasePriceType(HelperDTO basePriceType) {
        this.basePriceType = basePriceType;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getNetPriceTypeFormulaName() {
        return netPriceTypeFormulaName;
    }

    public void setNetPriceTypeFormulaName(String netPriceTypeFormulaName) {
        this.netPriceTypeFormulaName = netPriceTypeFormulaName;
    }

 
    
}

