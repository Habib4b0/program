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
public class ImtdRsDetailsFrSoap implements Serializable {
    private String _formulaMethodId;
    private int _itemMasterSid;
    private int _imtdRsDetailsSid;
    private Date _modifiedDate;
    private int _rsDetailsFrSid;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private int _imtdRsDetailsFrSid;
    private String _batchId;
    private Date _imtdCreatedDate;
    private String _sessionId;
    private String _usersId;
    private String _operation;
    private int _modifiedBy;
    private int _rsDetailsSid;
    private int _formulaId;
    private String _inboundStatus;

    public ImtdRsDetailsFrSoap() {
    }

    public static ImtdRsDetailsFrSoap toSoapModel(ImtdRsDetailsFr model) {
        ImtdRsDetailsFrSoap soapModel = new ImtdRsDetailsFrSoap();

        soapModel.setFormulaMethodId(model.getFormulaMethodId());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setImtdRsDetailsSid(model.getImtdRsDetailsSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRsDetailsFrSid(model.getRsDetailsFrSid());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setImtdRsDetailsFrSid(model.getImtdRsDetailsFrSid());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setImtdCreatedDate(model.getImtdCreatedDate());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setUsersId(model.getUsersId());
        soapModel.setOperation(model.getOperation());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setRsDetailsSid(model.getRsDetailsSid());
        soapModel.setFormulaId(model.getFormulaId());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static ImtdRsDetailsFrSoap[] toSoapModels(ImtdRsDetailsFr[] models) {
        ImtdRsDetailsFrSoap[] soapModels = new ImtdRsDetailsFrSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImtdRsDetailsFrSoap[][] toSoapModels(
        ImtdRsDetailsFr[][] models) {
        ImtdRsDetailsFrSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImtdRsDetailsFrSoap[models.length][models[0].length];
        } else {
            soapModels = new ImtdRsDetailsFrSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImtdRsDetailsFrSoap[] toSoapModels(
        List<ImtdRsDetailsFr> models) {
        List<ImtdRsDetailsFrSoap> soapModels = new ArrayList<ImtdRsDetailsFrSoap>(models.size());

        for (ImtdRsDetailsFr model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImtdRsDetailsFrSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _imtdRsDetailsFrSid;
    }

    public void setPrimaryKey(int pk) {
        setImtdRsDetailsFrSid(pk);
    }

    public String getFormulaMethodId() {
        return _formulaMethodId;
    }

    public void setFormulaMethodId(String formulaMethodId) {
        _formulaMethodId = formulaMethodId;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public int getImtdRsDetailsSid() {
        return _imtdRsDetailsSid;
    }

    public void setImtdRsDetailsSid(int imtdRsDetailsSid) {
        _imtdRsDetailsSid = imtdRsDetailsSid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getRsDetailsFrSid() {
        return _rsDetailsFrSid;
    }

    public void setRsDetailsFrSid(int rsDetailsFrSid) {
        _rsDetailsFrSid = rsDetailsFrSid;
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

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getImtdRsDetailsFrSid() {
        return _imtdRsDetailsFrSid;
    }

    public void setImtdRsDetailsFrSid(int imtdRsDetailsFrSid) {
        _imtdRsDetailsFrSid = imtdRsDetailsFrSid;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public Date getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    public void setImtdCreatedDate(Date imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }

    public String getUsersId() {
        return _usersId;
    }

    public void setUsersId(String usersId) {
        _usersId = usersId;
    }

    public String getOperation() {
        return _operation;
    }

    public void setOperation(String operation) {
        _operation = operation;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getRsDetailsSid() {
        return _rsDetailsSid;
    }

    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetailsSid = rsDetailsSid;
    }

    public int getFormulaId() {
        return _formulaId;
    }

    public void setFormulaId(int formulaId) {
        _formulaId = formulaId;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
