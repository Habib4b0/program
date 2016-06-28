package com.stpl.app.model.impl;

import com.stpl.app.model.ItemIdentifier;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemIdentifier in entity cache.
 *
 * @author
 * @see ItemIdentifier
 * @generated
 */
public class ItemIdentifierCacheModel implements CacheModel<ItemIdentifier>,
    Externalizable {
    public int itemIdentifierSid;
    public int itemMasterSid;
    public long endDate;
    public long modifiedDate;
    public int identifierStatus;
    public String entityCode;
    public String itemIdentifierValue;
    public boolean recordLockStatus;
    public int itemQualifierSid;
    public long startDate;
    public long createdDate;
    public String source;
    public int createdBy;
    public String batchId;
    public int modifiedBy;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(33);

        sb.append("{itemIdentifierSid=");
        sb.append(itemIdentifierSid);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", identifierStatus=");
        sb.append(identifierStatus);
        sb.append(", entityCode=");
        sb.append(entityCode);
        sb.append(", itemIdentifierValue=");
        sb.append(itemIdentifierValue);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", itemQualifierSid=");
        sb.append(itemQualifierSid);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ItemIdentifier toEntityModel() {
        ItemIdentifierImpl itemIdentifierImpl = new ItemIdentifierImpl();

        itemIdentifierImpl.setItemIdentifierSid(itemIdentifierSid);
        itemIdentifierImpl.setItemMasterSid(itemMasterSid);

        if (endDate == Long.MIN_VALUE) {
            itemIdentifierImpl.setEndDate(null);
        } else {
            itemIdentifierImpl.setEndDate(new Date(endDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            itemIdentifierImpl.setModifiedDate(null);
        } else {
            itemIdentifierImpl.setModifiedDate(new Date(modifiedDate));
        }

        itemIdentifierImpl.setIdentifierStatus(identifierStatus);

        if (entityCode == null) {
            itemIdentifierImpl.setEntityCode(StringPool.BLANK);
        } else {
            itemIdentifierImpl.setEntityCode(entityCode);
        }

        if (itemIdentifierValue == null) {
            itemIdentifierImpl.setItemIdentifierValue(StringPool.BLANK);
        } else {
            itemIdentifierImpl.setItemIdentifierValue(itemIdentifierValue);
        }

        itemIdentifierImpl.setRecordLockStatus(recordLockStatus);
        itemIdentifierImpl.setItemQualifierSid(itemQualifierSid);

        if (startDate == Long.MIN_VALUE) {
            itemIdentifierImpl.setStartDate(null);
        } else {
            itemIdentifierImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            itemIdentifierImpl.setCreatedDate(null);
        } else {
            itemIdentifierImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            itemIdentifierImpl.setSource(StringPool.BLANK);
        } else {
            itemIdentifierImpl.setSource(source);
        }

        itemIdentifierImpl.setCreatedBy(createdBy);

        if (batchId == null) {
            itemIdentifierImpl.setBatchId(StringPool.BLANK);
        } else {
            itemIdentifierImpl.setBatchId(batchId);
        }

        itemIdentifierImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            itemIdentifierImpl.setInboundStatus(StringPool.BLANK);
        } else {
            itemIdentifierImpl.setInboundStatus(inboundStatus);
        }

        itemIdentifierImpl.resetOriginalValues();

        return itemIdentifierImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemIdentifierSid = objectInput.readInt();
        itemMasterSid = objectInput.readInt();
        endDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        identifierStatus = objectInput.readInt();
        entityCode = objectInput.readUTF();
        itemIdentifierValue = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        itemQualifierSid = objectInput.readInt();
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdBy = objectInput.readInt();
        batchId = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(itemIdentifierSid);
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeLong(endDate);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(identifierStatus);

        if (entityCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(entityCode);
        }

        if (itemIdentifierValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifierValue);
        }

        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeInt(itemQualifierSid);
        objectOutput.writeLong(startDate);
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

        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
