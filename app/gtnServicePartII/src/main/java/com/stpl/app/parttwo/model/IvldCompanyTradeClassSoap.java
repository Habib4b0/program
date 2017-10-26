package com.stpl.app.parttwo.model;

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
public class IvldCompanyTradeClassSoap implements Serializable {
    private int _ivldCompanyTradeClassSid;
    private String _priorTradeClass;
    private String _reasonForFailure;
    private String _companyId;
    private String _lastUpdatedDate;
    private String _priorTradeClassStartDate;
    private Date _modifiedDate;
    private String _tradeClassEndDate;
    private String _tradeClassIntfid;
    private String _tradeClassStartDate;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _batchId;
    private String _errorField;
    private String _errorCode;
    private String _tradeClass;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private boolean _checkRecord;

    public IvldCompanyTradeClassSoap() {
    }

    public static IvldCompanyTradeClassSoap toSoapModel(
        IvldCompanyTradeClass model) {
        IvldCompanyTradeClassSoap soapModel = new IvldCompanyTradeClassSoap();

        soapModel.setIvldCompanyTradeClassSid(model.getIvldCompanyTradeClassSid());
        soapModel.setPriorTradeClass(model.getPriorTradeClass());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
        soapModel.setPriorTradeClassStartDate(model.getPriorTradeClassStartDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setTradeClassEndDate(model.getTradeClassEndDate());
        soapModel.setTradeClassIntfid(model.getTradeClassIntfid());
        soapModel.setTradeClassStartDate(model.getTradeClassStartDate());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setTradeClass(model.getTradeClass());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldCompanyTradeClassSoap[] toSoapModels(
        IvldCompanyTradeClass[] models) {
        IvldCompanyTradeClassSoap[] soapModels = new IvldCompanyTradeClassSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldCompanyTradeClassSoap[][] toSoapModels(
        IvldCompanyTradeClass[][] models) {
        IvldCompanyTradeClassSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldCompanyTradeClassSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldCompanyTradeClassSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldCompanyTradeClassSoap[] toSoapModels(
        List<IvldCompanyTradeClass> models) {
        List<IvldCompanyTradeClassSoap> soapModels = new ArrayList<IvldCompanyTradeClassSoap>(models.size());

        for (IvldCompanyTradeClass model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldCompanyTradeClassSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldCompanyTradeClassSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldCompanyTradeClassSid(pk);
    }

    public int getIvldCompanyTradeClassSid() {
        return _ivldCompanyTradeClassSid;
    }

    public void setIvldCompanyTradeClassSid(int ivldCompanyTradeClassSid) {
        _ivldCompanyTradeClassSid = ivldCompanyTradeClassSid;
    }

    public String getPriorTradeClass() {
        return _priorTradeClass;
    }

    public void setPriorTradeClass(String priorTradeClass) {
        _priorTradeClass = priorTradeClass;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public String getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;
    }

    public String getPriorTradeClassStartDate() {
        return _priorTradeClassStartDate;
    }

    public void setPriorTradeClassStartDate(String priorTradeClassStartDate) {
        _priorTradeClassStartDate = priorTradeClassStartDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getTradeClassEndDate() {
        return _tradeClassEndDate;
    }

    public void setTradeClassEndDate(String tradeClassEndDate) {
        _tradeClassEndDate = tradeClassEndDate;
    }

    public String getTradeClassIntfid() {
        return _tradeClassIntfid;
    }

    public void setTradeClassIntfid(String tradeClassIntfid) {
        _tradeClassIntfid = tradeClassIntfid;
    }

    public String getTradeClassStartDate() {
        return _tradeClassStartDate;
    }

    public void setTradeClassStartDate(String tradeClassStartDate) {
        _tradeClassStartDate = tradeClassStartDate;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
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

    public String getTradeClass() {
        return _tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        _tradeClass = tradeClass;
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
