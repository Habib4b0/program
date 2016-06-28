package com.stpl.app.model.impl;

import com.stpl.app.model.CfpContractDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CfpContractDetails in entity cache.
 *
 * @author
 * @see CfpContractDetails
 * @generated
 */
public class CfpContractDetailsCacheModel implements CacheModel<CfpContractDetails>,
    Externalizable {
    public int createdBy;
    public int tradeClass;
    public long tradeClassEndDate;
    public int cfpContractSid;
    public int modifiedBy;
    public long companyStartDate;
    public long tradeClassStartDate;
    public long createdDate;
    public long cfpContractAttachedDate;
    public long companyEndDate;
    public int companyMasterSid;
    public String batchId;
    public long modifiedDate;
    public boolean recordLockStatus;
    public String source;
    public int cfpContractDetailsSid;
    public int cfpContractAttachedStatus;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", tradeClass=");
        sb.append(tradeClass);
        sb.append(", tradeClassEndDate=");
        sb.append(tradeClassEndDate);
        sb.append(", cfpContractSid=");
        sb.append(cfpContractSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", companyStartDate=");
        sb.append(companyStartDate);
        sb.append(", tradeClassStartDate=");
        sb.append(tradeClassStartDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", cfpContractAttachedDate=");
        sb.append(cfpContractAttachedDate);
        sb.append(", companyEndDate=");
        sb.append(companyEndDate);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", source=");
        sb.append(source);
        sb.append(", cfpContractDetailsSid=");
        sb.append(cfpContractDetailsSid);
        sb.append(", cfpContractAttachedStatus=");
        sb.append(cfpContractAttachedStatus);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CfpContractDetails toEntityModel() {
        CfpContractDetailsImpl cfpContractDetailsImpl = new CfpContractDetailsImpl();

        cfpContractDetailsImpl.setCreatedBy(createdBy);
        cfpContractDetailsImpl.setTradeClass(tradeClass);

        if (tradeClassEndDate == Long.MIN_VALUE) {
            cfpContractDetailsImpl.setTradeClassEndDate(null);
        } else {
            cfpContractDetailsImpl.setTradeClassEndDate(new Date(
                    tradeClassEndDate));
        }

        cfpContractDetailsImpl.setCfpContractSid(cfpContractSid);
        cfpContractDetailsImpl.setModifiedBy(modifiedBy);

        if (companyStartDate == Long.MIN_VALUE) {
            cfpContractDetailsImpl.setCompanyStartDate(null);
        } else {
            cfpContractDetailsImpl.setCompanyStartDate(new Date(
                    companyStartDate));
        }

        if (tradeClassStartDate == Long.MIN_VALUE) {
            cfpContractDetailsImpl.setTradeClassStartDate(null);
        } else {
            cfpContractDetailsImpl.setTradeClassStartDate(new Date(
                    tradeClassStartDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            cfpContractDetailsImpl.setCreatedDate(null);
        } else {
            cfpContractDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        if (cfpContractAttachedDate == Long.MIN_VALUE) {
            cfpContractDetailsImpl.setCfpContractAttachedDate(null);
        } else {
            cfpContractDetailsImpl.setCfpContractAttachedDate(new Date(
                    cfpContractAttachedDate));
        }

        if (companyEndDate == Long.MIN_VALUE) {
            cfpContractDetailsImpl.setCompanyEndDate(null);
        } else {
            cfpContractDetailsImpl.setCompanyEndDate(new Date(companyEndDate));
        }

        cfpContractDetailsImpl.setCompanyMasterSid(companyMasterSid);

        if (batchId == null) {
            cfpContractDetailsImpl.setBatchId(StringPool.BLANK);
        } else {
            cfpContractDetailsImpl.setBatchId(batchId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            cfpContractDetailsImpl.setModifiedDate(null);
        } else {
            cfpContractDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        cfpContractDetailsImpl.setRecordLockStatus(recordLockStatus);

        if (source == null) {
            cfpContractDetailsImpl.setSource(StringPool.BLANK);
        } else {
            cfpContractDetailsImpl.setSource(source);
        }

        cfpContractDetailsImpl.setCfpContractDetailsSid(cfpContractDetailsSid);
        cfpContractDetailsImpl.setCfpContractAttachedStatus(cfpContractAttachedStatus);

        if (inboundStatus == null) {
            cfpContractDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            cfpContractDetailsImpl.setInboundStatus(inboundStatus);
        }

        cfpContractDetailsImpl.resetOriginalValues();

        return cfpContractDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        tradeClass = objectInput.readInt();
        tradeClassEndDate = objectInput.readLong();
        cfpContractSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        companyStartDate = objectInput.readLong();
        tradeClassStartDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        cfpContractAttachedDate = objectInput.readLong();
        companyEndDate = objectInput.readLong();
        companyMasterSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        source = objectInput.readUTF();
        cfpContractDetailsSid = objectInput.readInt();
        cfpContractAttachedStatus = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(tradeClass);
        objectOutput.writeLong(tradeClassEndDate);
        objectOutput.writeInt(cfpContractSid);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(companyStartDate);
        objectOutput.writeLong(tradeClassStartDate);
        objectOutput.writeLong(createdDate);
        objectOutput.writeLong(cfpContractAttachedDate);
        objectOutput.writeLong(companyEndDate);
        objectOutput.writeInt(companyMasterSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(recordLockStatus);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(cfpContractDetailsSid);
        objectOutput.writeInt(cfpContractAttachedStatus);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
