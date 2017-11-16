package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ForecastingMaster}.
 * </p>
 *
 * @author
 * @see ForecastingMaster
 * @generated
 */
public class ForecastingMasterWrapper implements ForecastingMaster,
    ModelWrapper<ForecastingMaster> {
    private ForecastingMaster _forecastingMaster;

    public ForecastingMasterWrapper(ForecastingMaster forecastingMaster) {
        _forecastingMaster = forecastingMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ForecastingMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ForecastingMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastValueType", getForecastValueType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("percentageEstimate", getPercentageEstimate());
        attributes.put("actualSalesPercentageMonth",
            getActualSalesPercentageMonth());
        attributes.put("ndc", getNdc());
        attributes.put("batchId", getBatchId());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("country", getCountry());
        attributes.put("product", getProduct());
        attributes.put("forecastStartDate", getForecastStartDate());
        attributes.put("forecastedSalesPercentage",
            getForecastedSalesPercentage());
        attributes.put("units", getUnits());
        attributes.put("dollars", getDollars());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("segment", getSegment());
        attributes.put("price", getPrice());
        attributes.put("actualSalesPercentage", getActualSalesPercentage());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("forecastName", getForecastName());
        attributes.put("forecastDate", getForecastDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brand", getBrand());
        attributes.put("forecastValue", getForecastValue());
        attributes.put("percentageEstimateYear", getPercentageEstimateYear());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("forecastMasterSid", getForecastMasterSid());
        attributes.put("forecastedSalesPercentMonth",
            getForecastedSalesPercentMonth());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String forecastValueType = (String) attributes.get("forecastValueType");

        if (forecastValueType != null) {
            setForecastValueType(forecastValueType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String percentageEstimate = (String) attributes.get(
                "percentageEstimate");

        if (percentageEstimate != null) {
            setPercentageEstimate(percentageEstimate);
        }

        Integer actualSalesPercentageMonth = (Integer) attributes.get(
                "actualSalesPercentageMonth");

        if (actualSalesPercentageMonth != null) {
            setActualSalesPercentageMonth(actualSalesPercentageMonth);
        }

        String ndc = (String) attributes.get("ndc");

        if (ndc != null) {
            setNdc(ndc);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String product = (String) attributes.get("product");

        if (product != null) {
            setProduct(product);
        }

        Date forecastStartDate = (Date) attributes.get("forecastStartDate");

        if (forecastStartDate != null) {
            setForecastStartDate(forecastStartDate);
        }

        String forecastedSalesPercentage = (String) attributes.get(
                "forecastedSalesPercentage");

        if (forecastedSalesPercentage != null) {
            setForecastedSalesPercentage(forecastedSalesPercentage);
        }

        Double units = (Double) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        Double dollars = (Double) attributes.get("dollars");

        if (dollars != null) {
            setDollars(dollars);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String actualSalesPercentage = (String) attributes.get(
                "actualSalesPercentage");

        if (actualSalesPercentage != null) {
            setActualSalesPercentage(actualSalesPercentage);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Date forecastDate = (Date) attributes.get("forecastDate");

        if (forecastDate != null) {
            setForecastDate(forecastDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String forecastValue = (String) attributes.get("forecastValue");

        if (forecastValue != null) {
            setForecastValue(forecastValue);
        }

        Integer percentageEstimateYear = (Integer) attributes.get(
                "percentageEstimateYear");

        if (percentageEstimateYear != null) {
            setPercentageEstimateYear(percentageEstimateYear);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer forecastMasterSid = (Integer) attributes.get(
                "forecastMasterSid");

        if (forecastMasterSid != null) {
            setForecastMasterSid(forecastMasterSid);
        }

        Integer forecastedSalesPercentMonth = (Integer) attributes.get(
                "forecastedSalesPercentMonth");

        if (forecastedSalesPercentMonth != null) {
            setForecastedSalesPercentMonth(forecastedSalesPercentMonth);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this forecasting master.
    *
    * @return the primary key of this forecasting master
    */
    @Override
    public int getPrimaryKey() {
        return _forecastingMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this forecasting master.
    *
    * @param primaryKey the primary key of this forecasting master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _forecastingMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the forecast value type of this forecasting master.
    *
    * @return the forecast value type of this forecasting master
    */
    @Override
    public java.lang.String getForecastValueType() {
        return _forecastingMaster.getForecastValueType();
    }

    /**
    * Sets the forecast value type of this forecasting master.
    *
    * @param forecastValueType the forecast value type of this forecasting master
    */
    @Override
    public void setForecastValueType(java.lang.String forecastValueType) {
        _forecastingMaster.setForecastValueType(forecastValueType);
    }

    /**
    * Returns the modified by of this forecasting master.
    *
    * @return the modified by of this forecasting master
    */
    @Override
    public int getModifiedBy() {
        return _forecastingMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this forecasting master.
    *
    * @param modifiedBy the modified by of this forecasting master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _forecastingMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this forecasting master.
    *
    * @return the created date of this forecasting master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _forecastingMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this forecasting master.
    *
    * @param createdDate the created date of this forecasting master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _forecastingMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the percentage estimate of this forecasting master.
    *
    * @return the percentage estimate of this forecasting master
    */
    @Override
    public java.lang.String getPercentageEstimate() {
        return _forecastingMaster.getPercentageEstimate();
    }

    /**
    * Sets the percentage estimate of this forecasting master.
    *
    * @param percentageEstimate the percentage estimate of this forecasting master
    */
    @Override
    public void setPercentageEstimate(java.lang.String percentageEstimate) {
        _forecastingMaster.setPercentageEstimate(percentageEstimate);
    }

    /**
    * Returns the actual sales percentage month of this forecasting master.
    *
    * @return the actual sales percentage month of this forecasting master
    */
    @Override
    public int getActualSalesPercentageMonth() {
        return _forecastingMaster.getActualSalesPercentageMonth();
    }

    /**
    * Sets the actual sales percentage month of this forecasting master.
    *
    * @param actualSalesPercentageMonth the actual sales percentage month of this forecasting master
    */
    @Override
    public void setActualSalesPercentageMonth(int actualSalesPercentageMonth) {
        _forecastingMaster.setActualSalesPercentageMonth(actualSalesPercentageMonth);
    }

    /**
    * Returns the ndc of this forecasting master.
    *
    * @return the ndc of this forecasting master
    */
    @Override
    public java.lang.String getNdc() {
        return _forecastingMaster.getNdc();
    }

    /**
    * Sets the ndc of this forecasting master.
    *
    * @param ndc the ndc of this forecasting master
    */
    @Override
    public void setNdc(java.lang.String ndc) {
        _forecastingMaster.setNdc(ndc);
    }

    /**
    * Returns the batch ID of this forecasting master.
    *
    * @return the batch ID of this forecasting master
    */
    @Override
    public java.lang.String getBatchId() {
        return _forecastingMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this forecasting master.
    *
    * @param batchId the batch ID of this forecasting master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _forecastingMaster.setBatchId(batchId);
    }

    /**
    * Returns the forecast ver of this forecasting master.
    *
    * @return the forecast ver of this forecasting master
    */
    @Override
    public java.lang.String getForecastVer() {
        return _forecastingMaster.getForecastVer();
    }

    /**
    * Sets the forecast ver of this forecasting master.
    *
    * @param forecastVer the forecast ver of this forecasting master
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _forecastingMaster.setForecastVer(forecastVer);
    }

    /**
    * Returns the country of this forecasting master.
    *
    * @return the country of this forecasting master
    */
    @Override
    public java.lang.String getCountry() {
        return _forecastingMaster.getCountry();
    }

    /**
    * Sets the country of this forecasting master.
    *
    * @param country the country of this forecasting master
    */
    @Override
    public void setCountry(java.lang.String country) {
        _forecastingMaster.setCountry(country);
    }

    /**
    * Returns the product of this forecasting master.
    *
    * @return the product of this forecasting master
    */
    @Override
    public java.lang.String getProduct() {
        return _forecastingMaster.getProduct();
    }

    /**
    * Sets the product of this forecasting master.
    *
    * @param product the product of this forecasting master
    */
    @Override
    public void setProduct(java.lang.String product) {
        _forecastingMaster.setProduct(product);
    }

    /**
    * Returns the forecast start date of this forecasting master.
    *
    * @return the forecast start date of this forecasting master
    */
    @Override
    public java.util.Date getForecastStartDate() {
        return _forecastingMaster.getForecastStartDate();
    }

    /**
    * Sets the forecast start date of this forecasting master.
    *
    * @param forecastStartDate the forecast start date of this forecasting master
    */
    @Override
    public void setForecastStartDate(java.util.Date forecastStartDate) {
        _forecastingMaster.setForecastStartDate(forecastStartDate);
    }

    /**
    * Returns the forecasted sales percentage of this forecasting master.
    *
    * @return the forecasted sales percentage of this forecasting master
    */
    @Override
    public java.lang.String getForecastedSalesPercentage() {
        return _forecastingMaster.getForecastedSalesPercentage();
    }

    /**
    * Sets the forecasted sales percentage of this forecasting master.
    *
    * @param forecastedSalesPercentage the forecasted sales percentage of this forecasting master
    */
    @Override
    public void setForecastedSalesPercentage(
        java.lang.String forecastedSalesPercentage) {
        _forecastingMaster.setForecastedSalesPercentage(forecastedSalesPercentage);
    }

    /**
    * Returns the units of this forecasting master.
    *
    * @return the units of this forecasting master
    */
    @Override
    public double getUnits() {
        return _forecastingMaster.getUnits();
    }

    /**
    * Sets the units of this forecasting master.
    *
    * @param units the units of this forecasting master
    */
    @Override
    public void setUnits(double units) {
        _forecastingMaster.setUnits(units);
    }

    /**
    * Returns the dollars of this forecasting master.
    *
    * @return the dollars of this forecasting master
    */
    @Override
    public double getDollars() {
        return _forecastingMaster.getDollars();
    }

    /**
    * Sets the dollars of this forecasting master.
    *
    * @param dollars the dollars of this forecasting master
    */
    @Override
    public void setDollars(double dollars) {
        _forecastingMaster.setDollars(dollars);
    }

    /**
    * Returns the forecast month of this forecasting master.
    *
    * @return the forecast month of this forecasting master
    */
    @Override
    public java.lang.String getForecastMonth() {
        return _forecastingMaster.getForecastMonth();
    }

    /**
    * Sets the forecast month of this forecasting master.
    *
    * @param forecastMonth the forecast month of this forecasting master
    */
    @Override
    public void setForecastMonth(java.lang.String forecastMonth) {
        _forecastingMaster.setForecastMonth(forecastMonth);
    }

    /**
    * Returns the created by of this forecasting master.
    *
    * @return the created by of this forecasting master
    */
    @Override
    public int getCreatedBy() {
        return _forecastingMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this forecasting master.
    *
    * @param createdBy the created by of this forecasting master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _forecastingMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the segment of this forecasting master.
    *
    * @return the segment of this forecasting master
    */
    @Override
    public java.lang.String getSegment() {
        return _forecastingMaster.getSegment();
    }

    /**
    * Sets the segment of this forecasting master.
    *
    * @param segment the segment of this forecasting master
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _forecastingMaster.setSegment(segment);
    }

    /**
    * Returns the price of this forecasting master.
    *
    * @return the price of this forecasting master
    */
    @Override
    public double getPrice() {
        return _forecastingMaster.getPrice();
    }

    /**
    * Sets the price of this forecasting master.
    *
    * @param price the price of this forecasting master
    */
    @Override
    public void setPrice(double price) {
        _forecastingMaster.setPrice(price);
    }

    /**
    * Returns the actual sales percentage of this forecasting master.
    *
    * @return the actual sales percentage of this forecasting master
    */
    @Override
    public java.lang.String getActualSalesPercentage() {
        return _forecastingMaster.getActualSalesPercentage();
    }

    /**
    * Sets the actual sales percentage of this forecasting master.
    *
    * @param actualSalesPercentage the actual sales percentage of this forecasting master
    */
    @Override
    public void setActualSalesPercentage(java.lang.String actualSalesPercentage) {
        _forecastingMaster.setActualSalesPercentage(actualSalesPercentage);
    }

    /**
    * Returns the forecast year of this forecasting master.
    *
    * @return the forecast year of this forecasting master
    */
    @Override
    public java.lang.String getForecastYear() {
        return _forecastingMaster.getForecastYear();
    }

    /**
    * Sets the forecast year of this forecasting master.
    *
    * @param forecastYear the forecast year of this forecasting master
    */
    @Override
    public void setForecastYear(java.lang.String forecastYear) {
        _forecastingMaster.setForecastYear(forecastYear);
    }

    /**
    * Returns the forecast name of this forecasting master.
    *
    * @return the forecast name of this forecasting master
    */
    @Override
    public java.lang.String getForecastName() {
        return _forecastingMaster.getForecastName();
    }

    /**
    * Sets the forecast name of this forecasting master.
    *
    * @param forecastName the forecast name of this forecasting master
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _forecastingMaster.setForecastName(forecastName);
    }

    /**
    * Returns the forecast date of this forecasting master.
    *
    * @return the forecast date of this forecasting master
    */
    @Override
    public java.util.Date getForecastDate() {
        return _forecastingMaster.getForecastDate();
    }

    /**
    * Sets the forecast date of this forecasting master.
    *
    * @param forecastDate the forecast date of this forecasting master
    */
    @Override
    public void setForecastDate(java.util.Date forecastDate) {
        _forecastingMaster.setForecastDate(forecastDate);
    }

    /**
    * Returns the modified date of this forecasting master.
    *
    * @return the modified date of this forecasting master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _forecastingMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this forecasting master.
    *
    * @param modifiedDate the modified date of this forecasting master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _forecastingMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the brand of this forecasting master.
    *
    * @return the brand of this forecasting master
    */
    @Override
    public java.lang.String getBrand() {
        return _forecastingMaster.getBrand();
    }

    /**
    * Sets the brand of this forecasting master.
    *
    * @param brand the brand of this forecasting master
    */
    @Override
    public void setBrand(java.lang.String brand) {
        _forecastingMaster.setBrand(brand);
    }

    /**
    * Returns the forecast value of this forecasting master.
    *
    * @return the forecast value of this forecasting master
    */
    @Override
    public java.lang.String getForecastValue() {
        return _forecastingMaster.getForecastValue();
    }

    /**
    * Sets the forecast value of this forecasting master.
    *
    * @param forecastValue the forecast value of this forecasting master
    */
    @Override
    public void setForecastValue(java.lang.String forecastValue) {
        _forecastingMaster.setForecastValue(forecastValue);
    }

    /**
    * Returns the percentage estimate year of this forecasting master.
    *
    * @return the percentage estimate year of this forecasting master
    */
    @Override
    public int getPercentageEstimateYear() {
        return _forecastingMaster.getPercentageEstimateYear();
    }

    /**
    * Sets the percentage estimate year of this forecasting master.
    *
    * @param percentageEstimateYear the percentage estimate year of this forecasting master
    */
    @Override
    public void setPercentageEstimateYear(int percentageEstimateYear) {
        _forecastingMaster.setPercentageEstimateYear(percentageEstimateYear);
    }

    /**
    * Returns the record lock status of this forecasting master.
    *
    * @return the record lock status of this forecasting master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _forecastingMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this forecasting master is record lock status.
    *
    * @return <code>true</code> if this forecasting master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _forecastingMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this forecasting master is record lock status.
    *
    * @param recordLockStatus the record lock status of this forecasting master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _forecastingMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the source of this forecasting master.
    *
    * @return the source of this forecasting master
    */
    @Override
    public java.lang.String getSource() {
        return _forecastingMaster.getSource();
    }

    /**
    * Sets the source of this forecasting master.
    *
    * @param source the source of this forecasting master
    */
    @Override
    public void setSource(java.lang.String source) {
        _forecastingMaster.setSource(source);
    }

    /**
    * Returns the forecast master sid of this forecasting master.
    *
    * @return the forecast master sid of this forecasting master
    */
    @Override
    public int getForecastMasterSid() {
        return _forecastingMaster.getForecastMasterSid();
    }

    /**
    * Sets the forecast master sid of this forecasting master.
    *
    * @param forecastMasterSid the forecast master sid of this forecasting master
    */
    @Override
    public void setForecastMasterSid(int forecastMasterSid) {
        _forecastingMaster.setForecastMasterSid(forecastMasterSid);
    }

    /**
    * Returns the forecasted sales percent month of this forecasting master.
    *
    * @return the forecasted sales percent month of this forecasting master
    */
    @Override
    public int getForecastedSalesPercentMonth() {
        return _forecastingMaster.getForecastedSalesPercentMonth();
    }

    /**
    * Sets the forecasted sales percent month of this forecasting master.
    *
    * @param forecastedSalesPercentMonth the forecasted sales percent month of this forecasting master
    */
    @Override
    public void setForecastedSalesPercentMonth(int forecastedSalesPercentMonth) {
        _forecastingMaster.setForecastedSalesPercentMonth(forecastedSalesPercentMonth);
    }

    /**
    * Returns the inbound status of this forecasting master.
    *
    * @return the inbound status of this forecasting master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _forecastingMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this forecasting master.
    *
    * @param inboundStatus the inbound status of this forecasting master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _forecastingMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _forecastingMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _forecastingMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _forecastingMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _forecastingMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _forecastingMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _forecastingMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _forecastingMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _forecastingMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _forecastingMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _forecastingMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _forecastingMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ForecastingMasterWrapper((ForecastingMaster) _forecastingMaster.clone());
    }

    @Override
    public int compareTo(ForecastingMaster forecastingMaster) {
        return _forecastingMaster.compareTo(forecastingMaster);
    }

    @Override
    public int hashCode() {
        return _forecastingMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ForecastingMaster> toCacheModel() {
        return _forecastingMaster.toCacheModel();
    }

    @Override
    public ForecastingMaster toEscapedModel() {
        return new ForecastingMasterWrapper(_forecastingMaster.toEscapedModel());
    }

    @Override
    public ForecastingMaster toUnescapedModel() {
        return new ForecastingMasterWrapper(_forecastingMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _forecastingMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _forecastingMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _forecastingMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ForecastingMasterWrapper)) {
            return false;
        }

        ForecastingMasterWrapper forecastingMasterWrapper = (ForecastingMasterWrapper) obj;

        if (Validator.equals(_forecastingMaster,
                    forecastingMasterWrapper._forecastingMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ForecastingMaster getWrappedForecastingMaster() {
        return _forecastingMaster;
    }

    @Override
    public ForecastingMaster getWrappedModel() {
        return _forecastingMaster;
    }

    @Override
    public void resetOriginalValues() {
        _forecastingMaster.resetOriginalValues();
    }
}
