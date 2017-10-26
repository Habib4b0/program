package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AdditionalNotes}.
 * </p>
 *
 * @author
 * @see AdditionalNotes
 * @generated
 */
public class AdditionalNotesWrapper implements AdditionalNotes,
    ModelWrapper<AdditionalNotes> {
    private AdditionalNotes _additionalNotes;

    public AdditionalNotesWrapper(AdditionalNotes additionalNotes) {
        _additionalNotes = additionalNotes;
    }

    @Override
    public Class<?> getModelClass() {
        return AdditionalNotes.class;
    }

    @Override
    public String getModelClassName() {
        return AdditionalNotes.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("forecastType", getForecastType());
        attributes.put("additionalNotesId", getAdditionalNotesId());
        attributes.put("projectionId", getProjectionId());
        attributes.put("notes", getNotes());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        Integer additionalNotesId = (Integer) attributes.get(
                "additionalNotesId");

        if (additionalNotesId != null) {
            setAdditionalNotesId(additionalNotesId);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }
    }

    /**
    * Returns the primary key of this additional notes.
    *
    * @return the primary key of this additional notes
    */
    @Override
    public int getPrimaryKey() {
        return _additionalNotes.getPrimaryKey();
    }

    /**
    * Sets the primary key of this additional notes.
    *
    * @param primaryKey the primary key of this additional notes
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _additionalNotes.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this additional notes.
    *
    * @return the created date of this additional notes
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _additionalNotes.getCreatedDate();
    }

    /**
    * Sets the created date of this additional notes.
    *
    * @param createdDate the created date of this additional notes
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _additionalNotes.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this additional notes.
    *
    * @return the created by of this additional notes
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _additionalNotes.getCreatedBy();
    }

    /**
    * Sets the created by of this additional notes.
    *
    * @param createdBy the created by of this additional notes
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _additionalNotes.setCreatedBy(createdBy);
    }

    /**
    * Returns the forecast type of this additional notes.
    *
    * @return the forecast type of this additional notes
    */
    @Override
    public java.lang.String getForecastType() {
        return _additionalNotes.getForecastType();
    }

    /**
    * Sets the forecast type of this additional notes.
    *
    * @param forecastType the forecast type of this additional notes
    */
    @Override
    public void setForecastType(java.lang.String forecastType) {
        _additionalNotes.setForecastType(forecastType);
    }

    /**
    * Returns the additional notes ID of this additional notes.
    *
    * @return the additional notes ID of this additional notes
    */
    @Override
    public int getAdditionalNotesId() {
        return _additionalNotes.getAdditionalNotesId();
    }

    /**
    * Sets the additional notes ID of this additional notes.
    *
    * @param additionalNotesId the additional notes ID of this additional notes
    */
    @Override
    public void setAdditionalNotesId(int additionalNotesId) {
        _additionalNotes.setAdditionalNotesId(additionalNotesId);
    }

    /**
    * Returns the projection ID of this additional notes.
    *
    * @return the projection ID of this additional notes
    */
    @Override
    public int getProjectionId() {
        return _additionalNotes.getProjectionId();
    }

    /**
    * Sets the projection ID of this additional notes.
    *
    * @param projectionId the projection ID of this additional notes
    */
    @Override
    public void setProjectionId(int projectionId) {
        _additionalNotes.setProjectionId(projectionId);
    }

    /**
    * Returns the notes of this additional notes.
    *
    * @return the notes of this additional notes
    */
    @Override
    public java.lang.String getNotes() {
        return _additionalNotes.getNotes();
    }

    /**
    * Sets the notes of this additional notes.
    *
    * @param notes the notes of this additional notes
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _additionalNotes.setNotes(notes);
    }

    @Override
    public boolean isNew() {
        return _additionalNotes.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _additionalNotes.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _additionalNotes.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _additionalNotes.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _additionalNotes.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _additionalNotes.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _additionalNotes.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _additionalNotes.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _additionalNotes.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _additionalNotes.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _additionalNotes.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AdditionalNotesWrapper((AdditionalNotes) _additionalNotes.clone());
    }

    @Override
    public int compareTo(AdditionalNotes additionalNotes) {
        return _additionalNotes.compareTo(additionalNotes);
    }

    @Override
    public int hashCode() {
        return _additionalNotes.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AdditionalNotes> toCacheModel() {
        return _additionalNotes.toCacheModel();
    }

    @Override
    public AdditionalNotes toEscapedModel() {
        return new AdditionalNotesWrapper(_additionalNotes.toEscapedModel());
    }

    @Override
    public AdditionalNotes toUnescapedModel() {
        return new AdditionalNotesWrapper(_additionalNotes.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _additionalNotes.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _additionalNotes.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _additionalNotes.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AdditionalNotesWrapper)) {
            return false;
        }

        AdditionalNotesWrapper additionalNotesWrapper = (AdditionalNotesWrapper) obj;

        if (Validator.equals(_additionalNotes,
                    additionalNotesWrapper._additionalNotes)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AdditionalNotes getWrappedAdditionalNotes() {
        return _additionalNotes;
    }

    @Override
    public AdditionalNotes getWrappedModel() {
        return _additionalNotes;
    }

    @Override
    public void resetOriginalValues() {
        _additionalNotes.resetOriginalValues();
    }
}
