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
public class ItemIdentifierSoap implements Serializable {
    private int _itemIdentifierSid;
    private int _itemMasterSid;
    private Date _endDate;
    private Date _modifiedDate;
    private int _identifierStatus;
    private String _entityCode;
    private String _itemIdentifierValue;
    private boolean _recordLockStatus;
    private int _itemQualifierSid;
    private Date _startDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private String _batchId;
    private int _modifiedBy;
    private String _inboundStatus;

    public ItemIdentifierSoap() {
    }

    public static ItemIdentifierSoap toSoapModel(ItemIdentifier model) {
        ItemIdentifierSoap soapModel = new ItemIdentifierSoap();

        soapModel.setItemIdentifierSid(model.getItemIdentifierSid());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setIdentifierStatus(model.getIdentifierStatus());
        soapModel.setEntityCode(model.getEntityCode());
        soapModel.setItemIdentifierValue(model.getItemIdentifierValue());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setItemQualifierSid(model.getItemQualifierSid());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static ItemIdentifierSoap[] toSoapModels(ItemIdentifier[] models) {
        ItemIdentifierSoap[] soapModels = new ItemIdentifierSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ItemIdentifierSoap[][] toSoapModels(ItemIdentifier[][] models) {
        ItemIdentifierSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ItemIdentifierSoap[models.length][models[0].length];
        } else {
            soapModels = new ItemIdentifierSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ItemIdentifierSoap[] toSoapModels(List<ItemIdentifier> models) {
        List<ItemIdentifierSoap> soapModels = new ArrayList<ItemIdentifierSoap>(models.size());

        for (ItemIdentifier model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ItemIdentifierSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _itemIdentifierSid;
    }

    public void setPrimaryKey(int pk) {
        setItemIdentifierSid(pk);
    }

    public int getItemIdentifierSid() {
        return _itemIdentifierSid;
    }

    public void setItemIdentifierSid(int itemIdentifierSid) {
        _itemIdentifierSid = itemIdentifierSid;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getIdentifierStatus() {
        return _identifierStatus;
    }

    public void setIdentifierStatus(int identifierStatus) {
        _identifierStatus = identifierStatus;
    }

    public String getEntityCode() {
        return _entityCode;
    }

    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;
    }

    public String getItemIdentifierValue() {
        return _itemIdentifierValue;
    }

    public void setItemIdentifierValue(String itemIdentifierValue) {
        _itemIdentifierValue = itemIdentifierValue;
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

    public int getItemQualifierSid() {
        return _itemQualifierSid;
    }

    public void setItemQualifierSid(int itemQualifierSid) {
        _itemQualifierSid = itemQualifierSid;
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
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

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
