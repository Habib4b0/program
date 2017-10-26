package com.stpl.app.model.impl;

import com.stpl.app.model.PsContractDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PsContractDetails in entity cache.
 *
 * @author
 * @see PsContractDetails
 * @generated
 */
public class PsContractDetailsCacheModel implements CacheModel<PsContractDetails>,
    Externalizable {
    public double price;
    public int itemMasterSid;
    public long priceProtectionStartDate;
    public double basePrice;
    public long modifiedDate;
    public long revisionDate;
    public double priceTolerance;
    public long createdDate;
    public String source;
    public int createdBy;
    public double suggestedPrice;
    public long psContractAttachedDate;
    public int psContractDetailsSid;
    public int modifiedBy;
    public String inboundStatus;
    public double contractPrice;
    public int priceToleranceType;
    public int itemPricingQualifierSid;
    public long contractPriceEndDate;
    public int priceToleranceFrequency;
    public long contractPriceStartDate;
    public int psContractSid;
    public long priceProtectionEndDate;
    public int psContractAttachedStatus;
    public boolean recordLockStatus;
    public String batchId;
    public int priceToleranceInterval;
    public double priceRevision;
    public String brandMasterSid;
    public double nep;
    public int priceProtectionStatus;
    public int priceProtectionPriceType;
    public int nepFormula;
    public double maxIncrementalChange;
    public int resetEligible;
    public int resetType;
    public long resetDate;
    public int resetInterval;
    public int resetFrequency;
    public int netPriceType;
    public String netPriceTypeFormula;
    public int basePriceType;
    public double basePriceEntry;
    public long basePriceDate;
    public int basePriceDdlb;
    public int netBasePrice;
    public int netBasePriceFormulaId;
    public int subsequentPeriodPriceType;
    public int netSubsequentPeriodPrice;
    public int netSubsequentPriceFormulaId;
    public int resetPriceType;
    public int netResetPriceType;
    public int netResetPriceFormulaId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(107);

        sb.append("{price=");
        sb.append(price);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", priceProtectionStartDate=");
        sb.append(priceProtectionStartDate);
        sb.append(", basePrice=");
        sb.append(basePrice);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", revisionDate=");
        sb.append(revisionDate);
        sb.append(", priceTolerance=");
        sb.append(priceTolerance);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", suggestedPrice=");
        sb.append(suggestedPrice);
        sb.append(", psContractAttachedDate=");
        sb.append(psContractAttachedDate);
        sb.append(", psContractDetailsSid=");
        sb.append(psContractDetailsSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", contractPrice=");
        sb.append(contractPrice);
        sb.append(", priceToleranceType=");
        sb.append(priceToleranceType);
        sb.append(", itemPricingQualifierSid=");
        sb.append(itemPricingQualifierSid);
        sb.append(", contractPriceEndDate=");
        sb.append(contractPriceEndDate);
        sb.append(", priceToleranceFrequency=");
        sb.append(priceToleranceFrequency);
        sb.append(", contractPriceStartDate=");
        sb.append(contractPriceStartDate);
        sb.append(", psContractSid=");
        sb.append(psContractSid);
        sb.append(", priceProtectionEndDate=");
        sb.append(priceProtectionEndDate);
        sb.append(", psContractAttachedStatus=");
        sb.append(psContractAttachedStatus);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", priceToleranceInterval=");
        sb.append(priceToleranceInterval);
        sb.append(", priceRevision=");
        sb.append(priceRevision);
        sb.append(", brandMasterSid=");
        sb.append(brandMasterSid);
        sb.append(", nep=");
        sb.append(nep);
        sb.append(", priceProtectionStatus=");
        sb.append(priceProtectionStatus);
        sb.append(", priceProtectionPriceType=");
        sb.append(priceProtectionPriceType);
        sb.append(", nepFormula=");
        sb.append(nepFormula);
        sb.append(", maxIncrementalChange=");
        sb.append(maxIncrementalChange);
        sb.append(", resetEligible=");
        sb.append(resetEligible);
        sb.append(", resetType=");
        sb.append(resetType);
        sb.append(", resetDate=");
        sb.append(resetDate);
        sb.append(", resetInterval=");
        sb.append(resetInterval);
        sb.append(", resetFrequency=");
        sb.append(resetFrequency);
        sb.append(", netPriceType=");
        sb.append(netPriceType);
        sb.append(", netPriceTypeFormula=");
        sb.append(netPriceTypeFormula);
        sb.append(", basePriceType=");
        sb.append(basePriceType);
        sb.append(", basePriceEntry=");
        sb.append(basePriceEntry);
        sb.append(", basePriceDate=");
        sb.append(basePriceDate);
        sb.append(", basePriceDdlb=");
        sb.append(basePriceDdlb);
        sb.append(", netBasePrice=");
        sb.append(netBasePrice);
        sb.append(", netBasePriceFormulaId=");
        sb.append(netBasePriceFormulaId);
        sb.append(", subsequentPeriodPriceType=");
        sb.append(subsequentPeriodPriceType);
        sb.append(", netSubsequentPeriodPrice=");
        sb.append(netSubsequentPeriodPrice);
        sb.append(", netSubsequentPriceFormulaId=");
        sb.append(netSubsequentPriceFormulaId);
        sb.append(", resetPriceType=");
        sb.append(resetPriceType);
        sb.append(", netResetPriceType=");
        sb.append(netResetPriceType);
        sb.append(", netResetPriceFormulaId=");
        sb.append(netResetPriceFormulaId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PsContractDetails toEntityModel() {
        PsContractDetailsImpl psContractDetailsImpl = new PsContractDetailsImpl();

        psContractDetailsImpl.setPrice(price);
        psContractDetailsImpl.setItemMasterSid(itemMasterSid);

        if (priceProtectionStartDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setPriceProtectionStartDate(null);
        } else {
            psContractDetailsImpl.setPriceProtectionStartDate(new Date(
                    priceProtectionStartDate));
        }

        psContractDetailsImpl.setBasePrice(basePrice);

        if (modifiedDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setModifiedDate(null);
        } else {
            psContractDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (revisionDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setRevisionDate(null);
        } else {
            psContractDetailsImpl.setRevisionDate(new Date(revisionDate));
        }

        psContractDetailsImpl.setPriceTolerance(priceTolerance);

        if (createdDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setCreatedDate(null);
        } else {
            psContractDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            psContractDetailsImpl.setSource(StringPool.BLANK);
        } else {
            psContractDetailsImpl.setSource(source);
        }

        psContractDetailsImpl.setCreatedBy(createdBy);
        psContractDetailsImpl.setSuggestedPrice(suggestedPrice);

        if (psContractAttachedDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setPsContractAttachedDate(null);
        } else {
            psContractDetailsImpl.setPsContractAttachedDate(new Date(
                    psContractAttachedDate));
        }

        psContractDetailsImpl.setPsContractDetailsSid(psContractDetailsSid);
        psContractDetailsImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            psContractDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            psContractDetailsImpl.setInboundStatus(inboundStatus);
        }

        psContractDetailsImpl.setContractPrice(contractPrice);
        psContractDetailsImpl.setPriceToleranceType(priceToleranceType);
        psContractDetailsImpl.setItemPricingQualifierSid(itemPricingQualifierSid);

        if (contractPriceEndDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setContractPriceEndDate(null);
        } else {
            psContractDetailsImpl.setContractPriceEndDate(new Date(
                    contractPriceEndDate));
        }

        psContractDetailsImpl.setPriceToleranceFrequency(priceToleranceFrequency);

        if (contractPriceStartDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setContractPriceStartDate(null);
        } else {
            psContractDetailsImpl.setContractPriceStartDate(new Date(
                    contractPriceStartDate));
        }

        psContractDetailsImpl.setPsContractSid(psContractSid);

        if (priceProtectionEndDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setPriceProtectionEndDate(null);
        } else {
            psContractDetailsImpl.setPriceProtectionEndDate(new Date(
                    priceProtectionEndDate));
        }

        psContractDetailsImpl.setPsContractAttachedStatus(psContractAttachedStatus);
        psContractDetailsImpl.setRecordLockStatus(recordLockStatus);

        if (batchId == null) {
            psContractDetailsImpl.setBatchId(StringPool.BLANK);
        } else {
            psContractDetailsImpl.setBatchId(batchId);
        }

        psContractDetailsImpl.setPriceToleranceInterval(priceToleranceInterval);
        psContractDetailsImpl.setPriceRevision(priceRevision);

        if (brandMasterSid == null) {
            psContractDetailsImpl.setBrandMasterSid(StringPool.BLANK);
        } else {
            psContractDetailsImpl.setBrandMasterSid(brandMasterSid);
        }

        psContractDetailsImpl.setNep(nep);
        psContractDetailsImpl.setPriceProtectionStatus(priceProtectionStatus);
        psContractDetailsImpl.setPriceProtectionPriceType(priceProtectionPriceType);
        psContractDetailsImpl.setNepFormula(nepFormula);
        psContractDetailsImpl.setMaxIncrementalChange(maxIncrementalChange);
        psContractDetailsImpl.setResetEligible(resetEligible);
        psContractDetailsImpl.setResetType(resetType);

        if (resetDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setResetDate(null);
        } else {
            psContractDetailsImpl.setResetDate(new Date(resetDate));
        }

        psContractDetailsImpl.setResetInterval(resetInterval);
        psContractDetailsImpl.setResetFrequency(resetFrequency);
        psContractDetailsImpl.setNetPriceType(netPriceType);

        if (netPriceTypeFormula == null) {
            psContractDetailsImpl.setNetPriceTypeFormula(StringPool.BLANK);
        } else {
            psContractDetailsImpl.setNetPriceTypeFormula(netPriceTypeFormula);
        }

        psContractDetailsImpl.setBasePriceType(basePriceType);
        psContractDetailsImpl.setBasePriceEntry(basePriceEntry);

        if (basePriceDate == Long.MIN_VALUE) {
            psContractDetailsImpl.setBasePriceDate(null);
        } else {
            psContractDetailsImpl.setBasePriceDate(new Date(basePriceDate));
        }

        psContractDetailsImpl.setBasePriceDdlb(basePriceDdlb);
        psContractDetailsImpl.setNetBasePrice(netBasePrice);
        psContractDetailsImpl.setNetBasePriceFormulaId(netBasePriceFormulaId);
        psContractDetailsImpl.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
        psContractDetailsImpl.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
        psContractDetailsImpl.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
        psContractDetailsImpl.setResetPriceType(resetPriceType);
        psContractDetailsImpl.setNetResetPriceType(netResetPriceType);
        psContractDetailsImpl.setNetResetPriceFormulaId(netResetPriceFormulaId);

        psContractDetailsImpl.resetOriginalValues();

        return psContractDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        price = objectInput.readDouble();
        itemMasterSid = objectInput.readInt();
        priceProtectionStartDate = objectInput.readLong();
        basePrice = objectInput.readDouble();
        modifiedDate = objectInput.readLong();
        revisionDate = objectInput.readLong();
        priceTolerance = objectInput.readDouble();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdBy = objectInput.readInt();
        suggestedPrice = objectInput.readDouble();
        psContractAttachedDate = objectInput.readLong();
        psContractDetailsSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        contractPrice = objectInput.readDouble();
        priceToleranceType = objectInput.readInt();
        itemPricingQualifierSid = objectInput.readInt();
        contractPriceEndDate = objectInput.readLong();
        priceToleranceFrequency = objectInput.readInt();
        contractPriceStartDate = objectInput.readLong();
        psContractSid = objectInput.readInt();
        priceProtectionEndDate = objectInput.readLong();
        psContractAttachedStatus = objectInput.readInt();
        recordLockStatus = objectInput.readBoolean();
        batchId = objectInput.readUTF();
        priceToleranceInterval = objectInput.readInt();
        priceRevision = objectInput.readDouble();
        brandMasterSid = objectInput.readUTF();
        nep = objectInput.readDouble();
        priceProtectionStatus = objectInput.readInt();
        priceProtectionPriceType = objectInput.readInt();
        nepFormula = objectInput.readInt();
        maxIncrementalChange = objectInput.readDouble();
        resetEligible = objectInput.readInt();
        resetType = objectInput.readInt();
        resetDate = objectInput.readLong();
        resetInterval = objectInput.readInt();
        resetFrequency = objectInput.readInt();
        netPriceType = objectInput.readInt();
        netPriceTypeFormula = objectInput.readUTF();
        basePriceType = objectInput.readInt();
        basePriceEntry = objectInput.readDouble();
        basePriceDate = objectInput.readLong();
        basePriceDdlb = objectInput.readInt();
        netBasePrice = objectInput.readInt();
        netBasePriceFormulaId = objectInput.readInt();
        subsequentPeriodPriceType = objectInput.readInt();
        netSubsequentPeriodPrice = objectInput.readInt();
        netSubsequentPriceFormulaId = objectInput.readInt();
        resetPriceType = objectInput.readInt();
        netResetPriceType = objectInput.readInt();
        netResetPriceFormulaId = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(price);
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeLong(priceProtectionStartDate);
        objectOutput.writeDouble(basePrice);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeLong(revisionDate);
        objectOutput.writeDouble(priceTolerance);
        objectOutput.writeLong(createdDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeDouble(suggestedPrice);
        objectOutput.writeLong(psContractAttachedDate);
        objectOutput.writeInt(psContractDetailsSid);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeDouble(contractPrice);
        objectOutput.writeInt(priceToleranceType);
        objectOutput.writeInt(itemPricingQualifierSid);
        objectOutput.writeLong(contractPriceEndDate);
        objectOutput.writeInt(priceToleranceFrequency);
        objectOutput.writeLong(contractPriceStartDate);
        objectOutput.writeInt(psContractSid);
        objectOutput.writeLong(priceProtectionEndDate);
        objectOutput.writeInt(psContractAttachedStatus);
        objectOutput.writeBoolean(recordLockStatus);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(priceToleranceInterval);
        objectOutput.writeDouble(priceRevision);

        if (brandMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandMasterSid);
        }

        objectOutput.writeDouble(nep);
        objectOutput.writeInt(priceProtectionStatus);
        objectOutput.writeInt(priceProtectionPriceType);
        objectOutput.writeInt(nepFormula);
        objectOutput.writeDouble(maxIncrementalChange);
        objectOutput.writeInt(resetEligible);
        objectOutput.writeInt(resetType);
        objectOutput.writeLong(resetDate);
        objectOutput.writeInt(resetInterval);
        objectOutput.writeInt(resetFrequency);
        objectOutput.writeInt(netPriceType);

        if (netPriceTypeFormula == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netPriceTypeFormula);
        }

        objectOutput.writeInt(basePriceType);
        objectOutput.writeDouble(basePriceEntry);
        objectOutput.writeLong(basePriceDate);
        objectOutput.writeInt(basePriceDdlb);
        objectOutput.writeInt(netBasePrice);
        objectOutput.writeInt(netBasePriceFormulaId);
        objectOutput.writeInt(subsequentPeriodPriceType);
        objectOutput.writeInt(netSubsequentPeriodPrice);
        objectOutput.writeInt(netSubsequentPriceFormulaId);
        objectOutput.writeInt(resetPriceType);
        objectOutput.writeInt(netResetPriceType);
        objectOutput.writeInt(netResetPriceFormulaId);
    }
}
