package com.stpl.app.model.impl;

import com.stpl.app.model.RsContractDetailsFr;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RsContractDetailsFr in entity cache.
 *
 * @author
 * @see RsContractDetailsFr
 * @generated
 */
public class RsContractDetailsFrCacheModel implements CacheModel<RsContractDetailsFr>,
    Externalizable {
    public boolean recordLockStatus;
    public long createdDate;
    public int createdBy;
    public String source;
    public String formulaMethodId;
    public int itemMasterSid;
    public String batchId;
    public int rsContractDetailsFrSid;
    public int modifiedBy;
    public String inboundStatus;
    public int formulaId;
    public long modifiedDate;
    public int rsContractDetailsSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", formulaMethodId=");
        sb.append(formulaMethodId);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", rsContractDetailsFrSid=");
        sb.append(rsContractDetailsFrSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", formulaId=");
        sb.append(formulaId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", rsContractDetailsSid=");
        sb.append(rsContractDetailsSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public RsContractDetailsFr toEntityModel() {
        RsContractDetailsFrImpl rsContractDetailsFrImpl = new RsContractDetailsFrImpl();

        rsContractDetailsFrImpl.setRecordLockStatus(recordLockStatus);

        if (createdDate == Long.MIN_VALUE) {
            rsContractDetailsFrImpl.setCreatedDate(null);
        } else {
            rsContractDetailsFrImpl.setCreatedDate(new Date(createdDate));
        }

        rsContractDetailsFrImpl.setCreatedBy(createdBy);

        if (source == null) {
            rsContractDetailsFrImpl.setSource(StringPool.BLANK);
        } else {
            rsContractDetailsFrImpl.setSource(source);
        }

        if (formulaMethodId == null) {
            rsContractDetailsFrImpl.setFormulaMethodId(StringPool.BLANK);
        } else {
            rsContractDetailsFrImpl.setFormulaMethodId(formulaMethodId);
        }

        rsContractDetailsFrImpl.setItemMasterSid(itemMasterSid);

        if (batchId == null) {
            rsContractDetailsFrImpl.setBatchId(StringPool.BLANK);
        } else {
            rsContractDetailsFrImpl.setBatchId(batchId);
        }

        rsContractDetailsFrImpl.setRsContractDetailsFrSid(rsContractDetailsFrSid);
        rsContractDetailsFrImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            rsContractDetailsFrImpl.setInboundStatus(StringPool.BLANK);
        } else {
            rsContractDetailsFrImpl.setInboundStatus(inboundStatus);
        }

        rsContractDetailsFrImpl.setFormulaId(formulaId);

        if (modifiedDate == Long.MIN_VALUE) {
            rsContractDetailsFrImpl.setModifiedDate(null);
        } else {
            rsContractDetailsFrImpl.setModifiedDate(new Date(modifiedDate));
        }

        rsContractDetailsFrImpl.setRsContractDetailsSid(rsContractDetailsSid);

        rsContractDetailsFrImpl.resetOriginalValues();

        return rsContractDetailsFrImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        recordLockStatus = objectInput.readBoolean();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        source = objectInput.readUTF();
        formulaMethodId = objectInput.readUTF();
        itemMasterSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        rsContractDetailsFrSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        formulaId = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        rsContractDetailsSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (formulaMethodId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaMethodId);
        }

        objectOutput.writeInt(itemMasterSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(rsContractDetailsFrSid);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(formulaId);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(rsContractDetailsSid);
    }
}
