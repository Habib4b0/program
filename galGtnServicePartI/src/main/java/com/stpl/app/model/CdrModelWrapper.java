package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CdrModel}.
 * </p>
 *
 * @author
 * @see CdrModel
 * @generated
 */
public class CdrModelWrapper implements CdrModel, ModelWrapper<CdrModel> {
    private CdrModel _cdrModel;

    public CdrModelWrapper(CdrModel cdrModel) {
        _cdrModel = cdrModel;
    }

    @Override
    public Class<?> getModelClass() {
        return CdrModel.class;
    }

    @Override
    public String getModelClassName() {
        return CdrModel.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("ruleCategory", getRuleCategory());
        attributes.put("ruleType", getRuleType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("ruleName", getRuleName());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("ruleNo", getRuleNo());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer ruleCategory = (Integer) attributes.get("ruleCategory");

        if (ruleCategory != null) {
            setRuleCategory(ruleCategory);
        }

        Integer ruleType = (Integer) attributes.get("ruleType");

        if (ruleType != null) {
            setRuleType(ruleType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String ruleName = (String) attributes.get("ruleName");

        if (ruleName != null) {
            setRuleName(ruleName);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String ruleNo = (String) attributes.get("ruleNo");

        if (ruleNo != null) {
            setRuleNo(ruleNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this cdr model.
    *
    * @return the primary key of this cdr model
    */
    @Override
    public int getPrimaryKey() {
        return _cdrModel.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cdr model.
    *
    * @param primaryKey the primary key of this cdr model
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cdrModel.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this cdr model.
    *
    * @return the created by of this cdr model
    */
    @Override
    public int getCreatedBy() {
        return _cdrModel.getCreatedBy();
    }

    /**
    * Sets the created by of this cdr model.
    *
    * @param createdBy the created by of this cdr model
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cdrModel.setCreatedBy(createdBy);
    }

    /**
    * Returns the rule category of this cdr model.
    *
    * @return the rule category of this cdr model
    */
    @Override
    public int getRuleCategory() {
        return _cdrModel.getRuleCategory();
    }

    /**
    * Sets the rule category of this cdr model.
    *
    * @param ruleCategory the rule category of this cdr model
    */
    @Override
    public void setRuleCategory(int ruleCategory) {
        _cdrModel.setRuleCategory(ruleCategory);
    }

    /**
    * Returns the rule type of this cdr model.
    *
    * @return the rule type of this cdr model
    */
    @Override
    public int getRuleType() {
        return _cdrModel.getRuleType();
    }

    /**
    * Sets the rule type of this cdr model.
    *
    * @param ruleType the rule type of this cdr model
    */
    @Override
    public void setRuleType(int ruleType) {
        _cdrModel.setRuleType(ruleType);
    }

    /**
    * Returns the modified by of this cdr model.
    *
    * @return the modified by of this cdr model
    */
    @Override
    public int getModifiedBy() {
        return _cdrModel.getModifiedBy();
    }

    /**
    * Sets the modified by of this cdr model.
    *
    * @param modifiedBy the modified by of this cdr model
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cdrModel.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the internal notes of this cdr model.
    *
    * @return the internal notes of this cdr model
    */
    @Override
    public java.lang.String getInternalNotes() {
        return _cdrModel.getInternalNotes();
    }

    /**
    * Sets the internal notes of this cdr model.
    *
    * @param internalNotes the internal notes of this cdr model
    */
    @Override
    public void setInternalNotes(java.lang.String internalNotes) {
        _cdrModel.setInternalNotes(internalNotes);
    }

    /**
    * Returns the created date of this cdr model.
    *
    * @return the created date of this cdr model
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cdrModel.getCreatedDate();
    }

    /**
    * Sets the created date of this cdr model.
    *
    * @param createdDate the created date of this cdr model
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cdrModel.setCreatedDate(createdDate);
    }

    /**
    * Returns the rule name of this cdr model.
    *
    * @return the rule name of this cdr model
    */
    @Override
    public java.lang.String getRuleName() {
        return _cdrModel.getRuleName();
    }

    /**
    * Sets the rule name of this cdr model.
    *
    * @param ruleName the rule name of this cdr model
    */
    @Override
    public void setRuleName(java.lang.String ruleName) {
        _cdrModel.setRuleName(ruleName);
    }

    /**
    * Returns the cdr model sid of this cdr model.
    *
    * @return the cdr model sid of this cdr model
    */
    @Override
    public int getCdrModelSid() {
        return _cdrModel.getCdrModelSid();
    }

    /**
    * Sets the cdr model sid of this cdr model.
    *
    * @param cdrModelSid the cdr model sid of this cdr model
    */
    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _cdrModel.setCdrModelSid(cdrModelSid);
    }

    /**
    * Returns the rule no of this cdr model.
    *
    * @return the rule no of this cdr model
    */
    @Override
    public java.lang.String getRuleNo() {
        return _cdrModel.getRuleNo();
    }

    /**
    * Sets the rule no of this cdr model.
    *
    * @param ruleNo the rule no of this cdr model
    */
    @Override
    public void setRuleNo(java.lang.String ruleNo) {
        _cdrModel.setRuleNo(ruleNo);
    }

    /**
    * Returns the modified date of this cdr model.
    *
    * @return the modified date of this cdr model
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cdrModel.getModifiedDate();
    }

    /**
    * Sets the modified date of this cdr model.
    *
    * @param modifiedDate the modified date of this cdr model
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cdrModel.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _cdrModel.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cdrModel.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cdrModel.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cdrModel.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cdrModel.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cdrModel.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cdrModel.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cdrModel.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cdrModel.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cdrModel.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cdrModel.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CdrModelWrapper((CdrModel) _cdrModel.clone());
    }

    @Override
    public int compareTo(CdrModel cdrModel) {
        return _cdrModel.compareTo(cdrModel);
    }

    @Override
    public int hashCode() {
        return _cdrModel.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CdrModel> toCacheModel() {
        return _cdrModel.toCacheModel();
    }

    @Override
    public CdrModel toEscapedModel() {
        return new CdrModelWrapper(_cdrModel.toEscapedModel());
    }

    @Override
    public CdrModel toUnescapedModel() {
        return new CdrModelWrapper(_cdrModel.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cdrModel.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cdrModel.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cdrModel.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CdrModelWrapper)) {
            return false;
        }

        CdrModelWrapper cdrModelWrapper = (CdrModelWrapper) obj;

        if (Validator.equals(_cdrModel, cdrModelWrapper._cdrModel)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CdrModel getWrappedCdrModel() {
        return _cdrModel;
    }

    @Override
    public CdrModel getWrappedModel() {
        return _cdrModel;
    }

    @Override
    public void resetOriginalValues() {
        _cdrModel.resetOriginalValues();
    }
}
