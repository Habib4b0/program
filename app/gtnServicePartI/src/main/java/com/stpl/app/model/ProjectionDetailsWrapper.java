package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionDetails}.
 * </p>
 *
 * @author
 * @see ProjectionDetails
 * @generated
 */
public class ProjectionDetailsWrapper implements ProjectionDetails,
    ModelWrapper<ProjectionDetails> {
    private ProjectionDetails _projectionDetails;

    public ProjectionDetailsWrapper(ProjectionDetails projectionDetails) {
        _projectionDetails = projectionDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("projectionMasterSid", getProjectionMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }
    }

    /**
    * Returns the primary key of this projection details.
    *
    * @return the primary key of this projection details
    */
    @Override
    public int getPrimaryKey() {
        return _projectionDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this projection details.
    *
    * @param primaryKey the primary key of this projection details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _projectionDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the projection details sid of this projection details.
    *
    * @return the projection details sid of this projection details
    */
    @Override
    public int getProjectionDetailsSid() {
        return _projectionDetails.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this projection details.
    *
    * @param projectionDetailsSid the projection details sid of this projection details
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetails.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the ccp details sid of this projection details.
    *
    * @return the ccp details sid of this projection details
    */
    @Override
    public int getCcpDetailsSid() {
        return _projectionDetails.getCcpDetailsSid();
    }

    /**
    * Sets the ccp details sid of this projection details.
    *
    * @param ccpDetailsSid the ccp details sid of this projection details
    */
    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _projectionDetails.setCcpDetailsSid(ccpDetailsSid);
    }

    /**
    * Returns the projection master sid of this projection details.
    *
    * @return the projection master sid of this projection details
    */
    @Override
    public int getProjectionMasterSid() {
        return _projectionDetails.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this projection details.
    *
    * @param projectionMasterSid the projection master sid of this projection details
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionDetails.setProjectionMasterSid(projectionMasterSid);
    }

    @Override
    public boolean isNew() {
        return _projectionDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _projectionDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _projectionDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _projectionDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _projectionDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _projectionDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _projectionDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _projectionDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _projectionDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _projectionDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _projectionDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProjectionDetailsWrapper((ProjectionDetails) _projectionDetails.clone());
    }

    @Override
    public int compareTo(ProjectionDetails projectionDetails) {
        return _projectionDetails.compareTo(projectionDetails);
    }

    @Override
    public int hashCode() {
        return _projectionDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ProjectionDetails> toCacheModel() {
        return _projectionDetails.toCacheModel();
    }

    @Override
    public ProjectionDetails toEscapedModel() {
        return new ProjectionDetailsWrapper(_projectionDetails.toEscapedModel());
    }

    @Override
    public ProjectionDetails toUnescapedModel() {
        return new ProjectionDetailsWrapper(_projectionDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _projectionDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _projectionDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _projectionDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProjectionDetailsWrapper)) {
            return false;
        }

        ProjectionDetailsWrapper projectionDetailsWrapper = (ProjectionDetailsWrapper) obj;

        if (Validator.equals(_projectionDetails,
                    projectionDetailsWrapper._projectionDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProjectionDetails getWrappedProjectionDetails() {
        return _projectionDetails;
    }

    @Override
    public ProjectionDetails getWrappedModel() {
        return _projectionDetails;
    }

    @Override
    public void resetOriginalValues() {
        _projectionDetails.resetOriginalValues();
    }
}
