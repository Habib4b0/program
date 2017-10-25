package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.VwCompanyIdentifier;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwCompanyIdentifier in entity cache.
 *
 * @author
 * @see VwCompanyIdentifier
 * @generated
 */
public class VwCompanyIdentifierCacheModel implements CacheModel<VwCompanyIdentifier>,
    Externalizable {
    public String companyId;
    public String companyName;
    public long endDate;
    public int companyIdentifierSid;
    public long modifiedDate;
    public String identifierStatus;
    public String companyIdentifier;
    public String entityCode;
    public long startDate;
    public long createdDate;
    public String createdBy;
    public String source;
    public String companyNo;
    public String batchId;
    public String addChgDelIndicator;
    public String identifierCodeQualifierName;
    public String modifiedBy;
    public String identifierCodeQualifier;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{companyId=");
        sb.append(companyId);
        sb.append(", companyName=");
        sb.append(companyName);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", companyIdentifierSid=");
        sb.append(companyIdentifierSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", identifierStatus=");
        sb.append(identifierStatus);
        sb.append(", companyIdentifier=");
        sb.append(companyIdentifier);
        sb.append(", entityCode=");
        sb.append(entityCode);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", companyNo=");
        sb.append(companyNo);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", identifierCodeQualifierName=");
        sb.append(identifierCodeQualifierName);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", identifierCodeQualifier=");
        sb.append(identifierCodeQualifier);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwCompanyIdentifier toEntityModel() {
        VwCompanyIdentifierImpl vwCompanyIdentifierImpl = new VwCompanyIdentifierImpl();

        if (companyId == null) {
            vwCompanyIdentifierImpl.setCompanyId(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setCompanyId(companyId);
        }

        if (companyName == null) {
            vwCompanyIdentifierImpl.setCompanyName(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setCompanyName(companyName);
        }

        if (endDate == Long.MIN_VALUE) {
            vwCompanyIdentifierImpl.setEndDate(null);
        } else {
            vwCompanyIdentifierImpl.setEndDate(new Date(endDate));
        }

        vwCompanyIdentifierImpl.setCompanyIdentifierSid(companyIdentifierSid);

        if (modifiedDate == Long.MIN_VALUE) {
            vwCompanyIdentifierImpl.setModifiedDate(null);
        } else {
            vwCompanyIdentifierImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (identifierStatus == null) {
            vwCompanyIdentifierImpl.setIdentifierStatus(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setIdentifierStatus(identifierStatus);
        }

        if (companyIdentifier == null) {
            vwCompanyIdentifierImpl.setCompanyIdentifier(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setCompanyIdentifier(companyIdentifier);
        }

        if (entityCode == null) {
            vwCompanyIdentifierImpl.setEntityCode(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setEntityCode(entityCode);
        }

        if (startDate == Long.MIN_VALUE) {
            vwCompanyIdentifierImpl.setStartDate(null);
        } else {
            vwCompanyIdentifierImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            vwCompanyIdentifierImpl.setCreatedDate(null);
        } else {
            vwCompanyIdentifierImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            vwCompanyIdentifierImpl.setCreatedBy(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setCreatedBy(createdBy);
        }

        if (source == null) {
            vwCompanyIdentifierImpl.setSource(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setSource(source);
        }

        if (companyNo == null) {
            vwCompanyIdentifierImpl.setCompanyNo(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setCompanyNo(companyNo);
        }

        if (batchId == null) {
            vwCompanyIdentifierImpl.setBatchId(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setBatchId(batchId);
        }

        if (addChgDelIndicator == null) {
            vwCompanyIdentifierImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (identifierCodeQualifierName == null) {
            vwCompanyIdentifierImpl.setIdentifierCodeQualifierName(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        if (modifiedBy == null) {
            vwCompanyIdentifierImpl.setModifiedBy(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setModifiedBy(modifiedBy);
        }

        if (identifierCodeQualifier == null) {
            vwCompanyIdentifierImpl.setIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            vwCompanyIdentifierImpl.setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        vwCompanyIdentifierImpl.resetOriginalValues();

        return vwCompanyIdentifierImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        companyId = objectInput.readUTF();
        companyName = objectInput.readUTF();
        endDate = objectInput.readLong();
        companyIdentifierSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        identifierStatus = objectInput.readUTF();
        companyIdentifier = objectInput.readUTF();
        entityCode = objectInput.readUTF();
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        source = objectInput.readUTF();
        companyNo = objectInput.readUTF();
        batchId = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        identifierCodeQualifierName = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        identifierCodeQualifier = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        if (companyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyName);
        }

        objectOutput.writeLong(endDate);
        objectOutput.writeInt(companyIdentifierSid);
        objectOutput.writeLong(modifiedDate);

        if (identifierStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(identifierStatus);
        }

        if (companyIdentifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyIdentifier);
        }

        if (entityCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(entityCode);
        }

        objectOutput.writeLong(startDate);
        objectOutput.writeLong(createdDate);

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (companyNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyNo);
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

        if (identifierCodeQualifierName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(identifierCodeQualifierName);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (identifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(identifierCodeQualifier);
        }
    }
}
