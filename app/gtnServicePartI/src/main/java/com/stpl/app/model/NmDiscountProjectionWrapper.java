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

        attributes.put("periodSid", getPeriodSid());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("projectionSales", getProjectionSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
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

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
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
