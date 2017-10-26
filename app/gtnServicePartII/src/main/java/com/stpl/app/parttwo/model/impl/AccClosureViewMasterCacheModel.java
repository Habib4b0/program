package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AccClosureViewMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccClosureViewMaster in entity cache.
 *
 * @author
 * @see AccClosureViewMaster
 * @generated
 */
public class AccClosureViewMasterCacheModel implements CacheModel<AccClosureViewMaster>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public String viewType;
    public int accClosureMasterSid;
    public int modifiedBy;
    public int accClosureViewMasterSid;
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
        sb.append(", accClosureMasterSid=");
        sb.append(accClosureMasterSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", accClosureViewMasterSid=");
        sb.append(accClosureViewMasterSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", viewName=");
        sb.append(viewName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public AccClosureViewMaster toEntityModel() {
        AccClosureViewMasterImpl accClosureViewMasterImpl = new AccClosureViewMasterImpl();

        if (createdDate == Long.MIN_VALUE) {
            accClosureViewMasterImpl.setCreatedDate(null);
        } else {
            accClosureViewMasterImpl.setCreatedDate(new Date(createdDate));
        }

        accClosureViewMasterImpl.setCreatedBy(createdBy);

        if (viewType == null) {
            accClosureViewMasterImpl.setViewType(StringPool.BLANK);
        } else {
            accClosureViewMasterImpl.setViewType(viewType);
        }

        accClosureViewMasterImpl.setAccClosureMasterSid(accClosureMasterSid);
        accClosureViewMasterImpl.setModifiedBy(modifiedBy);
        accClosureViewMasterImpl.setAccClosureViewMasterSid(accClosureViewMasterSid);

        if (modifiedDate == Long.MIN_VALUE) {
            accClosureViewMasterImpl.setModifiedDate(null);
        } else {
            accClosureViewMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (viewName == null) {
            accClosureViewMasterImpl.setViewName(StringPool.BLANK);
        } else {
            accClosureViewMasterImpl.setViewName(viewName);
        }

        accClosureViewMasterImpl.resetOriginalValues();

        return accClosureViewMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        viewType = objectInput.readUTF();
        accClosureMasterSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        accClosureViewMasterSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        viewName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (viewType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(viewType);
        }

        objectOutput.writeInt(accClosureMasterSid);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeInt(accClosureViewMasterSid);
        objectOutput.writeLong(modifiedDate);

        if (viewName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(viewName);
        }
    }
}
