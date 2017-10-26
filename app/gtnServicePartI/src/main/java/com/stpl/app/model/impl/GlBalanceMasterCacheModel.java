package com.stpl.app.model.impl;

import com.stpl.app.model.GlBalanceMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GlBalanceMaster in entity cache.
 *
 * @author
 * @see GlBalanceMaster
 * @generated
 */
public class GlBalanceMasterCacheModel implements CacheModel<GlBalanceMaster>,
    Externalizable {
    public int createdBy;
    public int modifiedBy;
    public String accountId;
    public long uploadDate;
    public long createdDate;
    public int glBalanceMasterSid;
    public String isActive;
    public String batchId;
    public long modifiedDate;
    public String balance;
    public String closeIndicator;
    public boolean recordLockStatus;
    public String year;
    public String period;
    public String source;
    public long insertedDate;
    public String accountNo;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", accountId=");
        sb.append(accountId);
        sb.append(", uploadDate=");
        sb.append(uploadDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", glBalanceMasterSid=");
        sb.append(glBalanceMasterSid);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", balance=");
        sb.append(balance);
        sb.append(", closeIndicator=");
        sb.append(closeIndicator);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", year=");
        sb.append(year);
        sb.append(", period=");
        sb.append(period);
        sb.append(", source=");
        sb.append(source);
        sb.append(", insertedDate=");
        sb.append(insertedDate);
        sb.append(", accountNo=");
        sb.append(accountNo);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public GlBalanceMaster toEntityModel() {
        GlBalanceMasterImpl glBalanceMasterImpl = new GlBalanceMasterImpl();

        glBalanceMasterImpl.setCreatedBy(createdBy);
        glBalanceMasterImpl.setModifiedBy(modifiedBy);

        if (accountId == null) {
            glBalanceMasterImpl.setAccountId(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setAccountId(accountId);
        }

        if (uploadDate == Long.MIN_VALUE) {
            glBalanceMasterImpl.setUploadDate(null);
        } else {
            glBalanceMasterImpl.setUploadDate(new Date(uploadDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            glBalanceMasterImpl.setCreatedDate(null);
        } else {
            glBalanceMasterImpl.setCreatedDate(new Date(createdDate));
        }

        glBalanceMasterImpl.setGlBalanceMasterSid(glBalanceMasterSid);

        if (isActive == null) {
            glBalanceMasterImpl.setIsActive(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setIsActive(isActive);
        }

        if (batchId == null) {
            glBalanceMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setBatchId(batchId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            glBalanceMasterImpl.setModifiedDate(null);
        } else {
            glBalanceMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (balance == null) {
            glBalanceMasterImpl.setBalance(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setBalance(balance);
        }

        if (closeIndicator == null) {
            glBalanceMasterImpl.setCloseIndicator(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setCloseIndicator(closeIndicator);
        }

        glBalanceMasterImpl.setRecordLockStatus(recordLockStatus);

        if (year == null) {
            glBalanceMasterImpl.setYear(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setYear(year);
        }

        if (period == null) {
            glBalanceMasterImpl.setPeriod(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setPeriod(period);
        }

        if (source == null) {
            glBalanceMasterImpl.setSource(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setSource(source);
        }

        if (insertedDate == Long.MIN_VALUE) {
            glBalanceMasterImpl.setInsertedDate(null);
        } else {
            glBalanceMasterImpl.setInsertedDate(new Date(insertedDate));
        }

        if (accountNo == null) {
            glBalanceMasterImpl.setAccountNo(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setAccountNo(accountNo);
        }

        if (inboundStatus == null) {
            glBalanceMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            glBalanceMasterImpl.setInboundStatus(inboundStatus);
        }

        glBalanceMasterImpl.resetOriginalValues();

        return glBalanceMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        accountId = objectInput.readUTF();
        uploadDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        glBalanceMasterSid = objectInput.readInt();
        isActive = objectInput.readUTF();
        batchId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        balance = objectInput.readUTF();
        closeIndicator = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        year = objectInput.readUTF();
        period = objectInput.readUTF();
        source = objectInput.readUTF();
        insertedDate = objectInput.readLong();
        accountNo = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(modifiedBy);

        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        objectOutput.writeLong(uploadDate);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(glBalanceMasterSid);

        if (isActive == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isActive);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeLong(modifiedDate);

        if (balance == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(balance);
        }

        if (closeIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(closeIndicator);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (year == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(year);
        }

        if (period == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(period);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeLong(insertedDate);

        if (accountNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountNo);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
