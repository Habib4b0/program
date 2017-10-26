package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChActualDiscount}.
 * </p>
 *
 * @author
 * @see ChActualDiscount
 * @generated
 */
public class ChActualDiscountWrapper implements ChActualDiscount,
    ModelWrapper<ChActualDiscount> {
    private ChActualDiscount _chActualDiscount;

    public ChActualDiscountWrapper(ChActualDiscount chActualDiscount) {
        _chActualDiscount = chActualDiscount;
    }

    @Override
    public Class<?> getModelClass() {
        return ChActualDiscount.class;
    }

    @Override
    public String getModelClassName() {
        return ChActualDiscount.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
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

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }
    }

    /**
    * Returns the primary key of this ch actual discount.
    *
    * @return the primary key of this ch actual discount
    */
    @Override
    public com.stpl.app.service.persistence.ChActualDiscountPK getPrimaryKey() {
        return _chActualDiscount.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ch actual discount.
    *
    * @param primaryKey the primary key of this ch actual discount
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.ChActualDiscountPK primaryKey) {
        _chActualDiscount.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the actual rate of this ch actual discount.
    *
    * @return the actual rate of this ch actual discount
    */
    @Override
    public double getActualRate() {
        return _chActualDiscount.getActualRate();
    }

    /**
    * Sets the actual rate of this ch actual discount.
    *
    * @param actualRate the actual rate of this ch actual discount
    */
    @Override
    public void setActualRate(double actualRate) {
        _chActualDiscount.setActualRate(actualRate);
    }

    /**
    * Returns the period sid of this ch actual discount.
    *
    * @return the period sid of this ch actual discount
    */
    @Override
    public int getPeriodSid() {
        return _chActualDiscount.getPeriodSid();
    }

    /**
    * Sets the period sid of this ch actual discount.
    *
    * @param periodSid the period sid of this ch actual discount
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _chActualDiscount.setPeriodSid(periodSid);
    }

    /**
    * Returns the projection details sid of this ch actual discount.
    *
    * @return the projection details sid of this ch actual discount
    */
    @Override
    public int getProjectionDetailsSid() {
        return _chActualDiscount.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this ch actual discount.
    *
    * @param projectionDetailsSid the projection details sid of this ch actual discount
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _chActualDiscount.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the rs model sid of this ch actual discount.
    *
    * @return the rs model sid of this ch actual discount
    */
    @Override
    public int getRsModelSid() {
        return _chActualDiscount.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this ch actual discount.
    *
    * @param rsModelSid the rs model sid of this ch actual discount
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _chActualDiscount.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the actual sales of this ch actual discount.
    *
    * @return the actual sales of this ch actual discount
    */
    @Override
    public double getActualSales() {
        return _chActualDiscount.getActualSales();
    }

    /**
    * Sets the actual sales of this ch actual discount.
    *
    * @param actualSales the actual sales of this ch actual discount
    */
    @Override
    public void setActualSales(double actualSales) {
        _chActualDiscount.setActualSales(actualSales);
    }

    @Override
    public boolean isNew() {
        return _chActualDiscount.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _chActualDiscount.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _chActualDiscount.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _chActualDiscount.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _chActualDiscount.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _chActualDiscount.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _chActualDiscount.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _chActualDiscount.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _chActualDiscount.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _chActualDiscount.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _chActualDiscount.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ChActualDiscountWrapper((ChActualDiscount) _chActualDiscount.clone());
    }

    @Override
    public int compareTo(ChActualDiscount chActualDiscount) {
        return _chActualDiscount.compareTo(chActualDiscount);
    }

    @Override
    public int hashCode() {
        return _chActualDiscount.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ChActualDiscount> toCacheModel() {
        return _chActualDiscount.toCacheModel();
    }

    @Override
    public ChActualDiscount toEscapedModel() {
        return new ChActualDiscountWrapper(_chActualDiscount.toEscapedModel());
    }

    @Override
    public ChActualDiscount toUnescapedModel() {
        return new ChActualDiscountWrapper(_chActualDiscount.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _chActualDiscount.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _chActualDiscount.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _chActualDiscount.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChActualDiscountWrapper)) {
            return false;
        }

        ChActualDiscountWrapper chActualDiscountWrapper = (ChActualDiscountWrapper) obj;

        if (Validator.equals(_chActualDiscount,
                    chActualDiscountWrapper._chActualDiscount)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ChActualDiscount getWrappedChActualDiscount() {
        return _chActualDiscount;
    }

    @Override
    public ChActualDiscount getWrappedModel() {
        return _chActualDiscount;
    }

    @Override
    public void resetOriginalValues() {
        _chActualDiscount.resetOriginalValues();
    }
}
