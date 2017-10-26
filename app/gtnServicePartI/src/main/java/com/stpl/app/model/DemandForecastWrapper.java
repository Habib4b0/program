package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DemandForecast}.
 * </p>
 *
 * @author
 * @see DemandForecast
 * @generated
 */
public class DemandForecastWrapper implements DemandForecast,
    ModelWrapper<DemandForecast> {
    private DemandForecast _demandForecast;

    public DemandForecastWrapper(DemandForecast demandForecast) {
        _demandForecast = demandForecast;
    }

    @Override
    public Class<?> getModelClass() {
        return DemandForecast.class;
    }

    @Override
    public String getModelClassName() {
        return DemandForecast.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("totalDemandUnits", getTotalDemandUnits());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("batchId", getBatchId());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("brandId", getBrandId());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("country", getCountry());
        attributes.put("demandForecastSid", getDemandForecastSid());
        attributes.put("forecastType", getForecastType());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("totalDemandAmount", getTotalDemandAmount());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("marketSizeUnits", getMarketSizeUnits());
        attributes.put("segment", getSegment());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("itemId", getItemId());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("forecastName", getForecastName());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("source", getSource());
        attributes.put("uncapturedUnits", getUncapturedUnits());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Double totalDemandUnits = (Double) attributes.get("totalDemandUnits");

        if (totalDemandUnits != null) {
            setTotalDemandUnits(totalDemandUnits);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Double marketShareUnits = (Double) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Double grossAmount = (Double) attributes.get("grossAmount");

        if (grossAmount != null) {
            setGrossAmount(grossAmount);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        Double grossUnits = (Double) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        Integer demandForecastSid = (Integer) attributes.get(
                "demandForecastSid");

        if (demandForecastSid != null) {
            setDemandForecastSid(demandForecastSid);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Double totalDemandAmount = (Double) attributes.get("totalDemandAmount");

        if (totalDemandAmount != null) {
            setTotalDemandAmount(totalDemandAmount);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Double marketSizeUnits = (Double) attributes.get("marketSizeUnits");

        if (marketSizeUnits != null) {
            setMarketSizeUnits(marketSizeUnits);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double inventoryUnitChange = (Double) attributes.get(
                "inventoryUnitChange");

        if (inventoryUnitChange != null) {
            setInventoryUnitChange(inventoryUnitChange);
        }

        Double grossPrice = (Double) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Double netSalesAmount = (Double) attributes.get("netSalesAmount");

        if (netSalesAmount != null) {
            setNetSalesAmount(netSalesAmount);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String uncapturedUnitsRatio = (String) attributes.get(
                "uncapturedUnitsRatio");

        if (uncapturedUnitsRatio != null) {
            setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String marketShareRatio = (String) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Double uncapturedUnits = (Double) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        Double netSalesPrice = (Double) attributes.get("netSalesPrice");

        if (netSalesPrice != null) {
            setNetSalesPrice(netSalesPrice);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this demand forecast.
    *
    * @return the primary key of this demand forecast
    */
    @Override
    public int getPrimaryKey() {
        return _demandForecast.getPrimaryKey();
    }

    /**
    * Sets the primary key of this demand forecast.
    *
    * @param primaryKey the primary key of this demand forecast
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _demandForecast.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the modified by of this demand forecast.
    *
    * @return the modified by of this demand forecast
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _demandForecast.getModifiedBy();
    }

    /**
    * Sets the modified by of this demand forecast.
    *
    * @param modifiedBy the modified by of this demand forecast
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _demandForecast.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this demand forecast.
    *
    * @return the created date of this demand forecast
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _demandForecast.getCreatedDate();
    }

    /**
    * Sets the created date of this demand forecast.
    *
    * @param createdDate the created date of this demand forecast
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _demandForecast.setCreatedDate(createdDate);
    }

    /**
    * Returns the total demand units of this demand forecast.
    *
    * @return the total demand units of this demand forecast
    */
    @Override
    public double getTotalDemandUnits() {
        return _demandForecast.getTotalDemandUnits();
    }

    /**
    * Sets the total demand units of this demand forecast.
    *
    * @param totalDemandUnits the total demand units of this demand forecast
    */
    @Override
    public void setTotalDemandUnits(double totalDemandUnits) {
        _demandForecast.setTotalDemandUnits(totalDemandUnits);
    }

    /**
    * Returns the brand master sid of this demand forecast.
    *
    * @return the brand master sid of this demand forecast
    */
    @Override
    public int getBrandMasterSid() {
        return _demandForecast.getBrandMasterSid();
    }

    /**
    * Sets the brand master sid of this demand forecast.
    *
    * @param brandMasterSid the brand master sid of this demand forecast
    */
    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _demandForecast.setBrandMasterSid(brandMasterSid);
    }

    /**
    * Returns the market share units of this demand forecast.
    *
    * @return the market share units of this demand forecast
    */
    @Override
    public double getMarketShareUnits() {
        return _demandForecast.getMarketShareUnits();
    }

    /**
    * Sets the market share units of this demand forecast.
    *
    * @param marketShareUnits the market share units of this demand forecast
    */
    @Override
    public void setMarketShareUnits(double marketShareUnits) {
        _demandForecast.setMarketShareUnits(marketShareUnits);
    }

    /**
    * Returns the batch ID of this demand forecast.
    *
    * @return the batch ID of this demand forecast
    */
    @Override
    public java.lang.String getBatchId() {
        return _demandForecast.getBatchId();
    }

    /**
    * Sets the batch ID of this demand forecast.
    *
    * @param batchId the batch ID of this demand forecast
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _demandForecast.setBatchId(batchId);
    }

    /**
    * Returns the gross amount of this demand forecast.
    *
    * @return the gross amount of this demand forecast
    */
    @Override
    public double getGrossAmount() {
        return _demandForecast.getGrossAmount();
    }

    /**
    * Sets the gross amount of this demand forecast.
    *
    * @param grossAmount the gross amount of this demand forecast
    */
    @Override
    public void setGrossAmount(double grossAmount) {
        _demandForecast.setGrossAmount(grossAmount);
    }

    /**
    * Returns the forecast ver of this demand forecast.
    *
    * @return the forecast ver of this demand forecast
    */
    @Override
    public java.lang.String getForecastVer() {
        return _demandForecast.getForecastVer();
    }

    /**
    * Sets the forecast ver of this demand forecast.
    *
    * @param forecastVer the forecast ver of this demand forecast
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _demandForecast.setForecastVer(forecastVer);
    }

    /**
    * Returns the brand ID of this demand forecast.
    *
    * @return the brand ID of this demand forecast
    */
    @Override
    public java.lang.String getBrandId() {
        return _demandForecast.getBrandId();
    }

    /**
    * Sets the brand ID of this demand forecast.
    *
    * @param brandId the brand ID of this demand forecast
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _demandForecast.setBrandId(brandId);
    }

    /**
    * Returns the gross units of this demand forecast.
    *
    * @return the gross units of this demand forecast
    */
    @Override
    public double getGrossUnits() {
        return _demandForecast.getGrossUnits();
    }

    /**
    * Sets the gross units of this demand forecast.
    *
    * @param grossUnits the gross units of this demand forecast
    */
    @Override
    public void setGrossUnits(double grossUnits) {
        _demandForecast.setGrossUnits(grossUnits);
    }

    /**
    * Returns the country of this demand forecast.
    *
    * @return the country of this demand forecast
    */
    @Override
    public java.lang.String getCountry() {
        return _demandForecast.getCountry();
    }

    /**
    * Sets the country of this demand forecast.
    *
    * @param country the country of this demand forecast
    */
    @Override
    public void setCountry(java.lang.String country) {
        _demandForecast.setCountry(country);
    }

    /**
    * Returns the demand forecast sid of this demand forecast.
    *
    * @return the demand forecast sid of this demand forecast
    */
    @Override
    public int getDemandForecastSid() {
        return _demandForecast.getDemandForecastSid();
    }

    /**
    * Sets the demand forecast sid of this demand forecast.
    *
    * @param demandForecastSid the demand forecast sid of this demand forecast
    */
    @Override
    public void setDemandForecastSid(int demandForecastSid) {
        _demandForecast.setDemandForecastSid(demandForecastSid);
    }

    /**
    * Returns the forecast type of this demand forecast.
    *
    * @return the forecast type of this demand forecast
    */
    @Override
    public java.lang.String getForecastType() {
        return _demandForecast.getForecastType();
    }

    /**
    * Sets the forecast type of this demand forecast.
    *
    * @param forecastType the forecast type of this demand forecast
    */
    @Override
    public void setForecastType(java.lang.String forecastType) {
        _demandForecast.setForecastType(forecastType);
    }

    /**
    * Returns the item master sid of this demand forecast.
    *
    * @return the item master sid of this demand forecast
    */
    @Override
    public int getItemMasterSid() {
        return _demandForecast.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this demand forecast.
    *
    * @param itemMasterSid the item master sid of this demand forecast
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _demandForecast.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the total demand amount of this demand forecast.
    *
    * @return the total demand amount of this demand forecast
    */
    @Override
    public double getTotalDemandAmount() {
        return _demandForecast.getTotalDemandAmount();
    }

    /**
    * Sets the total demand amount of this demand forecast.
    *
    * @param totalDemandAmount the total demand amount of this demand forecast
    */
    @Override
    public void setTotalDemandAmount(double totalDemandAmount) {
        _demandForecast.setTotalDemandAmount(totalDemandAmount);
    }

    /**
    * Returns the forecast month of this demand forecast.
    *
    * @return the forecast month of this demand forecast
    */
    @Override
    public java.lang.String getForecastMonth() {
        return _demandForecast.getForecastMonth();
    }

    /**
    * Sets the forecast month of this demand forecast.
    *
    * @param forecastMonth the forecast month of this demand forecast
    */
    @Override
    public void setForecastMonth(java.lang.String forecastMonth) {
        _demandForecast.setForecastMonth(forecastMonth);
    }

    /**
    * Returns the organization key of this demand forecast.
    *
    * @return the organization key of this demand forecast
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _demandForecast.getOrganizationKey();
    }

    /**
    * Sets the organization key of this demand forecast.
    *
    * @param organizationKey the organization key of this demand forecast
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _demandForecast.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the created by of this demand forecast.
    *
    * @return the created by of this demand forecast
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _demandForecast.getCreatedBy();
    }

    /**
    * Sets the created by of this demand forecast.
    *
    * @param createdBy the created by of this demand forecast
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _demandForecast.setCreatedBy(createdBy);
    }

    /**
    * Returns the market size units of this demand forecast.
    *
    * @return the market size units of this demand forecast
    */
    @Override
    public double getMarketSizeUnits() {
        return _demandForecast.getMarketSizeUnits();
    }

    /**
    * Sets the market size units of this demand forecast.
    *
    * @param marketSizeUnits the market size units of this demand forecast
    */
    @Override
    public void setMarketSizeUnits(double marketSizeUnits) {
        _demandForecast.setMarketSizeUnits(marketSizeUnits);
    }

    /**
    * Returns the segment of this demand forecast.
    *
    * @return the segment of this demand forecast
    */
    @Override
    public java.lang.String getSegment() {
        return _demandForecast.getSegment();
    }

    /**
    * Sets the segment of this demand forecast.
    *
    * @param segment the segment of this demand forecast
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _demandForecast.setSegment(segment);
    }

    /**
    * Returns the forecast year of this demand forecast.
    *
    * @return the forecast year of this demand forecast
    */
    @Override
    public java.lang.String getForecastYear() {
        return _demandForecast.getForecastYear();
    }

    /**
    * Sets the forecast year of this demand forecast.
    *
    * @param forecastYear the forecast year of this demand forecast
    */
    @Override
    public void setForecastYear(java.lang.String forecastYear) {
        _demandForecast.setForecastYear(forecastYear);
    }

    /**
    * Returns the item ID of this demand forecast.
    *
    * @return the item ID of this demand forecast
    */
    @Override
    public java.lang.String getItemId() {
        return _demandForecast.getItemId();
    }

    /**
    * Sets the item ID of this demand forecast.
    *
    * @param itemId the item ID of this demand forecast
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _demandForecast.setItemId(itemId);
    }

    /**
    * Returns the inventory unit change of this demand forecast.
    *
    * @return the inventory unit change of this demand forecast
    */
    @Override
    public double getInventoryUnitChange() {
        return _demandForecast.getInventoryUnitChange();
    }

    /**
    * Sets the inventory unit change of this demand forecast.
    *
    * @param inventoryUnitChange the inventory unit change of this demand forecast
    */
    @Override
    public void setInventoryUnitChange(double inventoryUnitChange) {
        _demandForecast.setInventoryUnitChange(inventoryUnitChange);
    }

    /**
    * Returns the gross price of this demand forecast.
    *
    * @return the gross price of this demand forecast
    */
    @Override
    public double getGrossPrice() {
        return _demandForecast.getGrossPrice();
    }

    /**
    * Sets the gross price of this demand forecast.
    *
    * @param grossPrice the gross price of this demand forecast
    */
    @Override
    public void setGrossPrice(double grossPrice) {
        _demandForecast.setGrossPrice(grossPrice);
    }

    /**
    * Returns the forecast name of this demand forecast.
    *
    * @return the forecast name of this demand forecast
    */
    @Override
    public java.lang.String getForecastName() {
        return _demandForecast.getForecastName();
    }

    /**
    * Sets the forecast name of this demand forecast.
    *
    * @param forecastName the forecast name of this demand forecast
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _demandForecast.setForecastName(forecastName);
    }

    /**
    * Returns the net sales amount of this demand forecast.
    *
    * @return the net sales amount of this demand forecast
    */
    @Override
    public double getNetSalesAmount() {
        return _demandForecast.getNetSalesAmount();
    }

    /**
    * Sets the net sales amount of this demand forecast.
    *
    * @param netSalesAmount the net sales amount of this demand forecast
    */
    @Override
    public void setNetSalesAmount(double netSalesAmount) {
        _demandForecast.setNetSalesAmount(netSalesAmount);
    }

    /**
    * Returns the modified date of this demand forecast.
    *
    * @return the modified date of this demand forecast
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _demandForecast.getModifiedDate();
    }

    /**
    * Sets the modified date of this demand forecast.
    *
    * @param modifiedDate the modified date of this demand forecast
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _demandForecast.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the item identifier of this demand forecast.
    *
    * @return the item identifier of this demand forecast
    */
    @Override
    public java.lang.String getItemIdentifier() {
        return _demandForecast.getItemIdentifier();
    }

    /**
    * Sets the item identifier of this demand forecast.
    *
    * @param itemIdentifier the item identifier of this demand forecast
    */
    @Override
    public void setItemIdentifier(java.lang.String itemIdentifier) {
        _demandForecast.setItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the record lock status of this demand forecast.
    *
    * @return the record lock status of this demand forecast
    */
    @Override
    public boolean getRecordLockStatus() {
        return _demandForecast.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this demand forecast is record lock status.
    *
    * @return <code>true</code> if this demand forecast is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _demandForecast.isRecordLockStatus();
    }

    /**
    * Sets whether this demand forecast is record lock status.
    *
    * @param recordLockStatus the record lock status of this demand forecast
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _demandForecast.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the uncaptured units ratio of this demand forecast.
    *
    * @return the uncaptured units ratio of this demand forecast
    */
    @Override
    public java.lang.String getUncapturedUnitsRatio() {
        return _demandForecast.getUncapturedUnitsRatio();
    }

    /**
    * Sets the uncaptured units ratio of this demand forecast.
    *
    * @param uncapturedUnitsRatio the uncaptured units ratio of this demand forecast
    */
    @Override
    public void setUncapturedUnitsRatio(java.lang.String uncapturedUnitsRatio) {
        _demandForecast.setUncapturedUnitsRatio(uncapturedUnitsRatio);
    }

    /**
    * Returns the item identifier code qualifier of this demand forecast.
    *
    * @return the item identifier code qualifier of this demand forecast
    */
    @Override
    public java.lang.String getItemIdentifierCodeQualifier() {
        return _demandForecast.getItemIdentifierCodeQualifier();
    }

    /**
    * Sets the item identifier code qualifier of this demand forecast.
    *
    * @param itemIdentifierCodeQualifier the item identifier code qualifier of this demand forecast
    */
    @Override
    public void setItemIdentifierCodeQualifier(
        java.lang.String itemIdentifierCodeQualifier) {
        _demandForecast.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
    }

    /**
    * Returns the market share ratio of this demand forecast.
    *
    * @return the market share ratio of this demand forecast
    */
    @Override
    public java.lang.String getMarketShareRatio() {
        return _demandForecast.getMarketShareRatio();
    }

    /**
    * Sets the market share ratio of this demand forecast.
    *
    * @param marketShareRatio the market share ratio of this demand forecast
    */
    @Override
    public void setMarketShareRatio(java.lang.String marketShareRatio) {
        _demandForecast.setMarketShareRatio(marketShareRatio);
    }

    /**
    * Returns the source of this demand forecast.
    *
    * @return the source of this demand forecast
    */
    @Override
    public java.lang.String getSource() {
        return _demandForecast.getSource();
    }

    /**
    * Sets the source of this demand forecast.
    *
    * @param source the source of this demand forecast
    */
    @Override
    public void setSource(java.lang.String source) {
        _demandForecast.setSource(source);
    }

    /**
    * Returns the uncaptured units of this demand forecast.
    *
    * @return the uncaptured units of this demand forecast
    */
    @Override
    public double getUncapturedUnits() {
        return _demandForecast.getUncapturedUnits();
    }

    /**
    * Sets the uncaptured units of this demand forecast.
    *
    * @param uncapturedUnits the uncaptured units of this demand forecast
    */
    @Override
    public void setUncapturedUnits(double uncapturedUnits) {
        _demandForecast.setUncapturedUnits(uncapturedUnits);
    }

    /**
    * Returns the net sales price of this demand forecast.
    *
    * @return the net sales price of this demand forecast
    */
    @Override
    public double getNetSalesPrice() {
        return _demandForecast.getNetSalesPrice();
    }

    /**
    * Sets the net sales price of this demand forecast.
    *
    * @param netSalesPrice the net sales price of this demand forecast
    */
    @Override
    public void setNetSalesPrice(double netSalesPrice) {
        _demandForecast.setNetSalesPrice(netSalesPrice);
    }

    /**
    * Returns the inbound status of this demand forecast.
    *
    * @return the inbound status of this demand forecast
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _demandForecast.getInboundStatus();
    }

    /**
    * Sets the inbound status of this demand forecast.
    *
    * @param inboundStatus the inbound status of this demand forecast
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _demandForecast.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _demandForecast.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _demandForecast.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _demandForecast.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _demandForecast.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _demandForecast.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _demandForecast.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _demandForecast.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _demandForecast.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _demandForecast.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _demandForecast.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _demandForecast.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DemandForecastWrapper((DemandForecast) _demandForecast.clone());
    }

    @Override
    public int compareTo(DemandForecast demandForecast) {
        return _demandForecast.compareTo(demandForecast);
    }

    @Override
    public int hashCode() {
        return _demandForecast.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<DemandForecast> toCacheModel() {
        return _demandForecast.toCacheModel();
    }

    @Override
    public DemandForecast toEscapedModel() {
        return new DemandForecastWrapper(_demandForecast.toEscapedModel());
    }

    @Override
    public DemandForecast toUnescapedModel() {
        return new DemandForecastWrapper(_demandForecast.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _demandForecast.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _demandForecast.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _demandForecast.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DemandForecastWrapper)) {
            return false;
        }

        DemandForecastWrapper demandForecastWrapper = (DemandForecastWrapper) obj;

        if (Validator.equals(_demandForecast,
                    demandForecastWrapper._demandForecast)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DemandForecast getWrappedDemandForecast() {
        return _demandForecast;
    }

    @Override
    public DemandForecast getWrappedModel() {
        return _demandForecast;
    }

    @Override
    public void resetOriginalValues() {
        _demandForecast.resetOriginalValues();
    }
}
