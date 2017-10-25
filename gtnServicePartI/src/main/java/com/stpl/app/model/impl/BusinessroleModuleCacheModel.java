package com.stpl.app.model.impl;

import com.stpl.app.model.BusinessroleModule;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BusinessroleModule in entity cache.
 *
 * @author
 * @see BusinessroleModule
 * @generated
 */
public class BusinessroleModuleCacheModel implements CacheModel<BusinessroleModule>,
    Externalizable {
    public int createdBy;
    public int businessroleModuleSid;
    public int businessroleMasterSid;
    public String addFlag;
    public String viewFlag;
    public int modifiedBy;
    public long createdDate;
    public int submodulePropertyId;
    public String editFlag;
    public int versionNo;
    public String accessModule;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", businessroleModuleSid=");
        sb.append(businessroleModuleSid);
        sb.append(", businessroleMasterSid=");
        sb.append(businessroleMasterSid);
        sb.append(", addFlag=");
        sb.append(addFlag);
        sb.append(", viewFlag=");
        sb.append(viewFlag);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", submodulePropertyId=");
        sb.append(submodulePropertyId);
        sb.append(", editFlag=");
        sb.append(editFlag);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", accessModule=");
        sb.append(accessModule);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public BusinessroleModule toEntityModel() {
        BusinessroleModuleImpl businessroleModuleImpl = new BusinessroleModuleImpl();

        businessroleModuleImpl.setCreatedBy(createdBy);
        businessroleModuleImpl.setBusinessroleModuleSid(businessroleModuleSid);
        businessroleModuleImpl.setBusinessroleMasterSid(businessroleMasterSid);

        if (addFlag == null) {
            businessroleModuleImpl.setAddFlag(StringPool.BLANK);
        } else {
            businessroleModuleImpl.setAddFlag(addFlag);
        }

        if (viewFlag == null) {
            businessroleModuleImpl.setViewFlag(StringPool.BLANK);
        } else {
            businessroleModuleImpl.setViewFlag(viewFlag);
        }

        businessroleModuleImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            businessroleModuleImpl.setCreatedDate(null);
        } else {
            businessroleModuleImpl.setCreatedDate(new Date(createdDate));
        }

        businessroleModuleImpl.setSubmodulePropertyId(submodulePropertyId);

        if (editFlag == null) {
            businessroleModuleImpl.setEditFlag(StringPool.BLANK);
        } else {
            businessroleModuleImpl.setEditFlag(editFlag);
        }

        businessroleModuleImpl.setVersionNo(versionNo);

        if (accessModule == null) {
            businessroleModuleImpl.setAccessModule(StringPool.BLANK);
        } else {
            businessroleModuleImpl.setAccessModule(accessModule);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            businessroleModuleImpl.setModifiedDate(null);
        } else {
            businessroleModuleImpl.setModifiedDate(new Date(modifiedDate));
        }

        businessroleModuleImpl.resetOriginalValues();

        return businessroleModuleImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        businessroleModuleSid = objectInput.readInt();
        businessroleMasterSid = objectInput.readInt();
        addFlag = objectInput.readUTF();
        viewFlag = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        submodulePropertyId = objectInput.readInt();
        editFlag = objectInput.readUTF();
        versionNo = objectInput.readInt();
        accessModule = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(businessroleModuleSid);
        objectOutput.writeInt(businessroleMasterSid);

        if (addFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addFlag);
        }

        if (viewFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(viewFlag);
        }

        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(submodulePropertyId);

        if (editFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(editFlag);
        }

        objectOutput.writeInt(versionNo);

        if (accessModule == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accessModule);
        }

        objectOutput.writeLong(modifiedDate);
    }
}
