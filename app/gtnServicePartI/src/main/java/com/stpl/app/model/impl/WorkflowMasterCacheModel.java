package com.stpl.app.model.impl;

import com.stpl.app.model.WorkflowMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkflowMaster in entity cache.
 *
 * @author
 * @see WorkflowMaster
 * @generated
 */
public class WorkflowMasterCacheModel implements CacheModel<WorkflowMaster>,
    Externalizable {
    public int createdBy;
    public String fileSize;
    public int workflowStatusId;
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
    public int contractMasterSid;
    public String contractStructure;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(45);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", fileSize=");
        sb.append(fileSize);
        sb.append(", workflowStatusId=");
        sb.append(workflowStatusId);
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
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", contractStructure=");
        sb.append(contractStructure);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public WorkflowMaster toEntityModel() {
        WorkflowMasterImpl workflowMasterImpl = new WorkflowMasterImpl();

        workflowMasterImpl.setCreatedBy(createdBy);

        if (fileSize == null) {
            workflowMasterImpl.setFileSize(StringPool.BLANK);
        } else {
            workflowMasterImpl.setFileSize(fileSize);
        }

        workflowMasterImpl.setWorkflowStatusId(workflowStatusId);
        workflowMasterImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            workflowMasterImpl.setCreatedDate(null);
        } else {
            workflowMasterImpl.setCreatedDate(new Date(createdDate));
        }

        workflowMasterImpl.setApprovalLevel(approvalLevel);
        workflowMasterImpl.setNoOfApproval(noOfApproval);

        if (fileName == null) {
            workflowMasterImpl.setFileName(StringPool.BLANK);
        } else {
            workflowMasterImpl.setFileName(fileName);
        }

        if (uploadedBy == null) {
            workflowMasterImpl.setUploadedBy(StringPool.BLANK);
        } else {
            workflowMasterImpl.setUploadedBy(uploadedBy);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            workflowMasterImpl.setModifiedDate(null);
        } else {
            workflowMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        workflowMasterImpl.setAccClosureMasterSid(accClosureMasterSid);

        if (notes == null) {
            workflowMasterImpl.setNotes(StringPool.BLANK);
        } else {
            workflowMasterImpl.setNotes(notes);
        }

        workflowMasterImpl.setWorkflowMasterSid(workflowMasterSid);

        if (workflowId == null) {
            workflowMasterImpl.setWorkflowId(StringPool.BLANK);
        } else {
            workflowMasterImpl.setWorkflowId(workflowId);
        }

        workflowMasterImpl.setProjectionMasterSid(projectionMasterSid);

        if (uploadedDate == Long.MIN_VALUE) {
            workflowMasterImpl.setUploadedDate(null);
        } else {
            workflowMasterImpl.setUploadedDate(new Date(uploadedDate));
        }

        if (fileType == null) {
            workflowMasterImpl.setFileType(StringPool.BLANK);
        } else {
            workflowMasterImpl.setFileType(fileType);
        }

        workflowMasterImpl.setApprovedBy(approvedBy);

        if (workflowDescrption == null) {
            workflowMasterImpl.setWorkflowDescrption(StringPool.BLANK);
        } else {
            workflowMasterImpl.setWorkflowDescrption(workflowDescrption);
        }

        if (approvedDate == Long.MIN_VALUE) {
            workflowMasterImpl.setApprovedDate(null);
        } else {
            workflowMasterImpl.setApprovedDate(new Date(approvedDate));
        }

        workflowMasterImpl.setContractMasterSid(contractMasterSid);

        if (contractStructure == null) {
            workflowMasterImpl.setContractStructure(StringPool.BLANK);
        } else {
            workflowMasterImpl.setContractStructure(contractStructure);
        }

        workflowMasterImpl.resetOriginalValues();

        return workflowMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        fileSize = objectInput.readUTF();
        workflowStatusId = objectInput.readInt();
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
        contractMasterSid = objectInput.readInt();
        contractStructure = objectInput.readUTF();
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

        objectOutput.writeInt(workflowStatusId);
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
        objectOutput.writeInt(contractMasterSid);

        if (contractStructure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractStructure);
        }
    }
}
