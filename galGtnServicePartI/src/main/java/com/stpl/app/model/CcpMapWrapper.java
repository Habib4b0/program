package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CcpMap}.
 * </p>
 *
 * @author
 * @see CcpMap
 * @generated
 */
public class CcpMapWrapper implements CcpMap, ModelWrapper<CcpMap> {
    private CcpMap _ccpMap;

    public CcpMapWrapper(CcpMap ccpMap) {
        _ccpMap = ccpMap;
    }

    @Override
    public Class<?> getModelClass() {
        return CcpMap.class;
    }

    @Override
    public String getModelClassName() {
        return CcpMap.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("ccpMapSid", getCcpMapSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }

        Integer ccpMapSid = (Integer) attributes.get("ccpMapSid");

        if (ccpMapSid != null) {
            setCcpMapSid(ccpMapSid);
        }
    }

    /**
    * Returns the primary key of this ccp map.
    *
    * @return the primary key of this ccp map
    */
    @Override
    public int getPrimaryKey() {
        return _ccpMap.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ccp map.
    *
    * @param primaryKey the primary key of this ccp map
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ccpMap.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ccp details sid of this ccp map.
    *
    * @return the ccp details sid of this ccp map
    */
    @Override
    public int getCcpDetailsSid() {
        return _ccpMap.getCcpDetailsSid();
    }

    /**
    * Sets the ccp details sid of this ccp map.
    *
    * @param ccpDetailsSid the ccp details sid of this ccp map
    */
    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpMap.setCcpDetailsSid(ccpDetailsSid);
    }

    /**
    * Returns the relationship level sid of this ccp map.
    *
    * @return the relationship level sid of this ccp map
    */
    @Override
    public int getRelationshipLevelSid() {
        return _ccpMap.getRelationshipLevelSid();
    }

    /**
    * Sets the relationship level sid of this ccp map.
    *
    * @param relationshipLevelSid the relationship level sid of this ccp map
    */
    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _ccpMap.setRelationshipLevelSid(relationshipLevelSid);
    }

    /**
    * Returns the ccp map sid of this ccp map.
    *
    * @return the ccp map sid of this ccp map
    */
    @Override
    public int getCcpMapSid() {
        return _ccpMap.getCcpMapSid();
    }

    /**
    * Sets the ccp map sid of this ccp map.
    *
    * @param ccpMapSid the ccp map sid of this ccp map
    */
    @Override
    public void setCcpMapSid(int ccpMapSid) {
        _ccpMap.setCcpMapSid(ccpMapSid);
    }

    @Override
    public boolean isNew() {
        return _ccpMap.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ccpMap.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ccpMap.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ccpMap.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ccpMap.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ccpMap.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ccpMap.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ccpMap.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ccpMap.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ccpMap.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ccpMap.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CcpMapWrapper((CcpMap) _ccpMap.clone());
    }

    @Override
    public int compareTo(CcpMap ccpMap) {
        return _ccpMap.compareTo(ccpMap);
    }

    @Override
    public int hashCode() {
        return _ccpMap.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CcpMap> toCacheModel() {
        return _ccpMap.toCacheModel();
    }

    @Override
    public CcpMap toEscapedModel() {
        return new CcpMapWrapper(_ccpMap.toEscapedModel());
    }

    @Override
    public CcpMap toUnescapedModel() {
        return new CcpMapWrapper(_ccpMap.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ccpMap.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ccpMap.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ccpMap.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CcpMapWrapper)) {
            return false;
        }

        CcpMapWrapper ccpMapWrapper = (CcpMapWrapper) obj;

        if (Validator.equals(_ccpMap, ccpMapWrapper._ccpMap)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CcpMap getWrappedCcpMap() {
        return _ccpMap;
    }

    @Override
    public CcpMap getWrappedModel() {
        return _ccpMap;
    }

    @Override
    public void resetOriginalValues() {
        _ccpMap.resetOriginalValues();
    }
}
