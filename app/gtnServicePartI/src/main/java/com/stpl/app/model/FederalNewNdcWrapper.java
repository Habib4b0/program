package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FederalNewNdc}.
 * </p>
 *
 * @author
 * @see FederalNewNdc
 * @generated
 */
public class FederalNewNdcWrapper implements FederalNewNdc,
    ModelWrapper<FederalNewNdc> {
    private FederalNewNdc _federalNewNdc;

    public FederalNewNdcWrapper(FederalNewNdc federalNewNdc) {
        _federalNewNdc = federalNewNdc;
    }

    @Override
    public Class<?> getModelClass() {
        return FederalNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return FederalNewNdc.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("fss", getFss());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("wacPrice", getWacPrice());
        attributes.put("nonFamp", getNonFamp());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double fss = (Double) attributes.get("fss");

        if (fss != null) {
            setFss(fss);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Double wacPrice = (Double) attributes.get("wacPrice");

        if (wacPrice != null) {
            setWacPrice(wacPrice);
        }

        Double nonFamp = (Double) attributes.get("nonFamp");

        if (nonFamp != null) {
            setNonFamp(nonFamp);
        }
    }

    /**
    * Returns the primary key of this federal new ndc.
    *
    * @return the primary key of this federal new ndc
    */
    @Override
    public int getPrimaryKey() {
        return _federalNewNdc.getPrimaryKey();
    }

    /**
    * Sets the primary key of this federal new ndc.
    *
    * @param primaryKey the primary key of this federal new ndc
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _federalNewNdc.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the fss of this federal new ndc.
    *
    * @return the fss of this federal new ndc
    */
    @Override
    public double getFss() {
        return _federalNewNdc.getFss();
    }

    /**
    * Sets the fss of this federal new ndc.
    *
    * @param fss the fss of this federal new ndc
    */
    @Override
    public void setFss(double fss) {
        _federalNewNdc.setFss(fss);
    }

    /**
    * Returns the item master sid of this federal new ndc.
    *
    * @return the item master sid of this federal new ndc
    */
    @Override
    public int getItemMasterSid() {
        return _federalNewNdc.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this federal new ndc.
    *
    * @param itemMasterSid the item master sid of this federal new ndc
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _federalNewNdc.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the wac price of this federal new ndc.
    *
    * @return the wac price of this federal new ndc
    */
    @Override
    public double getWacPrice() {
        return _federalNewNdc.getWacPrice();
    }

    /**
    * Sets the wac price of this federal new ndc.
    *
    * @param wacPrice the wac price of this federal new ndc
    */
    @Override
    public void setWacPrice(double wacPrice) {
        _federalNewNdc.setWacPrice(wacPrice);
    }

    /**
    * Returns the non famp of this federal new ndc.
    *
    * @return the non famp of this federal new ndc
    */
    @Override
    public double getNonFamp() {
        return _federalNewNdc.getNonFamp();
    }

    /**
    * Sets the non famp of this federal new ndc.
    *
    * @param nonFamp the non famp of this federal new ndc
    */
    @Override
    public void setNonFamp(double nonFamp) {
        _federalNewNdc.setNonFamp(nonFamp);
    }

    @Override
    public boolean isNew() {
        return _federalNewNdc.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _federalNewNdc.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _federalNewNdc.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _federalNewNdc.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _federalNewNdc.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _federalNewNdc.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _federalNewNdc.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _federalNewNdc.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _federalNewNdc.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _federalNewNdc.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _federalNewNdc.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FederalNewNdcWrapper((FederalNewNdc) _federalNewNdc.clone());
    }

    @Override
    public int compareTo(FederalNewNdc federalNewNdc) {
        return _federalNewNdc.compareTo(federalNewNdc);
    }

    @Override
    public int hashCode() {
        return _federalNewNdc.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<FederalNewNdc> toCacheModel() {
        return _federalNewNdc.toCacheModel();
    }

    @Override
    public FederalNewNdc toEscapedModel() {
        return new FederalNewNdcWrapper(_federalNewNdc.toEscapedModel());
    }

    @Override
    public FederalNewNdc toUnescapedModel() {
        return new FederalNewNdcWrapper(_federalNewNdc.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _federalNewNdc.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _federalNewNdc.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _federalNewNdc.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FederalNewNdcWrapper)) {
            return false;
        }

        FederalNewNdcWrapper federalNewNdcWrapper = (FederalNewNdcWrapper) obj;

        if (Validator.equals(_federalNewNdc, federalNewNdcWrapper._federalNewNdc)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public FederalNewNdc getWrappedFederalNewNdc() {
        return _federalNewNdc;
    }

    @Override
    public FederalNewNdc getWrappedModel() {
        return _federalNewNdc;
    }

    @Override
    public void resetOriginalValues() {
        _federalNewNdc.resetOriginalValues();
    }
}
