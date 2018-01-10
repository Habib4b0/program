/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.deductioncalendar.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import com.stpl.app.util.ConstantsUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class SelectionDTO {
  
    //Additional Search Fields.
    private String tradeClassSearch = StringUtils.EMPTY;
    
    
  // Customer Selection
    private HelperDTO organizationKey=new HelperDTO(0, "");
    private String customerId=StringUtils.EMPTY;
    private String customerNo=StringUtils.EMPTY;
    private String customerName=StringUtils.EMPTY;
    private HelperDTO tradeClass = new HelperDTO(0, "");
    private Date tradeClassStartDate ;
    private Date tradeClassEndDate ;
    private HelperDTO customerType=new HelperDTO(0, "");
    private HelperDTO customerStatus=new HelperDTO(0, "");
    private String lives=StringUtils.EMPTY;
    private Date customerStartDate ;
    private Date customerEndDate ;
    private HelperDTO udc1 = new HelperDTO(0, "");
    private HelperDTO udc2 = new HelperDTO(0, "");
    private HelperDTO udc3 = new HelperDTO(0, "");
    private HelperDTO udc4 = new HelperDTO(0, "");
    private HelperDTO udc5 = new HelperDTO(0, "");
    private HelperDTO udc6 = new HelperDTO(0, "");
    private HelperDTO customerGroup = new HelperDTO(0, "");
    private String financialSystem = StringUtils.EMPTY;
    private String address1 = StringUtils.EMPTY;
    private String address2 = StringUtils.EMPTY;
    private String city = StringUtils.EMPTY;
    private String state = StringUtils.EMPTY;
    private String zipCode = StringUtils.EMPTY;
    private String country = StringUtils.EMPTY;
    private String regionCode = StringUtils.EMPTY;
    private String parentCustomerNo = StringUtils.EMPTY;
    private Date parentStartDate ;
    private Date parentEndDate ;
    private Date priorParentStartDate ;
    private String priorParentCustomerNo = StringUtils.EMPTY;
    private String customer;
    private Boolean checkbox;  
    private int companyMasterSid = 0;
    private int parentCompanyMasterSid = 0;
    private String userId = StringUtils.EMPTY;
    private String sessionId = StringUtils.EMPTY;
    
    //product selection
    private String itemId=StringUtils.EMPTY;
    private String item=StringUtils.EMPTY;
    private String itemCode=StringUtils.EMPTY;
    private String itemName=StringUtils.EMPTY;
    private String itemDesc=StringUtils.EMPTY;
    private Date itemStartDate;
    private Date itemEndDate;

    public SelectionDTO() {
        super();
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
    private String itemStatus=StringUtils.EMPTY;
    private String therapeuticClass=StringUtils.EMPTY;
    private String brand=StringUtils.EMPTY;
    private String form=StringUtils.EMPTY;
    private String strength=StringUtils.EMPTY;
    private String packageSizeCode=StringUtils.EMPTY;
    private Date packageSizeIntroDate;
    private String psUP=StringUtils.EMPTY;
    private String manufacturerID=StringUtils.EMPTY;
    private String manufacturerNO=StringUtils.EMPTY;
    private String manufacturerName=StringUtils.EMPTY;
    private String labelerCode=StringUtils.EMPTY;
    private String productOrganizationKey=StringUtils.EMPTY;
    private Date acquisitionDate;
    private String authorizedGeneric=StringUtils.EMPTY;
    private Date authorizedGenericStartDate;
    private Date authorizedGenericEndDate;
    private Date firstSaleDate;
    private String itemTypeIndicator=StringUtils.EMPTY;
    private String itemClass=StringUtils.EMPTY;
    private String itemType=StringUtils.EMPTY;
    private Date marketTerminationDate;
    private String newFormulationIndicator=StringUtils.EMPTY;
    private String newFormulation=StringUtils.EMPTY;
    private Date newFormulationStartDate;
    private Date newFormulationEndDate;
    private String pediatricExclusiveIndicator=StringUtils.EMPTY;
    private Date pediatricExclusiveStartDate;
    private Date pediatricExclusiveEndDate;
    private String clottingFactorIndicator=StringUtils.EMPTY;
    private String clottingFactorStartDate;
    private String clottingFactorEndDate;
    private String primaryUOM=StringUtils.EMPTY;
    private String secondaryUOM=StringUtils.EMPTY;
    private String shelfLife=StringUtils.EMPTY;
    private String shelfLifeType=StringUtils.EMPTY;
    private String dualPricingIndicator=StringUtils.EMPTY;
    private String itemFamilyID=StringUtils.EMPTY;
    private String psUDC1=StringUtils.EMPTY;
    private String psUDC2=StringUtils.EMPTY;
    private String psUDC3=StringUtils.EMPTY;
    private String psUDC4=StringUtils.EMPTY;
    private String psUDC5=StringUtils.EMPTY;
    private String psUDC6=StringUtils.EMPTY;
    private String acquiredAMP=StringUtils.EMPTY;
    private String acquiredBAMP=StringUtils.EMPTY;
    private String psOBRABAMP=StringUtils.EMPTY;
    private String psDRA=StringUtils.EMPTY;
    private String dosesperUnit=StringUtils.EMPTY;
    private Date discontinuationDate;
    private Date lastLotExpirationDate;
    private String psNDC9=StringUtils.EMPTY;
    private String psNDC8=StringUtils.EMPTY;
    private String displayBrand=StringUtils.EMPTY;
    private String innovatorCode=StringUtils.EMPTY;
    private String baselineAMP=StringUtils.EMPTY;
    private Date baseYearCPI;
    private String itemSystemID = StringUtils.EMPTY;
    private String systemID = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private HelperDTO itemIrtQualifierName;
    private String itemIdentifier = StringUtils.EMPTY;
    private Boolean recordLockStatus;
    private String packageSize = StringUtils.EMPTY;
    private String upps;
    private HelperDTO ndc8 = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private HelperDTO ndc9 = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private com.stpl.ifs.util.HelperDTO itemTypeDdlb = new com.stpl.ifs.util.HelperDTO(ConstantsUtils.SELECT_ONE);
    private com.stpl.ifs.util.HelperDTO therapeuticclassDdlb = new com.stpl.ifs.util.HelperDTO(ConstantsUtils.SELECT_ONE);
    private com.stpl.ifs.util.HelperDTO strengthDdlb = new com.stpl.ifs.util.HelperDTO(ConstantsUtils.SELECT_ONE);
    private com.stpl.ifs.util.HelperDTO formDdlb = new com.stpl.ifs.util.HelperDTO(ConstantsUtils.SELECT_ONE);

    public com.stpl.ifs.util.HelperDTO getItemTypeDdlb() {
        return itemTypeDdlb;
    }

    public void setItemTypeDdlb(com.stpl.ifs.util.HelperDTO itemTypeDdlb) {
        this.itemTypeDdlb = itemTypeDdlb;
    }

    public com.stpl.ifs.util.HelperDTO getTherapeuticclassDdlb() {
        return therapeuticclassDdlb;
    }

    public void setTherapeuticclassDdlb(com.stpl.ifs.util.HelperDTO therapeuticclassDdlb) {
        this.therapeuticclassDdlb = therapeuticclassDdlb;
    }

    public com.stpl.ifs.util.HelperDTO getStrengthDdlb() {
        return strengthDdlb;
    }

    public void setStrengthDdlb(com.stpl.ifs.util.HelperDTO strengthDdlb) {
        this.strengthDdlb = strengthDdlb;
    }

    public com.stpl.ifs.util.HelperDTO getFormDdlb() {
        return formDdlb;
    }

    public void setFormDdlb(com.stpl.ifs.util.HelperDTO formDdlb) {
        this.formDdlb = formDdlb;
    }
            
    /** The brand. */
    private com.stpl.app.util.HelperDTO brandDdlb;

    public com.stpl.app.util.HelperDTO getBrandDdlb() {
        return brandDdlb;
    }

    public void setBrandDdlb(com.stpl.app.util.HelperDTO brandDdlb) {
        this.brandDdlb = brandDdlb;
    }

    public String getItemSystemID() {
        return itemSystemID;
    }

    public void setItemSystemID(String itemSystemID) {
        this.itemSystemID = itemSystemID;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public HelperDTO getItemIrtQualifierName() {
        return itemIrtQualifierName;
    }

    public void setItemIrtQualifierName(HelperDTO itemIrtQualifierName) {
        this.itemIrtQualifierName = itemIrtQualifierName;
    }

    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public Boolean getRecordLockStatus() {
        return recordLockStatus;
    }

    public void setRecordLockStatus(Boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getUpps() {
        return upps;
    }

    public void setUpps(String upps) {
        this.upps = upps;
    }

    public HelperDTO getNdc8() {
        return ndc8;
    }

    public void setNdc8(HelperDTO ndc8) {
        this.ndc8 = ndc8;
    }

    public HelperDTO getNdc9() {
        return ndc9;
    }

    public void setNdc9(HelperDTO ndc9) {
        this.ndc9 = ndc9;
    }

    public HelperDTO getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(HelperDTO customerGroup) {
        this.customerGroup = customerGroup;
    }    

    public String getFinancialSystem() {
        return financialSystem;
    }

    public void setFinancialSystem(String financialSystem) {
        this.financialSystem = financialSystem;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getParentCustomerNo() {
        return parentCustomerNo;
    }

    public void setParentCustomerNo(String parentCustomerNo) {
        this.parentCustomerNo = parentCustomerNo;
    }

    public String getPriorParentCustomerNo() {
        return priorParentCustomerNo;
    }

    public void setPriorParentCustomerNo(String priorParentCustomerNo) {
        this.priorParentCustomerNo = priorParentCustomerNo;
    }
    
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
   
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getLives() {
        return lives;
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getPackageSizeCode() {
        return packageSizeCode;
    }

    public void setPackageSizeCode(String packageSizeCode) {
        this.packageSizeCode = packageSizeCode;
    }

    public Date getPackageSizeIntroDate() {
        return packageSizeIntroDate;
    }

    public void setPackageSizeIntroDate(Date packageSizeIntroDate) {
        this.packageSizeIntroDate = packageSizeIntroDate;
    }
    
    public String getuP() {
        return psUP;
    }

    public void setuP(String uP) {
        this.psUP = uP;
    }

    public String getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(String manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public String getManufacturerNO() {
        return manufacturerNO;
    }

    public void setManufacturerNO(String manufacturerNO) {
        this.manufacturerNO = manufacturerNO;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getLabelerCode() {
        return labelerCode;
    }

    public void setLabelerCode(String labelerCode) {
        this.labelerCode = labelerCode;
    }

    public String getProductOrganizationKey() {
        return productOrganizationKey;
    }

    public void setProductOrganizationKey(String productOrganizationKey) {
        this.productOrganizationKey = productOrganizationKey;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }
    public String getAuthorizedGeneric() {
        return authorizedGeneric;
    }

    public void setAuthorizedGeneric(String authorizedGeneric) {
        this.authorizedGeneric = authorizedGeneric;
    }

    public Date getAuthorizedGenericStartDate() {
        return authorizedGenericStartDate;
    }

    public void setAuthorizedGenericStartDate(Date authorizedGenericStartDate) {
        this.authorizedGenericStartDate = authorizedGenericStartDate;
    }

    public Date getAuthorizedGenericEndDate() {
        return authorizedGenericEndDate;
    }

    public void setAuthorizedGenericEndDate(Date authorizedGenericEndDate) {
        this.authorizedGenericEndDate = authorizedGenericEndDate;
    }

    public Date getFirstSaleDate() {
        return firstSaleDate;
    }

    public void setFirstSaleDate(Date firstSaleDate) {
        this.firstSaleDate = firstSaleDate;
    }
    
    public String getItemTypeIndicator() {
        return itemTypeIndicator;
    }

    public void setItemTypeIndicator(String itemTypeIndicator) {
        this.itemTypeIndicator = itemTypeIndicator;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Date getMarketTerminationDate() {
        return marketTerminationDate;
    }

    public void setMarketTerminationDate(Date marketTerminationDate) {
        this.marketTerminationDate = marketTerminationDate;
    }

    public Date getNewFormulationStartDate() {
        return newFormulationStartDate;
    }

    public void setNewFormulationStartDate(Date newFormulationStartDate) {
        this.newFormulationStartDate = newFormulationStartDate;
    }

    public Date getNewFormulationEndDate() {
        return newFormulationEndDate;
    }

    public void setNewFormulationEndDate(Date newFormulationEndDate) {
        this.newFormulationEndDate = newFormulationEndDate;
    }

    public Date getPediatricExclusiveStartDate() {
        return pediatricExclusiveStartDate;
    }

    public void setPediatricExclusiveStartDate(Date pediatricExclusiveStartDate) {
        this.pediatricExclusiveStartDate = pediatricExclusiveStartDate;
    }

    public Date getPediatricExclusiveEndDate() {
        return pediatricExclusiveEndDate;
    }

    public void setPediatricExclusiveEndDate(Date pediatricExclusiveEndDate) {
        this.pediatricExclusiveEndDate = pediatricExclusiveEndDate;
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

    public String getPediatricExclusiveIndicator() {
        return pediatricExclusiveIndicator;
    }

    public void setPediatricExclusiveIndicator(String pediatricExclusiveIndicator) {
        this.pediatricExclusiveIndicator = pediatricExclusiveIndicator;
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

    public String getPrimaryUOM() {
        return primaryUOM;
    }

    public void setPrimaryUOM(String primaryUOM) {
        this.primaryUOM = primaryUOM;
    }

    public String getSecondaryUOM() {
        return secondaryUOM;
    }

    public void setSecondaryUOM(String secondaryUOM) {
        this.secondaryUOM = secondaryUOM;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getShelfLifeType() {
        return shelfLifeType;
    }

    public void setShelfLifeType(String shelfLifeType) {
        this.shelfLifeType = shelfLifeType;
    }

    public String getDualPricingIndicator() {
        return dualPricingIndicator;
    }

    public void setDualPricingIndicator(String dualPricingIndicator) {
        this.dualPricingIndicator = dualPricingIndicator;
    }

    public String getItemFamilyID() {
        return itemFamilyID;
    }

    public void setItemFamilyID(String itemFamilyID) {
        this.itemFamilyID = itemFamilyID;
    }

    public String getPsUDC1() {
        return psUDC1;
    }

    public void setPsUDC1(String psUDC1) {
        this.psUDC1 = psUDC1;
    }

    public String getPsUDC2() {
        return psUDC2;
    }

    public void setPsUDC2(String psUDC2) {
        this.psUDC2 = psUDC2;
    }

    public String getPsUDC3() {
        return psUDC3;
    }

    public void setPsUDC3(String psUDC3) {
        this.psUDC3 = psUDC3;
    }

    public String getPsUDC4() {
        return psUDC4;
    }

    public void setPsUDC4(String psUDC4) {
        this.psUDC4 = psUDC4;
    }

    public String getPsUDC5() {
        return psUDC5;
    }

    public void setPsUDC5(String psUDC5) {
        this.psUDC5 = psUDC5;
    }

    public String getPsUDC6() {
        return psUDC6;
    }

    public void setPsUDC6(String psUDC6) {
        this.psUDC6 = psUDC6;
    }

    public String getAcquiredAMP() {
        return acquiredAMP;
    }

    public void setAcquiredAMP(String acquiredAMP) {
        this.acquiredAMP = acquiredAMP;
    }

    public String getAcquiredBAMP() {
        return acquiredBAMP;
    }

    public void setAcquiredBAMP(String acquiredBAMP) {
        this.acquiredBAMP = acquiredBAMP;
    }

    public String getPsOBRABAMP() {
        return psOBRABAMP;
    }

    public void setPsOBRABAMP(String psOBRABAMP) {
        this.psOBRABAMP = psOBRABAMP;
    }
   
    public String getDRA() {
        return psDRA;
    }

    public void setDRA(String DRA) {
        this.psDRA = DRA;
    }

    public String getDosesperUnit() {
        return dosesperUnit;
    }

    public void setDosesperUnit(String dosesperUnit) {
        this.dosesperUnit = dosesperUnit;
    }

    public Date getDiscontinuationDate() {
        return discontinuationDate;
    }

    public void setDiscontinuationDate(Date discontinuationDate) {
        this.discontinuationDate = discontinuationDate;
    }

    public Date getLastLotExpirationDate() {
        return lastLotExpirationDate;
    }

    public void setLastLotExpirationDate(Date lastLotExpirationDate) {
        this.lastLotExpirationDate = lastLotExpirationDate;
    }

    public String getPsNDC9() {
        return psNDC9;
    }

    public void setPsNDC9(String psNDC9) {
        this.psNDC9 = psNDC9;
    }

    public String getPsNDC8() {
        return psNDC8;
    }

    public void setPsNDC8(String psNDC8) {
        this.psNDC8 = psNDC8;
    }

    public String getDisplayBrand() {
        return displayBrand;
    }

    public void setDisplayBrand(String displayBrand) {
        this.displayBrand = displayBrand;
    }

    public String getInnovatorCode() {
        return innovatorCode;
    }

    public void setInnovatorCode(String innovatorCode) {
        this.innovatorCode = innovatorCode;
    }

    public String getBaselineAMP() {
        return baselineAMP;
    }

    public void setBaselineAMP(String baselineAMP) {
        this.baselineAMP = baselineAMP;
    }

    public Date getBaseYearCPI() {
        return baseYearCPI;
    }

    public void setBaseYearCPI(Date baseYearCPI) {
        this.baseYearCPI = baseYearCPI;
    }

    public String getPsUP() {
        return psUP;
    }

    public void setPsUP(String psUP) {
        this.psUP = psUP;
    }

    public String getPsDRA() {
        return psDRA;
    }

    public void setPsDRA(String psDRA) {
        this.psDRA = psDRA;
    }

    public HelperDTO getOrganisationKey() {
        return organizationKey;
    }

    public void setOrganisationKey(HelperDTO organisationKey) {
        this.organizationKey = organisationKey;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public HelperDTO getCustomerType() {
        return customerType;
    }

    public void setCustomerType(HelperDTO customerType) {
        this.customerType = customerType;
    }

    public HelperDTO getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(HelperDTO customerStatus) {
        this.customerStatus = customerStatus;
    }

    public HelperDTO getUdc1() {
        return udc1;
    }

    public void setUdc1(HelperDTO udc1) {
        this.udc1 = udc1;
    }

    public HelperDTO getUdc2() {
        return udc2;
    }

    public void setUdc2(HelperDTO udc2) {
        this.udc2 = udc2;
    }

    public HelperDTO getUdc3() {
        return udc3;
    }

    public void setUdc3(HelperDTO udc3) {
        this.udc3 = udc3;
    }

    public HelperDTO getUdc4() {
        return udc4;
    }

    public void setUdc4(HelperDTO udc4) {
        this.udc4 = udc4;
    }

    public HelperDTO getUdc5() {
        return udc5;
    }

    public void setUdc5(HelperDTO udc5) {
        this.udc5 = udc5;
    }

    public HelperDTO getUdc6() {
        return udc6;
    }

    public void setUdc6(HelperDTO udc6) {
        this.udc6 = udc6;
    }        

    public String getTradeClassSearch() {
        return tradeClassSearch;
    }

    public void setTradeClassSearch(String tradeClassSearch) {
        this.tradeClassSearch = tradeClassSearch;
    }    

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getParentCompanyMasterSid() {
        return parentCompanyMasterSid;
    }

    public void setParentCompanyMasterSid(int parentCompanyMasterSid) {
        this.parentCompanyMasterSid = parentCompanyMasterSid;
    }

    public HelperDTO getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(HelperDTO tradeClass) {
        this.tradeClass = tradeClass;
    }

    public HelperDTO getOrganizationKey() {
        return organizationKey;
    }

    public void setOrganizationKey(HelperDTO organizationKey) {
        this.organizationKey = organizationKey;
    }

    public Date getTradeClassStartDate() {
        return tradeClassStartDate;
    }

    public void setTradeClassStartDate(Date tradeClassStartDate) {
        this.tradeClassStartDate = tradeClassStartDate;
    }

    public Date getTradeClassEndDate() {
        return tradeClassEndDate;
    }

    public void setTradeClassEndDate(Date tradeClassEndDate) {
        this.tradeClassEndDate = tradeClassEndDate;
    }

    public Date getCustomerStartDate() {
        return customerStartDate;
    }

    public void setCustomerStartDate(Date customerStartDate) {
        this.customerStartDate = customerStartDate;
    }

    public Date getCustomerEndDate() {
        return customerEndDate;
    }

    public void setCustomerEndDate(Date customerEndDate) {
        this.customerEndDate = customerEndDate;
    }

    public Date getParentStartDate() {
        return parentStartDate;
    }

    public void setParentStartDate(Date parentStartDate) {
        this.parentStartDate = parentStartDate;
    }

    public Date getParentEndDate() {
        return parentEndDate;
    }

    public void setParentEndDate(Date parentEndDate) {
        this.parentEndDate = parentEndDate;
    }

    public Date getPriorParentStartDate() {
        return priorParentStartDate;
    }

    public void setPriorParentStartDate(Date priorParentStartDate) {
        this.priorParentStartDate = priorParentStartDate;
    }
    
}
