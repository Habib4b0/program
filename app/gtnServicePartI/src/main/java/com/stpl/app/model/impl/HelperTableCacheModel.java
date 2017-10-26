package com.stpl.app.model.impl;

import com.stpl.app.model.HelperTable;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HelperTable in entity cache.
 *
 * @author
 * @see HelperTable
 * @generated
 */
public class HelperTableCacheModel implements CacheModel<HelperTable>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public String description;
    public int refCount;
    public String listName;
    public int helperTableSid;
    public int modifiedBy;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", description=");
        sb.append(description);
        sb.append(", refCount=");
        sb.append(refCount);
        sb.append(", listName=");
        sb.append(listName);
        sb.append(", helperTableSid=");
        sb.append(helperTableSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public HelperTable toEntityModel() {
        HelperTableImpl helperTableImpl = new HelperTableImpl();

        if (createdDate == Long.MIN_VALUE) {
            helperTableImpl.setCreatedDate(null);
        } else {
            helperTableImpl.setCreatedDate(new Date(createdDate));
        }

        helperTableImpl.setCreatedBy(createdBy);

        if (description == null) {
            helperTableImpl.setDescription(StringPool.BLANK);
        } else {
            helperTableImpl.setDescription(description);
        }

        helperTableImpl.setRefCount(refCount);

        if (listName == null) {
            helperTableImpl.setListName(StringPool.BLANK);
        } else {
            helperTableImpl.setListName(listName);
        }

        helperTableImpl.setHelperTableSid(helperTableSid);
        helperTableImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            helperTableImpl.setModifiedDate(null);
        } else {
            helperTableImpl.setModifiedDate(new Date(modifiedDate));
        }

        helperTableImpl.resetOriginalValues();

        return helperTableImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        description = objectInput.readUTF();
        refCount = objectInput.readInt();
        listName = objectInput.readUTF();
        helperTableSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        objectOutput.writeInt(refCount);

        if (listName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(listName);
        }

        objectOutput.writeInt(helperTableSid);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);
    }
}
