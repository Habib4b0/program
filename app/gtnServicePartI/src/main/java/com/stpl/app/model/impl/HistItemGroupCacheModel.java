package com.stpl.app.model.impl;

import com.stpl.app.model.HistItemGroup;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistItemGroup in entity cache.
 *
 * @author
 * @see HistItemGroup
 * @generated
 */
public class HistItemGroupCacheModel implements CacheModel<HistItemGroup>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public String actionFlag;
    public String itemGroupNo;
    public int versionNo;
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
        sb.append(", actionFlag=");
        sb.append(actionFlag);
        sb.append(", itemGroupNo=");
        sb.append(itemGroupNo);
        sb.append(", versionNo=");
        sb.append(versionNo);
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
    public HistItemGroup toEntityModel() {
        HistItemGroupImpl histItemGroupImpl = new HistItemGroupImpl();

        if (createdDate == Long.MIN_VALUE) {
            histItemGroupImpl.setCreatedDate(null);
        } else {
            histItemGroupImpl.setCreatedDate(new Date(createdDate));
        }

        histItemGroupImpl.setCreatedBy(createdBy);

        if (actionFlag == null) {
            histItemGroupImpl.setActionFlag(StringPool.BLANK);
        } else {
            histItemGroupImpl.setActionFlag(actionFlag);
        }

        if (itemGroupNo == null) {
            histItemGroupImpl.setItemGroupNo(StringPool.BLANK);
        } else {
            histItemGroupImpl.setItemGroupNo(itemGroupNo);
        }

        histItemGroupImpl.setVersionNo(versionNo);
        histItemGroupImpl.setModifiedBy(modifiedBy);

        if (itemGroupDescription == null) {
            histItemGroupImpl.setItemGroupDescription(StringPool.BLANK);
        } else {
            histItemGroupImpl.setItemGroupDescription(itemGroupDescription);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            histItemGroupImpl.setModifiedDate(null);
        } else {
            histItemGroupImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (itemGroupName == null) {
            histItemGroupImpl.setItemGroupName(StringPool.BLANK);
        } else {
            histItemGroupImpl.setItemGroupName(itemGroupName);
        }

        histItemGroupImpl.setItemGroupSid(itemGroupSid);
        histItemGroupImpl.setCompanyMasterSid(companyMasterSid);

        histItemGroupImpl.resetOriginalValues();

        return histItemGroupImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        actionFlag = objectInput.readUTF();
        itemGroupNo = objectInput.readUTF();
        versionNo = objectInput.readInt();
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

        if (actionFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actionFlag);
        }

        if (itemGroupNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemGroupNo);
        }

        objectOutput.writeInt(versionNo);
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
