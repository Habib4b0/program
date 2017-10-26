package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffCustomViewDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CffCustomViewDetails in entity cache.
 *
 * @author
 * @see CffCustomViewDetails
 * @generated
 */
public class CffCustomViewDetailsCacheModel implements CacheModel<CffCustomViewDetails>,
    Externalizable {
    public int hierarchyId;
    public String hierarchyIndicator;
    public int cffCustomViewDetailsSid;
    public int levelNo;
    public int cffCustomViewMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{hierarchyId=");
        sb.append(hierarchyId);
        sb.append(", hierarchyIndicator=");
        sb.append(hierarchyIndicator);
        sb.append(", cffCustomViewDetailsSid=");
        sb.append(cffCustomViewDetailsSid);
        sb.append(", levelNo=");
        sb.append(levelNo);
        sb.append(", cffCustomViewMasterSid=");
        sb.append(cffCustomViewMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffCustomViewDetails toEntityModel() {
        CffCustomViewDetailsImpl cffCustomViewDetailsImpl = new CffCustomViewDetailsImpl();

        cffCustomViewDetailsImpl.setHierarchyId(hierarchyId);

        if (hierarchyIndicator == null) {
            cffCustomViewDetailsImpl.setHierarchyIndicator(StringPool.BLANK);
        } else {
            cffCustomViewDetailsImpl.setHierarchyIndicator(hierarchyIndicator);
        }

        cffCustomViewDetailsImpl.setCffCustomViewDetailsSid(cffCustomViewDetailsSid);
        cffCustomViewDetailsImpl.setLevelNo(levelNo);
        cffCustomViewDetailsImpl.setCffCustomViewMasterSid(cffCustomViewMasterSid);

        cffCustomViewDetailsImpl.resetOriginalValues();

        return cffCustomViewDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        hierarchyId = objectInput.readInt();
        hierarchyIndicator = objectInput.readUTF();
        cffCustomViewDetailsSid = objectInput.readInt();
        levelNo = objectInput.readInt();
        cffCustomViewMasterSid = objectInput.readInt();
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

        objectOutput.writeInt(cffCustomViewDetailsSid);
        objectOutput.writeInt(levelNo);
        objectOutput.writeInt(cffCustomViewMasterSid);
    }
}
