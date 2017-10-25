package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MProjectionSelection}.
 * </p>
 *
 * @author
 * @see MProjectionSelection
 * @generated
 */
public class MProjectionSelectionWrapper implements MProjectionSelection,
    ModelWrapper<MProjectionSelection> {
    private MProjectionSelection _mProjectionSelection;

    public MProjectionSelectionWrapper(
        MProjectionSelection mProjectionSelection) {
        _mProjectionSelection = mProjectionSelection;
    }

    @Override
    public Class<?> getModelClass() {
        return MProjectionSelection.class;
    }

    @Override
    public String getModelClassName() {
        return MProjectionSelection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("mProjectionSelectionSid", getMProjectionSelectionSid());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("fieldValues", getFieldValues());
        attributes.put("fieldName", getFieldName());
        attributes.put("screenName", getScreenName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer mProjectionSelectionSid = (Integer) attributes.get(
                "mProjectionSelectionSid");

        if (mProjectionSelectionSid != null) {
            setMProjectionSelectionSid(mProjectionSelectionSid);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        String fieldValues = (String) attributes.get("fieldValues");

        if (fieldValues != null) {
            setFieldValues(fieldValues);
        }

        String fieldName = (String) attributes.get("fieldName");

        if (fieldName != null) {
            setFieldName(fieldName);
        }

        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }
    }

    /**
    * Returns the primary key of this m projection selection.
    *
    * @return the primary key of this m projection selection
    */
    @Override
    public int getPrimaryKey() {
        return _mProjectionSelection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this m projection selection.
    *
    * @param primaryKey the primary key of this m projection selection
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _mProjectionSelection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the m projection selection sid of this m projection selection.
    *
    * @return the m projection selection sid of this m projection selection
    */
    @Override
    public int getMProjectionSelectionSid() {
        return _mProjectionSelection.getMProjectionSelectionSid();
    }

    /**
    * Sets the m projection selection sid of this m projection selection.
    *
    * @param mProjectionSelectionSid the m projection selection sid of this m projection selection
    */
    @Override
    public void setMProjectionSelectionSid(int mProjectionSelectionSid) {
        _mProjectionSelection.setMProjectionSelectionSid(mProjectionSelectionSid);
    }

    /**
    * Returns the projection master sid of this m projection selection.
    *
    * @return the projection master sid of this m projection selection
    */
    @Override
    public int getProjectionMasterSid() {
        return _mProjectionSelection.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this m projection selection.
    *
    * @param projectionMasterSid the projection master sid of this m projection selection
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _mProjectionSelection.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the field values of this m projection selection.
    *
    * @return the field values of this m projection selection
    */
    @Override
    public java.lang.String getFieldValues() {
        return _mProjectionSelection.getFieldValues();
    }

    /**
    * Sets the field values of this m projection selection.
    *
    * @param fieldValues the field values of this m projection selection
    */
    @Override
    public void setFieldValues(java.lang.String fieldValues) {
        _mProjectionSelection.setFieldValues(fieldValues);
    }

    /**
    * Returns the field name of this m projection selection.
    *
    * @return the field name of this m projection selection
    */
    @Override
    public java.lang.String getFieldName() {
        return _mProjectionSelection.getFieldName();
    }

    /**
    * Sets the field name of this m projection selection.
    *
    * @param fieldName the field name of this m projection selection
    */
    @Override
    public void setFieldName(java.lang.String fieldName) {
        _mProjectionSelection.setFieldName(fieldName);
    }

    /**
    * Returns the screen name of this m projection selection.
    *
    * @return the screen name of this m projection selection
    */
    @Override
    public java.lang.String getScreenName() {
        return _mProjectionSelection.getScreenName();
    }

    /**
    * Sets the screen name of this m projection selection.
    *
    * @param screenName the screen name of this m projection selection
    */
    @Override
    public void setScreenName(java.lang.String screenName) {
        _mProjectionSelection.setScreenName(screenName);
    }

    @Override
    public boolean isNew() {
        return _mProjectionSelection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _mProjectionSelection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _mProjectionSelection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _mProjectionSelection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _mProjectionSelection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _mProjectionSelection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _mProjectionSelection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _mProjectionSelection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _mProjectionSelection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _mProjectionSelection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _mProjectionSelection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MProjectionSelectionWrapper((MProjectionSelection) _mProjectionSelection.clone());
    }

    @Override
    public int compareTo(MProjectionSelection mProjectionSelection) {
        return _mProjectionSelection.compareTo(mProjectionSelection);
    }

    @Override
    public int hashCode() {
        return _mProjectionSelection.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MProjectionSelection> toCacheModel() {
        return _mProjectionSelection.toCacheModel();
    }

    @Override
    public MProjectionSelection toEscapedModel() {
        return new MProjectionSelectionWrapper(_mProjectionSelection.toEscapedModel());
    }

    @Override
    public MProjectionSelection toUnescapedModel() {
        return new MProjectionSelectionWrapper(_mProjectionSelection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _mProjectionSelection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _mProjectionSelection.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _mProjectionSelection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MProjectionSelectionWrapper)) {
            return false;
        }

        MProjectionSelectionWrapper mProjectionSelectionWrapper = (MProjectionSelectionWrapper) obj;

        if (Validator.equals(_mProjectionSelection,
                    mProjectionSelectionWrapper._mProjectionSelection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MProjectionSelection getWrappedMProjectionSelection() {
        return _mProjectionSelection;
    }

    @Override
    public MProjectionSelection getWrappedModel() {
        return _mProjectionSelection;
    }

    @Override
    public void resetOriginalValues() {
        _mProjectionSelection.resetOriginalValues();
    }
}
