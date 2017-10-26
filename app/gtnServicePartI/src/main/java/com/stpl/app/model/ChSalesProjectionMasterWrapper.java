package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChSalesProjectionMaster}.
 * </p>
 *
 * @author
 * @see ChSalesProjectionMaster
 * @generated
 */
public class ChSalesProjectionMasterWrapper implements ChSalesProjectionMaster,
    ModelWrapper<ChSalesProjectionMaster> {
    private ChSalesProjectionMaster _chSalesProjectionMaster;

    public ChSalesProjectionMasterWrapper(
        ChSalesProjectionMaster chSalesProjectionMaster) {
        _chSalesProjectionMaster = chSalesProjectionMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ChSalesProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ChSalesProjectionMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("calculationPeriods", getCalculationPeriods());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("methodology", getMethodology());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
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

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }
    }

    /**
    * Returns the primary key of this ch sales projection master.
    *
    * @return the primary key of this ch sales projection master
    */
    @Override
    public int getPrimaryKey() {
        return _chSalesProjectionMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ch sales projection master.
    *
    * @param primaryKey the primary key of this ch sales projection master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _chSalesProjectionMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the check record of this ch sales projection master.
    *
    * @return the check record of this ch sales projection master
    */
    @Override
    public boolean getCheckRecord() {
        return _chSalesProjectionMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ch sales projection master is check record.
    *
    * @return <code>true</code> if this ch sales projection master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _chSalesProjectionMaster.isCheckRecord();
    }

    /**
    * Sets whether this ch sales projection master is check record.
    *
    * @param checkRecord the check record of this ch sales projection master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _chSalesProjectionMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the calculation periods of this ch sales projection master.
    *
    * @return the calculation periods of this ch sales projection master
    */
    @Override
    public java.lang.String getCalculationPeriods() {
        return _chSalesProjectionMaster.getCalculationPeriods();
    }

    /**
    * Sets the calculation periods of this ch sales projection master.
    *
    * @param calculationPeriods the calculation periods of this ch sales projection master
    */
    @Override
    public void setCalculationPeriods(java.lang.String calculationPeriods) {
        _chSalesProjectionMaster.setCalculationPeriods(calculationPeriods);
    }

    /**
    * Returns the projection details sid of this ch sales projection master.
    *
    * @return the projection details sid of this ch sales projection master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _chSalesProjectionMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this ch sales projection master.
    *
    * @param projectionDetailsSid the projection details sid of this ch sales projection master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _chSalesProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the methodology of this ch sales projection master.
    *
    * @return the methodology of this ch sales projection master
    */
    @Override
    public java.lang.String getMethodology() {
        return _chSalesProjectionMaster.getMethodology();
    }

    /**
    * Sets the methodology of this ch sales projection master.
    *
    * @param methodology the methodology of this ch sales projection master
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _chSalesProjectionMaster.setMethodology(methodology);
    }

    @Override
    public boolean isNew() {
        return _chSalesProjectionMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _chSalesProjectionMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _chSalesProjectionMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _chSalesProjectionMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _chSalesProjectionMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _chSalesProjectionMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _chSalesProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _chSalesProjectionMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _chSalesProjectionMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _chSalesProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _chSalesProjectionMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ChSalesProjectionMasterWrapper((ChSalesProjectionMaster) _chSalesProjectionMaster.clone());
    }

    @Override
    public int compareTo(ChSalesProjectionMaster chSalesProjectionMaster) {
        return _chSalesProjectionMaster.compareTo(chSalesProjectionMaster);
    }

    @Override
    public int hashCode() {
        return _chSalesProjectionMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ChSalesProjectionMaster> toCacheModel() {
        return _chSalesProjectionMaster.toCacheModel();
    }

    @Override
    public ChSalesProjectionMaster toEscapedModel() {
        return new ChSalesProjectionMasterWrapper(_chSalesProjectionMaster.toEscapedModel());
    }

    @Override
    public ChSalesProjectionMaster toUnescapedModel() {
        return new ChSalesProjectionMasterWrapper(_chSalesProjectionMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _chSalesProjectionMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _chSalesProjectionMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _chSalesProjectionMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChSalesProjectionMasterWrapper)) {
            return false;
        }

        ChSalesProjectionMasterWrapper chSalesProjectionMasterWrapper = (ChSalesProjectionMasterWrapper) obj;

        if (Validator.equals(_chSalesProjectionMaster,
                    chSalesProjectionMasterWrapper._chSalesProjectionMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ChSalesProjectionMaster getWrappedChSalesProjectionMaster() {
        return _chSalesProjectionMaster;
    }

    @Override
    public ChSalesProjectionMaster getWrappedModel() {
        return _chSalesProjectionMaster;
    }

    @Override
    public void resetOriginalValues() {
        _chSalesProjectionMaster.resetOriginalValues();
    }
}
