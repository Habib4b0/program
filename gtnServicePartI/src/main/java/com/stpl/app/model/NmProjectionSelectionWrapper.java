package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NmProjectionSelection}.
 * </p>
 *
 * @author
 * @see NmProjectionSelection
 * @generated
 */
public class NmProjectionSelectionWrapper implements NmProjectionSelection,
    ModelWrapper<NmProjectionSelection> {
    private NmProjectionSelection _nmProjectionSelection;

    public NmProjectionSelectionWrapper(
        NmProjectionSelection nmProjectionSelection) {
        _nmProjectionSelection = nmProjectionSelection;
    }

    @Override
    public Class<?> getModelClass() {
        return NmProjectionSelection.class;
    }

    @Override
    public String getModelClassName() {
        return NmProjectionSelection.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("screenName", getScreenName());
        attributes.put("nmProjectionSelectionSid", getNmProjectionSelectionSid());
        attributes.put("fieldName", getFieldName());
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

        Integer nmProjectionSelectionSid = (Integer) attributes.get(
                "nmProjectionSelectionSid");

        if (nmProjectionSelectionSid != null) {
            setNmProjectionSelectionSid(nmProjectionSelectionSid);
        }

        String fieldName = (String) attributes.get("fieldName");

        if (fieldName != null) {
            setFieldName(fieldName);
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
    * Returns the primary key of this nm projection selection.
    *
    * @return the primary key of this nm projection selection
    */
    @Override
    public int getPrimaryKey() {
        return _nmProjectionSelection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this nm projection selection.
    *
    * @param primaryKey the primary key of this nm projection selection
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _nmProjectionSelection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the screen name of this nm projection selection.
    *
    * @return the screen name of this nm projection selection
    */
    @Override
    public java.lang.String getScreenName() {
        return _nmProjectionSelection.getScreenName();
    }

    /**
    * Sets the screen name of this nm projection selection.
    *
    * @param screenName the screen name of this nm projection selection
    */
    @Override
    public void setScreenName(java.lang.String screenName) {
        _nmProjectionSelection.setScreenName(screenName);
    }

    /**
    * Returns the nm projection selection sid of this nm projection selection.
    *
    * @return the nm projection selection sid of this nm projection selection
    */
    @Override
    public int getNmProjectionSelectionSid() {
        return _nmProjectionSelection.getNmProjectionSelectionSid();
    }

    /**
    * Sets the nm projection selection sid of this nm projection selection.
    *
    * @param nmProjectionSelectionSid the nm projection selection sid of this nm projection selection
    */
    @Override
    public void setNmProjectionSelectionSid(int nmProjectionSelectionSid) {
        _nmProjectionSelection.setNmProjectionSelectionSid(nmProjectionSelectionSid);
    }

    /**
    * Returns the field name of this nm projection selection.
    *
    * @return the field name of this nm projection selection
    */
    @Override
    public java.lang.String getFieldName() {
        return _nmProjectionSelection.getFieldName();
    }

    /**
    * Sets the field name of this nm projection selection.
    *
    * @param fieldName the field name of this nm projection selection
    */
    @Override
    public void setFieldName(java.lang.String fieldName) {
        _nmProjectionSelection.setFieldName(fieldName);
    }

    /**
    * Returns the field values of this nm projection selection.
    *
    * @return the field values of this nm projection selection
    */
    @Override
    public java.lang.String getFieldValues() {
        return _nmProjectionSelection.getFieldValues();
    }

    /**
    * Sets the field values of this nm projection selection.
    *
    * @param fieldValues the field values of this nm projection selection
    */
    @Override
    public void setFieldValues(java.lang.String fieldValues) {
        _nmProjectionSelection.setFieldValues(fieldValues);
    }

    /**
    * Returns the projection master sid of this nm projection selection.
    *
    * @return the projection master sid of this nm projection selection
    */
    @Override
    public int getProjectionMasterSid() {
        return _nmProjectionSelection.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this nm projection selection.
    *
    * @param projectionMasterSid the projection master sid of this nm projection selection
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _nmProjectionSelection.setProjectionMasterSid(projectionMasterSid);
    }

    @Override
    public boolean isNew() {
        return _nmProjectionSelection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nmProjectionSelection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nmProjectionSelection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nmProjectionSelection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nmProjectionSelection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nmProjectionSelection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nmProjectionSelection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nmProjectionSelection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nmProjectionSelection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nmProjectionSelection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nmProjectionSelection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NmProjectionSelectionWrapper((NmProjectionSelection) _nmProjectionSelection.clone());
    }

    @Override
    public int compareTo(NmProjectionSelection nmProjectionSelection) {
        return _nmProjectionSelection.compareTo(nmProjectionSelection);
    }

    @Override
    public int hashCode() {
        return _nmProjectionSelection.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NmProjectionSelection> toCacheModel() {
        return _nmProjectionSelection.toCacheModel();
    }

    @Override
    public NmProjectionSelection toEscapedModel() {
        return new NmProjectionSelectionWrapper(_nmProjectionSelection.toEscapedModel());
    }

    @Override
    public NmProjectionSelection toUnescapedModel() {
        return new NmProjectionSelectionWrapper(_nmProjectionSelection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nmProjectionSelection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nmProjectionSelection.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nmProjectionSelection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmProjectionSelectionWrapper)) {
            return false;
        }

        NmProjectionSelectionWrapper nmProjectionSelectionWrapper = (NmProjectionSelectionWrapper) obj;

        if (Validator.equals(_nmProjectionSelection,
                    nmProjectionSelectionWrapper._nmProjectionSelection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NmProjectionSelection getWrappedNmProjectionSelection() {
        return _nmProjectionSelection;
    }

    @Override
    public NmProjectionSelection getWrappedModel() {
        return _nmProjectionSelection;
    }

    @Override
    public void resetOriginalValues() {
        _nmProjectionSelection.resetOriginalValues();
    }
}
