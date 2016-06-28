package com.stpl.app.parttwo.model;

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
public class CustomerGtsForecastSoap implements Serializable {
    private String _price;
    private int _itemMasterSid;
    private String _forecastYear;
    private String _deductionAmount;
    private String _deductionId;
    private Date _forecastDate;
    private String _itemId;
    private Date _modifiedDate;
    private int _brandMasterSid;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _addChgDelIndicator;
    private String _inboundStatus;
    private String _modifiedBy;
    private String _salesAmount;
    private String _deductionType;
    private int _companyMasterSid;
    private String _units;
    private String _deductionRate;
    private int _customerGtsForecastSid;
    private String _country;
    private String _companyId;
    private String _forecastValueType;
    private String _deductionCategory;
    private String _adjustmentCode;
    private String _deductionProgramType;
    private boolean _recordLockStatus;
    private String _salesInclusion;
    private String _forecastVer;
    private String _batchId;
    private String _priceType;
    private String _forecastMonth;
    private String _deductionInclusion;
    private String _segment;
    private String _brand;
    private String _forecastName;

    public CustomerGtsForecastSoap() {
    }

    public static CustomerGtsForecastSoap toSoapModel(CustomerGtsForecast model) {
        CustomerGtsForecastSoap soapModel = new CustomerGtsForecastSoap();

        soapModel.setPrice(model.getPrice());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setForecastYear(model.getForecastYear());
        soapModel.setDeductionAmount(model.getDeductionAmount());
        soapModel.setDeductionId(model.getDeductionId());
        soapModel.setForecastDate(model.getForecastDate());
        soapModel.setItemId(model.getItemId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setBrandMasterSid(model.getBrandMasterSid());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setSalesAmount(model.getSalesAmount());
        soapModel.setDeductionType(model.getDeductionType());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setUnits(model.getUnits());
        soapModel.setDeductionRate(model.getDeductionRate());
        soapModel.setCustomerGtsForecastSid(model.getCustomerGtsForecastSid());
        soapModel.setCountry(model.getCountry());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setForecastValueType(model.getForecastValueType());
        soapModel.setDeductionCategory(model.getDeductionCategory());
        soapModel.setAdjustmentCode(model.getAdjustmentCode());
        soapModel.setDeductionProgramType(model.getDeductionProgramType());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setSalesInclusion(model.getSalesInclusion());
        soapModel.setForecastVer(model.getForecastVer());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setPriceType(model.getPriceType());
        soapModel.setForecastMonth(model.getForecastMonth());
        soapModel.setDeductionInclusion(model.getDeductionInclusion());
        soapModel.setSegment(model.getSegment());
        soapModel.setBrand(model.getBrand());
        soapModel.setForecastName(model.getForecastName());

        return soapModel;
    }

    public static CustomerGtsForecastSoap[] toSoapModels(
        CustomerGtsForecast[] models) {
        CustomerGtsForecastSoap[] soapModels = new CustomerGtsForecastSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CustomerGtsForecastSoap[][] toSoapModels(
        CustomerGtsForecast[][] models) {
        CustomerGtsForecastSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CustomerGtsForecastSoap[models.length][models[0].length];
        } else {
            soapModels = new CustomerGtsForecastSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CustomerGtsForecastSoap[] toSoapModels(
        List<CustomerGtsForecast> models) {
        List<CustomerGtsForecastSoap> soapModels = new ArrayList<CustomerGtsForecastSoap>(models.size());

        for (CustomerGtsForecast model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CustomerGtsForecastSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _customerGtsForecastSid;
    }

    public void setPrimaryKey(int pk) {
        setCustomerGtsForecastSid(pk);
    }

    public String getPrice() {
        return _price;
    }

    public void setPrice(String price) {
        _price = price;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public String getForecastYear() {
        return _forecastYear;
    }

    public void setForecastYear(String forecastYear) {
        _forecastYear = forecastYear;
    }

    public String getDeductionAmount() {
        return _deductionAmount;
    }

    public void setDeductionAmount(String deductionAmount) {
        _deductionAmount = deductionAmount;
    }

    public String getDeductionId() {
        return _deductionId;
    }

    public void setDeductionId(String deductionId) {
        _deductionId = deductionId;
    }

    public Date getForecastDate() {
        return _forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        _forecastDate = forecastDate;
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

    public int getBrandMasterSid() {
        return _brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        _brandMasterSid = brandMasterSid;
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

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getSalesAmount() {
        return _salesAmount;
    }

    public void setSalesAmount(String salesAmount) {
        _salesAmount = salesAmount;
    }

    public String getDeductionType() {
        return _deductionType;
    }

    public void setDeductionType(String deductionType) {
        _deductionType = deductionType;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    public String getUnits() {
        return _units;
    }

    public void setUnits(String units) {
        _units = units;
    }

    public String getDeductionRate() {
        return _deductionRate;
    }

    public void setDeductionRate(String deductionRate) {
        _deductionRate = deductionRate;
    }

    public int getCustomerGtsForecastSid() {
        return _customerGtsForecastSid;
    }

    public void setCustomerGtsForecastSid(int customerGtsForecastSid) {
        _customerGtsForecastSid = customerGtsForecastSid;
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

    public String getForecastValueType() {
        return _forecastValueType;
    }

    public void setForecastValueType(String forecastValueType) {
        _forecastValueType = forecastValueType;
    }

    public String getDeductionCategory() {
        return _deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        _deductionCategory = deductionCategory;
    }

    public String getAdjustmentCode() {
        return _adjustmentCode;
    }

    public void setAdjustmentCode(String adjustmentCode) {
        _adjustmentCode = adjustmentCode;
    }

    public String getDeductionProgramType() {
        return _deductionProgramType;
    }

    public void setDeductionProgramType(String deductionProgramType) {
        _deductionProgramType = deductionProgramType;
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

    public String getSalesInclusion() {
        return _salesInclusion;
    }

    public void setSalesInclusion(String salesInclusion) {
        _salesInclusion = salesInclusion;
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

    public String getPriceType() {
        return _priceType;
    }

    public void setPriceType(String priceType) {
        _priceType = priceType;
    }

    public String getForecastMonth() {
        return _forecastMonth;
    }

    public void setForecastMonth(String forecastMonth) {
        _forecastMonth = forecastMonth;
    }

    public String getDeductionInclusion() {
        return _deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        _deductionInclusion = deductionInclusion;
    }

    public String getSegment() {
        return _segment;
    }

    public void setSegment(String segment) {
        _segment = segment;
    }

    public String getBrand() {
        return _brand;
    }

    public void setBrand(String brand) {
        _brand = brand;
    }

    public String getForecastName() {
        return _forecastName;
    }

    public void setForecastName(String forecastName) {
        _forecastName = forecastName;
    }
}
