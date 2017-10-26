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
public class InventoryWdActualMasSoap implements Serializable {
    private String _quantityOnOrder;
    private String _week;
    private String _amountOnHand;
    private int _itemMasterSid;
    private int _inventoryWdActualMasSid;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _day;
    private String _unitsOnHand;
    private String _amountReceived;
    private String _itemIdentifier;
    private String _modifiedBy;
    private String _inboundStatus;
    private String _month;
    private String _amountWithdrawn;
    private String _quantityReceived;
    private String _unitsWithdrawn;
    private String _country;
    private boolean _recordLockStatus;
    private String _itemIdentifierCodeQualifier;
    private String _batchId;
    private String _amountOnOrder;

    public InventoryWdActualMasSoap() {
    }

    public static InventoryWdActualMasSoap toSoapModel(
        InventoryWdActualMas model) {
        InventoryWdActualMasSoap soapModel = new InventoryWdActualMasSoap();

        soapModel.setQuantityOnOrder(model.getQuantityOnOrder());
        soapModel.setWeek(model.getWeek());
        soapModel.setAmountOnHand(model.getAmountOnHand());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setInventoryWdActualMasSid(model.getInventoryWdActualMasSid());
        soapModel.setYear(model.getYear());
        soapModel.setItemId(model.getItemId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setDay(model.getDay());
        soapModel.setUnitsOnHand(model.getUnitsOnHand());
        soapModel.setAmountReceived(model.getAmountReceived());
        soapModel.setItemIdentifier(model.getItemIdentifier());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setMonth(model.getMonth());
        soapModel.setAmountWithdrawn(model.getAmountWithdrawn());
        soapModel.setQuantityReceived(model.getQuantityReceived());
        soapModel.setUnitsWithdrawn(model.getUnitsWithdrawn());
        soapModel.setCountry(model.getCountry());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAmountOnOrder(model.getAmountOnOrder());

        return soapModel;
    }

    public static InventoryWdActualMasSoap[] toSoapModels(
        InventoryWdActualMas[] models) {
        InventoryWdActualMasSoap[] soapModels = new InventoryWdActualMasSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static InventoryWdActualMasSoap[][] toSoapModels(
        InventoryWdActualMas[][] models) {
        InventoryWdActualMasSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new InventoryWdActualMasSoap[models.length][models[0].length];
        } else {
            soapModels = new InventoryWdActualMasSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static InventoryWdActualMasSoap[] toSoapModels(
        List<InventoryWdActualMas> models) {
        List<InventoryWdActualMasSoap> soapModels = new ArrayList<InventoryWdActualMasSoap>(models.size());

        for (InventoryWdActualMas model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new InventoryWdActualMasSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _inventoryWdActualMasSid;
    }

    public void setPrimaryKey(int pk) {
        setInventoryWdActualMasSid(pk);
    }

    public String getQuantityOnOrder() {
        return _quantityOnOrder;
    }

    public void setQuantityOnOrder(String quantityOnOrder) {
        _quantityOnOrder = quantityOnOrder;
    }

    public String getWeek() {
        return _week;
    }

    public void setWeek(String week) {
        _week = week;
    }

    public String getAmountOnHand() {
        return _amountOnHand;
    }

    public void setAmountOnHand(String amountOnHand) {
        _amountOnHand = amountOnHand;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public int getInventoryWdActualMasSid() {
        return _inventoryWdActualMasSid;
    }

    public void setInventoryWdActualMasSid(int inventoryWdActualMasSid) {
        _inventoryWdActualMasSid = inventoryWdActualMasSid;
    }

    public String getYear() {
        return _year;
    }

    public void setYear(String year) {
        _year = year;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getOrganizationKey() {
        return _organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;
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

    public String getDay() {
        return _day;
    }

    public void setDay(String day) {
        _day = day;
    }

    public String getUnitsOnHand() {
        return _unitsOnHand;
    }

    public void setUnitsOnHand(String unitsOnHand) {
        _unitsOnHand = unitsOnHand;
    }

    public String getAmountReceived() {
        return _amountReceived;
    }

    public void setAmountReceived(String amountReceived) {
        _amountReceived = amountReceived;
    }

    public String getItemIdentifier() {
        return _itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        _itemIdentifier = itemIdentifier;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public String getMonth() {
        return _month;
    }

    public void setMonth(String month) {
        _month = month;
    }

    public String getAmountWithdrawn() {
        return _amountWithdrawn;
    }

    public void setAmountWithdrawn(String amountWithdrawn) {
        _amountWithdrawn = amountWithdrawn;
    }

    public String getQuantityReceived() {
        return _quantityReceived;
    }

    public void setQuantityReceived(String quantityReceived) {
        _quantityReceived = quantityReceived;
    }

    public String getUnitsWithdrawn() {
        return _unitsWithdrawn;
    }

    public void setUnitsWithdrawn(String unitsWithdrawn) {
        _unitsWithdrawn = unitsWithdrawn;
    }

    public String getCountry() {
        return _country;
    }

    public void setCountry(String country) {
        _country = country;
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

    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getAmountOnOrder() {
        return _amountOnOrder;
    }

    public void setAmountOnOrder(String amountOnOrder) {
        _amountOnOrder = amountOnOrder;
    }
}
