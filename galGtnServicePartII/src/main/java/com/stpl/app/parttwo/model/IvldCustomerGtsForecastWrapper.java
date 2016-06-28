package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldCustomerGtsForecast}.
 * </p>
 *
 * @author
 * @see IvldCustomerGtsForecast
 * @generated
 */
public class IvldCustomerGtsForecastWrapper implements IvldCustomerGtsForecast,
    ModelWrapper<IvldCustomerGtsForecast> {
    private IvldCustomerGtsForecast _ivldCustomerGtsForecast;

    public IvldCustomerGtsForecastWrapper(
        IvldCustomerGtsForecast ivldCustomerGtsForecast) {
        _ivldCustomerGtsForecast = ivldCustomerGtsForecast;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCustomerGtsForecast.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCustomerGtsForecast.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("price", getPrice());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("deductionAmount", getDeductionAmount());
        attributes.put("ivldCustomerGtsForecastSid",
            getIvldCustomerGtsForecastSid());
        attributes.put("deductionId", getDeductionId());
        attributes.put("forecastDate", getForecastDate());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("salesAmount", getSalesAmount());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("deductionType", getDeductionType());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("units", getUnits());
        attributes.put("deductionRate", getDeductionRate());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("forecastValueType", getForecastValueType());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("adjustmentCode", getAdjustmentCode());
        attributes.put("deductionProgramType", getDeductionProgramType());
        attributes.put("customerGtsForecastIntfId",
            getCustomerGtsForecastIntfId());
        attributes.put("salesInclusion", getSalesInclusion());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("priceType", getPriceType());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("deductionInclusion", getDeductionInclusion());
        attributes.put("errorField", getErrorField());
        attributes.put("segment", getSegment());
        attributes.put("brand", getBrand());
        attributes.put("forecastName", getForecastName());

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

        String deductionAmount = (String) attributes.get("deductionAmount");

        if (deductionAmount != null) {
            setDeductionAmount(deductionAmount);
        }

        Integer ivldCustomerGtsForecastSid = (Integer) attributes.get(
                "ivldCustomerGtsForecastSid");

        if (ivldCustomerGtsForecastSid != null) {
            setIvldCustomerGtsForecastSid(ivldCustomerGtsForecastSid);
        }

        String deductionId = (String) attributes.get("deductionId");

        if (deductionId != null) {
            setDeductionId(deductionId);
        }

        Date forecastDate = (Date) attributes.get("forecastDate");

        if (forecastDate != null) {
            setForecastDate(forecastDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
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

        String salesAmount = (String) attributes.get("salesAmount");

        if (salesAmount != null) {
            setSalesAmount(salesAmount);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String units = (String) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        String deductionRate = (String) attributes.get("deductionRate");

        if (deductionRate != null) {
            setDeductionRate(deductionRate);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String forecastValueType = (String) attributes.get("forecastValueType");

        if (forecastValueType != null) {
            setForecastValueType(forecastValueType);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }

        String adjustmentCode = (String) attributes.get("adjustmentCode");

        if (adjustmentCode != null) {
            setAdjustmentCode(adjustmentCode);
        }

        String deductionProgramType = (String) attributes.get(
                "deductionProgramType");

        if (deductionProgramType != null) {
            setDeductionProgramType(deductionProgramType);
        }

        Integer customerGtsForecastIntfId = (Integer) attributes.get(
                "customerGtsForecastIntfId");

        if (customerGtsForecastIntfId != null) {
            setCustomerGtsForecastIntfId(customerGtsForecastIntfId);
        }

        String salesInclusion = (String) attributes.get("salesInclusion");

        if (salesInclusion != null) {
            setSalesInclusion(salesInclusion);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
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

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }
    }

    /**
    * Returns the primary key of this ivld customer gts forecast.
    *
    * @return the primary key of this ivld customer gts forecast
    */
    @Override
    public int getPrimaryKey() {
        return _ivldCustomerGtsForecast.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld customer gts forecast.
    *
    * @param primaryKey the primary key of this ivld customer gts forecast
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldCustomerGtsForecast.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the price of this ivld customer gts forecast.
    *
    * @return the price of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getPrice() {
        return _ivldCustomerGtsForecast.getPrice();
    }

    /**
    * Sets the price of this ivld customer gts forecast.
    *
    * @param price the price of this ivld customer gts forecast
    */
    @Override
    public void setPrice(java.lang.String price) {
        _ivldCustomerGtsForecast.setPrice(price);
    }

    /**
    * Returns the forecast year of this ivld customer gts forecast.
    *
    * @return the forecast year of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getForecastYear() {
        return _ivldCustomerGtsForecast.getForecastYear();
    }

    /**
    * Sets the forecast year of this ivld customer gts forecast.
    *
    * @param forecastYear the forecast year of this ivld customer gts forecast
    */
    @Override
    public void setForecastYear(java.lang.String forecastYear) {
        _ivldCustomerGtsForecast.setForecastYear(forecastYear);
    }

    /**
    * Returns the deduction amount of this ivld customer gts forecast.
    *
    * @return the deduction amount of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getDeductionAmount() {
        return _ivldCustomerGtsForecast.getDeductionAmount();
    }

    /**
    * Sets the deduction amount of this ivld customer gts forecast.
    *
    * @param deductionAmount the deduction amount of this ivld customer gts forecast
    */
    @Override
    public void setDeductionAmount(java.lang.String deductionAmount) {
        _ivldCustomerGtsForecast.setDeductionAmount(deductionAmount);
    }

    /**
    * Returns the ivld customer gts forecast sid of this ivld customer gts forecast.
    *
    * @return the ivld customer gts forecast sid of this ivld customer gts forecast
    */
    @Override
    public int getIvldCustomerGtsForecastSid() {
        return _ivldCustomerGtsForecast.getIvldCustomerGtsForecastSid();
    }

    /**
    * Sets the ivld customer gts forecast sid of this ivld customer gts forecast.
    *
    * @param ivldCustomerGtsForecastSid the ivld customer gts forecast sid of this ivld customer gts forecast
    */
    @Override
    public void setIvldCustomerGtsForecastSid(int ivldCustomerGtsForecastSid) {
        _ivldCustomerGtsForecast.setIvldCustomerGtsForecastSid(ivldCustomerGtsForecastSid);
    }

    /**
    * Returns the deduction ID of this ivld customer gts forecast.
    *
    * @return the deduction ID of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getDeductionId() {
        return _ivldCustomerGtsForecast.getDeductionId();
    }

    /**
    * Sets the deduction ID of this ivld customer gts forecast.
    *
    * @param deductionId the deduction ID of this ivld customer gts forecast
    */
    @Override
    public void setDeductionId(java.lang.String deductionId) {
        _ivldCustomerGtsForecast.setDeductionId(deductionId);
    }

    /**
    * Returns the forecast date of this ivld customer gts forecast.
    *
    * @return the forecast date of this ivld customer gts forecast
    */
    @Override
    public java.util.Date getForecastDate() {
        return _ivldCustomerGtsForecast.getForecastDate();
    }

    /**
    * Sets the forecast date of this ivld customer gts forecast.
    *
    * @param forecastDate the forecast date of this ivld customer gts forecast
    */
    @Override
    public void setForecastDate(java.util.Date forecastDate) {
        _ivldCustomerGtsForecast.setForecastDate(forecastDate);
    }

    /**
    * Returns the item ID of this ivld customer gts forecast.
    *
    * @return the item ID of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldCustomerGtsForecast.getItemId();
    }

    /**
    * Sets the item ID of this ivld customer gts forecast.
    *
    * @param itemId the item ID of this ivld customer gts forecast
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldCustomerGtsForecast.setItemId(itemId);
    }

    /**
    * Returns the modified date of this ivld customer gts forecast.
    *
    * @return the modified date of this ivld customer gts forecast
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldCustomerGtsForecast.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld customer gts forecast.
    *
    * @param modifiedDate the modified date of this ivld customer gts forecast
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldCustomerGtsForecast.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the source of this ivld customer gts forecast.
    *
    * @return the source of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getSource() {
        return _ivldCustomerGtsForecast.getSource();
    }

    /**
    * Sets the source of this ivld customer gts forecast.
    *
    * @param source the source of this ivld customer gts forecast
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldCustomerGtsForecast.setSource(source);
    }

    /**
    * Returns the created date of this ivld customer gts forecast.
    *
    * @return the created date of this ivld customer gts forecast
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldCustomerGtsForecast.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld customer gts forecast.
    *
    * @param createdDate the created date of this ivld customer gts forecast
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldCustomerGtsForecast.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ivld customer gts forecast.
    *
    * @return the created by of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldCustomerGtsForecast.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld customer gts forecast.
    *
    * @param createdBy the created by of this ivld customer gts forecast
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldCustomerGtsForecast.setCreatedBy(createdBy);
    }

    /**
    * Returns the add chg del indicator of this ivld customer gts forecast.
    *
    * @return the add chg del indicator of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldCustomerGtsForecast.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld customer gts forecast.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld customer gts forecast
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldCustomerGtsForecast.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the error code of this ivld customer gts forecast.
    *
    * @return the error code of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldCustomerGtsForecast.getErrorCode();
    }

    /**
    * Sets the error code of this ivld customer gts forecast.
    *
    * @param errorCode the error code of this ivld customer gts forecast
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldCustomerGtsForecast.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld customer gts forecast.
    *
    * @return the intf inserted date of this ivld customer gts forecast
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldCustomerGtsForecast.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld customer gts forecast.
    *
    * @param intfInsertedDate the intf inserted date of this ivld customer gts forecast
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldCustomerGtsForecast.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld customer gts forecast.
    *
    * @return the modified by of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldCustomerGtsForecast.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld customer gts forecast.
    *
    * @param modifiedBy the modified by of this ivld customer gts forecast
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldCustomerGtsForecast.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the sales amount of this ivld customer gts forecast.
    *
    * @return the sales amount of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getSalesAmount() {
        return _ivldCustomerGtsForecast.getSalesAmount();
    }

    /**
    * Sets the sales amount of this ivld customer gts forecast.
    *
    * @param salesAmount the sales amount of this ivld customer gts forecast
    */
    @Override
    public void setSalesAmount(java.lang.String salesAmount) {
        _ivldCustomerGtsForecast.setSalesAmount(salesAmount);
    }

    /**
    * Returns the reprocessed flag of this ivld customer gts forecast.
    *
    * @return the reprocessed flag of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldCustomerGtsForecast.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld customer gts forecast.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld customer gts forecast
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldCustomerGtsForecast.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the udc6 of this ivld customer gts forecast.
    *
    * @return the udc6 of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getUdc6() {
        return _ivldCustomerGtsForecast.getUdc6();
    }

    /**
    * Sets the udc6 of this ivld customer gts forecast.
    *
    * @param udc6 the udc6 of this ivld customer gts forecast
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _ivldCustomerGtsForecast.setUdc6(udc6);
    }

    /**
    * Returns the udc5 of this ivld customer gts forecast.
    *
    * @return the udc5 of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getUdc5() {
        return _ivldCustomerGtsForecast.getUdc5();
    }

    /**
    * Sets the udc5 of this ivld customer gts forecast.
    *
    * @param udc5 the udc5 of this ivld customer gts forecast
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _ivldCustomerGtsForecast.setUdc5(udc5);
    }

    /**
    * Returns the deduction type of this ivld customer gts forecast.
    *
    * @return the deduction type of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getDeductionType() {
        return _ivldCustomerGtsForecast.getDeductionType();
    }

    /**
    * Sets the deduction type of this ivld customer gts forecast.
    *
    * @param deductionType the deduction type of this ivld customer gts forecast
    */
    @Override
    public void setDeductionType(java.lang.String deductionType) {
        _ivldCustomerGtsForecast.setDeductionType(deductionType);
    }

    /**
    * Returns the udc4 of this ivld customer gts forecast.
    *
    * @return the udc4 of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getUdc4() {
        return _ivldCustomerGtsForecast.getUdc4();
    }

    /**
    * Sets the udc4 of this ivld customer gts forecast.
    *
    * @param udc4 the udc4 of this ivld customer gts forecast
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _ivldCustomerGtsForecast.setUdc4(udc4);
    }

    /**
    * Returns the udc1 of this ivld customer gts forecast.
    *
    * @return the udc1 of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getUdc1() {
        return _ivldCustomerGtsForecast.getUdc1();
    }

    /**
    * Sets the udc1 of this ivld customer gts forecast.
    *
    * @param udc1 the udc1 of this ivld customer gts forecast
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _ivldCustomerGtsForecast.setUdc1(udc1);
    }

    /**
    * Returns the units of this ivld customer gts forecast.
    *
    * @return the units of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getUnits() {
        return _ivldCustomerGtsForecast.getUnits();
    }

    /**
    * Sets the units of this ivld customer gts forecast.
    *
    * @param units the units of this ivld customer gts forecast
    */
    @Override
    public void setUnits(java.lang.String units) {
        _ivldCustomerGtsForecast.setUnits(units);
    }

    /**
    * Returns the deduction rate of this ivld customer gts forecast.
    *
    * @return the deduction rate of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getDeductionRate() {
        return _ivldCustomerGtsForecast.getDeductionRate();
    }

    /**
    * Sets the deduction rate of this ivld customer gts forecast.
    *
    * @param deductionRate the deduction rate of this ivld customer gts forecast
    */
    @Override
    public void setDeductionRate(java.lang.String deductionRate) {
        _ivldCustomerGtsForecast.setDeductionRate(deductionRate);
    }

    /**
    * Returns the udc2 of this ivld customer gts forecast.
    *
    * @return the udc2 of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getUdc2() {
        return _ivldCustomerGtsForecast.getUdc2();
    }

    /**
    * Sets the udc2 of this ivld customer gts forecast.
    *
    * @param udc2 the udc2 of this ivld customer gts forecast
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _ivldCustomerGtsForecast.setUdc2(udc2);
    }

    /**
    * Returns the udc3 of this ivld customer gts forecast.
    *
    * @return the udc3 of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getUdc3() {
        return _ivldCustomerGtsForecast.getUdc3();
    }

    /**
    * Sets the udc3 of this ivld customer gts forecast.
    *
    * @param udc3 the udc3 of this ivld customer gts forecast
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _ivldCustomerGtsForecast.setUdc3(udc3);
    }

    /**
    * Returns the reason for failure of this ivld customer gts forecast.
    *
    * @return the reason for failure of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldCustomerGtsForecast.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld customer gts forecast.
    *
    * @param reasonForFailure the reason for failure of this ivld customer gts forecast
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldCustomerGtsForecast.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the country of this ivld customer gts forecast.
    *
    * @return the country of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getCountry() {
        return _ivldCustomerGtsForecast.getCountry();
    }

    /**
    * Sets the country of this ivld customer gts forecast.
    *
    * @param country the country of this ivld customer gts forecast
    */
    @Override
    public void setCountry(java.lang.String country) {
        _ivldCustomerGtsForecast.setCountry(country);
    }

    /**
    * Returns the company ID of this ivld customer gts forecast.
    *
    * @return the company ID of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getCompanyId() {
        return _ivldCustomerGtsForecast.getCompanyId();
    }

    /**
    * Sets the company ID of this ivld customer gts forecast.
    *
    * @param companyId the company ID of this ivld customer gts forecast
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _ivldCustomerGtsForecast.setCompanyId(companyId);
    }

    /**
    * Returns the forecast value type of this ivld customer gts forecast.
    *
    * @return the forecast value type of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getForecastValueType() {
        return _ivldCustomerGtsForecast.getForecastValueType();
    }

    /**
    * Sets the forecast value type of this ivld customer gts forecast.
    *
    * @param forecastValueType the forecast value type of this ivld customer gts forecast
    */
    @Override
    public void setForecastValueType(java.lang.String forecastValueType) {
        _ivldCustomerGtsForecast.setForecastValueType(forecastValueType);
    }

    /**
    * Returns the deduction category of this ivld customer gts forecast.
    *
    * @return the deduction category of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getDeductionCategory() {
        return _ivldCustomerGtsForecast.getDeductionCategory();
    }

    /**
    * Sets the deduction category of this ivld customer gts forecast.
    *
    * @param deductionCategory the deduction category of this ivld customer gts forecast
    */
    @Override
    public void setDeductionCategory(java.lang.String deductionCategory) {
        _ivldCustomerGtsForecast.setDeductionCategory(deductionCategory);
    }

    /**
    * Returns the adjustment code of this ivld customer gts forecast.
    *
    * @return the adjustment code of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getAdjustmentCode() {
        return _ivldCustomerGtsForecast.getAdjustmentCode();
    }

    /**
    * Sets the adjustment code of this ivld customer gts forecast.
    *
    * @param adjustmentCode the adjustment code of this ivld customer gts forecast
    */
    @Override
    public void setAdjustmentCode(java.lang.String adjustmentCode) {
        _ivldCustomerGtsForecast.setAdjustmentCode(adjustmentCode);
    }

    /**
    * Returns the deduction program type of this ivld customer gts forecast.
    *
    * @return the deduction program type of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getDeductionProgramType() {
        return _ivldCustomerGtsForecast.getDeductionProgramType();
    }

    /**
    * Sets the deduction program type of this ivld customer gts forecast.
    *
    * @param deductionProgramType the deduction program type of this ivld customer gts forecast
    */
    @Override
    public void setDeductionProgramType(java.lang.String deductionProgramType) {
        _ivldCustomerGtsForecast.setDeductionProgramType(deductionProgramType);
    }

    /**
    * Returns the customer gts forecast intf ID of this ivld customer gts forecast.
    *
    * @return the customer gts forecast intf ID of this ivld customer gts forecast
    */
    @Override
    public int getCustomerGtsForecastIntfId() {
        return _ivldCustomerGtsForecast.getCustomerGtsForecastIntfId();
    }

    /**
    * Sets the customer gts forecast intf ID of this ivld customer gts forecast.
    *
    * @param customerGtsForecastIntfId the customer gts forecast intf ID of this ivld customer gts forecast
    */
    @Override
    public void setCustomerGtsForecastIntfId(int customerGtsForecastIntfId) {
        _ivldCustomerGtsForecast.setCustomerGtsForecastIntfId(customerGtsForecastIntfId);
    }

    /**
    * Returns the sales inclusion of this ivld customer gts forecast.
    *
    * @return the sales inclusion of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getSalesInclusion() {
        return _ivldCustomerGtsForecast.getSalesInclusion();
    }

    /**
    * Sets the sales inclusion of this ivld customer gts forecast.
    *
    * @param salesInclusion the sales inclusion of this ivld customer gts forecast
    */
    @Override
    public void setSalesInclusion(java.lang.String salesInclusion) {
        _ivldCustomerGtsForecast.setSalesInclusion(salesInclusion);
    }

    /**
    * Returns the forecast ver of this ivld customer gts forecast.
    *
    * @return the forecast ver of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getForecastVer() {
        return _ivldCustomerGtsForecast.getForecastVer();
    }

    /**
    * Sets the forecast ver of this ivld customer gts forecast.
    *
    * @param forecastVer the forecast ver of this ivld customer gts forecast
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _ivldCustomerGtsForecast.setForecastVer(forecastVer);
    }

    /**
    * Returns the batch ID of this ivld customer gts forecast.
    *
    * @return the batch ID of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldCustomerGtsForecast.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld customer gts forecast.
    *
    * @param batchId the batch ID of this ivld customer gts forecast
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldCustomerGtsForecast.setBatchId(batchId);
    }

    /**
    * Returns the price type of this ivld customer gts forecast.
    *
    * @return the price type of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getPriceType() {
        return _ivldCustomerGtsForecast.getPriceType();
    }

    /**
    * Sets the price type of this ivld customer gts forecast.
    *
    * @param priceType the price type of this ivld customer gts forecast
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _ivldCustomerGtsForecast.setPriceType(priceType);
    }

    /**
    * Returns the forecast month of this ivld customer gts forecast.
    *
    * @return the forecast month of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getForecastMonth() {
        return _ivldCustomerGtsForecast.getForecastMonth();
    }

    /**
    * Sets the forecast month of this ivld customer gts forecast.
    *
    * @param forecastMonth the forecast month of this ivld customer gts forecast
    */
    @Override
    public void setForecastMonth(java.lang.String forecastMonth) {
        _ivldCustomerGtsForecast.setForecastMonth(forecastMonth);
    }

    /**
    * Returns the deduction inclusion of this ivld customer gts forecast.
    *
    * @return the deduction inclusion of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getDeductionInclusion() {
        return _ivldCustomerGtsForecast.getDeductionInclusion();
    }

    /**
    * Sets the deduction inclusion of this ivld customer gts forecast.
    *
    * @param deductionInclusion the deduction inclusion of this ivld customer gts forecast
    */
    @Override
    public void setDeductionInclusion(java.lang.String deductionInclusion) {
        _ivldCustomerGtsForecast.setDeductionInclusion(deductionInclusion);
    }

    /**
    * Returns the error field of this ivld customer gts forecast.
    *
    * @return the error field of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldCustomerGtsForecast.getErrorField();
    }

    /**
    * Sets the error field of this ivld customer gts forecast.
    *
    * @param errorField the error field of this ivld customer gts forecast
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldCustomerGtsForecast.setErrorField(errorField);
    }

    /**
    * Returns the segment of this ivld customer gts forecast.
    *
    * @return the segment of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getSegment() {
        return _ivldCustomerGtsForecast.getSegment();
    }

    /**
    * Sets the segment of this ivld customer gts forecast.
    *
    * @param segment the segment of this ivld customer gts forecast
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _ivldCustomerGtsForecast.setSegment(segment);
    }

    /**
    * Returns the brand of this ivld customer gts forecast.
    *
    * @return the brand of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getBrand() {
        return _ivldCustomerGtsForecast.getBrand();
    }

    /**
    * Sets the brand of this ivld customer gts forecast.
    *
    * @param brand the brand of this ivld customer gts forecast
    */
    @Override
    public void setBrand(java.lang.String brand) {
        _ivldCustomerGtsForecast.setBrand(brand);
    }

    /**
    * Returns the forecast name of this ivld customer gts forecast.
    *
    * @return the forecast name of this ivld customer gts forecast
    */
    @Override
    public java.lang.String getForecastName() {
        return _ivldCustomerGtsForecast.getForecastName();
    }

    /**
    * Sets the forecast name of this ivld customer gts forecast.
    *
    * @param forecastName the forecast name of this ivld customer gts forecast
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _ivldCustomerGtsForecast.setForecastName(forecastName);
    }

    @Override
    public boolean isNew() {
        return _ivldCustomerGtsForecast.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldCustomerGtsForecast.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldCustomerGtsForecast.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldCustomerGtsForecast.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldCustomerGtsForecast.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldCustomerGtsForecast.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldCustomerGtsForecast.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldCustomerGtsForecast.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldCustomerGtsForecast.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldCustomerGtsForecast.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldCustomerGtsForecast.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldCustomerGtsForecastWrapper((IvldCustomerGtsForecast) _ivldCustomerGtsForecast.clone());
    }

    @Override
    public int compareTo(IvldCustomerGtsForecast ivldCustomerGtsForecast) {
        return _ivldCustomerGtsForecast.compareTo(ivldCustomerGtsForecast);
    }

    @Override
    public int hashCode() {
        return _ivldCustomerGtsForecast.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldCustomerGtsForecast> toCacheModel() {
        return _ivldCustomerGtsForecast.toCacheModel();
    }

    @Override
    public IvldCustomerGtsForecast toEscapedModel() {
        return new IvldCustomerGtsForecastWrapper(_ivldCustomerGtsForecast.toEscapedModel());
    }

    @Override
    public IvldCustomerGtsForecast toUnescapedModel() {
        return new IvldCustomerGtsForecastWrapper(_ivldCustomerGtsForecast.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldCustomerGtsForecast.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldCustomerGtsForecast.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldCustomerGtsForecast.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldCustomerGtsForecastWrapper)) {
            return false;
        }

        IvldCustomerGtsForecastWrapper ivldCustomerGtsForecastWrapper = (IvldCustomerGtsForecastWrapper) obj;

        if (Validator.equals(_ivldCustomerGtsForecast,
                    ivldCustomerGtsForecastWrapper._ivldCustomerGtsForecast)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldCustomerGtsForecast getWrappedIvldCustomerGtsForecast() {
        return _ivldCustomerGtsForecast;
    }

    @Override
    public IvldCustomerGtsForecast getWrappedModel() {
        return _ivldCustomerGtsForecast;
    }

    @Override
    public void resetOriginalValues() {
        _ivldCustomerGtsForecast.resetOriginalValues();
    }
}
