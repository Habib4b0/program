package com.stpl.app.model.impl;

import com.stpl.app.model.PsModel;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PsModel in entity cache.
 *
 * @author
 * @see PsModel
 * @generated
 */
public class PsModelCacheModel implements CacheModel<PsModel>, Externalizable {
    public String psId;
    public String psName;
    public int psType;
    public long modifiedDate;
    public int psCategory;
    public boolean recordLockStatus;
    public int psStatus;
    public long createdDate;
    public int createdBy;
    public String source;
    public String psNo;
    public String psDesignation;
    public String parentPsId;
    public String batchId;
    public int psModelSid;
    public long psEndDate;
    public int psTradeClass;
    public int modifiedBy;
    public String inboundStatus;
    public long psStartDate;
    public String parentPsName;
    public String internalNotes;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(45);

        sb.append("{psId=");
        sb.append(psId);
        sb.append(", psName=");
        sb.append(psName);
        sb.append(", psType=");
        sb.append(psType);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", psCategory=");
        sb.append(psCategory);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", psStatus=");
        sb.append(psStatus);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", psNo=");
        sb.append(psNo);
        sb.append(", psDesignation=");
        sb.append(psDesignation);
        sb.append(", parentPsId=");
        sb.append(parentPsId);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", psModelSid=");
        sb.append(psModelSid);
        sb.append(", psEndDate=");
        sb.append(psEndDate);
        sb.append(", psTradeClass=");
        sb.append(psTradeClass);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", psStartDate=");
        sb.append(psStartDate);
        sb.append(", parentPsName=");
        sb.append(parentPsName);
        sb.append(", internalNotes=");
        sb.append(internalNotes);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PsModel toEntityModel() {
        PsModelImpl psModelImpl = new PsModelImpl();

        if (psId == null) {
            psModelImpl.setPsId(StringPool.BLANK);
        } else {
            psModelImpl.setPsId(psId);
        }

        if (psName == null) {
            psModelImpl.setPsName(StringPool.BLANK);
        } else {
            psModelImpl.setPsName(psName);
        }

        psModelImpl.setPsType(psType);

        if (modifiedDate == Long.MIN_VALUE) {
            psModelImpl.setModifiedDate(null);
        } else {
            psModelImpl.setModifiedDate(new Date(modifiedDate));
        }

        psModelImpl.setPsCategory(psCategory);
        psModelImpl.setRecordLockStatus(recordLockStatus);
        psModelImpl.setPsStatus(psStatus);

        if (createdDate == Long.MIN_VALUE) {
            psModelImpl.setCreatedDate(null);
        } else {
            psModelImpl.setCreatedDate(new Date(createdDate));
        }

        psModelImpl.setCreatedBy(createdBy);

        if (source == null) {
            psModelImpl.setSource(StringPool.BLANK);
        } else {
            psModelImpl.setSource(source);
        }

        if (psNo == null) {
            psModelImpl.setPsNo(StringPool.BLANK);
        } else {
            psModelImpl.setPsNo(psNo);
        }

        if (psDesignation == null) {
            psModelImpl.setPsDesignation(StringPool.BLANK);
        } else {
            psModelImpl.setPsDesignation(psDesignation);
        }

        if (parentPsId == null) {
            psModelImpl.setParentPsId(StringPool.BLANK);
        } else {
            psModelImpl.setParentPsId(parentPsId);
        }

        if (batchId == null) {
            psModelImpl.setBatchId(StringPool.BLANK);
        } else {
            psModelImpl.setBatchId(batchId);
        }

        psModelImpl.setPsModelSid(psModelSid);

        if (psEndDate == Long.MIN_VALUE) {
            psModelImpl.setPsEndDate(null);
        } else {
            psModelImpl.setPsEndDate(new Date(psEndDate));
        }

        psModelImpl.setPsTradeClass(psTradeClass);
        psModelImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            psModelImpl.setInboundStatus(StringPool.BLANK);
        } else {
            psModelImpl.setInboundStatus(inboundStatus);
        }

        if (psStartDate == Long.MIN_VALUE) {
            psModelImpl.setPsStartDate(null);
        } else {
            psModelImpl.setPsStartDate(new Date(psStartDate));
        }

        if (parentPsName == null) {
            psModelImpl.setParentPsName(StringPool.BLANK);
        } else {
            psModelImpl.setParentPsName(parentPsName);
        }

        if (internalNotes == null) {
            psModelImpl.setInternalNotes(StringPool.BLANK);
        } else {
            psModelImpl.setInternalNotes(internalNotes);
        }

        psModelImpl.resetOriginalValues();

        return psModelImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        psId = objectInput.readUTF();
        psName = objectInput.readUTF();
        psType = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        psCategory = objectInput.readInt();
        recordLockStatus = objectInput.readBoolean();
        psStatus = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        source = objectInput.readUTF();
        psNo = objectInput.readUTF();
        psDesignation = objectInput.readUTF();
        parentPsId = objectInput.readUTF();
        batchId = objectInput.readUTF();
        psModelSid = objectInput.readInt();
        psEndDate = objectInput.readLong();
        psTradeClass = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        psStartDate = objectInput.readLong();
        parentPsName = objectInput.readUTF();
        internalNotes = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (psId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(psId);
        }

        if (psName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(psName);
        }

        objectOutput.writeInt(psType);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(psCategory);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeInt(psStatus);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (psNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(psNo);
        }

        if (psDesignation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(psDesignation);
        }

        if (parentPsId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentPsId);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(psModelSid);
        objectOutput.writeLong(psEndDate);
        objectOutput.writeInt(psTradeClass);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeLong(psStartDate);

        if (parentPsName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentPsName);
        }

        if (internalNotes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(internalNotes);
        }
    }
}
