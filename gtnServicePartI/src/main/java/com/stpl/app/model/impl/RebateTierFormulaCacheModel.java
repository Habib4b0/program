package com.stpl.app.model.impl;

import com.stpl.app.model.RebateTierFormula;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RebateTierFormula in entity cache.
 *
 * @author
 * @see RebateTierFormula
 * @generated
 */
public class RebateTierFormulaCacheModel implements CacheModel<RebateTierFormula>,
    Externalizable {
    public String rebateTierFormulaNo;
    public String rebateTierFormulaName;
    public int rebatePlanMasterSid;
    public long modifiedDate;
    public boolean recordLockStatus;
    public String source;
    public int createdBy;
    public long createdDate;
    public String batchId;
    public String rebateTierFormulaId;
    public String inboundStatus;
    public int modifiedBy;
    public int rebateTierFormulaSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{rebateTierFormulaNo=");
        sb.append(rebateTierFormulaNo);
        sb.append(", rebateTierFormulaName=");
        sb.append(rebateTierFormulaName);
        sb.append(", rebatePlanMasterSid=");
        sb.append(rebatePlanMasterSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", rebateTierFormulaId=");
        sb.append(rebateTierFormulaId);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", rebateTierFormulaSid=");
        sb.append(rebateTierFormulaSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public RebateTierFormula toEntityModel() {
        RebateTierFormulaImpl rebateTierFormulaImpl = new RebateTierFormulaImpl();

        if (rebateTierFormulaNo == null) {
            rebateTierFormulaImpl.setRebateTierFormulaNo(StringPool.BLANK);
        } else {
            rebateTierFormulaImpl.setRebateTierFormulaNo(rebateTierFormulaNo);
        }

        if (rebateTierFormulaName == null) {
            rebateTierFormulaImpl.setRebateTierFormulaName(StringPool.BLANK);
        } else {
            rebateTierFormulaImpl.setRebateTierFormulaName(rebateTierFormulaName);
        }

        rebateTierFormulaImpl.setRebatePlanMasterSid(rebatePlanMasterSid);

        if (modifiedDate == Long.MIN_VALUE) {
            rebateTierFormulaImpl.setModifiedDate(null);
        } else {
            rebateTierFormulaImpl.setModifiedDate(new Date(modifiedDate));
        }

        rebateTierFormulaImpl.setRecordLockStatus(recordLockStatus);

        if (source == null) {
            rebateTierFormulaImpl.setSource(StringPool.BLANK);
        } else {
            rebateTierFormulaImpl.setSource(source);
        }

        rebateTierFormulaImpl.setCreatedBy(createdBy);

        if (createdDate == Long.MIN_VALUE) {
            rebateTierFormulaImpl.setCreatedDate(null);
        } else {
            rebateTierFormulaImpl.setCreatedDate(new Date(createdDate));
        }

        if (batchId == null) {
            rebateTierFormulaImpl.setBatchId(StringPool.BLANK);
        } else {
            rebateTierFormulaImpl.setBatchId(batchId);
        }

        if (rebateTierFormulaId == null) {
            rebateTierFormulaImpl.setRebateTierFormulaId(StringPool.BLANK);
        } else {
            rebateTierFormulaImpl.setRebateTierFormulaId(rebateTierFormulaId);
        }

        if (inboundStatus == null) {
            rebateTierFormulaImpl.setInboundStatus(StringPool.BLANK);
        } else {
            rebateTierFormulaImpl.setInboundStatus(inboundStatus);
        }

        rebateTierFormulaImpl.setModifiedBy(modifiedBy);
        rebateTierFormulaImpl.setRebateTierFormulaSid(rebateTierFormulaSid);

        rebateTierFormulaImpl.resetOriginalValues();

        return rebateTierFormulaImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        rebateTierFormulaNo = objectInput.readUTF();
        rebateTierFormulaName = objectInput.readUTF();
        rebatePlanMasterSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        source = objectInput.readUTF();
        createdBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        batchId = objectInput.readUTF();
        rebateTierFormulaId = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        rebateTierFormulaSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (rebateTierFormulaNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebateTierFormulaNo);
        }

        if (rebateTierFormulaName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebateTierFormulaName);
        }

        objectOutput.writeInt(rebatePlanMasterSid);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(recordLockStatus);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(createdDate);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (rebateTierFormulaId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebateTierFormulaId);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(modifiedBy);
        objectOutput.writeInt(rebateTierFormulaSid);
    }
}
