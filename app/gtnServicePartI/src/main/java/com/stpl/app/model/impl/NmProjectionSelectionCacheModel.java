package com.stpl.app.model.impl;

import com.stpl.app.model.NmProjectionSelection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmProjectionSelection in entity cache.
 *
 * @author
 * @see NmProjectionSelection
 * @generated
 */
public class NmProjectionSelectionCacheModel implements CacheModel<NmProjectionSelection>,
    Externalizable {
    public String screenName;
    public int nmProjectionSelectionSid;
    public String fieldName;
    public String fieldValues;
    public int projectionMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{screenName=");
        sb.append(screenName);
        sb.append(", nmProjectionSelectionSid=");
        sb.append(nmProjectionSelectionSid);
        sb.append(", fieldName=");
        sb.append(fieldName);
        sb.append(", fieldValues=");
        sb.append(fieldValues);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmProjectionSelection toEntityModel() {
        NmProjectionSelectionImpl nmProjectionSelectionImpl = new NmProjectionSelectionImpl();

        if (screenName == null) {
            nmProjectionSelectionImpl.setScreenName(StringPool.BLANK);
        } else {
            nmProjectionSelectionImpl.setScreenName(screenName);
        }

        nmProjectionSelectionImpl.setNmProjectionSelectionSid(nmProjectionSelectionSid);

        if (fieldName == null) {
            nmProjectionSelectionImpl.setFieldName(StringPool.BLANK);
        } else {
            nmProjectionSelectionImpl.setFieldName(fieldName);
        }

        if (fieldValues == null) {
            nmProjectionSelectionImpl.setFieldValues(StringPool.BLANK);
        } else {
            nmProjectionSelectionImpl.setFieldValues(fieldValues);
        }

        nmProjectionSelectionImpl.setProjectionMasterSid(projectionMasterSid);

        nmProjectionSelectionImpl.resetOriginalValues();

        return nmProjectionSelectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        screenName = objectInput.readUTF();
        nmProjectionSelectionSid = objectInput.readInt();
        fieldName = objectInput.readUTF();
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

        objectOutput.writeInt(nmProjectionSelectionSid);

        if (fieldName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fieldName);
        }

        if (fieldValues == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fieldValues);
        }

        objectOutput.writeInt(projectionMasterSid);
    }
}
