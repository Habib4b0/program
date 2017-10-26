package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.IvldCompanyParent;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldCompanyParent in entity cache.
 *
 * @author
 * @see IvldCompanyParent
 * @generated
 */
public class IvldCompanyParentCacheModel implements CacheModel<IvldCompanyParent>,
    Externalizable {
    public String parentCompanyId;
    public String priorParentCompanyId;
    public String reasonForFailure;
    public String companyId;
    public String lastUpdatedDate;
    public String parentEndDate;
    public long modifiedDate;
    public String parentDetailsIntfid;
    public String priorParentStartDate;
    public String source;
    public String createdBy;
    public long createdDate;
    public String addChgDelIndicator;
    public String batchId;
    public int ivldCompanyParentSid;
    public String errorField;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String reprocessedFlag;
    public String parentStartDate;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(45);

        sb.append("{parentCompanyId=");
        sb.append(parentCompanyId);
        sb.append(", priorParentCompanyId=");
        sb.append(priorParentCompanyId);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", lastUpdatedDate=");
        sb.append(lastUpdatedDate);
        sb.append(", parentEndDate=");
        sb.append(parentEndDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", parentDetailsIntfid=");
        sb.append(parentDetailsIntfid);
        sb.append(", priorParentStartDate=");
        sb.append(priorParentStartDate);
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
        sb.append(", ivldCompanyParentSid=");
        sb.append(ivldCompanyParentSid);
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
        sb.append(", parentStartDate=");
        sb.append(parentStartDate);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldCompanyParent toEntityModel() {
        IvldCompanyParentImpl ivldCompanyParentImpl = new IvldCompanyParentImpl();

        if (parentCompanyId == null) {
            ivldCompanyParentImpl.setParentCompanyId(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setParentCompanyId(parentCompanyId);
        }

        if (priorParentCompanyId == null) {
            ivldCompanyParentImpl.setPriorParentCompanyId(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setPriorParentCompanyId(priorParentCompanyId);
        }

        if (reasonForFailure == null) {
            ivldCompanyParentImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setReasonForFailure(reasonForFailure);
        }

        if (companyId == null) {
            ivldCompanyParentImpl.setCompanyId(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setCompanyId(companyId);
        }

        if (lastUpdatedDate == null) {
            ivldCompanyParentImpl.setLastUpdatedDate(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setLastUpdatedDate(lastUpdatedDate);
        }

        if (parentEndDate == null) {
            ivldCompanyParentImpl.setParentEndDate(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setParentEndDate(parentEndDate);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldCompanyParentImpl.setModifiedDate(null);
        } else {
            ivldCompanyParentImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (parentDetailsIntfid == null) {
            ivldCompanyParentImpl.setParentDetailsIntfid(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setParentDetailsIntfid(parentDetailsIntfid);
        }

        if (priorParentStartDate == null) {
            ivldCompanyParentImpl.setPriorParentStartDate(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setPriorParentStartDate(priorParentStartDate);
        }

        if (source == null) {
            ivldCompanyParentImpl.setSource(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setSource(source);
        }

        if (createdBy == null) {
            ivldCompanyParentImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldCompanyParentImpl.setCreatedDate(null);
        } else {
            ivldCompanyParentImpl.setCreatedDate(new Date(createdDate));
        }

        if (addChgDelIndicator == null) {
            ivldCompanyParentImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (batchId == null) {
            ivldCompanyParentImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setBatchId(batchId);
        }

        ivldCompanyParentImpl.setIvldCompanyParentSid(ivldCompanyParentSid);

        if (errorField == null) {
            ivldCompanyParentImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setErrorField(errorField);
        }

        if (errorCode == null) {
            ivldCompanyParentImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldCompanyParentImpl.setIntfInsertedDate(null);
        } else {
            ivldCompanyParentImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldCompanyParentImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setModifiedBy(modifiedBy);
        }

        if (reprocessedFlag == null) {
            ivldCompanyParentImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (parentStartDate == null) {
            ivldCompanyParentImpl.setParentStartDate(StringPool.BLANK);
        } else {
            ivldCompanyParentImpl.setParentStartDate(parentStartDate);
        }

        ivldCompanyParentImpl.setCheckRecord(checkRecord);

        ivldCompanyParentImpl.resetOriginalValues();

        return ivldCompanyParentImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        parentCompanyId = objectInput.readUTF();
        priorParentCompanyId = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        companyId = objectInput.readUTF();
        lastUpdatedDate = objectInput.readUTF();
        parentEndDate = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        parentDetailsIntfid = objectInput.readUTF();
        priorParentStartDate = objectInput.readUTF();
        source = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        addChgDelIndicator = objectInput.readUTF();
        batchId = objectInput.readUTF();
        ivldCompanyParentSid = objectInput.readInt();
        errorField = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        parentStartDate = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (parentCompanyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentCompanyId);
        }

        if (priorParentCompanyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priorParentCompanyId);
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

        if (parentEndDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentEndDate);
        }

        objectOutput.writeLong(modifiedDate);

        if (parentDetailsIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentDetailsIntfid);
        }

        if (priorParentStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priorParentStartDate);
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

        objectOutput.writeInt(ivldCompanyParentSid);

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

        if (parentStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentStartDate);
        }

        objectOutput.writeBoolean(checkRecord);
    }
}
