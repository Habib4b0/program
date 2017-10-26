package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CustomViewMaster}.
 * </p>
 *
 * @author
 * @see CustomViewMaster
 * @generated
 */
public class CustomViewMasterWrapper implements CustomViewMaster,
    ModelWrapper<CustomViewMaster> {
    private CustomViewMaster _customViewMaster;

    public CustomViewMasterWrapper(CustomViewMaster customViewMaster) {
        _customViewMaster = customViewMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return CustomViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CustomViewMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("customViewMasterSid", getCustomViewMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("viewName", getViewName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer customViewMasterSid = (Integer) attributes.get(
                "customViewMasterSid");

        if (customViewMasterSid != null) {
            setCustomViewMasterSid(customViewMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String viewName = (String) attributes.get("viewName");

        if (viewName != null) {
            setViewName(viewName);
        }
    }

    /**
    * Returns the primary key of this custom view master.
    *
    * @return the primary key of this custom view master
    */
    @Override
    public int getPrimaryKey() {
        return _customViewMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this custom view master.
    *
    * @param primaryKey the primary key of this custom view master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _customViewMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the custom view master sid of this custom view master.
    *
    * @return the custom view master sid of this custom view master
    */
    @Override
    public int getCustomViewMasterSid() {
        return _customViewMaster.getCustomViewMasterSid();
    }

    /**
    * Sets the custom view master sid of this custom view master.
    *
    * @param customViewMasterSid the custom view master sid of this custom view master
    */
    @Override
    public void setCustomViewMasterSid(int customViewMasterSid) {
        _customViewMaster.setCustomViewMasterSid(customViewMasterSid);
    }

    /**
    * Returns the created date of this custom view master.
    *
    * @return the created date of this custom view master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _customViewMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this custom view master.
    *
    * @param createdDate the created date of this custom view master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _customViewMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this custom view master.
    *
    * @return the created by of this custom view master
    */
    @Override
    public int getCreatedBy() {
        return _customViewMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this custom view master.
    *
    * @param createdBy the created by of this custom view master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _customViewMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the projection master sid of this custom view master.
    *
    * @return the projection master sid of this custom view master
    */
    @Override
    public int getProjectionMasterSid() {
        return _customViewMaster.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this custom view master.
    *
    * @param projectionMasterSid the projection master sid of this custom view master
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _customViewMaster.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the modified by of this custom view master.
    *
    * @return the modified by of this custom view master
    */
    @Override
    public int getModifiedBy() {
        return _customViewMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this custom view master.
    *
    * @param modifiedBy the modified by of this custom view master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _customViewMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this custom view master.
    *
    * @return the modified date of this custom view master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _customViewMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this custom view master.
    *
    * @param modifiedDate the modified date of this custom view master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _customViewMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the view name of this custom view master.
    *
    * @return the view name of this custom view master
    */
    @Override
    public java.lang.String getViewName() {
        return _customViewMaster.getViewName();
    }

    /**
    * Sets the view name of this custom view master.
    *
    * @param viewName the view name of this custom view master
    */
    @Override
    public void setViewName(java.lang.String viewName) {
        _customViewMaster.setViewName(viewName);
    }

    @Override
    public boolean isNew() {
        return _customViewMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _customViewMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _customViewMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _customViewMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _customViewMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _customViewMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _customViewMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _customViewMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _customViewMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _customViewMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _customViewMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CustomViewMasterWrapper((CustomViewMaster) _customViewMaster.clone());
    }

    @Override
    public int compareTo(CustomViewMaster customViewMaster) {
        return _customViewMaster.compareTo(customViewMaster);
    }

    @Override
    public int hashCode() {
        return _customViewMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CustomViewMaster> toCacheModel() {
        return _customViewMaster.toCacheModel();
    }

    @Override
    public CustomViewMaster toEscapedModel() {
        return new CustomViewMasterWrapper(_customViewMaster.toEscapedModel());
    }

    @Override
    public CustomViewMaster toUnescapedModel() {
        return new CustomViewMasterWrapper(_customViewMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _customViewMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _customViewMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _customViewMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CustomViewMasterWrapper)) {
            return false;
        }

        CustomViewMasterWrapper customViewMasterWrapper = (CustomViewMasterWrapper) obj;

        if (Validator.equals(_customViewMaster,
                    customViewMasterWrapper._customViewMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CustomViewMaster getWrappedCustomViewMaster() {
        return _customViewMaster;
    }

    @Override
    public CustomViewMaster getWrappedModel() {
        return _customViewMaster;
    }

    @Override
    public void resetOriginalValues() {
        _customViewMaster.resetOriginalValues();
    }
}
