package com.stpl.app.model.impl;

import com.stpl.app.model.PsContract;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PsContract in entity cache.
 *
 * @author
 * @see PsContract
 * @generated
 */
public class PsContractCacheModel implements CacheModel<PsContract>,
    Externalizable {
    public String psName;
    public String psNo;
    public String cfpContractSid;
    public int psContractSid;
    public int psType;
    public int psContractAttachedStatus;
    public long modifiedDate;
    public int psCategory;
    public boolean recordLockStatus;
    public int psStatus;
    public long createdDate;
    public int createdBy;
    public String source;
    public String parentPsId;
    public String psDesignation;
    public String batchId;
    public int contractMasterSid;
    public int psModelSid;
    public long psContractAttachedDate;
    public long psEndDate;
    public int modifiedBy;
    public String inboundStatus;
    public String parentPsName;
    public long psStartDate;
    public String ifpContractSid;
    public int psTradeClass;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(53);

        sb.append("{psName=");
        sb.append(psName);
        sb.append(", psNo=");
        sb.append(psNo);
        sb.append(", cfpContractSid=");
        sb.append(cfpContractSid);
        sb.append(", psContractSid=");
        sb.append(psContractSid);
        sb.append(", psType=");
        sb.append(psType);
        sb.append(", psContractAttachedStatus=");
        sb.append(psContractAttachedStatus);
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
        sb.append(", parentPsId=");
        sb.append(parentPsId);
        sb.append(", psDesignation=");
        sb.append(psDesignation);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", psModelSid=");
        sb.append(psModelSid);
        sb.append(", psContractAttachedDate=");
        sb.append(psContractAttachedDate);
        sb.append(", psEndDate=");
        sb.append(psEndDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", parentPsName=");
        sb.append(parentPsName);
        sb.append(", psStartDate=");
        sb.append(psStartDate);
        sb.append(", ifpContractSid=");
        sb.append(ifpContractSid);
        sb.append(", psTradeClass=");
        sb.append(psTradeClass);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PsContract toEntityModel() {
        PsContractImpl psContractImpl = new PsContractImpl();

        if (psName == null) {
            psContractImpl.setPsName(StringPool.BLANK);
        } else {
            psContractImpl.setPsName(psName);
        }

        if (psNo == null) {
            psContractImpl.setPsNo(StringPool.BLANK);
        } else {
            psContractImpl.setPsNo(psNo);
        }

        if (cfpContractSid == null) {
            psContractImpl.setCfpContractSid(StringPool.BLANK);
        } else {
            psContractImpl.setCfpContractSid(cfpContractSid);
        }

        psContractImpl.setPsContractSid(psContractSid);
        psContractImpl.setPsType(psType);
        psContractImpl.setPsContractAttachedStatus(psContractAttachedStatus);

        if (modifiedDate == Long.MIN_VALUE) {
            psContractImpl.setModifiedDate(null);
        } else {
            psContractImpl.setModifiedDate(new Date(modifiedDate));
        }

        psContractImpl.setPsCategory(psCategory);
        psContractImpl.setRecordLockStatus(recordLockStatus);
        psContractImpl.setPsStatus(psStatus);

        if (createdDate == Long.MIN_VALUE) {
            psContractImpl.setCreatedDate(null);
        } else {
            psContractImpl.setCreatedDate(new Date(createdDate));
        }

        psContractImpl.setCreatedBy(createdBy);

        if (source == null) {
            psContractImpl.setSource(StringPool.BLANK);
        } else {
            psContractImpl.setSource(source);
        }

        if (parentPsId == null) {
            psContractImpl.setParentPsId(StringPool.BLANK);
        } else {
            psContractImpl.setParentPsId(parentPsId);
        }

        if (psDesignation == null) {
            psContractImpl.setPsDesignation(StringPool.BLANK);
        } else {
            psContractImpl.setPsDesignation(psDesignation);
        }

        if (batchId == null) {
            psContractImpl.setBatchId(StringPool.BLANK);
        } else {
            psContractImpl.setBatchId(batchId);
        }

        psContractImpl.setContractMasterSid(contractMasterSid);
        psContractImpl.setPsModelSid(psModelSid);

        if (psContractAttachedDate == Long.MIN_VALUE) {
            psContractImpl.setPsContractAttachedDate(null);
        } else {
            psContractImpl.setPsContractAttachedDate(new Date(
                    psContractAttachedDate));
        }

        if (psEndDate == Long.MIN_VALUE) {
            psContractImpl.setPsEndDate(null);
        } else {
            psContractImpl.setPsEndDate(new Date(psEndDate));
        }

        psContractImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            psContractImpl.setInboundStatus(StringPool.BLANK);
        } else {
            psContractImpl.setInboundStatus(inboundStatus);
        }

        if (parentPsName == null) {
            psContractImpl.setParentPsName(StringPool.BLANK);
        } else {
            psContractImpl.setParentPsName(parentPsName);
        }

        if (psStartDate == Long.MIN_VALUE) {
            psContractImpl.setPsStartDate(null);
        } else {
            psContractImpl.setPsStartDate(new Date(psStartDate));
        }

        if (ifpContractSid == null) {
            psContractImpl.setIfpContractSid(StringPool.BLANK);
        } else {
            psContractImpl.setIfpContractSid(ifpContractSid);
        }

        psContractImpl.setPsTradeClass(psTradeClass);

        psContractImpl.resetOriginalValues();

        return psContractImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        psName = objectInput.readUTF();
        psNo = objectInput.readUTF();
        cfpContractSid = objectInput.readUTF();
        psContractSid = objectInput.readInt();
        psType = objectInput.readInt();
        psContractAttachedStatus = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        psCategory = objectInput.readInt();
        recordLockStatus = objectInput.readBoolean();
        psStatus = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        source = objectInput.readUTF();
        parentPsId = objectInput.readUTF();
        psDesignation = objectInput.readUTF();
        batchId = objectInput.readUTF();
        contractMasterSid = objectInput.readInt();
        psModelSid = objectInput.readInt();
        psContractAttachedDate = objectInput.readLong();
        psEndDate = objectInput.readLong();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        parentPsName = objectInput.readUTF();
        psStartDate = objectInput.readLong();
        ifpContractSid = objectInput.readUTF();
        psTradeClass = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (psName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(psName);
        }

        if (psNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(psNo);
        }

        if (cfpContractSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cfpContractSid);
        }

        objectOutput.writeInt(psContractSid);
        objectOutput.writeInt(psType);
        objectOutput.writeInt(psContractAttachedStatus);
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

        if (parentPsId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentPsId);
        }

        if (psDesignation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(psDesignation);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(contractMasterSid);
        objectOutput.writeInt(psModelSid);
        objectOutput.writeLong(psContractAttachedDate);
        objectOutput.writeLong(psEndDate);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (parentPsName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentPsName);
        }

        objectOutput.writeLong(psStartDate);

        if (ifpContractSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ifpContractSid);
        }

        objectOutput.writeInt(psTradeClass);
    }
}
