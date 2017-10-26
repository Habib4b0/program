package com.stpl.app.model.impl;

import com.stpl.app.model.CfpDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CfpDetails in entity cache.
 *
 * @author
 * @see CfpDetails
 * @generated
 */
public class CfpDetailsCacheModel implements CacheModel<CfpDetails>,
    Externalizable {
    public int createdBy;
    public int companyCfpAttachedStatus;
    public int tradeClass;
    public long tradeClassEndDate;
    public int modifiedBy;
    public long companyStartDate;
    public long tradeClassStartDate;
    public long createdDate;
    public long companyEndDate;
    public int companyMasterSid;
    public long companyCfpAttachedDate;
    public int cfpModelSid;
    public String batchId;
    public int cfpDetailsSid;
    public long modifiedDate;
    public boolean recordLockStatus;
    public String source;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", companyCfpAttachedStatus=");
        sb.append(companyCfpAttachedStatus);
        sb.append(", tradeClass=");
        sb.append(tradeClass);
        sb.append(", tradeClassEndDate=");
        sb.append(tradeClassEndDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", companyStartDate=");
        sb.append(companyStartDate);
        sb.append(", tradeClassStartDate=");
        sb.append(tradeClassStartDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", companyEndDate=");
        sb.append(companyEndDate);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append(", companyCfpAttachedDate=");
        sb.append(companyCfpAttachedDate);
        sb.append(", cfpModelSid=");
        sb.append(cfpModelSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", cfpDetailsSid=");
        sb.append(cfpDetailsSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", source=");
        sb.append(source);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CfpDetails toEntityModel() {
        CfpDetailsImpl cfpDetailsImpl = new CfpDetailsImpl();

        cfpDetailsImpl.setCreatedBy(createdBy);
        cfpDetailsImpl.setCompanyCfpAttachedStatus(companyCfpAttachedStatus);
        cfpDetailsImpl.setTradeClass(tradeClass);

        if (tradeClassEndDate == Long.MIN_VALUE) {
            cfpDetailsImpl.setTradeClassEndDate(null);
        } else {
            cfpDetailsImpl.setTradeClassEndDate(new Date(tradeClassEndDate));
        }

        cfpDetailsImpl.setModifiedBy(modifiedBy);

        if (companyStartDate == Long.MIN_VALUE) {
            cfpDetailsImpl.setCompanyStartDate(null);
        } else {
            cfpDetailsImpl.setCompanyStartDate(new Date(companyStartDate));
        }

        if (tradeClassStartDate == Long.MIN_VALUE) {
            cfpDetailsImpl.setTradeClassStartDate(null);
        } else {
            cfpDetailsImpl.setTradeClassStartDate(new Date(tradeClassStartDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            cfpDetailsImpl.setCreatedDate(null);
        } else {
            cfpDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        if (companyEndDate == Long.MIN_VALUE) {
            cfpDetailsImpl.setCompanyEndDate(null);
        } else {
            cfpDetailsImpl.setCompanyEndDate(new Date(companyEndDate));
        }

        cfpDetailsImpl.setCompanyMasterSid(companyMasterSid);

        if (companyCfpAttachedDate == Long.MIN_VALUE) {
            cfpDetailsImpl.setCompanyCfpAttachedDate(null);
        } else {
            cfpDetailsImpl.setCompanyCfpAttachedDate(new Date(
                    companyCfpAttachedDate));
        }

        cfpDetailsImpl.setCfpModelSid(cfpModelSid);

        if (batchId == null) {
            cfpDetailsImpl.setBatchId(StringPool.BLANK);
        } else {
            cfpDetailsImpl.setBatchId(batchId);
        }

        cfpDetailsImpl.setCfpDetailsSid(cfpDetailsSid);

        if (modifiedDate == Long.MIN_VALUE) {
            cfpDetailsImpl.setModifiedDate(null);
        } else {
            cfpDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        cfpDetailsImpl.setRecordLockStatus(recordLockStatus);

        if (source == null) {
            cfpDetailsImpl.setSource(StringPool.BLANK);
        } else {
            cfpDetailsImpl.setSource(source);
        }

        if (inboundStatus == null) {
            cfpDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            cfpDetailsImpl.setInboundStatus(inboundStatus);
        }

        cfpDetailsImpl.resetOriginalValues();

        return cfpDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        companyCfpAttachedStatus = objectInput.readInt();
        tradeClass = objectInput.readInt();
        tradeClassEndDate = objectInput.readLong();
        modifiedBy = objectInput.readInt();
        companyStartDate = objectInput.readLong();
        tradeClassStartDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        companyEndDate = objectInput.readLong();
        companyMasterSid = objectInput.readInt();
        companyCfpAttachedDate = objectInput.readLong();
        cfpModelSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        cfpDetailsSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        source = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(companyCfpAttachedStatus);
        objectOutput.writeInt(tradeClass);
        objectOutput.writeLong(tradeClassEndDate);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(companyStartDate);
        objectOutput.writeLong(tradeClassStartDate);
        objectOutput.writeLong(createdDate);
        objectOutput.writeLong(companyEndDate);
        objectOutput.writeInt(companyMasterSid);
        objectOutput.writeLong(companyCfpAttachedDate);
        objectOutput.writeInt(cfpModelSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(cfpDetailsSid);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(recordLockStatus);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
