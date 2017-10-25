package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StNmActualDiscount}.
 * </p>
 *
 * @author
 * @see StNmActualDiscount
 * @generated
 */
public class StNmActualDiscountWrapper implements StNmActualDiscount,
    ModelWrapper<StNmActualDiscount> {
    private StNmActualDiscount _stNmActualDiscount;

    public StNmActualDiscountWrapper(StNmActualDiscount stNmActualDiscount) {
        _stNmActualDiscount = stNmActualDiscount;
    }

    @Override
    public Class<?> getModelClass() {
        return StNmActualDiscount.class;
    }

    @Override
    public String getModelClassName() {
        return StNmActualDiscount.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualSales", getActualSales());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualRate", getActualRate());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double actualRate = (Double) attributes.get("actualRate");

        if (actualRate != null) {
            setActualRate(actualRate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Double actualProjectionSales = (Double) attributes.get(
                "actualProjectionSales");

        if (actualProjectionSales != null) {
            setActualProjectionSales(actualProjectionSales);
        }

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
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
    * Returns the primary key of this st nm actual discount.
    *
    * @return the primary key of this st nm actual discount
    */
    @Override
    public com.stpl.app.service.persistence.StNmActualDiscountPK getPrimaryKey() {
        return _stNmActualDiscount.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st nm actual discount.
    *
    * @param primaryKey the primary key of this st nm actual discount
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StNmActualDiscountPK primaryKey) {
        _stNmActualDiscount.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the actual sales of this st nm actual discount.
    *
    * @return the actual sales of this st nm actual discount
    */
    @Override
    public double getActualSales() {
        return _stNmActualDiscount.getActualSales();
    }

    /**
    * Sets the actual sales of this st nm actual discount.
    *
    * @param actualSales the actual sales of this st nm actual discount
    */
    @Override
    public void setActualSales(double actualSales) {
        _stNmActualDiscount.setActualSales(actualSales);
    }

    /**
    * Returns the period sid of this st nm actual discount.
    *
    * @return the period sid of this st nm actual discount
    */
    @Override
    public int getPeriodSid() {
        return _stNmActualDiscount.getPeriodSid();
    }

    /**
    * Sets the period sid of this st nm actual discount.
    *
    * @param periodSid the period sid of this st nm actual discount
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _stNmActualDiscount.setPeriodSid(periodSid);
    }

    /**
    * Returns the actual rate of this st nm actual discount.
    *
    * @return the actual rate of this st nm actual discount
    */
    @Override
    public double getActualRate() {
        return _stNmActualDiscount.getActualRate();
    }

    /**
    * Sets the actual rate of this st nm actual discount.
    *
    * @param actualRate the actual rate of this st nm actual discount
    */
    @Override
    public void setActualRate(double actualRate) {
        _stNmActualDiscount.setActualRate(actualRate);
    }

    /**
    * Returns the user ID of this st nm actual discount.
    *
    * @return the user ID of this st nm actual discount
    */
    @Override
    public int getUserId() {
        return _stNmActualDiscount.getUserId();
    }

    /**
    * Sets the user ID of this st nm actual discount.
    *
    * @param userId the user ID of this st nm actual discount
    */
    @Override
    public void setUserId(int userId) {
        _stNmActualDiscount.setUserId(userId);
    }

    /**
    * Returns the last modified date of this st nm actual discount.
    *
    * @return the last modified date of this st nm actual discount
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stNmActualDiscount.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st nm actual discount.
    *
    * @param lastModifiedDate the last modified date of this st nm actual discount
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stNmActualDiscount.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the actual projection sales of this st nm actual discount.
    *
    * @return the actual projection sales of this st nm actual discount
    */
    @Override
    public double getActualProjectionSales() {
        return _stNmActualDiscount.getActualProjectionSales();
    }

    /**
    * Sets the actual projection sales of this st nm actual discount.
    *
    * @param actualProjectionSales the actual projection sales of this st nm actual discount
    */
    @Override
    public void setActualProjectionSales(double actualProjectionSales) {
        _stNmActualDiscount.setActualProjectionSales(actualProjectionSales);
    }

    /**
    * Returns the actual projection rate of this st nm actual discount.
    *
    * @return the actual projection rate of this st nm actual discount
    */
    @Override
    public double getActualProjectionRate() {
        return _stNmActualDiscount.getActualProjectionRate();
    }

    /**
    * Sets the actual projection rate of this st nm actual discount.
    *
    * @param actualProjectionRate the actual projection rate of this st nm actual discount
    */
    @Override
    public void setActualProjectionRate(double actualProjectionRate) {
        _stNmActualDiscount.setActualProjectionRate(actualProjectionRate);
    }

    /**
    * Returns the projection details sid of this st nm actual discount.
    *
    * @return the projection details sid of this st nm actual discount
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stNmActualDiscount.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st nm actual discount.
    *
    * @param projectionDetailsSid the projection details sid of this st nm actual discount
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stNmActualDiscount.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the rs model sid of this st nm actual discount.
    *
    * @return the rs model sid of this st nm actual discount
    */
    @Override
    public int getRsModelSid() {
        return _stNmActualDiscount.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this st nm actual discount.
    *
    * @param rsModelSid the rs model sid of this st nm actual discount
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _stNmActualDiscount.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the session ID of this st nm actual discount.
    *
    * @return the session ID of this st nm actual discount
    */
    @Override
    public int getSessionId() {
        return _stNmActualDiscount.getSessionId();
    }

    /**
    * Sets the session ID of this st nm actual discount.
    *
    * @param sessionId the session ID of this st nm actual discount
    */
    @Override
    public void setSessionId(int sessionId) {
        _stNmActualDiscount.setSessionId(sessionId);
    }

    @Override
    public boolean isNew() {
        return _stNmActualDiscount.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stNmActualDiscount.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stNmActualDiscount.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stNmActualDiscount.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stNmActualDiscount.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stNmActualDiscount.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stNmActualDiscount.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stNmActualDiscount.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stNmActualDiscount.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stNmActualDiscount.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stNmActualDiscount.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StNmActualDiscountWrapper((StNmActualDiscount) _stNmActualDiscount.clone());
    }

    @Override
    public int compareTo(StNmActualDiscount stNmActualDiscount) {
        return _stNmActualDiscount.compareTo(stNmActualDiscount);
    }

    @Override
    public int hashCode() {
        return _stNmActualDiscount.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StNmActualDiscount> toCacheModel() {
        return _stNmActualDiscount.toCacheModel();
    }

    @Override
    public StNmActualDiscount toEscapedModel() {
        return new StNmActualDiscountWrapper(_stNmActualDiscount.toEscapedModel());
    }

    @Override
    public StNmActualDiscount toUnescapedModel() {
        return new StNmActualDiscountWrapper(_stNmActualDiscount.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stNmActualDiscount.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stNmActualDiscount.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stNmActualDiscount.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmActualDiscountWrapper)) {
            return false;
        }

        StNmActualDiscountWrapper stNmActualDiscountWrapper = (StNmActualDiscountWrapper) obj;

        if (Validator.equals(_stNmActualDiscount,
                    stNmActualDiscountWrapper._stNmActualDiscount)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StNmActualDiscount getWrappedStNmActualDiscount() {
        return _stNmActualDiscount;
    }

    @Override
    public StNmActualDiscount getWrappedModel() {
        return _stNmActualDiscount;
    }

    @Override
    public void resetOriginalValues() {
        _stNmActualDiscount.resetOriginalValues();
    }
}
