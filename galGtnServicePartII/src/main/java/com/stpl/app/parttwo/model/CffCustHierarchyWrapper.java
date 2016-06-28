package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffCustHierarchy}.
 * </p>
 *
 * @author
 * @see CffCustHierarchy
 * @generated
 */
public class CffCustHierarchyWrapper implements CffCustHierarchy,
    ModelWrapper<CffCustHierarchy> {
    private CffCustHierarchy _cffCustHierarchy;

    public CffCustHierarchyWrapper(CffCustHierarchy cffCustHierarchy) {
        _cffCustHierarchy = cffCustHierarchy;
    }

    @Override
    public Class<?> getModelClass() {
        return CffCustHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return CffCustHierarchy.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cffCustHierarchySid", getCffCustHierarchySid());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer cffCustHierarchySid = (Integer) attributes.get(
                "cffCustHierarchySid");

        if (cffCustHierarchySid != null) {
            setCffCustHierarchySid(cffCustHierarchySid);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }
    }

    /**
    * Returns the primary key of this cff cust hierarchy.
    *
    * @return the primary key of this cff cust hierarchy
    */
    @Override
    public int getPrimaryKey() {
        return _cffCustHierarchy.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff cust hierarchy.
    *
    * @param primaryKey the primary key of this cff cust hierarchy
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffCustHierarchy.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the cff cust hierarchy sid of this cff cust hierarchy.
    *
    * @return the cff cust hierarchy sid of this cff cust hierarchy
    */
    @Override
    public int getCffCustHierarchySid() {
        return _cffCustHierarchy.getCffCustHierarchySid();
    }

    /**
    * Sets the cff cust hierarchy sid of this cff cust hierarchy.
    *
    * @param cffCustHierarchySid the cff cust hierarchy sid of this cff cust hierarchy
    */
    @Override
    public void setCffCustHierarchySid(int cffCustHierarchySid) {
        _cffCustHierarchy.setCffCustHierarchySid(cffCustHierarchySid);
    }

    /**
    * Returns the cff master sid of this cff cust hierarchy.
    *
    * @return the cff master sid of this cff cust hierarchy
    */
    @Override
    public int getCffMasterSid() {
        return _cffCustHierarchy.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff cust hierarchy.
    *
    * @param cffMasterSid the cff master sid of this cff cust hierarchy
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffCustHierarchy.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the relationship level sid of this cff cust hierarchy.
    *
    * @return the relationship level sid of this cff cust hierarchy
    */
    @Override
    public int getRelationshipLevelSid() {
        return _cffCustHierarchy.getRelationshipLevelSid();
    }

    /**
    * Sets the relationship level sid of this cff cust hierarchy.
    *
    * @param relationshipLevelSid the relationship level sid of this cff cust hierarchy
    */
    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _cffCustHierarchy.setRelationshipLevelSid(relationshipLevelSid);
    }

    @Override
    public boolean isNew() {
        return _cffCustHierarchy.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffCustHierarchy.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffCustHierarchy.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffCustHierarchy.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffCustHierarchy.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffCustHierarchy.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffCustHierarchy.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffCustHierarchy.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffCustHierarchy.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffCustHierarchy.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffCustHierarchy.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffCustHierarchyWrapper((CffCustHierarchy) _cffCustHierarchy.clone());
    }

    @Override
    public int compareTo(CffCustHierarchy cffCustHierarchy) {
        return _cffCustHierarchy.compareTo(cffCustHierarchy);
    }

    @Override
    public int hashCode() {
        return _cffCustHierarchy.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffCustHierarchy> toCacheModel() {
        return _cffCustHierarchy.toCacheModel();
    }

    @Override
    public CffCustHierarchy toEscapedModel() {
        return new CffCustHierarchyWrapper(_cffCustHierarchy.toEscapedModel());
    }

    @Override
    public CffCustHierarchy toUnescapedModel() {
        return new CffCustHierarchyWrapper(_cffCustHierarchy.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffCustHierarchy.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffCustHierarchy.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffCustHierarchy.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffCustHierarchyWrapper)) {
            return false;
        }

        CffCustHierarchyWrapper cffCustHierarchyWrapper = (CffCustHierarchyWrapper) obj;

        if (Validator.equals(_cffCustHierarchy,
                    cffCustHierarchyWrapper._cffCustHierarchy)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffCustHierarchy getWrappedCffCustHierarchy() {
        return _cffCustHierarchy;
    }

    @Override
    public CffCustHierarchy getWrappedModel() {
        return _cffCustHierarchy;
    }

    @Override
    public void resetOriginalValues() {
        _cffCustHierarchy.resetOriginalValues();
    }
}
