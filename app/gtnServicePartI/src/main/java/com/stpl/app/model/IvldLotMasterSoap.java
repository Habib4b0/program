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
public class IvldLotMasterSoap implements Serializable {
    private String _reasonForFailure;
    private String _itemId;
    private int _ivldLotMasterSid;
    private Date _modifiedDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _lastLotSoldDate;
    private String _lotExpirationDate;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _lotNo;
    private String _reprocessedFlag;
    private String _lotMasterIntfid;
    private boolean _checkRecord;

    public IvldLotMasterSoap() {
    }

    public static IvldLotMasterSoap toSoapModel(IvldLotMaster model) {
        IvldLotMasterSoap soapModel = new IvldLotMasterSoap();

        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setItemId(model.getItemId());
        soapModel.setIvldLotMasterSid(model.getIvldLotMasterSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setLastLotSoldDate(model.getLastLotSoldDate());
        soapModel.setLotExpirationDate(model.getLotExpirationDate());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setLotNo(model.getLotNo());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setLotMasterIntfid(model.getLotMasterIntfid());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldLotMasterSoap[] toSoapModels(IvldLotMaster[] models) {
        IvldLotMasterSoap[] soapModels = new IvldLotMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldLotMasterSoap[][] toSoapModels(IvldLotMaster[][] models) {
        IvldLotMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldLotMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldLotMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldLotMasterSoap[] toSoapModels(List<IvldLotMaster> models) {
        List<IvldLotMasterSoap> soapModels = new ArrayList<IvldLotMasterSoap>(models.size());

        for (IvldLotMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldLotMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldLotMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldLotMasterSid(pk);
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public int getIvldLotMasterSid() {
        return _ivldLotMasterSid;
    }

    public void setIvldLotMasterSid(int ivldLotMasterSid) {
        _ivldLotMasterSid = ivldLotMasterSid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
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

    public String getLastLotSoldDate() {
        return _lastLotSoldDate;
    }

    public void setLastLotSoldDate(String lastLotSoldDate) {
        _lastLotSoldDate = lastLotSoldDate;
    }

    public String getLotExpirationDate() {
        return _lotExpirationDate;
    }

    public void setLotExpirationDate(String lotExpirationDate) {
        _lotExpirationDate = lotExpirationDate;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getLotNo() {
        return _lotNo;
    }

    public void setLotNo(String lotNo) {
        _lotNo = lotNo;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getLotMasterIntfid() {
        return _lotMasterIntfid;
    }

    public void setLotMasterIntfid(String lotMasterIntfid) {
        _lotMasterIntfid = lotMasterIntfid;
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
}
