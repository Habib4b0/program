package com.stpl.app.model.impl;

import com.stpl.app.model.BestPriceMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BestPriceMaster in entity cache.
 *
 * @author
 * @see BestPriceMaster
 * @generated
 */
public class BestPriceMasterCacheModel implements CacheModel<BestPriceMaster>,
    Externalizable {
    public double initialBestPrice;
    public int createdBy;
    public String itemNo;
    public int modifiedBy;
    public String accountId;
    public long createdDate;
    public String itemId;
    public String itemDesc;
    public double sellingPrice;
    public String contractId;
    public String contractNo;
    public String batchId;
    public int bestPriceMasterSid;
    public long modifiedDate;
    public boolean recordLockStatus;
    public double beginningWacPackage;
    public double initialDiscount;
    public String period;
    public String source;
    public long contractStartDate;
    public long contractEndDate;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(45);

        sb.append("{initialBestPrice=");
        sb.append(initialBestPrice);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", accountId=");
        sb.append(accountId);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", itemDesc=");
        sb.append(itemDesc);
        sb.append(", sellingPrice=");
        sb.append(sellingPrice);
        sb.append(", contractId=");
        sb.append(contractId);
        sb.append(", contractNo=");
        sb.append(contractNo);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", bestPriceMasterSid=");
        sb.append(bestPriceMasterSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", beginningWacPackage=");
        sb.append(beginningWacPackage);
        sb.append(", initialDiscount=");
        sb.append(initialDiscount);
        sb.append(", period=");
        sb.append(period);
        sb.append(", source=");
        sb.append(source);
        sb.append(", contractStartDate=");
        sb.append(contractStartDate);
        sb.append(", contractEndDate=");
        sb.append(contractEndDate);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public BestPriceMaster toEntityModel() {
        BestPriceMasterImpl bestPriceMasterImpl = new BestPriceMasterImpl();

        bestPriceMasterImpl.setInitialBestPrice(initialBestPrice);
        bestPriceMasterImpl.setCreatedBy(createdBy);

        if (itemNo == null) {
            bestPriceMasterImpl.setItemNo(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setItemNo(itemNo);
        }

        bestPriceMasterImpl.setModifiedBy(modifiedBy);

        if (accountId == null) {
            bestPriceMasterImpl.setAccountId(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setAccountId(accountId);
        }

        if (createdDate == Long.MIN_VALUE) {
            bestPriceMasterImpl.setCreatedDate(null);
        } else {
            bestPriceMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (itemId == null) {
            bestPriceMasterImpl.setItemId(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setItemId(itemId);
        }

        if (itemDesc == null) {
            bestPriceMasterImpl.setItemDesc(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setItemDesc(itemDesc);
        }

        bestPriceMasterImpl.setSellingPrice(sellingPrice);

        if (contractId == null) {
            bestPriceMasterImpl.setContractId(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setContractId(contractId);
        }

        if (contractNo == null) {
            bestPriceMasterImpl.setContractNo(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setContractNo(contractNo);
        }

        if (batchId == null) {
            bestPriceMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setBatchId(batchId);
        }

        bestPriceMasterImpl.setBestPriceMasterSid(bestPriceMasterSid);

        if (modifiedDate == Long.MIN_VALUE) {
            bestPriceMasterImpl.setModifiedDate(null);
        } else {
            bestPriceMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        bestPriceMasterImpl.setRecordLockStatus(recordLockStatus);
        bestPriceMasterImpl.setBeginningWacPackage(beginningWacPackage);
        bestPriceMasterImpl.setInitialDiscount(initialDiscount);

        if (period == null) {
            bestPriceMasterImpl.setPeriod(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setPeriod(period);
        }

        if (source == null) {
            bestPriceMasterImpl.setSource(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setSource(source);
        }

        if (contractStartDate == Long.MIN_VALUE) {
            bestPriceMasterImpl.setContractStartDate(null);
        } else {
            bestPriceMasterImpl.setContractStartDate(new Date(contractStartDate));
        }

        if (contractEndDate == Long.MIN_VALUE) {
            bestPriceMasterImpl.setContractEndDate(null);
        } else {
            bestPriceMasterImpl.setContractEndDate(new Date(contractEndDate));
        }

        if (inboundStatus == null) {
            bestPriceMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            bestPriceMasterImpl.setInboundStatus(inboundStatus);
        }

        bestPriceMasterImpl.resetOriginalValues();

        return bestPriceMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        initialBestPrice = objectInput.readDouble();
        createdBy = objectInput.readInt();
        itemNo = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        accountId = objectInput.readUTF();
        createdDate = objectInput.readLong();
        itemId = objectInput.readUTF();
        itemDesc = objectInput.readUTF();
        sellingPrice = objectInput.readDouble();
        contractId = objectInput.readUTF();
        contractNo = objectInput.readUTF();
        batchId = objectInput.readUTF();
        bestPriceMasterSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        beginningWacPackage = objectInput.readDouble();
        initialDiscount = objectInput.readDouble();
        period = objectInput.readUTF();
        source = objectInput.readUTF();
        contractStartDate = objectInput.readLong();
        contractEndDate = objectInput.readLong();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(initialBestPrice);
        objectOutput.writeInt(createdBy);

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        objectOutput.writeInt(modifiedBy);

        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        objectOutput.writeLong(createdDate);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (itemDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemDesc);
        }

        objectOutput.writeDouble(sellingPrice);

        if (contractId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractId);
        }

        if (contractNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractNo);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(bestPriceMasterSid);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeDouble(beginningWacPackage);
        objectOutput.writeDouble(initialDiscount);

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

        objectOutput.writeLong(contractStartDate);
        objectOutput.writeLong(contractEndDate);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
