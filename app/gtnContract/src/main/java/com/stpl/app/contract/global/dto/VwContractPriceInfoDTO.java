package com.stpl.app.contract.global.dto;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.ifs.util.HelperUtils;
import org.apache.commons.lang.StringUtils;


public class VwContractPriceInfoDTO implements Serializable {

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
    private String priceType = HelperUtils.EMPTY;
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
    /**
     * The contract price info dto.
     */
    private List<VwContractPriceInfoDTO> contractPriceInfoDTO = new ArrayList<VwContractPriceInfoDTO>();
    /**
     * The item list.
     */
    private List<ItemMasterDTO> itemList = new ArrayList<ItemMasterDTO>();
    
    private String tempItemPriceRebateSystemId;
    
    private String displayPriceType;

    /** The temp ps details system id. */
    private int tempPsDetailsSystemId;
    
    /**
     * The operationFlag.
     */
    private String operation;

    private int brandMasterSid;
    
    private String brand;

     /** The price protection status. */
    private HelperDTO priceProtectionStatus = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    /** Price Protection Start Date Field. */
    private Date priceProtectionStartDate;
    
    /** Price Protection End Date Field. */
    private Date priceProtectionEndDate;
    
    private String nep = StringUtils.EMPTY;

    /** The nep formula. */
    private String nepFormula = StringUtils.EMPTY;
    /** The nep formula. */
    private String nepFormulaId = StringUtils.EMPTY;
    /** The nep formula. */
    private String nepFormulaName = StringUtils.EMPTY;
    
    
    /** The max incremental change. */
    private String maxIncrementalChange = StringUtils.EMPTY;

    /** The reset eligible. */
    private String resetEligible=ConstantsUtils.SELECT_ONE;

    /** The reset type. */
    private HelperDTO resetType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The reset date. */
    private Date resetDate;

    /** The reset interval. */
    private HelperDTO resetInterval = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The reset frequency. */
    private HelperDTO resetFrequency = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /** The net price type. */
    private String netPriceType=ConstantsUtils.SELECT_ONE;

    /** The net price type formula. */
    private String netPriceTypeFormula = StringUtils.EMPTY;
    /** The net price type formula. */
    private String netPriceTypeFormulaName = StringUtils.EMPTY;
    
    private HelperDTO ppPriceToleranceInterval = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO ppPriceToleranceFrequency = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO ppPriceToleranceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO basePriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private Object basePriceValue;
    
    private String basePriceEntry = StringUtils.EMPTY;
    
    private Date basePriceDate;
    
