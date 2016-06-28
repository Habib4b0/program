package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StNmActualPpa}.
 * </p>
 *
 * @author
 * @see StNmActualPpa
 * @generated
 */
public class StNmActualPpaWrapper implements StNmActualPpa,
    ModelWrapper<StNmActualPpa> {
    private StNmActualPpa _stNmActualPpa;

    public StNmActualPpaWrapper(StNmActualPpa stNmActualPpa) {
        _stNmActualPpa = stNmActualPpa;
    }

    @Override
    public Class<?> getModelClass() {
        return StNmActualPpa.class;
    }

    @Override
    public String getModelClassName() {
        return StNmActualPpa.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualProjDiscountDollar", getActualProjDiscountDollar());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("sessionId", getSessionId());
        attributes.put("actualProjDiscountUnits", getActualProjDiscountUnits());
        attributes.put("actualDiscountDollar", getActualDiscountDollar());
        attributes.put("actualDiscountUnits", getActualDiscountUnits());
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

        Double actualProjDiscountDollar = (Double) attributes.get(
                "actualProjDiscountDollar");

        if (actualProjDiscountDollar != null) {
            setActualProjDiscountDollar(actualProjDiscountDollar);
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

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double actualProjDiscountUnits = (Double) attributes.get(
                "actualProjDiscountUnits");

        if (actualProjDiscountUnits != null) {
            setActualProjDiscountUnits(actualProjDiscountUnits);
        }

        Double actualDiscountDollar = (Double) attributes.get(
                "actualDiscountDollar");

        if (actualDiscountDollar != null) {
            setActualDiscountDollar(actualDiscountDollar);
        }

        Double actualDiscountUnits = (Double) attributes.get(
                "actualDiscountUnits");

        if (actualDiscountUnits != null) {
            setActualDiscountUnits(actualDiscountUnits);
        }

        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }
    }

    /**
    * Returns the primary key of this st nm actual ppa.
    *
    * @return the primary key of this st nm actual ppa
    */
    @Override
    public com.stpl.app.service.persistence.StNmActualPpaPK getPrimaryKey() {
        return _stNmActualPpa.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st nm actual ppa.
    *
    * @param primaryKey the primary key of this st nm actual ppa
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StNmActualPpaPK primaryKey) {
        _stNmActualPpa.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the last modified date of this st nm actual ppa.
    *
    * @return the last modified date of this st nm actual ppa
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stNmActualPpa.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st nm actual ppa.
    *
    * @param lastModifiedDate the last modified date of this st nm actual ppa
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stNmActualPpa.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the actual rate of this st nm actual ppa.
    *
    * @return the actual rate of this st nm actual ppa
    */
    @Override
    public double getActualRate() {
        return _stNmActualPpa.getActualRate();
    }

    /**
    * Sets the actual rate of this st nm actual ppa.
    *
    * @param actualRate the actual rate of this st nm actual ppa
    */
    @Override
    public void setActualRate(double actualRate) {
        _stNmActualPpa.setActualRate(actualRate);
    }

    /**
    * Returns the period sid of this st nm actual ppa.
    *
    * @return the period sid of this st nm actual ppa
    */
    @Override
    public int getPeriodSid() {
        return _stNmActualPpa.getPeriodSid();
    }

    /**
    * Sets the period sid of this st nm actual ppa.
    *
    * @param periodSid the period sid of this st nm actual ppa
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _stNmActualPpa.setPeriodSid(periodSid);
    }

    /**
    * Returns the actual proj discount dollar of this st nm actual ppa.
    *
    * @return the actual proj discount dollar of this st nm actual ppa
    */
    @Override
    public double getActualProjDiscountDollar() {
        return _stNmActualPpa.getActualProjDiscountDollar();
    }

    /**
    * Sets the actual proj discount dollar of this st nm actual ppa.
    *
    * @param actualProjDiscountDollar the actual proj discount dollar of this st nm actual ppa
    */
    @Override
    public void setActualProjDiscountDollar(double actualProjDiscountDollar) {
        _stNmActualPpa.setActualProjDiscountDollar(actualProjDiscountDollar);
    }

    /**
    * Returns the actual projection sales of this st nm actual ppa.
    *
    * @return the actual projection sales of this st nm actual ppa
    */
    @Override
    public double getActualProjectionSales() {
        return _stNmActualPpa.getActualProjectionSales();
    }

    /**
    * Sets the actual projection sales of this st nm actual ppa.
    *
    * @param actualProjectionSales the actual projection sales of this st nm actual ppa
    */
    @Override
    public void setActualProjectionSales(double actualProjectionSales) {
        _stNmActualPpa.setActualProjectionSales(actualProjectionSales);
    }

    /**
    * Returns the projection details sid of this st nm actual ppa.
    *
    * @return the projection details sid of this st nm actual ppa
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stNmActualPpa.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st nm actual ppa.
    *
    * @param projectionDetailsSid the projection details sid of this st nm actual ppa
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stNmActualPpa.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the user ID of this st nm actual ppa.
    *
    * @return the user ID of this st nm actual ppa
    */
    @Override
    public int getUserId() {
        return _stNmActualPpa.getUserId();
    }

    /**
    * Sets the user ID of this st nm actual ppa.
    *
    * @param userId the user ID of this st nm actual ppa
    */
    @Override
    public void setUserId(int userId) {
        _stNmActualPpa.setUserId(userId);
    }

    /**
    * Returns the actual projection rate of this st nm actual ppa.
    *
    * @return the actual projection rate of this st nm actual ppa
    */
    @Override
    public double getActualProjectionRate() {
        return _stNmActualPpa.getActualProjectionRate();
    }

    /**
    * Sets the actual projection rate of this st nm actual ppa.
    *
    * @param actualProjectionRate the actual projection rate of this st nm actual ppa
    */
    @Override
    public void setActualProjectionRate(double actualProjectionRate) {
        _stNmActualPpa.setActualProjectionRate(actualProjectionRate);
    }

    /**
    * Returns the session ID of this st nm actual ppa.
    *
    * @return the session ID of this st nm actual ppa
    */
    @Override
    public int getSessionId() {
        return _stNmActualPpa.getSessionId();
    }

    /**
    * Sets the session ID of this st nm actual ppa.
    *
    * @param sessionId the session ID of this st nm actual ppa
    */
    @Override
    public void setSessionId(int sessionId) {
        _stNmActualPpa.setSessionId(sessionId);
    }

    /**
    * Returns the actual proj discount units of this st nm actual ppa.
    *
    * @return the actual proj discount units of this st nm actual ppa
    */
    @Override
    public double getActualProjDiscountUnits() {
        return _stNmActualPpa.getActualProjDiscountUnits();
    }

    /**
    * Sets the actual proj discount units of this st nm actual ppa.
    *
    * @param actualProjDiscountUnits the actual proj discount units of this st nm actual ppa
    */
    @Override
    public void setActualProjDiscountUnits(double actualProjDiscountUnits) {
        _stNmActualPpa.setActualProjDiscountUnits(actualProjDiscountUnits);
    }

    /**
    * Returns the actual discount dollar of this st nm actual ppa.
    *
    * @return the actual discount dollar of this st nm actual ppa
    */
    @Override
    public double getActualDiscountDollar() {
        return _stNmActualPpa.getActualDiscountDollar();
    }

    /**
    * Sets the actual discount dollar of this st nm actual ppa.
    *
    * @param actualDiscountDollar the actual discount dollar of this st nm actual ppa
    */
    @Override
    public void setActualDiscountDollar(double actualDiscountDollar) {
        _stNmActualPpa.setActualDiscountDollar(actualDiscountDollar);
    }

    /**
    * Returns the actual discount units of this st nm actual ppa.
    *
    * @return the actual discount units of this st nm actual ppa
    */
    @Override
    public double getActualDiscountUnits() {
        return _stNmActualPpa.getActualDiscountUnits();
    }

    /**
    * Sets the actual discount units of this st nm actual ppa.
    *
    * @param actualDiscountUnits the actual discount units of this st nm actual ppa
    */
    @Override
    public void setActualDiscountUnits(double actualDiscountUnits) {
        _stNmActualPpa.setActualDiscountUnits(actualDiscountUnits);
    }

    /**
    * Returns the actual sales of this st nm actual ppa.
    *
    * @return the actual sales of this st nm actual ppa
    */
    @Override
    public double getActualSales() {
        return _stNmActualPpa.getActualSales();
    }

    /**
    * Sets the actual sales of this st nm actual ppa.
    *
    * @param actualSales the actual sales of this st nm actual ppa
    */
    @Override
    public void setActualSales(double actualSales) {
        _stNmActualPpa.setActualSales(actualSales);
    }

    @Override
    public boolean isNew() {
        return _stNmActualPpa.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stNmActualPpa.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stNmActualPpa.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stNmActualPpa.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stNmActualPpa.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stNmActualPpa.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stNmActualPpa.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stNmActualPpa.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stNmActualPpa.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stNmActualPpa.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stNmActualPpa.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StNmActualPpaWrapper((StNmActualPpa) _stNmActualPpa.clone());
    }

    @Override
    public int compareTo(StNmActualPpa stNmActualPpa) {
        return _stNmActualPpa.compareTo(stNmActualPpa);
    }

    @Override
    public int hashCode() {
        return _stNmActualPpa.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StNmActualPpa> toCacheModel() {
        return _stNmActualPpa.toCacheModel();
    }

    @Override
    public StNmActualPpa toEscapedModel() {
        return new StNmActualPpaWrapper(_stNmActualPpa.toEscapedModel());
    }

    @Override
    public StNmActualPpa toUnescapedModel() {
        return new StNmActualPpaWrapper(_stNmActualPpa.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stNmActualPpa.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stNmActualPpa.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stNmActualPpa.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmActualPpaWrapper)) {
            return false;
        }

        StNmActualPpaWrapper stNmActualPpaWrapper = (StNmActualPpaWrapper) obj;

        if (Validator.equals(_stNmActualPpa, stNmActualPpaWrapper._stNmActualPpa)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StNmActualPpa getWrappedStNmActualPpa() {
        return _stNmActualPpa;
    }

    @Override
    public StNmActualPpa getWrappedModel() {
        return _stNmActualPpa;
    }

    @Override
    public void resetOriginalValues() {
        _stNmActualPpa.resetOriginalValues();
    }
}
