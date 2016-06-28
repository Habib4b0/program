package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldInventoryWdProjMas}.
 * </p>
 *
 * @author
 * @see IvldInventoryWdProjMas
 * @generated
 */
public class IvldInventoryWdProjMasWrapper implements IvldInventoryWdProjMas,
    ModelWrapper<IvldInventoryWdProjMas> {
    private IvldInventoryWdProjMas _ivldInventoryWdProjMas;

    public IvldInventoryWdProjMasWrapper(
        IvldInventoryWdProjMas ivldInventoryWdProjMas) {
        _ivldInventoryWdProjMas = ivldInventoryWdProjMas;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldInventoryWdProjMas.class;
    }

    @Override
    public String getModelClassName() {
        return IvldInventoryWdProjMas.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("inventoryWdProjMasIntfId", getInventoryWdProjMasIntfId());
        attributes.put("week", getWeek());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("day", getDay());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("ivldInventoryWdProjMasSid",
            getIvldInventoryWdProjMasSid());
        attributes.put("month", getMonth());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("forecastName", getForecastName());
        attributes.put("amountWithdrawn", getAmountWithdrawn());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer inventoryWdProjMasIntfId = (Integer) attributes.get(
                "inventoryWdProjMasIntfId");

        if (inventoryWdProjMasIntfId != null) {
            setInventoryWdProjMasIntfId(inventoryWdProjMasIntfId);
        }

        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        String unitsWithdrawn = (String) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
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

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
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

        Integer ivldInventoryWdProjMasSid = (Integer) attributes.get(
                "ivldInventoryWdProjMasSid");

        if (ivldInventoryWdProjMasSid != null) {
            setIvldInventoryWdProjMasSid(ivldInventoryWdProjMasSid);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
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
    * Returns the primary key of this ivld inventory wd proj mas.
    *
    * @return the primary key of this ivld inventory wd proj mas
    */
    @Override
    public int getPrimaryKey() {
        return _ivldInventoryWdProjMas.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld inventory wd proj mas.
    *
    * @param primaryKey the primary key of this ivld inventory wd proj mas
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldInventoryWdProjMas.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the inventory wd proj mas intf ID of this ivld inventory wd proj mas.
    *
    * @return the inventory wd proj mas intf ID of this ivld inventory wd proj mas
    */
    @Override
    public int getInventoryWdProjMasIntfId() {
        return _ivldInventoryWdProjMas.getInventoryWdProjMasIntfId();
    }

    /**
    * Sets the inventory wd proj mas intf ID of this ivld inventory wd proj mas.
    *
    * @param inventoryWdProjMasIntfId the inventory wd proj mas intf ID of this ivld inventory wd proj mas
    */
    @Override
    public void setInventoryWdProjMasIntfId(int inventoryWdProjMasIntfId) {
        _ivldInventoryWdProjMas.setInventoryWdProjMasIntfId(inventoryWdProjMasIntfId);
    }

    /**
    * Returns the week of this ivld inventory wd proj mas.
    *
    * @return the week of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getWeek() {
        return _ivldInventoryWdProjMas.getWeek();
    }

    /**
    * Sets the week of this ivld inventory wd proj mas.
    *
    * @param week the week of this ivld inventory wd proj mas
    */
    @Override
    public void setWeek(java.lang.String week) {
        _ivldInventoryWdProjMas.setWeek(week);
    }

    /**
    * Returns the units withdrawn of this ivld inventory wd proj mas.
    *
    * @return the units withdrawn of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getUnitsWithdrawn() {
        return _ivldInventoryWdProjMas.getUnitsWithdrawn();
    }

    /**
    * Sets the units withdrawn of this ivld inventory wd proj mas.
    *
    * @param unitsWithdrawn the units withdrawn of this ivld inventory wd proj mas
    */
    @Override
    public void setUnitsWithdrawn(java.lang.String unitsWithdrawn) {
        _ivldInventoryWdProjMas.setUnitsWithdrawn(unitsWithdrawn);
    }

    /**
    * Returns the reason for failure of this ivld inventory wd proj mas.
    *
    * @return the reason for failure of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldInventoryWdProjMas.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld inventory wd proj mas.
    *
    * @param reasonForFailure the reason for failure of this ivld inventory wd proj mas
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldInventoryWdProjMas.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the country of this ivld inventory wd proj mas.
    *
    * @return the country of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getCountry() {
        return _ivldInventoryWdProjMas.getCountry();
    }

    /**
    * Sets the country of this ivld inventory wd proj mas.
    *
    * @param country the country of this ivld inventory wd proj mas
    */
    @Override
    public void setCountry(java.lang.String country) {
        _ivldInventoryWdProjMas.setCountry(country);
    }

    /**
    * Returns the year of this ivld inventory wd proj mas.
    *
    * @return the year of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getYear() {
        return _ivldInventoryWdProjMas.getYear();
    }

    /**
    * Sets the year of this ivld inventory wd proj mas.
    *
    * @param year the year of this ivld inventory wd proj mas
    */
    @Override
    public void setYear(java.lang.String year) {
        _ivldInventoryWdProjMas.setYear(year);
    }

    /**
    * Returns the item ID of this ivld inventory wd proj mas.
    *
    * @return the item ID of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldInventoryWdProjMas.getItemId();
    }

    /**
    * Sets the item ID of this ivld inventory wd proj mas.
    *
    * @param itemId the item ID of this ivld inventory wd proj mas
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldInventoryWdProjMas.setItemId(itemId);
    }

    /**
    * Returns the modified date of this ivld inventory wd proj mas.
    *
    * @return the modified date of this ivld inventory wd proj mas
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldInventoryWdProjMas.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld inventory wd proj mas.
    *
    * @param modifiedDate the modified date of this ivld inventory wd proj mas
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldInventoryWdProjMas.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the organization key of this ivld inventory wd proj mas.
    *
    * @return the organization key of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _ivldInventoryWdProjMas.getOrganizationKey();
    }

    /**
    * Sets the organization key of this ivld inventory wd proj mas.
    *
    * @param organizationKey the organization key of this ivld inventory wd proj mas
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _ivldInventoryWdProjMas.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the item identifier code qualifier of this ivld inventory wd proj mas.
    *
    * @return the item identifier code qualifier of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getItemIdentifierCodeQualifier() {
        return _ivldInventoryWdProjMas.getItemIdentifierCodeQualifier();
    }

    /**
    * Sets the item identifier code qualifier of this ivld inventory wd proj mas.
    *
    * @param itemIdentifierCodeQualifier the item identifier code qualifier of this ivld inventory wd proj mas
    */
    @Override
    public void setItemIdentifierCodeQualifier(
        java.lang.String itemIdentifierCodeQualifier) {
        _ivldInventoryWdProjMas.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
    }

    /**
    * Returns the source of this ivld inventory wd proj mas.
    *
    * @return the source of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getSource() {
        return _ivldInventoryWdProjMas.getSource();
    }

    /**
    * Sets the source of this ivld inventory wd proj mas.
    *
    * @param source the source of this ivld inventory wd proj mas
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldInventoryWdProjMas.setSource(source);
    }

    /**
    * Returns the created date of this ivld inventory wd proj mas.
    *
    * @return the created date of this ivld inventory wd proj mas
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldInventoryWdProjMas.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld inventory wd proj mas.
    *
    * @param createdDate the created date of this ivld inventory wd proj mas
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldInventoryWdProjMas.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ivld inventory wd proj mas.
    *
    * @return the created by of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldInventoryWdProjMas.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld inventory wd proj mas.
    *
    * @param createdBy the created by of this ivld inventory wd proj mas
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldInventoryWdProjMas.setCreatedBy(createdBy);
    }

    /**
    * Returns the day of this ivld inventory wd proj mas.
    *
    * @return the day of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getDay() {
        return _ivldInventoryWdProjMas.getDay();
    }

    /**
    * Sets the day of this ivld inventory wd proj mas.
    *
    * @param day the day of this ivld inventory wd proj mas
    */
    @Override
    public void setDay(java.lang.String day) {
        _ivldInventoryWdProjMas.setDay(day);
    }

    /**
    * Returns the forecast ver of this ivld inventory wd proj mas.
    *
    * @return the forecast ver of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getForecastVer() {
        return _ivldInventoryWdProjMas.getForecastVer();
    }

    /**
    * Sets the forecast ver of this ivld inventory wd proj mas.
    *
    * @param forecastVer the forecast ver of this ivld inventory wd proj mas
    */
    @Override
    public void setForecastVer(java.lang.String forecastVer) {
        _ivldInventoryWdProjMas.setForecastVer(forecastVer);
    }

    /**
    * Returns the batch ID of this ivld inventory wd proj mas.
    *
    * @return the batch ID of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldInventoryWdProjMas.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld inventory wd proj mas.
    *
    * @param batchId the batch ID of this ivld inventory wd proj mas
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldInventoryWdProjMas.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this ivld inventory wd proj mas.
    *
    * @return the add chg del indicator of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldInventoryWdProjMas.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld inventory wd proj mas.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld inventory wd proj mas
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldInventoryWdProjMas.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the item identifier of this ivld inventory wd proj mas.
    *
    * @return the item identifier of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getItemIdentifier() {
        return _ivldInventoryWdProjMas.getItemIdentifier();
    }

    /**
    * Sets the item identifier of this ivld inventory wd proj mas.
    *
    * @param itemIdentifier the item identifier of this ivld inventory wd proj mas
    */
    @Override
    public void setItemIdentifier(java.lang.String itemIdentifier) {
        _ivldInventoryWdProjMas.setItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the error field of this ivld inventory wd proj mas.
    *
    * @return the error field of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldInventoryWdProjMas.getErrorField();
    }

    /**
    * Sets the error field of this ivld inventory wd proj mas.
    *
    * @param errorField the error field of this ivld inventory wd proj mas
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldInventoryWdProjMas.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld inventory wd proj mas.
    *
    * @return the error code of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldInventoryWdProjMas.getErrorCode();
    }

    /**
    * Sets the error code of this ivld inventory wd proj mas.
    *
    * @param errorCode the error code of this ivld inventory wd proj mas
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldInventoryWdProjMas.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld inventory wd proj mas.
    *
    * @return the intf inserted date of this ivld inventory wd proj mas
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldInventoryWdProjMas.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld inventory wd proj mas.
    *
    * @param intfInsertedDate the intf inserted date of this ivld inventory wd proj mas
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldInventoryWdProjMas.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld inventory wd proj mas.
    *
    * @return the modified by of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldInventoryWdProjMas.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld inventory wd proj mas.
    *
    * @param modifiedBy the modified by of this ivld inventory wd proj mas
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldInventoryWdProjMas.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the ivld inventory wd proj mas sid of this ivld inventory wd proj mas.
    *
    * @return the ivld inventory wd proj mas sid of this ivld inventory wd proj mas
    */
    @Override
    public int getIvldInventoryWdProjMasSid() {
        return _ivldInventoryWdProjMas.getIvldInventoryWdProjMasSid();
    }

    /**
    * Sets the ivld inventory wd proj mas sid of this ivld inventory wd proj mas.
    *
    * @param ivldInventoryWdProjMasSid the ivld inventory wd proj mas sid of this ivld inventory wd proj mas
    */
    @Override
    public void setIvldInventoryWdProjMasSid(int ivldInventoryWdProjMasSid) {
        _ivldInventoryWdProjMas.setIvldInventoryWdProjMasSid(ivldInventoryWdProjMasSid);
    }

    /**
    * Returns the month of this ivld inventory wd proj mas.
    *
    * @return the month of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getMonth() {
        return _ivldInventoryWdProjMas.getMonth();
    }

    /**
    * Sets the month of this ivld inventory wd proj mas.
    *
    * @param month the month of this ivld inventory wd proj mas
    */
    @Override
    public void setMonth(java.lang.String month) {
        _ivldInventoryWdProjMas.setMonth(month);
    }

    /**
    * Returns the reprocessed flag of this ivld inventory wd proj mas.
    *
    * @return the reprocessed flag of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldInventoryWdProjMas.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld inventory wd proj mas.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld inventory wd proj mas
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldInventoryWdProjMas.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the forecast name of this ivld inventory wd proj mas.
    *
    * @return the forecast name of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getForecastName() {
        return _ivldInventoryWdProjMas.getForecastName();
    }

    /**
    * Sets the forecast name of this ivld inventory wd proj mas.
    *
    * @param forecastName the forecast name of this ivld inventory wd proj mas
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _ivldInventoryWdProjMas.setForecastName(forecastName);
    }

    /**
    * Returns the amount withdrawn of this ivld inventory wd proj mas.
    *
    * @return the amount withdrawn of this ivld inventory wd proj mas
    */
    @Override
    public java.lang.String getAmountWithdrawn() {
        return _ivldInventoryWdProjMas.getAmountWithdrawn();
    }

    /**
    * Sets the amount withdrawn of this ivld inventory wd proj mas.
    *
    * @param amountWithdrawn the amount withdrawn of this ivld inventory wd proj mas
    */
    @Override
    public void setAmountWithdrawn(java.lang.String amountWithdrawn) {
        _ivldInventoryWdProjMas.setAmountWithdrawn(amountWithdrawn);
    }

    @Override
    public boolean isNew() {
        return _ivldInventoryWdProjMas.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldInventoryWdProjMas.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldInventoryWdProjMas.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldInventoryWdProjMas.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldInventoryWdProjMas.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldInventoryWdProjMas.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldInventoryWdProjMas.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldInventoryWdProjMas.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldInventoryWdProjMas.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldInventoryWdProjMas.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldInventoryWdProjMas.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldInventoryWdProjMasWrapper((IvldInventoryWdProjMas) _ivldInventoryWdProjMas.clone());
    }

    @Override
    public int compareTo(IvldInventoryWdProjMas ivldInventoryWdProjMas) {
        return _ivldInventoryWdProjMas.compareTo(ivldInventoryWdProjMas);
    }

    @Override
    public int hashCode() {
        return _ivldInventoryWdProjMas.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldInventoryWdProjMas> toCacheModel() {
        return _ivldInventoryWdProjMas.toCacheModel();
    }

    @Override
    public IvldInventoryWdProjMas toEscapedModel() {
        return new IvldInventoryWdProjMasWrapper(_ivldInventoryWdProjMas.toEscapedModel());
    }

    @Override
    public IvldInventoryWdProjMas toUnescapedModel() {
        return new IvldInventoryWdProjMasWrapper(_ivldInventoryWdProjMas.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldInventoryWdProjMas.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldInventoryWdProjMas.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldInventoryWdProjMas.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldInventoryWdProjMasWrapper)) {
            return false;
        }

        IvldInventoryWdProjMasWrapper ivldInventoryWdProjMasWrapper = (IvldInventoryWdProjMasWrapper) obj;

        if (Validator.equals(_ivldInventoryWdProjMas,
                    ivldInventoryWdProjMasWrapper._ivldInventoryWdProjMas)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldInventoryWdProjMas getWrappedIvldInventoryWdProjMas() {
        return _ivldInventoryWdProjMas;
    }

    @Override
    public IvldInventoryWdProjMas getWrappedModel() {
        return _ivldInventoryWdProjMas;
    }

    @Override
    public void resetOriginalValues() {
        _ivldInventoryWdProjMas.resetOriginalValues();
    }
}
