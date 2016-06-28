package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffDetails}.
 * </p>
 *
 * @author
 * @see CffDetails
 * @generated
 */
public class CffDetailsWrapper implements CffDetails, ModelWrapper<CffDetails> {
    private CffDetails _cffDetails;

    public CffDetailsWrapper(CffDetails cffDetails) {
        _cffDetails = cffDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CffDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CffDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("cffDetailsSid", getCffDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer cffDetailsSid = (Integer) attributes.get("cffDetailsSid");

        if (cffDetailsSid != null) {
            setCffDetailsSid(cffDetailsSid);
        }
    }

    /**
    * Returns the primary key of this cff details.
    *
    * @return the primary key of this cff details
    */
    @Override
    public int getPrimaryKey() {
        return _cffDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff details.
    *
    * @param primaryKey the primary key of this cff details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ccp details sid of this cff details.
    *
    * @return the ccp details sid of this cff details
    */
    @Override
    public int getCcpDetailsSid() {
        return _cffDetails.getCcpDetailsSid();
    }

    /**
    * Sets the ccp details sid of this cff details.
    *
    * @param ccpDetailsSid the ccp details sid of this cff details
    */
    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _cffDetails.setCcpDetailsSid(ccpDetailsSid);
    }

    /**
    * Returns the projection master sid of this cff details.
    *
    * @return the projection master sid of this cff details
    */
    @Override
    public int getProjectionMasterSid() {
        return _cffDetails.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this cff details.
    *
    * @param projectionMasterSid the projection master sid of this cff details
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _cffDetails.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the cff master sid of this cff details.
    *
    * @return the cff master sid of this cff details
    */
    @Override
    public int getCffMasterSid() {
        return _cffDetails.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff details.
    *
    * @param cffMasterSid the cff master sid of this cff details
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffDetails.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the inbound status of this cff details.
    *
    * @return the inbound status of this cff details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _cffDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this cff details.
    *
    * @param inboundStatus the inbound status of this cff details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _cffDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the cff details sid of this cff details.
    *
    * @return the cff details sid of this cff details
    */
    @Override
    public int getCffDetailsSid() {
        return _cffDetails.getCffDetailsSid();
    }

    /**
    * Sets the cff details sid of this cff details.
    *
    * @param cffDetailsSid the cff details sid of this cff details
    */
    @Override
    public void setCffDetailsSid(int cffDetailsSid) {
        _cffDetails.setCffDetailsSid(cffDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _cffDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffDetailsWrapper((CffDetails) _cffDetails.clone());
    }

    @Override
    public int compareTo(CffDetails cffDetails) {
        return _cffDetails.compareTo(cffDetails);
    }

    @Override
    public int hashCode() {
        return _cffDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffDetails> toCacheModel() {
        return _cffDetails.toCacheModel();
    }

    @Override
    public CffDetails toEscapedModel() {
        return new CffDetailsWrapper(_cffDetails.toEscapedModel());
    }

    @Override
    public CffDetails toUnescapedModel() {
        return new CffDetailsWrapper(_cffDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffDetailsWrapper)) {
            return false;
        }

        CffDetailsWrapper cffDetailsWrapper = (CffDetailsWrapper) obj;

        if (Validator.equals(_cffDetails, cffDetailsWrapper._cffDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffDetails getWrappedCffDetails() {
        return _cffDetails;
    }

    @Override
    public CffDetails getWrappedModel() {
        return _cffDetails;
    }

    @Override
    public void resetOriginalValues() {
        _cffDetails.resetOriginalValues();
    }
}
