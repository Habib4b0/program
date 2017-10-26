/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.stpl.ifs.util.HelperDTO;

/**
 * The Class ItemDetailsDTO.
 *
 * @author vishalakshi
 */
public class ItemDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1847920181457757191L;

    private String itemId = StringUtils.EMPTY;

    private String itemNo = StringUtils.EMPTY;

    private String itemCode = StringUtils.EMPTY;

    private String itemName = StringUtils.EMPTY;

    private String itemDesc = StringUtils.EMPTY;

    private Date itemStartDate;

    private Date itemEndDate;

    private String itemStatus = StringUtils.EMPTY;

    private HelperDTO therapeuticClassDdlb;

    private String therapeuticClass = StringUtils.EMPTY;

    private String brand = StringUtils.EMPTY;

    private String form = StringUtils.EMPTY;

    private HelperDTO brandDdlb;

    private HelperDTO formDdlb;

    private String strength = StringUtils.EMPTY;

    private String packageSizeCode = StringUtils.EMPTY;

    private Date packageSizeIntroDate;

    private Double upps = 0.0;

    private String manufacturerId = StringUtils.EMPTY;

    private String manufacturerNo = StringUtils.EMPTY;

    private String manufacturerName = StringUtils.EMPTY;

    private String labelerCode = StringUtils.EMPTY;

    private String organizationKey = StringUtils.EMPTY;

    private Date acquisitionDate;

    private String authorizedGeneric = StringUtils.EMPTY;

    private Date authorizedGenericStartDate;

    private Date authorizedGenericEndDate;

    private Date firstSaleDate;

    private String itemTypeIndicator = StringUtils.EMPTY;

    private String itemClass = StringUtils.EMPTY;

    private HelperDTO itemTypeDdlb;

    private String itemType = StringUtils.EMPTY;

    private Date marketTerminationDate;

    private String newFormulationIndicator = StringUtils.EMPTY;

    private String newFormulation = StringUtils.EMPTY;

    private Date newFormulationStartDate;

    private Date newFormulationEndDate;

    private String pediatricExclusiveIndicator = StringUtils.EMPTY;

    private Date pediatricExclusiveStartDate;

    private Date pediatricExclusiveEndDate;

    private String clottingFactorIndicator = StringUtils.EMPTY;

    private Date clottingFactorStartDate;

    private Date clottingFactorEndDate;

    private String primaryUom = StringUtils.EMPTY;

    private String secondaryUom = StringUtils.EMPTY;

    private String shelfLife = StringUtils.EMPTY;

    private String shelfLifeType = StringUtils.EMPTY;

    private String dualPricingIndicator = StringUtils.EMPTY;

    private String itemFamilyId = StringUtils.EMPTY;

    private String udc1 = StringUtils.EMPTY;

    private String udc2 = StringUtils.EMPTY;

    private String udc3 = StringUtils.EMPTY;

    private String udc4 = StringUtils.EMPTY;

    private String udc5 = StringUtils.EMPTY;

    private String udc6 = StringUtils.EMPTY;

    private Double acquiredAmp;

    private Double acquiredBamp;

    private Double obraBamp;

    private String dra;

    private String dosesPerUnit = StringUtils.EMPTY;

    private String discontinuationDate;

    private String lastLotExpirationDate;

    private String ndc9 = StringUtils.EMPTY;

    private String ndc8 = StringUtils.EMPTY;

    private String displayBrand = StringUtils.EMPTY;

    private String innovatorCode = StringUtils.EMPTY;

    private Double baselineAmp;

    private Double baseYearCpi;

    private int itemSystemId;

    private Date createdDate;

    private Date modifiedDate;

    private int createdBy;

    private int modifiedBy;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(final String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Date getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(final Date itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public Date getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(final Date itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(final String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public HelperDTO getTherapeuticClassDdlb() {
        return therapeuticClassDdlb;
    }

    public void setTherapeuticClassDdlb(final HelperDTO therapeuticClassDdlb) {
        this.therapeuticClassDdlb = therapeuticClassDdlb;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public HelperDTO getFormDdlb() {
        return formDdlb;
    }

    public void setFormDdlb(final HelperDTO formDdlb) {
        this.formDdlb = formDdlb;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(final String strength) {
        this.strength = strength;
    }

    public String getPackageSizeCode() {
        return packageSizeCode;
    }

    public void setPackageSizeCode(final String packageSizeCode) {
        this.packageSizeCode = packageSizeCode;
    }

    public Date getPackageSizeIntroDate() {
        return packageSizeIntroDate;
    }

    public void setPackageSizeIntroDate(final Date packageSizeIntroDate) {
        this.packageSizeIntroDate = packageSizeIntroDate;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(final String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerNo() {
        return manufacturerNo;
    }

    public void setManufacturerNo(final String manufacturerNo) {
        this.manufacturerNo = manufacturerNo;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(final String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getLabelerCode() {
        return labelerCode;
    }

    public void setLabelerCode(final String labelerCode) {
        this.labelerCode = labelerCode;
    }

    public String getOrganizationKey() {
        return organizationKey;
    }

    public void setOrganizationKey(final String organizationKey) {
        this.organizationKey = organizationKey;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(final Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getAuthorizedGeneric() {
        return authorizedGeneric;
    }

    public void setAuthorizedGeneric(final String authorizedGeneric) {
        this.authorizedGeneric = authorizedGeneric;
    }

    public Date getAuthorizedGenericStartDate() {
        return authorizedGenericStartDate;
    }

    public void setAuthorizedGenericStartDate(final Date authorizedGenericStartDate) {
        this.authorizedGenericStartDate = authorizedGenericStartDate;
    }

    public Date getAuthorizedGenericEndDate() {
        return authorizedGenericEndDate;
    }

    public void setAuthorizedGenericEndDate(final Date authorizedGenericEndDate) {
        this.authorizedGenericEndDate = authorizedGenericEndDate;
    }

    public Date getFirstSaleDate() {
        return firstSaleDate;
    }

    public void setFirstSaleDate(final Date firstSaleDate) {
        this.firstSaleDate = firstSaleDate;
    }

    public String getItemTypeIndicator() {
        return itemTypeIndicator;
    }

    public void setItemTypeIndicator(final String itemTypeIndicator) {
        this.itemTypeIndicator = itemTypeIndicator;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(final String itemClass) {
        this.itemClass = itemClass;
    }

    public HelperDTO getItemTypeDdlb() {
        return itemTypeDdlb;
    }

    public void setItemTypeDdlb(final HelperDTO itemTypeDdlb) {
        this.itemTypeDdlb = itemTypeDdlb;
    }

    public Date getMarketTerminationDate() {
        return marketTerminationDate;
    }

    public void setMarketTerminationDate(final Date marketTerminationDate) {
        this.marketTerminationDate = marketTerminationDate;
    }

    public String getNewFormulationIndicator() {
        return newFormulationIndicator;
    }

    public void setNewFormulationIndicator(final String newFormulationIndicator) {
        this.newFormulationIndicator = newFormulationIndicator;
    }

    public String getNewFormulation() {
        return newFormulation;
    }

    public void setNewFormulation(final String newFormulation) {
        this.newFormulation = newFormulation;
    }

    public Date getNewFormulationStartDate() {
        return newFormulationStartDate;
    }

    public void setNewFormulationStartDate(final Date newFormulationStartDate) {
        this.newFormulationStartDate = newFormulationStartDate;
    }

    public Date getNewFormulationEndDate() {
        return newFormulationEndDate;
    }

    public void setNewFormulationEndDate(final Date newFormulationEndDate) {
        this.newFormulationEndDate = newFormulationEndDate;
    }

    public String getPediatricExclusiveIndicator() {
        return pediatricExclusiveIndicator;
    }

    public void setPediatricExclusiveIndicator(final String pediatricExclusiveIndicator) {
        this.pediatricExclusiveIndicator = pediatricExclusiveIndicator;
    }

    public Date getPediatricExclusiveStartDate() {
        return pediatricExclusiveStartDate;
    }

    public void setPediatricExclusiveStartDate(final Date pediatricExclusiveStartDate) {
        this.pediatricExclusiveStartDate = pediatricExclusiveStartDate;
    }

    public Date getPediatricExclusiveEndDate() {
        return pediatricExclusiveEndDate;
    }

    public void setPediatricExclusiveEndDate(final Date pediatricExclusiveEndDate) {
        this.pediatricExclusiveEndDate = pediatricExclusiveEndDate;
    }

    public String getClottingFactorIndicator() {
        return clottingFactorIndicator;
    }

    public void setClottingFactorIndicator(final String clottingFactorIndicator) {
        this.clottingFactorIndicator = clottingFactorIndicator;
    }

    public Date getClottingFactorStartDate() {
        return clottingFactorStartDate;
    }

    public void setClottingFactorStartDate(final Date clottingFactorStartDate) {
        this.clottingFactorStartDate = clottingFactorStartDate;
    }

    public Date getClottingFactorEndDate() {
        return clottingFactorEndDate;
    }

    public void setClottingFactorEndDate(final Date clottingFactorEndDate) {
        this.clottingFactorEndDate = clottingFactorEndDate;
    }

    public String getPrimaryUom() {
        return primaryUom;
    }

    public void setPrimaryUom(final String primaryUom) {
        this.primaryUom = primaryUom;
    }

    public String getSecondaryUom() {
        return secondaryUom;
    }

    public void setSecondaryUom(final String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(final String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getShelfLifeType() {
        return shelfLifeType;
    }

    public void setShelfLifeType(final String shelfLifeType) {
        this.shelfLifeType = shelfLifeType;
    }

    public String getDualPricingIndicator() {
        return dualPricingIndicator;
    }

    public void setDualPricingIndicator(final String dualPricingIndicator) {
        this.dualPricingIndicator = dualPricingIndicator;
    }

    public String getItemFamilyId() {
        return itemFamilyId;
    }

    public void setItemFamilyId(final String itemFamilyId) {
        this.itemFamilyId = itemFamilyId;
    }

    public String getUdc1() {
        return udc1;
    }

    public void setUdc1(final String udc1) {
        this.udc1 = udc1;
    }

    public String getUdc2() {
        return udc2;
    }

    public void setUdc2(final String udc2) {
        this.udc2 = udc2;
    }

    public String getUdc3() {
        return udc3;
    }

    public void setUdc3(final String udc3) {
        this.udc3 = udc3;
    }

    public String getUdc4() {
        return udc4;
    }

    public void setUdc4(final String udc4) {
        this.udc4 = udc4;
    }

    public String getUdc5() {
        return udc5;
    }

    public void setUdc5(final String udc5) {
        this.udc5 = udc5;
    }

    public String getUdc6() {
        return udc6;
    }

    public void setUdc6(final String udc6) {
        this.udc6 = udc6;
    }

    public Double getAcquiredAmp() {
        return acquiredAmp;
    }

    public void setAcquiredAmp(final Double acquiredAmp) {
        this.acquiredAmp = acquiredAmp;
    }

    public Double getAcquiredBamp() {
        return acquiredBamp;
    }

    public void setAcquiredBamp(final Double acquiredBamp) {
        this.acquiredBamp = acquiredBamp;
    }

    public Double getObraBamp() {
        return obraBamp;
    }

    public void setObraBamp(final Double obraBamp) {
        this.obraBamp = obraBamp;
    }

    public String getDra() {
        return dra;
    }

    public void setDra(final String dra) {
        this.dra = dra;
    }

    public String getDosesPerUnit() {
        return dosesPerUnit;
    }

    public void setDosesPerUnit(final String dosesPerUnit) {
        this.dosesPerUnit = dosesPerUnit;
    }

    public String getDiscontinuationDate() {
        return discontinuationDate;
    }

    public void setDiscontinuationDate(final String discontinuationDate) {
        this.discontinuationDate = discontinuationDate;
    }

    public String getLastLotExpirationDate() {
        return lastLotExpirationDate;
    }

    public void setLastLotExpirationDate(final String lastLotExpirationDate) {
        this.lastLotExpirationDate = lastLotExpirationDate;
    }

    public String getNdc9() {
        return ndc9;
    }

    public void setNdc9(final String ndc9) {
        this.ndc9 = ndc9;
    }

    public String getNdc8() {
        return ndc8;
    }

    public void setNdc8(final String ndc8) {
        this.ndc8 = ndc8;
    }

    public String getDisplayBrand() {
        return displayBrand;
    }

    public void setDisplayBrand(final String displayBrand) {
        this.displayBrand = displayBrand;
    }

    public String getInnovatorCode() {
        return innovatorCode;
    }

    public void setInnovatorCode(final String innovatorCode) {
        this.innovatorCode = innovatorCode;
    }

    public Double getBaselineAmp() {
        return baselineAmp;
    }

    public void setBaselineAmp(final Double baselineAmp) {
        this.baselineAmp = baselineAmp;
    }

    public Double getBaseYearCpi() {
        return baseYearCpi;
    }

    public void setBaseYearCpi(final Double baseYearCpi) {
        this.baseYearCpi = baseYearCpi;
    }

    public int getItemSystemId() {
        return itemSystemId;
    }

    public void setItemSystemId(final int itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final int createdBy) {
        this.createdBy = createdBy;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(final int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Double getUpps() {
        return upps;
    }

    public void setUpps(final Double upps) {
        this.upps = upps;
    }

    public HelperDTO getBrandDdlb() {
        return brandDdlb;
    }

    public void setBrandDdlb(final HelperDTO brandDdlb) {
        this.brandDdlb = brandDdlb;
    }

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

}
