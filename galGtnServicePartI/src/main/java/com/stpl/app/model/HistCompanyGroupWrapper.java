package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HistCompanyGroup}.
 * </p>
 *
 * @author
 * @see HistCompanyGroup
 * @generated
 */
public class HistCompanyGroupWrapper implements HistCompanyGroup,
    ModelWrapper<HistCompanyGroup> {
    private HistCompanyGroup _histCompanyGroup;

    public HistCompanyGroupWrapper(HistCompanyGroup histCompanyGroup) {
        _histCompanyGroup = histCompanyGroup;
    }

    @Override
    public Class<?> getModelClass() {
        return HistCompanyGroup.class;
    }

    @Override
    public String getModelClassName() {
        return HistCompanyGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyGroupNo", getCompanyGroupNo());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("companyGroupDescription", getCompanyGroupDescription());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyGroupName", getCompanyGroupName());
        attributes.put("companyFilter", getCompanyFilter());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String companyGroupNo = (String) attributes.get("companyGroupNo");

        if (companyGroupNo != null) {
            setCompanyGroupNo(companyGroupNo);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
        }

        Integer companyGroupSid = (Integer) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        String companyGroupDescription = (String) attributes.get(
                "companyGroupDescription");

        if (companyGroupDescription != null) {
            setCompanyGroupDescription(companyGroupDescription);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String companyGroupName = (String) attributes.get("companyGroupName");

        if (companyGroupName != null) {
            setCompanyGroupName(companyGroupName);
        }

        String companyFilter = (String) attributes.get("companyFilter");

        if (companyFilter != null) {
            setCompanyFilter(companyFilter);
        }
    }

    /**
    * Returns the primary key of this hist company group.
    *
    * @return the primary key of this hist company group
    */
    @Override
    public com.stpl.app.service.persistence.HistCompanyGroupPK getPrimaryKey() {
        return _histCompanyGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hist company group.
    *
    * @param primaryKey the primary key of this hist company group
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.HistCompanyGroupPK primaryKey) {
        _histCompanyGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the company group no of this hist company group.
    *
    * @return the company group no of this hist company group
    */
    @Override
    public java.lang.String getCompanyGroupNo() {
        return _histCompanyGroup.getCompanyGroupNo();
    }

    /**
    * Sets the company group no of this hist company group.
    *
    * @param companyGroupNo the company group no of this hist company group
    */
    @Override
    public void setCompanyGroupNo(java.lang.String companyGroupNo) {
        _histCompanyGroup.setCompanyGroupNo(companyGroupNo);
    }

    /**
    * Returns the created date of this hist company group.
    *
    * @return the created date of this hist company group
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _histCompanyGroup.getCreatedDate();
    }

    /**
    * Sets the created date of this hist company group.
    *
    * @param createdDate the created date of this hist company group
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _histCompanyGroup.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this hist company group.
    *
    * @return the created by of this hist company group
    */
    @Override
    public int getCreatedBy() {
        return _histCompanyGroup.getCreatedBy();
    }

    /**
    * Sets the created by of this hist company group.
    *
    * @param createdBy the created by of this hist company group
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _histCompanyGroup.setCreatedBy(createdBy);
    }

    /**
    * Returns the action date of this hist company group.
    *
    * @return the action date of this hist company group
    */
    @Override
    public java.util.Date getActionDate() {
        return _histCompanyGroup.getActionDate();
    }

    /**
    * Sets the action date of this hist company group.
    *
    * @param actionDate the action date of this hist company group
    */
    @Override
    public void setActionDate(java.util.Date actionDate) {
        _histCompanyGroup.setActionDate(actionDate);
    }

    /**
    * Returns the action flag of this hist company group.
    *
    * @return the action flag of this hist company group
    */
    @Override
    public java.lang.String getActionFlag() {
        return _histCompanyGroup.getActionFlag();
    }

    /**
    * Sets the action flag of this hist company group.
    *
    * @param actionFlag the action flag of this hist company group
    */
    @Override
    public void setActionFlag(java.lang.String actionFlag) {
        _histCompanyGroup.setActionFlag(actionFlag);
    }

    /**
    * Returns the company group sid of this hist company group.
    *
    * @return the company group sid of this hist company group
    */
    @Override
    public int getCompanyGroupSid() {
        return _histCompanyGroup.getCompanyGroupSid();
    }

    /**
    * Sets the company group sid of this hist company group.
    *
    * @param companyGroupSid the company group sid of this hist company group
    */
    @Override
    public void setCompanyGroupSid(int companyGroupSid) {
        _histCompanyGroup.setCompanyGroupSid(companyGroupSid);
    }

    /**
    * Returns the company group description of this hist company group.
    *
    * @return the company group description of this hist company group
    */
    @Override
    public java.lang.String getCompanyGroupDescription() {
        return _histCompanyGroup.getCompanyGroupDescription();
    }

    /**
    * Sets the company group description of this hist company group.
    *
    * @param companyGroupDescription the company group description of this hist company group
    */
    @Override
    public void setCompanyGroupDescription(
        java.lang.String companyGroupDescription) {
        _histCompanyGroup.setCompanyGroupDescription(companyGroupDescription);
    }

    /**
    * Returns the version no of this hist company group.
    *
    * @return the version no of this hist company group
    */
    @Override
    public int getVersionNo() {
        return _histCompanyGroup.getVersionNo();
    }

    /**
    * Sets the version no of this hist company group.
    *
    * @param versionNo the version no of this hist company group
    */
    @Override
    public void setVersionNo(int versionNo) {
        _histCompanyGroup.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this hist company group.
    *
    * @return the modified by of this hist company group
    */
    @Override
    public int getModifiedBy() {
        return _histCompanyGroup.getModifiedBy();
    }

    /**
    * Sets the modified by of this hist company group.
    *
    * @param modifiedBy the modified by of this hist company group
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _histCompanyGroup.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this hist company group.
    *
    * @return the modified date of this hist company group
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _histCompanyGroup.getModifiedDate();
    }

    /**
    * Sets the modified date of this hist company group.
    *
    * @param modifiedDate the modified date of this hist company group
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _histCompanyGroup.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the company group name of this hist company group.
    *
    * @return the company group name of this hist company group
    */
    @Override
    public java.lang.String getCompanyGroupName() {
        return _histCompanyGroup.getCompanyGroupName();
    }

    /**
    * Sets the company group name of this hist company group.
    *
    * @param companyGroupName the company group name of this hist company group
    */
    @Override
    public void setCompanyGroupName(java.lang.String companyGroupName) {
        _histCompanyGroup.setCompanyGroupName(companyGroupName);
    }

    /**
    * Returns the company filter of this hist company group.
    *
    * @return the company filter of this hist company group
    */
    @Override
    public java.lang.String getCompanyFilter() {
        return _histCompanyGroup.getCompanyFilter();
    }

    /**
    * Sets the company filter of this hist company group.
    *
    * @param companyFilter the company filter of this hist company group
    */
    @Override
    public void setCompanyFilter(java.lang.String companyFilter) {
        _histCompanyGroup.setCompanyFilter(companyFilter);
    }

    @Override
    public boolean isNew() {
        return _histCompanyGroup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _histCompanyGroup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _histCompanyGroup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _histCompanyGroup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _histCompanyGroup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _histCompanyGroup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _histCompanyGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _histCompanyGroup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _histCompanyGroup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _histCompanyGroup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _histCompanyGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HistCompanyGroupWrapper((HistCompanyGroup) _histCompanyGroup.clone());
    }

    @Override
    public int compareTo(HistCompanyGroup histCompanyGroup) {
        return _histCompanyGroup.compareTo(histCompanyGroup);
    }

    @Override
    public int hashCode() {
        return _histCompanyGroup.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HistCompanyGroup> toCacheModel() {
        return _histCompanyGroup.toCacheModel();
    }

    @Override
    public HistCompanyGroup toEscapedModel() {
        return new HistCompanyGroupWrapper(_histCompanyGroup.toEscapedModel());
    }

    @Override
    public HistCompanyGroup toUnescapedModel() {
        return new HistCompanyGroupWrapper(_histCompanyGroup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _histCompanyGroup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _histCompanyGroup.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _histCompanyGroup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistCompanyGroupWrapper)) {
            return false;
        }

        HistCompanyGroupWrapper histCompanyGroupWrapper = (HistCompanyGroupWrapper) obj;

        if (Validator.equals(_histCompanyGroup,
                    histCompanyGroupWrapper._histCompanyGroup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HistCompanyGroup getWrappedHistCompanyGroup() {
        return _histCompanyGroup;
    }

    @Override
    public HistCompanyGroup getWrappedModel() {
        return _histCompanyGroup;
    }

    @Override
    public void resetOriginalValues() {
        _histCompanyGroup.resetOriginalValues();
    }
}
