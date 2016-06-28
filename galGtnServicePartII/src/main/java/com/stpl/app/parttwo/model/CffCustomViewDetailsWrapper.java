package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffCustomViewDetails}.
 * </p>
 *
 * @author
 * @see CffCustomViewDetails
 * @generated
 */
public class CffCustomViewDetailsWrapper implements CffCustomViewDetails,
    ModelWrapper<CffCustomViewDetails> {
    private CffCustomViewDetails _cffCustomViewDetails;

    public CffCustomViewDetailsWrapper(
        CffCustomViewDetails cffCustomViewDetails) {
        _cffCustomViewDetails = cffCustomViewDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CffCustomViewDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CffCustomViewDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("hierarchyId", getHierarchyId());
        attributes.put("hierarchyIndicator", getHierarchyIndicator());
        attributes.put("cffCustomViewDetailsSid", getCffCustomViewDetailsSid());
        attributes.put("levelNo", getLevelNo());
        attributes.put("cffCustomViewMasterSid", getCffCustomViewMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer hierarchyId = (Integer) attributes.get("hierarchyId");

        if (hierarchyId != null) {
            setHierarchyId(hierarchyId);
        }

        String hierarchyIndicator = (String) attributes.get(
                "hierarchyIndicator");

        if (hierarchyIndicator != null) {
            setHierarchyIndicator(hierarchyIndicator);
        }

        Integer cffCustomViewDetailsSid = (Integer) attributes.get(
                "cffCustomViewDetailsSid");

        if (cffCustomViewDetailsSid != null) {
            setCffCustomViewDetailsSid(cffCustomViewDetailsSid);
        }

        Integer levelNo = (Integer) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }

        Integer cffCustomViewMasterSid = (Integer) attributes.get(
                "cffCustomViewMasterSid");

        if (cffCustomViewMasterSid != null) {
            setCffCustomViewMasterSid(cffCustomViewMasterSid);
        }
    }

    /**
    * Returns the primary key of this cff custom view details.
    *
    * @return the primary key of this cff custom view details
    */
    @Override
    public int getPrimaryKey() {
        return _cffCustomViewDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff custom view details.
    *
    * @param primaryKey the primary key of this cff custom view details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffCustomViewDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the hierarchy ID of this cff custom view details.
    *
    * @return the hierarchy ID of this cff custom view details
    */
    @Override
    public int getHierarchyId() {
        return _cffCustomViewDetails.getHierarchyId();
    }

    /**
    * Sets the hierarchy ID of this cff custom view details.
    *
    * @param hierarchyId the hierarchy ID of this cff custom view details
    */
    @Override
    public void setHierarchyId(int hierarchyId) {
        _cffCustomViewDetails.setHierarchyId(hierarchyId);
    }

    /**
    * Returns the hierarchy indicator of this cff custom view details.
    *
    * @return the hierarchy indicator of this cff custom view details
    */
    @Override
    public java.lang.String getHierarchyIndicator() {
        return _cffCustomViewDetails.getHierarchyIndicator();
    }

    /**
    * Sets the hierarchy indicator of this cff custom view details.
    *
    * @param hierarchyIndicator the hierarchy indicator of this cff custom view details
    */
    @Override
    public void setHierarchyIndicator(java.lang.String hierarchyIndicator) {
        _cffCustomViewDetails.setHierarchyIndicator(hierarchyIndicator);
    }

    /**
    * Returns the cff custom view details sid of this cff custom view details.
    *
    * @return the cff custom view details sid of this cff custom view details
    */
    @Override
    public int getCffCustomViewDetailsSid() {
        return _cffCustomViewDetails.getCffCustomViewDetailsSid();
    }

    /**
    * Sets the cff custom view details sid of this cff custom view details.
    *
    * @param cffCustomViewDetailsSid the cff custom view details sid of this cff custom view details
    */
    @Override
    public void setCffCustomViewDetailsSid(int cffCustomViewDetailsSid) {
        _cffCustomViewDetails.setCffCustomViewDetailsSid(cffCustomViewDetailsSid);
    }

    /**
    * Returns the level no of this cff custom view details.
    *
    * @return the level no of this cff custom view details
    */
    @Override
    public int getLevelNo() {
        return _cffCustomViewDetails.getLevelNo();
    }

    /**
    * Sets the level no of this cff custom view details.
    *
    * @param levelNo the level no of this cff custom view details
    */
    @Override
    public void setLevelNo(int levelNo) {
        _cffCustomViewDetails.setLevelNo(levelNo);
    }

    /**
    * Returns the cff custom view master sid of this cff custom view details.
    *
    * @return the cff custom view master sid of this cff custom view details
    */
    @Override
    public int getCffCustomViewMasterSid() {
        return _cffCustomViewDetails.getCffCustomViewMasterSid();
    }

    /**
    * Sets the cff custom view master sid of this cff custom view details.
    *
    * @param cffCustomViewMasterSid the cff custom view master sid of this cff custom view details
    */
    @Override
    public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
        _cffCustomViewDetails.setCffCustomViewMasterSid(cffCustomViewMasterSid);
    }

    @Override
    public boolean isNew() {
        return _cffCustomViewDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffCustomViewDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffCustomViewDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffCustomViewDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffCustomViewDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffCustomViewDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffCustomViewDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffCustomViewDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffCustomViewDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffCustomViewDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffCustomViewDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffCustomViewDetailsWrapper((CffCustomViewDetails) _cffCustomViewDetails.clone());
    }

    @Override
    public int compareTo(CffCustomViewDetails cffCustomViewDetails) {
        return _cffCustomViewDetails.compareTo(cffCustomViewDetails);
    }

    @Override
    public int hashCode() {
        return _cffCustomViewDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffCustomViewDetails> toCacheModel() {
        return _cffCustomViewDetails.toCacheModel();
    }

    @Override
    public CffCustomViewDetails toEscapedModel() {
        return new CffCustomViewDetailsWrapper(_cffCustomViewDetails.toEscapedModel());
    }

    @Override
    public CffCustomViewDetails toUnescapedModel() {
        return new CffCustomViewDetailsWrapper(_cffCustomViewDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffCustomViewDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffCustomViewDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffCustomViewDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffCustomViewDetailsWrapper)) {
            return false;
        }

        CffCustomViewDetailsWrapper cffCustomViewDetailsWrapper = (CffCustomViewDetailsWrapper) obj;

        if (Validator.equals(_cffCustomViewDetails,
                    cffCustomViewDetailsWrapper._cffCustomViewDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffCustomViewDetails getWrappedCffCustomViewDetails() {
        return _cffCustomViewDetails;
    }

    @Override
    public CffCustomViewDetails getWrappedModel() {
        return _cffCustomViewDetails;
    }

    @Override
    public void resetOriginalValues() {
        _cffCustomViewDetails.resetOriginalValues();
    }
}
