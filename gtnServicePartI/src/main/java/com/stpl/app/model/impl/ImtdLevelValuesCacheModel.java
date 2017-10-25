package com.stpl.app.model.impl;

import com.stpl.app.model.ImtdLevelValues;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdLevelValues in entity cache.
 *
 * @author
 * @see ImtdLevelValues
 * @generated
 */
public class ImtdLevelValuesCacheModel implements CacheModel<ImtdLevelValues>,
    Externalizable {
    public String levelValues;
    public long createdDate;
    public int createdBy;
    public int imtdLevelValuesSid;
    public int levelNo;
    public int versionNo;
    public int modifiedBy;
    public long modifiedDate;
    public String levelName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{levelValues=");
        sb.append(levelValues);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", imtdLevelValuesSid=");
        sb.append(imtdLevelValuesSid);
        sb.append(", levelNo=");
        sb.append(levelNo);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", levelName=");
        sb.append(levelName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImtdLevelValues toEntityModel() {
        ImtdLevelValuesImpl imtdLevelValuesImpl = new ImtdLevelValuesImpl();

        if (levelValues == null) {
            imtdLevelValuesImpl.setLevelValues(StringPool.BLANK);
        } else {
            imtdLevelValuesImpl.setLevelValues(levelValues);
        }

        if (createdDate == Long.MIN_VALUE) {
            imtdLevelValuesImpl.setCreatedDate(null);
        } else {
            imtdLevelValuesImpl.setCreatedDate(new Date(createdDate));
        }

        imtdLevelValuesImpl.setCreatedBy(createdBy);
        imtdLevelValuesImpl.setImtdLevelValuesSid(imtdLevelValuesSid);
        imtdLevelValuesImpl.setLevelNo(levelNo);
        imtdLevelValuesImpl.setVersionNo(versionNo);
        imtdLevelValuesImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            imtdLevelValuesImpl.setModifiedDate(null);
        } else {
            imtdLevelValuesImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (levelName == null) {
            imtdLevelValuesImpl.setLevelName(StringPool.BLANK);
        } else {
            imtdLevelValuesImpl.setLevelName(levelName);
        }

        imtdLevelValuesImpl.resetOriginalValues();

        return imtdLevelValuesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        levelValues = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        imtdLevelValuesSid = objectInput.readInt();
        levelNo = objectInput.readInt();
        versionNo = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        levelName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (levelValues == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(levelValues);
        }

        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(imtdLevelValuesSid);
        objectOutput.writeInt(levelNo);
        objectOutput.writeInt(versionNo);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);

        if (levelName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(levelName);
        }
    }
}
