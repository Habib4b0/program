package com.stpl.app.model.impl;

import com.stpl.app.model.ContractAliasMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ContractAliasMaster in entity cache.
 *
 * @author
 * @see ContractAliasMaster
 * @generated
 */
public class ContractAliasMasterCacheModel implements CacheModel<ContractAliasMaster>,
    Externalizable {
    public int contractAliasType;
    public int tpCompanyMasterSid;
    public long endDate;
    public int contractAliasMasterSid;
    public long modifiedDate;
    public String contractAliasNo;
    public boolean recordLockStatus;
    public long startDate;
    public long createdDate;
    public String source;
    public int createdBy;
    public int contractMasterSid;
    public String batchId;
    public String contractAliasName;
    public int modifiedBy;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(33);

        sb.append("{contractAliasType=");
        sb.append(contractAliasType);
        sb.append(", tpCompanyMasterSid=");
        sb.append(tpCompanyMasterSid);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", contractAliasMasterSid=");
        sb.append(contractAliasMasterSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", contractAliasNo=");
        sb.append(contractAliasNo);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", contractAliasName=");
        sb.append(contractAliasName);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContractAliasMaster toEntityModel() {
        ContractAliasMasterImpl contractAliasMasterImpl = new ContractAliasMasterImpl();

        contractAliasMasterImpl.setContractAliasType(contractAliasType);
        contractAliasMasterImpl.setTpCompanyMasterSid(tpCompanyMasterSid);

        if (endDate == Long.MIN_VALUE) {
            contractAliasMasterImpl.setEndDate(null);
        } else {
            contractAliasMasterImpl.setEndDate(new Date(endDate));
        }

        contractAliasMasterImpl.setContractAliasMasterSid(contractAliasMasterSid);

        if (modifiedDate == Long.MIN_VALUE) {
            contractAliasMasterImpl.setModifiedDate(null);
        } else {
            contractAliasMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (contractAliasNo == null) {
            contractAliasMasterImpl.setContractAliasNo(StringPool.BLANK);
        } else {
            contractAliasMasterImpl.setContractAliasNo(contractAliasNo);
        }

        contractAliasMasterImpl.setRecordLockStatus(recordLockStatus);

        if (startDate == Long.MIN_VALUE) {
            contractAliasMasterImpl.setStartDate(null);
        } else {
            contractAliasMasterImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            contractAliasMasterImpl.setCreatedDate(null);
        } else {
            contractAliasMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            contractAliasMasterImpl.setSource(StringPool.BLANK);
        } else {
            contractAliasMasterImpl.setSource(source);
        }

        contractAliasMasterImpl.setCreatedBy(createdBy);
        contractAliasMasterImpl.setContractMasterSid(contractMasterSid);

        if (batchId == null) {
            contractAliasMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            contractAliasMasterImpl.setBatchId(batchId);
        }

        if (contractAliasName == null) {
            contractAliasMasterImpl.setContractAliasName(StringPool.BLANK);
        } else {
            contractAliasMasterImpl.setContractAliasName(contractAliasName);
        }

        contractAliasMasterImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            contractAliasMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            contractAliasMasterImpl.setInboundStatus(inboundStatus);
        }

        contractAliasMasterImpl.resetOriginalValues();

        return contractAliasMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        contractAliasType = objectInput.readInt();
        tpCompanyMasterSid = objectInput.readInt();
        endDate = objectInput.readLong();
        contractAliasMasterSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        contractAliasNo = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdBy = objectInput.readInt();
        contractMasterSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        contractAliasName = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(contractAliasType);
        objectOutput.writeInt(tpCompanyMasterSid);
        objectOutput.writeLong(endDate);
        objectOutput.writeInt(contractAliasMasterSid);
        objectOutput.writeLong(modifiedDate);

        if (contractAliasNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractAliasNo);
        }

        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(startDate);
        objectOutput.writeLong(createdDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(contractMasterSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (contractAliasName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractAliasName);
        }

        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
