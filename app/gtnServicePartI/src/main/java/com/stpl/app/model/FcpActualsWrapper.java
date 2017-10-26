package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FcpActuals}.
 * </p>
 *
 * @author
 * @see FcpActuals
 * @generated
 */
public class FcpActualsWrapper implements FcpActuals, ModelWrapper<FcpActuals> {
    private FcpActuals _fcpActuals;

    public FcpActualsWrapper(FcpActuals fcpActuals) {
        _fcpActuals = fcpActuals;
    }

    @Override
    public Class<?> getModelClass() {
        return FcpActuals.class;
    }

    @Override
    public String getModelClassName() {
        return FcpActuals.class.getName();
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
    * Returns the primary key of this fcp actuals.
    *
    * @return the primary key of this fcp actuals
    */
    @Override
    public com.stpl.app.service.persistence.FcpActualsPK getPrimaryKey() {
        return _fcpActuals.getPrimaryKey();
    }

    /**
    * Sets the primary key of this fcp actuals.
    *
    * @param primaryKey the primary key of this fcp actuals
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.FcpActualsPK primaryKey) {
        _fcpActuals.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the period sid of this fcp actuals.
    *
    * @return the period sid of this fcp actuals
    */
    @Override
    public int getPeriodSid() {
        return _fcpActuals.getPeriodSid();
    }

    /**
    * Sets the period sid of this fcp actuals.
    *
    * @param periodSid the period sid of this fcp actuals
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _fcpActuals.setPeriodSid(periodSid);
    }

    /**
    * Returns the price type of this fcp actuals.
    *
    * @return the price type of this fcp actuals
    */
    @Override
    public java.lang.String getPriceType() {
        return _fcpActuals.getPriceType();
    }

    /**
    * Sets the price type of this fcp actuals.
    *
    * @param priceType the price type of this fcp actuals
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _fcpActuals.setPriceType(priceType);
    }

    /**
    * Returns the actual price of this fcp actuals.
    *
    * @return the actual price of this fcp actuals
    */
    @Override
    public double getActualPrice() {
        return _fcpActuals.getActualPrice();
    }

    /**
    * Sets the actual price of this fcp actuals.
    *
    * @param actualPrice the actual price of this fcp actuals
    */
    @Override
    public void setActualPrice(double actualPrice) {
        _fcpActuals.setActualPrice(actualPrice);
    }

    /**
    * Returns the notes of this fcp actuals.
    *
    * @return the notes of this fcp actuals
    */
    @Override
    public java.lang.String getNotes() {
        return _fcpActuals.getNotes();
    }

    /**
    * Sets the notes of this fcp actuals.
    *
    * @param notes the notes of this fcp actuals
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _fcpActuals.setNotes(notes);
    }

    /**
    * Returns the na proj details sid of this fcp actuals.
    *
    * @return the na proj details sid of this fcp actuals
    */
    @Override
    public int getNaProjDetailsSid() {
        return _fcpActuals.getNaProjDetailsSid();
    }

    /**
    * Sets the na proj details sid of this fcp actuals.
    *
    * @param naProjDetailsSid the na proj details sid of this fcp actuals
    */
    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _fcpActuals.setNaProjDetailsSid(naProjDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _fcpActuals.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _fcpActuals.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _fcpActuals.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _fcpActuals.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _fcpActuals.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _fcpActuals.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _fcpActuals.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _fcpActuals.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _fcpActuals.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _fcpActuals.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _fcpActuals.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FcpActualsWrapper((FcpActuals) _fcpActuals.clone());
    }

    @Override
    public int compareTo(FcpActuals fcpActuals) {
        return _fcpActuals.compareTo(fcpActuals);
    }

    @Override
    public int hashCode() {
        return _fcpActuals.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<FcpActuals> toCacheModel() {
        return _fcpActuals.toCacheModel();
    }

    @Override
    public FcpActuals toEscapedModel() {
        return new FcpActualsWrapper(_fcpActuals.toEscapedModel());
    }

    @Override
    public FcpActuals toUnescapedModel() {
        return new FcpActualsWrapper(_fcpActuals.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _fcpActuals.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _fcpActuals.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _fcpActuals.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FcpActualsWrapper)) {
            return false;
        }

        FcpActualsWrapper fcpActualsWrapper = (FcpActualsWrapper) obj;

        if (Validator.equals(_fcpActuals, fcpActualsWrapper._fcpActuals)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public FcpActuals getWrappedFcpActuals() {
        return _fcpActuals;
    }

    @Override
    public FcpActuals getWrappedModel() {
        return _fcpActuals;
    }

    @Override
    public void resetOriginalValues() {
        _fcpActuals.resetOriginalValues();
    }
}
