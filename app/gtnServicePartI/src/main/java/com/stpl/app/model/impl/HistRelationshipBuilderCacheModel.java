package com.stpl.app.model.impl;

import com.stpl.app.model.HistRelationshipBuilder;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistRelationshipBuilder in entity cache.
 *
 * @author
 * @see HistRelationshipBuilder
 * @generated
 */
public class HistRelationshipBuilderCacheModel implements CacheModel<HistRelationshipBuilder>,
    Externalizable {
    public long startDate;
    public long createdDate;
    public int createdBy;
    public String relationshipDescription;
    public long actionDate;
    public String actionFlag;
    public int hierarchyDefinitionSid;
    public int versionNo;
    public String relationshipName;
    public int relationshipBuilderSid;
    public int hierarchyVersion;
    public int modifiedBy;
    public long modifiedDate;
    public int relationshipType;
    public String buildType;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{startDate=");
        sb.append(startDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", relationshipDescription=");
        sb.append(relationshipDescription);
        sb.append(", actionDate=");
        sb.append(actionDate);
        sb.append(", actionFlag=");
        sb.append(actionFlag);
        sb.append(", hierarchyDefinitionSid=");
        sb.append(hierarchyDefinitionSid);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", relationshipName=");
        sb.append(relationshipName);
        sb.append(", relationshipBuilderSid=");
        sb.append(relationshipBuilderSid);
        sb.append(", hierarchyVersion=");
        sb.append(hierarchyVersion);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", relationshipType=");
        sb.append(relationshipType);
        sb.append(", buildType=");
        sb.append(buildType);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public HistRelationshipBuilder toEntityModel() {
        HistRelationshipBuilderImpl histRelationshipBuilderImpl = new HistRelationshipBuilderImpl();

        if (startDate == Long.MIN_VALUE) {
            histRelationshipBuilderImpl.setStartDate(null);
        } else {
            histRelationshipBuilderImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            histRelationshipBuilderImpl.setCreatedDate(null);
        } else {
            histRelationshipBuilderImpl.setCreatedDate(new Date(createdDate));
        }

        histRelationshipBuilderImpl.setCreatedBy(createdBy);

        if (relationshipDescription == null) {
            histRelationshipBuilderImpl.setRelationshipDescription(StringPool.BLANK);
        } else {
            histRelationshipBuilderImpl.setRelationshipDescription(relationshipDescription);
        }

        if (actionDate == Long.MIN_VALUE) {
            histRelationshipBuilderImpl.setActionDate(null);
        } else {
            histRelationshipBuilderImpl.setActionDate(new Date(actionDate));
        }

        if (actionFlag == null) {
            histRelationshipBuilderImpl.setActionFlag(StringPool.BLANK);
        } else {
            histRelationshipBuilderImpl.setActionFlag(actionFlag);
        }

        histRelationshipBuilderImpl.setHierarchyDefinitionSid(hierarchyDefinitionSid);
        histRelationshipBuilderImpl.setVersionNo(versionNo);

        if (relationshipName == null) {
            histRelationshipBuilderImpl.setRelationshipName(StringPool.BLANK);
        } else {
            histRelationshipBuilderImpl.setRelationshipName(relationshipName);
        }

        histRelationshipBuilderImpl.setRelationshipBuilderSid(relationshipBuilderSid);
        histRelationshipBuilderImpl.setHierarchyVersion(hierarchyVersion);
        histRelationshipBuilderImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            histRelationshipBuilderImpl.setModifiedDate(null);
        } else {
            histRelationshipBuilderImpl.setModifiedDate(new Date(modifiedDate));
        }

        histRelationshipBuilderImpl.setRelationshipType(relationshipType);

        if (buildType == null) {
            histRelationshipBuilderImpl.setBuildType(StringPool.BLANK);
        } else {
            histRelationshipBuilderImpl.setBuildType(buildType);
        }

        histRelationshipBuilderImpl.resetOriginalValues();

        return histRelationshipBuilderImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        relationshipDescription = objectInput.readUTF();
        actionDate = objectInput.readLong();
        actionFlag = objectInput.readUTF();
        hierarchyDefinitionSid = objectInput.readInt();
        versionNo = objectInput.readInt();
        relationshipName = objectInput.readUTF();
        relationshipBuilderSid = objectInput.readInt();
        hierarchyVersion = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        relationshipType = objectInput.readInt();
        buildType = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(startDate);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (relationshipDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(relationshipDescription);
        }

        objectOutput.writeLong(actionDate);

        if (actionFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actionFlag);
        }

        objectOutput.writeInt(hierarchyDefinitionSid);
        objectOutput.writeInt(versionNo);

        if (relationshipName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(relationshipName);
        }

        objectOutput.writeInt(relationshipBuilderSid);
        objectOutput.writeInt(hierarchyVersion);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(relationshipType);

        if (buildType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(buildType);
        }
    }
}
