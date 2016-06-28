package com.stpl.app.model.impl;

import com.stpl.app.model.CustomViewMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CustomViewMaster in entity cache.
 *
 * @author
 * @see CustomViewMaster
 * @generated
 */
public class CustomViewMasterCacheModel implements CacheModel<CustomViewMaster>,
    Externalizable {
    public int customViewMasterSid;
    public long createdDate;
    public int createdBy;
    public int projectionMasterSid;
    public int modifiedBy;
    public long modifiedDate;
    public String viewName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{customViewMasterSid=");
        sb.append(customViewMasterSid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
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
    public CustomViewMaster toEntityModel() {
        CustomViewMasterImpl customViewMasterImpl = new CustomViewMasterImpl();

        customViewMasterImpl.setCustomViewMasterSid(customViewMasterSid);

        if (createdDate == Long.MIN_VALUE) {
            customViewMasterImpl.setCreatedDate(null);
        } else {
            customViewMasterImpl.setCreatedDate(new Date(createdDate));
        }

        customViewMasterImpl.setCreatedBy(createdBy);
        customViewMasterImpl.setProjectionMasterSid(projectionMasterSid);
        customViewMasterImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            customViewMasterImpl.setModifiedDate(null);
        } else {
            customViewMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (viewName == null) {
            customViewMasterImpl.setViewName(StringPool.BLANK);
        } else {
            customViewMasterImpl.setViewName(viewName);
        }

        customViewMasterImpl.resetOriginalValues();

        return customViewMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        customViewMasterSid = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        projectionMasterSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        viewName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(customViewMasterSid);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(projectionMasterSid);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);

        if (viewName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(viewName);
        }
    }
}
