package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HierarchyDefinition}.
 * </p>
 *
 * @author
 * @see HierarchyDefinition
 * @generated
 */
public class HierarchyDefinitionWrapper implements HierarchyDefinition,
    ModelWrapper<HierarchyDefinition> {
    private HierarchyDefinition _hierarchyDefinition;

    public HierarchyDefinitionWrapper(HierarchyDefinition hierarchyDefinition) {
        _hierarchyDefinition = hierarchyDefinition;
    }

    @Override
    public Class<?> getModelClass() {
        return HierarchyDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return HierarchyDefinition.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("noOfLevels", getNoOfLevels());
        attributes.put("hierarchyType", getHierarchyType());
        attributes.put("hierarchyName", getHierarchyName());
        attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("hierarchyCategory", getHierarchyCategory());

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

        Integer noOfLevels = (Integer) attributes.get("noOfLevels");

        if (noOfLevels != null) {
            setNoOfLevels(noOfLevels);
        }

        Integer hierarchyType = (Integer) attributes.get("hierarchyType");

        if (hierarchyType != null) {
            setHierarchyType(hierarchyType);
        }

        String hierarchyName = (String) attributes.get("hierarchyName");

        if (hierarchyName != null) {
            setHierarchyName(hierarchyName);
        }

        Integer hierarchyDefinitionSid = (Integer) attributes.get(
                "hierarchyDefinitionSid");

        if (hierarchyDefinitionSid != null) {
            setHierarchyDefinitionSid(hierarchyDefinitionSid);
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

        Integer hierarchyCategory = (Integer) attributes.get(
                "hierarchyCategory");

        if (hierarchyCategory != null) {
            setHierarchyCategory(hierarchyCategory);
        }
    }

    /**
    * Returns the primary key of this hierarchy definition.
    *
    * @return the primary key of this hierarchy definition
    */
    @Override
    public int getPrimaryKey() {
        return _hierarchyDefinition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hierarchy definition.
    *
    * @param primaryKey the primary key of this hierarchy definition
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _hierarchyDefinition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this hierarchy definition.
    *
    * @return the created date of this hierarchy definition
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _hierarchyDefinition.getCreatedDate();
    }

    /**
    * Sets the created date of this hierarchy definition.
    *
    * @param createdDate the created date of this hierarchy definition
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _hierarchyDefinition.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this hierarchy definition.
    *
    * @return the created by of this hierarchy definition
    */
    @Override
    public int getCreatedBy() {
        return _hierarchyDefinition.getCreatedBy();
    }

    /**
    * Sets the created by of this hierarchy definition.
    *
    * @param createdBy the created by of this hierarchy definition
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _hierarchyDefinition.setCreatedBy(createdBy);
    }

    /**
    * Returns the no of levels of this hierarchy definition.
    *
    * @return the no of levels of this hierarchy definition
    */
    @Override
    public int getNoOfLevels() {
        return _hierarchyDefinition.getNoOfLevels();
    }

    /**
    * Sets the no of levels of this hierarchy definition.
    *
    * @param noOfLevels the no of levels of this hierarchy definition
    */
    @Override
    public void setNoOfLevels(int noOfLevels) {
        _hierarchyDefinition.setNoOfLevels(noOfLevels);
    }

    /**
    * Returns the hierarchy type of this hierarchy definition.
    *
    * @return the hierarchy type of this hierarchy definition
    */
    @Override
    public int getHierarchyType() {
        return _hierarchyDefinition.getHierarchyType();
    }

    /**
    * Sets the hierarchy type of this hierarchy definition.
    *
    * @param hierarchyType the hierarchy type of this hierarchy definition
    */
    @Override
    public void setHierarchyType(int hierarchyType) {
        _hierarchyDefinition.setHierarchyType(hierarchyType);
    }

    /**
    * Returns the hierarchy name of this hierarchy definition.
    *
    * @return the hierarchy name of this hierarchy definition
    */
    @Override
    public java.lang.String getHierarchyName() {
        return _hierarchyDefinition.getHierarchyName();
    }

    /**
    * Sets the hierarchy name of this hierarchy definition.
    *
    * @param hierarchyName the hierarchy name of this hierarchy definition
    */
    @Override
    public void setHierarchyName(java.lang.String hierarchyName) {
        _hierarchyDefinition.setHierarchyName(hierarchyName);
    }

    /**
    * Returns the hierarchy definition sid of this hierarchy definition.
    *
    * @return the hierarchy definition sid of this hierarchy definition
    */
    @Override
    public int getHierarchyDefinitionSid() {
        return _hierarchyDefinition.getHierarchyDefinitionSid();
    }

    /**
    * Sets the hierarchy definition sid of this hierarchy definition.
    *
    * @param hierarchyDefinitionSid the hierarchy definition sid of this hierarchy definition
    */
    @Override
    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        _hierarchyDefinition.setHierarchyDefinitionSid(hierarchyDefinitionSid);
    }

    /**
    * Returns the version no of this hierarchy definition.
    *
    * @return the version no of this hierarchy definition
    */
    @Override
    public int getVersionNo() {
        return _hierarchyDefinition.getVersionNo();
    }

    /**
    * Sets the version no of this hierarchy definition.
    *
    * @param versionNo the version no of this hierarchy definition
    */
    @Override
    public void setVersionNo(int versionNo) {
        _hierarchyDefinition.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this hierarchy definition.
    *
    * @return the modified by of this hierarchy definition
    */
    @Override
    public int getModifiedBy() {
        return _hierarchyDefinition.getModifiedBy();
    }

    /**
    * Sets the modified by of this hierarchy definition.
    *
    * @param modifiedBy the modified by of this hierarchy definition
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _hierarchyDefinition.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this hierarchy definition.
    *
    * @return the modified date of this hierarchy definition
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _hierarchyDefinition.getModifiedDate();
    }

    /**
    * Sets the modified date of this hierarchy definition.
    *
    * @param modifiedDate the modified date of this hierarchy definition
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _hierarchyDefinition.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the hierarchy category of this hierarchy definition.
    *
    * @return the hierarchy category of this hierarchy definition
    */
    @Override
    public int getHierarchyCategory() {
        return _hierarchyDefinition.getHierarchyCategory();
    }

    /**
    * Sets the hierarchy category of this hierarchy definition.
    *
    * @param hierarchyCategory the hierarchy category of this hierarchy definition
    */
    @Override
    public void setHierarchyCategory(int hierarchyCategory) {
        _hierarchyDefinition.setHierarchyCategory(hierarchyCategory);
    }

    @Override
    public boolean isNew() {
        return _hierarchyDefinition.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _hierarchyDefinition.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _hierarchyDefinition.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _hierarchyDefinition.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _hierarchyDefinition.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _hierarchyDefinition.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _hierarchyDefinition.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _hierarchyDefinition.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _hierarchyDefinition.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _hierarchyDefinition.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _hierarchyDefinition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HierarchyDefinitionWrapper((HierarchyDefinition) _hierarchyDefinition.clone());
    }

    @Override
    public int compareTo(HierarchyDefinition hierarchyDefinition) {
        return _hierarchyDefinition.compareTo(hierarchyDefinition);
    }

    @Override
    public int hashCode() {
        return _hierarchyDefinition.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HierarchyDefinition> toCacheModel() {
        return _hierarchyDefinition.toCacheModel();
    }

    @Override
    public HierarchyDefinition toEscapedModel() {
        return new HierarchyDefinitionWrapper(_hierarchyDefinition.toEscapedModel());
    }

    @Override
    public HierarchyDefinition toUnescapedModel() {
        return new HierarchyDefinitionWrapper(_hierarchyDefinition.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _hierarchyDefinition.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _hierarchyDefinition.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _hierarchyDefinition.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HierarchyDefinitionWrapper)) {
            return false;
        }

        HierarchyDefinitionWrapper hierarchyDefinitionWrapper = (HierarchyDefinitionWrapper) obj;

        if (Validator.equals(_hierarchyDefinition,
                    hierarchyDefinitionWrapper._hierarchyDefinition)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HierarchyDefinition getWrappedHierarchyDefinition() {
        return _hierarchyDefinition;
    }

    @Override
    public HierarchyDefinition getWrappedModel() {
        return _hierarchyDefinition;
    }

    @Override
    public void resetOriginalValues() {
        _hierarchyDefinition.resetOriginalValues();
    }
}
