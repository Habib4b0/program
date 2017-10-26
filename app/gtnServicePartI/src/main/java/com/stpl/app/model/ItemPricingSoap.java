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
public class ItemPricingSoap implements Serializable {
    private int _itemMasterSid;
    private int _itemPricingQualifierSid;
    private double _itemPrice;
    private Date _endDate;
    private Date _modifiedDate;
    private String _entityCode;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _batchId;
    private int _itemUom;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _itemPricingSid;
    private int _pricingCodeStatus;

    public ItemPricingSoap() {
    }

    public static ItemPricingSoap toSoapModel(ItemPricing model) {
        ItemPricingSoap soapModel = new ItemPricingSoap();

        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setItemPricingQualifierSid(model.getItemPricingQualifierSid());
        soapModel.setItemPrice(model.getItemPrice());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setEntityCode(model.getEntityCode());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setItemUom(model.getItemUom());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setItemPricingSid(model.getItemPricingSid());
        soapModel.setPricingCodeStatus(model.getPricingCodeStatus());

        return soapModel;
    }

    public static ItemPricingSoap[] toSoapModels(ItemPricing[] models) {
        ItemPricingSoap[] soapModels = new ItemPricingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ItemPricingSoap[][] toSoapModels(ItemPricing[][] models) {
        ItemPricingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ItemPricingSoap[models.length][models[0].length];
        } else {
            soapModels = new ItemPricingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ItemPricingSoap[] toSoapModels(List<ItemPricing> models) {
        List<ItemPricingSoap> soapModels = new ArrayList<ItemPricingSoap>(models.size());

        for (ItemPricing model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ItemPricingSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _itemPricingSid;
    }

    public void setPrimaryKey(int pk) {
        setItemPricingSid(pk);
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public int getItemPricingQualifierSid() {
        return _itemPricingQualifierSid;
    }

    public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
        _itemPricingQualifierSid = itemPricingQualifierSid;
    }

    public double getItemPrice() {
        return _itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        _itemPrice = itemPrice;
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

    public String getEntityCode() {
        return _entityCode;
    }

    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;
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

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getItemUom() {
        return _itemUom;
    }

    public void setItemUom(int itemUom) {
        _itemUom = itemUom;
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

    public int getItemPricingSid() {
        return _itemPricingSid;
    }

    public void setItemPricingSid(int itemPricingSid) {
        _itemPricingSid = itemPricingSid;
    }

    public int getPricingCodeStatus() {
        return _pricingCodeStatus;
    }

    public void setPricingCodeStatus(int pricingCodeStatus) {
        _pricingCodeStatus = pricingCodeStatus;
    }
}
