package com.stpl.app.model.impl;

import com.stpl.app.model.HierarchyDefinition;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HierarchyDefinition in entity cache.
 *
 * @author
 * @see HierarchyDefinition
 * @generated
 */
public class HierarchyDefinitionCacheModel implements CacheModel<HierarchyDefinition>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public int noOfLevels;
    public int hierarchyType;
    public String hierarchyName;
    public int hierarchyDefinitionSid;
    public int versionNo;
    public int modifiedBy;
    public long modifiedDate;
    public int hierarchyCategory;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", noOfLevels=");
        sb.append(noOfLevels);
        sb.append(", hierarchyType=");
        sb.append(hierarchyType);
        sb.append(", hierarchyName=");
        sb.append(hierarchyName);
        sb.append(", hierarchyDefinitionSid=");
        sb.append(hierarchyDefinitionSid);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", hierarchyCategory=");
        sb.append(hierarchyCategory);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public HierarchyDefinition toEntityModel() {
        HierarchyDefinitionImpl hierarchyDefinitionImpl = new HierarchyDefinitionImpl();

        if (createdDate == Long.MIN_VALUE) {
            hierarchyDefinitionImpl.setCreatedDate(null);
        } else {
            hierarchyDefinitionImpl.setCreatedDate(new Date(createdDate));
        }

        hierarchyDefinitionImpl.setCreatedBy(createdBy);
        hierarchyDefinitionImpl.setNoOfLevels(noOfLevels);
        hierarchyDefinitionImpl.setHierarchyType(hierarchyType);

        if (hierarchyName == null) {
            hierarchyDefinitionImpl.setHierarchyName(StringPool.BLANK);
        } else {
            hierarchyDefinitionImpl.setHierarchyName(hierarchyName);
        }

        hierarchyDefinitionImpl.setHierarchyDefinitionSid(hierarchyDefinitionSid);
        hierarchyDefinitionImpl.setVersionNo(versionNo);
        hierarchyDefinitionImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            hierarchyDefinitionImpl.setModifiedDate(null);
        } else {
            hierarchyDefinitionImpl.setModifiedDate(new Date(modifiedDate));
        }

        hierarchyDefinitionImpl.setHierarchyCategory(hierarchyCategory);

        hierarchyDefinitionImpl.resetOriginalValues();

        return hierarchyDefinitionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        noOfLevels = objectInput.readInt();
        hierarchyType = objectInput.readInt();
        hierarchyName = objectInput.readUTF();
        hierarchyDefinitionSid = objectInput.readInt();
        versionNo = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        hierarchyCategory = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(noOfLevels);
        objectOutput.writeInt(hierarchyType);

        if (hierarchyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(hierarchyName);
        }

        objectOutput.writeInt(hierarchyDefinitionSid);
        objectOutput.writeInt(versionNo);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(hierarchyCategory);
    }
}
