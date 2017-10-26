package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MSupplementalDiscActuals}.
 * </p>
 *
 * @author
 * @see MSupplementalDiscActuals
 * @generated
 */
public class MSupplementalDiscActualsWrapper implements MSupplementalDiscActuals,
    ModelWrapper<MSupplementalDiscActuals> {
    private MSupplementalDiscActuals _mSupplementalDiscActuals;

    public MSupplementalDiscActualsWrapper(
        MSupplementalDiscActuals mSupplementalDiscActuals) {
        _mSupplementalDiscActuals = mSupplementalDiscActuals;
    }

    @Override
    public Class<?> getModelClass() {
        return MSupplementalDiscActuals.class;
    }

    @Override
    public String getModelClassName() {
        return MSupplementalDiscActuals.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualSales", getActualSales());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualRate", getActualRate());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());

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
    }

    /**
    * Returns the primary key of this m supplemental disc actuals.
    *
    * @return the primary key of this m supplemental disc actuals
    */
    @Override
    public com.stpl.app.service.persistence.MSupplementalDiscActualsPK getPrimaryKey() {
        return _mSupplementalDiscActuals.getPrimaryKey();
    }

    /**
    * Sets the primary key of this m supplemental disc actuals.
    *
    * @param primaryKey the primary key of this m supplemental disc actuals
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.MSupplementalDiscActualsPK primaryKey) {
        _mSupplementalDiscActuals.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the actual sales of this m supplemental disc actuals.
    *
    * @return the actual sales of this m supplemental disc actuals
    */
    @Override
    public double getActualSales() {
        return _mSupplementalDiscActuals.getActualSales();
    }

    /**
    * Sets the actual sales of this m supplemental disc actuals.
    *
    * @param actualSales the actual sales of this m supplemental disc actuals
    */
    @Override
    public void setActualSales(double actualSales) {
        _mSupplementalDiscActuals.setActualSales(actualSales);
    }

    /**
    * Returns the period sid of this m supplemental disc actuals.
    *
    * @return the period sid of this m supplemental disc actuals
    */
    @Override
    public int getPeriodSid() {
        return _mSupplementalDiscActuals.getPeriodSid();
    }

    /**
    * Sets the period sid of this m supplemental disc actuals.
    *
    * @param periodSid the period sid of this m supplemental disc actuals
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _mSupplementalDiscActuals.setPeriodSid(periodSid);
    }

    /**
    * Returns the actual rate of this m supplemental disc actuals.
    *
    * @return the actual rate of this m supplemental disc actuals
    */
    @Override
    public double getActualRate() {
        return _mSupplementalDiscActuals.getActualRate();
    }

    /**
    * Sets the actual rate of this m supplemental disc actuals.
    *
    * @param actualRate the actual rate of this m supplemental disc actuals
    */
    @Override
    public void setActualRate(double actualRate) {
        _mSupplementalDiscActuals.setActualRate(actualRate);
    }

    /**
    * Returns the actual projection sales of this m supplemental disc actuals.
    *
    * @return the actual projection sales of this m supplemental disc actuals
    */
    @Override
    public double getActualProjectionSales() {
        return _mSupplementalDiscActuals.getActualProjectionSales();
    }

    /**
    * Sets the actual projection sales of this m supplemental disc actuals.
    *
    * @param actualProjectionSales the actual projection sales of this m supplemental disc actuals
    */
    @Override
    public void setActualProjectionSales(double actualProjectionSales) {
        _mSupplementalDiscActuals.setActualProjectionSales(actualProjectionSales);
    }

    /**
    * Returns the actual projection rate of this m supplemental disc actuals.
    *
    * @return the actual projection rate of this m supplemental disc actuals
    */
    @Override
    public double getActualProjectionRate() {
        return _mSupplementalDiscActuals.getActualProjectionRate();
    }

    /**
    * Sets the actual projection rate of this m supplemental disc actuals.
    *
    * @param actualProjectionRate the actual projection rate of this m supplemental disc actuals
    */
    @Override
    public void setActualProjectionRate(double actualProjectionRate) {
        _mSupplementalDiscActuals.setActualProjectionRate(actualProjectionRate);
    }

    /**
    * Returns the projection details sid of this m supplemental disc actuals.
    *
    * @return the projection details sid of this m supplemental disc actuals
    */
    @Override
    public int getProjectionDetailsSid() {
        return _mSupplementalDiscActuals.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this m supplemental disc actuals.
    *
    * @param projectionDetailsSid the projection details sid of this m supplemental disc actuals
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _mSupplementalDiscActuals.setProjectionDetailsSid(projectionDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _mSupplementalDiscActuals.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _mSupplementalDiscActuals.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _mSupplementalDiscActuals.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _mSupplementalDiscActuals.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _mSupplementalDiscActuals.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _mSupplementalDiscActuals.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _mSupplementalDiscActuals.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _mSupplementalDiscActuals.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _mSupplementalDiscActuals.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _mSupplementalDiscActuals.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _mSupplementalDiscActuals.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MSupplementalDiscActualsWrapper((MSupplementalDiscActuals) _mSupplementalDiscActuals.clone());
    }

    @Override
    public int compareTo(MSupplementalDiscActuals mSupplementalDiscActuals) {
        return _mSupplementalDiscActuals.compareTo(mSupplementalDiscActuals);
    }

    @Override
    public int hashCode() {
        return _mSupplementalDiscActuals.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MSupplementalDiscActuals> toCacheModel() {
        return _mSupplementalDiscActuals.toCacheModel();
    }

    @Override
    public MSupplementalDiscActuals toEscapedModel() {
        return new MSupplementalDiscActualsWrapper(_mSupplementalDiscActuals.toEscapedModel());
    }

    @Override
    public MSupplementalDiscActuals toUnescapedModel() {
        return new MSupplementalDiscActualsWrapper(_mSupplementalDiscActuals.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _mSupplementalDiscActuals.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _mSupplementalDiscActuals.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _mSupplementalDiscActuals.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MSupplementalDiscActualsWrapper)) {
            return false;
        }

        MSupplementalDiscActualsWrapper mSupplementalDiscActualsWrapper = (MSupplementalDiscActualsWrapper) obj;

        if (Validator.equals(_mSupplementalDiscActuals,
                    mSupplementalDiscActualsWrapper._mSupplementalDiscActuals)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MSupplementalDiscActuals getWrappedMSupplementalDiscActuals() {
        return _mSupplementalDiscActuals;
    }

    @Override
    public MSupplementalDiscActuals getWrappedModel() {
        return _mSupplementalDiscActuals;
    }

    @Override
    public void resetOriginalValues() {
        _mSupplementalDiscActuals.resetOriginalValues();
    }
}
