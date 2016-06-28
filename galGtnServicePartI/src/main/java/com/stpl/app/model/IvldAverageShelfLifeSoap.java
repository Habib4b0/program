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
public class IvldAverageShelfLifeSoap implements Serializable {
    private String _reasonForFailure;
    private int _ivldAverageShelfLifeSid;
    private String _itemId;
    private String _avgShelfLife;
    private Date _modifiedDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _itemIdType;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _averageShelfLifeIntfid;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;

    public IvldAverageShelfLifeSoap() {
    }

    public static IvldAverageShelfLifeSoap toSoapModel(
        IvldAverageShelfLife model) {
        IvldAverageShelfLifeSoap soapModel = new IvldAverageShelfLifeSoap();

        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setIvldAverageShelfLifeSid(model.getIvldAverageShelfLifeSid());
        soapModel.setItemId(model.getItemId());
        soapModel.setAvgShelfLife(model.getAvgShelfLife());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setItemIdType(model.getItemIdType());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setAverageShelfLifeIntfid(model.getAverageShelfLifeIntfid());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());

        return soapModel;
    }

    public static IvldAverageShelfLifeSoap[] toSoapModels(
        IvldAverageShelfLife[] models) {
        IvldAverageShelfLifeSoap[] soapModels = new IvldAverageShelfLifeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldAverageShelfLifeSoap[][] toSoapModels(
        IvldAverageShelfLife[][] models) {
        IvldAverageShelfLifeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldAverageShelfLifeSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldAverageShelfLifeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldAverageShelfLifeSoap[] toSoapModels(
        List<IvldAverageShelfLife> models) {
        List<IvldAverageShelfLifeSoap> soapModels = new ArrayList<IvldAverageShelfLifeSoap>(models.size());

        for (IvldAverageShelfLife model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldAverageShelfLifeSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldAverageShelfLifeSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldAverageShelfLifeSid(pk);
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public int getIvldAverageShelfLifeSid() {
        return _ivldAverageShelfLifeSid;
    }

    public void setIvldAverageShelfLifeSid(int ivldAverageShelfLifeSid) {
        _ivldAverageShelfLifeSid = ivldAverageShelfLifeSid;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public String getAvgShelfLife() {
        return _avgShelfLife;
    }

    public void setAvgShelfLife(String avgShelfLife) {
        _avgShelfLife = avgShelfLife;
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

    public String getItemIdType() {
        return _itemIdType;
    }

    public void setItemIdType(String itemIdType) {
        _itemIdType = itemIdType;
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

    public String getAverageShelfLifeIntfid() {
        return _averageShelfLifeIntfid;
    }

    public void setAverageShelfLifeIntfid(String averageShelfLifeIntfid) {
        _averageShelfLifeIntfid = averageShelfLifeIntfid;
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
}
