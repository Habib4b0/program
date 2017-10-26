package com.stpl.app.model.impl;

import com.stpl.app.model.BusinessroleMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BusinessroleMaster in entity cache.
 *
 * @author
 * @see BusinessroleMaster
 * @generated
 */
public class BusinessroleMasterCacheModel implements CacheModel<BusinessroleMaster>,
    Externalizable {
    public int businessroleMasterSid;
    public long createdDate;
    public int createdBy;
    public int usersSid;
    public int hierarchyLevel;
    public String processed;
    public String businessroleName;
    public int versionNo;
    public int modifiedBy;
    public long modifiedDate;
    public String businessroleDesc;
    public String isActive;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{businessroleMasterSid=");
        sb.append(businessroleMasterSid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", usersSid=");
        sb.append(usersSid);
        sb.append(", hierarchyLevel=");
        sb.append(hierarchyLevel);
        sb.append(", processed=");
        sb.append(processed);
        sb.append(", businessroleName=");
        sb.append(businessroleName);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", businessroleDesc=");
        sb.append(businessroleDesc);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public BusinessroleMaster toEntityModel() {
        BusinessroleMasterImpl businessroleMasterImpl = new BusinessroleMasterImpl();

        businessroleMasterImpl.setBusinessroleMasterSid(businessroleMasterSid);

        if (createdDate == Long.MIN_VALUE) {
            businessroleMasterImpl.setCreatedDate(null);
        } else {
            businessroleMasterImpl.setCreatedDate(new Date(createdDate));
        }

        businessroleMasterImpl.setCreatedBy(createdBy);
        businessroleMasterImpl.setUsersSid(usersSid);
        businessroleMasterImpl.setHierarchyLevel(hierarchyLevel);

        if (processed == null) {
            businessroleMasterImpl.setProcessed(StringPool.BLANK);
        } else {
            businessroleMasterImpl.setProcessed(processed);
        }

        if (businessroleName == null) {
            businessroleMasterImpl.setBusinessroleName(StringPool.BLANK);
        } else {
            businessroleMasterImpl.setBusinessroleName(businessroleName);
        }

        businessroleMasterImpl.setVersionNo(versionNo);
        businessroleMasterImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            businessroleMasterImpl.setModifiedDate(null);
        } else {
            businessroleMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (businessroleDesc == null) {
            businessroleMasterImpl.setBusinessroleDesc(StringPool.BLANK);
        } else {
            businessroleMasterImpl.setBusinessroleDesc(businessroleDesc);
        }

        if (isActive == null) {
            businessroleMasterImpl.setIsActive(StringPool.BLANK);
        } else {
            businessroleMasterImpl.setIsActive(isActive);
        }

        businessroleMasterImpl.resetOriginalValues();

        return businessroleMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        businessroleMasterSid = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        usersSid = objectInput.readInt();
        hierarchyLevel = objectInput.readInt();
        processed = objectInput.readUTF();
        businessroleName = objectInput.readUTF();
        versionNo = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        businessroleDesc = objectInput.readUTF();
        isActive = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(businessroleMasterSid);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(usersSid);
        objectOutput.writeInt(hierarchyLevel);

        if (processed == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(processed);
        }

        if (businessroleName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(businessroleName);
        }

        objectOutput.writeInt(versionNo);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);

        if (businessroleDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(businessroleDesc);
        }

        if (isActive == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isActive);
        }
    }
}
