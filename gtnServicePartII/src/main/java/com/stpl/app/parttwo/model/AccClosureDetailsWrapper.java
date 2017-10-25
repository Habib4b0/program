package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccClosureDetails}.
 * </p>
 *
 * @author
 * @see AccClosureDetails
 * @generated
 */
public class AccClosureDetailsWrapper implements AccClosureDetails,
    ModelWrapper<AccClosureDetails> {
    private AccClosureDetails _accClosureDetails;

    public AccClosureDetailsWrapper(AccClosureDetails accClosureDetails) {
        _accClosureDetails = accClosureDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return AccClosureDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AccClosureDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("accClosureDetailsSid", getAccClosureDetailsSid());
        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("rsModelSid", getRsModelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer accClosureDetailsSid = (Integer) attributes.get(
                "accClosureDetailsSid");

        if (accClosureDetailsSid != null) {
            setAccClosureDetailsSid(accClosureDetailsSid);
        }

        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }
    }

    /**
    * Returns the primary key of this acc closure details.
    *
    * @return the primary key of this acc closure details
    */
    @Override
    public int getPrimaryKey() {
        return _accClosureDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this acc closure details.
    *
    * @param primaryKey the primary key of this acc closure details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _accClosureDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the acc closure details sid of this acc closure details.
    *
    * @return the acc closure details sid of this acc closure details
    */
    @Override
    public int getAccClosureDetailsSid() {
        return _accClosureDetails.getAccClosureDetailsSid();
    }

    /**
    * Sets the acc closure details sid of this acc closure details.
    *
    * @param accClosureDetailsSid the acc closure details sid of this acc closure details
    */
    @Override
    public void setAccClosureDetailsSid(int accClosureDetailsSid) {
        _accClosureDetails.setAccClosureDetailsSid(accClosureDetailsSid);
    }

    /**
    * Returns the ccp details sid of this acc closure details.
    *
    * @return the ccp details sid of this acc closure details
    */
    @Override
    public int getCcpDetailsSid() {
        return _accClosureDetails.getCcpDetailsSid();
    }

    /**
    * Sets the ccp details sid of this acc closure details.
    *
    * @param ccpDetailsSid the ccp details sid of this acc closure details
    */
    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _accClosureDetails.setCcpDetailsSid(ccpDetailsSid);
    }

    /**
    * Returns the acc closure master sid of this acc closure details.
    *
    * @return the acc closure master sid of this acc closure details
    */
    @Override
    public int getAccClosureMasterSid() {
        return _accClosureDetails.getAccClosureMasterSid();
    }

    /**
    * Sets the acc closure master sid of this acc closure details.
    *
    * @param accClosureMasterSid the acc closure master sid of this acc closure details
    */
    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureDetails.setAccClosureMasterSid(accClosureMasterSid);
    }

    /**
    * Returns the rs model sid of this acc closure details.
    *
    * @return the rs model sid of this acc closure details
    */
    @Override
    public int getRsModelSid() {
        return _accClosureDetails.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this acc closure details.
    *
    * @param rsModelSid the rs model sid of this acc closure details
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _accClosureDetails.setRsModelSid(rsModelSid);
    }

    @Override
    public boolean isNew() {
        return _accClosureDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _accClosureDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _accClosureDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _accClosureDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _accClosureDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _accClosureDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _accClosureDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _accClosureDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _accClosureDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _accClosureDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _accClosureDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AccClosureDetailsWrapper((AccClosureDetails) _accClosureDetails.clone());
    }

    @Override
    public int compareTo(AccClosureDetails accClosureDetails) {
        return _accClosureDetails.compareTo(accClosureDetails);
    }

    @Override
    public int hashCode() {
        return _accClosureDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AccClosureDetails> toCacheModel() {
        return _accClosureDetails.toCacheModel();
    }

    @Override
    public AccClosureDetails toEscapedModel() {
        return new AccClosureDetailsWrapper(_accClosureDetails.toEscapedModel());
    }

    @Override
    public AccClosureDetails toUnescapedModel() {
        return new AccClosureDetailsWrapper(_accClosureDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _accClosureDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _accClosureDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _accClosureDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AccClosureDetailsWrapper)) {
            return false;
        }

        AccClosureDetailsWrapper accClosureDetailsWrapper = (AccClosureDetailsWrapper) obj;

        if (Validator.equals(_accClosureDetails,
                    accClosureDetailsWrapper._accClosureDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AccClosureDetails getWrappedAccClosureDetails() {
        return _accClosureDetails;
    }

    @Override
    public AccClosureDetails getWrappedModel() {
        return _accClosureDetails;
    }

    @Override
    public void resetOriginalValues() {
        _accClosureDetails.resetOriginalValues();
    }
}
