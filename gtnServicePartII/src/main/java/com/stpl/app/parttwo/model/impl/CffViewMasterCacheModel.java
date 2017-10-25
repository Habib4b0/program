package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffViewMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CffViewMaster in entity cache.
 *
 * @author
 * @see CffViewMaster
 * @generated
 */
public class CffViewMasterCacheModel implements CacheModel<CffViewMaster>,
    Externalizable {
    public long createdDate;
    public String createdBy;
    public String viewType;
    public int cffViewMasterSid;
    public int cffMasterSid;
    public String modifiedBy;
    public long modifiedDate;
    public String viewName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", viewType=");
        sb.append(viewType);
        sb.append(", cffViewMasterSid=");
        sb.append(cffViewMasterSid);
        sb.append(", cffMasterSid=");
        sb.append(cffMasterSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", viewName=");
        sb.append(viewName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffViewMaster toEntityModel() {
        CffViewMasterImpl cffViewMasterImpl = new CffViewMasterImpl();

        if (createdDate == Long.MIN_VALUE) {
            cffViewMasterImpl.setCreatedDate(null);
        } else {
            cffViewMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            cffViewMasterImpl.setCreatedBy(StringPool.BLANK);
        } else {
            cffViewMasterImpl.setCreatedBy(createdBy);
        }

        if (viewType == null) {
            cffViewMasterImpl.setViewType(StringPool.BLANK);
        } else {
            cffViewMasterImpl.setViewType(viewType);
        }

        cffViewMasterImpl.setCffViewMasterSid(cffViewMasterSid);
        cffViewMasterImpl.setCffMasterSid(cffMasterSid);

        if (modifiedBy == null) {
            cffViewMasterImpl.setModifiedBy(StringPool.BLANK);
        } else {
            cffViewMasterImpl.setModifiedBy(modifiedBy);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            cffViewMasterImpl.setModifiedDate(null);
        } else {
            cffViewMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (viewName == null) {
            cffViewMasterImpl.setViewName(StringPool.BLANK);
        } else {
            cffViewMasterImpl.setViewName(viewName);
        }

        cffViewMasterImpl.resetOriginalValues();

        return cffViewMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        viewType = objectInput.readUTF();
        cffViewMasterSid = objectInput.readInt();
        cffMasterSid = objectInput.readInt();
        modifiedBy = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        viewName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (viewType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(viewType);
        }

        objectOutput.writeInt(cffViewMasterSid);
        objectOutput.writeInt(cffMasterSid);

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        objectOutput.writeLong(modifiedDate);

        if (viewName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(viewName);
        }
    }
}
