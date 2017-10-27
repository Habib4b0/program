package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.IvldItemPricing;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldItemPricing in entity cache.
 *
 * @author
 * @see IvldItemPricing
 * @generated
 */
public class IvldItemPricingCacheModel implements CacheModel<IvldItemPricing>,
    Externalizable {
    public String itemNo;
    public String modifiedBy;
    public String pricingCodeQualifierName;
    public long createdDate;
    public String endDate;
    public String batchId;
    public String itemName;
    public String errorCode;
    public String reprocessedFlag;
    public String itemPricingIntfid;
    public int ivldItemPricingSid;
    public String pricingCodeStatus;
    public String createdBy;
    public String itemId;
    public String errorField;
    public String startDate;
    public String itemUom;
    public long modifiedDate;
    public String reasonForFailure;
    public String source;
    public String pricingCodeQualifier;
    public String addChgDelIndicator;
    public String entityCode;
    public String itemPrice;
    public long intfInsertedDate;
    public boolean checkRecord;
    public String itemPriceprecision;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(55);

        sb.append("{itemNo=");
        sb.append(itemNo);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", pricingCodeQualifierName=");
        sb.append(pricingCodeQualifierName);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", itemPricingIntfid=");
        sb.append(itemPricingIntfid);
        sb.append(", ivldItemPricingSid=");
        sb.append(ivldItemPricingSid);
        sb.append(", pricingCodeStatus=");
        sb.append(pricingCodeStatus);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", itemUom=");
        sb.append(itemUom);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", source=");
        sb.append(source);
        sb.append(", pricingCodeQualifier=");
        sb.append(pricingCodeQualifier);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", entityCode=");
        sb.append(entityCode);
        sb.append(", itemPrice=");
        sb.append(itemPrice);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", itemPriceprecision=");
        sb.append(itemPriceprecision);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldItemPricing toEntityModel() {
        IvldItemPricingImpl ivldItemPricingImpl = new IvldItemPricingImpl();

        if (itemNo == null) {
            ivldItemPricingImpl.setItemNo(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setItemNo(itemNo);
        }

        if (modifiedBy == null) {
            ivldItemPricingImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setModifiedBy(modifiedBy);
        }

        if (pricingCodeQualifierName == null) {
            ivldItemPricingImpl.setPricingCodeQualifierName(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setPricingCodeQualifierName(pricingCodeQualifierName);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldItemPricingImpl.setCreatedDate(null);
        } else {
            ivldItemPricingImpl.setCreatedDate(new Date(createdDate));
        }

        if (endDate == null) {
            ivldItemPricingImpl.setEndDate(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setEndDate(endDate);
        }

        if (batchId == null) {
            ivldItemPricingImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setBatchId(batchId);
        }

        if (itemName == null) {
            ivldItemPricingImpl.setItemName(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setItemName(itemName);
        }

        if (errorCode == null) {
            ivldItemPricingImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setErrorCode(errorCode);
        }

        if (reprocessedFlag == null) {
            ivldItemPricingImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (itemPricingIntfid == null) {
            ivldItemPricingImpl.setItemPricingIntfid(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setItemPricingIntfid(itemPricingIntfid);
        }

        ivldItemPricingImpl.setIvldItemPricingSid(ivldItemPricingSid);

        if (pricingCodeStatus == null) {
            ivldItemPricingImpl.setPricingCodeStatus(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setPricingCodeStatus(pricingCodeStatus);
        }

        if (createdBy == null) {
            ivldItemPricingImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setCreatedBy(createdBy);
        }

        if (itemId == null) {
            ivldItemPricingImpl.setItemId(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setItemId(itemId);
        }

        if (errorField == null) {
            ivldItemPricingImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setErrorField(errorField);
        }

        if (startDate == null) {
            ivldItemPricingImpl.setStartDate(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setStartDate(startDate);
        }

        if (itemUom == null) {
            ivldItemPricingImpl.setItemUom(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setItemUom(itemUom);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldItemPricingImpl.setModifiedDate(null);
        } else {
            ivldItemPricingImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (reasonForFailure == null) {
            ivldItemPricingImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setReasonForFailure(reasonForFailure);
        }

        if (source == null) {
            ivldItemPricingImpl.setSource(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setSource(source);
        }

        if (pricingCodeQualifier == null) {
            ivldItemPricingImpl.setPricingCodeQualifier(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setPricingCodeQualifier(pricingCodeQualifier);
        }

        if (addChgDelIndicator == null) {
            ivldItemPricingImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (entityCode == null) {
            ivldItemPricingImpl.setEntityCode(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setEntityCode(entityCode);
        }

        if (itemPrice == null) {
            ivldItemPricingImpl.setItemPrice(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setItemPrice(itemPrice);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldItemPricingImpl.setIntfInsertedDate(null);
        } else {
            ivldItemPricingImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        ivldItemPricingImpl.setCheckRecord(checkRecord);

        if (itemPriceprecision == null) {
            ivldItemPricingImpl.setItemPriceprecision(StringPool.BLANK);
        } else {
            ivldItemPricingImpl.setItemPriceprecision(itemPriceprecision);
        }

        ivldItemPricingImpl.resetOriginalValues();

        return ivldItemPricingImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemNo = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        pricingCodeQualifierName = objectInput.readUTF();
        createdDate = objectInput.readLong();
        endDate = objectInput.readUTF();
        batchId = objectInput.readUTF();
        itemName = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        itemPricingIntfid = objectInput.readUTF();
        ivldItemPricingSid = objectInput.readInt();
        pricingCodeStatus = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        itemId = objectInput.readUTF();
        errorField = objectInput.readUTF();
        startDate = objectInput.readUTF();
        itemUom = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        reasonForFailure = objectInput.readUTF();
        source = objectInput.readUTF();
        pricingCodeQualifier = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        entityCode = objectInput.readUTF();
        itemPrice = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        checkRecord = objectInput.readBoolean();
        itemPriceprecision = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (pricingCodeQualifierName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pricingCodeQualifierName);
        }

        objectOutput.writeLong(createdDate);

        if (endDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(endDate);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

        if (itemPricingIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemPricingIntfid);
        }

        objectOutput.writeInt(ivldItemPricingSid);

        if (pricingCodeStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pricingCodeStatus);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        if (startDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(startDate);
        }

        if (itemUom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemUom);
        }

        objectOutput.writeLong(modifiedDate);

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (pricingCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pricingCodeQualifier);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (entityCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(entityCode);
        }

        if (itemPrice == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemPrice);
        }

        objectOutput.writeLong(intfInsertedDate);
        objectOutput.writeBoolean(checkRecord);

        if (itemPriceprecision == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemPriceprecision);
        }
    }
}
