package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldForecastSales}.
 * </p>
 *
 * @author
 * @see IvldForecastSales
 * @generated
 */
public class IvldForecastSalesWrapper implements IvldForecastSales,
    ModelWrapper<IvldForecastSales> {
    private IvldForecastSales _ivldForecastSales;

    public IvldForecastSalesWrapper(IvldForecastSales ivldForecastSales) {
        _ivldForecastSales = ivldForecastSales;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldForecastSales.class;
    }

    @Override
    public String getModelClassName() {
        return IvldForecastSales.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("price", getPrice());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("forecastDate", getForecastDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("forecastValue", getForecastValue());
        attributes.put("forecastIntfid", getForecastIntfid());
        attributes.put("dollars", getDollars());
        attributes.put("ndc", getNdc());
        attributes.put("actualSalesPercentage", getActualSalesPercentage());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("actualSalesPercentageMonth",
            getActualSalesPercentageMonth());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("percentageEstimate", getPercentageEstimate());
        attributes.put("percentageEstimateYear", getPercentageEstimateYear());
        attributes.put("units", getUnits());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("forecastStartDate", getForecastStartDate());
        attributes.put("forecastValueType", getForecastValueType());
        attributes.put("forecastedSalesPercentMonth",
            getForecastedSalesPercentMonth());
        attributes.put("country", getCountry());
        attributes.put("product", getProduct());
        attributes.put("batchId", getBatchId());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("ivldForecastSalesSid", getIvldForecastSalesSid());
        attributes.put("errorField", getErrorField());
        attributes.put("segment", getSegment());
        attributes.put("brand", getBrand());
        attributes.put("forecastedSalesPercentage",
            getForecastedSalesPercentage());
        attributes.put("forecastName", getForecastName());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String price = (String) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String forecastDate = (String) attributes.get("forecastDate");

        if (forecastDate != null) {
            setForecastDate(forecastDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String forecastValue = (String) attributes.get("forecastValue");

        if (forecastValue != null) {
            setForecastValue(forecastValue);
        }

        String forecastIntfid = (String) attributes.get("forecastIntfid");

        if (forecastIntfid != null) {
            setForecastIntfid(forecastIntfid);
        }

        String dollars = (String) attributes.get("dollars");

        if (dollars != null) {
            setDollars(dollars);
        }

        String ndc = (String) attributes.get("ndc");

        if (ndc != null) {
            setNdc(ndc);
        }

        String actualSalesPercentage = (String) attributes.get(
                "actualSalesPercentage");

        if (actualSalesPercentage != null) {
            setActualSalesPercentage(actualSalesPercentage);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String actualSalesPercentageMonth = (String) attributes.get(
                "actualSalesPercentageMonth");

        if (actualSalesPercentageMonth != null) {
            setActualSalesPercentageMonth(actualSalesPercentageMonth);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String percentageEstimate = (String) attributes.get(
                "percentageEstimate");

        if (percentageEstimate != null) {
            setPercentageEstimate(percentageEstimate);
        }

        String percentageEstimateYear = (String) attributes.get(
                "percentageEstimateYear");

        if (percentageEstimateYear != null) {
            setPercentageEstimateYear(percentageEstimateYear);
        }

        String units = (String) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String forecastStartDate = (String) attributes.get("forecastStartDate");

        if (forecastStartDate != null) {
            setForecastStartDate(forecastStartDate);
        }

        String forecastValueType = (String) attributes.get("forecastValueType");

        if (forecastValueType != null) {
            setForecastValueType(forecastValueType);
        }

        String forecastedSalesPercentMonth = (String) attributes.get(
                "forecastedSalesPercentMonth");

        if (forecastedSalesPercentMonth != null) {
            setForecastedSalesPercentMonth(forecastedSalesPercentMonth);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String product = (String) attributes.get("product");

        if (product != null) {
            setProduct(product);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        Integer ivldForecastSalesSid = (Integer) attributes.get(
                "ivldForecastSalesSid");

        if (ivldForecastSalesSid != null) {
            setIvldForecastSalesSid(ivldForecastSalesSid);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String forecastedSalesPercentage = (String) attributes.get(
                "forecastedSalesPercentage");

        if (forecastedSalesPercentage != null) {
            setForecastedSalesPercentage(forecastedSalesPercentage);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld forecast sales.
    *
    * @return the primary key of this ivld forecast sales
    */
    @Override
    public int getPrimaryKey() {
        return _ivldForecastSales.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld forecast sales.
    *
    * @param primaryKey the primary key of this ivld forecast sales
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldForecastSales.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the price of this ivld forecast sales.
    *
    * @return the price of this ivld forecast sales
    */
    @Override
    public java.lang.String getPrice() {
        return _ivldForecastSales.getPrice();
    }

    /**
    * Sets the price of this ivld forecast sales.
    *
    * @param price the price of this ivld forecast sales
    */
    @Override
    public void setPrice(java.lang.String price) {
        _ivldForecastSales.setPrice(price);
    }

    /**
    * Returns the forecast year of this ivld forecast sales.
    *
    * @return the forecast year of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastYear() {
        return _ivldForecastSales.getForecastYear();
    }

    /**
    * Sets the forecast year of this ivld forecast sales.
    *
    * @param forecastYear the forecast year of this ivld forecast sales
    */
    @Override
    public void setForecastYear(java.lang.String forecastYear) {
        _ivldForecastSales.setForecastYear(forecastYear);
    }

    /**
    * Returns the forecast date of this ivld forecast sales.
    *
    * @return the forecast date of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastDate() {
        return _ivldForecastSales.getForecastDate();
    }

    /**
    * Sets the forecast date of this ivld forecast sales.
    *
    * @param forecastDate the forecast date of this ivld forecast sales
    */
    @Override
    public void setForecastDate(java.lang.String forecastDate) {
        _ivldForecastSales.setForecastDate(forecastDate);
    }

    /**
    * Returns the modified date of this ivld forecast sales.
    *
    * @return the modified date of this ivld forecast sales
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldForecastSales.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld forecast sales.
    *
    * @param modifiedDate the modified date of this ivld forecast sales
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldForecastSales.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the forecast value of this ivld forecast sales.
    *
    * @return the forecast value of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastValue() {
        return _ivldForecastSales.getForecastValue();
    }

    /**
    * Sets the forecast value of this ivld forecast sales.
    *
    * @param forecastValue the forecast value of this ivld forecast sales
    */
    @Override
    public void setForecastValue(java.lang.String forecastValue) {
        _ivldForecastSales.setForecastValue(forecastValue);
    }

    /**
    * Returns the forecast intfid of this ivld forecast sales.
    *
    * @return the forecast intfid of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastIntfid() {
        return _ivldForecastSales.getForecastIntfid();
    }

    /**
    * Sets the forecast intfid of this ivld forecast sales.
    *
    * @param forecastIntfid the forecast intfid of this ivld forecast sales
    */
    @Override
    public void setForecastIntfid(java.lang.String forecastIntfid) {
        _ivldForecastSales.setForecastIntfid(forecastIntfid);
    }

    /**
    * Returns the dollars of this ivld forecast sales.
    *
    * @return the dollars of this ivld forecast sales
    */
    @Override
    public java.lang.String getDollars() {
        return _ivldForecastSales.getDollars();
    }

    /**
    * Sets the dollars of this ivld forecast sales.
    *
    * @param dollars the dollars of this ivld forecast sales
    */
    @Override
    public void setDollars(java.lang.String dollars) {
        _ivldForecastSales.setDollars(dollars);
    }

    /**
    * Returns the ndc of this ivld forecast sales.
    *
    * @return the ndc of this ivld forecast sales
    */
    @Override
    public java.lang.String getNdc() {
        return _ivldForecastSales.getNdc();
    }

    /**
    * Sets the ndc of this ivld forecast sales.
    *
    * @param ndc the ndc of this ivld forecast sales
    */
    @Override
    public void setNdc(java.lang.String ndc) {
        _ivldForecastSales.setNdc(ndc);
    }

    /**
    * Returns the actual sales percentage of this ivld forecast sales.
    *
    * @return the actual sales percentage of this ivld forecast sales
    */
    @Override
    public java.lang.String getActualSalesPercentage() {
        return _ivldForecastSales.getActualSalesPercentage();
    }

    /**
    * Sets the actual sales percentage of this ivld forecast sales.
    *
    * @param actualSalesPercentage the actual sales percentage of this ivld forecast sales
    */
    @Override
    public void setActualSalesPercentage(java.lang.String actualSalesPercentage) {
        _ivldForecastSales.setActualSalesPercentage(actualSalesPercentage);
    }

    /**
    * Returns the source of this ivld forecast sales.
    *
    * @return the source of this ivld forecast sales
    */
    @Override
    public java.lang.String getSource() {
        return _ivldForecastSales.getSource();
    }

    /**
    * Sets the source of this ivld forecast sales.
    *
    * @param source the source of this ivld forecast sales
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldForecastSales.setSource(source);
    }

    /**
    * Returns the created date of this ivld forecast sales.
    *
    * @return the created date of this ivld forecast sales
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldForecastSales.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld forecast sales.
    *
    * @param createdDate the created date of this ivld forecast sales
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldForecastSales.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ivld forecast sales.
    *
    * @return the created by of this ivld forecast sales
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldForecastSales.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld forecast sales.
    *
    * @param createdBy the created by of this ivld forecast sales
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldForecastSales.setCreatedBy(createdBy);
    }

    /**
    * Returns the add chg del indicator of this ivld forecast sales.
    *
    * @return the add chg del indicator of this ivld forecast sales
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldForecastSales.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld forecast sales.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld forecast sales
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldForecastSales.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the actual sales percentage month of this ivld forecast sales.
    *
    * @return the actual sales percentage month of this ivld forecast sales
    */
    @Override
    public java.lang.String getActualSalesPercentageMonth() {
        return _ivldForecastSales.getActualSalesPercentageMonth();
    }

    /**
    * Sets the actual sales percentage month of this ivld forecast sales.
    *
    * @param actualSalesPercentageMonth the actual sales percentage month of this ivld forecast sales
    */
    @Override
    public void setActualSalesPercentageMonth(
        java.lang.String actualSalesPercentageMonth) {
        _ivldForecastSales.setActualSalesPercentageMonth(actualSalesPercentageMonth);
    }

    /**
    * Returns the error code of this ivld forecast sales.
    *
    * @return the error code of this ivld forecast sales
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldForecastSales.getErrorCode();
    }

    /**
    * Sets the error code of this ivld forecast sales.
    *
    * @param errorCode the error code of this ivld forecast sales
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldForecastSales.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld forecast sales.
    *
    * @return the intf inserted date of this ivld forecast sales
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldForecastSales.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld forecast sales.
    *
    * @param intfInsertedDate the intf inserted date of this ivld forecast sales
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldForecastSales.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld forecast sales.
    *
    * @return the modified by of this ivld forecast sales
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldForecastSales.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld forecast sales.
    *
    * @param modifiedBy the modified by of this ivld forecast sales
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldForecastSales.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the reprocessed flag of this ivld forecast sales.
    *
    * @return the reprocessed flag of this ivld forecast sales
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldForecastSales.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld forecast sales.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld forecast sales
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldForecastSales.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the percentage estimate of this ivld forecast sales.
    *
    * @return the percentage estimate of this ivld forecast sales
    */
    @Override
    public java.lang.String getPercentageEstimate() {
        return _ivldForecastSales.getPercentageEstimate();
    }

    /**
    * Sets the percentage estimate of this ivld forecast sales.
    *
    * @param percentageEstimate the percentage estimate of this ivld forecast sales
    */
    @Override
    public void setPercentageEstimate(java.lang.String percentageEstimate) {
        _ivldForecastSales.setPercentageEstimate(percentageEstimate);
    }

    /**
    * Returns the percentage estimate year of this ivld forecast sales.
    *
    * @return the percentage estimate year of this ivld forecast sales
    */
    @Override
    public java.lang.String getPercentageEstimateYear() {
        return _ivldForecastSales.getPercentageEstimateYear();
    }

    /**
    * Sets the percentage estimate year of this ivld forecast sales.
    *
    * @param percentageEstimateYear the percentage estimate year of this ivld forecast sales
    */
    @Override
    public void setPercentageEstimateYear(
        java.lang.String percentageEstimateYear) {
        _ivldForecastSales.setPercentageEstimateYear(percentageEstimateYear);
    }

    /**
    * Returns the units of this ivld forecast sales.
    *
    * @return the units of this ivld forecast sales
    */
    @Override
    public java.lang.String getUnits() {
        return _ivldForecastSales.getUnits();
    }

    /**
    * Sets the units of this ivld forecast sales.
    *
    * @param units the units of this ivld forecast sales
    */
    @Override
    public void setUnits(java.lang.String units) {
        _ivldForecastSales.setUnits(units);
    }

    /**
    * Returns the reason for failure of this ivld forecast sales.
    *
    * @return the reason for failure of this ivld forecast sales
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldForecastSales.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld forecast sales.
    *
    * @param reasonForFailure the reason for failure of this ivld forecast sales
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldForecastSales.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the forecast start date of this ivld forecast sales.
    *
    * @return the forecast start date of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastStartDate() {
        return _ivldForecastSales.getForecastStartDate();
    }

    /**
    * Sets the forecast start date of this ivld forecast sales.
    *
    * @param forecastStartDate the forecast start date of this ivld forecast sales
    */
    @Override
    public void setForecastStartDate(java.lang.String forecastStartDate) {
        _ivldForecastSales.setForecastStartDate(forecastStartDate);
    }

    /**
    * Returns the forecast value type of this ivld forecast sales.
    *
    * @return the forecast value type of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastValueType() {
        return _ivldForecastSales.getForecastValueType();
    }

    /**
    * Sets the forecast value type of this ivld forecast sales.
    *
    * @param forecastValueType the forecast value type of this ivld forecast sales
    */
    @Override
    public void setForecastValueType(java.lang.String forecastValueType) {
        _ivldForecastSales.setForecastValueType(forecastValueType);
    }

    /**
    * Returns the forecasted sales percent month of this ivld forecast sales.
    *
    * @return the forecasted sales percent month of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastedSalesPercentMonth() {
        return _ivldForecastSales.getForecastedSalesPercentMonth();
    }

    /**
    * Sets the forecasted sales percent month of this ivld forecast sales.
    *
    * @param forecastedSalesPercentMonth the forecasted sales percent month of this ivld forecast sales
    */
    @Override
    public void setForecastedSalesPercentMonth(
        java.lang.String forecastedSalesPercentMonth) {
        _ivldForecastSales.setForecastedSalesPercentMonth(forecastedSalesPercentMonth);
    }

    /**
    * Returns the country of this ivld forecast sales.
    *
    * @return the country of this ivld forecast sales
    */
    @Override
    public java.lang.String getCountry() {
        return _ivldForecastSales.getCountry();
    }

    /**
    * Sets the country of this ivld forecast sales.
    *
    * @param country the country of this ivld forecast sales
    */
    @Override
    public void setCountry(java.lang.String country) {
        _ivldForecastSales.setCountry(country);
    }

    /**
    * Returns the product of this ivld forecast sales.
    *
    * @return the product of this ivld forecast sales
    */
    @Override
    public java.lang.String getProduct() {
        return _ivldForecastSales.getProduct();
    }

    /**
    * Sets the product of this ivld forecast sales.
    *
    * @param product the product of this ivld forecast sales
    */
    @Override
    public void setProduct(java.lang.String product) {
        _ivldForecastSales.setProduct(product);
    }

    /**
    * Returns the batch ID of this ivld forecast sales.
    *
    * @return the batch ID of this ivld forecast sales
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldForecastSales.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld forecast sales.
    *
    * @param batchId the batch ID of this ivld forecast sales
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldForecastSales.setBatchId(batchId);
    }

    /**
    * Returns the forecast ver of this ivld forecast sales.
    *
    * @return the forecast ver of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastVer() {
        return _ivldForecastSales.getForecastVer();
    }

    /**
    * Sets the forecast ver of this ivld forecast sales.
    *
    * @param forecastVer the forecast ver of this ivld forecast sales
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _ivldForecastSales.setForecastVer(forecastVer);
    }

    /**
    * Returns the forecast month of this ivld forecast sales.
    *
    * @return the forecast month of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastMonth() {
        return _ivldForecastSales.getForecastMonth();
    }

    /**
    * Sets the forecast month of this ivld forecast sales.
    *
    * @param forecastMonth the forecast month of this ivld forecast sales
    */
    @Override
    public void setForecastMonth(java.lang.String forecastMonth) {
        _ivldForecastSales.setForecastMonth(forecastMonth);
    }

    /**
    * Returns the ivld forecast sales sid of this ivld forecast sales.
    *
    * @return the ivld forecast sales sid of this ivld forecast sales
    */
    @Override
    public int getIvldForecastSalesSid() {
        return _ivldForecastSales.getIvldForecastSalesSid();
    }

    /**
    * Sets the ivld forecast sales sid of this ivld forecast sales.
    *
    * @param ivldForecastSalesSid the ivld forecast sales sid of this ivld forecast sales
    */
    @Override
    public void setIvldForecastSalesSid(int ivldForecastSalesSid) {
        _ivldForecastSales.setIvldForecastSalesSid(ivldForecastSalesSid);
    }

    /**
    * Returns the error field of this ivld forecast sales.
    *
    * @return the error field of this ivld forecast sales
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldForecastSales.getErrorField();
    }

    /**
    * Sets the error field of this ivld forecast sales.
    *
    * @param errorField the error field of this ivld forecast sales
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldForecastSales.setErrorField(errorField);
    }

    /**
    * Returns the segment of this ivld forecast sales.
    *
    * @return the segment of this ivld forecast sales
    */
    @Override
    public java.lang.String getSegment() {
        return _ivldForecastSales.getSegment();
    }

    /**
    * Sets the segment of this ivld forecast sales.
    *
    * @param segment the segment of this ivld forecast sales
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _ivldForecastSales.setSegment(segment);
    }

    /**
    * Returns the brand of this ivld forecast sales.
    *
    * @return the brand of this ivld forecast sales
    */
    @Override
    public java.lang.String getBrand() {
        return _ivldForecastSales.getBrand();
    }

    /**
    * Sets the brand of this ivld forecast sales.
    *
    * @param brand the brand of this ivld forecast sales
    */
    @Override
    public void setBrand(java.lang.String brand) {
        _ivldForecastSales.setBrand(brand);
    }

    /**
    * Returns the forecasted sales percentage of this ivld forecast sales.
    *
    * @return the forecasted sales percentage of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastedSalesPercentage() {
        return _ivldForecastSales.getForecastedSalesPercentage();
    }

    /**
    * Sets the forecasted sales percentage of this ivld forecast sales.
    *
    * @param forecastedSalesPercentage the forecasted sales percentage of this ivld forecast sales
    */
    @Override
    public void setForecastedSalesPercentage(
        java.lang.String forecastedSalesPercentage) {
        _ivldForecastSales.setForecastedSalesPercentage(forecastedSalesPercentage);
    }

    /**
    * Returns the forecast name of this ivld forecast sales.
    *
    * @return the forecast name of this ivld forecast sales
    */
    @Override
    public java.lang.String getForecastName() {
        return _ivldForecastSales.getForecastName();
    }

    /**
    * Sets the forecast name of this ivld forecast sales.
    *
    * @param forecastName the forecast name of this ivld forecast sales
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _ivldForecastSales.setForecastName(forecastName);
    }

    /**
    * Returns the check record of this ivld forecast sales.
    *
    * @return the check record of this ivld forecast sales
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldForecastSales.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld forecast sales is check record.
    *
    * @return <code>true</code> if this ivld forecast sales is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldForecastSales.isCheckRecord();
    }

    /**
    * Sets whether this ivld forecast sales is check record.
    *
    * @param checkRecord the check record of this ivld forecast sales
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldForecastSales.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldForecastSales.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldForecastSales.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldForecastSales.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldForecastSales.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldForecastSales.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldForecastSales.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldForecastSales.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldForecastSales.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldForecastSales.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldForecastSales.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldForecastSales.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldForecastSalesWrapper((IvldForecastSales) _ivldForecastSales.clone());
    }

    @Override
    public int compareTo(IvldForecastSales ivldForecastSales) {
        return _ivldForecastSales.compareTo(ivldForecastSales);
    }

    @Override
    public int hashCode() {
        return _ivldForecastSales.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldForecastSales> toCacheModel() {
        return _ivldForecastSales.toCacheModel();
    }

    @Override
    public IvldForecastSales toEscapedModel() {
        return new IvldForecastSalesWrapper(_ivldForecastSales.toEscapedModel());
    }

    @Override
    public IvldForecastSales toUnescapedModel() {
        return new IvldForecastSalesWrapper(_ivldForecastSales.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldForecastSales.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldForecastSales.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldForecastSales.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldForecastSalesWrapper)) {
            return false;
        }

        IvldForecastSalesWrapper ivldForecastSalesWrapper = (IvldForecastSalesWrapper) obj;

        if (Validator.equals(_ivldForecastSales,
                    ivldForecastSalesWrapper._ivldForecastSales)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldForecastSales getWrappedIvldForecastSales() {
        return _ivldForecastSales;
    }

    @Override
    public IvldForecastSales getWrappedModel() {
        return _ivldForecastSales;
    }

    @Override
    public void resetOriginalValues() {
        _ivldForecastSales.resetOriginalValues();
    }
}
