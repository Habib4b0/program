package com.stpl.app.model.impl;

import com.stpl.app.model.IvldCpi;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldCpi in entity cache.
 *
 * @author
 * @see IvldCpi
 * @generated
 */
public class IvldCpiCacheModel implements CacheModel<IvldCpi>, Externalizable {
    public String effectiveDate;
    public String reasonForFailure;
    public String indexType;
    public String status;
    public long modifiedDate;
    public String cpiIntfid;
    public String createdBy;
    public long createdDate;
    public String source;
    public String indexValue;
    public String addChgDelIndicator;
    public String batchId;
    public int ivldCpiSid;
    public String errorField;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String reprocessedFlag;
    public String indexId;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(41);

        sb.append("{effectiveDate=");
        sb.append(effectiveDate);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", indexType=");
        sb.append(indexType);
        sb.append(", status=");
        sb.append(status);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", cpiIntfid=");
        sb.append(cpiIntfid);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", indexValue=");
        sb.append(indexValue);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", ivldCpiSid=");
        sb.append(ivldCpiSid);
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
        sb.append(", indexId=");
        sb.append(indexId);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldCpi toEntityModel() {
        IvldCpiImpl ivldCpiImpl = new IvldCpiImpl();

        if (effectiveDate == null) {
            ivldCpiImpl.setEffectiveDate(StringPool.BLANK);
        } else {
            ivldCpiImpl.setEffectiveDate(effectiveDate);
        }

        if (reasonForFailure == null) {
            ivldCpiImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldCpiImpl.setReasonForFailure(reasonForFailure);
        }

        if (indexType == null) {
            ivldCpiImpl.setIndexType(StringPool.BLANK);
        } else {
            ivldCpiImpl.setIndexType(indexType);
        }

        if (status == null) {
            ivldCpiImpl.setStatus(StringPool.BLANK);
        } else {
            ivldCpiImpl.setStatus(status);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldCpiImpl.setModifiedDate(null);
        } else {
            ivldCpiImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (cpiIntfid == null) {
            ivldCpiImpl.setCpiIntfid(StringPool.BLANK);
        } else {
            ivldCpiImpl.setCpiIntfid(cpiIntfid);
        }

        if (createdBy == null) {
            ivldCpiImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldCpiImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldCpiImpl.setCreatedDate(null);
        } else {
            ivldCpiImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            ivldCpiImpl.setSource(StringPool.BLANK);
        } else {
            ivldCpiImpl.setSource(source);
        }

        if (indexValue == null) {
            ivldCpiImpl.setIndexValue(StringPool.BLANK);
        } else {
            ivldCpiImpl.setIndexValue(indexValue);
        }

        if (addChgDelIndicator == null) {
            ivldCpiImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldCpiImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (batchId == null) {
            ivldCpiImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldCpiImpl.setBatchId(batchId);
        }

        ivldCpiImpl.setIvldCpiSid(ivldCpiSid);

        if (errorField == null) {
            ivldCpiImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldCpiImpl.setErrorField(errorField);
        }

        if (errorCode == null) {
            ivldCpiImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldCpiImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldCpiImpl.setIntfInsertedDate(null);
        } else {
            ivldCpiImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldCpiImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldCpiImpl.setModifiedBy(modifiedBy);
        }

        if (reprocessedFlag == null) {
            ivldCpiImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldCpiImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (indexId == null) {
            ivldCpiImpl.setIndexId(StringPool.BLANK);
        } else {
            ivldCpiImpl.setIndexId(indexId);
        }

        ivldCpiImpl.setCheckRecord(checkRecord);

        ivldCpiImpl.resetOriginalValues();

        return ivldCpiImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        effectiveDate = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        indexType = objectInput.readUTF();
        status = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        cpiIntfid = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        indexValue = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        batchId = objectInput.readUTF();
        ivldCpiSid = objectInput.readInt();
        errorField = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        indexId = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (effectiveDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(effectiveDate);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (indexType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(indexType);
        }

        if (status == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(status);
        }

        objectOutput.writeLong(modifiedDate);

        if (cpiIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cpiIntfid);
        }

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

        if (indexValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(indexValue);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(ivldCpiSid);

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

        if (indexId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(indexId);
        }

        objectOutput.writeBoolean(checkRecord);
    }
}
