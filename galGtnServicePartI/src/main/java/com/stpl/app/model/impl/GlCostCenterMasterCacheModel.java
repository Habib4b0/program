package com.stpl.app.model.impl;

import com.stpl.app.model.GlCostCenterMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GlCostCenterMaster in entity cache.
 *
 * @author
 * @see GlCostCenterMaster
 * @generated
 */
public class GlCostCenterMasterCacheModel implements CacheModel<GlCostCenterMaster>,
    Externalizable {
    public int createdBy;
    public int modifiedBy;
    public long uploadDate;
    public long createdDate;
    public int glCostCenterMasterSid;
    public String batchId;
    public long modifiedDate;
    public String ndc8;
    public boolean recordLockStatus;
    public String companyCode;
    public String source;
    public String companyCostCenter;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", uploadDate=");
        sb.append(uploadDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", glCostCenterMasterSid=");
        sb.append(glCostCenterMasterSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", ndc8=");
        sb.append(ndc8);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", companyCode=");
        sb.append(companyCode);
        sb.append(", source=");
        sb.append(source);
        sb.append(", companyCostCenter=");
        sb.append(companyCostCenter);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public GlCostCenterMaster toEntityModel() {
        GlCostCenterMasterImpl glCostCenterMasterImpl = new GlCostCenterMasterImpl();

        glCostCenterMasterImpl.setCreatedBy(createdBy);
        glCostCenterMasterImpl.setModifiedBy(modifiedBy);

        if (uploadDate == Long.MIN_VALUE) {
            glCostCenterMasterImpl.setUploadDate(null);
        } else {
            glCostCenterMasterImpl.setUploadDate(new Date(uploadDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            glCostCenterMasterImpl.setCreatedDate(null);
        } else {
            glCostCenterMasterImpl.setCreatedDate(new Date(createdDate));
        }

        glCostCenterMasterImpl.setGlCostCenterMasterSid(glCostCenterMasterSid);

        if (batchId == null) {
            glCostCenterMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            glCostCenterMasterImpl.setBatchId(batchId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            glCostCenterMasterImpl.setModifiedDate(null);
        } else {
            glCostCenterMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (ndc8 == null) {
            glCostCenterMasterImpl.setNdc8(StringPool.BLANK);
        } else {
            glCostCenterMasterImpl.setNdc8(ndc8);
        }

        glCostCenterMasterImpl.setRecordLockStatus(recordLockStatus);

        if (companyCode == null) {
            glCostCenterMasterImpl.setCompanyCode(StringPool.BLANK);
        } else {
            glCostCenterMasterImpl.setCompanyCode(companyCode);
        }

        if (source == null) {
            glCostCenterMasterImpl.setSource(StringPool.BLANK);
        } else {
            glCostCenterMasterImpl.setSource(source);
        }

        if (companyCostCenter == null) {
            glCostCenterMasterImpl.setCompanyCostCenter(StringPool.BLANK);
        } else {
            glCostCenterMasterImpl.setCompanyCostCenter(companyCostCenter);
        }

        if (inboundStatus == null) {
            glCostCenterMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            glCostCenterMasterImpl.setInboundStatus(inboundStatus);
        }

        glCostCenterMasterImpl.resetOriginalValues();

        return glCostCenterMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        uploadDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        glCostCenterMasterSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        ndc8 = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        companyCode = objectInput.readUTF();
        source = objectInput.readUTF();
        companyCostCenter = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(uploadDate);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(glCostCenterMasterSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeLong(modifiedDate);

        if (ndc8 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ndc8);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (companyCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyCode);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (companyCostCenter == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyCostCenter);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
