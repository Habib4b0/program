package com.stpl.app.model.impl;

import com.stpl.app.model.LotMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LotMaster in entity cache.
 *
 * @author
 * @see LotMaster
 * @generated
 */
public class LotMasterCacheModel implements CacheModel<LotMaster>,
    Externalizable {
    public int createdBy;
    public int modifiedBy;
    public long createdDate;
    public String itemId;
    public String batchId;
    public long modifiedDate;
    public boolean recordLockStatus;
    public long lastLotSoldDate;
    public long lotExpirationDate;
    public String source;
    public int lotMasterSid;
    public String lotNo;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", lastLotSoldDate=");
        sb.append(lastLotSoldDate);
        sb.append(", lotExpirationDate=");
        sb.append(lotExpirationDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", lotMasterSid=");
        sb.append(lotMasterSid);
        sb.append(", lotNo=");
        sb.append(lotNo);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public LotMaster toEntityModel() {
        LotMasterImpl lotMasterImpl = new LotMasterImpl();

        lotMasterImpl.setCreatedBy(createdBy);
        lotMasterImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            lotMasterImpl.setCreatedDate(null);
        } else {
            lotMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (itemId == null) {
            lotMasterImpl.setItemId(StringPool.BLANK);
        } else {
            lotMasterImpl.setItemId(itemId);
        }

        if (batchId == null) {
            lotMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            lotMasterImpl.setBatchId(batchId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            lotMasterImpl.setModifiedDate(null);
        } else {
            lotMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        lotMasterImpl.setRecordLockStatus(recordLockStatus);

        if (lastLotSoldDate == Long.MIN_VALUE) {
            lotMasterImpl.setLastLotSoldDate(null);
        } else {
            lotMasterImpl.setLastLotSoldDate(new Date(lastLotSoldDate));
        }

        if (lotExpirationDate == Long.MIN_VALUE) {
            lotMasterImpl.setLotExpirationDate(null);
        } else {
            lotMasterImpl.setLotExpirationDate(new Date(lotExpirationDate));
        }

        if (source == null) {
            lotMasterImpl.setSource(StringPool.BLANK);
        } else {
            lotMasterImpl.setSource(source);
        }

        lotMasterImpl.setLotMasterSid(lotMasterSid);

        if (lotNo == null) {
            lotMasterImpl.setLotNo(StringPool.BLANK);
        } else {
            lotMasterImpl.setLotNo(lotNo);
        }

        if (inboundStatus == null) {
            lotMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            lotMasterImpl.setInboundStatus(inboundStatus);
        }

        lotMasterImpl.resetOriginalValues();

        return lotMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        itemId = objectInput.readUTF();
        batchId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        lastLotSoldDate = objectInput.readLong();
        lotExpirationDate = objectInput.readLong();
        source = objectInput.readUTF();
        lotMasterSid = objectInput.readInt();
        lotNo = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(lastLotSoldDate);
        objectOutput.writeLong(lotExpirationDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(lotMasterSid);

        if (lotNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lotNo);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
