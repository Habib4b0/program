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
public class ItemPricingQualifierSoap implements Serializable {
    private int _createdBy;
    private int _itemPricingQualifierSid;
    private String _specificEntityCode;
    private int _modifiedBy;
    private Date _createdDate;
    private String _batchId;
    private Date _modifiedDate;
    private String _effectiveDates;
    private String _notes;
    private boolean _recordLockStatus;
    private String _source;
    private String _pricingQualifier;
    private String _itemPricingQualifierName;
    private String _inboundStatus;

    public ItemPricingQualifierSoap() {
    }

    public static ItemPricingQualifierSoap toSoapModel(
        ItemPricingQualifier model) {
        ItemPricingQualifierSoap soapModel = new ItemPricingQualifierSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setItemPricingQualifierSid(model.getItemPricingQualifierSid());
        soapModel.setSpecificEntityCode(model.getSpecificEntityCode());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setEffectiveDates(model.getEffectiveDates());
        soapModel.setNotes(model.getNotes());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setSource(model.getSource());
        soapModel.setPricingQualifier(model.getPricingQualifier());
        soapModel.setItemPricingQualifierName(model.getItemPricingQualifierName());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static ItemPricingQualifierSoap[] toSoapModels(
        ItemPricingQualifier[] models) {
        ItemPricingQualifierSoap[] soapModels = new ItemPricingQualifierSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ItemPricingQualifierSoap[][] toSoapModels(
        ItemPricingQualifier[][] models) {
        ItemPricingQualifierSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ItemPricingQualifierSoap[models.length][models[0].length];
        } else {
            soapModels = new ItemPricingQualifierSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ItemPricingQualifierSoap[] toSoapModels(
        List<ItemPricingQualifier> models) {
        List<ItemPricingQualifierSoap> soapModels = new ArrayList<ItemPricingQualifierSoap>(models.size());

        for (ItemPricingQualifier model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ItemPricingQualifierSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _itemPricingQualifierSid;
    }

    public void setPrimaryKey(int pk) {
        setItemPricingQualifierSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getItemPricingQualifierSid() {
        return _itemPricingQualifierSid;
    }

    public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
        _itemPricingQualifierSid = itemPricingQualifierSid;
    }

    public String getSpecificEntityCode() {
        return _specificEntityCode;
    }

    public void setSpecificEntityCode(String specificEntityCode) {
        _specificEntityCode = specificEntityCode;
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

    public String getEffectiveDates() {
        return _effectiveDates;
    }

    public void setEffectiveDates(String effectiveDates) {
        _effectiveDates = effectiveDates;
    }

    public String getNotes() {
        return _notes;
    }

    public void setNotes(String notes) {
        _notes = notes;
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

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getPricingQualifier() {
        return _pricingQualifier;
    }

    public void setPricingQualifier(String pricingQualifier) {
        _pricingQualifier = pricingQualifier;
    }

    public String getItemPricingQualifierName() {
        return _itemPricingQualifierName;
    }

    public void setItemPricingQualifierName(String itemPricingQualifierName) {
        _itemPricingQualifierName = itemPricingQualifierName;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
