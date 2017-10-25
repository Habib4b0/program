package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChProjectionSelection}.
 * </p>
 *
 * @author
 * @see ChProjectionSelection
 * @generated
 */
public class ChProjectionSelectionWrapper implements ChProjectionSelection,
    ModelWrapper<ChProjectionSelection> {
    private ChProjectionSelection _chProjectionSelection;

    public ChProjectionSelectionWrapper(
        ChProjectionSelection chProjectionSelection) {
        _chProjectionSelection = chProjectionSelection;
    }

    @Override
    public Class<?> getModelClass() {
        return ChProjectionSelection.class;
    }

    @Override
    public String getModelClassName() {
        return ChProjectionSelection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("screenName", getScreenName());
        attributes.put("fieldName", getFieldName());
        attributes.put("chProjectionSelectionSid", getChProjectionSelectionSid());
        attributes.put("fieldValues", getFieldValues());
        attributes.put("projectionMasterSid", getProjectionMasterSid());

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

        Integer chProjectionSelectionSid = (Integer) attributes.get(
                "chProjectionSelectionSid");

        if (chProjectionSelectionSid != null) {
            setChProjectionSelectionSid(chProjectionSelectionSid);
        }

        String fieldValues = (String) attributes.get("fieldValues");

        if (fieldValues != null) {
            setFieldValues(fieldValues);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }
    }

    /**
    * Returns the primary key of this ch projection selection.
    *
    * @return the primary key of this ch projection selection
    */
    @Override
    public int getPrimaryKey() {
        return _chProjectionSelection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ch projection selection.
    *
    * @param primaryKey the primary key of this ch projection selection
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _chProjectionSelection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the screen name of this ch projection selection.
    *
    * @return the screen name of this ch projection selection
    */
    @Override
    public java.lang.String getScreenName() {
        return _chProjectionSelection.getScreenName();
    }

    /**
    * Sets the screen name of this ch projection selection.
    *
    * @param screenName the screen name of this ch projection selection
    */
    @Override
    public void setScreenName(java.lang.String screenName) {
        _chProjectionSelection.setScreenName(screenName);
    }

    /**
    * Returns the field name of this ch projection selection.
    *
    * @return the field name of this ch projection selection
    */
    @Override
    public java.lang.String getFieldName() {
        return _chProjectionSelection.getFieldName();
    }

    /**
    * Sets the field name of this ch projection selection.
    *
    * @param fieldName the field name of this ch projection selection
    */
    @Override
    public void setFieldName(java.lang.String fieldName) {
        _chProjectionSelection.setFieldName(fieldName);
    }

    /**
    * Returns the ch projection selection sid of this ch projection selection.
    *
    * @return the ch projection selection sid of this ch projection selection
    */
    @Override
    public int getChProjectionSelectionSid() {
        return _chProjectionSelection.getChProjectionSelectionSid();
    }

    /**
    * Sets the ch projection selection sid of this ch projection selection.
    *
    * @param chProjectionSelectionSid the ch projection selection sid of this ch projection selection
    */
    @Override
    public void setChProjectionSelectionSid(int chProjectionSelectionSid) {
        _chProjectionSelection.setChProjectionSelectionSid(chProjectionSelectionSid);
    }

    /**
    * Returns the field values of this ch projection selection.
    *
    * @return the field values of this ch projection selection
    */
    @Override
    public java.lang.String getFieldValues() {
        return _chProjectionSelection.getFieldValues();
    }

    /**
    * Sets the field values of this ch projection selection.
    *
    * @param fieldValues the field values of this ch projection selection
    */
    @Override
    public void setFieldValues(java.lang.String fieldValues) {
        _chProjectionSelection.setFieldValues(fieldValues);
    }

    /**
    * Returns the projection master sid of this ch projection selection.
    *
    * @return the projection master sid of this ch projection selection
    */
    @Override
    public int getProjectionMasterSid() {
        return _chProjectionSelection.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this ch projection selection.
    *
    * @param projectionMasterSid the projection master sid of this ch projection selection
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _chProjectionSelection.setProjectionMasterSid(projectionMasterSid);
    }

    @Override
    public boolean isNew() {
        return _chProjectionSelection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _chProjectionSelection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _chProjectionSelection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _chProjectionSelection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _chProjectionSelection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _chProjectionSelection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _chProjectionSelection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _chProjectionSelection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _chProjectionSelection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _chProjectionSelection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _chProjectionSelection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ChProjectionSelectionWrapper((ChProjectionSelection) _chProjectionSelection.clone());
    }

    @Override
    public int compareTo(ChProjectionSelection chProjectionSelection) {
        return _chProjectionSelection.compareTo(chProjectionSelection);
    }

    @Override
    public int hashCode() {
        return _chProjectionSelection.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ChProjectionSelection> toCacheModel() {
        return _chProjectionSelection.toCacheModel();
    }

    @Override
    public ChProjectionSelection toEscapedModel() {
        return new ChProjectionSelectionWrapper(_chProjectionSelection.toEscapedModel());
    }

    @Override
    public ChProjectionSelection toUnescapedModel() {
        return new ChProjectionSelectionWrapper(_chProjectionSelection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _chProjectionSelection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _chProjectionSelection.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _chProjectionSelection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChProjectionSelectionWrapper)) {
            return false;
        }

        ChProjectionSelectionWrapper chProjectionSelectionWrapper = (ChProjectionSelectionWrapper) obj;

        if (Validator.equals(_chProjectionSelection,
                    chProjectionSelectionWrapper._chProjectionSelection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ChProjectionSelection getWrappedChProjectionSelection() {
        return _chProjectionSelection;
    }

    @Override
    public ChProjectionSelection getWrappedModel() {
        return _chProjectionSelection;
    }

    @Override
    public void resetOriginalValues() {
        _chProjectionSelection.resetOriginalValues();
    }
}
