package com.stpl.app.model.impl;

import com.stpl.app.model.CompanyIdentifier;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyIdentifier in entity cache.
 *
 * @author
 * @see CompanyIdentifier
 * @generated
 */
public class CompanyIdentifierCacheModel implements CacheModel<CompanyIdentifier>,
    Externalizable {
    public long endDate;
    public int companyIdentifierSid;
    public long modifiedDate;
    public int identifierStatus;
    public String entityCode;
    public boolean recordLockStatus;
    public long startDate;
    public long createdDate;
    public String source;
    public int createdBy;
    public String companyIdentifierValue;
    public String batchId;
    public int companyQualifierSid;
    public int modifiedBy;
    public String inboundStatus;
    public int companyMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(33);

        sb.append("{endDate=");
        sb.append(endDate);
        sb.append(", companyIdentifierSid=");
        sb.append(companyIdentifierSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", identifierStatus=");
        sb.append(identifierStatus);
        sb.append(", entityCode=");
        sb.append(entityCode);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", companyIdentifierValue=");
        sb.append(companyIdentifierValue);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", companyQualifierSid=");
        sb.append(companyQualifierSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CompanyIdentifier toEntityModel() {
        CompanyIdentifierImpl companyIdentifierImpl = new CompanyIdentifierImpl();

        if (endDate == Long.MIN_VALUE) {
            companyIdentifierImpl.setEndDate(null);
        } else {
            companyIdentifierImpl.setEndDate(new Date(endDate));
        }

        companyIdentifierImpl.setCompanyIdentifierSid(companyIdentifierSid);

        if (modifiedDate == Long.MIN_VALUE) {
            companyIdentifierImpl.setModifiedDate(null);
        } else {
            companyIdentifierImpl.setModifiedDate(new Date(modifiedDate));
        }

        companyIdentifierImpl.setIdentifierStatus(identifierStatus);

        if (entityCode == null) {
            companyIdentifierImpl.setEntityCode(StringPool.BLANK);
        } else {
            companyIdentifierImpl.setEntityCode(entityCode);
        }

        companyIdentifierImpl.setRecordLockStatus(recordLockStatus);

        if (startDate == Long.MIN_VALUE) {
            companyIdentifierImpl.setStartDate(null);
        } else {
            companyIdentifierImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            companyIdentifierImpl.setCreatedDate(null);
        } else {
            companyIdentifierImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            companyIdentifierImpl.setSource(StringPool.BLANK);
        } else {
            companyIdentifierImpl.setSource(source);
        }

        companyIdentifierImpl.setCreatedBy(createdBy);

        if (companyIdentifierValue == null) {
            companyIdentifierImpl.setCompanyIdentifierValue(StringPool.BLANK);
        } else {
            companyIdentifierImpl.setCompanyIdentifierValue(companyIdentifierValue);
        }

        if (batchId == null) {
            companyIdentifierImpl.setBatchId(StringPool.BLANK);
        } else {
            companyIdentifierImpl.setBatchId(batchId);
        }

        companyIdentifierImpl.setCompanyQualifierSid(companyQualifierSid);
        companyIdentifierImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            companyIdentifierImpl.setInboundStatus(StringPool.BLANK);
        } else {
            companyIdentifierImpl.setInboundStatus(inboundStatus);
        }

        companyIdentifierImpl.setCompanyMasterSid(companyMasterSid);

        companyIdentifierImpl.resetOriginalValues();

        return companyIdentifierImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        endDate = objectInput.readLong();
        companyIdentifierSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        identifierStatus = objectInput.readInt();
        entityCode = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdBy = objectInput.readInt();
        companyIdentifierValue = objectInput.readUTF();
        batchId = objectInput.readUTF();
        companyQualifierSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        companyMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(endDate);
        objectOutput.writeInt(companyIdentifierSid);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(identifierStatus);

        if (entityCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(entityCode);
        }

        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(startDate);
        objectOutput.writeLong(createdDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(createdBy);

        if (companyIdentifierValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyIdentifierValue);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(companyQualifierSid);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(companyMasterSid);
    }
}
