package com.stpl.app.model.impl;

import com.stpl.app.model.MasterDataFiles;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MasterDataFiles in entity cache.
 *
 * @author
 * @see MasterDataFiles
 * @generated
 */
public class MasterDataFilesCacheModel implements CacheModel<MasterDataFiles>,
    Externalizable {
    public int masterTableSid;
    public int masterDataFilesSid;
    public String masterTableName;
    public String filePath;
    public long createdDate;
    public int createdBy;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{masterTableSid=");
        sb.append(masterTableSid);
        sb.append(", masterDataFilesSid=");
        sb.append(masterDataFilesSid);
        sb.append(", masterTableName=");
        sb.append(masterTableName);
        sb.append(", filePath=");
        sb.append(filePath);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MasterDataFiles toEntityModel() {
        MasterDataFilesImpl masterDataFilesImpl = new MasterDataFilesImpl();

        masterDataFilesImpl.setMasterTableSid(masterTableSid);
        masterDataFilesImpl.setMasterDataFilesSid(masterDataFilesSid);

        if (masterTableName == null) {
            masterDataFilesImpl.setMasterTableName(StringPool.BLANK);
        } else {
            masterDataFilesImpl.setMasterTableName(masterTableName);
        }

        if (filePath == null) {
            masterDataFilesImpl.setFilePath(StringPool.BLANK);
        } else {
            masterDataFilesImpl.setFilePath(filePath);
        }

        if (createdDate == Long.MIN_VALUE) {
            masterDataFilesImpl.setCreatedDate(null);
        } else {
            masterDataFilesImpl.setCreatedDate(new Date(createdDate));
        }

        masterDataFilesImpl.setCreatedBy(createdBy);

        masterDataFilesImpl.resetOriginalValues();

        return masterDataFilesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        masterTableSid = objectInput.readInt();
        masterDataFilesSid = objectInput.readInt();
        masterTableName = objectInput.readUTF();
        filePath = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(masterTableSid);
        objectOutput.writeInt(masterDataFilesSid);

        if (masterTableName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(masterTableName);
        }

        if (filePath == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(filePath);
        }

        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
    }
}
