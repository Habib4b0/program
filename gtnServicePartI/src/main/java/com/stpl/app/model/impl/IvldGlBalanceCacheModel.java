package com.stpl.app.model.impl;

import com.stpl.app.model.IvldGlBalance;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldGlBalance in entity cache.
 *
 * @author
 * @see IvldGlBalance
 * @generated
 */
public class IvldGlBalanceCacheModel implements CacheModel<IvldGlBalance>,
    Externalizable {
    public String balance;
    public String accountNo;
    public String reasonForFailure;
    public String accountId;
    public String year;
    public String period;
    public long modifiedDate;
    public String isActive;
    public String source;
    public String uploadDate;
    public String createdBy;
    public long createdDate;
    public String addChgDelIndicator;
    public String batchId;
    public String closeIndicator;
    public String insertedDate;
    public String errorField;
    public int ivldGlBalanceSid;
    public String errorCode;
    public String glBalanceIntfid;
    public long intfInsertedDate;
    public String modifiedBy;
    public String reprocessedFlag;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(49);

        sb.append("{balance=");
        sb.append(balance);
        sb.append(", accountNo=");
        sb.append(accountNo);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", accountId=");
        sb.append(accountId);
        sb.append(", year=");
        sb.append(year);
        sb.append(", period=");
        sb.append(period);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append(", source=");
        sb.append(source);
        sb.append(", uploadDate=");
        sb.append(uploadDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", closeIndicator=");
        sb.append(closeIndicator);
        sb.append(", insertedDate=");
        sb.append(insertedDate);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", ivldGlBalanceSid=");
        sb.append(ivldGlBalanceSid);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", glBalanceIntfid=");
        sb.append(glBalanceIntfid);
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
    public IvldGlBalance toEntityModel() {
        IvldGlBalanceImpl ivldGlBalanceImpl = new IvldGlBalanceImpl();

        if (balance == null) {
            ivldGlBalanceImpl.setBalance(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setBalance(balance);
        }

        if (accountNo == null) {
            ivldGlBalanceImpl.setAccountNo(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setAccountNo(accountNo);
        }

        if (reasonForFailure == null) {
            ivldGlBalanceImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setReasonForFailure(reasonForFailure);
        }

        if (accountId == null) {
            ivldGlBalanceImpl.setAccountId(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setAccountId(accountId);
        }

        if (year == null) {
            ivldGlBalanceImpl.setYear(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setYear(year);
        }

        if (period == null) {
            ivldGlBalanceImpl.setPeriod(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setPeriod(period);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldGlBalanceImpl.setModifiedDate(null);
        } else {
            ivldGlBalanceImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (isActive == null) {
            ivldGlBalanceImpl.setIsActive(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setIsActive(isActive);
        }

        if (source == null) {
            ivldGlBalanceImpl.setSource(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setSource(source);
        }

        if (uploadDate == null) {
            ivldGlBalanceImpl.setUploadDate(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setUploadDate(uploadDate);
        }

        if (createdBy == null) {
            ivldGlBalanceImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldGlBalanceImpl.setCreatedDate(null);
        } else {
            ivldGlBalanceImpl.setCreatedDate(new Date(createdDate));
        }

        if (addChgDelIndicator == null) {
            ivldGlBalanceImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (batchId == null) {
            ivldGlBalanceImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setBatchId(batchId);
        }

        if (closeIndicator == null) {
            ivldGlBalanceImpl.setCloseIndicator(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setCloseIndicator(closeIndicator);
        }

        if (insertedDate == null) {
            ivldGlBalanceImpl.setInsertedDate(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setInsertedDate(insertedDate);
        }

        if (errorField == null) {
            ivldGlBalanceImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setErrorField(errorField);
        }

        ivldGlBalanceImpl.setIvldGlBalanceSid(ivldGlBalanceSid);

        if (errorCode == null) {
            ivldGlBalanceImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setErrorCode(errorCode);
        }

        if (glBalanceIntfid == null) {
            ivldGlBalanceImpl.setGlBalanceIntfid(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setGlBalanceIntfid(glBalanceIntfid);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldGlBalanceImpl.setIntfInsertedDate(null);
        } else {
            ivldGlBalanceImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldGlBalanceImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setModifiedBy(modifiedBy);
        }

        if (reprocessedFlag == null) {
            ivldGlBalanceImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldGlBalanceImpl.setReprocessedFlag(reprocessedFlag);
        }

        ivldGlBalanceImpl.setCheckRecord(checkRecord);

        ivldGlBalanceImpl.resetOriginalValues();

        return ivldGlBalanceImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        balance = objectInput.readUTF();
        accountNo = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        accountId = objectInput.readUTF();
        year = objectInput.readUTF();
        period = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        isActive = objectInput.readUTF();
        source = objectInput.readUTF();
        uploadDate = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        addChgDelIndicator = objectInput.readUTF();
        batchId = objectInput.readUTF();
        closeIndicator = objectInput.readUTF();
        insertedDate = objectInput.readUTF();
        errorField = objectInput.readUTF();
        ivldGlBalanceSid = objectInput.readInt();
        errorCode = objectInput.readUTF();
        glBalanceIntfid = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (balance == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(balance);
        }

        if (accountNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountNo);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        if (year == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(year);
        }

        if (period == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(period);
        }

        objectOutput.writeLong(modifiedDate);

        if (isActive == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isActive);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (uploadDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uploadDate);
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

        if (closeIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(closeIndicator);
        }

        if (insertedDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(insertedDate);
        }

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        objectOutput.writeInt(ivldGlBalanceSid);

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        if (glBalanceIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(glBalanceIntfid);
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
