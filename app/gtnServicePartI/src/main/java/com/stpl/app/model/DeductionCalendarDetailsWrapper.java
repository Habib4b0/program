package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DeductionCalendarDetails}.
 * </p>
 *
 * @author
 * @see DeductionCalendarDetails
 * @generated
 */
public class DeductionCalendarDetailsWrapper implements DeductionCalendarDetails,
    ModelWrapper<DeductionCalendarDetails> {
    private DeductionCalendarDetails _deductionCalendarDetails;

    public DeductionCalendarDetailsWrapper(
        DeductionCalendarDetails deductionCalendarDetails) {
        _deductionCalendarDetails = deductionCalendarDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionCalendarDetails.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionCalendarDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("adjustmentAllocationMethodology",
            getAdjustmentAllocationMethodology());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("discountAmount", getDiscountAmount());
        attributes.put("adjustmentVariable", getAdjustmentVariable());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String adjustmentValue = (String) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        String adjustmentAllocationMethodology = (String) attributes.get(
                "adjustmentAllocationMethodology");

        if (adjustmentAllocationMethodology != null) {
            setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer discountAmount = (Integer) attributes.get("discountAmount");

        if (discountAmount != null) {
            setDiscountAmount(discountAmount);
        }

        String adjustmentVariable = (String) attributes.get(
                "adjustmentVariable");

        if (adjustmentVariable != null) {
            setAdjustmentVariable(adjustmentVariable);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this deduction calendar details.
    *
    * @return the primary key of this deduction calendar details
    */
    @Override
    public com.stpl.app.service.persistence.DeductionCalendarDetailsPK getPrimaryKey() {
        return _deductionCalendarDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this deduction calendar details.
    *
    * @param primaryKey the primary key of this deduction calendar details
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.DeductionCalendarDetailsPK primaryKey) {
        _deductionCalendarDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the deduction calendar master sid of this deduction calendar details.
    *
    * @return the deduction calendar master sid of this deduction calendar details
    */
    @Override
    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarDetails.getDeductionCalendarMasterSid();
    }

    /**
    * Sets the deduction calendar master sid of this deduction calendar details.
    *
    * @param deductionCalendarMasterSid the deduction calendar master sid of this deduction calendar details
    */
    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarDetails.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
    }

    /**
    * Returns the adjustment basis of this deduction calendar details.
    *
    * @return the adjustment basis of this deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentBasis() {
        return _deductionCalendarDetails.getAdjustmentBasis();
    }

    /**
    * Sets the adjustment basis of this deduction calendar details.
    *
    * @param adjustmentBasis the adjustment basis of this deduction calendar details
    */
    @Override
    public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
        _deductionCalendarDetails.setAdjustmentBasis(adjustmentBasis);
    }

    /**
    * Returns the period sid of this deduction calendar details.
    *
    * @return the period sid of this deduction calendar details
    */
    @Override
    public int getPeriodSid() {
        return _deductionCalendarDetails.getPeriodSid();
    }

    /**
    * Sets the period sid of this deduction calendar details.
    *
    * @param periodSid the period sid of this deduction calendar details
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _deductionCalendarDetails.setPeriodSid(periodSid);
    }

    /**
    * Returns the adjustment value of this deduction calendar details.
    *
    * @return the adjustment value of this deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentValue() {
        return _deductionCalendarDetails.getAdjustmentValue();
    }

    /**
    * Sets the adjustment value of this deduction calendar details.
    *
    * @param adjustmentValue the adjustment value of this deduction calendar details
    */
    @Override
    public void setAdjustmentValue(java.lang.String adjustmentValue) {
        _deductionCalendarDetails.setAdjustmentValue(adjustmentValue);
    }

    /**
    * Returns the adjustment allocation methodology of this deduction calendar details.
    *
    * @return the adjustment allocation methodology of this deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentAllocationMethodology() {
        return _deductionCalendarDetails.getAdjustmentAllocationMethodology();
    }

    /**
    * Sets the adjustment allocation methodology of this deduction calendar details.
    *
    * @param adjustmentAllocationMethodology the adjustment allocation methodology of this deduction calendar details
    */
    @Override
    public void setAdjustmentAllocationMethodology(
        java.lang.String adjustmentAllocationMethodology) {
        _deductionCalendarDetails.setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
    }

    /**
    * Returns the company master sid of this deduction calendar details.
    *
    * @return the company master sid of this deduction calendar details
    */
    @Override
    public int getCompanyMasterSid() {
        return _deductionCalendarDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this deduction calendar details.
    *
    * @param companyMasterSid the company master sid of this deduction calendar details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _deductionCalendarDetails.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the discount amount of this deduction calendar details.
    *
    * @return the discount amount of this deduction calendar details
    */
    @Override
    public int getDiscountAmount() {
        return _deductionCalendarDetails.getDiscountAmount();
    }

    /**
    * Sets the discount amount of this deduction calendar details.
    *
    * @param discountAmount the discount amount of this deduction calendar details
    */
    @Override
    public void setDiscountAmount(int discountAmount) {
        _deductionCalendarDetails.setDiscountAmount(discountAmount);
    }

    /**
    * Returns the adjustment variable of this deduction calendar details.
    *
    * @return the adjustment variable of this deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentVariable() {
        return _deductionCalendarDetails.getAdjustmentVariable();
    }

    /**
    * Sets the adjustment variable of this deduction calendar details.
    *
    * @param adjustmentVariable the adjustment variable of this deduction calendar details
    */
    @Override
    public void setAdjustmentVariable(java.lang.String adjustmentVariable) {
        _deductionCalendarDetails.setAdjustmentVariable(adjustmentVariable);
    }

    /**
    * Returns the item master sid of this deduction calendar details.
    *
    * @return the item master sid of this deduction calendar details
    */
    @Override
    public int getItemMasterSid() {
        return _deductionCalendarDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this deduction calendar details.
    *
    * @param itemMasterSid the item master sid of this deduction calendar details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _deductionCalendarDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the adjustment type of this deduction calendar details.
    *
    * @return the adjustment type of this deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentType() {
        return _deductionCalendarDetails.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this deduction calendar details.
    *
    * @param adjustmentType the adjustment type of this deduction calendar details
    */
    @Override
    public void setAdjustmentType(java.lang.String adjustmentType) {
        _deductionCalendarDetails.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the check record of this deduction calendar details.
    *
    * @return the check record of this deduction calendar details
    */
    @Override
    public boolean getCheckRecord() {
        return _deductionCalendarDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this deduction calendar details is check record.
    *
    * @return <code>true</code> if this deduction calendar details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _deductionCalendarDetails.isCheckRecord();
    }

    /**
    * Sets whether this deduction calendar details is check record.
    *
    * @param checkRecord the check record of this deduction calendar details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _deductionCalendarDetails.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _deductionCalendarDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _deductionCalendarDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _deductionCalendarDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _deductionCalendarDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _deductionCalendarDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _deductionCalendarDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _deductionCalendarDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _deductionCalendarDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _deductionCalendarDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _deductionCalendarDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _deductionCalendarDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DeductionCalendarDetailsWrapper((DeductionCalendarDetails) _deductionCalendarDetails.clone());
    }

    @Override
    public int compareTo(DeductionCalendarDetails deductionCalendarDetails) {
        return _deductionCalendarDetails.compareTo(deductionCalendarDetails);
    }

    @Override
    public int hashCode() {
        return _deductionCalendarDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<DeductionCalendarDetails> toCacheModel() {
        return _deductionCalendarDetails.toCacheModel();
    }

    @Override
    public DeductionCalendarDetails toEscapedModel() {
        return new DeductionCalendarDetailsWrapper(_deductionCalendarDetails.toEscapedModel());
    }

    @Override
    public DeductionCalendarDetails toUnescapedModel() {
        return new DeductionCalendarDetailsWrapper(_deductionCalendarDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _deductionCalendarDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _deductionCalendarDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _deductionCalendarDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeductionCalendarDetailsWrapper)) {
            return false;
        }

        DeductionCalendarDetailsWrapper deductionCalendarDetailsWrapper = (DeductionCalendarDetailsWrapper) obj;

        if (Validator.equals(_deductionCalendarDetails,
                    deductionCalendarDetailsWrapper._deductionCalendarDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DeductionCalendarDetails getWrappedDeductionCalendarDetails() {
        return _deductionCalendarDetails;
    }

    @Override
    public DeductionCalendarDetails getWrappedModel() {
        return _deductionCalendarDetails;
    }

    @Override
    public void resetOriginalValues() {
        _deductionCalendarDetails.resetOriginalValues();
    }
}
