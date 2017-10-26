package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwInventoryWdActualProjMas}.
 * </p>
 *
 * @author
 * @see VwInventoryWdActualProjMas
 * @generated
 */
public class VwInventoryWdActualProjMasWrapper
    implements VwInventoryWdActualProjMas,
        ModelWrapper<VwInventoryWdActualProjMas> {
    private VwInventoryWdActualProjMas _vwInventoryWdActualProjMas;

    public VwInventoryWdActualProjMasWrapper(
        VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
        _vwInventoryWdActualProjMas = vwInventoryWdActualProjMas;
    }

    @Override
    public Class<?> getModelClass() {
        return VwInventoryWdActualProjMas.class;
    }

    @Override
    public String getModelClassName() {
        return VwInventoryWdActualProjMas.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("quantityOnOrder", getQuantityOnOrder());
        attributes.put("week", getWeek());
        attributes.put("price", getPrice());
        attributes.put("amountOnHand", getAmountOnHand());
        attributes.put("isMaster", getIsMaster());
        attributes.put("companyName", getCompanyName());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("inventoryWdActualProjMasSid",
            getInventoryWdActualProjMasSid());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("day", getDay());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("unitsOnHand", getUnitsOnHand());
        attributes.put("amountReceived", getAmountReceived());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("month", getMonth());
        attributes.put("amountWithdrawn", getAmountWithdrawn());
        attributes.put("quantityReceived", getQuantityReceived());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("isForecast", getIsForecast());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("amountOnOrder", getAmountOnOrder());
        attributes.put("forecastName", getForecastName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double quantityOnOrder = (Double) attributes.get("quantityOnOrder");

        if (quantityOnOrder != null) {
            setQuantityOnOrder(quantityOnOrder);
        }

        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Double amountOnHand = (Double) attributes.get("amountOnHand");

        if (amountOnHand != null) {
            setAmountOnHand(amountOnHand);
        }

        String isMaster = (String) attributes.get("isMaster");

        if (isMaster != null) {
            setIsMaster(isMaster);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        Integer inventoryWdActualProjMasSid = (Integer) attributes.get(
                "inventoryWdActualProjMasSid");

        if (inventoryWdActualProjMasSid != null) {
            setInventoryWdActualProjMasSid(inventoryWdActualProjMasSid);
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

        String day = (String) attributes.get("day");

        if (day != null) {
            setDay(day);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Double unitsOnHand = (Double) attributes.get("unitsOnHand");

        if (unitsOnHand != null) {
            setUnitsOnHand(unitsOnHand);
        }

        Double amountReceived = (Double) attributes.get("amountReceived");

        if (amountReceived != null) {
            setAmountReceived(amountReceived);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        Double amountWithdrawn = (Double) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }

        Double quantityReceived = (Double) attributes.get("quantityReceived");

        if (quantityReceived != null) {
            setQuantityReceived(quantityReceived);
        }

        Double unitsWithdrawn = (Double) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String isForecast = (String) attributes.get("isForecast");

        if (isForecast != null) {
            setIsForecast(isForecast);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        Double amountOnOrder = (Double) attributes.get("amountOnOrder");

        if (amountOnOrder != null) {
            setAmountOnOrder(amountOnOrder);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }
    }

    /**
    * Returns the primary key of this vw inventory wd actual proj mas.
    *
    * @return the primary key of this vw inventory wd actual proj mas
    */
    @Override
    public int getPrimaryKey() {
        return _vwInventoryWdActualProjMas.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw inventory wd actual proj mas.
    *
    * @param primaryKey the primary key of this vw inventory wd actual proj mas
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwInventoryWdActualProjMas.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the quantity on order of this vw inventory wd actual proj mas.
    *
    * @return the quantity on order of this vw inventory wd actual proj mas
    */
    @Override
    public double getQuantityOnOrder() {
        return _vwInventoryWdActualProjMas.getQuantityOnOrder();
    }

    /**
    * Sets the quantity on order of this vw inventory wd actual proj mas.
    *
    * @param quantityOnOrder the quantity on order of this vw inventory wd actual proj mas
    */
    @Override
    public void setQuantityOnOrder(double quantityOnOrder) {
        _vwInventoryWdActualProjMas.setQuantityOnOrder(quantityOnOrder);
    }

    /**
    * Returns the week of this vw inventory wd actual proj mas.
    *
    * @return the week of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getWeek() {
        return _vwInventoryWdActualProjMas.getWeek();
    }

    /**
    * Sets the week of this vw inventory wd actual proj mas.
    *
    * @param week the week of this vw inventory wd actual proj mas
    */
    @Override
    public void setWeek(java.lang.String week) {
        _vwInventoryWdActualProjMas.setWeek(week);
    }

    /**
    * Returns the price of this vw inventory wd actual proj mas.
    *
    * @return the price of this vw inventory wd actual proj mas
    */
    @Override
    public double getPrice() {
        return _vwInventoryWdActualProjMas.getPrice();
    }

    /**
    * Sets the price of this vw inventory wd actual proj mas.
    *
    * @param price the price of this vw inventory wd actual proj mas
    */
    @Override
    public void setPrice(double price) {
        _vwInventoryWdActualProjMas.setPrice(price);
    }

    /**
    * Returns the amount on hand of this vw inventory wd actual proj mas.
    *
    * @return the amount on hand of this vw inventory wd actual proj mas
    */
    @Override
    public double getAmountOnHand() {
        return _vwInventoryWdActualProjMas.getAmountOnHand();
    }

    /**
    * Sets the amount on hand of this vw inventory wd actual proj mas.
    *
    * @param amountOnHand the amount on hand of this vw inventory wd actual proj mas
    */
    @Override
    public void setAmountOnHand(double amountOnHand) {
        _vwInventoryWdActualProjMas.setAmountOnHand(amountOnHand);
    }

    /**
    * Returns the is master of this vw inventory wd actual proj mas.
    *
    * @return the is master of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getIsMaster() {
        return _vwInventoryWdActualProjMas.getIsMaster();
    }

    /**
    * Sets the is master of this vw inventory wd actual proj mas.
    *
    * @param isMaster the is master of this vw inventory wd actual proj mas
    */
    @Override
    public void setIsMaster(java.lang.String isMaster) {
        _vwInventoryWdActualProjMas.setIsMaster(isMaster);
    }

    /**
    * Returns the company name of this vw inventory wd actual proj mas.
    *
    * @return the company name of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getCompanyName() {
        return _vwInventoryWdActualProjMas.getCompanyName();
    }

    /**
    * Sets the company name of this vw inventory wd actual proj mas.
    *
    * @param companyName the company name of this vw inventory wd actual proj mas
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _vwInventoryWdActualProjMas.setCompanyName(companyName);
    }

    /**
    * Returns the year of this vw inventory wd actual proj mas.
    *
    * @return the year of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getYear() {
        return _vwInventoryWdActualProjMas.getYear();
    }

    /**
    * Sets the year of this vw inventory wd actual proj mas.
    *
    * @param year the year of this vw inventory wd actual proj mas
    */
    @Override
    public void setYear(java.lang.String year) {
        _vwInventoryWdActualProjMas.setYear(year);
    }

    /**
    * Returns the item ID of this vw inventory wd actual proj mas.
    *
    * @return the item ID of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getItemId() {
        return _vwInventoryWdActualProjMas.getItemId();
    }

    /**
    * Sets the item ID of this vw inventory wd actual proj mas.
    *
    * @param itemId the item ID of this vw inventory wd actual proj mas
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwInventoryWdActualProjMas.setItemId(itemId);
    }

    /**
    * Returns the modified date of this vw inventory wd actual proj mas.
    *
    * @return the modified date of this vw inventory wd actual proj mas
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwInventoryWdActualProjMas.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw inventory wd actual proj mas.
    *
    * @param modifiedDate the modified date of this vw inventory wd actual proj mas
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwInventoryWdActualProjMas.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the organization key of this vw inventory wd actual proj mas.
    *
    * @return the organization key of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _vwInventoryWdActualProjMas.getOrganizationKey();
    }

    /**
    * Sets the organization key of this vw inventory wd actual proj mas.
    *
    * @param organizationKey the organization key of this vw inventory wd actual proj mas
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _vwInventoryWdActualProjMas.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the inventory wd actual proj mas sid of this vw inventory wd actual proj mas.
    *
    * @return the inventory wd actual proj mas sid of this vw inventory wd actual proj mas
    */
    @Override
    public int getInventoryWdActualProjMasSid() {
        return _vwInventoryWdActualProjMas.getInventoryWdActualProjMasSid();
    }

    /**
    * Sets the inventory wd actual proj mas sid of this vw inventory wd actual proj mas.
    *
    * @param inventoryWdActualProjMasSid the inventory wd actual proj mas sid of this vw inventory wd actual proj mas
    */
    @Override
    public void setInventoryWdActualProjMasSid(int inventoryWdActualProjMasSid) {
        _vwInventoryWdActualProjMas.setInventoryWdActualProjMasSid(inventoryWdActualProjMasSid);
    }

    /**
    * Returns the source of this vw inventory wd actual proj mas.
    *
    * @return the source of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getSource() {
        return _vwInventoryWdActualProjMas.getSource();
    }

    /**
    * Sets the source of this vw inventory wd actual proj mas.
    *
    * @param source the source of this vw inventory wd actual proj mas
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwInventoryWdActualProjMas.setSource(source);
    }

    /**
    * Returns the created by of this vw inventory wd actual proj mas.
    *
    * @return the created by of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwInventoryWdActualProjMas.getCreatedBy();
    }

    /**
    * Sets the created by of this vw inventory wd actual proj mas.
    *
    * @param createdBy the created by of this vw inventory wd actual proj mas
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwInventoryWdActualProjMas.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this vw inventory wd actual proj mas.
    *
    * @return the created date of this vw inventory wd actual proj mas
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwInventoryWdActualProjMas.getCreatedDate();
    }

    /**
    * Sets the created date of this vw inventory wd actual proj mas.
    *
    * @param createdDate the created date of this vw inventory wd actual proj mas
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwInventoryWdActualProjMas.setCreatedDate(createdDate);
    }

    /**
    * Returns the day of this vw inventory wd actual proj mas.
    *
    * @return the day of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getDay() {
        return _vwInventoryWdActualProjMas.getDay();
    }

    /**
    * Sets the day of this vw inventory wd actual proj mas.
    *
    * @param day the day of this vw inventory wd actual proj mas
    */
    @Override
    public void setDay(java.lang.String day) {
        _vwInventoryWdActualProjMas.setDay(day);
    }

    /**
    * Returns the add chg del indicator of this vw inventory wd actual proj mas.
    *
    * @return the add chg del indicator of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwInventoryWdActualProjMas.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw inventory wd actual proj mas.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw inventory wd actual proj mas
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwInventoryWdActualProjMas.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the units on hand of this vw inventory wd actual proj mas.
    *
    * @return the units on hand of this vw inventory wd actual proj mas
    */
    @Override
    public double getUnitsOnHand() {
        return _vwInventoryWdActualProjMas.getUnitsOnHand();
    }

    /**
    * Sets the units on hand of this vw inventory wd actual proj mas.
    *
    * @param unitsOnHand the units on hand of this vw inventory wd actual proj mas
    */
    @Override
    public void setUnitsOnHand(double unitsOnHand) {
        _vwInventoryWdActualProjMas.setUnitsOnHand(unitsOnHand);
    }

    /**
    * Returns the amount received of this vw inventory wd actual proj mas.
    *
    * @return the amount received of this vw inventory wd actual proj mas
    */
    @Override
    public double getAmountReceived() {
        return _vwInventoryWdActualProjMas.getAmountReceived();
    }

    /**
    * Sets the amount received of this vw inventory wd actual proj mas.
    *
    * @param amountReceived the amount received of this vw inventory wd actual proj mas
    */
    @Override
    public void setAmountReceived(double amountReceived) {
        _vwInventoryWdActualProjMas.setAmountReceived(amountReceived);
    }

    /**
    * Returns the modified by of this vw inventory wd actual proj mas.
    *
    * @return the modified by of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwInventoryWdActualProjMas.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw inventory wd actual proj mas.
    *
    * @param modifiedBy the modified by of this vw inventory wd actual proj mas
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwInventoryWdActualProjMas.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the month of this vw inventory wd actual proj mas.
    *
    * @return the month of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getMonth() {
        return _vwInventoryWdActualProjMas.getMonth();
    }

    /**
    * Sets the month of this vw inventory wd actual proj mas.
    *
    * @param month the month of this vw inventory wd actual proj mas
    */
    @Override
    public void setMonth(java.lang.String month) {
        _vwInventoryWdActualProjMas.setMonth(month);
    }

    /**
    * Returns the amount withdrawn of this vw inventory wd actual proj mas.
    *
    * @return the amount withdrawn of this vw inventory wd actual proj mas
    */
    @Override
    public double getAmountWithdrawn() {
        return _vwInventoryWdActualProjMas.getAmountWithdrawn();
    }

    /**
    * Sets the amount withdrawn of this vw inventory wd actual proj mas.
    *
    * @param amountWithdrawn the amount withdrawn of this vw inventory wd actual proj mas
    */
    @Override
    public void setAmountWithdrawn(double amountWithdrawn) {
        _vwInventoryWdActualProjMas.setAmountWithdrawn(amountWithdrawn);
    }

    /**
    * Returns the quantity received of this vw inventory wd actual proj mas.
    *
    * @return the quantity received of this vw inventory wd actual proj mas
    */
    @Override
    public double getQuantityReceived() {
        return _vwInventoryWdActualProjMas.getQuantityReceived();
    }

    /**
    * Sets the quantity received of this vw inventory wd actual proj mas.
    *
    * @param quantityReceived the quantity received of this vw inventory wd actual proj mas
    */
    @Override
    public void setQuantityReceived(double quantityReceived) {
        _vwInventoryWdActualProjMas.setQuantityReceived(quantityReceived);
    }

    /**
    * Returns the units withdrawn of this vw inventory wd actual proj mas.
    *
    * @return the units withdrawn of this vw inventory wd actual proj mas
    */
    @Override
    public double getUnitsWithdrawn() {
        return _vwInventoryWdActualProjMas.getUnitsWithdrawn();
    }

    /**
    * Sets the units withdrawn of this vw inventory wd actual proj mas.
    *
    * @param unitsWithdrawn the units withdrawn of this vw inventory wd actual proj mas
    */
    @Override
    public void setUnitsWithdrawn(double unitsWithdrawn) {
        _vwInventoryWdActualProjMas.setUnitsWithdrawn(unitsWithdrawn);
    }

    /**
    * Returns the country of this vw inventory wd actual proj mas.
    *
    * @return the country of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getCountry() {
        return _vwInventoryWdActualProjMas.getCountry();
    }

    /**
    * Sets the country of this vw inventory wd actual proj mas.
    *
    * @param country the country of this vw inventory wd actual proj mas
    */
    @Override
    public void setCountry(java.lang.String country) {
        _vwInventoryWdActualProjMas.setCountry(country);
    }

    /**
    * Returns the company ID of this vw inventory wd actual proj mas.
    *
    * @return the company ID of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getCompanyId() {
        return _vwInventoryWdActualProjMas.getCompanyId();
    }

    /**
    * Sets the company ID of this vw inventory wd actual proj mas.
    *
    * @param companyId the company ID of this vw inventory wd actual proj mas
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _vwInventoryWdActualProjMas.setCompanyId(companyId);
    }

    /**
    * Returns the is forecast of this vw inventory wd actual proj mas.
    *
    * @return the is forecast of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getIsForecast() {
        return _vwInventoryWdActualProjMas.getIsForecast();
    }

    /**
    * Sets the is forecast of this vw inventory wd actual proj mas.
    *
    * @param isForecast the is forecast of this vw inventory wd actual proj mas
    */
    @Override
    public void setIsForecast(java.lang.String isForecast) {
        _vwInventoryWdActualProjMas.setIsForecast(isForecast);
    }

    /**
    * Returns the forecast ver of this vw inventory wd actual proj mas.
    *
    * @return the forecast ver of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getForecastVer() {
        return _vwInventoryWdActualProjMas.getForecastVer();
    }

    /**
    * Sets the forecast ver of this vw inventory wd actual proj mas.
    *
    * @param forecastVer the forecast ver of this vw inventory wd actual proj mas
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _vwInventoryWdActualProjMas.setForecastVer(forecastVer);
    }

    /**
    * Returns the batch ID of this vw inventory wd actual proj mas.
    *
    * @return the batch ID of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwInventoryWdActualProjMas.getBatchId();
    }

    /**
    * Sets the batch ID of this vw inventory wd actual proj mas.
    *
    * @param batchId the batch ID of this vw inventory wd actual proj mas
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwInventoryWdActualProjMas.setBatchId(batchId);
    }

    /**
    * Returns the item name of this vw inventory wd actual proj mas.
    *
    * @return the item name of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getItemName() {
        return _vwInventoryWdActualProjMas.getItemName();
    }

    /**
    * Sets the item name of this vw inventory wd actual proj mas.
    *
    * @param itemName the item name of this vw inventory wd actual proj mas
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwInventoryWdActualProjMas.setItemName(itemName);
    }

    /**
    * Returns the amount on order of this vw inventory wd actual proj mas.
    *
    * @return the amount on order of this vw inventory wd actual proj mas
    */
    @Override
    public double getAmountOnOrder() {
        return _vwInventoryWdActualProjMas.getAmountOnOrder();
    }

    /**
    * Sets the amount on order of this vw inventory wd actual proj mas.
    *
    * @param amountOnOrder the amount on order of this vw inventory wd actual proj mas
    */
    @Override
    public void setAmountOnOrder(double amountOnOrder) {
        _vwInventoryWdActualProjMas.setAmountOnOrder(amountOnOrder);
    }

    /**
    * Returns the forecast name of this vw inventory wd actual proj mas.
    *
    * @return the forecast name of this vw inventory wd actual proj mas
    */
    @Override
    public java.lang.String getForecastName() {
        return _vwInventoryWdActualProjMas.getForecastName();
    }

    /**
    * Sets the forecast name of this vw inventory wd actual proj mas.
    *
    * @param forecastName the forecast name of this vw inventory wd actual proj mas
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _vwInventoryWdActualProjMas.setForecastName(forecastName);
    }

    @Override
    public boolean isNew() {
        return _vwInventoryWdActualProjMas.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwInventoryWdActualProjMas.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwInventoryWdActualProjMas.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwInventoryWdActualProjMas.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwInventoryWdActualProjMas.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwInventoryWdActualProjMas.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwInventoryWdActualProjMas.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwInventoryWdActualProjMas.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwInventoryWdActualProjMas.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwInventoryWdActualProjMas.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwInventoryWdActualProjMas.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwInventoryWdActualProjMasWrapper((VwInventoryWdActualProjMas) _vwInventoryWdActualProjMas.clone());
    }

    @Override
    public int compareTo(VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
        return _vwInventoryWdActualProjMas.compareTo(vwInventoryWdActualProjMas);
    }

    @Override
    public int hashCode() {
        return _vwInventoryWdActualProjMas.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwInventoryWdActualProjMas> toCacheModel() {
        return _vwInventoryWdActualProjMas.toCacheModel();
    }

    @Override
    public VwInventoryWdActualProjMas toEscapedModel() {
        return new VwInventoryWdActualProjMasWrapper(_vwInventoryWdActualProjMas.toEscapedModel());
    }

    @Override
    public VwInventoryWdActualProjMas toUnescapedModel() {
        return new VwInventoryWdActualProjMasWrapper(_vwInventoryWdActualProjMas.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwInventoryWdActualProjMas.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwInventoryWdActualProjMas.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwInventoryWdActualProjMas.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwInventoryWdActualProjMasWrapper)) {
            return false;
        }

        VwInventoryWdActualProjMasWrapper vwInventoryWdActualProjMasWrapper = (VwInventoryWdActualProjMasWrapper) obj;

        if (Validator.equals(_vwInventoryWdActualProjMas,
                    vwInventoryWdActualProjMasWrapper._vwInventoryWdActualProjMas)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwInventoryWdActualProjMas getWrappedVwInventoryWdActualProjMas() {
        return _vwInventoryWdActualProjMas;
    }

    @Override
    public VwInventoryWdActualProjMas getWrappedModel() {
        return _vwInventoryWdActualProjMas;
    }

    @Override
    public void resetOriginalValues() {
        _vwInventoryWdActualProjMas.resetOriginalValues();
    }
}
