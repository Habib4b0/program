package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HistWorkflowMaster}.
 * </p>
 *
 * @author
 * @see HistWorkflowMaster
 * @generated
 */
public class HistWorkflowMasterWrapper implements HistWorkflowMaster,
    ModelWrapper<HistWorkflowMaster> {
    private HistWorkflowMaster _histWorkflowMaster;

    public HistWorkflowMasterWrapper(HistWorkflowMaster histWorkflowMaster) {
        _histWorkflowMaster = histWorkflowMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return HistWorkflowMaster.class;
    }

    @Override
    public String getModelClassName() {
        return HistWorkflowMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("fileSize", getFileSize());
        attributes.put("actionDate", getActionDate());
        attributes.put("workflowStatusId", getWorkflowStatusId());
        attributes.put("actionFlag", getActionFlag());
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

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
        }

        Integer workflowStatusId = (Integer) attributes.get("workflowStatusId");

        if (workflowStatusId != null) {
            setWorkflowStatusId(workflowStatusId);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
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
    }

    /**
    * Returns the primary key of this hist workflow master.
    *
    * @return the primary key of this hist workflow master
    */
    @Override
    public int getPrimaryKey() {
        return _histWorkflowMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hist workflow master.
    *
    * @param primaryKey the primary key of this hist workflow master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _histWorkflowMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this hist workflow master.
    *
    * @return the created by of this hist workflow master
    */
    @Override
    public int getCreatedBy() {
        return _histWorkflowMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this hist workflow master.
    *
    * @param createdBy the created by of this hist workflow master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _histWorkflowMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the file size of this hist workflow master.
    *
    * @return the file size of this hist workflow master
    */
    @Override
    public java.lang.String getFileSize() {
        return _histWorkflowMaster.getFileSize();
    }

    /**
    * Sets the file size of this hist workflow master.
    *
    * @param fileSize the file size of this hist workflow master
    */
    @Override
    public void setFileSize(java.lang.String fileSize) {
        _histWorkflowMaster.setFileSize(fileSize);
    }

    /**
    * Returns the action date of this hist workflow master.
    *
    * @return the action date of this hist workflow master
    */
    @Override
    public java.util.Date getActionDate() {
        return _histWorkflowMaster.getActionDate();
    }

    /**
    * Sets the action date of this hist workflow master.
    *
    * @param actionDate the action date of this hist workflow master
    */
    @Override
    public void setActionDate(java.util.Date actionDate) {
        _histWorkflowMaster.setActionDate(actionDate);
    }

    /**
    * Returns the workflow status ID of this hist workflow master.
    *
    * @return the workflow status ID of this hist workflow master
    */
    @Override
    public int getWorkflowStatusId() {
        return _histWorkflowMaster.getWorkflowStatusId();
    }

    /**
    * Sets the workflow status ID of this hist workflow master.
    *
    * @param workflowStatusId the workflow status ID of this hist workflow master
    */
    @Override
    public void setWorkflowStatusId(int workflowStatusId) {
        _histWorkflowMaster.setWorkflowStatusId(workflowStatusId);
    }

    /**
    * Returns the action flag of this hist workflow master.
    *
    * @return the action flag of this hist workflow master
    */
    @Override
    public java.lang.String getActionFlag() {
        return _histWorkflowMaster.getActionFlag();
    }

    /**
    * Sets the action flag of this hist workflow master.
    *
    * @param actionFlag the action flag of this hist workflow master
    */
    @Override
    public void setActionFlag(java.lang.String actionFlag) {
        _histWorkflowMaster.setActionFlag(actionFlag);
    }

    /**
    * Returns the modified by of this hist workflow master.
    *
    * @return the modified by of this hist workflow master
    */
    @Override
    public int getModifiedBy() {
        return _histWorkflowMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this hist workflow master.
    *
    * @param modifiedBy the modified by of this hist workflow master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _histWorkflowMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this hist workflow master.
    *
    * @return the created date of this hist workflow master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _histWorkflowMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this hist workflow master.
    *
    * @param createdDate the created date of this hist workflow master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _histWorkflowMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the approval level of this hist workflow master.
    *
    * @return the approval level of this hist workflow master
    */
    @Override
    public int getApprovalLevel() {
        return _histWorkflowMaster.getApprovalLevel();
    }

    /**
    * Sets the approval level of this hist workflow master.
    *
    * @param approvalLevel the approval level of this hist workflow master
    */
    @Override
    public void setApprovalLevel(int approvalLevel) {
        _histWorkflowMaster.setApprovalLevel(approvalLevel);
    }

    /**
    * Returns the no of approval of this hist workflow master.
    *
    * @return the no of approval of this hist workflow master
    */
    @Override
    public int getNoOfApproval() {
        return _histWorkflowMaster.getNoOfApproval();
    }

    /**
    * Sets the no of approval of this hist workflow master.
    *
    * @param noOfApproval the no of approval of this hist workflow master
    */
    @Override
    public void setNoOfApproval(int noOfApproval) {
        _histWorkflowMaster.setNoOfApproval(noOfApproval);
    }

    /**
    * Returns the file name of this hist workflow master.
    *
    * @return the file name of this hist workflow master
    */
    @Override
    public java.lang.String getFileName() {
        return _histWorkflowMaster.getFileName();
    }

    /**
    * Sets the file name of this hist workflow master.
    *
    * @param fileName the file name of this hist workflow master
    */
    @Override
    public void setFileName(java.lang.String fileName) {
        _histWorkflowMaster.setFileName(fileName);
    }

    /**
    * Returns the uploaded by of this hist workflow master.
    *
    * @return the uploaded by of this hist workflow master
    */
    @Override
    public java.lang.String getUploadedBy() {
        return _histWorkflowMaster.getUploadedBy();
    }

    /**
    * Sets the uploaded by of this hist workflow master.
    *
    * @param uploadedBy the uploaded by of this hist workflow master
    */
    @Override
    public void setUploadedBy(java.lang.String uploadedBy) {
        _histWorkflowMaster.setUploadedBy(uploadedBy);
    }

    /**
    * Returns the modified date of this hist workflow master.
    *
    * @return the modified date of this hist workflow master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _histWorkflowMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this hist workflow master.
    *
    * @param modifiedDate the modified date of this hist workflow master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _histWorkflowMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the acc closure master sid of this hist workflow master.
    *
    * @return the acc closure master sid of this hist workflow master
    */
    @Override
    public int getAccClosureMasterSid() {
        return _histWorkflowMaster.getAccClosureMasterSid();
    }

    /**
    * Sets the acc closure master sid of this hist workflow master.
    *
    * @param accClosureMasterSid the acc closure master sid of this hist workflow master
    */
    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _histWorkflowMaster.setAccClosureMasterSid(accClosureMasterSid);
    }

    /**
    * Returns the notes of this hist workflow master.
    *
    * @return the notes of this hist workflow master
    */
    @Override
    public java.lang.String getNotes() {
        return _histWorkflowMaster.getNotes();
    }

    /**
    * Sets the notes of this hist workflow master.
    *
    * @param notes the notes of this hist workflow master
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _histWorkflowMaster.setNotes(notes);
    }

    /**
    * Returns the workflow master sid of this hist workflow master.
    *
    * @return the workflow master sid of this hist workflow master
    */
    @Override
    public int getWorkflowMasterSid() {
        return _histWorkflowMaster.getWorkflowMasterSid();
    }

    /**
    * Sets the workflow master sid of this hist workflow master.
    *
    * @param workflowMasterSid the workflow master sid of this hist workflow master
    */
    @Override
    public void setWorkflowMasterSid(int workflowMasterSid) {
        _histWorkflowMaster.setWorkflowMasterSid(workflowMasterSid);
    }

    /**
    * Returns the workflow ID of this hist workflow master.
    *
    * @return the workflow ID of this hist workflow master
    */
    @Override
    public java.lang.String getWorkflowId() {
        return _histWorkflowMaster.getWorkflowId();
    }

    /**
    * Sets the workflow ID of this hist workflow master.
    *
    * @param workflowId the workflow ID of this hist workflow master
    */
    @Override
    public void setWorkflowId(java.lang.String workflowId) {
        _histWorkflowMaster.setWorkflowId(workflowId);
    }

    /**
    * Returns the projection master sid of this hist workflow master.
    *
    * @return the projection master sid of this hist workflow master
    */
    @Override
    public int getProjectionMasterSid() {
        return _histWorkflowMaster.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this hist workflow master.
    *
    * @param projectionMasterSid the projection master sid of this hist workflow master
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _histWorkflowMaster.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the uploaded date of this hist workflow master.
    *
    * @return the uploaded date of this hist workflow master
    */
    @Override
    public java.util.Date getUploadedDate() {
        return _histWorkflowMaster.getUploadedDate();
    }

    /**
    * Sets the uploaded date of this hist workflow master.
    *
    * @param uploadedDate the uploaded date of this hist workflow master
    */
    @Override
    public void setUploadedDate(java.util.Date uploadedDate) {
        _histWorkflowMaster.setUploadedDate(uploadedDate);
    }

    /**
    * Returns the file type of this hist workflow master.
    *
    * @return the file type of this hist workflow master
    */
    @Override
    public java.lang.String getFileType() {
        return _histWorkflowMaster.getFileType();
    }

    /**
    * Sets the file type of this hist workflow master.
    *
    * @param fileType the file type of this hist workflow master
    */
    @Override
    public void setFileType(java.lang.String fileType) {
        _histWorkflowMaster.setFileType(fileType);
    }

    /**
    * Returns the approved by of this hist workflow master.
    *
    * @return the approved by of this hist workflow master
    */
    @Override
    public int getApprovedBy() {
        return _histWorkflowMaster.getApprovedBy();
    }

    /**
    * Sets the approved by of this hist workflow master.
    *
    * @param approvedBy the approved by of this hist workflow master
    */
    @Override
    public void setApprovedBy(int approvedBy) {
        _histWorkflowMaster.setApprovedBy(approvedBy);
    }

    /**
    * Returns the workflow descrption of this hist workflow master.
    *
    * @return the workflow descrption of this hist workflow master
    */
    @Override
    public java.lang.String getWorkflowDescrption() {
        return _histWorkflowMaster.getWorkflowDescrption();
    }

    /**
    * Sets the workflow descrption of this hist workflow master.
    *
    * @param workflowDescrption the workflow descrption of this hist workflow master
    */
    @Override
    public void setWorkflowDescrption(java.lang.String workflowDescrption) {
        _histWorkflowMaster.setWorkflowDescrption(workflowDescrption);
    }

    /**
    * Returns the approved date of this hist workflow master.
    *
    * @return the approved date of this hist workflow master
    */
    @Override
    public java.util.Date getApprovedDate() {
        return _histWorkflowMaster.getApprovedDate();
    }

    /**
    * Sets the approved date of this hist workflow master.
    *
    * @param approvedDate the approved date of this hist workflow master
    */
    @Override
    public void setApprovedDate(java.util.Date approvedDate) {
        _histWorkflowMaster.setApprovedDate(approvedDate);
    }

    @Override
    public boolean isNew() {
        return _histWorkflowMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _histWorkflowMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _histWorkflowMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _histWorkflowMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _histWorkflowMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _histWorkflowMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _histWorkflowMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _histWorkflowMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _histWorkflowMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _histWorkflowMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _histWorkflowMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HistWorkflowMasterWrapper((HistWorkflowMaster) _histWorkflowMaster.clone());
    }

    @Override
    public int compareTo(HistWorkflowMaster histWorkflowMaster) {
        return _histWorkflowMaster.compareTo(histWorkflowMaster);
    }

    @Override
    public int hashCode() {
        return _histWorkflowMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HistWorkflowMaster> toCacheModel() {
        return _histWorkflowMaster.toCacheModel();
    }

    @Override
    public HistWorkflowMaster toEscapedModel() {
        return new HistWorkflowMasterWrapper(_histWorkflowMaster.toEscapedModel());
    }

    @Override
    public HistWorkflowMaster toUnescapedModel() {
        return new HistWorkflowMasterWrapper(_histWorkflowMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _histWorkflowMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _histWorkflowMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _histWorkflowMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistWorkflowMasterWrapper)) {
            return false;
        }

        HistWorkflowMasterWrapper histWorkflowMasterWrapper = (HistWorkflowMasterWrapper) obj;

        if (Validator.equals(_histWorkflowMaster,
                    histWorkflowMasterWrapper._histWorkflowMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HistWorkflowMaster getWrappedHistWorkflowMaster() {
        return _histWorkflowMaster;
    }

    @Override
    public HistWorkflowMaster getWrappedModel() {
        return _histWorkflowMaster;
    }

    @Override
    public void resetOriginalValues() {
        _histWorkflowMaster.resetOriginalValues();
    }
}
