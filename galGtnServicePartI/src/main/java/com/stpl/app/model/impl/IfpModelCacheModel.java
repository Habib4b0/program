package com.stpl.app.model.impl;

import com.stpl.app.model.IfpModel;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IfpModel in entity cache.
 *
 * @author
 * @see IfpModel
 * @generated
 */
public class IfpModelCacheModel implements CacheModel<IfpModel>, Externalizable {
    public int modifiedBy;
    public String totalDollarCommitment;
    public long createdDate;
    public int ifpStatus;
    public String totalVolumeCommitment;
    public String batchId;
    public String internalNotes;
    public String ifpId;
    public String totalMarketshareCommitment;
    public int ifpCategory;
    public String parentIfpName;
    public long ifpEndDate;
    public String ifpDesignation;
    public int createdBy;
    public long ifpStartDate;
    public String parentIfpId;
    public String commitmentPeriod;
    public int ifpType;
    public long modifiedDate;
    public int ifpModelSid;
    public boolean recordLockStatus;
    public String source;
    public String ifpName;
    public String ifpNo;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(51);

        sb.append("{modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", totalDollarCommitment=");
        sb.append(totalDollarCommitment);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", ifpStatus=");
        sb.append(ifpStatus);
        sb.append(", totalVolumeCommitment=");
        sb.append(totalVolumeCommitment);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", internalNotes=");
        sb.append(internalNotes);
        sb.append(", ifpId=");
        sb.append(ifpId);
        sb.append(", totalMarketshareCommitment=");
        sb.append(totalMarketshareCommitment);
        sb.append(", ifpCategory=");
        sb.append(ifpCategory);
        sb.append(", parentIfpName=");
        sb.append(parentIfpName);
        sb.append(", ifpEndDate=");
        sb.append(ifpEndDate);
        sb.append(", ifpDesignation=");
        sb.append(ifpDesignation);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", ifpStartDate=");
        sb.append(ifpStartDate);
        sb.append(", parentIfpId=");
        sb.append(parentIfpId);
        sb.append(", commitmentPeriod=");
        sb.append(commitmentPeriod);
        sb.append(", ifpType=");
        sb.append(ifpType);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", ifpModelSid=");
        sb.append(ifpModelSid);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", source=");
        sb.append(source);
        sb.append(", ifpName=");
        sb.append(ifpName);
        sb.append(", ifpNo=");
        sb.append(ifpNo);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IfpModel toEntityModel() {
        IfpModelImpl ifpModelImpl = new IfpModelImpl();

        ifpModelImpl.setModifiedBy(modifiedBy);

        if (totalDollarCommitment == null) {
            ifpModelImpl.setTotalDollarCommitment(StringPool.BLANK);
        } else {
            ifpModelImpl.setTotalDollarCommitment(totalDollarCommitment);
        }

        if (createdDate == Long.MIN_VALUE) {
            ifpModelImpl.setCreatedDate(null);
        } else {
            ifpModelImpl.setCreatedDate(new Date(createdDate));
        }

        ifpModelImpl.setIfpStatus(ifpStatus);

        if (totalVolumeCommitment == null) {
            ifpModelImpl.setTotalVolumeCommitment(StringPool.BLANK);
        } else {
            ifpModelImpl.setTotalVolumeCommitment(totalVolumeCommitment);
        }

        if (batchId == null) {
            ifpModelImpl.setBatchId(StringPool.BLANK);
        } else {
            ifpModelImpl.setBatchId(batchId);
        }

        if (internalNotes == null) {
            ifpModelImpl.setInternalNotes(StringPool.BLANK);
        } else {
            ifpModelImpl.setInternalNotes(internalNotes);
        }

        if (ifpId == null) {
            ifpModelImpl.setIfpId(StringPool.BLANK);
        } else {
            ifpModelImpl.setIfpId(ifpId);
        }

        if (totalMarketshareCommitment == null) {
            ifpModelImpl.setTotalMarketshareCommitment(StringPool.BLANK);
        } else {
            ifpModelImpl.setTotalMarketshareCommitment(totalMarketshareCommitment);
        }

        ifpModelImpl.setIfpCategory(ifpCategory);

        if (parentIfpName == null) {
            ifpModelImpl.setParentIfpName(StringPool.BLANK);
        } else {
            ifpModelImpl.setParentIfpName(parentIfpName);
        }

        if (ifpEndDate == Long.MIN_VALUE) {
            ifpModelImpl.setIfpEndDate(null);
        } else {
            ifpModelImpl.setIfpEndDate(new Date(ifpEndDate));
        }

        if (ifpDesignation == null) {
            ifpModelImpl.setIfpDesignation(StringPool.BLANK);
        } else {
            ifpModelImpl.setIfpDesignation(ifpDesignation);
        }

        ifpModelImpl.setCreatedBy(createdBy);

        if (ifpStartDate == Long.MIN_VALUE) {
            ifpModelImpl.setIfpStartDate(null);
        } else {
            ifpModelImpl.setIfpStartDate(new Date(ifpStartDate));
        }

        if (parentIfpId == null) {
            ifpModelImpl.setParentIfpId(StringPool.BLANK);
        } else {
            ifpModelImpl.setParentIfpId(parentIfpId);
        }

        if (commitmentPeriod == null) {
            ifpModelImpl.setCommitmentPeriod(StringPool.BLANK);
        } else {
            ifpModelImpl.setCommitmentPeriod(commitmentPeriod);
        }

        ifpModelImpl.setIfpType(ifpType);

        if (modifiedDate == Long.MIN_VALUE) {
            ifpModelImpl.setModifiedDate(null);
        } else {
            ifpModelImpl.setModifiedDate(new Date(modifiedDate));
        }

        ifpModelImpl.setIfpModelSid(ifpModelSid);
        ifpModelImpl.setRecordLockStatus(recordLockStatus);

        if (source == null) {
            ifpModelImpl.setSource(StringPool.BLANK);
        } else {
            ifpModelImpl.setSource(source);
        }

        if (ifpName == null) {
            ifpModelImpl.setIfpName(StringPool.BLANK);
        } else {
            ifpModelImpl.setIfpName(ifpName);
        }

        if (ifpNo == null) {
            ifpModelImpl.setIfpNo(StringPool.BLANK);
        } else {
            ifpModelImpl.setIfpNo(ifpNo);
        }

        if (inboundStatus == null) {
            ifpModelImpl.setInboundStatus(StringPool.BLANK);
        } else {
            ifpModelImpl.setInboundStatus(inboundStatus);
        }

        ifpModelImpl.resetOriginalValues();

        return ifpModelImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        modifiedBy = objectInput.readInt();
        totalDollarCommitment = objectInput.readUTF();
        createdDate = objectInput.readLong();
        ifpStatus = objectInput.readInt();
        totalVolumeCommitment = objectInput.readUTF();
        batchId = objectInput.readUTF();
        internalNotes = objectInput.readUTF();
        ifpId = objectInput.readUTF();
        totalMarketshareCommitment = objectInput.readUTF();
        ifpCategory = objectInput.readInt();
        parentIfpName = objectInput.readUTF();
        ifpEndDate = objectInput.readLong();
        ifpDesignation = objectInput.readUTF();
        createdBy = objectInput.readInt();
        ifpStartDate = objectInput.readLong();
        parentIfpId = objectInput.readUTF();
        commitmentPeriod = objectInput.readUTF();
        ifpType = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        ifpModelSid = objectInput.readInt();
        recordLockStatus = objectInput.readBoolean();
        source = objectInput.readUTF();
        ifpName = objectInput.readUTF();
        ifpNo = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(modifiedBy);

        if (totalDollarCommitment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(totalDollarCommitment);
        }

        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(ifpStatus);

        if (totalVolumeCommitment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(totalVolumeCommitment);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (internalNotes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(internalNotes);
        }

        if (ifpId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ifpId);
        }

        if (totalMarketshareCommitment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(totalMarketshareCommitment);
        }

        objectOutput.writeInt(ifpCategory);

        if (parentIfpName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentIfpName);
        }

        objectOutput.writeLong(ifpEndDate);

        if (ifpDesignation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ifpDesignation);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(ifpStartDate);

        if (parentIfpId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentIfpId);
        }

        if (commitmentPeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(commitmentPeriod);
        }

        objectOutput.writeInt(ifpType);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(ifpModelSid);
        objectOutput.writeBoolean(recordLockStatus);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (ifpName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ifpName);
        }

        if (ifpNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ifpNo);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
