package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StNmPpaProjectionMaster}.
 * </p>
 *
 * @author
 * @see StNmPpaProjectionMaster
 * @generated
 */
public class StNmPpaProjectionMasterWrapper implements StNmPpaProjectionMaster,
    ModelWrapper<StNmPpaProjectionMaster> {
    private StNmPpaProjectionMaster _stNmPpaProjectionMaster;

    public StNmPpaProjectionMasterWrapper(
        StNmPpaProjectionMaster stNmPpaProjectionMaster) {
        _stNmPpaProjectionMaster = stNmPpaProjectionMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return StNmPpaProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StNmPpaProjectionMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("userGroup", getUserGroup());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("sessionId", getSessionId());
        attributes.put("priceBasis", getPriceBasis());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("actualPriceCap", getActualPriceCap());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String userGroup = (String) attributes.get("userGroup");

        if (userGroup != null) {
            setUserGroup(userGroup);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String priceBasis = (String) attributes.get("priceBasis");

        if (priceBasis != null) {
            setPriceBasis(priceBasis);
        }

        Date priceProtectionEndDate = (Date) attributes.get(
                "priceProtectionEndDate");

        if (priceProtectionEndDate != null) {
            setPriceProtectionEndDate(priceProtectionEndDate);
        }

        Date priceProtectionStartDate = (Date) attributes.get(
                "priceProtectionStartDate");

        if (priceProtectionStartDate != null) {
            setPriceProtectionStartDate(priceProtectionStartDate);
        }

        Double actualPriceCap = (Double) attributes.get("actualPriceCap");

        if (actualPriceCap != null) {
            setActualPriceCap(actualPriceCap);
        }
    }

    /**
    * Returns the primary key of this st nm ppa projection master.
    *
    * @return the primary key of this st nm ppa projection master
    */
    @Override
    public com.stpl.app.service.persistence.StNmPpaProjectionMasterPK getPrimaryKey() {
        return _stNmPpaProjectionMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st nm ppa projection master.
    *
    * @param primaryKey the primary key of this st nm ppa projection master
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StNmPpaProjectionMasterPK primaryKey) {
        _stNmPpaProjectionMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the last modified date of this st nm ppa projection master.
    *
    * @return the last modified date of this st nm ppa projection master
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stNmPpaProjectionMaster.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st nm ppa projection master.
    *
    * @param lastModifiedDate the last modified date of this st nm ppa projection master
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stNmPpaProjectionMaster.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the check record of this st nm ppa projection master.
    *
    * @return the check record of this st nm ppa projection master
    */
    @Override
    public boolean getCheckRecord() {
        return _stNmPpaProjectionMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st nm ppa projection master is check record.
    *
    * @return <code>true</code> if this st nm ppa projection master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stNmPpaProjectionMaster.isCheckRecord();
    }

    /**
    * Sets whether this st nm ppa projection master is check record.
    *
    * @param checkRecord the check record of this st nm ppa projection master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stNmPpaProjectionMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the user group of this st nm ppa projection master.
    *
    * @return the user group of this st nm ppa projection master
    */
    @Override
    public java.lang.String getUserGroup() {
        return _stNmPpaProjectionMaster.getUserGroup();
    }

    /**
    * Sets the user group of this st nm ppa projection master.
    *
    * @param userGroup the user group of this st nm ppa projection master
    */
    @Override
    public void setUserGroup(java.lang.String userGroup) {
        _stNmPpaProjectionMaster.setUserGroup(userGroup);
    }

    /**
    * Returns the projection details sid of this st nm ppa projection master.
    *
    * @return the projection details sid of this st nm ppa projection master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stNmPpaProjectionMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st nm ppa projection master.
    *
    * @param projectionDetailsSid the projection details sid of this st nm ppa projection master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stNmPpaProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the user ID of this st nm ppa projection master.
    *
    * @return the user ID of this st nm ppa projection master
    */
    @Override
    public int getUserId() {
        return _stNmPpaProjectionMaster.getUserId();
    }

    /**
    * Sets the user ID of this st nm ppa projection master.
    *
    * @param userId the user ID of this st nm ppa projection master
    */
    @Override
    public void setUserId(int userId) {
        _stNmPpaProjectionMaster.setUserId(userId);
    }

    /**
    * Returns the session ID of this st nm ppa projection master.
    *
    * @return the session ID of this st nm ppa projection master
    */
    @Override
    public int getSessionId() {
        return _stNmPpaProjectionMaster.getSessionId();
    }

    /**
    * Sets the session ID of this st nm ppa projection master.
    *
    * @param sessionId the session ID of this st nm ppa projection master
    */
    @Override
    public void setSessionId(int sessionId) {
        _stNmPpaProjectionMaster.setSessionId(sessionId);
    }

    /**
    * Returns the price basis of this st nm ppa projection master.
    *
    * @return the price basis of this st nm ppa projection master
    */
    @Override
    public java.lang.String getPriceBasis() {
        return _stNmPpaProjectionMaster.getPriceBasis();
    }

    /**
    * Sets the price basis of this st nm ppa projection master.
    *
    * @param priceBasis the price basis of this st nm ppa projection master
    */
    @Override
    public void setPriceBasis(java.lang.String priceBasis) {
        _stNmPpaProjectionMaster.setPriceBasis(priceBasis);
    }

    /**
    * Returns the price protection end date of this st nm ppa projection master.
    *
    * @return the price protection end date of this st nm ppa projection master
    */
    @Override
    public java.util.Date getPriceProtectionEndDate() {
        return _stNmPpaProjectionMaster.getPriceProtectionEndDate();
    }

    /**
    * Sets the price protection end date of this st nm ppa projection master.
    *
    * @param priceProtectionEndDate the price protection end date of this st nm ppa projection master
    */
    @Override
    public void setPriceProtectionEndDate(java.util.Date priceProtectionEndDate) {
        _stNmPpaProjectionMaster.setPriceProtectionEndDate(priceProtectionEndDate);
    }

    /**
    * Returns the price protection start date of this st nm ppa projection master.
    *
    * @return the price protection start date of this st nm ppa projection master
    */
    @Override
    public java.util.Date getPriceProtectionStartDate() {
        return _stNmPpaProjectionMaster.getPriceProtectionStartDate();
    }

    /**
    * Sets the price protection start date of this st nm ppa projection master.
    *
    * @param priceProtectionStartDate the price protection start date of this st nm ppa projection master
    */
    @Override
    public void setPriceProtectionStartDate(
        java.util.Date priceProtectionStartDate) {
        _stNmPpaProjectionMaster.setPriceProtectionStartDate(priceProtectionStartDate);
    }

    /**
    * Returns the actual price cap of this st nm ppa projection master.
    *
    * @return the actual price cap of this st nm ppa projection master
    */
    @Override
    public double getActualPriceCap() {
        return _stNmPpaProjectionMaster.getActualPriceCap();
    }

    /**
    * Sets the actual price cap of this st nm ppa projection master.
    *
    * @param actualPriceCap the actual price cap of this st nm ppa projection master
    */
    @Override
    public void setActualPriceCap(double actualPriceCap) {
        _stNmPpaProjectionMaster.setActualPriceCap(actualPriceCap);
    }

    @Override
    public boolean isNew() {
        return _stNmPpaProjectionMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stNmPpaProjectionMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stNmPpaProjectionMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stNmPpaProjectionMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stNmPpaProjectionMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stNmPpaProjectionMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stNmPpaProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stNmPpaProjectionMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stNmPpaProjectionMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stNmPpaProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stNmPpaProjectionMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StNmPpaProjectionMasterWrapper((StNmPpaProjectionMaster) _stNmPpaProjectionMaster.clone());
    }

    @Override
    public int compareTo(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
        return _stNmPpaProjectionMaster.compareTo(stNmPpaProjectionMaster);
    }

    @Override
    public int hashCode() {
        return _stNmPpaProjectionMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StNmPpaProjectionMaster> toCacheModel() {
        return _stNmPpaProjectionMaster.toCacheModel();
    }

    @Override
    public StNmPpaProjectionMaster toEscapedModel() {
        return new StNmPpaProjectionMasterWrapper(_stNmPpaProjectionMaster.toEscapedModel());
    }

    @Override
    public StNmPpaProjectionMaster toUnescapedModel() {
        return new StNmPpaProjectionMasterWrapper(_stNmPpaProjectionMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stNmPpaProjectionMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stNmPpaProjectionMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stNmPpaProjectionMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmPpaProjectionMasterWrapper)) {
            return false;
        }

        StNmPpaProjectionMasterWrapper stNmPpaProjectionMasterWrapper = (StNmPpaProjectionMasterWrapper) obj;

        if (Validator.equals(_stNmPpaProjectionMaster,
                    stNmPpaProjectionMasterWrapper._stNmPpaProjectionMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StNmPpaProjectionMaster getWrappedStNmPpaProjectionMaster() {
        return _stNmPpaProjectionMaster;
    }

    @Override
    public StNmPpaProjectionMaster getWrappedModel() {
        return _stNmPpaProjectionMaster;
    }

    @Override
    public void resetOriginalValues() {
        _stNmPpaProjectionMaster.resetOriginalValues();
    }
}
