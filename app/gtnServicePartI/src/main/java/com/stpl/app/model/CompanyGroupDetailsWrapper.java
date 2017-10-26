package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CompanyGroupDetails}.
 * </p>
 *
 * @author
 * @see CompanyGroupDetails
 * @generated
 */
public class CompanyGroupDetailsWrapper implements CompanyGroupDetails,
    ModelWrapper<CompanyGroupDetails> {
    private CompanyGroupDetails _companyGroupDetails;

    public CompanyGroupDetailsWrapper(CompanyGroupDetails companyGroupDetails) {
        _companyGroupDetails = companyGroupDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyGroupDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
        attributes.put("companyTradeclassSid", getCompanyTradeclassSid());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("companyGroupDetailsSid", getCompanyGroupDetailsSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyMasterSid", getCompanyMasterSid());

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

        String companyParentDetailsSid = (String) attributes.get(
                "companyParentDetailsSid");

        if (companyParentDetailsSid != null) {
            setCompanyParentDetailsSid(companyParentDetailsSid);
        }

        Integer companyTradeclassSid = (Integer) attributes.get(
                "companyTradeclassSid");

        if (companyTradeclassSid != null) {
            setCompanyTradeclassSid(companyTradeclassSid);
        }

        Integer companyGroupSid = (Integer) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer companyGroupDetailsSid = (Integer) attributes.get(
                "companyGroupDetailsSid");

        if (companyGroupDetailsSid != null) {
            setCompanyGroupDetailsSid(companyGroupDetailsSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    /**
    * Returns the primary key of this company group details.
    *
    * @return the primary key of this company group details
    */
    @Override
    public int getPrimaryKey() {
        return _companyGroupDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this company group details.
    *
    * @param primaryKey the primary key of this company group details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _companyGroupDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this company group details.
    *
    * @return the created date of this company group details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _companyGroupDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this company group details.
    *
    * @param createdDate the created date of this company group details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _companyGroupDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this company group details.
    *
    * @return the created by of this company group details
    */
    @Override
    public int getCreatedBy() {
        return _companyGroupDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this company group details.
    *
    * @param createdBy the created by of this company group details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _companyGroupDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the company parent details sid of this company group details.
    *
    * @return the company parent details sid of this company group details
    */
    @Override
    public java.lang.String getCompanyParentDetailsSid() {
        return _companyGroupDetails.getCompanyParentDetailsSid();
    }

    /**
    * Sets the company parent details sid of this company group details.
    *
    * @param companyParentDetailsSid the company parent details sid of this company group details
    */
    @Override
    public void setCompanyParentDetailsSid(
        java.lang.String companyParentDetailsSid) {
        _companyGroupDetails.setCompanyParentDetailsSid(companyParentDetailsSid);
    }

    /**
    * Returns the company tradeclass sid of this company group details.
    *
    * @return the company tradeclass sid of this company group details
    */
    @Override
    public int getCompanyTradeclassSid() {
        return _companyGroupDetails.getCompanyTradeclassSid();
    }

    /**
    * Sets the company tradeclass sid of this company group details.
    *
    * @param companyTradeclassSid the company tradeclass sid of this company group details
    */
    @Override
    public void setCompanyTradeclassSid(int companyTradeclassSid) {
        _companyGroupDetails.setCompanyTradeclassSid(companyTradeclassSid);
    }

    /**
    * Returns the company group sid of this company group details.
    *
    * @return the company group sid of this company group details
    */
    @Override
    public int getCompanyGroupSid() {
        return _companyGroupDetails.getCompanyGroupSid();
    }

    /**
    * Sets the company group sid of this company group details.
    *
    * @param companyGroupSid the company group sid of this company group details
    */
    @Override
    public void setCompanyGroupSid(int companyGroupSid) {
        _companyGroupDetails.setCompanyGroupSid(companyGroupSid);
    }

    /**
    * Returns the version no of this company group details.
    *
    * @return the version no of this company group details
    */
    @Override
    public int getVersionNo() {
        return _companyGroupDetails.getVersionNo();
    }

    /**
    * Sets the version no of this company group details.
    *
    * @param versionNo the version no of this company group details
    */
    @Override
    public void setVersionNo(int versionNo) {
        _companyGroupDetails.setVersionNo(versionNo);
    }

    /**
    * Returns the company group details sid of this company group details.
    *
    * @return the company group details sid of this company group details
    */
    @Override
    public int getCompanyGroupDetailsSid() {
        return _companyGroupDetails.getCompanyGroupDetailsSid();
    }

    /**
    * Sets the company group details sid of this company group details.
    *
    * @param companyGroupDetailsSid the company group details sid of this company group details
    */
    @Override
    public void setCompanyGroupDetailsSid(int companyGroupDetailsSid) {
        _companyGroupDetails.setCompanyGroupDetailsSid(companyGroupDetailsSid);
    }

    /**
    * Returns the modified by of this company group details.
    *
    * @return the modified by of this company group details
    */
    @Override
    public int getModifiedBy() {
        return _companyGroupDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this company group details.
    *
    * @param modifiedBy the modified by of this company group details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _companyGroupDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this company group details.
    *
    * @return the modified date of this company group details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _companyGroupDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this company group details.
    *
    * @param modifiedDate the modified date of this company group details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _companyGroupDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the company master sid of this company group details.
    *
    * @return the company master sid of this company group details
    */
    @Override
    public int getCompanyMasterSid() {
        return _companyGroupDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this company group details.
    *
    * @param companyMasterSid the company master sid of this company group details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyGroupDetails.setCompanyMasterSid(companyMasterSid);
    }

    @Override
    public boolean isNew() {
        return _companyGroupDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _companyGroupDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _companyGroupDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _companyGroupDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _companyGroupDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _companyGroupDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _companyGroupDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _companyGroupDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _companyGroupDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _companyGroupDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _companyGroupDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CompanyGroupDetailsWrapper((CompanyGroupDetails) _companyGroupDetails.clone());
    }

    @Override
    public int compareTo(CompanyGroupDetails companyGroupDetails) {
        return _companyGroupDetails.compareTo(companyGroupDetails);
    }

    @Override
    public int hashCode() {
        return _companyGroupDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CompanyGroupDetails> toCacheModel() {
        return _companyGroupDetails.toCacheModel();
    }

    @Override
    public CompanyGroupDetails toEscapedModel() {
        return new CompanyGroupDetailsWrapper(_companyGroupDetails.toEscapedModel());
    }

    @Override
    public CompanyGroupDetails toUnescapedModel() {
        return new CompanyGroupDetailsWrapper(_companyGroupDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _companyGroupDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _companyGroupDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _companyGroupDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CompanyGroupDetailsWrapper)) {
            return false;
        }

        CompanyGroupDetailsWrapper companyGroupDetailsWrapper = (CompanyGroupDetailsWrapper) obj;

        if (Validator.equals(_companyGroupDetails,
                    companyGroupDetailsWrapper._companyGroupDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CompanyGroupDetails getWrappedCompanyGroupDetails() {
        return _companyGroupDetails;
    }

    @Override
    public CompanyGroupDetails getWrappedModel() {
        return _companyGroupDetails;
    }

    @Override
    public void resetOriginalValues() {
        _companyGroupDetails.resetOriginalValues();
    }
}
