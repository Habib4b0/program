package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HistRelationshipLevelDefn}.
 * </p>
 *
 * @author
 * @see HistRelationshipLevelDefn
 * @generated
 */
public class HistRelationshipLevelDefnWrapper
    implements HistRelationshipLevelDefn,
        ModelWrapper<HistRelationshipLevelDefn> {
    private HistRelationshipLevelDefn _histRelationshipLevelDefn;

    public HistRelationshipLevelDefnWrapper(
        HistRelationshipLevelDefn histRelationshipLevelDefn) {
        _histRelationshipLevelDefn = histRelationshipLevelDefn;
    }

    @Override
    public Class<?> getModelClass() {
        return HistRelationshipLevelDefn.class;
    }

    @Override
    public String getModelClassName() {
        return HistRelationshipLevelDefn.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("relationshipLevelValues", getRelationshipLevelValues());
        attributes.put("actionDate", getActionDate());
        attributes.put("hierarchyLevelDefinitionSid",
            getHierarchyLevelDefinitionSid());
        attributes.put("parentNode", getParentNode());
        attributes.put("versionNo", getVersionNo());
        attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("levelNo", getLevelNo());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("hierarchyNo", getHierarchyNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("flag", getFlag());
        attributes.put("levelName", getLevelName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String relationshipLevelValues = (String) attributes.get(
                "relationshipLevelValues");

        if (relationshipLevelValues != null) {
            setRelationshipLevelValues(relationshipLevelValues);
        }

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
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

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
        }

        String hierarchyNo = (String) attributes.get("hierarchyNo");

        if (hierarchyNo != null) {
            setHierarchyNo(hierarchyNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
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
    }

    /**
    * Returns the primary key of this hist relationship level defn.
    *
    * @return the primary key of this hist relationship level defn
    */
    @Override
    public com.stpl.app.service.persistence.HistRelationshipLevelDefnPK getPrimaryKey() {
        return _histRelationshipLevelDefn.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hist relationship level defn.
    *
    * @param primaryKey the primary key of this hist relationship level defn
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.HistRelationshipLevelDefnPK primaryKey) {
        _histRelationshipLevelDefn.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the relationship level values of this hist relationship level defn.
    *
    * @return the relationship level values of this hist relationship level defn
    */
    @Override
    public java.lang.String getRelationshipLevelValues() {
        return _histRelationshipLevelDefn.getRelationshipLevelValues();
    }

    /**
    * Sets the relationship level values of this hist relationship level defn.
    *
    * @param relationshipLevelValues the relationship level values of this hist relationship level defn
    */
    @Override
    public void setRelationshipLevelValues(
        java.lang.String relationshipLevelValues) {
        _histRelationshipLevelDefn.setRelationshipLevelValues(relationshipLevelValues);
    }

    /**
    * Returns the action date of this hist relationship level defn.
    *
    * @return the action date of this hist relationship level defn
    */
    @Override
    public java.util.Date getActionDate() {
        return _histRelationshipLevelDefn.getActionDate();
    }

    /**
    * Sets the action date of this hist relationship level defn.
    *
    * @param actionDate the action date of this hist relationship level defn
    */
    @Override
    public void setActionDate(java.util.Date actionDate) {
        _histRelationshipLevelDefn.setActionDate(actionDate);
    }

    /**
    * Returns the hierarchy level definition sid of this hist relationship level defn.
    *
    * @return the hierarchy level definition sid of this hist relationship level defn
    */
    @Override
    public int getHierarchyLevelDefinitionSid() {
        return _histRelationshipLevelDefn.getHierarchyLevelDefinitionSid();
    }

    /**
    * Sets the hierarchy level definition sid of this hist relationship level defn.
    *
    * @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hist relationship level defn
    */
    @Override
    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        _histRelationshipLevelDefn.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
    }

    /**
    * Returns the parent node of this hist relationship level defn.
    *
    * @return the parent node of this hist relationship level defn
    */
    @Override
    public java.lang.String getParentNode() {
        return _histRelationshipLevelDefn.getParentNode();
    }

    /**
    * Sets the parent node of this hist relationship level defn.
    *
    * @param parentNode the parent node of this hist relationship level defn
    */
    @Override
    public void setParentNode(java.lang.String parentNode) {
        _histRelationshipLevelDefn.setParentNode(parentNode);
    }

    /**
    * Returns the version no of this hist relationship level defn.
    *
    * @return the version no of this hist relationship level defn
    */
    @Override
    public int getVersionNo() {
        return _histRelationshipLevelDefn.getVersionNo();
    }

    /**
    * Sets the version no of this hist relationship level defn.
    *
    * @param versionNo the version no of this hist relationship level defn
    */
    @Override
    public void setVersionNo(int versionNo) {
        _histRelationshipLevelDefn.setVersionNo(versionNo);
    }

    /**
    * Returns the relationship builder sid of this hist relationship level defn.
    *
    * @return the relationship builder sid of this hist relationship level defn
    */
    @Override
    public int getRelationshipBuilderSid() {
        return _histRelationshipLevelDefn.getRelationshipBuilderSid();
    }

    /**
    * Sets the relationship builder sid of this hist relationship level defn.
    *
    * @param relationshipBuilderSid the relationship builder sid of this hist relationship level defn
    */
    @Override
    public void setRelationshipBuilderSid(int relationshipBuilderSid) {
        _histRelationshipLevelDefn.setRelationshipBuilderSid(relationshipBuilderSid);
    }

    /**
    * Returns the modified date of this hist relationship level defn.
    *
    * @return the modified date of this hist relationship level defn
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _histRelationshipLevelDefn.getModifiedDate();
    }

    /**
    * Sets the modified date of this hist relationship level defn.
    *
    * @param modifiedDate the modified date of this hist relationship level defn
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _histRelationshipLevelDefn.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the created by of this hist relationship level defn.
    *
    * @return the created by of this hist relationship level defn
    */
    @Override
    public int getCreatedBy() {
        return _histRelationshipLevelDefn.getCreatedBy();
    }

    /**
    * Sets the created by of this hist relationship level defn.
    *
    * @param createdBy the created by of this hist relationship level defn
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _histRelationshipLevelDefn.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this hist relationship level defn.
    *
    * @return the created date of this hist relationship level defn
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _histRelationshipLevelDefn.getCreatedDate();
    }

    /**
    * Sets the created date of this hist relationship level defn.
    *
    * @param createdDate the created date of this hist relationship level defn
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _histRelationshipLevelDefn.setCreatedDate(createdDate);
    }

    /**
    * Returns the level no of this hist relationship level defn.
    *
    * @return the level no of this hist relationship level defn
    */
    @Override
    public java.lang.String getLevelNo() {
        return _histRelationshipLevelDefn.getLevelNo();
    }

    /**
    * Sets the level no of this hist relationship level defn.
    *
    * @param levelNo the level no of this hist relationship level defn
    */
    @Override
    public void setLevelNo(java.lang.String levelNo) {
        _histRelationshipLevelDefn.setLevelNo(levelNo);
    }

    /**
    * Returns the action flag of this hist relationship level defn.
    *
    * @return the action flag of this hist relationship level defn
    */
    @Override
    public java.lang.String getActionFlag() {
        return _histRelationshipLevelDefn.getActionFlag();
    }

    /**
    * Sets the action flag of this hist relationship level defn.
    *
    * @param actionFlag the action flag of this hist relationship level defn
    */
    @Override
    public void setActionFlag(java.lang.String actionFlag) {
        _histRelationshipLevelDefn.setActionFlag(actionFlag);
    }

    /**
    * Returns the hierarchy no of this hist relationship level defn.
    *
    * @return the hierarchy no of this hist relationship level defn
    */
    @Override
    public java.lang.String getHierarchyNo() {
        return _histRelationshipLevelDefn.getHierarchyNo();
    }

    /**
    * Sets the hierarchy no of this hist relationship level defn.
    *
    * @param hierarchyNo the hierarchy no of this hist relationship level defn
    */
    @Override
    public void setHierarchyNo(java.lang.String hierarchyNo) {
        _histRelationshipLevelDefn.setHierarchyNo(hierarchyNo);
    }

    /**
    * Returns the modified by of this hist relationship level defn.
    *
    * @return the modified by of this hist relationship level defn
    */
    @Override
    public int getModifiedBy() {
        return _histRelationshipLevelDefn.getModifiedBy();
    }

    /**
    * Sets the modified by of this hist relationship level defn.
    *
    * @param modifiedBy the modified by of this hist relationship level defn
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _histRelationshipLevelDefn.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the relationship level sid of this hist relationship level defn.
    *
    * @return the relationship level sid of this hist relationship level defn
    */
    @Override
    public int getRelationshipLevelSid() {
        return _histRelationshipLevelDefn.getRelationshipLevelSid();
    }

    /**
    * Sets the relationship level sid of this hist relationship level defn.
    *
    * @param relationshipLevelSid the relationship level sid of this hist relationship level defn
    */
    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _histRelationshipLevelDefn.setRelationshipLevelSid(relationshipLevelSid);
    }

    /**
    * Returns the flag of this hist relationship level defn.
    *
    * @return the flag of this hist relationship level defn
    */
    @Override
    public java.lang.String getFlag() {
        return _histRelationshipLevelDefn.getFlag();
    }

    /**
    * Sets the flag of this hist relationship level defn.
    *
    * @param flag the flag of this hist relationship level defn
    */
    @Override
    public void setFlag(java.lang.String flag) {
        _histRelationshipLevelDefn.setFlag(flag);
    }

    /**
    * Returns the level name of this hist relationship level defn.
    *
    * @return the level name of this hist relationship level defn
    */
    @Override
    public java.lang.String getLevelName() {
        return _histRelationshipLevelDefn.getLevelName();
    }

    /**
    * Sets the level name of this hist relationship level defn.
    *
    * @param levelName the level name of this hist relationship level defn
    */
    @Override
    public void setLevelName(java.lang.String levelName) {
        _histRelationshipLevelDefn.setLevelName(levelName);
    }

    @Override
    public boolean isNew() {
        return _histRelationshipLevelDefn.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _histRelationshipLevelDefn.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _histRelationshipLevelDefn.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _histRelationshipLevelDefn.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _histRelationshipLevelDefn.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _histRelationshipLevelDefn.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _histRelationshipLevelDefn.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _histRelationshipLevelDefn.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _histRelationshipLevelDefn.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _histRelationshipLevelDefn.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _histRelationshipLevelDefn.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HistRelationshipLevelDefnWrapper((HistRelationshipLevelDefn) _histRelationshipLevelDefn.clone());
    }

    @Override
    public int compareTo(HistRelationshipLevelDefn histRelationshipLevelDefn) {
        return _histRelationshipLevelDefn.compareTo(histRelationshipLevelDefn);
    }

    @Override
    public int hashCode() {
        return _histRelationshipLevelDefn.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HistRelationshipLevelDefn> toCacheModel() {
        return _histRelationshipLevelDefn.toCacheModel();
    }

    @Override
    public HistRelationshipLevelDefn toEscapedModel() {
        return new HistRelationshipLevelDefnWrapper(_histRelationshipLevelDefn.toEscapedModel());
    }

    @Override
    public HistRelationshipLevelDefn toUnescapedModel() {
        return new HistRelationshipLevelDefnWrapper(_histRelationshipLevelDefn.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _histRelationshipLevelDefn.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _histRelationshipLevelDefn.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _histRelationshipLevelDefn.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistRelationshipLevelDefnWrapper)) {
            return false;
        }

        HistRelationshipLevelDefnWrapper histRelationshipLevelDefnWrapper = (HistRelationshipLevelDefnWrapper) obj;

        if (Validator.equals(_histRelationshipLevelDefn,
                    histRelationshipLevelDefnWrapper._histRelationshipLevelDefn)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HistRelationshipLevelDefn getWrappedHistRelationshipLevelDefn() {
        return _histRelationshipLevelDefn;
    }

    @Override
    public HistRelationshipLevelDefn getWrappedModel() {
        return _histRelationshipLevelDefn;
    }

    @Override
    public void resetOriginalValues() {
        _histRelationshipLevelDefn.resetOriginalValues();
    }
}
