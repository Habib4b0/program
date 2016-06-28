package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MedicaidUraActuals}.
 * </p>
 *
 * @author
 * @see MedicaidUraActuals
 * @generated
 */
public class MedicaidUraActualsWrapper implements MedicaidUraActuals,
    ModelWrapper<MedicaidUraActuals> {
    private MedicaidUraActuals _medicaidUraActuals;

    public MedicaidUraActualsWrapper(MedicaidUraActuals medicaidUraActuals) {
        _medicaidUraActuals = medicaidUraActuals;
    }

    @Override
    public Class<?> getModelClass() {
        return MedicaidUraActuals.class;
    }

    @Override
    public String getModelClassName() {
        return MedicaidUraActuals.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("actualPrice", getActualPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());
        attributes.put("baseYear", getBaseYear());

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

        Double baseYear = (Double) attributes.get("baseYear");

        if (baseYear != null) {
            setBaseYear(baseYear);
        }
    }

    /**
    * Returns the primary key of this medicaid ura actuals.
    *
    * @return the primary key of this medicaid ura actuals
    */
    @Override
    public com.stpl.app.service.persistence.MedicaidUraActualsPK getPrimaryKey() {
        return _medicaidUraActuals.getPrimaryKey();
    }

    /**
    * Sets the primary key of this medicaid ura actuals.
    *
    * @param primaryKey the primary key of this medicaid ura actuals
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.MedicaidUraActualsPK primaryKey) {
        _medicaidUraActuals.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the period sid of this medicaid ura actuals.
    *
    * @return the period sid of this medicaid ura actuals
    */
    @Override
    public int getPeriodSid() {
        return _medicaidUraActuals.getPeriodSid();
    }

    /**
    * Sets the period sid of this medicaid ura actuals.
    *
    * @param periodSid the period sid of this medicaid ura actuals
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _medicaidUraActuals.setPeriodSid(periodSid);
    }

    /**
    * Returns the price type of this medicaid ura actuals.
    *
    * @return the price type of this medicaid ura actuals
    */
    @Override
    public java.lang.String getPriceType() {
        return _medicaidUraActuals.getPriceType();
    }

    /**
    * Sets the price type of this medicaid ura actuals.
    *
    * @param priceType the price type of this medicaid ura actuals
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _medicaidUraActuals.setPriceType(priceType);
    }

    /**
    * Returns the actual price of this medicaid ura actuals.
    *
    * @return the actual price of this medicaid ura actuals
    */
    @Override
    public double getActualPrice() {
        return _medicaidUraActuals.getActualPrice();
    }

    /**
    * Sets the actual price of this medicaid ura actuals.
    *
    * @param actualPrice the actual price of this medicaid ura actuals
    */
    @Override
    public void setActualPrice(double actualPrice) {
        _medicaidUraActuals.setActualPrice(actualPrice);
    }

    /**
    * Returns the notes of this medicaid ura actuals.
    *
    * @return the notes of this medicaid ura actuals
    */
    @Override
    public java.lang.String getNotes() {
        return _medicaidUraActuals.getNotes();
    }

    /**
    * Sets the notes of this medicaid ura actuals.
    *
    * @param notes the notes of this medicaid ura actuals
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _medicaidUraActuals.setNotes(notes);
    }

    /**
    * Returns the na proj details sid of this medicaid ura actuals.
    *
    * @return the na proj details sid of this medicaid ura actuals
    */
    @Override
    public int getNaProjDetailsSid() {
        return _medicaidUraActuals.getNaProjDetailsSid();
    }

    /**
    * Sets the na proj details sid of this medicaid ura actuals.
    *
    * @param naProjDetailsSid the na proj details sid of this medicaid ura actuals
    */
    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _medicaidUraActuals.setNaProjDetailsSid(naProjDetailsSid);
    }

    /**
    * Returns the base year of this medicaid ura actuals.
    *
    * @return the base year of this medicaid ura actuals
    */
    @Override
    public double getBaseYear() {
        return _medicaidUraActuals.getBaseYear();
    }

    /**
    * Sets the base year of this medicaid ura actuals.
    *
    * @param baseYear the base year of this medicaid ura actuals
    */
    @Override
    public void setBaseYear(double baseYear) {
        _medicaidUraActuals.setBaseYear(baseYear);
    }

    @Override
    public boolean isNew() {
        return _medicaidUraActuals.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _medicaidUraActuals.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _medicaidUraActuals.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _medicaidUraActuals.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _medicaidUraActuals.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _medicaidUraActuals.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _medicaidUraActuals.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _medicaidUraActuals.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _medicaidUraActuals.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _medicaidUraActuals.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _medicaidUraActuals.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MedicaidUraActualsWrapper((MedicaidUraActuals) _medicaidUraActuals.clone());
    }

    @Override
    public int compareTo(MedicaidUraActuals medicaidUraActuals) {
        return _medicaidUraActuals.compareTo(medicaidUraActuals);
    }

    @Override
    public int hashCode() {
        return _medicaidUraActuals.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MedicaidUraActuals> toCacheModel() {
        return _medicaidUraActuals.toCacheModel();
    }

    @Override
    public MedicaidUraActuals toEscapedModel() {
        return new MedicaidUraActualsWrapper(_medicaidUraActuals.toEscapedModel());
    }

    @Override
    public MedicaidUraActuals toUnescapedModel() {
        return new MedicaidUraActualsWrapper(_medicaidUraActuals.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _medicaidUraActuals.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _medicaidUraActuals.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _medicaidUraActuals.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MedicaidUraActualsWrapper)) {
            return false;
        }

        MedicaidUraActualsWrapper medicaidUraActualsWrapper = (MedicaidUraActualsWrapper) obj;

        if (Validator.equals(_medicaidUraActuals,
                    medicaidUraActualsWrapper._medicaidUraActuals)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MedicaidUraActuals getWrappedMedicaidUraActuals() {
        return _medicaidUraActuals;
    }

    @Override
    public MedicaidUraActuals getWrappedModel() {
        return _medicaidUraActuals;
    }

    @Override
    public void resetOriginalValues() {
        _medicaidUraActuals.resetOriginalValues();
    }
}
