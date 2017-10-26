package com.stpl.app.model.impl;

import com.stpl.app.model.UsergroupBusinessrole;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UsergroupBusinessrole in entity cache.
 *
 * @author
 * @see UsergroupBusinessrole
 * @generated
 */
public class UsergroupBusinessroleCacheModel implements CacheModel<UsergroupBusinessrole>,
    Externalizable {
    public int createdBy;
    public int businessroleMasterSid;
    public int usersSid;
    public int modifiedBy;
    public long createdDate;
    public String processed;
    public int usergroupId;
    public int versionNo;
    public String isActive;
    public int usergroupBusinessroleSid;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", businessroleMasterSid=");
        sb.append(businessroleMasterSid);
        sb.append(", usersSid=");
        sb.append(usersSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", processed=");
        sb.append(processed);
        sb.append(", usergroupId=");
        sb.append(usergroupId);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append(", usergroupBusinessroleSid=");
        sb.append(usergroupBusinessroleSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public UsergroupBusinessrole toEntityModel() {
        UsergroupBusinessroleImpl usergroupBusinessroleImpl = new UsergroupBusinessroleImpl();

        usergroupBusinessroleImpl.setCreatedBy(createdBy);
        usergroupBusinessroleImpl.setBusinessroleMasterSid(businessroleMasterSid);
        usergroupBusinessroleImpl.setUsersSid(usersSid);
        usergroupBusinessroleImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            usergroupBusinessroleImpl.setCreatedDate(null);
        } else {
            usergroupBusinessroleImpl.setCreatedDate(new Date(createdDate));
        }

        if (processed == null) {
            usergroupBusinessroleImpl.setProcessed(StringPool.BLANK);
        } else {
            usergroupBusinessroleImpl.setProcessed(processed);
        }

        usergroupBusinessroleImpl.setUsergroupId(usergroupId);
        usergroupBusinessroleImpl.setVersionNo(versionNo);

        if (isActive == null) {
            usergroupBusinessroleImpl.setIsActive(StringPool.BLANK);
        } else {
            usergroupBusinessroleImpl.setIsActive(isActive);
        }

        usergroupBusinessroleImpl.setUsergroupBusinessroleSid(usergroupBusinessroleSid);

        if (modifiedDate == Long.MIN_VALUE) {
            usergroupBusinessroleImpl.setModifiedDate(null);
        } else {
            usergroupBusinessroleImpl.setModifiedDate(new Date(modifiedDate));
        }

        usergroupBusinessroleImpl.resetOriginalValues();

        return usergroupBusinessroleImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        businessroleMasterSid = objectInput.readInt();
        usersSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        processed = objectInput.readUTF();
        usergroupId = objectInput.readInt();
        versionNo = objectInput.readInt();
        isActive = objectInput.readUTF();
        usergroupBusinessroleSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(businessroleMasterSid);
        objectOutput.writeInt(usersSid);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);

        if (processed == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(processed);
        }

        objectOutput.writeInt(usergroupId);
        objectOutput.writeInt(versionNo);

        if (isActive == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isActive);
        }

        objectOutput.writeInt(usergroupBusinessroleSid);
        objectOutput.writeLong(modifiedDate);
    }
}
