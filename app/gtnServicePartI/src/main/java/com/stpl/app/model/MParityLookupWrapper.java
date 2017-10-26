package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MParityLookup}.
 * </p>
 *
 * @author
 * @see MParityLookup
 * @generated
 */
public class MParityLookupWrapper implements MParityLookup,
    ModelWrapper<MParityLookup> {
    private MParityLookup _mParityLookup;

    public MParityLookupWrapper(MParityLookup mParityLookup) {
        _mParityLookup = mParityLookup;
    }

    @Override
    public Class<?> getModelClass() {
        return MParityLookup.class;
    }

    @Override
    public String getModelClassName() {
        return MParityLookup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("marketType", getMarketType());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("mParityLookupSid", getMParityLookupSid());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String marketType = (String) attributes.get("marketType");

        if (marketType != null) {
            setMarketType(marketType);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer mParityLookupSid = (Integer) attributes.get("mParityLookupSid");

        if (mParityLookupSid != null) {
            setMParityLookupSid(mParityLookupSid);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }
    }

    /**
    * Returns the primary key of this m parity lookup.
    *
    * @return the primary key of this m parity lookup
    */
    @Override
    public int getPrimaryKey() {
        return _mParityLookup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this m parity lookup.
    *
    * @param primaryKey the primary key of this m parity lookup
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _mParityLookup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contract master sid of this m parity lookup.
    *
    * @return the contract master sid of this m parity lookup
    */
    @Override
    public int getContractMasterSid() {
        return _mParityLookup.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this m parity lookup.
    *
    * @param contractMasterSid the contract master sid of this m parity lookup
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _mParityLookup.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the market type of this m parity lookup.
    *
    * @return the market type of this m parity lookup
    */
    @Override
    public java.lang.String getMarketType() {
        return _mParityLookup.getMarketType();
    }

    /**
    * Sets the market type of this m parity lookup.
    *
    * @param marketType the market type of this m parity lookup
    */
    @Override
    public void setMarketType(java.lang.String marketType) {
        _mParityLookup.setMarketType(marketType);
    }

    /**
    * Returns the item master sid of this m parity lookup.
    *
    * @return the item master sid of this m parity lookup
    */
    @Override
    public int getItemMasterSid() {
        return _mParityLookup.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this m parity lookup.
    *
    * @param itemMasterSid the item master sid of this m parity lookup
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _mParityLookup.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the m parity lookup sid of this m parity lookup.
    *
    * @return the m parity lookup sid of this m parity lookup
    */
    @Override
    public int getMParityLookupSid() {
        return _mParityLookup.getMParityLookupSid();
    }

    /**
    * Sets the m parity lookup sid of this m parity lookup.
    *
    * @param mParityLookupSid the m parity lookup sid of this m parity lookup
    */
    @Override
    public void setMParityLookupSid(int mParityLookupSid) {
        _mParityLookup.setMParityLookupSid(mParityLookupSid);
    }

    /**
    * Returns the projection details sid of this m parity lookup.
    *
    * @return the projection details sid of this m parity lookup
    */
    @Override
    public int getProjectionDetailsSid() {
        return _mParityLookup.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this m parity lookup.
    *
    * @param projectionDetailsSid the projection details sid of this m parity lookup
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _mParityLookup.setProjectionDetailsSid(projectionDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _mParityLookup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _mParityLookup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _mParityLookup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _mParityLookup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _mParityLookup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _mParityLookup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _mParityLookup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _mParityLookup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _mParityLookup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _mParityLookup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _mParityLookup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MParityLookupWrapper((MParityLookup) _mParityLookup.clone());
    }

    @Override
    public int compareTo(MParityLookup mParityLookup) {
        return _mParityLookup.compareTo(mParityLookup);
    }

    @Override
    public int hashCode() {
        return _mParityLookup.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MParityLookup> toCacheModel() {
        return _mParityLookup.toCacheModel();
    }

    @Override
    public MParityLookup toEscapedModel() {
        return new MParityLookupWrapper(_mParityLookup.toEscapedModel());
    }

    @Override
    public MParityLookup toUnescapedModel() {
        return new MParityLookupWrapper(_mParityLookup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _mParityLookup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _mParityLookup.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _mParityLookup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MParityLookupWrapper)) {
            return false;
        }

        MParityLookupWrapper mParityLookupWrapper = (MParityLookupWrapper) obj;

        if (Validator.equals(_mParityLookup, mParityLookupWrapper._mParityLookup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MParityLookup getWrappedMParityLookup() {
        return _mParityLookup;
    }

    @Override
    public MParityLookup getWrappedModel() {
        return _mParityLookup;
    }

    @Override
    public void resetOriginalValues() {
        _mParityLookup.resetOriginalValues();
    }
}
