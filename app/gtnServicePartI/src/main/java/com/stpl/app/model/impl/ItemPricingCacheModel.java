package com.stpl.app.model.impl;

import com.stpl.app.model.ItemPricing;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemPricing in entity cache.
 *
 * @author
 * @see ItemPricing
 * @generated
 */
public class ItemPricingCacheModel implements CacheModel<ItemPricing>,
    Externalizable {
    public int itemMasterSid;
    public int itemPricingQualifierSid;
    public double itemPrice;
    public long endDate;
    public long modifiedDate;
    public String entityCode;
    public boolean recordLockStatus;
    public long startDate;
    public long createdDate;
    public int createdBy;
    public String source;
    public String batchId;
    public int itemUom;
    public int modifiedBy;
    public String inboundStatus;
    public int itemPricingSid;
    public int pricingCodeStatus;
    public int itemPricePrecision;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", itemPricingQualifierSid=");
        sb.append(itemPricingQualifierSid);
        sb.append(", itemPrice=");
        sb.append(itemPrice);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", entityCode=");
        sb.append(entityCode);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
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
        sb.append(", itemUom=");
        sb.append(itemUom);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", itemPricingSid=");
        sb.append(itemPricingSid);
        sb.append(", pricingCodeStatus=");
        sb.append(pricingCodeStatus);
        sb.append(", itemPricePrecision=");
        sb.append(itemPricePrecision);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ItemPricing toEntityModel() {
        ItemPricingImpl itemPricingImpl = new ItemPricingImpl();

        itemPricingImpl.setItemMasterSid(itemMasterSid);
        itemPricingImpl.setItemPricingQualifierSid(itemPricingQualifierSid);
        itemPricingImpl.setItemPrice(itemPrice);

        if (endDate == Long.MIN_VALUE) {
            itemPricingImpl.setEndDate(null);
        } else {
            itemPricingImpl.setEndDate(new Date(endDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            itemPricingImpl.setModifiedDate(null);
        } else {
            itemPricingImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (entityCode == null) {
            itemPricingImpl.setEntityCode(StringPool.BLANK);
        } else {
            itemPricingImpl.setEntityCode(entityCode);
        }

        itemPricingImpl.setRecordLockStatus(recordLockStatus);

        if (startDate == Long.MIN_VALUE) {
            itemPricingImpl.setStartDate(null);
        } else {
            itemPricingImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            itemPricingImpl.setCreatedDate(null);
        } else {
            itemPricingImpl.setCreatedDate(new Date(createdDate));
        }

        itemPricingImpl.setCreatedBy(createdBy);

        if (source == null) {
            itemPricingImpl.setSource(StringPool.BLANK);
        } else {
            itemPricingImpl.setSource(source);
        }

        if (batchId == null) {
            itemPricingImpl.setBatchId(StringPool.BLANK);
        } else {
            itemPricingImpl.setBatchId(batchId);
        }

        itemPricingImpl.setItemUom(itemUom);
        itemPricingImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            itemPricingImpl.setInboundStatus(StringPool.BLANK);
        } else {
            itemPricingImpl.setInboundStatus(inboundStatus);
        }

        itemPricingImpl.setItemPricingSid(itemPricingSid);
        itemPricingImpl.setPricingCodeStatus(pricingCodeStatus);
        itemPricingImpl.setItemPricePrecision(itemPricePrecision);

        itemPricingImpl.resetOriginalValues();

        return itemPricingImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemMasterSid = objectInput.readInt();
        itemPricingQualifierSid = objectInput.readInt();
        itemPrice = objectInput.readDouble();
        endDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        entityCode = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        source = objectInput.readUTF();
        batchId = objectInput.readUTF();
        itemUom = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        itemPricingSid = objectInput.readInt();
        pricingCodeStatus = objectInput.readInt();
        itemPricePrecision = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeInt(itemPricingQualifierSid);
        objectOutput.writeDouble(itemPrice);
        objectOutput.writeLong(endDate);
        objectOutput.writeLong(modifiedDate);

        if (entityCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(entityCode);
        }

        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(startDate);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

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

        objectOutput.writeInt(itemUom);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(itemPricingSid);
        objectOutput.writeInt(pricingCodeStatus);
        objectOutput.writeInt(itemPricePrecision);
    }
}
