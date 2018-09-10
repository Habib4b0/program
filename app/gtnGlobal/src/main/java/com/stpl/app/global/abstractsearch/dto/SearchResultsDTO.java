package com.stpl.app.global.abstractsearch.dto;

import com.stpl.app.util.ConstantsUtils;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.util.HelperDTO;
import java.util.Date;

/**
 * The Class SearchResultsDTO.
 */
public class SearchResultsDTO implements Serializable {

    public SearchResultsDTO() {
        super();
    }

    private static final long serialVersionUID = -2456121686748264335L;
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String systemID = StringUtils.EMPTY;//Holds the systemID of the respective module

    //Item Master
    private String itemSystemID = StringUtils.EMPTY;
    private String errorMessages = StringUtils.EMPTY;
    private String itemId = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    private String itemType = StringUtils.EMPTY;
    private String manufacturerNo = StringUtils.EMPTY;
    private String itemDesc = StringUtils.EMPTY;
    private String itemIdentifier = StringUtils.EMPTY;
    private String itemTypeDesc = StringUtils.EMPTY;
    private String itemStatusDesc = StringUtils.EMPTY;
    private HelperDTO itemIrtQualifierName;
    private String qualifierId = StringUtils.EMPTY;
    private String manufacturerId = StringUtils.EMPTY;
    private Boolean searchFlag;
    private Boolean qualifierFlag;
    private Boolean identifierFlag;
    private String successMessage;
    private String itemStartDate;
    private boolean recordLockStatus;
     
    private String itemCode = StringUtils.EMPTY;
    private String packageSize = StringUtils.EMPTY;
    private String packageSizeIntroDate;
    private String upps;
//    @NotBlank(message = "Item End  String should  be present")
    private String itemEndDate;
    private String labelerCode = StringUtils.EMPTY;
    private String organizationKey = StringUtils.EMPTY;
    private String acquisitionDate;
    private String authorizedGeneric;
    private String authorizedGenericStartDate;
    private String authorizedGenericEndDate;
    private String form = StringUtils.EMPTY;
    private String strength= StringUtils.EMPTY;
    private String therapeuticClass = StringUtils.EMPTY;
    private String firstSaleDate;
    private String itemTypeIndication;
    private String itemClass;
    private String marketTerminationDate;
    private String newFormulationIndicator;
    private String newFormulation;
    private String newFormulationStartDate;
    private String newFormulationEndDate;
    private String pediatricExclusiveIndicator;
    private String pediatricExclusiveStartDate;
    private String pediatricExclusiveEndDate;
    private String clottingFactorIndicator;
    private String clottingFactorStartDate;
    private String clottingFactorEndDate;
    private String primaryUom;
    private String secondaryUom;
    private String shelfLife;
    private int shelfLifeType;
    private String dualPricingIndicator;
    private String itemFamilyId;
    private String udc1 = StringUtils.EMPTY;
    private boolean itemIndicator;
    private String udc2 = StringUtils.EMPTY;
    private String udc3 = StringUtils.EMPTY;
    private String udc4 = StringUtils.EMPTY;
    private String udc5 = StringUtils.EMPTY;
    private String udc6 = StringUtils.EMPTY;
    private BigDecimal acquiredAmp;
    private BigDecimal acquiredBamp;
    private BigDecimal obraBamp;
    private BigDecimal dra;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private Date itemMasterStartDate;
    private Date itemMasterEndDate;
    private Date pediatricExcStartDate;
    private Date pediatricExcEndDate;
    private Date clottingFactStartDate;
    private Date clottingFactEndDate;
    private HelperDTO ndc9 = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private HelperDTO ndc8 = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String modifiedDate;
    private String batchId;
    private String brand;
    private int brandMasterSid;
    private String dosesPerUnit;
    private Character inboundStatus;
    private String brandId;
    private HelperDTO brandDdlb = new HelperDTO(ConstantsUtils.SELECT_ONE); 
    private HelperDTO itemIrtQualifierNameDDLB = new HelperDTO(ConstantsUtils.SELECT_ONE);

    //Comapny Master 
    private String companySystemId = StringUtils.EMPTY;
    private String companyId = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String companyStatus = StringUtils.EMPTY;
    private String companyType = StringUtils.EMPTY;
    private String compStartDate = StringUtils.EMPTY;
    private String compEndDate = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private Date tradeStartDate;
    private Date tradeEndDate;
    private String lives = StringUtils.EMPTY;
    private String companyGroup = StringUtils.EMPTY;
    private String companyCategory = StringUtils.EMPTY;
    private String companyorganizationKey = StringUtils.EMPTY;
    private String financialSystem = StringUtils.EMPTY;
    private String parentCompanyNo = StringUtils.EMPTY;
    private Date parentSDate;
    private Date parentEDate;
    private String priorParentCompanyNo = StringUtils.EMPTY;
    private Date priorParentSDate;
    private String regionCode = StringUtils.EMPTY;
    private String companyudc1 = StringUtils.EMPTY;
    private String companyudc2 = StringUtils.EMPTY;
    private String companyudc3 = StringUtils.EMPTY;
    private String companyudc4 = StringUtils.EMPTY;
    private String companyudc5 = StringUtils.EMPTY;
    private String companyudc6 = StringUtils.EMPTY;
    private String address1 = StringUtils.EMPTY;
    private String address2 = StringUtils.EMPTY;
    private String zipCode = StringUtils.EMPTY;
    private String city = StringUtils.EMPTY;
    private String state = StringUtils.EMPTY;
    private String country = StringUtils.EMPTY;
    private String companyQualifierName = StringUtils.EMPTY;
    private String companyIdentifier = StringUtils.EMPTY;
    private HelperDTO identifierTypeDesc;
    private String identifier = StringUtils.EMPTY;
    private String companyStartDate = StringUtils.EMPTY;
    private String companyEndDate = StringUtils.EMPTY;
    private String parentStartDate = StringUtils.EMPTY;
    private Date comStartDate;
    private Date comEndDate;
    private Date comTradeStartDate;
    private Date comTradeEndDate;
    private Date comParentStartDate;
    private Date comParentEndDate;
    private Date priorParentStartDate;
    private String prntEndDate;

    // Company Family Plan
    private String parentCompanyFamilyPlanName = StringUtils.EMPTY;
    private int cfpsystemId;
    private String companyFamilyPlanId = StringUtils.EMPTY;
    private String companyFamilyPlanName = StringUtils.EMPTY;
    private String companyFamilyPlanType = StringUtils.EMPTY;
    private String companyFamilyPlanDesignation = StringUtils.EMPTY;
    private String companyFamilyPlanNo = StringUtils.EMPTY;
    private String companyFamilyPlanStatus = StringUtils.EMPTY;
    private Date companyFamilyPlanStartDate;
    private Date companyFamilyPlanEndDate;
    private String parentCompanyFamilyPlanId = StringUtils.EMPTY;
    private String companyFamilyPlanCategory = StringUtils.EMPTY;
    private String companyFamilyPlanTradeClass = StringUtils.EMPTY;
   private String companyFamilyPlanSystemId  = StringUtils.EMPTY;
    private Date cfpmodifiedDate;
    private String cfpmodifiedBy;
    private String cfpcreatedBy;
    private Date cfpcreatedDate;

