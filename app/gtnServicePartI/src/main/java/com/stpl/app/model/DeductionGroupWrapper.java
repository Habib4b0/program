package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DeductionGroup}.
 * </p>
 *
 * @author
 * @see DeductionGroup
 * @generated
 */
public class DeductionGroupWrapper implements DeductionGroup,
    ModelWrapper<DeductionGroup> {
    private DeductionGroup _deductionGroup;

    public DeductionGroupWrapper(DeductionGroup deductionGroup) {
        _deductionGroup = deductionGroup;
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionGroup.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("deductionFilter", getDeductionFilter());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionGroupSid", getDeductionGroupSid());
        attributes.put("deductionGroupName", getDeductionGroupName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("deductionGroupDescription",
            getDeductionGroupDescription());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("deductionGroupNo", getDeductionGroupNo());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String deductionFilter = (String) attributes.get("deductionFilter");

        if (deductionFilter != null) {
            setDeductionFilter(deductionFilter);
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

        String deductionGroupName = (String) attributes.get(
                "deductionGroupName");

        if (deductionGroupName != null) {
            setDeductionGroupName(deductionGroupName);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String deductionGroupDescription = (String) attributes.get(
                "deductionGroupDescription");

        if (deductionGroupDescription != null) {
            setDeductionGroupDescription(deductionGroupDescription);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String deductionGroupNo = (String) attributes.get("deductionGroupNo");

        if (deductionGroupNo != null) {
            setDeductionGroupNo(deductionGroupNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this deduction group.
    *
    * @return the primary key of this deduction group
    */
    @Override
    public int getPrimaryKey() {
        return _deductionGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this deduction group.
    *
    * @param primaryKey the primary key of this deduction group
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _deductionGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the deduction filter of this deduction group.
    *
    * @return the deduction filter of this deduction group
    */
    @Override
    public java.lang.String getDeductionFilter() {
        return _deductionGroup.getDeductionFilter();
    }

    /**
    * Sets the deduction filter of this deduction group.
    *
    * @param deductionFilter the deduction filter of this deduction group
    */
    @Override
    public void setDeductionFilter(java.lang.String deductionFilter) {
        _deductionGroup.setDeductionFilter(deductionFilter);
    }

    /**
    * Returns the created date of this deduction group.
    *
    * @return the created date of this deduction group
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _deductionGroup.getCreatedDate();
    }

    /**
    * Sets the created date of this deduction group.
    *
    * @param createdDate the created date of this deduction group
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _deductionGroup.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this deduction group.
    *
    * @return the created by of this deduction group
    */
    @Override
    public int getCreatedBy() {
        return _deductionGroup.getCreatedBy();
    }

    /**
    * Sets the created by of this deduction group.
    *
    * @param createdBy the created by of this deduction group
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _deductionGroup.setCreatedBy(createdBy);
    }

    /**
    * Returns the deduction group sid of this deduction group.
    *
    * @return the deduction group sid of this deduction group
    */
    @Override
    public int getDeductionGroupSid() {
        return _deductionGroup.getDeductionGroupSid();
    }

    /**
    * Sets the deduction group sid of this deduction group.
    *
    * @param deductionGroupSid the deduction group sid of this deduction group
    */
    @Override
    public void setDeductionGroupSid(int deductionGroupSid) {
        _deductionGroup.setDeductionGroupSid(deductionGroupSid);
    }

    /**
    * Returns the deduction group name of this deduction group.
    *
    * @return the deduction group name of this deduction group
    */
    @Override
    public java.lang.String getDeductionGroupName() {
        return _deductionGroup.getDeductionGroupName();
    }

    /**
    * Sets the deduction group name of this deduction group.
    *
    * @param deductionGroupName the deduction group name of this deduction group
    */
    @Override
    public void setDeductionGroupName(java.lang.String deductionGroupName) {
        _deductionGroup.setDeductionGroupName(deductionGroupName);
    }

    /**
    * Returns the version no of this deduction group.
    *
    * @return the version no of this deduction group
    */
    @Override
    public int getVersionNo() {
        return _deductionGroup.getVersionNo();
    }

    /**
    * Sets the version no of this deduction group.
    *
    * @param versionNo the version no of this deduction group
    */
    @Override
    public void setVersionNo(int versionNo) {
        _deductionGroup.setVersionNo(versionNo);
    }

    /**
    * Returns the deduction group description of this deduction group.
    *
    * @return the deduction group description of this deduction group
    */
    @Override
    public java.lang.String getDeductionGroupDescription() {
        return _deductionGroup.getDeductionGroupDescription();
    }

    /**
    * Sets the deduction group description of this deduction group.
    *
    * @param deductionGroupDescription the deduction group description of this deduction group
    */
    @Override
    public void setDeductionGroupDescription(
        java.lang.String deductionGroupDescription) {
        _deductionGroup.setDeductionGroupDescription(deductionGroupDescription);
    }

    /**
    * Returns the modified by of this deduction group.
    *
    * @return the modified by of this deduction group
    */
    @Override
    public int getModifiedBy() {
        return _deductionGroup.getModifiedBy();
    }

    /**
    * Sets the modified by of this deduction group.
    *
    * @param modifiedBy the modified by of this deduction group
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _deductionGroup.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the deduction group no of this deduction group.
    *
    * @return the deduction group no of this deduction group
    */
    @Override
    public java.lang.String getDeductionGroupNo() {
        return _deductionGroup.getDeductionGroupNo();
    }

    /**
    * Sets the deduction group no of this deduction group.
    *
    * @param deductionGroupNo the deduction group no of this deduction group
    */
    @Override
    public void setDeductionGroupNo(java.lang.String deductionGroupNo) {
        _deductionGroup.setDeductionGroupNo(deductionGroupNo);
    }

    /**
    * Returns the modified date of this deduction group.
    *
    * @return the modified date of this deduction group
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _deductionGroup.getModifiedDate();
    }

    /**
    * Sets the modified date of this deduction group.
    *
    * @param modifiedDate the modified date of this deduction group
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _deductionGroup.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _deductionGroup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _deductionGroup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _deductionGroup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _deductionGroup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _deductionGroup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _deductionGroup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _deductionGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _deductionGroup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _deductionGroup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _deductionGroup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _deductionGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DeductionGroupWrapper((DeductionGroup) _deductionGroup.clone());
    }

    @Override
    public int compareTo(DeductionGroup deductionGroup) {
        return _deductionGroup.compareTo(deductionGroup);
    }

    @Override
    public int hashCode() {
        return _deductionGroup.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<DeductionGroup> toCacheModel() {
        return _deductionGroup.toCacheModel();
    }

    @Override
    public DeductionGroup toEscapedModel() {
        return new DeductionGroupWrapper(_deductionGroup.toEscapedModel());
    }

    @Override
    public DeductionGroup toUnescapedModel() {
        return new DeductionGroupWrapper(_deductionGroup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _deductionGroup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _deductionGroup.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _deductionGroup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeductionGroupWrapper)) {
            return false;
        }

        DeductionGroupWrapper deductionGroupWrapper = (DeductionGroupWrapper) obj;

        if (Validator.equals(_deductionGroup,
                    deductionGroupWrapper._deductionGroup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DeductionGroup getWrappedDeductionGroup() {
        return _deductionGroup;
    }

    @Override
    public DeductionGroup getWrappedModel() {
        return _deductionGroup;
    }

    @Override
    public void resetOriginalValues() {
        _deductionGroup.resetOriginalValues();
    }
}
