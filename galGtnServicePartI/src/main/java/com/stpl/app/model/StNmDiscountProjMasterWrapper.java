package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StNmDiscountProjMaster}.
 * </p>
 *
 * @author
 * @see StNmDiscountProjMaster
 * @generated
 */
public class StNmDiscountProjMasterWrapper implements StNmDiscountProjMaster,
    ModelWrapper<StNmDiscountProjMaster> {
    private StNmDiscountProjMaster _stNmDiscountProjMaster;

    public StNmDiscountProjMasterWrapper(
        StNmDiscountProjMaster stNmDiscountProjMaster) {
        _stNmDiscountProjMaster = stNmDiscountProjMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return StNmDiscountProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StNmDiscountProjMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("selectedPeriods", getSelectedPeriods());
        attributes.put("methodology", getMethodology());
        attributes.put("netFlag", getNetFlag());
        attributes.put("priceGroupType", getPriceGroupType());
        attributes.put("userGroup", getUserGroup());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("baselinePeriods", getBaselinePeriods());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String selectedPeriods = (String) attributes.get("selectedPeriods");

        if (selectedPeriods != null) {
            setSelectedPeriods(selectedPeriods);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        String netFlag = (String) attributes.get("netFlag");

        if (netFlag != null) {
            setNetFlag(netFlag);
        }

        String priceGroupType = (String) attributes.get("priceGroupType");

        if (priceGroupType != null) {
            setPriceGroupType(priceGroupType);
        }

        String userGroup = (String) attributes.get("userGroup");

        if (userGroup != null) {
            setUserGroup(userGroup);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String baselinePeriods = (String) attributes.get("baselinePeriods");

        if (baselinePeriods != null) {
            setBaselinePeriods(baselinePeriods);
        }
    }

    /**
    * Returns the primary key of this st nm discount proj master.
    *
    * @return the primary key of this st nm discount proj master
    */
    @Override
    public com.stpl.app.service.persistence.StNmDiscountProjMasterPK getPrimaryKey() {
        return _stNmDiscountProjMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st nm discount proj master.
    *
    * @param primaryKey the primary key of this st nm discount proj master
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StNmDiscountProjMasterPK primaryKey) {
        _stNmDiscountProjMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the selected periods of this st nm discount proj master.
    *
    * @return the selected periods of this st nm discount proj master
    */
    @Override
    public java.lang.String getSelectedPeriods() {
        return _stNmDiscountProjMaster.getSelectedPeriods();
    }

    /**
    * Sets the selected periods of this st nm discount proj master.
    *
    * @param selectedPeriods the selected periods of this st nm discount proj master
    */
    @Override
    public void setSelectedPeriods(java.lang.String selectedPeriods) {
        _stNmDiscountProjMaster.setSelectedPeriods(selectedPeriods);
    }

    /**
    * Returns the methodology of this st nm discount proj master.
    *
    * @return the methodology of this st nm discount proj master
    */
    @Override
    public java.lang.String getMethodology() {
        return _stNmDiscountProjMaster.getMethodology();
    }

    /**
    * Sets the methodology of this st nm discount proj master.
    *
    * @param methodology the methodology of this st nm discount proj master
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _stNmDiscountProjMaster.setMethodology(methodology);
    }

    /**
    * Returns the net flag of this st nm discount proj master.
    *
    * @return the net flag of this st nm discount proj master
    */
    @Override
    public java.lang.String getNetFlag() {
        return _stNmDiscountProjMaster.getNetFlag();
    }

    /**
    * Sets the net flag of this st nm discount proj master.
    *
    * @param netFlag the net flag of this st nm discount proj master
    */
    @Override
    public void setNetFlag(java.lang.String netFlag) {
        _stNmDiscountProjMaster.setNetFlag(netFlag);
    }

    /**
    * Returns the price group type of this st nm discount proj master.
    *
    * @return the price group type of this st nm discount proj master
    */
    @Override
    public java.lang.String getPriceGroupType() {
        return _stNmDiscountProjMaster.getPriceGroupType();
    }

    /**
    * Sets the price group type of this st nm discount proj master.
    *
    * @param priceGroupType the price group type of this st nm discount proj master
    */
    @Override
    public void setPriceGroupType(java.lang.String priceGroupType) {
        _stNmDiscountProjMaster.setPriceGroupType(priceGroupType);
    }

    /**
    * Returns the user group of this st nm discount proj master.
    *
    * @return the user group of this st nm discount proj master
    */
    @Override
    public java.lang.String getUserGroup() {
        return _stNmDiscountProjMaster.getUserGroup();
    }

    /**
    * Sets the user group of this st nm discount proj master.
    *
    * @param userGroup the user group of this st nm discount proj master
    */
    @Override
    public void setUserGroup(java.lang.String userGroup) {
        _stNmDiscountProjMaster.setUserGroup(userGroup);
    }

    /**
    * Returns the user ID of this st nm discount proj master.
    *
    * @return the user ID of this st nm discount proj master
    */
    @Override
    public int getUserId() {
        return _stNmDiscountProjMaster.getUserId();
    }

    /**
    * Sets the user ID of this st nm discount proj master.
    *
    * @param userId the user ID of this st nm discount proj master
    */
    @Override
    public void setUserId(int userId) {
        _stNmDiscountProjMaster.setUserId(userId);
    }

    /**
    * Returns the last modified date of this st nm discount proj master.
    *
    * @return the last modified date of this st nm discount proj master
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stNmDiscountProjMaster.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st nm discount proj master.
    *
    * @param lastModifiedDate the last modified date of this st nm discount proj master
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stNmDiscountProjMaster.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the projection details sid of this st nm discount proj master.
    *
    * @return the projection details sid of this st nm discount proj master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stNmDiscountProjMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st nm discount proj master.
    *
    * @param projectionDetailsSid the projection details sid of this st nm discount proj master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stNmDiscountProjMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the rs model sid of this st nm discount proj master.
    *
    * @return the rs model sid of this st nm discount proj master
    */
    @Override
    public int getRsModelSid() {
        return _stNmDiscountProjMaster.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this st nm discount proj master.
    *
    * @param rsModelSid the rs model sid of this st nm discount proj master
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _stNmDiscountProjMaster.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the session ID of this st nm discount proj master.
    *
    * @return the session ID of this st nm discount proj master
    */
    @Override
    public int getSessionId() {
        return _stNmDiscountProjMaster.getSessionId();
    }

    /**
    * Sets the session ID of this st nm discount proj master.
    *
    * @param sessionId the session ID of this st nm discount proj master
    */
    @Override
    public void setSessionId(int sessionId) {
        _stNmDiscountProjMaster.setSessionId(sessionId);
    }

    /**
    * Returns the check record of this st nm discount proj master.
    *
    * @return the check record of this st nm discount proj master
    */
    @Override
    public boolean getCheckRecord() {
        return _stNmDiscountProjMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st nm discount proj master is check record.
    *
    * @return <code>true</code> if this st nm discount proj master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stNmDiscountProjMaster.isCheckRecord();
    }

    /**
    * Sets whether this st nm discount proj master is check record.
    *
    * @param checkRecord the check record of this st nm discount proj master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stNmDiscountProjMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the baseline periods of this st nm discount proj master.
    *
    * @return the baseline periods of this st nm discount proj master
    */
    @Override
    public java.lang.String getBaselinePeriods() {
        return _stNmDiscountProjMaster.getBaselinePeriods();
    }

    /**
    * Sets the baseline periods of this st nm discount proj master.
    *
    * @param baselinePeriods the baseline periods of this st nm discount proj master
    */
    @Override
    public void setBaselinePeriods(java.lang.String baselinePeriods) {
        _stNmDiscountProjMaster.setBaselinePeriods(baselinePeriods);
    }

    @Override
    public boolean isNew() {
        return _stNmDiscountProjMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stNmDiscountProjMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stNmDiscountProjMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stNmDiscountProjMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stNmDiscountProjMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stNmDiscountProjMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stNmDiscountProjMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stNmDiscountProjMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stNmDiscountProjMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stNmDiscountProjMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stNmDiscountProjMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StNmDiscountProjMasterWrapper((StNmDiscountProjMaster) _stNmDiscountProjMaster.clone());
    }

    @Override
    public int compareTo(StNmDiscountProjMaster stNmDiscountProjMaster) {
        return _stNmDiscountProjMaster.compareTo(stNmDiscountProjMaster);
    }

    @Override
    public int hashCode() {
        return _stNmDiscountProjMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StNmDiscountProjMaster> toCacheModel() {
        return _stNmDiscountProjMaster.toCacheModel();
    }

    @Override
    public StNmDiscountProjMaster toEscapedModel() {
        return new StNmDiscountProjMasterWrapper(_stNmDiscountProjMaster.toEscapedModel());
    }

    @Override
    public StNmDiscountProjMaster toUnescapedModel() {
        return new StNmDiscountProjMasterWrapper(_stNmDiscountProjMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stNmDiscountProjMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stNmDiscountProjMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stNmDiscountProjMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmDiscountProjMasterWrapper)) {
            return false;
        }

        StNmDiscountProjMasterWrapper stNmDiscountProjMasterWrapper = (StNmDiscountProjMasterWrapper) obj;

        if (Validator.equals(_stNmDiscountProjMaster,
                    stNmDiscountProjMasterWrapper._stNmDiscountProjMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StNmDiscountProjMaster getWrappedStNmDiscountProjMaster() {
        return _stNmDiscountProjMaster;
    }

    @Override
    public StNmDiscountProjMaster getWrappedModel() {
        return _stNmDiscountProjMaster;
    }

    @Override
    public void resetOriginalValues() {
        _stNmDiscountProjMaster.resetOriginalValues();
    }
}
