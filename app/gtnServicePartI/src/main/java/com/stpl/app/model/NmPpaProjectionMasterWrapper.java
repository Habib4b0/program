package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NmPpaProjectionMaster}.
 * </p>
 *
 * @author
 * @see NmPpaProjectionMaster
 * @generated
 */
public class NmPpaProjectionMasterWrapper implements NmPpaProjectionMaster,
    ModelWrapper<NmPpaProjectionMaster> {
    private NmPpaProjectionMaster _nmPpaProjectionMaster;

    public NmPpaProjectionMasterWrapper(
        NmPpaProjectionMaster nmPpaProjectionMaster) {
        _nmPpaProjectionMaster = nmPpaProjectionMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return NmPpaProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NmPpaProjectionMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("userGroup", getUserGroup());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("priceBasis", getPriceBasis());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("actualPriceCap", getActualPriceCap());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
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
    * Returns the primary key of this nm ppa projection master.
    *
    * @return the primary key of this nm ppa projection master
    */
    @Override
    public int getPrimaryKey() {
        return _nmPpaProjectionMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this nm ppa projection master.
    *
    * @param primaryKey the primary key of this nm ppa projection master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _nmPpaProjectionMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the check record of this nm ppa projection master.
    *
    * @return the check record of this nm ppa projection master
    */
    @Override
    public boolean getCheckRecord() {
        return _nmPpaProjectionMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this nm ppa projection master is check record.
    *
    * @return <code>true</code> if this nm ppa projection master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _nmPpaProjectionMaster.isCheckRecord();
    }

    /**
    * Sets whether this nm ppa projection master is check record.
    *
    * @param checkRecord the check record of this nm ppa projection master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _nmPpaProjectionMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the user group of this nm ppa projection master.
    *
    * @return the user group of this nm ppa projection master
    */
    @Override
    public java.lang.String getUserGroup() {
        return _nmPpaProjectionMaster.getUserGroup();
    }

    /**
    * Sets the user group of this nm ppa projection master.
    *
    * @param userGroup the user group of this nm ppa projection master
    */
    @Override
    public void setUserGroup(java.lang.String userGroup) {
        _nmPpaProjectionMaster.setUserGroup(userGroup);
    }

    /**
    * Returns the projection details sid of this nm ppa projection master.
    *
    * @return the projection details sid of this nm ppa projection master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _nmPpaProjectionMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this nm ppa projection master.
    *
    * @param projectionDetailsSid the projection details sid of this nm ppa projection master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _nmPpaProjectionMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the price basis of this nm ppa projection master.
    *
    * @return the price basis of this nm ppa projection master
    */
    @Override
    public java.lang.String getPriceBasis() {
        return _nmPpaProjectionMaster.getPriceBasis();
    }

    /**
    * Sets the price basis of this nm ppa projection master.
    *
    * @param priceBasis the price basis of this nm ppa projection master
    */
    @Override
    public void setPriceBasis(java.lang.String priceBasis) {
        _nmPpaProjectionMaster.setPriceBasis(priceBasis);
    }

    /**
    * Returns the price protection end date of this nm ppa projection master.
    *
    * @return the price protection end date of this nm ppa projection master
    */
    @Override
    public java.util.Date getPriceProtectionEndDate() {
        return _nmPpaProjectionMaster.getPriceProtectionEndDate();
    }

    /**
    * Sets the price protection end date of this nm ppa projection master.
    *
    * @param priceProtectionEndDate the price protection end date of this nm ppa projection master
    */
    @Override
    public void setPriceProtectionEndDate(java.util.Date priceProtectionEndDate) {
        _nmPpaProjectionMaster.setPriceProtectionEndDate(priceProtectionEndDate);
    }

    /**
    * Returns the price protection start date of this nm ppa projection master.
    *
    * @return the price protection start date of this nm ppa projection master
    */
    @Override
    public java.util.Date getPriceProtectionStartDate() {
        return _nmPpaProjectionMaster.getPriceProtectionStartDate();
    }

    /**
    * Sets the price protection start date of this nm ppa projection master.
    *
    * @param priceProtectionStartDate the price protection start date of this nm ppa projection master
    */
    @Override
    public void setPriceProtectionStartDate(
        java.util.Date priceProtectionStartDate) {
        _nmPpaProjectionMaster.setPriceProtectionStartDate(priceProtectionStartDate);
    }

    /**
    * Returns the actual price cap of this nm ppa projection master.
    *
    * @return the actual price cap of this nm ppa projection master
    */
    @Override
    public double getActualPriceCap() {
        return _nmPpaProjectionMaster.getActualPriceCap();
    }

    /**
    * Sets the actual price cap of this nm ppa projection master.
    *
    * @param actualPriceCap the actual price cap of this nm ppa projection master
    */
    @Override
    public void setActualPriceCap(double actualPriceCap) {
        _nmPpaProjectionMaster.setActualPriceCap(actualPriceCap);
    }

    @Override
    public boolean isNew() {
        return _nmPpaProjectionMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nmPpaProjectionMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nmPpaProjectionMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nmPpaProjectionMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nmPpaProjectionMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nmPpaProjectionMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nmPpaProjectionMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nmPpaProjectionMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nmPpaProjectionMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nmPpaProjectionMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nmPpaProjectionMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NmPpaProjectionMasterWrapper((NmPpaProjectionMaster) _nmPpaProjectionMaster.clone());
    }

    @Override
    public int compareTo(NmPpaProjectionMaster nmPpaProjectionMaster) {
        return _nmPpaProjectionMaster.compareTo(nmPpaProjectionMaster);
    }

    @Override
    public int hashCode() {
        return _nmPpaProjectionMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NmPpaProjectionMaster> toCacheModel() {
        return _nmPpaProjectionMaster.toCacheModel();
    }

    @Override
    public NmPpaProjectionMaster toEscapedModel() {
        return new NmPpaProjectionMasterWrapper(_nmPpaProjectionMaster.toEscapedModel());
    }

    @Override
    public NmPpaProjectionMaster toUnescapedModel() {
        return new NmPpaProjectionMasterWrapper(_nmPpaProjectionMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nmPpaProjectionMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nmPpaProjectionMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nmPpaProjectionMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmPpaProjectionMasterWrapper)) {
            return false;
        }

        NmPpaProjectionMasterWrapper nmPpaProjectionMasterWrapper = (NmPpaProjectionMasterWrapper) obj;

        if (Validator.equals(_nmPpaProjectionMaster,
                    nmPpaProjectionMasterWrapper._nmPpaProjectionMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NmPpaProjectionMaster getWrappedNmPpaProjectionMaster() {
        return _nmPpaProjectionMaster;
    }

    @Override
    public NmPpaProjectionMaster getWrappedModel() {
        return _nmPpaProjectionMaster;
    }

    @Override
    public void resetOriginalValues() {
        _nmPpaProjectionMaster.resetOriginalValues();
    }
}
