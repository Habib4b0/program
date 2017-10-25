package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NaProjDetails}.
 * </p>
 *
 * @author
 * @see NaProjDetails
 * @generated
 */
public class NaProjDetailsWrapper implements NaProjDetails,
    ModelWrapper<NaProjDetails> {
    private NaProjDetails _naProjDetails;

    public NaProjDetailsWrapper(NaProjDetails naProjDetails) {
        _naProjDetails = naProjDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return NaProjDetails.class;
    }

    @Override
    public String getModelClassName() {
        return NaProjDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("naProjMasterSid", getNaProjMasterSid());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }
    }

    /**
    * Returns the primary key of this na proj details.
    *
    * @return the primary key of this na proj details
    */
    @Override
    public int getPrimaryKey() {
        return _naProjDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this na proj details.
    *
    * @param primaryKey the primary key of this na proj details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _naProjDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item master sid of this na proj details.
    *
    * @return the item master sid of this na proj details
    */
    @Override
    public int getItemMasterSid() {
        return _naProjDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this na proj details.
    *
    * @param itemMasterSid the item master sid of this na proj details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _naProjDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the na proj master sid of this na proj details.
    *
    * @return the na proj master sid of this na proj details
    */
    @Override
    public int getNaProjMasterSid() {
        return _naProjDetails.getNaProjMasterSid();
    }

    /**
    * Sets the na proj master sid of this na proj details.
    *
    * @param naProjMasterSid the na proj master sid of this na proj details
    */
    @Override
    public void setNaProjMasterSid(int naProjMasterSid) {
        _naProjDetails.setNaProjMasterSid(naProjMasterSid);
    }

    /**
    * Returns the na proj details sid of this na proj details.
    *
    * @return the na proj details sid of this na proj details
    */
    @Override
    public int getNaProjDetailsSid() {
        return _naProjDetails.getNaProjDetailsSid();
    }

    /**
    * Sets the na proj details sid of this na proj details.
    *
    * @param naProjDetailsSid the na proj details sid of this na proj details
    */
    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _naProjDetails.setNaProjDetailsSid(naProjDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _naProjDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _naProjDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _naProjDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _naProjDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _naProjDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _naProjDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _naProjDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _naProjDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _naProjDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _naProjDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _naProjDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NaProjDetailsWrapper((NaProjDetails) _naProjDetails.clone());
    }

    @Override
    public int compareTo(NaProjDetails naProjDetails) {
        return _naProjDetails.compareTo(naProjDetails);
    }

    @Override
    public int hashCode() {
        return _naProjDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NaProjDetails> toCacheModel() {
        return _naProjDetails.toCacheModel();
    }

    @Override
    public NaProjDetails toEscapedModel() {
        return new NaProjDetailsWrapper(_naProjDetails.toEscapedModel());
    }

    @Override
    public NaProjDetails toUnescapedModel() {
        return new NaProjDetailsWrapper(_naProjDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _naProjDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _naProjDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _naProjDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NaProjDetailsWrapper)) {
            return false;
        }

        NaProjDetailsWrapper naProjDetailsWrapper = (NaProjDetailsWrapper) obj;

        if (Validator.equals(_naProjDetails, naProjDetailsWrapper._naProjDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NaProjDetails getWrappedNaProjDetails() {
        return _naProjDetails;
    }

    @Override
    public NaProjDetails getWrappedModel() {
        return _naProjDetails;
    }

    @Override
    public void resetOriginalValues() {
        _naProjDetails.resetOriginalValues();
    }
}
