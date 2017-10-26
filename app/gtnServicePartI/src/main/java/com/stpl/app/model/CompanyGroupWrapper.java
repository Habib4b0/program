package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CompanyGroup}.
 * </p>
 *
 * @author
 * @see CompanyGroup
 * @generated
 */
public class CompanyGroupWrapper implements CompanyGroup,
    ModelWrapper<CompanyGroup> {
    private CompanyGroup _companyGroup;

    public CompanyGroupWrapper(CompanyGroup companyGroup) {
        _companyGroup = companyGroup;
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyGroup.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyGroupNo", getCompanyGroupNo());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyFilter", getCompanyFilter());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("companyGroupDescription", getCompanyGroupDescription());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyGroupName", getCompanyGroupName());

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

        String companyFilter = (String) attributes.get("companyFilter");

        if (companyFilter != null) {
            setCompanyFilter(companyFilter);
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
    }

    /**
    * Returns the primary key of this company group.
    *
    * @return the primary key of this company group
    */
    @Override
    public int getPrimaryKey() {
        return _companyGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this company group.
    *
    * @param primaryKey the primary key of this company group
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _companyGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the company group no of this company group.
    *
    * @return the company group no of this company group
    */
    @Override
    public java.lang.String getCompanyGroupNo() {
        return _companyGroup.getCompanyGroupNo();
    }

    /**
    * Sets the company group no of this company group.
    *
    * @param companyGroupNo the company group no of this company group
    */
    @Override
    public void setCompanyGroupNo(java.lang.String companyGroupNo) {
        _companyGroup.setCompanyGroupNo(companyGroupNo);
    }

    /**
    * Returns the created date of this company group.
    *
    * @return the created date of this company group
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _companyGroup.getCreatedDate();
    }

    /**
    * Sets the created date of this company group.
    *
    * @param createdDate the created date of this company group
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _companyGroup.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this company group.
    *
    * @return the created by of this company group
    */
    @Override
    public int getCreatedBy() {
        return _companyGroup.getCreatedBy();
    }

    /**
    * Sets the created by of this company group.
    *
    * @param createdBy the created by of this company group
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _companyGroup.setCreatedBy(createdBy);
    }

    /**
    * Returns the company filter of this company group.
    *
    * @return the company filter of this company group
    */
    @Override
    public java.lang.String getCompanyFilter() {
        return _companyGroup.getCompanyFilter();
    }

    /**
    * Sets the company filter of this company group.
    *
    * @param companyFilter the company filter of this company group
    */
    @Override
    public void setCompanyFilter(java.lang.String companyFilter) {
        _companyGroup.setCompanyFilter(companyFilter);
    }

    /**
    * Returns the company group sid of this company group.
    *
    * @return the company group sid of this company group
    */
    @Override
    public int getCompanyGroupSid() {
        return _companyGroup.getCompanyGroupSid();
    }

    /**
    * Sets the company group sid of this company group.
    *
    * @param companyGroupSid the company group sid of this company group
    */
    @Override
    public void setCompanyGroupSid(int companyGroupSid) {
        _companyGroup.setCompanyGroupSid(companyGroupSid);
    }

    /**
    * Returns the company group description of this company group.
    *
    * @return the company group description of this company group
    */
    @Override
    public java.lang.String getCompanyGroupDescription() {
        return _companyGroup.getCompanyGroupDescription();
    }

    /**
    * Sets the company group description of this company group.
    *
    * @param companyGroupDescription the company group description of this company group
    */
    @Override
    public void setCompanyGroupDescription(
        java.lang.String companyGroupDescription) {
        _companyGroup.setCompanyGroupDescription(companyGroupDescription);
    }

    /**
    * Returns the version no of this company group.
    *
    * @return the version no of this company group
    */
    @Override
    public int getVersionNo() {
        return _companyGroup.getVersionNo();
    }

    /**
    * Sets the version no of this company group.
    *
    * @param versionNo the version no of this company group
    */
    @Override
    public void setVersionNo(int versionNo) {
        _companyGroup.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this company group.
    *
    * @return the modified by of this company group
    */
    @Override
    public int getModifiedBy() {
        return _companyGroup.getModifiedBy();
    }

    /**
    * Sets the modified by of this company group.
    *
    * @param modifiedBy the modified by of this company group
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _companyGroup.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this company group.
    *
    * @return the modified date of this company group
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _companyGroup.getModifiedDate();
    }

    /**
    * Sets the modified date of this company group.
    *
    * @param modifiedDate the modified date of this company group
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _companyGroup.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the company group name of this company group.
    *
    * @return the company group name of this company group
    */
    @Override
    public java.lang.String getCompanyGroupName() {
        return _companyGroup.getCompanyGroupName();
    }

    /**
    * Sets the company group name of this company group.
    *
    * @param companyGroupName the company group name of this company group
    */
    @Override
    public void setCompanyGroupName(java.lang.String companyGroupName) {
        _companyGroup.setCompanyGroupName(companyGroupName);
    }

    @Override
    public boolean isNew() {
        return _companyGroup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _companyGroup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _companyGroup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _companyGroup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _companyGroup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _companyGroup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _companyGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _companyGroup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _companyGroup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _companyGroup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _companyGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CompanyGroupWrapper((CompanyGroup) _companyGroup.clone());
    }

    @Override
    public int compareTo(CompanyGroup companyGroup) {
        return _companyGroup.compareTo(companyGroup);
    }

    @Override
    public int hashCode() {
        return _companyGroup.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CompanyGroup> toCacheModel() {
        return _companyGroup.toCacheModel();
    }

    @Override
    public CompanyGroup toEscapedModel() {
        return new CompanyGroupWrapper(_companyGroup.toEscapedModel());
    }

    @Override
    public CompanyGroup toUnescapedModel() {
        return new CompanyGroupWrapper(_companyGroup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _companyGroup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _companyGroup.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _companyGroup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CompanyGroupWrapper)) {
            return false;
        }

        CompanyGroupWrapper companyGroupWrapper = (CompanyGroupWrapper) obj;

        if (Validator.equals(_companyGroup, companyGroupWrapper._companyGroup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CompanyGroup getWrappedCompanyGroup() {
        return _companyGroup;
    }

    @Override
    public CompanyGroup getWrappedModel() {
        return _companyGroup;
    }

    @Override
    public void resetOriginalValues() {
        _companyGroup.resetOriginalValues();
    }
}
