package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link InventoryWdActualMas}.
 * </p>
 *
 * @author
 * @see InventoryWdActualMas
 * @generated
 */
public class InventoryWdActualMasWrapper implements InventoryWdActualMas,
    ModelWrapper<InventoryWdActualMas> {
    private InventoryWdActualMas _inventoryWdActualMas;

    public InventoryWdActualMasWrapper(
        InventoryWdActualMas inventoryWdActualMas) {
        _inventoryWdActualMas = inventoryWdActualMas;
    }

    @Override
    public Class<?> getModelClass() {
        return InventoryWdActualMas.class;
    }

    @Override
    public String getModelClassName() {
        return InventoryWdActualMas.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("quantityOnOrder", getQuantityOnOrder());
        attributes.put("week", getWeek());
        attributes.put("amountOnHand", getAmountOnHand());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("inventoryWdActualMasSid", getInventoryWdActualMasSid());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("day", getDay());
        attributes.put("unitsOnHand", getUnitsOnHand());
        attributes.put("amountReceived", getAmountReceived());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("month", getMonth());
        attributes.put("amountWithdrawn", getAmountWithdrawn());
        attributes.put("quantityReceived", getQuantityReceived());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("country", getCountry());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("batchId", getBatchId());
        attributes.put("amountOnOrder", getAmountOnOrder());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String quantityOnOrder = (String) attributes.get("quantityOnOrder");

        if (quantityOnOrder != null) {
            setQuantityOnOrder(quantityOnOrder);
        }

        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        String amountOnHand = (String) attributes.get("amountOnHand");

        if (amountOnHand != null) {
            setAmountOnHand(amountOnHand);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer inventoryWdActualMasSid = (Integer) attributes.get(
                "inventoryWdActualMasSid");

        if (inventoryWdActualMasSid != null) {
            setInventoryWdActualMasSid(inventoryWdActualMasSid);
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

        String unitsOnHand = (String) attributes.get("unitsOnHand");

        if (unitsOnHand != null) {
            setUnitsOnHand(unitsOnHand);
        }

        String amountReceived = (String) attributes.get("amountReceived");

        if (amountReceived != null) {
            setAmountReceived(amountReceived);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String amountWithdrawn = (String) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }

        String quantityReceived = (String) attributes.get("quantityReceived");

        if (quantityReceived != null) {
            setQuantityReceived(quantityReceived);
        }

        String unitsWithdrawn = (String) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
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

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String amountOnOrder = (String) attributes.get("amountOnOrder");

        if (amountOnOrder != null) {
            setAmountOnOrder(amountOnOrder);
        }
    }

    /**
    * Returns the primary key of this inventory wd actual mas.
    *
    * @return the primary key of this inventory wd actual mas
    */
    @Override
    public int getPrimaryKey() {
        return _inventoryWdActualMas.getPrimaryKey();
    }

    /**
    * Sets the primary key of this inventory wd actual mas.
    *
    * @param primaryKey the primary key of this inventory wd actual mas
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _inventoryWdActualMas.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the quantity on order of this inventory wd actual mas.
    *
    * @return the quantity on order of this inventory wd actual mas
    */
    @Override
    public java.lang.String getQuantityOnOrder() {
        return _inventoryWdActualMas.getQuantityOnOrder();
    }

    /**
    * Sets the quantity on order of this inventory wd actual mas.
    *
    * @param quantityOnOrder the quantity on order of this inventory wd actual mas
    */
    @Override
    public void setQuantityOnOrder(java.lang.String quantityOnOrder) {
        _inventoryWdActualMas.setQuantityOnOrder(quantityOnOrder);
    }

    /**
    * Returns the week of this inventory wd actual mas.
    *
    * @return the week of this inventory wd actual mas
    */
    @Override
    public java.lang.String getWeek() {
        return _inventoryWdActualMas.getWeek();
    }

    /**
    * Sets the week of this inventory wd actual mas.
    *
    * @param week the week of this inventory wd actual mas
    */
    @Override
    public void setWeek(java.lang.String week) {
        _inventoryWdActualMas.setWeek(week);
    }

    /**
    * Returns the amount on hand of this inventory wd actual mas.
    *
    * @return the amount on hand of this inventory wd actual mas
    */
    @Override
    public java.lang.String getAmountOnHand() {
        return _inventoryWdActualMas.getAmountOnHand();
    }

    /**
    * Sets the amount on hand of this inventory wd actual mas.
    *
    * @param amountOnHand the amount on hand of this inventory wd actual mas
    */
    @Override
    public void setAmountOnHand(java.lang.String amountOnHand) {
        _inventoryWdActualMas.setAmountOnHand(amountOnHand);
    }

    /**
    * Returns the item master sid of this inventory wd actual mas.
    *
    * @return the item master sid of this inventory wd actual mas
    */
    @Override
    public int getItemMasterSid() {
        return _inventoryWdActualMas.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this inventory wd actual mas.
    *
    * @param itemMasterSid the item master sid of this inventory wd actual mas
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _inventoryWdActualMas.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the inventory wd actual mas sid of this inventory wd actual mas.
    *
    * @return the inventory wd actual mas sid of this inventory wd actual mas
    */
    @Override
    public int getInventoryWdActualMasSid() {
        return _inventoryWdActualMas.getInventoryWdActualMasSid();
    }

    /**
    * Sets the inventory wd actual mas sid of this inventory wd actual mas.
    *
    * @param inventoryWdActualMasSid the inventory wd actual mas sid of this inventory wd actual mas
    */
    @Override
    public void setInventoryWdActualMasSid(int inventoryWdActualMasSid) {
        _inventoryWdActualMas.setInventoryWdActualMasSid(inventoryWdActualMasSid);
    }

    /**
    * Returns the year of this inventory wd actual mas.
    *
    * @return the year of this inventory wd actual mas
    */
    @Override
    public java.lang.String getYear() {
        return _inventoryWdActualMas.getYear();
    }

    /**
    * Sets the year of this inventory wd actual mas.
    *
    * @param year the year of this inventory wd actual mas
    */
    @Override
    public void setYear(java.lang.String year) {
        _inventoryWdActualMas.setYear(year);
    }

    /**
    * Returns the item ID of this inventory wd actual mas.
    *
    * @return the item ID of this inventory wd actual mas
    */
    @Override
    public java.lang.String getItemId() {
        return _inventoryWdActualMas.getItemId();
    }

    /**
    * Sets the item ID of this inventory wd actual mas.
    *
    * @param itemId the item ID of this inventory wd actual mas
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _inventoryWdActualMas.setItemId(itemId);
    }

    /**
    * Returns the modified date of this inventory wd actual mas.
    *
    * @return the modified date of this inventory wd actual mas
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _inventoryWdActualMas.getModifiedDate();
    }

    /**
    * Sets the modified date of this inventory wd actual mas.
    *
    * @param modifiedDate the modified date of this inventory wd actual mas
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _inventoryWdActualMas.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the organization key of this inventory wd actual mas.
    *
    * @return the organization key of this inventory wd actual mas
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _inventoryWdActualMas.getOrganizationKey();
    }

    /**
    * Sets the organization key of this inventory wd actual mas.
    *
    * @param organizationKey the organization key of this inventory wd actual mas
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _inventoryWdActualMas.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the source of this inventory wd actual mas.
    *
    * @return the source of this inventory wd actual mas
    */
    @Override
    public java.lang.String getSource() {
        return _inventoryWdActualMas.getSource();
    }

    /**
    * Sets the source of this inventory wd actual mas.
    *
    * @param source the source of this inventory wd actual mas
    */
    @Override
    public void setSource(java.lang.String source) {
        _inventoryWdActualMas.setSource(source);
    }

    /**
    * Returns the created by of this inventory wd actual mas.
    *
    * @return the created by of this inventory wd actual mas
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _inventoryWdActualMas.getCreatedBy();
    }

    /**
    * Sets the created by of this inventory wd actual mas.
    *
    * @param createdBy the created by of this inventory wd actual mas
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _inventoryWdActualMas.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this inventory wd actual mas.
    *
    * @return the created date of this inventory wd actual mas
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _inventoryWdActualMas.getCreatedDate();
    }

    /**
    * Sets the created date of this inventory wd actual mas.
    *
    * @param createdDate the created date of this inventory wd actual mas
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _inventoryWdActualMas.setCreatedDate(createdDate);
    }

    /**
    * Returns the day of this inventory wd actual mas.
    *
    * @return the day of this inventory wd actual mas
    */
    @Override
    public java.lang.String getDay() {
        return _inventoryWdActualMas.getDay();
    }

    /**
    * Sets the day of this inventory wd actual mas.
    *
    * @param day the day of this inventory wd actual mas
    */
    @Override
    public void setDay(java.lang.String day) {
        _inventoryWdActualMas.setDay(day);
    }

    /**
    * Returns the units on hand of this inventory wd actual mas.
    *
    * @return the units on hand of this inventory wd actual mas
    */
    @Override
    public java.lang.String getUnitsOnHand() {
        return _inventoryWdActualMas.getUnitsOnHand();
    }

    /**
    * Sets the units on hand of this inventory wd actual mas.
    *
    * @param unitsOnHand the units on hand of this inventory wd actual mas
    */
    @Override
    public void setUnitsOnHand(java.lang.String unitsOnHand) {
        _inventoryWdActualMas.setUnitsOnHand(unitsOnHand);
    }

    /**
    * Returns the amount received of this inventory wd actual mas.
    *
    * @return the amount received of this inventory wd actual mas
    */
    @Override
    public java.lang.String getAmountReceived() {
        return _inventoryWdActualMas.getAmountReceived();
    }

    /**
    * Sets the amount received of this inventory wd actual mas.
    *
    * @param amountReceived the amount received of this inventory wd actual mas
    */
    @Override
    public void setAmountReceived(java.lang.String amountReceived) {
        _inventoryWdActualMas.setAmountReceived(amountReceived);
    }

    /**
    * Returns the item identifier of this inventory wd actual mas.
    *
    * @return the item identifier of this inventory wd actual mas
    */
    @Override
    public java.lang.String getItemIdentifier() {
        return _inventoryWdActualMas.getItemIdentifier();
    }

    /**
    * Sets the item identifier of this inventory wd actual mas.
    *
    * @param itemIdentifier the item identifier of this inventory wd actual mas
    */
    @Override
    public void setItemIdentifier(java.lang.String itemIdentifier) {
        _inventoryWdActualMas.setItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the modified by of this inventory wd actual mas.
    *
    * @return the modified by of this inventory wd actual mas
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _inventoryWdActualMas.getModifiedBy();
    }

    /**
    * Sets the modified by of this inventory wd actual mas.
    *
    * @param modifiedBy the modified by of this inventory wd actual mas
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _inventoryWdActualMas.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this inventory wd actual mas.
    *
    * @return the inbound status of this inventory wd actual mas
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _inventoryWdActualMas.getInboundStatus();
    }

    /**
    * Sets the inbound status of this inventory wd actual mas.
    *
    * @param inboundStatus the inbound status of this inventory wd actual mas
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _inventoryWdActualMas.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the month of this inventory wd actual mas.
    *
    * @return the month of this inventory wd actual mas
    */
    @Override
    public java.lang.String getMonth() {
        return _inventoryWdActualMas.getMonth();
    }

    /**
    * Sets the month of this inventory wd actual mas.
    *
    * @param month the month of this inventory wd actual mas
    */
    @Override
    public void setMonth(java.lang.String month) {
        _inventoryWdActualMas.setMonth(month);
    }

    /**
    * Returns the amount withdrawn of this inventory wd actual mas.
    *
    * @return the amount withdrawn of this inventory wd actual mas
    */
    @Override
    public java.lang.String getAmountWithdrawn() {
        return _inventoryWdActualMas.getAmountWithdrawn();
    }

    /**
    * Sets the amount withdrawn of this inventory wd actual mas.
    *
    * @param amountWithdrawn the amount withdrawn of this inventory wd actual mas
    */
    @Override
    public void setAmountWithdrawn(java.lang.String amountWithdrawn) {
        _inventoryWdActualMas.setAmountWithdrawn(amountWithdrawn);
    }

    /**
    * Returns the quantity received of this inventory wd actual mas.
    *
    * @return the quantity received of this inventory wd actual mas
    */
    @Override
    public java.lang.String getQuantityReceived() {
        return _inventoryWdActualMas.getQuantityReceived();
    }

    /**
    * Sets the quantity received of this inventory wd actual mas.
    *
    * @param quantityReceived the quantity received of this inventory wd actual mas
    */
    @Override
    public void setQuantityReceived(java.lang.String quantityReceived) {
        _inventoryWdActualMas.setQuantityReceived(quantityReceived);
    }

    /**
    * Returns the units withdrawn of this inventory wd actual mas.
    *
    * @return the units withdrawn of this inventory wd actual mas
    */
    @Override
    public java.lang.String getUnitsWithdrawn() {
        return _inventoryWdActualMas.getUnitsWithdrawn();
    }

    /**
    * Sets the units withdrawn of this inventory wd actual mas.
    *
    * @param unitsWithdrawn the units withdrawn of this inventory wd actual mas
    */
    @Override
    public void setUnitsWithdrawn(java.lang.String unitsWithdrawn) {
        _inventoryWdActualMas.setUnitsWithdrawn(unitsWithdrawn);
    }

    /**
    * Returns the country of this inventory wd actual mas.
    *
    * @return the country of this inventory wd actual mas
    */
    @Override
    public java.lang.String getCountry() {
        return _inventoryWdActualMas.getCountry();
    }

    /**
    * Sets the country of this inventory wd actual mas.
    *
    * @param country the country of this inventory wd actual mas
    */
    @Override
    public void setCountry(java.lang.String country) {
        _inventoryWdActualMas.setCountry(country);
    }

    /**
    * Returns the record lock status of this inventory wd actual mas.
    *
    * @return the record lock status of this inventory wd actual mas
    */
    @Override
    public boolean getRecordLockStatus() {
        return _inventoryWdActualMas.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this inventory wd actual mas is record lock status.
    *
    * @return <code>true</code> if this inventory wd actual mas is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _inventoryWdActualMas.isRecordLockStatus();
    }

    /**
    * Sets whether this inventory wd actual mas is record lock status.
    *
    * @param recordLockStatus the record lock status of this inventory wd actual mas
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _inventoryWdActualMas.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the item identifier code qualifier of this inventory wd actual mas.
    *
    * @return the item identifier code qualifier of this inventory wd actual mas
    */
    @Override
    public java.lang.String getItemIdentifierCodeQualifier() {
        return _inventoryWdActualMas.getItemIdentifierCodeQualifier();
    }

    /**
    * Sets the item identifier code qualifier of this inventory wd actual mas.
    *
    * @param itemIdentifierCodeQualifier the item identifier code qualifier of this inventory wd actual mas
    */
    @Override
    public void setItemIdentifierCodeQualifier(
        java.lang.String itemIdentifierCodeQualifier) {
        _inventoryWdActualMas.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
    }

    /**
    * Returns the batch ID of this inventory wd actual mas.
    *
    * @return the batch ID of this inventory wd actual mas
    */
    @Override
    public java.lang.String getBatchId() {
        return _inventoryWdActualMas.getBatchId();
    }

    /**
    * Sets the batch ID of this inventory wd actual mas.
    *
    * @param batchId the batch ID of this inventory wd actual mas
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _inventoryWdActualMas.setBatchId(batchId);
    }

    /**
    * Returns the amount on order of this inventory wd actual mas.
    *
    * @return the amount on order of this inventory wd actual mas
    */
    @Override
    public java.lang.String getAmountOnOrder() {
        return _inventoryWdActualMas.getAmountOnOrder();
    }

    /**
    * Sets the amount on order of this inventory wd actual mas.
    *
    * @param amountOnOrder the amount on order of this inventory wd actual mas
    */
    @Override
    public void setAmountOnOrder(java.lang.String amountOnOrder) {
        _inventoryWdActualMas.setAmountOnOrder(amountOnOrder);
    }

    @Override
    public boolean isNew() {
        return _inventoryWdActualMas.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _inventoryWdActualMas.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _inventoryWdActualMas.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _inventoryWdActualMas.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _inventoryWdActualMas.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _inventoryWdActualMas.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _inventoryWdActualMas.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _inventoryWdActualMas.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _inventoryWdActualMas.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _inventoryWdActualMas.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _inventoryWdActualMas.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new InventoryWdActualMasWrapper((InventoryWdActualMas) _inventoryWdActualMas.clone());
    }

    @Override
    public int compareTo(InventoryWdActualMas inventoryWdActualMas) {
        return _inventoryWdActualMas.compareTo(inventoryWdActualMas);
    }

    @Override
    public int hashCode() {
        return _inventoryWdActualMas.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<InventoryWdActualMas> toCacheModel() {
        return _inventoryWdActualMas.toCacheModel();
    }

    @Override
    public InventoryWdActualMas toEscapedModel() {
        return new InventoryWdActualMasWrapper(_inventoryWdActualMas.toEscapedModel());
    }

    @Override
    public InventoryWdActualMas toUnescapedModel() {
        return new InventoryWdActualMasWrapper(_inventoryWdActualMas.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _inventoryWdActualMas.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _inventoryWdActualMas.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _inventoryWdActualMas.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof InventoryWdActualMasWrapper)) {
            return false;
        }

        InventoryWdActualMasWrapper inventoryWdActualMasWrapper = (InventoryWdActualMasWrapper) obj;

        if (Validator.equals(_inventoryWdActualMas,
                    inventoryWdActualMasWrapper._inventoryWdActualMas)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public InventoryWdActualMas getWrappedInventoryWdActualMas() {
        return _inventoryWdActualMas;
    }

    @Override
    public InventoryWdActualMas getWrappedModel() {
        return _inventoryWdActualMas;
    }

    @Override
    public void resetOriginalValues() {
        _inventoryWdActualMas.resetOriginalValues();
    }
}
