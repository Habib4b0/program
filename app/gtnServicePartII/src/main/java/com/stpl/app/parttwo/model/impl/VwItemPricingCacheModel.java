package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.VwItemPricing;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwItemPricing in entity cache.
 *
 * @author
 * @see VwItemPricing
 * @generated
 */
public class VwItemPricingCacheModel implements CacheModel<VwItemPricing>,
    Externalizable {
    public String pricingCodeQualifier;
    public String itemPrice;
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
    public String itemUom;
    public String modifiedBy;
    public String itemNo;
    public int itemPricingSid;
    public String pricingCodeStatus;
    public String pricingCodeQualifierName;
    public int itemPriceprecision;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(41);

        sb.append("{pricingCodeQualifier=");
        sb.append(pricingCodeQualifier);
        sb.append(", itemPrice=");
        sb.append(itemPrice);
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
        sb.append(", itemUom=");
        sb.append(itemUom);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", itemPricingSid=");
        sb.append(itemPricingSid);
        sb.append(", pricingCodeStatus=");
        sb.append(pricingCodeStatus);
        sb.append(", pricingCodeQualifierName=");
        sb.append(pricingCodeQualifierName);
        sb.append(", itemPriceprecision=");
        sb.append(itemPriceprecision);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwItemPricing toEntityModel() {
        VwItemPricingImpl vwItemPricingImpl = new VwItemPricingImpl();

        if (pricingCodeQualifier == null) {
            vwItemPricingImpl.setPricingCodeQualifier(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setPricingCodeQualifier(pricingCodeQualifier);
        }

        if (itemPrice == null) {
            vwItemPricingImpl.setItemPrice(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setItemPrice(itemPrice);
        }

        if (endDate == Long.MIN_VALUE) {
            vwItemPricingImpl.setEndDate(null);
        } else {
            vwItemPricingImpl.setEndDate(new Date(endDate));
        }

        if (itemId == null) {
            vwItemPricingImpl.setItemId(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            vwItemPricingImpl.setModifiedDate(null);
        } else {
            vwItemPricingImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (entityCode == null) {
            vwItemPricingImpl.setEntityCode(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setEntityCode(entityCode);
        }

        if (startDate == Long.MIN_VALUE) {
            vwItemPricingImpl.setStartDate(null);
        } else {
            vwItemPricingImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            vwItemPricingImpl.setCreatedDate(null);
        } else {
            vwItemPricingImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            vwItemPricingImpl.setCreatedBy(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setCreatedBy(createdBy);
        }

        if (source == null) {
            vwItemPricingImpl.setSource(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setSource(source);
        }

        if (batchId == null) {
            vwItemPricingImpl.setBatchId(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setBatchId(batchId);
        }

        if (addChgDelIndicator == null) {
            vwItemPricingImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (itemName == null) {
            vwItemPricingImpl.setItemName(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setItemName(itemName);
        }

        if (itemUom == null) {
            vwItemPricingImpl.setItemUom(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setItemUom(itemUom);
        }

        if (modifiedBy == null) {
            vwItemPricingImpl.setModifiedBy(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setModifiedBy(modifiedBy);
        }

        if (itemNo == null) {
            vwItemPricingImpl.setItemNo(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setItemNo(itemNo);
        }

        vwItemPricingImpl.setItemPricingSid(itemPricingSid);

        if (pricingCodeStatus == null) {
            vwItemPricingImpl.setPricingCodeStatus(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setPricingCodeStatus(pricingCodeStatus);
        }

        if (pricingCodeQualifierName == null) {
            vwItemPricingImpl.setPricingCodeQualifierName(StringPool.BLANK);
        } else {
            vwItemPricingImpl.setPricingCodeQualifierName(pricingCodeQualifierName);
        }

        vwItemPricingImpl.setItemPriceprecision(itemPriceprecision);

        vwItemPricingImpl.resetOriginalValues();

        return vwItemPricingImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        pricingCodeQualifier = objectInput.readUTF();
        itemPrice = objectInput.readUTF();
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
        itemUom = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        itemNo = objectInput.readUTF();
        itemPricingSid = objectInput.readInt();
        pricingCodeStatus = objectInput.readUTF();
        pricingCodeQualifierName = objectInput.readUTF();
        itemPriceprecision = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (pricingCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pricingCodeQualifier);
        }

        if (itemPrice == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemPrice);
        }

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

        if (itemUom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemUom);
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

        objectOutput.writeInt(itemPricingSid);

        if (pricingCodeStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pricingCodeStatus);
        }

        if (pricingCodeQualifierName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pricingCodeQualifierName);
        }

        objectOutput.writeInt(itemPriceprecision);
    }
}
