package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldItemHierarchyDefinition}.
 * </p>
 *
 * @author
 * @see IvldItemHierarchyDefinition
 * @generated
 */
public class IvldItemHierarchyDefinitionWrapper
    implements IvldItemHierarchyDefinition,
        ModelWrapper<IvldItemHierarchyDefinition> {
    private IvldItemHierarchyDefinition _ivldItemHierarchyDefinition;

    public IvldItemHierarchyDefinitionWrapper(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
        _ivldItemHierarchyDefinition = ivldItemHierarchyDefinition;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemHierarchyDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemHierarchyDefinition.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("member", getMember());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("itemHierarchyDefnIntfid", getItemHierarchyDefnIntfid());
        attributes.put("bpiLvl", getBpiLvl());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("alias", getAlias());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("ivldItemHierarchyDefinitionSid",
            getIvldItemHierarchyDefinitionSid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String member = (String) attributes.get("member");

        if (member != null) {
            setMember(member);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String itemHierarchyDefnIntfid = (String) attributes.get(
                "itemHierarchyDefnIntfid");

        if (itemHierarchyDefnIntfid != null) {
            setItemHierarchyDefnIntfid(itemHierarchyDefnIntfid);
        }

        String bpiLvl = (String) attributes.get("bpiLvl");

        if (bpiLvl != null) {
            setBpiLvl(bpiLvl);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String alias = (String) attributes.get("alias");

        if (alias != null) {
            setAlias(alias);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Integer ivldItemHierarchyDefinitionSid = (Integer) attributes.get(
                "ivldItemHierarchyDefinitionSid");

        if (ivldItemHierarchyDefinitionSid != null) {
            setIvldItemHierarchyDefinitionSid(ivldItemHierarchyDefinitionSid);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
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
    }

    /**
    * Returns the primary key of this ivld item hierarchy definition.
    *
    * @return the primary key of this ivld item hierarchy definition
    */
    @Override
    public int getPrimaryKey() {
        return _ivldItemHierarchyDefinition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld item hierarchy definition.
    *
    * @param primaryKey the primary key of this ivld item hierarchy definition
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldItemHierarchyDefinition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the member of this ivld item hierarchy definition.
    *
    * @return the member of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getMember() {
        return _ivldItemHierarchyDefinition.getMember();
    }

    /**
    * Sets the member of this ivld item hierarchy definition.
    *
    * @param member the member of this ivld item hierarchy definition
    */
    @Override
    public void setMember(java.lang.String member) {
        _ivldItemHierarchyDefinition.setMember(member);
    }

    /**
    * Returns the reason for failure of this ivld item hierarchy definition.
    *
    * @return the reason for failure of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldItemHierarchyDefinition.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld item hierarchy definition.
    *
    * @param reasonForFailure the reason for failure of this ivld item hierarchy definition
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldItemHierarchyDefinition.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the item hierarchy defn intfid of this ivld item hierarchy definition.
    *
    * @return the item hierarchy defn intfid of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getItemHierarchyDefnIntfid() {
        return _ivldItemHierarchyDefinition.getItemHierarchyDefnIntfid();
    }

    /**
    * Sets the item hierarchy defn intfid of this ivld item hierarchy definition.
    *
    * @param itemHierarchyDefnIntfid the item hierarchy defn intfid of this ivld item hierarchy definition
    */
    @Override
    public void setItemHierarchyDefnIntfid(
        java.lang.String itemHierarchyDefnIntfid) {
        _ivldItemHierarchyDefinition.setItemHierarchyDefnIntfid(itemHierarchyDefnIntfid);
    }

    /**
    * Returns the bpi lvl of this ivld item hierarchy definition.
    *
    * @return the bpi lvl of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getBpiLvl() {
        return _ivldItemHierarchyDefinition.getBpiLvl();
    }

    /**
    * Sets the bpi lvl of this ivld item hierarchy definition.
    *
    * @param bpiLvl the bpi lvl of this ivld item hierarchy definition
    */
    @Override
    public void setBpiLvl(java.lang.String bpiLvl) {
        _ivldItemHierarchyDefinition.setBpiLvl(bpiLvl);
    }

    /**
    * Returns the modified date of this ivld item hierarchy definition.
    *
    * @return the modified date of this ivld item hierarchy definition
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldItemHierarchyDefinition.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld item hierarchy definition.
    *
    * @param modifiedDate the modified date of this ivld item hierarchy definition
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldItemHierarchyDefinition.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the alias of this ivld item hierarchy definition.
    *
    * @return the alias of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getAlias() {
        return _ivldItemHierarchyDefinition.getAlias();
    }

    /**
    * Sets the alias of this ivld item hierarchy definition.
    *
    * @param alias the alias of this ivld item hierarchy definition
    */
    @Override
    public void setAlias(java.lang.String alias) {
        _ivldItemHierarchyDefinition.setAlias(alias);
    }

    /**
    * Returns the created by of this ivld item hierarchy definition.
    *
    * @return the created by of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldItemHierarchyDefinition.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld item hierarchy definition.
    *
    * @param createdBy the created by of this ivld item hierarchy definition
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldItemHierarchyDefinition.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld item hierarchy definition.
    *
    * @return the created date of this ivld item hierarchy definition
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldItemHierarchyDefinition.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld item hierarchy definition.
    *
    * @param createdDate the created date of this ivld item hierarchy definition
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldItemHierarchyDefinition.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this ivld item hierarchy definition.
    *
    * @return the source of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getSource() {
        return _ivldItemHierarchyDefinition.getSource();
    }

    /**
    * Sets the source of this ivld item hierarchy definition.
    *
    * @param source the source of this ivld item hierarchy definition
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldItemHierarchyDefinition.setSource(source);
    }

    /**
    * Returns the batch ID of this ivld item hierarchy definition.
    *
    * @return the batch ID of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldItemHierarchyDefinition.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld item hierarchy definition.
    *
    * @param batchId the batch ID of this ivld item hierarchy definition
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldItemHierarchyDefinition.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this ivld item hierarchy definition.
    *
    * @return the add chg del indicator of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldItemHierarchyDefinition.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld item hierarchy definition.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld item hierarchy definition
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldItemHierarchyDefinition.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the ivld item hierarchy definition sid of this ivld item hierarchy definition.
    *
    * @return the ivld item hierarchy definition sid of this ivld item hierarchy definition
    */
    @Override
    public int getIvldItemHierarchyDefinitionSid() {
        return _ivldItemHierarchyDefinition.getIvldItemHierarchyDefinitionSid();
    }

    /**
    * Sets the ivld item hierarchy definition sid of this ivld item hierarchy definition.
    *
    * @param ivldItemHierarchyDefinitionSid the ivld item hierarchy definition sid of this ivld item hierarchy definition
    */
    @Override
    public void setIvldItemHierarchyDefinitionSid(
        int ivldItemHierarchyDefinitionSid) {
        _ivldItemHierarchyDefinition.setIvldItemHierarchyDefinitionSid(ivldItemHierarchyDefinitionSid);
    }

    /**
    * Returns the error field of this ivld item hierarchy definition.
    *
    * @return the error field of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldItemHierarchyDefinition.getErrorField();
    }

    /**
    * Sets the error field of this ivld item hierarchy definition.
    *
    * @param errorField the error field of this ivld item hierarchy definition
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldItemHierarchyDefinition.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld item hierarchy definition.
    *
    * @return the error code of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldItemHierarchyDefinition.getErrorCode();
    }

    /**
    * Sets the error code of this ivld item hierarchy definition.
    *
    * @param errorCode the error code of this ivld item hierarchy definition
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldItemHierarchyDefinition.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld item hierarchy definition.
    *
    * @return the intf inserted date of this ivld item hierarchy definition
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldItemHierarchyDefinition.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld item hierarchy definition.
    *
    * @param intfInsertedDate the intf inserted date of this ivld item hierarchy definition
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldItemHierarchyDefinition.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld item hierarchy definition.
    *
    * @return the modified by of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldItemHierarchyDefinition.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld item hierarchy definition.
    *
    * @param modifiedBy the modified by of this ivld item hierarchy definition
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldItemHierarchyDefinition.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the reprocessed flag of this ivld item hierarchy definition.
    *
    * @return the reprocessed flag of this ivld item hierarchy definition
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldItemHierarchyDefinition.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld item hierarchy definition.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld item hierarchy definition
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldItemHierarchyDefinition.setReprocessedFlag(reprocessedFlag);
    }

    @Override
    public boolean isNew() {
        return _ivldItemHierarchyDefinition.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldItemHierarchyDefinition.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldItemHierarchyDefinition.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldItemHierarchyDefinition.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldItemHierarchyDefinition.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldItemHierarchyDefinition.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldItemHierarchyDefinition.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldItemHierarchyDefinition.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldItemHierarchyDefinition.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldItemHierarchyDefinition.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldItemHierarchyDefinition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldItemHierarchyDefinitionWrapper((IvldItemHierarchyDefinition) _ivldItemHierarchyDefinition.clone());
    }

    @Override
    public int compareTo(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
        return _ivldItemHierarchyDefinition.compareTo(ivldItemHierarchyDefinition);
    }

    @Override
    public int hashCode() {
        return _ivldItemHierarchyDefinition.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldItemHierarchyDefinition> toCacheModel() {
        return _ivldItemHierarchyDefinition.toCacheModel();
    }

    @Override
    public IvldItemHierarchyDefinition toEscapedModel() {
        return new IvldItemHierarchyDefinitionWrapper(_ivldItemHierarchyDefinition.toEscapedModel());
    }

    @Override
    public IvldItemHierarchyDefinition toUnescapedModel() {
        return new IvldItemHierarchyDefinitionWrapper(_ivldItemHierarchyDefinition.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldItemHierarchyDefinition.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldItemHierarchyDefinition.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldItemHierarchyDefinition.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldItemHierarchyDefinitionWrapper)) {
            return false;
        }

        IvldItemHierarchyDefinitionWrapper ivldItemHierarchyDefinitionWrapper = (IvldItemHierarchyDefinitionWrapper) obj;

        if (Validator.equals(_ivldItemHierarchyDefinition,
                    ivldItemHierarchyDefinitionWrapper._ivldItemHierarchyDefinition)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldItemHierarchyDefinition getWrappedIvldItemHierarchyDefinition() {
        return _ivldItemHierarchyDefinition;
    }

    @Override
    public IvldItemHierarchyDefinition getWrappedModel() {
        return _ivldItemHierarchyDefinition;
    }

    @Override
    public void resetOriginalValues() {
        _ivldItemHierarchyDefinition.resetOriginalValues();
    }
}
