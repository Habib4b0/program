package com.stpl.app.model.impl;

import com.stpl.app.model.HistHierarchyLevelDefn;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistHierarchyLevelDefn in entity cache.
 *
 * @author
 * @see HistHierarchyLevelDefn
 * @generated
 */
public class HistHierarchyLevelDefnCacheModel implements CacheModel<HistHierarchyLevelDefn>,
    Externalizable {
    public String tableName;
    public long actionDate;
    public String fieldName;
    public int hierarchyLevelDefinitionSid;
    public int versionNo;
    public long modifiedDate;
    public int createdBy;
    public long createdDate;
    public String levelValueReference;
    public int levelNo;
    public String actionFlag;
    public int hierarchyDefinitionSid;
    public int modifiedBy;
    public String levelName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(29);

        sb.append("{tableName=");
        sb.append(tableName);
        sb.append(", actionDate=");
        sb.append(actionDate);
        sb.append(", fieldName=");
        sb.append(fieldName);
        sb.append(", hierarchyLevelDefinitionSid=");
        sb.append(hierarchyLevelDefinitionSid);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", levelValueReference=");
        sb.append(levelValueReference);
        sb.append(", levelNo=");
        sb.append(levelNo);
        sb.append(", actionFlag=");
        sb.append(actionFlag);
        sb.append(", hierarchyDefinitionSid=");
        sb.append(hierarchyDefinitionSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", levelName=");
        sb.append(levelName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public HistHierarchyLevelDefn toEntityModel() {
        HistHierarchyLevelDefnImpl histHierarchyLevelDefnImpl = new HistHierarchyLevelDefnImpl();

        if (tableName == null) {
            histHierarchyLevelDefnImpl.setTableName(StringPool.BLANK);
        } else {
            histHierarchyLevelDefnImpl.setTableName(tableName);
        }

        if (actionDate == Long.MIN_VALUE) {
            histHierarchyLevelDefnImpl.setActionDate(null);
        } else {
            histHierarchyLevelDefnImpl.setActionDate(new Date(actionDate));
        }

        if (fieldName == null) {
            histHierarchyLevelDefnImpl.setFieldName(StringPool.BLANK);
        } else {
            histHierarchyLevelDefnImpl.setFieldName(fieldName);
        }

        histHierarchyLevelDefnImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
        histHierarchyLevelDefnImpl.setVersionNo(versionNo);

        if (modifiedDate == Long.MIN_VALUE) {
            histHierarchyLevelDefnImpl.setModifiedDate(null);
        } else {
            histHierarchyLevelDefnImpl.setModifiedDate(new Date(modifiedDate));
        }

        histHierarchyLevelDefnImpl.setCreatedBy(createdBy);

        if (createdDate == Long.MIN_VALUE) {
            histHierarchyLevelDefnImpl.setCreatedDate(null);
        } else {
            histHierarchyLevelDefnImpl.setCreatedDate(new Date(createdDate));
        }

        if (levelValueReference == null) {
            histHierarchyLevelDefnImpl.setLevelValueReference(StringPool.BLANK);
        } else {
            histHierarchyLevelDefnImpl.setLevelValueReference(levelValueReference);
        }

        histHierarchyLevelDefnImpl.setLevelNo(levelNo);

        if (actionFlag == null) {
            histHierarchyLevelDefnImpl.setActionFlag(StringPool.BLANK);
        } else {
            histHierarchyLevelDefnImpl.setActionFlag(actionFlag);
        }

        histHierarchyLevelDefnImpl.setHierarchyDefinitionSid(hierarchyDefinitionSid);
        histHierarchyLevelDefnImpl.setModifiedBy(modifiedBy);

        if (levelName == null) {
            histHierarchyLevelDefnImpl.setLevelName(StringPool.BLANK);
        } else {
            histHierarchyLevelDefnImpl.setLevelName(levelName);
        }

        histHierarchyLevelDefnImpl.resetOriginalValues();

        return histHierarchyLevelDefnImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        tableName = objectInput.readUTF();
        actionDate = objectInput.readLong();
        fieldName = objectInput.readUTF();
        hierarchyLevelDefinitionSid = objectInput.readInt();
        versionNo = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        levelValueReference = objectInput.readUTF();
        levelNo = objectInput.readInt();
        actionFlag = objectInput.readUTF();
        hierarchyDefinitionSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        levelName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (tableName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tableName);
        }

        objectOutput.writeLong(actionDate);

        if (fieldName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fieldName);
        }

        objectOutput.writeInt(hierarchyLevelDefinitionSid);
        objectOutput.writeInt(versionNo);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(createdDate);

        if (levelValueReference == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(levelValueReference);
        }

        objectOutput.writeInt(levelNo);

        if (actionFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actionFlag);
        }

        objectOutput.writeInt(hierarchyDefinitionSid);
        objectOutput.writeInt(modifiedBy);

        if (levelName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(levelName);
        }
    }
}
