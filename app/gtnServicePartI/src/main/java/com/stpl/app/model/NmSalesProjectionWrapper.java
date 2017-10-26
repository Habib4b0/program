package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NmSalesProjection}.
 * </p>
 *
 * @author
 * @see NmSalesProjection
 * @generated
 */
public class NmSalesProjectionWrapper implements NmSalesProjection,
    ModelWrapper<NmSalesProjection> {
    private NmSalesProjection _nmSalesProjection;

    public NmSalesProjectionWrapper(NmSalesProjection nmSalesProjection) {
        _nmSalesProjection = nmSalesProjection;
    }

    @Override
    public Class<?> getModelClass() {
        return NmSalesProjection.class;
    }

    @Override
    public String getModelClassName() {
        return NmSalesProjection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentMethodology", getAdjustmentMethodology());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("productGrowth", getProductGrowth());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("adjustmentValues", getAdjustmentValues());
        attributes.put("adjustmentVariable", getAdjustmentVariable());
        attributes.put("accountGrowth", getAccountGrowth());
        attributes.put("projectionUnits", getProjectionUnits());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("projectionSales", getProjectionSales());

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

        Double productGrowth = (Double) attributes.get("productGrowth");

        if (productGrowth != null) {
            setProductGrowth(productGrowth);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double adjustmentValues = (Double) attributes.get("adjustmentValues");

        if (adjustmentValues != null) {
            setAdjustmentValues(adjustmentValues);
        }

        Boolean adjustmentVariable = (Boolean) attributes.get(
                "adjustmentVariable");

        if (adjustmentVariable != null) {
            setAdjustmentVariable(adjustmentVariable);
        }

        Double accountGrowth = (Double) attributes.get("accountGrowth");

        if (accountGrowth != null) {
            setAccountGrowth(accountGrowth);
        }

        Double projectionUnits = (Double) attributes.get("projectionUnits");

        if (projectionUnits != null) {
            setProjectionUnits(projectionUnits);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }
    }

    /**
    * Returns the primary key of this nm sales projection.
    *
    * @return the primary key of this nm sales projection
    */
    @Override
    public com.stpl.app.service.persistence.NmSalesProjectionPK getPrimaryKey() {
        return _nmSalesProjection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this nm sales projection.
    *
    * @param primaryKey the primary key of this nm sales projection
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.NmSalesProjectionPK primaryKey) {
        _nmSalesProjection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment methodology of this nm sales projection.
    *
    * @return the adjustment methodology of this nm sales projection
    */
    @Override
    public java.lang.String getAdjustmentMethodology() {
        return _nmSalesProjection.getAdjustmentMethodology();
    }

    /**
    * Sets the adjustment methodology of this nm sales projection.
    *
    * @param adjustmentMethodology the adjustment methodology of this nm sales projection
    */
    @Override
    public void setAdjustmentMethodology(java.lang.String adjustmentMethodology) {
        _nmSalesProjection.setAdjustmentMethodology(adjustmentMethodology);
    }

    /**
    * Returns the adjustment basis of this nm sales projection.
    *
    * @return the adjustment basis of this nm sales projection
    */
    @Override
    public java.lang.String getAdjustmentBasis() {
        return _nmSalesProjection.getAdjustmentBasis();
    }

    /**
    * Sets the adjustment basis of this nm sales projection.
    *
    * @param adjustmentBasis the adjustment basis of this nm sales projection
    */
    @Override
    public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
        _nmSalesProjection.setAdjustmentBasis(adjustmentBasis);
    }

    /**
    * Returns the period sid of this nm sales projection.
    *
    * @return the period sid of this nm sales projection
    */
    @Override
    public int getPeriodSid() {
        return _nmSalesProjection.getPeriodSid();
    }

    /**
    * Sets the period sid of this nm sales projection.
    *
    * @param periodSid the period sid of this nm sales projection
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _nmSalesProjection.setPeriodSid(periodSid);
    }

    /**
    * Returns the product growth of this nm sales projection.
    *
    * @return the product growth of this nm sales projection
    */
    @Override
    public double getProductGrowth() {
        return _nmSalesProjection.getProductGrowth();
    }

    /**
    * Sets the product growth of this nm sales projection.
    *
    * @param productGrowth the product growth of this nm sales projection
    */
    @Override
    public void setProductGrowth(double productGrowth) {
        _nmSalesProjection.setProductGrowth(productGrowth);
    }

    /**
    * Returns the projection details sid of this nm sales projection.
    *
    * @return the projection details sid of this nm sales projection
    */
    @Override
    public int getProjectionDetailsSid() {
        return _nmSalesProjection.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this nm sales projection.
    *
    * @param projectionDetailsSid the projection details sid of this nm sales projection
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _nmSalesProjection.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the adjustment values of this nm sales projection.
    *
    * @return the adjustment values of this nm sales projection
    */
    @Override
    public double getAdjustmentValues() {
        return _nmSalesProjection.getAdjustmentValues();
    }

    /**
    * Sets the adjustment values of this nm sales projection.
    *
    * @param adjustmentValues the adjustment values of this nm sales projection
    */
    @Override
    public void setAdjustmentValues(double adjustmentValues) {
        _nmSalesProjection.setAdjustmentValues(adjustmentValues);
    }

    /**
    * Returns the adjustment variable of this nm sales projection.
    *
    * @return the adjustment variable of this nm sales projection
    */
    @Override
    public boolean getAdjustmentVariable() {
        return _nmSalesProjection.getAdjustmentVariable();
    }

    /**
    * Returns <code>true</code> if this nm sales projection is adjustment variable.
    *
    * @return <code>true</code> if this nm sales projection is adjustment variable; <code>false</code> otherwise
    */
    @Override
    public boolean isAdjustmentVariable() {
        return _nmSalesProjection.isAdjustmentVariable();
    }

    /**
    * Sets whether this nm sales projection is adjustment variable.
    *
    * @param adjustmentVariable the adjustment variable of this nm sales projection
    */
    @Override
    public void setAdjustmentVariable(boolean adjustmentVariable) {
        _nmSalesProjection.setAdjustmentVariable(adjustmentVariable);
    }

    /**
    * Returns the account growth of this nm sales projection.
    *
    * @return the account growth of this nm sales projection
    */
    @Override
    public double getAccountGrowth() {
        return _nmSalesProjection.getAccountGrowth();
    }

    /**
    * Sets the account growth of this nm sales projection.
    *
    * @param accountGrowth the account growth of this nm sales projection
    */
    @Override
    public void setAccountGrowth(double accountGrowth) {
        _nmSalesProjection.setAccountGrowth(accountGrowth);
    }

    /**
    * Returns the projection units of this nm sales projection.
    *
    * @return the projection units of this nm sales projection
    */
    @Override
    public double getProjectionUnits() {
        return _nmSalesProjection.getProjectionUnits();
    }

    /**
    * Sets the projection units of this nm sales projection.
    *
    * @param projectionUnits the projection units of this nm sales projection
    */
    @Override
    public void setProjectionUnits(double projectionUnits) {
        _nmSalesProjection.setProjectionUnits(projectionUnits);
    }

    /**
    * Returns the adjustment type of this nm sales projection.
    *
    * @return the adjustment type of this nm sales projection
    */
    @Override
    public java.lang.String getAdjustmentType() {
        return _nmSalesProjection.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this nm sales projection.
    *
    * @param adjustmentType the adjustment type of this nm sales projection
    */
    @Override
    public void setAdjustmentType(java.lang.String adjustmentType) {
        _nmSalesProjection.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the projection sales of this nm sales projection.
    *
    * @return the projection sales of this nm sales projection
    */
    @Override
    public double getProjectionSales() {
        return _nmSalesProjection.getProjectionSales();
    }

    /**
    * Sets the projection sales of this nm sales projection.
    *
    * @param projectionSales the projection sales of this nm sales projection
    */
    @Override
    public void setProjectionSales(double projectionSales) {
        _nmSalesProjection.setProjectionSales(projectionSales);
    }

    @Override
    public boolean isNew() {
        return _nmSalesProjection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nmSalesProjection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nmSalesProjection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nmSalesProjection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nmSalesProjection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nmSalesProjection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nmSalesProjection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nmSalesProjection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nmSalesProjection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nmSalesProjection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nmSalesProjection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NmSalesProjectionWrapper((NmSalesProjection) _nmSalesProjection.clone());
    }

    @Override
    public int compareTo(NmSalesProjection nmSalesProjection) {
        return _nmSalesProjection.compareTo(nmSalesProjection);
    }

    @Override
    public int hashCode() {
        return _nmSalesProjection.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NmSalesProjection> toCacheModel() {
        return _nmSalesProjection.toCacheModel();
    }

    @Override
    public NmSalesProjection toEscapedModel() {
        return new NmSalesProjectionWrapper(_nmSalesProjection.toEscapedModel());
    }

    @Override
    public NmSalesProjection toUnescapedModel() {
        return new NmSalesProjectionWrapper(_nmSalesProjection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nmSalesProjection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nmSalesProjection.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nmSalesProjection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmSalesProjectionWrapper)) {
            return false;
        }

        NmSalesProjectionWrapper nmSalesProjectionWrapper = (NmSalesProjectionWrapper) obj;

        if (Validator.equals(_nmSalesProjection,
                    nmSalesProjectionWrapper._nmSalesProjection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NmSalesProjection getWrappedNmSalesProjection() {
        return _nmSalesProjection;
    }

    @Override
    public NmSalesProjection getWrappedModel() {
        return _nmSalesProjection;
    }

    @Override
    public void resetOriginalValues() {
        _nmSalesProjection.resetOriginalValues();
    }
}