    private HelperDTO basePriceItemPriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO netBasePrice =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);  
    
    private String netBasePriceFormulaName;

    private String netBasePriceFormulaID;
    
    private HelperDTO subsequentPeriodPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO netSubsequentPeriodPrice =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private String netSubsequentPriceFormulaName;
    
    private String netSubsequentPriceFormulaID;
    
    private HelperDTO resetPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private HelperDTO netResetPriceType =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    private String netResetPriceFormulaName;
    
    private String netResetPriceFormulaID;     
    
    private String selectedBasePrice;

    private HelperDTO priceProtectionPriceType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    private String suggestedPrice = StringUtils.EMPTY;
    
    private String source = StringUtils.EMPTY;
    
    /**
     * The createdBy.
     */
    private String createdBy = StringUtils.EMPTY;
    /**
     * The createdDate.
     */
    private Date createdDate;
   private HelperDTO ifpStatus =   new HelperDTO(0, ConstantsUtils.SELECT_ONE);
   private String itemDesc= StringUtils.EMPTY;
  private String form= StringUtils.EMPTY;
  private String strength= StringUtils.EMPTY;
  private String therapyClass= StringUtils.EMPTY;
  private String recordType= StringUtils.EMPTY;
  
      /**
     * The createdBy.
     */
    private String modifiedBy = StringUtils.EMPTY;
    /**
     * The createdDate.
     */
    private Date modifiedDate;

    public String getTempItemPriceRebateSystemId() {
        return tempItemPriceRebateSystemId;
    }

    public void setTempItemPriceRebateSystemId(String tempItemPriceRebateSystemId) {
        this.tempItemPriceRebateSystemId = tempItemPriceRebateSystemId;
    }

    /**
     * Gets the unique date.
     *
     * @return the unique date
     */
    public String getUniqueDate() {
        return uniqueDate;
    }

    /**
     * Sets the unique date.
     *
     * @param uniqueDate the unique date
     */
    public void setUniqueDate(final String uniqueDate) {
        this.uniqueDate = uniqueDate;
    }

    /**
     * The Constructor.
     */
    public VwContractPriceInfoDTO() {
        // default constructor
    }

    /**
     * The Constructor.
     *
     * @param companyFamilyPlanSystemId the company family plan system id
     * @param priceScheduleSystemId the price schedule system id
     * @param itemFamilyplanSystemId the item familyplan system id
     * @param itemSystemId the item system id
     * @param attachedDate the attached date
     * @param itemName the item name
     * @param primaryUom the primary uom
     * @param itemNo the item no
     * @param packageSize the package size
     * @param itemId the id,
     * @param contractSystemId the id,
     * @param revisionDate the revision date
     */
    public VwContractPriceInfoDTO(final String companyFamilyPlanSystemId, final String priceScheduleSystemId, final String itemFamilyplanSystemId, final String itemSystemId, final Date attachedDate,
            final String itemName, final String primaryUom, final String itemNo, final String packageSize, final String itemId, final String contractSystemId, final Date revisionDate) {
        super();

        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
        this.priceScheduleSystemId = priceScheduleSystemId;
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;

        this.itemSystemId = itemSystemId;
        this.attachedDate = attachedDate;
        this.itemName = itemName;
        this.primaryUom = primaryUom;

        this.itemNo = itemNo;
        this.packageSize = packageSize;

        this.itemId = itemId;

        this.contractSystemId = contractSystemId;
        this.revisionDate = revisionDate;

    }


    /**
     * Gets the attached date.
     *
     * @return the attached date
     */
    public Date getAttachedDate() {
        return attachedDate;
    }

    /**
     * Sets the attached date.
     *
     * @param attachedDate the attached date
     */
    public void setAttachedDate(final Date attachedDate) {
        this.attachedDate = attachedDate;
    }

    /**
     * Gets the revision date.
     *
     * @return the revision date
     */
    public Date getRevisionDate() {
        return revisionDate;
    }

    /**
     * Sets the revision date.
     *
     * @param revisionDate the revision date
     */
    public void setRevisionDate(final Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    /**
     * Gets the attached status.
     *
     * @return the attached status
     */
    public String getAttachedStatus() {
        return attachedStatus;
    }

    /**
     * Sets the attached status.
     *
     * @param attachedStatus the attached status
     */
    public void setAttachedStatus(final String attachedStatus) {
        this.attachedStatus = attachedStatus;
    }

    /**
     * Gets the price type.
     *
     * @return the price type
     */
    public String getPriceType() {
        return priceType;
    }

    /**
     * Sets the price type.
     *
     * @param priceType the price type
     */
    public void setPriceType(final String priceType) {
        this.priceType = priceType;
    }

    /**
     * Gets the cp end date.
     *
     * @return the cp end date
     */
    public Date getCpEndDate() {
        return cpEndDate;
    }

    /**
     * Sets the cp end date.
     *
     * @param cpEndDate the cp end date
     */
    public void setCpEndDate(final Date cpEndDate) {
        this.cpEndDate = cpEndDate;
    }

    /**
     * Gets the price schedule system id.
     *
     * @return the price schedule system id
     */
    public String getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    /**
     * Sets the price schedule system id.
     *
     * @param priceScheduleSystemId the price schedule system id
     */
    public void setPriceScheduleSystemId(final String priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    /**
     * Gets the item familyplan system id.
     *
     * @return the item familyplan system id
     */
    public String getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    /**
     * Sets the item familyplan system id.
     *
     * @param itemFamilyplanSystemId the item familyplan system id
     */
    public void setItemFamilyplanSystemId(final String itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    /**
     * Gets the pp start date.
     *
     * @return the pp start date
     */
    public Date getPpStartDate() {
        return ppStartDate;
    }

    /**
     * Sets the pp start date.
     *
     * @param ppStartDate the pp start date
     */
    public void setPpStartDate(final Date ppStartDate) {
        this.ppStartDate = ppStartDate;
    }

    /**
     * Gets the item system id.
     *
     * @return the item system id
     */
    public String getItemSystemId() {
        return itemSystemId;
    }

    /**
     * Sets the item system id.
     *
     * @param itemSystemId the item system id
     */
    public void setItemSystemId(final String itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    /**
     * Gets the contractprice.
     *
     * @return the contractprice
     */
    public String getContractprice() {
        return contractprice;
    }

    /**
     * Sets the contractprice.
     *
     * @param contractprice the contractprice
     */
    public void setContractprice(final String contractprice) {
        this.contractprice = contractprice;
    }

    /**
     * Gets the price tolerance frequency.
     *
     * @return the price tolerance frequency
     */
    public String getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    /**
     * Sets the price tolerance frequency.
     *
     * @param priceToleranceFrequency the price tolerance frequency
     */
    public void setPriceToleranceFrequency(final String priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the cp start date.
     *
     * @return the cp start date
     */
    public Date getCpStartDate() {
        return cpStartDate;
    }

    /**
     * Sets the cp start date.
     *
     * @param cpStartDate the cp start date
     */
    public void setCpStartDate(final Date cpStartDate) {
        this.cpStartDate = cpStartDate;
    }

    /**
     * Gets the base price.
     *
     * @return the base price
     */
    public String getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price.
     *
     * @param basePrice the base price
     */
    public void setBasePrice(final String basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Gets the globalitemstatus.
     *
     * @return the globalitemstatus
     */
    public String getGlobalitemstatus() {
        return globalitemstatus;
    }

    /**
     * Sets the globalitemstatus.
     *
     * @param globalitemstatus the globalitemstatus
     */
    public void setGlobalitemstatus(final String globalitemstatus) {
        this.globalitemstatus = globalitemstatus;
    }

    /**
     * Gets the price tolerance.
     *
     * @return the price tolerance
     */
    public String getPriceTolerance() {
        return priceTolerance;
    }

    /**
     * Sets the price tolerance.
     *
     * @param priceTolerance the price tolerance
     */
    public void setPriceTolerance(final String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the pp end date.
     *
     * @return the pp end date
     */
    public Date getPpEndDate() {
        return ppEndDate;
    }

    /**
     * Sets the pp end date.
     *
     * @param ppEndDate the pp end date
     */
    public void setPpEndDate(final Date ppEndDate) {
        this.ppEndDate = ppEndDate;
    }

    /**
     * Gets the price tolerance interval.
     *
     * @return the price tolerance interval
     */
    public String getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    /**
     * Sets the price tolerance interval.
     *
     * @param priceToleranceInterval the price tolerance interval
     */
    public void setPriceToleranceInterval(final String priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    /**
     * Gets the item name.
     *
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the item name.
     *
     * @param itemName the item name
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the primary uom.
     *
     * @return the primary uom
     */
    public String getPrimaryUom() {
        return primaryUom;
    }

    /**
     * Sets the primary uom.
     *
     * @param primaryUom the primary uom
     */
    public void setPrimaryUom(final String primaryUom) {
        this.primaryUom = primaryUom;
    }

    /**
     * Gets the secondary uom.
     *
     * @return the secondary uom
     */
    public String getSecondaryUom() {
        return secondaryUom;
    }

    /**
     * Sets the secondary uom.
     *
     * @param secondaryUom the secondary uom
     */
    public void setSecondaryUom(final String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    /**
     * Gets the item no.
     *
     * @return the item no
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the item no.
     *
     * @param itemNo the item no
     */
    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * Gets the package size.
     *
     * @return the package size
     */
    public String getPackageSize() {
        return packageSize;
    }

    /**
     * Sets the package size.
     *
     * @param packageSize the package size
     */
    public void setPackageSize(final String packageSize) {
        this.packageSize = packageSize;
    }

    /**
     * Gets the item id.
     *
     * @return the item id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Sets the item id.
     *
     * @param itemId the item id
     */
    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the price tolerance type.
     *
     * @return the price tolerance type
     */
    public String getPriceToleranceType() {
        return priceToleranceType;
    }

    /**
     * Sets the price tolerance type.
     *
     * @param priceToleranceType the price tolerance type
     */
    public void setPriceToleranceType(final String priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    /**
     * Gets the contract system id.
     *
     * @return the contract system id
     */
    public String getContractSystemId() {
        return contractSystemId;
    }

    /**
     * Sets the contract system id.
     *
     * @param contractSystemId the contract system id
     */
    public void setContractSystemId(final String contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    /**
     * Gets the checkbox.
     *
     * @return the checkbox
     */
    public Boolean getCheckbox() {
        return checkbox;
    }

    /**
     * Sets the checkbox.
     *
     * @param checkbox the checkbox
     */
    public void setCheckbox(final Boolean checkbox) {
        this.checkbox = checkbox;
    }

    /**
     * Gets the company family plan system id.
     *
     * @return the company family plan system id
     */
    public String getCompanyFamilyPlanSystemId() {
        return companyFamilyPlanSystemId;
    }

    /**
     * Sets the company family plan system id.
     *
     * @param companyFamilyPlanSystemId the company family plan system id
     */
    public void setCompanyFamilyPlanSystemId(final String companyFamilyPlanSystemId) {
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
    }

    /**
     * Gets the contract price info dto.
     *
     * @return the contract price info dto
     */
    public List<VwContractPriceInfoDTO> getContractPriceInfoDTO() {
        return contractPriceInfoDTO;
    }

    /**
     * Sets the contract price info dto.
     *
     * @param contractPriceInfoDTO the contract price info dto
     */
    public void setContractPriceInfoDTO(final List<VwContractPriceInfoDTO> contractPriceInfoDTO) {
        this.contractPriceInfoDTO = contractPriceInfoDTO;
    }

    /**
     * Gets the item list.
     *
     * @return the item list
     */
    public List<ItemMasterDTO> getItemList() {
        return itemList;
    }

    /**
     * Sets the item list.
     *
     * @param itemList the item list
     */
    public void setItemList(final List<ItemMasterDTO> itemList) {
        this.itemList = itemList;
    }

    /**
     * Gets the ifp details system id.
     *
     * @return the ifp details system id
     */
    public String getIfpDetailsSystemId() {
        return ifpDetailsSystemId;
    }

    /**
     * Sets the ifp details system id.
     *
     * @param ifpDetailsSystemId the ifp details system id
     */
    public void setIfpDetailsSystemId(final String ifpDetailsSystemId) {
        this.ifpDetailsSystemId = ifpDetailsSystemId;
    }

    /**
     * Gets the ps details system id.
     *
     * @return the ps details system id
     */
    public String getPsDetailsSystemId() {
        return psDetailsSystemId;
    }

    /**
     * Sets the ps details system id.
     *
     * @param psDetailsSystemId the ps details system id
     */
    public void setPsDetailsSystemId(final String psDetailsSystemId) {
        this.psDetailsSystemId = psDetailsSystemId;
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
     * @param price the price
     */
    public void setPrice(final String price) {
        this.price = price;
    }

    public String getDisplayPriceType() {
        return displayPriceType;
    }

    public void setDisplayPriceType(String displayPriceType) {
        this.displayPriceType = displayPriceType;
    }
    public int getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getTempPsDetailsSystemId() {
        return tempPsDetailsSystemId;
    }

    public void setTempPsDetailsSystemId(int tempPsDetailsSystemId) {
        this.tempPsDetailsSystemId = tempPsDetailsSystemId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public HelperDTO getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    public void setPriceProtectionStatus(HelperDTO priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
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

    public String getNepFormulaId() {
        return nepFormulaId;
    }

    public void setNepFormulaId(String nepFormulaId) {
        this.nepFormulaId = nepFormulaId;
    }

    public String getNepFormulaName() {
        return nepFormulaName;
    }

    public void setNepFormulaName(String nepFormulaName) {
        this.nepFormulaName = nepFormulaName;
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

    public String getNetPriceType() {
        return netPriceType;
    }

    public void setNetPriceType(String netPriceType) {
        this.netPriceType = netPriceType;
    }

    public String getNetPriceTypeFormula() {
        return netPriceTypeFormula;
    }

    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        this.netPriceTypeFormula = netPriceTypeFormula;
    }

    public String getNetPriceTypeFormulaName() {
        return netPriceTypeFormulaName;
    }

    public void setNetPriceTypeFormulaName(String netPriceTypeFormulaName) {
        this.netPriceTypeFormulaName = netPriceTypeFormulaName;
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

    public String getBasePriceEntry() {
        return basePriceEntry;
    }

    public void setBasePriceEntry(String basePriceEntry) {
        this.basePriceEntry = basePriceEntry;
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

    public HelperDTO getNetBasePrice() {
        return netBasePrice;
    }

    public void setNetBasePrice(HelperDTO netBasePrice) {
        this.netBasePrice = netBasePrice;
    }

    public String getNetBasePriceFormulaName() {
        return netBasePriceFormulaName;
    }

    public void setNetBasePriceFormulaName(String netBasePriceFormulaName) {
        this.netBasePriceFormulaName = netBasePriceFormulaName;
    }

    public String getNetBasePriceFormulaID() {
        return netBasePriceFormulaID;
    }

    public void setNetBasePriceFormulaID(String netBasePriceFormulaID) {
        this.netBasePriceFormulaID = netBasePriceFormulaID;
    }

    public HelperDTO getSubsequentPeriodPriceType() {
        return subsequentPeriodPriceType;
    }

    public void setSubsequentPeriodPriceType(HelperDTO subsequentPeriodPriceType) {
        this.subsequentPeriodPriceType = subsequentPeriodPriceType;
    }

    public HelperDTO getNetSubsequentPeriodPrice() {
        return netSubsequentPeriodPrice;
    }

    public void setNetSubsequentPeriodPrice(HelperDTO netSubsequentPeriodPrice) {
        this.netSubsequentPeriodPrice = netSubsequentPeriodPrice;
    }

    public String getNetSubsequentPriceFormulaName() {
        return netSubsequentPriceFormulaName;
    }

    public void setNetSubsequentPriceFormulaName(String netSubsequentPriceFormulaName) {
        this.netSubsequentPriceFormulaName = netSubsequentPriceFormulaName;
    }

    public String getNetSubsequentPriceFormulaID() {
        return netSubsequentPriceFormulaID;
    }

    public void setNetSubsequentPriceFormulaID(String netSubsequentPriceFormulaID) {
        this.netSubsequentPriceFormulaID = netSubsequentPriceFormulaID;
    }

    public HelperDTO getResetPriceType() {
        return resetPriceType;
    }

    public void setResetPriceType(HelperDTO resetPriceType) {
        this.resetPriceType = resetPriceType;
    }

    public HelperDTO getNetResetPriceType() {
        return netResetPriceType;
    }

    public void setNetResetPriceType(HelperDTO netResetPriceType) {
        this.netResetPriceType = netResetPriceType;
    }

    public String getNetResetPriceFormulaName() {
        return netResetPriceFormulaName;
    }

    public void setNetResetPriceFormulaName(String netResetPriceFormulaName) {
        this.netResetPriceFormulaName = netResetPriceFormulaName;
    }

    public String getNetResetPriceFormulaID() {
        return netResetPriceFormulaID;
    }

    public void setNetResetPriceFormulaID(String netResetPriceFormulaID) {
        this.netResetPriceFormulaID = netResetPriceFormulaID;
    }

    public String getSelectedBasePrice() {
        return selectedBasePrice;
    }

    public void setSelectedBasePrice(String selectedBasePrice) {
        this.selectedBasePrice = selectedBasePrice;
    }
    
    public HelperDTO getPriceProtectionPriceType() {
        return priceProtectionPriceType;
    }

    public void setPriceProtectionPriceType(HelperDTO priceProtectionPriceType) {
        this.priceProtectionPriceType = priceProtectionPriceType;
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

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    
}
