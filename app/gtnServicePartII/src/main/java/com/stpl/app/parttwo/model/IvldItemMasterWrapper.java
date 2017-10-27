package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldItemMaster}.
 * </p>
 *
 * @author
 * @see IvldItemMaster
 * @generated
 */
public class IvldItemMasterWrapper implements IvldItemMaster,
    ModelWrapper<IvldItemMaster> {
    private IvldItemMaster _ivldItemMaster;

    public IvldItemMasterWrapper(IvldItemMaster ivldItemMaster) {
        _ivldItemMaster = ivldItemMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemNo", getItemNo());
        attributes.put("udc6", getUdc6());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("newFormulationIndicator", getNewFormulationIndicator());
        attributes.put("udc5", getUdc5());
        attributes.put("newFormulationEndDate", getNewFormulationEndDate());
        attributes.put("udc4", getUdc4());
        attributes.put("clottingFactorStartDate", getClottingFactorStartDate());
        attributes.put("secondaryUom", getSecondaryUom());
        attributes.put("itemDesc", getItemDesc());
        attributes.put("authorizedGenericEndDate", getAuthorizedGenericEndDate());
        attributes.put("manufacturerName", getManufacturerName());
        attributes.put("itemName", getItemName());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("status", getStatus());
        attributes.put("baseCpi", getBaseCpi());
        attributes.put("baselineAmp", getBaselineAmp());
        attributes.put("authorizedGeneric", getAuthorizedGeneric());
        attributes.put("therapeuticClass", getTherapeuticClass());
        attributes.put("itemFamilyId", getItemFamilyId());
        attributes.put("pediatricExclusiveStartDate",
            getPediatricExclusiveStartDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("primaryUom", getPrimaryUom());
        attributes.put("ndc9", getNdc9());
        attributes.put("itemId", getItemId());
        attributes.put("lastLotExpirationDate", getLastLotExpirationDate());
        attributes.put("errorField", getErrorField());
        attributes.put("itemCode", getItemCode());
        attributes.put("strength", getStrength());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brand", getBrand());
        attributes.put("ndc8", getNdc8());
        attributes.put("labelerCode", getLabelerCode());
        attributes.put("udc3", getUdc3());
        attributes.put("source", getSource());
        attributes.put("udc2", getUdc2());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("udc1", getUdc1());
        attributes.put("acquiredAmp", getAcquiredAmp());
        attributes.put("discontinuationDate", getDiscontinuationDate());
        attributes.put("itemMasterIntfid", getItemMasterIntfid());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("divestitureDate", getDivestitureDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("baseCpiPeriod", getBaseCpiPeriod());
        attributes.put("clottingFactorEndDate", getClottingFactorEndDate());
        attributes.put("dosesPerUnit", getDosesPerUnit());
        attributes.put("manufacturerId", getManufacturerId());
        attributes.put("clottingFactorIndicator", getClottingFactorIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("acquisitionDate", getAcquisitionDate());
        attributes.put("dualPricingIndicator", getDualPricingIndicator());
        attributes.put("nonFederalExpirationDate", getNonFederalExpirationDate());
        attributes.put("errorCode", getErrorCode());
        attributes.put("newFormulation", getNewFormulation());
        attributes.put("obraBamp", getObraBamp());
        attributes.put("brandId", getBrandId());
        attributes.put("itemStatus", getItemStatus());
        attributes.put("authorizedGenericStartDate",
            getAuthorizedGenericStartDate());
        attributes.put("newFormulationStartDate", getNewFormulationStartDate());
        attributes.put("itemCategory", getItemCategory());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("itemType", getItemType());
        attributes.put("pediatricExclusiveEndDate",
            getPediatricExclusiveEndDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("firstSaleDate", getFirstSaleDate());
        attributes.put("shelfLifeType", getShelfLifeType());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("itemTypeIndication", getItemTypeIndication());
        attributes.put("acquiredBamp", getAcquiredBamp());
        attributes.put("form", getForm());
        attributes.put("itemClass", getItemClass());
        attributes.put("manufacturerNo", getManufacturerNo());
        attributes.put("pediatricExclusiveIndicator",
            getPediatricExclusiveIndicator());
        attributes.put("packageSizeCode", getPackageSizeCode());
        attributes.put("displayBrand", getDisplayBrand());
        attributes.put("dra", getDra());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("packageSizeIntroDate", getPackageSizeIntroDate());
        attributes.put("upps", getUpps());
        attributes.put("ivldItemMasterSid", getIvldItemMasterSid());
        attributes.put("packageSize", getPackageSize());
        attributes.put("shelfLife", getShelfLife());
        attributes.put("marketTerminationDate", getMarketTerminationDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("baseCpiPrecision", getBaseCpiPrecision());
        attributes.put("baselineAmpPrecision", getBaselineAmpPrecision());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String newFormulationIndicator = (String) attributes.get(
                "newFormulationIndicator");

        if (newFormulationIndicator != null) {
            setNewFormulationIndicator(newFormulationIndicator);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        String newFormulationEndDate = (String) attributes.get(
                "newFormulationEndDate");

        if (newFormulationEndDate != null) {
            setNewFormulationEndDate(newFormulationEndDate);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String clottingFactorStartDate = (String) attributes.get(
                "clottingFactorStartDate");

        if (clottingFactorStartDate != null) {
            setClottingFactorStartDate(clottingFactorStartDate);
        }

        String secondaryUom = (String) attributes.get("secondaryUom");

        if (secondaryUom != null) {
            setSecondaryUom(secondaryUom);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        String authorizedGenericEndDate = (String) attributes.get(
                "authorizedGenericEndDate");

        if (authorizedGenericEndDate != null) {
            setAuthorizedGenericEndDate(authorizedGenericEndDate);
        }

        String manufacturerName = (String) attributes.get("manufacturerName");

        if (manufacturerName != null) {
            setManufacturerName(manufacturerName);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        String baseCpi = (String) attributes.get("baseCpi");

        if (baseCpi != null) {
            setBaseCpi(baseCpi);
        }

        String baselineAmp = (String) attributes.get("baselineAmp");

        if (baselineAmp != null) {
            setBaselineAmp(baselineAmp);
        }

        String authorizedGeneric = (String) attributes.get("authorizedGeneric");

        if (authorizedGeneric != null) {
            setAuthorizedGeneric(authorizedGeneric);
        }

        String therapeuticClass = (String) attributes.get("therapeuticClass");

        if (therapeuticClass != null) {
            setTherapeuticClass(therapeuticClass);
        }

        String itemFamilyId = (String) attributes.get("itemFamilyId");

        if (itemFamilyId != null) {
            setItemFamilyId(itemFamilyId);
        }

        String pediatricExclusiveStartDate = (String) attributes.get(
                "pediatricExclusiveStartDate");

        if (pediatricExclusiveStartDate != null) {
            setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String primaryUom = (String) attributes.get("primaryUom");

        if (primaryUom != null) {
            setPrimaryUom(primaryUom);
        }

        String ndc9 = (String) attributes.get("ndc9");

        if (ndc9 != null) {
            setNdc9(ndc9);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String lastLotExpirationDate = (String) attributes.get(
                "lastLotExpirationDate");

        if (lastLotExpirationDate != null) {
            setLastLotExpirationDate(lastLotExpirationDate);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String itemCode = (String) attributes.get("itemCode");

        if (itemCode != null) {
            setItemCode(itemCode);
        }

        String strength = (String) attributes.get("strength");

        if (strength != null) {
            setStrength(strength);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }

        String labelerCode = (String) attributes.get("labelerCode");

        if (labelerCode != null) {
            setLabelerCode(labelerCode);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String acquiredAmp = (String) attributes.get("acquiredAmp");

        if (acquiredAmp != null) {
            setAcquiredAmp(acquiredAmp);
        }

        String discontinuationDate = (String) attributes.get(
                "discontinuationDate");

        if (discontinuationDate != null) {
            setDiscontinuationDate(discontinuationDate);
        }

        String itemMasterIntfid = (String) attributes.get("itemMasterIntfid");

        if (itemMasterIntfid != null) {
            setItemMasterIntfid(itemMasterIntfid);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String divestitureDate = (String) attributes.get("divestitureDate");

        if (divestitureDate != null) {
            setDivestitureDate(divestitureDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String baseCpiPeriod = (String) attributes.get("baseCpiPeriod");

        if (baseCpiPeriod != null) {
            setBaseCpiPeriod(baseCpiPeriod);
        }

        String clottingFactorEndDate = (String) attributes.get(
                "clottingFactorEndDate");

        if (clottingFactorEndDate != null) {
            setClottingFactorEndDate(clottingFactorEndDate);
        }

        String dosesPerUnit = (String) attributes.get("dosesPerUnit");

        if (dosesPerUnit != null) {
            setDosesPerUnit(dosesPerUnit);
        }

        String manufacturerId = (String) attributes.get("manufacturerId");

        if (manufacturerId != null) {
            setManufacturerId(manufacturerId);
        }

        String clottingFactorIndicator = (String) attributes.get(
                "clottingFactorIndicator");

        if (clottingFactorIndicator != null) {
            setClottingFactorIndicator(clottingFactorIndicator);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String acquisitionDate = (String) attributes.get("acquisitionDate");

        if (acquisitionDate != null) {
            setAcquisitionDate(acquisitionDate);
        }

        String dualPricingIndicator = (String) attributes.get(
                "dualPricingIndicator");

        if (dualPricingIndicator != null) {
            setDualPricingIndicator(dualPricingIndicator);
        }

        String nonFederalExpirationDate = (String) attributes.get(
                "nonFederalExpirationDate");

        if (nonFederalExpirationDate != null) {
            setNonFederalExpirationDate(nonFederalExpirationDate);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String newFormulation = (String) attributes.get("newFormulation");

        if (newFormulation != null) {
            setNewFormulation(newFormulation);
        }

        String obraBamp = (String) attributes.get("obraBamp");

        if (obraBamp != null) {
            setObraBamp(obraBamp);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String authorizedGenericStartDate = (String) attributes.get(
                "authorizedGenericStartDate");

        if (authorizedGenericStartDate != null) {
            setAuthorizedGenericStartDate(authorizedGenericStartDate);
        }

        String newFormulationStartDate = (String) attributes.get(
                "newFormulationStartDate");

        if (newFormulationStartDate != null) {
            setNewFormulationStartDate(newFormulationStartDate);
        }

        String itemCategory = (String) attributes.get("itemCategory");

        if (itemCategory != null) {
            setItemCategory(itemCategory);
        }

        String itemEndDate = (String) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        String itemType = (String) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        String pediatricExclusiveEndDate = (String) attributes.get(
                "pediatricExclusiveEndDate");

        if (pediatricExclusiveEndDate != null) {
            setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String firstSaleDate = (String) attributes.get("firstSaleDate");

        if (firstSaleDate != null) {
            setFirstSaleDate(firstSaleDate);
        }

        String shelfLifeType = (String) attributes.get("shelfLifeType");

        if (shelfLifeType != null) {
            setShelfLifeType(shelfLifeType);
        }

        String itemStartDate = (String) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String itemTypeIndication = (String) attributes.get(
                "itemTypeIndication");

        if (itemTypeIndication != null) {
            setItemTypeIndication(itemTypeIndication);
        }

        String acquiredBamp = (String) attributes.get("acquiredBamp");

        if (acquiredBamp != null) {
            setAcquiredBamp(acquiredBamp);
        }

        String form = (String) attributes.get("form");

        if (form != null) {
            setForm(form);
        }

        String itemClass = (String) attributes.get("itemClass");

        if (itemClass != null) {
            setItemClass(itemClass);
        }

        String manufacturerNo = (String) attributes.get("manufacturerNo");

        if (manufacturerNo != null) {
            setManufacturerNo(manufacturerNo);
        }

        String pediatricExclusiveIndicator = (String) attributes.get(
                "pediatricExclusiveIndicator");

        if (pediatricExclusiveIndicator != null) {
            setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
        }

        String packageSizeCode = (String) attributes.get("packageSizeCode");

        if (packageSizeCode != null) {
            setPackageSizeCode(packageSizeCode);
        }

        String displayBrand = (String) attributes.get("displayBrand");

        if (displayBrand != null) {
            setDisplayBrand(displayBrand);
        }

        String dra = (String) attributes.get("dra");

        if (dra != null) {
            setDra(dra);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String packageSizeIntroDate = (String) attributes.get(
                "packageSizeIntroDate");

        if (packageSizeIntroDate != null) {
            setPackageSizeIntroDate(packageSizeIntroDate);
        }

        String upps = (String) attributes.get("upps");

        if (upps != null) {
            setUpps(upps);
        }

        Integer ivldItemMasterSid = (Integer) attributes.get(
                "ivldItemMasterSid");

        if (ivldItemMasterSid != null) {
            setIvldItemMasterSid(ivldItemMasterSid);
        }

        String packageSize = (String) attributes.get("packageSize");

        if (packageSize != null) {
            setPackageSize(packageSize);
        }

        String shelfLife = (String) attributes.get("shelfLife");

        if (shelfLife != null) {
            setShelfLife(shelfLife);
        }

        String marketTerminationDate = (String) attributes.get(
                "marketTerminationDate");

        if (marketTerminationDate != null) {
            setMarketTerminationDate(marketTerminationDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
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
    * Returns the primary key of this ivld item master.
    *
    * @return the primary key of this ivld item master
    */
    @Override
    public int getPrimaryKey() {
        return _ivldItemMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld item master.
    *
    * @param primaryKey the primary key of this ivld item master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldItemMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item no of this ivld item master.
    *
    * @return the item no of this ivld item master
    */
    @Override
    public java.lang.String getItemNo() {
        return _ivldItemMaster.getItemNo();
    }

    /**
    * Sets the item no of this ivld item master.
    *
    * @param itemNo the item no of this ivld item master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _ivldItemMaster.setItemNo(itemNo);
    }

    /**
    * Returns the udc6 of this ivld item master.
    *
    * @return the udc6 of this ivld item master
    */
    @Override
    public java.lang.String getUdc6() {
        return _ivldItemMaster.getUdc6();
    }

    /**
    * Sets the udc6 of this ivld item master.
    *
    * @param udc6 the udc6 of this ivld item master
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _ivldItemMaster.setUdc6(udc6);
    }

    /**
    * Returns the created date of this ivld item master.
    *
    * @return the created date of this ivld item master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldItemMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld item master.
    *
    * @param createdDate the created date of this ivld item master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldItemMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the new formulation indicator of this ivld item master.
    *
    * @return the new formulation indicator of this ivld item master
    */
    @Override
    public java.lang.String getNewFormulationIndicator() {
        return _ivldItemMaster.getNewFormulationIndicator();
    }

    /**
    * Sets the new formulation indicator of this ivld item master.
    *
    * @param newFormulationIndicator the new formulation indicator of this ivld item master
    */
    @Override
    public void setNewFormulationIndicator(
        java.lang.String newFormulationIndicator) {
        _ivldItemMaster.setNewFormulationIndicator(newFormulationIndicator);
    }

    /**
    * Returns the udc5 of this ivld item master.
    *
    * @return the udc5 of this ivld item master
    */
    @Override
    public java.lang.String getUdc5() {
        return _ivldItemMaster.getUdc5();
    }

    /**
    * Sets the udc5 of this ivld item master.
    *
    * @param udc5 the udc5 of this ivld item master
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _ivldItemMaster.setUdc5(udc5);
    }

    /**
    * Returns the new formulation end date of this ivld item master.
    *
    * @return the new formulation end date of this ivld item master
    */
    @Override
    public java.lang.String getNewFormulationEndDate() {
        return _ivldItemMaster.getNewFormulationEndDate();
    }

    /**
    * Sets the new formulation end date of this ivld item master.
    *
    * @param newFormulationEndDate the new formulation end date of this ivld item master
    */
    @Override
    public void setNewFormulationEndDate(java.lang.String newFormulationEndDate) {
        _ivldItemMaster.setNewFormulationEndDate(newFormulationEndDate);
    }

    /**
    * Returns the udc4 of this ivld item master.
    *
    * @return the udc4 of this ivld item master
    */
    @Override
    public java.lang.String getUdc4() {
        return _ivldItemMaster.getUdc4();
    }

    /**
    * Sets the udc4 of this ivld item master.
    *
    * @param udc4 the udc4 of this ivld item master
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _ivldItemMaster.setUdc4(udc4);
    }

    /**
    * Returns the clotting factor start date of this ivld item master.
    *
    * @return the clotting factor start date of this ivld item master
    */
    @Override
    public java.lang.String getClottingFactorStartDate() {
        return _ivldItemMaster.getClottingFactorStartDate();
    }

    /**
    * Sets the clotting factor start date of this ivld item master.
    *
    * @param clottingFactorStartDate the clotting factor start date of this ivld item master
    */
    @Override
    public void setClottingFactorStartDate(
        java.lang.String clottingFactorStartDate) {
        _ivldItemMaster.setClottingFactorStartDate(clottingFactorStartDate);
    }

    /**
    * Returns the secondary uom of this ivld item master.
    *
    * @return the secondary uom of this ivld item master
    */
    @Override
    public java.lang.String getSecondaryUom() {
        return _ivldItemMaster.getSecondaryUom();
    }

    /**
    * Sets the secondary uom of this ivld item master.
    *
    * @param secondaryUom the secondary uom of this ivld item master
    */
    @Override
    public void setSecondaryUom(java.lang.String secondaryUom) {
        _ivldItemMaster.setSecondaryUom(secondaryUom);
    }

    /**
    * Returns the item desc of this ivld item master.
    *
    * @return the item desc of this ivld item master
    */
    @Override
    public java.lang.String getItemDesc() {
        return _ivldItemMaster.getItemDesc();
    }

    /**
    * Sets the item desc of this ivld item master.
    *
    * @param itemDesc the item desc of this ivld item master
    */
    @Override
    public void setItemDesc(java.lang.String itemDesc) {
        _ivldItemMaster.setItemDesc(itemDesc);
    }

    /**
    * Returns the authorized generic end date of this ivld item master.
    *
    * @return the authorized generic end date of this ivld item master
    */
    @Override
    public java.lang.String getAuthorizedGenericEndDate() {
        return _ivldItemMaster.getAuthorizedGenericEndDate();
    }

    /**
    * Sets the authorized generic end date of this ivld item master.
    *
    * @param authorizedGenericEndDate the authorized generic end date of this ivld item master
    */
    @Override
    public void setAuthorizedGenericEndDate(
        java.lang.String authorizedGenericEndDate) {
        _ivldItemMaster.setAuthorizedGenericEndDate(authorizedGenericEndDate);
    }

    /**
    * Returns the manufacturer name of this ivld item master.
    *
    * @return the manufacturer name of this ivld item master
    */
    @Override
    public java.lang.String getManufacturerName() {
        return _ivldItemMaster.getManufacturerName();
    }

    /**
    * Sets the manufacturer name of this ivld item master.
    *
    * @param manufacturerName the manufacturer name of this ivld item master
    */
    @Override
    public void setManufacturerName(java.lang.String manufacturerName) {
        _ivldItemMaster.setManufacturerName(manufacturerName);
    }

    /**
    * Returns the item name of this ivld item master.
    *
    * @return the item name of this ivld item master
    */
    @Override
    public java.lang.String getItemName() {
        return _ivldItemMaster.getItemName();
    }

    /**
    * Sets the item name of this ivld item master.
    *
    * @param itemName the item name of this ivld item master
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _ivldItemMaster.setItemName(itemName);
    }

    /**
    * Returns the reprocessed flag of this ivld item master.
    *
    * @return the reprocessed flag of this ivld item master
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldItemMaster.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld item master.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld item master
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldItemMaster.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the status of this ivld item master.
    *
    * @return the status of this ivld item master
    */
    @Override
    public java.lang.String getStatus() {
        return _ivldItemMaster.getStatus();
    }

    /**
    * Sets the status of this ivld item master.
    *
    * @param status the status of this ivld item master
    */
    @Override
    public void setStatus(java.lang.String status) {
        _ivldItemMaster.setStatus(status);
    }

    /**
    * Returns the base cpi of this ivld item master.
    *
    * @return the base cpi of this ivld item master
    */
    @Override
    public java.lang.String getBaseCpi() {
        return _ivldItemMaster.getBaseCpi();
    }

    /**
    * Sets the base cpi of this ivld item master.
    *
    * @param baseCpi the base cpi of this ivld item master
    */
    @Override
    public void setBaseCpi(java.lang.String baseCpi) {
        _ivldItemMaster.setBaseCpi(baseCpi);
    }

    /**
    * Returns the baseline amp of this ivld item master.
    *
    * @return the baseline amp of this ivld item master
    */
    @Override
    public java.lang.String getBaselineAmp() {
        return _ivldItemMaster.getBaselineAmp();
    }

    /**
    * Sets the baseline amp of this ivld item master.
    *
    * @param baselineAmp the baseline amp of this ivld item master
    */
    @Override
    public void setBaselineAmp(java.lang.String baselineAmp) {
        _ivldItemMaster.setBaselineAmp(baselineAmp);
    }

    /**
    * Returns the authorized generic of this ivld item master.
    *
    * @return the authorized generic of this ivld item master
    */
    @Override
    public java.lang.String getAuthorizedGeneric() {
        return _ivldItemMaster.getAuthorizedGeneric();
    }

    /**
    * Sets the authorized generic of this ivld item master.
    *
    * @param authorizedGeneric the authorized generic of this ivld item master
    */
    @Override
    public void setAuthorizedGeneric(java.lang.String authorizedGeneric) {
        _ivldItemMaster.setAuthorizedGeneric(authorizedGeneric);
    }

    /**
    * Returns the therapeutic class of this ivld item master.
    *
    * @return the therapeutic class of this ivld item master
    */
    @Override
    public java.lang.String getTherapeuticClass() {
        return _ivldItemMaster.getTherapeuticClass();
    }

    /**
    * Sets the therapeutic class of this ivld item master.
    *
    * @param therapeuticClass the therapeutic class of this ivld item master
    */
    @Override
    public void setTherapeuticClass(java.lang.String therapeuticClass) {
        _ivldItemMaster.setTherapeuticClass(therapeuticClass);
    }

    /**
    * Returns the item family ID of this ivld item master.
    *
    * @return the item family ID of this ivld item master
    */
    @Override
    public java.lang.String getItemFamilyId() {
        return _ivldItemMaster.getItemFamilyId();
    }

    /**
    * Sets the item family ID of this ivld item master.
    *
    * @param itemFamilyId the item family ID of this ivld item master
    */
    @Override
    public void setItemFamilyId(java.lang.String itemFamilyId) {
        _ivldItemMaster.setItemFamilyId(itemFamilyId);
    }

    /**
    * Returns the pediatric exclusive start date of this ivld item master.
    *
    * @return the pediatric exclusive start date of this ivld item master
    */
    @Override
    public java.lang.String getPediatricExclusiveStartDate() {
        return _ivldItemMaster.getPediatricExclusiveStartDate();
    }

    /**
    * Sets the pediatric exclusive start date of this ivld item master.
    *
    * @param pediatricExclusiveStartDate the pediatric exclusive start date of this ivld item master
    */
    @Override
    public void setPediatricExclusiveStartDate(
        java.lang.String pediatricExclusiveStartDate) {
        _ivldItemMaster.setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
    }

    /**
    * Returns the created by of this ivld item master.
    *
    * @return the created by of this ivld item master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldItemMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld item master.
    *
    * @param createdBy the created by of this ivld item master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldItemMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the primary uom of this ivld item master.
    *
    * @return the primary uom of this ivld item master
    */
    @Override
    public java.lang.String getPrimaryUom() {
        return _ivldItemMaster.getPrimaryUom();
    }

    /**
    * Sets the primary uom of this ivld item master.
    *
    * @param primaryUom the primary uom of this ivld item master
    */
    @Override
    public void setPrimaryUom(java.lang.String primaryUom) {
        _ivldItemMaster.setPrimaryUom(primaryUom);
    }

    /**
    * Returns the ndc9 of this ivld item master.
    *
    * @return the ndc9 of this ivld item master
    */
    @Override
    public java.lang.String getNdc9() {
        return _ivldItemMaster.getNdc9();
    }

    /**
    * Sets the ndc9 of this ivld item master.
    *
    * @param ndc9 the ndc9 of this ivld item master
    */
    @Override
    public void setNdc9(java.lang.String ndc9) {
        _ivldItemMaster.setNdc9(ndc9);
    }

    /**
    * Returns the item ID of this ivld item master.
    *
    * @return the item ID of this ivld item master
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldItemMaster.getItemId();
    }

    /**
    * Sets the item ID of this ivld item master.
    *
    * @param itemId the item ID of this ivld item master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldItemMaster.setItemId(itemId);
    }

    /**
    * Returns the last lot expiration date of this ivld item master.
    *
    * @return the last lot expiration date of this ivld item master
    */
    @Override
    public java.lang.String getLastLotExpirationDate() {
        return _ivldItemMaster.getLastLotExpirationDate();
    }

    /**
    * Sets the last lot expiration date of this ivld item master.
    *
    * @param lastLotExpirationDate the last lot expiration date of this ivld item master
    */
    @Override
    public void setLastLotExpirationDate(java.lang.String lastLotExpirationDate) {
        _ivldItemMaster.setLastLotExpirationDate(lastLotExpirationDate);
    }

    /**
    * Returns the error field of this ivld item master.
    *
    * @return the error field of this ivld item master
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldItemMaster.getErrorField();
    }

    /**
    * Sets the error field of this ivld item master.
    *
    * @param errorField the error field of this ivld item master
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldItemMaster.setErrorField(errorField);
    }

    /**
    * Returns the item code of this ivld item master.
    *
    * @return the item code of this ivld item master
    */
    @Override
    public java.lang.String getItemCode() {
        return _ivldItemMaster.getItemCode();
    }

    /**
    * Sets the item code of this ivld item master.
    *
    * @param itemCode the item code of this ivld item master
    */
    @Override
    public void setItemCode(java.lang.String itemCode) {
        _ivldItemMaster.setItemCode(itemCode);
    }

    /**
    * Returns the strength of this ivld item master.
    *
    * @return the strength of this ivld item master
    */
    @Override
    public java.lang.String getStrength() {
        return _ivldItemMaster.getStrength();
    }

    /**
    * Sets the strength of this ivld item master.
    *
    * @param strength the strength of this ivld item master
    */
    @Override
    public void setStrength(java.lang.String strength) {
        _ivldItemMaster.setStrength(strength);
    }

    /**
    * Returns the modified date of this ivld item master.
    *
    * @return the modified date of this ivld item master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldItemMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld item master.
    *
    * @param modifiedDate the modified date of this ivld item master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldItemMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the brand of this ivld item master.
    *
    * @return the brand of this ivld item master
    */
    @Override
    public java.lang.String getBrand() {
        return _ivldItemMaster.getBrand();
    }

    /**
    * Sets the brand of this ivld item master.
    *
    * @param brand the brand of this ivld item master
    */
    @Override
    public void setBrand(java.lang.String brand) {
        _ivldItemMaster.setBrand(brand);
    }

    /**
    * Returns the ndc8 of this ivld item master.
    *
    * @return the ndc8 of this ivld item master
    */
    @Override
    public java.lang.String getNdc8() {
        return _ivldItemMaster.getNdc8();
    }

    /**
    * Sets the ndc8 of this ivld item master.
    *
    * @param ndc8 the ndc8 of this ivld item master
    */
    @Override
    public void setNdc8(java.lang.String ndc8) {
        _ivldItemMaster.setNdc8(ndc8);
    }

    /**
    * Returns the labeler code of this ivld item master.
    *
    * @return the labeler code of this ivld item master
    */
    @Override
    public java.lang.String getLabelerCode() {
        return _ivldItemMaster.getLabelerCode();
    }

    /**
    * Sets the labeler code of this ivld item master.
    *
    * @param labelerCode the labeler code of this ivld item master
    */
    @Override
    public void setLabelerCode(java.lang.String labelerCode) {
        _ivldItemMaster.setLabelerCode(labelerCode);
    }

    /**
    * Returns the udc3 of this ivld item master.
    *
    * @return the udc3 of this ivld item master
    */
    @Override
    public java.lang.String getUdc3() {
        return _ivldItemMaster.getUdc3();
    }

    /**
    * Sets the udc3 of this ivld item master.
    *
    * @param udc3 the udc3 of this ivld item master
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _ivldItemMaster.setUdc3(udc3);
    }

    /**
    * Returns the source of this ivld item master.
    *
    * @return the source of this ivld item master
    */
    @Override
    public java.lang.String getSource() {
        return _ivldItemMaster.getSource();
    }

    /**
    * Sets the source of this ivld item master.
    *
    * @param source the source of this ivld item master
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldItemMaster.setSource(source);
    }

    /**
    * Returns the udc2 of this ivld item master.
    *
    * @return the udc2 of this ivld item master
    */
    @Override
    public java.lang.String getUdc2() {
        return _ivldItemMaster.getUdc2();
    }

    /**
    * Sets the udc2 of this ivld item master.
    *
    * @param udc2 the udc2 of this ivld item master
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _ivldItemMaster.setUdc2(udc2);
    }

    /**
    * Returns the add chg del indicator of this ivld item master.
    *
    * @return the add chg del indicator of this ivld item master
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldItemMaster.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld item master.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld item master
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldItemMaster.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the udc1 of this ivld item master.
    *
    * @return the udc1 of this ivld item master
    */
    @Override
    public java.lang.String getUdc1() {
        return _ivldItemMaster.getUdc1();
    }

    /**
    * Sets the udc1 of this ivld item master.
    *
    * @param udc1 the udc1 of this ivld item master
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _ivldItemMaster.setUdc1(udc1);
    }

    /**
    * Returns the acquired amp of this ivld item master.
    *
    * @return the acquired amp of this ivld item master
    */
    @Override
    public java.lang.String getAcquiredAmp() {
        return _ivldItemMaster.getAcquiredAmp();
    }

    /**
    * Sets the acquired amp of this ivld item master.
    *
    * @param acquiredAmp the acquired amp of this ivld item master
    */
    @Override
    public void setAcquiredAmp(java.lang.String acquiredAmp) {
        _ivldItemMaster.setAcquiredAmp(acquiredAmp);
    }

    /**
    * Returns the discontinuation date of this ivld item master.
    *
    * @return the discontinuation date of this ivld item master
    */
    @Override
    public java.lang.String getDiscontinuationDate() {
        return _ivldItemMaster.getDiscontinuationDate();
    }

    /**
    * Sets the discontinuation date of this ivld item master.
    *
    * @param discontinuationDate the discontinuation date of this ivld item master
    */
    @Override
    public void setDiscontinuationDate(java.lang.String discontinuationDate) {
        _ivldItemMaster.setDiscontinuationDate(discontinuationDate);
    }

    /**
    * Returns the item master intfid of this ivld item master.
    *
    * @return the item master intfid of this ivld item master
    */
    @Override
    public java.lang.String getItemMasterIntfid() {
        return _ivldItemMaster.getItemMasterIntfid();
    }

    /**
    * Sets the item master intfid of this ivld item master.
    *
    * @param itemMasterIntfid the item master intfid of this ivld item master
    */
    @Override
    public void setItemMasterIntfid(java.lang.String itemMasterIntfid) {
        _ivldItemMaster.setItemMasterIntfid(itemMasterIntfid);
    }

    /**
    * Returns the intf inserted date of this ivld item master.
    *
    * @return the intf inserted date of this ivld item master
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldItemMaster.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld item master.
    *
    * @param intfInsertedDate the intf inserted date of this ivld item master
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldItemMaster.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the divestiture date of this ivld item master.
    *
    * @return the divestiture date of this ivld item master
    */
    @Override
    public java.lang.String getDivestitureDate() {
        return _ivldItemMaster.getDivestitureDate();
    }

    /**
    * Sets the divestiture date of this ivld item master.
    *
    * @param divestitureDate the divestiture date of this ivld item master
    */
    @Override
    public void setDivestitureDate(java.lang.String divestitureDate) {
        _ivldItemMaster.setDivestitureDate(divestitureDate);
    }

    /**
    * Returns the modified by of this ivld item master.
    *
    * @return the modified by of this ivld item master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldItemMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld item master.
    *
    * @param modifiedBy the modified by of this ivld item master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldItemMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the base cpi period of this ivld item master.
    *
    * @return the base cpi period of this ivld item master
    */
    @Override
    public java.lang.String getBaseCpiPeriod() {
        return _ivldItemMaster.getBaseCpiPeriod();
    }

    /**
    * Sets the base cpi period of this ivld item master.
    *
    * @param baseCpiPeriod the base cpi period of this ivld item master
    */
    @Override
    public void setBaseCpiPeriod(java.lang.String baseCpiPeriod) {
        _ivldItemMaster.setBaseCpiPeriod(baseCpiPeriod);
    }

    /**
    * Returns the clotting factor end date of this ivld item master.
    *
    * @return the clotting factor end date of this ivld item master
    */
    @Override
    public java.lang.String getClottingFactorEndDate() {
        return _ivldItemMaster.getClottingFactorEndDate();
    }

    /**
    * Sets the clotting factor end date of this ivld item master.
    *
    * @param clottingFactorEndDate the clotting factor end date of this ivld item master
    */
    @Override
    public void setClottingFactorEndDate(java.lang.String clottingFactorEndDate) {
        _ivldItemMaster.setClottingFactorEndDate(clottingFactorEndDate);
    }

    /**
    * Returns the doses per unit of this ivld item master.
    *
    * @return the doses per unit of this ivld item master
    */
    @Override
    public java.lang.String getDosesPerUnit() {
        return _ivldItemMaster.getDosesPerUnit();
    }

    /**
    * Sets the doses per unit of this ivld item master.
    *
    * @param dosesPerUnit the doses per unit of this ivld item master
    */
    @Override
    public void setDosesPerUnit(java.lang.String dosesPerUnit) {
        _ivldItemMaster.setDosesPerUnit(dosesPerUnit);
    }

    /**
    * Returns the manufacturer ID of this ivld item master.
    *
    * @return the manufacturer ID of this ivld item master
    */
    @Override
    public java.lang.String getManufacturerId() {
        return _ivldItemMaster.getManufacturerId();
    }

    /**
    * Sets the manufacturer ID of this ivld item master.
    *
    * @param manufacturerId the manufacturer ID of this ivld item master
    */
    @Override
    public void setManufacturerId(java.lang.String manufacturerId) {
        _ivldItemMaster.setManufacturerId(manufacturerId);
    }

    /**
    * Returns the clotting factor indicator of this ivld item master.
    *
    * @return the clotting factor indicator of this ivld item master
    */
    @Override
    public java.lang.String getClottingFactorIndicator() {
        return _ivldItemMaster.getClottingFactorIndicator();
    }

    /**
    * Sets the clotting factor indicator of this ivld item master.
    *
    * @param clottingFactorIndicator the clotting factor indicator of this ivld item master
    */
    @Override
    public void setClottingFactorIndicator(
        java.lang.String clottingFactorIndicator) {
        _ivldItemMaster.setClottingFactorIndicator(clottingFactorIndicator);
    }

    /**
    * Returns the batch ID of this ivld item master.
    *
    * @return the batch ID of this ivld item master
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldItemMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld item master.
    *
    * @param batchId the batch ID of this ivld item master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldItemMaster.setBatchId(batchId);
    }

    /**
    * Returns the acquisition date of this ivld item master.
    *
    * @return the acquisition date of this ivld item master
    */
    @Override
    public java.lang.String getAcquisitionDate() {
        return _ivldItemMaster.getAcquisitionDate();
    }

    /**
    * Sets the acquisition date of this ivld item master.
    *
    * @param acquisitionDate the acquisition date of this ivld item master
    */
    @Override
    public void setAcquisitionDate(java.lang.String acquisitionDate) {
        _ivldItemMaster.setAcquisitionDate(acquisitionDate);
    }

    /**
    * Returns the dual pricing indicator of this ivld item master.
    *
    * @return the dual pricing indicator of this ivld item master
    */
    @Override
    public java.lang.String getDualPricingIndicator() {
        return _ivldItemMaster.getDualPricingIndicator();
    }

    /**
    * Sets the dual pricing indicator of this ivld item master.
    *
    * @param dualPricingIndicator the dual pricing indicator of this ivld item master
    */
    @Override
    public void setDualPricingIndicator(java.lang.String dualPricingIndicator) {
        _ivldItemMaster.setDualPricingIndicator(dualPricingIndicator);
    }

    /**
    * Returns the non federal expiration date of this ivld item master.
    *
    * @return the non federal expiration date of this ivld item master
    */
    @Override
    public java.lang.String getNonFederalExpirationDate() {
        return _ivldItemMaster.getNonFederalExpirationDate();
    }

    /**
    * Sets the non federal expiration date of this ivld item master.
    *
    * @param nonFederalExpirationDate the non federal expiration date of this ivld item master
    */
    @Override
    public void setNonFederalExpirationDate(
        java.lang.String nonFederalExpirationDate) {
        _ivldItemMaster.setNonFederalExpirationDate(nonFederalExpirationDate);
    }

    /**
    * Returns the error code of this ivld item master.
    *
    * @return the error code of this ivld item master
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldItemMaster.getErrorCode();
    }

    /**
    * Sets the error code of this ivld item master.
    *
    * @param errorCode the error code of this ivld item master
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldItemMaster.setErrorCode(errorCode);
    }

    /**
    * Returns the new formulation of this ivld item master.
    *
    * @return the new formulation of this ivld item master
    */
    @Override
    public java.lang.String getNewFormulation() {
        return _ivldItemMaster.getNewFormulation();
    }

    /**
    * Sets the new formulation of this ivld item master.
    *
    * @param newFormulation the new formulation of this ivld item master
    */
    @Override
    public void setNewFormulation(java.lang.String newFormulation) {
        _ivldItemMaster.setNewFormulation(newFormulation);
    }

    /**
    * Returns the obra bamp of this ivld item master.
    *
    * @return the obra bamp of this ivld item master
    */
    @Override
    public java.lang.String getObraBamp() {
        return _ivldItemMaster.getObraBamp();
    }

    /**
    * Sets the obra bamp of this ivld item master.
    *
    * @param obraBamp the obra bamp of this ivld item master
    */
    @Override
    public void setObraBamp(java.lang.String obraBamp) {
        _ivldItemMaster.setObraBamp(obraBamp);
    }

    /**
    * Returns the brand ID of this ivld item master.
    *
    * @return the brand ID of this ivld item master
    */
    @Override
    public java.lang.String getBrandId() {
        return _ivldItemMaster.getBrandId();
    }

    /**
    * Sets the brand ID of this ivld item master.
    *
    * @param brandId the brand ID of this ivld item master
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _ivldItemMaster.setBrandId(brandId);
    }

    /**
    * Returns the item status of this ivld item master.
    *
    * @return the item status of this ivld item master
    */
    @Override
    public java.lang.String getItemStatus() {
        return _ivldItemMaster.getItemStatus();
    }

    /**
    * Sets the item status of this ivld item master.
    *
    * @param itemStatus the item status of this ivld item master
    */
    @Override
    public void setItemStatus(java.lang.String itemStatus) {
        _ivldItemMaster.setItemStatus(itemStatus);
    }

    /**
    * Returns the authorized generic start date of this ivld item master.
    *
    * @return the authorized generic start date of this ivld item master
    */
    @Override
    public java.lang.String getAuthorizedGenericStartDate() {
        return _ivldItemMaster.getAuthorizedGenericStartDate();
    }

    /**
    * Sets the authorized generic start date of this ivld item master.
    *
    * @param authorizedGenericStartDate the authorized generic start date of this ivld item master
    */
    @Override
    public void setAuthorizedGenericStartDate(
        java.lang.String authorizedGenericStartDate) {
        _ivldItemMaster.setAuthorizedGenericStartDate(authorizedGenericStartDate);
    }

    /**
    * Returns the new formulation start date of this ivld item master.
    *
    * @return the new formulation start date of this ivld item master
    */
    @Override
    public java.lang.String getNewFormulationStartDate() {
        return _ivldItemMaster.getNewFormulationStartDate();
    }

    /**
    * Sets the new formulation start date of this ivld item master.
    *
    * @param newFormulationStartDate the new formulation start date of this ivld item master
    */
    @Override
    public void setNewFormulationStartDate(
        java.lang.String newFormulationStartDate) {
        _ivldItemMaster.setNewFormulationStartDate(newFormulationStartDate);
    }

    /**
    * Returns the item category of this ivld item master.
    *
    * @return the item category of this ivld item master
    */
    @Override
    public java.lang.String getItemCategory() {
        return _ivldItemMaster.getItemCategory();
    }

    /**
    * Sets the item category of this ivld item master.
    *
    * @param itemCategory the item category of this ivld item master
    */
    @Override
    public void setItemCategory(java.lang.String itemCategory) {
        _ivldItemMaster.setItemCategory(itemCategory);
    }

    /**
    * Returns the item end date of this ivld item master.
    *
    * @return the item end date of this ivld item master
    */
    @Override
    public java.lang.String getItemEndDate() {
        return _ivldItemMaster.getItemEndDate();
    }

    /**
    * Sets the item end date of this ivld item master.
    *
    * @param itemEndDate the item end date of this ivld item master
    */
    @Override
    public void setItemEndDate(java.lang.String itemEndDate) {
        _ivldItemMaster.setItemEndDate(itemEndDate);
    }

    /**
    * Returns the item type of this ivld item master.
    *
    * @return the item type of this ivld item master
    */
    @Override
    public java.lang.String getItemType() {
        return _ivldItemMaster.getItemType();
    }

    /**
    * Sets the item type of this ivld item master.
    *
    * @param itemType the item type of this ivld item master
    */
    @Override
    public void setItemType(java.lang.String itemType) {
        _ivldItemMaster.setItemType(itemType);
    }

    /**
    * Returns the pediatric exclusive end date of this ivld item master.
    *
    * @return the pediatric exclusive end date of this ivld item master
    */
    @Override
    public java.lang.String getPediatricExclusiveEndDate() {
        return _ivldItemMaster.getPediatricExclusiveEndDate();
    }

    /**
    * Sets the pediatric exclusive end date of this ivld item master.
    *
    * @param pediatricExclusiveEndDate the pediatric exclusive end date of this ivld item master
    */
    @Override
    public void setPediatricExclusiveEndDate(
        java.lang.String pediatricExclusiveEndDate) {
        _ivldItemMaster.setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
    }

    /**
    * Returns the organization key of this ivld item master.
    *
    * @return the organization key of this ivld item master
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _ivldItemMaster.getOrganizationKey();
    }

    /**
    * Sets the organization key of this ivld item master.
    *
    * @param organizationKey the organization key of this ivld item master
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _ivldItemMaster.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the first sale date of this ivld item master.
    *
    * @return the first sale date of this ivld item master
    */
    @Override
    public java.lang.String getFirstSaleDate() {
        return _ivldItemMaster.getFirstSaleDate();
    }

    /**
    * Sets the first sale date of this ivld item master.
    *
    * @param firstSaleDate the first sale date of this ivld item master
    */
    @Override
    public void setFirstSaleDate(java.lang.String firstSaleDate) {
        _ivldItemMaster.setFirstSaleDate(firstSaleDate);
    }

    /**
    * Returns the shelf life type of this ivld item master.
    *
    * @return the shelf life type of this ivld item master
    */
    @Override
    public java.lang.String getShelfLifeType() {
        return _ivldItemMaster.getShelfLifeType();
    }

    /**
    * Sets the shelf life type of this ivld item master.
    *
    * @param shelfLifeType the shelf life type of this ivld item master
    */
    @Override
    public void setShelfLifeType(java.lang.String shelfLifeType) {
        _ivldItemMaster.setShelfLifeType(shelfLifeType);
    }

    /**
    * Returns the item start date of this ivld item master.
    *
    * @return the item start date of this ivld item master
    */
    @Override
    public java.lang.String getItemStartDate() {
        return _ivldItemMaster.getItemStartDate();
    }

    /**
    * Sets the item start date of this ivld item master.
    *
    * @param itemStartDate the item start date of this ivld item master
    */
    @Override
    public void setItemStartDate(java.lang.String itemStartDate) {
        _ivldItemMaster.setItemStartDate(itemStartDate);
    }

    /**
    * Returns the item type indication of this ivld item master.
    *
    * @return the item type indication of this ivld item master
    */
    @Override
    public java.lang.String getItemTypeIndication() {
        return _ivldItemMaster.getItemTypeIndication();
    }

    /**
    * Sets the item type indication of this ivld item master.
    *
    * @param itemTypeIndication the item type indication of this ivld item master
    */
    @Override
    public void setItemTypeIndication(java.lang.String itemTypeIndication) {
        _ivldItemMaster.setItemTypeIndication(itemTypeIndication);
    }

    /**
    * Returns the acquired bamp of this ivld item master.
    *
    * @return the acquired bamp of this ivld item master
    */
    @Override
    public java.lang.String getAcquiredBamp() {
        return _ivldItemMaster.getAcquiredBamp();
    }

    /**
    * Sets the acquired bamp of this ivld item master.
    *
    * @param acquiredBamp the acquired bamp of this ivld item master
    */
    @Override
    public void setAcquiredBamp(java.lang.String acquiredBamp) {
        _ivldItemMaster.setAcquiredBamp(acquiredBamp);
    }

    /**
    * Returns the form of this ivld item master.
    *
    * @return the form of this ivld item master
    */
    @Override
    public java.lang.String getForm() {
        return _ivldItemMaster.getForm();
    }

    /**
    * Sets the form of this ivld item master.
    *
    * @param form the form of this ivld item master
    */
    @Override
    public void setForm(java.lang.String form) {
        _ivldItemMaster.setForm(form);
    }

    /**
    * Returns the item class of this ivld item master.
    *
    * @return the item class of this ivld item master
    */
    @Override
    public java.lang.String getItemClass() {
        return _ivldItemMaster.getItemClass();
    }

    /**
    * Sets the item class of this ivld item master.
    *
    * @param itemClass the item class of this ivld item master
    */
    @Override
    public void setItemClass(java.lang.String itemClass) {
        _ivldItemMaster.setItemClass(itemClass);
    }

    /**
    * Returns the manufacturer no of this ivld item master.
    *
    * @return the manufacturer no of this ivld item master
    */
    @Override
    public java.lang.String getManufacturerNo() {
        return _ivldItemMaster.getManufacturerNo();
    }

    /**
    * Sets the manufacturer no of this ivld item master.
    *
    * @param manufacturerNo the manufacturer no of this ivld item master
    */
    @Override
    public void setManufacturerNo(java.lang.String manufacturerNo) {
        _ivldItemMaster.setManufacturerNo(manufacturerNo);
    }

    /**
    * Returns the pediatric exclusive indicator of this ivld item master.
    *
    * @return the pediatric exclusive indicator of this ivld item master
    */
    @Override
    public java.lang.String getPediatricExclusiveIndicator() {
        return _ivldItemMaster.getPediatricExclusiveIndicator();
    }

    /**
    * Sets the pediatric exclusive indicator of this ivld item master.
    *
    * @param pediatricExclusiveIndicator the pediatric exclusive indicator of this ivld item master
    */
    @Override
    public void setPediatricExclusiveIndicator(
        java.lang.String pediatricExclusiveIndicator) {
        _ivldItemMaster.setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
    }

    /**
    * Returns the package size code of this ivld item master.
    *
    * @return the package size code of this ivld item master
    */
    @Override
    public java.lang.String getPackageSizeCode() {
        return _ivldItemMaster.getPackageSizeCode();
    }

    /**
    * Sets the package size code of this ivld item master.
    *
    * @param packageSizeCode the package size code of this ivld item master
    */
    @Override
    public void setPackageSizeCode(java.lang.String packageSizeCode) {
        _ivldItemMaster.setPackageSizeCode(packageSizeCode);
    }

    /**
    * Returns the display brand of this ivld item master.
    *
    * @return the display brand of this ivld item master
    */
    @Override
    public java.lang.String getDisplayBrand() {
        return _ivldItemMaster.getDisplayBrand();
    }

    /**
    * Sets the display brand of this ivld item master.
    *
    * @param displayBrand the display brand of this ivld item master
    */
    @Override
    public void setDisplayBrand(java.lang.String displayBrand) {
        _ivldItemMaster.setDisplayBrand(displayBrand);
    }

    /**
    * Returns the dra of this ivld item master.
    *
    * @return the dra of this ivld item master
    */
    @Override
    public java.lang.String getDra() {
        return _ivldItemMaster.getDra();
    }

    /**
    * Sets the dra of this ivld item master.
    *
    * @param dra the dra of this ivld item master
    */
    @Override
    public void setDra(java.lang.String dra) {
        _ivldItemMaster.setDra(dra);
    }

    /**
    * Returns the reason for failure of this ivld item master.
    *
    * @return the reason for failure of this ivld item master
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldItemMaster.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld item master.
    *
    * @param reasonForFailure the reason for failure of this ivld item master
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldItemMaster.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the package size intro date of this ivld item master.
    *
    * @return the package size intro date of this ivld item master
    */
    @Override
    public java.lang.String getPackageSizeIntroDate() {
        return _ivldItemMaster.getPackageSizeIntroDate();
    }

    /**
    * Sets the package size intro date of this ivld item master.
    *
    * @param packageSizeIntroDate the package size intro date of this ivld item master
    */
    @Override
    public void setPackageSizeIntroDate(java.lang.String packageSizeIntroDate) {
        _ivldItemMaster.setPackageSizeIntroDate(packageSizeIntroDate);
    }

    /**
    * Returns the upps of this ivld item master.
    *
    * @return the upps of this ivld item master
    */
    @Override
    public java.lang.String getUpps() {
        return _ivldItemMaster.getUpps();
    }

    /**
    * Sets the upps of this ivld item master.
    *
    * @param upps the upps of this ivld item master
    */
    @Override
    public void setUpps(java.lang.String upps) {
        _ivldItemMaster.setUpps(upps);
    }

    /**
    * Returns the ivld item master sid of this ivld item master.
    *
    * @return the ivld item master sid of this ivld item master
    */
    @Override
    public int getIvldItemMasterSid() {
        return _ivldItemMaster.getIvldItemMasterSid();
    }

    /**
    * Sets the ivld item master sid of this ivld item master.
    *
    * @param ivldItemMasterSid the ivld item master sid of this ivld item master
    */
    @Override
    public void setIvldItemMasterSid(int ivldItemMasterSid) {
        _ivldItemMaster.setIvldItemMasterSid(ivldItemMasterSid);
    }

    /**
    * Returns the package size of this ivld item master.
    *
    * @return the package size of this ivld item master
    */
    @Override
    public java.lang.String getPackageSize() {
        return _ivldItemMaster.getPackageSize();
    }

    /**
    * Sets the package size of this ivld item master.
    *
    * @param packageSize the package size of this ivld item master
    */
    @Override
    public void setPackageSize(java.lang.String packageSize) {
        _ivldItemMaster.setPackageSize(packageSize);
    }

    /**
    * Returns the shelf life of this ivld item master.
    *
    * @return the shelf life of this ivld item master
    */
    @Override
    public java.lang.String getShelfLife() {
        return _ivldItemMaster.getShelfLife();
    }

    /**
    * Sets the shelf life of this ivld item master.
    *
    * @param shelfLife the shelf life of this ivld item master
    */
    @Override
    public void setShelfLife(java.lang.String shelfLife) {
        _ivldItemMaster.setShelfLife(shelfLife);
    }

    /**
    * Returns the market termination date of this ivld item master.
    *
    * @return the market termination date of this ivld item master
    */
    @Override
    public java.lang.String getMarketTerminationDate() {
        return _ivldItemMaster.getMarketTerminationDate();
    }

    /**
    * Sets the market termination date of this ivld item master.
    *
    * @param marketTerminationDate the market termination date of this ivld item master
    */
    @Override
    public void setMarketTerminationDate(java.lang.String marketTerminationDate) {
        _ivldItemMaster.setMarketTerminationDate(marketTerminationDate);
    }

    /**
    * Returns the check record of this ivld item master.
    *
    * @return the check record of this ivld item master
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldItemMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld item master is check record.
    *
    * @return <code>true</code> if this ivld item master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldItemMaster.isCheckRecord();
    }

    /**
    * Sets whether this ivld item master is check record.
    *
    * @param checkRecord the check record of this ivld item master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldItemMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the base cpi precision of this ivld item master.
    *
    * @return the base cpi precision of this ivld item master
    */
    @Override
    public int getBaseCpiPrecision() {
        return _ivldItemMaster.getBaseCpiPrecision();
    }

    /**
    * Sets the base cpi precision of this ivld item master.
    *
    * @param baseCpiPrecision the base cpi precision of this ivld item master
    */
    @Override
    public void setBaseCpiPrecision(int baseCpiPrecision) {
        _ivldItemMaster.setBaseCpiPrecision(baseCpiPrecision);
    }

    /**
    * Returns the baseline amp precision of this ivld item master.
    *
    * @return the baseline amp precision of this ivld item master
    */
    @Override
    public int getBaselineAmpPrecision() {
        return _ivldItemMaster.getBaselineAmpPrecision();
    }

    /**
    * Sets the baseline amp precision of this ivld item master.
    *
    * @param baselineAmpPrecision the baseline amp precision of this ivld item master
    */
    @Override
    public void setBaselineAmpPrecision(int baselineAmpPrecision) {
        _ivldItemMaster.setBaselineAmpPrecision(baselineAmpPrecision);
    }

    @Override
    public boolean isNew() {
        return _ivldItemMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldItemMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldItemMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldItemMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldItemMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldItemMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldItemMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldItemMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldItemMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldItemMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldItemMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldItemMasterWrapper((IvldItemMaster) _ivldItemMaster.clone());
    }

    @Override
    public int compareTo(IvldItemMaster ivldItemMaster) {
        return _ivldItemMaster.compareTo(ivldItemMaster);
    }

    @Override
    public int hashCode() {
        return _ivldItemMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldItemMaster> toCacheModel() {
        return _ivldItemMaster.toCacheModel();
    }

    @Override
    public IvldItemMaster toEscapedModel() {
        return new IvldItemMasterWrapper(_ivldItemMaster.toEscapedModel());
    }

    @Override
    public IvldItemMaster toUnescapedModel() {
        return new IvldItemMasterWrapper(_ivldItemMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldItemMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldItemMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldItemMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldItemMasterWrapper)) {
            return false;
        }

        IvldItemMasterWrapper ivldItemMasterWrapper = (IvldItemMasterWrapper) obj;

        if (Validator.equals(_ivldItemMaster,
                    ivldItemMasterWrapper._ivldItemMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldItemMaster getWrappedIvldItemMaster() {
        return _ivldItemMaster;
    }

    @Override
    public IvldItemMaster getWrappedModel() {
        return _ivldItemMaster;
    }

    @Override
    public void resetOriginalValues() {
        _ivldItemMaster.resetOriginalValues();
    }
}