    //Item Family Plan
    private String itemFamilyplanSystemId = StringUtils.EMPTY;
    private String ifpId = StringUtils.EMPTY;
    private String ifpNo = StringUtils.EMPTY;
    private String ifpName = StringUtils.EMPTY;
    private String ifpStatus = StringUtils.EMPTY;
    private String ifpType = StringUtils.EMPTY;
    private String ifpCategory = StringUtils.EMPTY;
    private Date itemFamilyplanStartDate;
    private Date itemFamilyplanEndDate;
    private String totalMarketshareCommitment = StringUtils.EMPTY;
    private String totalVolumeCommitment = StringUtils.EMPTY;
    private String commitmentPeriod = StringUtils.EMPTY;
    private String totalDollarCommitment = StringUtils.EMPTY;
    private String ifpDesignation = StringUtils.EMPTY;
    private String parentItemFamilyplanName = StringUtils.EMPTY;
    private String parentItemFamilyplanId = StringUtils.EMPTY;
    private String ifpcreatedBy = StringUtils.EMPTY;
    private Date ifpcreatedDate;
    private String ifpStartDate = StringUtils.EMPTY;
    private String ifpEndDate = StringUtils.EMPTY;

    // Price schedule 
    private String psSystemId = StringUtils.EMPTY;
    private String parentId = StringUtils.EMPTY;
    private String parentName = StringUtils.EMPTY;
    private String priceScheduleNo = StringUtils.EMPTY;
    private String priceScheduleName = StringUtils.EMPTY;
    private String priceScheduleId = StringUtils.EMPTY;
    private String priceScheduleType = StringUtils.EMPTY;
    private String priceScheduleStatus = StringUtils.EMPTY;
    private String priceScheduleCategory = StringUtils.EMPTY;
    private Date priceScheduleStartDate;
    private Date priceScheduleEndDate;
    private String priceScheduleDesignation = StringUtils.EMPTY;
    private String pstradeClass = StringUtils.EMPTY;
    private int priceScheduleSystemId;
    
    //Rebate Schedule
    private String rsSystemId =StringUtils.EMPTY;
    private String rebateScheduleId=StringUtils.EMPTY;
    private String rebateScheduleNo=StringUtils.EMPTY;
    private String rebateScheduleName=StringUtils.EMPTY;
    private String rebateScheduleType=StringUtils.EMPTY;
    private String rebateScheduleStatus=StringUtils.EMPTY;
    private String rsProgramType=StringUtils.EMPTY;
    private String rsTradeClass=StringUtils.EMPTY;
    private String rebateScheduleAliasID=StringUtils.EMPTY;
    private String rsCategory =StringUtils.EMPTY;        
    private String rebateFrequency =StringUtils.EMPTY;
    private String calendar =StringUtils.EMPTY;
    private String calculationType =StringUtils.EMPTY;
    private String calculationLevel =StringUtils.EMPTY;
    private String rebateRuleType =StringUtils.EMPTY;
    private String rebateRuleAssociation =StringUtils.EMPTY;
    private String paymentLevel =StringUtils.EMPTY;
    private String paymentTerms =StringUtils.EMPTY;
    private String paymentMethod =StringUtils.EMPTY;
    private String paymentGracePeriod =StringUtils.EMPTY;
    private String paymentFrequency =StringUtils.EMPTY;
    private String interestBearingIndicator =StringUtils.EMPTY;
    private String interestBearingBasis =StringUtils.EMPTY;
    private String rsDesignation =StringUtils.EMPTY;
    private String rsParentId =StringUtils.EMPTY;
    private String rsParentName =StringUtils.EMPTY;
    private String rsTransactionReferenceID =StringUtils.EMPTY;
    private String rsTransactionReferenceName =StringUtils.EMPTY;       
    
    //Rebate Plan
    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanNo = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String rebatePlanType = StringUtils.EMPTY;
    private String rebatePlanStatus = StringUtils.EMPTY;
    private String formulaType = StringUtils.EMPTY;
    private String rebateBasedOn = StringUtils.EMPTY;
    private String rebateStructure = StringUtils.EMPTY;
    private String rebateRangeBasedOn = StringUtils.EMPTY;
    private String secondaryRebatePlanId = StringUtils.EMPTY;
    private String secondaryRebatePlanNo = StringUtils.EMPTY;
    private String secondaryRebatePlanName = StringUtils.EMPTY;
    private String parentPriceScheduleId = StringUtils.EMPTY;
    private String parentPSName = StringUtils.EMPTY;
    private String rebateNsfName =  StringUtils.EMPTY;
    private String rebateRuleName = StringUtils.EMPTY;
    private Date rpCreatedDate;
    private Date rpModifiedDate;
    private String rpCreationDate;
    private String rpModifyDate;
    
    //Net Sales Formula
    private String netSalesFormulaType = StringUtils.EMPTY;
    private String netSalesFormulaId = StringUtils.EMPTY;
    private String netSalesFormulaNo = StringUtils.EMPTY;
    private String netSalesFormulaName = StringUtils.EMPTY;
    private Date nsfcreatedDate;
    private String nsfcreateDate= StringUtils.EMPTY;
    private String nsfcreatedBy = StringUtils.EMPTY;
    private Date nsfmodifiedDate;
    private String nsfmodifyDate= StringUtils.EMPTY;
    private String  nsfmodifiedBy = StringUtils.EMPTY;
      
    // Deduction Calendar
    private int deductionCalendarSid;
    private String deductionCalendarno = StringUtils.EMPTY;
    private String deductionCalendarname = StringUtils.EMPTY;
    private String deductionCalendardesc = StringUtils.EMPTY;
    private String category = StringUtils.EMPTY;
    private Date dcCreationDate;
    private String dcCreatedBy = StringUtils.EMPTY;
    private Date dcModifiedDate;
    private String dcModifiedBy = StringUtils.EMPTY;
    
      //Compliance Deduction Rules
    private String ruleNo = StringUtils.EMPTY;
    private String ruleType = StringUtils.EMPTY;
    private String ruleName = StringUtils.EMPTY;
    private String ruleCategory = StringUtils.EMPTY;
    private Date cdrCreatedDate;
    private Date cdrModifiedDate;
    private String itemBatchId = StringUtils.EMPTY;
    
    public String getParentPriceScheduleId() {
        return parentPriceScheduleId;
    }

    public void setParentPriceScheduleId(String parentPriceScheduleId) {
        this.parentPriceScheduleId = parentPriceScheduleId;
    }

    public String getParentPSName() {
        return parentPSName;
    }

    public void setParentPSName(String parentPSName) {
        this.parentPSName = parentPSName;
    }
    public HelperDTO getDto() {
        return dto;
    }

