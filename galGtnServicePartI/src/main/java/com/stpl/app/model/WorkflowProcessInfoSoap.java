package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class WorkflowProcessInfoSoap implements Serializable {
    private int _processInstanceId;
    private int _projectionMasterSid;
    private int _workflowProcessInfoSid;
    private int _accClosureMasterSid;
    private int _contractMasterSid;
    private String _contractStructure;

    public WorkflowProcessInfoSoap() {
    }

    public static WorkflowProcessInfoSoap toSoapModel(WorkflowProcessInfo model) {
        WorkflowProcessInfoSoap soapModel = new WorkflowProcessInfoSoap();

        soapModel.setProcessInstanceId(model.getProcessInstanceId());
        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
        soapModel.setWorkflowProcessInfoSid(model.getWorkflowProcessInfoSid());
        soapModel.setAccClosureMasterSid(model.getAccClosureMasterSid());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setContractStructure(model.getContractStructure());

        return soapModel;
    }

    public static WorkflowProcessInfoSoap[] toSoapModels(
        WorkflowProcessInfo[] models) {
        WorkflowProcessInfoSoap[] soapModels = new WorkflowProcessInfoSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static WorkflowProcessInfoSoap[][] toSoapModels(
        WorkflowProcessInfo[][] models) {
        WorkflowProcessInfoSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new WorkflowProcessInfoSoap[models.length][models[0].length];
        } else {
            soapModels = new WorkflowProcessInfoSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static WorkflowProcessInfoSoap[] toSoapModels(
        List<WorkflowProcessInfo> models) {
        List<WorkflowProcessInfoSoap> soapModels = new ArrayList<WorkflowProcessInfoSoap>(models.size());

        for (WorkflowProcessInfo model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new WorkflowProcessInfoSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _workflowProcessInfoSid;
    }

    public void setPrimaryKey(int pk) {
        setWorkflowProcessInfoSid(pk);
    }

    public int getProcessInstanceId() {
        return _processInstanceId;
    }

    public void setProcessInstanceId(int processInstanceId) {
        _processInstanceId = processInstanceId;
    }

    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;
    }

    public int getWorkflowProcessInfoSid() {
        return _workflowProcessInfoSid;
    }

    public void setWorkflowProcessInfoSid(int workflowProcessInfoSid) {
        _workflowProcessInfoSid = workflowProcessInfoSid;
    }

    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;
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
