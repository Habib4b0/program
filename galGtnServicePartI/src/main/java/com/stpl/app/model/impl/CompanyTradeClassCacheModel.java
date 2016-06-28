package com.stpl.app.model.impl;

import com.stpl.app.model.CompanyTradeClass;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyTradeClass in entity cache.
 *
 * @author
 * @see CompanyTradeClass
 * @generated
 */
public class CompanyTradeClassCacheModel implements CacheModel<CompanyTradeClass>,
    Externalizable {
    public int priorTradeClass;
    public int companyTradeClassSid;
    public long lastUpdatedDate;
    public long priorTradeClassStartDate;
    public long modifiedDate;
    public long tradeClassEndDate;
    public long tradeClassStartDate;
    public boolean recordLockStatus;
    public long createdDate;
    public String source;
    public int createdBy;
    public String batchId;
    public int companyTradeClass;
    public int modifiedBy;
    public String inboundStatus;
    public int companyMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(33);

        sb.append("{priorTradeClass=");
        sb.append(priorTradeClass);
        sb.append(", companyTradeClassSid=");
        sb.append(companyTradeClassSid);
        sb.append(", lastUpdatedDate=");
        sb.append(lastUpdatedDate);
        sb.append(", priorTradeClassStartDate=");
        sb.append(priorTradeClassStartDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", tradeClassEndDate=");
        sb.append(tradeClassEndDate);
        sb.append(", tradeClassStartDate=");
        sb.append(tradeClassStartDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", companyTradeClass=");
        sb.append(companyTradeClass);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CompanyTradeClass toEntityModel() {
        CompanyTradeClassImpl companyTradeClassImpl = new CompanyTradeClassImpl();

        companyTradeClassImpl.setPriorTradeClass(priorTradeClass);
        companyTradeClassImpl.setCompanyTradeClassSid(companyTradeClassSid);

        if (lastUpdatedDate == Long.MIN_VALUE) {
            companyTradeClassImpl.setLastUpdatedDate(null);
        } else {
            companyTradeClassImpl.setLastUpdatedDate(new Date(lastUpdatedDate));
        }

        if (priorTradeClassStartDate == Long.MIN_VALUE) {
            companyTradeClassImpl.setPriorTradeClassStartDate(null);
        } else {
            companyTradeClassImpl.setPriorTradeClassStartDate(new Date(
                    priorTradeClassStartDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            companyTradeClassImpl.setModifiedDate(null);
        } else {
            companyTradeClassImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (tradeClassEndDate == Long.MIN_VALUE) {
            companyTradeClassImpl.setTradeClassEndDate(null);
        } else {
            companyTradeClassImpl.setTradeClassEndDate(new Date(
                    tradeClassEndDate));
        }

        if (tradeClassStartDate == Long.MIN_VALUE) {
            companyTradeClassImpl.setTradeClassStartDate(null);
        } else {
            companyTradeClassImpl.setTradeClassStartDate(new Date(
                    tradeClassStartDate));
        }

        companyTradeClassImpl.setRecordLockStatus(recordLockStatus);

        if (createdDate == Long.MIN_VALUE) {
            companyTradeClassImpl.setCreatedDate(null);
        } else {
            companyTradeClassImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            companyTradeClassImpl.setSource(StringPool.BLANK);
        } else {
            companyTradeClassImpl.setSource(source);
        }

        companyTradeClassImpl.setCreatedBy(createdBy);

        if (batchId == null) {
            companyTradeClassImpl.setBatchId(StringPool.BLANK);
        } else {
            companyTradeClassImpl.setBatchId(batchId);
        }

        companyTradeClassImpl.setCompanyTradeClass(companyTradeClass);
        companyTradeClassImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            companyTradeClassImpl.setInboundStatus(StringPool.BLANK);
        } else {
            companyTradeClassImpl.setInboundStatus(inboundStatus);
        }

        companyTradeClassImpl.setCompanyMasterSid(companyMasterSid);

        companyTradeClassImpl.resetOriginalValues();

        return companyTradeClassImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        priorTradeClass = objectInput.readInt();
        companyTradeClassSid = objectInput.readInt();
        lastUpdatedDate = objectInput.readLong();
        priorTradeClassStartDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        tradeClassEndDate = objectInput.readLong();
        tradeClassStartDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdBy = objectInput.readInt();
        batchId = objectInput.readUTF();
        companyTradeClass = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        companyMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(priorTradeClass);
        objectOutput.writeInt(companyTradeClassSid);
        objectOutput.writeLong(lastUpdatedDate);
        objectOutput.writeLong(priorTradeClassStartDate);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeLong(tradeClassEndDate);
        objectOutput.writeLong(tradeClassStartDate);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(createdDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(createdBy);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(companyTradeClass);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(companyMasterSid);
    }
}
