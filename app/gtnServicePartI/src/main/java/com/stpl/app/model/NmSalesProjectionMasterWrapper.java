package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NmSalesProjectionMaster}.
 * </p>
 *
 * @author
 * @see NmSalesProjectionMaster
 * @generated
 */
public class NmSalesProjectionMasterWrapper implements NmSalesProjectionMaster,
    ModelWrapper<NmSalesProjectionMaster> {
    private NmSalesProjectionMaster _nmSalesProjectionMaster;

    public NmSalesProjectionMasterWrapper(
        NmSalesProjectionMaster nmSalesProjectionMaster) {
        _nmSalesProjectionMaster = nmSalesProjectionMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return NmSalesProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NmSalesProjectionMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("calculationPeriods", getCalculationPeriods());
        attributes.put("userGroup", getUserGroup());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("methodology", getMethodology());
        attributes.put("calculationBased", getCalculationBased());

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

        String userGroup = (String) attributes.get("userGroup");

        if (userGroup != null) {
            setUserGroup(userGroup);
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

        String calculationBased = (String) attributes.get("calculationBased");

        if (calculationBased != null) {
            setCalculationBased(calculationBased);
        }
    }

    /**
    * Returns the primary key of this nm sales projection master.
    *
    * @return the primary key of this nm sales projection master
    */
    @Override
    public int getPrimaryKey() {
        return _nmSalesProjectionMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this nm sales projection master.
    *
    * @param primaryKey the primary key of this nm sales projection master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _nmSalesProjectionMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the check record of this nm sales projection master.
    *
    * @return the check record of this nm sales projection master
    */
    @Override
    public boolean getCheckRecord() {
        return _nmSalesProjectionMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this nm sales projection master is check record.
    *
    * @return <code>true</code> if this nm sales projection master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _nmSalesProjectionMaster.isCheckRecord();
    }

    /**
    * Sets whether this nm sales projection master is check record.
    *
    * @param checkRecord the check record of this nm sales projection master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _nmSalesProjectionMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the calculation periods of this nm sales projection master.
    *
    * @return the calculation periods of this nm sales projection master
    */
    @Override
    public java.lang.String getCalculationPeriods() {
        return _nmSalesProjectionMaster.getCalculationPeriods();
    }

    /**
    * Sets the calculation periods of this nm sales projection master.
    *
    * @param calculationPeriods the calculation periods of this nm sales projection master
    */
    @Override
    public void setCalculationPeriods(java.lang.String calculationPeriods) {
        _nmSalesProjectionMaster.setCalculationPeriods(calculationPeriods);
    }

    /**
    * Returns the user group of this nm sales projection master.
    *
    * @return the user group of this nm sales projection master
    */
    @Override
    public java.lang.String getUserGroup() {
        return _nmSalesProjectionMaster.getUserGroup();
    }

    /**
    * Sets the user group of this nm sales projection master.
    *
    * @param userGroup the user group of this nm sales projection master
    */
    @Override
    public void setUserGroup(java.lang.String userGroup) {
        _nmSalesProjectionMaster.setUserGroup(userGroup);
    }

    /**
    * Returns the projection details sid of this nm sales projection master.
    *
    * @return the projection details sid of this nm sales projection master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _nmSalesProjectionMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this nm sales projection master.
    *
    * @param projectionDetailsSid the projection details sid of this nm sales projection master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _nmSalesProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the methodology of this nm sales projection master.
    *
    * @return the methodology of this nm sales projection master
    */
    @Override
    public java.lang.String getMethodology() {
        return _nmSalesProjectionMaster.getMethodology();
    }

    /**
    * Sets the methodology of this nm sales projection master.
    *
    * @param methodology the methodology of this nm sales projection master
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _nmSalesProjectionMaster.setMethodology(methodology);
    }

    /**
    * Returns the calculation based of this nm sales projection master.
    *
    * @return the calculation based of this nm sales projection master
    */
    @Override
    public java.lang.String getCalculationBased() {
        return _nmSalesProjectionMaster.getCalculationBased();
    }

    /**
    * Sets the calculation based of this nm sales projection master.
    *
    * @param calculationBased the calculation based of this nm sales projection master
    */
    @Override
    public void setCalculationBased(java.lang.String calculationBased) {
        _nmSalesProjectionMaster.setCalculationBased(calculationBased);
    }

    @Override
    public boolean isNew() {
        return _nmSalesProjectionMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nmSalesProjectionMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nmSalesProjectionMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nmSalesProjectionMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nmSalesProjectionMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nmSalesProjectionMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nmSalesProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nmSalesProjectionMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nmSalesProjectionMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nmSalesProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nmSalesProjectionMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NmSalesProjectionMasterWrapper((NmSalesProjectionMaster) _nmSalesProjectionMaster.clone());
    }

    @Override
    public int compareTo(NmSalesProjectionMaster nmSalesProjectionMaster) {
        return _nmSalesProjectionMaster.compareTo(nmSalesProjectionMaster);
    }

    @Override
    public int hashCode() {
        return _nmSalesProjectionMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NmSalesProjectionMaster> toCacheModel() {
        return _nmSalesProjectionMaster.toCacheModel();
    }

    @Override
    public NmSalesProjectionMaster toEscapedModel() {
        return new NmSalesProjectionMasterWrapper(_nmSalesProjectionMaster.toEscapedModel());
    }

    @Override
    public NmSalesProjectionMaster toUnescapedModel() {
        return new NmSalesProjectionMasterWrapper(_nmSalesProjectionMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nmSalesProjectionMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nmSalesProjectionMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nmSalesProjectionMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmSalesProjectionMasterWrapper)) {
            return false;
        }

        NmSalesProjectionMasterWrapper nmSalesProjectionMasterWrapper = (NmSalesProjectionMasterWrapper) obj;

        if (Validator.equals(_nmSalesProjectionMaster,
                    nmSalesProjectionMasterWrapper._nmSalesProjectionMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NmSalesProjectionMaster getWrappedNmSalesProjectionMaster() {
        return _nmSalesProjectionMaster;
    }

    @Override
    public NmSalesProjectionMaster getWrappedModel() {
        return _nmSalesProjectionMaster;
    }

    @Override
    public void resetOriginalValues() {
        _nmSalesProjectionMaster.resetOriginalValues();
    }
}
