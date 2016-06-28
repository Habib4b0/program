package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CffDetails in entity cache.
 *
 * @author
 * @see CffDetails
 * @generated
 */
public class CffDetailsCacheModel implements CacheModel<CffDetails>,
    Externalizable {
    public int ccpDetailsSid;
    public int projectionMasterSid;
    public int cffMasterSid;
    public String inboundStatus;
    public int cffDetailsSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{ccpDetailsSid=");
        sb.append(ccpDetailsSid);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append(", cffMasterSid=");
        sb.append(cffMasterSid);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", cffDetailsSid=");
        sb.append(cffDetailsSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffDetails toEntityModel() {
        CffDetailsImpl cffDetailsImpl = new CffDetailsImpl();

        cffDetailsImpl.setCcpDetailsSid(ccpDetailsSid);
        cffDetailsImpl.setProjectionMasterSid(projectionMasterSid);
        cffDetailsImpl.setCffMasterSid(cffMasterSid);

        if (inboundStatus == null) {
            cffDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            cffDetailsImpl.setInboundStatus(inboundStatus);
        }

        cffDetailsImpl.setCffDetailsSid(cffDetailsSid);

        cffDetailsImpl.resetOriginalValues();

        return cffDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ccpDetailsSid = objectInput.readInt();
        projectionMasterSid = objectInput.readInt();
        cffMasterSid = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        cffDetailsSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(ccpDetailsSid);
        objectOutput.writeInt(projectionMasterSid);
        objectOutput.writeInt(cffMasterSid);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(cffDetailsSid);
    }
}
