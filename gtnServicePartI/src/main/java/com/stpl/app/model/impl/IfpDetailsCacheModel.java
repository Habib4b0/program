package com.stpl.app.model.impl;

import com.stpl.app.model.IfpDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IfpDetails in entity cache.
 *
 * @author
 * @see IfpDetails
 * @generated
 */
public class IfpDetailsCacheModel implements CacheModel<IfpDetails>,
    Externalizable {
    public int itemMasterSid;
    public long endDate;
    public long modifiedDate;
    public boolean recordLockStatus;
    public long startDate;
    public long createdDate;
    public String source;
    public int createdBy;
    public long itemIfpAttachedDate;
    public String batchId;
    public int modifiedBy;
    public String inboundStatus;
    public int ifpDetailsSid;
    public int ifpModelSid;
    public int itemIfpAttachedStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", itemIfpAttachedDate=");
        sb.append(itemIfpAttachedDate);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", ifpDetailsSid=");
        sb.append(ifpDetailsSid);
        sb.append(", ifpModelSid=");
        sb.append(ifpModelSid);
        sb.append(", itemIfpAttachedStatus=");
        sb.append(itemIfpAttachedStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IfpDetails toEntityModel() {
        IfpDetailsImpl ifpDetailsImpl = new IfpDetailsImpl();

        ifpDetailsImpl.setItemMasterSid(itemMasterSid);

        if (endDate == Long.MIN_VALUE) {
            ifpDetailsImpl.setEndDate(null);
        } else {
            ifpDetailsImpl.setEndDate(new Date(endDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ifpDetailsImpl.setModifiedDate(null);
        } else {
            ifpDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        ifpDetailsImpl.setRecordLockStatus(recordLockStatus);

        if (startDate == Long.MIN_VALUE) {
            ifpDetailsImpl.setStartDate(null);
        } else {
            ifpDetailsImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            ifpDetailsImpl.setCreatedDate(null);
        } else {
            ifpDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            ifpDetailsImpl.setSource(StringPool.BLANK);
        } else {
            ifpDetailsImpl.setSource(source);
        }

        ifpDetailsImpl.setCreatedBy(createdBy);

        if (itemIfpAttachedDate == Long.MIN_VALUE) {
            ifpDetailsImpl.setItemIfpAttachedDate(null);
        } else {
            ifpDetailsImpl.setItemIfpAttachedDate(new Date(itemIfpAttachedDate));
        }

        if (batchId == null) {
            ifpDetailsImpl.setBatchId(StringPool.BLANK);
        } else {
            ifpDetailsImpl.setBatchId(batchId);
        }

        ifpDetailsImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            ifpDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            ifpDetailsImpl.setInboundStatus(inboundStatus);
        }

        ifpDetailsImpl.setIfpDetailsSid(ifpDetailsSid);
        ifpDetailsImpl.setIfpModelSid(ifpModelSid);
        ifpDetailsImpl.setItemIfpAttachedStatus(itemIfpAttachedStatus);

        ifpDetailsImpl.resetOriginalValues();

        return ifpDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemMasterSid = objectInput.readInt();
        endDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdBy = objectInput.readInt();
        itemIfpAttachedDate = objectInput.readLong();
        batchId = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        ifpDetailsSid = objectInput.readInt();
        ifpModelSid = objectInput.readInt();
        itemIfpAttachedStatus = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeLong(endDate);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(startDate);
        objectOutput.writeLong(createdDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(itemIfpAttachedDate);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(ifpDetailsSid);
        objectOutput.writeInt(ifpModelSid);
        objectOutput.writeInt(itemIfpAttachedStatus);
    }
}
