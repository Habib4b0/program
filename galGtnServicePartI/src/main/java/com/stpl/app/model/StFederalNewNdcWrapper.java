package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StFederalNewNdc}.
 * </p>
 *
 * @author
 * @see StFederalNewNdc
 * @generated
 */
public class StFederalNewNdcWrapper implements StFederalNewNdc,
    ModelWrapper<StFederalNewNdc> {
    private StFederalNewNdc _stFederalNewNdc;

    public StFederalNewNdcWrapper(StFederalNewNdc stFederalNewNdc) {
        _stFederalNewNdc = stFederalNewNdc;
    }

    @Override
    public Class<?> getModelClass() {
        return StFederalNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return StFederalNewNdc.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("fss", getFss());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("wacPrice", getWacPrice());
        attributes.put("sessionId", getSessionId());
        attributes.put("nonFamp", getNonFamp());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double fss = (Double) attributes.get("fss");

        if (fss != null) {
            setFss(fss);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Double wacPrice = (Double) attributes.get("wacPrice");

        if (wacPrice != null) {
            setWacPrice(wacPrice);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double nonFamp = (Double) attributes.get("nonFamp");

        if (nonFamp != null) {
            setNonFamp(nonFamp);
        }
    }

    /**
    * Returns the primary key of this st federal new ndc.
    *
    * @return the primary key of this st federal new ndc
    */
    @Override
    public com.stpl.app.service.persistence.StFederalNewNdcPK getPrimaryKey() {
        return _stFederalNewNdc.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st federal new ndc.
    *
    * @param primaryKey the primary key of this st federal new ndc
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StFederalNewNdcPK primaryKey) {
        _stFederalNewNdc.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the fss of this st federal new ndc.
    *
    * @return the fss of this st federal new ndc
    */
    @Override
    public double getFss() {
        return _stFederalNewNdc.getFss();
    }

    /**
    * Sets the fss of this st federal new ndc.
    *
    * @param fss the fss of this st federal new ndc
    */
    @Override
    public void setFss(double fss) {
        _stFederalNewNdc.setFss(fss);
    }

    /**
    * Returns the user ID of this st federal new ndc.
    *
    * @return the user ID of this st federal new ndc
    */
    @Override
    public int getUserId() {
        return _stFederalNewNdc.getUserId();
    }

    /**
    * Sets the user ID of this st federal new ndc.
    *
    * @param userId the user ID of this st federal new ndc
    */
    @Override
    public void setUserId(int userId) {
        _stFederalNewNdc.setUserId(userId);
    }

    /**
    * Returns the last modified date of this st federal new ndc.
    *
    * @return the last modified date of this st federal new ndc
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stFederalNewNdc.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st federal new ndc.
    *
    * @param lastModifiedDate the last modified date of this st federal new ndc
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stFederalNewNdc.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the item master sid of this st federal new ndc.
    *
    * @return the item master sid of this st federal new ndc
    */
    @Override
    public int getItemMasterSid() {
        return _stFederalNewNdc.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this st federal new ndc.
    *
    * @param itemMasterSid the item master sid of this st federal new ndc
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _stFederalNewNdc.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the wac price of this st federal new ndc.
    *
    * @return the wac price of this st federal new ndc
    */
    @Override
    public double getWacPrice() {
        return _stFederalNewNdc.getWacPrice();
    }

    /**
    * Sets the wac price of this st federal new ndc.
    *
    * @param wacPrice the wac price of this st federal new ndc
    */
    @Override
    public void setWacPrice(double wacPrice) {
        _stFederalNewNdc.setWacPrice(wacPrice);
    }

    /**
    * Returns the session ID of this st federal new ndc.
    *
    * @return the session ID of this st federal new ndc
    */
    @Override
    public int getSessionId() {
        return _stFederalNewNdc.getSessionId();
    }

    /**
    * Sets the session ID of this st federal new ndc.
    *
    * @param sessionId the session ID of this st federal new ndc
    */
    @Override
    public void setSessionId(int sessionId) {
        _stFederalNewNdc.setSessionId(sessionId);
    }

    /**
    * Returns the non famp of this st federal new ndc.
    *
    * @return the non famp of this st federal new ndc
    */
    @Override
    public double getNonFamp() {
        return _stFederalNewNdc.getNonFamp();
    }

    /**
    * Sets the non famp of this st federal new ndc.
    *
    * @param nonFamp the non famp of this st federal new ndc
    */
    @Override
    public void setNonFamp(double nonFamp) {
        _stFederalNewNdc.setNonFamp(nonFamp);
    }

    @Override
    public boolean isNew() {
        return _stFederalNewNdc.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stFederalNewNdc.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stFederalNewNdc.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stFederalNewNdc.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stFederalNewNdc.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stFederalNewNdc.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stFederalNewNdc.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stFederalNewNdc.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stFederalNewNdc.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stFederalNewNdc.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stFederalNewNdc.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StFederalNewNdcWrapper((StFederalNewNdc) _stFederalNewNdc.clone());
    }

    @Override
    public int compareTo(StFederalNewNdc stFederalNewNdc) {
        return _stFederalNewNdc.compareTo(stFederalNewNdc);
    }

    @Override
    public int hashCode() {
        return _stFederalNewNdc.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StFederalNewNdc> toCacheModel() {
        return _stFederalNewNdc.toCacheModel();
    }

    @Override
    public StFederalNewNdc toEscapedModel() {
        return new StFederalNewNdcWrapper(_stFederalNewNdc.toEscapedModel());
    }

    @Override
    public StFederalNewNdc toUnescapedModel() {
        return new StFederalNewNdcWrapper(_stFederalNewNdc.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stFederalNewNdc.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stFederalNewNdc.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stFederalNewNdc.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StFederalNewNdcWrapper)) {
            return false;
        }

        StFederalNewNdcWrapper stFederalNewNdcWrapper = (StFederalNewNdcWrapper) obj;

        if (Validator.equals(_stFederalNewNdc,
                    stFederalNewNdcWrapper._stFederalNewNdc)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StFederalNewNdc getWrappedStFederalNewNdc() {
        return _stFederalNewNdc;
    }

    @Override
    public StFederalNewNdc getWrappedModel() {
        return _stFederalNewNdc;
    }

    @Override
    public void resetOriginalValues() {
        _stFederalNewNdc.resetOriginalValues();
    }
}
