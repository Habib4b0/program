package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DeductionGroupDetails}.
 * </p>
 *
 * @author
 * @see DeductionGroupDetails
 * @generated
 */
public class DeductionGroupDetailsWrapper implements DeductionGroupDetails,
    ModelWrapper<DeductionGroupDetails> {
    private DeductionGroupDetails _deductionGroupDetails;

    public DeductionGroupDetailsWrapper(
        DeductionGroupDetails deductionGroupDetails) {
        _deductionGroupDetails = deductionGroupDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionGroupDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("deductionGroupDetailsSid", getDeductionGroupDetailsSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionGroupSid", getDeductionGroupSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer deductionGroupDetailsSid = (Integer) attributes.get(
                "deductionGroupDetailsSid");

        if (deductionGroupDetailsSid != null) {
            setDeductionGroupDetailsSid(deductionGroupDetailsSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer deductionGroupSid = (Integer) attributes.get(
                "deductionGroupSid");

        if (deductionGroupSid != null) {
            setDeductionGroupSid(deductionGroupSid);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this deduction group details.
    *
    * @return the primary key of this deduction group details
    */
    @Override
    public int getPrimaryKey() {
        return _deductionGroupDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this deduction group details.
    *
    * @param primaryKey the primary key of this deduction group details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _deductionGroupDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the deduction group details sid of this deduction group details.
    *
    * @return the deduction group details sid of this deduction group details
    */
    @Override
    public int getDeductionGroupDetailsSid() {
        return _deductionGroupDetails.getDeductionGroupDetailsSid();
    }

    /**
    * Sets the deduction group details sid of this deduction group details.
    *
    * @param deductionGroupDetailsSid the deduction group details sid of this deduction group details
    */
    @Override
    public void setDeductionGroupDetailsSid(int deductionGroupDetailsSid) {
        _deductionGroupDetails.setDeductionGroupDetailsSid(deductionGroupDetailsSid);
    }

    /**
    * Returns the created date of this deduction group details.
    *
    * @return the created date of this deduction group details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _deductionGroupDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this deduction group details.
    *
    * @param createdDate the created date of this deduction group details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _deductionGroupDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this deduction group details.
    *
    * @return the created by of this deduction group details
    */
    @Override
    public int getCreatedBy() {
        return _deductionGroupDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this deduction group details.
    *
    * @param createdBy the created by of this deduction group details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _deductionGroupDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the deduction group sid of this deduction group details.
    *
    * @return the deduction group sid of this deduction group details
    */
    @Override
    public int getDeductionGroupSid() {
        return _deductionGroupDetails.getDeductionGroupSid();
    }

    /**
    * Sets the deduction group sid of this deduction group details.
    *
    * @param deductionGroupSid the deduction group sid of this deduction group details
    */
    @Override
    public void setDeductionGroupSid(int deductionGroupSid) {
        _deductionGroupDetails.setDeductionGroupSid(deductionGroupSid);
    }

    /**
    * Returns the version no of this deduction group details.
    *
    * @return the version no of this deduction group details
    */
    @Override
    public int getVersionNo() {
        return _deductionGroupDetails.getVersionNo();
    }

    /**
    * Sets the version no of this deduction group details.
    *
    * @param versionNo the version no of this deduction group details
    */
    @Override
    public void setVersionNo(int versionNo) {
        _deductionGroupDetails.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this deduction group details.
    *
    * @return the modified by of this deduction group details
    */
    @Override
    public int getModifiedBy() {
        return _deductionGroupDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this deduction group details.
    *
    * @param modifiedBy the modified by of this deduction group details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _deductionGroupDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the rs model sid of this deduction group details.
    *
    * @return the rs model sid of this deduction group details
    */
    @Override
    public int getRsModelSid() {
        return _deductionGroupDetails.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this deduction group details.
    *
    * @param rsModelSid the rs model sid of this deduction group details
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _deductionGroupDetails.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the modified date of this deduction group details.
    *
    * @return the modified date of this deduction group details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _deductionGroupDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this deduction group details.
    *
    * @param modifiedDate the modified date of this deduction group details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _deductionGroupDetails.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _deductionGroupDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _deductionGroupDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _deductionGroupDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _deductionGroupDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _deductionGroupDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _deductionGroupDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _deductionGroupDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _deductionGroupDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _deductionGroupDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _deductionGroupDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _deductionGroupDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DeductionGroupDetailsWrapper((DeductionGroupDetails) _deductionGroupDetails.clone());
    }

    @Override
    public int compareTo(DeductionGroupDetails deductionGroupDetails) {
        return _deductionGroupDetails.compareTo(deductionGroupDetails);
    }

    @Override
    public int hashCode() {
        return _deductionGroupDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<DeductionGroupDetails> toCacheModel() {
        return _deductionGroupDetails.toCacheModel();
    }

    @Override
    public DeductionGroupDetails toEscapedModel() {
        return new DeductionGroupDetailsWrapper(_deductionGroupDetails.toEscapedModel());
    }

    @Override
    public DeductionGroupDetails toUnescapedModel() {
        return new DeductionGroupDetailsWrapper(_deductionGroupDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _deductionGroupDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _deductionGroupDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _deductionGroupDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeductionGroupDetailsWrapper)) {
            return false;
        }

        DeductionGroupDetailsWrapper deductionGroupDetailsWrapper = (DeductionGroupDetailsWrapper) obj;

        if (Validator.equals(_deductionGroupDetails,
                    deductionGroupDetailsWrapper._deductionGroupDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DeductionGroupDetails getWrappedDeductionGroupDetails() {
        return _deductionGroupDetails;
    }

    @Override
    public DeductionGroupDetails getWrappedModel() {
        return _deductionGroupDetails;
    }

    @Override
    public void resetOriginalValues() {
        _deductionGroupDetails.resetOriginalValues();
    }
}
