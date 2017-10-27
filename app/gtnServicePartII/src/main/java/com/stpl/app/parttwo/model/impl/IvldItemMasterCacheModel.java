package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.IvldItemMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldItemMaster in entity cache.
 *
 * @author
 * @see IvldItemMaster
 * @generated
 */
public class IvldItemMasterCacheModel implements CacheModel<IvldItemMaster>,
    Externalizable {
    public String itemNo;
    public String udc6;
    public long createdDate;
    public String newFormulationIndicator;
    public String udc5;
    public String newFormulationEndDate;
    public String udc4;
    public String clottingFactorStartDate;
    public String secondaryUom;
    public String itemDesc;
    public String authorizedGenericEndDate;
    public String manufacturerName;
    public String itemName;
    public String reprocessedFlag;
    public String status;
    public String baseCpi;
    public String baselineAmp;
    public String authorizedGeneric;
    public String therapeuticClass;
    public String itemFamilyId;
    public String pediatricExclusiveStartDate;
    public String createdBy;
    public String primaryUom;
    public String ndc9;
    public String itemId;
    public String lastLotExpirationDate;
    public String errorField;
    public String itemCode;
    public String strength;
    public long modifiedDate;
    public String brand;
    public String ndc8;
    public String labelerCode;
    public String udc3;
    public String source;
    public String udc2;
    public String addChgDelIndicator;
    public String udc1;
    public String acquiredAmp;
    public String discontinuationDate;
    public String itemMasterIntfid;
    public long intfInsertedDate;
    public String divestitureDate;
    public String modifiedBy;
    public String baseCpiPeriod;
    public String clottingFactorEndDate;
    public String dosesPerUnit;
    public String manufacturerId;
    public String clottingFactorIndicator;
    public String batchId;
    public String acquisitionDate;
    public String dualPricingIndicator;
    public String nonFederalExpirationDate;
    public String errorCode;
    public String newFormulation;
    public String obraBamp;
    public String brandId;
    public String itemStatus;
    public String authorizedGenericStartDate;
    public String newFormulationStartDate;
    public String itemCategory;
    public String itemEndDate;
    public String itemType;
    public String pediatricExclusiveEndDate;
    public String organizationKey;
    public String firstSaleDate;
    public String shelfLifeType;
    public String itemStartDate;
    public String itemTypeIndication;
    public String acquiredBamp;
    public String form;
    public String itemClass;
    public String manufacturerNo;
    public String pediatricExclusiveIndicator;
    public String packageSizeCode;
    public String displayBrand;
    public String dra;
    public String reasonForFailure;
    public String packageSizeIntroDate;
    public String upps;
    public int ivldItemMasterSid;
    public String packageSize;
    public String shelfLife;
    public String marketTerminationDate;
    public boolean checkRecord;
    public int baseCpiPrecision;
    public int baselineAmpPrecision;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(175);

        sb.append("{itemNo=");
        sb.append(itemNo);
        sb.append(", udc6=");
        sb.append(udc6);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", newFormulationIndicator=");
        sb.append(newFormulationIndicator);
        sb.append(", udc5=");
        sb.append(udc5);
        sb.append(", newFormulationEndDate=");
        sb.append(newFormulationEndDate);
        sb.append(", udc4=");
        sb.append(udc4);
        sb.append(", clottingFactorStartDate=");
        sb.append(clottingFactorStartDate);
        sb.append(", secondaryUom=");
        sb.append(secondaryUom);
        sb.append(", itemDesc=");
        sb.append(itemDesc);
        sb.append(", authorizedGenericEndDate=");
        sb.append(authorizedGenericEndDate);
        sb.append(", manufacturerName=");
        sb.append(manufacturerName);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", status=");
        sb.append(status);
        sb.append(", baseCpi=");
        sb.append(baseCpi);
        sb.append(", baselineAmp=");
        sb.append(baselineAmp);
        sb.append(", authorizedGeneric=");
        sb.append(authorizedGeneric);
        sb.append(", therapeuticClass=");
        sb.append(therapeuticClass);
        sb.append(", itemFamilyId=");
        sb.append(itemFamilyId);
        sb.append(", pediatricExclusiveStartDate=");
        sb.append(pediatricExclusiveStartDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", primaryUom=");
        sb.append(primaryUom);
        sb.append(", ndc9=");
        sb.append(ndc9);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", lastLotExpirationDate=");
        sb.append(lastLotExpirationDate);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", itemCode=");
        sb.append(itemCode);
        sb.append(", strength=");
        sb.append(strength);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", brand=");
        sb.append(brand);
        sb.append(", ndc8=");
        sb.append(ndc8);
        sb.append(", labelerCode=");
        sb.append(labelerCode);
        sb.append(", udc3=");
        sb.append(udc3);
        sb.append(", source=");
        sb.append(source);
        sb.append(", udc2=");
        sb.append(udc2);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", udc1=");
        sb.append(udc1);
        sb.append(", acquiredAmp=");
        sb.append(acquiredAmp);
        sb.append(", discontinuationDate=");
        sb.append(discontinuationDate);
        sb.append(", itemMasterIntfid=");
        sb.append(itemMasterIntfid);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", divestitureDate=");
        sb.append(divestitureDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", baseCpiPeriod=");
        sb.append(baseCpiPeriod);
        sb.append(", clottingFactorEndDate=");
        sb.append(clottingFactorEndDate);
        sb.append(", dosesPerUnit=");
        sb.append(dosesPerUnit);
        sb.append(", manufacturerId=");
        sb.append(manufacturerId);
        sb.append(", clottingFactorIndicator=");
        sb.append(clottingFactorIndicator);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", acquisitionDate=");
        sb.append(acquisitionDate);
        sb.append(", dualPricingIndicator=");
        sb.append(dualPricingIndicator);
        sb.append(", nonFederalExpirationDate=");
        sb.append(nonFederalExpirationDate);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", newFormulation=");
        sb.append(newFormulation);
        sb.append(", obraBamp=");
        sb.append(obraBamp);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", itemStatus=");
        sb.append(itemStatus);
        sb.append(", authorizedGenericStartDate=");
        sb.append(authorizedGenericStartDate);
        sb.append(", newFormulationStartDate=");
        sb.append(newFormulationStartDate);
        sb.append(", itemCategory=");
        sb.append(itemCategory);
        sb.append(", itemEndDate=");
        sb.append(itemEndDate);
        sb.append(", itemType=");
        sb.append(itemType);
        sb.append(", pediatricExclusiveEndDate=");
        sb.append(pediatricExclusiveEndDate);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", firstSaleDate=");
        sb.append(firstSaleDate);
        sb.append(", shelfLifeType=");
        sb.append(shelfLifeType);
        sb.append(", itemStartDate=");
        sb.append(itemStartDate);
        sb.append(", itemTypeIndication=");
        sb.append(itemTypeIndication);
        sb.append(", acquiredBamp=");
        sb.append(acquiredBamp);
        sb.append(", form=");
        sb.append(form);
        sb.append(", itemClass=");
        sb.append(itemClass);
        sb.append(", manufacturerNo=");
        sb.append(manufacturerNo);
        sb.append(", pediatricExclusiveIndicator=");
        sb.append(pediatricExclusiveIndicator);
        sb.append(", packageSizeCode=");
        sb.append(packageSizeCode);
        sb.append(", displayBrand=");
        sb.append(displayBrand);
        sb.append(", dra=");
        sb.append(dra);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", packageSizeIntroDate=");
        sb.append(packageSizeIntroDate);
        sb.append(", upps=");
        sb.append(upps);
        sb.append(", ivldItemMasterSid=");
        sb.append(ivldItemMasterSid);
        sb.append(", packageSize=");
        sb.append(packageSize);
        sb.append(", shelfLife=");
        sb.append(shelfLife);
        sb.append(", marketTerminationDate=");
        sb.append(marketTerminationDate);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", baseCpiPrecision=");
        sb.append(baseCpiPrecision);
        sb.append(", baselineAmpPrecision=");
        sb.append(baselineAmpPrecision);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldItemMaster toEntityModel() {
        IvldItemMasterImpl ivldItemMasterImpl = new IvldItemMasterImpl();

        if (itemNo == null) {
            ivldItemMasterImpl.setItemNo(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemNo(itemNo);
        }

        if (udc6 == null) {
            ivldItemMasterImpl.setUdc6(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setUdc6(udc6);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldItemMasterImpl.setCreatedDate(null);
        } else {
            ivldItemMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (newFormulationIndicator == null) {
            ivldItemMasterImpl.setNewFormulationIndicator(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setNewFormulationIndicator(newFormulationIndicator);
        }

        if (udc5 == null) {
            ivldItemMasterImpl.setUdc5(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setUdc5(udc5);
        }

        if (newFormulationEndDate == null) {
            ivldItemMasterImpl.setNewFormulationEndDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setNewFormulationEndDate(newFormulationEndDate);
        }

        if (udc4 == null) {
            ivldItemMasterImpl.setUdc4(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setUdc4(udc4);
        }

        if (clottingFactorStartDate == null) {
            ivldItemMasterImpl.setClottingFactorStartDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setClottingFactorStartDate(clottingFactorStartDate);
        }

        if (secondaryUom == null) {
            ivldItemMasterImpl.setSecondaryUom(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setSecondaryUom(secondaryUom);
        }

        if (itemDesc == null) {
            ivldItemMasterImpl.setItemDesc(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemDesc(itemDesc);
        }

        if (authorizedGenericEndDate == null) {
            ivldItemMasterImpl.setAuthorizedGenericEndDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setAuthorizedGenericEndDate(authorizedGenericEndDate);
        }

        if (manufacturerName == null) {
            ivldItemMasterImpl.setManufacturerName(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setManufacturerName(manufacturerName);
        }

        if (itemName == null) {
            ivldItemMasterImpl.setItemName(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemName(itemName);
        }

        if (reprocessedFlag == null) {
            ivldItemMasterImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (status == null) {
            ivldItemMasterImpl.setStatus(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setStatus(status);
        }

        if (baseCpi == null) {
            ivldItemMasterImpl.setBaseCpi(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setBaseCpi(baseCpi);
        }

        if (baselineAmp == null) {
            ivldItemMasterImpl.setBaselineAmp(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setBaselineAmp(baselineAmp);
        }

        if (authorizedGeneric == null) {
            ivldItemMasterImpl.setAuthorizedGeneric(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setAuthorizedGeneric(authorizedGeneric);
        }

        if (therapeuticClass == null) {
            ivldItemMasterImpl.setTherapeuticClass(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setTherapeuticClass(therapeuticClass);
        }

        if (itemFamilyId == null) {
            ivldItemMasterImpl.setItemFamilyId(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemFamilyId(itemFamilyId);
        }

        if (pediatricExclusiveStartDate == null) {
            ivldItemMasterImpl.setPediatricExclusiveStartDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
        }

        if (createdBy == null) {
            ivldItemMasterImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setCreatedBy(createdBy);
        }

        if (primaryUom == null) {
            ivldItemMasterImpl.setPrimaryUom(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setPrimaryUom(primaryUom);
        }

        if (ndc9 == null) {
            ivldItemMasterImpl.setNdc9(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setNdc9(ndc9);
        }

        if (itemId == null) {
            ivldItemMasterImpl.setItemId(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemId(itemId);
        }

        if (lastLotExpirationDate == null) {
            ivldItemMasterImpl.setLastLotExpirationDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setLastLotExpirationDate(lastLotExpirationDate);
        }

        if (errorField == null) {
            ivldItemMasterImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setErrorField(errorField);
        }

        if (itemCode == null) {
            ivldItemMasterImpl.setItemCode(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemCode(itemCode);
        }

        if (strength == null) {
            ivldItemMasterImpl.setStrength(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setStrength(strength);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldItemMasterImpl.setModifiedDate(null);
        } else {
            ivldItemMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (brand == null) {
            ivldItemMasterImpl.setBrand(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setBrand(brand);
        }

        if (ndc8 == null) {
            ivldItemMasterImpl.setNdc8(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setNdc8(ndc8);
        }

        if (labelerCode == null) {
            ivldItemMasterImpl.setLabelerCode(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setLabelerCode(labelerCode);
        }

        if (udc3 == null) {
            ivldItemMasterImpl.setUdc3(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setUdc3(udc3);
        }

        if (source == null) {
            ivldItemMasterImpl.setSource(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setSource(source);
        }

        if (udc2 == null) {
            ivldItemMasterImpl.setUdc2(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setUdc2(udc2);
        }

        if (addChgDelIndicator == null) {
            ivldItemMasterImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (udc1 == null) {
            ivldItemMasterImpl.setUdc1(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setUdc1(udc1);
        }

        if (acquiredAmp == null) {
            ivldItemMasterImpl.setAcquiredAmp(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setAcquiredAmp(acquiredAmp);
        }

        if (discontinuationDate == null) {
            ivldItemMasterImpl.setDiscontinuationDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setDiscontinuationDate(discontinuationDate);
        }

        if (itemMasterIntfid == null) {
            ivldItemMasterImpl.setItemMasterIntfid(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemMasterIntfid(itemMasterIntfid);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldItemMasterImpl.setIntfInsertedDate(null);
        } else {
            ivldItemMasterImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (divestitureDate == null) {
            ivldItemMasterImpl.setDivestitureDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setDivestitureDate(divestitureDate);
        }

        if (modifiedBy == null) {
            ivldItemMasterImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setModifiedBy(modifiedBy);
        }

        if (baseCpiPeriod == null) {
            ivldItemMasterImpl.setBaseCpiPeriod(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setBaseCpiPeriod(baseCpiPeriod);
        }

        if (clottingFactorEndDate == null) {
            ivldItemMasterImpl.setClottingFactorEndDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setClottingFactorEndDate(clottingFactorEndDate);
        }

        if (dosesPerUnit == null) {
            ivldItemMasterImpl.setDosesPerUnit(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setDosesPerUnit(dosesPerUnit);
        }

        if (manufacturerId == null) {
            ivldItemMasterImpl.setManufacturerId(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setManufacturerId(manufacturerId);
        }

        if (clottingFactorIndicator == null) {
            ivldItemMasterImpl.setClottingFactorIndicator(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setClottingFactorIndicator(clottingFactorIndicator);
        }

        if (batchId == null) {
            ivldItemMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setBatchId(batchId);
        }

        if (acquisitionDate == null) {
            ivldItemMasterImpl.setAcquisitionDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setAcquisitionDate(acquisitionDate);
        }

        if (dualPricingIndicator == null) {
            ivldItemMasterImpl.setDualPricingIndicator(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setDualPricingIndicator(dualPricingIndicator);
        }

        if (nonFederalExpirationDate == null) {
            ivldItemMasterImpl.setNonFederalExpirationDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setNonFederalExpirationDate(nonFederalExpirationDate);
        }

        if (errorCode == null) {
            ivldItemMasterImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setErrorCode(errorCode);
        }

        if (newFormulation == null) {
            ivldItemMasterImpl.setNewFormulation(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setNewFormulation(newFormulation);
        }

        if (obraBamp == null) {
            ivldItemMasterImpl.setObraBamp(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setObraBamp(obraBamp);
        }

        if (brandId == null) {
            ivldItemMasterImpl.setBrandId(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setBrandId(brandId);
        }

        if (itemStatus == null) {
            ivldItemMasterImpl.setItemStatus(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemStatus(itemStatus);
        }

        if (authorizedGenericStartDate == null) {
            ivldItemMasterImpl.setAuthorizedGenericStartDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setAuthorizedGenericStartDate(authorizedGenericStartDate);
        }

        if (newFormulationStartDate == null) {
            ivldItemMasterImpl.setNewFormulationStartDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setNewFormulationStartDate(newFormulationStartDate);
        }

        if (itemCategory == null) {
            ivldItemMasterImpl.setItemCategory(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemCategory(itemCategory);
        }

        if (itemEndDate == null) {
            ivldItemMasterImpl.setItemEndDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemEndDate(itemEndDate);
        }

        if (itemType == null) {
            ivldItemMasterImpl.setItemType(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemType(itemType);
        }

        if (pediatricExclusiveEndDate == null) {
            ivldItemMasterImpl.setPediatricExclusiveEndDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
        }

        if (organizationKey == null) {
            ivldItemMasterImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setOrganizationKey(organizationKey);
        }

        if (firstSaleDate == null) {
            ivldItemMasterImpl.setFirstSaleDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setFirstSaleDate(firstSaleDate);
        }

        if (shelfLifeType == null) {
            ivldItemMasterImpl.setShelfLifeType(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setShelfLifeType(shelfLifeType);
        }

        if (itemStartDate == null) {
            ivldItemMasterImpl.setItemStartDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemStartDate(itemStartDate);
        }

        if (itemTypeIndication == null) {
            ivldItemMasterImpl.setItemTypeIndication(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemTypeIndication(itemTypeIndication);
        }

        if (acquiredBamp == null) {
            ivldItemMasterImpl.setAcquiredBamp(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setAcquiredBamp(acquiredBamp);
        }

        if (form == null) {
            ivldItemMasterImpl.setForm(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setForm(form);
        }

        if (itemClass == null) {
            ivldItemMasterImpl.setItemClass(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setItemClass(itemClass);
        }

        if (manufacturerNo == null) {
            ivldItemMasterImpl.setManufacturerNo(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setManufacturerNo(manufacturerNo);
        }

        if (pediatricExclusiveIndicator == null) {
            ivldItemMasterImpl.setPediatricExclusiveIndicator(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
        }

        if (packageSizeCode == null) {
            ivldItemMasterImpl.setPackageSizeCode(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setPackageSizeCode(packageSizeCode);
        }

        if (displayBrand == null) {
            ivldItemMasterImpl.setDisplayBrand(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setDisplayBrand(displayBrand);
        }

        if (dra == null) {
            ivldItemMasterImpl.setDra(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setDra(dra);
        }

        if (reasonForFailure == null) {
            ivldItemMasterImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setReasonForFailure(reasonForFailure);
        }

        if (packageSizeIntroDate == null) {
            ivldItemMasterImpl.setPackageSizeIntroDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setPackageSizeIntroDate(packageSizeIntroDate);
        }

        if (upps == null) {
            ivldItemMasterImpl.setUpps(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setUpps(upps);
        }

        ivldItemMasterImpl.setIvldItemMasterSid(ivldItemMasterSid);

        if (packageSize == null) {
            ivldItemMasterImpl.setPackageSize(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setPackageSize(packageSize);
        }

        if (shelfLife == null) {
            ivldItemMasterImpl.setShelfLife(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setShelfLife(shelfLife);
        }

        if (marketTerminationDate == null) {
            ivldItemMasterImpl.setMarketTerminationDate(StringPool.BLANK);
        } else {
            ivldItemMasterImpl.setMarketTerminationDate(marketTerminationDate);
        }

        ivldItemMasterImpl.setCheckRecord(checkRecord);
        ivldItemMasterImpl.setBaseCpiPrecision(baseCpiPrecision);
        ivldItemMasterImpl.setBaselineAmpPrecision(baselineAmpPrecision);

        ivldItemMasterImpl.resetOriginalValues();

        return ivldItemMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemNo = objectInput.readUTF();
        udc6 = objectInput.readUTF();
        createdDate = objectInput.readLong();
        newFormulationIndicator = objectInput.readUTF();
        udc5 = objectInput.readUTF();
        newFormulationEndDate = objectInput.readUTF();
        udc4 = objectInput.readUTF();
        clottingFactorStartDate = objectInput.readUTF();
        secondaryUom = objectInput.readUTF();
        itemDesc = objectInput.readUTF();
        authorizedGenericEndDate = objectInput.readUTF();
        manufacturerName = objectInput.readUTF();
        itemName = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        status = objectInput.readUTF();
        baseCpi = objectInput.readUTF();
        baselineAmp = objectInput.readUTF();
        authorizedGeneric = objectInput.readUTF();
        therapeuticClass = objectInput.readUTF();
        itemFamilyId = objectInput.readUTF();
        pediatricExclusiveStartDate = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        primaryUom = objectInput.readUTF();
        ndc9 = objectInput.readUTF();
        itemId = objectInput.readUTF();
        lastLotExpirationDate = objectInput.readUTF();
        errorField = objectInput.readUTF();
        itemCode = objectInput.readUTF();
        strength = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        brand = objectInput.readUTF();
        ndc8 = objectInput.readUTF();
        labelerCode = objectInput.readUTF();
        udc3 = objectInput.readUTF();
        source = objectInput.readUTF();
        udc2 = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        udc1 = objectInput.readUTF();
        acquiredAmp = objectInput.readUTF();
        discontinuationDate = objectInput.readUTF();
        itemMasterIntfid = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        divestitureDate = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        baseCpiPeriod = objectInput.readUTF();
        clottingFactorEndDate = objectInput.readUTF();
        dosesPerUnit = objectInput.readUTF();
        manufacturerId = objectInput.readUTF();
        clottingFactorIndicator = objectInput.readUTF();
        batchId = objectInput.readUTF();
        acquisitionDate = objectInput.readUTF();
        dualPricingIndicator = objectInput.readUTF();
        nonFederalExpirationDate = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        newFormulation = objectInput.readUTF();
        obraBamp = objectInput.readUTF();
        brandId = objectInput.readUTF();
        itemStatus = objectInput.readUTF();
        authorizedGenericStartDate = objectInput.readUTF();
        newFormulationStartDate = objectInput.readUTF();
        itemCategory = objectInput.readUTF();
        itemEndDate = objectInput.readUTF();
        itemType = objectInput.readUTF();
        pediatricExclusiveEndDate = objectInput.readUTF();
        organizationKey = objectInput.readUTF();
        firstSaleDate = objectInput.readUTF();
        shelfLifeType = objectInput.readUTF();
        itemStartDate = objectInput.readUTF();
        itemTypeIndication = objectInput.readUTF();
        acquiredBamp = objectInput.readUTF();
        form = objectInput.readUTF();
        itemClass = objectInput.readUTF();
        manufacturerNo = objectInput.readUTF();
        pediatricExclusiveIndicator = objectInput.readUTF();
        packageSizeCode = objectInput.readUTF();
        displayBrand = objectInput.readUTF();
        dra = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        packageSizeIntroDate = objectInput.readUTF();
        upps = objectInput.readUTF();
        ivldItemMasterSid = objectInput.readInt();
        packageSize = objectInput.readUTF();
        shelfLife = objectInput.readUTF();
        marketTerminationDate = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
        baseCpiPrecision = objectInput.readInt();
        baselineAmpPrecision = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        if (udc6 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc6);
        }

        objectOutput.writeLong(createdDate);

        if (newFormulationIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(newFormulationIndicator);
        }

        if (udc5 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc5);
        }

        if (newFormulationEndDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(newFormulationEndDate);
        }

        if (udc4 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc4);
        }

        if (clottingFactorStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(clottingFactorStartDate);
        }

        if (secondaryUom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(secondaryUom);
        }

        if (itemDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemDesc);
        }

        if (authorizedGenericEndDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(authorizedGenericEndDate);
        }

        if (manufacturerName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(manufacturerName);
        }

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

        if (status == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(status);
        }

        if (baseCpi == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(baseCpi);
        }

        if (baselineAmp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(baselineAmp);
        }

        if (authorizedGeneric == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(authorizedGeneric);
        }

        if (therapeuticClass == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(therapeuticClass);
        }

        if (itemFamilyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemFamilyId);
        }

        if (pediatricExclusiveStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pediatricExclusiveStartDate);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (primaryUom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(primaryUom);
        }

        if (ndc9 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ndc9);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (lastLotExpirationDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lastLotExpirationDate);
        }

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        if (itemCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemCode);
        }

        if (strength == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(strength);
        }

        objectOutput.writeLong(modifiedDate);

        if (brand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brand);
        }

        if (ndc8 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ndc8);
        }

        if (labelerCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(labelerCode);
        }

        if (udc3 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc3);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (udc2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc2);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (udc1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc1);
        }

        if (acquiredAmp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(acquiredAmp);
        }

        if (discontinuationDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(discontinuationDate);
        }

        if (itemMasterIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemMasterIntfid);
        }

        objectOutput.writeLong(intfInsertedDate);

        if (divestitureDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(divestitureDate);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (baseCpiPeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(baseCpiPeriod);
        }

        if (clottingFactorEndDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(clottingFactorEndDate);
        }

        if (dosesPerUnit == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(dosesPerUnit);
        }

        if (manufacturerId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(manufacturerId);
        }

        if (clottingFactorIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(clottingFactorIndicator);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (acquisitionDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(acquisitionDate);
        }

        if (dualPricingIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(dualPricingIndicator);
        }

        if (nonFederalExpirationDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(nonFederalExpirationDate);
        }

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        if (newFormulation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(newFormulation);
        }

        if (obraBamp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(obraBamp);
        }

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        if (itemStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemStatus);
        }

        if (authorizedGenericStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(authorizedGenericStartDate);
        }

        if (newFormulationStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(newFormulationStartDate);
        }

        if (itemCategory == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemCategory);
        }

        if (itemEndDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemEndDate);
        }

        if (itemType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemType);
        }

        if (pediatricExclusiveEndDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pediatricExclusiveEndDate);
        }

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        if (firstSaleDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(firstSaleDate);
        }

        if (shelfLifeType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(shelfLifeType);
        }

        if (itemStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemStartDate);
        }

        if (itemTypeIndication == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemTypeIndication);
        }

        if (acquiredBamp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(acquiredBamp);
        }

        if (form == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(form);
        }

        if (itemClass == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemClass);
        }

        if (manufacturerNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(manufacturerNo);
        }

        if (pediatricExclusiveIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pediatricExclusiveIndicator);
        }

        if (packageSizeCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(packageSizeCode);
        }

        if (displayBrand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(displayBrand);
        }

        if (dra == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(dra);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (packageSizeIntroDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(packageSizeIntroDate);
        }

        if (upps == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(upps);
        }

        objectOutput.writeInt(ivldItemMasterSid);

        if (packageSize == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(packageSize);
        }

        if (shelfLife == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(shelfLife);
        }

        if (marketTerminationDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketTerminationDate);
        }

        objectOutput.writeBoolean(checkRecord);
        objectOutput.writeInt(baseCpiPrecision);
        objectOutput.writeInt(baselineAmpPrecision);
    }
}
