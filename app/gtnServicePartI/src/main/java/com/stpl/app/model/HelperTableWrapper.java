package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HelperTable}.
 * </p>
 *
 * @author
 * @see HelperTable
 * @generated
 */
public class HelperTableWrapper implements HelperTable,
    ModelWrapper<HelperTable> {
    private HelperTable _helperTable;

    public HelperTableWrapper(HelperTable helperTable) {
        _helperTable = helperTable;
    }

    @Override
    public Class<?> getModelClass() {
        return HelperTable.class;
    }

    @Override
    public String getModelClassName() {
        return HelperTable.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("description", getDescription());
        attributes.put("refCount", getRefCount());
        attributes.put("listName", getListName());
        attributes.put("helperTableSid", getHelperTableSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("aliasName", getAliasName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Integer refCount = (Integer) attributes.get("refCount");

        if (refCount != null) {
            setRefCount(refCount);
        }

        String listName = (String) attributes.get("listName");

        if (listName != null) {
            setListName(listName);
        }

        Integer helperTableSid = (Integer) attributes.get("helperTableSid");

        if (helperTableSid != null) {
            setHelperTableSid(helperTableSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String aliasName = (String) attributes.get("aliasName");

        if (aliasName != null) {
            setAliasName(aliasName);
        }
    }

    /**
    * Returns the primary key of this helper table.
    *
    * @return the primary key of this helper table
    */
    @Override
    public int getPrimaryKey() {
        return _helperTable.getPrimaryKey();
    }

    /**
    * Sets the primary key of this helper table.
    *
    * @param primaryKey the primary key of this helper table
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _helperTable.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this helper table.
    *
    * @return the created date of this helper table
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _helperTable.getCreatedDate();
    }

    /**
    * Sets the created date of this helper table.
    *
    * @param createdDate the created date of this helper table
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _helperTable.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this helper table.
    *
    * @return the created by of this helper table
    */
    @Override
    public int getCreatedBy() {
        return _helperTable.getCreatedBy();
    }

    /**
    * Sets the created by of this helper table.
    *
    * @param createdBy the created by of this helper table
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _helperTable.setCreatedBy(createdBy);
    }

    /**
    * Returns the description of this helper table.
    *
    * @return the description of this helper table
    */
    @Override
    public java.lang.String getDescription() {
        return _helperTable.getDescription();
    }

    /**
    * Sets the description of this helper table.
    *
    * @param description the description of this helper table
    */
    @Override
    public void setDescription(java.lang.String description) {
        _helperTable.setDescription(description);
    }

    /**
    * Returns the ref count of this helper table.
    *
    * @return the ref count of this helper table
    */
    @Override
    public int getRefCount() {
        return _helperTable.getRefCount();
    }

    /**
    * Sets the ref count of this helper table.
    *
    * @param refCount the ref count of this helper table
    */
    @Override
    public void setRefCount(int refCount) {
        _helperTable.setRefCount(refCount);
    }

    /**
    * Returns the list name of this helper table.
    *
    * @return the list name of this helper table
    */
    @Override
    public java.lang.String getListName() {
        return _helperTable.getListName();
    }

    /**
    * Sets the list name of this helper table.
    *
    * @param listName the list name of this helper table
    */
    @Override
    public void setListName(java.lang.String listName) {
        _helperTable.setListName(listName);
    }

    /**
    * Returns the helper table sid of this helper table.
    *
    * @return the helper table sid of this helper table
    */
    @Override
    public int getHelperTableSid() {
        return _helperTable.getHelperTableSid();
    }

    /**
    * Sets the helper table sid of this helper table.
    *
    * @param helperTableSid the helper table sid of this helper table
    */
    @Override
    public void setHelperTableSid(int helperTableSid) {
        _helperTable.setHelperTableSid(helperTableSid);
    }

    /**
    * Returns the modified by of this helper table.
    *
    * @return the modified by of this helper table
    */
    @Override
    public int getModifiedBy() {
        return _helperTable.getModifiedBy();
    }

    /**
    * Sets the modified by of this helper table.
    *
    * @param modifiedBy the modified by of this helper table
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _helperTable.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this helper table.
    *
    * @return the modified date of this helper table
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _helperTable.getModifiedDate();
    }

    /**
    * Sets the modified date of this helper table.
    *
    * @param modifiedDate the modified date of this helper table
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _helperTable.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the alias name of this helper table.
    *
    * @return the alias name of this helper table
    */
    @Override
    public java.lang.String getAliasName() {
        return _helperTable.getAliasName();
    }

    /**
    * Sets the alias name of this helper table.
    *
    * @param aliasName the alias name of this helper table
    */
    @Override
    public void setAliasName(java.lang.String aliasName) {
        _helperTable.setAliasName(aliasName);
    }

    @Override
    public boolean isNew() {
        return _helperTable.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _helperTable.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _helperTable.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _helperTable.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _helperTable.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _helperTable.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _helperTable.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _helperTable.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _helperTable.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _helperTable.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _helperTable.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HelperTableWrapper((HelperTable) _helperTable.clone());
    }

    @Override
    public int compareTo(HelperTable helperTable) {
        return _helperTable.compareTo(helperTable);
    }

    @Override
    public int hashCode() {
        return _helperTable.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HelperTable> toCacheModel() {
        return _helperTable.toCacheModel();
    }

    @Override
    public HelperTable toEscapedModel() {
        return new HelperTableWrapper(_helperTable.toEscapedModel());
    }

    @Override
    public HelperTable toUnescapedModel() {
        return new HelperTableWrapper(_helperTable.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _helperTable.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _helperTable.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _helperTable.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HelperTableWrapper)) {
            return false;
        }

        HelperTableWrapper helperTableWrapper = (HelperTableWrapper) obj;

        if (Validator.equals(_helperTable, helperTableWrapper._helperTable)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HelperTable getWrappedHelperTable() {
        return _helperTable;
    }

    @Override
    public HelperTable getWrappedModel() {
        return _helperTable;
    }

    @Override
    public void resetOriginalValues() {
        _helperTable.resetOriginalValues();
    }
}
