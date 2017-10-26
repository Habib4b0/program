package com.stpl.app.model.impl;

import com.stpl.app.model.BrandMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BrandMaster in entity cache.
 *
 * @author
 * @see BrandMaster
 * @generated
 */
public class BrandMasterCacheModel implements CacheModel<BrandMaster>,
    Externalizable {
    public int createdBy;
    public int modifiedBy;
    public long createdDate;
    public int brandMasterSid;
    public String batchId;
    public long modifiedDate;
    public String brandId;
    public String displayBrand;
    public boolean recordLockStatus;
    public String brandName;
    public String brandDesc;
    public String source;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", brandMasterSid=");
        sb.append(brandMasterSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", displayBrand=");
        sb.append(displayBrand);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", brandName=");
        sb.append(brandName);
        sb.append(", brandDesc=");
        sb.append(brandDesc);
        sb.append(", source=");
        sb.append(source);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public BrandMaster toEntityModel() {
        BrandMasterImpl brandMasterImpl = new BrandMasterImpl();

        brandMasterImpl.setCreatedBy(createdBy);
        brandMasterImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            brandMasterImpl.setCreatedDate(null);
        } else {
            brandMasterImpl.setCreatedDate(new Date(createdDate));
        }

        brandMasterImpl.setBrandMasterSid(brandMasterSid);

        if (batchId == null) {
            brandMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            brandMasterImpl.setBatchId(batchId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            brandMasterImpl.setModifiedDate(null);
        } else {
            brandMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (brandId == null) {
            brandMasterImpl.setBrandId(StringPool.BLANK);
        } else {
            brandMasterImpl.setBrandId(brandId);
        }

        if (displayBrand == null) {
            brandMasterImpl.setDisplayBrand(StringPool.BLANK);
        } else {
            brandMasterImpl.setDisplayBrand(displayBrand);
        }

        brandMasterImpl.setRecordLockStatus(recordLockStatus);

        if (brandName == null) {
            brandMasterImpl.setBrandName(StringPool.BLANK);
        } else {
            brandMasterImpl.setBrandName(brandName);
        }

        if (brandDesc == null) {
            brandMasterImpl.setBrandDesc(StringPool.BLANK);
        } else {
            brandMasterImpl.setBrandDesc(brandDesc);
        }

        if (source == null) {
            brandMasterImpl.setSource(StringPool.BLANK);
        } else {
            brandMasterImpl.setSource(source);
        }

        if (inboundStatus == null) {
            brandMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            brandMasterImpl.setInboundStatus(inboundStatus);
        }

        brandMasterImpl.resetOriginalValues();

        return brandMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        brandMasterSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        brandId = objectInput.readUTF();
        displayBrand = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        brandName = objectInput.readUTF();
        brandDesc = objectInput.readUTF();
        source = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(brandMasterSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeLong(modifiedDate);

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        if (displayBrand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(displayBrand);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (brandName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandName);
        }

        if (brandDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandDesc);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
