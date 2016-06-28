package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChDiscountProjMaster}.
 * </p>
 *
 * @author
 * @see ChDiscountProjMaster
 * @generated
 */
public class ChDiscountProjMasterWrapper implements ChDiscountProjMaster,
    ModelWrapper<ChDiscountProjMaster> {
    private ChDiscountProjMaster _chDiscountProjMaster;

    public ChDiscountProjMasterWrapper(
        ChDiscountProjMaster chDiscountProjMaster) {
        _chDiscountProjMaster = chDiscountProjMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ChDiscountProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ChDiscountProjMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("selectedPeriods", getSelectedPeriods());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("priceGroupType", getPriceGroupType());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("baselinePeriods", getBaselinePeriods());
        attributes.put("netFlag", getNetFlag());
        attributes.put("methodology", getMethodology());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("discountType", getDiscountType());
        attributes.put("projectedType", getProjectedType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String selectedPeriods = (String) attributes.get("selectedPeriods");

        if (selectedPeriods != null) {
            setSelectedPeriods(selectedPeriods);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String priceGroupType = (String) attributes.get("priceGroupType");

        if (priceGroupType != null) {
            setPriceGroupType(priceGroupType);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        String baselinePeriods = (String) attributes.get("baselinePeriods");

        if (baselinePeriods != null) {
            setBaselinePeriods(baselinePeriods);
        }

        String netFlag = (String) attributes.get("netFlag");

        if (netFlag != null) {
            setNetFlag(netFlag);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String discountType = (String) attributes.get("discountType");

        if (discountType != null) {
            setDiscountType(discountType);
        }

        String projectedType = (String) attributes.get("projectedType");

        if (projectedType != null) {
            setProjectedType(projectedType);
        }
    }

    /**
    * Returns the primary key of this ch discount proj master.
    *
    * @return the primary key of this ch discount proj master
    */
    @Override
    public com.stpl.app.service.persistence.ChDiscountProjMasterPK getPrimaryKey() {
        return _chDiscountProjMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ch discount proj master.
    *
    * @param primaryKey the primary key of this ch discount proj master
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.ChDiscountProjMasterPK primaryKey) {
        _chDiscountProjMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the selected periods of this ch discount proj master.
    *
    * @return the selected periods of this ch discount proj master
    */
    @Override
    public java.lang.String getSelectedPeriods() {
        return _chDiscountProjMaster.getSelectedPeriods();
    }

    /**
    * Sets the selected periods of this ch discount proj master.
    *
    * @param selectedPeriods the selected periods of this ch discount proj master
    */
    @Override
    public void setSelectedPeriods(java.lang.String selectedPeriods) {
        _chDiscountProjMaster.setSelectedPeriods(selectedPeriods);
    }

    /**
    * Returns the check record of this ch discount proj master.
    *
    * @return the check record of this ch discount proj master
    */
    @Override
    public boolean getCheckRecord() {
        return _chDiscountProjMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ch discount proj master is check record.
    *
    * @return <code>true</code> if this ch discount proj master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _chDiscountProjMaster.isCheckRecord();
    }

    /**
    * Sets whether this ch discount proj master is check record.
    *
    * @param checkRecord the check record of this ch discount proj master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _chDiscountProjMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the price group type of this ch discount proj master.
    *
    * @return the price group type of this ch discount proj master
    */
    @Override
    public java.lang.String getPriceGroupType() {
        return _chDiscountProjMaster.getPriceGroupType();
    }

    /**
    * Sets the price group type of this ch discount proj master.
    *
    * @param priceGroupType the price group type of this ch discount proj master
    */
    @Override
    public void setPriceGroupType(java.lang.String priceGroupType) {
        _chDiscountProjMaster.setPriceGroupType(priceGroupType);
    }

    /**
    * Returns the projection details sid of this ch discount proj master.
    *
    * @return the projection details sid of this ch discount proj master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _chDiscountProjMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this ch discount proj master.
    *
    * @param projectionDetailsSid the projection details sid of this ch discount proj master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _chDiscountProjMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the baseline periods of this ch discount proj master.
    *
    * @return the baseline periods of this ch discount proj master
    */
    @Override
    public java.lang.String getBaselinePeriods() {
        return _chDiscountProjMaster.getBaselinePeriods();
    }

    /**
    * Sets the baseline periods of this ch discount proj master.
    *
    * @param baselinePeriods the baseline periods of this ch discount proj master
    */
    @Override
    public void setBaselinePeriods(java.lang.String baselinePeriods) {
        _chDiscountProjMaster.setBaselinePeriods(baselinePeriods);
    }

    /**
    * Returns the net flag of this ch discount proj master.
    *
    * @return the net flag of this ch discount proj master
    */
    @Override
    public java.lang.String getNetFlag() {
        return _chDiscountProjMaster.getNetFlag();
    }

    /**
    * Sets the net flag of this ch discount proj master.
    *
    * @param netFlag the net flag of this ch discount proj master
    */
    @Override
    public void setNetFlag(java.lang.String netFlag) {
        _chDiscountProjMaster.setNetFlag(netFlag);
    }

    /**
    * Returns the methodology of this ch discount proj master.
    *
    * @return the methodology of this ch discount proj master
    */
    @Override
    public java.lang.String getMethodology() {
        return _chDiscountProjMaster.getMethodology();
    }

    /**
    * Sets the methodology of this ch discount proj master.
    *
    * @param methodology the methodology of this ch discount proj master
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _chDiscountProjMaster.setMethodology(methodology);
    }

    /**
    * Returns the rs model sid of this ch discount proj master.
    *
    * @return the rs model sid of this ch discount proj master
    */
    @Override
    public int getRsModelSid() {
        return _chDiscountProjMaster.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this ch discount proj master.
    *
    * @param rsModelSid the rs model sid of this ch discount proj master
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _chDiscountProjMaster.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the discount type of this ch discount proj master.
    *
    * @return the discount type of this ch discount proj master
    */
    @Override
    public java.lang.String getDiscountType() {
        return _chDiscountProjMaster.getDiscountType();
    }

    /**
    * Sets the discount type of this ch discount proj master.
    *
    * @param discountType the discount type of this ch discount proj master
    */
    @Override
    public void setDiscountType(java.lang.String discountType) {
        _chDiscountProjMaster.setDiscountType(discountType);
    }

    /**
    * Returns the projected type of this ch discount proj master.
    *
    * @return the projected type of this ch discount proj master
    */
    @Override
    public java.lang.String getProjectedType() {
        return _chDiscountProjMaster.getProjectedType();
    }

    /**
    * Sets the projected type of this ch discount proj master.
    *
    * @param projectedType the projected type of this ch discount proj master
    */
    @Override
    public void setProjectedType(java.lang.String projectedType) {
        _chDiscountProjMaster.setProjectedType(projectedType);
    }

    @Override
    public boolean isNew() {
        return _chDiscountProjMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _chDiscountProjMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _chDiscountProjMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _chDiscountProjMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _chDiscountProjMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _chDiscountProjMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _chDiscountProjMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _chDiscountProjMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _chDiscountProjMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _chDiscountProjMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _chDiscountProjMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ChDiscountProjMasterWrapper((ChDiscountProjMaster) _chDiscountProjMaster.clone());
    }

    @Override
    public int compareTo(ChDiscountProjMaster chDiscountProjMaster) {
        return _chDiscountProjMaster.compareTo(chDiscountProjMaster);
    }

    @Override
    public int hashCode() {
        return _chDiscountProjMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ChDiscountProjMaster> toCacheModel() {
        return _chDiscountProjMaster.toCacheModel();
    }

    @Override
    public ChDiscountProjMaster toEscapedModel() {
        return new ChDiscountProjMasterWrapper(_chDiscountProjMaster.toEscapedModel());
    }

    @Override
    public ChDiscountProjMaster toUnescapedModel() {
        return new ChDiscountProjMasterWrapper(_chDiscountProjMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _chDiscountProjMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _chDiscountProjMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _chDiscountProjMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChDiscountProjMasterWrapper)) {
            return false;
        }

        ChDiscountProjMasterWrapper chDiscountProjMasterWrapper = (ChDiscountProjMasterWrapper) obj;

        if (Validator.equals(_chDiscountProjMaster,
                    chDiscountProjMasterWrapper._chDiscountProjMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ChDiscountProjMaster getWrappedChDiscountProjMaster() {
        return _chDiscountProjMaster;
    }

    @Override
    public ChDiscountProjMaster getWrappedModel() {
        return _chDiscountProjMaster;
    }

    @Override
    public void resetOriginalValues() {
        _chDiscountProjMaster.resetOriginalValues();
    }
}
