package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.IvldCompanyTradeClass;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldCompanyTradeClass in entity cache.
 *
 * @author
 * @see IvldCompanyTradeClass
 * @generated
 */
public class IvldCompanyTradeClassCacheModel implements CacheModel<IvldCompanyTradeClass>,
    Externalizable {
    public int ivldCompanyTradeClassSid;
    public String priorTradeClass;
    public String reasonForFailure;
    public String companyId;
    public String lastUpdatedDate;
    public String priorTradeClassStartDate;
    public long modifiedDate;
    public String tradeClassEndDate;
    public String tradeClassIntfid;
    public String tradeClassStartDate;
    public String source;
    public String createdBy;
    public long createdDate;
    public String addChgDelIndicator;
    public String batchId;
    public String errorField;
    public String errorCode;
    public String tradeClass;
    public long intfInsertedDate;
    public String modifiedBy;
    public String reprocessedFlag;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(45);

        sb.append("{ivldCompanyTradeClassSid=");
        sb.append(ivldCompanyTradeClassSid);
        sb.append(", priorTradeClass=");
        sb.append(priorTradeClass);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", lastUpdatedDate=");
        sb.append(lastUpdatedDate);
        sb.append(", priorTradeClassStartDate=");
        sb.append(priorTradeClassStartDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", tradeClassEndDate=");
        sb.append(tradeClassEndDate);
        sb.append(", tradeClassIntfid=");
        sb.append(tradeClassIntfid);
        sb.append(", tradeClassStartDate=");
        sb.append(tradeClassStartDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", tradeClass=");
        sb.append(tradeClass);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldCompanyTradeClass toEntityModel() {
        IvldCompanyTradeClassImpl ivldCompanyTradeClassImpl = new IvldCompanyTradeClassImpl();

        ivldCompanyTradeClassImpl.setIvldCompanyTradeClassSid(ivldCompanyTradeClassSid);

        if (priorTradeClass == null) {
            ivldCompanyTradeClassImpl.setPriorTradeClass(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setPriorTradeClass(priorTradeClass);
        }

        if (reasonForFailure == null) {
            ivldCompanyTradeClassImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setReasonForFailure(reasonForFailure);
        }

        if (companyId == null) {
            ivldCompanyTradeClassImpl.setCompanyId(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setCompanyId(companyId);
        }

        if (lastUpdatedDate == null) {
            ivldCompanyTradeClassImpl.setLastUpdatedDate(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setLastUpdatedDate(lastUpdatedDate);
        }

        if (priorTradeClassStartDate == null) {
            ivldCompanyTradeClassImpl.setPriorTradeClassStartDate(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldCompanyTradeClassImpl.setModifiedDate(null);
        } else {
            ivldCompanyTradeClassImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (tradeClassEndDate == null) {
            ivldCompanyTradeClassImpl.setTradeClassEndDate(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setTradeClassEndDate(tradeClassEndDate);
        }

        if (tradeClassIntfid == null) {
            ivldCompanyTradeClassImpl.setTradeClassIntfid(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setTradeClassIntfid(tradeClassIntfid);
        }

        if (tradeClassStartDate == null) {
            ivldCompanyTradeClassImpl.setTradeClassStartDate(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setTradeClassStartDate(tradeClassStartDate);
        }

        if (source == null) {
            ivldCompanyTradeClassImpl.setSource(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setSource(source);
        }

        if (createdBy == null) {
            ivldCompanyTradeClassImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldCompanyTradeClassImpl.setCreatedDate(null);
        } else {
            ivldCompanyTradeClassImpl.setCreatedDate(new Date(createdDate));
        }

        if (addChgDelIndicator == null) {
            ivldCompanyTradeClassImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (batchId == null) {
            ivldCompanyTradeClassImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setBatchId(batchId);
        }

        if (errorField == null) {
            ivldCompanyTradeClassImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setErrorField(errorField);
        }

        if (errorCode == null) {
            ivldCompanyTradeClassImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setErrorCode(errorCode);
        }

        if (tradeClass == null) {
            ivldCompanyTradeClassImpl.setTradeClass(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setTradeClass(tradeClass);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldCompanyTradeClassImpl.setIntfInsertedDate(null);
        } else {
            ivldCompanyTradeClassImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldCompanyTradeClassImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setModifiedBy(modifiedBy);
        }

        if (reprocessedFlag == null) {
            ivldCompanyTradeClassImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldCompanyTradeClassImpl.setReprocessedFlag(reprocessedFlag);
        }

        ivldCompanyTradeClassImpl.setCheckRecord(checkRecord);

        ivldCompanyTradeClassImpl.resetOriginalValues();

        return ivldCompanyTradeClassImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ivldCompanyTradeClassSid = objectInput.readInt();
        priorTradeClass = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        companyId = objectInput.readUTF();
        lastUpdatedDate = objectInput.readUTF();
        priorTradeClassStartDate = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        tradeClassEndDate = objectInput.readUTF();
        tradeClassIntfid = objectInput.readUTF();
        tradeClassStartDate = objectInput.readUTF();
        source = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        addChgDelIndicator = objectInput.readUTF();
        batchId = objectInput.readUTF();
        errorField = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        tradeClass = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(ivldCompanyTradeClassSid);

        if (priorTradeClass == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priorTradeClass);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        if (lastUpdatedDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lastUpdatedDate);
        }

        if (priorTradeClassStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priorTradeClassStartDate);
        }

        objectOutput.writeLong(modifiedDate);

        if (tradeClassEndDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tradeClassEndDate);
        }

        if (tradeClassIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tradeClassIntfid);
        }

        if (tradeClassStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tradeClassStartDate);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeLong(createdDate);

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

        if (tradeClass == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tradeClass);
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

        objectOutput.writeBoolean(checkRecord);
    }
}
