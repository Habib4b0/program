package com.stpl.app.model.impl;

import com.stpl.app.model.HistHierarchyDefinition;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistHierarchyDefinition in entity cache.
 *
 * @author
 * @see HistHierarchyDefinition
 * @generated
 */
public class HistHierarchyDefinitionCacheModel implements CacheModel<HistHierarchyDefinition>,
    Externalizable {
    public int noOfLevels;
    public int createdBy;
    public long actionDate;
    public String actionFlag;
    public int modifiedBy;
    public int hierarchyDefinitionSid;
    public long createdDate;
    public int hierarchyType;
    public int hierarchyCategory;
    public String hierarchyName;
    public int versionNo;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{noOfLevels=");
        sb.append(noOfLevels);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", actionDate=");
        sb.append(actionDate);
        sb.append(", actionFlag=");
        sb.append(actionFlag);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", hierarchyDefinitionSid=");
        sb.append(hierarchyDefinitionSid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", hierarchyType=");
        sb.append(hierarchyType);
        sb.append(", hierarchyCategory=");
        sb.append(hierarchyCategory);
        sb.append(", hierarchyName=");
        sb.append(hierarchyName);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public HistHierarchyDefinition toEntityModel() {
        HistHierarchyDefinitionImpl histHierarchyDefinitionImpl = new HistHierarchyDefinitionImpl();

        histHierarchyDefinitionImpl.setNoOfLevels(noOfLevels);
        histHierarchyDefinitionImpl.setCreatedBy(createdBy);

        if (actionDate == Long.MIN_VALUE) {
            histHierarchyDefinitionImpl.setActionDate(null);
        } else {
            histHierarchyDefinitionImpl.setActionDate(new Date(actionDate));
        }

        if (actionFlag == null) {
            histHierarchyDefinitionImpl.setActionFlag(StringPool.BLANK);
        } else {
            histHierarchyDefinitionImpl.setActionFlag(actionFlag);
        }

        histHierarchyDefinitionImpl.setModifiedBy(modifiedBy);
        histHierarchyDefinitionImpl.setHierarchyDefinitionSid(hierarchyDefinitionSid);

        if (createdDate == Long.MIN_VALUE) {
            histHierarchyDefinitionImpl.setCreatedDate(null);
        } else {
            histHierarchyDefinitionImpl.setCreatedDate(new Date(createdDate));
        }

        histHierarchyDefinitionImpl.setHierarchyType(hierarchyType);
        histHierarchyDefinitionImpl.setHierarchyCategory(hierarchyCategory);

        if (hierarchyName == null) {
            histHierarchyDefinitionImpl.setHierarchyName(StringPool.BLANK);
        } else {
            histHierarchyDefinitionImpl.setHierarchyName(hierarchyName);
        }

        histHierarchyDefinitionImpl.setVersionNo(versionNo);

        if (modifiedDate == Long.MIN_VALUE) {
            histHierarchyDefinitionImpl.setModifiedDate(null);
        } else {
            histHierarchyDefinitionImpl.setModifiedDate(new Date(modifiedDate));
        }

        histHierarchyDefinitionImpl.resetOriginalValues();

        return histHierarchyDefinitionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        noOfLevels = objectInput.readInt();
        createdBy = objectInput.readInt();
        actionDate = objectInput.readLong();
        actionFlag = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        hierarchyDefinitionSid = objectInput.readInt();
        createdDate = objectInput.readLong();
        hierarchyType = objectInput.readInt();
        hierarchyCategory = objectInput.readInt();
        hierarchyName = objectInput.readUTF();
        versionNo = objectInput.readInt();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(noOfLevels);
        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(actionDate);

        if (actionFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actionFlag);
        }

        objectOutput.writeInt(modifiedBy);
        objectOutput.writeInt(hierarchyDefinitionSid);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(hierarchyType);
        objectOutput.writeInt(hierarchyCategory);

        if (hierarchyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(hierarchyName);
        }

        objectOutput.writeInt(versionNo);
        objectOutput.writeLong(modifiedDate);
    }
}
