package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DocDetails}.
 * </p>
 *
 * @author
 * @see DocDetails
 * @generated
 */
public class DocDetailsWrapper implements DocDetails, ModelWrapper<DocDetails> {
    private DocDetails _docDetails;

    public DocDetailsWrapper(DocDetails docDetails) {
        _docDetails = docDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return DocDetails.class;
    }

    @Override
    public String getModelClassName() {
        return DocDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("fileName", getFileName());
        attributes.put("fileType", getFileType());
        attributes.put("uploadedBy", getUploadedBy());
        attributes.put("forecastType", getForecastType());
        attributes.put("projectionId", getProjectionId());
        attributes.put("docDetailsId", getDocDetailsId());
        attributes.put("uploadedDate", getUploadedDate());
        attributes.put("fileSize", getFileSize());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        String fileType = (String) attributes.get("fileType");

        if (fileType != null) {
            setFileType(fileType);
        }

        String uploadedBy = (String) attributes.get("uploadedBy");

        if (uploadedBy != null) {
            setUploadedBy(uploadedBy);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }

        Integer docDetailsId = (Integer) attributes.get("docDetailsId");

        if (docDetailsId != null) {
            setDocDetailsId(docDetailsId);
        }

        Date uploadedDate = (Date) attributes.get("uploadedDate");

        if (uploadedDate != null) {
            setUploadedDate(uploadedDate);
        }

        String fileSize = (String) attributes.get("fileSize");

        if (fileSize != null) {
            setFileSize(fileSize);
        }
    }

    /**
    * Returns the primary key of this doc details.
    *
    * @return the primary key of this doc details
    */
    @Override
    public int getPrimaryKey() {
        return _docDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this doc details.
    *
    * @param primaryKey the primary key of this doc details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _docDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the file name of this doc details.
    *
    * @return the file name of this doc details
    */
    @Override
    public java.lang.String getFileName() {
        return _docDetails.getFileName();
    }

    /**
    * Sets the file name of this doc details.
    *
    * @param fileName the file name of this doc details
    */
    @Override
    public void setFileName(java.lang.String fileName) {
        _docDetails.setFileName(fileName);
    }

    /**
    * Returns the file type of this doc details.
    *
    * @return the file type of this doc details
    */
    @Override
    public java.lang.String getFileType() {
        return _docDetails.getFileType();
    }

    /**
    * Sets the file type of this doc details.
    *
    * @param fileType the file type of this doc details
    */
    @Override
    public void setFileType(java.lang.String fileType) {
        _docDetails.setFileType(fileType);
    }

    /**
    * Returns the uploaded by of this doc details.
    *
    * @return the uploaded by of this doc details
    */
    @Override
    public java.lang.String getUploadedBy() {
        return _docDetails.getUploadedBy();
    }

    /**
    * Sets the uploaded by of this doc details.
    *
    * @param uploadedBy the uploaded by of this doc details
    */
    @Override
    public void setUploadedBy(java.lang.String uploadedBy) {
        _docDetails.setUploadedBy(uploadedBy);
    }

    /**
    * Returns the forecast type of this doc details.
    *
    * @return the forecast type of this doc details
    */
    @Override
    public java.lang.String getForecastType() {
        return _docDetails.getForecastType();
    }

    /**
    * Sets the forecast type of this doc details.
    *
    * @param forecastType the forecast type of this doc details
    */
    @Override
    public void setForecastType(java.lang.String forecastType) {
        _docDetails.setForecastType(forecastType);
    }

    /**
    * Returns the projection ID of this doc details.
    *
    * @return the projection ID of this doc details
    */
    @Override
    public int getProjectionId() {
        return _docDetails.getProjectionId();
    }

    /**
    * Sets the projection ID of this doc details.
    *
    * @param projectionId the projection ID of this doc details
    */
    @Override
    public void setProjectionId(int projectionId) {
        _docDetails.setProjectionId(projectionId);
    }

    /**
    * Returns the doc details ID of this doc details.
    *
    * @return the doc details ID of this doc details
    */
    @Override
    public int getDocDetailsId() {
        return _docDetails.getDocDetailsId();
    }

    /**
    * Sets the doc details ID of this doc details.
    *
    * @param docDetailsId the doc details ID of this doc details
    */
    @Override
    public void setDocDetailsId(int docDetailsId) {
        _docDetails.setDocDetailsId(docDetailsId);
    }

    /**
    * Returns the uploaded date of this doc details.
    *
    * @return the uploaded date of this doc details
    */
    @Override
    public java.util.Date getUploadedDate() {
        return _docDetails.getUploadedDate();
    }

    /**
    * Sets the uploaded date of this doc details.
    *
    * @param uploadedDate the uploaded date of this doc details
    */
    @Override
    public void setUploadedDate(java.util.Date uploadedDate) {
        _docDetails.setUploadedDate(uploadedDate);
    }

    /**
    * Returns the file size of this doc details.
    *
    * @return the file size of this doc details
    */
    @Override
    public java.lang.String getFileSize() {
        return _docDetails.getFileSize();
    }

    /**
    * Sets the file size of this doc details.
    *
    * @param fileSize the file size of this doc details
    */
    @Override
    public void setFileSize(java.lang.String fileSize) {
        _docDetails.setFileSize(fileSize);
    }

    @Override
    public boolean isNew() {
        return _docDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _docDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _docDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _docDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _docDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _docDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _docDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _docDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _docDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _docDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _docDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DocDetailsWrapper((DocDetails) _docDetails.clone());
    }

    @Override
    public int compareTo(DocDetails docDetails) {
        return _docDetails.compareTo(docDetails);
    }

    @Override
    public int hashCode() {
        return _docDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<DocDetails> toCacheModel() {
        return _docDetails.toCacheModel();
    }

    @Override
    public DocDetails toEscapedModel() {
        return new DocDetailsWrapper(_docDetails.toEscapedModel());
    }

    @Override
    public DocDetails toUnescapedModel() {
        return new DocDetailsWrapper(_docDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _docDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _docDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _docDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DocDetailsWrapper)) {
            return false;
        }

        DocDetailsWrapper docDetailsWrapper = (DocDetailsWrapper) obj;

        if (Validator.equals(_docDetails, docDetailsWrapper._docDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DocDetails getWrappedDocDetails() {
        return _docDetails;
    }

    @Override
    public DocDetails getWrappedModel() {
        return _docDetails;
    }

    @Override
    public void resetOriginalValues() {
        _docDetails.resetOriginalValues();
    }
}
