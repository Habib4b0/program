package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WorkflowMaster}.
 * </p>
 *
 * @author
 * @see WorkflowMaster
 * @generated
 */
public class WorkflowMasterWrapper implements WorkflowMaster,
    ModelWrapper<WorkflowMaster> {
    private WorkflowMaster _workflowMaster;

    public WorkflowMasterWrapper(WorkflowMaster workflowMaster) {
        _workflowMaster = workflowMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return WorkflowMaster.class;
    }

    @Override
    public String getModelClassName() {
        return WorkflowMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("fileSize", getFileSize());
        attributes.put("workflowStatusId", getWorkflowStatusId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("approvalLevel", getApprovalLevel());
        attributes.put("noOfApproval", getNoOfApproval());
        attributes.put("fileName", getFileName());
        attributes.put("uploadedBy", getUploadedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("notes", getNotes());
        attributes.put("workflowMasterSid", getWorkflowMasterSid());
        attributes.put("workflowId", getWorkflowId());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("uploadedDate", getUploadedDate());
        attributes.put("fileType", getFileType());
        attributes.put("approvedBy", getApprovedBy());
        attributes.put("workflowDescrption", getWorkflowDescrption());
        attributes.put("approvedDate", getApprovedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("contractStructure", getContractStructure());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String fileSize = (String) attributes.get("fileSize");

        if (fileSize != null) {
            setFileSize(fileSize);
        }

        Integer workflowStatusId = (Integer) attributes.get("workflowStatusId");

        if (workflowStatusId != null) {
            setWorkflowStatusId(workflowStatusId);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer approvalLevel = (Integer) attributes.get("approvalLevel");

        if (approvalLevel != null) {
            setApprovalLevel(approvalLevel);
        }

        Integer noOfApproval = (Integer) attributes.get("noOfApproval");

        if (noOfApproval != null) {
            setNoOfApproval(noOfApproval);
        }

        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        String uploadedBy = (String) attributes.get("uploadedBy");

        if (uploadedBy != null) {
            setUploadedBy(uploadedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        Integer workflowMasterSid = (Integer) attributes.get(
                "workflowMasterSid");

        if (workflowMasterSid != null) {
            setWorkflowMasterSid(workflowMasterSid);
        }

        String workflowId = (String) attributes.get("workflowId");

        if (workflowId != null) {
            setWorkflowId(workflowId);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Date uploadedDate = (Date) attributes.get("uploadedDate");

        if (uploadedDate != null) {
            setUploadedDate(uploadedDate);
        }

        String fileType = (String) attributes.get("fileType");

        if (fileType != null) {
            setFileType(fileType);
        }

        Integer approvedBy = (Integer) attributes.get("approvedBy");

        if (approvedBy != null) {
            setApprovedBy(approvedBy);
        }

        String workflowDescrption = (String) attributes.get(
                "workflowDescrption");

        if (workflowDescrption != null) {
            setWorkflowDescrption(workflowDescrption);
        }

        Date approvedDate = (Date) attributes.get("approvedDate");

        if (approvedDate != null) {
            setApprovedDate(approvedDate);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String contractStructure = (String) attributes.get("contractStructure");

        if (contractStructure != null) {
            setContractStructure(contractStructure);
        }
    }

    /**
    * Returns the primary key of this workflow master.
    *
    * @return the primary key of this workflow master
    */
    @Override
    public int getPrimaryKey() {
        return _workflowMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this workflow master.
    *
    * @param primaryKey the primary key of this workflow master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _workflowMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this workflow master.
    *
    * @return the created by of this workflow master
    */
    @Override
    public int getCreatedBy() {
        return _workflowMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this workflow master.
    *
    * @param createdBy the created by of this workflow master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _workflowMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the file size of this workflow master.
    *
    * @return the file size of this workflow master
    */
    @Override
    public java.lang.String getFileSize() {
        return _workflowMaster.getFileSize();
    }

    /**
    * Sets the file size of this workflow master.
    *
    * @param fileSize the file size of this workflow master
    */
    @Override
    public void setFileSize(java.lang.String fileSize) {
        _workflowMaster.setFileSize(fileSize);
    }

    /**
    * Returns the workflow status ID of this workflow master.
    *
    * @return the workflow status ID of this workflow master
    */
    @Override
    public int getWorkflowStatusId() {
        return _workflowMaster.getWorkflowStatusId();
    }

    /**
    * Sets the workflow status ID of this workflow master.
    *
    * @param workflowStatusId the workflow status ID of this workflow master
    */
    @Override
    public void setWorkflowStatusId(int workflowStatusId) {
        _workflowMaster.setWorkflowStatusId(workflowStatusId);
    }

    /**
    * Returns the modified by of this workflow master.
    *
    * @return the modified by of this workflow master
    */
    @Override
    public int getModifiedBy() {
        return _workflowMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this workflow master.
    *
    * @param modifiedBy the modified by of this workflow master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _workflowMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this workflow master.
    *
    * @return the created date of this workflow master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _workflowMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this workflow master.
    *
    * @param createdDate the created date of this workflow master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _workflowMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the approval level of this workflow master.
    *
    * @return the approval level of this workflow master
    */
    @Override
    public int getApprovalLevel() {
        return _workflowMaster.getApprovalLevel();
    }

    /**
    * Sets the approval level of this workflow master.
    *
    * @param approvalLevel the approval level of this workflow master
    */
    @Override
    public void setApprovalLevel(int approvalLevel) {
        _workflowMaster.setApprovalLevel(approvalLevel);
    }

    /**
    * Returns the no of approval of this workflow master.
    *
    * @return the no of approval of this workflow master
    */
    @Override
    public int getNoOfApproval() {
        return _workflowMaster.getNoOfApproval();
    }

    /**
    * Sets the no of approval of this workflow master.
    *
    * @param noOfApproval the no of approval of this workflow master
    */
    @Override
    public void setNoOfApproval(int noOfApproval) {
        _workflowMaster.setNoOfApproval(noOfApproval);
    }

    /**
    * Returns the file name of this workflow master.
    *
    * @return the file name of this workflow master
    */
    @Override
    public java.lang.String getFileName() {
        return _workflowMaster.getFileName();
    }

    /**
    * Sets the file name of this workflow master.
    *
    * @param fileName the file name of this workflow master
    */
    @Override
    public void setFileName(java.lang.String fileName) {
        _workflowMaster.setFileName(fileName);
    }

    /**
    * Returns the uploaded by of this workflow master.
    *
    * @return the uploaded by of this workflow master
    */
    @Override
    public java.lang.String getUploadedBy() {
        return _workflowMaster.getUploadedBy();
    }

    /**
    * Sets the uploaded by of this workflow master.
    *
    * @param uploadedBy the uploaded by of this workflow master
    */
    @Override
    public void setUploadedBy(java.lang.String uploadedBy) {
        _workflowMaster.setUploadedBy(uploadedBy);
    }

    /**
    * Returns the modified date of this workflow master.
    *
    * @return the modified date of this workflow master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _workflowMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this workflow master.
    *
    * @param modifiedDate the modified date of this workflow master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _workflowMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the acc closure master sid of this workflow master.
    *
    * @return the acc closure master sid of this workflow master
    */
    @Override
    public int getAccClosureMasterSid() {
        return _workflowMaster.getAccClosureMasterSid();
    }

    /**
    * Sets the acc closure master sid of this workflow master.
    *
    * @param accClosureMasterSid the acc closure master sid of this workflow master
    */
    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _workflowMaster.setAccClosureMasterSid(accClosureMasterSid);
    }

    /**
    * Returns the notes of this workflow master.
    *
    * @return the notes of this workflow master
    */
    @Override
    public java.lang.String getNotes() {
        return _workflowMaster.getNotes();
    }

    /**
    * Sets the notes of this workflow master.
    *
    * @param notes the notes of this workflow master
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _workflowMaster.setNotes(notes);
    }

    /**
    * Returns the workflow master sid of this workflow master.
    *
    * @return the workflow master sid of this workflow master
    */
    @Override
    public int getWorkflowMasterSid() {
        return _workflowMaster.getWorkflowMasterSid();
    }

    /**
    * Sets the workflow master sid of this workflow master.
    *
    * @param workflowMasterSid the workflow master sid of this workflow master
    */
    @Override
    public void setWorkflowMasterSid(int workflowMasterSid) {
        _workflowMaster.setWorkflowMasterSid(workflowMasterSid);
    }

    /**
    * Returns the workflow ID of this workflow master.
    *
    * @return the workflow ID of this workflow master
    */
    @Override
    public java.lang.String getWorkflowId() {
        return _workflowMaster.getWorkflowId();
    }

    /**
    * Sets the workflow ID of this workflow master.
    *
    * @param workflowId the workflow ID of this workflow master
    */
    @Override
    public void setWorkflowId(java.lang.String workflowId) {
        _workflowMaster.setWorkflowId(workflowId);
    }

    /**
    * Returns the projection master sid of this workflow master.
    *
    * @return the projection master sid of this workflow master
    */
    @Override
    public int getProjectionMasterSid() {
        return _workflowMaster.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this workflow master.
    *
    * @param projectionMasterSid the projection master sid of this workflow master
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _workflowMaster.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the uploaded date of this workflow master.
    *
    * @return the uploaded date of this workflow master
    */
    @Override
    public java.util.Date getUploadedDate() {
        return _workflowMaster.getUploadedDate();
    }

    /**
    * Sets the uploaded date of this workflow master.
    *
    * @param uploadedDate the uploaded date of this workflow master
    */
    @Override
    public void setUploadedDate(java.util.Date uploadedDate) {
        _workflowMaster.setUploadedDate(uploadedDate);
    }

    /**
    * Returns the file type of this workflow master.
    *
    * @return the file type of this workflow master
    */
    @Override
    public java.lang.String getFileType() {
        return _workflowMaster.getFileType();
    }

    /**
    * Sets the file type of this workflow master.
    *
    * @param fileType the file type of this workflow master
    */
    @Override
    public void setFileType(java.lang.String fileType) {
        _workflowMaster.setFileType(fileType);
    }

    /**
    * Returns the approved by of this workflow master.
    *
    * @return the approved by of this workflow master
    */
    @Override
    public int getApprovedBy() {
        return _workflowMaster.getApprovedBy();
    }

    /**
    * Sets the approved by of this workflow master.
    *
    * @param approvedBy the approved by of this workflow master
    */
    @Override
    public void setApprovedBy(int approvedBy) {
        _workflowMaster.setApprovedBy(approvedBy);
    }

    /**
    * Returns the workflow descrption of this workflow master.
    *
    * @return the workflow descrption of this workflow master
    */
    @Override
    public java.lang.String getWorkflowDescrption() {
        return _workflowMaster.getWorkflowDescrption();
    }

    /**
    * Sets the workflow descrption of this workflow master.
    *
    * @param workflowDescrption the workflow descrption of this workflow master
    */
    @Override
    public void setWorkflowDescrption(java.lang.String workflowDescrption) {
        _workflowMaster.setWorkflowDescrption(workflowDescrption);
    }

    /**
    * Returns the approved date of this workflow master.
    *
    * @return the approved date of this workflow master
    */
    @Override
    public java.util.Date getApprovedDate() {
        return _workflowMaster.getApprovedDate();
    }

    /**
    * Sets the approved date of this workflow master.
    *
    * @param approvedDate the approved date of this workflow master
    */
    @Override
    public void setApprovedDate(java.util.Date approvedDate) {
        _workflowMaster.setApprovedDate(approvedDate);
    }

    /**
    * Returns the contract master sid of this workflow master.
    *
    * @return the contract master sid of this workflow master
    */
    @Override
    public int getContractMasterSid() {
        return _workflowMaster.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this workflow master.
    *
    * @param contractMasterSid the contract master sid of this workflow master
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _workflowMaster.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the contract structure of this workflow master.
    *
    * @return the contract structure of this workflow master
    */
    @Override
    public java.lang.String getContractStructure() {
        return _workflowMaster.getContractStructure();
    }

    /**
    * Sets the contract structure of this workflow master.
    *
    * @param contractStructure the contract structure of this workflow master
    */
    @Override
    public void setContractStructure(java.lang.String contractStructure) {
        _workflowMaster.setContractStructure(contractStructure);
    }

    @Override
    public boolean isNew() {
        return _workflowMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _workflowMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _workflowMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _workflowMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _workflowMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _workflowMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _workflowMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _workflowMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _workflowMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _workflowMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _workflowMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new WorkflowMasterWrapper((WorkflowMaster) _workflowMaster.clone());
    }

    @Override
    public int compareTo(WorkflowMaster workflowMaster) {
        return _workflowMaster.compareTo(workflowMaster);
    }

    @Override
    public int hashCode() {
        return _workflowMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<WorkflowMaster> toCacheModel() {
        return _workflowMaster.toCacheModel();
    }

    @Override
    public WorkflowMaster toEscapedModel() {
        return new WorkflowMasterWrapper(_workflowMaster.toEscapedModel());
    }

    @Override
    public WorkflowMaster toUnescapedModel() {
        return new WorkflowMasterWrapper(_workflowMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _workflowMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _workflowMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _workflowMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof WorkflowMasterWrapper)) {
            return false;
        }

        WorkflowMasterWrapper workflowMasterWrapper = (WorkflowMasterWrapper) obj;

        if (Validator.equals(_workflowMaster,
                    workflowMasterWrapper._workflowMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public WorkflowMaster getWrappedWorkflowMaster() {
        return _workflowMaster;
    }

    @Override
    public WorkflowMaster getWrappedModel() {
        return _workflowMaster;
    }

    @Override
    public void resetOriginalValues() {
        _workflowMaster.resetOriginalValues();
    }
}
