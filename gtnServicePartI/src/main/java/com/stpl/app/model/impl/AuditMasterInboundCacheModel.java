package com.stpl.app.model.impl;

import com.stpl.app.model.AuditMasterInbound;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AuditMasterInbound in entity cache.
 *
 * @author
 * @see AuditMasterInbound
 * @generated
 */
public class AuditMasterInboundCacheModel implements CacheModel<AuditMasterInbound>,
    Externalizable {
    public String receivedRecordAmountAttr;
    public int modifiedBy;
    public long createdDate;
    public long interfaceRunEndDate;
    public String applicationProcess;
    public double discrepancyAmount;
    public String batchId;
    public String fileName;
    public String sentRecordAmountAttribute;
    public String status;
    public double receivedRecordAmount;
    public double validRecordAmount;
    public int invalidRecordCount;
    public int receivedRecordCount;
    public int createdBy;
    public int changeCount;
    public String unprocessedRecords;
    public int deleteCount;
    public long modifiedDate;
    public int auditInboundSid;
    public double sentRecordAmount;
    public int sentRecordCount;
    public int addCount;
    public String source;
    public double invalidRecordAmount;
    public long interfaceRunStartDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(53);

        sb.append("{receivedRecordAmountAttr=");
        sb.append(receivedRecordAmountAttr);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", interfaceRunEndDate=");
        sb.append(interfaceRunEndDate);
        sb.append(", applicationProcess=");
        sb.append(applicationProcess);
        sb.append(", discrepancyAmount=");
        sb.append(discrepancyAmount);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", fileName=");
        sb.append(fileName);
        sb.append(", sentRecordAmountAttribute=");
        sb.append(sentRecordAmountAttribute);
        sb.append(", status=");
        sb.append(status);
        sb.append(", receivedRecordAmount=");
        sb.append(receivedRecordAmount);
        sb.append(", validRecordAmount=");
        sb.append(validRecordAmount);
        sb.append(", invalidRecordCount=");
        sb.append(invalidRecordCount);
        sb.append(", receivedRecordCount=");
        sb.append(receivedRecordCount);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", changeCount=");
        sb.append(changeCount);
        sb.append(", unprocessedRecords=");
        sb.append(unprocessedRecords);
        sb.append(", deleteCount=");
        sb.append(deleteCount);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", auditInboundSid=");
        sb.append(auditInboundSid);
        sb.append(", sentRecordAmount=");
        sb.append(sentRecordAmount);
        sb.append(", sentRecordCount=");
        sb.append(sentRecordCount);
        sb.append(", addCount=");
        sb.append(addCount);
        sb.append(", source=");
        sb.append(source);
        sb.append(", invalidRecordAmount=");
        sb.append(invalidRecordAmount);
        sb.append(", interfaceRunStartDate=");
        sb.append(interfaceRunStartDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public AuditMasterInbound toEntityModel() {
        AuditMasterInboundImpl auditMasterInboundImpl = new AuditMasterInboundImpl();

        if (receivedRecordAmountAttr == null) {
            auditMasterInboundImpl.setReceivedRecordAmountAttr(StringPool.BLANK);
        } else {
            auditMasterInboundImpl.setReceivedRecordAmountAttr(receivedRecordAmountAttr);
        }

        auditMasterInboundImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            auditMasterInboundImpl.setCreatedDate(null);
        } else {
            auditMasterInboundImpl.setCreatedDate(new Date(createdDate));
        }

        if (interfaceRunEndDate == Long.MIN_VALUE) {
            auditMasterInboundImpl.setInterfaceRunEndDate(null);
        } else {
            auditMasterInboundImpl.setInterfaceRunEndDate(new Date(
                    interfaceRunEndDate));
        }

        if (applicationProcess == null) {
            auditMasterInboundImpl.setApplicationProcess(StringPool.BLANK);
        } else {
            auditMasterInboundImpl.setApplicationProcess(applicationProcess);
        }

        auditMasterInboundImpl.setDiscrepancyAmount(discrepancyAmount);

        if (batchId == null) {
            auditMasterInboundImpl.setBatchId(StringPool.BLANK);
        } else {
            auditMasterInboundImpl.setBatchId(batchId);
        }

        if (fileName == null) {
            auditMasterInboundImpl.setFileName(StringPool.BLANK);
        } else {
            auditMasterInboundImpl.setFileName(fileName);
        }

        if (sentRecordAmountAttribute == null) {
            auditMasterInboundImpl.setSentRecordAmountAttribute(StringPool.BLANK);
        } else {
            auditMasterInboundImpl.setSentRecordAmountAttribute(sentRecordAmountAttribute);
        }

        if (status == null) {
            auditMasterInboundImpl.setStatus(StringPool.BLANK);
        } else {
            auditMasterInboundImpl.setStatus(status);
        }

        auditMasterInboundImpl.setReceivedRecordAmount(receivedRecordAmount);
        auditMasterInboundImpl.setValidRecordAmount(validRecordAmount);
        auditMasterInboundImpl.setInvalidRecordCount(invalidRecordCount);
        auditMasterInboundImpl.setReceivedRecordCount(receivedRecordCount);
        auditMasterInboundImpl.setCreatedBy(createdBy);
        auditMasterInboundImpl.setChangeCount(changeCount);

        if (unprocessedRecords == null) {
            auditMasterInboundImpl.setUnprocessedRecords(StringPool.BLANK);
        } else {
            auditMasterInboundImpl.setUnprocessedRecords(unprocessedRecords);
        }

        auditMasterInboundImpl.setDeleteCount(deleteCount);

        if (modifiedDate == Long.MIN_VALUE) {
            auditMasterInboundImpl.setModifiedDate(null);
        } else {
            auditMasterInboundImpl.setModifiedDate(new Date(modifiedDate));
        }

        auditMasterInboundImpl.setAuditInboundSid(auditInboundSid);
        auditMasterInboundImpl.setSentRecordAmount(sentRecordAmount);
        auditMasterInboundImpl.setSentRecordCount(sentRecordCount);
        auditMasterInboundImpl.setAddCount(addCount);

        if (source == null) {
            auditMasterInboundImpl.setSource(StringPool.BLANK);
        } else {
            auditMasterInboundImpl.setSource(source);
        }

        auditMasterInboundImpl.setInvalidRecordAmount(invalidRecordAmount);

        if (interfaceRunStartDate == Long.MIN_VALUE) {
            auditMasterInboundImpl.setInterfaceRunStartDate(null);
        } else {
            auditMasterInboundImpl.setInterfaceRunStartDate(new Date(
                    interfaceRunStartDate));
        }

        auditMasterInboundImpl.resetOriginalValues();

        return auditMasterInboundImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        receivedRecordAmountAttr = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        interfaceRunEndDate = objectInput.readLong();
        applicationProcess = objectInput.readUTF();
        discrepancyAmount = objectInput.readDouble();
        batchId = objectInput.readUTF();
        fileName = objectInput.readUTF();
        sentRecordAmountAttribute = objectInput.readUTF();
        status = objectInput.readUTF();
        receivedRecordAmount = objectInput.readDouble();
        validRecordAmount = objectInput.readDouble();
        invalidRecordCount = objectInput.readInt();
        receivedRecordCount = objectInput.readInt();
        createdBy = objectInput.readInt();
        changeCount = objectInput.readInt();
        unprocessedRecords = objectInput.readUTF();
        deleteCount = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        auditInboundSid = objectInput.readInt();
        sentRecordAmount = objectInput.readDouble();
        sentRecordCount = objectInput.readInt();
        addCount = objectInput.readInt();
        source = objectInput.readUTF();
        invalidRecordAmount = objectInput.readDouble();
        interfaceRunStartDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (receivedRecordAmountAttr == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(receivedRecordAmountAttr);
        }

        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);
        objectOutput.writeLong(interfaceRunEndDate);

        if (applicationProcess == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(applicationProcess);
        }

        objectOutput.writeDouble(discrepancyAmount);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (fileName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fileName);
        }

        if (sentRecordAmountAttribute == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sentRecordAmountAttribute);
        }

        if (status == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(status);
        }

        objectOutput.writeDouble(receivedRecordAmount);
        objectOutput.writeDouble(validRecordAmount);
        objectOutput.writeInt(invalidRecordCount);
        objectOutput.writeInt(receivedRecordCount);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(changeCount);

        if (unprocessedRecords == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(unprocessedRecords);
        }

        objectOutput.writeInt(deleteCount);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(auditInboundSid);
        objectOutput.writeDouble(sentRecordAmount);
        objectOutput.writeInt(sentRecordCount);
        objectOutput.writeInt(addCount);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeDouble(invalidRecordAmount);
        objectOutput.writeLong(interfaceRunStartDate);
    }
}
