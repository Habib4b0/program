package com.stpl.app.model.impl;

import com.stpl.app.model.ChProjectionSelection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChProjectionSelection in entity cache.
 *
 * @author
 * @see ChProjectionSelection
 * @generated
 */
public class ChProjectionSelectionCacheModel implements CacheModel<ChProjectionSelection>,
    Externalizable {
    public String screenName;
    public String fieldName;
    public int chProjectionSelectionSid;
    public String fieldValues;
    public int projectionMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{screenName=");
        sb.append(screenName);
        sb.append(", fieldName=");
        sb.append(fieldName);
        sb.append(", chProjectionSelectionSid=");
        sb.append(chProjectionSelectionSid);
        sb.append(", fieldValues=");
        sb.append(fieldValues);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ChProjectionSelection toEntityModel() {
        ChProjectionSelectionImpl chProjectionSelectionImpl = new ChProjectionSelectionImpl();

        if (screenName == null) {
            chProjectionSelectionImpl.setScreenName(StringPool.BLANK);
        } else {
            chProjectionSelectionImpl.setScreenName(screenName);
        }

        if (fieldName == null) {
            chProjectionSelectionImpl.setFieldName(StringPool.BLANK);
        } else {
            chProjectionSelectionImpl.setFieldName(fieldName);
        }

        chProjectionSelectionImpl.setChProjectionSelectionSid(chProjectionSelectionSid);

        if (fieldValues == null) {
            chProjectionSelectionImpl.setFieldValues(StringPool.BLANK);
        } else {
            chProjectionSelectionImpl.setFieldValues(fieldValues);
        }

        chProjectionSelectionImpl.setProjectionMasterSid(projectionMasterSid);

        chProjectionSelectionImpl.resetOriginalValues();

        return chProjectionSelectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        screenName = objectInput.readUTF();
        fieldName = objectInput.readUTF();
        chProjectionSelectionSid = objectInput.readInt();
        fieldValues = objectInput.readUTF();
        projectionMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (screenName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(screenName);
        }

        if (fieldName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fieldName);
        }

        objectOutput.writeInt(chProjectionSelectionSid);

        if (fieldValues == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fieldValues);
        }

        objectOutput.writeInt(projectionMasterSid);
    }
}
