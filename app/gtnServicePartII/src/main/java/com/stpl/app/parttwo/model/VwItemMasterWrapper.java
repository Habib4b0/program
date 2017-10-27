package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwItemMaster}.
 * </p>
 *
 * @author
 * @see VwItemMaster
 * @generated
 */
public class VwItemMasterWrapper implements VwItemMaster,
    ModelWrapper<VwItemMaster> {
    private VwItemMaster _vwItemMaster;

    public VwItemMasterWrapper(VwItemMaster vwItemMaster) {
        _vwItemMaster = vwItemMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return VwItemMaster.class;
    }

    @Override
    public String getModelClassName() {
        return VwItemMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("itemDesc", getItemDesc());
        attributes.put("acquiredAmp", getAcquiredAmp());
        attributes.put("authorizedGenericStartDate",
            getAuthorizedGenericStartDate());
        attributes.put("newFormulationStartDate", getNewFormulationStartDate());
        attributes.put("marketTerminationDate", getMarketTerminationDate());
        attributes.put("obraBamp", getObraBamp());
        attributes.put("brand", getBrand());
        attributes.put("manufacturerNo", getManufacturerNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("therapeuticClass", getTherapeuticClass());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("acquiredBamp", getAcquiredBamp());
        attributes.put("pediatricExclusiveEndDate",
            getPediatricExclusiveEndDate());
        attributes.put("source", getSource());
        attributes.put("newFormulation", getNewFormulation());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("divestitureDate", getDivestitureDate());
        attributes.put("shelfLife", getShelfLife());
        attributes.put("primaryUom", getPrimaryUom());
        attributes.put("newFormulationEndDate", getNewFormulationEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("packageSizeCode", getPackageSizeCode());
        attributes.put("secondaryUom", getSecondaryUom());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("discontinuationDate", getDiscontinuationDate());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("udc2", getUdc2());
        attributes.put("packageSizeIntroDate", getPackageSizeIntroDate());
        attributes.put("udc3", getUdc3());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("manufacturerId", getManufacturerId());
        attributes.put("itemFamilyId", getItemFamilyId());
        attributes.put("strength", getStrength());
        attributes.put("itemCategory", getItemCategory());
        attributes.put("upps", getUpps());
        attributes.put("shelfLifeType", getShelfLifeType());
        attributes.put("pediatricExclusiveIndicator",
            getPediatricExclusiveIndicator());
        attributes.put("itemTypeIndication", getItemTypeIndication());
        attributes.put("acquisitionDate", getAcquisitionDate());
        attributes.put("clottingFactorIndicator", getClottingFactorIndicator());
        attributes.put("form", getForm());
        attributes.put("itemName", getItemName());
        attributes.put("manufacturerName", getManufacturerName());
        attributes.put("pediatricExclusiveStartDate",
            getPediatricExclusiveStartDate());
        attributes.put("firstSaleDate", getFirstSaleDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemType", getItemType());
        attributes.put("itemId", getItemId());
        attributes.put("baselineAmp", getBaselineAmp());
        attributes.put("dosesPerUnit", getDosesPerUnit());
        attributes.put("dualPricingIndicator", getDualPricingIndicator());
        attributes.put("baseCpi", getBaseCpi());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("authorizedGeneric", getAuthorizedGeneric());
        attributes.put("ndc9", getNdc9());
        attributes.put("authorizedGenericEndDate", getAuthorizedGenericEndDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("packageSize", getPackageSize());
        attributes.put("ndc8", getNdc8());
        attributes.put("itemClass", getItemClass());
        attributes.put("labelerCode", getLabelerCode());
        attributes.put("displayBrand", getDisplayBrand());
        attributes.put("clottingFactorEndDate", getClottingFactorEndDate());
        attributes.put("dra", getDra());
        attributes.put("brandId", getBrandId());
        attributes.put("baseCpiPeriod", getBaseCpiPeriod());
        attributes.put("newFormulationIndicator", getNewFormulationIndicator());
        attributes.put("lastLotExpirationDate", getLastLotExpirationDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemCode", getItemCode());
        attributes.put("clottingFactorStartDate", getClottingFactorStartDate());
        attributes.put("nonFederalExpirationDate", getNonFederalExpirationDate());
        attributes.put("baseCpiPrecision", getBaseCpiPrecision());
        attributes.put("baselineAmpPrecision", getBaselineAmpPrecision());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        String acquiredAmp = (String) attributes.get("acquiredAmp");

        if (acquiredAmp != null) {
            setAcquiredAmp(acquiredAmp);
        }

        Date authorizedGenericStartDate = (Date) attributes.get(
                "authorizedGenericStartDate");

        if (authorizedGenericStartDate != null) {
            setAuthorizedGenericStartDate(authorizedGenericStartDate);
        }

        Date newFormulationStartDate = (Date) attributes.get(
                "newFormulationStartDate");

        if (newFormulationStartDate != null) {
            setNewFormulationStartDate(newFormulationStartDate);
        }

        Date marketTerminationDate = (Date) attributes.get(
                "marketTerminationDate");

        if (marketTerminationDate != null) {
            setMarketTerminationDate(marketTerminationDate);
        }

        String obraBamp = (String) attributes.get("obraBamp");

        if (obraBamp != null) {
            setObraBamp(obraBamp);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String manufacturerNo = (String) attributes.get("manufacturerNo");

        if (manufacturerNo != null) {
            setManufacturerNo(manufacturerNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String therapeuticClass = (String) attributes.get("therapeuticClass");

        if (therapeuticClass != null) {
            setTherapeuticClass(therapeuticClass);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String acquiredBamp = (String) attributes.get("acquiredBamp");

        if (acquiredBamp != null) {
            setAcquiredBamp(acquiredBamp);
        }

        Date pediatricExclusiveEndDate = (Date) attributes.get(
                "pediatricExclusiveEndDate");

        if (pediatricExclusiveEndDate != null) {
            setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String newFormulation = (String) attributes.get("newFormulation");

        if (newFormulation != null) {
            setNewFormulation(newFormulation);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Date divestitureDate = (Date) attributes.get("divestitureDate");

        if (divestitureDate != null) {
            setDivestitureDate(divestitureDate);
        }

        String shelfLife = (String) attributes.get("shelfLife");

        if (shelfLife != null) {
            setShelfLife(shelfLife);
        }

        String primaryUom = (String) attributes.get("primaryUom");

        if (primaryUom != null) {
            setPrimaryUom(primaryUom);
        }

        Date newFormulationEndDate = (Date) attributes.get(
                "newFormulationEndDate");

        if (newFormulationEndDate != null) {
            setNewFormulationEndDate(newFormulationEndDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String packageSizeCode = (String) attributes.get("packageSizeCode");

        if (packageSizeCode != null) {
            setPackageSizeCode(packageSizeCode);
        }

        String secondaryUom = (String) attributes.get("secondaryUom");

        if (secondaryUom != null) {
            setSecondaryUom(secondaryUom);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Date discontinuationDate = (Date) attributes.get("discontinuationDate");

        if (discontinuationDate != null) {
            setDiscontinuationDate(discontinuationDate);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        Date packageSizeIntroDate = (Date) attributes.get(
                "packageSizeIntroDate");

        if (packageSizeIntroDate != null) {
            setPackageSizeIntroDate(packageSizeIntroDate);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        String manufacturerId = (String) attributes.get("manufacturerId");

        if (manufacturerId != null) {
            setManufacturerId(manufacturerId);
        }

        String itemFamilyId = (String) attributes.get("itemFamilyId");

        if (itemFamilyId != null) {
            setItemFamilyId(itemFamilyId);
        }

        String strength = (String) attributes.get("strength");

        if (strength != null) {
            setStrength(strength);
        }

        String itemCategory = (String) attributes.get("itemCategory");

        if (itemCategory != null) {
            setItemCategory(itemCategory);
        }

        Double upps = (Double) attributes.get("upps");

        if (upps != null) {
            setUpps(upps);
        }

        String shelfLifeType = (String) attributes.get("shelfLifeType");

        if (shelfLifeType != null) {
            setShelfLifeType(shelfLifeType);
        }

        String pediatricExclusiveIndicator = (String) attributes.get(
                "pediatricExclusiveIndicator");

        if (pediatricExclusiveIndicator != null) {
            setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
        }

        String itemTypeIndication = (String) attributes.get(
                "itemTypeIndication");

        if (itemTypeIndication != null) {
            setItemTypeIndication(itemTypeIndication);
        }

        Date acquisitionDate = (Date) attributes.get("acquisitionDate");

        if (acquisitionDate != null) {
            setAcquisitionDate(acquisitionDate);
        }

        String clottingFactorIndicator = (String) attributes.get(
                "clottingFactorIndicator");

        if (clottingFactorIndicator != null) {
            setClottingFactorIndicator(clottingFactorIndicator);
        }

        String form = (String) attributes.get("form");

        if (form != null) {
            setForm(form);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String manufacturerName = (String) attributes.get("manufacturerName");

        if (manufacturerName != null) {
            setManufacturerName(manufacturerName);
        }

        Date pediatricExclusiveStartDate = (Date) attributes.get(
                "pediatricExclusiveStartDate");

        if (pediatricExclusiveStartDate != null) {
            setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
        }

        Date firstSaleDate = (Date) attributes.get("firstSaleDate");

        if (firstSaleDate != null) {
            setFirstSaleDate(firstSaleDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String itemType = (String) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String baselineAmp = (String) attributes.get("baselineAmp");

        if (baselineAmp != null) {
            setBaselineAmp(baselineAmp);
        }

        String dosesPerUnit = (String) attributes.get("dosesPerUnit");

        if (dosesPerUnit != null) {
            setDosesPerUnit(dosesPerUnit);
        }

        String dualPricingIndicator = (String) attributes.get(
                "dualPricingIndicator");

        if (dualPricingIndicator != null) {
            setDualPricingIndicator(dualPricingIndicator);
        }

        String baseCpi = (String) attributes.get("baseCpi");

        if (baseCpi != null) {
            setBaseCpi(baseCpi);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String authorizedGeneric = (String) attributes.get("authorizedGeneric");

        if (authorizedGeneric != null) {
            setAuthorizedGeneric(authorizedGeneric);
        }

        String ndc9 = (String) attributes.get("ndc9");

        if (ndc9 != null) {
            setNdc9(ndc9);
        }

        Date authorizedGenericEndDate = (Date) attributes.get(
                "authorizedGenericEndDate");

        if (authorizedGenericEndDate != null) {
            setAuthorizedGenericEndDate(authorizedGenericEndDate);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String packageSize = (String) attributes.get("packageSize");

        if (packageSize != null) {
            setPackageSize(packageSize);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }

        String itemClass = (String) attributes.get("itemClass");

        if (itemClass != null) {
            setItemClass(itemClass);
        }

        String labelerCode = (String) attributes.get("labelerCode");

        if (labelerCode != null) {
            setLabelerCode(labelerCode);
        }

        String displayBrand = (String) attributes.get("displayBrand");

        if (displayBrand != null) {
            setDisplayBrand(displayBrand);
        }

        Date clottingFactorEndDate = (Date) attributes.get(
                "clottingFactorEndDate");

        if (clottingFactorEndDate != null) {
            setClottingFactorEndDate(clottingFactorEndDate);
        }

        String dra = (String) attributes.get("dra");

        if (dra != null) {
            setDra(dra);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        Date baseCpiPeriod = (Date) attributes.get("baseCpiPeriod");

        if (baseCpiPeriod != null) {
            setBaseCpiPeriod(baseCpiPeriod);
        }

        String newFormulationIndicator = (String) attributes.get(
                "newFormulationIndicator");

        if (newFormulationIndicator != null) {
            setNewFormulationIndicator(newFormulationIndicator);
        }

        Date lastLotExpirationDate = (Date) attributes.get(
                "lastLotExpirationDate");

        if (lastLotExpirationDate != null) {
            setLastLotExpirationDate(lastLotExpirationDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemCode = (String) attributes.get("itemCode");

        if (itemCode != null) {
            setItemCode(itemCode);
        }

        Date clottingFactorStartDate = (Date) attributes.get(
                "clottingFactorStartDate");

        if (clottingFactorStartDate != null) {
            setClottingFactorStartDate(clottingFactorStartDate);
        }

        Date nonFederalExpirationDate = (Date) attributes.get(
                "nonFederalExpirationDate");

        if (nonFederalExpirationDate != null) {
            setNonFederalExpirationDate(nonFederalExpirationDate);
        }

        Integer baseCpiPrecision = (Integer) attributes.get("baseCpiPrecision");

        if (baseCpiPrecision != null) {
            setBaseCpiPrecision(baseCpiPrecision);
        }

        Integer baselineAmpPrecision = (Integer) attributes.get(
                "baselineAmpPrecision");

        if (baselineAmpPrecision != null) {
            setBaselineAmpPrecision(baselineAmpPrecision);
        }
    }

    /**
    * Returns the primary key of this vw item master.
    *
    * @return the primary key of this vw item master
    */
    @Override
    public int getPrimaryKey() {
        return _vwItemMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw item master.
    *
    * @param primaryKey the primary key of this vw item master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwItemMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item status of this vw item master.
    *
    * @return the item status of this vw item master
    */
    @Override
    public java.lang.String getItemStatus() {
        return _vwItemMaster.getItemStatus();
    }

    /**
    * Sets the item status of this vw item master.
    *
    * @param itemStatus the item status of this vw item master
    */
    @Override
    public void setItemStatus(java.lang.String itemStatus) {
        _vwItemMaster.setItemStatus(itemStatus);
    }

    /**
    * Returns the item desc of this vw item master.
    *
    * @return the item desc of this vw item master
    */
    @Override
    public java.lang.String getItemDesc() {
        return _vwItemMaster.getItemDesc();
    }

    /**
    * Sets the item desc of this vw item master.
    *
    * @param itemDesc the item desc of this vw item master
    */
    @Override
    public void setItemDesc(java.lang.String itemDesc) {
        _vwItemMaster.setItemDesc(itemDesc);
    }

    /**
    * Returns the acquired amp of this vw item master.
    *
    * @return the acquired amp of this vw item master
    */
    @Override
    public java.lang.String getAcquiredAmp() {
        return _vwItemMaster.getAcquiredAmp();
    }

    /**
    * Sets the acquired amp of this vw item master.
    *
    * @param acquiredAmp the acquired amp of this vw item master
    */
    @Override
    public void setAcquiredAmp(java.lang.String acquiredAmp) {
        _vwItemMaster.setAcquiredAmp(acquiredAmp);
    }

    /**
    * Returns the authorized generic start date of this vw item master.
    *
    * @return the authorized generic start date of this vw item master
    */
    @Override
    public java.util.Date getAuthorizedGenericStartDate() {
        return _vwItemMaster.getAuthorizedGenericStartDate();
    }

    /**
    * Sets the authorized generic start date of this vw item master.
    *
    * @param authorizedGenericStartDate the authorized generic start date of this vw item master
    */
    @Override
    public void setAuthorizedGenericStartDate(
        java.util.Date authorizedGenericStartDate) {
        _vwItemMaster.setAuthorizedGenericStartDate(authorizedGenericStartDate);
    }

    /**
    * Returns the new formulation start date of this vw item master.
    *
    * @return the new formulation start date of this vw item master
    */
    @Override
    public java.util.Date getNewFormulationStartDate() {
        return _vwItemMaster.getNewFormulationStartDate();
    }

    /**
    * Sets the new formulation start date of this vw item master.
    *
    * @param newFormulationStartDate the new formulation start date of this vw item master
    */
    @Override
    public void setNewFormulationStartDate(
        java.util.Date newFormulationStartDate) {
        _vwItemMaster.setNewFormulationStartDate(newFormulationStartDate);
    }

    /**
    * Returns the market termination date of this vw item master.
    *
    * @return the market termination date of this vw item master
    */
    @Override
    public java.util.Date getMarketTerminationDate() {
        return _vwItemMaster.getMarketTerminationDate();
    }

    /**
    * Sets the market termination date of this vw item master.
    *
    * @param marketTerminationDate the market termination date of this vw item master
    */
    @Override
    public void setMarketTerminationDate(java.util.Date marketTerminationDate) {
        _vwItemMaster.setMarketTerminationDate(marketTerminationDate);
    }

    /**
    * Returns the obra bamp of this vw item master.
    *
    * @return the obra bamp of this vw item master
    */
    @Override
    public java.lang.String getObraBamp() {
        return _vwItemMaster.getObraBamp();
    }

    /**
    * Sets the obra bamp of this vw item master.
    *
    * @param obraBamp the obra bamp of this vw item master
    */
    @Override
    public void setObraBamp(java.lang.String obraBamp) {
        _vwItemMaster.setObraBamp(obraBamp);
    }

    /**
    * Returns the brand of this vw item master.
    *
    * @return the brand of this vw item master
    */
    @Override
    public java.lang.String getBrand() {
        return _vwItemMaster.getBrand();
    }

    /**
    * Sets the brand of this vw item master.
    *
    * @param brand the brand of this vw item master
    */
    @Override
    public void setBrand(java.lang.String brand) {
        _vwItemMaster.setBrand(brand);
    }

    /**
    * Returns the manufacturer no of this vw item master.
    *
    * @return the manufacturer no of this vw item master
    */
    @Override
    public java.lang.String getManufacturerNo() {
        return _vwItemMaster.getManufacturerNo();
    }

    /**
    * Sets the manufacturer no of this vw item master.
    *
    * @param manufacturerNo the manufacturer no of this vw item master
    */
    @Override
    public void setManufacturerNo(java.lang.String manufacturerNo) {
        _vwItemMaster.setManufacturerNo(manufacturerNo);
    }

    /**
    * Returns the modified date of this vw item master.
    *
    * @return the modified date of this vw item master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwItemMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw item master.
    *
    * @param modifiedDate the modified date of this vw item master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwItemMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the therapeutic class of this vw item master.
    *
    * @return the therapeutic class of this vw item master
    */
    @Override
    public java.lang.String getTherapeuticClass() {
        return _vwItemMaster.getTherapeuticClass();
    }

    /**
    * Sets the therapeutic class of this vw item master.
    *
    * @param therapeuticClass the therapeutic class of this vw item master
    */
    @Override
    public void setTherapeuticClass(java.lang.String therapeuticClass) {
        _vwItemMaster.setTherapeuticClass(therapeuticClass);
    }

    /**
    * Returns the organization key of this vw item master.
    *
    * @return the organization key of this vw item master
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _vwItemMaster.getOrganizationKey();
    }

    /**
    * Sets the organization key of this vw item master.
    *
    * @param organizationKey the organization key of this vw item master
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _vwItemMaster.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the acquired bamp of this vw item master.
    *
    * @return the acquired bamp of this vw item master
    */
    @Override
    public java.lang.String getAcquiredBamp() {
        return _vwItemMaster.getAcquiredBamp();
    }

    /**
    * Sets the acquired bamp of this vw item master.
    *
    * @param acquiredBamp the acquired bamp of this vw item master
    */
    @Override
    public void setAcquiredBamp(java.lang.String acquiredBamp) {
        _vwItemMaster.setAcquiredBamp(acquiredBamp);
    }

    /**
    * Returns the pediatric exclusive end date of this vw item master.
    *
    * @return the pediatric exclusive end date of this vw item master
    */
    @Override
    public java.util.Date getPediatricExclusiveEndDate() {
        return _vwItemMaster.getPediatricExclusiveEndDate();
    }

    /**
    * Sets the pediatric exclusive end date of this vw item master.
    *
    * @param pediatricExclusiveEndDate the pediatric exclusive end date of this vw item master
    */
    @Override
    public void setPediatricExclusiveEndDate(
        java.util.Date pediatricExclusiveEndDate) {
        _vwItemMaster.setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
    }

    /**
    * Returns the source of this vw item master.
    *
    * @return the source of this vw item master
    */
    @Override
    public java.lang.String getSource() {
        return _vwItemMaster.getSource();
    }

    /**
    * Sets the source of this vw item master.
    *
    * @param source the source of this vw item master
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwItemMaster.setSource(source);
    }

    /**
    * Returns the new formulation of this vw item master.
    *
    * @return the new formulation of this vw item master
    */
    @Override
    public java.lang.String getNewFormulation() {
        return _vwItemMaster.getNewFormulation();
    }

    /**
    * Sets the new formulation of this vw item master.
    *
    * @param newFormulation the new formulation of this vw item master
    */
    @Override
    public void setNewFormulation(java.lang.String newFormulation) {
        _vwItemMaster.setNewFormulation(newFormulation);
    }

    /**
    * Returns the add chg del indicator of this vw item master.
    *
    * @return the add chg del indicator of this vw item master
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwItemMaster.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw item master.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw item master
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwItemMaster.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the divestiture date of this vw item master.
    *
    * @return the divestiture date of this vw item master
    */
    @Override
    public java.util.Date getDivestitureDate() {
        return _vwItemMaster.getDivestitureDate();
    }

    /**
    * Sets the divestiture date of this vw item master.
    *
    * @param divestitureDate the divestiture date of this vw item master
    */
    @Override
    public void setDivestitureDate(java.util.Date divestitureDate) {
        _vwItemMaster.setDivestitureDate(divestitureDate);
    }

    /**
    * Returns the shelf life of this vw item master.
    *
    * @return the shelf life of this vw item master
    */
    @Override
    public java.lang.String getShelfLife() {
        return _vwItemMaster.getShelfLife();
    }

    /**
    * Sets the shelf life of this vw item master.
    *
    * @param shelfLife the shelf life of this vw item master
    */
    @Override
    public void setShelfLife(java.lang.String shelfLife) {
        _vwItemMaster.setShelfLife(shelfLife);
    }

    /**
    * Returns the primary uom of this vw item master.
    *
    * @return the primary uom of this vw item master
    */
    @Override
    public java.lang.String getPrimaryUom() {
        return _vwItemMaster.getPrimaryUom();
    }

    /**
    * Sets the primary uom of this vw item master.
    *
    * @param primaryUom the primary uom of this vw item master
    */
    @Override
    public void setPrimaryUom(java.lang.String primaryUom) {
        _vwItemMaster.setPrimaryUom(primaryUom);
    }

    /**
    * Returns the new formulation end date of this vw item master.
    *
    * @return the new formulation end date of this vw item master
    */
    @Override
    public java.util.Date getNewFormulationEndDate() {
        return _vwItemMaster.getNewFormulationEndDate();
    }

    /**
    * Sets the new formulation end date of this vw item master.
    *
    * @param newFormulationEndDate the new formulation end date of this vw item master
    */
    @Override
    public void setNewFormulationEndDate(java.util.Date newFormulationEndDate) {
        _vwItemMaster.setNewFormulationEndDate(newFormulationEndDate);
    }

    /**
    * Returns the modified by of this vw item master.
    *
    * @return the modified by of this vw item master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwItemMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw item master.
    *
    * @param modifiedBy the modified by of this vw item master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwItemMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the package size code of this vw item master.
    *
    * @return the package size code of this vw item master
    */
    @Override
    public java.lang.String getPackageSizeCode() {
        return _vwItemMaster.getPackageSizeCode();
    }

    /**
    * Sets the package size code of this vw item master.
    *
    * @param packageSizeCode the package size code of this vw item master
    */
    @Override
    public void setPackageSizeCode(java.lang.String packageSizeCode) {
        _vwItemMaster.setPackageSizeCode(packageSizeCode);
    }

    /**
    * Returns the secondary uom of this vw item master.
    *
    * @return the secondary uom of this vw item master
    */
    @Override
    public java.lang.String getSecondaryUom() {
        return _vwItemMaster.getSecondaryUom();
    }

    /**
    * Sets the secondary uom of this vw item master.
    *
    * @param secondaryUom the secondary uom of this vw item master
    */
    @Override
    public void setSecondaryUom(java.lang.String secondaryUom) {
        _vwItemMaster.setSecondaryUom(secondaryUom);
    }

    /**
    * Returns the udc6 of this vw item master.
    *
    * @return the udc6 of this vw item master
    */
    @Override
    public java.lang.String getUdc6() {
        return _vwItemMaster.getUdc6();
    }

    /**
    * Sets the udc6 of this vw item master.
    *
    * @param udc6 the udc6 of this vw item master
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _vwItemMaster.setUdc6(udc6);
    }

    /**
    * Returns the udc5 of this vw item master.
    *
    * @return the udc5 of this vw item master
    */
    @Override
    public java.lang.String getUdc5() {
        return _vwItemMaster.getUdc5();
    }

    /**
    * Sets the udc5 of this vw item master.
    *
    * @param udc5 the udc5 of this vw item master
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _vwItemMaster.setUdc5(udc5);
    }

    /**
    * Returns the discontinuation date of this vw item master.
    *
    * @return the discontinuation date of this vw item master
    */
    @Override
    public java.util.Date getDiscontinuationDate() {
        return _vwItemMaster.getDiscontinuationDate();
    }

    /**
    * Sets the discontinuation date of this vw item master.
    *
    * @param discontinuationDate the discontinuation date of this vw item master
    */
    @Override
    public void setDiscontinuationDate(java.util.Date discontinuationDate) {
        _vwItemMaster.setDiscontinuationDate(discontinuationDate);
    }

    /**
    * Returns the udc4 of this vw item master.
    *
    * @return the udc4 of this vw item master
    */
    @Override
    public java.lang.String getUdc4() {
        return _vwItemMaster.getUdc4();
    }

    /**
    * Sets the udc4 of this vw item master.
    *
    * @param udc4 the udc4 of this vw item master
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _vwItemMaster.setUdc4(udc4);
    }

    /**
    * Returns the udc1 of this vw item master.
    *
    * @return the udc1 of this vw item master
    */
    @Override
    public java.lang.String getUdc1() {
        return _vwItemMaster.getUdc1();
    }

    /**
    * Sets the udc1 of this vw item master.
    *
    * @param udc1 the udc1 of this vw item master
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _vwItemMaster.setUdc1(udc1);
    }

    /**
    * Returns the udc2 of this vw item master.
    *
    * @return the udc2 of this vw item master
    */
    @Override
    public java.lang.String getUdc2() {
        return _vwItemMaster.getUdc2();
    }

    /**
    * Sets the udc2 of this vw item master.
    *
    * @param udc2 the udc2 of this vw item master
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _vwItemMaster.setUdc2(udc2);
    }

    /**
    * Returns the package size intro date of this vw item master.
    *
    * @return the package size intro date of this vw item master
    */
    @Override
    public java.util.Date getPackageSizeIntroDate() {
        return _vwItemMaster.getPackageSizeIntroDate();
    }

    /**
    * Sets the package size intro date of this vw item master.
    *
    * @param packageSizeIntroDate the package size intro date of this vw item master
    */
    @Override
    public void setPackageSizeIntroDate(java.util.Date packageSizeIntroDate) {
        _vwItemMaster.setPackageSizeIntroDate(packageSizeIntroDate);
    }

    /**
    * Returns the udc3 of this vw item master.
    *
    * @return the udc3 of this vw item master
    */
    @Override
    public java.lang.String getUdc3() {
        return _vwItemMaster.getUdc3();
    }

    /**
    * Sets the udc3 of this vw item master.
    *
    * @param udc3 the udc3 of this vw item master
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _vwItemMaster.setUdc3(udc3);
    }

    /**
    * Returns the item end date of this vw item master.
    *
    * @return the item end date of this vw item master
    */
    @Override
    public java.util.Date getItemEndDate() {
        return _vwItemMaster.getItemEndDate();
    }

    /**
    * Sets the item end date of this vw item master.
    *
    * @param itemEndDate the item end date of this vw item master
    */
    @Override
    public void setItemEndDate(java.util.Date itemEndDate) {
        _vwItemMaster.setItemEndDate(itemEndDate);
    }

    /**
    * Returns the manufacturer ID of this vw item master.
    *
    * @return the manufacturer ID of this vw item master
    */
    @Override
    public java.lang.String getManufacturerId() {
        return _vwItemMaster.getManufacturerId();
    }

    /**
    * Sets the manufacturer ID of this vw item master.
    *
    * @param manufacturerId the manufacturer ID of this vw item master
    */
    @Override
    public void setManufacturerId(java.lang.String manufacturerId) {
        _vwItemMaster.setManufacturerId(manufacturerId);
    }

    /**
    * Returns the item family ID of this vw item master.
    *
    * @return the item family ID of this vw item master
    */
    @Override
    public java.lang.String getItemFamilyId() {
        return _vwItemMaster.getItemFamilyId();
    }

    /**
    * Sets the item family ID of this vw item master.
    *
    * @param itemFamilyId the item family ID of this vw item master
    */
    @Override
    public void setItemFamilyId(java.lang.String itemFamilyId) {
        _vwItemMaster.setItemFamilyId(itemFamilyId);
    }

    /**
    * Returns the strength of this vw item master.
    *
    * @return the strength of this vw item master
    */
    @Override
    public java.lang.String getStrength() {
        return _vwItemMaster.getStrength();
    }

    /**
    * Sets the strength of this vw item master.
    *
    * @param strength the strength of this vw item master
    */
    @Override
    public void setStrength(java.lang.String strength) {
        _vwItemMaster.setStrength(strength);
    }

    /**
    * Returns the item category of this vw item master.
    *
    * @return the item category of this vw item master
    */
    @Override
    public java.lang.String getItemCategory() {
        return _vwItemMaster.getItemCategory();
    }

    /**
    * Sets the item category of this vw item master.
    *
    * @param itemCategory the item category of this vw item master
    */
    @Override
    public void setItemCategory(java.lang.String itemCategory) {
        _vwItemMaster.setItemCategory(itemCategory);
    }

    /**
    * Returns the upps of this vw item master.
    *
    * @return the upps of this vw item master
    */
    @Override
    public double getUpps() {
        return _vwItemMaster.getUpps();
    }

    /**
    * Sets the upps of this vw item master.
    *
    * @param upps the upps of this vw item master
    */
    @Override
    public void setUpps(double upps) {
        _vwItemMaster.setUpps(upps);
    }

    /**
    * Returns the shelf life type of this vw item master.
    *
    * @return the shelf life type of this vw item master
    */
    @Override
    public java.lang.String getShelfLifeType() {
        return _vwItemMaster.getShelfLifeType();
    }

    /**
    * Sets the shelf life type of this vw item master.
    *
    * @param shelfLifeType the shelf life type of this vw item master
    */
    @Override
    public void setShelfLifeType(java.lang.String shelfLifeType) {
        _vwItemMaster.setShelfLifeType(shelfLifeType);
    }

    /**
    * Returns the pediatric exclusive indicator of this vw item master.
    *
    * @return the pediatric exclusive indicator of this vw item master
    */
    @Override
    public java.lang.String getPediatricExclusiveIndicator() {
        return _vwItemMaster.getPediatricExclusiveIndicator();
    }

    /**
    * Sets the pediatric exclusive indicator of this vw item master.
    *
    * @param pediatricExclusiveIndicator the pediatric exclusive indicator of this vw item master
    */
    @Override
    public void setPediatricExclusiveIndicator(
        java.lang.String pediatricExclusiveIndicator) {
        _vwItemMaster.setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
    }

    /**
    * Returns the item type indication of this vw item master.
    *
    * @return the item type indication of this vw item master
    */
    @Override
    public java.lang.String getItemTypeIndication() {
        return _vwItemMaster.getItemTypeIndication();
    }

    /**
    * Sets the item type indication of this vw item master.
    *
    * @param itemTypeIndication the item type indication of this vw item master
    */
    @Override
    public void setItemTypeIndication(java.lang.String itemTypeIndication) {
        _vwItemMaster.setItemTypeIndication(itemTypeIndication);
    }

    /**
    * Returns the acquisition date of this vw item master.
    *
    * @return the acquisition date of this vw item master
    */
    @Override
    public java.util.Date getAcquisitionDate() {
        return _vwItemMaster.getAcquisitionDate();
    }

    /**
    * Sets the acquisition date of this vw item master.
    *
    * @param acquisitionDate the acquisition date of this vw item master
    */
    @Override
    public void setAcquisitionDate(java.util.Date acquisitionDate) {
        _vwItemMaster.setAcquisitionDate(acquisitionDate);
    }

    /**
    * Returns the clotting factor indicator of this vw item master.
    *
    * @return the clotting factor indicator of this vw item master
    */
    @Override
    public java.lang.String getClottingFactorIndicator() {
        return _vwItemMaster.getClottingFactorIndicator();
    }

    /**
    * Sets the clotting factor indicator of this vw item master.
    *
    * @param clottingFactorIndicator the clotting factor indicator of this vw item master
    */
    @Override
    public void setClottingFactorIndicator(
        java.lang.String clottingFactorIndicator) {
        _vwItemMaster.setClottingFactorIndicator(clottingFactorIndicator);
    }

    /**
    * Returns the form of this vw item master.
    *
    * @return the form of this vw item master
    */
    @Override
    public java.lang.String getForm() {
        return _vwItemMaster.getForm();
    }

    /**
    * Sets the form of this vw item master.
    *
    * @param form the form of this vw item master
    */
    @Override
    public void setForm(java.lang.String form) {
        _vwItemMaster.setForm(form);
    }

    /**
    * Returns the item name of this vw item master.
    *
    * @return the item name of this vw item master
    */
    @Override
    public java.lang.String getItemName() {
        return _vwItemMaster.getItemName();
    }

    /**
    * Sets the item name of this vw item master.
    *
    * @param itemName the item name of this vw item master
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwItemMaster.setItemName(itemName);
    }

    /**
    * Returns the manufacturer name of this vw item master.
    *
    * @return the manufacturer name of this vw item master
    */
    @Override
    public java.lang.String getManufacturerName() {
        return _vwItemMaster.getManufacturerName();
    }

    /**
    * Sets the manufacturer name of this vw item master.
    *
    * @param manufacturerName the manufacturer name of this vw item master
    */
    @Override
    public void setManufacturerName(java.lang.String manufacturerName) {
        _vwItemMaster.setManufacturerName(manufacturerName);
    }

    /**
    * Returns the pediatric exclusive start date of this vw item master.
    *
    * @return the pediatric exclusive start date of this vw item master
    */
    @Override
    public java.util.Date getPediatricExclusiveStartDate() {
        return _vwItemMaster.getPediatricExclusiveStartDate();
    }

    /**
    * Sets the pediatric exclusive start date of this vw item master.
    *
    * @param pediatricExclusiveStartDate the pediatric exclusive start date of this vw item master
    */
    @Override
    public void setPediatricExclusiveStartDate(
        java.util.Date pediatricExclusiveStartDate) {
        _vwItemMaster.setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
    }

    /**
    * Returns the first sale date of this vw item master.
    *
    * @return the first sale date of this vw item master
    */
    @Override
    public java.util.Date getFirstSaleDate() {
        return _vwItemMaster.getFirstSaleDate();
    }

    /**
    * Sets the first sale date of this vw item master.
    *
    * @param firstSaleDate the first sale date of this vw item master
    */
    @Override
    public void setFirstSaleDate(java.util.Date firstSaleDate) {
        _vwItemMaster.setFirstSaleDate(firstSaleDate);
    }

    /**
    * Returns the item master sid of this vw item master.
    *
    * @return the item master sid of this vw item master
    */
    @Override
    public int getItemMasterSid() {
        return _vwItemMaster.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this vw item master.
    *
    * @param itemMasterSid the item master sid of this vw item master
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _vwItemMaster.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the item type of this vw item master.
    *
    * @return the item type of this vw item master
    */
    @Override
    public java.lang.String getItemType() {
        return _vwItemMaster.getItemType();
    }

    /**
    * Sets the item type of this vw item master.
    *
    * @param itemType the item type of this vw item master
    */
    @Override
    public void setItemType(java.lang.String itemType) {
        _vwItemMaster.setItemType(itemType);
    }

    /**
    * Returns the item ID of this vw item master.
    *
    * @return the item ID of this vw item master
    */
    @Override
    public java.lang.String getItemId() {
        return _vwItemMaster.getItemId();
    }

    /**
    * Sets the item ID of this vw item master.
    *
    * @param itemId the item ID of this vw item master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwItemMaster.setItemId(itemId);
    }

    /**
    * Returns the baseline amp of this vw item master.
    *
    * @return the baseline amp of this vw item master
    */
    @Override
    public java.lang.String getBaselineAmp() {
        return _vwItemMaster.getBaselineAmp();
    }

    /**
    * Sets the baseline amp of this vw item master.
    *
    * @param baselineAmp the baseline amp of this vw item master
    */
    @Override
    public void setBaselineAmp(java.lang.String baselineAmp) {
        _vwItemMaster.setBaselineAmp(baselineAmp);
    }

    /**
    * Returns the doses per unit of this vw item master.
    *
    * @return the doses per unit of this vw item master
    */
    @Override
    public java.lang.String getDosesPerUnit() {
        return _vwItemMaster.getDosesPerUnit();
    }

    /**
    * Sets the doses per unit of this vw item master.
    *
    * @param dosesPerUnit the doses per unit of this vw item master
    */
    @Override
    public void setDosesPerUnit(java.lang.String dosesPerUnit) {
        _vwItemMaster.setDosesPerUnit(dosesPerUnit);
    }

    /**
    * Returns the dual pricing indicator of this vw item master.
    *
    * @return the dual pricing indicator of this vw item master
    */
    @Override
    public java.lang.String getDualPricingIndicator() {
        return _vwItemMaster.getDualPricingIndicator();
    }

    /**
    * Sets the dual pricing indicator of this vw item master.
    *
    * @param dualPricingIndicator the dual pricing indicator of this vw item master
    */
    @Override
    public void setDualPricingIndicator(java.lang.String dualPricingIndicator) {
        _vwItemMaster.setDualPricingIndicator(dualPricingIndicator);
    }

    /**
    * Returns the base cpi of this vw item master.
    *
    * @return the base cpi of this vw item master
    */
    @Override
    public java.lang.String getBaseCpi() {
        return _vwItemMaster.getBaseCpi();
    }

    /**
    * Sets the base cpi of this vw item master.
    *
    * @param baseCpi the base cpi of this vw item master
    */
    @Override
    public void setBaseCpi(java.lang.String baseCpi) {
        _vwItemMaster.setBaseCpi(baseCpi);
    }

    /**
    * Returns the created date of this vw item master.
    *
    * @return the created date of this vw item master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwItemMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this vw item master.
    *
    * @param createdDate the created date of this vw item master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwItemMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this vw item master.
    *
    * @return the created by of this vw item master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwItemMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this vw item master.
    *
    * @param createdBy the created by of this vw item master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwItemMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the item start date of this vw item master.
    *
    * @return the item start date of this vw item master
    */
    @Override
    public java.util.Date getItemStartDate() {
        return _vwItemMaster.getItemStartDate();
    }

    /**
    * Sets the item start date of this vw item master.
    *
    * @param itemStartDate the item start date of this vw item master
    */
    @Override
    public void setItemStartDate(java.util.Date itemStartDate) {
        _vwItemMaster.setItemStartDate(itemStartDate);
    }

    /**
    * Returns the authorized generic of this vw item master.
    *
    * @return the authorized generic of this vw item master
    */
    @Override
    public java.lang.String getAuthorizedGeneric() {
        return _vwItemMaster.getAuthorizedGeneric();
    }

    /**
    * Sets the authorized generic of this vw item master.
    *
    * @param authorizedGeneric the authorized generic of this vw item master
    */
    @Override
    public void setAuthorizedGeneric(java.lang.String authorizedGeneric) {
        _vwItemMaster.setAuthorizedGeneric(authorizedGeneric);
    }

    /**
    * Returns the ndc9 of this vw item master.
    *
    * @return the ndc9 of this vw item master
    */
    @Override
    public java.lang.String getNdc9() {
        return _vwItemMaster.getNdc9();
    }

    /**
    * Sets the ndc9 of this vw item master.
    *
    * @param ndc9 the ndc9 of this vw item master
    */
    @Override
    public void setNdc9(java.lang.String ndc9) {
        _vwItemMaster.setNdc9(ndc9);
    }

    /**
    * Returns the authorized generic end date of this vw item master.
    *
    * @return the authorized generic end date of this vw item master
    */
    @Override
    public java.util.Date getAuthorizedGenericEndDate() {
        return _vwItemMaster.getAuthorizedGenericEndDate();
    }

    /**
    * Sets the authorized generic end date of this vw item master.
    *
    * @param authorizedGenericEndDate the authorized generic end date of this vw item master
    */
    @Override
    public void setAuthorizedGenericEndDate(
        java.util.Date authorizedGenericEndDate) {
        _vwItemMaster.setAuthorizedGenericEndDate(authorizedGenericEndDate);
    }

    /**
    * Returns the item no of this vw item master.
    *
    * @return the item no of this vw item master
    */
    @Override
    public java.lang.String getItemNo() {
        return _vwItemMaster.getItemNo();
    }

    /**
    * Sets the item no of this vw item master.
    *
    * @param itemNo the item no of this vw item master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _vwItemMaster.setItemNo(itemNo);
    }

    /**
    * Returns the package size of this vw item master.
    *
    * @return the package size of this vw item master
    */
    @Override
    public java.lang.String getPackageSize() {
        return _vwItemMaster.getPackageSize();
    }

    /**
    * Sets the package size of this vw item master.
    *
    * @param packageSize the package size of this vw item master
    */
    @Override
    public void setPackageSize(java.lang.String packageSize) {
        _vwItemMaster.setPackageSize(packageSize);
    }

    /**
    * Returns the ndc8 of this vw item master.
    *
    * @return the ndc8 of this vw item master
    */
    @Override
    public java.lang.String getNdc8() {
        return _vwItemMaster.getNdc8();
    }

    /**
    * Sets the ndc8 of this vw item master.
    *
    * @param ndc8 the ndc8 of this vw item master
    */
    @Override
    public void setNdc8(java.lang.String ndc8) {
        _vwItemMaster.setNdc8(ndc8);
    }

    /**
    * Returns the item class of this vw item master.
    *
    * @return the item class of this vw item master
    */
    @Override
    public java.lang.String getItemClass() {
        return _vwItemMaster.getItemClass();
    }

    /**
    * Sets the item class of this vw item master.
    *
    * @param itemClass the item class of this vw item master
    */
    @Override
    public void setItemClass(java.lang.String itemClass) {
        _vwItemMaster.setItemClass(itemClass);
    }

    /**
    * Returns the labeler code of this vw item master.
    *
    * @return the labeler code of this vw item master
    */
    @Override
    public java.lang.String getLabelerCode() {
        return _vwItemMaster.getLabelerCode();
    }

    /**
    * Sets the labeler code of this vw item master.
    *
    * @param labelerCode the labeler code of this vw item master
    */
    @Override
    public void setLabelerCode(java.lang.String labelerCode) {
        _vwItemMaster.setLabelerCode(labelerCode);
    }

    /**
    * Returns the display brand of this vw item master.
    *
    * @return the display brand of this vw item master
    */
    @Override
    public java.lang.String getDisplayBrand() {
        return _vwItemMaster.getDisplayBrand();
    }

    /**
    * Sets the display brand of this vw item master.
    *
    * @param displayBrand the display brand of this vw item master
    */
    @Override
    public void setDisplayBrand(java.lang.String displayBrand) {
        _vwItemMaster.setDisplayBrand(displayBrand);
    }

    /**
    * Returns the clotting factor end date of this vw item master.
    *
    * @return the clotting factor end date of this vw item master
    */
    @Override
    public java.util.Date getClottingFactorEndDate() {
        return _vwItemMaster.getClottingFactorEndDate();
    }

    /**
    * Sets the clotting factor end date of this vw item master.
    *
    * @param clottingFactorEndDate the clotting factor end date of this vw item master
    */
    @Override
    public void setClottingFactorEndDate(java.util.Date clottingFactorEndDate) {
        _vwItemMaster.setClottingFactorEndDate(clottingFactorEndDate);
    }

    /**
    * Returns the dra of this vw item master.
    *
    * @return the dra of this vw item master
    */
    @Override
    public java.lang.String getDra() {
        return _vwItemMaster.getDra();
    }

    /**
    * Sets the dra of this vw item master.
    *
    * @param dra the dra of this vw item master
    */
    @Override
    public void setDra(java.lang.String dra) {
        _vwItemMaster.setDra(dra);
    }

    /**
    * Returns the brand ID of this vw item master.
    *
    * @return the brand ID of this vw item master
    */
    @Override
    public java.lang.String getBrandId() {
        return _vwItemMaster.getBrandId();
    }

    /**
    * Sets the brand ID of this vw item master.
    *
    * @param brandId the brand ID of this vw item master
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _vwItemMaster.setBrandId(brandId);
    }

    /**
    * Returns the base cpi period of this vw item master.
    *
    * @return the base cpi period of this vw item master
    */
    @Override
    public java.util.Date getBaseCpiPeriod() {
        return _vwItemMaster.getBaseCpiPeriod();
    }

    /**
    * Sets the base cpi period of this vw item master.
    *
    * @param baseCpiPeriod the base cpi period of this vw item master
    */
    @Override
    public void setBaseCpiPeriod(java.util.Date baseCpiPeriod) {
        _vwItemMaster.setBaseCpiPeriod(baseCpiPeriod);
    }

    /**
    * Returns the new formulation indicator of this vw item master.
    *
    * @return the new formulation indicator of this vw item master
    */
    @Override
    public java.lang.String getNewFormulationIndicator() {
        return _vwItemMaster.getNewFormulationIndicator();
    }

    /**
    * Sets the new formulation indicator of this vw item master.
    *
    * @param newFormulationIndicator the new formulation indicator of this vw item master
    */
    @Override
    public void setNewFormulationIndicator(
        java.lang.String newFormulationIndicator) {
        _vwItemMaster.setNewFormulationIndicator(newFormulationIndicator);
    }

    /**
    * Returns the last lot expiration date of this vw item master.
    *
    * @return the last lot expiration date of this vw item master
    */
    @Override
    public java.util.Date getLastLotExpirationDate() {
        return _vwItemMaster.getLastLotExpirationDate();
    }

    /**
    * Sets the last lot expiration date of this vw item master.
    *
    * @param lastLotExpirationDate the last lot expiration date of this vw item master
    */
    @Override
    public void setLastLotExpirationDate(java.util.Date lastLotExpirationDate) {
        _vwItemMaster.setLastLotExpirationDate(lastLotExpirationDate);
    }

    /**
    * Returns the batch ID of this vw item master.
    *
    * @return the batch ID of this vw item master
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwItemMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this vw item master.
    *
    * @param batchId the batch ID of this vw item master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwItemMaster.setBatchId(batchId);
    }

    /**
    * Returns the item code of this vw item master.
    *
    * @return the item code of this vw item master
    */
    @Override
    public java.lang.String getItemCode() {
        return _vwItemMaster.getItemCode();
    }

    /**
    * Sets the item code of this vw item master.
    *
    * @param itemCode the item code of this vw item master
    */
    @Override
    public void setItemCode(java.lang.String itemCode) {
        _vwItemMaster.setItemCode(itemCode);
    }

    /**
    * Returns the clotting factor start date of this vw item master.
    *
    * @return the clotting factor start date of this vw item master
    */
    @Override
    public java.util.Date getClottingFactorStartDate() {
        return _vwItemMaster.getClottingFactorStartDate();
    }

    /**
    * Sets the clotting factor start date of this vw item master.
    *
    * @param clottingFactorStartDate the clotting factor start date of this vw item master
    */
    @Override
    public void setClottingFactorStartDate(
        java.util.Date clottingFactorStartDate) {
        _vwItemMaster.setClottingFactorStartDate(clottingFactorStartDate);
    }

    /**
    * Returns the non federal expiration date of this vw item master.
    *
    * @return the non federal expiration date of this vw item master
    */
    @Override
    public java.util.Date getNonFederalExpirationDate() {
        return _vwItemMaster.getNonFederalExpirationDate();
    }

    /**
    * Sets the non federal expiration date of this vw item master.
    *
    * @param nonFederalExpirationDate the non federal expiration date of this vw item master
    */
    @Override
    public void setNonFederalExpirationDate(
        java.util.Date nonFederalExpirationDate) {
        _vwItemMaster.setNonFederalExpirationDate(nonFederalExpirationDate);
    }

    /**
    * Returns the base cpi precision of this vw item master.
    *
    * @return the base cpi precision of this vw item master
    */
    @Override
    public int getBaseCpiPrecision() {
        return _vwItemMaster.getBaseCpiPrecision();
    }

    /**
    * Sets the base cpi precision of this vw item master.
    *
    * @param baseCpiPrecision the base cpi precision of this vw item master
    */
    @Override
    public void setBaseCpiPrecision(int baseCpiPrecision) {
        _vwItemMaster.setBaseCpiPrecision(baseCpiPrecision);
    }

    /**
    * Returns the baseline amp precision of this vw item master.
    *
    * @return the baseline amp precision of this vw item master
    */
    @Override
    public int getBaselineAmpPrecision() {
        return _vwItemMaster.getBaselineAmpPrecision();
    }

    /**
    * Sets the baseline amp precision of this vw item master.
    *
    * @param baselineAmpPrecision the baseline amp precision of this vw item master
    */
    @Override
    public void setBaselineAmpPrecision(int baselineAmpPrecision) {
        _vwItemMaster.setBaselineAmpPrecision(baselineAmpPrecision);
    }

    @Override
    public boolean isNew() {
        return _vwItemMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwItemMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwItemMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwItemMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwItemMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwItemMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwItemMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwItemMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwItemMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwItemMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwItemMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwItemMasterWrapper((VwItemMaster) _vwItemMaster.clone());
    }

    @Override
    public int compareTo(VwItemMaster vwItemMaster) {
        return _vwItemMaster.compareTo(vwItemMaster);
    }

    @Override
    public int hashCode() {
        return _vwItemMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwItemMaster> toCacheModel() {
        return _vwItemMaster.toCacheModel();
    }

    @Override
    public VwItemMaster toEscapedModel() {
        return new VwItemMasterWrapper(_vwItemMaster.toEscapedModel());
    }

    @Override
    public VwItemMaster toUnescapedModel() {
        return new VwItemMasterWrapper(_vwItemMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwItemMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwItemMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwItemMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwItemMasterWrapper)) {
            return false;
        }

        VwItemMasterWrapper vwItemMasterWrapper = (VwItemMasterWrapper) obj;

        if (Validator.equals(_vwItemMaster, vwItemMasterWrapper._vwItemMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwItemMaster getWrappedVwItemMaster() {
        return _vwItemMaster;
    }

    @Override
    public VwItemMaster getWrappedModel() {
        return _vwItemMaster;
    }

    @Override
    public void resetOriginalValues() {
        _vwItemMaster.resetOriginalValues();
    }
}
