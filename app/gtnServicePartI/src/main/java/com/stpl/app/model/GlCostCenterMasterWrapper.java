package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GlCostCenterMaster}.
 * </p>
 *
 * @author
 * @see GlCostCenterMaster
 * @generated
 */
public class GlCostCenterMasterWrapper implements GlCostCenterMaster,
    ModelWrapper<GlCostCenterMaster> {
    private GlCostCenterMaster _glCostCenterMaster;

    public GlCostCenterMasterWrapper(GlCostCenterMaster glCostCenterMaster) {
        _glCostCenterMaster = glCostCenterMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return GlCostCenterMaster.class;
    }

    @Override
    public String getModelClassName() {
        return GlCostCenterMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("glCostCenterMasterSid", getGlCostCenterMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("ndc8", getNdc8());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("companyCode", getCompanyCode());
        attributes.put("source", getSource());
        attributes.put("companyCostCenter", getCompanyCostCenter());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer glCostCenterMasterSid = (Integer) attributes.get(
                "glCostCenterMasterSid");

        if (glCostCenterMasterSid != null) {
            setGlCostCenterMasterSid(glCostCenterMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String companyCode = (String) attributes.get("companyCode");

        if (companyCode != null) {
            setCompanyCode(companyCode);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String companyCostCenter = (String) attributes.get("companyCostCenter");

        if (companyCostCenter != null) {
            setCompanyCostCenter(companyCostCenter);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this gl cost center master.
    *
    * @return the primary key of this gl cost center master
    */
    @Override
    public int getPrimaryKey() {
        return _glCostCenterMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this gl cost center master.
    *
    * @param primaryKey the primary key of this gl cost center master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _glCostCenterMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this gl cost center master.
    *
    * @return the created by of this gl cost center master
    */
    @Override
    public int getCreatedBy() {
        return _glCostCenterMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this gl cost center master.
    *
    * @param createdBy the created by of this gl cost center master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _glCostCenterMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the modified by of this gl cost center master.
    *
    * @return the modified by of this gl cost center master
    */
    @Override
    public int getModifiedBy() {
        return _glCostCenterMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this gl cost center master.
    *
    * @param modifiedBy the modified by of this gl cost center master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _glCostCenterMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the upload date of this gl cost center master.
    *
    * @return the upload date of this gl cost center master
    */
    @Override
    public java.util.Date getUploadDate() {
        return _glCostCenterMaster.getUploadDate();
    }

    /**
    * Sets the upload date of this gl cost center master.
    *
    * @param uploadDate the upload date of this gl cost center master
    */
    @Override
    public void setUploadDate(java.util.Date uploadDate) {
        _glCostCenterMaster.setUploadDate(uploadDate);
    }

    /**
    * Returns the created date of this gl cost center master.
    *
    * @return the created date of this gl cost center master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _glCostCenterMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this gl cost center master.
    *
    * @param createdDate the created date of this gl cost center master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _glCostCenterMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the gl cost center master sid of this gl cost center master.
    *
    * @return the gl cost center master sid of this gl cost center master
    */
    @Override
    public int getGlCostCenterMasterSid() {
        return _glCostCenterMaster.getGlCostCenterMasterSid();
    }

    /**
    * Sets the gl cost center master sid of this gl cost center master.
    *
    * @param glCostCenterMasterSid the gl cost center master sid of this gl cost center master
    */
    @Override
    public void setGlCostCenterMasterSid(int glCostCenterMasterSid) {
        _glCostCenterMaster.setGlCostCenterMasterSid(glCostCenterMasterSid);
    }

    /**
    * Returns the batch ID of this gl cost center master.
    *
    * @return the batch ID of this gl cost center master
    */
    @Override
    public java.lang.String getBatchId() {
        return _glCostCenterMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this gl cost center master.
    *
    * @param batchId the batch ID of this gl cost center master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _glCostCenterMaster.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this gl cost center master.
    *
    * @return the modified date of this gl cost center master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _glCostCenterMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this gl cost center master.
    *
    * @param modifiedDate the modified date of this gl cost center master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _glCostCenterMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the ndc8 of this gl cost center master.
    *
    * @return the ndc8 of this gl cost center master
    */
    @Override
    public java.lang.String getNdc8() {
        return _glCostCenterMaster.getNdc8();
    }

    /**
    * Sets the ndc8 of this gl cost center master.
    *
    * @param ndc8 the ndc8 of this gl cost center master
    */
    @Override
    public void setNdc8(java.lang.String ndc8) {
        _glCostCenterMaster.setNdc8(ndc8);
    }

    /**
    * Returns the record lock status of this gl cost center master.
    *
    * @return the record lock status of this gl cost center master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _glCostCenterMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this gl cost center master is record lock status.
    *
    * @return <code>true</code> if this gl cost center master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _glCostCenterMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this gl cost center master is record lock status.
    *
    * @param recordLockStatus the record lock status of this gl cost center master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _glCostCenterMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the company code of this gl cost center master.
    *
    * @return the company code of this gl cost center master
    */
    @Override
    public java.lang.String getCompanyCode() {
        return _glCostCenterMaster.getCompanyCode();
    }

    /**
    * Sets the company code of this gl cost center master.
    *
    * @param companyCode the company code of this gl cost center master
    */
    @Override
    public void setCompanyCode(java.lang.String companyCode) {
        _glCostCenterMaster.setCompanyCode(companyCode);
    }

    /**
    * Returns the source of this gl cost center master.
    *
    * @return the source of this gl cost center master
    */
    @Override
    public java.lang.String getSource() {
        return _glCostCenterMaster.getSource();
    }

    /**
    * Sets the source of this gl cost center master.
    *
    * @param source the source of this gl cost center master
    */
    @Override
    public void setSource(java.lang.String source) {
        _glCostCenterMaster.setSource(source);
    }

    /**
    * Returns the company cost center of this gl cost center master.
    *
    * @return the company cost center of this gl cost center master
    */
    @Override
    public java.lang.String getCompanyCostCenter() {
        return _glCostCenterMaster.getCompanyCostCenter();
    }

    /**
    * Sets the company cost center of this gl cost center master.
    *
    * @param companyCostCenter the company cost center of this gl cost center master
    */
    @Override
    public void setCompanyCostCenter(java.lang.String companyCostCenter) {
        _glCostCenterMaster.setCompanyCostCenter(companyCostCenter);
    }

    /**
    * Returns the inbound status of this gl cost center master.
    *
    * @return the inbound status of this gl cost center master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _glCostCenterMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this gl cost center master.
    *
    * @param inboundStatus the inbound status of this gl cost center master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _glCostCenterMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _glCostCenterMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _glCostCenterMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _glCostCenterMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _glCostCenterMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _glCostCenterMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _glCostCenterMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _glCostCenterMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _glCostCenterMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _glCostCenterMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _glCostCenterMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _glCostCenterMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new GlCostCenterMasterWrapper((GlCostCenterMaster) _glCostCenterMaster.clone());
    }

    @Override
    public int compareTo(GlCostCenterMaster glCostCenterMaster) {
        return _glCostCenterMaster.compareTo(glCostCenterMaster);
    }

    @Override
    public int hashCode() {
        return _glCostCenterMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<GlCostCenterMaster> toCacheModel() {
        return _glCostCenterMaster.toCacheModel();
    }

    @Override
    public GlCostCenterMaster toEscapedModel() {
        return new GlCostCenterMasterWrapper(_glCostCenterMaster.toEscapedModel());
    }

    @Override
    public GlCostCenterMaster toUnescapedModel() {
        return new GlCostCenterMasterWrapper(_glCostCenterMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _glCostCenterMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _glCostCenterMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _glCostCenterMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof GlCostCenterMasterWrapper)) {
            return false;
        }

        GlCostCenterMasterWrapper glCostCenterMasterWrapper = (GlCostCenterMasterWrapper) obj;

        if (Validator.equals(_glCostCenterMaster,
                    glCostCenterMasterWrapper._glCostCenterMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public GlCostCenterMaster getWrappedGlCostCenterMaster() {
        return _glCostCenterMaster;
    }

    @Override
    public GlCostCenterMaster getWrappedModel() {
        return _glCostCenterMaster;
    }

    @Override
    public void resetOriginalValues() {
        _glCostCenterMaster.resetOriginalValues();
    }
}
