package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccClosureViewMaster}.
 * </p>
 *
 * @author
 * @see AccClosureViewMaster
 * @generated
 */
public class AccClosureViewMasterWrapper implements AccClosureViewMaster,
    ModelWrapper<AccClosureViewMaster> {
    private AccClosureViewMaster _accClosureViewMaster;

    public AccClosureViewMasterWrapper(
        AccClosureViewMaster accClosureViewMaster) {
        _accClosureViewMaster = accClosureViewMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return AccClosureViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return AccClosureViewMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("viewType", getViewType());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("accClosureViewMasterSid", getAccClosureViewMasterSid());
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

        String viewType = (String) attributes.get("viewType");

        if (viewType != null) {
            setViewType(viewType);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer accClosureViewMasterSid = (Integer) attributes.get(
                "accClosureViewMasterSid");

        if (accClosureViewMasterSid != null) {
            setAccClosureViewMasterSid(accClosureViewMasterSid);
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
    * Returns the primary key of this acc closure view master.
    *
    * @return the primary key of this acc closure view master
    */
    @Override
    public int getPrimaryKey() {
        return _accClosureViewMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this acc closure view master.
    *
    * @param primaryKey the primary key of this acc closure view master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _accClosureViewMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this acc closure view master.
    *
    * @return the created date of this acc closure view master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _accClosureViewMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this acc closure view master.
    *
    * @param createdDate the created date of this acc closure view master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _accClosureViewMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this acc closure view master.
    *
    * @return the created by of this acc closure view master
    */
    @Override
    public int getCreatedBy() {
        return _accClosureViewMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this acc closure view master.
    *
    * @param createdBy the created by of this acc closure view master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _accClosureViewMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the view type of this acc closure view master.
    *
    * @return the view type of this acc closure view master
    */
    @Override
    public java.lang.String getViewType() {
        return _accClosureViewMaster.getViewType();
    }

    /**
    * Sets the view type of this acc closure view master.
    *
    * @param viewType the view type of this acc closure view master
    */
    @Override
    public void setViewType(java.lang.String viewType) {
        _accClosureViewMaster.setViewType(viewType);
    }

    /**
    * Returns the acc closure master sid of this acc closure view master.
    *
    * @return the acc closure master sid of this acc closure view master
    */
    @Override
    public int getAccClosureMasterSid() {
        return _accClosureViewMaster.getAccClosureMasterSid();
    }

    /**
    * Sets the acc closure master sid of this acc closure view master.
    *
    * @param accClosureMasterSid the acc closure master sid of this acc closure view master
    */
    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureViewMaster.setAccClosureMasterSid(accClosureMasterSid);
    }

    /**
    * Returns the modified by of this acc closure view master.
    *
    * @return the modified by of this acc closure view master
    */
    @Override
    public int getModifiedBy() {
        return _accClosureViewMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this acc closure view master.
    *
    * @param modifiedBy the modified by of this acc closure view master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _accClosureViewMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the acc closure view master sid of this acc closure view master.
    *
    * @return the acc closure view master sid of this acc closure view master
    */
    @Override
    public int getAccClosureViewMasterSid() {
        return _accClosureViewMaster.getAccClosureViewMasterSid();
    }

    /**
    * Sets the acc closure view master sid of this acc closure view master.
    *
    * @param accClosureViewMasterSid the acc closure view master sid of this acc closure view master
    */
    @Override
    public void setAccClosureViewMasterSid(int accClosureViewMasterSid) {
        _accClosureViewMaster.setAccClosureViewMasterSid(accClosureViewMasterSid);
    }

    /**
    * Returns the modified date of this acc closure view master.
    *
    * @return the modified date of this acc closure view master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _accClosureViewMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this acc closure view master.
    *
    * @param modifiedDate the modified date of this acc closure view master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _accClosureViewMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the view name of this acc closure view master.
    *
    * @return the view name of this acc closure view master
    */
    @Override
    public java.lang.String getViewName() {
        return _accClosureViewMaster.getViewName();
    }

    /**
    * Sets the view name of this acc closure view master.
    *
    * @param viewName the view name of this acc closure view master
    */
    @Override
    public void setViewName(java.lang.String viewName) {
        _accClosureViewMaster.setViewName(viewName);
    }

    @Override
    public boolean isNew() {
        return _accClosureViewMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _accClosureViewMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _accClosureViewMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _accClosureViewMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _accClosureViewMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _accClosureViewMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _accClosureViewMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _accClosureViewMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _accClosureViewMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _accClosureViewMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _accClosureViewMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AccClosureViewMasterWrapper((AccClosureViewMaster) _accClosureViewMaster.clone());
    }

    @Override
    public int compareTo(AccClosureViewMaster accClosureViewMaster) {
        return _accClosureViewMaster.compareTo(accClosureViewMaster);
    }

    @Override
    public int hashCode() {
        return _accClosureViewMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AccClosureViewMaster> toCacheModel() {
        return _accClosureViewMaster.toCacheModel();
    }

    @Override
    public AccClosureViewMaster toEscapedModel() {
        return new AccClosureViewMasterWrapper(_accClosureViewMaster.toEscapedModel());
    }

    @Override
    public AccClosureViewMaster toUnescapedModel() {
        return new AccClosureViewMasterWrapper(_accClosureViewMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _accClosureViewMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _accClosureViewMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _accClosureViewMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AccClosureViewMasterWrapper)) {
            return false;
        }

        AccClosureViewMasterWrapper accClosureViewMasterWrapper = (AccClosureViewMasterWrapper) obj;

        if (Validator.equals(_accClosureViewMaster,
                    accClosureViewMasterWrapper._accClosureViewMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AccClosureViewMaster getWrappedAccClosureViewMaster() {
        return _accClosureViewMaster;
    }

    @Override
    public AccClosureViewMaster getWrappedModel() {
        return _accClosureViewMaster;
    }

    @Override
    public void resetOriginalValues() {
        _accClosureViewMaster.resetOriginalValues();
    }
}
