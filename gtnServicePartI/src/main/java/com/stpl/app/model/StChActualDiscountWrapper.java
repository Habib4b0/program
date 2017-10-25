package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StChActualDiscount}.
 * </p>
 *
 * @author
 * @see StChActualDiscount
 * @generated
 */
public class StChActualDiscountWrapper implements StChActualDiscount,
    ModelWrapper<StChActualDiscount> {
    private StChActualDiscount _stChActualDiscount;

    public StChActualDiscountWrapper(StChActualDiscount stChActualDiscount) {
        _stChActualDiscount = stChActualDiscount;
    }

    @Override
    public Class<?> getModelClass() {
        return StChActualDiscount.class;
    }

    @Override
    public String getModelClassName() {
        return StChActualDiscount.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("sessionId", getSessionId());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("actualSales", getActualSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

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

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
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
    * Returns the primary key of this st ch actual discount.
    *
    * @return the primary key of this st ch actual discount
    */
    @Override
    public com.stpl.app.service.persistence.StChActualDiscountPK getPrimaryKey() {
        return _stChActualDiscount.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st ch actual discount.
    *
    * @param primaryKey the primary key of this st ch actual discount
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StChActualDiscountPK primaryKey) {
        _stChActualDiscount.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the last modified date of this st ch actual discount.
    *
    * @return the last modified date of this st ch actual discount
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stChActualDiscount.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st ch actual discount.
    *
    * @param lastModifiedDate the last modified date of this st ch actual discount
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stChActualDiscount.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the actual rate of this st ch actual discount.
    *
    * @return the actual rate of this st ch actual discount
    */
    @Override
    public double getActualRate() {
        return _stChActualDiscount.getActualRate();
    }

    /**
    * Sets the actual rate of this st ch actual discount.
    *
    * @param actualRate the actual rate of this st ch actual discount
    */
    @Override
    public void setActualRate(double actualRate) {
        _stChActualDiscount.setActualRate(actualRate);
    }

    /**
    * Returns the period sid of this st ch actual discount.
    *
    * @return the period sid of this st ch actual discount
    */
    @Override
    public int getPeriodSid() {
        return _stChActualDiscount.getPeriodSid();
    }

    /**
    * Sets the period sid of this st ch actual discount.
    *
    * @param periodSid the period sid of this st ch actual discount
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _stChActualDiscount.setPeriodSid(periodSid);
    }

    /**
    * Returns the projection details sid of this st ch actual discount.
    *
    * @return the projection details sid of this st ch actual discount
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stChActualDiscount.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st ch actual discount.
    *
    * @param projectionDetailsSid the projection details sid of this st ch actual discount
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stChActualDiscount.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the user ID of this st ch actual discount.
    *
    * @return the user ID of this st ch actual discount
    */
    @Override
    public int getUserId() {
        return _stChActualDiscount.getUserId();
    }

    /**
    * Sets the user ID of this st ch actual discount.
    *
    * @param userId the user ID of this st ch actual discount
    */
    @Override
    public void setUserId(int userId) {
        _stChActualDiscount.setUserId(userId);
    }

    /**
    * Returns the session ID of this st ch actual discount.
    *
    * @return the session ID of this st ch actual discount
    */
    @Override
    public int getSessionId() {
        return _stChActualDiscount.getSessionId();
    }

    /**
    * Sets the session ID of this st ch actual discount.
    *
    * @param sessionId the session ID of this st ch actual discount
    */
    @Override
    public void setSessionId(int sessionId) {
        _stChActualDiscount.setSessionId(sessionId);
    }

    /**
    * Returns the rs model sid of this st ch actual discount.
    *
    * @return the rs model sid of this st ch actual discount
    */
    @Override
    public int getRsModelSid() {
        return _stChActualDiscount.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this st ch actual discount.
    *
    * @param rsModelSid the rs model sid of this st ch actual discount
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _stChActualDiscount.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the actual sales of this st ch actual discount.
    *
    * @return the actual sales of this st ch actual discount
    */
    @Override
    public double getActualSales() {
        return _stChActualDiscount.getActualSales();
    }

    /**
    * Sets the actual sales of this st ch actual discount.
    *
    * @param actualSales the actual sales of this st ch actual discount
    */
    @Override
    public void setActualSales(double actualSales) {
        _stChActualDiscount.setActualSales(actualSales);
    }

    @Override
    public boolean isNew() {
        return _stChActualDiscount.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stChActualDiscount.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stChActualDiscount.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stChActualDiscount.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stChActualDiscount.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stChActualDiscount.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stChActualDiscount.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stChActualDiscount.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stChActualDiscount.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stChActualDiscount.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stChActualDiscount.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StChActualDiscountWrapper((StChActualDiscount) _stChActualDiscount.clone());
    }

    @Override
    public int compareTo(StChActualDiscount stChActualDiscount) {
        return _stChActualDiscount.compareTo(stChActualDiscount);
    }

    @Override
    public int hashCode() {
        return _stChActualDiscount.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StChActualDiscount> toCacheModel() {
        return _stChActualDiscount.toCacheModel();
    }

    @Override
    public StChActualDiscount toEscapedModel() {
        return new StChActualDiscountWrapper(_stChActualDiscount.toEscapedModel());
    }

    @Override
    public StChActualDiscount toUnescapedModel() {
        return new StChActualDiscountWrapper(_stChActualDiscount.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stChActualDiscount.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stChActualDiscount.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stChActualDiscount.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StChActualDiscountWrapper)) {
            return false;
        }

        StChActualDiscountWrapper stChActualDiscountWrapper = (StChActualDiscountWrapper) obj;

        if (Validator.equals(_stChActualDiscount,
                    stChActualDiscountWrapper._stChActualDiscount)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StChActualDiscount getWrappedStChActualDiscount() {
        return _stChActualDiscount;
    }

    @Override
    public StChActualDiscount getWrappedModel() {
        return _stChActualDiscount;
    }

    @Override
    public void resetOriginalValues() {
        _stChActualDiscount.resetOriginalValues();
    }
}
