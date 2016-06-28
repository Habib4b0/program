package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HierarchyLevelValues}.
 * </p>
 *
 * @author
 * @see HierarchyLevelValues
 * @generated
 */
public class HierarchyLevelValuesWrapper implements HierarchyLevelValues,
    ModelWrapper<HierarchyLevelValues> {
    private HierarchyLevelValues _hierarchyLevelValues;

    public HierarchyLevelValuesWrapper(
        HierarchyLevelValues hierarchyLevelValues) {
        _hierarchyLevelValues = hierarchyLevelValues;
    }

    @Override
    public Class<?> getModelClass() {
        return HierarchyLevelValues.class;
    }

    @Override
    public String getModelClassName() {
        return HierarchyLevelValues.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("levelValues", getLevelValues());
        attributes.put("hierarchyLevelValuesSid", getHierarchyLevelValuesSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
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
    * Returns the primary key of this hierarchy level values.
    *
    * @return the primary key of this hierarchy level values
    */
    @Override
    public int getPrimaryKey() {
        return _hierarchyLevelValues.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hierarchy level values.
    *
    * @param primaryKey the primary key of this hierarchy level values
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _hierarchyLevelValues.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the level values of this hierarchy level values.
    *
    * @return the level values of this hierarchy level values
    */
    @Override
    public java.lang.String getLevelValues() {
        return _hierarchyLevelValues.getLevelValues();
    }

    /**
    * Sets the level values of this hierarchy level values.
    *
    * @param levelValues the level values of this hierarchy level values
    */
    @Override
    public void setLevelValues(java.lang.String levelValues) {
        _hierarchyLevelValues.setLevelValues(levelValues);
    }

    /**
    * Returns the hierarchy level values sid of this hierarchy level values.
    *
    * @return the hierarchy level values sid of this hierarchy level values
    */
    @Override
    public int getHierarchyLevelValuesSid() {
        return _hierarchyLevelValues.getHierarchyLevelValuesSid();
    }

    /**
    * Sets the hierarchy level values sid of this hierarchy level values.
    *
    * @param hierarchyLevelValuesSid the hierarchy level values sid of this hierarchy level values
    */
    @Override
    public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid) {
        _hierarchyLevelValues.setHierarchyLevelValuesSid(hierarchyLevelValuesSid);
    }

    /**
    * Returns the created date of this hierarchy level values.
    *
    * @return the created date of this hierarchy level values
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _hierarchyLevelValues.getCreatedDate();
    }

    /**
    * Sets the created date of this hierarchy level values.
    *
    * @param createdDate the created date of this hierarchy level values
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _hierarchyLevelValues.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this hierarchy level values.
    *
    * @return the created by of this hierarchy level values
    */
    @Override
    public int getCreatedBy() {
        return _hierarchyLevelValues.getCreatedBy();
    }

    /**
    * Sets the created by of this hierarchy level values.
    *
    * @param createdBy the created by of this hierarchy level values
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _hierarchyLevelValues.setCreatedBy(createdBy);
    }

    /**
    * Returns the hierarchy level definition sid of this hierarchy level values.
    *
    * @return the hierarchy level definition sid of this hierarchy level values
    */
    @Override
    public int getHierarchyLevelDefinitionSid() {
        return _hierarchyLevelValues.getHierarchyLevelDefinitionSid();
    }

    /**
    * Sets the hierarchy level definition sid of this hierarchy level values.
    *
    * @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hierarchy level values
    */
    @Override
    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        _hierarchyLevelValues.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
    }

    /**
    * Returns the version no of this hierarchy level values.
    *
    * @return the version no of this hierarchy level values
    */
    @Override
    public int getVersionNo() {
        return _hierarchyLevelValues.getVersionNo();
    }

    /**
    * Sets the version no of this hierarchy level values.
    *
    * @param versionNo the version no of this hierarchy level values
    */
    @Override
    public void setVersionNo(int versionNo) {
        _hierarchyLevelValues.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this hierarchy level values.
    *
    * @return the modified by of this hierarchy level values
    */
    @Override
    public int getModifiedBy() {
        return _hierarchyLevelValues.getModifiedBy();
    }

    /**
    * Sets the modified by of this hierarchy level values.
    *
    * @param modifiedBy the modified by of this hierarchy level values
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _hierarchyLevelValues.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this hierarchy level values.
    *
    * @return the modified date of this hierarchy level values
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _hierarchyLevelValues.getModifiedDate();
    }

    /**
    * Sets the modified date of this hierarchy level values.
    *
    * @param modifiedDate the modified date of this hierarchy level values
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _hierarchyLevelValues.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _hierarchyLevelValues.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _hierarchyLevelValues.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _hierarchyLevelValues.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _hierarchyLevelValues.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _hierarchyLevelValues.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _hierarchyLevelValues.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _hierarchyLevelValues.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _hierarchyLevelValues.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _hierarchyLevelValues.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _hierarchyLevelValues.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _hierarchyLevelValues.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HierarchyLevelValuesWrapper((HierarchyLevelValues) _hierarchyLevelValues.clone());
    }

    @Override
    public int compareTo(HierarchyLevelValues hierarchyLevelValues) {
        return _hierarchyLevelValues.compareTo(hierarchyLevelValues);
    }

    @Override
    public int hashCode() {
        return _hierarchyLevelValues.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HierarchyLevelValues> toCacheModel() {
        return _hierarchyLevelValues.toCacheModel();
    }

    @Override
    public HierarchyLevelValues toEscapedModel() {
        return new HierarchyLevelValuesWrapper(_hierarchyLevelValues.toEscapedModel());
    }

    @Override
    public HierarchyLevelValues toUnescapedModel() {
        return new HierarchyLevelValuesWrapper(_hierarchyLevelValues.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _hierarchyLevelValues.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _hierarchyLevelValues.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _hierarchyLevelValues.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HierarchyLevelValuesWrapper)) {
            return false;
        }

        HierarchyLevelValuesWrapper hierarchyLevelValuesWrapper = (HierarchyLevelValuesWrapper) obj;

        if (Validator.equals(_hierarchyLevelValues,
                    hierarchyLevelValuesWrapper._hierarchyLevelValues)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HierarchyLevelValues getWrappedHierarchyLevelValues() {
        return _hierarchyLevelValues;
    }

    @Override
    public HierarchyLevelValues getWrappedModel() {
        return _hierarchyLevelValues;
    }

    @Override
    public void resetOriginalValues() {
        _hierarchyLevelValues.resetOriginalValues();
    }
}
