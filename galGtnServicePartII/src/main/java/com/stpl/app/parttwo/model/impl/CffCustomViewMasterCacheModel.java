package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffCustomViewMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CffCustomViewMaster in entity cache.
 *
 * @author
 * @see CffCustomViewMaster
 * @generated
 */
public class CffCustomViewMasterCacheModel implements CacheModel<CffCustomViewMaster>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public int cffMasterSid;
    public int modifiedBy;
    public int cffCustomViewMasterSid;
    public long modifiedDate;
    public String viewName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", cffMasterSid=");
        sb.append(cffMasterSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", cffCustomViewMasterSid=");
        sb.append(cffCustomViewMasterSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", viewName=");
        sb.append(viewName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffCustomViewMaster toEntityModel() {
        CffCustomViewMasterImpl cffCustomViewMasterImpl = new CffCustomViewMasterImpl();

        if (createdDate == Long.MIN_VALUE) {
            cffCustomViewMasterImpl.setCreatedDate(null);
        } else {
            cffCustomViewMasterImpl.setCreatedDate(new Date(createdDate));
        }

        cffCustomViewMasterImpl.setCreatedBy(createdBy);
        cffCustomViewMasterImpl.setCffMasterSid(cffMasterSid);
        cffCustomViewMasterImpl.setModifiedBy(modifiedBy);
        cffCustomViewMasterImpl.setCffCustomViewMasterSid(cffCustomViewMasterSid);

        if (modifiedDate == Long.MIN_VALUE) {
            cffCustomViewMasterImpl.setModifiedDate(null);
        } else {
            cffCustomViewMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (viewName == null) {
            cffCustomViewMasterImpl.setViewName(StringPool.BLANK);
        } else {
            cffCustomViewMasterImpl.setViewName(viewName);
        }

        cffCustomViewMasterImpl.resetOriginalValues();

        return cffCustomViewMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        cffMasterSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        cffCustomViewMasterSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        viewName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(cffMasterSid);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeInt(cffCustomViewMasterSid);
        objectOutput.writeLong(modifiedDate);

        if (viewName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(viewName);
        }
    }
}
