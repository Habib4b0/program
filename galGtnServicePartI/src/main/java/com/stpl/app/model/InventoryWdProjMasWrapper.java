package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link InventoryWdProjMas}.
 * </p>
 *
 * @author
 * @see InventoryWdProjMas
 * @generated
 */
public class InventoryWdProjMasWrapper implements InventoryWdProjMas,
    ModelWrapper<InventoryWdProjMas> {
    private InventoryWdProjMas _inventoryWdProjMas;

    public InventoryWdProjMasWrapper(InventoryWdProjMas inventoryWdProjMas) {
        _inventoryWdProjMas = inventoryWdProjMas;
    }

    @Override
    public Class<?> getModelClass() {
        return InventoryWdProjMas.class;
    }

    @Override
    public String getModelClassName() {
        return InventoryWdProjMas.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("week", getWeek());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("country", getCountry());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("inventoryWdProjMasSid", getInventoryWdProjMasSid());
        attributes.put("day", getDay());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("month", getMonth());
        attributes.put("forecastName", getForecastName());
        attributes.put("amountWithdrawn", getAmountWithdrawn());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String unitsWithdrawn = (String) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
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

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
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

        Integer inventoryWdProjMasSid = (Integer) attributes.get(
                "inventoryWdProjMasSid");

        if (inventoryWdProjMasSid != null) {
            setInventoryWdProjMasSid(inventoryWdProjMasSid);
        }

        String day = (String) attributes.get("day");

        if (day != null) {
            setDay(day);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        String amountWithdrawn = (String) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }
    }

    /**
    * Returns the primary key of this inventory wd proj mas.
    *
    * @return the primary key of this inventory wd proj mas
    */
    @Override
    public int getPrimaryKey() {
        return _inventoryWdProjMas.getPrimaryKey();
    }

    /**
    * Sets the primary key of this inventory wd proj mas.
    *
    * @param primaryKey the primary key of this inventory wd proj mas
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _inventoryWdProjMas.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the week of this inventory wd proj mas.
    *
    * @return the week of this inventory wd proj mas
    */
    @Override
    public java.lang.String getWeek() {
        return _inventoryWdProjMas.getWeek();
    }

    /**
    * Sets the week of this inventory wd proj mas.
    *
    * @param week the week of this inventory wd proj mas
    */
    @Override
    public void setWeek(java.lang.String week) {
        _inventoryWdProjMas.setWeek(week);
    }

    /**
    * Returns the item master sid of this inventory wd proj mas.
    *
    * @return the item master sid of this inventory wd proj mas
    */
    @Override
    public int getItemMasterSid() {
        return _inventoryWdProjMas.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this inventory wd proj mas.
    *
    * @param itemMasterSid the item master sid of this inventory wd proj mas
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _inventoryWdProjMas.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the units withdrawn of this inventory wd proj mas.
    *
    * @return the units withdrawn of this inventory wd proj mas
    */
    @Override
    public java.lang.String getUnitsWithdrawn() {
        return _inventoryWdProjMas.getUnitsWithdrawn();
    }

    /**
    * Sets the units withdrawn of this inventory wd proj mas.
    *
    * @param unitsWithdrawn the units withdrawn of this inventory wd proj mas
    */
    @Override
    public void setUnitsWithdrawn(java.lang.String unitsWithdrawn) {
        _inventoryWdProjMas.setUnitsWithdrawn(unitsWithdrawn);
    }

    /**
    * Returns the country of this inventory wd proj mas.
    *
    * @return the country of this inventory wd proj mas
    */
    @Override
    public java.lang.String getCountry() {
        return _inventoryWdProjMas.getCountry();
    }

    /**
    * Sets the country of this inventory wd proj mas.
    *
    * @param country the country of this inventory wd proj mas
    */
    @Override
    public void setCountry(java.lang.String country) {
        _inventoryWdProjMas.setCountry(country);
    }

    /**
    * Returns the year of this inventory wd proj mas.
    *
    * @return the year of this inventory wd proj mas
    */
    @Override
    public java.lang.String getYear() {
        return _inventoryWdProjMas.getYear();
    }

    /**
    * Sets the year of this inventory wd proj mas.
    *
    * @param year the year of this inventory wd proj mas
    */
    @Override
    public void setYear(java.lang.String year) {
        _inventoryWdProjMas.setYear(year);
    }

    /**
    * Returns the item ID of this inventory wd proj mas.
    *
    * @return the item ID of this inventory wd proj mas
    */
    @Override
    public java.lang.String getItemId() {
        return _inventoryWdProjMas.getItemId();
    }

    /**
    * Sets the item ID of this inventory wd proj mas.
    *
    * @param itemId the item ID of this inventory wd proj mas
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _inventoryWdProjMas.setItemId(itemId);
    }

    /**
    * Returns the modified date of this inventory wd proj mas.
    *
    * @return the modified date of this inventory wd proj mas
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _inventoryWdProjMas.getModifiedDate();
    }

    /**
    * Sets the modified date of this inventory wd proj mas.
    *
    * @param modifiedDate the modified date of this inventory wd proj mas
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _inventoryWdProjMas.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the organization key of this inventory wd proj mas.
    *
    * @return the organization key of this inventory wd proj mas
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _inventoryWdProjMas.getOrganizationKey();
    }

    /**
    * Sets the organization key of this inventory wd proj mas.
    *
    * @param organizationKey the organization key of this inventory wd proj mas
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _inventoryWdProjMas.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the record lock status of this inventory wd proj mas.
    *
    * @return the record lock status of this inventory wd proj mas
    */
    @Override
    public boolean getRecordLockStatus() {
        return _inventoryWdProjMas.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this inventory wd proj mas is record lock status.
    *
    * @return <code>true</code> if this inventory wd proj mas is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _inventoryWdProjMas.isRecordLockStatus();
    }

    /**
    * Sets whether this inventory wd proj mas is record lock status.
    *
    * @param recordLockStatus the record lock status of this inventory wd proj mas
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _inventoryWdProjMas.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the item identifier code qualifier of this inventory wd proj mas.
    *
    * @return the item identifier code qualifier of this inventory wd proj mas
    */
    @Override
    public java.lang.String getItemIdentifierCodeQualifier() {
        return _inventoryWdProjMas.getItemIdentifierCodeQualifier();
    }

    /**
    * Sets the item identifier code qualifier of this inventory wd proj mas.
    *
    * @param itemIdentifierCodeQualifier the item identifier code qualifier of this inventory wd proj mas
    */
    @Override
    public void setItemIdentifierCodeQualifier(
        java.lang.String itemIdentifierCodeQualifier) {
        _inventoryWdProjMas.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
    }

    /**
    * Returns the source of this inventory wd proj mas.
    *
    * @return the source of this inventory wd proj mas
    */
    @Override
    public java.lang.String getSource() {
        return _inventoryWdProjMas.getSource();
    }

    /**
    * Sets the source of this inventory wd proj mas.
    *
    * @param source the source of this inventory wd proj mas
    */
    @Override
    public void setSource(java.lang.String source) {
        _inventoryWdProjMas.setSource(source);
    }

    /**
    * Returns the created date of this inventory wd proj mas.
    *
    * @return the created date of this inventory wd proj mas
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _inventoryWdProjMas.getCreatedDate();
    }

    /**
    * Sets the created date of this inventory wd proj mas.
    *
    * @param createdDate the created date of this inventory wd proj mas
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _inventoryWdProjMas.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this inventory wd proj mas.
    *
    * @return the created by of this inventory wd proj mas
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _inventoryWdProjMas.getCreatedBy();
    }

    /**
    * Sets the created by of this inventory wd proj mas.
    *
    * @param createdBy the created by of this inventory wd proj mas
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _inventoryWdProjMas.setCreatedBy(createdBy);
    }

    /**
    * Returns the inventory wd proj mas sid of this inventory wd proj mas.
    *
    * @return the inventory wd proj mas sid of this inventory wd proj mas
    */
    @Override
    public int getInventoryWdProjMasSid() {
        return _inventoryWdProjMas.getInventoryWdProjMasSid();
    }

    /**
    * Sets the inventory wd proj mas sid of this inventory wd proj mas.
    *
    * @param inventoryWdProjMasSid the inventory wd proj mas sid of this inventory wd proj mas
    */
    @Override
    public void setInventoryWdProjMasSid(int inventoryWdProjMasSid) {
        _inventoryWdProjMas.setInventoryWdProjMasSid(inventoryWdProjMasSid);
    }

    /**
    * Returns the day of this inventory wd proj mas.
    *
    * @return the day of this inventory wd proj mas
    */
    @Override
    public java.lang.String getDay() {
        return _inventoryWdProjMas.getDay();
    }

    /**
    * Sets the day of this inventory wd proj mas.
    *
    * @param day the day of this inventory wd proj mas
    */
    @Override
    public void setDay(java.lang.String day) {
        _inventoryWdProjMas.setDay(day);
    }

    /**
    * Returns the forecast ver of this inventory wd proj mas.
    *
    * @return the forecast ver of this inventory wd proj mas
    */
    @Override
    public java.lang.String getForecastVer() {
        return _inventoryWdProjMas.getForecastVer();
    }

    /**
    * Sets the forecast ver of this inventory wd proj mas.
    *
    * @param forecastVer the forecast ver of this inventory wd proj mas
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _inventoryWdProjMas.setForecastVer(forecastVer);
    }

    /**
    * Returns the batch ID of this inventory wd proj mas.
    *
    * @return the batch ID of this inventory wd proj mas
    */
    @Override
    public java.lang.String getBatchId() {
        return _inventoryWdProjMas.getBatchId();
    }

    /**
    * Sets the batch ID of this inventory wd proj mas.
    *
    * @param batchId the batch ID of this inventory wd proj mas
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _inventoryWdProjMas.setBatchId(batchId);
    }

    /**
    * Returns the item identifier of this inventory wd proj mas.
    *
    * @return the item identifier of this inventory wd proj mas
    */
    @Override
    public java.lang.String getItemIdentifier() {
        return _inventoryWdProjMas.getItemIdentifier();
    }

    /**
    * Sets the item identifier of this inventory wd proj mas.
    *
    * @param itemIdentifier the item identifier of this inventory wd proj mas
    */
    @Override
    public void setItemIdentifier(java.lang.String itemIdentifier) {
        _inventoryWdProjMas.setItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the inbound status of this inventory wd proj mas.
    *
    * @return the inbound status of this inventory wd proj mas
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _inventoryWdProjMas.getInboundStatus();
    }

    /**
    * Sets the inbound status of this inventory wd proj mas.
    *
    * @param inboundStatus the inbound status of this inventory wd proj mas
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _inventoryWdProjMas.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the modified by of this inventory wd proj mas.
    *
    * @return the modified by of this inventory wd proj mas
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _inventoryWdProjMas.getModifiedBy();
    }

    /**
    * Sets the modified by of this inventory wd proj mas.
    *
    * @param modifiedBy the modified by of this inventory wd proj mas
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _inventoryWdProjMas.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the month of this inventory wd proj mas.
    *
    * @return the month of this inventory wd proj mas
    */
    @Override
    public java.lang.String getMonth() {
        return _inventoryWdProjMas.getMonth();
    }

    /**
    * Sets the month of this inventory wd proj mas.
    *
    * @param month the month of this inventory wd proj mas
    */
    @Override
    public void setMonth(java.lang.String month) {
        _inventoryWdProjMas.setMonth(month);
    }

    /**
    * Returns the forecast name of this inventory wd proj mas.
    *
    * @return the forecast name of this inventory wd proj mas
    */
    @Override
    public java.lang.String getForecastName() {
        return _inventoryWdProjMas.getForecastName();
    }

    /**
    * Sets the forecast name of this inventory wd proj mas.
    *
    * @param forecastName the forecast name of this inventory wd proj mas
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _inventoryWdProjMas.setForecastName(forecastName);
    }

    /**
    * Returns the amount withdrawn of this inventory wd proj mas.
    *
    * @return the amount withdrawn of this inventory wd proj mas
    */
    @Override
    public java.lang.String getAmountWithdrawn() {
        return _inventoryWdProjMas.getAmountWithdrawn();
    }

    /**
    * Sets the amount withdrawn of this inventory wd proj mas.
    *
    * @param amountWithdrawn the amount withdrawn of this inventory wd proj mas
    */
    @Override
    public void setAmountWithdrawn(java.lang.String amountWithdrawn) {
        _inventoryWdProjMas.setAmountWithdrawn(amountWithdrawn);
    }

    @Override
    public boolean isNew() {
        return _inventoryWdProjMas.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _inventoryWdProjMas.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _inventoryWdProjMas.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _inventoryWdProjMas.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _inventoryWdProjMas.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _inventoryWdProjMas.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _inventoryWdProjMas.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _inventoryWdProjMas.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _inventoryWdProjMas.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _inventoryWdProjMas.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _inventoryWdProjMas.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new InventoryWdProjMasWrapper((InventoryWdProjMas) _inventoryWdProjMas.clone());
    }

    @Override
    public int compareTo(InventoryWdProjMas inventoryWdProjMas) {
        return _inventoryWdProjMas.compareTo(inventoryWdProjMas);
    }

    @Override
    public int hashCode() {
        return _inventoryWdProjMas.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<InventoryWdProjMas> toCacheModel() {
        return _inventoryWdProjMas.toCacheModel();
    }

    @Override
    public InventoryWdProjMas toEscapedModel() {
        return new InventoryWdProjMasWrapper(_inventoryWdProjMas.toEscapedModel());
    }

    @Override
    public InventoryWdProjMas toUnescapedModel() {
        return new InventoryWdProjMasWrapper(_inventoryWdProjMas.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _inventoryWdProjMas.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _inventoryWdProjMas.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _inventoryWdProjMas.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof InventoryWdProjMasWrapper)) {
            return false;
        }

        InventoryWdProjMasWrapper inventoryWdProjMasWrapper = (InventoryWdProjMasWrapper) obj;

        if (Validator.equals(_inventoryWdProjMas,
                    inventoryWdProjMasWrapper._inventoryWdProjMas)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public InventoryWdProjMas getWrappedInventoryWdProjMas() {
        return _inventoryWdProjMas;
    }

    @Override
    public InventoryWdProjMas getWrappedModel() {
        return _inventoryWdProjMas;
    }

    @Override
    public void resetOriginalValues() {
        _inventoryWdProjMas.resetOriginalValues();
    }
}
