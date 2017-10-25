package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldItemIdentifier}.
 * </p>
 *
 * @author
 * @see IvldItemIdentifier
 * @generated
 */
public class IvldItemIdentifierWrapper implements IvldItemIdentifier,
    ModelWrapper<IvldItemIdentifier> {
    private IvldItemIdentifier _ivldItemIdentifier;

    public IvldItemIdentifierWrapper(IvldItemIdentifier ivldItemIdentifier) {
        _ivldItemIdentifier = ivldItemIdentifier;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemIdentifier.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("identifierCodeQualifierName",
            getIdentifierCodeQualifierName());
        attributes.put("ivldItemIdentifierSid", getIvldItemIdentifierSid());
        attributes.put("itemNo", getItemNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
        attributes.put("itemId", getItemId());
        attributes.put("endDate", getEndDate());
        attributes.put("errorField", getErrorField());
        attributes.put("startDate", getStartDate());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemName", getItemName());
        attributes.put("errorCode", getErrorCode());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("itemStatus", getItemStatus());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("entityCode", getEntityCode());
        attributes.put("itemIdentifierIntfid", getItemIdentifierIntfid());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String identifierCodeQualifierName = (String) attributes.get(
                "identifierCodeQualifierName");

        if (identifierCodeQualifierName != null) {
            setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        Integer ivldItemIdentifierSid = (Integer) attributes.get(
                "ivldItemIdentifierSid");

        if (ivldItemIdentifierSid != null) {
            setIvldItemIdentifierSid(ivldItemIdentifierSid);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String endDate = (String) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String startDate = (String) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        String itemIdentifierIntfid = (String) attributes.get(
                "itemIdentifierIntfid");

        if (itemIdentifierIntfid != null) {
            setItemIdentifierIntfid(itemIdentifierIntfid);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld item identifier.
    *
    * @return the primary key of this ivld item identifier
    */
    @Override
    public int getPrimaryKey() {
        return _ivldItemIdentifier.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld item identifier.
    *
    * @param primaryKey the primary key of this ivld item identifier
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldItemIdentifier.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this ivld item identifier.
    *
    * @return the created by of this ivld item identifier
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldItemIdentifier.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld item identifier.
    *
    * @param createdBy the created by of this ivld item identifier
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldItemIdentifier.setCreatedBy(createdBy);
    }

    /**
    * Returns the identifier code qualifier name of this ivld item identifier.
    *
    * @return the identifier code qualifier name of this ivld item identifier
    */
    @Override
    public java.lang.String getIdentifierCodeQualifierName() {
        return _ivldItemIdentifier.getIdentifierCodeQualifierName();
    }

    /**
    * Sets the identifier code qualifier name of this ivld item identifier.
    *
    * @param identifierCodeQualifierName the identifier code qualifier name of this ivld item identifier
    */
    @Override
    public void setIdentifierCodeQualifierName(
        java.lang.String identifierCodeQualifierName) {
        _ivldItemIdentifier.setIdentifierCodeQualifierName(identifierCodeQualifierName);
    }

    /**
    * Returns the ivld item identifier sid of this ivld item identifier.
    *
    * @return the ivld item identifier sid of this ivld item identifier
    */
    @Override
    public int getIvldItemIdentifierSid() {
        return _ivldItemIdentifier.getIvldItemIdentifierSid();
    }

    /**
    * Sets the ivld item identifier sid of this ivld item identifier.
    *
    * @param ivldItemIdentifierSid the ivld item identifier sid of this ivld item identifier
    */
    @Override
    public void setIvldItemIdentifierSid(int ivldItemIdentifierSid) {
        _ivldItemIdentifier.setIvldItemIdentifierSid(ivldItemIdentifierSid);
    }

    /**
    * Returns the item no of this ivld item identifier.
    *
    * @return the item no of this ivld item identifier
    */
    @Override
    public java.lang.String getItemNo() {
        return _ivldItemIdentifier.getItemNo();
    }

    /**
    * Sets the item no of this ivld item identifier.
    *
    * @param itemNo the item no of this ivld item identifier
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _ivldItemIdentifier.setItemNo(itemNo);
    }

    /**
    * Returns the modified by of this ivld item identifier.
    *
    * @return the modified by of this ivld item identifier
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldItemIdentifier.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld item identifier.
    *
    * @param modifiedBy the modified by of this ivld item identifier
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldItemIdentifier.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this ivld item identifier.
    *
    * @return the created date of this ivld item identifier
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldItemIdentifier.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld item identifier.
    *
    * @param createdDate the created date of this ivld item identifier
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldItemIdentifier.setCreatedDate(createdDate);
    }

    /**
    * Returns the identifier code qualifier of this ivld item identifier.
    *
    * @return the identifier code qualifier of this ivld item identifier
    */
    @Override
    public java.lang.String getIdentifierCodeQualifier() {
        return _ivldItemIdentifier.getIdentifierCodeQualifier();
    }

    /**
    * Sets the identifier code qualifier of this ivld item identifier.
    *
    * @param identifierCodeQualifier the identifier code qualifier of this ivld item identifier
    */
    @Override
    public void setIdentifierCodeQualifier(
        java.lang.String identifierCodeQualifier) {
        _ivldItemIdentifier.setIdentifierCodeQualifier(identifierCodeQualifier);
    }

    /**
    * Returns the item ID of this ivld item identifier.
    *
    * @return the item ID of this ivld item identifier
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldItemIdentifier.getItemId();
    }

    /**
    * Sets the item ID of this ivld item identifier.
    *
    * @param itemId the item ID of this ivld item identifier
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldItemIdentifier.setItemId(itemId);
    }

    /**
    * Returns the end date of this ivld item identifier.
    *
    * @return the end date of this ivld item identifier
    */
    @Override
    public java.lang.String getEndDate() {
        return _ivldItemIdentifier.getEndDate();
    }

    /**
    * Sets the end date of this ivld item identifier.
    *
    * @param endDate the end date of this ivld item identifier
    */
    @Override
    public void setEndDate(java.lang.String endDate) {
        _ivldItemIdentifier.setEndDate(endDate);
    }

    /**
    * Returns the error field of this ivld item identifier.
    *
    * @return the error field of this ivld item identifier
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldItemIdentifier.getErrorField();
    }

    /**
    * Sets the error field of this ivld item identifier.
    *
    * @param errorField the error field of this ivld item identifier
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldItemIdentifier.setErrorField(errorField);
    }

    /**
    * Returns the start date of this ivld item identifier.
    *
    * @return the start date of this ivld item identifier
    */
    @Override
    public java.lang.String getStartDate() {
        return _ivldItemIdentifier.getStartDate();
    }

    /**
    * Sets the start date of this ivld item identifier.
    *
    * @param startDate the start date of this ivld item identifier
    */
    @Override
    public void setStartDate(java.lang.String startDate) {
        _ivldItemIdentifier.setStartDate(startDate);
    }

    /**
    * Returns the batch ID of this ivld item identifier.
    *
    * @return the batch ID of this ivld item identifier
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldItemIdentifier.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld item identifier.
    *
    * @param batchId the batch ID of this ivld item identifier
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldItemIdentifier.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this ivld item identifier.
    *
    * @return the modified date of this ivld item identifier
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldItemIdentifier.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld item identifier.
    *
    * @param modifiedDate the modified date of this ivld item identifier
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldItemIdentifier.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the item name of this ivld item identifier.
    *
    * @return the item name of this ivld item identifier
    */
    @Override
    public java.lang.String getItemName() {
        return _ivldItemIdentifier.getItemName();
    }

    /**
    * Sets the item name of this ivld item identifier.
    *
    * @param itemName the item name of this ivld item identifier
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _ivldItemIdentifier.setItemName(itemName);
    }

    /**
    * Returns the error code of this ivld item identifier.
    *
    * @return the error code of this ivld item identifier
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldItemIdentifier.getErrorCode();
    }

    /**
    * Sets the error code of this ivld item identifier.
    *
    * @param errorCode the error code of this ivld item identifier
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldItemIdentifier.setErrorCode(errorCode);
    }

    /**
    * Returns the reprocessed flag of this ivld item identifier.
    *
    * @return the reprocessed flag of this ivld item identifier
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldItemIdentifier.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld item identifier.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld item identifier
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldItemIdentifier.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the item identifier of this ivld item identifier.
    *
    * @return the item identifier of this ivld item identifier
    */
    @Override
    public java.lang.String getItemIdentifier() {
        return _ivldItemIdentifier.getItemIdentifier();
    }

    /**
    * Sets the item identifier of this ivld item identifier.
    *
    * @param itemIdentifier the item identifier of this ivld item identifier
    */
    @Override
    public void setItemIdentifier(java.lang.String itemIdentifier) {
        _ivldItemIdentifier.setItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the item status of this ivld item identifier.
    *
    * @return the item status of this ivld item identifier
    */
    @Override
    public java.lang.String getItemStatus() {
        return _ivldItemIdentifier.getItemStatus();
    }

    /**
    * Sets the item status of this ivld item identifier.
    *
    * @param itemStatus the item status of this ivld item identifier
    */
    @Override
    public void setItemStatus(java.lang.String itemStatus) {
        _ivldItemIdentifier.setItemStatus(itemStatus);
    }

    /**
    * Returns the reason for failure of this ivld item identifier.
    *
    * @return the reason for failure of this ivld item identifier
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldItemIdentifier.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld item identifier.
    *
    * @param reasonForFailure the reason for failure of this ivld item identifier
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldItemIdentifier.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the source of this ivld item identifier.
    *
    * @return the source of this ivld item identifier
    */
    @Override
    public java.lang.String getSource() {
        return _ivldItemIdentifier.getSource();
    }

    /**
    * Sets the source of this ivld item identifier.
    *
    * @param source the source of this ivld item identifier
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldItemIdentifier.setSource(source);
    }

    /**
    * Returns the add chg del indicator of this ivld item identifier.
    *
    * @return the add chg del indicator of this ivld item identifier
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldItemIdentifier.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld item identifier.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld item identifier
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldItemIdentifier.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the entity code of this ivld item identifier.
    *
    * @return the entity code of this ivld item identifier
    */
    @Override
    public java.lang.String getEntityCode() {
        return _ivldItemIdentifier.getEntityCode();
    }

    /**
    * Sets the entity code of this ivld item identifier.
    *
    * @param entityCode the entity code of this ivld item identifier
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _ivldItemIdentifier.setEntityCode(entityCode);
    }

    /**
    * Returns the item identifier intfid of this ivld item identifier.
    *
    * @return the item identifier intfid of this ivld item identifier
    */
    @Override
    public java.lang.String getItemIdentifierIntfid() {
        return _ivldItemIdentifier.getItemIdentifierIntfid();
    }

    /**
    * Sets the item identifier intfid of this ivld item identifier.
    *
    * @param itemIdentifierIntfid the item identifier intfid of this ivld item identifier
    */
    @Override
    public void setItemIdentifierIntfid(java.lang.String itemIdentifierIntfid) {
        _ivldItemIdentifier.setItemIdentifierIntfid(itemIdentifierIntfid);
    }

    /**
    * Returns the intf inserted date of this ivld item identifier.
    *
    * @return the intf inserted date of this ivld item identifier
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldItemIdentifier.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld item identifier.
    *
    * @param intfInsertedDate the intf inserted date of this ivld item identifier
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldItemIdentifier.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the check record of this ivld item identifier.
    *
    * @return the check record of this ivld item identifier
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldItemIdentifier.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld item identifier is check record.
    *
    * @return <code>true</code> if this ivld item identifier is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldItemIdentifier.isCheckRecord();
    }

    /**
    * Sets whether this ivld item identifier is check record.
    *
    * @param checkRecord the check record of this ivld item identifier
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldItemIdentifier.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldItemIdentifier.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldItemIdentifier.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldItemIdentifier.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldItemIdentifier.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldItemIdentifier.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldItemIdentifier.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldItemIdentifier.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldItemIdentifier.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldItemIdentifier.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldItemIdentifier.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldItemIdentifier.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldItemIdentifierWrapper((IvldItemIdentifier) _ivldItemIdentifier.clone());
    }

    @Override
    public int compareTo(IvldItemIdentifier ivldItemIdentifier) {
        return _ivldItemIdentifier.compareTo(ivldItemIdentifier);
    }

    @Override
    public int hashCode() {
        return _ivldItemIdentifier.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldItemIdentifier> toCacheModel() {
        return _ivldItemIdentifier.toCacheModel();
    }

    @Override
    public IvldItemIdentifier toEscapedModel() {
        return new IvldItemIdentifierWrapper(_ivldItemIdentifier.toEscapedModel());
    }

    @Override
    public IvldItemIdentifier toUnescapedModel() {
        return new IvldItemIdentifierWrapper(_ivldItemIdentifier.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldItemIdentifier.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldItemIdentifier.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldItemIdentifier.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldItemIdentifierWrapper)) {
            return false;
        }

        IvldItemIdentifierWrapper ivldItemIdentifierWrapper = (IvldItemIdentifierWrapper) obj;

        if (Validator.equals(_ivldItemIdentifier,
                    ivldItemIdentifierWrapper._ivldItemIdentifier)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldItemIdentifier getWrappedIvldItemIdentifier() {
        return _ivldItemIdentifier;
    }

    @Override
    public IvldItemIdentifier getWrappedModel() {
        return _ivldItemIdentifier;
    }

    @Override
    public void resetOriginalValues() {
        _ivldItemIdentifier.resetOriginalValues();
    }
}
