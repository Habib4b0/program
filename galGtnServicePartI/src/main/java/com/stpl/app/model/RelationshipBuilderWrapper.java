package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RelationshipBuilder}.
 * </p>
 *
 * @author
 * @see RelationshipBuilder
 * @generated
 */
public class RelationshipBuilderWrapper implements RelationshipBuilder,
    ModelWrapper<RelationshipBuilder> {
    private RelationshipBuilder _relationshipBuilder;

    public RelationshipBuilderWrapper(RelationshipBuilder relationshipBuilder) {
        _relationshipBuilder = relationshipBuilder;
    }

    @Override
    public Class<?> getModelClass() {
        return RelationshipBuilder.class;
    }

    @Override
    public String getModelClassName() {
        return RelationshipBuilder.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("relationshipDescription", getRelationshipDescription());
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
    * Returns the primary key of this relationship builder.
    *
    * @return the primary key of this relationship builder
    */
    @Override
    public int getPrimaryKey() {
        return _relationshipBuilder.getPrimaryKey();
    }

    /**
    * Sets the primary key of this relationship builder.
    *
    * @param primaryKey the primary key of this relationship builder
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _relationshipBuilder.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the start date of this relationship builder.
    *
    * @return the start date of this relationship builder
    */
    @Override
    public java.util.Date getStartDate() {
        return _relationshipBuilder.getStartDate();
    }

    /**
    * Sets the start date of this relationship builder.
    *
    * @param startDate the start date of this relationship builder
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _relationshipBuilder.setStartDate(startDate);
    }

    /**
    * Returns the created date of this relationship builder.
    *
    * @return the created date of this relationship builder
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _relationshipBuilder.getCreatedDate();
    }

    /**
    * Sets the created date of this relationship builder.
    *
    * @param createdDate the created date of this relationship builder
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _relationshipBuilder.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this relationship builder.
    *
    * @return the created by of this relationship builder
    */
    @Override
    public int getCreatedBy() {
        return _relationshipBuilder.getCreatedBy();
    }

    /**
    * Sets the created by of this relationship builder.
    *
    * @param createdBy the created by of this relationship builder
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _relationshipBuilder.setCreatedBy(createdBy);
    }

    /**
    * Returns the relationship description of this relationship builder.
    *
    * @return the relationship description of this relationship builder
    */
    @Override
    public java.lang.String getRelationshipDescription() {
        return _relationshipBuilder.getRelationshipDescription();
    }

    /**
    * Sets the relationship description of this relationship builder.
    *
    * @param relationshipDescription the relationship description of this relationship builder
    */
    @Override
    public void setRelationshipDescription(
        java.lang.String relationshipDescription) {
        _relationshipBuilder.setRelationshipDescription(relationshipDescription);
    }

    /**
    * Returns the hierarchy definition sid of this relationship builder.
    *
    * @return the hierarchy definition sid of this relationship builder
    */
    @Override
    public int getHierarchyDefinitionSid() {
        return _relationshipBuilder.getHierarchyDefinitionSid();
    }

    /**
    * Sets the hierarchy definition sid of this relationship builder.
    *
    * @param hierarchyDefinitionSid the hierarchy definition sid of this relationship builder
    */
    @Override
    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        _relationshipBuilder.setHierarchyDefinitionSid(hierarchyDefinitionSid);
    }

    /**
    * Returns the version no of this relationship builder.
    *
    * @return the version no of this relationship builder
    */
    @Override
    public int getVersionNo() {
        return _relationshipBuilder.getVersionNo();
    }

    /**
    * Sets the version no of this relationship builder.
    *
    * @param versionNo the version no of this relationship builder
    */
    @Override
    public void setVersionNo(int versionNo) {
        _relationshipBuilder.setVersionNo(versionNo);
    }

    /**
    * Returns the relationship name of this relationship builder.
    *
    * @return the relationship name of this relationship builder
    */
    @Override
    public java.lang.String getRelationshipName() {
        return _relationshipBuilder.getRelationshipName();
    }

    /**
    * Sets the relationship name of this relationship builder.
    *
    * @param relationshipName the relationship name of this relationship builder
    */
    @Override
    public void setRelationshipName(java.lang.String relationshipName) {
        _relationshipBuilder.setRelationshipName(relationshipName);
    }

    /**
    * Returns the relationship builder sid of this relationship builder.
    *
    * @return the relationship builder sid of this relationship builder
    */
    @Override
    public int getRelationshipBuilderSid() {
        return _relationshipBuilder.getRelationshipBuilderSid();
    }

    /**
    * Sets the relationship builder sid of this relationship builder.
    *
    * @param relationshipBuilderSid the relationship builder sid of this relationship builder
    */
    @Override
    public void setRelationshipBuilderSid(int relationshipBuilderSid) {
        _relationshipBuilder.setRelationshipBuilderSid(relationshipBuilderSid);
    }

    /**
    * Returns the hierarchy version of this relationship builder.
    *
    * @return the hierarchy version of this relationship builder
    */
    @Override
    public int getHierarchyVersion() {
        return _relationshipBuilder.getHierarchyVersion();
    }

    /**
    * Sets the hierarchy version of this relationship builder.
    *
    * @param hierarchyVersion the hierarchy version of this relationship builder
    */
    @Override
    public void setHierarchyVersion(int hierarchyVersion) {
        _relationshipBuilder.setHierarchyVersion(hierarchyVersion);
    }

    /**
    * Returns the modified by of this relationship builder.
    *
    * @return the modified by of this relationship builder
    */
    @Override
    public int getModifiedBy() {
        return _relationshipBuilder.getModifiedBy();
    }

    /**
    * Sets the modified by of this relationship builder.
    *
    * @param modifiedBy the modified by of this relationship builder
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _relationshipBuilder.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this relationship builder.
    *
    * @return the modified date of this relationship builder
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _relationshipBuilder.getModifiedDate();
    }

    /**
    * Sets the modified date of this relationship builder.
    *
    * @param modifiedDate the modified date of this relationship builder
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _relationshipBuilder.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the relationship type of this relationship builder.
    *
    * @return the relationship type of this relationship builder
    */
    @Override
    public int getRelationshipType() {
        return _relationshipBuilder.getRelationshipType();
    }

    /**
    * Sets the relationship type of this relationship builder.
    *
    * @param relationshipType the relationship type of this relationship builder
    */
    @Override
    public void setRelationshipType(int relationshipType) {
        _relationshipBuilder.setRelationshipType(relationshipType);
    }

    /**
    * Returns the build type of this relationship builder.
    *
    * @return the build type of this relationship builder
    */
    @Override
    public java.lang.String getBuildType() {
        return _relationshipBuilder.getBuildType();
    }

    /**
    * Sets the build type of this relationship builder.
    *
    * @param buildType the build type of this relationship builder
    */
    @Override
    public void setBuildType(java.lang.String buildType) {
        _relationshipBuilder.setBuildType(buildType);
    }

    @Override
    public boolean isNew() {
        return _relationshipBuilder.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _relationshipBuilder.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _relationshipBuilder.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _relationshipBuilder.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _relationshipBuilder.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _relationshipBuilder.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _relationshipBuilder.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _relationshipBuilder.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _relationshipBuilder.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _relationshipBuilder.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _relationshipBuilder.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RelationshipBuilderWrapper((RelationshipBuilder) _relationshipBuilder.clone());
    }

    @Override
    public int compareTo(RelationshipBuilder relationshipBuilder) {
        return _relationshipBuilder.compareTo(relationshipBuilder);
    }

    @Override
    public int hashCode() {
        return _relationshipBuilder.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RelationshipBuilder> toCacheModel() {
        return _relationshipBuilder.toCacheModel();
    }

    @Override
    public RelationshipBuilder toEscapedModel() {
        return new RelationshipBuilderWrapper(_relationshipBuilder.toEscapedModel());
    }

    @Override
    public RelationshipBuilder toUnescapedModel() {
        return new RelationshipBuilderWrapper(_relationshipBuilder.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _relationshipBuilder.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _relationshipBuilder.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _relationshipBuilder.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RelationshipBuilderWrapper)) {
            return false;
        }

        RelationshipBuilderWrapper relationshipBuilderWrapper = (RelationshipBuilderWrapper) obj;

        if (Validator.equals(_relationshipBuilder,
                    relationshipBuilderWrapper._relationshipBuilder)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RelationshipBuilder getWrappedRelationshipBuilder() {
        return _relationshipBuilder;
    }

    @Override
    public RelationshipBuilder getWrappedModel() {
        return _relationshipBuilder;
    }

    @Override
    public void resetOriginalValues() {
        _relationshipBuilder.resetOriginalValues();
    }
}
