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
public class ContractAliasMasterSoap implements Serializable {
    private int _contractAliasType;
    private int _tpCompanyMasterSid;
    private Date _endDate;
    private int _contractAliasMasterSid;
    private Date _modifiedDate;
    private String _contractAliasNo;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private int _contractMasterSid;
    private String _batchId;
    private String _contractAliasName;
    private int _modifiedBy;
    private String _inboundStatus;

    public ContractAliasMasterSoap() {
    }

    public static ContractAliasMasterSoap toSoapModel(ContractAliasMaster model) {
        ContractAliasMasterSoap soapModel = new ContractAliasMasterSoap();

        soapModel.setContractAliasType(model.getContractAliasType());
        soapModel.setTpCompanyMasterSid(model.getTpCompanyMasterSid());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setContractAliasMasterSid(model.getContractAliasMasterSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setContractAliasNo(model.getContractAliasNo());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setContractAliasName(model.getContractAliasName());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static ContractAliasMasterSoap[] toSoapModels(
        ContractAliasMaster[] models) {
        ContractAliasMasterSoap[] soapModels = new ContractAliasMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContractAliasMasterSoap[][] toSoapModels(
        ContractAliasMaster[][] models) {
        ContractAliasMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContractAliasMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new ContractAliasMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContractAliasMasterSoap[] toSoapModels(
        List<ContractAliasMaster> models) {
        List<ContractAliasMasterSoap> soapModels = new ArrayList<ContractAliasMasterSoap>(models.size());

        for (ContractAliasMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContractAliasMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _contractAliasMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setContractAliasMasterSid(pk);
    }

    public int getContractAliasType() {
        return _contractAliasType;
    }

    public void setContractAliasType(int contractAliasType) {
        _contractAliasType = contractAliasType;
    }

    public int getTpCompanyMasterSid() {
        return _tpCompanyMasterSid;
    }

    public void setTpCompanyMasterSid(int tpCompanyMasterSid) {
        _tpCompanyMasterSid = tpCompanyMasterSid;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    public int getContractAliasMasterSid() {
        return _contractAliasMasterSid;
    }

    public void setContractAliasMasterSid(int contractAliasMasterSid) {
        _contractAliasMasterSid = contractAliasMasterSid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getContractAliasNo() {
        return _contractAliasNo;
    }

    public void setContractAliasNo(String contractAliasNo) {
        _contractAliasNo = contractAliasNo;
    }

    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getContractAliasName() {
        return _contractAliasName;
    }

    public void setContractAliasName(String contractAliasName) {
        _contractAliasName = contractAliasName;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
