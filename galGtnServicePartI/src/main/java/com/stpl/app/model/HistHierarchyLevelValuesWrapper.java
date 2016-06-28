package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HistHierarchyLevelValues}.
 * </p>
 *
 * @author
 * @see HistHierarchyLevelValues
 * @generated
 */
public class HistHierarchyLevelValuesWrapper implements HistHierarchyLevelValues,
    ModelWrapper<HistHierarchyLevelValues> {
    private HistHierarchyLevelValues _histHierarchyLevelValues;

    public HistHierarchyLevelValuesWrapper(
        HistHierarchyLevelValues histHierarchyLevelValues) {
        _histHierarchyLevelValues = histHierarchyLevelValues;
    }

    @Override
    public Class<?> getModelClass() {
        return HistHierarchyLevelValues.class;
    }

    @Override
    public String getModelClassName() {
        return HistHierarchyLevelValues.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("levelValues", getLevelValues());
        attributes.put("hierarchyLevelValuesSid", getHierarchyLevelValuesSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("hierarchyLevelDefinitionSid",
            getHierarchyLevelDefinitionSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String levelValues = (String) attributes.get("levelValues");

        if (levelValues != null) {
            setLevelValues(levelValues);
        }

        Integer hierarchyLevelValuesSid = (Integer) attributes.get(
                "hierarchyLevelValuesSid");

        if (hierarchyLevelValuesSid != null) {
            setHierarchyLevelValuesSid(hierarchyLevelValuesSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
        }

        Integer hierarchyLevelDefinitionSid = (Integer) attributes.get(
                "hierarchyLevelDefinitionSid");

        if (hierarchyLevelDefinitionSid != null) {
            setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this hist hierarchy level values.
    *
    * @return the primary key of this hist hierarchy level values
    */
    @Override
    public com.stpl.app.service.persistence.HistHierarchyLevelValuesPK getPrimaryKey() {
        return _histHierarchyLevelValues.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hist hierarchy level values.
    *
    * @param primaryKey the primary key of this hist hierarchy level values
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.HistHierarchyLevelValuesPK primaryKey) {
        _histHierarchyLevelValues.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the level values of this hist hierarchy level values.
    *
    * @return the level values of this hist hierarchy level values
    */
    @Override
    public java.lang.String getLevelValues() {
        return _histHierarchyLevelValues.getLevelValues();
    }

    /**
    * Sets the level values of this hist hierarchy level values.
    *
    * @param levelValues the level values of this hist hierarchy level values
    */
    @Override
    public void setLevelValues(java.lang.String levelValues) {
        _histHierarchyLevelValues.setLevelValues(levelValues);
    }

    /**
    * Returns the hierarchy level values sid of this hist hierarchy level values.
    *
    * @return the hierarchy level values sid of this hist hierarchy level values
    */
    @Override
    public int getHierarchyLevelValuesSid() {
        return _histHierarchyLevelValues.getHierarchyLevelValuesSid();
    }

    /**
    * Sets the hierarchy level values sid of this hist hierarchy level values.
    *
    * @param hierarchyLevelValuesSid the hierarchy level values sid of this hist hierarchy level values
    */
    @Override
    public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid) {
        _histHierarchyLevelValues.setHierarchyLevelValuesSid(hierarchyLevelValuesSid);
    }

    /**
    * Returns the created date of this hist hierarchy level values.
    *
    * @return the created date of this hist hierarchy level values
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _histHierarchyLevelValues.getCreatedDate();
    }

    /**
    * Sets the created date of this hist hierarchy level values.
    *
    * @param createdDate the created date of this hist hierarchy level values
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _histHierarchyLevelValues.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this hist hierarchy level values.
    *
    * @return the created by of this hist hierarchy level values
    */
    @Override
    public int getCreatedBy() {
        return _histHierarchyLevelValues.getCreatedBy();
    }

    /**
    * Sets the created by of this hist hierarchy level values.
    *
    * @param createdBy the created by of this hist hierarchy level values
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _histHierarchyLevelValues.setCreatedBy(createdBy);
    }

    /**
    * Returns the action date of this hist hierarchy level values.
    *
    * @return the action date of this hist hierarchy level values
    */
    @Override
    public java.util.Date getActionDate() {
        return _histHierarchyLevelValues.getActionDate();
    }

    /**
    * Sets the action date of this hist hierarchy level values.
    *
    * @param actionDate the action date of this hist hierarchy level values
    */
    @Override
    public void setActionDate(java.util.Date actionDate) {
        _histHierarchyLevelValues.setActionDate(actionDate);
    }

    /**
    * Returns the action flag of this hist hierarchy level values.
    *
    * @return the action flag of this hist hierarchy level values
    */
    @Override
    public java.lang.String getActionFlag() {
        return _histHierarchyLevelValues.getActionFlag();
    }

    /**
    * Sets the action flag of this hist hierarchy level values.
    *
    * @param actionFlag the action flag of this hist hierarchy level values
    */
    @Override
    public void setActionFlag(java.lang.String actionFlag) {
        _histHierarchyLevelValues.setActionFlag(actionFlag);
    }

    /**
    * Returns the hierarchy level definition sid of this hist hierarchy level values.
    *
    * @return the hierarchy level definition sid of this hist hierarchy level values
    */
    @Override
    public int getHierarchyLevelDefinitionSid() {
        return _histHierarchyLevelValues.getHierarchyLevelDefinitionSid();
    }

    /**
    * Sets the hierarchy level definition sid of this hist hierarchy level values.
    *
    * @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hist hierarchy level values
    */
    @Override
    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        _histHierarchyLevelValues.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
    }

    /**
    * Returns the version no of this hist hierarchy level values.
    *
    * @return the version no of this hist hierarchy level values
    */
    @Override
    public int getVersionNo() {
        return _histHierarchyLevelValues.getVersionNo();
    }

    /**
    * Sets the version no of this hist hierarchy level values.
    *
    * @param versionNo the version no of this hist hierarchy level values
    */
    @Override
    public void setVersionNo(int versionNo) {
        _histHierarchyLevelValues.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this hist hierarchy level values.
    *
    * @return the modified by of this hist hierarchy level values
    */
    @Override
    public int getModifiedBy() {
        return _histHierarchyLevelValues.getModifiedBy();
    }

    /**
    * Sets the modified by of this hist hierarchy level values.
    *
    * @param modifiedBy the modified by of this hist hierarchy level values
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _histHierarchyLevelValues.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this hist hierarchy level values.
    *
    * @return the modified date of this hist hierarchy level values
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _histHierarchyLevelValues.getModifiedDate();
    }

    /**
    * Sets the modified date of this hist hierarchy level values.
    *
    * @param modifiedDate the modified date of this hist hierarchy level values
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _histHierarchyLevelValues.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _histHierarchyLevelValues.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _histHierarchyLevelValues.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _histHierarchyLevelValues.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _histHierarchyLevelValues.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _histHierarchyLevelValues.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _histHierarchyLevelValues.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _histHierarchyLevelValues.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _histHierarchyLevelValues.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _histHierarchyLevelValues.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _histHierarchyLevelValues.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _histHierarchyLevelValues.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HistHierarchyLevelValuesWrapper((HistHierarchyLevelValues) _histHierarchyLevelValues.clone());
    }

    @Override
    public int compareTo(HistHierarchyLevelValues histHierarchyLevelValues) {
        return _histHierarchyLevelValues.compareTo(histHierarchyLevelValues);
    }

    @Override
    public int hashCode() {
        return _histHierarchyLevelValues.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HistHierarchyLevelValues> toCacheModel() {
        return _histHierarchyLevelValues.toCacheModel();
    }

    @Override
    public HistHierarchyLevelValues toEscapedModel() {
        return new HistHierarchyLevelValuesWrapper(_histHierarchyLevelValues.toEscapedModel());
    }

    @Override
    public HistHierarchyLevelValues toUnescapedModel() {
        return new HistHierarchyLevelValuesWrapper(_histHierarchyLevelValues.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _histHierarchyLevelValues.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _histHierarchyLevelValues.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _histHierarchyLevelValues.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistHierarchyLevelValuesWrapper)) {
            return false;
        }

        HistHierarchyLevelValuesWrapper histHierarchyLevelValuesWrapper = (HistHierarchyLevelValuesWrapper) obj;

        if (Validator.equals(_histHierarchyLevelValues,
                    histHierarchyLevelValuesWrapper._histHierarchyLevelValues)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HistHierarchyLevelValues getWrappedHistHierarchyLevelValues() {
        return _histHierarchyLevelValues;
    }

    @Override
    public HistHierarchyLevelValues getWrappedModel() {
        return _histHierarchyLevelValues;
    }

    @Override
    public void resetOriginalValues() {
        _histHierarchyLevelValues.resetOriginalValues();
    }
}