    public void setDto(HelperDTO dto) {
        this.dto = dto;
    }

    public String getItemSystemID() {
        return itemSystemID;
    }

    public void setItemSystemID(String itemSystemID) {
        this.itemSystemID = itemSystemID;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getManufacturerNo() {
        return manufacturerNo;
    }

    public void setManufacturerNo(String manufacturerNo) {
        this.manufacturerNo = manufacturerNo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public String getItemTypeDesc() {
        return itemTypeDesc;
    }

    public void setItemTypeDesc(String itemTypeDesc) {
        this.itemTypeDesc = itemTypeDesc;
    }

    public String getItemStatusDesc() {
        return itemStatusDesc;
    }

    public void setItemStatusDesc(String itemStatusDesc) {
        this.itemStatusDesc = itemStatusDesc;
    }

    public HelperDTO getItemIrtQualifierName() {
        return itemIrtQualifierName;
    }

    public void setItemIrtQualifierName(HelperDTO itemIrtQualifierName) {
        this.itemIrtQualifierName = itemIrtQualifierName;
    }

    public String getQualifierId() {
        return qualifierId;
    }

    public void setQualifierId(String qualifierId) {
        this.qualifierId = qualifierId;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Boolean isSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(Boolean searchFlag) {
        this.searchFlag = searchFlag;
    }

    public Boolean isQualifierFlag() {
        return qualifierFlag;
    }

    public void setQualifierFlag(Boolean qualifierFlag) {
        this.qualifierFlag = qualifierFlag;
    }

    public Boolean isIdentifierFlag() {
        return identifierFlag;
    }

    public void setIdentifierFlag(Boolean identifierFlag) {
        this.identifierFlag = identifierFlag;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(String itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

 public boolean isRecordLockStatus() {
  return recordLockStatus;
}

    public void setRecordLockStatus(boolean recordLockStatus) {
       this.recordLockStatus = recordLockStatus;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getPackageSizeIntroDate() {
        return packageSizeIntroDate;
    }

    public void setPackageSizeIntroDate(String packageSizeIntroDate) {
        this.packageSizeIntroDate = packageSizeIntroDate;
    }

    public String getUpps() {
        return upps;
    }

    public void setUpps(String upps) {
        this.upps = upps;
    }

    public String getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(String itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    public String getLabelerCode() {
        return labelerCode;
    }

    public void setLabelerCode(String labelerCode) {
        this.labelerCode = labelerCode;
    }

    public String getOrganizationKey() {
        return organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        this.organizationKey = organizationKey;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getAuthorizedGeneric() {
        return authorizedGeneric;
    }

    public void setAuthorizedGeneric(String authorizedGeneric) {
        this.authorizedGeneric = authorizedGeneric;
    }

    public String getAuthorizedGenericStartDate() {
        return authorizedGenericStartDate;
    }

    public void setAuthorizedGenericStartDate(String authorizedGenericStartDate) {
        this.authorizedGenericStartDate = authorizedGenericStartDate;
    }

    public String getAuthorizedGenericEndDate() {
        return authorizedGenericEndDate;
    }

    public void setAuthorizedGenericEndDate(String authorizedGenericEndDate) {
        this.authorizedGenericEndDate = authorizedGenericEndDate;
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

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public String getFirstSaleDate() {
        return firstSaleDate;
    }

    public void setFirstSaleDate(String firstSaleDate) {
        this.firstSaleDate = firstSaleDate;
    }

    public String getItemTypeIndication() {
        return itemTypeIndication;
    }

    public void setItemTypeIndication(String itemTypeIndication) {
        this.itemTypeIndication = itemTypeIndication;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getMarketTerminationDate() {
        return marketTerminationDate;
    }

    public void setMarketTerminationDate(String marketTerminationDate) {
        this.marketTerminationDate = marketTerminationDate;
    }

    public String getNewFormulationIndicator() {
        return newFormulationIndicator;
    }

    public void setNewFormulationIndicator(String newFormulationIndicator) {
        this.newFormulationIndicator = newFormulationIndicator;
    }

    public String getNewFormulation() {
        return newFormulation;
    }

    public void setNewFormulation(String newFormulation) {
        this.newFormulation = newFormulation;
    }

    public String getNewFormulationStartDate() {
        return newFormulationStartDate;
    }

    public void setNewFormulationStartDate(String newFormulationStartDate) {
        this.newFormulationStartDate = newFormulationStartDate;
    }

    public String getNewFormulationEndDate() {
        return newFormulationEndDate;
    }

    public void setNewFormulationEndDate(String newFormulationEndDate) {
        this.newFormulationEndDate = newFormulationEndDate;
    }

    public String getPediatricExclusiveIndicator() {
        return pediatricExclusiveIndicator;
    }

    public void setPediatricExclusiveIndicator(String pediatricExclusiveIndicator) {
        this.pediatricExclusiveIndicator = pediatricExclusiveIndicator;
    }

    public String getPediatricExclusiveStartDate() {
        return pediatricExclusiveStartDate;
    }

    public void setPediatricExclusiveStartDate(String pediatricExclusiveStartDate) {
        this.pediatricExclusiveStartDate = pediatricExclusiveStartDate;
    }

    public String getPediatricExclusiveEndDate() {
        return pediatricExclusiveEndDate;
    }

    public void setPediatricExclusiveEndDate(String pediatricExclusiveEndDate) {
        this.pediatricExclusiveEndDate = pediatricExclusiveEndDate;
    }

    public String getClottingFactorIndicator() {
        return clottingFactorIndicator;
    }

    public void setClottingFactorIndicator(String clottingFactorIndicator) {
        this.clottingFactorIndicator = clottingFactorIndicator;
    }

    public String getClottingFactorStartDate() {
        return clottingFactorStartDate;
    }

    public void setClottingFactorStartDate(String clottingFactorStartDate) {
        this.clottingFactorStartDate = clottingFactorStartDate;
    }

    public String getClottingFactorEndDate() {
        return clottingFactorEndDate;
    }

    public void setClottingFactorEndDate(String clottingFactorEndDate) {
        this.clottingFactorEndDate = clottingFactorEndDate;
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

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public int getShelfLifeType() {
        return shelfLifeType;
    }

    public void setShelfLifeType(int shelfLifeType) {
        this.shelfLifeType = shelfLifeType;
    }

    public String getDualPricingIndicator() {
        return dualPricingIndicator;
    }

    public void setDualPricingIndicator(String dualPricingIndicator) {
        this.dualPricingIndicator = dualPricingIndicator;
    }

    public String getItemFamilyId() {
        return itemFamilyId;
    }

    public void setItemFamilyId(String itemFamilyId) {
        this.itemFamilyId = itemFamilyId;
    }

    public String getUdc1() {
        return udc1;
    }

    public void setUdc1(String udc1) {
        this.udc1 = udc1;
    }

    public boolean isItemIndicator() {
        return itemIndicator;
    }

    public void setItemIndicator(boolean itemIndicator) {
        this.itemIndicator = itemIndicator;
    }

    public String getUdc2() {
        return udc2;
    }

    public void setUdc2(String udc2) {
        this.udc2 = udc2;
    }

    public String getUdc3() {
        return udc3;
    }

    public void setUdc3(String udc3) {
        this.udc3 = udc3;
    }

    public String getUdc4() {
        return udc4;
    }

    public void setUdc4(String udc4) {
        this.udc4 = udc4;
    }

    public String getUdc5() {
        return udc5;
    }

    public void setUdc5(String udc5) {
        this.udc5 = udc5;
    }

    public String getUdc6() {
        return udc6;
    }

    public void setUdc6(String udc6) {
        this.udc6 = udc6;
    }

    public BigDecimal getAcquiredAmp() {
        return acquiredAmp;
    }

    public void setAcquiredAmp(BigDecimal acquiredAmp) {
        this.acquiredAmp = acquiredAmp;
    }

    public BigDecimal getAcquiredBamp() {
        return acquiredBamp;
    }

    public void setAcquiredBamp(BigDecimal acquiredBamp) {
        this.acquiredBamp = acquiredBamp;
    }

    public BigDecimal getObraBamp() {
        return obraBamp;
    }

    public void setObraBamp(BigDecimal obraBamp) {
        this.obraBamp = obraBamp;
    }

    public BigDecimal getDra() {
        return dra;
    }

    public void setDra(BigDecimal dra) {
        this.dra = dra;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getItemMasterStartDate() {
        return itemMasterStartDate == null ? null : (Date) itemMasterStartDate.clone();
    }

    public void setItemMasterStartDate(Date itemMasterStartDate) {
        this.itemMasterStartDate = itemMasterStartDate == null ? null : (Date) itemMasterStartDate.clone();
    }

    public Date getItemMasterEndDate() {
        return itemMasterEndDate == null ? null : (Date) itemMasterEndDate.clone();
    }

    public void setItemMasterEndDate(Date itemMasterEndDate) {
        this.itemMasterEndDate = itemMasterEndDate == null ? null : (Date) itemMasterEndDate.clone();
    }

    public Date getPediatricExcStartDate() {
        return pediatricExcStartDate == null ? null : (Date) pediatricExcStartDate.clone();
    }

    public void setPediatricExcStartDate(Date pediatricExcStartDate) {
        this.pediatricExcStartDate = pediatricExcStartDate == null ? null : (Date) pediatricExcStartDate.clone();
    }

    public Date getPediatricExcEndDate() {
        return pediatricExcEndDate == null ? null : (Date) pediatricExcEndDate.clone();
    }

    public void setPediatricExcEndDate(Date pediatricExcEndDate) {
        this.pediatricExcEndDate = pediatricExcEndDate == null ? null : (Date) pediatricExcEndDate.clone();
    }

    public Date getClottingFactStartDate() {
        return clottingFactStartDate == null ? null : (Date) clottingFactStartDate.clone();
    }

    public void setClottingFactStartDate(Date clottingFactStartDate) {
        this.clottingFactStartDate = clottingFactStartDate == null ? null : (Date) clottingFactStartDate.clone();
    }

    public Date getClottingFactEndDate() {
        return clottingFactEndDate == null ? null : (Date) clottingFactEndDate.clone();
    }

    public void setClottingFactEndDate(Date clottingFactEndDate) {
        this.clottingFactEndDate = clottingFactEndDate == null ? null : (Date) clottingFactEndDate.clone();
    }

    public HelperDTO getNdc9() {
        return ndc9;
    }

    public void setNdc9(HelperDTO ndc9) {
        this.ndc9 = ndc9;
    }

    public HelperDTO getNdc8() {
        return ndc8;
    }

    public void setNdc8(HelperDTO ndc8) {
        this.ndc8 = ndc8;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public String getDosesPerUnit() {
        return dosesPerUnit;
    }

    public void setDosesPerUnit(String dosesPerUnit) {
        this.dosesPerUnit = dosesPerUnit;
    }

    public Character getInboundStatus() {
        return inboundStatus;
    }

    public void setInboundStatus(Character inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public HelperDTO getBrandDdlb() {
        return brandDdlb;
    }

    public void setBrandDdlb(HelperDTO brandDdlb) {
        this.brandDdlb = brandDdlb;
    }

    public HelperDTO getItemIrtQualifierNameDDLB() {
        return itemIrtQualifierNameDDLB;
    }

    public void setItemIrtQualifierNameDDLB(HelperDTO itemIrtQualifierNameDDLB) {
        this.itemIrtQualifierNameDDLB = itemIrtQualifierNameDDLB;
    }

    public String getCompanySystemId() {
        return companySystemId;
    }

    public void setCompanySystemId(String companySystemId) {
        this.companySystemId = companySystemId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompStartDate() {
        return compStartDate;
    }

    public void setCompStartDate(String compStartDate) {
        this.compStartDate = compStartDate;
    }

    public String getCompEndDate() {
        return compEndDate;
    }

    public void setCompEndDate(String compEndDate) {
        this.compEndDate = compEndDate;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public Date getTradeStartDate() {
        return tradeStartDate == null ? null : (Date) tradeStartDate.clone();
    }

    public void setTradeStartDate(Date tradeStartDate) {
        this.tradeStartDate = tradeStartDate == null ? null : (Date) tradeStartDate.clone();
    }

    public Date getTradeEndDate() {
        return tradeEndDate == null ? null : (Date) tradeEndDate.clone();
    }

    public void setTradeEndDate(Date tradeEndDate) {
        this.tradeEndDate = tradeEndDate == null ? null : (Date) tradeEndDate.clone();
    }

    public String getLives() {
        return lives;
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

    public String getCompanyGroup() {
        return companyGroup;
    }

    public void setCompanyGroup(String companyGroup) {
        this.companyGroup = companyGroup;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }
 
    
    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

    public String getCompanyorganizationKey() {
        return companyorganizationKey;
    }

    public void setCompanyorganizationKey(String companyorganizationKey) {
        this.companyorganizationKey = companyorganizationKey;
    }

    public String getFinancialSystem() {
        return financialSystem;
    }

    public void setFinancialSystem(String financialSystem) {
        this.financialSystem = financialSystem;
    }

    public String getParentCompanyNo() {
        return parentCompanyNo;
    }

    public void setParentCompanyNo(String parentCompanyNo) {
        this.parentCompanyNo = parentCompanyNo;
    }

    public Date getParentSDate() {
        return parentSDate == null ? null : (Date) parentSDate.clone();
    }

    public void setParentSDate(Date parentSDate) {
        this.parentSDate = parentSDate == null ? null : (Date) parentSDate.clone();
    }

    public Date getParentEDate() {
        return parentEDate == null ? null : (Date) parentEDate.clone();
    }

    public void setParentEDate(Date parentEDate) {
        this.parentEDate = parentEDate == null ? null : (Date) parentEDate.clone();
    }

    public String getPriorParentCompanyNo() {
        return priorParentCompanyNo;
    }

    public void setPriorParentCompanyNo(String priorParentCompanyNo) {
        this.priorParentCompanyNo = priorParentCompanyNo;
    }

    public Date getPriorParentSDate() {
        return priorParentSDate == null ? null : (Date) priorParentSDate.clone();
    }

    public void setPriorParentSDate(Date priorParentSDate) {
        this.priorParentSDate = priorParentSDate == null ? null : (Date) priorParentSDate.clone();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCompanyudc1() {
        return companyudc1;
    }

    public void setCompanyudc1(String companyudc1) {
        this.companyudc1 = companyudc1;
    }

    public String getCompanyudc2() {
        return companyudc2;
    }

    public void setCompanyudc2(String companyudc2) {
        this.companyudc2 = companyudc2;
    }

    public String getCompanyudc3() {
        return companyudc3;
    }

    public void setCompanyudc3(String companyudc3) {
        this.companyudc3 = companyudc3;
    }

    public String getCompanyudc4() {
        return companyudc4;
    }

    public void setCompanyudc4(String companyudc4) {
        this.companyudc4 = companyudc4;
    }

    public String getCompanyudc5() {
        return companyudc5;
    }

    public void setCompanyudc5(String companyudc5) {
        this.companyudc5 = companyudc5;
    }

    public String getCompanyudc6() {
        return companyudc6;
    }

    public void setCompanyudc6(String companyudc6) {
        this.companyudc6 = companyudc6;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyQualifierName() {
        return companyQualifierName;
    }

    public void setCompanyQualifierName(String companyQualifierName) {
        this.companyQualifierName = companyQualifierName;
    }

    public String getCompanyIdentifier() {
        return companyIdentifier;
    }

    public void setCompanyIdentifier(String companyIdentifier) {
        this.companyIdentifier = companyIdentifier;
    }

    public HelperDTO getIdentifierTypeDesc() {
        return identifierTypeDesc;
    }

    public void setIdentifierTypeDesc(HelperDTO identifierTypeDesc) {
        this.identifierTypeDesc = identifierTypeDesc;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    public String getParentStartDate() {
        return parentStartDate;
    }

    public void setParentStartDate(String parentStartDate) {
        this.parentStartDate = parentStartDate;
    }

    public Date getComStartDate() {
        return comStartDate == null ? null : (Date) comStartDate.clone();
    }

    public void setComStartDate(Date comStartDate) {
        this.comStartDate = comStartDate == null ? null : (Date) comStartDate.clone();
    }

    public Date getComEndDate() {
        return comEndDate == null ? null : (Date) comEndDate.clone();
    }

    public void setComEndDate(Date comEndDate) {
        this.comEndDate = comEndDate == null ? null : (Date) comEndDate.clone();
    }

    public Date getComTradeStartDate() {
        return comTradeStartDate == null ? null : (Date) comTradeStartDate.clone();
    }

    public void setComTradeStartDate(Date comTradeStartDate) {
        this.comTradeStartDate = comTradeStartDate == null ? null : (Date) comTradeStartDate.clone();
    }

    public Date getComTradeEndDate() {
        return comTradeEndDate == null ? null : (Date) comTradeEndDate.clone();
    }

    public void setComTradeEndDate(Date comTradeEndDate) {
        this.comTradeEndDate = comTradeEndDate == null ? null : (Date) comTradeEndDate.clone();
    }

    public Date getComParentStartDate() {
        return comParentStartDate == null ? null : (Date) comParentStartDate.clone();
    }

    public void setComParentStartDate(Date comParentStartDate) {
        this.comParentStartDate = comParentStartDate == null ? null : (Date) comParentStartDate.clone();
    }

    public Date getComParentEndDate() {
        return comParentEndDate == null ? null : (Date) comParentEndDate.clone();
    }

    public void setComParentEndDate(Date comParentEndDate) {
        this.comParentEndDate = comParentEndDate == null ? null : (Date) comParentEndDate.clone();
    }

    public Date getPriorParentStartDate() {
        return priorParentStartDate == null ? null : (Date) priorParentStartDate.clone();
    }

    public void setPriorParentStartDate(Date priorParentStartDate) {
        this.priorParentStartDate = priorParentStartDate == null ? null : (Date) priorParentStartDate.clone();
    }

    public String getPrntEndDate() {
        return prntEndDate;
    }

    public void setPrntEndDate(String prntEndDate) {
        this.prntEndDate = prntEndDate;
    }

    public String getParentCompanyFamilyPlanName() {
        return parentCompanyFamilyPlanName;
    }

    public void setParentCompanyFamilyPlanName(String parentCompanyFamilyPlanName) {
        this.parentCompanyFamilyPlanName = parentCompanyFamilyPlanName;
    }

    public int getCfpsystemId() {
        return cfpsystemId;
    }

    public void setCfpsystemId(int cfpsystemId) {
        this.cfpsystemId = cfpsystemId;
    }

    public String getCompanyFamilyPlanId() {
        return companyFamilyPlanId;
    }

    public void setCompanyFamilyPlanId(String companyFamilyPlanId) {
        this.companyFamilyPlanId = companyFamilyPlanId;
    }

    public String getCompanyFamilyPlanName() {
        return companyFamilyPlanName;
    }

    public void setCompanyFamilyPlanName(String companyFamilyPlanName) {
        this.companyFamilyPlanName = companyFamilyPlanName;
    }

    public String getCompanyFamilyPlanType() {
        return companyFamilyPlanType;
    }

    public void setCompanyFamilyPlanType(String companyFamilyPlanType) {
        this.companyFamilyPlanType = companyFamilyPlanType;
    }

    public String getCompanyFamilyPlanDesignation() {
        return companyFamilyPlanDesignation;
    }

    public void setCompanyFamilyPlanDesignation(String companyFamilyPlanDesignation) {
        this.companyFamilyPlanDesignation = companyFamilyPlanDesignation;
    }

    public String getCompanyFamilyPlanNo() {
        return companyFamilyPlanNo;
    }

    public void setCompanyFamilyPlanNo(String companyFamilyPlanNo) {
        this.companyFamilyPlanNo = companyFamilyPlanNo;
    }

    public String getCompanyFamilyPlanStatus() {
        return companyFamilyPlanStatus;
    }

    public void setCompanyFamilyPlanStatus(String companyFamilyPlanStatus) {
        this.companyFamilyPlanStatus = companyFamilyPlanStatus;
    }

    public Date getCompanyFamilyPlanStartDate() {
        return companyFamilyPlanStartDate == null ? null : (Date) companyFamilyPlanStartDate.clone();
    }

    public void setCompanyFamilyPlanStartDate(Date companyFamilyPlanStartDate) {
        this.companyFamilyPlanStartDate = companyFamilyPlanStartDate == null ? null : (Date) companyFamilyPlanStartDate.clone();
    }

    public Date getCompanyFamilyPlanEndDate() {
        return companyFamilyPlanEndDate == null ? null : (Date) companyFamilyPlanEndDate.clone();
    }

    public void setCompanyFamilyPlanEndDate(Date companyFamilyPlanEndDate) {
        this.companyFamilyPlanEndDate = companyFamilyPlanEndDate == null ? null : (Date) companyFamilyPlanEndDate.clone();
    }

    public String getParentCompanyFamilyPlanId() {
        return parentCompanyFamilyPlanId;
    }

    public void setParentCompanyFamilyPlanId(String parentCompanyFamilyPlanId) {
        this.parentCompanyFamilyPlanId = parentCompanyFamilyPlanId;
    }

    public String getCompanyFamilyPlanCategory() {
        return companyFamilyPlanCategory;
    }

    public void setCompanyFamilyPlanCategory(String companyFamilyPlanCategory) {
        this.companyFamilyPlanCategory = companyFamilyPlanCategory;
    }

    public String getCompanyFamilyPlanTradeClass() {
        return companyFamilyPlanTradeClass;
    }

    public void setCompanyFamilyPlanTradeClass(String companyFamilyPlanTradeClass) {
        this.companyFamilyPlanTradeClass = companyFamilyPlanTradeClass;
    }

    public String getCompanyFamilyPlanSystemId() {
        return companyFamilyPlanSystemId;
    }

    public void setCompanyFamilyPlanSystemId(String companyFamilyPlanSystemId) {
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
    }

    public Date getCfpmodifiedDate() {
        return cfpmodifiedDate == null ? null : (Date) cfpmodifiedDate.clone();
    }

    public void setCfpmodifiedDate(Date cfpmodifiedDate) {
        this.cfpmodifiedDate = cfpmodifiedDate == null ? null : (Date) cfpmodifiedDate.clone();
    }

    public String getCfpmodifiedBy() {
        return cfpmodifiedBy;
    }

    public void setCfpmodifiedBy(String cfpmodifiedBy) {
        this.cfpmodifiedBy = cfpmodifiedBy;
    }

    public String getCfpcreatedBy() {
        return cfpcreatedBy;
    }

    public void setCfpcreatedBy(String cfpcreatedBy) {
        this.cfpcreatedBy = cfpcreatedBy;
    }

    public Date getCfpcreatedDate() {
        return cfpcreatedDate == null ? null : (Date) cfpcreatedDate.clone();
    }

    public void setCfpcreatedDate(Date cfpcreatedDate) {
        this.cfpcreatedDate = cfpcreatedDate == null ? null : (Date) cfpcreatedDate.clone();
    }

   public String getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    public void setItemFamilyplanSystemId(String itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    public String getIfpId() {
        return ifpId;
    }

    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
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

    public String getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(String ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public String getIfpType() {
        return ifpType;
    }

    public void setIfpType(String ifpType) {
        this.ifpType = ifpType;
    }

    public String getIfpCategory() {
        return ifpCategory;
    }

    public void setIfpCategory(String ifpCategory) {
        this.ifpCategory = ifpCategory;
    }

    public Date getItemFamilyplanStartDate() {
        return itemFamilyplanStartDate == null ? null : (Date) itemFamilyplanStartDate.clone();
    }

    public void setItemFamilyplanStartDate(Date itemFamilyplanStartDate) {
        this.itemFamilyplanStartDate = itemFamilyplanStartDate == null ? null : (Date) itemFamilyplanStartDate.clone();
    }

    public Date getItemFamilyplanEndDate() {
        return itemFamilyplanEndDate == null ? null : (Date) itemFamilyplanEndDate.clone();
    }

    public void setItemFamilyplanEndDate(Date itemFamilyplanEndDate) {
        this.itemFamilyplanEndDate = itemFamilyplanEndDate == null ? null : (Date) itemFamilyplanEndDate.clone();
    }

    public String getTotalMarketshareCommitment() {
        return totalMarketshareCommitment;
    }

    public void setTotalMarketshareCommitment(String totalMarketshareCommitment) {
        this.totalMarketshareCommitment = totalMarketshareCommitment;
    }

    public String getTotalVolumeCommitment() {
        return totalVolumeCommitment;
    }

    public void setTotalVolumeCommitment(String totalVolumeCommitment) {
        this.totalVolumeCommitment = totalVolumeCommitment;
    }

    public String getCommitmentPeriod() {
        return commitmentPeriod;
    }

    public void setCommitmentPeriod(String commitmentPeriod) {
        this.commitmentPeriod = commitmentPeriod;
    }

    public String getTotalDollarCommitment() {
        return totalDollarCommitment;
    }

    public void setTotalDollarCommitment(String totalDollarCommitment) {
        this.totalDollarCommitment = totalDollarCommitment;
    }

    public String getIfpDesignation() {
        return ifpDesignation;
    }

    public void setIfpDesignation(String ifpDesignation) {
        this.ifpDesignation = ifpDesignation;
    }

    public String getParentItemFamilyplanName() {
        return parentItemFamilyplanName;
    }

    public void setParentItemFamilyplanName(String parentItemFamilyplanName) {
        this.parentItemFamilyplanName = parentItemFamilyplanName;
    }

    public String getParentItemFamilyplanId() {
        return parentItemFamilyplanId;
    }

    public void setParentItemFamilyplanId(String parentItemFamilyplanId) {
        this.parentItemFamilyplanId = parentItemFamilyplanId;
    }

    public String getIfpcreatedBy() {
        return ifpcreatedBy;
    }

    public void setIfpcreatedBy(String ifpcreatedBy) {
        this.ifpcreatedBy = ifpcreatedBy;
    }

    public Date getIfpcreatedDate() {
        return ifpcreatedDate == null ? null : (Date) ifpcreatedDate.clone();
    }

    public void setIfpcreatedDate(Date ifpcreatedDate) {
        this.ifpcreatedDate = ifpcreatedDate == null ? null : (Date) ifpcreatedDate.clone();
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

    public String getPsSystemId() {
        return psSystemId;
    }

    public void setPsSystemId(String psSystemId) {
        this.psSystemId = psSystemId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPriceScheduleNo() {
        return priceScheduleNo;
    }

    public void setPriceScheduleNo(String priceScheduleNo) {
        this.priceScheduleNo = priceScheduleNo;
    }

    public String getPriceScheduleName() {
        return priceScheduleName;
    }

    public void setPriceScheduleName(String priceScheduleName) {
        this.priceScheduleName = priceScheduleName;
    }

    public String getPriceScheduleId() {
        return priceScheduleId;
    }

    public void setPriceScheduleId(String priceScheduleId) {
        this.priceScheduleId = priceScheduleId;
    }

    public String getPriceScheduleType() {
        return priceScheduleType;
    }

    public void setPriceScheduleType(String priceScheduleType) {
        this.priceScheduleType = priceScheduleType;
    }

    public String getPriceScheduleStatus() {
        return priceScheduleStatus;
    }

    public void setPriceScheduleStatus(String priceScheduleStatus) {
        this.priceScheduleStatus = priceScheduleStatus;
    }

    public String getPriceScheduleCategory() {
        return priceScheduleCategory;
    }

    public void setPriceScheduleCategory(String priceScheduleCategory) {
        this.priceScheduleCategory = priceScheduleCategory;
    }

    public Date getPriceScheduleStartDate() {
        return priceScheduleStartDate == null ? null : (Date) priceScheduleStartDate.clone();
    }

    public void setPriceScheduleStartDate(Date priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate == null ? null : (Date) priceScheduleStartDate.clone();
    }

    public Date getPriceScheduleEndDate() {
        return priceScheduleEndDate == null ? null : (Date) priceScheduleEndDate.clone();
    }

    public void setPriceScheduleEndDate(Date priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate == null ? null : (Date) priceScheduleEndDate.clone();
    }

    public String getPriceScheduleDesignation() {
        return priceScheduleDesignation;
    }

    public void setPriceScheduleDesignation(String priceScheduleDesignation) {
        this.priceScheduleDesignation = priceScheduleDesignation;
    }

    public String getPstradeClass() {
        return pstradeClass;
    }

    public void setPstradeClass(String pstradeClass) {
        this.pstradeClass = pstradeClass;
    }

    public int getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    public void setPriceScheduleSystemId(int priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getRsSystemId() {
        return rsSystemId;
    }

    public void setRsSystemId(String rsSystemId) {
        this.rsSystemId = rsSystemId;
    }
    

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    public String getRebateScheduleType() {
        return rebateScheduleType;
    }

    public void setRebateScheduleType(String rebateScheduleType) {
        this.rebateScheduleType = rebateScheduleType;
    }

    public String getRebateScheduleStatus() {
        return rebateScheduleStatus;
    }

    public void setRebateScheduleStatus(String rebateScheduleStatus) {
        this.rebateScheduleStatus = rebateScheduleStatus;
    }

    public String getRsCategory() {
        return rsCategory;
    }

    public void setRsCategory(String rsCategory) {
        this.rsCategory = rsCategory;
    }

    public String getRsProgramType() {
        return rsProgramType;
    }

    public void setRsProgramType(String rsProgramType) {
        this.rsProgramType = rsProgramType;
    }

    public String getRsTradeClass() {
        return rsTradeClass;
    }

    public void setRsTradeClass(String rsTradeClass) {
        this.rsTradeClass = rsTradeClass;
    }

    public String getRebateScheduleAliasID() {
        return rebateScheduleAliasID;
    }

    public void setRebateScheduleAliasID(String rebateScheduleAliasID) {
        this.rebateScheduleAliasID = rebateScheduleAliasID;
    }

    public String getRebateFrequency() {
        return rebateFrequency;
    }

    public void setRebateFrequency(String rebateFrequency) {
        this.rebateFrequency = rebateFrequency;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public String getCalculationLevel() {
        return calculationLevel;
    }

    public void setCalculationLevel(String calculationLevel) {
        this.calculationLevel = calculationLevel;
    }

    public String getRebateRuleType() {
        return rebateRuleType;
    }

    public void setRebateRuleType(String rebateRuleType) {
        this.rebateRuleType = rebateRuleType;
    }

    public String getRebateRuleAssociation() {
        return rebateRuleAssociation;
    }

    public void setRebateRuleAssociation(String rebateRuleAssociation) {
        this.rebateRuleAssociation = rebateRuleAssociation;
    }

    public String getPaymentLevel() {
        return paymentLevel;
    }

    public void setPaymentLevel(String paymentLevel) {
        this.paymentLevel = paymentLevel;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentGracePeriod() {
        return paymentGracePeriod;
    }

    public void setPaymentGracePeriod(String paymentGracePeriod) {
        this.paymentGracePeriod = paymentGracePeriod;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public String getInterestBearingIndicator() {
        return interestBearingIndicator;
    }

    public void setInterestBearingIndicator(String interestBearingIndicator) {
        this.interestBearingIndicator = interestBearingIndicator;
    }

    public String getInterestBearingBasis() {
        return interestBearingBasis;
    }

    public void setInterestBearingBasis(String interestBearingBasis) {
        this.interestBearingBasis = interestBearingBasis;
    }

    public String getRsTransactionReferenceID() {
        return rsTransactionReferenceID;
    }

    public void setRsTransactionReferenceID(String rsTransactionReferenceID) {
        this.rsTransactionReferenceID = rsTransactionReferenceID;
    }

    public String getRsTransactionReferenceName() {
        return rsTransactionReferenceName;
    }

    public void setRsTransactionReferenceName(String rsTransactionReferenceName) {
        this.rsTransactionReferenceName = rsTransactionReferenceName;
    }
    
    public String getRsDesignation() {
        return rsDesignation;
    }

    public void setRsDesignation(String rsDesignation) {
        this.rsDesignation = rsDesignation;
    }

    public String getRsParentId() {
        return rsParentId;
    }

    public void setRsParentId(String rsParentId) {
        this.rsParentId = rsParentId;
    }

    public String getRsParentName() {
        return rsParentName;
    }

    public void setRsParentName(String rsParentName) {
        this.rsParentName = rsParentName;
    }

    public String getRebateScheduleId() {
        return rebateScheduleId;
    }

    public void setRebateScheduleId(String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    public String getRebatePlanId() {
        return rebatePlanId;
    }

    public void setRebatePlanId(String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }

    public String getRebatePlanNo() {
        return rebatePlanNo;
    }

    public void setRebatePlanNo(String rebatePlanNo) {
        this.rebatePlanNo = rebatePlanNo;
    }

    public String getRebatePlanName() {
        return rebatePlanName;
    }

    public void setRebatePlanName(String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public String getSecondaryRebatePlanId() {
        return secondaryRebatePlanId;
    }

    public void setSecondaryRebatePlanId(String secondaryRebatePlanId) {
        this.secondaryRebatePlanId = secondaryRebatePlanId;
    }

    public String getSecondaryRebatePlanNo() {
        return secondaryRebatePlanNo;
    }

    public void setSecondaryRebatePlanNo(String secondaryRebatePlanNo) {
        this.secondaryRebatePlanNo = secondaryRebatePlanNo;
    }

    public String getSecondaryRebatePlanName() {
        return secondaryRebatePlanName;
    }

    public void setSecondaryRebatePlanName(String secondaryRebatePlanName) {
        this.secondaryRebatePlanName = secondaryRebatePlanName;
    }

    public String getRebatePlanType() {
        return rebatePlanType;
    }

    public void setRebatePlanType(String rebatePlanType) {
        this.rebatePlanType = rebatePlanType;
    }

    public String getRebatePlanStatus() {
        return rebatePlanStatus;
    }

    public void setRebatePlanStatus(String rebatePlanStatus) {
        this.rebatePlanStatus = rebatePlanStatus;
    }

    public String getRebateBasedOn() {
        return rebateBasedOn;
    }

    public void setRebateBasedOn(String rebateBasedOn) {
        this.rebateBasedOn = rebateBasedOn;
    }

    public String getRebateStructure() {
        return rebateStructure;
    }

    public void setRebateStructure(String rebateStructure) {
        this.rebateStructure = rebateStructure;
    }

    public String getRebateRangeBasedOn() {
        return rebateRangeBasedOn;
    }

    public void setRebateRangeBasedOn(String rebateRangeBasedOn) {
        this.rebateRangeBasedOn = rebateRangeBasedOn;
    }

    public String getNetSalesFormulaType() {
        return netSalesFormulaType;
    }

    public void setNetSalesFormulaType(String netSalesFormulaType) {
        this.netSalesFormulaType = netSalesFormulaType;
    }

    public String getNetSalesFormulaId() {
        return netSalesFormulaId;
    }

    public void setNetSalesFormulaId(String netSalesFormulaId) {
        this.netSalesFormulaId = netSalesFormulaId;
    }

    public String getNetSalesFormulaNo() {
        return netSalesFormulaNo;
    }

    public void setNetSalesFormulaNo(String netSalesFormulaNo) {
        this.netSalesFormulaNo = netSalesFormulaNo;
    }

    public String getNetSalesFormulaName() {
        return netSalesFormulaName;
    }

    public void setNetSalesFormulaName(String netSalesFormulaName) {
        this.netSalesFormulaName = netSalesFormulaName;
    }

    public Date getNsfcreatedDate() {
        return nsfcreatedDate == null ? null : (Date) nsfcreatedDate.clone();
    }

    public void setNsfcreatedDate(Date nsfcreatedDate) {
        this.nsfcreatedDate = nsfcreatedDate == null ? null : (Date) nsfcreatedDate.clone();
    }

    public String getNsfcreatedBy() {
        return nsfcreatedBy;
    }

    public void setNsfcreatedBy(String nsfcreatedBy) {
        this.nsfcreatedBy = nsfcreatedBy;
    }

    public Date getNsfmodifiedDate() {
        return nsfmodifiedDate == null ? null : (Date) nsfmodifiedDate.clone();
    }

    public void setNsfmodifiedDate(Date nsfmodifiedDate) {
        this.nsfmodifiedDate = nsfmodifiedDate == null ? null : (Date) nsfmodifiedDate.clone();
    }

    public String getNsfmodifiedBy() {
        return nsfmodifiedBy;
    }

    public void setNsfmodifiedBy(String nsfmodifiedBy) {
        this.nsfmodifiedBy = nsfmodifiedBy;
    }

    public String getDeductionCalendarno() {
        return deductionCalendarno;
    }

    public void setDeductionCalendarno(String deductionCalendarno) {
        this.deductionCalendarno = deductionCalendarno;
    }

    public String getDeductionCalendarname() {
        return deductionCalendarname;
    }

    public void setDeductionCalendarname(String deductionCalendarname) {
        this.deductionCalendarname = deductionCalendarname;
    }

    public String getDeductionCalendardesc() {
        return deductionCalendardesc;
    }

    public void setDeductionCalendardesc(String deductionCalendardesc) {
        this.deductionCalendardesc = deductionCalendardesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDcCreatedBy() {
        return dcCreatedBy;
    }

    public void setDcCreatedBy(String dcCreatedBy) {
        this.dcCreatedBy = dcCreatedBy;
    }

    public Date getDcCreationDate() {
        return dcCreationDate == null ? null : (Date) dcCreationDate.clone();
    }

    public void setDcCreationDate(Date dcCreationDate) {
        this.dcCreationDate = dcCreationDate == null ? null : (Date) dcCreationDate.clone();
    }

    public Date getDcModifiedDate() {
        return dcModifiedDate == null ? null : (Date) dcModifiedDate.clone();
    }

    public void setDcModifiedDate(Date dcModifiedDate) {
        this.dcModifiedDate = dcModifiedDate == null ? null : (Date) dcModifiedDate.clone();
    }

    public String getDcModifiedBy() {
        return dcModifiedBy;
    }

    public void setDcModifiedBy(String dcModifiedBy) {
        this.dcModifiedBy = dcModifiedBy;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleCategory() {
        return ruleCategory;
    }

    public void setRuleCategory(String ruleCategory) {
        this.ruleCategory = ruleCategory;
    }

    public Date getCdrCreatedDate() {
        return cdrCreatedDate == null ? null : (Date) cdrCreatedDate.clone();
    }

    public void setCdrCreatedDate(Date cdrCreatedDate) {
        this.cdrCreatedDate = cdrCreatedDate == null ? null : (Date) cdrCreatedDate.clone();
    }

    public Date getCdrModifiedDate() {
        return cdrModifiedDate == null ? null : (Date) cdrModifiedDate.clone();
    }

    public void setCdrModifiedDate(Date cdrModifiedDate) {
        this.cdrModifiedDate = cdrModifiedDate == null ? null : (Date) cdrModifiedDate.clone();
    }
   
    public String getNsfcreateDate() {
        return nsfcreateDate;
    }

    public void setNsfcreateDate(String nsfcreateDate) {
        this.nsfcreateDate = nsfcreateDate;
    }

    public String getNsfmodifyDate() {
        return nsfmodifyDate;
    }

    public void setNsfmodifyDate(String nsfmodifyDate) {
        this.nsfmodifyDate = nsfmodifyDate;
    }
    
     public int getDeductionCalendarSid() {
        return deductionCalendarSid;
     }
    
     public void setDeductionCalendarSid(int deductionCalendarSid) {
        this.deductionCalendarSid = deductionCalendarSid;
     }

    public String getRebateNsfName() {
        return rebateNsfName;
    }

    public void setRebateNsfName(String rebateNsfName) {
        this.rebateNsfName = rebateNsfName;
    }

    public String getRebateRuleName() {
        return rebateRuleName;
    }

    public void setRebateRuleName(String rebateRuleName) {
        this.rebateRuleName = rebateRuleName;
    }

    public Date getRpCreatedDate() {
        return rpCreatedDate == null ? null : (Date) rpCreatedDate.clone();
    }

    public void setRpCreatedDate(Date rpCreatedDate) {
        this.rpCreatedDate = rpCreatedDate == null ? null : (Date) rpCreatedDate.clone();
    }

    public Date getRpModifiedDate() {
        return rpModifiedDate == null ? null : (Date) rpModifiedDate.clone();
    }

    public void setRpModifiedDate(Date rpModifiedDate) {
        this.rpModifiedDate = rpModifiedDate == null ? null : (Date) rpModifiedDate.clone();
    }

    public String getRpCreationDate() {
        return rpCreationDate;
    }

    public void setRpCreationDate(String rpCreationDate) {
        this.rpCreationDate = rpCreationDate;
    }

    public String getRpModifyDate() {
        return rpModifyDate;
    }

    public void setRpModifyDate(String rpModifyDate) {
        this.rpModifyDate = rpModifyDate;
    }

    public String getItemBatchId() {
        return itemBatchId;
    }

    public void setItemBatchId(String itemBatchId) {
        this.itemBatchId = itemBatchId;
    }
        
}
