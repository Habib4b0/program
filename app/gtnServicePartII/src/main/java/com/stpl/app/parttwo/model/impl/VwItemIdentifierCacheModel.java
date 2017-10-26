package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.VwItemIdentifier;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwItemIdentifier in entity cache.
 *
 * @author
 * @see VwItemIdentifier
 * @generated
 */
public class VwItemIdentifierCacheModel implements CacheModel<VwItemIdentifier>,
    Externalizable {
    public String itemStatus;
    public int itemIdentifierSid;
    public long endDate;
    public String itemId;
    public long modifiedDate;
    public String entityCode;
    public long startDate;
    public long createdDate;
    public String createdBy;
    public String source;
    public String batchId;
    public String addChgDelIndicator;
    public String itemName;
    public String itemIdentifier;
    public String identifierCodeQualifierName;
    public String modifiedBy;
    public String itemNo;
    public String identifierCodeQualifier;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{itemStatus=");
        sb.append(itemStatus);
        sb.append(", itemIdentifierSid=");
        sb.append(itemIdentifierSid);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", entityCode=");
        sb.append(entityCode);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", itemIdentifier=");
        sb.append(itemIdentifier);
        sb.append(", identifierCodeQualifierName=");
        sb.append(identifierCodeQualifierName);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", identifierCodeQualifier=");
        sb.append(identifierCodeQualifier);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwItemIdentifier toEntityModel() {
        VwItemIdentifierImpl vwItemIdentifierImpl = new VwItemIdentifierImpl();

        if (itemStatus == null) {
            vwItemIdentifierImpl.setItemStatus(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setItemStatus(itemStatus);
        }

        vwItemIdentifierImpl.setItemIdentifierSid(itemIdentifierSid);

        if (endDate == Long.MIN_VALUE) {
            vwItemIdentifierImpl.setEndDate(null);
        } else {
            vwItemIdentifierImpl.setEndDate(new Date(endDate));
        }

        if (itemId == null) {
            vwItemIdentifierImpl.setItemId(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            vwItemIdentifierImpl.setModifiedDate(null);
        } else {
            vwItemIdentifierImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (entityCode == null) {
            vwItemIdentifierImpl.setEntityCode(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setEntityCode(entityCode);
        }

        if (startDate == Long.MIN_VALUE) {
            vwItemIdentifierImpl.setStartDate(null);
        } else {
            vwItemIdentifierImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            vwItemIdentifierImpl.setCreatedDate(null);
        } else {
            vwItemIdentifierImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            vwItemIdentifierImpl.setCreatedBy(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setCreatedBy(createdBy);
        }

        if (source == null) {
            vwItemIdentifierImpl.setSource(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setSource(source);
        }

        if (batchId == null) {
            vwItemIdentifierImpl.setBatchId(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setBatchId(batchId);
        }

        if (addChgDelIndicator == null) {
            vwItemIdentifierImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (itemName == null) {
            vwItemIdentifierImpl.setItemName(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setItemName(itemName);
        }

        if (itemIdentifier == null) {
            vwItemIdentifierImpl.setItemIdentifier(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setItemIdentifier(itemIdentifier);
        }

        if (identifierCodeQualifierName == null) {
            vwItemIdentifierImpl.setIdentifierCodeQualifierName(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        if (modifiedBy == null) {
            vwItemIdentifierImpl.setModifiedBy(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setModifiedBy(modifiedBy);
        }

        if (itemNo == null) {
            vwItemIdentifierImpl.setItemNo(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setItemNo(itemNo);
        }

        if (identifierCodeQualifier == null) {
            vwItemIdentifierImpl.setIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            vwItemIdentifierImpl.setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        vwItemIdentifierImpl.resetOriginalValues();

        return vwItemIdentifierImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemStatus = objectInput.readUTF();
        itemIdentifierSid = objectInput.readInt();
        endDate = objectInput.readLong();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        entityCode = objectInput.readUTF();
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        source = objectInput.readUTF();
        batchId = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        itemName = objectInput.readUTF();
        itemIdentifier = objectInput.readUTF();
        identifierCodeQualifierName = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        itemNo = objectInput.readUTF();
        identifierCodeQualifier = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (itemStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemStatus);
        }

        objectOutput.writeInt(itemIdentifierSid);
        objectOutput.writeLong(endDate);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeLong(modifiedDate);

        if (entityCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(entityCode);
        }

        objectOutput.writeLong(startDate);
        objectOutput.writeLong(createdDate);

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        if (itemIdentifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifier);
        }

        if (identifierCodeQualifierName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(identifierCodeQualifierName);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        if (identifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(identifierCodeQualifier);
        }
    }
}
