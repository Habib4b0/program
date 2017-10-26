package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AcBrMethodologyDetails}.
 * </p>
 *
 * @author
 * @see AcBrMethodologyDetails
 * @generated
 */
public class AcBrMethodologyDetailsWrapper implements AcBrMethodologyDetails,
    ModelWrapper<AcBrMethodologyDetails> {
    private AcBrMethodologyDetails _acBrMethodologyDetails;

    public AcBrMethodologyDetailsWrapper(
        AcBrMethodologyDetails acBrMethodologyDetails) {
        _acBrMethodologyDetails = acBrMethodologyDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return AcBrMethodologyDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AcBrMethodologyDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("salesGrowthRate", getSalesGrowthRate());
        attributes.put("methodologyStartDate", getMethodologyStartDate());
        attributes.put("frequency", getFrequency());
        attributes.put("calculationFlag", getCalculationFlag());
        attributes.put("provisionGrowthRate", getProvisionGrowthRate());
        attributes.put("salesBasis", getSalesBasis());
        attributes.put("acBrMethodologyDetailsSid",
            getAcBrMethodologyDetailsSid());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("methodologyEndDate", getMethodologyEndDate());
        attributes.put("methodologyValue", getMethodologyValue());
        attributes.put("dampingFactor", getDampingFactor());
        attributes.put("methodologyName", getMethodologyName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double salesGrowthRate = (Double) attributes.get("salesGrowthRate");

        if (salesGrowthRate != null) {
            setSalesGrowthRate(salesGrowthRate);
        }

        Date methodologyStartDate = (Date) attributes.get(
                "methodologyStartDate");

        if (methodologyStartDate != null) {
            setMethodologyStartDate(methodologyStartDate);
        }

        String frequency = (String) attributes.get("frequency");

        if (frequency != null) {
            setFrequency(frequency);
        }

        Boolean calculationFlag = (Boolean) attributes.get("calculationFlag");

        if (calculationFlag != null) {
            setCalculationFlag(calculationFlag);
        }

        Double provisionGrowthRate = (Double) attributes.get(
                "provisionGrowthRate");

        if (provisionGrowthRate != null) {
            setProvisionGrowthRate(provisionGrowthRate);
        }

        Integer salesBasis = (Integer) attributes.get("salesBasis");

        if (salesBasis != null) {
            setSalesBasis(salesBasis);
        }

        Integer acBrMethodologyDetailsSid = (Integer) attributes.get(
                "acBrMethodologyDetailsSid");

        if (acBrMethodologyDetailsSid != null) {
            setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Date methodologyEndDate = (Date) attributes.get("methodologyEndDate");

        if (methodologyEndDate != null) {
            setMethodologyEndDate(methodologyEndDate);
        }

        Double methodologyValue = (Double) attributes.get("methodologyValue");

        if (methodologyValue != null) {
            setMethodologyValue(methodologyValue);
        }

        Double dampingFactor = (Double) attributes.get("dampingFactor");

        if (dampingFactor != null) {
            setDampingFactor(dampingFactor);
        }

        String methodologyName = (String) attributes.get("methodologyName");

        if (methodologyName != null) {
            setMethodologyName(methodologyName);
        }
    }

    /**
    * Returns the primary key of this ac br methodology details.
    *
    * @return the primary key of this ac br methodology details
    */
    @Override
    public int getPrimaryKey() {
        return _acBrMethodologyDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ac br methodology details.
    *
    * @param primaryKey the primary key of this ac br methodology details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _acBrMethodologyDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the sales growth rate of this ac br methodology details.
    *
    * @return the sales growth rate of this ac br methodology details
    */
    @Override
    public double getSalesGrowthRate() {
        return _acBrMethodologyDetails.getSalesGrowthRate();
    }

    /**
    * Sets the sales growth rate of this ac br methodology details.
    *
    * @param salesGrowthRate the sales growth rate of this ac br methodology details
    */
    @Override
    public void setSalesGrowthRate(double salesGrowthRate) {
        _acBrMethodologyDetails.setSalesGrowthRate(salesGrowthRate);
    }

    /**
    * Returns the methodology start date of this ac br methodology details.
    *
    * @return the methodology start date of this ac br methodology details
    */
    @Override
    public java.util.Date getMethodologyStartDate() {
        return _acBrMethodologyDetails.getMethodologyStartDate();
    }

    /**
    * Sets the methodology start date of this ac br methodology details.
    *
    * @param methodologyStartDate the methodology start date of this ac br methodology details
    */
    @Override
    public void setMethodologyStartDate(java.util.Date methodologyStartDate) {
        _acBrMethodologyDetails.setMethodologyStartDate(methodologyStartDate);
    }

    /**
    * Returns the frequency of this ac br methodology details.
    *
    * @return the frequency of this ac br methodology details
    */
    @Override
    public java.lang.String getFrequency() {
        return _acBrMethodologyDetails.getFrequency();
    }

    /**
    * Sets the frequency of this ac br methodology details.
    *
    * @param frequency the frequency of this ac br methodology details
    */
    @Override
    public void setFrequency(java.lang.String frequency) {
        _acBrMethodologyDetails.setFrequency(frequency);
    }

    /**
    * Returns the calculation flag of this ac br methodology details.
    *
    * @return the calculation flag of this ac br methodology details
    */
    @Override
    public boolean getCalculationFlag() {
        return _acBrMethodologyDetails.getCalculationFlag();
    }

    /**
    * Returns <code>true</code> if this ac br methodology details is calculation flag.
    *
    * @return <code>true</code> if this ac br methodology details is calculation flag; <code>false</code> otherwise
    */
    @Override
    public boolean isCalculationFlag() {
        return _acBrMethodologyDetails.isCalculationFlag();
    }

    /**
    * Sets whether this ac br methodology details is calculation flag.
    *
    * @param calculationFlag the calculation flag of this ac br methodology details
    */
    @Override
    public void setCalculationFlag(boolean calculationFlag) {
        _acBrMethodologyDetails.setCalculationFlag(calculationFlag);
    }

    /**
    * Returns the provision growth rate of this ac br methodology details.
    *
    * @return the provision growth rate of this ac br methodology details
    */
    @Override
    public double getProvisionGrowthRate() {
        return _acBrMethodologyDetails.getProvisionGrowthRate();
    }

    /**
    * Sets the provision growth rate of this ac br methodology details.
    *
    * @param provisionGrowthRate the provision growth rate of this ac br methodology details
    */
    @Override
    public void setProvisionGrowthRate(double provisionGrowthRate) {
        _acBrMethodologyDetails.setProvisionGrowthRate(provisionGrowthRate);
    }

    /**
    * Returns the sales basis of this ac br methodology details.
    *
    * @return the sales basis of this ac br methodology details
    */
    @Override
    public int getSalesBasis() {
        return _acBrMethodologyDetails.getSalesBasis();
    }

    /**
    * Sets the sales basis of this ac br methodology details.
    *
    * @param salesBasis the sales basis of this ac br methodology details
    */
    @Override
    public void setSalesBasis(int salesBasis) {
        _acBrMethodologyDetails.setSalesBasis(salesBasis);
    }

    /**
    * Returns the ac br methodology details sid of this ac br methodology details.
    *
    * @return the ac br methodology details sid of this ac br methodology details
    */
    @Override
    public int getAcBrMethodologyDetailsSid() {
        return _acBrMethodologyDetails.getAcBrMethodologyDetailsSid();
    }

    /**
    * Sets the ac br methodology details sid of this ac br methodology details.
    *
    * @param acBrMethodologyDetailsSid the ac br methodology details sid of this ac br methodology details
    */
    @Override
    public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
        _acBrMethodologyDetails.setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
    }

    /**
    * Returns the acc closure master sid of this ac br methodology details.
    *
    * @return the acc closure master sid of this ac br methodology details
    */
    @Override
    public int getAccClosureMasterSid() {
        return _acBrMethodologyDetails.getAccClosureMasterSid();
    }

    /**
    * Sets the acc closure master sid of this ac br methodology details.
    *
    * @param accClosureMasterSid the acc closure master sid of this ac br methodology details
    */
    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _acBrMethodologyDetails.setAccClosureMasterSid(accClosureMasterSid);
    }

    /**
    * Returns the methodology end date of this ac br methodology details.
    *
    * @return the methodology end date of this ac br methodology details
    */
    @Override
    public java.util.Date getMethodologyEndDate() {
        return _acBrMethodologyDetails.getMethodologyEndDate();
    }

    /**
    * Sets the methodology end date of this ac br methodology details.
    *
    * @param methodologyEndDate the methodology end date of this ac br methodology details
    */
    @Override
    public void setMethodologyEndDate(java.util.Date methodologyEndDate) {
        _acBrMethodologyDetails.setMethodologyEndDate(methodologyEndDate);
    }

    /**
    * Returns the methodology value of this ac br methodology details.
    *
    * @return the methodology value of this ac br methodology details
    */
    @Override
    public double getMethodologyValue() {
        return _acBrMethodologyDetails.getMethodologyValue();
    }

    /**
    * Sets the methodology value of this ac br methodology details.
    *
    * @param methodologyValue the methodology value of this ac br methodology details
    */
    @Override
    public void setMethodologyValue(double methodologyValue) {
        _acBrMethodologyDetails.setMethodologyValue(methodologyValue);
    }

    /**
    * Returns the damping factor of this ac br methodology details.
    *
    * @return the damping factor of this ac br methodology details
    */
    @Override
    public double getDampingFactor() {
        return _acBrMethodologyDetails.getDampingFactor();
    }

    /**
    * Sets the damping factor of this ac br methodology details.
    *
    * @param dampingFactor the damping factor of this ac br methodology details
    */
    @Override
    public void setDampingFactor(double dampingFactor) {
        _acBrMethodologyDetails.setDampingFactor(dampingFactor);
    }

    /**
    * Returns the methodology name of this ac br methodology details.
    *
    * @return the methodology name of this ac br methodology details
    */
    @Override
    public java.lang.String getMethodologyName() {
        return _acBrMethodologyDetails.getMethodologyName();
    }

    /**
    * Sets the methodology name of this ac br methodology details.
    *
    * @param methodologyName the methodology name of this ac br methodology details
    */
    @Override
    public void setMethodologyName(java.lang.String methodologyName) {
        _acBrMethodologyDetails.setMethodologyName(methodologyName);
    }

    @Override
    public boolean isNew() {
        return _acBrMethodologyDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _acBrMethodologyDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _acBrMethodologyDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _acBrMethodologyDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _acBrMethodologyDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _acBrMethodologyDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _acBrMethodologyDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _acBrMethodologyDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _acBrMethodologyDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _acBrMethodologyDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _acBrMethodologyDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AcBrMethodologyDetailsWrapper((AcBrMethodologyDetails) _acBrMethodologyDetails.clone());
    }

    @Override
    public int compareTo(AcBrMethodologyDetails acBrMethodologyDetails) {
        return _acBrMethodologyDetails.compareTo(acBrMethodologyDetails);
    }

    @Override
    public int hashCode() {
        return _acBrMethodologyDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AcBrMethodologyDetails> toCacheModel() {
        return _acBrMethodologyDetails.toCacheModel();
    }

    @Override
    public AcBrMethodologyDetails toEscapedModel() {
        return new AcBrMethodologyDetailsWrapper(_acBrMethodologyDetails.toEscapedModel());
    }

    @Override
    public AcBrMethodologyDetails toUnescapedModel() {
        return new AcBrMethodologyDetailsWrapper(_acBrMethodologyDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _acBrMethodologyDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _acBrMethodologyDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _acBrMethodologyDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AcBrMethodologyDetailsWrapper)) {
            return false;
        }

        AcBrMethodologyDetailsWrapper acBrMethodologyDetailsWrapper = (AcBrMethodologyDetailsWrapper) obj;

        if (Validator.equals(_acBrMethodologyDetails,
                    acBrMethodologyDetailsWrapper._acBrMethodologyDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AcBrMethodologyDetails getWrappedAcBrMethodologyDetails() {
        return _acBrMethodologyDetails;
    }

    @Override
    public AcBrMethodologyDetails getWrappedModel() {
        return _acBrMethodologyDetails;
    }

    @Override
    public void resetOriginalValues() {
        _acBrMethodologyDetails.resetOriginalValues();
    }
}
