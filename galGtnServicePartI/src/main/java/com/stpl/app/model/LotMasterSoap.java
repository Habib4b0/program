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
public class LotMasterSoap implements Serializable {
    private int _createdBy;
    private int _modifiedBy;
    private Date _createdDate;
    private String _itemId;
    private String _batchId;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private Date _lastLotSoldDate;
    private Date _lotExpirationDate;
    private String _source;
    private int _lotMasterSid;
    private String _lotNo;
    private String _inboundStatus;

    public LotMasterSoap() {
    }

    public static LotMasterSoap toSoapModel(LotMaster model) {
        LotMasterSoap soapModel = new LotMasterSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setItemId(model.getItemId());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setLastLotSoldDate(model.getLastLotSoldDate());
        soapModel.setLotExpirationDate(model.getLotExpirationDate());
        soapModel.setSource(model.getSource());
        soapModel.setLotMasterSid(model.getLotMasterSid());
        soapModel.setLotNo(model.getLotNo());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static LotMasterSoap[] toSoapModels(LotMaster[] models) {
        LotMasterSoap[] soapModels = new LotMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static LotMasterSoap[][] toSoapModels(LotMaster[][] models) {
        LotMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new LotMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new LotMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static LotMasterSoap[] toSoapModels(List<LotMaster> models) {
        List<LotMasterSoap> soapModels = new ArrayList<LotMasterSoap>(models.size());

        for (LotMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new LotMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _lotMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setLotMasterSid(pk);
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

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
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

    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;
    }

    public Date getLastLotSoldDate() {
        return _lastLotSoldDate;
    }

    public void setLastLotSoldDate(Date lastLotSoldDate) {
        _lastLotSoldDate = lastLotSoldDate;
    }

    public Date getLotExpirationDate() {
        return _lotExpirationDate;
    }

    public void setLotExpirationDate(Date lotExpirationDate) {
        _lotExpirationDate = lotExpirationDate;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getLotMasterSid() {
        return _lotMasterSid;
    }

    public void setLotMasterSid(int lotMasterSid) {
        _lotMasterSid = lotMasterSid;
    }

    public String getLotNo() {
        return _lotNo;
    }

    public void setLotNo(String lotNo) {
        _lotNo = lotNo;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
