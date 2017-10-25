package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffAdditionalInfo;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CffAdditionalInfo in entity cache.
 *
 * @author
 * @see CffAdditionalInfo
 * @generated
 */
public class CffAdditionalInfoCacheModel implements CacheModel<CffAdditionalInfo>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public int cffMasterSid;
    public int cffAdditionalInfoSid;
    public String notes;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", cffMasterSid=");
        sb.append(cffMasterSid);
        sb.append(", cffAdditionalInfoSid=");
        sb.append(cffAdditionalInfoSid);
        sb.append(", notes=");
        sb.append(notes);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffAdditionalInfo toEntityModel() {
        CffAdditionalInfoImpl cffAdditionalInfoImpl = new CffAdditionalInfoImpl();

        if (createdDate == Long.MIN_VALUE) {
            cffAdditionalInfoImpl.setCreatedDate(null);
        } else {
            cffAdditionalInfoImpl.setCreatedDate(new Date(createdDate));
        }

        cffAdditionalInfoImpl.setCreatedBy(createdBy);
        cffAdditionalInfoImpl.setCffMasterSid(cffMasterSid);
        cffAdditionalInfoImpl.setCffAdditionalInfoSid(cffAdditionalInfoSid);

        if (notes == null) {
            cffAdditionalInfoImpl.setNotes(StringPool.BLANK);
        } else {
            cffAdditionalInfoImpl.setNotes(notes);
        }

        cffAdditionalInfoImpl.resetOriginalValues();

        return cffAdditionalInfoImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        cffMasterSid = objectInput.readInt();
        cffAdditionalInfoSid = objectInput.readInt();
        notes = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(cffMasterSid);
        objectOutput.writeInt(cffAdditionalInfoSid);

        if (notes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(notes);
        }
    }
}
