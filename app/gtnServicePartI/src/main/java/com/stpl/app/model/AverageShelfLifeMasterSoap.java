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
public class AverageShelfLifeMasterSoap implements Serializable {
    private int _createdBy;
    private int _averageShelfLifeMasterSid;
    private boolean _recordLockStatus;
    private String _itemIdType;
    private int _modifiedBy;
    private Date _createdDate;
    private String _source;
    private String _itemId;
    private String _avgShelfLife;
    private String _batchId;
    private Date _modifiedDate;
    private String _inboundStatus;

    public AverageShelfLifeMasterSoap() {
    }

    public static AverageShelfLifeMasterSoap toSoapModel(
        AverageShelfLifeMaster model) {
        AverageShelfLifeMasterSoap soapModel = new AverageShelfLifeMasterSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setAverageShelfLifeMasterSid(model.getAverageShelfLifeMasterSid());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setItemIdType(model.getItemIdType());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setItemId(model.getItemId());
        soapModel.setAvgShelfLife(model.getAvgShelfLife());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static AverageShelfLifeMasterSoap[] toSoapModels(
        AverageShelfLifeMaster[] models) {
        AverageShelfLifeMasterSoap[] soapModels = new AverageShelfLifeMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AverageShelfLifeMasterSoap[][] toSoapModels(
        AverageShelfLifeMaster[][] models) {
        AverageShelfLifeMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AverageShelfLifeMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new AverageShelfLifeMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AverageShelfLifeMasterSoap[] toSoapModels(
        List<AverageShelfLifeMaster> models) {
        List<AverageShelfLifeMasterSoap> soapModels = new ArrayList<AverageShelfLifeMasterSoap>(models.size());

        for (AverageShelfLifeMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AverageShelfLifeMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _averageShelfLifeMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setAverageShelfLifeMasterSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getAverageShelfLifeMasterSid() {
        return _averageShelfLifeMasterSid;
    }

    public void setAverageShelfLifeMasterSid(int averageShelfLifeMasterSid) {
        _averageShelfLifeMasterSid = averageShelfLifeMasterSid;
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

    public String getItemIdType() {
        return _itemIdType;
    }

    public void setItemIdType(String itemIdType) {
        _itemIdType = itemIdType;
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

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
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

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
