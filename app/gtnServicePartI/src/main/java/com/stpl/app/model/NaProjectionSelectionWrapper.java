package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NaProjectionSelection}.
 * </p>
 *
 * @author
 * @see NaProjectionSelection
 * @generated
 */
public class NaProjectionSelectionWrapper implements NaProjectionSelection,
    ModelWrapper<NaProjectionSelection> {
    private NaProjectionSelection _naProjectionSelection;

    public NaProjectionSelectionWrapper(
        NaProjectionSelection naProjectionSelection) {
        _naProjectionSelection = naProjectionSelection;
    }

    @Override
    public Class<?> getModelClass() {
        return NaProjectionSelection.class;
    }

    @Override
    public String getModelClassName() {
        return NaProjectionSelection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("screenName", getScreenName());
        attributes.put("fieldName", getFieldName());
        attributes.put("fieldValues", getFieldValues());
        attributes.put("naProjectionSelectionSid", getNaProjectionSelectionSid());
        attributes.put("naProjMasterSid", getNaProjMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }

        String fieldName = (String) attributes.get("fieldName");

        if (fieldName != null) {
            setFieldName(fieldName);
        }

        String fieldValues = (String) attributes.get("fieldValues");

        if (fieldValues != null) {
            setFieldValues(fieldValues);
        }

        Integer naProjectionSelectionSid = (Integer) attributes.get(
                "naProjectionSelectionSid");

        if (naProjectionSelectionSid != null) {
            setNaProjectionSelectionSid(naProjectionSelectionSid);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }
    }

    /**
    * Returns the primary key of this na projection selection.
    *
    * @return the primary key of this na projection selection
    */
    @Override
    public int getPrimaryKey() {
        return _naProjectionSelection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this na projection selection.
    *
    * @param primaryKey the primary key of this na projection selection
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _naProjectionSelection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the screen name of this na projection selection.
    *
    * @return the screen name of this na projection selection
    */
    @Override
    public java.lang.String getScreenName() {
        return _naProjectionSelection.getScreenName();
    }

    /**
    * Sets the screen name of this na projection selection.
    *
    * @param screenName the screen name of this na projection selection
    */
    @Override
    public void setScreenName(java.lang.String screenName) {
        _naProjectionSelection.setScreenName(screenName);
    }

    /**
    * Returns the field name of this na projection selection.
    *
    * @return the field name of this na projection selection
    */
    @Override
    public java.lang.String getFieldName() {
        return _naProjectionSelection.getFieldName();
    }

    /**
    * Sets the field name of this na projection selection.
    *
    * @param fieldName the field name of this na projection selection
    */
    @Override
    public void setFieldName(java.lang.String fieldName) {
        _naProjectionSelection.setFieldName(fieldName);
    }

    /**
    * Returns the field values of this na projection selection.
    *
    * @return the field values of this na projection selection
    */
    @Override
    public java.lang.String getFieldValues() {
        return _naProjectionSelection.getFieldValues();
    }

    /**
    * Sets the field values of this na projection selection.
    *
    * @param fieldValues the field values of this na projection selection
    */
    @Override
    public void setFieldValues(java.lang.String fieldValues) {
        _naProjectionSelection.setFieldValues(fieldValues);
    }

    /**
    * Returns the na projection selection sid of this na projection selection.
    *
    * @return the na projection selection sid of this na projection selection
    */
    @Override
    public int getNaProjectionSelectionSid() {
        return _naProjectionSelection.getNaProjectionSelectionSid();
    }

    /**
    * Sets the na projection selection sid of this na projection selection.
    *
    * @param naProjectionSelectionSid the na projection selection sid of this na projection selection
    */
    @Override
    public void setNaProjectionSelectionSid(int naProjectionSelectionSid) {
        _naProjectionSelection.setNaProjectionSelectionSid(naProjectionSelectionSid);
    }

    /**
    * Returns the na proj master sid of this na projection selection.
    *
    * @return the na proj master sid of this na projection selection
    */
    @Override
    public int getNaProjMasterSid() {
        return _naProjectionSelection.getNaProjMasterSid();
    }

    /**
    * Sets the na proj master sid of this na projection selection.
    *
    * @param naProjMasterSid the na proj master sid of this na projection selection
    */
    @Override
    public void setNaProjMasterSid(int naProjMasterSid) {
        _naProjectionSelection.setNaProjMasterSid(naProjMasterSid);
    }

    @Override
    public boolean isNew() {
        return _naProjectionSelection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _naProjectionSelection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _naProjectionSelection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _naProjectionSelection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _naProjectionSelection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _naProjectionSelection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _naProjectionSelection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _naProjectionSelection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _naProjectionSelection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _naProjectionSelection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _naProjectionSelection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NaProjectionSelectionWrapper((NaProjectionSelection) _naProjectionSelection.clone());
    }

    @Override
    public int compareTo(NaProjectionSelection naProjectionSelection) {
        return _naProjectionSelection.compareTo(naProjectionSelection);
    }

    @Override
    public int hashCode() {
        return _naProjectionSelection.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NaProjectionSelection> toCacheModel() {
        return _naProjectionSelection.toCacheModel();
    }

    @Override
    public NaProjectionSelection toEscapedModel() {
        return new NaProjectionSelectionWrapper(_naProjectionSelection.toEscapedModel());
    }

    @Override
    public NaProjectionSelection toUnescapedModel() {
        return new NaProjectionSelectionWrapper(_naProjectionSelection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _naProjectionSelection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _naProjectionSelection.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _naProjectionSelection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NaProjectionSelectionWrapper)) {
            return false;
        }

        NaProjectionSelectionWrapper naProjectionSelectionWrapper = (NaProjectionSelectionWrapper) obj;

        if (Validator.equals(_naProjectionSelection,
                    naProjectionSelectionWrapper._naProjectionSelection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NaProjectionSelection getWrappedNaProjectionSelection() {
        return _naProjectionSelection;
    }

    @Override
    public NaProjectionSelection getWrappedModel() {
        return _naProjectionSelection;
    }

    @Override
    public void resetOriginalValues() {
        _naProjectionSelection.resetOriginalValues();
    }
}
