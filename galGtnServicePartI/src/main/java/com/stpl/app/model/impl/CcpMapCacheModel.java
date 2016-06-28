package com.stpl.app.model.impl;

import com.stpl.app.model.CcpMap;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CcpMap in entity cache.
 *
 * @author
 * @see CcpMap
 * @generated
 */
public class CcpMapCacheModel implements CacheModel<CcpMap>, Externalizable {
    public int ccpDetailsSid;
    public int relationshipLevelSid;
    public int ccpMapSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{ccpDetailsSid=");
        sb.append(ccpDetailsSid);
        sb.append(", relationshipLevelSid=");
        sb.append(relationshipLevelSid);
        sb.append(", ccpMapSid=");
        sb.append(ccpMapSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CcpMap toEntityModel() {
        CcpMapImpl ccpMapImpl = new CcpMapImpl();

        ccpMapImpl.setCcpDetailsSid(ccpDetailsSid);
        ccpMapImpl.setRelationshipLevelSid(relationshipLevelSid);
        ccpMapImpl.setCcpMapSid(ccpMapSid);

        ccpMapImpl.resetOriginalValues();

        return ccpMapImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ccpDetailsSid = objectInput.readInt();
        relationshipLevelSid = objectInput.readInt();
        ccpMapSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(ccpDetailsSid);
        objectOutput.writeInt(relationshipLevelSid);
        objectOutput.writeInt(ccpMapSid);
    }
}
