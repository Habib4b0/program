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
public class IvldInventoryWdActualMasSoap implements Serializable {
    private String _quantityOnOrder;
    private String _week;
    private String _amountOnHand;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private int _ivldInventoryWdActualMasSid;
    private String _day;
    private String _addChgDelIndicator;
    private String _unitsOnHand;
    private String _amountReceived;
    private String _itemIdentifier;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _month;
    private String _reprocessedFlag;
    private String _amountWithdrawn;
    private int _inventoryWdActualMasIntfId;
    private String _quantityReceived;
    private String _unitsWithdrawn;
    private String _reasonForFailure;
    private String _country;
    private String _itemIdentifierCodeQualifier;
    private String _batchId;
    private String _errorField;
    private String _amountOnOrder;

    public IvldInventoryWdActualMasSoap() {
    }

    public static IvldInventoryWdActualMasSoap toSoapModel(
        IvldInventoryWdActualMas model) {
        IvldInventoryWdActualMasSoap soapModel = new IvldInventoryWdActualMasSoap();

        soapModel.setQuantityOnOrder(model.getQuantityOnOrder());
        soapModel.setWeek(model.getWeek());
        soapModel.setAmountOnHand(model.getAmountOnHand());
        soapModel.setYear(model.getYear());
        soapModel.setItemId(model.getItemId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setIvldInventoryWdActualMasSid(model.getIvldInventoryWdActualMasSid());
        soapModel.setDay(model.getDay());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setUnitsOnHand(model.getUnitsOnHand());
        soapModel.setAmountReceived(model.getAmountReceived());
        soapModel.setItemIdentifier(model.getItemIdentifier());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setMonth(model.getMonth());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setAmountWithdrawn(model.getAmountWithdrawn());
        soapModel.setInventoryWdActualMasIntfId(model.getInventoryWdActualMasIntfId());
        soapModel.setQuantityReceived(model.getQuantityReceived());
        soapModel.setUnitsWithdrawn(model.getUnitsWithdrawn());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setCountry(model.getCountry());
        soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setAmountOnOrder(model.getAmountOnOrder());

        return soapModel;
    }

    public static IvldInventoryWdActualMasSoap[] toSoapModels(
        IvldInventoryWdActualMas[] models) {
        IvldInventoryWdActualMasSoap[] soapModels = new IvldInventoryWdActualMasSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldInventoryWdActualMasSoap[][] toSoapModels(
        IvldInventoryWdActualMas[][] models) {
        IvldInventoryWdActualMasSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldInventoryWdActualMasSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldInventoryWdActualMasSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldInventoryWdActualMasSoap[] toSoapModels(
        List<IvldInventoryWdActualMas> models) {
        List<IvldInventoryWdActualMasSoap> soapModels = new ArrayList<IvldInventoryWdActualMasSoap>(models.size());

        for (IvldInventoryWdActualMas model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldInventoryWdActualMasSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldInventoryWdActualMasSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldInventoryWdActualMasSid(pk);
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

    public int getIvldInventoryWdActualMasSid() {
        return _ivldInventoryWdActualMasSid;
    }

    public void setIvldInventoryWdActualMasSid(int ivldInventoryWdActualMasSid) {
        _ivldInventoryWdActualMasSid = ivldInventoryWdActualMasSid;
    }

    public String getDay() {
        return _day;
    }

    public void setDay(String day) {
        _day = day;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
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

    public String getMonth() {
        return _month;
    }

    public void setMonth(String month) {
        _month = month;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getAmountWithdrawn() {
        return _amountWithdrawn;
    }

    public void setAmountWithdrawn(String amountWithdrawn) {
        _amountWithdrawn = amountWithdrawn;
    }

    public int getInventoryWdActualMasIntfId() {
        return _inventoryWdActualMasIntfId;
    }

    public void setInventoryWdActualMasIntfId(int inventoryWdActualMasIntfId) {
        _inventoryWdActualMasIntfId = inventoryWdActualMasIntfId;
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

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getCountry() {
        return _country;
    }

    public void setCountry(String country) {
        _country = country;
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

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    public String getAmountOnOrder() {
        return _amountOnOrder;
    }

    public void setAmountOnOrder(String amountOnOrder) {
        _amountOnOrder = amountOnOrder;
    }
}
