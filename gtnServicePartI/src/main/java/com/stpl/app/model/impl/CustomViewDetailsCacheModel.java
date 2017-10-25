package com.stpl.app.model.impl;

import com.stpl.app.model.CustomViewDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CustomViewDetails in entity cache.
 *
 * @author
 * @see CustomViewDetails
 * @generated
 */
public class CustomViewDetailsCacheModel implements CacheModel<CustomViewDetails>,
    Externalizable {
    public int hierarchyId;
    public String hierarchyIndicator;
    public int customViewMasterSid;
    public int customViewDetailsSid;
    public int levelNo;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{hierarchyId=");
        sb.append(hierarchyId);
        sb.append(", hierarchyIndicator=");
        sb.append(hierarchyIndicator);
        sb.append(", customViewMasterSid=");
        sb.append(customViewMasterSid);
        sb.append(", customViewDetailsSid=");
        sb.append(customViewDetailsSid);
        sb.append(", levelNo=");
        sb.append(levelNo);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CustomViewDetails toEntityModel() {
        CustomViewDetailsImpl customViewDetailsImpl = new CustomViewDetailsImpl();

        customViewDetailsImpl.setHierarchyId(hierarchyId);

        if (hierarchyIndicator == null) {
            customViewDetailsImpl.setHierarchyIndicator(StringPool.BLANK);
        } else {
            customViewDetailsImpl.setHierarchyIndicator(hierarchyIndicator);
        }

        customViewDetailsImpl.setCustomViewMasterSid(customViewMasterSid);
        customViewDetailsImpl.setCustomViewDetailsSid(customViewDetailsSid);
        customViewDetailsImpl.setLevelNo(levelNo);

        customViewDetailsImpl.resetOriginalValues();

        return customViewDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        hierarchyId = objectInput.readInt();
        hierarchyIndicator = objectInput.readUTF();
        customViewMasterSid = objectInput.readInt();
        customViewDetailsSid = objectInput.readInt();
        levelNo = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(hierarchyId);

        if (hierarchyIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(hierarchyIndicator);
        }

        objectOutput.writeInt(customViewMasterSid);
        objectOutput.writeInt(customViewDetailsSid);
        objectOutput.writeInt(levelNo);
    }
}
