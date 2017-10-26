package com.stpl.app.model.impl;

import com.stpl.app.model.NaProjDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NaProjDetails in entity cache.
 *
 * @author
 * @see NaProjDetails
 * @generated
 */
public class NaProjDetailsCacheModel implements CacheModel<NaProjDetails>,
    Externalizable {
    public int itemMasterSid;
    public int naProjMasterSid;
    public int naProjDetailsSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", naProjMasterSid=");
        sb.append(naProjMasterSid);
        sb.append(", naProjDetailsSid=");
        sb.append(naProjDetailsSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NaProjDetails toEntityModel() {
        NaProjDetailsImpl naProjDetailsImpl = new NaProjDetailsImpl();

        naProjDetailsImpl.setItemMasterSid(itemMasterSid);
        naProjDetailsImpl.setNaProjMasterSid(naProjMasterSid);
        naProjDetailsImpl.setNaProjDetailsSid(naProjDetailsSid);

        naProjDetailsImpl.resetOriginalValues();

        return naProjDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemMasterSid = objectInput.readInt();
        naProjMasterSid = objectInput.readInt();
        naProjDetailsSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeInt(naProjMasterSid);
        objectOutput.writeInt(naProjDetailsSid);
    }
}
