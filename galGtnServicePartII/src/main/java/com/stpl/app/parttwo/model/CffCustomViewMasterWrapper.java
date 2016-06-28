package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffCustomViewMaster}.
 * </p>
 *
 * @author
 * @see CffCustomViewMaster
 * @generated
 */
public class CffCustomViewMasterWrapper implements CffCustomViewMaster,
    ModelWrapper<CffCustomViewMaster> {
    private CffCustomViewMaster _cffCustomViewMaster;

    public CffCustomViewMasterWrapper(CffCustomViewMaster cffCustomViewMaster) {
        _cffCustomViewMaster = cffCustomViewMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return CffCustomViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CffCustomViewMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("cffCustomViewMasterSid", getCffCustomViewMasterSid());
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

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer cffCustomViewMasterSid = (Integer) attributes.get(
                "cffCustomViewMasterSid");

        if (cffCustomViewMasterSid != null) {
            setCffCustomViewMasterSid(cffCustomViewMasterSid);
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
    * Returns the primary key of this cff custom view master.
    *
    * @return the primary key of this cff custom view master
    */
    @Override
    public int getPrimaryKey() {
        return _cffCustomViewMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff custom view master.
    *
    * @param primaryKey the primary key of this cff custom view master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffCustomViewMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this cff custom view master.
    *
    * @return the created date of this cff custom view master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cffCustomViewMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this cff custom view master.
    *
    * @param createdDate the created date of this cff custom view master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cffCustomViewMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this cff custom view master.
    *
    * @return the created by of this cff custom view master
    */
    @Override
    public int getCreatedBy() {
        return _cffCustomViewMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this cff custom view master.
    *
    * @param createdBy the created by of this cff custom view master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cffCustomViewMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the cff master sid of this cff custom view master.
    *
    * @return the cff master sid of this cff custom view master
    */
    @Override
    public int getCffMasterSid() {
        return _cffCustomViewMaster.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff custom view master.
    *
    * @param cffMasterSid the cff master sid of this cff custom view master
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffCustomViewMaster.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the modified by of this cff custom view master.
    *
    * @return the modified by of this cff custom view master
    */
    @Override
    public int getModifiedBy() {
        return _cffCustomViewMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this cff custom view master.
    *
    * @param modifiedBy the modified by of this cff custom view master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cffCustomViewMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the cff custom view master sid of this cff custom view master.
    *
    * @return the cff custom view master sid of this cff custom view master
    */
    @Override
    public int getCffCustomViewMasterSid() {
        return _cffCustomViewMaster.getCffCustomViewMasterSid();
    }

    /**
    * Sets the cff custom view master sid of this cff custom view master.
    *
    * @param cffCustomViewMasterSid the cff custom view master sid of this cff custom view master
    */
    @Override
    public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
        _cffCustomViewMaster.setCffCustomViewMasterSid(cffCustomViewMasterSid);
    }

    /**
    * Returns the modified date of this cff custom view master.
    *
    * @return the modified date of this cff custom view master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cffCustomViewMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this cff custom view master.
    *
    * @param modifiedDate the modified date of this cff custom view master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cffCustomViewMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the view name of this cff custom view master.
    *
    * @return the view name of this cff custom view master
    */
    @Override
    public java.lang.String getViewName() {
        return _cffCustomViewMaster.getViewName();
    }

    /**
    * Sets the view name of this cff custom view master.
    *
    * @param viewName the view name of this cff custom view master
    */
    @Override
    public void setViewName(java.lang.String viewName) {
        _cffCustomViewMaster.setViewName(viewName);
    }

    @Override
    public boolean isNew() {
        return _cffCustomViewMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffCustomViewMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffCustomViewMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffCustomViewMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffCustomViewMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffCustomViewMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffCustomViewMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffCustomViewMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffCustomViewMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffCustomViewMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffCustomViewMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffCustomViewMasterWrapper((CffCustomViewMaster) _cffCustomViewMaster.clone());
    }

    @Override
    public int compareTo(CffCustomViewMaster cffCustomViewMaster) {
        return _cffCustomViewMaster.compareTo(cffCustomViewMaster);
    }

    @Override
    public int hashCode() {
        return _cffCustomViewMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffCustomViewMaster> toCacheModel() {
        return _cffCustomViewMaster.toCacheModel();
    }

    @Override
    public CffCustomViewMaster toEscapedModel() {
        return new CffCustomViewMasterWrapper(_cffCustomViewMaster.toEscapedModel());
    }

    @Override
    public CffCustomViewMaster toUnescapedModel() {
        return new CffCustomViewMasterWrapper(_cffCustomViewMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffCustomViewMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffCustomViewMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffCustomViewMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffCustomViewMasterWrapper)) {
            return false;
        }

        CffCustomViewMasterWrapper cffCustomViewMasterWrapper = (CffCustomViewMasterWrapper) obj;

        if (Validator.equals(_cffCustomViewMaster,
                    cffCustomViewMasterWrapper._cffCustomViewMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffCustomViewMaster getWrappedCffCustomViewMaster() {
        return _cffCustomViewMaster;
    }

    @Override
    public CffCustomViewMaster getWrappedModel() {
        return _cffCustomViewMaster;
    }

    @Override
    public void resetOriginalValues() {
        _cffCustomViewMaster.resetOriginalValues();
    }
}
