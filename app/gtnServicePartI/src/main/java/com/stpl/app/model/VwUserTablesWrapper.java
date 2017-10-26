package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwUserTables}.
 * </p>
 *
 * @author
 * @see VwUserTables
 * @generated
 */
public class VwUserTablesWrapper implements VwUserTables,
    ModelWrapper<VwUserTables> {
    private VwUserTables _vwUserTables;

    public VwUserTablesWrapper(VwUserTables vwUserTables) {
        _vwUserTables = vwUserTables;
    }

    @Override
    public Class<?> getModelClass() {
        return VwUserTables.class;
    }

    @Override
    public String getModelClassName() {
        return VwUserTables.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uniqueId", getUniqueId());
        attributes.put("tableName", getTableName());
        attributes.put("columnName", getColumnName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer uniqueId = (Integer) attributes.get("uniqueId");

        if (uniqueId != null) {
            setUniqueId(uniqueId);
        }

        String tableName = (String) attributes.get("tableName");

        if (tableName != null) {
            setTableName(tableName);
        }

        String columnName = (String) attributes.get("columnName");

        if (columnName != null) {
            setColumnName(columnName);
        }
    }

    /**
    * Returns the primary key of this vw user tables.
    *
    * @return the primary key of this vw user tables
    */
    @Override
    public int getPrimaryKey() {
        return _vwUserTables.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw user tables.
    *
    * @param primaryKey the primary key of this vw user tables
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwUserTables.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the unique ID of this vw user tables.
    *
    * @return the unique ID of this vw user tables
    */
    @Override
    public int getUniqueId() {
        return _vwUserTables.getUniqueId();
    }

    /**
    * Sets the unique ID of this vw user tables.
    *
    * @param uniqueId the unique ID of this vw user tables
    */
    @Override
    public void setUniqueId(int uniqueId) {
        _vwUserTables.setUniqueId(uniqueId);
    }

    /**
    * Returns the table name of this vw user tables.
    *
    * @return the table name of this vw user tables
    */
    @Override
    public java.lang.String getTableName() {
        return _vwUserTables.getTableName();
    }

    /**
    * Sets the table name of this vw user tables.
    *
    * @param tableName the table name of this vw user tables
    */
    @Override
    public void setTableName(java.lang.String tableName) {
        _vwUserTables.setTableName(tableName);
    }

    /**
    * Returns the column name of this vw user tables.
    *
    * @return the column name of this vw user tables
    */
    @Override
    public java.lang.String getColumnName() {
        return _vwUserTables.getColumnName();
    }

    /**
    * Sets the column name of this vw user tables.
    *
    * @param columnName the column name of this vw user tables
    */
    @Override
    public void setColumnName(java.lang.String columnName) {
        _vwUserTables.setColumnName(columnName);
    }

    @Override
    public boolean isNew() {
        return _vwUserTables.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwUserTables.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwUserTables.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwUserTables.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwUserTables.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwUserTables.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwUserTables.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwUserTables.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwUserTables.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwUserTables.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwUserTables.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwUserTablesWrapper((VwUserTables) _vwUserTables.clone());
    }

    @Override
    public int compareTo(VwUserTables vwUserTables) {
        return _vwUserTables.compareTo(vwUserTables);
    }

    @Override
    public int hashCode() {
        return _vwUserTables.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwUserTables> toCacheModel() {
        return _vwUserTables.toCacheModel();
    }

    @Override
    public VwUserTables toEscapedModel() {
        return new VwUserTablesWrapper(_vwUserTables.toEscapedModel());
    }

    @Override
    public VwUserTables toUnescapedModel() {
        return new VwUserTablesWrapper(_vwUserTables.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwUserTables.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwUserTables.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwUserTables.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwUserTablesWrapper)) {
            return false;
        }

        VwUserTablesWrapper vwUserTablesWrapper = (VwUserTablesWrapper) obj;

        if (Validator.equals(_vwUserTables, vwUserTablesWrapper._vwUserTables)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwUserTables getWrappedVwUserTables() {
        return _vwUserTables;
    }

    @Override
    public VwUserTables getWrappedModel() {
        return _vwUserTables;
    }

    @Override
    public void resetOriginalValues() {
        _vwUserTables.resetOriginalValues();
    }
}
