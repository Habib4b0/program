package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PhsActuals}.
 * </p>
 *
 * @author
 * @see PhsActuals
 * @generated
 */
public class PhsActualsWrapper implements PhsActuals, ModelWrapper<PhsActuals> {
    private PhsActuals _phsActuals;

    public PhsActualsWrapper(PhsActuals phsActuals) {
        _phsActuals = phsActuals;
    }

    @Override
    public Class<?> getModelClass() {
        return PhsActuals.class;
    }

    @Override
    public String getModelClassName() {
        return PhsActuals.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("actualPrice", getActualPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double actualPrice = (Double) attributes.get("actualPrice");

        if (actualPrice != null) {
            setActualPrice(actualPrice);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }
    }

    /**
    * Returns the primary key of this phs actuals.
    *
    * @return the primary key of this phs actuals
    */
    @Override
    public com.stpl.app.service.persistence.PhsActualsPK getPrimaryKey() {
        return _phsActuals.getPrimaryKey();
    }

    /**
    * Sets the primary key of this phs actuals.
    *
    * @param primaryKey the primary key of this phs actuals
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.PhsActualsPK primaryKey) {
        _phsActuals.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the period sid of this phs actuals.
    *
    * @return the period sid of this phs actuals
    */
    @Override
    public int getPeriodSid() {
        return _phsActuals.getPeriodSid();
    }

    /**
    * Sets the period sid of this phs actuals.
    *
    * @param periodSid the period sid of this phs actuals
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _phsActuals.setPeriodSid(periodSid);
    }

    /**
    * Returns the price type of this phs actuals.
    *
    * @return the price type of this phs actuals
    */
    @Override
    public java.lang.String getPriceType() {
        return _phsActuals.getPriceType();
    }

    /**
    * Sets the price type of this phs actuals.
    *
    * @param priceType the price type of this phs actuals
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _phsActuals.setPriceType(priceType);
    }

    /**
    * Returns the actual price of this phs actuals.
    *
    * @return the actual price of this phs actuals
    */
    @Override
    public double getActualPrice() {
        return _phsActuals.getActualPrice();
    }

    /**
    * Sets the actual price of this phs actuals.
    *
    * @param actualPrice the actual price of this phs actuals
    */
    @Override
    public void setActualPrice(double actualPrice) {
        _phsActuals.setActualPrice(actualPrice);
    }

    /**
    * Returns the notes of this phs actuals.
    *
    * @return the notes of this phs actuals
    */
    @Override
    public java.lang.String getNotes() {
        return _phsActuals.getNotes();
    }

    /**
    * Sets the notes of this phs actuals.
    *
    * @param notes the notes of this phs actuals
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _phsActuals.setNotes(notes);
    }

    /**
    * Returns the na proj details sid of this phs actuals.
    *
    * @return the na proj details sid of this phs actuals
    */
    @Override
    public int getNaProjDetailsSid() {
        return _phsActuals.getNaProjDetailsSid();
    }

    /**
    * Sets the na proj details sid of this phs actuals.
    *
    * @param naProjDetailsSid the na proj details sid of this phs actuals
    */
    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _phsActuals.setNaProjDetailsSid(naProjDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _phsActuals.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _phsActuals.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _phsActuals.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _phsActuals.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _phsActuals.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _phsActuals.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _phsActuals.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _phsActuals.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _phsActuals.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _phsActuals.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _phsActuals.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PhsActualsWrapper((PhsActuals) _phsActuals.clone());
    }

    @Override
    public int compareTo(PhsActuals phsActuals) {
        return _phsActuals.compareTo(phsActuals);
    }

    @Override
    public int hashCode() {
        return _phsActuals.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<PhsActuals> toCacheModel() {
        return _phsActuals.toCacheModel();
    }

    @Override
    public PhsActuals toEscapedModel() {
        return new PhsActualsWrapper(_phsActuals.toEscapedModel());
    }

    @Override
    public PhsActuals toUnescapedModel() {
        return new PhsActualsWrapper(_phsActuals.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _phsActuals.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _phsActuals.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _phsActuals.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PhsActualsWrapper)) {
            return false;
        }

        PhsActualsWrapper phsActualsWrapper = (PhsActualsWrapper) obj;

        if (Validator.equals(_phsActuals, phsActualsWrapper._phsActuals)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PhsActuals getWrappedPhsActuals() {
        return _phsActuals;
    }

    @Override
    public PhsActuals getWrappedModel() {
        return _phsActuals;
    }

    @Override
    public void resetOriginalValues() {
        _phsActuals.resetOriginalValues();
    }
}
