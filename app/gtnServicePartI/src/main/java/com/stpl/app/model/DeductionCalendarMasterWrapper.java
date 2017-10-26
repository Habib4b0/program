package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DeductionCalendarMaster}.
 * </p>
 *
 * @author
 * @see DeductionCalendarMaster
 * @generated
 */
public class DeductionCalendarMasterWrapper implements DeductionCalendarMaster,
    ModelWrapper<DeductionCalendarMaster> {
    private DeductionCalendarMaster _deductionCalendarMaster;

    public DeductionCalendarMasterWrapper(
        DeductionCalendarMaster deductionCalendarMaster) {
        _deductionCalendarMaster = deductionCalendarMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionCalendarMaster.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionCalendarMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("deductionCalendarNo", getDeductionCalendarNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("category", getCategory());
        attributes.put("additionalNotes", getAdditionalNotes());
        attributes.put("userId", getUserId());
        attributes.put("deductionCalendarName", getDeductionCalendarName());
        attributes.put("deductionCalendarDesc", getDeductionCalendarDesc());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        String deductionCalendarNo = (String) attributes.get(
                "deductionCalendarNo");

        if (deductionCalendarNo != null) {
            setDeductionCalendarNo(deductionCalendarNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer category = (Integer) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        String additionalNotes = (String) attributes.get("additionalNotes");

        if (additionalNotes != null) {
            setAdditionalNotes(additionalNotes);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String deductionCalendarName = (String) attributes.get(
                "deductionCalendarName");

        if (deductionCalendarName != null) {
            setDeductionCalendarName(deductionCalendarName);
        }

        String deductionCalendarDesc = (String) attributes.get(
                "deductionCalendarDesc");

        if (deductionCalendarDesc != null) {
            setDeductionCalendarDesc(deductionCalendarDesc);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this deduction calendar master.
    *
    * @return the primary key of this deduction calendar master
    */
    @Override
    public int getPrimaryKey() {
        return _deductionCalendarMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this deduction calendar master.
    *
    * @param primaryKey the primary key of this deduction calendar master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _deductionCalendarMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this deduction calendar master.
    *
    * @return the created by of this deduction calendar master
    */
    @Override
    public int getCreatedBy() {
        return _deductionCalendarMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this deduction calendar master.
    *
    * @param createdBy the created by of this deduction calendar master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _deductionCalendarMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the deduction calendar master sid of this deduction calendar master.
    *
    * @return the deduction calendar master sid of this deduction calendar master
    */
    @Override
    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarMaster.getDeductionCalendarMasterSid();
    }

    /**
    * Sets the deduction calendar master sid of this deduction calendar master.
    *
    * @param deductionCalendarMasterSid the deduction calendar master sid of this deduction calendar master
    */
    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarMaster.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
    }

    /**
    * Returns the deduction calendar no of this deduction calendar master.
    *
    * @return the deduction calendar no of this deduction calendar master
    */
    @Override
    public java.lang.String getDeductionCalendarNo() {
        return _deductionCalendarMaster.getDeductionCalendarNo();
    }

    /**
    * Sets the deduction calendar no of this deduction calendar master.
    *
    * @param deductionCalendarNo the deduction calendar no of this deduction calendar master
    */
    @Override
    public void setDeductionCalendarNo(java.lang.String deductionCalendarNo) {
        _deductionCalendarMaster.setDeductionCalendarNo(deductionCalendarNo);
    }

    /**
    * Returns the modified by of this deduction calendar master.
    *
    * @return the modified by of this deduction calendar master
    */
    @Override
    public int getModifiedBy() {
        return _deductionCalendarMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this deduction calendar master.
    *
    * @param modifiedBy the modified by of this deduction calendar master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _deductionCalendarMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this deduction calendar master.
    *
    * @return the created date of this deduction calendar master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _deductionCalendarMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this deduction calendar master.
    *
    * @param createdDate the created date of this deduction calendar master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _deductionCalendarMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the category of this deduction calendar master.
    *
    * @return the category of this deduction calendar master
    */
    @Override
    public int getCategory() {
        return _deductionCalendarMaster.getCategory();
    }

    /**
    * Sets the category of this deduction calendar master.
    *
    * @param category the category of this deduction calendar master
    */
    @Override
    public void setCategory(int category) {
        _deductionCalendarMaster.setCategory(category);
    }

    /**
    * Returns the additional notes of this deduction calendar master.
    *
    * @return the additional notes of this deduction calendar master
    */
    @Override
    public java.lang.String getAdditionalNotes() {
        return _deductionCalendarMaster.getAdditionalNotes();
    }

    /**
    * Sets the additional notes of this deduction calendar master.
    *
    * @param additionalNotes the additional notes of this deduction calendar master
    */
    @Override
    public void setAdditionalNotes(java.lang.String additionalNotes) {
        _deductionCalendarMaster.setAdditionalNotes(additionalNotes);
    }

    /**
    * Returns the user ID of this deduction calendar master.
    *
    * @return the user ID of this deduction calendar master
    */
    @Override
    public int getUserId() {
        return _deductionCalendarMaster.getUserId();
    }

    /**
    * Sets the user ID of this deduction calendar master.
    *
    * @param userId the user ID of this deduction calendar master
    */
    @Override
    public void setUserId(int userId) {
        _deductionCalendarMaster.setUserId(userId);
    }

    /**
    * Returns the deduction calendar name of this deduction calendar master.
    *
    * @return the deduction calendar name of this deduction calendar master
    */
    @Override
    public java.lang.String getDeductionCalendarName() {
        return _deductionCalendarMaster.getDeductionCalendarName();
    }

    /**
    * Sets the deduction calendar name of this deduction calendar master.
    *
    * @param deductionCalendarName the deduction calendar name of this deduction calendar master
    */
    @Override
    public void setDeductionCalendarName(java.lang.String deductionCalendarName) {
        _deductionCalendarMaster.setDeductionCalendarName(deductionCalendarName);
    }

    /**
    * Returns the deduction calendar desc of this deduction calendar master.
    *
    * @return the deduction calendar desc of this deduction calendar master
    */
    @Override
    public java.lang.String getDeductionCalendarDesc() {
        return _deductionCalendarMaster.getDeductionCalendarDesc();
    }

    /**
    * Sets the deduction calendar desc of this deduction calendar master.
    *
    * @param deductionCalendarDesc the deduction calendar desc of this deduction calendar master
    */
    @Override
    public void setDeductionCalendarDesc(java.lang.String deductionCalendarDesc) {
        _deductionCalendarMaster.setDeductionCalendarDesc(deductionCalendarDesc);
    }

    /**
    * Returns the modified date of this deduction calendar master.
    *
    * @return the modified date of this deduction calendar master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _deductionCalendarMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this deduction calendar master.
    *
    * @param modifiedDate the modified date of this deduction calendar master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _deductionCalendarMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the inbound status of this deduction calendar master.
    *
    * @return the inbound status of this deduction calendar master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _deductionCalendarMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this deduction calendar master.
    *
    * @param inboundStatus the inbound status of this deduction calendar master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _deductionCalendarMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _deductionCalendarMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _deductionCalendarMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _deductionCalendarMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _deductionCalendarMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _deductionCalendarMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _deductionCalendarMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _deductionCalendarMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _deductionCalendarMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _deductionCalendarMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _deductionCalendarMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _deductionCalendarMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DeductionCalendarMasterWrapper((DeductionCalendarMaster) _deductionCalendarMaster.clone());
    }

    @Override
    public int compareTo(DeductionCalendarMaster deductionCalendarMaster) {
        return _deductionCalendarMaster.compareTo(deductionCalendarMaster);
    }

    @Override
    public int hashCode() {
        return _deductionCalendarMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<DeductionCalendarMaster> toCacheModel() {
        return _deductionCalendarMaster.toCacheModel();
    }

    @Override
    public DeductionCalendarMaster toEscapedModel() {
        return new DeductionCalendarMasterWrapper(_deductionCalendarMaster.toEscapedModel());
    }

    @Override
    public DeductionCalendarMaster toUnescapedModel() {
        return new DeductionCalendarMasterWrapper(_deductionCalendarMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _deductionCalendarMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _deductionCalendarMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _deductionCalendarMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeductionCalendarMasterWrapper)) {
            return false;
        }

        DeductionCalendarMasterWrapper deductionCalendarMasterWrapper = (DeductionCalendarMasterWrapper) obj;

        if (Validator.equals(_deductionCalendarMaster,
                    deductionCalendarMasterWrapper._deductionCalendarMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DeductionCalendarMaster getWrappedDeductionCalendarMaster() {
        return _deductionCalendarMaster;
    }

    @Override
    public DeductionCalendarMaster getWrappedModel() {
        return _deductionCalendarMaster;
    }

    @Override
    public void resetOriginalValues() {
        _deductionCalendarMaster.resetOriginalValues();
    }
}
