package com.stpl.app.model.impl;

import com.stpl.app.model.NaProjMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NaProjMaster in entity cache.
 *
 * @author
 * @see NaProjMaster
 * @generated
 */
public class NaProjMasterCacheModel implements CacheModel<NaProjMaster>,
    Externalizable {
    public String naProjName;
    public long createdDate;
    public int createdBy;
    public boolean saveFlag;
    public int modifiedBy;
    public long modifiedDate;
    public int naProjMasterSid;
    public int itemGroupSid;
    public int therapeuticClass;
    public int companyMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{naProjName=");
        sb.append(naProjName);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", saveFlag=");
        sb.append(saveFlag);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", naProjMasterSid=");
        sb.append(naProjMasterSid);
        sb.append(", itemGroupSid=");
        sb.append(itemGroupSid);
        sb.append(", therapeuticClass=");
        sb.append(therapeuticClass);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NaProjMaster toEntityModel() {
        NaProjMasterImpl naProjMasterImpl = new NaProjMasterImpl();

        if (naProjName == null) {
            naProjMasterImpl.setNaProjName(StringPool.BLANK);
        } else {
            naProjMasterImpl.setNaProjName(naProjName);
        }

        if (createdDate == Long.MIN_VALUE) {
            naProjMasterImpl.setCreatedDate(null);
        } else {
            naProjMasterImpl.setCreatedDate(new Date(createdDate));
        }

        naProjMasterImpl.setCreatedBy(createdBy);
        naProjMasterImpl.setSaveFlag(saveFlag);
        naProjMasterImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            naProjMasterImpl.setModifiedDate(null);
        } else {
            naProjMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        naProjMasterImpl.setNaProjMasterSid(naProjMasterSid);
        naProjMasterImpl.setItemGroupSid(itemGroupSid);
        naProjMasterImpl.setTherapeuticClass(therapeuticClass);
        naProjMasterImpl.setCompanyMasterSid(companyMasterSid);

        naProjMasterImpl.resetOriginalValues();

        return naProjMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        naProjName = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        saveFlag = objectInput.readBoolean();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        naProjMasterSid = objectInput.readInt();
        itemGroupSid = objectInput.readInt();
        therapeuticClass = objectInput.readInt();
        companyMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (naProjName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(naProjName);
        }

        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeBoolean(saveFlag);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(naProjMasterSid);
        objectOutput.writeInt(itemGroupSid);
        objectOutput.writeInt(therapeuticClass);
        objectOutput.writeInt(companyMasterSid);
    }
}
