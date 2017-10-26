package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldCompanyTradeClass}.
 * </p>
 *
 * @author
 * @see IvldCompanyTradeClass
 * @generated
 */
public class IvldCompanyTradeClassWrapper implements IvldCompanyTradeClass,
    ModelWrapper<IvldCompanyTradeClass> {
    private IvldCompanyTradeClass _ivldCompanyTradeClass;

    public IvldCompanyTradeClassWrapper(
        IvldCompanyTradeClass ivldCompanyTradeClass) {
        _ivldCompanyTradeClass = ivldCompanyTradeClass;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCompanyTradeClass.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCompanyTradeClass.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ivldCompanyTradeClassSid", getIvldCompanyTradeClassSid());
        attributes.put("priorTradeClass", getPriorTradeClass());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("tradeClassIntfid", getTradeClassIntfid());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("tradeClass", getTradeClass());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ivldCompanyTradeClassSid = (Integer) attributes.get(
                "ivldCompanyTradeClassSid");

        if (ivldCompanyTradeClassSid != null) {
            setIvldCompanyTradeClassSid(ivldCompanyTradeClassSid);
        }

        String priorTradeClass = (String) attributes.get("priorTradeClass");

        if (priorTradeClass != null) {
            setPriorTradeClass(priorTradeClass);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String lastUpdatedDate = (String) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        String priorTradeClassStartDate = (String) attributes.get(
                "priorTradeClassStartDate");

        if (priorTradeClassStartDate != null) {
            setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String tradeClassEndDate = (String) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        String tradeClassIntfid = (String) attributes.get("tradeClassIntfid");

        if (tradeClassIntfid != null) {
            setTradeClassIntfid(tradeClassIntfid);
        }

        String tradeClassStartDate = (String) attributes.get(
                "tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String tradeClass = (String) attributes.get("tradeClass");

        if (tradeClass != null) {
            setTradeClass(tradeClass);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld company trade class.
    *
    * @return the primary key of this ivld company trade class
    */
    @Override
    public int getPrimaryKey() {
        return _ivldCompanyTradeClass.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld company trade class.
    *
    * @param primaryKey the primary key of this ivld company trade class
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldCompanyTradeClass.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ivld company trade class sid of this ivld company trade class.
    *
    * @return the ivld company trade class sid of this ivld company trade class
    */
    @Override
    public int getIvldCompanyTradeClassSid() {
        return _ivldCompanyTradeClass.getIvldCompanyTradeClassSid();
    }

    /**
    * Sets the ivld company trade class sid of this ivld company trade class.
    *
    * @param ivldCompanyTradeClassSid the ivld company trade class sid of this ivld company trade class
    */
    @Override
    public void setIvldCompanyTradeClassSid(int ivldCompanyTradeClassSid) {
        _ivldCompanyTradeClass.setIvldCompanyTradeClassSid(ivldCompanyTradeClassSid);
    }

    /**
    * Returns the prior trade class of this ivld company trade class.
    *
    * @return the prior trade class of this ivld company trade class
    */
    @Override
    public java.lang.String getPriorTradeClass() {
        return _ivldCompanyTradeClass.getPriorTradeClass();
    }

    /**
    * Sets the prior trade class of this ivld company trade class.
    *
    * @param priorTradeClass the prior trade class of this ivld company trade class
    */
    @Override
    public void setPriorTradeClass(java.lang.String priorTradeClass) {
        _ivldCompanyTradeClass.setPriorTradeClass(priorTradeClass);
    }

    /**
    * Returns the reason for failure of this ivld company trade class.
    *
    * @return the reason for failure of this ivld company trade class
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldCompanyTradeClass.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld company trade class.
    *
    * @param reasonForFailure the reason for failure of this ivld company trade class
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldCompanyTradeClass.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the company ID of this ivld company trade class.
    *
    * @return the company ID of this ivld company trade class
    */
    @Override
    public java.lang.String getCompanyId() {
        return _ivldCompanyTradeClass.getCompanyId();
    }

    /**
    * Sets the company ID of this ivld company trade class.
    *
    * @param companyId the company ID of this ivld company trade class
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _ivldCompanyTradeClass.setCompanyId(companyId);
    }

    /**
    * Returns the last updated date of this ivld company trade class.
    *
    * @return the last updated date of this ivld company trade class
    */
    @Override
    public java.lang.String getLastUpdatedDate() {
        return _ivldCompanyTradeClass.getLastUpdatedDate();
    }

    /**
    * Sets the last updated date of this ivld company trade class.
    *
    * @param lastUpdatedDate the last updated date of this ivld company trade class
    */
    @Override
    public void setLastUpdatedDate(java.lang.String lastUpdatedDate) {
        _ivldCompanyTradeClass.setLastUpdatedDate(lastUpdatedDate);
    }

    /**
    * Returns the prior trade class start date of this ivld company trade class.
    *
    * @return the prior trade class start date of this ivld company trade class
    */
    @Override
    public java.lang.String getPriorTradeClassStartDate() {
        return _ivldCompanyTradeClass.getPriorTradeClassStartDate();
    }

    /**
    * Sets the prior trade class start date of this ivld company trade class.
    *
    * @param priorTradeClassStartDate the prior trade class start date of this ivld company trade class
    */
    @Override
    public void setPriorTradeClassStartDate(
        java.lang.String priorTradeClassStartDate) {
        _ivldCompanyTradeClass.setPriorTradeClassStartDate(priorTradeClassStartDate);
    }

    /**
    * Returns the modified date of this ivld company trade class.
    *
    * @return the modified date of this ivld company trade class
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldCompanyTradeClass.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld company trade class.
    *
    * @param modifiedDate the modified date of this ivld company trade class
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldCompanyTradeClass.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the trade class end date of this ivld company trade class.
    *
    * @return the trade class end date of this ivld company trade class
    */
    @Override
    public java.lang.String getTradeClassEndDate() {
        return _ivldCompanyTradeClass.getTradeClassEndDate();
    }

    /**
    * Sets the trade class end date of this ivld company trade class.
    *
    * @param tradeClassEndDate the trade class end date of this ivld company trade class
    */
    @Override
    public void setTradeClassEndDate(java.lang.String tradeClassEndDate) {
        _ivldCompanyTradeClass.setTradeClassEndDate(tradeClassEndDate);
    }

    /**
    * Returns the trade class intfid of this ivld company trade class.
    *
    * @return the trade class intfid of this ivld company trade class
    */
    @Override
    public java.lang.String getTradeClassIntfid() {
        return _ivldCompanyTradeClass.getTradeClassIntfid();
    }

    /**
    * Sets the trade class intfid of this ivld company trade class.
    *
    * @param tradeClassIntfid the trade class intfid of this ivld company trade class
    */
    @Override
    public void setTradeClassIntfid(java.lang.String tradeClassIntfid) {
        _ivldCompanyTradeClass.setTradeClassIntfid(tradeClassIntfid);
    }

    /**
    * Returns the trade class start date of this ivld company trade class.
    *
    * @return the trade class start date of this ivld company trade class
    */
    @Override
    public java.lang.String getTradeClassStartDate() {
        return _ivldCompanyTradeClass.getTradeClassStartDate();
    }

    /**
    * Sets the trade class start date of this ivld company trade class.
    *
    * @param tradeClassStartDate the trade class start date of this ivld company trade class
    */
    @Override
    public void setTradeClassStartDate(java.lang.String tradeClassStartDate) {
        _ivldCompanyTradeClass.setTradeClassStartDate(tradeClassStartDate);
    }

    /**
    * Returns the source of this ivld company trade class.
    *
    * @return the source of this ivld company trade class
    */
    @Override
    public java.lang.String getSource() {
        return _ivldCompanyTradeClass.getSource();
    }

    /**
    * Sets the source of this ivld company trade class.
    *
    * @param source the source of this ivld company trade class
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldCompanyTradeClass.setSource(source);
    }

    /**
    * Returns the created by of this ivld company trade class.
    *
    * @return the created by of this ivld company trade class
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldCompanyTradeClass.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld company trade class.
    *
    * @param createdBy the created by of this ivld company trade class
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldCompanyTradeClass.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld company trade class.
    *
    * @return the created date of this ivld company trade class
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldCompanyTradeClass.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld company trade class.
    *
    * @param createdDate the created date of this ivld company trade class
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldCompanyTradeClass.setCreatedDate(createdDate);
    }

    /**
    * Returns the add chg del indicator of this ivld company trade class.
    *
    * @return the add chg del indicator of this ivld company trade class
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldCompanyTradeClass.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld company trade class.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld company trade class
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldCompanyTradeClass.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the batch ID of this ivld company trade class.
    *
    * @return the batch ID of this ivld company trade class
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldCompanyTradeClass.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld company trade class.
    *
    * @param batchId the batch ID of this ivld company trade class
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldCompanyTradeClass.setBatchId(batchId);
    }

    /**
    * Returns the error field of this ivld company trade class.
    *
    * @return the error field of this ivld company trade class
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldCompanyTradeClass.getErrorField();
    }

    /**
    * Sets the error field of this ivld company trade class.
    *
    * @param errorField the error field of this ivld company trade class
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldCompanyTradeClass.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld company trade class.
    *
    * @return the error code of this ivld company trade class
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldCompanyTradeClass.getErrorCode();
    }

    /**
    * Sets the error code of this ivld company trade class.
    *
    * @param errorCode the error code of this ivld company trade class
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldCompanyTradeClass.setErrorCode(errorCode);
    }

    /**
    * Returns the trade class of this ivld company trade class.
    *
    * @return the trade class of this ivld company trade class
    */
    @Override
    public java.lang.String getTradeClass() {
        return _ivldCompanyTradeClass.getTradeClass();
    }

    /**
    * Sets the trade class of this ivld company trade class.
    *
    * @param tradeClass the trade class of this ivld company trade class
    */
    @Override
    public void setTradeClass(java.lang.String tradeClass) {
        _ivldCompanyTradeClass.setTradeClass(tradeClass);
    }

    /**
    * Returns the intf inserted date of this ivld company trade class.
    *
    * @return the intf inserted date of this ivld company trade class
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldCompanyTradeClass.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld company trade class.
    *
    * @param intfInsertedDate the intf inserted date of this ivld company trade class
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldCompanyTradeClass.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld company trade class.
    *
    * @return the modified by of this ivld company trade class
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldCompanyTradeClass.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld company trade class.
    *
    * @param modifiedBy the modified by of this ivld company trade class
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldCompanyTradeClass.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the reprocessed flag of this ivld company trade class.
    *
    * @return the reprocessed flag of this ivld company trade class
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldCompanyTradeClass.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld company trade class.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld company trade class
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldCompanyTradeClass.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the check record of this ivld company trade class.
    *
    * @return the check record of this ivld company trade class
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldCompanyTradeClass.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld company trade class is check record.
    *
    * @return <code>true</code> if this ivld company trade class is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldCompanyTradeClass.isCheckRecord();
    }

    /**
    * Sets whether this ivld company trade class is check record.
    *
    * @param checkRecord the check record of this ivld company trade class
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldCompanyTradeClass.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldCompanyTradeClass.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldCompanyTradeClass.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldCompanyTradeClass.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldCompanyTradeClass.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldCompanyTradeClass.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldCompanyTradeClass.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldCompanyTradeClass.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldCompanyTradeClass.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldCompanyTradeClass.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldCompanyTradeClass.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldCompanyTradeClass.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldCompanyTradeClassWrapper((IvldCompanyTradeClass) _ivldCompanyTradeClass.clone());
    }

    @Override
    public int compareTo(IvldCompanyTradeClass ivldCompanyTradeClass) {
        return _ivldCompanyTradeClass.compareTo(ivldCompanyTradeClass);
    }

    @Override
    public int hashCode() {
        return _ivldCompanyTradeClass.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldCompanyTradeClass> toCacheModel() {
        return _ivldCompanyTradeClass.toCacheModel();
    }

    @Override
    public IvldCompanyTradeClass toEscapedModel() {
        return new IvldCompanyTradeClassWrapper(_ivldCompanyTradeClass.toEscapedModel());
    }

    @Override
    public IvldCompanyTradeClass toUnescapedModel() {
        return new IvldCompanyTradeClassWrapper(_ivldCompanyTradeClass.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldCompanyTradeClass.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldCompanyTradeClass.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldCompanyTradeClass.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldCompanyTradeClassWrapper)) {
            return false;
        }

        IvldCompanyTradeClassWrapper ivldCompanyTradeClassWrapper = (IvldCompanyTradeClassWrapper) obj;

        if (Validator.equals(_ivldCompanyTradeClass,
                    ivldCompanyTradeClassWrapper._ivldCompanyTradeClass)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldCompanyTradeClass getWrappedIvldCompanyTradeClass() {
        return _ivldCompanyTradeClass;
    }

    @Override
    public IvldCompanyTradeClass getWrappedModel() {
        return _ivldCompanyTradeClass;
    }

    @Override
    public void resetOriginalValues() {
        _ivldCompanyTradeClass.resetOriginalValues();
    }
}
