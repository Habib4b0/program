package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StDeductionCalendarDetails}.
 * </p>
 *
 * @author
 * @see StDeductionCalendarDetails
 * @generated
 */
public class StDeductionCalendarDetailsWrapper
    implements StDeductionCalendarDetails,
        ModelWrapper<StDeductionCalendarDetails> {
    private StDeductionCalendarDetails _stDeductionCalendarDetails;

    public StDeductionCalendarDetailsWrapper(
        StDeductionCalendarDetails stDeductionCalendarDetails) {
        _stDeductionCalendarDetails = stDeductionCalendarDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return StDeductionCalendarDetails.class;
    }

    @Override
    public String getModelClassName() {
        return StDeductionCalendarDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("adjustmentAllocationMethodology",
            getAdjustmentAllocationMethodology());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("discountAmount", getDiscountAmount());
        attributes.put("adjustmentVariable", getAdjustmentVariable());
        attributes.put("userId", getUserId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("sessionId", getSessionId());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
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

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this st deduction calendar details.
    *
    * @return the primary key of this st deduction calendar details
    */
    @Override
    public com.stpl.app.service.persistence.StDeductionCalendarDetailsPK getPrimaryKey() {
        return _stDeductionCalendarDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st deduction calendar details.
    *
    * @param primaryKey the primary key of this st deduction calendar details
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StDeductionCalendarDetailsPK primaryKey) {
        _stDeductionCalendarDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment basis of this st deduction calendar details.
    *
    * @return the adjustment basis of this st deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentBasis() {
        return _stDeductionCalendarDetails.getAdjustmentBasis();
    }

    /**
    * Sets the adjustment basis of this st deduction calendar details.
    *
    * @param adjustmentBasis the adjustment basis of this st deduction calendar details
    */
    @Override
    public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
        _stDeductionCalendarDetails.setAdjustmentBasis(adjustmentBasis);
    }

    /**
    * Returns the period sid of this st deduction calendar details.
    *
    * @return the period sid of this st deduction calendar details
    */
    @Override
    public int getPeriodSid() {
        return _stDeductionCalendarDetails.getPeriodSid();
    }

    /**
    * Sets the period sid of this st deduction calendar details.
    *
    * @param periodSid the period sid of this st deduction calendar details
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _stDeductionCalendarDetails.setPeriodSid(periodSid);
    }

    /**
    * Returns the adjustment value of this st deduction calendar details.
    *
    * @return the adjustment value of this st deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentValue() {
        return _stDeductionCalendarDetails.getAdjustmentValue();
    }

    /**
    * Sets the adjustment value of this st deduction calendar details.
    *
    * @param adjustmentValue the adjustment value of this st deduction calendar details
    */
    @Override
    public void setAdjustmentValue(java.lang.String adjustmentValue) {
        _stDeductionCalendarDetails.setAdjustmentValue(adjustmentValue);
    }

    /**
    * Returns the adjustment allocation methodology of this st deduction calendar details.
    *
    * @return the adjustment allocation methodology of this st deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentAllocationMethodology() {
        return _stDeductionCalendarDetails.getAdjustmentAllocationMethodology();
    }

    /**
    * Sets the adjustment allocation methodology of this st deduction calendar details.
    *
    * @param adjustmentAllocationMethodology the adjustment allocation methodology of this st deduction calendar details
    */
    @Override
    public void setAdjustmentAllocationMethodology(
        java.lang.String adjustmentAllocationMethodology) {
        _stDeductionCalendarDetails.setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
    }

    /**
    * Returns the company master sid of this st deduction calendar details.
    *
    * @return the company master sid of this st deduction calendar details
    */
    @Override
    public int getCompanyMasterSid() {
        return _stDeductionCalendarDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this st deduction calendar details.
    *
    * @param companyMasterSid the company master sid of this st deduction calendar details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _stDeductionCalendarDetails.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the discount amount of this st deduction calendar details.
    *
    * @return the discount amount of this st deduction calendar details
    */
    @Override
    public int getDiscountAmount() {
        return _stDeductionCalendarDetails.getDiscountAmount();
    }

    /**
    * Sets the discount amount of this st deduction calendar details.
    *
    * @param discountAmount the discount amount of this st deduction calendar details
    */
    @Override
    public void setDiscountAmount(int discountAmount) {
        _stDeductionCalendarDetails.setDiscountAmount(discountAmount);
    }

    /**
    * Returns the adjustment variable of this st deduction calendar details.
    *
    * @return the adjustment variable of this st deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentVariable() {
        return _stDeductionCalendarDetails.getAdjustmentVariable();
    }

    /**
    * Sets the adjustment variable of this st deduction calendar details.
    *
    * @param adjustmentVariable the adjustment variable of this st deduction calendar details
    */
    @Override
    public void setAdjustmentVariable(java.lang.String adjustmentVariable) {
        _stDeductionCalendarDetails.setAdjustmentVariable(adjustmentVariable);
    }

    /**
    * Returns the user ID of this st deduction calendar details.
    *
    * @return the user ID of this st deduction calendar details
    */
    @Override
    public int getUserId() {
        return _stDeductionCalendarDetails.getUserId();
    }

    /**
    * Sets the user ID of this st deduction calendar details.
    *
    * @param userId the user ID of this st deduction calendar details
    */
    @Override
    public void setUserId(int userId) {
        _stDeductionCalendarDetails.setUserId(userId);
    }

    /**
    * Returns the item master sid of this st deduction calendar details.
    *
    * @return the item master sid of this st deduction calendar details
    */
    @Override
    public int getItemMasterSid() {
        return _stDeductionCalendarDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this st deduction calendar details.
    *
    * @param itemMasterSid the item master sid of this st deduction calendar details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _stDeductionCalendarDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the adjustment type of this st deduction calendar details.
    *
    * @return the adjustment type of this st deduction calendar details
    */
    @Override
    public java.lang.String getAdjustmentType() {
        return _stDeductionCalendarDetails.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this st deduction calendar details.
    *
    * @param adjustmentType the adjustment type of this st deduction calendar details
    */
    @Override
    public void setAdjustmentType(java.lang.String adjustmentType) {
        _stDeductionCalendarDetails.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the session ID of this st deduction calendar details.
    *
    * @return the session ID of this st deduction calendar details
    */
    @Override
    public java.lang.String getSessionId() {
        return _stDeductionCalendarDetails.getSessionId();
    }

    /**
    * Sets the session ID of this st deduction calendar details.
    *
    * @param sessionId the session ID of this st deduction calendar details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _stDeductionCalendarDetails.setSessionId(sessionId);
    }

    /**
    * Returns the check record of this st deduction calendar details.
    *
    * @return the check record of this st deduction calendar details
    */
    @Override
    public boolean getCheckRecord() {
        return _stDeductionCalendarDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st deduction calendar details is check record.
    *
    * @return <code>true</code> if this st deduction calendar details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stDeductionCalendarDetails.isCheckRecord();
    }

    /**
    * Sets whether this st deduction calendar details is check record.
    *
    * @param checkRecord the check record of this st deduction calendar details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stDeductionCalendarDetails.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _stDeductionCalendarDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stDeductionCalendarDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stDeductionCalendarDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stDeductionCalendarDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stDeductionCalendarDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stDeductionCalendarDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stDeductionCalendarDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stDeductionCalendarDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stDeductionCalendarDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stDeductionCalendarDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stDeductionCalendarDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StDeductionCalendarDetailsWrapper((StDeductionCalendarDetails) _stDeductionCalendarDetails.clone());
    }

    @Override
    public int compareTo(StDeductionCalendarDetails stDeductionCalendarDetails) {
        return _stDeductionCalendarDetails.compareTo(stDeductionCalendarDetails);
    }

    @Override
    public int hashCode() {
        return _stDeductionCalendarDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StDeductionCalendarDetails> toCacheModel() {
        return _stDeductionCalendarDetails.toCacheModel();
    }

    @Override
    public StDeductionCalendarDetails toEscapedModel() {
        return new StDeductionCalendarDetailsWrapper(_stDeductionCalendarDetails.toEscapedModel());
    }

    @Override
    public StDeductionCalendarDetails toUnescapedModel() {
        return new StDeductionCalendarDetailsWrapper(_stDeductionCalendarDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stDeductionCalendarDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stDeductionCalendarDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stDeductionCalendarDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StDeductionCalendarDetailsWrapper)) {
            return false;
        }

        StDeductionCalendarDetailsWrapper stDeductionCalendarDetailsWrapper = (StDeductionCalendarDetailsWrapper) obj;

        if (Validator.equals(_stDeductionCalendarDetails,
                    stDeductionCalendarDetailsWrapper._stDeductionCalendarDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StDeductionCalendarDetails getWrappedStDeductionCalendarDetails() {
        return _stDeductionCalendarDetails;
    }

    @Override
    public StDeductionCalendarDetails getWrappedModel() {
        return _stDeductionCalendarDetails;
    }

    @Override
    public void resetOriginalValues() {
        _stDeductionCalendarDetails.resetOriginalValues();
    }
}
