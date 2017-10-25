package com.stpl.app.model.impl;

import com.stpl.app.model.HistItemGroupDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistItemGroupDetails in entity cache.
 *
 * @author
 * @see HistItemGroupDetails
 * @generated
 */
public class HistItemGroupDetailsCacheModel implements CacheModel<HistItemGroupDetails>,
    Externalizable {
    public int itemGroupDetailsSid;
    public long createdDate;
    public int createdBy;
    public long actionDate;
    public int itemMasterSid;
    public String actionFlag;
    public int versionNo;
    public int modifiedBy;
    public long modifiedDate;
    public int itemGroupSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{itemGroupDetailsSid=");
        sb.append(itemGroupDetailsSid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", actionDate=");
        sb.append(actionDate);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", actionFlag=");
        sb.append(actionFlag);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", itemGroupSid=");
        sb.append(itemGroupSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public HistItemGroupDetails toEntityModel() {
        HistItemGroupDetailsImpl histItemGroupDetailsImpl = new HistItemGroupDetailsImpl();

        histItemGroupDetailsImpl.setItemGroupDetailsSid(itemGroupDetailsSid);

        if (createdDate == Long.MIN_VALUE) {
            histItemGroupDetailsImpl.setCreatedDate(null);
        } else {
            histItemGroupDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        histItemGroupDetailsImpl.setCreatedBy(createdBy);

        if (actionDate == Long.MIN_VALUE) {
            histItemGroupDetailsImpl.setActionDate(null);
        } else {
            histItemGroupDetailsImpl.setActionDate(new Date(actionDate));
        }

        histItemGroupDetailsImpl.setItemMasterSid(itemMasterSid);

        if (actionFlag == null) {
            histItemGroupDetailsImpl.setActionFlag(StringPool.BLANK);
        } else {
            histItemGroupDetailsImpl.setActionFlag(actionFlag);
        }

        histItemGroupDetailsImpl.setVersionNo(versionNo);
        histItemGroupDetailsImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            histItemGroupDetailsImpl.setModifiedDate(null);
        } else {
            histItemGroupDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        histItemGroupDetailsImpl.setItemGroupSid(itemGroupSid);

        histItemGroupDetailsImpl.resetOriginalValues();

        return histItemGroupDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemGroupDetailsSid = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        actionDate = objectInput.readLong();
        itemMasterSid = objectInput.readInt();
        actionFlag = objectInput.readUTF();
        versionNo = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        itemGroupSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(itemGroupDetailsSid);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(actionDate);
        objectOutput.writeInt(itemMasterSid);

        if (actionFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actionFlag);
        }

        objectOutput.writeInt(versionNo);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(itemGroupSid);
    }
}
