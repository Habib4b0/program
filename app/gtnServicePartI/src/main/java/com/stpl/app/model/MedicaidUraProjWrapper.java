package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MedicaidUraProj}.
 * </p>
 *
 * @author
 * @see MedicaidUraProj
 * @generated
 */
public class MedicaidUraProjWrapper implements MedicaidUraProj,
    ModelWrapper<MedicaidUraProj> {
    private MedicaidUraProj _medicaidUraProj;

    public MedicaidUraProjWrapper(MedicaidUraProj medicaidUraProj) {
        _medicaidUraProj = medicaidUraProj;
    }

    @Override
    public Class<?> getModelClass() {
        return MedicaidUraProj.class;
    }

    @Override
    public String getModelClassName() {
        return MedicaidUraProj.class.getName();
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
    * Returns the primary key of this medicaid ura proj.
    *
    * @return the primary key of this medicaid ura proj
    */
    @Override
    public com.stpl.app.service.persistence.MedicaidUraProjPK getPrimaryKey() {
        return _medicaidUraProj.getPrimaryKey();
    }

    /**
    * Sets the primary key of this medicaid ura proj.
    *
    * @param primaryKey the primary key of this medicaid ura proj
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.MedicaidUraProjPK primaryKey) {
        _medicaidUraProj.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment of this medicaid ura proj.
    *
    * @return the adjustment of this medicaid ura proj
    */
    @Override
    public double getAdjustment() {
        return _medicaidUraProj.getAdjustment();
    }

    /**
    * Sets the adjustment of this medicaid ura proj.
    *
    * @param adjustment the adjustment of this medicaid ura proj
    */
    @Override
    public void setAdjustment(double adjustment) {
        _medicaidUraProj.setAdjustment(adjustment);
    }

    /**
    * Returns the period sid of this medicaid ura proj.
    *
    * @return the period sid of this medicaid ura proj
    */
    @Override
    public int getPeriodSid() {
        return _medicaidUraProj.getPeriodSid();
    }

    /**
    * Sets the period sid of this medicaid ura proj.
    *
    * @param periodSid the period sid of this medicaid ura proj
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _medicaidUraProj.setPeriodSid(periodSid);
    }

    /**
    * Returns the price type of this medicaid ura proj.
    *
    * @return the price type of this medicaid ura proj
    */
    @Override
    public java.lang.String getPriceType() {
        return _medicaidUraProj.getPriceType();
    }

    /**
    * Sets the price type of this medicaid ura proj.
    *
    * @param priceType the price type of this medicaid ura proj
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _medicaidUraProj.setPriceType(priceType);
    }

    /**
    * Returns the projection price of this medicaid ura proj.
    *
    * @return the projection price of this medicaid ura proj
    */
    @Override
    public double getProjectionPrice() {
        return _medicaidUraProj.getProjectionPrice();
    }

    /**
    * Sets the projection price of this medicaid ura proj.
    *
    * @param projectionPrice the projection price of this medicaid ura proj
    */
    @Override
    public void setProjectionPrice(double projectionPrice) {
        _medicaidUraProj.setProjectionPrice(projectionPrice);
    }

    /**
    * Returns the notes of this medicaid ura proj.
    *
    * @return the notes of this medicaid ura proj
    */
    @Override
    public java.lang.String getNotes() {
        return _medicaidUraProj.getNotes();
    }

    /**
    * Sets the notes of this medicaid ura proj.
    *
    * @param notes the notes of this medicaid ura proj
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _medicaidUraProj.setNotes(notes);
    }

    /**
    * Returns the na proj details sid of this medicaid ura proj.
    *
    * @return the na proj details sid of this medicaid ura proj
    */
    @Override
    public int getNaProjDetailsSid() {
        return _medicaidUraProj.getNaProjDetailsSid();
    }

    /**
    * Sets the na proj details sid of this medicaid ura proj.
    *
    * @param naProjDetailsSid the na proj details sid of this medicaid ura proj
    */
    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _medicaidUraProj.setNaProjDetailsSid(naProjDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _medicaidUraProj.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _medicaidUraProj.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _medicaidUraProj.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _medicaidUraProj.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _medicaidUraProj.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _medicaidUraProj.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _medicaidUraProj.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _medicaidUraProj.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _medicaidUraProj.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _medicaidUraProj.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _medicaidUraProj.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MedicaidUraProjWrapper((MedicaidUraProj) _medicaidUraProj.clone());
    }

    @Override
    public int compareTo(MedicaidUraProj medicaidUraProj) {
        return _medicaidUraProj.compareTo(medicaidUraProj);
    }

    @Override
    public int hashCode() {
        return _medicaidUraProj.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MedicaidUraProj> toCacheModel() {
        return _medicaidUraProj.toCacheModel();
    }

    @Override
    public MedicaidUraProj toEscapedModel() {
        return new MedicaidUraProjWrapper(_medicaidUraProj.toEscapedModel());
    }

    @Override
    public MedicaidUraProj toUnescapedModel() {
        return new MedicaidUraProjWrapper(_medicaidUraProj.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _medicaidUraProj.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _medicaidUraProj.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _medicaidUraProj.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MedicaidUraProjWrapper)) {
            return false;
        }

        MedicaidUraProjWrapper medicaidUraProjWrapper = (MedicaidUraProjWrapper) obj;

        if (Validator.equals(_medicaidUraProj,
                    medicaidUraProjWrapper._medicaidUraProj)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MedicaidUraProj getWrappedMedicaidUraProj() {
        return _medicaidUraProj;
    }

    @Override
    public MedicaidUraProj getWrappedModel() {
        return _medicaidUraProj;
    }

    @Override
    public void resetOriginalValues() {
        _medicaidUraProj.resetOriginalValues();
    }
}
