package com.stpl.app.model.impl;

import com.stpl.app.model.CompanyGroupDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyGroupDetails in entity cache.
 *
 * @author
 * @see CompanyGroupDetails
 * @generated
 */
public class CompanyGroupDetailsCacheModel implements CacheModel<CompanyGroupDetails>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public String companyParentDetailsSid;
    public int companyTradeclassSid;
    public int companyGroupSid;
    public int versionNo;
    public int companyGroupDetailsSid;
    public int modifiedBy;
    public long modifiedDate;
    public int companyMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", companyParentDetailsSid=");
        sb.append(companyParentDetailsSid);
        sb.append(", companyTradeclassSid=");
        sb.append(companyTradeclassSid);
        sb.append(", companyGroupSid=");
        sb.append(companyGroupSid);
        sb.append(", versionNo=");
        sb.append(versionNo);
        sb.append(", companyGroupDetailsSid=");
        sb.append(companyGroupDetailsSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CompanyGroupDetails toEntityModel() {
        CompanyGroupDetailsImpl companyGroupDetailsImpl = new CompanyGroupDetailsImpl();

        if (createdDate == Long.MIN_VALUE) {
            companyGroupDetailsImpl.setCreatedDate(null);
        } else {
            companyGroupDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        companyGroupDetailsImpl.setCreatedBy(createdBy);

        if (companyParentDetailsSid == null) {
            companyGroupDetailsImpl.setCompanyParentDetailsSid(StringPool.BLANK);
        } else {
            companyGroupDetailsImpl.setCompanyParentDetailsSid(companyParentDetailsSid);
        }

        companyGroupDetailsImpl.setCompanyTradeclassSid(companyTradeclassSid);
        companyGroupDetailsImpl.setCompanyGroupSid(companyGroupSid);
        companyGroupDetailsImpl.setVersionNo(versionNo);
        companyGroupDetailsImpl.setCompanyGroupDetailsSid(companyGroupDetailsSid);
        companyGroupDetailsImpl.setModifiedBy(modifiedBy);

        if (modifiedDate == Long.MIN_VALUE) {
            companyGroupDetailsImpl.setModifiedDate(null);
        } else {
            companyGroupDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        companyGroupDetailsImpl.setCompanyMasterSid(companyMasterSid);

        companyGroupDetailsImpl.resetOriginalValues();

        return companyGroupDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        companyParentDetailsSid = objectInput.readUTF();
        companyTradeclassSid = objectInput.readInt();
        companyGroupSid = objectInput.readInt();
        versionNo = objectInput.readInt();
        companyGroupDetailsSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        companyMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (companyParentDetailsSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyParentDetailsSid);
        }

        objectOutput.writeInt(companyTradeclassSid);
        objectOutput.writeInt(companyGroupSid);
        objectOutput.writeInt(versionNo);
        objectOutput.writeInt(companyGroupDetailsSid);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(companyMasterSid);
    }
}
