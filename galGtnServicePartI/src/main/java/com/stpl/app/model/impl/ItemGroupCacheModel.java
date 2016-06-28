package com.stpl.app.model.impl;

import com.stpl.app.model.ItemGroup;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemGroup in entity cache.
 *
 * @author
 * @see ItemGroup
 * @generated
 */
public class ItemGroupCacheModel implements CacheModel<ItemGroup>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public String itemGroupNo;
    public int versionNo;
    public String itemFilter;
    public int modifiedBy;
    public String itemGroupDescription;
    public long modifiedDate;
    public String itemGroupName;
    public int itemGroupSid;
    public int companyMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", itemGroupNo=");
        sb.append(itemGroupNo);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", itemFilter=");
        sb.append(itemFilter);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", itemGroupDescription=");
        sb.append(itemGroupDescription);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", itemGroupName=");
        sb.append(itemGroupName);
        sb.append(", itemGroupSid=");
        sb.append(itemGroupSid);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ItemGroup toEntityModel() {
        ItemGroupImpl itemGroupImpl = new ItemGroupImpl();

        if (createdDate == Long.MIN_VALUE) {
            itemGroupImpl.setCreatedDate(null);
        } else {
            itemGroupImpl.setCreatedDate(new Date(createdDate));
        }

        itemGroupImpl.setCreatedBy(createdBy);

        if (itemGroupNo == null) {
            itemGroupImpl.setItemGroupNo(StringPool.BLANK);
        } else {
            itemGroupImpl.setItemGroupNo(itemGroupNo);
        }

        itemGroupImpl.setVersionNo(versionNo);

        if (itemFilter == null) {
            itemGroupImpl.setItemFilter(StringPool.BLANK);
        } else {
            itemGroupImpl.setItemFilter(itemFilter);
        }

        itemGroupImpl.setModifiedBy(modifiedBy);

        if (itemGroupDescription == null) {
            itemGroupImpl.setItemGroupDescription(StringPool.BLANK);
        } else {
            itemGroupImpl.setItemGroupDescription(itemGroupDescription);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            itemGroupImpl.setModifiedDate(null);
        } else {
            itemGroupImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (itemGroupName == null) {
            itemGroupImpl.setItemGroupName(StringPool.BLANK);
        } else {
            itemGroupImpl.setItemGroupName(itemGroupName);
        }

        itemGroupImpl.setItemGroupSid(itemGroupSid);
        itemGroupImpl.setCompanyMasterSid(companyMasterSid);

        itemGroupImpl.resetOriginalValues();

        return itemGroupImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        itemGroupNo = objectInput.readUTF();
        versionNo = objectInput.readInt();
        itemFilter = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        itemGroupDescription = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        itemGroupName = objectInput.readUTF();
        itemGroupSid = objectInput.readInt();
        companyMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (itemGroupNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemGroupNo);
        }

        objectOutput.writeInt(versionNo);

        if (itemFilter == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemFilter);
        }

        objectOutput.writeInt(modifiedBy);

        if (itemGroupDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemGroupDescription);
        }

        objectOutput.writeLong(modifiedDate);

        if (itemGroupName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemGroupName);
        }

        objectOutput.writeInt(itemGroupSid);
        objectOutput.writeInt(companyMasterSid);
    }
}
