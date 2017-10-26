package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class WorkflowMasterSoap implements Serializable {
    private int _createdBy;
    private String _fileSize;
    private int _workflowStatusId;
    private int _modifiedBy;
    private Date _createdDate;
    private int _approvalLevel;
    private int _noOfApproval;
    private String _fileName;
    private String _uploadedBy;
    private Date _modifiedDate;
    private int _accClosureMasterSid;
    private String _notes;
    private int _workflowMasterSid;
    private String _workflowId;
    private int _projectionMasterSid;
    private Date _uploadedDate;
    private String _fileType;
    private int _approvedBy;
    private String _workflowDescrption;
    private Date _approvedDate;
    private int _contractMasterSid;
    private String _contractStructure;

    public WorkflowMasterSoap() {
    }

    public static WorkflowMasterSoap toSoapModel(WorkflowMaster model) {
        WorkflowMasterSoap soapModel = new WorkflowMasterSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setFileSize(model.getFileSize());
        soapModel.setWorkflowStatusId(model.getWorkflowStatusId());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setApprovalLevel(model.getApprovalLevel());
        soapModel.setNoOfApproval(model.getNoOfApproval());
        soapModel.setFileName(model.getFileName());
        soapModel.setUploadedBy(model.getUploadedBy());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setAccClosureMasterSid(model.getAccClosureMasterSid());
        soapModel.setNotes(model.getNotes());
        soapModel.setWorkflowMasterSid(model.getWorkflowMasterSid());
        soapModel.setWorkflowId(model.getWorkflowId());
        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
        soapModel.setUploadedDate(model.getUploadedDate());
        soapModel.setFileType(model.getFileType());
        soapModel.setApprovedBy(model.getApprovedBy());
        soapModel.setWorkflowDescrption(model.getWorkflowDescrption());
        soapModel.setApprovedDate(model.getApprovedDate());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setContractStructure(model.getContractStructure());

        return soapModel;
    }

    public static WorkflowMasterSoap[] toSoapModels(WorkflowMaster[] models) {
        WorkflowMasterSoap[] soapModels = new WorkflowMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static WorkflowMasterSoap[][] toSoapModels(WorkflowMaster[][] models) {
        WorkflowMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new WorkflowMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new WorkflowMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static WorkflowMasterSoap[] toSoapModels(List<WorkflowMaster> models) {
        List<WorkflowMasterSoap> soapModels = new ArrayList<WorkflowMasterSoap>(models.size());

        for (WorkflowMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new WorkflowMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _workflowMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setWorkflowMasterSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getFileSize() {
        return _fileSize;
    }

    public void setFileSize(String fileSize) {
        _fileSize = fileSize;
    }

    public int getWorkflowStatusId() {
        return _workflowStatusId;
    }

    public void setWorkflowStatusId(int workflowStatusId) {
        _workflowStatusId = workflowStatusId;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getApprovalLevel() {
        return _approvalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        _approvalLevel = approvalLevel;
    }

    public int getNoOfApproval() {
        return _noOfApproval;
    }

    public void setNoOfApproval(int noOfApproval) {
        _noOfApproval = noOfApproval;
    }

    public String getFileName() {
        return _fileName;
    }

    public void setFileName(String fileName) {
        _fileName = fileName;
    }

    public String getUploadedBy() {
        return _uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        _uploadedBy = uploadedBy;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;
    }

    public String getNotes() {
        return _notes;
    }

    public void setNotes(String notes) {
        _notes = notes;
    }

    public int getWorkflowMasterSid() {
        return _workflowMasterSid;
    }

    public void setWorkflowMasterSid(int workflowMasterSid) {
        _workflowMasterSid = workflowMasterSid;
    }

    public String getWorkflowId() {
        return _workflowId;
    }

    public void setWorkflowId(String workflowId) {
        _workflowId = workflowId;
    }

    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;
    }

    public Date getUploadedDate() {
        return _uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        _uploadedDate = uploadedDate;
    }

    public String getFileType() {
        return _fileType;
    }

    public void setFileType(String fileType) {
        _fileType = fileType;
    }

    public int getApprovedBy() {
        return _approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        _approvedBy = approvedBy;
    }

    public String getWorkflowDescrption() {
        return _workflowDescrption;
    }

    public void setWorkflowDescrption(String workflowDescrption) {
        _workflowDescrption = workflowDescrption;
    }

    public Date getApprovedDate() {
        return _approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        _approvedDate = approvedDate;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public String getContractStructure() {
        return _contractStructure;
    }

    public void setContractStructure(String contractStructure) {
        _contractStructure = contractStructure;
    }
}
