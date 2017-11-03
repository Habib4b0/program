package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RelationshipLevelDefinition}.
 * </p>
 *
 * @author
 * @see RelationshipLevelDefinition
 * @generated
 */
public class RelationshipLevelDefinitionWrapper
    implements RelationshipLevelDefinition,
        ModelWrapper<RelationshipLevelDefinition> {
    private RelationshipLevelDefinition _relationshipLevelDefinition;

    public RelationshipLevelDefinitionWrapper(
        RelationshipLevelDefinition relationshipLevelDefinition) {
        _relationshipLevelDefinition = relationshipLevelDefinition;
    }

    @Override
    public Class<?> getModelClass() {
        return RelationshipLevelDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return RelationshipLevelDefinition.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("relationshipLevelValues", getRelationshipLevelValues());
        attributes.put("hierarchyLevelDefinitionSid",
            getHierarchyLevelDefinitionSid());
        attributes.put("parentNode", getParentNode());
        attributes.put("versionNo", getVersionNo());
        attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("levelNo", getLevelNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("hierarchyNo", getHierarchyNo());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("flag", getFlag());
        attributes.put("levelName", getLevelName());
        attributes.put("parentHierarchyNo", getParentHierarchyNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String relationshipLevelValues = (String) attributes.get(
                "relationshipLevelValues");

        if (relationshipLevelValues != null) {
            setRelationshipLevelValues(relationshipLevelValues);
        }

        Integer hierarchyLevelDefinitionSid = (Integer) attributes.get(
                "hierarchyLevelDefinitionSid");

        if (hierarchyLevelDefinitionSid != null) {
            setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
        }

        String parentNode = (String) attributes.get("parentNode");

        if (parentNode != null) {
            setParentNode(parentNode);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer relationshipBuilderSid = (Integer) attributes.get(
                "relationshipBuilderSid");

        if (relationshipBuilderSid != null) {
            setRelationshipBuilderSid(relationshipBuilderSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String levelNo = (String) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String hierarchyNo = (String) attributes.get("hierarchyNo");

        if (hierarchyNo != null) {
            setHierarchyNo(hierarchyNo);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }

        String flag = (String) attributes.get("flag");

        if (flag != null) {
            setFlag(flag);
        }

        String levelName = (String) attributes.get("levelName");

        if (levelName != null) {
            setLevelName(levelName);
        }

        String parentHierarchyNo = (String) attributes.get("parentHierarchyNo");

        if (parentHierarchyNo != null) {
            setParentHierarchyNo(parentHierarchyNo);
        }
    }

    /**
    * Returns the primary key of this relationship level definition.
    *
    * @return the primary key of this relationship level definition
    */
    @Override
    public int getPrimaryKey() {
        return _relationshipLevelDefinition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this relationship level definition.
    *
    * @param primaryKey the primary key of this relationship level definition
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _relationshipLevelDefinition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the relationship level values of this relationship level definition.
    *
    * @return the relationship level values of this relationship level definition
    */
    @Override
    public java.lang.String getRelationshipLevelValues() {
        return _relationshipLevelDefinition.getRelationshipLevelValues();
    }

    /**
    * Sets the relationship level values of this relationship level definition.
    *
    * @param relationshipLevelValues the relationship level values of this relationship level definition
    */
    @Override
    public void setRelationshipLevelValues(
        java.lang.String relationshipLevelValues) {
        _relationshipLevelDefinition.setRelationshipLevelValues(relationshipLevelValues);
    }

    /**
    * Returns the hierarchy level definition sid of this relationship level definition.
    *
    * @return the hierarchy level definition sid of this relationship level definition
    */
    @Override
    public int getHierarchyLevelDefinitionSid() {
        return _relationshipLevelDefinition.getHierarchyLevelDefinitionSid();
    }

    /**
    * Sets the hierarchy level definition sid of this relationship level definition.
    *
    * @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this relationship level definition
    */
    @Override
    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        _relationshipLevelDefinition.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
    }

    /**
    * Returns the parent node of this relationship level definition.
    *
    * @return the parent node of this relationship level definition
    */
    @Override
    public java.lang.String getParentNode() {
        return _relationshipLevelDefinition.getParentNode();
    }

    /**
    * Sets the parent node of this relationship level definition.
    *
    * @param parentNode the parent node of this relationship level definition
    */
    @Override
    public void setParentNode(java.lang.String parentNode) {
        _relationshipLevelDefinition.setParentNode(parentNode);
    }

    /**
    * Returns the version no of this relationship level definition.
    *
    * @return the version no of this relationship level definition
    */
    @Override
    public int getVersionNo() {
        return _relationshipLevelDefinition.getVersionNo();
    }

    /**
    * Sets the version no of this relationship level definition.
    *
    * @param versionNo the version no of this relationship level definition
    */
    @Override
    public void setVersionNo(int versionNo) {
        _relationshipLevelDefinition.setVersionNo(versionNo);
    }

    /**
    * Returns the relationship builder sid of this relationship level definition.
    *
    * @return the relationship builder sid of this relationship level definition
    */
    @Override
    public int getRelationshipBuilderSid() {
        return _relationshipLevelDefinition.getRelationshipBuilderSid();
    }

    /**
    * Sets the relationship builder sid of this relationship level definition.
    *
    * @param relationshipBuilderSid the relationship builder sid of this relationship level definition
    */
    @Override
    public void setRelationshipBuilderSid(int relationshipBuilderSid) {
        _relationshipLevelDefinition.setRelationshipBuilderSid(relationshipBuilderSid);
    }

    /**
    * Returns the modified date of this relationship level definition.
    *
    * @return the modified date of this relationship level definition
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _relationshipLevelDefinition.getModifiedDate();
    }

    /**
    * Sets the modified date of this relationship level definition.
    *
    * @param modifiedDate the modified date of this relationship level definition
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _relationshipLevelDefinition.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the created by of this relationship level definition.
    *
    * @return the created by of this relationship level definition
    */
    @Override
    public int getCreatedBy() {
        return _relationshipLevelDefinition.getCreatedBy();
    }

    /**
    * Sets the created by of this relationship level definition.
    *
    * @param createdBy the created by of this relationship level definition
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _relationshipLevelDefinition.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this relationship level definition.
    *
    * @return the created date of this relationship level definition
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _relationshipLevelDefinition.getCreatedDate();
    }

    /**
    * Sets the created date of this relationship level definition.
    *
    * @param createdDate the created date of this relationship level definition
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _relationshipLevelDefinition.setCreatedDate(createdDate);
    }

    /**
    * Returns the level no of this relationship level definition.
    *
    * @return the level no of this relationship level definition
    */
    @Override
    public java.lang.String getLevelNo() {
        return _relationshipLevelDefinition.getLevelNo();
    }

    /**
    * Sets the level no of this relationship level definition.
    *
    * @param levelNo the level no of this relationship level definition
    */
    @Override
    public void setLevelNo(java.lang.String levelNo) {
        _relationshipLevelDefinition.setLevelNo(levelNo);
    }

    /**
    * Returns the modified by of this relationship level definition.
    *
    * @return the modified by of this relationship level definition
    */
    @Override
    public int getModifiedBy() {
        return _relationshipLevelDefinition.getModifiedBy();
    }

    /**
    * Sets the modified by of this relationship level definition.
    *
    * @param modifiedBy the modified by of this relationship level definition
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _relationshipLevelDefinition.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the hierarchy no of this relationship level definition.
    *
    * @return the hierarchy no of this relationship level definition
    */
    @Override
    public java.lang.String getHierarchyNo() {
        return _relationshipLevelDefinition.getHierarchyNo();
    }

    /**
    * Sets the hierarchy no of this relationship level definition.
    *
    * @param hierarchyNo the hierarchy no of this relationship level definition
    */
    @Override
    public void setHierarchyNo(java.lang.String hierarchyNo) {
        _relationshipLevelDefinition.setHierarchyNo(hierarchyNo);
    }

    /**
    * Returns the relationship level sid of this relationship level definition.
    *
    * @return the relationship level sid of this relationship level definition
    */
    @Override
    public int getRelationshipLevelSid() {
        return _relationshipLevelDefinition.getRelationshipLevelSid();
    }

    /**
    * Sets the relationship level sid of this relationship level definition.
    *
    * @param relationshipLevelSid the relationship level sid of this relationship level definition
    */
    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _relationshipLevelDefinition.setRelationshipLevelSid(relationshipLevelSid);
    }

    /**
    * Returns the flag of this relationship level definition.
    *
    * @return the flag of this relationship level definition
    */
    @Override
    public java.lang.String getFlag() {
        return _relationshipLevelDefinition.getFlag();
    }

    /**
    * Sets the flag of this relationship level definition.
    *
    * @param flag the flag of this relationship level definition
    */
    @Override
    public void setFlag(java.lang.String flag) {
        _relationshipLevelDefinition.setFlag(flag);
    }

    /**
    * Returns the level name of this relationship level definition.
    *
    * @return the level name of this relationship level definition
    */
    @Override
    public java.lang.String getLevelName() {
        return _relationshipLevelDefinition.getLevelName();
    }

    /**
    * Sets the level name of this relationship level definition.
    *
    * @param levelName the level name of this relationship level definition
    */
    @Override
    public void setLevelName(java.lang.String levelName) {
        _relationshipLevelDefinition.setLevelName(levelName);
    }

    /**
    * Returns the parent hierarchy no of this relationship level definition.
    *
    * @return the parent hierarchy no of this relationship level definition
    */
    @Override
    public java.lang.String getParentHierarchyNo() {
        return _relationshipLevelDefinition.getParentHierarchyNo();
    }

    /**
    * Sets the parent hierarchy no of this relationship level definition.
    *
    * @param parentHierarchyNo the parent hierarchy no of this relationship level definition
    */
    @Override
    public void setParentHierarchyNo(java.lang.String parentHierarchyNo) {
        _relationshipLevelDefinition.setParentHierarchyNo(parentHierarchyNo);
    }

    @Override
    public boolean isNew() {
        return _relationshipLevelDefinition.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _relationshipLevelDefinition.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _relationshipLevelDefinition.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _relationshipLevelDefinition.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _relationshipLevelDefinition.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _relationshipLevelDefinition.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _relationshipLevelDefinition.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _relationshipLevelDefinition.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _relationshipLevelDefinition.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _relationshipLevelDefinition.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _relationshipLevelDefinition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RelationshipLevelDefinitionWrapper((RelationshipLevelDefinition) _relationshipLevelDefinition.clone());
    }

    @Override
    public int compareTo(
        RelationshipLevelDefinition relationshipLevelDefinition) {
        return _relationshipLevelDefinition.compareTo(relationshipLevelDefinition);
    }

    @Override
    public int hashCode() {
        return _relationshipLevelDefinition.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RelationshipLevelDefinition> toCacheModel() {
        return _relationshipLevelDefinition.toCacheModel();
    }

    @Override
    public RelationshipLevelDefinition toEscapedModel() {
        return new RelationshipLevelDefinitionWrapper(_relationshipLevelDefinition.toEscapedModel());
    }

    @Override
    public RelationshipLevelDefinition toUnescapedModel() {
        return new RelationshipLevelDefinitionWrapper(_relationshipLevelDefinition.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _relationshipLevelDefinition.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _relationshipLevelDefinition.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _relationshipLevelDefinition.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RelationshipLevelDefinitionWrapper)) {
            return false;
        }

        RelationshipLevelDefinitionWrapper relationshipLevelDefinitionWrapper = (RelationshipLevelDefinitionWrapper) obj;

        if (Validator.equals(_relationshipLevelDefinition,
                    relationshipLevelDefinitionWrapper._relationshipLevelDefinition)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RelationshipLevelDefinition getWrappedRelationshipLevelDefinition() {
        return _relationshipLevelDefinition;
    }

    @Override
    public RelationshipLevelDefinition getWrappedModel() {
        return _relationshipLevelDefinition;
    }

    @Override
    public void resetOriginalValues() {
        _relationshipLevelDefinition.resetOriginalValues();
    }
}
