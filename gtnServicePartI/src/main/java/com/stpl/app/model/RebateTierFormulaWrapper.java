package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RebateTierFormula}.
 * </p>
 *
 * @author
 * @see RebateTierFormula
 * @generated
 */
public class RebateTierFormulaWrapper implements RebateTierFormula,
    ModelWrapper<RebateTierFormula> {
    private RebateTierFormula _rebateTierFormula;

    public RebateTierFormulaWrapper(RebateTierFormula rebateTierFormula) {
        _rebateTierFormula = rebateTierFormula;
    }

    @Override
    public Class<?> getModelClass() {
        return RebateTierFormula.class;
    }

    @Override
    public String getModelClassName() {
        return RebateTierFormula.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("rebateTierFormulaNo", getRebateTierFormulaNo());
        attributes.put("rebateTierFormulaName", getRebateTierFormulaName());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("batchId", getBatchId());
        attributes.put("rebateTierFormulaId", getRebateTierFormulaId());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rebateTierFormulaSid", getRebateTierFormulaSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String rebateTierFormulaNo = (String) attributes.get(
                "rebateTierFormulaNo");

        if (rebateTierFormulaNo != null) {
            setRebateTierFormulaNo(rebateTierFormulaNo);
        }

        String rebateTierFormulaName = (String) attributes.get(
                "rebateTierFormulaName");

        if (rebateTierFormulaName != null) {
            setRebateTierFormulaName(rebateTierFormulaName);
        }

        Integer rebatePlanMasterSid = (Integer) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String rebateTierFormulaId = (String) attributes.get(
                "rebateTierFormulaId");

        if (rebateTierFormulaId != null) {
            setRebateTierFormulaId(rebateTierFormulaId);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer rebateTierFormulaSid = (Integer) attributes.get(
                "rebateTierFormulaSid");

        if (rebateTierFormulaSid != null) {
            setRebateTierFormulaSid(rebateTierFormulaSid);
        }
    }

    /**
    * Returns the primary key of this rebate tier formula.
    *
    * @return the primary key of this rebate tier formula
    */
    @Override
    public int getPrimaryKey() {
        return _rebateTierFormula.getPrimaryKey();
    }

    /**
    * Sets the primary key of this rebate tier formula.
    *
    * @param primaryKey the primary key of this rebate tier formula
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _rebateTierFormula.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the rebate tier formula no of this rebate tier formula.
    *
    * @return the rebate tier formula no of this rebate tier formula
    */
    @Override
    public java.lang.String getRebateTierFormulaNo() {
        return _rebateTierFormula.getRebateTierFormulaNo();
    }

    /**
    * Sets the rebate tier formula no of this rebate tier formula.
    *
    * @param rebateTierFormulaNo the rebate tier formula no of this rebate tier formula
    */
    @Override
    public void setRebateTierFormulaNo(java.lang.String rebateTierFormulaNo) {
        _rebateTierFormula.setRebateTierFormulaNo(rebateTierFormulaNo);
    }

    /**
    * Returns the rebate tier formula name of this rebate tier formula.
    *
    * @return the rebate tier formula name of this rebate tier formula
    */
    @Override
    public java.lang.String getRebateTierFormulaName() {
        return _rebateTierFormula.getRebateTierFormulaName();
    }

    /**
    * Sets the rebate tier formula name of this rebate tier formula.
    *
    * @param rebateTierFormulaName the rebate tier formula name of this rebate tier formula
    */
    @Override
    public void setRebateTierFormulaName(java.lang.String rebateTierFormulaName) {
        _rebateTierFormula.setRebateTierFormulaName(rebateTierFormulaName);
    }

    /**
    * Returns the rebate plan master sid of this rebate tier formula.
    *
    * @return the rebate plan master sid of this rebate tier formula
    */
    @Override
    public int getRebatePlanMasterSid() {
        return _rebateTierFormula.getRebatePlanMasterSid();
    }

    /**
    * Sets the rebate plan master sid of this rebate tier formula.
    *
    * @param rebatePlanMasterSid the rebate plan master sid of this rebate tier formula
    */
    @Override
    public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
        _rebateTierFormula.setRebatePlanMasterSid(rebatePlanMasterSid);
    }

    /**
    * Returns the modified date of this rebate tier formula.
    *
    * @return the modified date of this rebate tier formula
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _rebateTierFormula.getModifiedDate();
    }

    /**
    * Sets the modified date of this rebate tier formula.
    *
    * @param modifiedDate the modified date of this rebate tier formula
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _rebateTierFormula.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the record lock status of this rebate tier formula.
    *
    * @return the record lock status of this rebate tier formula
    */
    @Override
    public boolean getRecordLockStatus() {
        return _rebateTierFormula.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this rebate tier formula is record lock status.
    *
    * @return <code>true</code> if this rebate tier formula is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _rebateTierFormula.isRecordLockStatus();
    }

    /**
    * Sets whether this rebate tier formula is record lock status.
    *
    * @param recordLockStatus the record lock status of this rebate tier formula
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _rebateTierFormula.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the source of this rebate tier formula.
    *
    * @return the source of this rebate tier formula
    */
    @Override
    public java.lang.String getSource() {
        return _rebateTierFormula.getSource();
    }

    /**
    * Sets the source of this rebate tier formula.
    *
    * @param source the source of this rebate tier formula
    */
    @Override
    public void setSource(java.lang.String source) {
        _rebateTierFormula.setSource(source);
    }

    /**
    * Returns the created by of this rebate tier formula.
    *
    * @return the created by of this rebate tier formula
    */
    @Override
    public int getCreatedBy() {
        return _rebateTierFormula.getCreatedBy();
    }

    /**
    * Sets the created by of this rebate tier formula.
    *
    * @param createdBy the created by of this rebate tier formula
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _rebateTierFormula.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this rebate tier formula.
    *
    * @return the created date of this rebate tier formula
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _rebateTierFormula.getCreatedDate();
    }

    /**
    * Sets the created date of this rebate tier formula.
    *
    * @param createdDate the created date of this rebate tier formula
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _rebateTierFormula.setCreatedDate(createdDate);
    }

    /**
    * Returns the batch ID of this rebate tier formula.
    *
    * @return the batch ID of this rebate tier formula
    */
    @Override
    public java.lang.String getBatchId() {
        return _rebateTierFormula.getBatchId();
    }

    /**
    * Sets the batch ID of this rebate tier formula.
    *
    * @param batchId the batch ID of this rebate tier formula
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _rebateTierFormula.setBatchId(batchId);
    }

    /**
    * Returns the rebate tier formula ID of this rebate tier formula.
    *
    * @return the rebate tier formula ID of this rebate tier formula
    */
    @Override
    public java.lang.String getRebateTierFormulaId() {
        return _rebateTierFormula.getRebateTierFormulaId();
    }

    /**
    * Sets the rebate tier formula ID of this rebate tier formula.
    *
    * @param rebateTierFormulaId the rebate tier formula ID of this rebate tier formula
    */
    @Override
    public void setRebateTierFormulaId(java.lang.String rebateTierFormulaId) {
        _rebateTierFormula.setRebateTierFormulaId(rebateTierFormulaId);
    }

    /**
    * Returns the inbound status of this rebate tier formula.
    *
    * @return the inbound status of this rebate tier formula
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _rebateTierFormula.getInboundStatus();
    }

    /**
    * Sets the inbound status of this rebate tier formula.
    *
    * @param inboundStatus the inbound status of this rebate tier formula
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _rebateTierFormula.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the modified by of this rebate tier formula.
    *
    * @return the modified by of this rebate tier formula
    */
    @Override
    public int getModifiedBy() {
        return _rebateTierFormula.getModifiedBy();
    }

    /**
    * Sets the modified by of this rebate tier formula.
    *
    * @param modifiedBy the modified by of this rebate tier formula
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _rebateTierFormula.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the rebate tier formula sid of this rebate tier formula.
    *
    * @return the rebate tier formula sid of this rebate tier formula
    */
    @Override
    public int getRebateTierFormulaSid() {
        return _rebateTierFormula.getRebateTierFormulaSid();
    }

    /**
    * Sets the rebate tier formula sid of this rebate tier formula.
    *
    * @param rebateTierFormulaSid the rebate tier formula sid of this rebate tier formula
    */
    @Override
    public void setRebateTierFormulaSid(int rebateTierFormulaSid) {
        _rebateTierFormula.setRebateTierFormulaSid(rebateTierFormulaSid);
    }

    @Override
    public boolean isNew() {
        return _rebateTierFormula.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rebateTierFormula.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rebateTierFormula.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rebateTierFormula.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rebateTierFormula.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rebateTierFormula.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rebateTierFormula.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rebateTierFormula.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _rebateTierFormula.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rebateTierFormula.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _rebateTierFormula.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RebateTierFormulaWrapper((RebateTierFormula) _rebateTierFormula.clone());
    }

    @Override
    public int compareTo(RebateTierFormula rebateTierFormula) {
        return _rebateTierFormula.compareTo(rebateTierFormula);
    }

    @Override
    public int hashCode() {
        return _rebateTierFormula.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RebateTierFormula> toCacheModel() {
        return _rebateTierFormula.toCacheModel();
    }

    @Override
    public RebateTierFormula toEscapedModel() {
        return new RebateTierFormulaWrapper(_rebateTierFormula.toEscapedModel());
    }

    @Override
    public RebateTierFormula toUnescapedModel() {
        return new RebateTierFormulaWrapper(_rebateTierFormula.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rebateTierFormula.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rebateTierFormula.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _rebateTierFormula.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RebateTierFormulaWrapper)) {
            return false;
        }

        RebateTierFormulaWrapper rebateTierFormulaWrapper = (RebateTierFormulaWrapper) obj;

        if (Validator.equals(_rebateTierFormula,
                    rebateTierFormulaWrapper._rebateTierFormula)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RebateTierFormula getWrappedRebateTierFormula() {
        return _rebateTierFormula;
    }

    @Override
    public RebateTierFormula getWrappedModel() {
        return _rebateTierFormula;
    }

    @Override
    public void resetOriginalValues() {
        _rebateTierFormula.resetOriginalValues();
    }
}
