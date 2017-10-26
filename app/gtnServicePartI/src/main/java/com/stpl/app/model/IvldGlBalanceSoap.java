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
public class IvldGlBalanceSoap implements Serializable {
    private String _balance;
    private String _accountNo;
    private String _reasonForFailure;
    private String _accountId;
    private String _year;
    private String _period;
    private Date _modifiedDate;
    private String _isActive;
    private String _source;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _batchId;
    private String _closeIndicator;
    private String _insertedDate;
    private String _errorField;
    private int _ivldGlBalanceSid;
    private String _errorCode;
    private String _glBalanceIntfid;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private boolean _checkRecord;

    public IvldGlBalanceSoap() {
    }

    public static IvldGlBalanceSoap toSoapModel(IvldGlBalance model) {
        IvldGlBalanceSoap soapModel = new IvldGlBalanceSoap();

        soapModel.setBalance(model.getBalance());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setAccountId(model.getAccountId());
        soapModel.setYear(model.getYear());
        soapModel.setPeriod(model.getPeriod());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setSource(model.getSource());
        soapModel.setUploadDate(model.getUploadDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setCloseIndicator(model.getCloseIndicator());
        soapModel.setInsertedDate(model.getInsertedDate());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setIvldGlBalanceSid(model.getIvldGlBalanceSid());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setGlBalanceIntfid(model.getGlBalanceIntfid());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldGlBalanceSoap[] toSoapModels(IvldGlBalance[] models) {
        IvldGlBalanceSoap[] soapModels = new IvldGlBalanceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldGlBalanceSoap[][] toSoapModels(IvldGlBalance[][] models) {
        IvldGlBalanceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldGlBalanceSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldGlBalanceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldGlBalanceSoap[] toSoapModels(List<IvldGlBalance> models) {
        List<IvldGlBalanceSoap> soapModels = new ArrayList<IvldGlBalanceSoap>(models.size());

        for (IvldGlBalance model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldGlBalanceSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldGlBalanceSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldGlBalanceSid(pk);
    }

    public String getBalance() {
        return _balance;
    }

    public void setBalance(String balance) {
        _balance = balance;
    }

    public String getAccountNo() {
        return _accountNo;
    }

    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public String getYear() {
        return _year;
    }

    public void setYear(String year) {
        _year = year;
    }

    public String getPeriod() {
        return _period;
    }

    public void setPeriod(String period) {
        _period = period;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getIsActive() {
        return _isActive;
    }

    public void setIsActive(String isActive) {
        _isActive = isActive;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getUploadDate() {
        return _uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        _uploadDate = uploadDate;
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

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getCloseIndicator() {
        return _closeIndicator;
    }

    public void setCloseIndicator(String closeIndicator) {
        _closeIndicator = closeIndicator;
    }

    public String getInsertedDate() {
        return _insertedDate;
    }

    public void setInsertedDate(String insertedDate) {
        _insertedDate = insertedDate;
    }

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    public int getIvldGlBalanceSid() {
        return _ivldGlBalanceSid;
    }

    public void setIvldGlBalanceSid(int ivldGlBalanceSid) {
        _ivldGlBalanceSid = ivldGlBalanceSid;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public String getGlBalanceIntfid() {
        return _glBalanceIntfid;
    }

    public void setGlBalanceIntfid(String glBalanceIntfid) {
        _glBalanceIntfid = glBalanceIntfid;
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

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
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
