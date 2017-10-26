package com.stpl.app.model.impl;

import com.stpl.app.model.HistHierarchyLevelValues;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistHierarchyLevelValues in entity cache.
 *
 * @author
 * @see HistHierarchyLevelValues
 * @generated
 */
public class HistHierarchyLevelValuesCacheModel implements CacheModel<HistHierarchyLevelValues>,
    Externalizable {
    public String levelValues;
    public int hierarchyLevelValuesSid;
    public long createdDate;
    public int createdBy;
    public long actionDate;
    public String actionFlag;
    public int hierarchyLevelDefinitionSid;
    public int versionNo;
    public int modifiedBy;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{levelValues=");
        sb.append(levelValues);
        sb.append(", hierarchyLevelValuesSid=");
        sb.append(hierarchyLevelValuesSid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", actionDate=");
        sb.append(actionDate);
        sb.append(", actionFlag=");
        sb.append(actionFlag);
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
    public HistHierarchyLevelValues toEntityModel() {
        HistHierarchyLevelValuesImpl histHierarchyLevelValuesImpl = new HistHierarchyLevelValuesImpl();

        if (levelValues == null) {
            histHierarchyLevelValuesImpl.setLevelValues(StringPool.BLANK);
        } else {
            histHierarchyLevelValuesImpl.setLevelValues(levelValues);
        }

        histHierarchyLevelValuesImpl.setHierarchyLevelValuesSid(hierarchyLevelValuesSid);

        if (createdDate == Long.MIN_VALUE) {
            histHierarchyLevelValuesImpl.setCreatedDate(null);
        } else {
            histHierarchyLevelValuesImpl.setCreatedDate(new Date(createdDate));
        }

        histHierarchyLevelValuesImpl.setCreatedBy(createdBy);

        if (actionDate == Long.MIN_VALUE) {
            histHierarchyLevelValuesImpl.setActionDate(null);
        } else {
            histHierarchyLevelValuesImpl.setActionDate(new Date(actionDate));
        }

        if (actionFlag == null) {
            histHierarchyLevelValuesImpl.setActionFlag(StringPool.BLANK);
        } else {
            histHierarchyLevelValuesImpl.setActionFlag(actionFlag);
        }

        histHierarchyLevelValuesImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
        histHierarchyLevelValuesImpl.setVersionNo(versionNo);
        histHierarchyLevelValuesImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            histHierarchyLevelValuesImpl.setModifiedDate(null);
        } else {
            histHierarchyLevelValuesImpl.setModifiedDate(new Date(modifiedDate));
        }

        histHierarchyLevelValuesImpl.resetOriginalValues();

        return histHierarchyLevelValuesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        levelValues = objectInput.readUTF();
        hierarchyLevelValuesSid = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        actionDate = objectInput.readLong();
        actionFlag = objectInput.readUTF();
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
        objectOutput.writeLong(actionDate);

        if (actionFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actionFlag);
        }

        objectOutput.writeInt(hierarchyLevelDefinitionSid);
        objectOutput.writeInt(versionNo);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);
    }
}
