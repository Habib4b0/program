package com.stpl.app.model.impl;

import com.stpl.app.model.RebatePlanTier;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RebatePlanTier in entity cache.
 *
 * @author
 * @see RebatePlanTier
 * @generated
 */
public class RebatePlanTierCacheModel implements CacheModel<RebatePlanTier>,
    Externalizable {
    public double tierValue;
    public String returnRateSid;
    public int rebatePlanMasterSid;
    public int rebatePlanTierSid;
    public String itemPricingQualifierSid;
    public long modifiedDate;
    public double tierTolerance;
    public String tierFrom;
    public String tierOperator;
    public boolean recordLockStatus;
    public long createdDate;
    public int createdBy;
    public String source;
    public String tierTo;
    public String batchId;
    public String rebatePlanTierId;
    public double freeAmount;
    public int modifiedBy;
    public String inboundStatus;
    public String tierLevel;
    public String formulaNo;
    public String formulaName;
    public String secondaryRebatePlanNo;
    public String secondaryRebatePlanName;
    public String secondaryRebatePlanSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(51);

        sb.append("{tierValue=");
        sb.append(tierValue);
        sb.append(", returnRateSid=");
        sb.append(returnRateSid);
        sb.append(", rebatePlanMasterSid=");
        sb.append(rebatePlanMasterSid);
        sb.append(", rebatePlanTierSid=");
        sb.append(rebatePlanTierSid);
        sb.append(", itemPricingQualifierSid=");
        sb.append(itemPricingQualifierSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", tierTolerance=");
        sb.append(tierTolerance);
        sb.append(", tierFrom=");
        sb.append(tierFrom);
        sb.append(", tierOperator=");
        sb.append(tierOperator);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", tierTo=");
        sb.append(tierTo);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", rebatePlanTierId=");
        sb.append(rebatePlanTierId);
        sb.append(", freeAmount=");
        sb.append(freeAmount);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", tierLevel=");
        sb.append(tierLevel);
        sb.append(", formulaNo=");
        sb.append(formulaNo);
        sb.append(", formulaName=");
        sb.append(formulaName);
        sb.append(", secondaryRebatePlanNo=");
        sb.append(secondaryRebatePlanNo);
        sb.append(", secondaryRebatePlanName=");
        sb.append(secondaryRebatePlanName);
        sb.append(", secondaryRebatePlanSid=");
        sb.append(secondaryRebatePlanSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public RebatePlanTier toEntityModel() {
        RebatePlanTierImpl rebatePlanTierImpl = new RebatePlanTierImpl();

        rebatePlanTierImpl.setTierValue(tierValue);

        if (returnRateSid == null) {
            rebatePlanTierImpl.setReturnRateSid(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setReturnRateSid(returnRateSid);
        }

        rebatePlanTierImpl.setRebatePlanMasterSid(rebatePlanMasterSid);
        rebatePlanTierImpl.setRebatePlanTierSid(rebatePlanTierSid);

        if (itemPricingQualifierSid == null) {
            rebatePlanTierImpl.setItemPricingQualifierSid(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setItemPricingQualifierSid(itemPricingQualifierSid);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            rebatePlanTierImpl.setModifiedDate(null);
        } else {
            rebatePlanTierImpl.setModifiedDate(new Date(modifiedDate));
        }

        rebatePlanTierImpl.setTierTolerance(tierTolerance);

        if (tierFrom == null) {
            rebatePlanTierImpl.setTierFrom(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setTierFrom(tierFrom);
        }

        if (tierOperator == null) {
            rebatePlanTierImpl.setTierOperator(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setTierOperator(tierOperator);
        }

        rebatePlanTierImpl.setRecordLockStatus(recordLockStatus);

        if (createdDate == Long.MIN_VALUE) {
            rebatePlanTierImpl.setCreatedDate(null);
        } else {
            rebatePlanTierImpl.setCreatedDate(new Date(createdDate));
        }

        rebatePlanTierImpl.setCreatedBy(createdBy);

        if (source == null) {
            rebatePlanTierImpl.setSource(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setSource(source);
        }

        if (tierTo == null) {
            rebatePlanTierImpl.setTierTo(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setTierTo(tierTo);
        }

        if (batchId == null) {
            rebatePlanTierImpl.setBatchId(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setBatchId(batchId);
        }

        if (rebatePlanTierId == null) {
            rebatePlanTierImpl.setRebatePlanTierId(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setRebatePlanTierId(rebatePlanTierId);
        }

        rebatePlanTierImpl.setFreeAmount(freeAmount);
        rebatePlanTierImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            rebatePlanTierImpl.setInboundStatus(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setInboundStatus(inboundStatus);
        }

        if (tierLevel == null) {
            rebatePlanTierImpl.setTierLevel(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setTierLevel(tierLevel);
        }

        if (formulaNo == null) {
            rebatePlanTierImpl.setFormulaNo(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setFormulaNo(formulaNo);
        }

        if (formulaName == null) {
            rebatePlanTierImpl.setFormulaName(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setFormulaName(formulaName);
        }

        if (secondaryRebatePlanNo == null) {
            rebatePlanTierImpl.setSecondaryRebatePlanNo(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setSecondaryRebatePlanNo(secondaryRebatePlanNo);
        }

        if (secondaryRebatePlanName == null) {
            rebatePlanTierImpl.setSecondaryRebatePlanName(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setSecondaryRebatePlanName(secondaryRebatePlanName);
        }

        if (secondaryRebatePlanSid == null) {
            rebatePlanTierImpl.setSecondaryRebatePlanSid(StringPool.BLANK);
        } else {
            rebatePlanTierImpl.setSecondaryRebatePlanSid(secondaryRebatePlanSid);
        }

        rebatePlanTierImpl.resetOriginalValues();

        return rebatePlanTierImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        tierValue = objectInput.readDouble();
        returnRateSid = objectInput.readUTF();
        rebatePlanMasterSid = objectInput.readInt();
        rebatePlanTierSid = objectInput.readInt();
        itemPricingQualifierSid = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        tierTolerance = objectInput.readDouble();
        tierFrom = objectInput.readUTF();
        tierOperator = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        source = objectInput.readUTF();
        tierTo = objectInput.readUTF();
        batchId = objectInput.readUTF();
        rebatePlanTierId = objectInput.readUTF();
        freeAmount = objectInput.readDouble();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        tierLevel = objectInput.readUTF();
        formulaNo = objectInput.readUTF();
        formulaName = objectInput.readUTF();
        secondaryRebatePlanNo = objectInput.readUTF();
        secondaryRebatePlanName = objectInput.readUTF();
        secondaryRebatePlanSid = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(tierValue);

        if (returnRateSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(returnRateSid);
        }

        objectOutput.writeInt(rebatePlanMasterSid);
        objectOutput.writeInt(rebatePlanTierSid);

        if (itemPricingQualifierSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemPricingQualifierSid);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeDouble(tierTolerance);

        if (tierFrom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tierFrom);
        }

        if (tierOperator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tierOperator);
        }

        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (tierTo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tierTo);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (rebatePlanTierId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebatePlanTierId);
        }

        objectOutput.writeDouble(freeAmount);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (tierLevel == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tierLevel);
        }

        if (formulaNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaNo);
        }

        if (formulaName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaName);
        }

        if (secondaryRebatePlanNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(secondaryRebatePlanNo);
        }

        if (secondaryRebatePlanName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(secondaryRebatePlanName);
        }

        if (secondaryRebatePlanSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(secondaryRebatePlanSid);
        }
    }
}
