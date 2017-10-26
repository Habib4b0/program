package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AcBaseRateBaselineDetails}.
 * </p>
 *
 * @author
 * @see AcBaseRateBaselineDetails
 * @generated
 */
public class AcBaseRateBaselineDetailsWrapper
    implements AcBaseRateBaselineDetails,
        ModelWrapper<AcBaseRateBaselineDetails> {
    private AcBaseRateBaselineDetails _acBaseRateBaselineDetails;

    public AcBaseRateBaselineDetailsWrapper(
        AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
        _acBaseRateBaselineDetails = acBaseRateBaselineDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return AcBaseRateBaselineDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AcBaseRateBaselineDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodValue", getPeriodValue());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("paymentsPeriod", getPaymentsPeriod());
        attributes.put("acBrMethodologyDetailsSid",
            getAcBrMethodologyDetailsSid());
        attributes.put("salesPeriod", getSalesPeriod());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double periodValue = (Double) attributes.get("periodValue");

        if (periodValue != null) {
            setPeriodValue(periodValue);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Boolean paymentsPeriod = (Boolean) attributes.get("paymentsPeriod");

        if (paymentsPeriod != null) {
            setPaymentsPeriod(paymentsPeriod);
        }

        Integer acBrMethodologyDetailsSid = (Integer) attributes.get(
                "acBrMethodologyDetailsSid");

        if (acBrMethodologyDetailsSid != null) {
            setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
        }

        Boolean salesPeriod = (Boolean) attributes.get("salesPeriod");

        if (salesPeriod != null) {
            setSalesPeriod(salesPeriod);
        }
    }

    /**
    * Returns the primary key of this ac base rate baseline details.
    *
    * @return the primary key of this ac base rate baseline details
    */
    @Override
    public int getPrimaryKey() {
        return _acBaseRateBaselineDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ac base rate baseline details.
    *
    * @param primaryKey the primary key of this ac base rate baseline details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _acBaseRateBaselineDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the period value of this ac base rate baseline details.
    *
    * @return the period value of this ac base rate baseline details
    */
    @Override
    public double getPeriodValue() {
        return _acBaseRateBaselineDetails.getPeriodValue();
    }

    /**
    * Sets the period value of this ac base rate baseline details.
    *
    * @param periodValue the period value of this ac base rate baseline details
    */
    @Override
    public void setPeriodValue(double periodValue) {
        _acBaseRateBaselineDetails.setPeriodValue(periodValue);
    }

    /**
    * Returns the period sid of this ac base rate baseline details.
    *
    * @return the period sid of this ac base rate baseline details
    */
    @Override
    public int getPeriodSid() {
        return _acBaseRateBaselineDetails.getPeriodSid();
    }

    /**
    * Sets the period sid of this ac base rate baseline details.
    *
    * @param periodSid the period sid of this ac base rate baseline details
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _acBaseRateBaselineDetails.setPeriodSid(periodSid);
    }

    /**
    * Returns the payments period of this ac base rate baseline details.
    *
    * @return the payments period of this ac base rate baseline details
    */
    @Override
    public boolean getPaymentsPeriod() {
        return _acBaseRateBaselineDetails.getPaymentsPeriod();
    }

    /**
    * Returns <code>true</code> if this ac base rate baseline details is payments period.
    *
    * @return <code>true</code> if this ac base rate baseline details is payments period; <code>false</code> otherwise
    */
    @Override
    public boolean isPaymentsPeriod() {
        return _acBaseRateBaselineDetails.isPaymentsPeriod();
    }

    /**
    * Sets whether this ac base rate baseline details is payments period.
    *
    * @param paymentsPeriod the payments period of this ac base rate baseline details
    */
    @Override
    public void setPaymentsPeriod(boolean paymentsPeriod) {
        _acBaseRateBaselineDetails.setPaymentsPeriod(paymentsPeriod);
    }

    /**
    * Returns the ac br methodology details sid of this ac base rate baseline details.
    *
    * @return the ac br methodology details sid of this ac base rate baseline details
    */
    @Override
    public int getAcBrMethodologyDetailsSid() {
        return _acBaseRateBaselineDetails.getAcBrMethodologyDetailsSid();
    }

    /**
    * Sets the ac br methodology details sid of this ac base rate baseline details.
    *
    * @param acBrMethodologyDetailsSid the ac br methodology details sid of this ac base rate baseline details
    */
    @Override
    public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
        _acBaseRateBaselineDetails.setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
    }

    /**
    * Returns the sales period of this ac base rate baseline details.
    *
    * @return the sales period of this ac base rate baseline details
    */
    @Override
    public boolean getSalesPeriod() {
        return _acBaseRateBaselineDetails.getSalesPeriod();
    }

    /**
    * Returns <code>true</code> if this ac base rate baseline details is sales period.
    *
    * @return <code>true</code> if this ac base rate baseline details is sales period; <code>false</code> otherwise
    */
    @Override
    public boolean isSalesPeriod() {
        return _acBaseRateBaselineDetails.isSalesPeriod();
    }

    /**
    * Sets whether this ac base rate baseline details is sales period.
    *
    * @param salesPeriod the sales period of this ac base rate baseline details
    */
    @Override
    public void setSalesPeriod(boolean salesPeriod) {
        _acBaseRateBaselineDetails.setSalesPeriod(salesPeriod);
    }

    @Override
    public boolean isNew() {
        return _acBaseRateBaselineDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _acBaseRateBaselineDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _acBaseRateBaselineDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _acBaseRateBaselineDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _acBaseRateBaselineDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _acBaseRateBaselineDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _acBaseRateBaselineDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _acBaseRateBaselineDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _acBaseRateBaselineDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _acBaseRateBaselineDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _acBaseRateBaselineDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AcBaseRateBaselineDetailsWrapper((AcBaseRateBaselineDetails) _acBaseRateBaselineDetails.clone());
    }

    @Override
    public int compareTo(AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
        return _acBaseRateBaselineDetails.compareTo(acBaseRateBaselineDetails);
    }

    @Override
    public int hashCode() {
        return _acBaseRateBaselineDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AcBaseRateBaselineDetails> toCacheModel() {
        return _acBaseRateBaselineDetails.toCacheModel();
    }

    @Override
    public AcBaseRateBaselineDetails toEscapedModel() {
        return new AcBaseRateBaselineDetailsWrapper(_acBaseRateBaselineDetails.toEscapedModel());
    }

    @Override
    public AcBaseRateBaselineDetails toUnescapedModel() {
        return new AcBaseRateBaselineDetailsWrapper(_acBaseRateBaselineDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _acBaseRateBaselineDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _acBaseRateBaselineDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _acBaseRateBaselineDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AcBaseRateBaselineDetailsWrapper)) {
            return false;
        }

        AcBaseRateBaselineDetailsWrapper acBaseRateBaselineDetailsWrapper = (AcBaseRateBaselineDetailsWrapper) obj;

        if (Validator.equals(_acBaseRateBaselineDetails,
                    acBaseRateBaselineDetailsWrapper._acBaseRateBaselineDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AcBaseRateBaselineDetails getWrappedAcBaseRateBaselineDetails() {
        return _acBaseRateBaselineDetails;
    }

    @Override
    public AcBaseRateBaselineDetails getWrappedModel() {
        return _acBaseRateBaselineDetails;
    }

    @Override
    public void resetOriginalValues() {
        _acBaseRateBaselineDetails.resetOriginalValues();
    }
}
