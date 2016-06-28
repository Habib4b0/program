package com.stpl.app.model.impl;

import com.stpl.app.model.IvldAverageShelfLife;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldAverageShelfLife in entity cache.
 *
 * @author
 * @see IvldAverageShelfLife
 * @generated
 */
public class IvldAverageShelfLifeCacheModel implements CacheModel<IvldAverageShelfLife>,
    Externalizable {
    public String reasonForFailure;
    public int ivldAverageShelfLifeSid;
    public String itemId;
    public String avgShelfLife;
    public long modifiedDate;
    public String createdBy;
    public long createdDate;
    public String source;
    public String itemIdType;
    public String batchId;
    public String addChgDelIndicator;
    public String averageShelfLifeIntfid;
    public String errorField;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String reprocessedFlag;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(35);

        sb.append("{reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", ivldAverageShelfLifeSid=");
        sb.append(ivldAverageShelfLifeSid);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", avgShelfLife=");
        sb.append(avgShelfLife);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", itemIdType=");
        sb.append(itemIdType);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", averageShelfLifeIntfid=");
        sb.append(averageShelfLifeIntfid);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldAverageShelfLife toEntityModel() {
        IvldAverageShelfLifeImpl ivldAverageShelfLifeImpl = new IvldAverageShelfLifeImpl();

        if (reasonForFailure == null) {
            ivldAverageShelfLifeImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setReasonForFailure(reasonForFailure);
        }

        ivldAverageShelfLifeImpl.setIvldAverageShelfLifeSid(ivldAverageShelfLifeSid);

        if (itemId == null) {
            ivldAverageShelfLifeImpl.setItemId(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setItemId(itemId);
        }

        if (avgShelfLife == null) {
            ivldAverageShelfLifeImpl.setAvgShelfLife(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setAvgShelfLife(avgShelfLife);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldAverageShelfLifeImpl.setModifiedDate(null);
        } else {
            ivldAverageShelfLifeImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (createdBy == null) {
            ivldAverageShelfLifeImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldAverageShelfLifeImpl.setCreatedDate(null);
        } else {
            ivldAverageShelfLifeImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            ivldAverageShelfLifeImpl.setSource(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setSource(source);
        }

        if (itemIdType == null) {
            ivldAverageShelfLifeImpl.setItemIdType(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setItemIdType(itemIdType);
        }

        if (batchId == null) {
            ivldAverageShelfLifeImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setBatchId(batchId);
        }

        if (addChgDelIndicator == null) {
            ivldAverageShelfLifeImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (averageShelfLifeIntfid == null) {
            ivldAverageShelfLifeImpl.setAverageShelfLifeIntfid(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setAverageShelfLifeIntfid(averageShelfLifeIntfid);
        }

        if (errorField == null) {
            ivldAverageShelfLifeImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setErrorField(errorField);
        }

        if (errorCode == null) {
            ivldAverageShelfLifeImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldAverageShelfLifeImpl.setIntfInsertedDate(null);
        } else {
            ivldAverageShelfLifeImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldAverageShelfLifeImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setModifiedBy(modifiedBy);
        }

        if (reprocessedFlag == null) {
            ivldAverageShelfLifeImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldAverageShelfLifeImpl.setReprocessedFlag(reprocessedFlag);
        }

        ivldAverageShelfLifeImpl.resetOriginalValues();

        return ivldAverageShelfLifeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        reasonForFailure = objectInput.readUTF();
        ivldAverageShelfLifeSid = objectInput.readInt();
        itemId = objectInput.readUTF();
        avgShelfLife = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        itemIdType = objectInput.readUTF();
        batchId = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        averageShelfLifeIntfid = objectInput.readUTF();
        errorField = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        objectOutput.writeInt(ivldAverageShelfLifeSid);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (avgShelfLife == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(avgShelfLife);
        }

        objectOutput.writeLong(modifiedDate);

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeLong(createdDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (itemIdType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdType);
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

        if (averageShelfLifeIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(averageShelfLifeIntfid);
        }

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        objectOutput.writeLong(intfInsertedDate);

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }
    }
}
