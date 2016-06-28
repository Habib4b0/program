package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffAdditionalInfo}.
 * </p>
 *
 * @author
 * @see CffAdditionalInfo
 * @generated
 */
public class CffAdditionalInfoWrapper implements CffAdditionalInfo,
    ModelWrapper<CffAdditionalInfo> {
    private CffAdditionalInfo _cffAdditionalInfo;

    public CffAdditionalInfoWrapper(CffAdditionalInfo cffAdditionalInfo) {
        _cffAdditionalInfo = cffAdditionalInfo;
    }

    @Override
    public Class<?> getModelClass() {
        return CffAdditionalInfo.class;
    }

    @Override
    public String getModelClassName() {
        return CffAdditionalInfo.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("cffAdditionalInfoSid", getCffAdditionalInfoSid());
        attributes.put("notes", getNotes());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer cffAdditionalInfoSid = (Integer) attributes.get(
                "cffAdditionalInfoSid");

        if (cffAdditionalInfoSid != null) {
            setCffAdditionalInfoSid(cffAdditionalInfoSid);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }
    }

    /**
    * Returns the primary key of this cff additional info.
    *
    * @return the primary key of this cff additional info
    */
    @Override
    public int getPrimaryKey() {
        return _cffAdditionalInfo.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff additional info.
    *
    * @param primaryKey the primary key of this cff additional info
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffAdditionalInfo.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this cff additional info.
    *
    * @return the created date of this cff additional info
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cffAdditionalInfo.getCreatedDate();
    }

    /**
    * Sets the created date of this cff additional info.
    *
    * @param createdDate the created date of this cff additional info
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cffAdditionalInfo.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this cff additional info.
    *
    * @return the created by of this cff additional info
    */
    @Override
    public int getCreatedBy() {
        return _cffAdditionalInfo.getCreatedBy();
    }

    /**
    * Sets the created by of this cff additional info.
    *
    * @param createdBy the created by of this cff additional info
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cffAdditionalInfo.setCreatedBy(createdBy);
    }

    /**
    * Returns the cff master sid of this cff additional info.
    *
    * @return the cff master sid of this cff additional info
    */
    @Override
    public int getCffMasterSid() {
        return _cffAdditionalInfo.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff additional info.
    *
    * @param cffMasterSid the cff master sid of this cff additional info
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffAdditionalInfo.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the cff additional info sid of this cff additional info.
    *
    * @return the cff additional info sid of this cff additional info
    */
    @Override
    public int getCffAdditionalInfoSid() {
        return _cffAdditionalInfo.getCffAdditionalInfoSid();
    }

    /**
    * Sets the cff additional info sid of this cff additional info.
    *
    * @param cffAdditionalInfoSid the cff additional info sid of this cff additional info
    */
    @Override
    public void setCffAdditionalInfoSid(int cffAdditionalInfoSid) {
        _cffAdditionalInfo.setCffAdditionalInfoSid(cffAdditionalInfoSid);
    }

    /**
    * Returns the notes of this cff additional info.
    *
    * @return the notes of this cff additional info
    */
    @Override
    public java.lang.String getNotes() {
        return _cffAdditionalInfo.getNotes();
    }

    /**
    * Sets the notes of this cff additional info.
    *
    * @param notes the notes of this cff additional info
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _cffAdditionalInfo.setNotes(notes);
    }

    @Override
    public boolean isNew() {
        return _cffAdditionalInfo.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffAdditionalInfo.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffAdditionalInfo.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffAdditionalInfo.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffAdditionalInfo.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffAdditionalInfo.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffAdditionalInfo.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffAdditionalInfo.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffAdditionalInfo.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffAdditionalInfo.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffAdditionalInfo.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffAdditionalInfoWrapper((CffAdditionalInfo) _cffAdditionalInfo.clone());
    }

    @Override
    public int compareTo(CffAdditionalInfo cffAdditionalInfo) {
        return _cffAdditionalInfo.compareTo(cffAdditionalInfo);
    }

    @Override
    public int hashCode() {
        return _cffAdditionalInfo.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffAdditionalInfo> toCacheModel() {
        return _cffAdditionalInfo.toCacheModel();
    }

    @Override
    public CffAdditionalInfo toEscapedModel() {
        return new CffAdditionalInfoWrapper(_cffAdditionalInfo.toEscapedModel());
    }

    @Override
    public CffAdditionalInfo toUnescapedModel() {
        return new CffAdditionalInfoWrapper(_cffAdditionalInfo.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffAdditionalInfo.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffAdditionalInfo.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffAdditionalInfo.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffAdditionalInfoWrapper)) {
            return false;
        }

        CffAdditionalInfoWrapper cffAdditionalInfoWrapper = (CffAdditionalInfoWrapper) obj;

        if (Validator.equals(_cffAdditionalInfo,
                    cffAdditionalInfoWrapper._cffAdditionalInfo)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffAdditionalInfo getWrappedCffAdditionalInfo() {
        return _cffAdditionalInfo;
    }

    @Override
    public CffAdditionalInfo getWrappedModel() {
        return _cffAdditionalInfo;
    }

    @Override
    public void resetOriginalValues() {
        _cffAdditionalInfo.resetOriginalValues();
    }
}
