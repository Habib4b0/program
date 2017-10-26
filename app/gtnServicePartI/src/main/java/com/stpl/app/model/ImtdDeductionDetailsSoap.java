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
public class ImtdDeductionDetailsSoap implements Serializable {
    private int _imtdDeductionDetailsSid;
    private String _deductionName;
    private int _modifiedBy;
    private Date _createdDate;
    private String _imtdCreatedDate;
    private int _deductionDetailsSid;
    private String _indicator;
    private String _contractNo;
    private boolean _checkRecord;
    private String _deductionSubType;
    private int _cdrModelSid;
    private int _createdBy;
    private String _deductionNo;
    private int _netSalesFormulaMasterSid;
    private int _usersSid;
    private int _contractMasterSid;
    private String _contractName;
    private String _deductionCategory;
    private Date _modifiedDate;
    private String _deductionType;
    private boolean _recordLockStatus;
    private String _operation;
    private String _sessionId;
    private int _rsContractSid;
    private String _inboundStatus;

    public ImtdDeductionDetailsSoap() {
    }

    public static ImtdDeductionDetailsSoap toSoapModel(
        ImtdDeductionDetails model) {
        ImtdDeductionDetailsSoap soapModel = new ImtdDeductionDetailsSoap();

        soapModel.setImtdDeductionDetailsSid(model.getImtdDeductionDetailsSid());
        soapModel.setDeductionName(model.getDeductionName());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setImtdCreatedDate(model.getImtdCreatedDate());
        soapModel.setDeductionDetailsSid(model.getDeductionDetailsSid());
        soapModel.setIndicator(model.getIndicator());
        soapModel.setContractNo(model.getContractNo());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setDeductionSubType(model.getDeductionSubType());
        soapModel.setCdrModelSid(model.getCdrModelSid());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setDeductionNo(model.getDeductionNo());
        soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
        soapModel.setUsersSid(model.getUsersSid());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setContractName(model.getContractName());
        soapModel.setDeductionCategory(model.getDeductionCategory());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setDeductionType(model.getDeductionType());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setOperation(model.getOperation());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setRsContractSid(model.getRsContractSid());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static ImtdDeductionDetailsSoap[] toSoapModels(
        ImtdDeductionDetails[] models) {
        ImtdDeductionDetailsSoap[] soapModels = new ImtdDeductionDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImtdDeductionDetailsSoap[][] toSoapModels(
        ImtdDeductionDetails[][] models) {
        ImtdDeductionDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImtdDeductionDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new ImtdDeductionDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImtdDeductionDetailsSoap[] toSoapModels(
        List<ImtdDeductionDetails> models) {
        List<ImtdDeductionDetailsSoap> soapModels = new ArrayList<ImtdDeductionDetailsSoap>(models.size());

        for (ImtdDeductionDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImtdDeductionDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _imtdDeductionDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setImtdDeductionDetailsSid(pk);
    }

    public int getImtdDeductionDetailsSid() {
        return _imtdDeductionDetailsSid;
    }

    public void setImtdDeductionDetailsSid(int imtdDeductionDetailsSid) {
        _imtdDeductionDetailsSid = imtdDeductionDetailsSid;
    }

    public String getDeductionName() {
        return _deductionName;
    }

    public void setDeductionName(String deductionName) {
        _deductionName = deductionName;
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

    public String getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    public void setImtdCreatedDate(String imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;
    }

    public int getDeductionDetailsSid() {
        return _deductionDetailsSid;
    }

    public void setDeductionDetailsSid(int deductionDetailsSid) {
        _deductionDetailsSid = deductionDetailsSid;
    }

    public String getIndicator() {
        return _indicator;
    }

    public void setIndicator(String indicator) {
        _indicator = indicator;
    }

    public String getContractNo() {
        return _contractNo;
    }

    public void setContractNo(String contractNo) {
        _contractNo = contractNo;
    }

    public boolean getCheckRecord() {
        return _checkRecord;
    }

    public boolean isCheckRecord() {
        return _checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;
    }

    public String getDeductionSubType() {
        return _deductionSubType;
    }

    public void setDeductionSubType(String deductionSubType) {
        _deductionSubType = deductionSubType;
    }

    public int getCdrModelSid() {
        return _cdrModelSid;
    }

    public void setCdrModelSid(int cdrModelSid) {
        _cdrModelSid = cdrModelSid;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getDeductionNo() {
        return _deductionNo;
    }

    public void setDeductionNo(String deductionNo) {
        _deductionNo = deductionNo;
    }

    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;
    }

    public int getUsersSid() {
        return _usersSid;
    }

    public void setUsersSid(int usersSid) {
        _usersSid = usersSid;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public String getContractName() {
        return _contractName;
    }

    public void setContractName(String contractName) {
        _contractName = contractName;
    }

    public String getDeductionCategory() {
        return _deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        _deductionCategory = deductionCategory;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getDeductionType() {
        return _deductionType;
    }

    public void setDeductionType(String deductionType) {
        _deductionType = deductionType;
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

    public String getOperation() {
        return _operation;
    }

    public void setOperation(String operation) {
        _operation = operation;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }

    public int getRsContractSid() {
        return _rsContractSid;
    }

    public void setRsContractSid(int rsContractSid) {
        _rsContractSid = rsContractSid;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
