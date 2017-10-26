package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffProdHierarchy}.
 * </p>
 *
 * @author
 * @see CffProdHierarchy
 * @generated
 */
public class CffProdHierarchyWrapper implements CffProdHierarchy,
    ModelWrapper<CffProdHierarchy> {
    private CffProdHierarchy _cffProdHierarchy;

    public CffProdHierarchyWrapper(CffProdHierarchy cffProdHierarchy) {
        _cffProdHierarchy = cffProdHierarchy;
    }

    @Override
    public Class<?> getModelClass() {
        return CffProdHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return CffProdHierarchy.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("cffProdHierarchySid", getCffProdHierarchySid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }

        Integer cffProdHierarchySid = (Integer) attributes.get(
                "cffProdHierarchySid");

        if (cffProdHierarchySid != null) {
            setCffProdHierarchySid(cffProdHierarchySid);
        }
    }

    /**
    * Returns the primary key of this cff prod hierarchy.
    *
    * @return the primary key of this cff prod hierarchy
    */
    @Override
    public int getPrimaryKey() {
        return _cffProdHierarchy.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff prod hierarchy.
    *
    * @param primaryKey the primary key of this cff prod hierarchy
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffProdHierarchy.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the cff master sid of this cff prod hierarchy.
    *
    * @return the cff master sid of this cff prod hierarchy
    */
    @Override
    public int getCffMasterSid() {
        return _cffProdHierarchy.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff prod hierarchy.
    *
    * @param cffMasterSid the cff master sid of this cff prod hierarchy
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffProdHierarchy.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the relationship level sid of this cff prod hierarchy.
    *
    * @return the relationship level sid of this cff prod hierarchy
    */
    @Override
    public int getRelationshipLevelSid() {
        return _cffProdHierarchy.getRelationshipLevelSid();
    }

    /**
    * Sets the relationship level sid of this cff prod hierarchy.
    *
    * @param relationshipLevelSid the relationship level sid of this cff prod hierarchy
    */
    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _cffProdHierarchy.setRelationshipLevelSid(relationshipLevelSid);
    }

    /**
    * Returns the cff prod hierarchy sid of this cff prod hierarchy.
    *
    * @return the cff prod hierarchy sid of this cff prod hierarchy
    */
    @Override
    public int getCffProdHierarchySid() {
        return _cffProdHierarchy.getCffProdHierarchySid();
    }

    /**
    * Sets the cff prod hierarchy sid of this cff prod hierarchy.
    *
    * @param cffProdHierarchySid the cff prod hierarchy sid of this cff prod hierarchy
    */
    @Override
    public void setCffProdHierarchySid(int cffProdHierarchySid) {
        _cffProdHierarchy.setCffProdHierarchySid(cffProdHierarchySid);
    }

    @Override
    public boolean isNew() {
        return _cffProdHierarchy.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffProdHierarchy.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffProdHierarchy.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffProdHierarchy.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffProdHierarchy.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffProdHierarchy.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffProdHierarchy.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffProdHierarchy.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffProdHierarchy.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffProdHierarchy.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffProdHierarchy.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffProdHierarchyWrapper((CffProdHierarchy) _cffProdHierarchy.clone());
    }

    @Override
    public int compareTo(CffProdHierarchy cffProdHierarchy) {
        return _cffProdHierarchy.compareTo(cffProdHierarchy);
    }

    @Override
    public int hashCode() {
        return _cffProdHierarchy.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffProdHierarchy> toCacheModel() {
        return _cffProdHierarchy.toCacheModel();
    }

    @Override
    public CffProdHierarchy toEscapedModel() {
        return new CffProdHierarchyWrapper(_cffProdHierarchy.toEscapedModel());
    }

    @Override
    public CffProdHierarchy toUnescapedModel() {
        return new CffProdHierarchyWrapper(_cffProdHierarchy.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffProdHierarchy.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffProdHierarchy.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffProdHierarchy.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffProdHierarchyWrapper)) {
            return false;
        }

        CffProdHierarchyWrapper cffProdHierarchyWrapper = (CffProdHierarchyWrapper) obj;

        if (Validator.equals(_cffProdHierarchy,
                    cffProdHierarchyWrapper._cffProdHierarchy)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffProdHierarchy getWrappedCffProdHierarchy() {
        return _cffProdHierarchy;
    }

    @Override
    public CffProdHierarchy getWrappedModel() {
        return _cffProdHierarchy;
    }

    @Override
    public void resetOriginalValues() {
        _cffProdHierarchy.resetOriginalValues();
    }
}
