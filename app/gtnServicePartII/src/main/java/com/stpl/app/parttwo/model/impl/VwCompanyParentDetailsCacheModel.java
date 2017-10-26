package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.VwCompanyParentDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwCompanyParentDetails in entity cache.
 *
 * @author
 * @see VwCompanyParentDetails
 * @generated
 */
public class VwCompanyParentDetailsCacheModel implements CacheModel<VwCompanyParentDetails>,
    Externalizable {
    public String parentCompanyId;
    public String priorParentCompanyId;
    public String companyId;
    public long lastUpdatedDate;
    public long parentEndDate;
    public long modifiedDate;
    public long priorParentStartDate;
    public String source;
    public String createdBy;
    public long createdDate;
    public int companyParentDetailsSid;
    public String batchId;
    public String addChgDelIndicator;
    public String modifiedBy;
    public long parentStartDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{parentCompanyId=");
        sb.append(parentCompanyId);
        sb.append(", priorParentCompanyId=");
        sb.append(priorParentCompanyId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", lastUpdatedDate=");
        sb.append(lastUpdatedDate);
        sb.append(", parentEndDate=");
        sb.append(parentEndDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", priorParentStartDate=");
        sb.append(priorParentStartDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", companyParentDetailsSid=");
        sb.append(companyParentDetailsSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", parentStartDate=");
        sb.append(parentStartDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwCompanyParentDetails toEntityModel() {
        VwCompanyParentDetailsImpl vwCompanyParentDetailsImpl = new VwCompanyParentDetailsImpl();

        if (parentCompanyId == null) {
            vwCompanyParentDetailsImpl.setParentCompanyId(StringPool.BLANK);
        } else {
            vwCompanyParentDetailsImpl.setParentCompanyId(parentCompanyId);
        }

        if (priorParentCompanyId == null) {
            vwCompanyParentDetailsImpl.setPriorParentCompanyId(StringPool.BLANK);
        } else {
            vwCompanyParentDetailsImpl.setPriorParentCompanyId(priorParentCompanyId);
        }

        if (companyId == null) {
            vwCompanyParentDetailsImpl.setCompanyId(StringPool.BLANK);
        } else {
            vwCompanyParentDetailsImpl.setCompanyId(companyId);
        }

        if (lastUpdatedDate == Long.MIN_VALUE) {
            vwCompanyParentDetailsImpl.setLastUpdatedDate(null);
        } else {
            vwCompanyParentDetailsImpl.setLastUpdatedDate(new Date(
                    lastUpdatedDate));
        }

        if (parentEndDate == Long.MIN_VALUE) {
            vwCompanyParentDetailsImpl.setParentEndDate(null);
        } else {
            vwCompanyParentDetailsImpl.setParentEndDate(new Date(parentEndDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            vwCompanyParentDetailsImpl.setModifiedDate(null);
        } else {
            vwCompanyParentDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (priorParentStartDate == Long.MIN_VALUE) {
            vwCompanyParentDetailsImpl.setPriorParentStartDate(null);
        } else {
            vwCompanyParentDetailsImpl.setPriorParentStartDate(new Date(
                    priorParentStartDate));
        }

        if (source == null) {
            vwCompanyParentDetailsImpl.setSource(StringPool.BLANK);
        } else {
            vwCompanyParentDetailsImpl.setSource(source);
        }

        if (createdBy == null) {
            vwCompanyParentDetailsImpl.setCreatedBy(StringPool.BLANK);
        } else {
            vwCompanyParentDetailsImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            vwCompanyParentDetailsImpl.setCreatedDate(null);
        } else {
            vwCompanyParentDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        vwCompanyParentDetailsImpl.setCompanyParentDetailsSid(companyParentDetailsSid);

        if (batchId == null) {
            vwCompanyParentDetailsImpl.setBatchId(StringPool.BLANK);
        } else {
            vwCompanyParentDetailsImpl.setBatchId(batchId);
        }

        if (addChgDelIndicator == null) {
            vwCompanyParentDetailsImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            vwCompanyParentDetailsImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (modifiedBy == null) {
            vwCompanyParentDetailsImpl.setModifiedBy(StringPool.BLANK);
        } else {
            vwCompanyParentDetailsImpl.setModifiedBy(modifiedBy);
        }

        if (parentStartDate == Long.MIN_VALUE) {
            vwCompanyParentDetailsImpl.setParentStartDate(null);
        } else {
            vwCompanyParentDetailsImpl.setParentStartDate(new Date(
                    parentStartDate));
        }

        vwCompanyParentDetailsImpl.resetOriginalValues();

        return vwCompanyParentDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        parentCompanyId = objectInput.readUTF();
        priorParentCompanyId = objectInput.readUTF();
        companyId = objectInput.readUTF();
        lastUpdatedDate = objectInput.readLong();
        parentEndDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        priorParentStartDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        companyParentDetailsSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        parentStartDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (parentCompanyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentCompanyId);
        }

        if (priorParentCompanyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priorParentCompanyId);
        }

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        objectOutput.writeLong(lastUpdatedDate);
        objectOutput.writeLong(parentEndDate);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeLong(priorParentStartDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(companyParentDetailsSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        objectOutput.writeLong(parentStartDate);
    }
}
