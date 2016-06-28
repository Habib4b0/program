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
public class IvldCpiSoap implements Serializable {
    private String _effectiveDate;
    private String _reasonForFailure;
    private String _indexType;
    private String _status;
    private Date _modifiedDate;
    private String _cpiIntfid;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _indexValue;
    private String _addChgDelIndicator;
    private String _batchId;
    private int _ivldCpiSid;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private String _indexId;

    public IvldCpiSoap() {
    }

    public static IvldCpiSoap toSoapModel(IvldCpi model) {
        IvldCpiSoap soapModel = new IvldCpiSoap();

        soapModel.setEffectiveDate(model.getEffectiveDate());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setIndexType(model.getIndexType());
        soapModel.setStatus(model.getStatus());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCpiIntfid(model.getCpiIntfid());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setIndexValue(model.getIndexValue());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setIvldCpiSid(model.getIvldCpiSid());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setIndexId(model.getIndexId());

        return soapModel;
    }

    public static IvldCpiSoap[] toSoapModels(IvldCpi[] models) {
        IvldCpiSoap[] soapModels = new IvldCpiSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldCpiSoap[][] toSoapModels(IvldCpi[][] models) {
        IvldCpiSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldCpiSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldCpiSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldCpiSoap[] toSoapModels(List<IvldCpi> models) {
        List<IvldCpiSoap> soapModels = new ArrayList<IvldCpiSoap>(models.size());

        for (IvldCpi model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldCpiSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldCpiSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldCpiSid(pk);
    }

    public String getEffectiveDate() {
        return _effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        _effectiveDate = effectiveDate;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getIndexType() {
        return _indexType;
    }

    public void setIndexType(String indexType) {
        _indexType = indexType;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getCpiIntfid() {
        return _cpiIntfid;
    }

    public void setCpiIntfid(String cpiIntfid) {
        _cpiIntfid = cpiIntfid;
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

    public String getIndexValue() {
        return _indexValue;
    }

    public void setIndexValue(String indexValue) {
        _indexValue = indexValue;
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

    public int getIvldCpiSid() {
        return _ivldCpiSid;
    }

    public void setIvldCpiSid(int ivldCpiSid) {
        _ivldCpiSid = ivldCpiSid;
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

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getIndexId() {
        return _indexId;
    }

    public void setIndexId(String indexId) {
        _indexId = indexId;
    }
}
