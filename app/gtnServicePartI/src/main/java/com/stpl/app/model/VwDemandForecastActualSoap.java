package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class VwDemandForecastActualSoap implements Serializable {
    private String _forecastYear;
    private double _grossUnits;
    private String _businessUnitNo;
    private double _totalDemandUnits;
    private String _brandName;
    private String _itemId;
    private String _organizationKey;
    private String _source;
    private String _marketShareRatio;
    private String _businessUnitName;
    private int _demandForecastActualSid;
    private double _marketShareUnits;
    private double _inventoryUnitChange;
    private String _uncapturedUnitsRatio;
    private String _country;
    private String _forecastType;
    private String _brandId;
    private String _isForecast;
    private double _uncapturedUnits;
    private double _grossPrice;
    private String _isActive;
    private double _grossAmount;
    private String _batchId;
    private String _forecastVer;
    private String _itemName;
    private String _forecastMonth;
    private double _netSalesPrice;
    private double _netSalesAmount;
    private String _segment;
    private double _totalDemandAmount;
    private String _forecastName;
    private double _marketSizeUnits;
    private int _demandId;

    public VwDemandForecastActualSoap() {
    }

    public static VwDemandForecastActualSoap toSoapModel(
        VwDemandForecastActual model) {
        VwDemandForecastActualSoap soapModel = new VwDemandForecastActualSoap();

        soapModel.setForecastYear(model.getForecastYear());
        soapModel.setGrossUnits(model.getGrossUnits());
        soapModel.setBusinessUnitNo(model.getBusinessUnitNo());
        soapModel.setTotalDemandUnits(model.getTotalDemandUnits());
        soapModel.setBrandName(model.getBrandName());
        soapModel.setItemId(model.getItemId());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setSource(model.getSource());
        soapModel.setMarketShareRatio(model.getMarketShareRatio());
        soapModel.setBusinessUnitName(model.getBusinessUnitName());
        soapModel.setDemandForecastActualSid(model.getDemandForecastActualSid());
        soapModel.setMarketShareUnits(model.getMarketShareUnits());
        soapModel.setInventoryUnitChange(model.getInventoryUnitChange());
        soapModel.setUncapturedUnitsRatio(model.getUncapturedUnitsRatio());
        soapModel.setCountry(model.getCountry());
        soapModel.setForecastType(model.getForecastType());
        soapModel.setBrandId(model.getBrandId());
        soapModel.setIsForecast(model.getIsForecast());
        soapModel.setUncapturedUnits(model.getUncapturedUnits());
        soapModel.setGrossPrice(model.getGrossPrice());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setGrossAmount(model.getGrossAmount());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setForecastVer(model.getForecastVer());
        soapModel.setItemName(model.getItemName());
        soapModel.setForecastMonth(model.getForecastMonth());
        soapModel.setNetSalesPrice(model.getNetSalesPrice());
        soapModel.setNetSalesAmount(model.getNetSalesAmount());
        soapModel.setSegment(model.getSegment());
        soapModel.setTotalDemandAmount(model.getTotalDemandAmount());
        soapModel.setForecastName(model.getForecastName());
        soapModel.setMarketSizeUnits(model.getMarketSizeUnits());
        soapModel.setDemandId(model.getDemandId());

        return soapModel;
    }

    public static VwDemandForecastActualSoap[] toSoapModels(
        VwDemandForecastActual[] models) {
        VwDemandForecastActualSoap[] soapModels = new VwDemandForecastActualSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static VwDemandForecastActualSoap[][] toSoapModels(
        VwDemandForecastActual[][] models) {
        VwDemandForecastActualSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new VwDemandForecastActualSoap[models.length][models[0].length];
        } else {
            soapModels = new VwDemandForecastActualSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static VwDemandForecastActualSoap[] toSoapModels(
        List<VwDemandForecastActual> models) {
        List<VwDemandForecastActualSoap> soapModels = new ArrayList<VwDemandForecastActualSoap>(models.size());

        for (VwDemandForecastActual model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new VwDemandForecastActualSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _demandForecastActualSid;
    }

    public void setPrimaryKey(int pk) {
        setDemandForecastActualSid(pk);
    }

    public String getForecastYear() {
        return _forecastYear;
    }

    public void setForecastYear(String forecastYear) {
        _forecastYear = forecastYear;
    }

    public double getGrossUnits() {
        return _grossUnits;
    }

    public void setGrossUnits(double grossUnits) {
        _grossUnits = grossUnits;
    }

    public String getBusinessUnitNo() {
        return _businessUnitNo;
    }

    public void setBusinessUnitNo(String businessUnitNo) {
        _businessUnitNo = businessUnitNo;
    }

    public double getTotalDemandUnits() {
        return _totalDemandUnits;
    }

    public void setTotalDemandUnits(double totalDemandUnits) {
        _totalDemandUnits = totalDemandUnits;
    }

    public String getBrandName() {
        return _brandName;
    }

    public void setBrandName(String brandName) {
        _brandName = brandName;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
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

    public String getMarketShareRatio() {
        return _marketShareRatio;
    }

    public void setMarketShareRatio(String marketShareRatio) {
        _marketShareRatio = marketShareRatio;
    }

    public String getBusinessUnitName() {
        return _businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        _businessUnitName = businessUnitName;
    }

    public int getDemandForecastActualSid() {
        return _demandForecastActualSid;
    }

    public void setDemandForecastActualSid(int demandForecastActualSid) {
        _demandForecastActualSid = demandForecastActualSid;
    }

    public double getMarketShareUnits() {
        return _marketShareUnits;
    }

    public void setMarketShareUnits(double marketShareUnits) {
        _marketShareUnits = marketShareUnits;
    }

    public double getInventoryUnitChange() {
        return _inventoryUnitChange;
    }

    public void setInventoryUnitChange(double inventoryUnitChange) {
        _inventoryUnitChange = inventoryUnitChange;
    }

    public String getUncapturedUnitsRatio() {
        return _uncapturedUnitsRatio;
    }

    public void setUncapturedUnitsRatio(String uncapturedUnitsRatio) {
        _uncapturedUnitsRatio = uncapturedUnitsRatio;
    }

    public String getCountry() {
        return _country;
    }

    public void setCountry(String country) {
        _country = country;
    }

    public String getForecastType() {
        return _forecastType;
    }

    public void setForecastType(String forecastType) {
        _forecastType = forecastType;
    }

    public String getBrandId() {
        return _brandId;
    }

    public void setBrandId(String brandId) {
        _brandId = brandId;
    }

    public String getIsForecast() {
        return _isForecast;
    }

    public void setIsForecast(String isForecast) {
        _isForecast = isForecast;
    }

    public double getUncapturedUnits() {
        return _uncapturedUnits;
    }

    public void setUncapturedUnits(double uncapturedUnits) {
        _uncapturedUnits = uncapturedUnits;
    }

    public double getGrossPrice() {
        return _grossPrice;
    }

    public void setGrossPrice(double grossPrice) {
        _grossPrice = grossPrice;
    }

    public String getIsActive() {
        return _isActive;
    }

    public void setIsActive(String isActive) {
        _isActive = isActive;
    }

    public double getGrossAmount() {
        return _grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        _grossAmount = grossAmount;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getForecastVer() {
        return _forecastVer;
    }

    public void setForecastVer(String forecastVer) {
        _forecastVer = forecastVer;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    public String getForecastMonth() {
        return _forecastMonth;
    }

    public void setForecastMonth(String forecastMonth) {
        _forecastMonth = forecastMonth;
    }

    public double getNetSalesPrice() {
        return _netSalesPrice;
    }

    public void setNetSalesPrice(double netSalesPrice) {
        _netSalesPrice = netSalesPrice;
    }

    public double getNetSalesAmount() {
        return _netSalesAmount;
    }

    public void setNetSalesAmount(double netSalesAmount) {
        _netSalesAmount = netSalesAmount;
    }

    public String getSegment() {
        return _segment;
    }

    public void setSegment(String segment) {
        _segment = segment;
    }

    public double getTotalDemandAmount() {
        return _totalDemandAmount;
    }

    public void setTotalDemandAmount(double totalDemandAmount) {
        _totalDemandAmount = totalDemandAmount;
    }

    public String getForecastName() {
        return _forecastName;
    }

    public void setForecastName(String forecastName) {
        _forecastName = forecastName;
    }

    public double getMarketSizeUnits() {
        return _marketSizeUnits;
    }

    public void setMarketSizeUnits(double marketSizeUnits) {
        _marketSizeUnits = marketSizeUnits;
    }

    public int getDemandId() {
        return _demandId;
    }

    public void setDemandId(int demandId) {
        _demandId = demandId;
    }
}
