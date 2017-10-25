package com.stpl.app.model.impl;

import com.stpl.app.model.HierarchyLevelValues;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HierarchyLevelValues in entity cache.
 *
 * @author
 * @see HierarchyLevelValues
 * @generated
 */
public class HierarchyLevelValuesCacheModel implements CacheModel<HierarchyLevelValues>,
    Externalizable {
    public String levelValues;
    public int hierarchyLevelValuesSid;
    public long createdDate;
    public int createdBy;
    public int hierarchyLevelDefinitionSid;
    public int versionNo;
    public int modifiedBy;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{levelValues=");
        sb.append(levelValues);
        sb.append(", hierarchyLevelValuesSid=");
        sb.append(hierarchyLevelValuesSid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", hierarchyLevelDefinitionSid=");
        sb.append(hierarchyLevelDefinitionSid);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public HierarchyLevelValues toEntityModel() {
        HierarchyLevelValuesImpl hierarchyLevelValuesImpl = new HierarchyLevelValuesImpl();

        if (levelValues == null) {
            hierarchyLevelValuesImpl.setLevelValues(StringPool.BLANK);
        } else {
            hierarchyLevelValuesImpl.setLevelValues(levelValues);
        }

        hierarchyLevelValuesImpl.setHierarchyLevelValuesSid(hierarchyLevelValuesSid);

        if (createdDate == Long.MIN_VALUE) {
            hierarchyLevelValuesImpl.setCreatedDate(null);
        } else {
            hierarchyLevelValuesImpl.setCreatedDate(new Date(createdDate));
        }

        hierarchyLevelValuesImpl.setCreatedBy(createdBy);
        hierarchyLevelValuesImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
        hierarchyLevelValuesImpl.setVersionNo(versionNo);
        hierarchyLevelValuesImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            hierarchyLevelValuesImpl.setModifiedDate(null);
        } else {
            hierarchyLevelValuesImpl.setModifiedDate(new Date(modifiedDate));
        }

        hierarchyLevelValuesImpl.resetOriginalValues();

        return hierarchyLevelValuesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        levelValues = objectInput.readUTF();
        hierarchyLevelValuesSid = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        hierarchyLevelDefinitionSid = objectInput.readInt();
        versionNo = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (levelValues == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(levelValues);
        }

        objectOutput.writeInt(hierarchyLevelValuesSid);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(hierarchyLevelDefinitionSid);
        objectOutput.writeInt(versionNo);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);
    }
}
