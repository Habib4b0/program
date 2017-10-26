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
public class IvldInventoryWdProjMasSoap implements Serializable {
    private int _inventoryWdProjMasIntfId;
    private String _week;
    private String _unitsWithdrawn;
    private String _reasonForFailure;
    private String _country;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _itemIdentifierCodeQualifier;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _day;
    private String _forecastVer;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _itemIdentifier;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private int _ivldInventoryWdProjMasSid;
    private String _month;
    private String _reprocessedFlag;
    private String _forecastName;
    private String _amountWithdrawn;
    private boolean _checkRecord;

    public IvldInventoryWdProjMasSoap() {
    }

    public static IvldInventoryWdProjMasSoap toSoapModel(
        IvldInventoryWdProjMas model) {
        IvldInventoryWdProjMasSoap soapModel = new IvldInventoryWdProjMasSoap();

        soapModel.setInventoryWdProjMasIntfId(model.getInventoryWdProjMasIntfId());
        soapModel.setWeek(model.getWeek());
        soapModel.setUnitsWithdrawn(model.getUnitsWithdrawn());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setCountry(model.getCountry());
        soapModel.setYear(model.getYear());
        soapModel.setItemId(model.getItemId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setDay(model.getDay());
        soapModel.setForecastVer(model.getForecastVer());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setItemIdentifier(model.getItemIdentifier());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setIvldInventoryWdProjMasSid(model.getIvldInventoryWdProjMasSid());
        soapModel.setMonth(model.getMonth());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setForecastName(model.getForecastName());
        soapModel.setAmountWithdrawn(model.getAmountWithdrawn());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldInventoryWdProjMasSoap[] toSoapModels(
        IvldInventoryWdProjMas[] models) {
        IvldInventoryWdProjMasSoap[] soapModels = new IvldInventoryWdProjMasSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldInventoryWdProjMasSoap[][] toSoapModels(
        IvldInventoryWdProjMas[][] models) {
        IvldInventoryWdProjMasSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldInventoryWdProjMasSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldInventoryWdProjMasSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldInventoryWdProjMasSoap[] toSoapModels(
        List<IvldInventoryWdProjMas> models) {
        List<IvldInventoryWdProjMasSoap> soapModels = new ArrayList<IvldInventoryWdProjMasSoap>(models.size());

        for (IvldInventoryWdProjMas model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldInventoryWdProjMasSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldInventoryWdProjMasSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldInventoryWdProjMasSid(pk);
    }

    public int getInventoryWdProjMasIntfId() {
        return _inventoryWdProjMasIntfId;
    }

    public void setInventoryWdProjMasIntfId(int inventoryWdProjMasIntfId) {
        _inventoryWdProjMasIntfId = inventoryWdProjMasIntfId;
    }

    public String getWeek() {
        return _week;
    }

    public void setWeek(String week) {
        _week = week;
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

    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public String getDay() {
        return _day;
    }

    public void setDay(String day) {
        _day = day;
    }

    public String getForecastVer() {
        return _forecastVer;
    }

    public void setForecastVer(String forecastVer) {
        _forecastVer = forecastVer;
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

    public String getItemIdentifier() {
        return _itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        _itemIdentifier = itemIdentifier;
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

    public int getIvldInventoryWdProjMasSid() {
        return _ivldInventoryWdProjMasSid;
    }

    public void setIvldInventoryWdProjMasSid(int ivldInventoryWdProjMasSid) {
        _ivldInventoryWdProjMasSid = ivldInventoryWdProjMasSid;
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

    public String getForecastName() {
        return _forecastName;
    }

    public void setForecastName(String forecastName) {
        _forecastName = forecastName;
    }

    public String getAmountWithdrawn() {
        return _amountWithdrawn;
    }

    public void setAmountWithdrawn(String amountWithdrawn) {
        _amountWithdrawn = amountWithdrawn;
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
