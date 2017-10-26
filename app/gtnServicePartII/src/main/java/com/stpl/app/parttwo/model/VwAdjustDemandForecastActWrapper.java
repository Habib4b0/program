package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwAdjustDemandForecastAct}.
 * </p>
 *
 * @author
 * @see VwAdjustDemandForecastAct
 * @generated
 */
public class VwAdjustDemandForecastActWrapper
    implements VwAdjustDemandForecastAct,
        ModelWrapper<VwAdjustDemandForecastAct> {
    private VwAdjustDemandForecastAct _vwAdjustDemandForecastAct;

    public VwAdjustDemandForecastActWrapper(
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
        _vwAdjustDemandForecastAct = vwAdjustDemandForecastAct;
    }

    @Override
    public Class<?> getModelClass() {
        return VwAdjustDemandForecastAct.class;
    }

    @Override
    public String getModelClassName() {
        return VwAdjustDemandForecastAct.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastVersion", getForecastVersion());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("year", getYear());
        attributes.put("brandName", getBrandName());
        attributes.put("itemId", getItemId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("month", getMonth());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("country", getCountry());
        attributes.put("forecastType", getForecastType());
        attributes.put("totalAdjustedDemandUnits", getTotalAdjustedDemandUnits());
        attributes.put("brandId", getBrandId());
        attributes.put("isForecast", getIsForecast());
        attributes.put("totalAdjustedDemandAmount",
            getTotalAdjustedDemandAmount());
        attributes.put("uncapturedUnits", getUncapturedUnits());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("batchId", getBatchId());
        attributes.put("adjustedDemandForecastId", getAdjustedDemandForecastId());
        attributes.put("itemName", getItemName());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("forecastName", getForecastName());
        attributes.put("marketSizeUnits", getMarketSizeUnits());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String forecastVersion = (String) attributes.get("forecastVersion");

        if (forecastVersion != null) {
            setForecastVersion(forecastVersion);
        }

        Double grossUnits = (Double) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
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

        Integer marketShareRatio = (Integer) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        Double marketShareUnits = (Double) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        Double inventoryUnitChange = (Double) attributes.get(
                "inventoryUnitChange");

        if (inventoryUnitChange != null) {
            setInventoryUnitChange(inventoryUnitChange);
        }

        String uncapturedUnitsRatio = (String) attributes.get(
                "uncapturedUnitsRatio");

        if (uncapturedUnitsRatio != null) {
            setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        Double totalAdjustedDemandUnits = (Double) attributes.get(
                "totalAdjustedDemandUnits");

        if (totalAdjustedDemandUnits != null) {
            setTotalAdjustedDemandUnits(totalAdjustedDemandUnits);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String isForecast = (String) attributes.get("isForecast");

        if (isForecast != null) {
            setIsForecast(isForecast);
        }

        Double totalAdjustedDemandAmount = (Double) attributes.get(
                "totalAdjustedDemandAmount");

        if (totalAdjustedDemandAmount != null) {
            setTotalAdjustedDemandAmount(totalAdjustedDemandAmount);
        }

        Double uncapturedUnits = (Double) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        Double grossPrice = (Double) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        Double grossAmount = (Double) attributes.get("grossAmount");

        if (grossAmount != null) {
            setGrossAmount(grossAmount);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer adjustedDemandForecastId = (Integer) attributes.get(
                "adjustedDemandForecastId");

        if (adjustedDemandForecastId != null) {
            setAdjustedDemandForecastId(adjustedDemandForecastId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        Double netSalesPrice = (Double) attributes.get("netSalesPrice");

        if (netSalesPrice != null) {
            setNetSalesPrice(netSalesPrice);
        }

        Double netSalesAmount = (Double) attributes.get("netSalesAmount");

        if (netSalesAmount != null) {
            setNetSalesAmount(netSalesAmount);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Double marketSizeUnits = (Double) attributes.get("marketSizeUnits");

        if (marketSizeUnits != null) {
            setMarketSizeUnits(marketSizeUnits);
        }
    }

    /**
    * Returns the primary key of this vw adjust demand forecast act.
    *
    * @return the primary key of this vw adjust demand forecast act
    */
    @Override
    public int getPrimaryKey() {
        return _vwAdjustDemandForecastAct.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw adjust demand forecast act.
    *
    * @param primaryKey the primary key of this vw adjust demand forecast act
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwAdjustDemandForecastAct.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the forecast version of this vw adjust demand forecast act.
    *
    * @return the forecast version of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getForecastVersion() {
        return _vwAdjustDemandForecastAct.getForecastVersion();
    }

    /**
    * Sets the forecast version of this vw adjust demand forecast act.
    *
    * @param forecastVersion the forecast version of this vw adjust demand forecast act
    */
    @Override
    public void setForecastVersion(java.lang.String forecastVersion) {
        _vwAdjustDemandForecastAct.setForecastVersion(forecastVersion);
    }

    /**
    * Returns the gross units of this vw adjust demand forecast act.
    *
    * @return the gross units of this vw adjust demand forecast act
    */
    @Override
    public double getGrossUnits() {
        return _vwAdjustDemandForecastAct.getGrossUnits();
    }

    /**
    * Sets the gross units of this vw adjust demand forecast act.
    *
    * @param grossUnits the gross units of this vw adjust demand forecast act
    */
    @Override
    public void setGrossUnits(double grossUnits) {
        _vwAdjustDemandForecastAct.setGrossUnits(grossUnits);
    }

    /**
    * Returns the business unit no of this vw adjust demand forecast act.
    *
    * @return the business unit no of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getBusinessUnitNo() {
        return _vwAdjustDemandForecastAct.getBusinessUnitNo();
    }

    /**
    * Sets the business unit no of this vw adjust demand forecast act.
    *
    * @param businessUnitNo the business unit no of this vw adjust demand forecast act
    */
    @Override
    public void setBusinessUnitNo(java.lang.String businessUnitNo) {
        _vwAdjustDemandForecastAct.setBusinessUnitNo(businessUnitNo);
    }

    /**
    * Returns the year of this vw adjust demand forecast act.
    *
    * @return the year of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getYear() {
        return _vwAdjustDemandForecastAct.getYear();
    }

    /**
    * Sets the year of this vw adjust demand forecast act.
    *
    * @param year the year of this vw adjust demand forecast act
    */
    @Override
    public void setYear(java.lang.String year) {
        _vwAdjustDemandForecastAct.setYear(year);
    }

    /**
    * Returns the brand name of this vw adjust demand forecast act.
    *
    * @return the brand name of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getBrandName() {
        return _vwAdjustDemandForecastAct.getBrandName();
    }

    /**
    * Sets the brand name of this vw adjust demand forecast act.
    *
    * @param brandName the brand name of this vw adjust demand forecast act
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _vwAdjustDemandForecastAct.setBrandName(brandName);
    }

    /**
    * Returns the item ID of this vw adjust demand forecast act.
    *
    * @return the item ID of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getItemId() {
        return _vwAdjustDemandForecastAct.getItemId();
    }

    /**
    * Sets the item ID of this vw adjust demand forecast act.
    *
    * @param itemId the item ID of this vw adjust demand forecast act
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwAdjustDemandForecastAct.setItemId(itemId);
    }

    /**
    * Returns the organization key of this vw adjust demand forecast act.
    *
    * @return the organization key of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _vwAdjustDemandForecastAct.getOrganizationKey();
    }

    /**
    * Sets the organization key of this vw adjust demand forecast act.
    *
    * @param organizationKey the organization key of this vw adjust demand forecast act
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _vwAdjustDemandForecastAct.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the source of this vw adjust demand forecast act.
    *
    * @return the source of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getSource() {
        return _vwAdjustDemandForecastAct.getSource();
    }

    /**
    * Sets the source of this vw adjust demand forecast act.
    *
    * @param source the source of this vw adjust demand forecast act
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwAdjustDemandForecastAct.setSource(source);
    }

    /**
    * Returns the market share ratio of this vw adjust demand forecast act.
    *
    * @return the market share ratio of this vw adjust demand forecast act
    */
    @Override
    public int getMarketShareRatio() {
        return _vwAdjustDemandForecastAct.getMarketShareRatio();
    }

    /**
    * Sets the market share ratio of this vw adjust demand forecast act.
    *
    * @param marketShareRatio the market share ratio of this vw adjust demand forecast act
    */
    @Override
    public void setMarketShareRatio(int marketShareRatio) {
        _vwAdjustDemandForecastAct.setMarketShareRatio(marketShareRatio);
    }

    /**
    * Returns the business unit name of this vw adjust demand forecast act.
    *
    * @return the business unit name of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getBusinessUnitName() {
        return _vwAdjustDemandForecastAct.getBusinessUnitName();
    }

    /**
    * Sets the business unit name of this vw adjust demand forecast act.
    *
    * @param businessUnitName the business unit name of this vw adjust demand forecast act
    */
    @Override
    public void setBusinessUnitName(java.lang.String businessUnitName) {
        _vwAdjustDemandForecastAct.setBusinessUnitName(businessUnitName);
    }

    /**
    * Returns the market share units of this vw adjust demand forecast act.
    *
    * @return the market share units of this vw adjust demand forecast act
    */
    @Override
    public double getMarketShareUnits() {
        return _vwAdjustDemandForecastAct.getMarketShareUnits();
    }

    /**
    * Sets the market share units of this vw adjust demand forecast act.
    *
    * @param marketShareUnits the market share units of this vw adjust demand forecast act
    */
    @Override
    public void setMarketShareUnits(double marketShareUnits) {
        _vwAdjustDemandForecastAct.setMarketShareUnits(marketShareUnits);
    }

    /**
    * Returns the month of this vw adjust demand forecast act.
    *
    * @return the month of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getMonth() {
        return _vwAdjustDemandForecastAct.getMonth();
    }

    /**
    * Sets the month of this vw adjust demand forecast act.
    *
    * @param month the month of this vw adjust demand forecast act
    */
    @Override
    public void setMonth(java.lang.String month) {
        _vwAdjustDemandForecastAct.setMonth(month);
    }

    /**
    * Returns the inventory unit change of this vw adjust demand forecast act.
    *
    * @return the inventory unit change of this vw adjust demand forecast act
    */
    @Override
    public double getInventoryUnitChange() {
        return _vwAdjustDemandForecastAct.getInventoryUnitChange();
    }

    /**
    * Sets the inventory unit change of this vw adjust demand forecast act.
    *
    * @param inventoryUnitChange the inventory unit change of this vw adjust demand forecast act
    */
    @Override
    public void setInventoryUnitChange(double inventoryUnitChange) {
        _vwAdjustDemandForecastAct.setInventoryUnitChange(inventoryUnitChange);
    }

    /**
    * Returns the uncaptured units ratio of this vw adjust demand forecast act.
    *
    * @return the uncaptured units ratio of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getUncapturedUnitsRatio() {
        return _vwAdjustDemandForecastAct.getUncapturedUnitsRatio();
    }

    /**
    * Sets the uncaptured units ratio of this vw adjust demand forecast act.
    *
    * @param uncapturedUnitsRatio the uncaptured units ratio of this vw adjust demand forecast act
    */
    @Override
    public void setUncapturedUnitsRatio(java.lang.String uncapturedUnitsRatio) {
        _vwAdjustDemandForecastAct.setUncapturedUnitsRatio(uncapturedUnitsRatio);
    }

    /**
    * Returns the country of this vw adjust demand forecast act.
    *
    * @return the country of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getCountry() {
        return _vwAdjustDemandForecastAct.getCountry();
    }

    /**
    * Sets the country of this vw adjust demand forecast act.
    *
    * @param country the country of this vw adjust demand forecast act
    */
    @Override
    public void setCountry(java.lang.String country) {
        _vwAdjustDemandForecastAct.setCountry(country);
    }

    /**
    * Returns the forecast type of this vw adjust demand forecast act.
    *
    * @return the forecast type of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getForecastType() {
        return _vwAdjustDemandForecastAct.getForecastType();
    }

    /**
    * Sets the forecast type of this vw adjust demand forecast act.
    *
    * @param forecastType the forecast type of this vw adjust demand forecast act
    */
    @Override
    public void setForecastType(java.lang.String forecastType) {
        _vwAdjustDemandForecastAct.setForecastType(forecastType);
    }

    /**
    * Returns the total adjusted demand units of this vw adjust demand forecast act.
    *
    * @return the total adjusted demand units of this vw adjust demand forecast act
    */
    @Override
    public double getTotalAdjustedDemandUnits() {
        return _vwAdjustDemandForecastAct.getTotalAdjustedDemandUnits();
    }

    /**
    * Sets the total adjusted demand units of this vw adjust demand forecast act.
    *
    * @param totalAdjustedDemandUnits the total adjusted demand units of this vw adjust demand forecast act
    */
    @Override
    public void setTotalAdjustedDemandUnits(double totalAdjustedDemandUnits) {
        _vwAdjustDemandForecastAct.setTotalAdjustedDemandUnits(totalAdjustedDemandUnits);
    }

    /**
    * Returns the brand ID of this vw adjust demand forecast act.
    *
    * @return the brand ID of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getBrandId() {
        return _vwAdjustDemandForecastAct.getBrandId();
    }

    /**
    * Sets the brand ID of this vw adjust demand forecast act.
    *
    * @param brandId the brand ID of this vw adjust demand forecast act
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _vwAdjustDemandForecastAct.setBrandId(brandId);
    }

    /**
    * Returns the is forecast of this vw adjust demand forecast act.
    *
    * @return the is forecast of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getIsForecast() {
        return _vwAdjustDemandForecastAct.getIsForecast();
    }

    /**
    * Sets the is forecast of this vw adjust demand forecast act.
    *
    * @param isForecast the is forecast of this vw adjust demand forecast act
    */
    @Override
    public void setIsForecast(java.lang.String isForecast) {
        _vwAdjustDemandForecastAct.setIsForecast(isForecast);
    }

    /**
    * Returns the total adjusted demand amount of this vw adjust demand forecast act.
    *
    * @return the total adjusted demand amount of this vw adjust demand forecast act
    */
    @Override
    public double getTotalAdjustedDemandAmount() {
        return _vwAdjustDemandForecastAct.getTotalAdjustedDemandAmount();
    }

    /**
    * Sets the total adjusted demand amount of this vw adjust demand forecast act.
    *
    * @param totalAdjustedDemandAmount the total adjusted demand amount of this vw adjust demand forecast act
    */
    @Override
    public void setTotalAdjustedDemandAmount(double totalAdjustedDemandAmount) {
        _vwAdjustDemandForecastAct.setTotalAdjustedDemandAmount(totalAdjustedDemandAmount);
    }

    /**
    * Returns the uncaptured units of this vw adjust demand forecast act.
    *
    * @return the uncaptured units of this vw adjust demand forecast act
    */
    @Override
    public double getUncapturedUnits() {
        return _vwAdjustDemandForecastAct.getUncapturedUnits();
    }

    /**
    * Sets the uncaptured units of this vw adjust demand forecast act.
    *
    * @param uncapturedUnits the uncaptured units of this vw adjust demand forecast act
    */
    @Override
    public void setUncapturedUnits(double uncapturedUnits) {
        _vwAdjustDemandForecastAct.setUncapturedUnits(uncapturedUnits);
    }

    /**
    * Returns the gross price of this vw adjust demand forecast act.
    *
    * @return the gross price of this vw adjust demand forecast act
    */
    @Override
    public double getGrossPrice() {
        return _vwAdjustDemandForecastAct.getGrossPrice();
    }

    /**
    * Sets the gross price of this vw adjust demand forecast act.
    *
    * @param grossPrice the gross price of this vw adjust demand forecast act
    */
    @Override
    public void setGrossPrice(double grossPrice) {
        _vwAdjustDemandForecastAct.setGrossPrice(grossPrice);
    }

    /**
    * Returns the gross amount of this vw adjust demand forecast act.
    *
    * @return the gross amount of this vw adjust demand forecast act
    */
    @Override
    public double getGrossAmount() {
        return _vwAdjustDemandForecastAct.getGrossAmount();
    }

    /**
    * Sets the gross amount of this vw adjust demand forecast act.
    *
    * @param grossAmount the gross amount of this vw adjust demand forecast act
    */
    @Override
    public void setGrossAmount(double grossAmount) {
        _vwAdjustDemandForecastAct.setGrossAmount(grossAmount);
    }

    /**
    * Returns the batch ID of this vw adjust demand forecast act.
    *
    * @return the batch ID of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwAdjustDemandForecastAct.getBatchId();
    }

    /**
    * Sets the batch ID of this vw adjust demand forecast act.
    *
    * @param batchId the batch ID of this vw adjust demand forecast act
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwAdjustDemandForecastAct.setBatchId(batchId);
    }

    /**
    * Returns the adjusted demand forecast ID of this vw adjust demand forecast act.
    *
    * @return the adjusted demand forecast ID of this vw adjust demand forecast act
    */
    @Override
    public int getAdjustedDemandForecastId() {
        return _vwAdjustDemandForecastAct.getAdjustedDemandForecastId();
    }

    /**
    * Sets the adjusted demand forecast ID of this vw adjust demand forecast act.
    *
    * @param adjustedDemandForecastId the adjusted demand forecast ID of this vw adjust demand forecast act
    */
    @Override
    public void setAdjustedDemandForecastId(int adjustedDemandForecastId) {
        _vwAdjustDemandForecastAct.setAdjustedDemandForecastId(adjustedDemandForecastId);
    }

    /**
    * Returns the item name of this vw adjust demand forecast act.
    *
    * @return the item name of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getItemName() {
        return _vwAdjustDemandForecastAct.getItemName();
    }

    /**
    * Sets the item name of this vw adjust demand forecast act.
    *
    * @param itemName the item name of this vw adjust demand forecast act
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwAdjustDemandForecastAct.setItemName(itemName);
    }

    /**
    * Returns the net sales price of this vw adjust demand forecast act.
    *
    * @return the net sales price of this vw adjust demand forecast act
    */
    @Override
    public double getNetSalesPrice() {
        return _vwAdjustDemandForecastAct.getNetSalesPrice();
    }

    /**
    * Sets the net sales price of this vw adjust demand forecast act.
    *
    * @param netSalesPrice the net sales price of this vw adjust demand forecast act
    */
    @Override
    public void setNetSalesPrice(double netSalesPrice) {
        _vwAdjustDemandForecastAct.setNetSalesPrice(netSalesPrice);
    }

    /**
    * Returns the net sales amount of this vw adjust demand forecast act.
    *
    * @return the net sales amount of this vw adjust demand forecast act
    */
    @Override
    public double getNetSalesAmount() {
        return _vwAdjustDemandForecastAct.getNetSalesAmount();
    }

    /**
    * Sets the net sales amount of this vw adjust demand forecast act.
    *
    * @param netSalesAmount the net sales amount of this vw adjust demand forecast act
    */
    @Override
    public void setNetSalesAmount(double netSalesAmount) {
        _vwAdjustDemandForecastAct.setNetSalesAmount(netSalesAmount);
    }

    /**
    * Returns the segment of this vw adjust demand forecast act.
    *
    * @return the segment of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getSegment() {
        return _vwAdjustDemandForecastAct.getSegment();
    }

    /**
    * Sets the segment of this vw adjust demand forecast act.
    *
    * @param segment the segment of this vw adjust demand forecast act
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _vwAdjustDemandForecastAct.setSegment(segment);
    }

    /**
    * Returns the forecast name of this vw adjust demand forecast act.
    *
    * @return the forecast name of this vw adjust demand forecast act
    */
    @Override
    public java.lang.String getForecastName() {
        return _vwAdjustDemandForecastAct.getForecastName();
    }

    /**
    * Sets the forecast name of this vw adjust demand forecast act.
    *
    * @param forecastName the forecast name of this vw adjust demand forecast act
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _vwAdjustDemandForecastAct.setForecastName(forecastName);
    }

    /**
    * Returns the market size units of this vw adjust demand forecast act.
    *
    * @return the market size units of this vw adjust demand forecast act
    */
    @Override
    public double getMarketSizeUnits() {
        return _vwAdjustDemandForecastAct.getMarketSizeUnits();
    }

    /**
    * Sets the market size units of this vw adjust demand forecast act.
    *
    * @param marketSizeUnits the market size units of this vw adjust demand forecast act
    */
    @Override
    public void setMarketSizeUnits(double marketSizeUnits) {
        _vwAdjustDemandForecastAct.setMarketSizeUnits(marketSizeUnits);
    }

    @Override
    public boolean isNew() {
        return _vwAdjustDemandForecastAct.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwAdjustDemandForecastAct.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwAdjustDemandForecastAct.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwAdjustDemandForecastAct.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwAdjustDemandForecastAct.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwAdjustDemandForecastAct.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwAdjustDemandForecastAct.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwAdjustDemandForecastAct.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwAdjustDemandForecastAct.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwAdjustDemandForecastAct.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwAdjustDemandForecastAct.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwAdjustDemandForecastActWrapper((VwAdjustDemandForecastAct) _vwAdjustDemandForecastAct.clone());
    }

    @Override
    public int compareTo(VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
        return _vwAdjustDemandForecastAct.compareTo(vwAdjustDemandForecastAct);
    }

    @Override
    public int hashCode() {
        return _vwAdjustDemandForecastAct.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwAdjustDemandForecastAct> toCacheModel() {
        return _vwAdjustDemandForecastAct.toCacheModel();
    }

    @Override
    public VwAdjustDemandForecastAct toEscapedModel() {
        return new VwAdjustDemandForecastActWrapper(_vwAdjustDemandForecastAct.toEscapedModel());
    }

    @Override
    public VwAdjustDemandForecastAct toUnescapedModel() {
        return new VwAdjustDemandForecastActWrapper(_vwAdjustDemandForecastAct.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwAdjustDemandForecastAct.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwAdjustDemandForecastAct.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwAdjustDemandForecastAct.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwAdjustDemandForecastActWrapper)) {
            return false;
        }

        VwAdjustDemandForecastActWrapper vwAdjustDemandForecastActWrapper = (VwAdjustDemandForecastActWrapper) obj;

        if (Validator.equals(_vwAdjustDemandForecastAct,
                    vwAdjustDemandForecastActWrapper._vwAdjustDemandForecastAct)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwAdjustDemandForecastAct getWrappedVwAdjustDemandForecastAct() {
        return _vwAdjustDemandForecastAct;
    }

    @Override
    public VwAdjustDemandForecastAct getWrappedModel() {
        return _vwAdjustDemandForecastAct;
    }

    @Override
    public void resetOriginalValues() {
        _vwAdjustDemandForecastAct.resetOriginalValues();
    }
}
