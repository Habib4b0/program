package com.stpl.app.model.impl;

import com.stpl.app.model.FormulaDetailsMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FormulaDetailsMaster in entity cache.
 *
 * @author
 * @see FormulaDetailsMaster
 * @generated
 */
public class FormulaDetailsMasterCacheModel implements CacheModel<FormulaDetailsMaster>,
    Externalizable {
    public String companyId;
    public double contractPrice1;
    public double contractPrice2;
    public long endDate;
    public String formulaNo;
    public int formulaDetailsMasterSid;
    public String itemId;
    public double rebatePercent1;
    public long modifiedDate;
    public String formulaDesc;
    public double rebatePercent2;
    public double rebatePercent3;
    public boolean recordLockStatus;
    public long startDate;
    public long createdDate;
    public int createdBy;
    public String source;
    public String batchId;
    public double contractPrice3;
    public int modifiedBy;
    public String inboundStatus;
    public String formulaId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(45);

        sb.append("{companyId=");
        sb.append(companyId);
        sb.append(", contractPrice1=");
        sb.append(contractPrice1);
        sb.append(", contractPrice2=");
        sb.append(contractPrice2);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", formulaNo=");
        sb.append(formulaNo);
        sb.append(", formulaDetailsMasterSid=");
        sb.append(formulaDetailsMasterSid);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", rebatePercent1=");
        sb.append(rebatePercent1);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", formulaDesc=");
        sb.append(formulaDesc);
        sb.append(", rebatePercent2=");
        sb.append(rebatePercent2);
        sb.append(", rebatePercent3=");
        sb.append(rebatePercent3);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", contractPrice3=");
        sb.append(contractPrice3);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", formulaId=");
        sb.append(formulaId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public FormulaDetailsMaster toEntityModel() {
        FormulaDetailsMasterImpl formulaDetailsMasterImpl = new FormulaDetailsMasterImpl();

        if (companyId == null) {
            formulaDetailsMasterImpl.setCompanyId(StringPool.BLANK);
        } else {
            formulaDetailsMasterImpl.setCompanyId(companyId);
        }

        formulaDetailsMasterImpl.setContractPrice1(contractPrice1);
        formulaDetailsMasterImpl.setContractPrice2(contractPrice2);

        if (endDate == Long.MIN_VALUE) {
            formulaDetailsMasterImpl.setEndDate(null);
        } else {
            formulaDetailsMasterImpl.setEndDate(new Date(endDate));
        }

        if (formulaNo == null) {
            formulaDetailsMasterImpl.setFormulaNo(StringPool.BLANK);
        } else {
            formulaDetailsMasterImpl.setFormulaNo(formulaNo);
        }

        formulaDetailsMasterImpl.setFormulaDetailsMasterSid(formulaDetailsMasterSid);

        if (itemId == null) {
            formulaDetailsMasterImpl.setItemId(StringPool.BLANK);
        } else {
            formulaDetailsMasterImpl.setItemId(itemId);
        }

        formulaDetailsMasterImpl.setRebatePercent1(rebatePercent1);

        if (modifiedDate == Long.MIN_VALUE) {
            formulaDetailsMasterImpl.setModifiedDate(null);
        } else {
            formulaDetailsMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (formulaDesc == null) {
            formulaDetailsMasterImpl.setFormulaDesc(StringPool.BLANK);
        } else {
            formulaDetailsMasterImpl.setFormulaDesc(formulaDesc);
        }

        formulaDetailsMasterImpl.setRebatePercent2(rebatePercent2);
        formulaDetailsMasterImpl.setRebatePercent3(rebatePercent3);
        formulaDetailsMasterImpl.setRecordLockStatus(recordLockStatus);

        if (startDate == Long.MIN_VALUE) {
            formulaDetailsMasterImpl.setStartDate(null);
        } else {
            formulaDetailsMasterImpl.setStartDate(new Date(startDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            formulaDetailsMasterImpl.setCreatedDate(null);
        } else {
            formulaDetailsMasterImpl.setCreatedDate(new Date(createdDate));
        }

        formulaDetailsMasterImpl.setCreatedBy(createdBy);

        if (source == null) {
            formulaDetailsMasterImpl.setSource(StringPool.BLANK);
        } else {
            formulaDetailsMasterImpl.setSource(source);
        }

        if (batchId == null) {
            formulaDetailsMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            formulaDetailsMasterImpl.setBatchId(batchId);
        }

        formulaDetailsMasterImpl.setContractPrice3(contractPrice3);
        formulaDetailsMasterImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            formulaDetailsMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            formulaDetailsMasterImpl.setInboundStatus(inboundStatus);
        }

        if (formulaId == null) {
            formulaDetailsMasterImpl.setFormulaId(StringPool.BLANK);
        } else {
            formulaDetailsMasterImpl.setFormulaId(formulaId);
        }

        formulaDetailsMasterImpl.resetOriginalValues();

        return formulaDetailsMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        companyId = objectInput.readUTF();
        contractPrice1 = objectInput.readDouble();
        contractPrice2 = objectInput.readDouble();
        endDate = objectInput.readLong();
        formulaNo = objectInput.readUTF();
        formulaDetailsMasterSid = objectInput.readInt();
        itemId = objectInput.readUTF();
        rebatePercent1 = objectInput.readDouble();
        modifiedDate = objectInput.readLong();
        formulaDesc = objectInput.readUTF();
        rebatePercent2 = objectInput.readDouble();
        rebatePercent3 = objectInput.readDouble();
        recordLockStatus = objectInput.readBoolean();
        startDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        source = objectInput.readUTF();
        batchId = objectInput.readUTF();
        contractPrice3 = objectInput.readDouble();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        formulaId = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        objectOutput.writeDouble(contractPrice1);
        objectOutput.writeDouble(contractPrice2);
        objectOutput.writeLong(endDate);

        if (formulaNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaNo);
        }

        objectOutput.writeInt(formulaDetailsMasterSid);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeDouble(rebatePercent1);
        objectOutput.writeLong(modifiedDate);

        if (formulaDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaDesc);
        }

        objectOutput.writeDouble(rebatePercent2);
        objectOutput.writeDouble(rebatePercent3);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(startDate);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeDouble(contractPrice3);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (formulaId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaId);
        }
    }
}
