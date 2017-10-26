package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwIvldDemandForecastActual}.
 * </p>
 *
 * @author
 * @see VwIvldDemandForecastActual
 * @generated
 */
public class VwIvldDemandForecastActualWrapper
    implements VwIvldDemandForecastActual,
        ModelWrapper<VwIvldDemandForecastActual> {
    private VwIvldDemandForecastActual _vwIvldDemandForecastActual;

    public VwIvldDemandForecastActualWrapper(
        VwIvldDemandForecastActual vwIvldDemandForecastActual) {
        _vwIvldDemandForecastActual = vwIvldDemandForecastActual;
    }

    @Override
    public Class<?> getModelClass() {
        return VwIvldDemandForecastActual.class;
    }

    @Override
    public String getModelClassName() {
        return VwIvldDemandForecastActual.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("demandIntSid", getDemandIntSid());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("totalDemandUnits", getTotalDemandUnits());
        attributes.put("brandName", getBrandName());
        attributes.put("itemId", getItemId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("ivldDemandActualForecastSid",
            getIvldDemandActualForecastSid());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("forecastType", getForecastType());
        attributes.put("brandId", getBrandId());
        attributes.put("isForecast", getIsForecast());
        attributes.put("uncapturedUnits", getUncapturedUnits());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("isActive", getIsActive());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("batchId", getBatchId());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("itemName", getItemName());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("errorField", getErrorField());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("totalDemandAmount", getTotalDemandAmount());
        attributes.put("forecastName", getForecastName());
        attributes.put("marketSizeUnits", getMarketSizeUnits());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String demandIntSid = (String) attributes.get("demandIntSid");

        if (demandIntSid != null) {
            setDemandIntSid(demandIntSid);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String grossUnits = (String) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String totalDemandUnits = (String) attributes.get("totalDemandUnits");

        if (totalDemandUnits != null) {
            setTotalDemandUnits(totalDemandUnits);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String marketShareRatio = (String) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
        }

        Integer ivldDemandActualForecastSid = (Integer) attributes.get(
                "ivldDemandActualForecastSid");

        if (ivldDemandActualForecastSid != null) {
            setIvldDemandActualForecastSid(ivldDemandActualForecastSid);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String marketShareUnits = (String) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
        }

        String inventoryUnitChange = (String) attributes.get(
                "inventoryUnitChange");

        if (inventoryUnitChange != null) {
            setInventoryUnitChange(inventoryUnitChange);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String uncapturedUnitsRatio = (String) attributes.get(
                "uncapturedUnitsRatio");

        if (uncapturedUnitsRatio != null) {
            setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String isForecast = (String) attributes.get("isForecast");

        if (isForecast != null) {
            setIsForecast(isForecast);
        }

        String uncapturedUnits = (String) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        String grossPrice = (String) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String grossAmount = (String) attributes.get("grossAmount");

        if (grossAmount != null) {
            setGrossAmount(grossAmount);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String netSalesPrice = (String) attributes.get("netSalesPrice");

        if (netSalesPrice != null) {
            setNetSalesPrice(netSalesPrice);
        }

        String netSalesAmount = (String) attributes.get("netSalesAmount");

        if (netSalesAmount != null) {
            setNetSalesAmount(netSalesAmount);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        String totalDemandAmount = (String) attributes.get("totalDemandAmount");

        if (totalDemandAmount != null) {
            setTotalDemandAmount(totalDemandAmount);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        String marketSizeUnits = (String) attributes.get("marketSizeUnits");

        if (marketSizeUnits != null) {
            setMarketSizeUnits(marketSizeUnits);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this vw ivld demand forecast actual.
    *
    * @return the primary key of this vw ivld demand forecast actual
    */
    @Override
    public int getPrimaryKey() {
        return _vwIvldDemandForecastActual.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw ivld demand forecast actual.
    *
    * @param primaryKey the primary key of this vw ivld demand forecast actual
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwIvldDemandForecastActual.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the demand int sid of this vw ivld demand forecast actual.
    *
    * @return the demand int sid of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getDemandIntSid() {
        return _vwIvldDemandForecastActual.getDemandIntSid();
    }

    /**
    * Sets the demand int sid of this vw ivld demand forecast actual.
    *
    * @param demandIntSid the demand int sid of this vw ivld demand forecast actual
    */
    @Override
    public void setDemandIntSid(java.lang.String demandIntSid) {
        _vwIvldDemandForecastActual.setDemandIntSid(demandIntSid);
    }

    /**
    * Returns the forecast year of this vw ivld demand forecast actual.
    *
    * @return the forecast year of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getForecastYear() {
        return _vwIvldDemandForecastActual.getForecastYear();
    }

    /**
    * Sets the forecast year of this vw ivld demand forecast actual.
    *
    * @param forecastYear the forecast year of this vw ivld demand forecast actual
    */
    @Override
    public void setForecastYear(java.lang.String forecastYear) {
        _vwIvldDemandForecastActual.setForecastYear(forecastYear);
    }

    /**
    * Returns the gross units of this vw ivld demand forecast actual.
    *
    * @return the gross units of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getGrossUnits() {
        return _vwIvldDemandForecastActual.getGrossUnits();
    }

    /**
    * Sets the gross units of this vw ivld demand forecast actual.
    *
    * @param grossUnits the gross units of this vw ivld demand forecast actual
    */
    @Override
    public void setGrossUnits(java.lang.String grossUnits) {
        _vwIvldDemandForecastActual.setGrossUnits(grossUnits);
    }

    /**
    * Returns the business unit no of this vw ivld demand forecast actual.
    *
    * @return the business unit no of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getBusinessUnitNo() {
        return _vwIvldDemandForecastActual.getBusinessUnitNo();
    }

    /**
    * Sets the business unit no of this vw ivld demand forecast actual.
    *
    * @param businessUnitNo the business unit no of this vw ivld demand forecast actual
    */
    @Override
    public void setBusinessUnitNo(java.lang.String businessUnitNo) {
        _vwIvldDemandForecastActual.setBusinessUnitNo(businessUnitNo);
    }

    /**
    * Returns the total demand units of this vw ivld demand forecast actual.
    *
    * @return the total demand units of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getTotalDemandUnits() {
        return _vwIvldDemandForecastActual.getTotalDemandUnits();
    }

    /**
    * Sets the total demand units of this vw ivld demand forecast actual.
    *
    * @param totalDemandUnits the total demand units of this vw ivld demand forecast actual
    */
    @Override
    public void setTotalDemandUnits(java.lang.String totalDemandUnits) {
        _vwIvldDemandForecastActual.setTotalDemandUnits(totalDemandUnits);
    }

    /**
    * Returns the brand name of this vw ivld demand forecast actual.
    *
    * @return the brand name of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getBrandName() {
        return _vwIvldDemandForecastActual.getBrandName();
    }

    /**
    * Sets the brand name of this vw ivld demand forecast actual.
    *
    * @param brandName the brand name of this vw ivld demand forecast actual
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _vwIvldDemandForecastActual.setBrandName(brandName);
    }

    /**
    * Returns the item ID of this vw ivld demand forecast actual.
    *
    * @return the item ID of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getItemId() {
        return _vwIvldDemandForecastActual.getItemId();
    }

    /**
    * Sets the item ID of this vw ivld demand forecast actual.
    *
    * @param itemId the item ID of this vw ivld demand forecast actual
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwIvldDemandForecastActual.setItemId(itemId);
    }

    /**
    * Returns the organization key of this vw ivld demand forecast actual.
    *
    * @return the organization key of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _vwIvldDemandForecastActual.getOrganizationKey();
    }

    /**
    * Sets the organization key of this vw ivld demand forecast actual.
    *
    * @param organizationKey the organization key of this vw ivld demand forecast actual
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _vwIvldDemandForecastActual.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the source of this vw ivld demand forecast actual.
    *
    * @return the source of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getSource() {
        return _vwIvldDemandForecastActual.getSource();
    }

    /**
    * Sets the source of this vw ivld demand forecast actual.
    *
    * @param source the source of this vw ivld demand forecast actual
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwIvldDemandForecastActual.setSource(source);
    }

    /**
    * Returns the market share ratio of this vw ivld demand forecast actual.
    *
    * @return the market share ratio of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getMarketShareRatio() {
        return _vwIvldDemandForecastActual.getMarketShareRatio();
    }

    /**
    * Sets the market share ratio of this vw ivld demand forecast actual.
    *
    * @param marketShareRatio the market share ratio of this vw ivld demand forecast actual
    */
    @Override
    public void setMarketShareRatio(java.lang.String marketShareRatio) {
        _vwIvldDemandForecastActual.setMarketShareRatio(marketShareRatio);
    }

    /**
    * Returns the ivld demand actual forecast sid of this vw ivld demand forecast actual.
    *
    * @return the ivld demand actual forecast sid of this vw ivld demand forecast actual
    */
    @Override
    public int getIvldDemandActualForecastSid() {
        return _vwIvldDemandForecastActual.getIvldDemandActualForecastSid();
    }

    /**
    * Sets the ivld demand actual forecast sid of this vw ivld demand forecast actual.
    *
    * @param ivldDemandActualForecastSid the ivld demand actual forecast sid of this vw ivld demand forecast actual
    */
    @Override
    public void setIvldDemandActualForecastSid(int ivldDemandActualForecastSid) {
        _vwIvldDemandForecastActual.setIvldDemandActualForecastSid(ivldDemandActualForecastSid);
    }

    /**
    * Returns the business unit name of this vw ivld demand forecast actual.
    *
    * @return the business unit name of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getBusinessUnitName() {
        return _vwIvldDemandForecastActual.getBusinessUnitName();
    }

    /**
    * Sets the business unit name of this vw ivld demand forecast actual.
    *
    * @param businessUnitName the business unit name of this vw ivld demand forecast actual
    */
    @Override
    public void setBusinessUnitName(java.lang.String businessUnitName) {
        _vwIvldDemandForecastActual.setBusinessUnitName(businessUnitName);
    }

    /**
    * Returns the add chg del indicator of this vw ivld demand forecast actual.
    *
    * @return the add chg del indicator of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwIvldDemandForecastActual.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw ivld demand forecast actual.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw ivld demand forecast actual
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwIvldDemandForecastActual.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the error code of this vw ivld demand forecast actual.
    *
    * @return the error code of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getErrorCode() {
        return _vwIvldDemandForecastActual.getErrorCode();
    }

    /**
    * Sets the error code of this vw ivld demand forecast actual.
    *
    * @param errorCode the error code of this vw ivld demand forecast actual
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _vwIvldDemandForecastActual.setErrorCode(errorCode);
    }

    /**
    * Returns the market share units of this vw ivld demand forecast actual.
    *
    * @return the market share units of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getMarketShareUnits() {
        return _vwIvldDemandForecastActual.getMarketShareUnits();
    }

    /**
    * Sets the market share units of this vw ivld demand forecast actual.
    *
    * @param marketShareUnits the market share units of this vw ivld demand forecast actual
    */
    @Override
    public void setMarketShareUnits(java.lang.String marketShareUnits) {
        _vwIvldDemandForecastActual.setMarketShareUnits(marketShareUnits);
    }

    /**
    * Returns the inventory unit change of this vw ivld demand forecast actual.
    *
    * @return the inventory unit change of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getInventoryUnitChange() {
        return _vwIvldDemandForecastActual.getInventoryUnitChange();
    }

    /**
    * Sets the inventory unit change of this vw ivld demand forecast actual.
    *
    * @param inventoryUnitChange the inventory unit change of this vw ivld demand forecast actual
    */
    @Override
    public void setInventoryUnitChange(java.lang.String inventoryUnitChange) {
        _vwIvldDemandForecastActual.setInventoryUnitChange(inventoryUnitChange);
    }

    /**
    * Returns the reprocessed flag of this vw ivld demand forecast actual.
    *
    * @return the reprocessed flag of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _vwIvldDemandForecastActual.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this vw ivld demand forecast actual.
    *
    * @param reprocessedFlag the reprocessed flag of this vw ivld demand forecast actual
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _vwIvldDemandForecastActual.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the uncaptured units ratio of this vw ivld demand forecast actual.
    *
    * @return the uncaptured units ratio of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getUncapturedUnitsRatio() {
        return _vwIvldDemandForecastActual.getUncapturedUnitsRatio();
    }

    /**
    * Sets the uncaptured units ratio of this vw ivld demand forecast actual.
    *
    * @param uncapturedUnitsRatio the uncaptured units ratio of this vw ivld demand forecast actual
    */
    @Override
    public void setUncapturedUnitsRatio(java.lang.String uncapturedUnitsRatio) {
        _vwIvldDemandForecastActual.setUncapturedUnitsRatio(uncapturedUnitsRatio);
    }

    /**
    * Returns the reason for failure of this vw ivld demand forecast actual.
    *
    * @return the reason for failure of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _vwIvldDemandForecastActual.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this vw ivld demand forecast actual.
    *
    * @param reasonForFailure the reason for failure of this vw ivld demand forecast actual
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _vwIvldDemandForecastActual.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the country of this vw ivld demand forecast actual.
    *
    * @return the country of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getCountry() {
        return _vwIvldDemandForecastActual.getCountry();
    }

    /**
    * Sets the country of this vw ivld demand forecast actual.
    *
    * @param country the country of this vw ivld demand forecast actual
    */
    @Override
    public void setCountry(java.lang.String country) {
        _vwIvldDemandForecastActual.setCountry(country);
    }

    /**
    * Returns the forecast type of this vw ivld demand forecast actual.
    *
    * @return the forecast type of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getForecastType() {
        return _vwIvldDemandForecastActual.getForecastType();
    }

    /**
    * Sets the forecast type of this vw ivld demand forecast actual.
    *
    * @param forecastType the forecast type of this vw ivld demand forecast actual
    */
    @Override
    public void setForecastType(java.lang.String forecastType) {
        _vwIvldDemandForecastActual.setForecastType(forecastType);
    }

    /**
    * Returns the brand ID of this vw ivld demand forecast actual.
    *
    * @return the brand ID of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getBrandId() {
        return _vwIvldDemandForecastActual.getBrandId();
    }

    /**
    * Sets the brand ID of this vw ivld demand forecast actual.
    *
    * @param brandId the brand ID of this vw ivld demand forecast actual
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _vwIvldDemandForecastActual.setBrandId(brandId);
    }

    /**
    * Returns the is forecast of this vw ivld demand forecast actual.
    *
    * @return the is forecast of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getIsForecast() {
        return _vwIvldDemandForecastActual.getIsForecast();
    }

    /**
    * Sets the is forecast of this vw ivld demand forecast actual.
    *
    * @param isForecast the is forecast of this vw ivld demand forecast actual
    */
    @Override
    public void setIsForecast(java.lang.String isForecast) {
        _vwIvldDemandForecastActual.setIsForecast(isForecast);
    }

    /**
    * Returns the uncaptured units of this vw ivld demand forecast actual.
    *
    * @return the uncaptured units of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getUncapturedUnits() {
        return _vwIvldDemandForecastActual.getUncapturedUnits();
    }

    /**
    * Sets the uncaptured units of this vw ivld demand forecast actual.
    *
    * @param uncapturedUnits the uncaptured units of this vw ivld demand forecast actual
    */
    @Override
    public void setUncapturedUnits(java.lang.String uncapturedUnits) {
        _vwIvldDemandForecastActual.setUncapturedUnits(uncapturedUnits);
    }

    /**
    * Returns the gross price of this vw ivld demand forecast actual.
    *
    * @return the gross price of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getGrossPrice() {
        return _vwIvldDemandForecastActual.getGrossPrice();
    }

    /**
    * Sets the gross price of this vw ivld demand forecast actual.
    *
    * @param grossPrice the gross price of this vw ivld demand forecast actual
    */
    @Override
    public void setGrossPrice(java.lang.String grossPrice) {
        _vwIvldDemandForecastActual.setGrossPrice(grossPrice);
    }

    /**
    * Returns the is active of this vw ivld demand forecast actual.
    *
    * @return the is active of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getIsActive() {
        return _vwIvldDemandForecastActual.getIsActive();
    }

    /**
    * Sets the is active of this vw ivld demand forecast actual.
    *
    * @param isActive the is active of this vw ivld demand forecast actual
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _vwIvldDemandForecastActual.setIsActive(isActive);
    }

    /**
    * Returns the gross amount of this vw ivld demand forecast actual.
    *
    * @return the gross amount of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getGrossAmount() {
        return _vwIvldDemandForecastActual.getGrossAmount();
    }

    /**
    * Sets the gross amount of this vw ivld demand forecast actual.
    *
    * @param grossAmount the gross amount of this vw ivld demand forecast actual
    */
    @Override
    public void setGrossAmount(java.lang.String grossAmount) {
        _vwIvldDemandForecastActual.setGrossAmount(grossAmount);
    }

    /**
    * Returns the batch ID of this vw ivld demand forecast actual.
    *
    * @return the batch ID of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwIvldDemandForecastActual.getBatchId();
    }

    /**
    * Sets the batch ID of this vw ivld demand forecast actual.
    *
    * @param batchId the batch ID of this vw ivld demand forecast actual
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwIvldDemandForecastActual.setBatchId(batchId);
    }

    /**
    * Returns the forecast ver of this vw ivld demand forecast actual.
    *
    * @return the forecast ver of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getForecastVer() {
        return _vwIvldDemandForecastActual.getForecastVer();
    }

    /**
    * Sets the forecast ver of this vw ivld demand forecast actual.
    *
    * @param forecastVer the forecast ver of this vw ivld demand forecast actual
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _vwIvldDemandForecastActual.setForecastVer(forecastVer);
    }

    /**
    * Returns the item name of this vw ivld demand forecast actual.
    *
    * @return the item name of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getItemName() {
        return _vwIvldDemandForecastActual.getItemName();
    }

    /**
    * Sets the item name of this vw ivld demand forecast actual.
    *
    * @param itemName the item name of this vw ivld demand forecast actual
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwIvldDemandForecastActual.setItemName(itemName);
    }

    /**
    * Returns the forecast month of this vw ivld demand forecast actual.
    *
    * @return the forecast month of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getForecastMonth() {
        return _vwIvldDemandForecastActual.getForecastMonth();
    }

    /**
    * Sets the forecast month of this vw ivld demand forecast actual.
    *
    * @param forecastMonth the forecast month of this vw ivld demand forecast actual
    */
    @Override
    public void setForecastMonth(java.lang.String forecastMonth) {
        _vwIvldDemandForecastActual.setForecastMonth(forecastMonth);
    }

    /**
    * Returns the error field of this vw ivld demand forecast actual.
    *
    * @return the error field of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getErrorField() {
        return _vwIvldDemandForecastActual.getErrorField();
    }

    /**
    * Sets the error field of this vw ivld demand forecast actual.
    *
    * @param errorField the error field of this vw ivld demand forecast actual
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _vwIvldDemandForecastActual.setErrorField(errorField);
    }

    /**
    * Returns the net sales price of this vw ivld demand forecast actual.
    *
    * @return the net sales price of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getNetSalesPrice() {
        return _vwIvldDemandForecastActual.getNetSalesPrice();
    }

    /**
    * Sets the net sales price of this vw ivld demand forecast actual.
    *
    * @param netSalesPrice the net sales price of this vw ivld demand forecast actual
    */
    @Override
    public void setNetSalesPrice(java.lang.String netSalesPrice) {
        _vwIvldDemandForecastActual.setNetSalesPrice(netSalesPrice);
    }

    /**
    * Returns the net sales amount of this vw ivld demand forecast actual.
    *
    * @return the net sales amount of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getNetSalesAmount() {
        return _vwIvldDemandForecastActual.getNetSalesAmount();
    }

    /**
    * Sets the net sales amount of this vw ivld demand forecast actual.
    *
    * @param netSalesAmount the net sales amount of this vw ivld demand forecast actual
    */
    @Override
    public void setNetSalesAmount(java.lang.String netSalesAmount) {
        _vwIvldDemandForecastActual.setNetSalesAmount(netSalesAmount);
    }

    /**
    * Returns the segment of this vw ivld demand forecast actual.
    *
    * @return the segment of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getSegment() {
        return _vwIvldDemandForecastActual.getSegment();
    }

    /**
    * Sets the segment of this vw ivld demand forecast actual.
    *
    * @param segment the segment of this vw ivld demand forecast actual
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _vwIvldDemandForecastActual.setSegment(segment);
    }

    /**
    * Returns the total demand amount of this vw ivld demand forecast actual.
    *
    * @return the total demand amount of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getTotalDemandAmount() {
        return _vwIvldDemandForecastActual.getTotalDemandAmount();
    }

    /**
    * Sets the total demand amount of this vw ivld demand forecast actual.
    *
    * @param totalDemandAmount the total demand amount of this vw ivld demand forecast actual
    */
    @Override
    public void setTotalDemandAmount(java.lang.String totalDemandAmount) {
        _vwIvldDemandForecastActual.setTotalDemandAmount(totalDemandAmount);
    }

    /**
    * Returns the forecast name of this vw ivld demand forecast actual.
    *
    * @return the forecast name of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getForecastName() {
        return _vwIvldDemandForecastActual.getForecastName();
    }

    /**
    * Sets the forecast name of this vw ivld demand forecast actual.
    *
    * @param forecastName the forecast name of this vw ivld demand forecast actual
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _vwIvldDemandForecastActual.setForecastName(forecastName);
    }

    /**
    * Returns the market size units of this vw ivld demand forecast actual.
    *
    * @return the market size units of this vw ivld demand forecast actual
    */
    @Override
    public java.lang.String getMarketSizeUnits() {
        return _vwIvldDemandForecastActual.getMarketSizeUnits();
    }

    /**
    * Sets the market size units of this vw ivld demand forecast actual.
    *
    * @param marketSizeUnits the market size units of this vw ivld demand forecast actual
    */
    @Override
    public void setMarketSizeUnits(java.lang.String marketSizeUnits) {
        _vwIvldDemandForecastActual.setMarketSizeUnits(marketSizeUnits);
    }

    /**
    * Returns the check record of this vw ivld demand forecast actual.
    *
    * @return the check record of this vw ivld demand forecast actual
    */
    @Override
    public boolean getCheckRecord() {
        return _vwIvldDemandForecastActual.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this vw ivld demand forecast actual is check record.
    *
    * @return <code>true</code> if this vw ivld demand forecast actual is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _vwIvldDemandForecastActual.isCheckRecord();
    }

    /**
    * Sets whether this vw ivld demand forecast actual is check record.
    *
    * @param checkRecord the check record of this vw ivld demand forecast actual
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _vwIvldDemandForecastActual.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _vwIvldDemandForecastActual.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwIvldDemandForecastActual.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwIvldDemandForecastActual.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwIvldDemandForecastActual.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwIvldDemandForecastActual.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwIvldDemandForecastActual.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwIvldDemandForecastActual.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwIvldDemandForecastActual.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwIvldDemandForecastActual.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwIvldDemandForecastActual.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwIvldDemandForecastActual.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwIvldDemandForecastActualWrapper((VwIvldDemandForecastActual) _vwIvldDemandForecastActual.clone());
    }

    @Override
    public int compareTo(VwIvldDemandForecastActual vwIvldDemandForecastActual) {
        return _vwIvldDemandForecastActual.compareTo(vwIvldDemandForecastActual);
    }

    @Override
    public int hashCode() {
        return _vwIvldDemandForecastActual.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwIvldDemandForecastActual> toCacheModel() {
        return _vwIvldDemandForecastActual.toCacheModel();
    }

    @Override
    public VwIvldDemandForecastActual toEscapedModel() {
        return new VwIvldDemandForecastActualWrapper(_vwIvldDemandForecastActual.toEscapedModel());
    }

    @Override
    public VwIvldDemandForecastActual toUnescapedModel() {
        return new VwIvldDemandForecastActualWrapper(_vwIvldDemandForecastActual.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwIvldDemandForecastActual.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwIvldDemandForecastActual.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwIvldDemandForecastActual.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwIvldDemandForecastActualWrapper)) {
            return false;
        }

        VwIvldDemandForecastActualWrapper vwIvldDemandForecastActualWrapper = (VwIvldDemandForecastActualWrapper) obj;

        if (Validator.equals(_vwIvldDemandForecastActual,
                    vwIvldDemandForecastActualWrapper._vwIvldDemandForecastActual)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwIvldDemandForecastActual getWrappedVwIvldDemandForecastActual() {
        return _vwIvldDemandForecastActual;
    }

    @Override
    public VwIvldDemandForecastActual getWrappedModel() {
        return _vwIvldDemandForecastActual;
    }

    @Override
    public void resetOriginalValues() {
        _vwIvldDemandForecastActual.resetOriginalValues();
    }
}
