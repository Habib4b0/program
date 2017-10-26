package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NationalAssumptionsProj}.
 * </p>
 *
 * @author
 * @see NationalAssumptionsProj
 * @generated
 */
public class NationalAssumptionsProjWrapper implements NationalAssumptionsProj,
    ModelWrapper<NationalAssumptionsProj> {
    private NationalAssumptionsProj _nationalAssumptionsProj;

    public NationalAssumptionsProjWrapper(
        NationalAssumptionsProj nationalAssumptionsProj) {
        _nationalAssumptionsProj = nationalAssumptionsProj;
    }

    @Override
    public Class<?> getModelClass() {
        return NationalAssumptionsProj.class;
    }

    @Override
    public String getModelClassName() {
        return NationalAssumptionsProj.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("priceType", getPriceType());
        attributes.put("projectionPrice", getProjectionPrice());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double projectionPrice = (Double) attributes.get("projectionPrice");

        if (projectionPrice != null) {
            setProjectionPrice(projectionPrice);
        }
    }

    /**
    * Returns the primary key of this national assumptions proj.
    *
    * @return the primary key of this national assumptions proj
    */
    @Override
    public com.stpl.app.service.persistence.NationalAssumptionsProjPK getPrimaryKey() {
        return _nationalAssumptionsProj.getPrimaryKey();
    }

    /**
    * Sets the primary key of this national assumptions proj.
    *
    * @param primaryKey the primary key of this national assumptions proj
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.NationalAssumptionsProjPK primaryKey) {
        _nationalAssumptionsProj.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the period sid of this national assumptions proj.
    *
    * @return the period sid of this national assumptions proj
    */
    @Override
    public int getPeriodSid() {
        return _nationalAssumptionsProj.getPeriodSid();
    }

    /**
    * Sets the period sid of this national assumptions proj.
    *
    * @param periodSid the period sid of this national assumptions proj
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _nationalAssumptionsProj.setPeriodSid(periodSid);
    }

    /**
    * Returns the item master sid of this national assumptions proj.
    *
    * @return the item master sid of this national assumptions proj
    */
    @Override
    public int getItemMasterSid() {
        return _nationalAssumptionsProj.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this national assumptions proj.
    *
    * @param itemMasterSid the item master sid of this national assumptions proj
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _nationalAssumptionsProj.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the price type of this national assumptions proj.
    *
    * @return the price type of this national assumptions proj
    */
    @Override
    public java.lang.String getPriceType() {
        return _nationalAssumptionsProj.getPriceType();
    }

    /**
    * Sets the price type of this national assumptions proj.
    *
    * @param priceType the price type of this national assumptions proj
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _nationalAssumptionsProj.setPriceType(priceType);
    }

    /**
    * Returns the projection price of this national assumptions proj.
    *
    * @return the projection price of this national assumptions proj
    */
    @Override
    public double getProjectionPrice() {
        return _nationalAssumptionsProj.getProjectionPrice();
    }

    /**
    * Sets the projection price of this national assumptions proj.
    *
    * @param projectionPrice the projection price of this national assumptions proj
    */
    @Override
    public void setProjectionPrice(double projectionPrice) {
        _nationalAssumptionsProj.setProjectionPrice(projectionPrice);
    }

    @Override
    public boolean isNew() {
        return _nationalAssumptionsProj.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nationalAssumptionsProj.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nationalAssumptionsProj.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nationalAssumptionsProj.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nationalAssumptionsProj.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nationalAssumptionsProj.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nationalAssumptionsProj.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nationalAssumptionsProj.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nationalAssumptionsProj.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nationalAssumptionsProj.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nationalAssumptionsProj.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NationalAssumptionsProjWrapper((NationalAssumptionsProj) _nationalAssumptionsProj.clone());
    }

    @Override
    public int compareTo(NationalAssumptionsProj nationalAssumptionsProj) {
        return _nationalAssumptionsProj.compareTo(nationalAssumptionsProj);
    }

    @Override
    public int hashCode() {
        return _nationalAssumptionsProj.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NationalAssumptionsProj> toCacheModel() {
        return _nationalAssumptionsProj.toCacheModel();
    }

    @Override
    public NationalAssumptionsProj toEscapedModel() {
        return new NationalAssumptionsProjWrapper(_nationalAssumptionsProj.toEscapedModel());
    }

    @Override
    public NationalAssumptionsProj toUnescapedModel() {
        return new NationalAssumptionsProjWrapper(_nationalAssumptionsProj.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nationalAssumptionsProj.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nationalAssumptionsProj.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nationalAssumptionsProj.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NationalAssumptionsProjWrapper)) {
            return false;
        }

        NationalAssumptionsProjWrapper nationalAssumptionsProjWrapper = (NationalAssumptionsProjWrapper) obj;

        if (Validator.equals(_nationalAssumptionsProj,
                    nationalAssumptionsProjWrapper._nationalAssumptionsProj)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NationalAssumptionsProj getWrappedNationalAssumptionsProj() {
        return _nationalAssumptionsProj;
    }

    @Override
    public NationalAssumptionsProj getWrappedModel() {
        return _nationalAssumptionsProj;
    }

    @Override
    public void resetOriginalValues() {
        _nationalAssumptionsProj.resetOriginalValues();
    }
}
