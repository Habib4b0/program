package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwIvldAdjDemandForeActual}.
 * </p>
 *
 * @author
 * @see VwIvldAdjDemandForeActual
 * @generated
 */
public class VwIvldAdjDemandForeActualWrapper
    implements VwIvldAdjDemandForeActual,
        ModelWrapper<VwIvldAdjDemandForeActual> {
    private VwIvldAdjDemandForeActual _vwIvldAdjDemandForeActual;

    public VwIvldAdjDemandForeActualWrapper(
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
        _vwIvldAdjDemandForeActual = vwIvldAdjDemandForeActual;
    }

    @Override
    public Class<?> getModelClass() {
        return VwIvldAdjDemandForeActual.class;
    }

    @Override
    public String getModelClassName() {
        return VwIvldAdjDemandForeActual.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastVersion", getForecastVersion());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("errorCode", getErrorCode());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("month", getMonth());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("adjustedDemandForecastIntfId",
            getAdjustedDemandForecastIntfId());
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
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("errorField", getErrorField());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("forecastName", getForecastName());
        attributes.put("ivldAdjustedDemandForecastSid",
            getIvldAdjustedDemandForecastSid());
        attributes.put("marketSizeUnits", getMarketSizeUnits());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String forecastVersion = (String) attributes.get("forecastVersion");

        if (forecastVersion != null) {
            setForecastVersion(forecastVersion);
        }

        String grossUnits = (String) attributes.get("grossUnits");

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

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String marketShareRatio = (String) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
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

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String marketShareUnits = (String) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
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

        String adjustedDemandForecastIntfId = (String) attributes.get(
                "adjustedDemandForecastIntfId");

        if (adjustedDemandForecastIntfId != null) {
            setAdjustedDemandForecastIntfId(adjustedDemandForecastIntfId);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        String totalAdjustedDemandUnits = (String) attributes.get(
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

        String totalAdjustedDemandAmount = (String) attributes.get(
                "totalAdjustedDemandAmount");

        if (totalAdjustedDemandAmount != null) {
            setTotalAdjustedDemandAmount(totalAdjustedDemandAmount);
        }

        String uncapturedUnits = (String) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        String grossPrice = (String) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        String grossAmount = (String) attributes.get("grossAmount");

        if (grossAmount != null) {
            setGrossAmount(grossAmount);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
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

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Integer ivldAdjustedDemandForecastSid = (Integer) attributes.get(
                "ivldAdjustedDemandForecastSid");

        if (ivldAdjustedDemandForecastSid != null) {
            setIvldAdjustedDemandForecastSid(ivldAdjustedDemandForecastSid);
        }

        String marketSizeUnits = (String) attributes.get("marketSizeUnits");

        if (marketSizeUnits != null) {
            setMarketSizeUnits(marketSizeUnits);
        }
    }

    /**
    * Returns the primary key of this vw ivld adj demand fore actual.
    *
    * @return the primary key of this vw ivld adj demand fore actual
    */
    @Override
    public int getPrimaryKey() {
        return _vwIvldAdjDemandForeActual.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw ivld adj demand fore actual.
    *
    * @param primaryKey the primary key of this vw ivld adj demand fore actual
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwIvldAdjDemandForeActual.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the forecast version of this vw ivld adj demand fore actual.
    *
    * @return the forecast version of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getForecastVersion() {
        return _vwIvldAdjDemandForeActual.getForecastVersion();
    }

    /**
    * Sets the forecast version of this vw ivld adj demand fore actual.
    *
    * @param forecastVersion the forecast version of this vw ivld adj demand fore actual
    */
    @Override
    public void setForecastVersion(java.lang.String forecastVersion) {
        _vwIvldAdjDemandForeActual.setForecastVersion(forecastVersion);
    }

    /**
    * Returns the gross units of this vw ivld adj demand fore actual.
    *
    * @return the gross units of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getGrossUnits() {
        return _vwIvldAdjDemandForeActual.getGrossUnits();
    }

    /**
    * Sets the gross units of this vw ivld adj demand fore actual.
    *
    * @param grossUnits the gross units of this vw ivld adj demand fore actual
    */
    @Override
    public void setGrossUnits(java.lang.String grossUnits) {
        _vwIvldAdjDemandForeActual.setGrossUnits(grossUnits);
    }

    /**
    * Returns the business unit no of this vw ivld adj demand fore actual.
    *
    * @return the business unit no of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getBusinessUnitNo() {
        return _vwIvldAdjDemandForeActual.getBusinessUnitNo();
    }

    /**
    * Sets the business unit no of this vw ivld adj demand fore actual.
    *
    * @param businessUnitNo the business unit no of this vw ivld adj demand fore actual
    */
    @Override
    public void setBusinessUnitNo(java.lang.String businessUnitNo) {
        _vwIvldAdjDemandForeActual.setBusinessUnitNo(businessUnitNo);
    }

    /**
    * Returns the year of this vw ivld adj demand fore actual.
    *
    * @return the year of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getYear() {
        return _vwIvldAdjDemandForeActual.getYear();
    }

    /**
    * Sets the year of this vw ivld adj demand fore actual.
    *
    * @param year the year of this vw ivld adj demand fore actual
    */
    @Override
    public void setYear(java.lang.String year) {
        _vwIvldAdjDemandForeActual.setYear(year);
    }

    /**
    * Returns the item ID of this vw ivld adj demand fore actual.
    *
    * @return the item ID of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getItemId() {
        return _vwIvldAdjDemandForeActual.getItemId();
    }

    /**
    * Sets the item ID of this vw ivld adj demand fore actual.
    *
    * @param itemId the item ID of this vw ivld adj demand fore actual
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwIvldAdjDemandForeActual.setItemId(itemId);
    }

    /**
    * Returns the brand name of this vw ivld adj demand fore actual.
    *
    * @return the brand name of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getBrandName() {
        return _vwIvldAdjDemandForeActual.getBrandName();
    }

    /**
    * Sets the brand name of this vw ivld adj demand fore actual.
    *
    * @param brandName the brand name of this vw ivld adj demand fore actual
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _vwIvldAdjDemandForeActual.setBrandName(brandName);
    }

    /**
    * Returns the modified date of this vw ivld adj demand fore actual.
    *
    * @return the modified date of this vw ivld adj demand fore actual
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwIvldAdjDemandForeActual.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw ivld adj demand fore actual.
    *
    * @param modifiedDate the modified date of this vw ivld adj demand fore actual
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwIvldAdjDemandForeActual.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the organization key of this vw ivld adj demand fore actual.
    *
    * @return the organization key of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _vwIvldAdjDemandForeActual.getOrganizationKey();
    }

    /**
    * Sets the organization key of this vw ivld adj demand fore actual.
    *
    * @param organizationKey the organization key of this vw ivld adj demand fore actual
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _vwIvldAdjDemandForeActual.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the created date of this vw ivld adj demand fore actual.
    *
    * @return the created date of this vw ivld adj demand fore actual
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwIvldAdjDemandForeActual.getCreatedDate();
    }

    /**
    * Sets the created date of this vw ivld adj demand fore actual.
    *
    * @param createdDate the created date of this vw ivld adj demand fore actual
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwIvldAdjDemandForeActual.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this vw ivld adj demand fore actual.
    *
    * @return the created by of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwIvldAdjDemandForeActual.getCreatedBy();
    }

    /**
    * Sets the created by of this vw ivld adj demand fore actual.
    *
    * @param createdBy the created by of this vw ivld adj demand fore actual
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwIvldAdjDemandForeActual.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this vw ivld adj demand fore actual.
    *
    * @return the source of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getSource() {
        return _vwIvldAdjDemandForeActual.getSource();
    }

    /**
    * Sets the source of this vw ivld adj demand fore actual.
    *
    * @param source the source of this vw ivld adj demand fore actual
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwIvldAdjDemandForeActual.setSource(source);
    }

    /**
    * Returns the market share ratio of this vw ivld adj demand fore actual.
    *
    * @return the market share ratio of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getMarketShareRatio() {
        return _vwIvldAdjDemandForeActual.getMarketShareRatio();
    }

    /**
    * Sets the market share ratio of this vw ivld adj demand fore actual.
    *
    * @param marketShareRatio the market share ratio of this vw ivld adj demand fore actual
    */
    @Override
    public void setMarketShareRatio(java.lang.String marketShareRatio) {
        _vwIvldAdjDemandForeActual.setMarketShareRatio(marketShareRatio);
    }

    /**
    * Returns the business unit name of this vw ivld adj demand fore actual.
    *
    * @return the business unit name of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getBusinessUnitName() {
        return _vwIvldAdjDemandForeActual.getBusinessUnitName();
    }

    /**
    * Sets the business unit name of this vw ivld adj demand fore actual.
    *
    * @param businessUnitName the business unit name of this vw ivld adj demand fore actual
    */
    @Override
    public void setBusinessUnitName(java.lang.String businessUnitName) {
        _vwIvldAdjDemandForeActual.setBusinessUnitName(businessUnitName);
    }

    /**
    * Returns the add chg del indicator of this vw ivld adj demand fore actual.
    *
    * @return the add chg del indicator of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwIvldAdjDemandForeActual.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw ivld adj demand fore actual.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw ivld adj demand fore actual
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwIvldAdjDemandForeActual.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the item identifier of this vw ivld adj demand fore actual.
    *
    * @return the item identifier of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getItemIdentifier() {
        return _vwIvldAdjDemandForeActual.getItemIdentifier();
    }

    /**
    * Sets the item identifier of this vw ivld adj demand fore actual.
    *
    * @param itemIdentifier the item identifier of this vw ivld adj demand fore actual
    */
    @Override
    public void setItemIdentifier(java.lang.String itemIdentifier) {
        _vwIvldAdjDemandForeActual.setItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the error code of this vw ivld adj demand fore actual.
    *
    * @return the error code of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getErrorCode() {
        return _vwIvldAdjDemandForeActual.getErrorCode();
    }

    /**
    * Sets the error code of this vw ivld adj demand fore actual.
    *
    * @param errorCode the error code of this vw ivld adj demand fore actual
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _vwIvldAdjDemandForeActual.setErrorCode(errorCode);
    }

    /**
    * Returns the modified by of this vw ivld adj demand fore actual.
    *
    * @return the modified by of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwIvldAdjDemandForeActual.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw ivld adj demand fore actual.
    *
    * @param modifiedBy the modified by of this vw ivld adj demand fore actual
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwIvldAdjDemandForeActual.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the market share units of this vw ivld adj demand fore actual.
    *
    * @return the market share units of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getMarketShareUnits() {
        return _vwIvldAdjDemandForeActual.getMarketShareUnits();
    }

    /**
    * Sets the market share units of this vw ivld adj demand fore actual.
    *
    * @param marketShareUnits the market share units of this vw ivld adj demand fore actual
    */
    @Override
    public void setMarketShareUnits(java.lang.String marketShareUnits) {
        _vwIvldAdjDemandForeActual.setMarketShareUnits(marketShareUnits);
    }

    /**
    * Returns the month of this vw ivld adj demand fore actual.
    *
    * @return the month of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getMonth() {
        return _vwIvldAdjDemandForeActual.getMonth();
    }

    /**
    * Sets the month of this vw ivld adj demand fore actual.
    *
    * @param month the month of this vw ivld adj demand fore actual
    */
    @Override
    public void setMonth(java.lang.String month) {
        _vwIvldAdjDemandForeActual.setMonth(month);
    }

    /**
    * Returns the inventory unit change of this vw ivld adj demand fore actual.
    *
    * @return the inventory unit change of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getInventoryUnitChange() {
        return _vwIvldAdjDemandForeActual.getInventoryUnitChange();
    }

    /**
    * Sets the inventory unit change of this vw ivld adj demand fore actual.
    *
    * @param inventoryUnitChange the inventory unit change of this vw ivld adj demand fore actual
    */
    @Override
    public void setInventoryUnitChange(java.lang.String inventoryUnitChange) {
        _vwIvldAdjDemandForeActual.setInventoryUnitChange(inventoryUnitChange);
    }

    /**
    * Returns the reprocessed flag of this vw ivld adj demand fore actual.
    *
    * @return the reprocessed flag of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _vwIvldAdjDemandForeActual.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this vw ivld adj demand fore actual.
    *
    * @param reprocessedFlag the reprocessed flag of this vw ivld adj demand fore actual
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _vwIvldAdjDemandForeActual.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the uncaptured units ratio of this vw ivld adj demand fore actual.
    *
    * @return the uncaptured units ratio of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getUncapturedUnitsRatio() {
        return _vwIvldAdjDemandForeActual.getUncapturedUnitsRatio();
    }

    /**
    * Sets the uncaptured units ratio of this vw ivld adj demand fore actual.
    *
    * @param uncapturedUnitsRatio the uncaptured units ratio of this vw ivld adj demand fore actual
    */
    @Override
    public void setUncapturedUnitsRatio(java.lang.String uncapturedUnitsRatio) {
        _vwIvldAdjDemandForeActual.setUncapturedUnitsRatio(uncapturedUnitsRatio);
    }

    /**
    * Returns the reason for failure of this vw ivld adj demand fore actual.
    *
    * @return the reason for failure of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _vwIvldAdjDemandForeActual.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this vw ivld adj demand fore actual.
    *
    * @param reasonForFailure the reason for failure of this vw ivld adj demand fore actual
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _vwIvldAdjDemandForeActual.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the adjusted demand forecast intf ID of this vw ivld adj demand fore actual.
    *
    * @return the adjusted demand forecast intf ID of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getAdjustedDemandForecastIntfId() {
        return _vwIvldAdjDemandForeActual.getAdjustedDemandForecastIntfId();
    }

    /**
    * Sets the adjusted demand forecast intf ID of this vw ivld adj demand fore actual.
    *
    * @param adjustedDemandForecastIntfId the adjusted demand forecast intf ID of this vw ivld adj demand fore actual
    */
    @Override
    public void setAdjustedDemandForecastIntfId(
        java.lang.String adjustedDemandForecastIntfId) {
        _vwIvldAdjDemandForeActual.setAdjustedDemandForecastIntfId(adjustedDemandForecastIntfId);
    }

    /**
    * Returns the country of this vw ivld adj demand fore actual.
    *
    * @return the country of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getCountry() {
        return _vwIvldAdjDemandForeActual.getCountry();
    }

    /**
    * Sets the country of this vw ivld adj demand fore actual.
    *
    * @param country the country of this vw ivld adj demand fore actual
    */
    @Override
    public void setCountry(java.lang.String country) {
        _vwIvldAdjDemandForeActual.setCountry(country);
    }

    /**
    * Returns the forecast type of this vw ivld adj demand fore actual.
    *
    * @return the forecast type of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getForecastType() {
        return _vwIvldAdjDemandForeActual.getForecastType();
    }

    /**
    * Sets the forecast type of this vw ivld adj demand fore actual.
    *
    * @param forecastType the forecast type of this vw ivld adj demand fore actual
    */
    @Override
    public void setForecastType(java.lang.String forecastType) {
        _vwIvldAdjDemandForeActual.setForecastType(forecastType);
    }

    /**
    * Returns the total adjusted demand units of this vw ivld adj demand fore actual.
    *
    * @return the total adjusted demand units of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getTotalAdjustedDemandUnits() {
        return _vwIvldAdjDemandForeActual.getTotalAdjustedDemandUnits();
    }

    /**
    * Sets the total adjusted demand units of this vw ivld adj demand fore actual.
    *
    * @param totalAdjustedDemandUnits the total adjusted demand units of this vw ivld adj demand fore actual
    */
    @Override
    public void setTotalAdjustedDemandUnits(
        java.lang.String totalAdjustedDemandUnits) {
        _vwIvldAdjDemandForeActual.setTotalAdjustedDemandUnits(totalAdjustedDemandUnits);
    }

    /**
    * Returns the brand ID of this vw ivld adj demand fore actual.
    *
    * @return the brand ID of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getBrandId() {
        return _vwIvldAdjDemandForeActual.getBrandId();
    }

    /**
    * Sets the brand ID of this vw ivld adj demand fore actual.
    *
    * @param brandId the brand ID of this vw ivld adj demand fore actual
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _vwIvldAdjDemandForeActual.setBrandId(brandId);
    }

    /**
    * Returns the is forecast of this vw ivld adj demand fore actual.
    *
    * @return the is forecast of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getIsForecast() {
        return _vwIvldAdjDemandForeActual.getIsForecast();
    }

    /**
    * Sets the is forecast of this vw ivld adj demand fore actual.
    *
    * @param isForecast the is forecast of this vw ivld adj demand fore actual
    */
    @Override
    public void setIsForecast(java.lang.String isForecast) {
        _vwIvldAdjDemandForeActual.setIsForecast(isForecast);
    }

    /**
    * Returns the total adjusted demand amount of this vw ivld adj demand fore actual.
    *
    * @return the total adjusted demand amount of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getTotalAdjustedDemandAmount() {
        return _vwIvldAdjDemandForeActual.getTotalAdjustedDemandAmount();
    }

    /**
    * Sets the total adjusted demand amount of this vw ivld adj demand fore actual.
    *
    * @param totalAdjustedDemandAmount the total adjusted demand amount of this vw ivld adj demand fore actual
    */
    @Override
    public void setTotalAdjustedDemandAmount(
        java.lang.String totalAdjustedDemandAmount) {
        _vwIvldAdjDemandForeActual.setTotalAdjustedDemandAmount(totalAdjustedDemandAmount);
    }

    /**
    * Returns the uncaptured units of this vw ivld adj demand fore actual.
    *
    * @return the uncaptured units of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getUncapturedUnits() {
        return _vwIvldAdjDemandForeActual.getUncapturedUnits();
    }

    /**
    * Sets the uncaptured units of this vw ivld adj demand fore actual.
    *
    * @param uncapturedUnits the uncaptured units of this vw ivld adj demand fore actual
    */
    @Override
    public void setUncapturedUnits(java.lang.String uncapturedUnits) {
        _vwIvldAdjDemandForeActual.setUncapturedUnits(uncapturedUnits);
    }

    /**
    * Returns the gross price of this vw ivld adj demand fore actual.
    *
    * @return the gross price of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getGrossPrice() {
        return _vwIvldAdjDemandForeActual.getGrossPrice();
    }

    /**
    * Sets the gross price of this vw ivld adj demand fore actual.
    *
    * @param grossPrice the gross price of this vw ivld adj demand fore actual
    */
    @Override
    public void setGrossPrice(java.lang.String grossPrice) {
        _vwIvldAdjDemandForeActual.setGrossPrice(grossPrice);
    }

    /**
    * Returns the gross amount of this vw ivld adj demand fore actual.
    *
    * @return the gross amount of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getGrossAmount() {
        return _vwIvldAdjDemandForeActual.getGrossAmount();
    }

    /**
    * Sets the gross amount of this vw ivld adj demand fore actual.
    *
    * @param grossAmount the gross amount of this vw ivld adj demand fore actual
    */
    @Override
    public void setGrossAmount(java.lang.String grossAmount) {
        _vwIvldAdjDemandForeActual.setGrossAmount(grossAmount);
    }

    /**
    * Returns the item identifier code qualifier of this vw ivld adj demand fore actual.
    *
    * @return the item identifier code qualifier of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getItemIdentifierCodeQualifier() {
        return _vwIvldAdjDemandForeActual.getItemIdentifierCodeQualifier();
    }

    /**
    * Sets the item identifier code qualifier of this vw ivld adj demand fore actual.
    *
    * @param itemIdentifierCodeQualifier the item identifier code qualifier of this vw ivld adj demand fore actual
    */
    @Override
    public void setItemIdentifierCodeQualifier(
        java.lang.String itemIdentifierCodeQualifier) {
        _vwIvldAdjDemandForeActual.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
    }

    /**
    * Returns the batch ID of this vw ivld adj demand fore actual.
    *
    * @return the batch ID of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwIvldAdjDemandForeActual.getBatchId();
    }

    /**
    * Sets the batch ID of this vw ivld adj demand fore actual.
    *
    * @param batchId the batch ID of this vw ivld adj demand fore actual
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwIvldAdjDemandForeActual.setBatchId(batchId);
    }

    /**
    * Returns the item name of this vw ivld adj demand fore actual.
    *
    * @return the item name of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getItemName() {
        return _vwIvldAdjDemandForeActual.getItemName();
    }

    /**
    * Sets the item name of this vw ivld adj demand fore actual.
    *
    * @param itemName the item name of this vw ivld adj demand fore actual
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwIvldAdjDemandForeActual.setItemName(itemName);
    }

    /**
    * Returns the error field of this vw ivld adj demand fore actual.
    *
    * @return the error field of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getErrorField() {
        return _vwIvldAdjDemandForeActual.getErrorField();
    }

    /**
    * Sets the error field of this vw ivld adj demand fore actual.
    *
    * @param errorField the error field of this vw ivld adj demand fore actual
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _vwIvldAdjDemandForeActual.setErrorField(errorField);
    }

    /**
    * Returns the net sales price of this vw ivld adj demand fore actual.
    *
    * @return the net sales price of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getNetSalesPrice() {
        return _vwIvldAdjDemandForeActual.getNetSalesPrice();
    }

    /**
    * Sets the net sales price of this vw ivld adj demand fore actual.
    *
    * @param netSalesPrice the net sales price of this vw ivld adj demand fore actual
    */
    @Override
    public void setNetSalesPrice(java.lang.String netSalesPrice) {
        _vwIvldAdjDemandForeActual.setNetSalesPrice(netSalesPrice);
    }

    /**
    * Returns the net sales amount of this vw ivld adj demand fore actual.
    *
    * @return the net sales amount of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getNetSalesAmount() {
        return _vwIvldAdjDemandForeActual.getNetSalesAmount();
    }

    /**
    * Sets the net sales amount of this vw ivld adj demand fore actual.
    *
    * @param netSalesAmount the net sales amount of this vw ivld adj demand fore actual
    */
    @Override
    public void setNetSalesAmount(java.lang.String netSalesAmount) {
        _vwIvldAdjDemandForeActual.setNetSalesAmount(netSalesAmount);
    }

    /**
    * Returns the segment of this vw ivld adj demand fore actual.
    *
    * @return the segment of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getSegment() {
        return _vwIvldAdjDemandForeActual.getSegment();
    }

    /**
    * Sets the segment of this vw ivld adj demand fore actual.
    *
    * @param segment the segment of this vw ivld adj demand fore actual
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _vwIvldAdjDemandForeActual.setSegment(segment);
    }

    /**
    * Returns the forecast name of this vw ivld adj demand fore actual.
    *
    * @return the forecast name of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getForecastName() {
        return _vwIvldAdjDemandForeActual.getForecastName();
    }

    /**
    * Sets the forecast name of this vw ivld adj demand fore actual.
    *
    * @param forecastName the forecast name of this vw ivld adj demand fore actual
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _vwIvldAdjDemandForeActual.setForecastName(forecastName);
    }

    /**
    * Returns the ivld adjusted demand forecast sid of this vw ivld adj demand fore actual.
    *
    * @return the ivld adjusted demand forecast sid of this vw ivld adj demand fore actual
    */
    @Override
    public int getIvldAdjustedDemandForecastSid() {
        return _vwIvldAdjDemandForeActual.getIvldAdjustedDemandForecastSid();
    }

    /**
    * Sets the ivld adjusted demand forecast sid of this vw ivld adj demand fore actual.
    *
    * @param ivldAdjustedDemandForecastSid the ivld adjusted demand forecast sid of this vw ivld adj demand fore actual
    */
    @Override
    public void setIvldAdjustedDemandForecastSid(
        int ivldAdjustedDemandForecastSid) {
        _vwIvldAdjDemandForeActual.setIvldAdjustedDemandForecastSid(ivldAdjustedDemandForecastSid);
    }

    /**
    * Returns the market size units of this vw ivld adj demand fore actual.
    *
    * @return the market size units of this vw ivld adj demand fore actual
    */
    @Override
    public java.lang.String getMarketSizeUnits() {
        return _vwIvldAdjDemandForeActual.getMarketSizeUnits();
    }

    /**
    * Sets the market size units of this vw ivld adj demand fore actual.
    *
    * @param marketSizeUnits the market size units of this vw ivld adj demand fore actual
    */
    @Override
    public void setMarketSizeUnits(java.lang.String marketSizeUnits) {
        _vwIvldAdjDemandForeActual.setMarketSizeUnits(marketSizeUnits);
    }

    @Override
    public boolean isNew() {
        return _vwIvldAdjDemandForeActual.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwIvldAdjDemandForeActual.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwIvldAdjDemandForeActual.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwIvldAdjDemandForeActual.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwIvldAdjDemandForeActual.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwIvldAdjDemandForeActual.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwIvldAdjDemandForeActual.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwIvldAdjDemandForeActual.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwIvldAdjDemandForeActual.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwIvldAdjDemandForeActual.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwIvldAdjDemandForeActual.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwIvldAdjDemandForeActualWrapper((VwIvldAdjDemandForeActual) _vwIvldAdjDemandForeActual.clone());
    }

    @Override
    public int compareTo(VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
        return _vwIvldAdjDemandForeActual.compareTo(vwIvldAdjDemandForeActual);
    }

    @Override
    public int hashCode() {
        return _vwIvldAdjDemandForeActual.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwIvldAdjDemandForeActual> toCacheModel() {
        return _vwIvldAdjDemandForeActual.toCacheModel();
    }

    @Override
    public VwIvldAdjDemandForeActual toEscapedModel() {
        return new VwIvldAdjDemandForeActualWrapper(_vwIvldAdjDemandForeActual.toEscapedModel());
    }

    @Override
    public VwIvldAdjDemandForeActual toUnescapedModel() {
        return new VwIvldAdjDemandForeActualWrapper(_vwIvldAdjDemandForeActual.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwIvldAdjDemandForeActual.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwIvldAdjDemandForeActual.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwIvldAdjDemandForeActual.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwIvldAdjDemandForeActualWrapper)) {
            return false;
        }

        VwIvldAdjDemandForeActualWrapper vwIvldAdjDemandForeActualWrapper = (VwIvldAdjDemandForeActualWrapper) obj;

        if (Validator.equals(_vwIvldAdjDemandForeActual,
                    vwIvldAdjDemandForeActualWrapper._vwIvldAdjDemandForeActual)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwIvldAdjDemandForeActual getWrappedVwIvldAdjDemandForeActual() {
        return _vwIvldAdjDemandForeActual;
    }

    @Override
    public VwIvldAdjDemandForeActual getWrappedModel() {
        return _vwIvldAdjDemandForeActual;
    }

    @Override
    public void resetOriginalValues() {
        _vwIvldAdjDemandForeActual.resetOriginalValues();
    }
}
