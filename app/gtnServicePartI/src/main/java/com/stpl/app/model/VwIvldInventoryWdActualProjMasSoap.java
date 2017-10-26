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
public class VwIvldInventoryWdActualProjMasSoap implements Serializable {
    private int _ivldInventoryWdActualProjMasSid;
    private String _quantityOnOrder;
    private String _week;
    private double _price;
    private String _amountOnHand;
    private String _isMaster;
    private String _companyName;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _day;
    private String _addChgDelIndicator;
    private String _unitsOnHand;
    private String _amountReceived;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _month;
    private String _reprocessedFlag;
    private String _amountWithdrawn;
    private String _quantityReceived;
    private String _unitsWithdrawn;
    private String _reasonForFailure;
    private String _country;
    private String _companyId;
    private String _isForecast;
    private String _inventoryWdActualProjMasIntfId;
    private String _forecastVer;
    private String _batchId;
    private String _itemName;
    private String _errorField;
    private String _amountOnOrder;
    private String _forecastName;
    private boolean _checkRecord;

    public VwIvldInventoryWdActualProjMasSoap() {
    }

    public static VwIvldInventoryWdActualProjMasSoap toSoapModel(
        VwIvldInventoryWdActualProjMas model) {
        VwIvldInventoryWdActualProjMasSoap soapModel = new VwIvldInventoryWdActualProjMasSoap();

        soapModel.setIvldInventoryWdActualProjMasSid(model.getIvldInventoryWdActualProjMasSid());
        soapModel.setQuantityOnOrder(model.getQuantityOnOrder());
        soapModel.setWeek(model.getWeek());
        soapModel.setPrice(model.getPrice());
        soapModel.setAmountOnHand(model.getAmountOnHand());
        soapModel.setIsMaster(model.getIsMaster());
        soapModel.setCompanyName(model.getCompanyName());
        soapModel.setYear(model.getYear());
        soapModel.setItemId(model.getItemId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setDay(model.getDay());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setUnitsOnHand(model.getUnitsOnHand());
        soapModel.setAmountReceived(model.getAmountReceived());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setMonth(model.getMonth());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setAmountWithdrawn(model.getAmountWithdrawn());
        soapModel.setQuantityReceived(model.getQuantityReceived());
        soapModel.setUnitsWithdrawn(model.getUnitsWithdrawn());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setCountry(model.getCountry());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setIsForecast(model.getIsForecast());
        soapModel.setInventoryWdActualProjMasIntfId(model.getInventoryWdActualProjMasIntfId());
        soapModel.setForecastVer(model.getForecastVer());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setItemName(model.getItemName());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setAmountOnOrder(model.getAmountOnOrder());
        soapModel.setForecastName(model.getForecastName());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static VwIvldInventoryWdActualProjMasSoap[] toSoapModels(
        VwIvldInventoryWdActualProjMas[] models) {
        VwIvldInventoryWdActualProjMasSoap[] soapModels = new VwIvldInventoryWdActualProjMasSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static VwIvldInventoryWdActualProjMasSoap[][] toSoapModels(
        VwIvldInventoryWdActualProjMas[][] models) {
        VwIvldInventoryWdActualProjMasSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new VwIvldInventoryWdActualProjMasSoap[models.length][models[0].length];
        } else {
            soapModels = new VwIvldInventoryWdActualProjMasSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static VwIvldInventoryWdActualProjMasSoap[] toSoapModels(
        List<VwIvldInventoryWdActualProjMas> models) {
        List<VwIvldInventoryWdActualProjMasSoap> soapModels = new ArrayList<VwIvldInventoryWdActualProjMasSoap>(models.size());

        for (VwIvldInventoryWdActualProjMas model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new VwIvldInventoryWdActualProjMasSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldInventoryWdActualProjMasSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldInventoryWdActualProjMasSid(pk);
    }

    public int getIvldInventoryWdActualProjMasSid() {
        return _ivldInventoryWdActualProjMasSid;
    }

    public void setIvldInventoryWdActualProjMasSid(
        int ivldInventoryWdActualProjMasSid) {
        _ivldInventoryWdActualProjMasSid = ivldInventoryWdActualProjMasSid;
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

    public double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public String getAmountOnHand() {
        return _amountOnHand;
    }

    public void setAmountOnHand(String amountOnHand) {
        _amountOnHand = amountOnHand;
    }

    public String getIsMaster() {
        return _isMaster;
    }

    public void setIsMaster(String isMaster) {
        _isMaster = isMaster;
    }

    public String getCompanyName() {
        return _companyName;
    }

    public void setCompanyName(String companyName) {
        _companyName = companyName;
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

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public String getIsForecast() {
        return _isForecast;
    }

    public void setIsForecast(String isForecast) {
        _isForecast = isForecast;
    }

    public String getInventoryWdActualProjMasIntfId() {
        return _inventoryWdActualProjMasIntfId;
    }

    public void setInventoryWdActualProjMasIntfId(
        String inventoryWdActualProjMasIntfId) {
        _inventoryWdActualProjMasIntfId = inventoryWdActualProjMasIntfId;
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

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
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

    public String getForecastName() {
        return _forecastName;
    }

    public void setForecastName(String forecastName) {
        _forecastName = forecastName;
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
