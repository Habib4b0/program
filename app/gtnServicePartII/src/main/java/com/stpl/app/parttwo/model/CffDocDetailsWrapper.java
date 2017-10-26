package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffDocDetails}.
 * </p>
 *
 * @author
 * @see CffDocDetails
 * @generated
 */
public class CffDocDetailsWrapper implements CffDocDetails,
    ModelWrapper<CffDocDetails> {
    private CffDocDetails _cffDocDetails;

    public CffDocDetailsWrapper(CffDocDetails cffDocDetails) {
        _cffDocDetails = cffDocDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CffDocDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CffDocDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("fileName", getFileName());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("fileType", getFileType());
        attributes.put("uploadBy", getUploadBy());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("cffDocDetailsSid", getCffDocDetailsSid());
        attributes.put("fileSize", getFileSize());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String fileType = (String) attributes.get("fileType");

        if (fileType != null) {
            setFileType(fileType);
        }

        Integer uploadBy = (Integer) attributes.get("uploadBy");

        if (uploadBy != null) {
            setUploadBy(uploadBy);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer cffDocDetailsSid = (Integer) attributes.get("cffDocDetailsSid");

        if (cffDocDetailsSid != null) {
            setCffDocDetailsSid(cffDocDetailsSid);
        }

        String fileSize = (String) attributes.get("fileSize");

        if (fileSize != null) {
            setFileSize(fileSize);
        }
    }

    /**
    * Returns the primary key of this cff doc details.
    *
    * @return the primary key of this cff doc details
    */
    @Override
    public int getPrimaryKey() {
        return _cffDocDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff doc details.
    *
    * @param primaryKey the primary key of this cff doc details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffDocDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the file name of this cff doc details.
    *
    * @return the file name of this cff doc details
    */
    @Override
    public java.lang.String getFileName() {
        return _cffDocDetails.getFileName();
    }

    /**
    * Sets the file name of this cff doc details.
    *
    * @param fileName the file name of this cff doc details
    */
    @Override
    public void setFileName(java.lang.String fileName) {
        _cffDocDetails.setFileName(fileName);
    }

    /**
    * Returns the upload date of this cff doc details.
    *
    * @return the upload date of this cff doc details
    */
    @Override
    public java.util.Date getUploadDate() {
        return _cffDocDetails.getUploadDate();
    }

    /**
    * Sets the upload date of this cff doc details.
    *
    * @param uploadDate the upload date of this cff doc details
    */
    @Override
    public void setUploadDate(java.util.Date uploadDate) {
        _cffDocDetails.setUploadDate(uploadDate);
    }

    /**
    * Returns the file type of this cff doc details.
    *
    * @return the file type of this cff doc details
    */
    @Override
    public java.lang.String getFileType() {
        return _cffDocDetails.getFileType();
    }

    /**
    * Sets the file type of this cff doc details.
    *
    * @param fileType the file type of this cff doc details
    */
    @Override
    public void setFileType(java.lang.String fileType) {
        _cffDocDetails.setFileType(fileType);
    }

    /**
    * Returns the upload by of this cff doc details.
    *
    * @return the upload by of this cff doc details
    */
    @Override
    public int getUploadBy() {
        return _cffDocDetails.getUploadBy();
    }

    /**
    * Sets the upload by of this cff doc details.
    *
    * @param uploadBy the upload by of this cff doc details
    */
    @Override
    public void setUploadBy(int uploadBy) {
        _cffDocDetails.setUploadBy(uploadBy);
    }

    /**
    * Returns the cff master sid of this cff doc details.
    *
    * @return the cff master sid of this cff doc details
    */
    @Override
    public int getCffMasterSid() {
        return _cffDocDetails.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff doc details.
    *
    * @param cffMasterSid the cff master sid of this cff doc details
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffDocDetails.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the cff doc details sid of this cff doc details.
    *
    * @return the cff doc details sid of this cff doc details
    */
    @Override
    public int getCffDocDetailsSid() {
        return _cffDocDetails.getCffDocDetailsSid();
    }

    /**
    * Sets the cff doc details sid of this cff doc details.
    *
    * @param cffDocDetailsSid the cff doc details sid of this cff doc details
    */
    @Override
    public void setCffDocDetailsSid(int cffDocDetailsSid) {
        _cffDocDetails.setCffDocDetailsSid(cffDocDetailsSid);
    }

    /**
    * Returns the file size of this cff doc details.
    *
    * @return the file size of this cff doc details
    */
    @Override
    public java.lang.String getFileSize() {
        return _cffDocDetails.getFileSize();
    }

    /**
    * Sets the file size of this cff doc details.
    *
    * @param fileSize the file size of this cff doc details
    */
    @Override
    public void setFileSize(java.lang.String fileSize) {
        _cffDocDetails.setFileSize(fileSize);
    }

    @Override
    public boolean isNew() {
        return _cffDocDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffDocDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffDocDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffDocDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffDocDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffDocDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffDocDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffDocDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffDocDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffDocDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffDocDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffDocDetailsWrapper((CffDocDetails) _cffDocDetails.clone());
    }

    @Override
    public int compareTo(CffDocDetails cffDocDetails) {
        return _cffDocDetails.compareTo(cffDocDetails);
    }

    @Override
    public int hashCode() {
        return _cffDocDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffDocDetails> toCacheModel() {
        return _cffDocDetails.toCacheModel();
    }

    @Override
    public CffDocDetails toEscapedModel() {
        return new CffDocDetailsWrapper(_cffDocDetails.toEscapedModel());
    }

    @Override
    public CffDocDetails toUnescapedModel() {
        return new CffDocDetailsWrapper(_cffDocDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffDocDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffDocDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffDocDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffDocDetailsWrapper)) {
            return false;
        }

        CffDocDetailsWrapper cffDocDetailsWrapper = (CffDocDetailsWrapper) obj;

        if (Validator.equals(_cffDocDetails, cffDocDetailsWrapper._cffDocDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffDocDetails getWrappedCffDocDetails() {
        return _cffDocDetails;
    }

    @Override
    public CffDocDetails getWrappedModel() {
        return _cffDocDetails;
    }

    @Override
    public void resetOriginalValues() {
        _cffDocDetails.resetOriginalValues();
    }
}
