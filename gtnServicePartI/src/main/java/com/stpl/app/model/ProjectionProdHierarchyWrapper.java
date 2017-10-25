package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionProdHierarchy}.
 * </p>
 *
 * @author
 * @see ProjectionProdHierarchy
 * @generated
 */
public class ProjectionProdHierarchyWrapper implements ProjectionProdHierarchy,
    ModelWrapper<ProjectionProdHierarchy> {
    private ProjectionProdHierarchy _projectionProdHierarchy;

    public ProjectionProdHierarchyWrapper(
        ProjectionProdHierarchy projectionProdHierarchy) {
        _projectionProdHierarchy = projectionProdHierarchy;
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionProdHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionProdHierarchy.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("projectionProdHierarchySid",
            getProjectionProdHierarchySid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Integer projectionProdHierarchySid = (Integer) attributes.get(
                "projectionProdHierarchySid");

        if (projectionProdHierarchySid != null) {
            setProjectionProdHierarchySid(projectionProdHierarchySid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }
    }

    /**
    * Returns the primary key of this projection prod hierarchy.
    *
    * @return the primary key of this projection prod hierarchy
    */
    @Override
    public int getPrimaryKey() {
        return _projectionProdHierarchy.getPrimaryKey();
    }

    /**
    * Sets the primary key of this projection prod hierarchy.
    *
    * @param primaryKey the primary key of this projection prod hierarchy
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _projectionProdHierarchy.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the projection master sid of this projection prod hierarchy.
    *
    * @return the projection master sid of this projection prod hierarchy
    */
    @Override
    public int getProjectionMasterSid() {
        return _projectionProdHierarchy.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this projection prod hierarchy.
    *
    * @param projectionMasterSid the projection master sid of this projection prod hierarchy
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionProdHierarchy.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the projection prod hierarchy sid of this projection prod hierarchy.
    *
    * @return the projection prod hierarchy sid of this projection prod hierarchy
    */
    @Override
    public int getProjectionProdHierarchySid() {
        return _projectionProdHierarchy.getProjectionProdHierarchySid();
    }

    /**
    * Sets the projection prod hierarchy sid of this projection prod hierarchy.
    *
    * @param projectionProdHierarchySid the projection prod hierarchy sid of this projection prod hierarchy
    */
    @Override
    public void setProjectionProdHierarchySid(int projectionProdHierarchySid) {
        _projectionProdHierarchy.setProjectionProdHierarchySid(projectionProdHierarchySid);
    }

    /**
    * Returns the relationship level sid of this projection prod hierarchy.
    *
    * @return the relationship level sid of this projection prod hierarchy
    */
    @Override
    public int getRelationshipLevelSid() {
        return _projectionProdHierarchy.getRelationshipLevelSid();
    }

    /**
    * Sets the relationship level sid of this projection prod hierarchy.
    *
    * @param relationshipLevelSid the relationship level sid of this projection prod hierarchy
    */
    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _projectionProdHierarchy.setRelationshipLevelSid(relationshipLevelSid);
    }

    @Override
    public boolean isNew() {
        return _projectionProdHierarchy.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _projectionProdHierarchy.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _projectionProdHierarchy.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _projectionProdHierarchy.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _projectionProdHierarchy.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _projectionProdHierarchy.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _projectionProdHierarchy.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _projectionProdHierarchy.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _projectionProdHierarchy.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _projectionProdHierarchy.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _projectionProdHierarchy.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProjectionProdHierarchyWrapper((ProjectionProdHierarchy) _projectionProdHierarchy.clone());
    }

    @Override
    public int compareTo(ProjectionProdHierarchy projectionProdHierarchy) {
        return _projectionProdHierarchy.compareTo(projectionProdHierarchy);
    }

    @Override
    public int hashCode() {
        return _projectionProdHierarchy.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ProjectionProdHierarchy> toCacheModel() {
        return _projectionProdHierarchy.toCacheModel();
    }

    @Override
    public ProjectionProdHierarchy toEscapedModel() {
        return new ProjectionProdHierarchyWrapper(_projectionProdHierarchy.toEscapedModel());
    }

    @Override
    public ProjectionProdHierarchy toUnescapedModel() {
        return new ProjectionProdHierarchyWrapper(_projectionProdHierarchy.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _projectionProdHierarchy.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _projectionProdHierarchy.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _projectionProdHierarchy.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProjectionProdHierarchyWrapper)) {
            return false;
        }

        ProjectionProdHierarchyWrapper projectionProdHierarchyWrapper = (ProjectionProdHierarchyWrapper) obj;

        if (Validator.equals(_projectionProdHierarchy,
                    projectionProdHierarchyWrapper._projectionProdHierarchy)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProjectionProdHierarchy getWrappedProjectionProdHierarchy() {
        return _projectionProdHierarchy;
    }

    @Override
    public ProjectionProdHierarchy getWrappedModel() {
        return _projectionProdHierarchy;
    }

    @Override
    public void resetOriginalValues() {
        _projectionProdHierarchy.resetOriginalValues();
    }
}
