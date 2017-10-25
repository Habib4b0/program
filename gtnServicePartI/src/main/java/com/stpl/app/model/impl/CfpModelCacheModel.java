package com.stpl.app.model.impl;

import com.stpl.app.model.CfpModel;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CfpModel in entity cache.
 *
 * @author
 * @see CfpModel
 * @generated
 */
public class CfpModelCacheModel implements CacheModel<CfpModel>, Externalizable {
    public int createdBy;
    public int cfpType;
    public int cfpTradeClass;
    public int modifiedBy;
    public long createdDate;
    public String cfpNo;
    public int cfpModelSid;
    public String batchId;
    public long modifiedDate;
    public boolean recordLockStatus;
    public String internalNotes;
    public String cfpDesignation;
    public int salesInclusion;
    public String cfpName;
    public int cfpCategory;
    public String source;
    public String cfpId;
    public int cfpStatus;
    public int parentCfpId;
    public long cfpStartDate;
    public long cfpEndDate;
    public String parentCfpName;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(47);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", cfpType=");
        sb.append(cfpType);
        sb.append(", cfpTradeClass=");
        sb.append(cfpTradeClass);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", cfpNo=");
        sb.append(cfpNo);
        sb.append(", cfpModelSid=");
        sb.append(cfpModelSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", internalNotes=");
        sb.append(internalNotes);
        sb.append(", cfpDesignation=");
        sb.append(cfpDesignation);
        sb.append(", salesInclusion=");
        sb.append(salesInclusion);
        sb.append(", cfpName=");
        sb.append(cfpName);
        sb.append(", cfpCategory=");
        sb.append(cfpCategory);
        sb.append(", source=");
        sb.append(source);
        sb.append(", cfpId=");
        sb.append(cfpId);
        sb.append(", cfpStatus=");
        sb.append(cfpStatus);
        sb.append(", parentCfpId=");
        sb.append(parentCfpId);
        sb.append(", cfpStartDate=");
        sb.append(cfpStartDate);
        sb.append(", cfpEndDate=");
        sb.append(cfpEndDate);
        sb.append(", parentCfpName=");
        sb.append(parentCfpName);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CfpModel toEntityModel() {
        CfpModelImpl cfpModelImpl = new CfpModelImpl();

        cfpModelImpl.setCreatedBy(createdBy);
        cfpModelImpl.setCfpType(cfpType);
        cfpModelImpl.setCfpTradeClass(cfpTradeClass);
        cfpModelImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            cfpModelImpl.setCreatedDate(null);
        } else {
            cfpModelImpl.setCreatedDate(new Date(createdDate));
        }

        if (cfpNo == null) {
            cfpModelImpl.setCfpNo(StringPool.BLANK);
        } else {
            cfpModelImpl.setCfpNo(cfpNo);
        }

        cfpModelImpl.setCfpModelSid(cfpModelSid);

        if (batchId == null) {
            cfpModelImpl.setBatchId(StringPool.BLANK);
        } else {
            cfpModelImpl.setBatchId(batchId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            cfpModelImpl.setModifiedDate(null);
        } else {
            cfpModelImpl.setModifiedDate(new Date(modifiedDate));
        }

        cfpModelImpl.setRecordLockStatus(recordLockStatus);

        if (internalNotes == null) {
            cfpModelImpl.setInternalNotes(StringPool.BLANK);
        } else {
            cfpModelImpl.setInternalNotes(internalNotes);
        }

        if (cfpDesignation == null) {
            cfpModelImpl.setCfpDesignation(StringPool.BLANK);
        } else {
            cfpModelImpl.setCfpDesignation(cfpDesignation);
        }

        cfpModelImpl.setSalesInclusion(salesInclusion);

        if (cfpName == null) {
            cfpModelImpl.setCfpName(StringPool.BLANK);
        } else {
            cfpModelImpl.setCfpName(cfpName);
        }

        cfpModelImpl.setCfpCategory(cfpCategory);

        if (source == null) {
            cfpModelImpl.setSource(StringPool.BLANK);
        } else {
            cfpModelImpl.setSource(source);
        }

        if (cfpId == null) {
            cfpModelImpl.setCfpId(StringPool.BLANK);
        } else {
            cfpModelImpl.setCfpId(cfpId);
        }

        cfpModelImpl.setCfpStatus(cfpStatus);
        cfpModelImpl.setParentCfpId(parentCfpId);

        if (cfpStartDate == Long.MIN_VALUE) {
            cfpModelImpl.setCfpStartDate(null);
        } else {
            cfpModelImpl.setCfpStartDate(new Date(cfpStartDate));
        }

        if (cfpEndDate == Long.MIN_VALUE) {
            cfpModelImpl.setCfpEndDate(null);
        } else {
            cfpModelImpl.setCfpEndDate(new Date(cfpEndDate));
        }

        if (parentCfpName == null) {
            cfpModelImpl.setParentCfpName(StringPool.BLANK);
        } else {
            cfpModelImpl.setParentCfpName(parentCfpName);
        }

        if (inboundStatus == null) {
            cfpModelImpl.setInboundStatus(StringPool.BLANK);
        } else {
            cfpModelImpl.setInboundStatus(inboundStatus);
        }

        cfpModelImpl.resetOriginalValues();

        return cfpModelImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        cfpType = objectInput.readInt();
        cfpTradeClass = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        cfpNo = objectInput.readUTF();
        cfpModelSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        internalNotes = objectInput.readUTF();
        cfpDesignation = objectInput.readUTF();
        salesInclusion = objectInput.readInt();
        cfpName = objectInput.readUTF();
        cfpCategory = objectInput.readInt();
        source = objectInput.readUTF();
        cfpId = objectInput.readUTF();
        cfpStatus = objectInput.readInt();
        parentCfpId = objectInput.readInt();
        cfpStartDate = objectInput.readLong();
        cfpEndDate = objectInput.readLong();
        parentCfpName = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(cfpType);
        objectOutput.writeInt(cfpTradeClass);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);

        if (cfpNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cfpNo);
        }

        objectOutput.writeInt(cfpModelSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(recordLockStatus);

        if (internalNotes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(internalNotes);
        }

        if (cfpDesignation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cfpDesignation);
        }

        objectOutput.writeInt(salesInclusion);

        if (cfpName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cfpName);
        }

        objectOutput.writeInt(cfpCategory);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (cfpId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cfpId);
        }

        objectOutput.writeInt(cfpStatus);
        objectOutput.writeInt(parentCfpId);
        objectOutput.writeLong(cfpStartDate);
        objectOutput.writeLong(cfpEndDate);

        if (parentCfpName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentCfpName);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
