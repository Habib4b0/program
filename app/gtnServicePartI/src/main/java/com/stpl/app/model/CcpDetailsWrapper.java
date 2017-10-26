package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CcpDetails}.
 * </p>
 *
 * @author
 * @see CcpDetails
 * @generated
 */
public class CcpDetailsWrapper implements CcpDetails, ModelWrapper<CcpDetails> {
    private CcpDetails _ccpDetails;

    public CcpDetailsWrapper(CcpDetails ccpDetails) {
        _ccpDetails = ccpDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CcpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CcpDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    /**
    * Returns the primary key of this ccp details.
    *
    * @return the primary key of this ccp details
    */
    @Override
    public int getPrimaryKey() {
        return _ccpDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ccp details.
    *
    * @param primaryKey the primary key of this ccp details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ccpDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item master sid of this ccp details.
    *
    * @return the item master sid of this ccp details
    */
    @Override
    public int getItemMasterSid() {
        return _ccpDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this ccp details.
    *
    * @param itemMasterSid the item master sid of this ccp details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _ccpDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the contract master sid of this ccp details.
    *
    * @return the contract master sid of this ccp details
    */
    @Override
    public int getContractMasterSid() {
        return _ccpDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this ccp details.
    *
    * @param contractMasterSid the contract master sid of this ccp details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _ccpDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the ccp details sid of this ccp details.
    *
    * @return the ccp details sid of this ccp details
    */
    @Override
    public int getCcpDetailsSid() {
        return _ccpDetails.getCcpDetailsSid();
    }

    /**
    * Sets the ccp details sid of this ccp details.
    *
    * @param ccpDetailsSid the ccp details sid of this ccp details
    */
    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetails.setCcpDetailsSid(ccpDetailsSid);
    }

    /**
    * Returns the company master sid of this ccp details.
    *
    * @return the company master sid of this ccp details
    */
    @Override
    public int getCompanyMasterSid() {
        return _ccpDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this ccp details.
    *
    * @param companyMasterSid the company master sid of this ccp details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _ccpDetails.setCompanyMasterSid(companyMasterSid);
    }

    @Override
    public boolean isNew() {
        return _ccpDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ccpDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ccpDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ccpDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ccpDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ccpDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ccpDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ccpDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ccpDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ccpDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ccpDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CcpDetailsWrapper((CcpDetails) _ccpDetails.clone());
    }

    @Override
    public int compareTo(CcpDetails ccpDetails) {
        return _ccpDetails.compareTo(ccpDetails);
    }

    @Override
    public int hashCode() {
        return _ccpDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CcpDetails> toCacheModel() {
        return _ccpDetails.toCacheModel();
    }

    @Override
    public CcpDetails toEscapedModel() {
        return new CcpDetailsWrapper(_ccpDetails.toEscapedModel());
    }

    @Override
    public CcpDetails toUnescapedModel() {
        return new CcpDetailsWrapper(_ccpDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ccpDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ccpDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ccpDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CcpDetailsWrapper)) {
            return false;
        }

        CcpDetailsWrapper ccpDetailsWrapper = (CcpDetailsWrapper) obj;

        if (Validator.equals(_ccpDetails, ccpDetailsWrapper._ccpDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CcpDetails getWrappedCcpDetails() {
        return _ccpDetails;
    }

    @Override
    public CcpDetails getWrappedModel() {
        return _ccpDetails;
    }

    @Override
    public void resetOriginalValues() {
        _ccpDetails.resetOriginalValues();
    }
}
