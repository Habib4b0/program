package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NmActualDiscount}.
 * </p>
 *
 * @author
 * @see NmActualDiscount
 * @generated
 */
public class NmActualDiscountWrapper implements NmActualDiscount,
    ModelWrapper<NmActualDiscount> {
    private NmActualDiscount _nmActualDiscount;

    public NmActualDiscountWrapper(NmActualDiscount nmActualDiscount) {
        _nmActualDiscount = nmActualDiscount;
    }

    @Override
    public Class<?> getModelClass() {
        return NmActualDiscount.class;
    }

    @Override
    public String getModelClassName() {
        return NmActualDiscount.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("actualSales", getActualSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualRate = (Double) attributes.get("actualRate");

        if (actualRate != null) {
            setActualRate(actualRate);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double actualProjectionSales = (Double) attributes.get(
                "actualProjectionSales");

        if (actualProjectionSales != null) {
            setActualProjectionSales(actualProjectionSales);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
        }

        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }
    }

    /**
    * Returns the primary key of this nm actual discount.
    *
    * @return the primary key of this nm actual discount
    */
    @Override
    public com.stpl.app.service.persistence.NmActualDiscountPK getPrimaryKey() {
        return _nmActualDiscount.getPrimaryKey();
    }

    /**
    * Sets the primary key of this nm actual discount.
    *
    * @param primaryKey the primary key of this nm actual discount
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.NmActualDiscountPK primaryKey) {
        _nmActualDiscount.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the actual rate of this nm actual discount.
    *
    * @return the actual rate of this nm actual discount
    */
    @Override
    public double getActualRate() {
        return _nmActualDiscount.getActualRate();
    }

    /**
    * Sets the actual rate of this nm actual discount.
    *
    * @param actualRate the actual rate of this nm actual discount
    */
    @Override
    public void setActualRate(double actualRate) {
        _nmActualDiscount.setActualRate(actualRate);
    }

    /**
    * Returns the period sid of this nm actual discount.
    *
    * @return the period sid of this nm actual discount
    */
    @Override
    public int getPeriodSid() {
        return _nmActualDiscount.getPeriodSid();
    }

    /**
    * Sets the period sid of this nm actual discount.
    *
    * @param periodSid the period sid of this nm actual discount
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _nmActualDiscount.setPeriodSid(periodSid);
    }

    /**
    * Returns the actual projection sales of this nm actual discount.
    *
    * @return the actual projection sales of this nm actual discount
    */
    @Override
    public double getActualProjectionSales() {
        return _nmActualDiscount.getActualProjectionSales();
    }

    /**
    * Sets the actual projection sales of this nm actual discount.
    *
    * @param actualProjectionSales the actual projection sales of this nm actual discount
    */
    @Override
    public void setActualProjectionSales(double actualProjectionSales) {
        _nmActualDiscount.setActualProjectionSales(actualProjectionSales);
    }

    /**
    * Returns the projection details sid of this nm actual discount.
    *
    * @return the projection details sid of this nm actual discount
    */
    @Override
    public int getProjectionDetailsSid() {
        return _nmActualDiscount.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this nm actual discount.
    *
    * @param projectionDetailsSid the projection details sid of this nm actual discount
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _nmActualDiscount.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the actual projection rate of this nm actual discount.
    *
    * @return the actual projection rate of this nm actual discount
    */
    @Override
    public double getActualProjectionRate() {
        return _nmActualDiscount.getActualProjectionRate();
    }

    /**
    * Sets the actual projection rate of this nm actual discount.
    *
    * @param actualProjectionRate the actual projection rate of this nm actual discount
    */
    @Override
    public void setActualProjectionRate(double actualProjectionRate) {
        _nmActualDiscount.setActualProjectionRate(actualProjectionRate);
    }

    /**
    * Returns the actual sales of this nm actual discount.
    *
    * @return the actual sales of this nm actual discount
    */
    @Override
    public double getActualSales() {
        return _nmActualDiscount.getActualSales();
    }

    /**
    * Sets the actual sales of this nm actual discount.
    *
    * @param actualSales the actual sales of this nm actual discount
    */
    @Override
    public void setActualSales(double actualSales) {
        _nmActualDiscount.setActualSales(actualSales);
    }

    @Override
    public boolean isNew() {
        return _nmActualDiscount.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nmActualDiscount.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nmActualDiscount.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nmActualDiscount.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nmActualDiscount.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nmActualDiscount.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nmActualDiscount.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nmActualDiscount.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nmActualDiscount.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nmActualDiscount.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nmActualDiscount.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NmActualDiscountWrapper((NmActualDiscount) _nmActualDiscount.clone());
    }

    @Override
    public int compareTo(NmActualDiscount nmActualDiscount) {
        return _nmActualDiscount.compareTo(nmActualDiscount);
    }

    @Override
    public int hashCode() {
        return _nmActualDiscount.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NmActualDiscount> toCacheModel() {
        return _nmActualDiscount.toCacheModel();
    }

    @Override
    public NmActualDiscount toEscapedModel() {
        return new NmActualDiscountWrapper(_nmActualDiscount.toEscapedModel());
    }

    @Override
    public NmActualDiscount toUnescapedModel() {
        return new NmActualDiscountWrapper(_nmActualDiscount.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nmActualDiscount.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nmActualDiscount.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nmActualDiscount.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmActualDiscountWrapper)) {
            return false;
        }

        NmActualDiscountWrapper nmActualDiscountWrapper = (NmActualDiscountWrapper) obj;

        if (Validator.equals(_nmActualDiscount,
                    nmActualDiscountWrapper._nmActualDiscount)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NmActualDiscount getWrappedNmActualDiscount() {
        return _nmActualDiscount;
    }

    @Override
    public NmActualDiscount getWrappedModel() {
        return _nmActualDiscount;
    }

    @Override
    public void resetOriginalValues() {
        _nmActualDiscount.resetOriginalValues();
    }
}
