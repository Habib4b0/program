package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MasterDataFiles}.
 * </p>
 *
 * @author
 * @see MasterDataFiles
 * @generated
 */
public class MasterDataFilesWrapper implements MasterDataFiles,
    ModelWrapper<MasterDataFiles> {
    private MasterDataFiles _masterDataFiles;

    public MasterDataFilesWrapper(MasterDataFiles masterDataFiles) {
        _masterDataFiles = masterDataFiles;
    }

    @Override
    public Class<?> getModelClass() {
        return MasterDataFiles.class;
    }

    @Override
    public String getModelClassName() {
        return MasterDataFiles.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("masterTableSid", getMasterTableSid());
        attributes.put("masterDataFilesSid", getMasterDataFilesSid());
        attributes.put("masterTableName", getMasterTableName());
        attributes.put("filePath", getFilePath());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer masterTableSid = (Integer) attributes.get("masterTableSid");

        if (masterTableSid != null) {
            setMasterTableSid(masterTableSid);
        }

        Integer masterDataFilesSid = (Integer) attributes.get(
                "masterDataFilesSid");

        if (masterDataFilesSid != null) {
            setMasterDataFilesSid(masterDataFilesSid);
        }

        String masterTableName = (String) attributes.get("masterTableName");

        if (masterTableName != null) {
            setMasterTableName(masterTableName);
        }

        String filePath = (String) attributes.get("filePath");

        if (filePath != null) {
            setFilePath(filePath);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }
    }

    /**
    * Returns the primary key of this master data files.
    *
    * @return the primary key of this master data files
    */
    @Override
    public int getPrimaryKey() {
        return _masterDataFiles.getPrimaryKey();
    }

    /**
    * Sets the primary key of this master data files.
    *
    * @param primaryKey the primary key of this master data files
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _masterDataFiles.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the master table sid of this master data files.
    *
    * @return the master table sid of this master data files
    */
    @Override
    public int getMasterTableSid() {
        return _masterDataFiles.getMasterTableSid();
    }

    /**
    * Sets the master table sid of this master data files.
    *
    * @param masterTableSid the master table sid of this master data files
    */
    @Override
    public void setMasterTableSid(int masterTableSid) {
        _masterDataFiles.setMasterTableSid(masterTableSid);
    }

    /**
    * Returns the master data files sid of this master data files.
    *
    * @return the master data files sid of this master data files
    */
    @Override
    public int getMasterDataFilesSid() {
        return _masterDataFiles.getMasterDataFilesSid();
    }

    /**
    * Sets the master data files sid of this master data files.
    *
    * @param masterDataFilesSid the master data files sid of this master data files
    */
    @Override
    public void setMasterDataFilesSid(int masterDataFilesSid) {
        _masterDataFiles.setMasterDataFilesSid(masterDataFilesSid);
    }

    /**
    * Returns the master table name of this master data files.
    *
    * @return the master table name of this master data files
    */
    @Override
    public java.lang.String getMasterTableName() {
        return _masterDataFiles.getMasterTableName();
    }

    /**
    * Sets the master table name of this master data files.
    *
    * @param masterTableName the master table name of this master data files
    */
    @Override
    public void setMasterTableName(java.lang.String masterTableName) {
        _masterDataFiles.setMasterTableName(masterTableName);
    }

    /**
    * Returns the file path of this master data files.
    *
    * @return the file path of this master data files
    */
    @Override
    public java.lang.String getFilePath() {
        return _masterDataFiles.getFilePath();
    }

    /**
    * Sets the file path of this master data files.
    *
    * @param filePath the file path of this master data files
    */
    @Override
    public void setFilePath(java.lang.String filePath) {
        _masterDataFiles.setFilePath(filePath);
    }

    /**
    * Returns the created date of this master data files.
    *
    * @return the created date of this master data files
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _masterDataFiles.getCreatedDate();
    }

    /**
    * Sets the created date of this master data files.
    *
    * @param createdDate the created date of this master data files
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _masterDataFiles.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this master data files.
    *
    * @return the created by of this master data files
    */
    @Override
    public int getCreatedBy() {
        return _masterDataFiles.getCreatedBy();
    }

    /**
    * Sets the created by of this master data files.
    *
    * @param createdBy the created by of this master data files
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _masterDataFiles.setCreatedBy(createdBy);
    }

    @Override
    public boolean isNew() {
        return _masterDataFiles.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _masterDataFiles.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _masterDataFiles.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _masterDataFiles.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _masterDataFiles.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _masterDataFiles.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _masterDataFiles.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _masterDataFiles.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _masterDataFiles.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _masterDataFiles.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _masterDataFiles.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MasterDataFilesWrapper((MasterDataFiles) _masterDataFiles.clone());
    }

    @Override
    public int compareTo(MasterDataFiles masterDataFiles) {
        return _masterDataFiles.compareTo(masterDataFiles);
    }

    @Override
    public int hashCode() {
        return _masterDataFiles.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MasterDataFiles> toCacheModel() {
        return _masterDataFiles.toCacheModel();
    }

    @Override
    public MasterDataFiles toEscapedModel() {
        return new MasterDataFilesWrapper(_masterDataFiles.toEscapedModel());
    }

    @Override
    public MasterDataFiles toUnescapedModel() {
        return new MasterDataFilesWrapper(_masterDataFiles.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _masterDataFiles.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _masterDataFiles.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _masterDataFiles.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MasterDataFilesWrapper)) {
            return false;
        }

        MasterDataFilesWrapper masterDataFilesWrapper = (MasterDataFilesWrapper) obj;

        if (Validator.equals(_masterDataFiles,
                    masterDataFilesWrapper._masterDataFiles)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MasterDataFiles getWrappedMasterDataFiles() {
        return _masterDataFiles;
    }

    @Override
    public MasterDataFiles getWrappedModel() {
        return _masterDataFiles;
    }

    @Override
    public void resetOriginalValues() {
        _masterDataFiles.resetOriginalValues();
    }
}
