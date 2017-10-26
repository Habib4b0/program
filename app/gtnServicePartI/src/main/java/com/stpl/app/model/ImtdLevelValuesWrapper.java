package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImtdLevelValues}.
 * </p>
 *
 * @author
 * @see ImtdLevelValues
 * @generated
 */
public class ImtdLevelValuesWrapper implements ImtdLevelValues,
    ModelWrapper<ImtdLevelValues> {
    private ImtdLevelValues _imtdLevelValues;

    public ImtdLevelValuesWrapper(ImtdLevelValues imtdLevelValues) {
        _imtdLevelValues = imtdLevelValues;
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdLevelValues.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdLevelValues.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("levelValues", getLevelValues());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("imtdLevelValuesSid", getImtdLevelValuesSid());
        attributes.put("levelNo", getLevelNo());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("levelName", getLevelName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String levelValues = (String) attributes.get("levelValues");

        if (levelValues != null) {
            setLevelValues(levelValues);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer imtdLevelValuesSid = (Integer) attributes.get(
                "imtdLevelValuesSid");

        if (imtdLevelValuesSid != null) {
            setImtdLevelValuesSid(imtdLevelValuesSid);
        }

        Integer levelNo = (Integer) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String levelName = (String) attributes.get("levelName");

        if (levelName != null) {
            setLevelName(levelName);
        }
    }

    /**
    * Returns the primary key of this imtd level values.
    *
    * @return the primary key of this imtd level values
    */
    @Override
    public int getPrimaryKey() {
        return _imtdLevelValues.getPrimaryKey();
    }

    /**
    * Sets the primary key of this imtd level values.
    *
    * @param primaryKey the primary key of this imtd level values
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _imtdLevelValues.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the level values of this imtd level values.
    *
    * @return the level values of this imtd level values
    */
    @Override
    public java.lang.String getLevelValues() {
        return _imtdLevelValues.getLevelValues();
    }

    /**
    * Sets the level values of this imtd level values.
    *
    * @param levelValues the level values of this imtd level values
    */
    @Override
    public void setLevelValues(java.lang.String levelValues) {
        _imtdLevelValues.setLevelValues(levelValues);
    }

    /**
    * Returns the created date of this imtd level values.
    *
    * @return the created date of this imtd level values
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _imtdLevelValues.getCreatedDate();
    }

    /**
    * Sets the created date of this imtd level values.
    *
    * @param createdDate the created date of this imtd level values
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _imtdLevelValues.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this imtd level values.
    *
    * @return the created by of this imtd level values
    */
    @Override
    public int getCreatedBy() {
        return _imtdLevelValues.getCreatedBy();
    }

    /**
    * Sets the created by of this imtd level values.
    *
    * @param createdBy the created by of this imtd level values
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _imtdLevelValues.setCreatedBy(createdBy);
    }

    /**
    * Returns the imtd level values sid of this imtd level values.
    *
    * @return the imtd level values sid of this imtd level values
    */
    @Override
    public int getImtdLevelValuesSid() {
        return _imtdLevelValues.getImtdLevelValuesSid();
    }

    /**
    * Sets the imtd level values sid of this imtd level values.
    *
    * @param imtdLevelValuesSid the imtd level values sid of this imtd level values
    */
    @Override
    public void setImtdLevelValuesSid(int imtdLevelValuesSid) {
        _imtdLevelValues.setImtdLevelValuesSid(imtdLevelValuesSid);
    }

    /**
    * Returns the level no of this imtd level values.
    *
    * @return the level no of this imtd level values
    */
    @Override
    public int getLevelNo() {
        return _imtdLevelValues.getLevelNo();
    }

    /**
    * Sets the level no of this imtd level values.
    *
    * @param levelNo the level no of this imtd level values
    */
    @Override
    public void setLevelNo(int levelNo) {
        _imtdLevelValues.setLevelNo(levelNo);
    }

    /**
    * Returns the version no of this imtd level values.
    *
    * @return the version no of this imtd level values
    */
    @Override
    public int getVersionNo() {
        return _imtdLevelValues.getVersionNo();
    }

    /**
    * Sets the version no of this imtd level values.
    *
    * @param versionNo the version no of this imtd level values
    */
    @Override
    public void setVersionNo(int versionNo) {
        _imtdLevelValues.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this imtd level values.
    *
    * @return the modified by of this imtd level values
    */
    @Override
    public int getModifiedBy() {
        return _imtdLevelValues.getModifiedBy();
    }

    /**
    * Sets the modified by of this imtd level values.
    *
    * @param modifiedBy the modified by of this imtd level values
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _imtdLevelValues.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this imtd level values.
    *
    * @return the modified date of this imtd level values
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _imtdLevelValues.getModifiedDate();
    }

    /**
    * Sets the modified date of this imtd level values.
    *
    * @param modifiedDate the modified date of this imtd level values
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _imtdLevelValues.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the level name of this imtd level values.
    *
    * @return the level name of this imtd level values
    */
    @Override
    public java.lang.String getLevelName() {
        return _imtdLevelValues.getLevelName();
    }

    /**
    * Sets the level name of this imtd level values.
    *
    * @param levelName the level name of this imtd level values
    */
    @Override
    public void setLevelName(java.lang.String levelName) {
        _imtdLevelValues.setLevelName(levelName);
    }

    @Override
    public boolean isNew() {
        return _imtdLevelValues.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _imtdLevelValues.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _imtdLevelValues.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _imtdLevelValues.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _imtdLevelValues.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _imtdLevelValues.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _imtdLevelValues.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _imtdLevelValues.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _imtdLevelValues.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _imtdLevelValues.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _imtdLevelValues.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImtdLevelValuesWrapper((ImtdLevelValues) _imtdLevelValues.clone());
    }

    @Override
    public int compareTo(ImtdLevelValues imtdLevelValues) {
        return _imtdLevelValues.compareTo(imtdLevelValues);
    }

    @Override
    public int hashCode() {
        return _imtdLevelValues.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ImtdLevelValues> toCacheModel() {
        return _imtdLevelValues.toCacheModel();
    }

    @Override
    public ImtdLevelValues toEscapedModel() {
        return new ImtdLevelValuesWrapper(_imtdLevelValues.toEscapedModel());
    }

    @Override
    public ImtdLevelValues toUnescapedModel() {
        return new ImtdLevelValuesWrapper(_imtdLevelValues.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _imtdLevelValues.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _imtdLevelValues.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _imtdLevelValues.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdLevelValuesWrapper)) {
            return false;
        }

        ImtdLevelValuesWrapper imtdLevelValuesWrapper = (ImtdLevelValuesWrapper) obj;

        if (Validator.equals(_imtdLevelValues,
                    imtdLevelValuesWrapper._imtdLevelValues)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImtdLevelValues getWrappedImtdLevelValues() {
        return _imtdLevelValues;
    }

    @Override
    public ImtdLevelValues getWrappedModel() {
        return _imtdLevelValues;
    }

    @Override
    public void resetOriginalValues() {
        _imtdLevelValues.resetOriginalValues();
    }
}
