package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FcpProj}.
 * </p>
 *
 * @author
 * @see FcpProj
 * @generated
 */
public class FcpProjWrapper implements FcpProj, ModelWrapper<FcpProj> {
    private FcpProj _fcpProj;

    public FcpProjWrapper(FcpProj fcpProj) {
        _fcpProj = fcpProj;
    }

    @Override
    public Class<?> getModelClass() {
        return FcpProj.class;
    }

    @Override
    public String getModelClassName() {
        return FcpProj.class.getName();
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
    * Returns the primary key of this fcp proj.
    *
    * @return the primary key of this fcp proj
    */
    @Override
    public com.stpl.app.service.persistence.FcpProjPK getPrimaryKey() {
        return _fcpProj.getPrimaryKey();
    }

    /**
    * Sets the primary key of this fcp proj.
    *
    * @param primaryKey the primary key of this fcp proj
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.FcpProjPK primaryKey) {
        _fcpProj.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment of this fcp proj.
    *
    * @return the adjustment of this fcp proj
    */
    @Override
    public double getAdjustment() {
        return _fcpProj.getAdjustment();
    }

    /**
    * Sets the adjustment of this fcp proj.
    *
    * @param adjustment the adjustment of this fcp proj
    */
    @Override
    public void setAdjustment(double adjustment) {
        _fcpProj.setAdjustment(adjustment);
    }

    /**
    * Returns the period sid of this fcp proj.
    *
    * @return the period sid of this fcp proj
    */
    @Override
    public int getPeriodSid() {
        return _fcpProj.getPeriodSid();
    }

    /**
    * Sets the period sid of this fcp proj.
    *
    * @param periodSid the period sid of this fcp proj
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _fcpProj.setPeriodSid(periodSid);
    }

    /**
    * Returns the price type of this fcp proj.
    *
    * @return the price type of this fcp proj
    */
    @Override
    public java.lang.String getPriceType() {
        return _fcpProj.getPriceType();
    }

    /**
    * Sets the price type of this fcp proj.
    *
    * @param priceType the price type of this fcp proj
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _fcpProj.setPriceType(priceType);
    }

    /**
    * Returns the projection price of this fcp proj.
    *
    * @return the projection price of this fcp proj
    */
    @Override
    public double getProjectionPrice() {
        return _fcpProj.getProjectionPrice();
    }

    /**
    * Sets the projection price of this fcp proj.
    *
    * @param projectionPrice the projection price of this fcp proj
    */
    @Override
    public void setProjectionPrice(double projectionPrice) {
        _fcpProj.setProjectionPrice(projectionPrice);
    }

    /**
    * Returns the notes of this fcp proj.
    *
    * @return the notes of this fcp proj
    */
    @Override
    public java.lang.String getNotes() {
        return _fcpProj.getNotes();
    }

    /**
    * Sets the notes of this fcp proj.
    *
    * @param notes the notes of this fcp proj
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _fcpProj.setNotes(notes);
    }

    /**
    * Returns the na proj details sid of this fcp proj.
    *
    * @return the na proj details sid of this fcp proj
    */
    @Override
    public int getNaProjDetailsSid() {
        return _fcpProj.getNaProjDetailsSid();
    }

    /**
    * Sets the na proj details sid of this fcp proj.
    *
    * @param naProjDetailsSid the na proj details sid of this fcp proj
    */
    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _fcpProj.setNaProjDetailsSid(naProjDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _fcpProj.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _fcpProj.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _fcpProj.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _fcpProj.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _fcpProj.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _fcpProj.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _fcpProj.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _fcpProj.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _fcpProj.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _fcpProj.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _fcpProj.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FcpProjWrapper((FcpProj) _fcpProj.clone());
    }

    @Override
    public int compareTo(FcpProj fcpProj) {
        return _fcpProj.compareTo(fcpProj);
    }

    @Override
    public int hashCode() {
        return _fcpProj.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<FcpProj> toCacheModel() {
        return _fcpProj.toCacheModel();
    }

    @Override
    public FcpProj toEscapedModel() {
        return new FcpProjWrapper(_fcpProj.toEscapedModel());
    }

    @Override
    public FcpProj toUnescapedModel() {
        return new FcpProjWrapper(_fcpProj.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _fcpProj.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _fcpProj.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _fcpProj.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FcpProjWrapper)) {
            return false;
        }

        FcpProjWrapper fcpProjWrapper = (FcpProjWrapper) obj;

        if (Validator.equals(_fcpProj, fcpProjWrapper._fcpProj)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public FcpProj getWrappedFcpProj() {
        return _fcpProj;
    }

    @Override
    public FcpProj getWrappedModel() {
        return _fcpProj;
    }

    @Override
    public void resetOriginalValues() {
        _fcpProj.resetOriginalValues();
    }
}
