package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ForecastingViewMaster}.
 * </p>
 *
 * @author
 * @see ForecastingViewMaster
 * @generated
 */
public class ForecastingViewMasterWrapper implements ForecastingViewMaster,
    ModelWrapper<ForecastingViewMaster> {
    private ForecastingViewMaster _forecastingViewMaster;

    public ForecastingViewMasterWrapper(
        ForecastingViewMaster forecastingViewMaster) {
        _forecastingViewMaster = forecastingViewMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ForecastingViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ForecastingViewMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("viewType", getViewType());
        attributes.put("viewId", getViewId());
        attributes.put("projectionId", getProjectionId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("viewName", getViewName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String viewType = (String) attributes.get("viewType");

        if (viewType != null) {
            setViewType(viewType);
        }

        Integer viewId = (Integer) attributes.get("viewId");

        if (viewId != null) {
            setViewId(viewId);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

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
    * Returns the primary key of this forecasting view master.
    *
    * @return the primary key of this forecasting view master
    */
    @Override
    public int getPrimaryKey() {
        return _forecastingViewMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this forecasting view master.
    *
    * @param primaryKey the primary key of this forecasting view master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _forecastingViewMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this forecasting view master.
    *
    * @return the created date of this forecasting view master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _forecastingViewMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this forecasting view master.
    *
    * @param createdDate the created date of this forecasting view master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _forecastingViewMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this forecasting view master.
    *
    * @return the created by of this forecasting view master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _forecastingViewMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this forecasting view master.
    *
    * @param createdBy the created by of this forecasting view master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _forecastingViewMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the view type of this forecasting view master.
    *
    * @return the view type of this forecasting view master
    */
    @Override
    public java.lang.String getViewType() {
        return _forecastingViewMaster.getViewType();
    }

    /**
    * Sets the view type of this forecasting view master.
    *
    * @param viewType the view type of this forecasting view master
    */
    @Override
    public void setViewType(java.lang.String viewType) {
        _forecastingViewMaster.setViewType(viewType);
    }

    /**
    * Returns the view ID of this forecasting view master.
    *
    * @return the view ID of this forecasting view master
    */
    @Override
    public int getViewId() {
        return _forecastingViewMaster.getViewId();
    }

    /**
    * Sets the view ID of this forecasting view master.
    *
    * @param viewId the view ID of this forecasting view master
    */
    @Override
    public void setViewId(int viewId) {
        _forecastingViewMaster.setViewId(viewId);
    }

    /**
    * Returns the projection ID of this forecasting view master.
    *
    * @return the projection ID of this forecasting view master
    */
    @Override
    public int getProjectionId() {
        return _forecastingViewMaster.getProjectionId();
    }

    /**
    * Sets the projection ID of this forecasting view master.
    *
    * @param projectionId the projection ID of this forecasting view master
    */
    @Override
    public void setProjectionId(int projectionId) {
        _forecastingViewMaster.setProjectionId(projectionId);
    }

    /**
    * Returns the modified by of this forecasting view master.
    *
    * @return the modified by of this forecasting view master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _forecastingViewMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this forecasting view master.
    *
    * @param modifiedBy the modified by of this forecasting view master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _forecastingViewMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this forecasting view master.
    *
    * @return the modified date of this forecasting view master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _forecastingViewMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this forecasting view master.
    *
    * @param modifiedDate the modified date of this forecasting view master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _forecastingViewMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the view name of this forecasting view master.
    *
    * @return the view name of this forecasting view master
    */
    @Override
    public java.lang.String getViewName() {
        return _forecastingViewMaster.getViewName();
    }

    /**
    * Sets the view name of this forecasting view master.
    *
    * @param viewName the view name of this forecasting view master
    */
    @Override
    public void setViewName(java.lang.String viewName) {
        _forecastingViewMaster.setViewName(viewName);
    }

    @Override
    public boolean isNew() {
        return _forecastingViewMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _forecastingViewMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _forecastingViewMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _forecastingViewMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _forecastingViewMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _forecastingViewMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _forecastingViewMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _forecastingViewMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _forecastingViewMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _forecastingViewMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _forecastingViewMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ForecastingViewMasterWrapper((ForecastingViewMaster) _forecastingViewMaster.clone());
    }

    @Override
    public int compareTo(ForecastingViewMaster forecastingViewMaster) {
        return _forecastingViewMaster.compareTo(forecastingViewMaster);
    }

    @Override
    public int hashCode() {
        return _forecastingViewMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ForecastingViewMaster> toCacheModel() {
        return _forecastingViewMaster.toCacheModel();
    }

    @Override
    public ForecastingViewMaster toEscapedModel() {
        return new ForecastingViewMasterWrapper(_forecastingViewMaster.toEscapedModel());
    }

    @Override
    public ForecastingViewMaster toUnescapedModel() {
        return new ForecastingViewMasterWrapper(_forecastingViewMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _forecastingViewMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _forecastingViewMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _forecastingViewMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ForecastingViewMasterWrapper)) {
            return false;
        }

        ForecastingViewMasterWrapper forecastingViewMasterWrapper = (ForecastingViewMasterWrapper) obj;

        if (Validator.equals(_forecastingViewMaster,
                    forecastingViewMasterWrapper._forecastingViewMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ForecastingViewMaster getWrappedForecastingViewMaster() {
        return _forecastingViewMaster;
    }

    @Override
    public ForecastingViewMaster getWrappedModel() {
        return _forecastingViewMaster;
    }

    @Override
    public void resetOriginalValues() {
        _forecastingViewMaster.resetOriginalValues();
    }
}
