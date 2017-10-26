package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MSalesProjectionMaster}.
 * </p>
 *
 * @author
 * @see MSalesProjectionMaster
 * @generated
 */
public class MSalesProjectionMasterWrapper implements MSalesProjectionMaster,
    ModelWrapper<MSalesProjectionMaster> {
    private MSalesProjectionMaster _mSalesProjectionMaster;

    public MSalesProjectionMasterWrapper(
        MSalesProjectionMaster mSalesProjectionMaster) {
        _mSalesProjectionMaster = mSalesProjectionMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return MSalesProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return MSalesProjectionMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("methodology", getMethodology());
        attributes.put("calculationPeriods", getCalculationPeriods());
        attributes.put("calculationBased", getCalculationBased());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        String calculationPeriods = (String) attributes.get(
                "calculationPeriods");

        if (calculationPeriods != null) {
            setCalculationPeriods(calculationPeriods);
        }

        String calculationBased = (String) attributes.get("calculationBased");

        if (calculationBased != null) {
            setCalculationBased(calculationBased);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this m sales projection master.
    *
    * @return the primary key of this m sales projection master
    */
    @Override
    public int getPrimaryKey() {
        return _mSalesProjectionMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this m sales projection master.
    *
    * @param primaryKey the primary key of this m sales projection master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _mSalesProjectionMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the methodology of this m sales projection master.
    *
    * @return the methodology of this m sales projection master
    */
    @Override
    public java.lang.String getMethodology() {
        return _mSalesProjectionMaster.getMethodology();
    }

    /**
    * Sets the methodology of this m sales projection master.
    *
    * @param methodology the methodology of this m sales projection master
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _mSalesProjectionMaster.setMethodology(methodology);
    }

    /**
    * Returns the calculation periods of this m sales projection master.
    *
    * @return the calculation periods of this m sales projection master
    */
    @Override
    public java.lang.String getCalculationPeriods() {
        return _mSalesProjectionMaster.getCalculationPeriods();
    }

    /**
    * Sets the calculation periods of this m sales projection master.
    *
    * @param calculationPeriods the calculation periods of this m sales projection master
    */
    @Override
    public void setCalculationPeriods(java.lang.String calculationPeriods) {
        _mSalesProjectionMaster.setCalculationPeriods(calculationPeriods);
    }

    /**
    * Returns the calculation based of this m sales projection master.
    *
    * @return the calculation based of this m sales projection master
    */
    @Override
    public java.lang.String getCalculationBased() {
        return _mSalesProjectionMaster.getCalculationBased();
    }

    /**
    * Sets the calculation based of this m sales projection master.
    *
    * @param calculationBased the calculation based of this m sales projection master
    */
    @Override
    public void setCalculationBased(java.lang.String calculationBased) {
        _mSalesProjectionMaster.setCalculationBased(calculationBased);
    }

    /**
    * Returns the projection details sid of this m sales projection master.
    *
    * @return the projection details sid of this m sales projection master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _mSalesProjectionMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this m sales projection master.
    *
    * @param projectionDetailsSid the projection details sid of this m sales projection master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _mSalesProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the check record of this m sales projection master.
    *
    * @return the check record of this m sales projection master
    */
    @Override
    public boolean getCheckRecord() {
        return _mSalesProjectionMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this m sales projection master is check record.
    *
    * @return <code>true</code> if this m sales projection master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _mSalesProjectionMaster.isCheckRecord();
    }

    /**
    * Sets whether this m sales projection master is check record.
    *
    * @param checkRecord the check record of this m sales projection master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _mSalesProjectionMaster.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _mSalesProjectionMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _mSalesProjectionMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _mSalesProjectionMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _mSalesProjectionMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _mSalesProjectionMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _mSalesProjectionMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _mSalesProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _mSalesProjectionMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _mSalesProjectionMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _mSalesProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _mSalesProjectionMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MSalesProjectionMasterWrapper((MSalesProjectionMaster) _mSalesProjectionMaster.clone());
    }

    @Override
    public int compareTo(MSalesProjectionMaster mSalesProjectionMaster) {
        return _mSalesProjectionMaster.compareTo(mSalesProjectionMaster);
    }

    @Override
    public int hashCode() {
        return _mSalesProjectionMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MSalesProjectionMaster> toCacheModel() {
        return _mSalesProjectionMaster.toCacheModel();
    }

    @Override
    public MSalesProjectionMaster toEscapedModel() {
        return new MSalesProjectionMasterWrapper(_mSalesProjectionMaster.toEscapedModel());
    }

    @Override
    public MSalesProjectionMaster toUnescapedModel() {
        return new MSalesProjectionMasterWrapper(_mSalesProjectionMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _mSalesProjectionMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _mSalesProjectionMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _mSalesProjectionMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MSalesProjectionMasterWrapper)) {
            return false;
        }

        MSalesProjectionMasterWrapper mSalesProjectionMasterWrapper = (MSalesProjectionMasterWrapper) obj;

        if (Validator.equals(_mSalesProjectionMaster,
                    mSalesProjectionMasterWrapper._mSalesProjectionMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MSalesProjectionMaster getWrappedMSalesProjectionMaster() {
        return _mSalesProjectionMaster;
    }

    @Override
    public MSalesProjectionMaster getWrappedModel() {
        return _mSalesProjectionMaster;
    }

    @Override
    public void resetOriginalValues() {
        _mSalesProjectionMaster.resetOriginalValues();
    }
}
