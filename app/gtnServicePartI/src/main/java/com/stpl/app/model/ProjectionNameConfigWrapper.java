package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionNameConfig}.
 * </p>
 *
 * @author
 * @see ProjectionNameConfig
 * @generated
 */
public class ProjectionNameConfigWrapper implements ProjectionNameConfig,
    ModelWrapper<ProjectionNameConfig> {
    private ProjectionNameConfig _projectionNameConfig;

    public ProjectionNameConfigWrapper(
        ProjectionNameConfig projectionNameConfig) {
        _projectionNameConfig = projectionNameConfig;
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionNameConfig.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionNameConfig.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("businessProcessType", getBusinessProcessType());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("projectionNameConfigSid", getProjectionNameConfigSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("selectedAttributes", getSelectedAttributes());

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

        String businessProcessType = (String) attributes.get(
                "businessProcessType");

        if (businessProcessType != null) {
            setBusinessProcessType(businessProcessType);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer projectionNameConfigSid = (Integer) attributes.get(
                "projectionNameConfigSid");

        if (projectionNameConfigSid != null) {
            setProjectionNameConfigSid(projectionNameConfigSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String selectedAttributes = (String) attributes.get(
                "selectedAttributes");

        if (selectedAttributes != null) {
            setSelectedAttributes(selectedAttributes);
        }
    }

    /**
    * Returns the primary key of this projection name config.
    *
    * @return the primary key of this projection name config
    */
    @Override
    public int getPrimaryKey() {
        return _projectionNameConfig.getPrimaryKey();
    }

    /**
    * Sets the primary key of this projection name config.
    *
    * @param primaryKey the primary key of this projection name config
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _projectionNameConfig.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this projection name config.
    *
    * @return the created date of this projection name config
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _projectionNameConfig.getCreatedDate();
    }

    /**
    * Sets the created date of this projection name config.
    *
    * @param createdDate the created date of this projection name config
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _projectionNameConfig.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this projection name config.
    *
    * @return the created by of this projection name config
    */
    @Override
    public int getCreatedBy() {
        return _projectionNameConfig.getCreatedBy();
    }

    /**
    * Sets the created by of this projection name config.
    *
    * @param createdBy the created by of this projection name config
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _projectionNameConfig.setCreatedBy(createdBy);
    }

    /**
    * Returns the business process type of this projection name config.
    *
    * @return the business process type of this projection name config
    */
    @Override
    public java.lang.String getBusinessProcessType() {
        return _projectionNameConfig.getBusinessProcessType();
    }

    /**
    * Sets the business process type of this projection name config.
    *
    * @param businessProcessType the business process type of this projection name config
    */
    @Override
    public void setBusinessProcessType(java.lang.String businessProcessType) {
        _projectionNameConfig.setBusinessProcessType(businessProcessType);
    }

    /**
    * Returns the version no of this projection name config.
    *
    * @return the version no of this projection name config
    */
    @Override
    public int getVersionNo() {
        return _projectionNameConfig.getVersionNo();
    }

    /**
    * Sets the version no of this projection name config.
    *
    * @param versionNo the version no of this projection name config
    */
    @Override
    public void setVersionNo(int versionNo) {
        _projectionNameConfig.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this projection name config.
    *
    * @return the modified by of this projection name config
    */
    @Override
    public int getModifiedBy() {
        return _projectionNameConfig.getModifiedBy();
    }

    /**
    * Sets the modified by of this projection name config.
    *
    * @param modifiedBy the modified by of this projection name config
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _projectionNameConfig.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the projection name config sid of this projection name config.
    *
    * @return the projection name config sid of this projection name config
    */
    @Override
    public int getProjectionNameConfigSid() {
        return _projectionNameConfig.getProjectionNameConfigSid();
    }

    /**
    * Sets the projection name config sid of this projection name config.
    *
    * @param projectionNameConfigSid the projection name config sid of this projection name config
    */
    @Override
    public void setProjectionNameConfigSid(int projectionNameConfigSid) {
        _projectionNameConfig.setProjectionNameConfigSid(projectionNameConfigSid);
    }

    /**
    * Returns the modified date of this projection name config.
    *
    * @return the modified date of this projection name config
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _projectionNameConfig.getModifiedDate();
    }

    /**
    * Sets the modified date of this projection name config.
    *
    * @param modifiedDate the modified date of this projection name config
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _projectionNameConfig.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the selected attributes of this projection name config.
    *
    * @return the selected attributes of this projection name config
    */
    @Override
    public java.lang.String getSelectedAttributes() {
        return _projectionNameConfig.getSelectedAttributes();
    }

    /**
    * Sets the selected attributes of this projection name config.
    *
    * @param selectedAttributes the selected attributes of this projection name config
    */
    @Override
    public void setSelectedAttributes(java.lang.String selectedAttributes) {
        _projectionNameConfig.setSelectedAttributes(selectedAttributes);
    }

    @Override
    public boolean isNew() {
        return _projectionNameConfig.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _projectionNameConfig.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _projectionNameConfig.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _projectionNameConfig.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _projectionNameConfig.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _projectionNameConfig.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _projectionNameConfig.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _projectionNameConfig.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _projectionNameConfig.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _projectionNameConfig.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _projectionNameConfig.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProjectionNameConfigWrapper((ProjectionNameConfig) _projectionNameConfig.clone());
    }

    @Override
    public int compareTo(ProjectionNameConfig projectionNameConfig) {
        return _projectionNameConfig.compareTo(projectionNameConfig);
    }

    @Override
    public int hashCode() {
        return _projectionNameConfig.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ProjectionNameConfig> toCacheModel() {
        return _projectionNameConfig.toCacheModel();
    }

    @Override
    public ProjectionNameConfig toEscapedModel() {
        return new ProjectionNameConfigWrapper(_projectionNameConfig.toEscapedModel());
    }

    @Override
    public ProjectionNameConfig toUnescapedModel() {
        return new ProjectionNameConfigWrapper(_projectionNameConfig.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _projectionNameConfig.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _projectionNameConfig.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _projectionNameConfig.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProjectionNameConfigWrapper)) {
            return false;
        }

        ProjectionNameConfigWrapper projectionNameConfigWrapper = (ProjectionNameConfigWrapper) obj;

        if (Validator.equals(_projectionNameConfig,
                    projectionNameConfigWrapper._projectionNameConfig)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProjectionNameConfig getWrappedProjectionNameConfig() {
        return _projectionNameConfig;
    }

    @Override
    public ProjectionNameConfig getWrappedModel() {
        return _projectionNameConfig;
    }

    @Override
    public void resetOriginalValues() {
        _projectionNameConfig.resetOriginalValues();
    }
}
