package com.stpl.app.model.impl;

import com.stpl.app.model.DocDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DocDetails in entity cache.
 *
 * @author
 * @see DocDetails
 * @generated
 */
public class DocDetailsCacheModel implements CacheModel<DocDetails>,
    Externalizable {
    public String fileName;
    public String fileType;
    public String uploadedBy;
    public String forecastType;
    public int projectionId;
    public int docDetailsId;
    public long uploadedDate;
    public String fileSize;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{fileName=");
        sb.append(fileName);
        sb.append(", fileType=");
        sb.append(fileType);
        sb.append(", uploadedBy=");
        sb.append(uploadedBy);
        sb.append(", forecastType=");
        sb.append(forecastType);
        sb.append(", projectionId=");
        sb.append(projectionId);
        sb.append(", docDetailsId=");
        sb.append(docDetailsId);
        sb.append(", uploadedDate=");
        sb.append(uploadedDate);
        sb.append(", fileSize=");
        sb.append(fileSize);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public DocDetails toEntityModel() {
        DocDetailsImpl docDetailsImpl = new DocDetailsImpl();

        if (fileName == null) {
            docDetailsImpl.setFileName(StringPool.BLANK);
        } else {
            docDetailsImpl.setFileName(fileName);
        }

        if (fileType == null) {
            docDetailsImpl.setFileType(StringPool.BLANK);
        } else {
            docDetailsImpl.setFileType(fileType);
        }

        if (uploadedBy == null) {
            docDetailsImpl.setUploadedBy(StringPool.BLANK);
        } else {
            docDetailsImpl.setUploadedBy(uploadedBy);
        }

        if (forecastType == null) {
            docDetailsImpl.setForecastType(StringPool.BLANK);
        } else {
            docDetailsImpl.setForecastType(forecastType);
        }

        docDetailsImpl.setProjectionId(projectionId);
        docDetailsImpl.setDocDetailsId(docDetailsId);

        if (uploadedDate == Long.MIN_VALUE) {
            docDetailsImpl.setUploadedDate(null);
        } else {
            docDetailsImpl.setUploadedDate(new Date(uploadedDate));
        }

        if (fileSize == null) {
            docDetailsImpl.setFileSize(StringPool.BLANK);
        } else {
            docDetailsImpl.setFileSize(fileSize);
        }

        docDetailsImpl.resetOriginalValues();

        return docDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        fileName = objectInput.readUTF();
        fileType = objectInput.readUTF();
        uploadedBy = objectInput.readUTF();
        forecastType = objectInput.readUTF();
        projectionId = objectInput.readInt();
        docDetailsId = objectInput.readInt();
        uploadedDate = objectInput.readLong();
        fileSize = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (fileName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fileName);
        }

        if (fileType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fileType);
        }

        if (uploadedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uploadedBy);
        }

        if (forecastType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastType);
        }

        objectOutput.writeInt(projectionId);
        objectOutput.writeInt(docDetailsId);
        objectOutput.writeLong(uploadedDate);

        if (fileSize == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fileSize);
        }
    }
}
