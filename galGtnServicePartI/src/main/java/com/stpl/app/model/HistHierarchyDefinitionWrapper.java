package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HistHierarchyDefinition}.
 * </p>
 *
 * @author
 * @see HistHierarchyDefinition
 * @generated
 */
public class HistHierarchyDefinitionWrapper implements HistHierarchyDefinition,
    ModelWrapper<HistHierarchyDefinition> {
    private HistHierarchyDefinition _histHierarchyDefinition;

    public HistHierarchyDefinitionWrapper(
        HistHierarchyDefinition histHierarchyDefinition) {
        _histHierarchyDefinition = histHierarchyDefinition;
    }

    @Override
    public Class<?> getModelClass() {
        return HistHierarchyDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return HistHierarchyDefinition.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("noOfLevels", getNoOfLevels());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("hierarchyType", getHierarchyType());
        attributes.put("hierarchyCategory", getHierarchyCategory());
        attributes.put("hierarchyName", getHierarchyName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer noOfLevels = (Integer) attributes.get("noOfLevels");

        if (noOfLevels != null) {
            setNoOfLevels(noOfLevels);
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

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer hierarchyDefinitionSid = (Integer) attributes.get(
                "hierarchyDefinitionSid");

        if (hierarchyDefinitionSid != null) {
            setHierarchyDefinitionSid(hierarchyDefinitionSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer hierarchyType = (Integer) attributes.get("hierarchyType");

        if (hierarchyType != null) {
            setHierarchyType(hierarchyType);
        }

        Integer hierarchyCategory = (Integer) attributes.get(
                "hierarchyCategory");

        if (hierarchyCategory != null) {
            setHierarchyCategory(hierarchyCategory);
        }

        String hierarchyName = (String) attributes.get("hierarchyName");

        if (hierarchyName != null) {
            setHierarchyName(hierarchyName);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this hist hierarchy definition.
    *
    * @return the primary key of this hist hierarchy definition
    */
    @Override
    public com.stpl.app.service.persistence.HistHierarchyDefinitionPK getPrimaryKey() {
        return _histHierarchyDefinition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hist hierarchy definition.
    *
    * @param primaryKey the primary key of this hist hierarchy definition
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK primaryKey) {
        _histHierarchyDefinition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the no of levels of this hist hierarchy definition.
    *
    * @return the no of levels of this hist hierarchy definition
    */
    @Override
    public int getNoOfLevels() {
        return _histHierarchyDefinition.getNoOfLevels();
    }

    /**
    * Sets the no of levels of this hist hierarchy definition.
    *
    * @param noOfLevels the no of levels of this hist hierarchy definition
    */
    @Override
    public void setNoOfLevels(int noOfLevels) {
        _histHierarchyDefinition.setNoOfLevels(noOfLevels);
    }

    /**
    * Returns the created by of this hist hierarchy definition.
    *
    * @return the created by of this hist hierarchy definition
    */
    @Override
    public int getCreatedBy() {
        return _histHierarchyDefinition.getCreatedBy();
    }

    /**
    * Sets the created by of this hist hierarchy definition.
    *
    * @param createdBy the created by of this hist hierarchy definition
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _histHierarchyDefinition.setCreatedBy(createdBy);
    }

    /**
    * Returns the action date of this hist hierarchy definition.
    *
    * @return the action date of this hist hierarchy definition
    */
    @Override
    public java.util.Date getActionDate() {
        return _histHierarchyDefinition.getActionDate();
    }

    /**
    * Sets the action date of this hist hierarchy definition.
    *
    * @param actionDate the action date of this hist hierarchy definition
    */
    @Override
    public void setActionDate(java.util.Date actionDate) {
        _histHierarchyDefinition.setActionDate(actionDate);
    }

    /**
    * Returns the action flag of this hist hierarchy definition.
    *
    * @return the action flag of this hist hierarchy definition
    */
    @Override
    public java.lang.String getActionFlag() {
        return _histHierarchyDefinition.getActionFlag();
    }

    /**
    * Sets the action flag of this hist hierarchy definition.
    *
    * @param actionFlag the action flag of this hist hierarchy definition
    */
    @Override
    public void setActionFlag(java.lang.String actionFlag) {
        _histHierarchyDefinition.setActionFlag(actionFlag);
    }

    /**
    * Returns the modified by of this hist hierarchy definition.
    *
    * @return the modified by of this hist hierarchy definition
    */
    @Override
    public int getModifiedBy() {
        return _histHierarchyDefinition.getModifiedBy();
    }

    /**
    * Sets the modified by of this hist hierarchy definition.
    *
    * @param modifiedBy the modified by of this hist hierarchy definition
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _histHierarchyDefinition.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the hierarchy definition sid of this hist hierarchy definition.
    *
    * @return the hierarchy definition sid of this hist hierarchy definition
    */
    @Override
    public int getHierarchyDefinitionSid() {
        return _histHierarchyDefinition.getHierarchyDefinitionSid();
    }

    /**
    * Sets the hierarchy definition sid of this hist hierarchy definition.
    *
    * @param hierarchyDefinitionSid the hierarchy definition sid of this hist hierarchy definition
    */
    @Override
    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        _histHierarchyDefinition.setHierarchyDefinitionSid(hierarchyDefinitionSid);
    }

    /**
    * Returns the created date of this hist hierarchy definition.
    *
    * @return the created date of this hist hierarchy definition
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _histHierarchyDefinition.getCreatedDate();
    }

    /**
    * Sets the created date of this hist hierarchy definition.
    *
    * @param createdDate the created date of this hist hierarchy definition
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _histHierarchyDefinition.setCreatedDate(createdDate);
    }

    /**
    * Returns the hierarchy type of this hist hierarchy definition.
    *
    * @return the hierarchy type of this hist hierarchy definition
    */
    @Override
    public int getHierarchyType() {
        return _histHierarchyDefinition.getHierarchyType();
    }

    /**
    * Sets the hierarchy type of this hist hierarchy definition.
    *
    * @param hierarchyType the hierarchy type of this hist hierarchy definition
    */
    @Override
    public void setHierarchyType(int hierarchyType) {
        _histHierarchyDefinition.setHierarchyType(hierarchyType);
    }

    /**
    * Returns the hierarchy category of this hist hierarchy definition.
    *
    * @return the hierarchy category of this hist hierarchy definition
    */
    @Override
    public int getHierarchyCategory() {
        return _histHierarchyDefinition.getHierarchyCategory();
    }

    /**
    * Sets the hierarchy category of this hist hierarchy definition.
    *
    * @param hierarchyCategory the hierarchy category of this hist hierarchy definition
    */
    @Override
    public void setHierarchyCategory(int hierarchyCategory) {
        _histHierarchyDefinition.setHierarchyCategory(hierarchyCategory);
    }

    /**
    * Returns the hierarchy name of this hist hierarchy definition.
    *
    * @return the hierarchy name of this hist hierarchy definition
    */
    @Override
    public java.lang.String getHierarchyName() {
        return _histHierarchyDefinition.getHierarchyName();
    }

    /**
    * Sets the hierarchy name of this hist hierarchy definition.
    *
    * @param hierarchyName the hierarchy name of this hist hierarchy definition
    */
    @Override
    public void setHierarchyName(java.lang.String hierarchyName) {
        _histHierarchyDefinition.setHierarchyName(hierarchyName);
    }

    /**
    * Returns the version no of this hist hierarchy definition.
    *
    * @return the version no of this hist hierarchy definition
    */
    @Override
    public int getVersionNo() {
        return _histHierarchyDefinition.getVersionNo();
    }

    /**
    * Sets the version no of this hist hierarchy definition.
    *
    * @param versionNo the version no of this hist hierarchy definition
    */
    @Override
    public void setVersionNo(int versionNo) {
        _histHierarchyDefinition.setVersionNo(versionNo);
    }

    /**
    * Returns the modified date of this hist hierarchy definition.
    *
    * @return the modified date of this hist hierarchy definition
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _histHierarchyDefinition.getModifiedDate();
    }

    /**
    * Sets the modified date of this hist hierarchy definition.
    *
    * @param modifiedDate the modified date of this hist hierarchy definition
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _histHierarchyDefinition.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _histHierarchyDefinition.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _histHierarchyDefinition.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _histHierarchyDefinition.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _histHierarchyDefinition.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _histHierarchyDefinition.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _histHierarchyDefinition.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _histHierarchyDefinition.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _histHierarchyDefinition.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _histHierarchyDefinition.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _histHierarchyDefinition.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _histHierarchyDefinition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HistHierarchyDefinitionWrapper((HistHierarchyDefinition) _histHierarchyDefinition.clone());
    }

    @Override
    public int compareTo(HistHierarchyDefinition histHierarchyDefinition) {
        return _histHierarchyDefinition.compareTo(histHierarchyDefinition);
    }

    @Override
    public int hashCode() {
        return _histHierarchyDefinition.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HistHierarchyDefinition> toCacheModel() {
        return _histHierarchyDefinition.toCacheModel();
    }

    @Override
    public HistHierarchyDefinition toEscapedModel() {
        return new HistHierarchyDefinitionWrapper(_histHierarchyDefinition.toEscapedModel());
    }

    @Override
    public HistHierarchyDefinition toUnescapedModel() {
        return new HistHierarchyDefinitionWrapper(_histHierarchyDefinition.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _histHierarchyDefinition.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _histHierarchyDefinition.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _histHierarchyDefinition.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistHierarchyDefinitionWrapper)) {
            return false;
        }

        HistHierarchyDefinitionWrapper histHierarchyDefinitionWrapper = (HistHierarchyDefinitionWrapper) obj;

        if (Validator.equals(_histHierarchyDefinition,
                    histHierarchyDefinitionWrapper._histHierarchyDefinition)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HistHierarchyDefinition getWrappedHistHierarchyDefinition() {
        return _histHierarchyDefinition;
    }

    @Override
    public HistHierarchyDefinition getWrappedModel() {
        return _histHierarchyDefinition;
    }

    @Override
    public void resetOriginalValues() {
        _histHierarchyDefinition.resetOriginalValues();
    }
}
