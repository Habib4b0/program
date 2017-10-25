package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffViewMaster}.
 * </p>
 *
 * @author
 * @see CffViewMaster
 * @generated
 */
public class CffViewMasterWrapper implements CffViewMaster,
    ModelWrapper<CffViewMaster> {
    private CffViewMaster _cffViewMaster;

    public CffViewMasterWrapper(CffViewMaster cffViewMaster) {
        _cffViewMaster = cffViewMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return CffViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CffViewMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("viewType", getViewType());
        attributes.put("cffViewMasterSid", getCffViewMasterSid());
        attributes.put("cffMasterSid", getCffMasterSid());
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

        Integer cffViewMasterSid = (Integer) attributes.get("cffViewMasterSid");

        if (cffViewMasterSid != null) {
            setCffViewMasterSid(cffViewMasterSid);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
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
    * Returns the primary key of this cff view master.
    *
    * @return the primary key of this cff view master
    */
    @Override
    public int getPrimaryKey() {
        return _cffViewMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff view master.
    *
    * @param primaryKey the primary key of this cff view master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffViewMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this cff view master.
    *
    * @return the created date of this cff view master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cffViewMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this cff view master.
    *
    * @param createdDate the created date of this cff view master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cffViewMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this cff view master.
    *
    * @return the created by of this cff view master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _cffViewMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this cff view master.
    *
    * @param createdBy the created by of this cff view master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _cffViewMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the view type of this cff view master.
    *
    * @return the view type of this cff view master
    */
    @Override
    public java.lang.String getViewType() {
        return _cffViewMaster.getViewType();
    }

    /**
    * Sets the view type of this cff view master.
    *
    * @param viewType the view type of this cff view master
    */
    @Override
    public void setViewType(java.lang.String viewType) {
        _cffViewMaster.setViewType(viewType);
    }

    /**
    * Returns the cff view master sid of this cff view master.
    *
    * @return the cff view master sid of this cff view master
    */
    @Override
    public int getCffViewMasterSid() {
        return _cffViewMaster.getCffViewMasterSid();
    }

    /**
    * Sets the cff view master sid of this cff view master.
    *
    * @param cffViewMasterSid the cff view master sid of this cff view master
    */
    @Override
    public void setCffViewMasterSid(int cffViewMasterSid) {
        _cffViewMaster.setCffViewMasterSid(cffViewMasterSid);
    }

    /**
    * Returns the cff master sid of this cff view master.
    *
    * @return the cff master sid of this cff view master
    */
    @Override
    public int getCffMasterSid() {
        return _cffViewMaster.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff view master.
    *
    * @param cffMasterSid the cff master sid of this cff view master
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffViewMaster.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the modified by of this cff view master.
    *
    * @return the modified by of this cff view master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _cffViewMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this cff view master.
    *
    * @param modifiedBy the modified by of this cff view master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _cffViewMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this cff view master.
    *
    * @return the modified date of this cff view master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cffViewMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this cff view master.
    *
    * @param modifiedDate the modified date of this cff view master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cffViewMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the view name of this cff view master.
    *
    * @return the view name of this cff view master
    */
    @Override
    public java.lang.String getViewName() {
        return _cffViewMaster.getViewName();
    }

    /**
    * Sets the view name of this cff view master.
    *
    * @param viewName the view name of this cff view master
    */
    @Override
    public void setViewName(java.lang.String viewName) {
        _cffViewMaster.setViewName(viewName);
    }

    @Override
    public boolean isNew() {
        return _cffViewMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffViewMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffViewMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffViewMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffViewMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffViewMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffViewMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffViewMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffViewMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffViewMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffViewMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffViewMasterWrapper((CffViewMaster) _cffViewMaster.clone());
    }

    @Override
    public int compareTo(CffViewMaster cffViewMaster) {
        return _cffViewMaster.compareTo(cffViewMaster);
    }

    @Override
    public int hashCode() {
        return _cffViewMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffViewMaster> toCacheModel() {
        return _cffViewMaster.toCacheModel();
    }

    @Override
    public CffViewMaster toEscapedModel() {
        return new CffViewMasterWrapper(_cffViewMaster.toEscapedModel());
    }

    @Override
    public CffViewMaster toUnescapedModel() {
        return new CffViewMasterWrapper(_cffViewMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffViewMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffViewMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffViewMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffViewMasterWrapper)) {
            return false;
        }

        CffViewMasterWrapper cffViewMasterWrapper = (CffViewMasterWrapper) obj;

        if (Validator.equals(_cffViewMaster, cffViewMasterWrapper._cffViewMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffViewMaster getWrappedCffViewMaster() {
        return _cffViewMaster;
    }

    @Override
    public CffViewMaster getWrappedModel() {
        return _cffViewMaster;
    }

    @Override
    public void resetOriginalValues() {
        _cffViewMaster.resetOriginalValues();
    }
}
