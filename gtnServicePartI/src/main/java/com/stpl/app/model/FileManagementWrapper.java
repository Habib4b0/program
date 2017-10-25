package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FileManagement}.
 * </p>
 *
 * @author
 * @see FileManagement
 * @generated
 */
public class FileManagementWrapper implements FileManagement,
    ModelWrapper<FileManagement> {
    private FileManagement _fileManagement;

    public FileManagementWrapper(FileManagement fileManagement) {
        _fileManagement = fileManagement;
    }

    @Override
    public Class<?> getModelClass() {
        return FileManagement.class;
    }

    @Override
    public String getModelClassName() {
        return FileManagement.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("country", getCountry());
        attributes.put("fromPeriod", getFromPeriod());
        attributes.put("versionNo", getVersionNo());
        attributes.put("forecastSource", getForecastSource());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("version", getVersion());
        attributes.put("fileSource", getFileSource());
        attributes.put("toPeriod", getToPeriod());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("fileManagementSid", getFileManagementSid());
        attributes.put("forecastName", getForecastName());
        attributes.put("fileType", getFileType());
        attributes.put("businessUnit", getBusinessUnit());
        attributes.put("company", getCompany());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer country = (Integer) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        Date fromPeriod = (Date) attributes.get("fromPeriod");

        if (fromPeriod != null) {
            setFromPeriod(fromPeriod);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String forecastSource = (String) attributes.get("forecastSource");

        if (forecastSource != null) {
            setForecastSource(forecastSource);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String version = (String) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        String fileSource = (String) attributes.get("fileSource");

        if (fileSource != null) {
            setFileSource(fileSource);
        }

        Date toPeriod = (Date) attributes.get("toPeriod");

        if (toPeriod != null) {
            setToPeriod(toPeriod);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer fileManagementSid = (Integer) attributes.get(
                "fileManagementSid");

        if (fileManagementSid != null) {
            setFileManagementSid(fileManagementSid);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Integer fileType = (Integer) attributes.get("fileType");

        if (fileType != null) {
            setFileType(fileType);
        }

        String businessUnit = (String) attributes.get("businessUnit");

        if (businessUnit != null) {
            setBusinessUnit(businessUnit);
        }

        Integer company = (Integer) attributes.get("company");

        if (company != null) {
            setCompany(company);
        }
    }

    /**
    * Returns the primary key of this file management.
    *
    * @return the primary key of this file management
    */
    @Override
    public int getPrimaryKey() {
        return _fileManagement.getPrimaryKey();
    }

    /**
    * Sets the primary key of this file management.
    *
    * @param primaryKey the primary key of this file management
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _fileManagement.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the country of this file management.
    *
    * @return the country of this file management
    */
    @Override
    public int getCountry() {
        return _fileManagement.getCountry();
    }

    /**
    * Sets the country of this file management.
    *
    * @param country the country of this file management
    */
    @Override
    public void setCountry(int country) {
        _fileManagement.setCountry(country);
    }

    /**
    * Returns the from period of this file management.
    *
    * @return the from period of this file management
    */
    @Override
    public java.util.Date getFromPeriod() {
        return _fileManagement.getFromPeriod();
    }

    /**
    * Sets the from period of this file management.
    *
    * @param fromPeriod the from period of this file management
    */
    @Override
    public void setFromPeriod(java.util.Date fromPeriod) {
        _fileManagement.setFromPeriod(fromPeriod);
    }

    /**
    * Returns the version no of this file management.
    *
    * @return the version no of this file management
    */
    @Override
    public int getVersionNo() {
        return _fileManagement.getVersionNo();
    }

    /**
    * Sets the version no of this file management.
    *
    * @param versionNo the version no of this file management
    */
    @Override
    public void setVersionNo(int versionNo) {
        _fileManagement.setVersionNo(versionNo);
    }

    /**
    * Returns the forecast source of this file management.
    *
    * @return the forecast source of this file management
    */
    @Override
    public java.lang.String getForecastSource() {
        return _fileManagement.getForecastSource();
    }

    /**
    * Sets the forecast source of this file management.
    *
    * @param forecastSource the forecast source of this file management
    */
    @Override
    public void setForecastSource(java.lang.String forecastSource) {
        _fileManagement.setForecastSource(forecastSource);
    }

    /**
    * Returns the modified date of this file management.
    *
    * @return the modified date of this file management
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _fileManagement.getModifiedDate();
    }

    /**
    * Sets the modified date of this file management.
    *
    * @param modifiedDate the modified date of this file management
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _fileManagement.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the created by of this file management.
    *
    * @return the created by of this file management
    */
    @Override
    public int getCreatedBy() {
        return _fileManagement.getCreatedBy();
    }

    /**
    * Sets the created by of this file management.
    *
    * @param createdBy the created by of this file management
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _fileManagement.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this file management.
    *
    * @return the created date of this file management
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _fileManagement.getCreatedDate();
    }

    /**
    * Sets the created date of this file management.
    *
    * @param createdDate the created date of this file management
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _fileManagement.setCreatedDate(createdDate);
    }

    /**
    * Returns the version of this file management.
    *
    * @return the version of this file management
    */
    @Override
    public java.lang.String getVersion() {
        return _fileManagement.getVersion();
    }

    /**
    * Sets the version of this file management.
    *
    * @param version the version of this file management
    */
    @Override
    public void setVersion(java.lang.String version) {
        _fileManagement.setVersion(version);
    }

    /**
    * Returns the file source of this file management.
    *
    * @return the file source of this file management
    */
    @Override
    public java.lang.String getFileSource() {
        return _fileManagement.getFileSource();
    }

    /**
    * Sets the file source of this file management.
    *
    * @param fileSource the file source of this file management
    */
    @Override
    public void setFileSource(java.lang.String fileSource) {
        _fileManagement.setFileSource(fileSource);
    }

    /**
    * Returns the to period of this file management.
    *
    * @return the to period of this file management
    */
    @Override
    public java.util.Date getToPeriod() {
        return _fileManagement.getToPeriod();
    }

    /**
    * Sets the to period of this file management.
    *
    * @param toPeriod the to period of this file management
    */
    @Override
    public void setToPeriod(java.util.Date toPeriod) {
        _fileManagement.setToPeriod(toPeriod);
    }

    /**
    * Returns the modified by of this file management.
    *
    * @return the modified by of this file management
    */
    @Override
    public int getModifiedBy() {
        return _fileManagement.getModifiedBy();
    }

    /**
    * Sets the modified by of this file management.
    *
    * @param modifiedBy the modified by of this file management
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _fileManagement.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the file management sid of this file management.
    *
    * @return the file management sid of this file management
    */
    @Override
    public int getFileManagementSid() {
        return _fileManagement.getFileManagementSid();
    }

    /**
    * Sets the file management sid of this file management.
    *
    * @param fileManagementSid the file management sid of this file management
    */
    @Override
    public void setFileManagementSid(int fileManagementSid) {
        _fileManagement.setFileManagementSid(fileManagementSid);
    }

    /**
    * Returns the forecast name of this file management.
    *
    * @return the forecast name of this file management
    */
    @Override
    public java.lang.String getForecastName() {
        return _fileManagement.getForecastName();
    }

    /**
    * Sets the forecast name of this file management.
    *
    * @param forecastName the forecast name of this file management
    */
    @Override
    public void setForecastName(java.lang.String forecastName) {
        _fileManagement.setForecastName(forecastName);
    }

    /**
    * Returns the file type of this file management.
    *
    * @return the file type of this file management
    */
    @Override
    public int getFileType() {
        return _fileManagement.getFileType();
    }

    /**
    * Sets the file type of this file management.
    *
    * @param fileType the file type of this file management
    */
    @Override
    public void setFileType(int fileType) {
        _fileManagement.setFileType(fileType);
    }

    /**
    * Returns the business unit of this file management.
    *
    * @return the business unit of this file management
    */
    @Override
    public java.lang.String getBusinessUnit() {
        return _fileManagement.getBusinessUnit();
    }

    /**
    * Sets the business unit of this file management.
    *
    * @param businessUnit the business unit of this file management
    */
    @Override
    public void setBusinessUnit(java.lang.String businessUnit) {
        _fileManagement.setBusinessUnit(businessUnit);
    }

    /**
    * Returns the company of this file management.
    *
    * @return the company of this file management
    */
    @Override
    public int getCompany() {
        return _fileManagement.getCompany();
    }

    /**
    * Sets the company of this file management.
    *
    * @param company the company of this file management
    */
    @Override
    public void setCompany(int company) {
        _fileManagement.setCompany(company);
    }

    @Override
    public boolean isNew() {
        return _fileManagement.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _fileManagement.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _fileManagement.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _fileManagement.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _fileManagement.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _fileManagement.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _fileManagement.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _fileManagement.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _fileManagement.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _fileManagement.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _fileManagement.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FileManagementWrapper((FileManagement) _fileManagement.clone());
    }

    @Override
    public int compareTo(FileManagement fileManagement) {
        return _fileManagement.compareTo(fileManagement);
    }

    @Override
    public int hashCode() {
        return _fileManagement.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<FileManagement> toCacheModel() {
        return _fileManagement.toCacheModel();
    }

    @Override
    public FileManagement toEscapedModel() {
        return new FileManagementWrapper(_fileManagement.toEscapedModel());
    }

    @Override
    public FileManagement toUnescapedModel() {
        return new FileManagementWrapper(_fileManagement.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _fileManagement.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _fileManagement.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _fileManagement.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FileManagementWrapper)) {
            return false;
        }

        FileManagementWrapper fileManagementWrapper = (FileManagementWrapper) obj;

        if (Validator.equals(_fileManagement,
                    fileManagementWrapper._fileManagement)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public FileManagement getWrappedFileManagement() {
        return _fileManagement;
    }

    @Override
    public FileManagement getWrappedModel() {
        return _fileManagement;
    }

    @Override
    public void resetOriginalValues() {
        _fileManagement.resetOriginalValues();
    }
}
