package com.stpl.app.model.impl;

import com.stpl.app.model.CompanyQualifier;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyQualifier in entity cache.
 *
 * @author
 * @see CompanyQualifier
 * @generated
 */
public class CompanyQualifierCacheModel implements CacheModel<CompanyQualifier>,
    Externalizable {
    public boolean recordLockStatus;
    public long createdDate;
    public int createdBy;
    public String source;
    public String companyQualifierValue;
    public String batchId;
    public int companyQualifierSid;
    public String companyQualifierName;
    public String effectiveDates;
    public String notes;
    public int modifiedBy;
    public String inboundStatus;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", companyQualifierValue=");
        sb.append(companyQualifierValue);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", companyQualifierSid=");
        sb.append(companyQualifierSid);
        sb.append(", companyQualifierName=");
        sb.append(companyQualifierName);
        sb.append(", effectiveDates=");
        sb.append(effectiveDates);
        sb.append(", notes=");
        sb.append(notes);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CompanyQualifier toEntityModel() {
        CompanyQualifierImpl companyQualifierImpl = new CompanyQualifierImpl();

        companyQualifierImpl.setRecordLockStatus(recordLockStatus);

        if (createdDate == Long.MIN_VALUE) {
            companyQualifierImpl.setCreatedDate(null);
        } else {
            companyQualifierImpl.setCreatedDate(new Date(createdDate));
        }

        companyQualifierImpl.setCreatedBy(createdBy);

        if (source == null) {
            companyQualifierImpl.setSource(StringPool.BLANK);
        } else {
            companyQualifierImpl.setSource(source);
        }

        if (companyQualifierValue == null) {
            companyQualifierImpl.setCompanyQualifierValue(StringPool.BLANK);
        } else {
            companyQualifierImpl.setCompanyQualifierValue(companyQualifierValue);
        }

        if (batchId == null) {
            companyQualifierImpl.setBatchId(StringPool.BLANK);
        } else {
            companyQualifierImpl.setBatchId(batchId);
        }

        companyQualifierImpl.setCompanyQualifierSid(companyQualifierSid);

        if (companyQualifierName == null) {
            companyQualifierImpl.setCompanyQualifierName(StringPool.BLANK);
        } else {
            companyQualifierImpl.setCompanyQualifierName(companyQualifierName);
        }

        if (effectiveDates == null) {
            companyQualifierImpl.setEffectiveDates(StringPool.BLANK);
        } else {
            companyQualifierImpl.setEffectiveDates(effectiveDates);
        }

        if (notes == null) {
            companyQualifierImpl.setNotes(StringPool.BLANK);
        } else {
            companyQualifierImpl.setNotes(notes);
        }

        companyQualifierImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            companyQualifierImpl.setInboundStatus(StringPool.BLANK);
        } else {
            companyQualifierImpl.setInboundStatus(inboundStatus);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            companyQualifierImpl.setModifiedDate(null);
        } else {
            companyQualifierImpl.setModifiedDate(new Date(modifiedDate));
        }

        companyQualifierImpl.resetOriginalValues();

        return companyQualifierImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        recordLockStatus = objectInput.readBoolean();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        source = objectInput.readUTF();
        companyQualifierValue = objectInput.readUTF();
        batchId = objectInput.readUTF();
        companyQualifierSid = objectInput.readInt();
        companyQualifierName = objectInput.readUTF();
        effectiveDates = objectInput.readUTF();
        notes = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (companyQualifierValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyQualifierValue);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(companyQualifierSid);

        if (companyQualifierName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyQualifierName);
        }

        if (effectiveDates == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(effectiveDates);
        }

        if (notes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(notes);
        }

        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeLong(modifiedDate);
    }
}
