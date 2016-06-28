package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StChSalesProjectionMaster}.
 * </p>
 *
 * @author
 * @see StChSalesProjectionMaster
 * @generated
 */
public class StChSalesProjectionMasterWrapper
    implements StChSalesProjectionMaster,
        ModelWrapper<StChSalesProjectionMaster> {
    private StChSalesProjectionMaster _stChSalesProjectionMaster;

    public StChSalesProjectionMasterWrapper(
        StChSalesProjectionMaster stChSalesProjectionMaster) {
        _stChSalesProjectionMaster = stChSalesProjectionMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return StChSalesProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StChSalesProjectionMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("calculationPeriods", getCalculationPeriods());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("sessionId", getSessionId());
        attributes.put("methodology", getMethodology());

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

        String calculationPeriods = (String) attributes.get(
                "calculationPeriods");

        if (calculationPeriods != null) {
            setCalculationPeriods(calculationPeriods);
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

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }
    }

    /**
    * Returns the primary key of this st ch sales projection master.
    *
    * @return the primary key of this st ch sales projection master
    */
    @Override
    public com.stpl.app.service.persistence.StChSalesProjectionMasterPK getPrimaryKey() {
        return _stChSalesProjectionMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st ch sales projection master.
    *
    * @param primaryKey the primary key of this st ch sales projection master
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StChSalesProjectionMasterPK primaryKey) {
        _stChSalesProjectionMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the last modified date of this st ch sales projection master.
    *
    * @return the last modified date of this st ch sales projection master
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stChSalesProjectionMaster.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st ch sales projection master.
    *
    * @param lastModifiedDate the last modified date of this st ch sales projection master
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stChSalesProjectionMaster.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the check record of this st ch sales projection master.
    *
    * @return the check record of this st ch sales projection master
    */
    @Override
    public boolean getCheckRecord() {
        return _stChSalesProjectionMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st ch sales projection master is check record.
    *
    * @return <code>true</code> if this st ch sales projection master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stChSalesProjectionMaster.isCheckRecord();
    }

    /**
    * Sets whether this st ch sales projection master is check record.
    *
    * @param checkRecord the check record of this st ch sales projection master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stChSalesProjectionMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the calculation periods of this st ch sales projection master.
    *
    * @return the calculation periods of this st ch sales projection master
    */
    @Override
    public java.lang.String getCalculationPeriods() {
        return _stChSalesProjectionMaster.getCalculationPeriods();
    }

    /**
    * Sets the calculation periods of this st ch sales projection master.
    *
    * @param calculationPeriods the calculation periods of this st ch sales projection master
    */
    @Override
    public void setCalculationPeriods(java.lang.String calculationPeriods) {
        _stChSalesProjectionMaster.setCalculationPeriods(calculationPeriods);
    }

    /**
    * Returns the projection details sid of this st ch sales projection master.
    *
    * @return the projection details sid of this st ch sales projection master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stChSalesProjectionMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st ch sales projection master.
    *
    * @param projectionDetailsSid the projection details sid of this st ch sales projection master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stChSalesProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the user ID of this st ch sales projection master.
    *
    * @return the user ID of this st ch sales projection master
    */
    @Override
    public int getUserId() {
        return _stChSalesProjectionMaster.getUserId();
    }

    /**
    * Sets the user ID of this st ch sales projection master.
    *
    * @param userId the user ID of this st ch sales projection master
    */
    @Override
    public void setUserId(int userId) {
        _stChSalesProjectionMaster.setUserId(userId);
    }

    /**
    * Returns the session ID of this st ch sales projection master.
    *
    * @return the session ID of this st ch sales projection master
    */
    @Override
    public int getSessionId() {
        return _stChSalesProjectionMaster.getSessionId();
    }

    /**
    * Sets the session ID of this st ch sales projection master.
    *
    * @param sessionId the session ID of this st ch sales projection master
    */
    @Override
    public void setSessionId(int sessionId) {
        _stChSalesProjectionMaster.setSessionId(sessionId);
    }

    /**
    * Returns the methodology of this st ch sales projection master.
    *
    * @return the methodology of this st ch sales projection master
    */
    @Override
    public java.lang.String getMethodology() {
        return _stChSalesProjectionMaster.getMethodology();
    }

    /**
    * Sets the methodology of this st ch sales projection master.
    *
    * @param methodology the methodology of this st ch sales projection master
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _stChSalesProjectionMaster.setMethodology(methodology);
    }

    @Override
    public boolean isNew() {
        return _stChSalesProjectionMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stChSalesProjectionMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stChSalesProjectionMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stChSalesProjectionMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stChSalesProjectionMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stChSalesProjectionMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stChSalesProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stChSalesProjectionMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stChSalesProjectionMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stChSalesProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stChSalesProjectionMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StChSalesProjectionMasterWrapper((StChSalesProjectionMaster) _stChSalesProjectionMaster.clone());
    }

    @Override
    public int compareTo(StChSalesProjectionMaster stChSalesProjectionMaster) {
        return _stChSalesProjectionMaster.compareTo(stChSalesProjectionMaster);
    }

    @Override
    public int hashCode() {
        return _stChSalesProjectionMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StChSalesProjectionMaster> toCacheModel() {
        return _stChSalesProjectionMaster.toCacheModel();
    }

    @Override
    public StChSalesProjectionMaster toEscapedModel() {
        return new StChSalesProjectionMasterWrapper(_stChSalesProjectionMaster.toEscapedModel());
    }

    @Override
    public StChSalesProjectionMaster toUnescapedModel() {
        return new StChSalesProjectionMasterWrapper(_stChSalesProjectionMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stChSalesProjectionMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stChSalesProjectionMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stChSalesProjectionMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StChSalesProjectionMasterWrapper)) {
            return false;
        }

        StChSalesProjectionMasterWrapper stChSalesProjectionMasterWrapper = (StChSalesProjectionMasterWrapper) obj;

        if (Validator.equals(_stChSalesProjectionMaster,
                    stChSalesProjectionMasterWrapper._stChSalesProjectionMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StChSalesProjectionMaster getWrappedStChSalesProjectionMaster() {
        return _stChSalesProjectionMaster;
    }

    @Override
    public StChSalesProjectionMaster getWrappedModel() {
        return _stChSalesProjectionMaster;
    }

    @Override
    public void resetOriginalValues() {
        _stChSalesProjectionMaster.resetOriginalValues();
    }
}
