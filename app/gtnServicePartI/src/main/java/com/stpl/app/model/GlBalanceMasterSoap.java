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
public class GlBalanceMasterSoap implements Serializable {
    private int _createdBy;
    private int _modifiedBy;
    private String _accountId;
    private Date _uploadDate;
    private Date _createdDate;
    private int _glBalanceMasterSid;
    private String _isActive;
    private String _batchId;
    private Date _modifiedDate;
    private String _balance;
    private String _closeIndicator;
    private boolean _recordLockStatus;
    private String _year;
    private String _period;
    private String _source;
    private Date _insertedDate;
    private String _accountNo;
    private String _inboundStatus;

    public GlBalanceMasterSoap() {
    }

    public static GlBalanceMasterSoap toSoapModel(GlBalanceMaster model) {
        GlBalanceMasterSoap soapModel = new GlBalanceMasterSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setAccountId(model.getAccountId());
        soapModel.setUploadDate(model.getUploadDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setGlBalanceMasterSid(model.getGlBalanceMasterSid());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setBalance(model.getBalance());
        soapModel.setCloseIndicator(model.getCloseIndicator());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setYear(model.getYear());
        soapModel.setPeriod(model.getPeriod());
        soapModel.setSource(model.getSource());
        soapModel.setInsertedDate(model.getInsertedDate());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static GlBalanceMasterSoap[] toSoapModels(GlBalanceMaster[] models) {
        GlBalanceMasterSoap[] soapModels = new GlBalanceMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static GlBalanceMasterSoap[][] toSoapModels(
        GlBalanceMaster[][] models) {
        GlBalanceMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new GlBalanceMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new GlBalanceMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static GlBalanceMasterSoap[] toSoapModels(
        List<GlBalanceMaster> models) {
        List<GlBalanceMasterSoap> soapModels = new ArrayList<GlBalanceMasterSoap>(models.size());

        for (GlBalanceMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new GlBalanceMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _glBalanceMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setGlBalanceMasterSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public Date getUploadDate() {
        return _uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        _uploadDate = uploadDate;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getGlBalanceMasterSid() {
        return _glBalanceMasterSid;
    }

    public void setGlBalanceMasterSid(int glBalanceMasterSid) {
        _glBalanceMasterSid = glBalanceMasterSid;
    }

    public String getIsActive() {
        return _isActive;
    }

    public void setIsActive(String isActive) {
        _isActive = isActive;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getBalance() {
        return _balance;
    }

    public void setBalance(String balance) {
        _balance = balance;
    }

    public String getCloseIndicator() {
        return _closeIndicator;
    }

    public void setCloseIndicator(String closeIndicator) {
        _closeIndicator = closeIndicator;
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

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public Date getInsertedDate() {
        return _insertedDate;
    }

    public void setInsertedDate(Date insertedDate) {
        _insertedDate = insertedDate;
    }

    public String getAccountNo() {
        return _accountNo;
    }

    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
