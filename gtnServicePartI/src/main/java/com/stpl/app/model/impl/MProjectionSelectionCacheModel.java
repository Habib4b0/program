package com.stpl.app.model.impl;

import com.stpl.app.model.MProjectionSelection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MProjectionSelection in entity cache.
 *
 * @author
 * @see MProjectionSelection
 * @generated
 */
public class MProjectionSelectionCacheModel implements CacheModel<MProjectionSelection>,
    Externalizable {
    public int mProjectionSelectionSid;
    public int projectionMasterSid;
    public String fieldValues;
    public String fieldName;
    public String screenName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{mProjectionSelectionSid=");
        sb.append(mProjectionSelectionSid);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append(", fieldValues=");
        sb.append(fieldValues);
        sb.append(", fieldName=");
        sb.append(fieldName);
        sb.append(", screenName=");
        sb.append(screenName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MProjectionSelection toEntityModel() {
        MProjectionSelectionImpl mProjectionSelectionImpl = new MProjectionSelectionImpl();

        mProjectionSelectionImpl.setMProjectionSelectionSid(mProjectionSelectionSid);
        mProjectionSelectionImpl.setProjectionMasterSid(projectionMasterSid);

        if (fieldValues == null) {
            mProjectionSelectionImpl.setFieldValues(StringPool.BLANK);
        } else {
            mProjectionSelectionImpl.setFieldValues(fieldValues);
        }

        if (fieldName == null) {
            mProjectionSelectionImpl.setFieldName(StringPool.BLANK);
        } else {
            mProjectionSelectionImpl.setFieldName(fieldName);
        }

        if (screenName == null) {
            mProjectionSelectionImpl.setScreenName(StringPool.BLANK);
        } else {
            mProjectionSelectionImpl.setScreenName(screenName);
        }

        mProjectionSelectionImpl.resetOriginalValues();

        return mProjectionSelectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        mProjectionSelectionSid = objectInput.readInt();
        projectionMasterSid = objectInput.readInt();
        fieldValues = objectInput.readUTF();
        fieldName = objectInput.readUTF();
        screenName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(mProjectionSelectionSid);
        objectOutput.writeInt(projectionMasterSid);

        if (fieldValues == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fieldValues);
        }

        if (fieldName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fieldName);
        }

        if (screenName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(screenName);
        }
    }
}
