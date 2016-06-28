package com.stpl.app.model.impl;

import com.stpl.app.model.ModuleProperties;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ModuleProperties in entity cache.
 *
 * @author
 * @see ModuleProperties
 * @generated
 */
public class ModulePropertiesCacheModel implements CacheModel<ModuleProperties>,
    Externalizable {
    public int modulePropertySid;
    public int createdBy;
    public String moduleName;
    public int modifiedBy;
    public long createdDate;
    public String nullFlag;
    public int versionNo;
    public int moduleSubmoduleSid;
    public String categoryName;
    public String propertyName;
    public String displayName;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{modulePropertySid=");
        sb.append(modulePropertySid);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", moduleName=");
        sb.append(moduleName);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", nullFlag=");
        sb.append(nullFlag);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", moduleSubmoduleSid=");
        sb.append(moduleSubmoduleSid);
        sb.append(", categoryName=");
        sb.append(categoryName);
        sb.append(", propertyName=");
        sb.append(propertyName);
        sb.append(", displayName=");
        sb.append(displayName);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ModuleProperties toEntityModel() {
        ModulePropertiesImpl modulePropertiesImpl = new ModulePropertiesImpl();

        modulePropertiesImpl.setModulePropertySid(modulePropertySid);
        modulePropertiesImpl.setCreatedBy(createdBy);

        if (moduleName == null) {
            modulePropertiesImpl.setModuleName(StringPool.BLANK);
        } else {
            modulePropertiesImpl.setModuleName(moduleName);
        }

        modulePropertiesImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            modulePropertiesImpl.setCreatedDate(null);
        } else {
            modulePropertiesImpl.setCreatedDate(new Date(createdDate));
        }

        if (nullFlag == null) {
            modulePropertiesImpl.setNullFlag(StringPool.BLANK);
        } else {
            modulePropertiesImpl.setNullFlag(nullFlag);
        }

        modulePropertiesImpl.setVersionNo(versionNo);
        modulePropertiesImpl.setModuleSubmoduleSid(moduleSubmoduleSid);

        if (categoryName == null) {
            modulePropertiesImpl.setCategoryName(StringPool.BLANK);
        } else {
            modulePropertiesImpl.setCategoryName(categoryName);
        }

        if (propertyName == null) {
            modulePropertiesImpl.setPropertyName(StringPool.BLANK);
        } else {
            modulePropertiesImpl.setPropertyName(propertyName);
        }

        if (displayName == null) {
            modulePropertiesImpl.setDisplayName(StringPool.BLANK);
        } else {
            modulePropertiesImpl.setDisplayName(displayName);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            modulePropertiesImpl.setModifiedDate(null);
        } else {
            modulePropertiesImpl.setModifiedDate(new Date(modifiedDate));
        }

        modulePropertiesImpl.resetOriginalValues();

        return modulePropertiesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        modulePropertySid = objectInput.readInt();
        createdBy = objectInput.readInt();
        moduleName = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        nullFlag = objectInput.readUTF();
        versionNo = objectInput.readInt();
        moduleSubmoduleSid = objectInput.readInt();
        categoryName = objectInput.readUTF();
        propertyName = objectInput.readUTF();
        displayName = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(modulePropertySid);
        objectOutput.writeInt(createdBy);

        if (moduleName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(moduleName);
        }

        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);

        if (nullFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(nullFlag);
        }

        objectOutput.writeInt(versionNo);
        objectOutput.writeInt(moduleSubmoduleSid);

        if (categoryName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(categoryName);
        }

        if (propertyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(propertyName);
        }

        if (displayName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(displayName);
        }

        objectOutput.writeLong(modifiedDate);
    }
}
