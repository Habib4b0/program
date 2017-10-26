package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StNmDiscountProjection}.
 * </p>
 *
 * @author
 * @see StNmDiscountProjection
 * @generated
 */
public class StNmDiscountProjectionWrapper implements StNmDiscountProjection,
    ModelWrapper<StNmDiscountProjection> {
    private StNmDiscountProjection _stNmDiscountProjection;

    public StNmDiscountProjectionWrapper(
        StNmDiscountProjection stNmDiscountProjection) {
        _stNmDiscountProjection = stNmDiscountProjection;
    }

    @Override
    public Class<?> getModelClass() {
        return StNmDiscountProjection.class;
    }

    @Override
    public String getModelClassName() {
        return StNmDiscountProjection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionRate", getProjectionRate());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("discountRate", getDiscountRate());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("adjustmentMethodology", getAdjustmentMethodology());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
        }

        Double adjustmentValue = (Double) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Double discountRate = (Double) attributes.get("discountRate");

        if (discountRate != null) {
            setDiscountRate(discountRate);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String adjustmentMethodology = (String) attributes.get(
                "adjustmentMethodology");

        if (adjustmentMethodology != null) {
            setAdjustmentMethodology(adjustmentMethodology);
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

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    /**
    * Returns the primary key of this st nm discount projection.
    *
    * @return the primary key of this st nm discount projection
    */
    @Override
    public com.stpl.app.service.persistence.StNmDiscountProjectionPK getPrimaryKey() {
        return _stNmDiscountProjection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st nm discount projection.
    *
    * @param primaryKey the primary key of this st nm discount projection
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StNmDiscountProjectionPK primaryKey) {
        _stNmDiscountProjection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the projection rate of this st nm discount projection.
    *
    * @return the projection rate of this st nm discount projection
    */
    @Override
    public double getProjectionRate() {
        return _stNmDiscountProjection.getProjectionRate();
    }

    /**
    * Sets the projection rate of this st nm discount projection.
    *
    * @param projectionRate the projection rate of this st nm discount projection
    */
    @Override
    public void setProjectionRate(double projectionRate) {
        _stNmDiscountProjection.setProjectionRate(projectionRate);
    }

    /**
    * Returns the adjustment value of this st nm discount projection.
    *
    * @return the adjustment value of this st nm discount projection
    */
    @Override
    public double getAdjustmentValue() {
        return _stNmDiscountProjection.getAdjustmentValue();
    }

    /**
    * Sets the adjustment value of this st nm discount projection.
    *
    * @param adjustmentValue the adjustment value of this st nm discount projection
    */
    @Override
    public void setAdjustmentValue(double adjustmentValue) {
        _stNmDiscountProjection.setAdjustmentValue(adjustmentValue);
    }

    /**
    * Returns the user ID of this st nm discount projection.
    *
    * @return the user ID of this st nm discount projection
    */
    @Override
    public int getUserId() {
        return _stNmDiscountProjection.getUserId();
    }

    /**
    * Sets the user ID of this st nm discount projection.
    *
    * @param userId the user ID of this st nm discount projection
    */
    @Override
    public void setUserId(int userId) {
        _stNmDiscountProjection.setUserId(userId);
    }

    /**
    * Returns the last modified date of this st nm discount projection.
    *
    * @return the last modified date of this st nm discount projection
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stNmDiscountProjection.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st nm discount projection.
    *
    * @param lastModifiedDate the last modified date of this st nm discount projection
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stNmDiscountProjection.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the discount rate of this st nm discount projection.
    *
    * @return the discount rate of this st nm discount projection
    */
    @Override
    public double getDiscountRate() {
        return _stNmDiscountProjection.getDiscountRate();
    }

    /**
    * Sets the discount rate of this st nm discount projection.
    *
    * @param discountRate the discount rate of this st nm discount projection
    */
    @Override
    public void setDiscountRate(double discountRate) {
        _stNmDiscountProjection.setDiscountRate(discountRate);
    }

    /**
    * Returns the projection sales of this st nm discount projection.
    *
    * @return the projection sales of this st nm discount projection
    */
    @Override
    public double getProjectionSales() {
        return _stNmDiscountProjection.getProjectionSales();
    }

    /**
    * Sets the projection sales of this st nm discount projection.
    *
    * @param projectionSales the projection sales of this st nm discount projection
    */
    @Override
    public void setProjectionSales(double projectionSales) {
        _stNmDiscountProjection.setProjectionSales(projectionSales);
    }

    /**
    * Returns the adjustment type of this st nm discount projection.
    *
    * @return the adjustment type of this st nm discount projection
    */
    @Override
    public java.lang.String getAdjustmentType() {
        return _stNmDiscountProjection.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this st nm discount projection.
    *
    * @param adjustmentType the adjustment type of this st nm discount projection
    */
    @Override
    public void setAdjustmentType(java.lang.String adjustmentType) {
        _stNmDiscountProjection.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the adjustment basis of this st nm discount projection.
    *
    * @return the adjustment basis of this st nm discount projection
    */
    @Override
    public java.lang.String getAdjustmentBasis() {
        return _stNmDiscountProjection.getAdjustmentBasis();
    }

    /**
    * Sets the adjustment basis of this st nm discount projection.
    *
    * @param adjustmentBasis the adjustment basis of this st nm discount projection
    */
    @Override
    public void setAdjustmentBasis(java.lang.String adjustmentBasis) {
        _stNmDiscountProjection.setAdjustmentBasis(adjustmentBasis);
    }

    /**
    * Returns the period sid of this st nm discount projection.
    *
    * @return the period sid of this st nm discount projection
    */
    @Override
    public int getPeriodSid() {
        return _stNmDiscountProjection.getPeriodSid();
    }

    /**
    * Sets the period sid of this st nm discount projection.
    *
    * @param periodSid the period sid of this st nm discount projection
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _stNmDiscountProjection.setPeriodSid(periodSid);
    }

    /**
    * Returns the adjustment methodology of this st nm discount projection.
    *
    * @return the adjustment methodology of this st nm discount projection
    */
    @Override
    public java.lang.String getAdjustmentMethodology() {
        return _stNmDiscountProjection.getAdjustmentMethodology();
    }

    /**
    * Sets the adjustment methodology of this st nm discount projection.
    *
    * @param adjustmentMethodology the adjustment methodology of this st nm discount projection
    */
    @Override
    public void setAdjustmentMethodology(java.lang.String adjustmentMethodology) {
        _stNmDiscountProjection.setAdjustmentMethodology(adjustmentMethodology);
    }

    /**
    * Returns the projection details sid of this st nm discount projection.
    *
    * @return the projection details sid of this st nm discount projection
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stNmDiscountProjection.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st nm discount projection.
    *
    * @param projectionDetailsSid the projection details sid of this st nm discount projection
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stNmDiscountProjection.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the rs model sid of this st nm discount projection.
    *
    * @return the rs model sid of this st nm discount projection
    */
    @Override
    public int getRsModelSid() {
        return _stNmDiscountProjection.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this st nm discount projection.
    *
    * @param rsModelSid the rs model sid of this st nm discount projection
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _stNmDiscountProjection.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the session ID of this st nm discount projection.
    *
    * @return the session ID of this st nm discount projection
    */
    @Override
    public int getSessionId() {
        return _stNmDiscountProjection.getSessionId();
    }

    /**
    * Sets the session ID of this st nm discount projection.
    *
    * @param sessionId the session ID of this st nm discount projection
    */
    @Override
    public void setSessionId(int sessionId) {
        _stNmDiscountProjection.setSessionId(sessionId);
    }

    @Override
    public boolean isNew() {
        return _stNmDiscountProjection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stNmDiscountProjection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stNmDiscountProjection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stNmDiscountProjection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stNmDiscountProjection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stNmDiscountProjection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stNmDiscountProjection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stNmDiscountProjection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stNmDiscountProjection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stNmDiscountProjection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stNmDiscountProjection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StNmDiscountProjectionWrapper((StNmDiscountProjection) _stNmDiscountProjection.clone());
    }

    @Override
    public int compareTo(StNmDiscountProjection stNmDiscountProjection) {
        return _stNmDiscountProjection.compareTo(stNmDiscountProjection);
    }

    @Override
    public int hashCode() {
        return _stNmDiscountProjection.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StNmDiscountProjection> toCacheModel() {
        return _stNmDiscountProjection.toCacheModel();
    }

    @Override
    public StNmDiscountProjection toEscapedModel() {
        return new StNmDiscountProjectionWrapper(_stNmDiscountProjection.toEscapedModel());
    }

    @Override
    public StNmDiscountProjection toUnescapedModel() {
        return new StNmDiscountProjectionWrapper(_stNmDiscountProjection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stNmDiscountProjection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stNmDiscountProjection.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stNmDiscountProjection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmDiscountProjectionWrapper)) {
            return false;
        }

        StNmDiscountProjectionWrapper stNmDiscountProjectionWrapper = (StNmDiscountProjectionWrapper) obj;

        if (Validator.equals(_stNmDiscountProjection,
                    stNmDiscountProjectionWrapper._stNmDiscountProjection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StNmDiscountProjection getWrappedStNmDiscountProjection() {
        return _stNmDiscountProjection;
    }

    @Override
    public StNmDiscountProjection getWrappedModel() {
        return _stNmDiscountProjection;
    }

    @Override
    public void resetOriginalValues() {
        _stNmDiscountProjection.resetOriginalValues();
    }
}
