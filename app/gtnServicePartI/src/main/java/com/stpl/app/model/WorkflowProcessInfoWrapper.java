package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WorkflowProcessInfo}.
 * </p>
 *
 * @author
 * @see WorkflowProcessInfo
 * @generated
 */
public class WorkflowProcessInfoWrapper implements WorkflowProcessInfo,
    ModelWrapper<WorkflowProcessInfo> {
    private WorkflowProcessInfo _workflowProcessInfo;

    public WorkflowProcessInfoWrapper(WorkflowProcessInfo workflowProcessInfo) {
        _workflowProcessInfo = workflowProcessInfo;
    }

    @Override
    public Class<?> getModelClass() {
        return WorkflowProcessInfo.class;
    }

    @Override
    public String getModelClassName() {
        return WorkflowProcessInfo.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("processInstanceId", getProcessInstanceId());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("workflowProcessInfoSid", getWorkflowProcessInfoSid());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("contractStructure", getContractStructure());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer processInstanceId = (Integer) attributes.get(
                "processInstanceId");

        if (processInstanceId != null) {
            setProcessInstanceId(processInstanceId);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Integer workflowProcessInfoSid = (Integer) attributes.get(
                "workflowProcessInfoSid");

        if (workflowProcessInfoSid != null) {
            setWorkflowProcessInfoSid(workflowProcessInfoSid);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
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
    * Returns the primary key of this workflow process info.
    *
    * @return the primary key of this workflow process info
    */
    @Override
    public int getPrimaryKey() {
        return _workflowProcessInfo.getPrimaryKey();
    }

    /**
    * Sets the primary key of this workflow process info.
    *
    * @param primaryKey the primary key of this workflow process info
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _workflowProcessInfo.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the process instance ID of this workflow process info.
    *
    * @return the process instance ID of this workflow process info
    */
    @Override
    public int getProcessInstanceId() {
        return _workflowProcessInfo.getProcessInstanceId();
    }

    /**
    * Sets the process instance ID of this workflow process info.
    *
    * @param processInstanceId the process instance ID of this workflow process info
    */
    @Override
    public void setProcessInstanceId(int processInstanceId) {
        _workflowProcessInfo.setProcessInstanceId(processInstanceId);
    }

    /**
    * Returns the projection master sid of this workflow process info.
    *
    * @return the projection master sid of this workflow process info
    */
    @Override
    public int getProjectionMasterSid() {
        return _workflowProcessInfo.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this workflow process info.
    *
    * @param projectionMasterSid the projection master sid of this workflow process info
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _workflowProcessInfo.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the workflow process info sid of this workflow process info.
    *
    * @return the workflow process info sid of this workflow process info
    */
    @Override
    public int getWorkflowProcessInfoSid() {
        return _workflowProcessInfo.getWorkflowProcessInfoSid();
    }

    /**
    * Sets the workflow process info sid of this workflow process info.
    *
    * @param workflowProcessInfoSid the workflow process info sid of this workflow process info
    */
    @Override
    public void setWorkflowProcessInfoSid(int workflowProcessInfoSid) {
        _workflowProcessInfo.setWorkflowProcessInfoSid(workflowProcessInfoSid);
    }

    /**
    * Returns the acc closure master sid of this workflow process info.
    *
    * @return the acc closure master sid of this workflow process info
    */
    @Override
    public int getAccClosureMasterSid() {
        return _workflowProcessInfo.getAccClosureMasterSid();
    }

    /**
    * Sets the acc closure master sid of this workflow process info.
    *
    * @param accClosureMasterSid the acc closure master sid of this workflow process info
    */
    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _workflowProcessInfo.setAccClosureMasterSid(accClosureMasterSid);
    }

    /**
    * Returns the contract master sid of this workflow process info.
    *
    * @return the contract master sid of this workflow process info
    */
    @Override
    public int getContractMasterSid() {
        return _workflowProcessInfo.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this workflow process info.
    *
    * @param contractMasterSid the contract master sid of this workflow process info
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _workflowProcessInfo.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the contract structure of this workflow process info.
    *
    * @return the contract structure of this workflow process info
    */
    @Override
    public java.lang.String getContractStructure() {
        return _workflowProcessInfo.getContractStructure();
    }

    /**
    * Sets the contract structure of this workflow process info.
    *
    * @param contractStructure the contract structure of this workflow process info
    */
    @Override
    public void setContractStructure(java.lang.String contractStructure) {
        _workflowProcessInfo.setContractStructure(contractStructure);
    }

    @Override
    public boolean isNew() {
        return _workflowProcessInfo.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _workflowProcessInfo.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _workflowProcessInfo.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _workflowProcessInfo.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _workflowProcessInfo.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _workflowProcessInfo.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _workflowProcessInfo.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _workflowProcessInfo.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _workflowProcessInfo.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _workflowProcessInfo.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _workflowProcessInfo.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new WorkflowProcessInfoWrapper((WorkflowProcessInfo) _workflowProcessInfo.clone());
    }

    @Override
    public int compareTo(WorkflowProcessInfo workflowProcessInfo) {
        return _workflowProcessInfo.compareTo(workflowProcessInfo);
    }

    @Override
    public int hashCode() {
        return _workflowProcessInfo.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<WorkflowProcessInfo> toCacheModel() {
        return _workflowProcessInfo.toCacheModel();
    }

    @Override
    public WorkflowProcessInfo toEscapedModel() {
        return new WorkflowProcessInfoWrapper(_workflowProcessInfo.toEscapedModel());
    }

    @Override
    public WorkflowProcessInfo toUnescapedModel() {
        return new WorkflowProcessInfoWrapper(_workflowProcessInfo.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _workflowProcessInfo.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _workflowProcessInfo.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _workflowProcessInfo.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof WorkflowProcessInfoWrapper)) {
            return false;
        }

        WorkflowProcessInfoWrapper workflowProcessInfoWrapper = (WorkflowProcessInfoWrapper) obj;

        if (Validator.equals(_workflowProcessInfo,
                    workflowProcessInfoWrapper._workflowProcessInfo)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public WorkflowProcessInfo getWrappedWorkflowProcessInfo() {
        return _workflowProcessInfo;
    }

    @Override
    public WorkflowProcessInfo getWrappedModel() {
        return _workflowProcessInfo;
    }

    @Override
    public void resetOriginalValues() {
        _workflowProcessInfo.resetOriginalValues();
    }
}
