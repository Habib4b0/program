package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionCustHierarchy}.
 * </p>
 *
 * @author
 * @see ProjectionCustHierarchy
 * @generated
 */
public class ProjectionCustHierarchyWrapper implements ProjectionCustHierarchy,
    ModelWrapper<ProjectionCustHierarchy> {
    private ProjectionCustHierarchy _projectionCustHierarchy;

    public ProjectionCustHierarchyWrapper(
        ProjectionCustHierarchy projectionCustHierarchy) {
        _projectionCustHierarchy = projectionCustHierarchy;
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionCustHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionCustHierarchy.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("projectionCustHierarchySid",
            getProjectionCustHierarchySid());
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

        Integer projectionCustHierarchySid = (Integer) attributes.get(
                "projectionCustHierarchySid");

        if (projectionCustHierarchySid != null) {
            setProjectionCustHierarchySid(projectionCustHierarchySid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }
    }

    /**
    * Returns the primary key of this projection cust hierarchy.
    *
    * @return the primary key of this projection cust hierarchy
    */
    @Override
    public int getPrimaryKey() {
        return _projectionCustHierarchy.getPrimaryKey();
    }

    /**
    * Sets the primary key of this projection cust hierarchy.
    *
    * @param primaryKey the primary key of this projection cust hierarchy
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _projectionCustHierarchy.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the projection master sid of this projection cust hierarchy.
    *
    * @return the projection master sid of this projection cust hierarchy
    */
    @Override
    public int getProjectionMasterSid() {
        return _projectionCustHierarchy.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this projection cust hierarchy.
    *
    * @param projectionMasterSid the projection master sid of this projection cust hierarchy
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionCustHierarchy.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the projection cust hierarchy sid of this projection cust hierarchy.
    *
    * @return the projection cust hierarchy sid of this projection cust hierarchy
    */
    @Override
    public int getProjectionCustHierarchySid() {
        return _projectionCustHierarchy.getProjectionCustHierarchySid();
    }

    /**
    * Sets the projection cust hierarchy sid of this projection cust hierarchy.
    *
    * @param projectionCustHierarchySid the projection cust hierarchy sid of this projection cust hierarchy
    */
    @Override
    public void setProjectionCustHierarchySid(int projectionCustHierarchySid) {
        _projectionCustHierarchy.setProjectionCustHierarchySid(projectionCustHierarchySid);
    }

    /**
    * Returns the relationship level sid of this projection cust hierarchy.
    *
    * @return the relationship level sid of this projection cust hierarchy
    */
    @Override
    public int getRelationshipLevelSid() {
        return _projectionCustHierarchy.getRelationshipLevelSid();
    }

    /**
    * Sets the relationship level sid of this projection cust hierarchy.
    *
    * @param relationshipLevelSid the relationship level sid of this projection cust hierarchy
    */
    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _projectionCustHierarchy.setRelationshipLevelSid(relationshipLevelSid);
    }

    @Override
    public boolean isNew() {
        return _projectionCustHierarchy.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _projectionCustHierarchy.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _projectionCustHierarchy.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _projectionCustHierarchy.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _projectionCustHierarchy.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _projectionCustHierarchy.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _projectionCustHierarchy.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _projectionCustHierarchy.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _projectionCustHierarchy.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _projectionCustHierarchy.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _projectionCustHierarchy.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProjectionCustHierarchyWrapper((ProjectionCustHierarchy) _projectionCustHierarchy.clone());
    }

    @Override
    public int compareTo(ProjectionCustHierarchy projectionCustHierarchy) {
        return _projectionCustHierarchy.compareTo(projectionCustHierarchy);
    }

    @Override
    public int hashCode() {
        return _projectionCustHierarchy.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ProjectionCustHierarchy> toCacheModel() {
        return _projectionCustHierarchy.toCacheModel();
    }

    @Override
    public ProjectionCustHierarchy toEscapedModel() {
        return new ProjectionCustHierarchyWrapper(_projectionCustHierarchy.toEscapedModel());
    }

    @Override
    public ProjectionCustHierarchy toUnescapedModel() {
        return new ProjectionCustHierarchyWrapper(_projectionCustHierarchy.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _projectionCustHierarchy.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _projectionCustHierarchy.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _projectionCustHierarchy.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProjectionCustHierarchyWrapper)) {
            return false;
        }

        ProjectionCustHierarchyWrapper projectionCustHierarchyWrapper = (ProjectionCustHierarchyWrapper) obj;

        if (Validator.equals(_projectionCustHierarchy,
                    projectionCustHierarchyWrapper._projectionCustHierarchy)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProjectionCustHierarchy getWrappedProjectionCustHierarchy() {
        return _projectionCustHierarchy;
    }

    @Override
    public ProjectionCustHierarchy getWrappedModel() {
        return _projectionCustHierarchy;
    }

    @Override
    public void resetOriginalValues() {
        _projectionCustHierarchy.resetOriginalValues();
    }
}
