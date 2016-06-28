package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NmActualPpa}.
 * </p>
 *
 * @author
 * @see NmActualPpa
 * @generated
 */
public class NmActualPpaWrapper implements NmActualPpa,
    ModelWrapper<NmActualPpa> {
    private NmActualPpa _nmActualPpa;

    public NmActualPpaWrapper(NmActualPpa nmActualPpa) {
        _nmActualPpa = nmActualPpa;
    }

    @Override
    public Class<?> getModelClass() {
        return NmActualPpa.class;
    }

    @Override
    public String getModelClassName() {
        return NmActualPpa.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualProjDiscountDollar", getActualProjDiscountDollar());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("actualProjDiscountUnits", getActualProjDiscountUnits());
        attributes.put("actualDiscountDollar", getActualDiscountDollar());
        attributes.put("actualDiscountUnits", getActualDiscountUnits());
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

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
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
    * Returns the primary key of this nm actual ppa.
    *
    * @return the primary key of this nm actual ppa
    */
    @Override
    public com.stpl.app.service.persistence.NmActualPpaPK getPrimaryKey() {
        return _nmActualPpa.getPrimaryKey();
    }

    /**
    * Sets the primary key of this nm actual ppa.
    *
    * @param primaryKey the primary key of this nm actual ppa
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.NmActualPpaPK primaryKey) {
        _nmActualPpa.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the actual rate of this nm actual ppa.
    *
    * @return the actual rate of this nm actual ppa
    */
    @Override
    public double getActualRate() {
        return _nmActualPpa.getActualRate();
    }

    /**
    * Sets the actual rate of this nm actual ppa.
    *
    * @param actualRate the actual rate of this nm actual ppa
    */
    @Override
    public void setActualRate(double actualRate) {
        _nmActualPpa.setActualRate(actualRate);
    }

    /**
    * Returns the period sid of this nm actual ppa.
    *
    * @return the period sid of this nm actual ppa
    */
    @Override
    public int getPeriodSid() {
        return _nmActualPpa.getPeriodSid();
    }

    /**
    * Sets the period sid of this nm actual ppa.
    *
    * @param periodSid the period sid of this nm actual ppa
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _nmActualPpa.setPeriodSid(periodSid);
    }

    /**
    * Returns the actual proj discount dollar of this nm actual ppa.
    *
    * @return the actual proj discount dollar of this nm actual ppa
    */
    @Override
    public double getActualProjDiscountDollar() {
        return _nmActualPpa.getActualProjDiscountDollar();
    }

    /**
    * Sets the actual proj discount dollar of this nm actual ppa.
    *
    * @param actualProjDiscountDollar the actual proj discount dollar of this nm actual ppa
    */
    @Override
    public void setActualProjDiscountDollar(double actualProjDiscountDollar) {
        _nmActualPpa.setActualProjDiscountDollar(actualProjDiscountDollar);
    }

    /**
    * Returns the actual projection sales of this nm actual ppa.
    *
    * @return the actual projection sales of this nm actual ppa
    */
    @Override
    public double getActualProjectionSales() {
        return _nmActualPpa.getActualProjectionSales();
    }

    /**
    * Sets the actual projection sales of this nm actual ppa.
    *
    * @param actualProjectionSales the actual projection sales of this nm actual ppa
    */
    @Override
    public void setActualProjectionSales(double actualProjectionSales) {
        _nmActualPpa.setActualProjectionSales(actualProjectionSales);
    }

    /**
    * Returns the projection details sid of this nm actual ppa.
    *
    * @return the projection details sid of this nm actual ppa
    */
    @Override
    public int getProjectionDetailsSid() {
        return _nmActualPpa.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this nm actual ppa.
    *
    * @param projectionDetailsSid the projection details sid of this nm actual ppa
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _nmActualPpa.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the actual projection rate of this nm actual ppa.
    *
    * @return the actual projection rate of this nm actual ppa
    */
    @Override
    public double getActualProjectionRate() {
        return _nmActualPpa.getActualProjectionRate();
    }

    /**
    * Sets the actual projection rate of this nm actual ppa.
    *
    * @param actualProjectionRate the actual projection rate of this nm actual ppa
    */
    @Override
    public void setActualProjectionRate(double actualProjectionRate) {
        _nmActualPpa.setActualProjectionRate(actualProjectionRate);
    }

    /**
    * Returns the actual proj discount units of this nm actual ppa.
    *
    * @return the actual proj discount units of this nm actual ppa
    */
    @Override
    public double getActualProjDiscountUnits() {
        return _nmActualPpa.getActualProjDiscountUnits();
    }

    /**
    * Sets the actual proj discount units of this nm actual ppa.
    *
    * @param actualProjDiscountUnits the actual proj discount units of this nm actual ppa
    */
    @Override
    public void setActualProjDiscountUnits(double actualProjDiscountUnits) {
        _nmActualPpa.setActualProjDiscountUnits(actualProjDiscountUnits);
    }

    /**
    * Returns the actual discount dollar of this nm actual ppa.
    *
    * @return the actual discount dollar of this nm actual ppa
    */
    @Override
    public double getActualDiscountDollar() {
        return _nmActualPpa.getActualDiscountDollar();
    }

    /**
    * Sets the actual discount dollar of this nm actual ppa.
    *
    * @param actualDiscountDollar the actual discount dollar of this nm actual ppa
    */
    @Override
    public void setActualDiscountDollar(double actualDiscountDollar) {
        _nmActualPpa.setActualDiscountDollar(actualDiscountDollar);
    }

    /**
    * Returns the actual discount units of this nm actual ppa.
    *
    * @return the actual discount units of this nm actual ppa
    */
    @Override
    public double getActualDiscountUnits() {
        return _nmActualPpa.getActualDiscountUnits();
    }

    /**
    * Sets the actual discount units of this nm actual ppa.
    *
    * @param actualDiscountUnits the actual discount units of this nm actual ppa
    */
    @Override
    public void setActualDiscountUnits(double actualDiscountUnits) {
        _nmActualPpa.setActualDiscountUnits(actualDiscountUnits);
    }

    /**
    * Returns the actual sales of this nm actual ppa.
    *
    * @return the actual sales of this nm actual ppa
    */
    @Override
    public double getActualSales() {
        return _nmActualPpa.getActualSales();
    }

    /**
    * Sets the actual sales of this nm actual ppa.
    *
    * @param actualSales the actual sales of this nm actual ppa
    */
    @Override
    public void setActualSales(double actualSales) {
        _nmActualPpa.setActualSales(actualSales);
    }

    @Override
    public boolean isNew() {
        return _nmActualPpa.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nmActualPpa.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nmActualPpa.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nmActualPpa.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nmActualPpa.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nmActualPpa.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nmActualPpa.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nmActualPpa.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nmActualPpa.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nmActualPpa.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nmActualPpa.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NmActualPpaWrapper((NmActualPpa) _nmActualPpa.clone());
    }

    @Override
    public int compareTo(NmActualPpa nmActualPpa) {
        return _nmActualPpa.compareTo(nmActualPpa);
    }

    @Override
    public int hashCode() {
        return _nmActualPpa.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NmActualPpa> toCacheModel() {
        return _nmActualPpa.toCacheModel();
    }

    @Override
    public NmActualPpa toEscapedModel() {
        return new NmActualPpaWrapper(_nmActualPpa.toEscapedModel());
    }

    @Override
    public NmActualPpa toUnescapedModel() {
        return new NmActualPpaWrapper(_nmActualPpa.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nmActualPpa.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nmActualPpa.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nmActualPpa.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmActualPpaWrapper)) {
            return false;
        }

        NmActualPpaWrapper nmActualPpaWrapper = (NmActualPpaWrapper) obj;

        if (Validator.equals(_nmActualPpa, nmActualPpaWrapper._nmActualPpa)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NmActualPpa getWrappedNmActualPpa() {
        return _nmActualPpa;
    }

    @Override
    public NmActualPpa getWrappedModel() {
        return _nmActualPpa;
    }

    @Override
    public void resetOriginalValues() {
        _nmActualPpa.resetOriginalValues();
    }
}
