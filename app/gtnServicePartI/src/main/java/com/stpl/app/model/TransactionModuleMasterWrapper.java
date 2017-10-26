package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TransactionModuleMaster}.
 * </p>
 *
 * @author
 * @see TransactionModuleMaster
 * @generated
 */
public class TransactionModuleMasterWrapper implements TransactionModuleMaster,
    ModelWrapper<TransactionModuleMaster> {
    private TransactionModuleMaster _transactionModuleMaster;

    public TransactionModuleMasterWrapper(
        TransactionModuleMaster transactionModuleMaster) {
        _transactionModuleMaster = transactionModuleMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return TransactionModuleMaster.class;
    }

    @Override
    public String getModelClassName() {
        return TransactionModuleMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("transactionModuleMasterSid",
            getTransactionModuleMasterSid());
        attributes.put("invalidTableName", getInvalidTableName());
        attributes.put("tableName", getTableName());
        attributes.put("moduleName", getModuleName());
        attributes.put("tabName", getTabName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer transactionModuleMasterSid = (Integer) attributes.get(
                "transactionModuleMasterSid");

        if (transactionModuleMasterSid != null) {
            setTransactionModuleMasterSid(transactionModuleMasterSid);
        }

        String invalidTableName = (String) attributes.get("invalidTableName");

        if (invalidTableName != null) {
            setInvalidTableName(invalidTableName);
        }

        String tableName = (String) attributes.get("tableName");

        if (tableName != null) {
            setTableName(tableName);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        String tabName = (String) attributes.get("tabName");

        if (tabName != null) {
            setTabName(tabName);
        }
    }

    /**
    * Returns the primary key of this transaction module master.
    *
    * @return the primary key of this transaction module master
    */
    @Override
    public int getPrimaryKey() {
        return _transactionModuleMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this transaction module master.
    *
    * @param primaryKey the primary key of this transaction module master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _transactionModuleMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the transaction module master sid of this transaction module master.
    *
    * @return the transaction module master sid of this transaction module master
    */
    @Override
    public int getTransactionModuleMasterSid() {
        return _transactionModuleMaster.getTransactionModuleMasterSid();
    }

    /**
    * Sets the transaction module master sid of this transaction module master.
    *
    * @param transactionModuleMasterSid the transaction module master sid of this transaction module master
    */
    @Override
    public void setTransactionModuleMasterSid(int transactionModuleMasterSid) {
        _transactionModuleMaster.setTransactionModuleMasterSid(transactionModuleMasterSid);
    }

    /**
    * Returns the invalid table name of this transaction module master.
    *
    * @return the invalid table name of this transaction module master
    */
    @Override
    public java.lang.String getInvalidTableName() {
        return _transactionModuleMaster.getInvalidTableName();
    }

    /**
    * Sets the invalid table name of this transaction module master.
    *
    * @param invalidTableName the invalid table name of this transaction module master
    */
    @Override
    public void setInvalidTableName(java.lang.String invalidTableName) {
        _transactionModuleMaster.setInvalidTableName(invalidTableName);
    }

    /**
    * Returns the table name of this transaction module master.
    *
    * @return the table name of this transaction module master
    */
    @Override
    public java.lang.String getTableName() {
        return _transactionModuleMaster.getTableName();
    }

    /**
    * Sets the table name of this transaction module master.
    *
    * @param tableName the table name of this transaction module master
    */
    @Override
    public void setTableName(java.lang.String tableName) {
        _transactionModuleMaster.setTableName(tableName);
    }

    /**
    * Returns the module name of this transaction module master.
    *
    * @return the module name of this transaction module master
    */
    @Override
    public java.lang.String getModuleName() {
        return _transactionModuleMaster.getModuleName();
    }

    /**
    * Sets the module name of this transaction module master.
    *
    * @param moduleName the module name of this transaction module master
    */
    @Override
    public void setModuleName(java.lang.String moduleName) {
        _transactionModuleMaster.setModuleName(moduleName);
    }

    /**
    * Returns the tab name of this transaction module master.
    *
    * @return the tab name of this transaction module master
    */
    @Override
    public java.lang.String getTabName() {
        return _transactionModuleMaster.getTabName();
    }

    /**
    * Sets the tab name of this transaction module master.
    *
    * @param tabName the tab name of this transaction module master
    */
    @Override
    public void setTabName(java.lang.String tabName) {
        _transactionModuleMaster.setTabName(tabName);
    }

    @Override
    public boolean isNew() {
        return _transactionModuleMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _transactionModuleMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _transactionModuleMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _transactionModuleMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _transactionModuleMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _transactionModuleMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _transactionModuleMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _transactionModuleMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _transactionModuleMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _transactionModuleMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _transactionModuleMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new TransactionModuleMasterWrapper((TransactionModuleMaster) _transactionModuleMaster.clone());
    }

    @Override
    public int compareTo(TransactionModuleMaster transactionModuleMaster) {
        return _transactionModuleMaster.compareTo(transactionModuleMaster);
    }

    @Override
    public int hashCode() {
        return _transactionModuleMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<TransactionModuleMaster> toCacheModel() {
        return _transactionModuleMaster.toCacheModel();
    }

    @Override
    public TransactionModuleMaster toEscapedModel() {
        return new TransactionModuleMasterWrapper(_transactionModuleMaster.toEscapedModel());
    }

    @Override
    public TransactionModuleMaster toUnescapedModel() {
        return new TransactionModuleMasterWrapper(_transactionModuleMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _transactionModuleMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _transactionModuleMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _transactionModuleMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TransactionModuleMasterWrapper)) {
            return false;
        }

        TransactionModuleMasterWrapper transactionModuleMasterWrapper = (TransactionModuleMasterWrapper) obj;

        if (Validator.equals(_transactionModuleMaster,
                    transactionModuleMasterWrapper._transactionModuleMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public TransactionModuleMaster getWrappedTransactionModuleMaster() {
        return _transactionModuleMaster;
    }

    @Override
    public TransactionModuleMaster getWrappedModel() {
        return _transactionModuleMaster;
    }

    @Override
    public void resetOriginalValues() {
        _transactionModuleMaster.resetOriginalValues();
    }
}
