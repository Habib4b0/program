package com.stpl.app.model.impl;

import com.stpl.app.model.NaProjectionSelection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NaProjectionSelection in entity cache.
 *
 * @author
 * @see NaProjectionSelection
 * @generated
 */
public class NaProjectionSelectionCacheModel implements CacheModel<NaProjectionSelection>,
    Externalizable {
    public String screenName;
    public String fieldName;
    public String fieldValues;
    public int naProjectionSelectionSid;
    public int naProjMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{screenName=");
        sb.append(screenName);
        sb.append(", fieldName=");
        sb.append(fieldName);
        sb.append(", fieldValues=");
        sb.append(fieldValues);
        sb.append(", naProjectionSelectionSid=");
        sb.append(naProjectionSelectionSid);
        sb.append(", naProjMasterSid=");
        sb.append(naProjMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NaProjectionSelection toEntityModel() {
        NaProjectionSelectionImpl naProjectionSelectionImpl = new NaProjectionSelectionImpl();

        if (screenName == null) {
            naProjectionSelectionImpl.setScreenName(StringPool.BLANK);
        } else {
            naProjectionSelectionImpl.setScreenName(screenName);
        }

        if (fieldName == null) {
            naProjectionSelectionImpl.setFieldName(StringPool.BLANK);
        } else {
            naProjectionSelectionImpl.setFieldName(fieldName);
        }

        if (fieldValues == null) {
            naProjectionSelectionImpl.setFieldValues(StringPool.BLANK);
        } else {
            naProjectionSelectionImpl.setFieldValues(fieldValues);
        }

        naProjectionSelectionImpl.setNaProjectionSelectionSid(naProjectionSelectionSid);
        naProjectionSelectionImpl.setNaProjMasterSid(naProjMasterSid);

        naProjectionSelectionImpl.resetOriginalValues();

        return naProjectionSelectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        screenName = objectInput.readUTF();
        fieldName = objectInput.readUTF();
        fieldValues = objectInput.readUTF();
        naProjectionSelectionSid = objectInput.readInt();
        naProjMasterSid = objectInput.readInt();
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

        if (fieldValues == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fieldValues);
        }

        objectOutput.writeInt(naProjectionSelectionSid);
        objectOutput.writeInt(naProjMasterSid);
    }
}
