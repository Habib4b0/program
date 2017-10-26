package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NmDiscountProjMaster}.
 * </p>
 *
 * @author
 * @see NmDiscountProjMaster
 * @generated
 */
public class NmDiscountProjMasterWrapper implements NmDiscountProjMaster,
    ModelWrapper<NmDiscountProjMaster> {
    private NmDiscountProjMaster _nmDiscountProjMaster;

    public NmDiscountProjMasterWrapper(
        NmDiscountProjMaster nmDiscountProjMaster) {
        _nmDiscountProjMaster = nmDiscountProjMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return NmDiscountProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NmDiscountProjMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("discountId", getDiscountId());
        attributes.put("userGroup", getUserGroup());
        attributes.put("priceGroupType", getPriceGroupType());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("netFlag", getNetFlag());
        attributes.put("methodology", getMethodology());
        attributes.put("discountName", getDiscountName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String discountId = (String) attributes.get("discountId");

        if (discountId != null) {
            setDiscountId(discountId);
        }

        String userGroup = (String) attributes.get("userGroup");

        if (userGroup != null) {
            setUserGroup(userGroup);
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

        String netFlag = (String) attributes.get("netFlag");

        if (netFlag != null) {
            setNetFlag(netFlag);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        String discountName = (String) attributes.get("discountName");

        if (discountName != null) {
            setDiscountName(discountName);
        }
    }

    /**
    * Returns the primary key of this nm discount proj master.
    *
    * @return the primary key of this nm discount proj master
    */
    @Override
    public int getPrimaryKey() {
        return _nmDiscountProjMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this nm discount proj master.
    *
    * @param primaryKey the primary key of this nm discount proj master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _nmDiscountProjMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the check record of this nm discount proj master.
    *
    * @return the check record of this nm discount proj master
    */
    @Override
    public boolean getCheckRecord() {
        return _nmDiscountProjMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this nm discount proj master is check record.
    *
    * @return <code>true</code> if this nm discount proj master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _nmDiscountProjMaster.isCheckRecord();
    }

    /**
    * Sets whether this nm discount proj master is check record.
    *
    * @param checkRecord the check record of this nm discount proj master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _nmDiscountProjMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the discount ID of this nm discount proj master.
    *
    * @return the discount ID of this nm discount proj master
    */
    @Override
    public java.lang.String getDiscountId() {
        return _nmDiscountProjMaster.getDiscountId();
    }

    /**
    * Sets the discount ID of this nm discount proj master.
    *
    * @param discountId the discount ID of this nm discount proj master
    */
    @Override
    public void setDiscountId(java.lang.String discountId) {
        _nmDiscountProjMaster.setDiscountId(discountId);
    }

    /**
    * Returns the user group of this nm discount proj master.
    *
    * @return the user group of this nm discount proj master
    */
    @Override
    public java.lang.String getUserGroup() {
        return _nmDiscountProjMaster.getUserGroup();
    }

    /**
    * Sets the user group of this nm discount proj master.
    *
    * @param userGroup the user group of this nm discount proj master
    */
    @Override
    public void setUserGroup(java.lang.String userGroup) {
        _nmDiscountProjMaster.setUserGroup(userGroup);
    }

    /**
    * Returns the price group type of this nm discount proj master.
    *
    * @return the price group type of this nm discount proj master
    */
    @Override
    public java.lang.String getPriceGroupType() {
        return _nmDiscountProjMaster.getPriceGroupType();
    }

    /**
    * Sets the price group type of this nm discount proj master.
    *
    * @param priceGroupType the price group type of this nm discount proj master
    */
    @Override
    public void setPriceGroupType(java.lang.String priceGroupType) {
        _nmDiscountProjMaster.setPriceGroupType(priceGroupType);
    }

    /**
    * Returns the projection details sid of this nm discount proj master.
    *
    * @return the projection details sid of this nm discount proj master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _nmDiscountProjMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this nm discount proj master.
    *
    * @param projectionDetailsSid the projection details sid of this nm discount proj master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _nmDiscountProjMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the net flag of this nm discount proj master.
    *
    * @return the net flag of this nm discount proj master
    */
    @Override
    public java.lang.String getNetFlag() {
        return _nmDiscountProjMaster.getNetFlag();
    }

    /**
    * Sets the net flag of this nm discount proj master.
    *
    * @param netFlag the net flag of this nm discount proj master
    */
    @Override
    public void setNetFlag(java.lang.String netFlag) {
        _nmDiscountProjMaster.setNetFlag(netFlag);
    }

    /**
    * Returns the methodology of this nm discount proj master.
    *
    * @return the methodology of this nm discount proj master
    */
    @Override
    public java.lang.String getMethodology() {
        return _nmDiscountProjMaster.getMethodology();
    }

    /**
    * Sets the methodology of this nm discount proj master.
    *
    * @param methodology the methodology of this nm discount proj master
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _nmDiscountProjMaster.setMethodology(methodology);
    }

    /**
    * Returns the discount name of this nm discount proj master.
    *
    * @return the discount name of this nm discount proj master
    */
    @Override
    public java.lang.String getDiscountName() {
        return _nmDiscountProjMaster.getDiscountName();
    }

    /**
    * Sets the discount name of this nm discount proj master.
    *
    * @param discountName the discount name of this nm discount proj master
    */
    @Override
    public void setDiscountName(java.lang.String discountName) {
        _nmDiscountProjMaster.setDiscountName(discountName);
    }

    @Override
    public boolean isNew() {
        return _nmDiscountProjMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nmDiscountProjMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nmDiscountProjMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nmDiscountProjMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nmDiscountProjMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nmDiscountProjMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nmDiscountProjMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nmDiscountProjMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nmDiscountProjMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nmDiscountProjMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nmDiscountProjMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NmDiscountProjMasterWrapper((NmDiscountProjMaster) _nmDiscountProjMaster.clone());
    }

    @Override
    public int compareTo(NmDiscountProjMaster nmDiscountProjMaster) {
        return _nmDiscountProjMaster.compareTo(nmDiscountProjMaster);
    }

    @Override
    public int hashCode() {
        return _nmDiscountProjMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NmDiscountProjMaster> toCacheModel() {
        return _nmDiscountProjMaster.toCacheModel();
    }

    @Override
    public NmDiscountProjMaster toEscapedModel() {
        return new NmDiscountProjMasterWrapper(_nmDiscountProjMaster.toEscapedModel());
    }

    @Override
    public NmDiscountProjMaster toUnescapedModel() {
        return new NmDiscountProjMasterWrapper(_nmDiscountProjMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nmDiscountProjMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nmDiscountProjMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nmDiscountProjMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmDiscountProjMasterWrapper)) {
            return false;
        }

        NmDiscountProjMasterWrapper nmDiscountProjMasterWrapper = (NmDiscountProjMasterWrapper) obj;

        if (Validator.equals(_nmDiscountProjMaster,
                    nmDiscountProjMasterWrapper._nmDiscountProjMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NmDiscountProjMaster getWrappedNmDiscountProjMaster() {
        return _nmDiscountProjMaster;
    }

    @Override
    public NmDiscountProjMaster getWrappedModel() {
        return _nmDiscountProjMaster;
    }

    @Override
    public void resetOriginalValues() {
        _nmDiscountProjMaster.resetOriginalValues();
    }
}
