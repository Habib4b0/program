package com.stpl.app.model.impl;

import com.stpl.app.model.HistWorkflowMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistWorkflowMaster in entity cache.
 *
 * @author
 * @see HistWorkflowMaster
 * @generated
 */
public class HistWorkflowMasterCacheModel implements CacheModel<HistWorkflowMaster>,
    Externalizable {
    public int createdBy;
    public String fileSize;
    public long actionDate;
    public int workflowStatusId;
    public String actionFlag;
    public int modifiedBy;
    public long createdDate;
    public int approvalLevel;
    public int noOfApproval;
    public String fileName;
    public String uploadedBy;
    public long modifiedDate;
    public int accClosureMasterSid;
    public String notes;
    public int workflowMasterSid;
    public String workflowId;
    public int projectionMasterSid;
    public long uploadedDate;
    public String fileType;
    public int approvedBy;
    public String workflowDescrption;
    public long approvedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(45);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", fileSize=");
        sb.append(fileSize);
        sb.append(", actionDate=");
        sb.append(actionDate);
        sb.append(", workflowStatusId=");
        sb.append(workflowStatusId);
        sb.append(", actionFlag=");
        sb.append(actionFlag);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", approvalLevel=");
        sb.append(approvalLevel);
        sb.append(", noOfApproval=");
        sb.append(noOfApproval);
        sb.append(", fileName=");
        sb.append(fileName);
        sb.append(", uploadedBy=");
        sb.append(uploadedBy);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", accClosureMasterSid=");
        sb.append(accClosureMasterSid);
        sb.append(", notes=");
        sb.append(notes);
        sb.append(", workflowMasterSid=");
        sb.append(workflowMasterSid);
        sb.append(", workflowId=");
        sb.append(workflowId);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append(", uploadedDate=");
        sb.append(uploadedDate);
        sb.append(", fileType=");
        sb.append(fileType);
        sb.append(", approvedBy=");
        sb.append(approvedBy);
        sb.append(", workflowDescrption=");
        sb.append(workflowDescrption);
        sb.append(", approvedDate=");
        sb.append(approvedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public HistWorkflowMaster toEntityModel() {
        HistWorkflowMasterImpl histWorkflowMasterImpl = new HistWorkflowMasterImpl();

        histWorkflowMasterImpl.setCreatedBy(createdBy);

        if (fileSize == null) {
            histWorkflowMasterImpl.setFileSize(StringPool.BLANK);
        } else {
            histWorkflowMasterImpl.setFileSize(fileSize);
        }

        if (actionDate == Long.MIN_VALUE) {
            histWorkflowMasterImpl.setActionDate(null);
        } else {
            histWorkflowMasterImpl.setActionDate(new Date(actionDate));
        }

        histWorkflowMasterImpl.setWorkflowStatusId(workflowStatusId);

        if (actionFlag == null) {
            histWorkflowMasterImpl.setActionFlag(StringPool.BLANK);
        } else {
            histWorkflowMasterImpl.setActionFlag(actionFlag);
        }

        histWorkflowMasterImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            histWorkflowMasterImpl.setCreatedDate(null);
        } else {
            histWorkflowMasterImpl.setCreatedDate(new Date(createdDate));
        }

        histWorkflowMasterImpl.setApprovalLevel(approvalLevel);
        histWorkflowMasterImpl.setNoOfApproval(noOfApproval);

        if (fileName == null) {
            histWorkflowMasterImpl.setFileName(StringPool.BLANK);
        } else {
            histWorkflowMasterImpl.setFileName(fileName);
        }

        if (uploadedBy == null) {
            histWorkflowMasterImpl.setUploadedBy(StringPool.BLANK);
        } else {
            histWorkflowMasterImpl.setUploadedBy(uploadedBy);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            histWorkflowMasterImpl.setModifiedDate(null);
        } else {
            histWorkflowMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        histWorkflowMasterImpl.setAccClosureMasterSid(accClosureMasterSid);

        if (notes == null) {
            histWorkflowMasterImpl.setNotes(StringPool.BLANK);
        } else {
            histWorkflowMasterImpl.setNotes(notes);
        }

        histWorkflowMasterImpl.setWorkflowMasterSid(workflowMasterSid);

        if (workflowId == null) {
            histWorkflowMasterImpl.setWorkflowId(StringPool.BLANK);
        } else {
            histWorkflowMasterImpl.setWorkflowId(workflowId);
        }

        histWorkflowMasterImpl.setProjectionMasterSid(projectionMasterSid);

        if (uploadedDate == Long.MIN_VALUE) {
            histWorkflowMasterImpl.setUploadedDate(null);
        } else {
            histWorkflowMasterImpl.setUploadedDate(new Date(uploadedDate));
        }

        if (fileType == null) {
            histWorkflowMasterImpl.setFileType(StringPool.BLANK);
        } else {
            histWorkflowMasterImpl.setFileType(fileType);
        }

        histWorkflowMasterImpl.setApprovedBy(approvedBy);

        if (workflowDescrption == null) {
            histWorkflowMasterImpl.setWorkflowDescrption(StringPool.BLANK);
        } else {
            histWorkflowMasterImpl.setWorkflowDescrption(workflowDescrption);
        }

        if (approvedDate == Long.MIN_VALUE) {
            histWorkflowMasterImpl.setApprovedDate(null);
        } else {
            histWorkflowMasterImpl.setApprovedDate(new Date(approvedDate));
        }

        histWorkflowMasterImpl.resetOriginalValues();

        return histWorkflowMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        fileSize = objectInput.readUTF();
        actionDate = objectInput.readLong();
        workflowStatusId = objectInput.readInt();
        actionFlag = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        approvalLevel = objectInput.readInt();
        noOfApproval = objectInput.readInt();
        fileName = objectInput.readUTF();
        uploadedBy = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        accClosureMasterSid = objectInput.readInt();
        notes = objectInput.readUTF();
        workflowMasterSid = objectInput.readInt();
        workflowId = objectInput.readUTF();
        projectionMasterSid = objectInput.readInt();
        uploadedDate = objectInput.readLong();
        fileType = objectInput.readUTF();
        approvedBy = objectInput.readInt();
        workflowDescrption = objectInput.readUTF();
        approvedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);

        if (fileSize == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fileSize);
        }

        objectOutput.writeLong(actionDate);
        objectOutput.writeInt(workflowStatusId);

        if (actionFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actionFlag);
        }

        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(approvalLevel);
        objectOutput.writeInt(noOfApproval);

        if (fileName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fileName);
        }

        if (uploadedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uploadedBy);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(accClosureMasterSid);

        if (notes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(notes);
        }

        objectOutput.writeInt(workflowMasterSid);

        if (workflowId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(workflowId);
        }

        objectOutput.writeInt(projectionMasterSid);
        objectOutput.writeLong(uploadedDate);

        if (fileType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(fileType);
        }

        objectOutput.writeInt(approvedBy);

        if (workflowDescrption == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(workflowDescrption);
        }

        objectOutput.writeLong(approvedDate);
    }
}
