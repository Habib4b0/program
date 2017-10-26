package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NationalAssumptionsActuals}.
 * </p>
 *
 * @author
 * @see NationalAssumptionsActuals
 * @generated
 */
public class NationalAssumptionsActualsWrapper
    implements NationalAssumptionsActuals,
        ModelWrapper<NationalAssumptionsActuals> {
    private NationalAssumptionsActuals _nationalAssumptionsActuals;

    public NationalAssumptionsActualsWrapper(
        NationalAssumptionsActuals nationalAssumptionsActuals) {
        _nationalAssumptionsActuals = nationalAssumptionsActuals;
    }

    @Override
    public Class<?> getModelClass() {
        return NationalAssumptionsActuals.class;
    }

    @Override
    public String getModelClassName() {
        return NationalAssumptionsActuals.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("priceType", getPriceType());
        attributes.put("actualPrice", getActualPrice());

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

        Double actualPrice = (Double) attributes.get("actualPrice");

        if (actualPrice != null) {
            setActualPrice(actualPrice);
        }
    }

    /**
    * Returns the primary key of this national assumptions actuals.
    *
    * @return the primary key of this national assumptions actuals
    */
    @Override
    public com.stpl.app.service.persistence.NationalAssumptionsActualsPK getPrimaryKey() {
        return _nationalAssumptionsActuals.getPrimaryKey();
    }

    /**
    * Sets the primary key of this national assumptions actuals.
    *
    * @param primaryKey the primary key of this national assumptions actuals
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.NationalAssumptionsActualsPK primaryKey) {
        _nationalAssumptionsActuals.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the period sid of this national assumptions actuals.
    *
    * @return the period sid of this national assumptions actuals
    */
    @Override
    public int getPeriodSid() {
        return _nationalAssumptionsActuals.getPeriodSid();
    }

    /**
    * Sets the period sid of this national assumptions actuals.
    *
    * @param periodSid the period sid of this national assumptions actuals
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _nationalAssumptionsActuals.setPeriodSid(periodSid);
    }

    /**
    * Returns the item master sid of this national assumptions actuals.
    *
    * @return the item master sid of this national assumptions actuals
    */
    @Override
    public int getItemMasterSid() {
        return _nationalAssumptionsActuals.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this national assumptions actuals.
    *
    * @param itemMasterSid the item master sid of this national assumptions actuals
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _nationalAssumptionsActuals.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the price type of this national assumptions actuals.
    *
    * @return the price type of this national assumptions actuals
    */
    @Override
    public java.lang.String getPriceType() {
        return _nationalAssumptionsActuals.getPriceType();
    }

    /**
    * Sets the price type of this national assumptions actuals.
    *
    * @param priceType the price type of this national assumptions actuals
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _nationalAssumptionsActuals.setPriceType(priceType);
    }

    /**
    * Returns the actual price of this national assumptions actuals.
    *
    * @return the actual price of this national assumptions actuals
    */
    @Override
    public double getActualPrice() {
        return _nationalAssumptionsActuals.getActualPrice();
    }

    /**
    * Sets the actual price of this national assumptions actuals.
    *
    * @param actualPrice the actual price of this national assumptions actuals
    */
    @Override
    public void setActualPrice(double actualPrice) {
        _nationalAssumptionsActuals.setActualPrice(actualPrice);
    }

    @Override
    public boolean isNew() {
        return _nationalAssumptionsActuals.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nationalAssumptionsActuals.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nationalAssumptionsActuals.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nationalAssumptionsActuals.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nationalAssumptionsActuals.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nationalAssumptionsActuals.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nationalAssumptionsActuals.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nationalAssumptionsActuals.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nationalAssumptionsActuals.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nationalAssumptionsActuals.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nationalAssumptionsActuals.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NationalAssumptionsActualsWrapper((NationalAssumptionsActuals) _nationalAssumptionsActuals.clone());
    }

    @Override
    public int compareTo(NationalAssumptionsActuals nationalAssumptionsActuals) {
        return _nationalAssumptionsActuals.compareTo(nationalAssumptionsActuals);
    }

    @Override
    public int hashCode() {
        return _nationalAssumptionsActuals.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NationalAssumptionsActuals> toCacheModel() {
        return _nationalAssumptionsActuals.toCacheModel();
    }

    @Override
    public NationalAssumptionsActuals toEscapedModel() {
        return new NationalAssumptionsActualsWrapper(_nationalAssumptionsActuals.toEscapedModel());
    }

    @Override
    public NationalAssumptionsActuals toUnescapedModel() {
        return new NationalAssumptionsActualsWrapper(_nationalAssumptionsActuals.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nationalAssumptionsActuals.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nationalAssumptionsActuals.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nationalAssumptionsActuals.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NationalAssumptionsActualsWrapper)) {
            return false;
        }

        NationalAssumptionsActualsWrapper nationalAssumptionsActualsWrapper = (NationalAssumptionsActualsWrapper) obj;

        if (Validator.equals(_nationalAssumptionsActuals,
                    nationalAssumptionsActualsWrapper._nationalAssumptionsActuals)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NationalAssumptionsActuals getWrappedNationalAssumptionsActuals() {
        return _nationalAssumptionsActuals;
    }

    @Override
    public NationalAssumptionsActuals getWrappedModel() {
        return _nationalAssumptionsActuals;
    }

    @Override
    public void resetOriginalValues() {
        _nationalAssumptionsActuals.resetOriginalValues();
    }
}
