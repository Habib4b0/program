package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PhsProj}.
 * </p>
 *
 * @author
 * @see PhsProj
 * @generated
 */
public class PhsProjWrapper implements PhsProj, ModelWrapper<PhsProj> {
    private PhsProj _phsProj;

    public PhsProjWrapper(PhsProj phsProj) {
        _phsProj = phsProj;
    }

    @Override
    public Class<?> getModelClass() {
        return PhsProj.class;
    }

    @Override
    public String getModelClassName() {
        return PhsProj.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustment", getAdjustment());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("projectionPrice", getProjectionPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double adjustment = (Double) attributes.get("adjustment");

        if (adjustment != null) {
            setAdjustment(adjustment);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double projectionPrice = (Double) attributes.get("projectionPrice");

        if (projectionPrice != null) {
            setProjectionPrice(projectionPrice);
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
    * Returns the primary key of this phs proj.
    *
    * @return the primary key of this phs proj
    */
    @Override
    public com.stpl.app.service.persistence.PhsProjPK getPrimaryKey() {
        return _phsProj.getPrimaryKey();
    }

    /**
    * Sets the primary key of this phs proj.
    *
    * @param primaryKey the primary key of this phs proj
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.PhsProjPK primaryKey) {
        _phsProj.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment of this phs proj.
    *
    * @return the adjustment of this phs proj
    */
    @Override
    public double getAdjustment() {
        return _phsProj.getAdjustment();
    }

    /**
    * Sets the adjustment of this phs proj.
    *
    * @param adjustment the adjustment of this phs proj
    */
    @Override
    public void setAdjustment(double adjustment) {
        _phsProj.setAdjustment(adjustment);
    }

    /**
    * Returns the period sid of this phs proj.
    *
    * @return the period sid of this phs proj
    */
    @Override
    public int getPeriodSid() {
        return _phsProj.getPeriodSid();
    }

    /**
    * Sets the period sid of this phs proj.
    *
    * @param periodSid the period sid of this phs proj
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _phsProj.setPeriodSid(periodSid);
    }

    /**
    * Returns the price type of this phs proj.
    *
    * @return the price type of this phs proj
    */
    @Override
    public java.lang.String getPriceType() {
        return _phsProj.getPriceType();
    }

    /**
    * Sets the price type of this phs proj.
    *
    * @param priceType the price type of this phs proj
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _phsProj.setPriceType(priceType);
    }

    /**
    * Returns the projection price of this phs proj.
    *
    * @return the projection price of this phs proj
    */
    @Override
    public double getProjectionPrice() {
        return _phsProj.getProjectionPrice();
    }

    /**
    * Sets the projection price of this phs proj.
    *
    * @param projectionPrice the projection price of this phs proj
    */
    @Override
    public void setProjectionPrice(double projectionPrice) {
        _phsProj.setProjectionPrice(projectionPrice);
    }

    /**
    * Returns the notes of this phs proj.
    *
    * @return the notes of this phs proj
    */
    @Override
    public java.lang.String getNotes() {
        return _phsProj.getNotes();
    }

    /**
    * Sets the notes of this phs proj.
    *
    * @param notes the notes of this phs proj
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _phsProj.setNotes(notes);
    }

    /**
    * Returns the na proj details sid of this phs proj.
    *
    * @return the na proj details sid of this phs proj
    */
    @Override
    public int getNaProjDetailsSid() {
        return _phsProj.getNaProjDetailsSid();
    }

    /**
    * Sets the na proj details sid of this phs proj.
    *
    * @param naProjDetailsSid the na proj details sid of this phs proj
    */
    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _phsProj.setNaProjDetailsSid(naProjDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _phsProj.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _phsProj.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _phsProj.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _phsProj.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _phsProj.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _phsProj.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _phsProj.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _phsProj.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _phsProj.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _phsProj.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _phsProj.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PhsProjWrapper((PhsProj) _phsProj.clone());
    }

    @Override
    public int compareTo(PhsProj phsProj) {
        return _phsProj.compareTo(phsProj);
    }

    @Override
    public int hashCode() {
        return _phsProj.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<PhsProj> toCacheModel() {
        return _phsProj.toCacheModel();
    }

    @Override
    public PhsProj toEscapedModel() {
        return new PhsProjWrapper(_phsProj.toEscapedModel());
    }

    @Override
    public PhsProj toUnescapedModel() {
        return new PhsProjWrapper(_phsProj.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _phsProj.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _phsProj.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _phsProj.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PhsProjWrapper)) {
            return false;
        }

        PhsProjWrapper phsProjWrapper = (PhsProjWrapper) obj;

        if (Validator.equals(_phsProj, phsProjWrapper._phsProj)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PhsProj getWrappedPhsProj() {
        return _phsProj;
    }

    @Override
    public PhsProj getWrappedModel() {
        return _phsProj;
    }

    @Override
    public void resetOriginalValues() {
        _phsProj.resetOriginalValues();
    }
}
