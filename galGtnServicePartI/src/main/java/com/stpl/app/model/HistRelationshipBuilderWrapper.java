package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HistRelationshipBuilder}.
 * </p>
 *
 * @author
 * @see HistRelationshipBuilder
 * @generated
 */
public class HistRelationshipBuilderWrapper implements HistRelationshipBuilder,
    ModelWrapper<HistRelationshipBuilder> {
    private HistRelationshipBuilder _histRelationshipBuilder;

    public HistRelationshipBuilderWrapper(
        HistRelationshipBuilder histRelationshipBuilder) {
        _histRelationshipBuilder = histRelationshipBuilder;
    }

    @Override
    public Class<?> getModelClass() {
        return HistRelationshipBuilder.class;
    }

    @Override
    public String getModelClassName() {
        return HistRelationshipBuilder.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("relationshipDescription", getRelationshipDescription());
        attributes.put("actionDate", getActionDate());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("relationshipName", getRelationshipName());
        attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
        attributes.put("hierarchyVersion", getHierarchyVersion());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("relationshipType", getRelationshipType());
        attributes.put("buildType", getBuildType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String relationshipDescription = (String) attributes.get(
                "relationshipDescription");

        if (relationshipDescription != null) {
            setRelationshipDescription(relationshipDescription);
        }

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
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

        String relationshipName = (String) attributes.get("relationshipName");

        if (relationshipName != null) {
            setRelationshipName(relationshipName);
        }

        Integer relationshipBuilderSid = (Integer) attributes.get(
                "relationshipBuilderSid");

        if (relationshipBuilderSid != null) {
            setRelationshipBuilderSid(relationshipBuilderSid);
        }

        Integer hierarchyVersion = (Integer) attributes.get("hierarchyVersion");

        if (hierarchyVersion != null) {
            setHierarchyVersion(hierarchyVersion);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer relationshipType = (Integer) attributes.get("relationshipType");

        if (relationshipType != null) {
            setRelationshipType(relationshipType);
        }

        String buildType = (String) attributes.get("buildType");

        if (buildType != null) {
            setBuildType(buildType);
        }
    }

    /**
    * Returns the primary key of this hist relationship builder.
    *
    * @return the primary key of this hist relationship builder
    */
    @Override
    public com.stpl.app.service.persistence.HistRelationshipBuilderPK getPrimaryKey() {
        return _histRelationshipBuilder.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hist relationship builder.
    *
    * @param primaryKey the primary key of this hist relationship builder
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.HistRelationshipBuilderPK primaryKey) {
        _histRelationshipBuilder.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the start date of this hist relationship builder.
    *
    * @return the start date of this hist relationship builder
    */
    @Override
    public java.util.Date getStartDate() {
        return _histRelationshipBuilder.getStartDate();
    }

    /**
    * Sets the start date of this hist relationship builder.
    *
    * @param startDate the start date of this hist relationship builder
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _histRelationshipBuilder.setStartDate(startDate);
    }

    /**
    * Returns the created date of this hist relationship builder.
    *
    * @return the created date of this hist relationship builder
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _histRelationshipBuilder.getCreatedDate();
    }

    /**
    * Sets the created date of this hist relationship builder.
    *
    * @param createdDate the created date of this hist relationship builder
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _histRelationshipBuilder.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this hist relationship builder.
    *
    * @return the created by of this hist relationship builder
    */
    @Override
    public int getCreatedBy() {
        return _histRelationshipBuilder.getCreatedBy();
    }

    /**
    * Sets the created by of this hist relationship builder.
    *
    * @param createdBy the created by of this hist relationship builder
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _histRelationshipBuilder.setCreatedBy(createdBy);
    }

    /**
    * Returns the relationship description of this hist relationship builder.
    *
    * @return the relationship description of this hist relationship builder
    */
    @Override
    public java.lang.String getRelationshipDescription() {
        return _histRelationshipBuilder.getRelationshipDescription();
    }

    /**
    * Sets the relationship description of this hist relationship builder.
    *
    * @param relationshipDescription the relationship description of this hist relationship builder
    */
    @Override
    public void setRelationshipDescription(
        java.lang.String relationshipDescription) {
        _histRelationshipBuilder.setRelationshipDescription(relationshipDescription);
    }

    /**
    * Returns the action date of this hist relationship builder.
    *
    * @return the action date of this hist relationship builder
    */
    @Override
    public java.util.Date getActionDate() {
        return _histRelationshipBuilder.getActionDate();
    }

    /**
    * Sets the action date of this hist relationship builder.
    *
    * @param actionDate the action date of this hist relationship builder
    */
    @Override
    public void setActionDate(java.util.Date actionDate) {
        _histRelationshipBuilder.setActionDate(actionDate);
    }

    /**
    * Returns the action flag of this hist relationship builder.
    *
    * @return the action flag of this hist relationship builder
    */
    @Override
    public java.lang.String getActionFlag() {
        return _histRelationshipBuilder.getActionFlag();
    }

    /**
    * Sets the action flag of this hist relationship builder.
    *
    * @param actionFlag the action flag of this hist relationship builder
    */
    @Override
    public void setActionFlag(java.lang.String actionFlag) {
        _histRelationshipBuilder.setActionFlag(actionFlag);
    }

    /**
    * Returns the hierarchy definition sid of this hist relationship builder.
    *
    * @return the hierarchy definition sid of this hist relationship builder
    */
    @Override
    public int getHierarchyDefinitionSid() {
        return _histRelationshipBuilder.getHierarchyDefinitionSid();
    }

    /**
    * Sets the hierarchy definition sid of this hist relationship builder.
    *
    * @param hierarchyDefinitionSid the hierarchy definition sid of this hist relationship builder
    */
    @Override
    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        _histRelationshipBuilder.setHierarchyDefinitionSid(hierarchyDefinitionSid);
    }

    /**
    * Returns the version no of this hist relationship builder.
    *
    * @return the version no of this hist relationship builder
    */
    @Override
    public int getVersionNo() {
        return _histRelationshipBuilder.getVersionNo();
    }

    /**
    * Sets the version no of this hist relationship builder.
    *
    * @param versionNo the version no of this hist relationship builder
    */
    @Override
    public void setVersionNo(int versionNo) {
        _histRelationshipBuilder.setVersionNo(versionNo);
    }

    /**
    * Returns the relationship name of this hist relationship builder.
    *
    * @return the relationship name of this hist relationship builder
    */
    @Override
    public java.lang.String getRelationshipName() {
        return _histRelationshipBuilder.getRelationshipName();
    }

    /**
    * Sets the relationship name of this hist relationship builder.
    *
    * @param relationshipName the relationship name of this hist relationship builder
    */
    @Override
    public void setRelationshipName(java.lang.String relationshipName) {
        _histRelationshipBuilder.setRelationshipName(relationshipName);
    }

    /**
    * Returns the relationship builder sid of this hist relationship builder.
    *
    * @return the relationship builder sid of this hist relationship builder
    */
    @Override
    public int getRelationshipBuilderSid() {
        return _histRelationshipBuilder.getRelationshipBuilderSid();
    }

    /**
    * Sets the relationship builder sid of this hist relationship builder.
    *
    * @param relationshipBuilderSid the relationship builder sid of this hist relationship builder
    */
    @Override
    public void setRelationshipBuilderSid(int relationshipBuilderSid) {
        _histRelationshipBuilder.setRelationshipBuilderSid(relationshipBuilderSid);
    }

    /**
    * Returns the hierarchy version of this hist relationship builder.
    *
    * @return the hierarchy version of this hist relationship builder
    */
    @Override
    public int getHierarchyVersion() {
        return _histRelationshipBuilder.getHierarchyVersion();
    }

    /**
    * Sets the hierarchy version of this hist relationship builder.
    *
    * @param hierarchyVersion the hierarchy version of this hist relationship builder
    */
    @Override
    public void setHierarchyVersion(int hierarchyVersion) {
        _histRelationshipBuilder.setHierarchyVersion(hierarchyVersion);
    }

    /**
    * Returns the modified by of this hist relationship builder.
    *
    * @return the modified by of this hist relationship builder
    */
    @Override
    public int getModifiedBy() {
        return _histRelationshipBuilder.getModifiedBy();
    }

    /**
    * Sets the modified by of this hist relationship builder.
    *
    * @param modifiedBy the modified by of this hist relationship builder
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _histRelationshipBuilder.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this hist relationship builder.
    *
    * @return the modified date of this hist relationship builder
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _histRelationshipBuilder.getModifiedDate();
    }

    /**
    * Sets the modified date of this hist relationship builder.
    *
    * @param modifiedDate the modified date of this hist relationship builder
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _histRelationshipBuilder.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the relationship type of this hist relationship builder.
    *
    * @return the relationship type of this hist relationship builder
    */
    @Override
    public int getRelationshipType() {
        return _histRelationshipBuilder.getRelationshipType();
    }

    /**
    * Sets the relationship type of this hist relationship builder.
    *
    * @param relationshipType the relationship type of this hist relationship builder
    */
    @Override
    public void setRelationshipType(int relationshipType) {
        _histRelationshipBuilder.setRelationshipType(relationshipType);
    }

    /**
    * Returns the build type of this hist relationship builder.
    *
    * @return the build type of this hist relationship builder
    */
    @Override
    public java.lang.String getBuildType() {
        return _histRelationshipBuilder.getBuildType();
    }

    /**
    * Sets the build type of this hist relationship builder.
    *
    * @param buildType the build type of this hist relationship builder
    */
    @Override
    public void setBuildType(java.lang.String buildType) {
        _histRelationshipBuilder.setBuildType(buildType);
    }

    @Override
    public boolean isNew() {
        return _histRelationshipBuilder.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _histRelationshipBuilder.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _histRelationshipBuilder.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _histRelationshipBuilder.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _histRelationshipBuilder.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _histRelationshipBuilder.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _histRelationshipBuilder.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _histRelationshipBuilder.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _histRelationshipBuilder.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _histRelationshipBuilder.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _histRelationshipBuilder.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HistRelationshipBuilderWrapper((HistRelationshipBuilder) _histRelationshipBuilder.clone());
    }

    @Override
    public int compareTo(HistRelationshipBuilder histRelationshipBuilder) {
        return _histRelationshipBuilder.compareTo(histRelationshipBuilder);
    }

    @Override
    public int hashCode() {
        return _histRelationshipBuilder.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HistRelationshipBuilder> toCacheModel() {
        return _histRelationshipBuilder.toCacheModel();
    }

    @Override
    public HistRelationshipBuilder toEscapedModel() {
        return new HistRelationshipBuilderWrapper(_histRelationshipBuilder.toEscapedModel());
    }

    @Override
    public HistRelationshipBuilder toUnescapedModel() {
        return new HistRelationshipBuilderWrapper(_histRelationshipBuilder.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _histRelationshipBuilder.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _histRelationshipBuilder.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _histRelationshipBuilder.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistRelationshipBuilderWrapper)) {
            return false;
        }

        HistRelationshipBuilderWrapper histRelationshipBuilderWrapper = (HistRelationshipBuilderWrapper) obj;

        if (Validator.equals(_histRelationshipBuilder,
                    histRelationshipBuilderWrapper._histRelationshipBuilder)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HistRelationshipBuilder getWrappedHistRelationshipBuilder() {
        return _histRelationshipBuilder;
    }

    @Override
    public HistRelationshipBuilder getWrappedModel() {
        return _histRelationshipBuilder;
    }

    @Override
    public void resetOriginalValues() {
        _histRelationshipBuilder.resetOriginalValues();
    }
}
