package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwCustomerGtsForecast}.
 * </p>
 *
 * @author
 * @see VwCustomerGtsForecast
 * @generated
 */
public class VwCustomerGtsForecastWrapper implements VwCustomerGtsForecast,
    ModelWrapper<VwCustomerGtsForecast> {
    private VwCustomerGtsForecast _vwCustomerGtsForecast;

    public VwCustomerGtsForecastWrapper(
        VwCustomerGtsForecast vwCustomerGtsForecast) {
        _vwCustomerGtsForecast = vwCustomerGtsForecast;
    }

    @Override
    public Class<?> getModelClass() {
        return VwCustomerGtsForecast.class;
    }

    @Override
    public String getModelClassName() {
        return VwCustomerGtsForecast.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("price", getPrice());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("deductionAmount", getDeductionAmount());
        attributes.put("deductionId", getDeductionId());
        attributes.put("forecastDate", getForecastDate());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("salesAmount", getSalesAmount());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("deductionType", getDeductionType());
        attributes.put("udc4", getUdc4());
        attributes.put("units", getUnits());
        attributes.put("deductionRate", getDeductionRate());
        attributes.put("udc1", getUdc1());
        attributes.put("customerGtsForecastSid", getCustomerGtsForecastSid());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
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
        attributes.put("segment", getSegment());
        attributes.put("brand", getBrand());
        attributes.put("forecastName", getForecastName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        Double deductionAmount = (Double) attributes.get("deductionAmount");

        if (deductionAmount != null) {
            setDeductionAmount(deductionAmount);
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

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Double salesAmount = (Double) attributes.get("salesAmount");

        if (salesAmount != null) {
            setSalesAmount(salesAmount);
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

        Double units = (Double) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        Double deductionRate = (Double) attributes.get("deductionRate");

        if (deductionRate != null) {
            setDeductionRate(deductionRate);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        Integer customerGtsForecastSid = (Integer) attributes.get(
                "customerGtsForecastSid");

        if (customerGtsForecastSid != null) {
            setCustomerGtsForecastSid(customerGtsForecastSid);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
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
    * Returns the primary key of this vw customer gts forecast.
    *
    * @return the primary key of this vw customer gts forecast
    */
    @Override
    public int getPrimaryKey() {
        return _vwCustomerGtsForecast.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw customer gts forecast.
    *
    * @param primaryKey the primary key of this vw customer gts forecast
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwCustomerGtsForecast.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the price of this vw customer gts forecast.
    *
    * @return the price of this vw customer gts forecast
    */
    @Override
    public double getPrice() {
        return _vwCustomerGtsForecast.getPrice();
    }

    /**
    * Sets the price of this vw customer gts forecast.
    *
    * @param price the price of this vw customer gts forecast
    */
    @Override
    public void setPrice(double price) {
        _vwCustomerGtsForecast.setPrice(price);
    }

    /**
    * Returns the forecast year of this vw customer gts forecast.
    *
    * @return the forecast year of this vw customer gts forecast
    */
    @Override
    public java.lang.String getForecastYear() {
        return _vwCustomerGtsForecast.getForecastYear();
    }

    /**
    * Sets the forecast year of this vw customer gts forecast.
    *
    * @param forecastYear the forecast year of this vw customer gts forecast
    */
    @Override
    public void setForecastYear(java.lang.String forecastYear) {
        _vwCustomerGtsForecast.setForecastYear(forecastYear);
    }

    /**
    * Returns the deduction amount of this vw customer gts forecast.
    *
    * @return the deduction amount of this vw customer gts forecast
    */
    @Override
    public double getDeductionAmount() {
        return _vwCustomerGtsForecast.getDeductionAmount();
    }

    /**
    * Sets the deduction amount of this vw customer gts forecast.
    *
    * @param deductionAmount the deduction amount of this vw customer gts forecast
    */
    @Override
    public void setDeductionAmount(double deductionAmount) {
        _vwCustomerGtsForecast.setDeductionAmount(deductionAmount);
    }

    /**
    * Returns the deduction ID of this vw customer gts forecast.
    *
    * @return the deduction ID of this vw customer gts forecast
    */
    @Override
    public java.lang.String getDeductionId() {
        return _vwCustomerGtsForecast.getDeductionId();
    }

    /**
    * Sets the deduction ID of this vw customer gts forecast.
    *
    * @param deductionId the deduction ID of this vw customer gts forecast
    */
    @Override
    public void setDeductionId(java.lang.String deductionId) {
        _vwCustomerGtsForecast.setDeductionId(deductionId);
    }

    /**
    * Returns the forecast date of this vw customer gts forecast.
    *
    * @return the forecast date of this vw customer gts forecast
    */
    @Override
    public java.util.Date getForecastDate() {
        return _vwCustomerGtsForecast.getForecastDate();
    }

    /**
    * Sets the forecast date of this vw customer gts forecast.
    *
    * @param forecastDate the forecast date of this vw customer gts forecast
    */
    @Override
    public void setForecastDate(java.util.Date forecastDate) {
        _vwCustomerGtsForecast.setForecastDate(forecastDate);
    }

    /**
    * Returns the item ID of this vw customer gts forecast.
    *
    * @return the item ID of this vw customer gts forecast
    */
    @Override
    public java.lang.String getItemId() {
        return _vwCustomerGtsForecast.getItemId();
    }

    /**
    * Sets the item ID of this vw customer gts forecast.
    *
    * @param itemId the item ID of this vw customer gts forecast
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwCustomerGtsForecast.setItemId(itemId);
    }

    /**
    * Returns the modified date of this vw customer gts forecast.
    *
    * @return the modified date of this vw customer gts forecast
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwCustomerGtsForecast.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw customer gts forecast.
    *
    * @param modifiedDate the modified date of this vw customer gts forecast
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwCustomerGtsForecast.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the source of this vw customer gts forecast.
    *
    * @return the source of this vw customer gts forecast
    */
    @Override
    public java.lang.String getSource() {
        return _vwCustomerGtsForecast.getSource();
    }

    /**
    * Sets the source of this vw customer gts forecast.
    *
    * @param source the source of this vw customer gts forecast
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwCustomerGtsForecast.setSource(source);
    }

    /**
    * Returns the created by of this vw customer gts forecast.
    *
    * @return the created by of this vw customer gts forecast
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwCustomerGtsForecast.getCreatedBy();
    }

    /**
    * Sets the created by of this vw customer gts forecast.
    *
    * @param createdBy the created by of this vw customer gts forecast
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwCustomerGtsForecast.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this vw customer gts forecast.
    *
    * @return the created date of this vw customer gts forecast
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwCustomerGtsForecast.getCreatedDate();
    }

    /**
    * Sets the created date of this vw customer gts forecast.
    *
    * @param createdDate the created date of this vw customer gts forecast
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwCustomerGtsForecast.setCreatedDate(createdDate);
    }

    /**
    * Returns the add chg del indicator of this vw customer gts forecast.
    *
    * @return the add chg del indicator of this vw customer gts forecast
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwCustomerGtsForecast.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw customer gts forecast.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw customer gts forecast
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwCustomerGtsForecast.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the modified by of this vw customer gts forecast.
    *
    * @return the modified by of this vw customer gts forecast
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwCustomerGtsForecast.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw customer gts forecast.
    *
    * @param modifiedBy the modified by of this vw customer gts forecast
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwCustomerGtsForecast.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the sales amount of this vw customer gts forecast.
    *
    * @return the sales amount of this vw customer gts forecast
    */
    @Override
    public double getSalesAmount() {
        return _vwCustomerGtsForecast.getSalesAmount();
    }

    /**
    * Sets the sales amount of this vw customer gts forecast.
    *
    * @param salesAmount the sales amount of this vw customer gts forecast
    */
    @Override
    public void setSalesAmount(double salesAmount) {
        _vwCustomerGtsForecast.setSalesAmount(salesAmount);
    }

    /**
    * Returns the udc6 of this vw customer gts forecast.
    *
    * @return the udc6 of this vw customer gts forecast
    */
    @Override
    public java.lang.String getUdc6() {
        return _vwCustomerGtsForecast.getUdc6();
    }

    /**
    * Sets the udc6 of this vw customer gts forecast.
    *
    * @param udc6 the udc6 of this vw customer gts forecast
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _vwCustomerGtsForecast.setUdc6(udc6);
    }

    /**
    * Returns the udc5 of this vw customer gts forecast.
    *
    * @return the udc5 of this vw customer gts forecast
    */
    @Override
    public java.lang.String getUdc5() {
        return _vwCustomerGtsForecast.getUdc5();
    }

    /**
    * Sets the udc5 of this vw customer gts forecast.
    *
    * @param udc5 the udc5 of this vw customer gts forecast
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _vwCustomerGtsForecast.setUdc5(udc5);
    }

    /**
    * Returns the deduction type of this vw customer gts forecast.
    *
    * @return the deduction type of this vw customer gts forecast
    */
    @Override
    public java.lang.String getDeductionType() {
        return _vwCustomerGtsForecast.getDeductionType();
    }

    /**
    * Sets the deduction type of this vw customer gts forecast.
    *
    * @param deductionType the deduction type of this vw customer gts forecast
    */
    @Override
    public void setDeductionType(java.lang.String deductionType) {
        _vwCustomerGtsForecast.setDeductionType(deductionType);
    }

    /**
    * Returns the udc4 of this vw customer gts forecast.
    *
    * @return the udc4 of this vw customer gts forecast
    */
    @Override
    public java.lang.String getUdc4() {
        return _vwCustomerGtsForecast.getUdc4();
    }

    /**
    * Sets the udc4 of this vw customer gts forecast.
    *
    * @param udc4 the udc4 of this vw customer gts forecast
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _vwCustomerGtsForecast.setUdc4(udc4);
    }

    /**
    * Returns the units of this vw customer gts forecast.
    *
    * @return the units of this vw customer gts forecast
    */
    @Override
    public double getUnits() {
        return _vwCustomerGtsForecast.getUnits();
    }

    /**
    * Sets the units of this vw customer gts forecast.
    *
    * @param units the units of this vw customer gts forecast
    */
    @Override
    public void setUnits(double units) {
        _vwCustomerGtsForecast.setUnits(units);
    }

    /**
    * Returns the deduction rate of this vw customer gts forecast.
    *
    * @return the deduction rate of this vw customer gts forecast
    */
    @Override
    public double getDeductionRate() {
        return _vwCustomerGtsForecast.getDeductionRate();
    }

    /**
    * Sets the deduction rate of this vw customer gts forecast.
    *
    * @param deductionRate the deduction rate of this vw customer gts forecast
    */
    @Override
    public void setDeductionRate(double deductionRate) {
        _vwCustomerGtsForecast.setDeductionRate(deductionRate);
    }

    /**
    * Returns the udc1 of this vw customer gts forecast.
    *
    * @return the udc1 of this vw customer gts forecast
    */
    @Override
    public java.lang.String getUdc1() {
        return _vwCustomerGtsForecast.getUdc1();
    }

    /**
    * Sets the udc1 of this vw customer gts forecast.
    *
    * @param udc1 the udc1 of this vw customer gts forecast
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _vwCustomerGtsForecast.setUdc1(udc1);
    }

    /**
    * Returns the customer gts forecast sid of this vw customer gts forecast.
    *
    * @return the customer gts forecast sid of this vw customer gts forecast
    */
    @Override
    public int getCustomerGtsForecastSid() {
        return _vwCustomerGtsForecast.getCustomerGtsForecastSid();
    }

    /**
    * Sets the customer gts forecast sid of this vw customer gts forecast.
    *
    * @param customerGtsForecastSid the customer gts forecast sid of this vw customer gts forecast
    */
    @Override
    public void setCustomerGtsForecastSid(int customerGtsForecastSid) {
        _vwCustomerGtsForecast.setCustomerGtsForecastSid(customerGtsForecastSid);
    }

    /**
    * Returns the udc2 of this vw customer gts forecast.
    *
    * @return the udc2 of this vw customer gts forecast
    */
    @Override
    public java.lang.String getUdc2() {
        return _vwCustomerGtsForecast.getUdc2();
    }

    /**
    * Sets the udc2 of this vw customer gts forecast.
    *
    * @param udc2 the udc2 of this vw customer gts forecast
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _vwCustomerGtsForecast.setUdc2(udc2);
    }

    /**
    * Returns the udc3 of this vw customer gts forecast.
    *
    * @return the udc3 of this vw customer gts forecast
    */
    @Override
    public java.lang.String getUdc3() {
        return _vwCustomerGtsForecast.getUdc3();
    }

    /**
    * Sets the udc3 of this vw customer gts forecast.
    *
    * @param udc3 the udc3 of this vw customer gts forecast
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _vwCustomerGtsForecast.setUdc3(udc3);
    }

    /**
    * Returns the country of this vw customer gts forecast.
    *
    * @return the country of this vw customer gts forecast
    */
    @Override
    public java.lang.String getCountry() {
        return _vwCustomerGtsForecast.getCountry();
    }

    /**
    * Sets the country of this vw customer gts forecast.
    *
    * @param country the country of this vw customer gts forecast
    */
    @Override
    public void setCountry(java.lang.String country) {
        _vwCustomerGtsForecast.setCountry(country);
    }

    /**
    * Returns the company ID of this vw customer gts forecast.
    *
    * @return the company ID of this vw customer gts forecast
    */
    @Override
    public java.lang.String getCompanyId() {
        return _vwCustomerGtsForecast.getCompanyId();
    }

    /**
    * Sets the company ID of this vw customer gts forecast.
    *
    * @param companyId the company ID of this vw customer gts forecast
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _vwCustomerGtsForecast.setCompanyId(companyId);
    }

    /**
    * Returns the forecast value type of this vw customer gts forecast.
    *
    * @return the forecast value type of this vw customer gts forecast
    */
    @Override
    public java.lang.String getForecastValueType() {
        return _vwCustomerGtsForecast.getForecastValueType();
    }

    /**
    * Sets the forecast value type of this vw customer gts forecast.
    *
    * @param forecastValueType the forecast value type of this vw customer gts forecast
    */
    @Override
    public void setForecastValueType(java.lang.String forecastValueType) {
        _vwCustomerGtsForecast.setForecastValueType(forecastValueType);
    }

    /**
    * Returns the deduction category of this vw customer gts forecast.
    *
    * @return the deduction category of this vw customer gts forecast
    */
    @Override
    public java.lang.String getDeductionCategory() {
        return _vwCustomerGtsForecast.getDeductionCategory();
    }

    /**
    * Sets the deduction category of this vw customer gts forecast.
    *
    * @param deductionCategory the deduction category of this vw customer gts forecast
    */
    @Override
    public void setDeductionCategory(java.lang.String deductionCategory) {
        _vwCustomerGtsForecast.setDeductionCategory(deductionCategory);
    }

    /**
    * Returns the adjustment code of this vw customer gts forecast.
    *
    * @return the adjustment code of this vw customer gts forecast
    */
    @Override
    public java.lang.String getAdjustmentCode() {
        return _vwCustomerGtsForecast.getAdjustmentCode();
    }

    /**
    * Sets the adjustment code of this vw customer gts forecast.
    *
    * @param adjustmentCode the adjustment code of this vw customer gts forecast
    */
    @Override
    public void setAdjustmentCode(java.lang.String adjustmentCode) {
        _vwCustomerGtsForecast.setAdjustmentCode(adjustmentCode);
    }

    /**
    * Returns the deduction program type of this vw customer gts forecast.
    *
    * @return the deduction program type of this vw customer gts forecast
    */
    @Override
    public java.lang.String getDeductionProgramType() {
        return _vwCustomerGtsForecast.getDeductionProgramType();
    }

    /**
    * Sets the deduction program type of this vw customer gts forecast.
    *
    * @param deductionProgramType the deduction program type of this vw customer gts forecast
    */
    @Override
    public void setDeductionProgramType(java.lang.String deductionProgramType) {
        _vwCustomerGtsForecast.setDeductionProgramType(deductionProgramType);
    }

    /**
    * Returns the customer gts forecast intf ID of this vw customer gts forecast.
    *
    * @return the customer gts forecast intf ID of this vw customer gts forecast
    */
    @Override
    public int getCustomerGtsForecastIntfId() {
        return _vwCustomerGtsForecast.getCustomerGtsForecastIntfId();
    }

    /**
    * Sets the customer gts forecast intf ID of this vw customer gts forecast.
    *
    * @param customerGtsForecastIntfId the customer gts forecast intf ID of this vw customer gts forecast
    */
    @Override
    public void setCustomerGtsForecastIntfId(int customerGtsForecastIntfId) {
        _vwCustomerGtsForecast.setCustomerGtsForecastIntfId(customerGtsForecastIntfId);
    }

    /**
    * Returns the sales inclusion of this vw customer gts forecast.
    *
    * @return the sales inclusion of this vw customer gts forecast
    */
    @Override
    public java.lang.String getSalesInclusion() {
        return _vwCustomerGtsForecast.getSalesInclusion();
    }

    /**
    * Sets the sales inclusion of this vw customer gts forecast.
    *
    * @param salesInclusion the sales inclusion of this vw customer gts forecast
    */
    @Override
    public void setSalesInclusion(java.lang.String salesInclusion) {
        _vwCustomerGtsForecast.setSalesInclusion(salesInclusion);
    }

    /**
    * Returns the forecast ver of this vw customer gts forecast.
    *
    * @return the forecast ver of this vw customer gts forecast
    */
    @Override
    public java.lang.String getForecastVer() {
        return _vwCustomerGtsForecast.getForecastVer();
    }

    /**
    * Sets the forecast ver of this vw customer gts forecast.
    *
    * @param forecastVer the forecast ver of this vw customer gts forecast
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _vwCustomerGtsForecast.setForecastVer(forecastVer);
    }

    /**
    * Returns the batch ID of this vw customer gts forecast.
    *
    * @return the batch ID of this vw customer gts forecast
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwCustomerGtsForecast.getBatchId();
    }

    /**
    * Sets the batch ID of this vw customer gts forecast.
    *
    * @param batchId the batch ID of this vw customer gts forecast
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwCustomerGtsForecast.setBatchId(batchId);
    }

    /**
    * Returns the price type of this vw customer gts forecast.
    *
    * @return the price type of this vw customer gts forecast
    */
    @Override
    public java.lang.String getPriceType() {
        return _vwCustomerGtsForecast.getPriceType();
    }

    /**
    * Sets the price type of this vw customer gts forecast.
    *
    * @param priceType the price type of this vw customer gts forecast
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _vwCustomerGtsForecast.setPriceType(priceType);
    }

    /**
    * Returns the forecast month of this vw customer gts forecast.
    *
    * @return the forecast month of this vw customer gts forecast
    */
    @Override
    public java.lang.String getForecastMonth() {
        return _vwCustomerGtsForecast.getForecastMonth();
    }

    /**
    * Sets the forecast month of this vw customer gts forecast.
    *
    * @param forecastMonth the forecast month of this vw customer gts forecast
    */
    @Override
    public void setForecastMonth(java.lang.String forecastMonth) {
        _vwCustomerGtsForecast.setForecastMonth(forecastMonth);
    }

    /**
    * Returns the deduction inclusion of this vw customer gts forecast.
    *
    * @return the deduction inclusion of this vw customer gts forecast
    */
    @Override
    public java.lang.String getDeductionInclusion() {
        return _vwCustomerGtsForecast.getDeductionInclusion();
    }

    /**
    * Sets the deduction inclusion of this vw customer gts forecast.
    *
    * @param deductionInclusion the deduction inclusion of this vw customer gts forecast
    */
    @Override
    public void setDeductionInclusion(java.lang.String deductionInclusion) {
        _vwCustomerGtsForecast.setDeductionInclusion(deductionInclusion);
    }

    /**
    * Returns the segment of this vw customer gts forecast.
    *
    * @return the segment of this vw customer gts forecast
    */
    @Override
    public java.lang.String getSegment() {
        return _vwCustomerGtsForecast.getSegment();
    }

    /**
    * Sets the segment of this vw customer gts forecast.
    *
    * @param segment the segment of this vw customer gts forecast
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _vwCustomerGtsForecast.setSegment(segment);
    }

    /**
    * Returns the brand of this vw customer gts forecast.
    *
    * @return the brand of this vw customer gts forecast
    */
    @Override
    public java.lang.String getBrand() {
        return _vwCustomerGtsForecast.getBrand();
    }

    /**
    * Sets the brand of this vw customer gts forecast.
    *
    * @param brand the brand of this vw customer gts forecast
    */
    @Override
    public void setBrand(java.lang.String brand) {
        _vwCustomerGtsForecast.setBrand(brand);
    }

    /**
    * Returns the forecast name of this vw customer gts forecast.
    *
    * @return the forecast name of this vw customer gts forecast
    */
    @Override
    public java.lang.String getForecastName() {
        return _vwCustomerGtsForecast.getForecastName();
    }

    /**
    * Sets the forecast name of this vw customer gts forecast.
    *
    * @param forecastName the forecast name of this vw customer gts forecast
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _vwCustomerGtsForecast.setForecastName(forecastName);
    }

    @Override
    public boolean isNew() {
        return _vwCustomerGtsForecast.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwCustomerGtsForecast.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwCustomerGtsForecast.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwCustomerGtsForecast.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwCustomerGtsForecast.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwCustomerGtsForecast.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwCustomerGtsForecast.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwCustomerGtsForecast.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwCustomerGtsForecast.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwCustomerGtsForecast.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwCustomerGtsForecast.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwCustomerGtsForecastWrapper((VwCustomerGtsForecast) _vwCustomerGtsForecast.clone());
    }

    @Override
    public int compareTo(VwCustomerGtsForecast vwCustomerGtsForecast) {
        return _vwCustomerGtsForecast.compareTo(vwCustomerGtsForecast);
    }

    @Override
    public int hashCode() {
        return _vwCustomerGtsForecast.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwCustomerGtsForecast> toCacheModel() {
        return _vwCustomerGtsForecast.toCacheModel();
    }

    @Override
    public VwCustomerGtsForecast toEscapedModel() {
        return new VwCustomerGtsForecastWrapper(_vwCustomerGtsForecast.toEscapedModel());
    }

    @Override
    public VwCustomerGtsForecast toUnescapedModel() {
        return new VwCustomerGtsForecastWrapper(_vwCustomerGtsForecast.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwCustomerGtsForecast.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwCustomerGtsForecast.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwCustomerGtsForecast.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwCustomerGtsForecastWrapper)) {
            return false;
        }

        VwCustomerGtsForecastWrapper vwCustomerGtsForecastWrapper = (VwCustomerGtsForecastWrapper) obj;

        if (Validator.equals(_vwCustomerGtsForecast,
                    vwCustomerGtsForecastWrapper._vwCustomerGtsForecast)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwCustomerGtsForecast getWrappedVwCustomerGtsForecast() {
        return _vwCustomerGtsForecast;
    }

    @Override
    public VwCustomerGtsForecast getWrappedModel() {
        return _vwCustomerGtsForecast;
    }

    @Override
    public void resetOriginalValues() {
        _vwCustomerGtsForecast.resetOriginalValues();
    }
}
