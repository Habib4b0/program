package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NmDiscountProjection}.
 * </p>
 *
 * @author
 * @see NmDiscountProjection
 * @generated
 */
public class NmDiscountProjectionWrapper implements NmDiscountProjection,
    ModelWrapper<NmDiscountProjection> {
    private NmDiscountProjection _nmDiscountProjection;

    public NmDiscountProjectionWrapper(
        NmDiscountProjection nmDiscountProjection) {
        _nmDiscountProjection = nmDiscountProjection;
    }

    @Override
    public Class<?> getModelClass() {
        return NmDiscountProjection.class;
    }

    @Override
    public String getModelClassName() {
        return NmDiscountProjection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentMethodology", getAdjustmentMethodology());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("adjustmentVariable", getAdjustmentVariable());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("discountRate", getDiscountRate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String adjustmentMethodology = (String) attributes.get(
                "adjustmentMethodology");

        if (adjustmentMethodology != null) {
            setAdjustmentMethodology(adjustmentMethodology);
        }

        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Boolean adjustmentVariable = (Boolean) attributes.get(
                "adjustmentVariable");

        if (adjustmentVariable != null) {
            setAdjustmentVariable(adjustmentVariable);
        }

        Double adjustmentValue = (Double) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        Double discountRate = (Double) attributes.get("discountRate");

        if (discountRate != null) {
            setDiscountRate(discountRate);
        }
    }

    /**
    * Returns the primary key of this nm discount projection.
    *
    * @return the primary key of this nm discount projection
    */
    @Override
    public com.stpl.app.service.persistence.NmDiscountProjectionPK getPrimaryKey() {
        return _nmDiscountProjection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this nm discount projection.
    *
    * @param primaryKey the primary key of this nm discount projection
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.NmDiscountProjectionPK primaryKey) {
        _nmDiscountProjection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment methodology of this nm discount projection.
    *
    * @return the adjustment methodology of this nm discount projection
    */
    @Override
    public java.lang.String getAdjustmentMethodology() {
        return _nmDiscountProjection.getAdjustmentMethodology();
    }

    /**
    * Sets the adjustment methodology of this nm discount projection.
    *
    * @param adjustmentMethodology the adjustment methodology of this nm discount projection
    */
    @Override
    public void setAdjustmentMethodology(java.lang.String adjustmentMethodology) {
        _nmDiscountProjection.setAdjustmentMethodology(adjustmentMethodology);
    }

    /**
    * Returns the adjustment basis of this nm discount projection.
    *
    * @return the adjustment basis of this nm discount projection
    */
    @Override
    public java.lang.String getAdjustmentBasis() {
        return _nmDiscountProjection.getAdjustmentBasis();
    }

    /**
    * Sets the adjustment basis of this nm discount projection.
    *
    * @param adjustmentBasis the adjustment basis of this nm discount projection
    */
    @Override
    public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
        _nmDiscountProjection.setAdjustmentBasis(adjustmentBasis);
    }

    /**
    * Returns the period sid of this nm discount projection.
    *
    * @return the period sid of this nm discount projection
    */
    @Override
    public int getPeriodSid() {
        return _nmDiscountProjection.getPeriodSid();
    }

    /**
    * Sets the period sid of this nm discount projection.
    *
    * @param periodSid the period sid of this nm discount projection
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _nmDiscountProjection.setPeriodSid(periodSid);
    }

    /**
    * Returns the projection rate of this nm discount projection.
    *
    * @return the projection rate of this nm discount projection
    */
    @Override
    public double getProjectionRate() {
        return _nmDiscountProjection.getProjectionRate();
    }

    /**
    * Sets the projection rate of this nm discount projection.
    *
    * @param projectionRate the projection rate of this nm discount projection
    */
    @Override
    public void setProjectionRate(double projectionRate) {
        _nmDiscountProjection.setProjectionRate(projectionRate);
    }

    /**
    * Returns the projection details sid of this nm discount projection.
    *
    * @return the projection details sid of this nm discount projection
    */
    @Override
    public int getProjectionDetailsSid() {
        return _nmDiscountProjection.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this nm discount projection.
    *
    * @param projectionDetailsSid the projection details sid of this nm discount projection
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _nmDiscountProjection.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the adjustment variable of this nm discount projection.
    *
    * @return the adjustment variable of this nm discount projection
    */
    @Override
    public boolean getAdjustmentVariable() {
        return _nmDiscountProjection.getAdjustmentVariable();
    }

    /**
    * Returns <code>true</code> if this nm discount projection is adjustment variable.
    *
    * @return <code>true</code> if this nm discount projection is adjustment variable; <code>false</code> otherwise
    */
    @Override
    public boolean isAdjustmentVariable() {
        return _nmDiscountProjection.isAdjustmentVariable();
    }

    /**
    * Sets whether this nm discount projection is adjustment variable.
    *
    * @param adjustmentVariable the adjustment variable of this nm discount projection
    */
    @Override
    public void setAdjustmentVariable(boolean adjustmentVariable) {
        _nmDiscountProjection.setAdjustmentVariable(adjustmentVariable);
    }

    /**
    * Returns the adjustment value of this nm discount projection.
    *
    * @return the adjustment value of this nm discount projection
    */
    @Override
    public double getAdjustmentValue() {
        return _nmDiscountProjection.getAdjustmentValue();
    }

    /**
    * Sets the adjustment value of this nm discount projection.
    *
    * @param adjustmentValue the adjustment value of this nm discount projection
    */
    @Override
    public void setAdjustmentValue(double adjustmentValue) {
        _nmDiscountProjection.setAdjustmentValue(adjustmentValue);
    }

    /**
    * Returns the adjustment type of this nm discount projection.
    *
    * @return the adjustment type of this nm discount projection
    */
    @Override
    public java.lang.String getAdjustmentType() {
        return _nmDiscountProjection.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this nm discount projection.
    *
    * @param adjustmentType the adjustment type of this nm discount projection
    */
    @Override
    public void setAdjustmentType(java.lang.String adjustmentType) {
        _nmDiscountProjection.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the projection sales of this nm discount projection.
    *
    * @return the projection sales of this nm discount projection
    */
    @Override
    public double getProjectionSales() {
        return _nmDiscountProjection.getProjectionSales();
    }

    /**
    * Sets the projection sales of this nm discount projection.
    *
    * @param projectionSales the projection sales of this nm discount projection
    */
    @Override
    public void setProjectionSales(double projectionSales) {
        _nmDiscountProjection.setProjectionSales(projectionSales);
    }

    /**
    * Returns the discount rate of this nm discount projection.
    *
    * @return the discount rate of this nm discount projection
    */
    @Override
    public double getDiscountRate() {
        return _nmDiscountProjection.getDiscountRate();
    }

    /**
    * Sets the discount rate of this nm discount projection.
    *
    * @param discountRate the discount rate of this nm discount projection
    */
    @Override
    public void setDiscountRate(double discountRate) {
        _nmDiscountProjection.setDiscountRate(discountRate);
    }

    @Override
    public boolean isNew() {
        return _nmDiscountProjection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nmDiscountProjection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nmDiscountProjection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nmDiscountProjection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nmDiscountProjection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nmDiscountProjection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nmDiscountProjection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nmDiscountProjection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nmDiscountProjection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nmDiscountProjection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nmDiscountProjection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NmDiscountProjectionWrapper((NmDiscountProjection) _nmDiscountProjection.clone());
    }

    @Override
    public int compareTo(NmDiscountProjection nmDiscountProjection) {
        return _nmDiscountProjection.compareTo(nmDiscountProjection);
    }

    @Override
    public int hashCode() {
        return _nmDiscountProjection.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NmDiscountProjection> toCacheModel() {
        return _nmDiscountProjection.toCacheModel();
    }

    @Override
    public NmDiscountProjection toEscapedModel() {
        return new NmDiscountProjectionWrapper(_nmDiscountProjection.toEscapedModel());
    }

    @Override
    public NmDiscountProjection toUnescapedModel() {
        return new NmDiscountProjectionWrapper(_nmDiscountProjection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nmDiscountProjection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nmDiscountProjection.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nmDiscountProjection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmDiscountProjectionWrapper)) {
            return false;
        }

        NmDiscountProjectionWrapper nmDiscountProjectionWrapper = (NmDiscountProjectionWrapper) obj;

        if (Validator.equals(_nmDiscountProjection,
                    nmDiscountProjectionWrapper._nmDiscountProjection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NmDiscountProjection getWrappedNmDiscountProjection() {
        return _nmDiscountProjection;
    }

    @Override
    public NmDiscountProjection getWrappedModel() {
        return _nmDiscountProjection;
    }

    @Override
    public void resetOriginalValues() {
        _nmDiscountProjection.resetOriginalValues();
    }
}
