package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChProjectionDiscount}.
 * </p>
 *
 * @author
 * @see ChProjectionDiscount
 * @generated
 */
public class ChProjectionDiscountWrapper implements ChProjectionDiscount,
    ModelWrapper<ChProjectionDiscount> {
    private ChProjectionDiscount _chProjectionDiscount;

    public ChProjectionDiscountWrapper(
        ChProjectionDiscount chProjectionDiscount) {
        _chProjectionDiscount = chProjectionDiscount;
    }

    @Override
    public Class<?> getModelClass() {
        return ChProjectionDiscount.class;
    }

    @Override
    public String getModelClassName() {
        return ChProjectionDiscount.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentMethodology", getAdjustmentMethodology());
        attributes.put("productGrowth", getProductGrowth());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("accountGrowth", getAccountGrowth());
        attributes.put("discountAmount", getDiscountAmount());
        attributes.put("discountRate", getDiscountRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("rsModelSid", getRsModelSid());
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

        Double productGrowth = (Double) attributes.get("productGrowth");

        if (productGrowth != null) {
            setProductGrowth(productGrowth);
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

        Double accountGrowth = (Double) attributes.get("accountGrowth");

        if (accountGrowth != null) {
            setAccountGrowth(accountGrowth);
        }

        Double discountAmount = (Double) attributes.get("discountAmount");

        if (discountAmount != null) {
            setDiscountAmount(discountAmount);
        }

        Double discountRate = (Double) attributes.get("discountRate");

        if (discountRate != null) {
            setDiscountRate(discountRate);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Double adjustmentValue = (Double) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }
    }

    /**
    * Returns the primary key of this ch projection discount.
    *
    * @return the primary key of this ch projection discount
    */
    @Override
    public com.stpl.app.service.persistence.ChProjectionDiscountPK getPrimaryKey() {
        return _chProjectionDiscount.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ch projection discount.
    *
    * @param primaryKey the primary key of this ch projection discount
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.ChProjectionDiscountPK primaryKey) {
        _chProjectionDiscount.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment methodology of this ch projection discount.
    *
    * @return the adjustment methodology of this ch projection discount
    */
    @Override
    public java.lang.String getAdjustmentMethodology() {
        return _chProjectionDiscount.getAdjustmentMethodology();
    }

    /**
    * Sets the adjustment methodology of this ch projection discount.
    *
    * @param adjustmentMethodology the adjustment methodology of this ch projection discount
    */
    @Override
    public void setAdjustmentMethodology(java.lang.String adjustmentMethodology) {
        _chProjectionDiscount.setAdjustmentMethodology(adjustmentMethodology);
    }

    /**
    * Returns the product growth of this ch projection discount.
    *
    * @return the product growth of this ch projection discount
    */
    @Override
    public double getProductGrowth() {
        return _chProjectionDiscount.getProductGrowth();
    }

    /**
    * Sets the product growth of this ch projection discount.
    *
    * @param productGrowth the product growth of this ch projection discount
    */
    @Override
    public void setProductGrowth(double productGrowth) {
        _chProjectionDiscount.setProductGrowth(productGrowth);
    }

    /**
    * Returns the projection rate of this ch projection discount.
    *
    * @return the projection rate of this ch projection discount
    */
    @Override
    public double getProjectionRate() {
        return _chProjectionDiscount.getProjectionRate();
    }

    /**
    * Sets the projection rate of this ch projection discount.
    *
    * @param projectionRate the projection rate of this ch projection discount
    */
    @Override
    public void setProjectionRate(double projectionRate) {
        _chProjectionDiscount.setProjectionRate(projectionRate);
    }

    /**
    * Returns the projection details sid of this ch projection discount.
    *
    * @return the projection details sid of this ch projection discount
    */
    @Override
    public int getProjectionDetailsSid() {
        return _chProjectionDiscount.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this ch projection discount.
    *
    * @param projectionDetailsSid the projection details sid of this ch projection discount
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _chProjectionDiscount.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the account growth of this ch projection discount.
    *
    * @return the account growth of this ch projection discount
    */
    @Override
    public double getAccountGrowth() {
        return _chProjectionDiscount.getAccountGrowth();
    }

    /**
    * Sets the account growth of this ch projection discount.
    *
    * @param accountGrowth the account growth of this ch projection discount
    */
    @Override
    public void setAccountGrowth(double accountGrowth) {
        _chProjectionDiscount.setAccountGrowth(accountGrowth);
    }

    /**
    * Returns the discount amount of this ch projection discount.
    *
    * @return the discount amount of this ch projection discount
    */
    @Override
    public double getDiscountAmount() {
        return _chProjectionDiscount.getDiscountAmount();
    }

    /**
    * Sets the discount amount of this ch projection discount.
    *
    * @param discountAmount the discount amount of this ch projection discount
    */
    @Override
    public void setDiscountAmount(double discountAmount) {
        _chProjectionDiscount.setDiscountAmount(discountAmount);
    }

    /**
    * Returns the discount rate of this ch projection discount.
    *
    * @return the discount rate of this ch projection discount
    */
    @Override
    public double getDiscountRate() {
        return _chProjectionDiscount.getDiscountRate();
    }

    /**
    * Sets the discount rate of this ch projection discount.
    *
    * @param discountRate the discount rate of this ch projection discount
    */
    @Override
    public void setDiscountRate(double discountRate) {
        _chProjectionDiscount.setDiscountRate(discountRate);
    }

    /**
    * Returns the period sid of this ch projection discount.
    *
    * @return the period sid of this ch projection discount
    */
    @Override
    public int getPeriodSid() {
        return _chProjectionDiscount.getPeriodSid();
    }

    /**
    * Sets the period sid of this ch projection discount.
    *
    * @param periodSid the period sid of this ch projection discount
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _chProjectionDiscount.setPeriodSid(periodSid);
    }

    /**
    * Returns the adjustment basis of this ch projection discount.
    *
    * @return the adjustment basis of this ch projection discount
    */
    @Override
    public java.lang.String getAdjustmentBasis() {
        return _chProjectionDiscount.getAdjustmentBasis();
    }

    /**
    * Sets the adjustment basis of this ch projection discount.
    *
    * @param adjustmentBasis the adjustment basis of this ch projection discount
    */
    @Override
    public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
        _chProjectionDiscount.setAdjustmentBasis(adjustmentBasis);
    }

    /**
    * Returns the adjustment value of this ch projection discount.
    *
    * @return the adjustment value of this ch projection discount
    */
    @Override
    public double getAdjustmentValue() {
        return _chProjectionDiscount.getAdjustmentValue();
    }

    /**
    * Sets the adjustment value of this ch projection discount.
    *
    * @param adjustmentValue the adjustment value of this ch projection discount
    */
    @Override
    public void setAdjustmentValue(double adjustmentValue) {
        _chProjectionDiscount.setAdjustmentValue(adjustmentValue);
    }

    /**
    * Returns the adjustment type of this ch projection discount.
    *
    * @return the adjustment type of this ch projection discount
    */
    @Override
    public java.lang.String getAdjustmentType() {
        return _chProjectionDiscount.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this ch projection discount.
    *
    * @param adjustmentType the adjustment type of this ch projection discount
    */
    @Override
    public void setAdjustmentType(java.lang.String adjustmentType) {
        _chProjectionDiscount.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the rs model sid of this ch projection discount.
    *
    * @return the rs model sid of this ch projection discount
    */
    @Override
    public int getRsModelSid() {
        return _chProjectionDiscount.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this ch projection discount.
    *
    * @param rsModelSid the rs model sid of this ch projection discount
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _chProjectionDiscount.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the projection sales of this ch projection discount.
    *
    * @return the projection sales of this ch projection discount
    */
    @Override
    public double getProjectionSales() {
        return _chProjectionDiscount.getProjectionSales();
    }

    /**
    * Sets the projection sales of this ch projection discount.
    *
    * @param projectionSales the projection sales of this ch projection discount
    */
    @Override
    public void setProjectionSales(double projectionSales) {
        _chProjectionDiscount.setProjectionSales(projectionSales);
    }

    @Override
    public boolean isNew() {
        return _chProjectionDiscount.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _chProjectionDiscount.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _chProjectionDiscount.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _chProjectionDiscount.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _chProjectionDiscount.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _chProjectionDiscount.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _chProjectionDiscount.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _chProjectionDiscount.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _chProjectionDiscount.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _chProjectionDiscount.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _chProjectionDiscount.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ChProjectionDiscountWrapper((ChProjectionDiscount) _chProjectionDiscount.clone());
    }

    @Override
    public int compareTo(ChProjectionDiscount chProjectionDiscount) {
        return _chProjectionDiscount.compareTo(chProjectionDiscount);
    }

    @Override
    public int hashCode() {
        return _chProjectionDiscount.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ChProjectionDiscount> toCacheModel() {
        return _chProjectionDiscount.toCacheModel();
    }

    @Override
    public ChProjectionDiscount toEscapedModel() {
        return new ChProjectionDiscountWrapper(_chProjectionDiscount.toEscapedModel());
    }

    @Override
    public ChProjectionDiscount toUnescapedModel() {
        return new ChProjectionDiscountWrapper(_chProjectionDiscount.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _chProjectionDiscount.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _chProjectionDiscount.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _chProjectionDiscount.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChProjectionDiscountWrapper)) {
            return false;
        }

        ChProjectionDiscountWrapper chProjectionDiscountWrapper = (ChProjectionDiscountWrapper) obj;

        if (Validator.equals(_chProjectionDiscount,
                    chProjectionDiscountWrapper._chProjectionDiscount)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ChProjectionDiscount getWrappedChProjectionDiscount() {
        return _chProjectionDiscount;
    }

    @Override
    public ChProjectionDiscount getWrappedModel() {
        return _chProjectionDiscount;
    }

    @Override
    public void resetOriginalValues() {
        _chProjectionDiscount.resetOriginalValues();
    }
}
