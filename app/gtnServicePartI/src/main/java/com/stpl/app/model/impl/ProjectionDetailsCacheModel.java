package com.stpl.app.model.impl;

import com.stpl.app.model.ProjectionDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProjectionDetails in entity cache.
 *
 * @author
 * @see ProjectionDetails
 * @generated
 */
public class ProjectionDetailsCacheModel implements CacheModel<ProjectionDetails>,
    Externalizable {
    public int projectionDetailsSid;
    public int ccpDetailsSid;
    public int projectionMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", ccpDetailsSid=");
        sb.append(ccpDetailsSid);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProjectionDetails toEntityModel() {
        ProjectionDetailsImpl projectionDetailsImpl = new ProjectionDetailsImpl();

        projectionDetailsImpl.setProjectionDetailsSid(projectionDetailsSid);
        projectionDetailsImpl.setCcpDetailsSid(ccpDetailsSid);
        projectionDetailsImpl.setProjectionMasterSid(projectionMasterSid);

        projectionDetailsImpl.resetOriginalValues();

        return projectionDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        projectionDetailsSid = objectInput.readInt();
        ccpDetailsSid = objectInput.readInt();
        projectionMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(ccpDetailsSid);
        objectOutput.writeInt(projectionMasterSid);
    }
}
