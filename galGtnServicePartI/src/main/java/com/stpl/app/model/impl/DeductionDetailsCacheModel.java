package com.stpl.app.model.impl;

import com.stpl.app.model.DeductionDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DeductionDetails in entity cache.
 *
 * @author
 * @see DeductionDetails
 * @generated
 */
public class DeductionDetailsCacheModel implements CacheModel<DeductionDetails>,
    Externalizable {
    public int createdBy;
    public int netSalesFormulaMasterSid;
    public int modifiedBy;
    public int rsContractSid;
    public long createdDate;
    public int contractMasterSid;
    public int deductionDetailsSid;
    public String indicator;
    public long modifiedDate;
    public boolean recordLockStatus;
    public String source;
    public int cdrModelSid;
    public String inboundStatus;
    public String deductionSubType;
    public String deductionType;
    public String deductionCategory;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(33);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(netSalesFormulaMasterSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", rsContractSid=");
        sb.append(rsContractSid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", deductionDetailsSid=");
        sb.append(deductionDetailsSid);
        sb.append(", indicator=");
        sb.append(indicator);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", source=");
        sb.append(source);
        sb.append(", cdrModelSid=");
        sb.append(cdrModelSid);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", deductionSubType=");
        sb.append(deductionSubType);
        sb.append(", deductionType=");
        sb.append(deductionType);
        sb.append(", deductionCategory=");
        sb.append(deductionCategory);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public DeductionDetails toEntityModel() {
        DeductionDetailsImpl deductionDetailsImpl = new DeductionDetailsImpl();

        deductionDetailsImpl.setCreatedBy(createdBy);
        deductionDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        deductionDetailsImpl.setModifiedBy(modifiedBy);
        deductionDetailsImpl.setRsContractSid(rsContractSid);

        if (createdDate == Long.MIN_VALUE) {
            deductionDetailsImpl.setCreatedDate(null);
        } else {
            deductionDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        deductionDetailsImpl.setContractMasterSid(contractMasterSid);
        deductionDetailsImpl.setDeductionDetailsSid(deductionDetailsSid);

        if (indicator == null) {
            deductionDetailsImpl.setIndicator(StringPool.BLANK);
        } else {
            deductionDetailsImpl.setIndicator(indicator);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            deductionDetailsImpl.setModifiedDate(null);
        } else {
            deductionDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        deductionDetailsImpl.setRecordLockStatus(recordLockStatus);

        if (source == null) {
            deductionDetailsImpl.setSource(StringPool.BLANK);
        } else {
            deductionDetailsImpl.setSource(source);
        }

        deductionDetailsImpl.setCdrModelSid(cdrModelSid);

        if (inboundStatus == null) {
            deductionDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            deductionDetailsImpl.setInboundStatus(inboundStatus);
        }

        if (deductionSubType == null) {
            deductionDetailsImpl.setDeductionSubType(StringPool.BLANK);
        } else {
            deductionDetailsImpl.setDeductionSubType(deductionSubType);
        }

        if (deductionType == null) {
            deductionDetailsImpl.setDeductionType(StringPool.BLANK);
        } else {
            deductionDetailsImpl.setDeductionType(deductionType);
        }

        if (deductionCategory == null) {
            deductionDetailsImpl.setDeductionCategory(StringPool.BLANK);
        } else {
            deductionDetailsImpl.setDeductionCategory(deductionCategory);
        }

        deductionDetailsImpl.resetOriginalValues();

        return deductionDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        netSalesFormulaMasterSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        rsContractSid = objectInput.readInt();
        createdDate = objectInput.readLong();
        contractMasterSid = objectInput.readInt();
        deductionDetailsSid = objectInput.readInt();
        indicator = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        recordLockStatus = objectInput.readBoolean();
        source = objectInput.readUTF();
        cdrModelSid = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        deductionSubType = objectInput.readUTF();
        deductionType = objectInput.readUTF();
        deductionCategory = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(netSalesFormulaMasterSid);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeInt(rsContractSid);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(contractMasterSid);
        objectOutput.writeInt(deductionDetailsSid);

        if (indicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(indicator);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(recordLockStatus);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(cdrModelSid);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (deductionSubType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionSubType);
        }

        if (deductionType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionType);
        }

        if (deductionCategory == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionCategory);
        }
    }
}
