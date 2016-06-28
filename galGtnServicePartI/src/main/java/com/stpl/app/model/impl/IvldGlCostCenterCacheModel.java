package com.stpl.app.model.impl;

import com.stpl.app.model.IvldGlCostCenter;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldGlCostCenter in entity cache.
 *
 * @author
 * @see IvldGlCostCenter
 * @generated
 */
public class IvldGlCostCenterCacheModel implements CacheModel<IvldGlCostCenter>,
    Externalizable {
    public String reasonForFailure;
    public String glCostCenterIntfid;
    public long modifiedDate;
    public String companyCostCenter;
    public String uploadDate;
    public String createdBy;
    public long createdDate;
    public String source;
    public String batchId;
    public String addChgDelIndicator;
    public int ivldGlCostCenterSid;
    public String errorField;
    public String errorCode;
    public long intfInsertedDate;
    public String companyCode;
    public String modifiedBy;
    public String reprocessedFlag;
    public String ndc8;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", glCostCenterIntfid=");
        sb.append(glCostCenterIntfid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", companyCostCenter=");
        sb.append(companyCostCenter);
        sb.append(", uploadDate=");
        sb.append(uploadDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", ivldGlCostCenterSid=");
        sb.append(ivldGlCostCenterSid);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", companyCode=");
        sb.append(companyCode);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", ndc8=");
        sb.append(ndc8);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldGlCostCenter toEntityModel() {
        IvldGlCostCenterImpl ivldGlCostCenterImpl = new IvldGlCostCenterImpl();

        if (reasonForFailure == null) {
            ivldGlCostCenterImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setReasonForFailure(reasonForFailure);
        }

        if (glCostCenterIntfid == null) {
            ivldGlCostCenterImpl.setGlCostCenterIntfid(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setGlCostCenterIntfid(glCostCenterIntfid);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldGlCostCenterImpl.setModifiedDate(null);
        } else {
            ivldGlCostCenterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (companyCostCenter == null) {
            ivldGlCostCenterImpl.setCompanyCostCenter(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setCompanyCostCenter(companyCostCenter);
        }

        if (uploadDate == null) {
            ivldGlCostCenterImpl.setUploadDate(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setUploadDate(uploadDate);
        }

        if (createdBy == null) {
            ivldGlCostCenterImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldGlCostCenterImpl.setCreatedDate(null);
        } else {
            ivldGlCostCenterImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            ivldGlCostCenterImpl.setSource(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setSource(source);
        }

        if (batchId == null) {
            ivldGlCostCenterImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setBatchId(batchId);
        }

        if (addChgDelIndicator == null) {
            ivldGlCostCenterImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        ivldGlCostCenterImpl.setIvldGlCostCenterSid(ivldGlCostCenterSid);

        if (errorField == null) {
            ivldGlCostCenterImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setErrorField(errorField);
        }

        if (errorCode == null) {
            ivldGlCostCenterImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldGlCostCenterImpl.setIntfInsertedDate(null);
        } else {
            ivldGlCostCenterImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (companyCode == null) {
            ivldGlCostCenterImpl.setCompanyCode(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setCompanyCode(companyCode);
        }

        if (modifiedBy == null) {
            ivldGlCostCenterImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setModifiedBy(modifiedBy);
        }

        if (reprocessedFlag == null) {
            ivldGlCostCenterImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (ndc8 == null) {
            ivldGlCostCenterImpl.setNdc8(StringPool.BLANK);
        } else {
            ivldGlCostCenterImpl.setNdc8(ndc8);
        }

        ivldGlCostCenterImpl.resetOriginalValues();

        return ivldGlCostCenterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        reasonForFailure = objectInput.readUTF();
        glCostCenterIntfid = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        companyCostCenter = objectInput.readUTF();
        uploadDate = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        batchId = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        ivldGlCostCenterSid = objectInput.readInt();
        errorField = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        companyCode = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        ndc8 = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (glCostCenterIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(glCostCenterIntfid);
        }

        objectOutput.writeLong(modifiedDate);

        if (companyCostCenter == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyCostCenter);
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

        objectOutput.writeInt(ivldGlCostCenterSid);

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

        if (companyCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyCode);
        }

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

        if (ndc8 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ndc8);
        }
    }
}
